package by.bsuir.nika.lombard.service.impl;

import by.bsuir.nika.lombard.dao.ClientDao;
import by.bsuir.nika.lombard.dao.DaoProvider;
import by.bsuir.nika.lombard.dao.ItemDao;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.dao.model.util.Status;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.model.util.Report;
import by.bsuir.nika.lombard.service.util.validator.ItemValidator;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ItemService instance = new ItemServiceImpl();

    /*Экземпляр объекта ClientDaoImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientDao clientDao = DaoProvider.getInstance().getClientDao();

    /*Экземпляр объекта ItemDaoImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemDao itemDao = DaoProvider.getInstance().getItemDao();

    //Сообщение, которое помещается в Exception в случае ошибки создания предмета
    private static final String MESSAGE_ITEM_VALIDATION_EXCEPTION = "Item validation failed";

    //Сообщение, которое помещается в Exception в случае ошибки создания предмета
    private static final String MESSAGE_CREATE_EXCEPTION = "Create item failed";

    //Сообщение, которое помещается в Exception в случае, если предмет по ID не найден
    private static final String MESSAGE_GET_BY_ID_NOT_FOUND = "Item by ID not found";

    //Сообщение, которое помещается в Exception в случае, если предмет по ID не найден
    private static final String MESSAGE_GET_CLIENT_BY_ID_NOT_FOUND = "ItemService: Client by ID not found";

    //Сообщение, которое помещается в Exception в случае ошибки обновления предмета
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update item failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения предмета
    private static final String MESSAGE_GET_EXCEPTION = "Get item failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех предметов клиента
    private static final String MESSAGE_GET_BY_CLIENT_ID_EXCEPTION = "Get items by client ID failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех предметов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all items failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения отчета
    private static final String MESSAGE_GET_REPORT_EXCEPTION = "Get report failed";

    //ID статуса предмета "Реализован"
    private static final int STATUS_REALISED = 2;

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ItemServiceImpl() {
    }

    //Метод для создания предмета
    @Override
    public void create(Item item) throws ServiceException {
        if (!ItemValidator.validate(item)) {
            throw new ServiceException(MESSAGE_ITEM_VALIDATION_EXCEPTION);
        }
        try {
            itemDao.create(item);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_CREATE_EXCEPTION, e);
        }
    }

    //Метод для удаления предмета
    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (itemDao.get(id).isEmpty()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            itemDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для реализации предмета
    @Override
    public void realise(int id) throws ServiceException {
        try {
            Item item = itemDao.get(id).orElseThrow(() -> new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND));
            item.setStatus(Status.builder().id(STATUS_REALISED).build());
            update(item);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для получения отчета
    @Override
    public Report getReport() throws ServiceException {
        try {
            List<Item> itemList = itemDao.getAll();

            List<Item> acceptedItems = itemList.stream().filter(item -> item.getStatus().getId() == 1)
                    .collect(Collectors.toList());

            List<Item> expiredItems = acceptedItems.stream().filter(item -> item.getDaysLeft() < 0)
                    .collect(Collectors.toList());

            List<Item> realisedItems = itemList.stream().filter(item -> item.getStatus().getId() == 2)
                    .collect(Collectors.toList());

            Integer acceptedItemsQuantity = acceptedItems.size();
            Integer acceptedItemsPrice = acceptedItems.stream().mapToInt(Item::getPrice).sum();
            Integer expiredItemsQuantity = expiredItems.size();
            Integer expiredItemsPrice = expiredItems.stream().mapToInt(Item::getPrice).sum();
            Integer realisedItemsQuantity = realisedItems.size();
            Integer realisedItemsPrice = realisedItems.stream().mapToInt(Item::getPrice).sum();

            Double realisedItemsProfit = (double) realisedItemsPrice * 0.1D;

            return Report.builder()
                    .acceptedItemsQuantity(acceptedItemsQuantity)
                    .acceptedItemsPrice(acceptedItemsPrice)
                    .expiredItemsQuantity(expiredItemsQuantity)
                    .expiredItemsPrice(expiredItemsPrice)
                    .realisedItemsQuantity(realisedItemsQuantity)
                    .realisedItemsProfit(realisedItemsProfit)
                    .build();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_REPORT_EXCEPTION, e);
        }
    }

    //Метод для обновления предмета
    @Override
    public Item update(Item item) throws ServiceException {
        try {
            if (itemDao.get(item.getId()).isEmpty()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            if (!ItemValidator.validate(item)) {
                throw new ServiceException(MESSAGE_ITEM_VALIDATION_EXCEPTION);
            }
            return itemDao.update(item);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для получения предмета по его ID
    @Override
    public Item get(int id) throws ServiceException {
        try {
            Item item = itemDao.get(id).orElseThrow(() -> new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND));
            item.setOwner(clientDao.get(item.getOwner().getId()).get());

            return item;
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_EXCEPTION, e);
        }
    }

    //Метод для получения всех предметов пользователя
    @Override
    public List<Item> getByClientId(int id) throws ServiceException {
        try {
            if (clientDao.get(id).isEmpty()) {
                throw new ServiceException(MESSAGE_GET_CLIENT_BY_ID_NOT_FOUND);
            }
            List<Item> itemList = itemDao.getByClientId(id);

            for (Item item : itemList) {
                item.setOwner(clientDao.get(item.getOwner().getId()).get());
            }

            return itemList;
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_BY_CLIENT_ID_EXCEPTION, e);
        }
    }

    //Метод для получения всех предметов
    @Override
    public List<Item> getAll() throws ServiceException {
        try {
            List<Item> itemList = itemDao.getAll();
            for (Item item : itemList) {
                item.setOwner(clientDao.get(item.getOwner().getId()).get());
            }

            return itemList;
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

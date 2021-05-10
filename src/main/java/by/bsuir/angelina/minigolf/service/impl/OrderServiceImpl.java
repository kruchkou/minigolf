package by.bsuir.angelina.minigolf.service.impl;

import by.bsuir.angelina.minigolf.service.util.validator.OrderValidator;
import by.bsuir.angelina.minigolf.dao.OrderDao;
import by.bsuir.angelina.minigolf.dao.DaoProvider;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.service.OrderService;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final OrderService instance = new OrderServiceImpl();

    /*Экземпляр объекта OrderDaoImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private static final OrderDao orderDao = DaoProvider.getInstance().getOrderDao();

    //Сообщение, которое помещается в Exception в случае ошибки создания брони
    private static final String MESSAGE_VALIDATION_EXCEPTION = "Order validation failed";

    //Сообщение, которое помещается в Exception в случае ошибки создания брони
    private static final String MESSAGE_CREATE_EXCEPTION = "Create Order failed";

    //Сообщение, которое помещается в Exception в случае, если клиент по ID не найден
    private static final String MESSAGE_GET_BY_ID_NOT_FOUND = "Order by ID not found";

    //Сообщение, которое помещается в Exception в случае ошибки обновления брони
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update Order failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения брони
    private static final String MESSAGE_GET_EXCEPTION = "Get Order failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех броней
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all Orders failed";


    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private OrderServiceImpl() {
    }

    //Метод для создания брони
    @Override
    public void create(Order order) throws ServiceException {
        if (!OrderValidator.validate(order)) {
            throw new ServiceException(MESSAGE_VALIDATION_EXCEPTION);
        }
        try {
            orderDao.create(order);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_CREATE_EXCEPTION, e);
        }
    }

    //Метод для удаления брони
    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (!orderDao.get(id).isPresent()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            orderDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для обновления брони
    @Override
    public Order update(Order order) throws ServiceException {
        try {
            if (!orderDao.get(order.getId()).isPresent()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            if (!OrderValidator.validate(order)) {
                throw new ServiceException(MESSAGE_VALIDATION_EXCEPTION);
            }
            return orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для получения брони по его ID
    @Override
    public Order get(int id) throws ServiceException {
        try {
            return orderDao.get(id).orElseThrow(() -> new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND));
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_EXCEPTION, e);
        }
    }

    //Метод для получения всех броней
    @Override
    public List<Order> getAll() throws ServiceException {
        try {
            return orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

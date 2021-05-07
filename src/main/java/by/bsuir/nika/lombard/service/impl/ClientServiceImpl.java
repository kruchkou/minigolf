package by.bsuir.nika.lombard.service.impl;

import by.bsuir.nika.lombard.dao.ClientDao;
import by.bsuir.nika.lombard.dao.DaoProvider;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.util.validator.ClientValidator;
import lombok.Getter;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ClientService instance = new ClientServiceImpl();

    /*Экземпляр объекта ClientDaoImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientDao clientDao = DaoProvider.getInstance().getClientDao();

    //Сообщение, которое помещается в Exception в случае ошибки создания клиента
    private static final String MESSAGE_CLIENT_VALIDATION_EXCEPTION = "Client validation failed";

    //Сообщение, которое помещается в Exception в случае ошибки создания клиента
    private static final String MESSAGE_CREATE_EXCEPTION = "Create client failed";

    //Сообщение, которое помещается в Exception в случае, если клиент по ID не найден
    private static final String MESSAGE_GET_BY_ID_NOT_FOUND = "Сlient by ID not found";

    //Сообщение, которое помещается в Exception в случае ошибки обновления клиента
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update client failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения клиента
    private static final String MESSAGE_GET_EXCEPTION = "Get client failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех клиентов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all clients failed";


    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ClientServiceImpl() {
    }

    //Метод для создания клиента
    @Override
    public void create(Client client) throws ServiceException {
        if (!ClientValidator.validate(client)) {
            throw new ServiceException(MESSAGE_CLIENT_VALIDATION_EXCEPTION);
        }
        try {
            clientDao.create(client);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_CREATE_EXCEPTION, e);
        }
    }

    //Метод для удаления клиента
    @Override
    public void delete(int id) throws ServiceException {
        try {
            if (!clientDao.get(id).isPresent()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            clientDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для обновления клиента
    @Override
    public Client update(Client client) throws ServiceException {
        try {
            if (!clientDao.get(client.getId()).isPresent()) {
                throw new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND);
            }
            if (!ClientValidator.validate(client)) {
                throw new ServiceException(MESSAGE_CLIENT_VALIDATION_EXCEPTION);
            }
            return clientDao.update(client);
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    //Метод для получения клиента по его ID
    @Override
    public Client get(int id) throws ServiceException {
        try {
            return clientDao.get(id).orElseThrow(() -> new ServiceException(MESSAGE_GET_BY_ID_NOT_FOUND));
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_EXCEPTION, e);
        }
    }

    //Метод для получения всех клиентов
    @Override
    public List<Client> getAll() throws ServiceException {
        try {
            return clientDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

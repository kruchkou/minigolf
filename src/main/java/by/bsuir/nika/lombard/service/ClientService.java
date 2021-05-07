package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import java.util.List;

public interface ClientService {

    //Метод для создания клиента
    void create(Client client) throws ServiceException;

    //Метод для удаления клиента
    void delete(int id) throws ServiceException;

    //Метод для обновления клиента
    Client update(Client client) throws ServiceException;

    //Метод для получения клиента по его ID
    Client get(int id) throws ServiceException;

    //Метод для получения всех клиентов
    List<Client> getAll() throws ServiceException;
}

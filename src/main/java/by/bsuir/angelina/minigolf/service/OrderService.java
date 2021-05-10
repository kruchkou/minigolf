package by.bsuir.angelina.minigolf.service;

import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;

import java.util.List;

public interface OrderService {

    //Метод для создания клиента
    void create(Order order) throws ServiceException;

    //Метод для удаления клиента
    void delete(int id) throws ServiceException;

    //Метод для обновления клиента
    Order update(Order order) throws ServiceException;

    //Метод для получения клиента по его ID
    Order get(int id) throws ServiceException;

    //Метод для получения всех клиентов
    List<Order> getAll() throws ServiceException;
}

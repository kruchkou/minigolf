package by.bsuir.angelina.minigolf.dao;

import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    //Метод для создания брони
    void create(Order order) throws DaoException;

    //Метод для обновления брони
    Order update(Order order) throws DaoException;

    //Метод для удаления брони
    void delete(int id) throws DaoException;

    //Метод для получения брони по его ID
    Optional<Order> get(int id) throws DaoException;

    //Метод для получения всех броней
    List<Order> getAll() throws DaoException;

}

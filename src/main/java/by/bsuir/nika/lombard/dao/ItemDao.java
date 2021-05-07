package by.bsuir.nika.lombard.dao;

import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    //Метод для создания предмета
    void create(Item item) throws DaoException;

    //Метод для обновления предмета
    Item update(Item item) throws DaoException;

    //Метод для удаления предмета
    void delete(int id) throws DaoException;

    //Метод для получения предмета по его ID
    Optional<Item> get(int id) throws DaoException;

    //Метод для получения предметов клиента
    List<Item> getByClientId(int id) throws DaoException;

    //Метод для получения всех предметов
    List<Item> getAll() throws DaoException;

}

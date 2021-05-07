package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.model.util.Report;

import java.util.List;

public interface ItemService {

    //Метод для создания предмета
    void create(Item item) throws ServiceException;

    //Метод для удаления предмета
    void delete(int id) throws ServiceException;

    //Метод для реализации предмета
    void realise(int id) throws ServiceException;

    //Метод для получения отчета
    Report getReport() throws ServiceException;

    //Метод для обновления предмета
    Item update(Item item) throws ServiceException;

    //Метод для получения предмета по его ID
    Item get(int id) throws ServiceException;

    //Метод для получения всех предметов пользователя
    List<Item> getByClientId(int id) throws ServiceException;

    //Метод для получения всех предметов
    List<Item> getAll() throws ServiceException;
}

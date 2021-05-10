package by.bsuir.angelina.minigolf.dao;

import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;

import java.util.List;

public interface OfficeDao {

    //Метод для получения всех офисов
    List<Office> getAll() throws DaoException;

}

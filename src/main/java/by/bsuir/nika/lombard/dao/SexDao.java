package by.bsuir.nika.lombard.dao;

import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Sex;

import java.util.List;

public interface SexDao {

    //Метод для получения всех полов
    List<Sex> getAll() throws DaoException;

}

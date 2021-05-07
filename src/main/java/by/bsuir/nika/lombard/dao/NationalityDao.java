package by.bsuir.nika.lombard.dao;

import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Nationality;

import java.util.List;

public interface NationalityDao {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws DaoException;

}

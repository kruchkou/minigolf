package by.bsuir.nika.lombard.dao;

import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Category;

import java.util.List;

public interface CategoryDao {

    //Метод для получения всех категорий предметов
    List<Category> getAll() throws DaoException;

}

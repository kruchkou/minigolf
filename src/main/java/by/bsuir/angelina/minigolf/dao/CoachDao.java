package by.bsuir.angelina.minigolf.dao;

import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Coach;

import java.util.List;

public interface CoachDao {

    //Метод для получения всех категорий предметов
    List<Coach> getAll() throws DaoException;

}

package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.dao.model.entity.Category;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import java.util.List;

public interface CategoryService {

    //Метод для получения всех категорий предметов
    List<Category> getAll() throws ServiceException;

}

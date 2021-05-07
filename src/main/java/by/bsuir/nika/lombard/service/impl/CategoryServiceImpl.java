package by.bsuir.nika.lombard.service.impl;

import by.bsuir.nika.lombard.dao.CategoryDao;
import by.bsuir.nika.lombard.dao.DaoProvider;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Category;
import by.bsuir.nika.lombard.service.CategoryService;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CategoryServiceImpl instance = new CategoryServiceImpl();

    /*Экземпляр объекта CategoryDaoImpl, который предоставляет методы для
    взаимодействия с данными категорий предметов */
    private static final CategoryDao categoryDao = DaoProvider.getInstance().getCategoryDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех категорий предметов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CategoryServiceImpl() {
    }

    //Метод для получения всех категорий предметов
    @Override
    public List<Category> getAll() throws ServiceException {
        try {
            return categoryDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

package by.bsuir.nika.lombard.service.impl;

import by.bsuir.nika.lombard.dao.DaoProvider;
import by.bsuir.nika.lombard.dao.NationalityDao;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Nationality;
import by.bsuir.nika.lombard.service.NationalityService;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class NationalityServiceImpl implements NationalityService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final NationalityServiceImpl instance = new NationalityServiceImpl();

    /*Экземпляр объекта NationalityDaoImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    private static final NationalityDao nationalityDao = DaoProvider.getInstance().getNationalityDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех национальностей
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private NationalityServiceImpl() {
    }

    //Метод для получения всех национальностей
    @Override
    public List<Nationality> getAll() throws ServiceException {
        try {
            return nationalityDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

package by.bsuir.nika.lombard.service.impl;

import by.bsuir.nika.lombard.dao.DaoProvider;
import by.bsuir.nika.lombard.dao.SexDao;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Sex;
import by.bsuir.nika.lombard.service.SexService;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class SexServiceImpl implements SexService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final SexServiceImpl instance = new SexServiceImpl();

    /*Экземпляр объекта SexDaoImpl, который предоставляет методы для
    взаимодействия с данными полов */
    private static final SexDao sexDao = DaoProvider.getInstance().getSexDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех полов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all sex types failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private SexServiceImpl() {
    }

    //Метод для получения всех полов
    @Override
    public List<Sex> getAll() throws ServiceException {
        try {
            return sexDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

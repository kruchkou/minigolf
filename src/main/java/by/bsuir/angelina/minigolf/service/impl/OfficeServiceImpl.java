package by.bsuir.angelina.minigolf.service.impl;

import by.bsuir.angelina.minigolf.dao.DaoProvider;
import by.bsuir.angelina.minigolf.dao.OfficeDao;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;
import by.bsuir.angelina.minigolf.service.OfficeService;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class OfficeServiceImpl implements OfficeService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final OfficeServiceImpl instance = new OfficeServiceImpl();

    /*Экземпляр объекта OfficeDaoImpl, который предоставляет методы для
    взаимодействия с данными офисов */
    private static final OfficeDao officeDao = DaoProvider.getInstance().getOfficeDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех офисов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all offices failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private OfficeServiceImpl() {
    }

    //Метод для получения всех офисов
    @Override
    public List<Office> getAll() throws ServiceException {
        try {
            return officeDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

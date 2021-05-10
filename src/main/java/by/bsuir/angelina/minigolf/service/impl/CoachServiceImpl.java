package by.bsuir.angelina.minigolf.service.impl;

import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.dao.CoachDao;
import by.bsuir.angelina.minigolf.dao.DaoProvider;
import by.bsuir.angelina.minigolf.dao.model.entity.Coach;
import by.bsuir.angelina.minigolf.service.CoachService;
import lombok.Getter;

import java.util.List;

public class CoachServiceImpl implements CoachService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CoachServiceImpl instance = new CoachServiceImpl();

    /*Экземпляр объекта CoachDaoImpl, который предоставляет методы для
    взаимодействия с данными инструкторов */
    private static final CoachDao coachDao = DaoProvider.getInstance().getCoachDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех инструкторов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all coaches failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CoachServiceImpl() {
    }

    //Метод для получения всех инструкторов
    @Override
    public List<Coach> getAll() throws ServiceException {
        try {
            return coachDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

package by.bsuir.angelina.minigolf.service;

import by.bsuir.angelina.minigolf.dao.model.entity.Coach;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;

import java.util.List;

public interface CoachService {

    //Метод для получения всех инструкторов
    List<Coach> getAll() throws ServiceException;

}

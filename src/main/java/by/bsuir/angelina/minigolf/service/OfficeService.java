package by.bsuir.angelina.minigolf.service;

import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;

import java.util.List;

public interface OfficeService {

    //Метод для получения всех офисов
    List<Office> getAll() throws ServiceException;

}

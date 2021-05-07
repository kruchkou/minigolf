package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.dao.model.entity.Sex;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import java.util.List;

public interface SexService {

    //Метод для получения всех полов
    List<Sex> getAll() throws ServiceException;

}

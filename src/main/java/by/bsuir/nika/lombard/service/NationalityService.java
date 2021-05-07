package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.dao.model.entity.Nationality;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import java.util.List;

public interface NationalityService {

    //Метод для получения всех национальностей
    List<Nationality> getAll() throws ServiceException;

}

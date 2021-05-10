package by.bsuir.angelina.minigolf.service;

import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;

import java.util.List;

public interface PaymentTypeService {

    //Метод для получения всех типов оплаты
    List<PaymentType> getAll() throws ServiceException;

}

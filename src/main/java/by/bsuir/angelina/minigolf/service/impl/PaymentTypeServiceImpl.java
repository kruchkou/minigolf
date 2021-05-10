package by.bsuir.angelina.minigolf.service.impl;

import by.bsuir.angelina.minigolf.dao.DaoProvider;
import by.bsuir.angelina.minigolf.dao.PaymentTypeDao;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;
import by.bsuir.angelina.minigolf.service.PaymentTypeService;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import lombok.Getter;

import java.util.List;

public class PaymentTypeServiceImpl implements PaymentTypeService {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final PaymentTypeServiceImpl instance = new PaymentTypeServiceImpl();

    /*Экземпляр объекта PaymentTypeDaoImpl, который предоставляет методы для
    взаимодействия с данными типов оплаты */
    private static final PaymentTypeDao paymentTypeDao = DaoProvider.getInstance().getPaymentTypeDao();

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов оплаты
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all payment types failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private PaymentTypeServiceImpl() {
    }

    //Метод для получения всех типов оплаты
    @Override
    public List<PaymentType> getAll() throws ServiceException {
        try {
            return paymentTypeDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(MESSAGE_GET_ALL_EXCEPTION, e);
        }
    }
}

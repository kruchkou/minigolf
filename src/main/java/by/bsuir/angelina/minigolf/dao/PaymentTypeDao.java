package by.bsuir.angelina.minigolf.dao;

import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;

import java.util.List;

public interface PaymentTypeDao {

    //Метод для получения всех типов оплаты
    List<PaymentType> getAll() throws DaoException;

}

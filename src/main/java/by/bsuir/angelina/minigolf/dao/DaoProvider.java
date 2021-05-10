package by.bsuir.angelina.minigolf.dao;

import by.bsuir.angelina.minigolf.dao.impl.CoachDaoImpl;
import by.bsuir.angelina.minigolf.dao.impl.OfficeDaoImpl;
import by.bsuir.angelina.minigolf.dao.impl.OrderDaoImpl;
import by.bsuir.angelina.minigolf.dao.impl.PaymentTypeDaoImpl;
import lombok.Getter;

public class DaoProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DaoProvider instance = new DaoProvider();

    /*Экземпляр объекта OrderDaoImpl, который предоставляет методы для
    взаимодействия с данными заказов */
    @Getter
    private final OrderDao orderDao = OrderDaoImpl.getInstance();

    /*Экземпляр объекта PaymentTypeDaoImpl, который предоставляет методы для
    взаимодействия с данными типов оплаты */
    @Getter
    private final PaymentTypeDao paymentTypeDao = PaymentTypeDaoImpl.getInstance();

    /*Экземпляр объекта OfficeDaoImpl, который предоставляет методы для
    взаимодействия с данными офисов */
    @Getter
    private final OfficeDao officeDao = OfficeDaoImpl.getInstance();

    /*Экземпляр объекта CoachDaoImpl, который предоставляет методы для
    взаимодействия с данными тренеров */
    @Getter
    private final CoachDao coachDao = CoachDaoImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DaoProvider() {
    }

}

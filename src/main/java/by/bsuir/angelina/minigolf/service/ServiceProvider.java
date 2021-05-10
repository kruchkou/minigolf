package by.bsuir.angelina.minigolf.service;

import by.bsuir.angelina.minigolf.service.impl.CoachServiceImpl;
import by.bsuir.angelina.minigolf.service.impl.OfficeServiceImpl;
import by.bsuir.angelina.minigolf.service.impl.OrderServiceImpl;
import by.bsuir.angelina.minigolf.service.impl.PaymentTypeServiceImpl;
import by.bsuir.nika.lombard.service.impl.*;
import lombok.Getter;

public class ServiceProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ServiceProvider instance = new ServiceProvider();

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    @Getter
    private final OrderService orderService = OrderServiceImpl.getInstance();

    /*Экземпляр объекта PaymentTypeServiceImpl, который предоставляет методы для
    взаимодействия с данными типов оплаты */
    @Getter
    private final PaymentTypeService paymentTypeService = PaymentTypeServiceImpl.getInstance();

    /*Экземпляр объекта OfficeServiceImpl, который предоставляет методы для
    взаимодействия с данными офисов */
    @Getter
    private final OfficeService officeService = OfficeServiceImpl.getInstance();

    /*Экземпляр объекта CoachServiceImpl, который предоставляет методы для
    взаимодействия с данными инструкторов */
    @Getter
    private final CoachService coachService = CoachServiceImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ServiceProvider() {
    }

}

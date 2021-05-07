package by.bsuir.nika.lombard.service;

import by.bsuir.nika.lombard.service.impl.*;
import lombok.Getter;

public class ServiceProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ServiceProvider instance = new ServiceProvider();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    @Getter
    private final ClientService clientService = ClientServiceImpl.getInstance();

    /*Экземпляр объекта NationalityServiceImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final NationalityService nationalityService = NationalityServiceImpl.getInstance();

    /*Экземпляр объекта SexServiceImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final SexService sexService = SexServiceImpl.getInstance();

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
     взаимодействия с данными предметов */
    @Getter
    private final ItemService itemService = ItemServiceImpl.getInstance();

    /*Экземпляр объекта CategoryServiceImpl, который предоставляет методы для
    взаимодействия с данными категорий предметов */
    @Getter
    private final CategoryService categoryService = CategoryServiceImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ServiceProvider() {
    }

}

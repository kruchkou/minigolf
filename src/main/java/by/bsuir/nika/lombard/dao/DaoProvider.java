package by.bsuir.nika.lombard.dao;

import by.bsuir.nika.lombard.dao.impl.*;
import lombok.Getter;

public class DaoProvider {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final DaoProvider instance = new DaoProvider();

    /*Экземпляр объекта ClientDaoImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    @Getter
    private final ClientDao clientDao = ClientDaoImpl.getInstance();

    /*Экземпляр объекта NationalityDaoImpl, который предоставляет методы для
    взаимодействия с данными национальностей */
    @Getter
    private final NationalityDao nationalityDao = NationalityDaoImpl.getInstance();

    /*Экземпляр объекта SexDaoImpl, который предоставляет методы для
    взаимодействия с данными полов */
    @Getter
    private final SexDao sexDao = SexDaoImpl.getInstance();

    /*Экземпляр объекта ItemDaoImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    @Getter
    private final ItemDao itemDao = ItemDaoImpl.getInstance();

    /*Экземпляр объекта CategoryDaoImpl, который предоставляет методы для
    взаимодействия с данными категорий предметов */
    @Getter
    private final CategoryDao categoryDao = CategoryDaoImpl.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DaoProvider() {
    }

}

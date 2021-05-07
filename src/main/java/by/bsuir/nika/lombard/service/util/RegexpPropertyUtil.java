package by.bsuir.nika.lombard.service.util;

import lombok.Getter;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegexpPropertyUtil {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final RegexpPropertyUtil instance = new RegexpPropertyUtil();

    //Экземпляр класса ResourceBundle, предоставляющий ключ\значение из файла regexp.properties
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("regexp", Locale.getDefault());

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private RegexpPropertyUtil() {
    }

    //Метод, предоставляющий значение по ключу из файла regexp.properties
    public String getProperty(String propertyName) {
        return resourceBundle.getString(propertyName);
    }

}


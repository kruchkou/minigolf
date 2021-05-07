package by.bsuir.nika.lombard.service.util.validator;

import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.util.RegexpPropertyUtil;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class ItemValidator {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ItemValidator() {
    }

    //Публичный метод для валидации эклемпляра предмета
    public static boolean validate(Item item) {
        Integer ownerId = item.getOwner().getId();
        Integer categoryId = item.getCategory().getId();
        String name = item.getName();
        String desc = item.getDesc();
        String condition = item.getCondition();
        Integer price = item.getPrice();
        LocalDate acceptDate = item.getAcceptDate();
        Integer keepingDays = item.getKeepingDays();

        if (ownerId == null || !validateInteger(ownerId)) {
            return false;
        }
        if (categoryId == null || !validateInteger(categoryId)) {
            return false;
        }
        if (name == null || !validateName(name)) {
            return false;
        }
        if (desc == null || !validateDesc(desc)) {
            return false;
        }
        if (condition == null || !validateDesc(condition)) {
            return false;
        }
        if (price == null || !validateInteger(price)) {
            return false;
        }
        if (acceptDate == null || !validateAcceptDate(acceptDate)) {
            return false;
        }
        return keepingDays != null && validateInteger(keepingDays);
    }

    //Приватный метод для валидации названия
    private static boolean validateName(String name) {
        return validateString(name, regexpPropertyUtil.getProperty("regexp.item.name"));
    }

    //Приватный метод для валидации описания
    private static boolean validateDesc(String desc) {
        return validateString(desc, regexpPropertyUtil.getProperty("regexp.item.desc"));
    }

    //Приватный метод для валидации даты принятия товара
    private static boolean validateAcceptDate(LocalDate passportIssuedDate) {
        return passportIssuedDate.isBefore(LocalDate.now().plusDays(1));
    }

    //Приватный метод для валидации целых чисел
    private static boolean validateInteger(Integer integer) {
        return integer > 0;
    }

    //Приватный метод для валидации String с помощью regexp-выражения
    private static boolean validateString(String string, String regexp) {
        Pattern pattern = Pattern.compile(regexp);

        return pattern.matcher(string).find();
    }

}

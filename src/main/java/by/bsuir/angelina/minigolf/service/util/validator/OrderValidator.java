package by.bsuir.angelina.minigolf.service.util.validator;

import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.service.util.RegexpPropertyUtil;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class OrderValidator {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private OrderValidator() {
    }

    //Публичный метод для валидации эклемпляра клиента
    public static boolean validate(Order order) {
        String surname = order.getSurname();
        String name = order.getName();
        LocalDate birthdate = order.getBirthdate();
        String phoneCell = order.getPhoneCell();
        String email = order.getEmail();
        Integer officeId = order.getOffice().getId();
        Integer paymentTypeId = order.getPaymentType().getId();
        Integer coachId = order.getCoach().getId();
        Integer personQuantity = order.getPersonQuantity();
        Integer hours = order.getHours();

        if (surname == null || !validateFio(surname)) {
            return false;
        }
        if (name == null || !validateFio(name)) {
            return false;
        }
        if (birthdate == null || !validateBirthDate(birthdate)) {
            return false;
        }
        if (phoneCell == null || !validatePhoneNumber(phoneCell)) {
            return false;
        }
        if (email != null && !validateEmail(email)) {
            return false;
        }
        if (officeId == null || !validateId(officeId)) {
            return false;
        }
        if (paymentTypeId == null || !validateId(paymentTypeId)) {
            return false;
        }
        if (personQuantity == null || !validateId(personQuantity)) {
            return false;
        }
        if (hours == null || !validateId(hours)) {
            return false;
        }
        return coachId != null && validateId(coachId);
    }

    //Приватный метод для валидации телефона
    private static boolean validatePhoneNumber(String phoneHome) {
        return validateString(phoneHome, regexpPropertyUtil.getProperty("regexp.phone_number"));
    }

    //Приватный метод для валидации email
    private static boolean validateEmail(String email) {
        return validateString(email, regexpPropertyUtil.getProperty("regexp.email"));
    }

    //Приватный метод для валидации фамилии, имени и отчества
    private static boolean validateFio(String fio) {
        return validateString(fio, regexpPropertyUtil.getProperty("regexp.user_fio"));
    }

    //Приватный метод для валидации даты рождения
    private static boolean validateBirthDate(LocalDate birthDate) {
        return birthDate.isBefore(DateValidatorValueProvider.getMinBirthdate());
    }

    //Приватный метод для валидации ID
    private static boolean validateId(Integer integer) {
        return integer > 0;
    }

    //Приватный метод для валидации String с помощью regexp-выражения
    private static boolean validateString(String string, String regexp) {
        Pattern pattern = Pattern.compile(regexp);

        return pattern.matcher(string).find();
    }

}

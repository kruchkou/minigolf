package by.bsuir.angelina.minigolf.service.util.validator;

import java.time.LocalDate;

public final class DateValidatorValueProvider {

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private DateValidatorValueProvider() {
    }

    public static LocalDate getMinBirthdate() {
        return LocalDate.now().minusYears(14);
    }

}

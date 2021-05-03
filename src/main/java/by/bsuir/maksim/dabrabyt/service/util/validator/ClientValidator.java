package by.bsuir.maksim.dabrabyt.service.util.validator;

import by.bsuir.maksim.dabrabyt.dao.model.entity.Client;
import by.bsuir.maksim.dabrabyt.service.util.RegexpPropertyUtil;

import java.time.LocalDate;
import java.util.regex.Pattern;

public final class ClientValidator {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ClientValidator() {
    }

    //Публичный метод для валидации эклемпляра клиента
    public static boolean validate(Client client) {
        String surname = client.getSurname();
        String name = client.getName();
        String patronymic = client.getPatronymic();
        LocalDate birthdate = client.getBirthdate();
        Integer sexId = client.getSex().getId();
        String phoneHome = client.getPhoneHome();
        String phoneCell = client.getPhoneCell();
        String email = client.getEmail();
        Integer actualCityId = client.getActCity().getId();
        String actAddress = client.getActAddress();
        Integer salary = client.getSalary();
        String passportSeries = client.getPassportSeries();
        Integer passportNumber = client.getPassportNumber();
        String passportIssuedBy = client.getPassportIssuedBy();
        LocalDate passportIssuedDate = client.getPassportIssuedDate();
        String passportIdentityNumber = client.getPassportIdentityNumber();
        Integer passportCityId = client.getPassportCity().getId();
        Integer nationalityId = client.getNationality().getId();
        String passportBirthplace = client.getPassportBirthplace();
        Integer familyStatusId = client.getFamilyStatus().getId();
        Integer disablityId = client.getDisability().getId();
        Boolean retiree = client.getRetiree();

        if (surname == null || !validateFio(surname)) {
            return false;
        }
        if (name == null || !validateFio(name)) {
            return false;
        }
        if (patronymic == null || !validateFio(patronymic)) {
            return false;
        }
        if (birthdate == null || !validatebirtDate(birthdate)) {
            return false;
        }
        if (sexId == null || !validateId(sexId)) {
            return false;
        }
        if (phoneHome != null && !validatePhoneNumber(phoneHome)) {
            //return false;
        }
        if (phoneCell != null && !validatePhoneNumber(phoneCell)) {
            //return false;
        }
        if (email != null && !validateEmail(email)) {
            return false;
        }
        if (actualCityId == null || !validateId(actualCityId)) {
            return false;
        }
        if (salary != null && !validateSalary(salary)) {
            //return false;
        }
        if (actAddress == null || !validateAddress(actAddress)) {
            return false;
        }
        if (passportSeries == null || !validatePassportSeries(passportSeries)) {
            return false;
        }
        if (passportNumber == null || !validatePassportNumber(passportNumber)) {
            return false;
        }
        if (passportIssuedBy == null || !validatePassportIssuedBy(passportIssuedBy)) {
            return false;
        }
        if (passportIssuedDate == null || !validatePassportIssuedDate(passportIssuedDate)) {
            return false;
        }
        if (passportIdentityNumber == null || !validatePassportIdentityNumber(passportIdentityNumber)) {
            return false;
        }
        if (passportCityId == null || !validateId(passportCityId)) {
            return false;
        }
        if (nationalityId == null || !validateId(nationalityId)) {
            return false;
        }
        if (passportBirthplace == null || !validatePassportBirthplace(passportBirthplace)) {
            return false;
        }
        if (familyStatusId == null || !validateId(familyStatusId)) {
            return false;
        }
        if (disablityId == null || !validateId(disablityId)) {
            return false;
        }
        return retiree != null;
    }

    //Приватный метод для валидации телефона
    private static boolean validatePhoneNumber(String phoneHome) {
        return validateString(phoneHome, regexpPropertyUtil.getProperty("regexp.phone_number"));
    }

    //Приватный метод для валидации email
    private static boolean validateEmail(String email) {
        return validateString(email, regexpPropertyUtil.getProperty("regexp.email"));
    }

    //Приватный метод для валидации зарплаты
    private static boolean validateSalary(Integer salary) {
        return salary > 0;
    }

    //Приватный метод для валидации фамилии, имени и отчества
    private static boolean validateFio(String fio) {
        return validateString(fio, regexpPropertyUtil.getProperty("regexp.user_fio"));
    }

    //Приватный метод для валидации адреса
    private static boolean validateAddress(String address) {
        return validateString(address, regexpPropertyUtil.getProperty("regexp.address"));
    }

    //Приватный метод для валидации серии паспорта
    private static boolean validatePassportSeries(String passportSeries) {
        return validateString(passportSeries, regexpPropertyUtil.getProperty("regexp.passport.series"));
    }

    //Приватный метод для валидации номера паспорта
    private static boolean validatePassportNumber(Integer passportNumber) {
        return passportNumber > 0;
    }

    //Приватный метод для валидации поля кем выдан паспорт
    private static boolean validatePassportIssuedBy(String passportIssuedBy) {
        return validateString(passportIssuedBy, regexpPropertyUtil.getProperty("regexp.passport.issued_by"));
    }

    //Приватный метод для валидации даты выдачи паспорта
    private static boolean validatePassportIssuedDate(LocalDate passportIssuedDate) {
        return LocalDate.EPOCH.isBefore(LocalDate.now().plusDays(1));
    }

    //Приватный метод для валидации индентификационного номера
    private static boolean validatePassportIdentityNumber(String passportIdentityNumber) {
        return validateString(passportIdentityNumber, regexpPropertyUtil.getProperty("regexp.passport.identity_number"));
    }

    //Приватный метод для валидации места рождения
    private static boolean validatePassportBirthplace(String passportBirthplace) {
        return validateString(passportBirthplace, regexpPropertyUtil.getProperty("regexp.passport.birthplace"));
    }

    //Приватный метод для валидации даты рождения
    private static boolean validatebirtDate(LocalDate birthDate) {
        return LocalDate.EPOCH.isBefore(DateValidatorValueProvider.getMinBirthdate());
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

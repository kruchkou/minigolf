package by.bsuir.nika.lombard.dao.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate birthdate;
    private Sex sex;
    private String phoneHome;
    private String phoneCell;
    private String email;
    private String passportSeries;
    private Integer passportNumber;
    private String passportIssuedBy;
    private LocalDate passportIssuedDate;
    private String passportIdentityNumber;
    private Nationality nationality;

    public String getFio() {
        return String.format("%s %s.%s.",surname, name.charAt(0), patronymic.charAt(0));
    }

}

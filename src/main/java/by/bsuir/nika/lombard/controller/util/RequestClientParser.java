package by.bsuir.nika.lombard.controller.util;

import by.bsuir.nika.lombard.dao.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class RequestClientParser {

    //Метод для извлечение объекта клиента из данных, находящихся в объекте HttpServletRequest
    public static Client parse(HttpServletRequest req) {
        String phoneHomeParameter = req.getParameter("phone_home");
        String phoneCellParameter = req.getParameter("phone_cell");
        String emailParameter = req.getParameter("email");

        String phoneHome = phoneHomeParameter.isEmpty() ? null : phoneHomeParameter;
        String phoneCell = phoneCellParameter.isEmpty() ? null : phoneCellParameter;
        String email = emailParameter.isEmpty() ? null : emailParameter;

        LocalDate birthDate = LocalDate.parse(req.getParameter("birthdate"));
        Sex sex = Sex.builder().id(Integer.parseInt(req.getParameter("sex"))).build();
        LocalDate passportIssuedDate = LocalDate.parse(req.getParameter("passport_issued_date"));
        Nationality nationality = Nationality.builder().id(Integer.parseInt(req.getParameter("nationality"))).build();

        return Client.builder()
                .surname(req.getParameter("surname"))
                .name(req.getParameter("name"))
                .patronymic(req.getParameter("patronymic"))
                .birthdate(birthDate)
                .sex(sex)
                .phoneHome(phoneHome)
                .phoneCell(phoneCell)
                .email(email)
                .passportSeries(req.getParameter("passport_series"))
                .passportNumber(Integer.parseInt(req.getParameter("passport_number")))
                .passportIssuedBy(req.getParameter("passport_issued_by"))
                .passportIssuedDate(passportIssuedDate)
                .passportIdentityNumber(req.getParameter("passport_identity_number"))
                .nationality(nationality)
                .build();
    }

}

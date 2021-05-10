package by.bsuir.angelina.minigolf.controller.util;

import by.bsuir.angelina.minigolf.dao.model.entity.Coach;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;
import by.bsuir.nika.lombard.dao.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class RequestClientParser {

    //Метод для извлечение объекта клиента из данных, находящихся в объекте HttpServletRequest
    public static Order parse(HttpServletRequest req) {
        String emailParameter = req.getParameter("email");

        String email = emailParameter.isEmpty() ? null : emailParameter;

        LocalDate birthDate = LocalDate.parse(req.getParameter("birthdate"));
        Office office = Office.builder().id(Integer.parseInt(req.getParameter("office"))).build();
        PaymentType paymentType = PaymentType.builder().id(Integer.parseInt(req.getParameter("payment_type"))).build();
        Coach coach = Coach.builder().id(Integer.parseInt(req.getParameter("coach"))).build();

        return Order.builder()
                .surname(req.getParameter("surname"))
                .name(req.getParameter("name"))
                .birthdate(birthDate)
                .phoneCell(req.getParameter("phone_cell"))
                .email(email)
                .office(office)
                .paymentType(paymentType)
                .coach(coach)
                .personQuantity(Integer.parseInt(req.getParameter("person_quantity")))
                .hours(Integer.parseInt(req.getParameter("hours")))
                .build();
    }

}

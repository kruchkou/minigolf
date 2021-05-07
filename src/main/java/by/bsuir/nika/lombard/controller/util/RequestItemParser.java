package by.bsuir.nika.lombard.controller.util;

import by.bsuir.nika.lombard.dao.model.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public final class RequestItemParser {

    //Метод для извлечение объекта клиента из данных, находящихся в объекте HttpServletRequest
    public static Item parse(HttpServletRequest req) {
        LocalDate acceptDate = LocalDate.parse(req.getParameter("acceptance-date"));

        Client client = Client.builder().id(Integer.parseInt(req.getParameter("owner"))).build();
        Category category = Category.builder().id(Integer.parseInt(req.getParameter("category"))).build();

        return Item.builder()
                .owner(client)
                .category(category)
                .name(req.getParameter("name"))
                .desc(req.getParameter("description"))
                .condition(req.getParameter("condition"))
                .price(Integer.parseInt(req.getParameter("price")))
                .acceptDate(acceptDate)
                .keepingDays(Integer.parseInt(req.getParameter("keeping-days")))
                .build();
    }

}

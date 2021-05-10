package by.bsuir.angelina.minigolf.controller.command.impl;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.controller.util.RequestClientParser;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.service.OrderService;
import by.bsuir.angelina.minigolf.service.ServiceProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class UpdateOrderCommand implements Command {

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    //Метод, обрабатывающий запрос на обновление брони
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        Order order = RequestClientParser.parse(req);
        order.setId(Integer.valueOf(req.getParameter("id")));

        orderService.update(order);

        resp.sendRedirect(String.format("Controller?command=go_to_order_command&id=%d", order.getId()));
    }
}

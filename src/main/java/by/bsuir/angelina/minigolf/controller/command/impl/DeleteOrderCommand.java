package by.bsuir.angelina.minigolf.controller.command.impl;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.service.OrderService;
import by.bsuir.angelina.minigolf.service.ServiceProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class DeleteOrderCommand implements Command {

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    //Метод, обрабатывающий запрос на обновление брони
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        int id = Integer.parseInt(req.getParameter("id"));
        orderService.delete(id);

        resp.sendRedirect("Controller?command=go_to_all_orders_command");
    }
}

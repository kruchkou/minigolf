package by.bsuir.angelina.minigolf.controller.command.impl.go;

import by.bsuir.angelina.minigolf.service.OrderService;
import by.bsuir.angelina.minigolf.service.ServiceProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public final class GoToAllOrdersCommand implements Command {

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    //Метод, реализующий команду перехода на страницу броней
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, ServletException, IOException {

        List<Order> orderList = orderService.getAll();

        req.setAttribute("orderList", orderList);
        req.getRequestDispatcher("WEB-INF/orders.jsp").forward(req, resp);
    }
}

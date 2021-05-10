package by.bsuir.angelina.minigolf.controller.command.impl.go;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.service.OrderService;
import by.bsuir.angelina.minigolf.service.ServiceProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToOrderCommand implements Command {

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    //Метод, реализующий команду перехода на страницу клиента по его ID
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = orderService.get(id);

        req.setAttribute("order", order);
        req.getRequestDispatcher("WEB-INF/order-info.jsp").forward(req, resp);
    }
}

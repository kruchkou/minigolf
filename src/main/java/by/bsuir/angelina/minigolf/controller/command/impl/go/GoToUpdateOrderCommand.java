package by.bsuir.angelina.minigolf.controller.command.impl.go;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.service.*;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.service.util.RegexpPropertyUtil;
import by.bsuir.angelina.minigolf.service.util.validator.DateValidatorValueProvider;
import by.bsuir.nika.lombard.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToUpdateOrderCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта OrderServiceImpl, который предоставляет методы для
    взаимодействия с данными броней */
    private static final OrderService orderService = ServiceProvider.getInstance().getOrderService();

    /*Экземпляр объекта PaymentTypeServiceImpl, который предоставляет методы для
    взаимодействия с данными типов оплаты */
    private static final PaymentTypeService paymentTypeService = ServiceProvider.getInstance().getPaymentTypeService();

    /*Экземпляр объекта OfficeServiceImpl, который предоставляет методы для
    взаимодействия с данными офисов */
    private static final OfficeService officeService = ServiceProvider.getInstance().getOfficeService();

    /*Экземпляр объекта CoachServiceImpl, который предоставляет методы для
    взаимодействия с данными инструкторов */
    private static final CoachService coachService = ServiceProvider.getInstance().getCoachService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(req.getParameter("id"));
        Order order = orderService.get(id);

        req.setAttribute("id", id);
        req.setAttribute("order", order);
        req.setAttribute("paymentTypeList", paymentTypeService.getAll());
        req.setAttribute("officeList", officeService.getAll());
        req.setAttribute("coachList", officeService.getAll());

        req.setAttribute("regexp_user_fio", regexpPropertyUtil.getProperty("regexp.user_fio"));
        req.setAttribute("regexp_phone_number", regexpPropertyUtil.getProperty("regexp.phone_number"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));

        req.setAttribute("min_birthdate", DateValidatorValueProvider.getMinBirthdate());

        req.getRequestDispatcher("WEB-INF/order-update.jsp").forward(req, resp);
    }
}

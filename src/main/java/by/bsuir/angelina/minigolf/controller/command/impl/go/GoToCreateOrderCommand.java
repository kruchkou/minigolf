package by.bsuir.angelina.minigolf.controller.command.impl.go;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.service.CoachService;
import by.bsuir.angelina.minigolf.service.OfficeService;
import by.bsuir.angelina.minigolf.service.PaymentTypeService;
import by.bsuir.angelina.minigolf.service.ServiceProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;
import by.bsuir.angelina.minigolf.service.util.RegexpPropertyUtil;
import by.bsuir.angelina.minigolf.service.util.validator.DateValidatorValueProvider;
import by.bsuir.nika.lombard.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToCreateOrderCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными типов оплаты */
    private static final PaymentTypeService paymentTypeService = ServiceProvider.getInstance().getPaymentTypeService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными офисов */
    private static final OfficeService officeService = ServiceProvider.getInstance().getOfficeService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными инструкторов */
    private static final CoachService coachService = ServiceProvider.getInstance().getCoachService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        req.setAttribute("paymentTypeList", paymentTypeService.getAll());
        req.setAttribute("coachList", coachService.getAll());
        req.setAttribute("officeList", officeService.getAll());

        req.setAttribute("regexp_user_fio", regexpPropertyUtil.getProperty("regexp.user_fio"));
        req.setAttribute("regexp_phone_number", regexpPropertyUtil.getProperty("regexp.phone_number"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));

        req.setAttribute("min_birthdate", DateValidatorValueProvider.getMinBirthdate());

        req.getRequestDispatcher("WEB-INF/order-create.jsp").forward(req, resp);
    }
}

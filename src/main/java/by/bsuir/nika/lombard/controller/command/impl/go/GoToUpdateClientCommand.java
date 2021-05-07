package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.service.*;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.util.RegexpPropertyUtil;
import by.bsuir.nika.lombard.service.util.validator.DateValidatorValueProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public final class GoToUpdateClientCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными национальностей */
    private static final NationalityService nationalityService = ServiceProvider.getInstance().getNationalityService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными полов */
    private static final SexService sexService = ServiceProvider.getInstance().getSexService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = clientService.get(id);

        req.setAttribute("id", id);
        req.setAttribute("client", client);
        req.setAttribute("nationalityList", nationalityService.getAll());
        req.setAttribute("sexList", sexService.getAll());

        req.setAttribute("regexp_user_fio", regexpPropertyUtil.getProperty("regexp.user_fio"));
        req.setAttribute("regexp_phone_number", regexpPropertyUtil.getProperty("regexp.phone_number"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));
        req.setAttribute("regexp_passport_series", regexpPropertyUtil.getProperty("regexp.passport.series"));
        req.setAttribute("regexp_passport_issued_by", regexpPropertyUtil.getProperty("regexp.passport.issued_by"));
        req.setAttribute("regexp_passport_identity_number", regexpPropertyUtil.getProperty("regexp.passport.identity_number"));
        req.setAttribute("min_birthdate", DateValidatorValueProvider.getMinBirthdate());
        req.setAttribute("date_today", LocalDate.now());

        req.getRequestDispatcher("WEB-INF/client-update.jsp").forward(req, resp);
    }
}

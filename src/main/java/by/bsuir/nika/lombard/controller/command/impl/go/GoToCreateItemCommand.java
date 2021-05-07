package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.service.*;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.util.RegexpPropertyUtil;
import by.bsuir.nika.lombard.service.util.validator.DateValidatorValueProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public final class GoToCreateItemCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными категорий предметов */
    private static final CategoryService categoryService = ServiceProvider.getInstance().getCategoryService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        req.setAttribute("clientList", clientService.getAll());
        req.setAttribute("categoryList", categoryService.getAll());

        req.setAttribute("regexp_item_name", regexpPropertyUtil.getProperty("regexp.item.name"));
        req.setAttribute("regexp_item_desc", regexpPropertyUtil.getProperty("regexp.item.desc"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));

        req.setAttribute("date_today", LocalDate.now());

        req.getRequestDispatcher("WEB-INF/item-create.jsp").forward(req, resp);
    }
}

package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.CategoryService;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.util.RegexpPropertyUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public final class GoToUpdateItemCommand implements Command {

    //Экземпляр класса RegexpPropertyUtil, предоставляющий значение по ключу из файла regexp.properties
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemService itemService = ServiceProvider.getInstance().getItemService();

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    /*Экземпляр объекта , который предоставляет методы для
    взаимодействия с данными категорий предметов */
    private static final CategoryService categoryService = ServiceProvider.getInstance().getCategoryService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException {
        int id = Integer.valueOf(req.getParameter("id"));

        req.setAttribute("item",itemService.get(id));

        req.setAttribute("clientList", clientService.getAll());
        req.setAttribute("categoryList", categoryService.getAll());

        req.setAttribute("regexp_item_name", regexpPropertyUtil.getProperty("regexp.item.name"));
        req.setAttribute("regexp_item_desc", regexpPropertyUtil.getProperty("regexp.item.desc"));
        req.setAttribute("regexp_email", regexpPropertyUtil.getProperty("regexp.email"));

        req.setAttribute("date_today", LocalDate.now());

        req.getRequestDispatcher("WEB-INF/item-update.jsp").forward(req, resp);
    }
}

package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToItemCommand implements Command {

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemService itemService = ServiceProvider.getInstance().getItemService();

    //Метод, реализующий команду перехода на страницу клиента по его ID
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item item = itemService.get(id);

        req.setAttribute("item", item);
        req.getRequestDispatcher("WEB-INF/item-info.jsp").forward(req, resp);
    }
}

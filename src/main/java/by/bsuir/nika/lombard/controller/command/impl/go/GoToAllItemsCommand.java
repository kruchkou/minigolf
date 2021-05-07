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
import java.util.List;

public final class GoToAllItemsCommand implements Command {

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemService itemService = ServiceProvider.getInstance().getItemService();

    //Метод, реализующий команду перехода на страницу всех предметов
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServiceException, ServletException, IOException {

        List<Item> itemList = itemService.getAll();

        req.setAttribute("itemList", itemList);
        req.getRequestDispatcher("WEB-INF/items.jsp").forward(req, resp);
    }
}

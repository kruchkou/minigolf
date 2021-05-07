package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToClientCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private static final ClientService clientService = ServiceProvider.getInstance().getClientService();

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemService itemService = ServiceProvider.getInstance().getItemService();

    //Метод, реализующий команду перехода на страницу клиента по его ID
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = clientService.get(id);

        req.setAttribute("client", client);
        req.setAttribute("itemList", itemService.getByClientId(id));
        req.getRequestDispatcher("WEB-INF/client-info.jsp").forward(req, resp);
    }
}

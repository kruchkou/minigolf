package by.bsuir.nika.lombard.controller.command.impl.go;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;
import by.bsuir.nika.lombard.service.model.util.Report;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class GoToReportCommand implements Command {

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private static final ItemService itemService = ServiceProvider.getInstance().getItemService();

    //Метод, реализующий команду перехода на страницу клиента по его ID
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        Report report = itemService.getReport();

        req.setAttribute("report", report);
        req.getRequestDispatcher("WEB-INF/report.jsp").forward(req, resp);
    }
}

package by.bsuir.nika.lombard.controller.command.impl;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.controller.util.RequestClientParser;
import by.bsuir.nika.lombard.controller.util.RequestItemParser;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.ItemService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class CreateItemCommand implements Command {

    /*Экземпляр объекта ItemServiceImpl, который предоставляет методы для
    взаимодействия с данными предметов */
    private final ItemService itemService = ServiceProvider.getInstance().getItemService();

    //Метод, обрабатывающий запрос на создание предмета
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        Item item = RequestItemParser.parse(req);
        itemService.create(item);

        resp.sendRedirect("Controller?command=go_to_all_items_command");
    }
}

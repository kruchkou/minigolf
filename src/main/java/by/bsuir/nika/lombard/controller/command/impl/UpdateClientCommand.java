package by.bsuir.nika.lombard.controller.command.impl;

import by.bsuir.nika.lombard.controller.command.Command;
import by.bsuir.nika.lombard.controller.util.RequestClientParser;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.service.ClientService;
import by.bsuir.nika.lombard.service.ServiceProvider;
import by.bsuir.nika.lombard.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class UpdateClientCommand implements Command {

    /*Экземпляр объекта ClientServiceImpl, который предоставляет методы для
    взаимодействия с данными клиентов */
    private final ClientService clientService = ServiceProvider.getInstance().getClientService();

    //Метод, обрабатывающий запрос на обновление пользователя
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServiceException {
        Client client = RequestClientParser.parse(req);
        client.setId(Integer.valueOf(req.getParameter("id")));

        clientService.update(client);

        resp.sendRedirect(String.format("Controller?command=go_to_client_command&id=%d", client.getId()));
    }
}

package by.bsuir.angelina.minigolf.controller;

import by.bsuir.angelina.minigolf.controller.command.Command;
import by.bsuir.angelina.minigolf.controller.command.util.CommandProvider;
import by.bsuir.angelina.minigolf.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {

    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String COMMAND_NAME = req.getParameter("command");
        final Command COMMAND = COMMAND_PROVIDER.getCommand(COMMAND_NAME);

        if (COMMAND == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            try {
                COMMAND.execute(req, resp);
            } catch (ServiceException e) {
                System.out.printf(e.getMessage());
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}

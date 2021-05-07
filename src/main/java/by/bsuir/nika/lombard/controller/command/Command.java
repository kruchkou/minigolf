package by.bsuir.nika.lombard.controller.command;

import by.bsuir.nika.lombard.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ServiceException;

}

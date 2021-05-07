package by.bsuir.nika.lombard.dao.connection;

import by.bsuir.nika.lombard.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ConnectionPool {

    public Connection getConnection() throws DaoException;

    public void closeConnection(Connection connection, PreparedStatement ps) throws DaoException;
}

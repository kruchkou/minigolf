package by.bsuir.angelina.minigolf.dao.impl;

import by.bsuir.angelina.minigolf.dao.connection.ConnectionPool;
import by.bsuir.angelina.minigolf.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.OfficeDao;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfficeDaoImpl implements OfficeDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final OfficeDaoImpl instance = new OfficeDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех офисов
    private static final String GET_ALL_SQL = "SELECT * FROM Offices";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех офисов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private OfficeDaoImpl() {
    }

    //Метод для получения всех офисов
    @Override
    public List<Office> getAll() throws DaoException {
        List<Office> officeList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                officeList.add(buildOfficeFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return officeList;
    }

    /* Приватный метод для создания экземпляра пола из объекта ResultSet,
    содержащий данные из базы данных */
    private static Office buildOfficeFromResultSet(ResultSet rs) throws SQLException {
        return Office.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

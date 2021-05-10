package by.bsuir.angelina.minigolf.dao.impl;

import by.bsuir.angelina.minigolf.dao.CoachDao;
import by.bsuir.angelina.minigolf.dao.connection.ConnectionPool;
import by.bsuir.angelina.minigolf.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.Coach;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDaoImpl implements CoachDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CoachDaoImpl instance = new CoachDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех тренеров
    private static final String GET_ALL_SQL = "SELECT * FROM Coaches";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех тренеров
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all coaches failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CoachDaoImpl() {
    }

    //Метод для получения всех тренеров
    @Override
    public List<Coach> getAll() throws DaoException {
        List<Coach> familyStatusList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                familyStatusList.add(buildCoachFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return familyStatusList;
    }

    /* Приватный метод для создания экземпляра категории из объекта ResultSet,
    содержащий данные из базы данных */
    private static Coach buildCoachFromResultSet(ResultSet rs) throws SQLException {
        return Coach.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

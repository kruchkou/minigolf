package by.bsuir.nika.lombard.dao.impl;

import by.bsuir.nika.lombard.dao.NationalityDao;
import by.bsuir.nika.lombard.dao.connection.ConnectionPool;
import by.bsuir.nika.lombard.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Nationality;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NationalityDaoImpl implements NationalityDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final NationalityDaoImpl instance = new NationalityDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех национальностей
    private static final String GET_ALL_SQL = "SELECT * FROM ClientNationalities";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех национальностей
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private NationalityDaoImpl() {
    }

    //Метод для получения всех национальностей
    @Override
    public List<Nationality> getAll() throws DaoException {
        List<Nationality> nationalityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                nationalityList.add(buildNationalityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return nationalityList;
    }

    /* Приватный метод для создания экземпляра национальности из объекта ResultSet,
    содержащий данные из базы данных */
    private static Nationality buildNationalityFromResultSet(ResultSet rs) throws SQLException {
        return Nationality.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

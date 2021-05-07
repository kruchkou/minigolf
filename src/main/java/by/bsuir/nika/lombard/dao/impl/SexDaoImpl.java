package by.bsuir.nika.lombard.dao.impl;

import by.bsuir.nika.lombard.dao.SexDao;
import by.bsuir.nika.lombard.dao.connection.ConnectionPool;
import by.bsuir.nika.lombard.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Sex;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SexDaoImpl implements SexDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final SexDaoImpl instance = new SexDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех полов
    private static final String GET_ALL_SQL = "SELECT * FROM ClientSex";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех полов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all cities failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private SexDaoImpl() {
    }

    //Метод для получения всех полов
    @Override
    public List<Sex> getAll() throws DaoException {
        List<Sex> sexList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sexList.add(buildSexFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return sexList;
    }

    /* Приватный метод для создания экземпляра пола из объекта ResultSet,
    содержащий данные из базы данных */
    private static Sex buildSexFromResultSet(ResultSet rs) throws SQLException {
        return Sex.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

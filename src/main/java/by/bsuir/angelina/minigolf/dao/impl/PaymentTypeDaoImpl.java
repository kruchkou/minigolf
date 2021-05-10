package by.bsuir.angelina.minigolf.dao.impl;

import by.bsuir.angelina.minigolf.dao.PaymentTypeDao;
import by.bsuir.angelina.minigolf.dao.connection.ConnectionPool;
import by.bsuir.angelina.minigolf.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentTypeDaoImpl implements PaymentTypeDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final PaymentTypeDaoImpl instance = new PaymentTypeDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех типов оплаты
    private static final String GET_ALL_SQL = "SELECT * FROM PaymentTypes";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех типов оплаты
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all payment types failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private PaymentTypeDaoImpl() {
    }

    //Метод для получения всех типов оплаты
    @Override
    public List<PaymentType> getAll() throws DaoException {
        List<PaymentType> nationalityList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                nationalityList.add(buildPaymentTypeFromResultSet(rs));
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
    private static PaymentType buildPaymentTypeFromResultSet(ResultSet rs) throws SQLException {
        return PaymentType.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

package by.bsuir.angelina.minigolf.dao.impl;

import by.bsuir.angelina.minigolf.dao.connection.ConnectionPool;
import by.bsuir.angelina.minigolf.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.angelina.minigolf.dao.exception.DaoException;
import by.bsuir.angelina.minigolf.dao.OrderDao;
import by.bsuir.angelina.minigolf.dao.model.entity.Coach;
import by.bsuir.angelina.minigolf.dao.model.entity.Office;
import by.bsuir.angelina.minigolf.dao.model.entity.Order;
import by.bsuir.angelina.minigolf.dao.model.entity.PaymentType;
import by.bsuir.nika.lombard.dao.model.entity.*;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на создание брони
    private static final String CREATE_SQL = "INSERT INTO Orders(" +
            "surname, name, birthdate, phone_cell, email, " +
            "office, payment_type, coach, person_quantity, hours) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?)";

    //SQL Запрос на обновление брони
    private static final String UPDATE_SQL = "UPDATE Orders SET surname = ?, name = ?, birthdate = ?, phone_cell = ?, " +
            "email = ?, office = ?, payment_type = ?, coach = ?, person_quantity = ?, hours = ? "+
            "WHERE id = ?";

    //SQL Запрос на удаление брони по его ID
    private static final String DELETE_SQL = "DELETE FROM Orders WHERE id = ?";

    //SQL Запрос на получение брони по его ID
    private static final String GET_SQL = "SELECT Orders.id, surname, Orders.name, birthdate, phone_cell, email, office.id, office.name, " +
            "payment_type.id, payment_type.name, coach.id , coach.name, person_quantity, hours FROM Orders " +
            "JOIN Offices office ON Orders.office = office.id " +
            "JOIN PaymentTypes payment_type ON Orders.payment_type = payment_type.id " +
            "JOIN Coaches coach ON Orders.coach = coach.id " +
            "WHERE Orders.id = ?";

    //SQL Запрос на всех броней
    private static final String GET_ALL_SQL = "SELECT Orders.id, surname, Orders.name, birthdate, phone_cell, email, office.id, office.name, " +
            "payment_type.id, payment_type.name, coach.id , coach.name, person_quantity, hours FROM Orders " +
            "JOIN Offices office ON Orders.office = office.id " +
            "JOIN PaymentTypes payment_type ON Orders.payment_type = payment_type.id " +
            "JOIN Coaches coach ON Orders.coach = coach.id";

    //Сообщение, которое помещается в Exception в случае ошибки создания брони
    private static final String MESSAGE_CREATE_EXCEPTION = "Create order failed";

    //Сообщение, которое помещается в Exception в случае ошибки удаления брони
    private static final String MESSAGE_DELETE_EXCEPTION = "Create order failed";

    //Сообщение, которое помещается в Exception в случае ошибки обновления брони
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update order failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения брони
    private static final String MESSAGE_GET_EXCEPTION = "Get order failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех броней
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all order failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private OrderDaoImpl() {
    }

    //Метод для создания брони
    @Override
    public void create(Order order) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(CREATE_SQL);

            updatePreparedStatementFields(order, ps);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_CREATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для удаления брони
    @Override
    public void delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(DELETE_SQL);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_DELETE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    /* Приватный метод для обновления объекта PreparedStatement,
    используемый для отправки данных в базу данных */
    private static void updatePreparedStatementFields(Order order, PreparedStatement ps) throws SQLException {
        ps.setString(1, order.getSurname());
        ps.setString(2, order.getName());
        ps.setDate(3, Date.valueOf(order.getBirthdate()));
        ps.setString( 4, order.getPhoneCell());
        setPsFieldWithNullCheck(ps, 5, order.getEmail());
        ps.setInt(6, order.getOffice().getId());
        ps.setInt(7, order.getPaymentType().getId());
        ps.setInt(8, order.getCoach().getId());
        ps.setInt(9, order.getPersonQuantity());
        ps.setInt(10, order.getHours());
    }

    private static void setPsFieldWithNullCheck(PreparedStatement ps, int id, String value) throws SQLException {
        if (value == null) {
            ps.setNull(id, Types.VARCHAR);
        } else {
            ps.setString(id, value);
        }
    }

    //Метод для обновления брони
    @Override
    public Order update(Order order) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(UPDATE_SQL);

            updatePreparedStatementFields(order, ps);

            Integer orderId = order.getId();
            ps.setInt(11, orderId);

            ps.executeUpdate();

            return get(orderId).get();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_UPDATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для получения брони по его ID
    @Override
    public Optional<Order> get(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_SQL);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(buildOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return Optional.empty();
    }

    //Метод для получения всех броней
    @Override
    public List<Order> getAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                orderList.add(buildOrderFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return orderList;
    }

    /* Приватный метод для создания экземпляра клиента из объекта ResultSet,
    содержащий данные из базы данных */
    private static Order buildOrderFromResultSet(ResultSet rs) throws SQLException {
        return Order.builder()
                .id(rs.getInt("Orders.id"))
                .surname(rs.getString("surname"))
                .name(rs.getString("Orders.name"))
                .birthdate(rs.getDate("birthdate").toLocalDate())
                .phoneCell((rs.getString("phone_cell")))
                .email(rs.getString("email"))
                .office(new Office(rs.getInt("office.id"),rs.getString("office.name")))
                .paymentType(new PaymentType(rs.getInt("payment_type.id"),rs.getString("payment_type.name")))
                .coach(new Coach(rs.getInt("coach.id"),rs.getString("coach.name")))
                .personQuantity(rs.getInt("person_quantity"))
                .hours(rs.getInt("hours"))
                .build();
    }
}

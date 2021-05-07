package by.bsuir.nika.lombard.dao.impl;

import by.bsuir.nika.lombard.dao.ItemDao;
import by.bsuir.nika.lombard.dao.connection.ConnectionPool;
import by.bsuir.nika.lombard.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Category;
import by.bsuir.nika.lombard.dao.model.entity.Client;
import by.bsuir.nika.lombard.dao.model.entity.Item;
import by.bsuir.nika.lombard.dao.model.util.Status;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl implements ItemDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final ItemDaoImpl instance = new ItemDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на создание предмета
    private static final String CREATE_SQL = "INSERT INTO Items(" +
            "owner, category, name, `desc`, `condition`, price, accept_date, keeping_days, status) VALUES (" +
            "?,?,?,?,?,?,?,?,?)";

    //SQL Запрос на удаление предмета по его ID
    private static final String DELETE_SQL = "DELETE FROM Items WHERE id = ?";

    //SQL Запрос на обновление предмета
    private static final String UPDATE_SQL = "UPDATE Items SET owner = ?, category = ?, name = ?, `desc` = ?, " +
            "`condition` = ?, price = ?, accept_date = ?, keeping_days = ?, status = ? " +
            "WHERE id = ?";

    //SQL Запрос на получение предмета по его ID
    private static final String GET_SQL = "SELECT Items.id, owner, category.id, category.name, " +
            "Items.name, `desc`, Items.condition, price, accept_date, keeping_days, statuses.id, statuses.name FROM Items " +
            "JOIN ItemCategories category ON Items.category = category.id " +
            "JOIN ItemStatuses statuses ON Items.status = statuses.id " +
            "WHERE Items.id = ?";

    //SQL Запрос на всех предметов клиента
    private static final String GET_ALL_BY_CLIENT_ID_SQL = "SELECT Items.id, owner, category.id, category.name, " +
            "Items.name, Items.desc, Items.condition, price, accept_date, keeping_days, statuses.id, statuses.name FROM Items " +
            "JOIN ItemCategories category ON Items.category = category.id " +
            "JOIN ItemStatuses statuses ON Items.status = statuses.id " +
            "WHERE owner = ?";

    //SQL Запрос на всех предмета
    private static final String GET_ALL_SQL = "SELECT Items.id, owner, category.id, category.name, " +
            "Items.name, Items.desc, Items.condition, price, accept_date, keeping_days, statuses.id, statuses.name FROM Items " +
            "JOIN ItemCategories category ON Items.category = category.id " +
            "JOIN ItemStatuses statuses ON Items.status = statuses.id";

    //Сообщение, которое помещается в Exception в случае ошибки создания предмета
    private static final String MESSAGE_CREATE_EXCEPTION = "Create item failed";

    //Сообщение, которое помещается в Exception в случае ошибки удаления предмета
    private static final String MESSAGE_DELETE_EXCEPTION = "Create item failed";

    //Сообщение, которое помещается в Exception в случае ошибки обновления предмета
    private static final String MESSAGE_UPDATE_EXCEPTION = "Update item failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения предмета
    private static final String MESSAGE_GET_EXCEPTION = "Get item failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех предметов клиента
    private static final String MESSAGE_GET_BY_CLIENT_ID_EXCEPTION = "Get by client id failed";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех предметов
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all items failed";

    //ID статуса предмета "Принят"
    private static final int STATUS_ACCEPTED = 1;

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private ItemDaoImpl() {
    }

    //Метод для создания предмета
    @Override
    public void create(Item item) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(CREATE_SQL);

            updatePreparedStatementFields(item, ps);
            ps.setInt(9, STATUS_ACCEPTED);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_CREATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для удаления предмета
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
    private static void updatePreparedStatementFields(Item item, PreparedStatement ps) throws SQLException {
        ps.setInt(1, item.getOwner().getId());
        ps.setInt(2, item.getCategory().getId());
        ps.setString(3, item.getName());
        ps.setString(4, item.getDesc());
        ps.setString(5, item.getCondition());
        ps.setInt(6, item.getPrice());
        ps.setDate(7, Date.valueOf(item.getAcceptDate()));
        ps.setInt(8, item.getKeepingDays());
    }

    //Метод для обновления предмета
    @Override
    public Item update(Item item) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(UPDATE_SQL);

            updatePreparedStatementFields(item, ps);

            Integer itemId = item.getId();
            ps.setInt(9, item.getStatus().getId());
            ps.setInt(10, itemId);

            ps.executeUpdate();

            return get(itemId).get();
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_UPDATE_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
    }

    //Метод для получения предмета по его ID
    @Override
    public Optional<Item> get(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_SQL);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(buildItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return Optional.empty();
    }

    //Метод для получения предметов клиента
    @Override
    public List<Item> getByClientId(int id) throws DaoException {
        List<Item> itemList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_BY_CLIENT_ID_SQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(buildItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_BY_CLIENT_ID_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return itemList;
    }

    //Метод для получения всех предметов
    @Override
    public List<Item> getAll() throws DaoException {
        List<Item> itemList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                itemList.add(buildItemFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(MESSAGE_GET_ALL_EXCEPTION, e);
        } finally {
            connectionPool.closeConnection(connection, ps);
        }
        return itemList;
    }

    /* Приватный метод для создания экземпляра предмета из объекта ResultSet,
    содержащий данные из базы данных */
    private static Item buildItemFromResultSet(ResultSet rs) throws SQLException {
        return Item.builder()
                .id(rs.getInt("Items.id"))
                .owner(Client.builder().id(rs.getInt("owner")).build())
                .category(new Category(rs.getInt("category.id"), rs.getString("category.name")))
                .name(rs.getString("Items.name"))
                .desc(rs.getString("desc"))
                .condition(rs.getString("condition"))
                .price(rs.getInt("price"))
                .acceptDate(rs.getDate("accept_date").toLocalDate())
                .keepingDays(rs.getInt("keeping_days"))
                .status(new Status(rs.getInt("statuses.id"),rs.getString("statuses.name")))
                .build();
    }
}

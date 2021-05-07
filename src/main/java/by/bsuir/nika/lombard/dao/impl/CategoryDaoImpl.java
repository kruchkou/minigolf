package by.bsuir.nika.lombard.dao.impl;

import by.bsuir.nika.lombard.dao.CategoryDao;
import by.bsuir.nika.lombard.dao.connection.ConnectionPool;
import by.bsuir.nika.lombard.dao.connection.impl.ConnectionPoolImpl;
import by.bsuir.nika.lombard.dao.exception.DaoException;
import by.bsuir.nika.lombard.dao.model.entity.Category;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    //Экземпляр класса. Паттерн "Singleton"
    @Getter
    private static final CategoryDaoImpl instance = new CategoryDaoImpl();

    //Соединение с базой данных
    private static final ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

    //SQL Запрос на получение всех категорий
    private static final String GET_ALL_SQL = "SELECT * FROM ItemCategories";

    //Сообщение, которое помещается в Exception в случае ошибки получения всех категорий
    private static final String MESSAGE_GET_ALL_EXCEPTION = "Get all categories failed";

    //Приватный конструктор, чтобы невозможно было создать дополнительный экземпляр класса
    private CategoryDaoImpl() {
    }

    //Метод для получения всех категорий
    @Override
    public List<Category> getAll() throws DaoException {
        List<Category> familyStatusList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionPool.getConnection();
            ps = connection.prepareStatement(GET_ALL_SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                familyStatusList.add(buildCategoryFromResultSet(rs));
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
    private static Category buildCategoryFromResultSet(ResultSet rs) throws SQLException {
        return Category.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }
}

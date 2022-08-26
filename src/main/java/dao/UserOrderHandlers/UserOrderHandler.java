package dao.UserOrderHandlers;

import Enums.UserOrderStatus;
import Executor.ResultHandler;
import dao.BookDAO;
import dao.UserDAO;
import entity.Book;
import entity.User;
import entity.UserOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOrderHandler implements ResultHandler<Optional<UserOrder>> {

    private static UserOrderHandler instance;

    private UserOrderHandler() {
    }

    public static synchronized UserOrderHandler getInstance() {
        if (instance == null)
            instance = new UserOrderHandler();
        return instance;
    }

    @Override
    public Optional<UserOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        final int id = resultSet.getInt("user_order_id");
        final int bookId = resultSet.getInt("book_id");
        final int userId = resultSet.getInt("user_id");
        final UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
        final Book book = BookDAO.getInstance().getById(bookId).get();
        final User user = UserDAO.getInstance().getUserByID(userId).get();
        return Optional.of(new UserOrder(id, user, book, status));
    }
}
package dao.UserOrderHandlers;

import enums.UserOrderStatus;
import executor.ResultHandler;
import dao.BookDAO;
import dao.UserDAO;
import entity.Book;
import entity.User;
import entity.UserOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserOrderListHandler implements ResultHandler<List<UserOrder>> {
    private static UserOrderListHandler instance;

    private UserOrderListHandler() {
    }

    public static synchronized UserOrderListHandler getInstance() {
        if (instance == null)
            instance = new UserOrderListHandler();
        return instance;
    }

    @Override
    public List<UserOrder> handle(ResultSet resultSet) throws SQLException {
        final List<UserOrder> resultUserOrderList = new ArrayList();
        while (resultSet.next()) {
            final int id = resultSet.getInt("user_order_id");
            final int bookId = resultSet.getInt("book_id");
            final int userId = resultSet.getInt("user_id");
            final UserOrderStatus status = UserOrderStatus.valueOf(resultSet.getString("status"));
            final Book book = BookDAO.getInstance().getById(bookId).get();
            final User user = UserDAO.getInstance().getUserByID(userId).get();
            resultUserOrderList.add(new UserOrder(id, user, book, status));
        }
        return resultUserOrderList;
    }
}
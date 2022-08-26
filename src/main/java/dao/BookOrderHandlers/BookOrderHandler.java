package dao.BookOrderHandlers;

import enums.BookOption;
import executor.ResultHandler;
import services.BookInstanceService;
import services.UserOrderService;
import entity.BookOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookOrderHandler implements ResultHandler<Optional<BookOrder>> {
    private static BookOrderHandler instance;

    private BookOrderHandler() {
    }

    public static synchronized BookOrderHandler getInstance() {
        if (instance == null)
            instance = new BookOrderHandler();
        return instance;
    }

    @Override
    public Optional<BookOrder> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        final int id = resultSet.getInt("book_order_id");
        final int book_instanceid = resultSet.getInt("book_instanceid");
        final int user_orderid = resultSet.getInt("user_orderid");
        final BookOption option = BookOption.valueOf(resultSet.getString("option"));
        return Optional.of(new BookOrder(id, BookInstanceService.getInstance().getById(book_instanceid),
                UserOrderService.getInstance().getById(user_orderid), option));
    }
}

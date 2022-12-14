package dao.BookOrderHandlers;

import enums.BookOption;
import executor.ResultHandler;
import services.BookInstanceService;
import services.UserOrderService;
import entity.BookOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookOrderListHandler implements ResultHandler<List<BookOrder>> {
    private static BookOrderListHandler instance;

    private BookOrderListHandler() {
    }

    public static synchronized BookOrderListHandler getInstance() {
        if (instance == null)
            instance = new BookOrderListHandler();
        return instance;
    }

    @Override
    public List<BookOrder> handle(ResultSet resultSet) throws SQLException {
        final List<BookOrder> output = new ArrayList<BookOrder>();
        while (resultSet.next()) {
            final int id = resultSet.getInt("book_order_id");
            final int book_instanceid = resultSet.getInt("book_instanceid");
            final int user_orderid = resultSet.getInt("user_orderid");
            final BookOption option = BookOption.valueOf(resultSet.getString("option"));
            final BookOrder bookOrder = new BookOrder(id, BookInstanceService.getInstance().getById(book_instanceid),
                    UserOrderService.getInstance().getById(user_orderid), option);
            output.add(bookOrder);
        }
        return output;
    }
}
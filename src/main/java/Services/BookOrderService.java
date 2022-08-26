package Services;

import Enums.BookOption;
import dao.BookOrderDAO;
import entity.BookOrder;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookOrderService {
    private static final Logger logger = Logger.getLogger(BookOrderService.class);
    private static BookOrderService instance;
    private final BookOrderDAO bookOrderDAO = BookOrderDAO.getInstance();

    private BookOrderService(){}

    public static synchronized BookOrderService getInstance(){
        if (instance == null) {
            instance = new BookOrderService();
            logger.info("BookOrderService instance created");
        }
        logger.info("BookOrderService instance supplied");
        return instance;
    }


    public void create(int bookInstanceId, int userOrderId, BookOption bookOption) throws SQLException {
        bookOrderDAO.create(bookInstanceId, userOrderId, bookOption);
        logger.info("Created book order ({}, {}, {})");
    }


    public List<BookOrder> getAll() throws SQLException {
        return bookOrderDAO.getAll();
    }


    public List<BookOrder> getByUserId(int userId) throws SQLException {
        return bookOrderDAO.getByUserId(userId);
    }

    public List<User> getAllUsersWithOrders() throws SQLException {
        List<BookOrder> bookOrders = getAll();
        List<User> result = new ArrayList<>();
        for (BookOrder bookorder : bookOrders) {
            result.add(bookorder.getUserOrder().getUser());
        }
        return result;
    }

    public void deleteById(int userOrderId) throws SQLException {
        bookOrderDAO.deleteById(userOrderId);
        logger.info("Deleted book order id={}");
    }
}
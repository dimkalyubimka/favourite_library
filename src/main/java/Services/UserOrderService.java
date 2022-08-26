package Services;

import Enums.UserOrderStatus;
import dao.BookDAO;
import dao.UserOrderDAO;
import entity.UserOrder;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.*;



public class UserOrderService {
    private static final Logger logger = Logger.getLogger(UserOrderService.class);
    private static UserOrderService instance;
    private final UserOrderDAO userOrderDAO = UserOrderDAO.getInstance();
    private final BookDAO bookDAO = BookDAO.getInstance();

    private UserOrderService() {
    }

    public static synchronized UserOrderService getInstance() {
        if (instance == null) {
            instance = new UserOrderService();
            logger.info("UserOrderService instance created");
        }
        logger.info("UserOrderService instance supplied");
        return instance;
    }


    public List<UserOrder> getNewUserOrders() throws SQLException{
        return userOrderDAO.getUserOrderByStatus(UserOrderStatus.NEW);
    }


    private List<Integer> getFreeBookInstancesForThisBook(int bookId) throws SQLException{
        return bookDAO.getFreeBookInstancesForThisBook(bookId);
    }


    public Map<UserOrder, List<Integer>> getUserOrderAndFreeBookInstanceMap(List<UserOrder> userOrderList) throws SQLException {
        final Map<UserOrder, List<Integer>> resultMap = new TreeMap<>(Comparator.comparingInt(UserOrder::getId));
        for (UserOrder userOrder : userOrderList) {
            resultMap.put(userOrder, getFreeBookInstancesForThisBook(userOrder.getBook().getId()));
        }
        return resultMap;
    }


    public void setUserOrderStatus(int userOrderId, UserOrderStatus newStatus) throws SQLException {
        userOrderDAO.setUserOrderStatus(userOrderId, newStatus);
    }


    public void createNewUserOrder(final int bookID, final int userID) throws SQLException {
        userOrderDAO.createNewUserOrder(bookID, userID);
    }


    public UserOrder getById(int user_orderid) throws SQLException {
        Optional<UserOrder> oUserOrder = userOrderDAO.getUserOrderByID(user_orderid);
        if (oUserOrder.isEmpty()) throw new SQLException("There is no such user_order for id = " + user_orderid);
        else return oUserOrder.get();
    }


    public void deleteNewUserOrder(int userOrderId) throws SQLException {
        userOrderDAO.deleteNewUserOrder(userOrderId);
    }
}

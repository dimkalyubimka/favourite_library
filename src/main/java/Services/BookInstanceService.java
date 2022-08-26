package Services;


import dao.BookInstanceDAO;
import entity.BookInstance;
import org.apache.log4j.Logger;


import java.sql.SQLException;
import java.util.Optional;


public class BookInstanceService {
    private static final Logger logger = Logger.getLogger(BookInstanceService.class);
    private static BookInstanceService instance;
    private final BookInstanceDAO bookInstanceDAO = BookInstanceDAO.getInstance();

    private BookInstanceService(){}

    public static synchronized BookInstanceService getInstance() {
        if (instance == null) {
            instance = new BookInstanceService();
            logger.info("BookInstanceService instance created");
        }
        logger.info("BookInstanceService instance supplied");
        return instance;
    }

    public BookInstance getById(int book_instanceid) throws SQLException {
        Optional<BookInstance> oBookInstance = bookInstanceDAO.getById(book_instanceid);
        if (oBookInstance.isEmpty()) throw new SQLException("There is no such book_instance for id = " + book_instanceid);
        else return oBookInstance.get();
    }
}
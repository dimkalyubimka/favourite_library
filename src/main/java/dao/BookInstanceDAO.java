package dao;

import executor.Executor;
import dao.BookInstanceHandlers.BookInstanceHandler;
import entity.BookInstance;
import executor.ResultHandler;
import java.sql.SQLException;
import java.util.Optional;

public class BookInstanceDAO {
    private static final String QUERY_GET_BY_ID = "SELECT id, bookid FROM book_instance WHERE id=?";
    private final Executor executor = Executor.getInstance();
    private static BookInstanceDAO instance;
    private final ResultHandler<Optional<BookInstance>> bookInstanceHandler = BookInstanceHandler.getInstance();


    private BookInstanceDAO(){}

    public static synchronized BookInstanceDAO getInstance() {
        if (instance == null) instance = new BookInstanceDAO();
        return instance;
    }

    public Optional<BookInstance> getById(int book_instanceid) throws SQLException {
        return executor.executeQuery(QUERY_GET_BY_ID, bookInstanceHandler, book_instanceid);
    }
}
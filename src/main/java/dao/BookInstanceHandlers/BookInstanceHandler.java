package dao.BookInstanceHandlers;

import executor.ResultHandler;
import dao.BookDAO;
import entity.Book;
import entity.BookInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookInstanceHandler implements ResultHandler<Optional<BookInstance>> {
    private static BookInstanceHandler instance;

    private BookInstanceHandler() {
    }

    public static synchronized BookInstanceHandler getInstance() {
        if (instance == null)
            instance = new BookInstanceHandler();
        return instance;
    }

    @Override
    public Optional<BookInstance> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();

        final int id = resultSet.getInt("id");
        final int bookId = resultSet.getInt("bookid");
        final Optional<Book> oBook = BookDAO.getInstance().getById(bookId);
        return oBook.map(book -> new BookInstance(id, book));
    }
}
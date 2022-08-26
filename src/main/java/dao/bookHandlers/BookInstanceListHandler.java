package dao.bookHandlers;

import executor.ResultHandler;
import dao.BookDAO;
import entity.Book;
import entity.BookInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookInstanceListHandler implements ResultHandler<List<BookInstance>> {
    private static BookInstanceListHandler instance;

    private BookInstanceListHandler() {
    }

    public static synchronized BookInstanceListHandler getInstance() {
        if (instance == null)
            instance = new BookInstanceListHandler();
        return instance;
    }

    @Override
    public List<BookInstance> handle(ResultSet resultSet) throws SQLException {
        final List<BookInstance> bookInstances = new ArrayList<>();

        while (resultSet.next()) {
            final int bookId = resultSet.getInt("bookid");
            final Optional<Book> oBook = BookDAO.getInstance().getById(bookId);
            if (!oBook.isPresent()) throw new SQLException("There is no Book for id = " + bookId);
            else bookInstances.add(new BookInstance(resultSet.getInt("id"), oBook.get()));
        }

        return bookInstances;
    }
}
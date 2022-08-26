package dao.bookHandlers;


import Executor.ResultHandler;
import entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class BookHandler implements ResultHandler<Optional<Book>> {
    private static BookHandler instance;

    private BookHandler() {
    }

    public static synchronized BookHandler getInstance() {
        if (instance == null)
            instance = new BookHandler();
        return instance;
    }

    @Override
    public Optional<Book> handle(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) return Optional.empty();
        final int id = resultSet.getInt("id");
        final String author = resultSet.getString("author");
        final String title = resultSet.getString("title");
        final int year = resultSet.getInt("year");
        return Optional.of(new Book(id, author, title, year));
    }
}
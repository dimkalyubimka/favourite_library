package dao.bookHandlers;

import executor.ResultHandler;
import entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookListHandler implements ResultHandler<List<Book>> {
    private static BookListHandler instance;

    private BookListHandler() {
    }

    public static synchronized BookListHandler getInstance() {
        if (instance == null)
            instance = new BookListHandler();
        return instance;
    }

    @Override
    public List<Book> handle(ResultSet resultSet) throws SQLException {
        final List<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            books.add(new Book(resultSet.getInt("id"), resultSet.getString("author"),
                    resultSet.getString("title"), resultSet.getInt("year")));
        }

        return books;
    }
}
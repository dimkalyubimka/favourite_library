package dao.UserHandlers;

import Executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIsLibrarianHandler implements ResultHandler<Boolean> {
    private static UserIsLibrarianHandler instance;

    private UserIsLibrarianHandler() {
    }

    public static synchronized UserIsLibrarianHandler getInstance() {
        if (instance == null)
            instance = new UserIsLibrarianHandler();
        return instance;
    }

    @Override
    public Boolean handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return "LIBRARIAN".equals(resultSet.getString("role"));
        }

        throw new SQLException("No such user in database");
    }
}
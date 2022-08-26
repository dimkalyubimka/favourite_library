package dao.UserHandlers;

import enums.UserRole;
import executor.ResultHandler;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserHandler implements ResultHandler<Optional<User>> {
    private static UserHandler instance;

    private UserHandler() {
    }

    public static synchronized UserHandler getInstance() {
        if (instance == null)
            instance = new UserHandler();
        return instance;
    }

    @Override
    public Optional<User> handle(ResultSet resultSet) throws SQLException {

        if (!resultSet.next()) return Optional.empty();
        final int userID = resultSet.getInt("id");
        final String name = resultSet.getString("name");
        final String lastname = resultSet.getString("lastname");
        final String email = resultSet.getString("email");
        final String passwordhash = resultSet.getString("passwordhash");
        final UserRole role = UserRole.valueOf(resultSet.getString("role"));
        return Optional.of(new User(userID, name, lastname, email, passwordhash, role));
    }
}
package dao.UserHandlers;

import enums.UserRole;
import executor.ResultHandler;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListHandler implements ResultHandler<List<User>> {
    private static UserListHandler instance;

    private UserListHandler() {
    }

    public static synchronized UserListHandler getInstance() {
        if (instance == null)
            instance = new UserListHandler();
        return instance;
    }

    @Override
    public List<User> handle(ResultSet resultSet) throws SQLException {
        final List<User> resultUserList = new ArrayList();
        while (resultSet.next()) {
            final int userID = resultSet.getInt("id");
            final String name = resultSet.getString("name");
            final String lastname = resultSet.getString("lastname");
            final String email = resultSet.getString("email");
            final String passwordhash = resultSet.getString("passwordhash");
            final UserRole role = UserRole.valueOf(resultSet.getString("role"));
            resultUserList.add(new User(userID, name, lastname, email, passwordhash, role));
        }
        return resultUserList;
    }
}
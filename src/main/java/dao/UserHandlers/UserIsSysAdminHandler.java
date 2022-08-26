package dao.UserHandlers;

import Executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserIsSysAdminHandler implements ResultHandler<Boolean> {


    private static UserIsSysAdminHandler instance;

    private UserIsSysAdminHandler() {
    }

    public static synchronized UserIsSysAdminHandler getInstance() {
        if (instance == null)
            instance = new UserIsSysAdminHandler();
        return instance;
    }

    @Override
    public Boolean handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return "SYS_ADMIN".equals(resultSet.getString("role"));
        }

        throw new SQLException("No such user in database");
    }
}
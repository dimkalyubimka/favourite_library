package dao.DaoExceptions;

import java.sql.SQLException;

public class DataBaseExceptions extends SQLException {

    public DataBaseExceptions() {
    }

    public DataBaseExceptions(String message) {
        super(message);
    }

    public DataBaseExceptions(Exception e) {
        super(e);
    }

    public DataBaseExceptions(String message, Exception e) {
        super(message, e);
    }
}
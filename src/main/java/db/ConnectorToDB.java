package db;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.DaoExceptions.DataBaseExceptions;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class ConnectorToDB {

    private static ConnectorToDB pool;
    private final DataSource dataSource;

    public ConnectorToDB() throws DataBaseExceptions {
        dataSource = getMysqlDataSource();
    }

    public static DataSource getMysqlDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("public");
        dataSource.setUser("postgres");
        dataSource.setPassword("root");
        return dataSource;
    }

    public static synchronized ConnectorToDB getInstance() throws DataBaseExceptions {
        if (pool == null) {
            pool = new ConnectorToDB();
        }
        return pool;
    }

    public Connection connect() throws SQLException {
        return dataSource.getConnection();
    }
}


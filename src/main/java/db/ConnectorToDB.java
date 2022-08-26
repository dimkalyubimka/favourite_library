package db;


import dao.DaoExceptions.DataBaseExceptions;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public final class ConnectorToDB {

    private static ConnectorToDB pool;
    private final DataSource dataSource;

    public ConnectorToDB() throws DataBaseExceptions {
        dataSource = getDataSource();
    }

    public static DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
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


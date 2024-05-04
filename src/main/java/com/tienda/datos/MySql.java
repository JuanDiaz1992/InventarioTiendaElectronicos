package com.tienda.datos;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class MySql {
    private static final String JDBC_DATABASE = "inventariostiendaelectronica";

    private static final String JDBC_HOSTNAME = "localhost";

    private static final String JDBC_PORT = "3306";

    private static final String JDBC_URL = "jdbc:mysql://" + JDBC_HOSTNAME + ":" + JDBC_PORT + "/" + JDBC_DATABASE + "?useSSL=false";

    private static final String JDBC_USER = "root";

    private static final String JDBC_PASSWORD = "";

    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        ds.setInitialSize(5);
        return ds;
    }

    public static Connection getConection() throws SQLException {
        return getDataSource().getConnection();
    }
    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }
    public static void close(Statement smtm) throws SQLException {
        smtm.close();
    }
    public static void close(PreparedStatement smtm) throws SQLException {
        smtm.close();
    }
    public static void close(Connection connection) throws SQLException {
        connection.close();
    }

}

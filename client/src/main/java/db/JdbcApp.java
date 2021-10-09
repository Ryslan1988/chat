package db;


import java.sql.*;

public class JdbcApp  {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        try {
            connect();
//            createTableEx();
//            insertEx();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void disconnect() {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:user.db");
        statement = connection.createStatement();
    }

    private static void createTableEx() throws SQLException {
        connection.setAutoCommit(false);
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                " login TEXT UNIQUE ,\n" +
                " password TEXT,\n" +
                " nickname TEXT UNIQUE \n" +
                " );");
        System.out.println("Create new databace users");
        connection.setAutoCommit(true);
    }

    private static void insertEx() throws SQLException {
        statement.executeUpdate("INSERT INTO users (login,password,nickname) VALUES ('admin','123','admin');");

    }

    public static void setNewUsers(String login, String pass, String nick) throws SQLException {
        connect();
        String sql = String.format("INSERT INTO users (login, password, nickname) VALUES ('%s', '%s', '%s')", login, pass, nick);

        try {
            boolean rs = statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

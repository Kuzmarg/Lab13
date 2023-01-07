package task2;

import lombok.SneakyThrows;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connection {
    public static Connection connection;
    private java.sql.Connection dbConnection;
    @SneakyThrows
    private Connection() {
        dbConnection = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
    }
    @SneakyThrows
    public ResultSet executeQuery(String query) {
        Statement stmt = dbConnection.createStatement();
        return stmt.executeQuery(query);
    }
    public static Connection getConnection() {
        if (connection == null){
            connection = new Connection();
        }
        return connection;
    }
}

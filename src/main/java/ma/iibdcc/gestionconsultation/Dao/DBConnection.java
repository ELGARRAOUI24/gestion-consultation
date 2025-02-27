package ma.iibdcc.gestionconsultation.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/DB_Cabinet","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }
}

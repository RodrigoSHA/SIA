package ConexionDB;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


public class Conexion {
    public static Connection getConnection(String url, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection cn = DriverManager.getConnection(url, user, password);
            return cn;
        } catch (ClassNotFoundException ex) {
            System.out.println("Error en el driver");
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}

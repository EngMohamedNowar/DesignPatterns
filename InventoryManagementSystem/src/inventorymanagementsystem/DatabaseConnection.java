package inventorymanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    private static final String URL = 
        "jdbc:sqlserver://Localhost\\SQLEXPRESS:1433;"
        + "databaseName=InventoryManagement;";
    private static final String USER = "sa"; 
    private static final String PASSWORD = "1411";
    public static Connection getConnection() {
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Cannot Load JDBC Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed!");
            e.printStackTrace();
        }
        return null;
    }
}


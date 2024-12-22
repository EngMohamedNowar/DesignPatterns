package inventorymanagementsystem.db;

import inventorymanagementsystem.DatabaseConnection;
import java.sql.*;

public class StockDAO {
    private static StockDAO instance; // Singleton instance
    private Connection connection;

    // Private constructor to prevent instantiation
    private StockDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Public method to provide the Singleton instance
    public static synchronized StockDAO getInstance() {
        if (instance == null) {
            instance = new StockDAO();
        }
        return instance;
    }

    // Update stock quantity for a product
    public boolean updateStock(int productId, int quantityChange) {
        String query = "UPDATE Products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantityChange);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // View current stock levels
    public ResultSet getStockLevels() {
        String query = "SELECT product_id, name AS product_name, stock_quantity FROM Products";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}



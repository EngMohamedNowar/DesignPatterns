package inventorymanagementsystem.db;

import inventorymanagementsystem.DatabaseConnection;
import inventorymanagementsystem.db.Order;
import java.sql.*;

public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Add an order
    public boolean addOrder(Order order) {
        String query = "INSERT INTO Orders (product_id, supplier_id, order_date, quantity, total_amount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getProductId());
            stmt.setInt(2, order.getSupplierId());
            stmt.setDate(3, Date.valueOf(order.getOrderDate()));
            stmt.setInt(4, order.getQuantity());
            stmt.setDouble(5, order.getTotalAmount());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all orders
    public ResultSet getAllOrders() {
        String query = "SELECT * FROM Orders";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an order
    public boolean updateOrder(Order order) {
        String query = "UPDATE Orders SET product_id = ?, supplier_id = ?, order_date = ?, quantity = ?, total_amount = ? WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getProductId());
            stmt.setInt(2, order.getSupplierId());
            stmt.setDate(3, Date.valueOf(order.getOrderDate()));
            stmt.setInt(4, order.getQuantity());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setInt(6, order.getOrderId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an order
    public boolean deleteOrder(int orderId) {
        String query = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


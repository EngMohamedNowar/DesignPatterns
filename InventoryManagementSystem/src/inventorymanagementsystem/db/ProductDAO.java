/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem.db;

import inventorymanagementsystem.DatabaseConnection;
import inventorymanagementsystem.db.Product;
import java.sql.*;

public class ProductDAO {
    private Connection connection;

    
 public ProductDAO() {
        connection = DatabaseConnection.getConnection();  // Singleton connection
    }
    // Add a product
    public boolean addProduct(Product product) {
        String query = "INSERT INTO Products (name, category, price, stock_quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all products
    public ResultSet getAllProducts() {
        String query = "SELECT * FROM Products";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a product
    public boolean updateProduct(Product product) {
        String query = "UPDATE Products SET name = ?, category = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStockQuantity());
            stmt.setInt(5, product.getProductId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a product
public boolean deleteProduct(int productId) {
    // First, delete related orders if they exist
    String deleteOrdersQuery = "DELETE FROM Orders WHERE product_id = ?";
    
    try (PreparedStatement deleteOrdersStmt = connection.prepareStatement(deleteOrdersQuery)) {
        deleteOrdersStmt.setInt(1, productId);
        deleteOrdersStmt.executeUpdate(); // Delete orders

        // Now, delete the product
        String query = "DELETE FROM Products WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            return stmt.executeUpdate() > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}


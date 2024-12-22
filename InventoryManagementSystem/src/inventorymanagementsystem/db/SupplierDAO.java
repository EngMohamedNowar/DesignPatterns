
// SupplierDAO.java
package inventorymanagementsystem.db;

import inventorymanagementsystem.DatabaseConnection;
import inventorymanagementsystem.db.Supplier;
import java.sql.*;

public class SupplierDAO {
    private final Connection connection;

    public SupplierDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Add a supplier
    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO Suppliers (name, type, contact_info) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getType());
            stmt.setString(3, supplier.getContactInfo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all suppliers
    public ResultSet getAllSuppliers() {
        String query = "SELECT * FROM Suppliers";
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a supplier
    public boolean updateSupplier(Supplier supplier) {
        String query = "UPDATE Suppliers SET name = ?, type = ?, contact_info = ? WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getType());
            stmt.setString(3, supplier.getContactInfo());
            stmt.setInt(4, supplier.getSupplierId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a supplier
public boolean deleteSupplier(int supplierId) {
    // First, delete all orders that reference this supplier
    String deleteOrdersQuery = "DELETE FROM Orders WHERE supplier_id = ?";
    
    try (PreparedStatement deleteOrdersStmt = connection.prepareStatement(deleteOrdersQuery)) {
        deleteOrdersStmt.setInt(1, supplierId);
        deleteOrdersStmt.executeUpdate();
        
        // Now, delete the supplier
        String query = "DELETE FROM Suppliers WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, supplierId);
            return stmt.executeUpdate() > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}


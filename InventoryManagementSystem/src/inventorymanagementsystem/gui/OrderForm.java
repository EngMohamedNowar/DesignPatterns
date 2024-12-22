package inventorymanagementsystem.gui;

import inventorymanagementsystem.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import inventorymanagementsystem.db.Order;
import inventorymanagementsystem.db.OrderDAOProxy;  // Importing the Proxy
import inventorymanagementsystem.db.ProductDAO;
import inventorymanagementsystem.db.SupplierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import net.proteanit.sql.DbUtils;

public class OrderForm extends JPanel {
    private JTextField quantityField, totalAmountField;
    private JComboBox<String> productComboBox, supplierComboBox;
    private JButton addButton, updateButton, deleteButton;
    private JTable orderTable;
    private OrderDAOProxy orderDAO;  // Using the Proxy
    private ProductDAO productDAO;
    private SupplierDAO supplierDAO;

    public OrderForm() {
        // Initialize DAOs
        orderDAO = new OrderDAOProxy();  // Using the Proxy
        productDAO = new ProductDAO();
        supplierDAO = new SupplierDAO();

        setLayout(new BorderLayout());

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Product:"));
        productComboBox = new JComboBox<>(getProducts());
        inputPanel.add(productComboBox);

        inputPanel.add(new JLabel("Supplier:"));
        supplierComboBox = new JComboBox<>(getSuppliers());
        inputPanel.add(supplierComboBox);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Total Amount:"));
        totalAmountField = new JTextField();
        inputPanel.add(totalAmountField);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Order");
        updateButton = new JButton("Update Order");
        deleteButton = new JButton("Delete Order");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Table for displaying orders
        orderTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(orderTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        addButton.addActionListener(e -> addOrder());
        updateButton.addActionListener(e -> updateOrder());
        deleteButton.addActionListener(e -> deleteOrder());

        // Listener for quantityField to calculate totalAmount
        quantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int quantity = Integer.parseInt(quantityField.getText());
                    int productId = Integer.parseInt(productComboBox.getSelectedItem().toString().split(" - ")[0]);
                    double price = getProductPrice(productId);
                    double totalAmount = quantity * price;
                    totalAmountField.setText(String.format("%.2f", totalAmount));
                } catch (NumberFormatException ex) {
                    totalAmountField.setText(""); // Clear total amount if invalid input
                }
            }
        });

        // Automatically view orders when the form is loaded
        viewOrders();
    }

    // Method to get the price of a selected product
    private double getProductPrice(int productId) {
        double price = 0.0;
        String query = "SELECT price FROM Products WHERE product_id = " + productId;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    private String[] getProducts() {
        ArrayList<String> products = new ArrayList<>();
        String query = "SELECT product_id, name FROM Products";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("name");
                products.add(id + " - " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products.toArray(new String[0]);
    }

    private String[] getSuppliers() {
        ArrayList<String> suppliers = new ArrayList<>();
        String query = "SELECT supplier_id, name FROM Suppliers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("supplier_id");
                String name = rs.getString("name");
                suppliers.add(id + " - " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers.toArray(new String[0]);
    }

    private void addOrder() {
        try {
            int productId = Integer.parseInt(productComboBox.getSelectedItem().toString().split(" - ")[0]);
            int supplierId = Integer.parseInt(supplierComboBox.getSelectedItem().toString().split(" - ")[0]);
            int quantity = Integer.parseInt(quantityField.getText());
            double totalAmount = Double.parseDouble(totalAmountField.getText());

            String orderDate = java.time.LocalDate.now().toString();
            Order order = new Order(0, productId, supplierId, orderDate, quantity, totalAmount);

            if (orderDAO.addOrder(order)) {
                JOptionPane.showMessageDialog(this, "Order added successfully!");
                clearFields();
                viewOrders(); // Automatically refresh the order view
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add order.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity.");
        }
    }

    private void updateOrder() {
        try {
            int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Order ID to Update:"));
            int productId = Integer.parseInt(productComboBox.getSelectedItem().toString().split(" - ")[0]);
            int supplierId = Integer.parseInt(supplierComboBox.getSelectedItem().toString().split(" - ")[0]);
            int quantity = Integer.parseInt(quantityField.getText());
            double totalAmount = Double.parseDouble(totalAmountField.getText());

            Order order = new Order(orderId, productId, supplierId, java.time.LocalDate.now().toString(), quantity, totalAmount);

            if (orderDAO.updateOrder(order)) {
                JOptionPane.showMessageDialog(this, "Order updated successfully!");
                clearFields();
                viewOrders(); // Automatically refresh the order view
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update order.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage());
        }
    }

    private void deleteOrder() {
        int orderId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Order ID to Delete:"));
        if (orderDAO.deleteOrder(orderId)) {
            JOptionPane.showMessageDialog(this, "Order deleted successfully!");
            viewOrders(); // Automatically refresh the order view
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete order.");
        }
    }

    private void viewOrders() {
        ResultSet rs = orderDAO.getAllOrders();
        if (rs != null) {
            orderTable.setModel(DbUtils.resultSetToTableModel(rs));
        } else {
            JOptionPane.showMessageDialog(this, "No data to display!");
        }
    }

    private void clearFields() {
        quantityField.setText("");
        totalAmountField.setText("");
    }
}






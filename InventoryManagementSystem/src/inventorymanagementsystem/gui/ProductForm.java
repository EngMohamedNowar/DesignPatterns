package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.Product;
import inventorymanagementsystem.db.ProductDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ProductForm extends JPanel {
    private JTextField nameField, categoryField, priceField, quantityField;
    private JButton addButton, updateButton, deleteButton;
    private JTable productTable;
    private ProductDAO productDAO;

    public ProductForm() {
        // Initialize ProductDAO
        productDAO = new ProductDAO();

        // Set layout
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Product");
        updateButton = new JButton("Update Product");
        deleteButton = new JButton("Delete Product");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Table to display products
        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProduct();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProduct();
            }
        });

        // Automatically view products when the form is loaded
        viewProducts();
    }

    // Add product to database
    private void addProduct() {
        String name = nameField.getText();
        String category = categoryField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(0, name, category, price, quantity);
        if (productDAO.addProduct(product)) {
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            clearFields();
            viewProducts(); // Refresh the product view automatically
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add product.");
        }
    }

    // Update product in database
    private void updateProduct() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Product ID to Update:"));
        String name = nameField.getText();
        String category = categoryField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        Product product = new Product(productId, name, category, price, quantity);
        if (productDAO.updateProduct(product)) {
            JOptionPane.showMessageDialog(this, "Product updated successfully!");
            clearFields();
            viewProducts(); // Refresh the product view automatically
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update product.");
        }
    }

    // Delete product from database
    private void deleteProduct() {
        int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Product ID to Delete:"));
        if (productDAO.deleteProduct(productId)) {
            JOptionPane.showMessageDialog(this, "Product deleted successfully!");
            viewProducts(); // Refresh the product view automatically
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete product.");
        }
    }

    // View all products in the database
    private void viewProducts() {
        ResultSet rs = productDAO.getAllProducts();
        if (rs != null) {
            productTable.setModel(DbUtils.resultSetToTableModel(rs)); // Populate table with data
        } else {
            JOptionPane.showMessageDialog(this, "No products to display!");
        }
    }

    // Clear input fields
    private void clearFields() {
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
    }
}



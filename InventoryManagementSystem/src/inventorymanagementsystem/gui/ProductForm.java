package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.Product;
import inventorymanagementsystem.db.ProductDAO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;


public class ProductForm extends JPanel {
    private JTextField nameField, priceField, quantityField;
    private JButton addButton, updateButton, deleteButton;
    private JTable productTable;
    private ProductDAO productDAO;
    private JComboBox<String> categoryComboBox; // ComboBox for category selection

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
        // Create ComboBox for category selection
        categoryComboBox = new JComboBox<>(new String[]{"Mobile", "Laptop"});
        inputPanel.add(categoryComboBox);

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
        String category = (String) categoryComboBox.getSelectedItem(); // Get selected category
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        // Use the ProductFactory to create the appropriate Product
        Product product = ProductFactory.getProduct(category, 0, name, price, quantity); // productId is 0 for new products
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
        // Prompt user for product ID to update
        int productId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Product ID to Update:"));
        String name = nameField.getText();
        String category = (String) categoryComboBox.getSelectedItem(); // Get selected category
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        // Use the ProductFactory to create the appropriate Product
        Product product = ProductFactory.getProduct(category, productId, name, price, quantity); // Correct productId for updating
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
    int selectedRow = productTable.getSelectedRow();
    if (selectedRow != -1) {
        int supplierId = (int) productTable.getValueAt(selectedRow, 0);

        // Confirm the deletion
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this supplier?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Attempt to delete the supplier
                boolean result = productDAO.deleteProduct(supplierId);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
                    viewProducts();  // Refresh the table after deleting
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete supplier. Please try again.");
                }
            } catch (Exception e) {
                // Log the exception for debugging
                JOptionPane.showMessageDialog(this, "Error deleting supplier: " + e.getMessage());
                e.printStackTrace();  // Print the error stack trace for debugging
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a supplier to delete.");
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
        categoryComboBox.setSelectedIndex(0); // Reset category to the first item (Mobile)
        priceField.setText("");
        quantityField.setText("");
    }
}




package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.StockDAO;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class StockForm extends JPanel {
    private JTextField productIdField, quantityField;
    private JButton updateStockButton;
    private JTable stockTable;
    private StockDAO stockDAO;

    public StockForm() {
        // Get Singleton instance of StockDAO
        stockDAO = StockDAO.getInstance();

        // Set layout
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Stock Operations"));
        inputPanel.add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        inputPanel.add(productIdField);

        inputPanel.add(new JLabel("Quantity Change:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons for stock operations
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        updateStockButton = new JButton("Update Stock");
        buttonPanel.add(updateStockButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Table to display stock levels
        stockTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(stockTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Stock Levels"));
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        updateStockButton.addActionListener(e -> updateStock());

        // Automatically view stock levels when the form is loaded
        viewStockLevels();
    }

    // Update stock in database
    private void updateStock() {
        try {
            int productId = Integer.parseInt(productIdField.getText());
            int quantityChange = Integer.parseInt(quantityField.getText());

            if (stockDAO.updateStock(productId, quantityChange)) {
                JOptionPane.showMessageDialog(this, "Stock updated successfully!");
                clearFields();
                viewStockLevels(); // Refresh the stock view automatically
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update stock.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // View all stock levels in the database
    private void viewStockLevels() {
        ResultSet rs = stockDAO.getStockLevels();
        if (rs != null) {
            stockTable.setModel(DbUtils.resultSetToTableModel(rs)); // Populate table with data
        } else {
            JOptionPane.showMessageDialog(this, "No stock to display!");
        }
    }

    // Clear input fields
    private void clearFields() {
        productIdField.setText("");
        quantityField.setText("");
    }
}



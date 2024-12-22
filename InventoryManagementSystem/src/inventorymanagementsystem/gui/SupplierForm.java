package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.Supplier;
import inventorymanagementsystem.db.SupplierDAO;
import inventorymanagementsystem.db.SupplierPrototype;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class SupplierForm extends JPanel {
    private JTextField nameField, contactField;
    private JComboBox<String> typeComboBox;
    private JButton addButton, updateButton, deleteButton, cloneButton;
    private SupplierDAO supplierDAO;
    private JTable supplierTable;
    private SupplierPrototype supplierPrototype;

    public SupplierForm() {
        // Initialize SupplierDAO
        supplierDAO = new SupplierDAO();

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Contact Info:"));
        contactField = new JTextField();
        inputPanel.add(contactField);

        inputPanel.add(new JLabel("Supplier Type:"));
        typeComboBox = new JComboBox<>(new String[] {"Local", "International"});
        inputPanel.add(typeComboBox);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Supplier");
        updateButton = new JButton("Update Supplier");
        deleteButton = new JButton("Delete Supplier");
        cloneButton = new JButton("Clone Supplier");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(cloneButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Initialize supplierTable and add to JScrollPane
        supplierTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        addButton.addActionListener(e -> addSupplier());
        updateButton.addActionListener(e -> updateSupplier());
        deleteButton.addActionListener(e -> deleteSupplier());
        cloneButton.addActionListener(e -> cloneSupplier());

        // Initial loading of suppliers into the table
        viewSuppliers();
    }

    // Add supplier to the database using SupplierFactory
    private void addSupplier() {
        // Validate empty fields
        if (nameField.getText().isEmpty() || contactField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        String name = nameField.getText();
        String contactInfo = contactField.getText();
        String type = (String) typeComboBox.getSelectedItem();

        // Create a Supplier using the Factory pattern
        Supplier supplier = SupplierFactory.createSupplier(name, contactInfo, type);

        if (supplierDAO.addSupplier(supplier)) {
            JOptionPane.showMessageDialog(this, "Supplier added successfully!");
            clearFields();
            viewSuppliers();  // Refresh the table after adding
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add supplier.");
        }
    }

    // Update supplier in the database
    private void updateSupplier() {
        if (nameField.getText().isEmpty() || contactField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Supplier ID to Update:"));
        String name = nameField.getText();
        String contactInfo = contactField.getText();
        String type = (String) typeComboBox.getSelectedItem();

        Supplier supplier = new Supplier(supplierId, name, type, contactInfo);
        if (supplierDAO.updateSupplier(supplier)) {
            JOptionPane.showMessageDialog(this, "Supplier updated successfully!");
            clearFields();
            viewSuppliers();  // Refresh the table after updating
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update supplier.");
        }
    }

// Delete supplier from database
private void deleteSupplier() {
    int selectedRow = supplierTable.getSelectedRow();
    if (selectedRow != -1) {
        int supplierId = (int) supplierTable.getValueAt(selectedRow, 0);

        // Confirm the deletion
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this supplier?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                // Attempt to delete the supplier
                boolean result = supplierDAO.deleteSupplier(supplierId);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
                    viewSuppliers();  // Refresh the table after deleting
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


    // Clone a supplier
    private void cloneSupplier() {
        int selectedRow = supplierTable.getSelectedRow();
        if (selectedRow != -1) {
            int supplierId = (int) supplierTable.getValueAt(selectedRow, 0);
            String name = (String) supplierTable.getValueAt(selectedRow, 1);
            String type = (String) supplierTable.getValueAt(selectedRow, 2);
            String contactInfo = (String) supplierTable.getValueAt(selectedRow, 3);

            // Create a prototype and clone the selected supplier
            Supplier prototype = new Supplier(supplierId, name, type, contactInfo);
            supplierPrototype = new SupplierPrototype(prototype);
            Supplier clonedSupplier = supplierPrototype.cloneSupplier();

            // Add the cloned supplier to the database
            if (supplierDAO.addSupplier(clonedSupplier)) {
                JOptionPane.showMessageDialog(this, "Supplier cloned successfully!");
                viewSuppliers();  // Refresh the table after cloning
            } else {
                JOptionPane.showMessageDialog(this, "Failed to clone supplier.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a supplier to clone.");
        }
    }

    // View suppliers and populate the table
    private void viewSuppliers() {
        ResultSet rs = supplierDAO.getAllSuppliers();
        supplierTable.setModel(DbUtils.resultSetToTableModel(rs)); // Populate table with data
    }

    // Clear input fields
    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
    }
}



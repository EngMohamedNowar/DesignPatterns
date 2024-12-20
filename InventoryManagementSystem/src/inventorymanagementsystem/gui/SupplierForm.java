package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.Supplier;
import inventorymanagementsystem.db.SupplierDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class SupplierForm extends JPanel {
    private JTextField nameField, contactField;
    private JComboBox<String> typeComboBox;
    private JButton addButton, updateButton, deleteButton;
    private SupplierDAO supplierDAO;
    private JTable supplierTable;

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

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Initialize supplierTable and add to JScrollPane
        supplierTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSupplier();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSupplier();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSupplier();
            }
        });

        // Initial loading of suppliers into the table
        viewSuppliers();
    }

    // Add supplier to the database
    private void addSupplier() {
        // Validate empty fields
        if (nameField.getText().isEmpty() || contactField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        String name = nameField.getText();
        String contactInfo = contactField.getText();
        String type = (String) typeComboBox.getSelectedItem();

        Supplier supplier = new Supplier(0, name, type, contactInfo);
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
        int supplierId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Supplier ID to Delete:"));
        if (supplierDAO.deleteSupplier(supplierId)) {
            JOptionPane.showMessageDialog(this, "Supplier deleted successfully!");
            viewSuppliers();  // Refresh the table after deleting
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete supplier.");
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


package inventorymanagementsystem.gui;

import inventorymanagementsystem.db.Report;
import inventorymanagementsystem.db.ReportDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ReportForm extends JPanel {
    private JTextField reportDateField, reportTypeField, descriptionField, totalAmountField;
    private JButton addButton, updateButton, deleteButton;
    private JTable reportTable;
    private ReportDAO reportDAO;

    public ReportForm() {
        // Initialize ReportDAO
        // Initialize ReportDAO (Singleton)
    reportDAO = ReportDAO.getInstance();


        // Set layout
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Report Date (YYYY-MM-DD):"));
        reportDateField = new JTextField();
        inputPanel.add(reportDateField);

        inputPanel.add(new JLabel("Report Type:"));
        reportTypeField = new JTextField();
        inputPanel.add(reportTypeField);

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Total Amount:"));
        totalAmountField = new JTextField();
        inputPanel.add(totalAmountField);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons for CRUD operations
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Report");
        updateButton = new JButton("Update Report");
        deleteButton = new JButton("Delete Report");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Table to display reports
        reportTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.SOUTH);

        // Add button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addReport();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateReport();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteReport();
            }
        });

        // Automatically view reports when the form is loaded
        viewReports();
    }

    // Add report to database
private void addReport() {
    String reportDateStr = reportDateField.getText();
    String reportType = reportTypeField.getText();
    String description = descriptionField.getText();
    double totalAmount = Double.parseDouble(totalAmountField.getText());

    // Convert reportDateStr to Date format
    Date reportDate = java.sql.Date.valueOf(reportDateStr);

    // Use the builder pattern to create a report
    Report report = new Report.ReportBuilder()
                    .setReportId(0)  // reportId is 0 for new reports
                    .setReportDate(reportDate)
                    .setReportType(reportType)
                    .setDescription(description)
                    .setTotalAmount(totalAmount)
                    .build();

    if (reportDAO.addReport(report)) {
        JOptionPane.showMessageDialog(this, "Report added successfully!");
        clearFields();
        viewReports(); // Refresh the report view automatically
    } else {
        JOptionPane.showMessageDialog(this, "Failed to add report.");
    }
}

private void updateReport() {
    // Prompt user for report ID to update
    int reportId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Report ID to Update:"));
    String reportDateStr = reportDateField.getText();
    String reportType = reportTypeField.getText();
    String description = descriptionField.getText();
    double totalAmount = Double.parseDouble(totalAmountField.getText());

    // Convert reportDateStr to Date format
    Date reportDate = java.sql.Date.valueOf(reportDateStr);

    // Use the builder pattern to update the report
    Report report = new Report.ReportBuilder()
                    .setReportId(reportId) // Use the provided reportId for updating
                    .setReportDate(reportDate)
                    .setReportType(reportType)
                    .setDescription(description)
                    .setTotalAmount(totalAmount)
                    .build();

    if (reportDAO.updateReport(report)) {
        JOptionPane.showMessageDialog(this, "Report updated successfully!");
        clearFields();
        viewReports(); // Refresh the report view automatically
    } else {
        JOptionPane.showMessageDialog(this, "Failed to update report.");
    }
}


    // Delete report from database
    private void deleteReport() {
        int reportId = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Report ID to Delete:"));
        if (reportDAO.deleteReport(reportId)) {
            JOptionPane.showMessageDialog(this, "Report deleted successfully!");
            viewReports(); // Refresh the report view automatically
        } else {
            JOptionPane.showMessageDialog(this, "Failed to delete report.");
        }
    }

    // View all reports in the database
    private void viewReports() {
        ResultSet rs = reportDAO.getAllReports();
        if (rs != null) {
            reportTable.setModel(DbUtils.resultSetToTableModel(rs)); // Populate table with data
        } else {
            JOptionPane.showMessageDialog(this, "No reports to display!");
        }
    }

    // Clear input fields
    private void clearFields() {
        reportDateField.setText("");
        reportTypeField.setText("");
        descriptionField.setText("");
        totalAmountField.setText("");
    }
}

package inventorymanagementsystem;

import inventorymanagementsystem.gui.ProductForm;
import inventorymanagementsystem.gui.SupplierForm;
import inventorymanagementsystem.gui.OrderForm;
import inventorymanagementsystem.gui.StockForm;
import inventorymanagementsystem.gui.ReportForm; // Import ReportForm

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private JPanel leftPanel, cardPanel;
    private JButton homeButton, productButton, supplierButton, ordersButton, stockButton, reportButton; // Add reportButton
    private CardLayout cardLayout;

    public MainForm() {
        // Set up the main frame
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel with buttons
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        homeButton = new JButton("Home");
        productButton = new JButton("Product");
        supplierButton = new JButton("Supplier");
        ordersButton = new JButton("Orders");
        stockButton = new JButton("Stock");
        reportButton = new JButton("Reports"); // Initialize the report button

        leftPanel.add(homeButton);
        leftPanel.add(productButton);
        leftPanel.add(supplierButton);
        leftPanel.add(ordersButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton); // Add the report button to the left panel

        // Right panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels for each section
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Welcome to Inventory Management System"));

        ProductForm productPanel = new ProductForm(); // ProductForm for managing products
        SupplierForm supplierPanel = new SupplierForm(); // SupplierForm for managing suppliers
        OrderForm ordersPanel = new OrderForm(); // OrderForm for managing orders
        StockForm stockPanel = new StockForm(); // StockForm for managing stock
        ReportForm reportPanel = new ReportForm(); // ReportForm for managing reports

        // Add all panels to the card panel
        cardPanel.add(homePanel, "Home");
        cardPanel.add(productPanel, "Product");
        cardPanel.add(supplierPanel, "Supplier");
        cardPanel.add(ordersPanel, "Orders");
        cardPanel.add(stockPanel, "Stock");
        cardPanel.add(reportPanel, "Reports"); // Add ReportForm to the card panel

        // Add panels to the main frame
        add(leftPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        // Button actions to switch between panels
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "Home"));
        productButton.addActionListener(e -> cardLayout.show(cardPanel, "Product"));
        supplierButton.addActionListener(e -> cardLayout.show(cardPanel, "Supplier"));
        ordersButton.addActionListener(e -> cardLayout.show(cardPanel, "Orders"));
        stockButton.addActionListener(e -> cardLayout.show(cardPanel, "Stock"));
        reportButton.addActionListener(e -> cardLayout.show(cardPanel, "Reports")); // Switch to the Reports form
    }

    public static void main(String[] args) {
        // Set the look and feel for the GUI (optional, just to make it look nicer)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use SwingUtilities to ensure that the UI is created on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Create and display the MainForm GUI
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true); // Make the window visible
        });
    }
}


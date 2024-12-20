package inventorymanagementsystem;

import inventorymanagementsystem.gui.ProductForm;
import inventorymanagementsystem.gui.SupplierForm;
import inventorymanagementsystem.gui.OrderForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm extends JFrame {
    private JPanel leftPanel, rightPanel, cardPanel;
    private JButton homeButton, productButton, supplierButton, ordersButton, reportsButton;
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
        

        leftPanel.add(homeButton);
        leftPanel.add(productButton);
        leftPanel.add(supplierButton);
        leftPanel.add(ordersButton);
       

        // Right panel with CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels for each section
        JPanel homePanel = new JPanel();
        homePanel.add(new JLabel("Welcome to Inventory Management System"));

        ProductForm productPanel = new ProductForm(); // ProductForm for managing products
        SupplierForm supplierPanel = new SupplierForm(); // SupplierForm for managing suppliers
        OrderForm ordersPanel = new OrderForm(); // OrderForm for managing orders
        JPanel reportsPanel = new JPanel(); // Create ReportsForm for report generation (can be implemented later)

        // Add all panels to the card panel
        cardPanel.add(homePanel, "Home");
        cardPanel.add(productPanel, "Product");
        cardPanel.add(supplierPanel, "Supplier");
        cardPanel.add(ordersPanel, "Orders");
        cardPanel.add(reportsPanel, "Reports");

        // Add panels to the main frame (corrected, this part was fine)
        add(leftPanel, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        // Button actions to switch between panels
        homeButton.addActionListener(e -> cardLayout.show(cardPanel, "Home"));
        productButton.addActionListener(e -> cardLayout.show(cardPanel, "Product"));
        supplierButton.addActionListener(e -> cardLayout.show(cardPanel, "Supplier"));
        ordersButton.addActionListener(e -> cardLayout.show(cardPanel, "Orders"));
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


import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// InventoryManagementSystemGUI.java: GUI Integration
public class InventoryManagementSystemGUI {
    private final JFrame frame;

    public InventoryManagementSystemGUI() {
        frame = new JFrame("Inventory Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel productLabel = new JLabel("Product Type:");
        JTextField productField = new JTextField();

        JLabel supplierLabel = new JLabel("Supplier Type:");
        JTextField supplierField = new JTextField();

        JLabel orderLabel = new JLabel("Order Details:");
        JTextField orderField = new JTextField();

        JButton createProductButton = new JButton("Create Product");
        JButton createSupplierButton = new JButton("Create Supplier");
        JButton createOrderButton = new JButton("Create Order");

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);

        createProductButton.addActionListener(e -> {
            String productType = productField.getText();
            try {
                Product product = ProductFactory.getProduct(productType);
                product.create();
                outputArea.append("Product created: " + productType + "\n");
            } catch (IllegalArgumentException ex) {
                outputArea.append("Error: " + ex.getMessage() + "\n");
            }
        });

        createSupplierButton.addActionListener(e -> {
            String supplierType = supplierField.getText();
            try {
                Supplier supplier = SupplierFactory.getSupplier(supplierType);
                supplier.supply();
                outputArea.append("Supplier created: " + supplierType + "\n");
            } catch (IllegalArgumentException ex) {
                outputArea.append("Error: " + ex.getMessage() + "\n");
            }
        });

        createOrderButton.addActionListener(e -> {
            String[] orderDetails = orderField.getText().split(",");
            if (orderDetails.length == 3) {
                Order order = new Order.OrderBuilder()
                        .setProduct(orderDetails[0])
                        .setQuantity(Integer.parseInt(orderDetails[1]))
                        .setShipping(orderDetails[2])
                        .build();
                outputArea.append("Order created: " + order + "\n");
            } else {
                outputArea.append("Error: Invalid order format. Use 'product,quantity,shipping'.\n");
            }
        });

        panel.add(productLabel);
        panel.add(productField);
        panel.add(createProductButton);
        panel.add(supplierLabel);
        panel.add(supplierField);
        panel.add(createSupplierButton);
        panel.add(orderLabel);
        panel.add(orderField);
        panel.add(createOrderButton);
        panel.add(new JLabel("Output:"));
        panel.add(new JScrollPane(outputArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManagementSystemGUI::new);
    }
}

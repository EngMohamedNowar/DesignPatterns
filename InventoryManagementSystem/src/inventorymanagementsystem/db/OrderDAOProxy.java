package inventorymanagementsystem.db;
import java.sql.ResultSet;
import javax.swing.JOptionPane; // Importing JOptionPane

public class OrderDAOProxy implements OrderDAOInterface {
    private OrderDAO realOrderDAO;

    public OrderDAOProxy() {
        this.realOrderDAO = new OrderDAO(); // Real OrderDAO instance
    }

    @Override
    public boolean addOrder(Order order) {
        // Add any additional logic before or after the method call
        JOptionPane.showMessageDialog(null, "Proxy: Adding order..."); // Displaying message with JOptionPane
        return realOrderDAO.addOrder(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        // Add any additional logic before or after the method call
        JOptionPane.showMessageDialog(null, "Proxy: Updating order..."); // Displaying message with JOptionPane
        return realOrderDAO.updateOrder(order);
    }

    @Override
    public boolean deleteOrder(int orderId) {
        // Add any additional logic before or after the method call
        JOptionPane.showMessageDialog(null, "Proxy: Deleting order..."); // Displaying message with JOptionPane
        return realOrderDAO.deleteOrder(orderId);
    }

    @Override
    public ResultSet getAllOrders() {
        // If needed, we can add functionality to the getAllOrders method too
        JOptionPane.showMessageDialog(null, "Proxy: Fetching all orders..."); // Displaying message with JOptionPane
        return realOrderDAO.getAllOrders();
    }
}


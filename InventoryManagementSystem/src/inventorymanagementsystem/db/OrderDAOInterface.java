package inventorymanagementsystem.db;
import java.sql.ResultSet;

public interface OrderDAOInterface {
    boolean addOrder(Order order);
    ResultSet getAllOrders();
    boolean updateOrder(Order order);
    boolean deleteOrder(int orderId);
}

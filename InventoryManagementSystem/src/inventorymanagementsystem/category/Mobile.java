
package inventorymanagementsystem.category;



import inventorymanagementsystem.db.Product;


public class Mobile extends Product {
    public Mobile() {
        super(0, "Default Name", "Mobile", 0.0, 0);  // Default values
    }

    public Mobile(int productId, String name, String category, double price, int stockQuantity) {
        super(productId, name, category, price, stockQuantity);
    }
}







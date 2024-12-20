
package inventorymanagementsystem.category;

import inventorymanagementsystem.db.Product;

public class Laptop extends Product {
    public Laptop(int productId, String name, String category, double price, int stockQuantity) {
        super(productId, name, category, price, stockQuantity);
    }
    
}

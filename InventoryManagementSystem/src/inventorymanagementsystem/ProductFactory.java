package inventorymanagementsystem;

import inventorymanagementsystem.category.Laptop;
import inventorymanagementsystem.category.Mobile;
import inventorymanagementsystem.db.Product;

class ProductFactory {
    public static Product getProduct(String type, int productId, String name, double price, int stockQuantity) {
        String category;
        
        switch (type) {
            case "Mobile":
                category = "Mobile";
                return new Mobile(productId, name, category, price, stockQuantity);
            case "Laptop":
                category = "Laptop";
                return new Laptop(productId, name, category, price, stockQuantity);
            default:
                throw new IllegalArgumentException("Unknown product type");
        }
    }
}



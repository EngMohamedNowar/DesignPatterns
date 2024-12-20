// ProductFactory.java: Factory for Products
class ProductFactory {
    public static Product getProduct(String type) {
        switch (type) {
            case "Electronics": return new Electronics();
            case "Furniture": return new Furniture();
            default: throw new IllegalArgumentException("Unknown product type");
        }
    }
} // End of ProductFactory

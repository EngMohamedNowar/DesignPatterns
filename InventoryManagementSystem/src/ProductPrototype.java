// ProductPrototype.java: Prototype Pattern for Cloning
class ProductPrototype implements Cloneable {
    private String name;
    private double price;

    public ProductPrototype(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public ProductPrototype clone() {
        try {
            return (ProductPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported", e);
        }
    }

    @Override
    public String toString() {
        return "ProductPrototype [name=" + name + ", price=" + price + "]";
    }
} // End of ProductPrototype
// SupplierFactory.java: Factory for Suppliers
class SupplierFactory {
    public static Supplier getSupplier(String type) {
        switch (type) {
            case "Local": return new LocalSupplier();
            case "International": return new InternationalSupplier();
            default: throw new IllegalArgumentException("Unknown supplier type");
        }
    }
} // End of SupplierFactory
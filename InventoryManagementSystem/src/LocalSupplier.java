// LocalSupplier.java: Concrete Supplier for Factory Pattern
class LocalSupplier extends Supplier {
    public LocalSupplier() { this.type = "Local"; }
    public void supply() { System.out.println("Supplying locally"); }
} // End of LocalSupplier

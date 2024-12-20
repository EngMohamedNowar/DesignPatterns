// InternationalSupplier.java: Concrete Supplier for Factory Pattern
class InternationalSupplier extends Supplier {
    public InternationalSupplier() { this.type = "International"; }
    public void supply() { System.out.println("Supplying internationally"); }
} // End of InternationalSupplier
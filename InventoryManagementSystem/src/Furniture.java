// Furniture.java: Concrete Product for Factory Pattern
class Furniture extends Product {
    public Furniture() { this.name = "Furniture"; }
    public void create() { System.out.println("Creating Furniture Product"); }
}
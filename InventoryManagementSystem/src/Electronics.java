// Electronics.java: Concrete Product for Factory Pattern
class Electronics extends Product {
    public Electronics() { this.name = "Electronics"; }
    public void create() { System.out.println("Creating Electronics Product"); }
} // End of Electronics
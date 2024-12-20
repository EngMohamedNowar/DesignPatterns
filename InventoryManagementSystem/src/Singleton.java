// Singleton.java: Managing Stock and Reports
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void manageStock(String message) {
        System.out.println("Stock Management: " + message);
    }

    public void generateReport(String reportType) {
        System.out.println("Generating " + reportType + " report...");
    }
} // End of Singleton
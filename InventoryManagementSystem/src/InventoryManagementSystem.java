//// InventoryManagementSystem.java: Main Class
//public class InventoryManagementSystem {
//    public static void main(String[] args) {
//        // Singleton
//        Singleton stockManager = Singleton.getInstance();
//        stockManager.manageStock("Update stock levels");
//        stockManager.generateReport("Sales");
//
//        // Factory
//        Product product = ProductFactory.getProduct("Electronics");
//        product.create();
//
//        Supplier supplier = SupplierFactory.getSupplier("Local");
//        supplier.supply();
//
//        // Builder
//        Order order = new Order.OrderBuilder()
//                            .setProduct("Laptop")
//                            .setQuantity(10)
//                            .setShipping("Express")
//                            .build();
//        System.out.println(order);
//
//        // Prototype
//        ProductPrototype originalProduct = new ProductPrototype("Phone", 699.99);
//        ProductPrototype clonedProduct = originalProduct.clone();
//        System.out.println("Original: " + originalProduct);
//        System.out.println("Cloned: " + clonedProduct);
//
//        // Adapter
//        ExternalInventorySystem externalSystem = new ExternalInventorySystem();
//        ThirdPartyInventory adapter = new InventoryAdapter(externalSystem);
//        adapter.syncInventory("{\"product\": \"Tablet\", \"quantity\": 50}");
//    }
//} // End of InventoryManagementSystem

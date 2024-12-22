
package inventorymanagementsystem.gui;
// SupplierFactory.java

import inventorymanagementsystem.db.Supplier;
import inventorymanagementsystem.supplier.InternationalSupplier;
import inventorymanagementsystem.supplier.LocalSupplier;



public class SupplierFactory {
    
    public static Supplier createSupplier(String name, String contactInfo, String type) {
        // Based on the supplier type, create the appropriate Supplier instance
        if ("Local".equalsIgnoreCase(type)) {
            return new LocalSupplier(name, contactInfo);
        } else if ("International".equalsIgnoreCase(type)) {
            return new InternationalSupplier(name, contactInfo);
        } else {
            throw new IllegalArgumentException("Unknown supplier type: " + type);
        }
    }
}



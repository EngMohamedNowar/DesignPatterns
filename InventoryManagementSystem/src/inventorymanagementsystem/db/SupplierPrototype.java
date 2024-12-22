package inventorymanagementsystem.db;


public class SupplierPrototype {
    private Supplier prototype;

    public SupplierPrototype(Supplier prototype) {
        this.prototype = prototype;
    }

    // Clone method
    public Supplier cloneSupplier() {
        return new Supplier(prototype.getSupplierId(), prototype.getName(), prototype.getType(), prototype.getContactInfo());
    }

    // Set prototype (optional)
    public void setPrototype(Supplier prototype) {
        this.prototype = prototype;
    }

    // Get the current prototype
    public Supplier getPrototype() {
        return prototype;
    }
}


//    public class SupplierPrototype implements Cloneable {

//    private int id;
//    private String name;
//    private String type; // Local or International
//    private String contactInfo;
//
//    // Constructor
//    public SupplierPrototype(int id, String name, String type, String contactInfo) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//        this.contactInfo = contactInfo;
//    }
//
//    // Getters and Setters
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getContactInfo() {
//        return contactInfo;
//    }
//
//    public void setContactInfo(String contactInfo) {
//        this.contactInfo = contactInfo;
//    }
//
//    // Prototype: Clone method
//    @Override
//    public SupplierPrototype clone() {
//        try {
//            return (SupplierPrototype) super.clone();  // Ensure it returns a SupplierPrototype
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "SupplierPrototype{" +
//               "id=" + id +
//               ", name='" + name + '\'' +
//               ", type='" + type + '\'' +
//               ", contactInfo='" + contactInfo + '\'' +
//               '}';
//    }


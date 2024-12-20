/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem.db;
public class Supplier {
    private int supplierId;
    private String name;
    private String type;
    private String contactInfo;

    // Constructor, getters, and setters
    public Supplier(int supplierId, String name, String type, String contactInfo) {
        this.supplierId = supplierId;
        this.name = name;
        this.type = type;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}


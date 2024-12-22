/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem.supplier;

import inventorymanagementsystem.db.Supplier;



public class LocalSupplier extends Supplier {

    public LocalSupplier(String name, String contactInfo) {
        super(0, name, "Local", contactInfo); // Set default ID as 0 (for example) and type as Local
    }
}

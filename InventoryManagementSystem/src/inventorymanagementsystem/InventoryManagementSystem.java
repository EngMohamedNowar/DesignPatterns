/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

import inventorymanagementsystem.db.Product;

import inventorymanagementsystem.gui.ProductForm;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class InventoryManagementSystem {

   public static void main(String[] args) {
       
//--------------------------------------------Factory----------------------------------

// Creating a Mobile product using ProductFactory
Product mobile = ProductFactory.getProduct("Mobile", 1, "iPhone 13", 999.99, 50);

// Creating a Laptop product using ProductFactory
Product laptop = ProductFactory.getProduct("Laptop", 2, "MacBook Pro", 1999.99, 30);

// Displaying product details for mobile
System.out.println("Mobile Product Details:");
System.out.println("ID: " + mobile.getProductId());
System.out.println("Name: " + mobile.getName());
System.out.println("Category: " + mobile.getCategory());
System.out.println("Price: $" + mobile.getPrice());
System.out.println("Stock Quantity: " + mobile.getStockQuantity());

System.out.println();

// Displaying product details for laptop
System.out.println("Laptop Product Details:");
System.out.println("ID: " + laptop.getProductId());
System.out.println("Name: " + laptop.getName());
System.out.println("Category: " + laptop.getCategory());
System.out.println("Price: $" + laptop.getPrice());
System.out.println("Stock Quantity: " + laptop.getStockQuantity());
//---------------------------------------Prototype-----------------------------------------

// Create an original supplier
SupplierPrototype originalSupplier = new SupplierPrototype(1, "ABC Supplies", "Local", "123-456-7890");

// Clone the supplier
SupplierPrototype clonedSupplier = originalSupplier.clone(); // Ensure the correct type

// Modify the clone
clonedSupplier.setId(2);
clonedSupplier.setName("XYZ Supplies");

// Display both suppliers
System.out.println("Original Supplier: " + originalSupplier);
System.out.println("Cloned Supplier: " + clonedSupplier);


//----------------------------------------GUI-------------------------------------------
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    // Use SwingUtilities to ensure that the UI is created on the Event Dispatch Thread (EDT)
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            // Create and display the ProductForm GUI
            MainForm MainForm = new MainForm();
            MainForm.setVisible(true); // Make the window visible
            
        }
    });
}

}

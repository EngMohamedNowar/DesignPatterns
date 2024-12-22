/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagementsystem;

//import inventorymanagementsystem.gui.ProductFactory;
import inventorymanagementsystem.db.SupplierPrototype;
import inventorymanagementsystem.db.Product;

import inventorymanagementsystem.gui.ProductForm;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class InventoryManagementSystem {

   public static void main(String[] args) {

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

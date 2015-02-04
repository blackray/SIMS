/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sims;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author amalcs
 */
public class PurchaseOrderData {
    private SimpleStringProperty Product = new SimpleStringProperty("");
    private SimpleStringProperty Quantity = new SimpleStringProperty("");
    
    public PurchaseOrderData(){
        this("","");
    }
    public PurchaseOrderData(String Product,String Quantity){
        setProduct(Product);
        setQuantity(Quantity);
    }
    
    public void setProduct(String product){
        Product.set(product);
    }
    public String getProduct(){
        return Product.get();
    }
    
    public void setQuantity(String quantity){
        Quantity.set(quantity);
    }
    public String getQuantity(){
        return Quantity.get();
    }
}


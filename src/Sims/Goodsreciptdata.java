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
public class Goodsreciptdata {
    private SimpleStringProperty Product = new SimpleStringProperty("");
    private SimpleStringProperty B_Rate = new SimpleStringProperty("");
    private SimpleStringProperty MRP = new SimpleStringProperty("");
    private SimpleStringProperty Batch = new SimpleStringProperty("");
    private SimpleStringProperty Expiry = new SimpleStringProperty("");
    private SimpleStringProperty Qty = new SimpleStringProperty("");
    private SimpleStringProperty Free = new SimpleStringProperty("");
    
    public Goodsreciptdata(String pro,String Brate,String mrp,
            String batch,String expiry,String Qty,String Free){
        setProduct(pro);
        setB_Rate(Brate);
        setMRP(mrp);
        setBatch(batch);
        setExpiry(expiry);
        setQty(Qty);
        setFree(Free);
    }

    public void setProduct(String product) {
        this.Product.set(product);
    }

    public void setB_Rate(String B_Rate) {
        this.B_Rate.set(B_Rate);
    }

   

    public void setMRP(String MRP) {
        this.MRP.set(MRP);
    }

    public void setBatch(String Batch) {
        this.Batch.set(Batch);
    }

    public void setExpiry(String Expiry) {
        this.Expiry.set(Expiry);
    }

    public void setQty(String Qty) {
        this.Qty.set(Qty);
    }

    public void setFree(String Free) {
        this.Free.set(Free);
    }

    public String getProduct() {
        return Product.get();
    }

    public String getB_Rate() {
        return B_Rate.get();
    }

   

    public String getMRP() {
        return MRP.get();
    }

    public String getBatch() {
        return Batch.get();
    }

    public String getExpiry() {
        return Expiry.get();
    }

    public String getQty() {
        return Qty.get();
    }

    public String getFree() {
        return Free.get();
    }
    
    public Goodsreciptdata(){
        
    }
}
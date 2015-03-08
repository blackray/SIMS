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
public class Stockdata {
    private SimpleStringProperty Productname = new SimpleStringProperty("");
    private SimpleStringProperty Batch = new SimpleStringProperty("");
    private SimpleStringProperty Quantity = new SimpleStringProperty("");

    public String getProductname() {
        return Productname.get();
    }

    public void setProductname(String Productname) {
        this.Productname.set(Productname);
    }

    public String getBatch() {
        return Batch.get();
    }

    public void setBatch(String Batch) {
        this.Batch.set(Batch);
    }

    public String getQuantity() {
        return Quantity.get();
    }

    public void setQuantity(String Quantity) {
        this.Quantity.set(Quantity);
    }

    public Stockdata() {
        this("","","");
    }
    public Stockdata(String p,String b,String q){
        setProductname(p);
        setBatch(b);
        setQuantity(q);
    }
    
}

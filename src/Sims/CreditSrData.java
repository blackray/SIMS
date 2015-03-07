/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author jesuit
 */
public class CreditSrData {
     private SimpleStringProperty Product = new SimpleStringProperty("");
    private SimpleStringProperty Qty = new SimpleStringProperty("");
    private SimpleStringProperty Batch =new SimpleStringProperty("");
    private SimpleStringProperty Expdat =new SimpleStringProperty("");
    private SimpleStringProperty Free =new SimpleStringProperty("");
    private SimpleStringProperty Billed =new SimpleStringProperty("");
    private SimpleStringProperty Ptr =new SimpleStringProperty("");
    private SimpleStringProperty Pts =new SimpleStringProperty("");
    private SimpleStringProperty Mrp =new SimpleStringProperty("");
    private SimpleStringProperty Tax =new SimpleStringProperty("");
    private SimpleStringProperty Taxamt =new SimpleStringProperty("");
    private SimpleStringProperty Pdvalue =new SimpleStringProperty("");
    private SimpleStringProperty Mrpvalue =new SimpleStringProperty("");
    public CreditSrData(){
        this("","","","","","","","","","","","");
    }
    public CreditSrData(String product,String batch,String expdat,
            String free,String billed,String ptr,String pts,
            String mrp,String tax,String taxamt,String pdvalue,String mrpvalue ){
        setProduct(product);
        setBatch(batch);
        setExpdat(expdat);
        setFree(free);
        setBilled(billed);
        setPtr(ptr);
        setPts(pts);
        setMrp(mrp);
        setTax(tax);
        setTaxamt(taxamt);
        setPdvalue(pdvalue);
        setMrpvalue(mrpvalue);
    }
    
    public void setProduct(String product){
        Product.set(product);
    }
    public String getProduct(){
        return Product.get();
    }
    
    public void setBatch(String batch){
        Batch.set(batch);
    }
    public String getBatch(){
        return Batch.get();
    }
    public void setExpdat(String expdat){
        Expdat.set(expdat);
    }
    public String getExpdat(){
        return Expdat.get();
    }
     public void setFree(String free){
        Free.set(free);
    }
    public String getFree(){
        return Free.get();
    }
     public void setBilled(String billed){
        Billed.set(billed);
    }
    public String getBilled(){
        return Billed.get();
    }
     public void setPtr(String ptr){
        Ptr.set(ptr);
    }
    public String getPtr(){
        return Ptr.get();
    }
     public void setPts(String pts){
        Pts.set(pts);
    }
    public String getPts(){
        return Pts.get();
    }
     public void setMrp(String mrp){
        Mrp.set(mrp);
    }
    public String getMrp(){
        return Mrp.get();
    }
     public void setTax(String tax){
        Tax.set(tax);
    }
    public String getTax(){
        return Tax.get();
    }
    public void setTaxamt(String taxamt){
        Taxamt.set(taxamt);
    }
    public String getTaxamt(){
        return Taxamt.get();
    }
    public void setPdvalue(String pdvalue){
        Pdvalue.set(pdvalue);
    }
    public String getPdvalue(){
        return Pdvalue.get();
    }
     public void setMrpvalue(String mrpvalue){
        Mrpvalue.set(mrpvalue);
    }
    public String getMrpvalue(){
        return Mrpvalue.get();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

/**
 *
 * @author jesuit
 */
public class CreditSrProduct {
     private String Name;
    private String Batch ;
    private String Expdat ;
    private String Free ;
    private String Billed ;
    private String Ptr;
    private String Pts;
    private String Mrp;
    private String Tax;
    private String Taxamt;
    private String Pdvalue;
    private String Mrpvalue;
    
    public CreditSrProduct(){
        
    }
    

    public CreditSrProduct(String Name, String Batch, String Expdat, String Free, String Billed, String Ptr, String Pts, String Mrp, String Tax, String Taxamt, String Pdvalue, String Mrpvalue) {
        this.Name = Name;
        this.Batch = Batch;
        this.Expdat = Expdat;
        this.Free = Free;
        this.Billed = Billed;
        this.Ptr = Ptr;
        this.Pts = Pts;
        this.Mrp = Mrp;
        this.Tax = Tax;
        this.Taxamt = Taxamt;
        this.Pdvalue = Pdvalue;
        this.Mrpvalue = Mrpvalue;
    }
    

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBatch() {
        return Batch;
    }

    public void setBatch(String Batch) {
        this.Batch = Batch;
    }

    public String getExpdat() {
        return Expdat;
    }

    public void setExpdat(String Expdat) {
        this.Expdat = Expdat;
    }

    public String getFree() {
        return Free;
    }

    public void setFree(String Free) {
        this.Free = Free;
    }

    public String getBilled() {
        return Billed;
    }

    public void setBilled(String Billed) {
        this.Billed = Billed;
    }

    public String getPtr() {
        return Ptr;
    }

    public void setPtr(String Ptr) {
        this.Ptr = Ptr;
    }

    public String getPts() {
        return Pts;
    }

    public void setPts(String Pts) {
        this.Pts = Pts;
    }

    public String getMrp() {
        return Mrp;
    }

    public void setMrp(String Mrp) {
        this.Mrp = Mrp;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String Tax) {
        this.Tax = Tax;
    }

    public String getTaxamt() {
        return Taxamt;
    }

    public void setTaxamt(String Taxamt) {
        this.Taxamt = Taxamt;
    }

    public String getPdvalue() {
        return Pdvalue;
    }

    public void setPdvalue(String Pdvalue) {
        this.Pdvalue = Pdvalue;
    }

    public String getMrpvalue() {
        return Mrpvalue;
    }

    public void setMrpvalue(String Mrpvalue) {
        this.Mrpvalue = Mrpvalue;
    }
}

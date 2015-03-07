/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesuit
 */
public class CreditSprodcutcalc {
     public CreditSrProduct calculate(String pname,String batch,double qty,double free){
        CreditSrProduct pd = new CreditSrProduct(pname, batch, pname, pname, pname, batch, batch, batch, batch, pname, pname, pname);
        String stmnt = "SELECT MRP,Expiry FROM STOCK WHERE Batch=" + batch + " AND Product='"+pname+"'";
        ResultSet QB = Database.Query(stmnt);
        Date d = null;
        Double Mrp=0.0;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            while(QB.next()){
                Mrp = QB.getDouble("MRP");
                d = QB.getDate("Expiry");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Double temp = ((Mrp / 105) * 100);
        Double Ptr = (temp - ((temp * 20) / 100));
        Ptr=Math.round(100*Ptr)/100d;
        Double Pts = (Ptr - ((Ptr * 10) / 100));
        Pts=Math.round(100*Pts)/100d;
        Double Mrpvalue=qty*Mrp;
        Double Pdvalue=qty*Pts;
        Pdvalue=Math.round(100*Pdvalue)/100d;
        pd.setPtr(Double.toString(Ptr));
        pd.setPts(Double.toString(Pts));
        pd.setTax("5%");
        double taxamt = qty*((Mrp * 4.76) / 100);
        taxamt=Math.round(100*taxamt)/100d;
        pd.setTaxamt(Double.toString(taxamt));
        pd.setPdvalue(Double.toString(Pdvalue));
        pd.setMrpvalue(Double.toString(Mrpvalue));
        pd.setMrp(Mrp+"");
        pd.setExpdat(df.format(d));
        pd.setBilled(qty+"");
        pd.setFree(free+"");
        pd.setName(pname);
        pd.setBatch(batch);
        return pd;
    }
}

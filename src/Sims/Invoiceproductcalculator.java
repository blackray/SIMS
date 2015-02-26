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
 * @author amalcs
 */
public class Invoiceproductcalculator {
    public InvoiceProductdata calculate(String pname,String batch,double qty,double free){
        InvoiceProductdata pd = new InvoiceProductdata();
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
        Double Pts = (Ptr - ((Ptr * 10) / 100));
        pd.setPtr(Double.toString(Ptr));
        pd.setPts(Double.toString(Pts));
        pd.setTax("5%");
        double taxamt = ((Mrp * 4.76) / 100);
        pd.setTaxamt(Double.toString(taxamt));
        pd.setPdvalue("");
        pd.setMrpvalue("");
        pd.setMrp(Mrp+"");
        pd.setExpdat(df.format(d));
        pd.setBilled(qty+"");
        pd.setFree(free+"");
        pd.setName(pname);
        pd.setBatch(batch);
        return pd;
    }
    
}

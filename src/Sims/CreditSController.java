/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import Sims.com.jasper.jaspercontroller;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author jesuit
 */
public class CreditSController implements Initializable{
    
    Double tpdvalue;
    Double tmvalue;

    @FXML
    private TableColumn productcell;
    @FXML
    private ComboBox<String> customer;
    @FXML
    private Text address;
    @FXML
    private Label fxinvoiceno;
    @FXML
    private TextField qty;
    @FXML
    private TextField free;
    @FXML
    private ComboBox<String> product;
    @FXML
    private ComboBox<String> batch;
    @FXML
    Label fxdate;
    @FXML Label Tpvalue;
    @FXML Label Tmvalue;
    @FXML
    Label fxtin;
    @FXML
    TableView<CreditSrData> table;
    String expdate, PTR, PTS, mrp, Tax, Taxamt, Pdvalue, Mrpvalue, Rate;

    @FXML
    public void CustomerAction(ActionEvent ev) {
        try {
            String stmnt = "SELECT Address,Tin FROM CUSTOMER WHERE Name='" + customer.getValue() + "'";
            ResultSet Query = Database.Query(stmnt);
            if (Query.next()) {
                address.setText(Query.getString("Address"));
                fxtin.setText(Query.getString("Tin"));
                
            } else {
                System.out.println("No Product Found");
            }
            //stmnt = "SELECT Expiry FROM STOCK WHERE Batch='" + batch.getValue() + "'";
            //ResultSet Q = Database.Query(stmnt);
            //expdate = Q.getString("Expiry");
            //calculation();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    public void Productcomboboxaction(ActionEvent ev){
        try {
            String stmnt;
            stmnt = "SELECT Batch FROM STOCK WHERE Product='" + product.getValue() + "'";
            ResultSet q = Database.Query(stmnt);
            ObservableList<String> prod = FXCollections.observableArrayList();
            String pname;
            while (q.next()) {
                pname = q.getString("Batch");
                System.out.println(pname);
                prod.add(pname);
            }
            batch.setItems(prod);
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void calculation(){
        CreditSprodcutcalc ipc = new CreditSprodcutcalc();
        String pname = product.getValue();
        String bat = batch.getValue();
        Double Qty = Double.parseDouble(qty.getText());
        Double Free= Double.parseDouble(free.getText());
        CreditSrProduct pd = ipc.calculate(product.getValue(),batch.getValue(), Qty,Free);
        ObservableList<CreditSrData> data = table.getItems();
        data.add(new CreditSrData(pd.getName(), pd.getBatch(),
                pd.getExpdat(),pd.getFree(),pd.getBilled(),pd.getPtr(),pd.getPts(),
                pd.getMrp(),pd.getTax(),pd.getTaxamt(),pd.getPdvalue(),pd.getMrpvalue()));
        
        tpdvalue=(tpdvalue+(Double.parseDouble(pd.getPdvalue())));
        tpdvalue=Math.round(100*tpdvalue)/100d;
        Tpvalue.setText(tpdvalue+"");
        tmvalue=(tmvalue+(Double.parseDouble(pd.getMrpvalue())));
        tmvalue=Math.round(100*tmvalue)/100d;
        Tmvalue.setText(tmvalue+"");
        
        //Update Stock
        String stmnt = "SELECT Quantity,Free FROM STOCK WHERE Product='"+pname+"' AND Batch="+bat;
        ResultSet Query = Database.Query(stmnt);
        try {
            if(Query.next()){
                int newval =(int) (Query.getInt("Quantity")+Qty);
                int newfree = (int)(Query.getInt("Free")+Free);
                String updstmnt = "UPDATE STOCK SET Quantity="+newval+",Free="+newfree+" WHERE Product='" + pname + "' AND Batch=" + bat;
                Database.Update(updstmnt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CreditSController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void SubmitAction(ActionEvent ev) {
        ObservableList<CreditSrData> data = table.getItems();
        data.add(new CreditSrData(product.getValue(), batch.getValue(), expdate, free.getText(), qty.getText(), PTR, PTS, mrp, Tax, Taxamt, Pdvalue, Mrpvalue));
        
    }
    @FXML 
    public void productcellcommit(ActionEvent ev){
        System.out.println("dfghj");
    }
    @FXML
    public void Print(ActionEvent ev){
        jaspercontroller jc = new jaspercontroller();
        String[] ColumnNames={"SL","ITEM","MRP"};
        ObservableList<CreditSrData> id = table.getItems();
        int i =0;
        int size = id.size();
        String[][] Data = new String[size][3];
        for(CreditSrData idata : id){
            Data[i][0]=(i+1)+"";
            Data[i][1]=idata.getProduct();
            Data[i][2]=idata.getMrp();
            i++;
        }
        jc.printInvoice(ColumnNames, Data);
  
    }
@Override
    public void initialize(URL location, ResourceBundle resources) {
        tpdvalue =0.0;
        tmvalue=0.0;
        table.setEditable(true);
        productcell.setEditable(true);
        DateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fxdate.setText(d.format(date));
        try {
            String stmnt = "SELECT MAX(CRNO) FROM CREDITNOTESR";
            PreparedStatement GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            ResultSet executeQuery = GetPreparedStmt.executeQuery();
            int val = 0;
            if (executeQuery.next()) {
                String s = executeQuery.getString(1);
                if (s == null) {
                    val = 0;
                } else {
                    val = Integer.parseInt(s);
                }
                val++;
            }
            fxinvoiceno.setText(val + "");

            stmnt = "SELECT Name FROM CUSTOMER";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while (executeQuery.next()) {
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            customer.setItems(comp);
            stmnt = "SELECT Product FROM STOCK";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> compp = FXCollections.observableArrayList();
            String pname;
            while (executeQuery.next()) {
                pname = executeQuery.getString(1);
                compp.add(pname);
            }
            product.setItems(compp);

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

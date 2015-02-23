/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author jesuit
 */
public class InvoiceController implements Initializable{
@FXML private  ComboBox<String> customer;
@FXML private Text address;
@FXML private Label fxinvoiceno;
@FXML private TextField qty;
@FXML private TextField free;
@FXML private ComboBox<String> product;
@FXML private ComboBox<String> batch;
@FXML
    Label fxdate;
@FXML
    Label fxtin;
@FXML TableView<PurchaseOrderData> table;
   

@FXML
    public void CustomerAction(ActionEvent ev){
        try {
            String stmnt = "SELECT Address FROM CUSTOMER WHERE Name='"+customer.getValue()+"'";
            ResultSet Query = Database.Query(stmnt);
            if(Query.next()){
                address.setText(Query.getString("Address"));
                //fxtin.setText(Query.getString("TIN"));
                stmnt="SELECT Batch FROM STOCK WHERE Product='"+product.getValue()+"'";
                ResultSet q = Database.Query(stmnt);
                ObservableList<String> prod = FXCollections.observableArrayList();
                String pname;
                while(q.next()){
                    pname = q.getString("Batch");
                    System.out.println(pname);
                    prod.add(pname);
                }
                batch.setItems(prod);
            }else{
                System.out.println("No Product Found");
            }
            
        
        } 
        catch (SQLException ex) {
        Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        DateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fxdate.setText(d.format(date));
        try {
            String stmnt = "SELECT MAX(INVOICENO) FROM INVOICE";
            PreparedStatement GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            ResultSet executeQuery = GetPreparedStmt.executeQuery();
            int val=0;
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
            while(executeQuery.next()){
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            customer.setItems(comp);
            stmnt = "SELECT Product FROM STOCK";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> compp = FXCollections.observableArrayList();
            String pname;
            while(executeQuery.next()){
                pname = executeQuery.getString(1);
                compp.add(pname);
            }
            product.setItems(compp);
                
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    

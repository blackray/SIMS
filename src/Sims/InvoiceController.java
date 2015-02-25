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
public class InvoiceController implements Initializable {

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
    @FXML
    Label fxtin;
    @FXML
    TableView<InvoiceData> table;
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

        double Mrp=0, Ptr, Pts, temp, taxamt;
        String stmnt = "SELECT MRP FROM STOCK WHERE Batch=" + batch.getValue();
        ResultSet QB = Database.Query(stmnt);
        try {
            while(QB.next()){
                Mrp = QB.getDouble("MRP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        temp = ((Mrp / 105) * 100);
        Ptr = (temp - ((temp * 20) / 100));
        Pts = (Ptr - ((Ptr * 10) / 100));
        PTR = Double.toString(Ptr);
        PTS = Double.toString(Pts);
        Tax = "5%";
        taxamt = ((Mrp * 4.76) / 100);
        Taxamt = Double.toString(taxamt);
        Pdvalue = "";
        Mrpvalue = "";
        Rate = "";
        mrp = Mrp+"";
        ObservableList<InvoiceData> data = table.getItems();
        data.add(new InvoiceData(product.getValue(), batch.getValue(),
                "", free.getText(),qty.getText(), Rate, PTR, PTR, mrp, Tax, Taxamt, Pdvalue, Mrpvalue));
    }

    @FXML
    public void SubmitAction(ActionEvent ev) {
        ObservableList<InvoiceData> data = table.getItems();
        data.add(new InvoiceData(product.getValue(), batch.getValue(), expdate, free.getText(), qty.getText(), Rate, PTR, PTS, mrp, Tax, Taxamt, Pdvalue, Mrpvalue));

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

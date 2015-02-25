/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author jesuit
 */
public class GoodsReciptController implements Initializable {

    @FXML
    private Label recpno;
    @FXML
    private Label recpdt;
    @FXML
    private Text cmpaddr;
    @FXML
    private Label tin;
    @FXML
    private TextField ordno;
    @FXML
    private DatePicker orddt;
    @FXML
    private TextField product;
    @FXML
    private TextField mrp;
    @FXML
    private TextField batch;
    @FXML
    private DatePicker expdt;
    @FXML
    private TextField free;
    @FXML
    private TextField qty;
    @FXML
    private Label fxtin;
    @FXML
    private TextField brate;
    @FXML
    private Text fxaddress;
    @FXML
    private ComboBox<String> fxcompname;
    @FXML
    private TableView<Goodsreciptdata> table;

    @FXML
    private void add(ActionEvent ev) {
        String proname = product.getText();
        String Recptno = recpno.getText();
        String recdate = recpdt.getText();
        String Mrp = mrp.getText();
        String Batch = batch.getText();
        String Qty = qty.getText();
        String Free = free.getText();
        String Brate = brate.getText();
        LocalDate date = expdt.getValue();
        double dmrp = Double.parseDouble(Mrp);
        double dqty = Integer.parseInt(Qty);
        double dfree = Integer.parseInt(Free);
        double dbrate = Double.parseDouble(Brate);
        

        ObservableList<Goodsreciptdata> data = table.getItems();
        try {
            String stmt = "INSERT INTO STOCK (Product,Batch,MRP,Expiry,Quantity,Free)VALUES(?,?,?,?,?,?)";
            PreparedStatement updatestock = Database.GetPreparedStmt(stmt);
            updatestock.setString(1, proname);
            updatestock.setString(2, Batch);
            updatestock.setDouble(3, dmrp);
            updatestock.setString(4, date.toString());
            updatestock.setDouble(5, dqty);
            updatestock.setDouble(6, dfree);
            int executeUpdate = updatestock.executeUpdate();
            System.out.println(executeUpdate + " Row Changed");
            data.add(new Goodsreciptdata(proname, Brate,"", Mrp, Batch,date.toString(), Qty, Free));
        } catch (SQLException ex) {
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
        }
    }
    
    @FXML
    public void CompanyComboboxaction(ActionEvent ev){
          try {
            String stmnt = "SELECT Address,TIN FROM COMPANY WHERE Name='"+fxcompname.getValue()+"'";
            ResultSet Query = Database.Query(stmnt);
            if(Query.next()){
                fxaddress.setText(Query.getString("Address"));
                fxtin.setText(Query.getString("TIN"));
                stmnt="SELECT Name FROM PRODUCT WHERE Company='"+fxcompname.getValue()+"'";
                ResultSet q = Database.Query(stmnt);
                ObservableList<String> prod = FXCollections.observableArrayList();
                String pname;
                while(q.next()){
                    pname = q.getString("Name");
                    System.out.println(pname);
                    prod.add(pname);
                }
            }else{
                System.out.println("No Company Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String stmnt = "SELECT MAX(Reciptno) from GOODSRECIPTPRODUCT";
            PreparedStatement Query = Database.GetPreparedStmt(stmnt);
            ResultSet executeQuery = Query.executeQuery();
            while(executeQuery.next()){
                String s = executeQuery.getString(1);
                int val;
                if(s!=null)
                 val= Integer.parseInt(s);
                else
                    val=0;
                val++;
                recpno.setText(val+"");
            }
            DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            Date d= new Date();
            recpdt.setText(dateformat.format(d));
            
            
             stmnt = "SELECT Name FROM COMPANY";
            Query = Database.GetPreparedStmt(stmnt);
            executeQuery = Query.executeQuery();
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while(executeQuery.next()){
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            fxcompname.setItems(comp);
        } catch (SQLException ex) {
           System.out.println("Execption in Goodsrecipt Initialize");
           // Logger.getLogger(GoodsReciptController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

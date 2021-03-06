/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import static Sims.Database.GetPreparedStmt;
import Sims.com.Messagebox.Messagebox;
import Sims.com.jasper.jaspercontroller;
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
    private ComboBox<String> product;
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
        String proname = product.getValue();
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
        
        for(Goodsreciptdata d : data){
            if(d.getProduct().equals(proname) && d.getBatch().equals(Batch))
                return;
        }

        data.add(new Goodsreciptdata(proname, Brate, Mrp, Batch, date.toString(), Qty, Free));

    }

    @FXML
    public void Submitaction(ActionEvent ev) {
        ObservableList<Goodsreciptdata> data = table.getItems();

        String stmt = null;

        //Update Goodsrecipt
        try {
            stmt = "INSERT INTO GOODSRECIPT (Company,Orderno,Reciptno,Reciptdate) VALUES(?,?,?,?)";
            Date d = new Date();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement updatestmnt = Database.GetPreparedStmt(stmt);
            updatestmnt.setString(1, fxcompname.getValue());
            updatestmnt.setString(2, ordno.getText());
            updatestmnt.setString(3, recpno.getText());
            updatestmnt.setString(4, df.format(d));
            int executeUpdate = updatestmnt.executeUpdate();
            System.out.println(executeUpdate + " Row Changed");
        } catch (SQLException ex) {

            Messagebox.getInstance().message("Error", ex.getMessage());
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
            return;
        }

        //Update Goods Recipt Product
        stmt = "INSERT INTO GOODSRECIPTPRODUCT (Product,Reciptno,Batch,Quantity,Free,MRP,BRATE,Expiry) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement updatestock = Database.GetPreparedStmt(stmt);
            for (Goodsreciptdata d : data) {
                updatestock.setString(1, d.getProduct());
                updatestock.setString(2, recpno.getText());
                updatestock.setString(3, d.getBatch());
                updatestock.setString(4, d.getQty());
                updatestock.setString(5, d.getFree());
                updatestock.setString(6, d.getMRP());
                updatestock.setString(7, d.getB_Rate());
                updatestock.setString(8, d.getExpiry());

                int executeUpdate = updatestock.executeUpdate();
                System.out.println(executeUpdate + " Row Changed");
            }
        } catch (SQLException ex) {

            Messagebox.getInstance().message("Error", ex.getMessage());
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
            return;
        }
        
        stmt = "INSERT INTO STOCK (Product,Batch,MRP,Brate,Expiry,Quantity,Free)VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement updatestock = Database.GetPreparedStmt(stmt);
            for (Goodsreciptdata d : data) {
                updatestock.setString(1, d.getProduct());
                updatestock.setString(2, d.getBatch());
                updatestock.setString(3, d.getMRP());
                updatestock.setString(4, d.getB_Rate());
                updatestock.setString(5, d.getExpiry());
                updatestock.setString(6, d.getQty());
                updatestock.setString(7, d.getFree());

                int executeUpdate = updatestock.executeUpdate();
                System.out.println(executeUpdate + " Row Changed");
            }
        } catch (SQLException ex) {

            Messagebox.getInstance().message("Error", ex.getMessage());
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
        }
        jaspercontroller jc = new jaspercontroller();
        jc.printGoodsreceipt();
        
    }

    @FXML
    public void CompanyComboboxaction(ActionEvent ev) {
        try {
            String stmnt = "SELECT Address,TIN FROM COMPANY WHERE Name='" + fxcompname.getValue() + "'";
            ResultSet Query = Database.Query(stmnt);
            if (Query.next()) {
                fxaddress.setText(Query.getString("Address"));
                fxtin.setText(Query.getString("TIN"));
                stmnt = "SELECT Name FROM PRODUCT WHERE Company='" + fxcompname.getValue() + "'";
                ResultSet q = Database.Query(stmnt);
                ObservableList<String> prod = FXCollections.observableArrayList();
                String pname;
                while (q.next()) {
                    pname = q.getString(1);
                    System.out.println(pname);
                    prod.add(pname);
                }
                product.setItems(prod);
            } else {
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
            while (executeQuery.next()) {
                String s = executeQuery.getString(1);
                int val;
                if (s != null) {
                    val = Integer.parseInt(s);
                } else {
                    val = 0;
                }
                val++;
                recpno.setText(val + "");
            }
            DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            Date d = new Date();
            recpdt.setText(dateformat.format(d));

            stmnt = "SELECT Name FROM COMPANY";
            Query = Database.GetPreparedStmt(stmnt);
            executeQuery = Query.executeQuery();
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while (executeQuery.next()) {
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            fxcompname.setItems(comp);
            stmnt = "SELECT Name FROM PRODUCT WHERE Company='" + fxcompname.getValue() + "'";
            ResultSet q = Database.Query(stmnt);
            ObservableList<String> prod = FXCollections.observableArrayList();
            String pname;
            while (q.next()) {
                pname = q.getString("Name");
                System.out.println(pname);
                prod.add(pname);
            }
            product.setItems(prod);
        } catch (SQLException ex) {
            System.out.println("Execption in Goodsrecipt Initialize");
            // Logger.getLogger(GoodsReciptController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

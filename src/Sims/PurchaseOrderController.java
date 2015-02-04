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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import np.com.ngopal.control.AutoFillTextBox;

/**
 *
 * @author amalcs
 */
public class PurchaseOrderController implements Initializable {

    @FXML
    Text fxaddress;
    @FXML
    Label fxdate;
    @FXML
    Label fxorderno;
    @FXML
    Label fxtin;
    @FXML
    ComboBox<String> fxcompname;
    @FXML
    ComboBox<String> fxprodname;
    @FXML TableView<PurchaseOrderData> table;
    @FXML TextField fxquantity;
    
    
    @FXML
    public void CompanyAction(ActionEvent ev){
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
                fxprodname.setItems(prod);
            }else{
                System.out.println("No Company Found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void SubmitAction(ActionEvent ev){
        ObservableList<PurchaseOrderData> data=table.getItems();
        data.add(new PurchaseOrderData(fxprodname.getValue(),fxquantity.getText() ));
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fxdate.setText(d.format(date));
        try {
            String stmnt = "SELECT MAX(Orderno) FROM PURCHASE";
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
            fxorderno.setText(val + "");
            
            stmnt = "SELECT Name FROM COMPANY";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while(executeQuery.next()){
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            fxcompname.setItems(comp);
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

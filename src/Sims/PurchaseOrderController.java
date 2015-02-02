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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    TextField fxcompany;
    @FXML
    ChoiceBox fxcompbox;
    
    @FXML
    public void updateCombo(KeyEvent ev){
        try {
            String stmnt = "SELECT Name FROM COMPANY WHERE Name LIKE '%"+fxcompany.getText()+"%'";
            ResultSet Query = Database.Query(stmnt);
            String cname = null;
            ObservableList<String> comp= fxcompbox.getItems();
            comp.clear();
            while(Query.next()){
                cname = Query.getString(1);
                comp.add(cname);
            }
            fxcompbox.setItems(comp);
            if(ev.getCode()==KeyCode.ENTER){
                String name=fxcompbox.getValue().toString();
                fxcompany.setText(name);            
            }
            else
                fxcompbox.show();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void Choiceboxaction(ActionEvent ev){
        String name = fxcompbox.getValue().toString();
        fxcompany.setText(name);
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
            fxcompbox.setItems(comp);
            
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}

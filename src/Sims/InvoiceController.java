/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@FXML private TextField batch;
@FXML private TextField qty;
@FXML private TextField free;
@FXML private ComboBox<String> product;
@FXML
    Label fxdate;
@FXML
    Label fxtin;
@FXML TableView<PurchaseOrderData> table;
   

@FXML
    public void CompanyAction(ActionEvent ev){
        try {
            String stmnt = "SELECT Address,TIN FROM CUSTOMER WHERE Name='"+customer.getValue()+"'";
            ResultSet Query = Database.Query(stmnt);
            if(Query.next()){
                address.setText(Query.getString("Address"));
                fxtin.setText(Query.getString("TIN"));
            }
        } catch (SQLException ex) {
        Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}

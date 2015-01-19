/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author jesuit
 */
public class Productcontroller implements Initializable {

    @FXML private TextField proname;
    @FXML private TextField compname;
    @FXML private TextField gnercname;
    @FXML private Button add;
    @FXML private void add(ActionEvent ev){
        String product=proname.getText();
        MainDocumentController mc = Control.getInstance().getMainDocumentController();
     if(product.equals("")){
         mc.Setstatusmessage("Error : Enter Product Name");
         return;
     }
     String company=compname.getText();
     if(company.equals("")){
         mc.Setstatusmessage("Error : Enter Product Name");
         return;
     }
     String generic =gnercname.getText();
     if(generic.equals("")){
         mc.Setstatusmessage("Error : Enter Product Name");
         return;
     }
     try 
     {
         String stmt="INSERT INTO PRODUCT (Name,Company,Generic) VALUES(?,?,?)";
          PreparedStatement updateproduct = Database.GetPreparedStmt(stmt);
         updateproduct.setString(1, product);
         updateproduct.setString(2, company);
         updateproduct.setString(3, generic);
         int executeUpdate = updateproduct.executeUpdate();
         if(executeUpdate == 1)
            mc.Setstatusmessage("Update Success");
         if(executeUpdate == 0)
             mc.Setstatusmessage("Update Failed");
     }  catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}

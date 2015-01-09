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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author jesuit
 */
public class GoodsReciptController implements Initializable{
    
    @FXML private TextField  recpno;
    @FXML private TextField recpdt;
    @FXML private TextArea cmpaddr;
    @FXML private TextField tin;
    @FXML private TextField ordno;
    @FXML private DatePicker orddt;
    @FXML private TextField product;
    @FXML private TextField mrp;
    @FXML private TextField batch;
    @FXML private DatePicker expdt;
    @FXML private TextField free;
    @FXML private TextField qty;
  
    private boolean isNumber(String s){
        try{
            Double.parseDouble(s);
        }catch(NumberFormatException e){
            return false;
        }
        return true;
    }
     @FXML private void add(ActionEvent ev){
     String proname=product.getText();
     
     MainDocumentController mc = Control.getInstance().getMainDocumentController();
     if(proname.equals("")){
         mc.Setstatusmessage("Error : Enter Product Name");
         return;
     }
     String Mrp=mrp.getText();
     if(Mrp.equals("")){
         mc.Setstatusmessage("Error : Enter Mrp");
         return;
     }else if(!isNumber(Mrp)){
         mc.Setstatusmessage("Error : Mrp Should be Number");
         return;
     }
     String Batch=batch.getText();
     if(Batch.equals("")){
         mc.Setstatusmessage("Error : Enter Batch");
         return;
     }else if(!isNumber(Batch)){
         mc.Setstatusmessage("Error : Batch Should Be a Number");
         return;
     }
     String Qty=qty.getText();
     if(Qty.equals("")){
         mc.Setstatusmessage("Error : Enter Quantity");
         return;
     }else if(!isNumber(Qty)){
         mc.Setstatusmessage("Error : Quantity should be number");
         return;
     }
     String Free=free.getText();
     if(Free.equals("")){
         mc.Setstatusmessage("Error : Enter Free");
         return;
     }else if(!isNumber(Free)){
         mc.Setstatusmessage("Error : Quantity Should be Number");
         return;
     }
     double dmrp = Double.parseDouble(Mrp);
     double dqty=Double.parseDouble(Qty);
     double dfree=Double.parseDouble(Free);
     double dbatch=Double.parseDouble(Batch);
     try
     {
         String stmt ="INSERT INTO STOCK (Name,MRP,Quantity,Free)VALUES(?,?,?,?)";
         PreparedStatement updatestock = Database.GetPreparedStmt(stmt);
         updatestock.setString(1, proname);
         updatestock.setDouble(2, dmrp);
         updatestock.setDouble(3, dqty);
         updatestock.setDouble(4, dfree);
         int executeUpdate = updatestock.executeUpdate();
         mc.Setstatusmessage("Update Success");
     }  catch (SQLException ex) {
            Logger.getLogger(GoodsReciptController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}

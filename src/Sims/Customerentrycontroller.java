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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import np.com.ngopal.control.AutoFillTextBox;

/**
 *
 * @author amalcs
 */
public class Customerentrycontroller implements Initializable{
    @FXML private TextField name;
    @FXML private TextArea address;
    @FXML private TextField place;
    @FXML private TextField phone;
    @FXML private AutoFillTextBox area ;
    @FXML private TextField tin;
    
    @FXML protected void add(ActionEvent ev){
        String Name = name.getText();
        String Address = address.getText();
        String Place = place.getText();
        String Phone = phone.getText();
        String Area  = area.getText();
        String Tin=tin.getText();
        
        MainDocumentController mc = Control.getInstance().getMainDocumentController();
        if(Name.equals("")){
            mc.Setstatusmessage("Error : Name Can't be null");
            return;
        }else if(Address.equals("")){
            mc.Setstatusmessage("Error : Address Can't be null");
            return;
        }else if(Place.equals("")){
            mc.Setstatusmessage("Error : Place Can't be null");
            return;
        }else if(Area.equals("")){
            mc.Setstatusmessage("Error : Area Can't be null");
            return;
        }
        else if(Tin.equals("")){
            mc.Setstatusmessage("Error : Tin Can't be null");
            return;
        }
        
        try{
            String stmnt = "INSERT INTO CUSTOMER (Name,Address,Place,Phone,Area,Tin) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement updatestmnt = Database.GetPreparedStmt(stmnt);
            updatestmnt.setString(1, Name);
            updatestmnt.setString(2, Address);
            updatestmnt.setString(3, Place);
            updatestmnt.setString(4, Phone);
            updatestmnt.setString(5, Area);
            updatestmnt.setString(6, Tin);
            int executeUpdate = updatestmnt.executeUpdate();
            System.out.println(executeUpdate+" Rows Changed");
            Control.getInstance().getMainDocumentController().Setstatusmessage("Update Success");
        }catch(SQLException ex){
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            area.setFilterMode(true);
            ObservableList<String> data = FXCollections.observableArrayList();
            
            String q = "SELECT * FROM AREA";
            ResultSet Query = Database.getInstance().Query(q);
            
            while(Query.next()){
                data.add(Query.getString("Area"));
            }
            area.setData(data);
        } catch (SQLException ex) {
            Logger.getLogger(Customerentrycontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
}

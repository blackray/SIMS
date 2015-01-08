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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Companyentrycontroller implements Initializable{
    @FXML private TextField name;
    @FXML private TextArea area;
    @FXML private TextField place;
    @FXML private TextField phone;
    @FXML private TextField dl;
    @FXML private TextField tin;
    @FXML private TextField cst;

    @FXML private void add(ActionEvent ev){
        MainDocumentController mc = Control.getInstance().getMainDocumentController();
        String Name = name.getText();
        if(Name.equals("")){
            mc.Setstatusmessage("Update Failed : Enter Name");
            return;
        }
        
        String Area = area.getText();
        if(Area.equals("")){
            mc.Setstatusmessage("Update Failed : Enter Address");
            return;
        }
        
        String Place = place.getText();
        if(Place.equals("")){
            mc.Setstatusmessage("Update Failed : Enter Place");
            return;
        }
        
        String Phone = phone.getText();
        if(Phone.equals("")){
            mc.Setstatusmessage("Update Failed : Enter Phone No");
            return;
        }
        
        String DL = dl.getText();
        if(DL.equals("")){
            mc.Setstatusmessage("Update Failed : Enter DL No");
            return;
        }
        
        String TIN = tin.getText();
        if(TIN.equals("")){
            mc.Setstatusmessage("Update Failed : Enter TIN No");
            return;
        }
        
        String CST = cst.getText();
        if(CST.equals("")){
            mc.Setstatusmessage("Update Failed : Enter CST No");
            return;
        }
        try {
            String stmt = "INSERT INTO COMPANY (Name,Address,Place,Phone,DL,TIN,CST) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement updatecompany = Database.GetPreparedStmt(stmt);
            updatecompany.setString(1,Name);
            updatecompany.setString(2,Area);
            updatecompany.setString(3,Place);
            updatecompany.setString(4,Phone);
            updatecompany.setString(5,DL);
            updatecompany.setString(6,TIN);
            updatecompany.setString(7,CST);
            int executeUpdate = updatecompany.executeUpdate();
            System.out.println(executeUpdate+" Row Changed");
            mc.Setstatusmessage("Update Success");
            
            name.clear();
            area.clear();
            place.clear();
            phone.clear();
            dl.clear();
            tin.clear();
            cst.clear();
        } catch (SQLException ex) {
            Logger.getLogger(Companyentrycontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}

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
        try {
            String stmt = "INSERT INTO COMPANY (Name,Address,Place,Phone,DL,TIN,CST) "
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement updatecompany = Database.GetPreparedStmt(stmt);
            updatecompany.setString(1,name.getText());
            updatecompany.setString(2,area.getText());
            updatecompany.setString(3,place.getText());
            updatecompany.setString(4, phone.getText());
            updatecompany.setString(5, dl.getText());
            updatecompany.setString(6, tin.getText());
            updatecompany.setString(7, cst.getText());
            int executeUpdate = updatecompany.executeUpdate();
            System.out.println(executeUpdate+" Row Changed");
        } catch (SQLException ex) {
            Logger.getLogger(Companyentrycontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}

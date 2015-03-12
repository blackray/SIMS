/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Changepasswordcontroller implements Initializable{

    @FXML TextField tf_current_password;
    @FXML TextField tf_new_Ppassword;
    @FXML TextField tf_retype_password;
    @FXML Label rp_status;
    
    @FXML private void changepassword(ActionEvent ev){
        String cur_pass = tf_current_password.getText();
        String new_pass = tf_new_Ppassword.getText();
        String retype_pass = tf_retype_password.getText();
        
        
        MainDocumentController mc = Control.getInstance().getMainDocumentController();
        String Username = Control.getInstance().GetCurrentUser();
        mc.Setstatusmessage("Status");
        rp_status.setText("");
        if(new_pass.equals("")){
            mc.Setstatusmessage("Error : Password Can't Be Null");
            return;
        }
        if(!new_pass.equals(retype_pass)){
            rp_status.setText("Don't Match");
            return;
        }
        MessageDigest md;
        String sha_cur=null;
        String sha_newpass=null;
        try {
             md = MessageDigest.getInstance("SHA");
             byte[] shap = md.digest(cur_pass.getBytes());
             md.reset();
             sha_cur = Arrays.toString(shap);
             
             shap = md.digest(new_pass.getBytes());
             sha_newpass = Arrays.toString(shap);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Changepasswordcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet Query = Database.Query("select PASSWORD from LOGIN where USERNAME='"+Username+"'");
        try {
            while(Query.next()){
                String s=Query.getString("PASSWORD");
                if(s.equals(sha_cur)){
                    Database.Update("UPDATE LOGIN SET PASSWORD='"+sha_newpass+"' WHERE USERNAME = '"+Username+"'");
                }else{
                    mc.Setstatusmessage("Error : Current Username Dont Match");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Changepasswordcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

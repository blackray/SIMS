package Sims;

import Sims.com.Messagebox.Messagebox;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author amalcs
 */
public class AddAccountController implements Initializable {

    @FXML
    TextField tf_username;
    @FXML
    TextField tf_password;

    @FXML
    private void AddAccountAction(ActionEvent ev) {
        String Username = tf_username.getText();
        String Password = tf_password.getText();

        MainDocumentController mc = Control.getInstance().getMainDocumentController();
        mc.Setstatusmessage("Status");
        if (Username.length() < 4) {
            mc.Setstatusmessage("Error : Username Should be mininum 4 letter");
            return;
        }else{
            
            try {
                ResultSet Query = Database.Query("SELECT * FROM LOGIN WHERE USERNAME='"+Username+"'");
                if(Query.next()){
                    mc.Setstatusmessage("Error : Username Exist");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Password.length() < 6) {
            mc.Setstatusmessage("Error : Password Should be minimum 4 letter");
            return;
        }
        try {
            MessageDigest md;
            String shapass = null;
            try {
                md = MessageDigest.getInstance("SHA");
                md.reset();
                byte[] s = md.digest(Password.getBytes());
                shapass = Arrays.toString(s);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            String stmt = "INSERT INTO LOGIN (USERNAME,PASSWORD) VALUES(?,?)";
            PreparedStatement pstmt = Database.GetPreparedStmt(stmt);
            pstmt.setString(1, Username);
            pstmt.setString(2, shapass);
            pstmt.executeUpdate();
            mc.Setstatusmessage("Update Sucessful");
            Messagebox.getInstance().message("Accounts","New Account Created Successfully");
            tf_username.clear();
            tf_password.clear();
        } catch (SQLException ex) {
            Logger.getLogger(AddAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Control.getInstance().getMainDocumentController().Setstatusmessage("Status");
    }

}

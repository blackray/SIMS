/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Loginfxmlcontroller implements Initializable {
    
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label status;
    
    @FXML
    private void onclicked1(ActionEvent event) {
        String un =username.getText();
        String pw =password.getText();
        if(Control.Authenticate(un, pw)){
            
        }else{
            status.setText("Login Failed");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // label.setText("Click The Above Button");
    }    
    
}

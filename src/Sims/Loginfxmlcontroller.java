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
        System.out.println("Login Procedure Initiating ");
        System.out.println("Username :  "+un);
        System.out.println("Password :  "+pw);
        
        int ret=Database.connect("localhost", "test", un, pw);
        if(ret == 1){
            System.out.println("Login Failed");
            status.setText("Login failed");
        }else{
            System.out.println("Login Success");
        }
        
        //label.setText("Good Morning!!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // label.setText("Click The Above Button");
    }    
    
}

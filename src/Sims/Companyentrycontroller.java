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
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}

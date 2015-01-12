/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

/**
 *
 * @author amalcs
 */
public class MainDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private MenuItem Generic;
    @FXML
    private Pane displayarea;
    
    @FXML private Label status;

    @FXML
    private void MenuGenericAction(ActionEvent ev) {
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("GenericDatabase.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML 
    private void MenuCustomerAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("Customerdataentry.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML 
    private void MenuAreaAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("AreaDocument.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML 
    private void MenuCompanyAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("Companydataentry.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void MenuGoodsReciptAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("GoodsRecipt.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void MenuProductAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("ProductMaster.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    @FXML
    private void MenuForgotPaswordAction(ActionEvent ev){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("ChangePassword.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    public void Setstatusmessage(String str){
        status.setText(str);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Control ctrl = Control.getInstance();
        ctrl.SetMainDocumentController(this);
        status.setText("Buhaha");
        
                
    }
}

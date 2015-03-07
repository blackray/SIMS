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
    private void LoadFxml(String filename){
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource(filename)));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void MenuGenericAction(ActionEvent ev) {
        LoadFxml("GenericDatabase.fxml");
    }
    @FXML 
    private void MenuCustomerAction(ActionEvent ev){
        LoadFxml("Customerdataentry.fxml");
    }
    @FXML 
    private void MenuAreaAction(ActionEvent ev){
        LoadFxml("AreaDocument.fxml");
    }
    @FXML 
    private void MenuCompanyAction(ActionEvent ev){
        LoadFxml("Companydataentry.fxml");
    }
    @FXML
    private void MenuGoodsReciptAction(ActionEvent ev){
        LoadFxml("GoodsRecipt.fxml");
    }
    @FXML
    private void MenuProductAction(ActionEvent ev){
        LoadFxml("ProductMaster.fxml");
    }
    @FXML
    private void MenuForgotPaswordAction(ActionEvent ev){
        LoadFxml("ChangePassword.fxml");  
    }
    @FXML
    private void MenuAddAccountAction(ActionEvent ev){
        LoadFxml("addaccount.fxml");
    }
    @FXML
    private void MenuPurchaseOrderAction(ActionEvent ev){
        LoadFxml("PurchaseOrder.fxml");
    }
    @FXML
    private void MenuBillAction(ActionEvent ev){
        LoadFxml("Invoice.fxml");
    }
    @FXML
    private void MenuCreditSRAction(ActionEvent ev){
        LoadFxml("CreditnoteSR.fxml");
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

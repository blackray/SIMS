/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    @FXML TableView<Stockdata> stocktable;
    
    @FXML private Label status;
    @FXML private TextField tfsearchentry;
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
    @FXML
    private void MenuAboutAction(ActionEvent ev){
        LoadFxml("About.fxml");
    }
    @FXML
    private void MenuDebitAction(ActionEvent ev){
        LoadFxml("Debitnote.fxml");
    }
    @FXML
    private void MenuStocklistAction(ActionEvent ev){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Control c = Control.getInstance();
            Stage stage = c.getStage();
            Scene scene = c.getScene();
            scene.setRoot(root);
            stage.setScene(scene);
            stage.setTitle("Netbill");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void Setstatusmessage(String str){
        status.setText(str);
    }
    @FXML
    private void SearchbuttonAction(ActionEvent ev){
        String s = tfsearchentry.getText();
        if(s.equals("")){
            filltable("");
            return;
        }
        String stmnt = "SELECT Product,Batch,Quantity FROM STOCK WHERE Product='"+s+"'";
        filltable(stmnt);
    }
    
    private void filltable(String stmnt){
        if(stmnt.equals(""))
            stmnt = "SELECT Product,Batch,Quantity FROM STOCK ORDER BY Quantity";
        ObservableList<Stockdata> data = FXCollections.observableArrayList();
        ResultSet Query = Database.Query(stmnt);
        try {
            while(Query.next()){
                String pname = Query.getString(1);
                String batch = Query.getString(2);
                String quantity = Query.getString(3);
                data.add(new Stockdata(pname, batch, quantity));
            }
            stocktable.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Control ctrl = Control.getInstance();
        ctrl.SetMainDocumentController(this);
        status.setText("Status");
        
        String stmnt = "SELECT Product,Batch,Quantity FROM STOCK ORDER BY Quantity";
        filltable(stmnt);                
    }
}

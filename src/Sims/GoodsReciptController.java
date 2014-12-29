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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author jesuit
 */
public class GoodsReciptController implements Initializable{
    
    @FXML private TextField  recpno;
    @FXML private TextField recpdt;
    @FXML private TextArea cmpaddr;
    @FXML private TextField tin;
    @FXML private TextField ordno;
    @FXML private DatePicker orddt;
    @FXML private TextField product;
    @FXML private TextField mrp;
    @FXML private TextField batch;
    @FXML private TextField expdt;
    @FXML private TextField free;
    @FXML private TextField qty;
    @FXML private Button add;
  
    
    @FXML private void add(ActionEvent ev){
        
        
    }  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
    }
}

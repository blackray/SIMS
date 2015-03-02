/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims.com.Messagebox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author amalcs
 */
public class Messageboxcontroller implements Initializable{
    
    @FXML
    private Label messageLabel;
    @FXML
    private Label detailsLabel;
    
    @FXML
    public void close(ActionEvent ev){
        Messagebox.getInstance().close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messageLabel.setText(Messagebox.getInstance().header);
        detailsLabel.setText(Messagebox.getInstance().details);
    }
    
}

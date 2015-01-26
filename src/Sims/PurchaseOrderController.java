/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 *
 * @author amalcs
 */
public class PurchaseOrderController implements Initializable{
    @FXML
    Text fxaddress;
    @FXML
    Label fxdate;
    @FXML
    Label fxorderno;
    @FXML
    Label fxtin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fxdate.setText(d.format(date));
    }
    
}

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

    @FXML
    private void MenuGenericAction(ActionEvent ev) {
        displayarea.getChildren().clear();
        try {
            displayarea.getChildren().add(FXMLLoader.load(getClass().getResource("GenericDatabase.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(MainDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

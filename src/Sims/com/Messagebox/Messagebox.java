/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims.com.Messagebox;

import Sims.Control;
import Sims.MainDocumentController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author amalcs
 */
public class Messagebox {
    public String header;
    public String details;
    
    private Stage stage;
    
    private static final Messagebox Instance = new Messagebox();


    private Messagebox() {
    }
    public static Messagebox getInstance(){
        return Instance;
    }
    public void close(){
        stage.close();
    }
    public void messageerror(String details){
        message("Error",details);
    }
    public void message(String header , String details){
        this.header = header;
        this.details = details;
        try {
            stage = new Stage();
           
            Parent root = FXMLLoader.load(getClass().getResource("Messagebox.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Error");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Messagebox.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
}

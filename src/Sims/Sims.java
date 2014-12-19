/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amalcs
 */
public class Sims extends Application {
    
    Database db;
    
    @Override
    public void start(Stage stage) throws Exception {
        Control c = Control.getInstance();
        c.SetStage(stage);
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Database.connectDerby("sims");
        Database.setupDatabase();
        launch(args);
    }
    
}

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
        stage.setTitle("Netbill Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config c = Config.GetInstance();
        c.Parse();
        if(Config.Get_DB_Connection_type().equals("derby")){
            Database.connectDerby("sims");
        }else if(Config.Get_DB_Connection_type().equals("network")){
            String address = Config.Get_DB_Connection_Address();
            String username = Config.Get_DB_Connection_Username();
            String password = Config.Get_DB_Connection_Password();
            if(!Database.connectmysql(address,"test",username, password)){
                System.out.println("Connect to mysql server failed \n"
                        + "Using Internal DB");
                Database.connectDerby("sims");
            }
        }
        Database.setupDatabase();
        launch(args);
    }
    
}

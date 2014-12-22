/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author amalcs
 */
public class Control {
    private Stage stage;
    private boolean logedin;
    private static final Control Instance = new Control();
    private MainDocumentController maincontroller;

    private Control() {
        logedin = false;
    }
    public static Control getInstance(){
        return Instance;
    }
    public void SetMainDocumentController(MainDocumentController mc){
        maincontroller = mc;
    }
    public MainDocumentController getMainDocumentController(){
        return maincontroller;
    }
    public void SetStage(Stage stage){
        this.stage = stage;
    }
    public void SetPane(String s){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            if(s.equals("main")){
                stage.close();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Netbill");
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean Authenticate(String username,String password){
        ResultSet Query = Database.Query("select PASSWORD from LOGIN where USERNAME='"+username+"'");
        if(Query == null){
            return false;
        }
        try {
            
            while(Query.next()){
                String s=Query.getString("PASSWORD");
                System.out.println(s);
                if(s.equals(password)){
                    logedin = true;
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Authentication Failure");
            return false;
        }
        return false;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
    private String username;
    private Scene scene;
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
    public Stage getStage(){
        return stage;
    }
    public void Maximizewindow(){
        stage.setMaximized(true);
    }
    public Scene getScene(){
        return scene;
    }
    public void SetPane(String s){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            if(s.equals("main")){
                stage.close();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Netbill");
                Maximizewindow();
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean Authenticate(String username,String password){    
        String password_sha=null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.reset();
            byte[] password_sha_byte = md.digest(password.getBytes());
            md.reset();
            password_sha = Arrays.toString(password_sha_byte);
            System.out.println("Password for checking = "+password_sha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet Query = Database.Query("select * from LOGIN where USERNAME='"+username+"'");
        if(Query == null){
            return false;
        }
        try {
            
            while(Query.next()){
                String s=Query.getString("PASSWORD");
                System.out.println(s);
                if(s.equals(password_sha)){
                    this.username = username;
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
    public String GetCurrentUser(){
        return username;
    }
}


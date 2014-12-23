/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Customerentrycontroller implements Initializable{
    @FXML private TextField name;
    @FXML private TextArea address;
    @FXML private TextField place;
    @FXML private TextField phone;
    @FXML private ComboBox<String> area ;
    
    @FXML protected void add(ActionEvent ev){
        String Name = name.getText();
        String Address = address.getText();
        String Place = place.getText();
        String Phone = phone.getText();
        String Area  = area.getValue();
        
        ObservableList<String> data = area.getItems();
        
        System.out.println(Name + "\n"+Address+"\n"+Place+"\n"+Phone+"\n"+Area);
        String up = "insert into customer (Name,Address,Place,Phone,Area) values "
                + "('"+Name+"','"+Address+"','"+Place+"','"+Phone+"','"+Area+"')";
        Database db = Database.getInstance();
        boolean Update = db.Update(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<String> data = area.getItems();
            
            String q = "SELECT * FROM AREA";
            ResultSet Query = Database.getInstance().Query(q);
            
            while(Query.next()){
                data.add(Query.getString("Area"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customerentrycontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
}

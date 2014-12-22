/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Customerentrycontroller {
    @FXML private TextField name;
    @FXML private TextArea address;
    @FXML private TextField place;
    @FXML private TextField phone;
    
    @FXML protected void add(ActionEvent ev){
        String Name = name.getText();
        String Address = address.getText();
        String Place = place.getText();
        String Phone = phone.getText();
        String Area  = "";
        
        System.out.println(Name + "\n"+Address+"\n"+Place+"\n"+Phone);
        String up = "insert into customer (Name,Address,Place,Phone,Area) values "
                + "('"+Name+"','"+Address+"','"+Place+"','"+Phone+"','"+Area+"')";
        Database db = Database.getInstance();
        boolean Update = db.Update(up);
    }
    
}

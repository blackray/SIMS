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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class Areacontroller implements Initializable{

    @FXML private TableView<Area> table;
    @FXML private TextField area_tf;
    
    @FXML
    private void AddAction(ActionEvent ev){
        ObservableList<Area> data = table.getItems();
        data.add(new Area(area_tf.getText()));
        String query = "INSERT INTO AREA (Area) VALUES ('"+area_tf.getText()+"')";
        Database db = Database.getInstance();
        if(db.Update(query)){
            Control.getInstance().getMainDocumentController().Setstatusmessage("Updated Successfully");
        }else{
            Control.getInstance().getMainDocumentController().Setstatusmessage("Database Update Failed... Value Exist");
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("Hereeeeeeeeeee");
            ObservableList<Area> data = table.getItems();
            Database db = Database.getInstance();
            String query = "Select * from AREA";
            ResultSet Query;
            Query = Database.Query(query);
            while(Query.next()){
                String st = Query.getString("Area");
                data.add(new Area(st));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Areacontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

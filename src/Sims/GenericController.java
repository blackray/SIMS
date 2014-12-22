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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class GenericController implements Initializable{
    @FXML private TableView<GenericNames> table;
    ObservableList<GenericNames> list;
    @FXML private TextField entry;
    @FXML private Label status;
    
    int count = 0;

    @FXML protected void addGenericNames(ActionEvent ev){
        String n = entry.getText();
        if(n.equals("")){
            return;
        }
        count++;
        ObservableList<GenericNames> data = table.getItems();
        String c = count+"";
        
        String q = "insert into GENERIC (generic_name) values ('"+entry.getText()+"')";
        if(Database.Update(q)){
            data.add(new GenericNames(c,entry.getText()));  
        }else{
            Control ctrl = Control.getInstance();
            MainDocumentController mc =ctrl.getMainDocumentController();
            mc.Setstatusmessage("Database Updata Failed ... Value Exist");
        }
        entry.setText("");
    }
    public GenericController() {
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.err.println("GenericDatabase entry screen Initiazer");
        ObservableList<GenericNames> data = table.getItems();
        String c;
        try {
            String query = "select * from generic";
            ResultSet res = Database.Query(query);
            while(res.next()){
                count++;
                c=count+"";
                String out = res.getString("generic_name");
                data.add(new GenericNames(c,out));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

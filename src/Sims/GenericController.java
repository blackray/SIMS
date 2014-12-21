/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author amalcs
 */
public class GenericController {
    @FXML private TableView<GenericNames> table;
    ObservableList<GenericNames> list;
    @FXML private TextField entry;
    
    int count = 0;

    @FXML protected void addGenericNames(ActionEvent ev){
        String n = entry.getText();
        if(n.equals("")){
            return;
        }
        count++;
        ObservableList<GenericNames> data = table.getItems();
        String c = count+"";
        data.add(new GenericNames(c,entry.getText()));
        entry.setText("");
    }
    public GenericController() {
    } 
}

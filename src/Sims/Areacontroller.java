/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.sql.PreparedStatement;
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
public class Areacontroller implements Initializable {

    @FXML
    private TableView<Area> table;
    @FXML
    private TextField area_tf;

    @FXML
    private void AddAction(ActionEvent ev) {
        if (area_tf.getText().equals("")) {
            Control.getInstance().getMainDocumentController().Setstatusmessage("Error : Area Cant be Null");
            return;
        }
        ObservableList<Area> data = table.getItems();
        try {
            String stmnt = "INSERT INTO AREA (Area) VALUES (?)";
            PreparedStatement updatearea = Database.GetPreparedStmt(stmnt);
            updatearea.setString(1, area_tf.getText());
            int executeUpdate = updatearea.executeUpdate();
            System.out.println(executeUpdate + " Row Changed");
            Control.getInstance().getMainDocumentController().Setstatusmessage("Updated Successfully");
            area_tf.clear();
            refresh();
        } catch (SQLException ex) {
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
        }
    }

    private void refresh() {
        try {
            ObservableList<Area> data = table.getItems();
            data.clear();
            Database db = Database.getInstance();
            String query = "Select * from AREA";
            ResultSet Query;
            Query = Database.Query(query);
            while (Query.next()) {
                String st = Query.getString("Area");
                data.add(new Area(st));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Areacontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }

}

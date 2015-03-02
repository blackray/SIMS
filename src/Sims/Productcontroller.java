/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import Sims.com.Messagebox.Messagebox;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author jesuit
 */
public class Productcontroller implements Initializable {

    @FXML
    private TextField proname;
    @FXML
    private ComboBox<String> compname;
    @FXML
    private ComboBox<String> genname;
    @FXML
    private Button add;

    @FXML
    private void add(ActionEvent ev) {
        Messagebox mb = Messagebox.getInstance();
        String product = proname.getText();
        MainDocumentController mc = Control.getInstance().getMainDocumentController();
        if (product.equals("")) {
            mb.messageerror("Enter Product Name");
            mc.Setstatusmessage("Error : Enter Product Name");
            return;
        }
        String company = compname.getValue();
        if (company == null) {
            mb.messageerror("Enter Company Name");
            mc.Setstatusmessage("Error : Select Company");
            return;
        }
        String generic = genname.getValue();
        if (generic == null) {
            mb.messageerror("Enter Generic Name");
            mc.Setstatusmessage("Error : Select Generic");
            return;
        }
        try {
            String stmt = "INSERT INTO PRODUCT (Name,Company,Generic) VALUES(?,?,?)";
            PreparedStatement updateproduct = Database.GetPreparedStmt(stmt);
            updateproduct.setString(1, product);
            updateproduct.setString(2, company);
            updateproduct.setString(3, generic);
            int executeUpdate = updateproduct.executeUpdate();
            if (executeUpdate == 1) {
                mc.Setstatusmessage("Update Success");
            }
            if (executeUpdate == 0) {
                mb.messageerror("Database Update Failed");
                mc.Setstatusmessage("Update Failed");
            }
        } catch (SQLException ex) {
            mb.message("Database Error",ex.getMessage());
            mc.Setstatusmessage(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String stmnt = "SELECT Name FROM GENERIC";
            ResultSet Query = Database.Query(stmnt);
            ObservableList<String> gen = FXCollections.observableArrayList();
            String gname;
            while (Query.next()) {
                gname = Query.getString("Name");
                gen.add(gname);
            }
            genname.setItems(gen);
            
            
            stmnt = "SELECT Name FROM COMPANY";
            Query = Database.Query(stmnt);
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while (Query.next()) {
                cname = Query.getString("Name");
                comp.add(cname);
            }
            compname.setItems(comp);
        } catch (SQLException ex) {
            Logger.getLogger(Productcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author jesuit
 */
public class GoodsReciptController implements Initializable {

    @FXML
    private TextField recpno;
    @FXML
    private TextField recpdt;
    @FXML
    private TextArea cmpaddr;
    @FXML
    private TextField tin;
    @FXML
    private TextField ordno;
    @FXML
    private DatePicker orddt;
    @FXML
    private TextField product;
    @FXML
    private TextField mrp;
    @FXML
    private TextField batch;
    @FXML
    private DatePicker expdt;
    @FXML
    private TextField free;
    @FXML
    private TextField qty;
    @FXML
    private TableView<Goodsreciptdata> table;

    @FXML
    private void add(ActionEvent ev) {
        String proname = product.getText();
        String Mrp = mrp.getText();
        String Batch = batch.getText();
        String Qty = qty.getText();
        String Free = free.getText();
        LocalDate date = expdt.getValue();
        double dmrp = Integer.parseInt(Mrp);
        double dqty = Integer.parseInt(Qty);
        double dfree = Integer.parseInt(Free);
        

        ObservableList<Goodsreciptdata> data = table.getItems();
        try {
            String stmt = "INSERT INTO STOCK (Product,Batch,MRP,Expiry,Quantity,Free)VALUES(?,?,?,?,?,?)";
            PreparedStatement updatestock = Database.GetPreparedStmt(stmt);
            updatestock.setString(1, proname);
            updatestock.setString(2, Batch);
            updatestock.setDouble(3, dmrp);
            updatestock.setString(4, date.toString());
            updatestock.setDouble(5, dqty);
            updatestock.setDouble(6, dfree);
            int executeUpdate = updatestock.executeUpdate();
            System.out.println(executeUpdate + " Row Changed");
            data.add(new Goodsreciptdata(proname, "","", Mrp, Batch,date.toString(), Qty, Free));
        } catch (SQLException ex) {
            Control.getInstance().getMainDocumentController().Setstatusmessage(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

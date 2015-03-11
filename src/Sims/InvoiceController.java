/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import Sims.com.Messagebox.Messagebox;
import Sims.com.jasper.jaspercontroller;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author jesuit
 */
public class InvoiceController implements Initializable {

    Double tpdvalue;
    Double tmvalue;

    @FXML
    private TableColumn productcell;
    @FXML
    private ComboBox<String> customer;
    @FXML
    private Text address;
    @FXML
    private Label fxinvoiceno;
    @FXML
    private TextField qty;
    @FXML
    private TextField free;
    @FXML
    private ComboBox<String> product;
    @FXML
    private ComboBox<String> batch;
    @FXML
    Label fxdate;
    @FXML
    Label Tpvalue;
    @FXML
    Label Tmvalue;
    @FXML
    Label fxtin;
    @FXML
    TableView<InvoiceData> table;
    String expdate, PTR, PTS, mrp, Tax, Taxamt, Pdvalue, Mrpvalue, Rate;

    @FXML
    public void CustomerAction(ActionEvent ev) {
        try {
            String stmnt = "SELECT Address,Tin FROM CUSTOMER WHERE Name='" + customer.getValue() + "'";
            ResultSet Query = Database.Query(stmnt);
            if (Query.next()) {
                address.setText(Query.getString("Address"));
                fxtin.setText(Query.getString("Tin"));

            } else {
                System.out.println("No Product Found");
            }
            //stmnt = "SELECT Expiry FROM STOCK WHERE Batch='" + batch.getValue() + "'";
            //ResultSet Q = Database.Query(stmnt);
            //expdate = Q.getString("Expiry");
            //calculation();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void Productcomboboxaction(ActionEvent ev) {
        try {
            String stmnt;
            stmnt = "SELECT Batch FROM STOCK WHERE Product='" + product.getValue() + "'";
            ResultSet q = Database.Query(stmnt);
            ObservableList<String> prod = FXCollections.observableArrayList();
            String pname;
            while (q.next()) {
                pname = q.getString("Batch");
                System.out.println(pname);
                prod.add(pname);
            }
            batch.setItems(prod);
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void calculation() {

        String pname = product.getValue();
        String bat = batch.getValue();
        Double Qty = Double.parseDouble(qty.getText());
        Double Free = Double.parseDouble(free.getText());
        ObservableList<InvoiceData> data = table.getItems();
        for (InvoiceData d : data) {
            if (d.getProduct() == pname) {
                return;
            }
        }
        //Check stock balance
        String stmnt = "SELECT Quantity,Free FROM STOCK WHERE Product='"+pname+"' AND Batch="+bat;
        ResultSet Query = Database.Query(stmnt);
        try {
            if (Query.next()) {
                int stock_quantity = Query.getInt("Quantity");
                if (stock_quantity < Qty) {
                    Messagebox.getInstance().message("Check Quantity", "Entered Quantity is less than Stock Quantity");
                    return;
                }
                int freeqty = Query.getInt("Free");
                if(freeqty < Free){
                    Messagebox.getInstance().message("Check Free Quantity", "Entered Free Quantity is less than Stock Free Quantity");                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Invoiceproductcalculator ipc = new Invoiceproductcalculator();
        InvoiceProductdata pd = ipc.calculate(product.getValue(), batch.getValue(), Qty, Free);

        data.add(new InvoiceData(pd.getName(), pd.getBatch(),
                pd.getExpdat(), pd.getFree(), pd.getBilled(), pd.getPtr(), pd.getPts(),
                pd.getMrp(), pd.getTax(), pd.getTaxamt(), pd.getPdvalue(), pd.getMrpvalue()));

        tpdvalue = (tpdvalue + (Double.parseDouble(pd.getPdvalue())));
        tpdvalue = Math.round(100 * tpdvalue) / 100d;
        Tpvalue.setText(tpdvalue + "");
        tmvalue = (tmvalue + (Double.parseDouble(pd.getMrpvalue())));
        tmvalue = Math.round(100 * tmvalue) / 100d;
        Tmvalue.setText(tmvalue + "");

    }

    @FXML
    public void productcellcommit(ActionEvent ev) {
        System.out.println("dfghj");
    }

    @FXML
    public void Print(ActionEvent ev) {
        //Update INVOICE Database
        int invoiceno = Integer.parseInt(fxinvoiceno.getText());

        String stmnt = "INSERT INTO INVOICE VALUES(?,?,?,?,?)";
        PreparedStatement pstmnt = Database.GetPreparedStmt(stmnt);
        DateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            pstmnt.setInt(1, invoiceno);
            pstmnt.setString(2, customer.getValue());
            pstmnt.setString(3, fd.format(d));
            pstmnt.setDouble(4, Double.parseDouble(Tpvalue.getText()));
            pstmnt.setDouble(5, Double.parseDouble(Tmvalue.getText()));
            int executeUpdate = pstmnt.executeUpdate();
            System.out.println(executeUpdate + " Row Changed");
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Update invoiceproduct db
        
        String inpdstmnt = "INSERT INTO INVOICEPRODUCT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement p = Database.GetPreparedStmt(inpdstmnt);
        ObservableList<InvoiceData> id = table.getItems();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
        for(InvoiceData data : id){
            System.out.println("Here");
            p.setString(1,invoiceno+"");
            p.setString(2, data.getProduct());
            p.setString(3,data.getBatch() );
            p.setString(4,df.format(date) );
            p.setString(5, data.getBilled());
            p.setString(6, data.getFree());
            p.setString(7, data.getPtr());
            p.setString(8, data.getPts());
            p.setString(9, data.getMrp());
            p.setString(10, data.getTax());
            p.setString(11, data.getTaxamt());
            p.setString(12, data.getPdvalue());
            p.setString(13, data.getMrpvalue());
            
            int executeUpdate = p.executeUpdate();
            System.out.println(executeUpdate + " Row Changed in invoiceproduct");
        }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        //Updating Stock
        id = table.getItems();
        ResultSet Query;
        for (InvoiceData data : id) {
            try {
                stmnt = "SELECT Quantity,Free FROM STOCK WHERE Product='" + data.getProduct() + "' AND Batch=" + data.getBatch();
                Query = Database.Query(stmnt);
                if (Query.next()) {
                    int newval = (int) (Query.getInt(1) - Double.parseDouble(data.getBilled()));
                    int newfree = (int) (Query.getInt("Free") - Double.parseDouble(data.getFree()));
                    String updstmnt;
                    if(newval == 0){
                        updstmnt = "DELETE FROM STOCK WHERE Product='" + data.getProduct() + "' AND Batch=" + data.getBatch();
                    }else{

                        updstmnt = "UPDATE STOCK SET Quantity="+newval+",Free="+newfree+" WHERE Product='" + data.getProduct() + "' AND Batch=" + data.getBatch();
                    }
                    boolean Update = Database.Update(updstmnt);
                    if(Update){
                        System.out.println("Update Success");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //Printing Using Jasper
        jaspercontroller jc = new jaspercontroller();
        String[] ColumnNames = {"SL", "ITEM", "MRP"};
        int i = 0;
        int size = id.size();
        String[][] Data = new String[size][3];
        for (InvoiceData idata : id) {
            Data[i][0] = (i + 1) + "";
            Data[i][1] = idata.getProduct();
            Data[i][2] = idata.getMrp();
            i++;
        }
        jc.printInvoice(ColumnNames, Data);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tpdvalue = 0.0;
        tmvalue = 0.0;
        table.setEditable(true);
        productcell.setEditable(true);
        DateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fxdate.setText(d.format(date));
        try {
            String stmnt = "SELECT MAX(INVOICENO) FROM INVOICE";
            PreparedStatement GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            ResultSet executeQuery = GetPreparedStmt.executeQuery();
            int val = 0;
            if (executeQuery.next()) {
                String s = executeQuery.getString(1);
                if (s == null) {
                    val = 0;
                } else {
                    val = Integer.parseInt(s);
                }
                val++;
            }
            fxinvoiceno.setText(val + "");

            stmnt = "SELECT Name FROM CUSTOMER";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> comp = FXCollections.observableArrayList();
            String cname;
            while (executeQuery.next()) {
                cname = executeQuery.getString(1);
                comp.add(cname);
            }
            customer.setItems(comp);
            stmnt = "SELECT DISTINCT Product FROM STOCK";
            GetPreparedStmt = Database.GetPreparedStmt(stmnt);
            executeQuery = GetPreparedStmt.executeQuery();
            ObservableList<String> compp = FXCollections.observableArrayList();
            String pname;
            while (executeQuery.next()) {
                pname = executeQuery.getString(1);
                compp.add(pname);
            }
            product.setItems(compp);

        } catch (SQLException ex) {
            Logger.getLogger(PurchaseOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

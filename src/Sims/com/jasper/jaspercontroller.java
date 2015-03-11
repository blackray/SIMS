/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims.com.jasper;

import Sims.Config;
import Sims.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author amalcs
 */
public class jaspercontroller {

    DefaultTableModel tableModel;

    public void printInvoice(String[] ColumnNames, String[][] Data) {
        tableModel = new DefaultTableModel(Data, ColumnNames);
        Connection connection = null;
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
            connection = Database.getConnection();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        JasperPrint jasperprint = null;
        try {
            JasperCompileManager.compileReportToFile("report1.jrxml");
            jasperprint = JasperFillManager.fillReport("report name.jasper", new HashMap(),
                    connection);
            //JasperExportManager.exportReportToPdfFile(jasperprint,
              //    "invoice.pdf");            
            JasperViewer jasperviewer = new JasperViewer(jasperprint,false);
            jasperviewer.setVisible(true);
            jasperviewer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public jaspercontroller() {
    }
}

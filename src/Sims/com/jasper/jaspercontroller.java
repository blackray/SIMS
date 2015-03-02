/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims.com.jasper;

import java.util.HashMap;
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

        JasperPrint jasperprint = null;
        try {
            JasperCompileManager.compileReportToFile("report1.jrxml");
            jasperprint = JasperFillManager.fillReport("report name.jasper", new HashMap(),
                    new JRTableModelDataSource(tableModel));
JasperExportManager.exportReportToPdfFile(jasperprint,
                  "invoice.pdf");            
//JasperViewer jasperviewer = new JasperViewer(jasperprint);
            //jasperviewer.setVisible(true);
        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public jaspercontroller() {
    }
}

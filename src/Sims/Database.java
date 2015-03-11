/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amalcs
 */
public class Database {

    static Connection conn;
    private static final Database db = new Database();

    private Database() {
        conn = null;
    }
    public static Connection getConnection(){
        return conn;
    }

    /*To be called first time the Program is run
     the function create the necessary database table
     for the working of the program
     */
    public static void setupDatabase() {
        if (ifexist_table("LOGIN")) {
            System.out.println("LOGIN Table Found");
        } else {
            createLoginDB();
        }
        if (ifexist_table("GENERIC")) {
            System.out.println("generic Table Found");
        } else {
            createGenericDB();
        }
        if (ifexist_table("CUSTOMER")) {
            System.out.println("CUSTOMER Table Found");
        } else {
            createCustomerDB();
        }
        if (ifexist_table("AREA")) {
            System.out.println("AREA Table Found");
        } else {
            createAreaDB();
        }
        if (ifexist_table("COMPANY")) {
            System.out.println("COMPANY Table Found");
        } else {
            createCompanyDB();
        }
        if (ifexist_table("PRODUCT")) {
            System.out.println("PRODUCT Table Found");
        } else {
            createProuductDB();
        }
        if(ifexist_table("STOCK")){
            System.out.println("STOCK Table Found");
        }else{
            createStockDB();
        }
        if (ifexist_table("PURCHASE")) {
            System.out.println("PURCHASE Table Found");
        } else {
            createPurchaseDB();
        }
        if (ifexist_table("PURCHASEORDERPRODUCT")) {
            System.out.println("PURCHASEORDERPRODUCT Table Found");
        } else {
            createPurchaseOrderProductDB();
        }
        if (ifexist_table("GOODSRECIPT")) {
            System.out.println("GOODSRECIPT Table Found");
        } else {
            createGoodsReciptDB();
        }
        if (ifexist_table("GOODSRECIPTPRODUCT")) {
            System.out.println("GOODSRECIPTPRODUCT Table Found");
        } else {
            createGoodsReciptProductDB();
        }
        if (ifexist_table("INVOICE")) {
            System.out.println("INVOICE Table Found");
        } else {
            createInvoiceDB();
        }

        if (ifexist_table("INVOICEPRODUCT")) {
            System.out.println("INVOICEPRODUCT Table Found");
        } else {
            createInvoiceProductDB();
        }
        if (ifexist_table("CREDITNOTESR")) {
            System.out.println("CREDINOTESR Table Found");
        } else {
            createCreditnoteSRDB();
        }

        if (ifexist_table("CREDITNOTESRPDT")) {
            System.out.println("CREDITNOTESRPDT Table Found");
        } else {
            createCreditnoteSRPdtDB();
        }
        if (ifexist_table("DEBITNOTEPR")) {
            System.out.println("DEBITNOTEPR Table Found");
        } else {
            createDebitnotePRDB();
        }

        if (ifexist_table("DEBITNOTEPRPDT")) {
            System.out.println("DEBITNOTEPRPDT Table Found");
        } else {
            createDebitnotePRPdtDB();
        }
    }

    private static void createLoginDB() {
        System.out.println("Creating LOGIN Table");
        String str = "CREATE TABLE LOGIN "
                + "(USERNAME VARCHAR(20) NOT NULL PRIMARY KEY,"
                + "PASSWORD VARCHAR(20) NOT NULL)";

        Update(str);
        Update("insert into LOGIN (username,password) values ('admin','admin')");
    }

    private static void createGenericDB() {
        System.out.println("Creating Generic Table");
        String str = "CREATE TABLE GENERIC "
                + "(NAME VARCHAR(20) NOT NULL PRIMARY KEY)";

        Update(str);
    }

    private static void createCustomerDB() {
        System.out.println("Creating Customer Table");
        String str = "CREATE TABLE CUSTOMER "
                + "(Name VARCHAR(20) NOT NULL PRIMARY KEY,"
                + "Address VARCHAR(100) NOT NULL,"
                + "Place VARCHAR(50) NOT NULL,Phone VARCHAR(20) NOT NULL,"
                + "Area VARCHAR(20) NOT NULL,Tin VARCHAR(20) NOT NULL)";

        Update(str);
    }

    private static void createAreaDB() {
        System.out.println("Creating Area Table");
        String str = "CREATE TABLE AREA "
                + "(AREA VARCHAR(20) NOT NULL PRIMARY KEY)";

        Update(str);
    }

    private static void createCompanyDB() {
        System.out.println("Creating COMPANY Table");
        String str = "CREATE TABLE COMPANY "
                + "(Name VARCHAR(20) NOT NULL PRIMARY KEY,"
                + "Address VARCHAR(100) NOT NULL,"
                + "Place VARCHAR(50) NOT NULL,"
                + "Phone VARCHAR(20) NOT NULL UNIQUE,DL VARCHAR(20),"
                + "TIN VARCHAR(20) NOT NULL UNIQUE,CST VARCHAR(20))";

        Update(str);
    }

    private static void createStockDB() {
        System.out.println("Creating STOCK Table");
        String str = "CREATE TABLE STOCK "
                + "(Product VARCHAR(20) NOT NULL,"
                + "Batch INTEGER NOT NULL,"
                + "MRP REAL NOT NULL,"
                + "Brate REAL NOT NULL,"
                + "Expiry DATE NOT NULL,"
                + "Quantity INTEGER,"
                + "Free INTEGER,"
                + "FOREIGN KEY(Product) REFERENCES PRODUCT(Name))";
        Update(str);
    }

    private static void createProuductDB() {
        System.out.println("Creating PRODUCT Table");
        String str = "CREATE TABLE PRODUCT"
                + "(Name VARCHAR(20) PRIMARY KEY NOT NULL,"
                + "Company VARCHAR(20) NOT NULL,"
                + "Generic VARCHAR(20),"
                + "FOREIGN KEY(Company) REFERENCES COMPANY(Name))";
        Update(str);
    }

    private static void createPurchaseDB() {
        System.out.println("Creating PURCHASE Table");
        String str = "CREATE TABLE PURCHASE "
                + "(COMPANY VARCHAR(20) NOT NULL,"
                + "Orderno VARCHAR(10) NOT NULL PRIMARY KEY,"
                + "Orderdate DATE)";
        Update(str);
    }

    private static void createPurchaseOrderProductDB() {
        System.out.println("Creating PURCHASEORDERPRODUCT Table");
        String str = "CREATE TABLE PURCHASEORDERPRODUCT "
                + "(Orderno VARCHAR(10) NOT NULL,"
                + "Product VARCHAR(20) NOT NULL,"
                + "Quantity INTEGER,"
                + "FOREIGN KEY(Orderno) REFERENCES PURCHASE(Orderno))";
        Update(str);
    }

    private static void createInvoiceDB() {
        System.out.println("Creating INVOICE Table");
        String str = "CREATE TABLE INVOICE "
                + "(INVOICENO INTEGER NOT NULL PRIMARY KEY,"
                + "CNAME VARCHAR(20) NOT NULL,"
                + "INVOICEDATE DATE,"
                + "PDVALUE REAL,"
                + "MRPVALUE REAL,"
                + "FOREIGN KEY(CNAME) REFERENCES CUSTOMER(Name))";
        Update(str);
    }

    private static void createInvoiceProductDB() {
        System.out.println("Creating INVOICE Product Table");
        String str = "CREATE TABLE INVOICEPRODUCT "
                + "(INVOICENO INTEGER NOT NULL,"
                + "PNAME VARCHAR(20)  NOT NULL,"
                + "BATCH VARCHAR(10) NOT NULL,"
                + "Expdat VARCHAR(20),"
                + "QUANTITY INTEGER NOT NULL,"
                + "FREE INTEGER NOT NULL,"
                + "PTR VARCHAR(20),"
                + "PTS VARCHAR(20),"
                + "MRP VARCHAR(20),"
                + "TAX VARCHAR(20),"
                + "TAXAMT VARCHAR(20),"
                + "PDVALUE VARCHAR(20),"
                + "MRPVALUE VARCHAR(20),"
                + "FOREIGN KEY(INVOICENO) REFERENCES INVOICE(INVOICENO),"
                + "FOREIGN KEY(PNAME) REFERENCES PRODUCT(Name))";
        Update(str);
    }

    private static void createCreditnoteSRDB() {
        System.out.println("Creating CREDITNOTESR Table");
        String str = "CREATE TABLE CREDITNOTESR "
                + "(CRNO INTEGER NOT NULL PRIMARY KEY,"
                + "CNAME VARCHAR(20) NOT NULL,"
                + "CRDATE DATE,"
                + "FOREIGN KEY(CNAME) REFERENCES CUSTOMER(Name))";
        Update(str);
    }

    private static void createCreditnoteSRPdtDB() {
        System.out.println("Creating CREDITNOTESR Product Table");
        String str = "CREATE TABLE CREDITNOTESRPDT "
                + "(CRNO INTEGER NOT NULL PRIMARY KEY,"
                + "PNAME VARCHAR(20) UNIQUE NOT NULL,"
                + "BATCH VARCHAR(10) NOT NULL,"
                + "QUANTITY INTEGER NOT NULL,"
                + "FREE INTEGER NOT NULL,"
                + "FOREIGN KEY(CRNO) REFERENCES CREDITNOTESR(CRNO),"
                + "FOREIGN KEY(PNAME) REFERENCES PRODUCT(Name))";
        Update(str);
    }

    private static void createGoodsReciptDB() {
        System.out.println("Creating GOODSRECIPT Table");
        String str = "CREATE TABLE GOODSRECIPT "
                + "(Company VARCHAR(20) NOT NULL,"
                + "Orderno VARCHAR(10) NOT NULL,"
                + "Reciptno VARCHAR(10) NOT NULL PRIMARY KEY,"
                + "Reciptdate DATE,"
                + "FOREIGN KEY(Orderno) REFERENCES PURCHASE(Orderno))";
        Update(str);
    }

    private static void createGoodsReciptProductDB() {
        System.out.println("Creating GOODSRECIPTPRODUCT Table");
        String str = "CREATE TABLE GOODSRECIPTPRODUCT "
                + "(Product VARCHAR(20) NOT NULL,"
                + "Reciptno VARCHAR(10) NOT NULL,"
                + "Batch VARCHAR(10) NOT NULL,"
                + "Quantity INTEGER NOT NULL,"
                + "Free INTEGER NOT NULL,"
                + "MRP REAL NOT NULL,"
                + "BRATE REAL NOT NULL,"
                + "Expiry DATE,"
                + "FOREIGN KEY(Reciptno) REFERENCES GOODSRECIPT(Reciptno))";
        Update(str);
    }

    private static void createDebitnotePRDB() {
        System.out.println("Creating DEBITNOTEPR Table");
        String str = "CREATE TABLE DEBITNOTEPR "
                + "(Company VARCHAR(20) NOT NULL,"
                + "Dbno VARCHAR(10) NOT NULL PRIMARY KEY,"
                + "Reciptdate DATE)";
        Update(str);
    }

    private static void createDebitnotePRPdtDB() {
        System.out.println("Creating DEBITNOTEPRODUCT Table");
        String str = "CREATE TABLE DEBITNOTEPRPDT "
                + "(Product VARCHAR(20) NOT NULL,"
                + "Dbno VARCHAR(10) NOT NULL,"
                + "Batch VARCHAR(10) NOT NULL,"
                + "Quantity INTEGER NOT NULL,"
                + "Free INTEGER NOT NULL,"
                + "MRP REAL NOT NULL,"
                + "BRATE REAL NOT NULL,"
                + "Expiry DATE,"
                + "FOREIGN KEY(Dbno) REFERENCES DEBITNOTEPR(Dbno))";
        Update(str);
    }

    public static Database getInstance() {
        return db;
    }

    public static boolean connectmysql(String address, String db, String user, String pass) {
        String URL = "jdbc:mysql://" + address + "/" + db;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public static boolean ifexist_table(String dbname) {
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet table = dbm.getTables(null, null, dbname, null);
            if (!table.next()) {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;

    }

    public static boolean connectDerby(String dbname) {
        String ConnURL = "jdbc:derby:" + dbname + ";create=true";
        try {
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException ex) {
            System.out.println("Cant Create a connection to derby db");
            return false;
        }
        return true;
    }

    public static ResultSet Query(String q) {
        ResultSet res = null;
        try {
            Statement smt = conn.createStatement();
            System.out.println(q);
            res = smt.executeQuery(q);
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    public static boolean Update(String q) {
        System.out.println(q);
        try {
            Statement smt = conn.createStatement();
            int executeUpdate = smt.executeUpdate(q);
            System.out.println(executeUpdate + " rows changed");
            return true;
        } catch (SQLException ex) {
            System.err.println("Update Failure");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static PreparedStatement GetPreparedStmt(String stmt) {
        try {
            return conn.prepareStatement(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

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
        if (ifexist_table("GENERIC")){
            System.out.println("generic Table Found");
        }else{
            createGenericDB();
        }
        if (ifexist_table("CUSTOMER")){
            System.out.println("CUSTOMER Table Found");
        }else{
            createCustomerDB();
        }
        if (ifexist_table("AREA")){
            System.out.println("AREA Table Found");
        }else{
            createAreaDB();
        }
        if (ifexist_table("COMPANY")){
            System.out.println("COMPANY Table Found");
        }else{
            createCompanyDB();
        }
        if(ifexist_table("STOCK")){
            System.out.println("STOCK Table Found");
        }else{
            createStockDB();
        }
        if(ifexist_table("PRODUCT")){
            System.out.println("PRODUCT Table Found");
        }else{
            createProuductDB();
        }
        
        
        if(ifexist_table("PURCHASE")){
            System.out.println("PURCHASE Table Found");
        }else{
            createPurchaseDB();
        }
        if(ifexist_table("PURCHASEORDERPRODUCT")){
            System.out.println("PURCHASEORDERPRODUCT Table Found");
        }else{
            createPurchaseOrderProductDB();
        }
        if(ifexist_table("GOODSRECIPT")){
            System.out.println("GOODSRECIPT Table Found");
        }else{
            createGoodsReciptDB();
        }
        if(ifexist_table("GOODSRECIPTPRODUCT")){
            System.out.println("GOODSRECIPTPRODUCT Table Found");
        }else{
            createGoodsReciptProductDB();
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
                + "(Name VARCHAR(20) NOT NULL,Address VARCHAR(100) NOT NULL,Place VARCHAR(50) NOT NULL,Phone VARCHAR(20) NOT NULL,Area VARCHAR(20) NOT NULL)";

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
                + "Batch VARCHAR(10) NOT NULL,"
                + "MRP REAL NOT NULL,"
                + "Expiry DATE NOT NULL,"
                + "Quantity INTEGER,"
                + "Free INTEGER)";
        Update(str);
    }
    private static void createProuductDB(){
        System.out.println("Creating PRODUCT Table" );
        String str="CREATE TABLE PRODUCT"
                +"(Name VARCHAR(20) PRIMARY KEY NOT NULL,"
                + "Company VARCHAR(20) NOT NULL,"
                + "Generic VARCHAR(20),"
                + "FOREIGN KEY(Company) REFERENCES COMPANY(Name))";
        Update(str);
    }
    
    private static void createPurchaseDB(){
        System.out.println("Creating PURCHASE Table" );
        String str="CREATE TABLE PURCHASE "
                +"(COMPANY VARCHAR(20) NOT NULL,"
                + "Orderno VARCHAR(10) NOT NULL PRIMARY KEY,"
                + "Orderdate DATE)";
        Update(str);
    }
    private static void createPurchaseOrderProductDB(){
        System.out.println("Creating PURCHASEORDERPRODUCT Table" );
        String str="CREATE TABLE PURCHASEORDERPRODUCT "
                +"(Orderno VARCHAR(10) NOT NULL,"
                + "Product VARCHAR(20) NOT NULL,"
                + "Quantity INTEGER,"
                + "FOREIGN KEY(Orderno) REFERENCES PURCHASE(Orderno))";
        Update(str);
    }
    private static void createGoodsReciptDB(){
        System.out.println("Creating GOODSRECIPT Table" );
        String str="CREATE TABLE GOODSRECIPT "
                +"(Company VARCHAR(20) NOT NULL,"
                + "Orderno VARCHAR(10) NOT NULL,"
                + "Reciptno VARCHAR(10) NOT NULL PRIMARY KEY,"
                + "Reciptdate DATE,"
                + "FOREIGN KEY(Orderno) REFERENCES PURCHASE(Orderno))";
        Update(str);
    }
    private static void createGoodsReciptProductDB(){
        System.out.println("Creating GOODSRECIPTPRODUCT Table" );
        String str="CREATE TABLE GOODSRECIPTPRODUCT "
                +"(Product VARCHAR(20) NOT NULL,"
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

    public static Database getInstance() {
        return db;
    }

    public static boolean connectmysql(String address, String db, String user, String pass) {
        String URL = "jdbc:mysql://" + address + "/" + db;
        try {
            conn = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException ex) {
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
    public static PreparedStatement GetPreparedStmt(String stmt){    
        try {
            return conn.prepareStatement(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

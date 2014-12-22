/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
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
    }

    private static void createLoginDB() {
        System.out.println("Creating LOGIN Table");
        String str = "CREATE TABLE LOGIN "
                + "(USERNAME VARCHAR(20) NOT NULL,"
                + "PASSWORD VARCHAR(20) NOT NULL)";

        Update(str);
        Update("insert into LOGIN (username,password) values ('admin','admin')");
    }
    private static void createGenericDB() {
        System.out.println("Creating Generic Table");
        String str = "CREATE TABLE generic "
                + "(generic_name VARCHAR(20) NOT NULL UNIQUE)";

        Update(str);
    }
    private static void createCustomerDB() {
        System.out.println("Creating Customer Table");
        String str = "CREATE TABLE CUSTOMER "
                + "(Name VARCHAR(20) NOT NULL,Address VARCHAR(100) NOT NULL,Place VARCHAR(50) NOT NULL,Phone VARCHAR(20) NOT NULL,Area VARCHAR(20) NOT NULL)";

        Update(str);
    }

    public static Database getInstance() {
        return db;
    }

    public static int connect(String address, String db, String user, String pass) {
        String URL = "jdbc:mysql://" + address + "/" + db;
        try {
            conn = DriverManager.getConnection(URL, user, pass);
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
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
}

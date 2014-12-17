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
    
    private Database(){
        conn = null;
    }
    public static void setupDatabase(){
        if(!ifexistdb("LOGIN"))
            createLoginDB();
    }
    private static void createLoginDB(){
        String str = "CREATE TABLE LOGIN "
        + "(USERNAME VARCHAR(20) NOT NULL,"
        + "PASS VARCHAR(20) NOT NULL)";
        
        try {
            Statement smt = conn.createStatement();
            smt.executeUpdate(str);
        } catch (SQLException ex) {
            System.out.println("Can't Create Table Login"+ex.getMessage());
        }
    }
    public static Database getInstance(){
        return db;
    }
    public static int connect(String address,String db,String user,String pass){
        String URL = "jdbc:mysql://"+address+"/"+db;
        try {
            conn = DriverManager.getConnection(URL,user,pass);
        } catch (SQLException ex) {
            return 1;
        }
        return 0;
    }
    public static boolean ifexistdb(String dbname){
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet table = dbm.getTables(null, null, dbname, null);
            if(!table.next()){
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
        return true;
        
    }
    public static boolean connectDerby(String dbname){
        String Driver="org.apache.derby.jdbc.EmbeddedDriver";
        String ConnURL= "jdbc:derby:" + dbname+";create=true";
        try{
            conn = DriverManager.getConnection(ConnURL);
        }catch(SQLException ex){
            System.out.println("Cant Create a connection to derby db");
            return false;
        }
        return true;
    }
    
    public static boolean authenticate(String User,String Pass){
        System.out.println("Loging in with "+User+" "+Pass);
        return ifexistdb("LOGIN");
    } 
    public static boolean check(){
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }catch(java.lang.ClassNotFoundException ex){
            System.err.println("Class Not Found");
            return false;
        }
        return true;
    }
    
}

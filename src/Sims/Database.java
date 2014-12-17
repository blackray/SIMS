/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    
}

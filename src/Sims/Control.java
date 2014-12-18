/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author amalcs
 */
public class Control {
    private static boolean logedin;
    private static final Control Instance = new Control();

    private Control() {
        logedin = false;
    }
    public static Control getInstance(){
        return Instance;
    }
    public static boolean Authenticate(String username,String password){
        ResultSet Query = Database.Query("select PASSWORD from LOGIN where USERNAME='"+username+"'");
        if(Query == null){
            return false;
        }
        try {
            
            while(Query.next()){
                String s=Query.getString("PASSWORD");
                System.out.println(s);
                if(s.equals(password)){
                    logedin = true;
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Authentication Failure");
            return false;
        }
        return false;
    }
    
}

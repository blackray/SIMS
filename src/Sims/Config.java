/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sims;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.krb5.JavaxSecurityAuthKerberosAccess;

/**
 *
 * @author amalcs
 */
public class Config {

    private static String db_connection_type;
    private static String db_connection_address;
    private static String db_connection_username;
    private static String db_connection_password;
    private static Config obj = null;

    private Config() {
        db_connection_type = "derby";
    }

    public static Config GetInstance() {
        if (obj == null) {
            obj = new Config();
        }
        return obj;
    }

    public void Parse() {
        try {
            FileReader fr = new FileReader("config");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                int pos = line.indexOf(' ');
                String sub1 = line.substring(0, pos);
                switch (sub1) {
                    case "dbtype":
                        db_connection_type = line.substring(pos + 1, line.length());
                        System.out.println("dbtype : "+db_connection_type);
                        break;
                    case "dbaddress":
                        db_connection_address = line.substring(pos + 1,line.length());
                        System.out.println("dbaddress : "+db_connection_address);
                        break;
                    case "dbusername":
                        db_connection_username = line.substring(pos + 1,line.length());
                        System.out.println("dbusername : "+db_connection_username);
                        break;
                    case "dbpassword":
                        db_connection_password = line.substring(pos + 1,line.length());
                        System.out.println("dbpassword : "+db_connection_password);
                        break;
                }
                line = br.readLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Config File Not Found \nUsing Default Configuration");
            try {
                PrintWriter writer = new PrintWriter("config");
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String Get_DB_Connection_type() {
        return db_connection_type;
    }
    public static String Get_DB_Connection_Address(){
        return db_connection_address;
    }
    public static String Get_DB_Connection_Username(){
        return db_connection_username;
    }
    public static String Get_DB_Connection_Password(){
        return db_connection_password;
    }

}

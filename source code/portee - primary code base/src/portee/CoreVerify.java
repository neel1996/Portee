/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static portee.CoreFile.DB_URL;


/**
 *
 * @author SVNPC
 */
public class CoreVerify{
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    static final String user = "root";
    static final String pass = "";
    static String fir = "";
   
    CoreVerify(File file, VBox root, Stage primaryStage,ProgressBar pgb, Label label, Label label_3, Label label_4){
        
        Connection con = null;
        Statement st = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        FileReader fr = null;
        BufferedReader br = null;
        String getF = null;
        ArrayList arr = null;
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("DATABASE CONNECTED _ 01 !!!!");
            st = con.createStatement();
            
            CoreFile CF = new CoreFile();
            
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CF = (CoreFile) in.readObject();
            
            System.out.print("\n\nName : "+CF.ename+"\n\n");
            
            String xx = CF.ename;
            String yy = CF.email;
            String zz = CF.mob;
            
            Core c = new Core(xx, yy, zz);
            
            MacVerify mc = new MacVerify();
            int g = mc.MacGet();
            
            if( g==0 ){
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("@\n@\n@@@@\nFile erased due to security\n\n");
            }
            else if( g==3 ){
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("@\n@\n@@@@\nFile erased due to security\n\n");
            }
            else if( g==1 ){
                Splash ss = new Splash();
                ss.mainMod(primaryStage, pgb, label_3, label_4);
            }
            in.close();
            fileIn.close();
           
            System.out.println("\n\nFILE PRESENT\n\n"+"inputLine");
        
        } catch (Exception ex) {
            System.out.print("\n\nMISMATCH\n\n");
            Splash s = new Splash();
            try {
                s.loginLoad(root, primaryStage, pgb, label, label, label);
            } catch (Exception ex1) {
                Logger.getLogger(CoreVerify.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            
        }
        
        
    }

}

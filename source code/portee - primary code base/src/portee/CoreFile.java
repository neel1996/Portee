/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.*;
import java.net.*;

/**
 *
 * @author SVNPC
 */


public class CoreFile implements Serializable {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    static final String user = "root";
    static final String pass = "";
    static String fir = "";
    
    String ename;
    String email;
    String mob;

    public int getCoreData(String uname, String pname) throws SQLException, FileNotFoundException, IOException{
        Socket s = new Socket("127.0.0.1",80);
        
        try{
            if( s.isConnected() ){
                s.close();
            }
        }
        catch(Exception e){
            return 2;
        }
        
        System.out.print("\n\nI'M HERE YODA\n\n");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int ch = 1;
        CoreFile cff = new CoreFile();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("DATABASE CONNECTED !!!!");
            
            PreparedStatement ps = null;
            
            stmt = con.createStatement();
	    String fir_1 = "SELECT id,password FROM usertab WHERE email='"+uname+"';";
            ps = con.prepareStatement(fir_1);
            ResultSet rs_1 = ps.executeQuery();
            
            
            int newId = 0;
            String newPass = "";
            
            rs = stmt.executeQuery(fir_1);
            
            if( !rs_1.next() ){
                return 3;
            }
            
           
            if(rs.next()){
                newId = rs.getInt("id");
                newPass = rs.getString("password");
            }
            
            System.out.println(""+uname+"\n\n"+pname);
            
            if( newPass.equals(pname)){
                System.out.println("PASSED");
            }
            else{
                return 4;
            }
            
            ename = "";
            email = "";
            mob = "";
            
            String str = "SELECT enc_name,enc_email,enc_mobile FROM core WHERE id="+newId+";";
            rs = stmt.executeQuery(str);
            
            if( rs.next() ){
                cff.ename = rs.getString("enc_name");
                cff.email = rs.getString("enc_email");
                cff.mob = rs.getString("enc_mobile");
            }
                   
            System.out.println(ename+"\n"+email+"\n"+mob);
            System.out.println("ASASASA");     
            
            
        }catch(Exception e){
            e.printStackTrace();
            return 5;
        }
        finally{
            con.close();            
        }
        
        File file = new File("C:\\TEST_1\\core.dll");
            
        file.createNewFile();

        System.out.println("\n\n"+ename.getBytes()+"\n"+email.getBytes()+"\n"+mob.getBytes());
        
        
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oj = new ObjectOutputStream(fos);
        
        oj.writeObject(cff);
            
        
        return 1;
        
    }  
    
    
}


 /*byte[] data = new byte[] {'H','I'};
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();*/
package portee;

import java.io.*;
import java.math.BigInteger;
import java.security.Key;
import java.sql.*;
import java.util.*;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Core{
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    static final String user = "root";
    static final String pass = "";
    static String fir = "";
    
    Core(String xx, String yy, String zz){
        
        System.out.print("\n\nAPPLE\n\n");
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        int id = 0;
        String dat_1 = null;
        String dat_2 = null;
        String dat_3 = null;
        String dat_4 = null;
                
        try{
            
            AES aes = new AES();
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, user, pass);
            System.out.println("DATABASE CONNECTED !!!!");
            
            stmt = con.createStatement();
	    String fir_1 = "SELECT prev_key FROM core WHERE enc_email='"+yy+"';";
            
            ps = con.prepareStatement(fir_1);
            rs = ps.executeQuery();
            
            if( !rs.next() ){
               
                System.out.print("\n\nMIS-MATCH-CORE-ERR001\n\n");
                
            }
            else{
                 fir = rs.getString("prev_key");
            }
     
            System.out.print("\n\nDecrypting...\n\n");
            System.out.print("\n\nStrings are\n\n"+xx+"\n"+yy+"\n"+zz+"\n\n");
            
            String dx = aes.decrypt(xx,fir);
            String dy = aes.decrypt(yy,fir);
            String dz = aes.decrypt(zz,fir);
            
            System.out.print("\n\nStrings are\n\n"+dx+"\n"+dy+"\n"+dz);
            
           
        }catch(Exception e){e.printStackTrace();}
        finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }

    public String[] getData(String x, String y, String z)throws Exception{
        AES aes = new AES();
        //String arr[] = null;
       
            String a = aes.decrypt(x,fir);
            String b = aes.decrypt(y,fir);
            String c = aes.decrypt(z,fir);
            
            String arr[] = {a,b,c};
            return arr;
       
    }
    
    public static void main(String[] args) throws Exception{
        //new Core();
        //byte[] enc_1, enc_2, enc_3, enc_4;
                        
        
    }
    
}

class AES {
    private static String ALGO = "AES";
    private static byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
   // AES(){}
   // AES(String recK) throws IOException{
        //byte[] keyValue;
       // keyValue = new sun.misc.BASE64Decoder().decodeBuffer(recK);
       // System.out.println("     \n\n"+keyValue);
  //  }

public String encrypt(String Data, String k) throws Exception {
        System.out.println("\n\n\t"+k);
        char[] temp = k.toCharArray();
        Arrays.fill(temp, '\u0000');
        Arrays.fill(keyValue, (byte) 0);
        
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData, String k) throws Exception {
        
        System.out.println("\n\n\t"+k);
        char[] temp = k.toCharArray();
        Arrays.fill(temp, '\u0000');
        Arrays.fill(keyValue, (byte) 0);
        
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private static Key generateKey() throws Exception {
        String k = "";
        Key key = new SecretKeySpec(keyValue, ALGO);        
        return key;
}
}

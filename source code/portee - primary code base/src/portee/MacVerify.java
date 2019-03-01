/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portee;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import static portee.CoreFile.DB_URL;
import sun.misc.BASE64Encoder;

/**
 *
 * @author SVNPC
 */
public class MacVerify {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/user";
    static final String user = "root";
    static final String pass = "";
    static String fir = "";
    
    public int MacGet(){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rs_1 = null;
        System.out.print("\n\nINSIDE MAC PAA \n\n");
        InetAddress ip;
	try {
                CoreFile CF = new CoreFile();
                int idd = 0;
                FileInputStream fileIn = new FileInputStream("C://TEST_1//core.dll");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                CF = (CoreFile) in.readObject();

                System.out.print("\n\nName : "+CF.ename+"\n\n");

                String yy = CF.email;
                
                /////////////DATABASE
                
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL,user,pass);
                st = con.createStatement();
                String q_1 = "SELECT id FROM core WHERE enc_email='"+yy+"'";
                
                rs = st.executeQuery(q_1);
                
                if( rs.next() ){
                    idd = rs.getInt("id");
                }
                
                rs_1 = st.executeQuery("SELECT mac,mac_count FROM usertab WHERE id="+idd+"");
                
                int ch = 0;
                String mac_s = "";
                
                if( rs_1.next() ){
                    ch = rs_1.getInt("mac_count");
                    mac_s = rs_1.getString("mac");
                }
                
                
                System.out.print("\n\nINSIDE MAC DEE\n\n");
                if ( ch == 0 ){
                    ip = InetAddress.getLocalHost();
                    System.out.println("Current IP address : " + ip.getHostAddress());

                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                    byte[] mac = network.getHardwareAddress();

                    System.out.print("Current MAC address : ");

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    System.out.println(sb.toString());
                    ch++;
                    st.execute("UPDATE usertab SET mac='"+sb.toString()+"',mac_count="+ch+" WHERE id="+idd+"");
                    return 1;
                }
                else if( ch!= 0 && ch<5 ){
                    ip = InetAddress.getLocalHost();
                    NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                    byte[] mac = network.getHardwareAddress();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                    }
                    String dum = sb.toString();
                    if( !mac_s.equals(dum) ){
                        ch++;
                        st.execute("UPDATE usertab SET mac='"+sb.toString()+"',mac_count="+ch+" WHERE id="+idd+"");
                    }
                    return 1;
                }
                else if( ch > 5 ){
                    return 0;
                }
                
                /////////////////////

                
	} catch (Exception e) {
		e.printStackTrace();
                return 3;
	}
        
        return 4;   
    }
    
    public int MacGetLogin(String x){
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rs_1 = null;
        InetAddress ip;
        int macThreshold = 5;
        System.out.print("\n\nINSIDE MAC PPAP \n\n");
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(DB_URL,user,pass);
                st = con.createStatement();
                String q_1 = "SELECT mac,mac_count FROM usertab WHERE email='"+x+"'";
                rs_1 = st.executeQuery(q_1);
                
                int mac_ct = 0;
                String mac_ad = "";
                
                if( rs_1.next() ){
                    mac_ct = rs_1.getInt("mac_count");
                    mac_ad = rs_1.getString("mac");
                }
                
                System.out.print("\n\nINSIDE MAC DEE\n\n");
                
                ip = InetAddress.getLocalHost();
                System.out.println("Current IP address : " + ip.getHostAddress());

                NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                byte[] mac = network.getHardwareAddress();

                System.out.print("Current MAC address : ");

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                System.out.println(sb.toString());
                
                if( mac_ct < macThreshold ){
                    
                    if( !mac_ad.equals(sb.toString()) ){
                        mac_ct++;
                        st.execute("UPDATE usertab SET mac='"+sb.toString()+"',mac_count="+mac_ct+" WHERE email='"+x+"'");
                    }
                    
                }
                else{
                    return 0;
                }
                
                return 1;   
        
        }catch(Exception e){
            e.printStackTrace();
            return 2;
        }
        //return 3;
    }
}

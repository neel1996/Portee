/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portee;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.net.www.http.HttpClient;
/**
 *
 * @author SVNPC
 */
public class TimeStampClient {

    
    public int getTS(){
        
        try{
        URL yahoo = new URL("http://localhost/time.php");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yahoo.openStream(),"UTF-8"));
        String inputLine;
        String ip="";
        while ((inputLine = in.readLine()) != null){ 
            ip += inputLine;
            System.out.println("\n\nPHPCALL "+inputLine);   
        }
        
        while( in.readLine()!=null ){
            ip = ip + in.readLine();
        }
        
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date());
        System.out.print("\nSEE --> "+timeStamp+"\t\t"+ip);
        
        
        if( timeStamp.equals(ip) ){
            System.out.print("\n\nTIME MATCH\n\n");
            return 1;
        }
        }catch(Exception e){
            return 0;
        }
        
        return 0;
    }
    
    
}

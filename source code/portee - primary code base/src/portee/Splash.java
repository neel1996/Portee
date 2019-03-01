package portee;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.swing.SwingUtilities;

//import javax.swing.plaf.ProgressBarUI;
import javax.xml.soap.Text;
import sun.misc.BASE64Encoder;
//import jdk.internal.org.objectweb.asm.Handle;
//import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 * Created by SVNPC on 08-02-2017.
 */
public class Splash extends Application implements Serializable{
    Stage st;
    Scene scene;
    Label lab, x, sub, msg;
    Label lab_,label_1,label_2,label_3,label_4,label_5;
    TextField user;
    PasswordField pass;
    Stage primaryStage;
    VBox root;
    int rep;
    
    private String theme1Url = getClass().getResource("Main.css").toExternalForm();
    private String theme2Url = getClass().getResource("log.css").toExternalForm();
    private String theme3Url = getClass().getResource("Mod.css").toExternalForm();
    
     Thread td_1;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        String[] noti = {"Loading...","Verifying user...","Gathering info...","Success"};
        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        VBox root = new VBox();
        Scene scene = new Scene(root, 1100, 650);

        Label label = new Label("Loading...");
        Label label_1 = new Label("Checking required files...");
        Label label_2 = new Label("Verifying user...");
        Label label_3 = new Label("Accessing data...");
        Label label_4 = new Label("Loading main module...");
        Label label_5 = new Label("Collecting data & loading module...");
        
        
        label_1.setVisible(false);
        label_2.setVisible(false);
        label_3.setVisible(false);
        label_4.setVisible(false);
        label_5.setVisible(false);
        

        ProgressBar progressBar = new ProgressBar(0.1);
        ProgressIndicator pi = new ProgressIndicator();
        progressBar.setPrefWidth(500);

        progressBar.setTranslateX(50);
        progressBar.setTranslateY(400);
        label.setTranslateX(50);
        label.setTranslateY(410);
        
        label_1.setTranslateX(50);
        label_1.setTranslateY(410);
        
        label_2.setTranslateX(50);
        label_2.setTranslateY(410);
        
        label_3.setTranslateX(50);
        label_3.setTranslateY(410);
        
        label_4.setTranslateX(50);
        label_4.setTranslateY(410);
        
        label_5.setTranslateX(50);
        label_5.setTranslateY(410);

        label.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
            
        });
       
        Thread thr = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try{
                    
                    Thread.sleep(1500);
                    progressBar.setProgress(0.2);
                    label.setVisible(true);
                    //thr_1.start();
                    
                }catch(Exception e){}
                
            }
      
        });
        

        primaryStage.setScene(scene);
        root.getChildren().addAll(progressBar, label, label_1, label_2, label_3, label_4, label_5);
        
        try{
            scene.getStylesheets().add(theme1Url);
        }catch(Exception e){}
        
        
        //if (scene.getStylesheets() != null && getClass() != null && getClass().getResource("view/Main.CSS") != null) {
        //    scene.getStylesheets().add(getClass().getResource("Main.CSS").toExternalForm() );
        //}
        
        
        File file = new File("C:\\TEST_1");
        
        if( !file.exists() ){
            file.mkdir();         
        }
        
        file = new File("C:\\TEST_1\\core.dll");
       
        
        if( !file.exists() && !file.isDirectory() ){
            
            Splash s = new Splash();
            
            
            try {
                FirstWait fw = new FirstWait(root,primaryStage,progressBar,label,label_3,label_4);
                //FirstWait fw = new FirstWait(root,primaryStage,progressBar,label);
              //  s.loginLoad(root,primaryStage,progressBar,label);
            } catch (Exception ex) {
                //Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
        }
        
        else{
            TimeStampClient tss = new TimeStampClient();
            int ch = tss.getTS();
            
            if(ch==0){
                progressBar.setProgress(0.8);
                label.setText("TimeStamp mismatch: \nYour system time is not in synch with the server");
            }
            else if(ch==1){
                progressBar.setProgress(0.8);
                label.setText("User machine count exceeded\nYou can't install this software anymore in a new machine\nBoooo!!!");

                System.out.println("FILE PRESENT\n\n"+"inpurLine");

                CoreVerify cv = new CoreVerify(file, root, primaryStage, progressBar, label, label_3, label_5);
            }
            //mainMod(primaryStage, progressBar, label_3, label_5);
            
        }
    
        primaryStage.show();
        thr.start();
      
    }

    public void loginLoad(VBox root, Stage prm, ProgressBar progressBar, Label label, Label lab1, Label lab2)throws Exception{
        //int rep;
        //String theme2Url = getClass().getResource("Sub.css").toExternalForm();
        System.out.print("ASAS");
        prm.toBack();
        root.setEffect(new GaussianBlur(3.0));
        Stage st = new Stage();
        st.setAlwaysOnTop(true);
        st.toFront();
        st.initStyle(StageStyle.UNDECORATED);
        VBox root1 = new VBox();
        Scene sc = new Scene(root1, 650,500);
        
        x = new Label("X");
        lab = new Label("email ID");
        lab_ = new Label("Password");
        user = new TextField();
        pass = new PasswordField();
        sub = new Label("login");
        msg = new Label("Wrong email or password !!!");
        sub.setAlignment(Pos.CENTER);

        msg.setVisible(false);
        x.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                st.close();
                prm.close();
                //ti.cancel();
            }
        });
        
        user.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                msg.setVisible(false);
            }
        });
        pass.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                msg.setVisible(false);
            }
        });
         
         sub.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                
                

                CoreFile cf = new CoreFile();
                progressBar.setProgress(0.4);
                label.setText("User login...");
                String x = user.getText().toString();
                String y = pass.getText().toString();
                
                
                try {
                  
                  
                  ////////////MAC//////////////////////
                    MacVerify mc = new MacVerify();
                    int g = 0;
                    g = mc.MacGetLogin(user.getText().toString());

                    if( g==1 ){
                        rep = cf.getCoreData(x, y);
                    }
                    else if( g==0 ){
                      File file = new File("C:\\TEST_1\\core.dll");
                      boolean b = file.delete();
                      msg.setVisible(true);
                      msg.setText("Your User machine count exceeded !!!");
                    }
                    else if( g==2 ){
                      msg.setVisible(true);
                      msg.setText("Error contacting Server !!!");
                      File file = new File("C:\\TEST_1\\core.dll");
                      file.getAbsoluteFile().delete();
                    }

                    if( x.equals("") || y.equals("") ){
                        msg.setVisible(true);
                        msg.setText("Invalid user credentials !!!");
                    }
                   //////////MAC///////////////////
                  
                  System.out.print("\n\nPASS : "+rep);
                  
                    switch (rep) {
                      case 5:
                          System.out.print("\n\nDB CONNECTION LOSS\n");
                          msg.setVisible(true);
                          msg.setText("Server Error !!! Try again later");
                      case 4:
                          System.out.print("\n\n\nWrong password");
                          user.setText("");
                          pass.setText("");
                          msg.setText("Wrong password !!!");
                          msg.setVisible(true);
                          break;
                      case 3:
                          System.out.print("Error : Login problem !!! ");
                          msg.setText("Wrong credentials !!!");
                          user.setText("");
                          pass.setText("");
                          msg.setVisible(true);
                          break;
                      case 2:
                          msg.setVisible(true);
                          msg.setText("Server Error !!! Try again later");
                          break;
                      case 1:
                          mainMod(prm, progressBar, label_3, label_5);
                          st.close();
                          break;
                      default:
                          break;
                  }
                  
                } catch (Exception ex) {
                    user.setText("");
                    pass.setText("");
                    msg.setVisible(true);
                    msg.setText("Server Down!!! try again later");
                }
            }
        });
         
        

        setPos();

        sub.getStyleClass().add("sub");
        x.getStyleClass().add("x-class");
        msg.getStyleClass().add("msg-class");
        
        try{
            sc.getStylesheets().add(theme2Url);
        }catch(Exception e){}
        
        st.setScene(sc);
        root1.getChildren().addAll(lab,lab_,user,pass,msg,x,sub);
        st.show();
    }
    
    public void warnMe(String g){
        
    }
    
    public void mainMod(Stage st, ProgressBar pbb, Label l_1, Label l_2){
        
        Label l1 = new Label("Home");
        Label l2 = new Label("About Me");
        Label l3 = new Label("Help");
        Label l4 = new Label("Close");
        
        Label abt = new Label("ASASAS");
        Label abt_1 = new Label("ASASAS");
        Label abt_2 = new Label("ASASAS");
        
        String img[] = {
            "view/1.jpg",
            "view/2.jpg",
            "view/3.jpg",
            "view/4.jpg",
            "view/5.jpg",
            "view/6.jpg",
            "view/7.jpg",
            "view/8.jpg",
            "view/9.jpg",
            "view/10.jpg",
            "view/11.jpg"
        };
        
        
        
        l4.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                st.close();
                //ti.cancel();
            }
        });
        
        
        Random r = null;
        int i = ThreadLocalRandom.current().nextInt(0, 12);
        Image image = new Image(img[i],550,400,false,false);
        
        ImageView t1 = new ImageView();
        t1.setImage(image);
        
        abt.setVisible(false);
        abt_1.setVisible(false);
        abt_2.setVisible(false);
        
        l1.setPrefSize(200,50);
        l2.setPrefSize(200,50);
        l3.setPrefSize(200,50);
        l4.setPrefSize(200,50);
        
        l1.getStyleClass().add("active_1");
        t1.getStyleClass().add("text");
        
        l1.getStyleClass().add("label_1");
        l2.getStyleClass().add("label_1");
        l3.getStyleClass().add("label_1");
        l4.getStyleClass().add("label_1");
        
        t1.setTranslateX(250);
        t1.setTranslateY(-465);
        
        abt.setTranslateX(250);
        abt.setTranslateY(-830);
        abt_1.setTranslateX(250);
        abt_1.setTranslateY(-800);
        abt_2.setTranslateX(250);
        abt_2.setTranslateY(-770);
        
        abt.setPrefWidth(400);
        abt.setPrefHeight(1000);
        
       // /*
       
        l1.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                
                abt.setVisible(false);
                abt_1.setVisible(false);
                abt_2.setVisible(false);  
                
                t1.setVisible(true);
                
                l1.getStyleClass().add("active");
                l2.getStyleClass().remove("active");
                l3.getStyleClass().remove("active");
            }
        });
                
        l2.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                ObjectInputStream in = null;
                try {
                    CoreFile CF = new CoreFile();
                    File file = new File("C:\\TEST_1\\core.dll");
                    FileInputStream fileIn = new FileInputStream(file);
                    in = new ObjectInputStream(fileIn);
                    CF = (CoreFile) in.readObject();
                    System.out.print("\n\nName : "+CF.ename+"\n\n");
                    String x = CF.ename;
                    String y = CF.email;
                    String z = CF.mob;
                    Core cc = new Core(x,y,z);
                    String xx[] = cc.getData(x, y, z);
                    t1.setVisible(false);
                    
                    l1.getStyleClass().remove("active_1");
                    l1.getStyleClass().remove("active");
                    l3.getStyleClass().remove("active");
                    l2.getStyleClass().add("active");
                    
                    abt.setStyle("-fx-background:none; -fx-text-fill:#1492BC; -fx-font-size:30px; -fx-border:none;");
                    abt_1.setStyle("-fx-background:none; -fx-text-fill:#1492BC; -fx-font-size:30px; -fx-border:none;");
                    abt_2.setStyle("-fx-background:none; -fx-text-fill:#1492BC; -fx-font-size:30px; -fx-border:none;");
                    
                    abt.setVisible(true);
                    abt_1.setVisible(true);
                    abt_2.setVisible(true);
                    abt.setText("Name : "+xx[0]);
                    abt_1.setText("Email : "+xx[1]);
                    abt_2.setText("Mobile : "+xx[2]);
                    
                } catch (Exception ex) {
                    Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        
        l3.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                abt.setText("PORTEE.com");
                abt_1.setText("supportme@helpport.in");
                abt_2.setText("+91 785 12 56869");
                
                l1.getStyleClass().remove("active_1");
                l1.getStyleClass().remove("active");
                l2.getStyleClass().remove("active");
                l3.getStyleClass().add("active");
            }
        });
     //   */
        
        t1.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                
                int i = ThreadLocalRandom.current().nextInt(0, 11);
                Image image = new Image(img[i],550,400,false,false);
                t1.setImage(image);
            }
        });
        
        //t1.setFitWidth(100);
        //t1.setFitHeight(100);
        //t1.setPreserveRatio(true);
        
        
        
        VBox vb = new VBox();
        Scene sc = new Scene(vb,850,530);
        Stage stt = new Stage();
        System.out.print("\n\nMain Module Loaded\n\n");
        stt.alwaysOnTopProperty();
        Thread td = new Thread( new Runnable(){
            @Override
            public void run() {
                
                try{
                    Thread.sleep(1200);
                    l_1.setVisible(false);
                    l_2.setVisible(true);
                    pbb.setProgress(1.0);
                    st.close();
                }catch(Exception e){}
                
            }   
        
        });
        
        try{
            sc.getStylesheets().add(theme3Url);
        }catch(Exception e){}
        
        td.start();
        st.setScene(sc);
        vb.getChildren().addAll(l1,l2,l3,l4);
        vb.getChildren().addAll(t1,abt,abt_1,abt_2);
        st.show();
        //stt.show();
        
        
    }
    
    
    public void setPos(){
        lab.setTranslateX(90);
        lab.setTranslateY(115);
        lab_.setTranslateX(90);
        lab_.setTranslateY(175);
        msg.setTranslateX(160);
        msg.setTranslateY(140);
        
        
        user.setMaxWidth(250);
        pass.setMaxWidth(250);

        user.setTranslateX(270);
        user.setTranslateY(20);
        pass.setTranslateX(270);
        pass.setTranslateY(80);

        x.setTranslateX(580);
        x.setTranslateY(-130);

        sub.setTranslateX(270);
        sub.setTranslateY(150);
    }

    public  static  void  main(String args[]){
        launch(args);
    }
}

class FirstWait{

    public VBox root;
    public Stage prm;
    public ProgressBar progressBar;
    public Label label, lab1, lab2;
    
    FirstWait(VBox root_1, Stage prm_1, ProgressBar progressBar_1, Label label_1, Label label_2, Label label_3){
        root = root_1;
        prm = prm_1;
        progressBar = progressBar_1;
        label = label_1;
        this.lab1 = label_2;
        this.lab2 = label_3;
        this.run_1();
    }
    
    
    public void run_1() {
        
        
       
            try {
               
                progressBar.setProgress(0.5);
                label.setText("Verifying user...");
                Splash s = new Splash();
                s.loginLoad(root, prm, progressBar, label, lab1, lab2);
            } catch (InterruptedException e) {} catch (Exception ex) {
                Logger.getLogger(FirstWait.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
    }
}


class MainLoad{
    
    MainLoad(VBox root_1, Stage prm_1){
        Stage st = new Stage();
        st.setAlwaysOnTop(true);
        VBox root1 = new VBox();
        Scene sc = new Scene(root1, 650,500);
        
        Label l = new Label("ASAS");
        root1.getChildren().add(l);
        
        prm_1.close();
        st.show();
    }
    
}
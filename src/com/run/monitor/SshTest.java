package com.run.monitor;  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.util.Scanner;  
  
import ch.ethz.ssh2.Connection;  
import ch.ethz.ssh2.Session;  
import ch.ethz.ssh2.StreamGobbler;  
public class SshTest  
  
{  
  
    public static void main(String[] args)  
  
    {  
  
//       String hostname = "you ip adress";  
//  
//       String username = "root";  
//  
//       String password = "root";  
    	
    	String ip = "192.168.160.128";
    	String user = "root";
    	String password = "light";
    	String command = "JPS";
         
       boolean  flag = true;  
         
       Scanner in = new Scanner(System.in);  
         
       try{  
           Connection conn = new Connection(ip);  
           conn.connect();  
           boolean isAuthenticated = conn.authenticateWithPassword(user, password);  
           if (isAuthenticated == false)  
              throw new IOException("Authentication failed.");  
           Session sess = conn.openSession();  
  
           for(int i = 0;i<1;i++){  
               sess.execCommand(in.nextLine());  
               System.out.println("Result::");  
               InputStream stdout = new StreamGobbler(sess.getStdout());  
               BufferedReader br = new BufferedReader(new InputStreamReader(stdout));  
               while (true)  
               {  
                  String line = br.readLine();  
                  if (line == null)  
                      break;  
                  System.out.println(line);  
                  sess.close();  
                  conn.close();  
               }  
           }   
  
            
             
           //System.out.println("ExitCode: " + sess.getExitStatus());  
            
  
       }  
       catch (IOException e)  
       {  
           e.printStackTrace(System.err);  
           System.exit(2);  
       }  
    }  
} 
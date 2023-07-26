/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emsg4v1;

import java.sql.*;

/**
 *
 * @author Walter
 */
public class Connectdb {
    
    Connection con;
    Statement stmt;
        
    
   public Connectdb(){
       
       try
       {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(" The Pilot Is Loaded ....");
        
        } 
        catch (ClassNotFoundException ex){
            
            System.out.println(" Pilot Is Not Loaded .... :"+ex);
        
        }
        
        try{
            
            String url = "jdbc:mysql://localhost:/test";
            String user="root";
            String pswd = "";
            
            con = DriverManager.getConnection(url,user,pswd);
            stmt = con.createStatement();
            
            System.out.println("Connection Succesfull .... :");
        
        } catch (Exception ex){
            
            System.out.println(" Error In Connection .... :"+ex);
            ex.printStackTrace();
        }
        
        //return con;
    
    }
    
}

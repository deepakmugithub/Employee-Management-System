/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package emsg4v1;

/**
 *
 * @author Walter
 */
public class GetUser {
    
    public String usernm="";
    public String userid="";
    
   public void setUser(String user,String id)
    {
        this.usernm=user; 
        this.userid=id;   
        
    }
   public String getUsernm()
    {
        return usernm;
    }
      public String getUserid()
    {
        return userid;
    }
    
}

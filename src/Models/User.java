/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author SHAFI
 */
public class User {
    
    public User(String username, String password){
        this.Id = System.currentTimeMillis();
        this.Username = username;
        this.Password = password;
    }
    
    public long Id = 0;
    public String Username  = "";
    public String Password = "";
    
}

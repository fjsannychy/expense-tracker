/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Asus
 */
public class Head {
    
    public long Id = 0;
    public String Name = "";
    
    public Head(String name){
        this.Id = System.currentTimeMillis();
        this.Name = name;
    }
    
}

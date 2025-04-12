/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;

/**
 *
 * @author SHAFI
 */
public class Transaction {
    
    public Transaction(String Head, double Amount, String Date, String Type){
        this.Id = System.currentTimeMillis();
        this.Head = Head;
        this.Amount = Amount;
        this.Date = Date;
        this.Type = Type;
    }
    
    public long Id = 0;
    public String Head  = null;
    public double Amount = 0;
    public String Date  = null;
    public String Type = null;
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.BalanceModel;
import Models.DetailsReportModel;
import Models.Transaction;
import Services.Common.FileService;
import com.google.gson.reflect.TypeToken;
import expensetracker.AppSettings;
import java.util.ArrayList;

/**
 *
 * @author SHAFI
 */
public class TransactionService {
    

    private FileService _fileService;
    
    public ArrayList<Transaction> _transactions;
    
    public TransactionService(){
        
        this._fileService = new FileService();
        
        //loading all transactions from json file.
        this._transactions = this._fileService.GetData(AppSettings.TransactionDataFileName, new TypeToken<ArrayList<Transaction>>(){}.getType());
           
        
    }
    
    public void Add(Transaction model){
        
        this._transactions.add(model);
        
    }
    
    public void Edit(Transaction model){
        
        for(int i =0; i< this._transactions.size(); i++){
            
            if(this._transactions.get(i).Id == model.Id){
               this._transactions.set(i, model);
               break;
            }
        }
    }
    
    public BalanceModel GetBalance(){
        
        double income = 0;
        double expense = 0;
        
        for(int i =0; i< this._transactions.size(); i++){
            
            var transaction = this._transactions.get(i);
            if("Income".equals(transaction.Type)){
                income+=transaction.Amount;              
            }
            else{
                expense+=transaction.Amount;
            }

        }
        
        return new BalanceModel(income, expense);
    }
    
    public DetailsReportModel GetDetailsReport(
            String head,
            String from,
            String to){
        
        double income = 0;
        double expense = 0;
        
        var result = new DetailsReportModel();
        
        for(int i =0; i< this._transactions.size(); i++){
            
            var transaction = this._transactions.get(i);
            
            if(head.length() != 0 && !transaction.Head.equals(head))
            {
                continue;
            }
            
            
            if(transaction.Date.compareTo(from) >= 0 &&
               transaction.Date.compareTo(to) <= 0 )
            {
                
                if("Income".equals(transaction.Type)){
                    income+=transaction.Amount;              
                }
                else{
                    expense+=transaction.Amount;
                }
            
                result.transactions.add(transaction);
            }

        }

        result.balance = new BalanceModel(income, expense);
        
        return result;
    }
    
    public void Delete(long Id){
        
        this._transactions.removeIf(r -> r.Id == Id);
        
    }
    
    public void SaveChanges(){
                
        //save current transaction list to json file
        this._fileService.SaveData(this._transactions, AppSettings.TransactionDataFileName); 
        
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.Head;
import Services.Common.FileService;
import com.google.gson.reflect.TypeToken;
import expensetracker.AppSettings;
import java.util.ArrayList;

/**
 *
 * @author SHAFI
 */
public class HeadService {
    
    
    private FileService _fileService;
    
    public ArrayList<Head> _heads;
    
    public HeadService(){
        
        this._fileService = new FileService();
        
        //loading Heads from json file
        this._heads = this._fileService.GetData(AppSettings.HeadDataFileName, new TypeToken<ArrayList<Head>>(){}.getType());
           
    }
    
    public Head GetHead(String name){
 
        for(int i =0; i< this._heads.size(); i++){
            
            if(this._heads.get(i).Name.equals(name)){
                return this._heads.get(i);
            }
        }
        
        return null;
    }
    
    public boolean IsHeadExists(String name){
        
        boolean isHeadExists = false;
 
        for(int i =0; i< this._heads.size(); i++){
            
            if(this._heads.get(i).Name.equals(name)){
                isHeadExists = true;
                break;
            }
        }
        
        return isHeadExists;
    }
    
    public void Add(Head model){
        
        this._heads.add(model);
        
    }
    
    public void Edit(Head model, String name){
        
        for(int i =0; i< this._heads.size(); i++){
            
            if(this._heads.get(i).Name.equals(name)){
               this._heads.set(i, model);
               break;
            }
        }
    }
    
    public void Delete(String name){
        
        this._heads.removeIf(r -> r.Name == name);
        
    }
    
    public void SaveChanges(){
            
        //save current Heads list to json file.
        this._fileService.SaveData(this._heads, AppSettings.HeadDataFileName);
        
    }
}

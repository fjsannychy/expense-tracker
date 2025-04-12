/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Models.User;
import Services.Common.FileService;
import com.google.gson.reflect.TypeToken;
import expensetracker.AppSettings;
import java.util.ArrayList;

/**
 *
 * @author SHAFI
 */
public class UserService {
    
    
    private FileService _fileService;
    
    public ArrayList<User> _users;
    
    public UserService(){
        
        this._fileService = new FileService();
        
        //loading users from json file
        this._users = this._fileService.GetData(AppSettings.UserDataFileName, new TypeToken<ArrayList<User>>(){}.getType());
           
    }
    
    public void Add(User model){
        
        this._users.add(model);
        
    }
    
    public void Edit(User model){
        
        for(int i =0; i< this._users.size(); i++){
            
            if(this._users.get(i).Id == model.Id){
               this._users.set(i, model);
               break;
            }
        }
    }
    
    public void Delete(long Id){
        
        this._users.removeIf(r -> r.Id == Id);
        
    }
    
    public void SaveChanges(){
            
        //save current users list to json file.
        this._fileService.SaveData(this._users, AppSettings.UserDataFileName);
        
    }
}

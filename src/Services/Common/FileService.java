/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services.Common;
import com.google.gson.Gson;
import expensetracker.AppSettings;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author SHAFI
 */
public class FileService {
    
   public <T> void SaveData(ArrayList<T> list, String fileName){
       
       Gson gson = new  Gson();  //External Gson library load
       String json = gson.toJson(list); //Convert to json string
       
       try(FileWriter writer = new FileWriter(AppSettings.DataFilePath+fileName+".json")){
           
           writer.write(json); //write to json file
           
       }catch(IOException ex){
           
           ex.printStackTrace();
       }
       
       
   }
   
   
   public <T> ArrayList<T> GetData(String fileName, Type listType){ //here list type is data type of array list.
       
       Gson gson = new  Gson(); //Load json librfary
       
       var list = new ArrayList<T>();
      
       try(FileReader reader = new FileReader(AppSettings.DataFilePath+fileName+".json")){ //read json file
          
           list = gson.fromJson(reader, listType); //covert json to array list
           
       }catch(IOException ex){
           
           ex.printStackTrace();
       }  
       
       if(list == null){
           return new ArrayList<T>();
       }
       else{
           return  list;
       }

   }
    
}

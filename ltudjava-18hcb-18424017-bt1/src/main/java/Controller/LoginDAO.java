/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author danh
 */
public class LoginDAO {
    public static String Username="";
    public static String Pass="";
    
    public boolean IsLogin(String us, String pass) throws IOException{
        FileReader fr = new FileReader("Data/accountTeacher.csv");
        BufferedReader br = new BufferedReader(fr);
        
        while(true){
            String Acc = br.readLine();
            if (Acc == null) {
                
                return false;
            }
            
            if (Acc.split(" ")[0].equals(us) && Acc.split(" ")[1].equals(pass)){
                this.Username = us;
                this.Pass = pass;
                return true;
            }
                
        }
    }
}

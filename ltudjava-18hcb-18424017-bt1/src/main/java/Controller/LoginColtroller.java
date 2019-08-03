/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author danh
 */
public class LoginColtroller {
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
    
    public static boolean ChangePass(String newPass) throws IOException{
        try {
            //write file
            FileReader fr = new FileReader("Data/accountTeacher.csv");

            BufferedReader br = new BufferedReader(fr);
            String oldInfo = LoginColtroller.Username + " " + LoginColtroller.Pass;
            String data = "";
            String newInfo = LoginColtroller.Username + " " + newPass;

            while (true){
                String str = br.readLine();
                if (str.isEmpty())
                    break;

                data += str + "\r\n";

            }
            fr.close();

            FileWriter fw = new FileWriter("Data/accountTeacher.csv");
            data = data.replaceAll(oldInfo, newInfo);
            fw.write(data);
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

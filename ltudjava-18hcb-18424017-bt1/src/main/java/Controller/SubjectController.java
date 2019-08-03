/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Subject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author danh
 */
public class SubjectController {
    
    public void GetTimetable(String pathFile, HashMap<String, ArrayList<Subject>> ListSubject) throws FileNotFoundException, IOException{
        try {
            File fr = new File(pathFile);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                      new FileInputStream(fr), "UTF8"));
            ArrayList<Subject> LstSubject = new ArrayList<Subject>();

            String nameClass = br.readLine();
            while (true){
                Subject sb = new Subject();
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;

                sb.setID(str.split(",")[0]); //set id subject
                sb.setNAME(str.split(",")[1]);       //set Name for subject
                sb.setROOM(str.split(",")[2]); //set room for subject
                LstSubject.add(sb);

            }
            ListSubject.put(nameClass, LstSubject);
        br.close();
        } catch (Exception e) {
        }
    }
    
    
    public boolean ImportFileSubject(String pathFile) throws FileNotFoundException, IOException{
        try {
            
            FileReader fr = new FileReader(pathFile);
            //write file listNameTimetable.csv
            FileWriter fwListNameTimetable = new FileWriter("Data/listNameTimetable.csv", true);

            BufferedReader br = new BufferedReader(fr);
            ArrayList<Subject> LstStudet = new ArrayList<Subject>();

            String nameClass = br.readLine();
            fwListNameTimetable.write(nameClass + '\n');
            FileWriter fw = new FileWriter("Data/" + nameClass + "_Timetable.txt");
            fw.write(nameClass + '\n');
            while (true){
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;

                fw.write(str + '\n');
            }
            fr.close();
            fw.close();
            fwListNameTimetable.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}

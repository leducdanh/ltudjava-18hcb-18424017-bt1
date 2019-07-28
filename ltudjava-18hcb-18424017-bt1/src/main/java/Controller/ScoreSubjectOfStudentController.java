/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ScoreSubjectOfStudent;
import Model.Subject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author danh
 */
public class ScoreSubjectOfStudentController {
    public void GetTimetable(String idSubject,  ArrayList<ScoreSubjectOfStudent> ListSubject) throws FileNotFoundException, IOException{
        try {
            String nameClass = this.GetClassStudentByIdStudent();
            FileReader fr = new FileReader("Data/" + nameClass + "-" + idSubject + "_ScoreSubject.csv");

            BufferedReader br = new BufferedReader(fr);
            
            ScoreSubjectOfStudent sb = new ScoreSubjectOfStudent();
            String nameSubject = br.readLine();
            sb.setNameSubject(nameSubject);
            sb.setIdSubject(idSubject);
            while (true){
                String str = br.readLine();
                if (str == null)
                    break;
                
                if (str.indexOf(LoginColtroller.Username) >= 0){
                    sb.setScore(str.split(",")[1]);
                    ListSubject.add(sb);
                    break;
                }
            }
        fr.close();
        } catch (Exception e) {
        }
    }
    
    
    public void ImportFileSubject(String pathFile) throws FileNotFoundException, IOException{
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
            if (str == null)
                break;
            
            fw.write(str + '\n');
        }
        fr.close();
        fw.close();
        fwListNameTimetable.close();
    }
    
    
    private String GetClassStudentByIdStudent(){
        return LoginColtroller.Username.substring(0, 2) + "HCB";
    }
}

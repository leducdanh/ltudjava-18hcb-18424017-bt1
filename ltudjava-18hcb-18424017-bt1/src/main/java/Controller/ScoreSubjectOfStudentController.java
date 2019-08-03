/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ScoreSubjectOfStudent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author danh
 */
public class ScoreSubjectOfStudentController {
    public void GetTimetable(String idSubject,  ArrayList<ScoreSubjectOfStudent> ListSubject) throws FileNotFoundException, IOException{
        try {
            String nameClass = this.GetClassStudentByIdStudent();
            File fr = new File("Data/" + nameClass + "-" + idSubject + "_Scores.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                      new FileInputStream(fr), "UTF8"));
            
            ScoreSubjectOfStudent sb = new ScoreSubjectOfStudent();
            String nameSubject = this.GetNameSubjectByIdSubject(nameClass + "-" + idSubject);
            sb.setNameSubject(nameSubject);
            sb.setIdSubject(idSubject);
            while (true){
                String str = br.readLine();
                if (str.isEmpty())
                    break;
                
                if (str.indexOf(LoginColtroller.Username) >= 0){
                    sb.setScoreMidSemester(str.split(",")[2]);
                    sb.setScoreEndSemester(str.split(",")[3]);
                    sb.setScoreplus(str.split(",")[4]);
                    sb.setScoreSummary(str.split(",")[5]);
                    ListSubject.add(sb);
                    break;
                }
            }
        br.close();
        } catch (Exception e) {
        }
    }
    
    private String GetClassStudentByIdStudent(){
        return LoginColtroller.Username.substring(0, 2) + "HCB";
    }
    
    //IdSubject {18HCB-CT001}
    private String GetNameSubjectByIdSubject(String IdSubject) throws FileNotFoundException{
        try {
            
            File fr = new File("Data/" + IdSubject.split("-")[0] + "_Timetable.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                      new FileInputStream(fr), "UTF8"));
            
            while (true){
                String str = br.readLine();
                if (str == null)
                    break;
                
                if (str.indexOf(IdSubject) >= 0){
                    return str.split(",")[1];
                }
            }
            
            br.close();
        } catch (Exception e) {
            return "";
        }
        return "";
    }

}

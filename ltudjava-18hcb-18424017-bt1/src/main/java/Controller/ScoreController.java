/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Scores;
import Model.Student;
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
public class ScoreController {
    public void GetListScore(String pathFile, HashMap<String, ArrayList<Scores>> ListScoresBySubject) throws FileNotFoundException, IOException{
        try {
            FileReader fr = new FileReader(pathFile);

            BufferedReader br = new BufferedReader(fr);
            ArrayList<Scores> LstStudet = new ArrayList<Scores>();

            String nameClass = br.readLine();
            while (true){
                Scores score = new Scores();
                String str = br.readLine();
                if (str == null)
                    break;

                score.setIdStudent(str.split(",")[0]); //set MSSV for student
                score.setName(str.split(",")[1]);       //set Name for student
                score.setScoreMidSemester(Float.parseFloat(str.split(",")[2])); //set score mid semester
                score.setScoreEndSemester(Float.parseFloat(str.split(",")[3])); //set score end semester
                score.setScoreplus(Float.parseFloat(str.split(",")[4]));        //set score plus
                score.setScoreSummary(Float.parseFloat(str.split(",")[5]));     //set score summary
                LstStudet.add(score);

            }
            ListScoresBySubject.put(nameClass, LstStudet);
        fr.close();
        } catch (Exception e) {
        }
        
    }
    
    public void ImportFileScores(String pathFile) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(pathFile);
        
        //write name class for file listNameClass.csv
        FileWriter fwListSubject = new FileWriter("Data/listSubject.csv", true);
        
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Subject> LstStudet = new ArrayList<Subject>();
        
        String nameSubject = br.readLine();
        fwListSubject.write(nameSubject + '\n');
        FileWriter fw = new FileWriter("Data/" + nameSubject + "_Scores.txt");
        fw.write(nameSubject + '\n');
        while (true){
            String str = br.readLine();
            if (str == null)
                break;
            
            fw.write(str + '\n');

        }
        fr.close();
        fw.close();
        fwListSubject.close();
    }
}

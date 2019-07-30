/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Scores;
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
                score.setScoreMidSemester((str.split(",")[2])); //set score mid semester
                score.setScoreEndSemester((str.split(",")[3])); //set score end semester
                score.setScoreplus((str.split(",")[4]));        //set score plus
                score.setScoreSummary((str.split(",")[5]));     //set score summary
                LstStudet.add(score);

            }
            ListScoresBySubject.put(nameClass, LstStudet);
        fr.close();
        } catch (Exception e) {
        }
        
    }
    
    public boolean ImportFileScores(String pathFile) throws FileNotFoundException, IOException{
        try {
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
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean UpdateScore(Scores score, String nameClass, ArrayList<Scores> lstScores) throws FileNotFoundException, IOException{
        try {
            //write file
            FileReader fr = new FileReader("Data/" + nameClass + "_Scores.txt");

            BufferedReader br = new BufferedReader(fr);
            String oldInfo = "";
            String data = "";
            String newInfo = score.getIdStudent() + ","
                            + score.getName() + ","
                            + "" + score.getScoreMidSemester() + ","
                            + "" + score.getScoreEndSemester() + ","
                            + "" + score.getScoreplus() + ","
                            + "" + score.getScoreSummary();
            for (Scores st : lstScores){
                if (st.getIdStudent().equals(score.getIdStudent())){
                    oldInfo = st.getIdStudent() + ","
                            + st.getName() + ","
                            + "" + st.getScoreMidSemester() + ","
                            + "" + st.getScoreEndSemester() + ","
                            + "" + st.getScoreplus() + ","
                            + "" + st.getScoreSummary();
                    
                    //upd list
                    st.setScoreMidSemester(score.getScoreMidSemester());
                    st.setScoreEndSemester(score.getScoreEndSemester());
                    st.setScoreplus(score.getScoreplus());
                    st.setScoreSummary(score.getScoreSummary());
                    break;
                }
            }

            while (true){
                String str = br.readLine();
                if (str == null)
                    break;

                data += str + "\r\n";

            }
            fr.close();

            FileWriter fw = new FileWriter("Data/" + nameClass + "_Scores.txt");
            data = data.replaceAll(oldInfo, newInfo);
            fw.write(data);
            fw.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

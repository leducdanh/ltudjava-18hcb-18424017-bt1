/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Scores;
import Model.Student;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author danh
 */
public class StudentController {
    public void GetListStudent(String pathFile, HashMap<String, ArrayList<Student>> ListStudentByClass) throws FileNotFoundException, IOException{
        try {
            File fr = new File(pathFile);

            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fr), "UTF8"));
            ArrayList<Student> LstStudet = new ArrayList<Student>();

            String nameClass = br.readLine();
            while (true){
                Student st = new Student();
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;

                st.setIdStudent(str.split(",")[0]); //set MSSV for student
                st.setNAME(str.split(",")[1]);       //set Name for student
                if (str.split(",")[2].equals("Nam"))
                    st.setGENDER(true); //set true if is boy
                else
                    st.setGENDER(false); //set true if is girl
                st.setID(str.split(",")[3]); //set CMND for student
                LstStudet.add(st);

            }
            ListStudentByClass.put(nameClass, LstStudet);
        br.close();
        } catch (Exception e) {
        }
        
    }
    
    public boolean ImportFileClass(String pathFile) throws FileNotFoundException, IOException{
        try {
            
            FileReader fr = new FileReader(pathFile);

            //write name class for file listNameClass.csv
            FileWriter fwListNameClass = new FileWriter("Data/listNameClass.csv", true);
            FileReader frListNameClass = new FileReader("Data/listNameClass.csv");

            //write name class for file accountTeacher.csv
            FileWriter fwAccount = new FileWriter("Data/accountTeacher.csv", true);
            FileReader frAccount = new FileReader("Data/accountTeacher.csv");

            BufferedReader br;
            ArrayList<Student> LstStudet = new ArrayList<Student>();
            
            br = new BufferedReader(frListNameClass);
            String strLstNameClass = "";
            while (true){
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;
               strLstNameClass += str + ",";
            }
            frListNameClass.close();
            
            br = new BufferedReader(frAccount);
            String strLstAccount = "";
            while (true){
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;
               strLstAccount += str + ",";
            }
            frAccount.close();
            
            br = new BufferedReader(fr);
            String nameClass = br.readLine();
            if (strLstNameClass.indexOf(nameClass) <0 )
                fwListNameClass.write(nameClass + '\n');
            
            FileWriter fw = new FileWriter("Data/" + nameClass + ".txt");
            fw.write(nameClass + '\n');
            
            while (true){
                Student st = new Student();
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;
                
                fw.write(str + '\n');
                
                if (strLstAccount.indexOf(str.split(",")[0]) < 0)
                    fwAccount.write(str.split(",")[0] + " " + str.split(",")[0] + '\n');

            }
            fr.close();
            fw.close();
            fwListNameClass.close();
            fwAccount.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean AddStudent(Student student, String nameClass, ArrayList<Student> lstStudent) throws FileNotFoundException, IOException{
        //write file
        FileWriter fw = new FileWriter("Data/" + nameClass + ".txt", true);
        
        //check exist IdStudent
        for (Student st : lstStudent){
            if (st.getIdStudent().equals(student.getIdStudent())){
                JOptionPane.showMessageDialog((JFrame)null, "Mã sinh viên đã tồn tại");
                return false;
            }
        }
        
        //add for list
        lstStudent.add(student);
        
        //writing file
        String strGender = (student.getGENDER())? "Nam": "Nữ";
        fw.write(student.getIdStudent() + "," + student.getNAME() + "," +
                 strGender + "," + student.getID() + '\n');
        fw.close();
        return true;
    }
    
    public boolean DelStudent(String IdStudent, String nameClass, ArrayList<Student> lstStudent) throws FileNotFoundException, IOException{
        try {
            //write file
            FileWriter fw = new FileWriter("Data/" + nameClass + ".txt");

            File frScores = new File("Data/" + nameClass + "_Scores.txt");

            ArrayList<Scores> lstScores = new ArrayList<Scores>();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(frScores), "UTF8"));

            fw.write(nameClass + '\n');
            for (Student student : lstStudent) {
                //writing file
                String strGender = (student.getGENDER())? "Nam": "Nữ";
                fw.write(student.getIdStudent() + "," + student.getNAME() + "," +
                         strGender + "," + student.getID() + '\n');
            }
            fw.close();
            String data = "";
            while(true) {
                String str = br.readLine();
                if (str == null || str.trim().isEmpty())
                    break;
                if (!str.split(",")[0].equals(IdStudent)){
                    data += str + "\r\n";
                }
            }

            FileWriter fwScore = new FileWriter("Data/" + nameClass + "_Scores.txt");

            fwScore.write(data + '\n');
            fwScore.close();
            br.close();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}

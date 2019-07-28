/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
            FileReader fr = new FileReader(pathFile);

            BufferedReader br = new BufferedReader(fr);
            ArrayList<Student> LstStudet = new ArrayList<Student>();

            String nameClass = br.readLine();
            while (true){
                Student st = new Student();
                String str = br.readLine();
                if (str == null)
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
        fr.close();
        } catch (Exception e) {
        }
        
    }
    
    public void ImportFileClass(String pathFile) throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(pathFile);
        
        //write name class for file listNameClass.csv
        FileWriter fwListNameClass = new FileWriter("Data/listNameClass.csv", true);
        
        //write name class for file accountTeacher.csv
        FileWriter fwAccount = new FileWriter("Data/accountTeacher.csv", true);
        
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Student> LstStudet = new ArrayList<Student>();
        
        String nameClass = br.readLine();
        fwListNameClass.write(nameClass + '\n');
        FileWriter fw = new FileWriter("Data/" + nameClass + ".txt");
        fw.write(nameClass + '\n');
        while (true){
            Student st = new Student();
            String str = br.readLine();
            if (str == null)
                break;
            
            fw.write(str + '\n');
            fwAccount.write(str.split(",")[0] + " " + str.split(",")[0] + '\n');

        }
        fr.close();
        fw.close();
        fwListNameClass.close();
        fwAccount.close();
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
}

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author danh
 */
public class StudentDAO {
    
    public void GetListStudent(String pathFile, HashMap<String, ArrayList<Student>> ListStudentByClass) throws FileNotFoundException, IOException{
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
    }
}

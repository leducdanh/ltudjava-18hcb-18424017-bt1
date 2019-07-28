/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ScoreSubjectOfStudentController;
import Model.ScoreSubjectOfStudent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author danh
 */
public class FrSocreOfStudent extends JFrame implements ActionListener{
    String[] ColumnName = {"Stt", "Mã môn", "Tên môn", "Điểm"};
    private  ArrayList<ScoreSubjectOfStudent> LstScoreSubjectOfStudent = new  ArrayList<ScoreSubjectOfStudent>();
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    ArrayList<String> LstIdSubject= new ArrayList<String>();  
    
    public FrSocreOfStudent() throws IOException {
        LstIdSubject.add("");
        this.InitFrame();
        //event Closing Frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrHome Home = new FrHome();
                Home.setVisible(true);
            }
        });
        
        this.ShowListScoreBySubject();
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
    }
    
    private void InitFrame() throws IOException{
        this.setTitle("Tra cứu điểm");
        for(String columnName : this.ColumnName){
           this.tableModel.addColumn(columnName);
        }
        
        this.GetListIdSubject();
        this.LoadData();
        this.panel.setLayout(null);
        sp.setBounds(0,0,600,300);
        panel.add(this.sp);
        Collections.sort(this.LstIdSubject);
        
        add(panel);
        setSize(600, 300);
        setLocationRelativeTo(null);
//        panel.setBounds(0, 0, 600, 300);
        setVisible(true);
    }
    
    private void LoadData() throws IOException {
        ScoreSubjectOfStudentController subjectCtl = new ScoreSubjectOfStudentController();
        for (String  str: this.LstIdSubject){
            if (!str.isEmpty())
                //18HCB-CT001
                subjectCtl.GetTimetable(str.split("-")[1], this.LstScoreSubjectOfStudent);
        }
    }
    
    public void GetListIdSubject(){
        try {
            FileReader fr = new FileReader("Data/listSubject.csv");
            BufferedReader br = new BufferedReader(fr);
            while (true){
                String str = br.readLine();
                if (str == null)
                    break;
                this.LstIdSubject.add(str);

            }
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void ShowListScoreBySubject(){
        this.tableModel.getDataVector().removeAllElements();
        int index = 0;
        for (ScoreSubjectOfStudent subject : this.LstScoreSubjectOfStudent) {
            index++;
            this.tableModel.addRow(new String[]{ "" + index, subject.getIdSubject(), subject.getNameSubject(), subject.getScore()});
        }
        this.table.setModel(tableModel);
    }
    
    public static void main(String[] args) throws IOException {
        new FrSocreOfStudent();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

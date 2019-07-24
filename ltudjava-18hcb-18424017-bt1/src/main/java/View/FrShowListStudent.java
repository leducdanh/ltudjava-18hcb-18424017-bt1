/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginColtroller;
import Model.Student;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import Controller.StudentController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import javax.swing.ComboBoxModel;

/**
 *
 * @author danh
 */
public class FrShowListStudent extends JFrame implements ActionListener{
    String[] ColumnName = {"Stt", "Mã sinh viên", "Họ tên", "Giới tính", "CMND"};

    
    private HashMap<String, ArrayList<Student>> ListStudentByClass = new HashMap<String, ArrayList<Student>>();
    
    JPanel pn = new JPanel();
    JTable tb = new JTable();
    JScrollPane sp = new JScrollPane(tb);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameClass; 
    ArrayList<String> LstItemCmb= new ArrayList<String>();  
    
    public FrShowListStudent() throws IOException {
        LstItemCmb.add("");
//        if (LoginColtroller.Username.isEmpty() && LoginColtroller.Pass.isEmpty()){
//            Login lg = new Login();
//            lg.setVisible(true);
//            this.dispose();
//        }
        
        ///////////////////////////////////////////////////
        //event Closing Frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrHome Home = new FrHome();
                Home.setVisible(true);
            }
        });
        
        this.GetItemCombobox();
        this.DetailComponent();
        
        pn.setLayout(null);
        sp.setBounds(0,300,600,300);
        pn.add(this.sp);
        
        for(String columnName : ColumnName){
           tableModel.addColumn(columnName);
        }
        
        this.LoadData();
        this.ShowListStudent(this.cmbListNameClass.getSelectedItem().toString());
        
        TableColumnModel columnModel = tb.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);
        
        add(pn);
        setSize(600, 600);
        setLocationRelativeTo(null);
        pn.setBounds(200, 1, 600, 300);
        setVisible(true);
    }
    
    public static void main(String[] args) throws IOException {
        new FrShowListStudent();
    }
    
    public void DetailComponent(){
        Collections.sort(this.LstItemCmb);
        this.cmbListNameClass =new JComboBox(this.LstItemCmb.toArray());
        cmbListNameClass.setBounds(10,20,150,25);
        cmbListNameClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();
                ShowListStudent(selected.toString());
            }
        });
        pn.add(cmbListNameClass);
          
    }
    
    // show List student
    public void LoadData() throws IOException {
        
        StudentController studentCtr = new StudentController();
        for (String  str: this.LstItemCmb){
            studentCtr.GetListStudent("Data/" + str +".txt", this.ListStudentByClass);
        }
        
    }
    
    public void ShowListStudent(String Key){
        this.tableModel.getDataVector().removeAllElements();
        if (this.ListStudentByClass.get(Key) == null) {
            this.tb.setModel(tableModel);
            return;
        }
        int index = 0;
        for (Student student : this.ListStudentByClass.get(Key)) {
            index++;
            String strGender = (student.getGENDER())? "Nam": "Nữ";
            this.tableModel.addRow(new String[]{ "" + index, student.getIdStudent(), student.getNAME(), strGender, student.getID()});
        }
        this.tb.setModel(tableModel);
    }
    
    
    public void GetItemCombobox(){
        try {
            System.out.println("GetItemCombobox");
            FileReader fr = new FileReader("Data/listNameClass.csv");
            BufferedReader br = new BufferedReader(fr);
            while (true){
                String str = br.readLine();
                if (str == null)
                    break;
                this.LstItemCmb.add(str);

            }
            fr.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}

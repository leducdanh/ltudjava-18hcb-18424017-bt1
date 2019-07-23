/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

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
import Controller.StudentDAO;

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
    
    public FrShowListStudent() throws IOException {
        DetailComponent();
        
        pn.setLayout(null);
        
        DetailComponent();
        sp.setBounds(0,300,600,300);
        pn.add(this.sp);
        
        for(String columnName : ColumnName){
           tableModel.addColumn(columnName);
        }
        
//        tableModel
        
        LoadData();
        ShowListStudent(this.cmbListNameClass.getSelectedItem().toString());
        
        TableColumnModel columnModel = tb.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);

//        this.tb.setFillsViewportHeight(true);
        
        add(pn);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pn.setBounds(200, 1, 600, 300);
        setVisible(true);
    }
    
    public static void main(String[] args) throws IOException {
        new FrShowListStudent();
    }
    
    public void DetailComponent(){
      String country[]={"18HCB","18HCB-CT001"};  
      this.cmbListNameClass =new JComboBox(country);
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
        
        StudentDAO stDAO = new StudentDAO();
        stDAO.GetListStudent("Data/18HCBs.csv", this.ListStudentByClass);
        stDAO.GetListStudent("Data/18HCB-CT001.csv", this.ListStudentByClass);
        
    }
    
    public void ShowListStudent(String Key){
        this.tableModel.getDataVector().removeAllElements();
        int index = 0;
        for (Student student : this.ListStudentByClass.get(Key)) {
            index++;
            String strGender = (student.getGENDER())? "Nam": "Nữ";
            this.tableModel.addRow(new String[]{ "" + index, student.getIdStudent(), student.getNAME(), strGender, student.getID()});
        }
        this.tb.setModel(tableModel);
    }

//    @Override
//    public void itemStateChanged(ItemEvent evt) {
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.cmbListNameClass)){
            // Get the source of the component, which is our combo
                // box.
                JComboBox comboBox = (JComboBox) e.getSource();

                // Print the selected items and the action command.
                Object selected = comboBox.getSelectedItem();
                System.out.println("Selected Item  = " + selected);
                ShowListStudent(selected.toString());
                String command = e.getActionCommand();
                System.out.println("Action Command = " + command);

                // Detect whether the action command is "comboBoxEdited"
                // or "comboBoxChanged"
                if ("comboBoxEdited".equals(command)) {
                    System.out.println("User has typed a string in " +
                            "the combo box.");
                } else if ("comboBoxChanged".equals(command)) {
                    System.out.println("User has selected an item " +
                            "from the combo box.");
                }
        }
    }
}
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout.Group;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author danh
 */
public class FrShowListStudent extends JFrame implements ActionListener{
    String[] ColumnName = {"Stt", "Mã sinh viên", "Họ tên", "Giới tính", "CMND"};
    private HashMap<String, ArrayList<Student>> ListStudentByClass = new HashMap<String, ArrayList<Student>>();
    
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameClass;
    ArrayList<String> LstItemCmb= new ArrayList<String>();  
    JTextField txtIDStudent = new JTextField();
    JTextField txtNameStudent = new JTextField();
    JTextField txtID = new JTextField();
    JRadioButton rdbMale=new JRadioButton("Nam", true);  
    JRadioButton rdbFemale=new JRadioButton("Nữ");  
    ButtonGroup grGender=new ButtonGroup();  
    
    JButton btnAddStudent;
    
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
        
        panel.setLayout(null);
        sp.setBounds(0,300,600,300);
        panel.add(this.sp);
        
        for(String columnName : ColumnName){
           tableModel.addColumn(columnName);
        }
        
        this.LoadData();
        this.ShowListStudent(this.cmbListNameClass.getSelectedItem().toString());
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(150);
        
        add(panel);
        setSize(600, 600);
        setLocationRelativeTo(null);
        panel.setBounds(200, 1, 600, 300);
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
                
                table.getSelectionModel().clearSelection();
                ShowListStudent(selected.toString());
            }
        });
        panel.add(cmbListNameClass);
          
        JLabel lblIdStudent = new JLabel("Mã sinh viên");
        lblIdStudent.setBounds(10,50,100,25);
        panel.add(lblIdStudent);
        this.txtIDStudent.setBounds(100,50,165,25);
        panel.add(this.txtIDStudent);
          
        JLabel lblNameStudent = new JLabel("Tên sinh viên");
        lblNameStudent.setBounds(10,80,100,25);
        panel.add(lblNameStudent);
        this.txtNameStudent.setBounds(100,80,165,25);
        panel.add(this.txtNameStudent);
          
        JLabel blbID = new JLabel("CMND");
        blbID.setBounds(10,110,100,25);
        panel.add(blbID);
        this.txtID.setBounds(100,110,165,25);
        panel.add(this.txtID);
        
        JLabel blbGender = new JLabel("Giới tính");
        blbGender.setBounds(10,140,100,25);
        panel.add(blbGender);
        this.rdbMale.setBounds(100,140,90,30);  
        this.rdbFemale.setBounds(200,140,100,30);  
        this.grGender.add(this.rdbMale);grGender.add(this.rdbFemale);
        this.rdbMale.isSelected( );
        this.panel.add(this.rdbMale);panel.add(this.rdbFemale);
        
        ListSelectionModel model = this.table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (! model.isSelectionEmpty()){
                    txtIDStudent.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtNameStudent.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtID.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    
                    if (table.getValueAt(table.getSelectedRow(), 3).toString().toLowerCase().equals("nam")){
                        rdbMale.setSelected(true);
                    } else {
                        rdbFemale.setSelected(true);
                    }
                }
            }
        });
        
        this.btnAddStudent = new JButton("Them SV");
        this.btnAddStudent.setBounds(10,170,100,25);
        this.btnAddStudent.addActionListener(this);
        this.panel.add(this.btnAddStudent);
    }
    
    // show List student
    public void LoadData() throws IOException {
        
        StudentController studentCtr = new StudentController();
        for (String  str: this.LstItemCmb){
            studentCtr.GetListStudent("Data/" + str +".txt", this.ListStudentByClass);
        }
        
    }
    
    public void ShowListStudent(String Key){
        this.isFocusCycleRoot(this.cmbListNameClass);
        this.tableModel.getDataVector().removeAllElements();
        if (this.ListStudentByClass.get(Key) == null) {
            this.table.setModel(tableModel);
            return;
        }
        int index = 0;
        for (Student student : this.ListStudentByClass.get(Key)) {
            index++;
            String strGender = (student.getGENDER())? "Nam": "Nữ";
            this.tableModel.addRow(new String[]{ "" + index, student.getIdStudent(), student.getNAME(), strGender, student.getID()});
        }
        this.table.setModel(tableModel);
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
        if (e.getSource().equals(this.btnAddStudent)){
            StudentController STctl = new StudentController();
            Student student = new Student(this.txtIDStudent.getText(),
                                          this.txtNameStudent.getText(), 
                                          this.rdbMale.isSelected(), 
                                          this.txtID.getText());
            
            String nameClass = this.cmbListNameClass.getSelectedItem().toString();
            boolean isAddSuccess;
            try {
                isAddSuccess = STctl.AddStudent(student, nameClass, this.ListStudentByClass.get(nameClass));
                if (isAddSuccess) {
                    this.tableModel.addRow(new String[]{
                                            "" + this.ListStudentByClass.get(nameClass).size(),
                                            student.getIdStudent(),
                                            student.getNAME(),
                                            (student.getGENDER()) ? "Nam": "Nữ",
                                            student.getID()});
                    this.table.setModel(tableModel);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(FrShowListStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

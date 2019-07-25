/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.StudentController;
import Controller.SubjectController;
import Model.Student;
import Model.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JComboBox;
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
public class FrShowListSubject extends JFrame implements ActionListener{
    
    String[] ColumnName = {"Stt", "Mã môn", "Tên môn", "Phòng học"};
    private HashMap<String, ArrayList<Subject>> ListSubject = new HashMap<String, ArrayList<Subject>>();
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameClass;
    ArrayList<String> LstItemCmb= new ArrayList<String>();  
    
    public FrShowListSubject() throws IOException {
        this.InitFrame();
        
        this.ShowTimetable(this.cmbListNameClass.getSelectedItem().toString());
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
    }
    
    private void InitFrame() throws IOException{
        for(String columnName : this.ColumnName){
           this.tableModel.addColumn(columnName);
        }
        
        this.GetItemCombobox();
        this.LoadData();
        this.panel.setLayout(null);
        sp.setBounds(0,300,600,300);
        panel.add(this.sp);
        Collections.sort(this.LstItemCmb);
        this.cmbListNameClass =new JComboBox(this.LstItemCmb.toArray());
        cmbListNameClass.setBounds(10,20,150,25);
        cmbListNameClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();
                
                table.getSelectionModel().clearSelection();
                ShowTimetable(selected.toString());
            }
        });
        panel.add(cmbListNameClass);
        
        add(panel);
        setSize(600, 600);
        setLocationRelativeTo(null);
        panel.setBounds(200, 1, 600, 300);
        setVisible(true);
    }
    
    private void LoadData() throws IOException {
        SubjectController subjectCtl = new SubjectController();
        for (String  str: this.LstItemCmb){
            subjectCtl.GetTimetable("Data/" + str +"_Timetable.txt", this.ListSubject);
        }
    }
    
    public void GetItemCombobox(){
        try {
            System.out.println("GetItemCombobox");
            FileReader fr = new FileReader("Data/listNameTimetable.csv");
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
    
    public void ShowTimetable(String Key){
        this.isFocusCycleRoot(this.cmbListNameClass);
        this.tableModel.getDataVector().removeAllElements();
        if (this.ListSubject.get(Key) == null) {
            this.table.setModel(tableModel);
            return;
        }
        int index = 0;
        for (Subject subject : this.ListSubject.get(Key)) {
            index++;
            this.tableModel.addRow(new String[]{ "" + index, subject.getID(), subject.getNAME(), subject.getROOM()});
        }
        this.table.setModel(tableModel);
    }
    
    public static void main(String[] args) throws IOException {
        new FrShowListSubject();
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

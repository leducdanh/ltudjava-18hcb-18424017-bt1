/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.SubjectController;
import Model.ScoreSubjectOfStudent;
import Model.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class FrShowListSubject extends JFrame implements ActionListener, Comparator<Subject>{
    
    String[] ColumnName = {"Stt", "Mã môn", "Tên môn", "Phòng học"};
    private HashMap<String, ArrayList<Subject>> ListSubject = new HashMap<String, ArrayList<Subject>>();
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameClass;
    ArrayList<String> LstItemCmb= new ArrayList<String>();  
    
    public FrShowListSubject() throws IOException {
        this.setResizable(false);
        LstItemCmb.add("");
        this.InitFrame();
        //event Closing Frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrHome Home = new FrHome();
                Home.setVisible(true);
            }
        });
        
        this.ShowTimetable(this.cmbListNameClass.getSelectedItem().toString());
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(100);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(100);
    }
    
    private void InitFrame() throws IOException{
        this.setTitle("Xem thời khóa biểu");
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
        this.tableModel.addRow(new String[] {});
        if (this.ListSubject.get(Key) == null) {
            this.table.setModel(tableModel);
            return;
        }
        int index = 0;
        this.tableModel.getDataVector().removeAllElements();
        Comparator<Subject> compareById = (Subject o1, Subject o2) 
                                          -> o1.getID().compareTo( o2.getID());
        Collections.sort(this.ListSubject.get(Key), compareById);
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

    @Override
    public int compare(Subject arg0, Subject arg1) {
        return arg0.getID().compareTo(arg1.getID());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ScoreController;
import Controller.StudentController;
import Model.Scores;
import Model.Student;
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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author danh
 */
public class FrShowListScores extends JFrame implements ActionListener{
    String[] ColumnName = {"Stt", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng", "Đậu/Rớt"};
    private HashMap<String, ArrayList<Scores>> ListScores = new HashMap<String, ArrayList<Scores>>();
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameSubject;
    ArrayList<String> LstItemCmb= new ArrayList<String>();  
    JTextField txtIDStudent = new JTextField();
    JTextField txtNameStudent = new JTextField();
    JLabel lblQtyPass = new JLabel();
    JLabel lblPercentPass = new JLabel();
    JLabel lblQtyFail = new JLabel();
    JLabel lblPercentFail = new JLabel();
    
    public FrShowListScores() throws IOException{
        LstItemCmb.add("");
        this.InitFrame();
        
        //event Closing Frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrHome Home = new FrHome();
                Home.setVisible(true);
            }
        });
        
        this.ShowScores(this.cmbListNameSubject.getSelectedItem().toString());
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(70);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(100);
        columnModel.getColumn(4).setPreferredWidth(70);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(70);
        columnModel.getColumn(7).setPreferredWidth(70);
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
        this.cmbListNameSubject =new JComboBox(this.LstItemCmb.toArray());
        cmbListNameSubject.setBounds(10,20,150,25);
        cmbListNameSubject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();
                
                table.getSelectionModel().clearSelection();
                ShowScores(selected.toString());
            }
        });
        panel.add(cmbListNameSubject);
        
        this.lblQtyPass = new JLabel("Số SV đậu: ");
        this.lblQtyPass.setBounds(10,50,100,25);
        panel.add(this.lblQtyPass);
        this.lblPercentPass = new JLabel("Chiếm: ");
        this.lblPercentPass.setBounds(200,50,100,25);
        panel.add(this.lblPercentPass);
        
        this.lblQtyFail = new JLabel("Số SV rớt: ");
        this.lblQtyFail.setBounds(10,75,100,25);
        panel.add(this.lblQtyFail);
        this.lblPercentFail = new JLabel("Chiếm: ");
        this.lblPercentFail.setBounds(200,75,100,25);
        panel.add(this.lblPercentFail);
//        this.txtIDStudent.setBounds(100,50,165,25);
//        panel.add(this.txtIDStudent);
        
        add(panel);
        setSize(600, 600);
        setLocationRelativeTo(null);
        panel.setBounds(200, 1, 600, 300);
        setVisible(true);
    }
    
    private void LoadData() throws IOException {
        ScoreController scoreCtl = new ScoreController();
        for (String  str: this.LstItemCmb){
            scoreCtl.GetListScore("Data/" + str +"_Scores.txt", this.ListScores);
        }
    }
    
    public void GetItemCombobox(){
        try {
            System.out.println("GetItemCombobox");
            FileReader fr = new FileReader("Data/listSubject.csv");
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
    
    public void ShowScores(String Key){
        try {
            this.isFocusCycleRoot(this.cmbListNameSubject);
            this.tableModel.getDataVector().removeAllElements();
            if (this.ListScores.get(Key) == null) {
                this.table.setModel(tableModel);
                return;
            }
            int index = 0;
            int qtyPass = 0;
            int percentPass = 0;
            for (Scores score : this.ListScores.get(Key)) {
                index++;
                qtyPass = (score.getScoreSummary() >= 5) ? ++qtyPass : qtyPass;
                this.tableModel.addRow(new String[]{ "" + index, score.getIdStudent(), score.getName(), 
                                                    "" + score.getScoreMidSemester(),
                                                    "" + score.getScoreEndSemester(),
                                                    "" + score.getScoreplus(),
                                                    "" + score.getScoreSummary(),
                                                    (score.getScoreSummary() >= 5) ? "Đậu" : "Rớt"
                                                    });
            }
            this.lblQtyPass.setText("");
            this.lblQtyPass.setText("Số SV đậu: " + "" + qtyPass);
            percentPass = qtyPass * 100 / this.ListScores.get(Key).size();
            this.lblPercentPass.setText("");
            this.lblPercentPass.setText("Chiếm: : " + "" + percentPass + "%");
            
            this.lblQtyFail.setText("");
            this.lblQtyFail.setText("Số SV rớt: " + "" + (this.ListScores.get(Key).size() - qtyPass));
            percentPass = qtyPass * 100 / this.ListScores.get(Key).size();
            this.lblPercentFail.setText("");
            this.lblPercentFail.setText("Chiếm: : " + "" + (100 - percentPass) + "%");
            
            this.table.setModel(tableModel);
        } catch (Exception e) {
            this.table.setModel(tableModel);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new FrShowListScores();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

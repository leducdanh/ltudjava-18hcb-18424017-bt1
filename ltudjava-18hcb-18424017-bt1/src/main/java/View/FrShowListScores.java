/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.ScoreController;
import Model.Scores;
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
public class FrShowListScores extends JFrame implements ActionListener{
    String[] ColumnName = {"Stt", "MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng"};
    private HashMap<String, ArrayList<Scores>> ListScores = new HashMap<String, ArrayList<Scores>>();
    JPanel panel = new JPanel();
    JTable table = new JTable();
    JScrollPane sp = new JScrollPane(table);
    DefaultTableModel tableModel = new DefaultTableModel();
    JComboBox cmbListNameSubject;
    ArrayList<String> LstItemCmb= new ArrayList<String>();
    
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
            for (Scores score : this.ListScores.get(Key)) {
                index++;
                this.tableModel.addRow(new String[]{ "" + index, score.getIdStudent(), score.getName(), 
                                                    "" + score.getScoreMidSemester(),
                                                    "" + score.getScoreEndSemester(),
                                                    "" + score.getScoreplus(),
                                                    "" + score.getScoreSummary(),
                                                    });
            }
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

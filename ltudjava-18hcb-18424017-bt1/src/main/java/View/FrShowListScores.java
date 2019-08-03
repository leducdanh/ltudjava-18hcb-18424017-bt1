/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginColtroller;
import Controller.ScoreController;
import Model.Scores;
import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
public class FrShowListScores extends JFrame implements ActionListener, Comparator<Scores>{
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
    JTextField txtScoreMidSemester = new JTextField();
    JTextField txtScoreEndSemester = new JTextField();
    JTextField txtScoreplus = new JTextField();
    JTextField txtScoreSummary = new JTextField();
    JLabel lblQtyPass = new JLabel();
    JLabel lblPercentPass = new JLabel();
    JLabel lblQtyFail = new JLabel();
    JLabel lblPercentFail = new JLabel();
    JButton btnUpdate;
    
    public FrShowListScores() throws IOException{
        if (LoginColtroller.Username.isEmpty() && LoginColtroller.Pass.isEmpty()){
            Login lg = new Login();
            lg.setVisible(true);
            this.dispose();
            return;
        }
        this.setTitle("Xem danh sách điểm");
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
        sp.setBounds(0,300,600,262);
        panel.add(this.sp);
        Collections.sort(this.LstItemCmb);
        this.cmbListNameSubject =new JComboBox(this.LstItemCmb.toArray());
        cmbListNameSubject.setBounds(10,10,150,25);
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
        this.lblQtyPass.setBounds(10,40,100,25);
        panel.add(this.lblQtyPass);
        this.lblPercentPass = new JLabel("Chiếm: ");
        this.lblPercentPass.setBounds(200,40,100,25);
        panel.add(this.lblPercentPass);
        
        this.lblQtyFail = new JLabel("Số SV rớt: ");
        this.lblQtyFail.setBounds(10,65,100,25);
        panel.add(this.lblQtyFail);
        this.lblPercentFail = new JLabel("Chiếm: ");
        this.lblPercentFail.setBounds(200,65,100,25);
        panel.add(this.lblPercentFail);
        
        JLabel lblIdStudent = new JLabel("Mã sinh viên");
        lblIdStudent.setBounds(10,90,100,25);
        panel.add(lblIdStudent);
        this.txtIDStudent.setBounds(100,90,165,25);
        this.txtIDStudent.setEnabled(false);
        this.txtIDStudent.setDisabledTextColor(Color.BLACK);
        panel.add(this.txtIDStudent);
          
        JLabel lblNameStudent = new JLabel("Tên sinh viên");
        lblNameStudent.setBounds(10,115,100,25);
        panel.add(lblNameStudent);
        this.txtNameStudent.setBounds(100,115,165,25);
        this.txtNameStudent.setEnabled(false);
        this.txtNameStudent.setDisabledTextColor(Color.BLACK);
        panel.add(this.txtNameStudent);
        
        JLabel lblScoreMidSemester = new JLabel("Điểm GK:");
        lblScoreMidSemester.setBounds(10,140,100,25);
        panel.add(lblScoreMidSemester);
        this.txtScoreMidSemester.setBounds(100,140,165,25);
        panel.add(this.txtScoreMidSemester);
        
        JLabel lblScoreEndSemester = new JLabel("Điểm CK:");
        lblScoreEndSemester.setBounds(10,165,100,25);
        panel.add(lblScoreEndSemester);
        this.txtScoreEndSemester.setBounds(100,165,165,25);
        panel.add(this.txtScoreEndSemester);
        
        JLabel lblScoreplus = new JLabel("Điểm khác:");
        lblScoreplus.setBounds(10,190,100,25);
        panel.add(lblScoreplus);
        this.txtScoreplus.setBounds(100,190,165,25);
        panel.add(this.txtScoreplus);
        
        JLabel lblScoreSummary = new JLabel("Điểm tổng:");
        lblScoreSummary.setBounds(10,215,100,25);
        panel.add(lblScoreSummary);
        this.txtScoreSummary.setBounds(100,215,165,25);
        panel.add(this.txtScoreSummary);
        
        this.btnUpdate = new JButton("Cập nhật");
        this.btnUpdate.setBounds(10,250,100,25);
        this.btnUpdate.addActionListener(this);
        this.panel.add(this.btnUpdate);
        
        ListSelectionModel model = this.table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (! model.isSelectionEmpty()){
                    txtIDStudent.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtNameStudent.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtScoreMidSemester.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                    txtScoreEndSemester.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
                    txtScoreplus.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
                    txtScoreSummary.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
                    
                }
            }
        });
        
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
            FileReader fr = new FileReader("Data/listSubject.csv");
            BufferedReader br = new BufferedReader(fr);
            while (true){
                String str = br.readLine();
                if (str.isEmpty())
                    break;
                this.LstItemCmb.add(str);

            }
            fr.close();
        } catch (Exception e) {
        }
    }
    
    public void ShowScores(String Key){
        try {
            this.ClearControlTXT();
            this.isFocusCycleRoot(this.cmbListNameSubject);
            this.tableModel.getDataVector().removeAllElements();
            this.tableModel.addRow(new String[] {});
            this.tableModel.removeRow(0);
            if (this.ListScores.get(Key) == null) {
                this.table.setModel(tableModel);
                return;
            }
            int index = 0;
            int qtyPass = 0;
            int percentPass = 0;
            this.tableModel.getDataVector().removeAllElements();
            Comparator<Scores> compareById = (Scores o1, Scores o2) -> o1.getIdStudent().compareTo( o2.getIdStudent() );
            Collections.sort(this.ListScores.get(Key), compareById);
            for (Scores score : this.ListScores.get(Key)) {
                index++;
                qtyPass = (Float.parseFloat(score.getScoreSummary()) >= 5) ? ++qtyPass : qtyPass;
                this.tableModel.addRow(new String[]{ "" + index, score.getIdStudent(), score.getName(), 
                                                    "" + score.getScoreMidSemester(),
                                                    "" + score.getScoreEndSemester(),
                                                    "" + score.getScoreplus(),
                                                    "" + score.getScoreSummary(),
                                                    (Float.parseFloat(score.getScoreSummary()) >= 5) ? "Đậu" : "Rớt"
                                                    });
            }
            this.lblQtyPass.setText("");
            this.lblQtyPass.setText("Số SV đậu: " + "" + qtyPass);
            percentPass = qtyPass * 100 / this.ListScores.get(Key).size();
            this.lblPercentPass.setText("");
            this.lblPercentPass.setText("Chiếm: " + "" + percentPass + "%");
            
            this.lblQtyFail.setText("");
            this.lblQtyFail.setText("Số SV rớt: " + "" + (this.ListScores.get(Key).size() - qtyPass));
            percentPass = qtyPass * 100 / this.ListScores.get(Key).size();
            this.lblPercentFail.setText("");
            this.lblPercentFail.setText("Chiếm: " + "" + (100 - percentPass) + "%");
            
            this.table.setModel(tableModel);
        } catch (Exception e) {
            this.table.setModel(tableModel);
        }
    }
    
    public static void main(String[] args) throws IOException {
        new FrShowListScores();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnUpdate)){
            if (!this.CheckAllInput())
                return;
            
            ScoreController scoreCtr = new ScoreController();
            //value score
            Scores score = new Scores(this.txtIDStudent.getText(),
                                      this.txtNameStudent.getText(),
                                      (this.txtScoreMidSemester.getText()),
                                      (this.txtScoreEndSemester.getText()),
                                      (this.txtScoreplus.getText()),
                                      (this.txtScoreSummary.getText())
                                    );
            
            //value nameClass
            String nameClass = this.cmbListNameSubject.getSelectedItem().toString();
            boolean isUpdSuccess;
            try {
                this.ListScores.get(nameClass).set(this.table.getSelectedRow(), score);
                isUpdSuccess = scoreCtr.UpdateScore(nameClass, this.ListScores.get(nameClass));
                if (isUpdSuccess){
                    this.tableModel.setValueAt(this.txtScoreMidSemester.getText(), this.table.getSelectedRow(), 3);
                    this.tableModel.setValueAt(this.txtScoreEndSemester.getText(), this.table.getSelectedRow(), 4);
                    this.tableModel.setValueAt(this.txtScoreplus.getText(), this.table.getSelectedRow(), 5);
                    this.tableModel.setValueAt(this.txtScoreSummary.getText(), this.table.getSelectedRow(), 6);
                    this.tableModel.setValueAt(
                            Float.parseFloat(this.txtScoreSummary.getText()) >=5 ? "Đậu" : "Rớt"
                            , this.table.getSelectedRow(), 7);
                    
                    int qtyPass = 0;
                    int percentPass = 0;
                    Comparator<Scores> compareById = (Scores o1, Scores o2) -> o1.getIdStudent().compareTo( o2.getIdStudent() );
                    Collections.sort(this.ListScores.get(nameClass), compareById);
                    for (Scores sc : this.ListScores.get(nameClass)) {
                        qtyPass = (Float.parseFloat(sc.getScoreSummary()) >= 5) ? ++qtyPass : qtyPass;
                    }
                    this.lblQtyPass.setText("");
                    this.lblQtyPass.setText("Số SV đậu: " + "" + qtyPass);
                    percentPass = qtyPass * 100 / this.ListScores.get(nameClass).size();
                    this.lblPercentPass.setText("");
                    this.lblPercentPass.setText("Chiếm: " + "" + percentPass + "%");

                    this.lblQtyFail.setText("");
                    this.lblQtyFail.setText("Số SV rớt: " + "" + (this.ListScores.get(nameClass).size() - qtyPass));
                    percentPass = qtyPass * 100 / this.ListScores.get(nameClass).size();
                    this.lblPercentFail.setText("");
                    this.lblPercentFail.setText("Chiếm: " + "" + (100 - percentPass) + "%");
                    this.table.setModel(this.tableModel);
                }
            } catch (IOException ex) {
                Logger.getLogger(FrShowListScores.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
    }
    
    private void ClearControlTXT(){
        this.txtIDStudent.setText("");
        this.txtNameStudent.setText("");
        this.txtScoreEndSemester.setText("");
        this.txtScoreMidSemester.setText("");
        this.txtScoreplus.setText("");
        this.txtScoreSummary.setText("");
        
        this.lblQtyPass.setText("Số SV đậu: ");
        this.lblPercentPass.setText("Chiếm: ");

        this.lblQtyFail.setText("Số SV rớt: ");
        this.lblPercentFail.setText("Chiếm: ");
    }
    
    private boolean CheckAllInput() {
        if (this.txtScoreMidSemester.getText().trim().isEmpty()
            || this.txtScoreEndSemester.getText().trim().isEmpty()
            || this.txtScoreplus.getText().trim().isEmpty()
            || this.txtScoreSummary.getText().trim().isEmpty()
            || this.txtIDStudent.getText().trim().isEmpty()
            || this.txtNameStudent.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog((JFrame)null, "Hãy nhập đày đủ thông tin!", "Thiếu điểm", HEIGHT);
            return false;
        }
        else if (!this.CheckInputNumber(this.txtScoreMidSemester.getText().trim())
                || Float.parseFloat(this.txtScoreMidSemester.getText().trim()) < 0
                || Float.parseFloat(this.txtScoreMidSemester.getText().trim()) > 10){
            JOptionPane.showMessageDialog((JFrame)null, "\"Điểm giữa kỳ\" không hợp lệ!", "Thông báo", HEIGHT);
            return false;
        }
        else if (!this.CheckInputNumber(this.txtScoreMidSemester.getText().trim())
                || Float.parseFloat(this.txtScoreEndSemester.getText().trim()) < 0
                || Float.parseFloat(this.txtScoreEndSemester.getText().trim()) > 10){
            JOptionPane.showMessageDialog((JFrame)null, "\"Điểm cuối kỳ\" không hợp lệ!", "Thông báo", HEIGHT);
            return false;
        }
        else if (!this.CheckInputNumber(this.txtScoreMidSemester.getText().trim())
                || Float.parseFloat(this.txtScoreplus.getText().trim()) < 0
                || Float.parseFloat(this.txtScoreplus.getText().trim()) > 10){
            JOptionPane.showMessageDialog((JFrame)null, "\"Điểm khác\" không hợp lệ!", "Thông báo", HEIGHT);
            return false;
        }
        else if (!this.CheckInputNumber(this.txtScoreMidSemester.getText().trim())
                || Float.parseFloat(this.txtScoreSummary.getText().trim()) < 0
                || Float.parseFloat(this.txtScoreSummary.getText().trim()) > 10){
            JOptionPane.showMessageDialog((JFrame)null, "\"Điểm tổng\" không hợp lệ!", "Thông báo", HEIGHT);
            return false;
        }
        
        return true;
    }
    
    private boolean CheckInputNumber(String value) {
    try {
      Float.parseFloat(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

    @Override
    public int compare(Scores score1, Scores score2) {
        return score1.getIdStudent().compareTo(score2.getIdStudent());
    }
}

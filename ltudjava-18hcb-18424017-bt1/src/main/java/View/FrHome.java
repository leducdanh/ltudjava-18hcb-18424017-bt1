/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginColtroller;
import Controller.ScoreController;
import Controller.StudentController;
import Controller.SubjectController;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author danh
 */
public class FrHome extends JFrame implements ActionListener{
    private JButton btnImportListStudent;
    private JButton btnShowListStudent;
    private JFileChooser openFile;
    private JButton btnImportTimetable;
    private JButton btnShowListTimetable;
    private JButton btnImportScores;
    private JButton btnShowListScores;
    private JButton btnChangePass;
    private JButton btnShowScore;
    private JButton btnLogout;
    
    public FrHome() {
        if (LoginColtroller.Username.isEmpty() && LoginColtroller.Pass.isEmpty()){
            Login lg = new Login();
            lg.setVisible(true);
            this.dispose();
        }
        //event Closing Frame
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Login Home = new Login();
                Home.setVisible(true);
            }
        });
        this.setResizable(false);
        setTitle("Home");
        setSize(500, 500);
        setLocationRelativeTo(null);
        CreateFrHome();
        setVisible(true);
    }
    
    public void CreateFrHome() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JPanel panelStudent = new JPanel();
        panelStudent.setLayout(null);
        
        this.btnImportListStudent = new JButton("Import SV");
        this.btnImportListStudent.setBounds(this.getWidth()/2 - 165/2,20,165,25);
        this.btnImportListStudent.addActionListener(this);
        panel.add(this.btnImportListStudent);
        
        this.btnShowListStudent = new JButton("Xem DS SV");
        this.btnShowListStudent.setBounds(this.getWidth()/2 - 165/2,50,165,25);
        this.btnShowListStudent.addActionListener(this);
        panel.add(this.btnShowListStudent);
        
        this.btnImportTimetable = new JButton("Import TKB");
        this.btnImportTimetable.setBounds(this.getWidth()/2 - 165/2,80,165,25);
        this.btnImportTimetable.addActionListener(this);
        panel.add(this.btnImportTimetable);
        
        this.btnShowListTimetable = new JButton("Xem TKB");
        this.btnShowListTimetable.setBounds(this.getWidth()/2 - 165/2,110,165,25);
        this.btnShowListTimetable.addActionListener(this);
        panel.add(this.btnShowListTimetable);
        
        this.btnImportScores = new JButton("Import Điểm");
        this.btnImportScores.setBounds(this.getWidth()/2 - 165/2,140,165,25);
        this.btnImportScores.addActionListener(this);
        panel.add(this.btnImportScores);
        
        this.btnShowListScores = new JButton("Xem Điểm");
        this.btnShowListScores.setBounds(this.getWidth()/2 - 165/2,170,165,25);
        this.btnShowListScores.addActionListener(this);
        panel.add(this.btnShowListScores);
        
        this.btnShowScore = new JButton("Xem Điểm MH");
        this.btnShowScore.setBounds(this.getWidth()/2 - 165/2,170,165,25);
        this.btnShowScore.addActionListener(this);
        panelStudent.add(this.btnShowScore);
        
        this.btnChangePass = new JButton("Đổi mật khẩu");
        this.btnChangePass.setBounds(this.getWidth()/2 - 165/2,200,165,25);
        this.btnChangePass.addActionListener(this);
        
        this.btnLogout = new JButton("Đăng xuất");
        this.btnLogout.setBounds(this.getWidth()/2 - 165/2,230,165,25);
        this.btnLogout.addActionListener(this);
        
        if (LoginColtroller.Username.indexOf("giaovu") != -1){
            panel.add(this.btnChangePass);
            panel.add(this.btnLogout);
            add(panel);
        } else {
            panelStudent.add(this.btnChangePass);
            panelStudent.add(this.btnLogout);
            add(panelStudent);
        }
    }
    
    public static void main(String[] args) {
        new FrHome();
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource().equals(btnImportListStudent)){
            try {
                this.onClickBtnImportListStudent();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(btnShowListStudent)){
            try {
                this.onClickBtnShowListStudent();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnImportTimetable)){
            try {
                this.onClickBtnImportTimetable();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnShowListTimetable)){
            try {
                this.onClickBtnShowTimetable();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnImportScores)){
            try {
                this.onClickBtnImportScore();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnShowListScores)){
            try {
                this.onClickBtnShowListScore();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnShowScore)){
            try {
                this.onClickBtnShowScore();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnChangePass)){
            try {
                this.onClickBtnChangePass();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource().equals(this.btnLogout)){
            try {
                this.onClickBtnLogout();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void onClickBtnImportListStudent() throws IOException{
        this.openFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "*.csv", "csv");
        this.openFile.setAcceptAllFileFilterUsed(false);
        openFile.setFileFilter(filter);
        String approveButtonText = null;
        int returnVal;
        returnVal = openFile.showDialog(this, approveButtonText);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            StudentController studentCtr = new StudentController();
            if (studentCtr.ImportFileClass(openFile.getSelectedFile().getCanonicalPath()))
                JOptionPane.showMessageDialog((Frame)null, "Import thành công!");
            else 
                JOptionPane.showMessageDialog((Frame)null, "Import thất bại. Xin kiểm tra lại!");
            
        }
    }
    
    private void onClickBtnShowListStudent() throws IOException{
        FrShowListStudent frShow = new FrShowListStudent();
        frShow.setVisible(true);
        this.dispose();
    }
    
    private void onClickBtnImportTimetable() throws IOException{
        this.openFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "*.csv", "csv");
        this.openFile.setAcceptAllFileFilterUsed(false);
        openFile.setFileFilter(filter);
        String approveButtonText = null;
        int returnVal;
        returnVal = openFile.showDialog(this, approveButtonText);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            SubjectController subjectCtr = new SubjectController();
            if (subjectCtr.ImportFileSubject(openFile.getSelectedFile().getCanonicalPath()))
                JOptionPane.showMessageDialog((Frame)null, "Import thành công!");
            else 
                JOptionPane.showMessageDialog((Frame)null, "Import thất bại. Xin kiểm tra lại!");
        }
    }
    
    
    private void onClickBtnShowTimetable() throws IOException{
        FrShowListSubject frShow = new FrShowListSubject();
        frShow.setVisible(true);
        this.dispose();
    }
    
    
    private void onClickBtnImportScore() throws IOException{
        this.openFile = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "*.csv", "csv");
        this.openFile.setAcceptAllFileFilterUsed(false);
        openFile.setFileFilter(filter);
        String approveButtonText = null;
        int returnVal;
        returnVal = openFile.showDialog(this, approveButtonText);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            ScoreController scoreCtr = new ScoreController();
            if (scoreCtr.ImportFileScores(openFile.getSelectedFile().getCanonicalPath()))
                JOptionPane.showMessageDialog((Frame)null, "Import thành công!");
            else 
                JOptionPane.showMessageDialog((Frame)null, "Import thất bại. Xin kiểm tra lại!");
        }
    }
    
    
    private void onClickBtnShowListScore() throws IOException{
        FrShowListScores frShow = new FrShowListScores();
        frShow.setVisible(true);
        this.dispose();
    }
    
    private void onClickBtnShowScore() throws IOException{
        FrSocreOfStudent frShow = new FrSocreOfStudent();
        frShow.setVisible(true);
        this.dispose();
    }
    
    private void onClickBtnChangePass() throws IOException{
        FrChangePass frShow = new FrChangePass();
        frShow.setVisible(true);
        this.dispose();
    }
    
    private void onClickBtnLogout() throws IOException{
        Login frShow = new Login();
        LoginColtroller.Username = "";
        LoginColtroller.Pass = "";
        frShow.setVisible(true);
        this.dispose();
    }
            
    
}

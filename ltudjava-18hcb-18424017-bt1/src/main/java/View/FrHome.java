/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginColtroller;
import Controller.StudentController;
import Controller.SubjectController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    
    public FrHome() {
//        if (LoginColtroller.Username.isEmpty() && LoginColtroller.Pass.isEmpty()){
//            Login lg = new Login();
//            lg.setVisible(true);
//            this.dispose();
//        }
        setTitle("Home");
        setSize(500, 500);
        setLocationRelativeTo(null);
        CreateFrHome();
        setVisible(true);
    }
    
    public void CreateFrHome() {
        JPanel panel = new JPanel();
        
        add(panel);
        
        this.btnImportListStudent = new JButton("Import SV");
        this.btnImportListStudent.setBounds(10,20,80,25);
        this.btnImportListStudent.addActionListener(this);
        panel.add(this.btnImportListStudent);
        
        this.btnShowListStudent = new JButton("Xem DS SV");
        this.btnShowListStudent.setBounds(100,20,165,25);
        this.btnShowListStudent.addActionListener(this);
        panel.add(this.btnShowListStudent);
        
        this.btnImportTimetable = new JButton("Import TKB");
        this.btnImportTimetable.setBounds(10,50,165,25);
        this.btnImportTimetable.addActionListener(this);
        panel.add(this.btnImportTimetable);
        
        this.btnShowListTimetable = new JButton("Xem TKB");
        this.btnShowListTimetable.setBounds(100,50,165,25);
        this.btnShowListTimetable.addActionListener(this);
        panel.add(this.btnShowListTimetable);
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
           System.out.println("You chose to open this file: " +
                openFile.getSelectedFile().getCanonicalPath());
            StudentController studentCtr = new StudentController();
            studentCtr.ImportFileClass(openFile.getSelectedFile().getCanonicalPath());
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
           System.out.println("You chose to open this file: " +
                openFile.getSelectedFile().getCanonicalPath());
            SubjectController subjectCtr = new SubjectController();
            subjectCtr.ImportFileSubject(openFile.getSelectedFile().getCanonicalPath());
        }
    }
    
    
    private void onClickBtnShowTimetable() throws IOException{
        FrShowListSubject frShow = new FrShowListSubject();
        frShow.setVisible(true);
        this.dispose();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author danh
 */
public class FrHome extends JFrame implements ActionListener{
    private JButton btnImportListStudent;
    private JButton btnShowListStudent;
//    private JButton btnImportListStudent;
    
    public FrHome() {
        setTitle("Home");
        setSize(500, 500);
        setLocationRelativeTo(null);
        CreateFrHome();
    }
    
    public void CreateFrHome() {
        JPanel panel = new JPanel();
        
        add(panel);
        
        btnImportListStudent = new JButton("Import Student");
        btnImportListStudent.setBounds(10,20,80,25);
        btnImportListStudent.addActionListener(this);
        panel.add(btnImportListStudent);
        
        btnShowListStudent = new JButton("Show list Student");
        btnShowListStudent.setBounds(100,20,165,25);
        btnShowListStudent.addActionListener(this);
        panel.add(btnShowListStudent);
    }
    
    public static void main(String[] args) {
        new FrHome();
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        if (e.getSource().equals(btnImportListStudent)){
            onClickBtnImportListStudent();
        }
        else if (e.getSource().equals(btnShowListStudent)){
            try {
                onClickBtnShowListStudent();
            } catch (IOException ex) {
                Logger.getLogger(FrHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void onClickBtnImportListStudent(){
        JOptionPane.showInputDialog("Nhap duong dan");
    }
    
    private void onClickBtnShowListStudent() throws IOException{
        FrShowListStudent frShow = new FrShowListStudent();
        frShow.setVisible(true);
        this.dispose();
    }
}

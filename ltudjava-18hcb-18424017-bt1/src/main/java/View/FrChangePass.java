/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.LoginColtroller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author danh
 */
public class FrChangePass extends JFrame implements ActionListener{
    JButton btnUpd;
    JPasswordField txtOldPass = new JPasswordField(20);
    JPasswordField txtNewPass = new JPasswordField(20);
    JPasswordField txtConfirmPass = new JPasswordField(20);
    
    public FrChangePass() {
//        if (LoginColtroller.Username.isEmpty() && LoginColtroller.Pass.isEmpty()){
//            Login lg = new Login();
//            lg.setVisible(true);
//            this.dispose();
//        }
        CreateFrame();
    }
    
    public void CreateFrame() {
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        add(panel);
        placeComponents(panel);
        
        this.setResizable(false);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void placeComponents(JPanel panel) {

        
        panel.setLayout(null);

        // Creating JLabel
        JLabel lblCurPass = new JLabel("Mật khậu hiện tại: ");
        lblCurPass.setBounds(10,20,150,25);
        panel.add(lblCurPass);
        this.txtOldPass.setBounds(150,20,165,25);
        panel.add(this.txtOldPass);

        JLabel lblNewPass = new JLabel("Mật khẩu mới: ");
        lblNewPass.setBounds(10,50,150,25);
        panel.add(lblNewPass);
        this.txtNewPass.setBounds(150,50,165,25);
        panel.add(this.txtNewPass);
        
        JLabel lblConfirmPass = new JLabel("Nhập lại mật khẩu: ");
        lblConfirmPass.setBounds(10,80,150,25);
        panel.add(lblConfirmPass);
        this.txtConfirmPass.setBounds(150,80,165,25);
        panel.add(this.txtConfirmPass);

        this.btnUpd = new JButton("Cập nhật");
        this.btnUpd.setBounds(10, 110, 100, 25);
        this.btnUpd.addActionListener(this);
        panel.add(this.btnUpd);
    }
    
    public static void main(String[] args) {
        new FrChangePass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.btnUpd)){
            try {
                this.onClickBtnChangePass();
            } catch (IOException ex) {
                Logger.getLogger(FrChangePass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void onClickBtnChangePass() throws IOException{
        if (this.txtOldPass.getText().equals("")){
            JOptionPane.showMessageDialog((JFrame)null, "Hãy nhật mật khẩu hiện tại!");
            this.txtOldPass.requestFocus();
            return;
        } 
        if (this.txtNewPass.getText().equals("")){
            JOptionPane.showMessageDialog((JFrame)null, "Hãy nhật mật khẩu mới!");
            this.txtNewPass.requestFocus();
            return;
        } 
        if (this.txtConfirmPass.getText().equals("")){
            JOptionPane.showMessageDialog((JFrame)null, "Hãy nhật lại mật khẩu!");
            this.txtConfirmPass.requestFocus();
            return;
        } 
        if (!this.txtOldPass.getText().equals(LoginColtroller.Pass)){
            JOptionPane.showMessageDialog((JFrame)null, "Mật khẩu hiện tại sai!");
            this.txtOldPass.requestFocus();
            return;
        } 
        if (!this.txtNewPass.getText().equals(this.txtConfirmPass.getText())){
            JOptionPane.showMessageDialog((JFrame)null, "Mật khẩu nhập lại không khớp!");
            this.txtConfirmPass.requestFocus();
            return;
        }
        if (this.txtNewPass.getText().indexOf(" ") >= 0){
            JOptionPane.showMessageDialog((JFrame)null, "Mật khẩu mới không được có khoảng trắng!");
            this.txtConfirmPass.requestFocus();
            return;
        }
        if (LoginColtroller.ChangePass(this.txtNewPass.getText())){
            JOptionPane.showMessageDialog((JFrame)null, "Cập nhật thành công!");
            FrHome home = new FrHome();
            home.setVisible(true);
            this.dispose();
        }
    }
}

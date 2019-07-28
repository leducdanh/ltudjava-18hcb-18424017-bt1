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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author danh
 */
public class Login extends JFrame implements ActionListener{
    JButton btnLogin;
    JTextField txtUser = new JTextField(20);
    JPasswordField txtPass = new JPasswordField(20);
    
    public Login() {
        CreateFrame();
    }
    
    public void CreateFrame() {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        frame.add(panel);
        placeComponents(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void placeComponents(JPanel panel) {
        
        panel.setLayout(null);

        // Creating JLabel
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        this.txtUser.setBounds(100,20,165,25);
        panel.add(this.txtUser);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        this.txtPass.setBounds(100,50,165,25);
        panel.add(this.txtPass);

        JButton btnLogin = new JButton("login");
        btnLogin.setBounds(10, 80, 80, 25);
        btnLogin.addActionListener(this);
        panel.add(btnLogin);
    }
    
    public static void main(String[] args) {
        Login myFrameDemo = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        LoginColtroller lg = new LoginColtroller();
        try {
            boolean ckLogin;
            do{
                ckLogin = lg.IsLogin(this.txtUser.getText(), this.txtPass.getText());
                
                if (!ckLogin) {
//                    JOptionPane.setDefaultLocale(Locale.UK);
                    JOptionPane.showMessageDialog((JFrame)null, "Tài khoản không hợp lệ. Xin kiểm tra lại !");
                    return;
                }
            }while(!ckLogin);
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        FrHome Home = new FrHome();
        Home.setVisible(true);
        this.dispose();
    }
}

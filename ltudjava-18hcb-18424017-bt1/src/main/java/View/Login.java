/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author danh
 */
public class Login extends JFrame implements ActionListener{
    JButton btnLogin;
    
    public Login() {
        CreateFrame();
    }
    
    public void CreateFrame() {
         // Creating instance of JFrame
        JFrame frame = new JFrame("My First Swing Example");
        // Setting the width and height of frame
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();    
        // adding panel to frame
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

        JTextField userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

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
        FrHome Home = new FrHome();
        Home.setVisible(true);
        this.dispose();
    }
}

package Core;

import javax.swing.*;

import twitter4j.Twitter;
import twitter4j.examples.oauth.GetAccessToken;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

class LoginDialog extends JDialog implements ActionListener {
    JLabel labelName;
    JLabel labelPass;
    JTextField textName;
    JPasswordField passField;
    JButton okButton;
    

    JDialog dialog;

    public LoginDialog() {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JPanel panelThree = new JPanel();
        okButton = new JButton("Login");
        okButton.addActionListener(this);
        panelThree.add(okButton);

        dialog = new JDialog();
        dialog.setResizable(false);
        dialog.getContentPane().add(panelThree);
        dialog.setTitle("Login in to Twitter");
        dialog.getContentPane().setLayout(new FlowLayout());
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(null); // place in center of screen
        dialog.setModal(true);
        dialog.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
        	LoginTest.main(null);
            dialog.dispose();
        } 
    }



    
    public static void main(String[] args) {
        LoginDialog login = new LoginDialog();
           // Twitter twitter = new Twitter(userName, password); b
           // twitter.verifyCredentials();
            JOptionPane.showMessageDialog(login, "Login successful!");
         
    }    
    
    
}

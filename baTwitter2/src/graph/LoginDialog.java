package graph;

import javax.swing.*;

import code.LoginTest;
import code.LoginTest2;
import twitter4j.Twitter;
import twitter4j.auth.RequestToken;
import twitter4j.examples.oauth.GetAccessToken;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class LoginDialog extends JDialog implements ActionListener {
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

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == okButton) {
        	LoginTest2 lg2= new LoginTest2();
        	try {
				//lg2.openURL(lg2.);
				PinZone pz=new PinZone();
				pz.setVisible(true);
				this.setVisible(false);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } 
    }



    
    public static void main(String[] args) {
        LoginDialog login = new LoginDialog();
           
            JOptionPane.showMessageDialog(login, "Login successful!");
         
    }    
    
    
}

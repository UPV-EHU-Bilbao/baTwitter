package Core;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;


import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWINDOW extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtSartuHemenZure;
	private static LoginTest lg;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWINDOW window = new LoginWINDOW();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public LoginWINDOW() {
		this.setTitle("LOGIN");
		this.setVisible(true);
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(73, 105, 312, 62);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtSartuHemenZure = new JTextField();
		txtSartuHemenZure.setText("Sartu hemen zure PIN kodea");
		txtSartuHemenZure.setEditable(false);
		txtSartuHemenZure.setBounds(150, 74, 153, 20);
		frame.getContentPane().add(txtSartuHemenZure);
		txtSartuHemenZure.setColumns(10);
		
		JButton btnLogin_1 = new JButton("Login");
		btnLogin_1.setBounds(197, 208, 89, 23);
		frame.getContentPane().add(btnLogin_1);
		
		JButton btnLogin = new JButton("Autorizatu");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnLogin.setBounds(140, 40, 173, 23);
		frame.getContentPane().add(btnLogin);
	}
}

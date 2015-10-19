package graph;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginTest2;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;

public class PinZone extends JFrame {

	private JPanel contentPane;
	private JTextField Pin;
	private JTextField PinTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PinZone frame = new PinZone();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PinZone() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Pin = new JTextField();
		Pin.setBounds(23, 96, 164, 41);
		contentPane.add(Pin);
		Pin.setColumns(10);
		
		PinTitle = new JTextField();
		PinTitle.setText("Sartu PIN hemen");
		PinTitle.setEditable(false);
		PinTitle.setBounds(45, 65, 97, 20);
		contentPane.add(PinTitle);
		PinTitle.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginTest2 lg2=LoginTest2.getLogintest2();
				try {
					lg2.getAccessToken(Pin.getText());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnOk.setBounds(262, 105, 89, 23);
		contentPane.add(btnOk);
		
	}
	
	
}

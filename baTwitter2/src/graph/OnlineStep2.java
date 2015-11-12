package graph;


import java.awt.EventQueue;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginCode;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class OnlineStep2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
					OnlineStep2 frame = new OnlineStep2();
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
	public OnlineStep2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 245);
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
		PinTitle.setBounds(45, 65, 100, 20);
		contentPane.add(PinTitle);
		PinTitle.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoginCode.getLoginCode().getAccessToken(Pin.getText());
					dispose();
					new Twitter().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnOk.setBounds(261, 105, 89, 23);
		contentPane.add(btnOk);
		
	}
	
	
}

package graph;


import java.awt.EventQueue;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginBeharrezkoKode;
import twitterGraphs.Osoa;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class BigarrenLehioa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Pin;
	private JTextField PinTitle;
	//private BackBone bb=new BackBone();

	/**
	 * Launch the application.
	 */
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("RadioButtonDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        BigarrenLehioa newContentPane = new BigarrenLehioa();
	        frame.setContentPane(newContentPane);

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
	

	/**
	 * Create the frame.
	 */
	public BigarrenLehioa() {
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
		PinTitle.setBounds(45, 65, 107, 20);
		contentPane.add(PinTitle);
		PinTitle.setColumns(10);
		
		
		JRadioButton rdbtnGogoratuPasahitza = new JRadioButton("Gogoratu pasahitza");
		rdbtnGogoratuPasahitza.setBounds(50, 169, 107, 20);
		contentPane.add(rdbtnGogoratuPasahitza);
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					LoginBeharrezkoKode.getLoginCode().getAccessToken(Pin.getText());
					dispose();
					if (rdbtnGogoratuPasahitza.isSelected()) {
						LoginBeharrezkoKode.getLoginCode().SaveToken();
					}
					HirugarrenLehioa.main(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnOk.setBounds(262, 105, 89, 23);
		contentPane.add(btnOk);
		
	}
}


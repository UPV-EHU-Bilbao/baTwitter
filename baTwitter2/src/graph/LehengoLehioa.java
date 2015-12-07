package graph;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginBeharrezkoKode;

import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LehengoLehioa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("RadioButtonDemo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        LehengoLehioa newContentPane = new LehengoLehioa();
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
	public LehengoLehioa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnOffline = new JButton("OFFLINE");
		contentPane.add(btnOffline, BorderLayout.WEST);
		
		JButton btnOnline = new JButton("ONLINE");
		btnOnline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(LoginBeharrezkoKode.getLoginCode().isTokenSet()){
						LoginBeharrezkoKode.getLoginCode().LoginWithCredentials();
						dispose();
						Osoa.main(null);
						
					}else{
						LoginBeharrezkoKode.getLoginCode().Login();
						dispose();
						Osoa.main(null);
						contentPane.setVisible(false);
					}
					
				}	catch(Exception e1){
					e1.printStackTrace();
				}
        		
			}
		});
		contentPane.add(btnOnline, BorderLayout.EAST);
	}

}


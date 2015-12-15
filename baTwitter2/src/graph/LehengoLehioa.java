package graph;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginBeharrezkoKode;
import dbRelated.DBK;
import exception.Salbuespenak;

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
	        JFrame frame = new JFrame("BATwitter");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        //Create and set up the content pane.
	        LehengoLehioa newContentPane = new LehengoLehioa();
	        frame.setContentPane(newContentPane);

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	        frame.setResizable(false);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            @Override
				public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
	
	/**
	 * Create the frame.
	 */
	public LehengoLehioa() {
		setTitle("Sartu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{212, 212, 0};
		gbl_contentPane.rowHeights = new int[]{251, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		
		JButton btnSartu = new JButton("Sartu");
		btnSartu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(LoginBeharrezkoKode.getLoginCode().isTokenSet()){
						LoginBeharrezkoKode.getLoginCode().LoginWithCredentials();
						dispose();
						new HirugarrenLehioa().setVisible(true);
						contentPane.setVisible(false);

					}else{
						LoginBeharrezkoKode.getLoginCode().Login();
						dispose();
						new BigarrenLehioa().setVisible(true);
						contentPane.setVisible(false);
					}
					
				}	catch(Exception e1){
					throw new Salbuespenak("Ez dago Twitter-era konexiorik edo Datu Basean errorea dago");
				}
        		
			}
		});
		GridBagConstraints gbc_btnSartu = new GridBagConstraints();
		gbc_btnSartu.insets = new Insets(0, 0, 0, 5);
		gbc_btnSartu.gridx = 0;
		gbc_btnSartu.gridy = 0;
		contentPane.add(btnSartu, gbc_btnSartu);
		
		JButton btnDatuBaseaEzabatu = new JButton("Datu Basea Ezabatu");
		GridBagConstraints gbc_btnDatuBaseaEzabatu = new GridBagConstraints();
		gbc_btnDatuBaseaEzabatu.gridx = 1;
		gbc_btnDatuBaseaEzabatu.gridy = 0;
		contentPane.add(btnDatuBaseaEzabatu, gbc_btnDatuBaseaEzabatu);
		btnDatuBaseaEzabatu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					DBK.getInstantzia().ClearDB();
					
				}	catch(Exception e1){
					throw new Salbuespenak("Ezin da datu basera sartu.");
				}
        		
			}
		});
	}
	

}


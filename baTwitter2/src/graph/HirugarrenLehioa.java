package graph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import code.DeskargaKudeatzailea;
import code.HariKudeatzailea;
import code.LoginBeharrezkoKode;
import dbRelated.DBK;
import twitterGraphs.Osoa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HirugarrenLehioa extends JFrame {

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
	        JFrame frame = new JFrame("HirugarrenLehioa");
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
	public HirugarrenLehioa() {
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
		
		
		
		JButton btnDeskargatu = new JButton("Deskargatu");
		btnDeskargatu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					DeskargaKudeatzailea desk= new DeskargaKudeatzailea(null);
					//desk.faboritoak(LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName(), DBK.getInstantzia());
					desk.jarraitzaileak(DBK.getInstantzia()); //FUNCIONA
					/**TERMINA el de arriba, pero no sigue el de abajo**/
					desk.jarraituak(DBK.getInstantzia());  //FUNCIONA
					//desk.nireTweet(LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName(), DBK.getInstantzia());
					//HariKudeatzailea.main(null);
				}catch(Exception e){
					e.printStackTrace();
				}
        		
			}
		});
		GridBagConstraints gbc_btnDeskargatu = new GridBagConstraints();
		gbc_btnDeskargatu.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeskargatu.gridx = 0;
		gbc_btnDeskargatu.gridy = 0;
		contentPane.add(btnDeskargatu, gbc_btnDeskargatu);
		
		JButton btnIkusi = new JButton("DatuBasea Ikusi");
		GridBagConstraints gbc_btnIkusi = new GridBagConstraints();
		gbc_btnIkusi.gridx = 1;
		gbc_btnIkusi.gridy = 0;
		contentPane.add(btnIkusi, gbc_btnIkusi);
		btnIkusi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Osoa.main(null);
        		
			}
		});
	}
	

}


package graph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import code.DeskargaKudeatzailea;
import code.HariKudeatzailea;
import code.LoginBeharrezkoKode;
import dbRelated.DBK;
import dbRelated.InformazioHartzaile;
import twitter4j.TwitterException;
import twitterGraphs.Osoa;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Window.Type;

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
		setFont(new Font("Arial Black", Font.PLAIN, 12));
		setTitle("Tweet Kudeaketa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JButton btnDeskargatuTW = new JButton("Deskargatu Kontuko Tweetak");
		btnDeskargatuTW.setBounds(10, 60, 173, 23);
		btnDeskargatuTW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					DeskargaKudeatzailea desk= new DeskargaKudeatzailea();
					desk.nireTweet();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		JButton btnDeskargatuRT = new JButton("Deskargatu Kontuko RT-ak");
		btnDeskargatuRT.setBounds(10, 94, 173, 23);
		btnDeskargatuRT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{

					DeskargaKudeatzailea desk= new DeskargaKudeatzailea();
					desk.rtJaitsi();
				}catch(Exception e){
					e.printStackTrace();
				}
        		
			}
		});
		JButton btnDeskargatuFav = new JButton("Download Fav");
		btnDeskargatuFav.setBounds(10, 196, 173, 23);
		btnDeskargatuFav.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{

					DeskargaKudeatzailea desk= new DeskargaKudeatzailea();
					desk.gustokoakJaitsi();
					}catch(Exception e){
					e.printStackTrace();
				}
        		
			}
		});
		JButton btnDeskargatuJarr = new JButton("Download Follower");
		btnDeskargatuJarr.setBounds(10, 128, 173, 23);

		btnDeskargatuJarr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{

					DeskargaKudeatzailea desk= new DeskargaKudeatzailea();
					desk.jarraitzaileak(DBK.getInstantzia());
				}catch(Exception e){
					e.printStackTrace();
				}
        		
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnDeskargatuTW);
		contentPane.add(btnDeskargatuRT);
		contentPane.add(btnDeskargatuFav);
		contentPane.add(btnDeskargatuJarr);
		JButton btnDeskargatuJarr2 = new JButton("Download Followed");
		btnDeskargatuJarr2.setBounds(10, 162, 173, 23);
		btnDeskargatuJarr2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					DeskargaKudeatzailea desk= new DeskargaKudeatzailea();
					desk.jarraituak(DBK.getInstantzia());
					}catch(Exception e){
					e.printStackTrace();
				}
        		
			}
		});
		contentPane.add(btnDeskargatuJarr2);

		JButton btnIkusi = new JButton("DatuBasea Ikusi");
		btnIkusi.setBounds(220, 89, 191, 100);
		contentPane.add(btnIkusi);
		btnIkusi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Osoa.main(null);
				} catch (IllegalStateException | SQLException | TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
			}
		});
	}
	

}


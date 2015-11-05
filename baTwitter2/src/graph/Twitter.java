package graph;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import code.BackBone;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Twitter extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BackBone bb;
	private ArrayList<Status> statuses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Twitter frame = new Twitter();
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
	public Twitter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		@SuppressWarnings("rawtypes")
		/*JList list = new JList();
		list.setBounds(21, 25, 450, 450);
		contentPane.add(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane listScroller = new JScrollPane(list);*/

		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(600, 25, 350, 450);
		contentPane.add(lblNewLabel);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					statuses=(ArrayList<Status>) bb.getTimeline();
				} catch (TwitterException e) {
					e.printStackTrace();
				}
				JList list = new JList(statuses.toArray());
				list.setBounds(21, 25, 450, 450);
				contentPane.add(list);
				list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				JScrollPane listScroller = new JScrollPane(list);
			}
		});
		
		btnActualizar.setBounds(861, 449, 89, 23);
		contentPane.add(btnActualizar);
		
		
	}
}

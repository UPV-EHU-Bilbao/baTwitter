package graph;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import code.LoginCode;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class OnlineOfflineFrame1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineOfflineFrame1 frame = new OnlineOfflineFrame1();
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
	public OnlineOfflineFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 175, 118);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnOffline = new JButton("OFFLINE");
		btnOffline.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(btnOffline, BorderLayout.EAST);
		
		JButton btnOnline = new JButton("ONLINE");
		btnOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					LoginCode.getLoginCode().Login();
					dispose();
					new OnlineStep2().setVisible(true);
					contentPane.setVisible(false);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		
			}
		});
		contentPane.add(btnOnline, BorderLayout.WEST);
	}

}

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
			@Override
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
					if(LoginCode.getLoginCode().isTokenSet()){
						LoginCode.getLoginCode().LoginWithCredentials();
						dispose();
						new Twitter().setVisible(true);;
						contentPane.setVisible(false);
					}else{
						LoginCode.getLoginCode().Login();
						dispose();
						new OnlineStep2().setVisible(true);
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

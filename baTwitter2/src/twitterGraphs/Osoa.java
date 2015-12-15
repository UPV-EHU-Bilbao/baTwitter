package twitterGraphs;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import twitter4j.TwitterException;

public class Osoa extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824362418664576996L;
	private JPanel contentPane;
	private JPanel contentPaneT;
	private JComponent newContentPane;
	private TableDemo taula;
	/**
	 * Launch the application.
	 * @throws TwitterException 
	 * @throws SQLException 
	 * @throws IllegalStateException 
	 */
	public static void main(String[] args) throws IllegalStateException, SQLException, TwitterException {
		
		Osoa frame = new Osoa();
		frame.hasieratu("");
	}

	private void hasieratu(String actionCommand) throws IllegalStateException, SQLException, TwitterException {
		// TODO Auto-generated method stub

		newContentPane = new RadioButtonDemo(this);
		
        setContentPane(newContentPane);
        taula = new TableDemo("");
        getContentPane().remove(taula);
   		taula = new TableDemo(actionCommand);
   		getContentPane().add(taula);
   	
		getContentPane().add(taula);
		setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public Osoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 0, 1000, 700);
		contentPane = new JPanel();
		contentPaneT = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPaneT.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setContentPane(contentPaneT);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		contentPaneT.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	}

	


	 public void bistaratu(String actionCommand) throws IllegalStateException, SQLException, TwitterException {
		 hasieratu(actionCommand);}
	
}


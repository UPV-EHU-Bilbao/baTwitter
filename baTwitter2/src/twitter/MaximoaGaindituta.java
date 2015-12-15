package twitter;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author 
 *
 */
public class MaximoaGaindituta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7862008761580557329L;
	private JPanel contentPane;
	/**
	 * @wbp.nonvisual location=49,47
	 */
	private final JTextField textField = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MaximoaGaindituta frame = new MaximoaGaindituta();
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
	public MaximoaGaindituta() {
		textField.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(191, 43, -95, 60);
		//textPane.setText(Integer.toString(i));
		
		
	}
}

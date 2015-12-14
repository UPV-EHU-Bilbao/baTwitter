package exception;

import javax.swing.JOptionPane;

public class Salbuespenak extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8338441906212612358L;

	public Salbuespenak(String mezua) {
		JOptionPane.showMessageDialog(null, mezua, "Errorea", JOptionPane.ERROR_MESSAGE);
	}
}
package twitterGraphs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dbRelated.Exportatzailea;

public class ExpFrame {

	public void gorde() {
		String path = "";
		
		JFileChooser gureFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		gureFileChooser.setAcceptAllFileFilterUsed(false);
		int aukera = JOptionPane.NO_OPTION;
		while (aukera == JOptionPane.NO_OPTION) {
			int gureZenbakia = gureFileChooser.showSaveDialog(null);
			if (gureZenbakia == JFileChooser.CANCEL_OPTION)
				return;
			try {
				path = gureFileChooser.getSelectedFile().getAbsolutePath();
				
				File f = new File(path);
				if (f.exists()) {
					String[] aukerak = { "Bai", "Ez" };
					aukera = JOptionPane.showOptionDialog(null, "Liburua existitzen da jada.\nEzabatu nahi duzu?",
							"Gorde Aukerak", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, aukerak,
							aukerak[0]);
				} else
					aukera = JOptionPane.YES_OPTION;
			} catch (Exception salbuespena) {
				
			}
		}
		Exportatzailea exp= new Exportatzailea();
		if (exp.save(path))
			JOptionPane.showMessageDialog(null,
					"Excel liburua " + path + " karpetan gorde da", "Gorde da",
					JOptionPane.INFORMATION_MESSAGE);

	}
}

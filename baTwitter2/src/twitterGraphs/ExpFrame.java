package twitterGraphs;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dbRelated.Exportatzailea;
import exception.Salbuespenak;

public class ExpFrame {

	public void gorde() {
		String path = "";
		
		JFileChooser nireFileChooser = new JFileChooser(new File(System.getProperty("user.home")));
		nireFileChooser.setAcceptAllFileFilterUsed(false);
		int aukera = JOptionPane.NO_OPTION;
		while (aukera == JOptionPane.NO_OPTION) {
			int nireZenbakia = nireFileChooser.showSaveDialog(null);
			if (nireZenbakia == JFileChooser.CANCEL_OPTION)
				return;
			try {
				path = nireFileChooser.getSelectedFile().getAbsolutePath();
				File f = new File(path);
				if (f.exists()) {
					String[] aukerak = { "Bai", "Ez" };
					aukera = JOptionPane.showOptionDialog(null, "Liburua existitzen da jada.\nEzabatu nahi duzu?",
							"Gorde Aukerak", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, aukerak,
							aukerak[0]);
				} else
					aukera = JOptionPane.YES_OPTION;
			} catch (Exception salbuespena) {
				throw new Salbuespenak("Errorea egon da fitxategia gordetzerakoan.");
			}
		}
		Exportatzailea exp= new Exportatzailea();
		if (exp.save(path))
			JOptionPane.showMessageDialog(null,
					"Excel fitzategia " + path + " karpetan gorde da", "Gorde",
					JOptionPane.INFORMATION_MESSAGE);

	}
}

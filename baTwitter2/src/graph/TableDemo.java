package graph;

import java.awt.GridLayout;

import javax.swing.*;

public class TableDemo extends JPanel {
	public TableDemo(String mota){
		super(new GridLayout(1,0));
		JTable table= new JTable(new NireTaulaModeloa(mota));
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
	}

}

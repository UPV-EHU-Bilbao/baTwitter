package twitterGraphs;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.*;

import twitter4j.TwitterException;

public class TableDemo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1144765929570330501L;

	public TableDemo(String mota) throws IllegalStateException, SQLException, TwitterException{
		super(new GridLayout(1,0));
		JTable table= new JTable(new NireTaulaModeloa(mota));
		JScrollPane scroll = new JScrollPane(table);
		add(scroll);
	}

}

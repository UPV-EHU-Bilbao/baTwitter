package twitterGraphs;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import code.Tweet;
import dbRelated.InformazioHartzaile;
import twitter4j.TwitterException;

public class NireTaulaModeloa extends AbstractTableModel{
	
	private String[] kolumnaIzenak;
	private String mota;
	private ArrayList<Tweet> nireak= new ArrayList<Tweet>();
	private ArrayList<Tweet> rtak= new ArrayList<Tweet>();
	private ArrayList<Tweet> faboritoak= new ArrayList<Tweet>();
	private ArrayList<String> jarraitzaileak= new ArrayList<String>();
	private ArrayList<String> jarraituak= new ArrayList<String>();
	private InformazioHartzaile infor= new InformazioHartzaile();


	
	public NireTaulaModeloa(String mota) throws IllegalStateException, SQLException, TwitterException{
		this.mota=mota;
		
		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
			kolumnaIzenak= new String[1];
		}
		else
		kolumnaIzenak= new String[3];
		
		hasieratuKolumnak();
		kargatu(mota);
	}
	

	private ArrayList<Tweet> data = new ArrayList<Tweet>();
	private ArrayList<String> lista= new ArrayList<String>();
	
	public void kargatu(String mota) throws SQLException, IllegalStateException, TwitterException{
		
		if(mota.equals("Jarraitzaileak")){
			lista=infor.getJarraitzaileInfo();
			
		}
		else if(mota.equals("Jarraituak")){
			lista=infor.getJarraituakInfo();
		}
		else if(mota.equals("Favorito")){
			data=infor.getFavInfo();
		}
		else if(mota.equals("Nire Tweet-ak")){
			data=infor.getTweetInfo();
		}
		else if(mota.equals("ReTweet")) {
			data=infor.getRTInfo();
		}
	
	}
	
	private void hasieratuKolumnak(){
		if((mota.equals("Jarraitzaileak"))||(mota.equals("Jarraituak"))){
			kolumnaIzenak[0]="Izena";

		}
		
		else{
		kolumnaIzenak[0]="Idazlea";
		kolumnaIzenak[1]="Textua";
		kolumnaIzenak[2]="Rt kop";
		//kolumnaIzenak[3]="Idazlea";
		//kolumnaIzenak[4]="Fav";
		}

	}
	
	public int getColumnCount(){
		return kolumnaIzenak.length;		
	}

	public int getRowCount(){
		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
			return lista.size();
		}
		else
		return data.size();
	}

	public String getColumnName(int i){
		return kolumnaIzenak[i];
	}

	public Object getValueAt(int row, int col){
		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
			return lista.get(row);
		}
		else
		return data.get(row).getBalioa(col);
		
	}
	


	public boolean isCellEditable(int row, int col){
		return false;
	}

	
}

	

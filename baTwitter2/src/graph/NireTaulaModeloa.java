package graph;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import code.Tweet;

public class NireTaulaModeloa extends AbstractTableModel{
	
	private String[] kolumnaIzenak;
	private String mota;
	
	public NireTaulaModeloa(String mota){
		this.mota=mota;
		
		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
			kolumnaIzenak= new String[1];
		}
		else
		kolumnaIzenak= new String[5];
		
		hasieratuKolumnak();
		kargatu(mota);
	}
	

	private ArrayList<Tweet> data = new ArrayList<Tweet>();
	private ArrayList<String> lista= new ArrayList<>();
	
	public void kargatu(String mota){
		//Hartu db-tik datuak
		if(mota.equals("Favoritoak")){
			
		}
		else if(mota.equals("ReTweet")){
			
		}
		else if(mota.equals("Jarraituak")){
			
		}
		else if(mota.equals("Jarraitzaileak")){
			
		}
		else{
			
		}
		
	}
	
	private void hasieratuKolumnak(){
		if((mota.equals("Jarraitzaileak"))||(mota.equals("Jarraituak"))){
			kolumnaIzenak[0]="Erabiltzaile Izena";

		}
		
		else{
		kolumnaIzenak[0]="Idazlea";
		kolumnaIzenak[1]="Textua";
		kolumnaIzenak[2]="Rt kop";
		kolumnaIzenak[3]="fav Kop";
		kolumnaIzenak[4]="url";}

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

	

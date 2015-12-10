package graph;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import code.Tweet;

/**
 * Tweet-ak eta erabiltzaileak erakusteko taulak sortzeko metodoa 
 * @author BATwitter
 *
 */
public class NireTaulaModeloa{
//	
//	private String[] kolumnaIzenak;
//	private String mota;
//	
//	/**
//	 * Taula Modeloa sortzen du, 2 modelo egongo dira: Tweet-en modeloa eta erabiltzaile modeloa.
//	 * @param mota
//	 */
//	public NireTaulaModeloa(String mota){
//		this.mota=mota;
//		
//		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
//			kolumnaIzenak= new String[1];
//		}
//		else
//		kolumnaIzenak= new String[5];
//		
//		hasieratuKolumnak();
//		//kargatu();
//	}
//	
//
//	private ArrayList<Tweet> data = new ArrayList<Tweet>();
//	private ArrayList<String> lista= new ArrayList<>();
//	
//	/**
//	 * Motaren arabera datu basetik datu ezberdinak hartuko ditu, eta taulan erakutsiko dira.
//	 * @param mota  Mota parametroa tweet mota edo erabiltzaile mota izango da.
//	 */
//	
//	public void kargatu(String mota){
//		//Hartu db-tik datuak
//		if(mota.equals("Jarraitzaileak")){}
//		else if(mota.equals("Jarraituak")){}
//		else if(mota.equals("Retweet")){}
//		else if(mota.equals("Favorito")){}
//		else{}
//
//	
//	}
//	
//	/**
//	 * Taularen hasierako kolumnen izenak idatzi egingo ditu.
//	 * Bi hasiera mota daude, erabiltzaileena eta tweet-ena.
//	 */
//	private void hasieratuKolumnak(){
//		if((mota.equals("Jarraitzaileak"))||(mota.equals("Jarraituak"))){
//			kolumnaIzenak[0]="Izena";
//
//		}
//		
//		else{
//		kolumnaIzenak[0]="Idazlea";
//		kolumnaIzenak[1]="Textua";
//		kolumnaIzenak[2]="Rt kop";
//		kolumnaIzenak[3]="fav Kop";
//		kolumnaIzenak[4]="url";}
//
//	}
//	
//	/**
//	 * Kolumna kopurua bueltazten du. (2 edo 5)
//	 */
//	public int getColumnCount(){
//		return kolumnaIzenak.length;		
//	}
//
//	/**
//	 * ilara kopurua bueltatzen du.
//	 */
//	public int getRowCount(){
//		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
//			return lista.size();
//		}
//		else
//		return data.size();
//	}
//
//	/**
//	 * kolumnaren izena ematen du
//	 * @param kolumnaren zenbakia
//	 */
//	public String getColumnName(int i){
//		return kolumnaIzenak[i];
//	}
//
//	/**
//	 * kolumna eta ilara bat emanda, bertako datua bueltatzen du.
//	 */
//	public Object getValueAt(int row, int col){
//		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
//			return lista.get(row);
//		}
//		else
//		return data.get(row).getBalioa(col);
//		
//	}
//	
//
//
//	/**
//	 * Zeldak ez dira editagarriak.
//	 */
//	public boolean isCellEditable(int row, int col){
//		return false;
//	}
//
	
}

	

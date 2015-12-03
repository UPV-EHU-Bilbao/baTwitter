import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

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
		kargatu();
	}
	

	private ArrayList<Tweet> data = new ArrayList<Tweet>();
	private ArrayList<String> lista= new ArrayList<>();
	
	public void kargatu(){
		if(mota.equals("Jarraitzaileak")||mota.equals("Jarraituak")){
			lista.add("Bum");
		}
		else{
		Tweet t= new Tweet("Bingenzio","Oh may goood",3,4,9,"igandea.com");
		data.add(t);}

	
	}
	
	private void hasieratuKolumnak(){
		if((mota.equals("Jarraitzaileak"))||(mota.equals("Jarraituak"))){
			kolumnaIzenak[0]="Izena";

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

	
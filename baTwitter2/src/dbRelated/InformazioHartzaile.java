package dbRelated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.Tweet;
import exception.Salbuespenak;

public class InformazioHartzaile {

		
		public InformazioHartzaile(){
		}
		public String getSuperUserIzena(){
			String superuser="";
			try{
				ResultSet rs= DBK.getInstantzia().execSQL("Select izena from superuser");
				while (rs.next()){
					superuser= rs.getString(1);
				}
			}catch (Exception e){
				throw new Salbuespenak("Ezin da erabiltzailearen izena irakurri");
			}
			return superuser;
		}
		public ArrayList<Tweet> getTweetInfo(){
			ArrayList<Tweet>lista= new ArrayList<>();
			try{
			ResultSet rs=DBK.getInstantzia().execSQL("Select * from twit where USER_izena!= '"+getSuperUserIzena()+"'");
			while (rs.next()){
				//(String text,int RT, int Fav, int RTCount,int FAVCount,String URL, String Image,long tweetID, String USER_izena)
				String izena=rs.getString("USER_izena");
				String textua=rs.getString(1);				
				long id = rs.getLong(8);
				int RTKop=rs.getInt("rtKop");
				int FavKop=rs.getInt("favKop");
				
				
				boolean rt;
				boolean fav;
				if(rs.getInt(2)==1){
					 rt=true;
				}
				else
					 rt=false;
				if(rs.getInt(3)==1)fav=true;
				else fav=false;
				String url=" ";
				if(!rs.getString(6).isEmpty()){
					 url= "www."+rs.getString(6);
			}
			Tweet tw= new Tweet(textua, izena, rt, fav, RTKop, FavKop, id, url);
			lista.add(tw);
			}
			}catch(Exception e){
				throw new Salbuespenak("Ezin dira tweet-ak lortu");
			}
			
			return lista;
		}
		
		public ArrayList<String> getJarraitzaileInfo(){
			ArrayList<String> lista = new ArrayList<String>();
			try{
			ResultSet rs=DBK.getInstantzia().execSQL("Select * from user where jarraitzailea=1");
			while (rs.next()){
				String izena=rs.getString(1);
				lista.add(izena);
				}
			}catch(Exception e){
				throw new Salbuespenak("Ezin dira jarraitzaileak lortu");
			}
			return lista;
			}
		
		
		public ArrayList<String> getJarraituakInfo() {
			ArrayList<String> lista = new ArrayList<String>();
			try{
			ResultSet rs=DBK.getInstantzia().execSQL("Select * from user where jarraitua=1");
			while (rs.next()){
				String izena=rs.getString(1);
				lista.add(izena);
				}
			}catch (Exception e){
				throw new Salbuespenak("Ezin dira jarraituak lortu");
			}
			return lista;
				
		}
		
		public ArrayList<Tweet> getFavInfo(){
			ArrayList<Tweet>lista= new ArrayList<>();

			try{
			ResultSet rs=DBK.getInstantzia().execSQL("Select * from twit where fav=1");
			while (rs.next()){
				String izena=rs.getString(9);
				String textua=rs.getString(1);				
				long id = rs.getLong(8);
				int RTKop=rs.getInt("rtKop");
				int FavKop=rs.getInt("favKop");
				
				
				boolean rt;
				boolean fav;
				if(rs.getInt(2)==1){
					 rt=true;
				}
				else
					 rt=false;
				if(rs.getInt(3)==1)fav=true;
				else fav=false;
				String url=" ";
				if(!rs.getString(6).equals("0")){
					 url= "www."+rs.getString(6);
			}
			Tweet tw= new Tweet(textua, izena, rt, fav, RTKop, FavKop, id, url);
			lista.add(tw);
			}
			}catch(SQLException e){
				throw new Salbuespenak("Ezin dira Favoritoak lortu");
			}
			return lista;
		}
		
		public ArrayList<Tweet> getRTInfo(){
			ArrayList<Tweet>lista= new ArrayList<>();
			try{
			ResultSet rs=DBK.getInstantzia().execSQL("Select * from twit where rt=1");
			while (rs.next()){
				String izena=rs.getString(9);
				String textua=rs.getString(1);				
				long id = rs.getLong(8);
				int RTKop=rs.getInt("rtKop");
				int FavKop=rs.getInt("favKop");
				
				
				boolean rt;
				boolean fav;
				if(rs.getInt(2)==1){
					 rt=true;
				}
				else
					 rt=false;
				if(rs.getInt(3)==1)fav=true;
				else fav=false;
				String url=" ";
				if(!rs.getString(6).isEmpty()){
					 url= "www."+rs.getString(6);
			}
			Tweet tw= new Tweet(textua, izena, rt, fav, RTKop, FavKop, id, url);
			lista.add(tw);
			}
			}catch(Exception e){
				throw new Salbuespenak("Ezin dira Retweet-ak lortu");
			}
			return lista;
		}
	
		
		public long getID(String mota, String orden){
			
				ResultSet emaitza = DBK.getInstantzia().execSQL(
						"SELECT ID FROM twit WHERE "+mota+" =1 ORDER BY ID "+orden+"");
				try {
					if (emaitza.next())
						return emaitza.getLong(1);
					else if (orden.equals("ASC"))
						return 1L;
					else
						return Long.MAX_VALUE;
				} catch (SQLException e) {
					return 1L;
				}
		}
		
		
		
		
		
}

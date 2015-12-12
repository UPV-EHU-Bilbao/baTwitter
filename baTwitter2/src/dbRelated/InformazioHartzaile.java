package dbRelated;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.LoginBeharrezkoKode;
import code.Tweet;
import twitter4j.TwitterException;

public class InformazioHartzaile {

		private static DBK dbk;
		
		public InformazioHartzaile(){
			dbk=DBK.getInstantzia();
		}
		
		public ArrayList<Tweet> getTweetInfo() throws SQLException, IllegalStateException, TwitterException{
			String usr=LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName();
			ResultSet rs=dbk.execSQL("Select * from twit where USER_izena!='"+usr+"'" );
			ArrayList<Tweet>lista= new ArrayList<>();
			while (rs.next()){
				//(String text,int RT, int Fav, int RTCount,int FAVCount,String URL, String Image,long tweetID, String USER_izena)
				String izena=rs.getString("USER_izena");
				String textua=rs.getString(1);
				System.out.println(textua);
				
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
			return lista;
		}
		
		public ArrayList<String> getJarraitzaileInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from user where jarraitzailea=1");
			ArrayList<String> lista = new ArrayList<String>();
			while (rs.next()){
				String izena=rs.getString(1);
				System.out.println(izena);
				lista.add(izena);
				}
			return lista;
			}
		
		
		public ArrayList<String> getJarraituakInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from user where jarraitua=1");
			ArrayList<String> lista = new ArrayList<String>();
			while (rs.next()){
				String izena=rs.getString(1);
				System.out.println(izena);
				lista.add(izena);
				}
			return lista;
				
		}
		
		public ArrayList<Tweet> getFavInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from twit where fav=1");
			ArrayList<Tweet>lista= new ArrayList<>();
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
			return lista;
		}
		
		public ArrayList<Tweet> getRTInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from twit where rt=1");
			ArrayList<Tweet>lista= new ArrayList<>();
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

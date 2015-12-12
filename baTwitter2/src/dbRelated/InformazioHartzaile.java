package dbRelated;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformazioHartzaile {

		private static DBK dbk;
		
		public InformazioHartzaile(){
			dbk=DBK.getInstantzia();
		}
		
		public void getTweetInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from twit");
			while (rs.next()){
				String izena=rs.getString(9);
				String textua=rs.getString(1);
				System.out.println(textua);

				int RTKop=rs.getInt(6);
				int FavKop=rs.getInt(7);
				if(!rs.getString(1).isEmpty()){
					String Url= "www."+rs.getString(1);
			}
			}
		}
		
		public void getJarraitzaileInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from user where jarraitzailea=1");
			while (rs.next()){
				String izena=rs.getString(1);
				System.out.println(izena);

				}
			}
		
		
		public void getJarraituakInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from user where jarraitua=1");
			while (rs.next()){
				String izena=rs.getString(1);
				System.out.println(izena);

				}
		}
		
		public void getFavInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from twit where fav=1");
			while (rs.next()){
				String izena=rs.getString(9);
				String textua=rs.getString(1);
				System.out.println(textua);

				int RTKop=rs.getInt(6);
				int FavKop=rs.getInt(7);
				if(!rs.getString(1).isEmpty()){
						String Url= "www."+rs.getString(1);
				}
			}
		}
		
		public void getRTInfo() throws SQLException{
			ResultSet rs=dbk.execSQL("Select * from twit where rt=1");
			while (rs.next()){
				String izena=rs.getString(9);
				String textua=rs.getString(1);
				System.out.println(textua);
				int RTKop=rs.getInt(6);
				int FavKop=rs.getInt(7);
				if(!rs.getString(1).isEmpty()){
						String Url= "www."+rs.getString(1);
				}
			}
		}
		public void main(String[] args) throws SQLException {
			InformazioHartzaile info=new InformazioHartzaile();
			info.getFavInfo();
			info.getRTInfo();
		}
		
}

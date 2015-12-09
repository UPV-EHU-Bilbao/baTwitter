package dbRelated;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;



public class DBK{

	Connection conn = null;

	private void conOpen() {
		try {
			String url = "jdbc:sqlite:newTwitter.db";
			Class.forName("org.sqlite.JDBC").newInstance();
			
			conn = (Connection) DriverManager.getConnection(url);
			System.out.println("Database connection established");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
		}
		
		
	}
	
	



		


	private void conClose() {

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		System.out.println("Database connection terminated");

	}

	private ResultSet query(Statement s, String query) {

		ResultSet rs = null;

		try {
			rs = s.executeQuery(query);
			// rs = s.getResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rs;
	}

	// singleton patroia
	private static DBK instantzia = new DBK();

	private DBK() {
		this.conOpen();
	}

	public static DBK getInstantzia() {
		return instantzia;
	}

	//
	public ResultSet execSQL(String query) {
		int count = 0;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = (Statement) conn.createStatement();
			if (query.toLowerCase().indexOf("select") == 0) {
				// select agindu bat
				rs = this.query(s, query);
				
			} else {
				// update, delete, create agindu bat
				count = s.executeUpdate(query);
				System.out.println(count + " rows affected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			if (s != null)
//				try {
//					s.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		}

		return rs;
	}
	
	

	public void saveToken(String Name,String AccessToken,String AccessTokenSecret) throws SQLException{
		
		Statement st =conn.createStatement();
		st.execute("INSERT into superuser (izena, AccessToken,AccessTokenSecret)VALUES('"+Name+"','"+AccessToken+"','"+AccessTokenSecret+"')");
		
	}
	public String[] getATokens() throws SQLException{
       
		Statement st =this.conn.createStatement();
		String[] token= new String[2];
		ResultSet rs=st.executeQuery("SELECT AccessToken,AccessTokenSecret FROM superuser");
		//dump(rs,"executeQuery");
		while (rs.next()) {
			System.out.print("| ");
			int j=rs.getMetaData().getColumnCount();
			for (int i = 1; i <=j ; ++i) {
				Object o = rs.getObject(i);
				token[i-1]=o.toString();
				System.out.print(o + " | ");
			}
			System.out.println();
			System.out.println();
		}
		
		
		
	
		if (st != null){
			st.close();
		}
		return token;
	}
	public boolean isAnyToken() throws SQLException{
		Statement st =this.conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT AccessToken,AccessTokenSecret FROM superuser");
		if (!rs.next()){
			return false;
		}else{
			return true;
			}
		}
	public void saveTweetInfo(String text,boolean RT, boolean Fav, int RTCount,int FAVCount,String URL, String Image,long tweetID, String superuser,String USER_izena) throws SQLException{
		Statement st =this.conn.createStatement();
		st.execute("Insert into twit (edukia,url,irudia,fav,rt,favKop,rtKop,id,SUPERUSER_izena,USER_izena) VALUES ('"+text+"','"+URL+"','"+Image+"','"+Fav+"','"+RT+"','"+FAVCount+"','"+RTCount+"','"+tweetID+"','"+superuser+"','"+USER_izena+"')");
		
	}
	public ResultSet loadAllTweetInfo() throws SQLException{
		Statement st =this.conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM tweet");
		return rs;
	}
	public void ClearDB() throws SQLException{
		Statement st =this.conn.createStatement();
		st.executeQuery("DELETE * FROM twit");
		st.executeQuery("DELETE * FROM superuser");
		st.executeQuery("DELETE * FROM user");

	}
	
	public void saveFollowers(String izena) throws SQLException{
		Statement st =this.conn.createStatement();

		st.executeQuery("Insert into jarraitzaileak (izena,false,true) ");

	}
	//METODO NUEVO
	/*
	public void updateFollower(String izena)throws SQLException{
	Statement st =this.conn.createStatement();

	st.executeQuery("Update superuser.jarraituak=true where superuser.izena="+izena+";");

}*/
	
	public void saveFollowing(String izena) throws SQLException{
		Statement st =this.conn.createStatement();
		st.executeQuery("Insert into jarraituak (izena,true,false) ");
	}
	
	//METODO NUEVO
	/*
	public void updateFollowing(String izena) throws SQLException{
		Statement st =this.conn.createStatement();
		st.executeQuery("Update superuser.jarraituak=true where superuser.izena="+izena+";");
	}*/


}



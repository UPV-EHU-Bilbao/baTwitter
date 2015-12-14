package dbRelated;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import code.LoginBeharrezkoKode;
import twitter4j.TwitterException;


/**
 * Datu base kudeatzailea
 * @author 
 *
 */
public class DBK{

	Connection conn = null;

	/**
	 * Datu basearekin konexioa
	 */
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
	
	



		


	/**
	 * Konexioa itzi egiten du.
	 */
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

	/**
	 * Result set bat bueltatzen du egindako eskaeraren arabera.
	 * @param s
	 * @param query
	 * @return
	 */
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

	/**
	 * Result set bat bueltatzen du egindako eskaeraren arabera.
	 * @return ResultSet Datu basean hartutakoa
	 */
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
	
	

	/**
	 * Tokenak gordetzeko metodoa
	 * @param Name erabiltzaile izena
	 * @param AccessToken 
	 * @param AccessTokenSecret
	 * @throws SQLException
	 */
	public void saveToken(String Name,String AccessToken,String AccessTokenSecret) throws SQLException{
		
		Statement st =conn.createStatement();
		st.execute("INSERT into superuser (izena, AccessToken,AccessTokenSecret)VALUES('"+Name+"','"+AccessToken+"','"+AccessTokenSecret+"')");
		
	}
	/**
	 * Tokken-ak lortzek metodoa
	 * @return Beharresko 2 tokkenak bueltatzen ditu.
	 * @throws SQLException
	 */
	public String[] getATokens() throws SQLException{
       
		Statement st =this.conn.createStatement();
		String[] token= new String[2];
		ResultSet rs=st.executeQuery("SELECT AccessToken,AccessTokenSecret FROM superuser");
		while (rs.next()) {
			int j=rs.getMetaData().getColumnCount();
			for (int i = 1; i <=j ; ++i) {
				Object o = rs.getObject(i);
				token[i-1]=o.toString();
			}
			System.out.println();
			System.out.println();
		}
		
		
		
	
		if (st != null){
			st.close();
		}
		return token;
	}
	/**
	 * komprobatzen du ea tokken-ak datu basean gordeta ba al dauden.
	 * @return
	 * @throws SQLException
	 */
	public boolean isAnyToken() throws SQLException{
		Statement st =this.conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT AccessToken,AccessTokenSecret FROM superuser");
		if (!rs.next()){
			return false;
		}else{
			
			return true;
			}
		}
	/**
	 *  Tweet-a datu basean gordetzeko metodoa, sartu baino lehen komprobatu egingo du ea DB-n ba al zegoen.
	 * @param text
	 * @param RT
	 * @param Fav
	 * @param RTCount
	 * @param FAVCount
	 * @param URL
	 * @param Image
	 * @param tweetID
	 * @param superuser
	 * @param USER_izena
	 * @throws SQLException
	 */
	

		
		
	public void saveTweetInfo(String text,int RT, int Fav, int RTCount,int FAVCount,String URL, String Image,long tweetID, String USER_izena) throws SQLException{
		Statement st =this.conn.createStatement();
		//System.out.println(text);
		if(!this.komprobatuTweet(tweetID)){
			
		this.execSQL("Insert or replace into twit (edukia,url,irudia,fav,rt,favKop,rtKop,id,USER_izena) VALUES ('"+text.replace("'", "''")+"','"+URL+"','"+Image+"',"+Fav+","+RT+","+FAVCount+","+RTCount+","+tweetID+",'"+USER_izena.replace("'", " ")+"')");
		}st.close();
	}
	public void paramSave(long var1, long var2) throws IllegalStateException, TwitterException{
		execSQL("Update superuser set since="+var1+", max="+var2+" where izena='"+LoginBeharrezkoKode.getLoginCode().getTwitterInstance().getScreenName()+"'");
	}
	/**
	 * Tweet-ak hartzeko metodoa
	 * @return tweet guztiak bueltatzen ditu.
	 * @throws SQLException
	 */
	public ResultSet loadAllTweetInfo() throws SQLException{
		Statement st =this.conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM tweet");
		st.close();
		return rs;
	}
	/**
	 * Datu baseko datuak ezabatu
	 * @throws SQLException
	 */
	public void ClearDB() throws SQLException{
		Statement st =this.conn.createStatement();
		st.execute("DELETE FROM twit;");
		st.execute("DELETE FROM superuser;");
		st.execute("DELETE FROM user;");
		st.close();

	}

	
	public void saveFollowers(String erabiltzailea) throws SQLException{
		try{
			Statement st =this.conn.createStatement();
			erabiltzailea.replace("'", "//'");

				st.execute("INSERT INTO user VALUES ('"+erabiltzailea+"', 0, 1)");
				st.close();
			}catch(SQLException e){
				//updateFollowing(erabiltzailea);	
			}
	}
	

	
	/**
	 * Jarraitua gorde
	 * @param izena erabiltzaile izena
	 * @throws SQLException
	 */

	public void saveFollowing(String erabiltzailea) throws IllegalStateException, TwitterException, SQLException{
		try{
		Statement st =this.conn.createStatement();
		erabiltzailea.replace("'", "//'");

			st.execute("INSERT INTO user VALUES ('"+erabiltzailea+"', 1, 0)");
			st.close();
		}catch(SQLException e){
			//updateFollowing(erabiltzailea);	
		}
		
	}
	
	
	public void updateFollowing(String izena) throws SQLException{
		Statement st =this.conn.createStatement();
		st.execute("Update user set jarraitua=1 where izena='"+izena+"';");
		st.close();
	}


	
	private boolean komprobatuTweet(long tweetId) throws SQLException{
		Statement st =this.conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM twit WHERE id='"+tweetId+"'");
		st.close();
		if (!rs.next()){
			return false;
		}else{
			return true;
			}
		
	}
	
	

	
}



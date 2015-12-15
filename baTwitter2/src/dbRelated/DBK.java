package dbRelated;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

import code.LoginBeharrezkoKode;
import exception.Salbuespenak;
import twitter4j.TwitterException;


/**
 * Datu base kudeatzailea
 * @author 
 *
 */
public class DBK{

	Connection conn = null;

	/**
	 * Datu basearekin konexioa ireki
	 */
	private void conOpen() {
		try {
			String url = "jdbc:sqlite:newTwitter.db";
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null,"Datu basera konektatu da","Konexio ezarria", JOptionPane.DEFAULT_OPTION);
		} catch (Exception e) {
			throw new Salbuespenak("Database connection  NOT stablished");
		}
		
		
	}
	
	private void taulakSortu(){
		String sql="CREATE TABLE if not exists 'superuser' (`izena`	varchar(45),`jarraitzaileak`	integer,`jarraituak`	integer,`AccessToken`	TEXT,`AccessTokenSecret`	TEXT,`since`	INTEGER,`max`	INTEGER,PRIMARY KEY(izena))";
		instantzia.execSQL(sql);
		sql="CREATE TABLE if not exists'twit' (`edukia`	varchar(140),`url`	varchar(140),`irudia`	varchar(140),`fav`	NUMERIC,`rt`	NUMERIC,`favKop`	integer,`rtKop`	integer,`id`	integer,`USER_izena`	varchar(45),PRIMARY KEY(id))";
		instantzia.execSQL(sql);
		sql="CREATE TABLE 'user' (`izena`	varchar(45) NOT NULL,`jarraitua`	NUMERIC,`jarraitzailea`	NUMERIC,PRIMARY KEY(izena))";
		instantzia.execSQL(sql);
	}
	



		


	/**
	 * Konexioa itxi egiten du.
	 */
	@SuppressWarnings("unused")
	private void conClose() {

		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				throw new Salbuespenak("Ezin da konexioa itxi");
}

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
		@SuppressWarnings("unused")
		int count = 0;
		Statement s = null;
		ResultSet rs = null;
		
		try {
			s = conn.createStatement();
			if (query.toLowerCase().indexOf("select") == 0) {
				rs = this.query(s, query);
				
			} else {
				count = s.executeUpdate(query);
				//System.out.println(count + " rows affected");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	

	/**
	 * Tokenak gordetzeko metodoa
	 * @param Name erabiltzaile izena
	 * @param AccessToken 
	 * @param AccessTokenSecret
	 * @throws SQLException
	 */
	public void saveToken(String Name,String AccessToken,String AccessTokenSecret) {
		
		Statement st;
		try {
			st = conn.createStatement();
		 
		st.execute("INSERT into superuser (izena, AccessToken,AccessTokenSecret)VALUES('"+Name+"','"+AccessToken+"','"+AccessTokenSecret+"')");
		}catch (SQLException e) {
			throw new Salbuespenak("Ezin dira tokenak gorde");

		}
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



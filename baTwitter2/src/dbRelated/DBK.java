
package dbRelated;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteJDBCLoader;

public class DBK {
	static String path= "C:/Users/Ray/git/baTwitter/baTwitter2/src/Twitter.accdb";

	
	


	private static void dump(ResultSet rs,String exName)
			throws SQLException {
		System.out.println("-------------------------------------------------");
		System.out.println();
		System.out.println(exName+" result:");
		System.out.println();
		while (rs.next()) {
			System.out.print("| ");
			int j=rs.getMetaData().getColumnCount();
			for (int i = 1; i <=j ; ++i) {
				Object o = rs.getObject(i);
				System.out.print(o + " | ");
			}
			System.out.println();
			System.out.println();
		}
	}

	private static Connection getUcanaccessConnection(String pathNewDB) throws SQLException,
			IOException {
		   String url = pathNewDB;
		   return DriverManager.getConnection(url);
	}

	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		
		try {
		    if(args.length==0){
		    	System.err.println("You must specify new Database Access location (full path)");
		    	return;
			}
		    Example ex=new Example(args[0]);
			ex.createTablesExample();
			ex.insertData();
			ex.executeQuery();
			ex.executeQueryWithFunctions();
			ex.executeQueryWithCustomFunction();
			ex.executeLikeExample();
			ex.showExtensions();
			ex.transaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}*/

	private Connection ucaConn;
	private static DBK gureDBK;

	public DBK() {
		
		try {
			this.ucaConn=getUcanaccessConnection(path);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static synchronized DBK getDBK(){
		return gureDBK != null ? gureDBK : (gureDBK= new DBK());

	}
	
	

	public void saveToken(String Name,String AccessToken,String AccessTokenSecret) throws SQLException{
		
		Statement st =ucaConn.createStatement();
		st.execute("INSERT into superuser (izena, AccessToken,AccessTokenSecret)VALUES('"+Name+"','"+AccessToken+"','"+AccessTokenSecret+"')");
		
	}
	public String[] getATokens() throws SQLException{
       
		Statement st =this.ucaConn.createStatement();
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
		Statement st =this.ucaConn.createStatement();
		ResultSet rs=st.executeQuery("SELECT AccessToken,AccessTokenSecret FROM superuser");
		if (!rs.next()){
			return false;
		}else{
			return true;
			}
		}
	public void saveTweetInfo(String text,boolean RT, boolean Fav, int RTCount,int FAVCount,String URL, String Image,long tweetID, String superuser,String USER_izena) throws SQLException{
		Statement st =this.ucaConn.createStatement();
		st.execute("Insert into twit (edukia,url,irudia,fav,rt,favKop,rtKop,id,SUPERUSER_izena,USER_izena) VALUES ('"+text+"','"+URL+"','"+Image+"','"+Fav+"','"+RT+"','"+FAVCount+"','"+RTCount+"','"+tweetID+"','"+superuser+"','"+USER_izena+"')");
		
	}
	public ResultSet loadAllTweetInfo() throws SQLException{
		Statement st =this.ucaConn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * FROM tweet");
		return rs;
	}
	public void ClearDB() throws SQLException{
		Statement st =this.ucaConn.createStatement();
		st.executeQuery("DELETE * FROM twit");
		st.executeQuery("DELETE * FROM superuser");
		st.executeQuery("DELETE * FROM user");

	}
	
	public String FindFile(){
		
		return path;
	}

}



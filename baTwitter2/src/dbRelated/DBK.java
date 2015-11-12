package dbRelated;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.sql.Statement;


//import net.ucanaccess.jdbc.UcanaccessConnection;

public class DBK {
	
	//public UcanaccessConnection UDAC;
	

		
		
		public static String concat(String s1,String s2){
				return s1+" >>>>"+s2;
		}



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

		private static Connection getUcanaccessConnection() throws SQLException,
				IOException {
			   String url = "/baTwitter/src/Twitter.accdb";
			   return DriverManager.getConnection(url);
		}
		private Connection ucaConn;

		public DBK() {
			try {
				this.ucaConn=getUcanaccessConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private String[] getAccess() throws SQLException {
			Statement st = null;
			try {
				st =this.ucaConn.createStatement();
				ResultSet rs=st.executeQuery("SELECT * from superuser");
				dump(rs,"executeQuery");
			} finally {
				if (st != null)
					st.close();
			}
			String[] token= new String[2];
			while()
			return token;
			
		}
	
	

}

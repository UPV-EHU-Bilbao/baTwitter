package dbRelated;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class Exportatzailea {
	
	private static Exportatzailea export = new Exportatzailea();
	
	
	public Exportatzailea(){
		
	}
	
	public static synchronized Exportatzailea getExp(){
	if (export==null) {export= new Exportatzailea();} return export;
	}
	
	
	@SuppressWarnings("unused")
	public void exportatuTweet(Connection conn) throws SQLException, IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet TwitterSheet = wb.createSheet("Twitter");
		Row headerRow = TwitterSheet.createRow(0);
		Cell idHeaderCell = headerRow.createCell(0);
		Cell edukiaHeaderCell = headerRow.createCell(1);
		Cell idazleaHeaderCell = headerRow.createCell(2);
		Cell rtHeaderCell = headerRow.createCell(3);
		Cell favHeaderCell = headerRow.createCell(4);
		Cell RtKopHeaderCell = headerRow.createCell(5);
		Cell FavKopHeaderCell = headerRow.createCell(6);
		Cell URLHeaderCell = headerRow.createCell(7);

		String sql = "Select * from user";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();    

		int row = 1;
		while(resultSet.next()) {
		    long id = resultSet.getLong("id");
		    String edukia = resultSet.getString("edukia");
		    String idazlea = resultSet.getString("idazlea");
		    boolean rt = resultSet.getBoolean("rt");
		    boolean fav = resultSet.getBoolean("fav");
		    int rtKop = resultSet.getInt("rtKop");
		    int favKop = resultSet.getInt("favKop");
		    String url = resultSet.getString("url");

		    Row dataRow = TwitterSheet.createRow(row);
		    
		    Cell dataIdCell = dataRow.createCell(0);
		    dataIdCell.setCellValue(id);

		    Cell dataEdukiaCell = dataRow.createCell(1);
		    dataEdukiaCell.setCellValue(edukia);
		    
		    Cell dataIdazleaCell = dataRow.createCell(1);
		    dataIdazleaCell.setCellValue(idazlea);

		    Cell dataRTCell = dataRow.createCell(1);
		    dataRTCell.setCellValue(rt);

		    Cell dataFAVCell = dataRow.createCell(1);
		    dataFAVCell.setCellValue(fav);

		    Cell dataRtKopCell = dataRow.createCell(1);
		    dataRtKopCell.setCellValue(rtKop);

		    Cell dataFavKopCell = dataRow.createCell(1);
		    dataFavKopCell.setCellValue(favKop);

		    Cell dataURLCell = dataRow.createCell(1);
		    dataURLCell.setCellValue(url);


		    row = row + 1;
		}

		String outputDirPath = "D:/PersonList.xls";
		FileOutputStream fileOut = new FileOutputStream(outputDirPath);
		wb.write(fileOut);
		fileOut.close();
	}
	
	@SuppressWarnings("unused")
	public void exportatuErabiltzailearenDatuak(Connection conn) throws SQLException, IOException{
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet TwitterSheet = wb.createSheet("Twitter");
		Row headerRow = TwitterSheet.createRow(0);
		Cell IzenaHeaderCell = headerRow.createCell(0);
		Cell JarraitzaileakHeaderCell = headerRow.createCell(1);
		Cell JarraituakheaderCell=headerRow.createCell(2);
		
		String sql = "Select * from user where user.jarraizailea=1;";
		PreparedStatement ps =  conn.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();    
		
		int row = 1;
		while(resultSet.next()) {
		    String jarraitzailea = resultSet.getString("jarraitzailea");

		    Row dataRow = TwitterSheet.createRow(row);
		    
		    Cell dataNameCell = dataRow.createCell(0);
		    dataNameCell.setCellValue(jarraitzailea);

		    row = row + 1;
		}
		sql = "Select * from user where user.jarraituak=1;";
		ps =  conn.prepareStatement(sql);
		resultSet = ps.executeQuery();    
		
		row = 1;
		while(resultSet.next()) {
		    String jarraitua = resultSet.getString("izena");

		    Row dataRow = TwitterSheet.createRow(row);
		    
		    Cell dataNameCell = dataRow.createCell(0);
		    dataNameCell.setCellValue(jarraitua);

		    row = row + 1;
		}

		String outputDirPath = System.getProperty("user.id");
		FileOutputStream fileOut = new FileOutputStream(outputDirPath);
		wb.write(fileOut);
		fileOut.close();
	}
	

}

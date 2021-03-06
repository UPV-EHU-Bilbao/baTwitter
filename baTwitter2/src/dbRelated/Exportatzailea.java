package dbRelated;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import exception.Salbuespenak;

public class Exportatzailea {
		
	private Workbook wb;
	
	public Exportatzailea(){
		
	}
	
	
	public void exportatuTweet(){
		
		Sheet TwitterSheet = wb.createSheet("Twitter");
		Row headerRow = TwitterSheet.createRow(0);
		Cell idHeaderCell = headerRow.createCell(0);
		idHeaderCell.setCellValue("TweetID");
		
		Cell edukiaHeaderCell = headerRow.createCell(1);
		edukiaHeaderCell.setCellValue("Edukia");
		
		Cell idazleaHeaderCell = headerRow.createCell(2);
		idazleaHeaderCell.setCellValue("Idazlea");

		Cell rtHeaderCell = headerRow.createCell(3);
		rtHeaderCell.setCellValue("Retweeteatuta?");

		Cell favHeaderCell = headerRow.createCell(4);
		favHeaderCell.setCellValue("Faboritoak?");

		Cell RtKopHeaderCell = headerRow.createCell(5);
		RtKopHeaderCell.setCellValue("RT kopurua");

		Cell FavKopHeaderCell = headerRow.createCell(6);
		FavKopHeaderCell.setCellValue("Fav Kopurua");

		Cell URLHeaderCell = headerRow.createCell(7);
		URLHeaderCell.setCellValue("Tweet URL");

		String sql = "Select * from twit";
		try{
		PreparedStatement ps = DBK.getInstantzia().conn.prepareStatement(sql);
		ResultSet resultSet = ps.executeQuery();    

		int row = 1;
		while(resultSet.next()) {
		    long id = resultSet.getLong("id");
		    String edukia = resultSet.getString("edukia");
		    String idazlea = resultSet.getString("USER_izena");
		    boolean rt = resultSet.getBoolean("rt");
		    boolean fav = resultSet.getBoolean("fav");
		    int rtKop = resultSet.getInt("rtKop");
		    int favKop = resultSet.getInt("favKop");
		    String url = resultSet.getString("url");
		    if (url.equals(null)){
		    	url=" ";
		    }

		    Row dataRow = TwitterSheet.createRow(row);
		    
		    Cell dataIdCell = dataRow.createCell(0);
		    dataIdCell.setCellValue(id);

		    Cell dataEdukiaCell = dataRow.createCell(1);
		    dataEdukiaCell.setCellValue(edukia);
		    
		    Cell dataIdazleaCell = dataRow.createCell(2);
		    dataIdazleaCell.setCellValue(idazlea);

		    Cell dataRTCell = dataRow.createCell(3);
		    dataRTCell.setCellValue(rt);

		    Cell dataFAVCell = dataRow.createCell(4);
		    dataFAVCell.setCellValue(fav);

		    Cell dataRtKopCell = dataRow.createCell(5);
		    dataRtKopCell.setCellValue(rtKop);

		    Cell dataFavKopCell = dataRow.createCell(6);
		    dataFavKopCell.setCellValue(favKop);

		    if(url.equals("null")){
		    Cell dataURLCell = dataRow.createCell(7);
		    dataURLCell.setCellValue(url);}


		    row = row + 1;
		}
		}catch(Exception e){
			throw new Salbuespenak("Ezin da datuak esportatu");
		}
		
	}
	
	


	public void exportatuErabiltzailearenDatuak(){
		Sheet UserSheet = wb.createSheet("User");
		Row headerRow = UserSheet.createRow(0);
		Cell IzenaHeaderCell = headerRow.createCell(0);
		IzenaHeaderCell.setCellValue("IZENA");
		Cell JarraitzaileakHeaderCell = headerRow.createCell(1);
		JarraitzaileakHeaderCell.setCellValue("JARRAITZAILEA");
		Cell JarraituakheaderCell=headerRow.createCell(2);
		JarraituakheaderCell.setCellValue("JARRAITUA");

		
		String sql = "Select * from user where jarraitzailea=1;";
		try{
		PreparedStatement ps = DBK.getInstantzia().conn.prepareStatement(sql);
		
		ResultSet resultSet = ps.executeQuery();    
		
		int row = 1;
		while(resultSet.next()) {
		    String jarraitzailea = resultSet.getString("jarraitzailea");

		    Row dataRow = UserSheet.createRow(row);
		    
		    Cell dataNameCell = dataRow.createCell(0);
		    dataNameCell.setCellValue(jarraitzailea);

		    row = row + 1;
		}
		sql = "Select * from user where jarraitua=1;";
		ps = DBK.getInstantzia().conn.prepareStatement(sql);
		resultSet = ps.executeQuery();    
		
		
		while(resultSet.next()) {
		    String jarraitua = resultSet.getString("izena");

		    Row dataRow = UserSheet.createRow(row);
		    
		    Cell dataNameCell = dataRow.createCell(0);
		    dataNameCell.setCellValue(jarraitua);

		    row = row + 1;
		}}catch(Exception e){
			throw new Salbuespenak("Ezin da datuak esportatu");
		}

		
	}
	
	public boolean save(String path) {
		
		wb = new HSSFWorkbook();
		try {
			this.exportatuErabiltzailearenDatuak();
			this.exportatuTweet();
			
			FileOutputStream fileOut = new FileOutputStream(path+".xls");
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
			wb.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
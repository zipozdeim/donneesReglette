package data.reglette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class DbConn {
	// URL de connexion
	String jdbcUrl="jdbc:sqlite:DonneesReglette.sqlite";
	private static Connection connect;

	public  DbConn() {
		try {
			connect = DriverManager.getConnection(jdbcUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// UNicité de la connexion
	public static Connection getInstance() {
		if (connect == null) {
			new DbConn();
		}
		return connect;
	}
	

	
	public ResultSet getNotesQ(String question, String site, String dateVisite) throws SQLException {
		String query="select "+question + " as 'Reponse',"
				+ "count(*) as Nb from Notes "
				+ "where Reponse is not NULL and site='"+site+"' and dateVisite='"+dateVisite+"' "
						+ "group By "+question + " order by "+question+";";
		System.out.println(query);
		Statement st=connect.createStatement();
		ResultSet res=st.executeQuery(query);
		return res;
	}
	
	public ResultSet getSumsQ(String question, String site, String dateVisite) throws SQLException {
		String query="select "+question +",count("+question+"), site,dateVisite from Notes "
				+ "group by "+question+",site,dateVisite order by dateVisite,"+question+";";
		System.out.println(query);
		Statement st=connect.createStatement();
		ResultSet res=st.executeQuery(query);
		return res;
	}
	
	public ResultSet getMoyQ(String question, String site, String dateVisite) throws SQLException {
		String query="Select round(avg("+ question +"),2) as 'moy' from Notes"
				+ " where site='"+site+"' and dateVisite='"+dateVisite+"';";
		//System.out.println(query);
		Statement st=connect.createStatement();
		ResultSet res=st.executeQuery(query);
		return res;
	}
	
	public void resetTable() throws SQLException {
		String query="delete from Notes";
		Statement st=connect.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public void InsertNotes(String contenuFichier) throws SQLException {
		String query="insert into Notes ('site', 'datevisite', 'Q1','Q2','Q3','Q4','Q5','Q6','Q7') VALUES(";
		query+=contenuFichier+")";
		System.out.println("requete insertion : "+query);
		Statement st=connect.createStatement();
		st.executeUpdate(query);
		st.close();
	}
	
	public void CreateExcelDoc() throws SQLException, IOException{
		String query="select * from Notes;";
		Statement st=connect.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		XSSFWorkbook workbook= new XSSFWorkbook();
		
		for (int q=1;q<=7;q++) {
			//onglet par site
			
		}
		
		
		
		
		XSSFSheet ongletDonnees = workbook.createSheet("Donnees brutes");
		XSSFRow row=ongletDonnees.createRow(0);
		row.createCell(0).setCellValue("Site");
		row.createCell(1).setCellValue("dateVisite");
		row.createCell(2).setCellValue("condition");
		row.createCell(3).setCellValue("motivation");
		row.createCell(4).setCellValue("stress");
		row.createCell(5).setCellValue("fatigue");
		row.createCell(6).setCellValue("pression");
		row.createCell(7).setCellValue("methode");
		row.createCell(8).setCellValue("equilibre");
		
		int r=1;
		while(rs.next()) {
			String site=rs.getString("site");
			String dateVisite=rs.getString("dateVisite");
			int Q1=rs.getInt("Q1");
			int Q2=rs.getInt("Q2");
			int Q3=rs.getInt("Q3");
			int Q4=rs.getInt("Q4");
			int Q5=rs.getInt("Q5");
			int Q6=rs.getInt("Q6");
			int Q7=rs.getInt("Q7");
		
			XSSFRow currentRow=ongletDonnees.createRow(r);
			currentRow.createCell(0).setCellValue(site);
			currentRow.createCell(1).setCellValue(dateVisite);
			currentRow.createCell(2).setCellValue(Q1);
			currentRow.createCell(3).setCellValue(Q2);
			currentRow.createCell(4).setCellValue(Q3);
			currentRow.createCell(5).setCellValue(Q4);
			currentRow.createCell(6).setCellValue(Q5);
			currentRow.createCell(7).setCellValue(Q6);
			currentRow.createCell(8).setCellValue(Q7);
			r++;
		}
		
		File dirExport  = new File("export\\");
		boolean res = dirExport.mkdir();
		FileOutputStream excelFile= new FileOutputStream("export\\exportDatas.xls");
		workbook.write(excelFile);
		workbook.close();
		excelFile.close();
	}
	
	public void closeConection() {
		this.closeConection();
	}

}
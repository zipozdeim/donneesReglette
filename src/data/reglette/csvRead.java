package data.reglette;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class csvRead {
	public static DbConn myConn = new DbConn();

	public static void listFiles() {

		File dirData  = new File("data\\");
		boolean res = dirData.mkdir();
		File[] listeDatas = dirData.listFiles();
		for(File site : listeDatas){
			if (listeDatas.length>0) {
				//On parcourt les sites
				if(site.isDirectory()) {
					//System.out.println(site.getName());
					String siteName=site.getName(); 

					File dirSite  = new File("data\\"+siteName+"\\");
					File[] listeSites = dirSite.listFiles();
					//System.out.println("Parcours du dossier");
					for(File annee : listeSites){
						//on parcourt les annees
						if(annee.isDirectory())
						{
							//System.out.println(annee.getName());
							String dateVisite=annee.getName(); 
							File dirAnnee  = new File("data\\"+siteName+"\\"+dateVisite+"\\");
							File[] listeAnnees = dirAnnee.listFiles();

							for(File csv : listeAnnees){
								//on parcourt les fichiers
								if(csv.isFile())
								{
									//System.out.println(csv.getName());
									//System.out.println(csv.getPath());
									String csvName=csv.getName(); 
									File csvFile= new File(csv.getPath());
									try {
										loadCsv(csvFile,siteName,dateVisite);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} 
							}						
						} 
					}
				} 
			} else {
				System.out.println("pas de donnees a traiter");
			}
		}
	}			
	public static void loadCsv(File csvFile,String site, String dateVisite) throws IOException {
		FileReader fr = new FileReader(csvFile);  
		// Cr�er l'objet BufferedReader        
		BufferedReader br = new BufferedReader(fr);  
		StringBuffer sb = new StringBuffer();    
		String line;
		while((line = br.readLine()) != null)
		{
			try {
				//System.out.println("LIGNE AVANT : "+line);
				line=line.replace(';', ',');
				//System.out.println("LIGNE APRES : "+line);
				String[] listeValeurs = line.split(",",-1);
				String newLine="'"+site+"','"+dateVisite+"'";
				int i=1;
				for (String valeur : listeValeurs) {

					//System.out.println("valeur "+i+"="+valeur);
					if (i<=7) {
						if (valeur.equals("")) {
							newLine+=",NULL";   
						} else {
							newLine+=","+valeur;
						}
					}	
					i++;
				}
				//System.out.println("Newline : "+newLine);

				myConn.InsertNotes(newLine);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ajoute la ligne au buffer
			sb.append(line);      
			sb.append("\n");     
		}
		fr.close();    
		//  System.out.println("Contenu du fichier: ");
		//System.out.println(sb.toString()); 
		// System.out.println("-------------");

	}

	public static String removeLastChar(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		return str.substring(0, str.length() - 1);
	}
} 

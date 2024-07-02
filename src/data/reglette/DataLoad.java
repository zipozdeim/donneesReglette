package data.reglette;

import java.io.File;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;

import swing.reglette.RegletteUI;


public class DataLoad extends JFrame {

/**
	 * 
	 */
	private static final long serialVersionUID = 7927175486291879256L;


	
	public static DbConn myConn = new DbConn();
	public static csvRead myFiles= new csvRead();
	public static RegletteUI MainWindow=new RegletteUI();
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		//java.awt.EventQueue.invokeLater(new Runnable() {
			//public void run() {
			//	new jtree_selected_node_index().setVisible(true);
			//}
		//});
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
	
		try {
			
			//random generator : genere des donn�es aleatoirement pour tester � copier dans un fichier
			/*
			Random rn = new Random();
			for (int  i=0;i<100;i++) {
				String randomLine="";
				for(int nbChars =0; nbChars < 7; nbChars++)
				{
				    int answer = rn.nextInt(11) + 1;
				    randomLine+=answer+";";
				}
				randomLine=randomLine.replace("11", "");
				System.out.println(randomLine);
			}
			*/
			
			
			//on r�inintialise la table
			myConn.resetTable();
			
			//CHargement en base des donnees issues des csv 
			myFiles.listFiles();
			
			//Affihcage resultat
			//myConn.getNotes();
			
			//Afficher l'interface
			//RegletteUI MainWindow=new RegletteUI();
			MainWindow.setVisible(true);
			
			//Modification de l'arbo
		    File dirData  = new File("data\\");
		    File[] listeDatas = dirData.listFiles();
		    for(File site : listeDatas){
		    	//On parcourt les sites
		    	if(site.isDirectory())
			      {
		    		//System.out.println(site.getName());
		    		String siteName=site.getName();
		    		DefaultMutableTreeNode node =  new DefaultMutableTreeNode(siteName);
		    		MainWindow.visites.add(node);
		    		File dirSite  = new File("data\\"+siteName+"\\");
		    	    File[] listeSites = dirSite.listFiles();
		    	    for(File annee : listeSites){
		    	    	//on parcourt les annees
		    	    	if(annee.isDirectory())
		    		      {
		    	    		String anneeVisite=annee.getName();
		    	    		DefaultMutableTreeNode node2 =  new DefaultMutableTreeNode(anneeVisite);
		    	    		node.add(node2);


		    		      } 
		    	    }
			      } 
		    }
			

			
			

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

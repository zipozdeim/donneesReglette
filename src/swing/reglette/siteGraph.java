package swing.reglette;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import data.reglette.questions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import data.reglette.DbConn;

public class siteGraph extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3700015975168864096L;
	public static DbConn myConn = new DbConn();

	/**
	 * Create the panel.
	 */
		public siteGraph(String question, String site, String dateVisite) {
		
	this.add( PieDataset(question,site,dateVisite));
					
	}
	
	
	private DefaultPieDataset Q1Dataset(String question, String site, String dateVisite) {
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		//pieDataset.setValue("1", new Integer(75));
		try {
			ResultSet res=myConn.getNotesQ(question,site,dateVisite);
			while (res.next() ) {
				pieDataset.setValue(res.getString("Reponse"), res.getInt("Nb"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pieDataset;
	}
	
	private JPanel PieDataset(String question, String site, String dateVisite){
		JPanel PiePanel=new JPanel();
		String item= questions.valueOf(question).toString();
		//PiePanel.setPreferredSize(new Dimension(200,200));
		JFreeChart graphe = ChartFactory.createPieChart(site +"("+dateVisite+")"+ " : "+item, Q1Dataset(question,site,dateVisite),true,true, false);
		ChartPanel chartPanelQ1 = new ChartPanel(graphe);
		chartPanelQ1.setPreferredSize(new Dimension(500,500));
		PiePanel.add( chartPanelQ1 );
		return (PiePanel);
	}
	
}

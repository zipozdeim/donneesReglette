package swing.reglette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Paint;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import net.miginfocom.swing.MigLayout;

import data.reglette.DbConn;

public class chartPieQ extends JPanel {
	
	public static DbConn myConn = new DbConn();

	/**
	 * Create the panel.
	 */
	
	
	public chartPieQ(String question, String site, String dateVisite) {
		
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

		//PiePanel.setPreferredSize(new Dimension(200,200));
		JFreeChart graphe = ChartFactory.createPieChart("Détail de la question "+question, Q1Dataset(question,site,dateVisite),true,true, false);
		ChartPanel chartPanelQ1 = new ChartPanel(graphe);
		chartPanelQ1.setPreferredSize(new Dimension(500,500));
		PiePanel.add( chartPanelQ1 );
		return (PiePanel);
	}
	
	
}

package swing.reglette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.GroupedStackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.KeyToGroupMap;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import data.reglette.DbConn;
import net.miginfocom.swing.MigLayout;

public class CATSBarsPanel extends JPanel {
	
	public static DbConn myConn = new DbConn();

	/**
	 * Create the panel.
	 */
	
	
	public CATSBarsPanel(String question) {
		
		//System.out.println("entree dans constructeur bars");
		this.add(barDataset(question));
	}
	
	private JPanel statsPanel(String site, String DateVisite) {
		JPanel statsPanel = new JPanel(new MigLayout());

		try {
			// à factoriser avec variables dynamiques la nouvelle séparation en onglets le permet facilement
			
			/*for (int i =1;i<=7;i++) {
				ResultSet resmoyQ;
				resmoyQ = myConn.getMoyQ("Q"+i,site,DateVisite);
				JLabel titreMoyQ = new JLabel("CONDITIONS DE TRAVAIL :");
				String valeurMoyQ= resmoyQ.getString("moy");
				JLabel moyQ = new JLabel(valeurMoyQ);
				titreMoyQ.setForeground(Color.BLACK);
				titreMoyQ.setHorizontalAlignment(SwingConstants.CENTER);
				titreMoyQ.setFont(new Font("Times New Roman", Font.ITALIC, 16));
				moyQ.setForeground(Color.BLACK);
				moyQ.setHorizontalAlignment(SwingConstants.CENTER);
				moyQ.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			}*/
			
			statsPanel.add(new JLabel("         "),"wrap");
			
			ResultSet resmoyQ1;
			resmoyQ1 = myConn.getMoyQ("Q1",site,DateVisite);
			JLabel titreMoyQ1 = new JLabel("CONDITIONS DE TRAVAIL :");
			Float valeurMoyQ1= resmoyQ1.getFloat("moy");
			JLabel moyQ1 = new JLabel(""+valeurMoyQ1);
			titreMoyQ1.setForeground(Color.BLACK);
			titreMoyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ1.setForeground(Color.BLACK);
			moyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ1);
			statsPanel.add(moyQ1);
			statsPanel.add(new JLabel("         "));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statsPanel;
		
	}
	
	
	
	private JPanel barDataset(String question){
	    ChartPanel cPanel = new ChartPanel(createChart(barDatasetVisite(question))); 
	    cPanel.setPreferredSize(new Dimension(700,600));
	    cPanel.setMinimumSize(new Dimension(500,400));
	     return cPanel;
	}
	
	    private JFreeChart createChart(CategoryDataset dataset) {

	        JFreeChart barChart = ChartFactory.createBarChart(
	                "Comparaison tous sites (dernière visite)",
	                "",
	                "Moyenne",
	                dataset,
	                PlotOrientation.VERTICAL,
	                true, true, false);

	        return barChart;
	    }
	
	private DefaultCategoryDataset barDatasetVisite(String question) {
		//Creation des datsets par question/site/date pour les stacked bars
		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
			try {
				ResultSet res=myConn.getAllAvgs(question);
				while (res.next() ) {
					barDataset.setValue( res.getFloat("moy"),res.getString("datevisite"),res.getString("site"));
				}
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return barDataset;
	}
	


}

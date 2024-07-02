package swing.reglette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

import data.reglette.DbConn;
import data.reglette.questions;
import net.miginfocom.swing.MigLayout;

public class globalCATSPanel extends JPanel {
	
	public static DbConn myConn = new DbConn();

	/**
	 * Create the panel.
	 */
	
	
	public globalCATSPanel() {
		
		//System.out.println("entree dans constructeur bars");
		JPanel globalCATSPanel = new JPanel(new MigLayout()); 
		
		JLabel titreMoyLbl = new JLabel("MOYENNES GLOBALES (TOUTES VISITES) ");
		titreMoyLbl.setForeground(Color.BLUE);
		titreMoyLbl.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		globalCATSPanel.add(titreMoyLbl,"wrap");
		globalCATSPanel.add(statsPanel());
		//this.add(barDataset(site,DateVisite));
		this.add(globalCATSPanel);
	}
	
	private JPanel statsPanel() {
		JPanel statsPanel = new JPanel(new MigLayout());

		try {
			
			ResultSet resmoyQ1;
			resmoyQ1 = myConn.getCATSAvg("Q1");
			JLabel titreMoyQ1 = new JLabel("CONDITIONS DE TRAVAIL :");
			String valeurMoyQ1= resmoyQ1.getString("moy");
			JLabel moyQ1 = new JLabel(valeurMoyQ1);
			titreMoyQ1.setForeground(Color.BLACK);
			titreMoyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ1.setForeground(Color.BLACK);
			moyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ1);
			statsPanel.add(moyQ1);
			statsPanel.add(new JLabel("         "));
			
			ResultSet resmoyQ2;
			resmoyQ2 = myConn.getCATSAvg("Q2");
			JLabel titreMoyQ2 = new JLabel("MOTIVATION AU TRAVAIL :");
			String valeurMoyQ2= resmoyQ2.getString("moy");
			JLabel moyQ2 = new JLabel(valeurMoyQ2);
			titreMoyQ2.setForeground(Color.BLACK);
			titreMoyQ2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ2.setForeground(Color.BLACK);
			moyQ2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ2);
			statsPanel.add(moyQ2);
			statsPanel.add(new JLabel("         "));
			statsPanel.add(moyQ2,"wrap");
			
		
			ResultSet resmoyQ3;
			resmoyQ3 = myConn.getCATSAvg("Q3");
			JLabel titreMoyQ3 = new JLabel("STRESS LIE AU TRAVAIL :");
			String valeurMoyQ3= resmoyQ3.getString("moy");
			JLabel moyQ3 = new JLabel(valeurMoyQ3);
			titreMoyQ3.setForeground(Color.BLACK);
			titreMoyQ3.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ3.setForeground(Color.BLACK);
			moyQ3.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ3);
			statsPanel.add(moyQ3);
			statsPanel.add(new JLabel("         "));
			
			ResultSet resmoyQ4;
			resmoyQ4 = myConn.getCATSAvg("Q4");
			JLabel titreMoyQ4 = new JLabel("FATIGUE LIEE AU TRAVAIL :");
			String valeurMoyQ4= resmoyQ4.getString("moy");
			JLabel moyQ4 = new JLabel(valeurMoyQ4);
			titreMoyQ4.setForeground(Color.BLACK);
			titreMoyQ4.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ4.setForeground(Color.BLACK);
			moyQ4.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ4);
			statsPanel.add(moyQ4);
			statsPanel.add(new JLabel("         "));
			statsPanel.add(moyQ4,"wrap");
			
			ResultSet resmoyQ5;
			resmoyQ5 = myConn.getCATSAvg("Q5");
			JLabel titreMoyQ5 = new JLabel("NIVEAU DE PRESSION :");
			String valeurMoyQ5= resmoyQ5.getString("moy");
			JLabel moyQ5 = new JLabel(valeurMoyQ5);
			titreMoyQ5.setForeground(Color.BLACK);
			titreMoyQ5.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ5.setForeground(Color.BLACK);
			moyQ5.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ5);
			statsPanel.add(moyQ5);
			statsPanel.add(new JLabel("         "));
			
			ResultSet resmoyQ6;
			resmoyQ6 = myConn.getCATSAvg("Q6");
			JLabel titreMoyQ6 = new JLabel("METHODES DE TRAVAIL :");
			String valeurMoyQ6= resmoyQ6.getString("moy");
			JLabel moyQ6 = new JLabel(valeurMoyQ6);
			titreMoyQ6.setForeground(Color.BLACK);
			titreMoyQ6.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ6.setForeground(Color.BLACK);
			moyQ6.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ6);
			statsPanel.add(moyQ6);
			statsPanel.add(new JLabel("         "));
			statsPanel.add(moyQ6,"wrap");
			
			
			
			ResultSet resmoyQ7;
			resmoyQ7 = myConn.getCATSAvg("Q7");
			JLabel titreMoyQ7 = new JLabel("EQUILIBRE VIE PRO VIE PERSO");
			String valeurMoyQ7= resmoyQ7.getString("moy");
			JLabel moyQ7 = new JLabel(valeurMoyQ7);
			titreMoyQ7.setForeground(Color.BLACK);
			titreMoyQ7.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ7.setForeground(Color.BLACK);
			moyQ7.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			statsPanel.add(titreMoyQ7);
			statsPanel.add(moyQ7);
			statsPanel.add(new JLabel("         "));
			statsPanel.add(moyQ7,"wrap");
			
			



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statsPanel;
		
	}
	
	
	
	private JPanel barDataset(String site, String dateVisite){

		//DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	
	    //dataset.addValue(2, "1", "Motivation");

	    JFreeChart barChart = ChartFactory.createStackedBarChart3D(
	    		"Repartition des Notes","question", "Note", barDatasetVisite(site,dateVisite),PlotOrientation.VERTICAL, true, true, false ); 
	    ChartPanel cPanel = new ChartPanel(barChart); 
	    cPanel.setPreferredSize(new Dimension(500,600));
	    cPanel.setMinimumSize(new Dimension(500,600));


	    StackedBarRenderer3D renderer = new StackedBarRenderer3D();
	    renderer.setBarPainter( new StandardBarPainter() );
	    	renderer.setSeriesPaint(0, ChartColor.DARK_RED);
	        renderer.setSeriesPaint(1, ChartColor.RED);
	        renderer.setSeriesPaint(2, Color.ORANGE);
	        renderer.setSeriesPaint(3, Color.YELLOW);
	        renderer.setSeriesPaint(4, Color.GRAY);
	        renderer.setSeriesPaint(5, ChartColor.VERY_DARK_BLUE);
	        renderer.setSeriesPaint(6, Color.blue);
	        renderer.setSeriesPaint(7, Color.CYAN);
	        renderer.setSeriesPaint(8, ChartColor.DARK_GREEN);
	        renderer.setSeriesPaint(9, Color.GREEN);
	        renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	        CategoryAxis xAxis = plot.getDomainAxis();
	        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
	        plot.setBackgroundPaint(Color.LIGHT_GRAY);  
	        renderer.setMaximumBarWidth(1);
	        
	       // StackedBarRenderer stackedbarrenderer = (StackedBarRenderer) plot.getRenderer();
	       // renderer.setRenderAsPercentages(true);
	        renderer.setBaseItemLabelsVisible(true);
	        renderer.setItemLabelsVisible(true); 
	        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  

	        //plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
	        plot.setRenderer(renderer);
	        return cPanel;
	}
	
	private DefaultCategoryDataset barDatasetVisite(String site, String DateVisite) {
		//Creation des datsets par question/site/date pour les stacked bars
		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
			try {
			for (int i=1;i<=7;i++) {
				ResultSet res=myConn.getNotesQ("Q"+i,site, DateVisite);
				while (res.next() ) {
					String item= questions.valueOf("Q"+i).toString();
					barDataset.setValue( res.getInt("Nb"),res.getString("Reponse"),item);
				}
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return barDataset;
	}

}

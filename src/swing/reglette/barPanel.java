package swing.reglette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
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
import net.miginfocom.swing.MigLayout;

import data.reglette.DbConn;

public class barPanel extends JPanel {
	
	public static DbConn myConn = new DbConn();

	/**
	 * Create the panel.
	 */
	
	
	public barPanel(String site, String DateVisite) {
		
		System.out.println("entree dans constructeur bars");
		JLabel titreMoyLbl = new JLabel("MOYENNE DE CHAQUE ITEM ");
		titreMoyLbl.setForeground(Color.BLUE);
		titreMoyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titreMoyLbl.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		this.add(titreMoyLbl);
		this.add(statsPanel(site,DateVisite));
		this.add(barDataset(site,DateVisite));
		
		
			
	}
	
	private JPanel statsPanel(String site, String DateVisite) {
		JPanel statsPanel = new JPanel(new MigLayout());

		try {
			
			ResultSet resmoyQ1;
			resmoyQ1 = myConn.getMoyQ("Q1",site,DateVisite);
			JLabel titreMoyQ1 = new JLabel("CONDITIONS DE TRAVAIL :");
			String valeurMoyQ1= resmoyQ1.getString("moy");
			JLabel moyQ1 = new JLabel(valeurMoyQ1);
			titreMoyQ1.setForeground(Color.BLACK);
			titreMoyQ1.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ1.setForeground(Color.BLACK);
			moyQ1.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		
			ResultSet resmoyQ2;
			resmoyQ2 = myConn.getMoyQ("Q2",site,DateVisite);
			JLabel titreMoyQ2 = new JLabel("MOTIVATION :");
			String valeurMoyQ2= resmoyQ2.getString("moy");
			JLabel moyQ2 = new JLabel(valeurMoyQ2);
			titreMoyQ2.setForeground(Color.BLACK);
			titreMoyQ2.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ2.setForeground(Color.BLACK);
			moyQ2.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		
			ResultSet resmoyQ3;
			resmoyQ3 = myConn.getMoyQ("Q3",site,DateVisite);
			JLabel titreMoyQ3 = new JLabel("STRESS LIE AU TRAVAIL :");
			String valeurMoyQ3= resmoyQ3.getString("moy");
			JLabel moyQ3 = new JLabel(valeurMoyQ3);
			titreMoyQ3.setForeground(Color.BLACK);
			titreMoyQ3.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ3.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ3.setForeground(Color.BLACK);
			moyQ3.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ3.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			
			ResultSet resmoyQ4;
			resmoyQ4 = myConn.getMoyQ("Q4",site,DateVisite);
			JLabel titreMoyQ4 = new JLabel("FATIGUE LIEE AU TRAVAIL  :");
			String valeurMoyQ4= resmoyQ4.getString("moy");
			JLabel moyQ4 = new JLabel(valeurMoyQ4);
			titreMoyQ4.setForeground(Color.BLACK);
			titreMoyQ4.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ4.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ4.setForeground(Color.BLACK);
			moyQ4.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ4.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			
			ResultSet resmoyQ5;
			resmoyQ5 = myConn.getMoyQ("Q5",site,DateVisite);
			JLabel titreMoyQ5 = new JLabel("PRESSION :");
			String valeurMoyQ5= resmoyQ5.getString("moy");
			JLabel moyQ5 = new JLabel(valeurMoyQ5);
			titreMoyQ5.setForeground(Color.BLACK);
			titreMoyQ5.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ5.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ5.setForeground(Color.BLACK);
			moyQ5.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ5.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			
			ResultSet resmoyQ6;
			resmoyQ6 = myConn.getMoyQ("Q6",site,DateVisite);
			JLabel titreMoyQ6 = new JLabel("METHODES UTILISEES :");
			String valeurMoyQ6= resmoyQ6.getString("moy");
			JLabel moyQ6 = new JLabel(valeurMoyQ6);
			titreMoyQ6.setForeground(Color.BLACK);
			titreMoyQ6.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ6.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ6.setForeground(Color.BLACK);
			moyQ6.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ6.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			
			ResultSet resmoyQ7;
			resmoyQ7 = myConn.getMoyQ("Q7",site,DateVisite);
			JLabel titreMoyQ7 = new JLabel("EQUILIBRE DE VIE:");
			String valeurMoyQ7= resmoyQ7.getString("moy");
			JLabel moyQ7 = new JLabel(valeurMoyQ7);
			titreMoyQ7.setForeground(Color.BLACK);
			titreMoyQ7.setHorizontalAlignment(SwingConstants.CENTER);
			titreMoyQ7.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			moyQ7.setForeground(Color.BLACK);
			moyQ7.setHorizontalAlignment(SwingConstants.CENTER);
			moyQ7.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			
		statsPanel.add(titreMoyQ1);
		statsPanel.add(moyQ1);
		statsPanel.add(new JLabel("         "));
		statsPanel.add(titreMoyQ2);
		statsPanel.add(moyQ2,"wrap");
		statsPanel.add(titreMoyQ3);
		statsPanel.add(moyQ3);
		statsPanel.add(new JLabel("         "));
		statsPanel.add(titreMoyQ4);
		statsPanel.add(moyQ4,"wrap");
		statsPanel.add(titreMoyQ5);
		statsPanel.add(moyQ5);
		statsPanel.add(new JLabel("         "));
		statsPanel.add(titreMoyQ6);
		statsPanel.add(moyQ6,"wrap");
		statsPanel.add(titreMoyQ7);
		statsPanel.add(moyQ7);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statsPanel;
		
	}
	
	
	
	private JPanel barDataset(String site, String dateVisite){

		//DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
	
	    //dataset.addValue(2, "1", "Motivation");

	    JFreeChart barChart = ChartFactory.createStackedBarChart(
	    		"Répartition des Notes","question", "Note", barDatasetVisite(site,dateVisite),PlotOrientation.VERTICAL, true, true, false ); 
	    ChartPanel cPanel = new ChartPanel(barChart); 
	    cPanel.setPreferredSize(new Dimension(500,600));
	    cPanel.setMinimumSize(new Dimension(500,600));
	    
	    GroupedStackedBarRenderer renderer = new GroupedStackedBarRenderer();
	    Paint p1 = new GradientPaint(
	            0.0f, 0.0f, new Color(0x22, 0x22, 0xFF), 0.0f, 0.0f, new Color(0x88, 0x88, 0xFF)
	        );

	    
	    	renderer.setSeriesPaint(0, Color.RED);
	        renderer.setSeriesPaint(1, Color.ORANGE);
	        renderer.setSeriesPaint(2, Color.YELLOW);
	        renderer.setSeriesPaint(3, Color.GRAY);
	        renderer.setSeriesPaint(4, Color.LIGHT_GRAY);
	        renderer.setSeriesPaint(5, Color.DARK_GRAY);
	        renderer.setSeriesPaint(6, Color.blue);
	        renderer.setSeriesPaint(7, Color.CYAN);
	        renderer.setSeriesPaint(8, Color.GREEN);
	        renderer.setSeriesPaint(9, Color.PINK);
	        
	        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
	        CategoryAxis xAxis = plot.getDomainAxis();
	        xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); 
	        plot.setBackgroundPaint(Color.lightGray);  
	        renderer.setMaximumBarWidth(1);
	        StackedBarRenderer stackedbarrenderer = (StackedBarRenderer) plot.getRenderer();  
	        stackedbarrenderer.setDrawBarOutline(false);  
	        stackedbarrenderer.setBaseItemLabelsVisible(true);  
	        stackedbarrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  

	        //plot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
	        plot.setRenderer(renderer);
	        return cPanel;
		
	}
	
	private DefaultCategoryDataset barDatasetVisite(String site, String DateVisite) {
		DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
		//pieDataset.setValue("1", new Integer(75));
		try {
			ResultSet res=myConn.getNotesQ("Q1",site, DateVisite);
			while (res.next() ) {
				barDataset.setValue( res.getInt("Nb"),res.getString("Reponse"),"condition");
			}
			ResultSet res2=myConn.getNotesQ("Q2",site,DateVisite);
			while (res2.next() ) {
				barDataset.setValue( res2.getInt("Nb"),res2.getString("Reponse"),"motivation");
			}
			ResultSet res3=myConn.getNotesQ("Q3",site,DateVisite);
			while (res3.next() ) {
				barDataset.setValue( res3.getInt("Nb"),res3.getString("Reponse"),"stress");
			}
			ResultSet res4=myConn.getNotesQ("Q4",site, DateVisite);
			while (res4.next() ) {
				barDataset.setValue( res4.getInt("Nb"),res4.getString("Reponse"),"fatigue");
			}
			ResultSet res5=myConn.getNotesQ("Q5",site,DateVisite);
			while (res5.next() ) {
				barDataset.setValue( res5.getInt("Nb"),res5.getString("Reponse"),"pression");
			}
			ResultSet res6=myConn.getNotesQ("Q6",site,DateVisite);
			while (res6.next() ) {
				barDataset.setValue( res6.getInt("Nb"),res6.getString("Reponse"),"methodes");
			}
			ResultSet res7=myConn.getNotesQ("Q7",site,DateVisite);
			while (res7.next() ) {
				barDataset.setValue( res7.getInt("Nb"),res7.getString("Reponse"),"equilibre");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return barDataset;
	}

}

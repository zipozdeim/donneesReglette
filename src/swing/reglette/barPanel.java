package swing.reglette;

import java.awt.BasicStroke;
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
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;

import data.reglette.DbConn;
import data.reglette.questions;
import net.miginfocom.swing.MigLayout;

public class barPanel extends JPanel {
	
	
	
	public static DbConn myConn = new DbConn();
	private DefaultValueDataset dataset;
	
	/**
	 * Create the panel.
	 */
	
	
	public barPanel(String site, String DateVisite) {
		
		//System.out.println("entree dans constructeur bars");
		JLabel titreMoyLbl = new JLabel("MOYENNE DE CHAQUE QUESTION");
		titreMoyLbl.setForeground(Color.BLUE);
		titreMoyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titreMoyLbl.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		this.add(titreMoyLbl);
		this.add(statsPanel(site,DateVisite));
				
		//this.add(barDataset(site,DateVisite));
	}
	
	private JPanel statsPanel(String site, String DateVisite) {
		JPanel statsPanel = new JPanel(new MigLayout());

		try {
			
			statsPanel.add(new JLabel("         "),"wrap");	
			for (int i=1;i<=7;i++) {
			ResultSet resMoyQ1, resMoyCATSQ1;
			resMoyQ1 = myConn.getMoyQ("Q"+i,site,DateVisite);
			resMoyCATSQ1= myConn.getCATSAvg("Q"+i);
			JLabel titreMoyQ1 = new JLabel(questions.valueOf("Q"+i).toString()+" :");
			Float valeurMoyQ1=resMoyQ1.getFloat("moy");
			Float valeurMoyCATSQ1=resMoyCATSQ1.getFloat("moy");
			String resultQ1=valeurMoyQ1+ " ("+ valeurMoyCATSQ1 +")";
			JLabel moyQ1 = new JLabel(resultQ1);
			//Color couleurFont= Color.BLACK;
			//if (valeurMoyQ1>=(valeurMoyCATSQ1+1)) couleurFont=ChartColor.DARK_GREEN;
			//if (valeurMoyQ1<=(valeurMoyCATSQ1-1)) couleurFont=ChartColor.RED; 
			if ((i-1) % 2==0)  {
				statsPanel.add(createPanel("Q"+i,site,DateVisite,i));
				
			} else
				
				statsPanel.add(createPanel("Q"+i,site,DateVisite,i),"wrap");
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statsPanel;
		
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

	
    private JFreeChart createChart(ValueDataset valuedataset, int i) {
        MeterPlot meterplot = new MeterPlot(valuedataset);
        meterplot.setRange(new Range(0.0D, 10D));
        meterplot.addInterval(new MeterInterval("<4", new Range(0.0D, 4D),
            Color.red, new BasicStroke(2.0F), new Color(255, 0, 0, 128)));
        meterplot.addInterval(new MeterInterval("4=>7", new Range(4D, 7D),
            Color.yellow, new BasicStroke(2.0F), new Color(255, 255, 0, 64)));
        meterplot.addInterval(new MeterInterval(">7", new Range(7D, 10D),
            Color.green, new BasicStroke(2.0F), new Color(0, 255, 0, 64)));

        meterplot.setNeedlePaint(Color.darkGray);
        meterplot.setDialBackgroundPaint(Color.white);
        meterplot.setDialOutlinePaint(Color.black);
        meterplot.setDialShape(DialShape.CHORD);
        meterplot.setMeterAngle(180);
        meterplot.setTickLabelsVisible(true);
        meterplot.setTickLabelFont(new Font("Arial", 1, 14));
        meterplot.setTickLabelPaint(Color.black);
        meterplot.setTickSize(5D);
        meterplot.setUnits("");
        meterplot.setTickPaint(Color.gray);
        meterplot.setValuePaint(Color.black);
        meterplot.setValueFont(new Font("Arial", 1, 14));
       
        JFreeChart jfreechart = new JFreeChart(questions.valueOf("Q"+i).toString(),
            JFreeChart.DEFAULT_TITLE_FONT, meterplot, true);
        return jfreechart;
    }

    private JPanel createPanel(String question, String site, String dateVisite,int i) {	
    	//String site="Vannes";
    	//String dateVisite="2024";
        try {
        	ResultSet resmoyQ1 = myConn.getMoyQ(question,site,dateVisite);
			dataset = new DefaultValueDataset(resmoyQ1.getFloat("moy"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        JFreeChart chart = createChart(dataset,i);
        ChartPanel chartpanel = new ChartPanel(chart) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(640, 480);
            }
        };
        return chartpanel;
    }

    public void update(int data) {
        dataset.setValue(data);

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
}

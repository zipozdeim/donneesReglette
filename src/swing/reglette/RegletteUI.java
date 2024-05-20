package swing.reglette;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import data.reglette.DbConn;

public class RegletteUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 655523408233278523L;
	public static DbConn myConn = new DbConn();
	public JTree arbo1;
	public DefaultMutableTreeNode visites;
	public  DefaultMutableTreeNode selectedNode;
	public JPanel cardPanel;
	public CardLayout cards ;
	/**
	 * Create the frame.
	 */
	public RegletteUI() {
		super("Stats REGLETTE");
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowAdapter()
		{
		    @Override
		    public void windowClosing(WindowEvent e)
		    {
		        super.windowClosing(e);
		        myConn.closeConection();
		    }
		});
		//PARAMETRE GENERAUX DU FRAME
		
        try {
			UIManager.setLookAndFeel( new NimbusLookAndFeel() );
		} catch (UnsupportedLookAndFeelException e2) {
			//System.out.println(" pas nimbus");
			e2.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize( 1280, 950 );
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		
		//DEFINITION D'UN PANEL PRINCIPAL EN BORDER LAYOUT NORD/OUEST/SUD/CENTER
		JPanel mainPanel=(JPanel) this.getContentPane();
		mainPanel.setBackground(Color.LIGHT_GRAY);
		mainPanel.setLayout(new BorderLayout());

		// ZONE NORD UNE TOOLBAR Boutons de détail
		mainPanel.add(createNorthToolBar(),BorderLayout.NORTH);
		
		
		

		//ZONE CENTRE : CARD PANEL - Cartes gérées par les obutons des autres zones
		//myPane.add(barDataset(),BorderLayout.CENTER);
    	cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setBackground(Color.LIGHT_GRAY);
		cardPanel.setLayout(cards);
		cardPanel.paintImmediately(cardPanel.getVisibleRect());
		
			
		//ZONE OUEST ARBOESCENCE
				visites =  new DefaultMutableTreeNode("CATS");
				arbo1=new JTree(visites);
				arbo1.setBackground(Color.LIGHT_GRAY);
				arbo1.setForeground(new Color(240,240,240));
				arbo1.paintImmediately(getBounds());
				// GESTION DU LISTENER SUR MON P'TIT TREE FANTA DIALLO OUHOUH FANTA DIALLO
				arbo1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
			                selectedNode = (DefaultMutableTreeNode)arbo1.getLastSelectedPathComponent();
			                if (!(selectedNode.isRoot())) {
			                	System.out.println("not root node");
			                
			                if ((selectedNode != null) && (selectedNode.getParent().toString()!="CATS") && (selectedNode.getUserObject().toString()!="CATS") ) {
			                	JPanel barsVisitePanel = new barPanel(selectedNode.getParent().toString(),selectedNode.getUserObject().toString());
			                	barsVisitePanel.setBounds(0,0,1000,900);
			            		mainPanel.add(cardPanel, BorderLayout.CENTER);
			            		barsVisitePanel.setAlignmentX(CENTER_ALIGNMENT);
			            		BoxLayout bx_loadCard = new BoxLayout(barsVisitePanel, BoxLayout.Y_AXIS);
			            		barsVisitePanel.setLayout( bx_loadCard );
			            		cardPanel.add(barsVisitePanel, "barsCard");
			            		cards.show(cardPanel,"barsCard");
			                    System.out.println("Selected Node: " + selectedNode.getUserObject().toString());
			                    System.out.println("Selected Node parent: " + selectedNode.getParent().toString());
			                }
			                }
			            }
			        });
				
				JPanel west = new JPanel();
				west.setLayout(new GridLayout());
				west.setAlignmentX(Component.LEFT_ALIGNMENT);
				arbo1.setAlignmentX(Component.LEFT_ALIGNMENT);
				west.add(arbo1);
				west.setPreferredSize(new Dimension(150,0));
				west.setBackground(Color.LIGHT_GRAY);
				mainPanel.add(west,BorderLayout.WEST);
				
				
				
				//BARRE D'ETAT SUD
				mainPanel.add(createStatusBar(),BorderLayout.SOUTH);

	}
	
	
	
	
	private JToolBar createNorthToolBar() {
		JToolBar toolBar=new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		toolBar.setForeground(Color.WHITE);
		toolBar.setBorderPainted(false);
		JButton saveExcel=new JButton("Export excel");
		saveExcel.setForeground(Color.BLUE);
		saveExcel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		saveExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					myConn.CreateExcelDoc();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton butnQ1=new JButton("Conditions");
		butnQ1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q1",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});
		JButton butnQ2=new JButton("Motivation");
		butnQ2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q2",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}

		});
		JButton butnQ3=new JButton("Stress");
		butnQ3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q3",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});
		JButton butnQ4=new JButton("Fatigue");
		butnQ4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q4",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});
		JButton butnQ5=new JButton("Pression");
		butnQ5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q5",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});
		JButton butnQ6=new JButton("Methodes");
		butnQ6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q6",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});
		JButton butnQ7=new JButton("Equilibre");
		butnQ7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currentSite=selectedNode.getParent().toString();
				String currentDate=selectedNode.getUserObject().toString();
				chartPieQ pieCard = new chartPieQ("Q7",currentSite,currentDate);
				cardPanel.add(pieCard, "pieCard");
				cards.show(cardPanel,"pieCard");
			}
		});

		
		toolBar.add(saveExcel);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ1);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ2);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ3);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ4);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ5);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ6);
		toolBar.addSeparator(new Dimension(20,0));
		toolBar.add(butnQ7);
		return toolBar;
	}
	
	
	private JPanel createStatusBar() {
		JPanel statusBar=new JPanel();
		statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel statusLbl=new JLabel("statusLbl");
		statusBar.add(statusLbl);
		return statusBar;
	}
	
	

	



}

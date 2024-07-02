package swing.reglette;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
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
import javax.swing.tree.TreePath;

import data.reglette.DbConn;
import data.reglette.questions;

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

		// ZONE NORD UNE TOOLBAR Boutons de d�tail
		mainPanel.add(createNorthToolBar(),BorderLayout.NORTH);

		//ZONE CENTRE : CARD PANEL - Cartes g�r�es par les obutons des autres zones
		//myPane.add(barDataset(),BorderLayout.CENTER);
		cards = new CardLayout();
		cardPanel = new JPanel();
		mainPanel.add(cardPanel, BorderLayout.CENTER);
		cardPanel.setBackground(Color.LIGHT_GRAY);
		cardPanel.setLayout(cards);
		cardPanel.paintImmediately(cardPanel.getVisibleRect());
		
		//ZONE OUEST ARBOESCENCE
		visites =  new DefaultMutableTreeNode("CATS");
		arbo1=new JTree(visites);
		DefaultMutableTreeNode firstLeaf = ((DefaultMutableTreeNode)arbo1.getModel().getRoot()).getFirstLeaf();
		TreePath firstLeafPath=new TreePath(firstLeaf.getPath());
		arbo1.setSelectionPath(firstLeafPath);
		arbo1.setBackground(Color.LIGHT_GRAY);
		arbo1.setForeground(new Color(240,240,240));
		arbo1.paintImmediately(getBounds());
		JPanel globalCatsP= new globalCATSPanel();
		globalCatsP.setBounds(0,0,1000,900);
		globalCatsP.setAlignmentX(CENTER_ALIGNMENT);
		BoxLayout globalCATSLayout = new BoxLayout(globalCatsP, BoxLayout.Y_AXIS);
		globalCatsP.setLayout( globalCATSLayout );
		cardPanel.add(globalCatsP, "globalCatsP");
		JPanel noDatas= new JPanel();
		JLabel noDatasLabel= new JLabel("");
		noDatasLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		noDatas.add(noDatasLabel);
		cards.show(cardPanel,"globalCatsP");
		cardPanel.add(noDatas, "noDatas");
		// GESTION DU LISTENER SUR MON P'TIT TREE FREE FANTA DIALLO OUHOUH FANTA DIALLO
		arbo1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				selectedNode = (DefaultMutableTreeNode)arbo1.getLastSelectedPathComponent();
				if ( (selectedNode.isRoot()) ) {
					// System.out.println("root node ou Fils du root node");		
					// mainPanel.add(cardPanel, BorderLayout.CENTER);
					cards.show(cardPanel,"globalCatsP");
				} else {
					if ((selectedNode != null) && (selectedNode.getParent().toString()!="CATS") && (selectedNode.getUserObject().toString()!="CATS") ) {
						JPanel barsVisitePanel = new barPanel(selectedNode.getParent().toString(),selectedNode.getUserObject().toString());
						barsVisitePanel.setBounds(0,0,1000,900);
						mainPanel.add(cardPanel, BorderLayout.CENTER);
						barsVisitePanel.setAlignmentX(CENTER_ALIGNMENT);
						BoxLayout bx_loadCard = new BoxLayout(barsVisitePanel, BoxLayout.Y_AXIS);
						barsVisitePanel.setLayout( bx_loadCard );
						cardPanel.add(barsVisitePanel, "barsCard");
						cards.show(cardPanel,"barsCard");
						//System.out.println("Selected Node: " + selectedNode.getUserObject().toString());
						//System.out.println("Selected Node parent: " + selectedNode.getParent().toString());
					}
					if (selectedNode.getParent().toString()=="CATS") {
						cards.show(cardPanel,"noDatas");
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
		toolBar.setForeground(Color.LIGHT_GRAY);
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
		toolBar.add(saveExcel);
		for(int i=1;i<=7;i++) {
			String item= questions.valueOf("Q"+i).toString();
			JButton butn=new JButton(item);
			String question="Q"+i;
			CATSBarsPanel catsBars = new CATSBarsPanel(question);
			butn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String currentSite="",currentDate="";
					if (selectedNode==null ) {
						//System.out.println("je suis NULL");
						cardPanel.add(catsBars, "catsBars");
						cards.show(cardPanel,"catsBars");
						
					} else {
						if (selectedNode.isRoot()) {
							//System.out.println("je suis CATS");
							cardPanel.add(catsBars, "catsBars");
							cards.show(cardPanel,"catsBars");
						} else {
							if (selectedNode.getParent().toString()=="CATS") {
									//System.out.println("site : "+selectedNode.getParent().toString()); 
									//System.out.println("je suis Site"); //Ouuuuh le méchant site)
								cards.show(cardPanel,"noDatas");
							} else {
								//System.out.println("je suis visite");
								currentSite+=selectedNode.getParent().toString();
								currentDate+=selectedNode.getUserObject().toString();
								// System.out.println(currentSite + "*" +currentDate);
								chartPieQ pieCard = new chartPieQ(question,currentSite,currentDate);
								cardPanel.add(pieCard, "pieCard");
								cards.show(cardPanel,"pieCard");
							}
						}
					}
				}
			});
			toolBar.addSeparator(new Dimension(20,0));
			toolBar.add(butn);
		}
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

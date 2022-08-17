package com.wipro.igrs.device.applet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Display extends JFrame implements ActionListener {

	private JPanel contentPane;
ListQueue objQu;
Timer timer;
JLabel lblMec;
JLabel lblMac;
JLabel lblCec;
JLabel lblCac; 
public String language;
Color brown = new Color(139,125,105);
private Image backgroundImage;; 
int count =0;
private JTable table;
private JTable table_1;
String[] header = {"Etoken No", "Registration ID","Category","Persons Allowed","Counter -No:"};
String[][] rowAndColumn = {
		{"Dog", "Mammal"},
		{"Cat", "Mammal"},
		{"Shark", "Fish"},
		{"Parrots", "Bird"}
};
MyModel models = new MyModel(null,header);
MyModel1 models1 = new MyModel1(null,header);
//private JTable table_2;
private JLabel lblIgrs;
private JLabel label;
private JLabel lblMakerQueue;
private JLabel lblCheckerQueue;
public int x=120;
public int y = 140;
public int fontSize = 0;
public int rowHeight = 0;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @param heightS 
	 * @param widthS 
	 * @param fontSize 
	 * @param rowHeight 
	 */
	public Display(ListQueue obj, int widthS, int heightS, int fontSize, int rowHeight) {

		try{
		//	language = obj.getLanguage();
		//	models.language=language;
		//	models1.language = language;
			// backgroundImage = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Lighthouse.jpg"));
		timer = new Timer(2000,this);
		timer.start();
		objQu = obj;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GraphicsEnvironment ge = GraphicsEnvironment.
		   getLocalGraphicsEnvironment();
		   GraphicsDevice[] gs = ge.getScreenDevices();
		  this.fontSize = fontSize;
		   this.rowHeight = rowHeight;
		  
		Toolkit toolkit =  Toolkit.getDefaultToolkit ();
		  Dimension dim = toolkit.getScreenSize();
		//setBounds(0,0, gs[1].getDefaultConfiguration().getBounds().width, gs[].getDefaultConfiguration().getBounds().height);
		
		  
		 System.out.println(dim.width +" -- "+ dim.height);
		System.out.println(getSize().width+" -- "+getSize().height);
		int mid = widthS/2;
		int height = heightS;
		
		
		int table1x1 = mid/10;
		int table1y1 = 18*height/100;
		
		int table1x2 = widthS-table1x1-(mid/10);
		int table1y2 = ((height-(18*height)/100))-table1y1;
		
		int table2x1 = mid+table1x1;
		int table2y1 = table1y1;
		int table2x2 = table1x2;
		int table2y2 = table1y2;
		
		
		addMouseListener(new MouseAdapter() {  
			public void mousePressed(MouseEvent e) {  
			if(!e.isMetaDown()){  
			
			x = e.getX();  
			y = e.getY();  
			}   
			}  
			});  
			addMouseMotionListener(new MouseMotionAdapter() {  
			public void mouseDragged(MouseEvent e) {  
			if(!e.isMetaDown()){  
			Point p = getLocation();  
			setLocation(p.x + e.getX() - x,  
			p.y + e.getY() - y);  
			}  
			}  
			});  
		
		setIconImage(null);
		setTitle("IGRS");
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY.brighter());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMakerEmptyCounter = new JLabel("निर्माता खाली काउंटर गणना");
		lblMakerEmptyCounter.setBounds(103, 651, 160, 14);
		lblMakerEmptyCounter.setForeground(Color.WHITE);
		lblMakerEmptyCounter.setVisible(false);
		contentPane.add(lblMakerEmptyCounter);
		
		JLabel lblMakerAssignedCounter = new JLabel("निर्माता निरुपित काउंटर गणना");
		lblMakerAssignedCounter.setBounds(103, 676, 183, 14);
		lblMakerAssignedCounter.setForeground(Color.WHITE);
		lblMakerAssignedCounter.setVisible(false);
		contentPane.add(lblMakerAssignedCounter);
		
		JLabel lblCheckerEmptyCounter = new JLabel("चेकर खाली काउंटर गणना");
		lblCheckerEmptyCounter.setBounds(616, 651, 160, 14);
		lblCheckerEmptyCounter.setForeground(Color.WHITE);
		lblCheckerEmptyCounter.setVisible(false);
		contentPane.add(lblCheckerEmptyCounter);
		
		JLabel lblCheckerAssignedCounter = new JLabel("चेकर निरुपित काउंटर गणना");
		lblCheckerAssignedCounter.setBounds(620, 676, 183, 14);
		lblCheckerAssignedCounter.setForeground(Color.WHITE);
		lblCheckerAssignedCounter.setVisible(false);
		contentPane.add(lblCheckerAssignedCounter);
		
		lblMec = new JLabel("meC");
		lblMec.setBounds(273, 651, 46, 14);
		lblMec.setForeground(Color.WHITE);
		lblMec.setVisible(false);
		contentPane.add(lblMec);
		
		lblMac = new JLabel("Mac");
		lblMac.setBounds(273, 676, 46, 14);
		lblMac.setForeground(Color.WHITE);
		lblMac.setVisible(false);
		contentPane.add(lblMac);
		
		lblCec = new JLabel("CeC");
		lblCec.setBounds(817, 651, 46, 14);
		lblCec.setForeground(Color.WHITE);
		lblCec.setVisible(false);
		contentPane.add(lblCec);
		
		lblCac = new JLabel("Cac");
		lblCac.setBounds(817, 676, 46, 14);
		lblCac.setForeground(Color.WHITE);
		lblCac.setVisible(false);
		contentPane.add(lblCac);
		
		
		
		table = new JTable(models);
		table.getTableHeader().setFont(new Font("serif", Font.ITALIC, fontSize));
		table.setBorder(BorderFactory.createLineBorder(Color.YELLOW.darker().darker(), 2));
		table.setBounds(1, 26, 682, 16);
		 table.setBackground(new Color(255,228,225));
			table.setFont(new Font("Serif", Font.BOLD, fontSize));
			table.setRowHeight(rowHeight);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
		//table.setBounds(58, 217, 684, 200);
		contentPane.add(table);
		
		/* DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		 headerRenderer.setBackground(new Color(239, 198, 46));

		 for (int i = 0; i < table.getModel().getColumnCount(); i++) {
			 table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		 }*/
		
		//Compound borders
		Border compound;
		Border redline = BorderFactory.createLineBorder(Color.red);
		Border	raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		//This creates a nice frame.
		compound = BorderFactory.createCompoundBorder(
		                          raisedbevel, loweredbevel);
	
		JScrollPane pane = new JScrollPane(table);
		pane.setBorder(compound);
		pane.setBounds(table1x1,table1y1-20,table1x2,table1y2+100);
		pane.setViewportView(table);
		pane.setOpaque(false);
		pane.getViewport().setOpaque(false);
		contentPane.add(pane);
		
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Dialog", Font.CENTER_BASELINE, fontSize));
	    header.setBackground(Color.black);
	    header.setForeground(Color.yellow);
		//table_1 = new JTable(models1);
		//table_1.setBounds(0, 0, 0, 0);
		//table_1.setBounds(58, 454, 684, 200);

		JScrollPane pane1 = new JScrollPane();
		pane1.setBounds(table2x1,table2y1-20,table2x2+300,table2y2+100);
	//	pane1.add(table_1);
		//contentPane.add(pane1);
		
	
		
		/*table_2 = new JTable();
		table_2.getTableHeader().setFont(new Font("serif", Font.ITALIC, fontSize));
		table_2.setBackground(new Color(255,228,225));
		table_2.setBorder(BorderFactory.createLineBorder(Color.YELLOW.darker().darker(), 2));
		table_2.setFont(new Font("Serif", Font.BOLD, fontSize));
		table_2.setRowHeight(rowHeight);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		
		JTableHeader header_1 = table_2.getTableHeader();
		header_1.setFont(new Font("Dialog", Font.CENTER_BASELINE, fontSize));
		header_1.setBackground(Color.black);
		header_1.setForeground(Color.yellow);
		pane1.setViewportView(table_2);
		pane1.setBorder(compound);*/
		
		int xL = (3*dim.width/10);
		int xY = (dim.height/20);
		
		/*lblIgrs = new JLabel("रजिस्ट्रेशन एवं स्टाम्प विभाग ( वाणिज्यिक कर ),");
		lblIgrs.setBounds(xL, xY, 672, 45);
		lblIgrs.setFont(new Font("serif",Font.BOLD,30));
		lblIgrs.setForeground(Color.yellow);
		contentPane.add(lblIgrs);
		
		label = new JLabel("मध्य प्रदेश ");
		label.setBounds(xL+205, xY+40, 251, 35);
		label.setFont(new Font("serif",Font.BOLD,30));
		label.setBackground(Color.yellow);
		label.setForeground(Color.yellow);
		contentPane.add(label);*/
		
		
		int labelMx1 = table1x1+((table1x2*4)/10);
		int labelMx2 = table1x2 - ((table1x2*4)/10);
		int labelMy1 = table1y1-30;
		int labelMy2 = 35;
		
		int label2Mx1 =table2x1+((table2x2*4)/10);
		int label2My1 =table1y1-30;
		int label2Mx2 =table2x2 - ((table2x2*4)/10);
		int label2My2 =35;
		lblMakerQueue = new JLabel(" निर्माता की कतार ");
		lblMakerQueue.setBounds(labelMx1, labelMy1-30, labelMx2, labelMy2);
		lblMakerQueue.setFont(new Font("serif",Font.BOLD,30));
		lblMakerQueue.setForeground(Color.yellow);
		//contentPane.add(lblMakerQueue);
		
		lblCheckerQueue = new JLabel(" चेकर की कतार ");
		lblCheckerQueue.setBounds(label2Mx1,label2My1-30,label2Mx2,label2My2);
		lblCheckerQueue.setFont(new Font("serif",Font.BOLD,30));
		lblCheckerQueue.setForeground(Color.yellow);
	//	contentPane.add(lblCheckerQueue);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		synchronized (objQu) {
			count++;
			Vector<ETokenDTO> makerCounter =  objQu.getMakerCounter();
			//System.out.println("Make Check " + makerCounter.size());
			Vector<ETokenDTO> checkerCounter = objQu.getCheckerCounter();
			//System.out.println("Check Check " +checkerCounter.size());
			Vector<ETokenDTO> makerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> makerAssigned = new Vector<ETokenDTO>();
			Vector<ETokenDTO> checkerEmpty = new Vector<ETokenDTO>();
			Vector<ETokenDTO> checkerAssigned = new Vector<ETokenDTO>();
			
			for(int i=0;i<makerCounter.size();i++)
			{
				ETokenDTO dto = makerCounter.get(i);
				if("Y".equalsIgnoreCase(dto.getAssigned()))
				{
					makerAssigned.add(dto);
				}
				else
				{
					
					makerEmpty.add(dto);
					
				}
				
				
			}
			
			for(int i=0;i<checkerCounter.size();i++)
			{
				ETokenDTO dto = checkerCounter.get(i);
				if("Y".equalsIgnoreCase(dto.getAssigned()))
				{
					checkerAssigned.add(dto);
				}
				else
				{
					
					checkerEmpty.add(dto);
					
				}
				
				
			}
			
			
			lblMec.setText(String.valueOf(makerEmpty.size()));
			lblCec.setText(String.valueOf(checkerEmpty.size()));
			lblCac.setText(String.valueOf(checkerAssigned.size()));
			lblMac.setText(String.valueOf(makerAssigned.size()));
			
			
			models.add("ds", "sd", objQu);
			models1.add("l", "m", objQu);
		}
		
		 TableColumnModel model = table.getColumnModel();
		int i =  model.getColumnCount();
		 for(int j=0;j<i;j++)
		 {
			 
			 model.getColumn(j).setCellRenderer(new CustomTableCellRenderer());
		 }
		 
		/* TableColumnModel model_1 = table_2.getColumnModel();
			int k =  model_1.getColumnCount();
			 for(int j=0;j<k;j++)
			 {
				 
				 model_1.getColumn(j).setCellRenderer(new CustomTableCellRenderer());
			 }*/
			
	repaint();	
	
	}
	
	
	
	public void paintComponent(Graphics g) {
	    

	    // Draw the background image.
	//    g.drawImage(backgroundImage, 0, 0, this);
	  }
}




// class that extends the AbstractTableModel
class MyModel extends AbstractTableModel {
	public String language="en";
	// to store our elements it will be great to avoid parallel array and use 
	// an ArrayList<Animal> but for simplicity and not to have to add a new 
	// class with will use an ArrayList<Object> for each row
	
	ArrayList<Object[]> al;
	// the headers
	
	
	
	
	
	String[] header = {"ई टोकन","निर्माता काउंटर","SR काउंटर","मेकर   (दस्तावेज़ प्राप्ति)"};
	// constructor 
	MyModel(ListQueue objQu, String[] header) {
		// save the header
	//	this.header = header;	
		// and the rows
		al = new ArrayList<Object[]>();
		String[] str = new String[4];
		str[0] = "--";
		str[1] = "--";
		str[2] = "--";
		str[3] = "--";
		al.add(str);
		
	}
	// method that needs to be overload. The row count is the size of the ArrayList
	public int getRowCount() {
		return al.size();
	}

	// method that needs to be overload. The column count is the size of our header
	public int getColumnCount() {
		return header.length;
	} 

	// method that needs to be overload. The object is in the arrayList at rowIndex
	public Object getValueAt(int rowIndex, int columnIndex) {
		return al.get(rowIndex)[columnIndex];
	}
	
	// a method to return the column name 
	public String getColumnName(int index) {
		return header[index];
	}
	
	// a method to add a new line to the table
	void add(String animal, String family,ListQueue objQu) {
		// make it an array[2] as this is the way it is stored in the ArrayList
		// (not best design but we want simplicity)
		
		al.removeAll(al);
		synchronized (objQu) {
			Vector<ETokenDTO> MFdto = objQu.getMakerCheckerFinal();
		//	Vector<ETokenDTO> CFdto = objQu.getCheckerFinal();
			for(int i = 0; i < MFdto.size(); ++i)
			{
				ETokenDTO dto =   MFdto.get(i);
				
				
				if(dto.getCounterNo() == null||dto.getCounterNo().equalsIgnoreCase("null")){
					dto.setCounterNo("");
					
				}
				
				if(dto.getCounterNoChecker() == null ||dto.getCounterNoChecker().equalsIgnoreCase("null")){
					dto.setCounterNoChecker("");
				//	System.out.println("CounterNoChecker....... .."+dto.getCounterNoChecker());		
					
				}
				
				if(dto.getCollectCounter() == null || dto.getCollectCounter().equalsIgnoreCase("null")){
					dto.setCollectCounter("");
				//	System.out.println("CollectCounter....... .."+dto.getCollectCounter());		
				}
				String obj[] = new String[4];
				String regId = dto.getRegistrationId();
				/*System.out.println("No in display....... .."+dto.getEtokenNo());
				System.out.println("Maker in display.............."+dto.getCounterNo());
				System.out.println("Checker in display............"+dto.getCounterNoChecker());
				System.out.println("Collector in display..........."+dto.getCollectCounter());*/
				
				obj[0] = dto.getEtokenNo();
				
			/*	if(!dto.getCounterNo().isEmpty()){
					
				obj[1] = dto.getCounterNo();
				}
				
				else if(dto.getCounterNo().isEmpty() && !dto.getCounterNoChecker().isEmpty() && !dto.getCollectCounter().isEmpty()) {
					
					
					obj[1] = "Waiting";
				}
				*/
				
				
				
				
				if(dto.getCounterNo().isEmpty() && dto.getCounterNoChecker().isEmpty() && dto.getCollectCounter().isEmpty()){
					
					obj[1] = "Waiting";
				}
				
				else if (dto.getCounterNo().isEmpty()  && !dto.getCounterNoChecker().isEmpty() && dto.getCollectCounter().isEmpty()){
					
					obj[1] = dto.getCounterNo();
				}
				 
				
				else if (dto.getCounterNo().isEmpty()  && dto.getCounterNoChecker().isEmpty() && !dto.getCollectCounter().isEmpty()){
					
					obj[1] = dto.getCounterNo();
				}
				 
				
				else{
					obj[1] = dto.getCounterNo();	
				}
				obj[2] = dto.getCounterNoChecker();
				obj[3] = dto.getCollectCounter();
				al.add(obj);
			}
		
				
			
		}
		
		// inform the GUI that I have change
		fireTableDataChanged();
	}
	


}


class MyModel1 extends AbstractTableModel {

	public String language="en";
	// to store our elements it will be great to avoid parallel array and use 
	// an ArrayList<Animal> but for simplicity and not to have to add a new 
	// class with will use an ArrayList<Object> for each row
	ArrayList<Object[]> al;
	// the headers
	
	String[] header = {"ई टोकन","काउंटर"};
	
	// constructor 
	MyModel1(ListQueue objQu, String[] header) {
		// save the header
	//	this.header = header;	
		// and the rows
		al = new ArrayList<Object[]>();
		String[] str = new String[2];
		str[0] = "--";
		str[1] = "--";
	
		al.add(str);
		
	}
	// method that needs to be overload. The row count is the size of the ArrayList
	public int getRowCount() {
		return al.size();
	}

	// method that needs to be overload. The column count is the size of our header
	public int getColumnCount() {
		return header.length;
	}

	// method that needs to be overload. The object is in the arrayList at rowIndex
	public Object getValueAt(int rowIndex, int columnIndex) {
		return al.get(rowIndex)[columnIndex];
	}
	
	// a method to return the column name 
	public String getColumnName(int index) {
		return header[index];
	}
	
	// a method to add a new line to the table
	void add(String animal, String family,ListQueue objQu) {
		// make it an array[2] as this is the way it is stored in the ArrayList
		// (not best design but we want simplicity)
		
		al.removeAll(al);
		synchronized (objQu) {
			Vector<ETokenDTO> MFdto = objQu.getCheckerFinal();
		
			for(int i = 0; i < MFdto.size(); ++i)
			{
				ETokenDTO dto =   MFdto.get(i);
				
				String obj[] = new String[2];
				obj[0] = dto.getEtokenNo();
				
				obj[1] = dto.getCounterNo();
				
				al.add(obj);
			}
				
			
		}
		
		// inform the GUI that I have change
		fireTableDataChanged();
	}
}

class CustomTableCellRenderer extends DefaultTableCellRenderer{
	  public Component getTableCellRendererComponent (JTable table, 
	Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
	  Component cell = super.getTableCellRendererComponent(
	   table, obj, isSelected, hasFocus, row, column);
	setHorizontalAlignment(JLabel.CENTER);
	//System.out.println("Hello");
	
	String makerCounter =  "";
	String checkerCounter ="";
	if(table.getValueAt(row, 1).toString() == null ||table.getValueAt(row, 1).toString().equalsIgnoreCase("null") ){
		
		 makerCounter =  "";
	//	 System.out.println("makerCounter  set empty");
	}
	else{
	 makerCounter =  table.getValueAt(row, 1).toString();}
	
	if(table.getValueAt(row, 2).toString() == null ||table.getValueAt(row, 2).toString().equalsIgnoreCase("null") ){
		checkerCounter = "";
	//	 System.out.println("checkerCounter  set empty");
	}
	else{
		checkerCounter =  table.getValueAt(row, 2).toString();
		
	}
	 
//System.out.println("makerCounter value    "+makerCounter);

//System.out.println("checkerCounter value    "+checkerCounter);
	if(!makerCounter.equalsIgnoreCase("") || !checkerCounter.equalsIgnoreCase("")  )
	{
		
		if(makerCounter.equalsIgnoreCase("waiting")){
			cell.setBackground(new Color(252,242,206));
			
		}
		else{
		cell.setBackground(Color.RED);
		}
	}
	else
	{
		cell.setBackground(new Color(252,242,206));
	}
	
	
	
	//	System.out.println("Here");
	  return cell;
	  }
	  }
	

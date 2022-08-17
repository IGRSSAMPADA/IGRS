package com.wipro.igrs.device.applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import javax.swing.DefaultComboBoxModel;





 

public class EtokenApplet extends JApplet {
	
	private  String paramFileName;
public EtokenApplet(){
	
	this("df"); 
	 
	System.out.println("Construct");
}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	@Override
	public void start() {
		
		super.start();
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		System.exit(0);
	}
	public ListQueue qu;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField personallowedtextField;
	private JLabel lblNewLabel_1;
	private JLabel name_label;
	private JLabel lblSlotdatevalue;
	private JLabel lblApplicationtype;
	private JLabel lblError;
	private JPanel panel_Content;
	private JRadioButton rdbtnSpecial;
	private JRadioButton rdbtnNormal;
	private ButtonGroup bg;
	private JButton btnSearch;
	private JLabel lblSlottime;
	JLabel lblOfficeId ;
	private String mo;
	private JLabel lblDistrict;
	private JLabel lblSrOffice;
	private String districtName;
	private String officeName;
	private JLabel lblCategory;
	private String language;
	private final JLabel lblApplicaionType;
	private JLabel lblL;
	private JLabel lblNumberOfPersons;
	ETokenDTO dto;
	private JButton btnGenerateEtoken;
	private JButton btnReissueEtoken;
	private final JLabel lblSlotDate;
	final JLabel lblSlotTime;
	private boolean checkObject;
	private JLabel lblNewLabel;
	private JLabel lblApplicationtypevalue;
	// private JComboBox manualCombo;
	 private JComboBox comboBox;
	 private JComboBox comboBox1;
	 private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	private URL urls;

	// Object Persistence
	private  synchronized  boolean getObject() {
		boolean check = false;
		FileInputStream in =null;
		ObjectInputStream readers = null;
		try {
			File yourFile = new File("D:\\Data\\obj.sav");
			if(!yourFile.exists()) {
			//	System.out.println("File Doesnot Exist while getting it ");
			    yourFile.createNewFile();
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
			    qu = new ListQueue();
			    out.writeObject(qu);
			    out.close();
			    check = false;

			} 
			else
			{
			 in = new FileInputStream("D:\\Data\\obj.sav");
			 readers = new ObjectInputStream(in);
		
			try {
				qu  = (ListQueue)readers.readObject();
//				System.out.println("Get bean Objest method"+qu.getMakerCheckerN().get(0));
					 for (int i=0;i<qu.getMakerCheckerN().size();i++){
					 ETokenDTO data=qu.getMakerCheckerN().get(i);
					// System.out.println("Etoken No "+data.getEtokenNo());
				//	 System.out.println("Wait count "+data.getWaitCounterMaker());
				//	 System.out.println("app status"+data.getAppStatus());
				//	 System.out.println("id "+data.getRegistrationId());
					 
					 
					 
				 }
				
				
 				
				//System.out.println("Get bean Objest method"+qu.getMakerCheckerY().size());
				
				
			
				
				check = true;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			in.close();
			readers.close();
			}
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("counter "+qu.getMakerCheckerN().size());
		saveObject(qu);
		return check;
		
		
	}
	/**
	 * Create the frame.
	 */
	
	public void init(){
		try{
			// qu = new ListQueue();
		//	getObject();
			/*ETokenDTO dto1 = new ETokenDTO();
			ETokenDTO dto2 = new ETokenDTO();
			dto1.setCounterName("Maker-1");
			dto1.setCounterNo("1");
			dto1.setAssigned("N");
			dto2.setCounterName("Maker-2");
			dto2.setCounterNo("2");
			dto2.setAssigned("N");
			qu.getMakerCounter().add(dto1);
			qu.getMakerCounter().add(dto2);*/
			/*ThreadListener lists = new ThreadListener(qu);
			Thread t = new Thread(lists);
			t.start();
			ProcessorThread thread = new ProcessorThread(qu);
			Thread l = new Thread(thread);
			l.start();
			Display d = new Display(qu);
			d.setUndecorated(true);
			d.setVisible(true);
			
			TimeManagerClass times = new TimeManagerClass(qu);
			times.setVisible(false);*/
			
			//System.out.println("sssss"+ getMo() );
		/*SwingUtilities.invokeAndWait(new Runnable(){
		public void run(){
		//	BO obj = new BO();
		//	String detail = obj.getDetails();
			getDetails();
			abcd frame = new abcd("sdfsdf");
			frame.getObject();
			frame.setVisible(true);
		}
		});
		}*/
		}
		catch(Exception e){
		System.out.println(e);
		}
		}
	
	private void getDetails() {
		try{
		String getCodeBase = this.getCodeBase().toString();
		URL url = new URL(getCodeBase + "/ETokenServlet");
		urls = url;
	//	System.out.println("url is>>>" + url);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/binary");
		HttpURLConnection hc = (HttpURLConnection) (connection);
	//	System.out.println(hc);
		hc.setRequestMethod("POST");
		OutputStream os = hc.getOutputStream();
		InetAddress	ip = InetAddress.getLocalHost();
		HashMap<String, Object> payload = new HashMap<String, Object>();
	//	System.out.println(ip.getHostAddress());
		payload.put("work", "getInitialDetails");
		
		payload.put("ofc", lblOfficeId.getText());
		payload.put("ip", ip.getHostAddress());
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(payload);
		oos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean reissueEtoken(ETokenDTO dto)
	{
		boolean ch = false;
		try
		{
			ch = outputtingBarcodeAsPNG(dto.getRegistrationId());
	//	System.out.println(ch);
		if(ch){
			  Image img = new ImageIcon("D:\\mybarcode.jpg").getImage();
		ch = printnow.printCards(dto.getEtokenNo(), dto.getRegistrationId(), dto.getNoOfPersons(), dto.getTypeOfPerson(), dto.getOfficeId(),dto.getSlotDate(),img );
		
		img.flush();
		
		
		return ch;
		}
		}
		catch (Exception e) {
			e.printStackTrace();		}
		return checkObject;
		}
	
	
	private boolean printEtoken() {
		boolean kam=false;
		try{
			
		String getCodeBase = this.getCodeBase().toString();
		URL url = new URL(getCodeBase + "/ETokenServlet");
	//	System.out.println("url is>>>" + url);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/binary");
		HttpURLConnection hc = (HttpURLConnection) (connection);
	//	System.out.println(hc);
		hc.setRequestMethod("POST");
		OutputStream os = hc.getOutputStream();
		InetAddress	ip = InetAddress.getLocalHost();
		HashMap<String, Object> payload = new HashMap<String, Object>();
		//System.out.println(ip.getHostAddress());
		payload.put("work", "printEtoken");
		
		payload.put("ofc", lblOfficeId.getText());
		payload.put("ip", ip.getHostAddress());
		payload.put("dto",dto );
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(payload);
		oos.close();
		ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
		dto = (ETokenDTO) in.readObject();
		System.out.println("Before Printing..");
		if(dto!=null)
		{
			kam=true;
			try{
			printTheToken(dto);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			}
		else
		{

			if(lblL.getText().equalsIgnoreCase("en"))
			lblError.setText("Slot Should be booked for Today only");
			else
				lblError.setText("स्लॉट आज के लिए आरक्षित किया जाना चाहिए ही");
			kam=false;
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return kam;
	}
	
	public void printTheToken(ETokenDTO dto )
	{
		try{
		boolean ch = outputtingBarcodeAsPNG(dto.getRegistrationId());
		
		if(ch){
			  Image img = new ImageIcon("D:\\mybarcode.jpg").getImage();
			  try
			  {
		ch = printnow.printCards(dto.getEtokenNo(), dto.getRegistrationId(), dto.getNoOfPersons(), dto.getTypeOfPerson(), name_label.getText(),lblSlotdatevalue.getText(),img );
			  }
			  catch (Exception e) {
				
				e.printStackTrace();
			}
		
		img.flush();
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean outputtingBarcodeAsPNG(String RegId) throws BarcodeException {
        // get a Barcode from the BarcodeFactory
		boolean check = false;
		Barcode bar = BarcodeFactory.createCode128(RegId);
bar.setBarWidth(1);

        try {
            File f = new File("D:\\mybarcode.jpg");
            
            // Let the barcode image handler do the hard work //
            BarcodeImageHandler.saveJPEG(bar, f);
            check = true;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return check;
        
    }
	
	public static void main(String[] args) {
		
		EtokenApplet app = new  EtokenApplet();
		app.getObject();
		
	}
	public void sendToServlet(String regId) {
		try {

			
//			String url1 = "http://localhost:8080/IGRS";
			String getCodeBase = this.getCodeBase().toString();
			URL url = new URL(getCodeBase + "/ETokenServlet");
			//System.out.println("url is>>>" + url);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/binary");
			HttpURLConnection hc = (HttpURLConnection) (connection);
			//System.out.println(hc);
			hc.setRequestMethod("POST");
			OutputStream os = hc.getOutputStream();
			InetAddress	ip = InetAddress.getLocalHost();
			HashMap<String, Object> payload = new HashMap<String, Object>();
		//	System.out.println(ip.getHostAddress());
			payload.put("regID", regId);
			payload.put("ofc", lblOfficeId.getText());
			payload.put("ip", ip.getHostAddress());
			payload.put("work", "getDetails");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(payload);
			oos.close();
			
			ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
			com.wipro.igrs.device.applet.ETokenDTO myrespObj;
			try
			{
			    myrespObj= (com.wipro.igrs.device.applet.ETokenDTO) in.readObject();
			   if(!myrespObj.getAppStatus().equalsIgnoreCase("0"))
			   {
				   try {
						// dto = obj.searchEtoken(textField.getText(), "OFC02");
						
							if("10".equalsIgnoreCase(myrespObj.getAppStatus()))
							{
								if(lblL.getText().equalsIgnoreCase("en"))
									lblApplicationtype.setText("For Maker");
								else
									lblApplicationtype.setText("निर्माता के लिए"); 
								
							}
							else if ("11".equalsIgnoreCase(myrespObj.getAppStatus()))
							{
								if(lblL.getText().equalsIgnoreCase("en"))
										lblApplicationtype.setText("On Maker-Hold");
									else
										lblApplicationtype.setText("निर्माता के द्वारा होल्ड पर");
								
							}
							else if ("13".equalsIgnoreCase(myrespObj.getAppStatus()))
							{
								if(lblL.getText().equalsIgnoreCase("en"))
										lblApplicationtype.setText("For Checker");
									else
										lblApplicationtype.setText("चेकर के लिए");
								
								
							}
							else if ("14".equalsIgnoreCase(myrespObj.getAppStatus()))
							{
								if(lblL.getText().equalsIgnoreCase("en"))
										lblApplicationtype.setText("On Checker-Hold");
									else
										lblApplicationtype.setText("चेकर के द्वारा होल्ड पर");
								
							}
				lblSlotdatevalue.setText(myrespObj.getSlotDate());
						lblSlottime.setText(myrespObj.getTimeFrom()+" to "+myrespObj.getTimeTo());
						lblError.setText("");
					dto = myrespObj;
						bg.clearSelection();
				   }
			    catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
				else
					{
							if(lblL.getText().equalsIgnoreCase("en"))
							lblError.setText("Invalid Details! Please Enter correct Registration Initiation Id");
							else
							lblError.setText("गलत विवरण! सही पंजीकरण शुरूआत आईडी दर्ज करें");
							lblApplicationtype.setText("");
							lblSlotdatevalue.setText("");
							personallowedtextField.setText("");
							
							bg.clearSelection();
							
					}
						
					
				   
				   
				   
			   
			} catch (ClassNotFoundException e1)
			{
			    e1.printStackTrace();
			}
			finally
			{
				in.close();
			}
			
			
			
			
		//	System.out.println("File uploaded. Response Code is "
		//			+ hc.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean sendToServletMove(String regId,String Status) {
		boolean myrespObj = false;
		try {

		

			String getCodeBase = this.getCodeBase().toString();
			URL url = new URL(getCodeBase + "/ETokenServlet");
			//System.out.println("url is>>>" + url);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/binary");
			HttpURLConnection hc = (HttpURLConnection) (connection);
			//System.out.println(hc);
			hc.setRequestMethod("POST");
			OutputStream os = hc.getOutputStream();
			InetAddress	ip = InetAddress.getLocalHost();
			HashMap<String, Object> payload = new HashMap<String, Object>();
		//	System.out.println(ip.getHostAddress());
			payload.put("regID", regId);
			payload.put("ofc", lblOfficeId.getText());
			payload.put("status", Status);
			payload.put("work", "manualMovement");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(payload);
			oos.close();
			
			ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
			
			try
			{
			    myrespObj=  (Boolean) in.readObject();
				   
			   
			} catch (ClassNotFoundException e1)
			{
			    e1.printStackTrace();
			}
			finally
			{
				in.close();
			}
			
			
			
			
		//	System.out.println("File uploaded. Response Code is "
		//			+ hc.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myrespObj;
	}
	
	public EtokenApplet(String detail) {
		qu = new ListQueue();
		checkObject = getObject();
		if(!checkObject)
			System.out.println("The new object was created ");
		else
			System.out.println("The Old object was picked up");	
		String details[] = detail.split(";");
	
		setBounds(100, 100, 511, 513);
		//setTitle("ETOKEN v1");
		
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		//System.out.println(mo);
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			System.exit(0);
			}
		});
		mnMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JPanel panel_main = new JPanel();
		panel_main.setBounds(10, 11, 475, 429);
		panel_main.setLayout(null);
		panel_main.setBackground(Color.WHITE);
		contentPane.add(panel_main);
		
		JPanel reg_panel = new JPanel();
		reg_panel.setBounds(32, 58, 420, 93);
		reg_panel.setLayout(null);
		
		panel_main.add(reg_panel);
		
		 btnSearch= new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String reg_Id = textField.getText();
				if ( reg_Id == null || reg_Id.trim().equals( "" ) )
				{
					if(lblL.getText().equalsIgnoreCase("en"))
					JOptionPane.showMessageDialog(null, "Enter text in text field");  
					else
						JOptionPane.showMessageDialog(null, "टेक्स्ट क्षेत्र में टेक्स्ट दर्ज करें");  	
				}
				
				else
				{
					char test[] = textField.getText().toCharArray();
					boolean check1 = true;
					if ( textField.getText() == null || textField.getText().equals( "" ) )
					{
						JOptionPane.showMessageDialog(null, "Enter text in No of Persons textfield"); 
						check1 = false;
					}
					else
					{
						check1 = true;
					}
					
					int count =0;
					for(int i=0;i<test.length;i++)
					{
						
						for(int j=48;j<58;j++)
						{
							if(test[i]==j)
							{
								count++;
								break;
							}
							
						}
						
							
						
					}
					
					if(check1)
					{
					if(count==test.length)
					{
						if(test.length==12)
						{
				   sendToServlet(reg_Id);
						}
						else
						{
							if(lblL.getText().equalsIgnoreCase("en"))
							JOptionPane.showMessageDialog(null, "The Registration Initiation Id must be of 12 Digits");  
							else
								JOptionPane.showMessageDialog(null, "पंजीकरण शुरूआत आईडी 12 अंकों की होनी चाहिए");  
						}
						}
				   else
					{
					   if(lblL.getText().equalsIgnoreCase("en"))
						JOptionPane.showMessageDialog(null, "Enter Digits only in text field");  
					   else
						   JOptionPane.showMessageDialog(null, "टेक्स्ट क्षेत्र में केवल अंक दर्ज");  
					}
				}
				}
		//		BO obj = new BO();
				
					
				}
			
		});
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 365, 420, 51);
		panel.setLayout(null);
		panel_main.add(panel);
		
		lblL = new JLabel("l");
		lblL.setBounds(320, 15, 46, 14);
		lblL.setVisible(false);
		panel.add(lblL);
		
		System.out.println("Label En value "+lblL.getText());
		btnSearch.setBounds(325, 59, 85, 23);
		reg_panel.add(btnSearch);
		
		textField = new JTextField();
	
	
		textField.setBounds(195, 60, 120, 20);
		reg_panel.add(textField);
		textField.setColumns(10);
		
		 lblNewLabel = new JLabel("*Registration Initiation ID");
		lblNewLabel.setBounds(24, 63, 151, 14);
		reg_panel.add(lblNewLabel);
		
		 lblSrOffice = new JLabel("SR Office");
		lblSrOffice.setBounds(27, 38, 133, 14);
		reg_panel.add(lblSrOffice);
		
		 lblDistrict = new JLabel("District");
		lblDistrict.setBounds(27, 11, 57, 14);
		reg_panel.add(lblDistrict);
		
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(185, 11, 167, 14);
		lblNewLabel_1.setText(paramFileName);
		reg_panel.add(lblNewLabel_1);
		
		name_label = new JLabel("");
		name_label.setBounds(185, 38, 225, 14);
		name_label.setText(paramFileName);
		reg_panel.add(name_label);
	
	
		//String a[] = new String[5];
		 panel_Content = new JPanel();
		panel_Content.setBounds(32, 162, 420, 192);
		panel_Content.setLayout(null);
		panel_main.add(panel_Content);
		
		lblSlotDate = new JLabel("Slot Date");
		lblSlotDate.setBounds(23, 28, 157, 20);
		//lblSlotDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Content.add(lblSlotDate);
		
		 lblNumberOfPersons = new JLabel("Number Of Persons Allowed");
		lblNumberOfPersons.setBounds(23, 89, 186, 20);
	//	lblNumberOfPersons.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Content.add(lblNumberOfPersons);
		
		 lblCategory = new JLabel("Category");
		lblCategory.setBounds(23, 120, 186, 20);
		//lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Content.add(lblCategory);
		
		lblApplicaionType	 = new JLabel("Application Type");
		lblApplicaionType.setBounds(23, 151, 191, 20);
		//lblApplicaionType.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Content.add(lblApplicaionType);
		
		 lblSlotdatevalue = new JLabel("");
		lblSlotdatevalue.setBounds(219, 28, 170, 14);
		panel_Content.add(lblSlotdatevalue);
		
		personallowedtextField = new JTextField();
		personallowedtextField.setToolTipText("Enter The number of Persons Allowed");
		personallowedtextField.setBounds(219, 92, 114, 20);
		panel_Content.add(personallowedtextField);
		personallowedtextField.setColumns(10);
		
		
		 rdbtnSpecial = new JRadioButton("Special");
		rdbtnSpecial.setBounds(221, 119, 78, 23);
		panel_Content.add(rdbtnSpecial);
		
		 rdbtnNormal = new JRadioButton("Normal");
		rdbtnNormal.setBounds(301, 119, 88, 23);
		panel_Content.add(rdbtnNormal);
		
		 bg = new ButtonGroup();
		bg.add(rdbtnSpecial);
		bg.add(rdbtnNormal);
	//	bg.setSelected((ButtonModel) rdbtnNormal,true);
		
		lblApplicationtypevalue = new JLabel("");
		lblApplicationtypevalue.setBounds(224, 151, 114, 20);
		panel_Content.add(lblApplicationtypevalue);
		
		 lblApplicationtype = new JLabel("");
		lblApplicationtype.setBounds(224, 157, 151, 14);
		panel_Content.add(lblApplicationtype);
		
		 lblSlotTime = new JLabel("Slot Time ");
		lblSlotTime.setBounds(23, 64, 78, 14);
		panel_Content.add(lblSlotTime);
		
	 lblSlottime = new JLabel("");
		lblSlottime.setBounds(219, 53, 191, 20);
		panel_Content.add(lblSlottime);
		
		
		
		
		JButton btnNewButton = new JButton("New button");
		panel.add(btnNewButton);
		
		
	
		
		 btnGenerateEtoken = new JButton("Generate Etoken");
		btnGenerateEtoken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		//	BO obj = new BO();
			boolean boo = false;
			String regId = textField.getText();
			String noOfPersons = personallowedtextField.getText();
			String applicationType = dto.getAppStatus();
			
			System.out.println("dto.getAppStatus();"+dto.getAppStatus());
			String slotDate = lblSlotDate.getText();
			String slottime = lblSlottime.getText();
			
			char c[] = noOfPersons.toCharArray();
			int count =0;
			boolean check = false;
			for(int i=0;i<c.length;i++)
			{
				
				for(int j=48;j<58;j++)
				{
					if(c[i]==j)
					{
						count++;
						break;
					}
					
				}
				
					
				
			}
			
			if(count==c.length && c.length>0){
			
			if("10".equalsIgnoreCase(applicationType))
			{
				
				applicationType = "For Maker";
				
			}
			else if ("11".equalsIgnoreCase(applicationType))
			{
				
				applicationType = "On Maker-Hold";
			
			}
			else if ("13".equalsIgnoreCase(applicationType))
			{
				
				applicationType = "For Checker";
				
				
			}
			else if ("14".equalsIgnoreCase(applicationType))
			{
				
				applicationType = "On Checker-Hold";
				
			}
			String category = "";
			rdbtnNormal.setSelected(true);
				if(rdbtnNormal.isSelected())
				{
					category = "Normal";
				}
				else
				{
					category = "Special";
				}
			//	System.out.println(category);
			boolean check1 = 	checkExist(regId,applicationType);
		
				if(!check1)
				{
					if(lblL.getText().equalsIgnoreCase("en"))
					lblError.setText("Etoken For this Reg Id has already been generated for this Registration Id.");
					else
					lblError.setText("यह पंजीकरण आईडी के लिए टोकन पहले से ही उत्पन्न हो चुका है");	
					
				}
				
				else
				{
					dto.setRegistrationId(regId);
					dto.setTypeOfPerson(category);
					dto.setAppStatus(applicationType);
					dto.setNoOfPersons(noOfPersons);
					dto.setOfficeId(lblOfficeId.getText());
					dto.setSlotDate(lblSlotdatevalue.getText());
				
					lblError.setText("");
					//Code written to print etoken 
					boo =  printEtoken();
					
					if(boo)
					{
						if(applicationType.equalsIgnoreCase("For Maker") || applicationType.equalsIgnoreCase("On Maker-Hold"))
						{
							if(category.equalsIgnoreCase("Special"))
							{
								synchronized (qu) {
									dto.setUrls(urls);
									qu.getMakerCheckerS().add(dto);
								}
							}
							else
							{
								synchronized (qu) {
									dto.setUrls(urls);
									qu.getMakerCheckerN().add(dto);
								}
							}
						}
						else
						{
							if(applicationType.equalsIgnoreCase("For Checker") || applicationType.equalsIgnoreCase("On Checker-Hold"))
						//	if("On Checker-Hold".equalsIgnoreCase(applicationType))
							{
								String counterNo = getMakerDetails(regId);
								dto.setWaitCounterMaker(counterNo);
							}
							if(category.equalsIgnoreCase("Special"))
							{
								synchronized (qu) {
									dto.setUrls(urls);
									qu.getMakerCheckerS().add(dto);
								}
							}
							else
							{
								synchronized (qu) {
									dto.setUrls(urls);
									qu.getMakerCheckerN().add(dto);
								}
							}
							
						}
						synchronized (qu) {
				//Remove content from the makerFinal
				qu.getMakerFinal().removeAllElements();
				//Add the latest List 
				qu.getMakerFinal().addAll(qu.getMakseS());
				System.out.println("qu.getMakseS() in synchronize"+qu.getMakseS());
				qu.getMakerFinal().addAll(qu.getMakerN());
				System.out.println("qu.getMakerN() in Makers"+qu.getMakerN());
				
				//Remove content from the checkerFinal
				qu.getCheckerFinal().removeAllElements();
				//Add the latest List 
				qu.getCheckerFinal().addAll(qu.getCheckerS());
				System.out.println("qu.getCheckerS() in synchronize"+qu.getCheckerS());
				qu.getCheckerFinal().addAll(qu.getCheckerN());
				System.out.println("qu.getMakerN() in Makers"+qu.getCheckerN());
				qu.setFlag(true);
				saveObject(qu);//Save Object
				}
				dto = null;
					
				if(lblL.getText().equalsIgnoreCase("en"))
					JOptionPane.showMessageDialog(null, "E-Token has been printed");
				else
					JOptionPane.showMessageDialog(null, "ई-टोकन मुद्रित किया गया है");
					
					lblApplicationtypevalue.setText("");
					lblSlottime.setText("");
					lblSlotdatevalue.setText("");
					
					textField.setText("");
					personallowedtextField.setText("");
					bg.clearSelection();
					lblApplicationtypevalue.setText("");
					}
					
					
					else
					{
						lblApplicationtypevalue.setText("");
						lblSlottime.setText("");
						lblSlotdatevalue.setText("");
						
						textField.setText("");
						personallowedtextField.setText("");
						bg.clearSelection();
						lblApplicationtypevalue.setText("");
					}
			}
			}
			
			else
			{
				if(lblL.getText().equalsIgnoreCase("en"))
				JOptionPane.showMessageDialog(null, "Number of persons must be in Digits");
				else
					JOptionPane.showMessageDialog(null, "व्यक्तियों की संख्या अंकों में होना चाहिए");	

			}
		}});
		btnGenerateEtoken.setBounds(80, 11, 130, 23);
		panel.add(btnGenerateEtoken);
		
		btnReissueEtoken = new JButton("Re Issue Token");
		btnReissueEtoken.setBounds(220,11,130,23);
		btnReissueEtoken.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				lblError.setText("");
				String reg_Id = textField.getText();
				if ( reg_Id == null || reg_Id.trim().equals( "" ) )
				{
					if(lblL.getText().equalsIgnoreCase("en"))
					JOptionPane.showMessageDialog(null, "Enter text in registration initiation text field");  
					else
						JOptionPane.showMessageDialog(null, "पंजीकरण शुरूआत आईडी टेक्स्ट क्षेत्र में टेक्स्ट दर्ज करें");  	
				}
				
				else
				{
					char test[] = textField.getText().toCharArray();
					boolean check1 = true;
					
					
					int count =0;
					for(int i=0;i<test.length;i++)
					{
						
						for(int j=48;j<58;j++)
						{
							if(test[i]==j)
							{
								count++;
								break;
							}
							
						}
						
							
						
					}
					
					if(check1)
					{
					if(count==test.length)
					{
						if(test.length==12)
						{
							ETokenDTO dto = checkExistEtoken(reg_Id);
							
							if(dto!=null && !"BBB".equalsIgnoreCase(dto.getAssigned()))
							{
								reissueEtoken(dto);
								
								
								if(lblL.getText().equalsIgnoreCase("en"))
									JOptionPane.showMessageDialog(null, "Token has been Reissued");  
								
									else
										JOptionPane.showMessageDialog(null, "ई टोकन फिर से जारी किया गया है"); 
								
										lblApplicationtypevalue.setText("");
										lblSlottime.setText("");
										lblSlotdatevalue.setText("");
										
										textField.setText("");
										personallowedtextField.setText("");
										bg.clearSelection();
										lblApplicationtypevalue.setText("");
							}
							else
							{
								if(lblL.getText().equalsIgnoreCase("en"))
								lblError.setText("Cannot Re-Issue E-Token .Generate E-Token first");
								else
									lblError.setText("पुनर्निर्गम नहीं कर सकते ..ई टोकन पहले उत्पन्न करे.");	
							}
						}
						else
						{
							if(lblL.getText().equalsIgnoreCase("en"))
							JOptionPane.showMessageDialog(null, "The Registration Initiation Id must be of 12 Digits");  
							else
								JOptionPane.showMessageDialog(null, "पंजीकरण शुरूआत आईडी 12 अंकों की होनी चाहिए");  
						}
						}
				   else
					{
					   if(lblL.getText().equalsIgnoreCase("en"))
						JOptionPane.showMessageDialog(null, "Enter Digits only in text field");  
					   else
						   JOptionPane.showMessageDialog(null, "टेक्स्ट क्षेत्र में केवल अंक दर्ज");  
					}
				}
				}
		//		BO obj = new BO();
				
					
				}
				
			
				
				
				
				
		});
		
		panel.add(btnReissueEtoken);
		
		lblOfficeId = new JLabel("o");
		lblOfficeId.setBounds(29, 15, 46, 14);
		lblOfficeId.setVisible(false);
		panel.add(lblOfficeId);
		
		
		
		JPanel errorPanel = new JPanel();
		errorPanel.setBounds(32, 11, 420, 36);
		panel_main.add(errorPanel);
		
		 lblError = new JLabel("");
		 lblError.setForeground(Color.RED);
		errorPanel.add(lblError);
		
		System.out.println("qu"+qu);
		ThreadListener lists = new ThreadListener(qu);
		Thread t = new Thread(lists);
		t.start();
		ProcessorThread thread = new ProcessorThread(qu);
		Thread l = new Thread(thread);
		l.start();
		
		GraphicsEnvironment ge = GraphicsEnvironment.
		   getLocalGraphicsEnvironment();
		   GraphicsDevice[] gs = ge.getScreenDevices();
		
		  String[] size = getSizeFromFile().split(";");
		  int [] sizeI = new int[4];
		  for(int i=0;i<size.length;i++)
		  {
			 sizeI[i] =  Integer.parseInt(size[i]);
			 System.out.println("size"+sizeI[i]);
		  }
		  
		Display d =   new Display(qu, sizeI[0], sizeI[1], sizeI[2], sizeI[3]);
		d.setUndecorated(true);
		d.setResizable(true);
		d.setSize(sizeI[0],sizeI[1]);
		d.setVisible(true);
		/*
		manualCombo = new JComboBox();
		manualCombo.addItem("Move To Checker");
		manualCombo.addItem("Clear Checker");
		manualCombo.setBounds(120,11,130,23);
	panel_main.add(manualCombo);*/
	JLabel la = new JLabel("Manual Override Movement");
	la.setBounds(196, 450, 180, 20);
	contentPane.add(la);
	 comboBox = new JComboBox();
	comboBox.setToolTipText("Select The Manual Override Type.");
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"Maker To Checker", "Checker Empty","Hold At Maker","Hold At Checker"}));
	comboBox.setBounds(20, 471, 135, 20);
	contentPane.add(comboBox);
	
	
	 comboBox1 = new JComboBox();
		comboBox1.setToolTipText("Select The Person Type");
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Special", "Normal"}));
		comboBox1.setBounds(165, 471, 100, 20);
		contentPane.add(comboBox1);
	
	JButton btnNewButton_1 = new JButton("Move");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			String RegId = textField_1.getText();
			if(RegId.equalsIgnoreCase(""))
			{
				if(lblL.getText().equalsIgnoreCase("en"))
					JOptionPane.showMessageDialog(null, "Enter text in text field");  
					else
						JOptionPane.showMessageDialog(null, "टेक्स्ट क्षेत्र में टेक्स्ट दर्ज करें");  	
			}
			else if(!(RegId.length()==12))
			{
				if(lblL.getText().equalsIgnoreCase("en"))
					JOptionPane.showMessageDialog(null, "The Registration Initiation Id must be of 12 Digits");  
					else
						JOptionPane.showMessageDialog(null, "पंजीकरण शुरूआत आईडी 12 अंकों की होनी चाहिए");  
				
			}
			else
			{
			   int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to move?","Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				
				
				String type = comboBox1.getSelectedItem().toString();
				String moveType = comboBox.getSelectedItem().toString();
			//	System.out.println("Reg Id : "+RegId);
			//	System.out.println("MoveType : "+RegId);
				String status = "";
				
				if(moveType.equalsIgnoreCase("Maker To Checker"))
				{
					status = "13";
				}
				else if(moveType.equalsIgnoreCase("Hold At Maker"))
				{
				
					status = "11";
				
				}
				else if(moveType.equalsIgnoreCase("Checker Empty"))
				{
				
					status = "17";
				
				}
				else if(moveType.equalsIgnoreCase("Hold At Checker"))
				{
				
					status = "14";
				
				}
				boolean boo = sendToServletMove(RegId,status);
				if(boo)
				{
				if(moveType.equalsIgnoreCase("Hold At Maker"))
				{
				if(type.equalsIgnoreCase("Special"))
				{
					synchronized (qu) {
						Vector<ETokenDTO> msdto = qu.getMakseS();
						Vector<ETokenDTO> makerCounter = qu.getMakerCounter();
						for(int i=0;i<msdto.size();i++)
						{
							System.out.println("Before Removing Size MS : "+msdto.size());
							ETokenDTO d = msdto.get(i);
							if(d.getRegistrationId().equalsIgnoreCase(RegId))
							{
								String counter = d.getCounterNo();
								for(int f =0;f<makerCounter.size();f++)
								{
									ETokenDTO dtoC = makerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										dtoC.setAssigned("N");
									}
												
									
								}
								msdto.remove(i);
								System.out.println("After Removing Size MS: "+msdto.size());
							
								break;
							}
							
						}
						qu.setFlag(true);
					//	saveObject(objQu);
					}
					
					
					
					
					
				}
				
				
				else
				{
					synchronized (qu) {
					Vector<ETokenDTO> mndto = qu.getMakerN();	
					Vector<ETokenDTO> makerCounter = qu.getMakerCounter();
					for(int i=0;i<mndto.size();i++)
					{
						System.out.println("Before Removing Size MN: "+mndto.size());
						ETokenDTO d = mndto.get(i);
						if(d.getRegistrationId().equalsIgnoreCase(RegId))
						{
							
							String counter = d.getCounterNo();
							for(int f =0;f<makerCounter.size();f++)
							{
								ETokenDTO dtoC = makerCounter.get(f);
								if(dtoC.getCounterNo().equalsIgnoreCase(counter))
								{
									dtoC.setAssigned("N");
								}
											
								
							}
							
							mndto.remove(i);
							System.out.println("After Removing Size MN: "+mndto.size());
							
							break;
						}
						
					}
					qu.setFlag(true);
				//	saveObject(objQu);
					}
				}
				
		
			
			
			
			
			
			
			
			
			
			
			
				}
				else if(moveType.equalsIgnoreCase("Maker To Checker"))
				{
					if(type.equalsIgnoreCase("Special"))
					{
						synchronized (qu) {
							Vector<ETokenDTO> msdto = qu.getMakseS();
							Vector<ETokenDTO> csdto = qu.getCheckerS();
							Vector<ETokenDTO> makerCounter = qu.getMakerCounter();
							for(int i=0;i<msdto.size();i++)
							{
								System.out.println("Before Removing Size MS : "+msdto.size());
								ETokenDTO d = msdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(RegId))
								{
									String counter = d.getCounterNo();
									for(int f =0;f<makerCounter.size();f++)
									{
										ETokenDTO dtoC = makerCounter.get(f);
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											dtoC.setAssigned("N");
										}
													
									}
									d.setAppStatus("13");
									d.setCounterNo(""); 
									csdto.add(d);
									msdto.remove(i);
									System.out.println("After Removing Size MS: "+msdto.size());
									System.out.println("After Adding Size CS: "+csdto.size());
								
									break;
								}
								
							}
							qu.setFlag(true);
					//		saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (qu) {
						Vector<ETokenDTO> mndto = qu.getMakerN();	
						Vector<ETokenDTO> cndto = qu.getCheckerN();	
						Vector<ETokenDTO> makerCounter = qu.getMakerCounter();
						for(int i=0;i<mndto.size();i++)
						{
							System.out.println("Before Removing Size MN : "+mndto.size());
							ETokenDTO d = mndto.get(i);
							if(d.getRegistrationId().equalsIgnoreCase(RegId))
							{
								String counter = d.getCounterNo();
								for(int f =0;f<makerCounter.size();f++)
								{
									ETokenDTO dtoC = makerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										dtoC.setAssigned("N");
									}
												
								}
								
								d.setAppStatus("13");
								d.setCounterNo(""); 
								cndto.add(d);
								
								mndto.remove(i);
								System.out.println("After Removing Size MN : "+mndto.size());
								System.out.println("After Addng Size CN : "+cndto.size());
								break;
							}
							
						}
						qu.setFlag(true);
					//	saveObject(objQu);
						}
					}
					
					
					
					
					
					
					
					
				}
				else if(moveType.equalsIgnoreCase("Checker Empty") || moveType.equalsIgnoreCase("Hold At Checker"))
				{
					
					if(type.equalsIgnoreCase("Special"))
					{
						synchronized (qu) {
							
							Vector<ETokenDTO> csdto = qu.getCheckerS();
							Vector<ETokenDTO> checkerCounter = qu.getCheckerCounter();
							System.out.println("Before Removing Size CS : "+csdto.size());
							for(int i=0;i<csdto.size();i++)
							{
								ETokenDTO d = csdto.get(i);
								if(d.getRegistrationId().equalsIgnoreCase(RegId))
								{
									String counter = d.getCounterNo();
									for(int f =0;f<checkerCounter.size();f++)
									{
										ETokenDTO dtoC = checkerCounter.get(f);
										if(dtoC.getCounterNo().equalsIgnoreCase(counter))
										{
											dtoC.setAssigned("N");
										}
													
									}
									
									csdto.remove(i);
									System.out.println("After Removing Size CS : "+csdto.size());
									break;
								}
								
							}
							qu.setFlag(true);
							//saveObject(objQu);
						}
						
					}
					else
					{
						synchronized (qu) {
					
						Vector<ETokenDTO> cndto = qu.getCheckerN();
						Vector<ETokenDTO> checkerCounter = qu.getCheckerCounter();
						for(int i=0;i<cndto.size();i++)
						{
							System.out.println("Before Removing Size CN : "+cndto.size());
							ETokenDTO d = cndto.get(i);
							
							if(d.getRegistrationId().equalsIgnoreCase(RegId))
							{
								String counter = d.getCounterNo();
								for(int f =0;f<checkerCounter.size();f++)
								{
									ETokenDTO dtoC = checkerCounter.get(f);
									if(dtoC.getCounterNo().equalsIgnoreCase(counter))
									{
										dtoC.setAssigned("N");
									}
												
								}
								System.out.println("After Removing Size CN: "+cndto.size());
								cndto.remove(i);
								break;
							}
							
						}
						qu.setFlag(true);
					//	saveObject(objQu);
						
						}
					}
					
					
					
					
					
				}	
				JOptionPane.showMessageDialog(null, "Succesfully Moved");  
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Insertion into Database Failed");  
				}
		}
		}
		}
	});
	btnNewButton_1.setToolTipText("Move");
	btnNewButton_1.setBounds(416, 471, 89, 23);
	contentPane.add(btnNewButton_1);
	
	textField_1 = new JTextField();
	textField_1.setToolTipText("Enter The Registration Initiation Id");
	textField_1.setBounds(277, 471, 131, 20);
	contentPane.add(textField_1);
	textField_1.setColumns(10);
		
		TimeManagerClass times = new TimeManagerClass(qu);
		times.setVisible(false);
		
	}
	protected String getMakerDetails(String regId) {
		String counterNo = "";
		try{
			String getCodeBase = this.getCodeBase().toString();
			URL url = new URL(getCodeBase + "/ETokenServlet");
			urls = url;
			System.out.println("URL is " +urls);
		//	System.out.println("url is>>>" + url);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type", "application/binary");
			HttpURLConnection hc = (HttpURLConnection) (connection);
			//System.out.println(hc);
			hc.setRequestMethod("POST");
			OutputStream os = hc.getOutputStream();
			InetAddress	ip = InetAddress.getLocalHost();
			HashMap<String, Object> payload = new HashMap<String, Object>();
		//	System.out.println(ip.getHostAddress());
			payload.put("work", "CollectorCounter");
			payload.put("ofc", lblOfficeId.getText());
			payload.put("regId", regId);
			//payload.put("applicationType", applicationType);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(payload);
			oos.close();
			
			ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
			counterNo = (String) in.readObject();
			//System.out.println(boo);
			
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return counterNo;
		
	}
	private String getSizeFromFile() {
		
		BufferedReader br = null;
		String size="";
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("D:\\Data\\Size.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				size = size+sCurrentLine+";";
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return size;
		
		
	}
	private boolean checkExist(String regId, String applicationType) {
		try{
		String getCodeBase = this.getCodeBase().toString();
		URL url = new URL(getCodeBase + "/ETokenServlet");
		urls = url;
		System.out.println("URL is " +urls);
	//	System.out.println("url is>>>" + url);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/binary");
		HttpURLConnection hc = (HttpURLConnection) (connection);
		//System.out.println(hc);
		hc.setRequestMethod("POST");
		OutputStream os = hc.getOutputStream();
		InetAddress	ip = InetAddress.getLocalHost();
		HashMap<String, Object> payload = new HashMap<String, Object>();
	//	System.out.println(ip.getHostAddress());
		payload.put("work", "checkExists");
		payload.put("ofc", dto.getRegistrationId());
		payload.put("regId", regId);
		payload.put("applicationType", applicationType);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(payload);
		oos.close();
		
		ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
		String boo = (String) in.readObject();
		//System.out.println(boo);
		if(boo.equalsIgnoreCase("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	
	}
	
	public  ETokenDTO checkExistEtoken(String regId) {
		ETokenDTO dto = null;
		try{
		String getCodeBase = this.getCodeBase().toString();
		URL url = new URL(getCodeBase + "/ETokenServlet");
	//	System.out.println("url is>>>" + url);
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/binary");
		HttpURLConnection hc = (HttpURLConnection) (connection);
		//System.out.println(hc);
		hc.setRequestMethod("POST");
		OutputStream os = hc.getOutputStream();
		InetAddress	ip = InetAddress.getLocalHost();
		HashMap<String, Object> payload = new HashMap<String, Object>();
	//	System.out.println(ip.getHostAddress());
		payload.put("work", "checkExistsEtoken");
		payload.put("ofc", lblOfficeId.getText());
		payload.put("regId", regId);

		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(payload);
		oos.close();
		
		ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
		dto = (ETokenDTO) in.readObject();
	
	
		//System.out.println(boo);
		
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	
	}
	
	public void setParamFileName(String paramFileName) {
		this.paramFileName = paramFileName;
	}
	public String getParamFileName() {
		return paramFileName;
	}
	
	public void setMo(String mo) {

		lblOfficeId.setText(mo);
		
		
		
		this.mo = mo;
	}
	public String getMo() {
		return mo;
	}
	public void setDistrictName(String districtName) {
		lblNewLabel_1.setText(districtName);
		System.out.println(districtName);
		this.districtName = districtName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setOfficeName(String officeName) {
		name_label.setText(officeName);
		System.out.println(officeName);
		sendInitialData();
		this.officeName = officeName;
	}
	private boolean sendInitialData() {
		
		try
		{
		
		String getCodeBase = this.getCodeBase().toString();
		URL url = new URL(getCodeBase + "/ETokenServlet");
	
		URLConnection connection = url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Content-Type", "application/binary");
		HttpURLConnection hc = (HttpURLConnection) (connection);
	
		hc.setRequestMethod("POST");
		OutputStream os = hc.getOutputStream();
		InetAddress	ip = InetAddress.getLocalHost();
		HashMap<String, Object> payload = new HashMap<String, Object>();
//		System.out.println(ip.getHostAddress());
		payload.put("work", "initial");
		payload.put("ofc", lblOfficeId.getText());
		payload.put("ip", ip.getHostAddress());
		payload.put("language", lblL.getText());
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(payload);
		oos.close();
		
		ObjectInputStream in = new ObjectInputStream(hc.getInputStream());
		HashMap<String, Vector<ETokenDTO>> boo =   (HashMap<String, Vector<ETokenDTO>>) in.readObject();
//		System.out.println("In send initial Data ");
		synchronized (qu) {
		//Changes Object 
			System.out.println("Check Object : "+ checkObject);
			if(!checkObject)
			{
			System.out.println("Size is : "+boo.get("Checker").size());
			qu.setCheckerCounter(boo.get("Checker"));
			qu.setCheckerWaitTime(qu.getCheckerCounter().get(0).getWaitTime());
			System.out.println("Size is : "+qu.getCheckerCounter().size());
			qu.setMakerCounter(boo.get("Maker"));
			qu.setMakerWaitTime(qu.getMakerCounter().get(0).getWaitTime());
		saveObject(qu);//Save Object
			}
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
		
		
	}
	
	//Persist Objects
	public synchronized boolean saveObject(ListQueue obj)
	{
		FileInputStream in =null;
		ObjectInputStream readers = null;
		boolean check = false;
		try {
			File yourFile = new File("D:\\Data\\obj.sav");
			if(!yourFile.exists()) {
				System.out.println("File Doesnot");
			    yourFile.createNewFile();
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
			    qu = new ListQueue();
			    out.writeObject(obj);
			    out.close();
			    check = true;

			} 
			else
			{
				
				if(yourFile.delete())
				{
					System.out.println("inside file exist");
					yourFile.createNewFile();
					 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\Data\\obj.sav"));
					 out.writeObject(obj);
					    out.close();
					    check = true;
				}

			}
			
		} catch (FileNotFoundException e) {
			check = false;
			e.printStackTrace();
		} catch (IOException e) {
			check = false;
			e.printStackTrace();
		}
		/*} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return check;
		
		
	}
		
		
		
		
	
	
	
	public String getOfficeName() {
		
		return officeName;
	}
	public void setLanguage(String language) {
		lblL.setText(language);
		synchronized(qu)
		{
			qu.setLanguage(language);
		}
		if(lblL.getText().equalsIgnoreCase("hi"))
		{
			Font f = new Font("serif", Font.PLAIN, 15);
			lblDistrict.setText("जिला");
		//	lblDistrict.setFont(f);
			btnGenerateEtoken.setText("टोकन उत्पन्न करें");
			lblSrOffice.setText("एस.आर. कार्यालय");
			lblNewLabel.setText("पंजीकरण शुरूआत की आईडी");
			lblSlotDate.setText("स्लॉट की तिथि");
			lblSlotTime.setText("स्लॉट का समय");
			lblNumberOfPersons.setText("कितने लोगों को अनुमति दी जाती है ");
			lblCategory.setText("श्रेणी");
			lblApplicaionType.setText("आवेदन का प्रकार");
			btnSearch.setText("खोजें");
			rdbtnSpecial.setText("विशेष");
			rdbtnNormal.setText("साधारण");
			
		}
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
}

package com.wipro.igrs.device.applet;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;

import com.wipro.igrs.eToken.form.ETokenForm;



public class printnow {

	public static void main(String[] args) {
		printnow n = new printnow();
	//	n.printCards("111", "1221", "1", "Special", "Sub Registrar Office -1 Bhopal", "Time");
		//   SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
	      //  String formattedDate = dateFormat.format(new Date()).toString().substring(0, 11);
		//System.out.println(formattedDate);
		Image img = new ImageIcon("D:\\mybarcode.jpg").getImage();
		String s = null;
		System.err.println(s.toString());
		n.printCards("1", "121212", "1", "Special", "sadadasdasd", "123213213", img);
	}
	
	  public static boolean printCards(final String etokenNo,final String regID,final String noOfPersons,final String  catagory, final String office_Name,final String slot_time, final Image img){
			final PrinterJob job = PrinterJob.getPrinterJob();
			Printable contentToPrint =null;
		try{
			 contentToPrint = new Printable(){
			   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {


			       Graphics2D g2d = (Graphics2D) graphics;

			    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			   g2d.setFont(new Font("Times New Roman", Font.BOLD, 8));

			   

				
				
			       if (pageIndex >0){return NO_SUCH_PAGE;} //Only one page

			       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
			        String formattedDate = dateFormat.format(new Date()).toString();
			      

			      int y = 0;
			  
			    g2d.drawString("******************************************************", 0, 15);
		        g2d.drawString("ई-टोकन  संख्या: "+etokenNo, 70, 25);
		        g2d.drawString("******************************************************", 0, 35);
		      
		        g2d.drawString(office_Name,50, 50);
		        g2d.drawString("टोकन उत्पन्न का समय  ", 10, 65);
		        g2d.drawString(formattedDate, 100, 65);
		        g2d.drawString("पंजीकरण आईडी  ", 10, 80);
		        g2d.drawString(regID, 100, 80);
		        g2d.drawString("व्यक्तियों की संख्या    ", 10, 95);
		        g2d.drawString(String.valueOf(noOfPersons), 100, 95);
		        g2d.drawString("श्रेणी     ", 10, 110);
		        if(catagory.equalsIgnoreCase("Special"))
		        g2d.drawString("विशेष",100, 110 );
		        else
		        	 g2d.drawString("साधारण",100, 110 );	
		        g2d.drawString("स्लॉट की तिथि    ",10,125);
		        g2d.drawString(slot_time,100,125 );
		        
		        g2d.drawString("******************************************************", 0, 140);
		        java.io.File file = new java.io.File("D:\\mybarcode.jpg");
		        
		      
			//img =   img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			  
			   g2d.drawImage(img, 35, 145,130,40, null);
			   g2d.drawString("******************************************************", 0, 195);
			   
			   g2d.drawString("रजिस्ट्रेशन एवं स्टाम्प विभाग ( वाणिज्यिक कर ),  ", 39, 210);
			   g2d.drawString(" © मध्य प्रदेश शासन ", 59, 225);
			   g2d.drawString("******************************************************", 0, 2240);
			    return PAGE_EXISTS;

			   }


			}; 
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

			        PageFormat pageFormat = new PageFormat();
			    pageFormat.setOrientation(PageFormat.PORTRAIT);
			      Paper pPaper = pageFormat.getPaper();



			   pPaper.setImageableArea(0, 0, pPaper.getWidth() , pPaper.getHeight() -2);
			   pageFormat.setPaper(pPaper);

			   job.setPrintable(contentToPrint, pageFormat);





			    try {
			      job.print();
			      return true;
			  } catch (PrinterException e) {
			    System.err.println(e.getMessage());
			  }
				return true;
			    }
	

	  public static boolean printCard(final ETokenForm eForm,final String  catagory, final String office_Name){
	final PrinterJob job = PrinterJob.getPrinterJob();
	Printable contentToPrint =null;
try{
	 contentToPrint = new Printable(){
	   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {


	       Graphics2D g2d = (Graphics2D) graphics;

	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    g2d.setFont(new Font("Times New Roman", Font.BOLD, 8));

	   

		
		
	       if (pageIndex >0){return NO_SUCH_PAGE;} //Only one page

	       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
	        String formattedDate = dateFormat.format(new Date()).toString();
	      

	      int y = 0;
	  
	    g2d.drawString("******************************************************", 0, 15);
        g2d.drawString("ई-टोकन  संख्या: "+eForm.getEtokenDTO().getEtokenNumberC(), 60, 25);
        g2d.drawString("******************************************************", 0, 35);
      
        g2d.drawString(office_Name,55, 50);
        g2d.drawString("Etoken Generated Time  ", 10, 65);
        g2d.drawString(formattedDate, 100, 65);
        g2d.drawString("REGISTRATION ID  ", 10, 80);
        g2d.drawString(eForm.getEtokenDTO().getRegInitId(), 100, 80);
        g2d.drawString("NO OF PERSONS    ", 10, 95);
        g2d.drawString(String.valueOf(eForm.getEtokenDTO().getNoPersons()), 100, 95);
        g2d.drawString("CATAGORY     ", 10, 110);
        g2d.drawString(catagory,100, 110 );
        g2d.drawString("SLOT DATE    ",10,125);
        g2d.drawString(eForm.getEtokenDTO().getSlotDate().substring(0, 11),100,125 );
        
        g2d.drawString("******************************************************", 0, 140);
        Image img = new ImageIcon("D:\\mybarcode.jpg").getImage();
	//img =   img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	  
	   g2d.drawImage(img, 20, 145,130,40, null);
	   g2d.drawString("******************************************************", 0, 195);
	   
	   g2d.drawString("Inspector Genral of Registration Stamps  ", 50, 210);
	   g2d.drawString(" © MADHYA PRADESH ", 70, 225);
	   g2d.drawString("******************************************************", 0, 2240);
	    return PAGE_EXISTS;

	   }


	}; 
}
catch (Exception e) {
	System.out.println(e.getMessage());
}

	        PageFormat pageFormat = new PageFormat();
	    pageFormat.setOrientation(PageFormat.PORTRAIT);
	      Paper pPaper = pageFormat.getPaper();



	   pPaper.setImageableArea(0, 0, pPaper.getWidth() , pPaper.getHeight() -2);
	   pageFormat.setPaper(pPaper);

	   job.setPrintable(contentToPrint, pageFormat);





	    try {
	      job.print();
	      return true;
	  } catch (PrinterException e) {
	    System.err.println(e.getMessage());
	  }
		return false;
	    }
	    }
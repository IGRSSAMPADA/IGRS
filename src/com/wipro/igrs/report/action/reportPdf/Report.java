package com.wipro.igrs.report.action.reportPdf;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;
import com.wipro.igrs.report.form.MarketTrendReportForm;
import com.wipro.igrs.report.util.PropertiesFileReader;

public class Report {
	
	private Logger logger = (Logger) Logger
	.getLogger(Report.class);
	

	
	public synchronized File createCessMunicipalDutyReport(ArrayList param,String totalOrg ) {
		 Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		 String fileName = "";
		    try {
		      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		      Date date = new  Date();
		       
		      PropertiesFileReader pr = 
		    		PropertiesFileReader.
		    		 getInstance("com.wipro.igrs.igrs");
		      
		      fileName = pr.getValue("REPORT_LOCATION")
		             +"CessMunicipalDuty"+dateFormat.format(date)+".pdf";
		       
		      logger.debug("File is created :-"+fileName);
		      PdfWriter.getInstance(document, new FileOutputStream(fileName));
		      document.open();
		      
		      Table datatable = new Table(4);
		      int headerwidths[] = {5, 40, 10,
		                            10 };
		      datatable.setWidths(headerwidths);
		      datatable.setWidth(80);
		      datatable.setPadding(3);
		      Phrase firstheader = new Phrase(
		    		  "Cess, Block/Municipality/Municipal Corporation Duty", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD));
		      Cell cellfirstheader = new Cell(firstheader);
		      cellfirstheader.setHorizontalAlignment(Element.ALIGN_CENTER);
		      cellfirstheader.setLeading(30);
		      cellfirstheader.setColspan(4);
		      cellfirstheader.setBorder(Rectangle.NO_BORDER);
		      cellfirstheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(cellfirstheader);


		      Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      slno.setHeader(true);
		      slno.setRowspan(1);
		      slno.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(slno);

		      //datatable.addCell("Header 1");
		      Cell docType1 = new Cell(new Phrase("Name of the Organisation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType1.setHeader(true);
		      docType1.setRowspan(1);
		      docType1.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType1);
		      
		      Cell docType2 = new Cell(new Phrase("No of Document Registered During the Month", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType2.setHeader(true);
		      docType2.setRowspan(1);
		      docType2.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType2);
		       
		      Cell docType3 = new Cell(new Phrase("Revenue Receipt During the Month", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType3.setHeader(true);
		      docType3.setRowspan(1);
		      docType3.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType3);
		      
		     
		      

		      datatable.endHeaders();
		      datatable.setBorderWidth(1);
		      int row = 0;
		      if(param!=null) {
			      for(int i=0;i<param.size();i++){
			        datatable.setAlignment(Element.ALIGN_CENTER);
			        
			        MISReportDTO dto = (MISReportDTO) param.get(i);
			        row = i+1 ;
			        datatable.addCell(""+row);
			        datatable.addCell(dto.getNameOfOrg());
			        datatable.addCell(dto.getNoOfDocOrg());
			        datatable.addCell(dto.getRevenueReceiptOrg());
			      }
		      }
		      datatable.addCell("");
		      datatable.addCell("");
		      datatable.addCell("Total ");
		      datatable.addCell(totalOrg);
		      datatable.setCellsFitPage(true);
		      document.add(datatable);
		    } catch (Exception e) {
		    	logger.debug(e);
		    }
		    document.close();
		    return (new File(fileName));
	}
	public synchronized File createMarketTrendReport(ArrayList param, 
									String fromYear, String toYear) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		String fileName = "";
		int row = 0;
	    try {
	    	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		      Date date = new  Date();
		       
		      PropertiesFileReader pr = 
		    		PropertiesFileReader.
		    		 getInstance("com.wipro.igrs.igrs");
		      
		      fileName = pr.getValue("REPORT_LOCATION")
		             +"MarketTrend"+dateFormat.format(date)+".pdf";
		      PdfWriter.getInstance(document, new FileOutputStream(fileName));
		      document.open();
	
		     
		      

		      FileOutputStream out = new FileOutputStream(fileName);

		      PdfWriter.getInstance(document, out);
		      document.open();


		      Table datatable = new Table(8);
		      int headerwidths[] = {10, 24, 12,10, 24, 12,10,24};
		      datatable.setWidths(headerwidths);
		      datatable.setWidth(105);

		      datatable.setPadding(3);

		      Cell title = new Cell(new Phrase("Market Trend", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
		      title.setHorizontalAlignment(Element.ALIGN_CENTER);
		      title.setLeading(20);
		      title.setColspan(8);
		      title.setBorder(Rectangle.NO_BORDER);
		      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(title);
		      
		      //datatable.addCell("Header 2");
		      Cell yearType = new Cell(new Phrase("Years\n(Last X year - depending on "+fromYear+" and "+toYear+")", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      yearType.setHorizontalAlignment(Element.ALIGN_CENTER);
		      yearType.setHeader(true);
		      yearType.setColspan(8);
		      yearType.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(yearType);
		      
		       
		      Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      slno.setHeader(true);
		      slno.setRowspan(1);
		      slno.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(slno);

		      //datatable.addCell("Header 1");
		      Cell docType = new Cell(new Phrase("Document Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType.setHeader(true);
		      docType.setRowspan(1);
		      docType.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType);

 
		      //datatable.addCell("Header 5");
		      Cell noDocPrev = new Cell(new Phrase("No of Docs", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      noDocPrev.setHeader(true);
		      noDocPrev.setColspan(1);
		      noDocPrev.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(noDocPrev);

		      //datatable.addCell("Header 6");

		      Cell reveRecePrev = new Cell(new Phrase("Revenue Reciepts", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      reveRecePrev.setHeader(true);
		      reveRecePrev.setColspan(1);
		      reveRecePrev.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(reveRecePrev);
		      //datatable.addCell("Header 7");

		      Cell valPrev = new Cell(new Phrase("Valuation", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      valPrev.setHeader(true);
		      valPrev.setColspan(1);
		      valPrev.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(valPrev);

		      //datatable.addCell("Header 8");
		      Cell docPrevGro = new Cell(new Phrase("Comparative Growth in Documents from previous Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docPrevGro.setHeader(true);
		      docPrevGro.setColspan(1);
		      docPrevGro.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docPrevGro);

		      //datatable.addCell("Header 9");
		      Cell docRevGro = new Cell(new Phrase("Comparative Growth in Revenue from previous Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docRevGro.setHeader(true);
		      docRevGro.setColspan(1);
		      docRevGro.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docRevGro);

		      Cell docValGro = new Cell(new Phrase("Comparative Growth in Valuation from previous Year", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docValGro.setHeader(true);
		      docValGro.setColspan(1);
		      docValGro.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docValGro);

		      datatable.endHeaders();
		      datatable.setBorder(1);
		      
		      
		      if(param != null) {
			      for(int i = 0;i< param.size(); i++){
			        datatable.setAlignment(Element.ALIGN_LEFT);
			        
			        MarketTrendReportDTO dto = 
			        	(MarketTrendReportDTO)param.get(i);
			        
			        row = i + 1;
			        datatable.addCell(""+row);
			        datatable.addCell(dto.getDeedNameMT());
			        datatable.addCell(dto.getNoOfDocMT());
			        datatable.addCell(dto.getRevenueMT());
			        datatable.addCell(dto.getValuationMT());
			        datatable.addCell(dto.getNoOfDocPrevMT());
			        datatable.addCell(dto.getRevenuePrevMT());
			        datatable.addCell(dto.getValuationPrevMT());
	
			      }
		      }
		      
		      /*dataset = new DefaultPieDataset();
		      dataset.setValue("UNO", new Double(20.0));
		      dataset.setValue("DUE", new Double(10.0));
		      dataset.setValue("TRE", new Double(20.0));
		      dataset.setValue("QUATTRO", new Double(30.0));
		      dataset.setValue("CINQUE", new Double(20.0));*/
		/*  	  jfc = ChartFactory.createPieChart("Market Trend", dataset, true, true, false);
		      

		  	  PiePlot pp = (PiePlot) jfc.getPlot();
		  	  //pp.setSectionOutlinesVisible(false);
		  	  pp.setLabelFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 12));
		  	  pp.setNoDataMessage("Nessun Dato Inserito");

		  	  pp.setCircular(false);
		  	  pp.setLabelGap(0.02);
		  	  BufferedImage img=jfc.createBufferedImage(640,480);

		      
		      datatable.setCellsFitPage(true);
		      document.add(datatable);
		      Image jpg = Image.getInstance(img, new Color(0,0,255));
		      //document.add(jpg);
		      //PdfPTable pdfTable = datatable.createPdfPTable();
		      //pdfTable.setTotalWidth(400);*/
	    } catch (Exception e) {
	      logger.debug(e);
	    }
	    
	    document.close();
	    return (new File(fileName));
	}
	public synchronized File createSearchRequest(ArrayList param) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		String fileName = "";
	    try {
	    	 DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
			  Date date = new  Date();
			       
			  PropertiesFileReader pr = 
			   		PropertiesFileReader.
			    		 getInstance("com.wipro.igrs.igrs");
			      
	      fileName = pr.getValue("REPORT_LOCATION")
			             +"SearchRequest"+dateFormat.format(date)+".pdf";
	      PdfWriter.getInstance(document, new FileOutputStream(fileName));
	      document.open();

	      Table datatable = new Table(7);
	      int headerwidths[] = {12, 12, 12,24, 24, 24,12};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Registered Document Copy Request", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(7);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

//	      datatable.setDefaultCellBorderWidth(2);
//	      datatable.setDefaultHorizontalAlignment(1);
	      
	      Cell slno = new Cell(new Phrase("S.No.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setColspan(1);
	      slno.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(slno);
	     
	      Cell chkdate = new Cell(new Phrase("Date", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      chkdate.setHeader(true);
	      chkdate.setColspan(1);
	      chkdate.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(chkdate);
	      
	      //datatable.addCell("Header 6");
	      
	      Cell userId = new Cell(new Phrase("User Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      userId.setHeader(true);
	      userId.setColspan(1);
	      userId.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(userId);
	      //datatable.addCell("Header 7");
	      
	      
	      //datatable.addCell("Header 8");
	      Cell docDetails = new Cell(new Phrase("Document Details ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      docDetails.setHeader(true);
	      docDetails.setColspan(1);
	      docDetails.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(docDetails);
	      
	      Cell prop = new Cell(new Phrase("Property Owner Name ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      prop.setHeader(true);
	      prop.setColspan(1);
	      prop.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(prop);
	      
	      Cell count = new Cell(new Phrase("Count", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      count.setHeader(true);
	      count.setColspan(1);
	      count.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(count);
	      
	      Cell total = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD)));
	      total.setHeader(true);
	      total.setColspan(1);
	      total.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(total);
	      
	      
	      datatable.endHeaders();
//	      datatable.setDefaultCellBorderWidth(1);

	      int row = 0;
	      
	      if(param!=null) {
		      for(int i=0;i<param.size();i++){
		        datatable.setAlignment(Element.ALIGN_CENTER);
		        
		        MISReportDTO dto = (MISReportDTO) param.get(i);
		        row = i+1 ;
		        datatable.addCell(""+row);
		        datatable.addCell(dto.getUserDate());
		        datatable.addCell(dto.getUserName());
		        datatable.addCell(dto.getSearchDocumentDetails());
		        datatable.addCell(dto.getSearchPropertyOwnerName());
		        datatable.addCell(dto.getSearchCount());
		        datatable.addCell(dto.getSearchTotal());
		      }
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	    } catch (Exception e) {
	       logger.debug(e);
	       
	    }
	    document.close();	
	    return new File(fileName);
	}
	
	 
	public synchronized File createMonthlyRevenueJudicalReport(ArrayList param ) {
		 Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		 String fileName = "";
		    try {
		      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		      Date date = new  Date();
		       
		      PropertiesFileReader pr = 
		    		PropertiesFileReader.
		    		 getInstance("com.wipro.igrs.igrs");
		      
		      fileName = pr.getValue("REPORT_LOCATION")
		             +"MonthlyRevenueJudicial"+dateFormat.format(date)+".pdf";
		       
		      logger.debug("File is created :-"+fileName);
		      PdfWriter.getInstance(document, new FileOutputStream(fileName));
		      document.open();
		      
		      Table datatable = new Table(14);
		      int headerwidths[] = {10, 20, 20,
		                            20, 15, 15,
		                            25, 25, 25,
		                            25, 25, 25,
		                            25, 20};
		      datatable.setWidths(headerwidths);
		      datatable.setWidth(105);
		      datatable.setPadding(3);
		      Phrase firstheader = new Phrase(
		    		  "Monthly Information of Revenue "
		    		  +"Receipt From Judicial Stamps", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  15, Font.BOLD));
		      Cell cellfirstheader = new Cell(firstheader);
		      cellfirstheader.setHorizontalAlignment(Element.ALIGN_CENTER);
		      cellfirstheader.setLeading(30);
		      cellfirstheader.setColspan(14);
		      cellfirstheader.setBorder(Rectangle.NO_BORDER);
		      cellfirstheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(cellfirstheader);


		      Cell slno = new Cell(new Phrase("Sl No", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    		  10, Font.BOLD)));
		      slno.setHeader(true);
		      slno.setRowspan(1);
		      slno.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(slno);

		      //datatable.addCell("Header 1");
		      Cell docType1 = new Cell(new Phrase("District", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType1.setHeader(true);
		      docType1.setRowspan(1);
		      docType1.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType1);
		      
		      Cell docType2 = new Cell(new Phrase("April", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType2.setHeader(true);
		      docType2.setRowspan(1);
		      docType2.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType2);
		       
		      Cell docType3 = new Cell(new Phrase("May", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType3.setHeader(true);
		      docType3.setRowspan(1);
		      docType3.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType3);
		      
		      Cell docType4 = new Cell(new Phrase("June", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType4.setHeader(true);
		      docType4.setRowspan(1);
		      docType4.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType4);
		    
		      Cell docType5 = new Cell(new Phrase("July", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType5.setHeader(true);
		      docType5.setRowspan(1);
		      docType5.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType5);
		      
		      Cell docType6 = new Cell(new Phrase("August", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType6.setHeader(true);
		      docType6.setRowspan(1);
		      docType6.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType6);
		      
		      Cell docType7 = new Cell(new Phrase("Septemeber", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType7.setHeader(true);
		      docType7.setRowspan(1);
		      docType7.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType7);
		      
		      Cell docType8 = new Cell(new Phrase("October", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType8.setHeader(true);
		      docType8.setRowspan(1);
		      docType8.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType8);
		      
		      Cell docType9 = new Cell(new Phrase("Novemeber", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType9.setHeader(true);
		      docType9.setRowspan(1);
		      docType9.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType9);
		      
		      Cell docType10 = new Cell(new Phrase("Decemeber", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType10.setHeader(true);
		      docType10.setRowspan(1);
		      docType10.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType10);
		      
		      Cell docType11 = new Cell(new Phrase("January", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType11.setHeader(true);
		      docType11.setRowspan(1);
		      docType11.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType11);
		      
		      Cell docType12 = new Cell(new Phrase("Feburary", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType12.setHeader(true);
		      docType12.setRowspan(1);
		      docType12.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType12);
		      
		      Cell docType13 = new Cell(new Phrase("March", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
		    				  10, Font.BOLD)));
		      docType13.setHeader(true);
		      docType13.setRowspan(1);
		      docType13.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType13);
		      
		      
		      datatable.endHeaders();
		      datatable.setBorderWidth(1);
		      int row = 0;
		      if(param!=null) {
			      for(int i=0;i<param.size();i++){
			        datatable.setAlignment(Element.ALIGN_CENTER);
			        
			        MISReportDTO dto = (MISReportDTO) param.get(i);
			        row = i+1 ;
			        datatable.addCell(""+row);
			        datatable.addCell(dto.getDistrictName());
			        datatable.addCell(dto.getRevenueApril());
			        datatable.addCell(dto.getRevenueMay());
			        datatable.addCell(dto.getRevenueJune());
			        datatable.addCell(dto.getRevenueJuly());
			        datatable.addCell(dto.getRevenueAug());
			        datatable.addCell(dto.getRevenueSept());
			        datatable.addCell(dto.getRevenueOct());
			        datatable.addCell(dto.getRevenueNov());
			        datatable.addCell(dto.getRevenueDec());
			        datatable.addCell(dto.getRevenueJan());
			        datatable.addCell(dto.getRevenueFeb());
			        datatable.addCell(dto.getRevenueMar());
			         
			        
			      }
		      }
		      datatable.setCellsFitPage(true);
		      document.add(datatable);
		    } catch (Exception e) {
		    	logger.debug(e);
		    }
		    document.close();
		    return (new File(fileName));
	}

	public synchronized File createUsageReport(ArrayList param ) {
		 Document document = new Document(PageSize.A4.rotate(), 50, 50, 50, 50);
		 String fileName = "";
		    try {
		      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		      Date date = new  Date();
		       
		      PropertiesFileReader pr = 
		    		PropertiesFileReader.
		    		 getInstance("com.wipro.igrs.igrs");
		      
		      fileName = pr.getValue("REPORT_LOCATION")
		             +"UsageReport"+dateFormat.format(date)+".pdf";
		       
		      logger.debug("File is created :-"+fileName);
		      PdfWriter.getInstance(document, new FileOutputStream(fileName));
		      document.open();
		      
		      Table datatable = new Table(4);
		      int headerwidths[] = {5, 20, 20,
		                              10};
		      datatable.setWidths(headerwidths);
		      datatable.setWidth(80);
		      datatable.setPadding(3);
		      Phrase firstheader = new Phrase(
		    		  "Usage Report", 
		    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 15, Font.BOLD));
		      Cell cellfirstheader = new Cell(firstheader);
		      cellfirstheader.setHorizontalAlignment(Element.ALIGN_CENTER);
		      cellfirstheader.setLeading(30);
		      cellfirstheader.setColspan(4);
		      cellfirstheader.setBorder(Rectangle.NO_BORDER);
		      cellfirstheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(cellfirstheader);


		    

		      //datatable.addCell("Header 1");
		      Cell docType1 = new Cell(new Phrase("SNo", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType1.setHeader(true);
		      docType1.setRowspan(1);
		      docType1.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType1);
		      
		      Cell docType2 = new Cell(new Phrase("Functionality", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType2.setHeader(true);
		      docType2.setRowspan(1);
		      docType2.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType2);
		       
		      Cell docType3 = new Cell(new Phrase("No. of times accessed", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType3.setHeader(true);
		      docType3.setRowspan(1);
		      docType3.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType3);
		      
		      Cell docType4 = new Cell(new Phrase("Access Time", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
		      docType4.setHeader(true);
		      docType4.setRowspan(1);
		      docType4.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(docType4);
		     

		      datatable.endHeaders();
		      datatable.setBorderWidth(1);
		      int row = 0;
		      
		      if(param!=null) {
			      for(int i=0;i<param.size();i++){
			        datatable.setAlignment(Element.ALIGN_CENTER);
			        
			        MISReportDTO dto = (MISReportDTO) param.get(i);
			        row = i+1 ;
			        datatable.addCell(""+row);
			        datatable.addCell(dto.getFunctionality());
			        datatable.addCell(dto.getNoTimeAccessed());
			        datatable.addCell(dto.getAccessTime());
			       
			      }
		      }
		      datatable.setCellsFitPage(true);
		      document.add(datatable);
		    } catch (Exception e) {
		    	logger.debug(e);
		    }
		    document.close();
		    return (new File(fileName));
	}
	
	
	
	public synchronized File createRevenueReceiptSROWiseURReport
										(ArrayList param) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);
		String fileName = "";
	    try {
	      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		  Date date = new  Date();
		       
		  PropertiesFileReader pr = 
		    		PropertiesFileReader.
		    		 getInstance("com.wipro.igrs.igrs");
		      
		  fileName = pr.getValue("REPORT_LOCATION")
		             +"SROWiseUrbanRural"+dateFormat.format(date)+".pdf";
		       
		  logger.debug("File is created :-"+fileName);
		      
	      PdfWriter.getInstance(document, new FileOutputStream(fileName));
	      document.open();

	      Table datatable = new Table(20);

	      int headerwidths[] = {5, 20, 10, 10,
                  				10,10, 10, 10,
                  				10,10, 10, 10,
                  				10,10, 10, 10,
                  				10,10, 10, 10};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);

	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Revenue Receipt SRO Wise � "
	    		  +"Urban/Rural", FontFactory.getFont(FontFactory.TIMES_ROMAN,
	    				  18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(20);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

	      datatable.setBorder(1);
	      datatable.setAlignment(Element.ALIGN_CENTER);
	      Cell slno = new Cell(new Phrase("Sl No", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setColspan(1);
	      slno.setRowspan(2);
	      slno.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(slno);

	      //datatable.addCell("Header 1");
	      Cell supremeCourt = new Cell(new Phrase("SRO Name ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      supremeCourt.setHeader(true);
	      supremeCourt.setHorizontalAlignment(Element.ALIGN_CENTER);
	      supremeCourt.setColspan(1);
	      supremeCourt.setRowspan(2);
	      supremeCourt.setBackgroundColor(new Color(255,255,36));
	      
	      datatable.addCell(supremeCourt);

	      //datatable.addCell("Header 2");
	      Cell highCourt = new Cell(new Phrase("Income During ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      highCourt.setHeader(true);
	      highCourt.setHorizontalAlignment(Element.ALIGN_CENTER);
	      highCourt.setColspan(3);
	      highCourt.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(highCourt);

	      //datatable.addCell("Header 3");
	      Cell boardofRevenue = new Cell(new Phrase(
	    		  "Income During the Same in previous year", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      boardofRevenue.setHeader(true);
	      boardofRevenue.setColspan(3);
	      boardofRevenue.setHorizontalAlignment(Element.ALIGN_CENTER);
	      boardofRevenue.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(boardofRevenue);

	      //datatable.addCell("Header 4");
	      Cell commissioner = new Cell(new Phrase(
	    		  "Comparative Increase/Decrease ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      commissioner.setHeader(true);
	      commissioner.setColspan(3);
	      commissioner.setHorizontalAlignment(Element.ALIGN_CENTER);
	      commissioner.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(commissioner);

	      //datatable.addCell("Header 5");
	      Cell remarks = new Cell(new Phrase("No of Registered Document ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, 
	    		  Font.BOLD)));
	      remarks.setHeader(true);
	      remarks.setHorizontalAlignment(Element.ALIGN_CENTER);
	      remarks.setColspan(3);
	      remarks.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(remarks);

	      //datatable.addCell("Header 6");

	      Cell empty = new Cell(new Phrase(
	    		  "No of Registered Document in previous year", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      
	      empty.setHeader(true);
	      empty.setColspan(3);
	      empty.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(empty);
	      //datatable.addCell("Header 7");

	      Cell caseNoforSupCourt = new Cell(new Phrase(
	    		  "Comparative Increase/Decrease in Documents ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      
	      caseNoforSupCourt.setHeader(true);
	      caseNoforSupCourt.setColspan(3);
	      caseNoforSupCourt.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(caseNoforSupCourt);

	    
	      //datatable.addCell("Header 5");
	      Cell currentCaseStatusSupCourt = new Cell(new Phrase(
	    		  "Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      
	      currentCaseStatusSupCourt.setHeader(true);
	      currentCaseStatusSupCourt.setColspan(1);
	      currentCaseStatusSupCourt.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(currentCaseStatusSupCourt);

	      Cell caseNoforHighCourt = new Cell(new Phrase("Rural ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      caseNoforHighCourt.setHeader(true);
	      caseNoforHighCourt.setColspan(1);
	      caseNoforHighCourt.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(caseNoforHighCourt);

	      //datatable.addCell("Header 8");
	      Cell amtRecoverForHighCourt = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      amtRecoverForHighCourt.setHeader(true);
	      amtRecoverForHighCourt.setColspan(1);
	      amtRecoverForHighCourt.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(amtRecoverForHighCourt);

	      //datatable.addCell("Header 10");
	      Cell acnTakenVacateStayHighCourt = new Cell(new Phrase("Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      acnTakenVacateStayHighCourt.setHeader(true);
	      acnTakenVacateStayHighCourt.setColspan(1);
	      acnTakenVacateStayHighCourt.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(acnTakenVacateStayHighCourt);

	      Cell latestCaseProcesHighCourt = new Cell(new Phrase("Rural ",
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      latestCaseProcesHighCourt.setHeader(true);
	      latestCaseProcesHighCourt.setColspan(1);
	      latestCaseProcesHighCourt.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(latestCaseProcesHighCourt);


	      //datatable.addCell("Header 5");
	      Cell currentCaseStatusHighCourt = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      currentCaseStatusHighCourt.setHeader(true);
	      currentCaseStatusHighCourt.setColspan(1);
	      currentCaseStatusHighCourt.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(currentCaseStatusHighCourt);

	      Cell caseNoforBoardOfRevenue = new Cell(new Phrase("Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      caseNoforBoardOfRevenue.setHeader(true);
	      caseNoforBoardOfRevenue.setColspan(1);
	      caseNoforBoardOfRevenue.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(caseNoforBoardOfRevenue);

	      //datatable.addCell("Header 8");
	      Cell amtRecoverForBoardOfRevenue = new Cell(new Phrase("Rural ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    		  10, Font.BOLD)));
	      amtRecoverForBoardOfRevenue.setHeader(true);
	      amtRecoverForBoardOfRevenue.setColspan(1);
	      amtRecoverForBoardOfRevenue.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(amtRecoverForBoardOfRevenue);
	      
	      Cell currentCaseStatusHighCourt1 = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      currentCaseStatusHighCourt1.setHeader(true);
	      currentCaseStatusHighCourt1.setColspan(1);
	      currentCaseStatusHighCourt1.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(currentCaseStatusHighCourt1);
	      

	      //datatable.addCell("Header 10");
	      Cell acnTakenVacateStayBoardOfRevenue = new Cell(new Phrase("Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      acnTakenVacateStayBoardOfRevenue.setHeader(true);
	      acnTakenVacateStayBoardOfRevenue.setColspan(1);
	      acnTakenVacateStayBoardOfRevenue.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(acnTakenVacateStayBoardOfRevenue);

	      Cell latestCaseProcesBoardOfRevenue = new Cell(new Phrase("Rural ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      latestCaseProcesBoardOfRevenue.setHeader(true);
	      latestCaseProcesBoardOfRevenue.setColspan(1);
	      latestCaseProcesBoardOfRevenue.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(latestCaseProcesBoardOfRevenue);

	      //datatable.addCell("Header 5");
	      Cell currentCaseStatusBoardOfRevenue = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN,
	    				  10, Font.BOLD)));
	      currentCaseStatusBoardOfRevenue.setHeader(true);
	      currentCaseStatusBoardOfRevenue.setColspan(1);
	      currentCaseStatusBoardOfRevenue.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(currentCaseStatusBoardOfRevenue);



	      Cell caseNoforCommissioner = new Cell(new Phrase("Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      caseNoforCommissioner.setHeader(true);
	      caseNoforCommissioner.setColspan(1);
	      caseNoforCommissioner.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(caseNoforCommissioner);

	      //datatable.addCell("Header 8");
	      Cell amtRecoverForCommissioner = new Cell(new Phrase("Rural ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      amtRecoverForCommissioner.setHeader(true);
	      amtRecoverForCommissioner.setColspan(1);
	      amtRecoverForCommissioner.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(amtRecoverForCommissioner);

	      //datatable.addCell("Header 10");
	      Cell acnTakenVacateStayCommissioner = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      acnTakenVacateStayCommissioner.setHeader(true);
	      acnTakenVacateStayCommissioner.setColspan(1);
	      acnTakenVacateStayCommissioner.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(acnTakenVacateStayCommissioner);

	      Cell latestCaseProcesCommissioner = new Cell(new Phrase("Urban ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      latestCaseProcesCommissioner.setHeader(true);
	      latestCaseProcesCommissioner.setColspan(1);
	      latestCaseProcesCommissioner.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(latestCaseProcesCommissioner);

	      //datatable.addCell("Header 5");
	      Cell currentCaseStatusCommissioner = new Cell(new Phrase("Rural ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      currentCaseStatusCommissioner.setHeader(true);
	      currentCaseStatusCommissioner.setColspan(1);
	      currentCaseStatusCommissioner.setBackgroundColor(
	    		  new Color(196,255,255));
	      datatable.addCell(currentCaseStatusCommissioner);

	      Cell remarkEmpty = new Cell(new Phrase("Total ", 
	    		  FontFactory.getFont(FontFactory.TIMES_ROMAN, 
	    				  10, Font.BOLD)));
	      remarkEmpty.setHeader(true);
	      remarkEmpty.setColspan(1);
	      remarkEmpty.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(remarkEmpty);

	      datatable.endHeaders();
	      datatable.setBorder(1);

	      int row = 0;
	      
	      if(param!=null) {
		      for(int i=0;i<param.size();i++){
		        datatable.setAlignment(Element.ALIGN_CENTER);
		        
		        MISReportDTO dto = (MISReportDTO) param.get(i);
		        row = i+1 ;
		        datatable.addCell(""+row);
		        datatable.addCell(dto.getOfficeName());
		        datatable.addCell(dto.getRevenueRuralCur());
		        datatable.addCell(dto.getRevenueUrbanCur());
		        datatable.addCell(dto.getRevenueCurTotal());
		        datatable.addCell(dto.getRevenueRuralPrev());
		        datatable.addCell(dto.getRevenueUrbanPrev());
		        datatable.addCell(dto.getRevenuePrevTotal());
		        datatable.addCell(dto.getRevenueRuralPer());
		        datatable.addCell(dto.getRevenueUrbanPer());
		        datatable.addCell(dto.getRevenuePerTotal());
		        datatable.addCell(dto.getNoOfDocRuralCur());
		        datatable.addCell(dto.getNoOfDocUrbanCur());
		        datatable.addCell(dto.getNoOfDocCurTotal());
		        datatable.addCell(dto.getNoOfDocRuralPrev());
		        datatable.addCell(dto.getNoOfDocUrbanPrev());
		        datatable.addCell(dto.getNoOfDocPrevTotal());
		      }
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	      //PdfPTable pdfTable = datatable.createPdfPTable();
	      //pdfTable.setTotalWidth(400);
	    } catch (Exception e) {
	      logger.debug(e);
	    }
	    document.close();
	    return (new File(fileName));
	}
	
	
	public synchronized File createPendingCourtCaseReport(ArrayList param) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);
		String fileName = "";
	    try {
	      DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		  Date date = new  Date();
					       
		  PropertiesFileReader pr = 
					    		PropertiesFileReader.
					    		 getInstance("com.wipro.igrs.igrs");
					      
		  fileName = pr.getValue("REPORT_LOCATION")
					             +"PendingCourtCase"+dateFormat.format(date)+".pdf";
	      PdfWriter.getInstance(document, new FileOutputStream(fileName));
	      
	      document.open();

	      Table datatable = new Table(8);
	     
	      int headerwidths[] = {50, 50, 50,50, 50, 50,50,50};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);
	      
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Pending Court Cases in High Court/ Supreme Court:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(8);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

	      datatable.setBorder(2);
	      datatable.setAlignment(1);
	      Cell slno = new Cell(new Phrase("Petition Number", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setColspan(1);
	      slno.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(slno);
	      
	      //datatable.addCell("Header 1");
	      Cell statusDurMonth = new Cell(new Phrase("Petitioner Name  ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      statusDurMonth.setHeader(true);
	      statusDurMonth.setColspan(1);
	      statusDurMonth.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(statusDurMonth);
	      
	      //datatable.addCell("Header 2");
	      Cell statusDuringPeriod = new Cell(new Phrase("Petition Date", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      statusDuringPeriod.setHeader(true);
	      statusDuringPeriod.setColspan(1);
	      statusDuringPeriod.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(statusDuringPeriod);
	      
	      //datatable.addCell("Header 3");
	      Cell yearWisePending = new Cell(new Phrase("Court Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      yearWisePending.setHeader(true);
	      yearWisePending.setColspan(1);
	      yearWisePending.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(yearWisePending);
	      
	      //datatable.addCell("Header 4");
	      Cell annualTarget = new Cell(new Phrase("Officer In-charge ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      annualTarget.setHeader(true);
	      annualTarget.setColspan(1);
	      annualTarget.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(annualTarget);
	      
	      //datatable.addCell("Header 5");
	      Cell monthTagRecov = new Cell(new Phrase("Subject Matter of Petition in Brief ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      monthTagRecov.setHeader(true);
	      monthTagRecov.setColspan(1);
	      monthTagRecov.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(monthTagRecov);
	      
	      //datatable.addCell("Header 6");
	      
	      Cell noOfCasePendonApr = new Cell(new Phrase("Stay Order (if any)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      noOfCasePendonApr.setHeader(true);
	      noOfCasePendonApr.setColspan(1);
	      noOfCasePendonApr.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(noOfCasePendonApr);
	      //datatable.addCell("Header 7");
	      
	      Cell caseRegDurMonth = new Cell(new Phrase("Remarks ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      caseRegDurMonth.setHeader(true);
	      caseRegDurMonth.setColspan(1);
	      caseRegDurMonth.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(caseRegDurMonth);
	      
	      datatable.endHeaders();
	      datatable.setBorder(1);

	      for(int i=0;i<200;i++){
	        datatable.setAlignment(Element.ALIGN_LEFT);
	        
	        datatable.addCell("1");
	        datatable.addCell("2");
	        datatable.addCell("3");
	        datatable.addCell("4");
	        datatable.addCell("5");
	        datatable.addCell("6");
	        datatable.addCell("7");
	        datatable.addCell("8");
	       
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	      //PdfPTable pdfTable = datatable.createPdfPTable();
	      //pdfTable.setTotalWidth(400);
	    } catch (Exception e) {
	      logger.debug(e);
	    }
	    document.close();
	    return (new File(fileName));
	}
	public void createRevenueReceiptRegDocGANGReport(ArrayList param) {
		Document document = new Document(PageSize.A4.rotate(), 50, 50, 20, 20);

		String fileName = "";
	    try {
	     DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		 Date date = new  Date();
						       
		 PropertiesFileReader pr = 
					    		PropertiesFileReader.
					    		 getInstance("com.wipro.igrs.igrs");
						      
		  fileName = pr.getValue("REPORT_LOCATION")
						             +"RevenueGANG"+dateFormat.format(date)+".pdf";
	      PdfWriter.getInstance(document, new FileOutputStream(fileName));
	      document.open();

	      Table datatable = new Table(6);
	     
	      int headerwidths[] = {20, 70, 50,50, 50, 50};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);
	      
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Revenue Receipt And Registered Documents Relating to other local bodies(Govt and Non Govt)", FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(6);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

	      datatable.setBorder(2);
	      datatable.setAlignment(1);
	      Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setRowspan(2);
	      slno.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(slno);
	      
	      //datatable.addCell("Header 1");
	      Cell statusDurMonth = new Cell(new Phrase("       Name Of Institutions     ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      statusDurMonth.setHeader(true);
	      statusDurMonth.setRowspan(2);
	      statusDurMonth.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(statusDurMonth);
	      
	      //datatable.addCell("Header 2");
	      Cell statusDuringPeriod = new Cell(new Phrase("No of Documents Registered & Revenue Receipt in the Month ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      statusDuringPeriod.setHeader(true);
	      statusDuringPeriod.setColspan(2);
	      statusDuringPeriod.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(statusDuringPeriod);
	      
	      //datatable.addCell("Header 3");
	      Cell yearWisePending = new Cell(new Phrase("No of Documents Registered & Revenue Receipt in the Period April t0_____", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      yearWisePending.setHeader(true);
	      yearWisePending.setColspan(2);
	      yearWisePending.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(yearWisePending);
	      
	      //datatable.addCell("Header 4");
	      Cell annualTarget = new Cell(new Phrase("No of Documents ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      annualTarget.setHeader(true);
	      annualTarget.setColspan(1);
	      annualTarget.setBackgroundColor(new Color(176,255,255));
	      datatable.addCell(annualTarget);
	      
	      //datatable.addCell("Header 5");
	      Cell monthTagRecov = new Cell(new Phrase("Revenue Receipt ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      monthTagRecov.setHeader(true);
	      monthTagRecov.setColspan(1);
	      monthTagRecov.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(monthTagRecov);
	      
	      //datatable.addCell("Header 6");
	      
	      Cell noOfCasePendonApr = new Cell(new Phrase("No of Documents", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      noOfCasePendonApr.setHeader(true);
	      noOfCasePendonApr.setColspan(1);
	      noOfCasePendonApr.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(noOfCasePendonApr);
	      //datatable.addCell("Header 7");
	      
	      Cell caseRegDurMonth = new Cell(new Phrase("Revenue Receipt ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD)));
	      caseRegDurMonth.setHeader(true);
	      caseRegDurMonth.setColspan(1);
	      caseRegDurMonth.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(caseRegDurMonth);
	      
	      
	      datatable.endHeaders();
	      datatable.setBorder(1);

	      for(int i=0;i<200;i++){
	        datatable.setAlignment(Element.ALIGN_LEFT);
	        
	        datatable.addCell("1");
	        datatable.addCell("2");
	        datatable.addCell("3");
	        datatable.addCell("4");
	        datatable.addCell("5");
	        datatable.addCell("6");
	        
	       
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	      //PdfPTable pdfTable = datatable.createPdfPTable();
	      //pdfTable.setTotalWidth(400);
	    } catch (Exception e) {
	      logger.debug(e);
	    }
	    document.close();
	}

	public static void main(String[] args) {
		Report r = new Report();
		 
		ArrayList list = new ArrayList();
		//r.createRevenueReceiptSROWiseURReport(list);
		 
	}
}

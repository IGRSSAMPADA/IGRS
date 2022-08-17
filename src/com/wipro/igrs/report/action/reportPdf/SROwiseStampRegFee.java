package com.wipro.igrs.report.action.reportPdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.report.dto.MarketTrendReportDTO;

public class SROwiseStampRegFee {


	public static void main(String[] args) {
	    Document document = new Document(PageSize.A4.rotate(), 50, 50, 20,20);

	    try {
	      PdfWriter.getInstance(document, new FileOutputStream("SROwiseStampRegFee.pdf"));
	      document.open();

	      Table datatable = new Table(35);
	      int headerwidths[] = {10, 10, 10,10, 10, 10,10, 10, 10,10, 10, 10,10,10,10,10,10,10,
	    		                10, 10, 10,10, 10, 10,10, 10, 10,10, 10, 10,10,10,10,10,10};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Revenue Receipt SRO Wise - Stamp Duty/Registration Fee", FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(35);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

//	      datatable.setDefaultCellBorderWidth(2);
//	      datatable.setDefaultHorizontalAlignment(1);
	      Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setRowspan(2);
	      slno.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(slno);
	      
	      //datatable.addCell("Header 1");
	      Cell sroType = new Cell(new Phrase("Name of S.R.O.", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      sroType.setHeader(true);
	      sroType.setRowspan(2);
	      sroType.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(sroType);
	      
	      //datatable.addCell("Header 2");
	      Cell income = new Cell(new Phrase("Income During the month..............", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      income.setHeader(true);
	      income.setColspan(3);
	      income.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(income);     
	      
	      Cell incMon = new Cell(new Phrase("Income During the Same month of Previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      incMon.setHeader(true);
	      incMon.setColspan(3);
	      incMon.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(incMon);	      
	      
	      Cell compPercent = new Cell(new Phrase("Comparative Increase/Decrease in Percent", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      compPercent.setHeader(true);
	      compPercent.setColspan(3);
	      compPercent.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(compPercent);
	      
	      Cell incMonth = new Cell(new Phrase("Income During the month of April to ...........", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      incMonth.setHeader(true);
	      incMonth.setColspan(3);
	      incMonth.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(incMonth);
	      
	      Cell incMonthPres = new Cell(new Phrase("Income During Same Period of Previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      incMonthPres.setHeader(true);
	      incMonthPres.setColspan(3);
	      incMonthPres.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(incMonthPres);
	      
	      Cell compPercen = new Cell(new Phrase("Comparative (in Percent)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      compPercen.setHeader(true);
	      compPercen.setColspan(3);
	      compPercen.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(compPercen);
	      
	      Cell docReg = new Cell(new Phrase("No. of Documents Registered During the month of Previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docReg.setHeader(true);
	      docReg.setColspan(3);
	      docReg.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(docReg);
	      
	      Cell docRegMonth = new Cell(new Phrase("No. of Documents Registered During the Same month of Previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docRegMonth.setHeader(true);
	      docRegMonth.setColspan(3);
	      docRegMonth.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(docRegMonth);
	      
	      Cell docRegApr = new Cell(new Phrase("No. of Documents Registered During the month of April to ---------", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docRegApr.setHeader(true);
	      docRegApr.setColspan(3);
	      docRegApr.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(docRegApr);
	      
	      Cell docRegAprPeriod = new Cell(new Phrase("No. of Documents Registered During the same Period of Previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docRegAprPeriod.setHeader(true);
	      docRegAprPeriod.setColspan(3);
	      docRegAprPeriod.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(docRegAprPeriod);
	      
	      Cell compDoc = new Cell(new Phrase("Comparative Increase/Decrease in No. of Documents", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      compDoc.setHeader(true);
	      compDoc.setColspan(3);
	      compDoc.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(compDoc);    
	      
	      for(int i=0;i<11;i++){
		      Cell stampDty = new Cell(new Phrase("Stamp Duty", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      stampDty.setHeader(true);
		      stampDty.setColspan(1);
		      stampDty.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(stampDty);
		      
		      //datatable.addCell("Header 6");
		      
		      Cell regFee = new Cell(new Phrase("Registration Fee", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      regFee.setHeader(true);
		      regFee.setColspan(1);
		      regFee.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(regFee);
		      //datatable.addCell("Header 7");
		      
		      Cell total = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      total.setHeader(true);
		      total.setColspan(1);
		      total.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(total);
	      }
	      datatable.endHeaders();
	     // datatable.setDefaultCellBorderWidth(1);

	      for(int i=0;i<10;i++){
	        //datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
	        datatable.addCell("1");
	        datatable.addCell("2");
	        datatable.addCell("3");
	        datatable.addCell("4");
	        datatable.addCell("5");
	        datatable.addCell("6");
	        datatable.addCell("7");
	        datatable.addCell("8");
	        datatable.addCell("9");
	        datatable.addCell("10");
	        datatable.addCell("11");
	        datatable.addCell("12");
	        datatable.addCell("13");
	        datatable.addCell("14");
	        datatable.addCell("15");
	        datatable.addCell("16");
	        datatable.addCell("17");
	        datatable.addCell("18");
	        datatable.addCell("19");
	        datatable.addCell("20");
	        datatable.addCell("21");
	        datatable.addCell("22");
	        datatable.addCell("23");
	        datatable.addCell("24");
	        datatable.addCell("25");
	        datatable.addCell("26");
	        datatable.addCell("27");
	        datatable.addCell("28");
	        datatable.addCell("29");
	        datatable.addCell("30");
	        datatable.addCell("31");
	        datatable.addCell("32");
	        datatable.addCell("33");
	        datatable.addCell("34");
	        datatable.addCell("35");
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    document.close();
	 
	} 


}

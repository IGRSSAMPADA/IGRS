package com.wipro.igrs.report.action.reportPdf;

import java.awt.Color;
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

public class SubWiseStampRegFee {

	public static void main(String[] args) {
	    Document document = new Document(PageSize.A4.rotate(),40,40,10,10);

	    try {
	      PdfWriter.getInstance(document, new FileOutputStream("SubWiseStampReg.pdf"));
	      document.open();

	      Table datatable = new Table(10);
	      int headerwidths[] = {10,24,10,10,10,10,10,10,10,10};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(100);
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Subject Wise Stamp Duty/Registration Fee", FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      title.setLeading(20);
	      title.setColspan(10);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);
//	      datatable.setDefaultCellBorderWidth(2);
//	      datatable.setDefaultHorizontalAlignment(1);
	      Cell slno = new Cell(new Phrase("S.No", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setRowspan(2);
	      slno.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(slno);
	      
	      //datatable.addCell("Header 1");
	      Cell sub = new Cell(new Phrase("Subject", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      sub.setHeader(true);
	      sub.setRowspan(2);
	      sub.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(sub);
	      
	      //datatable.addCell("Header 2");
	      Cell exmMnth = new Cell(new Phrase("Exemption given in the Month", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      exmMnth.setHeader(true);
	      exmMnth.setColspan(4);
	      exmMnth.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(exmMnth);
	      
	      Cell exmMnthApr = new Cell(new Phrase("Exemption given in the Duration April to ..........", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      exmMnthApr.setHeader(true);
	      exmMnthApr.setColspan(4);
	      exmMnthApr.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(exmMnthApr);
	      
	      for(int i=0;i<2;i++){
	    	  Cell noDocPrev = new Cell(new Phrase("No of Documents ", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      noDocPrev.setHeader(true);
		      noDocPrev.setColspan(1);
		      noDocPrev.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(noDocPrev);
		      
		      Cell stampDty = new Cell(new Phrase("Exemption in Stamp Duty ", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      stampDty.setHeader(true);
		      stampDty.setColspan(1);
		      stampDty.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(stampDty);
		      //datatable.addCell("Header 7");
		      
		      Cell regFee = new Cell(new Phrase("Exemption in Registration Fee", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      regFee.setHeader(true);
		      regFee.setColspan(1);
		      regFee.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(regFee);
		      
		      //datatable.addCell("Header 8");
		      Cell total = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		      total.setHeader(true);
		      total.setColspan(1);
		      total.setBackgroundColor(new Color(196,255,255));
		      datatable.addCell(total);
	      } 
	      /*String[] str = new String[10];
	      
	      str[0] = "Exemption in Favour of Government.";
	      str[1] = "Exemption in Favour of Cooperative Society.";*/
	      
	     
	     
          Cell var = new Cell(new Phrase("1", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemGov = new Cell(new Phrase("Exemption in favour of Government.", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemGov.setHeader(true);
          exemGov.setColspan(1);
          exemGov.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemGov);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("2", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemCoop = new Cell(new Phrase("Exemption in favour of Cooperative Society.", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemCoop.setHeader(true);
          exemCoop.setColspan(1);
          exemCoop.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemCoop);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("3", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemNation = new Cell(new Phrase("Exemption in favour of Nationalised Bank", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemNation.setHeader(true);
          exemNation.setColspan(1);
          exemNation.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemNation);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("4", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemLand = new Cell(new Phrase("Exemption in favour of Land Developement Bank", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemLand.setHeader(true);
          exemLand.setColspan(1);
          exemLand.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemLand);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");         
          
	      
          var = new Cell(new Phrase("5", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemFinance = new Cell(new Phrase("Exemption in favour of Other Financial Institution", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemFinance.setHeader(true);
          exemFinance.setColspan(1);
          exemFinance.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemFinance);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("6", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemEcon = new Cell(new Phrase("Exemption in favour of Economically Weaker Section.", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemEcon.setHeader(true);
          exemEcon.setColspan(1);
          exemEcon.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemEcon);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("7", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemFemale = new Cell(new Phrase("Exemption in Favour of Females", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemFemale.setHeader(true);
          exemFemale.setColspan(1);
          exemFemale.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemFemale);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("8", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemIndUnit= new Cell(new Phrase("Exemption in favour of Industrial Units", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemIndUnit.setHeader(true);
          exemIndUnit.setColspan(1);
          exemIndUnit.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemIndUnit);
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
          var = new Cell(new Phrase("9", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          var.setHeader(true);
          var.setColspan(1);
          var.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(var);
	      
          Cell exemSpeExem = new Cell(new Phrase("Exemption in Favour of Other Special Exemptions.(Clarify the Subject)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          exemSpeExem.setHeader(true);
          exemSpeExem.setColspan(1);
          exemSpeExem.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(exemSpeExem); 
	      
	      datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
          
          Cell cell = new Cell(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          cell.setHeader(true);
          cell.setColspan(1);
          cell.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(cell);
	      
          Cell total = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
          total.setHeader(true); 
          total.setColspan(1);
          total.setBackgroundColor(new Color(196,255,255));
          datatable.addCell(total);
          datatable.addCell("3");
          datatable.addCell("4");
          datatable.addCell("5");
          datatable.addCell("6");
          datatable.addCell("7");
          datatable.addCell("8");
          datatable.addCell("9");
          datatable.addCell("10");
	      
	      datatable.endHeaders();
	      //datatable.setDefaultCellBorderWidth(1);

	      /*for(int i=0;i<10;i++){
	        datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
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
	      }*/
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    document.close();
	  }
}

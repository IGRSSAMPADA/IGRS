package com.wipro.igrs.report.action.reportPdf;

import java.awt.Color;
import java.io.FileOutputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class RevenueProjection {

	public static void main(String[] args) {
	    Document document = new Document(PageSize.A4.rotate(), 50, 50, 40,40);

	    try {
	      PdfWriter.getInstance(document, new FileOutputStream("RevenueProjection.pdf"));
	      document.open();

	      Table datatable = new Table(12);
	      int headerwidths[] = {10, 10, 10,10, 10, 10,10, 10, 10,10, 10, 10};
	      datatable.setWidths(headerwidths);
	      datatable.setWidth(105);
	      datatable.setPadding(3);

	      Cell title = new Cell(new Phrase("Revenue Projection Report", FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD)));
	      title.setHorizontalAlignment(Element.ALIGN_CENTER);
	      //title.setLeading(100);
	      title.setColspan(12);
	      title.setBorder(Rectangle.NO_BORDER);
	      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	      datatable.addCell(title);

//	      datatable.setDefaultCellBorderWidth(2);
//	      datatable.setDefaultHorizontalAlignment(1);
	      Cell slno = new Cell(new Phrase("Sl No", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      slno.setHeader(true);
	      slno.setRowspan(3);
	      slno.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(slno);
	      
	      //datatable.addCell("Header 1");
	      Cell docType = new Cell(new Phrase("Document Type", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docType.setHeader(true);
	      docType.setRowspan(3);
	      docType.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(docType);
	      
	      //datatable.addCell("Header 2");
	      Cell yearType = new Cell(new Phrase("Years(Last * Year - depending on FROM & TO Dates)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      yearType.setHeader(true);
	      yearType.setColspan(6);
	      yearType.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(yearType);     
	      
	      Cell projRevenue = new Cell(new Phrase("Projected Revenue", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      projRevenue.setHeader(true);
	      projRevenue.setColspan(4);
	      projRevenue.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(projRevenue);
	      
	      //datatable.addCell("Header 3");
	      Cell yearPrevType = new Cell(new Phrase("2005-2006", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      yearPrevType.setHorizontalAlignment(Element.ALIGN_CENTER);
	      yearPrevType.setHeader(true);
	      yearPrevType.setColspan(6);
	      yearPrevType.setBackgroundColor(new Color(176,255,255));
	      datatable.addCell(yearPrevType);
	      
	      Cell prjCurrType = new Cell(new Phrase("2007-2008", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      prjCurrType.setHorizontalAlignment(Element.ALIGN_CENTER);
	      prjCurrType.setHeader(true);
	      prjCurrType.setColspan(4);
	      prjCurrType.setBackgroundColor(new Color(176,255,255));
	      datatable.addCell(prjCurrType);
	      
	      //datatable.addCell("Header 5");
	      Cell noDocPrev = new Cell(new Phrase("No of Docs", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      noDocPrev.setHeader(true);
	      noDocPrev.setColspan(1);
	      noDocPrev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(noDocPrev);
	      
	      //datatable.addCell("Header 6");
	      
	      Cell reveRecePrev = new Cell(new Phrase("Revenue Reciepts", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      reveRecePrev.setHeader(true);
	      reveRecePrev.setColspan(1);
	      reveRecePrev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(reveRecePrev);
	      //datatable.addCell("Header 7");
	      
	      Cell valPrev = new Cell(new Phrase("Valuation", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      valPrev.setHeader(true);
	      valPrev.setColspan(1);
	      valPrev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(valPrev);
	      
	      //datatable.addCell("Header 8");
	      Cell docPrevGro = new Cell(new Phrase("Comparative Growth in Documents from previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docPrevGro.setHeader(true);
	      docPrevGro.setColspan(1);
	      docPrevGro.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(docPrevGro);
	      
	      //datatable.addCell("Header 9");
	      Cell docRevGro = new Cell(new Phrase("Comparative Growth in Revenue from previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docRevGro.setHeader(true);
	      docRevGro.setColspan(1);
	      docRevGro.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(docRevGro);
	      
	      Cell docValGro = new Cell(new Phrase("Comparative Growth in Valuation from previous Year", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      docValGro.setHeader(true);
	      docValGro.setColspan(1);
	      docValGro.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(docValGro);
	      
	      	      
	      Cell noDocRev = new Cell(new Phrase("No of Docs", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      noDocRev.setHeader(true);
	      noDocRev.setColspan(1);
	      noDocRev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(noDocRev);
	      
	      //datatable.addCell("Header 6");
	      
	      Cell reveReceRev = new Cell(new Phrase("Revenue Reciepts", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      reveReceRev.setHeader(true);
	      reveReceRev.setColspan(1);
	      reveReceRev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(reveReceRev);
	      //datatable.addCell("Header 7");
	      
	      Cell valRev = new Cell(new Phrase("Valuation", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      valRev.setHeader(true);
	      valRev.setColspan(1);
	      valRev.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(valRev);
	      
	      Cell effFactor = new Cell(new Phrase("Effecting Factor", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	      effFactor.setHeader(true);
	      effFactor.setColspan(1);
	      effFactor.setBackgroundColor(new Color(196,255,255));
	      datatable.addCell(effFactor);
	      
	      datatable.endHeaders();
	      //datatable.setDefaultCellBorderWidth(1);

	      for(int i=0;i<200;i++){
	     //   datatable.setDefaultHorizontalAlignment(Element.ALIGN_LEFT);
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
	      }
	      datatable.setCellsFitPage(true);
	      document.add(datatable);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    document.close();
	  }
}

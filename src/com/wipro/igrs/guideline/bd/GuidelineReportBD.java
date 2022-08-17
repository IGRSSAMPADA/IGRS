package com.wipro.igrs.guideline.bd;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.util.JRLoader;
import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.guideline.dao.GuidelineReportDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.jasperBean.JasperBean;
import com.wipro.igrs.util.PropertiesFileReader;

public class GuidelineReportBD {
	GuidelineReportDAO repDAO = new GuidelineReportDAO();
	
	
	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getFinancialYear() throws Exception{
		ArrayList finacialYearList = new ArrayList();
		
		ArrayList list = repDAO.getFinancialYear();
		for(int i = 0 ; i < list.size(); i++)
		{
			GuidelineDTO gDTO = new GuidelineDTO();
			ArrayList subList = (ArrayList)list.get(i);
			
			gDTO.setFinancialYear((String)subList.get(0));
			finacialYearList.add(gDTO);
		}
		return finacialYearList;
	}
	
	/**
	 * 
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String lang) throws Exception
	{
		ArrayList districtList = new ArrayList();
		
		ArrayList list = repDAO.getDistrict();
		for(int i = 0 ; i < list.size(); i++)
		{
			GuidelineDTO gDTO = new GuidelineDTO();
			ArrayList subList = (ArrayList)list.get(i);
			
			gDTO.setDistrictID((String)subList.get(0));
			if(lang.equalsIgnoreCase("en"))
			{
				gDTO.setDistrict((String)subList.get(1));
			}
			else
			{
				gDTO.setDistrict((String)subList.get(2));
			}
			
			
			districtList.add(gDTO);
		}
		return districtList;
	}
	
	/**
	 * 
	 * @param financialYear
	 * @param disttID
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAvialableGuidelines(String financialYear, String disttID) throws Exception
	{
		ArrayList guideLineList = new ArrayList();
		
		ArrayList list = repDAO.getAvialableGuidelines(financialYear, disttID);
		for(int i = 0 ; i < list.size(); i++)
		{
			GuidelineDTO gDTO = new GuidelineDTO();
			ArrayList subList = (ArrayList)list.get(i);
			
			gDTO.setGuideLineID((String)subList.get(0));
			gDTO.setDurationFrom((String)subList.get(1));
			gDTO.setDurationTo((String)subList.get(2));
			gDTO.setFinancialYear((String)subList.get(3));
			
			guideLineList.add(gDTO);
		}
		return guideLineList;
	}
	
	
	/**
	 * 
	 * @param disttID
	 * @return String
	 * @throws Exception
	 */
	public String getFinalGuidelineDetls(String disttID, GuidelineDTO gDTO) throws Exception
	{
		ArrayList finalGuidelineList = repDAO.getFinalGuidelineDetls(disttID);
		String finalGuideline = "";
		if(finalGuidelineList.size() == 0)
		{
			finalGuideline = "-";
		}
		else
		{
			for(int i = 0 ; i < finalGuidelineList.size(); i++)
			{
				
				ArrayList subList = (ArrayList)finalGuidelineList.get(i);
				if(subList.get(0) == "")
				{
					finalGuideline = "-";
				}
				else
				{
					String guidelineID = (String)subList.get(0);
					gDTO.setFinalGuideline(guidelineID);
					String finanYear = (String)subList.get(1);
					gDTO.setFinanYearFinalGuideline(finanYear);
					finalGuideline = guidelineID+"("+finanYear+")";
				}
				
			}
		}
		
		return finalGuideline;
	}
	
	/**
	 * 
	 * @param glDTO
	 * @param eForm
	 * @return HashMap
	 * @throws Exception
	 */
	public LinkedHashMap getGuidelineDataPraroop1 (GuidelineDTO glDTO, GuidelinePreparationForm eForm, String lang)throws Exception  {
		return repDAO.getGuidelineDataPraroop1(glDTO, eForm,lang);
	}
	
	/**
	 * 
	 * @param glDTO
	 * @param eForm
	 * @return HashMap
	 * @throws Exception
	 */
	
	public LinkedHashMap getGuidelineDataPraroop2 (GuidelineDTO glDTO, GuidelinePreparationForm eForm, String lang)throws Exception  {
		return repDAO.getGuidelineDataPraroop2(glDTO, eForm,lang);
		
	}
		
	/**
	 * 
	 * @param glDTO
	 * @param eForm
	 * @return HashMap
	 * @throws Exception
	 */
		public LinkedHashMap getGuidelineDataPraroop3 (GuidelineDTO glDTO, GuidelinePreparationForm eForm, String lang)throws Exception  {
			return repDAO.getGuidelineDataPraroop3(glDTO, eForm,lang);
	}
		
	/**
	 * 
	 * @param glDTO
	 * @param eForm
	 * @return HashMap
	 * @throws Exception
	*/
		public LinkedHashMap getGuidelineDataPraroop(GuidelineDTO glDTO, GuidelinePreparationForm eForm,String lang) throws Exception{
			eForm.setGuidelineDataMapPraroop1(repDAO.getGuidelineDataPraroop1(glDTO, eForm,lang));
			eForm.setGuidelineDataMapPraroop2(repDAO.getGuidelineDataPraroop2(glDTO, eForm,lang));
			return repDAO.getGuidelineDataPraroop3(glDTO, eForm,lang);
		}
		
	/**
	 * 	
	 * @param eForm
	 * @param request
	 * @param response
	 * @return GuidelinePreparationForm
	 * @throws Exception
	 */
		
		public GuidelinePreparationForm printToPdfPraroop1(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String locale, String projPath) throws Exception
		{
			
			
			try{
				HashMap praroop1Map = new HashMap();
				HashMap praroop2Map = new HashMap();
				HashMap praroop3Map = new HashMap();
				if(locale.equalsIgnoreCase("en")){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4,20,20,20,20);
				PdfWriter.getInstance(document, baos);
				document.open();
			    Table datatable = new Table(40);
			    System.out.println("*****PDF"+document.newPage()); 		      
			    datatable.setWidth(100);
			    datatable.setPadding(3);
			   
			    Cell title = new Cell(new Phrase("GUIDELINE COMPARISON REPORT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			    title.setHorizontalAlignment(Element.ALIGN_CENTER);
			    title.setLeading(20);
			    title.setColspan(40);
			    title.setBorder(Rectangle.NO_BORDER);
			    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(title);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle1.setLeading(20);
			    subTitle1.setColspan(40);
			    subTitle1.setBorder(Rectangle.NO_BORDER);
			    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR OF DRAFT GUIDELINE - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle2.setLeading(20);
			    subTitle2.setColspan(40);
			    subTitle2.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleFY = new Cell(new Phrase("FINANCIAL YEAR OF CURRENT GUIDELINE - "+ eForm.getGuideDTO().getFinanYearFinalGuideline(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitleFY.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleFY.setLeading(20);
			    subTitleFY.setColspan(40);
			    subTitleFY.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleFY);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell sectionheader=new Cell(new Phrase("Praroop 1", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheader.setColspan(40);
			    sectionheader.setBorder(Rectangle.NO_BORDER);
			    sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(sectionheader);
			    datatable.setAlignment(1);
			    
			    
			    Cell blankrow=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow.setColspan(40);
			    blankrow.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(blankrow);
			    datatable.setAlignment(1);
			    
			    Cell blankcell=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankcell.setColspan(2);
			    blankcell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(blankcell);
			    datatable.setAlignment(1);

			    
			    Cell subTitle3 = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle3.setLeading(20);
			    subTitle3.setColspan(4);
			    subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle10 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle10.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle10.setLeading(20);
			    subTitle10.setColspan(4);
			    subTitle10.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle10);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle11 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(4);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    
			    Cell subTitle4 = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle4.setLeading(20);
			    subTitle4.setColspan(4);
			    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle5 = new Cell(new Phrase("MOHALLA COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle5.setLeading(20);
			    subTitle5.setColspan(4);
			    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle6 = new Cell(new Phrase("RESIDENTIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle6.setLeading(20);
			    subTitle6.setColspan(6);
			    subTitle6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle7 = new Cell(new Phrase("COMMERCIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle7.setLeading(20);
			    subTitle7.setColspan(6);
			    subTitle7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    /*Cell subTitle8 = new Cell(new Phrase("PLOT UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle8.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle8.setLeading(20);
			    subTitle8.setColspan(9);
			    subTitle8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle8);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
			    
			    Cell subTitle9 = new Cell(new Phrase("INDUSTRIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle9.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle9.setLeading(20);
			    subTitle9.setColspan(6);
			    subTitle9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle9);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell2.setLeading(20);
			    blankCell2.setColspan(2);
			    datatable.addCell(blankCell2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle13 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle13.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle13.setLeading(20);
			    subTitle13.setColspan(4);
			    datatable.addCell(subTitle13);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP2.setLeading(20);
			    blankCellP2.setColspan(4);
			    datatable.addCell(blankCellP2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP3.setLeading(20);
			    blankCellP3.setColspan(4);
			    datatable.addCell(blankCellP3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP8 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP8.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP8.setLeading(20);
			    blankCellP8.setColspan(4);
			    datatable.addCell(blankCellP8);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP9 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP9.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP9.setLeading(20);
			    blankCellP9.setColspan(4);
			    datatable.addCell(blankCellP9);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP4.setLeading(20);
			    blankCellP4.setColspan(6);
			    datatable.addCell(blankCellP4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP6.setLeading(20);
			    blankCellP6.setColspan(6);
			    datatable.addCell(blankCellP6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP7 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP7.setLeading(20);
			    blankCellP7.setColspan(6);
			    datatable.addCell(blankCellP7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			  /*  Cell blankCellP5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP5.setLeading(20);
			    blankCellP5.setColspan(6);
			    datatable.addCell(blankCellP5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
			    
			    /*Cell subTitle11 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(3);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle12 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle12.setLeading(20);
			    subTitle12.setColspan(3);
			    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle13 = new Cell(new Phrase("NORMAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle13.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle13.setLeading(20);
			    subTitle13.setColspan(3);
			    subTitle13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle13);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
			    
			  /*  Cell subTitle14 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    subTitle14.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle14.setLeading(20);
			    subTitle14.setColspan(6);
			    datatable.addCell(subTitle14);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
				
			    praroop1Map = eForm.getGuidelineDataMapPraroop1();
			    
			    Set mapSet = (Set)praroop1Map.entrySet();
				Iterator mapIterator = mapSet.iterator();
				while(mapIterator.hasNext())
				{
					Map.Entry mapEntry = (Map.Entry)mapIterator.next();
					String key[] = ((String)mapEntry.getKey()).split("~");
					String tehsil = key[1];
					String ward = key[4];
					String mohalla = key[5];
					String areaType = key[2];
					String subAreaType = key[3];
					
					Cell guideVal = new Cell(new Phrase("DRAFT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal.setLeading(20);
					guideVal.setColspan(2);
					datatable.addCell(guideVal);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
					
					Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue.setLeading(20);
					tehValue.setColspan(4);
					datatable.addCell(tehValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell areaValue = new Cell(new Phrase(areaType, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    areaValue.setLeading(20);
				    areaValue.setColspan(4);
					datatable.addCell(areaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subAreaValue = new Cell(new Phrase(subAreaType, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subAreaValue.setLeading(20);
				    subAreaValue.setColspan(4);
					datatable.addCell(subAreaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue = new Cell(new Phrase(ward, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue.setLeading(20);
				    wardValue.setColspan(4);
				    datatable.addCell(wardValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue = new Cell(new Phrase(mohalla, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue.setLeading(20);
				    mohallaValue.setColspan(4);
				    datatable.addCell(mohallaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				
				    ArrayList list = (ArrayList)mapEntry.getValue();
				    for(int i = 0 ; i < list.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(6);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    
				    }
				    
				    Cell guideVal1 = new Cell(new Phrase("CURRENT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal1.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal1.setLeading(20);
					guideVal1.setColspan(2);
					datatable.addCell(guideVal1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell tehValue1 = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue1.setLeading(20);
					tehValue1.setColspan(4);
					datatable.addCell(tehValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell areaValue1 = new Cell(new Phrase(areaType, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    areaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    areaValue1.setLeading(20);
				    areaValue1.setColspan(4);
					datatable.addCell(areaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subAreaValue1 = new Cell(new Phrase(subAreaType, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    subAreaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subAreaValue1.setLeading(20);
				    subAreaValue1.setColspan(4);
					datatable.addCell(subAreaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue1 = new Cell(new Phrase(ward, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue1.setLeading(20);
				    wardValue1.setColspan(4);
				    datatable.addCell(wardValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue1 = new Cell(new Phrase(mohalla, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue1.setLeading(20);
				    mohallaValue1.setColspan(4);
				    datatable.addCell(mohallaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    for(int i = 0 ; i < list.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuidelineFinalValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(6);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    
				    }
				}  datatable.setCellsFitPage(true);
			      document.add(datatable);
			    
				    document.close();
						response.setContentType("guideline/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineComprisonPraroop1.pdf");
						response.setContentLength(baos.size());
						ServletOutputStream out = response.getOutputStream();
						baos.writeTo(out);
						out.flush();
				}
				else{
					 praroop1Map = eForm.getGuidelineDataMapPraroop1();
					    ArrayList jasper =new ArrayList();
					    Set mapSet = (Set)praroop1Map.entrySet();
						Iterator mapIterator = mapSet.iterator();
						while(mapIterator.hasNext())
						{
							JasperBean jb=new JasperBean();
						
							Map.Entry mapEntry = (Map.Entry)mapIterator.next();
							String key[] = ((String)mapEntry.getKey()).split("~");
							String tehsil = key[1];
							String ward = key[4];
							String mohalla = key[5];
							String areaType = key[2];
							String subAreaType = key[3];
							
							jb.setGuideType("प्रारूप");
							jb.setPlotTehsil(tehsil);
							jb.setPlotAreaName(areaType);
							jb.setPlotSubAreaName(subAreaType);
							jb.setPlotWard(ward);
							jb.setPlotMohallaColony(mohalla);
							jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
							jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
							 ArrayList list = (ArrayList)mapEntry.getValue();
							 GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
								jb.setPlotResi(gDTO1.getGuideLineValue());
								GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
								jb.setPlotComm(gDTO2.getGuideLineValue());
								GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
								jb.setPlotIndus(gDTO3.getGuideLineValue());
								   jasper.add(jb);
								   jb.setJasperList(jasper);
								   
								  jb=new JasperBean();
								  jb.setGuideType("मौजूदा");
								  jb.setPlotTehsil(tehsil);
								  jb.setPlotAreaName(areaType);
								  jb.setPlotSubAreaName(subAreaType);
								  jb.setPlotWard(ward);
								  jb.setPlotMohallaColony(mohalla);
								  jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
								  jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
									 ArrayList listFinal = (ArrayList)mapEntry.getValue();
									 GuidelineDTO gDTO4 = (GuidelineDTO)listFinal.get(0);
										jb.setPlotResi(gDTO4.getGuidelineFinalValue());
										GuidelineDTO gDTO5 = (GuidelineDTO)listFinal.get(1);
										jb.setPlotComm(gDTO5.getGuidelineFinalValue());
										GuidelineDTO gDTO6 = (GuidelineDTO)listFinal.get(2);
										jb.setPlotIndus(gDTO6.getGuidelineFinalValue());
										   jasper.add(jb);
										   jb.setJasperList(jasper);				   
						}
						 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
					//	 String masterReportFileName = projPath+"\\JasperXML\\plotMasterReport.jrxml";
					     // String subReportFileName =projPath+"\\JasperXML\\plotSubReport.jrxml";
					     // String destFileName = projPath+"\\JasperXML\\plotReportView.JRprint";
						 
							String masterReportFileName =property.getValue("pdf.location").concat("plotMasterReport.jrxml");
							String subReportFileName =property.getValue("pdf.location").concat("plotSubReport.jrxml");
							String destFileName = property.getValue("pdf.location").concat("plotReportView.JRprint");
					      String path=property.getValue("pdf.location");
						 JRBeanCollectionDataSource beanColDataSource =
					            new JRBeanCollectionDataSource(jasper);
						 try{
							 JasperReport jasperMasterReport = JasperCompileManager
					            .compileReport(masterReportFileName);
					         JasperReport jasperSubReport = JasperCompileManager
					            .compileReport(subReportFileName);
					         File file = new File(destFileName);

					         Map<String, Object> parameters = new HashMap<String, Object>();
					         parameters.put("subreportParameter", jasperSubReport);
					         parameters.put("path", path);
					         //ServletOutputStream servletOutputStream = response.getOutputStream();  


					         JasperFillManager.fillReportToFile(jasperMasterReport,
					            destFileName, parameters, beanColDataSource);
					         JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
					         
					         ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
								/*JRPdfExporter exporter = new JRPdfExporter();
								System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
								exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
								exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
								exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");*/
								
								
					         JRPdfExporter exporter = new JRPdfExporter();
								System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
								exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
								exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
								exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
								exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
								exporter.exportReport();
									//try {
									//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File("myreport.pdf")) );
									//} catch (FileNotFoundException e) {
									//e.printStackTrace();
									//}
							/*	exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
								exporter.exportReport();*/
								System.out.println("Done--->");
								
								ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
						  		response.setContentType("application/pdf");
						  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop-1 Report.pdf");
						  		response.setContentLength(pdfReport.size());
						  		ServletOutputStream out = response.getOutputStream();
						  		os.writeTo(out);
						  		out.flush();
						  		out.close();
					         /*ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
					         JRPdfExporter exporter = new JRPdfExporter();
				        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
				        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				        		exporter.exportReport();
				        		//added by shruti
				        		ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				        		response.setContentType("application/pdf");
				        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
				        		response.setContentLength(pdfReport.size());
				        		ServletOutputStream out = response.getOutputStream();
				        		os.writeTo(out);
				        		out.flush();
				        		out.close();
				        		*/
					    /*    ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
					         JRPdfExporter exporter = new JRPdfExporter();
				        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pestReport1.pdf");  
				 		  	   exporter.exportReport();
				        		//added by shruti
				 		  	   
				 		  	   
				 		  	    ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				        		response.setContentType("application/pdf");
				        		response.setCharacterEncoding( BaseFont.IDENTITY_H);
				        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
				        		response.setContentLength(pdfReport.size());
				        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
				        		os.writeTo(out);
				        		out.flush();
				        		out.close();*/
					      
					      
					     // JasperViewer.viewReport(jasperPrint);
					      
					      
						 	
						 }catch(Exception e){
							 e.printStackTrace();

						 }
				}
				
				}catch(Exception e){
					
					e.printStackTrace();
					
				}
			
				      
				      return eForm;
			}	

	/**
	 * 
	 * @param eForm
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
public GuidelinePreparationForm printToPdfPraroop2(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String locale, String projPath) throws Exception
		{
			try{
				HashMap praroop1Map = new HashMap();
				HashMap praroop2Map = new HashMap();
				HashMap praroop3Map = new HashMap();
				if(locale.equalsIgnoreCase("en")){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4,20,20,20,20);
				PdfWriter.getInstance(document, baos);
				document.open();
			    Table datatable = new Table(44);
			     		      
			    datatable.setWidth(100);
			    datatable.setPadding(3);
			   
			    Cell title = new Cell(new Phrase("GUIDELINE COMPARISON REPORT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			    title.setHorizontalAlignment(Element.ALIGN_CENTER);
			    title.setLeading(20);
			    title.setColspan(44);
			    title.setBorder(Rectangle.NO_BORDER);
			    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(title);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle1.setLeading(20);
			    subTitle1.setColspan(44);
			    subTitle1.setBorder(Rectangle.NO_BORDER);
			    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR OF DRAFT GUIDELINE - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle2.setLeading(20);
			    subTitle2.setColspan(44);
			    subTitle2.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleFY = new Cell(new Phrase("FINANCIAL YEAR OF CURRENT GUIDELINE - "+ eForm.getGuideDTO().getFinanYearFinalGuideline(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitleFY.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleFY.setLeading(20);
			    subTitleFY.setColspan(44);
			    subTitleFY.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleFY);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell sectionheader2 = new Cell(new Phrase("Praroop 2", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheader2.setColspan(44);
			    sectionheader2.setBorder(Rectangle.NO_BORDER);
			    sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(sectionheader2);
			    datatable.setAlignment(1);
			    
			    Cell blankrow2=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow2.setColspan(44);
			    blankrow2.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(blankrow2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell.setLeading(20);
			    blankCell.setColspan(2);
			    blankCell.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(blankCell);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleTehsil = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleTehsil.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleTehsil.setLeading(20);
			    subTitleTehsil.setColspan(3);
			    subTitleTehsil.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleTehsil);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAreaType = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAreaType.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAreaType.setLeading(20);
			    subTitleAreaType.setColspan(3);
			    subTitleAreaType.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAreaType);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleSubAreaType = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleSubAreaType.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleSubAreaType.setLeading(20);
			    subTitleSubAreaType.setColspan(3);
			    subTitleSubAreaType.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleSubAreaType);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleWard = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleWard.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleWard.setLeading(20);
			    subTitleWard.setColspan(3);
			    subTitleWard.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleWard);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleMohalla = new Cell(new Phrase("MOHALLA/COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleMohalla.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleMohalla.setLeading(20);
			    subTitleMohalla.setColspan(3);
			    subTitleMohalla.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleMohalla);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg1 = new Cell(new Phrase("RESIDENTIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg1.setLeading(20);
			    subTitleBldg1.setColspan(12);
			    subTitleBldg1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg2 = new Cell(new Phrase("COMMERCIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg2.setLeading(20);
			    subTitleBldg2.setColspan(9);
			    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			   
			    Cell subTitleBldg5 = new Cell(new Phrase("MULTISTOREY(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg5.setLeading(20);
			    subTitleBldg5.setColspan(6);
			    subTitleBldg5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell1.setLeading(20);
			    blankCell1.setColspan(2);
			    datatable.addCell(blankCell1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankrow23 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankrow23.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow23.setLeading(20);
			    blankrow23.setColspan(3);
			    datatable.addCell(blankrow23);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellB2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCellB2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellB2.setLeading(20);
			    blankCellB2.setColspan(3);
			    datatable.addCell(blankCellB2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellB4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCellB4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellB4.setLeading(20);
			    blankCellB4.setColspan(3);
			    datatable.addCell(blankCellB4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellB5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCellB5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellB5.setLeading(20);
			    blankCellB5.setColspan(3);
			    datatable.addCell(blankCellB5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellB3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCellB3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellB3.setLeading(20);
			    blankCellB3.setColspan(3);
			    datatable.addCell(blankCellB3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    
			    Cell subTitleBldg6 = new Cell(new Phrase("RCC", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg6.setLeading(20);
			    subTitleBldg6.setColspan(3);
			    subTitleBldg6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg7 = new Cell(new Phrase("RBC/GARDER FARSHI", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg7.setLeading(20);
			    subTitleBldg7.setColspan(3);
			    subTitleBldg7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg8 = new Cell(new Phrase("TIN SHADE/ENGLISH TILES/LAKDI PART/ASBESTOS", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg8.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg8.setLeading(20);
			    subTitleBldg8.setColspan(3);
			    subTitleBldg8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg8);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg9 = new Cell(new Phrase("KACCHA KABELU/KHAPREL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg9.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg9.setLeading(20);
			    subTitleBldg9.setColspan(3);
			    subTitleBldg9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg9);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg10 = new Cell(new Phrase("SHOP", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg10.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg10.setLeading(20);
			    subTitleBldg10.setColspan(3);
			    subTitleBldg10.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg10);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg11 = new Cell(new Phrase("OFFICE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg11.setLeading(20);
			    subTitleBldg11.setColspan(3);
			    subTitleBldg11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg12 = new Cell(new Phrase("GODOWN", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg12.setLeading(20);
			    subTitleBldg12.setColspan(3);
			    subTitleBldg12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg13 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg13.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg13.setLeading(20);
			    subTitleBldg13.setColspan(3);
			    subTitleBldg13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg13);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleBldg14 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleBldg14.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleBldg14.setLeading(20);
			    subTitleBldg14.setColspan(3);
			    subTitleBldg14.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleBldg14);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    
			    
			    
			    praroop2Map = eForm.getGuidelineDataMapPraroop2();
			    
			    Set mapSet2 = (Set)praroop2Map.entrySet();
				Iterator mapIterator2 = mapSet2.iterator();
				while(mapIterator2.hasNext())
				{
					Map.Entry mapEntry2 = (Map.Entry)mapIterator2.next();
					String key2[] = ((String)mapEntry2.getKey()).split("~");
					String tehsil2 = key2[1];
					String area2 = key2[2];
					String subArea2 = key2[3];
					String ward2 = key2[4];
					String mohalla2 = key2[5];
					
					Cell guideVal = new Cell(new Phrase("DRAFT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal.setLeading(20);
					guideVal.setColspan(2);
					datatable.addCell(guideVal);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
					
					Cell tehValue2 = new Cell(new Phrase(tehsil2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue2.setLeading(20);
					tehValue2.setColspan(3);
					datatable.addCell(tehValue2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell areaValue2 = new Cell(new Phrase(area2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					areaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					areaValue2.setLeading(20);
					areaValue2.setColspan(3);
					datatable.addCell(areaValue2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell subAreaValue2 = new Cell(new Phrase(subArea2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					subAreaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					subAreaValue2.setLeading(20);
					subAreaValue2.setColspan(3);
					datatable.addCell(subAreaValue2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue2 = new Cell(new Phrase(ward2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue2.setLeading(20);
				    wardValue2.setColspan(3);
				    datatable.addCell(wardValue2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue2 = new Cell(new Phrase(mohalla2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue2.setLeading(20);
				    mohallaValue2.setColspan(3);
				    datatable.addCell(mohallaValue2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    ArrayList list2 = (ArrayList)mapEntry2.getValue();
				    for(int i = 0 ; i < list2.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list2.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(3);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
				 }
				    
				    Cell guideVal1= new Cell(new Phrase("CURRENT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal1.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal1.setLeading(20);
					guideVal1.setColspan(2);
					datatable.addCell(guideVal1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
					
					Cell tehValue = new Cell(new Phrase(tehsil2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue.setLeading(20);
					tehValue.setColspan(3);
					datatable.addCell(tehValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell areaValue = new Cell(new Phrase(area2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    areaValue.setLeading(20);
				    areaValue.setColspan(3);
					datatable.addCell(areaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subAreaValue = new Cell(new Phrase(subArea2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subAreaValue.setLeading(20);
				    subAreaValue.setColspan(3);
					datatable.addCell(subAreaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue = new Cell(new Phrase(ward2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue.setLeading(20);
				    wardValue.setColspan(3);
				    datatable.addCell(wardValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue = new Cell(new Phrase(mohalla2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue.setLeading(20);
				    mohallaValue.setColspan(3);
				    datatable.addCell(mohallaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    for(int i = 0 ; i < list2.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list2.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuidelineFinalValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(3);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
				 }
				
			   }
				
				 datatable.setCellsFitPage(true);
			      document.add(datatable);
				    document.close();
						response.setContentType("guideline/pdf");
						response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineComparisonPraroop2.pdf");
						response.setContentLength(baos.size());
						ServletOutputStream out = response.getOutputStream();
						baos.writeTo(out);
						out.flush();
				}else{

					   praroop2Map = eForm.getGuidelineDataMapPraroop2();
						    ArrayList jasper =new ArrayList();
						    Set mapSet = (Set)praroop2Map.entrySet();
							Iterator mapIterator = mapSet.iterator();
							while(mapIterator.hasNext())
							{
								JasperBean jb=new JasperBean();
							
								Map.Entry mapEntry = (Map.Entry)mapIterator.next();
								String key[] = ((String)mapEntry.getKey()).split("~");
								String tehsil = key[1];
								String ward = key[4];
								String mohalla = key[5];
								String areaType = key[2];
								String subAreaType = key[3];
								
								jb.setGuideType("प्रारूप");
								jb.setBuildingTehsil(tehsil);
								jb.setBuildingAreaName(areaType);
								jb.setBuildingSubAreaName(subAreaType);
								jb.setBuildingWard(ward);
								jb.setBuildingMohallaColony(mohalla);
								jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
								jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
								 ArrayList list = (ArrayList)mapEntry.getValue();
								 GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
									jb.setBuildingRcc(gDTO1.getGuideLineValue());
									GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
									jb.setBuildingRbc(gDTO2.getGuideLineValue());
									GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
									jb.setBuildingtinShade(gDTO3.getGuideLineValue());
									GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
									jb.setBuildingKachaKab(gDTO4.getGuideLineValue());
									GuidelineDTO gDTO5 = (GuidelineDTO)list.get(4);
									jb.setBuildingShop(gDTO5.getGuideLineValue());
									GuidelineDTO gDTO6 = (GuidelineDTO)list.get(5);
									jb.setBuildingOffice(gDTO6.getGuideLineValue());
									GuidelineDTO gDTO7 = (GuidelineDTO)list.get(6);
									jb.setBuildingGodown(gDTO7.getGuideLineValue());
									GuidelineDTO gDTO8 = (GuidelineDTO)list.get(7);
									jb.setBuildingResi(gDTO8.getGuideLineValue());
									GuidelineDTO gDTO9 = (GuidelineDTO)list.get(8);
									jb.setBuildingComm(gDTO9.getGuideLineValue());
									   jasper.add(jb);
									   jb.setJasperList(jasper);
									   
									  jb=new JasperBean();
									  jb.setGuideType("मौजूदा");
									  jb.setBuildingTehsil(tehsil);
									  jb.setBuildingAreaName(areaType);
									  jb.setBuildingSubAreaName(subAreaType);
									  jb.setBuildingWard(ward);
									  jb.setBuildingMohallaColony(mohalla);
									  jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
									  jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
										 ArrayList list2 = (ArrayList)mapEntry.getValue();
										 GuidelineDTO gDTO = (GuidelineDTO)list2.get(0);
											jb.setBuildingRcc(gDTO.getGuidelineFinalValue());
											GuidelineDTO gDTO21 = (GuidelineDTO)list2.get(1);
											jb.setBuildingRbc(gDTO21.getGuidelineFinalValue());
											GuidelineDTO gDTO31 = (GuidelineDTO)list2.get(2);
											jb.setBuildingtinShade(gDTO31.getGuidelineFinalValue());
											GuidelineDTO gDTO41 = (GuidelineDTO)list2.get(3);
											jb.setBuildingKachaKab(gDTO41.getGuidelineFinalValue());
											GuidelineDTO gDTO51 = (GuidelineDTO)list2.get(4);
											jb.setBuildingShop(gDTO51.getGuidelineFinalValue());
											GuidelineDTO gDTO61 = (GuidelineDTO)list2.get(5);
											jb.setBuildingOffice(gDTO61.getGuidelineFinalValue());
											GuidelineDTO gDTO71 = (GuidelineDTO)list2.get(6);
											jb.setBuildingGodown(gDTO71.getGuidelineFinalValue());
											GuidelineDTO gDTO81 = (GuidelineDTO)list2.get(7);
											jb.setBuildingResi(gDTO81.getGuidelineFinalValue());
											GuidelineDTO gDTO91 = (GuidelineDTO)list2.get(8);
											jb.setBuildingComm(gDTO91.getGuidelineFinalValue());
											   jasper.add(jb);
											   jb.setJasperList(jasper);				   
							}
							 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
							// String masterReportFileName = projPath+"\\JasperXML\\buildingMasterReport.jrxml";
						   //   String subReportFileName =projPath+"\\JasperXML\\buildingSubReport.jrxml";
						   //   String destFileName = projPath+"\\JasperXML\\buildingReportView.JRprint";
							 String masterReportFileName =property.getValue("pdf.location").concat("buildingMasterReport.jrxml");
								String subReportFileName =property.getValue("pdf.location").concat("buildingSubReport.jrxml");
								String destFileName = property.getValue("pdf.location").concat("buildingReportView.JRprint");
							 String path=property.getValue("pdf.location");
							 JRBeanCollectionDataSource beanColDataSource =
						            new JRBeanCollectionDataSource(jasper);
							 try{
								 JasperReport jasperMasterReport = JasperCompileManager
						            .compileReport(masterReportFileName);
						         JasperReport jasperSubReport = JasperCompileManager
						            .compileReport(subReportFileName);
						         File file = new File(destFileName);

						         Map<String, Object> parameters = new HashMap<String, Object>();
						         parameters.put("subreportParameter", jasperSubReport);
						         parameters.put("path", path);
						         //ServletOutputStream servletOutputStream = response.getOutputStream();  


						         JasperFillManager.fillReportToFile(jasperMasterReport,
						            destFileName, parameters, beanColDataSource);
						         JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
						         
						         ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
									JRPdfExporter exporter = new JRPdfExporter();
									System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
									exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
									exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
									exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
										//try {
										//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File("myreport.pdf")) );
										//} catch (FileNotFoundException e) {
										//e.printStackTrace();
										//}
									exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
									exporter.exportReport();
									System.out.println("Done--->");
									
									ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
							  		response.setContentType("application/pdf");
							  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop-2 Report.pdf");
							  		response.setContentLength(pdfReport.size());
							  		ServletOutputStream out = response.getOutputStream();
							  		os.writeTo(out);
							  		out.flush();
							  		out.close();
						       /* ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
						         JRPdfExporter exporter = new JRPdfExporter();
					        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
					        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
					        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
					        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
					 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\buildingReport.pdf");  
					 		  	   exporter.exportReport();
					        		//added by shruti
					 		  	   
					 		  	   
					 		  	    ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
					        		response.setContentType("application/pdf");
					        		response.setCharacterEncoding( BaseFont.IDENTITY_H);
					        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
					        		response.setContentLength(pdfReport.size());
					        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
					        		os.writeTo(out);
					        		out.flush();
					        		out.close();*/
						      
						      
						     // JasperViewer.viewReport(jasperPrint);
						      
						      
							 	
							 }catch(Exception e){
								 e.printStackTrace();

							 }
				}
				}catch(Exception e){
					
					e.printStackTrace();
					
				}
			
				      
				      return eForm;
			
		}	


/**
 * 
 * @param eForm
 * @param request
 * @param response
 * @return GuidelinePreparationForm
 * @throws Exception
 */
public GuidelinePreparationForm printToPdfPraroop3(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String locale, String projPath) throws Exception
{
	try{
				HashMap praroop1Map = new HashMap();
				HashMap praroop2Map = new HashMap();
				HashMap praroop3Map = new HashMap();
				if(locale.equalsIgnoreCase("en")){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				Document document = new Document(PageSize.A4,20,20,20,20);
				PdfWriter.getInstance(document, baos);
				document.open();
			    Table datatable = new Table(29);
			    System.out.println("*****PDF"+document.newPage()); 		      
			    datatable.setWidth(100);
			    datatable.setPadding(3);
			   
			    Cell title = new Cell(new Phrase("GUIDELINE COMPARISON REPORT", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			    title.setHorizontalAlignment(Element.ALIGN_CENTER);
			    title.setLeading(20);
			    title.setColspan(29);
			    title.setBorder(Rectangle.NO_BORDER);
			    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(title);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle1.setLeading(20);
			    subTitle1.setColspan(29);
			    subTitle1.setBorder(Rectangle.NO_BORDER);
			    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR OF DRAFT GUIDELINE - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle2.setLeading(20);
			    subTitle2.setColspan(29);
			    subTitle2.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleFY = new Cell(new Phrase("FINANCIAL YEAR OF CURRENT GUIDELINE - "+ eForm.getGuideDTO().getFinanYearFinalGuideline(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitleFY.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleFY.setLeading(20);
			    subTitleFY.setColspan(29);
			    subTitleFY.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleFY);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    
			    Cell sectionheader3=new Cell(new Phrase("Praroop 3", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
				sectionheader3.setColspan(29);
				sectionheader3.setBorder(Rectangle.NO_BORDER);
				sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(sectionheader3);
			    datatable.setAlignment(1);
			    
			   
			    Cell blankrow4=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow4.setColspan(29);
			    blankrow4.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(blankrow4);
			    datatable.setAlignment(1);
			    
			   
			    
			    Cell blankCell1=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell1.setColspan(2);
			    blankCell1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(blankCell1);
			    datatable.setAlignment(1);
			    
			    Cell subTitleTeh = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleTeh.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleTeh.setLeading(20);
			    subTitleTeh.setColspan(3);
			    subTitleTeh.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleTeh);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleArea = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleArea.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleArea.setLeading(20);
			    subTitleArea.setColspan(3);
			    subTitleArea.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleArea);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleSubArea = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleSubArea.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleSubArea.setLeading(20);
			    subTitleSubArea.setColspan(3);
			    subTitleSubArea.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleSubArea);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleWard2 = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleWard2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleWard2.setLeading(20);
			    subTitleWard2.setColspan(3);
			    subTitleWard2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleWard2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleMohalla2 = new Cell(new Phrase("MOHALLA/COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleMohalla2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleMohalla2.setLeading(20);
			    subTitleMohalla2.setColspan(3);
			    subTitleMohalla2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleMohalla2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAgri1 = new Cell(new Phrase("IRRIGATED(HECTARE)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAgri1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAgri1.setLeading(20);
			    subTitleAgri1.setColspan(3);
			    subTitleAgri1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAgri1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAgri2 = new Cell(new Phrase("UNIRRIGATED(HECTARE)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAgri2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAgri2.setLeading(20);
			    subTitleAgri2.setColspan(3);
			    subTitleAgri2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAgri2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAgri3 = new Cell(new Phrase("LAND UPTO 300(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAgri3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAgri3.setLeading(20);
			    subTitleAgri3.setColspan(6);
			    subTitleAgri3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAgri3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellT = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCellT.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellT.setLeading(20);
			    blankCellT.setColspan(2);
			    datatable.addCell(blankCellT);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell.setLeading(20);
			    blankCell.setColspan(3);
			    datatable.addCell(blankCell);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell2.setLeading(20);
			    blankCell2.setColspan(3);
			    datatable.addCell(blankCell2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell6.setLeading(20);
			    blankCell6.setColspan(3);
			    datatable.addCell(blankCell6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell7 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell7.setLeading(20);
			    blankCell7.setColspan(3);
			    datatable.addCell(blankCell7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell3.setLeading(20);
			    blankCell3.setColspan(3);
			    datatable.addCell(blankCell3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell4.setLeading(20);
			    blankCell4.setColspan(3);
			    datatable.addCell(blankCell4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCell5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    blankCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCell5.setLeading(20);
			    blankCell5.setColspan(3);
			    datatable.addCell(blankCell5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAgri11 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAgri11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAgri11.setLeading(20);
			    subTitleAgri11.setColspan(3);
			    subTitleAgri11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAgri11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitleAgri12 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitleAgri12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitleAgri12.setLeading(20);
			    subTitleAgri12.setColspan(3);
			    subTitleAgri12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitleAgri12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    
			    praroop3Map = eForm.getGuidelineDataMapPraroop3();
			    
			    Set mapSet3 = (Set)praroop3Map.entrySet();
				Iterator mapIterator3 = mapSet3.iterator();
				while(mapIterator3.hasNext())
				{
					Map.Entry mapEntry = (Map.Entry)mapIterator3.next();
					String key[] = ((String)mapEntry.getKey()).split("~");
					String tehsil = key[1];
					String area = key[2];
					String subArea = key[3];
					String ward = key[4];
					String mohalla = key[5];
					
					Cell guideVal = new Cell(new Phrase("DRAFT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal.setLeading(20);
					guideVal.setColspan(2);
					datatable.addCell(guideVal);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue.setLeading(20);
					tehValue.setColspan(3);
					datatable.addCell(tehValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell areaValue = new Cell(new Phrase(area, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					areaValue.setLeading(20);
					areaValue.setColspan(3);
					datatable.addCell(areaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell subAreaValue = new Cell(new Phrase(subArea, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					subAreaValue.setLeading(20);
					subAreaValue.setColspan(3);
					datatable.addCell(subAreaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue = new Cell(new Phrase(ward, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue.setLeading(20);
				    wardValue.setColspan(3);
				    datatable.addCell(wardValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue = new Cell(new Phrase(mohalla, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue.setLeading(20);
				    mohallaValue.setColspan(3);
				    datatable.addCell(mohallaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    ArrayList list = (ArrayList)mapEntry.getValue();
				    for(int i = 0 ; i < list.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(3);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    
				    }
				    
				    Cell guideVal1 = new Cell(new Phrase("CURRENT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					guideVal1.setHorizontalAlignment(Element.ALIGN_CENTER);
					guideVal1.setLeading(20);
					guideVal1.setColspan(2);
					datatable.addCell(guideVal1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell tehValue1 = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue1.setLeading(20);
					tehValue1.setColspan(3);
					datatable.addCell(tehValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    
					Cell areaValue1 = new Cell(new Phrase(area, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					areaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
					areaValue1.setLeading(20);
					areaValue1.setColspan(3);
					datatable.addCell(areaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
					Cell subAreaValue1 = new Cell(new Phrase(subArea, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					subAreaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
					subAreaValue1.setLeading(20);
					subAreaValue1.setColspan(3);
					datatable.addCell(subAreaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue1 = new Cell(new Phrase(ward, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    wardValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue1.setLeading(20);
				    wardValue1.setColspan(3);
				    datatable.addCell(wardValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue1 = new Cell(new Phrase(mohalla, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    mohallaValue1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    mohallaValue1.setLeading(20);
				    mohallaValue1.setColspan(3);
				    datatable.addCell(mohallaValue1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    for(int i = 0 ; i < list.size() ; i++)
				    {
				    	GuidelineDTO gDTO = (GuidelineDTO)list.get(i);
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuidelineFinalValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(3);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    
				    }
				} 
		    
		    datatable.setCellsFitPage(true);
		      document.add(datatable);
		    document.close();
				response.setContentType("guideline/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineComparisonPraroop3.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
				}else{


					 praroop3Map = eForm.getGuidelineDataMapPraroop3();
					    ArrayList jasper =new ArrayList();
					    Set mapSet = (Set)praroop3Map.entrySet();
						Iterator mapIterator = mapSet.iterator();
						while(mapIterator.hasNext())
						{
							JasperBean jb=new JasperBean();
						
							Map.Entry mapEntry = (Map.Entry)mapIterator.next();
							String key[] = ((String)mapEntry.getKey()).split("~");
							String tehsil = key[1];
							String ward = key[4];
							String mohalla = key[5];
							String areaType = key[2];
							String subAreaType = key[3];
							
							jb.setGuideType("प्रारूप");
							jb.setAgriTehsil(tehsil);
							jb.setAgriAreaName(areaType);
							jb.setAgriSubAreaName(subAreaType);
							jb.setAgriWard(ward);
							jb.setAgriMohallaColony(mohalla);
							jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
							jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
							 ArrayList list = (ArrayList)mapEntry.getValue();
							 GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
								jb.setAgriIrri(gDTO1.getGuideLineValue());
								GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
								jb.setAgriUnIrri(gDTO2.getGuideLineValue());
								GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
								jb.setAgriLandResi(gDTO3.getGuideLineValue());
								GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
								jb.setAgriLandComm(gDTO4.getGuideLineValue());
								   jasper.add(jb);
								   jb.setJasperList(jasper);
								   
								  jb=new JasperBean();
								  jb.setGuideType("मौजूदा");
								  jb.setAgriTehsil(tehsil);
								  jb.setAgriAreaName(areaType);
								  jb.setAgriSubAreaName(subAreaType);
								  jb.setAgriWard(ward);
								  jb.setAgriMohallaColony(mohalla);
								  jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
								  jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
									 ArrayList listFinal = (ArrayList)mapEntry.getValue();
									 GuidelineDTO gDTO5 = (GuidelineDTO)listFinal.get(0);
										jb.setAgriIrri(gDTO5.getGuidelineFinalValue());
										GuidelineDTO gDTO6 = (GuidelineDTO)listFinal.get(1);
										jb.setAgriUnIrri(gDTO6.getGuidelineFinalValue());
										GuidelineDTO gDTO7 = (GuidelineDTO)listFinal.get(2);
										jb.setAgriLandResi(gDTO7.getGuidelineFinalValue());
										GuidelineDTO gDTO8 = (GuidelineDTO)listFinal.get(3);
										jb.setAgriLandComm(gDTO8.getGuidelineFinalValue());
										   jasper.add(jb);
										   jb.setJasperList(jasper);				   
						}
						 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");

				//		 String masterReportFileName = projPath+"\\JasperXML\\agriMasterReport.jrxml";
					//      String subReportFileName =projPath+"\\JasperXML\\agriSubReport.jrxml";
					 //     String destFileName = projPath+"\\JasperXML\\agriReportView.JRprint";
					      String masterReportFileName =property.getValue("pdf.location").concat("agriMasterReport.jrxml");
							String subReportFileName =property.getValue("pdf.location").concat("agriSubReport.jrxml");
							String destFileName = property.getValue("pdf.location").concat("agriReportView.JRprint");
							 String path=property.getValue("pdf.location");
							JRBeanCollectionDataSource beanColDataSource =
					            new JRBeanCollectionDataSource(jasper);
						 try{
							 JasperReport jasperMasterReport = JasperCompileManager
					            .compileReport(masterReportFileName);
					         JasperReport jasperSubReport = JasperCompileManager
					            .compileReport(subReportFileName);
					         File file = new File(destFileName);

					         Map<String, Object> parameters = new HashMap<String, Object>();
					         parameters.put("subreportParameter", jasperSubReport);
					         parameters.put("path", path);
					         //ServletOutputStream servletOutputStream = response.getOutputStream();  

					      
					         JasperFillManager.fillReportToFile(jasperMasterReport,
					            destFileName, parameters, beanColDataSource);
					         JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
					         
					       // ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
					        
					      /*   JRPdfExporter exporter = new JRPdfExporter();
				        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pestReport1.pdf");  
				 		  	   exporter.exportReport();
				 		 */
				 		  	   
				 		  	/*final JROdtExporter odtExporter = new JROdtExporter();
			        		final ByteArrayOutputStream odtStream = new ByteArrayOutputStream();
			        		odtExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,odtStream);
			        		odtExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			        		odtExporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
			        		odtExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
			        		odtExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pt1.odt");  
			        		odtExporter.exportReport();
			        		odtStream.close();*/
			        		
			        	
			        	//	JRPdfExporter exporter = new JRPdfExporter();
			        	//	exporter.setParameter(JRExporterParameter.INPUT_STREAM,odtStream);
			        		//JasperExportManager.exportReportToPdfFile(jasperPrint, "D://JRXML//reportpdf.pdf");
			        		//JasperExportManager.exportReportToHtmlFile(jasperPrint, "D://JRXML//reportpdf.pdf");
			        		//JasperExportManager.exportReportToPdfStream(jasperPrint, pdfReport);
				 		  	 /*   ByteArrayOutputStream os = (ByteArrayOutputStream)odtExporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				        		//response.setContentType("application/vnd.oasis.opendocument.text"); //for ODT file
				 		 //	 ByteArrayInputStream b=new ByteArrayInputStream(os);
				 		  	  byte b[]= os.toByteArray();
				 		  	DMSUtility dms=new DMSUtility();
				 		  	
				 		  	
				 		  	
				 		   String fileName="D:\\JRXML\\ptertert.odt";
				 			File newFile = new File(fileName);
							FileOutputStream fos = new FileOutputStream(newFile);
							fos.write(b);
							fos.close();
							byte d[]=JasperExportManager.exportReportToPdf(jasperPrint);
						//	dms.downloadDocument(response, "pestReport1.pdf", d);
							//JasperExportManager.exportReportToPdfFile(fileName, "D:\\JRXML\\pdsdsd.pdf");
							
							
				 		  	ByteArrayOutputStream sd=new ByteArrayOutputStream();
				 		  	os.writeTo(sd);
				 		System.out.println(sd.toString()+"~"+sd.size());
				 		  	   System.out.println(os);
				 		  	  
				 		  		response.setContentType("application/pdf");	//for PDF
				        		response.setCharacterEncoding( BaseFont.IDENTITY_H);
				        		response.setHeader("Content-Disposition", "attachment; filename=\"reportss.pdf");
				        		response.setContentLength(odtStream.size());
				        			os.writeTo(out);				        	
				        		out.flush();
				        		out.close();
				        	
				        		// System.out.println(f2);
				        		 ServletOutputStream out = response.getOutputStream();
				        		 sd.writeTo(out); 
				        		 sd.flush();
				        		 sd.close();*/
				        		 
			        		/*response.setContentType("application/pdf");
			        		response.setCharacterEncoding( BaseFont.IDENTITY_H);
			        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
			        		response.setContentLength(odtStream.size());
			        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
			        		os.writeTo(out);
			        		out.flush();
			        		out.close();*/
				 		  /* 
				 		  	JRPdfExporter exporter = new JRPdfExporter();
				        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
				        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);
				 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pestReport1.pdf");  
				 		  	   exporter.exportReport();*/
				 		  	   
				 		  /*	OutputStream f2 = new FileOutputStream("repppp.pdf"); 
				 		  	os.writeTo(f2); 
				 		  	   System.out.println(os);
				 		  		response.setContentType("application/pdf");	//for PDF
				        		response.setCharacterEncoding( BaseFont.IDENTITY_H);
				        		response.setHeader("Content-Disposition", "attachment; filename=\"reportss.pdf");
				        		response.setContentLength(os.size());
				        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
				        		os.writeTo(out);				        	
				        		out.flush();
				        		out.close();*/
				        		
				        		
					      
					         ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
								JRPdfExporter exporter = new JRPdfExporter();
								System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
								exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
								exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
								exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
									//try {
									//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream(new File("myreport.pdf")) );
									//} catch (FileNotFoundException e) {
									//e.printStackTrace();
									//}
								exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
								exporter.exportReport();
								System.out.println("Done--->");
								
								ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
						  		response.setContentType("application/pdf");
						  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop-3 Report.pdf");
						  		response.setContentLength(pdfReport.size());
						  		ServletOutputStream out = response.getOutputStream();
						  		os.writeTo(out);
						  		out.flush();
						  		out.close();
					    //  JasperViewer.viewReport(jasperPrint);
					      
					      
						 	
						 }catch(Exception e){
							 e.printStackTrace();

						 }
			
				}

		}catch(Exception e){
			
			e.printStackTrace();
			
		}

		      
		      return eForm;
			
		}	
}


/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: GuidelineViewBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008
 * MODIFIED ON:    6th MAY 2008 
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE APPROVAL ACTION.
 */

package com.wipro.igrs.guideline.bd;

import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.log4j.Logger;

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
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.guideline.dao.GuideLineViewDAO;
import com.wipro.igrs.guideline.dao.GuidelineApprovalDAO;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;
import com.wipro.igrs.jasperBean.JasperBean;
import com.wipro.igrs.util.PropertiesFileReader;

/**
 * @author NIHAR M.
 *
 */
public class GuidelineViewBD {
	private Logger logger = Logger.getLogger(GuidelineViewBD.class);

	GuideLineViewDAO guideDAO = new GuideLineViewDAO();
	GuidelineApprovalDAO appdAO=new GuidelineApprovalDAO();
	/**
	 * @return ArrayList that holds the DistrictList
	 */
	public ArrayList getDistrictList(String lang) {
		return guideDAO.getDistrictList(lang);
	}

	/**
	 * @param distid
	 * @return ArrayList that holds the TehsilList
	 */
	public ArrayList  getTehsilList(String distid,String lang){
		//String distID[] = new String[1];
		//distID[0] = distid;
		return guideDAO.getTehsilList(distid,lang);
	}

	/**
	 * @param tehsilId
	 * @return ArrayList that holds the WardList
	 */
	public ArrayList getWardList(String tehsilId){

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getWardList(tehsilId);
	}

	
	/**
	 * @param tehsilId
	 * @return PatwariList
	 */
	public ArrayList getPatwariList(String tehsilId){

		//String tehsilID[] = new String[1];
		//tehsilID[0] = tehsilId;
		return guideDAO.getPatwariList(tehsilId);
	}
	
	
	/**
	 * @return ArrayList that holds the AreaTypes
	 */
	public ArrayList getAreasTypeList(){
		return guideDAO.getAreasTypeList();
	}

	/*
	 * BELOW CODE COMMENTED BY SIMRAN
	 */
	
	/**
	 * @param mohID
	 * @param baseFrom
	 * @param baseTo
	 * @return ArrayList that holds the Read Only Mohalla Details skip
	 */
	/*public ArrayList getIndividualMohallaDraftView(String mohID, String DistrictID,String finYear, int status){

		String[] param = new String[4];
		param[0] = mohID;
		param[1] = finYear;
		param[2] = DistrictID;
		
		
		return guideDAO.getIndividualMohallaDraftView(param, status);
	}*/


	/**
	 * @param wardId
	 * @param fromDate
	 * @param toDate
	 * @return ArrayList that holds the MOHALLA List
	 */
	public ArrayList getMohViewList(String wardID,String lang){

		//String wardID[] = new String[1];
		//wardID[0] = warray;
		//wardID[1] = fyear;

		return guideDAO.getMohViewList(wardID,lang);

	}

	/*
	 * BELOW CODE COMMENTED BY SIMRAN
	 */
	
	/**
	 * @param mohID
	 * @param finYear
	 * @return ArrayList
	 */
	/*public ArrayList getIndividualVillageDraftView(String vilID, String finYear){

		String[] param = new String[2];
		param[0] = vilID;
		param[1] = finYear;

		return guideDAO.getIndividualVillageDraftView(param);
	}
*/

	/**
	 * @param mohID
	 * @param baseFrom
	 * @param baseTo
	 * @return ArrayList that holds the Read Only Mohalla Details 
	 */
	/*public ArrayList getIndividualMohallaFinalView(String mohID,String disttID){

		String[] param = new String[2];
		
		param[0] = disttID;
		param[1] = mohID;

		return guideDAO.getIndividualMohallaFinalView(param);
	}*/


	/**
	 * @param mohID
	 * @param finYear
	 * @return ArrayList
	 */
	/*public ArrayList getIndividualVillageFinalView(String vilID, String finYear){

		String[] param = new String[2];
		param[0] = vilID;
		param[1] = finYear;

		return guideDAO.getIndividualVillageDraftView(param);
	}*/

	/*public ArrayList getFinancialYearList()
	{
		return guideDAO.getFinancialYearList();
	}
	
	public ArrayList getIndividualMohallaPendingView(String mohID, String finYear, int status){

		String[] param = new String[3];
		param[0] = mohID;
		param[1] = finYear;
		//param[2] = guideStatus;
		return guideDAO.getIndividualMohallaPendingView(param, status);
	}*/
	
	/**
	 * @return ArrayList tht holds Financial Years
	 */
	public ArrayList getActiveFinancialYearList()
	{
		return guideDAO.getActiveFinancialYearList();
	}
	
	
	
	/**
	 * @param gDTO
	 * @param type
	 * @return ArrayList that holds list of available versions on the basis of selected district,
	 * Financial Year and approval Type(Final/Draft)
	 */
	public ArrayList viewVersionsTemp(GuidelineDTO gDTO, String type)
	{
		return guideDAO.viewVersionsTemp(gDTO, type);
	}
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that returns Guideline Data for selected mohalla in selected guideline
	 */
	public ArrayList getIndividualMohallaViewNew(GuidelineDTO gDTO)
	{
		return guideDAO.getIndividualMohallaViewNew(gDTO);
	}
	
	
	/**
	 * 
	 * @param gDTO
	 * @return ArrayList that holds list of subClauses on the basis of property types and mohalla
	 */
	public ArrayList subClauseReadOnly (GuidelineDTO gDTO)
	{
		return guideDAO.subClauseReadOnly(gDTO);
	}
	
	/**
	 * 
	 * @param disttId
	 * @return HashMap that holds guidelineData for the whole Distt
	 * @throws Exception
	 */
	public HashMap getGuidelineData (GuidelineDTO glDTO, String lang)throws Exception  {
		return guideDAO.getGuidelineData(glDTO,lang);
	}
	
	public String getEmailContent(GuidelineDTO gDTO) throws Exception
	{
		return guideDAO.getEmailContent(gDTO);
	}
	
	public LinkedHashMap getGuidelineDataPraroop(GuidelineDTO gDTO, GuidelinePreparationForm eForm,String lang) throws Exception
	{
		eForm.setGuidelineDataMapPraroop1(guideDAO.getGuidelineDataPraroop1(gDTO,eForm,lang));
		eForm.setGuidelineDataMapPraroop2(guideDAO.getGuidelineDataPraroop2(gDTO,eForm,lang));
		return guideDAO.getGuidelineDataPraroop3(gDTO,eForm,lang);
	}
	
	public LinkedHashMap getGuidelineDataPraroop1(GuidelineDTO gDTO, GuidelinePreparationForm eForm,String lang) throws Exception
	{
		return guideDAO.getGuidelineDataPraroop1(gDTO,eForm,lang);
	}
	
	public LinkedHashMap getGuidelineDataPraroop2(GuidelineDTO gDTO, GuidelinePreparationForm eForm,String lang) throws Exception
	{
		return guideDAO.getGuidelineDataPraroop2(gDTO,eForm,lang);
	}
	
	public LinkedHashMap getGuidelineDataPraroop3(GuidelineDTO gDTO, GuidelinePreparationForm eForm,String lang) throws Exception
	{
		return guideDAO.getGuidelineDataPraroop3(gDTO,eForm,lang);
	}
	
	public boolean generateGuidePDF(GuidelineDTO formDTO,GuidelinePreparationForm eForm ,String guideID,HttpServletResponse response,String lang) throws Exception{
		//Step 1: Call Procedure
		//String randomNo=guideDAO.updateTempGuideline(guideID,formDTO.getDistrictID());
		logger.debug("TEMP TABLE ENTRIES RESET");
		//Step 2: Status Change
		
		logger.debug("STATUS UPDATED");
		//Step 3: generate PDF
		for(int i=0;i<=1;i++){
			if(i==0)lang="hi";
			else lang="en";
		generatePDF(formDTO,eForm,guideID, response, lang);
		}
		logger.debug("generatePDF SUCCESSFULL");
		//Step 4: Empty table
		guideDAO.emptyTempTable(guideID);
		appdAO.updateStatus(guideID);
		logger.debug("TEMP TABLE ENTRIES RESET");
		return true;
	}
	 public void generatePDF(GuidelineDTO formDTO,GuidelinePreparationForm eForm ,String guideID,HttpServletResponse response,String lang) {
			JasperReport jasperReport = null;
		 	JasperPrint jasperPrint = null;
		 	try{ 
		 		 String uploadDir="";
		         PropertiesFileReader prObj;
		 		try {
		 			prObj = PropertiesFileReader.getInstance("resources.igrs");
		 			uploadDir=prObj.getValue("GuidelineSavePath");
		 			System.out.println(uploadDir);
		 		} catch (Exception e1) {
		 			// TODO Auto-generated catch block
		 			e1.printStackTrace();
		 		}
		 		
		 		String filePath=uploadDir.replace("//", File.separator);
		 		 filePath=filePath+File.separator+eForm.getGuideDTO().getGuideLineID();
				File folder = new File(filePath);
				 if (!folder.exists()) 
				 {
					 System.out.println(folder.toString());
					
		              folder.mkdirs();
		            
			     }	
				
				 if(lang.equalsIgnoreCase("en")){
					 File folderH = new File(filePath.concat(File.separator+"ENGLISH"));
					 if (!folderH.exists()) 
					 {
						 System.out.println(folderH.toString());
						
						 folderH.mkdirs();
			            
				     }	 
					 filePath=filePath.concat(File.separator+"ENGLISH");
				 }else{
					 File folderH = new File(filePath.concat(File.separator+"HINDI"));
					 if (!folderH.exists()) 
					 {
						 System.out.println(folderH.toString());
						
						 folderH.mkdirs();
			            
				     }
					 filePath=filePath.concat(File.separator+"HINDI");
				 }
				 logger.debug("FOLDER STRUCTURE CREATED ");
		 		
		 		
		 		
		 	PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
		 	DBUtility dbUtil = new DBUtility();
		 	String path=prop.getValue("pdf.location");
			HashMap jasperMap=new HashMap();
			jasperMap.put("path", path);
			jasperMap.put("lang", lang);
		 	jasperMap.put("plotFinancialYear", eForm.getGuideDTO().getFinancialYear());
		 	jasperMap.put("guideID", guideID);
		 	jasperReport=JasperCompileManager.compileReport(prop.getValue("pdf.location").concat("guidelineNeww.jrxml"));	
			jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
		 	//JasperViewer.viewReport(jasperPrint);
			jasperPrint.setPageHeight(612);
			jasperPrint.setPageWidth(1008);
			ByteArrayOutputStream  pdfReportPlot = new ByteArrayOutputStream();
	        //JRXlsExporter exporterPlot = new JRXlsExporter();
	        JRPdfExporter exporterPlot = new JRPdfExporter();
	       
	      	  exporterPlot.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporterPlot.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
			  exporterPlot.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
			  exporterPlot.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReportPlot);											
			  exporterPlot.exportReport();
			//  JasperViewer.viewReport(plotJasperPrint);
			 
				byte bytePlot[]=pdfReportPlot.toByteArray();
				
				
				logger.debug("byte[] array ");
			
																	
					//File newFilePlot = new File(filePath.concat("\\PraroopPlot.pdf"));
					File newFilePlot = new File(filePath.concat(File.separator+"GuidelineFull.pdf"));
					FileOutputStream fosp = new FileOutputStream(newFilePlot);
					fosp.write(bytePlot);
					fosp.close();
					logger.debug("GuidelineFull.pdf saved ");
		  		
		  		dbUtil.closeConnection();
		 	}catch(Exception e){
		 			logger.debug("EXCEPTION"+e.getMessage()) ;
		 			}
	 }
	
	
public GuidelinePreparationForm printToPdf(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String lang, String projPath) throws Exception
{
	try{
		
			HashMap praroop1Map = new HashMap();
			HashMap praroop2Map = new HashMap();
			HashMap praroop3Map = new HashMap();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Document document = new Document(PageSize.A4,20,20,20,20);
			PdfWriter.getInstance(document, baos);
			document.open();
		    Table datatable = new Table(24);
		    System.out.println("*****PDF"+document.newPage()); 		      
		    datatable.setWidth(100);
		    datatable.setPadding(3);
		    
		    if(lang.equalsIgnoreCase("en"))
		    {
		    	Cell title = new Cell(new Phrase("GUIDELINE VIEW", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			    title.setHorizontalAlignment(Element.ALIGN_CENTER);
			    title.setLeading(20);
			    title.setColspan(24);
			    title.setBorder(Rectangle.NO_BORDER);
			    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(title);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle1.setLeading(20);
			    subTitle1.setColspan(24);
			    subTitle1.setBorder(Rectangle.NO_BORDER);
			    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle2.setLeading(20);
			    subTitle2.setColspan(24);
			    subTitle2.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell sectionheader=new Cell(new Phrase("Praroop 1", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheader.setColspan(24);
			    sectionheader.setBorder(Rectangle.NO_BORDER);
			    sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(sectionheader);
			    datatable.setAlignment(1);
			    
			    Cell sectionheaderDtls=new Cell(new Phrase("Guideline for Plots for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
			    sectionheaderDtls.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheaderDtls.setColspan(24);
			    sectionheaderDtls.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    sectionheaderDtls.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(sectionheaderDtls);
			    datatable.setAlignment(1);
			    
			   
			    
			    Cell blankrow=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow.setColspan(24);
			    blankrow.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(blankrow);
			    datatable.setAlignment(1);
			    
			    Cell subTitle3 = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle3.setLeading(20);
			    subTitle3.setColspan(3);
			    subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle4 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle4.setLeading(20);
			    subTitle4.setColspan(3);
			    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle5 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle5.setLeading(20);
			    subTitle5.setColspan(3);
			    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle11 = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(3);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle12 = new Cell(new Phrase("MOHALLA/COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle12.setLeading(20);
			    subTitle12.setColspan(3);
			    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle6 = new Cell(new Phrase("RESIDENTIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle6.setLeading(20);
			    subTitle6.setColspan(3);
			    subTitle6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle7 = new Cell(new Phrase("COMMERCIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle7.setLeading(20);
			    subTitle7.setColspan(3);
			    subTitle7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    /*Cell subTitle8 = new Cell(new Phrase("PLOT UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle8.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle8.setLeading(20);
			    subTitle8.setColspan(6);
			    subTitle8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle8);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
			    
			    Cell subTitle9 = new Cell(new Phrase("INDUSTRIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle9.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle9.setLeading(20);
			    subTitle9.setColspan(3);
			    subTitle9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle9);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle10 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle10.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle10.setLeading(20);
			    subTitle10.setColspan(3);
			    datatable.addCell(subTitle10);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP2.setLeading(20);
			    blankCellP2.setColspan(3);
			    datatable.addCell(blankCellP2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP3.setLeading(20);
			    blankCellP3.setColspan(3);
			    datatable.addCell(blankCellP3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP6.setLeading(20);
			    blankCellP6.setColspan(3);
			    datatable.addCell(blankCellP6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP7 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP7.setLeading(20);
			    blankCellP7.setColspan(3);
			    datatable.addCell(blankCellP7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP4.setLeading(20);
			    blankCellP4.setColspan(3);
			    datatable.addCell(blankCellP4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    blankCellP5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP5.setLeading(20);
			    blankCellP5.setColspan(3);
			    datatable.addCell(blankCellP5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			   /* Cell subTitle11 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(2);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle12 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle12.setLeading(20);
			    subTitle12.setColspan(2);
			    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle13 = new Cell(new Phrase("NORMAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle13.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle13.setLeading(20);
			    subTitle13.setColspan(2);
			    subTitle13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle13);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);*/
			    
			    Cell subTitle14 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    subTitle14.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle14.setLeading(20);
			    subTitle14.setColspan(3);
			    datatable.addCell(subTitle14);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
		    }
			    praroop1Map = eForm.getGuidelineDataMapPraroop1();
			    ArrayList jasper=new ArrayList();
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
					String areaName = key[2];
					String subAreaName = key[3];
					jb.setPlotTehsil(tehsil);
					jb.setPlotAreaName(areaName);
					jb.setPlotSubAreaName(subAreaName);
					jb.setPlotWard(ward);
					jb.setPlotMohallaColony(mohalla);
					jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
					jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
					
					Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue.setLeading(20);
					tehValue.setColspan(3);
					datatable.addCell(tehValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell areaValue = new Cell(new Phrase(areaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    areaValue.setLeading(20);
				    areaValue.setColspan(3);
				    datatable.addCell(areaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subAreaValue = new Cell(new Phrase(subAreaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
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
				    GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
					jb.setPlotResi(gDTO1.getGuideLineValue());
					GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
					jb.setPlotComm(gDTO2.getGuideLineValue());
					GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
					jb.setPlotIndus(gDTO3.getGuideLineValue());
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
				    jasper.add(jb);
					   jb.setJasperList(jasper);
				} 
				 
				   if(lang.equalsIgnoreCase("en"))
				    {	
					Cell blankrow10=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
					blankrow10.setHorizontalAlignment(Element.ALIGN_CENTER);
					blankrow10.setColspan(24);
					blankrow10.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow10);
				    datatable.setAlignment(1);
				    
				    
				    
				    Cell sectionheader2 = new Cell(new Phrase("Praroop 2", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				    sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheader2.setColspan(24);
				    sectionheader2.setBorder(Rectangle.NO_BORDER);
				    sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(sectionheader2);
				    datatable.setAlignment(1);
				    
				    Cell sectionheaderDtls2=new Cell(new Phrase("Guideline for Constructed Properties for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
				    sectionheaderDtls2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheaderDtls2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    sectionheaderDtls2.setColspan(24);
				    sectionheaderDtls2.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(sectionheaderDtls2);
				    datatable.setAlignment(1);
				    
				   
				    
				    Cell blankrow2=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow2.setColspan(24);
				    blankrow2.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleTehsil = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleTehsil.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleTehsil.setLeading(20);
				    subTitleTehsil.setColspan(1);
				    subTitleTehsil.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleTehsil);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAreaType2 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleAreaType2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAreaType2.setLeading(20);
				    subTitleAreaType2.setColspan(1);
				    subTitleAreaType2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAreaType2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleSubAreaType2 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleSubAreaType2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleSubAreaType2.setLeading(20);
				    subTitleSubAreaType2.setColspan(1);
				    subTitleSubAreaType2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleSubAreaType2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleWard = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleWard.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleWard.setLeading(20);
				    subTitleWard.setColspan(1);
				    subTitleWard.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleWard);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleMohalla = new Cell(new Phrase("MOHALLA COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleMohalla.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleMohalla.setLeading(20);
				    subTitleMohalla.setColspan(2);
				    subTitleMohalla.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleMohalla);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg1 = new Cell(new Phrase("RESIDENTIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg1.setLeading(20);
				    subTitleBldg1.setColspan(8);
				    subTitleBldg1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg2 = new Cell(new Phrase("COMMERCIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg2.setLeading(20);
				    subTitleBldg2.setColspan(6);
				    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    Cell subTitleBldg5 = new Cell(new Phrase("MULTISTOREY(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg5.setLeading(20);
				    subTitleBldg5.setColspan(4);
				    subTitleBldg5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg5);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankrow23 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankrow23.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow23.setLeading(20);
				    blankrow23.setColspan(1);
				    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(blankrow23);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCellB2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCellB2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB2.setLeading(20);
				    blankCellB2.setColspan(1);
				    datatable.addCell(blankCellB2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankrowB3= new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankrowB3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrowB3.setLeading(20);
				    blankrowB3.setColspan(1);
				    datatable.addCell(blankrowB3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCellB4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCellB4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB4.setLeading(20);
				    blankCellB4.setColspan(1);
				    datatable.addCell(blankCellB4);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    
				    Cell blankCellB5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCellB5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB5.setLeading(20);
				    blankCellB5.setColspan(2);
				    datatable.addCell(blankCellB5);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				   
				    
				    Cell subTitleBldg6 = new Cell(new Phrase("RCC", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg6.setLeading(20);
				    subTitleBldg6.setColspan(2);
				    subTitleBldg6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg6);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg7 = new Cell(new Phrase("RBC/GARDER FARSHI", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg7.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg7.setLeading(20);
				    subTitleBldg7.setColspan(2);
				    subTitleBldg7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg7);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg8 = new Cell(new Phrase("TIN SHADE/ENGLISH TILES/LAKDI PART/ASBESTOS", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg8.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg8.setLeading(20);
				    subTitleBldg8.setColspan(2);
				    subTitleBldg8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg8);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg9 = new Cell(new Phrase("KACCHA KABELU/KHAPREL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg9.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg9.setLeading(20);
				    subTitleBldg9.setColspan(2);
				    subTitleBldg9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg9);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg10 = new Cell(new Phrase("SHOP", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg10.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg10.setLeading(20);
				    subTitleBldg10.setColspan(2);
				    subTitleBldg10.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg10);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg11 = new Cell(new Phrase("OFFICE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg11.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg11.setLeading(20);
				    subTitleBldg11.setColspan(2);
				    subTitleBldg11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg11);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg12 = new Cell(new Phrase("GODOWN", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg12.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg12.setLeading(20);
				    subTitleBldg12.setColspan(2);
				    subTitleBldg12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg12);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg13 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg13.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg13.setLeading(20);
				    subTitleBldg13.setColspan(2);
				    subTitleBldg13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg13);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg14 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleBldg14.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg14.setLeading(20);
				    subTitleBldg14.setColspan(2);
				    subTitleBldg14.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg14);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    }
				    
				    
				    praroop2Map = eForm.getGuidelineDataMapPraroop2();
				//    ArrayList jasper=new ArrayList();
				    Set mapSet2 = (Set)praroop2Map.entrySet();
					Iterator mapIterator2 = mapSet2.iterator();
					while(mapIterator2.hasNext())
					{  JasperBean jb=new JasperBean();
						Map.Entry mapEntry2 = (Map.Entry)mapIterator2.next();
						String key2[] = ((String)mapEntry2.getKey()).split("~");
						String tehsil2 = key2[1];
						String ward2 = key2[4];
						String mohalla2 = key2[5];
						String Area2 = key2[2];
						String subArea2 = key2[3];
						
						jb.setBuildingTehsil(tehsil2);
						jb.setBuildingAreaName(Area2);
						jb.setBuildingSubAreaName(subArea2);
						jb.setBuildingWard(ward2);
						jb.setBuildingMohallaColony(mohalla2);
					//	jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
						jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
						
						Cell tehValue2 = new Cell(new Phrase(tehsil2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
						tehValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
						tehValue2.setLeading(20);
						tehValue2.setColspan(1);
						datatable.addCell(tehValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell areaValue2 = new Cell(new Phrase(Area2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    areaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    areaValue2.setLeading(20);
					    areaValue2.setColspan(1);
					    datatable.addCell(areaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell subAreaValue2 = new Cell(new Phrase(subArea2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    subAreaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    subAreaValue2.setLeading(20);
					    subAreaValue2.setColspan(1);
					    datatable.addCell(subAreaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell wardValue2 = new Cell(new Phrase(ward2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    wardValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    wardValue2.setLeading(20);
					    wardValue2.setColspan(1);
					    datatable.addCell(wardValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell mohallaValue2 = new Cell(new Phrase(mohalla2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    mohallaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    mohallaValue2.setLeading(20);
					    mohallaValue2.setColspan(2);
					    datatable.addCell(mohallaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    ArrayList list2 = (ArrayList)mapEntry2.getValue();
						GuidelineDTO gDTO1 = (GuidelineDTO)list2.get(0);
						jb.setBuildingRcc(gDTO1.getGuideLineValue());
						GuidelineDTO gDTO2 = (GuidelineDTO)list2.get(1);
						jb.setBuildingRbc(gDTO2.getGuideLineValue());
						GuidelineDTO gDTO3 = (GuidelineDTO)list2.get(2);
						jb.setBuildingtinShade(gDTO3.getGuideLineValue());
						GuidelineDTO gDTO4 = (GuidelineDTO)list2.get(3);
						jb.setBuildingKachaKab(gDTO4.getGuideLineValue());
						GuidelineDTO gDTO5 = (GuidelineDTO)list2.get(4);
						jb.setBuildingShop(gDTO5.getGuideLineValue());
						GuidelineDTO gDTO6 = (GuidelineDTO)list2.get(5);
						jb.setBuildingOffice(gDTO6.getGuideLineValue());
						GuidelineDTO gDTO7 = (GuidelineDTO)list2.get(6);
						jb.setBuildingGodown(gDTO7.getGuideLineValue());
						GuidelineDTO gDTO8 = (GuidelineDTO)list2.get(7);
						jb.setBuildingResi(gDTO8.getGuideLineValue());
						GuidelineDTO gDTO9 = (GuidelineDTO)list2.get(8);
						jb.setBuildingComm(gDTO9.getGuideLineValue());
					    for(int i = 0 ; i < list2.size() ; i++)
					    {
					    	GuidelineDTO gDTO = (GuidelineDTO)list2.get(i);
					    	
					    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
						    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
						    plotResValue.setLeading(20);
						    plotResValue.setColspan(2);
						    datatable.addCell(plotResValue);
						    datatable.setBorderWidth(2);
						    datatable.setAlignment(1);
						    
					 }
					    jasper.add(jb);
						   jb.setJasperList(jasper);
				   }
					   if(lang.equalsIgnoreCase("en"))
					    {   
					Cell blankrow11=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
					blankrow11.setHorizontalAlignment(Element.ALIGN_CENTER);
					blankrow11.setColspan(24);
					blankrow11.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow11);
				    datatable.setAlignment(1);
				    
				    
				    
					Cell sectionheader3=new Cell(new Phrase("Praroop 3", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
					sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
					sectionheader3.setColspan(24);
					sectionheader3.setBorder(Rectangle.NO_BORDER);
					sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(sectionheader3);
				    datatable.setAlignment(1);
				    
				    Cell sectionheaderDtls3=new Cell(new Phrase("Guideline for Agricultural Land for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
				    sectionheaderDtls3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheaderDtls3.setColspan(24);
				    sectionheaderDtls3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    sectionheaderDtls3.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(sectionheaderDtls3);
				    datatable.setAlignment(1);
				    
				    
				    
				    Cell blankrow4=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
				    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow4.setColspan(24);
				    blankrow4.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow4);
				    datatable.setAlignment(1);
				    
				    Cell subTitleTeh = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleTeh.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleTeh.setLeading(20);
				    subTitleTeh.setColspan(2);
				    subTitleTeh.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleTeh);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAreaType22 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleAreaType22.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAreaType22.setLeading(20);
				    subTitleAreaType22.setColspan(2);
				    subTitleAreaType22.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAreaType22);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleSubAreaType22 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleSubAreaType22.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleSubAreaType22.setLeading(20);
				    subTitleSubAreaType22.setColspan(3);
				    subTitleSubAreaType22.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleSubAreaType22);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleWard2 = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleWard2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleWard2.setLeading(20);
				    subTitleWard2.setColspan(2);
				    subTitleWard2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleWard2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleMohalla2 = new Cell(new Phrase("MOHALLA COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
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
				    
				    Cell subTitleAgri3 = new Cell(new Phrase("LAND UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
				    subTitleAgri3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAgri3.setLeading(20);
				    subTitleAgri3.setColspan(6);
				    subTitleAgri3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAgri3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    
				    Cell blankCell = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell.setLeading(20);
				    blankCell.setColspan(2);
				    datatable.addCell(blankCell);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell2.setLeading(20);
				    blankCell2.setColspan(2);
				    datatable.addCell(blankCell2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell3.setLeading(20);
				    blankCell3.setColspan(3);
				    datatable.addCell(blankCell3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
				    blankCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell6.setLeading(20);
				    blankCell6.setColspan(2);
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
				    
					    }   
				    praroop3Map = eForm.getGuidelineDataMapPraroop3();
				    
				    Set mapSet3 = (Set)praroop3Map.entrySet();
					Iterator mapIterator3 = mapSet3.iterator();
					while(mapIterator3.hasNext())
					{
						JasperBean jb=new JasperBean();
						Map.Entry mapEntry = (Map.Entry)mapIterator3.next();
						String key[] = ((String)mapEntry.getKey()).split("~");
						String tehsil = key[1];
						String area = key[4];
						String subArea = key[5];
						String ward = key[2];
						String mohalla = key[3];
						
						jb.setAgriTehsil(tehsil);
						jb.setAgriAreaName(area);
						jb.setAgriSubAreaName(subArea);
						jb.setAgriWard(ward);
						jb.setAgriMohallaColony(mohalla);
						jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
						
						Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
						tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
						tehValue.setLeading(20);
						tehValue.setColspan(2);
						datatable.addCell(tehValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell areaValue = new Cell(new Phrase(area, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    areaValue.setLeading(20);
					    areaValue.setColspan(2);
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
					    wardValue.setColspan(2);
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
					    GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
						jb.setAgriIrri(gDTO1.getGuideLineValue());
						GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
						jb.setAgriUnIrri(gDTO2.getGuideLineValue());
						GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
						jb.setAgriLandResi(gDTO3.getGuideLineValue());
						GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
						jb.setAgriLandComm(gDTO4.getGuideLineValue());
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
					    jasper.add(jb);
						   jb.setJasperList(jasper);
					} 
		    
		    /*  else
		    {
		    	Cell title = new Cell(new Phrase("गाइडलाइन देखें ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,14)));
			    title.setHorizontalAlignment(Element.ALIGN_CENTER);
			    title.setLeading(20);
			    title.setColspan(24);
			    title.setBorder(Rectangle.NO_BORDER);
			    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(title);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			   
			    Cell subTitle1 = new Cell(new Phrase("जिला - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
			    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle1.setLeading(20);
			    subTitle1.setColspan(24);
			    subTitle1.setBorder(Rectangle.NO_BORDER);
			    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle1);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle2 = new Cell(new Phrase("वित्तीय वर्ष  - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
			    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle2.setLeading(20);
			    subTitle2.setColspan(24);
			    subTitle2.setBorder(Rectangle.NO_BORDER);
			    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell sectionheader=new Cell(new Phrase("प्रारूप् 1 ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
			    sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheader.setColspan(24);
			    sectionheader.setBorder(Rectangle.NO_BORDER);
			    sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(sectionheader);
			    datatable.setAlignment(1);
			    
			    Cell sectionheaderDtls=new Cell(new Phrase(" भूख्ण्ड की गाइडलाइन वर्ष के बाजार मूल्य पाने के लिए"+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,10)));
			    sectionheaderDtls.setHorizontalAlignment(Element.ALIGN_CENTER);
			    sectionheaderDtls.setColspan(24);
			    sectionheaderDtls.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    sectionheaderDtls.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(sectionheaderDtls);
			    datatable.setAlignment(1);
			    
			   
			    
			    Cell blankrow=new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
			    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankrow.setColspan(24);
			    blankrow.setBorder(Rectangle.NO_BORDER);
			    datatable.addCell(blankrow);
			    datatable.setAlignment(1);
			    
			    Cell subTitle3 = new Cell(new Phrase("तह्सील ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle3.setLeading(20);
			    subTitle3.setColspan(3);
			    subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle4 = new Cell(new Phrase("क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle4.setLeading(20);
			    subTitle4.setColspan(3);
			    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle5 = new Cell(new Phrase("उप-क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle5.setLeading(20);
			    subTitle5.setColspan(3);
			    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle11 = new Cell(new Phrase("वार्ड", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(3);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle12 = new Cell(new Phrase("मोहल्ला/कालोनी का नाम", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle12.setLeading(20);
			    subTitle12.setColspan(3);
			    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle6 = new Cell(new Phrase("आवासीय(वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle6.setLeading(20);
			    subTitle6.setColspan(3);
			    subTitle6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle7 = new Cell(new Phrase("व्यावसायिक(वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle7.setLeading(20);
			    subTitle7.setColspan(3);
			    subTitle7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle8 = new Cell(new Phrase("PLOT UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle8.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle8.setLeading(20);
			    subTitle8.setColspan(6);
			    subTitle8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle8);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle9 = new Cell(new Phrase("औद्योगिक(वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle9.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle9.setLeading(20);
			    subTitle9.setColspan(3);
			    subTitle9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle9);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle10 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    subTitle10.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle10.setLeading(20);
			    subTitle10.setColspan(3);
			    datatable.addCell(subTitle10);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP2 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP2.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP2.setLeading(20);
			    blankCellP2.setColspan(3);
			    datatable.addCell(blankCellP2);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP3 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP3.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP3.setLeading(20);
			    blankCellP3.setColspan(3);
			    datatable.addCell(blankCellP3);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP6 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP6.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP6.setLeading(20);
			    blankCellP6.setColspan(3);
			    datatable.addCell(blankCellP6);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP7 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP7.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP7.setLeading(20);
			    blankCellP7.setColspan(3);
			    datatable.addCell(blankCellP7);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP4 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP4.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP4.setLeading(20);
			    blankCellP4.setColspan(3);
			    datatable.addCell(blankCellP4);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell blankCellP5 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
			    blankCellP5.setHorizontalAlignment(Element.ALIGN_CENTER);
			    blankCellP5.setLeading(20);
			    blankCellP5.setColspan(3);
			    datatable.addCell(blankCellP5);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle11 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle11.setLeading(20);
			    subTitle11.setColspan(2);
			    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle11);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle12 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle12.setLeading(20);
			    subTitle12.setColspan(2);
			    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle12);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle13 = new Cell(new Phrase("NORMAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
			    subTitle13.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle13.setLeading(20);
			    subTitle13.setColspan(2);
			    subTitle13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			    datatable.addCell(subTitle13);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    Cell subTitle14 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
			    subTitle14.setHorizontalAlignment(Element.ALIGN_CENTER);
			    subTitle14.setLeading(20);
			    subTitle14.setColspan(3);
			    datatable.addCell(subTitle14);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
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
					String areaName = key[2];
					String subAreaName = key[3];
					
					Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					tehValue.setLeading(20);
					tehValue.setColspan(3);
					datatable.addCell(tehValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell areaValue = new Cell(new Phrase(areaName, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    areaValue.setLeading(20);
				    areaValue.setColspan(3);
				    datatable.addCell(areaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subAreaValue = new Cell(new Phrase(subAreaName, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subAreaValue.setLeading(20);
				    subAreaValue.setColspan(3);
				    datatable.addCell(subAreaValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell wardValue = new Cell(new Phrase(ward, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    wardValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    wardValue.setLeading(20);
				    wardValue.setColspan(3);
				    datatable.addCell(wardValue);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell mohallaValue = new Cell(new Phrase(mohalla, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
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
				    	
				    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    plotResValue.setLeading(20);
					    plotResValue.setColspan(3);
					    datatable.addCell(plotResValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    
				    }
				} 
				 
				
					Cell blankrow10=new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
					blankrow10.setHorizontalAlignment(Element.ALIGN_CENTER);
					blankrow10.setColspan(24);
					blankrow10.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow10);
				    datatable.setAlignment(1);
				    
				    
				    
				    Cell sectionheader2 = new Cell(new Phrase("प्रारूप् 2", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
				    sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheader2.setColspan(24);
				    sectionheader2.setBorder(Rectangle.NO_BORDER);
				    sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(sectionheader2);
				    datatable.setAlignment(1);
				    
				    Cell sectionheaderDtls2=new Cell(new Phrase(" बिल्डिंग की गाइडलाइन वर्ष के बाजार मूल्य पाने के लिए"+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,10)));
				    sectionheaderDtls2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheaderDtls2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    sectionheaderDtls2.setColspan(24);
				    sectionheaderDtls2.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(sectionheaderDtls2);
				    datatable.setAlignment(1);
				    
				   
				    
				    Cell blankrow2=new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
				    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow2.setColspan(24);
				    blankrow2.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleTehsil = new Cell(new Phrase("तहसील ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleTehsil.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleTehsil.setLeading(20);
				    subTitleTehsil.setColspan(1);
				    subTitleTehsil.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleTehsil);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAreaType2 = new Cell(new Phrase("क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAreaType2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAreaType2.setLeading(20);
				    subTitleAreaType2.setColspan(1);
				    subTitleAreaType2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAreaType2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleSubAreaType2 = new Cell(new Phrase("उप-क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleSubAreaType2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleSubAreaType2.setLeading(20);
				    subTitleSubAreaType2.setColspan(1);
				    subTitleSubAreaType2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleSubAreaType2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleWard = new Cell(new Phrase("वार्ड", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleWard.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleWard.setLeading(20);
				    subTitleWard.setColspan(1);
				    subTitleWard.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleWard);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleMohalla = new Cell(new Phrase("मोहल्ला/कालोनी का नाम", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleMohalla.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleMohalla.setLeading(20);
				    subTitleMohalla.setColspan(2);
				    subTitleMohalla.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleMohalla);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg1 = new Cell(new Phrase("आवासीय (वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg1.setLeading(20);
				    subTitleBldg1.setColspan(8);
				    subTitleBldg1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg2 = new Cell(new Phrase("व्यावसायिक (वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg2.setLeading(20);
				    subTitleBldg2.setColspan(6);
				    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    Cell subTitleBldg5 = new Cell(new Phrase("बहुमंजिल(वर्गमीटर) ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg5.setLeading(20);
				    subTitleBldg5.setColspan(4);
				    subTitleBldg5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg5);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankrow23 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankrow23.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow23.setLeading(20);
				    blankrow23.setColspan(1);
				    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(blankrow23);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCellB2 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCellB2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB2.setLeading(20);
				    blankCellB2.setColspan(1);
				    datatable.addCell(blankCellB2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankrowB3= new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankrowB3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrowB3.setLeading(20);
				    blankrowB3.setColspan(1);
				    datatable.addCell(blankrowB3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCellB4 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCellB4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB4.setLeading(20);
				    blankCellB4.setColspan(1);
				    datatable.addCell(blankCellB4);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    
				    Cell blankCellB5 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCellB5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCellB5.setLeading(20);
				    blankCellB5.setColspan(2);
				    datatable.addCell(blankCellB5);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				   
				    
				    Cell subTitleBldg6 = new Cell(new Phrase("आरसीसी", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg6.setLeading(20);
				    subTitleBldg6.setColspan(2);
				    subTitleBldg6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg6);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg7 = new Cell(new Phrase("आर.बी.सी./ गार्डर फर्शी", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg7.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg7.setLeading(20);
				    subTitleBldg7.setColspan(2);
				    subTitleBldg7.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg7);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg8 = new Cell(new Phrase("टिन छाया /अंग्रेजी टाइलें / लकड़ी भाग / अभ्रक", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg8.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg8.setLeading(20);
				    subTitleBldg8.setColspan(2);
				    subTitleBldg8.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg8);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg9 = new Cell(new Phrase("कच्चा कवेलु/खपरेल", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg9.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg9.setLeading(20);
				    subTitleBldg9.setColspan(2);
				    subTitleBldg9.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg9);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg10 = new Cell(new Phrase("दुकान", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg10.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg10.setLeading(20);
				    subTitleBldg10.setColspan(2);
				    subTitleBldg10.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg10);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg11 = new Cell(new Phrase("कार्यालय", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg11.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg11.setLeading(20);
				    subTitleBldg11.setColspan(2);
				    subTitleBldg11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg11);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg12 = new Cell(new Phrase("गोडाउन ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg12.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg12.setLeading(20);
				    subTitleBldg12.setColspan(2);
				    subTitleBldg12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg12);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg13 = new Cell(new Phrase("व्यावसायिक", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg13.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg13.setLeading(20);
				    subTitleBldg13.setColspan(2);
				    subTitleBldg13.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleBldg13);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleBldg14 = new Cell(new Phrase("आवासीय ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleBldg14.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleBldg14.setLeading(20);
				    subTitleBldg14.setColspan(2);
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
						String ward2 = key2[4];
						String mohalla2 = key2[5];
						String Area2 = key2[2];
						String subArea2 = key2[3];
						
						Cell tehValue2 = new Cell(new Phrase(tehsil2, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
						tehValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
						tehValue2.setLeading(20);
						tehValue2.setColspan(1);
						datatable.addCell(tehValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell areaValue2 = new Cell(new Phrase(Area2, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					    areaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    areaValue2.setLeading(20);
					    areaValue2.setColspan(1);
					    datatable.addCell(areaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell subAreaValue2 = new Cell(new Phrase(subArea2, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					    subAreaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    subAreaValue2.setLeading(20);
					    subAreaValue2.setColspan(1);
					    datatable.addCell(subAreaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell wardValue2 = new Cell(new Phrase(ward2, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					    wardValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    wardValue2.setLeading(20);
					    wardValue2.setColspan(1);
					    datatable.addCell(wardValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell mohallaValue2 = new Cell(new Phrase(mohalla2, FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
					    mohallaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
					    mohallaValue2.setLeading(20);
					    mohallaValue2.setColspan(2);
					    datatable.addCell(mohallaValue2);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    ArrayList list2 = (ArrayList)mapEntry2.getValue();
					    for(int i = 0 ; i < list2.size() ; i++)
					    {
					    	GuidelineDTO gDTO = (GuidelineDTO)list2.get(i);
					    	
					    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
						    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
						    plotResValue.setLeading(20);
						    plotResValue.setColspan(2);
						    datatable.addCell(plotResValue);
						    datatable.setBorderWidth(2);
						    datatable.setAlignment(1);
						    
					 }
					
				   }
			    
					Cell blankrow11=new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
					blankrow11.setHorizontalAlignment(Element.ALIGN_CENTER);
					blankrow11.setColspan(24);
					blankrow11.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow11);
				    datatable.setAlignment(1);
				    
				    
				    
					Cell sectionheader3=new Cell(new Phrase("प्रारूप् 3 ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
					sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
					sectionheader3.setColspan(24);
					sectionheader3.setBorder(Rectangle.NO_BORDER);
					sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(sectionheader3);
				    datatable.setAlignment(1);
				    
				    Cell sectionheaderDtls3=new Cell(new Phrase(" कृषि भूमि  की गाइडलाइन वर्ष के बाजार मूल्य पाने के लिए"+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,10)));
				    sectionheaderDtls3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    sectionheaderDtls3.setColspan(24);
				    sectionheaderDtls3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    sectionheaderDtls3.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(sectionheaderDtls3);
				    datatable.setAlignment(1);
				    
				    
				    
				    Cell blankrow4=new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,12)));
				    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankrow4.setColspan(24);
				    blankrow4.setBorder(Rectangle.NO_BORDER);
				    datatable.addCell(blankrow4);
				    datatable.setAlignment(1);
				    
				    Cell subTitleTeh = new Cell(new Phrase("तहसील ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleTeh.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleTeh.setLeading(20);
				    subTitleTeh.setColspan(2);
				    subTitleTeh.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleTeh);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAreaType22 = new Cell(new Phrase("क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAreaType22.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAreaType22.setLeading(20);
				    subTitleAreaType22.setColspan(2);
				    subTitleAreaType22.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAreaType22);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleSubAreaType22 = new Cell(new Phrase("उप-क्षेत्र का प्रकार", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleSubAreaType22.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleSubAreaType22.setLeading(20);
				    subTitleSubAreaType22.setColspan(3);
				    subTitleSubAreaType22.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleSubAreaType22);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleWard2 = new Cell(new Phrase("वार्ड", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleWard2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleWard2.setLeading(20);
				    subTitleWard2.setColspan(2);
				    subTitleWard2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleWard2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleMohalla2 = new Cell(new Phrase("मोहल्ला/कालोनी का नाम", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleMohalla2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleMohalla2.setLeading(20);
				    subTitleMohalla2.setColspan(3);
				    subTitleMohalla2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleMohalla2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAgri1 = new Cell(new Phrase("सिंचित (हेक्टेयर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAgri1.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAgri1.setLeading(20);
				    subTitleAgri1.setColspan(3);
				    subTitleAgri1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAgri1);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAgri2 = new Cell(new Phrase("असिंचित (हेक्टेयर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAgri2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAgri2.setLeading(20);
				    subTitleAgri2.setColspan(3);
				    subTitleAgri2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAgri2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAgri3 = new Cell(new Phrase("500 तक भूमि (वर्गमीटर)", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAgri3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAgri3.setLeading(20);
				    subTitleAgri3.setColspan(6);
				    subTitleAgri3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAgri3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				   
				    
				    Cell blankCell = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell.setLeading(20);
				    blankCell.setColspan(2);
				    datatable.addCell(blankCell);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell2 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell2.setLeading(20);
				    blankCell2.setColspan(2);
				    datatable.addCell(blankCell2);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell3 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell3.setLeading(20);
				    blankCell3.setColspan(3);
				    datatable.addCell(blankCell3);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell6 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell6.setLeading(20);
				    blankCell6.setColspan(2);
				    datatable.addCell(blankCell6);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell7 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell7.setLeading(20);
				    blankCell7.setColspan(3);
				    datatable.addCell(blankCell7);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell4 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell4.setLeading(20);
				    blankCell4.setColspan(3);
				    datatable.addCell(blankCell4);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell blankCell5 = new Cell(new Phrase("", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,8)));
				    blankCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
				    blankCell5.setLeading(20);
				    blankCell5.setColspan(3);
				    datatable.addCell(blankCell5);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAgri11 = new Cell(new Phrase("आवासीय ", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
				    subTitleAgri11.setHorizontalAlignment(Element.ALIGN_CENTER);
				    subTitleAgri11.setLeading(20);
				    subTitleAgri11.setColspan(3);
				    subTitleAgri11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				    datatable.addCell(subTitleAgri11);
				    datatable.setBorderWidth(2);
				    datatable.setAlignment(1);
				    
				    Cell subTitleAgri12 = new Cell(new Phrase("व्यावसायिक", FontFactory.getFont("C:\\ARIALUNI.ttf", BaseFont.IDENTITY_H ,BaseFont.EMBEDDED,7)));
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
						String area = key[4];
						String subArea = key[5];
						String ward = key[2];
						String mohalla = key[3];
						
						Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
						tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
						tehValue.setLeading(20);
						tehValue.setColspan(2);
						datatable.addCell(tehValue);
					    datatable.setBorderWidth(2);
					    datatable.setAlignment(1);
					    
					    Cell areaValue = new Cell(new Phrase(area, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
					    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
					    areaValue.setLeading(20);
					    areaValue.setColspan(2);
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
					    wardValue.setColspan(2);
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
					} 
		    }*/
		    
					if(lang.equalsIgnoreCase("hi")){
						 
						// String path=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
					//	  String masterReportFileName = projPath+"\\JasperXML\\allMasterView.jrxml";
					    //  String subReportPlotFileName = projPath+"\\JasperXML\\allPlotSubView.jrxml";
					    //  String subReportBuildFileName = projPath+"\\JasperXML\\allBuildSubView.jrxml";
					    //  String subReportAgriFileName = projPath+"\\JasperXML\\allAgriSubView.jrxml";

					      
					     // String destFileName = projPath+"\\JasperXML\\allMasterView.JRprint";
			//System.out.println("Dest="+destFileName+"~"+subReportPlotFileName);
			
			
			 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");

				String masterReportFileName =property.getValue("pdf.location").concat("allMasterView.jrxml");
				String subReportPlotFileName =property.getValue("pdf.location").concat("allPlotSubView.jrxml");
				String subReportBuildFileName = property.getValue("pdf.location").concat("allBuildSubView.jrxml");
			    String subReportAgriFileName = property.getValue("pdf.location").concat("allAgriSubView.jrxml");
				String destFileName =property.getValue("pdf.location").concat("allMasterView.JRprint");
			    String path=property.getValue("pdf.location");
			    
			    
						 JRBeanCollectionDataSource beanColDataSource =
					            new JRBeanCollectionDataSource(jasper);
						 try{
							 JasperReport jasperMasterReport = JasperCompileManager
					            .compileReport(masterReportFileName);
							
					         JasperReport jasperPlotSubReport = JasperCompileManager
					            .compileReport(subReportPlotFileName);
					         JasperReport jasperBuildSubReport = JasperCompileManager
					            .compileReport(subReportBuildFileName);
					         JasperReport jasperAgriSubReport = JasperCompileManager
					            .compileReport(subReportAgriFileName);
					         File file = new File(destFileName);
					        System.out.println("ALL REPORTS COMPILED");
					         Map<String, Object> parameters = new HashMap<String, Object>();
					         parameters.put("subreportParameter", jasperPlotSubReport);
					         parameters.put("subreportParameter1", jasperBuildSubReport);
					        parameters.put("subreportParameter2", jasperAgriSubReport);
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
						  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop All.pdf");
						  		response.setContentLength(pdfReport.size());
						  		ServletOutputStream out = response.getOutputStream();
						  		os.writeTo(out);
						  		out.flush();
						  		out.close();
					         
					        /* ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
					         JRPdfExporter exporter = new JRPdfExporter();
				        		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
				        		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				        		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
				        		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
				        		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
				 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pestReport.pdf");  
				 		  	   exporter.exportReport();
				        		//added by shruti
				        		ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				        		response.setContentType("application/pdf");
				        		response.setCharacterEncoding("UTF-8");
				        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
				        		response.setContentLength(pdfReport.size());
				        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
				        		os.writeTo(out);
				        		out.flush();
				        		out.close();*/
					    
					      
					      JasperViewer.viewReport(jasperPrint);
					      
						 	
						 }catch(Exception e){
							 e.printStackTrace();

						 }
						   }else{
		    datatable.setCellsFitPage(true);
		      document.add(datatable);
		    document.close();
				response.setContentType("guideline/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineView.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
		
						   }
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
	
		      
		      return eForm;
	}


public GuidelinePreparationForm printToPdfPraroop1(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String lang) throws Exception
{
	try{
		HashMap praroop1Map = new HashMap();
		HashMap praroop2Map = new HashMap();
		HashMap praroop3Map = new HashMap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4,20,20,20,20);
		PdfWriter.getInstance(document, baos);
		document.open();
	    Table datatable = new Table(36);
	    System.out.println("*****PDF"+document.newPage()); 		      
	    datatable.setWidth(100);
	    datatable.setPadding(3);
	   
	    if(lang.equalsIgnoreCase("en"))
	    {
	    	Cell title = new Cell(new Phrase("GUIDELINE VIEW", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
		    title.setHorizontalAlignment(Element.ALIGN_CENTER);
		    title.setLeading(20);
		    title.setColspan(36);
		    title.setBorder(Rectangle.NO_BORDER);
		    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(title);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		   
		    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle1.setLeading(20);
		    subTitle1.setColspan(36);
		    subTitle1.setBorder(Rectangle.NO_BORDER);
		    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle1);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle2.setLeading(20);
		    subTitle2.setColspan(36);
		    subTitle2.setBorder(Rectangle.NO_BORDER);
		    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell sectionheader=new Cell(new Phrase("Praroop 1", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    sectionheader.setHorizontalAlignment(Element.ALIGN_CENTER);
		    sectionheader.setColspan(36);
		    sectionheader.setBorder(Rectangle.NO_BORDER);
		    sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(sectionheader);
		    datatable.setAlignment(1);
		    
		    Cell sectionheaderDtls=new Cell(new Phrase("Guideline for Plots for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		    sectionheaderDtls.setHorizontalAlignment(Element.ALIGN_CENTER);
		    sectionheaderDtls.setColspan(36);
		    sectionheaderDtls.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    sectionheaderDtls.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(sectionheaderDtls);
		    datatable.setAlignment(1);
		    
		   
		    
		    Cell blankrow=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankrow.setColspan(36);
		    blankrow.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(blankrow);
		    datatable.setAlignment(1);
		    
		    Cell subTitle3 = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle3.setLeading(20);
		    subTitle3.setColspan(4);
		    subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle3);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle4 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle4.setLeading(20);
		    subTitle4.setColspan(3);
		    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle4);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle5 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle5.setLeading(20);
		    subTitle5.setColspan(3);
		    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle5);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);

		    
		    Cell subTitle6 = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle6.setLeading(20);
		    subTitle6.setColspan(4);
		    subTitle6.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle6);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle11 = new Cell(new Phrase("MOHALLA/COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle11.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle11.setLeading(20);
		    subTitle11.setColspan(4);
		    subTitle11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle11);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle12 = new Cell(new Phrase("RESIDENTIAL(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle12.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle12.setLeading(20);
		    subTitle12.setColspan(6);
		    subTitle12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle12);
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
		    
		   /* Cell subTitle8 = new Cell(new Phrase("PLOT UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
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
		    
		    Cell subTitle10 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle10.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle10.setLeading(20);
		    subTitle10.setColspan(4);
		    datatable.addCell(subTitle10);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP2.setLeading(20);
		    blankCellP2.setColspan(3);
		    datatable.addCell(blankCellP2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP6.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP6.setLeading(20);
		    blankCellP6.setColspan(3);
		    datatable.addCell(blankCellP6);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP7 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP7.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP7.setLeading(20);
		    blankCellP7.setColspan(4);
		    datatable.addCell(blankCellP7);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP21 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP21.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP21.setLeading(20);
		    blankCellP21.setColspan(4);
		    datatable.addCell(blankCellP21);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP22 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP22.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP22.setLeading(20);
		    blankCellP22.setColspan(6);
		    datatable.addCell(blankCellP22);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP3.setLeading(20);
		    blankCellP3.setColspan(6);
		    datatable.addCell(blankCellP3);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellP4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    blankCellP4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellP4.setLeading(20);
		    blankCellP4.setColspan(6);
		    datatable.addCell(blankCellP4);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		   /* Cell blankCellP5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
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
	    }
	   
	    
	    
	  /*  Cell subTitle14 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
	    subTitle14.setHorizontalAlignment(Element.ALIGN_CENTER);
	    subTitle14.setLeading(20);
	    subTitle14.setColspan(6);
	    datatable.addCell(subTitle14);
	    datatable.setBorderWidth(2);
	    datatable.setAlignment(1);*/
	   
	    praroop1Map = eForm.getGuidelineDataMapPraroop1();
	    ArrayList jasper=new ArrayList();
	    Set mapSet = (Set)praroop1Map.entrySet();
		Iterator mapIterator = mapSet.iterator();
		while(mapIterator.hasNext())
		{
			 JasperBean jb=new JasperBean();
			Map.Entry mapEntry = (Map.Entry)mapIterator.next();
			String key[] = ((String)mapEntry.getKey()).split("~");
			String tehsil = key[1];
			String areaName  = key[2];
			String subAreaName  = key[3];
			String ward = key[4];
			String mohalla = key[5];
			
			jb.setPlotTehsil(tehsil);
			jb.setPlotAreaName(areaName);
			jb.setPlotSubAreaName(subAreaName);
			jb.setPlotWard(ward);
			jb.setPlotMohallaColony(mohalla);
			jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
			jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
			
			Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			tehValue.setLeading(20);
			tehValue.setColspan(4);
			datatable.addCell(tehValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell areaValue = new Cell(new Phrase(areaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    areaValue.setLeading(20);
		    areaValue.setColspan(3);
		    datatable.addCell(areaValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subAreaValue = new Cell(new Phrase(subAreaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subAreaValue.setLeading(20);
		    subAreaValue.setColspan(3);
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
			GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
			jb.setPlotResi(gDTO1.getGuideLineValue());
			GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
			jb.setPlotComm(gDTO2.getGuideLineValue());
			GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
			jb.setPlotIndus(gDTO3.getGuideLineValue());
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
		   jasper.add(jb);
		   jb.setJasperList(jasper);
		}  
		   if(lang.equalsIgnoreCase("hi")){
			   //String path=request.getContextPath().concat("/WebContent/JasperXML//");
			   //String relativePath="../masterReports.jrxml";
			   //System.out.println(new File(path).getCanonicalPath());
			  // System.out.println(new File(path).getAbsolutePath());
			  // String absolutePath=new File(path).getAbsolutePath();
		PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");

		 String masterReportFileName =property.getValue("pdf.location").concat("masterReports.jrxml");
		 String subReportFileName =property.getValue("pdf.location").concat("subReport.jrxml");
		 String destFileName =property.getValue("pdf.location").concat("masterReports.JRprint");
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

	         JasperFillManager.fillReportToFile(jasperMasterReport,
	            destFileName, parameters, beanColDataSource);
	   
	      JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(file);
	      JasperViewer.viewReport(jasperPrint);
	      
	      
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
	  		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
	  		response.setContentLength(pdfReport.size());
	  		ServletOutputStream out = response.getOutputStream();
	  		os.writeTo(out);
	  		out.flush();
	  		out.close();
			/*catch (JRException e) {
			e.printStackTrace();
			}*/
	      
	      
	      
	      
	   /* ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
	    JRPdfExporter exporter = new JRPdfExporter();
  		System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
  		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  		exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
  		exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
  		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
  		exporter.exportReport();
  	
  		ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
  		response.setContentType("application/pdf");
  		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
  		response.setContentLength(pdfReport.size());
  		ServletOutputStream out = response.getOutputStream();
  		os.writeTo(out);
  		out.flush();
  		out.close();*/
  		
	    /*  
	      final JROdtExporter odtExporter = new JROdtExporter();
  		final ByteArrayOutputStream odtStream = new ByteArrayOutputStream();
  		odtExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,odtStream);
  		odtExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  		odtExporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
  		odtExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, BaseFont.IDENTITY_H);
  		odtExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\PRAROOP1.odt");  
  		odtExporter.exportReport();
  		odtStream.close();
  		
	  	    ByteArrayOutputStream os = (ByteArrayOutputStream)odtExporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
	  	  byte b[]= os.toByteArray();
 	   String fileName="D:\\JRXML\\PRAROOP1.odt";
		File newFile = new File(fileName);
		FileOutputStream fos = new FileOutputStream(newFile);
		fos.write(b);
		fos.close();
		//byte d[]=JasperExportManager.exportReportToPdf(jasperPrint);
	//	dms.downloadDocument(response, "pestReport1.pdf", d);
		//JasperExportManager.exportReportToPdfFile(fileName, "D:\\JRXML\\pdsdsd.pdf");
		
		
	  	ByteArrayOutputStream sd=new ByteArrayOutputStream();
	  	os.writeTo(sd);
	  	System.out.println(sd.toString()+"~"+sd.size());
	  	   System.out.println(os);
	  	  
	  	response.setContentType("application/vnd.oasis.opendocument.text");	//for odt
   		response.setCharacterEncoding( BaseFont.IDENTITY_H);
   		response.setHeader("Content-Disposition", "attachment; filename=\"reportss.odt");
   		response.setContentLength(odtStream.size());
   			os.writeTo(out);				        	
   		out.flush();
   		out.close();
   		//DMSUtility dms=new DMSUtility();
   		//dms.downloadDocument(response, "pestReport1.pdf", d);
   		// System.out.println(f2);
   		 ServletOutputStream out = response.getOutputStream();
   		 sd.writeTo(out); 
   		 sd.flush();
   		 sd.close();
		 	*/
		 }catch(Exception e){
			 e.printStackTrace();

		 }
		   }else{
		datatable.setCellsFitPage(true);
	      document.add(datatable);
		    document.close();
				response.setContentType("guideline/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineViewPraroop1.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
		   }
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
	
		      
		      return eForm;
	}	

public GuidelinePreparationForm printToPdfPraroop2(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String lang,String projPath) throws Exception
{
	try{
		HashMap praroop1Map = new HashMap();
		HashMap praroop2Map = new HashMap();
		HashMap praroop3Map = new HashMap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4,20,20,20,20);
		PdfWriter.getInstance(document, baos);
		document.open();
	    Table datatable = new Table(33);
	     		      
	    datatable.setWidth(100);
	    datatable.setPadding(3);
	   
	    if(lang.equalsIgnoreCase("en"))
	    {
	    	Cell title = new Cell(new Phrase("GUIDELINE VIEW", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
		    title.setHorizontalAlignment(Element.ALIGN_CENTER);
		    title.setLeading(20);
		    title.setColspan(33);
		    title.setBorder(Rectangle.NO_BORDER);
		    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(title);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		   
		    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle1.setLeading(20);
		    subTitle1.setColspan(33);
		    subTitle1.setBorder(Rectangle.NO_BORDER);
		    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle1);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle2.setLeading(20);
		    subTitle2.setColspan(33);
		    subTitle2.setBorder(Rectangle.NO_BORDER);
		    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell sectionheader2 = new Cell(new Phrase("Praroop 2", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    sectionheader2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    sectionheader2.setColspan(33);
		    sectionheader2.setBorder(Rectangle.NO_BORDER);
		    sectionheader2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(sectionheader2);
		    datatable.setAlignment(1);
		    
		    Cell sectionheaderDtls2=new Cell(new Phrase("Guideline for Constructed Properties for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		    sectionheaderDtls2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    sectionheaderDtls2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    sectionheaderDtls2.setColspan(33);
		    sectionheaderDtls2.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(sectionheaderDtls2);
		    datatable.setAlignment(1);
		    
		   
		    
		    Cell blankrow2=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankrow2.setColspan(33);
		    blankrow2.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(blankrow2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleTehsil = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleTehsil.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleTehsil.setLeading(20);
		    subTitleTehsil.setColspan(2);
		    subTitleTehsil.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleTehsil);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle4 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle4.setLeading(20);
		    subTitle4.setColspan(3);
		    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle4);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle5 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle5.setLeading(20);
		    subTitle5.setColspan(3);
		    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle5);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    

		    
		    Cell subTitleWard = new Cell(new Phrase("WARD", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleWard.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleWard.setLeading(20);
		    subTitleWard.setColspan(2);
		    subTitleWard.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleWard);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleMohalla = new Cell(new Phrase("MOHALLA/COLONY", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleMohalla.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleMohalla.setLeading(20);
		    subTitleMohalla.setColspan(2);
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
		    
		   
		    Cell subTitleBldg5 = new Cell(new Phrase("MULTISTOREY BUILDING(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleBldg5.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleBldg5.setLeading(20);
		    subTitleBldg5.setColspan(6);
		    subTitleBldg5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleBldg5);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankrow23 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankrow23.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankrow23.setLeading(20);
		    blankrow23.setColspan(2);
		    subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(blankrow23);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellB2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCellB2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellB2.setLeading(20);
		    blankCellB2.setColspan(2);
		    datatable.addCell(blankCellB2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellB21 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCellB21.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellB21.setLeading(20);
		    blankCellB21.setColspan(2);
		    datatable.addCell(blankCellB21);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellB22 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCellB22.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellB22.setLeading(20);
		    blankCellB22.setColspan(2);
		    datatable.addCell(blankCellB22);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCellB3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCellB3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCellB3.setLeading(20);
		    blankCellB3.setColspan(2);
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
		    
	    }
	  
	    
	    
	    
	    
	    praroop2Map = eForm.getGuidelineDataMapPraroop2();
	    ArrayList jasper = new ArrayList();
	    Set mapSet2 = (Set)praroop2Map.entrySet();
		Iterator mapIterator2 = mapSet2.iterator();
		while(mapIterator2.hasNext())
		{				 JasperBean jb=new JasperBean();

			Map.Entry mapEntry2 = (Map.Entry)mapIterator2.next();
			String key2[] = ((String)mapEntry2.getKey()).split("~");
			String tehsil2 = key2[1];
			String areaName  = key2[2];
			String subAreaName  = key2[3];
			String ward2 = key2[4];
			String mohalla2 = key2[5];

			jb.setBuildingTehsil(tehsil2);
			jb.setBuildingAreaName(areaName);
			jb.setBuildingSubAreaName(subAreaName);
			jb.setBuildingWard(ward2);
			jb.setBuildingMohallaColony(mohalla2);
			jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
			jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
			
			Cell tehValue2 = new Cell(new Phrase(tehsil2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			tehValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
			tehValue2.setLeading(20);
			tehValue2.setColspan(2);
			datatable.addCell(tehValue2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell areaValue = new Cell(new Phrase(areaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    areaValue.setLeading(20);
		    areaValue.setColspan(3);
		    datatable.addCell(areaValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subAreaValue = new Cell(new Phrase(subAreaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subAreaValue.setLeading(20);
		    subAreaValue.setColspan(3);
		    datatable.addCell(subAreaValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);

		    Cell wardValue2 = new Cell(new Phrase(ward2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    wardValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    wardValue2.setLeading(20);
		    wardValue2.setColspan(2);
		    datatable.addCell(wardValue2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		  
		    Cell mohallaValue2 = new Cell(new Phrase(mohalla2, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    mohallaValue2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    mohallaValue2.setLeading(20);
		    mohallaValue2.setColspan(2);
		    datatable.addCell(mohallaValue2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    ArrayList list2 = (ArrayList)mapEntry2.getValue();
			GuidelineDTO gDTO1 = (GuidelineDTO)list2.get(0);
			jb.setBuildingRcc(gDTO1.getGuideLineValue());
			GuidelineDTO gDTO2 = (GuidelineDTO)list2.get(1);
			jb.setBuildingRbc(gDTO2.getGuideLineValue());
			GuidelineDTO gDTO3 = (GuidelineDTO)list2.get(2);
			jb.setBuildingtinShade(gDTO3.getGuideLineValue());
			GuidelineDTO gDTO4 = (GuidelineDTO)list2.get(3);
			jb.setBuildingKachaKab(gDTO4.getGuideLineValue());
			GuidelineDTO gDTO5 = (GuidelineDTO)list2.get(4);
			jb.setBuildingShop(gDTO5.getGuideLineValue());
			GuidelineDTO gDTO6 = (GuidelineDTO)list2.get(5);
			jb.setBuildingOffice(gDTO6.getGuideLineValue());
			GuidelineDTO gDTO7 = (GuidelineDTO)list2.get(6);
			jb.setBuildingGodown(gDTO7.getGuideLineValue());
			GuidelineDTO gDTO8 = (GuidelineDTO)list2.get(7);
			jb.setBuildingResi(gDTO8.getGuideLineValue());
			GuidelineDTO gDTO9 = (GuidelineDTO)list2.get(8);
			jb.setBuildingComm(gDTO9.getGuideLineValue());
			
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
		    	jasper.add(jb);
			   jb.setJasperList(jasper);
	   }
		 if(lang.equalsIgnoreCase("hi")){
			 
			// String path=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
			// String masterReportFileName = projPath+"\\JasperXML\\buildingMasterView.jrxml";
		    ///  String subReportFileName =projPath+"\\JasperXML\\buildingSubView.jrxml";
		     // String destFileName = projPath+"\\JasperXML\\buildingDestinationView.JRprint";
		      PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");

			String masterReportFileName =property.getValue("pdf.location").concat("buildingMasterView.jrxml");
			String subReportFileName =property.getValue("pdf.location").concat("buildingSubView.jrxml");
			String destFileName =property.getValue("pdf.location").concat("buildingDestinationView.JRprint");
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
			  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop 2.pdf");
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
	 		  	   exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,"D:\\JRXML\\pestReport.pdf");  
	 		  	   exporter.exportReport();
	        		//added by shruti
	        		ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
	        		response.setContentType("application/pdf");
	        		response.setCharacterEncoding("UTF-8");
	        		response.setHeader("Content-Disposition", "attachment; filename=\"report.pdf");
	        		response.setContentLength(pdfReport.size());
	        		ServletOutputStream out = response.getOutputStream();System.out.println(os.toString());
	        		os.writeTo(out);
	        		out.flush();
	        		out.close();*/
		    
		      
		      JasperViewer.viewReport(jasperPrint);
		      
			 	
			 }catch(Exception e){
				 e.printStackTrace();

			 }
			   }else{
		 datatable.setCellsFitPage(true);
	      document.add(datatable);
		    document.close();
				response.setContentType("guideline/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineViewPraroop2.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
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
public GuidelinePreparationForm printToPdfPraroop3(GuidelinePreparationForm eForm,HttpServletRequest request,  HttpServletResponse response, String lang, String projPath) throws Exception
{
	try{
		HashMap praroop1Map = new HashMap();
		HashMap praroop2Map = new HashMap();
		HashMap praroop3Map = new HashMap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4,20,20,20,20);
		PdfWriter.getInstance(document, baos);
		document.open();
	    Table datatable = new Table(24);
	    System.out.println("*****PDF"+document.newPage()); 		      
	    datatable.setWidth(100);
	    datatable.setPadding(3);
	   
	    if(lang.equalsIgnoreCase("en"))
	    {
	    	Cell title = new Cell(new Phrase("GUIDELINE VIEW", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
		    title.setHorizontalAlignment(Element.ALIGN_CENTER);
		    title.setLeading(20);
		    title.setColspan(24);
		    title.setBorder(Rectangle.NO_BORDER);
		    title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(title);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		   
		    Cell subTitle1 = new Cell(new Phrase("DISTRICT - "+ eForm.getGuideDTO().getDistrict(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle1.setLeading(20);
		    subTitle1.setColspan(24);
		    subTitle1.setBorder(Rectangle.NO_BORDER);
		    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle1);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle2 = new Cell(new Phrase("FINANCIAL YEAR - "+ eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle2.setLeading(20);
		    subTitle2.setColspan(24);
		    subTitle2.setBorder(Rectangle.NO_BORDER);
		    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    
		    Cell sectionheader3=new Cell(new Phrase("Praroop 3", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
			sectionheader3.setHorizontalAlignment(Element.ALIGN_CENTER);
			sectionheader3.setColspan(24);
			sectionheader3.setBorder(Rectangle.NO_BORDER);
			sectionheader3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(sectionheader3);
		    datatable.setAlignment(1);
		    
		    Cell sectionheaderDtls3=new Cell(new Phrase("Guideline for Agricultural Land for deriving Market Values for Year "+eForm.getGuideDTO().getFinancialYear(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		    sectionheaderDtls3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    sectionheaderDtls3.setColspan(24);
		    sectionheaderDtls3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    sectionheaderDtls3.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(sectionheaderDtls3);
		    datatable.setAlignment(1);
		    
		    
		    
		    Cell blankrow4=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));
		    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankrow4.setColspan(24);
		    blankrow4.setBorder(Rectangle.NO_BORDER);
		    datatable.addCell(blankrow4);
		    datatable.setAlignment(1);
		    
		    Cell subTitleTeh = new Cell(new Phrase("TEHSIL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleTeh.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleTeh.setLeading(20);
		    subTitleTeh.setColspan(2);
		    subTitleTeh.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleTeh);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle4 = new Cell(new Phrase("AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle4.setLeading(20);
		    subTitle4.setColspan(3);
		    subTitle4.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle4);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitle5 = new Cell(new Phrase("SUB-AREA TYPE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitle5.setLeading(20);
		    subTitle5.setColspan(3);
		    subTitle5.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitle5);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleWard2 = new Cell(new Phrase("WARD/PATWARI HALKA", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleWard2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleWard2.setLeading(20);
		    subTitleWard2.setColspan(2);
		    subTitleWard2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleWard2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleMohalla2 = new Cell(new Phrase("MOHALLA/VILLAGE", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleMohalla2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleMohalla2.setLeading(20);
		    subTitleMohalla2.setColspan(4);
		    subTitleMohalla2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleMohalla2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleAgri1 = new Cell(new Phrase("IRRIGATED(HECTARE)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleAgri1.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleAgri1.setLeading(20);
		    subTitleAgri1.setColspan(4);
		    subTitleAgri1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleAgri1);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleAgri2 = new Cell(new Phrase("UNIRRIGATED(HECTARE)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleAgri2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleAgri2.setLeading(20);
		    subTitleAgri2.setColspan(4);
		    subTitleAgri2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleAgri2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleAgri3 = new Cell(new Phrase("LAND UPTO 500(SQM)", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleAgri3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleAgri3.setLeading(20);
		    subTitleAgri3.setColspan(8);
		    subTitleAgri3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleAgri3);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		   
		    
		    Cell blankCell = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell.setLeading(20);
		    blankCell.setColspan(2);
		    datatable.addCell(blankCell);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell2.setLeading(20);
		    blankCell2.setColspan(2);
		    datatable.addCell(blankCell2);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell6 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell6.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell6.setLeading(20);
		    blankCell6.setColspan(2);
		    datatable.addCell(blankCell6);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell7 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell7.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell7.setLeading(20);
		    blankCell7.setColspan(2);
		    datatable.addCell(blankCell7);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell3.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell3.setLeading(20);
		    blankCell3.setColspan(4);
		    datatable.addCell(blankCell3);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell4.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell4.setLeading(20);
		    blankCell4.setColspan(4);
		    datatable.addCell(blankCell4);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell blankCell5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    blankCell5.setHorizontalAlignment(Element.ALIGN_CENTER);
		    blankCell5.setLeading(20);
		    blankCell5.setColspan(4);
		    datatable.addCell(blankCell5);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleAgri11 = new Cell(new Phrase("RESIDENTIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleAgri11.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleAgri11.setLeading(20);
		    subTitleAgri11.setColspan(4);
		    subTitleAgri11.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleAgri11);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subTitleAgri12 = new Cell(new Phrase("COMMERCIAL", FontFactory.getFont(FontFactory.COURIER, 7, Font.BOLD)));
		    subTitleAgri12.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subTitleAgri12.setLeading(20);
		    subTitleAgri12.setColspan(4);
		    subTitleAgri12.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		    datatable.addCell(subTitleAgri12);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
	    }
	   
	    
	    
	    
	    praroop3Map = eForm.getGuidelineDataMapPraroop3();
	    ArrayList jasper = new ArrayList();
	    Set mapSet3 = (Set)praroop3Map.entrySet();
		Iterator mapIterator3 = mapSet3.iterator();
		while(mapIterator3.hasNext())
		{
			JasperBean jb=new JasperBean();
			Map.Entry mapEntry = (Map.Entry)mapIterator3.next();
			String key[] = ((String)mapEntry.getKey()).split("~");
			String tehsil = key[1];
			String areaName  = key[2];
			String subAreaName  = key[3];
			String ward = key[4];
			String mohalla = key[5];
			jb.setAgriTehsil(tehsil);
			jb.setAgriAreaName(areaName);
			jb.setAgriSubAreaName(subAreaName);
			jb.setAgriWard(ward);
			jb.setAgriMohallaColony(mohalla);
			jb.setPlotDistrict(eForm.getGuideDTO().getDistrict());
			jb.setPlotFinancialYear(eForm.getGuideDTO().getFinancialYear());
			
			Cell tehValue = new Cell(new Phrase(tehsil, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			tehValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			tehValue.setLeading(20);
			tehValue.setColspan(2);
			datatable.addCell(tehValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell areaValue = new Cell(new Phrase(areaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    areaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    areaValue.setLeading(20);
		    areaValue.setColspan(3);
		    datatable.addCell(areaValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);
		    
		    Cell subAreaValue = new Cell(new Phrase(subAreaName, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    subAreaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    subAreaValue.setLeading(20);
		    subAreaValue.setColspan(3);
		    datatable.addCell(subAreaValue);
		    datatable.setBorderWidth(2);
		    datatable.setAlignment(1);

		    
		    Cell wardValue = new Cell(new Phrase(ward, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		    wardValue.setHorizontalAlignment(Element.ALIGN_CENTER);
		    wardValue.setLeading(20);
		    wardValue.setColspan(2);
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
		    
		    GuidelineDTO gDTO1 = (GuidelineDTO)list.get(0);
			jb.setAgriIrri(gDTO1.getGuideLineValue());
			GuidelineDTO gDTO2 = (GuidelineDTO)list.get(1);
			jb.setAgriUnIrri(gDTO2.getGuideLineValue());
			GuidelineDTO gDTO3 = (GuidelineDTO)list.get(2);
			jb.setAgriLandResi(gDTO3.getGuideLineValue());
			GuidelineDTO gDTO4 = (GuidelineDTO)list.get(3);
			jb.setAgriLandComm(gDTO4.getGuideLineValue());
			
			
		    for(int i = 0 ; i < list.size() ; i++)
		    {
		    	GuidelineDTO gDTO = (GuidelineDTO)list.get(i);
		    	
		    	Cell plotResValue = new Cell(new Phrase(gDTO.getGuideLineValue(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
			    plotResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			    plotResValue.setLeading(20);
			    plotResValue.setColspan(4);
			    datatable.addCell(plotResValue);
			    datatable.setBorderWidth(2);
			    datatable.setAlignment(1);
			    
			    
		    }
			jasper.add(jb);
			   jb.setJasperList(jasper);
		} 
		 if(lang.equalsIgnoreCase("hi")){
			 
				// String path=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
				/* String masterReportFileName = projPath+"\\JasperXML\\agriMasterView.jrxml";
			      String subReportFileName =projPath+"\\JasperXML\\agriSubView.jrxml";
			      String destFileName = projPath+"\\JasperXML\\agriDestinationView.JRprint";*/

			     /// String masterReportFileName = "D:\\JASPERXML\\agriMasterView.jrxml";
			     // String subReportFileName ="D:\\JASPERXML\\agriSubView.jrxml";
			     // String destFileName = "D:\\JASPERXML\\agriDestinationView.JRprint";
			     // System.out.println(masterReportFileName+"~"+subReportFileName);
			      
			    PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");

				String masterReportFileName =property.getValue("pdf.location").concat("agriMasterView.jrxml");
				String subReportFileName =property.getValue("pdf.location").concat("agriSubView.jrxml");
				String destFileName =property.getValue("pdf.location").concat("agriDestinationView.JRprint");
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
				  		response.setHeader("Content-Disposition", "attachment; filename=\"Praroop 3.pdf");
				  		response.setContentLength(pdfReport.size());
				  		ServletOutputStream out = response.getOutputStream();
				  		os.writeTo(out);
				  		out.flush();
				  		out.close();
				  		
			      /*  ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
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
			      
			      
			      JasperViewer.viewReport(jasperPrint);
			      
				 	
				 }catch(Exception e){
					 e.printStackTrace();

				 }
				   }else{
    datatable.setCellsFitPage(true);
      document.add(datatable);
    document.close();
		response.setContentType("guideline/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"GuidelineViewPraroop3.pdf");
		response.setContentLength(baos.size());
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
				   }

}catch(Exception e){
	
	e.printStackTrace();
	
}

      
      return eForm;
	
}

//###########----added by satbir kumar-----##########

public String getFinalGuidelineId(GuidelineDTO formDTO) {
	String guideId=guideDAO.getFinalGuidelineId(formDTO);
	return guideId;
}

public ArrayList getGuidelineIdList(GuidelinePreparationForm form) {
	ArrayList guidelineList= guideDAO.getGuidelineIdList(form);
	return guidelineList;
}	
}

package com.wipro.igrs.newreginitefiling.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.poi.hssf.util.HSSFColor.BLACK;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.newgen.burning.ReadPropertiesFile;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.digitalSign.DocumentService;
import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.newdutycalculationefiling.constant.CommonConstant;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;
import com.wipro.igrs.newreginitefiling.constant.RegInitConstant;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;
import com.wipro.igrs.util.GUIDGenerator;


/**
 * @author PR836429
 * Preeti Kuralkar 20 May 2016
 *
 */
public class PrintPdfAction extends BaseAction {
	private String forwardJsp = CommonConstant.FORWARD_PAGE_HOME;
	private Logger logger = (Logger) Logger.getLogger(PrintPdfAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String language = (String)session.getAttribute("languageLocale");
		 PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
		String fwdPage = request.getParameter("fwdName");
		String pathfile = request.getParameter("pathfile");
		String forward = "";
		RegCommonBO commonBo = new RegCommonBO();

		RegCommonForm regForm;
		regForm = (RegCommonForm) form;
		RegCommonDTO commonDto;
		commonDto = regForm.getCommonDto();
		
		
		String languageLocale = "hi";
		if (session.getAttribute("languageLocale") != null) {
			languageLocale = (String) session.getAttribute("languageLocale");
		}

		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			session.setAttribute("modName", RegInitConstant.MODNAME_ENGLISH);
		} else {
			session.setAttribute("modName", RegInitConstant.MODNAME_HINDI);
		}
		String userId = (String) session.getAttribute("UserId");
		commonDto.setLanguage(languageLocale);
		
		String formName = regForm.getFormName();
		String actionName = regForm.getActionName();
		logger.debug("formName:-" + formName);
		logger.debug("actionName:-" + actionName);
		
		if(request.getParameter("printStatus")!=null){
			if(request.getParameter("printStatus").equalsIgnoreCase(RegInitConstant.PRINT_STATUS)){
				if(language.equalsIgnoreCase("en")){
					String efileNo=	(String) session.getAttribute("TempIdPrint");
					String TempEfileNo = commonBo.getTempEfileNo(efileNo);
					logger.debug("inside Print Pdf Action TempEfileNo ----------->" +TempEfileNo)	;
					String filePath;
					String barCodePath;
					String path="";
					boolean flag=false;
					
					
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					path=pr.getValue("upload.location");
					
					logger.debug("inside Print Pdf Action path ----------->" +path)	;
					filePath=path+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo;
					logger.debug("inside Print Pdf Action filePath ----------->" +filePath)	;
					String fileName=CommonConstant.FILE_NAME_EFILE;
					File folder = new File(filePath);
					if (!folder.exists()) {
			            folder.mkdirs();
			      }
						//end
					//Document document = new Document(PageSize.A4, 20, 20, 20, 20);
					Document document = new Document();  
					File newFile = new File(filePath, fileName);
					/*	
				        document.setMargins(Utilities.millimetersToPoints(30), Utilities.millimetersToPoints(30),   Utilities.millimetersToPoints(30), Utilities.millimetersToPoints(30));
				        document.setMargins(50f, 50f, 50f, 50f);
				        document.setMarginMirroring(true);
					
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile));
					document.open();
					  document.setPageSize(PageSize.A4);
					  document.setMargins(40f, 40f, 40f, 40f);
					Rectangle rect=new Rectangle(PageSize.A4);
					rect.setBorderWidthLeft(40f);
					rect.setBorderWidthRight(40f);
					rect.setBorderWidthBottom(40f);
					rect.setBorderWidthTop(40f);
					rect.enableBorderSide(1);
					rect.enableBorderSide(2);
					rect.enableBorderSide(4);
					rect.enableBorderSide(8);
					rect.setBorder(2);
					rect.setBorderColor(BaseColor.BLACK);
				      
				        document.add(rect);
				        
				      //  document.setMargins(40,40,40,40);
				        document.left(40f);
				        document.right(40f);
				        document.top(40f);
				        document.bottom(40f);
				        document.setMarginMirroring(true);*/
				     
				      
				        try
				        {
				        	
				        	
				        	PdfWriter    writer = PdfWriter.getInstance(document, new FileOutputStream(newFile));
				            document.open();
				            
				            logger.debug("inside Print Pdf Action document open----------->")	;
				            
				            //setting font size
				            Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
				            //end of setting font size
				            PdfContentByte pdfContentByte = writer.getDirectContent();

				            Rectangle pageSize = writer.getPageSize(); 
				            pdfContentByte.rectangle(pageSize.getLeft() + 3, pageSize.getBottom() + 3, 
				              pageSize.getWidth() - 6, pageSize.getHeight() - 6); 
				           
				            pdfContentByte.stroke(); 
				            
				            document.setMarginMirroring(true);
				            
				        	
							//below code for logo and header details
				            PdfPTable table00 = new PdfPTable(2);
				           
				         // Image img1 = Image.getInstance(getServlet().getServletContext().getRealPath("")+File.separator+"images"+File.separator+"header img.jpg");
				            
				           // add a couple of blank lines
				            table00.setSpacingBefore(20f); //Space before table
				            table00.setSpacingAfter(20f); //Space after table

				            
				            
				            String headerpath1=path+File.separator+"pdf"+File.separator+"/header img.jpg";
				            
				            logger.debug("inside Print Pdf Action :: header image path :: headerpath1 :: "+ headerpath1)	;
				            
				          Image img1=Image.getInstance(headerpath1);
				           img1.scaleAbsolute(90f, 90f);
				           img1.setAlignment(Image.LEFT); 
				            PdfPCell imageHeader2=new PdfPCell(img1,false);
				            
				           // Image img2 = Image.getInstance(getServlet().getServletContext().getRealPath("")+File.separator+"images"+File.separator+"title efile.jpg");
				          //  Image img2 = Image.getInstance("D:\\igrs_27March\\IGRS\\WebContent\\images\\title efile.jpg");
				           
				           
				          //Commented By Gulrej on 17th Aug, 2017
				           /*String headerpath=path+File.separator+"/Capture2.jpg";
				            Image img2=Image.getInstance(headerpath);
				            PdfPCell imageHeader3=new PdfPCell(img2,false);*/ //Commented By Gulrej on 17th Aug, 2017
				           
							
				            BarcodeEAN codeEAN = new BarcodeEAN();
				            logger.debug("inside Print Pdf Action :: header image path :: 1111111111111111111111111111")	;
				           codeEAN.setCode(regForm.getEFILETXNID());
				           logger.debug("inside Print Pdf Action :: header image path :: 22222222222222222222222222222")	;
				           codeEAN.setCodeType(Barcode.UPCA);
				         
							Image qr_image=codeEAN.createImageWithBarcode(pdfContentByte, BaseColor.BLACK, BaseColor.BLACK);
							qr_image.scaleAbsolute(90f, 90f);
							qr_image.setAlignment(Image.RIGHT); 
							PdfPCell imageHeader1=new PdfPCell(qr_image,false);
						      //for local 
							//Image img2 = Image.getInstance("D:\\igrs_27March\\IGRS\\WebContent\\images\\title efile.jpg");
							
							
								
							
							imageHeader1.setBorder(0);
							imageHeader2.setBorder(0);
							//imageHeader3.setBorder(0); //Commented By Gulrej on 17th Aug, 2017
				           
							 table00.addCell(imageHeader2);
						 //table00.addCell(imageHeader3); //Commented By Gulrej on 17th Aug, 2017
				           table00.addCell(imageHeader1);
				            
				            float[] columnWidths=new float[]{20f,7f};
				            table00.setWidths(columnWidths);
				            //table00.setHorizontalAlignment(Element.ALIGN_CENTER);
				            table00.setSpacingAfter(20f);
			 	            document.add(table00);
				            
				           
			 	          
			 	         
				           
			 	         
				            
				            
				            PdfPTable table15 = new PdfPTable(1); 
				          
				            table15.setWidthPercentage(80); //Width 100%
				            table15.setSpacingBefore(20f); //Space before table
				            table15.setSpacingAfter(20f); //Space after table
				            
				            //Set Column widths
				            float[] columnWidths15 = {2f};
				            table15.setWidths(columnWidths15);
				            PdfPCell cell15=new PdfPCell(new Phrase ("Efiling Details",titleFont));
				            cell15.setSpaceCharRatio(10);
				            cell15.setBackgroundColor(BaseColor.LIGHT_GRAY);
				            cell15.setBorderColor(BaseColor.BLACK);
				            cell15.setBorder(0);
				            cell15.setPaddingLeft(10);
				            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
				            cell15.setVerticalAlignment(Element.ALIGN_CENTER);
				     
				            table15.addCell(cell15);
				            table15.setSpacingAfter(20f);
				            document.add(table15); 
				           
				            
				            //below for 1 table
				            PdfPTable table1 = new PdfPTable(2);
				            PdfPCell c1 = new PdfPCell();
				            
				            

				            c1 = new PdfPCell();
				            
				           

				            
				            table1.setHeaderRows(1);
				            table1.setSpacingAfter(20f);
				            PdfPCell cellDeedName=new PdfPCell(new Phrase ("Deed Name",titleFont));
				            table1.addCell(cellDeedName);
				            table1.addCell(regForm.getDEEDNAME());
				            PdfPCell cellEfileNo=new PdfPCell(new Phrase ("e-Filing Number",titleFont));
				            table1.addCell(cellEfileNo);
				            
				            table1.addCell(regForm.getEFILETXNID());
				            PdfPCell cellEfileDate=new PdfPCell(new Phrase ("e-Filing Date",titleFont));
				            table1.addCell(cellEfileDate);
				           
				            table1.addCell(regForm.getEFILEDATE());
				            PdfPCell cellEfileFee=new PdfPCell(new Phrase ("E Filing fees",titleFont));
				            table1.addCell(cellEfileFee);
				            
				            table1.addCell("500");
				            PdfPCell cellStampDuty=new PdfPCell(new Phrase ("Stamp Duty",titleFont));
				            table1.addCell(cellStampDuty);
				           
				            table1.addCell(regForm.getSTAMPDUTY());
				            PdfPCell cellAmt=new PdfPCell(new Phrase ("Amount received under Mortgage/ Document",titleFont));
				            table1.addCell(cellAmt);
				          
				            table1.addCell(regForm.getFiledValue());
				            PdfPCell cellLoan=new PdfPCell(new Phrase ("Purpose of loan and interest rate or other",titleFont));
				            table1.addCell(cellLoan);
				            
				            table1.addCell(regForm.getPURLOAN());
				            PdfPCell cellModePay=new PdfPCell(new Phrase ("Physical Stamps and/ or e-Stamp number",titleFont));
				            table1.addCell(cellModePay);
				            
				            if(regForm.getECODE()!=null && regForm.getECODE()!=""){
				            	table1.addCell(regForm.getECODE());
				            }else{
				            	table1.addCell("-");
				            	regForm.setECODE("-");
				            }
				            PdfPCell cellExecDate=new PdfPCell(new Phrase ("Execution Date",titleFont));
				            table1.addCell(cellExecDate);
				           
				            table1.addCell(regForm.getEXCDATE());
				            PdfPCell cellRecDate=new PdfPCell(new Phrase ("Date of Receiving of document",titleFont));
				            table1.addCell(cellRecDate);
				            
				            table1.addCell(regForm.getDATEOFSUBMISSIONPO());
				            PdfPCell cellSRName=new PdfPCell(new Phrase ("SR Name",titleFont));
				            table1.addCell(cellSRName);
				            
				            table1.addCell(regForm.getSRNAME());
				            PdfPCell cellSROffice=new PdfPCell(new Phrase ("Sub Registrar Office",titleFont));
				            table1.addCell(cellSROffice);
				           
				            table1.addCell(regForm.getSUBREGISTRAROFFICE());
				            table1.setSpacingAfter(20f);
				            document.add(table1);
				            //end of table 
				            
				            
				            
				            
				            //For Bank Details
				            
				            PdfPTable table16 = new PdfPTable(1); // 3 columns.
				            table16.setWidthPercentage(80); //Width 100%
				            table16.setSpacingBefore(20f); //Space before table
				            table16.setSpacingAfter(20f); //Space after table
				            
				            //Set Column widths
				            float[] columnWidths16 = {2f};
				            table16.setWidths(columnWidths16);
				            PdfPCell cell16=new PdfPCell(new Phrase ("Bank Details",titleFont));
				            
				            cell16.setBackgroundColor(BaseColor.LIGHT_GRAY);
				            cell16.setBorderColor(BaseColor.BLACK);
				            cell16.setBorder(0);
				            cell16.setPaddingLeft(10);
				            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				            cell16.setVerticalAlignment(Element.ALIGN_CENTER);
				     
				            table16.addCell(cell16);
				            document.add(table16); 
				            
				            PdfPTable table2 = new PdfPTable(2);
				            PdfPCell c2 = new PdfPCell();
				            
				            

				            c2 = new PdfPCell();
				            
				           

				           
				            table2.setHeaderRows(1);
				            
			  	            table2.setSpacingAfter(250f); //Space after table
				            PdfPCell cellBankName=new PdfPCell(new Phrase ("Bank Name",titleFont));
				            table2.addCell(cellBankName);
				            table2.addCell(regForm.getBANKNAME());
				            
				            PdfPCell cellAddress=new PdfPCell(new Phrase ("Address",titleFont));
				            table2.addCell(cellAddress);
				            table2.addCell(regForm.getADDRESS());
				            
				            PdfPCell cellAutho=new PdfPCell(new Phrase ("Authorized person name",titleFont));
				            table2.addCell(cellAutho);
				            table2.addCell(regForm.getBANKAUTHORITYNAME());
				            
				            PdfPCell cellDeg=new PdfPCell(new Phrase ("Designation",titleFont));
				            table2.addCell(cellDeg);
				            table2.addCell(regForm.getBANKAUTHORITYDESIGNATION());
				           
				                  document.add(table2);
				            //end of table 
				            
				            
				           
				            //End of Bank DEtails 
				            
				            
				            //For Property Details
				                  
				                  PdfPTable table17 = new PdfPTable(1); // 3 columns.
				  	            table17.setWidthPercentage(80); //Width 100%
				  	            table17.setSpacingBefore(20f); //Space before table
				  	            table17.setSpacingAfter(20f); //Space after table
				  	            
				  	            //Set Column widths
				  	            float[] columnWidths17 = {2f};
				  	            table17.setWidths(columnWidths17);
				  	            PdfPCell cell17=new PdfPCell(new Phrase ("Property Details entered by Bank/Mortgagee",titleFont));
				  	            
				  	            cell17.setBackgroundColor(BaseColor.LIGHT_GRAY);
				  	            cell17.setBorderColor(BaseColor.BLACK);
				  	          cell17.setBorder(0);
				  	           
				  	            cell17.setPaddingLeft(10);
				  	            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	            cell17.setVerticalAlignment(Element.ALIGN_CENTER);
				  	     
				  	            table17.addCell(cell17);
				  	            document.add(table17); 
				  	            
				  	            PdfPTable table3 = new PdfPTable(2);
				  	            PdfPCell c3 = new PdfPCell();
				  	            
				  	            

				  	            c3 = new PdfPCell();
				  	            
				  	           

				  	            
				  	            table3.setHeaderRows(1);
				  	            
				  	          ArrayList getpdfPropertyDetails =new ArrayList();
				  	        getpdfPropertyDetails= regForm.getGetpropPrintDetails();
					            RegCommonDTO rowList1;
					            for(int i = 0; i < getpdfPropertyDetails.size(); i++){
					            	rowList1 = (RegCommonDTO)getpdfPropertyDetails.get(i);
				  	            table3.setHeaderRows(1);
				  	            
				  	            PdfPCell cellName=new PdfPCell(new Phrase ("Type of Property",titleFont));
				  	          table3.addCell(cellName);
				  	        table3.addCell(rowList1.getProtype());
				  	            
				  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Area of Property",titleFont));
				  	          table3.addCell(cellName1);
				  	        table3.addCell(rowList1.getPropaddress());
				  	            
				  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address of Property",titleFont));
				  	          table3.addCell(cellName2);
				  	        table3.addCell(rowList1.getProparear());
				  	      document.add(table3);
				  	    table3.flushContent();
				  	            
					            }
				  	            
				  	            
				  	           
				  	                 
				  	            //end of table 
				                  
				           
				            //End of Property DEtails
				            
				            
				            //For Party Details
				  	                  
				  	                PdfPTable table18 = new PdfPTable(1); // 3 columns.
					  	            table18.setWidthPercentage(80); //Width 100%
					  	            table18.setSpacingBefore(10f); //Space before table
					  	            table18.setSpacingAfter(10f); //Space after table
					  	            
					  	            //Set Column widths
					  	            float[] columnWidths18 = {2f};
					  	            table18.setWidths(columnWidths18);
					  	            PdfPCell cell18=new PdfPCell(new Phrase ("Party Details",titleFont));
					  	            
					  	            cell18.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  	            cell18.setBorderColor(BaseColor.BLACK);
					  	          cell18.setBorder(0);
					  	            cell18.setPaddingLeft(10);
					  	            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	            cell18.setVerticalAlignment(Element.ALIGN_CENTER);
					  	     
					  	            table18.addCell(cell18);
					  	            document.add(table18); 
					  	            
					  	            
					  	            
					  	            
					  	           
					  	          ArrayList getpdfPartyDetailsGov =new ArrayList();
					  	          ArrayList getpdfPartyDetailsInd =new ArrayList();
					  	        ArrayList getpdfPartyDetailsOrg =new ArrayList();
					  	      ArrayList getpdfPartyDetailsOrgPoA =new ArrayList();
					  	    ArrayList getpdfPartyDetailsIndPoA =new ArrayList();
						          
					  	     if( regForm.getGetpartyPrintDetailsGov()!=null && !regForm.getGetpartyPrintDetailsGov().isEmpty()){
					  	    	PdfPTable table4 = new PdfPTable(2);
				  	            PdfPCell c4 = new PdfPCell();
				  	            
				  	            

				  	            c4 = new PdfPCell();
					  	    	getpdfPartyDetailsGov=regForm.getGetpartyPrintDetailsGov();
					  	    	RegCommonDTO rowList;
					  	    	for(int i = 0; i < getpdfPartyDetailsGov.size(); i++){
					  	    		rowList = (RegCommonDTO)getpdfPartyDetailsGov.get(i);
					  	    		
					  	    		 table4.setHeaderRows(1);
					  	    		 
					  	    		//below code for heading of goverment offical
					  	    		 PdfPTable table19 = new PdfPTable(1); // 3 columns.
					  	    		table19.setWidthPercentage(80); //Width 100%
					  	    		table19.setSpacingBefore(10f); //Space before table
					  	    		table19.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths19 = {3f};
						  	          table19.setWidths(columnWidths19);
						  	        String c=rowList.getGovpartyType();
						  	          String d="Government Official("+c+")";
						  	            PdfPCell cell19=new PdfPCell(new Phrase (d,titleFont));
						  	            
						  	          cell19.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	        cell19.setBorderColor(BaseColor.BLACK);
						  	      cell19.setBorder(0);
						  	    cell19.setPaddingLeft(10);
						  	  cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell19.setVerticalAlignment(Element.ALIGN_LEFT);
						  	     
						  	table19.addCell(cell19);
						  	
						  	            document.add(table19);  
					  	    		 
					  	    		 //end of code
					  	    		 
					  	            
					  	            
					  	          PdfPCell cellName3=new PdfPCell(new Phrase ("Name",titleFont));
					  	            table4.addCell(cellName3);
					  	            table4.addCell(rowList.getGovdesg());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Father’s Name/ Husband’s name/ Guardian’s name",titleFont));
					  	            table4.addCell(cellName1);
					  	            table4.addCell(rowList.getGovfather());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address",titleFont));
					  	            table4.addCell(cellName2);
					  	            table4.addCell(rowList.getGovaddress());
					  	           
					  	            
					  	          document.add(table4);
					  	        table4.flushContent();
					  	    	}
					  	  	
					  	     }
					  	     
					  	     
					  	   if( regForm.getGetpartyPrintDetailsInd()!=null && !regForm.getGetpartyPrintDetailsInd().isEmpty()){
					  		   
					  		 PdfPTable table5 = new PdfPTable(2);
				  	            PdfPCell c4 = new PdfPCell();
				  	            
				  	            

				  	            c4 = new PdfPCell();
					  		 getpdfPartyDetailsInd=regForm.getGetpartyPrintDetailsInd();
					  	    	RegCommonDTO rowList;
					  	    	for(int i = 0; i < getpdfPartyDetailsInd.size(); i++){
					  	    		rowList = (RegCommonDTO)getpdfPartyDetailsInd.get(i);
					  	    		 table5.setHeaderRows(1);
					  	    		 
					  	    		 
					  	    		//below code for heading of goverment offical
					  	    		 PdfPTable table20 = new PdfPTable(1); // 3 columns.
					  	    		table20.setWidthPercentage(80); //Width 100%
					  	    		table20.setSpacingBefore(10f); //Space before table
					  	    		table20.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths20 = {3f};
						  	            table20.setWidths(columnWidths20);
						  	          String a=rowList.getIndpartyType();
						  	          String b="Individual"+"("+a+")";
						  	            PdfPCell cell20=new PdfPCell(new Phrase (b,titleFont));
						  	            
						  	          
						  	        
						  	          cell20.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	        cell20.setBorderColor(BaseColor.BLACK);
						  	      cell20.setBorder(0);
						  	    cell20.setPaddingLeft(10);
						  	  cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell20.setVerticalAlignment(Element.ALIGN_LEFT);
						  	     
						  	table20.addCell(cell20);
						  
						  	            document.add(table20);  
					  	    		 
					  	    		 //end of code
					  	    		 
					  	    	//PdfPCell cellName=new PdfPCell(new Phrase ("Individual",titleFont));
					  	        //   table4.addCell(cellName);
					  	            
					  	            
					  	          PdfPCell cellName3=new PdfPCell(new Phrase ("Name",titleFont));
					  	            table5.addCell(cellName3);
					  	            table5.addCell(rowList.getIndname());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Father’s Name/ Husband’s name/ Guardian’s name",titleFont));
					  	            table5.addCell(cellName1);
					  	            table5.addCell(rowList.getIndfather());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address",titleFont));
					  	            table5.addCell(cellName2);
					  	            table5.addCell(rowList.getIndiaddress());
					  	           
					  	            
					  	       	document.add(table5);
					  	      table5.flushContent();
					  	    	}
					  	  
					  	     }
					  	   
					  	 if( regForm.getGetpartyPrintDetailsOrg()!=null && !regForm.getGetpartyPrintDetailsOrg().isEmpty()){
					  		 
					  		 PdfPTable table6 = new PdfPTable(2);
				  	            PdfPCell c4 = new PdfPCell();
				  	            
				  	            

				  	            //c4 = new PdfPCell();
					  		 getpdfPartyDetailsOrg=regForm.getGetpartyPrintDetailsOrg();
					  	    	RegCommonDTO rowList;
					  	    	for(int i = 0; i < getpdfPartyDetailsOrg.size(); i++){
					  	    		rowList = (RegCommonDTO)getpdfPartyDetailsOrg.get(i);
					  	    		 table6.setHeaderRows(1);
					  	    		 
					  	    		//below code for heading of goverment offical
					  	    		 PdfPTable table21 = new PdfPTable(1); // 3 columns.
					  	    		table21.setWidthPercentage(80); //Width 100%
					  	    		table21.setSpacingBefore(10f); //Space before table
					  	    		table21.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths21 = {2f};
						  	            table21.setWidths(columnWidths21);
						  	          String e=rowList.getOrgpartyType();
						  	          String f="Organization"+"("+e+")";
						  	            PdfPCell cell21=new PdfPCell(new Phrase (f,titleFont));
						  	            
						  	          cell21.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	        cell21.setBorderColor(BaseColor.BLACK);
						  	      cell21.setBorder(0);
						  	    cell21.setPaddingLeft(10);
						  	  cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell21.setVerticalAlignment(Element.ALIGN_LEFT);
						  	     
						  	table21.addCell(cell21);
						  
						  	            document.add(table21);  
					  	    		 
					  	    		 //end of code
					  	    		 
					  	    		//PdfPCell cellName=new PdfPCell(new Phrase ("Organization",titleFont));
					  	         //  table4.addCell(cellName);
					  	            
					  	            
					  	          PdfPCell cellName3=new PdfPCell(new Phrase ("Name",titleFont));
					  	            table6.addCell(cellName3);
					  	            table6.addCell(rowList.getOrgname());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Father’s Name/ Husband’s name/ Guardian’s name",titleFont));
					  	            table6.addCell(cellName1);
					  	            table6.addCell(rowList.getOrgfather());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address",titleFont));
					  	            table6.addCell(cellName2);
					  	            table6.addCell(rowList.getOrgiaddress());
					  	           
					  	       	document.add(table6);
					  	      table6.flushContent();
					  	    		
					  	    	}
					  	  
					  	     }
					  	        
					  	    //below for poa organization
					  	if( regForm.getGetpartyPrintDetailsOrgPOA()!=null && !regForm.getGetpartyPrintDetailsOrgPOA().isEmpty()){
				  	    	PdfPTable table8 = new PdfPTable(2);
			  	            PdfPCell c25 = new PdfPCell();
			  	            
			  	            

			  	            c25 = new PdfPCell();
			  	          getpdfPartyDetailsOrgPoA=regForm.getGetpartyPrintDetailsOrgPOA();
				  	    	RegCommonDTO rowList;
				  	    	for(int i = 0; i < getpdfPartyDetailsOrgPoA.size(); i++){
				  	    		rowList = (RegCommonDTO)getpdfPartyDetailsOrgPoA.get(i);
				  	    		
				  	    		table8.setHeaderRows(1);
				  	    		 
				  	    		//below code for heading of goverment offical
				  	    		 PdfPTable table25 = new PdfPTable(1); // 3 columns.
				  	    		table25.setWidthPercentage(80); //Width 100%
				  	    		table25.setSpacingBefore(10f); //Space before table
				  	    		table25.setSpacingAfter(10f); //Space after table
					  	            
					  	            //Set Column widths
					  	            float[] columnWidths25 = {2f};
					  	          table25.setWidths(columnWidths25);
					  	        String g=rowList.getPoaorgpartytype();
					  	          String h="Organization"+"("+g+")";
					  	            PdfPCell cell25=new PdfPCell(new Phrase (h,titleFont));
					  	            
					  	          cell25.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  	        cell25.setBorderColor(BaseColor.BLACK);
					  	      cell25.setBorder(0);
					  	    cell25.setPaddingLeft(10);
					  	  cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
					  	cell25.setVerticalAlignment(Element.ALIGN_LEFT);
					  	     
					  	table25.addCell(cell25);
					  	
					  	            document.add(table25);  
				  	    		 
				  	    		 //end of code
				  	    		 
				  	            
				  	            
				  	          PdfPCell cellName3=new PdfPCell(new Phrase ("Name",titleFont));
				  	        table8.addCell(cellName3);
				  	      table8.addCell(rowList.getPoaorgauthname());
				  	            
				  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Father’s Name/ Husband’s name/ Guardian’s name",titleFont));
				  	          table8.addCell(cellName1);
				  	        table8.addCell(rowList.getPoaorgfather());
				  	            
				  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address",titleFont));
				  	          table8.addCell(cellName2);
				  	        table8.addCell(rowList.getPoaorgaddress());
				  	           
				  	      document.add(table8); 
				  		table8.flushContent();
				  	    	
				  	    	}
				  	  	
				  	     }
					  	
				  	    //below for poa ind
					  	if( regForm.getGetpartyPrintDetailsPOA()!=null && !regForm.getGetpartyPrintDetailsPOA().isEmpty() ){
				  	    	PdfPTable table7 = new PdfPTable(2);
			  	            PdfPCell c24 = new PdfPCell();
			  	            
			  	            

			  	            c24 = new PdfPCell();
			  	          getpdfPartyDetailsIndPoA=regForm.getGetpartyPrintDetailsPOA();
				  	    	RegCommonDTO rowList;
				  	    	for(int i = 0; i < getpdfPartyDetailsIndPoA.size(); i++){
				  	    		rowList = (RegCommonDTO)getpdfPartyDetailsIndPoA.get(i);
				  	    		
				  	    		table7.setHeaderRows(1);
				  	    		 
				  	    		//below code for heading of goverment offical
				  	    		 PdfPTable table24 = new PdfPTable(1); // 3 columns.
				  	    		table24.setWidthPercentage(80); //Width 100%
				  	    		table24.setSpacingBefore(10f); //Space before table
				  	    		table24.setSpacingAfter(10f); //Space after table
					  	            
					  	            //Set Column widths
					  	            float[] columnWidths24 = {2f};
					  	          table24.setWidths(columnWidths24);
					  	        String k=rowList.getPoapartytype();
					  	          String j="Organization"+"("+k+")";
					  	            PdfPCell cell24=new PdfPCell(new Phrase (j,titleFont));
					  	            
					  	          cell24.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  	        cell24.setBorderColor(BaseColor.BLACK);
					  	      cell24.setBorder(0);
					  	    cell24.setPaddingLeft(10);
					  	  cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
					  	cell24.setVerticalAlignment(Element.ALIGN_LEFT);
					  	     
					  	table24.addCell(cell24);
					  	
					  	            document.add(table24);  
				  	    		 
				  	    		 //end of code
				  	    		 
				  	            
				  	            
				  	          PdfPCell cellName3=new PdfPCell(new Phrase ("Name",titleFont));
				  	        
				  	        table7.addCell(cellName3);
				  	      table7.addCell(rowList.getPoaauthname());
				  	            
				  	            PdfPCell cellName1=new PdfPCell(new Phrase ("Father’s Name/ Husband’s name/ Guardian’s name",titleFont));
				  	          table7.addCell(cellName1);
				  	        table7.addCell(rowList.getPoafather());
				  	            
				  	            PdfPCell cellName2=new PdfPCell(new Phrase ("Address",titleFont));
				  	          table7.addCell(cellName2);
				  	        table7.addCell(rowList.getPoaaddress());
				  	      
				  	  	document.add(table7);
				        table7.flushContent();
				  	 		
				  	    	}
				  	  
				  	     }
				
					  	 document.close();
					  	                 
				           
					  	                logger.debug("inside Print Pdf Action PDF Created in english----------->")	;
					  	          	forwardJsp="printDetailsPage1";
					  	          return mapping.findForward(forwardJsp);	
				}
				        catch(Exception e){
							e.printStackTrace();
				        }
				}
				
				
			
		
				
				else{
					String efileNo=	(String) session.getAttribute("TempIdPrint");
					String TempEfileNo = commonBo.getTempEfileNo(efileNo);
						
					String filePath;
					String barCodePath;
					String path="";
					
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					path=pr.getValue("upload.location");
					filePath=path+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo;
					String fileName=CommonConstant.FILE_NAME_EFILE;
					
					
					File folder = new File(filePath);
					if (!folder.exists()) {
			            folder.mkdirs();
			      }
						//end
					
					Document document = new Document();  
					
				      
				        try
				        {
				        	
				        	File newFile = new File(filePath, fileName);
				        	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(newFile));
				            document.open();
				          //setting font size
				           // BaseFont unicode = BaseFont.createFont("D:\\shared2\\font\\AksharyoginiNormal.ttf", 
				                   // BaseFont.IDENTITY_H,    BaseFont.EMBEDDED);
				            //BaseFont unicode = BaseFont.createFont("//shared1//font//AksharyoginiNormal.ttf", BaseFont.IDENTITY_H,    BaseFont.EMBEDDED);
				            //Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
				            String hindiFont=pr.getValue("dms_hindi_font_path");
				            Font titleFont = FontFactory.getFont(hindiFont, BaseFont.IDENTITY_H, true);
				            //end of setting font size
				            PdfContentByte pdfContentByte = writer.getDirectContent();

				            Rectangle pageSize = writer.getPageSize(); 
				            pdfContentByte.rectangle(pageSize.getLeft() + 3, pageSize.getBottom() + 3, 
				              pageSize.getWidth() - 6, pageSize.getHeight() - 6); 
				            pdfContentByte.stroke();
				            document.setMarginMirroring(true);
				            
				          
				        	
							//below code for logo and header details
				            PdfPTable table00 = new PdfPTable(2);
				           
				         // Image img1 = Image.getInstance(getServlet().getServletContext().getRealPath("")+File.separator+"images"+File.separator+"header img.jpg");
				            table00.setSpacingBefore(20f); //Space before table
				            table00.setSpacingAfter(20f); //Space after table
				            String headerpath1=path+File.separator+"/header img.jpg";
				          Image img1=Image.getInstance(headerpath1);
				           img1.scaleAbsolute(90f, 90f);
				           img1.setAlignment(Image.LEFT); 
				            PdfPCell imageHeader2=new PdfPCell(img1,false);
				            
				           
				           // Image img2 = Image.getInstance(getServlet().getServletContext().getRealPath("")+File.separator+"images"+File.separator+"title efile.jpg");
				          //  Image img2 = Image.getInstance("D:\\igrs_27March\\IGRS\\WebContent\\images\\title efile.jpg");
				           
				           
				           //String headerpath=path+File.separator+"/Capture2.jpg";
				            //Image img2=Image.getInstance(headerpath);
				            //PdfPCell imageHeader3=new PdfPCell(img2,false);
				           
							
				            BarcodeEAN codeEAN = new BarcodeEAN();
				           codeEAN.setCode(regForm.getEFILETXNID());
				           codeEAN.setCodeType(Barcode.UPCA);
				         
							Image qr_image=codeEAN.createImageWithBarcode(pdfContentByte, BaseColor.BLACK, BaseColor.BLACK);
							qr_image.scaleAbsolute(90f, 90f);
							qr_image.setAlignment(Image.RIGHT);
							PdfPCell imageHeader1=new PdfPCell(qr_image,false);
						      //for local 
							//Image img2 = Image.getInstance("D:\\igrs_27March\\IGRS\\WebContent\\images\\title efile.jpg");
							
														
							imageHeader1.setBorder(0);
							imageHeader2.setBorder(0);
							//imageHeader3.setBorder(0);
				           
							 table00.addCell(imageHeader2);
						 //table00.addCell(imageHeader3);
				           table00.addCell(imageHeader1);
				            
				            float[] columnWidths=new float[]{20f,7f};
				            table00.setWidths(columnWidths);
				            table00.setSpacingAfter(20f);
				            //table00.setHorizontalAlignment(Element.ALIGN_CENTER);
				            
			 	            document.add(table00);
				           
				            
				            PdfPTable table15 = new PdfPTable(1); // 3 columns.
				            table15.setWidthPercentage(80); //Width 100%
				            table15.setSpacingBefore(10f); //Space before table
				            table15.setSpacingAfter(10f); //Space after table
				            
				            //Set Column widths
				            float[] columnWidths15 = {2f};
				            table15.setWidths(columnWidths15);
				            PdfPCell cell15=new PdfPCell(new Phrase ("ई -फाइलिंग विवरण",titleFont));
				            
				            cell15.setBackgroundColor(BaseColor.LIGHT_GRAY);
				            cell15.setBorderColor(BaseColor.BLACK);
				            cell15.setBorder(0);
				            cell15.setPaddingLeft(10);
				            cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
				            cell15.setVerticalAlignment(Element.ALIGN_CENTER);
				            table15.setSpacingBefore(20f); //Space before table
				            table15.setSpacingAfter(20f); //Space after table
				            table15.addCell(cell15);
				            document.add(table15); 
				            
				            
				            
				            //below for 1 table
				            PdfPTable table1 = new PdfPTable(2);
				            PdfPCell c1 = new PdfPCell();
				            
				            

				            c1 = new PdfPCell();
				            
				           

				            
				            table1.setHeaderRows(1);
				            
				            PdfPCell cellDeedName=new PdfPCell(new Phrase ("डीड नाम",titleFont));
				            table1.addCell(cellDeedName);
				            table1.addCell(regForm.getDEEDNAME());
				            PdfPCell cellEfileNo=new PdfPCell(new Phrase ("ई फाइलिंग नंबर ",titleFont));
				            table1.addCell(cellEfileNo);
				            
				            table1.addCell(regForm.getEFILETXNID());
				            PdfPCell cellEfileDate=new PdfPCell(new Phrase ("ई फाइलिंग की दिनांक ",titleFont));
				            table1.addCell(cellEfileDate);
				           
				            table1.addCell(regForm.getEFILEDATE());
				            PdfPCell cellEfileFee=new PdfPCell(new Phrase ("ई फाइलिंग की फ़ीस  ",titleFont));
				            table1.addCell(cellEfileFee);
				            
				            table1.addCell("500");
				            PdfPCell cellStampDuty=new PdfPCell(new Phrase ("स्टाम्प ड्यूटी ",titleFont));
				            table1.addCell(cellStampDuty);
				           
				            table1.addCell(regForm.getSTAMPDUTY());
				            PdfPCell cellAmt=new PdfPCell(new Phrase ("दस्तावेज द्वारा प्रतिभूत राशि  /ऋण राशि",titleFont));
				            table1.addCell(cellAmt);
				          
				            table1.addCell(" ");
				            PdfPCell cellLoan=new PdfPCell(new Phrase ("ऋण का विवरण ,ब्याज दर या अन्य विवरण ",titleFont));
				            table1.addCell(cellLoan);
				            
				            table1.addCell(regForm.getPURLOAN());
				            PdfPCell cellModePay=new PdfPCell(new Phrase ("भौतिक स्टाम्प अथवा /और ई स्टाम्प ",titleFont));
				            table1.addCell(cellModePay);
				            
				            if(regForm.getECODE()!=null && regForm.getECODE()!=""){
				            	table1.addCell(regForm.getECODE());
				            }else{
				            	table1.addCell("-");
				            	regForm.setECODE("-");
				            }
				            
				            PdfPCell cellExecDate=new PdfPCell(new Phrase ("निष्पादन दिनांक ",titleFont));
				            table1.addCell(cellExecDate);
				           
				            table1.addCell(regForm.getEXCDATE());
				            PdfPCell cellRecDate=new PdfPCell(new Phrase ("दस्तावेज प्राप्त करने की दिनांक ",titleFont));
				            table1.addCell(cellRecDate);
				            
				            table1.addCell(regForm.getDATEOFSUBMISSIONPO());
				            PdfPCell cellSRName=new PdfPCell(new Phrase ("उप पंजीयक का नाम ",titleFont));
				            table1.addCell(cellSRName);
				            
				            table1.addCell(regForm.getSRNAME());
				            PdfPCell cellSROffice=new PdfPCell(new Phrase ("उप पंजीयक कार्यालय ",titleFont));
				            table1.addCell(cellSROffice);
				           
				            table1.addCell(regForm.getSUBREGISTRAROFFICE());
				            document.add(table1);
				            //end of table 
				            
				            
				            
				            
				            //For Bank Details
				            
				            PdfPTable table16 = new PdfPTable(1); // 3 columns.
				            table16.setWidthPercentage(80); //Width 100%
				            table16.setSpacingBefore(20f); //Space before table
				            table16.setSpacingAfter(20f); //Space after table
				            
				            //Set Column widths
				            float[] columnWidths16 = {2f};
				            table16.setWidths(columnWidths16);
				            PdfPCell cell16=new PdfPCell(new Phrase ("बैंक  का विवरण",titleFont));
				            
				            cell16.setBackgroundColor(BaseColor.LIGHT_GRAY);
				            cell16.setBorderColor(BaseColor.BLACK);
				            cell16.setBorder(0);
				            cell16.setPaddingLeft(10);
				            cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
				            cell16.setVerticalAlignment(Element.ALIGN_CENTER);
				     
				            table16.addCell(cell16);
				            document.add(table16); 
				            
				            PdfPTable table2 = new PdfPTable(2);
				            PdfPCell c2 = new PdfPCell();
				            
				            

				            c2 = new PdfPCell();
				            
				           

				            
				            table2.setHeaderRows(1);
				            table2.setSpacingAfter(250f); //Space after table
				            PdfPCell cellBankName=new PdfPCell(new Phrase ("बैंक का नाम ",titleFont));
				            table2.addCell(cellBankName);
				            table2.addCell(regForm.getBANKNAME());
				            
				            PdfPCell cellAddress=new PdfPCell(new Phrase ("पता ",titleFont));
				            table2.addCell(cellAddress);
				            table2.addCell(regForm.getADDRESS());
				            
				            PdfPCell cellAutho=new PdfPCell(new Phrase ("अधिकृत व्यक्ति का नाम",titleFont));
				            table2.addCell(cellAutho);
				            table2.addCell(regForm.getBANKAUTHORITYNAME());
				            
				            PdfPCell cellDeg=new PdfPCell(new Phrase ("पद ",titleFont));
				            table2.addCell(cellDeg);
				            table2.addCell(regForm.getBANKAUTHORITYDESIGNATION());
				           
				                  document.add(table2);
				            //end of table 
				            
				            
				           
				            //End of Bank DEtails 
				            
				            
				            //For Property Details
				                  
				                  PdfPTable table17 = new PdfPTable(1); // 3 columns.
				  	            table17.setWidthPercentage(80); //Width 100%
				  	          table17.setSpacingBefore(20f); //Space before table
				  	            table17.setSpacingAfter(20f); //Space after table
				  	            //Set Column widths
				  	            float[] columnWidths17 = {2f};
				  	            table17.setWidths(columnWidths17);
				  	            PdfPCell cell17=new PdfPCell(new Phrase ("बैंक / ऋण दाता  द्वारा अंकित संपत्ति का विवरण",titleFont));
				  	            
				  	            cell17.setBackgroundColor(BaseColor.LIGHT_GRAY);
				  	            cell17.setBorderColor(BaseColor.BLACK);
				  	          cell17.setBorder(0);
				  	           
				  	            cell17.setPaddingLeft(10);
				  	            cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
				  	            cell17.setVerticalAlignment(Element.ALIGN_CENTER);
				  	     
				  	            table17.addCell(cell17);
				  	            document.add(table17); 
				  	            
				  	            PdfPTable table3 = new PdfPTable(2);
				  	            PdfPCell c3 = new PdfPCell();
				  	            
				  	            

				  	            c3 = new PdfPCell();
				  	            
				  	           

				  	            
				  	            table3.setHeaderRows(1);
				  	          ArrayList getpdfPropertyDetails =new ArrayList();
					  	        getpdfPropertyDetails= regForm.getGetpropPrintDetails();
						            RegCommonDTO rowList1;
						            for(int i = 0; i < getpdfPropertyDetails.size(); i++){
						            	rowList1 = (RegCommonDTO)getpdfPropertyDetails.get(i);
					  	            table3.setHeaderRows(1);
					  	            
					  	            PdfPCell cellName=new PdfPCell(new Phrase ("संपत्ति का प्रकार",titleFont));
					  	          table3.addCell(cellName);
					  	        table3.addCell(rowList1.getProtype());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("संपत्ति का विवरण",titleFont));
					  	          table3.addCell(cellName1);
					  	        table3.addCell(rowList1.getPropaddress());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("संपत्ति का पता",titleFont));
					  	          table3.addCell(cellName2);
					  	        table3.addCell(rowList1.getProparear());
					  	          
					  	            
						            }
				  	            
				  	            
				  	            
				  	            
				  	           
				  	                  document.add(table3);
				  	            //end of table 
				                  
				           
				            //End of Property DEtails
				            
				            
				            //For Party Details
				  	                  
				  	                /*PdfPTable table18 = new PdfPTable(1); // 3 columns.
					  	            table18.setWidthPercentage(80); //Width 100%
					  	            table18.setSpacingBefore(10f); //Space before table
					  	            table18.setSpacingAfter(10f); //Space after table
					  	            
					  	            //Set Column widths
					  	            float[] columnWidths18 = {2f};
					  	            table18.setWidths(columnWidths18);
					  	            PdfPCell cell18=new PdfPCell(new Phrase ("पक्षकारों का विवरण",font));
					  	            
					  	            cell18.setBackgroundColor(BaseColor.LIGHT_GRAY);
					  	            cell18.setBorderColor(BaseColor.BLACK);
					  	            cell18.setBorder(0);
					  	            cell18.setPaddingLeft(10);
					  	            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
					  	            cell18.setVerticalAlignment(Element.ALIGN_CENTER);
					  	     
					  	            table18.addCell(cell18);
					  	            document.add(table18); 
					  	            
					  	            PdfPTable table4 = new PdfPTable(2);
					  	            PdfPCell c4 = new PdfPCell();
					  	            
					  	            

					  	            c4 = new PdfPCell();
					  	            
					  	          ArrayList getpdfPartyDetails =new ArrayList();
						            getpdfPartyDetails= regForm.getGetpartyPrintDetails();
						            RegCommonDTO rowList;
						            for(int i = 0; i < getpdfPartyDetails.size(); i++){
						            	rowList = (RegCommonDTO)getpdfPartyDetails.get(i);
					  	            table4.setHeaderRows(1);
					  	            
					  	            PdfPCell cellName=new PdfPCell(new Phrase ("नाम",font));
					  	            table4.addCell(cellName);
					  	            table4.addCell(rowList.getPartyfirstname());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नाम ",font));
					  	            table4.addCell(cellName1);
					  	            table4.addCell(rowList.getPARTYLASTNAME());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",font));
					  	            table4.addCell(cellName2);
					  	            table4.addCell(rowList.getPARTYADDRESS());
					  	            
					  	            
						            }
					  	                  document.add(table4);
					  	                document.close();*/
				  	              //For Party Details
				  	                  
					  	                PdfPTable table18 = new PdfPTable(1); // 3 columns.
						  	            table18.setWidthPercentage(80); //Width 100%
						  	            table18.setSpacingBefore(10f); //Space before table
						  	            table18.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths18 = {2f};
						  	            table18.setWidths(columnWidths18);
						  	            PdfPCell cell18=new PdfPCell(new Phrase ("पक्षकारों का विवरण",titleFont));
						  	            
						  	            cell18.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	            cell18.setBorderColor(BaseColor.BLACK);
						  	          cell18.setBorder(0);
						  	            cell18.setPaddingLeft(10);
						  	            cell18.setHorizontalAlignment(Element.ALIGN_CENTER);
						  	            cell18.setVerticalAlignment(Element.ALIGN_CENTER);
						  	     
						  	            table18.addCell(cell18);
						  	            document.add(table18); 
						  	            
						  	            
						  	            
						  	            
						  	           
						  	          ArrayList getpdfPartyDetailsGov =new ArrayList();
						  	          ArrayList getpdfPartyDetailsInd =new ArrayList();
						  	        ArrayList getpdfPartyDetailsOrg =new ArrayList();
						  	      ArrayList getpdfPartyDetailsOrgPoA =new ArrayList();
						  	    ArrayList getpdfPartyDetailsIndPoA =new ArrayList();
							          
						  	     if( regForm.getGetpartyPrintDetailsGov()!=null && !regForm.getGetpartyPrintDetailsGov().isEmpty()){
						  	    	PdfPTable table4 = new PdfPTable(2);
					  	            PdfPCell c4 = new PdfPCell();
					  	            
					  	            

					  	            c4 = new PdfPCell();
						  	    	getpdfPartyDetailsGov=regForm.getGetpartyPrintDetailsGov();
						  	    	RegCommonDTO rowList;
						  	    	for(int i = 0; i < getpdfPartyDetailsGov.size(); i++){
						  	    		rowList = (RegCommonDTO)getpdfPartyDetailsGov.get(i);
						  	    		
						  	    		 table4.setHeaderRows(1);
						  	    		 
						  	    		//below code for heading of goverment offical
						  	    		 PdfPTable table19 = new PdfPTable(1); // 3 columns.
						  	    		table19.setWidthPercentage(80); //Width 100%
						  	    		table19.setSpacingBefore(10f); //Space before table
						  	    		table19.setSpacingAfter(10f); //Space after table
							  	            
							  	            //Set Column widths
							  	            float[] columnWidths19 = {2f};
							  	          table19.setWidths(columnWidths19);
							  	        String c=rowList.getGovpartyType();
							  	          String d="अधिकारी"+"("+c+")";
							  	            PdfPCell cell19=new PdfPCell(new Phrase (d,titleFont));
							  	            
							  	          cell19.setBackgroundColor(BaseColor.LIGHT_GRAY);
							  	        cell19.setBorderColor(BaseColor.BLACK);
							  	      cell19.setBorder(0);
							  	    cell19.setPaddingLeft(10);
							  	  cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
							  	cell19.setVerticalAlignment(Element.ALIGN_LEFT);
							  	     
							  	table19.addCell(cell19);
							  	
							  	            document.add(table19);  
						  	    		 
						  	    		 //end of code
						  	    		 
						  	            
						  	            
						  	          PdfPCell cellName3=new PdfPCell(new Phrase ("नाम",titleFont));
						  	            table4.addCell(cellName3);
						  	            table4.addCell(rowList.getGovdesg());
						  	            
						  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नाम",titleFont));
						  	            table4.addCell(cellName1);
						  	            table4.addCell(rowList.getGovfather());
						  	            
						  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",titleFont));
						  	            table4.addCell(cellName2);
						  	            table4.addCell(rowList.getGovaddress());
						  	           
						  	            
						  	          document.add(table4);
						  	        table4.flushContent();
						  	    	}
						  	  	
						  	     }
						  	     
						  	     
						  	   if( regForm.getGetpartyPrintDetailsInd()!=null && !regForm.getGetpartyPrintDetailsInd().isEmpty()){
						  		   
						  		 PdfPTable table5 = new PdfPTable(2);
					  	            PdfPCell c4 = new PdfPCell();
					  	            
					  	            

					  	            c4 = new PdfPCell();
						  		 getpdfPartyDetailsInd=regForm.getGetpartyPrintDetailsInd();
						  	    	RegCommonDTO rowList;
						  	    	for(int i = 0; i < getpdfPartyDetailsInd.size(); i++){
						  	    		rowList = (RegCommonDTO)getpdfPartyDetailsInd.get(i);
						  	    		 table5.setHeaderRows(1);
						  	    		 
						  	    		 
						  	    		//below code for heading of goverment offical
						  	    		 PdfPTable table20 = new PdfPTable(1); // 3 columns.
						  	    		table20.setWidthPercentage(80); //Width 100%
						  	    		table20.setSpacingBefore(10f); //Space before table
						  	    		table20.setSpacingAfter(10f); //Space after table
							  	            
							  	            //Set Column widths
							  	            float[] columnWidths20 = {2f};
							  	            table20.setWidths(columnWidths20);
							  	          String a=rowList.getIndpartyType();
							  	          String b="व्यक्तिगत"+"("+a+")";
							  	            PdfPCell cell20=new PdfPCell(new Phrase (b,titleFont));
							  	            
							  	          
							  	        
							  	          cell20.setBackgroundColor(BaseColor.LIGHT_GRAY);
							  	        cell20.setBorderColor(BaseColor.BLACK);
							  	      cell20.setBorder(0);
							  	    cell20.setPaddingLeft(10);
							  	  cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
							  	cell20.setVerticalAlignment(Element.ALIGN_LEFT);
							  	     
							  	table20.addCell(cell20);
							  
							  	            document.add(table20);  
						  	    		 
						  	    		 //end of code
						  	    		 
						  	    	//PdfPCell cellName=new PdfPCell(new Phrase ("Individual",titleFont));
						  	        //   table4.addCell(cellName);
						  	            
						  	            
						  	          PdfPCell cellName3=new PdfPCell(new Phrase ("नाम",titleFont));
						  	            table5.addCell(cellName3);
						  	            table5.addCell(rowList.getIndname());
						  	            
						  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नामe",titleFont));
						  	            table5.addCell(cellName1);
						  	            table5.addCell(rowList.getIndfather());
						  	            
						  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",titleFont));
						  	            table5.addCell(cellName2);
						  	            table5.addCell(rowList.getIndiaddress());
						  	           
						  	            
						  	       	document.add(table5);
						  	      table5.flushContent();
						  	    	}
						  	  
						  	     }
						  	   
						  	 if( regForm.getGetpartyPrintDetailsOrg()!=null && !regForm.getGetpartyPrintDetailsOrg().isEmpty()){
						  		 
						  		 PdfPTable table6 = new PdfPTable(2);
					  	            PdfPCell c4 = new PdfPCell();
					  	            
					  	            

					  	            //c4 = new PdfPCell();
						  		 getpdfPartyDetailsOrg=regForm.getGetpartyPrintDetailsOrg();
						  	    	RegCommonDTO rowList;
						  	    	for(int i = 0; i < getpdfPartyDetailsOrg.size(); i++){
						  	    		rowList = (RegCommonDTO)getpdfPartyDetailsOrg.get(i);
						  	    		 table6.setHeaderRows(1);
						  	    		 
						  	    		//below code for heading of goverment offical
						  	    		 PdfPTable table21 = new PdfPTable(1); // 3 columns.
						  	    		table21.setWidthPercentage(80); //Width 100%
						  	    		table21.setSpacingBefore(10f); //Space before table
						  	    		table21.setSpacingAfter(10f); //Space after table
							  	            
							  	            //Set Column widths
							  	            float[] columnWidths21 = {2f};
							  	            table21.setWidths(columnWidths21);
							  	          String e=rowList.getOrgpartyType();
							  	          String f="संगठन"+"("+e+")";
							  	            PdfPCell cell21=new PdfPCell(new Phrase (f,titleFont));
							  	            
							  	          cell21.setBackgroundColor(BaseColor.LIGHT_GRAY);
							  	        cell21.setBorderColor(BaseColor.BLACK);
							  	      cell21.setBorder(0);
							  	    cell21.setPaddingLeft(10);
							  	  cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
							  	cell21.setVerticalAlignment(Element.ALIGN_LEFT);
							  	     
							  	table21.addCell(cell21);
							  
							  	            document.add(table21);  
						  	    		 
						  	    		 //end of code
						  	    		 
						  	    		//PdfPCell cellName=new PdfPCell(new Phrase ("Organization",titleFont));
						  	         //  table4.addCell(cellName);
						  	            
						  	            
						  	          PdfPCell cellName3=new PdfPCell(new Phrase ("नाम",titleFont));
						  	            table6.addCell(cellName3);
						  	            table6.addCell(rowList.getOrgname());
						  	            
						  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नाम",titleFont));
						  	            table6.addCell(cellName1);
						  	            table6.addCell(rowList.getOrgfather());
						  	            
						  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",titleFont));
						  	            table6.addCell(cellName2);
						  	            table6.addCell(rowList.getOrgiaddress());
						  	           
						  	       	document.add(table6);
						  	      table6.flushContent();
						  	    		
						  	    	}
						  	  
						  	     }
						  	        
						  	    //below for poa organization
						  	if( regForm.getGetpartyPrintDetailsOrgPOA()!=null && !regForm.getGetpartyPrintDetailsOrgPOA().isEmpty()){
					  	    	PdfPTable table8 = new PdfPTable(2);
				  	            PdfPCell c25 = new PdfPCell();
				  	            
				  	            

				  	            c25 = new PdfPCell();
				  	          getpdfPartyDetailsOrgPoA=regForm.getGetpartyPrintDetailsOrgPOA();
					  	    	RegCommonDTO rowList;
					  	    	for(int i = 0; i < getpdfPartyDetailsOrgPoA.size(); i++){
					  	    		rowList = (RegCommonDTO)getpdfPartyDetailsOrgPoA.get(i);
					  	    		
					  	    		table8.setHeaderRows(1);
					  	    		 
					  	    		//below code for heading of goverment offical
					  	    		 PdfPTable table25 = new PdfPTable(1); // 3 columns.
					  	    		table25.setWidthPercentage(80); //Width 100%
					  	    		table25.setSpacingBefore(10f); //Space before table
					  	    		table25.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths25 = {2f};
						  	          table25.setWidths(columnWidths25);
						  	        String g=rowList.getPoaorgpartytype();
						  	          String h="संगठन"+"("+g+")";
						  	            PdfPCell cell25=new PdfPCell(new Phrase (h,titleFont));
						  	            
						  	          cell25.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	        cell25.setBorderColor(BaseColor.BLACK);
						  	      cell25.setBorder(0);
						  	    cell25.setPaddingLeft(10);
						  	  cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell25.setVerticalAlignment(Element.ALIGN_LEFT);
						  	     
						  	table25.addCell(cell25);
						  	
						  	            document.add(table25);  
					  	    		 
					  	    		 //end of code
					  	    		 
					  	            
					  	            
					  	          PdfPCell cellName3=new PdfPCell(new Phrase ("नाम",titleFont));
					  	        table8.addCell(cellName3);
					  	      table8.addCell(rowList.getPoaorgauthname());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नाम",titleFont));
					  	          table8.addCell(cellName1);
					  	        table8.addCell(rowList.getPoaorgfather());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",titleFont));
					  	          table8.addCell(cellName2);
					  	        table8.addCell(rowList.getPoaorgaddress());
					  	           
					  	      document.add(table8); 
					  		table8.flushContent();
					  	    	
					  	    	}
					  	  	
					  	     }
						  	
					  	    //below for poa ind
						  	if( regForm.getGetpartyPrintDetailsPOA()!=null && !regForm.getGetpartyPrintDetailsPOA().isEmpty() ){
					  	    	PdfPTable table7 = new PdfPTable(2);
				  	            PdfPCell c24 = new PdfPCell();
				  	            
				  	            

				  	            c24 = new PdfPCell();
				  	          getpdfPartyDetailsIndPoA=regForm.getGetpartyPrintDetailsPOA();
					  	    	RegCommonDTO rowList;
					  	    	for(int i = 0; i < getpdfPartyDetailsIndPoA.size(); i++){
					  	    		rowList = (RegCommonDTO)getpdfPartyDetailsIndPoA.get(i);
					  	    		
					  	    		table7.setHeaderRows(1);
					  	    		 
					  	    		//below code for heading of goverment offical
					  	    		 PdfPTable table24 = new PdfPTable(1); // 3 columns.
					  	    		table24.setWidthPercentage(80); //Width 100%
					  	    		table24.setSpacingBefore(10f); //Space before table
					  	    		table24.setSpacingAfter(10f); //Space after table
						  	            
						  	            //Set Column widths
						  	            float[] columnWidths24 = {2f};
						  	          table24.setWidths(columnWidths24);
						  	        String k=rowList.getPoapartytype();
						  	          String j="संगठन"+"("+k+")";
						  	            PdfPCell cell24=new PdfPCell(new Phrase (j,titleFont));
						  	            
						  	          cell24.setBackgroundColor(BaseColor.LIGHT_GRAY);
						  	        cell24.setBorderColor(BaseColor.BLACK);
						  	      cell24.setBorder(0);
						  	    cell24.setPaddingLeft(10);
						  	  cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
						  	cell24.setVerticalAlignment(Element.ALIGN_LEFT);
						  	     
						  	table24.addCell(cell24);
						  	
						  	            document.add(table24);  
					  	    		 
					  	    		 //end of code
					  	    		 
					  	            
					  	            
					  	          PdfPCell cellName3=new PdfPCell(new Phrase ("नाम",titleFont));
					  	        
					  	        table7.addCell(cellName3);
					  	      table7.addCell(rowList.getPoaauthname());
					  	            
					  	            PdfPCell cellName1=new PdfPCell(new Phrase ("पिता /पति /अभिभावक का नाम",titleFont));
					  	          table7.addCell(cellName1);
					  	        table7.addCell(rowList.getPoafather());
					  	            
					  	            PdfPCell cellName2=new PdfPCell(new Phrase ("पता",titleFont));
					  	          table7.addCell(cellName2);
					  	        table7.addCell(rowList.getPoaaddress());
					  	      
					  	  	document.add(table7);
					        table7.flushContent();
					  	 		
					  	    	}
					  	  
					  	     }
					
						  	 document.close();
					  	                 
								           
					  	                logger.debug("inside Print Pdf Action PDF Created in hindi----------->")	;
					  	          	forwardJsp="printDetailsPage1";
					  	          return mapping.findForward(forwardJsp);
				}
				        catch(Exception e){
				        	e.printStackTrace();
				        }
			}
		}
		}
		
				//digital sig
		  	              
	            if(RegInitConstant.PRINT_PDF_ACTION.equals(actionName)){
	            	

					
	            	logger.debug("inside Print Pdf Action before dig sign---------->")	;
	            	String efileNo=	(String) session.getAttribute("TempIdPrint");
					String TempEfileNo = commonBo.getTempEfileNo(efileNo);
					logger.debug("inside Print Pdf Action TempEfileNo ----------->" +TempEfileNo)	;
					 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
                      String path=pr.getValue("upload.location");
					
					logger.debug(" inside Print Pdf Action path ----------->" +path)	;
					String filePath=path+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo+CommonConstant.FILE_NAME_EFILE;
					logger.debug(" inside Print Pdf Action filePath ----------->" +filePath)	;
					String efileGenCert = commonBo.getefileGenCert(TempEfileNo);
					//PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					DocumentOperations docOperations = new DocumentOperations();
			        ODServerDetails connDetails = new ODServerDetails();
			       
			        logger.debug("value start:here");
			        logger.debug(pr.getValue("AppServerIp"));
			        logger.debug(pr.getValue("CabinetName"));
			        logger.debug(pr.getValue("AppServerUserId"));
			        logger.debug(pr.getValue("AppServerUserPass"));
			        logger.debug(pr.getValue("ImageServerIP"));
			        logger.debug(pr.getValue("ImageServerPort"));
			        Dataclass metaDataInfo = new Dataclass();
			        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			        connDetails.setCabinetName(pr.getValue("CabinetName"));
			        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			        connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			        metaDataInfo.setUniqueNo(TempEfileNo);
			        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			        logger.debug("inside Print Pdf Action line550---------->")	;
					if(efileGenCert.equalsIgnoreCase("Y"))   // Certificate already generated .....code to download that
					{
						if(language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.FAILURE_MSG,"The E-filing has already been printed");
						else
							request.setAttribute(CommonConstant.FAILURE_MSG,"ई -फाइलिंग प्रमाणपत्र पहले से ही मुद्रित किया गया है।");
						  
						forwardJsp="printDetailsPage1";
						
					}
					else
					{
						  logger.debug("inside Print Pdf Action line822 else condition to apply digital sign---------->")	;
						 String     outputPath = filePath;
					
				      	
							
				
					//String signFlag=pr.getValue("digital_sign_flag");
					//  EstampingBD objEstBd = new EstampingBD();
					 // String     outputPath = filePath;
					
					
					//new DocumentService().sign(outputPath+File.separator+ "Efiling.PDF", outputPath+File.separator+ "EfilingSigned.Pdf", userId, 100, 100, 200, 200);
						 byte[] eFileByte=DMSUtility.getDocumentBytes(outputPath);
							request.setAttribute("eFileByte", eFileByte);
							request.setAttribute("eFile_FilePath", outputPath);
							
							forwardJsp="eFileSignPDF";
							logger.debug("BYTE ARRAY IS:::::::"+eFileByte+"FINAL PATH IS::::::::"+outputPath);
							logger.debug("DIG PATH IS:::::::"+"Dig PATH IS::::::::"+outputPath);
							logger.debug("CODE TRANSFERRED TO E-MUDHRA");
							return mapping.findForward(forwardJsp);
						 
						 
    
					 	
					
					}
					}
	            
	            //below code for merging document
	            if(CommonConstant.CANCEL_ACTION.equals(actionName)) {
	            	regForm.setActionName("");
					forwardJsp = "welcome";
					return mapping.findForward(forward);
				}
	            
	            if("DOC_SIGNED_DSC".equalsIgnoreCase(actionName)){
	            	

					// For the pdf creation of efile certificate.
	            	logger.debug(" PrintPdf Action DOC_SIGNED_DSC"+regForm.getSignedPath());
					String outputPath=regForm.getSignedPath();
					if(outputPath!=null && !outputPath.isEmpty())
					{
						logger.debug("  PrintPdf Action output path not null");
					//String estampGenCert = objEstampBO.getCertChkDetails(estampFormBean.getObjEstampDet().getMainTxnId().toString());
					String efileNo=	(String) session.getAttribute("TempIdPrint");
					String TempEfileNo = commonBo.getTempEfileNo(efileNo);
					String efileGenCert = commonBo.getefileGenCert(TempEfileNo);
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					DocumentOperations docOperations = new DocumentOperations();
			        ODServerDetails connDetails = new ODServerDetails();
			        logger.debug("value start:here");
			        logger.debug(pr.getValue("AppServerIp"));
			        logger.debug(pr.getValue("CabinetName"));
			        logger.debug(pr.getValue("AppServerUserId"));
			        logger.debug(pr.getValue("AppServerUserPass"));
			        logger.debug(pr.getValue("ImageServerIP"));
			        logger.debug(pr.getValue("ImageServerPort"));
			        Dataclass metaDataInfo = new Dataclass();
			        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			        connDetails.setCabinetName(pr.getValue("CabinetName"));
			        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			        connDetails.setIniPath("D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			        metaDataInfo.setUniqueNo(TempEfileNo.toString());
			        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
			        if(efileGenCert!=null && efileGenCert.equalsIgnoreCase("Y"))
			        {
			        	regForm.setPrintCheck("N");
						forwardJsp = "printDetailsPage1";
						return mapping.findForward(forwardJsp);
			        }
					
					try
				      {
						ReadPropertiesFile prop = new ReadPropertiesFile();
							metaDataInfo.setUniqueNo(TempEfileNo.toString());
								//String	fileName = "EStamp.PDF";
									String path = outputPath;//+File.separator+fileName;
									logger.debug("path^^^^^^^^^^^^^^^^^^^^^^^^^^^"+path);
									File file = new File(path);
									if (!file.exists()) {
										throw new Exception();
									}
									
									metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
									metaDataInfo.setType("E");
									metaDataInfo.setLatestFlag("L");
									String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);
									logger.debug("doc Id "+docId);
									boolean flag = false;
									if(docId != "-1")
	  								{
	  									// update generate cert status in table
	  									 flag = commonBo.updateCertificateGenerationChk(TempEfileNo);
	  									session.setAttribute("estampID", "estampreprint");
	  									
	  								}
									if(flag){
										regForm.setPrintCheck("N");
									}
									String downloadPath=pr.getValue("upload.location");
								    downloadPath=downloadPath+File.separator+"IGRS//EFILING"; // Added By Gulrej on 16th Aug, 2017
				                     
									 String guid=GUIDGenerator.getGUID();
								        //String EstampPath = CommonConstant.ESTAMP_DOWNLOAD_PATH+estampFormBean.getObjEstampDet().getEcode().toString();
								      //  String EstampPath1 = CommonConstant.ESTAMP_DOWNLOAD_PATH_LINUX+guid;
								      String efilePath1 = downloadPath+File.separator+guid;

								        File output1;
										output1 = new File(efilePath1.toString());
										if (output1.exists()) {
											logger.info("file already exists deleting....");
											output1.delete();
										}
										if (output1.getParentFile().exists() == false) {
											logger.info("Parent not found creating directory....");
											output1.getParentFile().mkdirs();
										}
										metaDataInfo.setType("E");
										metaDataInfo.setLatestFlag("L");
								        int result = docOperations.downloadDocument(connDetails,metaDataInfo,efilePath1,CommonConstant.MODULE_NAME);
									
								        regForm.setOwmFlag("Y");
								        regForm.setOwmFile(efilePath1+File.separator+CommonConstant.FILE_NAME_EFILE);
									  logger.debug("OWN FILE PATH::::::"+ regForm.getOwmFile());
									  logger.debug("download result---------->"+result);
	  								 // below code to view the doc
	  						        // set the http content type to "APPLICATION/OCTET-STREAM
									forwardJsp="printDetailsPage1";
									
									
					}
					catch(Exception e)
					{
						logger.debug(e.getStackTrace());
						forwardJsp = "failure";
					}
					forwardJsp="printDetailsPage1";
					}else{
						if(language.equalsIgnoreCase("en"))
							request.setAttribute(CommonConstant.SUCCESS_MSG_PRINT1,"Could not Apply the Digital Signature");
						else
							request.setAttribute(CommonConstant.SUCCESS_MSG_PRINT1,"Could not Apply the Digital Signature");
						
						forwardJsp = "printDetailsPage1";
					}
					
				
	            	
	            	/* Commented By Gulrej on 31st July, 2017 -- Start*/  
	            	
	            	/*PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					DocumentOperations docOperations = new DocumentOperations();
			        ODServerDetails connDetails = new ODServerDetails();
			        Dataclass metaDataInfo = new Dataclass();
			        connDetails.setAppServerIp(pr.getValue("AppServerIp"));
			        connDetails.setCabinetName(pr.getValue("CabinetName"));
			        connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));
			        connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));
			        connDetails.setImageServerIp(pr.getValue("ImageServerIP"));
			        connDetails.setImageServerPort(pr.getValue("ImageServerPort"));
			        connDetails.setIniPath("D:\\simran\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");
			       
			        metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));
	            	String efileNo=	(String) session.getAttribute("TempIdPrint");
					String TempEfileNo = commonBo.getTempEfileNo(efileNo);
					 
                     String path=pr.getValue("upload.location");
                     
                   //below code if digital sign is not applied and document need not to be downloaded
                     
                     
 					String deedpath = path+ File.separator +"EFILING"+ File.separator+TempEfileNo;
 					String EfilingSignedPath1 = deedpath + File.separator + "Efiling.Pdf";
 					byte bytes1[] = DMSUtility.getDocumentBytes(EfilingSignedPath1);
 					PdfReader reader = new PdfReader(bytes1);
 					AcroFields acroFields = reader.getAcroFields();
                     List<String> signatureNames = acroFields.getSignatureNames();
                     logger.debug("reading digital signature"+signatureNames)	;
 					if(signatureNames.isEmpty()){
 						
 						request.setAttribute(CommonConstant.SUCCESS_MSG_PRINT1,"Could not Apply the Digital Signature");
 						forward = "printDetailsPage1";
 			    		return mapping.findForward(forward);
 					}
 					//end of code 
                     
                     
	            boolean	flag = commonBo.updateCertificateGenerationChk(TempEfileNo);
					 logger.debug("inside Print Pdf Action line841 flag updated in DB status updated---------->")	;
	            	 logger.debug("inside Print Pdf Action line587 to merge file ---------->")	;
	            String filepathmerge=path+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo+CommonConstant.FILE_MERGE_PATH;
	           
	            String filePathDes;
	            filePathDes=path+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo;
	           
	            
	            String fileOne=filePathDes+CommonConstant.FILE_NAME_EFILE;
	        	String fileSecond=filePathDes+CommonConstant.FILE_UPLOAD_INITATE;
	        	
	        	FileInputStream f1=new FileInputStream(fileOne);
	        	FileInputStream f2=new FileInputStream(fileSecond);
	        	
	        	PDFMergerUtility ut = new PDFMergerUtility();
	        	ut.addSource(f1);
	        	ut.addSource(f2);
	        	ut.setDestinationFileName(filepathmerge);
	        	ut.mergeDocuments();
	        	logger.debug("merging Done--->");
	        	System.out.println("merging done---->");
	        	  logger.debug("inside Print Pdf Action end of merging done--------->")	;
	        	
	        	//to view download signedEfiling pdf
	        	
	        	
	    			PropertiesFileReader pr1 = PropertiesFileReader
	    			.getInstance("resources.igrs");
	    			
	    			
	    	String downloadPath = pr1.getValue("igrs_upload_path");
	    	
	    	String EfilingSignedPath = downloadPath	+CommonConstant.FILE_UPLOAD_PATH+TempEfileNo+CommonConstant.FILE_MERGE_PATH;
	    	//EfilingSignedPath=path+CommonConstant.FILE_UPLOAD_PATH+efileTxnId+CommonConstant.FILE_MERGE_PATH;
	    	
	    	
	    	byte bytes[] = DMSUtility.getDocumentBytes(EfilingSignedPath);
	    	if (bytes != null) {
	    		DMSUtility.downloadDocument(response, CommonConstant.FILE_MERGE_PATH, bytes);
	    		 logger.debug("inside Print Pdf Action download complete -------->")	;
	    		forward = "printDetailsPage2";
	    		return mapping.findForward(forward);
	    	}
	    		
	            }
	            else{
	            	
	            	if(language.equalsIgnoreCase("en")){
		        		request.setAttribute(CommonConstant.SUCCESS_MSG_PRINT1,"Could not Apply the Digital Signature");
		        	}
		        	
	            	forward = "printDetailsPage2";
		    		return mapping.findForward(forward);
	            }
	            return mapping.findForward(forwardJsp);	*/
					
					/* Commented By Gulrej on 31st July, 2017 -- End */
	        
	            }
	            return mapping.findForward(forwardJsp);
	}
}
			


		


package com.wipro.igrs.revenueManagement.action;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import in.cdac.ilcg.jasperreports.pdfexporter.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.bd.RevMgtBD;
import com.wipro.igrs.revenueManagement.bo.RevMgtBO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;
import com.wipro.igrs.revenueManagement.form.RevMgtForm;
import com.wipro.igrs.util.PropertiesFileReader;


public class RevMgtAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(RevMgtAction.class);
	private String forwardPage = "";
	private String reqParam = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		try {
			if (form != null) {
				String locale="";
				Locale currentLocale=new Locale(locale);
				if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
						currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
						locale=currentLocale.toString();
						
					}
				String languageLocale=RegInitConstant.LANGUAGE_HINDI;
				if(session.getAttribute("languageLocale")!=null){
					languageLocale=(String)session.getAttribute("languageLocale");
				}
				
				RevMgtForm eForm = (RevMgtForm) form;
				RevMgtBD revBD = new RevMgtBD();
				RevMgtBO bo = new RevMgtBO();
				RevMgtDTO dto = eForm.getRmDto();
				String userId = (String) session.getAttribute("UserId");
				String officeId = (String) session
						.getAttribute("loggedToOffice");
				eForm.setUid(userId);
				String nextPage = "";
				String formName = eForm.getFormName();
				dto.setDistrictNameList(bo.getDistrictList(languageLocale));
				// eForm.getRmDto().setViewResultList(null);
				// String actionName = eForm.getActionName();
				logger.debug("inside action class, Action nmae is......."
						+ eForm.getActionName());

				String label = null;

				label = (String) request.getParameter("label");

				// for Revenue Collection
				if (label != null) {
					eForm.setActionName("");
					formName = "";
					if (label.equalsIgnoreCase("revCltRpt")) {

						dto.setRadioMode("");
						
						dto.setName("");
						dto.setValue("");

						// dto.setOfficeIdList(new ArrayList());
						// dto.setOfficeNameList(new ArrayList());
						dto.setIsViewEmpty(0);
						request.removeAttribute("viewCashRevenue");
						dto.setViewCashRevenue(new ArrayList());
						dto.setRevenueOfficeName("");
						dto.setRevenueDistrictId("");
						dto.setRevenueDistrictName("");
						dto.setRevenueOfficeId("");
						dto.setRevenueOfficeTypeName("");
						dto.setRevenueToDate("");
						dto.setRevenueFromDate("");
						dto.setCashTransactionId("");
						dto.setCashCreatedDate("");
						dto.setCashFunctionId("");
						dto.setCashFunctionName("");
						dto.setCashGrossAmt("");
						dto.setCashUserCreatedBy("");
						dto.setCashPaymentType("");
						

						forwardPage = "FifthJsp";
					}
					if (label.equalsIgnoreCase("revClt")) {

						forwardPage = "SixthJsp";
					}
				//}

				// for Transaction Mapping
			//	if (label != null) {
					if (label.equalsIgnoreCase("revRecon")) {

						//request.removeAttribute("viewResultList");
						// session.removeAttribute("combSt");
						dto.setIsViewEmpty(0);
						//eForm.setActionName("");
						//formName = "";
						//dto.setDistrictName("");
						dto.setDurationFrom("");
						dto.setDurationTo("");
						dto.setHdnTxnId("");
						dto.setOfficeName("");
						dto.setPaymentType("");
						dto.setRadioSelect("");
						dto.setRadioSelectSecond("");
						dto.setRadioSelectReceipt("");
						dto.setReceiptId("");
						dto.setReceiptID("");
						//dto.setTxnAmt(0.0);
						dto.setTxnAmt("0");
						dto.setTxnDate("");
						dto.setTxnId("");
						dto.setTxnPurpose("");
						dto.setTxnType("");
						dto.setUserId("");
						dto.setPurposeID("");
						dto.setPurposeList(bo.getPurposeList(languageLocale));
						eForm.setToDate("");
						eForm.setFromDate("");
						// dto.setViewComb("");
						//dto.setViewResultList(new ArrayList()); 

						forwardPage = "FirstJsp";
					}
					
					
			//	}

				// for Receipt Search

			//	if (label != null) {
					if (label.equalsIgnoreCase("receiptSearchLabel")) {

						request.removeAttribute("viewResultList");
						eForm.setActionName("");
						formName = "";
						dto.setIsViewEmpty(0);
						dto.setRadioSelectReceipt2("");
						dto.setRadioSelectReceipt("");
						dto.setReceiptTextBox("");
						dto.setViewResult(new ArrayList());

						forwardPage = "ThirdJsp";
					}
				}

				// On hyperlink click in Transaction Mapping
				if (request.getParameter("combSt") != null) {

					String comDet = request.getParameter("combSt");
					String stCumDtList[] = comDet.split("~");
					String txnId = "";
					//Double txnAmt;
					String txnAmt="";
					String txnDate = "";
					String receiptId = "";
					String paymentPurpose = "";
					String paymentTypeName = "";
					String userId1 = "";
					String districtName = "";
					String officeName = "";
					//double txnAmount = 0.0;
					logger.debug("the size of array is........"
							+ stCumDtList.length);
					if (stCumDtList.length == 9) {
						txnId = stCumDtList[0];
						//txnAmt = (Double.parseDouble(stCumDtList[1]));
						txnAmt = stCumDtList[1];
						//txnAmount = Double.valueOf(txnAmt);
						txnDate = stCumDtList[2];
						paymentTypeName = stCumDtList[3];
						userId1 = stCumDtList[4];
						txnAmt = new BigDecimal(txnAmt).toPlainString();
						
					
							paymentPurpose = stCumDtList[5];
						
						
						if(!"null".equalsIgnoreCase(stCumDtList[6]) && !"".equalsIgnoreCase(stCumDtList[6])){
							officeName = stCumDtList[6];
						}else{
							officeName="-";
						}
						
						if(!"null".equalsIgnoreCase(stCumDtList[7]) && !"".equalsIgnoreCase(stCumDtList[7])){
							districtName = stCumDtList[7];
						}else{
							districtName="-";
						}
						if(!"null".equalsIgnoreCase(stCumDtList[8]) && !"".equalsIgnoreCase(stCumDtList[8])){
							receiptId = stCumDtList[8];
						}else if("-".equalsIgnoreCase(stCumDtList[8])){
							receiptId="-";
						}else{
							receiptId="-";
						}
						
						
						
						

						dto.setHdnTxnId(txnId);
						dto.setTxnId(txnId);
						//dto.setTxnAmt(txnAmt);
						dto.setTxnAmt(txnAmt);
						dto.setTxnDate(txnDate);
						dto.setReceiptId(receiptId);
						dto.setTxnPurpose(paymentPurpose);
						dto.setTxnType(paymentTypeName);
						dto.setUserId(userId1);
						dto.setDistrictName(districtName);
						dto.setOfficeName(officeName);

						

					}
					formName="";
					forwardPage = "SecondJsp";
				}
				
				

				// start of Revenue Collection PDF form
				if (formName != null
						&& formName
								.equalsIgnoreCase("ShowRevenueCollectionReport")) {

					if (eForm.getActionName().equalsIgnoreCase(
							"generateReportPDF")) {
						eForm.setActionName("REVENUECOLLECTIONSEARCH");
						eForm.setFormName("revReconcSearchPage");
						dto.setPdfOfficeName(bo.getOfficeName(dto.getRevenueOfficeTypeName(), languageLocale));
						
						//added by ----------->SHREERAJ<----------
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_HINDI)){
							 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
								//System.out.println(request.getContextPath().concat("/WebContent/JasperXML/"));
								
					//	String fileName=getClass().getClassLoader().getResource("D:\\SRK_WOKSPACE_IGRS\\"+request.getContextPath().concat("/WebContent/JasperXML/").concat("RevenueCollectionReport.jrxml")).getFile();
					/*	File theFile=new File(property.getValue("pdf.location").concat("RevenueCollectionReport.pdf"));
						JasperDesign jasperDesign = JRXmlLoader.load(theFile);*/
						
						
						
							String radioMode = dto.getRadioMode();
							String revenueDistrictId = dto.getRevenueDistrictId();
							String revenueOfficeTypeName = dto.getRevenueOfficeTypeName();
							
							String revenueToDate = eForm.getRevenueToDate();
							String revenueFromDate = eForm.getRevenueFromDate();
							String sroName=dto.getPdfOfficeName();
							String trnsType="";
							JasperReport jasperReport;
						 	JasperPrint jasperPrint;
						 	// String sql="";
						 	
			
						 	HashMap jasperMap=new HashMap();
						//	jasperMap.put("SQLquery", sql);
						 	jasperMap.put("districtID", revenueDistrictId);
							jasperMap.put("officeID", revenueOfficeTypeName);
							jasperMap.put("fromDate", revenueFromDate);
							jasperMap.put("toDate", revenueToDate);
							jasperMap.put("paymentID", radioMode);
							jasperMap.put("sroName", sroName);
							jasperMap.put(JRParameter.REPORT_LOCALE, currentLocale);
							jasperMap.put("totSum",dto.getSum());
							jasperMap.put("totSumSampda",dto.getSampadaSum());
							
							DBUtility dbUtil = new DBUtility();
							
							//System.out.println(request.getContextPath().concat("/WebContent/JasperXML/"));
							if (radioMode.equalsIgnoreCase("2") || radioMode.equalsIgnoreCase("3")){
								
								jasperMap.put("transType", trnsType);
						 	jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueCollectionReport.jrxml"));	
						 	jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
						 	//JasperViewer.viewReport(jasperPrint);
						 	//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueCollectionReport.pdf"));
							
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
							}
							if (radioMode.equalsIgnoreCase("1")){
								trnsType="नकद";
								jasperMap.put("transType", trnsType);
							 	jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueCollectionReportCash.jrxml"));	
							 	jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
							 //	JasperViewer.viewReport(jasperPrint);
							 	//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueCollectionReportCash.pdf"));
							 	
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
								}
						 	//shruti mam approach 
							/*  CommonUtil cmUtil=new CommonUtil();
							  //path of jrxml report for the functionality
							  PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
							  String reportPath = property.getValue("pdf.location")+"RevenueCollectionReport.jrxml";   
							  System.out.println(">>>>"+reportPath);
							// This hashmap is used for passing parameters to your jrxml template.
				            //Here the first parameter denotes the name of the variable in your
							// jrxml template and the second paramater passes the value
							  HashMap jasperMap=new HashMap();
							 	jasperMap.put("districtID", dto.getRevenueDistrictId());
								jasperMap.put("officeID", dto.getRevenueOfficeTypeName());
								jasperMap.put("fromDate", eForm.getRevenueFromDate());
								jasperMap.put("toDate", eForm.getRevenueToDate());
								jasperMap.put("paymentID", dto.getRadioMode());
								jasperMap.put("sroName", dto.getPdfOfficeName());
							cmUtil.convertPdfHindi(jasperMap,reportPath,response);*/
						}else{

						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						Document document = new Document(PageSize.A4, 20, 20,
								70, 20);
						PdfWriter.getInstance(document, baos);
						document.open();
						String radioMode = dto.getRadioMode();
						Table datatable=null;
						int x=0;
						if (radioMode.equalsIgnoreCase("2")){
							x=28;
							 datatable = new Table(x);
						}else{
							x=24;
							 datatable = new Table(x);
						}
					
						datatable.setWidth(100);
						datatable.setPadding(4);

						Cell title = new Cell(new Phrase(
								"Revenue Collection Report",
								FontFactory.getFont(FontFactory.COURIER, 16,
										Font.BOLD)));
						title.setHorizontalAlignment(Element.ALIGN_CENTER);
						title.setLeading(20);
						title.setColspan(x);
						title.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(title);
						datatable.setAlignment(1);

						
						Cell title2 = new Cell(new Phrase(
								dto.getPdfOfficeName() + ", "
										+ dto.getRevenueDistrictName(),
								FontFactory.getFont(FontFactory.COURIER, 16,
										Font.BOLD)));
						title2.setHorizontalAlignment(Element.ALIGN_CENTER);
						title2.setLeading(20);
						title2.setColspan(x);
						title2.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(title2);
						datatable.setAlignment(1);

						Cell transIdHeader = new Cell(new Phrase(
								"Transaction Id", FontFactory.getFont(
										FontFactory.COURIER, 10, Font.BOLD)));
						transIdHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						transIdHeader.setLeading(20);
						transIdHeader.setColspan(4);
						transIdHeader.setBackgroundColor(new Color(0xC0, 0xC0,
								0xC0));
						datatable.addCell(transIdHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell transPurposeHeader = new Cell(new Phrase("Date",
								FontFactory.getFont(FontFactory.COURIER, 10,
										Font.BOLD)));
						transPurposeHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						transPurposeHeader.setLeading(20);
						transPurposeHeader.setColspan(4);
						transPurposeHeader.setBackgroundColor(new Color(0xC0,
								0xC0, 0xC0));
						datatable.addCell(transPurposeHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell districtHeader = new Cell(new Phrase(
								"Transaction Purpose", FontFactory.getFont(
										FontFactory.COURIER, 10, Font.BOLD)));
						districtHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						districtHeader.setLeading(20);
						districtHeader.setColspan(4);
						districtHeader.setBackgroundColor(new Color(0xC0, 0xC0,
								0xC0));
						datatable.addCell(districtHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell OfficeHeader = new Cell(new Phrase("User Id",
								FontFactory.getFont(FontFactory.COURIER, 10,
										Font.BOLD)));
						OfficeHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						OfficeHeader.setLeading(20);
						OfficeHeader.setColspan(4);
						OfficeHeader.setBackgroundColor(new Color(0xC0, 0xC0,
								0xC0));
						datatable.addCell(OfficeHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell transTypeHeader = new Cell(new Phrase(
								"Transaction Type", FontFactory.getFont(
										FontFactory.COURIER, 10, Font.BOLD)));
						transTypeHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						transTypeHeader.setLeading(20);
						transTypeHeader.setColspan(4);
						transTypeHeader.setBackgroundColor(new Color(0xC0,
								0xC0, 0xC0));
						datatable.addCell(transTypeHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell userIdHeader = new Cell(new Phrase(
								"Amount collected by Treasury(INR)", FontFactory.getFont(
										FontFactory.COURIER, 10, Font.BOLD)));
						userIdHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						userIdHeader.setLeading(20);
						userIdHeader.setColspan(4);
						userIdHeader.setBackgroundColor(new Color(0xC0, 0xC0,
								0xC0));
						datatable.addCell(userIdHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);
						if (radioMode.equalsIgnoreCase("2")){
						Cell sampadaHeader = new Cell(new Phrase(
								"Amount collected by SAMPADA Application(INR)", FontFactory.getFont(
										FontFactory.COURIER, 10, Font.BOLD)));
						sampadaHeader
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						sampadaHeader.setLeading(20);
						sampadaHeader.setColspan(4);
						sampadaHeader.setBackgroundColor(new Color(0xC0, 0xC0,
								0xC0));
						datatable.addCell(sampadaHeader);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);
						}

						ArrayList list = (ArrayList) dto.getViewCashRevenue();
						for (int i = 0; i < list.size(); i++) {
							RevMgtDTO gDTO = (RevMgtDTO) list.get(i);

							Cell transId = new Cell(new Phrase(
									gDTO.getCashTransactionId(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							transId.setHorizontalAlignment(Element.ALIGN_CENTER);
							transId.setLeading(20);
							transId.setColspan(4);
							datatable.addCell(transId);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);

							Cell transpurp = new Cell(new Phrase(
									gDTO.getCashCreatedDate(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							transpurp
									.setHorizontalAlignment(Element.ALIGN_CENTER);
							transpurp.setLeading(20);
							transpurp.setColspan(4);
							datatable.addCell(transpurp);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);

							Cell district = new Cell(new Phrase(
									gDTO.getCashFunctionName(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							district.setHorizontalAlignment(Element.ALIGN_CENTER);
							district.setLeading(20);
							district.setColspan(4);
							datatable.addCell(district);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);

							
							Cell trans = new Cell(new Phrase(
									gDTO.getCashUserCreatedBy(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							trans.setHorizontalAlignment(Element.ALIGN_CENTER);
							trans.setLeading(20);
							trans.setColspan(4);
							datatable.addCell(trans);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);

							

							Cell distric = new Cell(new Phrase(
									gDTO.getCashPaymentType(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							distric.setHorizontalAlignment(Element.ALIGN_CENTER);
							distric.setLeading(20);
							distric.setColspan(4);
							datatable.addCell(distric);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);

							Cell transpur = new Cell(new Phrase(
									gDTO.getCashGrossAmt(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							transpur.setHorizontalAlignment(Element.ALIGN_CENTER);
							transpur.setLeading(20);
							transpur.setColspan(4);
							datatable.addCell(transpur);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);
							
							if (radioMode.equalsIgnoreCase("2")){	
							Cell sampur = new Cell(new Phrase(
									gDTO.getAmtTreasury(),
									FontFactory.getFont(FontFactory.COURIER,
											10, Font.BOLD)));
							sampur.setHorizontalAlignment(Element.ALIGN_CENTER);
							sampur.setLeading(20);
							sampur.setColspan(4);
							datatable.addCell(sampur);
							datatable.setBorderWidth(2);
							datatable.setAlignment(1);
							}

						}

						Cell transAmTotDw = new Cell(new Phrase("Total (INR)",
								FontFactory.getFont(FontFactory.COURIER, 10,
										Font.BOLD)));
						transAmTotDw
								.setHorizontalAlignment(Element.ALIGN_RIGHT);
						transAmTotDw.setLeading(20);
						transAmTotDw.setColspan(20);
						transAmTotDw.setBorder(Rectangle.NO_BORDER);
						datatable.addCell(transAmTotDw);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);

						Cell transAmTotDwn = new Cell(new Phrase(dto.getSum(),
								FontFactory.getFont(FontFactory.COURIER, 10,
										Font.BOLD)));
						transAmTotDwn
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						transAmTotDwn.setLeading(20);
						transAmTotDwn.setColspan(4);
						datatable.addCell(transAmTotDwn);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);
						if (radioMode.equalsIgnoreCase("2")){
						Cell sampadaAmTotDwn = new Cell(new Phrase(dto.getSampadaSum(),
								FontFactory.getFont(FontFactory.COURIER, 10,
										Font.BOLD)));
						sampadaAmTotDwn
								.setHorizontalAlignment(Element.ALIGN_CENTER);
						sampadaAmTotDwn.setLeading(20);
						sampadaAmTotDwn.setColspan(4);
						datatable.addCell(sampadaAmTotDwn);
						datatable.setBorderWidth(2);
						datatable.setAlignment(1);
						}
						datatable.setCellsFitPage(true);
						document.add(datatable);
						document.close();
						response.setContentType("Report/pdf");
						response.setHeader("Content-Disposition",
								"attachment; filename=\"Report.pdf");
						response.setContentLength(baos.size());
						ServletOutputStream out = response.getOutputStream();
						baos.writeTo(out);
						
						/*eForm.setActionName("REVENUECOLLECTIONSEARCH");*/
						out.flush();
						
					}
					}
					
					//hhh
					/*forwardPage = "Fifth.jsp";*/
				}

				
				
				
				
				
				
				
				
				
				
				
				// Transaction Mapping
				
				if (formName != null
						&& formName.equalsIgnoreCase("SearchDetails")) {

					// for Primary And Secondary Radio Buttons
					if (eForm.getActionName().equalsIgnoreCase("SetDataCheckbox")) {
						logger.debug("the value of radio button is......");
						String radioValue = dto.getRadioSelect();
						logger.debug("the value of radio button is......"+ radioValue);
						// dto.setRadioSelect2(radioValue);
							
						dto.setRadioSelectSecond(radioValue);
						dto.setPaymentType("");
						eForm.setRmDto(dto);
						eForm.getRmDto().setCreditLimitMsg("");
						eForm.getRmDto().setChallanMsg("");
						//eForm.setToDate("");
						//eForm.setFromDate("");
						//formName = "";
						//eForm.setActionName("");
						forwardPage = "FirstJsp";

					}

					// reset

					if (eForm.getActionName().equalsIgnoreCase("Reset")) {

						//request.removeAttribute("viewResultList");
						// session.removeAttribute("combSt");
						//dto.setDistrictName("");
						//dto.setDurationFrom("");
						//dto.setDurationTo("");
						dto.setHdnTxnId("");
						dto.setOfficeName("");
						dto.setPaymentType("");
						dto.setRadioSelect("");

						dto.setRadioSelectSecond("");
						dto.setReceiptId("");
						dto.setReceiptID("");
						//dto.setTxnAmt(0.0);
						dto.setTxnAmt("0");
						dto.setTxnDate("");
						dto.setTxnId("");
						dto.setTxnPurpose("");
						dto.setTxnType("");
						dto.setUserId("");
						dto.setIsViewEmpty(0);
						dto.setPurposeID("");
						eForm.setFromDate("");
						eForm.setToDate("");
						// dto.setViewComb("");
						//dto.setViewResultList(new ArrayList());

						forwardPage = "FirstJsp";
					}

					// start of action viewSearch
					if ("SEARCH".equalsIgnoreCase(eForm.getActionName())) {

						String paymentType = "";
						String fromDate = "";
						String toDate = "";
						String txnId = "";
						String purposeID = "";
						paymentType = dto.getPaymentType();
						txnId = dto.getTxnId();
						fromDate = eForm.getFromDate();
						toDate = eForm.getToDate();
						purposeID=dto.getPurposeID();
						dto.setIsViewEmpty(0);
						dto.setViewResultList(new ArrayList());
						ArrayList viewResultList = new ArrayList();
						eForm.getRmDto().setChallanMsg("");
						eForm.getRmDto().setCreditLimitMsg("");
					
						if (paymentType.equalsIgnoreCase("4")) {

							// viewResultList=
							// revBD.getViewDetls(paymentType,fromDate,toDate,txnId);
							String judLedgerParam = revBD.getCreditLimitParam();
							  if(null!=judLedgerParam && !"".equalsIgnoreCase(judLedgerParam)){
								  
							  int judLedgerParam1 = Integer.parseInt(judLedgerParam);

							  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

							    Date d1 = null;
							    Date d2 = null;
								try{
								    d1 = format.parse(fromDate);
							        d2 = format.parse(toDate);
							     } catch (Exception e) {
							        e.printStackTrace();
							    }
							     
							     int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
							     
							  
			
									 if(diffInDays>judLedgerParam1){
										
										 if(languageLocale.equalsIgnoreCase("en")){
											 eForm.getRmDto().setCreditLimitMsg("Please select duration of " + judLedgerParam + " days or less than that.");
										 }else{
											 eForm.getRmDto().setCreditLimitMsg("सर्च की अवधी "+ judLedgerParam +" दिन या उस से कम की रखें।");
										 }
											 
										 return mapping.findForward("FirstJsp");
									 }
							  else{
							viewResultList = revBD.getFilteredresults1(paymentType, fromDate, toDate,txnId,languageLocale,purposeID,officeId);//HINDI
							  }}
						
							try {
								if (viewResultList.size() > 0) {

								
									dto.setViewResultList(viewResultList);
									request.setAttribute(
											"viewResultList",
											viewResultList);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";

								} else {
									dto.setIsViewEmpty(1);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";
								}

							} catch (Exception e) {
								logger.debug("inside popup page1, reg detl is empty");
								dto.setIsViewEmpty(1);
								//formName = "";
								//eForm.setActionName("");
								forwardPage = "FirstJsp";
							}
							//formName = "";
							//eForm.setActionName("");
						}

						else if (paymentType.equals("1")
								|| paymentType.equals("2")
								|| paymentType.equals("3")) {
							try {
								
								
								  String judLedgerParam = revBD.getChallanParam();
								  if(null!=judLedgerParam && !"".equalsIgnoreCase(judLedgerParam)){
									  
								  int judLedgerParam1 = Integer.parseInt(judLedgerParam);

								  SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

								    Date d1 = null;
								    Date d2 = null;
									try{
									    d1 = format.parse(fromDate);
								        d2 = format.parse(toDate);
								     } catch (Exception e) {
								        e.printStackTrace();
								    }
								     
								     int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
								     
								  
				
										 if(diffInDays>judLedgerParam1){
											
											 if(languageLocale.equalsIgnoreCase("en")){
												 eForm.getRmDto().setChallanMsg("Please select duration of " + judLedgerParam + " days or less than that.");
											 }else{
												 eForm.getRmDto().setChallanMsg("सर्च की अवधी "+ judLedgerParam +" दिन या उस से कम की रखें।");
											 }
												 
											 return mapping.findForward("FirstJsp");
										 }
								  else{
											 viewResultList = revBD
												.getFilteredresults(
														paymentType, fromDate,
														toDate, txnId,languageLocale,purposeID); 
										 }}
										 
								              //HINDI
								if (viewResultList.size() > 0) {

									dto.setViewResultList(viewResultList);
									request.setAttribute(
											"viewResultList",
											viewResultList);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";

								} else {
									dto.setIsViewEmpty(1);
									//formName = "";
									//eForm.setActionName("");
									forwardPage = "FirstJsp";
								}

							} catch (Exception e) {
								logger.debug("inside popup page1, reg detl is empty");
								dto.setIsViewEmpty(1);
								//formName = "";
								//eForm.setActionName("");
								forwardPage = "FirstJsp";
							}

							//formName = "";
							//eForm.setActionName("");
						}

					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				// Form For Receipt/Challan Search
				if (formName != null
						&& formName.equalsIgnoreCase("ReceiptSearch")) {

					// For Reset

					if (eForm.getActionName().equalsIgnoreCase("Reset")) {

						dto.setRadioSelectReceipt("");
						dto.setRadioSelectReceipt2("");
						dto.setIsViewEmpty(0);
						request.removeAttribute("viewResultList");
						// dto.setViewResultList(new ArrayList());
						dto.setReceiptTextBox("");

					}

					// For Cash/Online/Challan Radio Button
					if (eForm.getActionName().equalsIgnoreCase("SetDataRadioButton")) {

						String radioValue = dto.getRadioSelectReceipt();
						
						// dto.setRadioSelect2(radioValue);

						dto.setRadioSelectReceipt2(radioValue);
						dto.setReceiptTextBox("");

						eForm.setRmDto(dto);
						formName = "";
						radioValue = "";

						eForm.setActionName("");
						forwardPage = "ThirdJsp";

					}

					// Start Of Form View Search

					if (eForm.getActionName().equalsIgnoreCase("RECEIPTSEARCHACTION")) {

						String radioSelectReceipt = "";
						String receiptTextBox = "";

						radioSelectReceipt = dto.getRadioSelectReceipt();
						receiptTextBox = dto.getReceiptTextBox();
						dto.setIsViewEmpty(0);
						dto.setViewResult(new ArrayList());
						ArrayList viewResult = new ArrayList();
						if (radioSelectReceipt.equalsIgnoreCase("1")) // In Case of cash receipt search
					     {

							viewResult = revBD.getFilteredresults2(receiptTextBox, dto,languageLocale);

							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// eForm.setActionName("");
						}

						else if (radioSelectReceipt.equalsIgnoreCase("2")) // In Case Of Challan Receipt Search
														
						{

							viewResult = revBD.getFilteredresults3(receiptTextBox, radioSelectReceipt,dto,languageLocale);
							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							} 
							else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// formName="";
							// eForm.setActionName("");
						}

						else if (radioSelectReceipt
								.equalsIgnoreCase("3")) // In Case Of Online Receipt Search
														
						{

							viewResult = revBD.getFilteredresults4(receiptTextBox, radioSelectReceipt,dto,languageLocale);
							
							if (viewResult.size() != 0) {
								dto.setViewResult(viewResult);
								request.setAttribute("viewResult",viewResult);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FourthJsp";

							}

							else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "ThirdJsp";
							}

							// formName="";
							// eForm.setActionName("");
						}

					}

				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				// start of Revenue Collection Search form
				if (formName != null
						&& formName.equalsIgnoreCase("revReconcSearchPage")) {

					// For Reset

					if (eForm.getActionName().equalsIgnoreCase(
							"ResetRevenueCollection")) {

						dto.setRadioMode("");
						dto.setName("");
						dto.setValue("");
						dto.setIsViewEmpty(0);
						request.removeAttribute("viewCashRevenue");
						dto.setViewCashRevenue(new ArrayList());

						// dto.setOfficeIdList(new ArrayList());
						// dto.setOfficeNameList(new ArrayList());
						dto.setRevenueOfficeName("");
						dto.setRevenueDistrictId("");
						dto.setRevenueDistrictName("");
						dto.setRevenueOfficeId("");
						dto.setRevenueOfficeTypeName("");
						dto.setRevenueToDate("");
						dto.setRevenueFromDate("");
						dto.setCashTransactionId("");
						dto.setCashCreatedDate("");
						dto.setCashFunctionId("");
						dto.setCashFunctionName("");
						dto.setCashGrossAmt("");
						dto.setCashUserCreatedBy("");
						dto.setCashPaymentType("");
						forwardPage = "FifthJsp";
					}

					if (eForm.getActionName()
							.equalsIgnoreCase("OfficeTypeLoad")) {
						String disId = dto.getRevenueDistrictId();
						dto.setOfficeIdList(bo.getOfficeTypeList(disId,languageLocale));
						formName = "";
						// actionName="";
						forwardPage = "FifthJsp";
					}

					if (eForm.getActionName()
							.equalsIgnoreCase("OfficeNameLoad")) {
						String disId = dto.getRevenueDistrictId();
						String offtyp = dto.getRevenueOfficeId();
						dto.setOfficeNameList(bo.getOfficeNameList(disId,offtyp,languageLocale));
						formName = "";
						// actionName="";
						forwardPage = "FifthJsp";
					}

					if (eForm.getActionName().equalsIgnoreCase(
							"REVENUECOLLECTIONSEARCH")) {
						String radioMode = "";
						String revenueDistrictId = "";
						String revenueOfficeTypeName = "";
						String revenueToDate = "";
						String revenueFromDate = "";
						String sroName="";
						radioMode = dto.getRadioMode();
						revenueDistrictId = dto.getRevenueDistrictId();
						revenueOfficeTypeName = dto.getRevenueOfficeTypeName();
						
						revenueToDate = eForm.getRevenueToDate();
						revenueFromDate = eForm.getRevenueFromDate();
						sroName=dto.getPdfOfficeName();
						dto.setIsViewEmpty(0);
						dto.setViewResult(new ArrayList());
						ArrayList viewCashRevenue = new ArrayList();

						if (radioMode.equalsIgnoreCase("1")) // In Case Of Cash
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD.getRevenueCollectionCash(
									revenueDistrictId, revenueOfficeTypeName,
									revenueFromDate, revenueToDate, dto,languageLocale);
							
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}

						if (radioMode.equalsIgnoreCase("2")) // In Case Of
																// Challan
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD
									.getRevenueCollectionChallan(radioMode,
											revenueDistrictId,
											revenueOfficeTypeName,
											revenueFromDate, revenueToDate, dto,languageLocale,currentLocale,sroName);
						
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}

						if (radioMode.equalsIgnoreCase("3")) // In Case Of
																// Online
																// Revenue
																// Collection
						{
							
							viewCashRevenue = revBD.getRevenueCollectionOnline(
									radioMode, revenueDistrictId,
									revenueOfficeTypeName, revenueFromDate,
									revenueToDate, dto,languageLocale);
							
							if (viewCashRevenue.size() != 0) {
								dto.setViewCashRevenue(viewCashRevenue);
								request.setAttribute("viewCashRevenue",
										viewCashRevenue);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "SixthJsp";

							} else {
								dto.setIsViewEmpty(1);
								formName = "";
								// eForm.setActionName("");
								forwardPage = "FifthJsp";
							}

						}
						
						//added by Shreeraj for combined report
						if (radioMode.equalsIgnoreCase("4")) 
							{
							String totCash="",totChallan="",totOnline="";
							double totSum[] = revBD.getRevenueCollectionCombined(
							radioMode, revenueDistrictId,
							revenueOfficeTypeName, revenueFromDate,
							revenueToDate);
							
							dto.setTotalCash(BigDecimal.valueOf(totSum[0]).toPlainString());
							dto.setTotalChallan(BigDecimal.valueOf(totSum[1]).toPlainString());
							dto.setTotalOnline(BigDecimal.valueOf(totSum[2]).toPlainString());
							dto.setTotal(BigDecimal.valueOf(totSum[3]).toPlainString());
							//if (viewCashRevenue.size() != 0) {
						//	dto.setViewCashRevenue(viewCashRevenue);
							/*request.setAttribute("viewCashRevenue",
								viewCashRevenue);*/
							formName = "";
							// eForm.setActionName("");
							forwardPage = "SeventhJsp";
							
							/*} else {
							dto.setIsViewEmpty(1);
							formName = "";
							// eForm.setActionName("");
							forwardPage = "FifthJsp";
							}*/
							
							}

				

				
						// end of action viewSearch

					}
				}
		}
		}

		catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Returnign forwardPage : " + forwardPage);
		return mapping.findForward(forwardPage);
	}
}
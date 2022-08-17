package com.wipro.igrs.revenueManagement.action;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
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
import com.wipro.igrs.revenueManagement.bo.revenueMgtBO;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;
import com.wipro.igrs.revenueManagement.form.RevenueMgtForm;
import com.wipro.igrs.util.PropertiesFileReader;

public class RevenueMgtAction extends BaseAction {
	private Logger logger =(Logger)Logger.getLogger(RevenueMgtAction.class);
	private String forwardPage="";
	private String reqParam="";
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		
		if (form!=null){
			
			String languageLocale=RegInitConstant.LANGUAGE_HINDI;
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
			
			RevenueMgtForm eForm = (RevenueMgtForm)form;
			revenueMgtBO bo = new revenueMgtBO();
			RevenueMgtDTO dto = eForm.getRevdto();
			String userId = (String)session.getAttribute("UserId");
			String officeId = (String)session.getAttribute("loggedToOffice");
			eForm.setUid(userId);
			eForm.setOffid(officeId);			
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			
			if(request.getParameter("label")!=null){
				if(request.getParameter("label").equalsIgnoreCase("serviceFeeCreate")){
					dto.setFunctionList(bo.getFunction(languageLocale));//get modules for service fee matrix
					
					if(dto.getFunctionList()==null || dto.getFunctionList().size()<1){
						request.setAttribute("functionListPresent", "N");
					}
					else{
						request.removeAttribute("functionListPresent");
					dto.setServiceList(new ArrayList());
					dto.setRevMjrHeadList(bo.getMajorHead());
					//below 2 added by Roopam on 6 Nov 2013
					dto.setRevSubMjrHeadList(bo.getSubMajorHead());
					dto.setRevMnrHeadList(bo.getMinorHead());
					}
					//END
					formName="";
					dto.setFunctionId("");
					dto.setServiceId("");
					dto.setRadioSel("P");
					dto.setReadioSelVal("P");
					dto.setRevMjrHeadId("");
					dto.setRevSubMjrHeadId("");
					dto.setRevMnrHeadId("");
					dto.setRuBasicVal("");
					dto.setSpBasicVal("");
					dto.setDrBasicVal("0");
					dto.setServicePresent("Y");
					dto.setEditFlag("");
					dto.setCaptureFeeFlag("Y");
				}
				
				if(request.getParameter("label").equalsIgnoreCase("viewServiceFee")){
					
					//call method for retrieving service fee matrix details from db 
					formName="";
					dto.setRadioView("");
					dto.setMatrixView(bo.getMatrixView(languageLocale));
					
					
					forwardPage="matrixView";
				}
				
			}
			
			
			
			//dto.setRuDependList(bo.getDependList());
			//dto.setRuOperatorList(bo.getOperatorList());
			//dto.setRuPriorityList(bo.getPriorityList());
			//dto.setSpDependList(bo.getDependList());
			//dto.setSpOperatorList(bo.getOperatorList());
			//dto.setSpPriorityList(bo.getPriorityList());
			//dto.setDrDependList(bo.getDependList());
			//dto.setDrOperatorList(bo.getOperatorList());
			//dto.setDrPriorityList(bo.getPriorityList());
			dto.setDistrictList(bo.getDistrictList(languageLocale));
			dto.setYearList(bo.getYearList());
			
			if(request.getParameter("label")!=null){
				reqParam = (String)request.getParameter("label");
			
			if(!(reqParam).equalsIgnoreCase("")){
				if(reqParam.equalsIgnoreCase("serviceFeeCreate")){
				    formName="";
					actionName="";
					forwardPage="matrixCreate";
				}
				
				if(reqParam.equalsIgnoreCase("revReconc")){
					dto.setDistrictId("");
					dto.setDistrictName("");
					dto.setOfficeId("");
					dto.setOfficeName("");
					dto.setOfficeTypeId("");
					dto.setOfficeTypeName("");
					dto.setYearId("");
					dto.setYearName("");
					eForm.setUserDate("");
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setMonth("");
					dto.setNoRecFound(0);
					dto.setRadioMode("1");
					eForm.setRadiodate("date");
					request.removeAttribute("reportDetails");
				    formName="";
					actionName="";
					forwardPage="revReconcPage1";
				}
			}
			}
			
			
			
			
			
			
			//start of revReconcSearchPage form
			if (formName!=null && formName.equalsIgnoreCase("revReconcSearchPage")){
			  
				if(actionName.equalsIgnoreCase("OfficeTypeLoad")){
					String disId=dto.getDistrictId();
					dto.setOfficeTypeList(bo.getOfficeTypeList(disId,languageLocale));
					formName="";
					actionName="";
					forwardPage="revReconcPage1";
				}
				
				if(actionName.equalsIgnoreCase("OfficeNameLoad")){
					String disId=dto.getDistrictId();
					String offtyp = dto.getOfficeTypeId();
					dto.setOfficeNameList(bo.getOfficeNameList(disId,offtyp,languageLocale));
					formName="";
					actionName="";
					forwardPage="revReconcPage1";
				}
				
				if(actionName.equalsIgnoreCase("resetForm")){
					eForm.setRadiodate("date");
					dto.setDistrictId("");
					dto.setDistrictName("");
					dto.setOfficeTypeId("");
					dto.setOfficeTypeName("");
					dto.setOfficeId("");
					dto.setOfficeName("");
					dto.setRadioMode("1");
					eForm.setUserDate("");
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.setMonth("");
					dto.setYearId("");
					dto.setYearName("");
					dto.setNoRecFound(0);
					request.removeAttribute("reportDetails");
				}
			
		    	if(actionName.equalsIgnoreCase("generateReport")){
		    	dto.setNoRecFound(0);
				logger.debug("the mode selected is...."+dto.getRadioMode());	
				logger.debug("the district id selected is...."+dto.getDistrictId());	
				logger.debug("the district name selected is...."+dto.getDistrictName());	
				logger.debug("the Office id selected is...."+dto.getOfficeId());	
				logger.debug("the Office Name selected is...."+dto.getOfficeName());	
				logger.debug("the Office type id selected is...."+dto.getOfficeTypeId());	
				logger.debug("the Office type name selected is...."+dto.getOfficeTypeName());	
				logger.debug("the radioDate selected is...."+eForm.getRadiodate());	
				logger.debug("the userDate selected is...."+eForm.getUserDate());	
				logger.debug("the from date selected is...."+eForm.getFromDate());	
				logger.debug("the To Date selected is...."+eForm.getToDate());
				logger.debug("the Month selected is...."+eForm.getMonth());
				logger.debug("the Year selected is...."+dto.getYearId());
				String radioDT = eForm.getRadiodate();
				String userDt=eForm.getUserDate();
				String fmDt = eForm.getFromDate();
				String toDt = eForm.getToDate();
				String mnth = eForm.getMonth();
				if(dto.getRadioMode().equalsIgnoreCase("1")){
					dto.setRadioModeName("Cash");
				}
                if(dto.getRadioMode().equalsIgnoreCase("2")){
                	dto.setRadioModeName("Challan");
				}
               if(dto.getRadioMode().equalsIgnoreCase("3")){
            	   dto.setRadioModeName("Online");
                }
				ArrayList reportDetails = new ArrayList();
				 
				 reportDetails= bo.getreportDetails(dto,radioDT,userDt,fmDt,toDt,mnth,languageLocale);
				 
				 if (reportDetails.size()==0){
					 dto.setNoRecFound(1);
					 forwardPage="revReconcPage1";
					  }else
				 if (reportDetails.size()>0){
					 dto.setNoRecFound(0);
					 dto.setReportDetails(reportDetails);
					 request.setAttribute("reportDetails", reportDetails);
					 forwardPage="revReconcPageView";
					}


				}// end of action generateReport
		    	
		    	
		    	
		    	if(actionName.equalsIgnoreCase("generateReportPDF")){
		    		
		    		eForm.setActionName("generateReport");
		    		eForm.setFormName("revReconcSearchPage");
		    		if(languageLocale.equalsIgnoreCase("en"))
		    		{
		    		
		    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        		Document document = new Document(PageSize.A4,20,20,70,20);
	        		PdfWriter.getInstance(document, baos);
	        		document.open();
	        	    Table datatable = new Table(30);
	        	    datatable.setWidth(100);
	        	    datatable.setPadding(3);
	        	    
	        	    
	        	    Cell title = new Cell(new Phrase("Revenue Reconciliation Report", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD)));
	        	    title.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    title.setLeading(20);
	        	    title.setColspan(30);
	        	    title.setBorder(Rectangle.NO_BORDER);
	        	    datatable.addCell(title);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell title2 = new Cell(new Phrase(dto.getOfficeName()+", "+dto.getDistrictName(), FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD)));
	        	    title2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    title2.setLeading(20);
	        	    title2.setColspan(30);
	        	    title2.setBorder(Rectangle.NO_BORDER);
	        	    datatable.addCell(title2);
	        	    datatable.setAlignment(1);
	        	    
	        	    
	        	    Cell transIdHeader = new Cell(new Phrase("Transaction Id", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transIdHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transIdHeader.setLeading(20);
	        	    transIdHeader.setColspan(3);
	        	    transIdHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transIdHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);

	        	    Cell transPurposeHeader = new Cell(new Phrase("Transaction Purpose", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transPurposeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transPurposeHeader.setLeading(20);
	        	    transPurposeHeader.setColspan(3);
	        	    transPurposeHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transPurposeHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell districtHeader = new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    districtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    districtHeader.setLeading(20);
	        	    districtHeader.setColspan(3);
	        	    districtHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(districtHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell OfficeHeader = new Cell(new Phrase("Office", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    OfficeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    OfficeHeader.setLeading(20);
	        	    OfficeHeader.setColspan(3);
	        	    OfficeHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(OfficeHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell userIdHeader = new Cell(new Phrase("User ID", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    userIdHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    userIdHeader.setLeading(20);
	        	    userIdHeader.setColspan(3);
	        	    userIdHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(userIdHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell transTypeHeader = new Cell(new Phrase("Transaction Type", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transTypeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transTypeHeader.setLeading(20);
	        	    transTypeHeader.setColspan(3);
	        	    transTypeHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transTypeHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell transDtHeader = new Cell(new Phrase("Transaction Date", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transDtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transDtHeader.setLeading(20);
	        	    transDtHeader.setColspan(3);
	        	    transDtHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transDtHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell transDtTreasHeader = new Cell(new Phrase("Treasury Receipt date", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transDtTreasHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transDtTreasHeader.setLeading(20);
	        	    transDtTreasHeader.setColspan(3);
	        	    transDtTreasHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transDtTreasHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell transAmtHeader = new Cell(new Phrase("Transaction Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transAmtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transAmtHeader.setLeading(20);
	        	    transAmtHeader.setColspan(3);
	        	    transAmtHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transAmtHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
	        	    
	        	    Cell transAmtTrHeader = new Cell(new Phrase("Treasury Receipt Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	        	    transAmtTrHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        	    transAmtTrHeader.setLeading(20);
	        	    transAmtTrHeader.setColspan(3);
	        	    transAmtTrHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
	        	    datatable.addCell(transAmtTrHeader);
	        	    datatable.setBorderWidth(2);
	        	    datatable.setAlignment(1);
		    		
	        	    ArrayList list = (ArrayList)dto.getReportDetails();
	    		    for(int i = 0 ; i < list.size() ; i++)
	    		    {
	    		    	RevenueMgtDTO gDTO = (RevenueMgtDTO)list.get(i);
	    		    	
	    		    	Cell transId = new Cell(new Phrase(gDTO.getTransNo(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    		    	transId.setHorizontalAlignment(Element.ALIGN_CENTER);
	    		    	transId.setLeading(20);
	    		    	transId.setColspan(3);
	    			    datatable.addCell(transId);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transpurp = new Cell(new Phrase(gDTO.getTransPurp(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transpurp.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transpurp.setLeading(20);
	    			    transpurp.setColspan(3);
	    			    datatable.addCell(transpurp);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell district = new Cell(new Phrase(gDTO.getTransLocDis(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    district.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    district.setLeading(20);
	    			    district.setColspan(3);
	    			    datatable.addCell(district);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell Office = new Cell(new Phrase(gDTO.getTransLocOff(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    Office.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    Office.setLeading(20);
	    			    Office.setColspan(3);
	    			    datatable.addCell(Office);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell userID = new Cell(new Phrase(gDTO.getTransUser(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    userID.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    userID.setLeading(20);
	    			    userID.setColspan(3);
	    			    datatable.addCell(userID);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transType = new Cell(new Phrase(gDTO.getTransType(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transType.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transType.setLeading(20);
	    			    transType.setColspan(3);
	    			    datatable.addCell(transType);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transDt = new Cell(new Phrase(gDTO.getTransDtDwnld(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transDt.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transDt.setLeading(20);
	    			    transDt.setColspan(3);
	    			    datatable.addCell(transDt);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transDtTr = new Cell(new Phrase(gDTO.getTransDtTreas(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transDtTr.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transDtTr.setLeading(20);
	    			    transDtTr.setColspan(3);
	    			    datatable.addCell(transDtTr);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    if(gDTO.getTransAmntDwnld()==null||gDTO.getTransAmntDwnld().equalsIgnoreCase("")){
	    			    	gDTO.setTransAmntDwnld("0");
	    			    }
	    			    if(gDTO.getTransAmntTreas()==null||gDTO.getTransAmntTreas().equalsIgnoreCase("")){
	    			    	gDTO.setTransAmntTreas("0");
	    			    }
	    			    if(Double.parseDouble(gDTO.getTransAmntDwnld())==Double.parseDouble(gDTO.getTransAmntTreas()))
	    			    {
	    			    Cell transAm = new Cell(new Phrase(gDTO.getTransAmntDwnld(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transAm.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transAm.setLeading(20);
	    			    transAm.setColspan(3);
	    			    datatable.addCell(transAm);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transAmTr = new Cell(new Phrase(gDTO.getTransAmntTreas(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transAmTr.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transAmTr.setLeading(20);
	    			    transAmTr.setColspan(3);
	    			    datatable.addCell(transAmTr);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			   }
	    			    
	    			    if(Double.parseDouble(gDTO.getTransAmntDwnld())!=Double.parseDouble(gDTO.getTransAmntTreas()))
	    			    {
	    			    Cell transAm = new Cell(new Phrase(gDTO.getTransAmntDwnld(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transAm.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transAm.setLeading(20);
	    			    transAm.setColspan(3);
	    			    transAm.setBackgroundColor(Color.yellow);
	    			    datatable.addCell(transAm);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			    
	    			    Cell transAmTr = new Cell(new Phrase(gDTO.getTransAmntTreas(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    			    transAmTr.setHorizontalAlignment(Element.ALIGN_CENTER);
	    			    transAmTr.setLeading(20);
	    			    transAmTr.setColspan(3);
	    			    transAmTr.setBackgroundColor(Color.yellow);
	    			    datatable.addCell(transAmTr);
	    			    datatable.setBorderWidth(2);
	    			    datatable.setAlignment(1);
	    			   }
	    			    
	    		    }
	    		    Cell transAmTotDw = new Cell(new Phrase("Total (INR)", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
	    		    transAmTotDw.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		    transAmTotDw.setLeading(20);
	    		    transAmTotDw.setColspan(24);
	    		    transAmTotDw.setBorder(Rectangle.NO_BORDER);
    			    datatable.addCell(transAmTotDw);
    			    datatable.setBorderWidth(2);
    			    datatable.setAlignment(1);
    			    
    			    Cell transAmTotDwn = new Cell(new Phrase(dto.getTransTotalDwnld(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
    			    transAmTotDwn.setHorizontalAlignment(Element.ALIGN_CENTER);
    			    transAmTotDwn.setLeading(20);
    			    transAmTotDwn.setColspan(3);
    			    datatable.addCell(transAmTotDwn);
    			    datatable.setBorderWidth(2);
    			    datatable.setAlignment(1);
    			    
    			    Cell transAmTotTr = new Cell(new Phrase(dto.getTransTotalTreas(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
    			    transAmTotTr.setHorizontalAlignment(Element.ALIGN_CENTER);
    			    transAmTotTr.setLeading(20);
    			    transAmTotTr.setColspan(3);
    			    datatable.addCell(transAmTotTr);
    			    datatable.setBorderWidth(2);
    			    datatable.setAlignment(1);
	    		    
	        	    datatable.setCellsFitPage(true);
	      	        document.add(datatable);
	      		    document.close();
	      			response.setContentType("Report/pdf");
	      			response.setHeader("Content-Disposition", "attachment; filename=\"Report.pdf");
	      			response.setContentLength(baos.size());
	      			ServletOutputStream out = response.getOutputStream();
	      			baos.writeTo(out);
	      			out.flush();
		    		
		    		
		    		
		    	}else{
		    		
					
		    		String radioMode = dto.getRadioMode();
					String revenueDistrictId = dto.getDistrictId();
					String revenueOfficeTypeName = dto.getOfficeName();
					
					String revenueToDate = eForm.getToDate();
					String revenueFromDate = eForm.getFromDate();
					String sroName=dto.getOfficeTypeName();
					JasperReport jasperReport;
				 	JasperPrint jasperPrint;
				 	 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
				 	String pdfName="";
				 	HashMap<String, Object> jasperMap=new HashMap();
				 	jasperMap.put("districtID", revenueDistrictId);
					jasperMap.put("officeID", dto.getOfficeId());
					
					jasperMap.put("paymentID", radioMode);
					jasperMap.put("sroName", sroName);		
					jasperMap.put("userDate", eForm.getUserDate());		
					jasperMap.put("totChallanSum", dto.getTransTotalDwnld());
					jasperMap.put("totChallanRecSum", dto.getTransTotalTreas());
					DBUtility dbUtil = new DBUtility();
	        		//JRProperties.setProperty("net.sf.jasperreports.default.pdf.font.name", "C:\\mangal.ttf");
					if(radioMode.equalsIgnoreCase("1")){
						
						
						if(eForm.getRadiodate().equalsIgnoreCase("date")){
							jasperMap.put("userDate", eForm.getUserDate());
							
							
							jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReportDate.jrxml"));	
						 	//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
							pdfName="RevenueConciliationReport";
							//JasperViewer.viewReport(jasperPrint);
							//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueConciliationReportDate.pdf"));
					 		 
					 	 }else if(eForm.getRadiodate().equalsIgnoreCase("week")){
						 		jasperMap.put("fromDate", revenueFromDate);
								jasperMap.put("toDate", revenueToDate);
						 
									
						 		jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReport.jrxml"));	
							 	//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
						 		pdfName="RevenueConciliationReport";
								//JasperViewer.viewReport(jasperPrint);
								//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueConciliationReport.pdf"));
						 	 
						 	 }else{
						 String mnth=eForm.getMonth();
				 		 String month="";
				 		 
				 		String fromdate="01/"+mnth+"/"+dto.getYearId();
				 		if(mnth.equalsIgnoreCase("02")){
				 			if((Integer.parseInt(dto.getYearId()) % 4 == 0 && Integer.parseInt(dto.getYearId()) % 100 != 0) || (Integer.parseInt(dto.getYearId()) % 400 == 0))	
				 			{
				 			month="29/"+mnth+"/"+dto.getYearId();
				 			}else {
				 				month="28/"+mnth+"/"+dto.getYearId();
				 			}
				 			}
				 			if(mnth.equalsIgnoreCase("01")||mnth.equalsIgnoreCase("03")||mnth.equalsIgnoreCase("05")||mnth.equalsIgnoreCase("07")||mnth.equalsIgnoreCase("08")||mnth.equalsIgnoreCase("10")||mnth.equalsIgnoreCase("12")){
				 				month="31/"+mnth+"/"+dto.getYearId();
				 				}
				 			if(mnth.equalsIgnoreCase("04")||mnth.equalsIgnoreCase("06")||mnth.equalsIgnoreCase("09")||mnth.equalsIgnoreCase("11")){
				 				month="30/"+mnth+"/"+dto.getYearId();
				 				}
				 			
				 			jasperMap.put("fromDate", fromdate);
							jasperMap.put("toDate", month);
							
						
						jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReportCash.jrxml"));	
					 	 }
						//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
						pdfName="RevenueConciliationReport";
					}else{
						
					if(eForm.getRadiodate().equalsIgnoreCase("date")){
						jasperMap.put("userDate", eForm.getUserDate());
						//jasperMap.put("toDate", revenueToDate);
						
						jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReportDate.jrxml"));	
					 	//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
						pdfName="RevenueConciliationReport";
						//JasperViewer.viewReport(jasperPrint);
						//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueConciliationReportDate.pdf"));
				 		 
				 	 }else if(eForm.getRadiodate().equalsIgnoreCase("week")){
				 		jasperMap.put("fromDate", revenueFromDate);
						jasperMap.put("toDate", revenueToDate);
				 
							
				 		jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReport.jrxml"));	
					 	//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
				 		pdfName="RevenueConciliationReport";
						//JasperViewer.viewReport(jasperPrint);
						//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueConciliationReport.pdf"));
				 	 
				 	 }else{
				 		 String mnth=eForm.getMonth();
				 		 String month="";
				 		 
				 		String fromdate="01/"+mnth+"/"+dto.getYearId();
				 		if(mnth.equalsIgnoreCase("02")){
				 			if((Integer.parseInt(dto.getYearId()) % 4 == 0 && Integer.parseInt(dto.getYearId()) % 100 != 0) || (Integer.parseInt(dto.getYearId()) % 400 == 0))	
				 			{
				 			month="29/"+mnth+"/"+dto.getYearId();
				 			}else {
				 				month="28/"+mnth+"/"+dto.getYearId();
				 			}
				 			}
				 			if(mnth.equalsIgnoreCase("01")||mnth.equalsIgnoreCase("03")||mnth.equalsIgnoreCase("05")||mnth.equalsIgnoreCase("07")||mnth.equalsIgnoreCase("08")||mnth.equalsIgnoreCase("10")||mnth.equalsIgnoreCase("12")){
				 				month="31/"+mnth+"/"+dto.getYearId();
				 				}
				 			if(mnth.equalsIgnoreCase("04")||mnth.equalsIgnoreCase("06")||mnth.equalsIgnoreCase("09")||mnth.equalsIgnoreCase("11")){
				 				month="30/"+mnth+"/"+dto.getYearId();
				 				}
				 			
				 			jasperMap.put("fromDate", fromdate);
							jasperMap.put("toDate", month);
							
				 		jasperReport=JasperCompileManager.compileReport(property.getValue("pdf.location").concat("RevenueConciliationReport.jrxml"));	
					 	//jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
				 		pdfName="RevenueConciliationReport";
						//JasperViewer.viewReport(jasperPrint);
						//JasperExportManager.exportReportToPdfFile(jasperPrint, property.getValue("pdf.location").concat("RevenueConciliationReport.pdf"));
				 	 }
		    	}
					jasperPrint=JasperFillManager.fillReport(jasperReport, jasperMap, dbUtil.getDBConnection());
				 	//JasperViewer.viewReport(jasperPrint);
				 	
				 	
				 	 	ByteArrayOutputStream  pdfReport = new ByteArrayOutputStream();
				 	 	JRPdfExporter exporter = new JRPdfExporter();
						System.out.println("OPENOFFICE_PATH = "+System.getenv("OPENOFFICE_PATH"));
						exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
						exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
						exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,pdfReport);
						exporter.exportReport();
						System.out.println("Done--->");
						
						ByteArrayOutputStream os = (ByteArrayOutputStream)exporter.getParameter(JRExporterParameter.OUTPUT_STREAM);
				  		response.setContentType("application/pdf");
				  		response.setHeader("Content-Disposition", "attachment; filename=\\"+pdfName+".pdf");
				  		response.setContentLength(pdfReport.size());
				  		ServletOutputStream out = response.getOutputStream();
				  		os.writeTo(out);
				  		out.flush();
				  		out.close();
					
					/*CommonUtil cmUtil=new CommonUtil();
					  //path of jrxml report for the functionality
					 PropertiesFileReader property = PropertiesFileReader.getInstance("resources.igrs");
					  String reportPath = property.getValue("pdf.location")+"RevenueConciliationReport.jrxml";   
					  System.out.println(">>>>"+reportPath);
					// This hashmap is used for passing parameters to your jrxml template.
		            //Here the first parameter denotes the name of the variable in your
					
					cmUtil.convertPdfHindi(jasperMap,reportPath,response);*/
		    	}
		    		
		    		
		    	}
		    	
			}// end of revReconcSearchPage form
			
			if (formName!=null && formName.equalsIgnoreCase("serviceMatrixView")){
				if(actionName.equalsIgnoreCase("editAction")){    //for editing matrix details
					
					logger.debug("ddd--> "+dto.getRadioView());
					
					dto.setEditFlag("Y");
					
					
					ArrayList list=dto.getMatrixView();
					RevenueMgtDTO dto1 = new RevenueMgtDTO();
					if(list!=null && list.size()>0){
						  for (int i = 0; i < list.size(); i++) {
							  dto1 = (RevenueMgtDTO)list.get(i);
						      
							  if(dto1.getValue().equalsIgnoreCase(dto.getRadioView()))
							  {
						       dto.setFunctionId(dto1.getFunctionId());
						       dto.setServiceId(dto1.getServiceId());
						       dto.setFunctionName(dto1.getFunctionName());
						       dto.setServiceName(dto1.getServiceName());
						       dto.setRevMjrHeadId(dto1.getRevMjrHeadId());
						       dto.setRevSubMjrHeadId(dto1.getRevSubMjrHeadId());
						       dto.setRevMnrHeadId(dto1.getRevMnrHeadId());
						       dto.setCaptureFeeFlag(dto1.getCaptureFeeFlag());
						       if(dto.getCaptureFeeFlag().equalsIgnoreCase("Y")){
						       dto.setRuBasicVal(dto1.getRuBasicVal());
						       dto.setSpBasicVal(dto1.getSpBasicVal());
						       dto.setDrBasicVal(dto1.getDrBasicVal());
						       
						       double feeRu=Double.parseDouble(dto.getRuBasicVal());
						       double feeSp=Double.parseDouble(dto.getSpBasicVal());
						       double feeDr=Double.parseDouble(dto.getDrBasicVal());
								
								if(feeRu==0 && feeSp==0 && feeDr==0)
								{
									dto.setRadioSel("F");
									dto.setReadioSelVal("F");
								}else{
								dto.setRadioSel("P");
								dto.setReadioSelVal("P");
								}
						       
						       
						       }else{
						    	   dto.setFeeBasedOnLabel(dto1.getFeeBasedOnLabel());
						       }
						       break;
							  }
						       
						   	}
					}
					
					dto.setFunctionList(bo.getFunction(languageLocale));
					dto.setServiceList(bo.getService(dto.getFunctionId(),languageLocale,dto)); 
					dto.setRevMjrHeadList(bo.getMajorHead());
					dto.setRevSubMjrHeadList(bo.getSubMajorHead());
					dto.setRevMnrHeadList(bo.getMinorHead());
					
					if(dto.getServiceList().size()>0){
					dto.setServicePresent("Y");
					}else{
						dto.setServicePresent("N");
					}
				
					
					
					
					
					
					
					
						forwardPage="matrixEdit";
					
					
				}
			}
			
			//start of form serviceMatrixCreate/View/Edit
			if (formName!=null && (formName.equalsIgnoreCase("serviceMatrixCreate") || formName.equalsIgnoreCase("serviceMatrixEdit"))){
				
				//start of action serviceload
				if(actionName.equalsIgnoreCase("serviceload")){
					String funcId = dto.getFunctionId();
					if (funcId != null && !funcId.equalsIgnoreCase("") && !funcId.equalsIgnoreCase("Select")) {
						dto.setServiceList(bo.getService(funcId,languageLocale,dto));
						
						if(dto.getServiceList().size()>0){
							
							dto.setServicePresent("Y");
							
							logger.debug("capture fee:-" +dto.getCaptureFeeFlag());
							logger.debug("fee based on:-" +dto.getFeeBasedOnLabel());
							
							
						}else{
							
							dto.setServicePresent("N");
							
						}
						
						formName="";
						actionName="";
						if(dto.getEditFlag().equalsIgnoreCase("Y")){
							forwardPage="matrixEdit";
						}else{
						forwardPage="matrixCreate";
						}
					}else{
						dto.setServiceList(new ArrayList());
						dto.setServicePresent("Y");
						forwardPage="matrixCreate";
						
					}
				}// end of action serviceload
				
				if(actionName.equalsIgnoreCase("radioChk")){
					
					if(dto.getReadioSelVal().equalsIgnoreCase("F")){
						dto.setRuBasicVal("0");
						dto.setSpBasicVal("0");
						dto.setDrBasicVal("0");
					}
					if(dto.getReadioSelVal().equalsIgnoreCase("P")){
						dto.setRuBasicVal("");
						dto.setSpBasicVal("");
						dto.setDrBasicVal("0");
					}
					if(dto.getEditFlag().equalsIgnoreCase("Y")){
						forwardPage="matrixEdit";
					}else{
					forwardPage="matrixCreate";
					}
				}
				
				if(actionName.equalsIgnoreCase("saveMatrix")){    //for saving/updating service fee matrix details in bd
					
					//call method for updating revenue heads and fees corresponding to selected module & service 
					boolean updateMatrix=bo.updateMatrix(eForm);
					logger.debug("matrix update status...."+updateMatrix);
					
					if(updateMatrix){
						forwardPage="matrixSaved";
					}else{
						
						request.setAttribute("errorMsg", "Could not save details. Please try again.");
						if(dto.getEditFlag().equalsIgnoreCase("Y")){
							forwardPage="matrixEdit";
						}else{
						forwardPage="matrixCreate";
						}
						
					}
					
				}
				if(actionName.equalsIgnoreCase("okAction")){    //on click of OK
					
					if(dto.getEditFlag().equalsIgnoreCase("Y")){
						dto.setMatrixView(bo.getMatrixView(languageLocale));
						forwardPage="matrixView";
					}else{
					forwardPage="welcome";
					}
						
					
					
				}
				if(actionName.equalsIgnoreCase("reset")){
					
					dto.setRadioSel("P");
					dto.setReadioSelVal("P");
					dto.setRuBasicVal("");
					dto.setSpBasicVal("");
					dto.setDrBasicVal("0");
					dto.setRevMjrHeadId("");
					dto.setRevSubMjrHeadId("");
					dto.setRevMnrHeadId("");
					dto.setFunctionId("");
					dto.setServiceId("");
					dto.setServicePresent("Y");
					
					if(dto.getEditFlag().equalsIgnoreCase("Y")){
						forwardPage="matrixEdit";
					}else{
					forwardPage="matrixCreate";
					}
					
				}
				
				//start of SubMajorLoad
                /*if(actionName.equalsIgnoreCase("SubMajorLoad")){
					String mjrId = dto.getRevMjrHeadId();
					if (mjrId != null|| mjrId != "") {
						dto.setRevSubMjrHeadList(bo.getSubMajorHead(mjrId));
						formName="";
						actionName="";
						forwardPage="matrixCreate";
					}
				}*///end of SubMajorLoad
				
                //start of MinorLoad
                /*if(actionName.equalsIgnoreCase("MinorLoad")){
					String SubmjrId = dto.getRevSubMjrHeadId();
					if (SubmjrId != null|| SubmjrId != "") {
						dto.setRevMnrHeadList(bo.getMinorHead(SubmjrId));
						formName="";
						actionName="";
						forwardPage="matrixCreate";
					}
				}*///end of MinorLoad
				
			}//end of form serviceMatrixCreate
			
		}// end of (form!=null)
		return mapping.findForward(forwardPage);
		
	}// end of execute method

}

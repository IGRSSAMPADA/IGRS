package com.wipro.igrs.payment.action;


import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.wipro.igrs.common.IGRSCommon;
//import com.wipro.igrs.payment.bd.ChallanGenBD;
import com.wipro.igrs.payment.bd.EODChallanBD;
//import com.wipro.igrs.payment.dto.ChallanGenDTO;
import com.wipro.igrs.payment.dto.EODChallanDTO;
import com.wipro.igrs.payment.dao.CashCounterDAO;

//import com.wipro.igrs.payment.form.ChallanGenForm;
import com.wipro.igrs.payment.form.EODChallanForm;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.revenueManagement.bo.revenueMgtBO;
import com.wipro.igrs.util.DateToWords;

/**
 * ===========================================================================
 * File           :   EODChallanAction.java
 * Description    :   Represents the  EOD Challan generation  Action Class
 * Author         :   Aakriti
 * Created Date   :    Dec 21, 2012

 * ===========================================================================
 */

public class EODChallanAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */

	 private static final Logger logger = Logger
	.getLogger(EODChallanAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {    
    	String FORWARD_JSP="EOD";
    	if (form != null) {
    		String language=(String)session.getAttribute("languageLocale");
    		EODChallanForm eodchform = (EODChallanForm)form;
    		request.setAttribute("eodform", eodchform);
    		String par = request.getParameter("Prnt");
    		if("Y".equalsIgnoreCase(par)){
    			eodchform.setActionValue("");
    			eodchform.setChallanForm("");
    			request.setAttribute("eodform", eodchform);
    			FORWARD_JSP="printPopEOD";	
    		}
		    EODChallanBD chBD = new EODChallanBD();
		    CashCounterDAO newdao 		= new CashCounterDAO();
    		//String userid=session.getAttribute("UserId").toString();
    		 String userid = (String)session.getAttribute("UserId");
    		 String officeId = (String)session.getAttribute("loggedToOffice");
    		 String distId="";
    		 String disName="";
    		 String offcNme="";
    		 ArrayList alist = chBD.getrequestDetails(officeId,language);//HINDI
    		 if(alist.size()>0){
					ArrayList rowList = (ArrayList)alist.get(0);
					offcNme=(String)rowList.get(0);
					distId=(String)rowList.get(1);
					disName=(String)rowList.get(2);
					}
			 eodchform.setOfficeid(officeId);
			 eodchform.setDisId(distId);
			 eodchform.setOfficeName(offcNme);
			 eodchform.setDisName(disName);
			 System.out.println("the set value of office name is"+eodchform.getOfficeName());
			 ArrayList  officeidList = chBD.getOfficeNameList();//getting list officeids
						
			EODChallanDTO dto= new EODChallanDTO();
			//setting list of values to officeids,services.
			dto.setOfficeNameList(officeidList);
			//String language=(String)session.getAttribute("languageLocale");
           	dto.setLanguage(language);
           	//revenueMgtBO bo1 = new revenueMgtBO();
           	dto.setRevMjrHeadList(chBD.getMajorHead());
			dto.setRevSubMjrHeadList(chBD.getSubMajorHead());
			dto.setRevMnrHeadList(chBD.getMinorHead());
			//below 2 added by Roopam on 6 Nov 2013
           
			eodchform.setChgendto(dto);
			logger.debug("Action  ");
			String actionValue =eodchform.getActionValue();
			//if ("challanViewForm".equalsIgnoreCase(chform.getChallanForm()))
			//added by anuj to search the EOD details
			if(request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("EODView")){
				actionValue="";
				eodchform.setTransId("");
				eodchform.setChdate("");
				eodchform.setMinDate("");
				FORWARD_JSP = "EODChallanSearch";
			}
			//added by anuj to get the details on the basis of the search criteria duration/office
			String stat = (String)request.getParameter("status");
			if("showDetails".equalsIgnoreCase(stat)){
				actionValue="";
				String transId = (String)request.getParameter("transId");
				
				eodchform.setTransId(transId);
				chBD.getAllDetails(eodchform);
				request.setAttribute("challanlist",eodchform);
				FORWARD_JSP = "EODView";
				
			}
			if("EODChallanDash".equalsIgnoreCase(actionValue)){
				String transId = eodchform.getTransId();
				String chdate=eodchform.getChdate();
				String minDate = eodchform.getMinDate();
				eodchform.setOfficeid(officeId);
				ArrayList status=chBD.getData(eodchform);
				request.setAttribute("challanlist",eodchform);
				request.setAttribute("alist", alist);request.setAttribute("alist", status);
				FORWARD_JSP="showDetails";
				
				
				
				
				
				
			}
			if(request.getParameter("axn")!=null && request.getParameter("axn").equalsIgnoreCase("EODMain"))
			{   java.util.Date sysdate = new java.util.Date();
			    SimpleDateFormat sd =  new SimpleDateFormat("dd/MM/yyyy");
			    String sdate = sd.format(sysdate);
			    logger.debug(sdate);
			    eodchform.setChdate(sdate);
				/*dto.setRevMjrHeadList(chBD.getMajorHead());
				dto.setRevSubMjrHeadList(chBD.getSubMajorHead());
				dto.setRevMnrHeadList(chBD.getMinorHead());
				eodchform.setChgendto(dto);*/
			    eodchform.setRevMjrHeadId("0030,Stamp & Registration Fees");
			    eodchform.setRevSubMjrHeadId("");
			    eodchform.setRevMnrHeadId("");
			    actionValue="";
				FORWARD_JSP="EOD";
				}
			
			
			else if ("EODMainForm".equalsIgnoreCase(eodchform.getChallanForm())){
			System.out.println("page title is....."+eodchform.getChallanForm());
			if ("EODChallanView".equalsIgnoreCase(actionValue))
			{	logger.debug("here the action is:"+actionValue);
				String officeid=eodchform.getOfficeid();
				logger.debug("in next page the value is"+officeid);
				String chdate=eodchform.getChdate();
				logger.debug("in next page the value of date is"+chdate);
				ArrayList slist=null;
				String  fees=null;
				//RETRIVING total amount collected by cash for a selected date and office
				
				//fees=chBD.getTotalAmount(officeid,chdate);// Commented by Anuj
				
				String mindt="";
				//send service type here(sampada or manual)
				ArrayList newDet = chBD.getTotalAmount(officeid,chdate,eodchform.getRevSubMjrHeadId(),eodchform.getRevMnrHeadId(),eodchform.getOldNewReceipt());
			
					for(int i=0; i<newDet.size(); i++){
						ArrayList sublist = (ArrayList)newDet.get(i);
						fees= (String)sublist.get(0);
						mindt=(String)sublist.get(1);
					}
					if (fees!=null){
				logger.debug("total AMOUNT IS.."+fees);
				logger.debug("length of fees............."+fees.length());
				if(fees.length()!= 0){
				dto.setAmountCollected(fees);
				}
				logger.debug("the amount set in the dto is"+dto.getAmountCollected());
				eodchform.setChgendto(dto);
				eodchform.setAmt(fees);
				eodchform.setMinDate(mindt);
				/*eodchform.setRevMjrHeadName("");
				eodchform.setRevSubMjrHeadName("");
				eodchform.setRevMnrHeadName("");*/
				ArrayList chList;
				chList=chBD.getChallanGenData(officeid,chdate);
				ArrayList tmp_arr;
				int i;
				for( i=0;i<chList.size();i++){
					tmp_arr=(ArrayList)chList.get(i);
					logger.debug("temparray="+tmp_arr);
					logger.debug("temparray size="+tmp_arr.size());
					if (tmp_arr.size()>1){
						logger.debug("temp array value setting=");
						//eodchform.setTxnid((tmp_arr.get(0).toString()));
						eodchform.setOfficeName((tmp_arr.get(2).toString()));
						//eodchform.setCreatedby((tmp_arr.get(2).toString()));
						//eodchform.setChdate(tmp_arr.get(3).toString());
						//eodchform.setAmt((tmp_arr.get(4).toString()));
					}
			
				}
				request.setAttribute("challanlist",eodchform);
				FORWARD_JSP = "EODChallanView";
				}else{
					System.out.println("inside else condition");
					ArrayList refid = new ArrayList();
					ArrayList temp_refid = new ArrayList();
					ArrayList temp_refid1 = new ArrayList();
					refid = chBD.getlatestChlnDtls(officeid);
					String str1="";
					String str2="";
					String srName="";
					//refid.trimToSize();
					for(int i=0; i<refid.size();i++){
						temp_refid=(ArrayList)refid.get(i);
						     str1 = (String)temp_refid.get(0);
							System.out.println("aaaaa"+str1);
							str2 = (String)temp_refid.get(1);	
							System.out.println("bbbbb"+str2);
					}
					srName = chBD.getsrName(str2);
					request.setAttribute("ltstEod", str1);
					request.setAttribute("empname", srName);
					request.setAttribute("offcName", eodchform.getOfficeName());
					FORWARD_JSP = "NoPendingCash";
				}
				
				
				}
			else if ("Cancel".equalsIgnoreCase(actionValue))
		    {
		    	FORWARD_JSP="Cancel";
		    	
		    }
			eodchform.setChallanForm(null);
			eodchform.setActionValue(null);
			
			}//end of  EODChallanView action
			// action belongs to generation of eod challan
			
			
			else if ("EODViewOneForm".equalsIgnoreCase(eodchform.getChallanForm())){
				System.out.println("page title is....."+eodchform.getChallanForm());
			 if ("EODChallanGeneration".equalsIgnoreCase(actionValue)){
				/*IGRSCommon common=new IGRSCommon();
				try{String eodid=common.getSequenceNumber("IGRS_PAYMENT_TXNID","EOD" );
				System.out.println("the eod challan id generated is...."+eodid);
				eodchform.setTxnid(eodid);
				}catch(Exception e){
    		    	FORWARD_JSP = "Failurepage";
        			logger.error(" Exception at getting sequence number in action " + e);
        		    }*/
				boolean status = false;
				String srName = "";
				java.util.Date sysdate = new java.util.Date();
				SimpleDateFormat sd =  new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
				String sdate = sd.format(sysdate);
				eodchform.setCurrDate(sdate);
			    eodchform.setCreatedby(userid);
				srName = chBD.getsrName(userid);
				eodchform.setSrName(srName);
				try{
					status = chBD.addFinalEODDetails(eodchform, userid);
					if (status){
						request.setAttribute("challanlist",eodchform);
						FORWARD_JSP = "EODView";	
					}
				}catch (NullPointerException ne) {
					FORWARD_JSP = "Failurepage";
    				logger.error("Null Exception at PaymentTransactionFinal in DAO " + ne);
    		    }
    	   	    catch (SQLException se) {
    	   	    	FORWARD_JSP = "Failurepage";
    	   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
    			}
    		    catch(Exception e){
    		    	FORWARD_JSP = "Failurepage";
    			logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
    		    }
				/*status =chBD.addEODDetails(eodchform,userid);
				//System.out.println("the value of status in action class is"+status);
				if (status){
					System.out.println("Successfully updated the eod_challan_id in  payment_mode_details table.");
					
						status = chBD.addEODGenDetails(eodchform,userid);
						if (status){
						System.out.println("Successfully inserted the data in challan_gen_detailstable.");
					}else{
						System.out.println(" data unsuccessfully inserted in payment_mode_details table.");
						chBD.cancelTrasanction();
						FORWARD_JSP = "Failurepage";
						
					}
				
				}else{
					System.out.println("data UnSuccessfully updated in  payment_mode_details table.");
					chBD.cancelTrasanction();
					FORWARD_JSP = "Failurepage";
				}*/
				}
			 else if ("Cancel".equalsIgnoreCase(actionValue))
			    {
			    	FORWARD_JSP="Cancel";
			    	
			    }
			}else if ("EODViewTwoForm".equalsIgnoreCase(eodchform.getChallanForm())){
				
				
				if ("printEODChallan".equalsIgnoreCase(actionValue))
			    {
					
					

		        	//boolean trans = paymentBD.ChallanDwnldInsert(eForm, userId);
		        	
		        	if(true){
		        		String uniqid=eodchform.getTxnid();
		        		logger.debug(uniqid);
		        		ArrayList mainlist = new ArrayList();
		        		ArrayList sublist = new ArrayList();
		        		PaymentBD paymentBD = new PaymentBD();
		        		mainlist = paymentBD.getDwnldChallanInsertedDet(uniqid);
		        		if(mainlist.size()>0){
		        		sublist = (ArrayList)mainlist.get(0);

		        		logger.debug((String)sublist.get(0));
		        		/*logger.debug((String)sublist.get(1));
		        		logger.debug((String)sublist.get(2));
		        		logger.debug((String)sublist.get(3));
		        		logger.debug((String)sublist.get(4));
		        		logger.debug((String)sublist.get(5));
		        		logger.debug((String)sublist.get(6));
		        		logger.debug((String)sublist.get(7));
		        		logger.debug((String)sublist.get(8));
		        		logger.debug((String)sublist.get(9));
		        		logger.debug((String)sublist.get(10));
		        		logger.debug((String)sublist.get(11));
		        		logger.debug((String)sublist.get(12));
		        		logger.debug((String)sublist.get(13));
		        		
		        		logger.debug((String)sublist.get(14));
		        		logger.debug((String)sublist.get(15));
		        		logger.debug((String)sublist.get(16));*/
		        		
		        		
		        		String wholeNo="";
		        		String decimalNo="0";
		        		String[] amountArr=sublist.get(7).toString().split("\\.");
		        		
		        		if(amountArr!=null && amountArr.length==2){
		        			if(amountArr[0].equalsIgnoreCase("")){
		        				wholeNo="0";
		        			}else{
		        			wholeNo=amountArr[0];
		        			}
		            		decimalNo=amountArr[1];
		        		}else{
		        			wholeNo=(String)sublist.get(7);
		            		//decimalNo=amountArr[1];	
		        		}
		        		
		        		String loggedInUserId=(String)session.getAttribute("UserId");
		        		//get name and designation;
		        		ArrayList list=chBD.getEmpNameDesignation(loggedInUserId);
		        		
		        		String userName="";
		        		String designation="";
		        		
		        		if(list!=null && list.size()==1){
		        			ArrayList rowList=(ArrayList)list.get(0);
		        			
		        			userName=(rowList.get(0)!=null?rowList.get(0).toString():"-")+" "+(rowList.get(1)!=null?rowList.get(1).toString():"")+" "+(rowList.get(2)!=null?rowList.get(2).toString():"-");
		        			designation=rowList.get(3)!=null?rowList.get(3).toString():"-";
		        			
		        		}else{
		        			
		        			return mapping.findForward("failure");
		        			
		        		}
		        		
		        		
		        		//start of pdf
		        		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        		Document document = new Document(PageSize.A4,20,20,70,20);
		        		PdfWriter.getInstance(document, baos);
		        		document.open();
		        	    Table datatable = new Table(28);
		        	    
		        	    datatable.setWidth(100);
		        	    datatable.setPadding(1);
		        	    
		        	    Table datatable2 = new Table(5);
		        	     		      
		        	    //datatable2.setWidth(20);
		        	    datatable2.setPadding(0);
		        	    datatable2.setAlignment(Element.ALIGN_RIGHT);
		        	    datatable2.setBorder(0);
		        	    
		        	    
		        	    Cell titl1 = new Cell(new Phrase("ePRN: "+(String)sublist.get(13), FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
		        	    titl1.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    //title1.setLeading(20);
		        	    titl1.setColspan(5);
		        	    titl1.setBorder(Rectangle.NO_BORDER);
		        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable2.addCell(titl1);
		        	    //datatable.setBorderWidth(2);
		        	    datatable2.setAlignment(1);
		        	    
		        	    Cell titl2 = new Cell(new Phrase("Office Name: "+(String)sublist.get(11), FontFactory.getFont(FontFactory.COURIER, 8)));
		        	    titl2.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    //title1.setLeading(20);
		        	    titl2.setColspan(5);
		        	    titl2.setBorder(Rectangle.NO_BORDER);
		        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable2.addCell(titl2);
		        	    //datatable.setBorderWidth(2);
		        	    datatable2.setAlignment(1);
		        	    
		        	    Cell titl3 = new Cell(new Phrase("District Name: "+(String)sublist.get(10), FontFactory.getFont(FontFactory.COURIER, 8)));
		        	    titl3.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    //title1.setLeading(20);
		        	    titl3.setColspan(5);
		        	    titl3.setBorder(Rectangle.NO_BORDER);
		        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable2.addCell(titl3);
		        	    //datatable.setBorderWidth(2);
		        	    datatable2.setAlignment(1);
		        	    
		        	    Cell titl4 = new Cell(new Phrase(" . ", FontFactory.getFont(FontFactory.COURIER, 8)));
		        	    titl4.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    //title1.setLeading(20);
		        	    titl4.setColspan(5);
		        	    titl4.setBorder(Rectangle.NO_BORDER);
		        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable2.addCell(titl4);
		        	    //datatable.setBorderWidth(2);
		        	    datatable2.setAlignment(1);
		        	   
		        	    /*Cell title = new Cell(new Phrase("Vidya Bhawan, Bhopal", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    title.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    title.setLeading(20);
		        	    title.setColspan(10);
		        	    title.setBorder(Rectangle.NO_BORDER);
		        	    //title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title);
		        	    //datatable.setBorderWidth(2);
		        	    datatable.setAlignment(1);*/
		        	   
		        	    Cell title1 = new Cell(new Phrase("FORM M.P.T.C. 7", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD)));
		        	    title1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title1.setLeading(20);
		        	    title1.setColspan(28);
		        	    title1.setBorder(Rectangle.NO_BORDER);
		        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title1);
		        	    //datatable.setBorderWidth(2);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title2 = new Cell(new Phrase("(See Subsidiary Rule 69)", FontFactory.getFont(FontFactory.COURIER, 8)));
		        	    title2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title2.setLeading(20);
		        	    title2.setColspan(28);
		        	    title2.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    //Cell title3 = new Cell(new Phrase("CHALLAN No. "+(String)sublist.get(0), FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
		        	    Cell title3 = new Cell(new Phrase("CHALLAN No. ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
		        	    title3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title3.setLeading(20);
		        	    title3.setColspan(28);
		        	    title3.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title3);
		        	    //datatable.setBorderWidth(2);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title4 = new Cell(new Phrase("Challan of Cash Paid into the Treasury/Sub. Treasury/State/Reserve Bank of India", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
		        	    title4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title4.setLeading(20);
		        	    title4.setColspan(28);
		        	    title4.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title4);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    //Cell title5 = new Cell(new Phrase("at "+(String)sublist.get(11)+", "+(String)sublist.get(10)+" ("+(String)sublist.get(9)+")", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
		        	    Cell title5 = new Cell(new Phrase("at ", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
		        	    title5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title5.setLeading(20);
		        	    title5.setColspan(28);
		        	    title5.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title5);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title6 = new Cell(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
		        	    title6.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title6.setLeading(20);
		        	    title6.setColspan(28);
		        	    title6.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title6);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title7 = new Cell(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
		        	    title7.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    title7.setLeading(20);
		        	    title7.setColspan(28);
		        	    title7.setBorder(Rectangle.BOTTOM);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title7);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title8 = new Cell(new Phrase("To be filled in by the Remitter", FontFactory.getFont(FontFactory.COURIER, 6, Font.BOLD)));
		        	    title8.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    title8.setLeading(20);
		        	    title8.setColspan(14);
		        	    title8.setBorder(Rectangle.TOP);
		        	    title8.setBorder(Rectangle.BOTTOM);
		        	    //title7.setBorder(Rectangle.NO_BORDER);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title8);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell title9 = new Cell(new Phrase("To be filled in by the Departmental Officer or the Treasury Officer", FontFactory.getFont(FontFactory.COURIER, 6, Font.BOLD)));
		        	    title9.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    title9.setLeading(20);
		        	    title9.setColspan(14);
		        	    title9.setBorder(Rectangle.TOP);
		        	    title9.setBorder(Rectangle.BOTTOM);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(title9);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	            	   
		        	    Cell subTitleTendered = new Cell(new Phrase("By whom Tendered", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleTendered.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleTendered.setLeading(20);
		        	    subTitleTendered.setColspan(3);
		        	    subTitleTendered.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleTendered);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleName = new Cell(new Phrase("Name or Designation & address of the person on whose behalf money is Paid", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleName.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleName.setLeading(20);
		        	    subTitleName.setColspan(5);
		        	    subTitleName.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleName);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleParticulars = new Cell(new Phrase("Full particulars of the remittance and/of the authority (if any)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleParticulars.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleParticulars.setLeading(20);
		        	    subTitleParticulars.setColspan(5);
		        	    subTitleParticulars.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleParticulars);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleAmount = new Cell(new Phrase("Amount", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleAmount.setLeading(20);
		        	    subTitleAmount.setColspan(4);
		        	    subTitleAmount.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleAmount);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleAccount = new Cell(new Phrase("Head of Account", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleAccount.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleAccount.setLeading(20);
		        	    subTitleAccount.setColspan(4);
		        	    subTitleAccount.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleAccount);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleOfficer = new Cell(new Phrase("Accounts officer by whom adjustable", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleOfficer.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleOfficer.setLeading(20);
		        	    subTitleOfficer.setColspan(4);
		        	    subTitleOfficer.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleOfficer);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitleOrder = new Cell(new Phrase("Order to the bank", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitleOrder.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitleOrder.setLeading(20);
		        	    subTitleOrder.setColspan(3);
		        	    subTitleOrder.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitleOrder);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle1 = new Cell(new Phrase("1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle1.setLeading(20);
		        	    subTitle1.setColspan(3);
		        	    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle2 = new Cell(new Phrase("2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle2.setLeading(20);
		        	    subTitle2.setColspan(5);
		        	    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle3 = new Cell(new Phrase("3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle3.setLeading(20);
		        	    subTitle3.setColspan(5);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle3);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle4 = new Cell(new Phrase("4", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle4.setLeading(20);
		        	    subTitle4.setColspan(4);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle4);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle5 = new Cell(new Phrase("5", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle5.setLeading(20);
		        	    subTitle5.setColspan(4);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle5);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle6 = new Cell(new Phrase("6", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle6.setLeading(20);
		        	    subTitle6.setColspan(4);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle6);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle7 = new Cell(new Phrase("7", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle7.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle7.setLeading(20);
		        	    subTitle7.setColspan(3);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle7);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle1_1 = new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle1_1.setLeading(20);
		        	    subTitle1_1.setColspan(3);
		        	    subTitle1_1.setBorder(Rectangle.TOP);
		        	    subTitle1_1.setBorder(Rectangle.LEFT);
		        	    subTitle1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle2_1 = new Cell(new Phrase(userName+", "+designation+", "+(String)sublist.get(11)+",  "+(String)sublist.get(10), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    subTitle2_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle2_1.setLeading(20);
		        	    subTitle2_1.setColspan(5);
		        	    subTitle2_1.setBorder(Rectangle.TOP);
		        	    subTitle2_1.setBorder(Rectangle.LEFT);
		        	    subTitle2_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle2_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle3_1 = new Cell(new Phrase("Purpose - "+(String)sublist.get(3), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    subTitle3_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle3_1.setLeading(20);
		        	    subTitle3_1.setColspan(5);
		        	    subTitle3_1.setBorder(Rectangle.TOP);
		        	    subTitle3_1.setBorder(Rectangle.LEFT);
		        	    subTitle3_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle3_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle4_1 = new Cell(new Phrase("Rs.    \n"+wholeNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle4_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle4_1.setLeading(20);
		        	    subTitle4_1.setColspan(3);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle4_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle4_2 = new Cell(new Phrase("P.  "+decimalNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle4_2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle4_2.setLeading(20);
		        	    subTitle4_2.setColspan(1);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle4_2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle5_1 = new Cell(new Phrase((String)sublist.get(4)+" - "+  (String)sublist.get(14)  +" , "+(String)sublist.get(5)+" - "+  (String)sublist.get(15)  +" , "+(String)sublist.get(6)+" - "+  (String)sublist.get(16), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle5_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle5_1.setLeading(20);
		        	    subTitle5_1.setColspan(4);
		        	    subTitle5_1.setBorder(Rectangle.TOP);
		        	    subTitle5_1.setBorder(Rectangle.LEFT);
		        	    subTitle5_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle5_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle6_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle6_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle6_1.setLeading(20);
		        	    subTitle6_1.setColspan(4);
		        	    subTitle6_1.setBorder(Rectangle.TOP);
		        	    subTitle6_1.setBorder(Rectangle.LEFT);
		        	    subTitle6_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle6_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle7_1 = new Cell(new Phrase("Date             correct recieve and grant receipt(Sign & full designation of the officer ordering the money to be paid in)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle7_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle7_1.setLeading(20);
		        	    subTitle7_1.setColspan(3);
		        	    subTitle7_1.setBorder(Rectangle.TOP);
		        	    subTitle7_1.setBorder(Rectangle.LEFT);
		        	    subTitle7_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle7_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle1_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle1_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle1_1_1.setLeading(20);
		        	    subTitle1_1_1.setColspan(3);
		        	    subTitle1_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle1_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle1_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle1_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle2_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle2_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle2_1_1.setLeading(20);
		        	    subTitle2_1_1.setColspan(5);
		        	    subTitle2_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle2_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle2_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle2_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle3_1_1 = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle3_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    subTitle3_1_1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		        	    subTitle3_1_1.setLeading(20);
		        	    subTitle3_1_1.setColspan(5);
		        	    subTitle3_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle3_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle3_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle3_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle4_1_1 = new Cell(new Phrase(wholeNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle4_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle4_1_1.setLeading(20);
		        	    subTitle4_1_1.setColspan(3);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle4_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle4_1_2 = new Cell(new Phrase(decimalNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle4_1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    subTitle4_1_2.setLeading(20);
		        	    subTitle4_1_2.setColspan(1);
		        	   //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle4_1_2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle5_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle5_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    subTitle5_1_1.setLeading(20);
		        	    subTitle5_1_1.setColspan(4);
		        	    subTitle5_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle5_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle5_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle5_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle6_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle6_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    subTitle6_1_1.setLeading(20);
		        	    subTitle6_1_1.setColspan(4);
		        	    subTitle6_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle6_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle6_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle6_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell subTitle7_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    subTitle7_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    subTitle7_1_1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		        	    subTitle7_1_1.setLeading(20);
		        	    subTitle7_1_1.setColspan(3);
		        	    subTitle7_1_1.setBorder(Rectangle.BOTTOM);
		        	    subTitle7_1_1.setBorder(Rectangle.LEFT);
		        	    subTitle7_1_1.setBorder(Rectangle.RIGHT);
		        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(subTitle7_1_1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw = new Cell(new Phrase("Amount in words: "+DateToWords.convertAmountToWords((String)sublist.get(7)), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    titleBottomRw.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw.setLeading(20);
		        	    titleBottomRw.setColspan(16);
		        	    titleBottomRw.setBorderWidthRight(0);
		        	    titleBottomRw.setBorderColorRight(Color.white);
		        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    titleBottomRw.setBorderColorTop(Color.black);
		        	    titleBottomRw.setBorderColorBottom(Color.black);
		        	    titleBottomRw.setBorderWidthTop(1);
		        	    titleBottomRw.setBorderWidthBottom(1);
		        	    datatable.addCell(titleBottomRw);
		        	    
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw1 = new Cell(new Phrase("To be used only in the case of remittances to the bank through Departmental Officer or the Treasury Officer", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    titleBottomRw1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		        	    titleBottomRw1.setLeading(20);
		        	    titleBottomRw1.setColspan(12);
		        	    titleBottomRw1.setBorderWidthLeft(0);
		        	    titleBottomRw1.setBorderColorLeft(Color.white);
		        	    titleBottomRw1.setBorderColorTop(Color.black);
		        	    titleBottomRw1.setBorderWidthTop(1);
		        	    titleBottomRw1.setBorderColorBottom(Color.black);
		        	    titleBottomRw1.setBorderWidthBottom(1);
		        	    datatable.addCell(titleBottomRw1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw2 = new Cell(new Phrase("Received Payment (in words) Rupees", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		        	    titleBottomRw2.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw2.setLeading(20);
		        	    titleBottomRw2.setColspan(28);
		        	    titleBottomRw2.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBottomRw2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBlank = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
		        	    titleBlank.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBlank.setLeading(20);
		        	    titleBlank.setColspan(28);
		        	    titleBlank.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBlank);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBlank1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
		        	    titleBlank1.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBlank1.setLeading(20);
		        	    titleBlank1.setColspan(28);
		        	    titleBlank1.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBlank1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBlank2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
		        	    titleBlank2.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBlank2.setLeading(20);
		        	    titleBlank2.setColspan(28);
		        	    titleBlank2.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBlank2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBlank3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
		        	    titleBlank3.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBlank3.setLeading(20);
		        	    titleBlank3.setColspan(28);
		        	    titleBlank3.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBlank3);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    
		        	    Cell titleBottomRw3 = new Cell(new Phrase("Treasurer", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    titleBottomRw3.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw3.setLeading(20);
		        	    titleBottomRw3.setColspan(7);
		        	    titleBottomRw3.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBottomRw3);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw4 = new Cell(new Phrase("Accountant", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    titleBottomRw4.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw4.setLeading(20);
		        	    titleBottomRw4.setColspan(7);
		        	    titleBottomRw4.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBottomRw4);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw5 = new Cell(new Phrase("Date", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    titleBottomRw5.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw5.setLeading(20);
		        	    titleBottomRw5.setColspan(7);
		        	    titleBottomRw5.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBottomRw5);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell titleBottomRw6 = new Cell(new Phrase("Treasury Officer/Agent", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    titleBottomRw6.setHorizontalAlignment(Element.ALIGN_LEFT);
		        	    titleBottomRw6.setLeading(20);
		        	    titleBottomRw6.setColspan(7);
		        	    titleBottomRw6.setBorder(Rectangle.NO_BORDER);
		        	    datatable.addCell(titleBottomRw6);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell blankrow = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow.setLeading(20);
		        	    blankrow.setColspan(28);
		        	    blankrow.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	   
		        	    Cell blankrow1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow1.setLeading(20);
		        	    blankrow1.setColspan(28);
		        	    blankrow1.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow1);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell blankrow2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow2.setLeading(20);
		        	    blankrow2.setColspan(28);
		        	    blankrow2.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow2);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell blankrow3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow3.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow3.setLeading(20);
		        	    blankrow3.setColspan(28);
		        	    blankrow3.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow3);
		        	    datatable.setBorderWidth(1);
		        	    datatable.setAlignment(1);
		        	    
		        	    /*Cell blankrow4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow4.setLeading(20);
		        	    blankrow4.setColspan(28);
		        	    blankrow4.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow4);
		        	    datatable.setBorderWidth(2);
		        	    datatable.setAlignment(1);
		        	    
		        	    Cell blankrow5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
		        	    blankrow5.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	    blankrow5.setLeading(20);
		        	    blankrow5.setColspan(28);
		        	    blankrow5.setBorder(Rectangle.NO_BORDER);
		        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		        	    datatable.addCell(blankrow5);
		        	    datatable.setBorderWidth(2);
		        	    datatable.setAlignment(1);*/
		        	   
		        	    datatable.setCellsFitPage(true);
		      	        
		      	        document.add(datatable2);
		      	        
		      	      document.add(datatable);
		      		    document.close();
		      				response.setContentType("Challan/pdf");
		      				response.setHeader("Content-Disposition", "attachment; filename=\"Challan.pdf");
		      				response.setContentLength(baos.size());
		      				ServletOutputStream out = response.getOutputStream();
		      				baos.writeTo(out);
		      				out.flush();
		        		
		        		
		        		}
		        	}// end of(trans)
		    	
					
					
					
			    	//FORWARD_JSP="Cancel";
			    	
			    }
				
				
				
			}
				
			//action belong to cancel button
			/*else if ("Cancel".equalsIgnoreCase(actionValue))
		    {
		    	FORWARD_JSP="Cancel";
		    	
		    }*/
	    }
    	return mapping.findForward(FORWARD_JSP);
    	
    } //end of execute
    
}//end of class
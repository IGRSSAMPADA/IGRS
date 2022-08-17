package com.wipro.igrs.pendingCase.bd;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.aadhar.common.util.AadharUtil;
import com.wipro.igrs.caseMonitoring.bd.CaseMonBD;
import com.wipro.igrs.caseMonitoring.dao.CaseMonHistoryDAO;
import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.pendingCase.dao.PendingManualCaseDAO;
import com.wipro.igrs.pendingCase.dto.PendingManualCaseDTO;
import com.wipro.igrs.pendingCase.form.PendingManualCaseForm;
import com.wipro.igrs.report.dao.ReportDAO;

public class PendingManualCaseBD {
	private Logger logger = (Logger) Logger.getLogger(PendingManualCaseBD.class);
	PendingManualCaseDAO caseDAO=new PendingManualCaseDAO();
	
	public PendingManualCaseBD() {
	}
	
	
	public ArrayList getRevenueHeadList(String language)
	{
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getRevenueHeadList(language);
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			
			for(int i=0;i<list.size();i++)
			{
				temp=(ArrayList)list.get(i);
				PendingManualCaseDTO dto=new PendingManualCaseDTO();
				dto.setRevHeadName((String)temp.get(0));
				dto.setRevHeadId((String)temp.get(1));
				list1.add(dto);
			}
		}
		return  list1;
	}
	
	public ArrayList getCaseSectionList(String language,String revHeadId)
	{
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		list=caseDAO.getCaseSectionList(language,revHeadId);
		if(list!=null && list.size()>0)
		{
			ArrayList temp=new ArrayList();
			
			for(int i=0;i<list.size();i++)
			{
				temp=(ArrayList)list.get(i);
				PendingManualCaseDTO dto=new PendingManualCaseDTO();
				dto.setSectionName((String)temp.get(0));
				dto.setSectionId((String)temp.get(1));
				list1.add(dto);
			}
		}
		return  list1;
	}
	
	public boolean saveCaseData(PendingManualCaseForm form)
	throws Exception // main
     {
     boolean insertFlag = false;
     String[] param = new String[35];
     
     PendingManualCaseDTO caseDto = form.getCaseDTO();
     String seq = ""; 
     String caseSequence ="";
     
     seq= caseDAO.getCaseSequence();
     
 	if (seq.length() == 1) {
		seq = "00000" + seq;
	} else if (seq.length() == 2) {
		seq = "0000" + seq;
	} else if (seq.length() == 3) {
		seq = "000" + seq;
	} else if (seq.length() == 4) {
		seq = "00" + seq;
	} else if (seq.length() == 5) {
		seq = "0" + seq;
	}
     
     
     String offCode = caseDto.getLoggedInUserOffice().substring(3);
		
		
		int lengthOffice = offCode.length();
	
		if(lengthOffice !=3)
		{
			if(lengthOffice<3)
			{
				for(int i= lengthOffice;i< 3;i++)
				{	offCode = "0"+offCode;	
				}
				
			}
			if(lengthOffice>2)
			{
				offCode = offCode.substring(lengthOffice-2, lengthOffice);
			}
		}
     
     if(caseDto.getCaseType().equalsIgnoreCase("s")){
    	
    	 String stmp = "1212";
    	
    	 Calendar currentDate = Calendar.getInstance();
		 SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		 String dateNow = formatter.format(currentDate.getTime());
		 caseSequence = stmp+offCode+dateNow+seq;
    	 
     }
     
     if(caseDto.getCaseType().equalsIgnoreCase("r")){

    	 String stmp = "212";
    	
    	 Calendar currentDate = Calendar.getInstance();
		 SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		 String dateNow = formatter.format(currentDate.getTime());
		 caseSequence = stmp+offCode+dateNow+seq;
    	 
     }
 
     
     if(form.getCaseDTO().getSectionType().equalsIgnoreCase("1")){
    	  param[0] = caseSequence;
    	  param[1] = caseDto.getCaseType();
    	  param[2] = caseDto.getRevHeadId();
    	  param[3] = caseDto.getSectionId();
    	  param[4] = caseDto.getCaseNumber();
    	  param[5] = form.getCaseDate();
    	//  param[6] = caseDto.getMktValueDOc();
    	  param[6] = "";
    	  //param[7] = caseDto.getPraposedStampSubReg();// praposed stamp sub reg 
    	  param[7] = "";
    	  param[8] = caseDto.getCaseStatusId();
    	 
    	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
        	param[9] = form.getOrderDate();

  	     param[10] = caseDto.getMrkValue();
        	param[11] = caseDto.getOrderNumber();
  	  param[12] = caseDto.getStampDuty();//stamp duty
  	  param[13] = caseDto.getRegFee();//reg fee
    	
    	  param[14] = caseDto.getPaymentType();//payment type
    	  param[15] = "";//payable amount
    	  param[16] = form.getPaymentDate();// date of payment
    	//  param[17] = caseDto.getRemarks();// remarks of payment
    	//  param[32] = "";//remarks
    	  param[33] = caseDto.getPaymentAmount();//amount
    	 }
    	 else{
    	  param[9] = "";//order Date
    	  param[11] = "";//order number
       	  param[14] = "";//payment type
       	  param[15] = "";//payable amount
       	  param[16] = "";// date of payment
       	  param[17] = "";// remarks of payment 
       	// param[32] = caseDto.getRemarks();//remarks 
    	 }
    	 param[32] = caseDto.getRemarks();//remarks 
    	  param[18] = ""; // mkt value audit
    	  param[19] = "";// audit stmap duty
    	  param[20] = "";// audit reg fee
    	  param[21] = "";//audit total
    	  param[22] = caseDto.getStampImpound();// impound stmp duty
    	  param[23] = "";// defficit stamp audit
    	  param[24] = "";//defficit stamp collector
    	  param[25] = "";//estimated stamp
    	  param[26] = caseDto.getCaseNumber();//stamp case number
    	  param[27] = "";//rrc case number
    	  param[28] = "";//paid amount
    	  param[29] = "";//remaining amount
    	  param[30] = caseDto.getLoggedInUserOffice();//office id
    	  param[31] = caseDto.getLoggedInUser();//created by
    	  param[34] = ""; //proposed mrk value by sr
    	  
    	 
     }
     
     if(form.getCaseDTO().getSectionType().equalsIgnoreCase("2")){
    	 

   	  
	   	  param[0] = caseSequence;
	   	  param[1] = caseDto.getCaseType();
	   	  param[2] = caseDto.getRevHeadId();
	   	  param[3] = caseDto.getSectionId();
	   	  param[4] = caseDto.getCaseNumber();
	   	  param[5] = form.getCaseDate();// make this in dto
	   	  param[6] = caseDto.getMktValueDOc();
	   	  param[7] = caseDto.getPraposedStampSubReg();
	   	  param[8] = caseDto.getCaseStatusId();
	   	 
   	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
     	param[9] = form.getOrderDate();

    	  param[10] = caseDto.getMrkValue();
    	  param[11] = caseDto.getOrderNumber();
    	  param[12] = caseDto.getStampDuty();//stamp duty
    	  param[13] = caseDto.getRegFee();//reg fee
      	
      	  param[14] = caseDto.getPaymentType();//payment type
      	  param[15] = "";//payable amount
      	  param[16] = form.getPaymentDate();// date of payment
      //	  param[17] = caseDto.getRemarks();// remarks of payment
      	//  param[32] = "";//remarks
      	  param[33] = caseDto.getPaymentAmount();//amount
      	 }
     	 else{
   	      param[9] = "";//order Date
   	      param[11] = "";//order number
      	  param[14] = "";//payment type
      	  param[15] = "";//payable amount
      	  param[16] = "";// date of payment
      	  param[17] = "";// remarks of payment 
      	//  param[32] = caseDto.getRemarks();//remarks 
   	 }
   	 param[32] = caseDto.getRemarks();//remarks 
	   	  param[18] = ""; // mkt value audit
	   	  param[19] = "";// audit stmap duty
	   	  param[20] = "";// audit reg fee
	   	  param[21] = "";//audit total
	   	  param[22] = "";// impound stmp duty
	   	  param[23] = "";// defficit stamp audit
	   	  param[24] = "";//defficit stamp collector
	   	  param[25] = "";//estimated stamp
	   	  param[26] = caseDto.getCaseNumber();//stamp case number
	   	  param[27] = "";//rrc case number
	   	  param[28] = "";//paid amount
	   	  param[29] = "";//remaining amount
	   	  param[30] = caseDto.getLoggedInUserOffice();//office id
	   	  param[31] = caseDto.getLoggedInUser();//created by
	   	 param[34] = caseDto.getPraposedMrktValSr(); //proposed mrk value by sr
    	 
     }
     
     if(form.getCaseDTO().getSectionType().equalsIgnoreCase("3")){
    

      	  
      	  param[0] = caseSequence;
      	  param[1] = caseDto.getCaseType();
      	  param[2] = caseDto.getRevHeadId();
      	  param[3] = caseDto.getSectionId();
      	  param[4] = caseDto.getCaseNumber();
      	  param[5] = form.getCaseDate();// 
      	  param[6] =""; //mkt Valye of doc
      	  param[7] = "";
      	  param[8] = caseDto.getCaseStatusId();
      	 
      	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
         	param[9] = form.getOrderDate();

        	param[10] = caseDto.getMrkValue();
        	param[11] = caseDto.getOrderNumber();
        	param[12] = caseDto.getStampDuty();//stamp duty
        	param[13] = caseDto.getRegFee();//reg fee
          	
          	param[14] = caseDto.getPaymentType();//payment type
            param[15] = "";//payable amount
          	param[16] = form.getPaymentDate();// date of payment
          //	param[17] = caseDto.getRemarks();// remarks of payment
          //	param[32] = "";//remarks
          	param[33] = caseDto.getPaymentAmount();//amount
          	 }
      	 else{
      		param[9] = "";//order Date
      		param[11] = "";//order number
            param[14] = "";//payment type
         	param[15] = "";//payable amount
         	param[16] = "";// date of payment
         	param[17] = "";// remarks of payment 
         	//param[32] = caseDto.getRemarks();//remarks 
      	 }
      	 param[32] = caseDto.getRemarks();//remarks 
       	  	param[18] = ""; // mkt value audit
       	  	param[19] = "";// audit stmap duty
       	  	param[20] = "";// audit reg fee
       	  	param[21] = "";//audit total
       	  	param[22] = caseDto.getStampImpound();// impound stmp duty
       	  	param[23] = "";// defficit stamp audit
       	  	param[24] = "";//defficit stamp collector
       	  	param[25] = "";//estimated stamp
       	  	param[26] = caseDto.getCaseNumber();//stamp case number
       	  	param[27] = "";//rrc case number
       	  	param[28] = caseDto.getPaymentAmount();//paid amount
       	  	param[29] = "";//remaining amount
       	  	param[30] = caseDto.getLoggedInUserOffice();//office id
       	  	param[31] = caseDto.getLoggedInUser();//created by
       	 param[34] = ""; //proposed mrk value by sr
    	 
       }
	
       if(form.getCaseDTO().getSectionType().equalsIgnoreCase("4")){
   	 

     	  
    	 	param[0] = caseSequence;
    	 	param[1] = caseDto.getCaseType();
    	 	param[2] = caseDto.getRevHeadId();
    	 	param[3] = caseDto.getSectionId();
    	 	param[4] = caseDto.getCaseNumber();
    	 	param[5] = form.getCaseDate();// 
    	 	param[6] =""; //mkt Valye of doc
     	  	param[7] = "";
     	  	param[8] = caseDto.getCaseStatusId();
     	 
     	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
         	param[9] = form.getOrderDate();
        	param[10] = caseDto.getMrkValue();
            param[11] = caseDto.getOrderNumber();
        	param[12] = caseDto.getStampDuty();//stamp duty
        	param[13] = caseDto.getRegFee();//reg fee
          	
          	param[14] = caseDto.getPaymentType();//payment type
          	param[15] = "";//payable amount
          	param[16] = form.getPaymentDate();// date of payment
          //	param[17] = caseDto.getRemarks();// remarks of payment
          	//param[32] = "";//remarks
          	param[33] = caseDto.getPaymentAmount();//amount
          	 }
     	   else{
     	      param[9] = "";//order Date
     	      param[11] = "";//order number
        	  param[14] = "";//payment type
        	  param[15] = "";//payable amount
        	  param[16] = "";// date of payment
        	  param[17] = "";// remarks of payment 
        	//  param[32] = caseDto.getRemarks();//remarks 
     	 }
     	 param[32] = caseDto.getRemarks();//remarks 
     	  param[18] = ""; // mkt value audit
     	  param[19] = "";// audit stmap duty
     	  param[20] = "";// audit reg fee
     	  param[21] = "";//audit total
     	  param[22] = "";// impound stmp duty
     	  param[23] = caseDto.getDeficitStampAudit();// defficit stamp audit
     	  param[24] = caseDto.getDeficitStampCollector();//defficit stamp collector
     	  param[25] = "";//estimated stamp
     	  param[26] = caseDto.getCaseNumber();//stamp case number
     	  param[27] = "";//rrc case number
     	  param[28] = "";//paid amount
     	  param[29] = "";//remaining amount
     	  param[30] = caseDto.getLoggedInUserOffice();//office id
     	  param[31] = caseDto.getLoggedInUser();//created by 
     	 param[34] = ""; //proposed mrk value by sr
     }
    
     if(form.getCaseDTO().getSectionType().equalsIgnoreCase("5")){
    	
    	  param[0] = caseSequence;
    	  param[1] = caseDto.getCaseType();
    	  param[2] = caseDto.getRevHeadId();
    	  param[3] = caseDto.getSectionId();
    	  param[4] = caseDto.getCaseNumber();
    	  param[5] = form.getCaseDate();// 
    	  param[6] =""; //mkt Valye of doc
    	  param[7] = "";
    	  param[8] = caseDto.getCaseStatusId();
    	 
    	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
    	    	param[9] = form.getOrderDate();

    	    	  param[10] = caseDto.getMrkValue();
    	    	param[11] = caseDto.getOrderNumber();
    	    	  param[12] = caseDto.getStampDuty();//stamp duty
    	    	  param[13] = caseDto.getRegFee();//reg fee
    	      	
    	      	  param[14] = caseDto.getPaymentType();//payment type
    	      	  param[15] = "";//payable amount
    	      	  param[16] = form.getPaymentDate();// date of payment
    	      	 // param[17] = caseDto.getRemarks();// remarks of payment
    	      	//  param[32] = "";//remarks
    	      	  param[33] = caseDto.getPaymentAmount();//amount
    	      	 }
    	 else{
    	  param[9] = "";//order Date
    	  param[11] = "";//order number
       	  param[14] = "";//payment type
       	  param[15] = "";//payable amount
       	  param[16] = "";// date of payment
       	  param[17] = "";// remarks of payment 
      // 	 param[32] = caseDto.getRemarks();//remarks 
    	 }
    	 param[32] = caseDto.getRemarks();//remarks 
    	  param[18] = ""; // mkt value audit
    	  param[19] = "";// audit stmap duty
    	  param[20] = "";// audit reg fee
    	  param[21] = "";//audit total
    	  param[22] = "";// impound stmp duty
    	  param[23] = "";// defficit stamp audit
    	  param[24] = "";//defficit stamp collector
    	  param[25] = caseDto.getEstimatedDeffStampAudit();//estimated stamp
    	  param[26] = caseDto.getCaseNumber();//stamp case number
    	  param[27] = "";//rrc case number
    	  param[28] = "";//paid amount
    	  param[29] = "";//remaining amount
    	  param[30] = caseDto.getLoggedInUserOffice();//office id
    	  param[31] = caseDto.getLoggedInUser();//created by 
    	  param[34] = ""; //proposed mrk value by sr
      	 
     }
     
     if(form.getCaseDTO().getSectionType().equalsIgnoreCase("6")){
     	
   	  param[0] = caseSequence;
   	  param[1] = caseDto.getCaseType();
   	  param[2] = caseDto.getRevHeadId();
   	  param[3] = caseDto.getSectionId();
   	  param[4] = caseDto.getCaseNumber();
   	  param[5] = form.getCaseDate();// 
   	  param[6] = caseDto.getMktValueDOc(); //mkt Valye of doc
   	  param[7] = "";
   	  param[8] = caseDto.getCaseStatusId();
   	 
   	 if(caseDto.getCaseStatusId().equalsIgnoreCase("close")){
   	    	param[9] = form.getOrderDate();

   	    	  param[10] = caseDto.getMrkValue();
   	    	param[11] = caseDto.getOrderNumber();
   	    	  param[12] = caseDto.getStampDuty();//stamp duty
   	    	  param[13] = caseDto.getRegFee();//reg fee
   	      	
   	      	  param[14] = caseDto.getPaymentType();//payment type
   	      	  param[15] = "";//payable amount
   	      	  param[16] = form.getPaymentDate();// date of payment
   	      	//  param[17] = caseDto.getRemarks();// remarks of payment
   	      	//  param[32] = "";//remarks
   	      	  param[33] = caseDto.getPaymentAmount();//amount
   	      	 }
   	 else{
   	  param[9] = "";//order Date
   	  param[11] = "";//order number
      	  param[14] = "";//payment type
      	  param[15] = "";//payable amount
      	  param[16] = "";// date of payment
      	  param[17] = "";// remarks of payment 
      	// param[32] = caseDto.getRemarks();//remarks 
   	 }
   	 param[32] = caseDto.getRemarks();//remarks 
   	  param[18] = caseDto.getPraposedMktValueAudit(); // mkt value audit
   	  param[19] = caseDto.getPraposedStampAudit();// audit stmap duty
   	  param[20] = caseDto.getPraposedRegAudit();// audit reg fee
   	  param[21] = caseDto.getTotalAudit();//audit total
   	  param[22] = "";// impound stmp duty
   	  param[23] = "";// defficit stamp audit
   	  param[24] = "";//defficit stamp collector
   	  param[25] = "";//estimated stamp
   	  param[26] = "";//stamp case number
   	  param[27] = "";//rrc case number
   	  param[28] = "";//paid amount
   	  param[29] = "";//remaining amount
   	  param[30] = caseDto.getLoggedInUserOffice();//office id
   	  param[31] = caseDto.getLoggedInUser();//created by 
   	  param[34] = ""; //proposed mrk value by sr
     	 
    }
     if(form.getCaseDTO().getSectionCommon().equalsIgnoreCase("0")){
    	 


   	  
   	  param[0] = caseSequence;
   	  param[1] = caseDto.getCaseType();
   	  param[2] = caseDto.getRevHeadId();
   	  param[3] = caseDto.getSectionId();
   	  param[4] = caseDto.getStampCaseNumber();//case number
   	  param[5] = form.getCaseDate();// 
   	  param[6] =""; //mkt Valye of doc
   	  param[7] = "";
   	  param[8] = caseDto.getCaseStatusId();
   	 
   
   	    	  param[9] = "";//order date

   	    	  param[10] = ""; // mkt value
   	    	  param[11] = "";//order number
   	    	  param[12] = "";//stamp duty
   	    	  param[13] = "";//reg fee
   	      	
   	      	  param[14] = caseDto.getPaymentType();//payment type
   	      	  param[15] = caseDto.getPayableAmt();//payable amount
   	      	  param[16] = form.getPaymentDate();// date of payment
   	      	//  param[17] = caseDto.getRemarks();// remarks of payment
   	      param[18] = ""; // mkt value audit
   	   	  param[19] = "";// audit stmap duty
   	   	  param[20] = "";// audit reg fee
   	   	  param[21] = "";//audit total
   	   	  param[22] = "";// impound stmp duty
   	   	  param[23] = "";// defficit stamp audit
   	   	  param[24] = "";//defficit stamp collector
   	   	  param[25] = "";//estimated stamp
   	   	  param[26] = caseDto.getStampCaseNumber();//stamp case number
   	   	  param[27] = caseDto.getRrcCaseNumber();//rrc case number
   	   	  param[28] = caseDto.getPaidAmt();//paid amount
   	   	  param[29] = caseDto.getBalanceAmt();//remaining amount
   	   	  param[30] = caseDto.getLoggedInUserOffice();//office id
   	   	  param[31] = caseDto.getLoggedInUser();//created by 
   	   	  param[32] = caseDto.getRemarks();//remarks 
   	   	  param[33] = "";//amount
   	   param[34] = ""; //proposed mrk value by sr
     	 
    }
     insertFlag = caseDAO.saveCaseData(param,form);
     
     return insertFlag;
     }
	
	
	public boolean saveEditCaseData(PendingManualCaseForm form)
	throws Exception // main
     {
     boolean insertFlag = false;
   
     
     if(form.getCaseTypeEdit().equalsIgnoreCase("s")){
     if(form.getCaseStatus().equalsIgnoreCase("open")){
    	 
    	 form.setOrderDate("");
    	 form.setOrderNumber("");
    	 form.setMrkValue("");
    	 form.setStampDuty("");
    	 form.setRegFee("");
    	 form.setPaymentAmount("");
    	 form.setPaymentType("");
    	 form.setPaymentDate("");
    	 form.setPaymentRemarks("");
     }
     else if(form.getCaseStatus().equalsIgnoreCase("close")){
    	 
    	 form.setRemarks("");
     }
     
     }
     String[] param = null;
     if(form.getCaseTypeEdit().equalsIgnoreCase("s")){
    	 
    	
    	 param = new String[14];
    
    	  param[0] = form.getCaseStatus();
           param[1] = form.getOrderDate();
   	    param[2] = form.getOrderNumber();
   	    param[3] = form.getMrkValue().equalsIgnoreCase("")?"":form.getMrkValue();
   	    param[4] = form.getStampDuty().equalsIgnoreCase("")?"":form.getStampDuty();
   	    param[5] = form.getRegFee().equalsIgnoreCase("")?"":form.getRegFee();
   	   param[6] = form.getPaymentAmount().equalsIgnoreCase("")?"":form.getPaymentAmount();
   	  param[7] = form.getPaymentType();
   	  param[8] = form.getPaymentDate();
   	  
   	  if(form.getCaseStatus().equalsIgnoreCase("open")){
   	  param[9] = form.getRemarks();//open remarks
     param[10] = "";//payment remarks
   	  }
   	  else{
   		  param[9] = form.getPaymentRemarks();//open remarks
   	     param[10] = "";//payment remarks
   		  
   	  }
   	  param[11] = form.getCaseDTO().getLoggedInUser();
   	   param[12] = form.getUpdateRemarks();
	  param[13] = form.getCaseTxId();
     }
     
     if(form.getCaseTypeEdit().equalsIgnoreCase("r")){
    	  param = new String[10];
    	  
    	  param[0] = form.getCaseStatus();
          param[1] = form.getPayableAmt().equalsIgnoreCase("")?"":form.getPayableAmt();
   	      param[2] = form.getPaidAmt().equalsIgnoreCase("")?"":form.getPaidAmt();
   	      param[3] = form.getBalanceAmt().equalsIgnoreCase("")?"":form.getBalanceAmt();
   	      param[4] = form.getPaymentRemarks();
      	  param[5] = form.getCaseDate();
      	 param[6] = form.getCaseDTO().getLoggedInUser();
      	param[7] = form.getUpdateRemarks();
    	param[8] = form.getPaymentType();
      	param[9] = form.getCaseTxId();
     }

     insertFlag = caseDAO.saveEditCaseData(param,form);
     
     return insertFlag;
     }
	
	public String getRevenueHeadName(String revHeadId)throws Exception{
		   return caseDAO.getRevenueHeadName(revHeadId);
	}

	public String getSectionHeadName(String sectionHeadId)throws Exception{
		   return caseDAO.getSectionHeadName(sectionHeadId);
	}


	public ArrayList getPendingCases(String userId, PendingManualCaseForm form,
			String language, String userOffice) {
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list = caseDAO.getPendingApps(userId, form, userOffice);
		
		//double bal = 0;
		if (list != null && list.size() > 0) {

			ArrayList rowList;
			PendingManualCaseDTO manualDTO;
			try {
				for (int i = 0; i < list.size(); i++) {
					rowList = (ArrayList) list.get(i);
					manualDTO = new PendingManualCaseDTO();
					// double bal=0;
					
							String caseType = rowList.get(4) != null ? rowList.get(4)
									.toString() : "-";
									
									
									form.setCaseTypeEdit(caseType);
									if(caseType.equalsIgnoreCase("s"))	{
					  		
							if (language
									.equalsIgnoreCase("en")) {
								
								caseType = "Stamp Cases";
							}
							
							else{
								caseType = "मुद्रांक प्रकरण ";
								
							}
							
							manualDTO.setCaseTypeDisplay(caseType);
						}
                         if(caseType.equalsIgnoreCase("r"))	{
							
							
                        		if (language
    									.equalsIgnoreCase("en")) {
    								
    								caseType = "RRC Cases";
    							}
    							
    							else{
    								caseType = "आरआरसी प्रकरण";
    								
    							}
                        		manualDTO.setCaseTypeDisplay(caseType);
						}
                         manualDTO.setCaseTxId(rowList.get(0) != null ? rowList.get(0)
 								.toString() : "-")	;
						manualDTO.setStampCaseDisplay(rowList.get(1) != null ? rowList.get(1)
								.toString() : "-")	;
						manualDTO.setRrcCaseDisplay(rowList.get(3) != null ? rowList.get(3)
								.toString() : "-")	;
						manualDTO.setRevHeadDisplay(rowList.get(5) != null ? rowList.get(5)
								.toString() : "-")	;
						manualDTO.setSectionDisplay(rowList.get(6) != null ? rowList.get(6)
								.toString() : "-")	;
						manualDTO.setStatusDisplay(rowList.get(7) != null ? rowList.get(7)
								.toString() : "-")	;
						
						
						if (language
								.equalsIgnoreCase("hi")) {
							
							if(manualDTO.getStatusDisplay().equalsIgnoreCase("open")){
								
								manualDTO.setStatusDisplay("खोलें");
							}
							else if(manualDTO.getStatusDisplay().equalsIgnoreCase("close")){
								
								manualDTO.setStatusDisplay("बंद करे");	
							}
							
							
						}
						
						
						
						manualDTO.setDateDisplay(rowList.get(8) != null ? rowList.get(8)
								.toString() : "-")	;
						
						if(rowList.get(8)!=null){
						/*String newstring = new SimpleDateFormat("dd-mm-yyyy").format(rowList.get(8)
								);*/
							/*SimpleDateFormat sdfIn = new SimpleDateFormat("dd-mm-YYYY HH:mm:ss");
						Date date = (Date) sdfIn.parse(rowList.get(8)
								.toString());
						
						String newstring = new SimpleDateFormat("dd-mm-yyyy").format(date);
						*/
						
						
						SimpleDateFormat date1 = new SimpleDateFormat("yyyy-mm-dd");
						SimpleDateFormat date2 = new SimpleDateFormat("dd-mm-yyyy");
						String sysdate = rowList.get(8).toString();
						Date d1 = date1.parse(sysdate);
						String formatDate = date2.format(d1);
					//	System.out.println(formatDate); // 2011-01-18
						manualDTO.setDateDisplay(formatDate)	;}
						else{
							manualDTO.setDateDisplay("-");
							
						}
						
						
						//System.out.printf("dasdasdas"+manualDTO.getCaseTypeDisplay(),manualDTO.getStampCaseDisplay());
					pendingAppListFinal.add(manualDTO);
					

				}

			} catch (Exception e) {

				logger.debug(e.getMessage(), e);

			}
		}
		return pendingAppListFinal;
	}


	public void getEditDetails( String hiddenCaseNumber, PendingManualCaseForm form , String language) throws Exception {
		
		HashMap caseMap = new HashMap();
		ArrayList caseList = new ArrayList();
	//	caseId = "10";
		//PendingManualCaseForm form = new PendingManualCaseForm();
		caseList = getCaseDetlsForDisplay(hiddenCaseNumber);
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("dd-mm-yyyy");
		
		//PendingManualCaseDTO dto = new PendingManualCaseDTO();
		form.setSectionType("");
		form.setSectionCommon("");
		if (caseList != null && caseList.size() == 1) {

			ArrayList rowList = (ArrayList) caseList.get(0);
			
				if(rowList.get(0)!=null && !rowList.get(0).toString().isEmpty()){
					
					form.setCaseTxId(rowList.get(0).toString());
				}
				else{
					form.setCaseTxId("-");
				}
				
              if(rowList.get(1)!=null && !rowList.get(1).toString().isEmpty()){
					
            	  form.setCaseType(rowList.get(1).toString());
					
            	  form.setCaseTypeEdit(rowList.get(1).toString());
					if(form.getCaseType().equalsIgnoreCase("r")){
						//form.setSectionCommon("0");
						//form.setSectionType("");
						if(language.equalsIgnoreCase("en")){
						 form.setCaseType("RRC cases");}
						else{
							
							form.setCaseType("आरआरसी प्रकरण");
						}
						
					
						
					}
					
					else{
						
						//form.setSectionCommon("1");
						//form.setSectionType("0");
						if(language.equalsIgnoreCase("en")){
						 form.setCaseType("Stamp cases");}
						else{
							
							 form.setCaseType("मुद्रांक प्रकरण");	
						}
						
					}
				}
				else{
					form.setCaseType("-");
				}
              
              if(rowList.get(2)!=null && !rowList.get(2).toString().isEmpty()){
					
            	  form.setRevHeadName(rowList.get(2).toString());
				}
				else{
					form.setRevHeadName("-");
				}
              
              if(rowList.get(3)!=null && !rowList.get(3).toString().isEmpty()){
					
            	  form.setRevHeadId(rowList.get(3).toString());
				}
				else{
					form.setRevHeadId("-");
				}
              
              if(rowList.get(4)!=null && !rowList.get(4).toString().isEmpty()){
					
            	  form.setSectionId(rowList.get(4).toString());
				}
				else{
					form.setSectionId("-");
				}
              
              if(rowList.get(5)!=null && !rowList.get(5).toString().isEmpty()){
					
            	  form.setSectionName(rowList.get(5).toString());
				}
				else{
					form.setSectionName("-");
				}
              
              if(rowList.get(6)!=null && !rowList.get(6).toString().isEmpty()){
					
            	  form.setCaseNumber(rowList.get(6).toString());
				}
				else{
					form.setCaseNumber("-");
				}
              
              if(rowList.get(7)!=null && !rowList.get(7).toString().isEmpty()){
					
					//dto.setCaseDate(rowList.get(7).toString());
					
					String sysdate = rowList.get(7).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					form.setCaseDate(formatDate)	;
				}
				else{
					form.setCaseDate("-");
				}
              
              if(rowList.get(8)!=null && !rowList.get(8).toString().isEmpty()){
					
            	  form.setMktValueDOc(rowList.get(8).toString());
				}
				else{
					form.setMktValueDOc("-");
				}
              
              if(rowList.get(9)!=null && !rowList.get(9).toString().isEmpty()){
					
            	  form.setPraposedStampSubReg(rowList.get(9).toString());
				}
				else{
					form.setPraposedStampSubReg("-");
				}
              
              if(rowList.get(10)!=null && !rowList.get(10).toString().isEmpty()){
					
            	  form.setCaseStatus(rowList.get(10).toString());
				}
				else{
					form.setCaseStatus("-");
				}
              
              if(rowList.get(11)!=null && !rowList.get(11).toString().isEmpty()){
					
				//	dto.setOrderDate(rowList.get(11).toString());
					String sysdate = rowList.get(11).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					form.setOrderDate(formatDate)	;
				}
				else{
					form.setOrderDate("-");
				}
              
              if(rowList.get(12)!=null && !rowList.get(12).toString().isEmpty()){
					
            	  form.setMrkValue(rowList.get(12).toString());
				}
				else{
					form.setMrkValue("-");
				}
              if(rowList.get(13)!=null && !rowList.get(13).toString().isEmpty()){
					
            	  form.setOrderNumber(rowList.get(13).toString());
				}
				else{
					form.setOrderNumber("-");
				}
              
              if(rowList.get(14)!=null && !rowList.get(14).toString().isEmpty()){
					
            	  form.setStampDuty(rowList.get(14).toString());
				}
				else{
					form.setStampDuty("-");
				}
              
              if(rowList.get(15)!=null && !rowList.get(15).toString().isEmpty()){
					
            	  form.setRegFee(rowList.get(15).toString());
				}
				else{
					form.setRegFee("-");
				}
              
              if(rowList.get(16)!=null && !rowList.get(16).toString().isEmpty()){
					
            	  form.setPaymentType(rowList.get(16).toString());
				}
				else{
					form.setPaymentType("-");
				}
              if(rowList.get(17)!=null && !rowList.get(17).toString().isEmpty()){
					
            	  form.setPayableAmt(rowList.get(17).toString());
				}
				else{
					form.setPayableAmt("-");
				}
              if(rowList.get(18)!=null && !rowList.get(18).toString().isEmpty()){
					
            	  String sysdate = rowList.get(18).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					//dto.setOrderDate(formatDate)	;
					form.setPaymentDate(formatDate);
				}
				else{
					form.setPaymentDate("-");
				}
             
              // when status is open
              if(rowList.get(37)!=null && !rowList.get(37).toString().isEmpty()){
					
            	  form.setRemarks(rowList.get(37).toString());
            	  form.setPaymentRemarks(rowList.get(37).toString());
				}
				else{
					form.setRemarks("-");
					form.setPaymentRemarks("-");
				} 
            //when status is closed
         /*     if(rowList.get(19)!=null && !rowList.get(19).toString().isEmpty()){
					
            	  form.setPaymentRemarks(rowList.get(19).toString());
				}
				else{
					form.setPaymentRemarks("-");
				}*/
              
              
             
              
              if(rowList.get(20)!=null && !rowList.get(20).toString().isEmpty()){
					
            	  form.setPraposedMktValueAudit(rowList.get(20).toString());
				}
				else{
					form.setPraposedMktValueAudit("-");
				}
              
              if(rowList.get(21)!=null && !rowList.get(21).toString().isEmpty()){
					
            	  form.setPraposedStampAudit(rowList.get(21).toString());
				}
				else{
					form.setPraposedStampAudit("-");
				}
              
              if(rowList.get(22)!=null && !rowList.get(22).toString().isEmpty()){
					
            	  form.setPraposedRegAudit(rowList.get(22).toString());
				}
				else{
					form.setPraposedRegAudit("-");
				}
              
              if(rowList.get(23)!=null && !rowList.get(23).toString().isEmpty()){
					
            	  form.setTotalAudit(rowList.get(23).toString());
				}
				else{
					form.setTotalAudit("-");
				}
              
              if(rowList.get(24)!=null && !rowList.get(24).toString().isEmpty()){
					
            	  form.setStampImpound(rowList.get(24).toString());
				}
				else{
					form.setStampImpound("-");
				}
              
              if(rowList.get(25)!=null && !rowList.get(25).toString().isEmpty()){
					
            	  form.setDeficitStampAudit(rowList.get(25).toString());
				}
				else{
					form.setDeficitStampAudit("-");
				}
              
              if(rowList.get(26)!=null && !rowList.get(26).toString().isEmpty()){
					
            	  form.setDeficitStampCollector(rowList.get(26).toString());
				}
				else{
					form.setDeficitStampCollector("-");
				}
              
              if(rowList.get(27)!=null && !rowList.get(27).toString().isEmpty()){
					
            	  form.setEstimatedDeffStampAudit(rowList.get(27).toString());
				}
				else{
					form.setEstimatedDeffStampAudit("-");
				}
              if(rowList.get(28)!=null && !rowList.get(28).toString().isEmpty()){
					
            	  form.setStampCaseNumber(rowList.get(28).toString());
				}
				else{
					form.setStampCaseNumber("-");
				}
              if(rowList.get(29)!=null && !rowList.get(29).toString().isEmpty()){
					
            	  form.setRrcCaseNumber(rowList.get(29).toString());
				}
				else{
					form.setRrcCaseNumber("-");
				}
              
              if(rowList.get(30)!=null && !rowList.get(30).toString().isEmpty()){
					
            	  form.setPaidAmt(rowList.get(30).toString());
				}
				else{
					form.setPaidAmt("-");
				}
              if(rowList.get(31)!=null && !rowList.get(31).toString().isEmpty()){
					
            	  form.setBalanceAmt(rowList.get(31).toString());
				}
				else{
					form.setBalanceAmt("-");
				}
              
              if(rowList.get(38)!=null && !rowList.get(38).toString().isEmpty()){
					
            	  form.setPaymentAmount(rowList.get(38).toString());
				}
				else{
					form.setPaymentAmount("-");
				}
              
              if(rowList.get(39)!=null && !rowList.get(39).toString().isEmpty()){
					
            	  form.setUpdateRemarks(rowList.get(39).toString());
				}
				else{
					form.setUpdateRemarks("-");
				}
              
              if(rowList.get(40)!=null && !rowList.get(40).toString().isEmpty()){
					
            	  form.setPraposedMrktValSr(rowList.get(40).toString());
				}
				else{
					form.setPraposedMrktValSr("-");
				}
              
                
              if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("1")){
            	  form.setSectionType("1");
  				}
  				if(form.getRevHeadId().equalsIgnoreCase("2") && (form.getSectionId().equalsIgnoreCase("2"))){
  					form.setSectionType("2");
  				}
  				
  				if(form.getRevHeadId().equalsIgnoreCase("2") &&  form.getSectionId().equalsIgnoreCase("3")){
  					form.setSectionType("6");
  				}
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("4")){
  					form.setSectionType("3");
  			    }
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("5")){
  					form.setSectionType("4");
  				    }
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("6")){
  					form.setSectionType("5");
  				    }
			
  				
  				
     	//caseMap.put(hiddenCaseNumber, dto);	
		}
		else{
			
		//	return null;
		}
		//return caseMap;
	}

	
public void getEditDetailsDisplay( String hiddenCaseNumber, PendingManualCaseForm form , String language) throws Exception {
		
		HashMap caseMap = new HashMap();
		ArrayList caseList = new ArrayList();
	//	caseId = "10";
		//PendingManualCaseForm form = new PendingManualCaseForm();
		caseList = getCaseDetlsForDisplay(hiddenCaseNumber);
		SimpleDateFormat date1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat date2 = new SimpleDateFormat("dd-mm-yyyy");
		
		//PendingManualCaseDTO dto = new PendingManualCaseDTO();
		form.setSectionType("");
		form.setSectionCommon("");
		if (caseList != null && caseList.size() == 1) {

			ArrayList rowList = (ArrayList) caseList.get(0);
			
				if(rowList.get(0)!=null && !rowList.get(0).toString().isEmpty()){
					
					form.setCaseTxId(rowList.get(0).toString());
				}
				else{
					form.setCaseTxId("");
				}
				
              if(rowList.get(1)!=null && !rowList.get(1).toString().isEmpty()){
					
            	  form.setCaseType(rowList.get(1).toString());
					
            	  form.setCaseTypeEdit(rowList.get(1).toString());
					if(form.getCaseType().equalsIgnoreCase("r")){
						//form.setSectionCommon("0");
						//form.setSectionType("");
						if(language.equalsIgnoreCase("en")){
						 form.setCaseType("RRC cases");}
						else{
							
							form.setCaseType("आरआरसी प्रकरण ");
						}
						
					
						
					}
					
					else{
						
						//form.setSectionCommon("1");
						//form.setSectionType("0");
						if(language.equalsIgnoreCase("en")){
						 form.setCaseType("Stamp cases");}
						else{
							
							 form.setCaseType("मुद्रांक प्रकरण");	
						}
						
					}
				}
				else{
					form.setCaseType("-");
				}
              
              if(rowList.get(2)!=null && !rowList.get(2).toString().isEmpty()){
					
            	  form.setRevHeadName(rowList.get(2).toString());
				}
				else{
					form.setRevHeadName("-");
				}
              
              if(rowList.get(3)!=null && !rowList.get(3).toString().isEmpty()){
					
            	  form.setRevHeadId(rowList.get(3).toString());
				}
				else{
					form.setRevHeadId("-");
				}
              
              if(rowList.get(4)!=null && !rowList.get(4).toString().isEmpty()){
					
            	  form.setSectionId(rowList.get(4).toString());
				}
				else{
					form.setSectionId("-");
				}
              
              if(rowList.get(5)!=null && !rowList.get(5).toString().isEmpty()){
					
            	  form.setSectionName(rowList.get(5).toString());
				}
				else{
					form.setSectionName("-");
				}
              
              if(rowList.get(6)!=null && !rowList.get(6).toString().isEmpty()){
					
            	  form.setCaseNumber(rowList.get(6).toString());
				}
				else{
					form.setCaseNumber("-");
				}
              
              if(rowList.get(7)!=null && !rowList.get(7).toString().isEmpty()){
					
					//dto.setCaseDate(rowList.get(7).toString());
					
					String sysdate = rowList.get(7).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					form.setCaseDate(formatDate)	;
				}
				else{
					form.setCaseDate("-");
				}
              
              if(rowList.get(8)!=null && !rowList.get(8).toString().isEmpty()){
					
            	  form.setMktValueDOc(rowList.get(8).toString());
				}
				else{
					form.setMktValueDOc("-");
				}
              
              if(rowList.get(9)!=null && !rowList.get(9).toString().isEmpty()){
					
            	  form.setPraposedStampSubReg(rowList.get(9).toString());
				}
				else{
					form.setPraposedStampSubReg("-");
				}
              
              if(rowList.get(10)!=null && !rowList.get(10).toString().isEmpty()){
					
            	  form.setCaseStatus(rowList.get(10).toString());
				}
				else{
					form.setCaseStatus("-");
				}
              
              if(rowList.get(11)!=null && !rowList.get(11).toString().isEmpty()){
					
				//	dto.setOrderDate(rowList.get(11).toString());
					String sysdate = rowList.get(11).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					form.setOrderDate(formatDate)	;
				}
				else{
					form.setOrderDate("");
				}
              
              if(rowList.get(12)!=null && !rowList.get(12).toString().isEmpty()){
					
            	  form.setMrkValue(rowList.get(12).toString());
				}
				else{
					form.setMrkValue("");
				}
              if(rowList.get(13)!=null && !rowList.get(13).toString().isEmpty()){
					
            	  form.setOrderNumber(rowList.get(13).toString());
				}
				else{
					form.setOrderNumber("");
				}
              
              if(rowList.get(14)!=null && !rowList.get(14).toString().isEmpty()){
					
            	  form.setStampDuty(rowList.get(14).toString());
				}
				else{
					form.setStampDuty("");
				}
              
              if(rowList.get(15)!=null && !rowList.get(15).toString().isEmpty()){
					
            	  form.setRegFee(rowList.get(15).toString());
				}
				else{
					form.setRegFee("");
				}
              
              if(rowList.get(16)!=null && !rowList.get(16).toString().isEmpty()){
					
            	  form.setPaymentType(rowList.get(16).toString());
				}
				else{
					form.setPaymentType("");
				}
              if(rowList.get(17)!=null && !rowList.get(17).toString().isEmpty()){
					
            	  form.setPayableAmt(rowList.get(17).toString());
				}
				else{
					form.setPayableAmt("");
				}
              if(rowList.get(18)!=null && !rowList.get(18).toString().isEmpty()){
					
            	  String sysdate = rowList.get(18).toString();
					Date d1 = date1.parse(sysdate);
					String formatDate = date2.format(d1);
				//	System.out.println(formatDate); // 2011-01-18
					//dto.setOrderDate(formatDate)	;
					form.setPaymentDate(formatDate);
				}
				else{
					form.setPaymentDate("");
				}
             
              // when status is open
              if(rowList.get(37)!=null && !rowList.get(37).toString().isEmpty()){
					
            	  form.setRemarks(rowList.get(37).toString());
            	  form.setPaymentRemarks(rowList.get(37).toString());
				}
				else{
					form.setRemarks("");
					form.setPaymentRemarks("");
				} 
            //when status is closed
              /*if(rowList.get(19)!=null && !rowList.get(19).toString().isEmpty()){
					
            	  form.setPaymentRemarks(rowList.get(19).toString());
				}
				else{
					form.setPaymentRemarks("");
				}*/
              
              
             
              
              if(rowList.get(20)!=null && !rowList.get(20).toString().isEmpty()){
					
            	  form.setPraposedMktValueAudit(rowList.get(20).toString());
				}
				else{
					form.setPraposedMktValueAudit("-");
				}
              
              if(rowList.get(21)!=null && !rowList.get(21).toString().isEmpty()){
					
            	  form.setPraposedStampAudit(rowList.get(21).toString());
				}
				else{
					form.setPraposedStampAudit("-");
				}
              
              if(rowList.get(22)!=null && !rowList.get(22).toString().isEmpty()){
					
            	  form.setPraposedRegAudit(rowList.get(22).toString());
				}
				else{
					form.setPraposedRegAudit("-");
				}
              
              if(rowList.get(23)!=null && !rowList.get(23).toString().isEmpty()){
					
            	  form.setTotalAudit(rowList.get(23).toString());
				}
				else{
					form.setTotalAudit("-");
				}
              
              if(rowList.get(24)!=null && !rowList.get(24).toString().isEmpty()){
					
            	  form.setStampImpound(rowList.get(24).toString());
				}
				else{
					form.setStampImpound("-");
				}
              
              if(rowList.get(25)!=null && !rowList.get(25).toString().isEmpty()){
					
            	  form.setDeficitStampAudit(rowList.get(25).toString());
				}
				else{
					form.setDeficitStampAudit("-");
				}
              
              if(rowList.get(26)!=null && !rowList.get(26).toString().isEmpty()){
					
            	  form.setDeficitStampCollector(rowList.get(26).toString());
				}
				else{
					form.setDeficitStampCollector("-");
				}
              
              if(rowList.get(27)!=null && !rowList.get(27).toString().isEmpty()){
					
            	  form.setEstimatedDeffStampAudit(rowList.get(27).toString());
				}
				else{
					form.setEstimatedDeffStampAudit("-");
				}
              if(rowList.get(28)!=null && !rowList.get(28).toString().isEmpty()){
					
            	  form.setStampCaseNumber(rowList.get(28).toString());
				}
				else{
					form.setStampCaseNumber("-");
				}
              if(rowList.get(29)!=null && !rowList.get(29).toString().isEmpty()){
					
            	  form.setRrcCaseNumber(rowList.get(29).toString());
				}
				else{
					form.setRrcCaseNumber("-");
				}
              
              if(rowList.get(30)!=null && !rowList.get(30).toString().isEmpty()){
					
            	  form.setPaidAmt(rowList.get(30).toString());
				}
				else{
					form.setPaidAmt("");
				}
              if(rowList.get(31)!=null && !rowList.get(31).toString().isEmpty()){
					
            	  form.setBalanceAmt(rowList.get(31).toString());
				}
				else{
					form.setBalanceAmt("");
				}
              
              if(rowList.get(38)!=null && !rowList.get(38).toString().isEmpty()){
					
            	  form.setPaymentAmount(rowList.get(38).toString());
				}
				else{
					form.setPaymentAmount("");
				}
              
              if(rowList.get(39)!=null && !rowList.get(39).toString().isEmpty()){
					
            	  form.setUpdateRemarks(rowList.get(39).toString());
				}
				else{
					form.setUpdateRemarks("");
				}
              
                
              if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("1")){
            	  form.setSectionType("1");
  				}
  				if(form.getRevHeadId().equalsIgnoreCase("2") && form.getSectionId().equalsIgnoreCase("2") ){
  					form.setSectionType("2");
  				}
  				
  				if(form.getRevHeadId().equalsIgnoreCase("2") &&  form.getSectionId().equalsIgnoreCase("3")){
  					form.setSectionType("6");
  				}
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("4")){
  					form.setSectionType("3");
  			    }
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("5")){
  					form.setSectionType("4");
  				    }
  				if(form.getRevHeadId().equalsIgnoreCase("1") && form.getSectionId().equalsIgnoreCase("6")){
  					form.setSectionType("5");
  				    }
			
     	//caseMap.put(hiddenCaseNumber, dto);	
		}
		else{
			
		//	return null;
		}
		//return caseMap;
	}

	private ArrayList getCaseDetlsForDisplay(String caseId) {
		// TODO Auto-generated method stub
		ArrayList caseList = new ArrayList();
		caseList = caseDAO.getCaseDetlsForDisplay(caseId);
		
		return caseList;
	}


	public String getOfficeType(String officeId) {

		String officeType = null;
	//	ReportDAO dao = new ReportDAO();
		officeType = caseDAO.getOfficeType(officeId);

		return officeType;

	}

	public String getDistrictId(String officeId) {
		// TODO Auto-generated method stub
		//ReportDAO dao = new ReportDAO();

		return caseDAO.getDistrictId(officeId);
	}

	public String getType(String officeType) {
		// TODO Auto-generated method stub
		
		
		//ReportDAO dao = new ReportDAO();
		return caseDAO.getType(officeType);
	}

	public String getDIGZoneBo(String officeId) {
	//	ReportDAO dao = new ReportDAO();
		return caseDAO.getDIGZoneDAO(officeId);
	}


	public ArrayList getDistDIGListBO(String zoneId,String language) {
		//ReportDAO dao = new ReportDAO();
		return caseDAO.getDistDIGListDAO(zoneId,language);
	}


	public ArrayList getDistListBO(String officeId) {
		//ReportDAO dao = new ReportDAO();
		return caseDAO.getDistListDAO(officeId);

	}


	public boolean getSearchReportResult(String selectedDistrict,
			String selectedRevId, String selectedSectionId,
			String selectedCaseType, String durationFrom,String durationTo,
			String selectedCaseStatus, String selectedPaymentType, String selectedDRofficeId) {
		// TODO Auto-generated method stub
		return caseDAO.getSearchReportResult(selectedDistrict,selectedRevId,selectedSectionId,selectedCaseType,durationFrom,durationTo,selectedCaseStatus,selectedPaymentType,selectedDRofficeId);
	}


	
     }
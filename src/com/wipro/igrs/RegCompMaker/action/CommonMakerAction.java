/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonAction.java
 * Author      :   Imran Shaik
 * Description :   Represents the Common Action Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.RegCompMaker.action;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.bd.RegCompMkrBD;
import com.wipro.igrs.RegCompMaker.bo.RegCompMkrBO;
import com.wipro.igrs.RegCompMaker.constants.RegCompConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.Owner;
import com.wipro.igrs.newreginit.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;
import com.wipro.igrs.regCompChecker.bd.RegCompCheckerBD;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;
import com.wipro.igrs.regCompChecker.form.RegCompCheckerForm;




public class CommonMakerAction extends  Action {
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonMakerAction.class);
	private static Proxy proxy;
	//static private int count = 0;
     private HashMap map =null;
     private HashMap map2 =null;
    
  
    boolean bol = true;
    static private String key = "key";
    //static private int keyCount=0;
   
	@SuppressWarnings("unchecked")
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response ) throws Exception {
			
		
			 HttpSession session = request.getSession();
			 String language = (String)session.getAttribute("languageLocale");
			 //if(session.getAttribute("forwardPath")==null)
			 //session.setAttribute("forwardPath", "./regInit.do");
			 session.setAttribute("modName","Registration Process");
			 session.setAttribute("fnName","Initiation");
			String forward="";
			 
			RegCommonBO commonBo = new RegCommonBO();
			  RegCompMkrBO boMaker =new RegCompMkrBO();
			RegCommonForm regForm;
			RegCompletionForm regCompForm;
				RegCompCheckerBD bd = new RegCompCheckerBD(); 	
			

			if(request.getAttribute("regFormDashboard")!=null){
				regForm=(RegCommonForm)request.getAttribute("regFormDashboard");
				
				request.setAttribute("deedId", regForm.getDeedID());
    			request.setAttribute("roleId", regForm.getPartyType());
    			//request.setAttribute("hidnRegTxnId", regForm.getHidnRegTxnId());
    			
			}
			else
			    regForm = (RegCommonForm)form;
			
			  if("CONFIRMATION_OK_ACTION_NO_VALUATION1".equalsIgnoreCase(regForm.getActionName()))
              {
            	  logger.debug("*********CONFIRMATION NO VALUATION***************");
            	  if(regForm.getDeedDoc().equalsIgnoreCase("Y"))
             	  {
            		  ArrayList deedDocList = boMaker.getDeedDocDetails(regForm.getHidnRegTxnId());
						for(int i = 0 ; i < deedDocList.size() ;i++)
						{
							ArrayList subList = (ArrayList)deedDocList.get(i);
							String deedDocPath = (String)subList.get(1);
							String deedDocName = (String)subList.get(0);
							if(deedDocPath == null || deedDocPath.equals(""))
							{
								regForm.setDeedDocName("");
								regForm.setDeedDocPath("");
								regForm.setDeedStat("4");
								regForm.setPdfFlag("N");
								regForm.setDeedDraftId("");
							}
							else
							{
								regForm.setDeedDocName(deedDocName);
								regForm.setDeedDocPath(deedDocPath);
								regForm.setDeedStat("5");
								regForm.setPdfFlag("Y");
								regForm.setDeedDraftId("");
							}
								
						}
            		  
            		  
            	  }
            	  else
            	  {
            		  regForm.setDeedDocName("");
						regForm.setDeedDocPath("");
						regForm.setDeedStat("4");
						regForm.setPdfFlag("N");
						regForm.setDeedDraftId("");
            	  }
            	  String[] deedInstArray=boMaker.getDeedInstId1(regForm.getHidnRegTxnId());
            	  int adjuFlag = 0;
               	 if(deedInstArray!=null && deedInstArray.length>0){
                   
                   request.setAttribute("deedId", deedInstArray[0].trim());
                   regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));
                   request.setAttribute("instId", deedInstArray[1].trim());
                   regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                   regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                	if(deedInstArray[3].trim()!=null && deedInstArray[3].trim().equalsIgnoreCase("R")){
        				adjuFlag=1;
        				regForm.setAdjudicationNumber(regForm.getHidnRegTxnId());
        				
                	}
               	 }
              
             	regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
          		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
             	  if(regForm.getDeedDoc().equalsIgnoreCase("Y"))
             	  {
             		 ArrayList deedDocList = boMaker.getDeedDocDetails(regForm.getHidnRegTxnId());
             		if(deedDocList.size() == 0)
					{
             			regForm.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC);
             			regForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
					}
             		else
             		{
             			for(int i = 0 ; i < deedDocList.size() ;i++)
						{
							ArrayList subList = (ArrayList)deedDocList.get(i);
							String deedDocPath = (String)subList.get(1);
							String deedDocName = (String)subList.get(0);
							if(deedDocPath == null || deedDocPath.equals(""))
							{
								regForm.setDeedDocName("");
								regForm.setDeedDocPath("");
								regForm.setDeedStat("4");
								regForm.setPdfFlag("N");
								regForm.setDeedDraftId("");
							}
							else
							{
								regForm.setDeedDocName(deedDocName);
								regForm.setDeedDocPath(deedDocPath);
								regForm.setDeedDraftId("");
								regForm.setDeedStat("5");
								regForm.setPdfFlag("Y");
							}
								
								
						}
						
             		}
						
         		  
             	  }
             	 else
               	{
               		regForm.setDeedDocName("");
						regForm.setDeedDocPath("");
						regForm.setDeedStat("4");
						regForm.setPdfFlag("N");
						regForm.setDeedDraftId("");
               	}
             		/*if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION || 
             				regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
             				regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
             				regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
             				regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_POA ||
             				regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
             				regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
             				regForm.getCommonDeed()==1){      //for deeds following code set2
             			
             			
             			if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
             					regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
             					regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
             				
             				if(		regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9 ||
             						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_1 ||
             						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_2 ||
             						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_1){
             					
             					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2);
             					
             				}else if(regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_10 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_5 ||
             						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_8 ||
             						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_3 ||
             						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_4 ||
             						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_2){
             					
             					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_REQ_CONSTANT_1);
             				}
             				
             				
             			}
             			
             			regForm.setDeedTypeFlag(1);
             			//regForm.setHdnDeclareShareCheck("N");
             			
             			
             		}*/
             		String flags[];
             			
             		flags=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
             		
             			RegCompCheckerForm eForm = (RegCompCheckerForm)form;
             			flags=commonBo.getInstrumentFlag(String.valueOf( eForm.getRegDTO().getInstID()));
             		
        			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
        				
        				if(flags[2].trim().equalsIgnoreCase("Y"))
        				{regForm.setCommonDeed(1);}
        				else
        				{regForm.setCommonDeed(0);}
        				
        				regForm.setPvReqFlag(flags[1].trim());
        				regForm.setPropReqFlag(flags[0].trim());
        				
        				
        			}else{
        				regForm.setCommonDeed(0);
        				regForm.setPvReqFlag("");
        				regForm.setPropReqFlag("");
        			}
        			regForm.setShareOfPropSize(0);
        			String dutyTxnId = bd.getDutyTxnId(regForm.getHidnRegTxnId());
    				if(dutyTxnId.equals(null)|| dutyTxnId.equals(""))
    					regForm.setDuty_txn_id(0);
    				else
    					regForm.setDuty_txn_id(Integer.parseInt(dutyTxnId));
            	  boMaker.landConfirmationScreen(regForm,language);
     			 logger.debug("^^^^^^^"+regForm.getDeedtype1());
     			 logger.debug("^^^^^^^"+regForm.getInstType());
     			regForm.getRegDTO().setParentPathScan(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC);
     			regForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
     			regForm.getRegDTO().setGuidUpload("");
     			regForm.getRegDTO().setDeedDoc("N");
     			 //if(regForm.getDeedID()!=0 && regForm.getInstID()!=0){
     			regForm.setCommonDeed(commonBo.getCommonDeedFlag(String.valueOf(regForm.getDeedID())));
     			String TempId=regForm.getHidnRegTxnId();
     			ArrayList estampDet = commonBo.getEstampDet(TempId);
                regForm.setEstampDetLst(estampDet);
                if(estampDet.size()!=0)
                {
                      ArrayList list = new ArrayList();
                      list= (ArrayList) estampDet.get(0);
                      regForm.setEstampCode((String)list.get(8));
                      regForm.setEstampAmt((String)list.get(1));
                      String transDate1=(String)list.get(2);
                      SimpleDateFormat sdf1 = new SimpleDateFormat ("yyyy-mm-dd");
                      SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/mm/yyyy");
                      Date d1 = sdf1.parse(transDate1);
                      String transDate = sdf2.format(d1);
                      regForm.setEstampDateTime(transDate);
                }
     			
     			 request.setAttribute("deedId", regForm.getDeedID());
     			 request.setAttribute("instId", regForm.getInstID());
     			 //}
     			 
String dutyListArr[]=new String[9];
          		
          		if(adjuFlag==1){
          			
          			dutyListArr=boMaker.getDutyDetlsAdjuForConfirmation(regForm.getAdjudicationNumber());
				// START changes to fetch the duty details if data not found for adjudicated application by santosh
          			if(dutyListArr==null || dutyListArr.length==0){
          				dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          			}
          		   // END changes to fetch the duty details if data not found for adjudicated application by santosh
          		}
          		else{
          			dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          		}
                        
                        regForm.setStampduty1(dutyListArr[0].trim());
                        regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                        regForm.setPanchayatDuty(dutyListArr[1].trim());
                        regForm.setUpkarDuty(dutyListArr[3].trim());
                        regForm.setTotalduty(dutyListArr[5].trim());
                        regForm.setRegistrationFee(dutyListArr[4].trim());

     			 /*if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
    					 regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_POA ||
    					 regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
    					 regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
    					 (regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
    					 (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
    							 //&& 
      							 //(regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )
    							 )||
            			 regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
     					(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
     							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
             			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
             					&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                     	(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
                     			&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
    					 regForm.getDeedTypeFlag()==1|| regForm.getCommonDeed()==1){
    				 forward="reginitConfirmNonPV";
    			 }else{
    				 forward="reginitConfirm";
    			 }
    			 */
                        
                        ArrayList dutyList = new ArrayList();
                        if(adjuFlag==1){
                  			
                  			dutyList=boMaker.getAdjudicatedDutyDetlsChecker(regForm.getHidnRegTxnId());
                  		}
                  		else{
                  			dutyList=boMaker.getDutyDetls(regForm.getHidnRegTxnId());
                  		}
               			
               			for(int i = 0;i <dutyList.size();i++)
                		{
                			RegCompCheckerDTO rrdto = (RegCompCheckerDTO)dutyList.get(i);
                			regForm.setExempStamp(rrdto.getExempStamp());
                			regForm.setExempReg(rrdto.getExempReg());
                			regForm.setTotalduty(rrdto.getTotalduty());
                			regForm.setRegistrationFee(rrdto.getRegistrationFee());
                		}
     			if(regForm.getPvReqFlag().equalsIgnoreCase("N"))
   			 {
   				 forward="reginitConfirmNonPV";
   			 }else{
   				 forward="reginitConfirm";
   			 }

     			regForm.setActionName("");
     			regForm.setForwardJsp(forward);
     			 
     			return mapping.findForward(forward);
              }
			//Start=== Added By ankita
            if(request.getAttribute("regForm")!=null)
            {
                  RegCommonForm form2=new RegCommonForm();
                  
                  form2=(RegCommonForm) request.getAttribute("regForm");
            //regForm=(RegCommonForm) request.getAttribute("regForm");
                  
                  regForm.setActionName(form2.getActionName());
                  regForm.setFromModule(form2.getFromModule());
                  regForm.setHidnRegTxnId(form2.getHidnRegTxnId());
                  regForm.setCheckRegNo(form2.getCheckRegNo());
                  regForm.setDeedDoc(form2.getDeedDoc());
            String fromModule=regForm.getFromModule();
            logger.debug("action Name"+regForm.getActionName());
            logger.debug("*************"+fromModule);
            if(fromModule.equalsIgnoreCase("RegCompMaker"))
            {
            	//regForm.setMapPropertyTransParty(new HashMap());
                  String actionName=regForm.getActionName();
                  if(actionName!=null)
                        
                  if(RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
                        logger.debug("**************CONFIRMATION***************");
                        logger.debug("regForm deed doc"+ regForm.getRegDTO().getDeedDoc());
                        logger.debug("regForm deed doc"+ regForm.getDeedDoc());
                        if(regForm.getDeedDoc().equalsIgnoreCase("Y"))
                  	  {
                        	ArrayList deedDocList = boMaker.getDeedDocDetails(regForm.getHidnRegTxnId());
                        	if(deedDocList.size() == 0)
        					{
                     			regForm.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC);
                     			regForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
        					}
                     		else
                     		{
                     			for(int i = 0 ; i < deedDocList.size() ;i++)
        						{
        							ArrayList subList = (ArrayList)deedDocList.get(i);
        							String deedDocPath = (String)subList.get(1);
        							String deedDocName = (String)subList.get(0);
        							if(deedDocPath == null || deedDocPath.equals(""))
        							{
        								regForm.setDeedDocName("");
        								regForm.setDeedDocPath("");
        								regForm.setDeedStat("4");
        								regForm.setPdfFlag("N");
        								regForm.setDeedDraftId("");
        							}
        							else
        							{
        								regForm.setDeedDocName(deedDocName);
        								regForm.setDeedDocPath(deedDocPath);
        								regForm.setDeedStat("5");
        								regForm.setPdfFlag("Y");
        								regForm.setDeedDraftId("");
        							}
        								
        								
        						}
        						
                     		}
                        	//Added by rustam for set form link
                        	ArrayList cyberformlist = boMaker.getCyberTehsilFormDetails(regForm.getHidnRegTxnId());
                        	if(cyberformlist.size() != 0){
                        		for(int i = 0 ; i < cyberformlist.size() ;i++)
        						{
        							ArrayList subList = (ArrayList)cyberformlist.get(i);
        							regForm.setFormA1Path((String) subList.get(1));
        							regForm.setFormA2Path((String) subList.get(2));
        						}
                        	}else{
                        		regForm.setFormA1Path("");
    							regForm.setFormA2Path("");
                        	}
                		  
                  	  }
                        else
                      	{
                      		regForm.setDeedDocName("");
    						regForm.setDeedDocPath("");
    						regForm.setDeedStat("4");
    						regForm.setPdfFlag("N");
    						regForm.setDeedDraftId("");
                      	}
                        String TempId=regForm.getHidnRegTxnId();
                        regForm.setShareOfPropSize(0);
                        System.out.println(TempId);
                        regForm.setHidnRegTxnId(TempId);
                        System.out.println("hidden id"+regForm.getHidnRegTxnId());
                        regForm.setCheckModule("W");
                //Start : code for getting eStamp Det
                        int adjuFlag=0;
                        ArrayList estampDet = commonBo.getEstampDet(TempId);
                        regForm.setEstampDetLst(estampDet);
                        if(estampDet.size()!=0)
                        {
                              ArrayList list = new ArrayList();
                              list= (ArrayList) estampDet.get(0);
                              regForm.setEstampCode((String)list.get(8));
                              regForm.setEstampAmt((String)list.get(1));
                              String transDate1=(String)list.get(2);
                              SimpleDateFormat sdf1 = new SimpleDateFormat ("yyyy-mm-dd");
                              SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/mm/yyyy");
                              Date d1 = sdf1.parse(transDate1);
                              String transDate = sdf2.format(d1);
                              regForm.setEstampDateTime(transDate);
                        }
                        
                        //End Code for getting Estamp Details
                        regForm.getRegDTO().setParentPathScan(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC);
             			regForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
             			regForm.getRegDTO().setGuidUpload("");
             			regForm.getRegDTO().setDeedDoc("N");
                        
                        regForm.setActionName(RegInitConstant.NO_ACTION);
                            //uncomment later
                      
                        String[] deedInstArray=boMaker.getDeedInstId1(regForm.getHidnRegTxnId());
                    	//String purpose=commonBo.getEStampPurpose(regForm.getHidnRegTxnId());
                    	//if(purpose!=null && !purpose.equalsIgnoreCase("")){
                    		//regForm.setPurpose(purpose);
                    	//}regForm.getHidnRegTxnId()
                  if(deedInstArray!=null && deedInstArray.length>0){
                        
                        request.setAttribute("deedId", deedInstArray[0].trim());
                        regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));
                        request.setAttribute("instId", deedInstArray[1].trim());
                        regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                        regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                        if(deedInstArray[2].trim().equals("-")){
                			regForm.setExmpID("");
                			regForm.setHdnExAmount("");
                			}else{
                				regForm.setExmpID(deedInstArray[2].trim());
                    			//regForm.setHdnExAmount(deedInstArray[2].trim());
                			}
                			if(deedInstArray[3].trim()!=null && deedInstArray[3].trim().equalsIgnoreCase("R")){
                				adjuFlag=1;
                				regForm.setAdjudicationNumber(regForm.getHidnRegTxnId());
                				/*if(deedInstArray[3].trim().length()==13){
                					String adjudicationID="0"+deedInstArray[3].trim();
                					regForm.setAdjudicationNumber(adjudicationID);
                				}else{
                				regForm.setAdjudicationNumber(deedInstArray[3].trim());
                				}*/
                			}
                        
                  }else {
                        request.setAttribute("deedId", 0);
                        request.setAttribute("instId", 0);
                        regForm.setDeedID(0);
                        regForm.setInstID(0);
                        regForm.setExmpID("");
            			regForm.setHdnExAmount("");
                  }
                  
                  logger.debug("deed id"+request.getAttribute("deedId"));
          		logger.debug("inst id"+request.getAttribute("instId"));
          		NumberFormat obj=new DecimalFormat("#0.00");
          		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
          		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
          		//below code for exemptions
          		
          		String exemptions = regForm.getExmpID();
          		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions,language));
          		
          		//below code for getting extra details
          		
          		if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_PV ||
          				regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV ||
          				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV){
          		commonBo.getExtraDetls(regForm,language);
          		}
          		//end of code for getting extra details
          		String dutyTxnId = bd.getDutyTxnId(regForm.getHidnRegTxnId());
				if(dutyTxnId.equals(null)|| dutyTxnId.equals(""))
					regForm.setDuty_txn_id(0);
				else
					regForm.setDuty_txn_id(Integer.parseInt(dutyTxnId));
                  
                  HashMap propMap =new HashMap();
               //   propMap=regForm.getMapPropertyTransParty();
                  logger.debug("in confirmation action----------------------->");
                  ArrayList propertyIdList=new ArrayList();
                 // if(adjuFlag==1){
                    //  propertyIdList=commonBo.getPropertyIdMarketValAdju(regForm.getAdjudicationNumber());
            //  }else{
                  propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
             // }

                                 
                  double totalMarketVal=0;
                  for(int i=0;i<propertyIdList.size();i++){
                        
                        ArrayList row_list=new ArrayList();
                        row_list=(ArrayList)propertyIdList.get(i);
                        //String propIds=row_list.toString();
                        //propIds=propIds.substring(1, propIds.length()-1);
                        //String propId[]=propIds.split(",");
                        String propId = (String)row_list.get(0);
                        String mrktVal = (String)row_list.get(1);
                        
                        mrktVal = mrktVal == null?"0":mrktVal;
                        mrktVal = mrktVal == ""?"0":mrktVal;
                        logger.debug(mrktVal);
                        //if(mrktVal != null)
                       // {
                        	totalMarketVal=totalMarketVal+Double.parseDouble(mrktVal.trim());
                       // }
                        
                        
                        
                        if(regForm.getDeedID()!=RegInitConstant.DEED_PARTITION_PV)
            			{	regForm.setMapPropertyTransParty ( new HashMap());
                        	ArrayList transPartyDets = new ArrayList();
            			regForm.setPropListPVDeed(null);
            			
            			//Updated by Rakesh for PartyPropMappingDisplay: Start
            			
            			String clr_flag=commonBo.getClrFlagByPropId(propId.trim());
            			if(clr_flag !=null && !clr_flag.isEmpty()){
            				if(clr_flag.equalsIgnoreCase("Y")){
            				transPartyDets=commonBo.getTransPartyDetsCLR(propId.trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            				}else{            					
            			
            				transPartyDets=commonBo.getTransPartyDets(propId.trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            				}
            				
            			}
                        else{
            				transPartyDets=commonBo.getTransPartyDets(propId.trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                        }
            			
            			//Updated by Rakesh for PartyPropMappingDisplay: End
            			// transPartyDets=commonBo.getTransPartyDets(propId,regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            			for(int j=0;j<transPartyDets.size();j++){
            				
            				CommonDTO dto=new CommonDTO();
            				dto=(CommonDTO)transPartyDets.get(j);
            				logger.debug("transacting party list  role------>"+dto.getRole());
            				logger.debug("transacting party list  name------>"+dto.getName());
            				logger.debug("transacting party list  id------>"+dto.getId());
            			
            			}
            			logger.debug("property id------>"+propId);
            			logger.debug("market value------>"+mrktVal);
            			propMap.put(propId+","+mrktVal, transPartyDets);
            			}
            			else{
            				regForm.setPropListPVDeed(commonBo.getPropertyListPVDeed(propertyIdList));
            			}
            	
            		}
            		
            		if(regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV ||regForm.getInstID()==2041){
            			ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),language,regForm.getDeedID(),regForm.getInstID());
            			regForm.setTransPartyListPVDeed(transPartyDets);
            			regForm.setTransPartyListNonPropDeed(transPartyDets);
            		}else{
            			regForm.setTransPartyListPVDeed(null);
            			regForm.setTransPartyListNonPropDeed(null);
            		}
            		
            		
            		regForm.setTotalMarketValue(obj.format(totalMarketVal));
            		regForm.setMapPropertyTransParty(propMap);
            		
            		//below statement added by roopam
            		regForm.setDtlsMapConsntr(commonBo.getConsenters(regForm.getHidnRegTxnId()));
            		String[] param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
                 	regForm.setConsenterWithConsidFlag(param[0]);
            		
            		
                        String dutyListArr[]=new String[9];
          		
          		if(adjuFlag==1){
          			
          			dutyListArr=boMaker.getDutyDetlsAdjuForConfirmation(regForm.getAdjudicationNumber());
          			regForm.setListDtoAdju(commonBo.getAdditonalUploadsAdju(regForm.getAdjudicationNumber()));//added by roopam-21 may 2015
				// START changes to fetch the duty details if data not found for adjudicated application by santosh
          			if(dutyListArr==null || dutyListArr.length==0){
          				dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          			}
          		 // END changes to fetch the duty details if data not found for adjudicated application by santosh
          		}
          		else{
          			dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          		}
                        
                        regForm.setStampduty1(dutyListArr[0].trim());
                        regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                        regForm.setPanchayatDuty(dutyListArr[1].trim());
                        regForm.setUpkarDuty(dutyListArr[3].trim());
                        regForm.setTotalduty(dutyListArr[5].trim());
                        regForm.setRegistrationFee(dutyListArr[4].trim());
                        
                       /* if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                			else
                				regForm.setMarketValueShares(Double.toString(0.0));	
                			
                			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                			else
                				regForm.setDutyPaid(Double.toString(0.0));
                			
                			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                			else
                				regForm.setRegPaid(Double.toString(0.0));*/
                			
                			if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
                				
                				String finalMV=commonBo.getExchangeDeedFinalMV(String.valueOf(regForm.getDuty_txn_id()));
                				
                			regForm.setExchangeDeedMrktValue(obj.format(Double.parseDouble(finalMV)));
                			}
                			commonBo.getExtraDetls(regForm,language);
                        
                        /*String[] param={regForm.getHidnRegTxnId(),dutyListArr[0].trim(),dutyListArr[1].trim(),
                                             dutyListArr[2].trim(),dutyListArr[3].trim(),dutyListArr[4].trim(),
                                             dutyListArr[5].trim(),session.getAttribute("UserId").toString()};*/
                        
                        
                     /*   boolean stampDutiesInserted=false;
                        stampDutiesInserted=commonBo.insertStampDuties(regForm,session.getAttribute("UserId").toString());
                        
                        if(stampDutiesInserted){
                        regForm.setStampduty1(dutyListArr[0].trim());
                        regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                        regForm.setPanchayatDuty(dutyListArr[1].trim());
                        regForm.setUpkarDuty(dutyListArr[3].trim());
                        regForm.setTotalduty(dutyListArr[5].trim());
                        regForm.setRegistrationFee(dutyListArr[4].trim());
                        }else{
                              regForm.setStampduty1("-");
                        regForm.setNagarPalikaDuty("-");
                        regForm.setPanchayatDuty("-");
                        regForm.setUpkarDuty("-");
                        regForm.setTotalduty("-");
                        regForm.setRegistrationFee("-");
                        }*/
              //    }
                                 regForm.setIsDutyCalculated(1);        
                                 String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
                       			
                       			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
                       				
                       				if(flags[2].trim().equalsIgnoreCase("Y"))
                       				{regForm.setCommonDeed(1);}
                       				else
                       				{regForm.setCommonDeed(0);}
                       				
                       				regForm.setPvReqFlag(flags[1].trim());
                       				regForm.setPropReqFlag(flags[0].trim());
                       				
                       				
                       			}else{
                       				regForm.setCommonDeed(0);
                       				regForm.setPvReqFlag("");
                       				regForm.setPropReqFlag("");
                       			}
                       			dutyTxnId = bd.getDutyTxnId(regForm.getHidnRegTxnId());
                 				if(dutyTxnId.equals(null)|| dutyTxnId.equals(""))
                 					regForm.setDuty_txn_id(0);
                 				else
                 					regForm.setDuty_txn_id(Integer.parseInt(dutyTxnId));
                       			regForm.setShareOfPropSize(0);
                       			ArrayList dutyList = new ArrayList();
                       			
                       			if(adjuFlag==1){
                          			
                          			dutyList=boMaker.getAdjudicatedDutyDetlsChecker(regForm.getHidnRegTxnId());
                          		}
                          		else{
                          			dutyList=boMaker.getDutyDetls(regForm.getHidnRegTxnId());
                          		}
                       			
                       			for(int i = 0;i <dutyList.size();i++)
		                		{
		                			RegCompCheckerDTO rrdto = (RegCompCheckerDTO)dutyList.get(i);
		                			regForm.setExempStamp(rrdto.getExempStamp());
		                			regForm.setExempReg(rrdto.getExempReg());
		                			regForm.setTotalduty(rrdto.getTotalduty());
		                			regForm.setRegistrationFee(rrdto.getRegistrationFee());
		                		}
                       			
                       			//added by ankit for plant and machinery
                       			if(regForm.getInstID()==RegInitConstant.INST_PLANT_MACHINERY_ONLY){
                       					commonBo.getAllUserEnterables(regForm,language);
                       			}
                       			
                       			
                       			
                       			if(regForm.getPvReqFlag().equalsIgnoreCase("N"))
                   			 {
                   				 forward="reginitConfirmNonPV";
                   			 }else{
                   				 forward="reginitConfirm";
                   			 }
                  regForm.setForwardJsp(forward);
                        return mapping.findForward(forward);
                  }
                  
                  if("CONFIRMATION_OK_ACTION_NO_VALUATION".equalsIgnoreCase(actionName))
                  {
                	 
                	  logger.debug("*********CONFIRMATION NO VALUATION***************");
                	  logger.debug("regForm deed doc"+ regForm.getRegDTO().getDeedDoc());
                	  logger.debug("regForm deed doc"+ regForm.getDeedDoc());
                	  regForm.setDtlsMapConsntr(commonBo.getConsenters(regForm.getHidnRegTxnId()));
              		String[] param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
                   	regForm.setConsenterWithConsidFlag(param[0]);
                	  String[] deedInstArray=boMaker.getDeedInstId1(regForm.getHidnRegTxnId());
                	  int adjuFlag = 0;
                  	 if(deedInstArray!=null && deedInstArray.length>0){
                      
                      request.setAttribute("deedId", deedInstArray[0].trim());
                      regForm.setCommonDeed(commonBo.getCommonDeedFlag(deedInstArray[0].trim()));
                      request.setAttribute("instId", deedInstArray[1].trim());
                      regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                      regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                      if(deedInstArray[3].trim()!=null && deedInstArray[3].trim().equalsIgnoreCase("R")){
          				adjuFlag=1;
          				regForm.setAdjudicationNumber(regForm.getHidnRegTxnId());
          				
                  	}
                  	 }
                 	regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
              		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
                  	if(regForm.getDeedDoc().equalsIgnoreCase("Y"))
               	  {
                  		 ArrayList deedDocList = boMaker.getDeedDocDetails(regForm.getHidnRegTxnId());
                  		if(deedDocList.size() == 0)
    					{
                 			regForm.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC);
                 			regForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
    					}
                 		else
                 		{
                 			for(int i = 0 ; i < deedDocList.size() ;i++)
    						{
    							ArrayList subList = (ArrayList)deedDocList.get(i);
    							String deedDocPath = (String)subList.get(1);
    							String deedDocName = (String)subList.get(0);
    							if(deedDocPath == null || deedDocPath.equals(""))
    							{
    								regForm.setDeedDocName("");
    								regForm.setDeedDocPath("");
    								regForm.setDeedStat("4");
    								regForm.setPdfFlag("N");
    								regForm.setDeedDraftId("");
    							}
    							else
    							{
    								regForm.setDeedDocName(deedDocName);
    								regForm.setDeedDocPath(deedDocPath);
    								regForm.setDeedStat("5");
    								regForm.setPdfFlag("Y");
    								regForm.setDeedDraftId("");
    							}
    								
    								
    						}
    						
                 		}
    					
          		  
            		  
                	  }
                  	else
                  	{
                  		regForm.setDeedDocName("");
						regForm.setDeedDocPath("");
						regForm.setDeedStat("4");
						regForm.setPdfFlag("N");
						regForm.setDeedDraftId("");
                  	}
                	 /* if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION || 
               				regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
               				regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
               				regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
               				regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_POA ||
               				regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
               				regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
               				regForm.getCommonDeed()==1){      //for deeds following code set2
               			
               			
               			if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
               					regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
               					regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
               				
               				if(		regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9 ||
               						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_1 ||
               						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_2 ||
               						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_1){
               					
               					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2);
               					
               				}else if(regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_10 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_5 ||
               						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_8 ||
               						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_3 ||
               						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_4 ||
               						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_2){
               					
               					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_REQ_CONSTANT_1);
               				}
               				
               				
               			}
               			
               			regForm.setDeedTypeFlag(1);
               			//regForm.setHdnDeclareShareCheck("N");
               			
               			
               		}*/
               		String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
          			
          			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
          				
          				if(flags[2].trim().equalsIgnoreCase("Y"))
          				{regForm.setCommonDeed(1);}
          				else
          				{regForm.setCommonDeed(0);}
          				
          				regForm.setPvReqFlag(flags[1].trim());
          				regForm.setPropReqFlag(flags[0].trim());
          				
          				
          			}else{
          				regForm.setCommonDeed(0);
          				regForm.setPvReqFlag("");
          				regForm.setPropReqFlag("");
          			}
          			String dutyTxnId = bd.getDutyTxnId(regForm.getHidnRegTxnId());
    				if(dutyTxnId.equals(null)|| dutyTxnId.equals(""))
    					regForm.setDuty_txn_id(0);
    				else
    					regForm.setDuty_txn_id(Integer.parseInt(dutyTxnId));
          			regForm.setShareOfPropSize(0);
              	  boMaker.landConfirmationScreen(regForm,language);
       			 logger.debug("^^^^^^^"+regForm.getDeedtype1());
       			 logger.debug("^^^^^^^"+regForm.getInstType());
       			regForm.getRegDTO().setParentPathScan(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC);
       			regForm.getRegDTO().setFileNameScan(RegCompConstant.FILE_NAME_DEED_DOC);
       			regForm.getRegDTO().setGuidUpload("");
       			regForm.getRegDTO().setDeedDoc("N");
       			 //if(regForm.getDeedID()!=0 && regForm.getInstID()!=0){
       			regForm.setCommonDeed(commonBo.getCommonDeedFlag(String.valueOf(regForm.getDeedID())));
       			String TempId=regForm.getHidnRegTxnId();
       			ArrayList estampDet = commonBo.getEstampDet(TempId);
                  regForm.setEstampDetLst(estampDet);
                  if(estampDet.size()!=0)
                  {
                        ArrayList list = new ArrayList();
                        list= (ArrayList) estampDet.get(0);
                        regForm.setEstampCode((String)list.get(8));
                        regForm.setEstampAmt((String)list.get(1));
                        String transDate1=(String)list.get(2);
                        SimpleDateFormat sdf1 = new SimpleDateFormat ("yyyy-mm-dd");
                        SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/mm/yyyy");
                        Date d1 = sdf1.parse(transDate1);
                        String transDate = sdf2.format(d1);
                        regForm.setEstampDateTime(transDate);
                  }
       			
       			 request.setAttribute("deedId", regForm.getDeedID());
       			 request.setAttribute("instId", regForm.getInstID());
       			 //}
String dutyListArr[]=new String[9];
          		
          		if(adjuFlag==1){
          			
          			dutyListArr=boMaker.getDutyDetlsAdjuForConfirmation(regForm.getAdjudicationNumber());
			// START changes to fetch the duty details if data not found for will and others adjudicated application by santosh
          			if(dutyListArr==null || dutyListArr.length==0){
          				dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          			}
          		   // END changes to fetch the duty details if data not found for will and others adjudicated application by santosh
          		}
          		else{
          			dutyListArr=boMaker.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
          		}
                        
                        regForm.setStampduty1(dutyListArr[0].trim());
                        regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                        regForm.setPanchayatDuty(dutyListArr[1].trim());
                        regForm.setUpkarDuty(dutyListArr[3].trim());
                        regForm.setTotalduty(dutyListArr[5].trim());
                        regForm.setRegistrationFee(dutyListArr[4].trim());
                        ArrayList dutyList = new ArrayList();
                        if(adjuFlag==1){
                  			
                  			dutyList=boMaker.getAdjudicatedDutyDetlsChecker(regForm.getHidnRegTxnId());
                  		}
                  		else{
                  			dutyList=boMaker.getDutyDetls(regForm.getHidnRegTxnId());
                  		}
               			
               			for(int i = 0;i <dutyList.size();i++)
                		{
                			RegCompCheckerDTO rrdto = (RegCompCheckerDTO)dutyList.get(i);
                			regForm.setExempStamp(rrdto.getExempStamp());
                			regForm.setExempReg(rrdto.getExempReg());
                			regForm.setTotalduty(rrdto.getTotalduty());
                			regForm.setRegistrationFee(rrdto.getRegistrationFee());
                		}
       			 if(regForm.getPvReqFlag().equalsIgnoreCase("N"))
    			 {
    				 forward="reginitConfirmNonPV";
    			 }else{
    				 forward="reginitConfirm";
    			 }
       			regForm.setForwardJsp(forward);
       			 /*if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
      					 regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_POA ||
      					 regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
      					 regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
      					 (regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
      					 (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
      							 //&& 
        							 //(regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )
      							 )||
              			 regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
       					(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
       							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
               			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
               					&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                       	(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
                       			&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
      					 regForm.getDeedTypeFlag()==1|| regForm.getCommonDeed()==1){
      				 forward="reginitConfirmNonPV";
      			 }else{
      				 forward="reginitConfirm";
      			 }*/
      			 
        			 


         			 
         			return mapping.findForward(forward);
                  }
            }
            }
            

            //End==== added By ankita
            if(request.getParameter("label") != null)
            {
            	
            	if(request.getParameter("label").equalsIgnoreCase("displayExtra")){
            		
            		commonBo.getExtraDetls(regForm,language);
            		request.setAttribute("deedId",regForm.getDeedID());
            		request.setAttribute("instId",regForm.getInstID());
            		request.setAttribute("commonFlag",regForm.getCommonDeed());
            		
            		                		
                    logger.debug("request deed ---->"+request.getAttribute("deedId"));
                    regForm.setCallingAction("regInit.do");
                    //request.setAttribute("reginit", regForm);	
            		forward="displayExtraDetls";
            		return mapping.findForward(forward);
            	}
            	
            	
            }
            
			
			
			ArrayList mainList;
			//following code for clearing form beans when the module is hit first time.
			if(request.getParameter("modName")!=null){
				if(request.getParameter("modName").equalsIgnoreCase("RegistrationProperty")&& request.getAttribute("regFormDashboard")==null){
					if(regForm!=null)
					{
						
						RegCommonDTO commonDto =new RegCommonDTO();
					    commonDto.setInstrument(new ArrayList());
						commonDto.setExemption(new ArrayList());
						
						regForm.setFname("");
						regForm.setMname("");
						regForm.setLname("");
						regForm.setGendar("M");
						regForm.setAge("");
						regForm.setFatherName("");
						regForm.setMotherName("");
						regForm.setSpouseName("");
						regForm.setNationality("");
						regForm.setIndaddress("");
						regForm.setIndcountry("");
						regForm.setIndstatename("");
						regForm.setInddistrict("");
						regForm.setPostalCode("");
						regForm.setIndphno("");
						regForm.setIndmobno("");
						regForm.setEmailID("");
						regForm.setListID("");
						regForm.setIdno("");
						regForm.setDeedType("");				
						
						regForm.setInstrument("");
						regForm.setIndCaste("");
						regForm.setIndReligion("");
						regForm.setIndDisability("");
						
			             regForm.setOgrName("");
			             regForm.setAuthPerName("");
			             regForm.setOrgaddress("");
			             regForm.setCountry("");
			             regForm.setStatename("");
			             regForm.setDistrict("");
			             regForm.setPhno("");
			             regForm.setMobno("");
			             regForm.setDeedType("");				
						 regForm.setPurpose("");	
													
							regForm.setFnameTrns("");
							regForm.setMnameTrns("");
							regForm.setLnameTrns("");
							regForm.setGendarTrns("M");
							regForm.setAgeTrns("");
							regForm.setFatherNameTrns("");
							regForm.setMotherNameTrns("");
							regForm.setSpouseNameTrns("");
							regForm.setNationalityTrns("");
							regForm.setIndaddressTrns("");
							regForm.setIndcountryTrns("");
							regForm.setIndcountryNameTrns("");
							regForm.setIndstatenameTrns("");
							regForm.setIndstatenameNameTrns("");
							regForm.setInddistrictTrns("");
							regForm.setInddistrictNameTrns("");
							regForm.setPostalCodeTrns("");
							regForm.setIndphnoTrns("");
							regForm.setIndmobnoTrns("");
							regForm.setEmailIDTrns("");
							regForm.setListIDTrns("");
							regForm.setListIDNameTrns("");
							regForm.setIdnoTrns("");
								
							regForm.setPurposeTrns("");
							regForm.setDeleteTrnsPrtyID("");
							regForm.setIndCasteTrns("");
							regForm.setIndReligionTrns("");
							regForm.setIndDisabilityTrns("");
							regForm.setShareOfPropTrns("");
							regForm.setOwnershipShareTrns("");
							regForm.setRelationWithOwnerTrns("");
						
				             regForm.setOgrNameTrns("");
				             regForm.setAuthPerNameTrns("");
				             regForm.setOrgaddressTrns("");
				             regForm.setCountryTrns("");
				             regForm.setCountry("");
				             regForm.setCountryNameTrns("");
				             regForm.setStatenameTrns("");
				             regForm.setStatenameNameTrns("");
				             regForm.setDistrictTrns("");
				             regForm.setDistrictNameTrns("");
				             regForm.setPhnoTrns("");
				             regForm.setMobnoTrns("");
				             regForm.setDeleteTrnsPrtyID("");
				            
						    
					    regForm.setPartyType(null);
					    regForm.setPartyTypeTrns(null);
					    regForm.setListAdoptedParty(null);
					    regForm.setListAdoptedPartyTrns(null);
					    regForm.setOwnerList(new ArrayList());
					    regForm.setPoaList(new ArrayList());
					    regForm.setSelectedPoa(null);
					    regForm.setSelectedPoaName(null);
					    //regForm.setParty1OwnerCount(0);
					    //regForm.setParty1PoaHolderCount(0);
					    //regForm.setParty2OwnerCount(0);
					    //regForm.setParty2PoaHolderCount(0);
					    //regForm.setDoneeCount(0);
					    //regForm.setDonorCount(0);
					    //regForm.setBuyerCount(0);
					    //regForm.setSellerPoaCount(0);
					    //regForm.setSellerSelfCount(0);
					    //regForm.setOwnerCount(0);
					    //regForm.setPoaAccepterCount(0);
					    //regForm.setPoaHolderCount(0);
					    regForm.setOwnerId("");
					    regForm.setHdnDeleteTrnsPrtyId("");
					    regForm.setHdnOwnerId("");
					    //regForm.setHidnRegTxnId("");
					    regForm.setHidnUserId("");
					    regForm.setPropertyDTO(new PropertyValuationDTO());
					    regForm.setRegcompletDto(new RegCompletDTO());
					    regForm.setMapTransactingParties(new HashMap());
					    regForm.setMapTransactingPartiesDisp(new HashMap());
					    regForm.setRegInitEstampCode(null);
					    regForm.setRegInitPaymntTxnId(null);
					    regForm.setRegInitPermTxnId("");
					    regForm.setCurrDateTime("");
					    regForm.setPropertyId("");
					    regForm.setTotalPoaCount(0);
					    regForm.setTotalOwnerCount(0);
					    
				
					regForm.setActionName(" ");
					regForm.setFormName(" ");
					regForm.setPage("success");
				
					regForm.setListAdoptedPartyNameTrns("");
					regForm.setListAdoptedPartyName("");
					regForm.setAddMoreCounter(0);
					regForm.setTotalShareOfProp(0);
					regForm.setTotalShareOfPropBuyer(0);
					regForm.setTotalShareOfPropSellerSelf(0);
					regForm.setTotalShareOfPropDonor(0);
					regForm.setTotalShareOfPropDonee(0);
					regForm.setTotalShareOfPropOwnerSelf(0);
					
					
					 regForm.setOwnerNameTrns("");
	            	 regForm.setOwnerOgrNameTrns("");
	                 regForm.setOwnerGendarTrns("M");
	            	 regForm.setOwnerNationalityTrns("");
	            	 regForm.setOwnerAddressTrns("");
	            	 regForm.setOwnerPhnoTrns("");
	            	 regForm.setOwnerEmailIDTrns("");
	                 regForm.setOwnerIdnoTrns("");
	            	 regForm.setOwnerAgeTrns("");
	            	 regForm.setOwnerListIDTrns("");
	            	 regForm.setOwnerProofNameTrns("");
	            	 regForm.setIsTransactingPartyAddedFlag(0);
	            	 regForm.setIsDashboardFlag(0);
	            	 //commonDto.setIdProf(commonBo.getIdProf());
	            	 regForm.setOwnerName("");
	         		regForm.setOwnerOgrName("");
	         		regForm.setOwnerGendar("M");
	         		regForm.setOwnerNationality("");
	         		regForm.setOwnerAddress("");
	         		regForm.setOwnerPhno("");
	         		regForm.setOwnerEmailID("");
	         		regForm.setOwnerListID("");
	         		regForm.setOwnerIdno("");
	         		regForm.setOwnerAge("");
	         		regForm.setOwnerProofName("");
	         		
	         		/*regForm.setOwnerNameTrns("");
	         		regForm.setOwnerOgrNameTrns("");
	         		regForm.setOwnerGendarTrns("M");
	         		regForm.setOwnerNationalityTrns("");
	         		regForm.setOwnerAddressTrns("");
	         		regForm.setOwnerPhnoTrns("");
	         		regForm.setOwnerEmailIDTrns("");
	         		regForm.setOwnerListIDTrns("");
	         		regForm.setOwnerIdnoTrns("");
	         		regForm.setOwnerAgeTrns("");
	         		regForm.setOwnerProofNameTrns("");
	         		regForm.setIsDashboardFlag(0);*/
	         		//following fields for owner of poa
	        		/*regForm.setOwnerName("");
	        		regForm.setOwnerOgrName("");
	        		regForm.setOwnerGendar("M");
	        		regForm.setOwnerNationality("");
	        		regForm.setOwnerAddress("");
	        		regForm.setOwnerPhno("");
	        		regForm.setOwnerEmailID("");
	        		regForm.setOwnerListID("");
	        		regForm.setOwnerIdno("");
	        		regForm.setOwnerAge("");
	        		regForm.setOwnerProofName("");*/
	        		
	        		/*regForm.setOwnerNameTrns("");
	        		regForm.setOwnerOgrNameTrns("");
	        		regForm.setOwnerGendarTrns("M");
	        		regForm.setOwnerNationalityTrns("");
	        		regForm.setOwnerAddressTrns("");
	        		regForm.setOwnerPhnoTrns("");
	        		regForm.setOwnerEmailIDTrns("");
	        		regForm.setOwnerListIDTrns("");
	        		regForm.setOwnerIdnoTrns("");
	        		regForm.setOwnerAgeTrns("");
	        		regForm.setOwnerProofNameTrns("");*/
	        		
	        		/*private String ownerNameTrns;
	        		private String ownerOgrNameTrns;
	        		private String ownerGendarTrns="M";
	        		private String ownerNationalityTrns;
	        		private String ownerAddressTrns;
	        		private String ownerPhnoTrns;
	        		private String ownerEmailIDTrns;
	        		private String ownerListIDTrns;
	        		private String ownerIdnoTrns;
	        		private String ownerAgeTrns;
	        		private String ownerProofNameTrns;
	        		*/
	        		//private String poaOwnerId;
	        		//private String hdnExAmount;
	        		regForm.setIsDutyCalculated(0);
	        		regForm.setMarketValueShares("");
	        		//private String dutyPaid;
	        		
	        		regForm.setLabelAmountFlag("");
	        		regForm.setAdjudicationNumber("");
	        		//private double regPaid=0.0;
	        		
	        		regForm.setErrorMsg("");
	        		
	        		regForm.setMapPropertyTransParty(new HashMap());
					
					if(regForm.getIsMultiplePropsFlag()==0){
						
						regForm.setHidnRegTxnId("");
						regForm.setParty1OwnerCount(0);
					    regForm.setParty1PoaHolderCount(0);
					    regForm.setParty2OwnerCount(0);
					    regForm.setParty2PoaHolderCount(0);
					    regForm.setDoneeCount(0);
					    regForm.setDonorCount(0);
					    regForm.setBuyerCount(0);
					    regForm.setSellerPoaCount(0);
					    regForm.setSellerSelfCount(0);
					    regForm.setOwnerCount(0);
					    regForm.setPoaAccepterCount(0);
					    regForm.setPoaHolderCount(0);
					}
					
					
					
					}
					
					session.removeAttribute("commonDto");
					session.removeAttribute("roleId");
					session.removeAttribute("functionId");
					
					session.removeAttribute("status");
					session.removeAttribute("view");
					session.removeAttribute("regFormProp");
					//following attributes for payment
					/*session.removeAttribute("parentModName");
					session.removeAttribute("parentFunName");
					session.removeAttribute("parentFunId");
					session.removeAttribute("parentAmount");*/
					
		
				}
				
				
		
				
			}
			//end of code for clearing form beans
			
			RegCommonDTO commonDto;
			//ArrayList otherFee;
			//String roleId = (String)session.getAttribute("roleId");
			//String funId = (String)session.getAttribute("functionId");
			String userId = (String)session.getAttribute("UserId");			
			//session.setAttribute("roleId","ROLE_002");
			//session.setAttribute("roleName","ROLE_002");
			
			
			
			//Start:======== Added by Ankita
			PropertyValuationForm pform = null; 
			if(session.getAttribute("eform")!=null)
			{
			pform = (PropertyValuationForm)session.getAttribute("eform");
			
			if(regForm.getIsMultiplePropsFlag()==0){
			regForm.setDeedtype1(pform.getDutyDTO().getDeedType());
			regForm.setDeedID(pform.getDutyDTO().getDeedId());
			regForm.setInstType(pform.getInstrumentDTO().getInstType());
			regForm.setInstID(pform.getInstrumentDTO().getInstId());
			regForm.setExmpID(pform.getExemptionDTO().getHdnExAmount()); //comma separated exemption ids.
			regForm.setSelectedExemptionList(commonBo.getExemptionList(pform.getExemptionDTO().getHdnExAmount().replace(",", "-"),language));
			}
			regForm.setValuationId(pform.getPropertyDTO().getValuationId());
			regForm.setMarketValue(pform.getPropertyDTO().getMarketValueDisplay());
			
			
			if(pform.getInstrumentDTO().getLabelAmountFlag()!=null && pform.getInstrumentDTO().getLabelAmountFlag()!="")
			{
			session.setAttribute("labelAmountFlag", pform.getInstrumentDTO().getLabelAmountFlag());
			}
			if(session.getAttribute("labelAmountFlag")!=null){
				regForm.setLabelAmountFlag(session.getAttribute("labelAmountFlag").toString());
			}
			
			
			//following code for property id generation added by roopam
			
				String distId=Integer.toString(pform.getPropertyDTO().getDistrictID());
				if(distId.length()==1)
					distId="0"+distId;
				
				String tehslId=Integer.toString(pform.getPropertyDTO().getTehsilID());
				if(tehslId.length()==1)
					tehslId="0"+tehslId;
				
				if(regForm.getPropertyId()=="" || regForm.getPropertyId()==null)
				{
				String propertyId=distId+tehslId+commonBo.getPropertyIdSequence();
				logger.debug("property id------------>"+propertyId);
				if(propertyId.length()==15)
				regForm.setPropertyId("0"+propertyId);
				else
				regForm.setPropertyId(propertyId);
				}
				//end of code for property id generation by roopam
			}
			//End :====== Added by Ankita
			
			/*if(session.getAttribute("commonDto")!= null ){
				commonDto = (RegCommonDTO)session.getAttribute("commonDto");
				
			}else{				
				commonDto = new RegCommonDTO();
				commonDto.setState(new ArrayList());
				commonDto.setDistrict(new ArrayList());
				commonDto.setIndstate(new ArrayList());
				commonDto.setInddistrict(new ArrayList());
			}*/
			
			if(regForm.getCommonDto()!= null ){
				commonDto = regForm.getCommonDto();
				
			}else{				
				commonDto = new RegCommonDTO();
				commonDto.setState(new ArrayList());
				commonDto.setDistrict(new ArrayList());
				commonDto.setIndstate(new ArrayList());
				commonDto.setInddistrict(new ArrayList());
			}
			
			forward = regForm.getPage();
			
	
			if(request.getAttribute("regFormDashboard")==null){  //this line was added for dashboard
				int deed=0;
				deed=regForm.getDeedID();
			request.setAttribute("deedId", deed);
			commonDto.setDeedId(deed);
			if(regForm.getIsDashboardFlag()==0)
			commonDto.setPartyType(commonBo.getPartyType(deed,regForm.getInstID(),language));
		//	commonDto.setAppType(commonBo.getAppType(language));
			commonDto.setCountry(commonBo.getCountry(language));
			commonDto.setIndcountry(commonBo.getCountry(language));
			commonDto.setIdProf(commonBo.getIdProf(language));
			commonDto.setDeedType(commonBo.getDeedType(language));
			
			//following code for getting state and district of applicant
			//for getting organization state list
			if(regForm.getCountry()!=null && !regForm.getCountry().equalsIgnoreCase("")) {
			    commonDto.setState(commonBo.getState(regForm.getCountry(),language));
			    forward="success";
			}else{
			    commonDto.setState(new ArrayList());
			}
			//for getting organization district list
			if(regForm.getStatename()!=null && !regForm.getStatename().equalsIgnoreCase("")){
			    commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(),language));
			    forward="success";
			}else{
			    commonDto.setDistrict(new ArrayList());
			}
			//for getting individual state list
			if(regForm.getIndcountry()!=null && !regForm.getIndcountry().equalsIgnoreCase("")) {
			    commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(),language));
			    forward="success";
			}else{
			    commonDto.setIndstate(new ArrayList());
			}
			//for getting individual district list
			if(regForm.getIndstatename()!=null && !regForm.getIndstatename().equalsIgnoreCase("")){
			    commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(),language));
			    forward="success";
			}else{
			    commonDto.setInddistrict(new ArrayList());
			}
			}
			if(regForm.getIsMultiplePropsFlag()==1){
				
				String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
        		
        		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
    			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
    			if(deedInstArray[2].trim().equals("-")){
        			regForm.setExmpID("");
        			regForm.setHdnExAmount("");
        			}else{
        				regForm.setExmpID(deedInstArray[2].trim());
            			regForm.setHdnExAmount(deedInstArray[2].trim());
        			}
				
				
			}
			
			
			
			forward="success";
			
			
			String formName = regForm.getFormName();
			String actionName = regForm.getActionName();
			logger.debug("formName:-"+formName);
			logger.debug("actionName:-"+actionName);
			
			 
			if(RegInitConstant.PARTY_PAGE_FORM.equals(formName)) {
				if(RegInitConstant.CHANGE_PARTY_ACTION.equals(actionName)){
					String partyType = regForm.getListAdoptedParty();
					logger.debug("partyType:-"+partyType);
					
					//saveToken(request);
				}
				if(RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)){
					String partyTypeTrns = regForm.getListAdoptedPartyTrns();
					logger.debug("partyType:-"+partyTypeTrns);
					
					forward="transactingParty";
					//saveToken(request);
				}
				if(RegInitConstant.NEXT_ACTION.equals(actionName)) {
					 //if(isTokenValid(request)) {
						
						
						//following code for populating drop downs
					//	commonDto.setAppTypeTrns(commonBo.getAppType(language));
						commonDto.setCountryTrns(commonBo.getCountry(language));
						commonDto.setIndcountryTrns(commonBo.getCountry(language));
						commonDto.setIdProfTrns(commonBo.getIdProf(language));
						
						//BELOW CODE FOR INSERTING APPLICANT DETAILS IN DATABASE.
							
						regForm.setHidnUserId(userId);
						if(regForm.getIsMultiplePropsFlag()==0){
						  Calendar currentDate = Calendar.getInstance();
						  SimpleDateFormat formatter= new SimpleDateFormat("ddMMyy");
						  String dateNow = formatter.format(currentDate.getTime());
						  String  regTxnIdSeq= commonBo.getRegInitTxnIdSeq();
						  String regTxnId=null;
						  regTxnId=dateNow+regTxnIdSeq;
						  
						  regForm.setHidnRegTxnId(regTxnId);
						  
						  
						} 
						regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
						  
						
						  boolean applicantDetailsInserted;
						
							  applicantDetailsInserted = commonBo.insertApplicantAndPropertyDetails(regForm);
						  
						if(applicantDetailsInserted){
							
			                 
			                 RegCommonDTO mapDto =new RegCommonDTO();
			                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty());
			                 mapDto.setListAdoptedPartyNameTrns(regForm.getListAdoptedPartyName());
			                 mapDto.setPurposeTrns(regForm.getPurpose());
			                 mapDto.setBname("");
			                 mapDto.setBaddress("");
			                 mapDto.setApplicantOrTransParty("Applicant");
			                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
			                 mapDto.setUserID(regForm.getHidnUserId());
			                 if(regForm.getOwnershipShare().equalsIgnoreCase(""))
			                   		mapDto.setOwnershipShareTrns("-");
			                   	 else
			                	    mapDto.setOwnershipShareTrns(regForm.getOwnershipShare());
			                   	 if(regForm.getRelationWithOwner().equalsIgnoreCase(""))
			                   		mapDto.setRelationWithOwnerTrns("-");
			                   	 else
			                	    mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner());
			                   	 /*if(regForm.getShareOfProp().equalsIgnoreCase("")){
			                   		mapDto.setShareOfPropTrns("-");
			                   	 }*/
			                   	/* else
			                	    mapDto.setShareOfPropTrns(regForm.getShareOfProp());*/
			                   	 
			                   	mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyType(),language,regForm.getDeedID(),regForm.getInstID()));
			                 
			                mapDto.setPartyTypeFlag("A");
			                   	
			                   	if(regForm.getListAdoptedParty().equalsIgnoreCase("01")){
			                	 //organization
			                	 mapDto.setOgrNameTrns(regForm.getOgrName());
			                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerName());
			                	 mapDto.setIndAuthPersn(regForm.getAuthPerName());
			                	 mapDto.setOrgaddressTrns(regForm.getOrgaddress());
			                	 mapDto.setSelectedCountry(regForm.getCountry());
			                	 mapDto.setSelectedCountryName(regForm.getCountryName());
			                	 mapDto.setSelectedState(regForm.getStatename());
			                	 mapDto.setSelectedStateName(regForm.getStatenameName());
			                	 mapDto.setSelectedDistrict(regForm.getDistrict());
			                	 mapDto.setSelectedDistrictName(regForm.getDistrictName());
			                	 if(regForm.getMobno().equalsIgnoreCase(""))
			                		 mapDto.setMobnoTrns("-"); 
			                	 else
			                	     mapDto.setMobnoTrns(regForm.getMobno());
			                	 if(regForm.getPhno().equalsIgnoreCase(""))
			                		 mapDto.setPhnoTrns("-");
			                	 else
			                	     mapDto.setPhnoTrns(regForm.getPhno());
			                	
			                	 
			                	 mapDto.setFnameTrns("");
			               		 mapDto.setMnameTrns("");
			                	 mapDto.setLnameTrns("");
			                	 mapDto.setGendarTrns("-");
			                	 mapDto.setSelectedGender("");
			                	 mapDto.setAgeTrns("-");
			                   	 mapDto.setFatherNameTrns("-");
			                	 mapDto.setMotherNameTrns("");
			               		 mapDto.setSpouseNameTrns("");
			                  	 mapDto.setNationalityTrns("");
			                	 mapDto.setPostalCodeTrns("");
			               		 mapDto.setEmailIDTrns("");
			                	 mapDto.setSelectedPhotoId("");
			                	 mapDto.setSelectedPhotoIdName("-");
			                	 mapDto.setIdnoTrns("-");
			                
			                   	 mapDto.setPartyTypeTrns(regForm.getPartyType());
			                	 mapDto.setIndReligionTrns("");
			                	 mapDto.setIndCasteTrns("");
			                	 mapDto.setIndDisabilityTrns("");
			                	
			                	 mapDto.setIndividualOrOrganization("01");
			                	 
			                 }
			                 if(regForm.getListAdoptedParty().equalsIgnoreCase("02")){
			                	 //individual
			                	 mapDto.setFnameTrns(regForm.getFname());
			                	 if(regForm.getMname().equalsIgnoreCase(""))
			                		 mapDto.setMnameTrns("-");
			                	 else
			                	     mapDto.setMnameTrns(regForm.getMname());
			                	 mapDto.setLnameTrns(regForm.getLname());
			                	 mapDto.setGendarTrns(regForm.getGendar());
			                	 if(regForm.getGendar().equalsIgnoreCase("m"))
			                		 mapDto.setSelectedGender("Male");
			                	 else
			                		 mapDto.setSelectedGender("Female");
			                	 if(regForm.getAge().equalsIgnoreCase(""))
			                		 mapDto.setAgeTrns("-");
			                	 else
			                	     mapDto.setAgeTrns(regForm.getAge());
			                	 mapDto.setFatherNameTrns(regForm.getFatherName());
			                	 if(regForm.getMotherName().equalsIgnoreCase(""))
			                		 mapDto.setMotherNameTrns("-");
			                	 else
			                	     mapDto.setMotherNameTrns(regForm.getMotherName());
			                	 if(regForm.getSpouseName().equalsIgnoreCase(""))
			                		 mapDto.setSpouseNameTrns("-");
			                	 else
			                	     mapDto.setSpouseNameTrns(regForm.getSpouseName());
			                	 mapDto.setNationalityTrns(regForm.getNationality());
			                	 
			                	 if(regForm.getPostalCode().equalsIgnoreCase(""))
			                		 mapDto.setPostalCodeTrns("-");
			                	 else
			                	     mapDto.setPostalCodeTrns(regForm.getPostalCode());
			                	 
			                	 if(regForm.getIndphno().equalsIgnoreCase(""))
			                		 mapDto.setPhnoTrns("-");
			                	 else
			                	     mapDto.setPhnoTrns(regForm.getIndphno());
			                	 
			                	 if(regForm.getIndmobno().equalsIgnoreCase(""))
			                		mapDto.setMobnoTrns("-");
			                	 else
			                        mapDto.setMobnoTrns(regForm.getIndmobno());
			                	 
			                	 if(regForm.getEmailID().equalsIgnoreCase(""))
			                		 mapDto.setEmailIDTrns("-");
			                	 else
			                	     mapDto.setEmailIDTrns(regForm.getEmailID());
			                	 mapDto.setSelectedPhotoId(regForm.getListID());
			                	 
			                	 mapDto.setSelectedPhotoIdName(regForm.getListIDName());               
			                	 if(regForm.getIdno().equalsIgnoreCase(""))
			                		 mapDto.setIdnoTrns("-");
			                	 else
			                	     mapDto.setIdnoTrns(regForm.getIdno());
			                	 
			                	
			                	 mapDto.setOgrNameTrns("-");
			                	 mapDto.setAuthPerNameTrns("-");
			                	 mapDto.setIndAuthPersn(regForm.getFname()+" "+regForm.getLname());
			                	 mapDto.setOrgaddressTrns(regForm.getIndaddress());
			                	 mapDto.setSelectedCountry(regForm.getIndcountry());
			                	 mapDto.setSelectedCountryName(regForm.getIndcountryName());
			                	 mapDto.setSelectedState(regForm.getIndstatename());
			                	 mapDto.setSelectedStateName(regForm.getIndstatenameName());
			                	 mapDto.setSelectedDistrict(regForm.getInddistrict());
			                	 mapDto.setSelectedDistrictName(regForm.getInddistrictName());
			                	
			                	               	 
			                   	 mapDto.setPartyTypeTrns(regForm.getPartyType());
			                   	 if(regForm.getIndReligion().equalsIgnoreCase(""))
			                   		mapDto.setIndReligionTrns("-");
			                   	 else
			                	    mapDto.setIndReligionTrns(regForm.getIndReligion());
			                   	 if(regForm.getIndCaste().equalsIgnoreCase(""))
			                   		mapDto.setIndCasteTrns("-");
			                   	 else
			                	    mapDto.setIndCasteTrns(regForm.getIndCaste());
			                   	 if(regForm.getIndDisability().equalsIgnoreCase(""))
			                   		mapDto.setIndDisabilityTrns("-");
			                   	 else
			                	    mapDto.setIndDisabilityTrns(regForm.getIndDisability());
			                   	 
			                   	 
			                   	mapDto.setIndividualOrOrganization("02");
			                 }
			                 
			                 int roleId=Integer.parseInt(regForm.getPartyType());
			                 if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
			                		 roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
			                		 roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER ||
			                		 roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER)
			                 {
			                	 
			                	 mapDto.setOwnerNameTrns(regForm.getOwnerName());
			                	 if(regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
				                   		mapDto.setOwnerOgrNameTrns("-");
				                   	 else
				                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());
			                	
			                	 if(regForm.getOwnerGendar().equalsIgnoreCase("f"))
			                	 mapDto.setOwnerGendarTrns("Female");
			                	 else
			                     mapDto.setOwnerGendarTrns("Male");	 
			                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
			                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
			                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
			                	 if(regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
				                   		mapDto.setOwnerEmailIDTrns("-");
				                   	 else
				                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());
			                	
			                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno());
			                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAge());
			                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
			                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofName());
			                 }
			                
			                        
			                 map = regForm.getMapTransactingParties();
			                 int count=regForm.getMapKeyCount();
			                 if(count==0)
			                	 count=1;
			                 //else
			                //	 count++;
			                 
			                 if(map.containsKey(Integer.toString(count))){
				                  //keyCount=keyCount+1;
				                //	   key=key+keyCount;
			                	 count++;
			                	 map.put(Integer.toString(count), mapDto);
			                	 
				                 }
			                 else
			                	 map.put(Integer.toString(count), mapDto);
				                  
			                 
			                 regForm.setMapKeyCount(count);
			                 
			                 regForm.setAddMoreCounter(count);
			                 
			                key="key";
			                         
			                 regForm.setMapBuildingDetails(map);
			                 
			               //below code for inserting PoAs and Owners in lists.
			                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_SELLER_POA)||
			                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_POA_HOLDER)||
			                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_POA_HOLDER)||
			                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_POA_HOLDER))
			                 {
			                	 ArrayList list = regForm.getPoaList();
			                	 ArrayList row_list = new ArrayList();
			                	 row_list.add(mapDto.getPartyRoleTypeId());
			                	 row_list.add(mapDto.getIndAuthPersn());
			                	 list.add(row_list);
			                	 regForm.setPoaList(list);
			                	 regForm.setSelectedPoa(mapDto.getPartyRoleTypeId());
			     				 regForm.setSelectedPoaName(mapDto.getIndAuthPersn());
			                	 
			                 }
			                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_OWNER)||
			                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_OWNER)||
			                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_OWNER))
			                 {
			                	 ArrayList list = regForm.getOwnerList();
			                	 ArrayList row_list = new ArrayList();
			                	 row_list.add(mapDto.getPartyRoleTypeId());
			                	 row_list.add(mapDto.getIndAuthPersn());
			                	 list.add(row_list);
			                	 regForm.setOwnerList(list);
			                	 
			                 }
			               //above code for inserting PoAs and Owners in lists.
			                 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
	    						int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
	    						logger.debug("applicant role id---------->"+applicantRoleId);
	    						logger.debug("total share---------->"+totalShare);
	    						regForm.setApplicantRoleId2(Integer.parseInt(applicantRoleId));
	    						regForm.setTotalShareOfProp(totalShare);
	    						//following if condition for disabling applicant role once total sum of share is 100%.
	    						if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
	    							
	    							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_POA_ACCEPTER){
	    								regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
	    								
	    							}else if(totalShare==100){
		    							regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
		    						}
	    							
	    						}else
	    						if(totalShare==100){
	    							regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
	    						}
			               
			                 forward="transactingParty";
			                 
						}
						else{
							forward="success";
							regForm.setHidnRegTxnId(null);
						}
						actionName=RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
						regForm.setDeedId(request.getAttribute("deedId").toString());
						
						//resetToken(request);
						
					 //}
				}
				
				//below code for getting state and district for transacting party
				//for getting organization state list
				if(regForm.getCountryTrns()!=null && !regForm.getCountryTrns().equalsIgnoreCase("")) {
				    commonDto.setStateTrns(commonBo.getState(regForm.getCountryTrns(),language));
				    forward="transactingParty";
				}else{
				    commonDto.setStateTrns(new ArrayList());
				}
				//for getting organization  district list
				if(regForm.getStatenameTrns()!=null && !regForm.getStatenameTrns().equalsIgnoreCase("")){
				    commonDto.setDistrictTrns(commonBo.getDistrict(regForm.getStatenameTrns(),language));
				    forward="transactingParty";
				}else{
				    commonDto.setDistrictTrns(new ArrayList());
				}
				//for getting individual state list
				if(regForm.getIndcountryTrns()!=null && !regForm.getIndcountryTrns().equalsIgnoreCase("")) {
				    commonDto.setIndstateTrns(commonBo.getState(regForm.getIndcountryTrns(),language));
				    forward="transactingParty";
				}else{
				    commonDto.setIndstateTrns(new ArrayList());
				}
				//for getting individual district list
				if(regForm.getIndstatenameTrns()!=null && !regForm.getIndstatenameTrns().equalsIgnoreCase("")){
				    commonDto.setInddistrictTrns(commonBo.getDistrict(regForm.getIndstatenameTrns(),language));
				    forward="transactingParty";
				}else{
				    commonDto.setInddistrictTrns(new ArrayList());
				}
				//end of code for populating drop downs
				
				
				
				if(RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {
					
					//below code is for the insertion of last transacting party in map
					
					/*count = count + 1;
	                  if(count==1)
	                	 keyCount=1;*/
	               
	                 RegCommonDTO mapDto =new RegCommonDTO();
	                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
	                 mapDto.setListAdoptedPartyNameTrns(regForm.getListAdoptedPartyNameTrns());
	                 mapDto.setPurposeTrns("");
	                 mapDto.setBname("");                                                //might be changed
	                 mapDto.setBaddress("");                                             //might be changed
	                 mapDto.setApplicantOrTransParty("Transacting");
	                 mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
	                 regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
	                 //String roleName=getRoleNameId(regForm, RegInitConstant.NEXT_TO_MAPPING_ACTION, mapDto.getPartyTypeTrns());
	                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
	                 mapDto.setUserID(regForm.getHidnUserId());
	                 mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(),language,regForm.getDeedID(),regForm.getInstID()));
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("01")){
	                	 //organization
	                	 mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
	                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
	                	 mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getCountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getStatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getDistrictTrns());
	                	 mapDto.setSelectedDistrictName(regForm.getDistrictNameTrns());
	                	 if(regForm.getMobnoTrns()=="")
	                		 mapDto.setMobnoTrns("-"); 
	                	 else
	                	     mapDto.setMobnoTrns(regForm.getMobnoTrns());
	                	 if(regForm.getPhnoTrns()=="")
	                		 mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getPhnoTrns());
	                	 mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
	                	 mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
	                	 
	                	 mapDto.setFnameTrns("");
	               		 mapDto.setMnameTrns("");
	                	 mapDto.setLnameTrns("");
	                	 mapDto.setGendarTrns("-");
	                	 mapDto.setSelectedGender("");
	                	 mapDto.setAgeTrns("");
	                   	 mapDto.setFatherNameTrns("-");
	                	 mapDto.setMotherNameTrns("");
	               		 mapDto.setSpouseNameTrns("");
	                  	 mapDto.setNationalityTrns("");
	                	 mapDto.setPostalCodeTrns("");
	               		 mapDto.setEmailIDTrns("");
	                	 mapDto.setSelectedPhotoId("");
	                	 mapDto.setSelectedPhotoIdName("");
	                	 mapDto.setIdnoTrns("-");
	                	 /*mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns()));*/
	                	 mapDto.setIndReligionTrns("");
	                	 mapDto.setIndCasteTrns("");
	                	 mapDto.setIndDisabilityTrns("");
	                	 mapDto.setOwnershipShareTrns("");
	                	 mapDto.setRelationWithOwnerTrns("");
	                	 mapDto.setShareOfPropTrns("");
	                	 mapDto.setIndividualOrOrganization("01");
	                	
	                 }
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("02")){
	                	 //individual
	                	 mapDto.setFnameTrns(regForm.getFnameTrns());
	                	 if(regForm.getMnameTrns()=="")
	                		 mapDto.setMnameTrns("-");
	                	 else
	                	     mapDto.setMnameTrns(regForm.getMnameTrns());
	                	 mapDto.setLnameTrns(regForm.getLnameTrns());
	                	 mapDto.setGendarTrns(regForm.getGendarTrns());
	                	 if(regForm.getGendarTrns().equalsIgnoreCase("m"))
	                		 mapDto.setSelectedGender("Male");
	                	 else
	                		 mapDto.setSelectedGender("Female");
	                	 if(regForm.getAgeTrns()=="")
	                		 mapDto.setAgeTrns("-");
	                	 else
	                	     mapDto.setAgeTrns(regForm.getAgeTrns());
	                	 mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
	                	 if(regForm.getMotherNameTrns()=="")
	                		 mapDto.setMotherNameTrns("-");
	                	 else
	                	     mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
	                	 if(regForm.getSpouseNameTrns()=="")
	                		 mapDto.setSpouseNameTrns("-");
	                	 else
	                	     mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
	                	 mapDto.setNationalityTrns(regForm.getNationalityTrns());
	                	
	                	 if(regForm.getPostalCodeTrns()=="")
	                		 mapDto.setPostalCodeTrns("-");
	                	 else
	                	     mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
	                	 if(regForm.getIndphnoTrns()=="")
	                	     mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getIndphnoTrns());
	                	 if(regForm.getIndmobnoTrns()=="")
	                	     mapDto.setMobnoTrns("-");
	                	 else
	                         mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
	                	 if(regForm.getEmailIDTrns()=="")
	                		 mapDto.setEmailIDTrns("-");
	                	 else
	                	     mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
	                	 mapDto.setSelectedPhotoId(regForm.getListIDTrns());
	                	 mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
	                	 mapDto.setIdnoTrns(regForm.getIdnoTrns());
	                	 mapDto.setOgrNameTrns("-");
	                	 mapDto.setAuthPerNameTrns("-");
	                	 mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getIndcountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getIndstatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
	                	 mapDto.setSelectedDistrictName(regForm.getInddistrictNameTrns());
	                	// mapDto.setRoleName(roleName);
	                	
	                   	 if(regForm.getIndReligionTrns()=="")
	                   		 mapDto.setIndReligionTrns("-");
	                   	 else
	                	     mapDto.setIndReligionTrns(regForm.getIndReligionTrns());
	                	 if(regForm.getIndCasteTrns()=="")
	                		 mapDto.setIndCasteTrns("-");
	                	 else
	                   	     mapDto.setIndCasteTrns(regForm.getIndCasteTrns());
	                	 if(regForm.getIndDisabilityTrns()=="")
	                		 mapDto.setIndDisabilityTrns("-");
	                	 else
	                		 mapDto.setIndDisabilityTrns(regForm.getIndDisabilityTrns());
	                	 if(regForm.getOwnershipShareTrns()=="")
	                		 mapDto.setOwnershipShareTrns("-");
	                	 else
	                		 mapDto.setOwnershipShareTrns(regForm.getOwnershipShareTrns());
	                	 if(regForm.getRelationWithOwnerTrns()=="")
	                		 mapDto.setRelationWithOwnerTrns("-");
	                	 else
	                	     mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
	                	 if(regForm.getShareOfPropTrns()=="")
	                		 mapDto.setShareOfPropTrns("-");
	                	 else
	                	     mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
	                	 
	                	 mapDto.setIndividualOrOrganization("02");
	                 }
	                 
	                 
	                 
	                map = regForm.getMapTransactingParties();
	               
	                 /*regForm.setAddMoreCounter(count);
	                    if(map.containsKey(key)){
	                       keyCount=keyCount+1;
	                	   key=key+keyCount;
	                	   
	                     }
	                     map.put(key, mapDto);*/
	                int count=regForm.getMapKeyCount();
	                 if(count==0)
	                	 count=1;
	                 //else
	                //	 count++;
	                 
	                 if(map.containsKey(Integer.toString(count))){
		                  //keyCount=keyCount+1;
		                //	   key=key+keyCount;
	                	 count++;
	                	 map.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 map.put(Integer.toString(count), mapDto);
		                  
	                 
	                 regForm.setMapKeyCount(count);
	                 
	                 regForm.setAddMoreCounter(count);
	                     //key="key";
	                     regForm.setMapBuildingDetails(map);
					
					//end of insertion of last transacting party in map
	                //below code for inserting PoAs and Owners in lists.
		                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_SELLER_POA)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_POA_HOLDER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_POA_HOLDER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_POA_HOLDER))
		                 {
		                	 ArrayList list = regForm.getPoaList();
		                	 ArrayList row_list = new ArrayList();
		                	 row_list.add(mapDto.getPartyRoleTypeId());
		                	 row_list.add(mapDto.getIndAuthPersn());
		                	 list.add(row_list);
		                	 regForm.setPoaList(list);
		                	 regForm.setSelectedPoa(mapDto.getPartyRoleTypeId());
		     				 regForm.setSelectedPoaName(mapDto.getIndAuthPersn());
		                	 
		                 }
		                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_OWNER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_OWNER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_OWNER))
		                 {
		                	 ArrayList list = regForm.getOwnerList();
		                	 ArrayList row_list = new ArrayList();
		                	 row_list.add(mapDto.getPartyRoleTypeId());
		                	 row_list.add(mapDto.getIndAuthPersn());
		                	 list.add(row_list);
		                	 regForm.setOwnerList(list);
		                	 
		                 }
		                 
		                 if(regForm.getPoaList()!=null && regForm.getOwnerList()!=null){
		                	 
		                	 mainList = new ArrayList();
		                	 CommonDTO dto = null;
			                 ArrayList subList = null;
			                 //for poa list
			                 ArrayList list=regForm.getPoaList();
			                 for (int i = 0; i < list.size(); i++) {
			                     subList = (ArrayList)list.get(i);
			                     dto = new CommonDTO();
			                     dto.setId(subList.get(0).toString());
			                     dto.setName(subList.get(1).toString());
			                     mainList.add(dto);
			                 	}
			         		 regForm.setPoaList(mainList);
			         		 commonDto.setPoaList(new ArrayList());
			         		for(int i=0;i<mainList.size();i++)
								commonDto.getPoaList().add(mainList.get(i));
			         		 
			         		list.clear();
			         		 mainList.clear();
			         		 subList.clear();
			         		 //for owner list
			         		 list=regForm.getOwnerList();
			         		for (int i = 0; i < list.size(); i++) {
			                     subList = (ArrayList)list.get(i);
			                     dto = new CommonDTO();
			                     dto.setId(subList.get(0).toString());
			                     dto.setName(subList.get(1).toString());
			                     mainList.add(dto);
			                 	}
			         		 regForm.setOwnerList(mainList);
			         		commonDto.setOwnerList(new ArrayList());
			         		for(int i=0;i<mainList.size();i++)
								commonDto.getOwnerList().add(mainList.get(i));
			         		list.clear();
			         		 mainList.clear();
			         		 subList.clear();


			         		 
		                 }
		            //above code for inserting PoAs and Owners in lists.
				    //below code is for insertion of transacting parties in database.
                    Collection mapCollection=map.values();
                    Object[] l1=mapCollection.toArray();
                    RegCommonDTO mapDto1 =new RegCommonDTO();
                    for(int i=1;i<l1.length;i++)
                    {
                    	mapDto1=(RegCommonDTO)l1[i];
                    	mapDto1.setPropertyId(regForm.getPropertyId());
                    	boolean transPartyDetailsInserted;
                    	//if(isTokenValid(request)) {
                         /*transPartyDetailsInserted = 
                        	  commonBo.insertTransPartyDetails(mapDto1, regForm.getHidnRegTxnId());*/
                    	//}
                    	//else{
                    	//	transPartyDetailsInserted=false;
                    	//}
                         /*if(!transPartyDetailsInserted){
  							forward="transactingParty";
  							regForm.setHidnRegTxnId(null);
  							actionName=RegInitConstant.NO_ACTION;
  	  						regForm.setActionName(RegInitConstant.NO_ACTION);
  	  						break;
  						}*/
                         //else{
                        //	 resetToken(request);
                        // }
  						
                    }
					
                 
						//resetToken(request);
						
						regForm.setActionName(RegInitConstant.NO_ACTION);
				}
				
				
				
				//NEXT TO PROPERTY DETAILS PAGE THROUGH MAPPING PAGE
				if(RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName)){
					
					  //below code for getting owner list.
              	
              	  if(regForm.getHdnOwnerId()!=null){
              	  System.out.println("owner list :- "+regForm.getHdnOwnerId());
              	  String[] ownerID = regForm.getHdnOwnerId().split(",");
              	  for(int i=0;i<ownerID.length;i++){
              		  System.out.println("owner "+i+" :- "+ownerID[i]);
              	  }
              	  
              	  //end of code for getting owner list.
              	  
              	  boolean poaOwnerMappindInsertion=commonBo.insertMappingDetails(regForm.getHidnRegTxnId(), regForm.getSelectedPoa(), ownerID, session.getAttribute("UserId").toString());
              	  System.out.println("mapping insertion status :- "+poaOwnerMappindInsertion);
              	if(!poaOwnerMappindInsertion){
						forward="reginitMapping";
						regForm.setHidnRegTxnId(null);
						actionName=RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
						//break;
					}
              	  
              	  
              	  }
              	  
              	  request.setAttribute("propAction", "propAction");
              	request.setAttribute("regInitForm", regForm);
					
				}
				
				//NEXT TO PROPERTY DETAILS PAGE THROUGH TRANSACTING PARTIES PAGE
                      if(RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)) {
                    	  
                    	
					//below code is for the insertion of last transacting party in map
					
					
	               
	                 RegCommonDTO mapDto =new RegCommonDTO();
	                 
	               //following code for insertion of owner details into map
	                 
	                 int roleId=Integer.parseInt(regForm.getPartyTypeTrns());
	                 if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
	                		 roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
	                		 roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER ||
	                		 roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER)
	                 {
	                	 
	                	 mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
	                	 if(regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null") )
		                   		mapDto.setOwnerOgrNameTrns("-");
		                   	 else
		                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
	                	
	                	 if(regForm.getOwnerGendarTrns().equalsIgnoreCase("f"))
	                	 mapDto.setOwnerGendarTrns("Female");
	                	 else
	                     mapDto.setOwnerGendarTrns("Male");	 
	                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
	                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
	                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
	                	 if(regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
		                   		mapDto.setOwnerEmailIDTrns("-");
		                   	 else
		                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
	                	
	                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns());
	                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
	                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
	                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofNameTrns());
	                 }
	                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
	                 mapDto.setListAdoptedPartyNameTrns(regForm.getListAdoptedPartyNameTrns());
	                 mapDto.setPurposeTrns("");
	                 mapDto.setBname("");                                                //might be changed
	                 mapDto.setBaddress("");                                             //might be changed
	                 mapDto.setApplicantOrTransParty("Transacting");
	                 mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
	                 regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
	                
	                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
	                 mapDto.setUserID(regForm.getHidnUserId());
	                 if(regForm.getOwnershipShareTrns().equalsIgnoreCase(""))
                		 mapDto.setOwnershipShareTrns("-");
                	 else
                		 mapDto.setOwnershipShareTrns(regForm.getOwnershipShareTrns());
                	 if(regForm.getRelationWithOwnerTrns().equalsIgnoreCase(""))
                		 mapDto.setRelationWithOwnerTrns("-");
                	 else
                	     mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
                	 if(regForm.getShareOfPropTrns().equalsIgnoreCase(""))
                		 mapDto.setShareOfPropTrns("-");
                	 else
                	     mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
                	 
                	 mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(),language,regForm.getDeedID(),regForm.getInstID()));
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("01")){
	                	 //organization
	                	 mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
	                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
	                	 mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getCountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getStatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getDistrictTrns());
	                	 mapDto.setSelectedDistrictName(regForm.getDistrictNameTrns());
	                	 if(regForm.getMobnoTrns().equalsIgnoreCase(""))
	                		 mapDto.setMobnoTrns("-"); 
	                	 else
	                	     mapDto.setMobnoTrns(regForm.getMobnoTrns());
	                	 if(regForm.getPhnoTrns().equalsIgnoreCase(""))
	                		 mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getPhnoTrns());
	                	 mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
	                	 mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
	                	 
	                	 mapDto.setFnameTrns("");
	               		 mapDto.setMnameTrns("");
	                	 mapDto.setLnameTrns("");
	                	 mapDto.setGendarTrns("-");
	                	 mapDto.setSelectedGender("");
	                	 mapDto.setAgeTrns("");
	                   	 mapDto.setFatherNameTrns("-");
	                	 mapDto.setMotherNameTrns("");
	               		 mapDto.setSpouseNameTrns("");
	                  	 mapDto.setNationalityTrns("");
	                	 mapDto.setPostalCodeTrns("");
	               		 mapDto.setEmailIDTrns("");
	                	 mapDto.setSelectedPhotoId("");
	                	 mapDto.setSelectedPhotoIdName("");
	                	 mapDto.setIdnoTrns("-");
	                	 mapDto.setIndReligionTrns("");
	                	 mapDto.setIndCasteTrns("");
	                	 mapDto.setIndDisabilityTrns("");
	                	
	                	 mapDto.setIndividualOrOrganization("01");
	                	
	                 }
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("02")){
	                	 //individual
	                	 mapDto.setFnameTrns(regForm.getFnameTrns());
	                	 if(regForm.getMnameTrns().equalsIgnoreCase(""))
	                		 mapDto.setMnameTrns("-");
	                	 else
	                	     mapDto.setMnameTrns(regForm.getMnameTrns());
	                	 mapDto.setLnameTrns(regForm.getLnameTrns());
	                	 mapDto.setGendarTrns(regForm.getGendarTrns());
	                	 if(regForm.getGendarTrns().equalsIgnoreCase("m"))
	                		 mapDto.setSelectedGender("Male");
	                	 else
	                		 mapDto.setSelectedGender("Female");
	                	 if(regForm.getAgeTrns().equalsIgnoreCase(""))
	                		 mapDto.setAgeTrns("-");
	                	 else
	                	     mapDto.setAgeTrns(regForm.getAgeTrns());
	                	 mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
	                	 if(regForm.getMotherNameTrns().equalsIgnoreCase(""))
	                		 mapDto.setMotherNameTrns("-");
	                	 else
	                	     mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
	                	 if(regForm.getSpouseNameTrns().equalsIgnoreCase(""))
	                		 mapDto.setSpouseNameTrns("-");
	                	 else
	                	     mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
	                	 mapDto.setNationalityTrns(regForm.getNationalityTrns());
	                	
	                	 if(regForm.getPostalCodeTrns().equalsIgnoreCase(""))
	                		 mapDto.setPostalCodeTrns("-");
	                	 else
	                	     mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
	                	 if(regForm.getIndphnoTrns().equalsIgnoreCase(""))
	                	     mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getIndphnoTrns());
	                	 if(regForm.getIndmobnoTrns().equalsIgnoreCase(""))
	                	     mapDto.setMobnoTrns("-");
	                	 else
	                         mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
	                	 if(regForm.getEmailIDTrns().equalsIgnoreCase(""))
	                		 mapDto.setEmailIDTrns("-");
	                	 else
	                	     mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
	                	 mapDto.setSelectedPhotoId(regForm.getListIDTrns());
	                	 mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
	                	 mapDto.setIdnoTrns(regForm.getIdnoTrns());
	                	 
	                	
	                	 mapDto.setOgrNameTrns("-");
	                	 mapDto.setAuthPerNameTrns("-");
	                	 mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getIndcountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getIndstatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
	                	 mapDto.setSelectedDistrictName(regForm.getInddistrictNameTrns());
	                	 
	                	 if(regForm.getIndReligionTrns().equalsIgnoreCase(""))
	                   		 mapDto.setIndReligionTrns("-");
	                   	 else
	                	     mapDto.setIndReligionTrns(regForm.getIndReligionTrns());
	                	 if(regForm.getIndCasteTrns().equalsIgnoreCase(""))
	                		 mapDto.setIndCasteTrns("-");
	                	 else
	                   	     mapDto.setIndCasteTrns(regForm.getIndCasteTrns());
	                	 if(regForm.getIndDisabilityTrns().equalsIgnoreCase(""))
	                		 mapDto.setIndDisabilityTrns("-");
	                	 else
	                		 mapDto.setIndDisabilityTrns(regForm.getIndDisabilityTrns());
	                	 
	                	 mapDto.setIndividualOrOrganization("02");
	                 }
	                 
	                map = regForm.getMapTransactingParties();
	              
	                int count=regForm.getMapKeyCount();
	                 if(count==0)
	                	 count=1;
	               
	                 if(map.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 map.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 map.put(Integer.toString(count), mapDto);
		                  
	                 
	                 regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
	                 
	                 regForm.setMapKeyCount(count);
	                 
	                 regForm.setAddMoreCounter(count);
	                     key="key";
	                    
	                     regForm.setMapTransactingParties(map);
					
					//end of insertion of last transacting party in map
	                
				    //below code is for insertion of transacting parties in database.
                    Collection mapCollection=regForm.getMapTransPartyDbInsertion().values();
                    Object[] l1=mapCollection.toArray();
                    RegCommonDTO mapDto1 =new RegCommonDTO();
                    for(int i=0;i<l1.length;i++)
                    {
                    	mapDto1=(RegCommonDTO)l1[i];
                    	mapDto1.setPropertyId(regForm.getPropertyId());
                        //  boolean transPartyDetailsInserted = 
                        	//  commonBo.insertTransPartyDetails(mapDto1, regForm.getHidnRegTxnId());
                          /*if(!transPartyDetailsInserted){
    							forward="transactingParty";
    							regForm.setHidnRegTxnId(null);
    							actionName=RegInitConstant.NO_ACTION;
    	  						regForm.setActionName(RegInitConstant.NO_ACTION);
    	  						break;
    						}*/
                    }
					
					
                    regForm.setMapTransPartyDbInsertion(new HashMap());
						regForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
				}
				
			}
			
			if(RegInitConstant.REG_INIT_CONFIRM_PAGE.equals(formName)) {
				if(RegInitConstant.MODIFY_ACTION.equals(actionName)){
					
					forward="success";
				}
				
				if(RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName)){
					
					
					
					
					
					
					//CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					
					if(Double.parseDouble(regForm.getTotalBalanceAmount())>0)
					{
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("parentFunId", "FUN_023");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmount());
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					//regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					//request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./regInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					//end of addition on 13feb for new payment modality.
					
					
					
					//CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					String paymentType="2";
					
					regForm.setPaymentType(paymentType);
					String[] paymentArr=commonBo.getPaymentFlag(regForm.getHidnRegTxnId(),paymentType);
					//logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if(paymentArr!=null)
					{
					if(!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")){
						
						if(paymentArr[0].equalsIgnoreCase("I")){
							
							
							request.setAttribute("parentKey", paymentArr[1]);
							//following code for updating purpose
							boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
							if(updatePurpose)
							forward="reginitPayment";
							else
							forward="failure";
							
							
						}
						/*else if(paymentArr[0].equals(null)||paymentArr[0].equalsIgnoreCase("")){
							
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							//insertion code
							//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus=commonBo.insertPaymentDetails(regForm);
							logger.debug("----------------->payment insertion status:-"+insertStatus);
							if(insertStatus)
								forward="reginitPayment";
							else
								forward="failure";
							
						}*/
						
					}
					else if(paymentArr[0].equalsIgnoreCase("p")){
						
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						//insertion code
						//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus=commonBo.insertPaymentDetails(regForm,paymentType);
						logger.debug("----------------->payment insertion status:-"+insertStatus);
						if(insertStatus)
							forward="reginitPayment";
						else
							forward="failure";
					}
					else if(paymentArr[0].equalsIgnoreCase("c")){
						forward="success1";
					}else{
						forward="failure";
					}
					}else{
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						//insertion code
						//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus=commonBo.insertPaymentDetails(regForm,paymentType);
						logger.debug("----------------->payment insertion status:-"+insertStatus);
						if(insertStatus)
							forward="reginitPayment";
						else
							forward="failure";
					}
				}else{
					
					actionName=RegInitConstant.FINAL_ACTION;
					
				}
					//forward="reginitPayment";
					//return mapping.findForward(forward);
				}
				  
				
			}
			//FOLLOWING ADDED BY ROOPAM
			if(RegInitConstant.CANCEL_TRANSACTING_PART_ACTION.equals(actionName)||RegInitConstant.CANCEL_ACTION.equals(actionName) &&(request.getParameter("modName")==null)){				
				
				cancelAction(session,regForm);
				if(map!=null){
				if(!map.isEmpty())
					map.clear();
				}
				//count=0;
				//keyCount=0;
				forward="welcome";
				return mapping.findForward(forward);
			}
			
			if(regForm.getMapKeyCount()==0)
           	 regForm.setDeleteTrnsPrtyID("");
			
				 if(RegInitConstant.ADD_MORE_ACTION.equals(actionName)) {
                 /*count = count + 1;
                 
                 if(count==1)
                	 keyCount=1;*/
                
                 RegCommonDTO mapDto =new RegCommonDTO();
                 
                 //following code for insertion of owner details into map
                 
                 int roleId=Integer.parseInt(regForm.getPartyTypeTrns());
                 if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER)
                 {
                	 
                	 mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
                	 if(regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
	                   		mapDto.setOwnerOgrNameTrns("-");
	                   	 else
	                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
                	
                	 if(regForm.getOwnerGendarTrns().equalsIgnoreCase("f"))
                	 mapDto.setOwnerGendarTrns("Female");
                	 else
                     mapDto.setOwnerGendarTrns("Male");	 
                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
                	 if(regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
	                   		mapDto.setOwnerEmailIDTrns("-");
	                   	 else
	                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
                	
                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns());
                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofNameTrns());
                 }
                 
               
                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
                 mapDto.setListAdoptedPartyNameTrns(regForm.getListAdoptedPartyNameTrns());
                 mapDto.setPurposeTrns("");
                 mapDto.setBname("");                                                //might be changed
                 mapDto.setBaddress("");                                             //might be changed
                 mapDto.setApplicantOrTransParty("Transacting");
                 mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
                 regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
               
                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
                 mapDto.setUserID(regForm.getHidnUserId());
                 if(regForm.getOwnershipShareTrns().equalsIgnoreCase(""))
            		 mapDto.setOwnershipShareTrns("-");
            	 else
            		 mapDto.setOwnershipShareTrns(regForm.getOwnershipShareTrns());
            	 if(regForm.getRelationWithOwnerTrns().equalsIgnoreCase(""))
            		 mapDto.setRelationWithOwnerTrns("-");
            	 else
            	     mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
            	 if(regForm.getShareOfPropTrns().equalsIgnoreCase(""))
            		 mapDto.setShareOfPropTrns("-");
            	 else
            	     mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
            	 
            	 
            	 
            	 //following code for share of property validation.
            	 int appRoleId=regForm.getApplicantRoleId2();
            	// if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){
            	// if(appRoleId==RegInitConstant.ROLE_SELLER_SELF || appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
            		 
            		 String applicantRoleId1=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
 					
 					
 					//following if condition for disabling applicant role once total sum of share is 100%.
            		 
 					if(regForm.getTotalShareOfProp()==100){
 						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId1));
 					}
 					          		 
 					/* }else
            	 if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.getApplicantRoleId2()){
            	 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
					
					
					//following if condition for disabling applicant role once total sum of share is 100%.
					if(regForm.getTotalShareOfProp()==100){
						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
					}
            	 }*/
            	// }
            	 /*if(regForm.getDeedID()==RegInitConstant.DEED_GIFT){
                	 if(appRoleId==RegInitConstant.ROLE_SELLER_SELF || appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
                		 
                		 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
     					
     					
     					//following if condition for disabling applicant role once total sum of share is 100%.
     					if(regForm.getTotalShareOfProp()==100){
     						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
     					}
     					          		 
                	 }else
                	 if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.getApplicantRoleId2()){
                	 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
    					
    					
    					//following if condition for disabling applicant role once total sum of share is 100%.
    					if(regForm.getTotalShareOfProp()==100){
    						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
    					}
                	 }
                	 }*/
				//end of code for share of property validation.
            	 
            	 //FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
            	 int selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());
            	 int applicantRoleId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
            	 if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){    //CONVEYANCE DEED
            	 if(applicantRoleId==RegInitConstant.ROLE_SELLER_SELF || applicantRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
            		 
            						
 					 if(selectedRoleId==RegInitConstant.ROLE_SELLER_SELF || selectedRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
 						 mapDto.setPartyTypeFlag("C");
 					 }
            		 
            		 
            	 }else if(applicantRoleId==RegInitConstant.ROLE_BUYER){
            		 if(selectedRoleId==RegInitConstant.ROLE_BUYER){
 						 mapDto.setPartyTypeFlag("C");
 					 }
            	 }
            	 }else
            		 if(regForm.getDeedID()==RegInitConstant.DEED_GIFT || regForm.getDeedID()==RegInitConstant.DEED_POA_P){      //GIFT DEED
                    	
                    		 
                    						
         					 if(selectedRoleId==applicantRoleId ){
         						 mapDto.setPartyTypeFlag("C");
         					 }
         					 
         					 if(regForm.getDeedID()==RegInitConstant.DEED_POA_P && selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER){
         						 
         						 regForm.setIsPoaAddedFlag(1);
         						 
         					 }
                    
                    	 }
            		 else
            			 if(regForm.getDeedID()==RegInitConstant.DEED_CERTIFICATE_SALE){    //SALE DEED
                        	 if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF || applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                        		 
                        						
             					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             						 mapDto.setPartyTypeFlag("C");
             					 }
                        		 
                        		 
                        	 }else if(applicantRoleId==RegInitConstant.ROLE_BUYER){
                        		 if(selectedRoleId==RegInitConstant.ROLE_BUYER){
             						 mapDto.setPartyTypeFlag("C");
             					 }
                        	 }
                        	 }
            		 
            	 mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(),language,regForm.getDeedID(),regForm.getInstID()));
            	 
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("01")){
                	 //organization
                	 mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
                	 mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
                	 mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
                	 mapDto.setSelectedCountry(regForm.getCountryTrns());
                	 mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
                	 mapDto.setSelectedState(regForm.getStatenameTrns());
                	 mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
                	 mapDto.setSelectedDistrict(regForm.getDistrictTrns());
                	 mapDto.setSelectedDistrictName(regForm.getDistrictNameTrns());
                	 if(regForm.getMobnoTrns().equalsIgnoreCase(""))
                		 mapDto.setMobnoTrns("-"); 
                	 else
                	     mapDto.setMobnoTrns(regForm.getMobnoTrns());
                	 if(regForm.getPhnoTrns().equalsIgnoreCase(""))
                		 mapDto.setPhnoTrns("-");
                	 else
                	     mapDto.setPhnoTrns(regForm.getPhnoTrns());
                	 mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
                	 mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
                	 
                	 mapDto.setFnameTrns("");
               		 mapDto.setMnameTrns("");
                	 mapDto.setLnameTrns("");
                	 mapDto.setGendarTrns("-");
                	 mapDto.setSelectedGender("");
                	 mapDto.setAgeTrns("");
                   	 mapDto.setFatherNameTrns("-");
                	 mapDto.setMotherNameTrns("");
               		 mapDto.setSpouseNameTrns("");
                  	 mapDto.setNationalityTrns("");
                	 mapDto.setPostalCodeTrns("");
               		 mapDto.setEmailIDTrns("");
                	 mapDto.setSelectedPhotoId("");
                	 mapDto.setSelectedPhotoIdName("");
                	 mapDto.setIdnoTrns("-");
                	
                	 mapDto.setIndReligionTrns("");
                	 mapDto.setIndCasteTrns("");
                	 mapDto.setIndDisabilityTrns("");
                	
                	 mapDto.setIndividualOrOrganization("01");
                	
                 }
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("02")){
                	 //individual
                	 mapDto.setFnameTrns(regForm.getFnameTrns());
                	 if(regForm.getMnameTrns().equalsIgnoreCase(""))
                		 mapDto.setMnameTrns("-");
                	 else
                	     mapDto.setMnameTrns(regForm.getMnameTrns());
                	 mapDto.setLnameTrns(regForm.getLnameTrns());
                	 mapDto.setGendarTrns(regForm.getGendarTrns());
                	 if(regForm.getGendarTrns().equalsIgnoreCase("m"))
                		 mapDto.setSelectedGender("Male");
                	 else
                		 mapDto.setSelectedGender("Female");
                	 if(regForm.getAgeTrns().equalsIgnoreCase(""))
                		 mapDto.setAgeTrns("-");
                	 else
                	     mapDto.setAgeTrns(regForm.getAgeTrns());
                	 mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
                	 if(regForm.getMotherNameTrns().equalsIgnoreCase(""))
                		 mapDto.setMotherNameTrns("-");
                	 else
                	     mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
                	 if(regForm.getSpouseNameTrns().equalsIgnoreCase(""))
                		 mapDto.setSpouseNameTrns("-");
                	 else
                	     mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
                	 mapDto.setNationalityTrns(regForm.getNationalityTrns());
                	 if(regForm.getPostalCodeTrns().equalsIgnoreCase(""))
                		 mapDto.setPostalCodeTrns("-");
                	 else
                	     mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
                	 
                	 if(regForm.getIndphnoTrns().equalsIgnoreCase(""))
                		 mapDto.setPhnoTrns("-");
                	 else
                	     mapDto.setPhnoTrns(regForm.getIndphnoTrns());
                	 
                	 if(regForm.getIndmobnoTrns().equalsIgnoreCase(""))
                		 mapDto.setMobnoTrns("-");
                	 else
                         mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
                	 
                	 if(regForm.getEmailIDTrns().equalsIgnoreCase(""))
                		 mapDto.setEmailIDTrns("-");
                	 else
                	     mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
                	 mapDto.setSelectedPhotoId(regForm.getListIDTrns());
                	 mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
                	 mapDto.setIdnoTrns(regForm.getIdnoTrns());
                	 
                	
                	 mapDto.setOgrNameTrns("-");
                	 mapDto.setAuthPerNameTrns("-");
                	 mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
                	 mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
                	 mapDto.setSelectedCountry(regForm.getIndcountryTrns());
                	 mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
                	 mapDto.setSelectedState(regForm.getIndstatenameTrns());
                	 mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
                	 mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
                	 mapDto.setSelectedDistrictName(regForm.getInddistrictNameTrns());
                	
                	 if(regForm.getIndReligionTrns().equalsIgnoreCase(""))
                   		 mapDto.setIndReligionTrns("-");
                   	 else
                	     mapDto.setIndReligionTrns(regForm.getIndReligionTrns());
                	 if(regForm.getIndCasteTrns().equalsIgnoreCase(""))
                		 mapDto.setIndCasteTrns("-");
                	 else
                   	     mapDto.setIndCasteTrns(regForm.getIndCasteTrns());
                	 if(regForm.getIndDisabilityTrns().equalsIgnoreCase(""))
                		 mapDto.setIndDisabilityTrns("-");
                	 else
                		 mapDto.setIndDisabilityTrns(regForm.getIndDisabilityTrns());
                	                 	 
                	 mapDto.setIndividualOrOrganization("02");
                 }
                 
                
                 map = regForm.getMapTransactingParties();
            
                 int count=regForm.getMapKeyCount();
                 if(count==0)
                	 count=1;
             
                 if(map.containsKey(Integer.toString(count))){
	                 
                	 count++;
                	 map.put(Integer.toString(count), mapDto);
                	 
	                 }
                 else
                	 map.put(Integer.toString(count), mapDto);
	                  
                 
                 regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
                 
                 regForm.setMapKeyCount(count);
                 
                 regForm.setAddMoreCounter(count);
                                 key="key";
                         
                
                                 regForm.setMapTransactingParties(map);
                                 
                 //below code for inserting PoAs and Owners in lists.
                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_SELLER_POA)||
		            mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_POA_HOLDER)||
		           	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_POA_HOLDER)||
		           	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_POA_HOLDER))
		                 {
		                	 ArrayList list = regForm.getPoaList();
		                	 ArrayList row_list = new ArrayList();
		                	 row_list.add(mapDto.getPartyRoleTypeId());
		                	 row_list.add(mapDto.getIndAuthPersn());
		                	 list.add(row_list);
		                	 regForm.setPoaList(list);
		                	 regForm.setSelectedPoa(mapDto.getPartyRoleTypeId());
		     				 regForm.setSelectedPoaName(mapDto.getIndAuthPersn());
		                	
		                 }
		                 if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_OWNER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_OWNER)||
		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_OWNER))
		                 {
		                	 ArrayList list = regForm.getOwnerList();
		                	 ArrayList row_list = new ArrayList();
		                	 row_list.add(mapDto.getPartyRoleTypeId());
		                	 row_list.add(mapDto.getIndAuthPersn());
		                	 list.add(row_list);
		                	 regForm.setOwnerList(list);
		                	 
		                 }
		         //above code for inserting PoAs and Owners in lists.
                 
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("01")){
                	 regForm.setPartyTypeTrns(null);
                	 regForm.setOgrNameTrns("");
                	 regForm.setAuthPerNameTrns("");
                	 regForm.setOrgaddressTrns("");
                	 regForm.setPhnoTrns("");
                	 regForm.setMobnoTrns("");
                	 regForm.setConsiAmountTrns("");
                	 regForm.setMarketValueTrns("");
                	 regForm.setCountryTrns("");
                	 regForm.setDistrictTrns("");
                	 regForm.setStatenameTrns("");
                	 commonDto.setCountryTrns(commonBo.getCountry(language));
                	 regForm.setActionName("");
                	 regForm.setOwnershipShareTrns("");
					 regForm.setRelationWithOwnerTrns("");
					 regForm.setShareOfPropTrns("");
                	 
                	 
                 }
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase("02")){
                	 regForm.setPartyTypeTrns(null);
                	 regForm.setFnameTrns("");
                	 regForm.setMnameTrns("");
                	 regForm.setLnameTrns("");
                	 regForm.setGendarTrns("M");
                	 regForm.setAgeTrns("");
                	 regForm.setFatherNameTrns("");
                	 regForm.setMotherNameTrns("");
                	 regForm.setSpouseNameTrns("");
                	 regForm.setNationalityTrns("");
                	 regForm.setPostalCodeTrns("");
                	 regForm.setIndphnoTrns("");
                	 regForm.setIndmobnoTrns("");
                	 regForm.setEmailIDTrns("");
                	 regForm.setListIDTrns("");
                	 regForm.setIdnoTrns("");
                	 regForm.setIndaddressTrns("");
                	 regForm.setIndcountryTrns("");
                	 regForm.setInddistrictTrns("");
                	 regForm.setIndstatenameTrns("");
                	 commonDto.setIndcountryTrns(commonBo.getCountry(language));
					 commonDto.setIdProfTrns(commonBo.getIdProf(language));
					 regForm.setActionName("");
				     regForm.setIndReligionTrns("");
					 regForm.setIndCasteTrns("");
					 regForm.setIndDisabilityTrns("");
					 regForm.setOwnershipShareTrns("");
					 regForm.setRelationWithOwnerTrns("");
					 regForm.setShareOfPropTrns("");
					  
                 }
                 if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER ||
                		 roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
                 regForm.setOwnerNameTrns("");
            	 regForm.setOwnerOgrNameTrns("");
                 regForm.setOwnerGendarTrns("M");
            	 regForm.setOwnerNationalityTrns("");
            	 regForm.setOwnerAddressTrns("");
            	 regForm.setOwnerPhnoTrns("");
            	 regForm.setOwnerEmailIDTrns("");
                 regForm.setOwnerIdnoTrns("");
            	 regForm.setOwnerAgeTrns("");
            	 regForm.setOwnerListIDTrns("");
            	 regForm.setOwnerProofNameTrns("");
            	 commonDto.setIdProf(commonBo.getIdProf(language));
                 }
                 
                 regForm.setListAdoptedPartyTrns(null);
                 
                 forward = "transactingParty";
                }
				 if(RegInitConstant.DELETE_MORE_ACTION.equals(actionName)) {
					 
					 RegCommonDTO mapDto =new RegCommonDTO();
					int roleCount=0;
					String[] trnsPrtyID= regForm.getHdnDeleteTrnsPrtyId().split(",");
					 
					 int count=regForm.getMapKeyCount()-1;
					 map=regForm.getMapTransactingParties();
					
					 for(int i = 0 ;i < trnsPrtyID.length ;i++) {
                         logger.debug(trnsPrtyID[i]+" is removed...");
                         
                         //correction of poa and owner count.
                         mapDto=(RegCommonDTO)map.get(trnsPrtyID[i]);
                
                         /*if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_SELLER_POA)){
                        	 roleCount=regForm.getSellerPoaCount();
                        	 roleCount--;
                        	 regForm.setSellerPoaCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_POA_HOLDER)){
                        	 roleCount=regForm.getPoaHolderCount();
                        	 roleCount--;
                        	 regForm.setPoaHolderCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_POA_HOLDER)){
                        	 roleCount=regForm.getParty1PoaHolderCount();
                        	 roleCount--;
                        	 regForm.setParty1PoaHolderCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_POA_HOLDER)){
                        	 roleCount=regForm.getParty2PoaHolderCount();
                        	 roleCount--;
                        	 regForm.setParty2PoaHolderCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_OWNER)){
                        	 roleCount=regForm.getOwnerCount();
                        	 roleCount--;
                        	 regForm.setOwnerCount(roleCount);
                        	 roleCount=0;
                        	 
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_OWNER)){
                        	 roleCount=regForm.getParty1OwnerCount();
                        	 roleCount--;
                        	 regForm.setParty1OwnerCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_OWNER)){
                        	 roleCount=regForm.getParty2OwnerCount();
                        	 roleCount--;
                        	 regForm.setParty2OwnerCount(roleCount);
                        	 roleCount=0;
                         }
                         if(mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY_OWNER)||
     		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY1_OWNER)||
     		                	mapDto.getRoleName().equalsIgnoreCase(RegInitConstant.PARTY2_OWNER)){
                        	 
                        	//below code for removal of owner from list.
                        	 ArrayList list = regForm.getOwnerList();
                        	 ArrayList row_list = new ArrayList();
                        	 ArrayList new_list =new ArrayList();
                        	 for(int j=0;j<list.size();j++){
                        		 row_list=(ArrayList)list.get(j);
                        		 if(row_list.contains(mapDto.getPartyRoleTypeId())){
                        			 row_list.remove(mapDto);
                        		 }else
                        		 new_list.add(row_list);
		                	 
                        	 }
		                	 
		                	 regForm.setOwnerList(new_list);
                        	 
                         }*/
                         
                         //FOLLOWING CODE FOR CORRECTION OF TOTAL SHARE OF PROPERTY
                         int deedId=regForm.getDeedID();
                         int appId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
                         int selectedRoleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                         int totShare=0;
                         //CONVEYANVE DEED
                         if(deedId==RegInitConstant.DEED_CONVEYANCE_P){
                        	 if(appId==RegInitConstant.ROLE_SELLER_SELF || appId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
                        		 
         						
             					 if(selectedRoleId==RegInitConstant.ROLE_SELLER_SELF || selectedRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             						 
             						 
             					 }else{
             						 
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             						 
             						 
             					 }
                        		 
                        		 
                        	 }else if(appId==RegInitConstant.ROLE_BUYER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_BUYER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             						 
             						 
             					 }
                        	 }
                         
                         }
                         //GIFT
                         if(deedId==RegInitConstant.DEED_GIFT){
                        	 if(appId==RegInitConstant.ROLE_DONOR){
                        		 
         						
             					 if(selectedRoleId==RegInitConstant.ROLE_DONOR){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             						 
             						 
             					 }else{
             						 
             						totShare=regForm.getTotalShareOfPropDonee();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropDonee(totShare);
             						 
             						 
             					 }
                        		 
                        		 
                        	 }else if(appId==RegInitConstant.ROLE_DONEE){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_DONEE){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropDonor();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropDonor(totShare);
             						 
             						 
             					 }
                        	 }
                         
                         }
                       //CERTIFICATE OF DEED
                         if(deedId==RegInitConstant.DEED_CERTIFICATE_SALE){
                        	 if(appId==RegInitConstant.ROLE_OWNER_SELF || appId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                        		 
         						
             					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             						 
             						 
             					 }else{
             						 
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             						 
             						 
             					 }
                        		 
                        		 
                        	 }else if(appId==RegInitConstant.ROLE_BUYER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_BUYER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropOwnerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropOwnerSelf(totShare);
             						 
             						 
             					 }
                        	 }
                         
                         }
                         //PoA
                         if(deedId==RegInitConstant.DEED_POA_P){
                        	 if(appId==RegInitConstant.ROLE_OWNER_SELF && selectedRoleId==RegInitConstant.ROLE_OWNER_SELF){
                        		 
         						
             					/* if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF){*/
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             						 
             						 
             					 }else if(appId==RegInitConstant.ROLE_POA_ACCEPTER && selectedRoleId==RegInitConstant.ROLE_OWNER_SELF){
             						 
             						totShare=regForm.getTotalShareOfPropOwnerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropOwnerSelf(totShare);
             						 
             						 
             					 }else if(selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER){
             						 
              						regForm.setIsPoaAddedFlag(0);
              						regForm.setIsTransactingPartyAddedFlag(0);
              						 
              					 }
                        		 
                        		 
                        	 /*}else if(appId==RegInitConstant.ROLE_DONEE){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_DONEE){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropDonor();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropDonor(totShare);
             						 
             						 
             					 }
                        	 }*/
                         
                         }
                         map.remove(trnsPrtyID[i]);
                         
                         
                     }
					 regForm.setMapTransactingParties(map);
                     
                     //above for display map
					 //below for insertion map
					 map=new HashMap();
                     map=regForm.getMapTransPartyDbInsertion();
 					
					 for(int j = 0 ;j < trnsPrtyID.length ;j++) {
                         logger.debug(trnsPrtyID[j]+" is removed...");
                         map.remove(trnsPrtyID[j]);
                         
                         
					 }
					 regForm.setMapTransPartyDbInsertion(map);
					 
					 
					 regForm.setAddMoreCounter(count);
					 regForm.setMapKeyCount(count);
					 forward = "transactingParty";
					// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
					 request.setAttribute("deedId",regForm.getDeedID());
					 regForm.setActionName(RegInitConstant.NO_ACTION);
					 actionName=RegInitConstant.NO_ACTION;
					//return mapping.findForward(forward);		
				 }
				 
				 //following code for party type
				if(request.getAttribute("regFormDashboard")==null){   //this line was added for dashboard
				 String roleType=null;
				 String roleTypeTrns=null;
				 if(request.getParameter("partyType")==null)
					 roleType="0";
				 else 
				 {
					    roleType=(String)request.getParameter("partyType");
				 		regForm.setPartyType(roleType); //setting role id.
				 }
				 if(request.getParameter("partyTypeTrns")==null)
					 roleTypeTrns="0";
				 else 
				 {
					 roleTypeTrns=(String)request.getParameter("partyTypeTrns");
				 		regForm.setPartyTypeTrns(roleTypeTrns); //setting role id.
				 }		
				 
				
				 if((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) 
			             || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)){
		 
		             
					 
					 if((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName))&& !(RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					 {request.setAttribute("roleId", Integer.parseInt(roleType));
					 request.setAttribute("roleIdTrns", "0"); 
					 }
					 if(roleTypeTrns != null && (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)|| RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					 request.setAttribute("roleIdTrns", Integer.parseInt(roleTypeTrns)); 
					
					 
	             }
				 }
				
				if(RegInitConstant.NO_ACTION.equals(actionName)){
				if(regForm.getPartyRoleId()!=null){
					request.setAttribute("roleIdTrns", Integer.parseInt(regForm.getPartyRoleId())); 
				}
				else
					request.setAttribute("roleIdTrns", 0); 
				}
				 
				 if(RegInitConstant.NO_ACTION.equals(actionName))
				 { //forward ="reginitMapping";
					 
				 }
				 else if(RegInitConstant.NEXT_ACTION.equals(actionName) || 
		            	 RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || 
		            	 RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName) || 
		            	 RegInitConstant.ADD_MORE_ACTION.equals(actionName) ||
		            	 RegInitConstant.DELETE_MORE_ACTION.equals(actionName))
		            	  forward ="transactingParty";
		         else if(RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName))
		        	 forward ="reginitMapping";
		         else if(RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)||
		        		 RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName))
		        	 forward ="reginitProperty";
		         else if(RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName))
		        	 {//forward ="reginitPayment";
		        	 regForm.setActionName(RegInitConstant.NO_ACTION);
		        	 }
		         else
		             forward ="success";
				 
			
			if(RegInitConstant.RESET_APPLICANT_ACTION.equals(actionName)){
				 
				String partyType = regForm.getListAdoptedParty();
				
				//session.setAttribute("partyType", partyType);	
			    //resetToken(request);
			    
			    commonDto.setInstrument(new ArrayList());
				commonDto.setExemption(new ArrayList());
				regForm.setFname("");
				regForm.setMname("");
				regForm.setLname("");
				regForm.setGendar("M");
				regForm.setAge("");
				regForm.setFatherName("");
				regForm.setMotherName("");
				regForm.setSpouseName("");
				regForm.setNationality("");
				regForm.setIndaddress("");
				regForm.setIndcountry("");
				regForm.setIndstatename("");
				regForm.setInddistrict("");
				regForm.setPostalCode("");
				regForm.setIndphno("");
				regForm.setIndmobno("");
				regForm.setEmailID("");
				regForm.setListID("");
				regForm.setIdno("");
				regForm.setDeedType("");
				regForm.setIndCaste("");
				regForm.setIndReligion("");
				regForm.setIndDisability("");
				//regForm.setShareOfProp("");
				regForm.setOwnershipShare("");
				regForm.setRelationWithOwner("");
						
				regForm.setInstrument("");				
				commonDto.setState(new ArrayList());
				commonDto.setDistrict(new ArrayList());
				commonDto.setIndstate(new ArrayList());
				commonDto.setInddistrict(new ArrayList());
			//	commonDto.setAppType(commonBo.getAppType(language));
				commonDto.setCountry(commonBo.getCountry(language));
				commonDto.setIndcountry(commonBo.getCountry(language));
				commonDto.setIdProf(commonBo.getIdProf(language));
				commonDto.setDeedType(commonBo.getDeedType(language));			
			    regForm.setOgrName("");
                 regForm.setAuthPerName("");
                 regForm.setOrgaddress("");
                 regForm.setCountry("");
                 regForm.setStatename("");
                 regForm.setDistrict("");
                 regForm.setPhno("");
                 regForm.setMobno("");
                 regForm.setApplicantUserIdCheck(null);
                 regForm.setActionName(RegInitConstant.NO_ACTION);
                 //following reseting owner details
                 regForm.setOwnerName("");
                 regForm.setOwnerOgrName("");
                 regForm.setOwnerAddress("");
                 regForm.setOwnerAge("");
                 regForm.setOwnerEmailID("");
                 regForm.setOwnerGendar("M");
                 regForm.setOwnerIdno("");
                 regForm.setOwnerListID("");
                 regForm.setOwnerNationality("");
                 regForm.setOwnerPhno("");
                 regForm.setOwnerProofName("");
                 //session.setAttribute("commonDto", commonDto);
 				 regForm.setCommonDto(commonDto);
 				 //session.setAttribute("regForm", regForm);
			  
 				forward ="success";
			  
			}		
			if(RegInitConstant.RESET_TRANSACTING_ACTION.equals(actionName)){
				
				if(regForm.getPartyTypeTrns()!=null)
				request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
				else
					request.setAttribute("roleIdTrns",0);	
				String partyTypeTrns = regForm.getListAdoptedPartyTrns();
				
				//session.setAttribute("partyType", partyTypeTrns);	
			    //resetToken(request);
		
				regForm.setFnameTrns("");
				regForm.setMnameTrns("");
				regForm.setLnameTrns("");
				regForm.setGendarTrns("M");
				regForm.setAgeTrns("");
				regForm.setFatherNameTrns("");
				regForm.setMotherNameTrns("");
				regForm.setSpouseNameTrns("");
				regForm.setNationalityTrns("");
				regForm.setIndaddressTrns("");
				regForm.setIndcountryTrns("");
				regForm.setIndstatenameTrns("");
				regForm.setInddistrictTrns("");
				regForm.setPostalCodeTrns("");
				regForm.setIndphnoTrns("");
				regForm.setIndmobnoTrns("");
				regForm.setEmailIDTrns("");
				regForm.setListIDTrns("");
				regForm.setIdnoTrns("");
		
				regForm.setIndCasteTrns("");
				regForm.setIndReligionTrns("");
				regForm.setIndDisabilityTrns("");
				regForm.setShareOfPropTrns("");
				regForm.setOwnershipShareTrns("");
				regForm.setRelationWithOwnerTrns("");
				
				commonDto.setStateTrns(new ArrayList());
				commonDto.setDistrictTrns(new ArrayList());
				commonDto.setIndstateTrns(new ArrayList());
				commonDto.setInddistrictTrns(new ArrayList());
			//	commonDto.setAppTypeTrns(commonBo.getAppType(language));
				commonDto.setCountryTrns(commonBo.getCountry(language));
				commonDto.setIndcountryTrns(commonBo.getCountry(language));
				commonDto.setIdProfTrns(commonBo.getIdProf(language));
				commonDto.setIdProf(commonBo.getIdProf(language));
			
                regForm.setOgrNameTrns("");
                regForm.setAuthPerNameTrns("");
                regForm.setOrgaddressTrns("");
                regForm.setCountryTrns("");
                regForm.setStatenameTrns("");
                regForm.setDistrictTrns("");
                regForm.setPhnoTrns("");
                regForm.setMobnoTrns("");
                regForm.setActionName(RegInitConstant.NO_ACTION);
                
                
                regForm.setOwnerNameTrns("");
                regForm.setOwnerOgrNameTrns("");
                regForm.setOwnerAddressTrns("");
                regForm.setOwnerAgeTrns("");
                regForm.setOwnerEmailIDTrns("");
                regForm.setOwnerGendarTrns("M");
                regForm.setOwnerIdnoTrns("");
                regForm.setOwnerListIDTrns("");
                regForm.setOwnerNationalityTrns("");
                regForm.setOwnerPhnoTrns("");
                regForm.setOwnerProofNameTrns("");
                
                
                
				 //session.setAttribute("commonDto", commonDto);
				 regForm.setCommonDto(commonDto);
			
				 //session.setAttribute("regForm", regForm);
			
				 forward ="transactingParty";
			  
			}		
			
			if(RegInitConstant.RESET_MAPPING_ACTION.equals(actionName)){
				regForm.setActionName(RegInitConstant.NO_ACTION);
				regForm.setOwnerId("");
			    //session.setAttribute("regForm", regForm);
			    forward ="reginitMapping";
			  
			}	
            if(RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)){
            	regForm.setActionName(RegInitConstant.NO_ACTION);
            	PropertyValuationDTO dto= new PropertyValuationDTO();
            	RegCompletDTO comDto= new RegCompletDTO();
            	regForm.setRegcompletDto(comDto);
            	regForm.setPropertyDTO(dto);
            	
			    //session.setAttribute("regForm", regForm);
			    forward ="reginitProperty";
			  
			}
            if(RegInitConstant.APPLICANT_USERID_CHECK_ACTION.equalsIgnoreCase(actionName)){
            	regForm.setActionName(RegInitConstant.NO_ACTION);
            	if(regForm.getHdnapplicantUserIdCheck().equalsIgnoreCase("checked")){
            		
            	//	String[] appRegDetls=commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString(),regForm,language);
            		commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString(),regForm,language,commonDto);
            		/*regForm.setFname(appRegDetls[0].trim());
            		regForm.setMname(appRegDetls[1].trim());
            		regForm.setLname(appRegDetls[2].trim());
            		regForm.setGendar(appRegDetls[3].trim());
            		regForm.setNationality(appRegDetls[4].trim());
            		regForm.setIndcountry(appRegDetls[5].trim());
            		commonDto.setIndstate(commonBo.getState(appRegDetls[5].trim(),language));
            		commonDto.setInddistrict(commonBo.getDistrict(appRegDetls[6].trim(),language));
            		regForm.setCommonDto(commonDto);
            		regForm.setIndstatename(appRegDetls[6].trim());
            		regForm.setInddistrict(appRegDetls[7].trim());
            		regForm.setIndaddress(appRegDetls[8].trim());
            		regForm.setPostalCode(appRegDetls[9].trim());
            		regForm.setPhno(appRegDetls[10].trim());
            		regForm.setMobno(appRegDetls[11].trim());
            		regForm.setEmailID(appRegDetls[12].trim());
            		regForm.setListID(appRegDetls[13].trim());
            		regForm.setIdno(appRegDetls[14].trim());
            		regForm.setFatherName(appRegDetls[15].trim());
            		regForm.setMotherName(appRegDetls[16].trim());
            		regForm.setSpouseName(appRegDetls[17].trim());
            		regForm.setIndcountryName(commonBo.getCountryName(appRegDetls[5].trim(),language));
            		regForm.setIndstatenameName(commonBo.getStateName(appRegDetls[6].trim(),language));
            		regForm.setInddistrictName(commonBo.getDistrictName(appRegDetls[7].trim(),language));
            		regForm.setListIDName(commonBo.getPhotoIdProofName(appRegDetls[13].trim(),language));*/
            }
            	else{
            		regForm.setApplicantUserIdCheck(null);
            		regForm.setFname("");
            		regForm.setMname("");
            		regForm.setLname("");
            		regForm.setGendar("M");
            		regForm.setNationality("");
            		regForm.setIndcountry("");
            		regForm.setIndstatename("");
            		regForm.setInddistrict("");
            		regForm.setIndaddress("");
            		regForm.setPostalCode("");
            		regForm.setPhno("");
            		regForm.setMobno("");
            		regForm.setEmailID("");
            		regForm.setListID("");
            		regForm.setIdno("");
            		regForm.setFatherName("");
            		regForm.setMotherName("");
            		regForm.setSpouseName("");
            		commonDto.setIndstate(new ArrayList());
    				commonDto.setInddistrict(new ArrayList());
    				
    				commonDto.setIndcountry(commonBo.getCountry(language));
    				commonDto.setIdProf(commonBo.getIdProf(language));
    				regForm.setCommonDto(commonDto);
            		
            	}
            	
			    //session.setAttribute("regForm", regForm);
			    forward ="success";
			  
			}
			String label=null;
			label=(String)request.getParameter("label");
			if(label!=null && label!="")
			{
			if(label.equalsIgnoreCase("display")){
				
				RegCommonDTO mapDtoDisp =new RegCommonDTO();
				String key=request.getParameter("key");
				map2 = regForm.getMapTransactingPartiesDisp();
				
				if(!map2.isEmpty())
					map2.clear();
				
					mapDtoDisp=(RegCommonDTO)map.get(key);
					map2.put(key,mapDtoDisp);
					
					regForm.setMapTransactingPartiesDisp(map2);
					String confirmationFlag=null;
					confirmationFlag=(String)request.getParameter("confirmationFlag");
					if(confirmationFlag!=null && confirmationFlag!="")
					{
					if(confirmationFlag.equalsIgnoreCase("true")){
						regForm.setConfirmationFlag("01");
					}
					}else
						regForm.setConfirmationFlag("00");
				request.setAttribute("deedId", regForm.getDeedId());
				//request.setAttribute("roleId", regForm.getPartyType());
				request.setAttribute("roleId", mapDtoDisp.getPartyTypeTrns());
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
				
				forward="displayRegDetls";
			}
			}
			

			 //ok action
                if(RegInitConstant.OK_ACTION.equals(actionName)) {
				 
                	request.setAttribute("roleId", regForm.getPartyType());
                	if(regForm.getPartyTypeTrns()!=null && regForm.getPartyTypeTrns()!="")
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
                	else 
                		request.setAttribute("roleIdTrns", "0");
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "transactingParty";
				 
			 }
              //confirmation ok action
                if(RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
                	
					String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
            		if(deedInstArray!=null && deedInstArray.length>0){
            			
            			request.setAttribute("deedId", deedInstArray[0].trim());
            			request.setAttribute("instId", deedInstArray[1].trim());
            			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
            			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
            			
            		}else {
            			request.setAttribute("deedId", 0);
            			request.setAttribute("instId", 0);
            			regForm.setDeedID(0);
            			regForm.setInstID(0);
            		}
				 
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "reginitConfirm";
				 
				
				 
			 }
                /*if(RegInitConstant.TRANS_PARTY_VIEW_ACTION.equals(actionName)) {
   				 
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "transactingPartyView";
				 
			 }*/
		
             //view from confirmation screen.
                if(request.getParameter("view")!=null){
                	
                	
                String view=request.getParameter("view").toString();
                // Added by Rakesh for CLR Property View
                String propertyId=request.getParameter("key");
                ArrayList<String> propdet= commonBo.getPropertyTypeIdAndClrFlag(propertyId);
                String propertyTypeId="";
                String clrFlag="";
                for (int i = 0; i < propdet.size(); i++) {
				propertyTypeId=propdet.get(0);
				clrFlag=propdet.get(1);
               	}              
                           
               
                // Added by Rakesh for CLR Property View
               
                
                
                if(view.equalsIgnoreCase(RegInitConstant.APPLICANT_VIEW)){
                	forward="applicantView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.TRANSACTING_PARTY_VIEW)){
                	forward="transactingPartyView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.MAPPING_VIEW)){
                	forward="mappingView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.PROPERTY_VIEW)){
                	if(clrFlag !=null && propertyTypeId !=null){
                		if(clrFlag.equalsIgnoreCase("Y")&& propertyTypeId.equalsIgnoreCase("3")){                			
                		//ArrayList<String> clrKhasraAllDtls= commonBo.getPropDetlsForDisplayClr(propertyId);
                	
                		
                			forward="propertyViewClr";
                		}else{
                			forward="propertyView";
                		}
                		
                		
                	}else{
                		forward="propertyView";
                	}
                	
                	//forward="propertyView";
                	//forward="propertyViewClr";
                }
                }
                //return from payment
                String paymentTxnId=null;
                String strNonJudECode=null;
            	String modName=null;
            	String paidAmount=null;
                if(request.getParameter("paymentStatus")!=null)
                {
                	if(request.getParameter("paymentStatus").equalsIgnoreCase("P")){
                		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                		if(deedInstArray!=null && deedInstArray.length>0){
                			
                			request.setAttribute("deedId", deedInstArray[0].trim());
                			request.setAttribute("instId", deedInstArray[1].trim());
                			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			
                		}else {
                			request.setAttribute("deedId", 0);
                			request.setAttribute("instId", 0);
                			regForm.setDeedID(0);
                			regForm.setInstID(0);
                		}
                    	
                	//following added on 13feb for new payment modality.
                	//String paymentStatus=request.getParameter("paymentStatus");
                	//logger.debug("payment status---------->"+paymentStatus);
                	//end of addition on 13feb for new payment modality.
                		String paymentType="1";
    					
    					regForm.setPaymentType(paymentType);
                		String[] paymentArray=commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(),paymentType);
                		if(paymentArray!=null && paymentArray.length>0){
                			
                			paymentTxnId=paymentArray[0].trim();
                			paidAmount=paymentArray[1].trim();
                			regForm.setRegInitPaymntTxnId(paymentTxnId);
                			IGRSCommon objCommon=new IGRSCommon();
                        	
                        	String disId=commonBo.getApplicantDistrict(regForm.getHidnRegTxnId());
                        	modName = RegInitConstant.ESTAMP_CODE_MPET+disId;
                            strNonJudECode =  objCommon.getSequenceNumber1("IGRS_ESTAMP_SEQ_STAMP_CODE", modName);
                            if(strNonJudECode!=null)
                            regForm.setRegInitEstampCode(strNonJudECode);
                            
                            boolean eStampCodeInserted=commonBo.insertEStampCode(regForm);
                            logger.debug("e satmp code inserted.");
                		}
                      	forward = "reginitConfirm";
              
                	}
                }
              
              //final action after payment.
                if(RegInitConstant.FINAL_ACTION.equals(actionName)) {
                	
                	
                	//change payment flag to c here.
                	
                	
                	String currDate=commonBo.getCurrDateTime();
                	/*System.out.println("curr date 1 : "+currDate);
                	String mm=currDate.substring(0, 2);
                	String dd=currDate.substring(3, 5);
                	String rest=currDate.substring(6);
                	currDate=dd+"/"+mm+"/"+rest;*/
                	System.out.println("curr date 2 : "+currDate);
                	
                	regForm.setCurrDateTime(currDate);
                	
                	
				 String msg="";
				 String regFeeChk = "N";
                	String param1[]={RegInitConstant.PAYMENT_FLAG_COMPLETED,
                			session.getAttribute("UserId").toString(),regForm.getHidnRegTxnId(),
                			regForm.getRegInitPaymntTxnId()};
                	boolean txnDetailsInserted = 
                  	  commonBo.insertTxnDetails(param1,regFeeChk);
                	
                	logger.debug("e stamp purpose insertated status: "+txnDetailsInserted);
                	
                	if(txnDetailsInserted)
                		msg=regForm.getHidnRegTxnId().toString();
                	
                	else
                		msg=RegInitConstant.ERROR_MSG;
                	
                	
                 regForm.setActionName(RegInitConstant.NO_ACTION);
                 request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
                 forward = "success1";
				 
			 }
                //COMPLETE TRANSACTION
                if(RegInitConstant.COMPLETE_APPLICATION_ACTION.equals(actionName)) {
                	
                	
                	
                	
                	//boolean completeTransaction = commonBo.completeTransaction(regForm.getHidnRegTxnId());
                	//logger.debug("complete transactiooooon status: "+completeTransaction);
                	//if(completeTransaction){
                		/*request.setAttribute("label", "userSlotbook");
        				request.setAttribute("modName", "Slot");
        				request.setAttribute("from", "regInit");
        				request.setAttribute("regInitId", regForm.getHidnRegTxnId());*/
        				//
                		//cancelAction(session,regForm);
        				//if(map!=null){
        				//if(!map.isEmpty())
        					//map.clear();
        				//}
        				//count=0;
        				//keyCount=0;
        				//forward="welcome";
        				
        				
        				//label=userSlotbook&modName=Slot&from=regInit&regInitId=<%=request.getAttribute("regInitTxnId")%>
        				
        				
        				
        				//forward="bookslot";
        				//return mapping.findForward(forward);
                	//}
                	//else
                		//forward="failure";
                	
                	

                 //forward = "success1";
				 
			 }
                //for creating dashboard
                if(request.getParameter("modName")!=null){
                	if(request.getParameter("modName").equalsIgnoreCase("Registration Process")){
                		cancelAction(session,regForm);
                		ArrayList pendingApplicationList =  commonBo.getPendingRegApps(session.getAttribute("UserId").toString(),regForm.getFromAdjudication(),language);
    					if(pendingApplicationList.size()==0)
    						regForm.setPendingRegApplicationList(null);
    					else
                		regForm.setPendingRegApplicationList(pendingApplicationList);
    					request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
    					forward="reginitDashboard";
    					
    				}
                }
                //after click on any pending application if from dashboard
                if(request.getParameter("hdnApplicationId")!=null){
                	
        			int adjuFlag=0;
                	regForm.setHidnRegTxnId(request.getParameter("hdnApplicationId"));
                	regForm.setHidnUserId(userId);
                	//String appStatus[] = new String[5];
                	
                	try{
                	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                	String purpose=commonBo.getEStampPurpose(regForm.getHidnRegTxnId());
                	if(purpose!=null && !purpose.equalsIgnoreCase("")){
                		regForm.setPurpose(purpose);
                	}
                	
                		
            		if(deedInstArray!=null && deedInstArray.length>0){
            			
            			request.setAttribute("deedId", deedInstArray[0].trim());
            			request.setAttribute("instId", deedInstArray[1].trim());
            			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
            			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
            			if(deedInstArray[2].trim().equals("-")){
            			regForm.setExmpID("");
            			regForm.setHdnExAmount("");
            			}else{
            				regForm.setExmpID(deedInstArray[2].trim());
                			//regForm.setHdnExAmount(deedInstArray[2].trim());
            			}
            			if(deedInstArray[3].trim()!=null && !deedInstArray[3].trim().equalsIgnoreCase("")){
            				adjuFlag=1;
            				regForm.setAdjudicationNumber(deedInstArray[3].trim());
            			}
            			
            		}else {
            			request.setAttribute("deedId", 0);
            			request.setAttribute("instId", 0);
            			regForm.setDeedID(0);
            			regForm.setInstID(0);
            			regForm.setExmpID("");
            			regForm.setHdnExAmount("");
            		}
            		
            		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
            		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
            		//below code for exemptions
            		
            		String exemptions = regForm.getExmpID();
            		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions,language));
            		
            		HashMap propMap =new HashMap();
            		propMap=regForm.getMapPropertyTransParty();
            		logger.debug("in confirmation action----------------------->");
            		ArrayList propertyIdList=new ArrayList();
            		if(adjuFlag==1){
            			propertyIdList=commonBo.getPropertyIdMarketValAdju(regForm.getAdjudicationNumber());
            		}else{
            		    propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
            		}
            		double totalMarketVal=0;
            		
            		int numberOfProperties=propertyIdList.size();
            		
            		for(int i=0;i<propertyIdList.size();i++){
            			
            			ArrayList row_list=new ArrayList();
            			row_list=(ArrayList)propertyIdList.get(i);
            			String propIds=row_list.toString();
            			propIds=propIds.substring(1, propIds.length()-1);
            			logger.debug("property id and market value list-->"+propIds);
            			String propId[]=propIds.split(",");
            			
            			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
            			
            			//Updated by Rakesh for PartyPropMappingDisplay: Start
            			ArrayList transPartyDets=null;
            			String clr_flag=commonBo.getClrFlagByPropId(propId[0].trim());
            			if(clr_flag !=null && !clr_flag.isEmpty()){
            				if(clr_flag.equalsIgnoreCase("Y")){
            				transPartyDets=commonBo.getTransPartyDetsCLR(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            				}else{            					
            			
            				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            				}
            				
            			}
                        else{
            				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                        }
            			
            			//Updated by Rakesh for PartyPropMappingDisplay: End
            			
            			//ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
            			         			
            			
            			for(int j=0;j<transPartyDets.size();j++){
            				
            				CommonDTO dto=new CommonDTO();
            				dto=(CommonDTO)transPartyDets.get(j);
            				logger.debug("transacting party list  role------>"+dto.getRole());
            				logger.debug("transacting party list  name------>"+dto.getName());
            				logger.debug("transacting party list  id------>"+dto.getId());
            			
            			}
            			logger.debug("property id------>"+propId[0].trim());
            			logger.debug("market value------>"+propId[1].trim());
            			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
            	
            		}
            		
            		NumberFormat obj=new DecimalFormat("#0.00");
            		regForm.setTotalMarketValue(obj.format(totalMarketVal));
            		regForm.setMapPropertyTransParty(propMap);
            		
            		String dutyListArr[]=new String[9];
            		
            		if(adjuFlag==1){
            			dutyListArr=commonBo.getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());
            		}
            		else{
            			dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
            		}
            			if(numberOfProperties==1)
            			{
            			regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
            			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
            			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
            			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
            			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
            			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
            			            			
            			regForm.setIsMultiplePropsFlag(0);
                		regForm.setIsDutyCalculated(1);
                		
                		//if(adjuFlag==0){
                			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                			else
                				regForm.setMarketValueShares(Double.toString(0.0));	
                			
                			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                			else
                				regForm.setDutyPaid(Double.toString(0.0));
                			
                			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                			else
                				regForm.setRegPaid(Double.toString(0.0));
                			/*}
                			else{
                				regForm.setMarketValueShares(Double.toString(0.0));	
                				regForm.setDutyPaid(Double.toString(0.0));
                				regForm.setRegPaid(Double.toString(0.0));
                			}*/
                		
            			}else if(numberOfProperties>1 && (dutyListArr==null)){
            				
            				regForm.setIsDutyCalculated(0);
            				regForm.setIsMultiplePropsFlag(1);
            				
            			}else if(numberOfProperties>1 && dutyListArr!=null){
            				
            				regForm.setIsDutyCalculated(1);
            				regForm.setIsMultiplePropsFlag(1);
            				
            				regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
                			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
                			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
                			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
                			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
                			
                			//if(adjuFlag==0){
                			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                			else
                				regForm.setMarketValueShares(Double.toString(0.0));	
                			
                			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                			else
                				regForm.setDutyPaid(Double.toString(0.0));
                			
                			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                			else
                				regForm.setRegPaid(Double.toString(0.0));
                			/*}
                			else{
                				regForm.setMarketValueShares(Double.toString(0.0));	
                				regForm.setDutyPaid(Double.toString(0.0));
                				regForm.setRegPaid(Double.toString(0.0));
                			}*/
            				
            			}
                		
            			
            			if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
            				
            				String finalMV=commonBo.getExchangeDeedFinalMV(regForm.getHidnRegTxnId());
            				
            			regForm.setExchangeDeedMrktValue(obj.format(Double.parseDouble(finalMV)));
            			}
                		regForm.setRegInitPaymntTxnId(null);
            			
                }catch(Exception e){
                	
                	logger.debug(e);
                	forward="failure";
            		return mapping.findForward(forward);	
                	
                }
            		
            		forward="reginitConfirm";
            		return mapping.findForward(forward);	
                	
              }
              //delete application from dashboard
                if(request.getParameter("hdnDelApplicationId")!=null){
                	
                	String appId=request.getParameter("hdnDelApplicationId");
                	
                	boolean applicationDetlsDeltd=commonBo.deleteSelectedAppDetails(appId,0);
                	
                	logger.debug("Registration Initiation Application deleted :- "+applicationDetlsDeltd);
                	if(applicationDetlsDeltd){
                		ArrayList pendingApplicationList =  commonBo.getPendingRegApps(session.getAttribute("UserId").toString(),regForm.getFromAdjudication(),language);
    					if(pendingApplicationList.size()==0)
    						regForm.setPendingRegApplicationList(null);
    					else
                		regForm.setPendingRegApplicationList(pendingApplicationList);
                	}
                	request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
					forward="reginitDashboard";
                	
                }
              //end of delete application from dashboard
                //ADD MULTIPLE PROPERTIES
                if(request.getParameter("multipleProps")!=null){
                	if(request.getParameter("multipleProps").equalsIgnoreCase("Y")){
                		
                		regForm.setIsMultiplePropsFlag(1);
                		request.setAttribute("multipleProps", "multipleProps");
                		forward="propval";
                		
                	}
                		
                	
                }
                //END
              //NEXT TO CONFIRMATION SCREEN
                if(request.getParameter("confirmation")!=null){/*
                	if(request.getParameter("confirmation").equalsIgnoreCase("Y")){
                		
                		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                		if(deedInstArray!=null && deedInstArray.length>0){
                			
                			request.setAttribute("deedId", deedInstArray[0].trim());
                			request.setAttribute("instId", deedInstArray[1].trim());
                			
                			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			//regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                			
                		}else {
                			request.setAttribute("deedId", 0);
                			request.setAttribute("instId", 0);
                			regForm.setDeedID(0);
                			regForm.setInstID(0);
                			regForm.setExmpID("");
                			regForm.setHdnExAmount("");
                		}
                		
                		logger.debug("deed id"+request.getAttribute("deedId"));
                		logger.debug("inst id"+request.getAttribute("instId"));
                		
                		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID())));
                		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID())));
                		//below code for exemptions
                		
                		String exemptions = regForm.getExmpID();
                		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions));
                		
                		
                		HashMap propMap =new HashMap();
                		propMap=regForm.getMapPropertyTransParty();
                		logger.debug("in confirmation action----------------------->");
                		
                		ArrayList propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
                		double totalMarketVal=0;
                		for(int i=0;i<propertyIdList.size();i++){
                			
                			ArrayList row_list=new ArrayList();
                			row_list=(ArrayList)propertyIdList.get(i);
                			String propIds=row_list.toString();
                			propIds=propIds.substring(1, propIds.length()-1);
                			logger.debug("property id and market value list-->"+propIds);
                			String propId[]=propIds.split(",");
                			
                			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
                			
                			
                			
                			ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId());
                			         			
                			
                			for(int j=0;j<transPartyDets.size();j++){
                				
                				CommonDTO dto=new CommonDTO();
                				dto=(CommonDTO)transPartyDets.get(j);
                				logger.debug("transacting party list  role------>"+dto.getRole());
                				logger.debug("transacting party list  name------>"+dto.getName());
                				logger.debug("transacting party list  id------>"+dto.getId());
                			
                			}
                			logger.debug("property id------>"+propId[0].trim());
                			logger.debug("market value------>"+propId[1].trim());
                			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
                	
                		}
                		
                		NumberFormat obj=new DecimalFormat("#0.00");
                		regForm.setTotalMarketValue(obj.format(totalMarketVal));
                		regForm.setMapPropertyTransParty(propMap);
                		
                		//FOLLOWING CODE FOR DUTY.
                		if(regForm.getIsMultiplePropsFlag()!=1){
                			//IN CASE OF SINGLE PROPERTY
                			String dutyListArr[]=commonBo.getDutyDetls(regForm.getHidnRegTxnId());
                			
                			regForm.setStampduty1(dutyListArr[0].trim());
                			regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                			regForm.setPanchayatDuty(dutyListArr[1].trim());
                			regForm.setUpkarDuty(dutyListArr[3].trim());
                			regForm.setTotalduty(dutyListArr[5].trim());
                			regForm.setRegistrationFee(dutyListArr[4].trim());
                			
                			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                    			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                    			else
                    				regForm.setMarketValueShares(Double.toString(0.0));	
                    			
                    			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                    			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                    			else
                    				regForm.setDutyPaid(Double.toString(0.0));
                    			
                    			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                    			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                    			else
                    				regForm.setRegPaid(Double.toString(0.0));
                			
                			String[] param={regForm.getHidnRegTxnId(),dutyListArr[0].trim(),dutyListArr[1].trim(),
                					         dutyListArr[2].trim(),dutyListArr[3].trim(),dutyListArr[4].trim(),
                					         dutyListArr[5].trim(),session.getAttribute("UserId").toString()};
                			
                			
                			boolean stampDutiesInserted=false;
                		//	stampDutiesInserted=commonBo.insertStampDuties(regForm,session.getAttribute("UserId").toString());
                			
                			if(stampDutiesInserted){
                			regForm.setStampduty1(dutyListArr[0].trim());
                			regForm.setNagarPalikaDuty(dutyListArr[2].trim());
                			regForm.setPanchayatDuty(dutyListArr[1].trim());
                			regForm.setUpkarDuty(dutyListArr[3].trim());
                			regForm.setTotalduty(dutyListArr[5].trim());
                			regForm.setRegistrationFee(dutyListArr[4].trim());
                			}else{
                				regForm.setStampduty1("-");
                    			regForm.setNagarPalikaDuty("-");
                    			regForm.setPanchayatDuty("-");
                    			regForm.setUpkarDuty("-");
                    			regForm.setTotalduty("-");
                    			regForm.setRegistrationFee("-");
                			}
                			regForm.setIsDutyCalculated(1);
                		}
                		
                		forward="reginitConfirm";
                	}
                */}
                if(RegInitConstant.CALCULATE_DUTIES_ACTION.equals(actionName)) {
                	
                	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
            		if(deedInstArray!=null && deedInstArray.length>0){
            			
            			request.setAttribute("deedId", deedInstArray[0].trim());
            			request.setAttribute("instId", deedInstArray[1].trim());
            			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
            			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
            			if(deedInstArray[2].trim().equals("-")){
                			regForm.setExmpID("");
                			regForm.setHdnExAmount("");
                			}else{
                				regForm.setExmpID(deedInstArray[2].trim());
                				
                				String exemp=deedInstArray[2].trim().replace("-", ",");
                				exemp=exemp.substring(exemp.length()-1);
                				
                    			regForm.setHdnExAmount(exemp);
                			}
            			
            		}else {
            			request.setAttribute("deedId", 0);
            			request.setAttribute("instId", 0);
            			regForm.setDeedID(0);
            			regForm.setInstID(0);
            			regForm.setExmpID("");
            			regForm.setHdnExAmount("");
            		}
            		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
            		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
            		//below code for exemptions
            		
            		String exemptions = regForm.getExmpID();
            		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions,language));
                	
               	NumberFormat obj=new DecimalFormat("#0.00");
            			//FOLLOWING CODE FOR DUTY.
            	
            			//IN CASE OF MULTIPLE PROPERTIES
            		double dutyArray[] = new double[3];
			     	double nagarPalikaDuty = 0.0;
			     	double panchayatDuty = 0.0;
			     	double upkarDuty = 0.0;
			     	double total = 0.0;
            		double stampDuty=0.0;
            		double regFee=0.0;
            		stampDuty=commonBo.getDutyCalculation(regForm);
            		
            		logger.debug("after first procedure of duty calculation");
            		
            			regForm.setStampduty1(obj.format(stampDuty));
            			
            		    dutyArray=commonBo.getMunicipalDutyCalculation(regForm);
            		    logger.debug("after second procedure of duty calculation");
            		    if (dutyArray.length >= 1) {
    						nagarPalikaDuty = (dutyArray[0]);
    						panchayatDuty = (dutyArray[1]);
    						upkarDuty = (dutyArray[2]);
    			    	}
            			total = nagarPalikaDuty + panchayatDuty + upkarDuty + stampDuty;
            			regForm.setNagarPalikaDuty(obj.format(nagarPalikaDuty));
            			regForm.setPanchayatDuty(obj.format(panchayatDuty));
            			regForm.setUpkarDuty(obj.format(upkarDuty));
            			regForm.setTotalduty(obj.format(total));
            			
            			regFee = commonBo.getRegistrationFee(regForm);
            			logger.debug("after third procedure of duty calculation");
            			regForm.setRegistrationFee(obj.format(regFee));
            	
            			//below code for inserting duties into reg init table
            			/*String[] param={regForm.getHidnRegTxnId(),regForm.getStampduty1(),regForm.getPanchayatDuty(),
            					regForm.getNagarPalikaDuty(),regForm.getUpkarDuty(),regForm.getRegistrationFee(),
            					regForm.getTotalduty(),session.getAttribute("UserId").toString()};*/
            			//String[] param={regForm,session.getAttribute("UserId").toString()};
   			
   			
   			boolean stampDutiesInserted=false;
   			//stampDutiesInserted=commonBo.insertStampDuties(regForm,session.getAttribute("UserId").toString());
            		
   			logger.debug("multiple properties duties insertion status:--"+stampDutiesInserted);
            			
            			
            			
            		regForm.setIsDutyCalculated(1);
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		forward="reginitConfirm";
                }
                if(request.getParameter("label")!=null){
                	
                	if (request.getParameter("label").equalsIgnoreCase(
					"displayConsenter")) {

				/*if (request.getParameter("fromPage") != null
						&& request.getParameter("fromPage").toString()
								.equalsIgnoreCase("consenterDetls")) {*/
					regForm.setConfirmationFlag("00");
				/*} else {
					regForm.setConfirmationFlag("01");
				}*/

				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, language);

				forward = "displayConsenterDetls";
			}
                	if (request.getParameter("label").equalsIgnoreCase(
					"displaySlotConsenter")) {

				/*if (request.getParameter("fromPage") != null
						&& request.getParameter("fromPage").toString()
								.equalsIgnoreCase("consenterDetls")) {*/
					regForm.setConfirmationFlag("00");
					regForm.setHidnRegTxnId(request.getParameter("regTxnId"));
					regForm.setConsenterWithConsidFlag(request.getParameter("consenterFlag"));
				/*} else {
					regForm.setConfirmationFlag("01");
				}*/

				regForm.setPartyModifyFlag(0);
					
				commonBo.openConsenterView(request, regForm, language);

				forward = "displayConsenterDetls";
			}
                	
                	if (request.getParameter("label").equalsIgnoreCase("displayConsenterDuties")) {

        				commonBo.getConsenterDutiesView(regForm, language);

        				forward = "displayConsenterDuties";
        			}
                	
                	if(request.getParameter("label").equalsIgnoreCase("displayParty")){
                		RegCommonBD comBD = new RegCommonBD();
                		String partyId=request.getParameter("key");
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("roleId",request.getParameter("role"));
                		request.setAttribute("instId",regForm.getInstID());
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        logger.debug("request role ---->"+request.getAttribute("roleId"));
                        
                        
                        String roleIdNew = request.getParameter("role");
if(partyId!=null && roleIdNew!=null){
            				
            				if(roleIdNew.equalsIgnoreCase("50004")){
            					
            					String poaPartyId = comBD.getPoaPartyTxnId(partyId);
            					
            					if(poaPartyId==null || poaPartyId.isEmpty()){
            						
            						partyId=request.getParameter("key");
            					}
            					else{
            					partyId = poaPartyId;
            					
            					}
            				}
            				
            			}
                        
                        String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
                        
            			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
            				
            				if(flags[2].trim().equalsIgnoreCase("Y"))
            				{regForm.setCommonDeed(1);}
            				else
            				{regForm.setCommonDeed(0);}
            				
            				regForm.setPvReqFlag(flags[1].trim());
            				regForm.setPropReqFlag(flags[0].trim());
            				
            				
            			}else{
            				regForm.setCommonDeed(0);
            				regForm.setPvReqFlag("");
            				regForm.setPropReqFlag("");
            			}
            			String regNumber="";
            			if(regForm.getHidnRegTxnId().length()==12)
            			{
            				regNumber=regForm.getHidnRegTxnId();
            			}
            			else
            			{
            				regNumber="0"+regForm.getHidnRegTxnId();
            			}
            				
                        regForm.setMapTransactingPartiesDisp
                		(commonBo.getPartyDetsForViewConfirm(regNumber, partyId,regForm.getDeedID(),regForm.getPropReqFlag(),language,regForm.getInstID()));
                	
                		regForm.setConfirmationFlag("01");
                		
                		forward="displayRegDetls";
                	}
                	//added by Shreeraj
                	if(request.getParameter("label").equalsIgnoreCase("displayPropertyInspectionReport")){

                		
                		String propertyId=request.getParameter("key");
                		regForm.setInstID(Integer.parseInt(boMaker.getInstdFromProp(propertyId)));
                		
                		String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
            			
            			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
            				
            				if(flags[2].trim().equalsIgnoreCase("Y"))
            				{regForm.setCommonDeed(1);}
            				else
            				{regForm.setCommonDeed(0);}
            				
            				regForm.setPvReqFlag(flags[1].trim());
            				regForm.setPropReqFlag(flags[0].trim());
            				
            				
            			}else{
            				regForm.setCommonDeed(0);
            				regForm.setPvReqFlag("");
            				regForm.setPropReqFlag("");
            			}
                		
                		
                		regForm.setCheck("n");
                		//Start added By ankita 
                		/*
                		 * This code is added for redirecting from linking History page*/
                		if(request.getParameter("hidnregId")!=null &&
                				!(request.getParameter("hidnregId")).equalsIgnoreCase("")){
                		String num = request.getParameter("hidnregId");
                		String mod="";
                		String hidnregId;
                		
                		if(request.getParameter("mod")!=null &&
                				!(request.getParameter("mod")).equalsIgnoreCase(""))
                		{
                			 mod = request.getParameter("mod");
                		}
                		if(mod.equalsIgnoreCase("maker"))
                		{
                			 hidnregId = num;
                		}
                		else{
                		RegCompMkrBD regmkrBD = new RegCompMkrBD();
                		 hidnregId = regmkrBD.getreginitNumber(num);
                		}
                		if(!hidnregId.equalsIgnoreCase("") && hidnregId!=null)
                		{
                			regForm.setHidnRegTxnId(hidnregId);
                		}
                		}
                		//End added By shreeraj
                		
                		if(regForm.getPvReqFlag().equalsIgnoreCase("Y")){
                			String regNumber="";
                			if(regForm.getHidnRegTxnId().length()==12)
                			{
                				regNumber=regForm.getHidnRegTxnId();
                			}
                			else
                			{
                				regNumber="0"+regForm.getHidnRegTxnId();
                			}
                			regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId,language));
                		
                		//regForm.setConfirmationFlag("01");
                		
                		forward="propertyViewInspectionReport";
                		}else{
                		
                			String regNumber="";
                			if(regForm.getHidnRegTxnId().length()==12)
                			{
                				regNumber=regForm.getHidnRegTxnId();
                			}
                			else
                			{
                				regNumber="0"+regForm.getHidnRegTxnId();
                			}
                		regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId,language));
                		
                	// Added by Rakesh for CLR Property View
					//String propertyId = request.getParameter("key");
					ArrayList<String> propdet = commonBo
							.getPropertyTypeIdAndClrFlag(propertyId);
					String propertyTypeId = "";
					String clrFlag = "";
					for (int i = 0; i < propdet.size(); i++) {
						propertyTypeId = propdet.get(0);
						clrFlag = propdet.get(1);

					}
					if (clrFlag != null && propertyTypeId != null) {
						if (clrFlag.equalsIgnoreCase("Y")
								&& propertyTypeId.equalsIgnoreCase("3")) {
							// ArrayList<String> clrKhasraAllDtls=
							// commonBo.getPropDetlsForDisplayClr(propertyId);

							forward = "propertyViewNonPVClr";
						} else {
							forward = "propertyViewNonPV";
						}

					} else {
						forward = "propertyViewNonPV";
					}

					// Added by Rakesh for CLR Property View            		
                		              		                		
                	//forward="propertyViewNonPV";
                		}
                	
                	}
                	//added by Shubham
if(request.getParameter("label").equalsIgnoreCase("displayPartySlotReport")){
                		
                		String partyId=request.getParameter("key");
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("roleId",request.getParameter("role"));
                		request.setAttribute("instId",regForm.getInstID());
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        logger.debug("request role ---->"+request.getAttribute("roleId"));
                        regForm.setInstID(Integer.parseInt(request.getParameter("instID")));
                        regForm.setHidnRegTxnId(request.getParameter("reginitid"));
                        String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
                        
            			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
            				
            				if(flags[2].trim().equalsIgnoreCase("Y"))
            				{regForm.setCommonDeed(1);}
            				else
            				{regForm.setCommonDeed(0);}
            				
            				regForm.setPvReqFlag(flags[1].trim());
            				regForm.setPropReqFlag(flags[0].trim());
            				
            				
            			}else{
            				regForm.setCommonDeed(0);
            				regForm.setPvReqFlag("");
            				regForm.setPropReqFlag("");
            			}
            			String regNumber="";
            			if(regForm.getHidnRegTxnId().length()==12)
            			{
            				regNumber=regForm.getHidnRegTxnId();
            			}
            			else
            			{
            				regNumber="0"+regForm.getHidnRegTxnId();
            			}
            				
                        regForm.setMapTransactingPartiesDisp
                		(commonBo.getPartyDetsForViewConfirm(regNumber, partyId,regForm.getDeedID(),regForm.getPropReqFlag(),language,regForm.getInstID()));
                	
                		regForm.setConfirmationFlag("01");
                		
                		forward="displayRegDetls";
                	}
    					
    					if(request.getParameter("label").equalsIgnoreCase("retrievalInspectionReport")){
    						String filePath = "";
                    		if(request.getParameter("path")!=null){
                    			
                    			String partyType="";
                    			
                    			String filename = request.getParameter("path").toString();
                 			  
                 			  byte[] contents =null;
                 	            contents=DMSUtility.getDocumentBytes(filename);
                 	           downloadDocument(response, contents, filename);
                 	          forward="propertyViewInspectionReport";
                    		}
                    		
                    	}
               
                	if(request.getParameter("label").equalsIgnoreCase("displayProperty")){
                		regForm.setClrRcmsFlag("");
                		String propertyId ="";
                		 propertyId=request.getParameter("key");
                		regForm.setInstID(Integer.parseInt(boMaker.getInstdFromProp(propertyId)));
                		
                		String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
            			
                		String propTypeID = "";
               		 propTypeID = commonBo
						.getPropertyTypeID(propertyId);
				         regForm.setPropertyTypeID(propTypeID);
				
               		if (regForm.getPropertyTypeID().equalsIgnoreCase("3")) {
       					
       					String clrRcmsFlag = "N";
       					
       					clrRcmsFlag = commonBo.getClrRCMSFlag(propertyId);
       					
       					if(clrRcmsFlag.equalsIgnoreCase("y")){
       						
       						regForm.setClrRcmsFlag(clrRcmsFlag);
       					}
       					
       				
       				}
                		
                		
            			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
            				
            				if(flags[2].trim().equalsIgnoreCase("Y"))
            				{regForm.setCommonDeed(1);}
            				else
            				{regForm.setCommonDeed(0);}
            				
            				regForm.setPvReqFlag(flags[1].trim());
            				regForm.setPropReqFlag(flags[0].trim());
            				
            				
            			}else{
            				regForm.setCommonDeed(0);
            				regForm.setPvReqFlag("");
            				regForm.setPropReqFlag("");
            			}
                		
                		
                		regForm.setCheck("n");
                		//Start added By ankita 
                		/*
                		 * This code is added for redirecting from linking History page*/
                		if(request.getParameter("hidnregId")!=null &&
                				!(request.getParameter("hidnregId")).equalsIgnoreCase("")){
                		String num = request.getParameter("hidnregId");
                		String mod="";
                		String hidnregId;
                		
                		if(request.getParameter("mod")!=null &&
                				!(request.getParameter("mod")).equalsIgnoreCase(""))
                		{
                			 mod = request.getParameter("mod");
                		}
                		if(mod.equalsIgnoreCase("maker"))
                		{
                			 hidnregId = num;
                		}
                		else{
                		RegCompMkrBD regmkrBD = new RegCompMkrBD();
                		 hidnregId = regmkrBD.getreginitNumber(num);
                		}
                		if(!hidnregId.equalsIgnoreCase("") && hidnregId!=null)
                		{
                			regForm.setHidnRegTxnId(hidnregId);
                		}
                		}
                		//End added By ankita 
                		
                		// added by simran for checklist
                		
                		if(request.getParameter("param")!= null)
                		{
                			
                			if(request.getParameter("param").equals("checklist"))
                			{
                				String num  = request.getParameter("num");
                				RegCompMkrBD regmkrBD = new RegCompMkrBD();
                				String hidnregId = regmkrBD.getreginitNumber(num);
                				if(!hidnregId.equalsIgnoreCase("") && hidnregId!=null)
                        		{
                        			regForm.setHidnRegTxnId(hidnregId);
                        		}
                				regForm.setCheck("checkList");
                			}
                			else if(request.getParameter("param").equals("linkHstry")){
                				String num = request.getParameter("hidnregId");
                				RegCompMkrBD regmkrBD = new RegCompMkrBD();
                				String hidnregId = regmkrBD.getreginitNumber(num);
                				if(!hidnregId.equalsIgnoreCase("") && hidnregId!=null)
                        		{
                        			regForm.setHidnRegTxnId(hidnregId);
                        		}
                				regForm.setCheck("linkHstry");
                			}
                		}
                		
                		if(regForm.getPvReqFlag().equalsIgnoreCase("Y")){
                			String regNumber="";
                			if(regForm.getHidnRegTxnId().length()==12)
                			{
                				regNumber=regForm.getHidnRegTxnId();
                			}
                			else
                			{
                				regNumber="0"+regForm.getHidnRegTxnId();
                			}
                			regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId,language));
                			
                			// Added by Rakesh for CLR Property View
                            //String propertyId=request.getParameter("key");
                			System.out.println("PropID:"+ request.getParameter("key"));
                            ArrayList propdet= commonBo.getPropertyTypeIdAndClrFlag(propertyId);
                            String propertyTypeId="";
                            String clrFlag="";
                            for (int i = 0; i < propdet.size(); i++) {
                            	ArrayList subList=(ArrayList)propdet.get(i);
                            	propertyTypeId = (String)subList.get(0);
    							clrFlag = (String)subList.get(1);
            				
                           	}                                  
                            
                            if(clrFlag !=null && propertyTypeId !=null){
                        	if(clrFlag.equalsIgnoreCase("Y")&& propertyTypeId.equalsIgnoreCase("3")){                			
                        	/*ArrayList khasraDetails= commonBo.getPropDetlsForDisplayClr(propertyId);                       	
                        	                        	                        	
							
							ArrayList subList;
							for (int i = 0; i < khasraDetails.size(); i++) {
								subList = (ArrayList) khasraDetails.get(i);
								regForm.setClrFlg(subList.get(1).toString());
								regForm.getRegcompletDto().setRicircle(subList.get(1).toString());
								regForm.getRegcompletDto().setClrkhasraNo(subList.get(2).toString());					
								regForm.getRegcompletDto().setClrKhasraArea(subList.get(3).toString());									
								regForm.getRegcompletDto().setNorthKhasraNo(subList.get(4).toString());
								regForm.getRegcompletDto().setSouthKhasraNo(subList.get(5).toString());
								regForm.getRegcompletDto().setEastKhasraNo(subList.get(6).toString());
								regForm.getRegcompletDto().setWestKhasraNo(subList.get(7).toString());
								regForm.getRegcompletDto().setMapPathClr(subList.get(8).toString());
								regForm.getRegcompletDto().setKhasraPathClr(subList.get(9).toString());
							}
							//clr party owner details display: Start
							ArrayList partyDetails= commonBo.getPartyDetlsForDisplayClr(regForm.getRegcompletDto().getClrkhasraNo(),propertyId);                        	                        	                       	
							regForm.getRegcompletDto().setClrOwnerArray(partyDetails);
							ArrayList<RegCompletDTO> paramList1 = new ArrayList<RegCompletDTO>();	
							
							
							
							RegCompletDTO paramDto1 ;
							ArrayList subList2;
							for (int i = 0; i < partyDetails.size(); i++) {
								//row_list = (RegCompletDTO) ownerDetails.get(i);
								subList2 = (ArrayList) partyDetails.get(i);
								//Owner ow = (Owner) partyDetails.get(i);							
								paramDto1 = new RegCompletDTO();
								paramDto1.setPartyDetails(subList2.get(0).toString());							
								paramDto1.setCasteOfParty(subList2.get(1).toString());                        
								paramDto1.setShareOfParty(subList2.get(2).toString());                         
								                                              
								paramList1.add(paramDto1);
								
							}
							regForm.setClrOwnerArray(paramList1);
							//clr party owner details display: Start                    	                        	                       	
                        	                        	*/
                        		
                        			forward="propertyViewClr";
                        		}else{
                        			forward="propertyView";
                        		}
                        		
                        		
                        	}else{
                        		forward="propertyView";
                        	}
                            
                            
                            
                            // Added by Rakesh for CLR Property View
                			
                			
                			
                		
                		//regForm.setConfirmationFlag("01");
                		
                		//forward="propertyView";
                		//forward="propertyViewClr";
                		
                		}else{
                		
                			String regNumber="";
                			if(regForm.getHidnRegTxnId().length()==12)
                			{
                				regNumber=regForm.getHidnRegTxnId();
                			}
                			else
                			{
                				regNumber="0"+regForm.getHidnRegTxnId();
                			}
                		regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirm(regNumber, propertyId,language));
                		
                		
                		// Added by Rakesh for CLR Non Property View
                        //String propertyId=request.getParameter("key");
                        ArrayList propdet= commonBo.getPropertyTypeIdAndClrFlag(propertyId);
                        String propertyTypeId="";
                        String clrFlag="";
                        for (int i = 0; i < propdet.size(); i++) {
                        	ArrayList subList=(ArrayList)propdet.get(i);
                        	propertyTypeId = (String)subList.get(0);
							clrFlag = (String)subList.get(1);
        				
                       	}                                  
                        
                        if(clrFlag !=null && propertyTypeId !=null){
                    	if(clrFlag.equalsIgnoreCase("Y")&& propertyTypeId.equalsIgnoreCase("3")){
                    	                			
                    	/*ArrayList khasraDetails= commonBo.getPropDetlsForDisplayClr(propertyId);                       	
                    	                        	                        	
						
						ArrayList subList;
						for (int i = 0; i < khasraDetails.size(); i++) {
							subList = (ArrayList) khasraDetails.get(i);
							regForm.setClrFlg(subList.get(1).toString());
							regForm.getRegcompletDto().setRicircle(subList.get(1).toString());
							regForm.getRegcompletDto().setClrkhasraNo(subList.get(2).toString());						
							regForm.getRegcompletDto().setClrKhasraArea(subList.get(3).toString());								
							regForm.getRegcompletDto().setNorthKhasraNo(subList.get(4).toString());
							regForm.getRegcompletDto().setSouthKhasraNo(subList.get(5).toString());
							regForm.getRegcompletDto().setEastKhasraNo(subList.get(6).toString());
							regForm.getRegcompletDto().setWestKhasraNo(subList.get(7).toString());
							regForm.getRegcompletDto().setMapPathClr(subList.get(8).toString());
							regForm.getRegcompletDto().setKhasraPathClr(subList.get(9).toString());
							
							
							
							
						}
						
						//clr party owner details display: Start
						ArrayList partyDetails= commonBo.getPartyDetlsForDisplayClr(regForm.getRegcompletDto().getClrkhasraNo(),propertyId);                        	                        	                       	
						regForm.getRegcompletDto().setClrOwnerArray(partyDetails);
						ArrayList<RegCompletDTO> paramList1 = new ArrayList<RegCompletDTO>();	
						
						
						
						RegCompletDTO paramDto1 ;
						ArrayList subList2;
						for (int i = 0; i < partyDetails.size(); i++) {
							//row_list = (RegCompletDTO) ownerDetails.get(i);
							subList2 = (ArrayList) partyDetails.get(i);
							//Owner ow = (Owner) partyDetails.get(i);							
							paramDto1 = new RegCompletDTO();
							paramDto1.setPartyDetails(subList2.get(0).toString());							
							paramDto1.setCasteOfParty(subList2.get(1).toString());                        
							paramDto1.setShareOfParty(subList2.get(2).toString());                         
							                                              
							paramList1.add(paramDto1);
							
						}
						regForm.setClrOwnerArray(paramList1);
						//clr party owner details display: Start
						
                    		*/
                    			forward="propertyViewNonPVClr";
                    		}else{
                    			forward="propertyViewNonPV";
                    		}
                    		
                    		
                    	}else{
                    		forward="propertyViewNonPV";
                    	}
                        
                        
                        
                        // Added by Rakesh for CLR Non Property View           			
                		                		
                		
                		
                		//forward="propertyViewNonPV";
                		}
                	}
                	if(request.getParameter("label").equalsIgnoreCase("displayDuties")){
                		
                		logger.debug("*********************** display duties");
                		
                		
                		regForm.setInstID(Integer.parseInt((boMaker.getInst(regForm.getHidnRegTxnId()))));
                		regForm.setDeedID(Integer.parseInt(boMaker.getDeed(regForm.getHidnRegTxnId())));
                		logger.debug("*********************** display duties"+regForm.getDeedID());
                		logger.debug("*********************** display duties"+regForm.getInstID());
                		String flags[]=commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("roleId",request.getParameter("role"));
            			if(flags!=null && flags[0]!=null && flags[1]!=null && flags[2]!=null){
            				
            				if(flags[2].trim().equalsIgnoreCase("Y"))
            				{regForm.setCommonDeed(1);}
            				else
            				{regForm.setCommonDeed(0);}
            				
            				regForm.setPvReqFlag(flags[1].trim());
            				regForm.setPropReqFlag(flags[0].trim());
            				
            				
            			}else{
            				regForm.setCommonDeed(0);
            				regForm.setPvReqFlag("");
            				regForm.setPropReqFlag("");
            			}
            			logger.debug("*********************** display duties"+regForm.getPvReqFlag());
                		commonBo.getDutiesView(regForm,language);
                		//request.setAttribute("reginit", regForm);	
                		
                		forward="dutyView";
                	}
                	
                	if(request.getParameter("label").equalsIgnoreCase("validateDeedDoc"))
                	{
                		logger.debug("*************************validate***********************");
                		
                		String flag = boMaker.validateDeedDoc(regForm.getDeedDraftId());
                		logger.debug("flag---------->"+flag);
                		if(flag.equals("0"))
                			regForm.setDeedStat("3");
                		else if(flag.equals("1"))
                			regForm.setDeedStat("0");
                		else if (flag.equals("3"))
                			regForm.setDeedStat("1");
                		else
                			regForm.setDeedStat("2");
                		regForm.setDeedDoc("N");
                		regForm.setPdfFlag("");
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("instId",regForm.getInstID());
                		//request.setAttribute("reginit", regForm);
                		forward = regForm.getForwardJsp();
                	}
                	if(request.getParameter("label").equalsIgnoreCase("changeDeedDoc"))
                	{
                		logger.debug("change deed doc");
                		
                		
                		regForm.setDeedStat("4");
                		regForm.setDeedDoc("N");
                		regForm.setDeedDocName("");
                		regForm.setDeedDocPath("");
                		regForm.setDeedDraftId("");
                		regForm.setPdfFlag("N");
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("instId",regForm.getInstID());
                		//request.setAttribute("reginit", regForm);
                		forward = regForm.getForwardJsp();
                		
                	}
                	if(request.getParameter("label").equalsIgnoreCase("consumeDeedDoc"))
                	{
                		logger.debug("consume deed doc");
                		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
    					
						String path=pr.getValue("pdf.location");
                		String flag = boMaker.validateDeedDoc(regForm.getDeedDraftId());
                		if(flag.equals("0"))
                			regForm.setDeedStat("3");
                		else if(flag.equals("1"))
                			regForm.setDeedStat("0");
                		else if (flag.equals("3"))
                			regForm.setDeedStat("1");
                		else
                			{
                			//update status of deed_draft_id and reg_txn_detls
                			boolean flag1 = boMaker.checkConsumptionOfDeedDoc(regForm.getDeedDraftId(),RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC ,RegCompConstant.FILE_NAME_DEED_DOC, regForm.getHidnRegTxnId());
                    		///if(!flag1)
                    		//{
                    			//consumed
                    			//regForm.setDeedStat("1");
                    		//}
                    		//else
                    		//{
                    			regForm.setDeedStat("5");
                    			regForm.setDeedDoc("Y");
                    			if(regForm.getPdfFlag().equals("Y"))
                    			{
                    				String filename = regForm.getDeedDocPath();
                        			byte[] contents =null;
                      	            contents=DMSUtility.getDocumentBytes(filename);
                      	           downloadDocument(response, contents, filename);
                    			}
                    			else
                    			{
                    				DeedDraftBD deedBD = new DeedDraftBD();
                    				byte arr[];
                    				//byte[] propDetlsBytes = deedBD.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit for prop val pdf
                    				//byte arr[] = deedBD.generateDeedPDF(path, regForm.getDeedDraftId(), response,1,propDetlsBytes); //change by ankit for prop val pdf
                    				
                    				String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
                    				String propReq = flags[0];
                    				String propValReq = flags[1];
                    				String commonDeed = flags[2];
                    				  if (propReq.equalsIgnoreCase("Y")
                    							&& (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N"))
                    							&& commonDeed.equalsIgnoreCase("N")) {
                    					   arr = 	deedBD.newDeedPDF(regForm,path, regForm.getDeedDraftId(), response, 1,language);
                    				  }else{
                    					   arr =deedBD.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 1);  //changed by ankit for prop val pdf 
                    				  }
                    				//change by ankit for prop val pdf
                    				String filePath = uploadFile(regForm.getHidnRegTxnId(), arr);
                    				/* path = RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC;*/
                    				regForm.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC);
                         			regForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
                    				 // 1 consumption pdf will not open
                    				regForm.setPdfFlag("Y");
                    			}
                    		//}
                			}
                		
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("instId",regForm.getInstID());
                		forward = regForm.getForwardJsp();
                		
                	}
                	if(request.getParameter("label").equalsIgnoreCase("viewDeedDoc"))
                	{
                		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
    					
						String path=pr.getValue("pdf.location");
                		if(regForm.getPdfFlag().equals("Y"))
            			{
                			String filename = regForm.getDeedDocPath();
                			byte[] contents =null;
              	            contents=DMSUtility.getDocumentBytes(filename);
              	           downloadDocument(response, contents, filename);
            			}
            			else
            			{
            				DeedDraftBD deedBD = new DeedDraftBD();
            				byte arr[];
            				//byte[] propDetlsBytes = deedBD.propDetlsPdf(regForm.getHidnRegTxnId()); //added by ankit for prop val pdf
            				//byte arr[] = deedBD.generateDeedPDF(path, regForm.getDeedDraftId(), response,0,propDetlsBytes);  // pdf will open //change by ankit for prop val pdf
            				
            				String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
            				String propReq = flags[0];
            				String propValReq = flags[1];
            				String commonDeed = flags[2];
            				  if (propReq.equalsIgnoreCase("Y")
            							&& (propValReq.equalsIgnoreCase("Y") || propValReq.equalsIgnoreCase("N"))
            							&& commonDeed.equalsIgnoreCase("N")) {
            					arr =  deedBD.newDeedPDF(regForm,path, regForm.getDeedDraftId(), response, 0,language);
            				  }else{
            					  arr=deedBD.generateDeedPDFOLD(path, regForm.getDeedDraftId(), response, 0);  //changed by ankit for prop val pdf 
            				  }
            				
            				String filePath = uploadFile(regForm.getHidnRegTxnId(), arr);
            				/*String path = RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC;*/
            				regForm.setDeedDocPath(RegCompConstant.FILE_UPLOAD_PATH+regForm.getHidnRegTxnId()+RegCompConstant.UPLOAD_PATH_DEED_DOC+RegCompConstant.FILE_NAME_DEED_DOC);
                 			regForm.setDeedDocName(RegCompConstant.FILE_NAME_DEED_DOC);
            				
            				regForm.setPdfFlag("Y");
            			}
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("instId",regForm.getInstID());
                		forward = regForm.getForwardJsp();
                	}
                	
                	//below commented by roopam. 2 June 2014
                	if(request.getParameter("label").equalsIgnoreCase("displayPropertyNonPV")){/*
                		regForm.setCheck("n");
                		String propertyId=request.getParameter("key");
                		String num ="";
                		if(request.getParameter("hidnregId")!=null &&
                				!(request.getParameter("hidnregId")).equalsIgnoreCase("")){
                		num = request.getParameter("hidnregId");
                		}
                		else
                			num = regForm.getHidnRegTxnId();
                		
                		if(request.getParameter("param")!= null)
                		{
                			
                			if(request.getParameter("param").equals("checklist"))
                			{
                				num  = request.getParameter("num");
                				RegCompMkrBD regmkrBD = new RegCompMkrBD();
                				num = regmkrBD.getreginitNumber(num);
                				if(!num.equalsIgnoreCase("") && num!=null)
                        		{
                        			regForm.setHidnRegTxnId(num);
                        		}
                				regForm.setCheck("checkList");
                			}
                			else if(request.getParameter("param").equals("linkHstry")){
                				num = request.getParameter("hidnregId");
                				RegCompMkrBD regmkrBD = new RegCompMkrBD();
                				num = regmkrBD.getreginitNumber(num);
                				if(!num.equalsIgnoreCase("") && num!=null)
                        		{
                        			regForm.setHidnRegTxnId(num);
                        		}
                				regForm.setCheck("linkHstry");
                			}
                		}
                		
                		//regForm.setConfirmationFlag("01");
                		regForm.setPropertyId(propertyId);  		
                		regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirmNonProp(num, propertyId,language));
                		forward="propertyViewNonPV";
                	*/}
                	
                	if(request.getParameter("label").equalsIgnoreCase("displayParticular")){
                		
                		String particularId=request.getParameter("key");
                		regForm.setParticularTxnId(particularId);
                		commonBo.getParticularDetails(regForm);
                		request.setAttribute("deedId",regForm.getDeedID());
                		                		
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        
                		forward="displayParticularDetls";
                	}
                	
                	if(request.getParameter("label").equalsIgnoreCase("fromAdjudication")){
                   		forward="searchAdju";
                	}
                }
                //END
                //following code for skipping applicant details page in case of multiple properties.
                if(request.getParameter("modName")!=null){
    				if(request.getParameter("modName").equalsIgnoreCase("RegistrationProperty")&& request.getAttribute("regFormDashboard")==null){
    					if(regForm.getIsMultiplePropsFlag()==1)
    					{
    						//FOLLOWING CODE FOR INSERTING APPLICANT DETAILS IN HASHMAP
    						map=regForm.getMapTransactingParties();
    						
                    		regForm.setMapTransactingParties(commonBo.insertApplicantDetsInMap
                    				(map,key,
                    				 regForm.getHidnRegTxnId(),regForm,language));
                    		/*regForm.setMapTransPartyDbInsertion(commonBo.insertApplicantDetsInMap
                    				(map,key,
                           				 regForm.getHidnRegTxnId(),regForm));*/
                    		//keyCount=regForm.getMapTransactingParties().size();
                    		
                    		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                    		
                    		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                    		
                			commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(),regForm.getInstID(),language));
    					//	commonDto.setAppTypeTrns(commonBo.getAppType(language));
    						commonDto.setCountryTrns(commonBo.getCountry(language));
    						commonDto.setIndcountryTrns(commonBo.getCountry(language));
    						commonDto.setIdProfTrns(commonBo.getIdProf(language));
    						regForm.setDeedId(Integer.toString(regForm.getDeedID()));
    						regForm.setHidnUserId(userId);
    						
    						//FOLLOWING CODE FOR INSERTING PROPERTY DETAILS THROUGH PV INTO DB
    						boolean multiplePropDetlsInserted;
  						  //  multiplePropDetlsInserted = commonBo.insrtMultiplePropDetls(regForm);
    						//logger.debug("multiple property details insertion status---------->"+multiplePropDetlsInserted);
    						
    						//FOLLOWING CODE FOR FETCHING APPLICANT ROLE ID.
    						//disabling applicant role in multiple properties.
    						String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
    						logger.debug("applicant role id---------->"+applicantRoleId);
    						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
    						//
    						int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
			
    						regForm.setTotalShareOfProp(totalShare);
    						
    						forward ="transactingParty";
    					}
    				}
                }
                commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(),regForm.getInstID(),language));
				/*commonDto.setAppTypeTrns(commonBo.getAppType());
				commonDto.setCountryTrns(commonBo.getCountry());
				commonDto.setIndcountryTrns(commonBo.getCountry());
				commonDto.setIdProfTrns(commonBo.getIdProf());*/
                //after searching adjudication number
                if(RegInitConstant.ADJUDICATION_ACTION.equals(actionName)){
                	
        			regForm.setActionName(RegInitConstant.NO_ACTION);
                	String adjudicationId=regForm.getAdjudicationNumber();
                	
                	//following code for searching adjudication number and its status in database.
                	String[] adjuArray=commonBo.getAdjudicationStatus(adjudicationId);
                	
                	if(adjuArray!=null){
                		
                		if(adjuArray[1].trim().equalsIgnoreCase("Y")){
                			
                			regForm.setHidnUserId(userId);
                			
                			//for generating temporary Registration Initiation id.
                			Calendar currentDate = Calendar.getInstance();
  						  SimpleDateFormat formatter= new SimpleDateFormat("ddMMyy");
  						  String dateNow = formatter.format(currentDate.getTime());
  						  String  regTxnIdSeq= commonBo.getRegInitTxnIdSeq();
  						  String regTxnId=null;
  						  regTxnId=dateNow+regTxnIdSeq;
  						  regForm.setHidnRegTxnId(regTxnId);
                        	//code for insertion of registration id and status in initiation tables.
                        	boolean regIdUpdated=commonBo.updateAdjudicationRecords(regForm.getHidnRegTxnId(), adjudicationId, regForm.getHidnUserId());
                        	
  						  
  						  
                        	if(regIdUpdated)
                        	{
                        	try{
                        	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                    		if(deedInstArray!=null && deedInstArray.length>0){
                    			
                    			request.setAttribute("deedId", deedInstArray[0].trim());
                    			request.setAttribute("instId", deedInstArray[1].trim());
                    			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                    			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                    			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                    			
                    		}else {
                    			request.setAttribute("deedId", 0);
                    			request.setAttribute("instId", 0);
                    			regForm.setDeedID(0);
                    			regForm.setInstID(0);
                    			regForm.setExmpID("0");
                    			regForm.setHdnExAmount("0");
                    		}
                    		
                    		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),language));
                    		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),language));
                    		//below code for exemptions
                    		
                    		String exemptions = regForm.getExmpID();
                    		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions,language));
                    		
                    		HashMap propMap =new HashMap();
                    		propMap=regForm.getMapPropertyTransParty();
                    		logger.debug("in confirmation action----------------------->");
                    		
                    		ArrayList propertyIdList=commonBo.getPropertyIdMarketValAdju(adjudicationId);   //GETTING ADJUDICATED MARKET VALUES
                    		double totalMarketVal=0;
                    		
                    		int numberOfProperties=propertyIdList.size();
                    		
                    		for(int i=0;i<propertyIdList.size();i++){
                    			
                    			ArrayList row_list=new ArrayList();
                    			row_list=(ArrayList)propertyIdList.get(i);
                    			String propIds=row_list.toString();
                    			propIds=propIds.substring(1, propIds.length()-1);
                    			logger.debug("property id and market value list-->"+propIds);
                    			String propId[]=propIds.split(",");
                    			
                    			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
                    			//Updated by Rakesh for PartyPropMappingDisplay: Start
                    			ArrayList transPartyDets=null;
                    			String clr_flag=commonBo.getClrFlagByPropId(propId[0].trim());
                    			if(clr_flag !=null && !clr_flag.isEmpty()){
                    				if(clr_flag.equalsIgnoreCase("Y")){
                    				transPartyDets=commonBo.getTransPartyDetsCLR(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                    				}else{            					
                    			
                    				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                    				}
                    				
                    			}
                                else{
                    				transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                                }
                    			
                    			//Updated by Rakesh for PartyPropMappingDisplay: End
                    	
                    			//ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),language,regForm.getDeedID(),regForm.getInstID(),regForm);
                    			         			
                    			
                    			for(int j=0;j<transPartyDets.size();j++){
                    				
                    				CommonDTO dto=new CommonDTO();
                    				dto=(CommonDTO)transPartyDets.get(j);
                    				logger.debug("transacting party list  role------>"+dto.getRole());
                    				logger.debug("transacting party list  name------>"+dto.getName());
                    				logger.debug("transacting party list  id------>"+dto.getId());
                    			
                    			}
                    			logger.debug("property id------>"+propId[0].trim());
                    			logger.debug("market value------>"+propId[1].trim());
                    			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
                    	
                    		}
                    		
                    		NumberFormat obj=new DecimalFormat("#0.00");
                    		regForm.setTotalMarketValue(obj.format(totalMarketVal));
                    		regForm.setMapPropertyTransParty(propMap);
                    		
                    		
                    		
                    			String dutyListArr[]=commonBo.getAdjudicatedDutyDetls(adjudicationId);  //GETTING ADJUDICATED DUTIES
                    			
                    			if(numberOfProperties>0)
                    			{
                    			if(dutyListArr!=null){
                    				regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
                        			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
                        			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
                        			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
                        			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                        			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
                        			
                        			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                            			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                            			else
                            				regForm.setMarketValueShares(Double.toString(0.0));	
                            			
                            			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                            			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                            			else
                            				regForm.setDutyPaid(Double.toString(0.0));
                            			
                            			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                            			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                            			else
                            				regForm.setRegPaid(Double.toString(0.0));
                            			
                    			if(numberOfProperties==1)
                    			{
                    			
                    			            			
                    			regForm.setIsMultiplePropsFlag(0);
                        		regForm.setIsDutyCalculated(1);
                        		
                    			}else if(numberOfProperties>1){
                    				
                    				regForm.setIsDutyCalculated(1);
                    				regForm.setIsMultiplePropsFlag(1);
                    				
                    				
                    			}
                    			
                        		regForm.setRegInitPaymntTxnId(null);
                        		

                        		//forward="reginitConfirmAdju";
                        		forward="reginitConfirm";
                        		
                    			}
                    			else{
                    				String msg="No duties found";
                            		regForm.setErrorMsg(msg);
                            		forward="searchAdju";
                    			}
                        		//return mapping.findForward(forward);	
                    			
                        	}else{
                				String msg="No property found";
                        		regForm.setErrorMsg(msg);
                        		forward="searchAdju";
                			}
                    			
                        }catch(Exception e){
                        	
                        	forward="failure";
                    		return mapping.findForward(forward);	
                        	
                        }
                			
                		}else{
                			
                			
                			try{
                				
                				throw new SQLException();
                				
                			}catch(SQLException e){
                            	
                            	forward="failure";
                        		return mapping.findForward(forward);	
                            	
                            }
                			
                			
                		}
                			
                			
                		}else{
                			String msg="Adjudication not completed.";
                    		regForm.setErrorMsg(msg);
                    		forward="searchAdju";
                			
                		}
                		
                		
                	}else{
                		
                		String msg="Invalid Adjudication number";
                		regForm.setErrorMsg(msg);
                		forward="searchAdju";
                		
                	}
                	
                	
            		
                	
              }

			//session.setAttribute("commonDto", commonDto);
			regForm.setCommonDto(commonDto);
			
			//session.setAttribute("regForm", regForm);
			//request.setAttribute("commonDto", commonDto);
			request.setAttribute("reginit", regForm);
			
			logger.debug("total poa count-------->"+regForm.getTotalPoaCount());
			
			
			logger.debug("-------------------->"+session.getAttribute("parentModName"));
			logger.debug("-------------------->"+session.getAttribute("parentFunName"));
			logger.debug("-------------------->"+session.getAttribute("parentFunId"));
			logger.debug("-------------------->"+session.getAttribute("parentAmount"));
			
			
 			logger.debug("the forward path from the common action in reg init is ==> " + forward);
 			return mapping.findForward(forward);		
	}
	
	public static void cancelAction(HttpSession session, RegCommonForm regForm)
	{
		//RegCommonForm regForm=(RegCommonForm)session.getAttribute("regForm");
		
		if(regForm!=null)
		{
					    
		 //   RegCommonDTO commonDto = regForm.getCommonDto();
		//    commonDto.setInstrument(new ArrayList());
		//	commonDto.setExemption(new ArrayList());
			
			regForm.setFname("");
			regForm.setMname("");
			regForm.setLname("");
			regForm.setGendar("M");
			regForm.setAge("");
			regForm.setFatherName("");
			regForm.setMotherName("");
			regForm.setSpouseName("");
			regForm.setNationality("");
			regForm.setIndaddress("");
			regForm.setIndcountry("");
			regForm.setIndstatename("");
			regForm.setInddistrict("");
			regForm.setPostalCode("");
			regForm.setIndphno("");
			regForm.setIndmobno("");
			regForm.setEmailID("");
			regForm.setListID("");
			regForm.setIdno("");
			regForm.setDeedType("");				
			regForm.setPurpose("");		
			regForm.setInstrument("");
			regForm.setIndCaste("");
			regForm.setIndReligion("");
			regForm.setIndDisability("");
			//regForm.setShareOfProp("");
			regForm.setOwnershipShare("");
			regForm.setRelationWithOwner("");
			
             regForm.setOgrName("");
             regForm.setAuthPerName("");
             regForm.setOrgaddress("");
             regForm.setCountry("");
             regForm.setStatename("");
             regForm.setDistrict("");
             regForm.setPhno("");
             regForm.setMobno("");
             regForm.setDeedType("");				
			 regForm.setPurpose("");	
			 
			//    commonDto.setInstrument(new ArrayList());
			//	commonDto.setExemption(new ArrayList());
				
				regForm.setFnameTrns("");
				regForm.setMnameTrns("");
				regForm.setLnameTrns("");
				regForm.setGendarTrns("M");
				regForm.setAgeTrns("");
				regForm.setFatherNameTrns("");
				regForm.setMotherNameTrns("");
				regForm.setSpouseNameTrns("");
				regForm.setNationalityTrns("");
				regForm.setIndaddressTrns("");
				regForm.setIndcountryTrns("");
				regForm.setIndcountryNameTrns("");
				regForm.setIndstatenameTrns("");
				regForm.setIndstatenameNameTrns("");
				regForm.setInddistrictTrns("");
				regForm.setInddistrictNameTrns("");
				regForm.setPostalCodeTrns("");
				regForm.setIndphnoTrns("");
				regForm.setIndmobnoTrns("");
				regForm.setEmailIDTrns("");
				regForm.setListIDTrns("");
				regForm.setListIDNameTrns("");
				regForm.setIdnoTrns("");
						
				regForm.setPurposeTrns("");
				regForm.setDeleteTrnsPrtyID("");
				regForm.setIndCasteTrns("");
				regForm.setIndReligionTrns("");
				regForm.setIndDisabilityTrns("");
				regForm.setShareOfPropTrns("");
				regForm.setOwnershipShareTrns("");
				regForm.setRelationWithOwnerTrns("");
				
	             regForm.setOgrNameTrns("");
	             regForm.setAuthPerNameTrns("");
	             regForm.setOrgaddressTrns("");
	             regForm.setCountryTrns("");
	             regForm.setCountry("");
	             regForm.setCountryNameTrns("");
	             regForm.setStatenameTrns("");
	             regForm.setStatenameNameTrns("");
	             regForm.setDistrictTrns("");
	             regForm.setDistrictNameTrns("");
	             regForm.setPhnoTrns("");
	             regForm.setMobnoTrns("");
	             regForm.setDeleteTrnsPrtyID("");
	            
			    
		    regForm.setPartyType(null);
		    regForm.setPartyTypeTrns(null);
		    regForm.setListAdoptedParty(null);
		    regForm.setListAdoptedPartyTrns(null);
		    regForm.setOwnerList(new ArrayList());
		    regForm.setPoaList(new ArrayList());
		    regForm.setSelectedPoa(null);
		    regForm.setSelectedPoaName(null);
		    regForm.setParty1OwnerCount(0);
		    regForm.setParty1PoaHolderCount(0);
		    regForm.setParty2OwnerCount(0);
		    regForm.setParty2PoaHolderCount(0);
		    regForm.setDoneeCount(0);
		    regForm.setDonorCount(0);
		    regForm.setBuyerCount(0);
		    regForm.setSellerPoaCount(0);
		    regForm.setSellerSelfCount(0);
		    regForm.setOwnerCount(0);
		    regForm.setPoaAccepterCount(0);
		    regForm.setPoaHolderCount(0);
		    regForm.setOwnerId("");
		    regForm.setHdnDeleteTrnsPrtyId("");
		    regForm.setHdnOwnerId("");
		    regForm.setHidnRegTxnId("");
		    regForm.setHidnUserId("");
		    regForm.setPropertyDTO(new PropertyValuationDTO());
		    regForm.setMapTransactingParties(new HashMap());
		    regForm.setMapTransactingPartiesDisp(new HashMap());
		    regForm.setRegInitEstampCode(null);
		    regForm.setRegInitPaymntTxnId(null);
		    regForm.setRegInitPermTxnId("");
		    regForm.setCurrDateTime("");
		    regForm.setIsMultiplePropsFlag(0);
	
		regForm.setActionName(" ");
		regForm.setFormName(" ");
		regForm.setPage("success");
		
		regForm.setListAdoptedPartyNameTrns("");
		regForm.setListAdoptedPartyName("");
		regForm.setAddMoreCounter(0);
		//
		regForm.setRegInitPermTxnId("");
		regForm.setRegInitPaymntTxnId("");
		regForm.setRegInitEstampCode("");
		regForm.setMapRegTxnIdDisp(new HashMap());
		//
		regForm.setOwnerId("");
		regForm.setHdnOwnerId("");
		regForm.setHdnDeleteTrnsPrtyId("");
		regForm.setAbc("");
		//
		//private String selectedPoa;
		//private String selectedPoaName;
		regForm.setCurrDateTime("");
		regForm.setDeedID(0);
		
		//Start:==== Added by ankita
		regForm.setDeedtype1("");
		regForm.setInstType("");
		/*private double stampduty1;
		private double nagarPalikaDuty;
		private double panchayatDuty;
		private double upkarDuty;
		private double totalduty ;
		private double registrationFee;*/
		regForm.setStampduty1("");
		regForm.setNagarPalikaDuty("");
		regForm.setPanchayatDuty("");
		regForm.setUpkarDuty("");
		regForm.setTotalduty("") ;
		regForm.setRegistrationFee("");
		regForm.setSelectedExemptionList( new ArrayList());
		regForm.setTotalMarketValue("");
		regForm.setExemType("");
		regForm.setFromModule("");
		regForm.setCheckModule("");
		regForm.setCheckRegNo("");    //TO BE SET AS BLANK
		
		//End :====== Added by ankita
		
		//following added roopam after first demo.
		
		regForm.setApplicantUserIdCheck("");
		regForm.setHdnapplicantUserIdCheck("");
		regForm.setInstID(0);
		regForm.setExmpID("");
		regForm.setPendingRegApplicationList(new ArrayList());
		regForm.setPropertyId("");
		regForm.setValuationId("");
		regForm.setIsDashboardFlag(0);
		regForm.setIsMultiplePropsFlag(0);
		regForm.setIsTransactingPartyAddedFlag(0);
		regForm.setMapPropertyTransParty ( new HashMap());
		regForm.setMapPropertyTransPartyDisp ( new HashMap());
		//private float totalMarketValue=0;
		/*private String totalMarketValue;
		private int applicantRoleId;
		private int totalShareOfProp;
		private int applicantRoleId2;
		private int mapKeyCount=0;
		private HashMap mapTransPartyDbInsertion = new HashMap();
		
		private int totalShareOfPropBuyer;
		private int totalShareOfPropSellerSelf;
		private int totalShareOfPropSellerPoa;*/
		//private float marketValue;
		regForm.setApplicantRoleId(0);
		regForm.setApplicantRoleId2(0);
		
		//regForm.setTotalShareOfProp(0);
		regForm.setTotalShareOfProp(0);
		regForm.setTotalShareOfPropBuyer(0);
		regForm.setTotalShareOfPropSellerSelf(0);
		regForm.setTotalShareOfPropDonor(0);
		regForm.setTotalShareOfPropDonee(0);
		regForm.setTotalShareOfPropOwnerSelf(0);
		
		
		//following fields for owner of poa
		regForm.setOwnerName("");
		regForm.setOwnerOgrName("");
		regForm.setOwnerGendar("M");
		regForm.setOwnerNationality("");
		regForm.setOwnerAddress("");
		regForm.setOwnerPhno("");
		regForm.setOwnerEmailID("");
		regForm.setOwnerListID("");
		regForm.setOwnerIdno("");
		regForm.setOwnerAge("");
		regForm.setOwnerProofName("");
		
		regForm.setOwnerNameTrns("");
		regForm.setOwnerOgrNameTrns("");
		regForm.setOwnerGendarTrns("M");
		regForm.setOwnerNationalityTrns("");
		regForm.setOwnerAddressTrns("");
		regForm.setOwnerPhnoTrns("");
		regForm.setOwnerEmailIDTrns("");
		regForm.setOwnerListIDTrns("");
		regForm.setOwnerIdnoTrns("");
		regForm.setOwnerAgeTrns("");
		regForm.setOwnerProofNameTrns("");
		
		/*private String ownerNameTrns;
		private String ownerOgrNameTrns;
		private String ownerGendarTrns="M";
		private String ownerNationalityTrns;
		private String ownerAddressTrns;
		private String ownerPhnoTrns;
		private String ownerEmailIDTrns;
		private String ownerListIDTrns;
		private String ownerIdnoTrns;
		private String ownerAgeTrns;
		private String ownerProofNameTrns;
		*/
		//private String poaOwnerId;
		//private String hdnExAmount;
		regForm.setIsDutyCalculated(0);
		regForm.setMarketValueShares("");
		//private String dutyPaid;
		
		regForm.setLabelAmountFlag("");
		//private double regPaid=0.0;
		
		regForm.setMapPropertyTransParty(new HashMap());
		
		regForm.setAdjudicationNumber("");
		regForm.setErrorMsg("");
		
		
		
		}
		//session.removeAttribute("OrganisationList");
		//session.removeAttribute("TpartiesIndividualList");
		session.removeAttribute("commonDto");
		session.removeAttribute("roleId");
		session.removeAttribute("functionId");
		session.removeAttribute("partyType");	
		session.removeAttribute("regForm");	
		session.removeAttribute("status");
		session.removeAttribute("view");
		session.removeAttribute("eform");
		session.removeAttribute("labelAmountFlag");
		
		
		    
	}
	
	/*public static String getRoleNameId(RegCommonForm regForm, String action, String partyType)
	{
		
		String partyRoleTypeId=null;
		String roleName=null;
		int count;
		if(action.equalsIgnoreCase(RegInitConstant.NEXT_ACTION)){
		if(regForm.getPartyType().equalsIgnoreCase("50001"))
		{
			roleName=RegInitConstant.PARTY_BUYER;
			
			count=regForm.getBuyerCount();
			partyRoleTypeId="50001"+count;
			
		}
		if(regForm.getPartyType().equalsIgnoreCase("50002"))
		{
			roleName=RegInitConstant.PARTY_SELLER_SELF;
			
			count=regForm.getSellerSelfCount();
			partyRoleTypeId="50002"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50003"))
		{
			roleName=RegInitConstant.PARTY_SELLER_POA;
			
			count=regForm.getSellerPoaCount();
			partyRoleTypeId="50003"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50004"))
		{
			roleName=RegInitConstant.PARTY_OWNER;
			count=regForm.getOwnerCount();
			partyRoleTypeId="50004"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50005"))
		{
			roleName=RegInitConstant.PARTY_DONOR;
			count=regForm.getDonorCount();
			partyRoleTypeId="50005"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50006"))
		{
			roleName=RegInitConstant.PARTY_DONEE;
			count=regForm.getDoneeCount();
			partyRoleTypeId="50006"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50007"))
		{
			roleName=RegInitConstant.PARTY_POA_HOLDER;
			count=regForm.getPoaHolderCount();
			partyRoleTypeId="50007"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50008"))
		{
			roleName=RegInitConstant.PARTY_POA_ACCEPTER;
			count=regForm.getPoaAccepterCount();
			partyRoleTypeId="50008"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50009"))
		{
			roleName=RegInitConstant.PARTY1_OWNER;
			count=regForm.getParty1OwnerCount();
			partyRoleTypeId="50009"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50010"))
		{
			roleName=RegInitConstant.PARTY2_OWNER;
			count=regForm.getParty2OwnerCount();
			partyRoleTypeId="50010"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50011"))
		{
			roleName=RegInitConstant.PARTY1_POA_HOLDER;
			count=regForm.getParty1PoaHolderCount();
			partyRoleTypeId="50011"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50012"))
		{
			roleName=RegInitConstant.PARTY2_POA_HOLDER;
			count=regForm.getParty2PoaHolderCount();
			partyRoleTypeId="50012"+count;
		}
		}
		if(action.equalsIgnoreCase(RegInitConstant.ADD_MORE_ACTION) || 
		   action.equalsIgnoreCase(RegInitConstant.NEXT_TO_MAPPING_ACTION) ||
		   action.equalsIgnoreCase(RegInitConstant.NEXT_TO_PROPERTY_ACTION)){
			if(partyType.equalsIgnoreCase("50001"))
			{
				roleName=RegInitConstant.PARTY_BUYER;
				
				count=regForm.getBuyerCount();
				partyRoleTypeId="50001"+count;
			}
			if(partyType.equalsIgnoreCase("50002"))
			{
				roleName=RegInitConstant.PARTY_SELLER_SELF;
				
				count=regForm.getSellerSelfCount();
				partyRoleTypeId="50002"+count;
			}
			if(partyType.equalsIgnoreCase("50003"))
			{
				roleName=RegInitConstant.PARTY_SELLER_POA;
				
				count=regForm.getSellerPoaCount();
				partyRoleTypeId="50003"+count;
			}
			if(partyType.equalsIgnoreCase("50004"))
			{
				roleName=RegInitConstant.PARTY_OWNER;
				count=regForm.getOwnerCount();
				partyRoleTypeId="50004"+count;
			}
			if(partyType.equalsIgnoreCase("50005"))
			{
				roleName=RegInitConstant.PARTY_DONOR;
				count=regForm.getDonorCount();
				partyRoleTypeId="50005"+count;
			}
			if(partyType.equalsIgnoreCase("50006"))
			{
				roleName=RegInitConstant.PARTY_DONEE;
				count=regForm.getDoneeCount();
				partyRoleTypeId="50006"+count;
			}
			if(partyType.equalsIgnoreCase("50007"))
			{
				roleName=RegInitConstant.PARTY_POA_HOLDER;
				count=regForm.getPoaHolderCount();
				partyRoleTypeId="50007"+count;
			}
			if(partyType.equalsIgnoreCase("50008"))
			{
				roleName=RegInitConstant.PARTY_POA_ACCEPTER;
				count=regForm.getPoaAccepterCount();
				partyRoleTypeId="50008"+count;
			}
			if(partyType.equalsIgnoreCase("50009"))
			{
				roleName=RegInitConstant.PARTY1_OWNER;
				count=regForm.getParty1OwnerCount();
				partyRoleTypeId="50009"+count;
			}
			if(partyType.equalsIgnoreCase("50010"))
			{
				roleName=RegInitConstant.PARTY2_OWNER;
				count=regForm.getParty2OwnerCount();
				partyRoleTypeId="50010"+count;
			}
			if(partyType.equalsIgnoreCase("50011"))
			{
				roleName=RegInitConstant.PARTY1_POA_HOLDER;
				count=regForm.getParty1PoaHolderCount();
				partyRoleTypeId="50011"+count;
			}
			if(partyType.equalsIgnoreCase("50012"))
			{
				roleName=RegInitConstant.PARTY2_POA_HOLDER;
				count=regForm.getParty2PoaHolderCount();
				partyRoleTypeId="50012"+count;
			}
			}
		
		regForm.setPartyRoleTypeId(partyRoleTypeId);
		return roleName;
	}*/
	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileName,"UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}
	}
	
	public String uploadFile(String regTxnId, byte[] content) {

		String filePath;
		
		
        filePath =RegCompConstant.FILE_UPLOAD_PATH+regTxnId+RegCompConstant.UPLOAD_PATH_DEED_DOC;
		String fileName = RegCompConstant.FILE_NAME_DEED_DOC;
	
		
		
		
		
        
        File folder = new File(filePath);
        if (!folder.exists()) {
              folder.mkdirs();
        }
        try {

              File newFile = new File(filePath, fileName);
              if (!newFile.exists()) {
                    logger.info("NEW FILE NAME:-" + newFile);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              } else {

                    //String str = fileName.substring(0, fileName.indexOf("."));
                    //fileName = str + "_01" + ".jpg";
                    //newFile = new File(filePath, fileName);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(content);
                    fos.close();
              }
              

        } catch (Exception ex) {
        	
              ex.printStackTrace();
              return null;
        }
        return filePath+fileName;
  }
	
}
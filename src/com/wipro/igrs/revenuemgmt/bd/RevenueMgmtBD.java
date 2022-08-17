/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 *==============================================================================
 * File Name   :     RevenueMgmtBD.java
 * Author      :     @author srenaag
 * Description :     This class interacts with DbService for data persistance 
 * 					 and data fetch
 * -----------------------------------------------------------------------------
 * Version         Modified By     Modified On   Modification
 * -----------------------------------------------------------------------------
 * 1.0             Sreelatha     Dec 24, 2007   Created.
 * -----------------------------------------------------------------------------
 */
package com.wipro.igrs.revenuemgmt.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;
import com.wipro.igrs.revenuemgmt.dao.RevenueMgmtDAO;
import com.wipro.igrs.revenuemgmt.dto.RevenueMgmtDTO;
import com.wipro.igrs.revenuemgmt.form.RevenueMgmtForm;

public class RevenueMgmtBD {

	private Logger logger = (Logger) Logger.getLogger(RevenueMgmtBD.class);
	public RevenueMgmtBD() {
	}	
	/**
     * ===========================================================================
     * Method         :   getDistrictDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 31, 2007
     * ===========================================================================
     */
	public ArrayList getDistrictDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getDistrictDetails BD start");	    		  
	    	ArrayList resultList = dao.getDistrictDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                	revenueMgmtDTO.setDistrictId((String)list.get(0));
                	revenueMgmtDTO.setDistrictName((String)list.get(1));
                	returnList.add(revenueMgmtDTO);
                	logger.debug("in bd for loop end,DIST.Id:-"+revenueMgmtDTO.getDistrictId());
                	logger.debug("in bd for loop end,DIST.Location:-"+revenueMgmtDTO.getDistrictName());
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In getDistrictDetails BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In getDistrictDetails Exception "+e);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getOfficeTypeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 31, 2007
     * ===========================================================================
     */
	public ArrayList getOfficeTypeDetails(RevenueMgmtForm revenueMgmtForm){
		String districtId = revenueMgmtForm.getRevenueMgmtDTO().getDistrictId();
		logger.debug("districtId:-"+districtId);
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getOfficeTypeDetails BD start");	    		  
	    	ArrayList resultList = dao.getOfficeTypeDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	String offTypeId = (String) list.get(0);
                	RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                	if(districtId.equalsIgnoreCase("1") && offTypeId.equalsIgnoreCase("HO")){
                		revenueMgmtDTO.setOfficeTypeId((String)list.get(0));
                    	revenueMgmtDTO.setOfficeType((String)list.get(1));                		
                	}
                	else{
                		if(!(districtId.equalsIgnoreCase("1")) && offTypeId.equalsIgnoreCase("HO")){
                			continue;
                		}
                		else{
                			revenueMgmtDTO.setOfficeTypeId((String)list.get(0));
                        	revenueMgmtDTO.setOfficeType((String)list.get(1)); 
                		}
                	}                	
                	returnList.add(revenueMgmtDTO);
                	logger.debug("in bd for loop end,OffType.Id:-"+revenueMgmtDTO.getOfficeTypeId());
                	logger.debug("in bd for loop end,OffType.Name:-"+revenueMgmtDTO.getOfficeType());
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In getOfficeTypeDetails BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In getOfficeTypeDetails Exception "+e);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getOfficeNameDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 31, 2007
     * ===========================================================================
     */
	public ArrayList getOfficeNameDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In getOfficeNameDetails BD start");	    		  
	    	ArrayList resultList = dao.getOfficeNameDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                	revenueMgmtDTO.setOfficeId((String)list.get(0));
                	revenueMgmtDTO.setOfficeName((String)list.get(1));
                	returnList.add(revenueMgmtDTO);
                	logger.debug("in bd for loop end,OffName:-"+revenueMgmtDTO.getOfficeName());
                	logger.debug("in bd for loop end,OffId:-"+revenueMgmtDTO.getOfficeId());
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In getOfficeNameDetails BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In getOfficeNameDetails Exception "+e);
	    }
	    return returnList;
	}
	 /**
     * ===========================================================================
     * Method         :   getRevenueDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 24, 2007
     * ===========================================================================
     */
	public ArrayList getRevenueDetails(RevenueMgmtForm revenueMgmtForm) {
		 
		RevenueMgmtDAO dao = new RevenueMgmtDAO(); 
		ArrayList returnList=new ArrayList();
        try{
	    	logger.debug("In Revenue Collection BD start");	    		  
	    	ArrayList resultList = dao.getRevenueDetails(revenueMgmtForm);	    	
	    	if(resultList!=null) {
	    		for(int i=0;i<resultList.size();i++) {
	    			ArrayList list = (ArrayList)resultList.get(i);
	    			RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();			
	    			logger.debug("list size:-"+list.size());
	    			/*revenueMgmtDTO.setPurpose((String)list.get(0));
	    			revenueMgmtDTO.setReceiptID((String)list.get(1));
	    			revenueMgmtDTO.setPaymentType((String)list.get(2));
	    			revenueMgmtDTO.setTransactionDate((String)list.get(3));
	    			revenueMgmtDTO.setTransactionAmount(Float.parseFloat((String)list.get(4)));
	    			revenueMgmtDTO.setTransactionPurpose((String)list.get(5));
	    			revenueMgmtDTO.setUserId((String)list.get(6));*/
	    			
	    			revenueMgmtDTO.setPurpose((String)list.get(0));
	    			revenueMgmtDTO.setReceiptID((String)list.get(1));
	    			revenueMgmtDTO.setPaymentType((String)list.get(2));
	    			revenueMgmtDTO.setTransactionDate((String)list.get(3));
	    			logger.debug("(String)list.get(4)========="+(String)list.get(4));
	    			logger.debug("list.get(4).toString().trim().length()==="+list.get(4).toString().trim().length());;
	    			logger.debug("(String)list.get(4).toString().trim().length()==="+((String)list.get(4)).toString().trim().length());;
	    			if(((String)list.get(4)!=null) && ((String)list.get(4)).toString().trim().length()!= 0 ){
	    				revenueMgmtDTO.setTransactionAmount(Float.parseFloat((String)list.get(4)));	
	    			}else{
	    				revenueMgmtDTO.setTransactionAmount(0);	
	    			}
	    			    			
	    			revenueMgmtDTO.setUserId((String)list.get(5));
	    			logger.debug("BD:-" + list);
	    			returnList.add(revenueMgmtDTO);	    			
	    		}
	    	}
	    	logger.debug("In Revenue Collection BD TryBlock end ");
		 }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In Revenue Collection Exception "+e);
	    }        
        return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getAdvancePaymentsCreditDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 24, 2007
     * ===========================================================================
     */
	public ArrayList getAdvancePaymentsCreditDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In AdvancePayments BD start");	    		  
	    	ArrayList resultList = dao.getAdvancePaymentsCreditDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	 
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));	 
                    ArrayList list = (ArrayList)resultList.get(i);
                    RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                    revenueMgmtDTO.setCreditAmount((Float.parseFloat(((String)list.get(0)))));
                    logger.debug("creditAmt value:-"+revenueMgmtDTO.getCreditAmount());
                    revenueMgmtDTO.setCreditDate((String)list.get(1));
                    revenueMgmtDTO.setPaymentMode((String)list.get(2));
                    revenueMgmtDTO.setReceiptNumber((String)list.get(3));
                    revenueMgmtDTO.setBalanceAmount((Float.parseFloat((String)list.get(4))));
                    logger.debug(revenueMgmtDTO.getUserReceiptNumber());

                    returnList.add(revenueMgmtDTO);
                    logger.debug("in bd for loop end:-"+returnList);
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In Revenue Collection BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In AdvancePayments Exception "+e);
	    }
	    return returnList;
	}
	
	public ArrayList getAdvancePaymentsDebitDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In AdvancePayments BD start");	    		  
	    	ArrayList resultList = dao.getAdvancePaymentsDebitDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	 
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));	 
                    ArrayList list = (ArrayList)resultList.get(i);
                    RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                    revenueMgmtDTO.setDebitAmount((Float.parseFloat((String)list.get(0))));
                    revenueMgmtDTO.setDebitDate((String)list.get(1));
                    revenueMgmtDTO.setPurpose((String)list.get(2));
                    revenueMgmtDTO.setUserReceiptNumber((String)list.get(3));
                    logger.debug("DebitAmt value:-"+revenueMgmtDTO.getDebitAmount());

                    returnList.add(revenueMgmtDTO);
                    logger.debug("in bd for loop end:-"+returnList);
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In Revenue Collection BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In AdvancePayments Exception "+e);
	    }
	    return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getspDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 31, 2007
     * ===========================================================================
     */
	public ArrayList getspDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	logger.debug("In AdvancePayments BD start");	    		  
	    	ArrayList resultList = dao.getspDetails(revenueMgmtForm);
	    	if(resultList!=null){
	    		logger.debug("in bd if block start");	
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                	revenueMgmtDTO.setOffiId((String)list.get(0));
                	revenueMgmtDTO.setOffLocation((String)list.get(1));
                	returnList.add(revenueMgmtDTO);
                	logger.debug("in bd for loop end,offId:-"+revenueMgmtDTO.getOffiId());
                	logger.debug("in bd for loop end,offLocation:-"+revenueMgmtDTO.getOffLocation());
                } 
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In getspDetails BD TryBlock end ");
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In getspDetails Exception "+e);
	    }
	    return returnList;
	}	
	/**
     * ===========================================================================
     * Method         :   getCashPaymentDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 24, 2007
     * ===========================================================================
     */
	public ArrayList getCashPaymentDetails(RevenueMgmtForm revenueMgmtForm){
		 RevenueMgmtDAO dao = new RevenueMgmtDAO();  
		 ArrayList returnList=new ArrayList();
		 try{
		    logger.debug("In DailyCashPaymentsForm BD start");	    		  
		    ArrayList resultList = dao.getCashPaymentDetails(revenueMgmtForm);
		    if(resultList!=null){
		    	logger.debug("in bd if block start");	 
	    		int j=1;
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in bd for loop start:-" +resultList.get(i));	 
                    ArrayList list = (ArrayList)resultList.get(i);
                    RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();	
                    
                    revenueMgmtDTO.setSno(j);
                    revenueMgmtDTO.setTransactionDate((String)list.get(0));
                    if(list.get(1).toString().trim().length()!=0){
                    	revenueMgmtDTO.setTransactionAmount(Float.parseFloat((String)list.get(1)));
                    }else{
                    	revenueMgmtDTO.setTransactionAmount(0);
                    }
                    revenueMgmtDTO.setTransactionId((String)list.get(2));
                    revenueMgmtDTO.setTransactionType((String)list.get(3));
                    revenueMgmtDTO.setTransactionPurpose((String)list.get(4));
                    if(list.get(5).toString().trim().length()!=0){
                    	revenueMgmtDTO.setTransactionLocation((String)list.get(5));
                    }else{
                    	revenueMgmtDTO.setTransactionLocation(" ");
                    }                    
                    revenueMgmtDTO.setUserId((String)list.get(6));
                    revenueMgmtDTO.setSroDroName((String)list.get(7));
                    j++;
                    returnList.add(revenueMgmtDTO);
                    logger.debug("in bd for loop end:-");
                }
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In DailyCashPayments BD TryBlock end ");
	    }
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In Daily Cash Payments Exception "+e);
		}
		return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getChallanPaymentDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 24, 2007
     * ===========================================================================
     */
	public ArrayList getChallanPaymentDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList returnList = new ArrayList();
		try{
			logger.debug("In getDailyChallanPaymentDetails BD try block start" );
			ArrayList resultList = dao.getChallanPaymentDetails(revenueMgmtForm);
			if(resultList!=null){
				logger.debug("in bd if block start");	 
	    		int j=1;
				for(int i=0;i<resultList.size();i++){
					logger.debug("in bd for loop start resultList size:-"+resultList.size());
					logger.debug("in resultList values:-"+resultList.get(i));
					ArrayList list = (ArrayList)resultList.get(i);
					RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
					revenueMgmtDTO.setSno(j);
                    revenueMgmtDTO.setTransactionDate((String)list.get(0));
                    revenueMgmtDTO.setTransactionAmount(Float.parseFloat((String)list.get(1)));
                    revenueMgmtDTO.setTransactionId((String)list.get(2));
                    revenueMgmtDTO.setTransactionType((String)list.get(3));
                    revenueMgmtDTO.setTransactionPurpose((String)list.get(4));
                    revenueMgmtDTO.setTransactionLocation((String)list.get(5));                    
                    revenueMgmtDTO.setUserId((String)list.get(6));
                    revenueMgmtDTO.setReceiptID((String)list.get(7));
                    revenueMgmtDTO.setSroDroName((String)list.get(8));
                    j++;
                    returnList.add(revenueMgmtDTO);
                    logger.debug("in bd for loop end:-"+returnList);
                }
                logger.debug("in bd if block end ");               
			 }
	    	logger.debug("In DailyChallanPaymentDetails BD TryBlock end ");
	    }
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In DailyChallanPaymentDetails Exception "+e);
		}
		return returnList;
	}
	/**
     * ===========================================================================
     * Method         :   getOnlinePaymentDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 29, 2007
     * ===========================================================================
     */
	public ArrayList getOnlinePaymentDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList returnList = new ArrayList();
		try{
			logger.debug("in try block onlinePaymentDetailsBD start");
			ArrayList resultList = dao.getOnlinePaymentDetails(revenueMgmtForm);
			if(resultList!=null){
				logger.debug("in if block start");
				int j=1;
				for(int i=0;i<resultList.size();i++){
					logger.debug("in for loop start, resultList size:-"+resultList.size());
					logger.debug("resultList values:-"+resultList.get(i));
					ArrayList list = (ArrayList)resultList.get(i);
					RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
					revenueMgmtDTO.setSno(j);
					revenueMgmtDTO.setTransactionDate((String)list.get(0));
					revenueMgmtDTO.setTransactionAmount(
							(Float.parseFloat((String)list.get(1))));
					revenueMgmtDTO.setTransactionId((String)list.get(2));
					revenueMgmtDTO.setTransactionType((String)list.get(3));
					revenueMgmtDTO.setTransactionPurpose((String)list.get(4));
					revenueMgmtDTO.setTransactionLocation((String)list.get(5));
					revenueMgmtDTO.setUserId((String)list.get(6));
					revenueMgmtDTO.setSroDroName((String)list.get(7));
					j++;
					returnList.add(revenueMgmtDTO);
					logger.debug("in for loop end,userId value:-"+list.get(7));
				}
				logger.debug("in if block end");
			}
			logger.debug("in try block end");
		}
		catch (Exception e) {
			e.getStackTrace();
			logger.debug("in onlinePaymentDetails catch block:-"+e);
		}
		return returnList;
	}
	
	/**
     * ===========================================================================
     * Method         :   getRevRevenueDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Dec 24, 2007
     * ===========================================================================
     */
	public ArrayList getRevReconDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList resultList= new ArrayList();
		try{
			logger.debug("In getRevenueDailyDetails BD try block start" );
			resultList = dao.getRevReconDetails(revenueMgmtForm);
			logger.debug("resultlist size  BD:============="+resultList.size());
			/*if(resultList!=null){
				logger.debug("in bd if block start");	 
				for(int i=0;i<resultList.size();i++){
					logger.debug("in bd for loop start resultList size:-"+resultList.size());
					logger.debug("in resultList values:-"+resultList.get(i));					
					ArrayList list = (ArrayList)resultList.get(i);
					RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
					revenueMgmtDTO.setCashAtDRO((Float.parseFloat((String)list.get(0))));
					revenueMgmtDTO.setCashAtSRO((Float.parseFloat((String)list.get(1))));
					revenueMgmtDTO.setOnlinePayments((Float.parseFloat((String)list.get(2))));
					revenueMgmtDTO.setCreditLimitPayments((Float.parseFloat((String)list.get(3))));
					revenueMgmtDTO.setChallanPayments((Float.parseFloat((String)list.get(4))));
					revenueMgmtDTO.setRegistrationFee((Float.parseFloat((String)list.get(5))));
					revenueMgmtDTO.setStampDuty((Float.parseFloat((String)list.get(6))));
					revenueMgmtDTO.setRefunds((Float.parseFloat((String)list.get(7))));
					revenueMgmtDTO.setStampCases((Float.parseFloat((String)list.get(8))));
					revenueMgmtDTO.setRrc((Float.parseFloat((String)list.get(9))));
					logger.debug(revenueMgmtDTO.getRrc());
                    returnList.add(revenueMgmtDTO);
                    logger.debug("in bd for loop end:-"+returnList);
                }
                logger.debug("in bd if block end ");               
			 }*/
	    	logger.debug("In getRevenueDailyDetails BD TryBlock end ");
	    }
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In getRevenueDailyDetails Exception "+e);
		}
		return resultList;
	}
	/**
     * ===========================================================================
     * Method         :   insertServiceFeeDetails()
     * Description    :   Inserting all values into IGRS_SERVICE_FEE_PARAM_MASTER table. 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   void
     * Author         :   Sreelatha M
     * Created Date   :   Mar 18,2008
     * ===========================================================================
	 * @param userId 
	 * @param funId 
	 * @param roleId 
     */
	public boolean insertServiceFeeDetails(RevenueMgmtForm revenueMgmtForm, String roleId, String funId, String userId) throws Exception{
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		boolean returnflag = false;
		try{
		returnflag=  dao.insertServiceFeeDetails(revenueMgmtForm,roleId,funId,userId);
		}catch(Exception e){
			logger.debug("eXCEPTION AT bd " + e);			 
		}
		return returnflag;
     }
	/**
     * ===========================================================================
     * Method         :   getServiceFeeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Mar 19, 2008
  
	 * @param userId 
	 * @param funId 
	 * @param roleId    * ===========================================================================
, String roleId, String funId, String userId	 * @param userId 
	 * @param funId 
	 * @param roleId 
     */
	public boolean updateServiceFeeDetails(RevenueMgmtForm revenueMgmtForm, String roleId, String funId, String userId){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();	
		boolean flag = false;
		try{
			logger.debug("In updateServiceFeeDetails BD try block start" +revenueMgmtForm.getRevenueMgmtDTO().getParamId());
			flag = dao.updateServiceFeeDetails(revenueMgmtForm,roleId,funId,userId);			
		}
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In getServiceFeeDetails Exception "+e);
		}
		return flag;
	}
	/**
     * ===========================================================================
     * Method         :   getServiceFeeDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Mar 19, 2008
     * ===========================================================================
     */
	public ArrayList getServiceFeeDetails(RevenueMgmtForm revenueMgmtForm){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList resultList= new ArrayList();
		try{
			logger.debug("In getServiceFeeDetails BD try block start" );
			ArrayList returnList = dao.getServiceFeeDetails(revenueMgmtForm);
			if(returnList!=null){
				logger.debug("in bd for loop start returnList size:-"+returnList.size());
				for(int i=0;i<returnList.size();i++){					
					logger.debug("in returnList values:-"+returnList.get(i));		
					ArrayList list = (ArrayList)returnList.get(i);
					RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
					revenueMgmtDTO.setParamId((String)list.get(0));
					revenueMgmtDTO.setParamName((String)list.get(1));
					revenueMgmtDTO.setParamDescription((String)list.get(2));
					revenueMgmtDTO.setParamStatus((String)list.get(3));
					resultList.add(revenueMgmtDTO);
					logger.debug("param name in bd for loop========="+revenueMgmtDTO.getParamName()+" and " +
							"param desc in bd for loop======"+revenueMgmtDTO.getParamDescription());
				}
			}
		}
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In getServiceFeeDetails Exception "+e);
		}
		return resultList;
	}
	/**
     * ===========================================================================
     * Method         :   getParamIdDetails()
     * Description    :   Retrieving values from database 
     * Arguments      :   RevenueMgmtForm revenueMgmtForm 
     * return type    :   List
     * Author         :   Sreelatha M
     * Created Date   :   Mar 20, 2008
     * ===========================================================================
     */
	public ArrayList getParamIdDetails(String paramId){
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList resultList= new ArrayList();
		try{
			logger.debug("In getParamIdDetails BD try block start" );
			ArrayList returnList = dao.getParamIdDetails(paramId);
			if(returnList!=null){
				logger.debug("in bd for loop start returnList size:-"+returnList.size());
				for(int i=0;i<returnList.size();i++){					
					logger.debug("in returnList values:-"+returnList.get(i));		
					ArrayList list = (ArrayList)returnList.get(i);

					RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
					revenueMgmtDTO.setParamId((String)list.get(0));
					revenueMgmtDTO.setParamName((String)list.get(1));
					revenueMgmtDTO.setParamDescription((String)list.get(2));
					revenueMgmtDTO.setParamStatus((String)list.get(3));
					resultList.add(revenueMgmtDTO);
					logger.debug("param name in bd for loop========="+revenueMgmtDTO.getParamName()+" and " +
							"param desc in bd for loop======"+revenueMgmtDTO.getParamDescription());
				}
			}
		}
		catch(Exception e){
 			e.getStackTrace();
 	        logger.debug("In getParamIdDetails Exception "+e);
		}
		return resultList;
	}
	 
	public ArrayList getServiceParamList() throws Exception {
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		ArrayList plist = dao.getServiceParamList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				RevenueMgmtDTO revenueMgmtDTO = new RevenueMgmtDTO();
				revenueMgmtDTO.setParamId((String)lst.get(0));
				revenueMgmtDTO.setParamName((String)lst.get(1));
				revenueMgmtDTO.setParamDescription((String)lst.get(2));
				revenueMgmtDTO.setParamStatus((String)lst.get(3));
				revenueMgmtDTO.setCreatedBy((String)lst.get(4));
				
				list.add(revenueMgmtDTO);
			}
		}

		return list;
	}
	// method for deleting service master data
	public boolean deleteService(String serviceId, String roleId, String funId, String userId) throws Exception {
		RevenueMgmtDAO dao = new RevenueMgmtDAO();
		return dao.deleteService(serviceId,roleId,funId,userId);
	}

}
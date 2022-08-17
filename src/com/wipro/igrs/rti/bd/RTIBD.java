/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIBD.java
 * Author      :   Samuel Prabhakaran
 * 
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran       17th March, 2008	 		
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rti.bd;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.rti.bo.RTIBO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;
import com.wipro.igrs.rti.form.RTIRequestForm;

public class RTIBD {
    RTIBO bo = new RTIBO();
    ArrayList list = new ArrayList();

    public RTIBD() {
    }
    
    /**
     * ===========================================================================
     * Method : getDroName()
     * Description : Getting all DRO Name from IGRS_USERS_LIST . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */

    public ArrayList getDroName() {
        return bo.getDroName();
    }
    
    /**
     * ===========================================================================
     * Method : getStateList()
     * Description : Selecting all State from IGRS_STATE_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */

    public ArrayList getStateList() {
        return bo.getStateList();
    }
    /**
     * ===========================================================================
     * Method : getCountryList()
     * Description : Selecting all Country from IGRS_COUNTRY_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */
    public ArrayList getCountryList() {
        return bo.getCountryList();
    }
    /**
     * ===========================================================================
     * Method : getDistrictList()
     * Description : Selecting all District from IGRS_DISTRICT_MASTER  . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     */

    public ArrayList getDistrictList(String state) {
        return bo.getDistrictList(state);
    }
    /**
     * ===========================================================================
     * Method :  getRTIQuarterReport()
     * Description : This method get the Quarter Reports from IGRS_RTI_TXN_COMMENTS.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */

    public ArrayList getRTIQuarterReport(RTIRequestDTO rtireport) {
        
        ArrayList list = 
            bo.getRTIQuarterReport(rtireport);
        return list;
    }

    
    
    
    /**
     * ===========================================================================
     * Method :  getRTIReport()
     * Description : This method get the Annual Reports from IGRS_RTI_TXN_COMMENTS.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public ArrayList getRTIReport(RTIRequestDTO rtireport) {
        ArrayList list = bo.getRTIReport(rtireport);
        
        return list;
    }
    
    
    
    /**
     * ===========================================================================
     * Method :  rtiIDGenerator()
     * Description : This method generate RTIID.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public String rtiIDGenerator() {
    	
    	return bo.rtiIDGenerator();
    }
    
    
    
    
    /**
     * ===========================================================================
     * Method :  funIDGenerator()
     * Description : This method generate FunctionID.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    

    public String funIDGenerator() {
        
        return bo.funIDGenerator();
    }
    
    /**
     * ===========================================================================
     * Method :  getRTIReport()
     * Description : This method get the Annual Reports from IGRS_RTI_TXN_COMMENTS.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public ArrayList rtiRequest(RTIRequestDTO rtiRequest) {
        
        return bo.rtiRequest(rtiRequest);
    }
    
    
    
    /**
     * ===========================================================================
     * Method : rtiIntimationFee()
     * Description : getting intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
  

public ArrayList getRTIIntimationFee(RTIRequestDTO requestDTO,String  paramIds[]){
                              
      return    bo.getRTIIntimationFee(requestDTO,paramIds);              
    }



/**
 * ===========================================================================
 * Method : rtiWorkFee()
 * Description : getting serviec fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 
 * return type : Arraylist
 * Author : Samuel Prabhakaran
 * Created Date : 18 Feb,2008
 * ===========================================================================
 * 
 */

public ArrayList getRTIWorkFee(RTIRequestDTO rtiRequest){
    
    return    bo.getRTIWorkFee(rtiRequest);              
  }



/**
 * ===========================================================================
 * Method : getDate()
 * Description : This method convert date field into dd/mmm/yy format . 
 * return type : String
 * Author : Samuel Prabhakaran
 * Created Date : 18 Feb,2008
 * ===========================================================================
 * 
 */


    public String getDate(String _fdate) {
            

        
        return bo.getDate( _fdate);

    }
    
    /**
     * ===========================================================================
     * Method : getMonthName()
     * Description : This method gives the month name . 
     * return type : String
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    

    public String getMonthName(String _month) {
       
       
        return bo.getMonthName( _month);
    }

    
    
    /**
     * ===========================================================================
     * Method : getConvertedDate()
     * Description : This method convert the date into oracle date format. 
     * return type : String
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    
    
    public static String getConvertedDate(String dFromDate) {
        
        return RTIBO.getConvertedDate(dFromDate);

    }
    
    
    /**
     * ===========================================================================
     * Method :  getComments()
     * Description : This method get the DRO Comments from IGRS_RTI_TXN_COMMENTS.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public ArrayList getComments(String refId){
        
        return bo.getComments(refId);
         
    }

    
    
    /**
     * ===========================================================================
     * Method :  statusInfo()
     * Description : This method get the Request status information from IGRS_RTI_TXN_PARTY_DETAILS,IGRS_RTI_REQUEST_TXN.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */

    public ArrayList statusInfo(String refId, String fromDate, String toDate, 
                                String status) {
        {
            return bo.statusInfo(refId, fromDate, toDate, status);
        }
    }
    
    
    
    /**
     * ===========================================================================
     * Method :  getIntimation()
     * Description : This method get the intimation information from IGRS_RTI_INTIMATION_MAPPING.
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public ArrayList getIntimation(String refId){
    	
    		return bo.getIntimation(refId);
    	
    }

    
    
    /**
     * ===========================================================================
     * Method : getphotoIdList()
     * Description : Selecting all ID TYPE from IGRS_PHOTOID_PROOF_TYPE_MASTER . 
     * return type : Arraylist
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    public ArrayList getphotoIdList() {
        return bo.getphotoIdList();
    }

    
    /**
     * ===========================================================================
     * Method : assignDRO()
     * Description : Assign User request to DRO using  IGRS_RTI_REQUEST_TXN . 
     * return type : boolean
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public boolean assignDRO(RTIRequestDTO rtiassign) {
        
        return bo.assignDRO(rtiassign);
    }

     
    
    /**
     * ===========================================================================
     * Method : closeRTIStatus()
     * Description : User Request Status is closed after updating using IGRS_RTI_REQUEST_TXN and Comments is stored in IGRS_RTI_TXN_COMMENTS . 
     * return type : boolean
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    public ArrayList closeRTIStatus(RTIRequestDTO rtistatus) {
              
       return bo.closeRTIStatus(rtistatus);
       
    } 
    
    
    public ArrayList getReqFee(String id,String paramIds[]){
    	
    	return bo.getReqFee(id,paramIds);
    }

   
}

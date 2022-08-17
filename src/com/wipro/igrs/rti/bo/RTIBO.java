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
package com.wipro.igrs.rti.bo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.rti.dao.RTIDAO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;
import com.wipro.igrs.rti.form.RTIRequestForm;

public class RTIBO {
    RTIDAO rtidao = new RTIDAO();
    ArrayList list = new ArrayList();

    public RTIBO() {
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
        return rtidao.getDroName();
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
        return rtidao.getStateList();
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
        return rtidao.getCountryList();
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
        return rtidao.getDistrictList(state);
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
        Date date = new Date();
        String dd = date.toString();
        

        int currentDate = date.getDate();
        int currentMonth = date.getMonth();
        int currentYear = date.getYear();
        int y = 0;
        int yyy = 0;
        currentMonth = currentMonth + 1;
        currentYear = currentYear + 1900;
        
        String s = rtireport.getSearchReportType();
        String startdate = rtireport.getDurationFrom();
        String previousQuarter;
        String beforeOneMonth;
        String beforeThreeMonth;
        String todayDate;
        int fromDate = Integer.parseInt(startdate.substring(0, 2));
        int fromMonth = Integer.parseInt(startdate.substring(3, 5));
        int year = Integer.parseInt(startdate.substring(6));

        int previousMonth = fromMonth - 3;
        if (previousMonth == 0) {
            previousMonth = 12;
            year = year - 1;

        }
        if (previousMonth == -1) {
            previousMonth = 11;
            year = year - 1;

        }
        if (previousMonth == -2) {
            previousMonth = 10;
            year = year - 1;

        }
        y = currentYear;
        yyy = currentYear;
        previousQuarter = fromDate + "-" + previousMonth + "-" + year;
        int oneMonthBefore = currentMonth - 1;
        if (oneMonthBefore == 0) {
            oneMonthBefore = 12;
            y = currentYear - 1;

        }
        int threeMonthBefore = currentMonth - 3;
        if (threeMonthBefore == 0) {
            threeMonthBefore = 12;
            yyy = currentYear - 1;
        }
        if (threeMonthBefore == -1) {
            threeMonthBefore = 11;
            yyy = currentYear - 1;
        }
        if (threeMonthBefore == -2) {
            threeMonthBefore = 10;
            yyy = currentYear - 1;
        }
        todayDate = currentDate + "-" + currentMonth + "-" + currentYear;
        beforeOneMonth = currentDate + "-" + oneMonthBefore + "-" + y;
        beforeThreeMonth = currentDate + "-" + threeMonthBefore + "-" + yyy;
        
        String s1 = rtireport.getSearchAnualType();
       
        ArrayList list = 
            rtidao.getRTIQuarterReport(rtireport, previousQuarter, beforeOneMonth, 
                                       beforeThreeMonth, todayDate);
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
        ArrayList list = rtidao.getRTIReport(rtireport);
        String s = rtireport.getSearchReportType();
        
        String s1 = rtireport.getSearchAnualType();
        
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
    	try{
        String id = rtidao.getSequenceNumber("RTI","RTI");
        return id;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
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
        String id = "Fun-" + new Date().getTime();
        return id;
    }
    
    /**
     * ===========================================================================
     * Method :  rtiRequest()
     * Description : This method store the User information
     * return type : ArrayList
     * Author : Samuel Prabhakaran
     * Created Date : 18 Feb,2008
     * ===========================================================================
     * 
     */
    
    public ArrayList rtiRequest(RTIRequestDTO rtiRequest) {
        String rtiInsert[] = new String[19];
        String rtiInfo[] = new String[4];
        String funId = funIDGenerator();
        String rtiId = rtiIDGenerator();
        String gen = rtiRequest.getGender();
        if (gen.equals("Male")){
            gen = "M";
        }
        else{
            gen = "F";
        }
        rtiInsert[0] = rtiId;
        rtiRequest.setRtiID(rtiId);
        rtiInsert[1] = funId;
        rtiInsert[2] = rtiRequest.getFirstName();
        rtiInsert[3] = rtiRequest.getMiddleName();
        rtiInsert[4] = rtiRequest.getLastName();
        rtiInsert[5] = gen;
        rtiInsert[6] = rtiRequest.getAge();
        rtiInsert[7] = rtiRequest.getFatherName();
        rtiInsert[8] = rtiRequest.getMotherName();
        rtiInsert[9] = rtiRequest.getAddress();
        rtiInsert[10] = rtiRequest.getCountry();
        rtiInsert[11] = rtiRequest.getState();
        rtiInsert[12] = rtiRequest.getDistrict();
        rtiInsert[13] = rtiRequest.getPostelCode();
        rtiInsert[14] = rtiRequest.getPhoneNumber();
        rtiInsert[15] = rtiRequest.getMobileNumber();
        rtiInsert[16] = rtiRequest.getEmailID();
        rtiInsert[17] = rtiRequest.getListID();
        rtiInsert[18] = rtiRequest.getIdProofNumber();
        // rtiInsert[17] = rtiRequest.getRequestInformation();
        rtiInfo[0] = rtiId;
        rtiInfo[1] = rtiRequest.getRequestInformation();
        rtiInfo[2] = "open";
        rtiInfo[3] = rtiRequest.getUserId();
        
        String paymentmap[]=new String[2];
        paymentmap[0]=rtiId;
        paymentmap[1]=rtiRequest.getPaymentTxnId();

        return rtidao.rtiRequest(rtiInsert, rtiInfo,rtiRequest,paymentmap);
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
                              
      return    rtidao.getRTIIntimationFee(requestDTO,paramIds );              
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
    
    return    rtidao.getRTIWorkFee(rtiRequest );              
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
       

           StringTokenizer stoken = new StringTokenizer(_fdate, "/");
                    String dd = stoken.nextToken();
                    String mm = stoken.nextToken();
                    String yy = stoken.nextToken();
                    if (dd.length() == 2) {
                            _fdate = dd + "-" + getMonthName(mm) + "-" + yy;
                    } 

        
        return _fdate;

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
       
        HashMap hm = new HashMap();
        hm.put("01", "JAN");
        hm.put("02", "FEB");
        hm.put("03", "MAR");
        hm.put("04", "APR");
        hm.put("05", "MAY");
        hm.put("06", "JUN");
        hm.put("07", "JUL");
        hm.put("08", "AUG");
        hm.put("09", "SEP");
        hm.put("10", "OCT");
        hm.put("11", "NOV");
        hm.put("12", "DEC");
        return (String)hm.get(_month);
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
        String inputDate = dFromDate;
        // LoggerMsg.info(inputDate);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        String finalDate = "";
        Date newDate = new Date();
        try {

            newDate = formatter.parse(inputDate);


            SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");

            finalDate = format.format(newDate);


            //  LoggerMsg.info(finalDate);


        } catch (ParseException e) {

            System.out.print(e);


        }

        return finalDate;

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
        try{
        ArrayList list1 = new ArrayList();
        ArrayList list = rtidao.getComments(refId);
        
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ArrayList lst = (ArrayList)list.get(i);
                RTIRequestDTO dto = new RTIRequestDTO();
                dto.setRtiID((String)lst.get(0));
                dto.setResolutionInformation((String)lst.get(2));
                list1.add(dto);
            }
        }
        return list1;
        } catch (Exception e) {
        return null;
        }
        
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
        try {
            ArrayList list1 = new ArrayList();
            ArrayList list = 
                rtidao.statusInfo(refId, fromDate, toDate, status);


            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ArrayList lst = (ArrayList)list.get(i);
                    RTIRequestForm form = new RTIRequestForm();
                    form.getRTIRequestDTO().setRtiID((String)lst.get(0));
                    form.getRTIRequestDTO().setFirstName((String)lst.get(1));
                    form.getRTIRequestDTO().setMiddleName((String)lst.get(2));
                    form.getRTIRequestDTO().setLastName((String)lst.get(3));
                    form.getRTIRequestDTO().setGender((String)lst.get(4));
                    form.getRTIRequestDTO().setAge((String)lst.get(5));
                    form.getRTIRequestDTO().setFatherName((String)lst.get(6));
                    form.getRTIRequestDTO().setMotherName((String)lst.get(7));
                    //form.getRTIRequestDTO().setSpouseName((String)lst.get(19));
                    form.getRTIRequestDTO().setAddress((String)lst.get(8));
                    form.getRTIRequestDTO().setDistrict((String)lst.get(9));
                    form.getRTIRequestDTO().setCountry((String)lst.get(10));
                    form.getRTIRequestDTO().setState((String)lst.get(11));
                    form.getRTIRequestDTO().setPostelCode((String)lst.get(12));
                    form.getRTIRequestDTO().setPhoneNumber((String)lst.get(13));
                    form.getRTIRequestDTO().setMobileNumber((String)lst.get(14));
                    form.getRTIRequestDTO().setEmailID((String)lst.get(15));
                   // dto.setListID((String)lst.get(17));
                    form.getRTIRequestDTO().setRequestInformation((String)lst.get(16));
                   /// dto.setResolutionInformation((String)lst.get(17));
                    //dto.setResolutionRemark((String)lst.get(20));
                    form.getRTIRequestDTO().setRtiStatus((String)lst.get(17));
                   /// dto.setDroName((String)lst.get(19));
                   // dto.setUserId((String)lst.get(19)); we need this for DroName
                    form.getRTIRequestDTO().setRequestDate((String)lst.get(18));
                    form.getRTIRequestDTO().setListID((String)lst.get(19));
                    form.getRTIRequestDTO().setIdProofNumber((String)lst.get(20));
                    form.getRTIRequestDTO().setReplycomments((String)lst.get(21));
                    form.getRTIRequestDTO().setDroName((String)lst.get(22));
                    form.getRTIRequestDTO().setDueDate((String)lst.get(23));
                    
                    form.getRTIRequestDTO().setChallanNo((String)lst.get(24));
                   
                    form.getRTIRequestDTO().setChallanDate((String)lst.get(25));
                    
                    form.getRTIRequestDTO().setAmount((String)lst.get(26));
                  
                   /// dto.setResolutionDate((String)lst.get(21));
                    list1.add(form);
                }
            }
            return list1;
        } catch (Exception e) {
            return null;
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
    	try {
            ArrayList list1 = new ArrayList();
            ArrayList list = rtidao.getIntimation(refId);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    ArrayList lst = (ArrayList)list.get(i);
                    RTIRequestForm form = new RTIRequestForm();
                    form.getRTIRequestDTO().setRtiID((String)lst.get(0));
                    form.getRTIRequestDTO().setIntimationTypeId((String)lst.get(1));
                    
                    form.getRTIRequestDTO().setIntimationTypeValue((String)lst.get(2));
                    list1.add(form);
                }
            }
                    
    	return list1;
    	}catch(Exception e){
    		return null;
    	}
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
        return rtidao.getphotoIdList();
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
        String dro[] = new String[6];
        String droAssign[]=new String[2];
        dro[0] = rtiassign.getReplycomments();
        dro[1] = rtiassign.getDroName();
        dro[2] = getDate(rtiassign.getDueDate());
        String status=rtiassign.getReplyStatus();
        dro[3]= status;
        if(status.equalsIgnoreCase("Assign")){
        	dro[4]="open";
        }
        if(status.equalsIgnoreCase("Refuse")){
        	dro[4]="close";
        } 
        dro[5] = rtiassign.getRtiID();
        
        droAssign[1]=rtiassign.getRtiID();
        
        return rtidao.assignDRO(dro);
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
       
  
        String param[] = new String[3];
        String param1[]=new String[2];
        param[0] = rtistatus.getRtiID();
        param[1] = rtistatus.getDroName();
        param[2] = rtistatus.getResolutionInformation();
        param1[0] = rtistatus.getNumberOfPages();
        param1[1] = rtistatus.getNumberOfHour();
       return rtidao.closeRTIStatus(param, param1);
       
    } 
    
    public ArrayList getReqFee(String id,String paramIds[]){
    	
    	return rtidao.getReqFee(id,paramIds);
    }
}

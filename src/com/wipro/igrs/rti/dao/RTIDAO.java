/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RTIDAO.java
 * Author      :   Samuel Prabhakaran
 * 
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Samuel Prabhakaran   17th March, 2008	
 * 2.0		   Imran Shaik		17th October,2008	removed all static values for fee   		
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.rti.dao;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.rti.constants.Constants;
import com.wipro.igrs.rti.dto.IGRSCountryDTO;
import com.wipro.igrs.rti.dto.RTIRequestDTO;
import com.wipro.igrs.rti.sql.CommonSQL;



public class RTIDAO {

    DBUtility dbUtil;
    private  Logger logger = 
		(Logger) Logger.getLogger(RTIDAO.class);

    public RTIDAO() {
       
    }
    
    /**

     * ===========================================================================

     * Method : getSequenceNumber()

     * Description : Generate RTI Sequence Id . 

     * return type : String

     * Author : Samuel Prabhakaran

     * Created Date : 6 June,2008

     * ===========================================================================

     * 

     */
    
    
    
    
    public String getSequenceNumber(String _seqName,String _moduleConstant) throws Exception {    

        String seqnumber="";

        try{
        
        	dbUtil = new DBUtility();	

        	Date date=new Date();       

        	Format yearformat= new SimpleDateFormat("yyyy");   

        	Format monthformat= new SimpleDateFormat("MM");  

        	Format dateformat= new SimpleDateFormat("dd");  

        	String dfmt=  dateformat.format(date);

        	String yfmt=  yearformat.format(date);

        	String mfmt=  monthformat.format(date);      

        	String SQL =  "select "+_seqName+".nextval from dual";

        	dbUtil.createStatement();
 

        	String number= dbUtil.executeQry(SQL);

        	seqnumber=_moduleConstant+dfmt+mfmt+yfmt+number;
         
        }catch(Exception e)

        {

            logger.debug("Exception occur in RTIDAO: " + e);

        }finally{

            if(dbUtil!=null){

            

                  dbUtil.closeConnection();

            }

            

        }

        return seqnumber;

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


        
        ArrayList idlist = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        RTIRequestDTO dto;
        try {
            dbUtil = new DBUtility();


            dbUtil.createStatement();
            list2 = dbUtil.executeQuery(CommonSQL.IGRS_ID_TYPE);
            for (int i = 0; i < list2.size(); i++) {
                list1 = (ArrayList)list2.get(i);

                dto = new RTIRequestDTO();
                dto.setCommonID((String)list1.get(0));
                dto.setIdName((String)list1.get(1));
                idlist.add(dto);

            }
                


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTIDAO: " +  ex);

            
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTIDAO: " +  ex);

                ex.printStackTrace();
            }
        }


        return idlist;
    }
    
    /**

     * ===========================================================================

     * Method : getDroName()

     * Description : Getting all DRO Name from IGRS_USERS_LIST . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */


    public ArrayList getDroName() {
        ArrayList drnamelist = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();

        try {
            dbUtil = new DBUtility();
            RTIRequestDTO droname;

            dbUtil.createStatement();
            list2 = dbUtil.executeQuery(CommonSQL.EMPLOYEE);

            for (int i = 0; i < list2.size(); i++) {
                list1 = (ArrayList)list2.get(i);
                droname = new RTIRequestDTO();
                droname.setDroName((String)list1.get(0));
                               
                drnamelist.add(droname);
            }

        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }
        return drnamelist;
    } 
    
    
    /**

     * ===========================================================================

     * Method : getDistrictList()

     * Description : Selecting all District from IGRS_DISTRICT_MASTER  . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */


    public ArrayList getDistrictList(String state) {
        ArrayList countryList = 
            new ArrayList();


        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();

        try {
            dbUtil = new DBUtility();
            IGRSCountryDTO country;
            dbUtil.createStatement();

            list2 = 
                    dbUtil.executeQuery(CommonSQL.DISTRICT + "STATE_ID='" + state + 
                                        "'");

            for (int i = 0; i < list2.size(); i++) {
                list1 = (ArrayList)list2.get(i);


                country = new IGRSCountryDTO();
                country.setDistrictId((String)list1.get(0));
                
                country.setDistrictName((String)list1.get(1));
                
                countryList.add(country);

            }

              


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }


        return countryList;
    }

   
    /**

     * ===========================================================================

     * Method : getStateList()

     * Description : Selecting all State from IGRS_STATE_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
    
    public ArrayList getStateList() {
        ArrayList stateList = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();

        try {
            dbUtil = new DBUtility();
            IGRSCountryDTO country;
            dbUtil.createStatement();

            list2 = dbUtil.executeQuery(CommonSQL.STATE);

            for (int i = 0; i < list2.size(); i++) {
                list1 = (ArrayList)list2.get(i);

                country = new IGRSCountryDTO();
                country.setStateId((String)list1.get(0));
                country.setStateName((String)list1.get(1));
                stateList.add(country);

            }

         
            


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }


        return stateList;
    }
    
    
    /**

     * ===========================================================================

     * Method : getCountryList()

     * Description : Selecting all Country from IGRS_COUNTRY_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */

    public ArrayList getCountryList() {
        ArrayList countryList = 
            new ArrayList();


        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();

        try {
            dbUtil = new DBUtility();
            IGRSCountryDTO country;
            dbUtil.createStatement();

            list2 = dbUtil.executeQuery(CommonSQL.COUNTRY);

            for (int i = 0; i < list2.size(); i++) {
                list1 = (ArrayList)list2.get(i);


                country = new IGRSCountryDTO();
                country.setCountryId((String)list1.get(0));
                country.setCountry((String)list1.get(1));
                countryList.add(country);

            }

           
           


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

               ex.printStackTrace();
            }
        }


        return countryList;
    }
    
    
    /**

     * ===========================================================================

     * Method : rtiRequest()

     * Description : Insert user details , user request information and intimation type details into IGRS_RTI_TXN_PARTY_DETAILS,IGRS_RTI_REQUEST_TXN,IGRS_RTI_INTIMATION_MAPPING . 

     * return type : ArrayList

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
    

    public ArrayList rtiRequest( String[] details, 
                              String[] info,RTIRequestDTO rtiRequest,String[] paymentMap) {
        boolean bol = false;
		ArrayList list=null;
        try {
            dbUtil = new DBUtility();
            
            dbUtil.createPreparedStatement(CommonSQL.REQUEST_RTI_DETAILS);
            bol = dbUtil.executeUpdate(info);
            
            if(bol) {           
            dbUtil.createPreparedStatement(CommonSQL.REQUEST_RTI);
            bol = dbUtil.executeUpdate(details);
            }
            if(bol){
			dbUtil.createPreparedStatement(CommonSQL.RTI_PAYMENT);
            bol = dbUtil.executeUpdate(paymentMap);
            
            }
			dbUtil.createStatement();
            String id=details[0];
            list = 
                    dbUtil.executeQuery(CommonSQL.RTI_REQUEST_DATE+" WHERE TRANSACTION_ID='"+id+"'");
            
            String inti[]=new String[3];
            if(bol){
            if(("E-MailId").equals(rtiRequest.getEmailId())){
                
                inti[0]=info[0];
                inti[1]="1";
                inti[2]=rtiRequest.getTextEmailId();
               
                
                dbUtil.createPreparedStatement(CommonSQL.RTI_INTIMATION_MAPPING);
                bol = dbUtil.executeUpdate(inti);
                

            }
            if(("Mobile-No").equals(rtiRequest.getSms())){
                inti[0]=info[0];
                inti[1]="2";
                inti[2]=rtiRequest.getTextSMS();
               
                
                dbUtil.createPreparedStatement(CommonSQL.RTI_INTIMATION_MAPPING);
                bol = dbUtil.executeUpdate(inti);
               

            }
            if(("Post").equals(rtiRequest.getPost())){
                inti[0]=info[0];
                inti[1]="3";
                inti[2]=rtiRequest.getPostaddress();
               
                
                dbUtil.createPreparedStatement(CommonSQL.RTI_INTIMATION_MAPPING);
                bol = dbUtil.executeUpdate(inti);
                

            }
            }
            if(bol)
            dbUtil.commit();
            else
            dbUtil.rollback();	

        } catch (Exception ex) {
        	try{
        		dbUtil.rollback();	
        	}catch(Exception x){
        	
        	logger.debug("Exception occur in RTI: " + x);

            ex.printStackTrace();
        	}
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);
                ex.printStackTrace();
                
            }
        }
        return list;

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
        ArrayList intimation =new ArrayList();                     
       
              
            ArrayList list1 = new ArrayList();
            ArrayList list2 = new ArrayList();
            
            try {
            	
                IGRSCommon  common2 =  new IGRSCommon();
           	 
                dbUtil = new DBUtility();
               
                dbUtil.createStatement();

                
                list2= common2.getOthersFeeDuty("1001","4","SRO");
                rtiRequest.setPageIntimationFee((String)list2.get(0));
                rtiRequest.setPageIntimationOthers((String)list2.get(1));
                rtiRequest.setPageIntimationTotal((String)list2.get(2));
                               
                
            
                
                dbUtil = new DBUtility();
                
                dbUtil.createStatement();

                common2 =  new IGRSCommon();
                list1= common2. getOthersFeeDuty("1001","5","SRO");
               
                rtiRequest.setHourIntimationFee((String)list1.get(0));
                rtiRequest.setHourIntimationOthers((String)list1.get(1));
                rtiRequest.setHourIntimationTotal((String)list1.get(2));
                               
                
               
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

               ex.printStackTrace();
            } finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);
                	
                    ex.printStackTrace();
                }
            }
              
        intimation.add(rtiRequest);
        return intimation;
                                 
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
    
    
    
  public ArrayList getRTIIntimationFee(RTIRequestDTO rtiRequest,String  paramIds[]){
         ArrayList intimation =new ArrayList();                     
        
         	 ArrayList list3 = new ArrayList();  
             ArrayList list1 = new ArrayList();
             ArrayList list2 = new ArrayList();
             int totalfee=0;
             
             try {
            	 
                 IGRSCommon  common2 =  new IGRSCommon();
                
                 dbUtil = new DBUtility();
                 dbUtil.createStatement();
                 list1= common2.getOthersFeeDuty(paramIds[1],Constants.EMAIL_SERVICE_ID,paramIds[2]);
                 rtiRequest.setEmailIntimationFee((String)list1.get(0));
                 rtiRequest.setEmailIntimationOthers((String)list1.get(1));
                 rtiRequest.setEmailIntimationTotal((String)list1.get(2));
                 dbUtil.closeConnection();

                 dbUtil = new DBUtility();
                 dbUtil.createStatement();
                 list2= common2.getOthersFeeDuty(paramIds[1],Constants.SMS_SERVICE_ID,paramIds[2]);
                 rtiRequest.setSmsIntimationFee((String)list2.get(0));
                 rtiRequest.setSmsIntimationOthers((String)list2.get(1));
                 rtiRequest.setSmsIntimationTotal((String)list2.get(2));
                 dbUtil.closeConnection();
                 
                 dbUtil = new DBUtility();
                 dbUtil.createStatement();
                 list3= common2.getOthersFeeDuty(paramIds[1],Constants.POST_SERVICE_ID,paramIds[2]);
                 rtiRequest.setPostIntimationFee((String)list3.get(0));
                 rtiRequest.setPostIntimationOthers((String)list3.get(1));
                 rtiRequest.setPostIntimationTotal((String)list3.get(2));
                     
                 dbUtil = new DBUtility();
                 dbUtil.createStatement();
                 //list2= common2.getOthersFeeDuty(paramIds[1],"6",paramIds[2]);//Constants.OTHERS_SERVICE_ID
                 list2= common2.getOthersFeeDuty(paramIds[1],Constants.OTHERS_SERVICE_ID,paramIds[2]);
                 rtiRequest.setAppealIntimationFee((String)list2.get(0));
                 rtiRequest.setAppealIntimationOthers((String)list2.get(1));
                 rtiRequest.setAppealIntimationTotal((String)list2.get(2));
                
             } catch (Exception ex) {
             	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
             } finally {
                 try {
                     dbUtil.closeConnection();
                 } catch (Exception ex) {
                 	logger.debug("Exception occur in RTI: " + ex);
                 	
                     ex.printStackTrace();
                 }
             }
         
       
         intimation.add(rtiRequest);
         return intimation;
                                  
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
    
    
    public boolean assignDRO(String[] dro) {
        boolean bol = false; 
        
        try {

           


            dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.RTI_ASSIGN);
            bol = dbUtil.executeUpdate(dro);
            
            if(bol)
            dbUtil.commit();
            else
            	dbUtil.rollback();	
            
            


        } catch (Exception ex) {
        	try{
        		dbUtil.rollback();	
        	}catch(Exception x){
        	
        	logger.debug("Exception occur in RTI: " + x);

            ex.printStackTrace();
        	}
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

            	ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
        }
        return bol;

    }
    
    
    /**

     * ===========================================================================

     * Method : closeRTIStatus()

     * Description : User Request Status is closed after updating using IGRS_RTI_REQUEST_TXN and Comments is stored in IGRS_RTI_TXN_COMMENTS . 

     * return type : ArrayList

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
  

    public ArrayList closeRTIStatus(String[] param,String[] parameter) {
    
    	boolean bol=false;
		ArrayList   list2=null;
                try {
                    dbUtil = new DBUtility();
                    
                    String[] param1=new String[4];
                    param1[0]="close";
                    param1[1]=parameter[0];
                    param1[2]=parameter[1];
                    param1[3]=param[0];
                   
                    dbUtil.createPreparedStatement(CommonSQL.INSERT_RESOLUTION);
                    bol = dbUtil.executeUpdate(param);
                    
                    if(bol){
                    dbUtil.createPreparedStatement(CommonSQL.SAVE_CLOSE);
                    bol = dbUtil.executeUpdate(param1);
                    }
                   
                    if(bol)
                    	 dbUtil.commit();
                    else
                    	dbUtil.rollback();
					dbUtil.createStatement();
                    String id=param[0];
                   
                    list2 = 
                            dbUtil.executeQuery(CommonSQL.RTI_UPDATE_DATE+" WHERE TRANSACTION_ID='"+id+"'");
                    
                    
                    
                } catch (Exception ex) {
                	try{
                		dbUtil.rollback();	
                	}catch(Exception x){
                	
                	logger.debug("Exception occur in RTI: " + x);

                    ex.printStackTrace();
                	}
                }finally {
                    try {
                        dbUtil.closeConnection();
                    } catch (Exception ex) {
                    	logger.debug("Exception occur in RTI: " + ex);

                        ex.printStackTrace();
                    }
                }
    
        return list2;


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

    public ArrayList getRTIQuarterReport(RTIRequestDTO rtiReport, 
                                         String previousQuarter, 
                                         String beforeOneMonth, 
                                         String beforeThreeMonth, 
                                         String todayDate) {
        String report = rtiReport.getSearchReportType();
        

        String annual = rtiReport.getSearchAnualType();
        
        String datefrom = rtiReport.getDurationFrom();
        String dateto = rtiReport.getDurationTo();
        

        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();


        try {


            String total = "0";
            String received = "0";
            String closed = "0";
            String openOnEndDate = "0";
            String openOnStartDate = "0";
            String openUptoOneMonth = "0";
            String openOneToThreeMonth = "0";
            String beyondThreeMonth = "0";
            String totalOpen = "0";


            dbUtil = new DBUtility();
            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + "TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + "','DD/MM/YYYY'))");


            list1 = (ArrayList)list2.get(0);
            received = (String)list1.get(0);


            

            dbUtil.createStatement();

            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + "TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        beforeOneMonth + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        todayDate + 
                                        "','DD/MM/YYYY') ) AND RTI_STATUS='open'");
            list1 = (ArrayList)list2.get(0);
            openUptoOneMonth = (String)list1.get(0);

            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        beforeThreeMonth + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        beforeOneMonth + 
                                        "','DD/MM/YYYY') ) AND RTI_STATUS='open'");

            list1 = (ArrayList)list2.get(0);
            openOneToThreeMonth = (String)list1.get(0);

            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) < TO_CHAR(TO_DATE('" + 
                                        beforeThreeMonth + 
                                        "','DD/MM/YYYY')) AND RTI_STATUS='open'");

            list1 = (ArrayList)list2.get(0);
            beyondThreeMonth = (String)list1.get(0);

            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + 
                                        "','DD/MM/YYYY')) AND RTI_STATUS='close'");

            list1 = (ArrayList)list2.get(0);
            closed = (String)list1.get(0);

            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        previousQuarter + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + 
                                        "','DD/MM/YYYY')) AND RTI_STATUS='open'");

            list1 = (ArrayList)list2.get(0);
            openOnEndDate = (String)list1.get(0);

            dbUtil.createStatement();
            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        previousQuarter + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        datefrom + "','DD/MM/YYYY') ) ");

            list1 = (ArrayList)list2.get(0);
            openOnStartDate = (String)list1.get(0);

            
            total = 
                    "" + (Integer.parseInt(openOnStartDate) + Integer.parseInt(received));
            totalOpen = 
                    "" + (Integer.parseInt(openUptoOneMonth) + Integer.parseInt(openOneToThreeMonth) + 
                          Integer.parseInt(beyondThreeMonth));
            
            rtiReport.setOpenOnStartDate(openOnStartDate);
            rtiReport.setReceived(received);
            rtiReport.setTotal(total);
            rtiReport.setClosed(closed);
            rtiReport.setOpenOnEndDate(openOnEndDate);
            rtiReport.setOpenUptoOneMonth(openUptoOneMonth);
            rtiReport.setOpenOneToThreeMonth(openOneToThreeMonth);
            rtiReport.setBeyondThreeMonth(beyondThreeMonth);
            rtiReport.setTotalOpen(totalOpen);
            list.add(rtiReport);


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        }finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }
        return list;
    }
    
    
    
    
    
  public String getRTIAppealFee(){
    	
    	
        ArrayList list2 = new ArrayList();
       
        
        try {

    	 dbUtil = new DBUtility();
         
         dbUtil.createStatement();
         
         float total=0;
         String param_name="0";
         int param_value=0;

         
         IGRSCommon  common2 =  new IGRSCommon();
     	 
         dbUtil = new DBUtility();
        
         dbUtil.createStatement();

         
         //list2= common2.getOthersFeeDuty("1001","6","SRO");   //    Constants.OTHERS_SERVICE_ID    
         list2= common2.getOthersFeeDuty("1001",Constants.OTHERS_SERVICE_ID,"SRO");
         total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
         
         
        return Float.toString(total);
    }catch (Exception ex) {
    	logger.debug("Exception occur in RTI: " + ex);

        ex.printStackTrace();
     }finally {
         try {
             dbUtil.closeConnection();
         } catch (Exception ex) {
         	logger.debug("Exception occur in RTI: " + ex);

             ex.printStackTrace();
         }
     }
     return null;
    }
    
    
    /**

     * ===========================================================================

     * Method : rtiHourIntimationFee()

     * Description : getting Hour intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
    
    public String getRTIHourFee(){
                    
           
            ArrayList list2 = new ArrayList();
                       
            try {
           	                 
               float total=0;
               String param_name="0";
               int param_value=0;
                              
               IGRSCommon  common2 =  new IGRSCommon();
              
               dbUtil = new DBUtility();
              
               dbUtil.createStatement();
         
               list2= common2.getOthersFeeDuty("1001","5","SRO");                  
              
               total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
                             
                
                return Float.toString(total);
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

               ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
            return null;
    }
    
    
    /**

     * ===========================================================================

     * Method : rtiPageIntimationFee()

     * Description : getting page intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */

   public String getRTIPageFee(){
                      
            ArrayList list2 = new ArrayList();
            String totalfee="0";
            
            try {
           	     
                
                float total=0;
               String param_name="0";
               int param_value=0;
               IGRSCommon  common2 =  new IGRSCommon();
             	 
               dbUtil = new DBUtility();
              
               dbUtil.createStatement();
               
               list2= common2.getOthersFeeDuty("1001","4","SRO");
                             
                total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
                          
                return Float.toString(total);
            }catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
            return null;
    }
    
    /**

     * ===========================================================================

     * Method : rtiEmailIntimationFee()

     * Description : getting Email intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
       
    public String getRTIEmailIntimationFee(){
        ArrayList intimation =new ArrayList();                     
                  
            ArrayList list2 = new ArrayList();
            String totalfee="0";
            
            try {
           	                 
                float total=0;
                String param_name="0";
                int param_value=0;
                     
                IGRSCommon  common2 =  new IGRSCommon();
            	 
                dbUtil = new DBUtility();
               
                dbUtil.createStatement();
                
                list2= common2.getOthersFeeDuty("1001","2","SRO");
                               
                total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
                     
                 return Float.toString(total);
            }catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
      return null;
    }
    
    
    /**

     * ===========================================================================

     * Method : rtiSMSIntimationFee()

     * Description : getting SMS intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */
    
    
    
    public String getRTISMSIntimationFee(){
        ArrayList intimation =new ArrayList();                     
                      
            ArrayList list2 = new ArrayList();
            String totalfee="0";
            
            try {
           	                   
                float total=0;
                String param_name="0";
                int param_value=0;
                     
                IGRSCommon  common2 =  new IGRSCommon();
           	 
                dbUtil = new DBUtility();
               
                dbUtil.createStatement();
                
                list2= common2.getOthersFeeDuty("1001","1","SRO");                
                            
                total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
                               
                return Float.toString(total);
            }catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
            return null;
    }
    
    /**

     * ===========================================================================

     * Method : rtiPostIntimationFee()

     * Description : getting post intimation fee from IGRS_SERVICE_FEE_PARAM_MAPPING,IGRS_SERVICE_FEE_PARAM_MASTER,IGRS_FUNCTION_MASTER . 

     * return type : Arraylist

     * Author : Samuel Prabhakaran

     * Created Date : 18 Feb,2008

     * ===========================================================================

     * 

     */

     public String getRTIPostIntimationFee(){
        ArrayList intimation =new ArrayList();                     
                 
            ArrayList list2 = new ArrayList();
            String totalfee="0";
            
            try {
           	                 
                float total=0;
                String param_name="0";
                int param_value=0;
                     
                IGRSCommon  common2 =  new IGRSCommon();
              	 
                dbUtil = new DBUtility();
               
                dbUtil.createStatement();
                
                list2= common2.getOthersFeeDuty("1001","3","SRO");                   
                       
                total=total+Float.parseFloat((String)list2.get(0))+Float.parseFloat((String)list2.get(1))+Float.parseFloat((String)list2.get(2));
                           
                return Float.toString(total);
            }catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }finally {
                try {
                    dbUtil.closeConnection();
                } catch (Exception ex) {
                	logger.debug("Exception occur in RTI: " + ex);

                    ex.printStackTrace();
                }
            }
            return null;
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


    public ArrayList getRTIReport(RTIRequestDTO rtiReport) {
        String report = rtiReport.getSearchReportType();
        

        String annual = rtiReport.getSearchAnualType();
        
        String datefrom = rtiReport.getDurationFrom();
        String dateto = rtiReport.getDurationTo();
        
        

        ArrayList list = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();


        //String sql="SELECT COUNT(*) FROM IGRS_RTI_INFO WHERE REQUEST_DATE BETWEEN(?,?)";
        try {


            String total = "0";
            String received = "0";
            String closed = "0";
            String openOnEndDate = "0";
            String openOnStartDate = "0";
            String totalInformationFee = "0";
            String totalAppealFee="0";
            String totalFee="0";
            String totalFixedFee="0";
            String totalEmail="0";
            String totalSMS="0";
            String totalPost="0";
            String totalPage="0";
            String totalHour="0";
            String totalAppeal="0";
            String rtiEmailIntimation="0";
            String rtiSMSIntimation="0";
            String rtiPostIntimation="0";
            String rtiPageInformation="0";
            String rtiHourInformation="0";
            String rtiAppeal="0";
            
            

            dbUtil = new DBUtility();
            dbUtil.createStatement();

            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + "','DD/MM/YYYY'))");
            list1 = (ArrayList)list2.get(0);
            received = (String)list1.get(0);
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + 
                                        "','DD/MM/YYYY')) AND RTI_STATUS='close'");

            list1 = (ArrayList)list2.get(0);
            closed = (String)list1.get(0);
           

            dbUtil.createStatement();

            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) < TO_CHAR(TO_DATE('" + 
                                        dateto + 
                                        "','DD/MM/YYYY')) AND RTI_STATUS='open'");

            list1 = (ArrayList)list2.get(0);
            openOnEndDate = (String)list1.get(0);
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.COUNT + " TRUNC(CREATED_DATE) < TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') )  AND RTI_STATUS='open'");

            list1 = (ArrayList)list2.get(0);
            openOnStartDate = (String)list1.get(0);
           
            
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_EMAIL_FEE +" AND TRUNC(B.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                                        datefrom + 
                                        "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                                        dateto + 
                                        "','DD/MM/YYYY'))");

            list1 = (ArrayList)list2.get(0);
            totalEmail = (String)list1.get(0);
         
            
            
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_SMS_FEE+" AND TRUNC(B.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                            datefrom + 
                            "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                            dateto + 
                            "','DD/MM/YYYY'))");

            list1 = (ArrayList)list2.get(0);
            totalSMS = (String)list1.get(0);
            
          
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_POST_FEE+" AND TRUNC(B.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                            datefrom + 
                            "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                            dateto + 
                            "','DD/MM/YYYY'))" );

            list1 = (ArrayList)list2.get(0);
            totalPost = (String)list1.get(0);
           
            
            
            
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_PAGE_FEE+" TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                            datefrom + 
                            "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                            dateto + 
                            "','DD/MM/YYYY'))" );

            list1 = (ArrayList)list2.get(0);
            totalPage = (String)list1.get(0);
           

            
            
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_HOUR_FEE+" TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                            datefrom + 
                            "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                            dateto + 
                            "','DD/MM/YYYY'))" );

            list1 = (ArrayList)list2.get(0);
            totalHour = (String)list1.get(0);
            
            
            dbUtil.createStatement();


            list2 = 
                    dbUtil.executeQuery(CommonSQL.RTI_APPEAL+" TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + 
                            datefrom + 
                            "','DD/MM/YYYY') ) AND TO_CHAR(TO_DATE('" + 
                            dateto + 
                            "','DD/MM/YYYY'))" );

            list1 = (ArrayList)list2.get(0);
            totalAppeal = (String)list1.get(0);
           
            rtiPageInformation=getRTIPageFee();
           
            rtiHourInformation=getRTIHourFee();
            
            rtiEmailIntimation=getRTIEmailIntimationFee();
            rtiSMSIntimation=getRTISMSIntimationFee();
            rtiPostIntimation=getRTIPostIntimationFee();
            rtiAppeal=getRTIAppealFee();
            
            
            
            
            total = 
                    "" + (Float.parseFloat(openOnStartDate) + Float.parseFloat(received));
            
            
            totalAppealFee=""+Integer.parseInt(totalAppeal)*Float.parseFloat(rtiAppeal);
            if(!totalHour.equalsIgnoreCase("")&& !totalPage.equalsIgnoreCase("")){
            totalInformationFee =""+(Integer.parseInt(totalPage)*Float.parseFloat(rtiPageInformation)+Integer.parseInt(totalHour)*Float.parseFloat(rtiHourInformation));
            }
            totalFixedFee =""+(Integer.parseInt(totalEmail)*Float.parseFloat(rtiEmailIntimation)+Integer.parseInt(totalSMS)*Float.parseFloat(rtiSMSIntimation)+Integer.parseInt(totalPost)*Float.parseFloat(rtiPostIntimation));
            totalFee=""+(Float.parseFloat(totalFixedFee)+Float.parseFloat(totalInformationFee));
            rtiReport.setOpenOnStartDate(openOnStartDate);
            rtiReport.setReceived(received);
            rtiReport.setTotal(total);
            rtiReport.setClosed(closed);
            rtiReport.setOpenOnEndDate(openOnEndDate);
            rtiReport.setTotalFixedFee(totalFixedFee);
            rtiReport.setTotalAppealFee(totalAppealFee);
            
            rtiReport.setTotalInformationFee(totalInformationFee);
            rtiReport.setTotalfee(totalFee);
            list.add(rtiReport);


        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        }finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }
        return list;
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
        ArrayList list = new ArrayList();
        String SQL = CommonSQL.RTI_TXN_COMMENTS;
        
        try {
            dbUtil = new DBUtility();
            
            dbUtil.createStatement();
 

        list = dbUtil.executeQuery(SQL);
        if (list != null) {
        
            for (int i = 0; i < list.size(); i++) {
                ArrayList lst = (ArrayList)list.get(i);
                
            }
        }

        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

        	ex.printStackTrace();
        } finally {
        try {
            dbUtil.closeConnection();
        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

            ex.printStackTrace();
        }
        }
        return list;
            
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
        ArrayList list = new ArrayList();
        String SQL = CommonSQL.RTI_STATUS;
        
        try {
            dbUtil = new DBUtility();
            
            dbUtil.createStatement();
            

            //SQL = SQL + " where  RTI_ID like '%RTI%' ";


            if (refId != null && !"".equals(refId.trim())) {
                SQL = SQL + " AND A.RTI_TXN_ID='" + refId.trim() + "'";
            }
            if ((status != null && !"".equals(status.trim())) && 
                !status.trim().equals("Select")) {
                SQL = SQL + " AND UPPER(B.RTI_STATUS)=UPPER('" + status + "')";
            }

            if (fromDate != null && !"".equals(fromDate.trim()) && 
                toDate != null && !"".equals(toDate.trim())) {

                
                SQL = 
SQL + " AND TRUNC(B.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
  "','dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yyyy'))";

             } else if (fromDate != null && !"".equals(fromDate.trim()) && 
               ( toDate == null || "".equals(toDate.trim()))) {

                
                SQL = 
SQL + " AND TRUNC(B.CREATED_DATE) >= TO_CHAR(TO_DATE('" + fromDate + "','dd/mm/yyyy'))";

            } else if ((fromDate == null || "".equals(fromDate.trim())) && 
                toDate != null && !"".equals(toDate.trim())) {

                
                SQL = 
SQL + " AND TRUNC(B.CREATED_DATE) <= TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yyyy'))";

            }                    
            list = dbUtil.executeQuery(SQL);
            

        } catch (Exception ex) {
        	logger.debug("Exception occur in RTI: " + ex);

           ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }
        return list;
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
    	ArrayList list = new ArrayList();
    	String SQL = CommonSQL.GET_RTI_INTIMATION;
    	
    	try {
            dbUtil = new DBUtility();
            
            dbUtil.createStatement();
            
            list = dbUtil.executeQuery(SQL);
            
    	}catch(Exception ex){
        	logger.debug("Exception occur in RTI: " + ex);

    		ex.printStackTrace();
    	}finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
            	logger.debug("Exception occur in RTI: " + ex);

                ex.printStackTrace();
            }
        }
    	return list;
    }

	
	/**

     * ===========================================================================

     * Method :   getReqFee()

     * Description : This method get the RequestFee .

     * return type : ArrayList

     * Author : Samuel Prabhakaran

     * Created Date : 20 June,2008

     * ===========================================================================

     * 

     */



	public ArrayList getReqFee(String id,String paramIds[]){
    	ArrayList feelist=new ArrayList();
    	  try {
    	dbUtil = new DBUtility();
    	dbUtil.createStatement();
        float reqFee=0;
        float reqOthers=0;
        float reqTotal=0;
        dbUtil = new DBUtility();
        
        dbUtil.createStatement();

        IGRSCommon common2 =  new IGRSCommon();
//      ArrayList list2= common2.getOthersFeeDuty(paramIds[1],"6",paramIds[2]); //Constants.OTHERS_SERVICE_ID
        ArrayList list2= common2.getOthersFeeDuty(paramIds[1],Constants.OTHERS_SERVICE_ID,paramIds[2]);
        if(list2!=null){
        	reqFee= reqFee+Float.parseFloat((String)list2.get(0));
        	reqOthers=reqOthers+Float.parseFloat((String)list2.get(1));
        	reqTotal=reqTotal+Float.parseFloat((String)list2.get(2));
        }
      ArrayList list=  dbUtil.executeQuery(CommonSQL.INTIMATION_TYPE_ID+" WHERE TRANSACTION_ID='"+id+"'");
      
      RTIRequestDTO dto=new RTIRequestDTO();
          if(list!=null){
        	  for(int i=0;i<list.size();i++){
        		  ArrayList list1=(ArrayList)list.get(i);
        		  String intimationid=(String)list1.get(0);
        		  //if(intimationid.equalsIgnoreCase("1")){
        		  if(intimationid.equalsIgnoreCase("SMS")){
        			  common2 =  new IGRSCommon();
        		         list2= common2.getOthersFeeDuty(paramIds[1],Constants.SMS_SERVICE_ID,paramIds[2]);
        		        if(list2!=null){
        		        	reqFee= reqFee+Float.parseFloat((String)list2.get(0));
        		        	reqOthers=reqOthers+Float.parseFloat((String)list2.get(1));
        		        	reqTotal=reqTotal+Float.parseFloat((String)list2.get(2));
        		        }
        		  }
        		  //if(intimationid.equalsIgnoreCase("2")){
        		  if(intimationid.equalsIgnoreCase("EMAIL")){
        			  common2 =  new IGRSCommon();
        		         list2= common2.getOthersFeeDuty(paramIds[1],Constants.EMAIL_SERVICE_ID,paramIds[2]);
        		        if(list2!=null){
        		        	reqFee= reqFee+Float.parseFloat((String)list2.get(0));
        		        	reqOthers=reqOthers+Float.parseFloat((String)list2.get(1));
        		        	reqTotal=reqTotal+Float.parseFloat((String)list2.get(2));
        		        }
        		  }
        		  //if(intimationid.equalsIgnoreCase("3")){
        		  if(intimationid.equalsIgnoreCase("POST")){
        			  common2 =  new IGRSCommon();
        		         list2= common2.getOthersFeeDuty(paramIds[1],Constants.POST_SERVICE_ID,paramIds[2]);
        		        if(list2!=null){
        		        	reqFee= reqFee+Float.parseFloat((String)list2.get(0));
        		        	reqOthers=reqOthers+Float.parseFloat((String)list2.get(1));
        		        	reqTotal=reqTotal+Float.parseFloat((String)list2.get(2));
        		        }
        		  }
        		  
        	  }
        	  
        	  
          
          }
         
          dto.setReqfee(Float.toString(reqFee));
          dto.setReqothers(Float.toString(reqOthers));
          dto.setReqtotal(Float.toString(reqTotal));
          feelist.add(dto);
          
        dbUtil.commit();
    	  } catch (Exception ex) {
          	logger.debug("Exception occur in RTI: " + ex);

              ex.printStackTrace();
          } finally {
              try {
                  dbUtil.closeConnection();
              } catch (Exception ex) {
              	logger.debug("Exception occur in RTI: " + ex);

                  ex.printStackTrace();
              }
          }

    	return feelist;
    }
	/*public static void main(String[] args) {
	    IGRSCommon common;
	    //email=SER_002
	    //sms=SER_001
	    //post=SER_003
	    try {
		common = new IGRSCommon();
		ArrayList list = common.getOthersFeeDuty("FUN_044","SER_002","3");
		logger.debug("list="+list);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	   
	}*/
}
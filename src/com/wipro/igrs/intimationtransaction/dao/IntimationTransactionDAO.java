/**
 * ===========================================================================
 * File           :   IntimationTransactionDAO.java
 * Description    :   Represents the DAO Class

 * Author         :   Nithya
 * Created Date   :   Jan 8, 2008

 * Updated By	  :   Imran Shaik
 * Updated Data	  :   Oct 21,2008
 * ===========================================================================
 */


package com.wipro.igrs.intimationtransaction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.intimationtransaction.constant.CommonConstant;
import com.wipro.igrs.intimationtransaction.dto.IntimationTxnDetailsDTO;
import com.wipro.igrs.intimationtransaction.sql.CommonSQL;
import com.wipro.igrs.rti.constants.Constants;

public class IntimationTransactionDAO {

    private static Logger log = org.apache.log4j.Logger.getLogger(IntimationTransactionDAO.class);

    DBUtility dbUtil,dbUtil1;

    //default constructor
    public IntimationTransactionDAO() throws Exception {
        dbUtil = new DBUtility();
    }

    
    //To get Country Name
    public String getCountryName(String countryId,String langauge) {
    	
    	String list = null;
        try
        {
        	DBUtility dbUtil = new DBUtility();
            log.debug("before getting lock details ");
            if("en".equalsIgnoreCase(langauge))
            {	
            dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
            }
            else
            {
            dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY_HI);
            }
            // log.debug("SQL:"+CommonSQL.STATUS_QRY);
         //   dbUtil.createStatement();
          //  list = dbUtil.executeQry(CommonSQL.GET_COUNTRY+"'"+countryId+"'");
            list = dbUtil.executeQry(new String[]{countryId});
            return list;
            //  log.debug("LIST="+list.size());
        } catch (Exception x)
        {
            x.printStackTrace();
        }
        finally {
    		try {
    			//dbUtil.closeConnection();
    		} catch (Exception ex) {
    			log.debug("Exception in closing connection :-" + ex);
    		}
    	}
       
        return list;
    }

    
    public String getStateName(String countryId,String langauge) {
        String list = null;
        try
        {
        	DBUtility dbUtil = new DBUtility();
        log.debug("before getting lock details ");
        if("en".equalsIgnoreCase(langauge))
        {	
        dbUtil.createPreparedStatement(CommonSQL.GET_STATE);
        }
        else
        {
        	dbUtil.createPreparedStatement(CommonSQL.GET_STATE_HI);	
        }
        // log.debug("SQL:"+CommonSQL.STATUS_QRY);
      //  dbUtil.createStatement();
      //  list = dbUtil.executeQry(CommonSQL.GET_STATE+"'"+countryId+"'");
        list = dbUtil.executeQry(new String[]{countryId});
        return list;
            //  log.debug("LIST="+list.size());
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
    		try {
    			//dbUtil.closeConnection();
    		} catch (Exception ex) {
    			log.debug("Exception in closing connection :-" + ex);
    		}
    	}
       
        return list;
    }

    public String getDistrictName(String countryId,String langauge) {
        String list = null;
        try
        {
        	DBUtility dbUtil = new DBUtility();
            log.debug("before getting lock details ");
            if("en".equalsIgnoreCase(langauge))
            { 
            dbUtil.createPreparedStatement(CommonSQL.GET_DISTRICT);
            }
            else
            {
            	 dbUtil.createPreparedStatement(CommonSQL.GET_DISTRICT_HI);	
            }
            // log.debug("SQL:"+CommonSQL.STATUS_QRY);
            /*dbUtil.createStatement();
            list = dbUtil.executeQry(CommonSQL.GET_DISTRICT+"'"+countryId+"'");*/
            list = dbUtil.executeQry(new String[]{countryId});
            return list;
            //  log.debug("LIST="+list.size());
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
    		try {
    			//dbUtil.closeConnection();
    		} catch (Exception ex) {
    			log.debug("Exception in closing connection :-" + ex);
    		}
    	}
       
        return list;
    }


    public String getPhotoIdName(String photoId,String langauge) {
        String list = null;
        try
        {
        	DBUtility dbUtil = new DBUtility();
            log.debug("before getting lock details ");
            if("en".equalsIgnoreCase(langauge))
            {	
            dbUtil.createPreparedStatement(CommonSQL.GET_PHOTOID_TYPE_NAME);
            }
            else
            {
            	 dbUtil.createPreparedStatement(CommonSQL.GET_PHOTOID_TYPE_NAME_HI);	
            }
            // log.debug("SQL:"+CommonSQL.STATUS_QRY);
           // dbUtil.createStatement();
           // list = dbUtil.executeQry(CommonSQL.GET_PHOTOID_TYPE_NAME+"'"+photoId+"'");
            list = dbUtil.executeQry(new String[]{photoId});
            return list;
            //  log.debug("LIST="+list.size());
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
    		try {
    			//dbUtil.closeConnection();
    		} catch (Exception ex) {
    			log.debug("Exception in closing connection :-" + ex);
    		}
    	}
       
        return list;
    }

    
    /**
    * @since       :  08-01-2008
    * Method       :  getIntimationType
    * Description  :  This method sets the Intimation Transaction Mode
    * @return list :  ArrayList
    * @throws      :  Exception
    */
    public ArrayList getIntimationType() {
        ArrayList list = null;
        IGRSCommon igrsCommon;

        try {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getIntimationType();
        } catch (Exception e) {
            log.debug("Exception in getIntimationType():" + e);
        }

        return list;
    }

    /**
     * @since       :  08-01-2008 
     * Method       :  getCountry
     * Description  :  This method is to get the country values from the table
     * @return list :  ArrayList
     */
    public ArrayList getCountry() {
        ArrayList list = null;
        IGRSCommon igrsCommon;

        try {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getCountry();
        } catch (Exception e) {
            log.debug("Exception in getCountry():" + e);
        }

        return list;
    }

    /**
     * @since       :  10-02-2008
     * Method       :  getIDProof
     * Description  :  This method sets the ID proof list
     * @return list :  ArrayList
     * @throws      :  Exception
     */
    public ArrayList getIDProof() {
        ArrayList list = null;
        IGRSCommon igrsCommon;

        try {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getIDProof();
        } catch (Exception e) {
            log.debug("Exception in getCountry():" + e);
        }

        return list;
    }

    /**
     * @since            :  08-01-2008
     * Method            :  getState
     * Description       :  This method is to get the state values from the table.
     * @param countryId  :  String
     * @return list      :  ArrayList
     */
    public ArrayList getState(String countryId) {
        ArrayList list = null;
        IGRSCommon igrsCommon;        
        try {            
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getState(countryId);
        } catch (Exception e) {
            log.debug("Exception in getState():" + e);
        }

        return list;
    }

    /**
     * @since            :  08-02-2008 
     * Method            :  getCity
     * Description       :  This method is to get the district values from the table.
     * @param stateId    :  String
     * @return list      :  ArrayList
     */
    public ArrayList getCity(String stateId) {
        ArrayList list = null;
        IGRSCommon igrsCommon;
        try {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getDistrict(stateId);
        } catch (Exception e) {
            log.debug("Exception in getCity():" + e);
        }

        return list;
    }

    /**
     * @since        :  10-02-2008
     * @Method       :  getFee
     * @Description  :  This method is to get the fee details
     * @param fnId   :  String
     * @return list  :  ArrayList
     */
    /*public ArrayList getFee(String fnId) {
        ArrayList list = null;

        try {
            dbUtil.createStatement();
            String fee = dbUtil.executeQry(CommonSQL.SERVICE_FEE_QUERY);
            String otherfee = dbUtil.executeQry(CommonSQL.SERVICE_OTHER_FEE_QUERY);
            list = new ArrayList();
            list.add(fee);
            list.add(otherfee);
        } catch (Exception x) {
            log.debug("Exception in getFee():" + x);
        }

        return list;
    }*/
    
    /**
     * @since             : 25-02-2008
     * Method             : getStringFromDate
     * Description        : To typecast the Date into String
     * @param date        : Date
     * @return dateString : String
     */
    private String getStringFromDate(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); 
        String dateString = null;
        try {
            if ( date!=null)
                dateString = dateFormat.format(date);
        } catch (Exception e) {
            log.debug("Error getting date1 field of Newattendanceoutput1 in 'dd-MMM-yy' format: " + e ); 
        }
        return dateString;
    }

    
    
    /**
     * @since                          : 25-02-2008
     * Method                          : getDateFromString
     * Description                     : To Typeecast the String into custom Date Format
     * @param dateString               : String
     * @return getStringFromDate(date) : Method
     */  
    
    private String getDateFromString(String dateString){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException pe) {
            log.debug("ParseException: " + pe);
        }
        return getStringFromDate(date);
    }


    
	/**
     * @since                       : 10-02-2008
     * Method                       : submitIntimationInfo
     * Description                  : This method inserts Inti Req Form details into Table
     * @param param                 : String[]
     * @param commonSQL             : String
     * @return submitIntimationInfo : boolean
     * @throws                      : Exception
     */
    public boolean submitIntimationInfo(String[] param,String commonSQLQuery) {
        boolean submitIntimationInfo = false;

        try {
            dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(commonSQLQuery);
            submitIntimationInfo = dbUtil.executeUpdate(param);
            dbUtil.commit();
        } catch (Exception x) {
            log.debug("Exception in submitIntimationInfo() :- " + x);
            x.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
                log.debug("Exception in submitIntimationInfo() con close:-" + ex);
                ex.printStackTrace();
            }
        }

        return submitIntimationInfo;
    }

    
    public ArrayList getofficeDetails(String officeId){
		  ArrayList alist= new ArrayList();
		  
		 // String sql="select a.office_id , a.office_name, a.district_id, b.district_name from igrs_office_master a, igrs_district_master b where a.district_id=b.district_id and a.office_id='"+officeId+"'";
		  
		  try {
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_OFFICE_DETAILS);
			alist=dbUtil.executeQuery(new String[]{officeId});
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  return alist;
		  
		  }
    
    
    
    public ArrayList getuserDetails(String userId){
		  ArrayList alist= new ArrayList();
		  
		 // String sql="select a.district_id, b.district_name from igrs_sp_detls a, igrs_district_master b where a.district_id=b.district_id and applctn_status=8 and a.created_by='"+userId+"'";
		  
		  try {
			dbUtil= new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.GET_USER_DETAILS);
			alist=dbUtil.executeQuery(new String[]{userId});
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  return alist;
		  
		  }
  
    
    
    public ArrayList getPendingApps(String userId,String commonSQLQuery)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={userId};
		
		try {
			     dbUtil = new DBUtility();
		         dbUtil.createStatement();
				
					
					try
					{	
						
						pendingAppList=dbUtil.executeQuery(commonSQLQuery);
				            log.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			log.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			log.debug(e);
		}
		return pendingAppList;
		}

    
    public ArrayList getDetailsOfApplicant(String txnId,String commonSQLQuery)
    {
    	ArrayList partyDetails = new ArrayList();
    	//ArrayList pendingAppListFinal = new ArrayList();
    	
    	
    	
    	ArrayList list1 = new ArrayList();
    	
    	String[] param={txnId};
    	
    	try {
    		dbUtil = new DBUtility();
	        dbUtil.createPreparedStatement(commonSQLQuery);
    		
    			/*	SQL = CommonSQL.GET_DETAILS_FOR_PARTY;
    				log.debug("***************"+SQL);
    				dbUtil.createPreparedStatement(SQL);    */
    			
    				
    				try
    				{	
    					
    					partyDetails=dbUtil.executeQuery(new String[]{txnId});
    			            log.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
    			           
    			            partyDetails.trimToSize();
    		
    	} catch (Exception x) {
    		log.debug(x);
    		x.printStackTrace();
    	} finally {
    		try {
    			
    			dbUtil.closeConnection();
    		} catch (Exception ex) {
    			log.debug(ex);
    			ex.printStackTrace();
    		}
    	}
    	
    }
    	catch(Exception e){
    		log.debug(e);
    	}
    	return partyDetails;
    	}
    
    
    public ArrayList isPaymentEntry(String trancId,String commonSQLQuery)

    {

            ArrayList pendingAppList = new ArrayList();

            try {

                    dbUtil = new DBUtility();
                    dbUtil.createPreparedStatement(commonSQLQuery);

                   try
                       {       

                           pendingAppList=dbUtil.executeQuery(new String[]{trancId});

                                           
            } catch (Exception x) {

                    //logger.debug(x);

                    x.printStackTrace();

            } finally {

                    try {

                            

                            dbUtil.closeConnection();

                    } catch (Exception ex) {

                    //      logger.debug(ex);

                            ex.printStackTrace();

                    }
            }
    }

            catch(Exception e){

                    //logger.debug(e);

            }
            
            return pendingAppList;
    }



    public String isCompletePayment(String trancId,String commonSQLQuery)

    {

            String balance=null;

            try {

                    dbUtil = new DBUtility();
                    dbUtil.createPreparedStatement(commonSQLQuery);

                   try
                       {       

                	   balance=dbUtil.executeQry(new String[]{trancId});

                                           
            } catch (Exception x) {

                    //logger.debug(x);

                    x.printStackTrace();

            } finally {

                    try {
                            dbUtil.closeConnection();

                    } catch (Exception ex) {

                    //      logger.debug(ex);

                            ex.printStackTrace();

                    }
            }
    }

            catch(Exception e){

                    //logger.debug(e);

            }
            
            return balance;
    }
 
    
    
    
    
    public boolean submitPaymentDetails(String[] param,String commonSQLQuery) {
    	boolean submitPaymentDetails = false;

        try {
            dbUtil = new DBUtility();
            dbUtil.createPreparedStatement(commonSQLQuery);
            submitPaymentDetails = dbUtil.executeUpdate(param);
            dbUtil.commit();
        } catch (Exception x) {
            log.debug("Exception in submitIntimationInfo() :- " + x);
            x.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
                log.debug("Exception in submitIntimationInfo() con close:-" + ex);
                ex.printStackTrace();
            }
        }

        return submitPaymentDetails;
    }

    
    
    public boolean updateIntimDetails(IntimationTxnDetailsDTO dto,String txnId) {
    	boolean submitIntimationDetails = false;
    	Connection con=null;
        try {
        	if (con == null )
        		dbUtil.setAutoCommit(false);
           
            dbUtil = new DBUtility();
            //con.setAutoCommit(false);
        //    dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_INFO_SUBMIT1);
         //  String []param = new String[3];
          // param[0]=
           // submitIntimationDetails = dbUtil.executeUpdate();
       
            //String[] intiReqType = dto.getIntimationReqType();
            //String intiReqType1[] = intiReqType[0].split(",");
            
            //String[] intiTypeId = dto.getIntimationTypes();
           // String intiTypeId1[] = intiTypeId[0].split(",");
            
          //  String[] intiTypeValue = dto.getIntimationValues();
	        
		// for(int i=0;i<intiReqType1.length;i++)
		// {
			 String[] submitParam1 = new String[4];
		     String dId=getIntimationDetailId();
            //     submitParam1[0] = dId;
              //   submitParam1[1] = txnId;
               //  submitParam1[2] = intiReqType1[i];
          //       submitParam1[2] = intiTypeId[i];
		     boolean flagsms=false;
		     boolean flagmail=false;
		     boolean flagpost=false;
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox1()))
                 {
                	 flagsms=true;
                	 dbUtil.createPreparedStatement(CommonSQL.CHECK_ENTRY);
                	 ArrayList yes=dbUtil.executeQuery(new String[]{txnId,"SMS"});
                	// submitParam1[0] = getIntimationDetailId();
                     if(yes!=null && yes.size()>0)
                     {	 
                	 submitParam1[0] = "SMS"; 
                     submitParam1[1]= dto.getSmsValue();
                 //    submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                  //   submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[2]=txnId;
                     submitParam1[3]= "SMS";
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_UPDATE);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                     }
                     else
                     {
                    	 dbUtil.createStatement();
                    	 String id=getIntimationDetailId();
                    	 dbUtil.createPreparedStatement(CommonSQL.INSERT_ENTRY);
                    	 dbUtil.executeUpdate(new String[]{id,txnId,"SMS",dto.getSmsValue(),dto.getUserId()});
                   
                     }
                 }
                 
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox2()))
                 {
                //	 submitParam1[0] = getIntimationDetailId();
                	 flagmail=true;
                	 dbUtil.createPreparedStatement(CommonSQL.CHECK_ENTRY);
                	 ArrayList yes=dbUtil.executeQuery(new String[]{txnId,"EMAIL"});
                	// submitParam1[0] = getIntimationDetailId();
                     if(yes!=null && yes.size()>0)
                     {	
                	 submitParam1[0] =  "EMAIL";
                     submitParam1[1]= dto.getEmailValue();
                  //   submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                //     submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[2]=txnId;
                     submitParam1[3]= "EMAIL";
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_UPDATE);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                     }
                     else
                     {
                    	 dbUtil.createStatement();
                    	 String id=getIntimationDetailId();
                    	 dbUtil.createPreparedStatement(CommonSQL.INSERT_ENTRY);
                    	 dbUtil.executeUpdate(new String[]{id,txnId,"EMAIL",dto.getEmailValue(),dto.getUserId()});
                     }
                 }
                 
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox3()))
                 {
              //  	 submitParam1[0] = getIntimationDetailId();
                	 flagpost=true;
                	 dbUtil.createPreparedStatement(CommonSQL.CHECK_ENTRY);
                	 ArrayList yes=dbUtil.executeQuery(new String[]{txnId,"POST"});
                	// submitParam1[0] = getIntimationDetailId();
                     if(yes!=null && yes.size()>0)
                     {	
                	 submitParam1[0] =  "POST";
                     submitParam1[1]= dto.getPostValue();
                //     submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                  //   submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[2]=txnId;
                     submitParam1[3]= "POST";
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_UPDATE);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                     }
                     else
                     {
                    	 dbUtil.createStatement();
                    	 String id=getIntimationDetailId();
                    	 dbUtil.createPreparedStatement(CommonSQL.INSERT_ENTRY);
                    	 dbUtil.executeUpdate(new String[]{id,txnId,"POST",dto.getPostValue(),dto.getUserId()});
                   
                     }
                 }
          
              if(flagsms==false)
              {
            	  dbUtil.createPreparedStatement(CommonSQL.DELETE_ENTRY);
            	  dbUtil.executeUpdate(new String[]{txnId,"SMS"});
              }
              if(flagmail==false)
              {
            	  dbUtil.createPreparedStatement(CommonSQL.DELETE_ENTRY);
            	  dbUtil.executeUpdate(new String[]{txnId,"EMAIL"});
              }
              if(flagpost==false)
              {
            	  dbUtil.createPreparedStatement(CommonSQL.DELETE_ENTRY);
            	  dbUtil.executeUpdate(new String[]{txnId,"POST"});
              }
                 dbUtil.commit();
            return submitIntimationDetails;
        } catch (Exception x) {
            log.debug("Exception in submitIntimationDetails() :- " + x);
            x.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
                log.debug("Exception in submitIntimationDetails() con close:-" + ex);
                ex.printStackTrace();
            }
        }
            return submitIntimationDetails;
        }
    
    
    
    
    
    
    public boolean submitIntimationDetails(String[] param,IntimationTxnDetailsDTO dto,String txnId) {
    	boolean submitIntimationDetails = false;
    	Connection con=null;
        try {
        	if (con == null )
        		dbUtil.setAutoCommit(false);
           
            dbUtil = new DBUtility();
            //con.setAutoCommit(false);
            dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_INFO_SUBMIT1);
            submitIntimationDetails = dbUtil.executeUpdate(param);
       
            //String[] intiReqType = dto.getIntimationReqType();
            //String intiReqType1[] = intiReqType[0].split(",");
            
            //String[] intiTypeId = dto.getIntimationTypes();
           // String intiTypeId1[] = intiTypeId[0].split(",");
            
          //  String[] intiTypeValue = dto.getIntimationValues();
	        
		// for(int i=0;i<intiReqType1.length;i++)
		// {
			 String[] submitParam1 = new String[6];
		     String dId=getIntimationDetailId();
                 submitParam1[0] = dId;
                 submitParam1[1] = txnId;
               //  submitParam1[2] = intiReqType1[i];
          //       submitParam1[2] = intiTypeId[i];
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox1()))
                 {
                	 submitParam1[0] = getIntimationDetailId();;
                     submitParam1[1] = txnId; 
                     submitParam1[2]= "SMS";
                     submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                     submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[5]=dto.getSmsValue();
                     
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_INFO_SUBMIT4);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                 }
                 
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox2()))
                 {
                	 submitParam1[0] = getIntimationDetailId();
                     submitParam1[1] = txnId; 
                     submitParam1[2]= "EMAIL";
                     submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                     submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[5]=dto.getEmailValue();
                     
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_INFO_SUBMIT4);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                 }
                 
                 
                 if("Y".equalsIgnoreCase(dto.getIntimationCheckbox3()))
                 {
                	 submitParam1[0] = getIntimationDetailId();
                     submitParam1[1] = txnId; 
                     submitParam1[2]= "POST";
                     submitParam1[3] =  ""; //update by
                 // prepStmt.setString(4, ""); //update date
                     submitParam1[4] = dto.getUserId(); //created by
                     submitParam1[5]=dto.getPostValue();
                     
                     dbUtil.createPreparedStatement(CommonSQL.INTIMATION_TXN_INFO_SUBMIT4);
                     submitIntimationDetails = dbUtil.executeUpdate(submitParam1);
                 }
                // submitParam1[5] = intiTypeValue[i];
                /* if("SMS".equalsIgnoreCase(submitParam1[2]))
                 {submitParam1[5]=dto.getSmsValue();}
                 if("EMAIL".equalsIgnoreCase(submitParam1[2]))
                 {submitParam1[5]=dto.getEmailValue();}
                 if("POST".equalsIgnoreCase(submitParam1[2]))
                 {submitParam1[5]=dto.getPostValue();}
                */ 
                // submitParam1[5] = "";
                 //  prepStmt.setString(6,"" ); //created date
                 
                 //dbUtil.commit();
		// }
            dbUtil.commit();
            return submitIntimationDetails;
        } catch (Exception x) {
            log.debug("Exception in submitIntimationDetails() :- " + x);
            x.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
                log.debug("Exception in submitIntimationDetails() con close:-" + ex);
                ex.printStackTrace();
            }
        }
            return submitIntimationDetails;
        }
    
public String getIntimationPaymentId(){
	String payId=null;
	try {
		dbUtil = new DBUtility();
	    dbUtil.createStatement();
	    payId = dbUtil.executeQry(CommonSQL.INTIMATION_TXN_INFO_SUBMIT6);
	      log.debug("detailId...."+payId);
	     return payId;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return payId;
}
    
    
    
public String getIntimationDetailId(){
	String detailId=null;
	 try {
		dbUtil = new DBUtility();
	    dbUtil.createStatement();
	      detailId = dbUtil.executeQry(CommonSQL.INTIMATION_TXN_INFO_SUBMIT5);
	      log.debug("detailId...."+detailId);
	     return detailId;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 return detailId;
}
    
    
    
public boolean submitIntimationDetails2(IntimationTxnDetailsDTO dto,String txnId,String funId,String userId){
	boolean submitIntimationDetails2 = false;
	 IGRSCommon igrsCommon = null;
	 String sqlQuery = ""; 
     int i=0;
     PreparedStatement prepStmt;
     Connection con=null;
     
     try
     {
			 String[] intiReqType = dto.getIntimationReqType();
		        String intiReqType1[] = intiReqType[0].split(",");
			 for(i=0;i<intiReqType1.length;i++)
			 {
				 
			//String detailId="SELECT IGRS_TXN_INTIMN_DETAIL_SEQ.NEXTVAL FROM DUAL";
				 dbUtil = new DBUtility();
				con=dbUtil.returnConnection();
				 dbUtil.createStatement();
		         String detailId = dbUtil.executeQry(CommonSQL.INTIMATION_TXN_INFO_SUBMIT5);
               int dId=Integer.parseInt(detailId);
			
				 sqlQuery = CommonSQL.INTIMATION_TXN_INFO_SUBMIT4;
		            prepStmt = con.prepareStatement(sqlQuery);
		            prepStmt.setInt(1, dId);
		            prepStmt.setString(2, txnId);
		            prepStmt.setString(3,intiReqType1[i]);
		            prepStmt.setString(4, ""); //update by
		           // prepStmt.setString(4, ""); //update date
		            prepStmt.setString(5, dto.getUserId()); //created by
		          //  prepStmt.setString(6,"" ); //created date
		          
		            prepStmt.executeUpdate();		
					 prepStmt.close();
			 }
			
			 con.commit();
     	
			 //String userId=dto.getUserId();
			 igrsCommon.saveLogDet("IGRS_INTIMN_TXN_DETAILS","INSERT","T",funId,userId,txnId);
			 submitIntimationDetails2=true;
            // submitIntimationInfo = dbUtil.executeUpdate(param);
        // dbUtil.commit();
     } catch (Exception x) {
         log.debug("Exception in submitIntimationDetails2() :- " + x);
         x.printStackTrace();
     } finally {
         try {
             dbUtil.closeConnection();
         } catch (Exception ex) {
             log.debug("Exception in submitIntimationDetails2() con close:-" + ex);
             ex.printStackTrace();
         }
     }
     return submitIntimationDetails2;
 }




    public boolean submitIntimationDetails1(IntimationTxnDetailsDTO dto,String funId,String userId,String txnId) {
        boolean submitIntimationDetails1 = false;
        Connection con=null;
        String sqlQuery = "";
           IGRSCommon igrsCommon = null;
	        try {
				igrsCommon = new IGRSCommon();
			} catch (Exception e) {
				e.printStackTrace();
			}
        try
        {
        	PreparedStatement prepStmt;
        	
        	if (con == null || con.isClosed()) {
				con = dbUtil.returnConnection();
			}
        	con.setAutoCommit(false);  		
      
            dbUtil = new DBUtility();
            sqlQuery = CommonSQL.INTIMATION_TXN_INFO_SUBMIT2;
            prepStmt = con.prepareStatement(sqlQuery);
            prepStmt.setString(1, dto.getTxnId());
            prepStmt.setString(2, dto.getDocRegNo()); //party type='Applicant'
            prepStmt.setString(3, "");//req type fn id
            prepStmt.setString(4,funId);
            prepStmt.setString(5,dto.getUserId());//created by
            prepStmt.setString(6,  "");//created date
            prepStmt.setString(7, "");//update by
            prepStmt.setString(8, "");//update date
            prepStmt.setString(9, "Y");//inti status
            prepStmt.setString(10, "02-Aug-2013");//Registration Date
            prepStmt.setString(11, dto.getFromDate()); //intimn start date
            prepStmt.setString(12, dto.getToDate()); //intimn end date 
            prepStmt.setString(13, ""); //paymt txn id
            
            prepStmt.executeUpdate();		
			 prepStmt.close();
			 con.commit();
			 //String userId=dto.getUserId();
			 igrsCommon.saveLogDet("IGRS_INTIMATION_TXN_DETAILS","INSERT","T",funId,userId,txnId);
			 submitIntimationDetails1=true;
               // submitIntimationInfo = dbUtil.executeUpdate(param);
           // dbUtil.commit();
        } catch (Exception x) {
            log.debug("Exception in submitIntimationDetails1() :- " + x);
            x.printStackTrace();
        } finally {
            try {
                dbUtil.closeConnection();
            } catch (Exception ex) {
                log.debug("Exception in submitIntimationDetails1() con close:-" + ex);
                ex.printStackTrace();
            }
        }
        return submitIntimationDetails1;
    }
    
    
    
    
 /*   String refId=certifiedIDgenerator();
	dto.setCertifiedId(refId);
	sqlQuery = CommonSQL.COPY_ISSUANCE_TXN;
	prepStmt = con.prepareStatement(sqlQuery);
	prepStmt.setString(1, dto.getCertifiedId());
	 if("".equalsIgnoreCase(dto.getDocumetId1()))
 		{prepStmt.setString(2, dto.getNum());}
	     if("N".equalsIgnoreCase(dto.getDocumetId1()))
	     {prepStmt.setString(2, dto.getRegNo());}
	//prepStmt.setString(2, dto.getRegNo());
	prepStmt.setString(3, funId);
	prepStmt.setString(4, dto.getPurposeReq());
	prepStmt.setString(5, CommonConstant.CERTIFIED_COPY_FLAG);
	prepStmt.setString(6, userId);
	prepStmt.setString(7, "");
	prepStmt.setString(8, dto.getTypeReq());
	 if("".equalsIgnoreCase(dto.getDocumetId1()))
		{dto.setDocumentId("Old");}
     if("N".equalsIgnoreCase(dto.getDocumetId1()))
     {dto.setDocumentId("New");}
	prepStmt.setString(9, dto.getDocumentId());
	prepStmt.executeUpdate();
	prepStmt.close();
	
	
	sqlQuery =CommonSQL.COPY_ISSUANCE_INSERT;			
	prepStmt = con.prepareStatement(sqlQuery);			
	prepStmt.setString(1, dto.getCertifiedId());
	prepStmt.setString(2, "");
	prepStmt.setString(3, dto.getFirstName());
	prepStmt.setString(4, dto.getMiddleName());
	prepStmt.setString(5, dto.getLastName());
	prepStmt.setString(6, dto.getGender());
	prepStmt.setString(7, dto.getAge());
	prepStmt.setString(8, "");
	prepStmt.setString(9, dto.getCountryId());
	prepStmt.setString(10, dto.getStateId());
	prepStmt.setString(11, dto.getDistrictName());
	prepStmt.setString(12, dto.getAddress());
	prepStmt.setString(13, dto.getPin());
	prepStmt.setString(14, dto.getPhone());
	prepStmt.setString(15, dto.getMphone());
	prepStmt.setString(16, dto.getEmail());
	prepStmt.setString(17, dto.getIdProofNo());
	prepStmt.setString(18, dto.getIdProof());
	prepStmt.setString(19, dto.getBankName());
	prepStmt.setString(20, dto.getBankAddress());
	prepStmt.setString(21, "");
	prepStmt.setString(22, dto.getFatherName());
	prepStmt.setString(23, dto.getMotherName());
	prepStmt.setString(24, "");
	prepStmt.setString(25, "");
	prepStmt.setString(26, "");
	prepStmt.setString(27, "");
	prepStmt.setString(28, "");
	prepStmt.setString(29, "");
	prepStmt.setString(30, dto.getFee());
	prepStmt.setString(31, dto.getPostalFee());
	prepStmt.setString(32, dto.getTotalFee());
	prepStmt.setString(33, dto.getIssuanceRemark());
	prepStmt.setString(34, dto.getTransPartyFirstName());
	prepStmt.setString(35, dto.getTransPartyMidName());
	prepStmt.setString(36, dto.getTransPartyLastName());
	prepStmt.setString(37, dto.getTransPartySpouseName());
	prepStmt.setString(38, dto.getTransPartyFGHName());
	prepStmt.setString(39, dto.getTransPartyMotherName());			
	
	
	
	String regDate=dto.getRegDate();
    String docDate=dto.getNumberDate();
        if("".equals(regDate))
        {
	        regDate=null;
	       
        }else{
        	regDate =getOracleDate(regDate);
        }
        if("".equals(docDate))
        {
	        docDate=null;			       
        }
        else{
        	docDate =getOracleDate(docDate);
        }
	
     prepStmt.setString(40, regDate);
     prepStmt.setString(41, docDate);
	
     prepStmt.setString(42, dto.getVolume());
	 prepStmt.setString(43, dto.getBookNo());
	 if("".equalsIgnoreCase(dto.getDocumetId1()))
 		{prepStmt.setString(44, dto.getNum());}
	     if("N".equalsIgnoreCase(dto.getDocumetId1()))
	     {prepStmt.setString(44, dto.getRegNo());}
	 //prepStmt.setString(44, dto.getNum());
	 String numDate=dto.getNumberDate();
	 if("".equals(numDate))
        {
		 numDate=null;			       
        }else{
        	numDate =getOracleDate(numDate);
        }
	 prepStmt.setString(45, numDate);
	 prepStmt.setString(46, dto.getAppSopuse());
		
	
	
	 prepStmt.executeUpdate();		
	 prepStmt.close();
	 con.commit();
	 igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",funId,userId,roleId);
	 copyIssuance=true;      */

    

    /**
        * @since               :  18-02-2008 
        * Method               :  validatePayment
        * Description          :  This method is used to validate the payment details from DB        
        * @param challanNo     :  String
        * @param challanDt     :  String  
        * @param challanAmt    :  String
        * @param bnkName       :  String  
        * @return list         : ArrayList
        */
   /* public ArrayList validatePayment(String challanNo, String challanDt, 
                                     String challanAmt, String bnkName) {
        String sql = CommonSQL.INTIMATION_CHALLAN_PAYMENT_VALIDATE + "WHERE";

        if (challanNo != null && challanDt != null && challanAmt != null && bnkName != null ) {
            if (challanNo != null && !"".equals(challanNo.trim())) {
                sql += " CHALLAN_ONLINE_NUMBER='" + challanNo + "'";
            }
            if (challanDt != null && !"".equals(challanDt.trim())) {
                sql += " AND CHALLAN_ONLINE_DATE=" + "(TO_DATE('" + challanDt + 
                       "','dd/mm/yyyy'))";
            }
            if (challanAmt != null && !"".equals(challanAmt.trim())) {
                sql += " AND NET_AMOUNT='" + challanAmt + "'";
            }
            if (bnkName != null && !"".equals(bnkName.trim())) {
                sql += " AND BANK_NAME='" + bnkName + "'";
            }
        }

        ArrayList list = null;

        try {
            log.debug("before execute Query "+sql);
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
        } catch (Exception x) {
            log.debug("Exception in validatePayment() :- " + x);
        }

        return list;
    }*/
    
    
    public ArrayList getIntimationSearch(String txnId) throws Exception {
    
    	ArrayList list = null;


		String SQL = CommonSQL.INTIMATION_SEARCH_QUERY;//+"'"+txnId+"'";
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(new String[]{txnId});
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;

    
    
    }

    /**
       * @since             :  20-01-2008
       * Method             :  getIntimationSearch
       * Description        :  This method is to search and view the copy issuance list.
       * @param refId       :  String
       * @param appStatus   :  String
       * @param fromDate    :  String
       * @param toDate      :  String
       * @param docRegNo    :  String
       * @return list       :  ArrayList
       */
 /*   public ArrayList getIntimationSearch(String refId, String status, 
                                         String fromDate, String toDate, String docRegNo) {
        String sql = "";//CommonSQL.INTIMATION_VIEW_QUERY;
      //  log.debug("Sql above-->  "+sql);
        //String statusNew = null;

     /*   if (refId != null || status != null || fromDate != null || 
            toDate != null || docRegNo != null ) {
            if((refId!=null && !"".equals(refId.trim())) && (status=="") && 
               (fromDate=="" && toDate=="") && (docRegNo=="")){
                sql += " TRANSACTION_ID='" + refId + "'";
            }            
            if ((status != null && !"".equals(status.trim())) && (refId=="") &&
               (fromDate=="" && toDate=="") && (docRegNo=="")) {
                sql += " INTIMATION_STATUS='" + status + "'";
            }
            if ((fromDate != null && toDate != null) && 
                (!"".equals(fromDate.trim()) && !"".equals(toDate.trim())) 
                &&(refId=="") && (docRegNo=="") && (status=="")) {
                sql += " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE";
                sql += "('" + fromDate + "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE";
                sql += "('" + toDate + "','mm/dd/yyyy'))";
            }
            if (docRegNo != null && !"".equals(docRegNo.trim()) && (refId=="")
               &&(fromDate=="" && toDate=="") && (status=="")) {
                sql += " REGISTRATION_ID='" + docRegNo + "'";
            }
            if ((refId != null && !"".equals(refId.trim())) && 
                (docRegNo != null && !"".equals(docRegNo.trim())) && (fromDate != null && 
                toDate != null) && (!"".equals(fromDate.trim())  && 
                !"".equals(toDate.trim()) ) && (status != null && 
                !"".equals(status.trim()))){
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND REGISTRATION_ID='" + docRegNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yyyy'))";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }
            if((refId != null && !"".equals(refId.trim())) && 
               (docRegNo != null && !"".equals(docRegNo.trim())) && 
               (fromDate == "" && toDate == "") && (status == "")){
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND REGISTRATION_ID='" + docRegNo + "'";
            }
            if ((refId != null && !"".equals(refId.trim())) && 
                (docRegNo != null && !"".equals(docRegNo.trim())) && 
                (fromDate == "" && toDate == "") && (status != null && 
                !"".equals(status.trim()))){
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND REGISTRATION_ID='" + docRegNo + "'";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }
            if ( (refId == "") && (docRegNo != null && !"".equals(docRegNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (status != null && !"".equals(status.trim())) )
            {
                sql += " REGISTRATION_ID='" + docRegNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yyyy'))";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }

            if ( (docRegNo == "") && (refId != null && !"".equals(refId.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (status != null && !"".equals(status.trim())) )
            {
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yyyy'))";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }

            if ( (refId != null && !"".equals(refId.trim())) && (status != null && !"".equals(status.trim())) && (docRegNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }

            if ( (docRegNo != null && !"".equals(docRegNo.trim())) && (status != null && !"".equals(status.trim())) && (refId == "") && (fromDate == "" && toDate == "") )
            {
                sql += " REGISTRATION_ID='" + docRegNo + "'";
                sql += " AND INTIMATION_STATUS='" + status + "'";
            }

            if ( (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (status != null && !"".equals(status.trim())) && (refId == "") && (docRegNo == "") )
            {
                sql += " INTIMATION_STATUS='" + status + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yyyy'))";
            }

            if ( (status == "") && (refId != null && !"".equals(refId.trim())) && (docRegNo != null && !"".equals(docRegNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " TRANSACTION_ID='" + refId + "'";
                sql += " AND REGISTRATION_ID='" + docRegNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yyyy'))";
            }
            
            if ( (status != null && !"".equals(status.trim())) && (refId == "") && (docRegNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " INTIMATION_STATUS='" + status + "'";
            }

              sql += " TRANSACTION_ID='" + refId + "'"; 
           if (status != null && !"".equals(status.trim())) {
                sql += " OR INTIMATION_STATUS='" + statusNew + "'";
            }
            if ((fromDate != null && toDate != null) && 
                (!"".equals(fromDate.trim()) && !"".equals(toDate.trim()) )) {
                sql += " OR TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','mm/dd/yy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','mm/dd/yy'))";
            }
            if (docdocRegNo != null && !"".equals(docdocRegNo.trim())) {
                sql += " OR REGISTRATION_ID='" + docdocRegNo + "'";
            }
 
        }*/
        
 /*       log.debug("refId-->"+refId+" fromDate-->"+fromDate+" toDate--> "+toDate);
        if ( refId != null && !"".equals(refId.trim())){
        	sql = CommonSQL.INTIMATION_VIEW_QUERY;
        	sql += " TRANSACTION_ID='" + refId + "'";
        }
        if (fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) {
        	if(sql.equals("")){
        		sql = CommonSQL.INTIMATION_VIEW_QUERY;
        		sql += " TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE ('" + fromDate + "','dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yyyy'))";
        	}
        	else{
        		sql +=" AND "+" TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE ('" + fromDate + "','dd/mm/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + "','dd/mm/yyyy'))";
        	}
        }
        if(docRegNo != null && !"".equals(docRegNo.trim())){
        	if(sql.equals("")){
        		sql = CommonSQL.INTIMATION_VIEW_QUERY;
        		 sql += " REGISTRATION_ID='" + docRegNo + "'";
        	}
        	else{
        		 sql += " AND "+" REGISTRATION_ID='" + docRegNo + "'";
        	}
        }
        if(status != null && !"".equals(status.trim())){
        	if(sql.equals("")){
        		sql = CommonSQL.INTIMATION_VIEW_QUERY;
        		 sql += " INTIMATION_STATUS='" + status + "'";
        	}
        	else{
        		 sql += " AND INTIMATION_STATUS='" + status + "'";
        	}
        }
        ArrayList list = null;
        try {
            log.debug("before execute query"+sql);
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
        } catch (Exception x) {
            log.debug("Exception in getIntimationSearch() :- " + x);
        }
        return list;
    }   */
 



    /**
      * @since                : 15-01-2008
      * Method                : getIntimationDetails
      * Description           : This method is to view the certified copy of issuance details
      * @param refId          : String[]
      * @param commonSQLQuery : String
      * @return list          : ArrayList 
      */
    public ArrayList getIntimationDetails(String[] refId,String commonSQLQuery) {
        ArrayList list = null;
        try {
            dbUtil.createPreparedStatement(commonSQLQuery);
            log.debug("SQL:"+commonSQLQuery);
            list = dbUtil.executeQuery(refId);
        } catch (Exception x) {
            log.debug("Exception in getIntimationDetails() :- " + x);
        }
        return list;
    }
    

    
    public String TotalPayment(String trancId)throws Exception
   	{
   		String total=null;


   		String SQL = CommonSQL.INTIMATION_TOTALFEE_QUERY;//+"'"+trancId+"'";
   		try {
   			dbUtil = new DBUtility();
   			dbUtil.createPreparedStatement(SQL);
   			total = dbUtil.executeQry(new String[]{trancId});
   		} catch (Exception e) {
   			log.debug("Exception in IntimationDAO - getFee():"+ e);
   			e.printStackTrace();
   		}
   		finally {
   			 dbUtil.closeConnection();
   		 }
   		return total;
   	}
 
    
    public boolean updateIntimDetails(String tranxID) throws Exception{
        boolean updateDetail=false;
    //	String SQL = CommonSQL.INTIMATION_DELETE_QUERY+"'"+tranxID+"'";
    	String SQL1 = CommonSQL.INTIMATION_DELETE_QUERY1;//+"'"+tranxID+"'";
		
    	try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL1);
			updateDetail = dbUtil.executeUpdate(new String[]{tranxID});
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
    	
    	
 /*   	try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			updateDetail = dbUtil.executeUpdate(SQL);
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }        */
		return updateDetail;     
    	
    	
    }
    
    
    
    
    
    public boolean updateIntimationDetails(String tranxID) throws Exception{
        boolean updateDetail=false;
    	String SQL = CommonSQL.INTIMATION_DELETE_QUERY;//+"'"+tranxID+"'";
    	String SQL1 = CommonSQL.INTIMATION_DELETE_QUERY1;//+"'"+tranxID+"'";
		
    	try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL1);
			updateDetail = dbUtil.executeUpdate(new String[]{tranxID});
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
    	
    	
    	try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			updateDetail = dbUtil.executeUpdate(new String[]{tranxID});
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return updateDetail;
    	
    	
    }
    
    public boolean updatePaymentInfo(IntimationTxnDetailsDTO dto) throws Exception {

        boolean icopy = false;

        String sqlQuery = "";

        int rowCount;

        Connection con=null;

        try

        {
                PreparedStatement prepStmt;             

                if (con == null || con.isClosed()) {

                                con = dbUtil.returnConnection();

                        }

                con.setAutoCommit(false);       

                sqlQuery = CommonSQL.PYMNT_UPDATE;

                        prepStmt = con.prepareStatement(sqlQuery);

                        prepStmt.setString(1, CommonConstant.TXN_PYMNT_SUCC_FLAG);

                        prepStmt.setString(2, dto.getTxnId());                    

                        prepStmt.executeUpdate();                       

                        prepStmt.close();                       

                        con.commit();

                        icopy=true;

        } catch (Exception e)

        {

                log.info("Exception in updatePaymentInfo() :- " + e);

                icopy=false;

                e.printStackTrace();

                con.rollback();          

            

        } finally

        {

            try

            {                   

                dbUtil.closeConnection();

            } catch (Exception ex)

            {

                log.info("Exception in insertlockProperty() :-" + ex);

            }

        }

        return icopy;

    }


    
    
    
    public ArrayList getFee(String funId)throws Exception
	{
		ArrayList list = new ArrayList();


		String SQL = CommonSQL.INTIMATION_FEE_QUERY;//+"'"+funId+"'";
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(SQL);
			list = dbUtil.executeQuery(new String[]{funId});
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getFee():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}



	public ArrayList getOthersFeeDuty(String funId, String serviceId,
			String userType) {
		ArrayList othersFeeDuty = null;
		try {
			IGRSCommon common = new IGRSCommon();
			 othersFeeDuty = common.getOthersFeeDuty(funId,serviceId,userType);
		} catch (Exception e) 
		{
			log.debug("IntimationDAO -- Exception in getOthersFeeDuty() "+ e);
		}
		return othersFeeDuty;
	}
	
	/**
	 * @author Imran Shaik
	 * @return ArryList
	 * @throws Exception
	 * it returns list of intimation type
	 */
	public ArrayList getTypeOfIntimation() throws Exception {
	    ArrayList list = new ArrayList();
		String SQL = CommonSQL.FUNTIONS_LIST;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {
			log.debug("Exception in IntimationDAO - getTypeOfIntimation():"+ e);
			e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}
	
	public ArrayList getSmsFee(String funId, String userId) throws Exception {
		ArrayList list = new ArrayList();
		IGRSCommon  common2 =  new IGRSCommon();
		try{
        dbUtil = new DBUtility();
        dbUtil.createStatement();
        list= common2.getOthersFeeDuty(funId,Constants.SMS_SERVICE_ID,userId);
		} catch (Exception ex) {
         	log.debug("Exception occur in Txn: " + ex);

         } finally {
             try {
                 dbUtil.closeConnection();
             } catch (Exception ex) {
             	log.debug("Exception occur in Txn: " + ex);
             }
		return list;
	}
	}
	public ArrayList getEmailFee(String funId, String userId) throws Exception {
		ArrayList list = new ArrayList();
		IGRSCommon  common2 =  new IGRSCommon();
		try{
        dbUtil = new DBUtility();
        dbUtil.createStatement();
        list= common2.getOthersFeeDuty(funId,Constants.EMAIL_SERVICE_ID,userId);
		} catch (Exception ex) {
         	log.debug("Exception occur in Txn: " + ex);

         } finally {
             try {
                 dbUtil.closeConnection();
             } catch (Exception ex) {
             	log.debug("Exception occur in Txn: " + ex);
             }
		return list;
	}
	}
	public ArrayList getPostFee(String funId, String userId) throws Exception {
		ArrayList list = new ArrayList();
		IGRSCommon  common2 =  new IGRSCommon();
		try{
        dbUtil = new DBUtility();
        dbUtil.createStatement();
        list= common2.getOthersFeeDuty(funId,Constants.POST_SERVICE_ID,userId);
		} catch (Exception ex) {
         	log.debug("Exception occur in Txn: " + ex);

         } finally {
             try {
                 dbUtil.closeConnection();
             } catch (Exception ex) {
             	log.debug("Exception occur in Txn: " + ex);
             }
		return list;
	}
	}

	
	
	/*public boolean updateIntimationInf(FormFile photo, FormFile signature,
		FormFile thumb, String txnId) {
	    	log.debug("WE ARE IN -Intimation DAO - updateIntimationInf()");
	    	DBUtility dbUtil=null;
	    	boolean bol=false;
	    	try {	    		
	    		dbUtil=new DBUtility();
	    		
	    		String SQL = 
	    		    "UPDATE IGRS_INTIMN_TXN_PARTY_DETAILS "
	    		    +"SET PARTY_PHOTO = ?,PARTY_SIGNATURE = ?, "
	    		    +"PARTY_THUMB_IMPRESSION =? WHERE INTIMATION_TXN_ID=?";
	    		
	    		PreparedStatement pst 
	    			=  dbUtil.returnPreparedStatement(SQL);
	    		
	    		
	    		    //Blob bPhoto = new BLOB();
	    		    pst.setBinaryStream(1,photo.getInputStream(),
	    			photo.getInputStream().available());
	    		    pst.setBinaryStream(2,signature.getInputStream(),
	    			signature.getInputStream().available());			    
	    		    pst.setBinaryStream(3,thumb.getInputStream(),
	    			thumb.getInputStream().available());
	    		    pst.setString(4, txnId);
	    		    int i = pst.executeUpdate();
	    		    //pst.setBlob(i, x)
	    		    log.debug("updated record:-"+i);
	    		    if(i>0) {
	    			bol = true;
	    		    }
	    		
	    		
	    		if(bol) {
	    		    dbUtil.commit();
	    		}else {
	    		    dbUtil.rollback();
	    		}   		 
	    		
	    		return bol;
	    	}catch(Exception e)
	    	{
	    		log.error("Exception in Intimation DAO --- updateIntimationInf() "+ e);
	    		try {
			    dbUtil.rollback();
			} catch (Exception e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
			}
	    	}
	    	finally 
	    	{
	    		log.error("Connection is closed using FINALLY");
	    		try {
			    dbUtil.closeConnection();
			} catch (Exception e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} 
	    	}
	    	return bol;
		}

*/
		 public ArrayList getIntimationTypeRequest(String txnId,String commonSQLQuery)
		    {
		    	ArrayList intimationTypeRequest = new ArrayList();
		    	//ArrayList pendingAppListFinal = new ArrayList();
		    	
		    	
		    	
		    //	ArrayList list1 = new ArrayList();
		    	
		    	String[] param={txnId};
		    	
		    	try {
		    		dbUtil = new DBUtility();
			        dbUtil.createPreparedStatement(commonSQLQuery);
		    		
		    			/*	SQL = CommonSQL.GET_DETAILS_FOR_PARTY;
		    				log.debug("***************"+SQL);
		    				dbUtil.createPreparedStatement(SQL);    */
		    			
		    				
		    				try
		    				{	
		    					
		    					intimationTypeRequest=dbUtil.executeQuery(new String[]{txnId});
		    			            log.debug("Wipro in EstampDAO - getPendingEstampApps() after dbUtil.executeQuery(SQL)");
		    			           
		    			            intimationTypeRequest.trimToSize();
		    		
		    	} catch (Exception x) {
		    		log.debug(x);
		    		x.printStackTrace();
		    	} finally {
		    		try {
		    			
		    			dbUtil.closeConnection();
		    		} catch (Exception ex) {
		    			log.debug(ex);
		    			ex.printStackTrace();
		    		}
		    	}
		    	
		    }
		    	catch(Exception e){
		    		log.debug(e);
		    	}
		    	return intimationTypeRequest;
		    	}
	}
		
		
		
		
	
		/*ArrayList list = new ArrayList();
	//	IGRSCommon  common2 =  new IGRSCommon();
		try{
        dbUtil = new DBUtility();
        dbUtil.createStatement();
        list= dbUtil.executeQuery("SELECT INTIMATION_TYPE_ID,INTIMATION_TYPE_VALUE  FROM IGRS_INTIMN_TXN_DETAILS  WHERE INTIMATION_TXN_ID ='"+txnId+"'");
		} catch (Exception ex) {
         	log.debug("Exception occur in Txn: " + ex);

         } finally {
             try {
                 dbUtil.closeConnection();
             } catch (Exception ex) {
             	log.debug("Exception occur in Txn: " + ex);
             }
		return list;
	}
}
*/
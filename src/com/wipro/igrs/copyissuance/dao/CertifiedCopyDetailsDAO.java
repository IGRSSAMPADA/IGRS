/**
 * Copyright 2011 IGRS All rights reserved.
 */

package com.wipro.igrs.copyissuance.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;
import com.wipro.igrs.copyissuance.sql.CommonSQL;
import com.wipro.igrs.copyissuance.util.CommonUtil;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.newreginit.constant.RegInitConstant;

/**
 * ===========================================================================
 * Copyright: IGRS.
 * Project Name: CCTNS
 * Class Name: CertifiedCopyDetailsDAO.java
 * Description: DAO for the module Certified Copy.
 * Version Information: 2.0, Ramesh Majhee, 15/10/2012
 * Modification History:
 * Date of Development:- Oct , 2012 / Modification:- Oct , 2012
 * ===========================================================================
 */

/**
 * @author test
 *
 */
/**
 * @author test
 *
 */
/**
 * @author test
 *
 */
public class CertifiedCopyDetailsDAO
{
    private static Logger log = org.apache.log4j.Logger.getLogger(CertifiedCopyDetailsDAO.class);

  //  DBUtility dbUtil=null;

    /**
     * <p>Constructor for the class</p>
     */

    public CertifiedCopyDetailsDAO() throws Exception {
      //  dbUtil = new DBUtility();

    }

    /** 
     * populate country list from country master table
     * @since       :  08-01-2008 
     * Method       :  getCountry    
     * @return      :  ArrayList
     */
    public ArrayList getCountry() {
        ArrayList list = null;
        IGRSCommon igrsCommon;
        try
        {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getCountry();
        } catch (Exception e)
        {
            log.debug("Exception in getCountry():" + e);
        }
        finally {
			try {
			//	dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }
    
    /**
     * populate state list from state master table
     * @since            :  08-01-2008
     * Method            :  getState    
     * @param countryId  :  String
     * @return           :  ArrayList
     */
    public ArrayList getState(String countryId) {
        ArrayList list = null;
        IGRSCommon igrsCommon;
        try
        {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getState(countryId);
        } catch (Exception e)
        {
            log.debug("Exception in getState():" + e);
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
     * populate district list from district master table
     * @since            :  08-02-2008 
     * Method            :  getDistrict    
     * @param stateId  :  String
     * @return       :  ArrayList
     */
    
    public ArrayList getDistrict(String stateId) {
        ArrayList list = null;
        IGRSCommon igrsCommon;
        try
        {          
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getDistrict(stateId);
        } catch (Exception e)
        {
            log.debug("Exception in getDistrict():" + e);
        }
        finally {
			try {
			//	dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }

    /**
     * populate district list from district master table
     * @since       :  10-02-2008
     * Method       :  getType
     * Description  :  This method is used to get the ID proof value
     * @return      :  ArrayList
     */
    
    public ArrayList getType() {
        ArrayList list = null;
        IGRSCommon igrsCommon;
        try
        {
            igrsCommon = new IGRSCommon();
            list = igrsCommon.getCommon();
        } catch (Exception e)
        {
            log.debug("Exception in getCommon():" + e);
        }
        finally {
			try {
				//dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing conn ection :-" + ex);
			}
		}
        return list;
    }

    /**
     * getting fee for certified copy
     * @since       :  10-02-2008
     * Method       :  getFee   
     * @return      :  ArrayList
     */
   /* public ArrayList getFee() {
        ArrayList list = null;
        try
        {
            dbUtil.createStatement();
            String fee = dbUtil.executeQry(CommonSQL.COPY_ISSUANCE_FEE_QUERY);
            String otherfee = dbUtil.executeQry(CommonSQL.REQUISITE_OTHER_FEE_QUERY);
            list = new ArrayList();
            list.add(fee);
            list.add(otherfee);
            
        } catch (Exception x)
        {
            log.debug("Exception in getFee():" + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }*/


    /**
     * getting Reference Id for certified copy
     * @since       :  10-02-2008
     * Method       :  certifiedIDgenerator
     * @return      :  String
     */
    
    private String certifiedIDgenerator(String district_id) {
        String id = new CommonUtil().getAutoId("IGRS_CERCOPY_TXN_PARTY_DETAILS","CER_COPY_TXN_ID","CCI",district_id);
       return id;
    }



    /**  
     * Saving data to DB for certified copy request
     * @since               :  22-01-2013
     * Method               :  submitCertifiedInfo
     * @param dto
     * @param roleId
     * @param funId
     * @param userId
     * @return boolean
     * @throws Exception
     */
    public boolean submitCertifiedInfo(CertifiedCopyDetailsDTO dto,  String roleId, String funId, String userId) throws Exception {
        boolean copyIssuance = false;
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
        	DBUtility dbUtil=new DBUtility("");
        	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
        	con.setAutoCommit(false);  		
        	ArrayList district=getPaymentParameter(dto.getRegNo());
        	String district_id="";
        	ArrayList list;
        	if(district!=null&&district.size()>0)
        	{
        		list=(ArrayList) district.get(0);
        		district_id=(String) list.get(2);
        	}
        	String refId=certifiedIDgenerator(district_id);
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
			prepStmt.setString(8,"Certified Copy");
			 if("".equalsIgnoreCase(dto.getDocumetId1()))
     		{dto.setDocumentId("Old");}
		     if("N".equalsIgnoreCase(dto.getDocumetId1()))
		     {dto.setDocumentId("New");}
			prepStmt.setString(9, dto.getDocumentId());
			prepStmt.executeUpdate();
			prepStmt.close();
			
			
			
		
			/*dbUtil.createStatement();
	        String paymentId = dbUtil.executeQry("SELECT IGRS_CERT_PAYMENT_SEQ.NEXTVAL FROM DUAL");
			sqlQuery=CommonSQL.COPY_PAYMENT_DTLS;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, paymentId);
			prepStmt.setString(2, dto.getCertifiedId());
			prepStmt.setString(3, funId);
			prepStmt.setString(4, dto.getTotalFee());
			prepStmt.setString(5,"I");
			prepStmt.setString(6, userId);
			prepStmt.executeUpdate();
			prepStmt.close();*/

			
			//prepStmt.executeUpdate();
			//prepStmt.close();
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
			 prepStmt.setString(47,userId);
			
			 prepStmt.executeUpdate();		
			 prepStmt.close();
			 con.commit();
			 igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",funId,userId,roleId);
			 copyIssuance=true;
			 con.close();
			
          /* log.debug("before inserting issuance ");
            dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_INSERT);
            copyIssuance = dbUtil.executeUpdate(issuanceparam);
            if(copyIssuance)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","INSERT","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","INSERT","F",funId,userId,roleId);
            }
            if (copyIssuance)
            {
                if (copyparam != null)
                {
                    if (copyparam.length > 0)
                    {
                        dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_TXN);
                        copyIssuance = dbUtil.executeUpdate(copyparam);
                    }
                    if(copyIssuance)
                    {
                    	dbUtil.commit();
                    	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",funId,userId,roleId);
                    }
                    else{
                    	dbUtil.rollback();
                    	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
                    }
                }
            }
            log.debug("before inserting issuance ");           
            log.debug("after inserting");*/
        } catch (Exception e)
        {
            log.debug("Exception in insertCopyIssuance() :- " + e);
            copyIssuance=false;
        	e.printStackTrace();
        	con.rollback();
        	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
        } finally
        {
            try
            {
                //dbUtil.closeConnection();
            } catch (Exception ex)
            {
            	 //copyIssuance=false;
                 //ex.printStackTrace();
             	//con.rollback();
             	//igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
                log.debug("Exception in insertCopyIssuance() :-" + ex);
            }
        }
        return copyIssuance;
    }





    /**
     * This method is used to validate the payment details from the db
     * @since               :  22-01-2008
     * Method               :  getPayment   
	 * @param				:  payment
	 * @return              :  ArrayList 
	 */
	public ArrayList getPayment(String[] payment) {
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before getting payment id ");
            dbUtil=new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.CHALLAN_QUERY);
            log.debug("SQL:"+CommonSQL.CHALLAN_QUERY);
            list = dbUtil.executeQuery(payment);

            //dbUtil.createPreparedStatement(CommonSQL.PAYMENT_QUERY);
            //list = dbUtil.executeQuery(payment);

        } catch (Exception x)
        {
            log.debug("Exception in getPayment() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }



    /** 
     * This method is used to insert challan and payment details
     * @since                :  12-02-2008
     * Method                :  insertChallanPayment   
     * @param param          :  String[]
     * @return               :  boolean
     */
    public boolean insertChallanPayment(String[] param,String roleId, String funId, String userId) {
        boolean chalanPayment = false;
        IGRSCommon igrsCommon = null;
        try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtility dbUtil=null;
		try
        {
            log.debug("Before Inserting Challan Payment ");
            dbUtil=new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.ISSUANCE_CHALLAN_PAYMENT_INSERT);
            chalanPayment = dbUtil.executeUpdate(param);
            if(chalanPayment)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","F",funId,userId,roleId);
            }
            log.debug("After Inserting Challan Payment ");
        } catch (Exception x)
        {
            log.debug("Exception in insertChallanPayment() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return chalanPayment;
    }


 
	/**
	 * conversion of date string format to oracle date format
	 * @param DateFormat
	 * @return String
	 */
    
	public static String getOracleDate(String DateFormat) {
		String finalDate  =  "";
			if(DateFormat!= null || !DateFormat.equalsIgnoreCase("") )
			{
	    	StringTokenizer st = new StringTokenizer(DateFormat,"/");
	        String day = st.nextToken();
	        String month = st.nextToken();
	        String year = st.nextToken();
			String inputDate  =  day + "-" + month + "-" + year;
			SimpleDateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy");			
			
			Date newDate ;
			try {
				newDate  =  formatter.parse(inputDate);
				SimpleDateFormat format  =  new SimpleDateFormat("dd/MMM/yyyy");
				finalDate  =  format.format(newDate);
				
			}
			catch (Exception e) {
				log.debug(e);
			}
			
			
			}
			
			return  finalDate;
		}

    /**
     * This method is used to validate the payment
     * @since               :  18-02-2008 
     * Method               :  validatePayment    
     * details from the db.
     * @param challanNo     :  String
     * @param challanDt     :  String  
     * @param challanAmt    :  String
     * @param bnkName       :  String  
     * @return ArrayList
     */
    public ArrayList validatePayment(String challanNo, String challanDt, 
                                     String challanAmt, String bnkName) {
        String sql = CommonSQL.ISSUANCE_CHALLAN_PAYMENT_VALIDATE + "WHERE";

        //log.debug(challanNo+":"+challanDt+":"+challanAmt+":"+bnkName);
        if (challanNo != null && challanDt != null && challanAmt != null && bnkName != null )
        {
            if (challanNo != null && !"".equals(challanNo.trim()))
            {
                sql += " CHALLAN_ONLINE_NUMBER='" + challanNo + "'";
            }
            if (challanDt != null && !"".equals(challanDt.trim()))
            {
                sql += " AND CHALLAN_ONLINE_DATE=" + "TO_DATE('" + challanDt + 
                       "','mm/dd/yy')";
            }
            if (challanAmt != null && !"".equals(challanAmt.trim()))
            {
                sql += " AND NET_AMOUNT='" + challanAmt + "'";
            }
            if (bnkName != null && !"".equals(bnkName.trim()))
            {
                sql += " AND BANK_NAME='" + bnkName + "'";
            }

        }
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }




    /**
     * This method is to view the certified copy of issuance details
     * @since              :  15-01-2008
     * Method              :  getCopyIssuance    
     * @param certifiedid  :  String[]
     * @return list        :  ArrayList 
     */
    public ArrayList getCopyIssuance(String[] certifiedid,String langauge) {
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before getting copy issuance ");
            dbUtil=new DBUtility();
            if("en".equalsIgnoreCase(langauge))
            {	
            dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_QUERY);
            log.debug("SQL:"+CommonSQL.COPY_ISSUANCE_QUERY);

            }
            else
            {
            	dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_QUERY_HINDI);
                log.debug("SQL:"+CommonSQL.COPY_ISSUANCE_QUERY);
            }
            list = dbUtil.executeQuery(certifiedid);

            //dbUtil.createPreparedStatement(CommonSQL.PAYMENT_QUERY);
            //list = dbUtil.executeQuery(payment);

        } catch (Exception x)
        {
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }




    /**
     * This method is to view the certified copy of issuance details
     * @since              :  12-02-2008
     * Method              :  getCopyIssuanceRemarks
     * @param certifiedid  :  String[]
     * @return             :  ArrayList
     */
    public ArrayList getCopyIssuanceRemarks(String[] certifiedid,String languageLocale) {
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before getting copy issuance ");
            dbUtil=new DBUtility();
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_QUERY);
            }else{
            	 dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_QUERY_HINDI);
            }
            log.debug("SQL:"+CommonSQL.COPY_ISSUANCE_QUERY);
            list = dbUtil.executeQuery(certifiedid);
        } catch (Exception x)
        {
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }




    /**
     * This method is to search and view the copy issuance list.
     * @since             :  20-01-2008
     * Method             :  getIssuanceSearch    
     * @param certifiedId :  String
     * @param appStatus   :  String
     * @param fromDate    :  String
     * @param toDate      :  String
     * @return            :  ArrayList
     */
    public ArrayList getIssuanceSearch(String certifiedId, String appStatus, 
                                       String fromDate, String toDate, String regNo) {
        String sql = "";//CommonSQL.ISSUANCE_QUERY;

        log.debug("getIssuanceSearch---> "+certifiedId+":"+appStatus+":"+fromDate+":"+toDate+":"+ regNo);

        if (certifiedId != null || appStatus != null || fromDate != null ||  toDate != null || regNo != null )
        {
        	if ( certifiedId != null && !"".equals(certifiedId.trim())){
        		sql = CommonSQL.ISSUANCE_QUERY;
        		sql += " TRANSACTION_ID='" + certifiedId + "'";
        	}
        	if (regNo != null && !"".equals(regNo.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_QUERY;
        			sql += " REGISTRATION_ID='" + regNo + "'";
        		}
        		else{
        			sql += " AND REGISTRATION_ID='" + regNo + "'";
        		}
        	}
        	if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
            {
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_QUERY;
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
        		}
        		else{
                    sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                    "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                    "','dd/MM/yyyy'))";
        		}
            }
        	if(appStatus != null && !"".equals(appStatus.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_QUERY;
        			sql += " TRANSACTION_STATUS='" + appStatus + "'";
        		}
        		else{
        			sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
        		}
        	}
        	
/*            if ( (certifiedId != null && !"".equals(certifiedId.trim())) && (regNo == "") && (fromDate == "" && toDate == "") && (appStatus == "") )
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
            }
            if ( (regNo != null && !"".equals(regNo.trim())) && (certifiedId == "") && (fromDate == "" && toDate == "") && (appStatus == "") )
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
            }
            if ( (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (certifiedId == "") && (regNo == "") && (appStatus == "") )
            {
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (certifiedId != null && !"".equals(certifiedId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (appStatus != null && !"".equals(appStatus.trim())) )
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
                sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
            }

            if ( (certifiedId != null && !"".equals(certifiedId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate == "" && toDate == "") && (appStatus == "") )
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
            }

            if ( (certifiedId == "") && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (appStatus != null && !"".equals(appStatus.trim())) )
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
                sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
            }

            if ( (regNo == "") && (certifiedId != null && !"".equals(certifiedId.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (appStatus != null && !"".equals(appStatus.trim())) )
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
                sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
            }

            if ( (certifiedId != null && !"".equals(certifiedId.trim())) && (appStatus != null && !"".equals(appStatus.trim())) && (regNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
                sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
            }

            if ( (regNo != null && !"".equals(regNo.trim())) && (appStatus != null && !"".equals(appStatus.trim())) && (certifiedId == "") && (fromDate == "" && toDate == "") )
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRANSACTION_STATUS='" + appStatus + "'";
            }

            if ( (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (appStatus != null && !"".equals(appStatus.trim())) && (certifiedId == "") && (regNo == "") )
            {
                sql += " TRANSACTION_STATUS='" + appStatus + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }

            if ( (appStatus == "") && (certifiedId != null && !"".equals(certifiedId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " TRANSACTION_ID='" + certifiedId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }

            if ( (appStatus != null && !"".equals(appStatus.trim())) && (certifiedId == "") && (regNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_STATUS='" + appStatus + "'";
            }*/

          
            
            
        }
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }




    /**
     * This method is to get the issuance status
     * @since        :  11-02-2008 
     * Method        :  getIssuanceStatus
     * Description   :  This method is to get the issuance status
     * @return       :  ArrayList
     */
    public ArrayList getIssuanceStatus() {
        String sql = CommonSQL.ISSUANCE_STATUS_QUERY;
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }   





    /**
     * This method is to search and view the copy issuance list
     * @since              :  21-01-2008
     * Method              :  getIssuanceOnlineSearch    
     * registration date.
     * @param fromDate     :  String
     * @param toDate       :  String 
     * @return             :  ArrayList 
     */
    public ArrayList getIssuanceOnlineSearch(CertifiedCopyDetailsDTO dto) {
        String sql = "";//CommonSQL.ISSUANCE_ONLINE_QUERY;
       
        String fromDate = dto.getFromRequestDate();
        String toDate = dto.getToRequestDate();
        String regNo = dto.getRegNo();
        String referId = dto.getCertifiedId();
        String appStatus = dto.getAppStatus();
        String doctype=dto.getDocumentId();
        String volume=dto.getVolume();
        String bookNo=dto.getBookNo();
        String oldRegNo=dto.getNum();
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
        log.debug("getIssuanceOnlineSearch--> "+fromDate+":"+toDate+":"+referId+":"+regNo);
        if (fromDate != null || toDate != null || referId != null || regNo != null|| appStatus!=null||doctype!=null)
        {
           	if ( referId != null && !"".equals(referId.trim())){
           		sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
        		sql += " TRANSACTION_ID='" + referId + "' AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        	}
        	if (regNo != null && !"".equals(regNo.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
        			sql += " REGISTRATION_ID='" + regNo + "' AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        		}
        		else{
        			sql += " AND REGISTRATION_ID='" + regNo + "' AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        		}
        	}
        	if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
            {
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy')) AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        		}
        		else{
                    sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                    "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                    "','dd/MM/yyyy')) AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        		}
            }
        	
        	if (appStatus != null && !"".equals(appStatus.trim()))
        	{
        		sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
        		sql += " TRANSACTION_STATUS='" + appStatus + "' AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
        	}
        	
        	if ("Old".equalsIgnoreCase(doctype) && volume!=null)
        	  {
        			sql = CommonSQL.ISSUANCE_ONLINE_QUERY_OLD;
            		sql += " pd.cer_copy_txn_id= txn.transaction_id AND pd.VOLUME_NO='" + volume + "' AND pd.BOOK_NUM='" + bookNo + "' AND  pd.OLD_REG_NUM='" + oldRegNo + "' AND  upper(DOC_TYPE)='" + doctype.toUpperCase() + "' AND UPPER(PAYMENT_FLAG)='C' AND TYPE_REQ='Certified Copy' ";
            			     			
        	  }
                                            
        }
          
       
            log.debug("before inserting issuance "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } 
        catch (Exception x)
        {
        	x.printStackTrace();
            log.debug("Exception in getCopyIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;

    }
    /** 
     * This method uses to get the list for online certified copy request
     * @param fromDate
     * @param toDate
     * @param referId
     * @param regNo
     * @return ArrayList
     */
    public ArrayList getIssuanceOnlineSearch1(String fromDate, String toDate, String referId,
            String regNo,String officeId) {
				String sql = "";//CommonSQL.ISSUANCE_ONLINE_QUERY;
				String sql1;
				/*if(officeId.equalsIgnoreCase("ofc01"))
				{
					sql1="";
				}
				else*/
				{
				 sql1=" AND (SELECT UPPER(REGISTRATION_OFFICE_ID) FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER=CT.REGISTRATION_ID)='"+officeId.toUpperCase()+"'";
				}
				log.debug("getIssuanceOnlineSearch--> "+fromDate+":"+toDate+":"+referId+":"+regNo);
				
				if (fromDate != null || toDate != null || referId != null || regNo != null)
				{
				if ( referId != null && !"".equals(referId.trim())){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY1;
				sql += " CT.TRANSACTION_ID='" + referId + "' AND UPPER(CT.PAYMENT_FLAG)='C' "+sql1;
				}
				if (regNo != null && !"".equals(regNo.trim())){
				if(sql.equals("")){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY1;
				sql += " CT.REGISTRATION_ID='" + regNo + "' AND UPPER(CT.PAYMENT_FLAG)='C' "+sql1;
				}
				else{
				sql += " AND CT.REGISTRATION_ID='" + regNo + "' AND UPPER(CT.PAYMENT_FLAG)='C' "+sql1;
				}
				}
				if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
				{
				if(sql.equals("")){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY1;
				sql += "  TRUNC(CT.CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
				"','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
				"','dd/MM/yyyy')) AND UPPER(CT.PAYMENT_FLAG)='C' "+sql1;
				}
				else{
				sql += " AND  TRUNC(CT.CREATED_DATE) BETWEEN TO_CHAR(CT.TO_DATE('" + fromDate + 
				"','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
				"','dd/MM/yyyy')) AND UPPER(CT.PAYMENT_FLAG)='C' "+sql1;
				}
				}
								    
				}
				ArrayList list = null;
				DBUtility dbUtil=null;
				try
				{
				log.debug("before inserting issuance "+sql);
				dbUtil=new DBUtility();
				dbUtil.createStatement();
				list = dbUtil.executeQuery(sql);
				log.debug("before inserting ");
				} catch (Exception x)
				{
				log.debug("Exception in getCopyIssuance() :- " + x);
				}
				finally{
					try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return list;
				
				}

    public ArrayList getIssuanceOnlineSearchNoEncum1(String fromDate, String toDate, String referId,
            String regNo) {
				String sql = "";//CommonSQL.ISSUANCE_ONLINE_QUERY;
				
				log.debug("getIssuanceOnlineSearch--> "+fromDate+":"+toDate+":"+referId+":"+regNo);
				
				if (fromDate != null || toDate != null || referId != null || regNo != null)
				{
				if ( referId != null && !"".equals(referId.trim())){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY8;
				sql += " TRANSACTION_ID='" + referId + "' AND UPPER(PAYMENT_FLAG)='C' ";
				}
				if (regNo != null && !"".equals(regNo.trim())){
				if(sql.equals("")){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY8;
				sql += " REGISTRATION_ID='" + regNo + "' AND UPPER(PAYMENT_FLAG)='C' ";
				}
				else{
				sql += " AND REGISTRATION_ID='" + regNo + "' AND UPPER(PAYMENT_FLAG)='C' ";
				}
				}
				if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
				{
				if(sql.equals("")){
				sql = CommonSQL.ISSUANCE_ONLINE_QUERY8;
				sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
				"','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
				"','dd/MM/yyyy')) AND UPPER(PAYMENT_FLAG)='C' ";
				}
				else{
				sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
				"','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
				"','dd/MM/yyyy')) AND UPPER(PAYMENT_FLAG)='C' ";
				}
				}
								    
				}
				ArrayList list = null;
				DBUtility dbUtil=null;
				try
				{
				log.debug("before inserting issuance "+sql);
				dbUtil=new DBUtility();
				dbUtil.createStatement();
				list = dbUtil.executeQuery(sql);
				log.debug("before inserting ");
				} catch (Exception x)
				{
				log.debug("Exception in getCopyIssuance() :- " + x);
				}
				finally{
					try {
						dbUtil.closeConnection();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return list;
				
				}


    /**
     * This method is used to get the list according to the search criteria
     * Method                  :  getIssuanceRequestSearch
     * @param referId
     * @param regNo
     * @param fromDate
     * @param toDate
     * @return ArrayList
     */
    public ArrayList getIssuanceRequestSearch(String referId, String regNo, String fromDate, String toDate) 
    {
        String sql = "";//CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY;

        log.debug("getIssuanceRequestSearch---> "+referId+":"+regNo+":"+fromDate+":"+toDate);

        if (referId != null || regNo != null || fromDate != null || toDate != null)
        {
           	if ( referId != null && !"".equals(referId.trim())){
           		sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY;
        		sql += " TRANSACTION_ID='" + referId + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        	}
        	if (regNo != null && !"".equals(regNo.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY;
        			sql += " REGISTRATION_ID='" + regNo + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        		else{
        			sql += " AND REGISTRATION_ID='" + regNo + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        	}
        	if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
            {
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY;
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy')) AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        		else{
                    sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                    "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                    "','dd/MM/yyyy')) AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
            }
        	
        	
       /*     if ( (referId != null && !"".equals(referId.trim())) && (regNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
            }
            if ( (regNo != null && !"".equals(regNo.trim())) && (referId == "") && (fromDate == "" && toDate == "") )
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
            }
            if ( (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (referId == "") && (regNo == "") )
            {
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referId != null && !"".equals(referId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referId != null && !"".equals(referId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
            }
            if ( (referId == "") && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (regNo == "") && (referId != null && !"".equals(referId.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }*/
   
        }
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getIssuanceRequestSearch() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }

    public ArrayList getIssuanceRequestSearchNoEncum(String referId, String regNo, String fromDate, String toDate) 
    {
        String sql = "";//CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY;

        log.debug("getIssuanceRequestSearch---> "+referId+":"+regNo+":"+fromDate+":"+toDate);

        if (referId != null || regNo != null || fromDate != null || toDate != null)
        {
           	if ( referId != null && !"".equals(referId.trim())){
           		sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY8;
        		sql += " TRANSACTION_ID='" + referId + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        	}
        	if (regNo != null && !"".equals(regNo.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY8;
        			sql += " REGISTRATION_ID='" + regNo + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        		else{
        			sql += " AND REGISTRATION_ID='" + regNo + "' AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        	}
        	if ( fromDate != null && toDate != null && !"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) 
            {
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_REQUEST_UPDATE_QUERY8;
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy')) AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
        		else{
                    sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                    "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                    "','dd/MM/yyyy')) AND upper(TRANSACTION_STATUS)='C' AND UPPER(PAYMENT_FLAG)='C' ";
        		}
            }
        	
        	
       /*     if ( (referId != null && !"".equals(referId.trim())) && (regNo == "") && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
            }
            if ( (regNo != null && !"".equals(regNo.trim())) && (referId == "") && (fromDate == "" && toDate == "") )
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
            }
            if ( (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) && (referId == "") && (regNo == "") )
            {
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referId != null && !"".equals(referId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ) )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referId != null && !"".equals(referId.trim())) && (regNo != null && !"".equals(regNo.trim())) && (fromDate == "" && toDate == "") )
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND REGISTRATION_ID='" + regNo + "'";
            }
            if ( (referId == "") && (regNo != null && !"".equals(regNo.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " REGISTRATION_ID='" + regNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }
            if ( (regNo == "") && (referId != null && !"".equals(referId.trim())) && (fromDate != null && toDate != null) && (!"".equals(fromDate.trim())  && !"".equals(toDate.trim()) ))
            {
                sql += " TRANSACTION_ID='" + referId + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + fromDate + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + toDate + 
                       "','dd/MM/yyyy'))";
            }*/
   
        }
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
           dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getIssuanceRequestSearch() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }




    /**
     * This method is used to get the list for status according to the search criteria
     * @since                    : 15-02-2008
     * Method                    : getIssuanceStatusSearch   
     * @param durationFrom       : String
     * @param durationTo         : String 
     * @param referenceId        : String
     * @param regisNo            : String
     * @return list              : ArrayList
     */
    public ArrayList getIssuanceStatusSearch(String durationFrom, String durationTo, String referenceId,
                                             String regisNo) {
        String sql = "";//CommonSQL.ISSUANCE_ONLINE_QUERY;

        log.debug("getIssuanceStatusSearch---> "+durationFrom+":"+durationTo+":"+referenceId+":"+regisNo);
        
        if (durationFrom != null || durationTo != null || referenceId != null || regisNo != null)
        {
           	if ( referenceId != null && !"".equals(referenceId.trim())){
           		sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
        		sql += " TRANSACTION_ID='" + referenceId + "'";
        	}
        	if (regisNo != null && !"".equals(regisNo.trim())){
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
        			sql += " REGISTRATION_ID='" + regisNo + "'";
        		}
        		else{
        			sql += " AND REGISTRATION_ID='" + regisNo + "'";
        		}
        	}
        	if ( durationFrom != null && durationTo != null && !"".equals(durationFrom.trim())  && !"".equals(durationTo.trim()) ) 
            {
        		if(sql.equals("")){
        			sql = CommonSQL.ISSUANCE_ONLINE_QUERY;
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                       "','dd/MM/yyyy'))";
        		}
        		else{
                    sql += " AND  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                    "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                    "','dd/MM/yyyy'))";
        		}
            }
        	
        	
   /*     	
            if ( (referenceId != null && !"".equals(referenceId.trim())) && (regisNo == "") && (durationFrom == "" && durationTo == "") )
            {
                sql += " TRANSACTION_ID='" + referenceId + "'";
            }
            if ( (regisNo != null && !"".equals(regisNo.trim())) && (referenceId == "") && (durationFrom == "" && durationTo == "") )
            {
                sql += " REGISTRATION_ID='" + regisNo + "'";
            }
            if ( (durationFrom != null && durationTo != null) && (!"".equals(durationFrom.trim())  && !"".equals(durationTo.trim()) ) && (referenceId == "") && (regisNo == "") )
            {
                sql += "  TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referenceId != null && !"".equals(referenceId.trim())) && (regisNo != null && !"".equals(regisNo.trim())) && (durationFrom != null && durationTo != null) && (!"".equals(durationFrom.trim())  && !"".equals(durationTo.trim()) ) )
            {
                sql += " TRANSACTION_ID='" + referenceId + "'";
                sql += " AND REGISTRATION_ID='" + regisNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                       "','dd/MM/yyyy'))";
            }
            if ( (referenceId != null && !"".equals(referenceId.trim())) && (regisNo != null && !"".equals(regisNo.trim())) && (durationFrom == "" && durationTo == "") )
            {
                sql += " TRANSACTION_ID='" + referenceId + "'";
                sql += " AND REGISTRATION_ID='" + regisNo + "'";
            }
            if ( (referenceId == "") && (regisNo != null && !"".equals(regisNo.trim())) && (durationFrom != null && durationTo != null) && (!"".equals(durationFrom.trim())  && !"".equals(durationTo.trim()) ))
            {
                sql += " REGISTRATION_ID='" + regisNo + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                       "','dd/MM/yyyy'))";
            }
            if ( (regisNo == "") && (referenceId != null && !"".equals(referenceId.trim())) && (durationFrom != null && durationTo != null) && (!"".equals(durationFrom.trim())  && !"".equals(durationTo.trim()) ))
            {
                sql += " TRANSACTION_ID='" + referenceId + "'";
                sql += " AND TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE('" + durationFrom + 
                       "','dd/MM/yyyy')) AND TO_CHAR(TO_DATE('" + durationTo + 
                       "','dd/MM/yyyy'))";
            }*/
          
        }
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting issuance "+sql);
           dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getIssuanceStatusSearch() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    }





    /**
     * This method is used to update the remarks into the database table.
     * @since          :  15-01-2008
     * Method          :  updateIssuance     
     * @param regid
     * @return ArrayList
     */
    public ArrayList getcopyStatus(String[] regid) {
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before getting lock details ");
            dbUtil=new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.STATUS_QRY);
            log.debug("SQL:"+CommonSQL.STATUS_QRY);
            list = dbUtil.executeQuery(regid);
            log.debug("LIST="+list.size());
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
     * This method uses to update the status 
     * @param param          :String[]
     * @param statusparam    :String[]
     * @param roleId         :String
     * @param funId          :String
     * @param userId         :String
     * @return boolean
     */
    public boolean updateIssuance(String[] param, String[] statusparam, String roleId, String funId, String userId,CertifiedCopyDetailsDTO dto) {
    	String disDate= statusparam[2];
    	if("".equals(disDate))
        {
    		disDate=null;
	       
        }else{
        	disDate =getOracleDate(disDate);
        }
    	statusparam[2]=disDate;
        boolean updateIssuance = false;
        IGRSCommon igrsCommon = null;
        try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtility dbUtil=null;
		try
        {
            log.debug("before updating ");
            dbUtil=new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.ISSUANCE_UPDATE_QUERY);
            updateIssuance = dbUtil.executeUpdate(param);
            if(updateIssuance)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","F",funId,userId,roleId);
            }

            dbUtil.createPreparedStatement(CommonSQL.ISSUANCE_DELIVERY_QUERY);
            updateIssuance = dbUtil.executeUpdate(statusparam);
            if(updateIssuance)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","UPDATE","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","UPDATE","F",funId,userId,roleId);
            }

            log.debug("after inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in updateIssuance() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return updateIssuance;
    }




    /**
     *  This method is used to update the remarks  field into the db
     * @since               : 15-02-2008
     * Method               : updateIssuanceStatus    
     * @param param			: String[]
     * @param dparam		: String[]
     * @param roleId		: String
     * @param funId			: String
     * @param userId		: String
     * @return              : boolean
     */
    public boolean updateIssuanceStatus(String[] param, String[] dparam, String roleId, String funId, String userId) {
        boolean updateIssuance = false;
        IGRSCommon igrsCommon = null;
        try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBUtility dbUtil=null;
        try
        {
            log.debug("before updating ");
            dbUtil=new DBUtility();
            dbUtil.createPreparedStatement(CommonSQL.COPY_UPDATE_QUERY);
            updateIssuance = dbUtil.executeUpdate(param);
            if(updateIssuance)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","F",funId,userId,roleId);
            }

            dbUtil.createPreparedStatement(CommonSQL.COPY_DATE_QUERY);
            updateIssuance = dbUtil.executeUpdate(dparam);
            if(updateIssuance)
            {
            	dbUtil.commit();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","T",funId,userId,roleId);
            }
            else{
            	dbUtil.rollback();
            	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","UPDATE","F",funId,userId,roleId);
            }

            log.debug("after inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in updateIssuanceStatus() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return updateIssuance;
    }


	/**
	 * This method uses to get the fee for copy issuance
	 * @param funId		:String
	 * Method name		:getFee
	 * @param userType 
	 * @return			:ArrayList
	 * @throws Exception
	 */
	public ArrayList getFee(String funId, String userType)throws Exception
	{
		ArrayList list = new ArrayList();

		String query="";
		if(userType.equalsIgnoreCase("I"))
		{
			query=CommonSQL.COPY_ISSUANCE_FEE_QUERY_INTERNAL;
		}
		else if("3".equalsIgnoreCase(userType)||"4".equalsIgnoreCase(userType)||"5".equalsIgnoreCase(userType)||"7".equalsIgnoreCase(userType))
		{
			query=CommonSQL.COPY_ISSUANCE_FEE_QUERY_SP;
		}
		else if("2".equalsIgnoreCase(userType))
		{
			query=CommonSQL.COPY_ISSUANCE_FEE_QUERY_RU;
		}
		else
		{
			query=CommonSQL.COPY_ISSUANCE_FEE_QUERY_INTERNAL;
		}
		String SQL = query+"'"+funId+"'";
		DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(SQL);
			log.debug("CertifiedDAO in getFee(): list.size()"+ list.size());
		} catch (Exception e) {
			log.debug("Exception in CertifiedDAO - getFee():"+ e);
			//e.printStackTrace();
		}
		finally {
			 dbUtil.closeConnection();
		 }
		return list;
	}

	/**
	 * This method uses to get other fee for copy issuance
	 * Method name			:getOthersFeeDuty
	 * @param funId			:String
	 * @param serviceId		:String
	 * @param userType		:String
	 * @return				:ArrayList
	 */
	public ArrayList getOthersFeeDuty(String funId, String serviceId,
			String userType) {
		ArrayList othersFeeDuty = null;
		try {
			IGRSCommon common = new IGRSCommon();
			 othersFeeDuty = common.getOthersFeeDuty(funId,serviceId,userType);
			 log.debug("CertifiedDAO  getOthersFeeDuty()- othersFeeDuty="+ othersFeeDuty);
		} catch (Exception e) 
		{
			log.debug("CertifiedDAO -- Exception in getOthersFeeDuty() "+ e);
		}
		  finally {
				try {
					//dbUtil.closeConnection();
				} catch (Exception ex) {
					log.debug("Exception in closing connection :-" + ex);
				}
			}
		return othersFeeDuty;
	}

	
	/**
	 * This method uses to update the photo details in DB
	 * Method Nmae 		:updateCertifiedInfo
	 * @param photo		:FormFile
	 * @param signature	:FormFile
	 * @param thumb		:FormFile
	 * @param txnId		:String
	 * @return			:boolean
	 */
	/*public boolean updateCertifiedInfo(FormFile photo, FormFile signature,
		FormFile thumb, String txnId) {
	    	log.debug("WE ARE IN - CertifiedCOPyDetails DAO - updateCertifiedInfo()");
	    	DBUtility dbUtil=null;
	    	boolean bol=false;
	    	try {	    		
	    		dbUtil=new DBUtility();
	    		
	    		String SQL = 
	    		    "UPDATE IGRS_CERCOPY_TXN_PARTY_DETAILS "
	    		    +"SET PARTY_PHOTO = ?,PARTY_SIGNATURE = ?, "
	    		    +"PARTY_THUMB_IMPRESSION =? WHERE CER_COPY_TXN_ID=?";
	    		
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
	    		log.error("Exception in CertifiedCOPyDetails DAO --- updateCertifiedInfo() "+ e);
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
		}*/

	/**
	 * This method is used to display BOLB data from DB
	 * Method Name			 :displayObjectDetails
	 * @param res			 :HttpServletResponse
	 * @param contId		 :String
	 * @param strFunctionName:strFunctionName
	 * @return String
	 */
	public String displayObjectDetails(HttpServletResponse res, String contId,String strFunctionName) {

		String status = null;
		DBUtility dbUtility = null;

		try {
			dbUtility = new DBUtility();
			dbUtility.readBLOBToFileGetForCopyIssuance(res, contId,strFunctionName);

		} catch (Exception e) {

			log.debug(e);
		} finally {
			try {
				if (dbUtility != null)
					dbUtility.closeConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return status;
	}

    /**
     * This method uses to get the function name,Module names fron DB
     * Method Name		:getFunctionName
     * @param funId 	:String[] 
     * @return			:ArrayList
     */
    public ArrayList getFunctionName(String[] funId) {
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
           dbUtil=new DBUtility();
        	dbUtil.createPreparedStatement(CommonSQL.GET_FUNID_QUERY);
            log.debug("SQL:"+CommonSQL.GET_FUNID_QUERY +"Fun id:- "+funId);
            list = dbUtil.executeQuery(funId);
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;
    } 

      
    /**
     * This method uses to update the certified copy details
     * Method Name			:updateICopyInfo
     * @param dto			:CertifiedCopyDetailsDTO
     * @param pymntStatus	:String
     * @return				:boolean
     * @throws Exception
     */
    public boolean updateICopyInfo(CertifiedCopyDetailsDTO dto,String pymntStatus) throws Exception {
        boolean icopy = false;
        String sqlQuery = "";
        int rowCount;
        Connection con=null;
        try
        {
        	DBUtility dbUtil=null;
        	dbUtil=new DBUtility("");
        	PreparedStatement prepStmt;        	
        	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
        	con.setAutoCommit(false);      	
        	sqlQuery = CommonSQL.ICOPY_PYMNT_UPDATE;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
			prepStmt.setString(2, dto.getCertifiedId());
			prepStmt.setString(3, pymntStatus);	
			prepStmt.executeUpdate();			
			prepStmt.close();			
			con.commit();
			prepStmt = con.prepareStatement(CommonSQL.TXN_PAYMENT_UPDATE);
			prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
			prepStmt.setString(2, dto.getCertifiedId());
			prepStmt.executeUpdate();			
			prepStmt.close();			
			con.commit();
			con.close();
			icopy=true;
        } catch (Exception e)
        {
        	log.info("Exception in insertlockProperty() :- " + e);
        	icopy=false;
        	e.printStackTrace();
        	con.rollback();          
            
        } finally
        {
            try
            {            	
               // dbUtil.closeConnection();
            } catch (Exception ex)
            {
                log.info("Exception in insertlockProperty() :-" + ex);
            }
        }
        return icopy;
    }
   
    /**
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    
    public boolean updatePaymentInfo(CertifiedCopyDetailsDTO dto) throws Exception {
        boolean icopy = false;
        String sqlQuery = "";
        int rowCount;
        Connection con=null;
        try
        {
        	DBUtility dbUtil=null;
        	dbUtil=new DBUtility("");
        	PreparedStatement prepStmt;        	
        	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
        	con.setAutoCommit(false);      	
        	sqlQuery = CommonSQL.PYMNT_UPDATE;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
			prepStmt.setString(2, dto.getCertifiedId());			
			prepStmt.executeUpdate();			
			prepStmt.close();			
			con.commit();
			prepStmt = con.prepareStatement(CommonSQL.TXN_PAYMENT_UPDATE);
			prepStmt.setString(1, CommonConstant.ICOPY_PYMNT_SUCC_FLAG);
			prepStmt.setString(2, dto.getCertifiedId());			
			prepStmt.executeUpdate();			
			prepStmt.close();			
			con.commit();
			con.close();
			icopy=true;
        } catch (Exception e)
        {
        	log.info("Exception in insertlockProperty() :- " + e);
        	icopy=false;
        	e.printStackTrace();
        	con.rollback();          
            
        } finally
        {
            try
            {            	
              //  dbUtil.closeConnection();
            } catch (Exception ex)
            {
                log.info("Exception in insertlockProperty() :-" + ex);
            }
        }
        return icopy;
    }
    
    /**
     * 
     * @param regisId
     * @return
     */
    public ArrayList getRegistrationSearch(String regisId) {
        String sql = CommonSQL.SEARCH_QUERY_CERCOPY + " WHERE"; 
        sql += " REGISTRATION_NUMBER = '" + regisId + "' AND ROWNUM<2 ";      
        log.debug("Redid :- " + regisId);
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting lock "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;

    }
    
    
 /**
  *    
  * @param regNo
  * @param pin
  * @return
  */
    
    public ArrayList getPinSearch(String regNo,String pin) {
        String sql = CommonSQL.PIN_SEARCH_QUERY; 
        sql += " CD.REGISTRATION_ID= '" + regNo + "' AND PN.PIN_NUMBER='" + pin + "'";  
       log.debug("sql QRY = "+sql);
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting lock "+sql);
           dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;

    } 
    
    /**
     * 
     * @param regisId
     * @return
     */
    public ArrayList getOldRegistrationSearch(String regisId) {
        String sql = CommonSQL.SEARCH_QUERY_CERCOPY + " WHERE"; 
        sql += " REGISTRATION_NUMBER='" + regisId + "' AND ROWNUM<2 ";      
        ArrayList list = null;
        DBUtility dbUtil=null;
        try
        {
            log.debug("before inserting lock "+sql);
            dbUtil=new DBUtility();
            dbUtil.createStatement();
            list = dbUtil.executeQuery(sql);
            log.debug("before inserting ");
        } catch (Exception x)
        {
            log.debug("Exception in getlockProperty() :- " + x);
        }
        finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
        return list;

    } 
    public ArrayList getPendingApps(String userId)
	{
		ArrayList pendingAppList = new ArrayList();
		
		String[] param={userId};
		  DBUtility dbUtil=null;
		try {
			dbUtil = new DBUtility();
			String SQL=CommonSQL.PENDING_APPLICATION;
				//	SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
					dbUtil.createPreparedStatement(SQL);
				//dbUtil.createStatement();
				
					
					try
					{	
						
						//pendingAppList=dbUtil.executeQuery(param);
						pendingAppList=dbUtil.executeQuery(new String[]{userId});
				         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
				           
				            pendingAppList.trimToSize();
			
		} catch (Exception x) {
			//logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
			//	logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			//logger.debug(e);
		}
		return pendingAppList;
		
	}

    public ArrayList  getCertifiedCopyDetails(String certifiedId,String language)
    {
		
	ArrayList pendingAppList = new ArrayList();
	  DBUtility dbUtil=null;
		
		try {
			dbUtil = new DBUtility();
			String SQL="";
			if("en".equalsIgnoreCase(language))
			{
				SQL=CommonSQL.COPY_DETAILS;
			}
			else
			{
				SQL=CommonSQL.COPY_DETAILS_HI;
			}
				//	SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
					dbUtil.createPreparedStatement(SQL);
				//dbUtil.createPreparedStatement(SQL);
			//	dbUtil.createStatement();
				//String param[]={certifiedId};
					
					try
					{	
					
						pendingAppList=dbUtil.executeQuery(new String[]{certifiedId});
						//pendingAppList=dbUtil.executeQuery(SQL);
				         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
				           
				          // pendingAppList.trimToSize();
			
		} catch (Exception x) {
			//logger.debug(x);
			x.printStackTrace();
		} finally {
			try {
				
				dbUtil.closeConnection();
			} catch (Exception ex) {
			//	logger.debug(ex);
				ex.printStackTrace();
			}
		}
		
	}
		catch(Exception e){
			//logger.debug(e);
		}
    	
    	return pendingAppList;
    	
    }
    public String setPaymentDetails(CertifiedCopyDetailsDTO dto,String funId,String userId)
    {
    	Connection con=null;
    	String paymentId="";
    	  DBUtility dbUtil=null;
    	try {
    		
			dbUtil=new DBUtility("");
    		con = dbUtil.getDBConnection();
			PreparedStatement prepStmt;
			dbUtil.createStatement();
			paymentId = dbUtil.executeQry("SELECT IGRS_CERT_PAYMENT_SEQ.NEXTVAL FROM DUAL");
			String sqlQuery=CommonSQL.COPY_PAYMENT_DTLS;
			prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(1, paymentId);
			prepStmt.setString(2, dto.getCertifiedId());
			prepStmt.setString(3, funId);
			prepStmt.setString(4, dto.getBalanceAmount());
			prepStmt.setString(5,"I");
			prepStmt.setString(6, userId);
			prepStmt.executeUpdate();
			prepStmt.close();
			con.close();
    	
    	} catch (Exception e) {
		}
   return paymentId;
    }

public ArrayList isPaymentEntry(String certifiedId)
{
	ArrayList pendingAppList = new ArrayList();
	
	  DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		String SQL=CommonSQL.PRESENT_PAYMENT_ENTRY;
			//	SQL = CommonSQL.GET_PENDING_APPLICATIONS_DETLS_JUD;
				//dbUtil.createPreparedStatement(SQL);
			dbUtil.createPreparedStatement(SQL);
		//	dbUtil.createStatement();
			String param[]={certifiedId};
				
				try
				{	
				
					pendingAppList=dbUtil.executeQuery(param);
					//pendingAppList=dbUtil.executeQuery(SQL);
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
			           
			          // pendingAppList.trimToSize();
		
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
}
	catch(Exception e){
		//logger.debug(e);
	}
	
	return pendingAppList;
}
    
 public String isCompletePayment(String certifiedId)
	{
	String balance="";
	  DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		String SQL=CommonSQL.PENDING_BALANCE_DETAILS;
			
			dbUtil.createPreparedStatement(SQL);
			
				
				try
				{	
				
					 balance=dbUtil.executeQry(new String[]{certifiedId});
					
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
}
	catch(Exception e){
		//logger.debug(e);
	}
	return balance;
	}

public void modify(CertifiedCopyDetailsDTO dto)
{
	 boolean copyIssuance = false;
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
     	  DBUtility dbUtil=null;
     	  dbUtil=new DBUtility("");
     	if (con == null || con.isClosed()) {
				con = dbUtil.getDBConnection();
			}
     	con.setAutoCommit(false);  		
     	sqlQuery = CommonSQL.MODIFY_COPY_TXN;
     	prepStmt = con.prepareStatement(sqlQuery);
			prepStmt.setString(4, dto.getCertifiedId());
			 if("".equalsIgnoreCase(dto.getDocumetId1()))
	     		{prepStmt.setString(1, dto.getNum());}
			     if("N".equalsIgnoreCase(dto.getDocumetId1()))
			     {prepStmt.setString(1, dto.getRegNo());}
			//prepStmt.setString(2, dto.getRegNo());
			//prepStmt.setString(3, funId);
			prepStmt.setString(2, dto.getPurposeReq());
			 if("".equalsIgnoreCase(dto.getDocumetId1()))
			 {dto.setDocumentId("Old");}
		     if("N".equalsIgnoreCase(dto.getDocumetId1()))
		     {dto.setDocumentId("New");}
			prepStmt.setString(3, dto.getDocumentId());
			prepStmt.executeUpdate();
			prepStmt.close();
			
			sqlQuery =CommonSQL.COPY_MODIFY_ISSUANCE;			
			prepStmt = con.prepareStatement(sqlQuery);			
			prepStmt.setString(46, dto.getCertifiedId());
			prepStmt.setString(1, "");
			prepStmt.setString(2, dto.getFirstName());
			prepStmt.setString(3, dto.getMiddleName());
			prepStmt.setString(4, dto.getLastName());
			prepStmt.setString(5, dto.getGender());
			prepStmt.setString(6, dto.getAge());
			prepStmt.setString(7, "");
			prepStmt.setString(8, dto.getCountryId());
			prepStmt.setString(9, dto.getStateId());
			prepStmt.setString(10, dto.getDistrictName());
			prepStmt.setString(11, dto.getAddress());
			prepStmt.setString(12, dto.getPin());
			prepStmt.setString(13, dto.getPhone());
			prepStmt.setString(14, dto.getMphone());
			prepStmt.setString(15, dto.getEmail());
			prepStmt.setString(16, dto.getIdProofNo());
			prepStmt.setString(17, dto.getIdProof());
			prepStmt.setString(18, dto.getBankName());
			prepStmt.setString(19, dto.getBankAddress());
			prepStmt.setString(20, "");
			prepStmt.setString(21, dto.getFatherName());
			prepStmt.setString(22, dto.getMotherName());
			prepStmt.setString(23, "");
			prepStmt.setString(24, "");
			prepStmt.setString(25, "");
			prepStmt.setString(26, "");
			prepStmt.setString(27, "");
			prepStmt.setString(28, "");
			prepStmt.setString(29, dto.getFee());
			prepStmt.setString(30, dto.getPostalFee());
			prepStmt.setString(31, dto.getTotalFee());
			prepStmt.setString(32, dto.getIssuanceRemark());
			prepStmt.setString(33, dto.getTransPartyFirstName());
			prepStmt.setString(34, dto.getTransPartyMidName());
			prepStmt.setString(35, dto.getTransPartyLastName());
			prepStmt.setString(36, dto.getTransPartySpouseName());
			prepStmt.setString(37, dto.getTransPartyFGHName());
			prepStmt.setString(38, dto.getTransPartyMotherName());			
		
			
			
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
			
		     prepStmt.setString(39, regDate);
		     prepStmt.setString(40, docDate);
			
		     prepStmt.setString(41, dto.getVolume());
			 prepStmt.setString(42, dto.getBookNo());
			 if("".equalsIgnoreCase(dto.getDocumetId1()))
	     		{prepStmt.setString(43, dto.getNum());}
			     if("N".equalsIgnoreCase(dto.getDocumetId1()))
			     {prepStmt.setString(43, dto.getRegNo());}
			 //prepStmt.setString(44, dto.getNum());
			 String numDate=dto.getNumberDate();
			 if("".equals(numDate))
		        {
				 numDate=null;			       
		        }else{
		        	numDate =getOracleDate(numDate);
		        }
			 prepStmt.setString(44, numDate);
			 prepStmt.setString(45, dto.getAppSopuse());
				
			
			
			 prepStmt.executeUpdate();		
			 prepStmt.close();
			 con.commit();
			// igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",funId,userId,roleId);
			con.close();
			 copyIssuance=true;
			
       /* log.debug("before inserting issuance ");
         dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_INSERT);
         copyIssuance = dbUtil.executeUpdate(issuanceparam);
         if(copyIssuance)
         {
         	dbUtil.commit();
         	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","INSERT","T",funId,userId,roleId);
         }
         else{
         	dbUtil.rollback();
         	igrsCommon.saveLogDet("IGRS_CERCOPY_TXN_PARTY_DETAILS","INSERT","F",funId,userId,roleId);
         }
         if (copyIssuance)
         {
             if (copyparam != null)
             {
                 if (copyparam.length > 0)
                 {
                     dbUtil.createPreparedStatement(CommonSQL.COPY_ISSUANCE_TXN);
                     copyIssuance = dbUtil.executeUpdate(copyparam);
                 }
                 if(copyIssuance)
                 {
                 	dbUtil.commit();
                 	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","T",funId,userId,roleId);
                 }
                 else{
                 	dbUtil.rollback();
                 	igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
                 }
             }
         }
         log.debug("before inserting issuance ");           
         log.debug("after inserting");*/
     } catch (Exception e)
     {
         log.debug("Exception in modifyCopyIssuance() :- " + e);
         copyIssuance=false;
     	e.printStackTrace();
     	//con.rollback();
     	//igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
     } finally
     {
         try
         {
            // dbUtil.closeConnection();
         } catch (Exception ex)
         {
         	 //copyIssuance=false;
              //ex.printStackTrace();
          	//con.rollback();
          	//igrsCommon.saveLogDet("IGRS_CERTIFIED_COPY_TXN","INSERT","F",funId,userId,roleId);
             log.debug("Exception in modifyCopyIssuance() :-" + ex);
         }
     }
}
public ArrayList getPendingAppsinIssuance(String userId)
{
	ArrayList pendingAppList = new ArrayList();
	  DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
			String	SQL = CommonSQL.PENDING_APPS;
				dbUtil.createPreparedStatement(SQL);
			//dbUtil.createStatement();
			
				
				try
				{	
					
					//pendingAppList=dbUtil.executeQuery(param);
					//pendingAppList=dbUtil.executeQuery("select  cer_copy_txn_id,total,TO_CHAR((CREATED_DATE),'dd/mm/yyyy') CREATED_DATE from  igrs_cercopy_txn_party_details where cer_copy_txn_id not in (select cert_txn_id from igrs_cert_copy_payment_dtls) and cer_copy_txn_id like 'CCI%' and created_by='"+userId+"'");
			         //   logger.debug("-----> in EstampDAO - getPendingApps() for Judicial after dbUtil.executeQuery(SQL)");
					pendingAppList=dbUtil.executeQuery(new String[]{userId});
			            pendingAppList.trimToSize();
		
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
}
	catch(Exception e){
		//logger.debug(e);
	}
	return pendingAppList;
	
}
public String TotalPayment(String certifiedId)
	{
	String balance="";
	  DBUtility dbUtil=null;
	try {
		dbUtil = new DBUtility();
		//String SQL=CommonSQL.PENDING_BALANCE_DETAILS;
			
			//dbUtil.createStatement();
		
		dbUtil.createPreparedStatement(CommonSQL.TOTAL_PAYMENT);
				
				try
				{	
				
					// balance=dbUtil.executeQry("select total from igrs_cercopy_txn_party_details where cer_copy_txn_id='"+certifiedId+"'");
					balance=dbUtil.executeQry(new String[]{certifiedId});
	} catch (Exception x) {
		//logger.debug(x);
		x.printStackTrace();
	} finally {
		try {
			
			dbUtil.closeConnection();
		} catch (Exception ex) {
		//	logger.debug(ex);
			ex.printStackTrace();
		}
	}
	
}
	catch(Exception e){
		//logger.debug(e);
	}
	return balance;
	}

public ArrayList searchRegIdDao(String RegNo)throws IOException,SQLException,Exception
{
//	logger.debug("IN DAO searchRegIdDao");
    ArrayList list=null;
    DBUtility dbUtil = new DBUtility();
    try{
//        String query=CommonSQL.IGRS_CAVEATS_REGID_SEARCH_IN_REG_TABLE;
        String query = CommonSQL.GET_CAVEATS_REGID_PROPERTYID;
		dbUtil.createPreparedStatement(query );
        list = dbUtil.executeQuery(new String[]{RegNo});
        return (list);
    }catch(IOException ie){
  //  	logger.error(ie);
    }
    catch(SQLException se){
    //	logger.error(se);
    }
    catch(Exception e){
      //  logger.error(e);
    }
    finally {
		//logger.debug("using FINALLY Connection is closed");
        dbUtil.closeConnection(); 
    }
    return list;
}

public ArrayList transactingPartyDetails(String propertyId)throws IOException,SQLException,Exception
{
//	logger.debug("IN DAO searchRegIdDao");
    ArrayList list=null;
    DBUtility dbUtil = new DBUtility();
    try{
//        String query=CommonSQL.IGRS_CAVEATS_REGID_SEARCH_IN_REG_TABLE;
        String query = CommonSQL.PARTY_DETAILS;
		dbUtil.createPreparedStatement(query );
        list = dbUtil.executeQuery(new String[]{propertyId});
        return (list);
    }catch(IOException ie){
  //  	logger.error(ie);
    }
    catch(SQLException se){
    //	logger.error(se);
    }
    catch(Exception e){
      //  logger.error(e);
    }
    finally {
		//logger.debug("using FINALLY Connection is closed");
        dbUtil.closeConnection(); 
    }
    return list;
}

public String getCountryName(String countryId,String langauge) {
	
	String list = null;
	  DBUtility dbUtil=null;
	try
    {
    	 dbUtil = new DBUtility();
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
       // dbUtil.createStatement();
        //list = dbUtil.executeQry(CommonSQL.GET_COUNTRY+"'"+countryId+"'");
        list = dbUtil.executeQry(new String[]{countryId});
        return list;
        //  log.debug("LIST="+list.size());
    } catch (Exception x)
    {
        x.printStackTrace();
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			log.debug("Exception in closing connection :-" + ex);
		}
	}
   
    return list;
}

public String getStateName(String countryId,String langauge) {
    String list = null;
    DBUtility dbUtil=null;
    try
    {
    	 dbUtil = new DBUtility();
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
   // dbUtil.createStatement();
    list = dbUtil.executeQry(new String[]{countryId});
    // list = dbUtil.executeQry(CommonSQL.GET_STATE+"'"+countryId+"'");
    return list;
        //  log.debug("LIST="+list.size());
    } catch (Exception x)
    {
        log.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			log.debug("Exception in closing connection :-" + ex);
		}
	}
   
    return list;
}

public String getDistrictName(String countryId,String langauge) {
    String list = null;
    DBUtility dbUtil=null;
    try
    {
    	 dbUtil = new DBUtility();
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
       // dbUtil.createStatement();
      //  list = dbUtil.executeQry(CommonSQL.GET_DISTRICT+"'"+countryId+"'");
      list = dbUtil.executeQry(new String[]{countryId});
      return list;
        //  log.debug("LIST="+list.size());
    } catch (Exception x)
    {
        log.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			log.debug("Exception in closing connection :-" + ex);
		}
	}
   
    return list;
}

public ArrayList searchByAllDAO(String searchFields[],CertifiedActionForm  form,String languageLocale) {
	//logger.debug("IN DAO searchByAllDAO");
	ArrayList retVal = new ArrayList();
	ArrayList queryResult;
	StringBuilder stbr = new StringBuilder(CommonSQL.ISSUANCE_ONLINE_QUERY);
	DBUtility dbUtil=null;
//	searchFields[0] = _refid;
//	searchFields[1] = _RegNo;
//	searchFields[2] = _status;
//	searchFields[3] = _frDate;
//	searchFields[4] = _toDate;
	
	TreeMap<String, String> paramsMap = new TreeMap<String, String>();
	try {
		if ("".equals(searchFields[0]) == false) {
			stbr.append(CommonSQL.ISSUANCE_ONLINE_QUERY_CERID);
			paramsMap.put("1txnID", searchFields[0]);
		}
		if ("".equals(searchFields[1]) == false) {
			stbr.append(CommonSQL.ISSUANCE_ONLINE_QUERY_REGID);
			paramsMap.put("2regID", searchFields[1]);
		}
		if ("".equals(searchFields[2]) == false) {
			stbr.append(CommonSQL.ISSUANCE_ONLINE_QUERY_STATUS);
			paramsMap.put("3status", searchFields[2]);
		}
		if (("".equals(searchFields[3]) == false)
				&& ("".equals(searchFields[4]) == false)) {
			stbr.append(CommonSQL.ISSUANCE_ONLINE_QUERY_DATE);
			
			
			paramsMap.put("4fromDate", searchFields[3]);
			paramsMap.put("5toDate", searchFields[4]);
		} 
		//logger.info("Final build query : " + stbr.toString());
		//logger.info("Params mapping : " + paramsMap);
		try {
			dbUtil = new DBUtility();
			
			Collection<String> values = paramsMap.values();
			ArrayList<String> tmp = new ArrayList(values);
//			Collections.reverse(tmp);
			String[] params = tmp.toArray(new String[]{}); 
			//logger.info("query params : " + params);
			queryResult = dbUtil.getCopyDetails(stbr.toString(), params,form,languageLocale);
			retVal.addAll(queryResult);
			dbUtil.closeConnection();
		} catch (Exception e) {
			//logger.error(e.getMessage(), e);
		}

	} catch (Exception e) {
		//logger.error(e.getMessage(), e);
	}
	finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	return retVal;
}
public ArrayList srDashboard(String officeid,CertifiedCopyDetailsDTO dto) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null;   
	 try
	    {
	    	
	  
	    /*if(officeid.equalsIgnoreCase("ofc01"))
	   {  
	    	DBUtility dbUtil = new DBUtility();
		    log.debug("before getting lock details ");
		   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
		   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
		    dbUtil.createStatement();
	    	//list = dbUtil.executeQuery("SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,CREATED_DATE FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='P' and type_req='Certified Copy'");
	    	list = dbUtil.executeQuery(CommonSQL.SR_DASH);

	   } 
	   else*/
	   {
		    dbUtil = new DBUtility();
		    log.debug("before getting lock details ");
		    dbUtil.createPreparedStatement(CommonSQL.SR_DASH_OFFC);
		   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
		  //  dbUtil.createStatement();
		   //list = dbUtil.executeQuery("SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,CT.CREATED_DATE,cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='P'AND CT.TYPE_REQ='Certified Copy' and cp.registration_number=ct.registration_id AND Upper(cp.registration_office_id)='"+officeid.toUpperCase()+"'"); 
		   list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

	   }
	 
		  
	 
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList srDashboardUpdate(String officeid,CertifiedCopyDetailsDTO dto) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null;   
	 try
	    {
	    	/*DBUtility dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	    dbUtil.createStatement();*/
	  
	  /*  if(officeid.equalsIgnoreCase("ofc01"))
	   {  
	    	DBUtility dbUtil = new DBUtility();
		    log.debug("before getting lock details ");
		   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
		   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
		    dbUtil.createStatement();
	    	//list = dbUtil.executeQuery("SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,CREATED_DATE FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='C' and type_req='Certified Copy'");
	    	list = dbUtil.executeQuery(CommonSQL.GET_SR_DASH_UPDATE1);

	   } 
	   else*/
	   {
		    dbUtil = new DBUtility();
		    log.debug("before getting lock details ");
		   dbUtil.createPreparedStatement(CommonSQL.GET_SR_DASH_UPDATE1_OFC);
		   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
		   // dbUtil.createStatement();
		   //list = dbUtil.executeQuery("SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,CT.CREATED_DATE,cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='C'AND CT.TYPE_REQ='Certified Copy' and cp.registration_number=ct.registration_id AND upper(cp.registration_office_id)='"+officeid.toUpperCase()+"'"); 
			 list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

	   }
	 
		  
	 
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList srDashboardNoEncum(String officeid,CertifiedCopyDetailsDTO dto) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null;
	 try
	    {/*
	    	DBUtility dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	    dbUtil.createStatement();*/
	 
	   
	  
		  /*if(officeid.equalsIgnoreCase("ofc01"))
		   {  

		    	DBUtility dbUtil = new DBUtility();
		    log.debug("before getting lock details ");
		   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
		   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
		    	dbUtil.createStatement();
			  //list = dbUtil.executeQuery("SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,CREATED_DATE FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='P' and type_req='No-Encumbrance'");
				list = dbUtil.executeQuery(CommonSQL.GET_SR_DASH_NOENCUM);

		   } 
		   else*/
		   {
		    	 dbUtil = new DBUtility();
			    log.debug("before getting lock details ");
			    dbUtil.createPreparedStatement(CommonSQL.GET_SR_DASH_NOENCUM_OFC);
			   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
			   // dbUtil.createStatement();
			   //list = dbUtil.executeQuery("SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,CT.CREATED_DATE,cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='P'AND CT.TYPE_REQ='No-Encumbrance' and cp.registration_number=ct.registration_id AND Upper(cp.registration_office_id)='"+officeid.toUpperCase()+"'"); 
				list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

		   }
	 
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList srDashboardNoEncumUpdate(String officeid,CertifiedCopyDetailsDTO dto) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null; 
	 try
	    {
	    	/*DBUtility dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	    dbUtil.createStatement();*/
	 
	   
	  
		  /*if(officeid.equalsIgnoreCase("ofc01"))
		   {  
			  DBUtility dbUtil = new DBUtility();
			    log.debug("before getting lock details ");
			   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
			   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
			    dbUtil.createStatement();
			 // list = dbUtil.executeQuery("SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,CREATED_DATE FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='C' and type_req='No-Encumbrance'");
				 list = dbUtil.executeQuery(CommonSQL.GET_SR_DASH_NOENCUM_UPDATE);

		   } 
		   else*/
		   {
			    dbUtil = new DBUtility();
			    log.debug("before getting lock details ");
			   dbUtil.createPreparedStatement(CommonSQL.GET_SR_DAS_NOENCUM_UPDATE_OFC);
			   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
			    //dbUtil.createStatement();
			  // list = dbUtil.executeQuery("SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,CT.CREATED_DATE,cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='C'AND CT.TYPE_REQ='No-Encumbrance' and cp.registration_number=ct.registration_id AND upper(cp.registration_office_id)='"+officeid.toUpperCase()+"'"); 
				  list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

		   }
	 
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList pendingSrDashboard(String officeid) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null;
	 try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	    dbUtil.createPreparedStatement(CommonSQL.GET_SR_DASH);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	   // dbUtil.createStatement();
	   
	   /* if(officeid.equalsIgnoreCase("ofc01"))
		   {  
	    	list=null;
		   } 
	    else*/
	    {   
//	    list = dbUtil.executeQuery("select txn.transaction_id,txn.created_date from igrs_certified_copy_txn txn ,igrs_noecum_property_dtls pd where pd.txn_id=txn.transaction_id and txn.transaction_status='P' and pd.district_id=(select district_id from igrs_office_master where upper(office_id)='"+officeid.toUpperCase()+"')"); 
	    list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

	    
	    }
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList pendingSrDashboardUpdate(String officeid) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null; 
	 try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	    dbUtil.createPreparedStatement(CommonSQL.GET_SR_DASH_UPDATE);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	   // dbUtil.createStatement();
	   
	   /* if(officeid.equalsIgnoreCase("ofc01"))
		   {  
	    	list=null;
		   } 
	    else*/
	    {   
	   // list = dbUtil.executeQuery("select txn.transaction_id,txn.created_date from igrs_certified_copy_txn txn ,igrs_noecum_property_dtls pd where pd.txn_id=txn.transaction_id and txn.transaction_status='C' and pd.district_id=(select district_id from igrs_office_master where office_id='"+officeid+"')"); 
	 	  list = dbUtil.executeQuery(new String[]{officeid.toUpperCase()}); 

	    }
	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}
public ArrayList getPaymentParameter(String regno) 
{
	 ArrayList list = null;
	  DBUtility dbUtil=null;  
	 try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	    dbUtil.createPreparedStatement(CommonSQL.GET_PAYMENT_PARAMETER);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	   // dbUtil.createStatement();
	 //   list = dbUtil.executeQuery("select wt.REGISTRATION_OFFICE_ID,(select office_name from igrs_office_master where office_id= upper(wt.REGISTRATION_OFFICE_ID)) name,(select district_id from igrs_office_master where office_id= upper(wt.REGISTRATION_OFFICE_ID)) district_id,(select district_name from igrs_district_master where district_id =(select district_id from igrs_office_master where office_id=upper(wt.REGISTRATION_OFFICE_ID))) from igrs_reg_txn_detls wt where registration_number='"+regno+"'"); 
	    list = dbUtil.executeQuery(new String[]{regno}); 

	  
	   return list;
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return list;
}

public boolean checkOfficeNoEncum(String regno,String officeId)
{
	String officeIdreg="";
	  DBUtility dbUtil=null;
	try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	    dbUtil.createPreparedStatement(CommonSQL.GET_COLLECTION_OFFICE);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	   // dbUtil.createStatement();
	    officeIdreg = dbUtil.executeQry(new String[]{regno}); 
	  
	    if(officeIdreg.equalsIgnoreCase(officeId.toUpperCase()))
	    {
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	   
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return false;
}

public boolean checkOfficeDisrtictMapping(String officeId,String txnId)
{
	String propDistrictid="";
	String officeDistrictId="";
	  DBUtility dbUtil=null;
	try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	  String SQL=CommonSQL.PROPERTY_DISTRICT_ID;
	    dbUtil.createPreparedStatement(SQL);
	    propDistrictid = dbUtil.executeQry(new String[]{txnId}); 
	  
	      } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	try
    {
		 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   // dbUtil.createPreparedStatement(CommonSQL.GET_COUNTRY);
	   // log.debug("SQL:"+CommonSQL.STATUS_QRY);
	    String SQL=CommonSQL.OFFICE_DISTRICT_ID;
	    dbUtil.createPreparedStatement(SQL);
	    officeDistrictId = dbUtil.executeQry(new String[]{officeId}); 
  
      } catch (Exception x)
    {
        log.debug("Exception in getlockProperty() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			log.debug("Exception in closing connection :-" + ex);
		}
	}
	if(officeDistrictId.equalsIgnoreCase(propDistrictid))
	{
		return true;
	}
	else{
		return false;
	}
}
public boolean UpdateDownloadStatus(CertifiedCopyDetailsDTO dto)
{
	  DBUtility dbUtil=null;
	try
	    {
	    	 dbUtil = new DBUtility();
	    log.debug("before getting lock details ");
	   
	   String sql=CommonSQL.UPDATE_TRAN_STATUS;
	    // String sql="UPDATE IGRS_CERTIFIED_COPY_TXN SET TRANSACTION_STATUS='C',PURPOSE_DOWNLOAD='"+dto.getPurposeDownload()+"' where TRANSACTION_ID ='"+dto.getCertifiedId()+"'";
	   dbUtil.createPreparedStatement(sql);
	   log.debug("before getting lock details "+sql);
	    return dbUtil.executeUpdate(new String[]{dto.getPurposeDownload(),dto.getCertifiedId()});
	        //  log.debug("LIST="+list.size());
	    } catch (Exception x)
	    {
	        log.debug("Exception in getlockProperty() :- " + x);
	    }
	    finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				log.debug("Exception in closing connection :-" + ex);
			}
		}
	   
	    return false;
	
}

public String getUserType(String userId) {

    String list = null;
    DBUtility dbUtil=null;
    try
    {
    	 dbUtil = new DBUtility();
        dbUtil.createPreparedStatement(CommonSQL.GET_USER_TYPE);
	  list = dbUtil.executeQry(new String[]{userId});
      return list;
    } catch (Exception x)
    {
        log.debug("Exception in getUserType() :- " + x);
    }
    finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception ex) {
			log.debug("Exception in closing connection :-" + ex);
		}
	}
   
    return list;

}
}

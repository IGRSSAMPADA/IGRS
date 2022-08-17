/**
 * 
 */
package com.wipro.igrs.payments.dao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.payments.dto.PaymentDTO;
import com.wipro.igrs.payments.form.PaymentForm;
import com.wipro.igrs.payments.sql.CommonSQL;
import com.wipro.igrs.propertylock.sql.PlockSQL;
import com.wipro.igrs.reginit.sql.RegCommonSQL;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.serviceProvider.bd.ServiceProviderAccountBD;
import com.wipro.igrs.reginit.constant.RegInitConstant;



/**
 * ===========================================================================
 * File           :   PaymentDAO.java
 * Description    :   Represents the Payment DAO class
 * Author         :   Karteek P
 * Created Date   :   March 18, 2008

 * ===========================================================================
 */
public class CashInternalDAO {
	//DBUtility dbUtility;
	Logger logger = (Logger) Logger.getLogger(CashInternalDAO.class);
	
	
	public CashInternalDAO(){
		try{
		//dbUtility = new DBUtility();		
     	}
		catch(Exception objE){
		  logger.error("Exception when creating DBUtiliti obj creating " + objE);			
		}
	}	
	
	
	
	
	/**
     * ===========================================================================
     * Method         :   getRole()
     * Description    :   Returns String of roleid. 
     * Arguments      :   userid
     * return type    :   String
     * ===========================================================================
     */  
	public String getRole(String uid){
		String roleid=null;
		DBUtility dbUtil = null;
		try {
			String query = "SELECT ROLE_GROUP_ID FROM IGRS_USER_ROLE_GROUP_MAPPING WHERE USER_ID=";
			query=query+" '"+uid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			roleid = dbUtil.executeQry(query);
			logger.debug("the value in roleId is " + roleid);
		} catch (Exception x) {
			logger.error("Exception in roleId(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return roleid;
	}
	
	
	
	
	/******************************************************************* 
	 *    Method Name   :  getCashDetails()
     *    Arguments     :  String Array[]
     *    Return        :  String
     *    Throws 	:  NullPointer  or SQLException or Exception
     *******************************************************************/
	public  String getCashDetails(String[] _cashList)
	              throws NullPointerException,SQLException,Exception{	
		String returnFlag = "fail";
		DBUtility dbUtil = null;
		//DBUtility cashDbUtility = null;
		try{
			dbUtil = new DBUtility();
			String cashTxnID;
			//dbUtil = new DBUtility();
			//String cashTxnNo = CommonSQL.cashTxn + " TRANSACTION_ID LIKE   '" + _cashList[0] + "')";
					//" AND GROSS_AMOUNT =  '" + _cashList[1] + "' AND trunc(CREATED_DATE) = to_date('"+_cashList[2]+"','dd/mm/yyyy')";
			
			String cashTxnNo = "SELECT TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS WHERE STATUS_FLAG LIKE 'A' AND TRANSACTION_ID = '"+_cashList[0]+"'";
			dbUtil.createStatement();					
			cashTxnID = dbUtil.executeQry(cashTxnNo);
			if("".equals(cashTxnID)|| cashTxnID==null)
			{
				returnFlag = "fail";
			}
			else
			{
				 returnFlag = "success";
			}
			
		 }catch (NullPointerException ne) {
			 logger.error("Null Pointer Exception at getCashDetails in Dao " + ne);
			 ne.printStackTrace();
		}
		catch(Exception e){
			logger.error("Exception at getCashDetails in Dao " + e);
			e.printStackTrace();
		}
		finally{
			try{
				dbUtil.closeConnection();	
			}

			catch (NullPointerException ne) {
				logger.error("Exception at  Close Connection for getCashDetails in Dao " + ne);
				ne.printStackTrace();
			}
			catch (SQLException se) {
				logger.error("Exception at  Close Connection for getCashDetails in Dao " + se);
				se.printStackTrace();
			}
			catch (Exception e) {
				logger.error("Exception at Close Conection for getCashDetails in Dao " + e);
				e.printStackTrace();
			}
			
		}
		logger.debug("flag in dao is----------"+returnFlag);
		return returnFlag;
	}
	
	
	
	
	public boolean searchRespId(String rsptId, String entAmt, String funid) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      DBUtility dbUtil = null;
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	     
	      try {
		  
		   dbUtil = new DBUtility();
		   dbUtil.createStatement();  
		    	   
		       	     recordList = dbUtil.executeQuery("select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'");
		       	     
		      if(recordList != null)
	           {
	        	   if(recordList.size()>0)
	        	   { 
	        	   flag=false; 
	        	   }
	        	
	        	 for(int i=0;i<recordList.size();i++)
	        	 {
	        		 record = (ArrayList)recordList.get(i);
	        	 }
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return flag;
	    }
	
	
	/******************************************************************* 
	 *   Method Name   :  searchChallanRespId()
     *   Arguments     :  Strings
     *   Return        :  ArrayList
     *   Throws 	   :  Exception
     *******************************************************************/	
	/*public ArrayList searchChallanRespId(String rsptId, String entAmt, String funid, String bankid) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      String txnNo = "";
	     
	      try {
		  
		  DBUtility dbUtil = new DBUtility();
		  String challanSql = CommonSQL.cashTxn + "  SCROLL_NUMBER LIKE   '" + rsptId + "'" +
			" AND GROSS_AMOUNT =  '" + entAmt + "' AND BANK_ID='"+bankid+"'  AND FUNCTION_ID = '"+funid+"'" ;			
		  dbUtil.createStatement();  
          txnNo =  dbUtil.executeQry(challanSql);         	   
		  //recordList = dbUtil.executeQuery("select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'");
		       	     
		  if(txnNo != null && txnNo != ""){	
		    	  String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.BANK_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_BANK_MASTER OFM where PM.BANK_ID = OFM.BANK_ID AND STATUS_FLAG LIKE 'A' AND SCROLL_NUMBER='"+rsptId+"'";                     
		          dbUtil.createStatement(); 
		          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
		          subList = dbUtil.executeQuery(query);   
		            if(subList != null){
	        	 for(int i=0;i<subList.size();i++)
	        	 { 
	        		 record.add((ArrayList)subList.get(i));
	        		 //record = (ArrayList)subList.get(i);
	        	 }
		            }
	           } 
	           else{
	        	 throw new Exception("20000"); //Throwing an Exception
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }*/
	
	
	
	
	//commnetd for new challan details
	/******************************************************************* 
	 *   Method Name   :  searchChallanRespId1()
     *   Arguments     :  Strings
     *   Return        :  ArrayList
     *   Throws 	   :  Exception
     *******************************************************************/	
	/*public ArrayList searchChallanRespId1(String rsptId, String entAmt, String funid, String bankid) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      ArrayList comp = new ArrayList();
	      String txnNo = "";
	     
	      try {
		  
		  dbUtil = new DBUtility();
		  String challanSql = CommonSQL.cashTxn + "  SCROLL_NUMBER LIKE   '" + rsptId + "'" +
			" AND GROSS_AMOUNT =  '" + entAmt + "' AND BANK_ID='"+bankid+"'  AND FUNCTION_ID = '"+funid+"'" ;	
		  String challanSql = "select TRANSACTION_ID, GROSS_AMOUNT, FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_MODE_DETAILS where SCROLL_NUMBER LIKE '" + rsptId + "' AND BANK_ID ='"+bankid+"'";
		  dbUtil.createStatement();  
		  recordList = dbUtil.executeQuery(challanSql);
          if(recordList != null && recordList.size()>0){
        	  for(int j= 0; j<recordList.size(); j++){
        		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);;
						  String am = (String) compSub.get(1);
						  String fn = (String) compSub.get(2);
						  String st = (String) compSub.get(3);
						  if(!entAmt.equals(am)&& am !=null && am!=""){
							  throw new Exception("20001");
						  }
						  if(!funid.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("20002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("20003");
						  }else{
							  String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.BANK_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_BANK_MASTER OFM where PM.BANK_ID = OFM.BANK_ID AND STATUS_FLAG LIKE 'A' AND SCROLL_NUMBER='"+rsptId+"'";                     
					          dbUtil.createStatement(); 
					          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
					          subList = dbUtil.executeQuery(query);   
					            if(subList != null){
				        	 for(int i=0;i<subList.size();i++)
				        	 { 
				        		 record.add((ArrayList)subList.get(i));
				        		
				        	 }
					            }
							  
						  } 
					  } 
				  }
        	  } 
		    	  
	           } 
	           else{
	        	 throw new Exception("20000"); //Throwing an Exception
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }*/
	
	
	// added for new challan details
	/******************************************************************* 
	 *   Method Name   :  searchChallanRespId1()
     *   Arguments     :  Strings
     *   Return        :  ArrayList
     *   Throws 	   :  Exception
     *******************************************************************/	
	public ArrayList searchChallanRespId1(String rsptId, String entAmt, String funid, String refid) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      ArrayList comp = new ArrayList();
	      String txnNo = "";
	      DBUtility dbUtil = null;
	      try {
		  
		  dbUtil = new DBUtility();
		  String challanSql = "select CHALLAN_SERIAL_NUMBER,CIN,PAID_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where EPNR_NO ='" + refid + "'";
		  dbUtil.createStatement();  
		  recordList = dbUtil.executeQuery(challanSql);
          if(recordList != null && recordList.size()>0){
        	  for(int j= 0; j<recordList.size(); j++){
        		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						  /*if(am!=null && !am.equalsIgnoreCase("")){
						  am=BigDecimal.valueOf(Double.parseDouble(am)).toPlainString();
						  }else{
							  am="0";
						  }
						  
						  if(entAmt!=null && !entAmt.equalsIgnoreCase("")){
							  entAmt=BigDecimal.valueOf(Double.parseDouble(entAmt)).toPlainString();
							  }else{
								  entAmt="0";
							  }*/
						  logger.debug("amount from db:-"+am);
						  logger.debug("amount from screen:-"+entAmt);
						  if(cin!=null&& cin!=""){
						  if(!rsptId.equals(cin.toLowerCase())&& cin !=null && cin!=""){
							  throw new Exception("20000");
						  }
						  }else{throw new Exception("20005");
							  
						  }
						  if(!entAmt.equals(am)&& am !=null && am!=""){
							  throw new Exception("20001");
						  }
						  if(!funid.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("20002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("20003");
						  }else{
							  String query = "select TO_CHAR(CHALLAN_ONLINE_DATE,'dd/MM/yyyy hh:mi:ss AM'),FIRST_NAME ||' '||MIDDLE_NAME ||' ' ||LAST_NAME from IGRS_PAYMENT_ECHALLAN_DETAILS where STATUS_FLAG ='A' AND EPNR_NO='"+refid+"' AND LOWER(CIN)='"+rsptId+"'";                     
					          dbUtil.createStatement(); 
					          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
					          subList = dbUtil.executeQuery(query);   
					            if(subList != null){
				        	 for(int i=0;i<subList.size();i++)
				        	 { 
				        		 record.add((ArrayList)subList.get(i));
				        		
				        	 }
					            }
							  
						  } 
					  } 
				  }
        	  } 
		    	  
	           } 
	           else{
	        	 throw new Exception("20004"); //Throwing an Exception
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }
	
	
	
	
	/******************************************************************* 
	 *   Method Name   :  searchOnlineRespId1()
     *   Arguments     :  Strings
     *   Return        :  ArrayList
     *   Throws 	   :  Exception
     *******************************************************************/	
	public ArrayList searchOnlineRespId1(String rsptId, String entAmt, String funid) throws Exception
	
	{
		DBUtility dbUtil = null;
		logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;
	      String []arr=new String[1];
	      arr[0]=rsptId;
	      
	      String []arr2=new String[2];
	      arr2[1]=rsptId;
	      
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      ArrayList comp = new ArrayList();
	      String txnNo = "";
	     
	      try {
		  
		  dbUtil = new DBUtility();
		  /*String challanSql = "select CHALLAN_SERIAL_NUMBER,CIN,PAID_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where CHALLAN_SERIAL_NUMBER ='" + bankid + "'";
		  dbUtil.createStatement();
		  recordList = dbUtil.executeQuery(challanSql); */ 
		  dbUtil.createPreparedStatement(CommonSQL.ONLINE_SEARCH_QRY);
		  recordList = dbUtil.executeQuery(arr);
		 
          if(recordList != null && recordList.size()>0){
        	  for(int j= 0; j<recordList.size(); j++){
        		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);
						  arr2[0]=tid;
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(!entAmt.equals(am)&& am !=null && am!=""){
							  throw new Exception("40001");
						  }
						  if(!funid.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("40002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("40003");
						  }else{
							 /* String query = "select TO_CHAR(CHALLAN_ONLINE_DATE,'dd/MM/yyyy hh:mi:ss AM'),FIRST_NAME ||' '||MIDDLE_NAME ||' ' ||LAST_NAME from IGRS_PAYMENT_ECHALLAN_DETAILS where STATUS_FLAG ='A' AND CHALLAN_SERIAL_NUMBER='"+bankid+"' AND CIN='"+rsptId+"'";                     
					          dbUtil.createStatement(); 
					          subList = dbUtil.executeQuery(query);   
					          */
					          dbUtil.createPreparedStatement(CommonSQL.ONLINE_DETL_FETCH);
					          subList=dbUtil.executeQuery(arr2);
					           if(subList != null){
				        	 for(int i=0;i<subList.size();i++)
				        	 { 
				        		 record.add((ArrayList)subList.get(i));
				        		
				        	 }
					            }
							  
						  } 
					  } 
				  }
        	  } 
		    	  
	           } 
	           else{
	        	 throw new Exception("40000"); //Throwing an Exception
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }
	
	
	
	
	/*public ArrayList searchCashRespId(String rsptId, String entAmt, String funid) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      String txnNo = "";
	     
	      try {
		  
		  DBUtility dbUtil = new DBUtility();
		  String challanSql = "select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'";		
		  dbUtil.createStatement();  
          txnNo =  dbUtil.executeQry(challanSql);         	   
		  //recordList = dbUtil.executeQuery("select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'");
		       	     
		  if(txnNo != null && txnNo != ""){	
		    	  String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND TRANSACTION_ID='"+rsptId+"'";                                        
		          dbUtil.createStatement(); 
		          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
		          subList = dbUtil.executeQuery(query);   
		            if(subList != null){
	        	 for(int i=0;i<subList.size();i++)
	        	 { 
	        		 record.add((ArrayList)subList.get(i));
	        		
	        	 }
		            }
	           } 
	           else{
	        	 throw new Exception("30000"); //Throwing an Exception
	         }
	           

	            }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }*/
	
	

	/******************************************************************* 
	 *   Method Name   :  searchCashRespId1()
     *   Arguments     :  Strings
     *   Return        :  ArrayList
     *   Throws 	   :  Exception
     *******************************************************************/	
	public ArrayList searchCashRespId1(String rsptId, String entAmt, String funid, String officeIdLoggedIn, String parentAmountNew) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      ArrayList comp = new ArrayList();
	      String arry[]=new String[2];
	      arry[0]= rsptId;
	      arry[1]= officeIdLoggedIn;
	      DBUtility dbUtil = null;
	     try {
		  
		  dbUtil = new DBUtility();
		  /*String cashSql = "select GROSS_AMOUNT, FUNCTION_ID,STATUS_FLAG from IGRS_PAYMENT_MODE_DETAILS where TRANSACTION_ID='"+rsptId+"'";		
		  dbUtil.createStatement();
		  recordList = dbUtil.executeQuery(cashSql);*/
		  
		  dbUtil.createPreparedStatement(CommonSQL.cashSql);
		  recordList = dbUtil.executeQuery(arry);
          //txnNo =  dbUtil.executeQry(cashSql);         	   
		  //recordList = dbUtil.executeQuery("select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'");
		       	     
		  if(recordList != null && recordList.size()>0){
			  for(int j= 0; j<recordList.size(); j++){
				  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){ 
				  for (int k=0; k< comp.size(); k++){
					  ArrayList compSub = (ArrayList)comp.get(k);
					  String am = (String) compSub.get(0);
					  String fn = (String) compSub.get(1);
					  String st = (String) compSub.get(2);
					  //float parentAmountNew1 = Float.parseFloat(parentAmountNew) ;
					 // float amnt = Float.parseFloat(am);
					  if(!entAmt.equals(am)&& am !=null && am!=""){
						  throw new Exception("30001");
					  }
					 /* if(parentAmountNew1 < amnt){
						  throw new Exception("30004");

					  }*/
					  if(!funid.equals(fn)&& fn !=null && fn!=""){
						  throw new Exception("30002");
					  }
					  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
						  throw new Exception("30003");
					  }else{
						 /* String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND LOWER(TRANSACTION_ID)='"+rsptId+"'";                                        
				          dbUtil.createStatement(); 
				          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
				          subList = dbUtil.executeQuery(query);  */
				          
				          //dbUtil.createPreparedStatement(CommonSQL.cashCorrectDtl);
						  dbUtil.createPreparedStatement(CommonSQL.cashCorrectDtl_HINDI);
				          subList=dbUtil.executeQuery(new String[] {arry[0]});
				            if(subList != null){
			        	 for(int i=0;i<subList.size();i++)
			        	 { 
			        		 record.add((ArrayList)subList.get(i));
			        		
			        	 }
			        	 }
				            }
					  }
				  }
			  }
		    	  
	           } 
	           else{
	        	 throw new Exception("30000"); //Throwing an Exception
	         }
		  }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }
	//akansha
	public ArrayList searchCashRespId1New(String rsptId, String entAmt, String funid, String officeIdLoggedIn, String parentAmountNew) throws Exception
	{logger.debug("IN DAO...for checking consumed id");
	      boolean flag = true;  
	      ArrayList recordList = new ArrayList();
	      ArrayList record = new ArrayList();
	      ArrayList subList = new ArrayList();
	      ArrayList comp = new ArrayList();
	      String arry[]=new String[2];
	      arry[0]= rsptId;
	      arry[1]= officeIdLoggedIn;
	      DBUtility dbUtil = null;
	     try {
		  
		  dbUtil = new DBUtility();
		  /*String cashSql = "select GROSS_AMOUNT, FUNCTION_ID,STATUS_FLAG from IGRS_PAYMENT_MODE_DETAILS where TRANSACTION_ID='"+rsptId+"'";		
		  dbUtil.createStatement();
		  recordList = dbUtil.executeQuery(cashSql);*/
		  
		  dbUtil.createPreparedStatement(CommonSQL.cashSql);
		  recordList = dbUtil.executeQuery(arry);
          //txnNo =  dbUtil.executeQry(cashSql);         	   
		  //recordList = dbUtil.executeQuery("select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+rsptId+"'"+" "+"AND GROSS_AMOUNT='"+entAmt+"'"+" AND FUNCTION_ID = '"+funid+"'");
		       	     
		  if(recordList != null && recordList.size()>0){
			  for(int j= 0; j<recordList.size(); j++){
				  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){ 
				  for (int k=0; k< comp.size(); k++){
					  ArrayList compSub = (ArrayList)comp.get(k);
					  //String am = (String) compSub.get(0);
					// String fn = (String) compSub.get(1);
					 // String st = (String) compSub.get(2);
					  //float parentAmountNew1 = Float.parseFloat(parentAmountNew) ;
					 // float amnt = Float.parseFloat(am);
					 /* if(!entAmt.equals(am)&& am !=null && am!=""){
						  throw new Exception("30001");
					  }*/
					 /* if(parentAmountNew1 < amnt){
						  throw new Exception("30004");

					  }*/
					  /*if(!funid.equals(fn)&& fn !=null && fn!=""){
						  throw new Exception("30002");
					  }*/
					 /* if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
						  throw new Exception("30003");
					  }*/
					  
					 // else{
						 /* String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND LOWER(TRANSACTION_ID)='"+rsptId+"'";                                        
				          dbUtil.createStatement(); 
				          logger.debug("INSIDE DAO........THE QUERY IS....."+query);
				          subList = dbUtil.executeQuery(query);  */
				          
				          //dbUtil.createPreparedStatement(CommonSQL.cashCorrectDtl);
						  dbUtil.createPreparedStatement(CommonSQL.cashCorrectDtl_HINDI);
				          subList=dbUtil.executeQuery(new String[] {arry[0]});
				            if(subList != null){
			        	 for(int i=0;i<subList.size();i++)
			        	 { 
			        		 record.add((ArrayList)subList.get(i));
			        		
			        	 }
			        	 }
				           // }
					  }
				  }
			  }
		    	  
	           } 
	           else{
	        	 throw new Exception("30000"); //Throwing an Exception
	         }
		  }
	      catch (Exception x)
	      {
	    	  logger.error("Exception ) :- " + x);
	    	  x.printStackTrace();
	    	  throw x;
	      }
	      finally
	      {
	            try 
	            {
	              dbUtil.closeConnection();

	            } 
	            catch (Exception ex) 
	            {
	            	 logger.error("Exception  :-" + ex);
	            }
	        }
	         return record;
	    }

	
	public  ArrayList getRsptDetails(String rsptId) throws  Exception
	{                     
		          ArrayList mainList = new ArrayList();  
		          ArrayList subList = new ArrayList();
		          String grossamt = "";
		          //DBUtility dbUtil = new DBUtility();
		          DBUtility dbUtil = null;
		          try 
		          
		          {
		        	  dbUtil = new DBUtility();
		               
		            String query = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'),PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME from IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND TRANSACTION_ID='"+rsptId+"'";                     
		            dbUtil.createStatement(); 
		            logger.debug("INSIDE DAO........THE QUERY IS....."+query);
		            subList = dbUtil.executeQuery(query);                                              
		          }
		          catch (Exception e) {
		            logger.error("exception in calling at DAO Class at getRsptDetails()  " +e);
		          }
		          finally 
		          {
		             try
		             {
		            	 dbUtil.closeConnection();                                  
		              }
		             catch(Exception e){
		            	 logger.error("Exception in Finally Block  "+ e);   
		             } 
		          } 
		         if(subList!=null) 
		          {
		              for(int i=0;i<subList.size();i++)  
		                {
		            	  mainList.add((ArrayList)subList.get(i));
		                  }
		          }         
		       return mainList;
		 }  
	
	
	
	
	
	/******************************************************************* 
	 *   Method Name   :  getChallanDetails()
     *   Arguments     :  String Array[] and Amount
     *   Return        :  String
     *   Throws 	   :  NullPointer or Exception
     *******************************************************************/	
	public  String getChallanDetails(String[] _challan,double amt,String _bankId) throws NullPointerException,Exception {	
		String txnNo = null;
		String payTxnID = null;
		String returnFlag = "fail";	
		DBUtility dbUtil = null;
		try{
        txnNo = getChallanTxn(_challan,amt,_bankId);    
        
        if(txnNo == "null" || txnNo.equalsIgnoreCase(""))
        {			
			returnFlag = "fail";
	}else
	{
        	if("cash".equalsIgnoreCase(_challan[2])){
        		ArrayList getMappingList = getTxnMpping(_challan[3]);
        		if(getMappingList.size()==0){
        			returnFlag = "success";	
        		}
        		else{        			
        			for(int i=0;i<getMappingList.size();i++){        
        				ArrayList tempTxnId =(ArrayList)getMappingList.get(i);
        				if(tempTxnId.size()>0){
        				String txnId = (String)tempTxnId.get(i);
        				  if(txnNo.equalsIgnoreCase(txnId)){
        				   return "fail";
        				  }
        				  else{
        					returnFlag = "success";	
        			      }
        				} 
        			}
        		}
        	}
        	returnFlag = "success" ;
         }
		}
		catch (NullPointerException ne) {
			logger.error("Null Exception at getChallanDetails in DAO " + ne);
			ne.printStackTrace();
		}
		catch(Exception e){
			logger.error(" Exception at getChallanDetails in DAO " + e);
			e.printStackTrace();
		}
		finally
		{
			dbUtil.closeConnection();
		}
		
		
	return returnFlag;
  }
  
	 
	/**************************************************************** 
	 *    Method Name   :  getChallanTxn
     *    Arguments     :  String Array[] and Amount
     *    Return        :  String 
     *    Throws 	:  NullPointer  or SQLException or Exception
     ****************************************************************/
	public  String getChallanTxn(String[] _challan,double amt,String bankId) throws NullPointerException,Exception {			
		
		String txnNo = null;
		String payTxnID = null;
		String returnFlag = "fail";
		//DBUtility challnTxnDbUtility = null;
		DBUtility dbUtil = null;
		try{
			dbUtil = new DBUtility();
	 	//challnTxnDbUtility = new DBUtility();	 	
	 	String bankCodeSql="SELECT   BANK_CODE FROM IGRS_BANK_ID_CODE_MAPPING WHERE BANK_ID='"+bankId+"'";
	 	dbUtil.createStatement();	
	 	String bankCode=dbUtil.executeQry(bankCodeSql); 
	 	if(bankCode!=null && !"".equals(bankCode))
	 	{
	 	   	String challanSql = CommonSQL.cashTxn + "  SCROLL_NUMBER LIKE   '" + _challan[0] + "'" +
			" AND GROSS_AMOUNT =  '" + amt + "' AND BANK_ID='"+bankId+"'  AND trunc(CREATED_DATE) = to_date('"+_challan[1]+"','dd/mm/yyyy')" ;			
	 	   dbUtil.createStatement();		
             txnNo = dbUtil.executeQry(challanSql);       
	    
	     
         //system.out.println("DAO..... getChallanTxn...... Transaction Number----<>"+txnNo);
         if(txnNo == null || txnNo.equalsIgnoreCase("")){			
		   returnFlag = "null";
         }
         else{
    	    returnFlag = txnNo;
         }
	 		
	 	}
	 	
		
		}catch(NullPointerException ne){
		    
			 logger.error(" NullException at getChallanDetails  in DAO " + ne);
			 ne.printStackTrace();
		}catch (SQLException se) {
		   
			  logger.error(" Exception at getChallanDetails in DAO " +  se);
			  se.printStackTrace();
		}
		catch (Exception e) {
		   
			   logger.error(" Exception at getChallanDetails  in DAO " + e);	
			   e.printStackTrace();
		}
		finally{
			try{
				dbUtil.closeConnection();
			}			
			catch (SQLException se) {
			  logger.error(" Exception at getChallanDetails close Connection  in DAO " +  se);
			  se.printStackTrace();
			}
			catch (Exception e) {
		      logger.error(" Exception at getChallanDetails close Connection in DAO " + e);
		      e.printStackTrace();
			}			
		}
		return returnFlag;
	}
	
	
	
	/**
     * ===========================================================================
     * Method         :   getpurpName()
     * Description    :   Returns String of purpose name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String getpurpName(PaymentForm paymentForm) throws Exception{
		String oid=null;
		String funid = paymentForm.getFuncId();
		//DBUtility newUtil = null;
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			//newUtil = new DBUtility();	 	
			String query = "SELECT  FUNCTION_NAME  FROM IGRS_FUNCTION_MASTER WHERE FUNCTION_ID = '"+funid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			oid = dbUtil.executeQry(query);
			logger.debug("the value in officeid is " + oid);
		} catch (Exception x) {
			logger.error("Exception in getOfficeid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return oid;
	}
	
	/**
     * ===========================================================================
     * Method         :   gettranDate()
     * Description    :   Returns String of purpose name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String gettranDate(PaymentForm paymentForm) throws Exception{
		String oid=null;
		String transid = paymentForm.getTransId();
		
		//DBUtility newUtil = null;
		DBUtility dbUtil = null;
		try {
			//newUtil = new DBUtility();
			dbUtil = new DBUtility();
			String query = "SELECT  TO_CHAR(PAYMENT_DATE,'MM/dd/yyyy HH:mm:ss')  FROM IGRS_PAYMENT_TXN_DETAILS  WHERE TRANSACTION_ID = '"+transid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			oid = dbUtil.executeQry(query);
			logger.debug("the value in officeid is " + oid);
		} catch (Exception x) {
			logger.error("Exception in getOfficeid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return oid;
	}
	
	
	
	/**
     * ===========================================================================
     * Method         :   getdepoDtl()
     * Description    :   Returns String of depositor name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String getdepoDtl(PaymentForm paymentForm) throws Exception{
		String dep=null;
		String transids = paymentForm.getChallanNO();
		String transid=transids.toLowerCase();
		//DBUtility newUtil = null;
		DBUtility dbUtil = null;
		try {
			//newUtil = new DBUtility();	 	
			dbUtil = new DBUtility();
			String query = "SELECT  FIRST_NAME||' '||MIDDLE_NAME||' ' ||LAST_NAME  FROM IGRS_PAYMENT_ECHALLAN_DETAILS where LOWER(CIN) = '"+transid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			dep = dbUtil.executeQry(query);
			logger.debug("the value in officeid is " + dep);
		} catch (Exception x) {
			logger.error("Exception in getOfficeid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return dep;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getdepoDtl()
     * Description    :   Returns String of depositor name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String getdepoDtlON(PaymentForm paymentForm) throws Exception{
		String dep=null;
		//String transids = paymentForm.getOnlineCinNo();
		String transid=paymentForm.getCrnNumber(); 
		String []arr=new String[1];
		arr[0]=transid;
		DBUtility dbUtil = null;
		//DBUtility newUtil = null;
		try {
			//newUtil = new DBUtility();	
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.ONLINE_PRNT_QRY);
			dep=dbUtil.executeQry(arr);
			
		} catch (Exception x) {
			logger.error("Exception in getdepoDtlON(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return dep;
	}
	
	

	/**
     * ===========================================================================
     * Method         :   getdepoDtl()
     * Description    :   Returns String of depositor name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String getdepoDtlCa(PaymentForm paymentForm) throws Exception{
		String dep=null;
		String transids = paymentForm.getReceiptID();
		String transid= transids.toLowerCase();
		//DBUtility newUtil = null;
		DBUtility dbUtil = null;
		try {
			//newUtil = new DBUtility();	 	
			dbUtil = new DBUtility();
			String query = "SELECT  FIRST_NAME||' '||MIDDLE_NAME||' ' ||LAST_NAME  FROM IGRS_PAYMENT_MODE_DETAILS where TRANSACTION_ID = '"+transid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			dep = dbUtil.executeQry(query);
			logger.debug("the value in officeid is " + dep);
		} catch (Exception x) {
			logger.error("Exception in getOfficeid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return dep;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getissueName()
     * Description    :   Returns String of purpose name. 
     * Arguments      :   form
     * return type    :   String
     * ===========================================================================
     */  
	public String getissueName(PaymentForm paymentForm) throws Exception{
		String oid=null;
		String uid = paymentForm.getLoggedUser();
		//DBUtility newUtil = null;
		DBUtility dbUtil = null;
		try {
			//newUtil = new DBUtility();
			dbUtil = new DBUtility();
			String query = "SELECT FIRST_NAME||' '||MIDDLE_NAME||' ' ||LAST_NAME FROM IGRS_EMP_MASTER where EMP_ID ='" +uid+"'";
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			oid = dbUtil.executeQry(query);
			if (oid== null|| oid=="")
			{
				String query2 = "SELECT FIRST_NAME||' '||MIDDLE_NAME||' ' ||LAST_NAME FROM IGRS_USER_REG_DETAILS where USER_ID ='" +uid+"'";
				logger.debug("the query is  in DAO   " + query2);
				dbUtil.createStatement();
				oid = dbUtil.executeQry(query2);
			}
			logger.debug("the value in username is " + oid);
		} catch (Exception x) {
			logger.error("Exception in username(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return oid;
	}
	/***************************************************************** 
	 *    Method Name   :  getTxnMapping()
     *    Arguments     :  Transaction ID
     *    Return        :  ArrayList
     *    Throws   	    :  NullPointer  or SQLException or Exception
     *****************************************************************/
	  public  ArrayList getTxnMpping(String _cashTxnID)
	         throws NullPointerException,SQLException,Exception{	
	    	//DBUtility dbMappingUtilty = null;
	    	String returnFlag = "fail";
	    	ArrayList txnIDList = null;
	    	DBUtility dbUtil = null;
	    	try{					
	       		  //dbMappingUtilty = new DBUtility();
	    		dbUtil = new DBUtility();
				  String challanTxnqry = CommonSQL.mppingTxnqry + "WHERE TXN_ID LIKE '" + _cashTxnID +"'" ;  
				  dbUtil.createStatement();
				  txnIDList = dbUtil.executeQuery(challanTxnqry);	
				
	    	}catch (NullPointerException ne) 
	    	{
	    		logger.error("Null Exception at getTxnMapping in DAO " + ne);
	    		ne.printStackTrace();
	    		
	    	}catch (SQLException se) {
	    		logger.error("SQL Exception at getTxnMapping in DAO " + se);
	    		se.printStackTrace();
			}
	    	catch(Exception e){
	    		logger.error(" Exception at getTxnMapping in DAO " + e);
	    		e.printStackTrace();
	    	}
	        finally {
	    		try{	    
	    			dbUtil.closeConnection();
	    		}
	    		catch (Exception e) {
	    			logger.error("Exception when Close Connection for getTxnMapping " + e);
	    			e.printStackTrace();
	    		}		
	        }
	        return txnIDList;
	      }
	  
		/***************************************************************** 
		 *    Method Name   :  getTxnMapping()
	     *    Arguments     :  Transaction ID
	     *    Return        :  ArrayList
	     *    Throws   	    :  NullPointer  or SQLException or Exception
	     *****************************************************************/
		  public  ArrayList getBankIds()
		         throws NullPointerException,SQLException,Exception{	
		    	//DBUtility dbUtilty = null;
		    	String returnFlag = "fail";
		    	ArrayList txnIDList = null;
		    	DBUtility dbUtil = null;
		    	try{					
		       		 // dbUtilty = new DBUtility();   
		    		dbUtil = new DBUtility();
					  //String challanTxnqry = CommonSQL.PAYMENTS_BANKID_SELECT;  
					  String challanTxnqry = CommonSQL.PAYMENTS_BANKS_SELECT;  
					  dbUtil.createStatement();
					  txnIDList = dbUtil.executeQuery(challanTxnqry);			
		    		
		    	}catch (NullPointerException ne) {
		    		logger.error("Null Exception at getTxnMapping in DAO " + ne);
		    	}catch (SQLException se) {
		    		logger.error("SQL Exception at getTxnMapping in DAO " + se);
				}
		    	catch(Exception e){
		    		logger.error(" Exception at getTxnMapping in DAO " + e);
		    	}
		        finally {
		    		try{	    
		    			dbUtil.closeConnection();
		    		}
		    		catch (Exception e) {
		    			logger.error("Exception when Close Connection for getTxnMapping " + e);
		    		}		
		        }
		        return txnIDList;
		      }
	    /********************************************************************
	     *  Method Name  :  setPaymentTxn()
	     *  Arguments    :  Created new Transaction ID and given Transaction ID 
	     *  Return       :  Boolean
	     *  Throws 	     :  NullPointer  or SQLException or Exception
	     ********************************************************************/
	  public   boolean setPaymentTxn(String _cashTxnId,String _txnID)
	        throws NullPointerException,Exception{			
			boolean flagcash=true;
			String returnflag = null;
			String payTxnID;			
			try{	      	 
		    	returnflag = setTxnMapID(_cashTxnId,_txnID);		    	
		      	 if (returnflag != null && returnflag!="false"){
		      		 flagcash = setStatus(_cashTxnId);	
		      		 if(flagcash){		      			
		      			 return flagcash;		      			
		      		 }
		      		 else{
		      			 return false;
		      		 }
		      	 }
		      	 else{		      		 
		      		 return false;
		      	 }
			}catch (NullPointerException ne) {
				logger.error("Exception at getPayemntTxN in DAO  " + ne);
			}			
			catch(Exception e){
				logger.error("Exception at getPayemntTxN in DAO  " + e);
			}
			return flagcash;
		}
	
	 /*********************************************************************
	  *  Method Name  :  setTxnMappingID()
	  *  Arguments    :  Created new Transaction ID and given Transaction ID 
	  *  Return       :  String
	  *  Throws 	  :  NullPointer  or SQLException or Exception
	  ********************************************************************/
	  public  String setTxnMapID(String _cashTxnId,String _txnID)
	         throws NullPointerException,SQLException,Exception{
	    	//DBUtility dbsetUtility = null;
	    	boolean returnFlag = false;
	    	String returnid = "false";
	    	DBUtility dbUtil = null;
	    	try{								
	    		//dbsetUtility = new DBUtility();
	    		dbUtil = new DBUtility();
	    		dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
		      String[] txnID = new String[2];		      
		      txnID[0] = _txnID;
		      txnID[1] = _cashTxnId;
		     returnFlag = dbUtil.executeUpdate(txnID);
		     if(!returnFlag){	    	 
		    	 dbUtil.rollback();
		     }
		     if(returnFlag){		    	
		    	 returnid = _txnID;
		     }		     
		    }
	    	catch (NullPointerException ne) {
			logger.error("Null Exception at setTxnMapID  in DAO " + ne);
			ne.printStackTrace();
		    }
	    	catch (SQLException se) {
	    	logger.error("SQL Exception at setTxnMapID in DAO " + se);
	    	se.printStackTrace();
			}
		    catch(Exception e){
			logger.error(" Exception at setTxnMapID in DAO " + e);
			e.printStackTrace();
		    }
	        finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at setTxnMapID in DAO  " + e);
			 e.printStackTrace();
			 }		
	        }
	        return returnid;
	   }
	  
     /******************************************************************  
	  *   Method Name  :   setTxnID()
	  *   Arguments    :   TotalAmount Received 
	  *   Return       :   Boolean
	  *   Throws 	  :   NullPointer  or SQLException or Exception
	  *****************************************************************/
	  public String setTxnID(double _totamt, String _mode, String purpose, String usid)throws NullPointerException,
                                            SQLException,Exception{
    	//DBUtility dbsetUtility = null;
    	boolean returnFlag = false;
    	String returnid = "false";
    	DBUtility dbUtil = null;
    	try{								
    		//dbsetUtility = new DBUtility();
    		dbUtil = new DBUtility();
    		IGRSCommon igrsCommon = new IGRSCommon();
    		String payment_txn_id = igrsCommon.getSequenceNumber("IGRS_PAYMENT_TXNID", "PTID");    		
           if(payment_txn_id != null){   	
        	   dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
    		String[] txnID = new String[5];
            txnID[0] = payment_txn_id;
	        txnID[1] = String.valueOf(_totamt);
	        txnID[2] = _mode;
	        txnID[3] = purpose;
	        txnID[4] = usid;
	        logger.debug("INSIDE DAO........THE AMOUNT IS......"+txnID[1]);
	        returnFlag = dbUtil.executeUpdate(txnID);	     
	      
	        if(!returnFlag){
	        	
	        	dbUtil.rollback();
	       }
	       if(returnFlag){
	    	
	    	 String txnno = payment_txn_id;
				 //getPayTxn();
	    	 returnid = txnno;
	       }	     
	    }else{
	    	returnid = "false";
	    }
    	}
    	
    	catch (NullPointerException ne) {
		logger.error("Null Exception at insert TxnID  in DAO " + ne);
	    }
    	catch (SQLException se) {
    		logger.error("SQL Exception at insert TxnID  in DAO " + se); 
		}
	    catch(Exception e){
		logger.error(" Exception at insert TxnID in DAO " + e);
	    }
        finally {
		 try{	    
			 dbUtil.closeConnection();
		 }
		 catch (Exception e) {
		 logger.error("Exception at setTxnID in DAO  " + e);
		 }		
        }  
       
        return returnid;
   }
	 
	  /******************************************************************  
		  *   Method Name  :   PaymentTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
      *******************************************************************/  
	  
	  public  boolean PaymentTransactionFinal (PaymentForm eform)throws NullPointerException,
      SQLException,Exception{
	   boolean transactionflag = false;
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getEntrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   String parentColmName = eform.getParentColumnName();
	   String moduleCnstnt = "PTID";
	   String resIds = eform.getReceiptID();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	  // DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{
	  // transmgtUtil = new DBUtility();
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	   //String challanSql = "select GROSS_AMOUNT from IGRS_PAYMENT_MODE_DETAILS where STATUS_FLAG = 'A' AND TRANSACTION_ID='"+resId+"'"+" "+"AND GROSS_AMOUNT='"+amt+"'"+" AND FUNCTION_ID = '"+purp+"'";
	  /* String cashSql = "select GROSS_AMOUNT, FUNCTION_ID,STATUS_FLAG from IGRS_PAYMENT_MODE_DETAILS where TRANSACTION_ID='"+resId+"'";
	   transmgtUtil.createStatement();
	   recordList = transmgtUtil.executeQuery(cashSql);*/
	   String arry[]=new String[1];
	   arry[0]=resId;
	 
	   dbUtil.createPreparedStatement(CommonSQL.cashSqlNew);
	   recordList = dbUtil.executeQuery(arry);
	   
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String am = (String) compSub.get(0);
						  String fn = (String) compSub.get(1);
						  String st = (String) compSub.get(2);
						  if(!amt.equals(am)&& am!= null && am!=""){
							  throw new Exception("30001");
						  }
						  if(!purp.equals(fn)&& fn!= null && fn!=""){
							  throw new Exception("30002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
							  throw new Exception("30003");
						  }else{
							  String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							  dbUtil.createStatement();
						  	   String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   txnmapDetails [1] = resId;
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[1];
						  	   resStatus [0] = resId;
						  	   String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	   transactionflag=dbUtil.executeUpdate(txnDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag=  dbUtil.executeUpdate(txnmapDetails); 
						  	   if(transactionflag){
						  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY = 'PAYMENTS', UPDATE_DATE=SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	 dbUtil.createStatement();
						  	   transactionflag= dbUtil.executeUpdate(SQL2);
						  	  if(transactionflag){
						  		dbUtil.commit();	
						  	  }
						  	   }
						  	   }
						  	   }
							  
						  }  
					  }
				  }  
		   }
	      }
	     else{
	    	 throw new Exception("30000");
	     }
	  }catch (NullPointerException ne) {
		  dbUtil.rollback();
		   throw ne;
			
	    }
   	    catch (SQLException se) {
   	    	dbUtil.rollback();
   	    	
   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
   	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if(msg.equalsIgnoreCase("30000")||msg.equalsIgnoreCase("30001")||msg.equalsIgnoreCase("30002")||msg.equalsIgnoreCase("30003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }

	  
	  //akansha
	  
	  public  boolean PaymentTransactionFinalNew (PaymentForm eform)throws NullPointerException,
      SQLException,Exception{
	   boolean transactionflag = false;
	   ArrayList recordList = new ArrayList();
	   ArrayList chkList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getEntrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   String parentColmName = eform.getParentColumnName();
	   String moduleCnstnt = "PTID";
	   String resIds = eform.getReceiptID();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	  // DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{
	  // transmgtUtil = new DBUtility();
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   if(eform.getTransId()!=null){
	String checkRefresh[] = new String[5];
	checkRefresh[0]=eform.getParentRefId();
	checkRefresh[1] = mode;	
	checkRefresh[2] = amt;
	checkRefresh[3] = amt;
	checkRefresh[4] = eform.getTransId();			
			dbUtil.createPreparedStatement(CommonSQL.Cash_Check);
			chkList = dbUtil.executeQuery(checkRefresh);
			   if(chkList!=null&&!chkList.isEmpty()){
				   throw new Exception("30007");  
				   
			   }
			   
			   else{
				   
				   String arry[]=new String[1];
				   arry[0]=resId;
				 
				   dbUtil.createPreparedStatement(CommonSQL.cashSqlNew);
				   recordList = dbUtil.executeQuery(arry);
				   
				   if(recordList != null && recordList.size()>0){
					   for(int j= 0; j<recordList.size(); j++){
						   comp.add((ArrayList)recordList.get(j));
							  if(comp != null && comp.size()>0){
								  for (int k=0; k< comp.size(); k++){
									  ArrayList compSub = (ArrayList)comp.get(k);
									  String am = (String) compSub.get(0);
									  String fn = (String) compSub.get(1);
									  String st = (String) compSub.get(2);
									  String createdBy = (String) compSub.get(3);
									  String applicationId = (String) compSub.get(4);
									  if(!amt.equals(am)&& am!= null && am!=""){
										  throw new Exception("30001");
									  }
									  if(!purp.equals(fn)&& fn!= null && fn!=""){
										  throw new Exception("30002");
									  }
									  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
										  throw new Exception("30003");
									  }
									  if(!createdBy.equals(usid)&& createdBy!= null && createdBy!=""){
										  throw new Exception("30010");
									  }
									  if(!applicationId.equals(eform.getParentRefId())&& applicationId!= null && applicationId!=""){
										  throw new Exception("30010");
									  }
									  
									  else{
										  String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
										  dbUtil.createStatement();
									  	   String seqNumber = dbUtil.executeQry(SQL1);
									  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
									  	   transId = pymntTxnid;
									  	   String[] txnDetails = new String[9];
									  	   txnDetails[0] = pymntTxnid;
									  	   txnDetails[1] = amt;
									  	   txnDetails[2] = mode;
									  	   txnDetails[3] = purp;
									  	   txnDetails[4] = usid;
									  	   txnDetails[5] = parntKey;
									  	   txnDetails[6] = eform.getParentRefId();
									  	   txnDetails[7] = eform.getParentOffId();
									  	   txnDetails[8] = eform.getParentDistId();
									  	   String[] txnmapDetails = new String[3];
									  	   txnmapDetails [0] = pymntTxnid;
									  	   txnmapDetails [1] = resId;
									  	   txnmapDetails [2] = usid;
									  	   String[] resStatus = new String[3];
									  	   resStatus [0] = resId;
									  	 resStatus [1] = createdBy;
									  	 resStatus [2] = applicationId;
									  	   String[] parentDetails = new String[5];
									  	   parentDetails [0] = parentTbl;
									  	   parentDetails [1] = pymntTxnid;
									  	   parentDetails [2] = amt;
									  	   parentDetails [3] = mode;
									  	   parentDetails [4] = parntKey;
									  	   String[] parentFlag = new String[1];
									  	   parentFlag [0] = parntKey; 
									  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqryCash);
									  	   transactionflag=dbUtil.executeUpdate(resStatus);
									  	   if(transactionflag){
									  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
									  	   transactionflag=dbUtil.executeUpdate(txnDetails);
									  	   if(transactionflag){
									  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
									  	   transactionflag=  dbUtil.executeUpdate(txnmapDetails); 
									  	   if(transactionflag){
									  		   
									  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
									  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'C', UPDATE_BY = 'PAYMENTS', UPDATE_DATE=SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' and REG_TXN_ID = '"+applicationId+"'";
									  	   logger.debug(SQL2);
									  	 dbUtil.createStatement();
									  	   transactionflag= dbUtil.executeUpdate(SQL2);
									  	  if(transactionflag){
									  		dbUtil.commit();	
									  	  }
									  	  else{
									  	dbUtil.rollback();
									  	throw new Exception("30010");
									  	   }
									  	   }
									  	   }
									  	   }
										  
									  }  
								  }
							  }  
					   }
				      }
				     else{
				    	 throw new Exception("30010");
				     }
				   
			   }
		   }
			   else{  
	   String arry[]=new String[1];
	   arry[0]=resId;
	 
	   dbUtil.createPreparedStatement(CommonSQL.cashSqlNew);
	   recordList = dbUtil.executeQuery(arry);
	   
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String am = (String) compSub.get(0);
						  String fn = (String) compSub.get(1);
						  String st = (String) compSub.get(2);
						  String createdBy = (String) compSub.get(3);
						  String applicationId = (String) compSub.get(4);
						  if(!amt.equals(am)&& am!= null && am!=""){
							  throw new Exception("30001");
						  }
						  if(!purp.equals(fn)&& fn!= null && fn!=""){
							  throw new Exception("30002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
							  throw new Exception("30003");
						  }
						  if(!createdBy.equals(usid)&& createdBy!= null && createdBy!=""){
							  throw new Exception("30010");
						  }
						  if(!applicationId.equals(eform.getParentRefId())&& applicationId!= null && applicationId!=""){
							  throw new Exception("30010");
						  }
						  else{
							  String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							  dbUtil.createStatement();
						  	   String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   txnmapDetails [1] = resId;
						  	   txnmapDetails [2] = usid;
						  	 String[] resStatus = new String[3];
						  	   resStatus [0] = resId;
						  	 resStatus [1] = createdBy;
						  	 resStatus [2] = applicationId;
						  	   String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqryCash);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	   transactionflag=dbUtil.executeUpdate(txnDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag=  dbUtil.executeUpdate(txnmapDetails); 
						  	   if(transactionflag){
						  		   
						  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'C', UPDATE_BY = 'PAYMENTS', UPDATE_DATE=SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I'and REG_TXN_ID = '"+applicationId+"'";
									  	   logger.debug(SQL2);
						  	   logger.debug(SQL2);
						  	 dbUtil.createStatement();
						  	   transactionflag= dbUtil.executeUpdate(SQL2);
						  	  if(transactionflag){
						  		dbUtil.commit();	
						  	  }
						  	   }
						  	   }
						  	   }
							  
						  }  
					  }
				  }  
		   }
	      }
	     else{
	    	 throw new Exception("30000");
	     }
	   }
	  }catch (NullPointerException ne) {
		  dbUtil.rollback();
		   throw ne;
			
	    }
   	    catch (SQLException se) {
   	    	dbUtil.rollback();
   	    	
   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
   	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if(msg.equalsIgnoreCase("30000")||msg.equalsIgnoreCase("30001")||msg.equalsIgnoreCase("30002")||msg.equalsIgnoreCase("30003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  
	  
	  public  boolean cashCheck (PaymentForm eform)throws NullPointerException,
      SQLException,Exception{
	   boolean transactionflag = false;
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	
	   String usid = eform.getLoggedUser();
	   String amt = eform.getEntrAmt();
	  
	   String resIds = eform.getReceiptID();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	  
	  // DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{
	  // transmgtUtil = new DBUtility();
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	  
	  }catch (NullPointerException ne) {
		  dbUtil.rollback();
		   throw ne;
			
	    }
   	    catch (SQLException se) {
   	    	dbUtil.rollback();
   	    	
   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
   	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if(msg.equalsIgnoreCase("30000")||msg.equalsIgnoreCase("30001")||msg.equalsIgnoreCase("30002")||msg.equalsIgnoreCase("30003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }

	  /******************************************************************  
		  *   Method Name  :   PaymentChallanTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
      *******************************************************************/  
	  
	  
	  public  synchronized boolean PaymentChallanTransactionFinal (PaymentForm eform)throws NullPointerException,
      SQLException,Exception{
	   boolean transactionflag = false; 
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getChallanentrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   //String bankid = eform.getChallanBankId();
	   String bankid = eform.getChallanRefNo();
	   String parentColmName = eform.getParentColumnName();
	   String moduleCnstnt = "PTID";
	   //String resIds = _form.getReceiptID();
	   String resIds = eform.getChallanNO();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   
	  		
	  // DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{  	
	  // transmgtUtil = new DBUtility();
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	   String challanSql = "select CHALLAN_SERIAL_NUMBER,CIN,PAID_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where EPNR_NO ='" + bankid + "'";
	   dbUtil.createStatement();  
	   recordList = dbUtil.executeQuery(challanSql);
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String txnNo =(String) compSub.get(0);
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						  
						  
						  if(cin!=null&& cin!=""){
							  if(!resId.equals(cin.toLowerCase())&& cin !=null && cin!=""){
								  throw new Exception("20000");
							  }
							  }else{throw new Exception("20005");
								  
							  }
						  if(!amt.equals(am)&& am!= null && am!=""){
							  throw new Exception("20001");
						  }
						  if(!purp.equals(fn)&& fn!= null && fn!=""){
							  throw new Exception("20002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
							  throw new Exception("20003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (txnNo != null && txnNo!=""){
						  	   txnmapDetails [1] = txnNo;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (txnNo != null && txnNo!=""){
						  	   resStatus [1] = txnNo;}
						  	   String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry1);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	   transactionflag=dbUtil.executeUpdate(txnDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag=dbUtil.executeUpdate(txnmapDetails); 
						  	   if(transactionflag){
						  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY= 'PAYMENTS', UPDATE_DATE= SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	 dbUtil.createStatement();
						  	   transactionflag= dbUtil.executeUpdate(SQL2);
						  	  if(transactionflag){
						  		dbUtil.commit();
						  	  }
						  	   }
						  	   }
						  	   }
						  }  
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("20004"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
   	    catch (SQLException se) {
   	    	dbUtil.rollback();
   	    	
   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
   	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("20000")||msg.equalsIgnoreCase("20001")||msg.equalsIgnoreCase("20002")||msg.equalsIgnoreCase("20003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  
	  
	  /******************************************************************  
		  *   Method Name  :   PaymentOnlineTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
   *******************************************************************/  
	  
	  
	  public  synchronized boolean PaymentOnlineTransactionFinal (PaymentForm eform)throws NullPointerException,
   SQLException,Exception{
	   boolean transactionflag = false; 
	   
	   
	   String arr[]=new String[1];
	  
	   
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getOnlineEntrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   String parentColmName = eform.getParentColumnName();
	   String moduleCnstnt = "PTID";
	   String resIds = eform.getOnlineCinNo();
	   String resId = resIds.toLowerCase();
	   arr[0]=resId;
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   
	   DBUtility dbUtil = null;
	   //DBUtility transmgtUtil = null;
	   try{  	
	   //transmgtUtil = new DBUtility();
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   dbUtil.createPreparedStatement(CommonSQL.ONLINE_SEARCH_QRY);
	   recordList = dbUtil.executeQuery(arr);
		 
       if(recordList != null && recordList.size()>0){
     	  for(int j= 0; j<recordList.size(); j++){
     		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);
						  //arr2[0]=tid;
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(!amt.equals(am)&& am !=null && am!=""){
							  throw new Exception("40001");
						  }
						  if(!purp.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("40002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("40003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (tid != null && tid!=""){
						  	   txnmapDetails [1] = tid;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (tid != null && tid!=""){
						  	   resStatus [1] = tid;}
						  	   String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry2);
						  	   transactionflag= dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	    transactionflag=dbUtil.executeUpdate(txnDetails);
						  	    if(transactionflag){
						  	    	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag= dbUtil.executeUpdate(txnmapDetails); 
						  	   if(transactionflag){
						  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY= 'PAYMENTS', UPDATE_DATE= SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	 dbUtil.createStatement();
						  	   transactionflag= dbUtil.executeUpdate(SQL2);
						  	  if(transactionflag){
						  		dbUtil.commit();
						  	   }
						  	  }
						  	    }  
						  	    }
						  }  
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("40000"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("40000")||msg.equalsIgnoreCase("40001")||msg.equalsIgnoreCase("40002")||msg.equalsIgnoreCase("40003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  
	  //debit through online
	  public  synchronized boolean PaymentOnlineTransactionFinalM (PaymentForm eform)throws NullPointerException,
	   SQLException,Exception{
		   boolean transactionflag = false; 
		   
		   
		   String arr[]=new String[1];
		  
		   
		   ArrayList recordList = new ArrayList();
		   ArrayList comp = new ArrayList();
		   String mode = eform.getPayMode(); 
		   String purp = eform.getFuncId();
		   String usid = eform.getLoggedUser();
		   String amt = eform.getOnlineEntrAmt();
		   String parntKey = eform.getParentKey();
		   String parentTbl = eform.getParentTable();
		   String parentColmName = eform.getParentColumnName();
		   String moduleCnstnt = "PTID";
		 //  String resIds = eform.getOnlineCinNo();
		//   String resId = resIds.toLowerCase();
		//   arr[0]=resId;
		   String transId = "";
		   Date date = new Date();
		   Format yearformat = new SimpleDateFormat("yyyy");
		   Format monthformat = new SimpleDateFormat("MM");
		   Format dateformat = new SimpleDateFormat("dd");
		   String dfmt = dateformat.format(date);
		   String yfmt = yearformat.format(date);
		   String mfmt = monthformat.format(date);
		   
		   DBUtility dbUtil = null;	
		   //DBUtility transmgtUtil = null;
		   try{  	
		   //transmgtUtil = new DBUtility();
			   dbUtil = new DBUtility();
			   dbUtil.setAutoCommit(false);
			  
			  
						
								   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
								   dbUtil.createStatement();
							       String seqNumber = dbUtil.executeQry(SQL1);
							  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
							  	   transId = pymntTxnid;
							  	   eform.setTransId(transId);
							  	   String[] txnDetails = new String[9];
							  	   txnDetails[0] = pymntTxnid;
							  	   txnDetails[1] = amt;
							  	   txnDetails[2] = mode;
							  	   txnDetails[3] = purp;
							  	   txnDetails[4] = usid;
							  	   txnDetails[5] = parntKey;
							  	   txnDetails[6] = eform.getParentRefId();
							  	   txnDetails[7] = eform.getParentOffId();
							  	   txnDetails[8] = eform.getParentDistId();
							  	   String[] txnmapDetails = new String[3];
							  	   txnmapDetails [0] = pymntTxnid;
							  	   String tid = "1212";
							  	   if (tid != null && tid!=""){
							  	   txnmapDetails [1] = tid;}
							  	   txnmapDetails [2] = usid;
							  	 //  String[] resStatus = new String[2];
							  	 //  resStatus [0] = resId;
							  	   if (tid != null && tid!=""){
							  	//   resStatus [1] = tid;
							  		   }
							  	   String[] parentDetails = new String[5];
							  	   parentDetails [0] = parentTbl;
							  	   parentDetails [1] = pymntTxnid;
							  	   parentDetails [2] = amt;
							  	   parentDetails [3] = mode;
							  	   parentDetails [4] = parntKey;
							  	   String[] parentFlag = new String[1];
							  	   parentFlag [0] = parntKey; 
							//  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry2);
							  	   
							  	   
							  	  double payableAmt=Double.parseDouble(getPayableAmount(eform));
				        		    
				        		    if(payableAmt!=Double.parseDouble(amt)){
				        		    	 throw new Exception("10002");
				        		    	
				        		    }
							  	  String licCheckSQL = "SELECT distinct created_by from "+ parentTbl +
									" where "+parentColmName+" = '"+parntKey+"' ";
									//" and b.status='A' and APPLCTN_STATUS=8 and license_number is not null";
						
							  dbUtil.createStatement();
							       String userID = dbUtil.executeQry(licCheckSQL);
						if (!userID.equalsIgnoreCase(eform.getLoggedUser())){
							 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD
						  	
						  			 	transId=pymntTxnid;
									   String[] spTableD = new String[8];
									   spTableD [0] = userID; 
									   spTableD [1] = mode; 
									   spTableD [2] = amt; 
									   spTableD [3] = amt; 
									   spTableD [4] = amt;
									   spTableD [5] = "0"; 
									   spTableD [6] = purp; 
									   spTableD [7] = pymntTxnid; 
									//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
										//dbUtil.executeUpdate(spTable);
									 dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_CREDIT);
									transactionflag= dbUtil.executeUpdate(spTableD);
									if(transactionflag){
									dbUtil.commit();
									}
						throw new Exception("10025");
						}
						
							  	   transactionflag= true;
							  	   if(transactionflag){
							  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
							  	    transactionflag=dbUtil.executeUpdate(txnDetails);
							  	    if(transactionflag){
							  	    	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
							  	   transactionflag= dbUtil.executeUpdate(txnmapDetails); 
							  	   
							  	   if(transactionflag)
							  	   {
							  		 dbUtil.createPreparedStatement(CommonSQL.UPDATE_ONLINE);
							  		 String ar[] = new String[7];
							  		 ar[0] = eform.getChallanNumber();
							  		 ar[1] = eform.getCin();
							  		 ar[2] = eform.getBrn();
							  		 ar[3] = eform.getScrollNumber();
							  		 ar[4] = eform.getScrollDate();
							  		 ar[5] = eform.getTrasactionDate();
							  		 ar[6] = eform.getCrnNumber();
							  		 transactionflag= dbUtil.executeUpdate(ar); 
							  	   }
							  	   
							  	   if(transactionflag){
							  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
							  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY= 'PAYMENTS', UPDATE_DATE= SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
							  	   logger.debug(SQL2);
							  	 dbUtil.createStatement();
							  	   transactionflag= dbUtil.executeUpdate(SQL2);
							  	  if(transactionflag){
							  		dbUtil.commit();
					
							  	  }
							  	   }
							  	    }
							  	   }
		   }
		   catch (Exception e) {
			   String msg = e.getMessage();
		    	if (msg.equalsIgnoreCase("10025")){
		    		throw e;
		    	}else if (msg.equalsIgnoreCase("10002")){
		    		throw e;
		    	}
			e.printStackTrace();
		}
		   finally {
				try {
					if(dbUtil!=null)
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.error("Exception in closing connection" + ex);
					
				}
		   }
		   
		    eform.setTransId(transId);
		    return transactionflag;   
		  }
	  
	  
	  
/******************************************************************  
		  *   Method Name  :   PaymentOnlineTransactionFinalSP()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
	  //changes 0n 27 dec 2105
	  
	  public  synchronized boolean PaymentOnlineTransactionFinalSP (PaymentForm eform,HttpSession session)throws NullPointerException,
SQLException,Exception{
	   boolean transactionflag = false; 
	   
	   String sessionLic="";

	   String arr[]=new String[1];
	  
	   if(session.getAttribute("licNo")!=null)
			  sessionLic=(String) session.getAttribute("licNo");


	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getOnlineEntrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   String parentColmName = eform.getParentColumnName();
	   String licNo = sessionLic;
	   String moduleCnstnt = "PTID";
	   String resIds = eform.getOnlineCinNo();
	  String resId = resIds.toLowerCase();
	  arr[0]=resId;
	   String transId = "";
	   String[] actdtl = new String[2];
	   actdtl [0] = licNo;
	   actdtl [1] = licNo;
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   
	   DBUtility dbUtil = null;	
	  // DBUtility transmgtUtil = null;
	   try{  	
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   dbUtil.createPreparedStatement(CommonSQL.ONLINE_SEARCH_QRY);
	   recordList = dbUtil.executeQuery(arr);
		 
    if(recordList != null && recordList.size()>0){
  	  for(int j= 0; j<recordList.size(); j++){
  		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);
						  //arr2[0]=tid;
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(!amt.equals(am)&& am !=null && am!=""){
							  throw new Exception("40001");
						  }
						  if(!purp.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("40002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("40003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String SQL3 = CommonSQL.spActQRY1_2;
						  	 dbUtil.createPreparedStatement(SQL3);
						  	   String spActBal = dbUtil.executeQry(actdtl);
						  	   logger.debug("the amount balance is.."+spActBal);
						  	  
						  	   double spActBald = Double.parseDouble(spActBal);
						  	   double amtd = Double.parseDouble(amt);
						  	   double resultSpBald = spActBald+ amtd;
						  	   logger.debug("the result amount balance is.."+resultSpBald);
						  	   String resultSpBal = Double.toString(resultSpBald);
						  	   
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (tid != null && tid!=""){
						  	   txnmapDetails [1] = tid;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (tid != null && tid!=""){
						  	   resStatus [1] = tid;}
						  	   /*String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;*/
						  	   String[] parentDetails = new String[6];
						  	   parentDetails [0] = mode;
						  	   parentDetails [1] = pymntTxnid ;
						  	   parentDetails [2] = amt;
						  	   
						  	   if(resultSpBal==null){
						  		 dbUtil.createPreparedStatement(SQL3);
						  		spActBal = dbUtil.executeQry(actdtl);
						  		 spActBald = Double.parseDouble(spActBal);
							  	    amtd = Double.parseDouble(amt);
							  	    resultSpBald = spActBald+ amtd;
							  	  resultSpBal = Double.toString(resultSpBald);
						  		 	   }
						  	   parentDetails [3] = resultSpBal;
						  	   parentDetails [4] = amt;
						  	   parentDetails [5] = parntKey ;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry2);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	     if(transactionflag){
						  	    	dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	      transactionflag=dbUtil.executeUpdate(txnDetails);
						  	        if(transactionflag){
						  	        	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	           transactionflag= dbUtil.executeUpdate(txnmapDetails); 
						  	  /* String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY= 'PAYMENTS', UPDATE_DATE= SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	   transmgtUtil.createStatement();
						  	   transmgtUtil.executeUpdate(SQL2);*/
						  	             if(transactionflag){
						  	            	dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY_1);
						  	                 transactionflag= dbUtil.executeUpdate(parentDetails);
						  	  
						  	                 if(transactionflag){
						  	                	dbUtil.commit();
						  	                                    }
						  	                 }
						  	             }
						  	        }
						  }  
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("40000"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("40000")||msg.equalsIgnoreCase("40001")||msg.equalsIgnoreCase("40002")||msg.equalsIgnoreCase("40003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  //changes on 27 dec 2015
	  //online credit limit estamp modified by shreeraj
	  public  synchronized boolean PaymentOnlineTransactionFinalSPM (PaymentForm eform,HttpSession session)throws NullPointerException,
	  SQLException,Exception{
	  	   boolean transactionflag = false; 
	  	 String sessionLic="";
		
		  if(session.getAttribute("licNo")!=null)
		  sessionLic=(String) session.getAttribute("licNo");
	  	   
	  	   String arr[]=new String[1];
	  	  
	  	   //FOR SP ONLINE MOHIT
	  	
	  	   ArrayList comp = new ArrayList();
	  	   String mode = eform.getPayMode(); 
	  	   String purp = eform.getFuncId();
	  	   String usid = eform.getLoggedUser();
	  	   String amt = eform.getOnlineEntrAmt();
	  	   String parntKey = eform.getParentKey();
	  	   String parentTbl = eform.getParentTable();
	  	   String parentColmName = eform.getParentColumnName();
	  	  // String licNo = eform.getSpLicenceNo();
	  	 String licNo =sessionLic;
	  	   String moduleCnstnt = "PTID";
	  	   String resIds = eform.getOnlineCinNo();
	  	 //  String resId = resIds.toLowerCase();
	  	//   arr[0]=resId;
	  	   String transId = "";
	  	   String[] actdtl = new String[2];
	  	   actdtl [0] = licNo;
	  	   actdtl [1] = licNo;
	  	   Date date = new Date();
	  	   Format yearformat = new SimpleDateFormat("yyyy");
	  	   Format monthformat = new SimpleDateFormat("MM");
	  	   Format dateformat = new SimpleDateFormat("dd");
	  	   String dfmt = dateformat.format(date);
	  	   String yfmt = yearformat.format(date);
	  	   String mfmt = monthformat.format(date);
	  	   ArrayList swapList=null;
	  	 DBUtility dbUtil = null;	
	  	 
	  	  // DBUtility transmgtUtil = null;
	  	  
	  		 try{
	  dbUtil = new DBUtility();
	  //to prevent session swapping
							
	  							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
	  							   dbUtil.createStatement();
	  						       String seqNumber = dbUtil.executeQry(SQL1);
	  						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
	  						  	   transId = pymntTxnid;
	  						  	   String SQL3 = CommonSQL.spActQRY1_2;
	  						  	 dbUtil.createPreparedStatement(SQL3);
	  						  	   String spActBal = dbUtil.executeQry(actdtl);
	  						  	   logger.debug("the amount balance is.."+spActBal);
	  						  	  
	  						  	   double spActBald = Double.parseDouble(spActBal);
	  						  	   double amtd = Double.parseDouble(amt);
	  						  	  
	  						  	   double resultSpBald = spActBald+ amtd;
	  						  	   logger.debug("the result amount balance is.."+resultSpBald);
	  						  	   DecimalFormat format  = new DecimalFormat("##.##");
	  						  	  String resultSpBal  =	   format.format(resultSpBald);
	  						  	 
	  						  	   
	  						  	   String[] txnDetails = new String[9];
	  						  	   txnDetails[0] = pymntTxnid;
	  						  	   txnDetails[1] = amt;
	  						  	   txnDetails[2] = mode;
	  						  	   txnDetails[3] = purp;
	  						  	   txnDetails[4] = usid;
	  						  	   txnDetails[5] = parntKey;
	  						  	   txnDetails[6] = eform.getParentRefId();
	  						  	   txnDetails[7] = eform.getParentOffId();
	  						  	   txnDetails[8] = eform.getParentDistId();
	  						  	   String[] txnmapDetails = new String[3];
	  						  	   txnmapDetails [0] = pymntTxnid;
	  						  	String tid ="SPCREDITONLINE";
	  						  	   txnmapDetails [1] = tid;
	  						  	   txnmapDetails [2] = usid;
	  						  	   String[] resStatus = new String[2];
	  						  	  // resStatus [0] = resId;
	  						  	   if (tid != null && tid!=""){
	  						  	   resStatus [1] = tid;}
	  						  	   /*String[] parentDetails = new String[5];
	  						  	   parentDetails [0] = parentTbl;
	  						  	   parentDetails [1] = pymntTxnid;
	  						  	   parentDetails [2] = amt;
	  						  	   parentDetails [3] = mode;
	  						  	   parentDetails [4] = parntKey;*/
	  						  	   String[] parentDetails = new String[6];
	  						  	   parentDetails [0] = mode;
	  						  	   parentDetails [1] = pymntTxnid ;
	  						  	   parentDetails [2] = amt;
	  						  	   parentDetails [3] = resultSpBal;
	  						  	   parentDetails [4] = amt;
	  						  	   parentDetails [5] = parntKey ;
	  						  	   String[] parentFlag = new String[1];
	  						  	   parentFlag [0] = parntKey; 
	  						  	/* dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry2);
	  						  	   transactionflag=dbUtil.executeUpdate(resStatus);*/
	  						  	  String licCheckSQL = "SELECT b.license_number from "+ parentTbl +" a,igrs_sp_detls b "+
									" where a.created_by=b.created_by and a."+parentColmName+" = '"+parntKey+"' "+
									" and b.status='A' and APPLCTN_STATUS=8 and license_number is not null";
						
							  dbUtil.createStatement();
							       String licenseCheck = dbUtil.executeQry(licCheckSQL);
						if (!licenseCheck.equalsIgnoreCase(sessionLic)){
							 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD
						  	
						  			 	transId=pymntTxnid;
									   String[] spTableD = new String[8];
									   spTableD [0] = licNo; 
									   spTableD [1] = mode; 
									   spTableD [2] = amt; 
									   spTableD [3] = resultSpBal; 
									   spTableD [4] = amt;
									   spTableD [5] = "0"; 
									   spTableD [6] = purp; 
									   spTableD [7] = pymntTxnid; 
									//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
										//dbUtil.executeUpdate(spTable);
									 dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_CREDIT);
									transactionflag= dbUtil.executeUpdate(spTableD);
									if(transactionflag){
										dbUtil.commit();
										}
						throw new Exception("10025");
						}
						
	  						  	    	dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
	  						  	      transactionflag=dbUtil.executeUpdate(txnDetails);
	  						  	        if(transactionflag){
	  						  	        	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
	  						  	           transactionflag= dbUtil.executeUpdate(txnmapDetails); 
	  						 
	  						  	       if(transactionflag)
								  	   {
								  		 dbUtil.createPreparedStatement(CommonSQL.UPDATE_ONLINE);
								  		 String ar[] = new String[7];
								  		 ar[0] = eform.getChallanNumber();
								  		 ar[1] = eform.getCin();
								  		 ar[2] = eform.getBrn();
								  		 ar[3] = eform.getScrollNumber();
								  		 ar[4] = eform.getScrollDate();
								  		 ar[5] = eform.getTrasactionDate();
								  		 ar[6] = eform.getCrnNumber();
								  		 transactionflag= dbUtil.executeUpdate(ar); 
								  	   }
	  						  	           
	  						  	             if(transactionflag){
	  						  	            	dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY_1);
	  						  	                 transactionflag= dbUtil.executeUpdate(parentDetails);
	  						  	  
	  						  	                 if(transactionflag){
	  						  	                	dbUtil.commit();
	  						  	             
	  						  	                                    }
	  						  	                 }
	  						  	             }
	  	    
	  	    eform.setTransId(transId);
	  		 }
	  		 catch (Exception e) {
	  			String msg = e.getMessage();
		    	if (msg.equalsIgnoreCase("10025")){
		    		throw e;
		    	}
				logger.debug(e.getMessage(),e);
			}
	  		finally {
				try {
					if(dbUtil!=null)
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.error("Exception in closing connection" + ex);
					
				}
	  		}
	  	    return transactionflag;   
	  	  }
	  
	  /******************************************************************  
		  *   Method Name  :   PaymentOnlineTransactionFinalSP2()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
	  
	 //changes on 27 dec 2015 
	  public  synchronized boolean PaymentOnlineTransactionFinalSP2 (PaymentForm eform,HttpSession session)throws NullPointerException,
SQLException,Exception{
	   boolean transactionflag = false; 
	   
	   DBUtility dbUtil = null;
	   String arr[]=new String[1];
		 String sessionLic="";
		 
		  if(session.getAttribute("licNo")!=null)
		  sessionLic=(String) session.getAttribute("licNo");
	   
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = eform.getPayMode(); 
	   String purp = eform.getFuncId();
	   String usid = eform.getLoggedUser();
	   String amt = eform.getOnlineEntrAmt();
	   String parntKey = eform.getParentKey();
	   String parentTbl = eform.getParentTable();
	   String parentColmName = eform.getParentColumnName();
	 //  String licNo = eform.getSpLicenceNo();
	   String licNo =sessionLic;
	   String moduleCnstnt = "PTID";
	   String resIds = eform.getOnlineCinNo();
	   String resId = resIds.toLowerCase();
	   arr[0]=resId;
	   String transId = "";
	   String[] actdtl = new String[2];
	   actdtl [0] = licNo;
	   actdtl [1] = licNo;
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   
	  		
	  // DBUtility transmgtUtil = null;
	   try{  	
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   dbUtil.createPreparedStatement(CommonSQL.ONLINE_SEARCH_QRY);
	   recordList = dbUtil.executeQuery(arr);
		 
 if(recordList != null && recordList.size()>0){
	  for(int j= 0; j<recordList.size(); j++){
		  comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String tid =(String) compSub.get(0);
						  //arr2[0]=tid;
						  String cin =(String) compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(!amt.equals(am)&& am !=null && am!=""){
							  throw new Exception("40001");
						  }
						  if(!purp.equals(fn)&& fn !=null && fn!=""){
							  throw new Exception("40002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st !=null && st!=""){
							  throw new Exception("40003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String SQL3 = CommonSQL.spActQRY1;
						  	 dbUtil.createPreparedStatement(SQL3);
						  	   String spActBal = dbUtil.executeQry(actdtl);
						  	   logger.debug("the amount balance is.."+spActBal);
						  	   if(spActBal == ""){
						  		 spActBal = "0";
						  	   }
						  	   double spActBald = Double.parseDouble(spActBal);
						  	   double amtd = Double.parseDouble(amt);
						  	   double resultSpBald = spActBald+ amtd;
						  	   logger.debug("the result amount balance is.."+resultSpBald);
						  	   String resultSpBal = Double.toString(resultSpBald);
						  	   
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = eform.getParentRefId();
						  	   txnDetails[7] = eform.getParentOffId();
						  	   txnDetails[8] = eform.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (tid != null && tid!=""){
						  	   txnmapDetails [1] = tid;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (tid != null && tid!=""){
						  	   resStatus [1] = tid;}
						  	   /*String[] parentDetails = new String[5];
						  	   parentDetails [0] = parentTbl;
						  	   parentDetails [1] = pymntTxnid;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = mode;
						  	   parentDetails [4] = parntKey;*/
						  	   String[] parentDetails = new String[6];
						  	   parentDetails [0] = mode;
						  	   parentDetails [1] = pymntTxnid ;
						  	   parentDetails [2] = amt;
						  	   //Changes on 27 dec 2015
						  	   
						  	 if(resultSpBal==null){
						  		 dbUtil.createPreparedStatement(SQL3);
						  		spActBal = dbUtil.executeQry(actdtl);
						  		 spActBald = Double.parseDouble(spActBal);
							  	    amtd = Double.parseDouble(amt);
							  	    resultSpBald = spActBald+ amtd;
							  	  resultSpBal = Double.toString(resultSpBald);
						  		 	   }
						  	   
						  	   parentDetails [3] = resultSpBal;
						  	   parentDetails [4] = amt;
						  	   parentDetails [5] = parntKey ;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey; 
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry2);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	     if(transactionflag){
						  	    	dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	      transactionflag=dbUtil.executeUpdate(txnDetails);
						  	        if(transactionflag){
						  	        	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	           transactionflag= dbUtil.executeUpdate(txnmapDetails); 
						  	  /* String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY= 'PAYMENTS', UPDATE_DATE= SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	   transmgtUtil.createStatement();
						  	   transmgtUtil.executeUpdate(SQL2);*/
						  	             if(transactionflag){
						  	            	dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY);
						  	                 transactionflag= dbUtil.executeUpdate(parentDetails);
						  	  
						  	                 if(transactionflag){
						  	                	dbUtil.commit();
						  	                                    }
						  	                 }
						  	             }
						  	        }
						  }  
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("40000"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("40000")||msg.equalsIgnoreCase("40001")||msg.equalsIgnoreCase("40002")||msg.equalsIgnoreCase("40003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    eform.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  //changes on 27 dec 2015
	  //online credit limit other services by shreeraj
	  public  synchronized boolean PaymentOnlineTransactionFinalSP2M (PaymentForm eform,HttpSession session)throws NullPointerException,
	  SQLException,Exception{
	  	   boolean transactionflag = false; 
	  	   
	  	 String sessionLic="";
		 
		  if(session.getAttribute("licNo")!=null)
		  sessionLic=(String) session.getAttribute("licNo");
	  	   String arr[]=new String[1];
	  	  
	  	   
	  	   ArrayList recordList = new ArrayList();
	  	   ArrayList comp = new ArrayList();
	  	   String mode = eform.getPayMode(); 
	  	   String purp = eform.getFuncId();
	  	   String usid = eform.getLoggedUser();
	  	   String amt = eform.getOnlineEntrAmt();
	  	   String parntKey = eform.getParentKey();
	  	   String parentTbl = eform.getParentTable();
	  	   String parentColmName = eform.getParentColumnName();
	  	//   String licNo = eform.getSpLicenceNo();
	  	   String licNo = sessionLic;
	  	   String moduleCnstnt = "PTID";
	  	   String resIds = eform.getOnlineCinNo();
	  	 //  String resId = resIds.toLowerCase();
	  	 //  arr[0]=resId;
	  	   String transId = "";
	  	   String[] actdtl = new String[2];
	  	   actdtl [0] = licNo;
	  	   actdtl [1] = licNo;
	  	   Date date = new Date();
	  	   Format yearformat = new SimpleDateFormat("yyyy");
	  	   Format monthformat = new SimpleDateFormat("MM");
	  	   Format dateformat = new SimpleDateFormat("dd");
	  	   String dfmt = dateformat.format(date);
	  	   String yfmt = yearformat.format(date);
	  	   String mfmt = monthformat.format(date);
	  	   ArrayList swapList=null;
	  	 DBUtility dbUtil = null;	
	  	  // DBUtility transmgtUtil = null;
	  	 
	  		 try
	  		 {
	   dbUtil = new DBUtility();
	   
					  
					/*	  dbUtil.createStatement();
						       String licenseCheck = dbUtil.executeQry(licNo);
					if (!licenseCheck.equalsIgnoreCase(sessionLic)){
					
					throw new Exception("10025");
					}*/
	  							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
	  							   dbUtil.createStatement();
	  						       String seqNumber = dbUtil.executeQry(SQL1);
	  						  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
	  						  	   transId = pymntTxnid;
	  						  	   String SQL3 = CommonSQL.spActQRY1;
	  						  	 dbUtil.createPreparedStatement(SQL3);
	  						  	   String spActBal = dbUtil.executeQry(actdtl);
	  						  	   logger.debug("the amount balance is.."+spActBal);
	  						  	   if(spActBal == ""){
	  						  		 spActBal = "0";
	  						  	   }
	  						  	   double spActBald = Double.parseDouble(spActBal);
	  						  	   double amtd = Double.parseDouble(amt);
	  						  	   double resultSpBald = spActBald+ amtd;
	  						  	   logger.debug("the result amount balance is.."+resultSpBald);
	  						  	   DecimalFormat format = new DecimalFormat("##.##");
	  						  	String resultSpBal  = format.format(resultSpBald);
	  						  	   
	  						  	   
	  						  	   String[] txnDetails = new String[9];
	  						  	   txnDetails[0] = pymntTxnid;
	  						  	   txnDetails[1] = amt;
	  						  	   txnDetails[2] = mode;
	  						  	   txnDetails[3] = purp;
	  						  	   txnDetails[4] = usid;
	  						  	   txnDetails[5] = parntKey;
	  						  	   txnDetails[6] = eform.getParentRefId();
	  						  	   txnDetails[7] = eform.getParentOffId();
	  						  	   txnDetails[8] = eform.getParentDistId();
	  						  	   String[] txnmapDetails = new String[3];
	  						  	   txnmapDetails [0] = pymntTxnid;
	  						  	String    tid="SPOTHERCREDIT";
	  						  	   if (tid != null && tid!=""){
	  						  	   txnmapDetails [1] = tid;}
	  						  	   txnmapDetails [2] = usid;
	  						  	 //  String[] resStatus = new String[2];
	  						  	//   resStatus [0] = resId;
	  						  	  /* if (tid != null && tid!=""){
	  						  	   resStatus [1] = tid;}*/
	  						  	   /*String[] parentDetails = new String[5];
	  						  	   parentDetails [0] = parentTbl;
	  						  	   parentDetails [1] = pymntTxnid;
	  						  	   parentDetails [2] = amt;
	  						  	   parentDetails [3] = mode;
	  						  	   parentDetails [4] = parntKey;*/
	  						  	   String[] parentDetails = new String[6];
	  						  	   parentDetails [0] = mode;
	  						  	   parentDetails [1] = pymntTxnid ;
	  						  	   parentDetails [2] = amt;
	  						  	   parentDetails [3] = resultSpBal;
	  						  	   parentDetails [4] = amt;
	  						  	   parentDetails [5] = parntKey ;
	  						  	   String[] parentFlag = new String[1];
	  						  	   parentFlag [0] = parntKey; 
	  						  	 
	  						  	 //to prevent session swapping
	  							  String licCheckSQL = "SELECT b.license_number from "+ parentTbl +" a,igrs_sp_detls b "+
	  									" where a.created_by=b.created_by and a."+parentColmName+" = '"+parntKey+"' "+
	  									" and b.status='A' and APPLCTN_STATUS=8 and license_number is not null";
	  						
	  							  dbUtil.createStatement();
							       String licenseCheck = dbUtil.executeQry(licCheckSQL);
						if (!licenseCheck.equalsIgnoreCase(sessionLic)){
							 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD
						  	
						  			 	transId=pymntTxnid;
									   String[] spTableD = new String[8];
									   spTableD [0] = licNo; 
									   spTableD [1] = mode; 
									   spTableD [2] = amt; 
									   spTableD [3] = resultSpBal; 
									   spTableD [4] = amt;
									   spTableD [5] = "0"; 
									   spTableD [6] = purp; 
									   spTableD [7] = pymntTxnid; 
									//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
										//dbUtil.executeUpdate(spTable);
									 dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_CREDIT);
									transactionflag= dbUtil.executeUpdate(spTableD);
									if(transactionflag){
										dbUtil.commit();
										}
						throw new Exception("10025");
						}
	  						  	    	dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
	  						  	      transactionflag=dbUtil.executeUpdate(txnDetails);
	  						  	        if(transactionflag){
	  						  	        	dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
	  						  	           transactionflag= dbUtil.executeUpdate(txnmapDetails); 
	  						
	  						  	       if(transactionflag)
								  	   {
								  		 dbUtil.createPreparedStatement(CommonSQL.UPDATE_ONLINE);
								  		 String ar[] = new String[7];
								  		 ar[0] = eform.getChallanNumber();
								  		 ar[1] = eform.getCin();
								  		 ar[2] = eform.getBrn();
								  		 ar[3] = eform.getScrollNumber();
								  		 ar[4] = eform.getScrollDate();
								  		 ar[5] = eform.getTrasactionDate();
								  		 ar[6] = eform.getCrnNumber();
								  		 transactionflag= dbUtil.executeUpdate(ar); 
								  	   
	  						  	       
	  						  	             if(transactionflag){
	  						  	            	dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY);
	  						  	                 transactionflag= dbUtil.executeUpdate(parentDetails);
	  						  	  
	  						  	                 if(transactionflag){
	  						  	                	dbUtil.commit();
	  						  	             /*   Statement st=null;
	  									  		 swapList=getSwapRecordList(parentTbl,licNo,purp,dbUtil.getDBConnection());
	  									  	 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD
	  									  		 if(swapList.size()>0){
	  									  			 	transId=pymntTxnid;
	  												   String[] spTableD = new String[8];
	  												   spTableD [0] = licNo; 
	  												   spTableD [1] = mode; 
	  												   spTableD [2] = amt; 
	  												   spTableD [3] = resultSpBal; 
	  												   spTableD [4] = amt;
	  												   spTableD [5] = "0"; 
	  												   spTableD [6] = purp; 
	  												   spTableD [7] = pymntTxnid; 
	  												//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
	  													//dbUtil.executeUpdate(spTable);
	  												 dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_CREDIT);
	  												transactionflag= dbUtil.executeUpdate(spTableD);
	  									  		 }
	  												  for(int i=0;i<swapList.size();i++){
	  													  ArrayList subSwapList=(ArrayList)swapList.get(i);
	  													  String srno=subSwapList.get(0).toString();
	  													  String payTxnID=subSwapList.get(1).toString();
	  													  String paidAmount=subSwapList.get(2).toString();
	  													  String licenseNo=subSwapList.get(3).toString();
	  													  
	  													 boolean flag= deleteSwapData(parentTbl,licNo,purp,dbUtil.getDBConnection(),srno,
	  															  payTxnID,paidAmount,licenseNo,parentColmName,parntKey);
	  												  }*/
	  						  	                                    }
	  						  	                 else
	  						  	                 {
	  						  	                	 dbUtil.rollback();
	  						  	                 }
	  						  	             }
								  	   }
	  						  	        }
	  		 }
	  		 catch (Exception e) {
	  			String msg = e.getMessage();
		    	if (msg.equalsIgnoreCase("10025")){
		    		throw e;
		    	}
				if(dbUtil!=null)
					dbUtil.closeConnection();
			}
	  	    eform.setTransId(transId);
	  	    return transactionflag;   
	  						  	             
	  	  }
	  
	  
	  /******************************************************************  
		  *   Method Name  :   PaymentChallanAvailTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
   *******************************************************************/  
	  
	  //changes on 27 dec 2015
	  
	  public  synchronized boolean PaymentChallanAvailTransactionFinal (PaymentForm _form,HttpSession session)throws NullPointerException,
   SQLException,Exception{
	   boolean transactionflag = false; 
	   String sessionLic="";
	   
	   if(session.getAttribute("licNo")!=null)
			  sessionLic=(String) session.getAttribute("licNo");



	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = _form.getPayMode(); 
	   String purp = _form.getFuncId();
	   String usid = _form.getLoggedUser();
	   String amt = _form.getChallanentrAmt();
	   String parntKey = _form.getParentKey();
	   String parentTbl = _form.getParentTable();
	   //String bankid = _form.getChallanBankId();
	   String bankid = _form.getChallanRefNo();
	   String parentColmName = _form.getParentColumnName();
	  // String licNo = _form.getSpLicenceNo();
	   String licNo =sessionLic;
	   String moduleCnstnt = "PTID";
	   //String resIds = _form.getReceiptID();
	   String resIds = _form.getChallanNO();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   String[] actdtl = new String[2];
  	   actdtl [0] = licNo;
  	   actdtl [1] = licNo;
	   
  	 DBUtility dbUtil = null;	
	   //DBUtility transmgtUtil = null;
	   try{  	
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	   String challanSql =  "select CHALLAN_SERIAL_NUMBER,CIN,PAID_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where EPNR_NO ='" + bankid + "'";
	   dbUtil.createStatement();  
	   recordList = dbUtil.executeQuery(challanSql);
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String txnNo =(String) compSub.get(0);
						  String cin=  (String)compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(cin!=null&& cin!=""){
							  if(!resId.equals(cin.toLowerCase())&& cin !=null && cin!=""){
								  throw new Exception("20000");
							  }
							  }else{throw new Exception("20005");
								  
							  }
						  if(!amt.equals(am)&& am!= null && am!=""){
							  throw new Exception("20001");
						  }
						  if(!purp.equals(fn)&& fn!= null && fn!=""){
							  throw new Exception("20002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
							  throw new Exception("20003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						       String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String SQL3 = CommonSQL.spActQRY1;
						  	 dbUtil.createPreparedStatement(SQL3);
						  	   String spActBal = dbUtil.executeQry(actdtl);
						  	   logger.debug("the amount balance is.."+spActBal);
						  	   if (spActBal == ""){
						  		 spActBal = "0";
						  	   }
						  	   double spActBald = Double.parseDouble(spActBal);
						  	   double amtd = Double.parseDouble(amt);
						  	   double resultSpBald = spActBald+ amtd;
						  	   logger.debug("the result amount balance is.."+resultSpBald);
						  	   String resultSpBal = Double.toString(resultSpBald);
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = _form.getParentRefId();
						  	   txnDetails[7] = _form.getParentOffId();
						  	   txnDetails[8] = _form.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (txnNo != null && txnNo!=""){
						  	   txnmapDetails [1] = txnNo;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (txnNo != null && txnNo!=""){
						  	   resStatus [1] = txnNo;}
						  	   String[] parentDetails = new String[6];
						  	   parentDetails [0] = mode;
						  	   parentDetails [1] = pymntTxnid ;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = resultSpBal;
						  	   parentDetails [4] = amt;
						  	   parentDetails [5] = parntKey ;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey;
						  	   
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry1);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	   transactionflag= dbUtil.executeUpdate(txnDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag=dbUtil.executeUpdate(txnmapDetails); 
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY);
						  	   transactionflag=dbUtil.executeUpdate(parentDetails);
						  	  /* String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE= '"+mode+"' ,  PAYMENT_FLAG = 'P' WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	   transmgtUtil.createStatement();
						  	   transmgtUtil.executeUpdate(SQL2);*/
						  	  if(transactionflag){
						  		dbUtil.commit();
						  	  }
						  	   }
						  	   }
						  }  }
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("20004"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("20000")||msg.equalsIgnoreCase("20001")||msg.equalsIgnoreCase("20002")||msg.equalsIgnoreCase("20003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    _form.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  
	  /******************************************************************  
		  *   Method Name  :   PaymentChallanAvailTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
	  //chnages on 27 dec 2015
	  
	  public  synchronized boolean PaymentChallanAvailTransactionFinal2 (PaymentForm _form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	   boolean transactionflag = false; 
	   String sessionLic="";
	   if(session.getAttribute("licNo")!=null)
			  sessionLic=(String) session.getAttribute("licNo");

	   
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = _form.getPayMode(); 
	   String purp = _form.getFuncId();
	   String usid = _form.getLoggedUser();
	   String amt = _form.getChallanentrAmt();
	   String parntKey = _form.getParentKey();
	   String parentTbl = _form.getParentTable();
	   //String bankid = _form.getChallanBankId();
	   String bankid = _form.getChallanRefNo();
	   String parentColmName = _form.getParentColumnName();
	  // String licNo = _form.getSpLicenceNo();
	   String licNo= sessionLic;
	   String moduleCnstnt = "PTID";
	   //String resIds = _form.getReceiptID();
	   String resIds = _form.getChallanNO();
	   String resId = resIds.toLowerCase();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   String[] actdtl = new String[2];
	   actdtl [0] = licNo;
	   actdtl [1] = licNo;
	   
	   DBUtility dbUtil = null;
	   //DBUtility transmgtUtil = null;
	   try{  	
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	   String challanSql =  "select CHALLAN_SERIAL_NUMBER,CIN,PAID_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where EPNR_NO ='" + bankid + "'";
	   dbUtil.createStatement();  
	   recordList = dbUtil.executeQuery(challanSql);
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String txnNo =(String) compSub.get(0);
						  String cin=  (String)compSub.get(1);
						  String am = (String) compSub.get(2);
						  String fn = (String) compSub.get(3);
						  String st = (String) compSub.get(4);
						 
						  if(cin!=null&& cin!=""){
							  if(!resId.equals(cin.toLowerCase())&& cin !=null && cin!=""){
								  throw new Exception("20000");
							  }
							  }else{throw new Exception("20005");
								  
							  }
						  if(!amt.equals(am)&& am!= null && am!=""){
							  throw new Exception("20001");
						  }
						  if(!purp.equals(fn)&& fn!= null && fn!=""){
							  throw new Exception("20002");
						  }
						  if(!st.equalsIgnoreCase("A")&& st!= null && st!=""){
							  throw new Exception("20003");
						  }else{
							   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
							   dbUtil.createStatement();
						       String seqNumber = dbUtil.executeQry(SQL1);
						       String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
						  	   transId = pymntTxnid;
						  	   String SQL3 = CommonSQL.spActQRY1_2;
						  	 dbUtil.createPreparedStatement(SQL3);
						  	   String spActBal = dbUtil.executeQry(actdtl);
						  	   logger.debug("the amount balance is.."+spActBal);
						  	   if (spActBal == ""){
						  		 spActBal = "0";
						  	   }
						  	   double spActBald = Double.parseDouble(spActBal);
						  	   double amtd = Double.parseDouble(amt);
						  	   double resultSpBald = spActBald+ amtd;
						  	   logger.debug("the result amount balance is.."+resultSpBald);
						  	   String resultSpBal = Double.toString(resultSpBald);
						  	   String[] txnDetails = new String[9];
						  	   txnDetails[0] = pymntTxnid;
						  	   txnDetails[1] = amt;
						  	   txnDetails[2] = mode;
						  	   txnDetails[3] = purp;
						  	   txnDetails[4] = usid;
						  	   txnDetails[5] = parntKey;
						  	   txnDetails[6] = _form.getParentRefId();
						  	   txnDetails[7] = _form.getParentOffId();
						  	   txnDetails[8] = _form.getParentDistId();
						  	   String[] txnmapDetails = new String[3];
						  	   txnmapDetails [0] = pymntTxnid;
						  	   if (txnNo != null && txnNo!=""){
						  	   txnmapDetails [1] = txnNo;}
						  	   txnmapDetails [2] = usid;
						  	   String[] resStatus = new String[2];
						  	   resStatus [0] = resId;
						  	   if (txnNo != null && txnNo!=""){
						  	   resStatus [1] = txnNo;}
						  	   String[] parentDetails = new String[6];
						  	   parentDetails [0] = mode;
						  	   parentDetails [1] = pymntTxnid ;
						  	   parentDetails [2] = amt;
						  	   parentDetails [3] = resultSpBal;
						  	   parentDetails [4] = amt;
						  	   parentDetails [5] = parntKey ;
						  	   String[] parentFlag = new String[1];
						  	   parentFlag [0] = parntKey;
						  	   
						  	 dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry1);
						  	   transactionflag=dbUtil.executeUpdate(resStatus);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
						  	   transactionflag=dbUtil.executeUpdate(txnDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.paymentTxnMapQry);
						  	   transactionflag=dbUtil.executeUpdate(txnmapDetails);
						  	   if(transactionflag){
						  		 dbUtil.createPreparedStatement(CommonSQL.spParentUpdateQRY_1);
						  	   transactionflag=dbUtil.executeUpdate(parentDetails);
						  	  /* String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
						  	   				"' , PAYMENT_MODE= '"+mode+"' ,  PAYMENT_FLAG = 'P' WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
						  	   logger.debug(SQL2);
						  	   transmgtUtil.createStatement();
						  	   transmgtUtil.executeUpdate(SQL2);*/
						  	   if(transactionflag){
						  		 dbUtil.commit();
						  	   }
						  	   }
						  	   }
						  	   }
						  }  
					  }  
				  }   
		   }
	       
	  	    
	     }else{
	    	 throw new Exception("20004"); //Throwing an Exception
	     }
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("20000")||msg.equalsIgnoreCase("20001")||msg.equalsIgnoreCase("20002")||msg.equalsIgnoreCase("20003")){
	    		throw e;
	    	}else{
	    		dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    _form.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  /******************************************************************  
		  *   Method Name  :   PaymentCreditTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		  *   Modified		: Shreeraj Khare
   *******************************************************************/  
	  
	  
	  public  synchronized boolean PaymentCreditTransactionFinal (PaymentForm _form,HttpSession session)throws NullPointerException,
   SQLException,Exception{
		 
		  String sessionLic="";
		  String userID="";
		  if(session.getAttribute("licNo")!=null)
		  sessionLic=(String) session.getAttribute("licNo");
		  
		  if(session.getAttribute("UserId")!=null)
			  userID = (String)session.getAttribute("UserId");
		  
	   boolean transactionflag = false; 
	   ArrayList recordList = new ArrayList();
	   ArrayList comp = new ArrayList();
	   String mode = _form.getPayMode(); 
	   String purp = _form.getFuncId();
	   String usid = userID;
	   String amt = _form.getCrEntrAmt();
	   String parntKey = _form.getParentKey();
	   String parentTbl = _form.getParentTable();
	   String parentColmName = _form.getParentColumnName();
	   String spamt = _form.getSpAmt();//sp commission amount
	   double spamt1 = _form.getSpamtd();
	   String cmsn = _form.getSpComsn();
	   String licNo = sessionLic;
	   String resultAmt = "";
	   double resultAmtd= 0.0;
	   String moduleCnstnt = "PTID";
	   //String resIds = _form.getReceiptID();
	   String resId = _form.getChallanNO();
	   String transId = "";
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   String[] spdtl = new String[3];
	   spdtl[0]= usid;
	   spdtl[1]= licNo;
	   spdtl[2]= licNo;
	   String timeTaken="";
	   String confgParam="";
	   String ptid="";
	   ArrayList swapList=null;
	   DBUtility dbUtil = null;
	   PreparedStatement pst=null;
  	   Statement st=null;
  	   Connection conn=null;
	  // DBUtility transmgtUtil = null;
	   try{  
		   
		  dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
		   conn=dbUtil.getDBConnection(); 
		   

		   String challanSql ="";
	  
	   
	  
	 
	   
	   if(purp.equalsIgnoreCase("FUN_022")|| purp.equalsIgnoreCase("FUN_023")){
		 
		  // dbUtil.createPreparedStatement(CommonSQL.SPActQryE);
		   //recordList = dbUtil.executeQuery(spdtl);
		   pst=conn.prepareStatement(CommonSQL.SPActQryE);
		   recordList =executeQuery(spdtl, pst);
		  
	   }else{
		//   dbUtil.createPreparedStatement(CommonSQL.SPActQryO);		 
		   //recordList = dbUtil.executeQuery(spdtl);
		   pst=conn.prepareStatement(CommonSQL.SPActQryO);
		   recordList =executeQuery(spdtl, pst);
	   }
	   
	   if(recordList != null && recordList.size()>0){
		   for(int j= 0; j<recordList.size(); j++){
			   comp.add((ArrayList)recordList.get(j));
				  if(comp != null && comp.size()>0){
					  for (int k=0; k< comp.size(); k++){
						  ArrayList compSub = (ArrayList)comp.get(k);
						  String am = (String) compSub.get(1);//sp account balance
						  _form.setSpActBal(am);
						  _form.getPaymentDTO().setSpAmt(am);
						  double amd = Double.parseDouble(am);
						  double entrAmtd = Double.parseDouble(amt);//amount entered by sp
						  //7th dec bypass 
						  boolean paymentFlag=false;
						  ArrayList list=new ArrayList();
							//dbUtil.createStatement();
							//   transactionflag = dbUtil.executeUpdate();
						//	ArrayList list=dbUtil.executeQuery(CommonSQL.GET_LAST_PAYMENT_DATE_BYPASS);
							//Added by Shreeraj
							   if(purp.equalsIgnoreCase("FUN_022")|| purp.equalsIgnoreCase("FUN_023")){
								  // dbUtil.createPreparedStatement(CommonSQL.GET_DATE_E);
								   pst=conn.prepareStatement(CommonSQL.GET_DATE_E);
								   list=executeQuery(new String[]{licNo,purp,amt,licNo,licNo,purp,amt}, pst);
								  // list= dbUtil.executeQuery(new String[]{licNo,purp,amt,licNo,licNo,purp,amt});
							   }else{
								  // dbUtil.createPreparedStatement(CommonSQL.GET_DATE_O);  
								   pst=conn.prepareStatement(CommonSQL.GET_DATE_O);
								 //  list= dbUtil.executeQuery(new String[]{licNo,purp,amt,licNo,licNo,purp,amt});
								   list=executeQuery(new String[]{licNo,purp,amt,licNo,licNo,purp,amt}, pst);
							   }
							   if(list!=null)
							   {
								   
								   ArrayList subList=(ArrayList)list.get(0);
								   if(subList.get(0)!=null && subList.get(2)!=null ){
								   timeTaken=subList.get(0).toString();
								   confgParam=subList.get(1).toString();
								   ptid=subList.get(2).toString();
								   int splitsize=0;
								   String time=timeTaken.substring(0, timeTaken.indexOf(" ")).trim();
								   /*if(timeTaken.length()<=10)
									   splitsize=2;
								   else
									   splitsize=4;*/
								  // String time=timeTaken.substring(0, splitsize).trim();
								   if(Integer.parseInt(time)>Integer.parseInt(confgParam))
									   paymentFlag=true;
								   else
									   paymentFlag=false; 
								 /*  ArrayList paymentList=new ArrayList();
								   if(purp.equalsIgnoreCase("FUN_022")|| purp.equalsIgnoreCase("FUN_023")){
									   dbUtil.createPreparedStatement(CommonSQL.GET_LAST_PAYMENT_DETAILS_BYPASS_E);
									   paymentList = dbUtil.executeQuery(new String[]{licNo,licNo,toTime,froTime,purp,amt});
									   }else{
										   dbUtil.createPreparedStatement(CommonSQL.GET_LAST_PAYMENT_DETAILS_BYPASS_O);
										    paymentList = dbUtil.executeQuery(new String[]{licNo,licNo,toTime,froTime,purp,amt});
									   }
								  
								   if(paymentList.size()>0){
									   paymentFlag=false;
								   }else{
									   paymentFlag=true; 
								   }
								   }*/
								   }else{
									   paymentFlag=true; 
								   }
								   
							   }						   
							    double payableAmt=Double.parseDouble(getPayableAmount(_form));
			        		    
			        		    if(payableAmt!=entrAmtd){
			        		    	 throw new Exception("10002");
			        		    	
			        		    }
							   if(paymentFlag){
							   if(am!=null && am!=""){
						  if(entrAmtd > amd){
							  throw new Exception("10001");
						  }
						  else {
							  resultAmtd = amd-spamt1;
							  if (resultAmtd <0){
								  throw new Exception("10001");
							  }
							  //
							  else {
								   resultAmt = Double.toString(resultAmtd);
								   String SQL1 = "Select IGRS_PAYMENT_TXNID.nextval from dual";
								  // dbUtil.createStatement();
							       //String seqNumber = dbUtil.executeQry(SQL1);
								   st=conn.createStatement();
								   String seqNumber=executeQry(SQL1, st);
							  	   String pymntTxnid = moduleCnstnt+dfmt+mfmt+yfmt+seqNumber;
							  	   transId = pymntTxnid;
							  	   String[] txnDetails = new String[9];
							  	   txnDetails[0] = pymntTxnid;
							  	   txnDetails[1] = amt;
							  	   txnDetails[2] = mode;
							  	   txnDetails[3] = purp;
							  	   txnDetails[4] = usid;
							  	   txnDetails[5] = parntKey;
							  	   txnDetails[6] = _form.getParentRefId();
							  	   txnDetails[7] = _form.getParentOffId();
							  	   txnDetails[8] = _form.getParentDistId();
							  	   String[] txnmapDetails = new String[3];
							  	   txnmapDetails [0] = pymntTxnid;
							  	   txnmapDetails [1] = licNo;
							  	   txnmapDetails [2] = usid;
							  	   String[] parentDetails = new String[5];
							  	   parentDetails [0] = parentTbl;
							  	   parentDetails [1] = pymntTxnid;
							  	   parentDetails [2] = amt;
							  	   parentDetails [3] = mode;
							  	   parentDetails [4] = parntKey;
							  	   String[] spTable = new String[8];
							  	   spTable [0] = licNo; 
							  	   spTable [1] = mode; 
							  	   spTable [2] = pymntTxnid; 
							  	  spTable [3] = amt; 
							  	   spTable [4] = resultAmt; 
							  	   spTable [5] = spamt;
							  	   spTable [6] = cmsn; 
							  	   spTable [7] = purp; 
							  	  
							  	 String licCheckSQL = "SELECT license_number from "+ parentTbl +" a,igrs_sp_detls b "+
					   				" where a.created_by=b.created_by and a."+parentColmName+" = '"+parntKey+"' "+
					   				" and b.status='A' and APPLCTN_STATUS=8 and license_number is not null";
					   
					   st=conn.createStatement();
					   String licenseCheck=executeQry(licCheckSQL, st);
					   if (!licenseCheck.equalsIgnoreCase(sessionLic)){
					  		 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD

							 	transId=ptid;
						   String[] spTableD = new String[8];
						   spTableD [0] = licNo; 
						   spTableD [1] = mode; 
						   spTableD [2] = amt; 
						   spTableD [3] = resultAmt; 
						   spTableD [4] = spamt;
						   spTableD [5] = cmsn; 
						   spTableD [6] = purp; 
						   spTableD [7] = ptid; 
						//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
							//dbUtil.executeUpdate(spTable);
							 pst=conn.prepareStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_DEBIT);
						  		transactionflag=executeUpdateDB(spTableD, pst);
							
							 if(transactionflag){
								// dbUtil.commit();
								 conn.commit();
							 }
							
							
					  
						 
							  throw new Exception("10025");
						  }
					   
							  	   try{
							  		
						   
							  	  if(purp.equalsIgnoreCase("FUN_022")|| purp.equalsIgnoreCase("FUN_023")){
							  		  
							  		  pst=conn.prepareStatement(CommonSQL.SPInsertQryE);
							  		  transactionflag=executeUpdateDB(spTable, pst);
							  	//	dbUtil.createPreparedStatement(CommonSQL.SPInsertQryE);
									 //  transactionflag = dbUtil.executeUpdate(spTable);
								   }else{
									  pst=conn.prepareStatement(CommonSQL.SPInsertQryO);
								  	  transactionflag=executeUpdateDB(spTable, pst);
									 //  dbUtil.createPreparedStatement(CommonSQL.SPInsertQryO);
									 //  transactionflag = dbUtil.executeUpdate(spTable);
								   }
							  	 
							  	   /*transmgtUtil.createPreparedStatement(CommonSQL.SPInsertQryO);
							  	   transactionflag=transmgtUtil.executeUpdate(spTable);*/
							  	   if(transactionflag){
							  		// dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
							  	 //  transactionflag=dbUtil.executeUpdate(txnDetails);
							  		 pst=conn.prepareStatement(CommonSQL.challanTxnInsert);
								  		transactionflag=executeUpdateDB(txnDetails, pst);
							  	   if(transactionflag){
							  		 pst=conn.prepareStatement(CommonSQL.paymentTxnMapQry);
							  		transactionflag=executeUpdateDB(txnmapDetails, pst); 
							  		   
							  	  if(transactionflag){
							  	   String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ pymntTxnid+"' , PAID_AMOUNT= '"+amt+
							  	   				"' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY='PAYMENTS', UPDATE_DATE=SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
							  	// dbUtil.createStatement();
							  	//   transactionflag=dbUtil.executeUpdate(SQL2);
							  	   st=conn.createStatement();
							  	 transactionflag=executeUpdateDBCreateStmt(SQL2, st);
							  	   if(transactionflag){
							  		 conn.commit();
							  		swapList=new ArrayList();
							  		 swapList=getSwapRecordList(parentTbl,licNo,purp,conn);
							  		 
										  for(int i=0;i<swapList.size();i++){
											  ArrayList subSwapList=(ArrayList)swapList.get(i);
											  String srno=subSwapList.get(0).toString();
											  String payTxnID=subSwapList.get(1).toString();
											  String paidAmount=subSwapList.get(2).toString();
											  String licenseNo=subSwapList.get(3).toString();
											  
											 boolean flag= deleteSwapData(parentTbl,licNo,purp,conn,srno,
													  payTxnID,paidAmount,licenseNo,parentColmName,parntKey);
										  }
										
							  	   }
							  	   }
							  	   } 
							  	   }
							  	   }catch(Exception e){
							  		   e.printStackTrace();
							  	   }
							  	 if(swapList.size()>0){
									 transactionflag = false; 
									  throw new Exception("10025");
									
								  }
							  	   }
							  
						  }
						  }	
					  }else{
						 // String payTxnID="";
						  /*if(purp.equalsIgnoreCase("FUN_022")|| purp.equalsIgnoreCase("FUN_023")){
						  		dbUtil.createPreparedStatement(CommonSQL.GET_PAYTXN_ID_E);
						  		transId = dbUtil.executeQry(new String[]{licNo,toTime,froTime,purp,amt});
							   }else{
								   dbUtil.createPreparedStatement(CommonSQL.GET_PAYTXN_ID_O);
								   transId = dbUtil.executeQry(new String[]{licNo,toTime,froTime,purp,amt});
							   }*/
						  transId=ptid;
						   String[] spTable = new String[8];
					  	   spTable [0] = licNo; 
					  	   spTable [1] = mode; 
					  	   spTable [2] = amt; 
					  	   spTable [3] = resultAmt; 
					  	   spTable [4] = spamt;
					  	   spTable [5] = cmsn; 
					  	   spTable [6] = purp; 
					  	   spTable [7] = ptid; 
						//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
							//dbUtil.executeUpdate(spTable);
							 pst=conn.prepareStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
						  		transactionflag=executeUpdateDB(spTable, pst);
							 String SQL2 = "UPDATE "+ parentTbl +" SET PAYMENT_TXN_ID= '"+ transId+"' , PAID_AMOUNT= '0' , PAYMENT_MODE_ID= '"+mode+"' ,  PAYMENT_FLAG = 'P', UPDATE_BY='PAYMENTS', UPDATE_DATE=SYSDATE WHERE "+parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'I' ";
							// dbUtil.createStatement();
							// transactionflag=dbUtil.executeUpdate(SQL2);
							 	st=conn.createStatement();
							  	 transactionflag=executeUpdateDBCreateStmt(SQL2, st);
							 if(transactionflag){
								// dbUtil.commit();
								 conn.commit();
							 }
							
							
					  }
							   
						 }  
				  }   
		   }
	       
	  	    
	     }/*else{
	    	 throw new Exception("20000"); //Throwing an Exception
	     }
	  */
	   }catch (NullPointerException ne) {
		   //dbUtil.rollback();
		   conn.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    //	dbUtil.rollback();
	    	   conn.rollback();
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	String msg = e.getMessage();
	    	if (msg.equalsIgnoreCase("10001")){
	    		throw e;
	    	}else if (msg.equalsIgnoreCase("10025")){
	    		throw e;
	    	}
	    	else{
	    		//dbUtil.rollback();
	    		   conn.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
	    }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    _form.setTransId(transId);
	    return transactionflag;   
	  }
	  
	  /******************************************************************  
		  *   Method Name  :   setTxnID1()
		  *   Arguments    :   TotalAmount Received 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		  *****************************************************************/
		  public String setTxnID1(PaymentForm _paymentForm, String uid, double amount)throws NullPointerException,
	                                            SQLException,Exception{
	    	//DBUtility dbsetUtility = null;
			  DBUtility dbUtil = null;
	    	boolean returnFlag = false;
	    	String returnid = "false";
	    	String mode = _paymentForm.getPayMode();
	    	String purp = _paymentForm.getFuncId();
	    	try{								
	    		dbUtil = new DBUtility();
	    		dbUtil.setAutoCommit(false);
	    		IGRSCommon igrsComman = new IGRSCommon();
	    		String payment_txn_id = igrsComman.getSequenceNumber("IGRS_PAYMENT_TXNID", "PTID");    		
	            if(payment_txn_id != null){   	
	            	dbUtil.createPreparedStatement(CommonSQL.challanTxnInsert);
	    		String[] txnID = new String[5];
	            txnID[0] = payment_txn_id;
		        txnID[1] = String.valueOf(amount);
		        txnID[2] = mode;
		        txnID[3] = purp;
		        txnID[4] = uid;
		        logger.debug("INSIDE DAO........THE AMOUNT IS......"+txnID[1]);
		        logger.debug("INSIDE DAO........THE MODE IS......"+txnID[2]);
		        logger.debug("INSIDE DAO........THE purpose IS......"+txnID[3]);
		        returnFlag = dbUtil.executeUpdate(txnID);	     
		      
		        if(!returnFlag){
		        	
		        	dbUtil.rollback();
		       }
		       if(returnFlag){
		    	
		    	 String txnno = payment_txn_id;
		    	 ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
 		         boolean dbt = false;
 		         dbt = spbd.getSPBalanceAfterDebit(uid, txnno, mode, txnID[1]);
					if(dbt == true){
		    	 returnid = txnno;
		    	 dbUtil.commit();
		    	 }else{
		    		 logger.debug("INSIDE DAO........Before Rollback");
		    		 dbUtil.rollback();
		    		 logger.debug("INSIDE DAO........After Rollback");
		    		 returnid = "fail";
		    		 }
		       }	     
		    }else{
		    	returnid = "fail";
		    }
	    	}
	    	
	    	catch (NullPointerException ne) {
			logger.error("Null Exception at insert TxnID  in DAO " + ne);
		    }
	    	catch (SQLException se) {
	    		logger.error("SQL Exception at insert TxnID  in DAO " + se); 
			}
		    catch(Exception e){
			logger.error(" Exception at insert TxnID in DAO " + e);
		    }
	        finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at setTxnID1 in DAO  " + e);
			 }		
	        }  
	       
	        return returnid;
	   }
    
    /*******************************************************************
     *  Method Name  :  setStatus()
     *  Arguments    :  Transaction ID
     *  Return       :  Boolean
     *   Throws 	 :  NullPointer  or SQLException or Exception
     *******************************************************************/
     public  boolean setStatus(String _payTxnID)throws NullPointerException,
                   SQLException,Exception{
    	//DBUtility dbSetStatusUtility = null;
    	boolean setFlag = false;
    	DBUtility dbUtil = null;
    		try{				
    			dbUtil = new DBUtility();
    			dbUtil.createPreparedStatement(CommonSQL.statusUpdatqry);
	            String[] txnID = new String[1];
	            txnID[0] = _payTxnID;
	            setFlag = dbUtil.executeUpdate(txnID);
	            
	            if(setFlag == false){	            	
	            	dbUtil.rollback();	            
	            	}
	         }catch (NullPointerException ne) {
		      logger.error("Null Exception at Update Status of  TxnID  in DAO " + ne);
		      ne.printStackTrace();
	         }catch (SQLException se) {
	          logger.error("SQL Exception at Update Status of  TxnID  in DAO " + se);
	          se.printStackTrace();
			}
	         catch(Exception e){
		  logger.error(" Exception at Update Status of TxnID in DAO " + e);
		  e.printStackTrace();
	    }
	
    finally {
		try{	    
			dbUtil.closeConnection();
		}
		catch (Exception e) {
			logger.error("Exception at close connection for setStatus() in DAO  " + e);
			e.printStackTrace();
			
		}		
    }    
    return setFlag;
  }
 
    /***************************************************************
     *   Method Name    :  setPayTxn()
     *   Arguments      :  Null
     *   Return         :  String
     *   Throws 	    :  NullPointer  or SQLException or Exception
     ***************************************************************/
    public  String getPayTxn() throws NullPointerException,
                                    SQLException,Exception{
	//DBUtility dbgetPaytxn = null;
	String returnFlag = "fail";
	ArrayList txnIDList = null;
	DBUtility dbUtil = null;
	try{					
		dbUtil = new DBUtility();    		  
		  String challanTxnqry = CommonSQL.getPayTxnIDQry;				  
		  dbUtil.createStatement();
		  returnFlag = dbUtil.executeQry(challanTxnqry);
		  if(returnFlag == null || returnFlag == ""){
			  returnFlag = "Fail";
		  }
		  else{
			  return returnFlag;
		  }
	}catch (NullPointerException ne) {
		logger.error("Null Exception at getPayTxn   in DAO " + ne);
	}
	catch (SQLException se) {
		logger.error("SQL Exception at getPayTxn   in DAO " + se);
	}
	catch(Exception e){
		logger.error(" Exception at getPayTxn in DAO " + e);
	}
    finally {
		try{	    
			dbUtil.closeConnection();
		}
		catch (Exception e) {
			logger.error("Exception when Close Connection for getPayTxn " + e);
		}		
    }
    return returnFlag;
  }

   /**************************************************************** 
   *   Method Name   :  getSpLicense()
   *   Arguments     :  SPUSER ID
   *   Return        :  ArrayList
   *   Throws 	     :  NullPointer  or SQLException or Exception
   *   Edited By     :  Aakriti
 * @param funid 
   ****************************************************************/
   
  //preeti changes on 1 dec 2015
    public  boolean getAcctBalance(String sessionLicenseNo,String funid)
    throws NullPointerException,SQLException,Exception{
    	
   String spLList1 = "";
   boolean balflag = false; 
   
    	ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
   	 DBUtility dbUtil = null;
   	 
   	 try{
   		if(funid.equalsIgnoreCase("FUN_022")|| funid.equalsIgnoreCase("FUN_023")){
   		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(CommonSQL.AcctBal);
        String[] txnID = new String[2];
        txnID[0] = sessionLicenseNo;
        txnID[1] = sessionLicenseNo;
        spLList1 = dbUtil.executeQry(txnID);
       // dbUtil.executeQuery
        if(spLList1.isEmpty()){
        	balflag = false;
      }
      else{
    	  balflag = true;     
      }

   		}
   		
   		else{
   			dbUtil = new DBUtility();
   			dbUtil.createPreparedStatement(CommonSQL.AcctBalO);
   			String[] txnID = new String[2];
   	        txnID[0] = sessionLicenseNo;
   	        txnID[1] = sessionLicenseNo;
   	        spLList1 = dbUtil.executeQry(txnID);
   	        
   	     if(spLList1.isEmpty()){
         	balflag = false;
       }
       else{
     	  balflag = true;     
       }
   		}
   		 
   		
   	 }
   	 
   	 catch(Exception e){
   		 e.printStackTrace();
   	 }
   	 
   	return balflag;
    }
    
    //preeti changes
    public  String getAcctBal(String sessionLicenseNo,String funid)
    throws NullPointerException,SQLException,Exception{
    	
   String spLList1 = "";
   
   
    	ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
   	 DBUtility dbUtil = null;
   	 
   	 try{
   		if(funid.equalsIgnoreCase("FUN_022")|| funid.equalsIgnoreCase("FUN_023")){
   		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(CommonSQL.AcctBal);
        String[] txnID = new String[2];
        txnID[0] = sessionLicenseNo;
        txnID[1] = sessionLicenseNo;
        spLList1 = dbUtil.executeQry(txnID);
       // dbUtil.executeQuery
        
   		}
   		
   		else{
   			dbUtil = new DBUtility();
   			dbUtil.createPreparedStatement(CommonSQL.AcctBalO);
   			String[] txnID = new String[2];
   	        txnID[0] = sessionLicenseNo;
   	        txnID[1] = sessionLicenseNo;
   	        spLList1 = dbUtil.executeQry(txnID);
   		}
   		 
   		
   	 }
   	 
   	 catch(Exception e){
   		 e.printStackTrace();
   	 }
   	 
   	return spLList1;
    }
    
    
   public  ArrayList getSpLicense(String _spUserId, String funid,String sessionLicenseNo)
         throws NullPointerException,SQLException,Exception{
	   
	    String spdt[]=new String[2];
	    spdt[0]=_spUserId;
	    spdt[1]=_spUserId;
	    
	    String spdt1[]=new String[3];
	    spdt1[0]=_spUserId;
	    
	    
    	//DBUtility spLicense = null;
    	String returnFlag = "fail";
    	ArrayList spList = null;
    	ArrayList spLicenseList = null;
    	 ArrayList spLList = new ArrayList();
    	 String spLicenseqry1="";
    	 ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
    	 DBUtility dbUtil = null;
    	try{					
    		dbUtil = new DBUtility(); 
       	    spLicenseList = new ArrayList();
			//String licNo = spbd.getLicenseNumber(_spUserId);
       	 
			/*spdt1[1]=licNo;
		    spdt1[2]=licNo;*/
			
			spdt1[1]=sessionLicenseNo;
		    spdt1[2]=sessionLicenseNo;
			
			if(funid.equalsIgnoreCase("FUN_022")|| funid.equalsIgnoreCase("FUN_023")){
       		/*spLicenseqry1 = "SELECT distinct LICENSE_NO,nvl(ACCOUNT_BAL,0),(select first_name||' '||middle_name||' '||last_name"+
                                   " from IGRS_USER_REG_DETAILS where USER_ID='" + _spUserId +"'"+") as ACNT_HOLDER_NAME"+
                                   " FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP WHERE LICENSE_NO='"+licNo+"' AND PAYMENT_FLAG='C' AND"+
                                   " UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP where LICENSE_NO='"+licNo+"' AND PAYMENT_FLAG='C')";   		
       		logger.debug("First querry is..."+spLicenseqry1);*/
				dbUtil.createPreparedStatement(CommonSQL.SPActQryE);
				spLicenseList = dbUtil.executeQuery(spdt1);
       		}else{
       			/*spLicenseqry1 = "SELECT distinct LICENSE_NO,nvl(ACCOUNT_BAL,0),(select first_name||' '||middle_name||' '||last_name"+
                " from IGRS_USER_REG_DETAILS where USER_ID='" + _spUserId +"'"+") as ACNT_HOLDER_NAME"+
                " FROM IGRS_CREDIT_LIMIT_TXN_LOG WHERE LICENSE_NO='"+licNo+"' AND PAYMENT_FLAG='C' AND"+
                " UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM IGRS_CREDIT_LIMIT_TXN_LOG where LICENSE_NO='"+licNo+"' AND PAYMENT_FLAG='C')";   		
                 logger.debug("First querry is..."+spLicenseqry1);*/
       			dbUtil.createPreparedStatement(CommonSQL.SPActQryO);
				spLicenseList = dbUtil.executeQuery(spdt1);
       		}
			
			 //spLicense.createStatement();
			
			//spLicenseList = spLicense.executeQuery(spLicenseqry1);
			  if(spLicenseList.size()==0){
				/*String newqry = "SELECT distinct iusd.LICENSE_NO,0 as ACCOUNT_BAL,"
					+" (select first_name||' '||middle_name||' '||last_name from IGRS_USER_REG_DETAILS"
					+" where USER_ID='"+ _spUserId +"'"+")as ACNT_HOLDER_NAME from"
					+" IGRS_SP_USER_LICENSE_DETAILS iusd"
					+" WHERE iusd.LICENSE_NO=(SELECT LICENSE_NO FROM"
					+" IGRS_SP_USER_LICENSE_DETAILS WHERE SP_USER_ID='"+ _spUserId +"'"+" AND iusd.LICENSE_STATUS='A')";*/
				  
				  dbUtil.createPreparedStatement(CommonSQL.SP_SETL_QRY);
				  spLicenseList = dbUtil.executeQuery(spdt);
				/*  
				  if(spLicenseList.size()==0)
				  {
					  String newqry1 = "SELECT distinct iusd.LICENSE_NO,0 as ACCOUNT_BAL,"
							+" (select first_name||' '||middle_name||' '||last_name from IGRS_USER_REG_DETAILS"
							+" where USER_ID='"+ _spUserId +"'"+")as ACNT_HOLDER_NAME from"
							+" IGRS_SP_BANK_LICENSE_DETAILS iusd"
							+" WHERE iusd.LICENSE_NO=(SELECT LICENSE_NO FROM"
							+" IGRS_SP_BANK_LICENSE_DETAILS WHERE SP_BANK_USER_ID='"+ _spUserId +"'"+" AND iusd.LICENSE_STATUS='A')";
                          spLicenseList = spLicense.executeQuery(newqry1);
						  logger.debug("Third querry is..."+newqry1);
				  }
				  */
				  
			  }
			  if(spLicenseList != null)
			  {
				for(int i=0;i<spLicenseList.size();i++)
				{
				 // spLList = (ArrayList)spLicenseList.get(0);
				    spLList.add((ArrayList)spLicenseList.get(i));
				    
				}
				}
                 	  
    	}catch (NullPointerException ne) {
    		logger.error("Null Exception at getSpLicense in DAO " + ne);
    		ne.printStackTrace();
    		throw ne;
    	}catch (SQLException se) {
    		logger.error("SQL Exception at getSpLicense in DAO " + se);
    		se.printStackTrace();
    		throw se;
		}
    	
    	catch(Exception e){
    		logger.error(" Exception at getSpLicense in DAO " + e);
    		e.printStackTrace();
    		throw e;
    	}
    	
        finally {
    		try{	    
    			dbUtil.closeConnection();
    		}
    		catch (Exception e) {
    			logger.error("Exception when Close Connection for getSpLicense " + e);
    		}		
        }
        return spLList;
      }
  
      /**************************************************************** 
       *    Method Name   :   getSpAmt()
       *    Arguments     :   SP License NO 
       *    Return        :   ArrayList
       *    Throws  	  :   NullPointer  or SQLException or Exception
       ****************************************************************/
       public  String getSpAmt(String _spLicense)
            throws NullPointerException,SQLException,Exception{	
       	//DBUtility spAmtDbUtility = null;
       	String returnFlag = "fail";
       	ArrayList spList = null;
       	DBUtility dbUtil = null;
       	try{				
       		dbUtil = new DBUtility();    		  
   			String spLicenseQry = CommonSQL.spAmtQry + "'" + _spLicense +"'" ;
   			dbUtil.createStatement();
   			returnFlag = dbUtil.executeQry(spLicenseQry);
   			
       		
       	}catch (NullPointerException ne) {
       		logger.error("Null Exception at getTxnMapping in DAO " + ne);
       	}catch (SQLException se) {
       		logger.error("SQL Exception at getTxnMapping in DAO " + se);
   		}
       	
       	catch(Exception e){
       		logger.error(" Exception at getTxnMapping in DAO " + e);
       	}
       	
           finally {
       		try{	    
       			dbUtil.closeConnection();
       		}
       		catch (Exception e) {
       			logger.error("Exception when Close Connection for getTxnMapping " + e);
       		}		
           }
           return returnFlag;
         }
      
      /****************************************************************
       *  Method Name : setSPDetails()
       *  Arguments   : DTO Object
       *  Return      : SPTXN ID
       *   Throws     : NullPointer  or SQLException or Exception
       ****************************************************************/
     
      public String setSPDetails(PaymentDTO _paymentDTO)
                       throws NullPointerException,SQLException,Exception{
        //DBUtility dbSetSpList = null;
        boolean setFlag = false;
        String spTxnId = null;
        DBUtility dbUtil = null;
         try{
            	 boolean spUpdate = spUpdate(_paymentDTO);
        	 if(spUpdate){
        		 dbUtil = new DBUtility();
        		 dbUtil.createPreparedStatement(CommonSQL.spInsertQry);        	 
             String[] sp = new String[3];
             sp[0] = _paymentDTO.getSpLicenseNo();
             sp[1] = _paymentDTO.getSpAmt();
             sp[2] = _paymentDTO.getSpFnlAmt();
             //TODO: Note the query expects 4 parameters
             //however the array passed contains only 3
             //The fourth parameter should be a logged in username
             //this needs to be configured/confirmed by the developer
         //    sp[3] = _paymentDTO.getSpLicenseNo();
          //   System.out.println("I am in DAO _paymentDTO.getSpLicenseNo();---"+_paymentDTO.getSpLicenseNo());
             setFlag = dbUtil.executeUpdate(sp);
              
             if(!setFlag){
            	 dbUtil.rollback();	
            	 return spTxnId;
         	 }
             if(setFlag){
               	 spTxnId = getSpTxnId();
               	 if(spTxnId != null){
               		 return spTxnId;
               	 }
               	 else{
               		dbUtil.rollback();
               		 return spTxnId;
               	 }
             }
          }else{
        	  return spTxnId;
          }
        }catch (NullPointerException ne) {
            ne.printStackTrace();
          logger.error("Null Exception at setSPDetails  in DAO " + ne);
        }catch (SQLException se) {
            se.printStackTrace();
          logger.error("SQL Exception at setSPDetails in DAO " + se);
        }catch(Exception e){
            e.printStackTrace();
           logger.error(" Exception at setSPDetails in DAO " + e);
        }finally {
          try{	    
        	  dbUtil.closeConnection();
          }catch (Exception e) {
              e.printStackTrace();
              logger.error("Exception at close connection for setSPDetails in DAO  " + e);
          }		
       }    
       return spTxnId;
     }
      
      
      /**************************************************************
       *   Method Name   :  spUpdate()
       *   Arguments     :  PaymentDTO
       *   Return        :  Boolean
       *   Throws 	     :  NullPointer  or SQLException or Exception
       ***************************************************************/
         public synchronized boolean spUpdate(PaymentDTO _paymentDto)
               throws NullPointerException,SQLException,Exception{	
          	//DBUtility spUpdateDbUtility = null;
          	        	
          	boolean returnFlag = false;
          	DBUtility dbUtil = null;
             	try{			
             		dbUtil = new DBUtility();
             		dbUtil.createPreparedStatement(CommonSQL.spUpdateQry);
             		String sp[] = new String[2];
             		sp[1] = _paymentDto.getSpLicenseTxnid();
             		sp[0] = _paymentDto.getSpFnlAmt();       
             		
      			  returnFlag =dbUtil.executeUpdate(sp);
      			 	  
          		  if(returnFlag){
          			  return returnFlag;
          		  }
          		  else{
          			  return returnFlag;
          		  }
          	}catch (NullPointerException ne) {
          		logger.error("Null Exception at spUpdate in DAO " + ne);
          		ne.printStackTrace();
          	}catch (SQLException se) {
          		logger.error("SQL Exception at spUpdate in DAO " + se);
          		se.printStackTrace();
      		}
          	
          	catch(Exception e){
          		logger.error(" Exception at spUpdate in DAO " + e);
          		e.printStackTrace();
          	}
          	
              finally {
          		try{	    
          			dbUtil.closeConnection();
          		}
          		catch (Exception e) {
          			logger.error("Exception when Close Connection for spUpdate " + e);
          			e.printStackTrace();
          		}		
              }
              return returnFlag;
            }
      
      
      
       /**************************************************************
       *   Method Name  :  getSpTxnID()
       *   Arguments    :  Null
       *   Return       :  String
       *   Throws       :  NullPointer  or SQLException or Exception
       ***************************************************************/
         public synchronized String getSpTxnId()
               throws NullPointerException,SQLException,Exception{	
          //	DBUtility sptxnDbUtility = null;
          	String returnFlag = null;  
          	DBUtility dbUtil = null;
             	try{				
             		dbUtil = new DBUtility();
             		dbUtil.createStatement();
      			  returnFlag =dbUtil.executeQry(CommonSQL.spTxnIdQry); 
      				  
          		  if(returnFlag !=null){          			  
          			  return returnFlag;
          		  }
          		  else{
          			
          			  return returnFlag;
          		  }
          	}catch (NullPointerException ne) {
          		logger.error("Null Exception at getSpTxnId in DAO " + ne);
          	}catch (SQLException se) {
          		logger.error("SQL Exception at getSpTxnId in DAO " + se);
      		}
          	
          	catch(Exception e){
          		logger.error(" Exception at getSpTxnId in DAO " + e);
          	}
          	
              finally {
          		try{	    
          			dbUtil.closeConnection();
          		}
          		catch (Exception e) {
          			logger.error("Exception when Close Connection for getTxnMapping " + e);
          		}		
              }              
              return returnFlag;
            }
        
         
        
         /**************************************************************
          *   Method Name  :  getSPFEE()
          *   Arguments    :  Function ID and userID
          *   Return       :  String
          *   Throws 	   :  NullPointer  or SQLException or Exception
          ***************************************************************/

      public String getSPFee(String _funId,String _user)
                    throws NullPointerException,SQLException,Exception {
           String spFee = null;           
           ArrayList spFeeList = null;
           
           try {
    	      IGRSCommon igrsCommon = new IGRSCommon();
    	      String serviceId = "";
    	      spFeeList = igrsCommon.getOthersFeeDuty(_funId,serviceId,_user);
              if(spFeeList != null){
                spFee =(String)spFeeList.get(2);
              }
             }catch (NullPointerException ne) {
            	 logger.error("Null Pointer Exception at getSPFEE in DAO " + ne);
			}catch (SQLException se) {
			    se.printStackTrace();
				logger.error("SQL Exception at getSPFEE in DAO " + se);
			}catch (Exception e) {
			    e.printStackTrace();
				logger.error("Exception at getSPFee in DAO " + e);
           }
           return spFee;
      }
      
      /**
       * ===========================================================================
       * Method         :   getFunctionName()
       * Description    :   Returns String of function name. 
       * Arguments      :   function id
       * return type    :   String
       * ===========================================================================
       */  
  	public String getFunctionName(String funId,String languageLocale){
  		String[]arry=new String[1];
  		arry[0]=funId;
  		String FunName=null;
  		//DBUtility dbUtils = null;
  		DBUtility dbUtil = null;
  		try {
  			dbUtil = new DBUtility();
  			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
  				dbUtil.createPreparedStatement(CommonSQL.FUNC_NAME);
  			}else{
  				dbUtil.createPreparedStatement(CommonSQL.FUNC_NAME_HINDI);
  			}
  			FunName = dbUtil.executeQry(arry);
  			logger.debug("the value in function Name is " + FunName);
  		} catch (Exception x) {
  			logger.error("Exception in getFunctionName(): " + x);
  			
  		} finally {
  			try {
  				dbUtil.closeConnection();
  			} catch (Exception ex) {
  				logger.error("Exception in closing connection" + ex);
  				
  			}
  		}
  		return FunName;
  	}
  	 /**
     * ===========================================================================
     * Method         :   getRevenueHeads()
     * Description    :   Returns list carrying revenue heads. 
     * Arguments      :   function id
     * return type    :   ArrayList
     * ===========================================================================
     */  public ArrayList getRevenueHeads(String funId) throws Exception{
			String[] tid = new String[1];
			tid[0]=funId;
			//DBUtility dbUtils1 = null;
			ArrayList list = new ArrayList();
			DBUtility dbUtil = null;
			try{
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.REV_HEADS);
			    list=dbUtil.executeQuery(tid);
	          }  catch (Exception x) {
					logger.debug(x);
				} finally {
					if (dbUtil != null) {
						dbUtil.closeConnection();
					}
				}list.trimToSize();
			return list;
		}
     /**
      * ===========================================================================
      * Method         :   getDistrictList()
      * Description    :   Returns district List. 
      * Arguments      :   -
      * return type    :   ArrayList
      * ===========================================================================
      */  public ArrayList getDistrictList() throws Exception{
 			String[] tid = new String[1];
 			ArrayList list = new ArrayList();
 			//DBUtility dbUtils2 = null;
 			DBUtility dbUtil = null;
 			try{
 				
 				dbUtil = new DBUtility();
 				dbUtil.createStatement();
 			    //list=dbUtils2.executeQuery(CommonSQL.DISTRICT_QRY);
 				list=dbUtil.executeQuery(CommonSQL.DISTRICT_QRY_HINDI);
 	          }  catch (Exception x) {
 					logger.debug(x);
 				} finally {
 					if (dbUtil != null) {
 						dbUtil.closeConnection();
 					}
 				}list.trimToSize();
 			return list;
 		}

      /**
       * ===========================================================================
       * Method         :   getOfficeList()
       * Description    :   Returns district List. 
       * Arguments      :   -
       * return type    :   ArrayList
       * ===========================================================================
       */  public ArrayList getOfficeList(String disId) throws Exception{
  			String[] tid = new String[1];
  			tid[0]=disId;
  			ArrayList list = new ArrayList();
  			//DBUtility dbUtils3 = null;
  			DBUtility dbUtil = null;
  			try{
  				dbUtil = new DBUtility();
  				//dbUtils3.createPreparedStatement(CommonSQL.OFFICE_QRY);
  				dbUtil.createPreparedStatement(CommonSQL.OFFICE_QRY_HINDI);
  			    list=dbUtil.executeQuery(tid);
  	          }  catch (Exception x) {
  					logger.debug(x);
  				} finally {
  					if (dbUtil != null) {
  						dbUtil.closeConnection();
  					}
  				}list.trimToSize();
  			return list;
  		}

       
       public ArrayList getTreasuryList(String disId,String language) throws Exception{
 			String[] tid = new String[1];
 			tid[0]=disId;
 			ArrayList list = new ArrayList();
 			//DBUtility dbUtils3 = null;
 			DBUtility dbUtil = null;
 			try{
 				dbUtil = new DBUtility();
 				//dbUtils3.createPreparedStatement(CommonSQL.OFFICE_QRY);
 				if(language.equalsIgnoreCase("en"))
 				dbUtil.createPreparedStatement(CommonSQL.IGRS_GET_TREASURY_LIST);
 				else
 					dbUtil.createPreparedStatement(CommonSQL.IGRS_GET_TREASURY_LIST_HI);
 			    list=dbUtil.executeQuery(tid);
 	          }  catch (Exception x) {
 					logger.debug(x);
 				} finally {
 					if (dbUtil != null) {
 						dbUtil.closeConnection();
 					}
 				}list.trimToSize();
 			return list;
 		}
       
       
       
       
       /******************************************************************  
		  *   Method Name  :   ChallanDwnldInsert()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
     * @param userId 
     * @param jspPage 
    *******************************************************************/  
	  
	  public  boolean ChallanDwnldInsert (PaymentForm eform, String userId, String jspPage)throws NullPointerException,
    SQLException,Exception{
	   boolean transactionflag = false;
	   String arry[]=new String[16];
	   arry[1] = eform.getParentRefId();
	   arry[2] = eform.getFuncId();
	   arry[3] = eform.getRevMjrHd();
	   arry[4] = eform.getRevSbMjrHd();
	   arry[5] = eform.getRevMnrHd();
	   arry[6] = eform.getParentAmount();
	   arry[7] = eform.getDwnldChallanAmt();
	   arry[8] = eform.getDwnldChallanFirstName();
	   arry[9] = eform.getDwnldChallanMiddleName();
	   arry[10]= eform.getDwnldChallanLastName();
	   arry[11]= eform.getParentDistId();
	   arry[12]= eform.getParentOffId();
	   arry[13]= userId;
	   arry[14]= eform.getDwnldChallanAddress();
	   
	   String refChallanID="";
	   
	   String disId=eform.getParentDistId();
	   String offId=eform.getParentOffId();
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   if(disId.length()<2){
		   disId= "0"+disId;
	   }
	   if(offId.length()==1){
		   offId= "00"+offId;
	   }
	   if(offId.length()==2){
		   offId= "0"+offId;
	   }
	   offId="010";//to be removed post creation of office master
	   DBUtility dbUtil = null;
	   //DBUtility transmgtUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	     String SQL1 = "SELECT COUNT(CHALLAN_SERIAL_NUMBER) FROM IGRS_PAYMENT_ECHALLAN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
	     logger.debug(SQL1);
	     dbUtil.createStatement();
	     String number1 = dbUtil.executeQry(SQL1);
	     
	     
 		String SQL5 ="SELECT COUNT(TRANSACTION_ID) FROM IGRS_CHALLAN_GEN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";    //query moved to CommonSQL by Roopam: 7 Oct 2013.
 		logger.debug(SQL5);
 		dbUtil.createStatement();
	   	String number5 = dbUtil.executeQry(SQL5);
	     
	     
	     if (number1.equalsIgnoreCase("0") && number5.equalsIgnoreCase("0")){
	                 
	                   String drpqry = "DROP SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ";                         
	                   dbUtil.createStatement();
	                   dbUtil.executeUpdate(drpqry);
	                   String crtqry = "CREATE SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
	                   dbUtil.createStatement();
	                   dbUtil.executeUpdate(crtqry);
	           }
	             String SQL2 = "select IGRS_PAYMENT_ECHALLAN_ID_SEQ.nextval from dual";
	             dbUtil.createStatement();
	             String number2 = dbUtil.executeQry(SQL2);
	             if(number2.length()==1){
	                     number2="00000"+number2;  
	                     }else
	                     if(number2.length()==2){
	                             number2="0000"+number2;  
	                     }else
	                     if(number2.length()==3){
	                             number2="000"+number2;  
	                     }else
	                     if(number2.length()==4){
	                             number2="00"+number2;  
	                     }else
	                     if(number2.length()==5){
	                             number2="0"+number2;  
	                     }
	             
	 refChallanID="1"+disId+offId+dfmt+mfmt+yfmt+number2; 
	 arry[0] = refChallanID;
	 
	 //
	 int epnrNo=10000000;
	 String ePNR = "select EPRN_CHALLAN.nextval from dual";
     dbUtil.createStatement();
     String ePNR2 = dbUtil.executeQry(ePNR);
     if(ePNR2!=null && !ePNR2.equalsIgnoreCase("")){
     epnrNo=epnrNo+Integer.parseInt(ePNR2);
     }
     arry[15]= Integer.toString(epnrNo);
     //above EPNR section added by roopam as per new challan requirement.
	 
	 dbUtil.createPreparedStatement(CommonSQL.DWNLD_CHALLAN_INSERT_QRY);
	 boolean up=dbUtil.executeUpdate(arry);
	 
	 if(up)
	 {
		 
		 String arr1[] = new String[7];
		
		 arr1[0] = eform.getDwnldChallanAmt();
		 arr1[1] = eform.getParentTable();
	
		 arr1[2] = eform.getParentColumnName();
		 arr1[3] = eform.getParentKey();
		 
		 arr1[4] = jspPage;
		 arr1[5] = arry[15];
		 arr1[6] = userId;
		
		
		 
		 
		 dbUtil.createPreparedStatement(CommonSQL.INSERT_INTO_OFFLINE_DATA);
		up = dbUtil.executeUpdate(arr1);
	 }
	 
	 if(up){ 
		 eform.setDwnldChallanUniqueId(refChallanID);
		 eform.setEPNR(arry[15]);
		 dbUtil.commit();
	     transactionflag=true;
	 }
	  }catch (NullPointerException ne) {
		  dbUtil.rollback();
		   throw ne;
			
	    }
 	    catch (SQLException se) {
 	    	dbUtil.rollback();
 	    	
 		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
 	 throw se;
		}
	    catch(Exception e){
	    	
	    	dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    //eform.setTransId(transId);
	    eform.setDwnldChallanUniqueId(refChallanID);
	    eform.setEPNR(arry[15]);
	    return transactionflag;   
	  }
	  
	  /******************************************************************  
		  *   Method Name  :   onlineDwnldInsert()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
  * @param userId 
	 * @param crn 
	 * @param jspPage 
 *******************************************************************/  
	  
	  public  boolean onlineDwnldInsert (PaymentForm eform, String userId, String crn, String jspPage)throws NullPointerException,
 SQLException,Exception{
	   boolean transactionflag = false;
	   String arry[]=new String[18];
	   arry[1] = eform.getParentRefId();
	   arry[2] = eform.getFuncId();
	   arry[3] = eform.getRevMjrHd();
	   arry[4] = eform.getRevSbMjrHd(); 
	   arry[5] = eform.getRevMnrHd();
	   arry[6] = eform.getParentAmount();
	   arry[7] = eform.getDwnldChallanAmt();
	   arry[8] = eform.getDwnldChallanFirstName();
	   arry[9] = eform.getDwnldChallanMiddleName();
	   arry[10]= eform.getDwnldChallanLastName();
	   arry[11]= eform.getParentDistId();
	   arry[12]= eform.getParentOffId();
	   arry[13]= userId;
	   arry[14]= eform.getDwnldChallanAddress();
	   arry[15] = crn;
	   arry[16] = eform.getRevSchemeHead();
	   arry[17] =     eform.getTreasuryId(); 
	   String refChallanID="";
	   String disId=eform.getParentDistId();
	   String offId=eform.getParentOffId();
	   Date date = new Date();
	   Format yearformat = new SimpleDateFormat("yyyy");
	   Format monthformat = new SimpleDateFormat("MM");
	   Format dateformat = new SimpleDateFormat("dd");
	   String dfmt = dateformat.format(date);
	   String yfmt = yearformat.format(date);
	   String mfmt = monthformat.format(date);
	   if(disId.length()<2){
		   disId= "0"+disId;
	   }
	   if(offId.length()==1){
		   offId= "00"+offId;
	   }
	   if(offId.length()==2){
		   offId= "0"+offId;
	   }
	   offId="010";//to be removed post creation of office master
	  
	//   DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{
		   dbUtil = new DBUtility();
		   dbUtil.setAutoCommit(false);
	     String SQL1 = "SELECT COUNT(CHALLAN_SERIAL_NUMBER) FROM IGRS_PAYMENT_ECHALLAN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
	     logger.debug(SQL1);
	     dbUtil.createStatement();
	     String number1 = dbUtil.executeQry(SQL1);
	     
	     String SQL5 ="SELECT COUNT(TRANSACTION_ID) FROM IGRS_CHALLAN_GEN_DETAILS WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";    //query moved to CommonSQL by Roopam: 7 Oct 2013.
	 		logger.debug(SQL5);
	 		dbUtil.createStatement();
		   	String number5 = dbUtil.executeQry(SQL5);
	     
	     if (number1.equalsIgnoreCase("0") && number5.equalsIgnoreCase("0")){
	                 
	                   String drpqry = "DROP SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ";                         
	                   dbUtil.createStatement();
	                   dbUtil.executeUpdate(drpqry);
	                   String crtqry = "CREATE SEQUENCE IGRS_PAYMENT_ECHALLAN_ID_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";
	                   dbUtil.createStatement();
	                   dbUtil.executeUpdate(crtqry);
	           }
	             String SQL2 = "select IGRS_PAYMENT_ECHALLAN_ID_SEQ.nextval from dual";
	             dbUtil.createStatement();
	             String number2 = dbUtil.executeQry(SQL2);
	             if(number2.length()==1){
	                     number2="00000"+number2;  
	                     }else
	                     if(number2.length()==2){
	                             number2="0000"+number2;  
	                     }else
	                     if(number2.length()==3){
	                             number2="000"+number2;  
	                     }else
	                     if(number2.length()==4){
	                             number2="00"+number2;  
	                     }else
	                     if(number2.length()==5){
	                             number2="0"+number2;  
	                     }
	             
	 refChallanID="1"+disId+offId+dfmt+mfmt+yfmt+number2; 
	 arry[0] = refChallanID;
	 
	 dbUtil.createPreparedStatement(CommonSQL.DWNLD_ONLINE_INSERT_QRY);
	 boolean up=dbUtil.executeUpdate(arry);
	 
	 if(up)
	 {
		 
		 String arr1[] = new String[7];
		
		 arr1[0] = eform.getDwnldChallanAmt();
		 arr1[1] = eform.getParentTable();
	
		 arr1[2] = eform.getParentColumnName();
		 arr1[3] = eform.getParentKey();
		 
		 arr1[4] = jspPage;
		 arr1[5] = arry[15];
		 arr1[6] = userId;
		
		
		 
		 
		 dbUtil.createPreparedStatement(CommonSQL.INSERT_INTO_OFFLINE_DATA_CYBER);
		up = dbUtil.executeUpdate(arr1);
	 }
	 if(up){ 
		 eform.setDwnldChallanUniqueId(refChallanID);
		 dbUtil.commit();
	     transactionflag=true;
	 }
	  }catch (NullPointerException ne) {
		  dbUtil.rollback();
		   throw ne;
			
	    }
	    catch (SQLException se) {
	    	dbUtil.rollback();
	    	
		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
	 throw se;
		}
	    catch(Exception e){
	    	
	    	dbUtil.rollback();
		logger.error(" Exception at PaymentTransactionFinal in DAO " + e);
		throw e;
		}
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at PaymentTransactionFinal in DAO  " + e);
			 }		
	        }
	    //eform.setTransId(transId);
	    eform.setDwnldChallanUniqueId(refChallanID);
	    return transactionflag;   
	  }
	  
	  /**
	     * ===========================================================================
	     * Method         :   getDwnldChallanInsertedDet()
	     * Description    :   Returns list carrying inserted details of downloaded challan. 
	     * Arguments      :   unique id
	     * return type    :   ArrayList
	     * ===========================================================================
	     */  public ArrayList getDwnldChallanInsertedDet(String uniquID) throws Exception{
				String[] tid = new String[1];
				tid[0]=uniquID;
				//DBUtility dbUtils1 = null;
				ArrayList list = new ArrayList();
				DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(CommonSQL.DWNLD_CHALN_DETLS);
				    list=dbUtil.executeQuery(tid);
		          }  catch (Exception x) {
						logger.debug(x);
					} finally {
						if (dbUtil != null) {
							dbUtil.closeConnection();
						}
					}list.trimToSize();
				return list;
			}
	     
	     public ArrayList getDwnldChallanInsertedDetInfo(String uniquID) throws Exception{
				String[] tid = new String[1];
				tid[0]=uniquID;
				//DBUtility dbUtils1 = null;
				ArrayList list = new ArrayList();
				DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
					dbUtil.createPreparedStatement(CommonSQL.DWNLD_CHALN_DETLS);
				    list=dbUtil.executeQuery(tid);
		          }  catch (Exception x) {
						logger.debug(x);
					} finally {
						if (dbUtil != null) {
							dbUtil.closeConnection();
						}
					}list.trimToSize();
				return list;
			}
		  /******************************************************************  
		  *   Method Name  :   PaymentChallanTransactionFinal()
		  *   Arguments    :   Cash Form 
		  *   Return       :   Boolean
		  *   Throws 	  :   NullPointer  or SQLException or Exception
		  *   Author      :   Roopam Mehta
		  *   Date        :   10 Oct 2013
      *******************************************************************/  
	  
	  
	  public  synchronized ArrayList getDistIdOfficeIdForRUChallan (PaymentForm eform)throws NullPointerException, SQLException,Exception{
	   boolean transactionflag = false; 
	   ArrayList recordList = new ArrayList();
	 //  ArrayList comp = new ArrayList();

	   
	  		
	  // DBUtility transmgtUtil = null;
	   DBUtility dbUtil = null;
	   try{  	
		   dbUtil = new DBUtility();
	   String[] param={eform.getChallanRefNo()};
	  // transmgtUtil.setAutoCommit(false);
	   String challanSql = CommonSQL.getDistIdOfficeIdForRUChallan;
	   dbUtil.createPreparedStatement(challanSql);  
	   recordList = dbUtil.executeQuery(param);
	  
	  
	   }catch (NullPointerException ne) {
		   dbUtil.rollback();
		   //throw ne;
		   logger.error("SQL Exception at getDistIdOfficeIdForRUChallan  in DAO " + ne); 	
	    }
   	    catch (SQLException se) {
   	    	dbUtil.rollback();
   	    	
   		logger.error("SQL Exception at getDistIdOfficeIdForRUChallan  in DAO " + se); 
   	 throw se;
		}
	    catch(Exception e){
	    	//String msg = e.getMessage();
	    	//if (msg.equalsIgnoreCase("20000")||msg.equalsIgnoreCase("20001")||msg.equalsIgnoreCase("20002")||msg.equalsIgnoreCase("20003")){
	    	//	throw e;
	    	//}else{
	    	dbUtil.rollback();
		logger.error(" Exception at getDistIdOfficeIdForRUChallan in DAO " + e);
		//throw e;
	   // }
	    }
	    finally {
			 try{	    
				 dbUtil.closeConnection();
			 }
			 catch (Exception e) {
			 logger.error("Exception at getDistIdOfficeIdForRUChallan in DAO  " + e);
			 }		
	        }
	    //eform.setTransId(transId);
	    return recordList;   
	  }

		/**
		 * getCurrDateTime
		 * for getting current system date/time from db.
		 * @param 
		 * @return String
		 * @author ROOPAM
		 */
		public String getCurrDateTime() throws Exception {
			String currDateTime = new String();
			DBUtility dbUtil = null;
			try {
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				currDateTime = dbUtil.executeQry(CommonSQL.CURRENT_DATE_TIME);
				
			} catch (Exception exception) {
				logger.debug("Exception in getCurrDateTime" + exception);
			}finally {
				 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at getDistIdOfficeIdForRUChallan in DAO  " + e);
				 }		
		        }
			return currDateTime;

		}




		public String getTreasuryDisctrictId(String districtID) {
			String distrcitId = new String();
			DBUtility dbUtil = null;
			try {
				String arr[] = new String[1];
				arr[0] = districtID;
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.GET_TREASURY_DISTRICT);
				
				distrcitId = dbUtil.executeQry(arr);
				
			} catch (Exception exception) {
				logger.debug("Exception in getTresuryDistrictId" + exception);
			}finally {
				 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at getTresuryDistrictId in DAO  " + e);
				 }		
		        }
			return distrcitId;

		}
		
		
		public String getDescriptionHOA(String districtID, String string, String string2) {
			String majorHead = new String();
			DBUtility dbUtil = null;
			try {
				String arr[] = new String[1];
				
				arr[0] = districtID;
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.GET_MAJOR_HEAD);
				
				majorHead = dbUtil.executeQry(arr);
				
				dbUtil.createPreparedStatement(CommonSQL.GET_SUB_MAJOR_HEAD);
				
				arr[0] = string;
				majorHead = majorHead +" and " +dbUtil.executeQry(arr);
				
				dbUtil.createPreparedStatement(CommonSQL.GET_MINOR_HEAD);
				arr[0] = string2;
				majorHead = majorHead +" and " +dbUtil.executeQry(arr);
				
				
			} catch (Exception exception) {
				logger.debug("Exception in getDescriptionHOA" + exception);
			}finally {
				 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at getDescriptionHOA in DAO  " + e);
				 }		
		        }
			return majorHead;

		}




		public String getSequence() {
			
			String sequence = new String();
			DBUtility dbUtil = null;
			try {
				
				
				dbUtil = new DBUtility();
				dbUtil.createStatement();
				
				sequence = dbUtil.executeQry(CommonSQL.GET_ONLINE_SEQUENCE);
				
			} catch (Exception exception) {
				logger.debug("Exception in getSequence" + exception);
			}finally {
				 try{	    
					 dbUtil.closeConnection();
				 }
				 catch (Exception e) {
				 logger.error("Exception at getSequence in DAO  " + e);
				 }		
		        }
			
			if(sequence.length()==1){
				sequence="00"+sequence;  
                }else
                if(sequence.length()==2){
                	sequence="0"+sequence;  
                }
			
			return sequence;

		
			
			
		}




		public void getpaymentDetailsOnline(PaymentForm form) {
			
			DBUtility dbUtil = null;
			try {
				String arr[] = new String[1];
				
				arr[0] = form.getCrnNumber();
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.GET_PAYMENT_DETAILS);
				ArrayList list = dbUtil.executeQuery(arr);
				
				if(list!=null)
				{
					ArrayList list1 = (ArrayList) list.get(0);
					form.setChallanNumber((String)list1.get(0));
					form.setHoa(getDescriptionHOA((String)list1.get(1), (String)list1.get(2), (String)list1.get(3)));
					form.setBrn((String)list1.get(4));
					form.setTranDate((String)list1.get(5));
					
					String scrollDate = (String)list1.get(6);
					if(scrollDate!=null && !"null".equalsIgnoreCase(scrollDate))
					scrollDate = getProperDate(scrollDate);
					
					form.setScrollDate(scrollDate);
					form.setScrollNumber((String)list1.get(7));
					form.setCin((String)list1.get(8));
					form.setDwnldChallanAddress((String)list1.get(9));
				}
				
			} catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
			
		}




		private String getProperDate(String scrollDate) {
			String finalDate="";
			try {
			
				finalDate= scrollDate.substring(0,2)+"/"+scrollDate.substring(2, 4)+"/"+scrollDate.subSequence(4, 8);
				 
				
			} catch (Exception e) {
			 logger.debug(e.getMessage(),e);
			}
			return finalDate ;
		}

		public boolean insertURL(String crn,String type,String url){
		logger.debug("IN DAO...for insertURL");
		      boolean flag = true;  
		      DBUtility dbUtil = null;
		      String sql="";
		      ArrayList recordList = new ArrayList();
		      ArrayList record = new ArrayList();
		     
		      try {
			  
			   dbUtil = new DBUtility();
			   if("O".equalsIgnoreCase(type))
			   sql=CommonSQL.UPDATE_OUTPUT_TO_TREASURY_URL;
			   else
				   sql=CommonSQL.UPDATE_INPUT_FROM_TREASURY_URL;  
				dbUtil.createPreparedStatement(sql);
				//ArrayList list = dbUtil.executeQuery(new String[]{url,crn});
				flag=dbUtil.executeUpdate(new String[]{url,crn});
				

		            }
		      catch (Exception x)
		      {
		    	  logger.error("Exception ) :- " + x);
		    	  x.printStackTrace();
		      }
		      finally
		      {
		            try 
		            {
		              dbUtil.closeConnection();

		            } 
		            catch (Exception ex) 
		            {
		            	 logger.error("Exception  :-" + ex);
		            }
		        }
		         return flag;
		    }
		
		public boolean checkAmount(String crn,String amt){
			logger.debug("IN DAO...for checkAmount");
			      boolean flag = true;  
			      DBUtility dbUtil = null;
			      String sql="";
			      ArrayList recordList = new ArrayList();
			      ArrayList record = new ArrayList();
			     
			      try {
				  
				   dbUtil = new DBUtility();
				  
					   sql=CommonSQL.SELECTPAYINGAMOUNT_CRN;  
					dbUtil.createPreparedStatement(sql);
					//ArrayList list = dbUtil.executeQuery(new String[]{url,crn});
				//	flag=dbUtil.executeUpdate(new String[]{crn});
					//ArrayList list = dbUtil.executeQuery(new String[]{crn});
				String payingAmt=dbUtil.executeQry(new String[]{crn});
					if(amt.equalsIgnoreCase(payingAmt))
							flag=true;
					else
							flag=false;	

			            }
			      catch (Exception x)
			      {
			    	  logger.error("Exception ) :- " + x);
			    	  x.printStackTrace();
			      }
			      finally
			      {
			            try 
			            {
			              dbUtil.closeConnection();

			            } 
			            catch (Exception ex) 
			            {
			            	 logger.error("Exception  :-" + ex);
			            }
			        }
			         return flag;
			    }
		public boolean checkUser(String crn,String userID){
			logger.debug("IN DAO...for checkUser");
			      boolean flag = true;  
			      DBUtility dbUtil = null;
			      String sql="";
			      ArrayList recordList = new ArrayList();
			      ArrayList record = new ArrayList();
			     
			      try {
				  
				   dbUtil = new DBUtility();
				  
					   sql=CommonSQL.SELECTUSERID_CRN;  
					dbUtil.createPreparedStatement(sql);
					//ArrayList list = dbUtil.executeQuery(new String[]{url,crn});
				//	flag=dbUtil.executeUpdate(new String[]{crn});
					//ArrayList list = dbUtil.executeQuery(new String[]{crn});
				String payingUser=dbUtil.executeQry(new String[]{crn});
					if(userID.equalsIgnoreCase(payingUser))
							flag=true;
					else
							flag=false;	

			            }
			      catch (Exception x)
			      {
			    	  logger.error("Exception ) :- " + x);
			    	  x.printStackTrace();
			      }
			      finally
			      {
			            try 
			            {
			              dbUtil.closeConnection();

			            } 
			            catch (Exception ex) 
			            {
			            	 logger.error("Exception  :-" + ex);
			            }
			        }
			         return flag;
			    }

		public void updateIP(String dwnldChallanUniqueId, String onlineIp) {
			DBUtility dbUtil = null;
			String arr[] = new String[2];
			
			
			
			try
			{
				arr[1] = dwnldChallanUniqueId;
				    arr[0] = onlineIp;
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement("Update IGRS_PAYMENT_ECHALLAN_DETAILS set IP_ADDRESS=? where crn_number=?");
				boolean as = dbUtil.executeUpdate(arr);
				
			}
			catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
			
		}
   
		public  boolean executeUpdateDB(String arr[],PreparedStatement pst) throws SQLException{

	        int j = 0;
	        try {
	            for (int i = 0; i < arr.length; i++) {
	            	logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
	                pst.setString(i + 1, arr[i]);
	            }
	            logger.debug("PaymentUtility:::Wipro : before execute update");
	            try {
	                j = pst.executeUpdate();
	            } catch (StringIndexOutOfBoundsException e) {
	                logger.error(e.getMessage(), e);
	            }
	            logger.debug("after execute update row cownt is " + j);
	            pst.clearParameters();
	        } catch (SQLException sqle) {
	        	logger.error(sqle.getMessage(),sqle);
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.error(nfe.getMessage(),nfe);
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.error(iae.getMessage(),iae);
	            throw iae;
	        } catch (StringIndexOutOfBoundsException sioobe) {
	        	logger.error(sioobe.getMessage(), sioobe);
	            throw sioobe;
	        }
	        if (j == 0)
	            return false;
	        return true;
	    }
		
		public  boolean executeUpdateDBCreateStmt(String sql,Statement st) throws SQLException{

	        int j = 0;
	        try {
	            
	                j = st.executeUpdate(sql);
	             
	            logger.debug("PaymentUtility:::Wipro : after execute update");
	            //pst.clearParameters();
	        } catch (SQLException sqle) {
	        	logger.error(sqle.getMessage(),sqle);
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.error(nfe.getMessage(),nfe);
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.error(iae.getMessage(),iae);
	            throw iae;
	        } catch (StringIndexOutOfBoundsException sioobe) {
	            logger.error(sioobe.getMessage(),sioobe);
	            throw sioobe;
	        }
	        if (j == 0)
	            return false;
	        return true;
	    }
		public  ArrayList executeQuery(String arr[],PreparedStatement pst) throws SQLException{
	        logger.debug("PaymentUtility - executeQuery()");
	        ResultSet rst = null;
	        ArrayList list = new ArrayList();
	        try {
	            for (int i = 0; i < arr.length; i++) {
	            	logger.debug("Wipro : arr[" + i + "] = " + arr[i]);
	                pst.setString(i + 1, arr[i]);
	            }
	            rst = pst.executeQuery();
	            logger.debug("Wipro in PaymentUtility - executeQuery() after pst.executeQuery()");
	            ResultSetMetaData rsmd = rst.getMetaData();
	            int col_count = rsmd.getColumnCount();
	            while (rst.next()) {
	                ArrayList row_list = new ArrayList();
	                for (int i = 1; i <= col_count; i++) {
	                	//logger.debug("index for reg init----> "+i);
	                    row_list.add(rst.getString(i));
	                } //for
	                list.add(row_list);
	            } //while
	            pst.clearParameters();
	        } catch (SQLException sqle) {
	        	logger.error(sqle.getMessage(),sqle);
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.error(nfe.getMessage(),nfe);
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.error(iae.getMessage(),iae);
	            throw iae;
	        }
	        list.trimToSize();
	        return list;
	    }
		
	    public  String executeQry(String query,Statement st) throws Exception {
	        ResultSet rst = null;
	        String record = "";
	        try {
	            rst = st.executeQuery(query);
	            while (rst.next()) {
	                String data = rst.getString(1);
	                if (rst.wasNull()) {
	                    data = "";
	                }
	                record = data;
	            } //while
	        } catch (SQLException sqle) {
	        	logger.error(sqle.getMessage(),sqle);
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.error(nfe.getMessage(),nfe);
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.error(iae.getMessage(),iae);
	            throw iae;
	        }
	        return record;
	    }
	    public  ArrayList executeQuery(String query,Statement st) throws Exception {
	    	logger.debug("DBUtility - executeQuery(String query)" + query);
	    	  ResultSet rst = null;
	        ArrayList list = new ArrayList();
	        try {
	            rst = st.executeQuery(query);
	            ResultSetMetaData rsmd = rst.getMetaData();
	            int col_count = rsmd.getColumnCount();
	            while (rst.next()) {
	                ArrayList row_list = new ArrayList();
	                for (int i = 1; i <= col_count; i++) {
	                    String temp = rst.getString(i);
	                    if (rst.wasNull()) {
	                        temp = "";
	                    }
	                    row_list.add(temp);
	                }
	                list.add(row_list);
	            }
	        } catch (SQLException sqle) {
	        	logger.error(sqle.getMessage(),sqle);
	            throw sqle;
	        } catch (NumberFormatException nfe) {
	        	logger.error(nfe.getMessage(),nfe);
	            throw nfe;
	        } catch (IllegalArgumentException iae) {
	        	logger.error(iae.getMessage(),iae);
	            throw iae;
	        }
	        list.trimToSize();
	        return list;
	    }
		  public  synchronized ArrayList getTreasuryData (String crn)throws NullPointerException, SQLException,Exception{
			   boolean transactionflag = false; 
			   ArrayList recordList = new ArrayList();
			 //  ArrayList comp = new ArrayList();

			   
			  		
			  // DBUtility transmgtUtil = null;
			   DBUtility dbUtil = null;
			   try{  	
				   dbUtil = new DBUtility();
			   String[] param={crn};
			  // transmgtUtil.setAutoCommit(false);
			   String challanSql = CommonSQL.TREASURY_URL_DATA;
			   dbUtil.createPreparedStatement(challanSql);  
			   recordList = dbUtil.executeQuery(param);
			  
			  
			   }catch (NullPointerException ne) {
				   dbUtil.rollback();
				   //throw ne;
				   logger.error("SQL Exception at getTreasuryData  in DAO " + ne); 	
			    }
		   	    catch (SQLException se) {
		   	    	dbUtil.rollback();
		   	    	
		   		logger.error("SQL Exception at getTreasuryData  in DAO " + se); 
		   	 throw se;
				}
			    catch(Exception e){
			    	//String msg = e.getMessage();
			    	//if (msg.equalsIgnoreCase("20000")||msg.equalsIgnoreCase("20001")||msg.equalsIgnoreCase("20002")||msg.equalsIgnoreCase("20003")){
			    	//	throw e;
			    	//}else{
			    	dbUtil.rollback();
				logger.error(" Exception at getTreasuryData in DAO " + e);
				//throw e;
			   // }
			    }
			    finally {
					 try{	    
						 dbUtil.closeConnection();
					 }
					 catch (Exception e) {
					 logger.error("Exception at getTreasuryData in DAO  " + e);
					 }		
			        }
			    //eform.setTransId(transId);
			    return recordList;   
			  }
	    
		  public ArrayList getSwapRecordList(String parentTbl,String licNo,String funID,Connection conn) throws Exception{
			  ArrayList swapList=new ArrayList();
			  String swapCheck="";
			  Statement st=null;
			  if(funID.equalsIgnoreCase("FUN_022")|| funID.equalsIgnoreCase("FUN_023")){
			  		
			  		swapCheck="SELECT b.sr_no,a.payment_txn_id,b.paid_amount,b.license_no victim,c.license_number actual_payee,b.created_date,credit_or_Debit"+
			  					" FROM "+ parentTbl +" A,igrs_credit_limit_txn_estamp B,igrs_sp_detls c " +
			  					" WHERE a.payment_txn_id=b.payment_txn_id and a.created_by=c.created_by and b.license_no!=c.license_number and applctn_status=8 and b.payment_flag='C' "+
			  					" and c.status='A' and to_char(b.CREATED_DATE,'DD-MON-YY') = to_char(sysdate,'DD-MON-YY')"+
			  					" and (b.license_no='"+licNo +"' or c.license_number='"+licNo+"')";
			  		
			  									  		
			  	  }else{
			  		swapCheck="SELECT b.sr_no,a.payment_txn_id,b.paid_amount,b.license_no victim,c.license_number actual_payee,b.created_date,credit_or_Debit"+
					" FROM "+ parentTbl +" A,IGRS_CREDIT_LIMIT_TXN_LOG B,igrs_sp_detls c " +
					" WHERE a.payment_txn_id=b.payment_txn_id and a.created_by=c.created_by and b.license_no!=c.license_number and applctn_status=8 and b.payment_flag='C' "+
					" and c.status='A' and to_char(b.CREATED_DATE,'DD-MON-YY') = to_char(sysdate,'DD-MON-YY')"+
					" and (b.license_no='"+licNo +"' or c.license_number='"+licNo+"')";
		
	
			  	  }
			  	  st=conn.createStatement();
			  	  
			  	  
				   swapList=executeQuery(swapCheck, st);
				  
				return swapList;
		  }
		  
		
		 
		  public boolean deleteSwapData(String strParentTbl,String strLicNo,String strFunID,Connection conn,String strSrNo,
				  String strPtid,String strPaidAmount,String strlicenseNo,String parentColmName,String parntKey) throws Exception{
			  ArrayList swapList=new ArrayList();
			  String swapCheck="";
			  Boolean boo=false;
			  PreparedStatement pstmt=null;
			  Statement st=null;		  
			  String SQL2 = "UPDATE "+ strParentTbl +" SET PAYMENT_TXN_ID= null , PAID_AMOUNT= null , PAYMENT_MODE_ID= null ,  PAYMENT_FLAG = 'I', UPDATE_BY=null, UPDATE_DATE=null WHERE "+
			  				parentColmName+" = '"+parntKey+"' AND PAYMENT_FLAG = 'P' and PAYMENT_TXN_ID='"+strPtid+"'";
// dbUtil.createStatement();
//   transactionflag=dbUtil.executeUpdate(SQL2);
			 
 
			  if(strFunID.equalsIgnoreCase("FUN_022")|| strFunID.equalsIgnoreCase("FUN_023")){
			  		
			  		swapCheck=CommonSQL.DELETE_FROM_CREDIT_LIMIT_ESTAMP;
			  	
			  }else{
				  
			  		swapCheck=CommonSQL.DELETE_FROM_CREDIT_LIMIT_LOG;
			  	  }
		
			  String[] strQueryArg = new String[7];
			  strQueryArg [0] = strParentTbl; 
			  strQueryArg [1] = strLicNo; 
			  strQueryArg [2] = strFunID; 
			  strQueryArg [3] = strSrNo; 
			  strQueryArg [4] = strPtid; 
			  strQueryArg [5] = strPaidAmount;
			  strQueryArg [6] = strlicenseNo; 
			
			  st=conn.createStatement();
			  boo=executeUpdateDBCreateStmt(SQL2, st);
			  if(boo){
				  pstmt=conn.prepareStatement(CommonSQL.DELETE_FROM_PAY_TXN_MAP);
				  boo=executeUpdateDB(new String[]{strPtid}, pstmt);	 
				  if(boo){
					  pstmt=conn.prepareStatement(CommonSQL.DELETE_FROM_PAY_TXN_DETAILS);
					  boo=executeUpdateDB(new String[]{strPtid}, pstmt);
						  if(boo){
							  pstmt=conn.prepareStatement(swapCheck);
							  boo=executeUpdateDB(new String[]{strSrNo,strLicNo,strPtid,strPaidAmount}, pstmt);
						  if(boo){
							  conn.commit();
						  }else
						  {
							  conn.rollback();
						  }
						 
						  } 
			  }
		  }
				return boo;
		  }
		    /**
	       * ===========================================================================
	       * Method         :   getPaidAmount()
	       * Description    :   Returns String of Amount Paid. 
	       * Arguments      :   transID
	       * return type    :   String
	       * ===========================================================================
	       */  
	  	public String getPaidAmount(String transID){
	  		String[]arry=new String[1];
	  		arry[0]=transID;
	  		String FunName=null;
	  		//DBUtility dbUtils = null;
	  		DBUtility dbUtil = null;
	  		try {
	  			dbUtil = new DBUtility();
	  			
	  				dbUtil.createPreparedStatement(CommonSQL.PAID_AMOUNT);
	  			
	  			FunName = dbUtil.executeQry(arry);
	  			logger.debug("the value in getPaidAmount is " + FunName);
	  		} catch (Exception x) {
	  			logger.error("Exception in getPaidAmount(): " + x);
	  			
	  		} finally {
	  			try {
	  				dbUtil.closeConnection();
	  			} catch (Exception ex) {
	  				logger.error("Exception in closing connection" + ex);
	  				
	  			}
	  		}
	  		return FunName;
	  	}
	    /**
	       * ===========================================================================
	       * Method         :   getPaidAmount()
	       * Description    :   Returns String of Amount Paid. 
	       * Arguments      :   transID
	       * return type    :   String
	       * ===========================================================================
	       */  
	  	public String getPayableAmount(PaymentForm eForm){
	  		String funID=eForm.getFuncId();
	  		String payableAmt=null;
	  		//DBUtility dbUtils = null;
	  		String parentColName="";
	  		DBUtility dbUtil = null;
	  		
	  		
	  		
	  		try {
	  			dbUtil = new DBUtility();
	  	  	  if("FUN_232".equalsIgnoreCase(funID)||"FUN_230".equalsIgnoreCase(funID)||"FUN_064".equalsIgnoreCase(funID)||"FUN_063".equalsIgnoreCase(funID))
	  	  		parentColName="TOTAL_FEE";
	  	  	  else
	  	  		parentColName="PAYABLE_AMOUNT";
	  	  	  
			  	 String licCheckSQL = "SELECT "+parentColName+" FROM "+ eForm.getParentTable() +
	   				" WHERE "+eForm.getParentColumnName()+" = '"+eForm.getParentKey()+"'";
	   
			  	dbUtil.createStatement();
			  	payableAmt = dbUtil.executeQry(licCheckSQL);
	  			logger.debug("the value in getPayableAmount is " + payableAmt);
	  		} catch (Exception x) {
	  			logger.error("Exception in getPayableAmount(): " + x);
	  			
	  		} finally {
	  			try {
	  				dbUtil.closeConnection();
	  			} catch (Exception ex) {
	  				logger.error("Exception in closing connection" + ex);
	  				
	  			}
	  		}
	  		return payableAmt;
	  	}
	    /**
	     * ===========================================================================
	     * Method         :   getPayDtls()
	     * Description    :   Returns list carrying inserted details of downloaded challan. 
	     * Arguments      :   unique id
	     * return type    :   ArrayList
	     * ===========================================================================
	     */  public ArrayList getPayDtls(PaymentForm eForm) throws Exception{
			//	String[] tid = new String[1];
			//	tid[0]=uniquID;
				//DBUtility dbUtils1 = null;
	    	 String colName="";
				ArrayList list = new ArrayList();
				DBUtility dbUtil = null;
				try{
					dbUtil = new DBUtility();
					//As discused with Manager and TL implementing hard coding for column names
					if("IGRS_REG_PAYMENT_DETLS".equalsIgnoreCase(eForm.getParentTable()))
							colName="REG_TXN_ID";
					
				 	 String licCheckSQL = "SELECT '123', max(PAYABLE_AMOUNT), sum(nvl(PAID_AMOUNT,0)), 'P' FROM "+ eForm.getParentTable() +
				     "WHERE ESTAMP_TXN_ID = ? ";
		   
				 	dbUtil.createStatement();
				 	list = dbUtil.executeQuery(licCheckSQL);
		          }  catch (Exception x) {
						logger.debug(x);
					} finally {
						if (dbUtil != null) {
							dbUtil.closeConnection();
						}
					}list.trimToSize();
				return list;
			}
	     
	     
	     public boolean validateCRN(String userID,PaymentForm eForm){
				logger.debug("IN DAO...for validateCRN");
				      boolean flag = true;  
				      DBUtility dbUtil = null;
				      String sql="";
				      ArrayList recordList = new ArrayList();
				      ArrayList record = new ArrayList();
				     
				      try {
					  
					   dbUtil = new DBUtility();
					  
						   sql=CommonSQL.VALIDATE_CRN;  
						dbUtil.createPreparedStatement(sql);
						//ArrayList list = dbUtil.executeQuery(new String[]{url,crn});
					//	flag=dbUtil.executeUpdate(new String[]{crn});
						//ArrayList list = dbUtil.executeQuery(new String[]{crn});
					String crnUser=dbUtil.executeQry(new String[]{eForm.getCrnNumber()});
						if(crnUser.equalsIgnoreCase(userID)){
								flag=true;
						}
						else{
								flag=false;	

						  		 //INSERTING THE RECORD ON DUMMY TABLE FOR SWAPPED RECORD

								 //	transId=ptid;
							   String[] spTableD = new String[8];
							   spTableD [0] = userID;
							   spTableD [1] = "";
							   spTableD [2] = "";
							   spTableD [3] = "";
							   spTableD [4] = "";
							   spTableD [5] = eForm.getParentAmount(); 
							   spTableD [6] = eForm.getFuncId(); 
							   spTableD [7] = eForm.getCrnNumber(); 
							//	dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG);
								//dbUtil.executeUpdate(spTable);
							   dbUtil.createPreparedStatement(CommonSQL.INSERT_MULTIPLE_PAY_LOG_SWAP_ONLINE);
							  boolean transactionflag= dbUtil.executeUpdate(spTableD);
								
								 if(transactionflag){
									// dbUtil.commit();
									 dbUtil.commit();
								 }
								
								
						  
							 
								
							  
						}
				            }
				      catch (Exception x)
				      {
				    	  logger.error("Exception ) :- " + x);
				    	  x.printStackTrace();
				      }
				      finally
				      {
				            try 
				            {
				              dbUtil.closeConnection();

				            } 
				            catch (Exception ex) 
				            {
				            	 logger.error("Exception  :-" + ex);
				            }
				        }
				         return flag;
				    }
	     
	     
	     
	     public String getCashPaymentDetails(String[] param)throws Exception{
		    	
		    	String amt="";
		        ArrayList mainlist =new ArrayList();
		        DBUtility dbUtil = null;
		    	try {
		    		dbUtil = new DBUtility();
		    		logger.debug("before inserting cash data");
		    		//DBUtility db = new DBUtility();
		    		dbUtil.setAutoCommit(false);
		    		dbUtil.createPreparedStatement(CommonSQL.Cash_Amount);
		    		mainlist=dbUtil.executeQuery(param);
		                   	       
		    		
		                   	        
		                   	     if(mainlist!=null) {
		 							for(int i = 0; i < mainlist.size(); i++){
		 								ArrayList lst = (ArrayList) mainlist.get(0);
		 								amt = lst.get(0).toString();
		 								
		 								
		 							}
		                   	     }
		            logger.debug("after inserting ");

		        	}
		    	catch (Exception x) {
		    		logger.error("Exception in addCombineDetails(): " + x);
		    	   throw x;		
		        }
		    	finally {
		            try {
		              		dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	logger.error("Exception in addCombineDetails(): " + ex);
		            }
		        }
		        return amt;
		 }
}  // DAO CLOSE

	

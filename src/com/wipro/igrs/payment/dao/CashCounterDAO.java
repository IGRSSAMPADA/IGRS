package com.wipro.igrs.payment.dao;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.db.DBUtility;

import com.wipro.igrs.payment.sql.CommonSQL;

import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.payment.dto.CashCounterDTO;
import com.wipro.igrs.payment.form.CashCounterForm;
import com.wipro.igrs.payment.form.EODChallanForm;
import com.wipro.igrs.reginit.constant.RegInitConstant;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
/**
 * ===========================================================================
 * File           :   CashCounterDAO.java
 * Description    :   Represents the Cash Payment DAO Class
 * Author         :   vengamamba P
 * Created Date   :    Feb 25, 2008

 * ===========================================================================
 */
public class CashCounterDAO {
	ArrayList list = null;
	String query;
	DBUtility dbUtil=null; // obj for dbutility
	private Logger logger = (Logger) Logger.getLogger(CashCounterDAO.class);
	private CallableStatement clstmt;
	public CashCounterDAO() throws Exception {
		//dbUtil = new DBUtility(); // creating connection to db

	}
	
	
	/**
     * ===========================================================================
     * Method         :   getDistrictId
     * Description    :   Returns String of districtid.
     * Arguments      :   officeid
     * return type    :   String
     * ===========================================================================
     */  
	public String getDistrictId(String oid){
		String did=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={oid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_DISTRICT_ID;
			//query=query+" '"+oid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			did = dbUtil.executeQry(param);
			//logger.debug("the value in String is " + did);
			
		} catch (Exception x) {
			logger.error("Exception in getDistrictid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return did;
	}
	/**
     * ===========================================================================
     * Method         :   getDistrictName
     * Description    :   Returns String of districtid.
     * Arguments      :   officeid
     * return type    :   String
     * ===========================================================================
     */  
	public String getDistrictName(String oid,String languageLocale){
		String did=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={oid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			query = CommonSQL.CASH_DISTRICT_NAME;
			}else{
				query = CommonSQL.CASH_DISTRICT_NAME_HINDI;
			}
			//query=query+" '"+oid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			did = dbUtil.executeQry(param);
			//logger.debug("the value in String is " + did);
			
		} catch (Exception x) {
			logger.error("Exception in getDistrictid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return did;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getempName
     * Description    :   Returns String of emp name.
     * Arguments      :  user id
     * return type    :   String
     * ===========================================================================
     */  
	public String getempName(String uid){
		String did=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={uid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_EMP_NAME;
			//query=query+" '"+uid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			did = dbUtil.executeQry(param);
			//logger.debug("the value in String is " + did);
			
		} catch (Exception x) {
			logger.error("Exception in getDistrictid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return did;
	}
	/**
     * ===========================================================================
     * Method         :   getDetailheadId
     * Description    :   Returns String of detailheadid.
     * Arguments      :   functionid
     * return type    :   String
     * ===========================================================================
     */  
	public String getDetailheadId(String fid){
		String did=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={fid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_DETAILHEAD_ID;
			//query=query+" '"+fid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			did = dbUtil.executeQry(param);
			//logger.debug("the value in String is " + did);
		} catch (Exception x) {
			logger.error("Exception in getDetailedheadid(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return did;
	}
	
	/**
     * ===========================================================================
     * Method         :   getOfficeid()
     * Description    :   Returns String of officeid. 
     * Arguments      :   userid
     * return type    :   String
     * ===========================================================================
     */  
	public String getOfficeid(String uid) throws Exception{
		String oid=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={uid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_OFFICE_ID;
			//query=query+" '"+uid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			oid = dbUtil.executeQry(param);
			//logger.debug("the value in officeid is " + oid);
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
     * Method         :   getSum()
     * Description    :   Returns the sum 
     * Arguments      :   office id
     * return type    :   String
     * ===========================================================================
     */  
	public String getSum(String oid){
		String sum=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={oid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_SUM;
			//query=query+" '"+oid+"'"+" "+"AND TO_CHAR(CREATED_DATE, 'DD-MON-YY') = TO_CHAR(SYSDATE, 'DD-MON-YY')";
			//logger.debug("the query is  in DAO   " + query);
			//System.out.println("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			sum = dbUtil.executeQry(param);
			if (sum == ""){
				sum = "0";
			}
			//logger.debug("the value in SUM is " + sum);
		} catch (Exception x) {
			logger.error("Exception in getSum(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return sum;
	}
	
	/**
     * ===========================================================================
     * Method         :   getLimit()
     * Description    :   Returns the limit
     * Arguments      :   office id
     * return type    :   String
     * ===========================================================================
     */  
	public String getLimit(String oid){
		String limit=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={oid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_LIMIT;
			//query=query+" '"+oid+"'";
			//logger.debug("the query is  in DAO   " + query);
			//System.out.println("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			limit = dbUtil.executeQry(param);
			//logger.debug("the value in limit is " + limit);
		} catch (Exception x) {
			logger.error("Exception in getLimit(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return limit;
	}
	
	/**
     * ===========================================================================
     * Method         :   getFuncName()
     * Description    :   Returns String of function name. 
     * Arguments      :  functionid
     * return type    :   String
     * ===========================================================================
     */  
	public String getFuncName(String fid) throws Exception{
		String oid=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={fid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_FUNCTION_NAME;
			//query=query+" '"+fid+"'";
			//logger.debug("the query is  in DAO   " + query);
			dbUtil.createPreparedStatement(query);
			oid = dbUtil.executeQry(param);
			//logger.debug("the value in officeid is " + oid);
		} catch (Exception x) {
			
			logger.error("Exception in getOfficeid(): " + x);
			throw x;
			
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
     * Method         :   getPaymode()
     * Description    :   Returns String of paymode name. 
     * Arguments      :  paymentid
     * return type    :   String
     * ===========================================================================
     */  
	public String getPaymode(String fid)throws Exception{
		String mode=null;
		
		try {
			dbUtil = new DBUtility();
			String[] param={fid};          // createStatement converted to createPreparedStatement by Roopam: 7 Oct 2013.
			query = CommonSQL.CASH_PAYMENT_MODE_NAME;
			//query=query+" '"+fid+"'";
			//logger.debug("the query is  in DAO   " + query);
			//System.out.println("the querry is in dao...."+query);
			dbUtil.createPreparedStatement(query);
			mode = dbUtil.executeQry(param);
			//logger.debug("the value in paymentmode is " + mode);
		} catch (Exception x) {
			logger.error("Exception in paymentmode(): " + x);
			throw x;
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return mode;
	}
	
	
	/**
     * ===========================================================================
     * Method         :   getPhotoIDList()
     * Description    :   Returns list  of photoids and its values. 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getPhotoIdList() {

		try {
			dbUtil = new DBUtility();
			//query = CommonSQL.CASH_PHOTOID_LIST;
			query = CommonSQL.CASH_PHOTOID_LIST_HINDI;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	/**
     * Method 		: getServiceList
     * Description	: returns list of function ids whose payment flag=Y 
     * @param query : 
     * return Type  :ArrayList
     */
	public ArrayList getServiceList() {

		try {
			dbUtil = new DBUtility();
			//query = CommonSQL.CASH_FUNCTIONID_LIST;
			query = CommonSQL.CASH_FUNCTIONID_LIST_HINDI;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in funcidlist(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	/**
     * ===========================================================================
     * Method         :   getbankidList()
     * Description    :   Returns list  of bankids and its names  . 
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getBankidList() {

		try {
			dbUtil = new DBUtility();
			query = CommonSQL.BANK_ID_LIST;
			logger.debug("the query is  in DAO   " + query);
			dbUtil.createStatement();
			list = new ArrayList();
			list = dbUtil.executeQuery(query);
			logger.debug("the values in list is  " + list);
		} catch (Exception x) {
			logger.error("Exception in getBankidList(): " + x);
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return list;
	}
	
	
	
	

	/**
     * ===========================================================================
     * Method         :   getTransPrintList()
     * Description    :   Returns list  of transaction ids
     * Arguments      :   
     * return type    :   Arraylist
     
     * ===========================================================================
     */  
	public ArrayList getTransPrintList(String comid) throws Exception{
         ArrayList mainlist = new ArrayList();
         String []arry=new String[1];
         arry[0]=comid;
		try {
			dbUtil = new DBUtility();
			/*query = "select P.FIRST_NAME||' '||P.MIDDLE_NAME||' '||P.LAST_NAME,  P.TRANSACTION_ID, P.GROSS_AMOUNT,"+
			        " to_char(to_date(P.CREATED_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'), "+
			        " F.FUNCTION_NAME  from  IGRS_PAYMENT_MODE_DETAILS P, IGRS_FUNCTION_MASTER F"+
	                " where COMBINE_ID = '"+comid+"' AND P.FUNCTION_ID = F.FUNCTION_ID";*/
			/*query = "select P.FIRST_NAME||' '||P.MIDDLE_NAME||' '||P.LAST_NAME,  P.TRANSACTION_ID, P.GROSS_AMOUNT,"+
	                " P.CREATED_DATE, "+
	                " F.FUNCTION_NAME  from  IGRS_PAYMENT_MODE_DETAILS P, IGRS_FUNCTION_MASTER F"+
                    " where COMBINE_ID = '"+comid+"' AND P.FUNCTION_ID = F.FUNCTION_ID";
			logger.debug("the query is  in DAO   " + query);*/
			//dbUtil.createPreparedStatement(CommonSQL.TRANS_QUERY);
			dbUtil.createPreparedStatement(CommonSQL.TRANS_QUERY_HINDI);
			mainlist=dbUtil.executeQuery(arry);
			/*dbUtil.createStatement();
			dbUtil.createPreparedStatement(CommonSQL.PRINT_TRANS_DETAILS);
			list = new ArrayList();
			mainlist = dbUtil.executeQuery(query);*/
			logger.debug("the values in list is  " + mainlist);
		} catch (Exception x) {
			logger.error("Exception in photoidlist(): " + x);
			throw x;
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return mainlist;
	}
	
	public ArrayList getTransPrintListView(String transId) throws Exception{
        ArrayList mainlist = new ArrayList();
        String []arry=new String[1];
        arry[0]=transId;
		try {
			dbUtil = new DBUtility();
			
			dbUtil.createPreparedStatement(CommonSQL.TRANS_QUERY_HINDI_VIEW);
			mainlist=dbUtil.executeQuery(arry);
			
			logger.debug("the values in list is  " + mainlist);
		} catch (Exception x) {
			logger.error("Exception in getTransPrintListView(): " + x);
			throw x;
			
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception ex) {
				logger.error("Exception in closing connection" + ex);
				
			}
		}
		return mainlist;
	}
	/* Method Name : getChallanTxn
     *  Arguments   :String Array[] and Amount
     *  Return      : if it success return Transaction ID
     *               other wise return fail 
     *  Exception 	: NullPointer  or SQLException or Exception*/
	public String getChallanTxn(String[] _challan,double amt) throws NullPointerException,Exception {			
		String txnNo = null;
		String payTxnID = null;
		String returnFlag = null;
		//DBUtility challnTxnDbUtility = null;
		//dbUtil = new DBUtility();
		try{
			dbUtil = new DBUtility();
		String challanTxnNo = CommonSQL.CHALLAN_VALID + "  SCROLL_NUMBER LIKE   '" + _challan[0] + "'" +
				" AND GROSS_AMOUNT =  '" + amt + "' AND trunc(CHALLAN_ONLINE_DATE) = to_date('"+_challan[1]+"','dd/mm/yyyy')" ;			
		dbUtil.createStatement();		
        txnNo = dbUtil.executeQry(challanTxnNo);        
        logger.debug("transaction="+txnNo);
        if(txnNo == null || txnNo.equalsIgnoreCase("")){			
			returnFlag=null;
        }
        else{
        	returnFlag = txnNo;
        }
		}catch(NullPointerException ne){
			 logger.error(" NullException at getChallanDetails  in DAO " + ne);
		}catch (SQLException se) {
			  logger.error(" Exception at getChallanDetails in DAO " +  se);
		}
		catch (Exception e) {
			   logger.error(" Exception at getChallanDetails  in DAO " + e);			
		}
		finally{
			try{
				dbUtil.closeConnection();
			}			
			catch (SQLException se) {
			  logger.error(" Exception at getChallanDetails close Connection  in DAO " +  se);
			}
			catch (Exception e) {
		      logger.error(" Exception at getChallanDetails close Connection in DAO " + e);
			}
			
		}
		return returnFlag;
	}
	/**
     * Method 		: addCombineDetails
     * Description	: returns boolean gives success of insert or not
     * @param query :  array of string ,
     * return Type  :boolean
     */
	 public boolean addCombineDetails(String[] param)throws Exception{
	    	
	    	boolean insertflag = false;	
	        
	    	try {
	    		dbUtil = new DBUtility();
	    		logger.debug("before inserting cash data");
	    		//DBUtility db = new DBUtility();
	    		dbUtil.setAutoCommit(false);
	    		dbUtil.createPreparedStatement(CommonSQL.COMMON_INSERT);
	            insertflag = dbUtil.executeUpdate(param);
	                   	             
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
	        return insertflag;
	 }
	
	
	
	/**
     * Method 		: addCashDetails
     * Description	: returns boolean gives success of insert or not
     * @param query :  array of string ,
     * return Type  :boolean
     */
	 public boolean addCashDetails(String[] param)throws Exception{
	    	
	    	boolean insertflag = false;	
	        
	    	try {
	    		dbUtil = new DBUtility();
	    		logger.debug("before inserting cash data");
	    		//DBUtility db = new DBUtility();
	    		dbUtil.setAutoCommit(false);
	    		dbUtil.createPreparedStatement(CommonSQL.CASH_INSERT);
	            insertflag = dbUtil.executeUpdate(param);
	                   	             
	            logger.debug("after inserting ");

	        	}
	    	catch (Exception x) {
	    		logger.error("Exception in addCashDetails(): " + x);
	    	   		
	        }
	    	finally {
	            try {
	              		dbUtil.closeConnection();
	            } catch (Exception ex) {
	            	logger.error("Exception in addTransidDetails(): " + ex);
	            }
	        }
	        return insertflag;
	 }
	 
	 
	 
	 
	 /**
	     * Method 		: addFinalCashDetails
	     * Description	: returns boolean gives success of insert or not
	     * @param query : array of string ,
	     * return Type  :boolean
	     */
		 public boolean addFinalCashDetails(CashCounterForm form, HashMap map, String uid)throws Exception{
		    	
		    	boolean insertflag = false;	
		    	IGRSCommon common=new IGRSCommon();
		        //HashMap m1 = null;
		        Collection col=map.values();
				Object[] objary=col.toArray();
				String[] 	param 		= new String[28];
				String[] 	param2 		= new String[1];
				CashCounterDTO cashdto1 = new CashCounterDTO() ;
				try {
					dbUtil = new DBUtility();
					dbUtil.setAutoCommit(false);
		    		for(int n=0;n<objary.length;n++){
					cashdto1=(CashCounterDTO)objary[n];
					String tid = common.getSequenceNumber("IGRS_PAYMENT_TXNID","cash" );
					//String txnid	  =	form.getChallanDTO().getTxnid();
					 String ddate	  =	form.getChallanDTO().getCurrentdate();
					 String fname	  =	form.getFirstName();
					 String mname	  =	form.getMiddleName();
					 String lname	  =	form.getLastName();
					 String gender	  =	form.getGender();
					 String age	      =	form.getAge();
					 String gname	  =	form.getFatherName();
					 String pid	      =	form.getListID();
					 String pidno	  =	form.getIdNo();
					 String bankname  =	form.getBankName();
					 String bankaddr  =	form.getBankAddr();
					 String serviceid =	cashdto1.getListService();
					 String amount	  =	cashdto1.getServFee(); 
					 //String mjrHead	  =	cashdto1.getRevenueMjrHd(); 
					 //String sbmjrHead	  =	cashdto1.getRevenueSubMjrHd(); 
					// String mnrHead	  =	cashdto1.getRevenueMnrHd(); 
					 String remarks	  =	form.getRemarks();
					 String oid	      = form.getOffId();
					 String ofName    = form.getOffName();
					 String did	      = form.getDistId();
					 String comid     = form.getCombId();
					 param[0]	=	tid; 
					 param[1]	=	amount;
					 param[2]	=	amount;
					 param[3]	=	uid;
					 param[4]	=	uid;
					 param[5]	=	fname;
					 param[6]	=	mname;
					 param[7]	=	lname;
					 param[8]	=	gender;		
					 param[9]	=	age;
					 param[10]	=	pid;
					 param[11]	=	pidno;
					 param[12]	=	gname;		 
					 if (form.getCheckboxAssociateChallan()== null)
						 param[13]	=	"1";
					 else
						 param[13]	=	"2";
							 
					 param[14]	=	bankname;
					 param[15]	=	bankaddr;
					 param[16]	=	oid;
					 param[17]	=	uid;
					 param[18]	=	did;
					 param[19]	=	serviceid;
					 param[20]	=	remarks;
					 param[21]	=	"A";
					 param[22]  =    comid;
					 param[23]  =    serviceid; 
					 param[24]  =    serviceid; 
					 param[25]  =    serviceid; 
					 param[26]  =    form.getPosRefNo(); 
					 param[27]  =    form.getOldNewReceipt();
					 param2[0]  =    comid;
		    		logger.debug("before inserting cash data");
		    		//logger.debug("before inserting"+ n +" RevMjrHd is............................................."+mjrHead);
					//logger.debug("before inserting"+ n +" RevSubMjrHd is............................................."+sbmjrHead);
					//logger.debug("before inserting"+ n +" RevMnrHd is............................................."+mnrHead);
		    		//DBUtility db = new DBUtility();
		    		dbUtil.createPreparedStatement(CommonSQL.CASH_INSERT);
		            dbUtil.executeUpdate(param);
		            

		    		}
		    		dbUtil.createPreparedStatement(CommonSQL.CASH_UPDATE);
		            dbUtil.executeUpdate(param2);
		            insertflag = true;
		            logger.debug("after inserting ");
		            dbUtil.commit();
		    		}
		    	catch (Exception x) {
		    		dbUtil.rollback();
		    		logger.error("Exception in addCashDetails(): " + x);
		    		throw x;
		    	   		
		        }
		    	finally {
		            try {
		              		dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	logger.error("Exception in addTransidDetails(): " + ex);
		            }
		        }
		        return insertflag;
		 }
		 
		 
		 
		 /**
			 * @param _retFunId
			 * @param _serId
			 * @param _userType
			 * @return
			 * @throws Exception
			 */
			public ArrayList getOthersFeeDuty(String _retFunId, String _serId,
					String _userType) throws Exception {
				ArrayList othersFeeDuty = new ArrayList();
				//System.out.println("Inside getOthersFeeDuty()");
				try {
					dbUtil = new DBUtility();
					clstmt = dbUtil
							.returnCallableStatement(CommonSQL.SERVICE_FEE_PROCEDURE);
					clstmt.setString(1, _retFunId);
					clstmt.setString(2, _serId);
					clstmt.setString(3, _userType);
					clstmt.registerOutParameter(4, OracleTypes.NUMBER);
					clstmt.registerOutParameter(5, OracleTypes.CLOB);
					clstmt.registerOutParameter(6, OracleTypes.VARCHAR);
					if (!clstmt.execute()) {
						int temp = clstmt.getInt(4);
						othersFeeDuty = new ArrayList();
						String serviceFeeStr = String.valueOf(temp);
						double totalFee = Double.parseDouble(serviceFeeStr);
						double fee = 0.0;
						double otherFee = 0.0;
						String SerVal = clstmt.getString(5);
						String[] serTokenOne = SerVal.split(";");
						if (serTokenOne.length > 0) {
							for (int i = 1; i < serTokenOne.length; i++) {
								String[] serTokenTwo = serTokenOne[i].split(",");
								if (serTokenTwo.length > 0) {
									if (i == 1) {
										fee = Double.parseDouble(serTokenTwo[4]);
									}
								}
							}
						}
						othersFeeDuty.add("" + fee);
						otherFee = totalFee - fee;
						othersFeeDuty.add("" + otherFee);
						othersFeeDuty.add("" + totalFee);

					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
				} finally {

					dbUtil.closeConnection();
				}
				//System.out.println("othersFeeDuty:-" + othersFeeDuty);
				return othersFeeDuty;
			}

	 /**
	     * Method 		: addTransidDetails
	     * Description	: returns boolean gives success of insert or not
	     * @param query :  array of string ,
	     * return Type  :boolean
	     */
		 public boolean addTransidDetails(String[] param)throws Exception{
		    	
		    	boolean insertflag = false;	
		        
		    	try {
		    		dbUtil = new DBUtility();
		    		logger.debug("before inserting TID data ");
		    		
		            dbUtil.createPreparedStatement(CommonSQL.Tid_INSERT);
		            insertflag = dbUtil.executeUpdate(param);
		           
		             
		            logger.debug("after inserting ");

		        	}
		    	catch (Exception x) {
		    		logger.error("Exception in addTransidDetails(): " + x);
		    		
		    		
		        }
		    	finally {
		            try {
		            	
		            		dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	logger.error("Exception in addTransidDetails(): " + ex);
		            }
		        }
		        return insertflag;
		 }
		 /**
		     * Method 		: updateStatus
		     * Description	: returns boolean gives success of update or not
		     * @param query :  array of string ,
		     * return Type  :boolean
		     */
			 public boolean updateStatus(String[] param)throws Exception{
			    	
			    	boolean insertflag = false;	
			        
			    	try {
			    		dbUtil = new DBUtility();
			    		logger.debug("before inserting update status ");
			    		
			            dbUtil.createPreparedStatement(CommonSQL.STATUS_UPDATE);
			            insertflag = dbUtil.executeUpdate(param);
			           
			            
			             
			            logger.debug("after inserting ");

			        	}
			    	catch (Exception x) {
			    		logger.error("Exception in updateStatus(): " + x);
			    		
			        }
			    	finally {
			            try {
			            	dbUtil.closeConnection();
			            } catch (Exception ex) {
			            	logger.error("Exception in updateStatus()close connection: " + ex);
			            	
			            }
			        }
			        return insertflag;
			 } 
			 /**
			     * Method 		: commitingTransaction
			     * Description	: commiting whole trans success
			     * @param query : 
			     * @throws 		: Exception
			     * return Type  :void
			     */ 
			 
		    public void commitingTrasanction()throws Exception{
		    	try {
		    		dbUtil = new DBUtility();
		    		logger.debug("before commiting whole transaction ");
		    		dbUtil.commit();
		    	}	 
		    	catch (Exception x) {
		    		logger.error("Exception in commit(): " + x);
		    		
		        }
		    	finally {
		            try {
		            	dbUtil.closeConnection();
		            } catch (Exception ex) {
		            	logger.error("Exception in updateStatus()close connection: " + ex);
		            	
		            }
		        }
		    }
		    /**
		     * Method 		: cancelTransaction
		     * Description	: rollbacking whole trans if anyone fail
		     * @param query : 
		     * @throws 		: Exception
		     * return Type  :void
		     */ 
		 
	    public void cancelTrasanction()throws Exception{
	    	try {
	    		dbUtil = new DBUtility();
	    		logger.debug("before commiting whole transaction ");
	    		dbUtil.rollback(); 
	    	}	 
	    	catch (Exception x) {
	    		logger.error("Exception in rollback(): " + x);
	    		
	        }finally {
	            try {
	            	dbUtil.closeConnection();
	            } catch (Exception ex) {
	            	logger.error("Exception in updateStatus()close connection: " + ex);
	            	
	            }
	        }
	    }
	    /**
	     * ===========================================================================
	     * Method         :   getRevHeadsFee()
	     * Description    :   for retrieving revenue heads and fee applicable for DRS officials 
	     * Arguments      :   String
	     * return type    :   Arraylist
	     * Author         :   ROOPAM MEHTA
	     * Date           :   24 feb 2014
	     
	     * ===========================================================================
	     */  
		public ArrayList getRevHeadsFee(String serviceId) throws Exception{
	         ArrayList mainlist = new ArrayList();
	         String []arry=new String[1];
	         arry[0]=serviceId;
			try {
				dbUtil = new DBUtility();
				dbUtil.createPreparedStatement(CommonSQL.GET_REV_HEAD_FEE);
				mainlist=dbUtil.executeQuery(arry);
				
				logger.debug("the values in list is  " + mainlist);
			} catch (Exception x) {
				logger.error("Exception in getRevHeadsFee(): " + x);
				throw x;
				
			} finally {
				try {
					dbUtil.closeConnection();
				} catch (Exception ex) {
					logger.error("Exception in closing connection" + ex);
					
				}
			}
			return mainlist;
		}
		
		
		 public ArrayList getDetails(CashCounterForm form){
			 ArrayList alist = new ArrayList();
			 StringTokenizer st=new StringTokenizer(form.getChdate(),"/");
	 	     String day=st.nextToken();
	 	     String month=st.nextToken();
	 	     String year=st.nextToken();
			 String param[]=new String[4];
			 param[0]=form.getTransId();
			 param[1]=getOracleDate(day, month, year);
			 
			 
			  st=new StringTokenizer(form.getMinDate(),"/");
	 	     day=st.nextToken();
	 	     month=st.nextToken();
	 	     year=st.nextToken();
			 param[2]=getOracleDate(day, month, year);
			 param[3]=form.getOffId();
			 try{
				 dbUtil = new DBUtility();
				 String sql=CommonSQL.CASH_DETAILS_VIEW;
				 if(form.getTransId()!=null && !("").equalsIgnoreCase(form.getTransId())){
					 sql=CommonSQL.CASH_DETAILS_VIEW_AND;
				 }
					dbUtil.createPreparedStatement(sql);
					alist=dbUtil.executeQuery(param);
					alist.trimToSize();
					} catch(Exception e){
						   e.printStackTrace();
						   try {
							dbUtil.rollback();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						logger.error(" Exception in getDetails " + e);
						try {
							throw e;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						} finally {
							 try{	    
								 if (dbUtil != null) {
									 dbUtil.closeConnection();
							 }
								 }
							 catch (Exception e) {
							 logger.error("Exception in closing connection  " + e);
							 }		
					       }
					 
			 
			 return alist;
		 }
		 
		 public  String getOracleDate(String day,String month,String year) {

			 
			 String inputDate = day + "-" + month + "-" + year;
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			 logger.debug(" " + inputDate + " parses as ");
			 String finalDate = "";
			 Date newDate ;
			 try {
				 newDate = formatter.parse(inputDate);
				 SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
				 finalDate = format.format(newDate);
				 logger.debug(finalDate);

			 	}
			 catch (Exception e) {
				 logger.error(e);
			 }

			 return finalDate;

		}

}

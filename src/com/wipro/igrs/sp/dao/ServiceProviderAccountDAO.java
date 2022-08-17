/**
 * ServiceProviderAccountDAO.java
 */


package com.wipro.igrs.sp.dao;


import java.sql.CallableStatement;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.reginit.sql.RegCommonSQL;
import com.wipro.igrs.sp.dto.ServiceProviderAccountDTO;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.sp.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;


/**
 * @author root
 * 
 */

public class ServiceProviderAccountDAO {

	private static Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderAccountDAO.class);
	/**
	 * Constructor name : ServiceProviderAccountDAO
	 * 
	 */
	DBUtility dbUtil = null;

	ArrayList list = null;
	
	private CallableStatement clstmt;

	/**
	 * Constructor name : ServiceProviderAccountDAO
	 * 
	 * @throws Exception
	 */
	public ServiceProviderAccountDAO() throws Exception {
		dbUtil = new DBUtility();
	}

	/**
	 * Method name :getUserType
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getUserType(String userid) throws Exception {
		try {
			String arrdistid[] = { userid };
			dbUtil.createPreparedStatement(CommonSQL.SELECTSPUSERTYPE);
			list = dbUtil.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return list;
	}

	/**
	 * Method name :getServiceProviderInfo
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderInfo(String userid) throws Exception {
		try {
			String arrdistid[] = { userid, userid };
			//dbUtil.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTINFO);
			dbUtil.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTINFONEW);
			
			list = dbUtil.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return list;
	}
	
	//added by shruti
			public ArrayList getSpAcntDetails(String userid,String licenseno) throws Exception {
				try {
					String arrdistid[] = {userid,licenseno,licenseno};
					dbUtil.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTINFONEW1);
					ArrayList listDetails=new ArrayList();
					listDetails= dbUtil.executeQuery(arrdistid);
					list = dbUtil.executeQuery(arrdistid);
				} catch (Exception exception) {
					logger.info("Exception in getSPusers-SPDAo" + exception);
				}
				return list;
			}
			//end 
			
			//added by shruti
			public ArrayList getSpDetails(String userid) throws Exception {
				try {
					String arrdistid[] = { userid,userid };
					dbUtil.createPreparedStatement(CommonSQL.SELECTSPDETAILS);
					list = dbUtil.executeQuery(arrdistid);
				} catch (Exception exception) {
					logger.info("Exception in getSPusers-SPDAo" + exception);
				}
				return list;
			}
			//end 	
			
			//added by shruti
			public ArrayList getSpBankDetails(String userid) throws Exception {
				try {
					String arrdistid[] = { userid,userid };
					dbUtil.createPreparedStatement(CommonSQL.SELECTSPBANKDETAILS);
					list = dbUtil.executeQuery(arrdistid);
				} catch (Exception exception) {
					logger.info("Exception in getSPusers-SPDAo" + exception);
				}
				return list;
			}
			
	/**
	 * Method name :getServiceProviderBankInfo
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderBankInfo(String userid) throws Exception {
		try {
			String arrdistid[] = { userid, userid };
			dbUtil
					.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERBANKACCOUNTINFO);
			list = dbUtil.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return list;
	}

	/**
	 * Method name :getServiceProviderStmt
	 * 
	 * @param userid
	 * @param accountDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	public ArrayList getServiceProviderStmt(String userid,
			ServiceProviderAccountDTO accountDTO) throws Exception {
		try {
			String lno=accountDTO.getLicencenumber();
			String arrdistid[] = { lno,CommonUtil.getConvertedDate(accountDTO.getFromdate()),
					CommonUtil.getConvertedDate(accountDTO.getTodate())
							};
			//commented by shruti
			/*dbUtil
					.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTSTMT);*/
			//modified by shruti-5Feb 2013
		/*	dbUtil
			.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTSTMTNEW);*/
			/*dbUtil
			.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTSTMTNEW1);*/
			dbUtil
			.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCOUNTSTMTNEW2);
			list = dbUtil.executeQuery(arrdistid);
			logger.info("STATEMENT LIST:----"+list);
		} catch (Exception exception) {
			logger.info("Exception in getServiceProviderStmt" + exception);
		}
		return list;
	}
	
	public String getOpeningBal(String licno) throws Exception
	{
		String openingBal="";
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createStatement();
			String[] licnoDetails={licno,licno};
			dbUtil.createPreparedStatement(CommonSQL.SPOPENINGBALANCE);
			openingBal=dbUtil.executeQry(licnoDetails);
			if(openingBal=="")
			{
				openingBal="0";
			}
			logger.info("OPENING BALANCE----"+openingBal);
		}
		catch(Exception exception)
		{
			logger.info("EXCEPTION IN OPENING BALANCE MEHTOD"+exception);
		}
		return openingBal;
	}
	
	/** 
		 * Method name		:getServiceProviderStmtmonth
		 * @param userid
		 * @param accountDTO
		 * @return
		 * @throws Exception
		 *  
		 */
	public ArrayList getServiceProviderStmtmonth(String userid,
			ServiceProviderAccountDTO accountDTO) throws Exception {
		try {
			String arrdistid[] = { userid, accountDTO.getPaymonth(),
					accountDTO.getPayyear() };
			dbUtil
					.createPreparedStatement(CommonSQL.SELECTSERVICEPROVIDERACCTSTMT);
			list = dbUtil.executeQuery(arrdistid);
		} catch (Exception exception) {
			logger.info("Exception in getSPusers-SPDAo" + exception);
		}
		return list;
	}
	
	//added by shruti for credited amount txn record for SP--------
	public boolean spAcntBalUpdt(ServiceProviderAccountDTO accountDTO,String userid) throws Exception {
		
		boolean flag=false;
		
		try {
			
			dbUtil = new DBUtility();
						String spdtls[]={
					accountDTO.getSrNo(),
					accountDTO.getLicencenumber(),
					//accountDTO.getPaymentdate(),
					//accountDTO.getPaymenttypename(),
					//accountDTO.getTransactionno(),
					//accountDTO.getPayamount(),
					//accountDTO.getTxnpurpose(),
					//accountDTO.getAccountbalance()
					userid,
					"FUN_200"
			};
			//logger.info("@@@@@@"+amount);
			dbUtil.createPreparedStatement(CommonSQL.CREDIT_SP_ACNT);
			flag=dbUtil.executeUpdate(spdtls);
			
		} catch (Exception exception) {
			logger.info("Exception in getNewSPSerialNumber" + exception);
		}
		return flag;

	}
	
	//added by shruti for serial number generation of credit and debit transactions 
	public String getCrdtLimitSerialNumber() throws Exception
	{
		String crdtlmtxnid="";
		try
		{
			dbUtil=new DBUtility();
			dbUtil.createStatement();
			crdtlmtxnid=dbUtil.executeQry(CommonSQL.SELECT_CRDT_LIMIT_TXN_SEQ);
		}
		catch(Exception exception)
		{
			logger.info("Exception in getCrdtLimitSerialNumber" + exception);
		}
		return crdtlmtxnid;
	}
	//added by shruti for debited amt txn record for SP
	
public boolean spDebitAcntBalUpdt(ServiceProviderAccountDTO accountDTO,String userid) throws Exception {
		
		boolean flag=false;
		
		try {
			
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String spdtls[]={
					getCrdtLimitSerialNumber(),
					accountDTO.getLicencenumber(),
					accountDTO.getPaymenttypename(),
					accountDTO.getTransactionno(),
					accountDTO.getPaymentamount(),
					
					accountDTO.getTxnpurpose(),
					accountDTO.getAccountbalance(),
					accountDTO.getSpcomm()
			};
			dbUtil.createPreparedStatement(CommonSQL.DEBIT_SP_ACNT);
			flag=dbUtil.executeUpdate(spdtls);
			if(flag)
			{
				dbUtil.commit();
			}
			else
			{
				dbUtil.rollback();
			}
		} catch (Exception exception) {
			logger.info("Exception in credit limit usage" + exception);
		}
		return flag;

	}
		

public ArrayList spCreditBalance(String lno ) throws Exception
{
try{
	String[] uid={lno,lno};
	dbUtil.createPreparedStatement(CommonSQL.SELECTCREDITBALANCE);
	list = dbUtil.executeQuery(uid);
}
catch(Exception exception){
	logger.info("Exception in getspCreditBalance-DAO" + exception);	
}
finally {
	dbUtil.closeConnection();
}
return list;
}

public ArrayList getLicenseNumber(String userid) throws Exception {
	try {
		String uid[] = { userid };
		dbUtil.createPreparedStatement(CommonSQL.SELECTLICENSENUMBER);
		list = dbUtil.executeQuery(uid);
		
		if(list.size()==0)
		{
			dbUtil.createPreparedStatement(CommonSQL.SELECTLICENSENUMBER1);
			list = dbUtil.executeQuery(uid);	
		}
		
	} catch (Exception exception) {
		logger.info("Exception in get License Number" + exception);
	}
	return list;
}


public String getSpCommFactor() throws Exception {
	String spcommfctr = "";
	try
	{
	dbUtil.createStatement();
	spcommfctr = dbUtil.executeQry(CommonSQL.SELECTSPCOMMFACTOR);
	}
	catch(Exception exception)
	{
		logger.info("Exception in get Sp Comm Factor");
	}
	return spcommfctr;
}

public String getPaymentFlag(String sr_no) throws Exception {

    
    String paymentFlag=null;
    
    try {
    	dbUtil = new DBUtility();
    	dbUtil.createStatement();
    	paymentFlag = dbUtil.executeQry(CommonSQL.SPPAYMENTFLAGSTATUS+"'"+sr_no+"'");
    } catch (Exception exception) {
            logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
    }finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
		}
	}
    return paymentFlag;

}
public boolean getUpdatedPaymentFlag(String sr_no) throws Exception {

    boolean flag=false;
    //String paymentFlag=null;
    
    try {
    	dbUtil = new DBUtility();
    	dbUtil.createStatement();
    	
    	String[] srno={sr_no};
    	dbUtil.createPreparedStatement(CommonSQL.SETPAYMENTSTATUS);
		flag=dbUtil.executeUpdate(srno);
		dbUtil.commit();
    	
    } catch (Exception exception) {
            logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
    }finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
		}
	}
    return flag;

}


public String getCreditAmt(String licenseno) throws Exception
{
	String crdtamt="";
	try
	{
		dbUtil=new DBUtility();
		dbUtil.createStatement();
		String[] lno={licenseno};
		dbUtil.createPreparedStatement(CommonSQL.GETCREDITAMOUNT);
		crdtamt=dbUtil.executeQry(lno);
		logger.info("C amount----"+crdtamt);
	}
	catch(Exception exception)
	{
		logger.info("Exception in getCreditAmt" + exception);
	}
	return crdtamt;
}
public String getDebitAmt(String licenseno) throws Exception
{
	String debitamt="";
	try
	{
		dbUtil=new DBUtility();
		dbUtil.createStatement();
		String[] lno={licenseno};
		dbUtil.createPreparedStatement(CommonSQL.GETDEBITAMOUNT);
		debitamt=dbUtil.executeQry(lno);
		logger.info("D amount----"+debitamt);
	}
	catch(Exception exception)
	{
		logger.info("Exception in getDebitAmt" + exception);
	}
	return debitamt;
}
public String getPaidAmt(String sr_no) throws Exception
{
	String paidAmt="";
	try
	{
		dbUtil=new DBUtility();
		dbUtil.createStatement();
		String[] srno={sr_no};
		dbUtil.createPreparedStatement(CommonSQL.GETPAIDAMT);
		paidAmt=dbUtil.executeQry(srno);
		logger.info("PAID amount----"+paidAmt);
	}
	catch(Exception exception)
	{
		logger.info("Exception in getDebitAmt" + exception);
	}
	return paidAmt;
}
public boolean getUpdatedSPAmtFlag(String spAmt,String sr_no) throws Exception {

    boolean flag=false;
    //String paymentFlag=null;
    
    try {
    	dbUtil = new DBUtility();
    	dbUtil.createStatement();
    	
    	String[] srno={spAmt,sr_no};
    	dbUtil.createPreparedStatement(CommonSQL.UPDATESPAMT);
		flag=dbUtil.executeUpdate(srno);
    	
    } catch (Exception exception) {
            logger.info("Exception in getPaymentFlag-ServiceProviderAccountDAO" + exception);
    }finally{
		try {
			dbUtil.closeConnection();
		} catch (Exception e) {
			logger.error("Service Provider Account DAO in dao start" + e.getStackTrace());
		}
	}
    return flag;

}

public String getSPComm(String _funId, String _amt) throws Exception {
	String spComm="";
	logger.info("Inside getOthersFeeDuty()");
	try {
		
	
		dbUtil = new DBUtility();
		clstmt = dbUtil.returnCallableStatement(CommonSQL.SPCOMMISION_CALC);
		clstmt.setString(1, _funId);
		clstmt.setString(2, _amt);
		clstmt.registerOutParameter(3, OracleTypes.DOUBLE);
		
		if (!clstmt.execute()) {
			double temp = clstmt.getDouble(3);
			logger.info("value fetched in callable statement-----"+temp);
			spComm = String.valueOf(temp);
			logger.info("value as fetched from callable statement and conversion into string-----"+spComm);
			double spCommamt = Double.parseDouble(spComm);
			logger.info("double converted value:-------"+spCommamt);
		}
	} catch (Exception e) {
		e.printStackTrace();
		logger.error(e);
	} finally {

		dbUtil.closeConnection();
	}
	logger.info("spComm:-" + spComm);
	return spComm;
}


}

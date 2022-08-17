/**
 * 
 */
package com.wipro.igrs.willdeposit.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.willdeposit.sql.CommonSQL;

public class PaymentDAO {
	//DBUtility dbUtility;
	private Logger logger = (Logger) Logger.getLogger(PaymentDAO.class);
	public PaymentDAO(){
		try{
			//dbUtility = new DBUtility();		
		}
		catch(Exception objE){
			logger.error("Exception when creating DBUtiliti obj creating " + objE);			
		}
	}	
	public String getCashDetails(String[] _cashList)throws Exception{
		System.out.println("DAO STARTS HERE ");
		String returnFlag = "fail";
		DBUtility cashDbUtility = null;
		try{
			String cashTxnID;
			cashDbUtility = new DBUtility();
			String cashTxnNo = CommonSQL.CASH_TXN + " TRANSACTION_ID LIKE   '" + _cashList[0] + "'" +
			" AND GROSS_AMOUNT =  '" + _cashList[1] + "' AND trunc(CREATED_DATE) = to_date('"+_cashList[2]+"','mm/dd/yyyy')";			
			cashDbUtility.createStatement();					
			cashTxnID = cashDbUtility.executeQry(cashTxnNo);    

			if(cashTxnID.equalsIgnoreCase("")|| cashTxnID==null){
				returnFlag = "fail";
			}
			else{
				returnFlag = "success";
			}
		}catch (NullPointerException ne) {
			logger.error("Null Pointer Exception at getCashDetails in Dao " + ne);
		}
		catch(Exception e){
			logger.error("Exception at getCashDetails in Dao " + e);
		}
		finally{
			try{
				cashDbUtility.closeConnection();	
			}

			catch (NullPointerException ne) {
				logger.error("Exception at  Close Connection for getCashDetails in Dao " + ne);
			}
			catch (SQLException se) {
				logger.error("Exception at  Close Connection for getCashDetails in Dao " + se);
			}
			catch (Exception e) {
				logger.error("Exception at Close Conection for getCashDetails in Dao " + e);
			}

		}
		return returnFlag;
	}




	/**
	 * Returns the transaction ID after successful transaction
	 * @param _challan
	 * @param amt
	 * @return String
	 * @throws NullPointerException
	 * @throws Exception
	 */
	public String getChallanDetails(String[] _challan,double amt) throws NullPointerException,Exception {			
		String txnNo = null;
		String payTxnID = null;
		String returnFlag = "fail";	
		try{
			txnNo = getChallanTxn(_challan,amt);        
			if(txnNo == "null" || txnNo.equalsIgnoreCase("")){			
				returnFlag = "fail";
			}
			else{
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
		}
		catch(Exception e){
			logger.error(" Exception at getChallanDetails in DAO " + e);
		}	  
		System.out.println("DAO final Retrun Value is " + returnFlag);
		return returnFlag;
	}



	/**
	 * @param _challan
	 * @param amt
	 * @return String
	 * @throws NullPointerException
	 * @throws Exception
	 */
	public String getChallanTxn(String[] _challan,double amt) throws NullPointerException,Exception {			
		String txnNo = null;
		String payTxnID = null;
		String returnFlag = "fail";
		DBUtility challnTxnDbUtility = null;
		try{
			challnTxnDbUtility = new DBUtility();
			String challanTxnNo = CommonSQL.CASH_TXN + "  SCROLL_NUMBER LIKE   '" + _challan[0] + "'" +
			" AND GROSS_AMOUNT =  '" + amt + "' AND trunc(CREATED_DATE) = to_date('"+_challan[1]+"','mm/dd/yyyy')" ;			
			challnTxnDbUtility.createStatement();		
			txnNo = challnTxnDbUtility.executeQry(challanTxnNo);        
			if(txnNo == null || txnNo.equalsIgnoreCase("")){			
				returnFlag = "null";
			}
			else{
				returnFlag = txnNo;
			}
		}catch(NullPointerException e){

		}catch (Exception e) {

		}
		finally{
			try{
				challnTxnDbUtility.closeConnection();
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
	 * @param _cashTxnID
	 * @return
	 */
	public ArrayList getTxnMpping(String _cashTxnID){	
		DBUtility dbMappingUtilty = null;
		String returnFlag = "fail";
		ArrayList txnIDList = null;

		try{

			dbMappingUtilty = new DBUtility();    		  
			String challanTxnqry = CommonSQL.MAPPING_TXN_QUERY + "WHERE TXN_ID LIKE '" + _cashTxnID +"'" ;  
			dbMappingUtilty.createStatement();
			txnIDList = dbMappingUtilty.executeQuery(challanTxnqry);			

		}catch (NullPointerException ne) {
			logger.error("Null Exception at getTxnMapping   in DAO " + ne);
		}
		catch(Exception e){
			logger.error(" Exception at getTxnMapping in DAO " + e);
		}

		finally {
			try{	    
				dbMappingUtilty.closeConnection();
			}
			catch (Exception e) {
				logger.error("Exception when Close Connection for getTxnMapping " + e);
			}		
		}
		return txnIDList;
	}



	public boolean setPaymentTxn(String _cashTxnId,String _txnID){
		DBUtility dbUtilitypayTxn = null;
		boolean flagcash=true;
		String returnflag = null;
		String payTxnID;
		System.out.println("DAO at SetPaymentTxn " +_txnID);
		try{	      	 
			returnflag = setTxnMapID(_cashTxnId,_txnID);
			System.out.println("DAO AFTER calling setTxnMapID " + returnflag);
			if (returnflag != null){
				flagcash = setStatus(_cashTxnId);	
				if(flagcash == true){		      			
					return flagcash;		      			
				}
				else{
					return false;
				}
			}
			else{		      		 
				return false;
			}
		}
		catch(Exception e){
			logger.error("Exception at getPayemntTxN in DAO  " + e);
		}
		finally{
			try{
				dbUtilitypayTxn.closeConnection();
			}
			catch(Exception e){
				logger.error("Exception when close connection in getPayemntTxn " + e);
			}
		}	
		System.out.println("at final the return type of setPaytxn is  " + flagcash);
		return flagcash;
	}



	public String setTxnMapID(String _cashTxnId,String _txnID){
		DBUtility dbsetUtility = null;
		boolean returnFlag = false;
		String returnid = "false";
		try{								
			dbsetUtility = new DBUtility();
			dbsetUtility.createPreparedStatement(CommonSQL.PAYMENT_TXN_MAP);
			logger.debug("Query:-    "+CommonSQL.PAYMENT_TXN_MAP);
			String[] txnID = new String[2];		      
			txnID[0] = _txnID;
			txnID[1] = _cashTxnId;
			returnFlag = dbsetUtility.executeUpdate(txnID);
			if(returnFlag == false){	    	 
				dbsetUtility.rollback();
			}
			if(returnFlag == true){		    	
				returnid = _txnID;
			}

		}
		catch (NullPointerException ne) {
			logger.error("Null Exception at insert TxnID  in DAO " + ne);
		}
		catch(Exception e){
			logger.error(" Exception at insert TxnID in DAO " + e);
		}
		finally {
			try{	    
				dbsetUtility.closeConnection();
			}
			catch (Exception e) {
				logger.error("Exception at setTxnID in DAO  " + e);
			}		
		}        
		System.out.println("the Return DAO set TxnMap id " + returnid);
		return returnid;
	}


	public String setTxnID(double _totamt){
		DBUtility dbsetUtility = null;
		boolean returnFlag = false;
		String returnid = "false";
		try{								
			dbsetUtility = new DBUtility();
			System.out.println("Dao setTxn id " + _totamt);
			System.out.println(CommonSQL.CHALLAN_TXN_INSERT);
			dbsetUtility.createPreparedStatement(CommonSQL.CHALLAN_TXN_INSERT);
			String[] txnID = new String[1];
			txnID[0] = String.valueOf(_totamt);	     
			returnFlag = dbsetUtility.executeUpdate(txnID);
			System.out.println("after execute update " + returnFlag);
			if(returnFlag == false){	    	 
				dbsetUtility.rollback();
			}
			if(returnFlag == true){

				String txnno = getPayTxn();
				returnid = txnno;
			}

		}
		catch (NullPointerException ne) {
			logger.error("Null Exception at insert TxnID  in DAO " + ne);
		}
		catch(Exception e){
			logger.error(" Exception at insert TxnID in DAO " + e);
		}
		finally {
			try{	    
				dbsetUtility.closeConnection();
			}
			catch (Exception e) {
				logger.error("Exception at setTxnID in DAO  " + e);
			}		
		}        
		return returnid;
	}

	/* Method used for update Status for Txn Id in IGRS_PAYMENT_DETAILS TABLE 
	 * 
	 * 
	 * */

	public boolean setStatus(String _payTxnID){
		DBUtility dbSetStatusUtility = null;
		boolean setFlag = false;
		try{				
			dbSetStatusUtility = new DBUtility();
			dbSetStatusUtility.createPreparedStatement(CommonSQL.STATUS_UPDATE_QUERY);
			String[] txnID = new String[1];
			txnID[0] = _payTxnID;
			setFlag = dbSetStatusUtility.executeUpdate(txnID);
			if(setFlag == false){
				System.out.println("in setStatus fail in DAO");
				dbSetStatusUtility.rollback();	            }

		}catch (NullPointerException ne) {
			logger.error("Null Exception at Update Status of  TxnID  in DAO " + ne);
		}
		catch(Exception e){
			logger.error(" Exception at Update Status of TxnID in DAO " + e);
		}

		finally {
			try{	    
				dbSetStatusUtility.closeConnection();
			}
			catch (Exception e) {
				logger.error("Exception at close connection for setStatus() in DAO  " + e);

			}		
		}    
		return setFlag;
	}


	public String getPayTxn(){
		DBUtility dbgetPaytxn = null;
		String returnFlag = "fail";
		ArrayList txnIDList = null;
		try{					
			dbgetPaytxn = new DBUtility();    		  
			String challanTxnqry = CommonSQL.INQUIRE_PAY_TXN_ID;				  
			dbgetPaytxn.createStatement();
			returnFlag = dbgetPaytxn.executeQry(challanTxnqry);
			if(returnFlag == null || returnFlag == ""){
				returnFlag = "Fail";
			}
			else{
				return returnFlag;
			}
		}catch (NullPointerException ne) {
			logger.error("Null Exception at getPayTxn   in DAO " + ne);
		}
		catch(Exception e){
			logger.error(" Exception at getPayTxn in DAO " + e);
		}
		finally {
			try{	    
				dbgetPaytxn.closeConnection();
			}
			catch (Exception e) {
				logger.error("Exception when Close Connection for getPayTxn " + e);
			}		
		}
		return returnFlag;
	}

}  



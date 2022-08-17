package com.wipro.igrs.payments.bd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.payment.dao.CashLinkedDAO;
import com.wipro.igrs.payment.form.CashCounterLinkedForm;
import com.wipro.igrs.payment.util.MD5Check;
import com.wipro.igrs.payments.bo.CashInternalBO;
import com.wipro.igrs.payments.bo.PaymentBO;
import com.wipro.igrs.payments.dao.CashInternalDAO;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.payments.dto.OnlineDTO;
import com.wipro.igrs.payments.dto.PaymentDTO;
import com.wipro.igrs.payments.form.PaymentForm;

/**
 * ===========================================================================
 * File : PaymentBD.java Description : Represents the Payment BD Class Author :
 * Karteek P
 * 
 * Created Date : March 18, 2008
 * 
 * ===========================================================================
 */
public class CashInternalBD {

	CashInternalBO paymentBO;
	Logger logger = (Logger) Logger.getLogger(CashInternalBD.class);

	public CashInternalBD() {
		try {
			paymentBO = new CashInternalBO();
		} catch (Exception e) {
			logger.error("DAO obj creation at BD construtor   " + e);

		}
	}

	/************************************************************
	 * Method Name : getCashDetails() Arguments : PaymentForm Bean Return : if
	 * it success return Transaction ID other wise return fail
	 ***********************************************************/
	public String getCashDetails(PaymentForm _paymentForm, String _amount,
			String respId) throws Exception {
		logger.debug("INSIDE BD..check for consumed id");
		String returnFlag = paymentBO.getCashDetails(_paymentForm, respId);

		if ("cashbutton".equalsIgnoreCase(_paymentForm.getPaymentDTO()
				.getCheckButton())) {

			return returnFlag;
		}
		if (returnFlag == "success") {
			logger
					.debug("INSIDE BD.....BEFORE SETCASHTXN....AMOUNT TO BE ENTERED IS......"
							+ _amount);
			returnFlag = paymentBO.setCashTxn(_paymentForm, _amount);
		}
		return returnFlag;
	}

	/******************************************************************
	 * Method Name : PaymentTransactionFinal() Arguments : Cash Form Return :
	 * Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean PaymentTransactionFinal(PaymentForm _form)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.PaymentTransactionFinal(_form);
		return flg;
	}
	public boolean PaymentTransactionFinalNew(PaymentForm _form)
	throws NullPointerException, SQLException, Exception {
boolean flg = false;
flg = paymentBO.PaymentTransactionFinalNew(_form);
return flg;
}

	/******************************************************************
	 * Method Name : PaymentChallanTransactionFinal() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentChallanTransactionFinal(PaymentForm _form)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.PaymentChallanTransactionFinal(_form);
		return flg;
	}

	/******************************************************************
	 * Method Name : PaymentOnlineTransactionFinal() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentOnlineTransactionFinal(PaymentForm form)
			throws NullPointerException, SQLException, Exception {
		boolean flg = true;
		flg = paymentBO.PaymentOnlineTransactionFinal(form);
		return flg;
	}

	/******************************************************************
	 * Method Name : PaymentOnlineTransactionFinalSP() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentOnlineTransactionFinalSP(PaymentForm form,HttpSession session)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.PaymentOnlineTransactionFinalSP(form,session);
		return flg;
	}

	public synchronized String saveObject(String moduleId,ActionForm form)
	{
		FileInputStream in =null;
		ObjectInputStream readers = null;
		String check = null;
		UUID s = java.util.UUID.randomUUID();
		 Date date = new Date();
		Format yearformat  = new SimpleDateFormat("yy");
		  Format monthformat = new SimpleDateFormat("MM");
		  Format dateformat  = new SimpleDateFormat("dd");
		  Format hourformat  = new SimpleDateFormat("hh");
		  Format minformat  = new SimpleDateFormat("mm");
		  Format secformat  = new SimpleDateFormat("ss");
		  Format ampmformat  = new SimpleDateFormat("a");
		  String dfmt = dateformat.format(date);
		  String yfmt = yearformat.format(date);
		  String mfmt = monthformat.format(date);
		  String hfmt = hourformat.format(date);
		  String mifmt = minformat.format(date);
		  String sfmt = secformat.format(date);
		
		  PropertiesFileReader proper;
		  String downloadPath = "";
		try {
			proper = PropertiesFileReader.getInstance("resources.igrs");
			downloadPath=proper.getValue("mp_treasury_disk_path");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
	      
		String fileName = downloadPath+File.separator+moduleId+File.separator+s.toString()+File.separator;
		
		logger.debug("FileName"+fileName);
		
		try {
			File yourFile = new File(fileName);
			if(!yourFile.exists()) {
				logger.debug(("File Doesnot"));
				boolean boo = yourFile.mkdirs();
				
				yourFile = new File(fileName+"Obj.sav");
			    yourFile.createNewFile();
			    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName+"Obj.sav"));
			 
			    out.writeObject(form);
			    out.close();
			    check = fileName+"Obj.sav";

			} 
			else
			{
				
				if(yourFile.delete())
				{
					boolean boo = yourFile.mkdirs();
					
					yourFile = new File(fileName+"Obj.sav");
				    yourFile.createNewFile();
				    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName+"Obj.sav"));
					 out.writeObject(form);
					    out.close();
					    check = fileName+"Obj.sav";
				}

			}
			
		} catch (FileNotFoundException e) {
			check = "";
			e.printStackTrace();
		} catch (IOException e) {
			check = "";
			e.printStackTrace();
		}
		/*} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return check;
		
		
	}
	
	/******************************************************************
	 * Method Name : PaymentOnlineTransactionFinalSP2() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentOnlineTransactionFinalSP2(
			PaymentForm form,HttpSession session) throws NullPointerException, SQLException,
			Exception {
		boolean flg = false;
		flg = paymentBO.PaymentOnlineTransactionFinalSP2(form,session);
		return flg;
	}

	/******************************************************************
	 * Method Name : PaymentChallanAvailTransactionFinal() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentChallanAvailTransactionFinal(
			PaymentForm _form,HttpSession session) throws NullPointerException, SQLException,
			Exception {
		boolean flg = false;
		flg = paymentBO.PaymentChallanAvailTransactionFinal(_form,session);
		return flg;
	}

	/******************************************************************
	 * Method Name : PaymentChallanAvailTransactionFinal2() Arguments : Cash
	 * Form Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentChallanAvailTransactionFinal2(
			PaymentForm _form,HttpSession session) throws NullPointerException, SQLException,
			Exception {
		boolean flg = false;
		flg = paymentBO.PaymentChallanAvailTransactionFinal2(_form,session);
		return flg;
	}

	/******************************************************************
	 * Method Name : PaymentCreditTransactionFinal() Arguments : Cash Form
	 * Return : Boolean Throws : NullPointer or SQLException or Exception
	 *******************************************************************/
	public synchronized boolean PaymentCreditTransactionFinal(PaymentForm _form,HttpSession session)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.PaymentCreditTransactionFinal(_form,session);
		return flg;
	}

	/*
	 * public boolean searchRsptId(String rspId) throws Exception { return
	 * paymentBO.searchRsptId(rspId); }
	 */
	public PaymentDTO getRsptDetails(String rspId, String entAmt, String funid,
			String languageLocale, String officeIdLoggedIn, String parentAmountNew) throws Exception {
		return paymentBO.getRsptDetails(rspId, entAmt, funid, languageLocale,officeIdLoggedIn, parentAmountNew);
	}

	//akansha
	public PaymentDTO getRsptDetailsNew(String rspId, String entAmt, String funid,
			String languageLocale, String officeIdLoggedIn, String parentAmountNew) throws Exception {
		return paymentBO.getRsptDetailsNew(rspId, entAmt, funid, languageLocale,officeIdLoggedIn, parentAmountNew);
	}
	// commneted for new Challan details
	/*
	 * public ChallanDTO getchallanRsptDetails(String rspId, String entAmt,
	 * String funid, String bankid) throws Exception { return
	 * paymentBO.getchallanRsptDetails(rspId, entAmt, funid, bankid); }
	 */
	// added for new Challan details
	public ChallanDTO getchallanRsptDetails(String rspId, String entAmt,
			String funid, String refId) throws Exception {
		return paymentBO.getchallanRsptDetails(rspId, entAmt, funid, refId);
	}

	/***********************************************************
	 * Method Name : getOnlineRsptDetails() Arguments : CIN, amount, functionid
	 * Return : if it success return Transaction ID other wise return fail
	 ***********************************************************/

	public OnlineDTO getOnlineRsptDetails(String rspId, String entAmt,
			String funid) throws Exception {
		return paymentBO.getOnlineRsptDetails(rspId, entAmt, funid);
	}

	/***********************************************************
	 * Method Name : getChallanDetails() Arguments : PaymentForm Bean Return :
	 * if it success return Transaction ID other wise return fail
	 ***********************************************************/
	public String getChallanDetails(PaymentForm _paymentForm, String _amount)
			throws Exception {

		String returnFlag = paymentBO.getChallanDetail(_paymentForm);
		if (returnFlag == "success") {
			String str = "Challan";
			returnFlag = paymentBO.setChallanDetail(_paymentForm, str, _amount);
		}
		return returnFlag;
	}

	/***********************************************************
	 * Method Name : getCreditDetails() Arguments : PaymentForm Bean, amount
	 * Return : if it success return Transaction ID other wise return fail
	 ***********************************************************/
	/*
	 * public String getCreditDetails(PaymentForm _paymentForm,double _amount)
	 * throws Exception {
	 * 
	 * 
	 * String returnFlag = paymentBO.setCreditDetail(_paymentForm,_amount);
	 * 
	 * return returnFlag; }
	 */

	/***********************************************************
	 * Method Name : getCreditDetails1() Arguments : PaymentForm Bean, amount
	 * Return : if it success return Transaction ID other wise return fail
	 ***********************************************************/
	public String getCreditDetails1(PaymentForm _paymentForm, String uid,
			double _amount) throws Exception {

		String returnFlag = paymentBO.setCreditDetail1(_paymentForm, uid,
				_amount);

		return returnFlag;
	}

	/**
	 * Method : getRole () Description : getting role id based on the user id
	 * return Type : String
	 * 
	 */
	public String getRole(String uid) throws Exception {
		String role = null;
		CashInternalDAO dao = new CashInternalDAO();
		role = dao.getRole(uid);
		return role;
	}

	/**
	 * Method : getFunctionName () Description : getting function name based on
	 * the function id return Type : String
	 * 
	 */
	public String getFunctionName(String funId, String languageLocale)
			throws Exception {
		String funName = null;
		CashInternalDAO dao = new CashInternalDAO();
		funName = dao.getFunctionName(funId, languageLocale);
		return funName;
	}

	/**
	 * Method : getRevenueHeads () Description : getting function name based on
	 * the function id return Type : ArrayList
	 * 
	 */
	public ArrayList getRevenueHeads(String funId) throws Exception {
		ArrayList revHeads = new ArrayList();
		CashInternalDAO dao = new CashInternalDAO();
		revHeads = dao.getRevenueHeads(funId);
		return revHeads;
	}

	/**************************************************************
	 * Method Name : getChallanNoDetails Arguments : PaymentForm Bean and
	 * Selected Button NO Return : if it success return Success other wise
	 * return fail
	 ****************************************************************/
	public String getChallanNoDetails(PaymentForm _paymentForm, int buttonNo)
			throws Exception {

		String returnFlag = paymentBO
				.getChallanNoDetail(_paymentForm, buttonNo);
		return returnFlag;
	}

	/***************************************************************
	 * Method Name : getpurpName Arguments : form Return : String
	 * **************************************************************/
	public String getpurpName(PaymentForm _paymentForm) throws Exception {
		String oid = null;
		CashInternalDAO dao = new CashInternalDAO();
		oid = dao.getpurpName(_paymentForm);
		return oid;
	}

	/***************************************************************
	 * Method Name : gettranDate Arguments : form Return : String
	 * **************************************************************/
	public String gettranDate(PaymentForm _paymentForm) throws Exception {
		String oid = null;
		CashInternalDAO dao = new CashInternalDAO();
		oid = dao.gettranDate(_paymentForm);
		return oid;
	}

	/***************************************************************
	 * Method Name : getdepoDtl Arguments : form Return : String
	 * **************************************************************/
	public String getdepoDtl(PaymentForm _paymentForm) throws Exception {
		String dep = null;
		CashInternalDAO dao = new CashInternalDAO();
		dep = dao.getdepoDtl(_paymentForm);
		return dep;
	}

	/***************************************************************
	 * Method Name : getdepoDtl Arguments : form Return : String
	 * **************************************************************/
	public String getdepoDtlON(PaymentForm _paymentForm) throws Exception {
		String dep = null;
		CashInternalDAO dao = new CashInternalDAO();
		dep = dao.getdepoDtlON(_paymentForm);
		return dep;
	}

	/***************************************************************
	 * Method Name : getdepoDtlCa Arguments : form Return : String
	 * **************************************************************/
	public String getdepoDtlCa(PaymentForm _paymentForm) throws Exception {
		String dep = null;
		CashInternalDAO dao = new CashInternalDAO();
		dep = dao.getdepoDtlCa(_paymentForm);
		return dep;
	}

	/***************************************************************
	 * Method Name : getissueName Arguments : form Return : String
	 * **************************************************************/
	public String getissueName(PaymentForm _paymentForm) throws Exception {
		String oid = null;
		CashInternalDAO dao = new CashInternalDAO();
		oid = dao.getissueName(_paymentForm);
		return oid;
	}

	/***************************************************************
	 * Method Name : getSpDetails Arguments : SP USER ID Return : ArrayList
	 * 
	 * @param funid
	 * **************************************************************/
	public ArrayList getSpDetails(String _spUserId, String funid,String sessionLicenseNo)
			throws Exception {
		ArrayList returnList = paymentBO.getSpDetails(_spUserId, funid,sessionLicenseNo);
		return returnList;

	}
	
	//preeti changes on 1 dec 2015
	public boolean getAcctBalance(String sessionLicenseNo,String funid)
	throws Exception {
		 boolean    insertflag  = false;
		 insertflag = paymentBO.getAcctBalance(sessionLicenseNo,funid);
return insertflag;

}
	
	//preeti changes
	public String getAcctBal(String sessionLicenseNo,String funid)
	throws Exception {
String returnList = paymentBO.getAcctBal(sessionLicenseNo,funid);
return returnList;

}

	/***********************************************************
	 * Method Name : setSpDetails() Arguments : DTO Object Return : SpTxn Id
	 * Exception : NullPointer or Exception
	 ***********************************************************/
	public String setSpDetails(PaymentDTO _paymentDTO)
			throws NullPointerException, Exception {
		String returnFlag = null;
		try {

			returnFlag = paymentBO.setSpDetails(_paymentDTO);
			if (returnFlag != null) {
				return returnFlag;
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			logger.error("Null Pointer Exception at setSpDetails in BD " + ne);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception at setSpDetails in BD " + e);
		}
		return returnFlag;
	}

	/***********************************************************
	 * Method Name : getSPFee() Arguments : Function Id, Sub Function Id Return
	 * : String Exception : NullPointer or Exception
	 ***********************************************************/
	public String getSPFee(String _funId, String _subFunId)
			throws NullPointerException, Exception {
		String returnFlag = null;
		try {
			returnFlag = paymentBO.getSPFee(_funId, _subFunId);
			if (returnFlag != null) {
				return returnFlag;
			}
		} catch (NullPointerException ne) {
			logger.error("Null Pointer Exception at  getSPFee in BD " + ne);
			ne.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception at  getSPFee in BD " + e);
			e.printStackTrace();
		}
		return returnFlag;
	}

	/***********************************************************
	 * Method Name : getBankIds()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 ***********************************************************/
	public ArrayList getBankIds() throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getBankIds();

		} catch (NullPointerException ne) {
			logger.error("Null Pointer Exception at  getSPFee in BD " + ne);
		} catch (Exception e) {
			logger.error("Exception at  getSPFee in BD " + e);
		}
		return returnList;
	}

	/***********************************************************
	 * Method Name : getDistrictList()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 ***********************************************************/
	public ArrayList getDistrictList(String languageLocale)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getDistrictList(languageLocale);

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return returnList;
	}

	public ArrayList getTreasuryList(String districtId, String languageLocale)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getTreasuryList(districtId, languageLocale);

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return returnList;
	}

	/***********************************************************
	 * Method Name : getOfficeList()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 ***********************************************************/
	public ArrayList getOfficeList(String disId, String languageLocale)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getOfficeList(disId, languageLocale);

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return returnList;
	}

	/******************************************************************
	 * Method Name : ChallanDwnldInsert() Arguments : Cash Form Return : Boolean
	 * Throws : NullPointer or SQLException or Exception
	 * @param jspPage 
	 *******************************************************************/
	public boolean ChallanDwnldInsert(PaymentForm frm, String userId, String jspPage)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.ChallanDwnldInsert(frm, userId,jspPage);
		return flg;
	}

	/******************************************************************
	 * Method Name : onlineDwnldInsert() Arguments : Cash Form Return : Boolean
	 * Throws : NullPointer or SQLException or Exception
	 * @param crn 
	 * @param jspPage 
	 *******************************************************************/
	public boolean onlineDwnldInsert(PaymentForm frm, String userId, String crn, String jspPage)
			throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		flg = paymentBO.onlineDwnldInsert(frm, userId,crn,jspPage);
		return flg;
	}

	/***********************************************************
	 * Method Name : getDwnldChallanInsertedDet()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 * 
	 * @param uniqid
	 ***********************************************************/
	public ArrayList getDwnldChallanInsertedDet(String uniqid)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getDwnldChallanInsertedDet(uniqid);

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return returnList;
	}

	public String createUrl(String params[], String values[], String key) {
		String url = "";
		if (params.length != values.length) {
			return null;
		} else {

			for (int i = 0; i < params.length; i++) {
				if (i != params.length - 1) {
					url = url + params[i] + "=" + values[i] + "|";

				} else {
					url = url + params[i] + "=" + values[i];
				}

			}

		}
		MD5Check check = new MD5Check();
		url = url + "|checkSum=" + check.md5Java(url);

		try {
			// AESEncrypt encrpt = new AESEncrypt();
			// encrpt.setSecretKey("D://MPT_CYBER.key");
			// String encData = encrpt.encryptFile(url);
			// System.out.println(encData);
			// String uString=URLEncoder.encode (encData);
			// System.out.println("encdata="+uString+"&merchant_code=MPGOVT_IGR");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/***********************************************************
	 * Method Name : getDwnldChallanInsertedDet()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 * 
	 * @param uniqid
	 ***********************************************************/
	public String[] getDistIdOfficeIdForRUChallan(PaymentForm eform)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		String[] strArr = new String[2];
		try {
			returnList = paymentBO.getDistIdOfficeIdForRUChallan(eform);

			if (returnList != null && returnList.size() == 1) {
				String str = returnList.toString();
				str = str.substring(2, str.length() - 2);
				strArr = str.split(",");
				// return strArr;
			} else {
				strArr = null;
			}

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return strArr;
		// return returnList;
	}

	/**
	 * getCurrDateTime for getting current system date/time from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 * 
	 */
	public String getCurrDateTime() throws Exception {

		String sysdate = paymentBO.getCurrDateTime();

		// String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat date1 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		SimpleDateFormat date2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		Date d1 = date1.parse(sysdate);
		String formatDate = date2.format(d1);

		// System.out.println("formatted date=----->"+formatDate);

		return formatDate;
	}

	public String getTreasuryDistrict(String districtID) {

		CashInternalDAO dao = new CashInternalDAO();
		return dao.getTreasuryDisctrictId(districtID);

	}
	public String getDescriptionHOA(String major, String string, String string2) {

		CashInternalDAO dao = new CashInternalDAO();
		return dao.getDescriptionHOA(major,string,string2);

	}

	public void getPaymentDetailsOnline(PaymentForm form) {
		CashInternalDAO dao = new CashInternalDAO();
		dao.getpaymentDetailsOnline(form);
		
	}
	
	public boolean insertURL(String crn,String type,String url){
		CashInternalDAO dao = new CashInternalDAO();
		return dao.insertURL(crn,type,url);
	}
	public boolean checkAmount(String crn,String amt){
		CashInternalDAO dao = new CashInternalDAO();
		return dao.checkAmount(crn,amt.trim());
	}
	public boolean checkUser(String crn,String userID){
		CashInternalDAO dao = new CashInternalDAO();
		return dao.checkUser(crn,userID.trim());
	}
	/***********************************************************
	 * Method Name : getDwnldChallanInsertedDet()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 * 
	 * @param uniqid
	 ***********************************************************/
	public String[] getTreasuryData(String crn)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		String[] strArr = new String[2];
		try {
			returnList = paymentBO.getTreasuryData(crn);

			if (returnList != null && returnList.size() == 1) {
				String str = returnList.toString();
				str = str.substring(2, str.length() - 2);
				strArr = str.split(",");
				// return strArr;
			} else {
				strArr = null;
			}

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return strArr;
		// return returnList;
	}
	/**
	 * Method : getFunctionName () Description : getting function name based on
	 * the function id return Type : String
	 * 
	 */
	public String getPaidAmount(String txnID)
			throws Exception {
		String funName = null;
		CashInternalDAO dao = new CashInternalDAO();
		funName = dao.getPaidAmount(txnID);
		return funName;
	}
	/**
	 * Method : getFunctionName () Description : getting function name based on
	 * the function id return Type : String
	 * 
	 */
	public String getPayableAmount(PaymentForm eForm)
			throws Exception {
		String funName = null;
		CashInternalDAO dao = new CashInternalDAO();
		funName = dao.getPayableAmount(eForm);
		return funName;
	}
	/***********************************************************
	 * Method Name : getPayDtls()
	 * 
	 * Return : ArrayList Exception : NullPointer or Exception
	 * 
	 * @param uniqid
	 ***********************************************************/
	public ArrayList getPayDtls(PaymentForm eForm)
			throws NullPointerException, Exception {
		ArrayList returnList = new ArrayList();
		try {
			returnList = paymentBO.getPayDtls(eForm);

		} catch (NullPointerException ne) {

		} catch (Exception e) {

		}
		return returnList;
	}
	
	public boolean validateCRN(String userID,PaymentForm eForm){
		CashInternalDAO dao = new CashInternalDAO();
		return dao.validateCRN(userID,eForm);
	}
	/*//Changes done by preeti on 24 nov 2015
	public void getTreasurydata(ArrayList adbsa){
		CashInternalDAO dao = new CashInternalDAO();
		dao.getTreasurydata(adbsa);
	}*/
	
	
	
	public String cashPayableAmt(PaymentForm form) throws Exception{
		 CashInternalDAO dao1 		= new CashInternalDAO();
		 String 	amt	= "";
	        String []arry=new String[2];
	          arry[0] = form.getParentRefId();
	        arry[1]= "I";
	       
			
	        amt=dao1.getCashPaymentDetails(arry);
		 
		 return amt;
	 }
	
	public boolean cashCheck(PaymentForm _form)
	throws NullPointerException, SQLException, Exception {
     boolean flg = false;
    flg = paymentBO.cashCheck(_form);
return flg;
}
} // BO CLOSE

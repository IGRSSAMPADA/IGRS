package com.wipro.igrs.payments.bo;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.payments.dao.PaymentDAO;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.payments.dto.OnlineDTO;
import com.wipro.igrs.payments.dto.PaymentDTO;
import com.wipro.igrs.payments.form.PaymentForm;
import com.wipro.igrs.reginit.constant.RegInitConstant;


/**
 * ===========================================================================
 * File : PaymentBO.java Description : Represents the Payment BO Class Author :
 * Karteek P Created Date : March 18, 2008
 * 
 * ===========================================================================
 */

public class PaymentBO {
    PaymentDAO paymentDao;
    Logger logger = (Logger) Logger.getLogger(PaymentBO.class);

    public PaymentBO() {

	try {
	    paymentDao = new PaymentDAO();
	} catch (Exception e) {
	    logger.error("DAO obj creation at BO construtor   " + e);
	}
    }

    /***************************************************************************
     * Method Name : getCashDetails() Arguments : PaymentForm Bean Return : if
     * it success return Transaction ID other wise return fail Exception :
     * NullPointer or Exception
     **************************************************************************/
    public String getCashDetails(PaymentForm _paymentForm,String respId)
	    throws NullPointerException, Exception {
	double Totamt;
	String cashStatus = "";
	ArrayList returnList = new ArrayList();
	double totAmt = 0.0;
	boolean setTxn = true;
	String returnFlag = "success";
	boolean flag = false;
	try {
	    String challanStatus;
	    String[] challanmsg = new String[15];
	    String[] cashlist = new String[1];
	    //cashlist[0] = _paymentForm.getReceiptID();
	     cashlist[0] = respId;
	    logger.debug("cashlist[0]------------"+cashlist[0]);
	   
	  //  cashlist[1] = _paymentForm.getAmount();
	   // System.out.println("cashlist[1]----------"+cashlist[1]);
	  // String date = _paymentForm.getCashDate();
	  // System.out.println("date"+date);
	   // cashlist[2] = _paymentForm.getCashDate();getDate(objDate);
	 //  cashlist[2] = date;
	    
	//    if ("".equalsIgnoreCase(_paymentForm.getChallancheck())) {
	//	cashlist[1] = "challan";
	//    }
	 //   System.out.println("cashlist[1]------------"+cashlist[1]);
	    // *** Starts here Check The Cash Details ***//
	    
	    //PaymentForm eForm = new PaymentForm();
	    String entAmt = _paymentForm.getEntrAmt();
	    String fid = _paymentForm.getFuncId();
	    flag = paymentDao.searchRespId(respId, entAmt, fid);
		//System.out.println("IN bo flag of cash "+flag);//Flag false only to process
		//logger.debug("IN bo flag of cash "+flag);
		if(flag)
		{
			//System.out.println("I am in BD Record Already exists");
		    
			throw new Exception("Receipt Id  Does not exist"); //Throwing an Exception
			
		}
	    
	    
	    cashStatus = paymentDao.getCashDetails(cashlist);
	    
	    if ("success".equalsIgnoreCase(cashStatus)) 
	    {
		// *** Starts here check Challan Details ***//
		if ("".equalsIgnoreCase(_paymentForm.getChallancheck()))
		{
		    String getChallnflg = getChallanDetail(_paymentForm);
		    if ("fail".equalsIgnoreCase(getChallnflg)) 
		    {
			return getChallnflg;
		    } 
		    else 
		    {
			returnFlag = getChallnflg;
		    }
		    returnFlag = cashStatus;
		}
	    }
	    else
	    {
		return cashStatus;
	    }
	} catch (NullPointerException ne) {
	    logger.error("Null Pointer Exception at getCash Details in BO "
		    + ne);

	} 
	
	return returnFlag;
    }
    
    
    
    public String getDate(String _fdate) {

	StringTokenizer stoken = new StringTokenizer(_fdate, "-");
	String dd = stoken.nextToken();
	String mm = stoken.nextToken();
	String yy = stoken.nextToken();
	if (dd.length() == 2) {
		_fdate = dd + "-" + getMonthName(mm) + "-" + yy;
	}
	
	return _fdate;

		
		
}  

/**
 * @param _month
 * @return
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
	return (String) hm.get(_month);
}
/******************************************************************  
 *   Method Name  :   PaymentTransactionFinal()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public  boolean PaymentTransactionFinal(PaymentForm _form)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentTransactionFinal(_form);
	
	return flg;
}
  
//akansha

public  boolean PaymentTransactionFinalNew(PaymentForm _form)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentTransactionFinalNew(_form);
	
	return flg;
}
/******************************************************************  
 *   Method Name  :   PaymentChallanTransactionFinal()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentChallanTransactionFinal (PaymentForm _form)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentChallanTransactionFinal(_form);
	
	return flg;
}

/******************************************************************  
 *   Method Name  :   PaymentOnlineTransactionFinal()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentOnlineTransactionFinal (PaymentForm form)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentOnlineTransactionFinalM(form);
	
	return flg;
}

/******************************************************************  
 *   Method Name  :   PaymentOnlineTransactionFinalSP()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentOnlineTransactionFinalSP (PaymentForm form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentOnlineTransactionFinalSPM(form,session);
	
	return flg;
}

public synchronized  boolean JudicialPaymentOnlineTransactionFinalSP (PaymentForm form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.JudicialPaymentOnlineTransactionFinalSPM(form,session);
	
	return flg;
}

/******************************************************************  
 *   Method Name  :   PaymentOnlineTransactionFinalSP2()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentOnlineTransactionFinalSP2 (PaymentForm form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentOnlineTransactionFinalSP2M(form,session);
	
	return flg;
}
/******************************************************************  
 *   Method Name  :   PaymentChallanAvailTransactionFinal()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentChallanAvailTransactionFinal (PaymentForm _form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentChallanAvailTransactionFinal(_form,session);
	
	return flg;
}


/******************************************************************  
 *   Method Name  :   PaymentChallanAvailTransactionFinal2()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentChallanAvailTransactionFinal2 (PaymentForm _form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentChallanAvailTransactionFinal2(_form,session);
	
	return flg;
}

/** added by gulrej */
/**
 * @param _form
 * @param session
 * @return boolean
 * @throws SQLException
 * @throws Exception
 */
public synchronized  boolean JudicialChallanPayment (PaymentForm _form,HttpSession session)throws SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.JudicialChallanPayment(_form,session);
	
	return flg;
}


/******************************************************************  
 *   Method Name  :   PaymentCreditTransactionFinal()
 *   Arguments    :   Cash Form 
 *   Return       :   Boolean
 *   Throws 	  :   NullPointer  or SQLException or Exception
*******************************************************************/  
public synchronized  boolean PaymentCreditTransactionFinal (PaymentForm _form,HttpSession session)throws NullPointerException,
SQLException,Exception{
	boolean flg = false;
	PaymentDAO dao = new PaymentDAO();
	
		flg = dao.PaymentCreditTransactionFinal(_form,session);
	
	return flg;
}
  /*public boolean searchRsptId(String respId) throws SQLException,Exception
	{
		boolean flag = false;
		PaymentDAO dao = null;
		
		try
		{
				dao = new PaymentDAO();
				PaymentForm eForm = new PaymentForm();
			    String entAmt = eForm.getEntrAmt();
				flag = dao.searchRespId(respId,entAmt );
				
				if(flag)
				{
					//System.out.println("I am in BD Record Already exists");
				 	throw new Exception("Receipt Id  Does not exist"); //Throwing an Exception
					
				}
				
				
	
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
	}
    */
    
    
    public PaymentDTO getRsptDetails(String rsptId, String entAmt, String funid, String languageLocale, String officeIdLoggedIn, String parentAmountNew) throws SQLException,Exception
	{
		logger.debug("IN BO...For checking consumed id");
		ArrayList setList = new ArrayList();
		PaymentDAO dao = null;
		PaymentForm vo = null;
		PaymentDTO dto = null;
		boolean flag = false;
		try
		{
		   dao = new PaymentDAO();
		   ArrayList fullList = dao.searchCashRespId1(rsptId, entAmt, funid,officeIdLoggedIn, parentAmountNew);
        			if(fullList!=null)
        			{
        				for(int i=0;i<fullList.size();i++)
        				{
        				     dto = new PaymentDTO();
        				  //  vo = new PaymentForm();
        				    	ArrayList subList = (ArrayList)fullList.get(i);
        					//vo.setCashDate((String)subList.get(0));
        					dto.setCashDate((String)subList.get(0));
        					//vo.setAmount((String)subList.get(1));
        					dto.setDepositorName((String)subList.get(1));
        					//dto.setAmount(Double.parseDouble((String)subList.get(1)));
        					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        					dto.setOffice((String)subList.get(2));
        					}else{
        						dto.setOffice((String)subList.get(3));
        					}
        					//dto.setAmount(getAmout(subList));
        					//System.out.println("(Double)subList.get(1)"+(String)subList.get(1));
        					setList.add(dto);
        										
        				}
        			}			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		
		return dto;
		}
    
	//akansha 
    public PaymentDTO getRsptDetailsNew(String rsptId, String entAmt, String funid, String languageLocale, String officeIdLoggedIn, String parentAmountNew) throws SQLException,Exception
	{
		logger.debug("IN BO...For checking consumed id");
		ArrayList setList = new ArrayList();
		PaymentDAO dao = null;
		PaymentForm vo = null;
		PaymentDTO dto = null;
		boolean flag = false;
		try
		{
		   dao = new PaymentDAO();
		   ArrayList fullList = dao.searchCashRespId1New(rsptId, entAmt, funid,officeIdLoggedIn, parentAmountNew);
        			if(fullList!=null)
        			{
        				for(int i=0;i<fullList.size();i++)
        				{
        				     dto = new PaymentDTO();
        				  //  vo = new PaymentForm();
        				    	ArrayList subList = (ArrayList)fullList.get(i);
        					//vo.setCashDate((String)subList.get(0));
        					dto.setCashDate((String)subList.get(0));
        					//vo.setAmount((String)subList.get(1));
        					dto.setDepositorName((String)subList.get(1));
        					//dto.setAmount(Double.parseDouble((String)subList.get(1)));
        					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        					dto.setOffice((String)subList.get(2));
        					}else{
        						dto.setOffice((String)subList.get(3));
        					}
        					//dto.setAmount(getAmout(subList));
        					//System.out.println("(Double)subList.get(1)"+(String)subList.get(1));
        					setList.add(dto);
        										
        				}
        			}			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		
		return dto;
		}
    
    // commentd for new challan details
   /* public ChallanDTO getchallanRsptDetails(String rsptId, String entAmt, String funid, String bankid) throws SQLException,Exception
	{
		logger.debug("IN BO...For checking consumed id");
		ArrayList setList = new ArrayList();
		PaymentDAO dao = null;
		PaymentForm vo = null;
		ChallanDTO dto = null;
		boolean flag = false;
		try
		{
		    dao = new PaymentDAO();
		   ArrayList fullList = dao.searchChallanRespId1(rsptId, entAmt, funid, bankid);
        			if(fullList!=null)
        			{
        				for(int i=0;i<fullList.size();i++)
        				{
        				     dto = new ChallanDTO();
        				  //  vo = new PaymentForm();
        				    	ArrayList subList = (ArrayList)fullList.get(i);
        					//vo.setCashDate((String)subList.get(0));
        					dto.setChallanDate((String)subList.get(0));
        					//vo.setAmount((String)subList.get(1));
        					dto.setDepositorName((String)subList.get(1));
        					//dto.setAmount(Double.parseDouble((String)subList.get(1)));
        					dto.setBankName((String)subList.get(2));
        					//dto.setAmount(getAmout(subList));
        					//System.out.println("(Double)subList.get(1)"+(String)subList.get(1));
        					setList.add(dto);
        										
        				}
        			}
			
			
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		
		return dto;
				
	}
    */
    
    
    // added for new challan details
    public ChallanDTO getchallanRsptDetails(String rsptId, String entAmt, String funid, String refId) throws SQLException,Exception
	{
		logger.debug("IN BO...For checking consumed id");
		ArrayList setList = new ArrayList();
		PaymentDAO dao = null;
		PaymentForm vo = null;
		ChallanDTO dto = null;
		boolean flag = false;
		try
		{
		    dao = new PaymentDAO();
		   ArrayList fullList = dao.searchChallanRespId1(rsptId, entAmt, funid, refId);
        			if(fullList!=null)
        			{
        				for(int i=0;i<fullList.size();i++)
        				{
        				     dto = new ChallanDTO();
        				  //  vo = new PaymentForm();
        				    	ArrayList subList = (ArrayList)fullList.get(i);
        					//vo.setCashDate((String)subList.get(0));
        					dto.setChallanDate((String)subList.get(0));
        					//vo.setAmount((String)subList.get(1));
        					dto.setDepositorName((String)subList.get(1));
        					//dto.setAmount(Double.parseDouble((String)subList.get(1)));
        					//dto.setBankName((String)subList.get(2));
        					//dto.setAmount(getAmout(subList));
        					//System.out.println("(Double)subList.get(1)"+(String)subList.get(1));
        					setList.add(dto);
        										
        				}
        			}
			
			
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		
		return dto;
				
	}
    
    
    
    
    
    
    // for online search
    
    public OnlineDTO getOnlineRsptDetails(String rsptId, String entAmt, String funid) throws SQLException,Exception
	{
		logger.debug("IN BO...For checking consumed id");
		ArrayList setList = new ArrayList();
		PaymentDAO dao = null;
		PaymentForm vo = null;
		OnlineDTO dto = null;
		boolean flag = false;
		try
		{
		    dao = new PaymentDAO();
		   ArrayList fullList = dao.searchOnlineRespId1(rsptId, entAmt, funid);
        			if(fullList!=null)
        			{
        				for(int i=0;i<fullList.size();i++)
        				{
        				     dto = new OnlineDTO();
        				    ArrayList subList = (ArrayList)fullList.get(i);
        					dto.setOnlineDate((String)subList.get(0));
        					dto.setOnlineDepositorName((String)subList.get(1));
        					setList.add(dto);
        										
        				}
        			}
			
			
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		
		return dto;
				
	}
    
   
    
    public double getAmout(ArrayList list)
    {
	String db =(String) list.get(1);
	
	return Double.parseDouble(db);
    }
    
   
    
    
    
    

    /***************************************************************************
     * Method Name : getChallanDetail Arguments : PaymentForm Bean Return : if
     * it success return Success other wise return Fail Exception : NullPointer
     * or Exception
     **************************************************************************/
    public String getChallanDetail(PaymentForm _paymentForm) {
	boolean flag = true;
	ArrayList returnList = new ArrayList();
	String[] returnflag = new String[15];
	String returnFlag = null;
	try {
	    ChallanDTO challanDTO = new ChallanDTO();
	    double totamt = 0;
	    int n = 0;
	    int sizeList = _paymentForm.getPaymentList().size();
	    String[] challanNos = new String[15];
	    String bankId = null;
	   
	    for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {
		
		if (i == 1) 
		{
		    continue;
		}
		else 
		{
		    String challanMsg = null;
		    double amt;
		    challanDTO = new ChallanDTO();
		    String[] challanNo = new String[4];
		    challanDTO = (ChallanDTO) _paymentForm.getPaymentList()
			    .get(i);
		    challanNo[0] = challanDTO.getChallanNo();
		    challanNo[1] = challanDTO.getChallanDate();
		   	  
		    bankId = challanDTO.getBankId();
		    amt = Double.valueOf(challanDTO.getAmount().trim()).doubleValue();
		    if ("cash".equalsIgnoreCase(_paymentForm.getPaymentDTO()
			    .getSelectType()))
		    {
			challanNo[2] = "cash";
			
			challanNo[3] = _paymentForm.getReceiptID();
				
		    }
		    
		    challanMsg = paymentDao.getChallanDetails(challanNo, amt,
			    bankId);
		   if ("fail".equalsIgnoreCase(challanMsg)) 
		    {
			return challanMsg;
		    } else 
		    {
			returnFlag = challanMsg;
		    }
		}
	    }
	} catch (NullPointerException ne) 
	{
	   
	    logger.error("Null Pointer Exception at getChallan Details in BO "
		    + ne);
	    ne.printStackTrace();
	} catch (Exception e) {
	    logger.error("Exception at getChallan Details in BO " + e);
	    e.printStackTrace();
	}
	return returnFlag;
    }

    /***************************************************************************
     * Method Name : getChallanNoDetail() Arguments : PaymentForm Bean and
     * ButtonNo Return : if it success return Success other wise return Fail
     * Exception : NullPointer or Exception
     **************************************************************************/

    public String getChallanNoDetail(PaymentForm _paymentForm, int buttonNo)
	    throws NullPointerException, Exception {
	boolean flag = true;
	ArrayList returnList = new ArrayList();
	String[] returnflag = new String[15];
	String returnFlag = null;
	try {
	    ChallanDTO challanDTO = new ChallanDTO();
	    double totamt = 0;
	    int n = 0;
	    int sizeList = _paymentForm.getPaymentList().size();
	    // String[] challanNos = new String[5];
	    String challanMsg = null;
	    double amt;

	    String bankId = null;
	    String[] challanNo = new String[3];
	    challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(
		    buttonNo);
	    challanNo[0] = challanDTO.getChallanNo();
	    challanNo[1] = challanDTO.getChallanDate();
	    bankId = challanDTO.getBankId();
	    /*System.out.println(challanDTO.getChallanNo() + "--"
		    + challanDTO.getChallanDate() + "---"
		    + challanDTO.getBankId() + "--" + challanDTO.getAmount());*/
	    amt = Double.valueOf(challanDTO.getAmount().trim()).doubleValue();

	    challanMsg = paymentDao.getChallanTxn(challanNo, amt, bankId);
	    
	    if ("null".equalsIgnoreCase(challanMsg)
		    || "fail".equalsIgnoreCase(challanMsg)) {
		return "fail";
	    } else {
		returnFlag = "success";
	    }

	} catch (NullPointerException ne) {
	   
	    logger.error("Null Pointer Exception at getChallan Details in BO "
		    + ne);
	    ne.printStackTrace();
	} catch (Exception e) {
	   
	    logger.error("Exception at getChallan Details in BO " + e);
	    e.printStackTrace();
	}
		
	return returnFlag;
    }

    /***************************************************************************
     * Method Name : SetCashTxn() Arguments : PaymentForm Bean Return : if it
     * success return Transaction ID other wise return Fail Exception :
     * NullPointer or Exception
     **************************************************************************/
    public String setCashTxn(PaymentForm _paymentForm, String _amt)
	    throws NullPointerException, Exception {
	double totAmt = 0.0;
	String returnFlag = "success";
	//totAmt = totAmt + _paymentForm.getPaymentDTO().getAmount();
	totAmt = totAmt +Double.parseDouble(_amt);
	logger.debug("INSIDE BO....... THE TOTAL AMOUNT....."+totAmt);
	logger.debug("I am in Bo setCashTxn()-----------");
	
	
	try {
	    
	
	    String mode = _paymentForm.getPayMode();
	    String purpose =  _paymentForm.getFuncId();
	    String usid = _paymentForm.getLoggedUser();
	    String setTxn = paymentDao.setTxnID(totAmt, mode, purpose,usid);
	    logger.debug("In Bo after setTnx----------"+setTxn);
	    if (!(setTxn.equalsIgnoreCase("false"))) {
		_paymentForm.setTotAmt(totAmt);
		String rspids = _paymentForm.getReceiptID();
		String rspid = rspids.toLowerCase();
		boolean setTxnMping = paymentDao.setPaymentTxn(rspid, setTxn);
		logger.debug("_paymentForm.getChallancheck()):-" +_paymentForm.getChkChallan());
				
		
		if ("CHECK".equalsIgnoreCase(_paymentForm.getChkChallan())) {
		    // code for insert challan values in to Txn Details table
		    String setChallnFlag = setChallanDetail(_paymentForm,
			    setTxn, _amt);
		    logger.debug("setChallnFlag- in BO--------"+setChallnFlag);
		    if (setChallnFlag != "fail") {
			returnFlag = setChallnFlag;
		    } else {
			return setChallnFlag;
		    }
		}
		returnFlag = setTxn;

	    } else {
		return "fail";
	    }
	} catch (NullPointerException ne) {
	    logger.error("Null Pointer Exception at setCash Txn in BO " + ne);
	    ne.printStackTrace();
	} catch (Exception e) {
	    logger.error("Null Pointer Exception at setCash Txn in BO " + e);
	    e.printStackTrace();
	}
	return returnFlag;
    }

    /***************************************************************************
     * Method Name : setChallanDetail() Arguments : PaymentForm Bean and
     * Transaction NO Return : if it success return Success other wise return
     * Fail Exception : NullPointer or Exception
     **************************************************************************/
    public String setChallanDetail(PaymentForm _paymentForm, String _TxnNo,
	    String _sessionamt) {
	/*System.out.println("--setChallanDetail--<>_paymentForm--"
		+ _paymentForm + "--_TxnNo--<>" + _TxnNo + "--_sessionamt--<>"
		+ _sessionamt);*/
	logger.debug("I am in setChallanDetail-------------------");
	boolean flag = true;
	ArrayList returnList = new ArrayList();
	String[] returnflag = new String[15];
	String returnFlag = null;

	HttpSession session = null;
	String txnNo = _TxnNo;
	try {
	    double totAmt = 0;
	    int n = 0;
	    int sizeList = _paymentForm.getPaymentList().size();
	    String[] challanNos = new String[15];
	    for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {
		if(i==1){
		    continue;
		}else{
		ChallanDTO challanDTO = new ChallanDTO();
		challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(i);
		totAmt = totAmt + Double.parseDouble(challanDTO.getAmount());
		}
	    }
	    logger.debug("totAmt:-"+totAmt+":"+_sessionamt);
	    /*if (totAmt < Double.valueOf(_sessionamt.trim()).doubleValue()) {
		return "fail";
	    }*/
	    if ("Challan".equalsIgnoreCase(_TxnNo)) {
		_paymentForm.setTotAmt(totAmt);
		String mode = _paymentForm.getPayMode(); 
		String purp = _paymentForm.getFuncId();
		String usid = _paymentForm.getLoggedUser();
		txnNo = paymentDao.setTxnID(totAmt, mode, purp,usid);
	    }
	    ChallanDTO challanDTO = null;
	    for (int i = 0; i < _paymentForm.getPaymentList().size(); i++) {
		if(i==1){		    
		    continue;
		}else{
		
		String challanTxnId = null;
		double amt;
		challanDTO = new ChallanDTO();
		String[] challanNo = new String[4];
		challanDTO = (ChallanDTO) _paymentForm.getPaymentList().get(i);
		challanNo[0] = challanDTO.getChallanNo();
		challanNo[1] = challanDTO.getChallanDate();
		amt = Double.valueOf(challanDTO.getAmount().trim())
			.doubleValue();
		// Call the DAO method
		challanTxnId = paymentDao.getChallanTxn(challanNo, amt,
			challanDTO.getBankId());
		
		logger.debug("challanTxnId-------------------"+challanTxnId);
		if (challanTxnId == null || challanTxnId.equalsIgnoreCase("")) {
		    return "fail";
		} else {
		    flag = paymentDao.setPaymentTxn(challanTxnId, txnNo);
		    if (flag) {
			returnFlag = txnNo;
		    } else {
			return "fail";
		    }
		}
	    }
	    }
	} catch (NullPointerException ne) {
	    logger.error("Null Pointer Exception at getChallan Details in BO "
		    + ne);
	} catch (Exception e) {
	    logger.error("Exception at getChallan Details in BO " + e);
	}
	return returnFlag;
    }
    
    
    /***************************************************************************
     * Method Name : setCreditDetail() Arguments : PaymentForm Bean and
     * Transaction NO Return : if it success return Success other wise return
     * Fail Exception : NullPointer or Exception
     **************************************************************************/
  /*  public String setCreditDetail(PaymentForm _paymentForm, double amount) {
    	String id = "";
    	String mode= _paymentForm.getPayMode();
    	try{ 
    		id = paymentDao.setTxnID(amount, mode);}
    	catch(Exception e){
    		 logger.error("Exception at setCreditDetail  in BO " + e);
    	}
    	 return id;
    }*/
    /***************************************************************************
     * Method Name : setCreditDetail1() Arguments : PaymentForm Bean and
     * Transaction NO Return : if it success return Success other wise return
     * Fail Exception : NullPointer or Exception
     **************************************************************************/
    public String setCreditDetail1(PaymentForm _paymentForm, String uid, double amount) {
    	String id = "";
    	
    	try{ 
    		id = paymentDao.setTxnID1(_paymentForm,uid, amount);}
    	catch(Exception e){
    		 logger.error("Exception at setCreditDetail  in BO " + e);
    	}
    	 return id;
    }
    /***************************************************************************
     * Method Name : getSpDetails() Arguments : SP User Id Return : if it
     * success return Success other wise return Fail Exception : NullPointer or
     * Exception
     * @param funid 
     **************************************************************************/
    public ArrayList getSpDetails(String _spUserId, String funid,String sessionLicenseNo)
	    throws NullPointerException, Exception 
	    {
	boolean flag = true;
	ArrayList returnList = null;
	String[] returnflag = new String[15];
	String returnFlag = null;
	try
	{
	    returnList = new ArrayList();
	    returnList = paymentDao.getSpLicense(_spUserId,funid,sessionLicenseNo);
	} catch (NullPointerException ne) {
	    logger.error("Null Pointer Exception at getSpDetails in BO " + ne);
	    ne.printStackTrace();
	} catch (Exception e) {
	    logger.error("Exception at getSpDetails in BO " + e);
	    e.printStackTrace();
	}
	return returnList;
    }

    
    //preeti changes on 1 dec 2105
    public boolean getAcctBalance(String sessionLicenseNo,String funid)
    throws NullPointerException, Exception{
    	 boolean    insertflag  = false;
    	try{
    	
    		insertflag=paymentDao.getAcctBalance(sessionLicenseNo,funid);
    	}
    	
    	catch(Exception e){
    	    e.printStackTrace();
    	}
    	return insertflag;
    }
    
    //preeti changes
    public String getAcctBal(String sessionLicenseNo,String funid)
    throws NullPointerException, Exception{
    	String returnList1 = null;
    	try{
    	
    		 returnList1=paymentDao.getAcctBal(sessionLicenseNo,funid);
    	}
    	
    	catch(Exception e){
    	    e.printStackTrace();
    	}
    	return returnList1;
    }
    /***************************************************************************
     * Method Name : setSpDetails() Arguments : DTO Object Return : SPTxn ID
     * Exception : NullPointer or Exception
     **************************************************************************/
    public String setSpDetails(PaymentDTO _paymentDTO)
	    throws NullPointerException, Exception {
	String returnFlag = null;
	try {
	    returnFlag = paymentDao.setSPDetails(_paymentDTO);
	    if (returnFlag != null) {
		return returnFlag;
	    }
	} catch (NullPointerException ne) {
	    ne.printStackTrace();
	    logger.error("Null Pointer Exception at setSpDetails in BO " + ne);
	} catch (Exception e) {
	    e.printStackTrace();
	    logger.error("Exception at setSpDetails in BO " + e);
	}
	return returnFlag;
    }

    /***************************************************************************
     * Method Name : getSPFee() Arguments : Function Id, Sub Function Id Return :
     * SP Fee Exception : NullPointer or Exception
     **************************************************************************/
    public String getSPFee(String _funId, String _subFunId)
	    throws NullPointerException, Exception {
	String returnFlag = null;
	try {

	    returnFlag = paymentDao.getSPFee(_funId, _subFunId);
	    if (returnFlag != null) {
		return returnFlag;
	    }
	} catch (NullPointerException ne) {
	    logger.error("Null Pointer Exception at  getSPFee in BO " + ne);
	} catch (Exception e) {
	    logger.error("Exception at  getSPFee in BO " + e);
	}
	return returnFlag;
    }

    /**
     * @return
     * @throws Exception
     */
    public ArrayList getBankIds() throws Exception {
	ArrayList bankList = new ArrayList();
	IGRSCommon common = new IGRSCommon();
	try {
	    ArrayList tmpList = paymentDao.getBankIds();
	    for (int i = 0; i < tmpList.size(); i++) {
		ArrayList tmpsubList = (ArrayList) tmpList.get(i);
		if (tmpsubList != null) {
		    ChallanDTO dto = new ChallanDTO();
		    dto.setBankId((String) tmpsubList.get(0));
		    dto.setBankName((String) tmpsubList.get(1));
		    bankList.add(dto);
		}

	    }
	    logger.info("getBankIds-->" + bankList);
	} catch (Exception e) {
	    logger.error(e);

	}
	return bankList;
    }
    /**
     * @return
     * @throws Exception
     */
    public ArrayList getDistrictList(String languageLocale) throws Exception {
	ArrayList bankList = new ArrayList();
	PaymentDAO dao = new PaymentDAO();
	try {
		
	    ArrayList tmpList = dao.getDistrictList();
	    for (int i = 0; i < tmpList.size(); i++) {
		ArrayList tmpsubList = (ArrayList) tmpList.get(i);
		if (tmpsubList != null) {
		    PaymentDTO dto = new PaymentDTO();
		    dto.setValue((String) tmpsubList.get(0));
		    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		    dto.setName((String) tmpsubList.get(1));
		    }else{
		    	dto.setName((String) tmpsubList.get(2));	
		    }
		    bankList.add(dto);
		}

	    }
	   
	} catch (Exception e) {
	    logger.error(e);

	}
	return bankList;
    }
    
    /**
     * @return
     * @throws Exception
     */
    public ArrayList getOfficeList(String disId,String languageLocale) throws Exception {
	ArrayList bankList = new ArrayList();
	PaymentDAO dao = new PaymentDAO();
	try {
		
	    ArrayList tmpList = dao.getOfficeList(disId);
	    for (int i = 0; i < tmpList.size(); i++) {
		ArrayList tmpsubList = (ArrayList) tmpList.get(i);
		if (tmpsubList != null) {
		    PaymentDTO dto = new PaymentDTO();
		    dto.setValue((String) tmpsubList.get(0));
		    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		    dto.setName((String) tmpsubList.get(1));
		    }else{
		    	dto.setName((String) tmpsubList.get(2));
		    }
		    bankList.add(dto);
		}

	    }
	   
	} catch (Exception e) {
	    logger.error(e);

	}
	return bankList;
    }
    
    
    public ArrayList getTreasuryList(String disId,String languageLocale) throws Exception {
    	ArrayList treasuryList = new ArrayList();
    	PaymentDAO dao = new PaymentDAO();
    	try {
    		
    	    ArrayList tmpList = dao.getTreasuryList(disId,languageLocale);
    	    for (int i = 0; i < tmpList.size(); i++) {
    		ArrayList tmpsubList = (ArrayList) tmpList.get(i);
    		if (tmpsubList != null) {
    		    PaymentDTO dto = new PaymentDTO();
    		    dto.setValue((String) tmpsubList.get(0));
    		   
    		    dto.setName((String) tmpsubList.get(1));
    		  
    		    
    		    treasuryList.add(dto);
    		}

    	    }
    	   
    	} catch (Exception e) {
    	    logger.error(e);

    	}
    	return treasuryList;
        }
    
    /******************************************************************  
     *   Method Name  :   ChallanDwnldInsert()
     *   Arguments    :   Cash Form 
     *   Return       :   Boolean
     *   Throws 	  :   NullPointer  or SQLException or Exception
     * @param userId 
     * @param jspPage 
    *******************************************************************/  
    public  boolean ChallanDwnldInsert (PaymentForm frm, String userId, String jspPage)throws NullPointerException,
    SQLException,Exception{
    	boolean flg = false;
    	PaymentDAO dao = new PaymentDAO();
    	
    		flg = dao.ChallanDwnldInsert(frm,userId,jspPage);
    	
    	return flg;
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
    public  boolean onlineDwnldInsert (PaymentForm frm, String userId, String crn, String jspPage)throws NullPointerException,
    SQLException,Exception{
    	boolean flg = false;
    	PaymentDAO dao = new PaymentDAO();
    	
    		flg = dao.onlineDwnldInsert(frm,userId,crn,jspPage);
    	
    	return flg;
    }
    /******************************************************************  
	  *   Method Name  :   getDwnldChallanInsertedDet()
	  *   Arguments    :   unique id
	  *   Return       :   ArrayList
	  *   Throws 	  :   Exception
     * @param uniqid 
	 *******************************************************************/  
	
	public ArrayList getDwnldChallanInsertedDet(String uniqid) throws Exception{
		PaymentDAO dao = new PaymentDAO();
		ArrayList list = dao.getDwnldChallanInsertedDet(uniqid);
      
		return list;
	}
	
	/***********************************************************
	    *  Method Name :  getDwnldChallanInsertedDet()
	    *  	   
	    *  Return      :  ArrayList
	    *  Exception   :  NullPointer or Exception
	 * @param uniqid 
	    ***********************************************************/     
	   public ArrayList getDistIdOfficeIdForRUChallan(PaymentForm eform) throws NullPointerException ,Exception{
		   ArrayList returnList = new ArrayList();
		   PaymentDAO dao = new PaymentDAO();
		   try{			   
			   returnList = dao.getDistIdOfficeIdForRUChallan(eform);
			  
	     }catch (NullPointerException ne) {
		  
	    }catch (Exception e) {
	    	 
		}
	       return returnList;
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
			PaymentDAO dao = new PaymentDAO();
			return dao.getCurrDateTime();
		}
    
    public synchronized String getCRNumber(String majorHead ) throws Exception
    {
    	Date date = new Date();
		  Format yearformat  = new SimpleDateFormat("yyyy");
		  Format monthformat = new SimpleDateFormat("MM");
		  Format dateformat  = new SimpleDateFormat("dd");
		  Format hourformat  = new SimpleDateFormat("hh");
		  Format minformat  = new SimpleDateFormat("mm");
		  Format secformat  = new SimpleDateFormat("ss");
		  String dfmt = dateformat.format(date);
		  String yfmt = yearformat.format(date);
		  String mfmt = monthformat.format(date);
		  String hfmt = hourformat.format(date);
		  String mifmt = minformat.format(date);
		  String sfmt = secformat.format(date);
		//  String sequence =String.valueOf(PaymentBO.generatePin()) ;//.paPaymentBO.generatePin();//paymentDao.getSequence();
		 // System.out.println("sequence1---"+sequence);
		  String sequence =paymentDao.getIfmisCRNSequence();
		//  String sequence =paymentDao.getSequence();
		  System.out.println("sequence---"+sequence);
		  String major_head="0030";
		  major_head=majorHead; //changed by saurav
		  System.out.println("Date format---"+dfmt + mfmt + yfmt);
		//  String fldrnm ="IGR" + dfmt + mfmt + yfmt + hfmt + mifmt + sfmt + sequence;
		  String fldrnm ="IGR"+ major_head + dfmt + mfmt + yfmt + sequence;
		 	  
    	System.out.println("checking crn number inside bo class"+fldrnm);
		return fldrnm;
    	
    }
    //Method added by Ajit to generate 6 digit sequence to create 21 digit CRN
	public static int generatePin()  {
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		  
		int num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
		num = generator.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) {
		try {
			throw new Exception("Unable to generate PIN at this time..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
		return num;
		}
    //added by sanjeev for ifmis 
    //New method for IFMIS URN , required at treasury side for unique transaction
    public String getURN()
    {
    	Date date = new Date();
		  Format yearformat  = new SimpleDateFormat("yyyy");
		  Format monthformat = new SimpleDateFormat("MM");
		  Format dateformat  = new SimpleDateFormat("dd");
		  String dfmt = dateformat.format(date);
		  String yfmt = yearformat.format(date);
		  String mfmt = monthformat.format(date);
		  
		  String sequence =	paymentDao.getIfmisURNSequence();	
		  String urnPattern ="MPT"+"IGR" + dfmt + mfmt + yfmt + sequence;
		  
		 	  
    	System.out.println("checking URN number inside bo class"+urnPattern);
		return urnPattern;
    	
    }
    public static void main(String[] args) {
		PaymentBO b = new PaymentBO();
		try {
			b.getCRNumber("0029");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateIP(String dwnldChallanUniqueId, String onlineIp) {
		PaymentDAO da = new PaymentDAO();
		da.updateIP(dwnldChallanUniqueId,onlineIp);
		
	}
	/***********************************************************
	    *  Method Name :  getDwnldChallanInsertedDet()
	    *  	   
	    *  Return      :  ArrayList
	    *  Exception   :  NullPointer or Exception
	 * @param uniqid 
	    ***********************************************************/     
	   public ArrayList getTreasuryData(String crn) throws NullPointerException ,Exception{
		   ArrayList returnList = new ArrayList();
		   PaymentDAO dao = new PaymentDAO();
		   try{	
			   logger.debug("crn in getTreasuryData in PAymentBO is---"+crn);
			   returnList = dao.getTreasuryData(crn);
			   logger.debug("TreasuryDataBO:-"+ returnList);
	     }catch (NullPointerException ne) {
		  
	    }catch (Exception e) {
	    	 
		}
	       return returnList;
	  } 
	    /******************************************************************  
		  *   Method Name  :   getPayDtls()
		  *   Arguments    :   unique id
		  *   Return       :   ArrayList
		  *   Throws 	  :   Exception
	     * @param uniqid 
		 *******************************************************************/  
		
		public ArrayList getPayDtls(PaymentForm eForm) throws Exception{
			PaymentDAO dao = new PaymentDAO();
			ArrayList list = dao.getPayDtls(eForm);
	      
			return list;
		}
		public synchronized  boolean MutationChallanPayment (PaymentForm form,HttpSession session)throws SQLException,Exception{
			boolean flg = false;
			PaymentDAO dao = new PaymentDAO();

			System.out.println("In MutationChallanPayment of payment bd");
			logger.debug("In MutationChallanPayment of payment bo");
				flg = dao.MutationChallanPayment(form,session);
			
			return flg;
		}
		public synchronized  boolean PaymentOnlineTransactionFinalSP3 (PaymentForm form,HttpSession session)throws NullPointerException,
		SQLException,Exception{
			boolean flg = false;
			PaymentDAO dao = new PaymentDAO();
			
				flg = dao.PaymentOnlineTransactionFinalSP3(form,session);
			
			return flg;
		}
}// Close BO

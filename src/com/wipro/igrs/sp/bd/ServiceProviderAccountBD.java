/**
 * ServiceProviderAccountBD.java
 */

package com.wipro.igrs.sp.bd;


import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.reginit.dao.RegCommonDAO;
import com.wipro.igrs.sp.dao.ServiceProviderAccountDAO;
import com.wipro.igrs.sp.dto.ServiceProviderAccountDTO;
import com.wipro.igrs.util.CommonUtil;


/**
 * @author root
 * 
 */
public class ServiceProviderAccountBD {
	
	private static Logger logger = 
		(Logger) Logger.getLogger(ServiceProviderAccountBD.class);

	/**
	 * Constructor name : ServiceProviderAccountBD
	 * 
	 */
	ServiceProviderAccountDAO serviceProviderDAO = null;

	/**
	 * Constructor name : ServiceProviderAccountBD
	 * 
	 * @throws Exception
	 */
	public ServiceProviderAccountBD() throws Exception {
		serviceProviderDAO = new ServiceProviderAccountDAO();
	}

	/**
	 * Method name :getUserType
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 * 
	 */
	public String getUserType(String userid) throws Exception {
		String usertype = "";
		ArrayList arrayList = serviceProviderDAO.getUserType(userid);
		for (int i = 0; i < arrayList.size(); i++) {
			ArrayList templist = (ArrayList) arrayList.get(i);
			usertype = templist.get(0).toString();
		}
		return usertype;
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
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = serviceProviderDAO
			.getServiceProviderInfo(userid);
			logger.info("arraylist" + arrayList);
			providerinfolist = new ArrayList();
			ServiceProviderAccountDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderAccountDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setTxnid((String)templist.get(0));
				//providerDTO.setLicencetxn((String)templist.get(0));
				providerDTO.setLicencenumber((String)templist.get(1));
				//providerDTO.setPlace((String)templist.get(2));
				providerDTO.setAccountantname((String)templist.get(2));
				providerDTO.setAccountbalance((String)templist.get(3));
				providerinfolist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return providerinfolist;
	}
	
	//added by shruti
	public ArrayList getSpAcntDetails(String userid,String licenseno) throws Exception {
		ArrayList templist=new ArrayList();
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getSpAcntDetails(userid,licenseno);
			if(arrayList.size()==0)
			{
				ArrayList arrayList1 = new ArrayList();
				arrayList1=serviceProviderDAO.getSpDetails(userid);
				arrayList1.trimToSize();
				logger.info("arraylist" + arrayList1);
				logger.info("SIZE OF ARRAYLIST:---"+arrayList1.size());
				if(arrayList1.size()==0)
				{
					arrayList1 = serviceProviderDAO.getSpBankDetails(userid);
				}
				
				for (int i = 0; i < arrayList1.size(); i++) {
					 templist = (ArrayList) arrayList1.get(i);
				}
			}
			else
			{
			for (int i = 0; i < arrayList.size(); i++) {
				 templist = (ArrayList) arrayList.get(i);}
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
	
		return templist;
	}
	//end of code
	/**
	 * Method name :getServiceProviderStmt
	 * 
	 * @param userid
	 * @param accountDTO
	 * @return
	 * @throws Exception
	 * 
	 */
	//modified by shruti
	public ArrayList getServiceProviderStmt(String licenseno,
			ServiceProviderAccountDTO accountDTO) throws Exception {
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getServiceProviderStmt(
					licenseno, accountDTO);
			logger.info("arraylist" + arrayList);
			providerinfolist = new ArrayList();
			ServiceProviderAccountDTO providerDTO = null;
			if(arrayList.size()==0)
			{
					//Date date=new Date();					
					//SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
					providerDTO = new ServiceProviderAccountDTO();	
					//providerDTO.setPaymentdate(dt.format(date).toString());
					providerDTO.setPaymentdate("-");
					providerDTO.setTransactionno("-");
					providerDTO.setPaymenttypename("-");
					providerDTO.setTxnstatus("-");
					providerDTO.setPaymentamount("-");
					providerDTO.setTxnpurpose("-");
					providerDTO.setAccountbalance("0");
					providerDTO.setSpcomm("");
					providerinfolist.add(providerDTO);
			}
			else
			{
			for (int i = 0; i < arrayList.size(); i++)
			{
				providerDTO = new ServiceProviderAccountDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				logger.info(templist);
				providerDTO.setPaymentdate((String)templist.get(0));
				providerDTO.setTransactionno((String)templist.get(1));
				providerDTO.setPaymenttypename((String)templist.get(2));
				providerDTO.setTxnstatus((String)templist.get(3));
				providerDTO.setPaymentamount((String)templist.get(4));
				providerDTO.setTxnpurpose((String)templist.get(5));
				providerDTO.setAccountbalance((String)templist.get(6));
				providerDTO.setSpcomm((String)templist.get(7));
				providerinfolist.add(providerDTO);
			}
			}
				
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return providerinfolist;
	}
	
	public ArrayList getServiceProviderStmtonMonth(String userid,
			ServiceProviderAccountDTO accountDTO) throws Exception {
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = serviceProviderDAO.getServiceProviderStmtmonth(
					userid, accountDTO);
			logger.info("arraylist" + arrayList);
			providerinfolist = new ArrayList();
			ServiceProviderAccountDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderAccountDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				logger.info(templist);
				providerDTO.setPaymentdate((String)templist.get(0));
				providerDTO.setTransactionno((String)templist.get(1));
				providerDTO.setPaymenttypename((String)templist.get(2));
				providerDTO.setPaymentamount((String)templist.get(3));
				providerinfolist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return providerinfolist;
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
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = serviceProviderDAO
			.getServiceProviderBankInfo(userid);
			logger.info("arraylist" + arrayList);
			providerinfolist = new ArrayList();
			ServiceProviderAccountDTO providerDTO = null;
			for (int i = 0; i < arrayList.size(); i++) {
				providerDTO = new ServiceProviderAccountDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				providerDTO.setLicencetxn((String)templist.get(0));
				providerDTO.setLicencenumber((String)templist.get(1));
				providerDTO.setPlace((String)templist.get(2));
				providerDTO.setAccountantname((String)templist.get(3));
				providerDTO.setAccountbalance((String)templist.get(4));
				providerinfolist.add(providerDTO);
			}
		} catch (Exception e) {
			logger.info("Exception in getSPUsers() -ServiceProviderBD"
					+ e);
		}
		return providerinfolist;
	}
	
	//below code added/modified by shruti
	
	public boolean spAcntBalUpdt(ServiceProviderAccountDTO accountDTO,String userid) throws Exception {
		
			return serviceProviderDAO.spAcntBalUpdt(accountDTO, userid);
		
	}
		
	public boolean spDebitAcntBalUpdt(ServiceProviderAccountDTO accountDTO,String userid) throws Exception {
		
		return serviceProviderDAO.spDebitAcntBalUpdt(accountDTO, userid);
	
}
	
	public ArrayList spCreditBalance(String userid) throws Exception
	{
		ArrayList templist=null;
		try{
		
			ServiceProviderAccountBD bd=new ServiceProviderAccountBD();
			String lno=bd.getLicenseNumber(userid);
			
		ArrayList list=serviceProviderDAO.spCreditBalance(lno);
		for(int i=0;i<list.size();i++)
		{
			templist=(ArrayList)list.get(i);
		}
		}
		 catch (Exception e) {
				logger.info("Exception in getSPUsers() -ServiceProviderBD"
						+ e);
			}
		 return templist;
	}

	//added by shruti for maintaining transactions regarding debit amount
	public boolean getSPBalanceAfterDebit(String userid,String status,String paymode,String amount) {	
		boolean dbt = false;
		boolean flag = false;
		String forwardpage=null;
		try {
		String uid = userid;
		String txnNo=status;
		String paymentType=paymode;
		String amnt=amount;
		
				ServiceProviderAccountBD providerBD = new ServiceProviderAccountBD();
				ServiceProviderAccountDTO accountDTO=new ServiceProviderAccountDTO();
				ArrayList balList=new ArrayList();
				balList=providerBD.spCreditBalance(uid);
				String creditLimit=balList.get(0).toString();
				String lno=balList.get(1).toString();
				//double crdtLimit = (Double.valueOf(creditLimit.trim()).doubleValue());
				double crdtLimit=Double.parseDouble(creditLimit);
				double amt = Double.parseDouble(amnt);
				
				DecimalFormat df = new DecimalFormat("#0.00");
				
				String funId="FUN_023";
				if(funId=="FUN_023")
				{
				String commFac=serviceProviderDAO.getSpCommFactor();
				double comm=Double.parseDouble(commFac) ;
				double commAmt=(comm*(amt))/100;
				
				//rounding of commission factor
				String spcomm=df.format(commAmt);
				//String spcomm=Double.toString(commAmt);
				//amt=Double.parseDouble(amnt)-(commAmt);
				amt=Double.parseDouble(amnt)-Double.parseDouble(spcomm);
				accountDTO.setSpcomm(spcomm);
				}
				else
				{
				amt = Double.parseDouble(amnt);
				}
				amount=Double.toString(amt);
				//rounding of credit limit figure.
			    double remCredit =(crdtLimit - amt);
			    //String rmcrdt=Double.toString(remCredit).trim();
			    String rmcrdt=df.format(remCredit); 
			    accountDTO.setLicencenumber(lno);
			    accountDTO.setPaymenttypename(paymentType);
			    accountDTO.setPaymentamount(amount);
			    accountDTO.setTransactionno(txnNo);
			    accountDTO.setTxnpurpose("E-Stamp");
			    accountDTO.setAccountbalance(rmcrdt);

			flag=providerBD.spDebitAcntBalUpdt(accountDTO, userid);
			if (flag==true)
			{
			dbt = true;
			}
					
		} catch (Exception e) {
			logger.info("Exception in execute() ServiceProviderAction"
					+ e);
			/*logger.info("Exception in Action" + e);
*/		}
		return dbt;
	}
	
	public String getLicenseNumber(String userid) throws Exception {
		String licenseno = "";
		ArrayList arrayList = serviceProviderDAO.getLicenseNumber(userid);
		
		for (int i = 0; i < arrayList.size(); i++) {
			ArrayList templist = (ArrayList) arrayList.get(i);
			licenseno = templist.get(0).toString();
		}
		return licenseno;
	}
	public String getPaymentFlag(String sr_no) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
			return serviceProviderDAO.getPaymentFlag(sr_no);
	}
	public String getCrdtLimitSerialNumber() throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
			return serviceProviderDAO.getCrdtLimitSerialNumber();
	}
	public boolean getUpdatedPaymentFlag(String sr_no) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
		return serviceProviderDAO.getUpdatedPaymentFlag(sr_no);
	}
	
	public String getPaidAmt(String sr_no) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
		return serviceProviderDAO.getPaidAmt(sr_no);
	}
	
	public String getCreditAmt(String licenseno) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
			return serviceProviderDAO.getCreditAmt(licenseno);
	}
	public String getDebitAmt(String licenseno) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
		return serviceProviderDAO.getDebitAmt(licenseno);
	}
	public boolean getUpdatedSPAmtFlag(String spAmt,String sr_no) throws Exception
	{
		ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
		return serviceProviderDAO.getUpdatedSPAmtFlag(spAmt, sr_no);
	}
	
	
	//added by shruti for sp commission
    public String getSPCommission(String _funId,String _amt) throws Exception
    { 	
    ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
    return serviceProviderDAO.getSPComm(_funId, _amt);
	}
    //end
    public String getOpeningBal(String licno) throws Exception
    {
    	ServiceProviderAccountDAO serviceProviderDAO =new ServiceProviderAccountDAO();
    	return serviceProviderDAO.getOpeningBal(licno);
    }


}


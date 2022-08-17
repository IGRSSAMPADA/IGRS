/**
 * 
 */
package com.wipro.igrs.revenueManagement.bo;

/**
 * @author SHREERAJ KHARE
 *
 */

import java.util.ArrayList;
import com.wipro.igrs.revenueManagement.bd.RevMgtAdvBD;


public class RevMgtAdvBO {
	
	RevMgtAdvBD rmBd = new RevMgtAdvBD();
	
	/**
	 * form fetching advance payment records from license number
	 * @param licNo
	 * @return ArrayList
	 * @author SHREERAJ KHARE
	 */
	public ArrayList fetchSearchDetails(String licNo,String languageLocale) throws Exception{
		return rmBd.fetchSearchDetails(licNo,languageLocale);
	}
	
	/**
	 * form fetching advance payment records from Duration
	 * @param licNo
	 * @return ArrayList
	 * @author SHREERAJ KHARE
	 */
	public ArrayList fetchSearchDetails(String fromDate,String toDate,String languageLocale) throws Exception{
		return rmBd.fetchSearchDetails(fromDate,toDate,languageLocale);
	}
	
	/**
	 * form fetching full name of an individual from user_reg
	 * @param LICNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getIndvdualName(String licNo) throws Exception{
		return rmBd.getIndvdualName(licNo);
	}
	
	/**
	 * form fetching full name of the bank from user_reg
	 * @param licNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	/*public String getBankName(String licNo) throws Exception{
		return rmBd.getBankName(licNo);
	}*/
	/**
	 * Method name :getSPStmtEstamp
	 * 
	 * @param LicNo,flag
	 * @param accountDTO
	 * @return ArrayList
	 * @throws Exception
	 * @author SH836413
	 * 
	 */
	public ArrayList getSPStmtEstamp(String licNo,String flag,String languageLocale) throws Exception{
		return rmBd.getSPStmtEstamp(licNo,flag,languageLocale);
	}
	
	/**
	 * form fetching authorized person name of SP
	 * @param licNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getOthersName(String licNo,String languageLocale) throws Exception{
		return rmBd.getOthersName(licNo,languageLocale);
	}
	
	/**
	 * form fetching account balance of SP
	 * getAccountBal
	 * @param licNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getAccountBal(String licNo,String flag) throws Exception{
		return rmBd.getAccountBal(licNo,flag);
	}
	
	
	
	
}

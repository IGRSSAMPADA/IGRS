/**
 * 
 */
package com.wipro.igrs.revenueManagement.bd;

/**
 * @author SH836413
 *
 */
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.dao.RevMgtAdvDAO;
import com.wipro.igrs.revenueManagement.dto.RevMgtAdvDTO;


public class RevMgtAdvBD {
	private Logger logger = (Logger) Logger.getLogger(RevMgtAdvBD.class);
	RevMgtAdvDAO rmDao = new RevMgtAdvDAO();
	RevMgtAdvDTO rDTO = new RevMgtAdvDTO();	
	/**
	 * form fetching advance payment records from license number
	 * @param licNo
	 * @return ArrayList
	 * @author SH836413
	 */
	
	public ArrayList fetchSearchDetails(String licNo, String languageLocale) throws Exception{
		//RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	ArrayList resultList = rmDao.fetchSearchDetails(licNo);
	    	if(resultList!=null){
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in fetchSearchDetails in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	RevMgtAdvDTO rDTO = new RevMgtAdvDTO();	
                	rDTO.setLicenseNo((String)list.get(0));
                	
                	//rDTO.setLicenseFrom((String)list.get(2));
                	//rDTO.setLicenseTo((String)list.get(3));
                	
                	String fromdate=(String)list.get(2);
                	String todate=(String)list.get(3);
                	rDTO.setLicenseFrom(fromdate.substring(0,10));
                	rDTO.setLicenseTo(todate.substring(0,10));    
                	
                	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	rDTO.setSpType((String)list.get(1));
                    	rDTO.setCombDet((String)list.get(0)+"~"+(String)list.get(1)+"~"+(String)list.get(2)+"~"+(String)list.get(3)+"~"+(String)list.get(4));
                    	}else{
                    		rDTO.setSpType((String)list.get(5));
                    		rDTO.setCombDet((String)list.get(0)+"~"+(String)list.get(5)+"~"+(String)list.get(2)+"~"+(String)list.get(3)+"~"+(String)list.get(4));
                    	}
                	
                	
                	
                	returnList.add(rDTO);
                	logger.debug("in fetchSearchDetails in bd for loop end,LicNumber:-"+rDTO.getLicenseNo());
                	
                } 
			 }
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In fetchSearchDetails Exception "+e);
	    }
	    return returnList;
	}
	
	/**
	 * form fetching advance payment records from Duration
	 * @param licNo
	 * @return ArrayList
	 * @author SHREERAJ KHARE
	 */

	public ArrayList fetchSearchDetails(String fromDate,String toDate, String languageLocale) throws Exception{
	//	RevenueMgmtDAO dao = new RevenueMgmtDAO();        
		ArrayList returnList=new ArrayList();
	    try{
	    	ArrayList resultList = rmDao.fetchSearchDetails(fromDate,toDate);
	    	if(resultList!=null){
                for(int i=0;i<resultList.size();i++){
                	logger.debug("in fetchSearchDetails in bd for loop start:-" +resultList.get(i));
                	ArrayList list = (ArrayList)resultList.get(i);
                	RevMgtAdvDTO rDTO = new RevMgtAdvDTO();	
                	rDTO.setLicenseNo((String)list.get(0));
                	//rDTO.setSpType((String)list.get(1));
                	String fromdate=(String)list.get(2);
                	String todate=(String)list.get(3);
                	rDTO.setLicenseFrom(fromdate.substring(0,10));
                	rDTO.setLicenseTo(todate.substring(0,10));                
                	rDTO.setCombDet((String)list.get(0)+"~"+(String)list.get(1)+"~"+(String)list.get(2)+"~"+(String)list.get(3)+"~"+(String)list.get(4));
                	
                	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	rDTO.setSpType((String)list.get(1));
                    	rDTO.setCombDet((String)list.get(0)+"~"+(String)list.get(1)+"~"+(String)list.get(2)+"~"+(String)list.get(3)+"~"+(String)list.get(4));
                    	}else{
                    		rDTO.setSpType((String)list.get(5));
                    		rDTO.setCombDet((String)list.get(0)+"~"+(String)list.get(5)+"~"+(String)list.get(2)+"~"+(String)list.get(3)+"~"+(String)list.get(4));
                    	}
                	
                	
                	
                	
                	returnList.add(rDTO);
                	logger.debug("in fetchSearchDetails in bd for loop end,LicNum:-"+rDTO.getLicenseNo());
                	
                } 
			 }
	    }
	    catch(Exception e){
			e.getStackTrace();
	        logger.debug("In fetchSearchDetails Exception "+e);
	    }
	    return returnList;
	}
	
	/**
	 * form fetching full name of an individual from user_reg
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getIndvdualName(String licNo) throws Exception{
		logger.debug("in getIndvdualName in bd LicNo:-" +licNo);
		String name="";
		name= rmDao.getIndvdualName(licNo);
		rDTO.setIndvName(name);
		return name;
	}
	/**
	 * form fetching full name of the bank from user_reg
	 * @param userID
	 * @return String
	 * @author SHREERAJ KHARE
	 */
/*	public String getBankName(String licNo) throws Exception{
		logger.debug("in getBankName in bd LicNo:-" +licNo);
		String name="";
		name= rmDao.getBankName(licNo);
		rDTO.setIndvName(name);
		return name;
	}*/
	/**
	 * form fetching authorized person name of SP
	 * @param licNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getOthersName(String licNo,String languageLocale) throws Exception{
		logger.debug("in getOthersName in bd LicNo:-" +licNo);
		String name="";
		name= rmDao.getOthersName(licNo,languageLocale);
		rDTO.setIndvName(name);
		return name;
	}
	
	/**
	 *form fetching account balance of SP
	 * getAccountBal
	 * @param licNo
	 * @return String
	 * @author SHREERAJ KHARE
	 */
	public String getAccountBal(String licNo,String flag) throws Exception{
		logger.debug("in getAccountBal in bdlicno,flag:-" +licNo+flag);
		String bal="";
		bal= rmDao.getAccountBal(licNo,flag);
		rDTO.setAccountbalance(bal);
		return bal;
	}
	/**
	 * Method name :getSPStmtEstamp
	 * 
	 * @param userid
	 * @param accountDTO
	 * @return
	 * @throws Exception
	 * @author SH836413
	 * 
	 */
	public ArrayList getSPStmtEstamp(String licenseno,String flag,String languageLocale) throws Exception {
		logger.debug("in getAccountBal in bdlicno,flag:-" +licenseno+flag);
		ArrayList providerinfolist = null;
		try {
			ArrayList arrayList = rmDao.getSPStmtEstamp(licenseno,flag);
			logger.info("arraylist" + arrayList);
			providerinfolist = new ArrayList();
			RevMgtAdvDTO providerDTO = null;
			if(arrayList.size()==0)
			{
					//Date date=new Date();					
					//SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
					providerDTO = new RevMgtAdvDTO();	
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
				providerDTO = new RevMgtAdvDTO();
				ArrayList templist = (ArrayList) arrayList.get(i);
				logger.info(templist);
				providerDTO.setPaymentdate((String)templist.get(0));
				providerDTO.setTransactionno((String)templist.get(1));
				
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				providerDTO.setPaymenttypename((String)templist.get(2));
				providerDTO.setTxnstatus((String)templist.get(3));
				providerDTO.setTxnpurpose((String)templist.get(5));
				}else{
					
					providerDTO.setPaymenttypename((String)templist.get(9));
					
					if(templist.get(3).toString().equalsIgnoreCase("D")){
						
						providerDTO.setTxnstatus(CommonConstant.DEBIT_HINDI);
					}else{
						providerDTO.setTxnstatus(CommonConstant.CREDIT_HINDI);
					}
					
					//providerDTO.setTxnstatus((String)templist.get(3));
					providerDTO.setTxnpurpose((String)templist.get(8));
					
				}
				
				
				
				providerDTO.setPaymentamount((String)templist.get(4));
				providerDTO.setAccountbalance((String)templist.get(6));
				providerDTO.setSpcomm((String)templist.get(7));
				providerinfolist.add(providerDTO);
			}
			}
				
		} catch (Exception e) {
			logger.info("Exception in getSPStmtEstamp() -ServiceProviderBD"
					+ e);
		}
		return providerinfolist;
	}
}

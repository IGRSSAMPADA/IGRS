/**
 * 
 */
package com.wipro.igrs.revenueManagement.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.CitizenFeedback.dao.CitizenFeedbackViewDAO;
import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.revenueManagement.bd.RevMgtBD;
import com.wipro.igrs.revenueManagement.bd.RevenueMgtBD;
import com.wipro.igrs.revenueManagement.dao.RevMgtDAO;
import com.wipro.igrs.revenueManagement.dto.RevMgtDTO;

/**
 * @author RA836784
 *
 */
public class RevMgtBO {

	/*public ArrayList getViewDetails(RevMgtDTO actionDTO) {
		
		ArrayList listbo=new ArrayList();
		// TODO Auto-generated method stub
		return listbo;
	}
	*/
/*
	public ArrayList getDetails(String txnId) throws Exception
	{
		RevMgtDAO dao = new RevMgtDAO();
		
		ArrayList ret = dao.getDetails(txnId);
		System.out.println(ret);
		ArrayList list = new ArrayList();
		
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				RevMgtDTO dto = new RevMgtDTO();
				dto.setTxnId((String)lst.get(0));
				dto.setTxnAmt((Double.parseDouble((String)lst.get(1))));
				dto.setTxnDate((String) lst.get(2));
			//	System.out.println("at o index" + lst.get(0));
				dto.setPaymentType((String)lst.get(3));
				dto.setTxnPurpose((String)lst.get(4);
				dto.setEmailID((String) lst.get(1));
				dto.setCityDistrict((String) lst.get(2));
				dto.setFeedbackFunctionName((String) lst.get(3));
				dto.setFeedback((String) lst.get(4));
				list.add(dto);
			}
		}
		return list;
	}*/
	
	//For Cash Transaction Mapping
	
public ArrayList getFilteredresults(String param[],String languageLocale) throws Exception{
		
		RevMgtDAO dao = new RevMgtDAO();
		
		
		
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list = dao.getFilteredresults(param,languageLocale);
		
		
	
			return list;
	}


//For Credit Limit Transaction Mapping

public ArrayList getFilteredresults1(String param[],String languageLocale) throws Exception{
	
	RevMgtDAO dao = new RevMgtDAO();
	
	
	
	ArrayList pendingAppListFinal = new ArrayList();
	ArrayList list = dao.getFilteredresults1(param,languageLocale);
	
	

		return list;
}

/*//For Cash Receipt Search
public ArrayList getFilteredresults2(String param[]) throws Exception{
	
	RevMgtDAO dao = new RevMgtDAO();
	
	
	
	ArrayList pendingAppListFinal = new ArrayList();
	ArrayList list = dao.getFilteredresults2(param);
	
	

		return list;
}

//For Challan Receipt Search
public ArrayList getFilteredresults3(String param[]) throws Exception{
	
	RevMgtDAO dao = new RevMgtDAO();
	
	
	
	ArrayList pendingAppListFinal = new ArrayList();
	ArrayList list = dao.getFilteredresults3(param);
	
	

		return list;
}


//For Online Receipt Search
public ArrayList getFilteredresults4(String param[]) throws Exception{
	
	RevMgtDAO dao = new RevMgtDAO();
	
	
	
	ArrayList pendingAppListFinal = new ArrayList();
	ArrayList list = dao.getFilteredresults4(param);
	
	

		return list;
}*/


/******************************************************************  
  *   Method Name  :   getDistrictList()
  *   Arguments    :   -
  *   Return       :   ArrayList
  *   Throws 	  :   Exception
 *******************************************************************/  
public ArrayList getDistrictList(String languageLocale) throws Exception{
	RevMgtBD revBD = new RevMgtBD();
	ArrayList desDtl =new ArrayList();
	try{
		desDtl =revBD.getDistrictList(languageLocale);
	
	}catch(Exception e){
	}
	return desDtl;
}
/******************************************************************  
  *   Method Name  :   getOfficeTypeList()
  *   Arguments    :   -
  *   Return       :   ArrayList
  *   Throws 	  :   Exception
 *******************************************************************/  
public ArrayList getOfficeTypeList(String disId,String languageLocale) throws Exception{
	RevMgtBD revBD = new RevMgtBD();
	ArrayList desDtl =new ArrayList();
	try{
		desDtl =revBD.getOfficeTypeList(disId,languageLocale);
	
	}catch(Exception e){
	}
	return desDtl;
}

/******************************************************************  
  *   Method Name  :   getOfficeNameList()
  *   Arguments    :   -
  *   Return       :   ArrayList
  *   Throws 	  :   Exception
 * @param offtyp 
 *******************************************************************/  
public ArrayList getOfficeNameList(String disId, String offtyp,String languageLocale) throws Exception{
	RevMgtBD revBD = new RevMgtBD();
	ArrayList desDtl =new ArrayList();
	try{
		desDtl =revBD.getOfficeNameList(disId, offtyp,languageLocale);
	
	}catch(Exception e){
	}
	return desDtl;
}

public String getOfficeName(String id,String languageLocale) throws Exception {
	
	RevMgtBD revBD = new RevMgtBD();
		return revBD.getOfficeName(id,languageLocale);
	}


/******************************************************************  
  *   Method Name  :   getPurposeList(String)
  *   Arguments    :   -
  *   Return       :   ArrayList
  *   Throws 	  :   Exception
  *   Author	   :  Shreeraj
 *******************************************************************/  
public ArrayList getPurposeList(String languageLocale) throws Exception{
	RevMgtBD revBD = new RevMgtBD();
	ArrayList desDtl =new ArrayList();
	try{
		desDtl =revBD.getPurposeList(languageLocale);
	
	}catch(Exception e){
	}
	return desDtl;
}

}

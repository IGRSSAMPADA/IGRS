package com.wipro.igrs.copyissuance.bd;

/**
 * ===========================================================================
 * File           :   CertifiedBD.java
 * Description    :   Represents the Business Class

 * Author         :   Dev Pradhan
 * Created Date   :   Jan 8, 2008

 * ===========================================================================
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.copyissuance.dao.CertifiedCopyDetailsDAO;
import com.wipro.igrs.copyissuance.dto.CertifiedCopyDetailsDTO;
import com.wipro.igrs.copyissuance.dto.PaymentDTO;
import com.wipro.igrs.copyissuance.form.CertifiedActionForm;
import com.wipro.igrs.copyissuance.util.CommonUtil;
import com.wipro.igrs.noEncumbrance.bo.NoEncumBO;
//import com.wipro.igrs.propertylock.dao.PropertyLockingDAO;
//import com.wipro.igrs.propertylock.dto.PropertyLockingDTO;
public class CertifiedBD
{
    private static Logger log = org.apache.log4j.Logger.getLogger(CertifiedBD.class);
    

    public CertifiedBD() {
    }


    /**
     * @since       :  08-01-2008
     * Method       :  getCountry
     * Description  :  This method sets the country list
     * @return list :  ArrayList
     * @throws      :  Exception
     */
    public ArrayList getCountry() throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList ret = dao.getCountry();
        ArrayList list = new ArrayList();
        if (ret != null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList lst = (ArrayList)ret.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setCountryId((String)lst.get(0)+"~"+(String)lst.get(1));
                dto.setCountry((String)lst.get(1));
                list.add(dto);
            }
        }
        return list;
    }



    /**
     * @since       :  08-01-2008
     * Method       :  getState
     * Description  :  This method sets the state list
     * @param       :  stateId
     * @return list :  ArrayList
     * @throws      :  Exception
     */
    public ArrayList getState(String countryId) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList ret = dao.getState(countryId);
        ArrayList list = new ArrayList();
        if (ret != null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList lst = (ArrayList)ret.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setStateId((String)lst.get(0)+"~"+(String)lst.get(1));
                dto.setState((String)lst.get(1));
                list.add(dto);
            }
        }
        return list;
    }




    /**
     * @since       :  08-02-2008 
     * Method       :  getDistrict
     * Description  :  This method sets the district list
     * @param       :  stateId
     * @return list :  ArrayList
     * @throws      :  Exception
     */
    public ArrayList getDistrict(String stateId) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList ret = dao.getDistrict(stateId);
        ArrayList list = new ArrayList();
        if (ret != null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList lst = (ArrayList)ret.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setCityId((String)lst.get(0)+"~"+(String)lst.get(1));
                dto.setCity((String)lst.get(1));
                list.add(dto);
                
            }
        }
        return list;
    }


    /**
     * @since       :  10-02-2008
     * Method       :  getIDProof
     * Description  :  This method sets the ID proof list
     * @return list :  ArrayList
     * @throws      :  Exception
     */
    public ArrayList getIDProof(String language) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList ret = dao.getType();
        ArrayList list = new ArrayList();
        if (ret != null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList lst = (ArrayList)ret.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setId((String)lst.get(0));
                if("en".equalsIgnoreCase(language))
                {
                dto.setIdProof((String)lst.get(1));
                }
                else
                {
                    dto.setIdProof((String)lst.get(2));
                }
                list.add(dto);
            }
        }
        return list;
    }



    /**
     * @since      :  10-02-2008
     * Method      :  getFee
     * Description :  This method gets the fee details from DB
     * @return dto :  CertifiedCopyDetailsDTO
     * @throws     :  Exception
     */
   /* public CertifiedCopyDetailsDTO getFee() throws Exception  {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getFee();
        CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
        dto.setFee((String)list.get(0));
        dto.setPostalFee((String)list.get(1));
        int total = Integer.parseInt((String)list.get(0)) + Integer.parseInt((String)list.get(1));
        dto.setTotalFee("" + total);
        return dto;
    }*/



    /**
     * @since           :  12-02-2008
     * Method           :  insertChallanPayment
     * Description      :  This method inserts the challan payment details
     * @param al
     * @param userId 
     * @param funId 
     * @param roleId 
     * @return boolean  :  challanPayment
     * @throws          :  Exception
     */
    public boolean insertChallanPayment(ArrayList al, String roleId, String funId, String userId) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        boolean challanPayment = false;
        for (int i = 0;i < al.size();i++)
        {
            PaymentDTO dto = (PaymentDTO)al.get(i);
            String[] param = new String[2];
            param[0] = dto.getChallanNo();
            //param[1] = "12/12/2007";
            param[1] = dto.getAmount();
            challanPayment = dao.insertChallanPayment(param,roleId,funId,userId);
        }
        return challanPayment;
    }




    /**
     * @since             : 18-02-2008 
     * Method             : validatePayment
     * Description        : This method is used to validate the payment details from the db
     * @param challanNo	  : String
     * @param challanDt   : String
     * @param challanAmt  : String
     * @param bnkName     : String 
     * @return returnList : ArrayList
     * @throws Exception
     */
    public ArrayList validatePayment(String challanNo, String challanDt,
                                     String challanAmt, String bnkName) throws Exception {

        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.validatePayment(challanNo, challanDt, challanAmt, bnkName);
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);

                PaymentDTO dto = new PaymentDTO();
                dto.setChallanNo((String)lst.get(0));
                dto.setChallanDate((String)lst.get(1));
                dto.setAmount((String)lst.get(2));
                dto.setBankName((String)lst.get(3));

                dto.setPaymentTxnId((String)lst.get(4));

                returnList.add(dto);
            }
        }
        return returnList;
    }




    /**
     * @since         :  22-01-2008
     * Method         :  submitCertifiedInfo
     * Description    :  This method is to insert the certified copy of issuance details
     * into the database.   
     * @param dto     :  CertifiedCopyDetailsDTO 
     * @param userId 
     * @param funId 
     * @param roleId 
     * @param formFile3 
     * @param formFile2 
     * @param formFile 
     * @return String :  refId
     * @throws        :  Exception
     */
    
	public static String getOracleDate(String DateFormat) {
		String finalDate  =  "";
			if(DateFormat!= null || !DateFormat.equalsIgnoreCase("") )
			{
	    	StringTokenizer st = new StringTokenizer(DateFormat,"/");
	        String day = st.nextToken();
	        String month = st.nextToken();
	        String year = st.nextToken();
			String inputDate  =  day + "-" + month + "-" + year;
			SimpleDateFormat formatter  =  new SimpleDateFormat("dd-MM-yyyy");			
			
			Date newDate ;
			try {
				newDate  =  formatter.parse(inputDate);
				SimpleDateFormat format  =  new SimpleDateFormat("dd/MMM/yyyy");
				finalDate  =  format.format(newDate);	
			}
			catch (Exception e) {
				log.info(e);
			}
			}
			return  finalDate;
		}
    //public String submitCertifiedInfo(CertifiedCopyDetailsDTO dto, PaymentDTO payDTO, String roleId, String funId, String userId,FormFile photo, FormFile signature, FormFile thumb) throws Exception {
	/**
	 * 
	 * @param dto
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public String submitCertifiedInfo(CertifiedCopyDetailsDTO dto,  String roleId, String funId, String userId) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();

        boolean copyissuance = false;
        String retMsg="";
        try{
        copyissuance = dao.submitCertifiedInfo(dto,roleId,funId,userId);
        if(copyissuance){
        	
        	retMsg=dto.getCertifiedId();
      	  }
        else{
        	retMsg="";}
        
        log.debug("Cert submitCertifiedInfo  copyissuance ="+copyissuance);
        
        /*if(copyissuance)
            copyissuance =  dao.updateCertifiedInfo(photo,signature,thumb,issuanceparam[0]);
        */ //Commented by ramesh  as photo,thumb and signature not reqired now for copy issuance request
                  
        }
        catch (Exception e) {
            log.debug("Exception in submitCertifiedInfo()  copyissuance ="+copyissuance);
	    }
               
        return retMsg;
          
    }





    /**
     * @since             :  20-01-2008
     * Method             :  getIssuanceSearch
     * Description        :  This method is used to search the copy issuance details according to the given 
     * input data and set the output parameters.
     * @param certifiedId :  String
     * @param appStatus   :  String
     * @param fromDate    :  String
     * @param toDate      :  String
     * @return returnList : ArrayList
     * @throws            : Exception
     */
    public ArrayList getIssuanceSearch(String certifiedId, String appStatus, 
                                       String fromDate, String toDate, String regNo) throws Exception {

        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getIssuanceSearch(certifiedId, appStatus, fromDate, toDate, regNo);
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setTypeReq((String)lst.get(0));
                dto.setCreatedDt((String)lst.get(1));
                dto.setRegNo((String)lst.get(2));
                dto.setCertifiedId((String)lst.get(3));
                dto.setAppStatus((String)lst.get(4));
                returnList.add(dto);
            }
        }
        return returnList;
    }




    /**
     * @since             :  21-01-2008
     * Method             :  getIssuanceOnlineSearch
     * Description        :  This method is to search the copy issuance online request details 
     * according to the given input data. 
     * @param fromDate    :  String
     * @param toDate      :  String
     * @return returnList :  ArrayList
     * @throws            :  Exception
     */
  /*  public ArrayList getIssuanceOnlineSearch(String fromDate, String toDate, String referId,
                                             String regNo) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getIssuanceOnlineSearch(fromDate, toDate, referId, regNo);
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setTypeReq((String)lst.get(0));
                dto.setCreatedDt((String)lst.get(1));
                dto.setRegNo((String)lst.get(2));
                dto.setCertifiedId((String)lst.get(3));

                dto.setAppStatus((String)lst.get(4));
                log.debug("BD:-"+(String)lst.get(1));
                returnList.add(dto);
            }
        }
        return returnList;
    }
*/ //Ramesh commented on 19Jan13
 
    /**
     * 
     * @param form
     * @param fromDate
     * @param toDate
     * @param referId
     * @param regNo
     * @return
     * @throws Exception
     */
 public CertifiedActionForm getIssuanceOnlineSearch1(CertifiedActionForm  form,String fromDate, String toDate, String referId,
    String regNo,String officeId,String language) throws Exception {
	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
	ArrayList list = dao.getIssuanceOnlineSearch1(fromDate, toDate, referId, regNo,officeId);	
	ArrayList dataList = new ArrayList();
		if (list!=null)
		{
			for (int i=0;i<list.size();i++)
			{
				form =new CertifiedActionForm();
				ArrayList retList1=(ArrayList)list.get(i);
				if("en".equalsIgnoreCase(language))
				form.setTypeReq((String)retList1.get(0));
				else
				form.setTypeReq("प्रमाणित प्रति ");	
				form.setCreatedDt((String)retList1.get(1));
				form.setRegNo((String)retList1.get(2));
				form.setCertifiedId((String)retList1.get(3));
				String status=(String)retList1.get(4);
				
				if("D".equalsIgnoreCase(status))
				{
					if("en".equalsIgnoreCase(language))
					{	
					status="Dispatched";
					}
					else
					{
					status="भेजा";	
					}
				}
				else if("C".equalsIgnoreCase(status))
				{
					if("en".equalsIgnoreCase(language))
					{	
					status="Compelted";
					}
					else
					{
					status="संपूर्ण";	
					}
				}
				else
				{
					if("en".equalsIgnoreCase(language))
					{	
					status="Pending";
					}
					else
					{
					status="लंबित";	
					}
					
				}
				
				form.setAppStatus(status); 
				form.setReqCreatedAt("at SRO");
				dataList.add(form);             
			}
		form.setOnlineCopyViewList(dataList);
		}
	  return form;
	}
    
 public CertifiedActionForm getIssuanceOnlineSearchNoEncum1(CertifiedActionForm  form,String fromDate, String toDate, String referId,
		    String regNo,String officeId,String language) throws Exception {
			CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
			ArrayList list = dao.getIssuanceOnlineSearchNoEncum1(fromDate, toDate, referId, regNo);	
			ArrayList dataList = new ArrayList();
				if (list!=null)
				{
					for (int i=0;i<list.size();i++)
					{
						form =new CertifiedActionForm();
						ArrayList retList1=(ArrayList)list.get(i);
						if(retList1.get(2)!=null &&!((String)retList1.get(2)).equalsIgnoreCase(""))
						{
							/*if(officeId.equalsIgnoreCase("ofc01"))
							{
								form.setTypeReq((String)retList1.get(0));
								form.setCreatedDt((String)retList1.get(1));
								form.setRegNo(((String)retList1.get(2)).equalsIgnoreCase("")?"NA":(String)retList1.get(2));
								form.setCertifiedId((String)retList1.get(3));
								String status=(String)retList1.get(4);
								
								if("D".equalsIgnoreCase(status)){status="Dispatched";}
								else
								{
									status="Un-delivered";
								}
								form.setAppStatus(status); 
								form.setReqCreatedAt("at SRO");
								dataList.add(form);
							}*/
							 if(dao.checkOfficeNoEncum((String)retList1.get(2), officeId))
							{
								 if("en".equalsIgnoreCase(language))
								form.setTypeReq((String)retList1.get(0));
								 else
								form.setTypeReq("भारहीनता प्रमाणपत्र ");
								form.setCreatedDt((String)retList1.get(1));
								form.setRegNo((String)retList1.get(2));
								form.setCertifiedId((String)retList1.get(3));
								String status=(String)retList1.get(4);
								
								if("D".equalsIgnoreCase(status)){
									if("en".equalsIgnoreCase(language))
									{	
									status="Dispatched";
									}
									else
									{
									status="भेजा";	
									}
									}
								else
								{
									if("en".equalsIgnoreCase(language))
									{	
										status="Un-delivered";
									}
									else
									{
									status="अप्राप्य";	
									}
									
									
								}
								form.setAppStatus(status); 
								form.setReqCreatedAt("at SRO");
								dataList.add(form);
							}
						}
						else
						{
							if(dao.checkOfficeDisrtictMapping(officeId,(String)retList1.get(3)))
							{
								 if("en".equalsIgnoreCase(language))
									 	form.setTypeReq((String)retList1.get(0));
								 else
										form.setTypeReq("भारहीनता प्रमाणपत्र ");
								form.setCreatedDt((String)retList1.get(1));
								form.setRegNo("NA");
								form.setCertifiedId((String)retList1.get(3));
								String status=(String)retList1.get(4);
								
								if("D".equalsIgnoreCase(status)){
									if("en".equalsIgnoreCase(language))
									{	
									status="Dispatched";
									}
									else
									{
									status="भेजा";	
									}
									}
								else
								{
									if("en".equalsIgnoreCase(language))
									{	
										status="Un-delivered";
									}
									else
									{
									status="अप्राप्य";	
									}
									
									
								}
								form.setAppStatus(status); 
								form.setReqCreatedAt("at SRO");
								dataList.add(form); 
							}
						}
						             
					}
				}
						form.setOnlineCopyViewList1(dataList);
				
			  return form;
			}
 /**
  * 
  * @param form
  * @param dto
  * @return
  * @throws Exception
  */
public CertifiedActionForm  getIssuanceViewSearch(CertifiedActionForm  form,CertifiedCopyDetailsDTO dto,String languageLocale) throws Exception 
      {
		CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
		String arraySearch[] = new String[5];
		arraySearch[0]=dto.getCertifiedId();
		arraySearch[1]=dto.getRegNo();
		arraySearch[2]=dto.getAppStatus();
		arraySearch[3]=dto.getFromRequestDate();
		arraySearch[4]=dto.getToRequestDate();
		ArrayList list = dao.searchByAllDAO(arraySearch,form,languageLocale);
		/*ArrayList returnList = new ArrayList();
		ArrayList dataList = new ArrayList();
		if (list!=null)
		{
		for (int i=0;i<list.size();i++)
		{
		form =new CertifiedActionForm();
		ArrayList retList1=(ArrayList)list.get(i);    
		form.setTypeReq((String)retList1.get(0));
		form.setCreatedDt((String)retList1.get(1));
		form.setRegNo((String)retList1.get(2));
		form.setCertifiedId((String)retList1.get(3));
		String status=(String)retList1.get(4);
		if("U".equalsIgnoreCase(status)){status="Un-delivered";}
		if("D".equalsIgnoreCase(status)){status="Dispatched";}
		form.setAppStatus(status); 
		form.setReqCreatedAt("at SRO");
		dataList.add(form);                 
		}
		form.setCopyViewList(dataList);
		}*/
		form.setCopyViewList(list);
		return form;
	 }
    


    /**
     * @since               : 13-02-2008
     * Method               : getIssuanceRequestSearch  
     * Description          : This method is to set the request search details according
     * to the given input data.
     * @param referId		: String
     * @param regNo			: String
     * @param fromDate		: String	
     * @param toDate		: String
     * @return returnList	: ArrayList
     * @throws Exception
     */
   /* public ArrayList getIssuanceRequestSearch(String referId, String regNo, String fromDate, String toDate) 
    throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getIssuanceRequestSearch(referId, regNo, fromDate, toDate);
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setTypeReq((String)lst.get(0));
                dto.setCreatedDt((String)lst.get(1));
                dto.setRegNo((String)lst.get(2));
                dto.setCertifiedId((String)lst.get(3));
                dto.setAppStatus((String)lst.get(4));
                log.debug("BD:-"+(String)lst.get(1));
                returnList.add(dto);
            }
        }
        return returnList;
    }


*/
   
/**
 * 
 * @param form
 * @param referId
 * @param regNo
 * @param fromDate
 * @param toDate
 * @return
 * @throws Exception
 */
    
public CertifiedActionForm getIssuanceRequestSearch(CertifiedActionForm form,String referId, String regNo, String fromDate, String toDate,String officeId) throws Exception
 {
	
	ArrayList dataList = new ArrayList();
	ArrayList list = new ArrayList();
	ArrayList retList1 = null;
	
	try{
		CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        list = dao.getIssuanceRequestSearch(referId, regNo, fromDate, toDate);
	    //list = objBo.getSpotViewRes(form);
	if(list!= null) 
	{
       for(int i = 0;i<list.size();i++)  
        {
    	  form =new CertifiedActionForm();
    	   retList1 = (ArrayList)list.get(i);
    	   if(dao.checkOfficeNoEncum((String)retList1.get(2), officeId))
			{
    	   form.setTypeReq((String)retList1.get(0));
    	   form.setCreatedDt((String)retList1.get(1));
    	   form.setRegNo((String)retList1.get(2));
    	   form.setCertifiedId((String)retList1.get(3));
    	   String cerStatus="";
    	   cerStatus=(String)retList1.get(4);
    	   if("D".equalsIgnoreCase(cerStatus)){cerStatus="Dispatched";}else{cerStatus="Un-delivered";}
    	   form.setAppStatus(cerStatus);   	   
    	   dataList.add(form);  
			}
       }
           form.setCopReqViewList(dataList);
     } 
  }
	 catch(Exception e)
	  {
	         log.info("this is Exception in getSpotViewRes in BD" + e);
	   }
	 return form;
 }
	
public CertifiedActionForm getIssuanceRequestSearchNoEncum(CertifiedActionForm form,String referId, String regNo, String fromDate, String toDate ,String officeId) throws Exception
{
	
	ArrayList dataList = new ArrayList();
	ArrayList list = new ArrayList();
	
	
	try{
		
		CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
       list = dao.getIssuanceRequestSearchNoEncum(referId, regNo, fromDate, toDate);
	    //list = objBo.getSpotViewRes(form);
	if(list!= null) 
	{
		for (int i=0;i<list.size();i++)
		{
			form =new CertifiedActionForm();
			ArrayList retList1=(ArrayList)list.get(i);
			if(retList1.get(2)!=null &&!((String)retList1.get(2)).equalsIgnoreCase(""))
			{
				/*if(officeId.equalsIgnoreCase("ofc01"))
				{
					form.setTypeReq((String)retList1.get(0));
					form.setCreatedDt((String)retList1.get(1));
					form.setRegNo(((String)retList1.get(2)).equalsIgnoreCase("")?"NA":(String)retList1.get(2));
					form.setCertifiedId((String)retList1.get(3));
					String status=(String)retList1.get(4);
					
					if("D".equalsIgnoreCase(status)){status="Dispatched";}
					else
					{
						status="Un-delivered";
					}
					form.setAppStatus(status); 
					form.setReqCreatedAt("at SRO");
					dataList.add(form);
				}*/
				 if(dao.checkOfficeNoEncum((String)retList1.get(2), officeId))
				{
					form.setTypeReq((String)retList1.get(0));
					form.setCreatedDt((String)retList1.get(1));
					form.setRegNo((String)retList1.get(2));
					form.setCertifiedId((String)retList1.get(3));
					String status=(String)retList1.get(4);
					
					if("D".equalsIgnoreCase(status)){status="Dispatched";}
					else
					{
						status="Un-delivered";
					}
					form.setAppStatus(status); 
					form.setReqCreatedAt("at SRO");
					dataList.add(form);
				}
			}
			else
			{
				if(dao.checkOfficeDisrtictMapping(officeId,(String)retList1.get(3)))
				{
					form.setTypeReq((String)retList1.get(0));
					form.setCreatedDt((String)retList1.get(1));
					form.setRegNo("NA");
					form.setCertifiedId((String)retList1.get(3));
					String status=(String)retList1.get(4);
					
					if("D".equalsIgnoreCase(status)){status="Dispatched";}
					else
					{
						status="Un-delivered";
					}
					form.setAppStatus(status); 
					form.setReqCreatedAt("at SRO");
					dataList.add(form); 
				}
			}
			             
		}
	} 
 }
	 catch(Exception e)
	  {
	         log.info("this is Exception in getSpotViewRes in BD" + e);
	   }
	 form.setCopReqViewList1(dataList);
	 return form;
}
    /**
     * @since                : 15-02-2008 
     * Method                : getIssuanceStatusSearch
     * Description           : This method is to get the status from the database
     * according to the input data. 
     * @param durationFrom   : String
     * @param durationTo     : String
     * @param referenceId    : String
     * @param regisNo        : String
     * @return returnList    : ArrayList 
     * @throws Exception
     */
    public ArrayList getIssuanceStatusSearch(String durationFrom, String durationTo, String referenceId,
                                             String regisNo) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getIssuanceStatusSearch(durationFrom, durationTo, referenceId, regisNo);
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setTypeReq((String)lst.get(0));
                dto.setCreatedDt((String)lst.get(1));
                dto.setRegNo((String)lst.get(2));
                dto.setCertifiedId((String)lst.get(3));
                String cerStatus="";
         	    cerStatus=(String)lst.get(4);
         	    if("D".equalsIgnoreCase(cerStatus)){cerStatus="Delivered";}else{cerStatus="Un-delivered";}
                dto.setAppStatus(cerStatus);
                log.debug("BD:-"+(String)lst.get(1));
                returnList.add(dto);
            }
        }
        return returnList;
    }




    /**
     * @since             :  15-01-2008
     * Method             :  getCopyIssuance
     * Description        :  This method is to set the copy issuance details for a particular id. 
     * @param certifiedid :  String
     * @param flag        :  String  
     * @return dto        :  CertifiedCopyDetailsDTO
     * @throws            :  Exception
     */
    public CertifiedCopyDetailsDTO getCopyIssuance(String certifiedid,String language) throws Exception {
        CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        String[] param = new String[1];
        param[0] = certifiedid;       
dto.setLanguage(language);
        ArrayList ret = dao.getCopyIssuance(param,language);
        if (ret!=null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList list = (ArrayList)ret.get(i);
                dto.setCertifiedId((String)list.get(0));  
                dto.setFirstName((String)list.get(1));
                dto.setMiddleName((String)list.get(2));
                dto.setLastName((String)list.get(3));
                String gender=(String)list.get(4);
                if("en".equalsIgnoreCase(language))
                { 	
                if("Male".equalsIgnoreCase(gender)){gender="Male";}
                if("Female".equalsIgnoreCase(gender)){gender="Female";}
                }
                else
                {
                	if("Male".equalsIgnoreCase(gender)){gender="पुस्र्ष";}
                    if("Female".equalsIgnoreCase(gender)){gender="महिला";}
                }
                dto.setGender(gender);
                dto.setAge((String)list.get(5));
                dto.setFatherName((String)list.get(6));
                dto.setMotherName((String)list.get(7));
                dto.setAddress((String)list.get(8));
                dto.setCity((String)list.get(9));
                dto.setCountry((String)list.get(10));
                dto.setState((String)list.get(11));
                dto.setPin((String)list.get(12));
                dto.setPhone((String)list.get(13));
                dto.setMphone((String)list.get(14));
                dto.setEmail((String)list.get(15));
                dto.setIdProof((String)list.get(16));
                dto.setIdProofNo((String)list.get(17));
                dto.setFee((String)list.get(18));
                dto.setPostalFee((String)list.get(19));
                dto.setTotalFee((String)list.get(20));
                dto.setIssuanceRemark((String)list.get(21));
                dto.setRegNo((String)list.get(22));
                if("en".equalsIgnoreCase(language))
                {	
                dto.setTypeReq((String)list.get(23));
                }
                else
                {
                	dto.setTypeReq("प्रमाणित प्रतिलिपि");	
                }
                dto.setPurposeReq((String)list.get(24));
                dto.setCreatedDt((String)list.get(25));
                dto.setAppStatus((String)list.get(26));
                dto.setModifiedDt((String)list.get(27));
                dto.setPaymentTxnId((String)list.get(28));
                dto.setPaymentAmount((String)list.get(29));
                dto.setPaymentMode((String)list.get(30));  
                dto.setDocumentId((String)list.get(31));
                dto.setPostalTrakingNum((String)list.get(32));
                dto.setDisDate((String)list.get(33));                
                dto.setBankName((String)list.get(34));
                dto.setBankAddress((String)list.get(35));
                dto.setTransPartyFirstName((String)list.get(36));
                dto.setTransPartyMidName((String)list.get(37));
                dto.setTransPartyLastName((String)list.get(38));
                dto.setTransPartySpouseName((String)list.get(39));
                dto.setTransPartyFGHName((String)list.get(40));
                dto.setTransPartyMotherName((String)list.get(41));                
                dto.setVolume((String)list.get(42));
                dto.setBookNo((String)list.get(43));
                dto.setNum((String)list.get(44));
                dto.setNumberDate((String)list.get(45));
                dto.setDocumentSavePath((String)list.get(46));
                dto.setSelectedItems(searchRegId(dto.getRegNo(),language));
                NoEncumBO objEncumBO = new NoEncumBO();
                dto.setRegNo1(objEncumBO.getRegTxn( dto.getRegNo()));
            }
        }
        return dto;
    }




    /**
     * @since                :  12-02-2008
     * Method                :  getCopyIssuanceRemarks
     * Description           :  This method is to get the details from the database.
     * @param certifiedid    :  String
     * @param flag           :  String
     * @return dto           :  CertifiedCopyDetailsDTO
     * @throws Exception
     */
    public CertifiedCopyDetailsDTO getCopyIssuanceRemarks(String certifiedid,String languageLocale) throws Exception {
        CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        String[] param = new String[1];
        param[0] = certifiedid;
        ArrayList ret = dao.getCopyIssuanceRemarks(param,languageLocale);
        if (ret!=null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList list = (ArrayList)ret.get(i);
                dto.setCertifiedId((String)list.get(0));  
                dto.setFirstName((String)list.get(1));
                dto.setMiddleName((String)list.get(2));
                dto.setLastName((String)list.get(3));
                //dto.setGender((String)list.get(4));
                String gender=(String)list.get(4);
                if("M".equalsIgnoreCase(gender)){gender="Male";}
                if("F".equalsIgnoreCase(gender)){gender="Female";}
                dto.setGender(gender);
                dto.setAge((String)list.get(5));
                dto.setFatherName((String)list.get(6));
                dto.setMotherName((String)list.get(7));
                dto.setAddress((String)list.get(8));
                dto.setCity((String)list.get(9));
                dto.setCountry((String)list.get(10));
                dto.setState((String)list.get(11));
                dto.setPin((String)list.get(12));
                dto.setPhone((String)list.get(13));
                dto.setMphone((String)list.get(14));
                dto.setEmail((String)list.get(15));
                dto.setIdProof((String)list.get(16));
                dto.setIdProofNo((String)list.get(17));
                dto.setFee((String)list.get(18));
                dto.setPostalFee((String)list.get(19));
                dto.setTotalFee((String)list.get(20));
                dto.setIssuanceRemark((String)list.get(21));
                dto.setRegNo((String)list.get(22));
                dto.setTypeReq((String)list.get(23));
                dto.setPurposeReq((String)list.get(24));
                dto.setCreatedDt((String)list.get(25));
                dto.setAppStatus((String)list.get(26));
                dto.setModifiedDt((String)list.get(27));
                dto.setPaymentTxnId((String)list.get(28));
                dto.setPaymentAmount((String)list.get(29));
                dto.setPaymentMode((String)list.get(30));  
                dto.setDocumentId((String)list.get(31));
                dto.setPostalTrakingNum((String)list.get(32));
                dto.setDisDate((String)list.get(33));                
                dto.setBankName((String)list.get(34));
                dto.setBankAddress((String)list.get(35));

            }
        }
        return dto;
    }




    /**
     * @since                 :  15-01-2008 
     * Method                 :  updateIssuance
     * Description            :  This method updates the remarks into the database.
     * @param dto             :  CertifiedCopyDetailsDTO
     * @param userId 
     * @param funId 
     * @param roleId 
     * @return issuanceupdate :  boolean
     * @throws                :  Exception
     */
    
    public String getcopyStatus(String regId)  throws Exception 
	{
		{
			CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
			ArrayList list = new ArrayList();
			ArrayList retList1 = new ArrayList();
			String lockstatus = "";
			try {
				
				//list = dao.getLockStatus(regId);
				
				String[] par1 = new String[1];
		        par1[0] = regId;
		        ArrayList ret2 = dao.getcopyStatus(par1);
		        list = dao.getcopyStatus(par1);
				retList1 = (ArrayList) list.get(0);
				lockstatus = ((String) retList1.get(0));
				log.info("PropertyLockingBD in lockstatus(): lockstatus="+ lockstatus);
			} catch (Exception e)
			{
				log.debug("PropertyLockingBD -- Exception in PropertyLockingBD - getFee():"+ e);
			}
			return lockstatus;
		}
	}
    
    /**
     * 
     * @param dto
     * @param roleId
     * @param funId
     * @param userId
     * @param status
     * @return
     * @throws Exception
     */
    
    public boolean updateIssuance(CertifiedCopyDetailsDTO dto, String roleId, String funId, String userId,String status) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        boolean issuanceupdate = false;
        String[] param = new String[3];
        //param[0] = dto.getIssuanceRemark();
        //param[1] = dto.getCertifiedId();
        
        param[0] = dto.getUpdtdRemarks();
        param[1] =dto.getUploaded_doc_path();
        param[2] =dto.getCertifiedId();
        String[] statusparam = new String[4];
        log.debug("STATUS IN BD="+dto.getAppStatus());

       
        statusparam[0] = "D";
        statusparam[1] = dto.getPostalTrakingNum();
        statusparam[2] = dto.getDisDate();
        statusparam[3] = dto.getCertifiedId();

        issuanceupdate = dao.updateIssuance(param,statusparam,roleId,funId,userId,dto);
        return issuanceupdate;
    }




    /**
     * @since                  :  15-02-2008
     * Method                  :  updateIssuanceStatus
     * Description             :  This method updates the remarks into the db.
     * @param dto              :  CertifiedCopyDetailsDTO
     * @param userId 
     * @param funId 
     * @param roleId 
     * @return issuanceupdate  :  boolean 
     * @throws Exception
     */
    public boolean updateIssuanceStatus(CertifiedCopyDetailsDTO dto, String roleId, String funId, String userId) throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        boolean issuanceupdate = false;
        String[] param = new String[2];
        param[0] = dto.getIssuanceRemark();
        param[1] = dto.getCertifiedId();

        String[] dparam = new String[1];
        dparam[0] = dto.getCertifiedId();

        issuanceupdate = dao.updateIssuanceStatus(param,dparam,roleId,funId,userId);
        return issuanceupdate;
    }




    /**
     * @since        :  11-02-2008
     * Method        :  getIssuanceStatus
     * Description   :  This method is used to get the copy issuance request status from the database.
     * @return list  :  ArrayList
     * @throws       :  Exception
     */
    public ArrayList getIssuanceStatus() throws Exception {
        CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();    
        ArrayList list = new ArrayList();
        try
        {
            ArrayList rst = dao.getIssuanceStatus();
            if (rst!=null)
            {
                for (int i=0;i<rst.size();i++)
                {
                    ArrayList returnList = (ArrayList) rst.get(i);
                    CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                    int j=i+1;
                    dto.setSerialNo(j);
                    dto.setCertifiedId((String)returnList.get(0));
                    dto.setTypeReq((String)returnList.get(18));
                    dto.setCreatedDt((String)returnList.get(24));
                    dto.setRegNo((String)returnList.get(17));
                    dto.setAppStatus((String)returnList.get(27));   
                    list.add(dto);  
                }
            }
        } catch (Exception x)
        {
            log.debug("Exception="+x);
        }
        return list;
    }




    /**
     * @since      :  12-01-2008
     * Method      :  certifiedIDgenerator
     * Description :  This method is to generate the primary key value automatically.
     * @return id  :  String
     */
    private String certifiedIDgenerator() {
      
    	String id = new CommonUtil().getAutoId("IGRS_CERCOPY_TXN_PARTY_DETAILS","CER_COPY_TXN_ID","CCI","");
        return id;
    }


    /**
     * 
     * @param funId
     * @param userType 
     * @return
     * @throws Exception
     */
	public String getFee(String funId, String userType)  throws Exception 
	{
		{
			 CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO(); 
			ArrayList list = new ArrayList();
			ArrayList retList1 = new ArrayList();
			String feeVal = "0";

			try {
				
				list = dao.getFee(funId,userType);
			if(list!=null&&list.size()>0)
			{	
				retList1 = (ArrayList) list.get(0);
				feeVal = ((String) retList1.get(1));
			}
				log.debug("CertifiedBD in getFee(): feeVal="+ feeVal);
			} catch (Exception e)
			{
				log.debug("CertifiedBD -- Exception in CertifiedBD - getFee():"+ e);
			}
			return feeVal;
		}
	}

/**
 * 
 * @param funId
 * @param serviceId
 * @param userType
 * @return
 * @throws Exception
 */
	public String getOthersFeeDuty(String funId, String serviceId,
			String userType) throws Exception  {
		
		log.debug("CertifiedBD -- getOthersFeeDuty() funId= "+ funId);
		CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
		ArrayList OthersDuty = null;
		String otherFee = null;
		try {
			OthersDuty = dao.getOthersFeeDuty( funId, serviceId, userType);
			otherFee = (String)OthersDuty.get(2);
			
		} catch (Exception e) {
			log.debug("CertifiedBD --- Exception in  getOthersFeeDuty()  " + e);
			e.printStackTrace();
		}

		return otherFee;
	}
	
	/**
	 * 
	 * @param res
	 * @param contId
	 * @param strFunctionName
	 * @return
	 * @throws Exception
	 */
	public String displayObjectDetails(HttpServletResponse res, String contId,String strFunctionName)  throws Exception {
		CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
		String status="";
		try {
			status = dao.displayObjectDetails(res, contId,strFunctionName);
		} catch (Exception e) {
			log.debug("CertifiedBD --- Exception in  getOthersFeeDuty()  " + e);
			e.printStackTrace();
		}
	
		
		return status;
	}
	
	/**
	 * 
	 * @param funId
	 * @return
	 * @throws Exception
	 */
    public CertifiedCopyDetailsDTO getFunctionName(String funId) throws Exception {
    	CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();

        String[] param = new String[1];
        param[0] = funId;
       
        ArrayList ret = dao.getFunctionName(param);
       
        if (ret!=null)
        {
            for (int i = 0; i < ret.size(); i++)
            {
                ArrayList list = (ArrayList)ret.get(0);
                String pFunName=(String)list.get(0);
                String pModName=(String)list.get(1);
                log.debug("pFunName = "+pFunName+"pModName = "+pModName);
                dto.setParentFunName((String)list.get(0));
                dto.setParentModName((String)list.get(1));
                
            }
        }

        
        return dto;
    }  
    
    public String updateICopyInfo(CertifiedCopyDetailsDTO dto,String pymntStatus) throws Exception {
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        String refId ="";
        String retRefId ="";
        log.debug("REFERENCE ID IS = "+refId);
        boolean retMsg = dao.updateICopyInfo(dto,pymntStatus);       
        if(retMsg){retRefId="succ";}
        else{retRefId="fail";}
        return retRefId;
    }
    
    /**
     * 
     * @param dto
     * @return
     * @throws Exception
     */
    public String updatePaymentInfo(CertifiedCopyDetailsDTO dto) throws Exception {
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        String refId ="";
        String retRefId ="";
        log.debug("REFERENCE ID IS = "+refId);
        boolean retMsg = dao.updatePaymentInfo(dto);       
        if(retMsg){retRefId="succ";}
        else{retRefId="fail";}
        return retRefId;
    }
    
    /**
     * 
     * @param regisId
     * @return
     * @throws Exception
     */
    public ArrayList getRegistrationSearch(String regisId) throws Exception{
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getRegistrationSearch(regisId);        
        //log.debug("List in BD....."+list.size());
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            log.debug("Inside If");
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setRegNo((String)lst.get(0));
                dto.setRegDate((String)lst.get(1)); 
                dto.setDeedType((String)lst.get(2));
                dto.setDeedId((String)lst.get(3));
                returnList.add(dto);                
            }
        }
        return returnList;      
    }
    
    
    /**
     * 
     * @param regNo
     * @param pin
     * @return
     * @throws Exception
     */
    public ArrayList getPinSearch(String regNo,String pin) throws Exception{
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getPinSearch(regNo,pin);        
        //log.debug("List in BD....."+list.size());
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            log.debug("Inside If");
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setPinNo((String)lst.get(0));
               // dto.setRegDate((String)lst.get(1));        
                returnList.add(dto);                
            }
        }
        return returnList;      
    }
    /**
     * 
     * @param regisId
     * @return
     * @throws Exception
     */
    public ArrayList getOldRegistrationSearch(String regisId) throws Exception{
    	CertifiedCopyDetailsDAO dao = new CertifiedCopyDetailsDAO();
        ArrayList list = dao.getOldRegistrationSearch(regisId);        
        //log.debug("List in BD....."+list.size());
        ArrayList returnList = new ArrayList();
        if (list!=null)
        {
            log.debug("Inside If");
            for (int i=0;i<list.size();i++)
            {
                ArrayList lst=(ArrayList)list.get(i);
                CertifiedCopyDetailsDTO dto = new CertifiedCopyDetailsDTO();
                dto.setOldRegNo((String)lst.get(0));
                dto.setOldRegDate((String)lst.get(1));        
                dto.setDeedType((String)lst.get(2));
                returnList.add(dto);                
            }
        }
        return returnList;      
    }
    public ArrayList getPendingApps(String userId) throws Exception
    {
    	
    	CertifiedCopyDetailsDAO dao= new CertifiedCopyDetailsDAO();
    	
    	ArrayList pendingAppListFinal = new ArrayList();
    	ArrayList list=dao.getPendingApps(userId);
    	
    	if(list!=null && list.size()>0){
    		
    		ArrayList rowList;
    		
            try{
    		for (int i = 0; i < list.size(); i++) {
    			
    			CertifiedCopyDetailsDTO objdto = new CertifiedCopyDetailsDTO();
    			
    			rowList = (ArrayList)list.get(i);
    			//objDashBoardDTO = new DashBoardDTO();
    		
    			//if(rowList.get(0).toString().length()==11){
    			
    			objdto.setCertifiedId(rowList.get(0).toString());
    				
    				//if (rowList.get(2)== null)
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(6));
    			//	else
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(3));
    				 
    			//}else{
    			//	objdto.setTypeReq(rowList.get(1).toString());
    			
    				//objDashBoardDTO.setPaidAmount(rowList.get(2));
    			//	System.out.println("*******"+(String)rowList.get(2));
    			if (rowList.get(1)==null || (rowList.get(1)).toString().equals(""))
    			{
    				//System.out.println("*******");
    				objdto.setPaidAmount("--");
    			}
    				
    			else
    			
    				objdto.setPaidAmount(rowList.get(1).toString());
    			
    			if (rowList.get(1)==null || rowList.get(2).toString().equalsIgnoreCase(""))
    			{
    				objdto.setBalanceAmount(rowList.get(5).toString());
    			}
    			else
    			{
    				objdto.setBalanceAmount(rowList.get(2).toString());
    			}
    			
    			//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
    			
    			if (rowList.get(1)==null || rowList.get(3).toString().equalsIgnoreCase(""))
    			{
    				objdto.setLastTransactionDate(rowList.get(4).toString());
    			}
    			else
    			{
    				objdto.setLastTransactionDate(rowList.get(3).toString());
    			}
    		//	System.out.println(rowList.get(4).toString());
    		//	System.out.println(rowList.get(3).toString());
    		//	System.out.println(objdto.getLastTransactionDate());
    			pendingAppListFinal.add(objdto);
    		
    		}
            }
            catch(Exception e){
            	e.printStackTrace();
            	
            }
    	}	
    		
    		ArrayList newlist=dao.getPendingAppsinIssuance(userId); 
    		ArrayList rowList1;
    		if(newlist!=null&& newlist.size()>0){
    		for (int i = 0; i < newlist.size(); i++) {
    			
    			CertifiedCopyDetailsDTO objdto = new CertifiedCopyDetailsDTO();
    			
    			rowList1 = (ArrayList)newlist.get(i);
    			//objDashBoardDTO = new DashBoardDTO();
    		
    			//if(rowList.get(0).toString().length()==11){
    			
    			objdto.setCertifiedId(rowList1.get(0).toString());
    				
    				//if (rowList.get(2)== null)
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(6));
    			//	else
    		//	objdto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(3));
    				 
    			//}else{
    				objdto.setTypeReq(rowList1.get(0).toString());
    			
    				//objDashBoardDTO.setPaidAmount(rowList.get(2));
    			
    				
    				objdto.setPaidAmount("--");
    				objdto.setBalanceAmount(rowList1.get(1).toString());
    			
    			
    			//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
    			
    				objdto.setLastTransactionDate(rowList1.get(2).toString());
    				pendingAppListFinal.add(objdto);
    		
    		}	
    		
    		}
   
    	return pendingAppListFinal;
    }
    
public CertifiedCopyDetailsDTO getCertifiedCopyDetails(String certifiedId,String language)  throws Exception
{
	
	CertifiedCopyDetailsDTO dto= new CertifiedCopyDetailsDTO();
	CertifiedCopyDetailsDAO dao= new CertifiedCopyDetailsDAO();
	ArrayList rowlist=dao.getCertifiedCopyDetails(certifiedId,language);
	ArrayList list = (ArrayList)rowlist.get(0);
	dto.setVolume(list.get(48)==null?"":list.get(48).toString());
	dto.setBookNo(list.get(49)==null?"":list.get(49).toString());
	dto.setNumberDate(list.get(51)==null?"":list.get(51).toString());
	dto.setCertifiedId(list.get(0)==null?"":list.get(0).toString());
	dto.setDocReg(list.get(50)==null?"":list.get(50).toString());
	dto.setRegNo(list.get(50)==null?"":list.get(50).toString());
	dto.setNum(list.get(50)==null?"":list.get(50).toString());
	dto.setTransPartyFirstName(list.get(2)==null?"":list.get(2).toString());
	dto.setTransPartyMidName(list.get(3)==null?"":list.get(3).toString());
	dto.setTransPartyLastName(list.get(4)==null?"":list.get(4).toString());
	dto.setTransPartySpouseName(list.get(40)==null?"":list.get(40).toString());
	dto.setTransPartyMotherName(list.get(22)==null?"":list.get(22).toString());
	dto.setTransPartyFGHName(list.get(21)==null?"":list.get(21).toString());
	dto.setFirstName(list.get(2)==null?"":list.get(2).toString());
	dto.setLastName(list.get(4)==null?"":list.get(4).toString());
	dto.setMiddleName(list.get(3)==null?"":list.get(3).toString());
	
	dto.setAge(list.get(6)==null?"":list.get(6).toString());
	dto.setFatherName(list.get(21)==null?"":list.get(21).toString());
	dto.setMotherName(list.get(22)==null?"":list.get(22).toString());
	dto.setAppSopuse(list.get(52)==null?"":list.get(52).toString());
	dto.setAddress(list.get(11)==null?"":list.get(11).toString());
	dto.setCountryName(list.get(8)==null?"":list.get(8).toString());
	dto.setShowState(list.get(9)==null?"":list.get(9).toString());
	dto.setDistrictName2(list.get(10)==null?"":list.get(10).toString());
	dto.setPin(list.get(12)==null?"":list.get(12).toString());
	dto.setPhone(list.get(13)==null?"":list.get(13).toString());
	dto.setMphone(list.get(14)==null?"":list.get(14).toString());
	dto.setEmail(list.get(15)==null?"":list.get(15).toString());
	dto.setIdProof(list.get(17)==null?"":list.get(17).toString());
	dto.setIdProofNo(list.get(16)==null?"":list.get(16).toString());
	double balance=isCompletePayment(certifiedId);
	dto.setBalanceAmount(String.valueOf(balance));
	dto.setTotalFee(list.get(30)==null?"":list.get(30).toString());
	dto.setIssuanceRemark(list.get(29)==null?"":list.get(29).toString());
	if("en".equalsIgnoreCase(language))
	{	
	dto.setTypeReq(list.get(54)==null?"":list.get(54).toString());
	dto.setGender(list.get(5)==null?"":list.get(5).toString());

	}
	else
	{
		if(list.get(54)!=null&&!list.get(54).toString().equalsIgnoreCase("") )
		{
			dto.setTypeReq("प्रमाणित प्रतिलिपि");
		}
		if(list.get(5)!=null&&!list.get(5).toString().equalsIgnoreCase("male") )
		{
			dto.setGender("पुरुष");
		}
		if(list.get(5)!=null&&!list.get(5).toString().equalsIgnoreCase("female") )
		{
			dto.setGender("महिला");
		}


	}
	dto.setPurposeReq(list.get(53)==null?"":list.get(53).toString());
	dto.setDocumetId1(list.get(55)==null?"":list.get(55).toString());
	double paid=Double.parseDouble(list.get(30)==null?"":list.get(30).toString())-balance;
	String paidAmount=String.valueOf(paid);
	dto.setPaidAmount(paidAmount);
	dto.setSelectedItems(searchRegId(dto.getRegNo(),language));
	return dto;
	
}

public String setPaymentDetails(CertifiedCopyDetailsDTO dto,String funId,String userId)
{
	String paymentId="";
	try {
		CertifiedCopyDetailsDAO  dao = new CertifiedCopyDetailsDAO();
		ArrayList list=dao.isPaymentEntry(dto.getCertifiedId());
		if(false)
		//if(list.size()>0)
		{	
		ArrayList rowlist=(ArrayList) list.get(0);
		if(rowlist.get(0).toString().equalsIgnoreCase("I"))
			{
				return rowlist.get(1).toString();
			}
			else
			{
				return dao.setPaymentDetails(dto, funId, userId);
			}
		}
		else
		{
		return dao.setPaymentDetails(dto, funId, userId);
		}
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return paymentId;
}

public double isCompletePayment(String certifiedId)
{
	String balance="";
	double bal=0;
	try {
		CertifiedCopyDetailsDAO  dao = new CertifiedCopyDetailsDAO();
		balance=dao.isCompletePayment(certifiedId);
		if(balance.equalsIgnoreCase(""))
		{
			String total=dao.TotalPayment(certifiedId);
			return Double.parseDouble(total);
		}
		else
		{	
		bal=Double.parseDouble(balance);
		return bal;
		}
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bal;
}
public void modifyDetails(CertifiedCopyDetailsDTO dto)
{
	CertifiedCopyDetailsDAO dao;
	try {
		dao = new CertifiedCopyDetailsDAO();
		dao.modify(dto);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public ArrayList searchRegId(String regNo,String language) throws Exception {
//	logger.debug("IN BD searchRegId");
	ArrayList dataSet, row;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.searchRegIdDao(regNo);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				cdto.setRegNo1(row.get(0).toString());
				cdto.setPropertyTxnId(row.get(1).toString());
				//cdto.setPropertyTypeId(row.get(2).toString());
				if("en".equalsIgnoreCase(language))
				{	
				cdto.setPropertyTypeLabel(row.get(3).toString());
				}
				else
				{
				cdto.setPropertyTypeLabel(row.get(4).toString());
				}
				retList.add(cdto);
			}
			dataSet.clear();
		}
	} catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}
public ArrayList transactingPartyDetails(String propertyId)
{
	ArrayList dataSet, row;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.transactingPartyDetails(propertyId);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) 
			{
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
			if(row.get(0).toString().equalsIgnoreCase("organization"))
				{
					cdto.setAppellate((String)row.get(0));
					cdto.setOrganisationName((String)row.get(5));
					cdto.setAuthorizedPerson((String)row.get(6));
					cdto.setTransPartyFirstName("NA");
					cdto.setTransPartyMotherName("NA");
					cdto.setTransPartyFGHName("NA");
					cdto.setTransPartySpouseName("NA");
					retList.add(cdto);
				}
			else
				{
				cdto.setAppellate((String)row.get(0));
				cdto.setTransPartyFirstName((String)row.get(1));
				cdto.setTransPartyMotherName((String)row.get(2));
				cdto.setTransPartyFGHName((String)row.get(3));
				cdto.setTransPartySpouseName((String)row.get(4));
				cdto.setOrganisationName("NA");
				cdto.setAuthorizedPerson("NA");
				retList.add(cdto);
				}
		}
			dataSet.clear();
		}
	} catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}

public String getCountryName(String countryId,String langauge)
{
	try {
		return new CertifiedCopyDetailsDAO().getCountryName(countryId,langauge);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public String getStateName(String countryId,String langauge)
{
	try {
		return new CertifiedCopyDetailsDAO().getStateName(countryId,langauge);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
public String getDistrictName(String countryId,String langauge)
{
	try {
		return new CertifiedCopyDetailsDAO().getDistrictName(countryId,langauge);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public ArrayList srDashboardCertified(String officeid,CertifiedCopyDetailsDTO dto,String languageLocale) 
{
	ArrayList dataSet=null,noEncumData=null,row=null;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.srDashboard(officeid,dto);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				if(row.get(0)!=null&&!row.get(0).toString().equalsIgnoreCase(""))
				{
					cdto.setRegNo(row.get(0).toString());
				}
				else
				{
					cdto.setRegNo("NA");
				}
					cdto.setCertifiedId(row.get(1).toString());
				
				
				if("en".equalsIgnoreCase(languageLocale))
					cdto.setTypeReq(row.get(2).toString());
				else
					cdto.setTypeReq("प्रमाणित प्रति ");	
				 cdto.setCreatedDt(row.get(3).toString());
				retList.add(cdto);
			}
			dataSet.clear();
		}
		
		  } catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}

public ArrayList srDashboardNoEcumbrance(String officeid,CertifiedCopyDetailsDTO dto,String languageLocale) 
{
	ArrayList dataSet=null,noEncumData=null,row=null;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.srDashboardNoEncum(officeid,dto);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				if(row.get(0)!=null&&!row.get(0).toString().equalsIgnoreCase(""))
				{
					cdto.setRegNo(row.get(0).toString());
				}
				else
				{
					cdto.setRegNo("NA");
				}
					cdto.setCertifiedId(row.get(1).toString());
				
					if("en".equalsIgnoreCase(languageLocale))
						cdto.setTypeReq(row.get(2).toString());
					else
						cdto.setTypeReq("भारहीनता प्रमाणपत्र ");	
				cdto.setCreatedDt(row.get(3).toString());
				retList.add(cdto);
			}
			dataSet.clear();
		}
		
			noEncumData=dao.pendingSrDashboard(officeid);
			if (noEncumData != null && noEncumData.size() > 0) {
				CertifiedCopyDetailsDTO cdto;
				for (int iLoop = 0; iLoop < noEncumData.size(); iLoop++) {
				row = (ArrayList) noEncumData.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				cdto.setRegNo("NA");
				cdto.setCertifiedId(row.get(0).toString());
				cdto.setTypeReq("No-Ecumbrance");
				cdto.setCreatedDt(row.get(1).toString());
				retList.add(cdto);
				}
			noEncumData.clear();
			}
		
		  } catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}
public ArrayList srDashboardCertifiedUpdate(String officeid,CertifiedCopyDetailsDTO dto) 
{
	ArrayList dataSet=null,noEncumData=null,row=null;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.srDashboardUpdate(officeid,dto);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				if(row.get(0)!=null&&!row.get(0).toString().equalsIgnoreCase(""))
				{
					cdto.setRegNo(row.get(0).toString());
				}
				else
				{
					cdto.setRegNo("NA");
				}
					cdto.setCertifiedId(row.get(1).toString());
				
				cdto.setTypeReq(row.get(2).toString());
				cdto.setCreatedDt(row.get(3).toString());
				retList.add(cdto);
			}
			dataSet.clear();
		}
		
		  } catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}

public ArrayList srDashboardNoEcumbranceUpdate(String officeid,CertifiedCopyDetailsDTO dto) 
{
	ArrayList dataSet=null,noEncumData=null,row=null;
	ArrayList retList = new ArrayList();
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		CaveatsBO bo = new CaveatsBO();
		dataSet = dao.srDashboardNoEncumUpdate(officeid,dto);
		if (dataSet != null && dataSet.size() > 0) {
			CertifiedCopyDetailsDTO cdto;
			for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
				row = (ArrayList) dataSet.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				if(row.get(0)!=null&&!row.get(0).toString().equalsIgnoreCase(""))
				{
					cdto.setRegNo(row.get(0).toString());
				}
				else
				{
					cdto.setRegNo("NA");
				}
					cdto.setCertifiedId(row.get(1).toString());
				
				
				cdto.setTypeReq(row.get(2).toString());
				cdto.setCreatedDt(row.get(3).toString());
				retList.add(cdto);
			}
			dataSet.clear();
		}
		
			noEncumData=dao.pendingSrDashboardUpdate(officeid);
			if (noEncumData != null && noEncumData.size() > 0) {
				CertifiedCopyDetailsDTO cdto;
				for (int iLoop = 0; iLoop < noEncumData.size(); iLoop++) {
				row = (ArrayList) noEncumData.get(iLoop);
				cdto = new CertifiedCopyDetailsDTO();
				cdto.setRegNo("NA");
				cdto.setCertifiedId(row.get(0).toString());
				cdto.setTypeReq("No-Ecumbrance");
				cdto.setCreatedDt(row.get(1).toString());
				retList.add(cdto);
				}
			noEncumData.clear();
			}
		
		  } catch (Exception e) {
			e.printStackTrace();
		//	logger.error(e);
	}
	return retList;
}

public ArrayList getPaymentParameter(String regno)
{
	
		try {
			CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
			return dao.getPaymentParameter(regno);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
}

public boolean UpdateDownloadStatus(CertifiedCopyDetailsDTO dto)
{
	
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		return dao.UpdateDownloadStatus(dto);
	
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	return false;
}


public String getUserType(String userId) {
	try {
		CertifiedCopyDetailsDAO dao=new CertifiedCopyDetailsDAO();
		return dao.getUserType(userId);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null; 
}
}
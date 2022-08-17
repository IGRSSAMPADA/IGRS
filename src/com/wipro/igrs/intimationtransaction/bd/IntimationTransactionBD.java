/**
 * ===========================================================================
 * File           :   IntimationTransactionBD.java
 * Description    :   Represents the Business Class

 * Author         :   Nithya
 * Created Date   :   Jan 8, 2008
 * Updated By	  :   Imran Shaik
 * Updated Data	  :   Oct 21,2008
 * ===========================================================================
 */


package com.wipro.igrs.intimationtransaction.bd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.intimationtransaction.dao.IntimationTransactionDAO;
import com.wipro.igrs.intimationtransaction.dto.IntimationTxnDetailsDTO;
import com.wipro.igrs.intimationtransaction.dto.PaymentDTO;
import com.wipro.igrs.intimationtransaction.sql.CommonSQL;
import com.wipro.igrs.intimationtransaction.util.CommonUtil;

public class IntimationTransactionBD {

    Date currentDate = new Date();
	private static Logger log = org.apache.log4j.Logger.getLogger(IntimationTransactionBD.class);

    //default constructor
    public IntimationTransactionBD() {
    }

    /**
   * @since       :  08-01-2008
   * Method       :  getIntimationType
   * Description  :  This method sets the Intimation Transaction Mode
   * @return list :  ArrayList
   * @throws      :  Exception
   */
    public ArrayList getIntimationType(String funId, String userId) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList smsFee=dao.getSmsFee(funId, userId);
        ArrayList emailFee=dao.getEmailFee(funId, userId);
        ArrayList postFee=dao.getPostFee(funId, userId);
        
        String feeSms = (String) smsFee.get(0);
        String emailSms = (String) emailFee.get(0);
        String postSms = (String) postFee.get(0);
        
        ArrayList ret = dao.getIntimationType();
        ArrayList list = new ArrayList();

        if (ret != null) {
            for (int i = 0; i < ret.size(); i++) {
                ArrayList lst = (ArrayList)ret.get(i);
                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
                dto.setIntimationTypeId((String)lst.get(0));
                	if(((String)lst.get(0)).equalsIgnoreCase("SMS"))
                         dto.setIntimationType(feeSms);
                	if(((String)lst.get(0)).equalsIgnoreCase("EMAIL"))
                        dto.setIntimationType(emailSms);
                	if(((String)lst.get(0)).equalsIgnoreCase("POST"))
                        dto.setIntimationType(postSms);
                	
                list.add(dto);
            }
        }
        return list;
    }

    
    //To get country Name
    public String getCountryName(String countryId,String language)
    {
    	try {
    		return new IntimationTransactionDAO().getCountryName(countryId,language);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    public String getStateName(String countryId,String language)
    {
    	try {
    		return new IntimationTransactionDAO().getStateName(countryId,language);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }
    
    
    public String getDistrictName(String countryId,String language)
    {
    	try {
    		return new IntimationTransactionDAO().getDistrictName(countryId,language);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }

   
    public String getPhotoIdName(String photoId,String language)
    {
    	try {
    		return new IntimationTransactionDAO().getPhotoIdName(photoId,language);
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	return null;
    }

    
    /**
    * @since       :  08-01-2008
    * Method       :  getCountry
    * Description  :  This method sets the country list
    * @return list :  ArrayList
    * @throws      :  Exception
    */
    public ArrayList getCountry(String language) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList ret = dao.getCountry();
        ArrayList list = new ArrayList();

        if (ret != null) {
            for (int i = 0; i < ret.size(); i++) {
                ArrayList lst = (ArrayList)ret.get(i);
                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
                //dto.setCountryId((String)typeTemp.get(0));
            	//dto.setCountryName((String)typeTemp.get(1));
                dto.setCountryId((String)lst.get(0));
                if("en".equalsIgnoreCase(language))
                {	
                dto.setCountryName((String)lst.get(1));
                }
                else
                {
                dto.setCountryName((String)lst.get(2));	
                }
                list.add(dto);
                
               
            }
        }
        return list;
    }

    /**
    * @since           :  08-01-2008
    * Method           :  getState
    * Description      :  This method sets the state list
    * @param  stateId  :  String
    * @return list     :  ArrayList
    * @throws          :  Exception
    */
    public ArrayList getState(String stateId,String language) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList ret = dao.getState(stateId);
        ArrayList list = new ArrayList();

        if (ret != null) {
            for (int i = 0; i < ret.size(); i++) {
                ArrayList lst = (ArrayList)ret.get(i);
                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
                if("en".equalsIgnoreCase(language))
                {
                	dto.setStateName((String)lst.get(1));
                }
                else
                {
                	dto.setStateName((String)lst.get(2));
                }
                dto.setStateId((String)lst.get(0));
                
                list.add(dto);
               // log.debug("**********************statelist="+list);
            }
        }
        return list;
    }

    /**
     * @since            :  08-02-2008 
     * Method            :  getCity
     * Description       :  This method sets the district list
     * @param  stateId   :  String
     * @return list      :  ArrayList
     * @throws           :  Exception
     */
    public ArrayList getCity(String stateId,String langauge) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList ret = dao.getCity(stateId);
        ArrayList list = new ArrayList();

        if (ret != null) {
            for (int i = 0; i < ret.size(); i++) {
                ArrayList lst = (ArrayList)ret.get(i);
                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
                dto.setCityId((String)lst.get(0));
                if("en".equalsIgnoreCase(langauge))
                {	
                dto.setCityName((String)lst.get(1));
                }
                else
                {
                dto.setCityName((String)lst.get(2));	
                }
                list.add(dto);
            }
        }
        return list;
    }

    
    public ArrayList getofficeDetails(String officeId){
    	 IntimationTransactionDAO dao;
		  ArrayList alist = new ArrayList();
		try {
			dao = new IntimationTransactionDAO();
			
		  alist=dao.getofficeDetails(officeId);
		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
				  return alist;
	  }
    
    
    public ArrayList getuserDetails(String userId){
   	 IntimationTransactionDAO dao;
		  ArrayList alist = new ArrayList();
		try {
			dao = new IntimationTransactionDAO();
			
		  alist=dao.getuserDetails(userId);
		  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
				  return alist;
	  }
    
    
    /**
    * @since       :  10-02-2008
    * Method       :  getIDProof
    * Description  :  This method sets the ID proof list
    * @return list :  ArrayList
    * @throws      :  Exception
    */
    public ArrayList getIDProof(String language) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList ret = dao.getIDProof();
        ArrayList list = new ArrayList();

        if (ret != null) {
            for (int i = 0; i < ret.size(); i++) {
                ArrayList lst = (ArrayList)ret.get(i);
                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
                dto.setTxnId((String)lst.get(0));
                if("en".equalsIgnoreCase(language))
                {	
                dto.setPhotoIdType((String)lst.get(1));
                }
                else
                {
                dto.setPhotoIdType((String)lst.get(2));	
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
      * @param fnId :  String
      * @return dto :  IntimationTxnDetailsDTO
      * @throws     :  Exception
      */
    /*public IntimationTxnDetailsDTO getFee(String fnId) throws Exception  {
    	double total = 0;
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        log.debug("FunctionId="+fnId);
        ArrayList list = dao.getFee(fnId);
        IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
        dto.setFee((String)list.get(0));
        log.debug("list.get(0)="+list.get(0));
        dto.setOtherFee((String)list.get(1));
        log.debug("list.get(1)="+list.get(1));
        //total = Double.parseDouble(list.get(0)==null?"0":(String)list.get(0)) + Integer.parseInt((String)list.get(1)==null?"0":(String)list.get(1));
        total = Double.parseDouble(dto.getFee()) + Double.parseDouble(dto.getOtherFee());
        log.debug("Total="+total);
        dto.setTotalFee("" + total);
        return dto;
    }*/
    
  public String updateIntimDetails(IntimationTxnDetailsDTO dto,String txnid)  throws Exception {
	  IntimationTransactionDAO dao = new IntimationTransactionDAO();
	  boolean submitIntimationDetails = false;
	  submitIntimationDetails= dao.updateIntimDetails(dto,txnid);
	  return txnid;
  }
     
    
    public String submitIntimationDetails(IntimationTxnDetailsDTO dto,String funId,String userId,FormFile photo, FormFile signature, FormFile thumb ) throws Exception {
    	 IntimationTransactionDAO dao = new IntimationTransactionDAO();
         boolean submitIntimationDetails = false;
         String[] submitParam = new String[36];
         if(dto.getTxnId()!=null &&!dto.getTxnId().equalsIgnoreCase(""))
         {
        	 submitParam[0]=dto.getTxnId();
        }
         else
         {
         submitParam[0] = txnIDgenerator();
         }
         String txnId = submitParam[0];
         submitParam[1] = funId;//party type='Applicant'
         submitParam[2] = dto.getFirstName();
         submitParam[3] = dto.getMiddleName();
         submitParam[4] = dto.getLastName();
         submitParam[5] = dto.getGender();
         submitParam[6] = dto.getAge();
         submitParam[7] = "" ;//nationality
         submitParam[8] = dto.getCountry();
         submitParam[9] = dto.getState();
         submitParam[10] = dto.getCity();
         submitParam[11] = dto.getAddress();
         submitParam[12] = dto.getPostalCode();
         submitParam[13] = dto.getPhone();
         submitParam[14] = dto.getMobile();
         submitParam[15] = dto.getEmail();
         submitParam[16] = dto.getPhotoIdType();
         submitParam[17] = dto.getPhotoIdNo();
         submitParam[18] = dto.getFatherName();
         submitParam[19] = dto.getMotherName();
         submitParam[20] = ""; //party thump impression
         submitParam[21] = ""; //party photo
         submitParam[22] = ""; //party sign
         submitParam[23] = ""; //update by
         submitParam[24] = dto.getUserId(); //created by
         submitParam[25] = dto.getVolumeNo();
         submitParam[26] = dto.getBookNo();
         submitParam[27] = "";
     //    log.debug("*****regno in bd *******"+dto.getDocRegNo());
         
         /*if("".equalsIgnoreCase(dto.getDocId()))
  		{submitParam[27]=dto.getNumber();}
		     if("N".equalsIgnoreCase(dto.getDocId()))
		     {submitParam[27]= dto.getDocRegNo();}
*/         
         
         submitParam[28] = getDateFromString("");
         submitParam[29] = getDateFromString("");
         submitParam[30] = "";
         submitParam[31] = "";
         submitParam[32] = dto.getTotalFee();
         submitParam[33] = dto.getOtherFee();
         submitParam[34] = dto.getFee();
         submitParam[35] = "A";
         
    	
         submitIntimationDetails = dao.submitIntimationDetails(submitParam,dto,txnId);
         
       
        return txnId;
    }
    
    
    public double isCompletePayment(IntimationTxnDetailsDTO dto)

    {
            String trancId=dto.getTxnId();
            String balance="";

            double bal=0;

            try {

            	IntimationTransactionDAO dao = new IntimationTransactionDAO();

                    balance=dao.isCompletePayment(trancId,CommonSQL.PENDING_BALANCE_DETAILS);
                //    log.debug("********balance in bd"+balance);
                    if(balance.equalsIgnoreCase(""))

                    {

                            String total=dao.TotalPayment(trancId);

                            return Double.parseDouble(total);

                    }

                    else

                    {       

                    bal=Double.parseDouble(balance);
         //           log.debug("********bal in bd******"+bal);
                    return bal;

                    }

                    } catch (Exception e) {

                                 e.printStackTrace();

            }

            return bal;

    }



    
    
    
    
    public String submitPaymentDetails1(IntimationTxnDetailsDTO dto,String funId,String userId) throws Exception{
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
		 boolean submitPaymentDetails = false;
		 String trancId = dto.getTxnId();

		 ArrayList list= dao.isPaymentEntry(trancId,CommonSQL.PRESENT_PAYMENT_ENTRY);
		 
		 if(list.size()>0)

         {       

         ArrayList rowlist=(ArrayList) list.get(0);

         if(rowlist.get(0).toString().equalsIgnoreCase("I"))

                 {

                         return rowlist.get(1).toString();

                 }

                 else

                 {

                	  String[] submitParam = new String[6];
          	        // submitParam[0] = txnIDgenerator();
          	            submitParam[0] = dao.getIntimationPaymentId();
          	            submitParam[1] = trancId;
          	            submitParam[2] = funId;
          	            submitParam[3] = dto.getTotalFee();
          	            submitParam[4] = "I";
          	            submitParam[5] = userId;
          	            
          	          submitPaymentDetails = dao.submitPaymentDetails(submitParam,CommonSQL.INTIMATION_TXN_PAYMENT_SUBMIT);
          	          
          	          return submitParam[0];
                   }

         }

         else

         {

        	  String[] submitParam = new String[6];
  	        // submitParam[0] = txnIDgenerator();
  	            submitParam[0] = dao.getIntimationPaymentId();
  	            submitParam[1] = trancId;
  	            submitParam[2] = funId;
  	            submitParam[3] = dto.getTotalFee();
  	            submitParam[4] = "I";
  	            submitParam[5] = userId;
  	            
  	            submitPaymentDetails = dao.submitPaymentDetails(submitParam,CommonSQL.INTIMATION_TXN_PAYMENT_SUBMIT);
  	            
  	             return submitParam[0];
          }

         }
		   

    

    public String submitPaymentDetails(IntimationTxnDetailsDTO dto,String funId,String userId) throws Exception{
            IntimationTransactionDAO dao = new IntimationTransactionDAO();
			 boolean submitPaymentDetails = false;
			 String trancId = dto.getTransactionID();

			 ArrayList list= dao.isPaymentEntry(trancId,CommonSQL.PRESENT_PAYMENT_ENTRY);
			 
			 if(list.size()>0)

             {       

             ArrayList rowlist=(ArrayList) list.get(0);

             if(rowlist.get(0).toString().equalsIgnoreCase("I"))

                     {

                             return rowlist.get(1).toString();

                     }

                     else

                     {

                    	  String[] submitParam = new String[6];
              	        // submitParam[0] = txnIDgenerator();
              	            submitParam[0] = dao.getIntimationPaymentId();
              	            submitParam[1] = trancId;
              	            submitParam[2] = funId;
              //	          log.debug("******payable amt1 in bd****"+isCompletePayment(dto));
              	            submitParam[3] = Double.toString(isCompletePayment(dto));
              	            submitParam[4] = "I";
              	            submitParam[5] = userId;
              	            
              	          submitPaymentDetails = dao.submitPaymentDetails(submitParam,CommonSQL.INTIMATION_TXN_PAYMENT_SUBMIT);
              	          
              	          return submitParam[0];
                       }

             }

             else

             {

            	  String[] submitParam = new String[6];
      	        // submitParam[0] = txnIDgenerator();
      	            submitParam[0] = dao.getIntimationPaymentId();
      	            submitParam[1] = trancId;
      	            submitParam[2] = funId;
      	  //          log.debug("******payable amt in bd****"+isCompletePayment(dto));
      	            submitParam[3] = Double.toString(isCompletePayment(dto));
      	            submitParam[4] = "I";
      	            submitParam[5] = userId;
      	            
      	            submitPaymentDetails = dao.submitPaymentDetails(submitParam,CommonSQL.INTIMATION_TXN_PAYMENT_SUBMIT);
      	            
      	             return submitParam[0];
              }

             }
			   
           	

    
    
    
    public ArrayList getPendingApps(IntimationTxnDetailsDTO dto,String userId) throws Exception {
    	
    	IntimationTransactionDAO dao = new IntimationTransactionDAO();
    	ArrayList pendingAppListFinal = new ArrayList();
    	ArrayList list=dao.getPendingApps(userId,CommonSQL.INTIMATION_TXN_PAYMENT_SUBMIT1);
    //	log.debug(list);
    	if(list!=null && list.size()>0){
    		
    		ArrayList rowList;
    		
            try{
    		for (int i = 0; i < list.size(); i++) {
    			
                      rowList = (ArrayList)list.get(i);
    			//objDashBoardDTO = new DashBoardDTO();
                      IntimationTxnDetailsDTO dto1=new IntimationTxnDetailsDTO();
    			//if(rowList.get(0).toString().length()==11){
    			
    			dto1.setTxnId(rowList.get(0).toString());
    				
    			/*	if (rowList.get(2)== null)
    					dto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(6));
    				else
    					dto.setHdntransactionID(rowList.get(0)+"~"+rowList.get(3));
    				 
    			//}else{
    				dto.setDocName(rowList.get(1).toString());   */
    			
    				//objDashBoardDTO.setPaidAmount(rowList.get(2));
    			//	System.out.println("*******"+(String)rowList.get(2));
    			if ((rowList.get(1))==null)
    			{
    			//	System.out.println("*******");
    			       dto1.setPaidAmount("-");
    			}
    				
    			else
    				   dto1.setPaidAmount(rowList.get(1).toString());
    			
    			if (rowList.get(2)== null)
    			{
    				dto1.setBalanceAmount("--");
    			}
    			else
    				dto1.setBalanceAmount(rowList.get(2).toString());
    			
    			//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
    			
    			if ((rowList.get(3))==null||"".equalsIgnoreCase((rowList.get(3).toString())))
    			{
    				
    				dto1.setLastTransactionDate(rowList.get(4).toString());
    			}
    				else
    				{
    				dto1.setLastTransactionDate(rowList.get(3).toString());
    				}
    			pendingAppListFinal.add(dto1);
    	
    		}
    			
    		
    			
    		}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
    	}
    	return pendingAppListFinal;
    }
    
    
    
    public ArrayList getDetailsOfApplicant(String tranxId,String langauge) throws Exception
    {
    	
    	IntimationTransactionDAO dao = new IntimationTransactionDAO();    
    	ArrayList detilsOfApplicant = new ArrayList();
    	ArrayList list=dao.getDetailsOfApplicant(tranxId,CommonSQL.INTIMATION_REQ_DETAILS_GET_QUERY1);
    	IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
    	
    	if(list!=null && list.size()>0){
    		
    		ArrayList rowList;
    		
            try{
    		for (int i = 0; i < list.size(); i++) {
    			
    			
    			
    			rowList = (ArrayList)list.get(i);
    			dto.setTxnId((String)rowList.get(0));  
                dto.setFirstName((String)rowList.get(1));
                dto.setMiddleName((String)rowList.get(2));
                dto.setLastName((String)rowList.get(3));
                dto.setGender((String)rowList.get(4));
                dto.setAge((String)rowList.get(5));
                dto.setCountryName(getCountryName((String)rowList.get(6),langauge));
                dto.setStateName(getStateName((String)rowList.get(7),langauge));
                dto.setCityName(getDistrictName(((String)rowList.get(8)),langauge));
                dto.setCountry((String)rowList.get(6));
                dto.setState((String)rowList.get(7));
                dto.setCity((String)rowList.get(8));
                dto.setAddress((String)rowList.get(9));
                dto.setPostalCode((String)rowList.get(10));
                dto.setPhone((String)rowList.get(11));
                dto.setMobile((String)rowList.get(12));
                dto.setEmail((String)rowList.get(13));
                dto.setPhotoIdTypeName(getPhotoIdName((String)rowList.get(14),langauge));
                dto.setPhotoIdNo((String)rowList.get(15));
                dto.setFatherName((String)rowList.get(16));
                dto.setMotherName((String)rowList.get(17));
                dto.setDocId("-");
                dto.setBankName("-");
                dto.setBankAddr("-");
               // dto.setDocRegNo((String)rowList.get(18));
                dto.setFromDate((String)rowList.get(18));
                dto.setToDate((String)rowList.get(19));
               /* if(dto.getIntimationCheckbox1()=="Y")
                {dto.setSmsValue((String)rowList.get(21));
                 dto.sete}*/
                              /* if((String)rowList.get(20)=="SMS")
                {dto.setSmsValue((String)rowList.get(21));}
                if((String)rowList.get(20)=="EMAIL")
                {dto.setEmailValue((String)rowList.get(21));}
                if((String)rowList.get(20)=="POST")
                {dto.setPostValue((String)rowList.get(21));}*/
                ArrayList typelist=dao.getIntimationTypeRequest(tranxId,CommonSQL.INTIMATION_REQ_DETAILS_GET_QUERY2);
                if(typelist!=null&&typelist.size()>0)
                {
                	for(int k=0;k<typelist.size();k++)
                	{
                		ArrayList rowlist=(ArrayList) typelist.get(k);
                		if(rowlist.get(0)!=null&&rowlist.get(0).toString().equalsIgnoreCase("EMAIL"))
                		{
                			dto.setEmailValue(rowlist.get(1).toString());
                			
                		}
                		else if(rowlist.get(0)!=null&&rowlist.get(0).toString().equalsIgnoreCase("SMS"))
                		{
                			dto.setSmsValue(rowlist.get(1).toString());
                		}
                		else if(rowlist.get(0)!=null&&rowlist.get(0).toString().equalsIgnoreCase("POST"))
                		{
                			dto.setPostValue(rowlist.get(1).toString());
                		}
                	}
                		
                }
                if(dto.getEmailValue()==null || dto.getEmailValue().equalsIgnoreCase(""))
                {
                	dto.setEmailValue("NA");
                }
                if(dto.getSmsValue()==null || dto.getSmsValue().equalsIgnoreCase(""))
                {
                	dto.setSmsValue("NA");
                }
                if(dto.getPostValue()==null || dto.getPostValue().equalsIgnoreCase(""))
                {
                	dto.setPostValue("NA");
                }
                
               // dto.setIntimationTypeId((String)rowList.get(20));
               // dto.setIntimationType((String)rowList.get(21));
             //   dto.setTotalFee((String)rowList.get(23));
             //   dto.setOtherFee((String)rowList.get(24));
                dto.setFee((String)rowList.get(20));
                dto.setBalance(isCompletePayment(dto));
                double value=dto.getBalance();
             //   log.debug("******blnce*******"+value);
                double blnc=isCompletePayment(dto);
                double total=Double.parseDouble(dao.TotalPayment(dto.getTxnId()));
                double paidAmount=total-blnc;
                dto.setPaidAmnt(paidAmount);
                dto.setFilePhoto("-");
                dto.setFileSignature("-");
                dto.setFileThumb("-");
                
                detilsOfApplicant.add(dto);
                
    		} 	
    		
    		}
            catch(Exception e){
            	e.printStackTrace();
            	
            }
    	}
    	return detilsOfApplicant;
    }
    
    
    
    public String updatePaymentInfo(IntimationTxnDetailsDTO dto) throws Exception {

    	IntimationTransactionDAO dao = new IntimationTransactionDAO();

        String refId ="";

        String retRefId ="";

     //   log.debug("REFERENCE ID IS = "+refId);

        boolean retMsg = dao.updatePaymentInfo(dto);       

        if(retMsg){retRefId="succ";}

        else{retRefId="fail";}

        return retRefId;

    }
    
    
      

    public boolean updateIntimationDetails(IntimationTxnDetailsDTO dto) throws Exception{
    	boolean updateDetails=false;
    	IntimationTransactionDAO dao = new IntimationTransactionDAO();
    	String tranxID=dto.getTxnId();
    	updateDetails=dao.updateIntimationDetails(tranxID);
    	
    	
    	return updateDetails;
    }

    	
    
    /**
     * @since          :  10-02-2008
     * Method          :  submitIntimationInfo1
     * Description     :  This method inserts personal details into DB
     * @param dto      :  IntimationTxnDetailsDTO
     * @param payDTO   :  PaymentDTO
     * @param formFile3 
     * @param formFile2 
     * @param formFile 
     * @return txnId   :  String
     * @throws         :  Exception
     */
    public String submitIntimationInfo1(IntimationTxnDetailsDTO dto,PaymentDTO payDTO,String funId, FormFile photo, FormFile signature, FormFile thumb ) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        boolean submitInfo1 = false;//,submitInfo2 = false,submitInfo3 = false,submitInfo4 = false;
        String[] submitParam = new String[29];
        submitParam[0] = txnIDgenerator();
        String txnId = submitParam[0];
        submitParam[1] = "02";//party type='Applicant'
        submitParam[2] = dto.getFirstName();
        submitParam[3] = dto.getMiddleName();;
        submitParam[4] = dto.getLastName();
        submitParam[5] = dto.getLastName();;
        submitParam[6] = dto.getAge();
        submitParam[7] = "" ;//nationality
        submitParam[8] = dto.getCountry();
        submitParam[9] = dto.getState();
        submitParam[10] = dto.getCity();
        submitParam[11] = dto.getAddress();
        submitParam[12] = dto.getPostalCode();
        submitParam[13] = dto.getPhone();
        submitParam[14] = dto.getMobile();
        submitParam[15] = dto.getEmail();
        submitParam[16] = dto.getPhotoIdType();
        submitParam[17] = dto.getPhotoIdNo();
        submitParam[18] = dto.getBankName();//Bank name
        submitParam[19] = dto.getBankAddr();//Bank addr
        submitParam[20] = "";//Org name
        submitParam[21] = dto.getFatherName();
        submitParam[22] = dto.getMotherName();
        submitParam[23] = ""; //category id
        submitParam[24] = ""; //caste id
        submitParam[25] = ""; //religion id
        submitParam[26] = ""; //party thump impression
        submitParam[27] = ""; //party photo
        submitParam[28] = ""; //party sign
        
      //insert intimation details
      //  submitInfo1 = submitIntimationInfo2(dto,txnId,payDTO,funId);
        
        //insert party details
        if(submitInfo1)
        submitInfo1 = dao.submitIntimationInfo(submitParam,CommonSQL.INTIMATION_TXN_INFO_SUBMIT1);
        
     //   log.debug("after inserting values  submitInfo1--->"+submitInfo1);
        
        // insert thumb,photo,signature details
        /*if(submitInfo1)
            submitInfo1 = dao.updateIntimationInf(photo,signature,thumb,txnId);*/
        //insert mapping details
        if(submitInfo1)
            submitInfo1 = submitIntimationInfo3(dto,txnId);        
        //insert req mapping details
        if(submitInfo1)
        submitInfo1 = submitIntimationInfo4(dto,txnId);
        
        return txnId;
    }

    /**
     * @since                :  10-02-2008
     * Method                :  submitIntimationInfo2
     * Description           :  This method inserts  details into IGRS_INTIMATION_TXN_DETAILS
     * @param dto            :  IntimationTxnDetailsDTO
     * @param payDTO         :  PaymentDTO
     * @param txnId          :  String
     * @param thumb 
     * @param signature 
     * @param photo 
     * @return submitInfo    :  boolean
     * @throws               :  Exception
     */
    public boolean submitIntimationInfo2(IntimationTxnDetailsDTO dto,String txnId,PaymentDTO payDTO,String funId) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        boolean submitInfo = false;
        String[] intiReqType = dto.getIntimationReqType();
     //   log.debug("*********by********intiReqType="+intiReqType[0]);
       
        String[] submitParam = new String[12];
        submitParam[0] = txnId;
        submitParam[1] = dto.getDocRegNo();
        submitParam[2] = "";//req type fn id						   
        submitParam[3] = funId;//function id
        submitParam[4] = dto.getUserId();//created by
        submitParam[5] = "";//update by
        submitParam[6] = "";//update date	
        submitParam[7] = "Y";//inti status	
        submitParam[8] = "02-Aug-2013";//Registration Date
        submitParam[9] = getDateFromString(dto.getFromDate()); //intimn start date
        submitParam[10] = getDateFromString(dto.getToDate()); //intimn end date
        submitParam[11] = "123456"; 
        submitParam[11] = payDTO.getPaymentTxnId(); //paymt txn id
        submitInfo = dao.submitIntimationInfo(submitParam,CommonSQL.INTIMATION_TXN_INFO_SUBMIT2);
        return submitInfo;
    }

    /**
     * @since             : 10-02-2008
     * Method             : submitIntimationInfo3
     * Description        : This method inserts  details into IGRS_INTIMATION_TXN_MAPPING
     * @param dto         : IntimationTxnDetailsDTO
     * @param txnId       : String
     * @return submitInfo : boolean
     * @throws            : Exception
     */
    public boolean submitIntimationInfo3(IntimationTxnDetailsDTO dto,String txnId ) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        boolean submitInfo = false;
        String[] intiType= dto.getIntimationTypes();
        String[] intiVal = dto.getIntimationValues();
        for (int i=0;i<intiType.length;i++) {
            String[] submitParam = new String[3];   
            submitParam[0] = txnId;
            submitParam[1] = intiType[i];
            submitParam[2] = intiVal[i];
            submitInfo = dao.submitIntimationInfo(submitParam,CommonSQL.INTIMATION_TXN_INFO_SUBMIT3);
        }
        return submitInfo;  
    }

    /**
     * @since             : 10-02-2008
     * Method             : submitIntimationInfo4
     * Description        : This method inserts  details into IGRS_INTIMATION_REQ_TYPE_MAP
     * @param dto         : IntimationTxnDetailsDTO
     * @param txnId       : String
     * @return submitInfo : boolean
     * @throws            : Exception
     */

    public boolean submitIntimationInfo4(IntimationTxnDetailsDTO dto,String txnId ) throws Exception {
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        boolean submitInfo = false;
        String[] intiReqType = dto.getIntimationReqType();
        String intiReqType1[] = intiReqType[0].split(","); 
        for (int i=0;i<intiReqType1.length;i++) {
            String[] submitParam = new String[6];
            submitParam[0] = txnId;
            submitParam[1] = intiReqType1[i];
            submitParam[2] = "Y";
            submitParam[3] = dto.getUserId();//created by
            //submitParam[3] = "";//created date
            submitParam[4] = "";//update by
            submitParam[5] = "";//update date
            submitInfo = dao.submitIntimationInfo(submitParam,CommonSQL.INTIMATION_TXN_INFO_SUBMIT4);
        }
        return submitInfo;  
    }

    /**
    * @since             : 18-02-2008 
    * Method             : validatePayment
    * Description        : This method is used to validate the payment details from the db
    * @param challanNo	 : String
    * @param challanDt   : String
    * @param challanAmt  : String
    * @param bnkName     : String 
    * @return returnList : ArrayList
    * @throws Exception
    */
   /* public ArrayList validatePayment(String challanNo, String challanDt,
                                     String challanAmt, String bnkName) throws Exception {

        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList list = dao.validatePayment(challanNo, challanDt, challanAmt, bnkName);
        ArrayList returnList = new ArrayList();

        if (list!=null) {
            for (int i=0;i<list.size();i++) {
                ArrayList lst=(ArrayList)list.get(i);
                PaymentDTO dto = new PaymentDTO();
                dto.setPaymentTxnId((String)lst.get(0));
                dto.setChallanNo((String)lst.get(1));
                dto.setChallanDate((String)lst.get(2));
                dto.setAmount((String)lst.get(3));
                dto.setBankName((String)lst.get(4));

                returnList.add(dto);
            }
        }
        return returnList;
    }*/

    /**
     * @since             : 25-02-2008
     * Method             : getStringFromDate
     * Description        : To typecast the Date into String
     * @param date        : Date
     * @return dateString : String
     */
    private String getStringFromDate(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy"); 
        String dateString = null;
        try {
            if ( date!=null)
                dateString = dateFormat.format(date);
        } catch (Exception e) {
            log.debug("Error getting date1 field of Newattendanceoutput1 in 'dd-MMM-yy' format: " + e ); 
        }
        return dateString;
    }

    /**
     * @since                          : 25-02-2008
     * Method                          : getDateFromString
     * Description                     : To Typeecast the String into custom Date Format
     * @param dateString               : String
     * @return getStringFromDate(date) : Method
     */    
    private String getDateFromString(String dateString){
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException pe) {
            log.debug("ParseException: " + pe);
        }
        return getStringFromDate(date);
    }

    /**
     * @since      :  12-01-2008
     * Method      :  txnIDgenerator
     * Description :  This method is to generate the primary key value automatically.
     * @return id  :  String
     */
    private String txnIDgenerator() {
        String id =new CommonUtil().getAutoId("IGRS_INTIMN_TXN_INFO","INTIMATION_TXN_ID", "MPTIR");
        return id;
    }

    /**
   * @since             : 20-01-2008
   * Method             : getIntimationSearch
   * Description        : This method is used to search the Intimation Req Form Details details according to the given 
   * input data and set the output parameters.
   * @param refId       : String
   * @param status      : String
   * @param fromDate    : String
   * @param toDate      : String
   * @param  docRegNo   : String
   * @return returnList : ArrayList
   * @throws            : Exception
   */
    public ArrayList getIntimationSearch(IntimationTxnDetailsDTO dto,String txnId,String langauge) throws Exception {

        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        ArrayList list = dao.getIntimationSearch(txnId);
        ArrayList returnList = null;
        if (list!=null) {
        	 returnList = new ArrayList();
        	for (int i=0;i<list.size();i++) {
                ArrayList lst=(ArrayList)list.get(i);
                 dto = new IntimationTxnDetailsDTO();
                dto.setSNo("1.");
                dto.setIntId((String)lst.get(0));
                dto.setDateOfRequest((String)lst.get(1));
                if("en".equalsIgnoreCase(langauge))
                {	
                dto.setIntimationStatus("Active");
                }
                else
                {
                	dto.setIntimationStatus("सक्रिय");	
                }

                returnList.add(dto);
            }
        }
        return returnList;
    }

    /**
        * @since             :  15-01-2008
        * Method             :  getIntimationDetails
        * Description        :  This method is to get the Intimation Form details for a particular id. 
        * @param refId       :  String
        * @return dto        :  IntimationTxnDetailsDTO
        * @throws            :  Exception
        */
    public IntimationTxnDetailsDTO getIntimationDetails(String refId) throws Exception {
        IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
        IntimationTransactionDAO dao = new IntimationTransactionDAO();
        String[] param = new String[2];
        param[0] = refId;
        param[1] = refId;
        String fnId = null;
        ArrayList ret1 = dao.getIntimationDetails(param,CommonSQL.INTIMATION_DETAILS_GET_QUERY1);
        if (ret1!=null) {
            for (int i = 0; i < ret1.size(); i++) {
                ArrayList list = (ArrayList)ret1.get(i);
                dto.setTxnId((String)list.get(0));  
                dto.setFirstName((String)list.get(1));
                dto.setMiddleName((String)list.get(2));
                dto.setLastName((String)list.get(3));
                dto.setGender((String)list.get(4));
                dto.setAge((String)list.get(5));
                dto.setFatherName((String)list.get(6));
                dto.setMotherName((String)list.get(7));
                dto.setAddress((String)list.get(8));
                dto.setCity((String)list.get(9));
                dto.setCountry((String)list.get(10));
                dto.setState((String)list.get(11));
                dto.setPostalCode((String)list.get(12));
                dto.setPhone((String)list.get(13));
                dto.setMobile((String)list.get(14));
                dto.setEmail((String)list.get(15));
                dto.setPhotoIdType((String)list.get(16));
                dto.setPhotoIdNo((String)list.get(17));
                dto.setBankName((String)list.get(18));
                dto.setBankAddr((String)list.get(19));
                dto.setDocId("New");
                dto.setFromDate((String)list.get(20));
                dto.setToDate((String)list.get(21));
                dto.setDocRegNo((String)list.get(22));
                fnId = (String)list.get(23);
                dto.setStatus((String)list.get(24));
                dto.setCreatedDate((String)list.get(25));
            }
        }
        String[] param1 = new String[1];
        param1[0] = refId;
        ArrayList ret2 = dao.getIntimationDetails(param1,CommonSQL.INTIMATION_DETAILS_GET_QUERY2);
        String[] intiReqType = new String[ret2.size()];
        if (ret2!=null) {
            for (int i = 0; i < ret2.size(); i++) {
                ArrayList list = (ArrayList)ret2.get(i);
                intiReqType[i] = (String)list.get(0);
            }   
            dto.setIntimationReqType(intiReqType);
        }
        ArrayList ret3 = dao.getIntimationDetails(param1,CommonSQL.INTIMATION_DETAILS_GET_QUERY3);
        String[] intiType = new String[ret3.size()];
        String[] intiVal = new String[ret3.size()];
        if (ret3!=null) {
            for (int i = 0; i < ret3.size(); i++) {
                ArrayList list = (ArrayList)ret3.get(i);
                intiType[i] = (String)list.get(0);
                intiVal[i] = (String)list.get(1);           

            }   
            dto.setIntimationTypes(intiType);
            dto.setIntimationValues(intiVal);
        }
        String dtoFee = getFee(fnId);
        dto.setFee(dtoFee);
        /*dto.setOtherFee(dtoFee.getOtherFee());
        dto.setTotalFee(dtoFee.getTotalFee());*/
        return dto;
    }

    
    
    
    
    public String getFee(String funId)  throws Exception 
	{
		{
			IntimationTransactionDAO dao = new IntimationTransactionDAO(); 
			ArrayList list = new ArrayList();
			ArrayList retList1 = new ArrayList();
			String feeVal = "";
			try {
				list = dao.getFee(funId);
				retList1 = (ArrayList) list.get(0);
				feeVal = ((String) retList1.get(0));
			} catch (Exception e)
			{
				log.debug("IntimationTxnBD -- Exception in IntimationTxnBD - getFee():"+ e);
			}
			return feeVal;
		}
	}


	public String getOthersFeeDuty(String funId, String serviceId,
			String userType) throws Exception  {
		
		IntimationTransactionDAO dao = new IntimationTransactionDAO();
		ArrayList OthersDuty = null;
		String otherFee = null;
		try {
			OthersDuty = dao.getOthersFeeDuty( funId, serviceId, userType);
			otherFee = (String)OthersDuty.get(2);
			
		} catch (Exception e) {
			log.debug(" IntimationTxnBD --- Exception in  getOthersFeeDuty()  " + e);
			e.printStackTrace();
		}

		return otherFee;
	}
	
	/**
	 * @author Imran Shaik
	 * @return ArryList
	 * @throws Exception
	 * it returns list of intimation type
	 */
	public ArrayList getTypeOfIntimation(String langauge) throws Exception {
	    IntimationTransactionDAO dao = new IntimationTransactionDAO();
	        ArrayList ret = dao.getTypeOfIntimation();
	        ArrayList list = new ArrayList();

	        if (ret != null) {
	            for (int i = 0; i < ret.size(); i++) {
	                ArrayList lst = (ArrayList)ret.get(i);
	                IntimationTxnDetailsDTO dto = new IntimationTxnDetailsDTO();
	                dto.setIntimationTypeId((String)lst.get(0));
	                if("en".equalsIgnoreCase(langauge))
	                {
	                dto.setIntimationType((String)lst.get(1));
	                }
	                else
	                {
	                dto.setIntimationType((String)lst.get(2));	
	                }
	                list.add(dto);
	            }
	        }
	        return list;
	}
	
}


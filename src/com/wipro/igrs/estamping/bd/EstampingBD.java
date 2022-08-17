package com.wipro.igrs.estamping.bd;

/**
 * ===========================================================================
 * File           :   EstampingBD.java
 * Description    :   Represents the Business Delegate Class

 * Author         :   pavani Param
 * Created Date   :   Dec 05, 2007

 * ===========================================================================
 */
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.dao.EstampDAO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.estamping.form.EstampFormBean;

/**
 * @author pavpapa
 */
public class EstampingBD {
	boolean boo = false;
	boolean blnUpLoadIns = false;
	String estampId;
	String estampCode;
	boolean blnDeactIns;
	EstampBO objBo;
	ArrayList list = null;
	int str1;
	EstampDAO objEstampDAO = new EstampDAO();
	private Logger logger = (Logger) Logger.getLogger(EstampingBD.class);

	public EstampingBD() throws Exception {
		objBo = new EstampBO();
	}
	//EstampDAO dao;
	IGRSCommon objCommon;

	/* public ArrayList getCountry() throws Exception {
	    
	     ArrayList ret  =  objBo.getCountry();
	     logger.info("in estamping bd getCountry() ret "+ret);
	     list  =  new ArrayList();

	     if (ret !=  null) {
	         for (int i  =  0; i < ret.size(); i++) {
	             ArrayList lst  =  (ArrayList)ret.get(i);
	             EstampDetailsDTO dto = new EstampDetailsDTO();
	             dto.setValue((String)lst.get(0));
	             dto.setName((String)lst.get(1));
	             list.add(dto);
	         }

	     }
	     return list;
	 }*/
	////////////////////////// commented by Lavi on 04th October 2013. //////////////////////////////////
	/* public ArrayList getSROFirst(String id) throws Exception {
	     
	     ArrayList ret  =  objBo.getSROFirst(id);
	     logger.info("in estamping bd getSROFirst() ret "+ret);
	     list  =  new ArrayList();

	     if (ret !=  null) {
	         for (int i  =  0; i < ret.size(); i++) {
	             ArrayList lst  =  (ArrayList)ret.get(i);
	             //EstampDetailsDTO dto = new EstampDetailsDTO();
	             DashBoardDTO dto1 = new DashBoardDTO();
	             dto1.setTempId((String)lst.get(0));
	             //dto.setName((String)lst.get(1));
	             list.add(dto1);
	         }

	     }
	     return list;
	 }*/
	/* public String getFee(String strPurpose)throws Exception {
	 	String str = objBo.getFee(strPurpose);
	 	logger.info("in estamping bd getFee() str"+str);
	 
	 	//int str1 = 0;
	 	
	 	
	 		EstampDetailsDTO dto = new EstampDetailsDTO();
	   		logger.info(" above dto @@@@@@@@@@@ ");
	 		//dto.setStampFee((String) str.get(0));
	 		logger.info("in estamping bd getFee()......."+str);
	 		//str1=dto.getStampFee();
	 		
	
	 	return str;
	 }*/
	/*public ArrayList getOptionalDeedType() throws Exception {
	 logger.info("in estamp BD getOptionalDeedType");
	       ArrayList ret  =  objBo.getOptionalDeedType();
	       logger.info("in estamping bd getOptionalDeedType() ret "+ret);
	        list  =  new ArrayList();

	       if (ret != null) {
	           for (int i  =  0; i < ret.size(); i++) {
	               ArrayList lst  =  (ArrayList)ret.get(i);
	               EstampDetailsDTO dto  =  new EstampDetailsDTO();
	               dto.setValue((String)lst.get(0));
	               dto.setName((String)lst.get(1));
	               list.add(dto);
	               logger.info("in estamp BD getOptionalDeedType"+list);
	           }

	       }
	       return list;
	       
	   }*/
	/*public ArrayList getInstrumentDet(String DeedTypeID) throws Exception {
	 logger.info("in estamp BD getInstrumentDet");
	    ArrayList ret  =  objBo.getInstrumentDet(DeedTypeID);
	    logger.info("in estamping bd getInstrumentDet() ret "+ret);
	     list  =  new ArrayList();

	    if (ret != null) {
	        for (int i  =  0; i < ret.size(); i++) {
	            ArrayList lst  =  (ArrayList)ret.get(i);
	            EstampDetailsDTO dto  =  new EstampDetailsDTO();
	            dto.setValue((String)lst.get(0));
	            dto.setName((String)lst.get(1));
	            list.add(dto);
	        }
	    }
	    return list;
	}*/
	//////////////////////////end of commented code by Lavi on 04th October 2013. //////////////////////////////////
	public ArrayList getExempList(String insID, String deedId) throws Exception {
		logger.info("in estamp BD getExemList");
		IGRSCommon common = new IGRSCommon();
		list = new ArrayList();
		String deedType[] = new String[3];
		String[] deedAry = deedId != null ? deedId.split("~") : null;
		deedType[0] = deedAry[0];
		deedType[1] = insID;
		deedType[2] = "A";
		ArrayList ret = common.getExemptions(deedType);
		//ArrayList ret  =  objBo.getExempList(insID,deedTypeID);      
		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setExemptionId((String) lst.get(0));
				dto.setExemptionName((String) lst.get(1));
				list.add(dto);
				logger.info("in estamp BD getExemList() =  = " + list);
			}
		}
		return list;
	}

	//////////////////////////commented by Lavi on 04th October 2013. //////////////////////////////////
	/*public ArrayList getphotoIdList() throws Exception {
	        ArrayList ret  =  objBo.getphotoIdList();
	        logger.info("in estamping bd getphotoIdList() ret "+ret);
	         list  =  new ArrayList();

	        if (ret !=  null) {
	            for (int i = 0; i < ret.size(); i++) {
	                ArrayList lst = (ArrayList)ret.get(i);
	                EstampDetailsDTO dto = new EstampDetailsDTO();
	                dto.setValue((String)lst.get(0));
	                dto.setName((String)lst.get(1));
	                list.add(dto);
	            }

	        }
	        return list;
	    }*/
	/*public ArrayList getPurpose() throws Exception {
	    
	    ArrayList ret  =  objBo.getPurpose();
	    logger.info("in estamping bd getPurpose() ret "+ret);
	    list  =  new ArrayList();

	    if (ret !=  null) {
	        for (int i  =  0; i < ret.size(); i++) {
	            ArrayList lst  =  (ArrayList)ret.get(i);
	            EstampDetailsDTO dto = new EstampDetailsDTO();
	            dto.setValue((String)lst.get(0));
	            dto.setName((String)lst.get(1));
	            list.add(dto);
	            System.out.println("<---------->"+lst.get(0).toString());
	        }

	    }
	    return list;
	}*/
	/**
	 * @param form
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return boolean
	 * @throws Exception
	 */
	/*public ArrayList getPartyTypeList() throws Exception {
		

		ArrayList ret  = objBo.getPartyTypeList(); 
	   
	       list  =  new ArrayList();

	      if (ret !=  null) {
	          for (int i  =  0; i < ret.size(); i++) {
	              ArrayList lst  =  (ArrayList)ret.get(i);
	              EstampDetailsDTO dto  =  new EstampDetailsDTO();
	              dto.setValue((String)lst.get(0));
	              dto.setName((String)lst.get(1));
	              dto.setState((String)lst.get(1));
	              list.add(dto);
	          }

	      }
	  
	      logger.info("in estamping bd getPartyTypeList()............... "+list);
	      return list;
	  }*/
	/**
	 * Inserting E-Stmp De-activation Details.
	 * 
	 * @param form
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return boolean value.
	 * @throws Exception
	 */
	/*public boolean subDeactEstampInfo(EstampFormBean form, String roleId, String funId, String userId) throws  Exception
	{
		
	 logger.info(" enter in to    BD  subDeactEstampInfo()");
	 
	 
	 String dactpartyDet[] = new String[20];
	 String dactAppDet[] = new String[4];
	 String deactRefId="";
	 
	 EstampDetailsDTO objEstampDet = form.getObjEstampDet();
	 

	 logger.info(" enter in to    BD  subDeactEstampInfo() objEstampDet.getCountry()=="+objEstampDet.getCountry());
	 
	 
	 dactAppDet[0] = form.getDeactTxnId();//deactRefId;//Deactivation Reference Id is inserted 
	 dactAppDet[1] = form.getApplNo();
	 dactAppDet[2] = form.getEstampCode();
	 //dactAppDet[3] = form.getDeacRemarks();
	 dactAppDet[4] = form.getGender();
	 dactAppDet[5] = form.getAge();
	 dactAppDet[6] = form.getFatherName();
	 dactAppDet[7] = form.getMotherName();
	 
	   
		      logger.info("EstampBD-- before insert Deactive E-Stamp Details ");
		      blnDeactIns = objBo.deactEstampPartyDetIns(dactAppDet,roleId,funId,userId);
			  logger.info("EstampBD-- after insertDeactive E-Stamp  Details  blnDeactIns=="+blnDeactIns);
			  
	 dactpartyDet[0] = form.getDeactTxnId();//deactRefId;//Deactivation Reference Id is inserted 
	 dactpartyDet[1] = form.getFirstName();
	 dactpartyDet[2] = form.getMiddleName();
	 dactpartyDet[3] = form.getLastName();
	 dactpartyDet[4] = form.getGender();
	 dactpartyDet[5] = form.getAge();
	 dactpartyDet[6] = form.getFatherName();
	 dactpartyDet[7] = form.getMotherName();
	 dactpartyDet[8] = form.getAddress();
	 dactpartyDet[9] = objEstampDet.getCountry();
	 dactpartyDet[10] = objEstampDet.getState();
	 dactpartyDet[11] = objEstampDet.getDistrict();
	 dactpartyDet[12] = form.getPostalCode();
	 dactpartyDet[13] = form.getPhoneNumber();
	 dactpartyDet[14] = form.getMobileNumber();
	 dactpartyDet[15] = form.getEmailID();
	 dactpartyDet[16] = objEstampDet.getPhotoId();
	 dactpartyDet[17] = form.getIdNo();
	 dactpartyDet[18] = form.getBankName();
	 dactpartyDet[19] = form.getBankAddr();
	   
		      logger.info("EstampBD-- before insert Deactive E-Stamp  party Details ");
		      blnDeactIns = objBo.deactEstampPartyDetIns(dactpartyDet,roleId,funId,userId);
			  logger.info("EstampBD-- after insertDeactive E-Stamp  party Details  blnDeactIns=="+blnDeactIns);
			  
			 
			  
			  return blnDeactIns;
	//---- END--ESTAMP DETAILS --	
	}
	public String getPartyTypeId(String party,String partyType) throws Exception {
		logger.info("in estamping bd getPartyTypeId() partyTypeId");
	 	
	   ArrayList ret  = objBo.getPartyTypeId(party,partyType);
	   logger.info("in estamping bd getPartyTypeId() ret "+ret);
	     ArrayList list = (ArrayList) ret.get(0);
	      String partyTypeId = (String)list.get(0);
	      logger.info("in estamping bd getPartyTypeId() partyTypeId ="+partyTypeId);
	return partyTypeId;
	}*/
	/**
	 * getting Deactive Txn Id
	 * 
	 * @param party
	 * @param partyType
	 * @return
	 * @throws Exception
	 */
	/*public String getDeactTxnId() throws Exception {
		logger.info("in estamping bd getDeactTxnId()DeactTxnId");
	 	
	   ArrayList ret  = objBo.getDeactTxnId();
	   logger.info("in estamping bd getDeactTxnId() ret "+ret);
	     ArrayList list = (ArrayList) ret.get(0);
	      String deactTxnId = (String)list.get(0);
	      logger.info("in estamping bd getDeactTxnId() getDeactTxnId ="+deactTxnId);
	return deactTxnId;
	}*/
	/**
	 * getting Estamp Txn Id
	 * 
	 * @param party
	 * @param partyType
	 * @return
	 * @throws Exception
	 */
	/*public String getEstampTxnId() throws Exception {
		logger.info("in estamping bd getEstampTxnId()DeactTxnId");
	 	
	   ArrayList ret  = objBo.getEstampTxnId();
	   logger.info("in estamping bd getEstampTxnId() ret "+ret);
	     ArrayList list = (ArrayList) ret.get(0);
	      String estampTxnId = (String)list.get(0);
	      logger.info("in estamping bd getEstampTxnId()  ="+estampTxnId);
	return estampTxnId;
	}*/
	/*public String getNonJudStampDuty(String _refDeedTypeId, String _refInstrId,String  _refExemId,String _secAmt )
	{
		logger.info("in estamping BD getNonJudStampDuty() partyTypeId");
		String StampDuty = "";	
		try {
			StampDuty = objBo.getNonJudStampDuty(_refDeedTypeId, _refInstrId, _refExemId,_secAmt);
		} catch (Exception e) {
			logger.error("in estamping bd getJudStampDuty "+e);
			e.printStackTrace();
		}
	   
		return StampDuty;
		
	}*/
	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @return othersFeeDuty
	 */
	/*public  ArrayList getOthersDuty(String _retFunId,String _serId,String _userType) throws Exception
	{
		logger.info("in estamping BD getOthersDuty() partyTypeId");
		ArrayList OthersDuty = null;	
		try {
			OthersDuty = objBo.getOthersDuty(_retFunId,_serId, _userType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("in estamping bd getOthersDuty "+e);
			e.printStackTrace();
		}
	   
		return OthersDuty;
	  }     */
	//////////////////////////end of commented code by Lavi on 04th October 2013. //////////////////////////////////
	public ArrayList setValues(ArrayList list2, String[] selectedIds) {
		EstampDetailsDTO dto1 = null;
		for (int i = 0; i < list2.size(); i++) {
			dto1 = (EstampDetailsDTO) list2.get(i);
			for (int j = 0; j < selectedIds.length; j++) {
				if (dto1.getValue().equalsIgnoreCase(selectedIds[j])) {
					dto1.setChecked("checked");
				}
			}
		}
		return list2;
	}

	/**
	 * getting SRO list values.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	//////////////////////////commented by Lavi on 04th October 2013. //////////////////////////////////
	/*public ArrayList getSroList(String distId)throws Exception
	{
		
		ArrayList ret = objBo.getSroList(distId);
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
			logger.info("EstampingBD----getSroList  " + list);
		}
		return list;
	}*/
	/*public ArrayList getTrNameList() throws Exception
	{
		ArrayList ret = objBo.getTrNameList();
		list = new ArrayList();

		if (ret != null) {
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
			logger.info("EstampingBD----getSroList  " + list);
		}
		return list;
	}*/
	/*public EstampFormBean getMStampViewDet(EstampFormBean form)
	{
		
		list = new ArrayList();
		 ArrayList retestampList  = new ArrayList();
		 ArrayList retList1 = new ArrayList();
		EstampDetailsDTO objDTo = form.getObjEstampDet();
		String mStampCode = objDTo.getMstampCode();
		logger.info("Wipro in EstampBD - getMStampViewDet() before EXCUTIN QUERY");
		
		 try
		 {
		 list = objBo.getMStampViewDet(objDTo.getStampCodeFrom(),objDTo.getStampCodeTo());
		 retList1  =  (ArrayList)list.get(0);
		 if(((String)retList1.get(0)).equals("J"))
		 form.setTypeOfStamp("Judicial");
		 else
		 form.setTypeOfStamp("Non Judicial");
		 form.setVenderName((String)retList1.get(1));
		 form.setVenderId((String)retList1.get(2));
		 form.setDateOfIssue((String)retList1.get(3));
		 form.setMdistrict((String)retList1.get(4));
		 form.setSroName((String)retList1.get(5));
		 form.setTreasuryName((String)retList1.get(6));
		 if(((String)retList1.get(10)).equalsIgnoreCase("1"))
			 objDTo.setMstampCode((String)retList1.get(7));
		 else
			 objDTo.setMstampCode((String)retList1.get(7)+" TO  "+(String)retList1.get(9));
		 objDTo.setDenomination((String)retList1.get(8));
		 }
		  catch(Exception e)
		  {
			 logger.info("this is Exception in getMStampViewDet() in BD" + e);
		     }

		  return form;
	}*/
	/**
	 * for getting All Deed Details
	 * 
	 * @return ArrayList
	 */
	//------------------------------------------------Start------test commented by Aakriti------------------------------------
	/*public ArrayList getDeedTypeList() throws Exception 
	{
		ArrayList list = objBo.getDeedTypeList();
		ArrayList listDeed = new ArrayList();
		if(list!=null) {
			for(int i = 0;i<list.size();i++) {
				ArrayList listReturn = (ArrayList) list.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setValue(listReturn.get(0).toString()+"~"
							+listReturn.get(1).toString()+"~"
							+listReturn.get(2).toString()+"~"
							+listReturn.get(3).toString()+"~"
							+listReturn.get(4).toString());
				dto.setName(listReturn.get(1).toString());
				dto.setDeedValReq(listReturn.get(2).toString());
				dto.setDutyCalReq(listReturn.get(3).toString());
				dto.setPropRelDeed(listReturn.get(4).toString());
				listDeed.add(dto);
			}
		}
		
		
		return listDeed;
	}
	*/
	//------------------------------------------------End------test commented by Aakriti------------------------------------
	//------------------------------------------------Start------Modifications by Aakriti------------------------------------
	// ------------------------ commented by Lavi ------------------------------------------------------
	/*public ArrayList getDeedTypeList() throws Exception 
	{
		ArrayList list = objBo.getDeedTypeList();
		ArrayList listDeed = new ArrayList();
		if(list!=null) {
			for(int i = 0;i<list.size();i++) {
				ArrayList listReturn = (ArrayList) list.get(i);
				EstampDetailsDTO dto = new EstampDetailsDTO();
				dto.setValue(listReturn.get(0).toString());
							//+listReturn.get(1).toString()------------test by Aakriti
							//+listReturn.get(2).toString()------------test by Aakriti
							//+listReturn.get(3).toString()------------test by Aakriti
							//+listReturn.get(4).toString());------------test by Aakriti
				dto.setName(listReturn.get(1).toString());
				dto.setDeedValReq(listReturn.get(2).toString());
				dto.setDutyCalReq(listReturn.get(3).toString());
				dto.setPropRelDeed(listReturn.get(4).toString());
				listDeed.add(dto);
			}
		}
		
		
		return listDeed;
	}*/
	//------------------------------------------------End------Modifications by Aakriti------------------------------------
	//------------------------ end of commented code by Lavi ------------------------------------------------------
	/**
	 * getting deed details for given Deed Id
	 * 
	 * @param deedId
	 * @return ApplicantForm
	 */
	/*public EstampDetailsDTO getDeedDetails(String deedId) throws Exception {
		return objBo.getDeedDetails(deedId);
	}*/
	//////////////////////////end of commented code by Lavi on 04th October 2013. //////////////////////////////////
	/**
	 * getPendingEstampApps for getting pending applications of estamps from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author LAVI
	 */
	public ArrayList getPendingEstampApps(String userId, String language) {
		return objEstampDAO.getPendingEstampApps(userId, language);
	}

	public ArrayList getDetailsOfDuties(String TxnId, String language) {
		return objEstampDAO.getDetailsOfDuties(TxnId, language);
	}

	public ArrayList getRegExemptions(String TxnId, String language) {
		return objEstampDAO.getRegExemptions(TxnId, language);
	}

	public ArrayList getStampExemptions(String TxnId, String language) {
		return objEstampDAO.getStampExemeptions(TxnId, language);
	}

	public ArrayList getUserEneterableList(String TxnId, String language) {
		return objEstampDAO.getUserEneterableList(TxnId, language);
	}

	public ArrayList getParaDetls(String txnId, String language) {
		return objEstampDAO.getParaDetls(txnId, language);
	}

	public ArrayList getDetailsOfApplicant(String TxnId, String language) {
		return objEstampDAO.getDetailsOfApplicant(TxnId, language);
	}

	public ArrayList getDetailsOfDocument(String TxnId) {
		return objEstampDAO.getDetailsOfDocument(TxnId);
	}

	public ArrayList checkEcode(String ecode) {
		return objEstampDAO.checkEcode(ecode);
	}

	public ArrayList viewEcodeDetails(String ecode) {
		return objEstampDAO.viewEcodeDetails(ecode);
	}

	public ArrayList viewEcodeType(String ecodeTypeID) {
		return objEstampDAO.viewEcodeType(ecodeTypeID);
	}

	public ArrayList viewEcodeDetailsDRS(String ecode) {
		return objEstampDAO.viewEcodeDetailsDRS(ecode);
	}

	public ArrayList getDetailsOfApplicantDRS(String TxnId, String language) {
		return objEstampDAO.getDetailsOfApplicantDRS(TxnId, language);
	}

	public ArrayList deactEcodeDetails(String ecode) {
		return objEstampDAO.deactEcodeDetails(ecode);
	}

	public ArrayList deactEcodeDetailsZero(String ecode) {
		return objEstampDAO.deactEcodeDetailsZero(ecode);
	}

	public ArrayList deactEcodeDetailsJudZero(String ecode) {
		return objEstampDAO.deactEcodeDetailsJudZero(ecode);
	}

	public ArrayList deactDocTypeJud(String ecode) {
		return objEstampDAO.deactDocTypeJud(ecode);
	}

	public ArrayList estampAmount(String ecode) {
		return objEstampDAO.estampAmount(ecode);
	}

	public ArrayList deactEcodeDate() {
		return objEstampDAO.deactEcodeDate();
	}

	public ArrayList deactEcodeAppDetails(String ecode) {
		return objEstampDAO.deactEcodeAppDetails(ecode);
	}

	public ArrayList deactEcodePartyDetails(String ecode) {
		return objEstampDAO.deactEcodePartyDetails(ecode);
	}

	public ArrayList deactDRName(String userId) {
		return objEstampDAO.deactDRName(userId);
	}

	public boolean insertDeactDetails(DutyCalculationForm eform, DashBoardDTO dto, String userId) {
		return objEstampDAO.insertDeactDetails(eform, dto, userId);
	}

	public ArrayList deactRequestId(DashBoardDTO dto) {
		return objEstampDAO.deactRequestId(dto);
	}

	public ArrayList checkEcodeDeact(String ecode) {
		return objEstampDAO.checkEcodeDeact(ecode);
	}

	public ArrayList checkEcodeExp(String ecode) {
		return objEstampDAO.checkEcodeExp(ecode);
	}

	public ArrayList checkEcodeDeactv(String ecode) {
		return objEstampDAO.checkEcodeDeactv(ecode);
	}

	public ArrayList checkEcodeConsumed(String ecode) {
		return objEstampDAO.checkEcodeConsumed(ecode);
	}

	// ADDED BY LAVI FOR JUDICIAL ESTAMPS
	public ArrayList getPendingApps(String userId, String lang) {
		return objEstampDAO.getPendingApps(userId, lang);
	}

	public ArrayList getCountry(String language) throws Exception {
		//ArrayList list =  new IGRSCommon().getCountry();
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCountryJud(language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue(subList.get(0).toString());
			dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public ArrayList getState(String cntryID, String language) throws Exception {
		//ArrayList list =  new IGRSCommon().getState(cntryID);
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getStateJud(cntryID, language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			if (subList.get(0).toString().equals("1")) {
				dto.setValue(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
			}
			mainList.add(dto);
		}
		return mainList;
	}

	public ArrayList getDistrict(String stateID, String language) throws Exception {
		ArrayList list = new IGRSCommon().getDistrict(stateID);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue(subList.get(0).toString());
			if (language.equalsIgnoreCase("en")) {
				dto.setName(subList.get(1).toString());
			} else {
				dto.setName(subList.get(2).toString());
			}
			// dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public ArrayList getPhoto(String language) throws Exception {
		ArrayList list = new IGRSCommon().getPhotoIdProof(language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue(subList.get(0).toString());
			dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getAppellate(String language) throws Exception {
		ArrayList list = new EstampDAO().getAppellate(language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue(subList.get(0).toString());
			dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public boolean insertTxn(EstampFormBean _form, String lang) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.insertTxn(_form, lang);
		return flg;
	}

	public ArrayList getDocType(String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getDocType(language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue1(subList.get(0).toString());
			dto.setName1(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public String getDate() {
		return objEstampDAO.getDate();
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getPayDtls(String txnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getPayDtls(txnId);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return String
	 */
	public String getDuty(String txnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		String list = dao.getDuty(txnId);
		return list;
	}

	/******************************************************************
	 * Method Name : insertPay() Arguments : Cash Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean insertPay(EstampFormBean _form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.insertPay(_form, dto);
		return flg;
	}

	//added by gulrej
	//This method updates IGRS_ESTAMP_PAYMENT_DETLS on basis of ESTAMP_TXN_ID
	public boolean updatePay1(EstampFormBean _form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.updatePay1(_form, dto);
		return flg;
	}

	public boolean updatePay(EstampFormBean _form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.updatePay(_form, dto);
		return flg;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getruName(String txnId, String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getruName(txnId, language);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return String
	 */
	public String getspName(String txnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		String list = dao.getspName(txnId);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getspDtls(String txnId, String lang) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getspDtls(txnId, lang);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getspbnkDtls(String txnId, String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getspbnkDtls(txnId, language);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getAppDtls(String txnId, String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getAppDtls(txnId, language);
		return list;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getPartyDtls(String txnId, String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getPartyDtls(txnId, language);
		return list;
	}

	/******************************************************************
	 * Method Name : inserteCode() Arguments : Cash Form Return : Boolean Throws
	 * : NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean inserteCode(EstampFormBean _form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.inserteCode(_form, dto);
		return flg;
	}

	/**
	 * @param -
	 * @exception Exception
	 * @return ArrayList
	 */
	public ArrayList getEcodeDtls(String txnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getEcodeDtls(txnId);
		return list;
	}

	/******************************************************************
	 * Method Name : updateTxn() Arguments : Cash Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean updateTxn(EstampFormBean _form, String lang) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.updateTxn(_form, lang);
		return flg;
	}

	public boolean updateStatus(EstampFormBean form) throws Exception, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.updateStatus(form);
		return flg;
	}

	public ArrayList getjudDetails(String TxnId, String language) {
		return objEstampDAO.getjudDetails(TxnId, language);
	}

	public ArrayList getDetailsOfApplicantJud(String TxnId, String language) {
		return objEstampDAO.getDetailsOfApplicantJud(TxnId, language);
	}

	public ArrayList getDetailsOfDocumentJud(String TxnId) {
		return objEstampDAO.getDetailsOfDocumentJud(TxnId);
	}

	public ArrayList getdocTypeValue(String documentName, String language) throws NullPointerException, SQLException, Exception {
		ArrayList docTypeValue = new ArrayList();
		EstampDAO dao = new EstampDAO();
		docTypeValue = dao.getdocTypeValue(documentName, language);
		return docTypeValue;
	}

	public ArrayList getecodeConsumption(String ecode) {
		return objEstampDAO.getecodeConsumption(ecode);
	}

	public String gettype(String userId) {
		String type = objEstampDAO.gettype(userId);
		return type;
	}

	public ArrayList getdetails(String userId, EstampDetailsDTO objEstampDto, String language) {
		ArrayList details = new ArrayList();
		details = objEstampDAO.getdetails(userId, objEstampDto, language);
		return details;
	}

	public ArrayList getrudetails(String userId, EstampDetailsDTO objEstampDto, String txnId, String language) {
		ArrayList details = new ArrayList();
		details = objEstampDAO.getrudetails(userId, objEstampDto, txnId, language);
		return details;
	}

	public ArrayList getiudetails(String offcId, EstampDetailsDTO objEstampDto, String language) {
		ArrayList details = new ArrayList();
		details = objEstampDAO.getiudetails(objEstampDto, offcId, language);
		return details;
	}

	public boolean insertDocDetls(EstampFormBean _form) throws NullPointerException, SQLException, Exception {
		boolean flg = false;
		EstampDAO dao = new EstampDAO();
		flg = dao.insertDocDetls(_form);
		return flg;
	}

	public ArrayList getiuDtls(String userId, String officeId, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getiuDtls(userId, officeId, language);
		return list;
	}

	public String getecodetype(String ecode) {
		String type = objEstampDAO.getecodetype(ecode);
		return type;
	}

	public ArrayList deactCREcodeDetailsZero(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.deactCREcodeDetailsZero(ecode, regID);
		return list;
	}

	public String getregId(String ecode) throws NullPointerException, SQLException, Exception {
		String id = objEstampDAO.getregID(ecode);
		return id;
	}

	public ArrayList deactCREcodeAppdetails(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.deactCREcodeAppdetails(ecode, regID);
		return list;
	}

	public ArrayList deactCREcodepartydetails(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.deactCREcodepartydetails(ecode, regID);
		return list;
	}

	public ArrayList viewCREcodeDetails(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.viewCREcodeDetails(ecode, regID);
		return list;
	}

	public ArrayList viewCREcodeDetailsDRS(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.viewCREcodeDetailsDRS(ecode, regID);
		return list;
	}

	public ArrayList getCRDetailsOfApplicantDRS(String ecode, String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRDetailsOfApplicantDRS(ecode, regID);
		return list;
	}

	public ArrayList getCRtype(String regID) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRtype(regID);
		return list;
	}

	public ArrayList getCRYDetailsOfApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRYDetailsOfApplicantDRS(regID, language);
		return list;
	}

	public ArrayList getCRNDetailsOfApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRNDetailsOfApplicantDRS(regID, language);
		return list;
	}

	public ArrayList getCRNorgDetailsOfApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRNorgDetailsOfApplicantDRS(regID, language);
		return list;
	}

	public ArrayList getCRNindvDetailsOfApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRNindvDetailsOfApplicantDRS(regID, language);
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRS(String ecode, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getindvDetailsOfApplicantDRS(ecode, language);
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRSAdpotion(String ecode, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getindvDetailsOfApplicantDRSAdoption(ecode, language);
		return list;
	}

	public ArrayList getorgDetailsOfApplicantDRS(String ecode, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getorgDetailsOfApplicantDRS(ecode, language);
		return list;
	}

	public ArrayList getNDetailsOfApplicantDRS(String ecode, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getNDetailsOfApplicantDRS(ecode, language);
		return list;
	}

	public ArrayList getCRYindvDetailsOfApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRYindvDetailsOfApplicantDRS(regID, language);
		return list;
	}

	public ArrayList getindvDetailsOfApplicantDRSNew(String ecode, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getindvDetailsOfApplicantDRSNew(ecode, language);
		return list;
	}

	public String getRemarksForConsumption(String eCode) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		return dao.getRemarksForConsumption(eCode);
	}

	//added by simran
	public String getDeactivateCount(String ecode) {
		EstampDAO dao = new EstampDAO();
		return dao.getDeactivateCount(ecode);
	}

	public String getCertChkDetails(String ecode) {
		EstampDAO dao = new EstampDAO();
		return dao.getCertChkDetails(ecode);
	}

	public String getEstampDocRegDetails(String ecode) {
		EstampDAO dao = new EstampDAO();
		return dao.getEstampDocRegDetails(ecode);
	}

	public ArrayList getEstampDocDetails(String ecode) {
		EstampDAO dao = new EstampDAO();
		return dao.getEstampDocDetails(ecode);
	}

	public boolean updateCertificateGenerationChk(String eStampTxnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.updateCertificateGenerationChk(eStampTxnId);
	}

	public String deedDraftId(String deedId) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.deedDraftId(deedId);
	}

	public String deedDraftIdValidate(String deedId, String userID, String type) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.deedDraftIdValidate(deedId, userID, type);
	}

	public String deedDraftIdIncompValidate(String deedId, String userId) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.deedDraftIdIncompValidate(deedId, userId);
	}

	public boolean getDeedAppDetls(String deedId, String userId, String type) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.getDeedAppDetls(deedId, userId, type);
	}

	public boolean updateDeedStatus(DutyCalculationForm form, String langauge, HttpServletResponse response) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.updateDeedConsume(form, langauge, response);
	}

	public String getAmount(String ecode) {
		return objEstampDAO.getAmount(ecode);
	}

	public String getCurrDateTime() {
		try {
			return objEstampDAO.getCurrDateTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getSubjectNameBD(String userId) {
		return objEstampDAO.getSubjectNameDao(userId);
	}

	public String getUserType(String userId) {
		return objEstampDAO.getUserType(userId);
	}

	public boolean checkEstamp(String regTxnID, String mod_flag) {
		//DutyCalculationDAO dao = new DutyCalculationDAO();
		return objEstampDAO.checkEstamp(regTxnID, mod_flag);
	}

	public String getEcode(String txnID, String modFlag) {
		return objEstampDAO.getEcode(txnID, modFlag);
	}

	public String[] getFatherName(String txnID) {
		ArrayList list = objEstampDAO.getFatherName(txnID);
		ArrayList subList = (ArrayList) list.get(0);
		String arr[] = { subList.get(0).toString(), subList.get(1).toString() };
		return arr;
	}

	public String deedDraftIdValidateInEstampTxn(String deedId) throws Exception {
		EstampDAO dao = new EstampDAO();
		return dao.deedDraftIdValidateInEstampTxn(deedId);
	}

	public String checkUser(String estampCodeId, String userId) {
		EstampDAO dao = new EstampDAO();
		return dao.checkUser(estampCodeId, userId);
	}

	public ArrayList getDistrictCourt(String language) throws Exception {
		ArrayList list = new EstampDAO().getDistrictCourt(language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			dto.setValue(subList.get(0).toString());
			dto.setName(subList.get(0).toString());
			/*if(language.equalsIgnoreCase("en"))
			{
			dto.setValue(subList.get(1).toString());
			dto.setName(subList.get(1).toString());
			}
			else
			{
				dto.setValue(subList.get(2).toString());
				dto.setName(subList.get(2).toString());
			}*/
			//        if(language.equalsIgnoreCase("en"))
			//        {
			//        	dto.setName(subList.get(1).toString());
			//        }
			//        else
			//        {
			//        	dto.setName(subList.get(2).toString());
			//        }
			// dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public ArrayList getCourtList(String courtType, String DistrictCourtId, String language) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCourtList(courtType, DistrictCourtId, language);
		ArrayList mainList = new ArrayList();
		ArrayList subList = null;
		EstampDetailsDTO dto = new EstampDetailsDTO();
		for (int i = 0; i < list.size(); i++) {
			subList = (ArrayList) list.get(i);
			dto = new EstampDetailsDTO();
			//dto.setValue(subList.get(0).toString()+","+subList.get(0).toString());
			if (language.equalsIgnoreCase("en")) {
				dto.setName(subList.get(1).toString());
				dto.setValue(subList.get(0).toString() + "::" + subList.get(1).toString());
			} else {
				dto.setName(subList.get(2).toString());
				dto.setValue(subList.get(0).toString() + "::" + subList.get(2).toString());
			}
			// dto.setName(subList.get(1).toString());
			mainList.add(dto);
		}
		return mainList;
	}

	public ArrayList getjudCourtDetails(String txnId) throws Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getjudCourtDetails(txnId);
		return list;
	}

	public ArrayList viewEcodeDetailsNJ_DRS(String ecode) {
		return objEstampDAO.viewEcodeDetailsNJ_DRS(ecode);
	}

	public String getGovtStamp(String txnID) {
		return objEstampDAO.getGovtStamp(txnID);
	}

	public ArrayList getRefundRequestDtls(String ecode) {
		return objEstampDAO.getRefundRequestDtls(ecode);
	}
	//ADDED BY SAURAV
	public ArrayList getCRYindvDetailsOfGOVApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRYindvDetailsOfGOVApplicantDRS(regID, language);
		return list;
	}
	public ArrayList getCRNindvDetailsOfGOVApplicantDRS(String regID, String language) throws NullPointerException, SQLException, Exception {
		EstampDAO dao = new EstampDAO();
		ArrayList list = dao.getCRNindvDetailsOfGOVApplicantDRS(regID, language);
		return list;
	}
	
}

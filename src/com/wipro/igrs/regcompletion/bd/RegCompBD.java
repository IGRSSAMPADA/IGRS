package com.wipro.igrs.regcompletion.bd;


/**
 * ===========================================================================
 * File           :   RegCompBD.java
 * Description    :   Represents the RegComp BD Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.bo.PropertyValuationBO;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dao.RegCompDAO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.PropertyValuationDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;
import com.wipro.igrs.regcompletion.form.ApplicantForm;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;
import com.wipro.igrs.spotInsp.bo.SpotInspBO;
import com.wipro.igrs.util.CommonUtil;


public class RegCompBD{

	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompBD.class);
	RegCompDAO regCompDAO =  new RegCompDAO();
	
	RegCompBO refBO= null;
	public RegCompBD() {
		 
	}
	
	
	/**
	 * for getting All Deed Details
	 * @return ArrayList
	 */
	public ArrayList getDeedType() {
		ArrayList list = regCompDAO.getDeedTypeList();
		ArrayList listDeed = new ArrayList();
		if(list!=null) {
			for(int i = 0;i<list.size();i++) {
				ArrayList listReturn = (ArrayList) list.get(i);
				RegInitCompleteDTO dto = new RegInitCompleteDTO();
				dto.setDeedId(listReturn.get(0).toString()+"~"
							+listReturn.get(1).toString()+"~"
							+listReturn.get(2).toString()+"~"
							+listReturn.get(3).toString()+"~"
							+listReturn.get(4).toString());
				dto.setDeedName(listReturn.get(1).toString());
				dto.setDeedValReq(listReturn.get(2).toString());
				dto.setDutyCalReq(listReturn.get(3).toString());
				dto.setPropertyRelatedDeed(listReturn.get(4).toString());
				listDeed.add(dto);
			}
		}
		
		
		return listDeed;
	}
	
	/**
	 * for getting Instruments list based on deed
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getInstrument(RegInitCompleteDTO dto) {
		ArrayList returnList = new ArrayList();
		 try {
			 
			 String deedId = dto.getDeedId();
			 String instId = dto.getInstrumentId();
			 String[] deedAry = deedId !=null ? deedId.split("~") : null;
			 IGRSCommon common = new IGRSCommon();
			 String[] deedType = new String[3];
			 deedType[0] = deedAry[0];
			 deedType[1] = instId;
			 deedType[2] = "A";
			 ArrayList listExem = common.getExemptions(deedType);
			 if(listExem!=null) {
				 for(int i =0;i<listExem.size();i++) {
					 ArrayList list = (ArrayList) listExem.get(i);
					 RegInitCompleteDTO listDTO = new RegInitCompleteDTO();
					 listDTO.setExemptionId(list.get(0).toString());
					 listDTO.setExemptionName(list.get(1).toString());
					 returnList.add(listDTO);
				 }
			 }
		 }catch(Exception x) {
			 logger.debug(x);
		 }
		 
		 return returnList;
	}
	
	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * @param deed
	 * @param deed1
	 * @param string 
	 * @return ArrayList
	 */
	public ArrayList getExemption(String deed,String deed1, String string) {
		return regCompDAO.getExemption(deed,deed1,string);
	}
	
	/**
	 * returns the given application details
	 * @param regNo
	 * @return ApplicantForm
	 */
	public RegInitCompleteDTO getRegApplicationDetails(String regNo) {
		return regCompDAO.getRegApplicationDetails(regNo);
	}
	
	/**
	 * returns the Form Fields for selected deed
	 * @param deed
	 * @return ArrayList
	 */
	/*public ArrayList getFormFields(String deed) throws Exception {
		return regCompDAO.getFormFields(deed);
	}*/
	
	/**
	 * Storing deed details
	 * @param deedDetails
	 * @return ArrayList
	 */
	/*public ArrayList saveDeedDetails(ArrayList deedDetails) {
		return regCompDAO.saveDeedDetails(deedDetails);
	}*/

	/**
	 * getting deed details for given Deed Id
	 * @param deedId
	 * @return ApplicantForm
	 */
	public RegInitCompleteDTO getDeedDetails(String deedId) throws Exception {
		return regCompDAO.getDeedDetails(deedId);
	}
	
	/**
	 * Update Deed Details
	 * @param appForm
	 * @return boolean
	 */
/*	public boolean updateDeedDetails(ApplicantForm appForm) {
		return regCompDAO.updateDeedDetails(appForm);
	}*/
	
	/**
	 * Delete Deed Details
	 * @param appForm
	 * @return boolean
	 */
	public ArrayList deleteDeedDetails(RegInitCompleteDTO appForm) {
		return regCompDAO.deleteDeedDetails(appForm);
	}
	
	/**
	 * Save Deed Details
	 * @param appForm
	 * @return ArrayList
	 */
	public ArrayList saveDeedDetail(RegInitCompleteDTO dto) 
			throws Exception{
		return regCompDAO.saveDeedDetail(dto);
	}
	
	/**
	 * returns the selected deed details only
	 * @param deed
	 * @return CommonDTO
	 */
	public CommonDTO getDeed(String deed) {
		return regCompDAO.getDeed(deed);
	}
//piyush data
	public boolean addParty(CommonDTO _cDTO) throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
    	try{
    		 refBO=new RegCompBO();
	    	CommonUtil cUtil=new CommonUtil();
	    	String param[]=new String[28];
	    	String txnIdAuto=cUtil.getAutoId("IGRS_REG_TXN_PARTY_DETAILS","PARTY_TXN_ID","PTXN");
	
	    	param[0]=""+txnIdAuto;
	    	param[1]=""+_cDTO.getPartyType();
	    	param[2]=""+_cDTO.getFname();
	    	param[3]=""+_cDTO.getMname();
	    	param[4]=""+_cDTO.getLname();
	    	param[5]=""+_cDTO.getGender();
	    	param[6]=""+_cDTO.getAge();
	    	param[7]=""+_cDTO.getNationality();
	    	param[8]=""+_cDTO.getCountryName();
	    	param[9]=""+_cDTO.getStateName();
	    	param[10]=""+_cDTO.getDistrictName();
	    	param[11]=""+_cDTO.getAddress();
	    	param[12]=""+_cDTO.getPostalCode();
	    	param[13]=""+_cDTO.getPhoneNo();
	    	param[14]=""+_cDTO.getMobNo();
	    	param[15]=""+_cDTO.getMailId();
	    	param[16]=""+_cDTO.getPhotoProofTypeName();
	    	param[17]=""+_cDTO.getPhotoId();
	    	param[18]=""+_cDTO.getBankName();
	    	param[19]=""+_cDTO.getBankAddress();
	    	param[20]=""+_cDTO.getFatherName();
	    	param[21]=""+_cDTO.getMotherName();
	    	param[22]=""+_cDTO.getCasteName();
	    	param[23]=""+_cDTO.getReligionName();
	    	param[24]=""+_cDTO.getOrganizationName();
	    	param[25]=""+_cDTO.getOccupation();
	    	param[26]=""+_cDTO.getPhyChallanged();
	    	param[27]=""+_cDTO.getTempApplNo();
    	
	    	logger.info("After Assigning value from DTO to Param Array");
	    	return (refBO.addPartyBO(param));//refDAO.addPartyDao(param));
    	}catch (Exception e) {
			logger.error("Exception Caught in BD "+e);
    			return false;
		}
    }
    
    public boolean addOrga(CommonDTO _cDTO) throws Exception
    {
    	try{
    		 refBO=new RegCompBO();
	    	CommonUtil cUtil=new CommonUtil();
	    	String param[]=new String[13];
	    	String txnIdAuto=cUtil.getAutoId("IGRS_REG_TXN_PARTY_DETAILS","PARTY_TXN_ID","PTXN");
	
	    	param[0]=""+txnIdAuto;
	    	param[1]=""+_cDTO.getPartyType();
	    	param[2]=""+_cDTO.getOrganizationName();
	    	param[3]=""+_cDTO.getAuthPersonName();
	    	param[4]=""+_cDTO.getCountryName();
	    	param[5]=""+_cDTO.getStateName();
	    	param[6]=""+_cDTO.getDistrictName();
	    	param[7]=""+_cDTO.getAddress();
	    	param[8]=""+_cDTO.getPostalCode();
	    	param[9]=""+_cDTO.getPhoneNo();
	    	param[10]=""+_cDTO.getMobNo();
	    	param[11]=""+_cDTO.getMailId();
	    	param[12]=""+_cDTO.getTempApplNo();
	    	
	        return refBO.addOrgaBO(param);
    	}catch (Exception e) {
			logger.error("Exception Caught in BD "+e);
			return false;
		}
    }
    public ArrayList selectParties(String _appliNo) throws Exception
    {
        ArrayList list=new ArrayList();
        try{
        	 refBO=new RegCompBO();
        	list= refBO.selectPartiesBO(_appliNo);
        }catch(Exception ex)
        {
            logger.error(" searchByRegNO Exception --> " +ex);
            return null;
        }
        return list;
    }
    public CommonDTO viewParty(String _partyId)throws Exception
    {
    	CommonDTO cdto=new CommonDTO();
    	 refBO=new RegCompBO();
		ArrayList partyData=new ArrayList();
    	try{
    		partyData=refBO.viewPartyBO(_partyId);
    		if(!partyData.isEmpty())
    		{
    			ArrayList fieldList= new ArrayList();
                fieldList=(ArrayList) partyData.get(0);
                cdto.setPartyId((String)fieldList.get(0));
                cdto.setPartyType((String)fieldList.get(1));
                if(fieldList.get(24)==null)
                {
	                cdto.setFname((String)fieldList.get(2));
	                cdto.setMname((String)fieldList.get(3));
	                cdto.setLname((String)fieldList.get(4));
	                cdto.setGender((String)fieldList.get(5));
	                cdto.setAge(Integer.parseInt(String.valueOf(fieldList.get(6))));
	                cdto.setNationality((String)fieldList.get(7));
	                
	                cdto.setPhotoProofTypeName((String)fieldList.get(16));
	                cdto.setPhotoId((String)fieldList.get(17));
	                cdto.setBankName((String)fieldList.get(18));
	                cdto.setBankAddress((String)fieldList.get(19));
	                cdto.setFatherName((String)fieldList.get(20));
	                cdto.setMotherName((String)fieldList.get(21));
	                cdto.setCasteName((String)fieldList.get(22));
	                cdto.setReligionName((String)fieldList.get(23));
	                cdto.setOccupation((String)fieldList.get(25));
	                cdto.setPhyChallanged((String)fieldList.get(26));
	            }
                else
                {
                	cdto.setAuthPersonName((String)fieldList.get(2));
                	cdto.setOrganizationName((String)fieldList.get(24));
                }
                cdto.setCountryName((String)fieldList.get(8));
                cdto.setStateName((String)fieldList.get(9));
                cdto.setDistrictName((String)fieldList.get(10));
                cdto.setAddress((String)fieldList.get(11));
                cdto.setPostalCode(Integer.parseInt(String.valueOf(fieldList.get(12))));
                cdto.setPhoneNo((String)fieldList.get(13));
                cdto.setMobNo((String)fieldList.get(14));
                cdto.setMailId((String)fieldList.get(15));
    			return cdto;
    		}
    		else
    		{
    			logger.info("DTO not set");
    			return null;
    		}
    	}catch (Exception e) {
			logger.error("Exception Caught "+e);
			return null;
		}
    }
    public boolean deleteParty(String _partyId)throws Exception
    {
    	 refBO=new RegCompBO();
    	try{
    		boolean result=refBO.deletePartyBO(_partyId);
    		return result;
    	}catch (Exception e) {
    		logger.error("Exception in deletion "+e);
    		return false;
    	}
    }
    public CommonDTO retrieveParty(String _partyId)throws Exception
    {
    	CommonDTO cdto=new CommonDTO();
    	 refBO=new RegCompBO();
    	ArrayList partyData=new ArrayList();
    	try{
    		partyData=refBO.retrievePartyBO(_partyId);
    		if(!partyData.isEmpty())
    		{
    			ArrayList fieldList= new ArrayList();
                fieldList=(ArrayList) partyData.get(0);
                cdto.setPartyId((String)fieldList.get(0));
                cdto.setPartyType((String)fieldList.get(1));
                if(fieldList.get(24)==null)
                {
	                cdto.setFname((String)fieldList.get(2));
	                cdto.setMname((String)fieldList.get(3));
	                cdto.setLname((String)fieldList.get(4));
	                cdto.setGender((String)fieldList.get(5));
	                cdto.setAge(Integer.parseInt(String.valueOf(fieldList.get(6))));
	                cdto.setNationality((String)fieldList.get(7));
	                cdto.setPhotoProofTypeName((String)fieldList.get(16));
	                cdto.setPhotoId((String)fieldList.get(17));
	                cdto.setBankName((String)fieldList.get(18));
	                cdto.setBankAddress((String)fieldList.get(19));
	                cdto.setFatherName((String)fieldList.get(20));
	                cdto.setMotherName((String)fieldList.get(21));
	                cdto.setCasteName((String)fieldList.get(22));
	                cdto.setReligionName((String)fieldList.get(23));
	                cdto.setOccupation((String)fieldList.get(25));
	                cdto.setPhyChallanged((String)fieldList.get(26));
                }
                else
                {
                	cdto.setAuthPersonName((String)fieldList.get(2));
                	cdto.setOrganizationName((String)fieldList.get(24));
                }
                cdto.setCountryName((String)fieldList.get(8));
                cdto.setCountryId((String)fieldList.get(8));
                cdto.setStateName((String)fieldList.get(9));
                cdto.setStateId((String)fieldList.get(9));
                cdto.setDistrictName((String)fieldList.get(10));
                cdto.setDistrictId((String)fieldList.get(10));
                cdto.setAddress((String)fieldList.get(11));
                cdto.setPostalCode(Integer.parseInt(String.valueOf(fieldList.get(12))));
                cdto.setPhoneNo((String)fieldList.get(13));
                cdto.setMobNo((String)fieldList.get(14));
                cdto.setMailId((String)fieldList.get(15));
    			return cdto;
    		}
    		else
    		{
    			logger.info("If PartyData is Empty");
    			return null;
    		}
    	}catch (Exception e) {
			logger.error("Exception Caught "+e);
			return null;
		}
    }
    public boolean updateParty(CommonDTO _cDTO) throws Exception
    {
    	try{
    		//RegCompDAO refDAO=new RegCompDAO();
    		 refBO=new RegCompBO();
	    	if(_cDTO.getPartyType().equalsIgnoreCase("Individuals"))
	    	{
	    		logger.info("If INDIVIDUALS");
	    		String param[]=new String[26];
		    	param[0]=""+_cDTO.getFname();
		    	param[1]=""+_cDTO.getMname();
		    	param[2]=""+_cDTO.getLname();
		    	param[3]=""+_cDTO.getGender();
		    	param[4]=""+_cDTO.getAge();
		    	param[5]=""+_cDTO.getNationality();
		    	param[6]=""+_cDTO.getCountryName();
		    	param[7]=""+_cDTO.getStateName();
		    	param[8]=""+_cDTO.getDistrictName();
		    	param[9]=""+_cDTO.getAddress();
		    	param[10]=""+_cDTO.getPostalCode();
		    	param[11]=""+_cDTO.getPhoneNo();
		    	param[12]=""+_cDTO.getMobNo();
		    	param[13]=""+_cDTO.getMailId();
		    	param[14]=""+_cDTO.getPhotoProofTypeName();
		    	param[15]=""+_cDTO.getPhotoId();
		    	param[16]=""+_cDTO.getBankName();
		    	param[17]=""+_cDTO.getBankAddress();
		    	param[18]=""+_cDTO.getFatherName();
		    	param[19]=""+_cDTO.getMotherName();
		    	param[20]=""+_cDTO.getCasteName();
		    	param[21]=""+_cDTO.getReligionName();
		    	param[22]="";//+_cDTO.getOrganizationName();
		    	param[23]=""+_cDTO.getOccupation();
		    	param[24]=""+_cDTO.getPhyChallanged();
		    	param[25]=""+_cDTO.getPartyId();
		    	return (refBO.updatePartyBO(_cDTO.getPartyType(),param));
	    	}
	    	else
	    	{
	    		logger.info("If Organization");
	    		String param[]=new String[11];
	    		param[0]=""+_cDTO.getAuthPersonName();
	    		param[1]=""+_cDTO.getCountryName();
	        	param[2]=""+_cDTO.getStateName();
	        	param[3]=""+_cDTO.getDistrictName();
	        	param[4]=""+_cDTO.getAddress();
	        	param[5]=""+_cDTO.getPostalCode();
	        	param[6]=""+_cDTO.getPhoneNo();
	        	param[7]=""+_cDTO.getMobNo();
	        	param[8]=""+_cDTO.getMailId();
	        	param[9]=""+_cDTO.getOrganizationName();
	        	param[10]=""+_cDTO.getPartyId();
	        	return (refBO.updatePartyBO(_cDTO.getPartyType(),param));
	    	}
    	}catch (Exception e) {
			logger.error("Exception Caught in BD "+e);
			return false;
		}
    }
    public ArrayList countryStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (countryStackBD) Method");
    		listBD=refBO.countryStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList stateStackBD(String _countryId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (stateStackBD) Method");
    		listBD=refBO.stateStackBO(_countryId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList photoIdStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (photoIdStackBD) Method");
    		listBD=refBO.photoIdStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList casteStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (casteStackBD) Method");
    		listBD=refBO.casteStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList religionStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (religionStackBD) Method");
    		listBD=refBO.religionStackBO();
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList districtStackBD(String _stateId)throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		 refBO=new RegCompBO();
		ArrayList listBD=new ArrayList();
    	try{
    		logger.info("Inside (districtStackBD) Method");
    		listBD=refBO.districtStackBO(_stateId);
    		return listBD;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }

    /**
     * returns the selected deeds list for given Reg Id
     * @param regId
     * @return ArrayList
     */
	public HashMap getDeedsList(String regId) {
		refBO=new RegCompBO();
		return refBO.getDeedsList(regId);
	}

	/**
	 * returns the selected Properties list for given Reg Id
	 * @param regId
	 * @return ArrayList
	 */
	public ArrayList getPropertyListByRegId(String[] param) {
		refBO=new RegCompBO();
		return refBO.getPropertyListByRegId(param);
	}
	public ArrayList getPropertyList(String  regID) {
		refBO=new RegCompBO();
		String[] param = new String[1];
		param[0] = regID;
		return refBO.getPropertyList(param);
	}
	
	/**
	 * based on deed txn it will returns the number of dependent properties
	 * @param deed
	 * @return NofProperty
	 */
	
	
	public String getNofProperty(String deed) {
		refBO=new RegCompBO();
		return refBO.getNofProperty(deed);
	}

	
	/**
	 * save Deed To Property or Transacting Party Mapping
	 * @param string
	 * @return boolean
	 */

	public boolean saveDeedToPropAndTranMap(HashMap map,String regNo)
						throws Exception {
		refBO=new RegCompBO();
		 
		boolean boo=false;
		String params[] = new String[5];
		DBUtility dbUtility = new DBUtility();
	    
		refBO.deleteDeedToPropAndTranMap(regNo);
		try {
			Iterator I = map.entrySet().iterator();
			while(I.hasNext()) {
				Map.Entry me = (Map.Entry) I.next();
				String key = (String)me.getKey();
				CommonDTO dtoValue = (CommonDTO)me.getValue();
				String[] param = new String[5];
				param[0] = regNo;
				param[1] = dtoValue.getDeed();
				param[2] = dtoValue.getPropId();
				
				ArrayList list = dtoValue.getPropertyList();
				
				if(list!=null) {
					
					for(int i =0;i<list.size();i++) {
						CommonDTO dto = (CommonDTO)list.get(i);
						param[3] = dto.getPartyId();
						param[4] = dto.getPartyTypeId();
						
						logger.debug("dto.getPartyId():-"+dto.getPartyId());
						logger.debug("dto.getPartyTypeId():-"
										+dto.getPartyTypeId());
						boo = refBO.saveDeedToPropAndTranMap(param,dbUtility);
						if(boo) {
							dbUtility.commit();
						}else {
							dbUtility.rollback();
						}
						
						for(int j=0;j<param.length;j++) {
							logger.debug("param["+j+"]="+param[j]);
						}
					}
				}
				
			}
		 
		}catch(Exception x) {
			logger.debug(x);
			dbUtility.rollback();
		}finally{
			dbUtility.closeConnection();
		}
		return boo;
	}
	public boolean saveDeedToPropAndTranMap(String string,String regNo)
		throws Exception {
		refBO=new RegCompBO();
		String main[] = string.split(",");
		boolean boo=false;
		String params[] = new String[5];
		DBUtility dbUtility = new DBUtility();
		logger.debug("param:-"+string);
		refBO.deleteDeedToPropAndTranMap(regNo);
		try {
			for(int i=0;i<main.length;i++){
				params = main[i].split("#");
				/*String[] param = new String[7];
				param[0] = params[4];
				param[1] = params[2];
				param[2] = params[3];
				param[3] = params[0];
				param[4] = params[1];
				param[5] = params[2]; 
				param[6] = params[3];*/


			 
				     
				boo = refBO.saveDeedToPropAndTranMap(params,dbUtility);
				if(boo) {
					dbUtility.commit();
				}else {
					dbUtility.rollback();
				}
			}
			
		}catch(Exception x) {
			logger.debug(x);
			dbUtility.rollback();
		}finally{
			dbUtility.closeConnection();
		}
		return boo;
	}

	
	/**
	 * save Deed To Property or Transacting Party Mapping
	 * @param params
	 * @param nofProperty
	 * @param size
	 * @return boolean
	 */
	
	
	public HashMap saveDeedToPropAndTranMap(CommonDTO dto,
			String nofProperty, HashMap map) {
		refBO=new RegCompBO();
		return refBO.saveDeedToPropAndTranMap(dto,nofProperty,map);
	}


	/**
	 * to get the details of Deed , Property and Transaction Parties details
	 * @param regNo
	 * @return ArrayList
	 */
	
	
	public ArrayList getDeedPropAndTreanMapping(String regNo) {
		refBO=new RegCompBO();
		return refBO.getDeedPropAndTreanMapping(regNo);
	}
	
	
	public ArrayList getDeedList(String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompBO().getDeedList(regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPropertyList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompBO().getPropertyList(deedTxnId, regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPartyList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompBO().getPartyList(deedTxnId, regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPartyTypeList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompBO().getPartyTypeList(deedTxnId,regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	/** pav Code
	 * getting the compliance list.
	 * @return
	 * @throws Exception
	 */
	 public ArrayList getComplList() 
	 {
		 refBO=new RegCompBO();
	        ArrayList list  =  refBO.getComplList();
	        ArrayList  list1  =  new ArrayList();
	         
	        if (list != null) {
	            for (int i  =  0; i < list.size(); i++) {
	                ArrayList subList  =  (ArrayList)list.get(i);
	                CommonDTO dto = new  CommonDTO();               
	                dto.setId((String)subList.get(0));
	                dto.setName((String)subList.get(1));
	                list1.add(dto);
	            }

	        }
	        return list1;
	    }

	/**
	  * inserting compliance details.
	  * @param form
	  * @return
	  */
	public boolean insComplDet(String regNo, String compId,String userId) {
		boolean boo=false;
		 refBO=new RegCompBO();
		String param[] = new String[4];
		param[0] = regNo;
		param[2] = "A";
		param[3] = userId;
		logger.debug(regNo);
		logger.debug(userId);
		logger.debug(compId);
		
		String temp[]=compId.split(",");
			for(int i=0;i<temp.length;i++){
				param[1] = temp[i];
				boo = refBO.insComplDet(param);
			}
		return boo;
	}
	
	/**
	 * 
	 * @param regNO
	 * @param functionId
	 * @param userId
	 * @param serviceId
	 * @return ArrayList
	 */
	public ArrayList getPrDeedDet(String regNO,String functionId, String userId, String serviceId) 
				throws Exception
	{
		
		CommonDTO dto= null;
		IGRSCommon common = null;
		try {
			common = new IGRSCommon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		refBO=new RegCompBO();       
        ArrayList deedTypeList;
        ArrayList insExemList;
        ArrayList consAmtList;
        ArrayList subConsList;
        ArrayList propList = null;
        ArrayList subDutyList;
        ArrayList munDuty;
        ArrayList  list1  =  new ArrayList();
        ArrayList othersDuty = null;
        double consAmt = 0.0;
        double sysAmt = 0.0;
        String strConsAmt = null;
        String strSysAmt;
        String[] stampDuty ;
        String[] regFee = null ;
        ApplicantForm form;
        String tehsil = null ;
    	String ward = null ;
    	String mohilla = null ;
    	String propVal= null ;
    	 ArrayList list  =  refBO.getPrDeedDet(regNO);
    	 // Added By Aruna
    	 HashMap regDeedPropertyMap=new HashMap();
    	 int firstTime=0;
        if (list != null)
        	 if (list.size()>0)
        {
            for (int i  =  0; i < list.size(); i++) {
            	//String[] exem ;
            	
            
            	String strOtherFee;
            	double total = 0.0; 
            	stampDuty = new String[3];
            	othersDuty = new ArrayList();
                ArrayList subList  =  (ArrayList)list.get(i);
                // Added By Aruna
            	//regDeedPropertyMap.put("regID",(String)subList.get(0));
            	//regDeedPropertyMap.put("deedID",(String)subList.get(1));
            	//regDeedPropertyMap.put("propertyID",(String)subList.get(2));
            	if(!whetherToLoop(regDeedPropertyMap,subList,firstTime))
            	{
            		firstTime=+1;            	
                dto = new  CommonDTO();               
                dto.setRegNo((String)subList.get(0));
                dto.setDeed((String)subList.get(1));
                dto.setPropId((String)subList.get(2));
                dto.setPartyId((String)subList.get(3));
                dto.setPartyType((String)subList.get(4));
                dto.setMiddleName((String)subList.get(5));
                dto.setName((String)subList.get(6));
                dto.setFname((String)subList.get(7)+""+(String)subList.get(8)+""+(String)subList.get(9));
                
                form = new ApplicantForm();
                deedTypeList = refBO.getDeedTypeId(dto.getDeed());
                ArrayList subPropList  =  (ArrayList)deedTypeList.get(0);
                String deedId = (String)subPropList.get(0);
                dto.setCasteName((String)subPropList.get(1));
                insExemList = refBO.getInsExemList(dto.getDeed());
                String strexem="";
                int im=0;
                if(insExemList.size()>0)
                {
                	ArrayList subinsExemList = null;
                	for (int j=0; j< insExemList.size(); j++)
                	 {
                 subinsExemList  =  (ArrayList)insExemList.get(j);
                 //exem = new String[j];
                if(subinsExemList.size()>0)
                {
                form.setInstrument((String)subinsExemList.get(0));
                form.setExemption((String)subinsExemList.get(1));
                }
                if(im==0)
            		strexem = form.getExemption();
            	else
            		strexem =strexem+","+form.getExemption();
                im++;
                }
                }
                stampDuty = refBO.getStampDuty(deedId,form.getInstrument(),strexem,dto.getPropId());
                othersDuty = refBO.getOthersDuty(functionId,serviceId,userId);
                strOtherFee = (String)othersDuty.get(2);
                total =Double.parseDouble(stampDuty[0])+Double.parseDouble(strOtherFee);
            	dto.setStampDuty(stampDuty[0]);	
            	dto.setOtherFee(strOtherFee);
            	dto.setTotal(String.valueOf(total));
            	consAmtList = refBO.getPropertyValueDetailsList(dto.getPropId());
            	
            	if(consAmtList.size()>0)
            	{
            	for (int j=0; j< consAmtList.size(); j++)
            	{
            	subConsList  =  (ArrayList)consAmtList.get(j);
            	strSysAmt = (String)subConsList.get(0);
            	strConsAmt = (String)subConsList.get(1);
            	sysAmt = sysAmt + Double.parseDouble(strSysAmt);
            	consAmt = consAmt+Long.parseLong(strConsAmt);
            	}
            	}
            	dto.setConsAmt(common.getCurrencyFormat(consAmt));
            	dto.setSysAmt(String.valueOf(sysAmt));
            	if(consAmt>sysAmt)
            		propVal = String.valueOf(consAmt);
            	else
            		propVal = String.valueOf(sysAmt);
            	propList = refBO.getPropDetDuty(dto.getPropId());
            	if(propList.size()>0)
            	{
            	subDutyList= (ArrayList)propList.get(0);
            	dto.setDistrictId((String)subDutyList.get(0));
            	tehsil = (String)subDutyList.get(1);
            	ward = (String)subDutyList.get(2);
            	mohilla = (String)subDutyList.get(3);
            	}
            	munDuty = refBO.getMunDuty(dto.getDistrictId(),tehsil,ward,mohilla,propVal);
            	dto.setDutyName((String)munDuty.get(1));
            	dto.setDutyVal((String)munDuty.get(2));
            	regFee = refBO.getRegFeeDuty(deedId,form.getInstrument(),strexem,propVal);
            	dto.setRegFee(regFee[0]);
            	total =Double.parseDouble(stampDuty[0])+
            	Double.parseDouble(strOtherFee)+Double.parseDouble(dto.getDutyVal());
            	dto.setTotal(common.getCurrencyFormat(total));
            	// Added By Aruna
            	dto.setRorRequired((String)subList.get(11));
            	dto.setDeedTypeName((String)subList.get(12));
            	list1.add(dto);
            	} // End of if
            } // End of for loop
               
                
            }
            
        return list1;
        }
	/*public ArrayList getPrDeedDet(String regNO,String functionId, String userId, String serviceId) 
	{
		
		logger.debug("RegCompBD -- getPrDeedDet()");
		CommonDTO dto= null;
		refBO=new RegCompBO();
        ArrayList list  =  refBO.getPrDeedDet(regNO);
        ArrayList deedTypeList;
        ArrayList insExemList;
        ArrayList  list1  =  new ArrayList();
        ArrayList othersDuty = null;
        String[] stampDuty ;
        ApplicantForm form;
        if (list != null) {
            for (int i  =  0; i < list.size(); i++) {
            	String[] exem ;
            	String strOtherFee;
            	Double total = 0.0; 
            	stampDuty = new String[3];
            	othersDuty = new ArrayList();
                ArrayList subList  =  (ArrayList)list.get(i);
                dto = new  CommonDTO();               
                dto.setRegNo((String)subList.get(0));
                dto.setDeed((String)subList.get(1));
                dto.setPropId((String)subList.get(2));
                dto.setPartyId((String)subList.get(3));
                dto.setPartyType((String)subList.get(4));
                dto.setMiddleName((String)subList.get(5));
                dto.setName((String)subList.get(6));
                dto.setFname((String)subList.get(7)+""+(String)subList.get(8)+""+(String)subList.get(9));
                
                form = new ApplicantForm();
                deedTypeList = refBO.getDeedTypeId(dto.getDeed());
                ArrayList subPropList  =  (ArrayList)deedTypeList.get(0);
                String deedId = (String)subPropList.get(0);
                dto.setCasteName((String)subPropList.get(1));
                insExemList = refBO.getInsExemList(dto.getDeed());
                String strexem="";
                int im=0;
                if(insExemList.size()>0)
                {
                	ArrayList subinsExemList = null;
                	for (int j=0; j< insExemList.size(); j++)
                	 {
                 subinsExemList  =  (ArrayList)insExemList.get(j);
                 exem = new String[j];
                if(subinsExemList.size()>0)
                {
                form.setInstrument((String)subinsExemList.get(0));
                form.setExemption((String)subinsExemList.get(1));
                }
                if(im==0)
            		strexem = form.getExemption();
            	else
            		strexem =strexem+","+form.getExemption();
                im++;
                }
                }
                stampDuty = refBO.getStampDuty(deedId,form.getInstrument(),strexem,dto.getPropId());
                othersDuty = refBO.getOthersDuty(functionId,serviceId,userId);
                strOtherFee = (String)othersDuty.get(2);
                total =Double.parseDouble(stampDuty[0])+Double.parseDouble(strOtherFee);
            	dto.setStampDuty(stampDuty[0]);	
            	dto.setOtherFee(strOtherFee);
            	dto.setTotal(total.toString());
            	
                list1.add(dto);
                
            }
            
            
        }
        return list1;
	}
	*/
	/**
	 * Hari Venkata
	 */
	/**
	 * @param _distId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPropertyTxnIdList(String _regTxnId) throws Exception {
		ArrayList propList = new ArrayList();

		try {

			ArrayList tmpList = regCompDAO.getPropertyTxnIdList(_regTxnId);
			for (int i = 0; i < tmpList.size(); i++) {
				ArrayList tmpsubList = (ArrayList) tmpList.get(i);
				if (tmpsubList != null) {
					RegCompletDTO dto = new RegCompletDTO();
					dto.setPropertyTxnNo((String) tmpsubList.get(0));

					propList.add(dto);
				}

			}
			logger.info("getPartyDetails-->" + propList);
		} catch (Exception e) {
			logger.error(e);

		}
		return propList;
	}
	public ArrayList getSelectedProperty (String _propTxnNo)
		throws Exception {
		ArrayList returnList = new ArrayList();
		logger.debug("in bd getSelectedPropertyDetails " + _propTxnNo);
		DBUtility dbUtility = new DBUtility();
		
		try {
	
		ArrayList tmpPropDetList = regCompDAO
				.getPropertyDetailsList(_propTxnNo,dbUtility);
	
		if (tmpPropDetList.size() > 0) {
			ArrayList tmpsubList = (ArrayList) tmpPropDetList.get(0);
			if (tmpsubList != null) {
				RegCompletDTO dto = new RegCompletDTO();
				dto.setDistName((String) tmpsubList.get(0));
				dto.setTehsilName((String) tmpsubList.get(1));
				dto.setAreaTypeName((String) tmpsubList.get(4));
				dto.setGovmunciplName((String) tmpsubList.get(5));
				dto.setWardpatwarName((String) tmpsubList.get(2));
				dto.setMohallaName((String) tmpsubList.get(3));
				dto.setDistId((String) tmpsubList.get(1) + "~"
						+ (String) tmpsubList.get(0));
				dto.setTehsilId((String) tmpsubList.get(3) + "~"
						+ (String) tmpsubList.get(2));
				dto.setWardpatwarId((String) tmpsubList.get(5) + "~"
						+ (String) tmpsubList.get(4));
				dto.setMohallaId((String) tmpsubList.get(7) + "~"
						+ (String) tmpsubList.get(6));
				dto.setAreaTypeId((String) tmpsubList.get(9) + "~"
						+ (String) tmpsubList.get(8));
				dto.setGovmunciplId((String) tmpsubList.get(11) + "~"
						+ (String) tmpsubList.get(10));
				dto.setVikasId((String) tmpsubList.get(12));
				dto.setRicircle((String) tmpsubList.get(13));
				dto.setLayoutdet((String) tmpsubList.get(14));
				dto.setKhasaraNum((String) tmpsubList.get(15));
				dto.setNazoolstNum((String) tmpsubList.get(16));
				dto.setRinpustikaNum((String) tmpsubList.get(17));
				dto.setAddress((String) tmpsubList.get(18));
				dto.setEast((String) tmpsubList.get(19));
				dto.setWest((String) tmpsubList.get(20));
				dto.setNorth((String) tmpsubList.get(21));
				dto.setSouth((String) tmpsubList.get(22));
				dto.setPropertyTypeId((String) tmpsubList.get(22) + "~"
						+ (String) tmpsubList.get(22));
				dto.setPropertyTypeName((String) tmpsubList.get(6));
				dto.setPropertyUsageTypeName((String) tmpsubList.get(23));
				dto.setPropertyUsageTypeId((String) tmpsubList.get(24)
						+ "~" + (String) tmpsubList.get(23));
				dto.setTotalArea((String) tmpsubList.get(27));
				ArrayList tmpPropValueList = regCompDAO
						.getPropertyValueDetailsList(_propTxnNo);
				String selectedFloors="";
				if (tmpPropValueList.size() > 0) {
					String floorTxnId = "";
					// logger.debug(tmpPropValueList.get(0));
					HashMap floorDetailsList = new HashMap();
					logger.debug("property value size--<>"
							+ tmpPropValueList.size());
					for (int kk = 0; kk < tmpPropValueList.size(); kk++) {
						String marketVal = "";
						String considerAmt = "";
						RegCompletDTO floorDto = new RegCompletDTO();
						ArrayList tmpValueList = (ArrayList) tmpPropValueList
								.get(kk);
						if (tmpValueList!=null && 
								tmpValueList.size() > 0) {
							marketVal = tmpValueList.get(0).toString();
							considerAmt = tmpValueList.get(1).toString();
							floorTxnId = tmpValueList.get(2).toString();
							logger.debug(floorTxnId);
							floorDto.setMarketValue(marketVal);
							floorDto.setConsidearAmt(considerAmt);
							dto.setMarketValue(marketVal);
							dto.setConsidearAmt(considerAmt);
							dto.setFloorDesc(floorTxnId);
							if (floorTxnId != null
									&& !floorTxnId.equals("")) {
	
								ArrayList floorDetList = regCompDAO
										.getPropertyFloorDetailsList(
												_propTxnNo, floorTxnId,
												dbUtility);
								if (floorDetList.size() > 0) {
	
									ArrayList floorList = (ArrayList) floorDetList
											.get(0);
									floorDto
											.setFloorNo((String) floorList.get(0)+"~"+floorList.get(1)+"~"+floorList.get(2));
									selectedFloors=selectedFloors+","+floorDto.getFloorNo();
									floorDto
											.setFloorName((String) floorList
													.get(1));
									floorDto
											.setFloorDesc((String) floorList
													.get(2));
									floorDto
											.setTotalArea((String) floorList
													.get(3));
									floorDto
											.setConstructedArea((String) floorList
													.get(4));
									floorDto.setFloorTxnId((String) floorList
											.get(7));
									floorDto
											.setPropertyTypeId((String) tmpsubList
													.get(22)
													+ "~"
													+ (String) tmpsubList
															.get(22));
									floorDto
											.setPropertyUsageTypeName((String) floorList
													.get(6)+"~"+floorList
													.get(5));
									floorDto
											.setPropertyUsageTypeId((String) floorList
													.get(5));
									floorDto
											.setCommercialType((String) floorList
													.get(7));
									floorDto
											.setCommercialTypeId((String) floorList
													.get(7)
													+ "~"
													+ floorList.get(7)
													+ "~"
													+ floorList.get(7));
									floorDto.setDistId((String) tmpsubList
											.get(1)
											+ "~"
											+ (String) tmpsubList.get(0));
									floorDto
											.setTehsilId((String) tmpsubList
													.get(3)
													+ "~"
													+ (String) tmpsubList
															.get(2));
									floorDto
											.setWardpatwarId((String) tmpsubList
													.get(5)
													+ "~"
													+ (String) tmpsubList
															.get(4));
									floorDto
											.setMohallaId((String) tmpsubList
													.get(7)
													+ "~"
													+ (String) tmpsubList
															.get(6));
									floorDto
											.setAreaTypeId((String) tmpsubList
													.get(9)
													+ "~"
													+ (String) tmpsubList
															.get(8));
									floorDto
											.setGovmunciplId((String) tmpsubList
													.get(11)
													+ "~"
													+ (String) tmpsubList
															.get(10));
									floorDto.setTypeFloorName(
											(String) floorList
													.get(0)
											+ "~"
											+ (String) floorList.get(1)
											+ "~"
											+ (String) floorList.get(2));
									//int floorNo = Integer.parseInt((String) floorList.get(0)); //ramesh commented on 05 dec 5
	
								}
								ArrayList tmpSubclauseList = regCompDAO
										.getPropertySubclauseList(
												floorTxnId,
												dbUtility);
	
								if (tmpSubclauseList.size() > 0) {
									String subString = "";
									ArrayList subList = new ArrayList();
									for (int i = 0; i < tmpSubclauseList
											.size(); i++) {
										ArrayList tmpsubclList = (ArrayList) tmpSubclauseList
												.get(i);
										
											subString = subString + "*"
													+ tmpsubclList.get(1)
													+ "~"
													+ tmpsubclList.get(0);
											subList.add(tmpsubclList.get(0));
	
									}
									System.out
											.println("sub clause String--<><> "
													+ subString);
									floorDto.setSubclauseString(subString);
									System.out
									.println("sub clause subList--<><> "
											+ subList);
									floorDto.setFloorList(subList);
									
								}
								floorDetailsList.put(floorDto.getFloorNo(),
										floorDto);
							} else {
								ArrayList tmpSubclauseList = regCompDAO
										.getPropertySubclauseList(
												_propTxnNo, null);
	
								if (tmpSubclauseList.size() > 0) {
									ArrayList subList = new ArrayList();
									String subString = "";
									for (int i = 0; i < tmpSubclauseList
											.size(); i++) {
	
										ArrayList tmpsubclList = (ArrayList) tmpSubclauseList
												.get(i);
										if (!subString.equals("")) {
											subString = subString + "*"
													+ tmpsubclList.get(1)
													+ "~"
													+ tmpsubclList.get(0);
										} else {
											subString = tmpsubclList.get(1)
													+ "~"
													+ tmpsubclList.get(0);
										}
										subList.add(tmpsubclList.get(0));
	
									}
									logger.debug("sub clause String--<><> "
													+ subString);
									dto.setFloorList(subList);
									dto.setSubclauseString(subString);
								}
							}
						}
	
					}
					dto.setSelectedFloor(selectedFloors);
					dto.setFloorValuesList(floorDetailsList);
					logger.debug(dto.getFloorValuesList());
				     logger.debug("--selected floors-->"+dto.getSelectedFloor());
				     returnList.add(dto);
				}
				logger.debug(dto.getFloorValuesList());
			
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally{
		dbUtility.closeConnection();
	}
	logger.debug("in action"+((RegCompletDTO)returnList.get(0)).getFloorList());
	return returnList;

}

	public boolean insSpReqDet(
						CommonDTO dto,String roleId,
						String funId,String userId) throws Exception
	{
       boolean blnInsVal = false;
       IGRSCommon common = new IGRSCommon();
		String param[]=new String[8]; 
		 SpotInspBO objBo = new SpotInspBO();
		logger.info("  Enter in to the Wipro in Spot Inspection BD - insSpReqDet() ");
		
		String spotId = null;
		spotId = common.getSequenceNumber("IGRS_SPOT_INS_TXN_ID_SEQ", "SpotTxn");
		dto.setSpotInspTxnId(spotId);
		param[0]= dto.getRegNo();
		param[1]= dto.getPropId();
		param[2]= dto.getUserId();
		param[3]= dto.getUserId();
		param[4]= dto.getRemarks();
		param[5]= dto.getSpotInspTxnId();
		param[6]= dto.getSiReason();
		param[7]= "Spot Inspection Request";

		try {
			blnInsVal  = objBo.insSpReqDet(param,roleId,funId,userId);
		} catch (Exception e) {
			logger.info("this is Exception in Spot Inspection BD - insSpReqDet()"+ e);
		}
		return blnInsVal;
	}

	public RegCompletDTO getSelectedPropertyDetails(String _propTxnNo)
						throws Exception {
			
		 
		RegCompletDTO rDTO = new RegCompletDTO();

		logger.debug("in bd getSelectedPropertyDetails " + _propTxnNo);
		DBUtility dbUtil = new DBUtility();
		try {

				
			ArrayList tmpPropDetList = regCompDAO
				.getPropertyDetailsList(_propTxnNo,dbUtil);

			if (tmpPropDetList!=null && tmpPropDetList.size() > 0) {
				ArrayList tmpsubList = (ArrayList) tmpPropDetList.get(0);
				PropertiesFileReader pr = 
			    	PropertiesFileReader.
			    	 getInstance("com.wipro.igrs.propertyvaluation");
				
				if (tmpsubList != null) {
					
					//RegCompletDTO dto = new RegCompletDTO();
					String distName = (String) tmpsubList.get(0);
					rDTO.setDistName(distName);
					
					rDTO.setTehsilName((String) tmpsubList.get(1));
					rDTO.setWardpatwarName((String) tmpsubList.get(2));
					rDTO.setMohallaName((String) tmpsubList.get(3));
					String areaName = (String) tmpsubList.get(4);
					rDTO.setAreaTypeName(areaName);
					String govName = (String) tmpsubList.get(5);
					rDTO.setGovmunciplName(govName);
					rDTO.setPropertyTypeName(
						(String) tmpsubList.get(6));
					rDTO.setPropertyUsageTypeName(
						(String) tmpsubList.get(7));
					rDTO.setPropertyL2((String) tmpsubList.get(8));
					 
					rDTO.setVikasId((String) tmpsubList.get(9));
					rDTO.setRicircle((String) tmpsubList.get(10));
					rDTO.setLayoutdet((String) tmpsubList.get(11));
					rDTO.setKhasaraNum((String) tmpsubList.get(12));
					rDTO.setNazoolstNum((String) tmpsubList.get(13));
					rDTO.setRinpustikaNum((String) tmpsubList.get(14));
					rDTO.setAddress((String) tmpsubList.get(15));
					rDTO.setEast((String) tmpsubList.get(16));
					rDTO.setWest((String) tmpsubList.get(17));
					rDTO.setNorth((String) tmpsubList.get(18));
					rDTO.setSouth((String) tmpsubList.get(19));
					
					String totalArea = (String) tmpsubList.get(20);
					String propertyTypeID = (String) tmpsubList.get(22);
					rDTO.setDistId((String) tmpsubList.get(23)
									+"~"+distName);
					// Edited By Aruna
					/*rDTO.setAreaTypeId((String) tmpsubList.get(24)
									+"~"+areaName);
					rDTO.setGovmunciplId((String) tmpsubList.get(25)
									+"~"+govName);*/
					
					rDTO.setAreaTypeId((String) tmpsubList.get(25)
							+"~"+areaName);
					rDTO.setGovmunciplId((String) tmpsubList.get(24)
									+"~"+govName);
						
					rDTO.setPropertyTxnNo((String) tmpsubList.get(21));
					rDTO.setPropertyTypeId(propertyTypeID+"~"
							+rDTO.getPropertyTypeName());
					
					logger.debug("setPropertyTypeId:-"
							+(String) tmpsubList.get(22));
					
					// Added By Aruna
					rDTO.setTehsilId((String) tmpsubList.get(26)+"~"+((String) tmpsubList.get(1)));
					rDTO.setWardpatwarId((String) tmpsubList.get(27));
					rDTO.setWardStatus((String) tmpsubList.get(27));					
					rDTO.setWardId((String) tmpsubList.get(28)+"~"+((String) tmpsubList.get(2)));
					rDTO.setMohallaId((String) tmpsubList.get(29)+"~"+((String) tmpsubList.get(3)));
					

					if(!pr.getValue(RegCompConstant.BUILDING_ID).
					      equals(propertyTypeID)) {
							
						rDTO.setTotalArea(totalArea);
					}
						
					ArrayList tmpPropValueList = regCompDAO
							.getFloorTxnID(_propTxnNo,dbUtil);

					if(pr.getValue(RegCompConstant.BUILDING_ID).
						      equals(propertyTypeID)) {
						
						HashMap map = new HashMap();
						
						// For Building
						
						if(tmpPropValueList != null) {
							for (int kk = 0; 
										kk < tmpPropValueList.size();
										kk++) {
								ArrayList tmpValueList = 
									(ArrayList) tmpPropValueList.get(kk);
								// Edited By Aruna
								//if(tmpPropValueList !=null){
								if(tmpValueList !=null){	 
									logger.debug("tmpValueList.get(0):-"
											+tmpValueList.get(0));
									rDTO.setFloorTxnId(
										(String)tmpValueList.get(0));
										ArrayList floorDtl = regCompDAO
											.getPropertyFloorDetailsList(
													_propTxnNo, 
													rDTO.getFloorTxnId(),
													dbUtil);
									if(floorDtl !=null) {
										for(int i =0;
											i<floorDtl.size();
											i++) {
												
											ArrayList listFloor = 
													(ArrayList) 
														floorDtl.get(i);
												
											RegCompletDTO dto = 
												new RegCompletDTO();
											dto.setFloorTxnId(
												(String)listFloor.get(0));
											
											dto.setFloorName(
												(String)listFloor.get(1));
											dto.setTotalArea(
												(String)listFloor.get(2));
											dto.setConstructedArea(
												(String)listFloor.get(3));
											dto.setUsageBuilding(
													(String)listFloor.get(4));
											dto.setPropertyL2(
													(String)listFloor.get(5));
											dto.setConsiderAmt(
												listFloor.get(7)!=null?
												Double.parseDouble(
												(String)listFloor.get(7)) 
												: 0.0);
											dto.setMarketValue(
												(String)listFloor.get(6));
											
											ArrayList subclause = regCompDAO.
												getFloorSubclauseList(
														_propTxnNo, 
														dto.getFloorTxnId(), 
														dbUtil); 
												
											if(subclause !=null) {
												ArrayList list = 
													new ArrayList();
												for(int j = 0; 
													j<subclause.size();
													j++){
														
													ArrayList subclauseList 
														= (ArrayList) 
													subclause.get(i);
													RegCompletDTO sDTO = 
														 new RegCompletDTO();
													   
													sDTO.setSubclauseName(
														(String)
														subclauseList.get(0));
						logger.debug("subclauseList.get(0):-"+subclauseList.get(0));
													list.add(sDTO);
														
												}
												dto.
													setPropertySubclauseList
													(list);
											}
												
											map.put(rDTO.getFloorTxnId(),dto);
										}
									}
								}
							
							}
								
						}
						rDTO.setMapBuilding(map);
						//End Building
							
							
					}else {
							
						// For Plot & Agri
						if(tmpPropValueList != null) {
							for (int kk = 0; 
									kk < tmpPropValueList.size();
									kk++) {
									 
								ArrayList tmpValueList = 
								(ArrayList) tmpPropValueList.get(kk);
								// Edited By Aruna
								/*if(tmpPropValueList !=null){
									rDTO.setMarketValue(
									(String)tmpValueList.get(0));
									rDTO.setConsiderAmt(
											tmpValueList.get(1) != null ?
											Double.parseDouble(
											(String)tmpValueList.get(1)) 
											: 0.0);
								}*/
								if(tmpValueList !=null){
									rDTO.setMarketValue(
									(String)tmpValueList.get(1));
									rDTO.setConsiderAmt(
											tmpValueList.get(2) != null ?
											Double.parseDouble(
											(String)tmpValueList.get(2)) 
											: 0.0);
								}
							}
						}
						ArrayList tmpSubclause = regCompDAO
							.getPropertySubclauseList(_propTxnNo,dbUtil);
						if(tmpSubclause !=null){
							ArrayList list = new ArrayList();
							for(int i = 0; i<tmpSubclause.size();i++){
								ArrayList subclauseList = 
									(ArrayList) tmpSubclause.get(i);
								RegCompletDTO dto = new RegCompletDTO();
								dto.setSubclauseName(
									(String)subclauseList.get(0));
								list.add(dto);
							}
							rDTO.setPropertySubclauseList(list);
						}
						//End Plot & Agri
					}
						
				}		
	 
				// returnList.add(dto);
				// }
			}
		} catch (Exception e) {
			logger.debug(e) ;
		}finally {
			dbUtil.closeConnection();
		}
		
		return rDTO;

	}
	public boolean deletePropertyDetails(String _propTxnNo,
			String propertyType) throws Exception {
			boolean flg = false;
			
			DBUtility dbUtil = new DBUtility();
			try {
				String propertyID = regCompDAO.getPropertyTypeID(
									_propTxnNo, dbUtil);
				
				PropertyValuationBO bo = new PropertyValuationBO();
				
				if(bo.getPropertyID(RegCompConstant.BUILDING_ID).
						equals(propertyID)) {
					propertyType = RegCompConstant.PROPERTY_TYPE_BUILDING;
				}
				if(bo.getPropertyID(RegCompConstant.PLOT_ID).
						equals(propertyID)) {
					propertyType = RegCompConstant.PROPERTY_TYPE_PLOT;
				}
				if(bo.getPropertyID(RegCompConstant.AGRICULTURELAND_ID).
						equals(propertyID)) {
					propertyType = RegCompConstant.PROPERTY_TYPE_AGRI_LAND;
				}
				flg = regCompDAO.deletePropertyDetails(
							_propTxnNo,
							propertyType,dbUtil);
				if(flg) {
					dbUtil.commit();
				}else{
					dbUtil.rollback();
				}
			} catch (Exception e) {
				logger.error(e);

			}finally {
				dbUtil.closeConnection();
			}
			return flg;
		}
	public boolean updatePropertyDetails(
				RegCompletDTO _searchDTO, RegCompletDTO _completeDTO)
				throws Exception {
			boolean flg = false;
			try {
				logger.debug("in bd hash map size--<>"+_completeDTO.getMapBuilding().size());
				logger.debug("in bd hash map size--<>"+_completeDTO.getSelectedFloor());
				// String[] _propDetailsArr=new String[22];
				String[] propDetails = new String[22];// 25
				String[] propSubclausese = new String[2];
				String[] floorWise = new String[7];
				String[] propValues = new String[3];
				String[] propUOM = new String[4];
				String[] propFloor = new String[7];

				String[] propId = _searchDTO.getPropertyTypeId().split("~");
				String[] distId = _searchDTO.getDistId().split("~");
				String[] tehsilId = _searchDTO.getTehsilId().split("~");
				String[] wardId = _searchDTO.getWardId().split("~");
				String[] mohallaId = _searchDTO.getMohallaId().split("~");
				String[] areaId = _searchDTO.getAreaTypeId().split("~");
				String[] munipaliId = _searchDTO.getGovmunciplId().split("~");
				String[] proUseId = _completeDTO.getPropertyUsageTypeId().split("~");
				propDetails[0] = distId[0];
				propDetails[1] = tehsilId[0];
				propDetails[2] = wardId[0];
				propDetails[3] = mohallaId[0];
				propDetails[4] = areaId[0];

				propDetails[5] = munipaliId[0];
				propDetails[6] = _searchDTO.getVikasId();
				propDetails[7] = _searchDTO.getRicircle();
				propDetails[8] = _searchDTO.getLayoutdet();
				propDetails[9] = _searchDTO.getKhasaraNum();
				propDetails[10] = _searchDTO.getNazoolstNum();

				propDetails[11] = _searchDTO.getRinpustikaNum();
				propDetails[12] = _searchDTO.getAddress();
				propDetails[13] = _searchDTO.getEast();
				propDetails[14] = _searchDTO.getWest();
				propDetails[15] = _searchDTO.getNorth();
				propDetails[16] = _searchDTO.getSouth();
				propDetails[17] = proUseId[0];
				propDetails[18] = "";// pro type 2 id celing type
				propDetails[19] = _completeDTO.getTotalArea();
				propDetails[20] = _completeDTO.getPropertyTxnNo();
				propDetails[21] = _completeDTO.getRegNo();

				propValues[0] = _completeDTO.getPropertyTxnNo();
				// propValues[1]="";
				propValues[1] = _completeDTO.getMarketValue();
				propValues[2] = _completeDTO.getConsidearAmt();
				flg = regCompDAO.updatePropertyDetails(propDetails);
				logger.debug(" update of prop details--<><>" +flg);
				if (flg) {			
					//map start
					if(_completeDTO.getMapBuilding().size()>0){
					   HashMap floorMap=_completeDTO.getMapBuilding();
						String floorString = _completeDTO.getSelectedFloor();
						StringTokenizer subfloorstoken = new StringTokenizer(
								floorString, ",");
						while (subfloorstoken.hasMoreTokens()) {				
							floorString=subfloorstoken.nextToken();
							RegCompletDTO floorDto=(RegCompletDTO)floorMap.get(floorString);
							logger.debug("--floor no--<>"+floorDto.getFloorNo());
						//	MARKET_VALUE=?,CONSIDERATION_VALUE=?PROPERTY_TXN_ID=? AND FLOOR_TXN_ID=?
							propValues = new String[4];
							propValues[0]=floorDto.getMarketValue();
							propValues[1] = floorDto.getConsidearAmt();
							propValues[2] = _completeDTO.getPropertyTxnNo();
							propValues[3] = floorDto.getFloorTxnId();
							
							logger
							.debug("floorDto.getFloorNo()--<>"
									+ floorDto.getFloorNo());
					String floorNo[] = floorDto.getFloorNo()
							.split("~");
					       proUseId = floorDto.getPropertyUsageTypeId().split("~");
					// proPurposeOfUseId=floorDto.getPropertyUsageTypeId().split("~");
					   	logger
						.debug("floorDto.getPropertyUsageTypeId()--<>"
								+ floorDto.getPropertyUsageTypeId().split("~"));
					logger
							.debug("floorDto.getCeilingTypeId()--<>"
									+ floorDto
											.getCeilingTypeId());
					logger
							.debug("floorDto.getCommercialType()--<>"
									+ floorDto
											.getCommercialTypeId());
					String[] proPurposeOfUseId=null;
					if (floorDto.getCeilingTypeId() != null
							&& !floorDto.getCeilingTypeId()
									.equals("Select")) {
						proPurposeOfUseId = floorDto
								.getCeilingTypeId().split("~");
					} else if (floorDto.getCommercialTypeId()!= null
							&& !floorDto.getCommercialTypeId()
									.equals("Select")) {
						proPurposeOfUseId = _searchDTO
								.getCommercialTypeId().split("~");
					} else {
						proPurposeOfUseId[0] = "";
							
					}		
							
							boolean flgv = regCompDAO
							.updatePropertyFloorValueDetails(propValues);
					        logger.debug(" update of prop value --<>" + flgv);
		           // FLOOR_ID=?,TOTAL_AREA=?,CONSTRUCTED_AREA=?,PROPERTY_L1_ID=?,PROPERTY_L2_ID=? 
				   // PROPERTY_TXN_ID=? AND FLOOR_TXN_ID=?					
					       String[] floorValues = new String[7];
					       floorValues[0]=floorNo[0];
				    	   floorValues[1]=floorDto.getTotalArea();
			    		   floorValues[2]=floorDto.getConstructedArea();
		    			   floorValues[3]=proUseId[0];					
	    				   floorValues[4]=proPurposeOfUseId[0];
						   floorValues[5]= _completeDTO.getPropertyTxnNo();	
						   floorValues[6]=floorDto.getFloorTxnId();	
						  	
						    flgv = regCompDAO
							.updatePropertyFloorDetails(floorValues);
						    if(flgv){
						    	String subclauseStr = floorDto.getSubclauseString();
						    	logger.debug("floorDto.getSubclauseString()--<>"+floorDto.getSubclauseString());
								
						    	StringTokenizer substoken = new StringTokenizer(
										subclauseStr, ",");
								while (substoken.hasMoreTokens()) {

									subclauseStr = substoken.nextToken();
									String subclauseArray[] = subclauseStr.split("~");
									logger.debug("--subclauseStr---<><>"
											+ subclauseArray[0]);
									// PROPERTY_TXN_ID,SUBCLAUSE_ID,FLOOR_TXN_ID
									propSubclausese = new String[3];
									propSubclausese[1] = _completeDTO.getPropertyTxnNo();
									propSubclausese[0] = subclauseArray[0];							
									propSubclausese[2]=floorDto.getFloorTxnId();								

									flg = regCompDAO
											.updatePropertyFloorSubclauseDetails(propSubclausese);
									logger.debug(" update of subclause --<>" + flg);
								}// end while
						    	
						    }
						
						}		
					
					}else{//map last
					boolean flgv = regCompDAO
							.updatePropertyValueDetails(propValues);
					logger.debug(" update of prop value --<>" + flgv);
					if (flg) {
						// regCompDAO.updatePropertySubclauseDetails(_propDetails);
						String subclauseStr = _completeDTO.getSubclauseString();
						StringTokenizer substoken = new StringTokenizer(
								subclauseStr, ",");
						while (substoken.hasMoreTokens()) {

							subclauseStr = substoken.nextToken();
							String subclauseArray[] = subclauseStr.split("~");
							logger.debug("--subclauseStr---<><>"
									+ subclauseArray[0]);
							// PROPERTY_TXN_ID,SUBCLAUSE_ID,FLOOR_TXN_ID
							propSubclausese[1] = _completeDTO.getPropertyTxnNo();
							propSubclausese[0] = subclauseArray[0];
							// propSubclausese[2]="";
							// if(!floorTxnId.equals("")){
							// propSubclausese[3]=floorTxnId;
							// }else{
							// propSubclausese[3]="";
							// }

							flg = regCompDAO
									.updatePropertySubclauseDetails(propSubclausese);
							logger.debug(" update of subclause --<>" + flg);
						}// end while
					}
					}
				}
				// eletePropertyDetails(_propDetailsArr);

			} catch (Exception e) {
				logger.error(e);

			}
			return flg;

		}
		
	public String validateRegComplAdjudicationId(String _adjudiTxnId)throws Exception{
			
			String propertyTxnId="";
			try{
				
				propertyTxnId=regCompDAO.validateRegComplAdjudicationId(_adjudiTxnId);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			return propertyTxnId;	
			
		}

	public boolean validateRegComplPoaTxnId(String _poaTxnId)throws Exception{	
		boolean flg=false;
		try{
			
			_poaTxnId=regCompDAO.validateRegComplPoaTxnId(_poaTxnId);
			if(_poaTxnId!=null && !_poaTxnId.equals("") ){
				
				flg=true;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flg;	
		
	}
	public String updatePropertyDetails(
			RegCompletDTO regDTO,
			PropertyValuationDTO propertyDTO ) throws Exception{
		boolean status = false;
		String propTxnNo = regDTO.getPropertyTxnNo();
	 
		DBUtility dbUtil =  new DBUtility();
		try {
			IGRSCommon common = new IGRSCommon();
			//propTxnNo = common.getSequenceNumber("dummy", "PROP");
			
			String[] propDetails = new String[24];
			String propertyType = propertyDTO.getPropertyTypeID();
			propDetails[0] = propertyType;
			propDetails[1] = propertyDTO.getDistrictID();
			propDetails[2] = propertyDTO.getTehsilID();
			propDetails[3] = propertyDTO.getWardId();
			propDetails[4] = propertyDTO.getMahallaId();
			propDetails[5] = propertyDTO.getAreaId();
			propDetails[6] = propertyDTO.getMunicipalBodyID();
			propDetails[7] = regDTO.getVikasId();
			propDetails[8] = regDTO.getRicircle();
			propDetails[9] = regDTO.getLayoutdet();
			propDetails[10] = regDTO.getKhasaraNum();
			propDetails[11] = regDTO.getNazoolstNum();
			propDetails[12] = regDTO.getRinpustikaNum();
			propDetails[13] = regDTO.getAddress();
			propDetails[14] = regDTO.getEast();
			propDetails[15] = regDTO.getWest();
			propDetails[16] = regDTO.getNorth();
			propDetails[17] = regDTO.getSouth();
			propDetails[18] = propTxnNo;// pro txn id
			propDetails[19] = "1";
			propDetails[20] = propertyDTO.getUsePlotId();
			
			String ceiling = propertyDTO.getCeilingTypeId();
			String ceilingId = "";
			if(ceiling !=null) {
				String[] cellingary = ceiling.split("~");
				ceilingId = cellingary[0];
			}
			propDetails[21] = ceilingId;
			propDetails[22] = String.valueOf(propertyDTO.getTotalSqMeter());
			propDetails[23] = regDTO.getOldRegNo();
			
			for(int i=0; i<24; i++) {
				logger.debug("update propertyDetails["+i+"]="+propDetails[i]);
			}
			
			HashMap map = regDTO.getMapBuilding();
			
			Iterator It = map.entrySet().iterator();
			 
			status = regCompDAO.storePropertyDetails(propDetails, dbUtil);
			
			
			
			PropertyValuationBO bo = new PropertyValuationBO();
			String buildingID =  bo.getPropertyID(
						RegCompConstant.BUILDING_ID);  
			
			logger.debug("propertyType:-"+propertyType+":"+buildingID);
			
			//if(status) {
				
				if(buildingID.equals(propertyType)) {
					//Insert Building Details
					
					logger.debug("It.hasNext():-"+It.hasNext());
					while(It.hasNext()) {
						Map.Entry me = (Map.Entry) It.next();
						logger.debug("Inside Key :-"
								+me.getKey());
						String floorTxnId = common.getSequenceNumber("dummy",
							"FLOOR");
						
						 PropertyValuationDTO dtoMap 
						 	= (PropertyValuationDTO) me.getValue();
						 
						 
						 logger.debug("Floor Txn ID:-"+floorTxnId);
						 logger.debug("Property Txn ID:-"+propTxnNo);
						 logger.debug("Floor ID:-"+dtoMap.getFloorID());
						 logger.debug("Total Area:-"
								 	+dtoMap.getTotalSqMeter());
						 logger.debug("Constructed Area:-"
								 	+dtoMap.getConstructedArea());
						 logger.debug("Property L1 ID:-"
								 	+dtoMap.getPropertyTypeID());
						 
						  
						 
						 logger.debug("Property L2 ID:-"
								 	+ ceilingId+":"+ceiling);
						 String[]  floorDetails = new String[7];
						 floorDetails[0] = floorTxnId;
						 floorDetails[1] = propTxnNo;
						 floorDetails[2] = dtoMap.getFloorID();
						 floorDetails[3] = String.valueOf(
								 			dtoMap.getTotalSqMeter());
						 floorDetails[4] = String.valueOf(
								 			dtoMap.getConstructedArea());
						 floorDetails[5] = dtoMap.getPropertyTypeID();
						 floorDetails[6] = ceilingId;
	
						 status = regCompDAO.storePropertyFloorDetails(
								 			floorDetails, dbUtil);
						 String subclauseStr = dtoMap.getHdnExtSubClause();
						 logger.debug("Building subclause:-"+subclauseStr);
						 
						 
						 if (status) {
								
							    String[] _propValues = new String[4];
							    _propValues[0] = propTxnNo;
							    _propValues[1] = floorTxnId;
							    _propValues[2] = String.valueOf(
							    			dtoMap.getMarketValue());
							    _propValues[3] = String.valueOf(
						    			dtoMap.getConsiderAmt());
							    
							 	status = regCompDAO.storePropertyValueDetails(
							 				_propValues, dbUtil);
							 	if(status) {
									String[] propSubclausese = new String[4];
									
									StringTokenizer substoken = new StringTokenizer(
											subclauseStr, ",");
									while (substoken.hasMoreTokens()) {
				 
										
										propSubclausese[0] = propTxnNo;
										propSubclausese[1] = substoken.nextToken();
										propSubclausese[2] = "0";
										 
										propSubclausese[3] = floorTxnId;
										logger.debug("propSubclausese[1]="
												+propSubclausese[1]);
										
										status = regCompDAO
												.storePropertySubclauseDetails(
														propSubclausese,dbUtil);
				
									}// end while
							 	}
							}// end of if
						 
						 
						 
						 
					}
					
					
				}
	
				if(!buildingID.equals(propertyType)) {
					//Insert Plot & Agri Details
					
					String[] plotAgriDetails = new String[4];
					plotAgriDetails[0] = propTxnNo;
					plotAgriDetails[1] = "";
					plotAgriDetails[2] = String.valueOf(
										 propertyDTO.getMarketValue());
					plotAgriDetails[3] = String.valueOf(
										 propertyDTO.getConsiderAmt());
					for(int i = 0;i<plotAgriDetails.length;i++){
						logger.debug("plotAgriDetails["+i+"]="
									+plotAgriDetails[i]);
					}
					
					status = regCompDAO.storePropertyValueDetails(
									plotAgriDetails, dbUtil);
					String[] propSubclausese = new String[4];
					
					logger.debug("propertyDTO.getHdnExtSubClause():-"
							+propertyDTO.getHdnExtSubClause());	
					if (status) {
						String subclauseStr = propertyDTO.getHdnExtSubClause();
							
						
						
						StringTokenizer substoken = new StringTokenizer(
								subclauseStr, ",");
						while (substoken.hasMoreTokens()) {
	 
							
							propSubclausese[0] = propTxnNo;
							propSubclausese[1] = substoken.nextToken();
							propSubclausese[2] = "0";
							 
							propSubclausese[3] = "";
							logger.debug("propSubclausese[1]="
									+propSubclausese[1]);
							
							status = regCompDAO
									.storePropertySubclauseDetails(
											propSubclausese,dbUtil);
	
						}// end while
					}// end of if
				}		
				
			//}
			
			if(status) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
		}catch (Exception x){
			logger.debug(x);
			dbUtil.rollback();
		}finally {
			dbUtil.closeConnection();
		}
	
		return propTxnNo;
	
	
	}

	
	public String storePropertyDetails(
				RegCompletDTO regDTO,
				PropertyValuationDTO propertyDTO ) throws Exception{
		boolean status = false;
		String propTxnNo = "";
		DBUtility dbUtil =  new DBUtility();
		try {
			IGRSCommon common = new IGRSCommon();
			propTxnNo = common.getSequenceNumber("dummy", "PROP");
			
			String[] propDetails = new String[24];
			String propertyType = propertyDTO.getPropertyTypeID();
			propDetails[0] = propertyType;
			propDetails[1] = propertyDTO.getDistrictID();
			propDetails[2] = propertyDTO.getTehsilID();
			propDetails[3] = propertyDTO.getWardId();
			propDetails[4] = propertyDTO.getMahallaId();
			propDetails[5] = propertyDTO.getAreaId();
			propDetails[6] = propertyDTO.getMunicipalBodyID();
			propDetails[7] = regDTO.getVikasId();
			propDetails[8] = regDTO.getRicircle();
			propDetails[9] = regDTO.getLayoutdet();
			propDetails[10] = regDTO.getKhasaraNum();
			propDetails[11] = regDTO.getNazoolstNum();
			propDetails[12] = regDTO.getRinpustikaNum();
			propDetails[13] = regDTO.getAddress();
			propDetails[14] = regDTO.getEast();
			propDetails[15] = regDTO.getWest();
			propDetails[16] = regDTO.getNorth();
			propDetails[17] = regDTO.getSouth();
			propDetails[18] = propTxnNo;// pro txn id
			propDetails[19] = "1";
			propDetails[20] = propertyDTO.getUsePlotId();
			
			String ceiling = propertyDTO.getCeilingTypeId();
			String ceilingId = "";
			if(ceiling !=null) {
				String[] cellingary = ceiling.split("~");
				ceilingId = cellingary[0];
			}
			propDetails[21] = ceilingId;
			propDetails[22] = String.valueOf(propertyDTO.getTotalSqMeter());
			propDetails[23] = regDTO.getOldRegNo();
			
			for(int i=0; i<24; i++) {
				logger.debug("propertyDetails["+i+"]="+propDetails[i]);
			}
			
			HashMap map = regDTO.getMapBuilding();
			
			Iterator It = map.entrySet().iterator();
			 
			status = regCompDAO.storePropertyDetails(propDetails, dbUtil);
			
			
			
			PropertyValuationBO bo = new PropertyValuationBO();
			String buildingID =  bo.getPropertyID(
						RegCompConstant.BUILDING_ID);  
			
			logger.debug("propertyType:-"+propertyType+":"+buildingID);
			
			//if(status) {
				
				if(buildingID.equals(propertyType)) {
					//Insert Building Details
					
					logger.debug("It.hasNext():-"+It.hasNext());
					while(It.hasNext()) {
						Map.Entry me = (Map.Entry) It.next();
						logger.debug("Inside Key :-"
								+me.getKey());
						String floorTxnId = common.getSequenceNumber("dummy",
							"FLOOR");
						
						 PropertyValuationDTO dtoMap 
						 	= (PropertyValuationDTO) me.getValue();
						 
						 
						 logger.debug("Floor Txn ID:-"+floorTxnId);
						 logger.debug("Property Txn ID:-"+propTxnNo);
						 logger.debug("Floor ID:-"+dtoMap.getFloorID());
						 logger.debug("Total Area:-"
								 	+dtoMap.getTotalSqMeter());
						 logger.debug("Constructed Area:-"
								 	+dtoMap.getConstructedArea());
						 logger.debug("Property L1 ID:-"
								 	+dtoMap.getPropertyTypeID());
						 
						  
						 
						 logger.debug("Property L2 ID:-"
								 	+ ceilingId+":"+ceiling);
						 String[]  floorDetails = new String[7];
						 floorDetails[0] = floorTxnId;
						 floorDetails[1] = propTxnNo;
						 floorDetails[2] = dtoMap.getFloorID();
						 floorDetails[3] = String.valueOf(
								 			dtoMap.getTotalSqMeter());
						 floorDetails[4] = String.valueOf(
								 			dtoMap.getConstructedArea());
						 floorDetails[5] = dtoMap.getPropertyTypeID();
						 floorDetails[6] = ceilingId;

						 status = regCompDAO.storePropertyFloorDetails(
								 			floorDetails, dbUtil);
						 String subclauseStr = dtoMap.getHdnExtSubClause();
						 logger.debug("Building subclause:-"+subclauseStr);
						 
						 
						 if (status) {
								
							    String[] _propValues = new String[4];
							    _propValues[0] = propTxnNo;
							    _propValues[1] = floorTxnId;
							    _propValues[2] = String.valueOf(
							    			dtoMap.getMarketValue());
							    _propValues[3] = String.valueOf(
						    			dtoMap.getConsiderAmt());
							    
							 	status = regCompDAO.storePropertyValueDetails(
							 				_propValues, dbUtil);
							 	if(status) {
									String[] propSubclausese = new String[4];
									
									StringTokenizer substoken = new StringTokenizer(
											subclauseStr, ",");
									while (substoken.hasMoreTokens()) {
				 
										
										propSubclausese[0] = propTxnNo;
										propSubclausese[1] = substoken.nextToken();
										propSubclausese[2] = "0";
										 
										propSubclausese[3] = floorTxnId;
										logger.debug("propSubclausese[1]="
												+propSubclausese[1]);
										
										status = regCompDAO
												.storePropertySubclauseDetails(
														propSubclausese,dbUtil);
				
									}// end while
							 	}
							}// end of if
						 
						 
						 
						 
					}
					
					
				}
	
				if(!buildingID.equals(propertyType)) {
					//Insert Plot & Agri Details
					
					String[] plotAgriDetails = new String[4];
					plotAgriDetails[0] = propTxnNo;
					plotAgriDetails[1] = "";
					plotAgriDetails[2] = String.valueOf(
										 propertyDTO.getMarketValue());
					plotAgriDetails[3] = String.valueOf(
										 propertyDTO.getConsiderAmt());
					for(int i = 0;i<plotAgriDetails.length;i++){
						logger.debug("plotAgriDetails["+i+"]="
									+plotAgriDetails[i]);
					}
					
					status = regCompDAO.storePropertyValueDetails(
									plotAgriDetails, dbUtil);
					String[] propSubclausese = new String[4];
					
					logger.debug("propertyDTO.getHdnExtSubClause():-"
							+propertyDTO.getHdnExtSubClause());	
					if (status) {
						String subclauseStr = propertyDTO.getHdnExtSubClause();
							
						
						
						StringTokenizer substoken = new StringTokenizer(
								subclauseStr, ",");
						while (substoken.hasMoreTokens()) {
	 
							
							propSubclausese[0] = propTxnNo;
							propSubclausese[1] = substoken.nextToken();
							propSubclausese[2] = "0";
							 
							propSubclausese[3] = "";
							logger.debug("propSubclausese[1]="
									+propSubclausese[1]);
							
							status = regCompDAO
									.storePropertySubclauseDetails(
											propSubclausese,dbUtil);
	
						}// end while
					}// end of if
				}		
				
			//}
			
			if(status) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
		}catch (Exception x){
			logger.debug(x);
			dbUtil.rollback();
		}finally {
			dbUtil.closeConnection();
		}
		
		return propTxnNo;
	}
	
	
	
	/*
	public String storePropertyDetails(RegCompletDTO _detailsDto,
			RegCompletDTO _searchDTO, RegCompletDTO _displayDTO)
			throws Exception {

		boolean status = false;
		String propTxnNo = "";
		DBUtility dbUtil =  new DBUtility();
		try {
			
			String[] propDetails = new String[24];// 25
			String[] propSubclausese = new String[4];
			String[] floorWise = new String[7];
			String[] propValues = new String[4];
			//String[] propUOM = new String[4];
			//String[] propFloor = new String[7];

			String[] propId = _searchDTO.getPropertyTypeId().split("~");
			String[] distId = _searchDTO.getDistId().split("~");
			String[] tehsilId = _searchDTO.getTehsilId().split("~");
			String[] wardId = _searchDTO.getWardId().split("~");
			String[] mohallaId = _searchDTO.getMohallaId().split("~");
			String[] areaId = _searchDTO.getAreaTypeId().split("~");
			String[] munipaliId = _searchDTO.getGovmunciplId().split("~");
			String[] proUseId = _searchDTO.getPropertyUsageTypeId().split("~");
			String[] proPurposeOfUseId = null;
			
			if (_searchDTO.getCeilingTypeId() != null
					&& !_searchDTO.getCeilingTypeId().equals("Select")) {
				proPurposeOfUseId = _searchDTO.getCeilingTypeId().split("~");
			} else if (_searchDTO.getCommercialType() != null
					&& !_searchDTO.getCommercialType().equals("Select")) {
				proPurposeOfUseId = _searchDTO.getCommercialType().split("~");
			} else if (proPurposeOfUseId != null) {
				proPurposeOfUseId[0] = "";
			} else {
				proPurposeOfUseId = new String[1];
				proPurposeOfUseId[0] = "";
			}

			propDetails[0] = propId[0];
			propDetails[1] = distId[0];
			propDetails[2] = tehsilId[0];
			propDetails[3] = wardId[0];
			propDetails[4] = mohallaId[0];
			propDetails[5] = areaId[0];

			propDetails[6] = munipaliId[0];
			propDetails[7] = _searchDTO.getVikasId();
			propDetails[8] = _searchDTO.getRicircle();
			propDetails[9] = _searchDTO.getLayoutdet();
			propDetails[10] = _searchDTO.getKhasaraNum();
			propDetails[11] = _searchDTO.getNazoolstNum();

			propDetails[12] = _searchDTO.getRinpustikaNum();
			propDetails[13] = _searchDTO.getAddress();
			propDetails[14] = _searchDTO.getEast();
			propDetails[15] = _searchDTO.getWest();
			propDetails[16] = _searchDTO.getNorth();
			propDetails[17] = _searchDTO.getSouth();

			propDetails[18] = "";// pro txn id
			propDetails[19] = "1";
			propDetails[20] = proUseId[0];
			propDetails[21] = proPurposeOfUseId[0];
			propDetails[22] = _displayDTO.getTotalArea();
			propDetails[23] = _displayDTO.getRegNo();
			// propDetails[23]="1";
			// propDetails[24]="MP123456";
			// TOTAL_AREA,DEED_TYPE_ID,REG_TXN_ID
			propValues[0] = "";
			propValues[1] = "";

			IGRSCommon common = new IGRSCommon();
			
			
			if (propTxnNo != null && propTxnNo != "") {

				propDetails[18] = propTxnNo;
				propValues[0] = propTxnNo;
				String floorTxnId = "";
				PropertyValuationBO bo = new PropertyValuationBO();
				String buildingID =  bo.getPropertyID(
							RegCompConstant.BUILDING_ID);
				
				if ((_displayDTO.getMapBuilding() != null && 
						_displayDTO.getMapBuilding().size() > 0)
						|| propId[0].equals(buildingID)) {
					// if(_displayDTO.getMapBuilding().size()==0){
					
					if(_displayDTO.getCommercialType().equals("Select")){
						
						_displayDTO.setCommercialType(_displayDTO.getCeilingTypeId());
						
					}
					
					
					_displayDTO.getMapBuilding().put(_displayDTO.getFloorNo(),
							_displayDTO);
					// }

					logger.debug(" in reg bd hash map uuuuuuuuuu moreFloors--<><>"
									+ _displayDTO.getMoreFloors());
					logger.debug(""+_displayDTO.getMapBuilding().size());
					HashMap map = _displayDTO.getMapBuilding();
					if (map.size() > 0) {
						status = regCompDAO.storePropertyDetails(propDetails,dbUtil);
					
							if (map != null) {
								if (map.size() > 0) {
									Map mapp=map;
									Set mapset=mapp.entrySet();
								//	Set entries = map.entrySet();
								    Iterator it = mapset.iterator();
								    while (it.hasNext()) {
								      Map.Entry entry = (Map.Entry) it.next();
								      logger.debug(entry.getKey() + "-->" + entry.getValue());				    
								      floorTxnId = common.getSequenceNumber("dummy",
										"FLOOR");		
									
									
									PropertyValuationDTO floorDto = (PropertyValuationDTO) map
											.get(entry.getKey());
									 proUseId = _searchDTO.getPropertyUsageTypeId().split("~");
									//floorDto.getPropertyUsageTypeId();
									// FLOOR_TXN_ID,PROPERTY_TXN_ID,FLOOR_ID,TOTAL_AREA,CONSTRUCTED_AREA,PROPERTY_L1_ID,PROPERTY_L2_ID
									logger.debug("floorDto.getFloorNo()--<>"
													+ floorDto.getFloorNo());
									String floorNo[] = floorDto.getFloorNo()
											.split("~");
									// proPurposeOfUseId=floorDto.getPropertyUsageTypeId().split("~");
									propValues[1] = floorTxnId;
									logger.debug("floorDto.getCeilingTypeId()--<>"
													+ floorDto
															.getCeilingTypeId());
									logger.debug("floorDto.getCommercialType()--<>"
													+ floorDto
															.getCommercialType());
									if (floorDto.getCeilingTypeId() != null
											&& !floorDto.getCeilingTypeId()
													.equals("Select")) {
										proPurposeOfUseId = floorDto
												.getCeilingTypeId().split("~");
									} else if (floorDto.getCommercialType() != null
											&& !floorDto.getCommercialType()
													.equals("Select")) {
										proPurposeOfUseId = _searchDTO
												.getCommercialType().split("~");
									} else {
										proPurposeOfUseId[0] = "";
									}
									propValues[2] = floorDto.getMarketValue();
									propValues[3] = floorDto.getConsidearAmt();
									floorWise[0] = floorTxnId;
									floorWise[1] = propTxnNo;
									floorWise[2] = floorNo[0];
									floorWise[3] = floorDto.getTotalArea();
									floorWise[4] = floorDto
											.getConstructedArea();
									floorWise[5] = proUseId[0];
									floorWise[6] = proPurposeOfUseId[0];

									propDetails[22] = "";

									status = regCompDAO
											.storePropertyFloorDetails(floorWise,dbUtil);
									status = regCompDAO
											.storePropertyValueDetails(propValues,dbUtil);

									logger.debug("getFloorNo--<>"
											+ floorNo[0]);
									logger.debug("getCommercialType--<>"
											+ proPurposeOfUseId[0]);
									logger.debug("getTotalArea--<>"
											+ floorDto.getTotalArea());
									logger.debug("getConstructedArea--<>"
											+ floorDto.getConstructedArea());
									logger.debug("getConsidearAmt--<>"
											+ floorDto.getConsidearAmt());
									logger.debug("getConsidearAmt--<>"
											+ floorDto.getSubclauseString());
									//
									String subclauseStr = floorDto
											.getSubclauseString();
									StringTokenizer substoken = new StringTokenizer(
											subclauseStr, ",");
									while (substoken.hasMoreTokens()) {

										subclauseStr = substoken.nextToken();
										String subclauseArray[] = subclauseStr
												.split("~");
										logger
												.debug("--subclauseStr---<><>"
														+ subclauseArray[0]);
										// PROPERTY_TXN_ID,SUBCLAUSE_ID,SUBCLAUSE_VALUE,FLOOR_TXN_ID
										propSubclausese[0] = propTxnNo;
										propSubclausese[1] = subclauseArray[0];
										propSubclausese[2] = "0";
										propSubclausese[3] = floorTxnId;

										status = regCompDAO
												.storePropertySubclauseDetails(propSubclausese,dbUtil);

									}

									//

									logger.debug(" end while loop ");
								}
							}
						}

					}
				}
				
				
				if (!propId[0].equals(buildingID)) {
					status = regCompDAO.storePropertyDetails(propDetails,dbUtil);
					if (status) {
						propValues[2] = _displayDTO.getMarketValue();
						propValues[3] = _displayDTO.getConsidearAmt();
						status = regCompDAO
								.storePropertyValueDetails(propValues,dbUtil);
						if (status) {
							String subclauseStr = _displayDTO
									.getSubclauseString();
							StringTokenizer substoken = new StringTokenizer(
									subclauseStr, ",");
							while (substoken.hasMoreTokens()) {

								subclauseStr = substoken.nextToken();
								String subclauseArray[] = subclauseStr
										.split("~");
								logger.debug("--subclauseStr---<><>"
										+ subclauseArray[0]);
								
								
								propSubclausese[0] = propTxnNo;
								propSubclausese[1] = subclauseArray[0];
								propSubclausese[2] = "0";
								if (!floorTxnId.equals("")) {
									propSubclausese[3] = floorTxnId;
								} else {
									propSubclausese[3] = "";
								}

								status = regCompDAO
										.storePropertySubclauseDetails(propSubclausese,dbUtil);

							}// end while
						}// end of if
					}
				}// end for property details status if
			}// end for if txn
			
			if(status) {
				dbUtil.commit();
			}else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
			dbUtil.rollback();
			
		} finally {

			if (dbUtil != null) {

				dbUtil.closeConnection();
			}

			 dbUtil = null;
		}


		return propTxnNo;

	}
*/
	/**
	 * store registration documents
	 * @param attachments
	 */
	public void storeRegDocuments(ArrayList attachments) {
		RegCompBO regCompBO = new RegCompBO();
		regCompBO.storeRegDocuments(attachments);
	}

	/**
	 * 
	 * @param regNo
	 * @param stampDuty
	 * @param regFee
	 * @param total
	 * @param payId
	 * @param userId
	 * @return
	 */
	public String insertRegDetails(String regNo, String stampDuty,
			String regFee, String total, String payId, String userId,String type) {
		RegCompBO regCompBO = new RegCompBO();
		String regId = null;
		//TRANSACTION_ID, REGISTRATION_ID,CREATED_BY, CREATED_DATE,REGISTRATION_STATUS, TOTAL_STAMP_DUTY,TOTAL_REG_FEE, PAYMENT_TXN_ID,TOTAL_REGISTRATION_FEE
		
		String params[] = new String[8];
		params[0] = "TRANSACTION_ID";
		params[1] = regNo;
		params[2] = userId;
		if(type.equalsIgnoreCase("spot"))
			params[3] = "REG_STAT_002";
		else
			params[3] = "REG_STAT_009";
		params[4] = stampDuty;
		params[5] = regFee;
		params[6] = payId;
		params[7] = total;
		regId = regCompBO.insertRegDetails(params);

		return regId;
	}
	
	/**
	 * getting stamp Duty paid value
	 * @param regNo
	 * @return stampDuty
	 */

	public String getDutyPaid(String regNo)
	{	
	
	String dutyPaid = null;
	RegCompBO regCompBO = new RegCompBO();
	ArrayList list = regCompBO.getDutyPaid(regNo);
	if(list!=null && list.size()>0)
	{
	ArrayList retList = (ArrayList)list.get(0);
	dutyPaid = (String)retList.get(0);
	}
		
		return  dutyPaid;
	}

	/**
	 * 
	 * @param regCompId
	 * @return
	 */
	public boolean updateRegDetails(String regCompId,String regStatus) {
		RegCompBO regCompBO = new RegCompBO();		
		return regCompBO.updateRegDetails(regCompId,regStatus);
	}
	
	public StringBuffer updateDeedRORDtls(ArrayList propDeedList) {
		RegCompBO regCompBO = new RegCompBO();		
		return regCompBO.updateDeedRORDtls(propDeedList);
	}
	
	public boolean insertOldAndNewRegIDS(String regCompId,String regStatus) {
		RegCompBO regCompBO = new RegCompBO();		
		return regCompBO.insertOldAndNewRegIDS(regCompId,regStatus);
	}
	
	public String getRegInitDate(String[] regCompId) {
		RegCompBO regCompBO = new RegCompBO();		
		return regCompBO.getRegInitDate(regCompId);
	}
	
	public String verifyPin(CommonDTO dtoMap) throws Exception {
		RegCompBO regCompBO = new RegCompBO();
		return regCompBO.verifyPin(dtoMap);
	}
	
	// Added By Aruna
	/**
	 * Save get Deed Details
	 * @param regNumber
	 * @return ArrayList
	 */
	public ArrayList getCompDeedList(String regNumber)
			throws Exception{
		return regCompDAO.getCompDeedList(regNumber);
	}
	
	/**
	 * Save get Complete Deed Details
	 * @param deedTxnID
	 * @return ArrayList
	 */
	public RegInitCompleteDTO getCompleteDeedDetails(String deedTxnID)
			throws Exception{
		return regCompDAO.getCompleteDeedList(deedTxnID);
	}
	 public boolean whetherToLoop(HashMap regDeedPropertyMap,ArrayList subList,int firstTime)
	 {
		 boolean doLoop=false;
		 String regID="";
		 String deedID="";
		 String propertyID="";
		 String regNum="";
		 String deedNum="";
		 String propertyNum="";
		 boolean regNumVal=false;
		 boolean deedNumVal=false;
		 boolean propertyNumVal=false;
		 String propertyRelatedDeed=(String)subList.get(10);
		 if(firstTime==0)
		 {
			 if(subList!=null && subList.size()>0)
			 {			
				regDeedPropertyMap.put("regID",(String)subList.get(0));
	            regDeedPropertyMap.put("deedID",(String)subList.get(1));
	            regDeedPropertyMap.put("propertyID",(String)subList.get(2));
	            return doLoop;
			 }
		 }
		 if(regDeedPropertyMap!=null)
		 {
			  regID=(String)regDeedPropertyMap.get("regID");
			  deedID=(String)regDeedPropertyMap.get("deedID");
			  propertyID=(String)regDeedPropertyMap.get("propertyID");
		 }
		  
		 if(subList!=null)
		 {
			  regNum=(String)subList.get(0);
			  deedNum=(String)subList.get(1);
			  propertyNum=(String)subList.get(2);
		 }
		 
		 if((regID!=null && regID.length()>0) && (regNum!=null && regNum.length()>0))
		 {
			 if(regID.equalsIgnoreCase(regNum))
			 {
				 regNumVal=true;
			 }else{
				 regDeedPropertyMap.put("regID", regNum);
			 }
		 }
		 if((deedID!=null && deedID.length()>0) && (deedNum!=null && deedNum.length()>0))
		 {
			 if(deedID.equalsIgnoreCase(deedNum))
			 {
				 deedNumVal=true;
			 }else{
				 regDeedPropertyMap.put("deedID", deedNum);
			 }
		 }
		 if(propertyRelatedDeed!=null && propertyRelatedDeed.length()>0 && propertyRelatedDeed.equalsIgnoreCase("Y"))
		 {
			 if((propertyNum!=null && propertyNum.length()>0))
			 {
				 if(propertyID.equalsIgnoreCase(propertyNum))
				 {
					 propertyNumVal=true;
				 }else{
					 regDeedPropertyMap.put("propertyID", propertyNum);
				 }
			 }
		 }
		 
		 if(propertyRelatedDeed!=null && propertyRelatedDeed.length()>0 && propertyRelatedDeed.equalsIgnoreCase("Y"))
		 {		 
			 if(regNumVal && deedNumVal && propertyNumVal)
			 {
				 doLoop=true;
			 }	 
		 }else{
			 if(regNumVal && deedNumVal)
			 {
				 doLoop=true;
			 }	
		 }
		 
		 return doLoop;
	 }
	
	 public ArrayList getPartyIdList(String[] regParam) throws Exception
	 {
		 return regCompDAO.getPartyIdList(regParam);
	 }
	 
	 public boolean shouldPropertyBeAdded(String _regTxnId) throws Exception {
		 boolean shouldPropertyBeAdded=false;
			try {
				shouldPropertyBeAdded = regCompDAO.shouldPropertyBeAdded(_regTxnId);
				
			} catch (Exception e) {
				logger.error(e);
			}
			return shouldPropertyBeAdded;
		}
}
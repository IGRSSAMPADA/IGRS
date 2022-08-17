package com.wipro.igrs.regcompletion.bo;


/**
 * ===========================================================================
 * File           :   RegCompBO.java
 * Description    :   Represents the RegComp BO Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.dao.RegCompDAO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;


public class RegCompBO{

	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompBO.class);
	RegCompBD regCompBD   = new RegCompBD();;
	RegCompDAO refDAO=null;
	public RegCompBO() {
		
	}	
	
//imran data
	/**
	 * for getting All Deed Details
	 * @return ArrayList
	 */
	public ArrayList getDeedType() {
		return regCompBD.getDeedType();
	}
	
	/**
	 * for getting Instruments list based on deed
	 * @param deed
	 * @return ArrayList
	 */
	/*public ArrayList getInstrument(RegInitCompleteDTO dto) {
		 return regCompBD.getInstrument(dto);
	}*/
	
	public ArrayList getExemption(RegInitCompleteDTO dto) {
		 return regCompBD.getInstrument(dto);
	}
	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * @param deed
	 * @param deed1
	 * @param string 
	 * @return ArrayList
	 */
	/*public ArrayList getExemption(String deed,String deed1, String string) {
		return regCompBD.getExemption(deed,deed1,string);
	}*/
	/**
	 * returns the given application details
	 * @param regNo
	 * @return ApplicantForm
	 */
	public RegInitCompleteDTO getRegApplicationDetails(String regNo) {
		return regCompBD.getRegApplicationDetails(regNo);
	}
	/**
	 * returns the Form Fields for selected deed
	 * @param deed
	 * @return ArrayList
	 */
	/*public ArrayList getFormFields(String deed) {
		return regCompBD.getFormFields(deed);
	}*/
	
	/**
	 * Storing deed details
	 * @param deedDetails
	 * @return ArrayList
	 */
	/*public ArrayList saveDeedDetails(ArrayList deedDetails) {
		return regCompBD.saveDeedDetails(deedDetails);
	}*/
	
	/**
	 * getting deed details for given Deed Id
	 * @param deedId
	 * @return ApplicantForm
	 */
	public RegInitCompleteDTO getDeedDetails(String deedId) throws Exception {
		return regCompBD.getDeedDetails(deedId);
	}
	
	/**
	 * Update Deed Details
	 * @param appForm
	 * @return boolean
	 */
	/*public boolean updateDeedDetails(ApplicantForm appForm) {
		return regCompBD.updateDeedDetails(appForm);
	}*/
	
	/**
	 * Delete Deed Details
	 * @param appForm
	 * @return boolean
	 */
	public ArrayList deleteDeedDetails(RegInitCompleteDTO appForm) {
		return regCompBD.deleteDeedDetails(appForm);
	}
	
	/**
	 * Save Deed Details
	 * @param appForm
	 * @return ArrayList
	 */
	public ArrayList saveDeedDetail(RegInitCompleteDTO dto)
					throws Exception {
		return regCompBD.saveDeedDetail(dto);
	}
	
	/**
	 * returns the selected deed details only
	 * @param deed
	 * @return CommonDTO
	 */
	public CommonDTO getDeed(String deed) {
		return regCompBD.getDeed(deed);
	}
	
//piyush data
	public ArrayList countryStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (countryStackBO) Method");
    		listBO=refDAO.countryStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList stateStackBO(String _countryId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (stateStackBO) Method");
    		listBO=refDAO.stateStackDAO(_countryId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList photoIdStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (photoIdStackBO) Method");
    		listBO=refDAO.photoIdStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList casteStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (casteStackBO) Method");
    		listBO=refDAO.casteStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList religionStackBO()throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (religionStackBO) Method");
    		listBO=refDAO.religionStackDAO();
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList districtStackBO(String _stateId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (districtStackBO) Method");
    		listBO=refDAO.districtStackDAO(_stateId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean addPartyBO(String _param[])throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (addPartyBO) Method");
    		flag=refDAO.addPartyDAO(_param);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public boolean addOrgaBO(String _param[])throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (addOrgaBO) Method");
    		flag=refDAO.addOrgaDAO(_param);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public ArrayList selectPartiesBO(String _appliNo)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (selectPartiesBO) Method");
    		listBO=refDAO.selectPartiesDAO(_appliNo);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList getPropertyList(String[] regID)  {
    	 
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (selectPartiesBO) Method");
    		listBO=refDAO.getPropertyList(regID);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public ArrayList viewPartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (viewPartyBO) Method");
    		listBO=refDAO.viewPartyDAO(_partyId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean deletePartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (deletePartyBO) Method");
    		flag=refDAO.deletePartyDAO(_partyId);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
    public ArrayList retrievePartyBO(String _partyId)throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		ArrayList listBO=new ArrayList();
    	try{
    		logger.info("Inside (retrievePartyBO) Method");
    		listBO=refDAO.retrievePartyDAO(_partyId);
    		return listBO;
    	}catch (Exception e) {
    		logger.error(e);
    		return null;
    	}
    }
    public boolean updatePartyBO(String _partyType, String param[])throws Exception
    {
    	logger.debug("WE ARE IN BO Debug");
		refDAO=new RegCompDAO();
		boolean flag=false;
    	try{
    		logger.info("Inside (updatePartyBO) Method");
    		flag=refDAO.updatePartyDAO(_partyType, param);
    		return flag;
    	}catch (Exception e) {
    		logger.error(e);
    		return flag;
    	}
    }
	
    /**
     * returns the selected deeds for given reg Id
     * @param regId
     * @return ArrayList
     */
	public HashMap getDeedsList(String regId) {
		refDAO=new RegCompDAO();
		return refDAO.getDeedsList(regId);
	}
	
	/**
	 * returns the selected Properties list for given Reg Id
	 * @param regId
	 * @return ArrayList
	 */
	public ArrayList getPropertyListByRegId(String[] param) {
		refDAO=new RegCompDAO();
		return refDAO.getPropertyListByRegId(param);
	}
	
	/**
	 * based on deed txn it will returns the number of dependent properties
	 * @param deed
	 * @return NofProperty
	 */
	public String getNofProperty(String deed) {
		refDAO=new RegCompDAO();
		return refDAO.getNofProperty(deed);
	}
	
	
	/**
	 * save Deed To Property or Transacting Party Mapping
	 * @param params
	 * @return boolean
	 */
	
	
	public boolean saveDeedToPropAndTranMap(
			String[] params,DBUtility dbUtility) {
		refDAO=new RegCompDAO();
		return refDAO.saveDeedToPropAndTranMap(params,dbUtility);
	}
	
	
	/**
	 * save Deed To Property or Transacting Party Mapping
	 * @param params
	 * @param nofProperty
	 * @param size
	 * @return boolean
	 */
	
	
	public HashMap saveDeedToPropAndTranMap(CommonDTO dto,
			String nofProperty,HashMap map) {
		refDAO=new RegCompDAO();
		return refDAO.saveDeedToPropAndTranMap(dto,nofProperty,map);
	}
	
	
	/**
	 * to get the details of Deed , Property and Transaction Parties details
	 * @param regNo
	 * @return ArrayList
	 */
	public ArrayList getDeedList(String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompDAO().getDeedList(regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPropertyList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompDAO().getPropertyList(deedTxnId, regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPartyList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompDAO().getPartyList(deedTxnId, regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getPartyTypeList(String deedTxnId,String regNo)  {
		ArrayList list = new ArrayList();
		try {
			list = new RegCompDAO().getPartyTypeList(deedTxnId,regNo);
		}catch(Exception x) {
			logger.debug(x);
		}
		return list;
	}
	public ArrayList getDeedPropAndTreanMapping(String regNo) {
		ArrayList list = new ArrayList();
		refDAO=new RegCompDAO();
		try {
			list = refDAO.getDeedPropAndTreanMapping(regNo);
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
		refDAO=new RegCompDAO();
		return refDAO.getComplList();
	}
	 /**
	  * inserting compliance details.
	  * @param form
	  * @return
	  */
	public boolean insComplDet(String[] param) {
		refDAO=new RegCompDAO();
		return refDAO.insComplDet(param);
	}
	public ArrayList getPrDeedDet(String regNO) {
		refDAO=new RegCompDAO();
		return refDAO.getPrDeedDet(regNO);
	}
	public ArrayList getDeedTypeId(String deedId)
	{
		refDAO=new RegCompDAO();
		return refDAO.getDeedTypeId(deedId);
	}
	public ArrayList getInsExemList(String deed) {
		refDAO=new RegCompDAO();
		return refDAO.getInsExemList(deed);
	}
	public String[] getStampDuty(String deed, String instrument,
			String exemption, String propId) 
	{
		refDAO=new RegCompDAO();
		return refDAO.getStampDuty(deed,instrument,exemption,propId);
	}
	public ArrayList getOthersDuty(String functionId, String serviceId,
			String userId)
	{
		refDAO=new RegCompDAO();
		return refDAO.getOthersDuty(functionId,serviceId,userId);
	}
	/**
	 * delete tran details
	 * @param regNo
	 */
	public void deleteDeedToPropAndTranMap(String string) {
		refDAO=new RegCompDAO();
		refDAO.deleteDeedToPropAndTranMap(string);
	}
	/**
	 * Hari Venkata
	 */
	
	/**
	 * 
	 * @param _propTxnNo
	 * @return
	 * @throws Exception
	 */
	  public RegCompletDTO getSelectedPropertyDetails(String _propTxnNo) throws Exception {		
		  RegCompletDTO dto = new RegCompletDTO();
			try {
				dto = regCompBD.getSelectedPropertyDetails(_propTxnNo);
				
			 } catch (Exception e) {
					logger.error(e);

				}
			  return dto;
			  
	  }
	  /**
		 * 
		 * @param _propTxnNo
		 * @return
		 * @throws Exception
		 */
	  public ArrayList getSelectedProperty(String _propTxnNo) throws Exception {		
			ArrayList dto = new  ArrayList();
				try {
					dto = regCompBD.getSelectedProperty(_propTxnNo);
					
				 } catch (Exception e) {
						logger.error(e);

			    }
			return dto;
				  
	  }
/**
 * @param _distId
 * @return
 * @throws Exception
 */
	public ArrayList getPropertyTxnIdList(String _regTxnId) throws Exception {
			ArrayList propList = new ArrayList();
			try {
				propList = regCompBD.getPropertyTxnIdList(_regTxnId);
				
			} catch (Exception e) {
				logger.error(e);
			}
			return propList;
		}
	public boolean deletePropertyDetails(String _propTxnNo,
			String propertyType)throws Exception {
		  boolean flg=false; 
		  try{
			  flg=regCompBD.deletePropertyDetails(_propTxnNo,
						propertyType);
			
		  } catch (Exception e) {
				logger.error(e);

			}
		  return flg;
		  
	  }
	public boolean updatePropertyDetails(RegCompletDTO _searchDTO,RegCompletDTO _completeDTO)throws Exception {
		  boolean flg=false; 
		  try{
			  flg=regCompBD.updatePropertyDetails(_searchDTO, _completeDTO);
			
		  } catch (Exception e) {
				logger.error(e);

			}
		  return flg;
		  
	  }
	public String validateRegComplAdjudicationId(String _adjudiTxnId)throws Exception{
			
			String propertyTxnId="";
			try{
				
				propertyTxnId=regCompBD.validateRegComplAdjudicationId(_adjudiTxnId);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			return propertyTxnId;	
			
		}

	public boolean validateRegComplPoaTxnId(String _poaTxnId)throws Exception{	
		boolean flg=false;
		try{
			
			flg=regCompBD.validateRegComplPoaTxnId(_poaTxnId);
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flg;	
	}
	
	public String storePropertyDetails(RegCompletDTO _detailsDto,RegCompletDTO _searchDTO,RegCompletDTO _displayDTO) throws Exception {
		String propTxnNo="";
		try{		
			 
			//propTxnNo=regCompBD.storePropertyDetails(_detailsDto, _searchDTO, _displayDTO);
			
		}catch(Exception e){
			
			logger.error(e);
		}
		
		
		
		return propTxnNo;
		
	}

	/**
	 * store registration documents
	 * @param attachments
	 */
	public void storeRegDocuments(ArrayList attachments) {
		RegCompDAO regCompDAO = new RegCompDAO();
		regCompDAO.storeRegDocuments(attachments);
	}
	/**
	 * 
	 * @param propId
	 * @return
	 */
	public ArrayList getPropertyValueDetailsList(String propId) 
				throws Exception{
		return refDAO.getPropertyValueDetailsList(propId);
	}

	public ArrayList getPropDetDuty(String propId) {
		refDAO=new RegCompDAO();
		return refDAO.getPropDetDuty(propId);
	}
	public ArrayList getMunDuty(String districtId, String tehsil, String ward,
			String mohilla, String propVal) {
		refDAO=new RegCompDAO();
		return refDAO.getMunDuty(districtId,tehsil,ward,mohilla,propVal);
	}
	public String[] getRegFeeDuty(String deedId, String instrument,
			String strexem, String propVal) 
	{
		refDAO=new RegCompDAO();
		return refDAO.getRegFeeDuty(deedId,instrument,strexem,propVal);
	}
	
	/**
	 * inserting registration details
	 * @param params
	 * @return status(boolean)
	 */
	
	public String insertRegDetails(String[] params)  {
		refDAO=new RegCompDAO();
		return refDAO.insertRegDetails(params); 
	}
	/**
	 * getting stamp Duty paid value
	 * @param regNo
	 * @return stampDuty
	 */

	public ArrayList getDutyPaid(String regNo) {
		refDAO=new RegCompDAO();
		return refDAO.getDutyPaid(regNo); 
	}
	
	/**
	 * 
	 * @param regCompId
	 * @return
	 */
	public boolean updateRegDetails(String regCompId,String regStatus) {
		refDAO=new RegCompDAO();
		return refDAO.updateRegDetails(regCompId,regStatus);
	}
	public StringBuffer updateDeedRORDtls(ArrayList propDeedList) {
		refDAO=new RegCompDAO();
		return refDAO.updateDeedRORDtls(propDeedList);
	}
	public boolean insertOldAndNewRegIDS(String regCompId,String regStatus) {
		refDAO=new RegCompDAO();
		return refDAO.insertOldAndNewRegIDS(regCompId,regStatus);
	}
	public String getRegInitDate(String[] regCompId) {
		refDAO=new RegCompDAO();
		return refDAO.getRegInitDate(regCompId);
	}
	public String verifyPin(CommonDTO dtoMap) throws Exception {
		refDAO = new RegCompDAO();
		return refDAO.verifyPin(dtoMap);
	}
	
	// Added By Aruna
	/**
	 * Save Deed Details
	 * @param appForm
	 * @return ArrayList
	 */
	public ArrayList getCompDeedList(String regNumber)
					throws Exception {
		return regCompBD.getCompDeedList(regNumber);
	}
	

	 public ArrayList getPartyIdList(String[] regParam) throws Exception
	 {
		 return regCompBD.getPartyIdList(regParam);
	 }
	 
	 public boolean shouldPropertyBeAdded(String _regTxnId) throws Exception {
		 boolean shouldPropertyBeAdded=false;
			try {
				shouldPropertyBeAdded = regCompBD.shouldPropertyBeAdded(_regTxnId);				
			} catch (Exception e) {
				logger.error(e);
			}
			return shouldPropertyBeAdded;
		}
	
}

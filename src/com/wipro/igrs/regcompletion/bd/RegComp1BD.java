package com.wipro.igrs.regcompletion.bd;


import com.wipro.igrs.regcompletion.dto.Common1DTO;
import com.wipro.igrs.regcompletion.bo.RegComp1BO;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.util.CommonUtil;


public class RegComp1BD {
    
	private  Logger logger = 
		(Logger) Logger.getLogger(RegComp1BD.class);
    public RegComp1BD() {
    }
    public boolean addParty(Common1DTO _cDTO, FormFile filePhoto,
	    FormFile fileSign, FormFile fileThumb) throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
    	try{
    		logger.info("Inside (addParty) method");
    		boolean boo = false;
	    	//RegCompDAO refDAO=new RegCompDAO();
    		RegComp1BO refBO=new RegComp1BO();
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
	    	boo = refBO.addPartyBO(param,filePhoto,fileSign,fileThumb);
	    	return boo;//refDAO.addPartyDao(param));
    	}catch (Exception e) {
    			logger.error(e);
    			return false;
		}
    }
    
    public boolean addOrga(Common1DTO _cDTO) throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
    	try{
    		logger.info("Inside (addOrga) Method");
	    	//RegCompDAO refDAO=new RegCompDAO();
    		RegComp1BO refBO=new RegComp1BO();
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
				logger.error(e);
			return false;
		}
    }
    public ArrayList selectParties(String[] _appliNo) throws Exception
    {
        ArrayList list=new ArrayList();
        logger.debug("WE ARE IN BD Debug");
        try{
        	logger.info("Inside (selectParties) Method");
        	RegComp1BO refBO=new RegComp1BO();
        	list= refBO.selectPartiesBO(_appliNo);
        }catch(Exception ex)
        {
            logger.error(ex);
            return null;
        }
        return list;
    }
    public ArrayList selectParties(String appliNo) throws Exception
    {
        ArrayList list=new ArrayList();
        logger.debug("WE ARE IN BD Debug");
        try{
        	logger.info("Inside (selectParties) Method");
        	RegComp1BO refBO=new RegComp1BO();
        	list= refBO.selectPartiesBO(appliNo);
        }catch(Exception ex)
        {
            logger.error(ex);
            return null;
        }
        return list;
    }
    public Common1DTO viewParty(String _partyId)throws Exception
    {
    	Common1DTO cdto=new Common1DTO();
    	RegComp1BO refBo=new RegComp1BO();
		ArrayList partyData=new ArrayList();
		logger.debug("WE ARE IN BD Debug");
    	try{
    		logger.info("Inside (viewParty) Method");
    		partyData=refBo.viewPartyBO(_partyId);
    		if(!partyData.isEmpty())
    		{
    			ArrayList fieldList= new ArrayList();
                fieldList=(ArrayList) partyData.get(0);
                cdto.setPartyId((String)fieldList.get(0));
                cdto.setPartyType((String)fieldList.get(1));
                if(fieldList.get(24)==null)
                {
                	logger.info("If Party is Not an Organization");
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
                	logger.info("If Party is an Organization");
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
			logger.error(e);
			return null;
		}
    }
    public boolean deleteParty(String _partyId)throws Exception
    {
		//RegCompDAO refDAO=new RegCompDAO();
    	RegComp1BO refBO=new RegComp1BO();
    	try{
    		logger.info("Inside (deleteParty) Method");
    		boolean result=refBO.deletePartyBO(_partyId);
    		return result;
    	}catch (Exception e) {
    		logger.error(e);
    		return false;
    	}
    }
    public Common1DTO retrieveParty(String _partyId)throws Exception
    {
    	Common1DTO cdto=new Common1DTO();
		//RegCompDAO refDAO=new RegCompDAO();
    	RegComp1BO refBO=new RegComp1BO();
    	ArrayList partyData=new ArrayList();
    	try{
    		partyData=refBO.retrievePartyBO(_partyId);
    		if(!partyData.isEmpty())
    		{
    			logger.info("Inside (retrieveParty) Method");
    			ArrayList fieldList= new ArrayList();
                fieldList=(ArrayList) partyData.get(0);
                cdto.setPartyId((String)fieldList.get(0));
                cdto.setPartyType((String)fieldList.get(1));
                if(fieldList.get(24)==null)
                {
                	logger.info("If not Organization");
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
                	logger.info("If Organization");
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
    		logger.error(e);
			return null;
		}
    }
    public boolean updateParty(Common1DTO _cDTO) throws Exception
    {
    	try{
    		logger.info("Inside (retrieveParty) Method");
    		//RegCompDAO refDAO=new RegCompDAO();
    		RegComp1BO refBO=new RegComp1BO();
	    	// Edited By Aruna
    		//if(_cDTO.getPartyType().equalsIgnoreCase("Individuals"))
    		if(_cDTO.getPartyType().equalsIgnoreCase("02"))
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
    		logger.error(e);
			return false;
		}
    }
    public ArrayList countryStackBD()throws Exception
    {
    	logger.debug("WE ARE IN BD Debug");
		RegComp1BO refBO=new RegComp1BO();
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
		RegComp1BO refBO=new RegComp1BO();
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
		RegComp1BO refBO=new RegComp1BO();
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
		RegComp1BO refBO=new RegComp1BO();
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
		RegComp1BO refBO=new RegComp1BO();
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
		RegComp1BO refBO=new RegComp1BO();
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
}
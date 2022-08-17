/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.poa.bd;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.poa.bo.POAAuthenticationFormBO;
import com.wipro.igrs.poa.dao.POAAuthenticationFormDAO;
import com.wipro.igrs.poa.dto.POAAuthenticationDTO;
import com.wipro.igrs.poa.dto.POAAuthenticationFormDTO;

/* class added by Shruti*/

public class POAAuthenticationFormBD {
	
	
	POAAuthenticationFormDAO poaDAO=new POAAuthenticationFormDAO();
	
	private Logger logger = (Logger) Logger.getLogger(POAAuthenticationFormBD.class);
	

	public ArrayList getpoaTypeList() {
			 return poaDAO.getpoaTypeList();
	}
	
	public ArrayList getecodePoaTypeList() {
		 return poaDAO.getecodePoaTypeList();
	}
	public ArrayList getawrdrcountryList() 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getCountry();
}
	public ArrayList getawrdrstateList(String _countryId) 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getState(_countryId);
}
	public ArrayList getawrdrdistrictList(String _stateId) 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getDistrict(_stateId);
}
	public ArrayList getacptrcountryList() 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getCountry1();
}
	public ArrayList getacptrstateList(String _countryId1) 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getState1(_countryId1);
}
	public ArrayList getacptrdistrictList(String _stateId1) 
	throws NullPointerException, SQLException, Exception {
		 return poaDAO.getDistrict1(_stateId1);
}
	public String getInsertionOfPOADetails(POAAuthenticationFormDTO poaDTO)
	throws NullPointerException, SQLException, Exception
	{
		return poaDAO.getInsertionOfPOADetails(poaDTO);
	}
	public ArrayList getSROName(POAAuthenticationFormDTO poaDTO) throws Exception
	{
		return poaDAO.getSROName(poaDTO);
	}
	public String getSRName(String userid)
	{
		return poaDAO.getSRName(userid);
	}
	public String getSystemDate()
	{
		return poaDAO.systemDate();
	}
	public String getCurrentYear() throws Exception
	{
		return poaDAO.getcurrentYear();
	}

	public synchronized ArrayList getPoaViewDetails(POAAuthenticationFormDTO poaDTO)
	throws NullPointerException, Exception {
		ArrayList poaList = new ArrayList();
		ArrayList poaid = null;
		try {
				ArrayList getpoa = new ArrayList();
				String[] poaString = new String[3];
				poaString[0] = poaDTO.getPoaId();
				String poaId = poaDTO.getPoaId();
				poaString[1] = poaDTO.getPoaFromDate();
				String dateFrom = poaDTO.getPoaFromDate();
				poaString[2] = poaDTO.getPoaToDate();
				String dateTo = poaDTO.getPoaToDate();
	
				getpoa = poaDAO.getPoaViewDetails(poaId);
				
				if(poaId!=null && (!poaId.equals("")))
				{
				    logger.info("I am in poaId -------------------------");
				    if (getpoa != null) 
				    {
					for (int i = 0; i < getpoa.size(); i++) 
							{
								poaid = new ArrayList();
								poaid = (ArrayList) getpoa.get(i);
			            					if (poaid.size() > 0)
			            					{
			            						poaDTO = new POAAuthenticationFormDTO();
			            						poaDTO.setPoaId((String) poaid.get(0));
			            						logger.info("(String) poaid.get(0)----"+(String) poaid.get(0));
			            						poaDTO.setPoaCreateDate((String) poaid.get(1));
			            						logger.info("(String) poaid.get(1)---"+(String) poaid.get(1));
			            						if((String)poaid.get(2) ==null)
			            						{
			            							poaDTO.setPoaFromDate("-");
			            						}
			            						else
			            						{
			            							poaDTO.setPoaFromDate((String)poaid.get(2));
			            						}
			            						if((String)poaid.get(3) == null)
			            						{
			            							poaDTO.setPoaToDate("-");
			            						}
			            						else
			            						{
			            							poaDTO.setPoaToDate((String)poaid.get(3));
			            						}
			            						poaList.add(poaDTO);
			            					} else 
			            					{
			            						poaList.add(null);
			            					}
							}
				    } 
				    else 
				    {
					poaList.add(null);
				    }
				}
				else
				{
				    getpoa = poaDAO.getPoaViewDetails2(dateFrom,dateTo);
				    
				    if (getpoa != null) 
				    {
					for (int i = 0; i < getpoa.size(); i++) 
							{
								poaid = new ArrayList();
								poaid = (ArrayList) getpoa.get(i);
			            					if (poaid.size() > 0)
			            					{
			            						poaDTO = new POAAuthenticationFormDTO();
			            						poaDTO.setPoaId((String) poaid.get(0));
			            						poaDTO.setPoaCreateDate((String) poaid.get(1));
			            						if((String)poaid.get(2) == "")
			            						{
			            							poaDTO.setPoaFromDate("-");
			            						}
			            						else
			            						{
			            							poaDTO.setPoaFromDate((String)poaid.get(2));
			            						}
			            						if((String)poaid.get(3) == "")
			            						{
			            							poaDTO.setPoaToDate("-");
			            						}
			            						else
			            						{
			            							poaDTO.setPoaToDate((String)poaid.get(3));
			            						}
			            						poaList.add(poaDTO);
			            					} else 
			            					{
			            						poaList.add(null);
			            					}
							}
				    } 
				    else 
				    {
					poaList.add(null);
				    }
				    
				}

} 
catch (NullPointerException ne) {
	System.out
			.println("NullPoninter Exception at getPoaViewDetails() in Bd "
					+ ne);
} catch (Exception e) {
	logger.info("Exception at getPoaViewDetails() in Bd " + e);
}
return poaList;
}

	
	
	public synchronized ArrayList getPoaDetails(String _poaId)
	throws NullPointerException, Exception {
		
		ArrayList poaSearchList = new ArrayList();
		ArrayList poaList = null;
		try {
			ArrayList getPoaList = new ArrayList();
			String poaId = new String();
			poaId = _poaId;
		
			getPoaList = poaDAO.getPoaDetails(poaId);
			if (getPoaList != null) {
				POAAuthenticationDTO poaDto = new POAAuthenticationDTO();
				for (int i = 0; i < getPoaList.size(); i++) {
					poaList = new ArrayList();
					poaList = (ArrayList) getPoaList.get(i);
					if (poaList.size() > 0) {
						if (i == 0) {
							poaDto.setRemarks((String) poaList.get(0));
							poaDto.setCreatedDate((String) poaList.get(1));
							poaDto.setDeedId((String) poaList.get(2));
							poaDto.setPurpose((String) poaList.get(3));
							poaDto.setPoaTxnId((String) poaList.get(4));
							poaDto.setOrgName((String) poaList.get(24));
							poaDto.setPoaStatus((String) poaList.get(40));
							poaDto.setPayTxnId((String) poaList.get(41));
		
							poaDto.setAwderPartyId((String) poaList.get(5));
							poaDto.setAwderFName((String) poaList.get(6));
							poaDto.setAwderMName((String) poaList.get(7));
							poaDto.setAwderLName((String) poaList.get(8));
							poaDto.setAwderGender((String) poaList.get(9));
							poaDto.setAwderAge(Integer.parseInt((String) poaList.get(10)));
							poaDto.setAwderNationality((String) poaList.get(11));
							poaDto.setAwderCountry((String) poaList.get(12));
							poaDto.setAwderState((String) poaList.get(13));
							poaDto.setAwderDist((String) poaList.get(14));
							poaDto.setAwderAddress((String) poaList.get(15));
							poaDto.setAwderPostPin((String) poaList.get(16));
							poaDto.setAwderPhoneNo((String) poaList.get(17));
							poaDto.setAwderMobilNo((String) poaList.get(18));
							poaDto.setAwderEmail((String) poaList.get(19));
							poaDto.setAwderPhotoId((String) poaList.get(20));
							poaDto.setAwderPhotoIDNo((String) poaList.get(21));
							poaDto.setAwderBank((String) poaList.get(22));
							poaDto.setAwderBankAddress((String) poaList.get(23));
							poaDto.setAwderFatherName((String) poaList.get(25));
							poaDto.setAwderMotherName((String) poaList.get(26));
							poaDto.setAwaderCategory((String) poaList.get(27));
							poaDto.setAwderCaste((String) poaList.get(28));
							poaDto.setAwderReligion((String) poaList.get(29));
							poaDto.setThumImpression((String) poaList.get(30));
							poaDto.setPartyphoto((String) poaList.get(31));
							poaDto.setPartySignature((String) poaList.get(32));
							poaDto.setPoaRegNo((String) poaList.get(33));
							poaDto.setRegNo((String) poaList.get(34));
							poaDto.setPoaEstamCode((String) poaList.get(35));
							poaDto.setEstampAmt((String) poaList.get(36));
							poaDto.setRegNo((String) poaList.get(37));
							poaDto.setExecuteDate((String) poaList.get(38));
							poaDto.setPresentationDate((String) poaList.get(39));
						}
						if (i == 1) {
							poaDto.setAwdeePartyId((String) poaList.get(5));
							poaDto.setAwdeeFName((String) poaList.get(6));
							poaDto.setAwdeeMName((String) poaList.get(7));
							poaDto.setAwdeeLName((String) poaList.get(8));
							poaDto.setAwdeeGender((String) poaList.get(9));
							poaDto.setAwdeeAge(Integer.parseInt((String) poaList.get(10)));
							poaDto.setAwdeeNationality((String) poaList.get(11));
							poaDto.setAwdeeCountry((String) poaList.get(12));
							poaDto.setAwdeeState((String) poaList.get(13));
							poaDto.setAwdeeDist((String) poaList.get(14));
							poaDto.setAwdeeAddress((String) poaList.get(15));
							poaDto.setAwdeePostPin((String) poaList.get(16));
							poaDto.setAwdeePhoneNo((String) poaList.get(17));
							poaDto.setAwdeeMobileNo((String) poaList.get(18));
							poaDto.setAwdeeEmail((String) poaList.get(19));
							poaDto.setAwdeePhotoId((String) poaList.get(20));
							poaDto.setAwdeePhotoIDNo((String) poaList.get(21));
							poaDto.setAwdeeBank((String) poaList.get(22));
							poaDto.setAwdeeBankAddress((String) poaList.get(23));
							poaDto.setAwdeeFatherName((String) poaList.get(25));
							poaDto.setAwdeeMotherName((String) poaList.get(26));
							poaDto.setAwadeeCategory((String) poaList.get(27));
							poaDto.setAwdeeCaste((String) poaList.get(28));
							poaDto.setAwdeeReligion((String) poaList.get(29));
						}
		
					} else {
						poaSearchList.add(null);
					}
		
				}
				poaSearchList.add(poaDto);
			} else {
				poaSearchList.add(null);
			}
		
		} catch (NullPointerException ne) {
			System.out
					.println("NullPoninter Exception at getPoaDetails() in BO "
							+ ne);
		} catch (Exception e) {
			logger.info("Exception at getPoaDetails() in BO " + e);
		}
		return poaSearchList;
}
	
//*******************************************ADDED BY SIMRAN****************************************************//
	
/**
 * 
 * @param poaAuthNo
 * @return
 * @throws Exception
 */
public ArrayList getPOAApplicantDetlsForView(String poaAuthNo) throws Exception {

	ArrayList list = poaDAO.getPOAApplicantDetlsForView(poaAuthNo);
	ArrayList mainList  = new ArrayList();
	
	for(int i = 0 ; i < list.size() ; i++)
	{
		ArrayList list1 = (ArrayList)list.get(i);
		POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
		if((String)list1.get(0) == "")
			pdto.setFirstName("-");
		else
			pdto.setFirstName((String)list1.get(0));
		if((String)list1.get(1) == "")
			pdto.setMiddleName("-");
		else
			pdto.setMiddleName((String)list1.get(1));
		if((String)list1.get(2) == "")
			pdto.setLastName("-");
		else
			pdto.setLastName((String)list1.get(2));
		if((String)list1.get(3) == "")
			pdto.setGender("");
		else
			pdto.setGender((String)list1.get(3));
		if((String)list1.get(4) == "")
			pdto.setDob("-");
		else
			pdto.setDob((String)list1.get(4));
		if((String)list1.get(5) == "")
			pdto.setFatherName("-");
		else
			pdto.setFatherName((String)list1.get(5));
		if((String)list1.get(6) == "")
			pdto.setMotherName("-");
		else
			pdto.setMotherName((String)list1.get(6));
		if((String)list1.get(7) == "")
			pdto.setSpouseName("-");
		else
			pdto.setSpouseName((String)list1.get(7));
		if((String)list1.get(8) == "")
			pdto.setAddress("-");
		else
			pdto.setAddress((String)list1.get(8));
		if((String)list1.get(9) == "")
			pdto.setPhno("-");
		else
			pdto.setPhno((String)list1.get(9));
		if((String)list1.get(10) == "")
			pdto.setMobno("-");
		else
			pdto.setMobno((String)list1.get(10));
		if((String)list1.get(11) == "")
			pdto.setEmail("-");
		else
			pdto.setEmail((String)list1.get(11));
		if((String)list1.get(12) == "")
			pdto.setPoaTypeId("-");
		else
			pdto.setPoaTypeId((String)list1.get(12));
		if((String)list1.get(13) == "")
			pdto.setManualStampchk("Yes");
		else
			pdto.setManualStampchk("No");
		if(((String)list1.get(12)).equalsIgnoreCase("Registered"))
		{
			if((String)list1.get(14) == "")
				pdto.setPoaRegNo("-");
			else
				pdto.setPoaRegNo((String)list1.get(14));
				
		}
		else
		{
			if((String)list1.get(15) == "")
				pdto.setEstampAppNo("-");
			else
				pdto.setEstampAppNo((String)list1.get(15));
		}
		mainList.add(pdto);	
		
	}
	logger.debug("Size of applicant List****"+mainList.size());
	return mainList;
}

/**
 * 
 * @param poaAuthNo
 * @return
 * @throws Exception
 */
public ArrayList getPOAAwardeeDetlsForView(String poaAuthNo) throws Exception {

	ArrayList list = poaDAO.getPOAAwardeeDetlsForView(poaAuthNo);
	ArrayList mainList  = new ArrayList();
	if(list.size() == 0)
	{
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			pdto.setAwrdrFirstName("-");
			pdto.setAwrdrMiddleName("-");
			pdto.setAwrdrLastName("-");
			pdto.setAwrdrGender("");
			pdto.setAwrdrDob("-");
			pdto.setAwrdrFatherName("-");
			pdto.setAwrdrMotherName("-");
			pdto.setAwrdrSpouseName("-");
			pdto.setAwrdrAddress("-");
			pdto.setAwrdrPhno("-");
			pdto.setAwrdrMobno("-");
			pdto.setAwrdrEmail("-");
			mainList.add(pdto);	
	}
	else
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			ArrayList list1 = (ArrayList)list.get(i);
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			if((String)list1.get(0) == "")
				pdto.setAwrdrFirstName("-");
			else
				pdto.setAwrdrFirstName((String)list1.get(0));
			if((String)list1.get(1) == "")
				pdto.setAwrdrMiddleName("-");
			else
				pdto.setAwrdrMiddleName((String)list1.get(1));
			if((String)list1.get(2) == "")
				pdto.setAwrdrLastName("-");
			else
				pdto.setAwrdrLastName((String)list1.get(2));
			if((String)list1.get(3) == "")
				pdto.setAwrdrGender("");
			else
				pdto.setAwrdrGender((String)list1.get(3));
			if((String)list1.get(4) == "")
				pdto.setAwrdrDob("-");
			else
				pdto.setAwrdrDob((String)list1.get(4));
			if((String)list1.get(5) == "")
				pdto.setAwrdrFatherName("-");
			else
				pdto.setAwrdrFatherName((String)list1.get(5));
			if((String)list1.get(6) == "")
				pdto.setAwrdrMotherName("-");
			else
				pdto.setAwrdrMotherName((String)list1.get(6));
			if((String)list1.get(7) == "")
				pdto.setAwrdrSpouseName("-");
			else
				pdto.setAwrdrSpouseName((String)list1.get(7));
			if((String)list1.get(8) == "")
				pdto.setAwrdrAddress("-");
			else
				pdto.setAwrdrAddress((String)list1.get(8));
			if((String)list1.get(9) == "")
				pdto.setAwrdrPhno("-");
			else
				pdto.setAwrdrPhno((String)list1.get(9));
			if((String)list1.get(10) == "")
				pdto.setAwrdrMobno("-");
			else
				pdto.setAwrdrMobno((String)list1.get(10));
			if((String)list1.get(11) == "")
				pdto.setAwrdrEmail("-");
			else
				pdto.setAwrdrEmail((String)list1.get(11));
			
			mainList.add(pdto);	
			
		}
	}
	logger.debug("Size of awardee List****"+mainList.size());
	
	return mainList;
}

/**
 * 
 * @param poaAuthNo
 * @return
 * @throws Exception
 */
public ArrayList getPOAAcceptorDetlsForView(String poaAuthNo) throws Exception {

	ArrayList list = poaDAO.getPOAAcceptorDetlsForView(poaAuthNo);
	ArrayList mainList  = new ArrayList();
	if(list.size() == 0)
	{
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			pdto.setAcptrFirstName("-");
			pdto.setAcptrMiddleName("-");
			pdto.setAcptrLastName("-");
			pdto.setAcptrGender("");
			pdto.setAcptrDob("-");
			pdto.setAcptrFatherName("-");
			pdto.setAcptrMotherName("-");
			pdto.setAcptrSpouseName("-");
			pdto.setAcptrAddress("-");
			pdto.setAcptrPhno("-");
			pdto.setAcptrMobno("-");
			pdto.setAcptrEmail("-");
			mainList.add(pdto);	
	}
	else
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			ArrayList list1 = (ArrayList)list.get(i);
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			if((String)list1.get(0) == "")
				pdto.setAcptrFirstName("-");
			else
				pdto.setAcptrFirstName((String)list1.get(0));
			if((String)list1.get(1) == "")
				pdto.setAcptrMiddleName("-");
			else
				pdto.setAcptrMiddleName((String)list1.get(1));
			if((String)list1.get(2) == "")
				pdto.setAcptrLastName("-");
			else
				pdto.setAcptrLastName((String)list1.get(2));
			if((String)list1.get(3) == "")
				pdto.setAcptrGender("");
			else
				pdto.setAcptrGender((String)list1.get(3));
			if((String)list1.get(4) == "")
				pdto.setAcptrDob("-");
			else
				pdto.setAcptrDob((String)list1.get(4));
			if((String)list1.get(5) == "")
				pdto.setAcptrFatherName("-");
			else
				pdto.setAcptrFatherName((String)list1.get(5));
			if((String)list1.get(6) == "")
				pdto.setAcptrMotherName("-");
			else
				pdto.setAcptrMotherName((String)list1.get(6));
			if((String)list1.get(7) == "")
				pdto.setAcptrSpouseName("-");
			else
				pdto.setAcptrSpouseName((String)list1.get(7));
			if((String)list1.get(8) == "")
				pdto.setAcptrAddress("-");
			else
				pdto.setAcptrAddress((String)list1.get(8));
			if((String)list1.get(9) == "")
				pdto.setAcptrPhno("-");
			else
				pdto.setAcptrPhno((String)list1.get(9));
			if((String)list1.get(10) == "")
				pdto.setAcptrMobno("-");
			else
				pdto.setAcptrMobno((String)list1.get(10));
			if((String)list1.get(11) == "")
				pdto.setAcptrEmail("-");
			else
				pdto.setAcptrEmail((String)list1.get(11));
			
			mainList.add(pdto);	
			
		}
		
	}
	logger.debug("Size of acceptor List****"+mainList.size());
	return mainList;
	
}

/**
 * 
 * @param poaAuthNo
 * @return
 * @throws Exception
 */
public ArrayList getPOACommonDetlsForView(String poaAuthNo) throws Exception {
	ArrayList list = poaDAO.getPOACommonDetlsForView(poaAuthNo);
	ArrayList mainList = new ArrayList();
	if(list.size() == 0)
	{
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			pdto.setPoaFromDate("-");
			pdto.setPoaToDate("-");	
			pdto.setStampduty("-");	
			pdto.setStampCode("-");		
			pdto.setDoe("-");	
			pdto.setDoa("-");		
			pdto.setSrname("-");	
			pdto.setSroName("-");
			pdto.setAwrdrSignatureName("-");
			pdto.setAwrdrThumbName("-");
			pdto.setAwrdrPhotoName("-");
			pdto.setAcptrSignatureName("-");
			pdto.setAcptrThumbName("-");
			pdto.setAcptrPhotoName("-");
			mainList.add(pdto);
	
	}
	else
	{
		for(int i = 0 ; i < list.size() ; i++)
		{
			ArrayList list1 = (ArrayList)list.get(i);
			POAAuthenticationFormDTO pdto = new POAAuthenticationFormDTO();
			if((String)list1.get(0) == "")
				pdto.setPoaFromDate("-");
			else
				pdto.setPoaFromDate((String)list1.get(0));
			if((String)list1.get(1) == "")
				pdto.setPoaToDate("-");
			else
				pdto.setPoaToDate((String)list1.get(1));
			if((String)list1.get(2) == "")
				pdto.setStampduty("-");
			else
				pdto.setStampduty((String)list1.get(2));
			if((String)list1.get(3) == "")
				pdto.setStampCode("-");
			else
				pdto.setStampCode((String)list1.get(3));
			if((String)list1.get(4) == "")
				pdto.setDoe("-");
			else
				pdto.setDoe((String)list1.get(4));
			if((String)list1.get(5) == "")
				pdto.setDoa("-");
			else
				pdto.setDoa((String)list1.get(5));
			if((String)list1.get(6) == "")
				pdto.setSrname("-");
			else
				pdto.setSrname((String)list1.get(6));
			if((String)list1.get(7) == "")
				pdto.setSroName("-");
			else
				pdto.setSroName((String)list1.get(7));
			
			pdto.setAwrdrSignatureName("-");
			pdto.setAwrdrThumbName("-");
			pdto.setAwrdrPhotoName("-");
			pdto.setAcptrSignatureName("-");
			pdto.setAcptrThumbName("-");
			pdto.setAcptrPhotoName("-");
		
		
			mainList.add(pdto);
		}
	}
	
	logger.debug("Size of common List****"+mainList.size());
	return mainList;
}
//************************************************************************************************************//

}//BD Close

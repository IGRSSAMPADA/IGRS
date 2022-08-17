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
package com.wipro.igrs.poa.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.poa.bd.POAAuthenticationFormBD;
import com.wipro.igrs.poa.dto.POAAuthenticationDTO;
import com.wipro.igrs.poa.dto.POAAuthenticationFormDTO;
import com.wipro.igrs.poa.form.POAAuthenticationForm;

public class POAAuthenticationFormBO {
	
	/* class added by Shruti */
	
	POAAuthenticationFormBD poaBD =null;
	

	
	private Logger logger = (Logger) Logger.getLogger(POAAuthenticationFormBO.class);
	
	
	public ArrayList getpoaTypeList() throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getpoaTypeList();
	}
	public ArrayList getecodePoaTypeList() throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getecodePoaTypeList();
	}
	public ArrayList getawrdrcountryList() throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getawrdrcountryList();
	}
	public ArrayList getawrdrstateList(String _countryId) throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getawrdrstateList(_countryId);
	}
	public ArrayList getawrdrdistrictList(String _stateId) throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getawrdrdistrictList(_stateId);
	}
	public ArrayList getacptrcountryList() throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getacptrcountryList();
	}
	public ArrayList getacptrstateList(String _countryId1) throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getacptrstateList(_countryId1);
	}
	public ArrayList getacptrdistrictList(String _stateId1) throws Exception{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getacptrdistrictList(_stateId1);
	}
	public String getInsertionOfPOADetails(POAAuthenticationFormDTO poaDTO) throws Exception
	{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getInsertionOfPOADetails(poaDTO);
	}
	public String getSRName(String userid) throws Exception
	{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getSRName(userid);
	}
	public ArrayList getSROName(POAAuthenticationFormDTO poaDTO) throws Exception
	{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getSROName(poaDTO);
	}
	public String getSystemDate()
	{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getSystemDate();
	}
	public String getCurrentYear() throws Exception
	{
		POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
		return poaBD.getCurrentYear();
	}
	
	 public synchronized ArrayList getPoaViewDetails(POAAuthenticationFormDTO poaDTO)
     throws NullPointerException,Exception{
		 
		  ArrayList poaList = null;
		  try{
			  logger.info("BO POA SEARCH-->");
			poaList = new ArrayList();   	
			POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
			poaList = poaBD.getPoaViewDetails(poaDTO);
			if(poaList != null){
				return poaList;
			}
		  }catch (NullPointerException ne) {
				logger.info("NullPointer Exception at getPoaViewDetails in BD " + ne);
			  }catch (Exception e) {
				logger.info(" Exception at getPoaViewDetails in BD " + e);
			  }
		  return poaList;
}
	 
	 public synchronized ArrayList getPoaDetails(String _poaId)
     throws NullPointerException,Exception{
		 
		  ArrayList poaList = null;
		  try{
			  logger.info("BD POA SEARCH-->");
			poaList = new ArrayList();   	
			
			poaList = poaBD.getPoaDetails(_poaId);
			if(poaList != null){
				return poaList;
			}
		  }catch (NullPointerException ne) {
				logger.info("NullPointer Exception at GetPoaDetails in BD " + ne);
			  }catch (Exception e) {
				logger.info(" Exception at GetPoaDetails in BD " + e);
			  }
		  return poaList;
		  
}
//*********************************added by SIMRAN****************************************************//	
/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAApplicantDetlsForView(String poaAuthNo) throws Exception {
	POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
	ArrayList list =  poaBD.getPOAApplicantDetlsForView(poaAuthNo);
	return list;
		
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAAwardeeDetlsForView(String poaAuthNo) throws Exception {
	POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
	return poaBD.getPOAAwardeeDetlsForView(poaAuthNo);
	
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOAAcceptorDetlsForView(String poaAuthNo) throws Exception {
	POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
	return poaBD.getPOAAcceptorDetlsForView(poaAuthNo);
}

/**
 * 
 * @param poaAuthNo
 * @return ArrayList
 * @throws Exception
 */
public ArrayList getPOACommonDetlsForView(String poaAuthNo) throws Exception {
	POAAuthenticationFormBD poaBD =new POAAuthenticationFormBD();
	return poaBD.getPOACommonDetlsForView(poaAuthNo);
}
//**********************************************end added by SIMRAN**************************************************//	 
}// BO close

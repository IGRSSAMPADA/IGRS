/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.bd;




import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.util.CommonUtil;


/**
* 
* CaveatsDelegate.java <br>
* CaveatsDelegate <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CaveatsDelegate {

	/**
	 * 
	 */
	CaveatsDAO objCaveatDAO= new CaveatsDAO();

	public CaveatsDelegate() {
	}

	private Logger logger = (Logger) Logger.getLogger(CaveatsDelegate.class);

	/**
	 * @param _caveatsDto
	 * @param payAmount 
	 * @return
	 * @throws Exception
	 */
	public boolean logCaveats(CaveatsDTO _caveatsDto,String userId,String functionId, String payAmount) throws Exception {
		logger.debug("IN BD logCaveats");
//		CommonUtil cUtil = new CommonUtil();
		CaveatsBO bo = new CaveatsBO();
//		String txnIdAuto = cUtil.getAutoId("IGRS_CAVEAT_TXN", "TRANSACTION_ID",
//				"CTXN");
//		_caveatsDto.setReferenceID(txnIdAuto);
//		String[] param1 = new String[19];
//		param1[0] = txnIdAuto;
//		param1[1] = _caveatsDto.getBankCourtName();
//		param1[2] = _caveatsDto.getBankCourtAddress();
//		param1[3] = _caveatsDto.getBankCourtRepName();
//		param1[4] = _caveatsDto.getCountryId();
//		param1[5] = _caveatsDto.getStateId();
//		param1[6] = _caveatsDto.getDistrictId();
//		param1[7] = _caveatsDto.getBankCourtPostalCode();
//		param1[8] = _caveatsDto.getBankCourtPhoneNumber();
//		param1[9] = _caveatsDto.getCaveatId();
//		param1[10] = _caveatsDto.getStayOrderNo();
//		param1[11] = _caveatsDto.getStayOrderDetails();
//		param1[12] = _caveatsDto.getCaveatDetails();
//		param1[13] = _caveatsDto.getDocumentName();
//		param1[14] = _caveatsDto.getDocUrl();
//		param1[15] = _caveatsDto.getRemarks();
//		param1[16] = _caveatsDto.getRegistrationNumber();
//		param1[17] = "LOGGED";
//		param1[18] = _caveatsDto.getLoggedIn();
//		
//		String param2[] = new String[2];
//		param2[0] = txnIdAuto;
//		param2[1] = _caveatsDto.getPropertyTxnId();
//		
//		File file=new File(_caveatsDto.getDocUrl());
//		String fileName=file.getName();

		return (bo.logCaveats(_caveatsDto,userId,functionId,payAmount));
	}

	/**
	 * @param _refId
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO releaseCaveat(String _refId,String language) throws Exception {
		logger.debug("IN BD releaseCaveat");
		ArrayList list = null;
		CaveatsDTO retVal = null;
		try {
			list = searchByAll(_refId, "", "LOGGED", "", "",language);
			retVal = (CaveatsDTO) list.get(0);
			searchByRefID(retVal,language);
//			CaveatsBO bo = new CaveatsBO();
//			CaveatsDTO cavDTO = new CaveatsDTO();
//			list = bo.findCaveatBO(_refId);
//			if (!list.isEmpty()) {
//				logger.info("If List is not Empty");
//				ArrayList fieldList = new ArrayList();
//				fieldList = (ArrayList) list.get(0);
//				cavDTO.setReferenceID((String) fieldList.get(0));
//				cavDTO.setBankCourtName((String) fieldList.get(1));
//				cavDTO.setBankCourtAddress((String) fieldList.get(2));
//				cavDTO.setBankCourtRepName((String) fieldList.get(3));
//				cavDTO.setBankCourtCountry((String) fieldList.get(4));
//				cavDTO.setBankCourtStateName((String) fieldList.get(5));
//				cavDTO.setCityDistrict((String) fieldList.get(6));
//				cavDTO.setBankCourtPostalCode((String) fieldList.get(7));
//				cavDTO.setBankCourtPhoneNumber((String) fieldList.get(8));
//				cavDTO.setTypeOfCaveat((String) fieldList.get(9));
//				cavDTO.setStayOrderNo((String) fieldList.get(10));
//				cavDTO.setStayOrderDetails((String) fieldList.get(11));
//				cavDTO.setCaveatDetails((String) fieldList.get(12));
//				cavDTO.setDocumentName((String) fieldList.get(13));
//				cavDTO.setDocUrl((String) fieldList.get(14));
//				cavDTO.setRemarks((String) fieldList.get(15));
//				cavDTO.setRegistrationNumber((String) fieldList.get(16));
//				cavDTO.setFlag((String) fieldList.get(17));
//				cavDTO.setLoggedDate((String) fieldList.get(18));
//				cavDTO.setReleaseDate((String) fieldList.get(19));
//				cavDTO.setReasonForRelease((String) fieldList.get(20));
//				cavDTO.setRemarksForRelease((String) fieldList.get(21));
//			} else {
//				cavDTO = null;
//			}
//			return cavDTO;
		} catch (Exception e) {
			logger.error(e);
//			return null;
		}
		return retVal;
	}
	
	

	
	public String ivrsCaveatsCharges(String eRegNumber, String propID)
	{
		
		CaveatsDAO dao = new CaveatsDAO();
		String ivrs = "";
		boolean boo=false;
		ArrayList li = dao.getivrsCaveatsCharges(eRegNumber,propID);
		for(int i=0;i<li.size();i++)
		{
			ArrayList m = (ArrayList) li.get(0);
			String status = m.get(0).toString();
			if(status.equalsIgnoreCase("LOGGED"))
			{
				boo = true;
				break;
				
			}
			
		}
		if(boo)
		{
			return "LOGGED~Caveat is logged on this property";
		}
		else
		{
			return "Released~No Caveat is logged on this property";
		}
		
	}
	/**
	 * @param _refId
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO releaseCvtBankByRefId(String _refId) throws Exception {
		logger.debug("IN BD releaseCvtBankByRefId");
		ArrayList list = null;
		try {
			CaveatsBO bo = new CaveatsBO();
			CaveatsDTO cavDTO = new CaveatsDTO();
//			list = bo.releaseCvtBankByRefIdBO(_refId);
//			if (!list.isEmpty()) {
//				logger.info("If List is not Empty");
//				ArrayList fieldList = new ArrayList();
//				fieldList = (ArrayList) list.get(0);
//				cavDTO.setReferenceID((String) fieldList.get(0));
//				cavDTO.setOttNumber((String) fieldList.get(1));
//				cavDTO.setLoanAccountNumber((String) fieldList.get(2));
//				cavDTO.setLoanStartDate((String) fieldList.get(3));
//				cavDTO.setLoanEndDate((String) fieldList.get(4));
//				cavDTO.setLoanAmount(Float.parseFloat(String.valueOf(fieldList
//						.get(5))));
//				cavDTO.setSecuredAmount(Float.parseFloat(String
//						.valueOf(fieldList.get(6))));
//				cavDTO.setNoOfInatalls(Integer.parseInt(String
//						.valueOf(fieldList.get(7))));
//				cavDTO.setNameOfInsti((String) fieldList.get(8));
//				cavDTO.setAddressOfInsti((String) fieldList.get(9));
//				cavDTO.setNameOfBankPerson((String) fieldList.get(10));
//				cavDTO.setDocumentName((String) fieldList.get(11));
//				cavDTO.setDocUrl((String) fieldList.get(12));
//				cavDTO.setRemarks((String) fieldList.get(13));
//				cavDTO.setRegistrationNumber((String) fieldList.get(14));
//				cavDTO.setFlag((String) fieldList.get(15));
//				cavDTO.setLoggedDate((String) fieldList.get(16));
//				cavDTO.setMobOfBankPerson((String) fieldList.get(17));
//				cavDTO.setEmailOfBankPerson((String) fieldList.get(18));
//			} else {
//				cavDTO = null;
//			}
			return cavDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * @param _refid
	 * @param _regNo
	 * @param _frDate
	 * @param _toDate
	 * @return
	 * @throws Exception
	 */
	public ArrayList releaseSearchByAll(String _refid,
			String _regNo, String _frDate, String _toDate) throws Exception {
		logger.debug("IN BD releaseSearchByAll");
		CaveatsBO bo = new CaveatsBO();
		ArrayList listOfCaveats = new ArrayList();
		String searchFields[] = new String[4];
		searchFields[0] = "" + _refid;
		searchFields[1] = "" + _regNo;
		searchFields[2] = "" + _frDate;
		searchFields[3] = "" + _toDate;
		try {
			listOfCaveats = bo.releaseSearchByAllBO(searchFields);
		} catch (NullPointerException e) {
			logger.error(e);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return listOfCaveats;
	}

	/**
	 * @param _refid
	 * @param _RegNo
	 * @param _frDate
	 * @param _toDate
	 * @return
	 * @throws Exception
	 */
	public ArrayList relsCavtBankByAll(String _refid,
			String _RegNo, String _frDate, String _toDate) throws Exception {
		logger.debug("IN BD relsCavtBankByAll");
		CaveatsBO bo = new CaveatsBO();
		ArrayList listOfCaveats = new ArrayList();
		String searchFields[] = new String[4];
		searchFields[0] = "" + _refid;
		searchFields[1] = "" + _RegNo;
		searchFields[2] = "" + _frDate;
		searchFields[3] = "" + _toDate;
		try {

			listOfCaveats = bo.relsCavtBankByAllBO(searchFields);
		} catch (NullPointerException e) {
			logger.error(e);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return listOfCaveats;
	}

	/**
	 * @param _regNo
	 * @return
	 * @throws Exception
	 */
	public ArrayList releaseByRegNO(String _regNo) throws Exception {
		logger.debug("IN BD releaseByRegNO");
		CaveatsBO bo = new CaveatsBO();
		ArrayList list = new ArrayList();
		try {

			list = bo.releaseByRegnoBO(_regNo);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return list;
	}

	// $13 Method for searching Registration Id
	/**
	 * @param _regNo
	 * @param language 
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchRegId(String _regNo, String language) throws Exception {
		logger.debug("IN BD searchRegId");
		ArrayList dataSet, row;
		ArrayList retList = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			dataSet = bo.searchRegIdBO(_regNo);
			if (dataSet != null && dataSet.size() > 0) {
				CaveatsDTO cdto;
				for (int iLoop = 0; iLoop < dataSet.size(); iLoop++) {
					row = (ArrayList) dataSet.get(iLoop);
					cdto = new CaveatsDTO();
					cdto.setRegistrationNumber(row.get(0).toString());
					String prop_id = row.get(1).toString();
					char [] pr = prop_id.toCharArray();
					if(pr.length<16)
					prop_id = "0"+prop_id;
					
					cdto.setPropertyTxnId(prop_id);
					cdto.setPropertyTypeId(row.get(2).toString());
					String propType = row.get(3).toString();
				if(language.equalsIgnoreCase("en"))
				{
					cdto.setPropertyTypeLabel(row.get(3).toString());
				}
				else
				{
					if(propType.equalsIgnoreCase("PLOT"))
						cdto.setPropertyTypeLabel("भूखंड");
					else if(propType.equalsIgnoreCase("AGRICULTURAL LAND"))
						cdto.setPropertyTypeLabel("कृषि भूमि");
					else if (propType.equalsIgnoreCase("BUILDING"))
						cdto.setPropertyTypeLabel("बिल्डिंग");
					
				}
					cdto.setValId(row.get(4).toString());
					retList.add(cdto);
				}
				dataSet.clear();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retList;
	}
	
	// End
	/**
	 * @param caveatDTO
	 * @return
	 * @throws Exception
	 */
	public boolean updateFlag(CaveatsDTO caveatDTO) throws Exception {
		CaveatsBO bo = new CaveatsBO();
		logger.debug("IN BD updateFlag");
//		String[] param = new String[]{
//		caveatDTO.getReasonForRelease(),
//		caveatDTO.getRemarksForRelease(),
//		caveatDTO.getLoggedIn(),
//		caveatDTO.getReferenceID(),
//		caveatDTO.getReleaseDocName()};
		try {

			boolean log = bo.modifyFlagBO(caveatDTO);
			return log;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	
	public boolean insertSpDocDetls1 (CaveatsForm fm) throws Exception
	{
		return CaveatsDAO.insertSpDocDetls1(fm);
	}
	
	
////
	/*
	public boolean updatePayFlag(CaveatsDTO caveatDTO) throws Exception {
		CaveatsBO bo = new CaveatsBO();
		logger.debug("IN BD updateFlag");
//		String[] param = new String[]{
//		caveatDTO.getReasonForRelease(),
//		caveatDTO.getRemarksForRelease(),
//		caveatDTO.getLoggedIn(),
//		caveatDTO.getReferenceID(),
//		caveatDTO.getReleaseDocName()};
		try {

			boolean log = bo.updateFlagBO(caveatDTO);
			return log;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	
	
	
	
	
	
	*/
	
	
	
	// $12 Method for updating status for Released Caveat
	/**
	 * @param _caveatsDto
	 * @return
	 * @throws Exception
	 */
	public boolean relBankUpdation(CaveatsDTO _caveatsDto) throws Exception {
		logger.debug("IN BD relBankUpdation");
		CaveatsBO bo = new CaveatsBO();
		String[] param = new String[4];
		param[0] = _caveatsDto.getReasonForRelease();
		param[1] = _caveatsDto.getRemarksForRelease();
		param[2] = _caveatsDto.getLoggedIn();
		param[3] = _caveatsDto.getReferenceID();

		try {

			return bo.relBankUpdationBO(param);
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	// $12 End
	/**
	 * @param cDTO
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO searchByRefID(CaveatsDTO cDTO,String language) throws Exception {
		logger.debug("IN BD searchByRefID");
		ArrayList list = new ArrayList();
		ArrayList selectedProps = new ArrayList();
		CaveatsDTO tmp = null;
		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchByRefidBO(cDTO.getReferenceID(),language);
			if(list != null && list.isEmpty()==false) {
				for (int iLoop = 0; iLoop < list.size(); iLoop++) {
					ArrayList row  = (ArrayList) list.get(iLoop);
					for (int jLoop = 0; jLoop < row.size(); jLoop++) {
						tmp = new CaveatsDTO();
						tmp.setRegistrationNumber(row.get(0).toString());
						tmp.setPropertyTxnId(row.get(1).toString());
						tmp.setPropertyTypeId(row.get(2).toString());
						tmp.setPropertyTypeLabel(row.get(3).toString());
						
					}selectedProps.add(tmp);
				}
			}
			cDTO.setSelectedItems(selectedProps);
		} catch (Exception e) {
			logger.error(e);
		}
		return cDTO;
	}
	
	/**
	 * @param cDTO
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO searchBankByRefID(CaveatsDTO cDTO) throws Exception {
		logger.debug("IN BD searchBankByRefID");
		try {
			CaveatsBO bo = new CaveatsBO();
			bo.searchForOttBO(cDTO);
//			cDTO.setSelectedItems(selectedProps);
		} catch (Exception e) {
			logger.error(e);
		}
		return cDTO;
	}
	
	/**
	 * @param cDTO
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO searchBankPropertyByRefID(CaveatsDTO cDTO) throws Exception {
		logger.debug("IN BD searchBankByRefID");
		try {
			CaveatsBO bo = new CaveatsBO();
			bo.searchBankPropertyByRefID(cDTO);
//			cDTO.setSelectedItems(selectedProps);
		} catch (Exception e) {
			logger.error(e);
		}
		return cDTO;
	}
	/**
	 * @param _refid
	 * @param _RegNo
	 * @param _status
	 * @param _frDate
	 * @param _toDate
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchByAll(String _refid, String _RegNo,
			String _status, String _frDate, String _toDate,String language) throws Exception {
		logger.debug("IN BD searchByAll");
		ArrayList list = new ArrayList();
		String searchFields[] = new String[5];
		searchFields[0] = _refid;
		searchFields[1] = _RegNo;
		searchFields[2] = _status;
		searchFields[3] = _frDate;
		searchFields[4] = _toDate;

		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchByAllBO(searchFields,language);
		} catch (Exception e) {
			logger.error(e);
		}
		
		return list;
	}
	
		/**
		 * @param _refid
		 * @param _RegNo
		 * @param _status
		 * @param _frDate
		 * @param _toDate
		 * @param userID
		 * @return
		 * @throws Exception
		 */
		public ArrayList searchBankAll(String _refid, String _RegNo,
			String _status, String _frDate, String _toDate, String userID,String language) throws Exception {
		logger.debug("IN BD searchByAll");
		ArrayList list = new ArrayList();
		String searchFields[] = new String[5];
		searchFields[0] = _refid;
		searchFields[1] = _RegNo;
		searchFields[2] = _status;
		searchFields[3] = _frDate;
		searchFields[4] = _toDate;

		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchBankAllBO(searchFields,userID,language);
		} catch (Exception e) {
			logger.error(e);
		}
		
		return list;
	}
	/**
	 * @param _regNo
	 * @param _pin
	 * @return
	 * @throws Exception
	 */
	public ArrayList searchForPin(String _regNo, String _pin) throws Exception {
		logger.debug("IN BD searchForPin");
		ArrayList records = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			records = bo.searchForPinBO(_regNo, _pin);
		} catch (Exception e) {
			logger.error(e);
			return records;
		}
		return records;
	}

	/**
	 * @param _regNo
	 * @param _pin
	 * @return
	 * @throws Exception
	 */
	public CaveatsDTO insertott(String _regNo, String _pin) throws Exception {
		logger.debug("IN BD insertott");
		CaveatsBO bo = new CaveatsBO();
		CommonUtil cUtil = new CommonUtil();
		String ottauto = cUtil.getAutoId("IGRS_CAVEATS_OTT", "OTT", "OTT");
		CaveatsDTO insertion = bo.insertOttBO(_regNo, _pin, ottauto);
		if (insertion != null) {
			return insertion;
		} else
			return null;
	}

	/**
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean searchForOtt(CaveatsDTO dto) throws Exception {
		logger.debug("IN BD searchForOtt");
		boolean retVal = false;
		try {
			CaveatsBO bo = new CaveatsBO();
			retVal = bo.searchForOttBO(dto);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @param _bankdto
	 * @return
	 * @throws Exception
	 */
	public boolean insertCaveatByBank(CaveatsDTO _bankdto) throws Exception {
		logger.debug("IN BD insertCaveatByBank");
		CaveatsBO bo = new CaveatsBO();
		String[] param = new String[19];
		CommonUtil cUtil = new CommonUtil();
		String txnIdAuto = cUtil.getAutoId("IGRS_CAVEAT_BANK_TXN",
				"CAVEAT_BANK_TXN_ID", "BNKC");
		_bankdto.setReferenceID(txnIdAuto);
		param[0] = txnIdAuto;
		param[1] = _bankdto.getOttNumber();
		param[2] = _bankdto.getLoanAccountNumber();
		param[3] = _bankdto.getLoanStartDate();
		param[4] = _bankdto.getLoanEndDate();
		param[5] = "" + _bankdto.getLoanAmount();
		param[6] = "" + _bankdto.getSecuredAmount();
		param[7] = "" + _bankdto.getAmountOfInstall();
		param[8] = _bankdto.getNameOfInsti();
		param[9] = _bankdto.getAddressOfInsti();
		param[10] = _bankdto.getNameOfBankPerson();
		param[11] = _bankdto.getDocumentName();
		param[12] = "";// ""+_bankdto.getNoOfInatalls();
		param[13] = _bankdto.getRemarks();
		param[14] = _bankdto.getRegistrationNumber();
		param[15] = "LOGGED";
		param[16] = _bankdto.getMobOfBankPerson();
		param[17] = _bankdto.getEmailOfBankPerson();
		param[18] = _bankdto.getLoggedIn();
		return (bo.insertCaveatBankBO(param));
	}

	/**
	 * @param _dto
	 * @return
	 * @throws Exception
	 */
	public boolean updateOttStatus(CaveatsDTO _dto) throws Exception {
		logger.debug("IN BD updateOttStatus");
		CaveatsBO bo = new CaveatsBO();
		return (bo.updateOttStatusBO(_dto.getRegistrationNumber(), _dto
				.getOttNumber()));
	}

	
	/**
	 * @param registrationNumber
	 * @return
	 */
	public ArrayList getPropertyTxnIDList(String registrationNumber) {
		ArrayList retVal = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			ArrayList result = bo.getPropertyTxnIDList(registrationNumber);
			ArrayList row = null;
			CaveatsDTO dto;
			if(result != null) {
				for (Object item : result) {
					row = (ArrayList) item;
					//PROPERTY_TXN_ID REGISTRATION_ID PIN 
					dto = new CaveatsDTO();
					dto.setPropertyTxnId((String) row.get(0));
					dto.setRegistrationNumber((String) row.get(1));
					dto.setPinNumberInDB((String) row.get(2));
					dto.setPinFlag((String) row.get(3));

					retVal.add(dto);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @param registrationNumber
	 * @return
	 */
	public ArrayList getAvailablePINList(String registrationNumber) {
		return new ArrayList();
	}

	/**
	 * @param attribName
	 * @return
	 */
	public String getOTTValidity(String attribName) {
		String retVal="NA";

		CaveatsBO bo = new CaveatsBO();
		retVal = bo.getOTTValidity(attribName);
		
		return retVal;
	}

	/**
	 * @param caveatfrm
	 */
	public void generateOTTNumbers(CaveatsForm caveatfrm) {
		CaveatsBO bo = new CaveatsBO();
		bo.generateOTTNumbers(caveatfrm);
	}

	/**
	 * @param selectedList
	 * @return
	 */
	public ArrayList<String> getActiveOTTPropertyMapping(ArrayList selectedList) {
		ArrayList<String> retVal = new ArrayList<String>();
		try {
			CaveatsBO bo = new CaveatsBO();
			retVal= bo.getActiveOTTPropertyMapping(selectedList);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @return
	 */
	public ArrayList getCaveatTypeList(String language) {
		ArrayList retVal = new ArrayList<String>();
		try {
			CaveatsBO bo = new CaveatsBO();
			retVal= bo.getCaveatTypeList(language);
			
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @param dto
	 * @return
	 */
	public boolean logBankCaveat(CaveatsDTO dto) {
		CaveatsBO bo = new CaveatsBO();
		return bo.logBankCaveat(dto);
	}

	/**
	 * @param dto
	 * @return
	 */
	public boolean searchBankCaveat(CaveatsDTO dto) {
		CaveatsBO bo = new CaveatsBO();
		return bo.searchBankCaveat(dto);
	}

	/**
	 * @param userID
	 * @return
	 */
	public String checkBankRoleForUser(String userID) {
		CaveatsBO bo = new CaveatsBO();
		return bo.checkBankRoleForUser(userID);
	}

	/**
	 * @param cDTO
	 * @param userID
	 * @return
	 */
	public ArrayList<OTTDetailDTO> getOTTListing(CaveatsDTO cDTO, String userID,String language) {
		ArrayList retVal = new ArrayList<OTTDetailDTO>();
		try {
			//tmpPopOttList(retVal);
			CaveatsBO bo = new CaveatsBO();
			retVal = bo.getOTTListing(cDTO, userID,language);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	/**
	 * @param retVal
	 */
	/**
	 * @param retVal
	 */
	private void tmpPopOttList(ArrayList retVal) {
		OTTDetailDTO item = new OTTDetailDTO();
		item.setSerialNo("1");
		item.setOttNumber("OTT1");
		item.setRegistrationNo("Regn1234");
		item.setGeneratedDate("19/01/2012");
		item.setExpirationDate("20/02/2014");
		item.setStatus("Expired");
		retVal.add(item );
		item = new OTTDetailDTO();
		item.setSerialNo("2");
		item.setOttNumber("OTT2");
		item.setRegistrationNo("Regn1234");
		item.setGeneratedDate("29/11/2012");
		item.setExpirationDate("20/02/2015");
		item.setStatus("Active");
		retVal.add(item );
		item = new OTTDetailDTO();
		item.setSerialNo("3");
		item.setOttNumber("OTT3");
		item.setRegistrationNo("Regn1234");
		item.setGeneratedDate("22/01/2013");
		item.setExpirationDate("20/02/2020");
		item.setStatus("Deactivated");
		retVal.add(item );
	}

	/**
	 * @param registrationNumber
	 * @return
	 */
	public int getStayOrderLoggedCount(String registrationNumber) {
		int retVal = 0;
		try {
			CaveatsBO bo = new CaveatsBO();
			retVal = bo.getStayOrderLoggedCount(registrationNumber);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}

	public ArrayList getFee(String functionId, String serId, String loggedIn) {
		ArrayList retVal = new ArrayList();
		try {
			//tmpPopOttList(retVal);
			CaveatsBO bo = new CaveatsBO();
			retVal = bo.getFee(functionId, serId, loggedIn);
		} catch (Exception e) {
			logger.error(e);
		}
		return retVal;
	}
//ashima
	public ArrayList getPendingApps(String userId,String language)
	{
		return objCaveatDAO.getPendingApps(userId,language);
	}
	public ArrayList getTransidDetails(String transid,String language) throws Exception
	{
		return objCaveatDAO.getTransidDetails(transid,language);
	}
	 
	public ArrayList getPartial(String userId)
	{
		return objCaveatDAO.getPartial(userId);
	}

	 
	  public static ArrayList getDetails(String officeId){
		  CaveatsDAO cDAO;
		  ArrayList alist = new ArrayList();
		try {
			cDAO = new CaveatsDAO();
		
		  
		  
		  alist=cDAO.getDetails(officeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	  
				  return alist;
	  }

	public void getotherDetails(CaveatsDTO tmpDTO) {
		CaveatsDAO cDAO = new CaveatsDAO();
		
		try {
			cDAO.getOtherDetails(tmpDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
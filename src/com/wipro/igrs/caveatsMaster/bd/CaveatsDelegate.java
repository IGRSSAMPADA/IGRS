package com.wipro.igrs.caveatsMaster.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.caveatsMaster.bo.CaveatsBO;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.exception.CaveatsException;
import com.wipro.igrs.util.CommonUtil;

public class CaveatsDelegate {

	public CaveatsDelegate() {
	}

	private Logger logger = (Logger) Logger.getLogger(CaveatsDelegate.class);

	public boolean logCaveats(CaveatsDTO _caveatsDto) throws Exception {
		logger.debug("IN BD logCaveats");
		CommonUtil cUtil = new CommonUtil();
		CaveatsBO bo = new CaveatsBO();
		String txnIdAuto = cUtil.getAutoId("IGRS_CAVEAT_TXN", "TRANSACTION_ID",
				"CTXN");
		_caveatsDto.setReferenceID(txnIdAuto);
		String[] param1 = new String[19];
		param1[0] = txnIdAuto;
		param1[1] = _caveatsDto.getBankCourtName();
		param1[2] = _caveatsDto.getBankCourtAddress();
		param1[3] = _caveatsDto.getBankCourtRepName();
		param1[4] = _caveatsDto.getCountryId();
		param1[5] = _caveatsDto.getStateId();
		param1[6] = _caveatsDto.getDistrictId();
		param1[7] = _caveatsDto.getBankCourtPostalCode();
		param1[8] = _caveatsDto.getBankCourtPhoneNumber();
		param1[9] = _caveatsDto.getCaveatId();
		param1[10] = _caveatsDto.getStayOrderNo();
		param1[11] = _caveatsDto.getStayOrderDetails();
		param1[12] = _caveatsDto.getCaveatDetails();
		param1[13] = _caveatsDto.getDocumentName();
		param1[14] = _caveatsDto.getDocUrl();
		param1[15] = _caveatsDto.getRemarks();
		param1[16] = _caveatsDto.getRegistrationNumber();
		param1[17] = "LOGGED";
		param1[18] = _caveatsDto.getLoggedIn();
		String param2[] = new String[2];
		param2[0] = txnIdAuto;
		param2[1] = _caveatsDto.getPropertyTxnId();

		return (bo.logCaveatsBO(param1, param2));
	}

	// ////////////mona
	public ArrayList listCaveats() throws Exception {
		logger.debug("IN BD listCaveats");
		try {
			CaveatsBO boRef = new CaveatsBO();
			return boRef.listCaveatsBO();

		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean deleteCaveatsMasterBD(String id) throws Exception {
		logger.debug("IN BD deleteCaveatsMasterBD");
		boolean result = false;
		try {
			CaveatsBO boRef = new CaveatsBO();

			result = boRef.deleteCaveatsMasterBO(id);

		} catch (Exception e) {
			logger.error(e);

		}
		return result;
	}

	public boolean updateCaveatsMasterBD(String[] param, String originalName)
			throws Exception, CaveatsException {
		logger.debug("IN BD updateCaveatsMasterBD");
		boolean result = false;
		// try
		// {
		CaveatsBO boRef = new CaveatsBO();

		result = boRef.updateCaveatsMasterBO(param, originalName);

		/*
		 * } catch (Exception e) { logger.error(e); }
		 */
		return result;
	}

	public CaveatsDTO retrieveCavietsByNameBD(String name) {
		logger.debug("IN BD retrieveCavietsByNameBD");
		try {
			CaveatsBO boRef = new CaveatsBO();
			return boRef.retrieveCavietsByNameBO(name);

		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public boolean insertCaveatsMasterBD(String[] param)
			throws CaveatsException, Exception {
		logger.debug("IN BD listCaveats");
		boolean result = false;

		CaveatsBO boRef = new CaveatsBO();

		result = boRef.insertCaveatsMasterBO(param);

		return result;
	}

	// ///////
	public CaveatsDTO releaseCaveat(String _refId) throws Exception {
		logger.debug("IN BD releaseCaveat");
		ArrayList list = null;
		try {
			CaveatsBO bo = new CaveatsBO();
			CaveatsDTO cavDTO = new CaveatsDTO();
			list = bo.findCaveatBO(_refId);
			if (!list.isEmpty()) {
				logger.info("If List is not Empty");
				ArrayList fieldList = new ArrayList();
				fieldList = (ArrayList) list.get(0);
				cavDTO.setReferenceID((String) fieldList.get(0));
				cavDTO.setBankCourtName((String) fieldList.get(1));
				cavDTO.setBankCourtAddress((String) fieldList.get(2));
				cavDTO.setBankCourtRepName((String) fieldList.get(3));
				cavDTO.setBankCourtCountry((String) fieldList.get(4));
				cavDTO.setBankCourtStateName((String) fieldList.get(5));
				cavDTO.setCityDistrict((String) fieldList.get(6));
				cavDTO.setBankCourtPostalCode((String) fieldList.get(7));
				cavDTO.setBankCourtPhoneNumber((String) fieldList.get(8));
				cavDTO.setTypeOfCaveat((String) fieldList.get(9));
				cavDTO.setStayOrderNo((String) fieldList.get(10));
				cavDTO.setStayOrderDetails((String) fieldList.get(11));
				cavDTO.setCaveatDetails((String) fieldList.get(12));
				cavDTO.setDocumentName((String) fieldList.get(13));
				cavDTO.setDocUrl((String) fieldList.get(14));
				cavDTO.setRemarks((String) fieldList.get(15));
				cavDTO.setRegistrationNumber((String) fieldList.get(16));
				cavDTO.setFlag((String) fieldList.get(17));
				cavDTO.setLoggedDate((String) fieldList.get(18));
				cavDTO.setReleaseDate((String) fieldList.get(19));
				cavDTO.setReasonForRelease((String) fieldList.get(20));
				cavDTO.setRemarksForRelease((String) fieldList.get(21));
			} else {
				cavDTO = null;
			}
			return cavDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public CaveatsDTO releaseCvtBankByRefId(String _refId) throws Exception {
		logger.debug("IN BD releaseCvtBankByRefId");
		ArrayList list = null;
		try {
			CaveatsBO bo = new CaveatsBO();
			CaveatsDTO cavDTO = new CaveatsDTO();
			list = bo.releaseCvtBankByRefIdBO(_refId);
			if (!list.isEmpty()) {
				logger.info("If List is not Empty");
				ArrayList fieldList = new ArrayList();
				fieldList = (ArrayList) list.get(0);
				cavDTO.setReferenceID((String) fieldList.get(0));
				cavDTO.setOttNumber((String) fieldList.get(1));
				cavDTO.setLoanAccountNumber((String) fieldList.get(2));
				cavDTO.setLoanStartDate((String) fieldList.get(3));
				cavDTO.setLoanEndDate((String) fieldList.get(4));
				cavDTO.setLoanAmount(Float.parseFloat(String.valueOf(fieldList
						.get(5))));
				cavDTO.setSecuredAmount(Float.parseFloat(String
						.valueOf(fieldList.get(6))));
				cavDTO.setNoOfInatalls(Integer.parseInt(String
						.valueOf(fieldList.get(7))));
				cavDTO.setNameOfInsti((String) fieldList.get(8));
				cavDTO.setAddressOfInsti((String) fieldList.get(9));
				cavDTO.setNameOfBankPerson((String) fieldList.get(10));
				cavDTO.setDocumentName((String) fieldList.get(11));
				cavDTO.setDocUrl((String) fieldList.get(12));
				cavDTO.setRemarks((String) fieldList.get(13));
				cavDTO.setRegistrationNumber((String) fieldList.get(14));
				cavDTO.setFlag((String) fieldList.get(15));
				cavDTO.setLoggedDate((String) fieldList.get(16));
				cavDTO.setMobOfBankPerson((String) fieldList.get(17));
				cavDTO.setEmailOfBankPerson((String) fieldList.get(18));
			} else {
				cavDTO = null;
			}
			return cavDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList releaseSearchByAll(String _refid, String _regNo,
			String _frDate, String _toDate) throws Exception {
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

	public ArrayList relsCavtBankByAll(String _refid, String _RegNo,
			String _frDate, String _toDate) throws Exception {
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
	public boolean searchRegId(String _regNo) throws Exception {
		logger.debug("IN BD searchRegId");
		ArrayList list = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchRegIdBO(_regNo);
			if (!list.isEmpty()) {
				logger.info("List Is Not Empty");
				return true;
			} else {
				logger.info("List Is Empty");
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
		return false;
	}

	// End
	public boolean updateFlag(CaveatsDTO _caveatsDto) throws Exception {
		CaveatsBO bo = new CaveatsBO();
		logger.debug("IN BD updateFlag");
		String[] param = new String[4];
		param[0] = _caveatsDto.getReasonForRelease();
		param[1] = _caveatsDto.getRemarksForRelease();
		param[2] = _caveatsDto.getLoggedIn();
		param[3] = _caveatsDto.getReferenceID();
		try {

			boolean log = bo.modifyFlagBO(param);
			return log;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}

	// $12 Method for updating status for Released Caveat
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
	public CaveatsDTO searchByRefID(String _refid) throws Exception {
		logger.debug("IN BD searchByRefID");
		ArrayList list = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchByRefidBO(_refid);
			CaveatsDTO cavDTO = new CaveatsDTO();
			if (!(list.isEmpty())) {
				logger.info("If List Is Not Empty");
				ArrayList fieldList = new ArrayList();
				fieldList = (ArrayList) list.get(0);

				cavDTO.setReferenceID((String) fieldList.get(0));
				cavDTO.setBankCourtName((String) fieldList.get(1));
				cavDTO.setBankCourtAddress((String) fieldList.get(2));
				cavDTO.setBankCourtRepName((String) fieldList.get(3));
				cavDTO.setBankCourtCountry((String) fieldList.get(4));
				cavDTO.setBankCourtStateName((String) fieldList.get(5));
				cavDTO.setCityDistrict((String) fieldList.get(6));
				cavDTO.setBankCourtPostalCode((String) fieldList.get(7));
				cavDTO.setBankCourtPhoneNumber((String) fieldList.get(8));
				cavDTO.setTypeOfCaveat((String) fieldList.get(9));
				cavDTO.setStayOrderNo((String) fieldList.get(10));
				cavDTO.setStayOrderDetails((String) fieldList.get(11));
				cavDTO.setCaveatDetails((String) fieldList.get(12));
				cavDTO.setDocumentName((String) fieldList.get(13));
				cavDTO.setDocUrl((String) fieldList.get(14));
				cavDTO.setRemarks((String) fieldList.get(15));
				cavDTO.setRegistrationNumber((String) fieldList.get(16));
				cavDTO.setFlag((String) fieldList.get(17));
				cavDTO.setLoggedDate((String) fieldList.get(18));
				cavDTO.setReleaseDate((String) fieldList.get(19));
				cavDTO.setReasonForRelease((String) fieldList.get(20));
				cavDTO.setRemarksForRelease((String) fieldList.get(21));
				cavDTO.setPropertyTxnId((String) fieldList.get(22));
			} else {
				logger.info("List is Empty");
				cavDTO = null;
			}
			return cavDTO;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	public ArrayList searchByAll(String _refid, String _RegNo, String _status,
			String _frDate, String _toDate) throws Exception {
		logger.debug("IN BD searchByAll");
		ArrayList list = new ArrayList();
		String searchFields[] = new String[5];
		searchFields[0] = "" + _refid;
		searchFields[1] = "" + _RegNo;
		searchFields[2] = "" + _status;
		searchFields[3] = "" + _frDate;
		searchFields[4] = "" + _toDate;

		try {
			CaveatsBO bo = new CaveatsBO();
			list = bo.searchByAllBO(searchFields);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
		return list;
	}

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

	public ArrayList searchForOtt(String _regNo, String ott) throws Exception {
		logger.debug("IN BD searchForOtt");
		ArrayList records = new ArrayList();
		try {
			CaveatsBO bo = new CaveatsBO();
			records = bo.searchForOttBO(_regNo, ott);
		} catch (Exception e) {
			logger.error(e);
			return records;
		}
		return records;
	}

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

	public boolean updateOttStatus(CaveatsDTO _dto) throws Exception {
		logger.debug("IN BD updateOttStatus");
		CaveatsBO bo = new CaveatsBO();
		return (bo.updateOttStatusBO(_dto.getRegistrationNumber(), _dto
				.getOttNumber()));
	}
}
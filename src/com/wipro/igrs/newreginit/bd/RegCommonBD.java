package com.wipro.igrs.newreginit.bd;

//import java.text.DateFormat;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.AadharEsign.util.GenerateFormVIAENGHINDI;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.aadhar.common.util.AadharUtil;
import com.wipro.igrs.clr.services.FormVIBPdfGeneration;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.cybertehsil.forms.dto.PropDetailDto;
import com.wipro.igrs.newPropvaluation.bo.PropertyValuationBO;
import com.wipro.igrs.newdutycalculation.dto.UserEnterableDTO;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dao.RegCommonDAO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.newreginit.dto.DeclarationDTO;
import com.wipro.igrs.newreginit.dto.FormOneDTO;
import com.wipro.igrs.newreginit.dto.FormUserEnterableDTO;
import com.wipro.igrs.newreginit.dto.PartyDetailsDTO;
import com.wipro.igrs.newreginit.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newreginit.form.RegCompletionForm;
import com.wipro.igrs.util.DateToWords;
import com.wipro.igrs.util.PropertiesFileReader;

/*import com.wipro.igrs.reginit.sql.RegCommonSQL;
 import com.wipro.igrs.util.CommonUtil;*/
public class RegCommonBD {
	RegCommonDAO commonDao = null;
	// String eStamp = null;
	// String eCode = null;
	// String regInit = null;
	// static int reg_txn_id_count = 0;
	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(RegCommonBD.class);

	public RegCommonBD() {
		commonDao = new RegCommonDAO();
		// eStamp = eStampIDgenerator();
		// eCode = eCodeIDgenerator();
		// regInit = regInitIDgenerator();
	}

	/**
	 * for getting all Applicant Types
	 * 
	 * @return ArrayList
	 */
	public ArrayList getAppType(String languageLocale, int poaHolderFlag, int role) {
		return commonDao.getAppType(languageLocale, poaHolderFlag, role);
	}

	/**
	 * for getting all Country details
	 * 
	 * @return ArrayList
	 */
	public ArrayList getCountry(String languageLocale) {
		return commonDao.getCountry(languageLocale);
	}

	/**
	 * for getting all State details
	 * 
	 * @param param
	 * @return ArrayList
	 */
	public ArrayList getState(String param, String languageLocale) {
		return commonDao.getState(param, languageLocale);
	}

	/**
	 * for getting all District details
	 * 
	 * @param stateId
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId, String languageLocale) {
		return commonDao.getDistrict(stateId, languageLocale);
	}

	/**
	 * for getting all ID Proof details
	 * 
	 * @return ArrayList
	 */
	public ArrayList getIdProf(String languageLocale) {
		return commonDao.getIdProf(languageLocale);
	}

	/**
	 * for getting All Deed Details
	 * 
	 * @return ArrayList
	 */
	public ArrayList getDeedType(String languageLocale) {
		return commonDao.getDeedType(languageLocale);
	}

	// following method created by Roopam
	public ArrayList getDeedTypeNew(String flag) {
		return commonDao.getDeedTypeNew(flag);
	}

	/**
	 * for getting Instruments list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getInstrument(String deed) {
		return commonDao.getInstrument(deed);
	}

	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * 
	 * @param deed
	 * @param deed1
	 * @return ArrayList
	 */
	public ArrayList getExemption(String deed, String deed1) {
		return commonDao.getExemption(deed, deed1);
	}

	/**
	 * form setting selected Exemptions Details(in confirmation page)
	 * 
	 * @param exemptions
	 * @return ArrayList
	 */
	public ArrayList setExemptionList(String exemptions) {
		ArrayList list = new ArrayList();
		String[] temp = exemptions.split(",");
		CommonDTO dto = null;
		for (int i = 0; i < temp.length; i++) {
			dto = new CommonDTO();
			dto.setId(temp[i]);
			dto.setChecked("yes");
			i = i + 1;
			dto.setName(temp[i]);
			list.add(dto);
		}
		return list;
	}

	/**
	 * form setting selected Exemptions Details(in form)
	 * 
	 * @param exemption
	 * @param list
	 * @return ArrayList
	 */
	public ArrayList getExemptions(ArrayList exemption, ArrayList list) {
		CommonDTO dto = null;
		for (int i = 0; i < exemption.size(); i++) {
			dto = (CommonDTO) exemption.get(i);
			for (int j = 0; j < list.size(); j++) {
				CommonDTO dto1 = (CommonDTO) list.get(j);
				if (dto.getId().equalsIgnoreCase(dto1.getId())) {
					dto.setChecked("checked");
				}
			}
		}
		return exemption;
	}

	/**
	 * for setting Transaction Party details
	 * 
	 * @param tparties
	 * @return ArrayList
	 */
	public ArrayList setTpartiesList(String tparties) {
		ArrayList list = new ArrayList();
		String[] temp = tparties.split(",");
		CommonDTO dto = null;
		int j = 1;
		if (temp.length > 2)
			for (int i = 0; i < temp.length; i++) {
				dto = new CommonDTO();
				dto.setId(j + ".");
				dto.setName(temp[i]);
				dto.setMiddleName(temp[++i]);
				dto.setLastName(temp[++i]);
				list.add(dto);
				j++;
			}
		return list;
	}

	/**
	 * for setting organization details
	 * 
	 * @param organisation
	 * @return ArrayList
	 */
	public ArrayList setOrganisationList(String organisation) {
		ArrayList list = new ArrayList();
		String[] temp = organisation.split(",");
		CommonDTO dto = null;
		if (temp.length > 3)
			for (int i = 0; i < temp.length; i++) {
				dto = new CommonDTO();
				dto.setId(temp[i]);
				dto.setName(temp[++i]);
				dto.setMiddleName(temp[++i]);
				dto.setLastName(temp[++i]);
				list.add(dto);
			}
		return list;
	}

	/**
	 * for eStamp Id generation
	 * 
	 * @return eStampID
	 */
	private String eStampIDgenerator() {
		return "eStamp" + new Date().getTime();
	}

	/**
	 * for eCode Id generation
	 * 
	 * @return eCode
	 */
	private String eCodeIDgenerator() {
		return "eCode" + new Date().getTime();
	}

	/**
	 * for Registration Initiation Id generation
	 * 
	 * @return regInitID
	 */
	private String regInitIDgenerator() {
		return "RegInit" + new Date().getTime();
	}

	/**
	 * for calculating Stamp Duty
	 * 
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param marketValue
	 * @return StampDuty
	 */
	/*
	 * public String getStampDuty(String deed,String instrument,String
	 * exemption, String marketValue){ return
	 * commonDao.getStampDuty(deed,instrument,exemption,marketValue); }
	 */
	/**
	 * for calculating other fee for stamp duty
	 * 
	 * @param function_id
	 * @param user_id
	 * @param service_id
	 * @return other fee
	 */
	/*
	 * public ArrayList getOthersDuty(String function_id, String service_id,
	 * String user_id) { return commonDao.getOthersDuty(function_id, service_id,
	 * user_id); }
	 */
	public PropertyValuationDTO getPropertyValuationDTO(PropertyValuationDTO useDTO) throws Exception {
		String[] district = useDTO.getDistrictID() == null ? null : useDTO.getDistrictID().split("~");
		if (district != null && district.length == 2) {
			useDTO.setDistrict(district[1]);
			useDTO.setDistrictID(district[0]);
		}
		String[] tehsil = useDTO.getTehsilID() == null ? null : useDTO.getTehsilID().split("~");
		if (tehsil != null && tehsil.length == 2) {
			useDTO.setTehsil(tehsil[1]);
			useDTO.setTehsilID(tehsil[0]);
		}
		String[] ward = useDTO.getWardId() == null ? null : useDTO.getWardId().split("~");
		if (ward != null && ward.length == 2) {
			useDTO.setWard(ward[1]);
			useDTO.setWardId(ward[0]);
		}
		String[] mohalla = useDTO.getMahallaId() == null ? null : useDTO.getMahallaId().split("~");
		if (mohalla != null && mohalla.length == 2) {
			useDTO.setMahalla(mohalla[1]);
			useDTO.setMahallaId(mohalla[0]);
		}
		String[] property = useDTO.getPropertyTypeID() == null ? null : useDTO.getPropertyTypeID().split("~");
		if (property != null && property.length == 2) {
			useDTO.setPropertyType(property[1]);
			useDTO.setPropertyTypeID(property[0]);
		}
		String[] muncipal = useDTO.getMunicipalBodyID() == null ? null : useDTO.getMunicipalBodyID().split("~");
		if (muncipal != null && muncipal.length == 2) {
			useDTO.setMunicipalBody(muncipal[1]);
			useDTO.setMunicipalBodyID(muncipal[0]);
		}
		String[] area = useDTO.getAreaId() == null ? null : useDTO.getAreaId().split("~");
		if (area != null && area.length == 2) {
			useDTO.setAreaType(area[1]);
			useDTO.setAreaId(area[0]);
		}
		String[] usePlot = useDTO.getUsePlotId() == null ? null : useDTO.getUsePlotId().split("~");
		if (usePlot != null && usePlot.length == 3) {
			useDTO.setUsePlot(usePlot[1]);
			useDTO.setUsePlotId(usePlot[0]);
			logger.debug(usePlot[1]);
		}
		IGRSCommon common = new IGRSCommon();
		// if (useDTO.getTotalSqMeter() != null) {
		useDTO.setTotalSqMeter(Double.parseDouble(common.getCurrencyFormat(useDTO.getTotalSqMeter())));
		// }
		// if(useDTO.getConsiderAmt() != null) {
		useDTO.setConsiderAmt(Double.parseDouble(common.getCurrencyFormat(useDTO.getConsiderAmt())));
		// }
		useDTO.setMktValue(common.getCurrencyFormat(useDTO.getMarketValue()));
		/*
		 * String[] subclause = useDTO.getHdnSubClauseName().split(",");
		 * if(subclause != null && subclause.length ==2) {
		 * useDTO.setSubClause(subclause[1]); }
		 */
		return useDTO;
	}

	/**
	 * @auther : ROOPAM MEHTA
	 * @Created : 16 Nov.2012
	 * @Description : checks weather the given deed requires property valuation
	 *              or duty calculation
	 * @Paratmeter : String
	 */
	/*
	 * public boolean propertyValReqCheck(String deed) { return
	 * commonDao.propertyValReqCheck(deed); }
	 */
	/**
	 * for getting party type list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyType(int deed, int inst, String languageLocale) {
		return commonDao.getPartyType(deed, inst, languageLocale);
	}

	/**
	 * insertApplicantAndPropertyDetails Returns boolean value in order to check
	 * the insertion.
	 * 
	 * @param form
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertApplicantAndPropertyDetails(RegCommonForm form) throws Exception // main
	{
		boolean insertsuccess = false;
		// FOLLOWING CODE FOR OWNER DETAILS
		// String ownerDetails[] = new String[19];
		int partyId = Integer.parseInt(form.getPartyType());
		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());
		if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {/*
																						 * 
																						 * ownerDetails[
																						 * 0
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getHidnRegTxnId
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * reg
																						 * txn
																						 * id
																						 * if
																						 * (
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * .
																						 * equals
																						 * (
																						 * ""
																						 * )
																						 * ||
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * .
																						 * equals
																						 * (
																						 * null
																						 * )
																						 * )
																						 * {
																						 * ownerDetails
																						 * [
																						 * 1
																						 * ]
																						 * =
																						 * "2"
																						 * ;
																						 * /
																						 * /
																						 * individual
																						 * party
																						 * type
																						 * ownerDetails
																						 * [
																						 * 2
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerName
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * first
																						 * name
																						 * ownerDetails
																						 * [
																						 * 12
																						 * ]
																						 * =
																						 * ""
																						 * ;
																						 * /
																						 * /
																						 * authorized
																						 * person
																						 * name
																						 * }
																						 * else
																						 * {
																						 * ownerDetails
																						 * [
																						 * 1
																						 * ]
																						 * =
																						 * "1"
																						 * ;
																						 * /
																						 * /
																						 * organization
																						 * party
																						 * type
																						 * ownerDetails
																						 * [
																						 * 2
																						 * ]
																						 * =
																						 * ""
																						 * ;
																						 * /
																						 * /
																						 * first
																						 * name
																						 * ownerDetails
																						 * [
																						 * 12
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerName
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * authorized
																						 * person
																						 * /
																						 * /
																						 * name
																						 * }
																						 * 
																						 * ownerDetails
																						 * [
																						 * 3
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerGendar
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * gender
																						 * /
																						 * /
																						 * ownerDetails
																						 * [
																						 * 4
																						 * ]
																						 * =
																						 * UserRegistrationBD
																						 * .
																						 * getOracleDate
																						 * (
																						 * form
																						 * .
																						 * getOwnerDay
																						 * (
																						 * )
																						 * ,
																						 * /
																						 * /
																						 * form
																						 * .
																						 * getOwnerMonth
																						 * (
																						 * )
																						 * ,
																						 * form
																						 * .
																						 * getOwnerYear
																						 * (
																						 * )
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * age
																						 * to
																						 * DOB
																						 * ownerDetails
																						 * [
																						 * 4
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerAge
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * DOB
																						 * to
																						 * age
																						 * ownerDetails
																						 * [
																						 * 5
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerNationality
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * nationality
																						 * 
																						 * /
																						 * /
																						 * String
																						 * address
																						 * =
																						 * form
																						 * .
																						 * getOwnerAddress
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * address
																						 * =
																						 * address
																						 * .
																						 * replace
																						 * (
																						 * ","
																						 * ,
																						 * " "
																						 * )
																						 * ;
																						 * ownerDetails
																						 * [
																						 * 6
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerAddress
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * address
																						 * 
																						 * ownerDetails
																						 * [
																						 * 7
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerPhno
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * phone
																						 * number
																						 * if
																						 * (
																						 * form
																						 * .
																						 * getOwnerEmailID
																						 * (
																						 * )
																						 * !=
																						 * null
																						 * &&
																						 * !
																						 * form
																						 * .
																						 * getOwnerEmailID
																						 * (
																						 * )
																						 * .
																						 * equalsIgnoreCase
																						 * (
																						 * ""
																						 * )
																						 * &&
																						 * !
																						 * form
																						 * .
																						 * getOwnerEmailID
																						 * (
																						 * )
																						 * .
																						 * equalsIgnoreCase
																						 * (
																						 * "null"
																						 * )
																						 * )
																						 * {
																						 * ownerDetails
																						 * [
																						 * 8
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerEmailID
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * email
																						 * id
																						 * }
																						 * else
																						 * {
																						 * ownerDetails
																						 * [
																						 * 8
																						 * ]
																						 * =
																						 * ""
																						 * ;
																						 * }
																						 * ownerDetails
																						 * [
																						 * 9
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerListID
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * photo
																						 * proof
																						 * type
																						 * id
																						 * 
																						 * /
																						 * /
																						 * String
																						 * proof
																						 * =
																						 * form
																						 * .
																						 * getOwnerIdno
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * proof
																						 * =
																						 * proof
																						 * .
																						 * replace
																						 * (
																						 * ","
																						 * ,
																						 * ""
																						 * )
																						 * ;
																						 * ownerDetails
																						 * [
																						 * 10
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerIdno
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * photo
																						 * proof
																						 * number
																						 * if
																						 * (
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * !=
																						 * null
																						 * &&
																						 * !
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * .
																						 * equalsIgnoreCase
																						 * (
																						 * ""
																						 * )
																						 * &&
																						 * !
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * .
																						 * equalsIgnoreCase
																						 * (
																						 * "null"
																						 * )
																						 * )
																						 * {
																						 * ownerDetails
																						 * [
																						 * 11
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getOwnerOgrName
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * organization
																						 * name
																						 * }
																						 * else
																						 * {
																						 * ownerDetails
																						 * [
																						 * 11
																						 * ]
																						 * =
																						 * ""
																						 * ;
																						 * }
																						 * ownerDetails
																						 * [
																						 * 13
																						 * ]
																						 * =
																						 * getTransactingPartyIdSeq
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * party
																						 * transaction
																						 * /
																						 * /
																						 * id
																						 * ownerDetails
																						 * [
																						 * 14
																						 * ]
																						 * =
																						 * "O"
																						 * ;
																						 * /
																						 * /
																						 * is
																						 * applicant
																						 * O
																						 * -
																						 * Owner
																						 * ownerDetails
																						 * [
																						 * 15
																						 * ]
																						 * =
																						 * ""
																						 * ;
																						 * /
																						 * /
																						 * share
																						 * of
																						 * property
																						 * ownerDetails
																						 * [
																						 * 16
																						 * ]
																						 * =
																						 * Integer
																						 * .
																						 * toString
																						 * (
																						 * RegInitConstant
																						 * .
																						 * ROLE_OWNER_SELF
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * party
																						 * role
																						 * /
																						 * /
																						 * type
																						 * id
																						 * ownerDetails
																						 * [
																						 * 17
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getHidnUserId
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * CREATED
																						 * BY
																						 * 
																						 * ownerDetails
																						 * [
																						 * 18
																						 * ]
																						 * =
																						 * form
																						 * .
																						 * getAddressOwnerOutMp
																						 * (
																						 * )
																						 * ;
																						 * /
																						 * /
																						 * address
																						 * of
																						 * owner
																						 * outside
																						 * mp
																						 */
		}
		// FOLLOWING CODE FOR APPLICANT DETAILS
		String[] param;
		if (!form.getAge().isEmpty() && form.getAge() != null) {
			int partyAge = 0;
			partyAge = Integer.parseInt(form.getAge());
			if (partyAge >= 18) {
				param = new String[62];
			} else {
				param = new String[63];
			}
		} else {
			param = new String[62];
		}
		if (form.getPartyType().equalsIgnoreCase(Integer.toString(RegInitConstant.ROLE_POA_ACCEPTER)) && form.getHdnDeclareShareCheck().equalsIgnoreCase("Y")) {
			param[28] = "0";
		} else {
			param[28] = "";
		}
		param[49] = "";
		param[51] = ""; // for universal deeds executant claimant flag
		param[60] = "";
		param[61] = "";
		if (form.getListAdoptedParty().equalsIgnoreCase("3")) {
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[8] = "1";
			param[9] = "1";
			param[10] = form.getInddistrict();
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y";
			param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[32] = "";
			param[56] = "Y";
			param[57] = form.getNameOfOfficial();
			param[58] = form.getDesgOfOfficial();
			param[59] = form.getAddressOfOfficial();
			param[60] = form.getAddressGovtOffclOutMp();
			param[61] = "";
		} else if (form.getListAdoptedParty().equalsIgnoreCase("1")) {
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			// param[5] = "";
			param[6] = ""; // AGE
			param[7] = "";
			param[8] = form.getCountry();
			param[9] = form.getStatename();
			param[10] = form.getDistrict();
			// String address=form.getOrgaddress();
			// address=address.replace(",", " ");
			param[11] = form.getOrgaddress();
			param[12] = "";
			param[13] = form.getPhno();
			param[14] = form.getMobno();
			param[15] = "";
			param[16] = "";
			param[17] = "";
			param[18] = form.getOgrName();
			param[19] = form.getAuthPerName();
			// param[20] = "";
			param[21] = "";
			param[22] = ""; // CASTE TO CATEGORY
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y";
			param[25] = ""; // OCCUPATION
			param[26] = "";
			param[27] = form.getRelationWithOwner();
			// param[28] = form.getShareOfProp();
			param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = "";
			// if (ownerDetails[13] == null) {
			param[32] = "";
			// } else {
			// param[32] = ownerDetails[13];
			// } // self referencing key of owner in case of poa holder
			param[33] = "";
			param[34] = "";
			param[35] = "";
			param[36] = ""; // ST RADIO
			param[37] = ""; // ST CERTIFICATE FIELD
			param[38] = ""; // ST UPLOAD file path
			/*
			 * param[39]=""; //PHOTO_PROOF_PATH param[40]="";
			 * //NOT_AFFD_EXEC_PATH param[41]=""; //EXEC_PHOTO_PATH
			 * param[42]=""; //NOT_AFFD_ATTR_PATH param[43]="";
			 * //ATTR_PHOTO_PATH param[44]=""; //CLAIMNT_PHOTO_PATH
			 * param[45]=""; //PAN_FORM_60_PATH param[46]=""; //SR OFFICE NAME
			 * param[47]=""; //POA REG NO param[48]=""; //POA REG DATE
			 */
			param[50] = Integer.toString(form.getAuthPerRelationship()); // relationship
			// type
			param[5] = form.getAuthPerGendar();
			param[20] = form.getAuthPerFatherName();
			param[52] = form.getAuthPerCountry();
			param[53] = form.getAuthPerStatename();
			param[54] = form.getAuthPerDistrict();
			String authAddress = form.getAuthPerAddress();
			authAddress = authAddress;
			param[55] = authAddress;
			param[60] = form.getAddressOrgOutMp();
			param[61] = form.getAddressAuthPerOutMp();
		} else {
			// for individual
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = form.getFname();
			param[3] = form.getMname();
			param[4] = form.getLname();
			param[5] = form.getGendar();
			// param[6] = UserRegistrationBD.getOracleDate(form.getUserDay(),
			// form.getUserMonth(), form.getUserYear()); //DOB
			param[6] = form.getAge(); // AGE
			param[7] = form.getNationality();
			param[8] = form.getIndcountry();
			param[9] = form.getIndstatename();
			param[10] = form.getInddistrict();
			// String address=form.getIndaddress();
			// address=address.replace(",", " ");
			param[11] = form.getIndaddress();
			param[12] = form.getPostalCode();
			param[13] = form.getIndphno();
			param[14] = form.getIndmobno();
			if (form.getEmailID() != null && !form.getEmailID().equalsIgnoreCase("") && !form.getEmailID().equalsIgnoreCase("null")) {
				param[15] = form.getEmailID();
			} else {
				param[15] = "";
			}
			param[16] = form.getListID();// adhar no 7
			// String proof=form.getIdno();
			// proof=proof.replace(",","");
			// Aadhar changes - ankit
			/*
			 * if ("7".equalsIgnoreCase(form.getListID())) { param[17] =
			 * AadharUtil.encryptWithAES256(form.getIdno()); } else {
			 */
			param[17] = form.getIdno();
			// }
			param[18] = ""; // organization name
			param[19] = ""; // authorized person name
			param[20] = form.getFatherName();
			param[21] = form.getMotherName();
			param[22] = form.getIndCategory(); // CASTE TO CATEGORY
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y"; // IS APPLICANT
			param[25] = form.getOccupationId(); // OCCUPATION
			param[26] = form.getIndDisability();
			param[27] = form.getRelationWithOwner();
			// param[28] = form.getShareOfProp();
			param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = form.getSpouseName();
			// if (ownerDetails[13] == null) {
			param[32] = "";
			// } else {
			// param[32] = ownerDetails[13]; // self referencing key of owner
			// in case of poa holder
			// }
			if (form.getIndDisabilityDesc() != null && !form.getIndDisabilityDesc().equalsIgnoreCase("") && !form.getIndDisabilityDesc().equalsIgnoreCase("null")) {
				param[33] = form.getIndDisabilityDesc(); // DISABILITY
				// DESCRIPTION
			} else {
				param[33] = "";
			}
			param[34] = form.getIndMinority(); // MINORITY
			param[35] = form.getIndPovertyLine(); // POVERTY LINE
			if (form.getIndCategory().equalsIgnoreCase("1")) {
				param[36] = form.getIndScheduleArea(); // ST RADIO
				if (form.getIndScheduleArea().equalsIgnoreCase("N")) {
					param[37] = form.getPermissionNo(); // ST CERTIFICATE
					// number
					param[38] = form.getFilePath(); // ST UPLOAD file path
				} else {
					param[37] = "";
					param[38] = "";
				}
			} else {
				param[36] = "";
				param[37] = "";
				param[38] = "";
			}
			param[50] = Integer.toString(form.getRelationship());
			param[52] = "";
			param[53] = "";
			param[54] = "";
			param[55] = "";
			param[60] = form.getAddressIndOutMp();
			param[61] = "";
		}
		param[39] = form.getU2FilePath(); // PHOTO_PROOF_PATH
		param[40] = ""; // NOT_AFFD_EXEC_PATH
		param[41] = ""; // EXEC_PHOTO_PATH
		param[42] = ""; // NOT_AFFD_ATTR_PATH
		param[43] = ""; // ATTR_PHOTO_PATH
		param[44] = ""; // CLAIMNT_PHOTO_PATH
		param[45] = ""; // PAN_FORM_60_PATH
		param[46] = ""; // SR OFFICE NAME
		param[47] = ""; // POA REG NO
		param[48] = ""; // POA REG DATE
		if (!(form.getListAdoptedParty().equalsIgnoreCase("3"))) {
			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
				// param[41] = form.getU4FilePath(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				param[40] = form.getU3FilePath(); // NOT_AFFD_EXEC_PATH
				param[45] = form.getU10FilePath(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
				RegCommonBO bo = new RegCommonBO();
				// param[41] = form.getU7FilePath(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				// param[42] = form.getU5FilePath(); // NOT_AFFD_ATTR_PATH
				param[42] = "";
				param[43] = form.getU6FilePath(); // ATTR_PHOTO_PATH
				param[46] = form.getSrOfficeName(); // SR OFFICE NAME
				param[47] = form.getPoaRegNo(); // POA REG NO
				if (form.getDatePoaReg() != null && !form.getDatePoaReg().equalsIgnoreCase("")) {
					param[48] = bo.convertCalenderDate(form.getDatePoaReg()); // POA
					// REG
					// DATE
				}
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
				// param[44] = form.getU8FilePath(); // CLAIMNT_PHOTO_PATH
				param[44] = ""; // CLAIMNT_PHOTO_PATH
				param[45] = form.getU11FilePath(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
				param[43] = form.getU9FilePath(); // ATTR_PHOTO_PATH
			}
		}
		// anuj - start with index 56- 4 columns- conditionally
		/*
		 * if("Y".equalsIgnoreCase((String)form.getOfficialCheck())){
		 * 
		 * form.setGovtOfcCheck("Y");
		 * 
		 * param[56]="Y"; param[57]=(String)form.getNameOfOfficial();
		 * param[58]=(String)form.getDesgOfOfficial();
		 * param[59]=(String)form.getAddressOfOfficial(); } else{
		 * 
		 * form.setGovtOfcCheck("N");
		 * 
		 * param[56]="N"; param[57]=""; param[58]=""; param[59]=""; }
		 */
		// FOLLOWING CODE FOR TRANSACTION DETAILS
		String deed[] = new String[10];
		deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID
		deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
		deed[2] = Integer.toString(form.getDeedID()); // DEED_ID
		deed[3] = Integer.toString(form.getInstID()); // INSTRUMENTS_ID
		deed[4] = form.getHidnUserId(); // CREATED_BY
		deed[5] = ""; // REF_VAL_TXN_ID
		deed[6] = Integer.toString(form.getDuty_txn_id()); // DUTY TXN ID
		if (form.getFromAdjudication() == 1) {
			deed[7] = "A";
		} else {
			deed[7] = "";
		}
		if (form.getCancellationLabel() != null && !form.getCancellationLabel().equalsIgnoreCase("")) {
			deed[8] = "Y";
		} else {
			deed[8] = "N";
		}
		deed[9] = form.getTransCheck();
		if (form.getDeedID() == RegInitConstant.DEED_EXCHANGE || form.getDeedID() == RegInitConstant.DEED_PARTITION_PV || form.getExchangePropertyList().size() == 1) {
			// for exchange deed
			/*
			 * // FOLLOWING CODE FOR TRANSACTION DETAILS String deed[] = new
			 * String[8]; deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID
			 * deed[1] = RegInitConstant.STATUS_APP_SAVED; //
			 * REGISTRATION_TXN_STATUS deed[2] =
			 * Integer.toString(form.getDeedID()); // DEED_ID deed[3] =
			 * Integer.toString(form.getInstID()); // INSTRUMENTS_ID deed[4] =
			 * form.getHidnUserId(); // CREATED_BY deed[5] = ""; //
			 * REF_VAL_TXN_ID deed[6] = Integer.toString(form.getDuty_txn_id());
			 * // DUTY TXN ID if (form.getFromAdjudication() == 1) { deed[7] =
			 * "A"; } else { deed[7] = ""; }
			 */
			ArrayList<CommonDTO> list;
			/*
			 * if(form.getDeedID() == RegInitConstant.DEED_EXCHANGE ||
			 * form.getDeedID() == RegInitConstant.DEED_PARTITION_PV){
			 */
			list = form.getExchangePropertyList();
			/*
			 * }else{ list = form.getAgriPropertyList(); }
			 */
			ArrayList<CommonDTO> paramList1 = new ArrayList<CommonDTO>();
			ArrayList<CommonDTO> paramList2 = new ArrayList<CommonDTO>();
			CommonDTO row_list;
			CommonDTO paramDto1;
			CommonDTO paramDto2;
			for (int i = 0; i < list.size(); i++) {
				row_list = (CommonDTO) list.get(i);
				paramDto1 = new CommonDTO();
				paramDto2 = new CommonDTO();
				// FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION
				// TABLES
				RegCommonBO bo = new RegCommonBO();
				String[] valPropDetls = bo.getPropertyDetailsFromValuation(row_list.getValuationId().trim());
				// FOLLOWING CODE FOR PROPERTY DETAILS
				paramDto1.setPropertyId(row_list.getPropertyId().trim());
				paramDto1.setRegTxnId(form.getHidnRegTxnId());
				paramDto1.setMarketValue(valPropDetls[0].trim());
				paramDto1.setAreaTypeId(valPropDetls[1].trim());
				paramDto1.setGovBodyId(valPropDetls[2].trim());
				paramDto1.setPropTypeId(valPropDetls[3].trim());
				// paramDto1.setL1TypeId(valPropDetls[4].trim());
				paramDto1.setL1TypeId("");
				/*
				 * if (valPropDetls[5].trim().equalsIgnoreCase("null") ||
				 * valPropDetls[5].trim().equalsIgnoreCase("")) {
				 */
				paramDto1.setL2TypeId("");
				/*
				 * } else { paramDto1.setL2TypeId(valPropDetls[5].trim()); }
				 */
				/*
				 * if (valPropDetls[6].trim().equalsIgnoreCase("null") ||
				 * valPropDetls[6].trim().equalsIgnoreCase("")) {
				 */
				paramDto1.setAreaUnitId("");
				/*
				 * } else { paramDto1.setAreaUnitId(valPropDetls[6].trim()); }
				 */
				// paramDto1.setArea(valPropDetls[7].trim());
				paramDto1.setArea("");
				paramDto1.setDistId(valPropDetls[8].trim());
				paramDto1.setTehsilId(valPropDetls[9].trim());
				paramDto1.setWardId(valPropDetls[10].trim());
				paramDto1.setMohalaId(valPropDetls[11].trim());
				paramDto1.setUserId(form.getHidnUserId());
				paramDto1.setValuationId(row_list.getValuationId().trim());
				paramDto1.setSysMv(valPropDetls[12].trim());
				paramList1.add(paramDto1);
				// FOLLOWING CODE FOR PROPERTY & TRANSACTING PARTY MAPPING
				if (form.getDeedID() == RegInitConstant.DEED_EXCHANGE || form.getDeedID() == RegInitConstant.DEED_PARTITION_PV) {
					if (partyId == RegInitConstant.ROLE1_OWNER_POA_HOLDER || partyId == RegInitConstant.ROLE1_OWNER_SELF || partyId == RegInitConstant.ROLE1_OWNER_AUTHENTICATED_POA_HOLDER) {
						if (row_list.getPartyType().trim().equalsIgnoreCase(RegInitConstant.PARTY_1_EN)) {
							// if
							// (row_list.getId().trim().equalsIgnoreCase("1")) {
							paramDto2.setRegTxnId(form.getHidnRegTxnId());
							paramDto2.setPropertyId(row_list.getPropertyId().trim());
							paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
							paramDto2.setUserId(form.getHidnUserId());
							paramList2.add(paramDto2);
						}
					} else if (partyId == RegInitConstant.ROLE2_OWNER_POA_HOLDER || partyId == RegInitConstant.ROLE2_OWNER_SELF || partyId == RegInitConstant.ROLE2_OWNER_AUTHENTICATED_POA_HOLDER) {
						if (row_list.getPartyType().trim().equalsIgnoreCase(RegInitConstant.PARTY_2_EN)) {
							// if
							// (row_list.getId().trim().equalsIgnoreCase("2")) {
							paramDto2.setRegTxnId(form.getHidnRegTxnId());
							paramDto2.setPropertyId(row_list.getPropertyId().trim());
							paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
							paramDto2.setUserId(form.getHidnUserId());
							paramList2.add(paramDto2);
						}
					} else if (partyId == RegInitConstant.ROLE_CO_OWNER_POA_HOLDER || partyId == RegInitConstant.ROLE_CO_OWNER_SELF || partyId == RegInitConstant.ROLE_CO_OWNER_AUTHENTICATED_POA_HOLDER) {
						paramDto2.setRegTxnId(form.getHidnRegTxnId());
						paramDto2.setPropertyId(row_list.getPropertyId().trim());
						paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
						paramDto2.setUserId(form.getHidnUserId());
						paramList2.add(paramDto2);
					}
				} else {
					paramDto2.setRegTxnId(form.getHidnRegTxnId());
					paramDto2.setPropertyId(row_list.getPropertyId().trim());
					paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
					paramDto2.setUserId(form.getHidnUserId());
					paramList2.add(paramDto2);
				}
			}
			String extraDetls[] = setExtraDetlsParam(form);
			insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsMineral(param, deed, paramList1, paramList2, form.getAppOwnerList(), form.getExmpID(), null, extraDetls, form);
		} else {
			// for exchange deed
			// FOLLOWING CODE FOR TRANSACTION DETAILS
			/*
			 * String deed[] = new String[8]; deed[0] = form.getHidnRegTxnId();
			 * // REG_TXN_ID deed[1] = RegInitConstant.STATUS_APP_SAVED; //
			 * REGISTRATION_TXN_STATUS deed[2] =
			 * Integer.toString(form.getDeedID()); // DEED_ID deed[3] =
			 * Integer.toString(form.getInstID()); // INSTRUMENTS_ID deed[4] =
			 * form.getHidnUserId(); // CREATED_BY deed[5] = ""; //
			 * REF_VAL_TXN_ID deed[6] = Integer.toString(form.getDuty_txn_id());
			 * // DUTY TXN ID if (form.getFromAdjudication() == 1) { deed[7] =
			 * "A"; } else { deed[7] = ""; }
			 */
			ArrayList<CommonDTO> list;
			/*
			 * if(form.getDeedID() == RegInitConstant.DEED_EXCHANGE ||
			 * form.getDeedID() == RegInitConstant.DEED_PARTITION_PV){
			 */
			list = form.getExchangePropertyList();
			/*
			 * }else{ list = form.getAgriPropertyList(); }
			 */
			ArrayList<CommonDTO> paramList1 = new ArrayList<CommonDTO>();
			// ArrayList<CommonDTO> paramList2 = new ArrayList<CommonDTO>();
			CommonDTO row_list;
			CommonDTO paramDto1;
			// CommonDTO paramDto2;
			for (int i = 0; i < list.size(); i++) {
				row_list = (CommonDTO) list.get(i);
				paramDto1 = new CommonDTO();
				// paramDto2 = new CommonDTO();
				// FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION
				// TABLES
				RegCommonBO bo = new RegCommonBO();
				String[] valPropDetls = bo.getPropertyDetailsFromValuation(row_list.getValuationId().trim());
				// FOLLOWING CODE FOR PROPERTY DETAILS
				paramDto1.setPropertyId(row_list.getPropertyId().trim());
				paramDto1.setRegTxnId(form.getHidnRegTxnId());
				paramDto1.setMarketValue(valPropDetls[0].trim());
				paramDto1.setAreaTypeId(valPropDetls[1].trim());
				paramDto1.setGovBodyId(valPropDetls[2].trim());
				paramDto1.setPropTypeId(valPropDetls[3].trim());
				// paramDto1.setL1TypeId(valPropDetls[4].trim());
				paramDto1.setL1TypeId("");
				/*
				 * if (valPropDetls[5].trim().equalsIgnoreCase("null") ||
				 * valPropDetls[5].trim().equalsIgnoreCase("")) {
				 */
				paramDto1.setL2TypeId("");
				/*
				 * } else { paramDto1.setL2TypeId(valPropDetls[5].trim()); }
				 */
				/*
				 * if (valPropDetls[6].trim().equalsIgnoreCase("null") ||
				 * valPropDetls[6].trim().equalsIgnoreCase("")) {
				 */
				paramDto1.setAreaUnitId("");
				/*
				 * } else { paramDto1.setAreaUnitId(valPropDetls[6].trim()); }
				 */
				// paramDto1.setArea(valPropDetls[7].trim());
				paramDto1.setArea("");
				paramDto1.setDistId(valPropDetls[8].trim());
				paramDto1.setTehsilId(valPropDetls[9].trim());
				paramDto1.setWardId(valPropDetls[10].trim());
				paramDto1.setMohalaId(valPropDetls[11].trim());
				paramDto1.setUserId(form.getHidnUserId());
				paramDto1.setValuationId(row_list.getValuationId().trim());
				paramDto1.setSysMv(valPropDetls[12].trim());
				paramList1.add(paramDto1);
				// FOLLOWING CODE FOR PROPERTY & TRANSACTING PARTY MAPPING
				/*
				 * if(form.getDeedID() == RegInitConstant.DEED_EXCHANGE ||
				 * form.getDeedID() == RegInitConstant.DEED_PARTITION_PV){ if
				 * (partyId == RegInitConstant.ROLE1_OWNER_POA_HOLDER || partyId
				 * == RegInitConstant.ROLE1_OWNER_SELF) { if
				 * (row_list.getPartyType().trim().equalsIgnoreCase( "Party 1"))
				 * { paramDto2.setRegTxnId(form.getHidnRegTxnId()); paramDto2
				 * .setPropertyId(row_list.getPropertyId().trim());
				 * paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
				 * paramDto2.setUserId(form.getHidnUserId());
				 * paramList2.add(paramDto2); } } else if (partyId ==
				 * RegInitConstant.ROLE2_OWNER_POA_HOLDER || partyId ==
				 * RegInitConstant.ROLE2_OWNER_SELF) { if
				 * (row_list.getPartyType().trim().equalsIgnoreCase( "Party 2"))
				 * { paramDto2.setRegTxnId(form.getHidnRegTxnId()); paramDto2
				 * .setPropertyId(row_list.getPropertyId().trim());
				 * paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
				 * paramDto2.setUserId(form.getHidnUserId());
				 * paramList2.add(paramDto2); } } else if (partyId ==
				 * RegInitConstant.ROLE_OWNER_POA_HOLDER || partyId ==
				 * RegInitConstant.ROLE_OWNER_SELF) {
				 * 
				 * paramDto2.setRegTxnId(form.getHidnRegTxnId());
				 * paramDto2.setPropertyId(row_list.getPropertyId().trim());
				 * paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
				 * paramDto2.setUserId(form.getHidnUserId());
				 * paramList2.add(paramDto2);
				 * 
				 * } }else{ paramDto2.setRegTxnId(form.getHidnRegTxnId());
				 * paramDto2.setPropertyId(row_list.getPropertyId().trim());
				 * paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
				 * paramDto2.setUserId(form.getHidnUserId());
				 * paramList2.add(paramDto2); }
				 */
			}
			String extraDetls[] = setExtraDetlsParam(form);
			insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsMineral(param, deed, paramList1, null, form.getAppOwnerList(), form.getExmpID(), null, extraDetls, form);
			/*
			 * // for other than exchange deed
			 * 
			 * // FOLLOWING CODE FOR TRANSACTION DETAILS String deed[] = new
			 * String[8]; deed[0] = form.getHidnRegTxnId(); if (form.getDeedID()
			 * == RegInitConstant.DEED_TRUST_PV) {
			 * 
			 * if (form.getHdnDeclareShareCheck().equalsIgnoreCase("Y")) { int
			 * share = Integer.parseInt(form.getShareOfProp()); if (share ==
			 * 100) { deed[1] = RegInitConstant.STATUS_TRNS_SAVED; } else {
			 * deed[1] = RegInitConstant.STATUS_APP_SAVED; } } else {
			 * 
			 * if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) { deed[1]
			 * = RegInitConstant.STATUS_APP_SAVED; } else { deed[1] =
			 * RegInitConstant.STATUS_TRNS_SAVED; }
			 * 
			 * }
			 * 
			 * } else { deed[1] = RegInitConstant.STATUS_APP_SAVED; //
			 * REGISTRATION_TXN_STATUS } deed[2] =
			 * Integer.toString(form.getDeedID()); // DEED_ID deed[3] =
			 * Integer.toString(form.getInstID()); // INSTRUMENTS_ID deed[4] =
			 * form.getHidnUserId(); // CREATED_BY deed[5] = ""; //
			 * REF_VAL_TXN_ID deed[6] = ""; if (form.getFromAdjudication() == 1)
			 * { deed[7] = "A"; } else { deed[7] = ""; }
			 * 
			 * // FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION
			 * TABLES RegCommonBO bo = new RegCommonBO(); String[] valPropDetls
			 * = bo.getPropertyDetailsFromValuation(form .getValuationId());
			 * 
			 * // FOLLOWING CODE FOR PROPERTY DETAILS String propDetls[] = new
			 * String[19]; propDetls[0] = form.getPropertyId(); propDetls[1] =
			 * form.getHidnRegTxnId(); propDetls[2] = valPropDetls[0].trim(); //
			 * MARKET_VALUE - FINAL MV propDetls[3] = valPropDetls[1].trim(); //
			 * AREA_TYPE_ID propDetls[4] = valPropDetls[2].trim(); //
			 * GOV_BODY_ID - SUB AREA TYPE ID propDetls[5] =
			 * valPropDetls[3].trim(); // PROP_TYPE_ID propDetls[6] = ""; //
			 * L1_TYPE_ID - NOT TO BE USED if
			 * (valPropDetls[5].trim().equalsIgnoreCase("null")) { propDetls[7]
			 * = null; } else { propDetls[7] = ""; // L2_TYPE_ID - NOT TO BE
			 * USED //} if (valPropDetls[6]!=null) { propDetls[8] = ""; //
			 * AREA_UNIT_ID - REF VAL TXN ID will be used in case of agri land }
			 * else { propDetls[8] = ""; } propDetls[9] = ""; // AREA - VAL TXN
			 * ID propDetls[10] = valPropDetls[8].trim(); // DISTRICT_ID
			 * propDetls[11] = valPropDetls[9].trim(); // TEHSIL_ID
			 * propDetls[12] = valPropDetls[10].trim(); // WARD_ID propDetls[13]
			 * = valPropDetls[11].trim(); // MOHALLA_ID propDetls[14] =
			 * form.getHidnUserId(); propDetls[15] = form.getValuationId();
			 * propDetls[16] = "N"; // PROP_TXN_COMPL_FLAG propDetls[17] =
			 * valPropDetls[0].trim(); // INITIAL_MARKET_VALUE propDetls[18] =
			 * valPropDetls[12].trim(); // System market value - GUIDELINE MV
			 * 
			 * // FOLLOWING CODE FOR PROPERTY AND TRANSACTING PARTY MAPPING
			 * DETAILS String propTransDets[] = new String[4]; propTransDets[0]
			 * = form.getHidnRegTxnId(); propTransDets[1] =
			 * form.getPropertyId(); propTransDets[2] =
			 * form.getPartyRoleTypeId(); propTransDets[3] =
			 * form.getHidnUserId();
			 * 
			 * String extraDetls[] = setExtraDetlsParam(form);
			 * 
			 * 
			 * insertsuccess = commonDao.insrtApplcntTransPartyPropDetls(param,
			 * deed, propDetls, propTransDets, ownerDetails, form .getExmpID(),
			 * null, extraDetls);
			 */
		}
		return insertsuccess;
	}

	/*
	 * public boolean insertPVPropertyDetails(RegCommonForm form) throws
	 * Exception // main { boolean insertsuccess = false;
	 * 
	 * //RegCommonBO commonBo = new RegCommonBO();
	 * 
	 * if (form.getDeedID() == RegInitConstant.DEED_EXCHANGE || form.getDeedID()
	 * == RegInitConstant.DEED_PARTITION_PV || (form.getAgriPropertyList()!=null
	 * && form.getAgriPropertyList().size()>0 )) { // for // exchange // deed
	 * 
	 * // FOLLOWING CODE FOR TRANSACTION DETAILS String deed[] = new String[8];
	 * deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID deed[1] =
	 * RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS deed[2] =
	 * Integer.toString(form.getDeedID()); // DEED_ID deed[3] =
	 * Integer.toString(form.getInstID()); // INSTRUMENTS_ID deed[4] =
	 * form.getHidnUserId(); // CREATED_BY deed[5] = form.getValuationId(); //
	 * REF_VAL_TXN_ID deed[6] = ""; // DUTY TXN ID if
	 * (form.getFromAdjudication() == 1) { deed[7] = "A"; } else { deed[7] = "";
	 * } ArrayList<CommonDTO> list; if(form.getDeedID() ==
	 * RegInitConstant.DEED_EXCHANGE || form.getDeedID() ==
	 * RegInitConstant.DEED_PARTITION_PV){ list =
	 * form.getExchangePropertyList(); }else{ list = form.getAgriPropertyList();
	 * }
	 * 
	 * ArrayList paramList1 = new ArrayList(); //ArrayList paramList2 = new
	 * ArrayList();
	 * 
	 * CommonDTO row_list; CommonDTO paramDto1; //CommonDTO paramDto2;
	 * 
	 * for (int i = 0; i < list.size(); i++) { row_list = (CommonDTO)
	 * list.get(i);
	 * 
	 * paramDto1 = new CommonDTO(); //paramDto2 = new CommonDTO();
	 * 
	 * // FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION // TABLES
	 * RegCommonBO bo = new RegCommonBO(); String[] valPropDetls = bo
	 * .getPropertyDetailsFromValuation(row_list .getValuationId().trim());
	 * 
	 * // FOLLOWING CODE FOR PROPERTY DETAILS
	 * 
	 * paramDto1.setPropertyId(row_list.getPropertyId().trim());
	 * paramDto1.setRegTxnId(form.getHidnRegTxnId());
	 * 
	 * paramDto1.setMarketValue(valPropDetls[0].trim());
	 * paramDto1.setAreaTypeId(valPropDetls[1].trim());
	 * paramDto1.setGovBodyId(valPropDetls[2].trim());
	 * paramDto1.setPropTypeId(valPropDetls[3].trim());
	 * //paramDto1.setL1TypeId(valPropDetls[4].trim());
	 * paramDto1.setL1TypeId(""); if
	 * (valPropDetls[5].trim().equalsIgnoreCase("null") ||
	 * valPropDetls[5].trim().equalsIgnoreCase("")) { paramDto1.setL2TypeId("");
	 * } else { paramDto1.setL2TypeId(valPropDetls[5].trim()); } if
	 * (valPropDetls[6].trim().equalsIgnoreCase("null") ||
	 * valPropDetls[6].trim().equalsIgnoreCase("")) {
	 * paramDto1.setAreaUnitId(""); } else {
	 * paramDto1.setAreaUnitId(valPropDetls[6].trim()); }
	 * 
	 * //paramDto1.setArea(valPropDetls[7].trim()); paramDto1.setArea("");
	 * paramDto1.setDistId(valPropDetls[8].trim());
	 * paramDto1.setTehsilId(valPropDetls[9].trim());
	 * paramDto1.setWardId(valPropDetls[10].trim());
	 * paramDto1.setMohalaId(valPropDetls[11].trim());
	 * 
	 * paramDto1.setUserId(form.getHidnUserId());
	 * paramDto1.setValuationId(row_list.getValuationId().trim());
	 * paramDto1.setSysMv(valPropDetls[12].trim());
	 * 
	 * paramList1.add(paramDto1);
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * insertsuccess = commonDao.insrtPVPropDetlsExchange(deed,
	 * paramList1,form.getExmpID(), null);
	 * 
	 * }else { // for other than exchange deed
	 * 
	 * // FOLLOWING CODE FOR TRANSACTION DETAILS String deed[] = new String[8];
	 * deed[0] = form.getHidnRegTxnId(); if (form.getDeedID() ==
	 * RegInitConstant.DEED_TRUST_PV) {
	 * 
	 * if (form.getHdnDeclareShareCheck().equalsIgnoreCase("Y")) { int share =
	 * Integer.parseInt(form.getShareOfProp()); if (share == 100) { deed[1] =
	 * RegInitConstant.STATUS_TRNS_SAVED; } else { deed[1] =
	 * RegInitConstant.STATUS_APP_SAVED; } } else {
	 * 
	 * if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) { deed[1] =
	 * RegInitConstant.STATUS_APP_SAVED; } else { deed[1] =
	 * RegInitConstant.STATUS_TRNS_SAVED; }
	 * 
	 * }
	 * 
	 * } else { deed[1] = RegInitConstant.STATUS_APP_SAVED; //
	 * REGISTRATION_TXN_STATUS } deed[2] = Integer.toString(form.getDeedID());
	 * // DEED_ID deed[3] = Integer.toString(form.getInstID()); //
	 * INSTRUMENTS_ID deed[4] = form.getHidnUserId(); // CREATED_BY deed[5] =
	 * ""; // REF_VAL_TXN_ID deed[6] = ""; if (form.getFromAdjudication() == 1)
	 * { deed[7] = "A"; } else { deed[7] = ""; }
	 * 
	 * // FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION TABLES
	 * RegCommonBO bo = new RegCommonBO(); String[] valPropDetls =
	 * bo.getPropertyDetailsFromValuation(form .getValuationId());
	 * 
	 * // FOLLOWING CODE FOR PROPERTY DETAILS String propDetls[] = new
	 * String[19]; propDetls[0] = form.getPropertyId(); propDetls[1] =
	 * form.getHidnRegTxnId(); propDetls[2] = valPropDetls[0].trim(); //
	 * MARKET_VALUE - FINAL MV propDetls[3] = valPropDetls[1].trim(); //
	 * AREA_TYPE_ID propDetls[4] = valPropDetls[2].trim(); // GOV_BODY_ID - SUB
	 * AREA TYPE ID propDetls[5] = valPropDetls[3].trim(); // PROP_TYPE_ID
	 * propDetls[6] = ""; // L1_TYPE_ID - NOT TO BE USED if
	 * (valPropDetls[5].trim().equalsIgnoreCase("null")) { propDetls[7] = null;
	 * } else { propDetls[7] = ""; // L2_TYPE_ID - NOT TO BE USED //} if
	 * (valPropDetls[6]!=null) { propDetls[8] = ""; // AREA_UNIT_ID - REF VAL
	 * TXN ID will be used in case of agri land } else { propDetls[8] = ""; }
	 * propDetls[9] = ""; // AREA - VAL TXN ID propDetls[10] =
	 * valPropDetls[8].trim(); // DISTRICT_ID propDetls[11] =
	 * valPropDetls[9].trim(); // TEHSIL_ID propDetls[12] =
	 * valPropDetls[10].trim(); // WARD_ID propDetls[13] =
	 * valPropDetls[11].trim(); // MOHALLA_ID propDetls[14] =
	 * form.getHidnUserId(); propDetls[15] = form.getValuationId();
	 * propDetls[16] = "N"; // PROP_TXN_COMPL_FLAG propDetls[17] =
	 * valPropDetls[0].trim(); // INITIAL_MARKET_VALUE propDetls[18] =
	 * valPropDetls[12].trim(); // System market value - GUIDELINE MV
	 * 
	 * // FOLLOWING CODE FOR PROPERTY AND TRANSACTING PARTY MAPPING DETAILS
	 * String propTransDets[] = new String[4]; propTransDets[0] =
	 * form.getHidnRegTxnId(); propTransDets[1] = form.getPropertyId();
	 * propTransDets[2] = form.getPartyRoleTypeId(); propTransDets[3] =
	 * form.getHidnUserId();
	 * 
	 * String extraDetls[] = setExtraDetlsParam(form);
	 * 
	 * 
	 * insertsuccess = commonDao.insrtPVPropertyDetls(deed,
	 * propDetls,form.getExmpID(), null); }
	 * 
	 * return insertsuccess; }
	 */
	// modifed array add extra field for new instrument - rahul
	public String[] setExtraDetlsParam(RegCommonForm form) {
		RegCommonBO bo = new RegCommonBO();
		String extraDetls[] = new String[62];
		try {
			extraDetls[0] = form.getHidnRegTxnId();
			extraDetls[1] = "";
			extraDetls[2] = "";
			extraDetls[3] = "";
			extraDetls[4] = "";
			extraDetls[5] = "";
			extraDetls[6] = "";
			extraDetls[7] = form.getHidnUserId();
			extraDetls[8] = "";
			extraDetls[9] = "";
			extraDetls[10] = "";
			extraDetls[11] = "";
			extraDetls[12] = "";
			extraDetls[13] = "";
			extraDetls[14] = "";
			extraDetls[15] = "";
			extraDetls[16] = "";
			extraDetls[17] = "";
			extraDetls[18] = "";
			extraDetls[19] = "";
			extraDetls[20] = "";
			extraDetls[21] = "";
			extraDetls[22] = "";
			extraDetls[23] = "";
			extraDetls[24] = "";
			extraDetls[25] = "";
			extraDetls[26] = "";
			extraDetls[27] = "";
			extraDetls[28] = "";
			extraDetls[29] = "";
			extraDetls[30] = "";
			extraDetls[31] = "";
			extraDetls[32] = "";
			extraDetls[33] = "";
			extraDetls[34] = "";
			extraDetls[35] = "";
			extraDetls[36] = "";
			extraDetls[37] = "";
			extraDetls[38] = "";
			extraDetls[39] = "";
			extraDetls[40] = "";
			extraDetls[41] = "";
			extraDetls[42] = "";
			extraDetls[43] = "";
			extraDetls[44] = "";
			extraDetls[45] = "";
			extraDetls[46] = "";
			extraDetls[47] = "";
			extraDetls[48] = "";
			extraDetls[49] = "";
			extraDetls[50] = "";
			extraDetls[51] = "";
			extraDetls[52] = "";
			extraDetls[53] = "";
			extraDetls[54] = "";
			extraDetls[55] = "";
			extraDetls[56] = "";
			extraDetls[57] = "";
			extraDetls[58] = "";
			extraDetls[59] = "";
			// new column
			extraDetls[60] = "";
			extraDetls[61] = "";
			if (form.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE) {
				extraDetls[1] = form.getBankName();
				extraDetls[2] = form.getBranchName();
				String bankAddress = form.getBankAddress();
				extraDetls[3] = bankAddress.replace(",", RegInitConstant.SPLIT_CONSTANT);
				extraDetls[4] = form.getBankAuthPer();
				extraDetls[5] = form.getBankLoanAmt();
				extraDetls[6] = form.getBankSancAmt();
			} else if (form.getDeedID() == RegInitConstant.DEED_TRUST) {
				extraDetls[8] = form.getTrustName();
				/*
				 * if (form.getTrustDate() != null &&
				 * !form.getTrustDate().equalsIgnoreCase("")) {
				 */
				extraDetls[9] = bo.convertCalenderDate(form.getTrustDate());
				/*
				 * } else { extraDetls[9] = form.getTrustDate(); }
				 */
			} else if (form.getDeedID() == RegInitConstant.DEED_LEASE_PV || form.getDeedID() == RegInitConstant.DEED_LEASE_NPV) {
				extraDetls[10] = Double.toString(form.getRent());
				extraDetls[11] = Double.toString(form.getAdvance());
				extraDetls[12] = Double.toString(form.getDevlpmtCharge());
				if (!Double.toString(form.getOtherRecCharge()).equalsIgnoreCase("")) {
					extraDetls[13] = Double.toString(form.getOtherRecCharge());
				} else {
					extraDetls[13] = "";
				}
				extraDetls[14] = Double.toString(form.getPremium());
				extraDetls[15] = Double.toString(form.getTermLease());
			} /*
			 * else if (form.getDeedID() == RegInitConstant.DEED_TRUST_PV) {
			 * 
			 * extraDetls[8] = form.getTrustName(); extraDetls[9] =
			 * bo.convertCalenderDate(form.getTrustDate());
			 * 
			 * }
			 */else if (form.getDeedID() == RegInitConstant.DEED_MORTGAGE_PV || form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV) {
				extraDetls[16] = form.getWithPos();
				extraDetls[17] = Double.toString(form.getSecLoanAmt());
			} else if (form.getDeedID() == RegInitConstant.DEED_AWARD_PV || form.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) // award
			{
				extraDetls[18] = form.getFnameArb();
				extraDetls[19] = form.getMnameArb();
				extraDetls[20] = form.getLnameArb();
				extraDetls[21] = form.getGendarArb();
				extraDetls[22] = form.getAgeArb();
				extraDetls[23] = form.getFatherNameArb();
				extraDetls[24] = form.getMotherNameArb();
				extraDetls[25] = form.getSpouseNameArb();
				extraDetls[26] = form.getNationalityArb();
				extraDetls[27] = form.getIndaddressArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
				extraDetls[28] = form.getIndcountryArb();
				extraDetls[29] = form.getIndstatenameArb();
				extraDetls[30] = form.getInddistrictArb();
				extraDetls[31] = form.getIndphnoArb();
				extraDetls[32] = form.getIndmobnoArb();
				if (form.getEmailIDArb() != null && !form.getEmailIDArb().equalsIgnoreCase("")) {
					extraDetls[33] = form.getEmailIDArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
				} else {
					extraDetls[33] = "";
				}
				extraDetls[34] = form.getIndCategoryArb();
				extraDetls[35] = form.getIndDisabilityArb();
				if (form.getIndDisabilityArb().equalsIgnoreCase("Y")) {
					if (form.getIndDisabilityDescArb() != null && !form.getIndDisabilityDescArb().equalsIgnoreCase("")) {
						extraDetls[36] = form.getIndDisabilityDescArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
					} else {
						extraDetls[36] = "";
					}
				} else {
					extraDetls[36] = "";
				}
				extraDetls[37] = form.getListIDArb();
				extraDetls[38] = form.getIdnoArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
			} else if (form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV_LOAN) {
				// added by rahul for new instrument
				if (form.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_MEDIA) {
					if (form.getAgreementDetails() != null && !form.getAgreementDetails().equalsIgnoreCase("")) {
						extraDetls[60] = form.getAgreementDetails();
					}
				} else if (form.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_WORK_CONTRACT) {
					if (form.getContractDetails() != null && !form.getContractDetails().equalsIgnoreCase("")) {
						extraDetls[61] = form.getContractDetails();
					}
				}
				// extraDetls[11] = Double.toString(form.getAdvance());
				else {
					extraDetls[58] = form.getAdvancePaymntDetails();
					// extraDetls[39] = bo.convertCalenderDate(form
					// .getAdvancePaymntDate());
					extraDetls[39] = "";
					// below if condition for uat observation pt. 6
					if (form.getInstID() != RegInitConstant.INST_AGREEMENT_MEMO_NPV_WITHOUTPOS) {
						extraDetls[40] = form.getPossGiven();
					}
				}
			} else if (form.getDeedID() == RegInitConstant.DEED_POA) {
				if (form.getInstID() == RegInitConstant.INST_POA_3) {
					extraDetls[41] = form.getPoaWithConsid();
				}
				if (form.getInstID() == RegInitConstant.INST_SHARE_DEBENTURE) {
					extraDetls[59] = form.getPoaShareDebenture();
				}
				extraDetls[42] = Double.toString(form.getPoaPeriod());
			} else if (form.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV) {
				extraDetls[43] = Double.toString(form.getPaidLoanAmt());
			} else if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && (form.getInstID() != RegInitConstant.INST_DISSOLUTION_NPV && form.getInstID() != RegInitConstant.INST_DISSOLUTION_2 && form.getInstID() != RegInitConstant.INST_PARTNERSHIP_EXCLUDING_CASH)) {
				if (form.getContriProp() != null && !form.getContriProp().equalsIgnoreCase("")) {
					extraDetls[44] = form.getContriProp();
				} else {
					extraDetls[44] = "";
				}
			} else if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && (form.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || form.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) {
				/*
				 * if (form.getDissolutionDate() != null &&
				 * !form.getDissolutionDate().equalsIgnoreCase("")) {
				 */
				extraDetls[45] = bo.convertCalenderDate(form.getDissolutionDate());
				/*
				 * } else { extraDetls[45] = form.getDissolutionDate(); }
				 */
				/*
				 * if (form.getRetirmentDate() != null &&
				 * !form.getRetirmentDate().equalsIgnoreCase("")) {
				 */
				extraDetls[46] = bo.convertCalenderDate(form.getRetirmentDate());
				/*
				 * } else { extraDetls[46] = form.getRetirmentDate(); }
				 */
			} else if (form.getInstID() == RegInitConstant.INST_ACK_RECEIPT_1) {
				/*
				 * if (form.getExecutionDate() != null &&
				 * !form.getExecutionDate().equalsIgnoreCase("")) {
				 */
				extraDetls[47] = bo.convertCalenderDate(form.getExecutionDate());
				/*
				 * } else { extraDetls[47] = form.getExecutionDate(); }
				 */
				/*
				 * if (form.getRegistrationDate() != null &&
				 * !form.getRegistrationDate().equalsIgnoreCase("")) {
				 */
				extraDetls[48] = bo.convertCalenderDate(form.getRegistrationDate());
				/*
				 * } else { extraDetls[48] = form.getRegistrationDate(); }
				 */
				extraDetls[49] = form.getRegistrationNo();
				extraDetls[50] = Double.toString(form.getReceiptAmount());
			} else if (form.getInstID() == RegInitConstant.INST_BANK_GAURANTEE_1) {
				extraDetls[50] = Double.toString(form.getReceiptAmount());
				extraDetls[47] = bo.convertCalenderDate(form.getExecutionDate());
				extraDetls[1] = form.getBankName();
				extraDetls[51] = form.getPropDetls();
			} else if (form.getInstID() == RegInitConstant.INST_CONSENT_1) {
				extraDetls[48] = bo.convertCalenderDate(form.getRegistrationDate());
				extraDetls[49] = form.getRegistrationNo();
				extraDetls[52] = form.getDeedNamePreReg();
				extraDetls[53] = form.getDeedTypePreReg();
			} else if (form.getInstID() == RegInitConstant.INST_DEC_UNDER_MP_1) {
				extraDetls[51] = form.getPropDetls();
				extraDetls[54] = form.getMapOrderNo();
				extraDetls[55] = bo.convertCalenderDate(form.getMapOrderDate());
				extraDetls[56] = form.getTcpOrderNo();
				extraDetls[57] = bo.convertCalenderDate(form.getTcpOrderDate());
			} else if (form.getInstID() == RegInitConstant.INST_DOC_AMEND_1) {
				extraDetls[48] = bo.convertCalenderDate(form.getRegistrationDate());
				extraDetls[49] = form.getRegistrationNo();
				extraDetls[52] = form.getDeedNamePreReg();
				extraDetls[53] = form.getDeedTypePreReg();
				extraDetls[47] = bo.convertCalenderDate(form.getExecutionDate());
				extraDetls[51] = form.getPropDetls();
			} else {
				extraDetls = null;
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			extraDetls = null;
		}
		return extraDetls;
	}

	/**
	 * getRegTxnIdSeq Returns string value for daily sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 */
	public String getRegTxnIdSeq() throws Exception {
		return commonDao.getRegTxnIdSeq();
	}

	/**
	 * getRegTxnIdSeq Returns string value for daily sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 */
	public String getNewRegTxnIdSeq() throws Exception {
		return commonDao.getNewRegTxnIdSeq();
	}

	/**
	 * insertTransPartyDetails Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param dto
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertTransPartyDetails(RegCommonDTO dto, RegCommonForm regForm, ArrayList list, int multiPropFlag, ArrayList agriList) throws Exception {
		boolean insertsuccess = false;
		// FOLLOWING CODE FOR OWNER DETAILS
		// String ownerDetails[] = new String[19];
		int partyId = 0;
		if (dto.getPartyTypeTrns() != null) {
			partyId = Integer.parseInt(dto.getPartyTypeTrns());
		}
		// int
		// claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(partyId)));
		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());
		/*
		 * if (Integer.parseInt(claimantArr[1].trim()) ==
		 * RegInitConstant.POA_HOLDER_FLAG || (regForm.getCommonDeed()==1 &&
		 * (dto.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_2 ||
		 * dto.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_4))) {
		 * 
		 * ownerDetails[0] = regForm.getHidnRegTxnId(); // reg txn id if
		 * (dto.getOwnerOgrNameTrns().equalsIgnoreCase("-")) { ownerDetails[1] =
		 * RegInitConstant.INDIVIDUAL_ID; // individual // party // type
		 * ownerDetails[2] = dto.getOwnerNameTrns(); // first name
		 * ownerDetails[12] = ""; // authorized person name } else {
		 * ownerDetails[1] = RegInitConstant.ORGANISATION_ID; // organization //
		 * party // type ownerDetails[2] = ""; // first name ownerDetails[12] =
		 * dto.getOwnerNameTrns(); // authorized person // name }
		 * 
		 * if (dto.getOwnerGendarTrns().equalsIgnoreCase("female"))
		 * ownerDetails[3] = "F"; // gender else ownerDetails[3] = "M"; //
		 * ownerDetails[4]=dto.getOwnerDOBTrns(); //DOB ownerDetails[4] =
		 * dto.getOwnerAgeTrns(); // age ownerDetails[5] =
		 * dto.getOwnerNationalityTrns(); // nationality
		 * 
		 * 
		 * String address=dto.getOwnerAddressTrns();
		 * address=address.replace(",", " ");
		 * 
		 * 
		 * ownerDetails[6] = dto.getOwnerAddressTrns(); // address
		 * ownerDetails[7] = dto.getOwnerPhnoTrns(); // phone number if
		 * (dto.getOwnerEmailIDTrns().equalsIgnoreCase("-")) ownerDetails[8] =
		 * ""; // email id else ownerDetails[8] = dto.getOwnerEmailIDTrns(); //
		 * email id ownerDetails[9] = dto.getOwnerListIDTrns(); // photo proof
		 * type id
		 * 
		 * 
		 * String proof=dto.getOwnerIdnoTrns(); proof=proof.replace(",", "");
		 * 
		 * 
		 * //ownerDetails[10] = dto.getOwnerIdnoTrns(); // photo proof number
		 * 
		 * 
		 * if (dto.getOwnerIdnoTrns().equalsIgnoreCase("-")) ownerDetails[10] =
		 * null; // photo proof number else ownerDetails[10] =
		 * dto.getOwnerIdnoTrns(); // photo proof number
		 * 
		 * 
		 * 
		 * if (dto.getOwnerOgrNameTrns().equalsIgnoreCase("-")) ownerDetails[11]
		 * = ""; // organization name else ownerDetails[11] =
		 * dto.getOwnerOgrNameTrns(); // organization name ownerDetails[13] =
		 * getTransactingPartyIdSeq(); // party transaction // id
		 * ownerDetails[14] = "O"; // is applicant O-Owner if
		 * (dto.getShareOfPropTrns() == null) ownerDetails[15] = ""; else if
		 * (dto.getShareOfPropTrns().equalsIgnoreCase("-")) ownerDetails[15] =
		 * ""; else ownerDetails[15] = dto.getShareOfPropTrns(); //
		 * ownerDetails[15]=dto.getShareOfPropTrns(); //share of property
		 * ownerDetails[16] = Integer
		 * .toString(RegInitConstant.ROLE_OWNER_SELF); // party role // type id
		 * ownerDetails[17] = dto.getUserID();
		 * 
		 * ownerDetails[18] =
		 * dto.getAddressOwnerOutMpTrns().equalsIgnoreCase("-"
		 * )?"":dto.getAddressOwnerOutMpTrns(); }
		 */
		String[] param;
		if (dto.getAgeTrns() != null && !dto.getAgeTrns().isEmpty()) {
			int partyAge = 0;
			partyAge = Integer.parseInt(dto.getAgeTrns());
			if (partyAge >= 18) {
				param = new String[62];
			} else {
				param = new String[63];
				param[62] = dto.getGuardianTrans();
			}
		} else {
			param = new String[62];
		}
		// String[] param = new String[62];
		param[0] = regForm.getHidnRegTxnId(); // Registration Txn Id.
		// relation with owner
		if (dto.getRelationWithOwnerTrns() == null)
			param[27] = "";
		else if (dto.getRelationWithOwnerTrns().equalsIgnoreCase("-"))
			param[27] = "";
		else
			param[27] = dto.getRelationWithOwnerTrns();
		// share of prop
		// if (dto.getShareOfPropTrns() == null)
		param[28] = "";
		/*
		 * else if (dto.getShareOfPropTrns().equalsIgnoreCase("-")) param[28] =
		 * ""; else param[28] = dto.getShareOfPropTrns();
		 */
		if (regForm.getCommonDeed() != 1) {
			param[29] = dto.getPartyTypeTrns();
			param[49] = "";
			param[51] = "";
		} else {
			param[29] = "0";
			param[49] = dto.getRoleName(); // role name entered by user
			param[51] = Integer.toString(dto.getExecutantClaimantTrns()); // for
			// universal
			// deeds
			// executant
			// claimant
			// from
			// front
			// end
		}
		param[60] = "";
		param[61] = "";
		// param[51]=""; // for universal deeds executant claimant from front
		// end
		if (dto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
			// param[0] = form.getHidnRegTxnId();
			param[1] = dto.getListAdoptedPartyTrns();
			param[8] = "1";
			param[9] = "1";
			param[10] = dto.getSelectedDistrict();
			param[23] = dto.getPartyRoleTypeId(); // party txn id
			param[24] = "N";
			param[30] = dto.getUserID();
			param[32] = "";
			param[56] = "Y";
			if (("-").equalsIgnoreCase(dto.getNameOfOfficial())) {
				param[57] = "";
			} else {
				param[57] = dto.getNameOfOfficial();
			}
			param[58] = dto.getDesgOfOfficial();
			param[59] = dto.getAddressOfOfficial();
			param[60] = dto.getAddressGovtOffclOutMpTrns().equalsIgnoreCase("-") ? "" : dto.getAddressGovtOffclOutMpTrns();
			param[61] = "";
		} else if (dto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) { // Organization
			param[1] = dto.getListAdoptedPartyTrns();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			// param[5] = "";
			param[6] = "";
			param[7] = "";
			param[8] = dto.getSelectedCountry();
			param[9] = dto.getSelectedState();
			param[10] = dto.getSelectedDistrict();
			/*
			 * String address=dto.getOrgaddressTrns();
			 * address=address.replace(",", " ");
			 */
			param[11] = dto.getOrgaddressTrns();
			param[12] = "";
			if (dto.getPhnoTrns().equalsIgnoreCase("-")) {
				param[13] = "";
			} else {
				param[13] = dto.getPhnoTrns();
			}
			if (dto.getMobnoTrns().equalsIgnoreCase("-")) {
				param[14] = "";
			} else {
				param[14] = dto.getMobnoTrns();
			}
			param[15] = "";
			param[16] = "";
			param[17] = "";
			param[18] = dto.getOgrNameTrns();
			param[19] = dto.getAuthPerNameTrns();
			// param[20] = "";
			param[21] = "";
			param[22] = "";
			param[23] = dto.getPartyRoleTypeId(); // party txn id
			if (dto.getPartyTypeFlag().equalsIgnoreCase("C")) {
				param[24] = dto.getPartyTypeFlag();
			} else {
				param[24] = "N";
			} // IS APPLICANT FLAG
			param[25] = ""; // OCCUPATION
			param[26] = ""; // DISABILITY
			// param[29] = dto.getPartyTypeTrns();
			param[30] = dto.getUserID();
			param[31] = ""; // SPOUSE NAME
			// if (ownerDetails[13] == null) {
			param[32] = "";
			// } else {
			// param[32] = ownerDetails[13];
			// } // self referencing key of owner in case of poa holder
			param[33] = ""; // DISABILITY DESCRIPTION
			param[34] = ""; // MINORITY
			param[35] = ""; // POVERTY LINE
			param[36] = ""; // ST RADIO
			param[37] = ""; // ST CERTIFICATE number
			param[38] = ""; // ST UPLOAD FIELD
			/*
			 * param[39]=""; //PHOTO_PROOF_PATH param[40]="";
			 * //NOT_AFFD_EXEC_PATH param[41]=""; //EXEC_PHOTO_PATH
			 * param[42]=""; //NOT_AFFD_ATTR_PATH param[43]="";
			 * //ATTR_PHOTO_PATH param[44]=""; //CLAIMNT_PHOTO_PATH
			 * param[45]=""; //PAN_FORM_60_PATH param[46]=""; param[47]="";
			 * param[48]="";
			 */
			// param[50] = "";
			param[50] = Integer.toString(dto.getRelationshipTrns()); // relationship
			// type
			param[5] = dto.getAuthPerGendarTrns();
			param[20] = dto.getAuthPerFatherNameTrns();
			param[52] = dto.getAuthPerCountryTrns();
			param[53] = dto.getAuthPerStatenameTrns();
			param[54] = dto.getAuthPerDistrictTrns();
			String authAddress = dto.getAuthPerAddressTrns();
			authAddress = authAddress;
			param[55] = authAddress;
			param[60] = dto.getAddressOrgOutMpTrns().equalsIgnoreCase("-") ? "" : dto.getAddressOrgOutMpTrns();
			param[61] = dto.getAddressAuthPerOutMpTrns().equalsIgnoreCase("-") ? "" : dto.getAddressAuthPerOutMpTrns();
		} else // Individual
		{
			param[1] = dto.getListAdoptedPartyTrns();
			param[2] = dto.getFnameTrns();
			if (dto.getMnameTrns().equalsIgnoreCase("-")) {
				param[3] = "";
			} else {
				param[3] = dto.getMnameTrns();
			}
			param[4] = dto.getLnameTrns();
			param[5] = dto.getGendarTrns();
			// param[6] = dto.getUserDOBTrns(); //DOB
			param[6] = dto.getAgeTrns();
			param[7] = dto.getNationalityTrns();
			param[8] = dto.getSelectedCountry();
			param[9] = dto.getSelectedState();
			param[10] = dto.getSelectedDistrict();
			/*
			 * String address=dto.getOrgaddressTrns();
			 * address=address.replace(",", " ");
			 */
			param[11] = dto.getOrgaddressTrns();
			if (dto.getPostalCodeTrns().equalsIgnoreCase("-")) {
				param[12] = "";
			} else {
				param[12] = dto.getPostalCodeTrns();
			}
			if (dto.getPhnoTrns().equalsIgnoreCase("-")) {
				param[13] = "";
			} else {
				param[13] = dto.getPhnoTrns();
			}
			if (dto.getMobnoTrns().equalsIgnoreCase("-")) {
				param[14] = "";
			} else {
				param[14] = dto.getMobnoTrns();
			}
			if (dto.getEmailIDTrns().equalsIgnoreCase("-")) {
				param[15] = "";
			} else {
				param[15] = dto.getEmailIDTrns();
			}
			// commented by ankit for aadhar bypass
			/*
			 * if (regForm.getClrFlg() != null) { if
			 * (regForm.getClrFlg().equalsIgnoreCase("Y")) {
			 * dto.setSelectedPhotoId("7"); } }
			 */
			param[16] = dto.getSelectedPhotoId();
			String proof = dto.getIdnoTrns();
			proof = proof;
			// Aadhar Changes - ankit
			/*
			 * if ("7".equalsIgnoreCase(dto.getSelectedPhotoId())) { param[17] =
			 * AadharUtil.encryptWithAES256(proof); } else {
			 */
			param[17] = proof;
			// }
			param[18] = ""; // ORGANISATION NAME
			param[19] = ""; // AUTHORISED PERSON NAME
			param[20] = dto.getFatherNameTrns();
			if (dto.getMotherNameTrns().equalsIgnoreCase("-")) {
				param[21] = "";
			} else {
				param[21] = dto.getMotherNameTrns();
			}
			param[22] = dto.getIndCategoryTrns();
			param[23] = dto.getPartyRoleTypeId(); // party txn id
			if (dto.getPartyTypeFlag().equalsIgnoreCase("C")) {
				param[24] = dto.getPartyTypeFlag();
			} else {
				param[24] = "N";
			} // IS APPLICANT FLAG
			param[25] = dto.getOccupationIdTrns(); // OCCUPATION
			if (dto.getIndDisabilityTrns().equalsIgnoreCase("-")) {
				param[26] = "";
			} else if (dto.getIndDisabilityTrns().equalsIgnoreCase("Yes")) {
				param[26] = "Y";
			} else if (dto.getIndDisabilityTrns().equalsIgnoreCase("No")) {
				param[26] = "N";
			}
			// param[29] = dto.getPartyTypeTrns();
			param[30] = dto.getUserID();
			if (dto.getSpouseNameTrns().equalsIgnoreCase("-")) {
				param[31] = "";
			} else {
				param[31] = dto.getSpouseNameTrns();
			}
			// if (ownerDetails[13] == null) {
			param[32] = "";
			// } else {
			// param[32] = ownerDetails[13];
			// } // self referencing key of owner in case of poa holder
			// if(dto.getIndDisabilityDescTrns().equals(null)){
			// param[33] = "";
			// }
			// else
			if (dto.getIndDisabilityDescTrns() != null) {
				if (dto.getIndDisabilityDescTrns().equalsIgnoreCase("-") || dto.getIndDisabilityDescTrns().equalsIgnoreCase("") || dto.getIndDisabilityDescTrns().equalsIgnoreCase("null")) {
					param[33] = "";
				} else {
					param[33] = dto.getIndDisabilityDescTrns();
				}
			} else {
				param[33] = "";
			}
			if (dto.getIndMinorityTrns() == null || dto.getIndMinorityTrns().equalsIgnoreCase("-")) {
				param[34] = "";
			} else if (dto.getIndMinorityTrns().equalsIgnoreCase("Yes")) {
				param[34] = "Y";
			} else if (dto.getIndMinorityTrns().equalsIgnoreCase("No")) {
				param[34] = "N";
			}
			if (dto.getIndPovertyLineTrns() == null || dto.getIndPovertyLineTrns().equalsIgnoreCase("-")) {
				param[35] = "";
			} else if (dto.getIndPovertyLineTrns().equalsIgnoreCase("Yes")) {
				param[35] = "Y";
			} else if (dto.getIndPovertyLineTrns().equalsIgnoreCase("No")) {
				param[35] = "N";
			}
			if (dto.getIndCategoryTrns() != null) {
				if (dto.getIndCategoryTrns().equalsIgnoreCase("1")) { // IF
					// CATEGORY
					// IS ST
					param[36] = dto.getIndScheduleAreaTrns(); // ST RADIO
					if (dto.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
						param[37] = dto.getPermissionNoTrns(); // ST
						// CERTIFICATE
						// number
						param[38] = dto.getFilePathTrns(); // ST UPLOAD file
						// path
					} else {
						param[37] = "";
						param[38] = "";
					}
				} else {
					param[36] = "";
					param[37] = "";
					param[38] = "";
				}
			} else {
				param[36] = "";
				param[37] = "";
				param[38] = "";
			}
			param[50] = Integer.toString(dto.getRelationshipTrns());
			param[52] = "";
			param[53] = "";
			param[54] = "";
			param[55] = "";
			param[60] = dto.getAddressIndOutMpTrns().equalsIgnoreCase("-") ? "" : dto.getAddressIndOutMpTrns();
			param[61] = "";
		}
		param[39] = dto.getU2FilePathTrns(); // PHOTO_PROOF_PATH
		if ( // regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
		// regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_POA ||
		// regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
		// regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
		// regForm.getDeedTypeFlag() == 1 || regForm.getCommonDeed() == 1
		dto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID) || regForm.getPropReqFlag().equalsIgnoreCase("N")) { // 1
			// for
			// deeds
			// not
			// requiring
			// property
			// details
			// even.
			param[40] = ""; // NOT_AFFD_EXEC_PATH
			param[41] = ""; // EXEC_PHOTO_PATH
			param[42] = ""; // NOT_AFFD_ATTR_PATH
			param[43] = ""; // ATTR_PHOTO_PATH
			param[44] = ""; // CLAIMNT_PHOTO_PATH
			param[45] = ""; // PAN_FORM_60_PATH
			param[46] = "";
			param[47] = "";
			param[48] = "";
		} else {
			param[40] = dto.getU3FilePathTrns(); // NOT_AFFD_EXEC_PATH
			param[41] = ""; // EXEC_PHOTO_PATH
			// param[42] = dto.getU5FilePathTrns(); // NOT_AFFD_ATTR_PATH
			param[42] = "";
			param[43] = ""; // ATTR_PHOTO_PATH
			// param[44] = dto.getU8FilePathTrns(); // CLAIMNT_PHOTO_PATH
			param[44] = ""; // CLAIMNT_PHOTO_PATH
			param[45] = ""; // PAN_FORM_60_PATH
			param[46] = "";
			param[47] = "";
			param[48] = "";
			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
				// param[41] = dto.getU4FilePathTrns(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				param[45] = dto.getU10FilePathTrns(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
				// param[41] = dto.getU7FilePathTrns(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				param[43] = dto.getU6FilePathTrns(); // ATTR_PHOTO_PATH
				param[46] = dto.getSrOfficeNameTrns();
				if (dto.getPoaRegNoTrns().equalsIgnoreCase("-")) {
					param[47] = "";
				} else {
					param[47] = dto.getPoaRegNoTrns();
				}
				if (dto.getDatePoaRegTrns().equalsIgnoreCase("-")) {
					param[48] = "";
				} else {
					param[48] = dto.getDatePoaRegTrns();
				}
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
				param[45] = dto.getU11FilePathTrns(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
				param[43] = dto.getU9FilePathTrns(); // ATTR_PHOTO_PATH
			}
		}
		// set in string[] start from 56
		/*
		 * if("on".equalsIgnoreCase((String)dto.getGovtOfcCheck()) ||
		 * "Y".equalsIgnoreCase((String)dto.getGovtOfcCheck())){ param[56]="Y";
		 * } else{ param[56]="N"; } //param[56]=dto.getGovtOfcCheck();
		 * param[57]=dto.getNameOfOfficial(); param[58]=dto.getDesgOfOfficial();
		 * param[59]=dto.getAddressOfOfficial();
		 */
		// FOLLOWING FOR REG TXN STATUS
		String[] statusParam = new String[2];
		// if (regForm.getCommonDeed() == 1) { // for common deeds
		// statusParam[0] = RegInitConstant.STATUS_TRNS_SAVED; //status common
		// for all
		statusParam[1] = regForm.getHidnRegTxnId();
		if (regForm.getPvReqFlag().equalsIgnoreCase("y") || regForm.getPropReqFlag().equalsIgnoreCase("y") || regForm.getCommonDeed() == 1) {
			statusParam[0] = RegInitConstant.STATUS_TRNS_SAVED;
		} else {
			statusParam[0] = RegInitConstant.STATUS_SHARES_SAVED;
		}
		// }
		/*
		 * else { if (regForm.getDeedTypeFlag() == 0) { // for deeds other than
		 * // trust/adoption if (multiPropFlag == 0) { statusParam[0] =
		 * RegInitConstant.STATUS_TRNS_SAVED; statusParam[1] =
		 * regForm.getHidnRegTxnId(); } if (multiPropFlag == 1) { statusParam[0]
		 * = RegInitConstant.STATUS_MULTI_TRNS_SAVED; statusParam[1] =
		 * regForm.getHidnRegTxnId(); } } else { statusParam[0] =
		 * RegInitConstant.STATUS_PROP_SAVED; statusParam[1] =
		 * regForm.getHidnRegTxnId(); } }
		 */
		if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV || (regForm.getExchangePropertyList() != null && regForm.getExchangePropertyList().size() == 1)) {
			ArrayList paramList1 = new ArrayList();
			CommonDTO row_list;
			CommonDTO paramDto1;
			/*
			 * if(regForm.getDeedID() != RegInitConstant.DEED_EXCHANGE &&
			 * regForm.getDeedID() != RegInitConstant.DEED_PARTITION_PV){
			 * list=agriList; }
			 */
			// clrOwnerShare
			for (int i = 0; i < list.size(); i++) {
				row_list = (CommonDTO) list.get(i);
				paramDto1 = new CommonDTO();
				if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV) {
					if (partyId == RegInitConstant.ROLE1_OWNER_POA_HOLDER || // FOR
					// EXCHANGE
					// DEED
					// PARTIES
					partyId == RegInitConstant.ROLE1_OWNER_SELF || partyId == RegInitConstant.ROLE1_OWNER_AUTHENTICATED_POA_HOLDER) {
						if (row_list.getPartyType().trim().equalsIgnoreCase(RegInitConstant.PARTY_1_EN)) {
							// if
							// (row_list.getId().trim().equalsIgnoreCase("1")) {
							paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
							paramDto1.setPropertyId(row_list.getPropertyId().trim());
							paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
							paramDto1.setUserId(dto.getUserID());
							paramList1.add(paramDto1);
						}
					} else if (partyId == RegInitConstant.ROLE2_OWNER_POA_HOLDER || // FOR
					// EXCHANGE
					// DEED
					// PARTIES
					partyId == RegInitConstant.ROLE2_OWNER_SELF || partyId == RegInitConstant.ROLE2_OWNER_AUTHENTICATED_POA_HOLDER) {
						if (row_list.getPartyType().trim().equalsIgnoreCase(RegInitConstant.PARTY_2_EN)) {
							// if
							// (row_list.getId().trim().equalsIgnoreCase("2")) {
							paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
							paramDto1.setPropertyId(row_list.getPropertyId().trim());
							paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
							paramDto1.setUserId(dto.getUserID());
							paramList1.add(paramDto1);
						}
					} else if (partyId == RegInitConstant.ROLE_CO_OWNER_POA_HOLDER || // FOR
					// PARTITION
					// DEED
					// PARTIES
					partyId == RegInitConstant.ROLE_CO_OWNER_SELF || partyId == RegInitConstant.ROLE_CO_OWNER_AUTHENTICATED_POA_HOLDER) {
						paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
						paramDto1.setPropertyId(row_list.getPropertyId().trim());
						paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
						paramDto1.setUserId(dto.getUserID());
						paramList1.add(paramDto1);
					}
				} else {
					paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
					paramDto1.setPropertyId(row_list.getPropertyId().trim());
					paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
					paramDto1.setUserId(dto.getUserID());
					paramList1.add(paramDto1);
				}
			}
			insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsExchange(param, new String[0], null, paramList1, dto.getTrnsOwnerList(), "", statusParam, null, regForm, dto);
		} else /*
				 * if (regForm.getDeedID() ==
				 * RegInitConstant.DEED_DEPOSIT_OF_TITLE || regForm.getDeedID()
				 * == RegInitConstant.DEED_TRUST || regForm.getDeedID() ==
				 * RegInitConstant.DEED_ADOPTION || regForm.getDeedID() ==
				 * RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
				 * regForm.getDeedID() == RegInitConstant.DEED_LEASE_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_POA ||
				 * regForm.getDeedID() == RegInitConstant.DEED_SETTLEMENT_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_WILL_NPV ||
				 * regForm.getDeedID() ==
				 * RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
				 * regForm.getDeedID() ==
				 * RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_COMPOSITION_NPV
				 * || regForm.getDeedID() ==
				 * RegInitConstant.DEED_SECURITY_BOND_NPV || regForm.getDeedID()
				 * == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
				 * regForm.getDeedID() ==
				 * RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
				 * || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV
				 * || regForm.getDeedID() ==
				 * RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
				 * regForm.getDeedID() == RegInitConstant.DEED_TRANSFER_NPV ||
				 * regForm.getDeedID() ==
				 * RegInitConstant.DEED_FURTHER_CHARGE_NPV)
				 */
		{ // for
			// title
			// deed
			insertsuccess = commonDao.insrtTransPartyDetlsTitleDeed(param, dto.getTrnsOwnerList(), statusParam, regForm, dto);
		} /*
		 * else if (regForm.getCommonDeed() == 1) { // for common deeds
		 * insertsuccess = commonDao.insrtTransPartyDetlsCommonDeeds(param,
		 * ownerDetails, statusParam); }
		 */
		/*
		 * else { // for other than exchange deed and title deed
		 * 
		 * String propDets[] = new String[4]; propDets[0] =
		 * regForm.getHidnRegTxnId(); propDets[1] = dto.getPropertyId();
		 * propDets[2] = dto.getPartyRoleTypeId(); propDets[3] =
		 * dto.getUserID();
		 * 
		 * insertsuccess = commonDao.insrtApplcntTransPartyPropDetls(param, new
		 * String[0], new String[0], propDets, ownerDetails, "", statusParam,
		 * null); }
		 */
		return insertsuccess;
	}

	/**
	 * insertTxnDetails Returns boolean value in order to check the insertion.
	 * 
	 * @param String
	 *            []
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertTxnDetails(String[] txnDetls, String regFeeCheck) throws Exception {
		boolean insertsuccess = false;
		insertsuccess = commonDao.insertTxnDetails(txnDetls, regFeeCheck);
		return insertsuccess;
	}

	public boolean updatePaymentStatus(String[] txnDetls) throws Exception {
		boolean insertsuccess = false;
		insertsuccess = commonDao.updatePaymentStatus(txnDetls);
		return insertsuccess;
	}

	public boolean insertTxnDetailsFee(String[] txnDetls) throws Exception {
		boolean insertsuccess = false;
		insertsuccess = commonDao.insertTxnDetailsFee(txnDetls);
		return insertsuccess;
	}

	/**
	 * form setting selected Owner Details(in confirmation page)
	 * 
	 * @param ownerList
	 * @return ArrayList
	 */
	public ArrayList setOwnerList(String ownerList) {
		ArrayList list = new ArrayList();
		String[] temp = ownerList.split(",");
		CommonDTO dto = null;
		for (int i = 0; i < temp.length; i++) {
			dto = new CommonDTO();
			dto.setId(temp[i]);
			dto.setChecked("yes");
			i = i + 1;
			dto.setName(temp[i]);
			list.add(dto);
		}
		return list;
	}

	/**
	 * insertMappingDetails Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertMappingDetails(String txnId, String poaId, String[] ownerId, String userId) throws Exception // main
	{
		boolean insertsuccess = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
		Date currentDate = new Date();
		// RegCommonDAO dao = new RegCommonDAO();
		String[] param = new String[5];
		param[0] = txnId;
		param[1] = poaId;
		param[2] = userId;
		// param[3]=formatter.format(currentDate);
		param[3] = "";
		param[4] = "";
		insertsuccess = commonDao.insertMappingDetails(ownerId, param);
		return insertsuccess;
	}

	/**
	 * getRegInitTxnIdSeq Returns string value for daily sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 */
	public String getRegInitTxnIdSeq() throws Exception {
		String seq = null;
		Calendar cal = new GregorianCalendar();
		String mon = "";
		String day = "";
		String yr = "";
		int curr_month = cal.get(Calendar.MONTH) + 1;
		int curr_day = cal.get(Calendar.DATE);
		int curr_year = cal.get(Calendar.YEAR);
		// System.out.println("current month :- "+curr_month);
		mon = String.valueOf(curr_month);
		day = String.valueOf(curr_day);
		yr = String.valueOf(curr_year);
		yr = yr.substring(2);
		// System.out.println("current mon :- "+mon);
		// System.out.println("current day :- "+day);
		// System.out.println("current yr :- "+yr);
		// String serialnumber = getSPSerialNumber();
		// String serialnumber;
		// getting the month of recently saved licence number;
		// String[] previousDate =getPreviousRegIdDate();
		// System.out.println("previous dd :- "+previousDate[0]);
		// System.out.println("previous mm :- "+previousDate[1]);
		// System.out.println("previous yy :- "+previousDate[2]);
		// if(day.equals(previousDate[0]) &&
		// mon.equals(previousDate[1]) &&
		// yr.equals(previousDate[2])){
		seq = getRegTxnIdSeq();
		// }
		// else
		// seq=getNewRegTxnIdSeq();
		if (seq.length() == 1) {
			seq = "00000" + seq;
		} else if (seq.length() == 2) {
			seq = "0000" + seq;
		} else if (seq.length() == 3) {
			seq = "000" + seq;
		} else if (seq.length() == 4) {
			seq = "00" + seq;
		} else if (seq.length() == 5) {
			seq = "0" + seq;
		}
		return seq;
	}

	public String[] getPreviousRegIdDate() throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPreviousRegIdDate();
	}

	/**
	 * getCurrDateTime for getting current system date/time from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getCurrDateTime() throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		// String currDate=commonDao.getCurrDateTime();
		// String str_date="2011-03-10T11:54:30.207Z";
		/*
		 * DateFormat formatter ; Date date ; formatter = new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS"); date =
		 * (Date)formatter.parse(currDate); System.out.println("output: " +date
		 * ); currDate=date.toString();
		 */
		/*
		 * System.out.println("curr date 1 : "+currDate); String mm=""; String
		 * dd=""; String rest=""; if(currDate.length()==16){
		 * mm="0"+currDate.substring(0, 1); dd="0"+currDate.substring(2, 3);
		 * rest=currDate.substring(4); }else{ mm=currDate.substring(0, 2);
		 * dd=currDate.substring(3, 5); rest=currDate.substring(6);
		 * 
		 * } currDate=dd+"/"+mm+"/"+rest;
		 */
		return commonDao.getCurrDateTime();
	}

	/**
	 * getApplicantRegistrationDetls for getting applicant details registration
	 * initiation from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantRegistrationDetls(String userId) throws Exception {
		ArrayList appRegDetls = new ArrayList();
		appRegDetls = commonDao.getApplicantRegistrationDetls(userId);
		/*
		 * String regDtls = appRegDetls.toString();
		 * 
		 * regDtls = regDtls.substring(2, (regDtls.length() - 2));
		 * 
		 * String regDetls1[] = regDtls.split(",");
		 * logger.debug("--------------" + regDetls1);
		 */
		return (ArrayList) appRegDetls.get(0);
	}

	/**
	 * getPendingRegApps for getting pending applications of registration
	 * initiation from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPendingRegApps(String userId, int fromAdju) {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPendingRegApps(userId, fromAdju);
	}

	/**
	 * getSavedRegInitApplication for getting pending applications of
	 * registration initiation from db.
	 * 
	 * @param String
	 * @return RegCommonForm
	 * @author ROOPAM
	 */
	public RegCommonForm getSavedRegInitApplication(String appId) {
		// ArrayList savedTxnPartyDets = new ArrayList();
		// ArrayList savedPoaOwnerMapDets = new ArrayList();
		// ArrayList savedRegInitTxnDets = new ArrayList();
		ArrayList finalList = new ArrayList();
		RegCommonForm form = new RegCommonForm();
		finalList = commonDao.getSavedRegInitApplication(appId);
		int listCount = finalList.size();
		// logger.debug("ddddddddddddddddddd--size---->"+listCount);
		if (listCount == 1) {
			setAppDetsInForm(form, finalList);
			// logger.debug("fffffffffffffffffffffffff-->"+finalList.get(0).toString());
		}
		/*
		 * if(listCount==2){ setAppDetsInForm(form,finalList);
		 * savedTxnPartyDets=(ArrayList)finalList.get(1); } if(listCount==3){
		 * setAppDetsInForm(form,finalList);
		 * savedTxnPartyDets=(ArrayList)finalList.get(1);
		 * savedPoaOwnerMapDets=(ArrayList)finalList.get(2); } if(listCount==4){
		 * setAppDetsInForm(form,finalList);
		 * savedTxnPartyDets=(ArrayList)finalList.get(1);
		 * savedPoaOwnerMapDets=(ArrayList)finalList.get(2);
		 * savedRegInitTxnDets=(ArrayList)finalList.get(3); }
		 */
		return form;
	}

	private void setAppDetsInForm(RegCommonForm form, ArrayList finalList) {
		ArrayList savedAppDets = new ArrayList();
		savedAppDets = (ArrayList) finalList.get(0);
		savedAppDets = (ArrayList) savedAppDets.get(0);
		/*
		 * String savedAppDetsStr = savedAppDets.toString().substring(2,
		 * (savedAppDets.toString().length() - 2)); String[] appDets =
		 * savedAppDetsStr.split(",");
		 */
		if (savedAppDets.get(0).toString().length() == 11) {
			form.setHidnRegTxnId("0" + savedAppDets.get(0).toString());
		} else {
			form.setHidnRegTxnId(savedAppDets.get(0).toString());
		}
		form.setListAdoptedParty(savedAppDets.get(1).toString());
		form.setFname(savedAppDets.get(2) != null ? savedAppDets.get(2).toString() : "");
		form.setMname(savedAppDets.get(3) != null ? savedAppDets.get(3).toString() : "");
		form.setLname(savedAppDets.get(4) != null ? savedAppDets.get(4).toString() : "");
		form.setGendar(savedAppDets.get(5) != null ? savedAppDets.get(5).toString() : "");
		form.setAge(savedAppDets.get(6) != null ? savedAppDets.get(6).toString() : ""); // AGE
		form.setNationality(savedAppDets.get(7) != null ? savedAppDets.get(7).toString() : "");
		if (savedAppDets.get(1).toString().equals("1")) {
			form.setCountry(savedAppDets.get(8).toString());
			form.setStatename(savedAppDets.get(9).toString());
			form.setDistrict(savedAppDets.get(10).toString());
			form.setOrgaddress(savedAppDets.get(11).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		} else if (savedAppDets.get(1).toString().equals("2")) {
			form.setIndcountry(savedAppDets.get(8).toString());
			form.setIndstatename(savedAppDets.get(9).toString());
			form.setInddistrict(savedAppDets.get(10).toString());
			form.setIndaddress(savedAppDets.get(11).toString().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		} else {
			form.setInddistrict(savedAppDets.get(10).toString());
		}
		form.setPostalCode(savedAppDets.get(12) != null ? savedAppDets.get(12).toString() : "");
		form.setPhno(savedAppDets.get(13) != null ? savedAppDets.get(13).toString() : "");
		form.setMobno(savedAppDets.get(14) != null ? savedAppDets.get(14).toString() : "");
		form.setEmailID(savedAppDets.get(15) != null ? savedAppDets.get(15).toString() : "");
		form.setListID(savedAppDets.get(16) != null ? savedAppDets.get(16).toString() : "");
		// added For adhar decryption
		try {
			/*
			 * if ("7".equalsIgnoreCase(form.getListID())) {
			 * form.setIdno(savedAppDets.get(17) != null ?
			 * savedAppDets.get(17).toString().isEmpty() ? "" :
			 * AadharUtil.decryptWithAES256(savedAppDets.get(17).toString()) :
			 * ""); } else {
			 */
			form.setIdno(savedAppDets.get(17) != null ? savedAppDets.get(17).toString() : "");
			// }
			form.setAadharName(savedAppDets.get(60) != null ? savedAppDets.get(60).toString().isEmpty() ? "" : savedAppDets.get(60).toString() : "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		form.setGovtOfficeNameSelected(savedAppDets.get(61) != null ? savedAppDets.get(61).toString() : "");
		if (savedAppDets.get(18) != null && !savedAppDets.get(18).toString().equalsIgnoreCase("") && !savedAppDets.get(18).toString().equalsIgnoreCase("null")) {
			form.setOgrName(savedAppDets.get(18).toString());
		} else {
			form.setOgrName("");
		}
		form.setAuthPerName(savedAppDets.get(19) != null ? savedAppDets.get(19).toString() : "");
		form.setFatherName(savedAppDets.get(20) != null ? savedAppDets.get(20).toString() : "");
		form.setMotherName(savedAppDets.get(21) != null ? savedAppDets.get(21).toString() : "");
		form.setIndCategory(savedAppDets.get(22) != null ? savedAppDets.get(22).toString() : "");
		form.setPartyRoleTypeId(savedAppDets.get(23) != null ? savedAppDets.get(23).toString() : "");
		form.setIndDisability(savedAppDets.get(24) != null ? savedAppDets.get(24).toString() : "");
		form.setRelationWithOwner(savedAppDets.get(25) != null ? savedAppDets.get(25).toString() : "");
		// form.setShareOfProp(appDets[26].trim());
		form.setPartyType(savedAppDets.get(27) != null ? savedAppDets.get(27).toString() : "");
		form.setSpouseName(savedAppDets.get(28) != null ? savedAppDets.get(28).toString() : "");
		form.setPoaOwnerId(savedAppDets.get(29) != null ? savedAppDets.get(29).toString() : "");
		if (savedAppDets.get(30) != null && !savedAppDets.get(30).toString().equalsIgnoreCase("") && !savedAppDets.get(30).toString().equalsIgnoreCase("null")) {
			form.setIndDisabilityDesc(savedAppDets.get(30).toString());
		} else {
			form.setIndDisabilityDesc("-");
		}
		form.setOccupationId(savedAppDets.get(31) != null ? savedAppDets.get(31).toString() : "");
		if (savedAppDets.get(32) != null && !savedAppDets.get(32).toString().equalsIgnoreCase("") && !savedAppDets.get(32).toString().equalsIgnoreCase("null")) {
			form.setPermissionNo(savedAppDets.get(32).toString());
		} else {
			form.setPermissionNo("-");
		}
		// collector certificate path 33
		form.setFilePath(savedAppDets.get(33) != null ? savedAppDets.get(33).toString() : "");
		form.setIndMinority(savedAppDets.get(34) != null ? savedAppDets.get(34).toString() : "");
		form.setIndPovertyLine(savedAppDets.get(35) != null ? savedAppDets.get(35).toString() : "");
		form.setIndScheduleArea(savedAppDets.get(36) != null ? savedAppDets.get(36).toString() : "");
		form.setU2FilePath(savedAppDets.get(37) != null ? savedAppDets.get(37).toString() : ""); // 37
		// PHOTO_PROOF_PATH
		form.setU3FilePath(savedAppDets.get(38) != null ? savedAppDets.get(38).toString() : ""); // 38
		// NOT_AFFD_EXEC_PATH
		form.setU4FilePath(savedAppDets.get(39) != null ? savedAppDets.get(39).toString() : ""); // 39
		// EXEC_PHOTO_PATH
		form.setU7FilePath(savedAppDets.get(39) != null ? savedAppDets.get(39).toString() : ""); // 39
		// EXEC_PHOTO_PATH
		form.setU5FilePath(savedAppDets.get(40) != null ? savedAppDets.get(40).toString() : ""); // 40
		// NOT_AFFD_ATTR_PATH
		form.setU6FilePath(savedAppDets.get(41) != null ? savedAppDets.get(41).toString() : ""); // 41
		// ATTR_PHOTO_PATH
		form.setU9FilePath(savedAppDets.get(41) != null ? savedAppDets.get(41).toString() : ""); // 41
		// ATTR_PHOTO_PATH
		form.setU8FilePath(savedAppDets.get(42) != null ? savedAppDets.get(42).toString() : ""); // 42
		// CLAIMNT_PHOTO_PATH
		form.setU10FilePath(savedAppDets.get(43) != null ? savedAppDets.get(43).toString() : ""); // 43
		// PAN_FORM_60_PATH
		form.setU11FilePath(savedAppDets.get(43) != null ? savedAppDets.get(43).toString() : ""); // 43
		// PAN_FORM_60_PATH
		form.setSrOfficeName(savedAppDets.get(44) != null ? savedAppDets.get(44).toString() : "");
		form.setPoaRegNo(savedAppDets.get(45) != null ? savedAppDets.get(45).toString() : "");
		form.setDatePoaReg(savedAppDets.get(46) != null ? savedAppDets.get(46).toString() : "");
		form.setCommonDeedRoleName(savedAppDets.get(47) != null ? savedAppDets.get(47).toString() : "");
		if (savedAppDets.get(48) != null && !savedAppDets.get(48).toString().equalsIgnoreCase("") && !savedAppDets.get(48).toString().equalsIgnoreCase("null")) {
			form.setRelationship(Integer.parseInt(savedAppDets.get(48).toString()));
		} else {
			form.setRelationship(0);
		}
		if (savedAppDets.get(49) != null && !savedAppDets.get(49).toString().equalsIgnoreCase("") && !savedAppDets.get(49).toString().equalsIgnoreCase("null")) {
			form.setExecutantClaimant(Integer.parseInt(savedAppDets.get(49).toString()));
		} else {
			form.setExecutantClaimant(0);
		}
		form.setAuthPerCountry(savedAppDets.get(50) != null ? savedAppDets.get(50).toString() : "");
		form.setAuthPerStatename(savedAppDets.get(51) != null ? savedAppDets.get(51).toString() : "");
		form.setAuthPerDistrict(savedAppDets.get(52) != null ? savedAppDets.get(52).toString() : "");
		form.setAuthPerAddress(savedAppDets.get(53) != null ? savedAppDets.get(53).toString() : "");
		// set 4 properties in form
		form.setGovtOfcCheck(savedAppDets.get(54) != null ? savedAppDets.get(54).toString() : "");
		form.setNameOfOfficial(savedAppDets.get(55) != null ? savedAppDets.get(55).toString() : "");
		form.setDesgOfOfficial(savedAppDets.get(56) != null ? savedAppDets.get(56).toString() : "");
		form.setAddressOfOfficial(savedAppDets.get(57) != null ? savedAppDets.get(57).toString() : "");
		if (savedAppDets.get(1).toString().equalsIgnoreCase("1")) {// organization
			form.setAddressOrgOutMp(savedAppDets.get(58) != null ? savedAppDets.get(58).toString() : "");
			form.setAddressAuthPerOutMp(savedAppDets.get(59) != null ? savedAppDets.get(59).toString() : "");
		} else if (savedAppDets.get(1).toString().equalsIgnoreCase("2")) {// individual
			form.setAddressIndOutMp(savedAppDets.get(58) != null ? savedAppDets.get(58).toString() : "");
		} else {// GOVT OFFICIAL
			form.setAddressGovtOffclOutMp(savedAppDets.get(58) != null ? savedAppDets.get(58).toString() : "");
		}
	}

	private void setTxnPartyDetsInForm(RegCommonForm form, ArrayList finalList) {
		ArrayList savedAppDets = new ArrayList();
		savedAppDets = (ArrayList) finalList.get(1);
		String savedAppDetsStr = savedAppDets.toString().substring(3, (savedAppDets.toString().length() - 3));
		String[] appDets = savedAppDetsStr.split(",");
		form.setHidnRegTxnId(appDets[0]);
		form.setListAdoptedParty(appDets[1]);
		form.setFname(appDets[2]);
		form.setMname(appDets[3]);
		form.setLname(appDets[4]);
		form.setGendar(appDets[5]);
		form.setAge(appDets[6]);
		form.setNationality(appDets[7]);
		if (appDets[1].equals("1")) {
			form.setCountry(appDets[8]);
			form.setStatename(appDets[9]);
			form.setDistrict(appDets[10]);
			form.setOrgaddress(appDets[11]);
		} else if (appDets[1].equals("2")) {
			form.setIndcountry(appDets[8]);
			form.setIndstatename(appDets[9]);
			form.setInddistrict(appDets[10]);
			form.setIndaddress(appDets[11]);
		}
		form.setPostalCode(appDets[12]);
		form.setPhno(appDets[13]);
		form.setMobno(appDets[14]);
		form.setEmailID(appDets[15]);
		form.setListID(appDets[16]);
		form.setIdno(appDets[17]);
		// BANK_NAME
		// BANK_ADDRESS
		form.setOgrName(appDets[20]);
		form.setAuthPerName(appDets[21]);
		form.setFatherName(appDets[22]);
		form.setMotherName(appDets[23]);
		// CATEGORY_ID
		form.setIndCaste(appDets[25]);
		form.setIndReligion(appDets[26]);
		// PARTY_THUMB_IMPRESSION
		// PARTY_PHOTO
		// PARTY_SIGNATURE
		// NOTIFY_MAIL
		// DEED_EXECUTION_DATE
		// DEED_EXECUTED_OUT_INDIA
		// DEED_DOC_INDIA_REACH_DATE
		form.setPartyRoleTypeId(appDets[34].trim());
		// IS_APPLICANT
		// IS_TXN_COMPLETE
		form.setIndDisability(appDets[37]);
		form.setOwnershipShare(appDets[38]);
		form.setRelationWithOwner(appDets[39]);
		// form.setShareOfProp(appDets[40]);
		// DEED_PRESENTATION_DATE
		// EXISTING_REG_DOC_NO_PROP1
		// PROP1_DATE_OF_REG
		// EXISTING_REG_DOC_NO_PROP2
		// PROP2_DATE_OF_REG
		// EXISTING_REG_DOC_NO_PROP
		// PROP_DATE_OF_REG
		// CONSIDERATION_FACE_VALUE
		// NO_OF_BUYERS
		// NO_OF_SELLERS
		// NO_OF_DONORS
		// NO_OF_DONEES
		// NO_OF_PARTY1
		// NO_OF_PARTY2
		// NO_OF_OWNERS_AUTH_POA
		// NO_OF_AUTH_PERSNS_ACCPTNG_POA
		// POA_PERIOD_LESS_MORE_THAN_YEAR
		// NO_OF_OWNERS
		// BANK_NAME_DEPOSIT_DEED
		// BRANCH_NAME_DEPOSIT_DEED
		// BRANCH_ADDRESS_DEPOSIT_DEED
		// AUTH_PERSN_BANK_DEPOSIT_DEED
		// DEPOSITION_WITH_POSSESSION
		// LOAN_AMOUNT
		// SANCTIONED_AMOUNT
		// POA_WITH_CONSIDRTN
		// AUCTION_AMOUNT
		// AUTHORITY_TYPE
		// AUTHORITY_NAME
		// AUTHORITY_ADDRESS
		// AUTHORITY_CONTACT_NO
		// PARTY_ROLE_TYPE_ID
		// CREATED_BY
		// CREATED_DATE
		// UPDATE_BY
		// UPDATE_DATE
		// SPOUSE_NAME
	}

	/**
	 * insertPropertyDetails Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param String
	 *            []
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPropertyDetails(RegCommonForm regInitForm, RegCompletionForm regPropDetsForm) throws Exception {
		boolean insertsuccess = false;
		// RegCommonDAO dao = new RegCommonDAO();
		String[] param = new String[22]; // change by ankit for GIS
		if (regPropDetsForm.getRegcompletDto().getVikasId() != null) {
			param[0] = regPropDetsForm.getRegcompletDto().getVikasId();
		} else {
			param[0] = "";
		}
		param[1] = regPropDetsForm.getRegcompletDto().getRicircle();
		if (regPropDetsForm.getRegcompletDto().getLayoutdet() != null) {
			param[2] = regPropDetsForm.getRegcompletDto().getLayoutdet();
		} else {
			param[2] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getSheetnum() != null) {
			param[3] = regPropDetsForm.getRegcompletDto().getSheetnum();
		} else {
			param[3] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getPlotnum() != null) {
			param[4] = regPropDetsForm.getRegcompletDto().getPlotnum();
		} else {
			param[4] = "";
		}
		param[5] = regPropDetsForm.getRegcompletDto().getPropAddress();
		param[6] = "";
		param[7] = "";
		param[8] = "";
		param[9] = "";
		param[10] = regInitForm.getHidnUserId();
		if (regPropDetsForm.getRegcompletDto().getPropLandmark() != null) {
			param[11] = regPropDetsForm.getRegcompletDto().getPropLandmark();
		} else {
			param[11] = "";
		}
		param[12] = regPropDetsForm.getRegcompletDto().getPropImage1FilePath();
		param[13] = "";// angle 2
		param[14] = "";// angle 3
		param[15] = regPropDetsForm.getRegcompletDto().getPropMapFilePath();
		param[16] = regPropDetsForm.getRegcompletDto().getPropRinFilePath();
		param[17] = regPropDetsForm.getRegcompletDto().getPropKhasraFilePath();
		param[18] = regPropDetsForm.getRegcompletDto().getAuditReportFilePath(); // added
		// by
		// ankit
		// for
		// plant
		// and
		// machinery
		param[19] = regPropDetsForm.getRegcompletDto().getGisReferenceKey(); // added
		// by
		// ankit
		// for
		// GIS
		// work
		param[20] = regInitForm.getPropertyId();
		param[21] = regInitForm.getHidnRegTxnId();
		// String[] khasra = new String[8];
		/*
		 * if (regPropDetsForm.getKhasraNoArray() != null &&
		 * regPropDetsForm.getKhasraAreaArray() != null &&
		 * regPropDetsForm.getLagaanArray() != null &&
		 * regPropDetsForm.getRinPustikaArray() != null &&
		 * !regPropDetsForm.getKhasraNoArray().equalsIgnoreCase("null") &&
		 * !regPropDetsForm.getKhasraAreaArray().equalsIgnoreCase( "null") &&
		 * !regPropDetsForm.getLagaanArray().equalsIgnoreCase("null") &&
		 * !regPropDetsForm.getRinPustikaArray().equalsIgnoreCase( "null") &&
		 * !regPropDetsForm.getKhasraNoArray().equalsIgnoreCase("") &&
		 * !regPropDetsForm.getKhasraAreaArray().equalsIgnoreCase("") &&
		 * !regPropDetsForm.getLagaanArray().equalsIgnoreCase("") &&
		 * !regPropDetsForm.getRinPustikaArray().equalsIgnoreCase("")) {
		 * khasra[0] = regPropDetsForm.getKhasraNoArray(); khasra[1] =
		 * regPropDetsForm.getRinPustikaArray(); khasra[2] =
		 * regPropDetsForm.getKhasraAreaArray(); khasra[3] =
		 * regPropDetsForm.getLagaanArray();
		 * 
		 * khasra[4] = regPropDetsForm.getNorthArray(); khasra[5] =
		 * regPropDetsForm.getSouthArray(); khasra[6] =
		 * regPropDetsForm.getEastArray(); khasra[7] =
		 * regPropDetsForm.getWestArray();
		 * 
		 * } else { khasra = null; }
		 */
		String regStatus[] = new String[2];
		if (regPropDetsForm.getActionName().equalsIgnoreCase(RegInitConstant.NEXT_TO_MAPPING_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_PROP_SAVED;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		} else if (/*
					 * regPropDetsForm.getActionName().equalsIgnoreCase(
					 * RegInitConstant.ADD_MORE_PROP_ACTION) ||
					 */
		regPropDetsForm.getActionName().equalsIgnoreCase(RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		}
		insertsuccess = commonDao.updatePropertyDetails(param, regPropDetsForm.getRegcompletDto().getKhasraDetlsDisplay(), regPropDetsForm.getActionName(), regStatus);
		return insertsuccess;
	}

	public boolean insertPropertyDetailsCLR(RegCommonForm regInitForm, RegCompletionForm regPropDetsForm) throws Exception {
		boolean insertsuccess = false;
		// RegCommonDAO dao = new RegCommonDAO();
		String valuationID = "";
		String clrF = "";
		String[] param = new String[23];
		if (regPropDetsForm.getRegcompletDto().getVikasId() != null) {
			param[0] = regPropDetsForm.getRegcompletDto().getVikasId();
		} else {
			param[0] = "";
		}
		param[1] = regPropDetsForm.getRegcompletDto().getRicircle();
		if (regPropDetsForm.getRegcompletDto().getLayoutdet() != null) {
			param[2] = regPropDetsForm.getRegcompletDto().getLayoutdet();
		} else {
			param[2] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getSheetnum() != null) {
			param[3] = regPropDetsForm.getRegcompletDto().getSheetnum();
		} else {
			param[3] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getPlotnum() != null) {
			param[4] = regPropDetsForm.getRegcompletDto().getPlotnum();
		} else {
			param[4] = "";
		}
		param[5] = regPropDetsForm.getRegcompletDto().getPropAddress();
		param[6] = "";
		param[7] = "";
		param[8] = "";
		param[9] = "";
		param[10] = regInitForm.getHidnUserId();
		if (regPropDetsForm.getRegcompletDto().getPropLandmark() != null) {
			param[11] = regPropDetsForm.getRegcompletDto().getPropLandmark();
		} else {
			param[11] = "";
		}
		param[12] = regPropDetsForm.getRegcompletDto().getPropImage1FilePath();
		param[13] = "";// angle 2
		param[14] = "";// angle 3
		param[15] = regPropDetsForm.getRegcompletDto().getPropMapFilePath();
		param[16] = regPropDetsForm.getRegcompletDto().getPropRinFilePath();
		param[17] = regPropDetsForm.getRegcompletDto().getPropKhasraFilePath();
		// param[17] = ""; //change for multiple clr khasra
		if (regPropDetsForm.getPropertyTypeId().equalsIgnoreCase("3")) {
			param[18] = regPropDetsForm.getClrFlag();
		} else {
			param[18] = "";
		}

		param[19] = regPropDetsForm.getRegcompletDto().getAuditReportFilePath(); // added//
		// by
		// ankit
		// for
		// plant
		// and
		// machinery
		param[20] = regPropDetsForm.getRegcompletDto().getGisReferenceKey(); // added
		// for
		// GIS
		// Reference
		// key

		param[21] = regInitForm.getHidnRegTxnId(); // reg txn id
		param[22] = regInitForm.getPropertyId(); // property id
		String regStatus[] = new String[2];
		if (regPropDetsForm.getActionName().equalsIgnoreCase(RegInitConstant.NEXT_TO_MAPPING_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_PROP_SAVED;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		} else if (/*
					 * regPropDetsForm.getActionName().equalsIgnoreCase(
					 * RegInitConstant.ADD_MORE_PROP_ACTION) ||
					 */
		regPropDetsForm.getActionName().equalsIgnoreCase(RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		}
		insertsuccess = commonDao.updatePropertyDetailsCLR(param, regPropDetsForm.getRegcompletDto().getKhasraDetlsDisplay(), regPropDetsForm.getActionName(), regStatus, regPropDetsForm, regInitForm);

		return insertsuccess;
	}

	/**
	 * for getting property sequence id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropIdSeq() throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		String seq = commonDao.getPropIdSeq();
		if (seq.length() == 1) {
			seq = "0000000" + seq;
		} else if (seq.length() == 2) {
			seq = "000000" + seq;
		} else if (seq.length() == 3) {
			seq = "00000" + seq;
		} else if (seq.length() == 4) {
			seq = "0000" + seq;
		} else if (seq.length() == 5) {
			seq = "000" + seq;
		} else if (seq.length() == 6) {
			seq = "00" + seq;
		} else if (seq.length() == 7) {
			seq = "0" + seq;
		}
		return seq;
	}

	/**
	 * for deleting partial saved applications from db.
	 * 
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean deleteSelectedAppDetails(String appId, int fromAdju) throws Exception // main
	{
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.deleteSelectedAppDetails(appId, fromAdju);
	}

	/**
	 * getRegInitPendingAppStatus for getting pending reg init application
	 * details from db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	/*
	 * public String[] getRegInitPendingAppStatus(String appId) throws Exception
	 * { //RegCommonDAO dao = new RegCommonDAO(); return
	 * commonDao.getRegInitPendingAppStatus(appId); }
	 */
	/**
	 * for getting country name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getCountryName(String countryId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getCountryName(countryId, languageLocale);
	}

	/**
	 * for fetching the adjudication ID from db
	 * 
	 * @param String
	 * @return String
	 * @author SHREERAJ
	 */
	public String getAdjFlag(String regID) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAdjFlag(regID);
	}

	/**
	 * for getting state name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getStateName(String stateId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getStateName(stateId, languageLocale);
	}

	/**
	 * for getting district name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistrictName(String distId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getDistrictName(distId, languageLocale);
	}

	/**
	 * for getting valuation id corresponding to registration app id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getValuationId(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getValuationId(appId);
	}

	/**
	 * getPropDetlsForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDashboard(String appId, String propId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropDetlsForDashboard(appId, propId);
	}

	/**
	 * getPoaForDashboard for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getPoaForDashboard(String appId) throws Exception {
	 * //RegCommonDAO dao = new RegCommonDAO(); return
	 * commonDao.getPoaForDashboard(appId); }
	 */
	/**
	 * getPoaListFromDb for getting poa list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getPoaListFromDb(String appId) throws Exception {
	 * //RegCommonDAO dao = new RegCommonDAO(); return
	 * commonDao.getPoaListFromDb(appId); }
	 */
	/**
	 * getOwnerListFromDb for getting owner list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*
	 * public ArrayList getOwnerListFromDb(String appId) throws Exception {
	 * 
	 * //RegCommonDAO dao = new RegCommonDAO(); return
	 * commonDao.getOwnerListFromDb(appId); }
	 */
	/**
	 * for getting valuation id corresponding to registration app id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyId(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyId(appId);
	}

	/**
	 * getPropertyIdMarketVal for getting PROPERTY ID AND MARKET VALUE details
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketVal(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyIdMarketVal(appId);
	}

	/**
	 * getTransPartyDets for getting transacting party details corresponding to
	 * a PROPERTY ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDets(String propId, String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDets(propId, appId);
	}

	public ArrayList getTransPartyDetsForExchangeDeed(String propId, String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDetsExchangeDeed(propId, appId);
	}

	public ArrayList getTransPartyDetsForExchangeDeedCLR(String propId, String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDetsExchangeDeedCLR(propId, appId);
	}

	public ArrayList getTransPartyDetsForExchangeDeedCLRForOwner(String party_txn_d) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDetsForExchangeDeedCLRForOwner(party_txn_d);
	}

	/**
	 * getTransPartyDets for getting transacting party details corresponding to
	 * a PROPERTY ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Rakesh Kumar Soni
	 */
	public ArrayList getTransPartyDetsCLR(String propId, String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDetsCLR(propId, appId);
	}

	public String getOwnerDetails(String appId, String partyId, String language) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getOwnerDetails(appId, partyId, language);
	}

	public String getOwnerPOAnameDetails(String partyId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getOwnerPOAnameDetails(partyId);
	}

	public ArrayList getTransPartyDetsForPaticular(String particularId, String appId) throws Exception {
		return commonDao.getTransPartyDetsForPaticular(particularId, appId);
	}

	/**
	 * getApplicantPartyDets for getting PROPERTY ID AND MARKET VALUE details
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantPartyDets(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getApplicantPartyDets(appId);
	}

	/**
	 * for getting payment flag corresponding to registration app id from db.
	 * 
	 * @param
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentFlag(String appId, String funId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPaymentFlag(appId, funId);
	}

	/**
	 * for inserting registration initiation payment txn details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPaymentDetails(RegCommonForm regForm, String paymentType) {
		boolean insertsuccess = false;
		// RegCommonDAO dao = new RegCommonDAO();
		String[] param = new String[7];
		param[0] = regForm.getPaymentTxnSeqId();
		param[1] = regForm.getHidnRegTxnId();
		param[2] = regForm.getHidnUserId();
		param[3] = RegInitConstant.PAYMENT_FLAG_INITIATED;
		if (paymentType.equalsIgnoreCase("1")) {
			param[4] = regForm.getTotalBalanceAmount();
			param[5] = "I";
		} else if (paymentType.equalsIgnoreCase("2")) {
			param[4] = regForm.getTotalBalanceAmountFee();
			param[5] = "I";
		} else if (paymentType.equalsIgnoreCase("4")) {
			param[4] = regForm.getTotalMutationFees();
			param[5] = "I";
		} else {
			param[4] = regForm.getTotalBalanceAmountAdjuFee();
			param[5] = "A";
		}
		param[6] = paymentType;
		insertsuccess = commonDao.insertPaymentDetails(param);
		return insertsuccess;
	}

	public String getRegFeeCheck(String reg_txn_id) {
		return (commonDao.getRegFeeCheck(reg_txn_id));
	}

	public ArrayList checkFeeCompletFlag(String reg_txn_id) {
		return (commonDao.checkFeeCompletFlag(reg_txn_id));
	}

	/**
	 * getPartyDetsForViewConfirm for getting APPLICANT PARTY details from db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetsForViewConfirm(String appId, String partyId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPartyDetsForViewConfirm(appId, partyId);
	}

	/**
	 * for getting photo id proof name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPhotoIdProofName(String proofId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPhotoIdProofName(proofId, languageLocale);
	}

	/**
	 * for getting valuation id corresponding to registration app id and
	 * property Id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValuationId(String appId, String propId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropValuationId(appId, propId);
	}

	public String getPropValuationIdPinGeneration(String appId, String propId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropValuationIdPinGeneration(appId, propId);
	}

	/**
	 * getOtherPropDetsForViewConfirm for getting other property details from
	 * db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOtherPropDetsForViewConfirm(String appId, String propId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getOtherPropDetsForViewConfirm(appId, propId);
	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetls(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getDutyDetls(appId);
	}

	/**
	 * insrtMultiplePropDetls Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param form
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	/*
	 * public boolean insrtMultiplePropDetls(RegCommonForm form) throws
	 * Exception // main { boolean insertsuccess = false;
	 * 
	 * // FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION TABLES
	 * RegCommonBO bo = new RegCommonBO();
	 * 
	 * String[] regStatus = { RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,
	 * form.getHidnRegTxnId() };
	 * 
	 * if (form.getAgriPropertyList()!=null &&
	 * form.getAgriPropertyList().size()>0 ) {
	 * 
	 * 
	 * ArrayList<CommonDTO> list = form.getAgriPropertyList();
	 * 
	 * ArrayList paramList1 = new ArrayList(); //ArrayList paramList2 = new
	 * ArrayList();
	 * 
	 * CommonDTO row_list; CommonDTO paramDto1; //CommonDTO paramDto2; String
	 * mapTransPartyPropDetls[][][] = new
	 * String[list.size()][form.getMapTransactingParties().size()][4];
	 * 
	 * for (int i = 0; i < list.size(); i++) { row_list = (CommonDTO)
	 * list.get(i);
	 * 
	 * paramDto1 = new CommonDTO(); //paramDto2 = new CommonDTO();
	 * 
	 * // FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION // TABLES
	 * //RegCommonBO bo = new RegCommonBO(); String[] valPropDetls = bo
	 * .getPropertyDetailsFromValuation(row_list .getValuationId().trim());
	 * 
	 * // FOLLOWING CODE FOR PROPERTY DETAILS
	 * 
	 * paramDto1.setPropertyId(row_list.getPropertyId().trim());
	 * paramDto1.setRegTxnId(form.getHidnRegTxnId());
	 * 
	 * paramDto1.setMarketValue(valPropDetls[0].trim());
	 * paramDto1.setAreaTypeId(valPropDetls[1].trim());
	 * paramDto1.setGovBodyId(valPropDetls[2].trim());
	 * paramDto1.setPropTypeId(valPropDetls[3].trim());
	 * //paramDto1.setL1TypeId(valPropDetls[4].trim());
	 * paramDto1.setL1TypeId(""); if
	 * (valPropDetls[5].trim().equalsIgnoreCase("null") ||
	 * valPropDetls[5].trim().equalsIgnoreCase("")) { paramDto1.setL2TypeId("");
	 * } else { paramDto1.setL2TypeId(valPropDetls[5].trim()); } if
	 * (valPropDetls[6].trim().equalsIgnoreCase("null") ||
	 * valPropDetls[6].trim().equalsIgnoreCase("")) {
	 * paramDto1.setAreaUnitId(""); } else {
	 * paramDto1.setAreaUnitId(valPropDetls[6].trim()); }
	 * 
	 * //paramDto1.setArea(valPropDetls[7].trim()); paramDto1.setArea("");
	 * paramDto1.setDistId(valPropDetls[8].trim());
	 * paramDto1.setTehsilId(valPropDetls[9].trim());
	 * paramDto1.setWardId(valPropDetls[10].trim());
	 * paramDto1.setMohalaId(valPropDetls[11].trim());
	 * 
	 * paramDto1.setUserId(form.getHidnUserId());
	 * paramDto1.setValuationId(row_list.getValuationId().trim());
	 * paramDto1.setSysMv(valPropDetls[12].trim());
	 * 
	 * paramList1.add(paramDto1);
	 * 
	 * // FOLLOWING CODE FOR PROPERTY & TRANSACTING PARTY MAPPING
	 * 
	 * paramDto2.setRegTxnId(form.getHidnRegTxnId());
	 * paramDto2.setPropertyId(row_list.getPropertyId().trim());
	 * paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
	 * paramDto2.setUserId(form.getHidnUserId()); paramList2.add(paramDto2);
	 * 
	 * 
	 * 
	 * if (form.getDeedID() != RegInitConstant.DEED_TRUST_PV) { Collection
	 * mapCollection = form.getMapTransactingParties().values(); Object[] l1 =
	 * mapCollection.toArray(); RegCommonDTO mapDtoDisp = new RegCommonDTO();
	 * for (int j = 0; j < l1.length;j++) { mapDtoDisp = (RegCommonDTO) l1[j];
	 * mapTransPartyPropDetls[i][j][0] = form.getHidnRegTxnId();
	 * mapTransPartyPropDetls[i][j][1] = row_list.getPropertyId().trim();
	 * mapTransPartyPropDetls[i][j][2] = mapDtoDisp.getPartyRoleTypeId();
	 * mapTransPartyPropDetls[i][j][3] = form.getHidnUserId();
	 * 
	 * } } else { mapTransPartyPropDetls = null; }
	 * 
	 * 
	 * } //insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsExchange( //
	 * param, deed, paramList1, paramList2, ownerDetails, form // .getExmpID(),
	 * null); insertsuccess = commonDao.insrtMultiplePropDetls(null,
	 * mapTransPartyPropDetls, regStatus,paramList1,1); } else { String[]
	 * valPropDetls = bo.getPropertyDetailsFromValuation(form
	 * .getValuationId());
	 * 
	 * // FOLLOWING CODE FOR PROPERTY DETAILS String propDetls[] = new
	 * String[19]; propDetls[0] = form.getPropertyId(); propDetls[1] =
	 * form.getHidnRegTxnId(); propDetls[2] = valPropDetls[0].trim(); //
	 * MARKET_VALUE propDetls[3] = valPropDetls[1].trim(); // AREA_TYPE_ID
	 * propDetls[4] = valPropDetls[2].trim(); // GOV_BODY_ID propDetls[5] =
	 * valPropDetls[3].trim(); // PROP_TYPE_ID propDetls[6] = ""; // L1_TYPE_ID
	 * if (valPropDetls[5].trim().equalsIgnoreCase("null")) { propDetls[7] =
	 * null; } else { propDetls[7] = ""; // L2_TYPE_ID //} if
	 * (valPropDetls[6].trim().equalsIgnoreCase("null")) { propDetls[8] = null;
	 * } else { propDetls[8] = ""; // AREA_UNIT_ID //} propDetls[9] = ""; //
	 * AREA propDetls[10] = valPropDetls[8].trim(); // DISTRICT_ID propDetls[11]
	 * = valPropDetls[9].trim(); // TEHSIL_ID propDetls[12] =
	 * valPropDetls[10].trim(); // WARD_ID propDetls[13] =
	 * valPropDetls[11].trim(); // MOHALLA_ID propDetls[14] =
	 * form.getHidnUserId(); propDetls[15] = form.getValuationId();
	 * propDetls[16] = "N"; // PROP_TXN_COMPL_FLAG propDetls[17] =
	 * valPropDetls[0].trim(); // INITIAL_MARKET_VALUE propDetls[18] =
	 * valPropDetls[12].trim(); // SYSTEM_MARKET_VALUE
	 * 
	 * String mapTransPartyPropDetls[][][] = new String[1][form
	 * .getMapTransactingParties().size()][4];
	 * 
	 * if (form.getDeedID() != RegInitConstant.DEED_TRUST_PV) { Collection
	 * mapCollection = form.getMapTransactingParties().values(); Object[] l1 =
	 * mapCollection.toArray(); RegCommonDTO mapDtoDisp = new RegCommonDTO();
	 * for (int i = 0; i < l1.length; i++) { mapDtoDisp = (RegCommonDTO) l1[i];
	 * mapTransPartyPropDetls[0][i][0] = form.getHidnRegTxnId();
	 * mapTransPartyPropDetls[0][i][1] = form.getPropertyId();
	 * mapTransPartyPropDetls[0][i][2] = mapDtoDisp.getPartyRoleTypeId();
	 * mapTransPartyPropDetls[0][i][3] = form.getHidnUserId();
	 * 
	 * } } else { mapTransPartyPropDetls = null; }
	 * 
	 * insertsuccess = commonDao.insrtMultiplePropDetls(propDetls,
	 * mapTransPartyPropDetls, regStatus,null,0);
	 * 
	 * }
	 * 
	 * return insertsuccess; }
	 */
	/**
	 * for getting role id of applicant from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantRoleId(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getApplicantRoleId(appId);
	}

	/**
	 * getShareOfPropList for getting other property details from db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getShareOfPropList(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getShareOfPropList(appId);
	}

	/**
	 * getShareOfPropList for getting other property details from db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantShareHolders(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getApplicantShareHolders(appId);
	}

	/**
	 * for getting ROLE NAME from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getRoleName(String roleId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getRoleName(roleId, languageLocale);
	}

	/**
	 * getTransactingPartyIdSeq Returns string value for sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 */
	public String getTransactingPartyIdSeq() throws Exception {
		return commonDao.getTransactingPartyIdSeq();
	}

	public String getConsenterIdSeq() throws Exception {
		return commonDao.getConsenterIdSeq();
	}

	/**
	 * getTransactingPartyIdSeq Returns string value for sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 */
	public String getTransPartyPropertyMapIdSeq() throws Exception {
		return commonDao.getTransPartyPropertyMapIdSeq();
	}

	/**
	 * getOwnerDetls for getting owner details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getOwnerDetls(String ownerId) throws Exception {
		return commonDao.getOwnerDetls(ownerId);
	}

	/**
	 * getPropDetlsForDutyCalc for getting property details from db for duty
	 * calculation.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDutyCalc(String valuationId) throws Exception {
		return commonDao.getPropDetlsForDutyCalc(valuationId);
	}

	/**
	 * getPaymentTxnId for getting payment transaction id from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentTxnId(String appId, String paymentType) throws Exception {
		return commonDao.getPaymentTxnId(appId, paymentType);
	}

	/**
	 * for inserting registration initiation E STAMP CODE details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEStampCode(RegCommonForm form) {
		String[] param = new String[3];
		param[0] = form.getRegInitEstampCode();
		param[1] = form.getHidnRegTxnId();
		param[2] = form.getRegInitPaymntTxnId();
		return commonDao.insertEStampCode(param);
	}

	/**
	 * for updating registration txn status in db.
	 * 
	 * @param RegCommonForm
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransactionStatus(RegCommonForm form, String uplaodNo, String uploadSize) {
		String[] param = new String[8];
		if (form.getHdnPostalAddress1().equalsIgnoreCase("Y")) {
			param[0] = RegInitConstant.STATUS_COMPLETE;
			param[1] = form.getPostalCountry();
			param[2] = form.getPostalState();
			param[3] = form.getPostalDistrict();
			param[4] = form.getPostalAddress();
		} else {
			param[0] = RegInitConstant.STATUS_COMPLETE;
			param[1] = "";
			param[2] = "";
			param[3] = "";
			param[4] = "";
		}
		param[5] = uplaodNo;
		param[6] = uploadSize;
		param[7] = form.getHidnRegTxnId();
		return commonDao.updateTransactionStatus(param);
	}

	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDeedInstId(String appId) throws Exception {
		return commonDao.getDeedInstId(appId);
	}

	/**
	 * for inserting registration initiation stamp duties details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertStampDuties(RegCommonForm form, String userID) {
		String[] param = {form.getHidnRegTxnId(), form.getStampduty1(), form.getPanchayatDuty(), form.getNagarPalikaDuty(), form.getUpkarDuty(), form.getRegistrationFee(), form.getTotalduty(), userID, form.getMarketValueShares(), form.getDutyPaid(), form.getRegPaid(), form.getConsiAmountTrns()};
		return commonDao.insertStampDuties(param);
	}

	/**
	 * getDeedInstId for getting deed and instrument ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getGuildingFloorDetails(String valId) throws Exception {
		return commonDao.getGuildingFloorDetails(valId);
	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDutyDetlsForConfirmation(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getDutyDetlsForConfirmation(appId);
	}

	/**
	 * for getting deed name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDeedName(String deedId, String languageLocale) throws Exception {
		return commonDao.getDeedName(deedId, languageLocale);
	}

	/**
	 * for getting instrument name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instId, String languageLocale) throws Exception {
		return commonDao.getInstrumentName(instId, languageLocale);
	}

	public String getFamilyFlag(String dutyId) throws Exception {
		return commonDao.getFamilyFlag(dutyId);
	}

	/**
	 * for getting exemption name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getExemptionName(String exmpId, String languageLocale) throws Exception {
		return commonDao.getExemptionName(exmpId, languageLocale);
	}

	/**
	 * getAdjudicationStatus for getting adjudication id and status from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicationStatus(String adjuId) throws Exception {
		return commonDao.getAdjudicationStatus(adjuId);
	}

	/**
	 * for updating reg init id corresponding to adjudication id in database
	 * 
	 * @param String
	 *            regInitId,String adjuId
	 * @param boolean
	 */
	public boolean updateAdjudicationRecords(String regInitId, String adjuId, String userId) {
		return commonDao.updateAdjudicationRecords(regInitId, adjuId, userId);
	}

	/**
	 * getPropertyIdMarketValAdju for getting PROPERTY ID AND MARKET VALUE
	 * details FOR ADJUDICATED APPLICATIONS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketValAdju(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyIdMarketValAdju(appId);
	}

	/**
	 * getAdjudicatedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAdjudicatedDutyDetls(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAdjudicatedDutyDetls(appId);
	}

	// by ankita
	public ArrayList getEstampDet(String tempId) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.getEstampDet(tempId);
	}

	/**
	 * for getting applicant district id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantDistrict(String appId) throws Exception {
		return commonDao.getApplicantDistrict(appId);
	}

	/**
	 * for getting e stamp purpose from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getEStampPurpose(String appId) throws Exception {
		return commonDao.getEStampPurpose(appId);
	}

	/**
	 * for updating updating e stamp purpose of registration in database
	 * 
	 * @param String
	 *            regInitId,String purpose
	 * @param boolean
	 */
	public boolean updateEStampPurpose(String regInitId, String purpose) {
		return commonDao.updateEStampPurpose(regInitId, purpose);
	}

	/**
	 * getAllValuationIdsExchangeDeed for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIdsExchangeDeed(String finalValId) throws Exception {
		return commonDao.getAllValuationIdsExchangeDeed(finalValId);
	}

	/**
	 * getAllValuationIds for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIds(String dutyTxnId) throws Exception {
		return commonDao.getAllValuationIds(dutyTxnId);
	}

	/**
	 * getAllPropDetlsExchangeDeed for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeed(String valId, String dutyTxnId) throws Exception {
		return commonDao.getAllPropDetlsExchangeDeed(valId, dutyTxnId);
	}

	public ArrayList getAllPropDetlsNPVDeed(String valId) throws Exception {
		return commonDao.getAllPropDetlsNPVDeed(valId);
	}

	/**
	 * getExchangeDeedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExchangeDeedDutyDetls(String appId) throws Exception {
		String refValId = getRefValIdExchngDeed(appId, 0); // method for getting
		// reference valuation
		// id in case of
		// exchange deed
		if (refValId != null && !refValId.equalsIgnoreCase("")) {
			return commonDao.getExchangeDeedDutyDetls(refValId);
		} else {
			return null;
		}
	}

	/**
	 * getRefValIdExchngDeed for getting reference valuation id in case of
	 * exchange deed
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRefValIdExchngDeed(String appId, int flag) throws Exception {
		return commonDao.getRefValIdExchngDeed(appId, flag);
	}

	/**
	 * getExchangeDeedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExchangeDeedFinalMV(String appId) throws Exception {
		// method for getting reference valuation id in case of exchange deed
		// String refId = getRefValIdExchngDeed(appId,0);
		// if (refId != null && !refId.equalsIgnoreCase("")) {
		return commonDao.getExchangeDeedFinalMV(appId);
		// } else {
		// return null;
		// }
	}

	/**
	 * getSubClauseListNonBuilding for getting sub clause list non building from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListNonBuilding(String valId) throws Exception {
		return commonDao.getSubClauseListNonBuilding(valId);
	}

	/**
	 * for getting sub clause name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getSubClauseName(String Id) throws Exception {
		return commonDao.getSubClauseName(Id);
	}

	/**
	 * getSubClauseListBuilding for getting sub clause list building from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListBuilding(String valId, String floorId) throws Exception {
		String[] param = {valId, floorId};
		return commonDao.getSubClauseListBuilding(param);
	}

	// added by shruti
	public String getDeedId(int duty_txn_id) {
		return commonDao.getDeedId(duty_txn_id);
	}

	public ArrayList getExempId(int duty_txn_id) {
		return commonDao.getExempId(duty_txn_id);
	}

	public String getInstId(int duty_txn_id) {
		return commonDao.getInstrumentId(duty_txn_id);
	}

	/**
	 * insertDepositDeedApplicantAndBankDetails Returns boolean value in order
	 * to check the insertion.
	 * 
	 * @param form
	 * @author Shruti
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertDepositDeedApplicantAndBankDetails(RegCommonForm form) throws Exception // main
	{
		boolean insertsuccess = false;
		RegCommonBO commonBo = new RegCommonBO();
		// SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		// Date currentDate = new Date();
		// RegCommonDAO dao = new RegCommonDAO();
		// FOLLOWING CODE FOR OWNER DETAILS
		// String ownerDetails[] = new String[19];
		// int partyId=Integer.parseInt(form.getPartyType());
		int partyId = 0;
		int claimantFlag = 0;
		int poaFlag = 0;
		if (form.getCommonDeed() == 1) {
			claimantFlag = form.getExecutantClaimant();
			poaFlag = form.getPoaHolderFlag();
		} else {
			if (form.getPartyType() != null) {
				partyId = Integer.parseInt(form.getPartyType());
			}
			String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(partyId));
			claimantFlag = Integer.parseInt(claimantArr[0].trim());
			poaFlag = Integer.parseInt(claimantArr[1].trim());
		}
		if (poaFlag == RegInitConstant.POA_HOLDER_FLAG) {/*
														 * 
														 * ownerDetails[0] =
														 * form
														 * .getHidnRegTxnId();
														 * // reg txn id if
														 * (form
														 * .getOwnerOgrName(
														 * ).equals("") ||
														 * form.getOwnerOgrName
														 * ().equals(null)) {
														 * ownerDetails[1] =
														 * "2"; // individual
														 * party type
														 * ownerDetails[2] =
														 * form.getOwnerName();
														 * // first name
														 * ownerDetails[12] =
														 * ""; // authorized
														 * person name } else {
														 * ownerDetails[1] =
														 * "1"; // organization
														 * party type
														 * ownerDetails[2] = "";
														 * // first name
														 * ownerDetails[12] =
														 * form.getOwnerName();
														 * // authorized person
														 * // name }
														 * 
														 * ownerDetails[3] =
														 * form
														 * .getOwnerGendar(); //
														 * gender //
														 * ownerDetails
														 * [4]=UserRegistrationBD
														 * .getOracleDate(form.
														 * getOwnerDay(), //
														 * form.getOwnerMonth(),
														 * form.getOwnerYear());
														 * //age to DOB
														 * ownerDetails[4] =
														 * form.getOwnerAge();
														 * // age
														 * ownerDetails[5] =
														 * form
														 * .getOwnerNationality
														 * (); // nationality
														 * 
														 * String address =
														 * form.
														 * getOwnerAddress();
														 * address = address;
														 * ownerDetails[6] =
														 * address; // address
														 * 
														 * ownerDetails[7] =
														 * form.getOwnerPhno();
														 * // phone number
														 * 
														 * if
														 * (form.getOwnerEmailID
														 * () != null &&
														 * !form.getOwnerEmailID
														 * (
														 * ).equalsIgnoreCase(""
														 * ) &&
														 * !form.getOwnerEmailID
														 * (
														 * ).equalsIgnoreCase("null"
														 * )) { ownerDetails[8]
														 * =
														 * form.getOwnerEmailID
														 * ();// email id } else
														 * { ownerDetails[8] =
														 * form
														 * .getOwnerEmailID(); }
														 * ownerDetails[9] =
														 * form
														 * .getOwnerListID(); //
														 * photo proof type id
														 * 
														 * String proof =
														 * form.getOwnerIdno();
														 * proof = proof;
														 * ownerDetails[10] =
														 * proof; // photo proof
														 * number
														 * 
														 * if
														 * (form.getOwnerEmailID
														 * () != null &&
														 * !form.getOwnerEmailID
														 * (
														 * ).equalsIgnoreCase(""
														 * ) &&
														 * !form.getOwnerEmailID
														 * (
														 * ).equalsIgnoreCase("null"
														 * )) { ownerDetails[11]
														 * =
														 * form.getOwnerOgrName
														 * (); } // organization
														 * name else {
														 * ownerDetails[11] =
														 * form
														 * .getOwnerOgrName(); }
														 * ownerDetails[13] =
														 * getTransactingPartyIdSeq
														 * (); // party
														 * transaction // id
														 * ownerDetails[14] =
														 * "O"; // is applicant
														 * O-Owner
														 * ownerDetails[15] =
														 * ""; // share of
														 * property
														 * ownerDetails[16] =
														 * Integer
														 * .toString(RegInitConstant
														 * .ROLE_OWNER_SELF); //
														 * party role // type id
														 * ownerDetails[17] =
														 * form.getHidnUserId();
														 * // CREATED BY
														 * 
														 * ownerDetails[18] =
														 * form
														 * .getAddressOwnerOutMp
														 * ();
														 */
		}
		// FOLLOWING CODE FOR APPLICANT DETAILS
		String[] param;
		if (form.getAge() != null && !form.getAge().isEmpty()) {
			int partyAge = 0;
			partyAge = Integer.parseInt(form.getAge().toString());
			if (partyAge >= 18) {
				param = new String[62];
			} else {
				param = new String[63];
			}
		} else {
			param = new String[62];
		}
		if (form.getCommonDeed() != 1) {
			param[29] = form.getPartyType();
			param[51] = "";
		} else {
			param[29] = "0"; // for common deeds
			param[51] = Integer.toString(form.getExecutantClaimant());
		}
		param[49] = form.getCommonDeedRoleName(); // COMMON DEED ROLE NAME
		param[60] = "";
		param[61] = "";
		if (form.getListAdoptedParty().equalsIgnoreCase("3")) {
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[8] = "1";
			param[9] = "1";
			param[10] = form.getInddistrict();
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y";
			param[30] = form.getHidnUserId();
			param[32] = "";
			param[56] = "Y";
			param[57] = form.getNameOfOfficial();
			param[58] = form.getDesgOfOfficial();
			param[59] = form.getAddressOfOfficial();
			param[60] = form.getAddressGovtOffclOutMp();
			param[61] = "";
		} else if (form.getListAdoptedParty().equalsIgnoreCase("1")) {
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			// param[5] = "";
			param[6] = ""; // AGE
			param[7] = "";
			param[8] = form.getCountry();
			param[9] = form.getStatename();
			param[10] = form.getDistrict();
			String address = form.getOrgaddress();
			address = address;
			param[11] = address;
			param[12] = "";
			param[13] = form.getPhno();
			param[14] = form.getMobno();
			param[15] = "";
			param[16] = "";
			param[17] = "";
			param[18] = form.getOgrName();
			param[19] = form.getAuthPerName();
			// param[20] = "";
			param[21] = "";
			param[22] = ""; // CASTE TO CATEGORY
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y";
			param[25] = ""; // OCCUPATION
			param[26] = "";
			param[27] = form.getRelationWithOwner();
			param[28] = "";
			// param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = "";
			// if (ownerDetails[13] == null) {
			param[32] = "";
			// } else {
			// param[32] = ownerDetails[13];
			// } // self referencing key of owner in case of poa holder
			param[33] = "";
			param[34] = "";
			param[35] = "";
			param[36] = ""; // ST RADIO
			param[37] = ""; // ST CERTIFICATE FIELD
			param[38] = ""; // ST UPLOAD file path
			param[50] = Integer.toString(form.getAuthPerRelationship()); // relationship
			// type
			param[5] = form.getAuthPerGendar();
			param[20] = form.getAuthPerFatherName();
			param[52] = form.getAuthPerCountry();
			param[53] = form.getAuthPerStatename();
			param[54] = form.getAuthPerDistrict();
			String authAddress = form.getAuthPerAddress();
			authAddress = authAddress;
			param[55] = authAddress;
			param[56] = "N";
			param[57] = "";
			param[58] = "";
			param[59] = "";
			param[60] = form.getAddressOrgOutMp();
			param[61] = form.getAddressAuthPerOutMp();
		} else if (form.getListAdoptedParty().equalsIgnoreCase("2")) {
			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = form.getFname();
			param[3] = form.getMname();
			param[4] = form.getLname();
			param[5] = form.getGendar();
			// param[6] = UserRegistrationBD.getOracleDate(form.getUserDay(),
			// form.getUserMonth(), form.getUserYear()); //AGE TO DOB
			param[6] = form.getAge(); // age
			param[7] = form.getNationality();
			param[8] = form.getIndcountry();
			param[9] = form.getIndstatename();
			param[10] = form.getInddistrict();
			String address = form.getIndaddress();
			address = address;
			param[11] = address;
			param[12] = form.getPostalCode();
			param[13] = form.getIndphno();
			param[14] = form.getIndmobno();
			if (form.getEmailID() != null && !form.getEmailID().equalsIgnoreCase("") && !form.getEmailID().equalsIgnoreCase("null")) {
				param[15] = form.getEmailID();
			} else {
				param[15] = "";
			}
			param[16] = form.getListID();
			String proof = form.getIdno();
			proof = proof;
			// Aadhar encryption changes
			/*
			 * if ("7".equalsIgnoreCase(form.getListID())) { param[17] =
			 * proof.isEmpty() ? "" : AadharUtil.encryptWithAES256(proof); }
			 * else {
			 */
			param[17] = proof;
			// }
			param[18] = ""; // organization name
			param[19] = ""; // authorized person name
			param[20] = form.getFatherName();
			param[21] = form.getMotherName();
			param[22] = form.getIndCategory(); // CASTE TO CATEGORY
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y"; // IS APPLICANT
			param[25] = form.getOccupationId(); // OCCUPATION
			param[26] = form.getIndDisability();
			param[27] = form.getRelationWithOwner();
			param[28] = "";
			// param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = form.getSpouseName();
			// if (ownerDetails[13] == null)
			param[32] = "";
			// else
			// param[32] = ownerDetails[13]; // self referencing key of owner
			// in case of poa holder
			if (form.getIndDisabilityDesc() != null && !form.getIndDisabilityDesc().equalsIgnoreCase("") && !form.getIndDisabilityDesc().equalsIgnoreCase("null")) {
				param[33] = form.getIndDisabilityDesc(); // DISABILITY
				// DESCRIPTION
			} else {
				param[33] = "";
			}
			param[34] = form.getIndMinority(); // MINORITY
			param[35] = form.getIndPovertyLine(); // POVERTY LINE
			if (form.getIndCategory().equalsIgnoreCase("1")) {
				param[36] = form.getIndScheduleArea(); // ST RADIO
				if (form.getIndScheduleArea().equalsIgnoreCase("N")) {
					param[37] = form.getPermissionNo(); // ST CERTIFICATE
					// number
					param[38] = form.getFilePath(); // ST UPLOAD file path
				} else {
					param[37] = "";
					param[38] = "";
				}
			} else {
				param[36] = "";
				param[37] = "";
				param[38] = "";
			}
			/*
			 * param[39]=form.getU2FilePath(); //PHOTO_PROOF_PATH param[40]="";
			 * //NOT_AFFD_EXEC_PATH param[41]=""; //EXEC_PHOTO_PATH
			 * param[42]=""; //NOT_AFFD_ATTR_PATH param[43]="";
			 * //ATTR_PHOTO_PATH param[44]=""; //CLAIMNT_PHOTO_PATH
			 * param[45]=""; //PAN_FORM_60_PATH param[46]=""; //SR OFFICE NAME
			 * param[47]=""; //POA REG NO param[48]=""; //POA REG DATE
			 */
			param[50] = Integer.toString(form.getRelationship()); // relationship
			// type
			param[52] = "";
			param[53] = "";
			param[54] = "";
			param[55] = "";
			param[56] = "N";
			param[57] = "";
			param[58] = "";
			param[59] = "";
			param[60] = form.getAddressIndOutMp();
			param[61] = "";
		}
		param[39] = form.getU2FilePath(); // PHOTO_PROOF_PATH
		param[40] = ""; // NOT_AFFD_EXEC_PATH
		param[41] = ""; // EXEC_PHOTO_PATH
		param[42] = ""; // NOT_AFFD_ATTR_PATH
		param[43] = ""; // ATTR_PHOTO_PATH
		param[44] = ""; // CLAIMNT_PHOTO_PATH
		param[45] = ""; // PAN_FORM_60_PATH
		param[46] = ""; // SR OFFICE NAME
		param[47] = ""; // POA REG NO
		param[48] = ""; // POA REG DATE
		if (!(form.getListAdoptedParty().equalsIgnoreCase("3")) && form.getPropReqFlag().equalsIgnoreCase("Y")) {
			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
				// param[41] = form.getU4FilePath(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				param[40] = form.getU3FilePath(); // NOT_AFFD_EXEC_PATH
				param[45] = form.getU10FilePath(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
				RegCommonBO bo = new RegCommonBO();
				// param[41] = form.getU7FilePath(); // EXEC_PHOTO_PATH
				param[41] = ""; // EXEC_PHOTO_PATH
				// param[42] = form.getU5FilePath(); // NOT_AFFD_ATTR_PATH
				param[42] = "";
				param[43] = form.getU6FilePath(); // ATTR_PHOTO_PATH
				param[46] = form.getSrOfficeName(); // SR OFFICE NAME
				param[47] = form.getPoaRegNo(); // POA REG NO
				if (form.getDatePoaReg() != null && !form.getDatePoaReg().equalsIgnoreCase("")) {
					param[48] = bo.convertCalenderDate(form.getDatePoaReg()); // POA
					// REG
					// DATE
				}
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
				// param[44] = form.getU8FilePath(); // CLAIMNT_PHOTO_PATH
				param[44] = ""; // CLAIMNT_PHOTO_PATH
				param[45] = form.getU11FilePath(); // PAN_FORM_60_PATH
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
				param[43] = form.getU9FilePath(); // ATTR_PHOTO_PATH
			}
		}
		// FOLLOWING CODE FOR TRANSACTION DETAILS
		String deed[] = new String[11];
		deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID
		/*
		 * if (form.getDeedID() == RegInitConstant.DEED_LEASE_NPV ||
		 * form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV ||
		 * form.getDeedID() == RegInitConstant.DEED_POA || form.getDeedID() ==
		 * RegInitConstant.DEED_SETTLEMENT_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_WILL_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_TRANSFER_LEASE_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_SURRENDER_LEASE_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_COMPOSITION_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_SECURITY_BOND_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_LETTER_OF_LICENCE_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_AGREEMENT_MEMO_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_TRANSFER_NPV || form.getDeedID() ==
		 * RegInitConstant.DEED_FURTHER_CHARGE_NPV ||
		 * (form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV &&
		 * form.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)) {
		 * 
		 * deed[1] = RegInitConstant.STATUS_APP_SAVED; //
		 * REGISTRATION_TXN_STATUS } else {
		 */
		if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) {
			deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
		} else if (form.getPropReqFlag().equalsIgnoreCase("y") || form.getCommonDeed() == 1) {
			deed[1] = RegInitConstant.STATUS_TRNS_SAVED;
		} else if (form.getCommonDeed() == 0) {
			deed[1] = RegInitConstant.STATUS_SHARES_SAVED;
		}
		/*
		 * if (form.getShareOfProp() != null &&
		 * !form.getShareOfProp().equalsIgnoreCase("")) { if
		 * (form.getShareOfProp().equalsIgnoreCase("100")) { deed[1] =
		 * RegInitConstant.STATUS_TRNS_SAVED; // REGISTRATION_TXN_STATUS } else
		 * { deed[1] = RegInitConstant.STATUS_APP_SAVED; //
		 * REGISTRATION_TXN_STATUS } } else {
		 * 
		 * if (form.getDeedTypeFlag() == 0) { if
		 * (form.getAddMoreTransParty().equalsIgnoreCase("Y")) { deed[1] =
		 * RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS } else {
		 * deed[1] = RegInitConstant.STATUS_TRNS_SAVED; } } else {
		 * 
		 * if (form.getCommonDeed() == 1) { if
		 * (form.getAddMoreTransParty().equalsIgnoreCase("Y")) { deed[1] =
		 * RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS } else {
		 * deed[1] = RegInitConstant.STATUS_TRNS_SAVED; // for // landing // on
		 * // particulars // of // transaction } } else { if
		 * (form.getAddMoreTransParty().equalsIgnoreCase("Y")) { deed[1] =
		 * RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS } else {
		 * deed[1] = RegInitConstant.STATUS_PROP_SAVED; } }
		 * 
		 * } }
		 */
		// }
		deed[2] = Integer.toString(form.getDeedID()); // DEED_ID
		deed[3] = Integer.toString(form.getInstID()); // INSTRUMENTS_ID
		deed[4] = form.getHidnUserId(); // CREATED_BY
		deed[5] = ""; // REF_VAL_TXN_ID
		deed[6] = Integer.toString(form.getDuty_txn_id());
		if (form.getDeedID() == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA) {
			deed[7] = Integer.toString(form.getCancelDeedRadio());
		} else {
			deed[7] = "";
		}
		if (form.getFromAdjudication() == 1) {
			deed[8] = "A";
		} else {
			deed[8] = "";
		}
		if (form.getCancellationLabel() != null && !form.getCancellationLabel().equalsIgnoreCase("")) {
			deed[9] = "Y";
		} else {
			deed[9] = "N";
		}
		deed[10] = form.getTransCheck();
		// set pan 50000 param
		String extraDetls[] = setExtraDetlsParam(form);
		/*
		 * if(form.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
		 * form.getDeedID()==RegInitConstant.DEED_TRUST) {
		 * bankDetls[0]=form.getHidnRegTxnId(); bankDetls[1]=form.getBankName();
		 * bankDetls[2]=form.getBranchName(); String
		 * bankAddress=form.getBankAddress();
		 * bankDetls[3]=bankAddress.replace(",",
		 * RegInitConstant.SPLIT_CONSTANT); bankDetls[4]=form.getBankAuthPer();
		 * bankDetls[5]=Integer.toString(form.getBankLoanAmt());
		 * bankDetls[6]=Integer.toString(form.getBankSancAmt());
		 * bankDetls[7]=form.getHidnUserId(); bankDetls[8]=form.getTrustName();
		 * if(form.getTrustDate()!=null &&
		 * !form.getTrustDate().equalsIgnoreCase("")){ RegCommonBO bo=new
		 * RegCommonBO();
		 * bankDetls[9]=bo.convertCalenderDate(form.getTrustDate()); }else{
		 * bankDetls[9]=form.getTrustDate(); }
		 * 
		 * bankDetls[10]=""; //10-15 for trust deed pv bankDetls[11]="";
		 * bankDetls[12]=""; bankDetls[13]=""; bankDetls[14]="";
		 * bankDetls[15]=""; }else{ bankDetls=null; }
		 */
		/*
		 * if("Y".equalsIgnoreCase((String)form.getOfficialCheck())){
		 * form.setGovtOfcCheck("Y"); param[56]="Y";
		 * param[57]=(String)form.getNameOfOfficial();
		 * param[58]=(String)form.getDesgOfOfficial();
		 * param[59]=(String)form.getAddressOfOfficial(); } else{
		 * form.setGovtOfcCheck("N"); param[56]="N"; param[57]=""; param[58]="";
		 * param[59]=""; }
		 */
		insertsuccess = commonDao.insrtDepositDeedApplcntTransPartyBnkDetls(param, deed, extraDetls, form.getAppOwnerList(), form.getExmpID(), form);
		return insertsuccess;
	}

	public ArrayList getPropTypeL1List(String propId, String languageLocale) throws Exception {
		return commonDao.getPropTypeL1List(propId, languageLocale);
	}

	/**
	 * getDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author Shruti
	 */
	public ArrayList getNonPropDutyDetls(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getNonPropDutyDetls(appId);
	}

	// end of code by shruti
	// FOLLOWING ADDED BY ROOPAM ON OR AFTER 25TH APRIL 2013
	/**
	 * for getting all Categories from DB.
	 * 
	 * @return ArrayList
	 * @param String
	 * @return ArrayList
	 * @author Roopam
	 */
	public ArrayList getCategoryList(String languageLocale) {
		return commonDao.getCategoryList(languageLocale);
	}

	/**
	 * for getting category name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCategoryName(String id, String languageLocale) throws Exception {
		return commonDao.getCategoryName(id, languageLocale);
	}

	/**
	 * getPropertyDetailsFromValuation for getting property details from
	 * valuation tables.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyDetailsFromValuation(String valId) throws Exception {
		return commonDao.getPropertyDetailsFromValuation(valId);
	}

	/**
	 * getExemptionList for getting exemption ids from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExemptionList(String appId) throws Exception {
		return commonDao.getExemptionList(appId);
	}

	/**
	 * getPropDetlsForDisplay for getting PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropDetlsForDisplay(String propId) throws Exception {
		return commonDao.getPropDetlsForDisplay(propId);
	}

	/**
	 * getPropDetlsForDisplay for getting Clr PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author RAKESH
	 */
	public ArrayList getPropDetlsForDisplayClr(String propId) throws Exception {
		return commonDao.getPropDetlsForDisplayClr(propId);
	}

	/**
	 * getPropDetlsForDisplay for getting Clr PROPERTY details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author RAKESH
	 */
	public ArrayList getPartyDetlsForDisplayClr(String propID, String clrKhasraNO) throws Exception {
		return commonDao.getPartyDetlsForDisplayClr(propID, clrKhasraNO);
	}

	/**
	 * getPropKhasraDetlsForDisplay for getting PROPERTY KHASRA details from db.
	 * 
	 * @param String
	 * @return String
	 * @author Rakesh Kumar Soni
	 */
	public String getClrFlagByPropId(String propId) throws Exception {
		return commonDao.getClrFlagByPropId(propId);
	}

	/**
	 * getPropKhasraDetlsForDisplay for getting PROPERTY KHASRA details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropKhasraDetlsForDisplay(String propId) throws Exception {
		return commonDao.getPropKhasraDetlsForDisplay(propId);
	}

	/**
	 * for getting all OCCUPATION list
	 * 
	 * @return ArrayList
	 */
	public ArrayList getOccupationList(String languageLocale) {
		return commonDao.getOccupationList(languageLocale);
	}

	/**
	 * for getting OCCUPATION name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getOccupationName(String id, String languageLocale) throws Exception {
		return commonDao.getOccupationName(id, languageLocale);
	}

	/**
	 * for getting applicant share from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantShare(String id) throws Exception {
		return commonDao.getApplicantShare(id);
	}

	/**
	 * getPartyTxnIdList for getting party txn id list corresponding to property
	 * id given
	 * 
	 * @param String
	 * @return String[]
	 * @author ROOPAM
	 */
	public String[] getPartyTxnIdList(String propId) throws Exception {
		ArrayList list = new ArrayList();
		list = commonDao.getPartyTxnIdList(propId);
		String[] partyIdArr = new String[list.size()];
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList row_list = new ArrayList();
					row_list = (ArrayList) list.get(i);
					String propIds = row_list.toString();
					propIds = propIds.substring(1, propIds.length() - 1);
					String partyId[] = propIds.split(",");
					partyIdArr[i] = partyId[0].trim();
				}
			}
		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdList" + exception);
			logger.debug(exception.getMessage(), exception);
		}
		return partyIdArr;
	}

	/**
	 * getPartyDetailsPdf
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetailsPdf(String partyId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPartyDetailsPdf(partyId);
	}

	/**
	 * getGuidelineRate for getting current guideline rate from db.
	 * 
	 * @param String
	 *            []
	 * @return String
	 * @author ROOPAM
	 */
	public String getGuidelineRate(String[] param) throws Exception {
		return commonDao.getGuidelineRate(param);
	}

	/**
	 * getPropTypeL2List for getting PROPERTY TYPE L2 LIST from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropTypeL2List(String propL1Id, String languageLocale) throws Exception {
		ArrayList list = new ArrayList();
		try {
			ArrayList ret = commonDao.getPropTypeL2List(propL1Id);
			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("property type l2:-" + lst.get(0) + ":" + lst.get(1));
					dto.setPropTypeL2Id((String) lst.get(0));
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setPropTypeL2((String) lst.get(1));
					} else {
						dto.setPropTypeL2((String) lst.get(2));
					}
					// dto.setHdnPropTypeL2((String) lst.get(0) + "~"
					// + (String) lst.get(1));
					list.add(dto);
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * getUnitList for getting UNIT LIST from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getUnitList(String propL1Id, String languageLocale) throws Exception {
		ArrayList list = new ArrayList();
		try {
			ArrayList ret = commonDao.getUnitList(propL1Id);
			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("unit:-" + lst.get(0) + ":" + lst.get(1));
					dto.setUnitTypeId((String) lst.get(0));
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						dto.setUnitType((String) lst.get(1));
						dto.setHdnUnitType((String) lst.get(0) + "~" + (String) lst.get(1));
					} else {
						dto.setUnitType((String) lst.get(2));
						dto.setHdnUnitType((String) lst.get(0) + "~" + (String) lst.get(2));
					}
					list.add(dto);
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * for getting property type name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropertyTypeName(String id, String languageLocale) throws Exception {
		return commonDao.getPropertyTypeName(id, languageLocale);
	}

	public String getPropertyTypeID(String id) throws Exception {
		return commonDao.getPropertyTypeID(id);
	}

	// added by akansha for clr
	public String getClrByClrTable(String id) throws Exception {
		return commonDao.getClrByClrTable(id);
	}

	/**
	 * insertPropertyDetails Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param String
	 *            []
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertPropertyDetailsNonPropDeeds(RegCommonForm regInitForm, RegCompletDTO dto, String status, RegCompletionForm eForm, String[] newDuties) throws Exception {
		boolean insertsuccess = false;
		// String[] splitParam;
		// PROPERTY DETAILS
		String[] param = new String[22];
		// param[0] = dto.getPropertyId(); // PROPERTY ID
		if (dto.getVikasId() != null) {
			param[0] = dto.getVikasId();
		} else {
			param[0] = "";
		}
		param[1] = dto.getRicircle();
		if (dto.getLayoutdet() != null) {
			param[2] = dto.getLayoutdet();
		} else {
			param[2] = "";
		}
		if (dto.getSheetnum() != null) {
			param[3] = dto.getSheetnum();
		} else {
			param[3] = "";
		}
		if (dto.getPlotnum() != null) {
			param[4] = dto.getPlotnum();
		} else {
			param[4] = "";
		}
		param[5] = dto.getPropAddress();
		param[6] = "";
		param[7] = "";
		param[8] = "";
		param[9] = "";
		if (dto.getPropLandmark() != null) {
			param[10] = dto.getPropLandmark();
		} else {
			param[10] = "";
		}
		// splitParam = dto.getAreaTypeId().split("~");
		// param[13] = splitParam[0];
		// splitParam = dto.getGovmunciplId().split("~");
		// param[14] = splitParam[0];
		// splitParam = dto.getPropertyTypeId().split("~");
		// param[15] = splitParam[0];
		// splitParam = dto.getPropTypeL1Id().split("~");
		// param[16] = splitParam[0];
		/*
		 * if (dto.getPropTypeL2Id() != null &&
		 * !dto.getPropTypeL2Id().equalsIgnoreCase("Select")) { splitParam =
		 * dto.getPropTypeL2Id().split("~"); param[17] = splitParam[0]; } else {
		 * param[17] = ""; }
		 */
		/*
		 * if (!param[15].equalsIgnoreCase("2")) { splitParam =
		 * dto.getUnitId().split("~"); param[18] = splitParam[0]; param[19] =
		 * Float.toString(dto.getArea()); } else { param[18] = ""; param[19] =
		 * ""; }
		 */
		param[11] = "N";
		/*
		 * splitParam = dto.getDistId().split("~"); param[21] = splitParam[0];
		 * splitParam = dto.getTehsilId().split("~"); param[22] = splitParam[0];
		 * splitParam = dto.getWardId().split("~"); param[23] = splitParam[0];
		 * splitParam = dto.getMohallaId().split("~"); param[24] =
		 * splitParam[0];
		 */
		param[12] = regInitForm.getHidnUserId();
		param[13] = dto.getPropImage1FilePath();
		param[14] = "";
		param[15] = "";
		param[16] = dto.getPropMapFilePath();
		param[17] = dto.getPropRinFilePath();
		param[18] = dto.getPropKhasraFilePath();
		param[19] = dto.getAuditReportFilePath(); // added by ankit for plant
		// and machinery
		param[20] = dto.getSelectedPropId(); // property id
		param[21] = regInitForm.getHidnRegTxnId(); // REG TXN ID
		// KHASRA DETAILS
		/*
		 * String[] khasra = new String[8]; if (dto.getKhasraNoArray() != null
		 * && dto.getKhasraAreaArray() != null && dto.getLagaanArray() != null
		 * && dto.getRinPustikaArray() != null &&
		 * !dto.getKhasraNoArray().equalsIgnoreCase("null") &&
		 * !dto.getKhasraAreaArray().equalsIgnoreCase("null") &&
		 * !dto.getLagaanArray().equalsIgnoreCase("null") &&
		 * !dto.getRinPustikaArray().equalsIgnoreCase("null") &&
		 * !dto.getKhasraNoArray().equalsIgnoreCase("") &&
		 * !dto.getKhasraAreaArray().equalsIgnoreCase("") &&
		 * !dto.getLagaanArray().equalsIgnoreCase("") &&
		 * !dto.getRinPustikaArray().equalsIgnoreCase("")) { khasra[0] =
		 * dto.getKhasraNoArray(); khasra[1] = dto.getRinPustikaArray();
		 * khasra[2] = dto.getKhasraAreaArray(); khasra[3] =
		 * dto.getLagaanArray();
		 * 
		 * khasra[4] = dto.getNorthArray(); khasra[5] = dto.getSouthArray();
		 * khasra[6] = dto.getEastArray(); khasra[7] = dto.getWestArray(); }
		 * else { khasra = null; }
		 */
		String[] regStatus = {status, regInitForm.getHidnRegTxnId()};
		// FLOOR DETAILS
		// if (dto.getMapBuilding().isEmpty()) {
		/*
		 * insertsuccess = commonDao.insertPropertyDetailsNonPropDeeds(param,
		 * khasra, new HashMap(), regStatus,
		 * getPartyTxnIdsForPropMap(regInitForm));
		 */
		insertsuccess = commonDao.insertPropertyDetailsNonPropDeeds(param, eForm.getRegcompletDto().getKhasraDetlsDisplay(), new HashMap(), regStatus, null, newDuties);
		// }
		/*
		 * else { insertsuccess =
		 * commonDao.insertPropertyDetailsNonPropDeeds(param, khasra,
		 * dto.getMapBuilding(), regStatus,
		 * getPartyTxnIdsForPropMap(regInitForm)); insertsuccess =
		 * commonDao.insertPropertyDetailsNonPropDeeds(param, khasra,
		 * dto.getMapBuilding(), regStatus, null); }
		 */
		return insertsuccess;
	}

	public boolean insertPropertyDetailsNonPropDeedsCLR(RegCommonForm regInitForm, RegCompletDTO dto, String status, RegCompletionForm eForm, String[] newDuties) throws Exception {
		boolean insertsuccess = false;
		// String[] param = new String[21];
		String[] param = new String[22];// changes by ajit CLR- EM deed
		if (dto.getVikasId() != null) {
			param[0] = dto.getVikasId();
		} else {
			param[0] = "";
		}
		param[1] = dto.getRicircle();
		if (dto.getLayoutdet() != null) {
			param[2] = dto.getLayoutdet();
		} else {
			param[2] = "";
		}
		if (dto.getSheetnum() != null) {
			param[3] = dto.getSheetnum();
		} else {
			param[3] = "";
		}
		if (dto.getPlotnum() != null) {
			param[4] = dto.getPlotnum();
		} else {
			param[4] = "";
		}
		param[5] = dto.getPropAddress();
		param[6] = "";
		param[7] = "";
		param[8] = "";
		param[9] = "";
		if (dto.getPropLandmark() != null) {
			param[10] = dto.getPropLandmark();
		} else {
			param[10] = "";
		}
		param[11] = "N";
		param[12] = regInitForm.getHidnUserId();
		param[13] = dto.getPropImage1FilePath();
		param[14] = "";
		param[15] = "";
		param[16] = dto.getPropMapFilePath();
		param[17] = dto.getPropRinFilePath();
		param[18] = dto.getPropKhasraFilePath();
		param[19] = dto.getAuditReportFilePath(); // added by ankit for plant
		// and machinery
		param[20] = dto.getSelectedPropId(); // property id
		param[21] = regInitForm.getHidnRegTxnId(); // REG TXN ID
		String[] regStatus = {status, regInitForm.getHidnRegTxnId()};
		insertsuccess = commonDao.insertPropertyDetailsNonPropDeedsCLR(param, eForm.getRegcompletDto().getKhasraDetlsDisplay(), new HashMap(), regStatus, null, newDuties, eForm);
		return insertsuccess;
	}

	/**
	 * getPropertyListNonPropDeed for getting PROPERTY ID details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyListNonPropDeed(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyListNonPropDeed(appId);
	}

	/**
	 * getTransPartyDetsNonPropDeed for getting transacting party details
	 * corresponding to a registration ID from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsNonPropDeed(String appId) throws Exception {
		return commonDao.getTransPartyDetsNonPropDeed(appId);
	}

	/**
	 * getBuildingFloorDetailsNonProp for getting floor details from db in case
	 * of non property deed
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsNonProp(String propId) throws Exception {
		return commonDao.getBuildingFloorDetailsNonProp(propId);
	}

	/**
	 * for getting payment table primary key sequence id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPaymentTxnIdSeq() throws Exception {
		return commonDao.getPaymentTxnIdSeq();
	}

	/**
	 * getPaidAmounts for getting all paid amounts details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPaidAmounts(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAllPaidAmounts(appId);
	}

	/**
	 * for getting district id from db that is required for generating e stamp
	 * code.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistIdEstamp(String appId) throws Exception {
		return commonDao.getDistIdEstamp(appId);
	}

	/**
	 * for inserting estamp txn id mapped with reg txn id into database.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEstampMappingDetls(String eStampTxnId, RegCommonForm form, String eStampPdfPath) throws Exception {
		String[] param = {eStampTxnId, form.getHidnRegTxnId(), form.getHidnUserId(), eStampPdfPath};
		return commonDao.insertEstampMappingDetls(param);
	}

	/**
	 * getBankDetails for getting bank details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBankDetails(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getBankDetails(appId);
	}

	/**
	 * getPartyIdsExchngPdf for getting party ids of exchange deed for pdf from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyIdsExchngPdf(String appId, int partyCount, int deedId) throws Exception {
		String[] param = new String[3];
		param[0] = "";
		param[1] = "";
		if (partyCount == 1) {
			if (deedId == RegInitConstant.DEED_EXCHANGE) {
				param[0] = Integer.toString(RegInitConstant.ROLE1_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE1_OWNER_SELF);
			} else {
				param[0] = Integer.toString(RegInitConstant.ROLE_CO_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE_CO_OWNER_SELF);
			}
		}
		if (partyCount == 2) {
			param[0] = Integer.toString(RegInitConstant.ROLE2_OWNER_POA_HOLDER);
			param[1] = Integer.toString(RegInitConstant.ROLE2_OWNER_SELF);
		}
		param[2] = appId;
		return commonDao.getPartyIdsExchngPdf(param);
	}

	/**
	 * getPropIdsExchngPdf for getting party ids of exchange deed for pdf from
	 * db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropIdsExchngPdf(String appId, int partyCount, int deedId) throws Exception {
		String[] param = new String[4];
		param[0] = "";
		param[1] = "";
		if (partyCount == 1) {
			if (deedId == RegInitConstant.DEED_EXCHANGE) {
				param[0] = Integer.toString(RegInitConstant.ROLE1_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE1_OWNER_SELF);
			} else {
				param[0] = Integer.toString(RegInitConstant.ROLE_CO_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE_CO_OWNER_SELF);
			}
		}
		if (partyCount == 2) {
			param[0] = Integer.toString(RegInitConstant.ROLE2_OWNER_POA_HOLDER);
			param[1] = Integer.toString(RegInitConstant.ROLE2_OWNER_SELF);
		}
		param[2] = appId;
		param[3] = appId;
		return commonDao.getPropIdsExchngPdf(param);
	}

	/**
	 * getPartyTxnIdListTitleDeed for getting party txn id list corresponding to
	 * reg id given
	 * 
	 * @param String
	 * @return String[]
	 * @author ROOPAM
	 */
	public String[] getPartyTxnIdListTitleDeed(String appId) throws Exception {
		ArrayList list = new ArrayList();
		list = commonDao.getPartyTxnIdListTitleDeed(appId);
		String[] partyIdArr = new String[list.size()];
		try {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ArrayList row_list = new ArrayList();
					row_list = (ArrayList) list.get(i);
					String propIds = row_list.toString();
					propIds = propIds.substring(1, propIds.length() - 1);
					String partyId[] = propIds.split(",");
					partyIdArr[i] = partyId[0].trim();
				}
			}
		} catch (Exception e) {
			logger.debug("Exception in getPartyTxnIdListTitleDeed" + e);
			logger.debug(e.getMessage(), e);
		}
		return partyIdArr;
	}

	/**
	 * getConsidAmtSysMV for getting consideration amount and system calculated
	 * MV from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getConsidAmtSysMV(String valId) throws Exception {
		return commonDao.getConsidAmtSysMV(valId);
	}

	/**
	 * getConsidAmtTitle for getting consideration amount from db for Title
	 * deed.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getConsidAmtTitle(String regId) throws Exception {
		return commonDao.getConsidAmtTitle(regId);
	}

	/**
	 * getBuildingFloorDetailsTitleDeed for getting building floor details for
	 * title deed from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getBuildingFloorDetailsTitleDeed(String propId) throws Exception {
		return commonDao.getBuildingFloorDetailsTitleDeed(propId);
	}

	/**
	 * getPaymentDetlsForDisplay for getting payment matrix details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentDetlsForDisplay(String regId) throws Exception {
		return commonDao.getPaymentDetlsForDisplay(regId);
	}

	/**
	 * updateTransPartyDetails Returns boolean value in order to check the
	 * insertion.
	 * 
	 * @param RegCommonForm
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateTransPartyDetails(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;
		// FOLLOWING CODE FOR OWNER DETAILS
		// String ownerDetails[] = new String[16];
		int partyId = Integer.parseInt(form.getPartyType());
		// int
		// claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(partyId)));
		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());
		/*
		 * if (Integer.parseInt(claimantArr[1].trim()) ==
		 * RegInitConstant.POA_HOLDER_FLAG ||
		 * form.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG) {
		 * 
		 * if (form.getOwnerOgrNameTrns().equals("") ||
		 * form.getOwnerOgrNameTrns().equals(null)) { ownerDetails[0] = "2"; //
		 * individual party type ownerDetails[1] = form.getOwnerNameTrns(); //
		 * first name ownerDetails[11] = ""; // authorized person name } else {
		 * ownerDetails[0] = "1"; // organization party type ownerDetails[1] =
		 * ""; // first name ownerDetails[11] = form.getOwnerNameTrns(); //
		 * authorized person // name }
		 * 
		 * ownerDetails[2] = form.getOwnerGendarTrns(); // gender //
		 * ownerDetails
		 * [3]=UserRegistrationBD.getOracleDate(form.getOwnerDayTrns(), //
		 * form.getOwnerMonthTrns(), form.getOwnerYearTrns()); //age to DOB
		 * ownerDetails[3] = form.getOwnerAgeTrns(); // age ownerDetails[4] =
		 * form.getOwnerNationalityTrns(); // nationality
		 * 
		 * String address = form.getOwnerAddressTrns(); address = address;
		 * ownerDetails[5] = address; // address
		 * 
		 * ownerDetails[6] = form.getOwnerPhnoTrns(); // phone number
		 * 
		 * if (form.getOwnerEmailIDTrns() != null &&
		 * !form.getOwnerEmailIDTrns().equalsIgnoreCase("") &&
		 * !form.getOwnerEmailIDTrns().equalsIgnoreCase("null")) {
		 * ownerDetails[7] = form.getOwnerEmailIDTrns(); // email id } else {
		 * ownerDetails[7] = ""; } ownerDetails[8] = form.getOwnerListIDTrns();
		 * // photo proof type id
		 * 
		 * String proof = form.getOwnerIdnoTrns(); proof = proof;
		 * ownerDetails[9] = proof; // photo proof number
		 * 
		 * if (form.getOwnerOgrNameTrns() != null &&
		 * !form.getOwnerOgrNameTrns().equalsIgnoreCase("") &&
		 * !form.getOwnerOgrNameTrns().equalsIgnoreCase("null")) {
		 * ownerDetails[10] = form.getOwnerOgrNameTrns(); // organization name }
		 * else { ownerDetails[10] = ""; } ownerDetails[12] =
		 * form.getHidnUserId(); // CREATED BY
		 * 
		 * 
		 * ownerDetails[13] = form.getAddressOwnerOutMpTrns();
		 * 
		 * ownerDetails[14] = form.getHidnRegTxnId(); // reg txn id
		 * ownerDetails[15] = form.getOwnerIdTrns(); // party transaction id }
		 * else { ownerDetails = null; }
		 */
		// FOLLOWING CODE FOR APPLICANT DETAILS
		// String[] param = new String[54];
		String[] param = new String[53];
		if (form.getListAdoptedPartyTrns().equalsIgnoreCase("2")) {
			if (form.getGuardianTrans() != null && !"".equalsIgnoreCase(form.getGuardianTrans())) {
				param = new String[54];
				// param[53]=form.getGuardianTrans();
			}
		}
		if (form.getListAdoptedPartyTrns().equalsIgnoreCase("3")) {
			param[6] = form.getInddistrictTrns();
			param[22] = form.getHidnUserId();
			param[45] = "Y";
			param[46] = form.getNameOfOfficialTrns();
			param[47] = form.getDesgOfOfficialTrns();
			param[48] = form.getAddressOfOfficialTrns();
			param[49] = form.getAddressGovtOffclOutMpTrns();
			param[50] = "";
			param[51] = form.getHidnRegTxnId();
			param[52] = form.getPartyTxnId(); // party txn id
		} else if (form.getListAdoptedPartyTrns().equalsIgnoreCase("1")) {
			param[0] = "";
			param[1] = "";
			param[2] = "";
			// param[3] = "";
			param[3] = form.getAuthPerGendarTrns();
			param[4] = ""; // AGE TO DOB
			param[5] = "";
			param[6] = form.getDistrictTrns();
			String address = form.getOrgaddressTrns();
			address = address;
			param[7] = address;
			param[8] = "";
			param[9] = form.getPhnoTrns();
			param[10] = form.getMobnoTrns();
			param[11] = "";
			param[12] = "";
			param[13] = "";
			param[14] = form.getOgrNameTrns();
			param[15] = form.getAuthPerNameTrns();
			// param[16] = "";
			param[16] = form.getAuthPerFatherNameTrns();
			param[17] = "";
			param[18] = ""; // CASTE TO CATEGORY
			param[19] = ""; // OCCUPATION
			param[20] = "";
			param[21] = form.getRelationWithOwnerTrns();
			param[22] = form.getHidnUserId();
			param[23] = "";
			param[24] = "";
			param[25] = "";
			param[26] = "";
			param[27] = ""; // ST RADIO
			param[28] = ""; // ST CERTIFICATE FIELD
			param[29] = ""; // ST UPLOAD file path
			/*
			 * param[30]=""; //PHOTO_PROOF_PATH param[31]="";
			 * //NOT_AFFD_EXEC_PATH param[32]=""; //EXEC_PHOTO_PATH
			 * param[33]=""; //NOT_AFFD_ATTR_PATH param[34]="";
			 * //ATTR_PHOTO_PATH param[35]=""; //CLAIMNT_PHOTO_PATH
			 * param[36]=""; //PAN_FORM_60_PATH param[37]=""; //SR OFFICE NAME
			 * param[38]=""; //POA REG NO param[39]=""; //POA REG DATE
			 */
			// param[40] = ""; // RELATIONSHIP_TYPE_ID
			param[40] = Integer.toString(form.getAuthPerRelationshipTrns()); // RELATIONSHIP_TYPE_ID
			param[41] = form.getAuthPerCountryTrns();
			param[42] = form.getAuthPerStatenameTrns();
			param[43] = form.getAuthPerDistrictTrns();
			param[44] = form.getAuthPerAddressTrns();
			param[49] = form.getAddressOrgOutMpTrns();
			param[50] = form.getAddressAuthPerOutMpTrns();
			param[51] = form.getHidnRegTxnId();
			param[52] = form.getPartyTxnId(); // party txn id
		} else {
			// param[1] = form.getListAdoptedPartyTrns();
			param[0] = form.getFnameTrns();
			param[1] = form.getMnameTrns();
			param[2] = form.getLnameTrns();
			param[3] = form.getGendarTrns();
			if (form.getGuardianTrans() != null && !"".equalsIgnoreCase(form.getGuardianTrans())) {
				param[51] = form.getGuardianTrans();
			} else {
				param[51] = "";
			}
			// param[4] =
			// UserRegistrationBD.getOracleDate(form.getUserDayTrns(),
			// form.getUserMonthTrns(), form.getUserYearTrns()); //AGE TO DOB
			param[4] = form.getAgeTrns();
			param[5] = form.getNationalityTrns();
			param[6] = form.getInddistrictTrns();
			String address = form.getIndaddressTrns();
			address = address;
			param[7] = address;
			param[8] = form.getPostalCodeTrns();
			param[9] = form.getIndphnoTrns();
			param[10] = form.getIndmobnoTrns();
			if (form.getEmailIDTrns() != null && !form.getEmailIDTrns().equalsIgnoreCase("") && !form.getEmailIDTrns().equalsIgnoreCase("null")) {
				param[11] = form.getEmailIDTrns();
			} else {
				param[11] = "";
			}
			param[12] = form.getListIDTrns();
			String proof = form.getIdnoTrns();
			// Aadhar encryption changes
			/*
			 * if ("7".equalsIgnoreCase(form.getListIDTrns())) { param[13] =
			 * proof.isEmpty() ? "" : AadharUtil.encryptWithAES256(proof); }
			 * else {
			 */
			param[13] = proof;
			// }
			param[14] = ""; // organization name
			param[15] = ""; // authorized person name
			param[16] = form.getFatherNameTrns();
			param[17] = form.getMotherNameTrns();
			param[18] = form.getIndCategoryTrns(); // CASTE TO CATEGORY
			param[19] = form.getOccupationIdTrns(); // OCCUPATION
			param[20] = form.getIndDisabilityTrns();
			param[21] = form.getRelationWithOwnerTrns();
			param[22] = form.getHidnUserId();
			param[23] = form.getSpouseNameTrns();
			if (form.getIndDisabilityDescTrns() != null && !form.getIndDisabilityDescTrns().equalsIgnoreCase("") && !form.getIndDisabilityDescTrns().equalsIgnoreCase("null")) {
				param[24] = form.getIndDisabilityDescTrns(); // DISABILITY
				// DESCRIPTION
			} else {
				param[24] = "";
			}
			param[25] = form.getIndMinorityTrns(); // MINORITY
			param[26] = form.getIndPovertyLineTrns(); // POVERTY LINE
			if (form.getIndCategoryTrns().equalsIgnoreCase("1")) {
				param[27] = form.getIndScheduleAreaTrns(); // ST RADIO
				if (form.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
					param[28] = form.getPermissionNoTrns(); // ST CERTIFICATE
					// number
					param[29] = form.getFilePathTrns(); // ST UPLOAD file path
				} else {
					param[28] = "";
					param[29] = "";
				}
			} else {
				param[27] = "";
				param[28] = "";
				param[29] = "";
			}
			param[40] = Integer.toString(form.getRelationshipTrns()); // RELATIONSHIP_TYPE_ID
			param[41] = "";
			param[42] = "";
			param[43] = "";
			param[44] = "";
			param[49] = form.getAddressIndOutMpTrns();
			param[50] = "";
			if (param.length == 54) {
				param[52] = form.getHidnRegTxnId();
				param[53] = form.getPartyTxnId(); // party txn id
			} else {
				param[51] = form.getHidnRegTxnId();
				param[52] = form.getPartyTxnId(); // party txn id
			}
		}
		param[30] = form.getU2FilePathTrns(); // PHOTO_PROOF_PATH
		if (form.getPropReqFlag().equalsIgnoreCase("N") || form.getListAdoptedPartyTrns().equalsIgnoreCase("3")) {
			param[31] = ""; // NOT_AFFD_EXEC_PATH
			param[32] = ""; // EXEC_PHOTO_PATH
			param[33] = ""; // NOT_AFFD_ATTR_PATH
			param[34] = ""; // ATTR_PHOTO_PATH
			param[35] = ""; // CLAIMNT_PHOTO_PATH
			param[36] = ""; // PAN_FORM_60_PATH
			param[37] = ""; // SR OFFICE NAME
			param[38] = ""; // POA REG NO
			param[39] = ""; // POA REG DATE
		} else {
			param[31] = form.getU3FilePathTrns(); // NOT_AFFD_EXEC_PATH
			param[32] = ""; // EXEC_PHOTO_PATH
			param[33] = form.getU5FilePathTrns(); // NOT_AFFD_ATTR_PATH
			param[34] = ""; // ATTR_PHOTO_PATH
			// param[35] = form.getU8FilePathTrns(); // CLAIMNT_PHOTO_PATH
			param[35] = ""; // CLAIMNT_PHOTO_PATH
			param[36] = ""; // PAN_FORM_60_PATH
			param[37] = ""; // SR OFFICE NAME
			param[38] = ""; // POA REG NO
			param[39] = ""; // POA REG DATE
			/*
			 * int deedId=form.getDeedID();
			 * if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE &&
			 * deedId!=RegInitConstant.DEED_LEASE_NPV &&
			 * deedId!=RegInitConstant.DEED_TRUST &&
			 * deedId!=RegInitConstant.DEED_ADOPTION &&
			 * deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA &&
			 * deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
			 * deedId!=RegInitConstant.DEED_POA &&
			 * deedId!=RegInitConstant.DEED_SETTLEMENT_NPV &&
			 * deedId!=RegInitConstant.DEED_WILL_NPV &&
			 * deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
			 * deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV &&
			 * deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
			 * deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV &&
			 * deedId!=RegInitConstant.DEED_PARTITION_NPV &&
			 * deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV &&
			 * deedId!=RegInitConstant.DEED_COMPOSITION_NPV &&
			 * deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV &&
			 * deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV &&
			 * deedId!=RegInitConstant.DEED_TRANSFER_NPV &&
			 * deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV) {
			 */
			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
				// param[32] = form.getU4FilePathTrns(); // EXEC_PHOTO_PATH
				param[32] = ""; // EXEC_PHOTO_PATH
				param[36] = form.getU10FilePathTrns(); // PAN_FORM_60_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_5) {
				RegCommonBO bo = new RegCommonBO();
				// param[32] = form.getU7FilePathTrns(); // EXEC_PHOTO_PATH
				param[32] = ""; // EXEC_PHOTO_PATH
				param[34] = form.getU6FilePathTrns(); // ATTR_PHOTO_PATH
				param[37] = form.getSrOfficeNameTrns(); // SR OFFICE NAME
				param[38] = form.getPoaRegNoTrns(); // POA REG NO
				if (form.getDatePoaRegTrns() != null && !form.getDatePoaRegTrns().equalsIgnoreCase("")) {
					param[39] = bo.convertCalenderDate(form.getDatePoaRegTrns()); // POA
					// REG
					// DATE
				}
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
				param[36] = form.getU11FilePathTrns(); // PAN_FORM_60_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE
			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4 || claimantFlag == RegInitConstant.CLAIMANT_FLAG_6) {
				param[34] = form.getU9FilePathTrns(); // ATTR_PHOTO_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE
			}
			// }
		}
		/*
		 * if("Y".equalsIgnoreCase((String)form.getGovtOfcCheckTrns())){
		 * param[45]="Y"; param[46]=form.getNameOfOfficialTrns();
		 * param[47]=form.getDesgOfOfficialTrns();
		 * param[48]=form.getAddressOfOfficialTrns(); } else{ param[45]="N";
		 * param[46]=""; param[47]=""; param[48]=""; }
		 */
		// if(param.length==54) {
		insertsuccess = commonDao.updateTransPartyDetls(param, form);
		// }
		/*
		 * else{ //insertsuccess = commonDao.updateTransPartyDetls1(param); }
		 */
		return insertsuccess;
	}

	public boolean updateOwnerDetls(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;
		// FOLLOWING CODE FOR OWNER DETAILS
		/* String ownerDetails[] = new String[16]; */
		String ownerDetails[] = new String[34];
		RegCommonBO commonBo = new RegCommonBO();
		if (form.getOwnerOgrName().equals("") || form.getOwnerOgrName().equals(null)) {
			ownerDetails[0] = "2"; // individual party type
			ownerDetails[1] = form.getOwnerName(); // first name
			ownerDetails[11] = ""; // authorized person name
		} else {
			ownerDetails[0] = "1"; // organization party type
			ownerDetails[1] = ""; // first name
			ownerDetails[11] = form.getOwnerName(); // authorized person
			// name
		}
		ownerDetails[2] = form.getOwnerGendar(); // gender
		// ownerDetails[3]=UserRegistrationBD.getOracleDate(form.getOwnerDayTrns(),
		// form.getOwnerMonthTrns(), form.getOwnerYearTrns()); //age to DOB
		ownerDetails[3] = form.getOwnerAge(); // age
		ownerDetails[4] = form.getOwnerNationality(); // nationality
		String address = form.getOwnerAddress();
		ownerDetails[5] = address; // address
		ownerDetails[6] = form.getOwnerPhno(); // phone number
		if (form.getOwnerEmailID() != null && !form.getOwnerEmailID().equalsIgnoreCase("") && !form.getOwnerEmailID().equalsIgnoreCase("null")) {
			ownerDetails[7] = form.getOwnerEmailID(); // email id
		} else {
			ownerDetails[7] = "";
		}
		ownerDetails[8] = form.getOwnerListID(); // photo proof type id
		String proof = form.getOwnerIdno();
		// Aadhar encryption changes
		/*
		 * if ("7".equalsIgnoreCase(form.getOwnerListID())) { ownerDetails[9] =
		 * proof.isEmpty() ? "" : AadharUtil.encryptWithAES256(proof); } else {
		 */
		ownerDetails[9] = proof; // photo proof number
		// }
		if (form.getOwnerOgrName() != null && !form.getOwnerOgrName().equalsIgnoreCase("") && !form.getOwnerOgrName().equalsIgnoreCase("null")) {
			ownerDetails[10] = form.getOwnerOgrName(); // organization name
		} else {
			ownerDetails[10] = "";
		}
		ownerDetails[12] = form.getHidnUserId(); // CREATED BY
		ownerDetails[13] = form.getAddressOwnerOutMp();
		/*
		 * ownerDetails[14] = form.getHidnRegTxnId(); // reg txn id
		 * ownerDetails[15] = form.getOwnerId(); // party transaction id
		 */
		ownerDetails[14] = form.getOwnerIndcountry();
		ownerDetails[15] = form.getOwnerIndstatename();
		ownerDetails[16] = form.getOwnerInddistrict();
		ownerDetails[17] = form.getOwnerPostalCode();
		ownerDetails[18] = form.getOwnerIndphno();
		ownerDetails[19] = form.getOwnerFatherName();
		ownerDetails[20] = form.getOwnerMotherName() == null ? "" : form.getOwnerMotherName();
		ownerDetails[21] = form.getOwnerSpouseName() == null ? "" : form.getOwnerSpouseName();
		ownerDetails[22] = form.getOwnerIndCategory();
		ownerDetails[23] = form.getOwnerIndDisabilityDesc() == null ? "" : form.getOwnerIndDisabilityDesc();
		ownerDetails[24] = form.getOwnerOccupationId();
		ownerDetails[25] = form.getOwnerPermissionNo();
		ownerDetails[26] = form.getOwnerIndDisability();
		ownerDetails[27] = form.getOwnerIndMinority();
		ownerDetails[28] = form.getOwnerIndPovertyLine();
		ownerDetails[29] = form.getOwnerIndScheduleArea();
		ownerDetails[30] = form.getOwnerRelationship();
		ownerDetails[31] = form.getOwnerPanNumber() == null ? "" : form.getOwnerPanNumber();
		ownerDetails[32] = form.getHidnRegTxnId(); // reg txn id
		ownerDetails[33] = form.getOwnerId(); // party transaction id
		insertsuccess = commonDao.updateOwnerDetls(ownerDetails, form);
		return insertsuccess;
	}

	/**
	 * getEstampCode for getting eStamp code from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getEstampCode(String regId) throws Exception {
		return commonDao.getEstampCode(regId);
	}

	public ArrayList getEstampTxnId(String regId) throws Exception {
		return commonDao.getEstampTxnDetls(regId);
	}

	/**
	 * getPendingAppStatus for getting pending reg init application details from
	 * db
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public ArrayList getPendingAppStatus(String appId) throws Exception {
		return commonDao.getPendingAppStatus(appId);
	}

	/**
	 * getPropertyIdApplicant for getting property id of applicant from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdApplicant(String appId) throws Exception {
		return commonDao.getPropertyIdApplicant(appId);
	}

	/**
	 * getLatestPropertyId for getting latest property id from db
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getLatestPropertyId(String appId) throws Exception {
		return commonDao.getLatestPropertyId(appId);
	}

	public ArrayList getAllPropertyId(String appId) throws Exception {
		return commonDao.getAllPropertyId(appId);
	}

	/**
	 * getAllPropDetlsExchangeDeedDash for getting ALL PROPERTY DETAILS from db
	 * for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeedDash(String valId, String dutyTxnId, String appId) throws Exception {
		return commonDao.getAllPropDetlsExchangeDeedDash(valId, dutyTxnId, appId);
	}

	/**
	 * getRemValuationIdsExchangeDeed for getting remaining VALUATION IDS from
	 * db for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getRemValuationIds(String dutyTxnId) throws Exception {
		return commonDao.getRemValuationIds(dutyTxnId);
	}

	/**
	 * getDutyTxnId for getting duty txn id from db
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getDutyTxnId(String appId) throws Exception {
		return commonDao.getDutyTxnId(appId);
	}

	/**
	 * getApplicantAddress for getting applicant district and address from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getApplicantAddress(String appId) throws Exception {
		return commonDao.getApplicantAddress(appId);
	}

	/**
	 * insertTxnDetailsFinalAction for inserting reg txn status from final
	 * action
	 * 
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertTxnDetailsFinalAction(String status, String appId) {
		String[] statusParam = {status, appId};
		return commonDao.insertTxnDetailsFinalAction(statusParam);
	}

	public boolean insertTxnDetailsFinalActionWithRegFee(String status, String appId, String regFeeChk) {
		String[] statusParam = {status, regFeeChk, appId};
		return commonDao.insertTxnDetailsFinalActionWithRegFee(statusParam);
	}

	public boolean updateConsenterFlag(String status, String appId, String flag, String flagConsid) {
		String[] statusParam = {status, flag, flagConsid, appId};
		return commonDao.updateConsenterFlag(statusParam);
	}

	/**
	 * updateBankDetails Returns boolean value in order to check the updation.
	 * 
	 * @param form
	 * @author Roopam
	 * @return boolean
	 * @throws Exception
	 */
	// modified for new instrument - rahul
	public boolean updateBankDetails(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;
		String bankDetls[] = new String[62];
		bankDetls[0] = "";
		bankDetls[1] = "";
		bankDetls[2] = "";
		bankDetls[3] = "";
		bankDetls[4] = "";
		bankDetls[5] = "";
		bankDetls[6] = form.getHidnUserId();
		bankDetls[7] = "";
		bankDetls[8] = "";
		bankDetls[9] = "";
		bankDetls[10] = "";
		bankDetls[11] = "";
		bankDetls[12] = "";
		bankDetls[13] = "";
		bankDetls[14] = "";
		bankDetls[15] = "";
		bankDetls[16] = "";
		bankDetls[17] = "";
		bankDetls[18] = "";
		bankDetls[19] = "";
		bankDetls[20] = "";
		bankDetls[21] = "";
		bankDetls[22] = "";
		bankDetls[23] = "";
		bankDetls[24] = "";
		bankDetls[25] = "";
		bankDetls[26] = "";
		bankDetls[27] = "";
		bankDetls[28] = "";
		bankDetls[29] = "";
		bankDetls[30] = "";
		bankDetls[31] = "";
		bankDetls[32] = "";
		bankDetls[33] = "";
		bankDetls[34] = "";
		bankDetls[35] = "";
		bankDetls[38] = "";
		bankDetls[37] = "";
		bankDetls[38] = "";
		bankDetls[39] = "";
		bankDetls[40] = "";
		bankDetls[41] = "";
		bankDetls[42] = "";
		bankDetls[43] = "";
		bankDetls[44] = "";
		bankDetls[45] = "";
		bankDetls[46] = "";
		bankDetls[47] = "";
		bankDetls[48] = "";
		bankDetls[49] = "";
		bankDetls[50] = "";
		bankDetls[51] = "";
		bankDetls[52] = "";
		bankDetls[53] = "";
		bankDetls[54] = "";
		bankDetls[55] = "";
		bankDetls[56] = "";
		bankDetls[57] = "";
		bankDetls[58] = "";
		bankDetls[59] = "";
		bankDetls[60] = "";
		bankDetls[61] = form.getHidnRegTxnId();
		if (form.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE) {
			bankDetls[0] = form.getBankName();
			bankDetls[1] = form.getBranchName();
			bankDetls[2] = form.getBankAddress().replace(",", RegInitConstant.SPLIT_CONSTANT);
			bankDetls[3] = form.getBankAuthPer();
			bankDetls[4] = form.getBankLoanAmt();
			bankDetls[5] = form.getBankSancAmt();
		}
		if (form.getDeedID() == RegInitConstant.DEED_TRUST || form.getDeedID() == RegInitConstant.DEED_TRUST_PV) {
			bankDetls[7] = form.getTrustName();
			/*
			 * if (form.getTrustDate() != null &&
			 * !form.getTrustDate().equalsIgnoreCase("")) {
			 */
			RegCommonBO bo = new RegCommonBO();
			bankDetls[8] = bo.convertCalenderDate(form.getTrustDate());
			/*
			 * } else { bankDetls[8] = form.getTrustDate(); }
			 */
		}
		if (form.getDeedID() == RegInitConstant.DEED_LEASE_PV || form.getDeedID() == RegInitConstant.DEED_LEASE_NPV) {
			if (!Double.toString(form.getRent()).equalsIgnoreCase("")) {
				bankDetls[9] = Double.toString(form.getRent());
			} else {
				bankDetls[9] = "";
			}
			// bankDetls[9]=Double.toString(form.getRent());
			bankDetls[10] = Double.toString(form.getAdvance());
			bankDetls[11] = Double.toString(form.getDevlpmtCharge());
			if (!Double.toString(form.getOtherRecCharge()).equalsIgnoreCase("")) {
				bankDetls[12] = Double.toString(form.getOtherRecCharge());
			} else {
				bankDetls[12] = "";
			}
			bankDetls[13] = Double.toString(form.getPremium());
			bankDetls[14] = Double.toString(form.getTermLease());
		}
		if (form.getDeedID() == RegInitConstant.DEED_MORTGAGE_PV || form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV) {
			bankDetls[15] = form.getWithPos();
			bankDetls[16] = Double.toString(form.getSecLoanAmt());
		}
		if (form.getDeedID() == RegInitConstant.DEED_AWARD_PV || form.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) {
			bankDetls[17] = form.getFnameArb();
			bankDetls[18] = form.getMnameArb();
			bankDetls[19] = form.getLnameArb();
			bankDetls[20] = form.getGendarArb();
			bankDetls[21] = form.getAgeArb();
			bankDetls[22] = form.getFatherNameArb();
			bankDetls[23] = form.getMotherNameArb();
			bankDetls[24] = form.getSpouseNameArb();
			bankDetls[25] = form.getNationalityArb();
			bankDetls[26] = form.getIndaddressArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
			bankDetls[27] = form.getIndcountryArb();
			bankDetls[28] = form.getIndstatenameArb();
			bankDetls[29] = form.getInddistrictArb();
			bankDetls[30] = form.getIndphnoArb();
			bankDetls[31] = form.getIndmobnoArb();
			if (form.getEmailIDArb() != null && !form.getEmailIDArb().equalsIgnoreCase("")) {
				bankDetls[32] = form.getEmailIDArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
			} else {
				bankDetls[32] = "";
			}
			bankDetls[33] = form.getIndCategoryArb();
			bankDetls[34] = form.getIndDisabilityArb();
			if (form.getIndDisabilityArb().equalsIgnoreCase("Y")) {
				if (form.getIndDisabilityDescArb() != null && !form.getIndDisabilityDescArb().equalsIgnoreCase("")) {
					bankDetls[35] = form.getIndDisabilityDescArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
				} else {
					bankDetls[35] = "";
				}
			} else {
				bankDetls[35] = "";
			}
			bankDetls[36] = form.getListIDArb();
			bankDetls[37] = form.getIdnoArb().replace(",", RegInitConstant.SPLIT_CONSTANT);
		}
		// rahul changes
		if (form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV_LOAN || form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV_LOAN) {
			if (form.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_MEDIA) {
				if (form.getAgreementDetails() != null && !form.getAgreementDetails().equalsIgnoreCase("")) {
					bankDetls[59] = form.getAgreementDetails();
				} else {
					bankDetls[59] = "";
				}
			} else if (form.getInstID() == RegInitConstant.INST_AGREEMENT_MEMO_WORK_CONTRACT) {
				if (form.getContractDetails() != null && !form.getContractDetails().equalsIgnoreCase("")) {
					bankDetls[60] = form.getContractDetails();
				} else {
					bankDetls[60] = "";
				}
			} else {
				// bankDetls[10] = Double.toString(form.getAdvance());
				bankDetls[57] = form.getAdvancePaymntDetails();
				// RegCommonBO bo = new RegCommonBO();
				// bankDetls[38] =
				// bo.convertCalenderDate(form.getAdvancePaymntDate());
				if (form.getInstID() != RegInitConstant.INST_AGREEMENT_MEMO_NPV_WITHOUTPOS) {
					bankDetls[39] = form.getPossGiven();
				}
			}
		}
		if (form.getDeedID() == RegInitConstant.DEED_POA) {
			if (form.getInstID() == RegInitConstant.INST_POA_3) {
				bankDetls[40] = form.getPoaWithConsid();
			}
			if (form.getInstID() == RegInitConstant.INST_SHARE_DEBENTURE) {
				bankDetls[58] = form.getPoaShareDebenture();
			}
			bankDetls[41] = Double.toString(form.getPoaPeriod());
		}
		if (form.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV) {
			bankDetls[42] = Double.toString(form.getPaidLoanAmt());
		}
		if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && (form.getInstID() != RegInitConstant.INST_DISSOLUTION_NPV && form.getInstID() != RegInitConstant.INST_DISSOLUTION_2 && form.getInstID() != RegInitConstant.INST_PARTNERSHIP_EXCLUDING_CASH)) {
			if (form.getContriProp() != null && !form.getContriProp().equalsIgnoreCase("")) {
				bankDetls[43] = form.getContriProp();
			} else {
				bankDetls[43] = "";
			}
		}
		if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && (form.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || form.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) {
			RegCommonBO bo = new RegCommonBO();
			// if (form.getDissolutionDate() != null
			// && !form.getDissolutionDate().equalsIgnoreCase("")) {
			bankDetls[44] = bo.convertCalenderDate(form.getDissolutionDate());
			/*
			 * } else { bankDetls[44] = form.getDissolutionDate(); }
			 */
			// if (form.getRetirmentDate() != null
			// && !form.getRetirmentDate().equalsIgnoreCase("")) {
			// RegCommonBO bo=new RegCommonBO();
			bankDetls[45] = bo.convertCalenderDate(form.getRetirmentDate());
			/*
			 * } else { bankDetls[45] = form.getRetirmentDate(); }
			 */
		}
		if (form.getInstID() == RegInitConstant.INST_ACK_RECEIPT_1) {
			RegCommonBO bo = new RegCommonBO();
			// if (form.getExecutionDate() != null
			// && !form.getExecutionDate().equalsIgnoreCase("")) {
			bankDetls[46] = bo.convertCalenderDate(form.getExecutionDate());
			/*
			 * } else { bankDetls[46] = form.getExecutionDate(); }
			 */
			// if (form.getRegistrationDate() != null
			// && !form.getRegistrationDate().equalsIgnoreCase("")) {
			bankDetls[47] = bo.convertCalenderDate(form.getRegistrationDate());
			/*
			 * } else { bankDetls[47] = form.getRegistrationDate(); }
			 */
			bankDetls[48] = form.getRegistrationNo();
			bankDetls[49] = form.getReceiptAmountDisp();
		}
		if (form.getInstID() == RegInitConstant.INST_BANK_GAURANTEE_1) {
			RegCommonBO bo = new RegCommonBO();
			bankDetls[46] = bo.convertCalenderDate(form.getExecutionDate());
			bankDetls[0] = form.getBankName();
			bankDetls[49] = form.getReceiptAmountDisp();
			bankDetls[50] = form.getPropDetls();
		}
		if (form.getInstID() == RegInitConstant.INST_CONSENT_1) {
			RegCommonBO bo = new RegCommonBO();
			bankDetls[47] = bo.convertCalenderDate(form.getRegistrationDate());
			bankDetls[48] = form.getRegistrationNo();
			bankDetls[51] = form.getDeedNamePreReg();
			bankDetls[52] = form.getDeedTypePreReg();
		}
		if (form.getInstID() == RegInitConstant.INST_DEC_UNDER_MP_1) {
			RegCommonBO bo = new RegCommonBO();
			bankDetls[50] = form.getPropDetls();
			bankDetls[53] = form.getMapOrderNo();
			bankDetls[54] = bo.convertCalenderDate(form.getMapOrderDate());
			bankDetls[55] = form.getTcpOrderNo();
			bankDetls[56] = bo.convertCalenderDate(form.getTcpOrderDate());
		}
		if (form.getInstID() == RegInitConstant.INST_DOC_AMEND_1) {
			RegCommonBO bo = new RegCommonBO();
			bankDetls[47] = bo.convertCalenderDate(form.getRegistrationDate());
			bankDetls[48] = form.getRegistrationNo();
			bankDetls[51] = form.getDeedNamePreReg();
			bankDetls[52] = form.getDeedTypePreReg();
			bankDetls[46] = bo.convertCalenderDate(form.getExecutionDate());
			bankDetls[50] = form.getPropDetls();
		}
		insertsuccess = commonDao.updateBankDetails(bankDetls);
		return insertsuccess;
	}

	/**
	 * getTransPartyDetsNonPropDeed for getting party txn ids from db for prop
	 * trns map non pv deeds
	 * 
	 * @param String
	 * @return String[]
	 * @author ROOPAM
	 */
	public String[] getPartyTxnIdsForPropMap(RegCommonForm form) throws Exception {
		String[] param = {form.getHidnRegTxnId(), form.getHidnRegTxnId()};
		ArrayList list = commonDao.getPartyTxnIdsForPropMap(param, form.getDeedID(), form.getInstID());
		ArrayList rowList;
		String[] strArr = new String[list.size()];
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				rowList = (ArrayList) list.get(i);
				String str = rowList.toString();
				str = str.substring(1, str.length() - 1);
				strArr[i] = str;
			}
			return strArr;
		} else {
			return null;
		}
	}

	/**
	 * for getting extra field label from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExtraFieldLabel(String instId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getExtraFieldLabel(instId, languageLocale);
	}

	/**
	 * for getting claimant flag from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getClaimantFlag(String roleId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getClaimantFlag(roleId);
	}

	/**
	 * for getting user type id from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getUserTypeId(String userId) throws Exception {
		return commonDao.getUserTypeId(userId);
	}

	/**
	 * getDistIdNameRU for getting dist id name of registered user
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameRU(String userId) throws Exception {
		return commonDao.getDistIdNameRU(userId);
	}

	/**
	 * getDistIdNameOfficeNameDRS for getting dist id name, office name of DRS
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameOfficeNameDRS(String officeId) throws Exception {
		return commonDao.getDistIdNameOfficeNameDRS(officeId);
	}

	/**
	 * getDistIdNameSP for getting dist id name of SP
	 * 
	 * @param String
	 *            []
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getDistIdNameSP(String userId) throws Exception {
		return commonDao.getDistIdNameSP(userId);
	}

	/**
	 * for inserting registration adjudication duties details in db.
	 * 
	 * @param RegCommonForm
	 * @return boolean
	 * @author SHREERAJ
	 */
	public boolean insertAdjuDuties(RegCommonForm regForm) {
		boolean insertsuccess = false;
		String[] param = new String[16];
		param[0] = regForm.getHidnRegTxnId();
		param[1] = regForm.getHidnUserId();
		if (regForm.getStampManually().equalsIgnoreCase("Y")) {
			param[2] = regForm.getStampduty1Adju();
			param[3] = regForm.getNagarPalikaDutyAdju(); // MUNICIPAL
			param[4] = regForm.getPanchayatDutyAdju(); // JANPAD
			param[5] = regForm.getUpkarDutyAdju();
			param[6] = regForm.getTotaldutyAdju();
			param[7] = regForm.getRegistrationFeeAdju();
		} else {
			param[2] = regForm.getStampduty1();
			param[3] = regForm.getPanchayatDuty(); // MUNICIPAL
			param[4] = regForm.getNagarPalikaDuty(); // JANPAD
			param[5] = regForm.getUpkarDuty();
			param[6] = regForm.getTotalduty();
			param[7] = regForm.getRegistrationFee();
		}
		param[8] = regForm.getMarketValueShares();
		param[9] = regForm.getDutyPaid();
		param[10] = regForm.getRegPaid();
		param[11] = regForm.getConsiAmountTrns();
		param[12] = regForm.getPurpose();
		param[13] = regForm.getTotalConsenterConsid();
		param[14] = regForm.getExempStamp() != null && ("-").equals(regForm.getExempStamp()) ? "" : regForm.getExempStamp();
		param[15] = regForm.getExempReg() != null && ("-").equals(regForm.getExempReg()) ? "" : regForm.getExempReg();
		insertsuccess = commonDao.insertAdjuDuties(param);
		return insertsuccess;
	}

	/**
	 * for inserting registration adjudication duties details in db.
	 * 
	 * @param RegCommonForm
	 * @return boolean
	 * @author SHREERAJ
	 */
	/*
	 * public boolean insertAdjuDutiesSys(RegCommonForm regForm) { boolean
	 * insertsuccess = false;
	 * 
	 * String[] param = new String[8]; param[0] = regForm.getHidnRegTxnId();
	 * param[1] = regForm.getHidnUserId(); param[2] = regForm.getStampduty1();
	 * param[3] = regForm.getNagarPalikaDuty(); param[4] =
	 * regForm.getPanchayatDuty(); param[5] = regForm.getUpkarDuty(); param[6] =
	 * regForm.getTotalduty(); param[7] = regForm.getRegistrationFee();
	 * insertsuccess=commonDao.insertAdjuDutiesSys(param); return insertsuccess;
	 * }
	 */
	/*
	 * public double calcBalanceReg(int fromAdju,String regTxnId, String
	 * adjuFlag)throws Exception{ return commonDao.calcBalanceReg(
	 * fromAdju,regTxnId, adjuFlag); }
	 */
	/**
	 * getDutyDetls for getting duty details adju from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getDutyDetlsAdjuForConfirmation(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getDutyDetlsAdjuForConfirmation(appId);
	}

	/**
	 * getCommonDeedFlag for getting common deed flag from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCommonDeedFlag(String deedId) throws Exception {
		return commonDao.getCommonDeedFlag(deedId);
	}

	/**
	 * getTransPartyDetsCommonDeed for getting transacting party details
	 * corresponding to a registration id common deed from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDetsCommonDeed(String appId) throws Exception {
		return commonDao.getTransPartyDetsCommonDeed(appId);
	}

	/**
	 * insertParticularsDetails for inserting particular details db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public boolean insertParticularsDetails(HashMap map, String regId, String userId) {
		String statusParam[] = {RegInitConstant.STATUS_PROP_SAVED, regId};
		return commonDao.insertParticularsDetails(map, regId, userId, statusParam);
	}

	/**
	 * getParticularList for getting particular list from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularList(String appId) throws Exception {
		return commonDao.getParticularList(appId);
	}

	/**
	 * getParticularDetails for getting particular details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getParticularDetails(String partId) throws Exception {
		return commonDao.getParticularDetails(partId);
	}

	/**
	 * updateParticularDetails for updating particular details in db
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author Shruti
	 */
	public boolean updateParticularDetails(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;
		String particularDetls[] = new String[3];
		particularDetls[0] = form.getParticularName();
		particularDetls[1] = form.getParticularDesc();
		particularDetls[2] = form.getParticularTxnId();
		insertsuccess = commonDao.updateParticularDetails(particularDetls);
		return insertsuccess;
	}

	/**
	 * getPartyDetailsCommonDeedsPdf
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetailsCommonDeedsPdf(String partyId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPartyDetailsCommonDeedsPdf(partyId);
	}

	/**
	 * getRemarks for getting REMARKS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public String getRemarks(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getRemarks(appId);
	}

	/**
	 * updateAdjudicationFlag for updating the adjudicated apllication status
	 * from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public boolean updateAdjudicationFlag(String appId, String userId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.updateAdjudicationFlag(appId, userId);
	}

	/**
	 * for getting all relationship list
	 * 
	 * @return ArrayList
	 */
	public ArrayList getRelationshipList(String gender, String languageLocale) {
		return commonDao.getRelationshipList(gender, languageLocale);
	}

	/**
	 * for getting relationship name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRelationshipName(String id, String languageLocale) throws Exception {
		return commonDao.getRelationshipName(id, languageLocale);
	}

	/**
	 * for getting ExecutantClaimant drop down for universal deeds
	 * 
	 * @author ROOPAM
	 * @date 22 October 2013
	 * @return ArrayList
	 */
	public ArrayList getExecutantClaimant(String languageLocale, int instId, String govtOffcl) {
		return commonDao.getExecutantClaimant(languageLocale, instId, govtOffcl);
	}

	/**
	 * for getting ExecutantClaimant name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExecutantClaimantName(String id) throws Exception {
		return commonDao.getExecutantClaimantName(id);
	}

	public String getUnitName(String proofId, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getUnitName(proofId, languageLocale);
	}

	public String getPropertyL1Name(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL1Name(id, languageLocale);
	}

	public String getPropertyL2Name(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL2Name(id, languageLocale);
	}

	public String getFloorName(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getFloorName(id, languageLocale);
	}

	public String getAppleteName(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAppleteName(id, languageLocale);
	}

	public String getOfficeName(String id, String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getOfficeName(id, languageLocale);
	}

	public boolean updateStatus(String id, String status) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.updateStatus(id, status);
	}

	/**
	 * for getLatestPropertyType from db.
	 * 
	 * @param String
	 *            regTxnId
	 * @return String
	 * @author ROOPAM
	 */
	public String getLatestPropertyType(String regTxnId) throws Exception {
		return commonDao.getLatestPropertyType(regTxnId);
	}

	public ArrayList getPropIdsExchangeAgriDisp(String regTxnId) throws Exception {
		return commonDao.getPropIdsExchangeAgriDisp(regTxnId);
	}

	/**
	 * for getInstrumentFlag from db.
	 * 
	 * @param String
	 *            instID
	 * @return String[]
	 * @author SHREERAJ
	 */
	public String[] getInstrumentFlag(String instID) throws Exception {
		String flag[] = new String[3];
		ArrayList instFlags = commonDao.getInstrumentFlag(instID);
		// for(int i=0;i<instFlags.size();i++){
		// }
		ArrayList subList = (ArrayList) instFlags.get(0);
		if (subList.get(0) != null && subList.get(1) != null && subList.get(2) != null) {
			flag[0] = subList.get(0).toString();
			flag[1] = subList.get(1).toString();
			flag[2] = subList.get(2).toString();
		}
		return flag;
	}

	public ArrayList getAllPartiesProperties(String regTxnID, int flag) throws Exception {
		return (commonDao.getAllPartiesProperties(regTxnID, flag));
	}

	public ArrayList getAllPartiesPropertiesCLR(String regTxnID, int flag) throws Exception {
		return (commonDao.getAllPartiesPropertiesCLR(regTxnID, flag));
	}

	public boolean insertPartyPropMapAll(HashMap map, RegCommonForm form) throws Exception {
		return (commonDao.insertPartyPropMapAll(map, form));
	}

	public ArrayList getAllProperties(String regTxnID) throws Exception {
		return (commonDao.getAllProperties(regTxnID));
	}

	public ArrayList getAllPropertiesForShareMapping(String regTxnID) throws Exception {
		return (commonDao.getAllPropertiesForShareMapping(regTxnID));
	}

	public boolean getAllPropertiesCLR(String regTxnID, String userId) throws Exception {
		return (commonDao.getAllPropertiesCLR(regTxnID, userId));
	}

	public ArrayList getAllPropertiesCLRcount(String regTxnID) throws Exception {
		return (commonDao.getAllPropertiesCLRcount(regTxnID));
	}

	public ArrayList getAllPropertiesWithType(String regTxnID) throws Exception {
		return (commonDao.getAllPropertiesWithType(regTxnID));
	}

	public boolean updatePartyPropShares(HashMap map, RegCommonForm form, int saveMappingFlag) throws Exception {
		return (commonDao.updatePartyPropShares(map, form, saveMappingFlag));
	}

	// added by Shreeraj
	public ArrayList getAllParticular(String regTxnID) throws Exception {
		return (commonDao.getAllParticular(regTxnID));
	}

	public ArrayList getAllPartiesParticulars(String regTxnID, int flag) throws Exception {
		return (commonDao.getAllPartiesParticulars(regTxnID, flag));
	}

	public boolean insertPartyParticularMapAll(HashMap map, RegCommonForm form) throws Exception {
		return (commonDao.insertPartyParticularMapAll(map, form));
	}

	public boolean updatePartyPropShares(HashMap map, RegCompletionForm form) throws Exception {
		return (commonDao.updatePartyPropShares(map, form));
	}

	public boolean insertPartyParticularMap(HashMap map, RegCommonForm form) throws Exception {
		return (commonDao.insertPartyParticularMap(map, form));
	}

	/**
	 * for getTransPartyDetsParticular from db.
	 * 
	 * @param String
	 *            instID
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getTransPartyDetsParticular(String propId, String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDetsParticular(propId, appId);
	}

	public boolean insertPartyPropMapAll(HashMap map, RegCompletionForm form) throws Exception {
		return (commonDao.insertPartyPropMapAll(map, form));
	}

	public String getPartyType1Or2(int deedId, int instId, int roleId) throws Exception {
		return (commonDao.getPartyType1Or2(deedId, instId, roleId));
	}

	public String getRoleIdOfParent(String party_txn_id) throws Exception {
		return (commonDao.getRoleIdOfParent(party_txn_id));
	}

	public String getPartyTypeFlag(String party_txn_id) throws Exception {
		return (commonDao.getPartyTypeFlag(party_txn_id));
	}

	public ArrayList getAllParties(String regTxnID) throws Exception {
		return (commonDao.getAllParties(regTxnID));
	}

	public ArrayList getAllExemptions(String dutyTxnID) throws Exception {
		return (commonDao.getAllExemptions(dutyTxnID));
	}

	public ArrayList getAllUserEnterables(String dutyTxnID, String languageLocale) throws Exception {
		return (commonDao.getAllUserEnterables(dutyTxnID, languageLocale));
	}

	public ArrayList<CommonDTO> getPropertyType(String language) {
		return (commonDao.getPropertyType(language));
	}

	public ArrayList<CommonDTO> getArea(String language) throws Exception {
		return (commonDao.getArea(language));
	}

	public ArrayList getTehsil(String language, String districtId) {
		return (commonDao.getTehsil(language, districtId));
	}

	public ArrayList getCancellationLabel(String dutyTxnID) throws Exception {
		return (commonDao.getCancellationLabel(dutyTxnID));
	}

	public ArrayList getPropWiseUserEnterables(String dutyTxnID, String propDutyId, String languageLocale) throws Exception {
		return (commonDao.getPropWiseUserEnterables(dutyTxnID, propDutyId, languageLocale));
	}

	public ArrayList getPropertiesWithDuties(String regTxnID, String dutyTxnId) throws Exception {
		return (commonDao.getPropertiesWithDuties(regTxnID, dutyTxnId));
	}

	public ArrayList getConsenterWithDuties(String regTxnID) throws Exception {
		return (commonDao.getConsenterWithDuties(regTxnID));
	}

	public boolean insrtPropDetlsNonPVDeeds(RegCompletionForm form) throws Exception {
		boolean insertsuccess = false;
		ArrayList<CommonDTO> list;
		list = form.getExchangePropertyList();
		ArrayList<CommonDTO> paramList1 = new ArrayList<CommonDTO>();
		CommonDTO row_list;
		CommonDTO paramDto1;
		for (int i = 0; i < list.size(); i++) {
			row_list = (CommonDTO) list.get(i);
			paramDto1 = new CommonDTO();
			// FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION TABLES
			RegCommonBO bo = new RegCommonBO();
			String[] valPropDetls = bo.getPropertyDetailsFromValuation(row_list.getValuationId().trim());
			// FOLLOWING CODE FOR PROPERTY DETAILS
			paramDto1.setPropertyId(row_list.getPropertyId().trim());
			paramDto1.setRegTxnId(form.getRegInitForm().getHidnRegTxnId());
			paramDto1.setMarketValue("");
			paramDto1.setAreaTypeId(valPropDetls[1].trim());
			paramDto1.setGovBodyId(valPropDetls[2].trim());
			paramDto1.setPropTypeId(valPropDetls[3].trim());
			// paramDto1.setL1TypeId(valPropDetls[4].trim());
			paramDto1.setL1TypeId("");
			/*
			 * if (valPropDetls[5].trim().equalsIgnoreCase("null") ||
			 * valPropDetls[5].trim().equalsIgnoreCase("")) {
			 */
			paramDto1.setL2TypeId("");
			/*
			 * } else { paramDto1.setL2TypeId(valPropDetls[5].trim()); }
			 */
			/*
			 * if (valPropDetls[6].trim().equalsIgnoreCase("null") ||
			 * valPropDetls[6].trim().equalsIgnoreCase("")) {
			 */
			paramDto1.setAreaUnitId("");
			/*
			 * } else { paramDto1.setAreaUnitId(valPropDetls[6].trim()); }
			 */
			// paramDto1.setArea(valPropDetls[7].trim());
			paramDto1.setArea("");
			paramDto1.setDistId(valPropDetls[8].trim());
			paramDto1.setTehsilId(valPropDetls[9].trim());
			paramDto1.setWardId(valPropDetls[10].trim());
			paramDto1.setMohalaId(valPropDetls[11].trim());
			paramDto1.setUserId(form.getRegInitForm().getHidnUserId());
			paramDto1.setValuationId(row_list.getValuationId().trim());
			paramDto1.setSysMv("");
			// //////
			PropertyValuationBO pvBO = new PropertyValuationBO();
			String instClrflag = getInstrumentClrFlag(String.valueOf(form.getInstID()));
			if (instClrflag != null && !instClrflag.isEmpty() && !instClrflag.equalsIgnoreCase("")) {

				if (instClrflag.equalsIgnoreCase("y")) {

					form.setClrFlag(pvBO.getTehsilClrFlag("", valPropDetls[9].trim()));
				}

				else {

					form.setClrFlag("");
				}

			}

			else {

				form.setClrFlag("");
			}
			// form.setClrFlag(pvBO.getTehsilClrFlag("",
			// valPropDetls[9].trim()));
			if (form.getClrFlag() != null && !form.getClrFlag().isEmpty()) {
				if (form.getClrFlag().equalsIgnoreCase("Y")) {
					String lgdCode = (pvBO.lgdCode(valPropDetls[11].trim()));
					if (lgdCode != null && !lgdCode.isEmpty()) {
						form.setClrFlag("Y");
					} else {
						form.setClrFlag("");
					}
				}
			} else {
				form.setClrFlag("");
			}
			paramList1.add(paramDto1);
		}
		insertsuccess = commonDao.insrtPropDetlsNonPVDeeds(paramList1, form);
		return insertsuccess;
	}

	public boolean insertAdditionalUploads(RegCommonForm regForm) {
		return commonDao.insertAdditionalUploads(regForm);
	}

	public boolean insertAdditionalUploadsConsenter(RegCommonForm regForm) {
		return commonDao.insertAdditionalUploadsConsenter(regForm);
	}

	public boolean insertAdditionalUploadsAdju(RegCommonForm regForm) {
		return commonDao.insertAdditionalUploadsAdju(regForm);
	}

	public boolean insertAdditionalUploads(RegCompletionForm eForm) {
		return commonDao.insertAdditionalUploads(eForm);
	}

	public boolean insertAdditionalUploads(RegCommonDTO regDTO, RegCommonForm regForm) {
		return commonDao.insertAdditionalUploads(regDTO, regForm);
	}

	public ArrayList<CommonDTO> getAdditonalUploadsAdju(String appId) {
		ArrayList li = commonDao.getAdditonalUploadsAdju(appId);
		ArrayList<CommonDTO> dtoList = null;
		if (li != null && li.size() > 0) {
			dtoList = new ArrayList<CommonDTO>();
			for (int i = 0; i < li.size(); i++) {
				CommonDTO dto = new CommonDTO();
				ArrayList l = (ArrayList) li.get(i);
				dto.setDocumentPath((String) l.get(0));
				dto.setDocumentName((String) l.get(1));
				dto.setDocContents(DMSUtility.getDocumentBytes((String) l.get(0)));
				// dto.setUniqueIdUpload((String)l.get(2));
				dto.setUniqueIdUpload((String) l.get(2));
				dtoList.add(dto);
			}
		} else {
			return null;
		}
		return dtoList;
	}

	public ArrayList<CommonDTO> getAdditonalUploads(String appId, String partyId) {
		ArrayList li = commonDao.getAdditonalUploads(appId, partyId);
		ArrayList<CommonDTO> dtoList = null;
		if (li != null && li.size() > 0) {
			dtoList = new ArrayList<CommonDTO>();
			for (int i = 0; i < li.size(); i++) {
				CommonDTO dto = new CommonDTO();
				ArrayList l = (ArrayList) li.get(i);
				dto.setDocumentPath((String) l.get(0));
				dto.setDocumentName((String) l.get(1));
				dto.setDocContents(DMSUtility.getDocumentBytes((String) l.get(0)));
				// dto.setUniqueIdUpload((String)l.get(2));
				dto.setUniqueIdUpload((String) l.get(2));
				dtoList.add(dto);
			}
		} else {
			return null;
		}
		return dtoList;
	}

	public ArrayList<CommonDTO> getAdditonalUploadsConsenter(String appId, String consenterId) {
		ArrayList li = commonDao.getAdditonalUploadsConsenter(appId, consenterId);
		ArrayList<CommonDTO> dtoList = null;
		if (li != null && li.size() > 0) {
			dtoList = new ArrayList<CommonDTO>();
			for (int i = 0; i < li.size(); i++) {
				CommonDTO dto = new CommonDTO();
				ArrayList l = (ArrayList) li.get(i);
				dto.setDocumentPath((String) l.get(0));
				dto.setDocumentName((String) l.get(1));
				dto.setDocContents(DMSUtility.getDocumentBytes((String) l.get(0)));
				// dto.setUniqueIdUpload((String)l.get(2));
				dto.setUniqueIdUpload((String) l.get(2));
				dtoList.add(dto);
			}
		} else {
			return null;
		}
		return dtoList;
	}

	public ArrayList<CommonDTO> getAdditonalUploadsP(String appId, String propId) {
		ArrayList li = commonDao.getAdditonalUploadsP(appId, propId);
		ArrayList<CommonDTO> dtoList = null;
		if (li.size() > 0) {
			dtoList = new ArrayList<CommonDTO>();
			for (int i = 0; i < li.size(); i++) {
				CommonDTO dto = new CommonDTO();
				ArrayList l = (ArrayList) li.get(i);
				dto.setDocumentPath((String) l.get(0));
				dto.setDocumentName((String) l.get(1));
				dto.setDocContents(DMSUtility.getDocumentBytes((String) l.get(0)));
				// dto.setUniqueIdUpload((String)l.get(2));
				dto.setUniqueIdUpload((String) l.get(2));
				dtoList.add(dto);
			}
		} else {
			return null;
		}
		return dtoList;
	}

	public boolean deleteALLRemovedUploads(String hidnRegTxnId, String partyTxnId) {
		return commonDao.deleteALLRemovedUploads(hidnRegTxnId, partyTxnId);
	}

	public boolean deleteAllRemovedUploadsConsenter(String hidnRegTxnId, String ConsenterId) {
		return commonDao.deleteAllRemovedUploadsConsenter(hidnRegTxnId, ConsenterId);
	}

	public void validateDeedDraftId(RegCommonForm reg, String lang) {
		commonDao.validateDeedDraftId(reg, lang);
	}

	public void consumeDeedDraftId(RegCommonForm reg, String lang, HttpServletResponse response) throws Exception {
		commonDao.consumeDeedDraftId(reg, lang, response);
	}

	public String getPlotTotArea(String valId) {
		return commonDao.getPlotTotArea(valId);
	}

	public String getAgriTotArea(String valId) {
		return commonDao.getAgriTotArea(valId);
	}

	public ArrayList getMPCSTParams(String valId) {
		return commonDao.getMPCSTParams(valId);
	}

	public ArrayList getDeedDraftId(String appId) {
		return commonDao.getDeedDraftId(appId);
	}

	public boolean deleteALLRemovedUploadsP(String hidnRegTxnId, String propertyId) {
		return commonDao.deleteALLRemovedUploadsP(hidnRegTxnId, propertyId);
	}

	public boolean insertAdditionalUploads(RegCompletDTO mapDto1, RegCompletionForm form) {
		return commonDao.insertAdditionalUploads(mapDto1, form);
	}

	public String getAdjuFee(String funId) {
		return commonDao.getAdjuFee(funId);
	}

	public int getMaxDeedPage(String deedDraftID) {
		return commonDao.getMaxDeedPage(deedDraftID);
	}

	public boolean updateDeedDetails(String deedName, String deedPath, String regTxnID, int noofPages) {
		return commonDao.updateDeedDetails(deedName, deedPath, regTxnID, noofPages);
	}

	public boolean insertConsenterDetails(RegCommonForm form, String action) {
		String[] param = new String[15];
		/*
		 * if (form.getRegDTO().getConsenterPhotoId() != null &&
		 * form.getRegDTO().getConsenterPhotoId().equalsIgnoreCase("7")) { param
		 * = new String[16]; try { param[15] = form.getAadharName().isEmpty() ?
		 * "" : AadharUtil.encryptWithAES256(form.getAadharName()); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } form.setAadharName(""); }
		 */
		param[0] = form.getRegDTO().getConsenterFirstName();
		param[1] = form.getRegDTO().getConsenterAge();
		param[2] = form.getRegDTO().getConsenterRelation();
		param[3] = form.getRegDTO().getConsenterFatherName();
		param[4] = form.getRegDTO().getConsenterAddress();
		param[5] = "1";
		param[6] = form.getRegDTO().getConsenterState();
		param[7] = form.getRegDTO().getConsenterDistrict();
		param[8] = form.getRegDTO().getConsenterPhotoId();
		// Aadhar encryption changes
		/*
		 * if ("7".equalsIgnoreCase(form.getRegDTO().getConsenterPhotoId())) {
		 * try { param[9] =
		 * form.getRegDTO().getConsenterPhotoIdNumber().isEmpty() ? "" :
		 * AadharUtil
		 * .encryptWithAES256(form.getRegDTO().getConsenterPhotoIdNumber()); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else {
		 */
		param[9] = form.getRegDTO().getConsenterPhotoIdNumber();
		// }
		param[10] = form.getHidnRegTxnId();
		param[11] = form.getHidnUserId();
		param[12] = form.getConsenterId();
		if (form.getConsenterWithConsidFlag().equalsIgnoreCase("Y")) {
			param[13] = form.getRegDTO().getConsenterConsidAmount();
		} else {
			param[13] = "0";
		}
		param[14] = form.getConsenterWithConsidFlag();
		return commonDao.insertConsenterDetails(param, action, form);
	}

	public boolean updateConsenterDetails(RegCommonForm form) {
		String[] param = new String[13];
		param[0] = form.getRegDTO().getConsenterFirstName();
		param[1] = form.getRegDTO().getConsenterAge();
		param[2] = form.getRegDTO().getConsenterRelation();
		param[3] = form.getRegDTO().getConsenterFatherName();
		param[4] = form.getRegDTO().getConsenterAddress();
		param[5] = "1";
		param[6] = form.getRegDTO().getConsenterState();
		param[7] = form.getRegDTO().getConsenterDistrict();
		param[8] = form.getRegDTO().getConsenterPhotoId();
		// Aadhar encryption changes
		/*
		 * if ("7".equalsIgnoreCase(form.getRegDTO().getConsenterPhotoId())) {
		 * try { param[9] =
		 * form.getRegDTO().getConsenterPhotoIdNumber().isEmpty() ? "" :
		 * AadharUtil
		 * .encryptWithAES256(form.getRegDTO().getConsenterPhotoIdNumber()); }
		 * catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } else {
		 */
		param[9] = form.getRegDTO().getConsenterPhotoIdNumber();
		// }
		param[10] = form.getHidnUserId();
		param[11] = form.getConsenterId();
		param[12] = form.getHidnRegTxnId();
		return commonDao.updateConsenterDetails(param, form);
	}

	public boolean deleteConsenterDetails(String[] consenterIds, String regTxnId) {
		return commonDao.deleteConsenterDetails(consenterIds, regTxnId);
	}

	public ArrayList getConsenters(String regTxnID) {
		return commonDao.getConsenters(regTxnID);
	}

	public ArrayList getConsenterFlag(String regTxnID) {
		return commonDao.getConsenterFlag(regTxnID);
	}

	public ArrayList getAddConsenterFlag(String regTxnID) {
		return commonDao.getAddConsenterFlag(regTxnID);
	}

	public ArrayList getConsenterDetailsView(String regTxnID, String consenterId) {
		return commonDao.getConsenterDetailsView(regTxnID, consenterId);
	}

	public boolean copyFinalConsenterDuties(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;
		insertsuccess = commonDao.copyFinalConsenterDuties(form.getHidnRegTxnId(), form.getHidnUserId(), Integer.toString(form.getDuty_txn_id()));
		return insertsuccess;
	}

	public String getTotalConsenterConsidAmount(String regTxnId) {
		return commonDao.getTotalConsenterConsidAmount(regTxnId);
	}

	public String getTotalConsenterConsidAmountFinal(String regTxnId) {
		return commonDao.getTotalConsenterConsidAmountFinal(regTxnId);
	}

	/*
	 * public boolean updateMPCSTdata(RegCompletDTO dto,RegCompletionForm form)
	 * {
	 * 
	 * String[] param = {
	 * dto.getFile1Path(),dto.getFile2Path(),form.getImgHash1(),
	 * form.getImgHash2(),dto.getHash1(),dto.getHash2(),
	 * form.getUserID(),form.getIGRS_DATA_AVLBL
	 * (),form.getPropertyId(),form.getHidnRegTxnId()};
	 * 
	 * return commonDao.updateMPCSTdata(param); }
	 */
	public boolean updateMPCSTdata(String[] param) {
		return commonDao.updateMPCSTdata(param);
	}

	public ArrayList getMPCSTdata(String propID) {
		return commonDao.getMPCSTdata(propID);
	}

	public ArrayList getOwnersHashMap(String partyTxnId, String regTxnId) {
		return commonDao.getOwnersHashMap(partyTxnId, regTxnId);
	}

	public ArrayList getOwnersHashMapAllDetails(String partyTxnId) {
		return commonDao.getOwnersHashMapAllDetails(partyTxnId);
	}

	public ArrayList getBuilderOwnerShares(String propID, int dutyTxnID) {
		return commonDao.getBuilderOwnerShares(propID, dutyTxnID);
	}

	public ArrayList getAllUploadsCountAndSize(String appId) {
		return commonDao.getAllUploadsCountAndSize(appId);
	}

	public ArrayList getPrintForm(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPrintForm(appId);
	}

	public ArrayList getConsentersList(String regTxnID) {
		return commonDao.getConsenterList(regTxnID);
	}

	public String getRoleNameForPOA(String roleId) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.getRoleNameForPOA(roleId);
	}

	public String getRoleNameForPOACLR(String roleId) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.getRoleNameForPOACLR(roleId);
	}

	public String getOwnerPartyDescForOrg(String languageLocale, String hidnRegTxnId) {
		return commonDao.getOwnerPartyDescForOrg(languageLocale, hidnRegTxnId);
	}

	public String getOwnerPartyPoaDescForOrg(String languageLocale, String hidnRegTxnId) {
		return commonDao.getOwnerPartyPoaDescForOrg(languageLocale, hidnRegTxnId);
	}

	public String getOwnerPartyDesc(String languageLocale, String hidnRegTxnId) {
		return commonDao.getOwnerPartyDesc(languageLocale, hidnRegTxnId);
	}

	public String getOwnerPartyPoaName(String languageLocale, String hidnRegTxnId) {
		return commonDao.getOwnerPartyPoaName(languageLocale, hidnRegTxnId);
	}

	public ArrayList getOwnerPoaDescDisplay(String languageLocale, String hidnRegTxnId) throws Exception {
		return commonDao.getOwnerPoaDescDisplay(languageLocale, hidnRegTxnId);
	}

	public ArrayList getOwnerPoaDescDisplayForOrg(String languageLocale, String hidnRegTxnId) throws Exception {
		return commonDao.getOwnerPoaDescDisplayForOrg(languageLocale, hidnRegTxnId);
	}

	public String getExecutantClaimantNameForPOA(String id) throws Exception {
		return commonDao.getExecutantClaimantNameForPOA(id);
	}

	public String getMinorName(String partyID) throws Exception {
		return commonDao.getMinorName(partyID);
	}

	public boolean check2(String etxnId) {
		// TODO Auto-generated method stub
		return commonDao.check2(etxnId);
	}

	public ArrayList getDeedDocDetails(String regNum) throws Exception {
		return commonDao.getDeedDocDetails(regNum);
	}

	public String getDistrictID(String txnId) throws Exception {
		return commonDao.getDistrictID(txnId);
	}

	public String getPropTypeId(String txnId) throws Exception {
		return commonDao.getPropTypeId(txnId);
	}

	public String getTehsilID(String txnId) throws Exception {
		return commonDao.getTehsilID(txnId);
	}

	public String getDistrictClrFlag(String language, String districtId) {
		return commonDao.getDistrictClrFlag(language, districtId);
	}

	public String getClrFlag(String propId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getClrFlag(propId);
	}

	public String getClrFlagByValId(String valTxnId) {
		return commonDao.getClrFlagByValId(valTxnId);
	}

	public String getClrFlagForConsenter(String regTxnId) {
		return commonDao.getClrFlagForConsenter(regTxnId);
	}

	public String getGovtOffcName(String govtOffcId, String languageLocale) {
		return commonDao.getGovtOffcName(govtOffcId, languageLocale);
	}

	public String getValTxnID(String propId) throws Exception {
		return commonDao.getValTxnID(propId);
	}

	public ArrayList getGovtOfficeName(String languageLocale) {
		return commonDao.getGovtOfficeName(languageLocale);
	}

	public boolean updatePropertyKhasraDetls(RegCompletionForm form, String userId) throws Exception {
		return commonDao.updatePropertyKhasraDetls(form, userId);
	}

	public boolean updatePropertyKhasraDetls1(RegCompletionForm eForm) throws Exception {
		return commonDao.updatePropertyKhasraDetls1(eForm);
	}

	public boolean insertClrPropOwnerDetails(RegCompletionForm eForm) throws Exception {
		return commonDao.insertClrPropOwnerDetails(eForm);
	}

	public ArrayList getkhasraDetails(String propertyId) {
		return commonDao.getkhasraDetails(propertyId);
	}

	public ArrayList getPropertyTypeIdAndClrFlag(String id) throws Exception {
		return commonDao.getPropertyTypeIdAndClrFlag(id);
	}

	// added for adhar EKYC by ankit -- start
	/*
	 * public boolean insertAadharFailTx(String aadharNo, String partyTxnId,
	 * String regInitNo, String consenterTxnId, String errCode, String errMsg,
	 * String transCode, String ekyc) throws Exception { String adhar[] = new
	 * String[9]; //adhar[0] = "adharSeq"; // from DB Utility adhar[1] =
	 * aadharNo != null ? AadharUtil.encryptWithAES256(aadharNo) : ""; adhar[2]
	 * = partyTxnId != null ? partyTxnId : ""; adhar[3] = regInitNo != null ?
	 * regInitNo : ""; adhar[4] = consenterTxnId != null ? consenterTxnId : "";
	 * adhar[5] = errCode != null ? errCode : ""; adhar[6] = errMsg != null ?
	 * errMsg : ""; adhar[7] = transCode != null ? transCode : ""; adhar[8] =
	 * ekyc != null ? ekyc : ""; return commonDao.insertAadharFailTx(adhar); }
	 */
	public String getUserTxnId(String UserId) {
		return commonDao.getUserTxnIdByUserId(UserId);
	}

	public String getSellableArea(String propId, String valuationId, String getPropType) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getSellableArea(propId, valuationId, getPropType);
	}

	public ArrayList getAllKhasraNoCLR(String propId) throws Exception {
		return commonDao.getAllKhasraNoCLR(propId);
	}

	public String getOwnerNameCLR(String party_txn) throws Exception {
		return commonDao.getOwnerNameCLR(party_txn);
	}

	public String getPoaPartyTxnId(String id) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPoaPartyTxnId(id);
	}

	public String getApplicantFlag(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getApplicantFlag(appId);
	}

	/**
	 * for inserting stamp duties detls manually entered by DR in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	// changes to insert the stamp details manually entered by DR on
	// Adjudication Fee payment page - By Santosh
	/*
	 * public boolean insertAdjuStampDetls(RegCommonForm form,String userId) {
	 * 
	 * String[] param = { form.getStampduty1Adju(),
	 * form.getNagarPalikaDutyAdju(),form.getPanchayatDutyAdju() ,
	 * form.getUpkarDutyAdju(), form.getRegistrationFeeAdju()
	 * ,form.getTotaldutyAdju(),form.getHidnRegTxnId(),userId,form.getPurpose()
	 * };
	 * 
	 * return commonDao.insertAdjuStampDetls(param); }
	 */
	//
	public ArrayList getKhasraToValidate(String id) throws Exception {
		return commonDao.getKhasraToValidate(id);
	}

	// Added by ankit for plant and machinery
	public String getMovPropFlag(String deedID, String instID) {
		return commonDao.getMovPropFlag(deedID, instID);
	}

	public String getCLRcensusCode(String appId, String propID) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getCLRcensusCode(appId, propID);
	}

	public String getClrRCMSFlag(String propID) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getClrRCMSFlag(propID);
	}

	// addded by ankit for prop val changes
	public String[] getInstrumentFlagDeedPDF(String instID) throws Exception {
		String flag[] = new String[4];
		ArrayList instFlags = commonDao.getInstrumentFlagDeedPDF(instID);
		// for(int i=0;i<instFlags.size();i++){
		// }
		ArrayList subList = (ArrayList) instFlags.get(0);
		if (subList.get(0) != null && subList.get(1) != null && subList.get(2) != null) {
			flag[0] = subList.get(0).toString();
			flag[1] = subList.get(1).toString();
			flag[2] = subList.get(2).toString();
			if (subList.get(3) != null) {
				flag[3] = subList.get(3).toString();
			} else {
				flag[3] = "N";
			}
		}
		return flag;
	}

	// addded by ankit for female rebate Work
	public boolean checkFemaleRebate(String regTxnId) throws Exception {
		boolean flag = true;
		ArrayList femaleFlagsListPOA = commonDao.getFemaleFlagsRegisteredPOA(regTxnId);
		if (femaleFlagsListPOA != null) {
			if (femaleFlagsListPOA.size() > 0) {
				return false;
			}
		}
		ArrayList femaleFlagsList = commonDao.getFemaleFlagsRegistered(regTxnId);
		if (femaleFlagsList != null) {
			if (femaleFlagsList.size() > 0) {
				for (int i = 0; i < femaleFlagsList.size(); i++) {
					ArrayList subList = (ArrayList) femaleFlagsList.get(i);
					logger.info("checkFemaleRebate size - " + subList.size());
					if (subList.size() > 0) {
						if (subList.get(2) != null) {// subList.get(0) != null
							// && subList.get(1) !=
							// null &&
							// exemption ID check and where buyer is not female
							// Integer.parseInt(subList.get(0).toString().trim())
							// == 188 &&
							if (!"F".equalsIgnoreCase(subList.get(2).toString().trim())) {
								// logger.info("checkFemaleRebate exemption id - "+subList.get(0).toString());
								// logger.info("checkFemaleRebate gender - "+subList.get(2).toString());
								flag = false;
								// break;
								return flag;
							}
							if (!"2".equalsIgnoreCase(subList.get(1).toString().trim())) {
								flag = false;
								return flag;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	public String getInstrumentClrFlag(String instId) {
		return commonDao.getInstrumentClrFlag(instId);
	}

	// addded by ankit for GIS Work
	public String getMunGISFlag(String govMunID) throws Exception {

		String flag = commonDao.getMunGISFlag(govMunID);

		return flag;
	}

	// added by ankit for GIS work
	public boolean checkGISDuplicate(String gisRefKey, String propId) {
		boolean flag = false;
		String dupVal = commonDao.getGISIDDuplicate(gisRefKey, propId);
		int gisDupCount = Integer.parseInt(dupVal);
		if (gisDupCount > 0) {
			flag = true;
		}
		return flag;
	}

	// added by ankit for GIS work
	public boolean UpdateGISId(String gisId, String propId, String regInitNo, String userId, RegCommonForm regForm) {
		String gisArry[] = new String[6];
		// GIS_TXN_ID,PROPERTY_ID,REG_TXN_ID,GIS_REFERENCE_KEY,GIS_REFERENCE_KEY_OLD,UPDATED_BY,UPDATED_DA
		// gisArry[0] = "GIS_TXN_ID"; // from DB Utility
		gisArry[1] = regForm.getPropertyId();
		gisArry[2] = regInitNo;
		gisArry[3] = regForm.getGisReferenceKeyChecker();
		gisArry[4] = regForm.getRegcompletDto().getGisReferenceKey();
		gisArry[5] = userId;
		return commonDao.updateGISId(gisId, propId, gisArry);
	}

	// added by Ankit for GIS work
	// added for adhar EKYC by ankit -- start
	public boolean insertGISUpdateTxn(String regInitNo, String userId, RegCommonForm regForm) throws Exception {
		String gisArry[] = new String[6];
		// GIS_TXN_ID,PROPERTY_ID,REG_TXN_ID,GIS_REFERENCE_KEY,GIS_REFERENCE_KEY_OLD,UPDATED_BY,UPDATED_DA
		// gisArry[0] = "GIS_TXN_ID"; // from DB Utility
		gisArry[1] = regForm.getPropertyId();
		gisArry[2] = regInitNo;
		gisArry[3] = regForm.getGisReferenceKeyChecker();
		gisArry[4] = regForm.getRegcompletDto().getGisReferenceKey();
		gisArry[5] = userId;

		return commonDao.insertGISUpdateTxn(gisArry);
	}

	public String rcmsCheck(String regTxnId) throws Exception {
		String rcmsFlag = "";
		rcmsFlag = commonDao.rcmsCheck(regTxnId);
		return rcmsFlag;
	}
	public String rcmsCheckCyber(String regTxnId) throws Exception {
		String rcmsFlag = "";
		rcmsFlag = commonDao.rcmsCheckCyber(regTxnId);
		return rcmsFlag;
	}
	public String distinctTehCount(String regTxnId) throws Exception {
		String tehCount = "";
		tehCount = commonDao.distinctTehCount(regTxnId);
		return tehCount;
	}
	public String getRCMSFee() throws Exception {
		String tehCount = "";
		tehCount = commonDao.getRCMSFee();
		return tehCount;
	}
	public String getRcmsFeeStatus(String regTxnId) throws Exception {
		String tehCount = "";
		tehCount = commonDao.getRcmsFeeStatus(regTxnId);
		return tehCount;
	}
	public ArrayList getFormData(String formName) throws Exception {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		list = commonDao.getFormData(formName);
		FormOneDTO fDto;
		for (int i = 0; i < list.size(); i++) {
			ArrayList tempList = (ArrayList) list.get(i);
			String declarationId = (String) tempList.get(1);
			if (!"1010".equalsIgnoreCase(declarationId)) {
				fDto = new FormOneDTO();
				fDto.setDeclarationId(declarationId);
				ArrayList declarationList = commonDao.getDeclarationData(formName, declarationId);
				ArrayList<DeclarationDTO> decDtoList = new ArrayList<DeclarationDTO>();
				for (int j = 0; j < declarationList.size(); j++) {
					ArrayList tempDecList = (ArrayList) declarationList.get(j);
					DeclarationDTO decDto = new DeclarationDTO();
					decDto.setDeclarationTxnId("");
					decDto.setDeclarationId((String) tempDecList.get(0));
					decDto.setDeclarationSubId((String) tempDecList.get(2));
					decDto.setDeclarationNameEnglish((String) tempDecList.get(3));
					decDto.setDeclarationNameHindi((String) tempDecList.get(4));
					if ("Y".equalsIgnoreCase((String) tempDecList.get(5))) {
						decDto.setUserEntrableFlag("Y");
						String userEnterableId = (String) tempDecList.get(6);
						ArrayList<FormUserEnterableDTO> userEnterableForm = new ArrayList<FormUserEnterableDTO>();
						ArrayList enterableFieldList = commonDao.getUserEnterableFieldName(userEnterableId);
						for (int k = 0; k < enterableFieldList.size(); k++) {
							FormUserEnterableDTO userDto = new FormUserEnterableDTO();
							ArrayList userField = (ArrayList) enterableFieldList.get(k);
							userDto.setValueEntered("");
							userDto.setUserEnterableId((String) userField.get(0));
							userDto.setUserEnterableFieldNameEnglish((String) userField.get(3));
							userDto.setUserEnterableFieldNameHindi((String) userField.get(4));
							userEnterableForm.add(userDto);
						}
						decDto.setUserEnterableData(userEnterableForm);
					} else {
						decDto.setUserEntrableFlag("N");
					}
					decDtoList.add(decDto);
				}
				fDto.setDeclaration(decDtoList);
				returnList.add(fDto);
			}
		}
		return returnList;
	}

	public boolean insertFormOneData(String star[], ArrayList<FormOneDTO> formData, String[] fieldValue, String regTxnId, String userId, String formName, ArrayList<PartyDetailsDTO> sellerInfo, ArrayList courtData) throws Exception {
		boolean insertStatus = false;
		List<String> fl = Arrays.asList(fieldValue);

		for (int i = 0; i < formData.size(); i++) {
			FormOneDTO fDto = formData.get(i);
			ArrayList<DeclarationDTO> decDtoList = fDto.getDeclaration();
			for (int j = 0; j < decDtoList.size(); j++) {
				DeclarationDTO decDto = decDtoList.get(j);
				String declrationTxnid = decDto.getDeclarationId();
				if (!"1010".equalsIgnoreCase(declrationTxnid)) {
					if (fl.contains(declrationTxnid)) {
						// String formName="FORM_VIB";
						String formSequenceId = commonDao.getFormSequenceId();
						String arr[] = {formSequenceId, formName, declrationTxnid, regTxnId, userId, decDto.getUserEntrableFlag()};
						insertStatus = commonDao.insertFormDetail(arr);
						if ("Y".equalsIgnoreCase(decDto.getUserEntrableFlag())) {
							ArrayList<FormUserEnterableDTO> userEnterableDtoList = decDto.getUserEnterableData();
							for (int k = 0; k < userEnterableDtoList.size(); k++) {
								FormUserEnterableDTO userDto = userEnterableDtoList.get(k);
								String userTxnId = commonDao.getUserTxnIdSequenceId();
								String userFieldId = userDto.getUserEnterableId();
								String userFieldValue = userDto.getValueEntered();
								String arr1[] = {userTxnId, formSequenceId, userFieldId, regTxnId, userId, userFieldValue};
								insertStatus = commonDao.insertUserEnterableDetail(arr1);
							}
						}
					}
				}
			}
		}
		if (fl.contains("1010")) {
			String formSequenceId = commonDao.getFormSequenceId();
			String arr[] = {formSequenceId, formName, "1010", regTxnId, userId, "Y"};
			insertStatus = commonDao.insertFormDetail(arr);
			String[] s1 = (String[]) courtData.get(0);
			String[] s2 = (String[]) courtData.get(1);
			String[] s3 = (String[]) courtData.get(2);
			for (int i = 0; i < s1.length; i++) {
				String userTxnId = commonDao.getUserTxnIdSequenceId();
				String arr2[] = {userTxnId, formSequenceId, "2004", regTxnId, userId, s1[i]};
				String arr3[] = {userTxnId, formSequenceId, "2005", regTxnId, userId, s2[i]};
				String arr4[] = {userTxnId, formSequenceId, "2006", regTxnId, userId, s3[i]};
				insertStatus = commonDao.insertUserEnterableDetail(arr2);
				insertStatus = commonDao.insertUserEnterableDetail(arr3);
				insertStatus = commonDao.insertUserEnterableDetail(arr4);
			}
		}
		for (int i = 0; i < sellerInfo.size(); i++) {
			PartyDetailsDTO partyDto = sellerInfo.get(i);
			String userTxnId = commonDao.getUserTxnIdSequenceId();
			String userFieldId = "SELLER";
			String txnId = partyDto.getPartyTxnId();
			String arr2[] = {userTxnId, userFieldId, regTxnId, userId, partyDto.getPartyName(), partyDto.getPartyAdress(), partyDto.getRelationType(), partyDto.getGuardianName(), star[i], txnId};
			insertStatus = commonDao.insertSellerInfor(arr2);
			if (insertStatus) {
				String arr3[] = {star[i], txnId};
				insertStatus = commonDao.updateAdhar(arr3);
			}
		}
		return insertStatus;
	}
	public boolean getFormOneInsertStatus(String regTxnId, String fromName) throws Exception {
		boolean resp = false;
		resp = commonDao.getFormOneInsertStatus(regTxnId, fromName);
		return resp;
	}
	public ArrayList getSellerDetail(String regTxnId, String lang) throws Exception {
		ArrayList party = new ArrayList();
		party = commonDao.getPartyTxnId(regTxnId);
		ArrayList<PartyDetailsDTO> partyDtoList = new ArrayList<PartyDetailsDTO>();
		for (int i = 0; i < party.size(); i++) {
			ArrayList tempList = (ArrayList) party.get(i);
			String partyTxnId = (String) tempList.get(0);
			ArrayList tempBuyer = commonDao.getSellerDetail(partyTxnId, lang);
			for (int k = 0; k < tempBuyer.size(); k++) {
				String parentId = "";
				PartyDetailsDTO partyDto = new PartyDetailsDTO();
				ArrayList tmp = (ArrayList) tempBuyer.get(k);
				String appTypeId = (String) tmp.get(0);
				String partyName = "";
				if ("1".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(2);
				} else if ("2".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(1);
				} else if ("3".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(3);
				}
				String partyAdress = "";
				if ("1".equalsIgnoreCase(appTypeId) || "2".equalsIgnoreCase(appTypeId)) {
					partyAdress = (String) tmp.get(4);
				} else if ("3".equalsIgnoreCase(appTypeId)) {
					partyAdress = (String) tmp.get(5);
				}
				String guardianName = "";
				String relationStatus = "";
				guardianName = (null == ((String) tmp.get(4)) ? "--" : (String) tmp.get(6));
				relationStatus = (null == ((String) tmp.get(4)) ? "--" : (String) tmp.get(7));
				String mobileNumber = (null == ((String) tmp.get(9)) ? "--" : (String) tmp.get(9));
				String emailId = (null == ((String) tmp.get(10)) ? "--" : (String) tmp.get(10));
				String photoProofId = (null == ((String) tmp.get(11)) ? "--" : (String) tmp.get(11));
				String photoProofType = (null == ((String) tmp.get(12)) ? "--" : (String) tmp.get(12));
				//String partyEmailId = (null == ((String) tmp.get(12)) ? "--" : (String) tmp.get(12));
				partyDto.setPartyName(partyName);
				partyDto.setGuardianName(guardianName);
				partyDto.setPartyAdress(partyAdress);
				partyDto.setRelationType(relationStatus);
				partyDto.setFourDigit("");
				partyDto.setPartyTxnId(partyTxnId);
				partyDto.setMobileNumber(mobileNumber);
				partyDto.setEmailId(emailId);
				partyDto.setPhotoProodId(photoProofId);
				partyDto.setPhotoProofTypeName(photoProofType);
				if (lang.equalsIgnoreCase("en")) {
					partyDto.setCategorName(null == ((String) tmp.get(14)) ? "--" : (String) tmp.get(14));
				} else {
					partyDto.setCategorName(null == ((String) tmp.get(15)) ? "--" : (String) tmp.get(15));
				}
				partyDto.setPartyAge(null == ((String) tmp.get(13)) ? "--" : (String) tmp.get(13));
				parentId = (String) tmp.get(8);
				if (null != parentId) {
					if (commonDao.checkIfSeller(parentId)) {
						partyDtoList.add(partyDto);
					}
				} else {
					partyDtoList.add(partyDto);
				}
			}

		}
		return partyDtoList;
	}
	public ArrayList getBuyerDetail(String regTxnId, String lang) throws Exception {
		ArrayList party = new ArrayList();
		party = commonDao.getPartyTxnId(regTxnId);
		ArrayList<PartyDetailsDTO> partyDtoList = new ArrayList<PartyDetailsDTO>();
		for (int i = 0; i < party.size(); i++) {
			ArrayList tempList = (ArrayList) party.get(i);
			String partyTxnId = (String) tempList.get(0);
			ArrayList tempBuyer = commonDao.getBuyerDetail(partyTxnId, lang);
			for (int k = 0; k < tempBuyer.size(); k++) {
				String parentId = "";
				PartyDetailsDTO partyDto = new PartyDetailsDTO();
				ArrayList tmp = (ArrayList) tempBuyer.get(k);
				String appTypeId = (String) tmp.get(0);
				String partyName = "";
				if ("1".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(2);
				} else if ("2".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(1);
				} else if ("3".equalsIgnoreCase(appTypeId)) {
					partyName = (String) tmp.get(3);
				}
				String partyAdress = "";
				if ("1".equalsIgnoreCase(appTypeId) || "2".equalsIgnoreCase(appTypeId)) {
					partyAdress = (String) tmp.get(4);
				} else if ("3".equalsIgnoreCase(appTypeId)) {
					partyAdress = (String) tmp.get(5);
				}
				String guardianName = "";
				String relationStatus = "";
				guardianName = (null == ((String) tmp.get(4)) ? "--" : (String) tmp.get(6));
				relationStatus = (null == ((String) tmp.get(4)) ? "--" : (String) tmp.get(7));
				String mobileNumber = (null == ((String) tmp.get(9)) ? "--" : (String) tmp.get(9));
				String emailId = (null == ((String) tmp.get(10)) ? "--" : (String) tmp.get(10));
				String photoProofId = (null == ((String) tmp.get(11)) ? "--" : (String) tmp.get(11));
				String photoProofType = (null == ((String) tmp.get(12)) ? "--" : (String) tmp.get(12));
				partyDto.setPartyName(partyName);
				partyDto.setGuardianName(guardianName);
				partyDto.setPartyAdress(partyAdress);
				partyDto.setRelationType(relationStatus);
				partyDto.setFourDigit("");
				partyDto.setPartyRemarks("");
				partyDto.setPartyTxnId(partyTxnId);
				partyDto.setMobileNumber(mobileNumber);
				partyDto.setEmailId(emailId);
				partyDto.setPhotoProodId(photoProofId);
				partyDto.setPhotoProofTypeName(photoProofType);
				partyDto.setCategorName(null == ((String) tmp.get(14)) ? "--" : (String) tmp.get(14));
				partyDto.setPartyAge(null == ((String) tmp.get(13)) ? "--" : (String) tmp.get(13));
				parentId = (String) tmp.get(8);
				if (null != parentId) {
					if (commonDao.checkIfBuyer(parentId)) {
						partyDtoList.add(partyDto);
					}
				} else {
					partyDtoList.add(partyDto);
				}
			}

		}
		return partyDtoList;
	}
	public boolean insertFormTwoData(String[] adhar, ArrayList<FormOneDTO> formData, String[] fieldValue, String regTxnId, String userId, String formName, ArrayList<PartyDetailsDTO> buyerInfo) throws Exception {
		boolean insertStatus = false;
		List<String> fl = Arrays.asList(fieldValue);

		for (int i = 0; i < formData.size(); i++) {
			FormOneDTO fDto = formData.get(i);
			ArrayList<DeclarationDTO> decDtoList = fDto.getDeclaration();
			for (int j = 0; j < decDtoList.size(); j++) {
				DeclarationDTO decDto = decDtoList.get(j);
				String declrationTxnid = decDto.getDeclarationId();
				if (!"1010".equalsIgnoreCase(declrationTxnid)) {
					if (fl.contains(declrationTxnid)) {
						// String formName="FORM_VIB";
						String formSequenceId = commonDao.getFormSequenceId();
						String arr[] = {formSequenceId, formName, declrationTxnid, regTxnId, userId, decDto.getUserEntrableFlag()};
						insertStatus = commonDao.insertFormDetail(arr);
						if ("Y".equalsIgnoreCase(decDto.getUserEntrableFlag())) {
							ArrayList<FormUserEnterableDTO> userEnterableDtoList = decDto.getUserEnterableData();
							for (int k = 0; k < userEnterableDtoList.size(); k++) {
								FormUserEnterableDTO userDto = userEnterableDtoList.get(k);
								String userTxnId = commonDao.getUserTxnIdSequenceId();
								String userFieldId = userDto.getUserEnterableId();
								String userFieldValue = userDto.getValueEntered();
								String arr1[] = {userTxnId, formSequenceId, userFieldId, regTxnId, userId, userFieldValue};
								insertStatus = commonDao.insertUserEnterableDetail(arr1);
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < buyerInfo.size(); i++) {
			PartyDetailsDTO partyDto = buyerInfo.get(i);
			String userTxnId = commonDao.getUserTxnIdSequenceId();
			String userFieldId = "BUYER";
			String txnId = partyDto.getPartyTxnId();
			String arr2[] = {userTxnId, userFieldId, regTxnId, userId, partyDto.getPartyName(), partyDto.getPartyAdress(), partyDto.getRelationType(), partyDto.getGuardianName(), adhar[i], txnId};
			insertStatus = commonDao.insertSellerInfor(arr2);
			if (insertStatus) {
				String arr3[] = {adhar[i], txnId};
				insertStatus = commonDao.updateAdhar(arr3);
			}
		}
		return insertStatus;
	}
	public ArrayList getPropDetail(String regTxnId, String language) throws Exception {
		ArrayList propData = new ArrayList();
		propData = commonDao.getPropDetail(regTxnId);
		ArrayList<PropDetailDto> propDetailList = new ArrayList<PropDetailDto>();
		for (int i = 0; i < propData.size(); i++) {
			PropDetailDto propDto = new PropDetailDto();
			ArrayList tmp = (ArrayList) propData.get(i);
			boolean bool = false;
			String partyTxnId = (String) tmp.get(4);
			if (null == ((String) tmp.get(8))) {
				String parentTxnId = (String) tmp.get(4);
				bool = commonDao.checkIfBuyer(parentTxnId);
			} else {
				String parentId = (String) tmp.get(8);
				bool = commonDao.checkIfBuyer(parentId);
			}
			if (bool) {
				ArrayList party = commonDao.getPartyData(partyTxnId);
				ArrayList partyTemp = (ArrayList) party.get(0);
				String appletId = (String) partyTemp.get(0);
				String partyName = "--";
				if ("1".equals(appletId)) {
					partyName = (String) partyTemp.get(2);
				} else if ("2".equals(appletId)) {
					partyName = (String) partyTemp.get(1);
				} else {
					partyName = (String) partyTemp.get(3);
				}
				propDto.setPartyName(partyName);
				if ("en".equalsIgnoreCase(language)) {
					propDto.setPatwariHalka(null == ((String) tmp.get(0)) ? "--" : (String) tmp.get(0));
					propDto.setMohallaName(null == ((String) tmp.get(2)) ? "--" : (String) tmp.get(2));
				} else {
					propDto.setPatwariHalka(null == ((String) tmp.get(1)) ? "--" : (String) tmp.get(1));
					propDto.setMohallaName(null == ((String) tmp.get(3)) ? "--" : (String) tmp.get(3));
				}
				propDto.setSurveyNumber(null == ((String) tmp.get(6)) ? "--" : (String) tmp.get(6));
				propDto.setTotalKhasraArea(null == ((String) tmp.get(10)) ? "--" : (String) tmp.get(10));
				propDto.setSaleableArea(null == ((String) tmp.get(11)) ? "--" : (String) tmp.get(11));
				propDto.setAreaAsPerShare(null == ((String) tmp.get(13)) ? "--" : (String) tmp.get(13));
				String singleCrop = "";
				String doubleCrop = "";
				String dryLand = "NA";
				propDto.setDryLand(dryLand);
				// propDetailList.add(propDto);
				String cropType = (null == ((String) tmp.get(11)) ? "--" : (String) tmp.get(11));
				if ("1".equalsIgnoreCase(cropType)) {
					singleCrop = "YES";
					doubleCrop = "NO";
				} else if ("1".equalsIgnoreCase(cropType)) {
					singleCrop = "NO";
					doubleCrop = "YES";
				} else {
					singleCrop = "NA";
					doubleCrop = "NA";
				}
				propDto.setSingleCrop(singleCrop);
				propDto.setDoubleCrop(doubleCrop);
				propDto.setDryLand(dryLand);
				propDetailList.add(propDto);
			}
		}
		return propDetailList;
	}
	public ArrayList getPartyDetails(String regTxnId, String language) throws Exception {
		ArrayList retList = new ArrayList();
		ArrayList tempList = commonDao.getPartyDetails(regTxnId);
		for (int i = 0; i < tempList.size(); i++) {
			ArrayList temp = (ArrayList) tempList.get(i);
			PartyDetailsDTO partyDto = new PartyDetailsDTO();
			partyDto.setPartyType((null==(String)temp.get(0))?"--":(String)temp.get(0));
			partyDto.setPartyName((null==(String)temp.get(1))?"--":(String)temp.get(1));
			partyDto.setPartyAdress((null==(String)temp.get(2))?"--":(String)temp.get(2));
			partyDto.setRelationType((null==(String)temp.get(3))?"--":(String)temp.get(3));
			partyDto.setGuardianName((null==(String)temp.get(4))?"--":(String)temp.get(4));
			partyDto.setFourDigit((null==(String)temp.get(5))?"--":(String)temp.get(5));
			partyDto.setPartyTxnId((null==(String)temp.get(6))?"--":(String)temp.get(6));
			partyDto.setEsignStatus((null==(String)temp.get(7))?"--":Integer.parseInt((String)temp.get(7))>0?"true":"false");
			retList.add(partyDto);
		}
		return retList;
	}
	
	
	//Added by ankit for aadhar Esign and form PDF
		public ArrayList<com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO> getFormVIDetails(String reg_txn_id, String formName) throws Exception {
			ArrayList masterDecList = commonDao.getDeclarationDetlList(reg_txn_id,formName);
			ArrayList masterUserEntList = commonDao.getUserEntDetlList(reg_txn_id);
			ArrayList<com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO> dtoList = null;
			ArrayList<com.wipro.igrs.cybertehsil.forms.dto.FormUserEnterableDTO> dtoList2 = null; 
			
			if (masterDecList != null && masterDecList.size() > 0) {
				dtoList = new ArrayList<com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO>();
				for (int i = 0; i < masterDecList.size(); i++) {
					com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO dto = new com.wipro.igrs.cybertehsil.forms.dto.DeclarationDTO();
					ArrayList l = (ArrayList) masterDecList.get(i);
					dto.setDeclarationId((String) l.get(0));
					dto.setDeclarationSubId((String) l.get(1));
					dto.setDeclarationNameEnglish((String) l.get(2));
					dto.setDeclarationNameHindi((String) l.get(3));
					dto.setUserEntrableFlag((String) l.get(4));
					dto.setUserEntrableFieldId((String) l.get(5));
					dto.setForm_txn_id((String) l.get(6));
					
					if("Y".equalsIgnoreCase(dto.getUserEntrableFlag())) {
						dtoList2 = new ArrayList<com.wipro.igrs.cybertehsil.forms.dto.FormUserEnterableDTO>();
						for(int j=0;j<masterUserEntList.size();j++) {	
							ArrayList m = (ArrayList) masterUserEntList.get(j);
								if(dto.getForm_txn_id().equals((String) m.get(0))) {
										
										System.out.println("Adding user enterable list for : "+dto.getForm_txn_id());
										
										com.wipro.igrs.cybertehsil.forms.dto.FormUserEnterableDTO dto2 = new com.wipro.igrs.cybertehsil.forms.dto.FormUserEnterableDTO();
										dto2.setUserEnterableTxnId((String) m.get(1));
										dto2.setUserEnterableId((String) m.get(2));
										dto2.setUserEnterableFieldNameEnglish((String) m.get(3));
										dto2.setUserEnterableFieldNameHindi((String) m.get(4));
										dto2.setValueEntered((String) m.get(5));
										dtoList2.add(dto2);
								}
									
							}
						
					}
					
					dto.setUserEnterableData(dtoList2);
					dtoList.add(dto);
				}
			} else {
						return null;
			}
			return dtoList;
		}
		
		//Added by ankit for aadhar Esign and form PDF
			public ArrayList<com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO> getFormVIPartyDetails(String reg_txn_id, String[] fieldId) throws Exception {
				ArrayList List = commonDao.getFormVIPartyDetails(reg_txn_id,fieldId);
				
				ArrayList<com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO> dtoList = null;
			
				
				if (List != null && List.size() > 0) {
					dtoList = new ArrayList<com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO>();
					for (int i = 0; i < List.size(); i++) {
						com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO dto = new com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO();
						ArrayList l = (ArrayList) List.get(i);
						 //1 Organisation
						if("1".equalsIgnoreCase((String) l.get(0))) {
							dto.setPartyName((String) l.get(2));
							dto.setPartyAdress((String) l.get(4));
						}
						//2 Indvidual
						if("2".equalsIgnoreCase((String) l.get(0))) {
							dto.setPartyName((String) l.get(1));
							dto.setPartyAdress((String) l.get(4));
						}
						//3 Govt
						if("3".equalsIgnoreCase((String) l.get(0))) {
							dto.setPartyName((String) l.get(3));
							dto.setPartyAdress((String) l.get(5));
						}
					
						dto.setGuardianName((String) l.get(6)==null?" NA ":(String) l.get(6));
						dto.setRelationType((String) l.get(7));
						dto.setRelationTypeHindi((String) l.get(8));
						dto.setMobileNo((String) l.get(9)==null?" NA ":(String) l.get(9));
						dto.setEmailId((String) l.get(10)==null?" NA ":(String) l.get(10));
						dto.setIdProof((String) l.get(11)==null?" NA ":(String) l.get(11));
						dto.setIdProofType((String) l.get(12));
						dto.setIdProofTypeHindi((String) l.get(13));
						dto.setFourDigit((String) l.get(14));
						dto.setPartyTxnId((String) l.get(15));
						dtoList.add(dto);
					}
				}
				else {
					return null;
				}
				return dtoList;
			}
			
			//added by ankit for aadhar esign 
			public com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO getPartyDetailsEsign(String partyTxnId) throws Exception {
				ArrayList retList = new ArrayList();
				ArrayList tempList = commonDao.getPartyDetailsEsign(partyTxnId);
				for(int i = 0;i<tempList.size();i++){
					ArrayList temp = (ArrayList) tempList.get(i);
					com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO partyDto = new com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO();
					partyDto.setPartyType((null==(String)temp.get(0))?"--":(String)temp.get(0));
					partyDto.setPartyName((null==(String)temp.get(1))?"--":(String)temp.get(1));
					partyDto.setPartyAdress((null==(String)temp.get(2))?"--":(String)temp.get(2));
					partyDto.setRelationType((null==(String)temp.get(3))?"--":(String)temp.get(3));
					partyDto.setGuardianName((null==(String)temp.get(4))?"--":(String)temp.get(4));
					partyDto.setFourDigit((null==(String)temp.get(5))?"--":(String)temp.get(5));
					partyDto.setPartyTxnId((null==(String)temp.get(6))?"--":(String)temp.get(6));
					partyDto.setRegTxnId((null==(String)temp.get(7))?"--":(String)temp.get(7));
					retList.add(partyDto);
				}
				return (com.wipro.igrs.cybertehsil.forms.dto.PartyDetailsDTO) retList.get(0);
			}
			
			//added by ankit for aadhar esign 
			public boolean insertEsignReqData(ArrayList<String> insertParams) {
				boolean insertsuccess = false;
					//transid, reg_txn_id,_party_id, createdby,created_date, updateby,updatedate,status, IP_Address, userid,docfilepath,error,errorDesc
				String[] param = new String[11];
				param[0] = insertParams.get(1); //transaction id cannot be null or empty
				param[1] = insertParams.get(2);//partyTxnId
				param[2] = insertParams.get(3);  //reg_txn_id
				param[3] = insertParams.get(4);  //userid
				//param[] = "sys date";  //create date
				param[4] = insertParams.get(5);  //ipAddress
				param[5] = insertParams.get(6);  //pdf file
				param[6] = insertParams.get(7);  //error code
				if(insertParams.get(8)!=null) {
					param[7] = insertParams.get(8).length()>200?insertParams.get(8).substring(0, 200):insertParams.get(8);  //error desc
				}
				else {
					param[7] = "";  //error desc
				}
				param[8] = "I";  //initiated request  STATUS	I=initiate, P=pass, F=fail			
				param[9] = "";  //update by
				param[10] = insertParams.get(9);  //hash
				//param[11] = "sys date";  //update date
				insertsuccess = commonDao.insertEsignTxnDetails(param);
				return insertsuccess;
			}
			
			
	//added by ankit for aadhar esign 
			/*public boolean updateEsignDetls(AspEsignXMLResponse esignXmlResp,String transId,String userId) {
				boolean insertsuccess = false;
					//transid, reg_txn_id,_party_id, createdby,created_date, updateby,updatedate,status, IP_Address, userid,docfilepath,error,errorDesc
				String[] param = new String[5];
				param[0] = esignXmlResp.getErrorCode(); //transaction id
				param[1] = esignXmlResp.getErrorMsg();//partyTxnId
				param[2] = esignXmlResp.isStatus()?"P":"F";  //reg_txn_id
				param[3] = userId;  //userid
				param[4] = transId;
				
				insertsuccess = commonDao.updateEsignDetls(param);
				return insertsuccess;
			}*/
			
			//added by ankit for aadhar esign anf pdf work
			public boolean generateFormVIAPDF(String regTxnId, String lang) throws Exception {
				boolean resp = false;
				String checkPayment = commonDao.getRcmsFeeStatus(regTxnId);
				String perCaseFee = commonDao.getRCMSFee();
				if(!checkPayment.equalsIgnoreCase(perCaseFee)){
					return false;
				}
				
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String dirPath = pr.getValue(RegInitConstant.FORMVIA_PATH)+"//"+regTxnId;
				File dir2 =  new File(dirPath);
				if (!dir2.exists()) {
					dir2.mkdir();
				}
				
				String formAfile = dirPath+"//"+pr.getValue(RegInitConstant.FORMVIA_FILENAME);
				
				File filePrest = new File(formAfile);
				//GenerateFormVIAENGHINDI formPDF = new GenerateFormVIAENGHINDI();
				//resp = formPDF.generateFormVIAPDF( regTxnId,lang,formAfile);
				if(filePrest.exists()) {
					resp = true;
					if(filePrest.length()==0)
					{
						GenerateFormVIAENGHINDI formPDF = new GenerateFormVIAENGHINDI();
						resp = formPDF.generateFormVIAPDF( regTxnId,lang,formAfile);
					}	
					
				}else {
							GenerateFormVIAENGHINDI formPDF = new GenerateFormVIAENGHINDI();
							resp = formPDF.generateFormVIAPDF( regTxnId,lang,formAfile);
				}
				
				if(resp){
					
				}
				//GenerateFormVIAPDF formPDF = new GenerateFormVIAPDF();
				
				return resp;
			}
			
			
			//added by ankit for aadhar esign anf pdf work
			public boolean generateFormVIBPDF(String regTxnId, String lang) throws Exception {
				boolean resp = false;

				String checkPayment = commonDao.getRcmsFeeStatus(regTxnId);
				String perCaseFee = commonDao.getRCMSFee();
				if(!checkPayment.equalsIgnoreCase(perCaseFee)){
					return false;
				}
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				String dirPath = pr.getValue(RegInitConstant.FORMVIB_PATH)+"//"+regTxnId;
				File dir2 =  new File(dirPath);
				if (!dir2.exists()) {
					dir2.mkdir();
				}
				String formBfile = dirPath+"//"+pr.getValue(RegInitConstant.FORMVIB_FILENAME);
				
				File filePrest = new File(formBfile);

				FormVIBPdfGeneration viBPdf = new FormVIBPdfGeneration();
				viBPdf.generationFormVIB(regTxnId, lang, formBfile);
			/*	if(filePrest.exists()) {
					resp = true;
					if(filePrest.length()==0)
					{
						FormVIBPdfGeneration viBPdf = new FormVIBPdfGeneration();
						viBPdf.generationFormVIB(regTxnId, lang, formBfile);
					}	
					
				}else {
							FormVIBPdfGeneration viBPdf = new FormVIBPdfGeneration();
							viBPdf.generationFormVIB(regTxnId, lang, formBfile);
				}*/
				
				//GenerateFormVIAPDF formPDF = new GenerateFormVIAPDF();
				
				return resp;
			}
			
			//added by ankit for aadhar esign and pdf work
			public String getDocHash(String transId) throws Exception {
				return commonDao.getDocHash(transId);
				
			}
			
			/*public void closeDoc(String transactionId) throws Exception {
				
				ConcurrentHashMap<String, ReadWritePDF> mapIdto= LoadMenuExternal.getInstance().getAllEsignPDFDto();
				ReadWritePDF pdfSign= LoadMenuExternal.getInstance().getReadWritePDFDto(transactionId!=null?transactionId:"0");
				PdfDictionary dic2 = new PdfDictionary();
				if(pdfSign!=null)
				pdfSign.appearance.close(dic2);
				
			 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			 * String hashFile =
			 * pr.getValue(RegInitConstant.HASHFILE_TEMP_PATH)+transactionId+".ser";
			 * ReadWriteObjectFile readFile = new ReadWriteObjectFile();
			 * PdfSignatureAppearance appearance = readFile.readFromFile(hashFile);
			 * 
			 * appearance.close(dic2); 
				
			}*/
			
			//addded by ankit for esign work
			public String checkforManualEsign(String regTxnId) throws Exception {
				int failCount = commonDao.checkforManualEsign(regTxnId);
				if(failCount>3) {
					return "true";
				}else {
					return "false";
				}
			}
			
			



	public ArrayList<String> getSelectedDeclarationFormVIB(String regTxnId, String lang) throws Exception {
		ArrayList<String> retList = new ArrayList<String>();
		ArrayList list = commonDao.getSelectedDeclarationFormVIB(regTxnId);
		for(int i = 0;i<list.size();i++){
			ArrayList tempList = (ArrayList) list.get(i);
			retList.add(("en".equalsIgnoreCase(lang))?(String)tempList.get(1):(String)tempList.get(1)); //change for hindi
		}
		return retList;
	}
	
	public ArrayList getPaymentDetails(String regTxnId )throws Exception {
		ArrayList retList = new ArrayList();
		ArrayList tmpList = commonDao.getPaymentDetail(regTxnId);
		for (int i = 0; i < tmpList.size(); i++) {
			ArrayList tmp = (ArrayList) tmpList.get(i);
			ArrayList list = new ArrayList();
			list.add(tmp.get(0));
			list.add(DateToWords.convertAmountToWords((String) tmp.get(1)));
			list.add((String) tmp.get(1));
			retList.add(list);
		}
		return retList;
	}

	
	public boolean updateFormPath(String regTxnId, String formType, String path) throws Exception {
		return commonDao.updateFormPath(regTxnId, formType, path);
	}

	public ArrayList getPropDistIds(String regTxnId) throws Exception {
		return commonDao.getPropDistIds(regTxnId);
	}
	
	public ArrayList getPropDistrict(String regTxnId,String language) throws Exception{
		ArrayList retList = new ArrayList();
		ArrayList tempList = commonDao.getPropDistrict(regTxnId,language);
		retList = (ArrayList) tempList.get(0);
		return retList;
	}

	public String getUserDetail(String userId) throws Exception {
		return commonDao.getUserDetail(userId);
	}

	public boolean updateconsent(String con1, String con2, String regTxnId, String formType, String userId) throws Exception {
		return commonDao.updateconsent(con1, con2,regTxnId,formType,userId);
	}
	public ArrayList getDeclaration(String regTxnId, String formType) throws Exception{
		ArrayList retList = new ArrayList();
		ArrayList tempList = commonDao.getDeclaration(regTxnId,formType);
		retList = (ArrayList) tempList.get(0);
		return retList;
	}
	public String getUserDetailWithRegid(String regTxnId) throws Exception {
		return commonDao.getUserDetailWithRegid(regTxnId);
	}
}

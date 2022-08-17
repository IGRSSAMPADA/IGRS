package com.wipro.igrs.reginit.bd;

//import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

//import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/*import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
 import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
 import com.wipro.igrs.UserRegistration.constant.CommonConstant;
 import com.wipro.igrs.UserRegistration.dao.UserRegistrationDAO;
 import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;*/
import com.wipro.igrs.common.CustomArrayList;
import com.wipro.igrs.common.IGRSCommon; //import com.wipro.igrs.db.DBUtility;
//import com.wipro.igrs.common.customArrayList;
import com.wipro.igrs.reginit.bo.RegCommonBO;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.dao.RegCommonDAO;
import com.wipro.igrs.reginit.dto.CommonDTO; //import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.form.RegCompletionForm;

/*import com.wipro.igrs.reginit.sql.RegCommonSQL;
 import com.wipro.igrs.util.CommonUtil;*/

public class RegCommonBD {

	RegCommonDAO commonDao = null;
	String eStamp = null;
	String eCode = null;
	String regInit = null;
	static int reg_txn_id_count = 0;

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(RegCommonBD.class);

	public RegCommonBD() {
		commonDao = new RegCommonDAO();
		eStamp = eStampIDgenerator();
		eCode = eCodeIDgenerator();
		regInit = regInitIDgenerator();
	}

	/**
	 * for getting all Applicant Types
	 * 
	 * @return ArrayList
	 */
	public ArrayList getAppType(String languageLocale) {
		return commonDao.getAppType(languageLocale);
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
	public ArrayList getState(String param,String languageLocale) {
		return commonDao.getState(param,languageLocale);
	}

	/**
	 * for getting all District details
	 * 
	 * @param stateId
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId,String languageLocale) {
		return commonDao.getDistrict(stateId,languageLocale);
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
	public ArrayList getOthersDuty(String function_id, String service_id,
			String user_id) {
		return commonDao.getOthersDuty(function_id, service_id, user_id);
	}

	public PropertyValuationDTO getPropertyValuationDTO(
			PropertyValuationDTO useDTO) throws Exception {

		String[] district = useDTO.getDistrictID() == null ? null : useDTO
				.getDistrictID().split("~");
		if (district != null && district.length == 2) {
			useDTO.setDistrict(district[1]);
			useDTO.setDistrictID(district[0]);
		}
		String[] tehsil = useDTO.getTehsilID() == null ? null : useDTO
				.getTehsilID().split("~");
		if (tehsil != null && tehsil.length == 2) {
			useDTO.setTehsil(tehsil[1]);
			useDTO.setTehsilID(tehsil[0]);
		}
		String[] ward = useDTO.getWardId() == null ? null : useDTO.getWardId()
				.split("~");
		if (ward != null && ward.length == 2) {
			useDTO.setWard(ward[1]);
			useDTO.setWardId(ward[0]);
		}

		String[] mohalla = useDTO.getMahallaId() == null ? null : useDTO
				.getMahallaId().split("~");
		if (mohalla != null && mohalla.length == 2) {
			useDTO.setMahalla(mohalla[1]);
			useDTO.setMahallaId(mohalla[0]);
		}
		String[] property = useDTO.getPropertyTypeID() == null ? null : useDTO
				.getPropertyTypeID().split("~");
		if (property != null && property.length == 2) {
			useDTO.setPropertyType(property[1]);
			useDTO.setPropertyTypeID(property[0]);
		}

		String[] muncipal = useDTO.getMunicipalBodyID() == null ? null : useDTO
				.getMunicipalBodyID().split("~");
		if (muncipal != null && muncipal.length == 2) {
			useDTO.setMunicipalBody(muncipal[1]);
			useDTO.setMunicipalBodyID(muncipal[0]);
		}
		String[] area = useDTO.getAreaId() == null ? null : useDTO.getAreaId()
				.split("~");
		if (area != null && area.length == 2) {
			useDTO.setAreaType(area[1]);
			useDTO.setAreaId(area[0]);
		}
		String[] usePlot = useDTO.getUsePlotId() == null ? null : useDTO
				.getUsePlotId().split("~");

		if (usePlot != null && usePlot.length == 3) {
			useDTO.setUsePlot(usePlot[1]);
			useDTO.setUsePlotId(usePlot[0]);
			logger.debug(usePlot[1]);
		}

		IGRSCommon common = new IGRSCommon();
		// if (useDTO.getTotalSqMeter() != null) {
		useDTO.setTotalSqMeter(Double.parseDouble(common
				.getCurrencyFormat(useDTO.getTotalSqMeter())));
		// }
		// if(useDTO.getConsiderAmt() != null) {
		useDTO.setConsiderAmt(Double.parseDouble(common
				.getCurrencyFormat(useDTO.getConsiderAmt())));
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
	public boolean propertyValReqCheck(String deed) {
		return commonDao.propertyValReqCheck(deed);
	}

	/**
	 * for getting party type list based on deed
	 * 
	 * @param deed
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyType(int deed, int inst,String languageLocale) {
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
	 * 
	 */
	public boolean insertApplicantAndPropertyDetails(RegCommonForm form)
			throws Exception // main
	{
		boolean insertsuccess = false;

		// FOLLOWING CODE FOR OWNER DETAILS
		String ownerDetails[] = new String[18];
		int partyId = Integer.parseInt(form.getPartyType());
		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer
				.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());
		if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {

			ownerDetails[0] = form.getHidnRegTxnId(); // reg txn id
			if (form.getOwnerOgrName().equals("")
					|| form.getOwnerOgrName().equals(null)) {
				ownerDetails[1] = "2"; // individual party type
				ownerDetails[2] = form.getOwnerName(); // first name
				ownerDetails[12] = ""; // authorized person name
			} else {
				ownerDetails[1] = "1"; // organization party type
				ownerDetails[2] = ""; // first name
				ownerDetails[12] = form.getOwnerName(); // authorized person
														// name
			}

			ownerDetails[3] = form.getOwnerGendar(); // gender
			// ownerDetails[4]=UserRegistrationBD.getOracleDate(form.getOwnerDay(),
			// form.getOwnerMonth(), form.getOwnerYear()); //age to DOB
			ownerDetails[4] = form.getOwnerAge(); // DOB to age
			ownerDetails[5] = form.getOwnerNationality(); // nationality

			// String address=form.getOwnerAddress();
			// address=address.replace(",", " ");
			ownerDetails[6] = form.getOwnerAddress().replace(",",
					RegInitConstant.SPLIT_CONSTANT); // address

			ownerDetails[7] = form.getOwnerPhno(); // phone number
			if (form.getOwnerEmailID() != null
					&& !form.getOwnerEmailID().equalsIgnoreCase("")
					&& !form.getOwnerEmailID().equalsIgnoreCase("null")) {
				ownerDetails[8] = form.getOwnerEmailID().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // email id
			} else {
				ownerDetails[8] = "";
			}
			ownerDetails[9] = form.getOwnerListID(); // photo proof type id

			// String proof=form.getOwnerIdno();
			// proof=proof.replace(",","");
			ownerDetails[10] = form.getOwnerIdno().replace(",",
					RegInitConstant.SPLIT_CONSTANT); // photo proof number
			if (form.getOwnerOgrName() != null
					&& !form.getOwnerOgrName().equalsIgnoreCase("")
					&& !form.getOwnerOgrName().equalsIgnoreCase("null")) {
				ownerDetails[11] = form.getOwnerOgrName().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // organization name
			} else {
				ownerDetails[11] = "";
			}
			ownerDetails[13] = getTransactingPartyIdSeq(); // party transaction
															// id
			ownerDetails[14] = "O"; // is applicant O-Owner
			ownerDetails[15] = form.getShareOfProp(); // share of property
			ownerDetails[16] = Integer
					.toString(RegInitConstant.ROLE_OWNER_SELF); // party role
																// type id
			ownerDetails[17] = form.getHidnUserId(); // CREATED BY
		}

		// FOLLOWING CODE FOR APPLICANT DETAILS
		String[] param = new String[56];

		if (form.getPartyType().equalsIgnoreCase(
				Integer.toString(RegInitConstant.ROLE_POA_ACCEPTER))
				&& form.getHdnDeclareShareCheck().equalsIgnoreCase("Y")) {
			param[28] = "0";
		} else {
			param[28] = form.getShareOfProp();
		}
		param[49] = "";
		param[51] = ""; // for universal deeds executant  claimant flag

		if (form.getListAdoptedParty().equalsIgnoreCase("1")) {

			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			//param[5] = "";
			param[6] = ""; // AGE
			param[7] = "";
			param[8] = form.getCountry();
			param[9] = form.getStatename();
			param[10] = form.getDistrict();
			// String address=form.getOrgaddress();
			// address=address.replace(",", " ");
			param[11] = form.getOrgaddress().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			param[12] = "";
			param[13] = form.getPhno();
			param[14] = form.getMobno();
			param[15] = "";
			param[16] = "";
			param[17] = "";
			param[18] = form.getOgrName().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			param[19] = form.getAuthPerName();
			//param[20] = "";
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

			if (ownerDetails[13] == null) {
				param[32] = "";
			} else {
				param[32] = ownerDetails[13];
			} // self referencing key of owner in case of poa holder

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

			param[50] = Integer.toString(form.getAuthPerRelationship()); // relationship type
			
			param[5] = form.getAuthPerGendar();
			param[20] = form.getAuthPerFatherName();
			
			param[52] = form.getAuthPerCountry();
			param[53] = form.getAuthPerStatename();
			param[54] = form.getAuthPerDistrict();
			String authAddress = form.getAuthPerAddress();
			authAddress = authAddress.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[55] = authAddress;

		} else {

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
			param[11] = form.getIndaddress().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			param[12] = form.getPostalCode();
			param[13] = form.getIndphno();
			param[14] = form.getIndmobno();
			if (form.getEmailID() != null
					&& !form.getEmailID().equalsIgnoreCase("")
					&& !form.getEmailID().equalsIgnoreCase("null")) {
				param[15] = form.getEmailID().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			} else {
				param[15] = "";
			}
			param[16] = form.getListID();
			// String proof=form.getIdno();
			// proof=proof.replace(",","");
			param[17] = form.getIdno().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
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

			if (ownerDetails[13] == null) {
				param[32] = "";
			} else {
				param[32] = ownerDetails[13]; // self referencing key of owner
												// in case of poa holder
			}
			if (form.getIndDisabilityDesc() != null
					&& !form.getIndDisabilityDesc().equalsIgnoreCase("")
					&& !form.getIndDisabilityDesc().equalsIgnoreCase("null")) {
				param[33] = form.getIndDisabilityDesc().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // DISABILITY
															// DESCRIPTION
			} else {
				param[33] = "";
			}
			param[34] = form.getIndMinority(); // MINORITY
			param[35] = form.getIndPovertyLine(); // POVERTY LINE

			if (form.getIndCategory().equalsIgnoreCase("1")) {
				param[36] = form.getIndScheduleArea(); // ST RADIO
				if (form.getIndScheduleArea().equalsIgnoreCase("N")) {
					param[37] = form.getPermissionNo().replace(",",
							RegInitConstant.SPLIT_CONSTANT); // ST CERTIFICATE
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

		if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

			param[41] = form.getU4FilePath(); // EXEC_PHOTO_PATH
			param[40] = form.getU3FilePath(); // NOT_AFFD_EXEC_PATH
			param[45] = form.getU10FilePath(); // PAN_FORM_60_PATH

		} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

			RegCommonBO bo = new RegCommonBO();
			param[41] = form.getU7FilePath(); // EXEC_PHOTO_PATH
			param[42] = form.getU5FilePath(); // NOT_AFFD_ATTR_PATH
			param[43] = form.getU6FilePath(); // ATTR_PHOTO_PATH
			param[46] = form.getSrOfficeName(); // SR OFFICE NAME
			param[47] = form.getPoaRegNo(); // POA REG NO
			param[48] = bo.convertCalenderDate(form.getDatePoaReg()); // POA REG
																		// DATE

		} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

			param[44] = form.getU8FilePath(); // CLAIMNT_PHOTO_PATH
			param[45] = form.getU11FilePath(); // PAN_FORM_60_PATH

		} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

			param[43] = form.getU9FilePath(); // ATTR_PHOTO_PATH

		}

		if (form.getDeedID() == RegInitConstant.DEED_EXCHANGE
				|| form.getDeedID() == RegInitConstant.DEED_PARTITION_PV) { // for
																			// exchange
																			// deed

			// FOLLOWING CODE FOR TRANSACTION DETAILS
			String deed[] = new String[8];
			deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID
			deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
			deed[2] = Integer.toString(form.getDeedID()); // DEED_ID
			deed[3] = Integer.toString(form.getInstID()); // INSTRUMENTS_ID
			deed[4] = form.getHidnUserId(); // CREATED_BY
			deed[5] = form.getValuationId(); // REF_VAL_TXN_ID
			deed[6] = ""; // DUTY TXN ID
			if (form.getFromAdjudication() == 1) {
				deed[7] = "A";
			} else {
				deed[7] = "";
			}

			ArrayList list = form.getExchangePropertyList();

			ArrayList paramList1 = new ArrayList();
			ArrayList paramList2 = new ArrayList();

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
				String[] valPropDetls = bo
						.getPropertyDetailsFromValuation(row_list
								.getValuationId().trim());

				// FOLLOWING CODE FOR PROPERTY DETAILS

				paramDto1.setPropertyId(row_list.getPropertyId().trim());
				paramDto1.setRegTxnId(form.getHidnRegTxnId());

				paramDto1.setMarketValue(valPropDetls[0].trim());
				paramDto1.setAreaTypeId(valPropDetls[1].trim());
				paramDto1.setGovBodyId(valPropDetls[2].trim());
				paramDto1.setPropTypeId(valPropDetls[3].trim());
				paramDto1.setL1TypeId(valPropDetls[4].trim());
				if (valPropDetls[5].trim().equalsIgnoreCase("null")
						|| valPropDetls[5].trim().equalsIgnoreCase("")) {
					paramDto1.setL2TypeId(null);
				} else {
					paramDto1.setL2TypeId(valPropDetls[5].trim());
				}
				if (valPropDetls[6].trim().equalsIgnoreCase("null")
						|| valPropDetls[6].trim().equalsIgnoreCase("")) {
					paramDto1.setAreaUnitId(null);
				} else {
					paramDto1.setAreaUnitId(valPropDetls[6].trim());
				}

				paramDto1.setArea(valPropDetls[7].trim());
				paramDto1.setDistId(valPropDetls[8].trim());
				paramDto1.setTehsilId(valPropDetls[9].trim());
				paramDto1.setWardId(valPropDetls[10].trim());
				paramDto1.setMohalaId(valPropDetls[11].trim());

				paramDto1.setUserId(form.getHidnUserId());
				paramDto1.setValuationId(row_list.getValuationId().trim());
				paramDto1.setSysMv(valPropDetls[12].trim());

				paramList1.add(paramDto1);

				// FOLLOWING CODE FOR PROPERTY & TRANSACTING PARTY MAPPING

				if (partyId == RegInitConstant.ROLE1_OWNER_POA_HOLDER
						|| partyId == RegInitConstant.ROLE1_OWNER_SELF) {
					if (row_list.getPartyType().trim().equalsIgnoreCase(
							"Party1")) {
						paramDto2.setRegTxnId(form.getHidnRegTxnId());
						paramDto2
								.setPropertyId(row_list.getPropertyId().trim());
						paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
						paramDto2.setUserId(form.getHidnUserId());
						paramList2.add(paramDto2);
					}
				} else if (partyId == RegInitConstant.ROLE2_OWNER_POA_HOLDER
						|| partyId == RegInitConstant.ROLE2_OWNER_SELF) {
					if (row_list.getPartyType().trim().equalsIgnoreCase(
							"Party2")) {
						paramDto2.setRegTxnId(form.getHidnRegTxnId());
						paramDto2
								.setPropertyId(row_list.getPropertyId().trim());
						paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
						paramDto2.setUserId(form.getHidnUserId());
						paramList2.add(paramDto2);
					}
				} else if (partyId == RegInitConstant.ROLE_OWNER_POA_HOLDER
						|| partyId == RegInitConstant.ROLE_OWNER_SELF) {

					paramDto2.setRegTxnId(form.getHidnRegTxnId());
					paramDto2.setPropertyId(row_list.getPropertyId().trim());
					paramDto2.setPartyTxnId(form.getPartyRoleTypeId());
					paramDto2.setUserId(form.getHidnUserId());
					paramList2.add(paramDto2);

				}
			}
			insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsExchange(
					param, deed, paramList1, paramList2, ownerDetails, form
							.getExmpID(), null);

		} else { // for other than exchange deed

			// FOLLOWING CODE FOR TRANSACTION DETAILS
			String deed[] = new String[8];
			deed[0] = form.getHidnRegTxnId();
			if (form.getDeedID() == RegInitConstant.DEED_TRUST_PV) {

				if (form.getHdnDeclareShareCheck().equalsIgnoreCase("Y")) {
					int share = Integer.parseInt(form.getShareOfProp());
					if (share == 100) {
						deed[1] = RegInitConstant.STATUS_TRNS_SAVED;
					} else {
						deed[1] = RegInitConstant.STATUS_APP_SAVED;
					}
				} else {

					if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) {
						deed[1] = RegInitConstant.STATUS_APP_SAVED;
					} else {
						deed[1] = RegInitConstant.STATUS_TRNS_SAVED;
					}

				}

			} else {
				deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
			}
			deed[2] = Integer.toString(form.getDeedID()); // DEED_ID
			deed[3] = Integer.toString(form.getInstID()); // INSTRUMENTS_ID
			deed[4] = form.getHidnUserId(); // CREATED_BY
			deed[5] = ""; // REF_VAL_TXN_ID
			deed[6] = "";
			if (form.getFromAdjudication() == 1) {
				deed[7] = "A";
			} else {
				deed[7] = "";
			}

			// FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION TABLES
			RegCommonBO bo = new RegCommonBO();
			String[] valPropDetls = bo.getPropertyDetailsFromValuation(form
					.getValuationId());

			// FOLLOWING CODE FOR PROPERTY DETAILS
			String propDetls[] = new String[19];
			propDetls[0] = form.getPropertyId();
			propDetls[1] = form.getHidnRegTxnId();
			propDetls[2] = valPropDetls[0].trim(); // MARKET_VALUE
			propDetls[3] = valPropDetls[1].trim(); // AREA_TYPE_ID
			propDetls[4] = valPropDetls[2].trim(); // GOV_BODY_ID
			propDetls[5] = valPropDetls[3].trim(); // PROP_TYPE_ID
			propDetls[6] = valPropDetls[4].trim(); // L1_TYPE_ID
			if (valPropDetls[5].trim().equalsIgnoreCase("null")) {
				propDetls[7] = null;
			} else {
				propDetls[7] = valPropDetls[5].trim(); // L2_TYPE_ID
			}
			if (valPropDetls[6].trim().equalsIgnoreCase("null")) {
				propDetls[8] = null;
			} else {
				propDetls[8] = valPropDetls[6].trim(); // AREA_UNIT_ID
			}
			propDetls[9] = valPropDetls[7].trim(); // AREA
			propDetls[10] = valPropDetls[8].trim(); // DISTRICT_ID
			propDetls[11] = valPropDetls[9].trim(); // TEHSIL_ID
			propDetls[12] = valPropDetls[10].trim(); // WARD_ID
			propDetls[13] = valPropDetls[11].trim(); // MOHALLA_ID
			propDetls[14] = form.getHidnUserId();
			propDetls[15] = form.getValuationId();
			propDetls[16] = "N"; // PROP_TXN_COMPL_FLAG
			propDetls[17] = valPropDetls[0].trim(); // INITIAL_MARKET_VALUE
			propDetls[18] = valPropDetls[12].trim(); // System market value

			// FOLLOWING CODE FOR PROPERTY AND TRANSACTING PARTY MAPPING DETAILS
			String propTransDets[] = new String[4];
			propTransDets[0] = form.getHidnRegTxnId();
			propTransDets[1] = form.getPropertyId();
			propTransDets[2] = form.getPartyRoleTypeId();
			propTransDets[3] = form.getHidnUserId();

			String extraDetls[] = setExtraDetlsParam(form);
			/*
			 * extraDetls[0]=form.getHidnRegTxnId(); extraDetls[1]="";
			 * extraDetls[2]=""; extraDetls[3]=""; extraDetls[4]="";
			 * extraDetls[5]=""; extraDetls[6]="";
			 * extraDetls[7]=form.getHidnUserId(); extraDetls[8]="";
			 * extraDetls[9]=""; extraDetls[10]=""; extraDetls[11]="";
			 * extraDetls[12]=""; extraDetls[13]=""; extraDetls[14]="";
			 * extraDetls[15]=""; extraDetls[16]=""; extraDetls[17]="";
			 * extraDetls[18]=""; extraDetls[19]=""; extraDetls[20]="";
			 * extraDetls[21]=""; extraDetls[22]=""; extraDetls[23]="";
			 * extraDetls[24]=""; extraDetls[25]=""; extraDetls[26]="";
			 * extraDetls[27]=""; extraDetls[28]=""; extraDetls[29]="";
			 * extraDetls[30]=""; extraDetls[31]=""; extraDetls[32]="";
			 * extraDetls[33]=""; extraDetls[34]=""; extraDetls[35]="";
			 * extraDetls[36]=""; extraDetls[37]=""; extraDetls[38]="";
			 * extraDetls[39]=""; extraDetls[40]="";
			 * 
			 * 
			 * 
			 * if(form.getDeedID()==RegInitConstant.DEED_LEASE_PV) {
			 * extraDetls[10]=Double.toString(form.getRent());
			 * extraDetls[11]=Double.toString(form.getAdvance());
			 * extraDetls[12]=Double.toString(form.getDevlpmtCharge());
			 * if(!Double
			 * .toString(form.getOtherRecCharge()).equalsIgnoreCase("")){
			 * extraDetls[13]=Double.toString(form.getOtherRecCharge()); }else{
			 * extraDetls[13]=""; }
			 * extraDetls[14]=Double.toString(form.getPremium());
			 * extraDetls[15]=Double.toString(form.getTermLease());
			 * 
			 * }else if(form.getDeedID()==RegInitConstant.DEED_TRUST_PV) {
			 * 
			 * extraDetls[8]=form.getTrustName();
			 * extraDetls[9]=bo.convertCalenderDate(form.getTrustDate());
			 * 
			 * } else if(form.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV) {
			 * extraDetls[16]=form.getWithPos();
			 * extraDetls[17]=Double.toString(form.getSecLoanAmt());
			 * 
			 * } else if(form.getDeedID()==RegInitConstant.DEED_AWARD_PV)
			 * //award { extraDetls[18]=form.getFnameArb();
			 * extraDetls[19]=form.getMnameArb();
			 * extraDetls[20]=form.getLnameArb();
			 * extraDetls[21]=form.getGendarArb();
			 * extraDetls[22]=form.getAgeArb();
			 * extraDetls[23]=form.getFatherNameArb();
			 * extraDetls[24]=form.getMotherNameArb();
			 * extraDetls[25]=form.getSpouseNameArb();
			 * extraDetls[26]=form.getNationalityArb();
			 * extraDetls[27]=form.getIndaddressArb().replace(",",
			 * RegInitConstant.SPLIT_CONSTANT);
			 * extraDetls[28]=form.getIndcountryArb();
			 * extraDetls[29]=form.getIndstatenameArb();
			 * extraDetls[30]=form.getInddistrictArb();
			 * extraDetls[31]=form.getIndphnoArb();
			 * extraDetls[32]=form.getIndmobnoArb();
			 * if(form.getEmailIDArb()!=null &&
			 * !form.getEmailIDArb().equalsIgnoreCase("")){
			 * extraDetls[33]=form.getEmailIDArb().replace(",",
			 * RegInitConstant.SPLIT_CONSTANT); }else{ extraDetls[33]=""; }
			 * extraDetls[34]=form.getIndCategoryArb();
			 * extraDetls[35]=form.getIndDisabilityArb();
			 * if(form.getIndDisabilityArb().equalsIgnoreCase("Y")){
			 * 
			 * if(form.getIndDisabilityDescArb()!=null &&
			 * !form.getIndDisabilityDescArb().equalsIgnoreCase("")){
			 * extraDetls[36]=form.getIndDisabilityDescArb().replace(",",
			 * RegInitConstant.SPLIT_CONSTANT); }else{ extraDetls[36]=""; }
			 * }else{ extraDetls[36]=""; } extraDetls[37]=form.getListIDArb();
			 * extraDetls[38]=form.getIdnoArb().replace(",",
			 * RegInitConstant.SPLIT_CONSTANT);
			 * 
			 * 
			 * } else
			 * if(form.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_PV){
			 * extraDetls[11]=Double.toString(form.getAdvance());
			 * extraDetls[39]=
			 * bo.convertCalenderDate(form.getAdvancePaymntDate());
			 * extraDetls[40]=form.getPossGiven(); } else{ extraDetls=null; }
			 */

			insertsuccess = commonDao.insrtApplcntTransPartyPropDetls(param,
					deed, propDetls, propTransDets, ownerDetails, form
							.getExmpID(), null, extraDetls);
		}

		return insertsuccess;
	}

	public String[] setExtraDetlsParam(RegCommonForm form) {

		RegCommonBO bo = new RegCommonBO();
		String extraDetls[] = new String[47];
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

			if (form.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE) {

				extraDetls[1] = form.getBankName();
				extraDetls[2] = form.getBranchName();
				String bankAddress = form.getBankAddress();
				extraDetls[3] = bankAddress.replace(",",
						RegInitConstant.SPLIT_CONSTANT);
				extraDetls[4] = form.getBankAuthPer();
				extraDetls[5] = Integer.toString(form.getBankLoanAmt());
				extraDetls[6] = Integer.toString(form.getBankSancAmt());
			} else if (form.getDeedID() == RegInitConstant.DEED_TRUST) {
				extraDetls[8] = form.getTrustName();
				if (form.getTrustDate() != null
						&& !form.getTrustDate().equalsIgnoreCase("")) {
					extraDetls[9] = bo.convertCalenderDate(form.getTrustDate());
				} else {
					extraDetls[9] = form.getTrustDate();
				}

			} else if (form.getDeedID() == RegInitConstant.DEED_LEASE_PV
					|| form.getDeedID() == RegInitConstant.DEED_LEASE_NPV) {
				extraDetls[10] = Double.toString(form.getRent());
				extraDetls[11] = Double.toString(form.getAdvance());
				extraDetls[12] = Double.toString(form.getDevlpmtCharge());
				if (!Double.toString(form.getOtherRecCharge())
						.equalsIgnoreCase("")) {
					extraDetls[13] = Double.toString(form.getOtherRecCharge());
				} else {
					extraDetls[13] = "";
				}
				extraDetls[14] = Double.toString(form.getPremium());
				extraDetls[15] = Double.toString(form.getTermLease());

			} else if (form.getDeedID() == RegInitConstant.DEED_TRUST_PV) {

				extraDetls[8] = form.getTrustName();
				extraDetls[9] = bo.convertCalenderDate(form.getTrustDate());

			} else if (form.getDeedID() == RegInitConstant.DEED_MORTGAGE_PV
					|| form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV) {
				extraDetls[16] = form.getWithPos();
				extraDetls[17] = Double.toString(form.getSecLoanAmt());

			} else if (form.getDeedID() == RegInitConstant.DEED_AWARD_PV
					|| form.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) // award
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
				extraDetls[27] = form.getIndaddressArb().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
				extraDetls[28] = form.getIndcountryArb();
				extraDetls[29] = form.getIndstatenameArb();
				extraDetls[30] = form.getInddistrictArb();
				extraDetls[31] = form.getIndphnoArb();
				extraDetls[32] = form.getIndmobnoArb();
				if (form.getEmailIDArb() != null
						&& !form.getEmailIDArb().equalsIgnoreCase("")) {
					extraDetls[33] = form.getEmailIDArb().replace(",",
							RegInitConstant.SPLIT_CONSTANT);
				} else {
					extraDetls[33] = "";
				}
				extraDetls[34] = form.getIndCategoryArb();
				extraDetls[35] = form.getIndDisabilityArb();
				if (form.getIndDisabilityArb().equalsIgnoreCase("Y")) {

					if (form.getIndDisabilityDescArb() != null
							&& !form.getIndDisabilityDescArb()
									.equalsIgnoreCase("")) {
						extraDetls[36] = form.getIndDisabilityDescArb()
								.replace(",", RegInitConstant.SPLIT_CONSTANT);
					} else {
						extraDetls[36] = "";
					}
				} else {
					extraDetls[36] = "";
				}
				extraDetls[37] = form.getListIDArb();
				extraDetls[38] = form.getIdnoArb().replace(",",
						RegInitConstant.SPLIT_CONSTANT);

			} else if (form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV
					|| form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV) {
				extraDetls[11] = Double.toString(form.getAdvance());
				extraDetls[39] = bo.convertCalenderDate(form
						.getAdvancePaymntDate());
				extraDetls[40] = form.getPossGiven();
			} else if (form.getDeedID() == RegInitConstant.DEED_POA) {
				extraDetls[41] = form.getPoaWithConsid();
				extraDetls[42] = Double.toString(form.getPoaPeriod());

			} else if (form.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV) {
				extraDetls[43] = Double.toString(form.getPaidLoanAmt());

			} else if ((form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
						 (form.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || form.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))) {
				if (form.getContriProp() != null
						&& !form.getContriProp().equalsIgnoreCase("")) {
					extraDetls[44] = form.getContriProp();
				} else {
					extraDetls[44] = "";
				}

			} else if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_PV || 
					(form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
					&& form.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)) {
				if (form.getDissolutionDate() != null
						&& !form.getDissolutionDate().equalsIgnoreCase("")) {
					extraDetls[45] = bo.convertCalenderDate(form
							.getDissolutionDate());
				} else {
					extraDetls[45] = form.getDissolutionDate();
				}
				if (form.getRetirmentDate() != null
						&& !form.getRetirmentDate().equalsIgnoreCase("")) {
					extraDetls[46] = bo.convertCalenderDate(form
							.getRetirmentDate());
				} else {
					extraDetls[46] = form.getRetirmentDate();
				}

			} else {
				extraDetls = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	 * 
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
	 * 
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
	 * 
	 */
	public boolean insertTransPartyDetails(RegCommonDTO dto,
			RegCommonForm regForm, ArrayList list, int multiPropFlag)
			throws Exception {
		boolean insertsuccess = false;

		// FOLLOWING CODE FOR OWNER DETAILS
		String ownerDetails[] = new String[18];
		int partyId = 0;
		if (dto.getPartyTypeTrns() != null) {
			partyId = Integer.parseInt(dto.getPartyTypeTrns());
		}
		// int
		// claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(partyId)));
		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer
				.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());

		if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || 
				(regForm.getCommonDeed()==1 && 
				(dto.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_2 ||
						dto.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_4))) {

			ownerDetails[0] = regForm.getHidnRegTxnId(); // reg txn id
			if (dto.getOwnerOgrNameTrns().equalsIgnoreCase("-")) {
				ownerDetails[1] = RegInitConstant.INDIVIDUAL_ID; // individual
																	// party
																	// type
				ownerDetails[2] = dto.getOwnerNameTrns(); // first name
				ownerDetails[12] = ""; // authorized person name
			} else {
				ownerDetails[1] = RegInitConstant.ORGANISATION_ID; // organization
																	// party
																	// type
				ownerDetails[2] = ""; // first name
				ownerDetails[12] = dto.getOwnerNameTrns(); // authorized person
															// name
			}

			if (dto.getOwnerGendarTrns().equalsIgnoreCase("female"))
				ownerDetails[3] = "F"; // gender
			else
				ownerDetails[3] = "M";
			// ownerDetails[4]=dto.getOwnerDOBTrns(); //DOB
			ownerDetails[4] = dto.getOwnerAgeTrns(); // age
			ownerDetails[5] = dto.getOwnerNationalityTrns(); // nationality

			/*
			 * String address=dto.getOwnerAddressTrns();
			 * address=address.replace(",", " ");
			 */

			ownerDetails[6] = dto.getOwnerAddressTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT); // address
			ownerDetails[7] = dto.getOwnerPhnoTrns(); // phone number
			if (dto.getOwnerEmailIDTrns().equalsIgnoreCase("-"))
				ownerDetails[8] = ""; // email id
			else
				ownerDetails[8] = dto.getOwnerEmailIDTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // email id
			ownerDetails[9] = dto.getOwnerListIDTrns(); // photo proof type id

			/*
			 * String proof=dto.getOwnerIdnoTrns(); proof=proof.replace(",",
			 * "");
			 */

			ownerDetails[10] = dto.getOwnerIdnoTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT); // photo proof number
			if (dto.getOwnerOgrNameTrns().equalsIgnoreCase("-"))
				ownerDetails[11] = ""; // organization name
			else
				ownerDetails[11] = dto.getOwnerOgrNameTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // organization name
			ownerDetails[13] = getTransactingPartyIdSeq(); // party transaction
															// id
			ownerDetails[14] = "O"; // is applicant O-Owner
			if (dto.getShareOfPropTrns() == null)
				ownerDetails[15] = "";
			else if (dto.getShareOfPropTrns().equalsIgnoreCase("-"))
				ownerDetails[15] = "";
			else
				ownerDetails[15] = dto.getShareOfPropTrns();
			// ownerDetails[15]=dto.getShareOfPropTrns(); //share of property
			ownerDetails[16] = Integer
					.toString(RegInitConstant.ROLE_OWNER_SELF); // party role
																// type id
			ownerDetails[17] = dto.getUserID();
		}

		String[] param = new String[56];
		param[0] = regForm.getHidnRegTxnId(); // Registration Txn Id.

		// relation with owner
		if (dto.getRelationWithOwnerTrns() == null)
			param[27] = "";
		else if (dto.getRelationWithOwnerTrns().equalsIgnoreCase("-"))
			param[27] = "";
		else
			param[27] = dto.getRelationWithOwnerTrns();
		// share of prop
		if (dto.getShareOfPropTrns() == null)
			param[28] = "";
		else if (dto.getShareOfPropTrns().equalsIgnoreCase("-"))
			param[28] = "";
		else
			param[28] = dto.getShareOfPropTrns();

		if (regForm.getCommonDeed() != 1) {
			
			param[29] = dto.getPartyTypeTrns();
			param[49] = "";
			param[51] = "";
		} else {
			param[29] = "0";
			param[49] = dto.getRoleName(); // role name entered by user
			param[51]=Integer.toString(dto.getExecutantClaimantTrns()); // for universal deeds executant claimant from front end
		}

		
		//param[51]=""; // for universal deeds executant claimant from front end
		
		if (dto.getListAdoptedPartyTrns().equalsIgnoreCase(
				RegInitConstant.ORGANISATION_ID)) { // Organization

			param[1] = dto.getListAdoptedPartyTrns();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			//param[5] = "";
			param[6] = "";
			param[7] = "";
			param[8] = dto.getSelectedCountry();
			param[9] = dto.getSelectedState();
			param[10] = dto.getSelectedDistrict();

			/*
			 * String address=dto.getOrgaddressTrns();
			 * address=address.replace(",", " ");
			 */

			param[11] = dto.getOrgaddressTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
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
			param[18] = dto.getOgrNameTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			param[19] = dto.getAuthPerNameTrns();
			//param[20] = "";
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
			if (ownerDetails[13] == null) {
				param[32] = "";
			} else {
				param[32] = ownerDetails[13];
			} // self referencing key of owner in case of poa holder

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
			//param[50] = "";
			
			
			param[50] = Integer.toString(dto.getRelationshipTrns()); // relationship type
			
			param[5] = dto.getAuthPerGendarTrns();
			param[20] = dto.getAuthPerFatherNameTrns();
			
			param[52] = dto.getAuthPerCountryTrns();
			param[53] = dto.getAuthPerStatenameTrns();
			param[54] = dto.getAuthPerDistrictTrns();
			String authAddress = dto.getAuthPerAddressTrns();
			authAddress = authAddress.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[55] = authAddress;

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

			param[11] = dto.getOrgaddressTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
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
				param[15] = dto.getEmailIDTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			}
			param[16] = dto.getSelectedPhotoId();
			String proof = dto.getIdnoTrns();
			proof = proof.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[17] = proof;
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

			if (ownerDetails[13] == null) {
				param[32] = "";
			} else {
				param[32] = ownerDetails[13];
			} // self referencing key of owner in case of poa holder

			// if(dto.getIndDisabilityDescTrns().equals(null)){
			// param[33] = "";
			// }
			// else
			if (dto.getIndDisabilityDescTrns() != null) {
				if (dto.getIndDisabilityDescTrns().equalsIgnoreCase("-")
						|| dto.getIndDisabilityDescTrns().equalsIgnoreCase("")
						|| dto.getIndDisabilityDescTrns().equalsIgnoreCase(
								"null")) {
					param[33] = "";
				} else {
					param[33] = dto.getIndDisabilityDescTrns().replace(",",
							RegInitConstant.SPLIT_CONSTANT);
				}
			} else {
				param[33] = "";
			}

			if (dto.getIndMinorityTrns() == null
					|| dto.getIndMinorityTrns().equalsIgnoreCase("-")) {
				param[34] = "";
			} else if (dto.getIndMinorityTrns().equalsIgnoreCase("Yes")) {
				param[34] = "Y";
			} else if (dto.getIndMinorityTrns().equalsIgnoreCase("No")) {
				param[34] = "N";
			}

			if (dto.getIndPovertyLineTrns() == null
					|| dto.getIndPovertyLineTrns().equalsIgnoreCase("-")) {
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
						param[37] = dto.getPermissionNoTrns().replace(",",
								RegInitConstant.SPLIT_CONSTANT); // ST
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
		regForm.getDeedTypeFlag() == 1 || regForm.getCommonDeed() == 1) { // 1
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
			param[42] = dto.getU5FilePathTrns(); // NOT_AFFD_ATTR_PATH
			param[43] = ""; // ATTR_PHOTO_PATH
			param[44] = dto.getU8FilePathTrns(); // CLAIMNT_PHOTO_PATH
			param[45] = ""; // PAN_FORM_60_PATH
			param[46] = "";
			param[47] = "";
			param[48] = "";

			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

				param[41] = dto.getU4FilePathTrns(); // EXEC_PHOTO_PATH
				param[45] = dto.getU10FilePathTrns(); // PAN_FORM_60_PATH

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

				param[41] = dto.getU7FilePathTrns(); // EXEC_PHOTO_PATH
				param[43] = dto.getU6FilePathTrns(); // ATTR_PHOTO_PATH
				param[46] = dto.getSrOfficeNameTrns();
				param[47] = dto.getPoaRegNoTrns();
				param[48] = dto.getDatePoaRegTrns();

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

				param[45] = dto.getU11FilePathTrns(); // PAN_FORM_60_PATH

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

				param[43] = dto.getU9FilePathTrns(); // ATTR_PHOTO_PATH
			}
		}

		// FOLLOWING FOR REG TXN STATUS
		String[] statusParam = new String[2];

		if (regForm.getCommonDeed() == 1) { // for common deeds

			statusParam[0] = RegInitConstant.STATUS_TRNS_SAVED;
			statusParam[1] = regForm.getHidnRegTxnId();

		} else {
			if (regForm.getDeedTypeFlag() == 0) { // for deeds other than
													// trust/adoption
				if (multiPropFlag == 0) {
					statusParam[0] = RegInitConstant.STATUS_TRNS_SAVED;
					statusParam[1] = regForm.getHidnRegTxnId();
				}
				if (multiPropFlag == 1) {
					statusParam[0] = RegInitConstant.STATUS_MULTI_TRNS_SAVED;
					statusParam[1] = regForm.getHidnRegTxnId();
				}
			} else {
				statusParam[0] = RegInitConstant.STATUS_PROP_SAVED;
				statusParam[1] = regForm.getHidnRegTxnId();
			}
		}

		if (regForm.getDeedID() == RegInitConstant.DEED_EXCHANGE
				|| regForm.getDeedID() == RegInitConstant.DEED_PARTITION_PV) { // for
																				// exchange
																				// deed

			ArrayList paramList1 = new ArrayList();

			CommonDTO row_list;
			CommonDTO paramDto1;

			for (int i = 0; i < list.size(); i++) {
				row_list = (CommonDTO) list.get(i);

				paramDto1 = new CommonDTO();

				if (partyId == RegInitConstant.ROLE1_OWNER_POA_HOLDER || // FOR
																			// EXCHANGE
																			// DEED
																			// PARTIES
						partyId == RegInitConstant.ROLE1_OWNER_SELF) {
					if (row_list.getPartyType().trim().equalsIgnoreCase(
							"Party1")) {
						paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
						paramDto1
								.setPropertyId(row_list.getPropertyId().trim());
						paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
						paramDto1.setUserId(dto.getUserID());
						paramList1.add(paramDto1);
					}
				} else if (partyId == RegInitConstant.ROLE2_OWNER_POA_HOLDER || // FOR
																				// EXCHANGE
																				// DEED
																				// PARTIES
						partyId == RegInitConstant.ROLE2_OWNER_SELF) {
					if (row_list.getPartyType().trim().equalsIgnoreCase(
							"Party2")) {
						paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
						paramDto1
								.setPropertyId(row_list.getPropertyId().trim());
						paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
						paramDto1.setUserId(dto.getUserID());
						paramList1.add(paramDto1);
					}
				} else if (partyId == RegInitConstant.ROLE_OWNER_POA_HOLDER || // FOR
																				// PARTITION
																				// DEED
																				// PARTIES
						partyId == RegInitConstant.ROLE_OWNER_SELF) {
					paramDto1.setRegTxnId(regForm.getHidnRegTxnId());
					paramDto1.setPropertyId(row_list.getPropertyId().trim());
					paramDto1.setPartyTxnId(dto.getPartyRoleTypeId());
					paramDto1.setUserId(dto.getUserID());
					paramList1.add(paramDto1);

				}
			}
			insertsuccess = commonDao.insrtApplcntTransPartyPropDetlsExchange(
					param, new String[0], null, paramList1, ownerDetails, "",
					statusParam);

		} else if (regForm.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE
				|| regForm.getDeedID() == RegInitConstant.DEED_TRUST
				|| regForm.getDeedID() == RegInitConstant.DEED_ADOPTION
				|| regForm.getDeedID() == RegInitConstant.DEED_CANCELLATION_OF_WILL_POA
				|| regForm.getDeedID() == RegInitConstant.DEED_LEASE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_POA
				|| regForm.getDeedID() == RegInitConstant.DEED_SETTLEMENT_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_WILL_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_TRANSFER_LEASE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_COMPOSITION_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_SECURITY_BOND_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_TRANSFER_NPV
				|| regForm.getDeedID() == RegInitConstant.DEED_FURTHER_CHARGE_NPV) { // for
																						// title
																						// deed
			insertsuccess = commonDao.insrtTransPartyDetlsTitleDeed(param,
					ownerDetails, statusParam);
		} else if (regForm.getCommonDeed() == 1) { // for common deeds
			insertsuccess = commonDao.insrtTransPartyDetlsCommonDeeds(param,
					ownerDetails, statusParam);
		} else { // for other than exchange deed and title deed

			String propDets[] = new String[4];
			propDets[0] = regForm.getHidnRegTxnId();
			propDets[1] = dto.getPropertyId();
			propDets[2] = dto.getPartyRoleTypeId();
			propDets[3] = dto.getUserID();

			insertsuccess = commonDao.insrtApplcntTransPartyPropDetls(param,
					new String[0], new String[0], propDets, ownerDetails, "",
					statusParam, null);
		}

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
	 * 
	 */
	public boolean insertTxnDetails(String[] txnDetls) throws Exception {
		boolean insertsuccess = false;
		insertsuccess = commonDao.insertTxnDetails(txnDetls);

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
	 * 
	 */
	public boolean insertMappingDetails(String txnId, String poaId,
			String[] ownerId, String userId) throws Exception // main
	{
		boolean insertsuccess = false;

		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss aa");
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
	 * 
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
	 * 
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
	public String[] getApplicantRegistrationDetls(String userId)
			throws Exception {
		ArrayList appRegDetls = new ArrayList();

		appRegDetls = commonDao.getApplicantRegistrationDetls(userId);
		String regDtls = appRegDetls.toString();

		regDtls = regDtls.substring(2, (regDtls.length() - 2));

		String regDetls1[] = regDtls.split(",");
		logger.debug("--------------" + regDetls1);

		return regDetls1;
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
		String savedAppDetsStr = savedAppDets.toString().substring(2,
				(savedAppDets.toString().length() - 2));
		String[] appDets = savedAppDetsStr.split(",");
		if (appDets[0].trim().length() == 11) {
			form.setHidnRegTxnId("0" + appDets[0].trim());
		} else {
			form.setHidnRegTxnId(appDets[0].trim());
		}
		form.setListAdoptedParty(appDets[1].trim());
		form.setFname(appDets[2].trim());
		form.setMname(appDets[3].trim());
		form.setLname(appDets[4].trim());
		form.setGendar(appDets[5].trim());
		form.setAge(appDets[6].trim()); // AGE
		form.setNationality(appDets[7].trim());
		if (appDets[1].trim().equals("1")) {
			form.setCountry(appDets[8].trim());
			form.setStatename(appDets[9].trim());
			form.setDistrict(appDets[10].trim());
			form.setOrgaddress(appDets[11].trim().replace(
					RegInitConstant.SPLIT_CONSTANT, ","));

		} else if (appDets[1].trim().equals("2")) {
			form.setIndcountry(appDets[8].trim());
			form.setIndstatename(appDets[9].trim());
			form.setInddistrict(appDets[10].trim());
			form.setIndaddress(appDets[11].trim().replace(
					RegInitConstant.SPLIT_CONSTANT, ","));
		}

		form.setPostalCode(appDets[12].trim());
		form.setPhno(appDets[13].trim());
		form.setMobno(appDets[14].trim());
		form.setEmailID(appDets[15].trim());
		form.setListID(appDets[16].trim());
		form.setIdno(appDets[17].trim().replace(RegInitConstant.SPLIT_CONSTANT,
				","));

		if (appDets[18].trim() != null
				&& !appDets[18].trim().equalsIgnoreCase("")
				&& !appDets[18].trim().equalsIgnoreCase("null")) {
			form.setOgrName(appDets[18].trim().replace(
					RegInitConstant.SPLIT_CONSTANT, ","));
		} else {
			form.setOgrName(appDets[18].trim());
		}
		form.setAuthPerName(appDets[19].trim());
		form.setFatherName(appDets[20].trim());
		form.setMotherName(appDets[21].trim());
		form.setIndCategory(appDets[22].trim());
		form.setPartyRoleTypeId(appDets[23].trim());
		form.setIndDisability(appDets[24].trim());
		form.setRelationWithOwner(appDets[25].trim());
		form.setShareOfProp(appDets[26].trim());
		form.setPartyType(appDets[27].trim());
		form.setSpouseName(appDets[28].trim());
		form.setPoaOwnerId(appDets[29].trim());
		if (appDets[30].trim() != null
				&& !appDets[30].trim().equalsIgnoreCase("")
				&& !appDets[30].trim().equalsIgnoreCase("null")) {
			form.setIndDisabilityDesc(appDets[30].trim().replace(
					RegInitConstant.SPLIT_CONSTANT, ","));
		} else {
			form.setIndDisabilityDesc(appDets[30].trim());
		}
		form.setOccupationId(appDets[31].trim());
		if (appDets[32].trim() != null
				&& !appDets[32].trim().equalsIgnoreCase("")
				&& !appDets[32].trim().equalsIgnoreCase("null")) {
			form.setPermissionNo(appDets[32].trim().replace(
					RegInitConstant.SPLIT_CONSTANT, ","));
		} else {
			form.setPermissionNo(appDets[32].trim());
		}
		// collector certificate path 33
		form.setFilePath(appDets[33].trim());
		form.setIndMinority(appDets[34].trim());
		form.setIndPovertyLine(appDets[35].trim());
		form.setIndScheduleArea(appDets[36].trim());
		form.setU2FilePath(appDets[37].trim()); // 37 PHOTO_PROOF_PATH
		form.setU3FilePath(appDets[38].trim()); // 38 NOT_AFFD_EXEC_PATH
		form.setU4FilePath(appDets[39].trim()); // 39 EXEC_PHOTO_PATH
		form.setU7FilePath(appDets[39].trim()); // 39 EXEC_PHOTO_PATH
		form.setU5FilePath(appDets[40].trim()); // 40 NOT_AFFD_ATTR_PATH
		form.setU6FilePath(appDets[41].trim()); // 41 ATTR_PHOTO_PATH
		form.setU9FilePath(appDets[41].trim()); // 41 ATTR_PHOTO_PATH
		form.setU8FilePath(appDets[42].trim()); // 42 CLAIMNT_PHOTO_PATH
		form.setU10FilePath(appDets[43].trim()); // 43 PAN_FORM_60_PATH
		form.setU11FilePath(appDets[43].trim()); // 43 PAN_FORM_60_PATH

		form.setSrOfficeName(appDets[44].trim());
		form.setPoaRegNo(appDets[45].trim());
		form.setDatePoaReg(appDets[46].trim());
		form.setCommonDeedRoleName(appDets[47].trim());
		if (appDets[48].trim() != null
				&& !appDets[48].trim().equalsIgnoreCase("")
				&& !appDets[48].trim().equalsIgnoreCase("null")) {
			form.setRelationship(Integer.parseInt(appDets[48].trim()));
		} else {
			form.setRelationship(0);
		}
		
		if(appDets[49].trim()!=null && !appDets[49].trim().equalsIgnoreCase("") && !appDets[49].trim().equalsIgnoreCase("null")){
			form.setExecutantClaimant(Integer.parseInt(appDets[49].trim()));
		}else{
			form.setExecutantClaimant(0);
		}
		
		
		form.setAuthPerCountry(appDets[50].trim());
		form.setAuthPerStatename(appDets[51].trim());
		form.setAuthPerDistrict(appDets[52].trim());
		form.setAuthPerAddress(appDets[53].trim());

	}

	private void setTxnPartyDetsInForm(RegCommonForm form, ArrayList finalList) {
		ArrayList savedAppDets = new ArrayList();
		savedAppDets = (ArrayList) finalList.get(1);
		String savedAppDetsStr = savedAppDets.toString().substring(3,
				(savedAppDets.toString().length() - 3));
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
		form.setShareOfProp(appDets[40]);
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
	 * 
	 */

	public boolean insertPropertyDetails(RegCommonForm regInitForm,
			RegCompletionForm regPropDetsForm) throws Exception {
		boolean insertsuccess = false;
		// RegCommonDAO dao = new RegCommonDAO();

		String[] param = new String[20];
		if (regPropDetsForm.getRegcompletDto().getVikasId() != null) {
			param[0] = regPropDetsForm.getRegcompletDto().getVikasId().replace(
					",", RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[0] = "";
		}
		param[1] = regPropDetsForm.getRegcompletDto().getRicircle().replace(
				",", RegInitConstant.SPLIT_CONSTANT);
		if (regPropDetsForm.getRegcompletDto().getLayoutdet() != null) {
			param[2] = regPropDetsForm.getRegcompletDto().getLayoutdet()
					.replace(",", RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[2] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getSheetnum() != null) {
			param[3] = regPropDetsForm.getRegcompletDto().getSheetnum()
					.replace(",", RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[3] = "";
		}
		if (regPropDetsForm.getRegcompletDto().getPlotnum() != null) {
			param[4] = regPropDetsForm.getRegcompletDto().getPlotnum().replace(
					",", RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[4] = "";
		}
		param[5] = regPropDetsForm.getRegcompletDto().getPropAddress().replace(
				",", RegInitConstant.SPLIT_CONSTANT);
		param[6] = regPropDetsForm.getRegcompletDto().getEast().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		param[7] = regPropDetsForm.getRegcompletDto().getWest().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		param[8] = regPropDetsForm.getRegcompletDto().getNorth().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		param[9] = regPropDetsForm.getRegcompletDto().getSouth().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		param[10] = regInitForm.getHidnUserId();
		if (regPropDetsForm.getRegcompletDto().getPropLandmark() != null) {
			param[11] = regPropDetsForm.getRegcompletDto().getPropLandmark()
					.replace(",", RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[11] = "";
		}
		param[12] = regPropDetsForm.getRegcompletDto().getPropImage1FilePath();
		param[13] = regPropDetsForm.getRegcompletDto().getPropImage2FilePath();
		param[14] = regPropDetsForm.getRegcompletDto().getPropImage3FilePath();
		param[15] = regPropDetsForm.getRegcompletDto().getPropMapFilePath();
		param[16] = regPropDetsForm.getRegcompletDto().getPropRinFilePath();
		param[17] = regPropDetsForm.getRegcompletDto().getPropKhasraFilePath();
		param[18] = regInitForm.getHidnRegTxnId();
		param[19] = regInitForm.getPropertyId();

		String[] khasra = new String[4];

		if (regPropDetsForm.getKhasraNoArray() != null
				&& regPropDetsForm.getKhasraAreaArray() != null
				&& regPropDetsForm.getLagaanArray() != null
				&& regPropDetsForm.getRinPustikaArray() != null
				&& !regPropDetsForm.getKhasraNoArray().equalsIgnoreCase("null")
				&& !regPropDetsForm.getKhasraAreaArray().equalsIgnoreCase(
						"null")
				&& !regPropDetsForm.getLagaanArray().equalsIgnoreCase("null")
				&& !regPropDetsForm.getRinPustikaArray().equalsIgnoreCase(
						"null")
				&& !regPropDetsForm.getKhasraNoArray().equalsIgnoreCase("")
				&& !regPropDetsForm.getKhasraAreaArray().equalsIgnoreCase("")
				&& !regPropDetsForm.getLagaanArray().equalsIgnoreCase("")
				&& !regPropDetsForm.getRinPustikaArray().equalsIgnoreCase("")) {
			khasra[0] = regPropDetsForm.getKhasraNoArray();
			khasra[1] = regPropDetsForm.getRinPustikaArray();
			khasra[2] = regPropDetsForm.getKhasraAreaArray();
			khasra[3] = regPropDetsForm.getLagaanArray();
		} else {
			khasra = null;
		}

		String regStatus[] = new String[2];

		if (regPropDetsForm.getActionName().equalsIgnoreCase(
				RegInitConstant.NEXT_TO_CONFIRM_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_PROP_SAVED;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		}
		if (regPropDetsForm.getActionName().equalsIgnoreCase(
				RegInitConstant.ADD_MORE_PROP_ACTION)
				|| regPropDetsForm.getActionName().equalsIgnoreCase(
						RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION)) {
			regStatus[0] = RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS;
			regStatus[1] = regInitForm.getHidnRegTxnId();
		}

		insertsuccess = commonDao.updatePropertyDetails(param, khasra,
				regPropDetsForm.getActionName(), regStatus);

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
	public boolean deleteSelectedAppDetails(String appId) throws Exception // main
	{
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.deleteSelectedAppDetails(appId);
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
	public String getCountryName(String countryId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getCountryName(countryId,languageLocale);
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
	public String getStateName(String stateId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getStateName(stateId,languageLocale);
	}

	/**
	 * for getting district name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistrictName(String distId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getDistrictName(distId,languageLocale);
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
	public CustomArrayList getPropDetlsForDashboard(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropDetlsForDashboard(appId);
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
	public ArrayList getTransPartyDets(String propId, String appId)
			throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getTransPartyDets(propId, appId);
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
	public ArrayList getPaymentFlag(String appId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPaymentFlag(appId);
	}

	/**
	 * for inserting registration initiation payment txn details in db.
	 * 
	 * @param String
	 *            []
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPaymentDetails(RegCommonForm regForm) {
		boolean insertsuccess = false;
		// RegCommonDAO dao = new RegCommonDAO();

		String[] param = new String[7];
		param[0] = regForm.getPaymentTxnSeqId();
		param[1] = regForm.getHidnRegTxnId();
		param[2] = regForm.getHidnUserId();
		param[3] = RegInitConstant.PAYMENT_FLAG_INITIATED;
		param[4] = regForm.getTotalBalanceAmount();
		param[5] = "I";
		param[6] = "1";

		insertsuccess = commonDao.insertPaymentDetails(param);

		return insertsuccess;
	}

	/**
	 * getPartyDetsForViewConfirm for getting APPLICANT PARTY details from db.
	 * 
	 * @param String
	 *            , String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyDetsForViewConfirm(String appId, String partyId)
			throws Exception {
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
	public String getPhotoIdProofName(String proofId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPhotoIdProofName(proofId,languageLocale);
	}

	/**
	 * for getting valuation id corresponding to registration app id and
	 * property Id from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getPropValuationId(String appId, String propId)
			throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropValuationId(appId, propId);
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
	public ArrayList getOtherPropDetsForViewConfirm(String appId, String propId)
			throws Exception {
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
	 * 
	 */
	public boolean insrtMultiplePropDetls(RegCommonForm form) throws Exception // main
	{
		boolean insertsuccess = false;

		// FOLLOWING CODE FOR GETTING PROPERTY DETAILS FROM VALUATION TABLES
		RegCommonBO bo = new RegCommonBO();
		String[] valPropDetls = bo.getPropertyDetailsFromValuation(form
				.getValuationId());

		// FOLLOWING CODE FOR PROPERTY DETAILS
		String propDetls[] = new String[19];
		propDetls[0] = form.getPropertyId();
		propDetls[1] = form.getHidnRegTxnId();
		propDetls[2] = valPropDetls[0].trim(); // MARKET_VALUE
		propDetls[3] = valPropDetls[1].trim(); // AREA_TYPE_ID
		propDetls[4] = valPropDetls[2].trim(); // GOV_BODY_ID
		propDetls[5] = valPropDetls[3].trim(); // PROP_TYPE_ID
		propDetls[6] = valPropDetls[4].trim(); // L1_TYPE_ID
		if (valPropDetls[5].trim().equalsIgnoreCase("null")) {
			propDetls[7] = null;
		} else {
			propDetls[7] = valPropDetls[5].trim(); // L2_TYPE_ID
		}
		if (valPropDetls[6].trim().equalsIgnoreCase("null")) {
			propDetls[8] = null;
		} else {
			propDetls[8] = valPropDetls[6].trim(); // AREA_UNIT_ID
		}
		propDetls[9] = valPropDetls[7].trim(); // AREA
		propDetls[10] = valPropDetls[8].trim(); // DISTRICT_ID
		propDetls[11] = valPropDetls[9].trim(); // TEHSIL_ID
		propDetls[12] = valPropDetls[10].trim(); // WARD_ID
		propDetls[13] = valPropDetls[11].trim(); // MOHALLA_ID
		propDetls[14] = form.getHidnUserId();
		propDetls[15] = form.getValuationId();
		propDetls[16] = "N"; // PROP_TXN_COMPL_FLAG
		propDetls[17] = valPropDetls[0].trim(); // INITIAL_MARKET_VALUE
		propDetls[18] = valPropDetls[12].trim(); // SYSTEM_MARKET_VALUE

		String mapTransPartyPropDetls[][] = new String[form
				.getMapTransactingParties().size()][4];

		if (form.getDeedID() != RegInitConstant.DEED_TRUST_PV) {
			Collection mapCollection = form.getMapTransactingParties().values();
			Object[] l1 = mapCollection.toArray();
			RegCommonDTO mapDtoDisp = new RegCommonDTO();
			for (int i = 0; i < l1.length; i++) {
				mapDtoDisp = (RegCommonDTO) l1[i];
				mapTransPartyPropDetls[i][0] = form.getHidnRegTxnId();
				mapTransPartyPropDetls[i][1] = form.getPropertyId();
				mapTransPartyPropDetls[i][2] = mapDtoDisp.getPartyRoleTypeId();
				mapTransPartyPropDetls[i][3] = form.getHidnUserId();

			}
		} else {
			mapTransPartyPropDetls = null;
		}

		String[] regStatus = { RegInitConstant.STATUS_MULTI_PROP_PV_RECVD,
				form.getHidnRegTxnId() };

		insertsuccess = commonDao.insrtMultiplePropDetls(propDetls,
				mapTransPartyPropDetls, regStatus);

		return insertsuccess;
	}

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
	public String getRoleName(String roleId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getRoleName(roleId,languageLocale);
	}

	/**
	 * getTransactingPartyIdSeq Returns string value for sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 * 
	 */
	public String getTransactingPartyIdSeq() throws Exception {
		return commonDao.getTransactingPartyIdSeq();
	}

	/**
	 * getTransactingPartyIdSeq Returns string value for sequence.
	 * 
	 * @param
	 * @author ROOPAM
	 * @return string
	 * @throws Exception
	 * 
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
	public ArrayList getPropDetlsForDutyCalc(String valuationId)
			throws Exception {
		return commonDao.getPropDetlsForDutyCalc(valuationId);
	}

	/**
	 * getPaymentTxnId for getting payment transaction id from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPaymentTxnId(String appId) throws Exception {
		return commonDao.getPaymentTxnId(appId);
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
	public boolean updateTransactionStatus(RegCommonForm form) {

		String[] param = new String[6];

		if (form.getHdnPostalAddress1().equalsIgnoreCase("Y")) {
			param[0] = RegInitConstant.STATUS_COMPLETE;
			param[1] = form.getPostalCountry();
			param[2] = form.getPostalState();
			param[3] = form.getPostalDistrict();
			param[4] = form.getPostalAddress();
			param[5] = form.getHidnRegTxnId();
		} else {

			param[0] = RegInitConstant.STATUS_COMPLETE;
			param[1] = "";
			param[2] = "";
			param[3] = "";
			param[4] = "";
			param[5] = form.getHidnRegTxnId();

		}

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

		String[] param = { form.getHidnRegTxnId(), form.getStampduty1(),
				form.getPanchayatDuty(), form.getNagarPalikaDuty(),
				form.getUpkarDuty(), form.getRegistrationFee(),
				form.getTotalduty(), userID, form.getMarketValueShares(),
				form.getDutyPaid(), form.getRegPaid(),
				form.getConsiAmountTrns() };

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
	public String getDeedName(String deedId,String languageLocale) throws Exception {
		return commonDao.getDeedName(deedId,languageLocale);
	}

	/**
	 * for getting instrument name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instId,String languageLocale) throws Exception {
		return commonDao.getInstrumentName(instId,languageLocale);
	}

	/**
	 * for getting exemption name from db.
	 * 
	 * @param
	 * @return String
	 * @author ROOPAM
	 */
	public String getExemptionName(String exmpId) throws Exception {
		return commonDao.getExemptionName(exmpId);
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
	 * 
	 */
	public boolean updateAdjudicationRecords(String regInitId, String adjuId,
			String userId) {
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
	 * 
	 */
	public boolean updateEStampPurpose(String regInitId, String purpose) {
		return commonDao.updateEStampPurpose(regInitId, purpose.replace(",",
				RegInitConstant.SPLIT_CONSTANT));
	}

	/**
	 * getAllValuationIdsExchangeDeed for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIdsExchangeDeed(String finalValId)
			throws Exception {
		return commonDao.getAllValuationIdsExchangeDeed(finalValId);
	}

	/**
	 * getAllPropDetlsExchangeDeed for getting ALL VALUATION IDS from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeed(String valId) throws Exception {
		return commonDao.getAllPropDetlsExchangeDeed(valId);
	}

	/**
	 * getExchangeDeedDutyDetls for getting duty details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getExchangeDeedDutyDetls(String appId) throws Exception {

		String refValId = getRefValIdExchngDeed(appId); // method for getting
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
	public String getRefValIdExchngDeed(String appId) throws Exception {

		return commonDao.getRefValIdExchngDeed(appId);

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

		String refId = getRefValIdExchngDeed(appId);

		if (refId != null && !refId.equalsIgnoreCase("")) {
			return commonDao.getExchangeDeedFinalMV(refId);
		} else {
			return null;
		}

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
	public ArrayList getSubClauseListBuilding(String valId, String floorId)
			throws Exception {

		String[] param = { valId, floorId };

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
	 * 
	 */
	public boolean insertDepositDeedApplicantAndBankDetails(RegCommonForm form)
			throws Exception // main
	{
		boolean insertsuccess = false;
		RegCommonBO commonBo = new RegCommonBO();
	//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
	//	Date currentDate = new Date();

		// RegCommonDAO dao = new RegCommonDAO();

		// FOLLOWING CODE FOR OWNER DETAILS
		String ownerDetails[] = new String[18];
		// int partyId=Integer.parseInt(form.getPartyType());

		int partyId = 0;
		int claimantFlag = 0;
		int poaFlag=0 ;
		
		if(form.getCommonDeed()==1){
			claimantFlag = form.getExecutantClaimant();
			poaFlag=form.getPoaHolderFlag();
		}
		else{
		if (form.getPartyType() != null) {
			partyId = Integer.parseInt(form.getPartyType());
		}
        String[] claimantArr = commonBo.getClaimantFlag(Integer
				.toString(partyId));
		claimantFlag = Integer.parseInt(claimantArr[0].trim());
		poaFlag=Integer.parseInt(claimantArr[1].trim()) ;
		}
		
		
		if (poaFlag == RegInitConstant.POA_HOLDER_FLAG) {

			ownerDetails[0] = form.getHidnRegTxnId(); // reg txn id
			if (form.getOwnerOgrName().equals("")
					|| form.getOwnerOgrName().equals(null)) {
				ownerDetails[1] = "2"; // individual party type
				ownerDetails[2] = form.getOwnerName(); // first name
				ownerDetails[12] = ""; // authorized person name
			} else {
				ownerDetails[1] = "1"; // organization party type
				ownerDetails[2] = ""; // first name
				ownerDetails[12] = form.getOwnerName(); // authorized person
														// name
			}

			ownerDetails[3] = form.getOwnerGendar(); // gender
			// ownerDetails[4]=UserRegistrationBD.getOracleDate(form.getOwnerDay(),
			// form.getOwnerMonth(), form.getOwnerYear()); //age to DOB
			ownerDetails[4] = form.getOwnerAge(); // age
			ownerDetails[5] = form.getOwnerNationality(); // nationality

			String address = form.getOwnerAddress();
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			ownerDetails[6] = address; // address

			ownerDetails[7] = form.getOwnerPhno(); // phone number

			if (form.getOwnerEmailID() != null
					&& !form.getOwnerEmailID().equalsIgnoreCase("")
					&& !form.getOwnerEmailID().equalsIgnoreCase("null")) {
				ownerDetails[8] = form.getOwnerEmailID().replace(",",
						RegInitConstant.SPLIT_CONSTANT);// email id
			} else {
				ownerDetails[8] = form.getOwnerEmailID();
			}
			ownerDetails[9] = form.getOwnerListID(); // photo proof type id

			String proof = form.getOwnerIdno();
			proof = proof.replace(",", RegInitConstant.SPLIT_CONSTANT);
			ownerDetails[10] = proof; // photo proof number

			if (form.getOwnerEmailID() != null
					&& !form.getOwnerEmailID().equalsIgnoreCase("")
					&& !form.getOwnerEmailID().equalsIgnoreCase("null")) {
				ownerDetails[11] = form.getOwnerOgrName().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			} // organization name
			else {
				ownerDetails[11] = form.getOwnerOgrName();
			}
			ownerDetails[13] = getTransactingPartyIdSeq(); // party transaction
															// id
			ownerDetails[14] = "O"; // is applicant O-Owner
			ownerDetails[15] = form.getShareOfProp(); // share of property
			ownerDetails[16] = Integer
					.toString(RegInitConstant.ROLE_OWNER_SELF); // party role
																// type id
			ownerDetails[17] = form.getHidnUserId(); // CREATED BY
		}

		// FOLLOWING CODE FOR APPLICANT DETAILS

		String[] param = new String[56];

		if (form.getCommonDeed() != 1) {
			param[29] = form.getPartyType();
			param[51] = "";
		} else {
			param[29] = "0"; // for common deeds
			param[51] = Integer.toString(form.getExecutantClaimant());
		}

		param[49] = form.getCommonDeedRoleName(); // COMMON DEED ROLE NAME

		if (form.getListAdoptedParty().equalsIgnoreCase("1")) {

			param[0] = form.getHidnRegTxnId();
			param[1] = form.getListAdoptedParty();
			param[2] = "";
			param[3] = "";
			param[4] = "";
			//param[5] = "";
			param[6] = ""; // AGE
			param[7] = "";
			param[8] = form.getCountry();
			param[9] = form.getStatename();
			param[10] = form.getDistrict();
			String address = form.getOrgaddress();
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[11] = address;
			param[12] = "";
			param[13] = form.getPhno();
			param[14] = form.getMobno();
			param[15] = "";
			param[16] = "";
			param[17] = "";
			param[18] = form.getOgrName().replace(",",RegInitConstant.SPLIT_CONSTANT);
			param[19] = form.getAuthPerName();
			//param[20] = "";
			param[21] = "";
			param[22] = ""; // CASTE TO CATEGORY
			param[23] = form.getPartyRoleTypeId(); // party txn id
			param[24] = "Y";
			param[25] = ""; // OCCUPATION
			param[26] = "";
			param[27] = form.getRelationWithOwner();
			param[28] = form.getShareOfProp();
			// param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = "";

			if (ownerDetails[13] == null) {
				param[32] = "";
			} else {
				param[32] = ownerDetails[13];
			} // self referencing key of owner in case of poa holder

			param[33] = "";
			param[34] = "";
			param[35] = "";
			param[36] = ""; // ST RADIO
			param[37] = ""; // ST CERTIFICATE FIELD
			param[38] = ""; // ST UPLOAD file path
			
			param[50] = Integer.toString(form.getAuthPerRelationship()); // relationship type
			
			param[5] = form.getAuthPerGendar();
			param[20] = form.getAuthPerFatherName();
			
			param[52] = form.getAuthPerCountry();
			param[53] = form.getAuthPerStatename();
			param[54] = form.getAuthPerDistrict();
			String authAddress = form.getAuthPerAddress();
			authAddress = authAddress.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[55] = authAddress; 

		} else {

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
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[11] = address;
			param[12] = form.getPostalCode();
			param[13] = form.getIndphno();
			param[14] = form.getIndmobno();
			if (form.getEmailID() != null
					&& !form.getEmailID().equalsIgnoreCase("")
					&& !form.getEmailID().equalsIgnoreCase("null")) {
				param[15] = form.getEmailID().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			} else {
				param[15] = "";
			}
			param[16] = form.getListID();
			String proof = form.getIdno();
			proof = proof.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[17] = proof;
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
			param[28] = form.getShareOfProp();
			// param[29] = form.getPartyType();
			param[30] = form.getHidnUserId();
			param[31] = form.getSpouseName();

			if (ownerDetails[13] == null)
				param[32] = "";
			else
				param[32] = ownerDetails[13]; // self referencing key of owner
												// in case of poa holder

			if (form.getIndDisabilityDesc() != null
					&& !form.getIndDisabilityDesc().equalsIgnoreCase("")
					&& !form.getIndDisabilityDesc().equalsIgnoreCase("null")) {
				param[33] = form.getIndDisabilityDesc().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // DISABILITY
															// DESCRIPTION
			} else {
				param[33] = "";
			}
			param[34] = form.getIndMinority(); // MINORITY
			param[35] = form.getIndPovertyLine(); // POVERTY LINE

			if (form.getIndCategory().equalsIgnoreCase("1")) {
				param[36] = form.getIndScheduleArea(); // ST RADIO
				if (form.getIndScheduleArea().equalsIgnoreCase("N")) {
					param[37] = form.getPermissionNo().replace(",",
							RegInitConstant.SPLIT_CONSTANT); // ST CERTIFICATE
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

		if (form.getDeedTypeFlag() == 0) {
			if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

				param[41] = form.getU4FilePath(); // EXEC_PHOTO_PATH
				param[40] = form.getU3FilePath(); // NOT_AFFD_EXEC_PATH
				param[45] = form.getU10FilePath(); // PAN_FORM_60_PATH

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

				RegCommonBO bo = new RegCommonBO();
				param[41] = form.getU7FilePath(); // EXEC_PHOTO_PATH
				param[42] = form.getU5FilePath(); // NOT_AFFD_ATTR_PATH
				param[43] = form.getU6FilePath(); // ATTR_PHOTO_PATH
				param[46] = form.getSrOfficeName(); // SR OFFICE NAME
				param[47] = form.getPoaRegNo(); // POA REG NO
				param[48] = bo.convertCalenderDate(form.getDatePoaReg()); // POA
																			// REG
																			// DATE

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

				param[44] = form.getU8FilePath(); // CLAIMNT_PHOTO_PATH
				param[45] = form.getU11FilePath(); // PAN_FORM_60_PATH

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

				param[43] = form.getU9FilePath(); // ATTR_PHOTO_PATH

			}
		}

		// FOLLOWING CODE FOR TRANSACTION DETAILS
		String deed[] = new String[9];
		deed[0] = form.getHidnRegTxnId(); // REG_TXN_ID

		if (form.getDeedID() == RegInitConstant.DEED_LEASE_NPV
				|| form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV
				|| form.getDeedID() == RegInitConstant.DEED_POA
				|| form.getDeedID() == RegInitConstant.DEED_SETTLEMENT_NPV
				|| form.getDeedID() == RegInitConstant.DEED_WILL_NPV
				|| form.getDeedID() == RegInitConstant.DEED_TRANSFER_LEASE_NPV
				|| form.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV
				|| form.getDeedID() == RegInitConstant.DEED_COMPOSITION_NPV
				|| form.getDeedID() == RegInitConstant.DEED_SECURITY_BOND_NPV
				|| form.getDeedID() == RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
				|| form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV
				|| form.getDeedID() == RegInitConstant.DEED_TRANSFER_NPV
				|| form.getDeedID() == RegInitConstant.DEED_FURTHER_CHARGE_NPV
				|| (form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
						&& form.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)) {

			deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
		} else {
			if (form.getShareOfProp() != null
					&& !form.getShareOfProp().equalsIgnoreCase("")) {
				if (form.getShareOfProp().equalsIgnoreCase("100")) {
					deed[1] = RegInitConstant.STATUS_TRNS_SAVED; // REGISTRATION_TXN_STATUS
				} else {
					deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
				}
			} else {

				if (form.getDeedTypeFlag() == 0) {
					if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) {
						deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
					} else {
						deed[1] = RegInitConstant.STATUS_TRNS_SAVED;
					}
				} else {

					if (form.getCommonDeed() == 1) {
						if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) {
							deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
						} else {
							deed[1] = RegInitConstant.STATUS_TRNS_SAVED; // for
																			// landing
																			// on
																			// particulars
																			// of
																			// transaction
						}
					} else {
						if (form.getAddMoreTransParty().equalsIgnoreCase("Y")) {
							deed[1] = RegInitConstant.STATUS_APP_SAVED; // REGISTRATION_TXN_STATUS
						} else {
							deed[1] = RegInitConstant.STATUS_PROP_SAVED;
						}
					}

				}
			}
		}
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
		insertsuccess = commonDao.insrtDepositDeedApplcntTransPartyBnkDetls(param, deed, extraDetls, ownerDetails, form.getExmpID());

		return insertsuccess;
	}

	public ArrayList getPropTypeL1List(String propId,String languageLocale) throws Exception {

		return commonDao.getPropTypeL1List(propId,languageLocale);

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
	public String getCategoryName(String id,String languageLocale) throws Exception {
		return commonDao.getCategoryName(id,languageLocale);
	}

	/**
	 * getPropertyDetailsFromValuation for getting property details from
	 * valuation tables.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyDetailsFromValuation(String valId)
			throws Exception {

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
	public CustomArrayList getPropDetlsForDisplay(String propId) throws Exception {
		return commonDao.getPropDetlsForDisplay(propId);

	}

	/**
	 * getPropKhasraDetlsForDisplay for getting PROPERTY KHASRA details from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropKhasraDetlsForDisplay(String propId)
			throws Exception {
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
	public String getOccupationName(String id,String languageLocale) throws Exception {
		return commonDao.getOccupationName(id,languageLocale);
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
					logger.debug("property type l2:-" + lst.get(0) + ":"
							+ lst.get(1));
					dto.setPropTypeL2Id((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setPropTypeL2((String) lst.get(1));
					}else{
						dto.setPropTypeL2((String) lst.get(2));
					}
					// dto.setHdnPropTypeL2((String) lst.get(0) + "~"
					// + (String) lst.get(1));
					list.add(dto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
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
	public ArrayList getUnitList(String propL1Id,String languageLocale) throws Exception {
		ArrayList list = new ArrayList();
		try {

			ArrayList ret = commonDao.getUnitList(propL1Id);

			if (ret != null) {
				for (int i = 0; i < ret.size(); i++) {
					ArrayList lst = (ArrayList) ret.get(i);
					RegCompletDTO dto = new RegCompletDTO();
					logger.debug("unit:-" + lst.get(0) + ":" + lst.get(1));
					dto.setUnitTypeId((String) lst.get(0));
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setUnitType((String) lst.get(1));
					dto.setHdnUnitType((String) lst.get(0) + "~"
							+ (String) lst.get(1));
					}else{
						dto.setUnitType((String) lst.get(2));
						dto.setHdnUnitType((String) lst.get(0) + "~"
								+ (String) lst.get(2));
					}
					list.add(dto);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
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
	public String getPropertyTypeName(String id,String languageLocale) throws Exception {
		return commonDao.getPropertyTypeName(id,languageLocale);
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
	 * 
	 */

	public boolean insertPropertyDetailsNonPropDeeds(RegCommonForm regInitForm,
			RegCompletDTO dto, String status) throws Exception {
		boolean insertsuccess = false;
		String[] splitParam;

		// PROPERTY DETAILS
		String[] param = new String[32];
		param[0] = dto.getPropertyId(); // PROPERTY ID
		param[1] = regInitForm.getHidnRegTxnId(); // REG TXN ID
		if (dto.getVikasId() != null) {
			param[2] = dto.getVikasId().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[2] = "";
		}
		param[3] = dto.getRicircle().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		if (dto.getLayoutdet() != null) {
			param[4] = dto.getLayoutdet().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[4] = "";
		}
		if (dto.getSheetnum() != null) {
			param[5] = dto.getSheetnum().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[5] = "";
		}
		if (dto.getPlotnum() != null) {
			param[6] = dto.getPlotnum().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[6] = "";
		}
		param[7] = dto.getPropAddress().replace(",",
				RegInitConstant.SPLIT_CONSTANT);
		param[8] = dto.getEast().replace(",", RegInitConstant.SPLIT_CONSTANT);
		param[9] = dto.getWest().replace(",", RegInitConstant.SPLIT_CONSTANT);
		param[10] = dto.getNorth().replace(",", RegInitConstant.SPLIT_CONSTANT);
		param[11] = dto.getSouth().replace(",", RegInitConstant.SPLIT_CONSTANT);
		if (dto.getPropLandmark() != null) {
			param[12] = dto.getPropLandmark().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
		} else {
			param[12] = "";
		}

		splitParam = dto.getAreaTypeId().split("~");
		param[13] = splitParam[0];
		splitParam = dto.getGovmunciplId().split("~");
		param[14] = splitParam[0];
		splitParam = dto.getPropertyTypeId().split("~");
		param[15] = splitParam[0];
		splitParam = dto.getPropTypeL1Id().split("~");
		param[16] = splitParam[0];

		if (dto.getPropTypeL2Id() != null
				&& !dto.getPropTypeL2Id().equalsIgnoreCase("Select")) {
			splitParam = dto.getPropTypeL2Id().split("~");
			param[17] = splitParam[0];
		} else {
			param[17] = "";
		}

		if (!param[15].equalsIgnoreCase("2")) {
			splitParam = dto.getUnitId().split("~");
			param[18] = splitParam[0];
			param[19] = Float.toString(dto.getArea());
		} else {
			param[18] = "";
			param[19] = "";
		}
		param[20] = "N";
		splitParam = dto.getDistId().split("~");
		param[21] = splitParam[0];
		splitParam = dto.getTehsilId().split("~");
		param[22] = splitParam[0];
		splitParam = dto.getWardId().split("~");
		param[23] = splitParam[0];
		splitParam = dto.getMohallaId().split("~");
		param[24] = splitParam[0];
		param[25] = regInitForm.getHidnUserId();

		param[26] = dto.getPropImage1FilePath();
		param[27] = dto.getPropImage2FilePath();
		param[28] = dto.getPropImage3FilePath();
		param[29] = dto.getPropMapFilePath();
		param[30] = dto.getPropRinFilePath();
		param[31] = dto.getPropKhasraFilePath();

		// KHASRA DETAILS
		String[] khasra = new String[4];
		if (dto.getKhasraNoArray() != null && dto.getKhasraAreaArray() != null
				&& dto.getLagaanArray() != null
				&& dto.getRinPustikaArray() != null
				&& !dto.getKhasraNoArray().equalsIgnoreCase("null")
				&& !dto.getKhasraAreaArray().equalsIgnoreCase("null")
				&& !dto.getLagaanArray().equalsIgnoreCase("null")
				&& !dto.getRinPustikaArray().equalsIgnoreCase("null")
				&& !dto.getKhasraNoArray().equalsIgnoreCase("")
				&& !dto.getKhasraAreaArray().equalsIgnoreCase("")
				&& !dto.getLagaanArray().equalsIgnoreCase("")
				&& !dto.getRinPustikaArray().equalsIgnoreCase("")) {
			khasra[0] = dto.getKhasraNoArray();
			khasra[1] = dto.getRinPustikaArray();
			khasra[2] = dto.getKhasraAreaArray();
			khasra[3] = dto.getLagaanArray();
		} else {
			khasra = null;
		}

		String[] regStatus = { status, regInitForm.getHidnRegTxnId() };

		// FLOOR DETAILS
		if (dto.getMapBuilding().isEmpty()) {
			insertsuccess = commonDao.insertPropertyDetailsNonPropDeeds(param,
					khasra, new HashMap(), regStatus,
					getPartyTxnIdsForPropMap(regInitForm));
		} else {
			insertsuccess = commonDao.insertPropertyDetailsNonPropDeeds(param,
					khasra, dto.getMapBuilding(), regStatus,
					getPartyTxnIdsForPropMap(regInitForm));
		}

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
	public ArrayList getTransPartyDetsNonPropDeed(String appId)
			throws Exception {

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
	public ArrayList getBuildingFloorDetailsNonProp(String propId)
			throws Exception {
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
	public boolean insertEstampMappingDetls(String eStampTxnId,
			RegCommonForm form) {

		String[] param = { eStampTxnId, form.getHidnRegTxnId(),
				form.getHidnUserId() };
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
	public ArrayList getPartyIdsExchngPdf(String appId, int partyCount,
			int deedId) throws Exception {

		String[] param = new String[3];

		param[0] = "";
		param[1] = "";

		if (partyCount == 1) {

			if (deedId == RegInitConstant.DEED_EXCHANGE) {
				param[0] = Integer
						.toString(RegInitConstant.ROLE1_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE1_OWNER_SELF);
			} else {
				param[0] = Integer
						.toString(RegInitConstant.ROLE_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE_OWNER_SELF);
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
	public ArrayList getPropIdsExchngPdf(String appId, int partyCount,
			int deedId) throws Exception {
		String[] param = new String[4];

		param[0] = "";
		param[1] = "";

		if (partyCount == 1) {
			if (deedId == RegInitConstant.DEED_EXCHANGE) {
				param[0] = Integer
						.toString(RegInitConstant.ROLE1_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE1_OWNER_SELF);
			} else {
				param[0] = Integer
						.toString(RegInitConstant.ROLE_OWNER_POA_HOLDER);
				param[1] = Integer.toString(RegInitConstant.ROLE_OWNER_SELF);
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

		} catch (Exception exception) {
			logger.debug("Exception in getPartyTxnIdListTitleDeed" + exception);
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
	public ArrayList getBuildingFloorDetailsTitleDeed(String propId)
			throws Exception {
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
	 * 
	 */
	public boolean updateTransPartyDetails(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;

		// FOLLOWING CODE FOR OWNER DETAILS
		String ownerDetails[] = new String[15];
		int partyId = Integer.parseInt(form.getPartyType());
		// int
		// claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(partyId)));

		RegCommonBO commonBo = new RegCommonBO();
		String[] claimantArr = commonBo.getClaimantFlag(Integer
				.toString(partyId));
		int claimantFlag = Integer.parseInt(claimantArr[0].trim());

		if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || 
				form.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG) {

			if (form.getOwnerOgrNameTrns().equals("")
					|| form.getOwnerOgrNameTrns().equals(null)) {
				ownerDetails[0] = "2"; // individual party type
				ownerDetails[1] = form.getOwnerNameTrns(); // first name
				ownerDetails[11] = ""; // authorized person name
			} else {
				ownerDetails[0] = "1"; // organization party type
				ownerDetails[1] = ""; // first name
				ownerDetails[11] = form.getOwnerNameTrns(); // authorized person
															// name
			}

			ownerDetails[2] = form.getOwnerGendarTrns(); // gender
			// ownerDetails[3]=UserRegistrationBD.getOracleDate(form.getOwnerDayTrns(),
			// form.getOwnerMonthTrns(), form.getOwnerYearTrns()); //age to DOB
			ownerDetails[3] = form.getOwnerAgeTrns(); // age
			ownerDetails[4] = form.getOwnerNationalityTrns(); // nationality

			String address = form.getOwnerAddressTrns();
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			ownerDetails[5] = address; // address

			ownerDetails[6] = form.getOwnerPhnoTrns(); // phone number

			if (form.getOwnerEmailIDTrns() != null
					&& !form.getOwnerEmailIDTrns().equalsIgnoreCase("")
					&& !form.getOwnerEmailIDTrns().equalsIgnoreCase("null")) {
				ownerDetails[7] = form.getOwnerEmailIDTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // email id
			} else {
				ownerDetails[7] = "";
			}
			ownerDetails[8] = form.getOwnerListIDTrns(); // photo proof type id

			String proof = form.getOwnerIdnoTrns();
			proof = proof.replace(",", RegInitConstant.SPLIT_CONSTANT);
			ownerDetails[9] = proof; // photo proof number

			if (form.getOwnerOgrNameTrns() != null
					&& !form.getOwnerOgrNameTrns().equalsIgnoreCase("")
					&& !form.getOwnerOgrNameTrns().equalsIgnoreCase("null")) {
				ownerDetails[10] = form.getOwnerOgrNameTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // organization name
			} else {
				ownerDetails[10] = "";
			}
			ownerDetails[12] = form.getHidnUserId(); // CREATED BY
			ownerDetails[13] = form.getHidnRegTxnId(); // reg txn id
			ownerDetails[14] = form.getOwnerIdTrns(); // party transaction id
		} else {
			ownerDetails = null;
		}

		// FOLLOWING CODE FOR APPLICANT DETAILS
		String[] param = new String[47];

		if (form.getListAdoptedPartyTrns().equalsIgnoreCase("1")) {

			param[0] = "";
			param[1] = "";
			param[2] = "";
			//param[3] = "";
			
			param[3] = form.getAuthPerGendarTrns();
			
			param[4] = ""; // AGE TO DOB
			param[5] = "";
			param[6] = form.getDistrictTrns();
			String address = form.getOrgaddressTrns();
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[7] = address;
			param[8] = "";
			param[9] = form.getPhnoTrns();
			param[10] = form.getMobnoTrns();
			param[11] = "";
			param[12] = "";
			param[13] = "";
			param[14] = form.getOgrNameTrns().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			param[15] = form.getAuthPerNameTrns();
			//param[16] = "";
			
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
			//param[40] = ""; // RELATIONSHIP_TYPE_ID
			param[40] = Integer.toString(form.getAuthPerRelationshipTrns()); // RELATIONSHIP_TYPE_ID
			
			param[41]=form.getAuthPerCountryTrns();
			param[42]=form.getAuthPerStatenameTrns();
			param[43]=form.getAuthPerDistrictTrns();
			param[44]=form.getAuthPerAddressTrns();
			
			param[45] = form.getHidnRegTxnId();
			param[46] = form.getPartyTxnId(); // party txn id

		} else {

			// param[1] = form.getListAdoptedPartyTrns();
			param[0] = form.getFnameTrns();
			param[1] = form.getMnameTrns();
			param[2] = form.getLnameTrns();
			param[3] = form.getGendarTrns();
			// param[4] =
			// UserRegistrationBD.getOracleDate(form.getUserDayTrns(),
			// form.getUserMonthTrns(), form.getUserYearTrns()); //AGE TO DOB
			param[4] = form.getAgeTrns();
			param[5] = form.getNationalityTrns();
			param[6] = form.getInddistrictTrns();
			String address = form.getIndaddressTrns();
			address = address.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[7] = address;
			param[8] = form.getPostalCodeTrns();
			param[9] = form.getIndphnoTrns();
			param[10] = form.getIndmobnoTrns();
			if (form.getEmailIDTrns() != null
					&& !form.getEmailIDTrns().equalsIgnoreCase("")
					&& !form.getEmailIDTrns().equalsIgnoreCase("null")) {
				param[11] = form.getEmailIDTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			} else {
				param[11] = "";
			}
			param[12] = form.getListIDTrns();
			String proof = form.getIdnoTrns();
			proof = proof.replace(",", RegInitConstant.SPLIT_CONSTANT);
			param[13] = proof;
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
			if (form.getIndDisabilityDescTrns() != null
					&& !form.getIndDisabilityDescTrns().equalsIgnoreCase("")
					&& !form.getIndDisabilityDescTrns()
							.equalsIgnoreCase("null")) {
				param[24] = form.getIndDisabilityDescTrns().replace(",",
						RegInitConstant.SPLIT_CONSTANT); // DISABILITY
															// DESCRIPTION
			} else {
				param[24] = "";
			}
			param[25] = form.getIndMinorityTrns(); // MINORITY
			param[26] = form.getIndPovertyLineTrns(); // POVERTY LINE

			if (form.getIndCategoryTrns().equalsIgnoreCase("1")) {
				param[27] = form.getIndScheduleAreaTrns(); // ST RADIO
				if (form.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
					param[28] = form.getPermissionNoTrns().replace(",",
							RegInitConstant.SPLIT_CONSTANT); // ST CERTIFICATE
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

			param[41]="";
			param[42]="";
			param[43]="";
			param[44]="";
			
			param[45] = form.getHidnRegTxnId();
			param[46] = form.getPartyTxnId(); // party txn id

		}

		param[30] = form.getU2FilePathTrns(); // PHOTO_PROOF_PATH

		if (form.getDeedTypeFlag() == 1 || form.getCommonDeed() == 1) {

			param[31] = ""; // NOT_AFFD_EXEC_PATH
			param[32] = ""; // EXEC_PHOTO_PATH
			param[33] = ""; // NOT_AFFD_ATTR_PATH
			param[34] = ""; // ATTR_PHOTO_PATH
			param[35] = ""; // CLAIMNT_PHOTO_PATH
			param[36] = ""; // PAN_FORM_60_PATH
			param[37] = ""; // SR OFFICE NAME
			param[38] = ""; // POA REG NO
			param[39] = ""; // POA REG DATE

		}

		else {
			param[31] = form.getU3FilePathTrns(); // NOT_AFFD_EXEC_PATH
			param[32] = ""; // EXEC_PHOTO_PATH
			param[33] = form.getU5FilePathTrns(); // NOT_AFFD_ATTR_PATH
			param[34] = ""; // ATTR_PHOTO_PATH
			param[35] = form.getU8FilePathTrns(); // CLAIMNT_PHOTO_PATH
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

				param[32] = form.getU4FilePathTrns(); // EXEC_PHOTO_PATH
				param[36] = form.getU10FilePathTrns(); // PAN_FORM_60_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

				RegCommonBO bo = new RegCommonBO();
				param[32] = form.getU7FilePathTrns(); // EXEC_PHOTO_PATH
				param[34] = form.getU6FilePathTrns(); // ATTR_PHOTO_PATH
				param[37] = form.getSrOfficeNameTrns(); // SR OFFICE NAME
				param[38] = form.getPoaRegNoTrns(); // POA REG NO
				param[39] = bo.convertCalenderDate(form.getDatePoaRegTrns()); // POA
																				// REG
																				// DATE

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

				param[36] = form.getU11FilePathTrns(); // PAN_FORM_60_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE

			} else if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

				param[34] = form.getU9FilePathTrns(); // ATTR_PHOTO_PATH
				// param[37]=""; //SR OFFICE NAME
				// param[38]=""; //POA REG NO
				// param[39]=""; //POA REG DATE
			}
			// }
		}

		insertsuccess = commonDao.updateTransPartyDetls(param, ownerDetails);

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

	/**
	 * getAllPropDetlsExchangeDeedDash for getting ALL PROPERTY DETAILS from db
	 * for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllPropDetlsExchangeDeedDash(String valId)
			throws Exception {
		return commonDao.getAllPropDetlsExchangeDeedDash(valId);
	}

	/**
	 * getRemValuationIdsExchangeDeed for getting remaining VALUATION IDS from
	 * db for dashboard.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getRemValuationIdsExchangeDeed(String valId)
			throws Exception {
		return commonDao.getRemValuationIdsExchangeDeed(valId);
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
	public boolean insertTxnDetailsFinalAction(String appId) {

		String[] statusParam = { RegInitConstant.STATUS_FINAL_SCREEN, appId };

		return commonDao.insertTxnDetailsFinalAction(statusParam);
	}

	/**
	 * updateBankDetails Returns boolean value in order to check the updation.
	 * 
	 * @param form
	 * @author Roopam
	 * @return boolean
	 * @throws Exception
	 * 
	 */
	public boolean updateBankDetails(RegCommonForm form) throws Exception {
		boolean insertsuccess = false;

		String bankDetls[] = new String[47];

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
		bankDetls[46] = form.getHidnRegTxnId();

		if (form.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE) {
			bankDetls[0] = form.getBankName();
			bankDetls[1] = form.getBranchName();
			bankDetls[2] = form.getBankAddress().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			bankDetls[3] = form.getBankAuthPer();
			bankDetls[4] = Integer.toString(form.getBankLoanAmt());
			bankDetls[5] = Integer.toString(form.getBankSancAmt());
		}
		if (form.getDeedID() == RegInitConstant.DEED_TRUST
				|| form.getDeedID() == RegInitConstant.DEED_TRUST_PV) {

			bankDetls[7] = form.getTrustName();
			if (form.getTrustDate() != null
					&& !form.getTrustDate().equalsIgnoreCase("")) {
				RegCommonBO bo = new RegCommonBO();
				bankDetls[8] = bo.convertCalenderDate(form.getTrustDate());

			} else {
				bankDetls[8] = form.getTrustDate();
			}

		}
		if (form.getDeedID() == RegInitConstant.DEED_LEASE_PV
				|| form.getDeedID() == RegInitConstant.DEED_LEASE_NPV) {
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
		if (form.getDeedID() == RegInitConstant.DEED_MORTGAGE_PV
				|| form.getDeedID() == RegInitConstant.DEED_MORTGAGE_NPV) {

			bankDetls[15] = form.getWithPos();
			bankDetls[16] = Double.toString(form.getSecLoanAmt());

		}
		if (form.getDeedID() == RegInitConstant.DEED_AWARD_PV
				|| form.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) {

			bankDetls[17] = form.getFnameArb();
			bankDetls[18] = form.getMnameArb();
			bankDetls[19] = form.getLnameArb();
			bankDetls[20] = form.getGendarArb();
			bankDetls[21] = form.getAgeArb();
			bankDetls[22] = form.getFatherNameArb();
			bankDetls[23] = form.getMotherNameArb();
			bankDetls[24] = form.getSpouseNameArb();
			bankDetls[25] = form.getNationalityArb();
			bankDetls[26] = form.getIndaddressArb().replace(",",
					RegInitConstant.SPLIT_CONSTANT);
			bankDetls[27] = form.getIndcountryArb();
			bankDetls[28] = form.getIndstatenameArb();
			bankDetls[29] = form.getInddistrictArb();
			bankDetls[30] = form.getIndphnoArb();
			bankDetls[31] = form.getIndmobnoArb();
			if (form.getEmailIDArb() != null
					&& !form.getEmailIDArb().equalsIgnoreCase("")) {
				bankDetls[32] = form.getEmailIDArb().replace(",",
						RegInitConstant.SPLIT_CONSTANT);
			} else {
				bankDetls[32] = "";
			}
			bankDetls[33] = form.getIndCategoryArb();
			bankDetls[34] = form.getIndDisabilityArb();
			if (form.getIndDisabilityArb().equalsIgnoreCase("Y")) {

				if (form.getIndDisabilityDescArb() != null
						&& !form.getIndDisabilityDescArb().equalsIgnoreCase("")) {
					bankDetls[35] = form.getIndDisabilityDescArb().replace(",",
							RegInitConstant.SPLIT_CONSTANT);
				} else {
					bankDetls[35] = "";
				}
			} else {
				bankDetls[35] = "";
			}
			bankDetls[36] = form.getListIDArb();
			bankDetls[37] = form.getIdnoArb().replace(",",
					RegInitConstant.SPLIT_CONSTANT);

		}
		if (form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_PV
				|| form.getDeedID() == RegInitConstant.DEED_AGREEMENT_MEMO_NPV) {
			bankDetls[10] = Double.toString(form.getAdvance());
			RegCommonBO bo = new RegCommonBO();
			bankDetls[38] = bo.convertCalenderDate(form.getAdvancePaymntDate());
			bankDetls[39] = form.getPossGiven();
		}
		if (form.getDeedID() == RegInitConstant.DEED_POA) {
			bankDetls[40] = form.getPoaWithConsid();
			bankDetls[41] = Double.toString(form.getPoaPeriod());

		}
		if (form.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV) {

			bankDetls[42] = Double.toString(form.getPaidLoanAmt());

		}
		if ((form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
					 (form.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || form.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))) {

			if (form.getContriProp() != null
					&& !form.getContriProp().equalsIgnoreCase("")) {
				bankDetls[43] = form.getContriProp();
			} else {
				bankDetls[43] = "";
			}

		}

		if (form.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_PV || 
				(form.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
				&& form.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)) {

			RegCommonBO bo = new RegCommonBO();
			if (form.getDissolutionDate() != null
					&& !form.getDissolutionDate().equalsIgnoreCase("")) {

				bankDetls[44] = bo.convertCalenderDate(form
						.getDissolutionDate());

			} else {
				bankDetls[44] = form.getDissolutionDate();
			}

			if (form.getRetirmentDate() != null
					&& !form.getRetirmentDate().equalsIgnoreCase("")) {
				// RegCommonBO bo=new RegCommonBO();
				bankDetls[45] = bo.convertCalenderDate(form.getRetirmentDate());

			} else {
				bankDetls[45] = form.getRetirmentDate();
			}

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
	public String[] getPartyTxnIdsForPropMap(RegCommonForm form)
			throws Exception {

		String[] param = { form.getHidnRegTxnId(), form.getHidnRegTxnId() };

		ArrayList list = commonDao.getPartyTxnIdsForPropMap(param, form
				.getDeedID(), form.getInstID());
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
		return commonDao.getExtraFieldLabel(instId,languageLocale);
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
	public ArrayList getDistIdNameOfficeNameDRS(String officeId)
			throws Exception {
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

		String[] param = new String[13];

		param[0] = regForm.getHidnRegTxnId();
		param[1] = regForm.getHidnUserId();

		if (regForm.getStampManually().equalsIgnoreCase("Y")) {

			param[2] = regForm.getStampduty1Adju();
			param[3] = regForm.getNagarPalikaDutyAdju();
			param[4] = regForm.getPanchayatDutyAdju();
			param[5] = regForm.getUpkarDutyAdju();
			param[6] = regForm.getTotaldutyAdju();
			param[7] = regForm.getRegistrationFeeAdju();

		} else {

			param[2] = regForm.getStampduty1();
			param[3] = regForm.getNagarPalikaDuty();
			param[4] = regForm.getPanchayatDuty();
			param[5] = regForm.getUpkarDuty();
			param[6] = regForm.getTotalduty();
			param[7] = regForm.getRegistrationFee();
		}

		param[8] = regForm.getMarketValueShares();
		param[9] = regForm.getDutyPaid();
		param[10] = regForm.getRegPaid();
		param[11] = regForm.getConsiAmountTrns();
		param[12] = regForm.getPurpose();

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
	/**
	 * getDutyDetls for getting duty details adju from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author SHREERAJ
	 */
	public ArrayList getDutyDetlsAdjuForConfirmation(String appId)
			throws Exception {
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
	public boolean insertParticularsDetails(HashMap map, String regId,
			String userId) {

		String statusParam[] = { RegInitConstant.STATUS_PROP_SAVED, regId };

		return commonDao.insertParticularsDetails(map, regId, userId,
				statusParam);
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
	public ArrayList getPartyDetailsCommonDeedsPdf(String partyId)
			throws Exception {
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
	public boolean updateAdjudicationFlag(String appId,String userId) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.updateAdjudicationFlag(appId,userId);
	}

	/**
	 * for getting all relationship list
	 * 
	 * @return ArrayList
	 */
	public ArrayList getRelationshipList(String gender,String languageLocale) {
		return commonDao.getRelationshipList(gender,languageLocale);
	}

	/**
	 * for getting relationship name from db.
	 * 
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRelationshipName(String id,String languageLocale) throws Exception {
		return commonDao.getRelationshipName(id,languageLocale);
	}
	/**
	 * for getting ExecutantClaimant drop down for universal deeds
	 * @author ROOPAM
	 * @date 22 October 2013
	 * @return ArrayList
	 */
	public ArrayList getExecutantClaimant(String languageLocale,int instId) {
		return commonDao.getExecutantClaimant(languageLocale,instId);
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
	public String getUnitName(String proofId,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getUnitName(proofId,languageLocale);
	}
	public String getPropertyL1Name(String id,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL1Name(id,languageLocale);
	}
	public String getPropertyL2Name(String id,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getPropertyL2Name(id,languageLocale);
	}
	public String getFloorName(String id,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getFloorName(id,languageLocale);
	}
	public String getAppleteName(String id,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getAppleteName(id,languageLocale);
	}
	public String getOfficeName(String id,String languageLocale) throws Exception {
		// RegCommonDAO dao = new RegCommonDAO();
		return commonDao.getOfficeName(id,languageLocale);
	}
}
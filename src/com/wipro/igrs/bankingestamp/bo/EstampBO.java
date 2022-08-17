package com.wipro.igrs.bankingestamp.bo;

import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.wipro.igrs.estamping.bd.EstampingBD;
import com.wipro.igrs.estamping.dao.EstampDAO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.dto.EstampDetailsDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.estamping.form.EstampFormBean;
import com.wipro.igrs.payments.dao.PaymentDAO;

/**
 * @author pavpapa
 */
public class EstampBO {
	EstampDAO objDao;

	public EstampBO() throws Exception {
		objDao = new EstampDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getting Country list
	 * 
	 * @return list
	 */
	/*public ArrayList getCountry() throws Exception {
		return objDao.getCountry();
	}*/
	/**
	 * Getting id list for dashboard page
	 * 
	 * @return list
	 */
	public ArrayList getSROFirst(String id) throws Exception {
		return objDao.getSROFirst(id);
	}

	/**
	 * Getting Purpose list
	 * 
	 * @return list
	 */
	public ArrayList getPurpose() throws Exception {
		return objDao.getPurpose();
	}

	public String getFee(String strPurpose) throws Exception {
		return objDao.getFee(strPurpose);
	}

	/**
	 * Getting Photo List
	 * 
	 * @return list
	 */
	public ArrayList getphotoIdList() {
		return objDao.getphotoIdList();
	}

	/**
	 * Getting deed details
	 * 
	 * @return list
	 */
	public ArrayList getOptionalDeedType() {
		return objDao.getOptionalDeedType();
	}

	/**
	 * Getting Instrument list
	 * 
	 * @param deedTypeID
	 * @return
	 */
	public ArrayList getInstrumentDet(String deedTypeID) throws Exception {
		return objDao.getInstrumentDet(deedTypeID);
	}

	/**
	 * getting Exemption list
	 * 
	 * @param deedTypeID
	 * @param insID
	 * @return
	 */
	public ArrayList getExempList(String insID, String deedTypeID) throws Exception {
		return objDao.getExempList(insID, deedTypeID);
	}

	/**
	 * Inserting Applicant Individual Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean applIndDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.applIndDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting Applicant Organization Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean applOrgDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.applOrgDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting Txn party Individual Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean txnIndDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.txnIndDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting Txn party Organization Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean txnOrgDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.txnOrgDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting Deed Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deedDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.deedDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting Stamp Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean stampdutyDetInsert(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.stampdutyDetInsert(param, roleId, funId, userId);
	}

	/**
	 * Inserting upload Document Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public boolean upLoadDocIns(String docId, String empTxnId, String fileName, File file) throws Exception {
		return objDao.upLoadDocIns(docId, empTxnId, fileName, file);
	}

	/**
	 * Inserting E-Stmp De-activation Party Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deactEstampPartyDetIns(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.deactEstampPartyDetIns(param, roleId, funId, userId);
	}

	/**
	 * Inserting E-Stmp De-activation A Details.
	 * 
	 * @param param
	 * @param userId
	 * @param funId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public boolean deactEstampDetIns(String param[], String roleId, String funId, String userId) throws Exception {
		return objDao.deactEstampDetIns(param, roleId, funId, userId);
	}

	/**
	 * getting Estamp Txn ID
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getEstampTxnId() {
		return objDao.getEstampTxnId();
	}

	/**
	 * getting Estamp Non Judicial Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printEstampTxnDet(String estampTxnId) throws Exception {
		return objDao.printEstampTxnDet(estampTxnId);
	}

	/**
	 * getting Estamp Judicial Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList printJudEstampTxnDet(String estampTxnId) throws Exception {
		return objDao.printJudEstampTxnDet(estampTxnId);
	}

	/**
	 * getting Registation Applicant Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getInitAppDet(String applRegId) throws Exception {
		return objDao.getInitAppDet(applRegId);
	}

	/**
	 * getting Party Type list Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyTypeList() {
		return objDao.getPartyTypeList();
	}

	/**
	 * getting Party Type ID -party Type Master
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPartyTypeId(String party, String partyType) {
		return objDao.getPartyTypeId(party, partyType);
	}

	/**
	 * getting getting Deactive Txn Id
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDeactTxnId() {
		return objDao.getDeactTxnId();
	}

	/**
	 * getting Registation Initiated Application Details
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getRegDeactDet(String _regIntId) throws Exception {
		return objDao.getRegDeactDet(_regIntId);
	}

	/**
	 * printing Deactivation Details.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList PrintDeactDet(String _dactTxnId) throws Exception {
		return objDao.PrintDeactDet(_dactTxnId);
	}

	/**
	 * calculating Non Judicial Stamp Duty
	 * 
	 * @param _refDeedTypeId
	 * @param _refInstrId
	 * @param _refExemId
	 * @param _secAmt
	 * @throws Exception
	 * @return
	 */
	public String getNonJudStampDuty(String _refDeedTypeId, String _refInstrId, String _refExemId, String _secAmt) {
		return objDao.getNonJudStampDuty(_refDeedTypeId, _refInstrId, _refExemId, _secAmt);
	}

	/**
	 * for calculating othersFeeDuty
	 * 
	 * @param _refFunId
	 * @throws Exception
	 * @return othersFeeDuty
	 */
	public ArrayList getOthersDuty(String _retFunId, String _serId, String _userType) throws Exception {
		return objDao.getOthersDuty(_retFunId, _serId, _userType);
	}

	/**
	 * getting SRO list values.
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSroList(String distId) throws Exception {
		return objDao.getSroList(distId);
	}

	public ArrayList getTrNameList() throws Exception {
		return objDao.getTrNameList();
	}

	public boolean savePstampDet(String[] pstampDet) throws Exception {
		return objDao.savePstampDet(pstampDet);
	}

	public boolean savePstCodeDet(String[] param) throws Exception {
		return objDao.savePstCodeDet(param);
	}

	public ArrayList getMStampViewDet(String mStampCodeFm, String mStampCodeTo) throws Exception {
		return objDao.getMStampViewDet(mStampCodeFm, mStampCodeTo);
	}

	public boolean upLoadDocIns(ArrayList columnName, ArrayList columnValue, String tableName, String docFieldName, String txnFieldName, String docTxnId, String fileName, String filePath) {
		return objDao.upLoadDocIns(columnName, columnValue, tableName, docFieldName, txnFieldName, docTxnId, fileName, filePath);
	}

	/**
	 * getting E-Stamp Searach details
	 * 
	 * @param estampCode
	 * @return
	 */
	public ArrayList getEstampSearchDet(String estampCode) {
		return objDao.getEstampSearchDet(estampCode);
	}

	/**
	 * getting deed details for given Deed Id
	 * 
	 * @param deedId
	 * @return ApplicantForm
	 */
	public EstampDetailsDTO getDeedDetails(String deedId) throws Exception {
		// TODO Auto-generated method stub
		return objDao.getDeedDetails(deedId);
	}

	public ArrayList getDeedTypeList() throws Exception {
		return objDao.getDeedTypeList();
	}

	public boolean deedAttrDet(EstampDetailsDTO objEstampDet, String roleId, String funId, String userId, String strNonJudEstTxnId) throws Exception {
		return objDao.deedAttrDet(objEstampDet, roleId, funId, userId, strNonJudEstTxnId);
	}

	/**
	 * getting deed details for estamp search
	 * 
	 * @param estampCode
	 * @return
	 */
	public ArrayList getdeedSearchDet(String estampCode) throws Exception {
		return objDao.getdeedSearchDet(estampCode);
	}

	public EstampDetailsDTO getdeedAttDet(String estampCode, String deedId) throws Exception {
		return objDao.getdeedAttDet(estampCode, deedId);
	}

	/**
	 * getPendingEstampApps for getting pending applications of estamps from db.
	 * 
	 * @param String
	 * @return ArrayList
	 * @author LAVI
	 */
	public ArrayList getPendingEstampApps(String userId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list = objEstampingBD.getPendingEstampApps(userId, language);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					objDashBoardDTO.setTransactionID(rowList.get(0));
					if (rowList.get(2) == null)
						objDashBoardDTO.setHdntransactionID(rowList.get(0) + "~" + rowList.get(6));
					else
						objDashBoardDTO.setHdntransactionID(rowList.get(0) + "~" + rowList.get(3));
					//}else{
					objDashBoardDTO.setDeedName(rowList.get(1));
					//objDashBoardDTO.setPaidAmount(rowList.get(2));
					System.out.println("*******" + (String) rowList.get(2));
					if ((rowList.get(2)) == null) {
						System.out.println("*******");
						objDashBoardDTO.setPaidAmount("-");
					} else
						objDashBoardDTO.setPaidAmount(rowList.get(2));
					if (rowList.get(2) == null) {
						objDashBoardDTO.setBalanceAmount(rowList.get(6));
					} else
						objDashBoardDTO.setBalanceAmount(rowList.get(3));
					/*	if(rowList.get(2)!=null){
							int paidAmt=Integer.parseInt(rowList.get(2).toString());
							int balAmt=Integer.parseInt(rowList.get(6).toString());
							if(balAmt>=paidAmt){
								objDashBoardDTO.setBalanceAmount(0);
							}
						}*/
					objDashBoardDTO.setLastTransactionDate(rowList.get(4));
					if ((rowList.get(4)) == null)
						objDashBoardDTO.setLastTransactionDate(rowList.get(5));
					else
						objDashBoardDTO.setLastTransactionDate(rowList.get(4));
					pendingAppListFinal.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pendingAppListFinal;
	}

	public ArrayList getDetailsOfDuties(DashBoardDTO dto, String language) throws Exception {
		String TxnId = dto.getHidnEstampTxnId();
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfDuties = new ArrayList();
		ArrayList stampexemp = new ArrayList();
		ArrayList regexemp = new ArrayList();
		ArrayList userListFinal = new ArrayList();
		ArrayList stampList = objEstampingBD.getStampExemptions(TxnId, language);
		if (stampList != null && stampList.size() > 0) {
			ArrayList rowList;
			for (int i = 0; i < stampList.size(); i++) {
				DashBoardDTO objDashBoardDTO = new DashBoardDTO();
				rowList = (ArrayList) stampList.get(i);
				objDashBoardDTO.setExemptionName((String) rowList.get(0));
				objDashBoardDTO.setExemptionDescription((String) rowList.get(1));
				stampexemp.add(objDashBoardDTO);
			}
		}
		ArrayList regList = objEstampingBD.getStampExemptions(TxnId, language);
		if (regList != null && regList.size() > 0) {
			ArrayList rowList;
			for (int i = 0; i < regList.size(); i++) {
				DashBoardDTO objDashBoardDTO = new DashBoardDTO();
				rowList = (ArrayList) regList.get(i);
				objDashBoardDTO.setExemptionName((String) rowList.get(0));
				objDashBoardDTO.setExemptionDescription((String) rowList.get(1));
				regexemp.add(objDashBoardDTO);
			}
		}
		ArrayList userList = objEstampingBD.getUserEneterableList(TxnId, language);
		if (userList != null && userList.size() > 0) {
			ArrayList rowList;
			for (int i = 0; i < userList.size(); i++) {
				DashBoardDTO objDashBoardDTO = new DashBoardDTO();
				rowList = (ArrayList) userList.get(i);
				objDashBoardDTO.setUserfieldName((String) rowList.get(0));
				objDashBoardDTO.setUserFieldValue((String) rowList.get(1));
				userListFinal.add(objDashBoardDTO);
			}
		}
		ArrayList list = objEstampingBD.getDetailsOfDuties(TxnId, language);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					dto.setMainTxnId1(rowList.get(0));
					objDashBoardDTO.setMainTxnId1(rowList.get(0));
					System.out.println("************" + rowList.get(0));
					System.out.println("************" + objDashBoardDTO.getMainTxnId1());
					//}else{
					objDashBoardDTO.setDeedType(rowList.get(1));
					System.out.println("************" + objDashBoardDTO.getDeedType());
					objDashBoardDTO.setInstType(rowList.get(2));
					objDashBoardDTO.setStampDutyDisplay(rowList.get(3));
					objDashBoardDTO.setNagarPalikaDutyDisplay(rowList.get(4));
					objDashBoardDTO.setPanchayatDutyDisplay(rowList.get(5));
					objDashBoardDTO.setUpkarDutyDisplay(rowList.get(6));
					objDashBoardDTO.setTotalDisplay(rowList.get(7));
					objDashBoardDTO.setDateCalculation(rowList.get(8));
					objDashBoardDTO.setEstampPurpose(rowList.get(9));
					if (rowList.get(10) == null)
						objDashBoardDTO.setDeedDuration("-");
					else
						objDashBoardDTO.setDeedDuration(rowList.get(10).toString());
					objDashBoardDTO.setInstrumentDiscription((String) rowList.get(11));
					objDashBoardDTO.setDutyAfterExemption((String) rowList.get(12));
					objDashBoardDTO.setRegFeeAfterExmemption((String) rowList.get(13));
					objDashBoardDTO.setStampExemptionList(stampexemp);
					objDashBoardDTO.setRegExemptionList(regexemp);
					objDashBoardDTO.setUserList(userListFinal);
					detilsOfDuties.add(objDashBoardDTO);
				}
				for (int i = 0; i < detilsOfDuties.size(); i++) {
					DashBoardDTO dto1 = (DashBoardDTO) detilsOfDuties.get(i);
					//System.out.println("<-----"+dto1.getMainTxnId1());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detilsOfDuties;
	}

	public ArrayList getParaDetls(String txnId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList alist = new ArrayList();
		ArrayList ali = objEstampingBD.getParaDetls(txnId, language);
		if (ali != null && ali.size() > 0) {
			ArrayList row;
			try {
				for (int i = 0; i < ali.size(); i++) {
					row = (ArrayList) ali.get(i);
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					objDashBoardDTO.setParaName((String) row.get(0));
					objDashBoardDTO.setParaValue((String) row.get(1));
					alist.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return alist;
	}

	public ArrayList getDetailsOfApplicant(String TxnId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfApplicant = new ArrayList();
		ArrayList list = objEstampingBD.getDetailsOfApplicant(TxnId, language);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//System.out.println("*********IN BO"+(String)rowList.get(22));
					//System.out.println("*********IN BO"+(String)rowList.get(2));
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					objDashBoardDTO.setApplicant_ind(rowList.get(22));
					if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("Y")) {
						objDashBoardDTO.setAdoptionFlag("N");
						objDashBoardDTO.setAppType(rowList.get(2));
						if (objDashBoardDTO.getAppType().toString().equals("2"))
						//}else{
						{
							objDashBoardDTO.setAppType(rowList.get(2));
							objDashBoardDTO.setAppTypeName(rowList.get(1));
							objDashBoardDTO.setAppFirsName(rowList.get(3));
							objDashBoardDTO.setAppMiddleName(rowList.get(4));
							objDashBoardDTO.setAppLastName(rowList.get(5));
							objDashBoardDTO.setAppGender(rowList.get(6));
							Object apgender = rowList.get(6);
							if (apgender.toString().equalsIgnoreCase("M")) {
								if (language.equalsIgnoreCase("en"))
									objDashBoardDTO.setAppGenderDisp("Male");
								else
									objDashBoardDTO.setAppGenderDisp("पुस्र्ष");
							} else {
								if (language.equalsIgnoreCase("en"))
									objDashBoardDTO.setAppGenderDisp("Female");
								else
									objDashBoardDTO.setAppGenderDisp("महिला");
							}
							objDashBoardDTO.setAppAge(rowList.get(7));
							objDashBoardDTO.setAppFatherName(rowList.get(8));
							objDashBoardDTO.setAppMotherName(rowList.get(9));
							objDashBoardDTO.setAppCountryName(rowList.get(10));
							objDashBoardDTO.setAppStateName(rowList.get(11));
							objDashBoardDTO.setAppDistrictName(rowList.get(12));
							objDashBoardDTO.setAppAddress(rowList.get(13));
							objDashBoardDTO.setAppPostalCode(rowList.get(14));
							objDashBoardDTO.setAppPhno(rowList.get(15));
							objDashBoardDTO.setAppMobno(rowList.get(16));
							objDashBoardDTO.setAppEmailID(rowList.get(17));
							objDashBoardDTO.setAppPhotoIdName(rowList.get(18));
							objDashBoardDTO.setAppPhotoIdno(rowList.get(19));
							objDashBoardDTO.setAppPersons(rowList.get(20));
						} else {
							objDashBoardDTO.setAppType(rowList.get(2));
							objDashBoardDTO.setAppTypeName(rowList.get(1));
							objDashBoardDTO.setAppOrgName(rowList.get(21));
							objDashBoardDTO.setAppAuthFirstName(rowList.get(3));
							objDashBoardDTO.setAppAuthMiddleName(rowList.get(4));
							objDashBoardDTO.setAppAuthLastName(rowList.get(5));
							objDashBoardDTO.setAppCountryName(rowList.get(10));
							objDashBoardDTO.setAppStateName(rowList.get(11));
							objDashBoardDTO.setAppDistrictName(rowList.get(12));
							objDashBoardDTO.setAppAddress(rowList.get(13));
							objDashBoardDTO.setAppPostalCode(rowList.get(14));
							objDashBoardDTO.setAppPhno(rowList.get(15));
							objDashBoardDTO.setAppMobno(rowList.get(16));
							objDashBoardDTO.setAppEmailID(rowList.get(17));
							objDashBoardDTO.setAppPersons(rowList.get(20));
						}
					}
					if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("N") || objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("A")) {
						if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("A")) {
							objDashBoardDTO.setAdoptionFlag("Y");
						} else {
							objDashBoardDTO.setAdoptionFlag("N");
						}
						objDashBoardDTO.setPartyType(rowList.get(2));
						if (objDashBoardDTO.getPartyType().toString().equals("2"))
						//}else{
						{
							objDashBoardDTO.setPartyType(rowList.get(2));
							objDashBoardDTO.setPartyTypeName(rowList.get(1));
							objDashBoardDTO.setPartyFirstName(rowList.get(3));
							objDashBoardDTO.setPartyMiddleName(rowList.get(4));
							objDashBoardDTO.setPartyLastName(rowList.get(5));
							objDashBoardDTO.setPartyGender(rowList.get(6));
							Object partygender = rowList.get(6);
							if (partygender.toString().equalsIgnoreCase("M")) {
								if (language.equalsIgnoreCase("en"))
									objDashBoardDTO.setPartyGenderDisp("Male");
								else
									objDashBoardDTO.setPartyGenderDisp("पुस्र्ष");
							} else {
								if (language.equalsIgnoreCase("en"))
									objDashBoardDTO.setPartyGenderDisp("Female");
								else
									objDashBoardDTO.setPartyGenderDisp("महिला");
							}
							objDashBoardDTO.setPartyAge(rowList.get(7));
							objDashBoardDTO.setPartyFatherName(rowList.get(8));
							objDashBoardDTO.setPartyMotherName(rowList.get(9));
							objDashBoardDTO.setPartyCountryName(rowList.get(10));
							objDashBoardDTO.setPartyStateName(rowList.get(11));
							objDashBoardDTO.setPartyDistrictName(rowList.get(12));
							objDashBoardDTO.setPartyAddress(rowList.get(13));
							objDashBoardDTO.setPartyPostalCode(rowList.get(14));
							objDashBoardDTO.setPartyPhno(rowList.get(15));
							objDashBoardDTO.setPartyMobno(rowList.get(16));
							objDashBoardDTO.setPartyEmailID(rowList.get(17));
							objDashBoardDTO.setPartyPhotoIdName(rowList.get(18));
							objDashBoardDTO.setPartyPhotoIdno(rowList.get(19));
							objDashBoardDTO.setPartyPersons(rowList.get(20));
						} else {
							objDashBoardDTO.setPartyType(rowList.get(2));
							objDashBoardDTO.setPartyTypeName(rowList.get(1));
							objDashBoardDTO.setPartyOrgName(rowList.get(21));
							objDashBoardDTO.setPartyAuthFirstName(rowList.get(3));
							objDashBoardDTO.setPartyAuthMiddleName(rowList.get(4));
							objDashBoardDTO.setPartyAuthLastName(rowList.get(5));
							objDashBoardDTO.setPartyCountryName(rowList.get(10));
							objDashBoardDTO.setPartyStateName(rowList.get(11));
							objDashBoardDTO.setPartyDistrictName(rowList.get(12));
							objDashBoardDTO.setPartyAddress(rowList.get(13));
							objDashBoardDTO.setPartyPostalCode(rowList.get(14));
							objDashBoardDTO.setPartyPhno(rowList.get(15));
							objDashBoardDTO.setPartyMobno(rowList.get(16));
							objDashBoardDTO.setPartyEmailID(rowList.get(17));
							objDashBoardDTO.setPartyPersons(rowList.get(20));
						}
					}
					detilsOfApplicant.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detilsOfApplicant;
	}

	public ArrayList getDetailsOfDocument(String TxnId) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfDocuments = new ArrayList();
		ArrayList list = objEstampingBD.getDetailsOfDocument(TxnId);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setDoc(rowList.get(1));
					detilsOfDocuments.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detilsOfDocuments;
	}

	// Added By Lavi for checking Ecode Id for search screen -----------
	public ArrayList checkEcode(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeNumber = new ArrayList();
		ArrayList list = objEstampingBD.checkEcode(ecode);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setEcode((String) rowList.get(0));
					ecodeNumber.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeNumber;
	}

	public ArrayList viewEcodeDetails(String ecode, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeDetails = new ArrayList();
		String type = objEstampingBD.getecodetype(ecode);
		/* Added by Gulrej ************** adds Refund Request no in View Screen *** Start */
		ArrayList refundRequestDtls = objEstampingBD.getRefundRequestDtls(ecode);
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		if (refundRequestDtls != null && refundRequestDtls.size() > 0) {
			ArrayList refundRequestDtlsList = new ArrayList();
			for (int i = 0; i < refundRequestDtls.size(); i++) {
				refundRequestDtlsList = (ArrayList) refundRequestDtls.get(i);
				objDashBoardDTO.setRefundRequestNo(refundRequestDtlsList.get(0).toString());
			}
		} else {
			objDashBoardDTO.setRefundRequestNo("NA");
		}
		/* Added by Gulrej ************** adds Refund Request no in View Screen **** End*/
		//---added by satbir kumar---
		String estampid = ecode.substring(3, 4);
		if (estampid.equalsIgnoreCase("2")) {
			if ("E".equalsIgnoreCase(type)) {
				ArrayList list = objEstampingBD.viewEcodeDetails(ecode);
				try {
					if (list != null && list.size() > 0) {
						ArrayList rowList = new ArrayList();
						//try{
						for (int i = 0; i < list.size(); i++) {
							//DashBoardDTO objDashBoardDTO = new DashBoardDTO();				
							rowList = (ArrayList) list.get(i);
							//objDashBoardDTO = new DashBoardDTO();
							//if(rowList.get(0).toString().length()==11){
							//}else{				
							objDashBoardDTO.setEstampId(rowList.get(0));
							objDashBoardDTO.setEstampPurpose(rowList.get(1));
							objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
							objDashBoardDTO.setEstampStatus(rowList.get(3));
							if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Active");
								else
									objDashBoardDTO.setEstampStatus("सक्रिय");
							} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Expired");
								else
									objDashBoardDTO.setEstampStatus("अवधि समाप्त");
							} else {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Deactive");
								else
									objDashBoardDTO.setEstampStatus("अक्रिय");
							}
							/*if (objDashBoardDTO.getEstampStatus().toString().equals("A"))
								objDashBoardDTO.setEstampStatus("Active");
							
							else if (objDashBoardDTO.getEstampStatus().toString().equals("E"))
								objDashBoardDTO.setEstampStatus("Expired");
							else
								objDashBoardDTO.setEstampStatus("Deactive");*/
							objDashBoardDTO.setEstampValidity(rowList.get(4));
							objDashBoardDTO.setEstampBuyerName(rowList.get(5));
							if (lang.equalsIgnoreCase("en")) {
								objDashBoardDTO.setCourtName(rowList.get(6).toString());
								objDashBoardDTO.setDistrictname(rowList.get(9).toString());
								objDashBoardDTO.setCourtDocTypeNameS(rowList.get(11).toString());
							} else {
								objDashBoardDTO.setCourtName(rowList.get(7).toString());
								objDashBoardDTO.setDistrictname(rowList.get(10).toString());
								objDashBoardDTO.setCourtDocTypeNameS(rowList.get(12).toString());
							}
							if (rowList.get(8).toString().trim().equals("D")) {
								if ("en".equalsIgnoreCase(lang)) {
									objDashBoardDTO.setCourtType("District Court");
								} else {
									objDashBoardDTO.setCourtType("जिला न्यायालय ");
								}
							} else {
								if ("en".equalsIgnoreCase(lang)) {
									objDashBoardDTO.setCourtType("Tehsil Court");
								} else {
									objDashBoardDTO.setCourtType("तहसील न्यायालय");
								}
							}
							/* if(lang.equalsIgnoreCase("en")){
								objDashBoardDTO.setDistrictname(rowList.get(9).toString());
							}else{
								objDashBoardDTO.setDistrictname(rowList.get(10).toString());
							}*/
							ecodeDetails.add(objDashBoardDTO);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//}
		} else if (estampid.equalsIgnoreCase("1")) {
			if ("E".equalsIgnoreCase(type)) {
				ArrayList list = objEstampingBD.viewEcodeDetails(ecode);
				//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
				try {
					if (list != null && list.size() > 0) {
						ArrayList rowList = new ArrayList();
						for (int i = 0; i < list.size(); i++) {
							rowList = (ArrayList) list.get(i);
							//objDashBoardDTO = new DashBoardDTO();
							//if(rowList.get(0).toString().length()==11){
							//}else{				
							objDashBoardDTO.setEstampId(rowList.get(0));
							objDashBoardDTO.setEstampPurpose(rowList.get(1));
							objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
							objDashBoardDTO.setEstampStatus(rowList.get(3));
							if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Active");
								else
									objDashBoardDTO.setEstampStatus("सक्रिय");
							} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Expired");
								else
									objDashBoardDTO.setEstampStatus("अवधि समाप्त");
							} else {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Deactive");
								else
									objDashBoardDTO.setEstampStatus("अक्रिय");
							}
							/*if (objDashBoardDTO.getEstampStatus().toString().equals("A"))
								objDashBoardDTO.setEstampStatus("Active");
							
							else if (objDashBoardDTO.getEstampStatus().toString().equals("E"))
								objDashBoardDTO.setEstampStatus("Expired");
							else
								objDashBoardDTO.setEstampStatus("Deactive");*/
							objDashBoardDTO.setEstampValidity(rowList.get(4));
							objDashBoardDTO.setEstampBuyerName(rowList.get(5));
							/*if(lang.equalsIgnoreCase("en")){
								objDashBoardDTO.setCourtName(rowList.get(6));
							}else{
								objDashBoardDTO.setCourtName(rowList.get(7));
							}   
							if(rowList.get(8).toString().trim().equals("D")){
							if("en".equalsIgnoreCase(lang)){
								objDashBoardDTO.setCourtType("District Court");
							}else{
								objDashBoardDTO.setCourtType("जिला न्यायालय ");
							}				
							}else{
							if("en".equalsIgnoreCase(lang)){
								objDashBoardDTO.setCourtType("Tehsil Court");
							}else{
								objDashBoardDTO.setCourtType("तहसील न्यायालय");
							}
							}				
							if(lang.equalsIgnoreCase("en")){
							objDashBoardDTO.setDistrictname(rowList.get(9).toString());
							}else{
							objDashBoardDTO.setDistrictname(rowList.get(10).toString());
							}*/
							ecodeDetails.add(objDashBoardDTO);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//}
		}
		///////////////////////		
		else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			ArrayList list = objEstampingBD.viewCREcodeDetails(ecode, regID);
			ArrayList rowList = new ArrayList();
			try {
				for (int i = 0; i < list.size(); i++) {
					//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setEstampId(rowList.get(0));
					String purpose = (String) rowList.get(1);
					if ("".equalsIgnoreCase(purpose) || purpose == null) {
						objDashBoardDTO.setEstampPurpose("-");
					} else {
						objDashBoardDTO.setEstampPurpose(rowList.get(1));
					}
					objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
					objDashBoardDTO.setEstampStatus(rowList.get(3));
					if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
						if (lang.equalsIgnoreCase("en"))
							objDashBoardDTO.setEstampStatus("Active");
						else
							objDashBoardDTO.setEstampStatus("सक्रिय");
					} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
						if (lang.equalsIgnoreCase("en"))
							objDashBoardDTO.setEstampStatus("Expired");
						else
							objDashBoardDTO.setEstampStatus("अवधि समाप्त");
					} else {
						if (lang.equalsIgnoreCase("en"))
							objDashBoardDTO.setEstampStatus("Deactive");
						else
							objDashBoardDTO.setEstampStatus("अक्रिय");
					}
					/*if (objDashBoardDTO.getEstampStatus().toString().equals("A"))
						objDashBoardDTO.setEstampStatus("Active");
					
					else if (objDashBoardDTO.getEstampStatus().toString().equals("E"))
						objDashBoardDTO.setEstampStatus("Expired");
					else
						objDashBoardDTO.setEstampStatus("Deactive");*/
					objDashBoardDTO.setEstampValidity(rowList.get(4));
					String Buyername = (String) rowList.get(5);
					if ("  ".equalsIgnoreCase(Buyername)) {
						objDashBoardDTO.setEstampBuyerName(rowList.get(6));
					} else {
						objDashBoardDTO.setEstampBuyerName(rowList.get(5));
					}
					ecodeDetails.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeDetails;
	}

	public ArrayList viewEcodeType(String ecodeTypeID, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeType = new ArrayList();
		ArrayList list = objEstampingBD.viewEcodeType(ecodeTypeID);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					if (lang.equalsIgnoreCase("en"))
						objDashBoardDTO.setEstampType(rowList.get(0));
					else
						objDashBoardDTO.setEstampType(rowList.get(1));
					System.out.println("------->Ecode Name" + objDashBoardDTO.getEstampType().toString());
					ecodeType.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeType;
	}

	public ArrayList viewEcodeDetailsDRS(String ecode, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeDetailsDRS = new ArrayList();
		String estampid = ecode.substring(3, 4);
		String type = objEstampingBD.getecodetype(ecode);
		/* Added by Gulrej ************** adds Refund Request no in View Screen *** Start */
		ArrayList refundRequestDtls = objEstampingBD.getRefundRequestDtls(ecode);
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		if (refundRequestDtls != null && refundRequestDtls.size() > 0) {
			ArrayList refundRequestDtlsList = new ArrayList();
			for (int i = 0; i < refundRequestDtls.size(); i++) {
				refundRequestDtlsList = (ArrayList) refundRequestDtls.get(i);
				objDashBoardDTO.setRefundRequestNo(refundRequestDtlsList.get(0).toString());
			}
		} else {
			objDashBoardDTO.setRefundRequestNo("NA");
		}
		/* Added by Gulrej ************** adds Refund Request no in View Screen **** End*///---added by satbir kumar---
		if ("E".equalsIgnoreCase(type)) {
			ArrayList list = objEstampingBD.viewEcodeDetailsDRS(ecode);
			String deactivateCount = objEstampingBD.getDeactivateCount(ecode);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();			
						rowList = (ArrayList) list.get(i);
						objDashBoardDTO.setEstampId(rowList.get(0));
						objDashBoardDTO.setEstampPurpose(rowList.get(1));
						objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
						objDashBoardDTO.setEstampStatus(rowList.get(3));
						if (deactivateCount.equalsIgnoreCase("0")) {
							if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Active");
								else
									objDashBoardDTO.setEstampStatus("सक्रिय");
							} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Expired");
								else
									objDashBoardDTO.setEstampStatus("अवधि समाप्त");
							} else {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Deactive");
								else
									objDashBoardDTO.setEstampStatus("अक्रिय");
							}
						} else {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Deactive");
							else
								objDashBoardDTO.setEstampStatus("अक्रिय");
						}
						objDashBoardDTO.setEstampValidity(rowList.get(4));
						objDashBoardDTO.setEstampBuyerName(rowList.get(5));
						objDashBoardDTO.setEstampAmount(rowList.get(6));
						if (lang.equalsIgnoreCase("en")) {
							objDashBoardDTO.setCourtName(rowList.get(7).toString());
							objDashBoardDTO.setCourtDistrict(rowList.get(9).toString());
							objDashBoardDTO.setCourtDocTypeNameS(rowList.get(12).toString());
						} else {
							objDashBoardDTO.setCourtName(rowList.get(8).toString());
							objDashBoardDTO.setCourtDistrict(rowList.get(10).toString());
							objDashBoardDTO.setCourtDocTypeNameS(rowList.get(13).toString());
						}
						if (rowList.get(11).toString().trim().equals("D")) {
							if (lang.equalsIgnoreCase("en")) {
								objDashBoardDTO.setCourtType("District Court");
							} else {
								objDashBoardDTO.setCourtType("जिला न्यायालय ");
							}
						} else {
							if (lang.equalsIgnoreCase("en")) {
								objDashBoardDTO.setCourtType("Tehsil Court");
							} else {
								objDashBoardDTO.setCourtType("तहसील न्यायालय");
							}
						}
						ecodeDetailsDRS.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			ArrayList list = objEstampingBD.viewCREcodeDetailsDRS(ecode, regID);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						objDashBoardDTO.setEstampId(rowList.get(0));
						String purpose = (String) (rowList.get(1));
						if ("".equalsIgnoreCase(purpose) || purpose == null) {
							objDashBoardDTO.setEstampPurpose("-");
						} else {
							objDashBoardDTO.setEstampPurpose(rowList.get(1));
						}
						objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
						objDashBoardDTO.setEstampStatus(rowList.get(3));
						if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Active");
							else
								objDashBoardDTO.setEstampStatus("सक्रिय");
						} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Expired");
							else
								objDashBoardDTO.setEstampStatus("अवधि समाप्त");
						} else {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Deactive");
							else
								objDashBoardDTO.setEstampStatus("अक्रिय");
						}
						/*if (objDashBoardDTO.getEstampStatus().toString().equals("A"))
							objDashBoardDTO.setEstampStatus("Active");
						
						else if (objDashBoardDTO.getEstampStatus().toString().equals("E"))
							objDashBoardDTO.setEstampStatus("Expired");
						
						else
							objDashBoardDTO.setEstampStatus("Deactive");
						*/
						objDashBoardDTO.setEstampValidity(rowList.get(4));
						String buyername = (String) (rowList.get(5));
						if ("  ".equalsIgnoreCase(buyername) || buyername == null) {
							objDashBoardDTO.setEstampBuyerName(rowList.get(7));
						} else {
							objDashBoardDTO.setEstampBuyerName(rowList.get(5));
						}
						objDashBoardDTO.setEstampAmount(rowList.get(6));
						/* if (rowList.get(7)== null){
							objDashBoardDTO.setRegInitId("-");
						}
						else
						{
						objDashBoardDTO.setRegInitId(rowList.get(7));
						}*/
						ecodeDetailsDRS.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ecodeDetailsDRS;
	}

	public ArrayList getDetailsOfApplicantDRS(String ecode, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfApplicantDRS = new ArrayList();
		String txnid = "";
		String type = objEstampingBD.getecodetype(ecode); //---added by satbir kumar---
		//if("E".equalsIgnoreCase(type)||"C".equalsIgnoreCase(type)||"R".equalsIgnoreCase(type))
		if ("E".equalsIgnoreCase(type)) {
			ArrayList list = objEstampingBD.getDetailsOfApplicantDRS(ecode, language);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						objDashBoardDTO.setApplicant_ind(rowList.get(21));
						if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("Y")) {
							objDashBoardDTO.setAppType(rowList.get(2));
							if (objDashBoardDTO.getAppType().toString().equals("2"))
							//}else{
							{
								ArrayList list4 = objEstampingBD.getindvDetailsOfApplicantDRSNew(ecode, language);
								if (list4 != null && list4.size() > 0) {
									for (int g = 0; g < list4.size(); g++) {
										ArrayList rowList4 = new ArrayList();
										rowList4 = (ArrayList) list4.get(i);
										objDashBoardDTO.setAppType(rowList4.get(2));
										objDashBoardDTO.setAppTypeName(rowList4.get(1));
										objDashBoardDTO.setAppFirsName(rowList4.get(3));
										objDashBoardDTO.setAppMiddleName(rowList4.get(4));
										objDashBoardDTO.setAppLastName(rowList4.get(5));
										objDashBoardDTO.setAppGender(rowList4.get(6));
										String apgender = rowList4.get(6).toString();
										if (apgender.equalsIgnoreCase("M")) {
											if (language.equalsIgnoreCase("en"))
												objDashBoardDTO.setAppGenderDisp("Male");
											else
												objDashBoardDTO.setAppGenderDisp("पुस्र्ष");
										} else {
											if (language.equalsIgnoreCase("en"))
												objDashBoardDTO.setAppGenderDisp("Female");
											else
												objDashBoardDTO.setAppGenderDisp("महिला");
										}
										objDashBoardDTO.setAppAge(rowList4.get(7));
										objDashBoardDTO.setAppFatherName(rowList4.get(8));
										objDashBoardDTO.setAppMotherName(rowList4.get(9));
										objDashBoardDTO.setAppCountryName(rowList4.get(10));
										objDashBoardDTO.setAppStateName(rowList4.get(11));
										objDashBoardDTO.setAppDistrictName(rowList4.get(12));
										objDashBoardDTO.setAppAddress(rowList4.get(13));
										objDashBoardDTO.setAppPostalCode(rowList4.get(14));
										objDashBoardDTO.setAppPhno(rowList4.get(15));
										objDashBoardDTO.setAppMobno(rowList4.get(16));
										objDashBoardDTO.setAppEmailID(rowList4.get(17));
										objDashBoardDTO.setAppPhotoIdName(rowList4.get(18));
										objDashBoardDTO.setAppPhotoIdno(rowList4.get(19));
										objDashBoardDTO.setAppPersons(rowList4.get(20));
									}
								}
							} else {
								//ArrayList list6 = objEstampingBD.getorgDetailsOfApplicantDRS(ecode);
								objDashBoardDTO.setAppType(rowList.get(2));
								objDashBoardDTO.setAppTypeName(rowList.get(1));
								objDashBoardDTO.setAppOrgName(rowList.get(20));
								objDashBoardDTO.setAppAuthFirstName(rowList.get(3));
								objDashBoardDTO.setAppAuthMiddleName(rowList.get(4));
								objDashBoardDTO.setAppAuthLastName(rowList.get(5));
								objDashBoardDTO.setAppCountryName(rowList.get(10));
								objDashBoardDTO.setAppStateName(rowList.get(11));
								objDashBoardDTO.setAppDistrictName(rowList.get(12));
								objDashBoardDTO.setAppAddress(rowList.get(13));
								objDashBoardDTO.setAppPostalCode(rowList.get(14));
								objDashBoardDTO.setAppPhno(rowList.get(15));
								objDashBoardDTO.setAppMobno(rowList.get(16));
								objDashBoardDTO.setAppEmailID(rowList.get(17));
								objDashBoardDTO.setAppPersons(rowList.get(19));
							}
						}
						ArrayList list3 = objEstampingBD.getNDetailsOfApplicantDRS(ecode, language);
						if (list3 != null && list3.size() > 0) {
							ArrayList rowList8 = new ArrayList();
							for (int k = 0; k < list3.size(); k++) {
								rowList8 = (ArrayList) list3.get(k);
								txnid = rowList8.get(0).toString();
								objDashBoardDTO.setApplicant_ind(rowList8.get(21));
								if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("N")) {
									objDashBoardDTO.setPartyType(rowList8.get(2));
									if (objDashBoardDTO.getPartyType().toString().equals("2"))
									//}else{
									{
										ArrayList list4 = objEstampingBD.getindvDetailsOfApplicantDRS(rowList8.get(0).toString(), (language));
										if (list4 != null && list4.size() > 0) {
											for (int g = 0; g < list4.size(); g++) {
												ArrayList rowList4 = new ArrayList();
												rowList4 = (ArrayList) list4.get(g);
												objDashBoardDTO.setPartyType(rowList4.get(2));
												objDashBoardDTO.setPartyTypeName(rowList4.get(1));
												objDashBoardDTO.setPartyFirstName(rowList4.get(3));
												objDashBoardDTO.setPartyMiddleName(rowList4.get(4));
												objDashBoardDTO.setPartyLastName(rowList4.get(5));
												objDashBoardDTO.setPartyGender(rowList4.get(6));
												String prtygender = rowList4.get(6).toString();
												if (prtygender.equalsIgnoreCase("M")) {
													if (language.equalsIgnoreCase("en")) {
														objDashBoardDTO.setPartyGenderDisp("Male");
													} else {
														objDashBoardDTO.setPartyGenderDisp("पुस्र्ष");
													}
												} else {
													if (language.equalsIgnoreCase("en"))
														objDashBoardDTO.setPartyGenderDisp("Female");
													else
														objDashBoardDTO.setPartyGenderDisp("महिला");
												}
												objDashBoardDTO.setPartyAge(rowList4.get(7));
												objDashBoardDTO.setPartyFatherName(rowList4.get(8));
												objDashBoardDTO.setPartyMotherName(rowList4.get(9));
												objDashBoardDTO.setPartyCountryName(rowList4.get(10));
												objDashBoardDTO.setPartyStateName(rowList4.get(11));
												objDashBoardDTO.setPartyDistrictName(rowList4.get(12));
												objDashBoardDTO.setPartyAddress(rowList4.get(13));
												objDashBoardDTO.setPartyPostalCode(rowList4.get(14));
												objDashBoardDTO.setPartyPhno(rowList4.get(15));
												objDashBoardDTO.setPartyMobno(rowList4.get(16));
												objDashBoardDTO.setPartyEmailID(rowList4.get(17));
												objDashBoardDTO.setPartyPhotoIdName(rowList4.get(18));
												objDashBoardDTO.setPartyPhotoIdno(rowList4.get(19));
												objDashBoardDTO.setPartyPersons(rowList4.get(20));
											}
										}
									} else {
										objDashBoardDTO.setPartyType(rowList8.get(2));
										objDashBoardDTO.setPartyTypeName(rowList8.get(1));
										objDashBoardDTO.setPartyOrgName(rowList8.get(20));
										objDashBoardDTO.setPartyAuthFirstName(rowList8.get(3));
										objDashBoardDTO.setPartyAuthMiddleName(rowList8.get(4));
										objDashBoardDTO.setPartyAuthLastName(rowList8.get(5));
										objDashBoardDTO.setPartyCountryName(rowList8.get(10));
										objDashBoardDTO.setPartyStateName(rowList8.get(11));
										objDashBoardDTO.setPartyDistrictName(rowList8.get(12));
										objDashBoardDTO.setPartyAddress(rowList8.get(13));
										objDashBoardDTO.setPartyPostalCode(rowList8.get(14));
										objDashBoardDTO.setPartyPhno(rowList8.get(15));
										objDashBoardDTO.setPartyMobno(rowList8.get(16));
										objDashBoardDTO.setPartyEmailID(rowList8.get(17));
										objDashBoardDTO.setPartyPersons(rowList8.get(19));
									}
									detilsOfApplicantDRS.add(objDashBoardDTO);
								}
							}
						}
						//added
						ArrayList list4 = objEstampingBD.getindvDetailsOfApplicantDRSAdpotion(txnid, (language));
						if (list4 != null && list4.size() > 0) {
							for (int g = 0; g < list4.size(); g++) {
								DashBoardDTO objDashBoardDTO1 = new DashBoardDTO();
								objDashBoardDTO1.setAdoptionFlag("Y");
								ArrayList rowList4 = new ArrayList();
								rowList4 = (ArrayList) list4.get(g);
								objDashBoardDTO1.setPartyType(rowList4.get(2));
								objDashBoardDTO1.setPartyTypeName(rowList4.get(1));
								objDashBoardDTO1.setPartyFirstName(rowList4.get(3));
								objDashBoardDTO1.setPartyMiddleName(rowList4.get(4));
								objDashBoardDTO1.setPartyLastName(rowList4.get(5));
								objDashBoardDTO1.setPartyGender(rowList4.get(6));
								String prtygender = rowList4.get(6).toString();
								if (prtygender.equalsIgnoreCase("M")) {
									if (language.equalsIgnoreCase("en")) {
										objDashBoardDTO1.setPartyGenderDisp("Male");
									} else {
										objDashBoardDTO1.setPartyGenderDisp("पुस्र्ष");
									}
								} else {
									if (language.equalsIgnoreCase("en"))
										objDashBoardDTO1.setPartyGenderDisp("Female");
									else
										objDashBoardDTO1.setPartyGenderDisp("महिला");
								}
								objDashBoardDTO1.setPartyAge(rowList4.get(7));
								objDashBoardDTO1.setPartyFatherName(rowList4.get(8));
								objDashBoardDTO1.setPartyMotherName(rowList4.get(9));
								objDashBoardDTO1.setPartyCountryName(rowList4.get(10));
								objDashBoardDTO1.setPartyStateName(rowList4.get(11));
								objDashBoardDTO1.setPartyDistrictName(rowList4.get(12));
								objDashBoardDTO1.setPartyAddress(rowList4.get(13));
								objDashBoardDTO1.setPartyPostalCode(rowList4.get(14));
								objDashBoardDTO1.setPartyPhno(rowList4.get(15));
								objDashBoardDTO1.setPartyMobno(rowList4.get(16));
								objDashBoardDTO1.setPartyEmailID(rowList4.get(17));
								objDashBoardDTO1.setPartyPhotoIdName(rowList4.get(18));
								objDashBoardDTO1.setPartyPhotoIdno(rowList4.get(19));
								objDashBoardDTO1.setPartyPersons(rowList4.get(20));
								detilsOfApplicantDRS.add(objDashBoardDTO1);
							}
						}
						//added
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			//ArrayList list = objEstampingBD.getCRtype(regID);    //----giving applicant type and apptype----
			ArrayList list = objEstampingBD.getCRYDetailsOfApplicantDRS(regID, language);
			//ArrayList list=objEstampingBD.getCRDetailsOfApplicantDRS(ecode,regID);
			DashBoardDTO objDashBoardDTO = new DashBoardDTO();
			if (list != null && list.size() > 0) {
				ArrayList rowList1 = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					rowList1 = (ArrayList) list.get(i);
					objDashBoardDTO.setApplicant_ind(rowList1.get(22));
					if (objDashBoardDTO.getApplicant_ind().toString().equalsIgnoreCase("Y")) {
						objDashBoardDTO.setAppType(rowList1.get(2));
						String apptype = (rowList1.get(2).toString());
						if (objDashBoardDTO.getAppType().toString().equals("2")) {
							ArrayList detailsNindv = objEstampingBD.getCRYindvDetailsOfApplicantDRS(regID, language);
							if (detailsNindv != null && detailsNindv.size() > 0) {
								ArrayList rowList4 = new ArrayList();
								for (int s = 0; s < detailsNindv.size(); s++) {
									rowList4 = (ArrayList) detailsNindv.get(s);
									objDashBoardDTO.setAppType(rowList4.get(2));
									objDashBoardDTO.setAppTypeName(rowList4.get(1));
									objDashBoardDTO.setAppFirsName(rowList4.get(3));
									String middlename = (String) ((rowList4.get(4)));
									if ("".equalsIgnoreCase(middlename) || middlename == null) {
										objDashBoardDTO.setAppMiddleName("");
									} else {
										objDashBoardDTO.setAppMiddleName(rowList4.get(4));
									}
									objDashBoardDTO.setAppLastName(rowList4.get(5));
									objDashBoardDTO.setAppGender(rowList4.get(6));
									String apgender = rowList4.get(6).toString();
									if (apgender.equalsIgnoreCase("M")) {
										if (language.equalsIgnoreCase("en"))
											objDashBoardDTO.setAppGenderDisp("Male");
										else
											objDashBoardDTO.setAppGenderDisp("पुस्र्ष");
									} else {
										if (language.equalsIgnoreCase("en"))
											objDashBoardDTO.setAppGenderDisp("Female");
										else
											objDashBoardDTO.setAppGenderDisp("महिला");
									}
									objDashBoardDTO.setAppAge(rowList4.get(7));
									objDashBoardDTO.setAppFatherName(rowList4.get(8));
									String mothername = (String) ((rowList4.get(9)));
									if ("".equalsIgnoreCase(mothername) || mothername == null) {
										objDashBoardDTO.setAppMotherName("");
									} else {
										objDashBoardDTO.setAppMotherName(rowList4.get(9));
									}
									objDashBoardDTO.setAppCountryName(rowList4.get(10));
									objDashBoardDTO.setAppStateName(rowList4.get(11));
									objDashBoardDTO.setAppDistrictName(rowList4.get(12));
									objDashBoardDTO.setAppAddress(rowList4.get(13));
									String postalcode = (String) ((rowList4.get(14)));
									if ("".equalsIgnoreCase(postalcode) || postalcode == null) {
										objDashBoardDTO.setAppPostalCode("");
									} else {
										objDashBoardDTO.setAppPostalCode(rowList4.get(14));
									}
									String phonenumber = (String) ((rowList4.get(14)));
									if ("".equalsIgnoreCase(phonenumber) || phonenumber == null) {
										objDashBoardDTO.setAppPhno("");
									} else {
										objDashBoardDTO.setAppPhno(rowList4.get(15));
									}
									objDashBoardDTO.setAppMobno(rowList4.get(16));
									objDashBoardDTO.setAppEmailID(rowList4.get(17));
									objDashBoardDTO.setAppPhotoIdName(rowList4.get(18));
									objDashBoardDTO.setAppPhotoIdno(rowList4.get(19));
									objDashBoardDTO.setAppPersons(rowList4.get(20));
								}
							}
						} else {
							objDashBoardDTO.setAppType(rowList1.get(2));
							objDashBoardDTO.setAppTypeName(rowList1.get(1));
							objDashBoardDTO.setAppOrgName(rowList1.get(20));
							objDashBoardDTO.setAppAuthFirstName(rowList1.get(21));
							//objDashBoardDTO.setAppAuthMiddleName(rowList.get(4));
							//objDashBoardDTO.setAppAuthLastName(rowList.get(5));
							objDashBoardDTO.setAppCountryName(rowList1.get(10));
							objDashBoardDTO.setAppStateName(rowList1.get(11));
							objDashBoardDTO.setAppDistrictName(rowList1.get(12));
							objDashBoardDTO.setAppAddress(rowList1.get(13));
							objDashBoardDTO.setAppPostalCode(rowList1.get(14));
							String phonenmber = (String) (rowList1.get(15));
							if ("".equalsIgnoreCase(phonenmber) || phonenmber == null) {
								objDashBoardDTO.setAppPhno("");
							} else {
								objDashBoardDTO.setAppPhno(rowList1.get(15));
							}
							objDashBoardDTO.setAppMobno(rowList1.get(16));
							objDashBoardDTO.setAppEmailID(rowList1.get(17));
							objDashBoardDTO.setAppPersons(rowList1.get(19));
						}
					}
				}
			}
			ArrayList list1 = objEstampingBD.getCRNDetailsOfApplicantDRS(regID, language);
			if (list1 != null && list1.size() > 0) {
				ArrayList rowList2 = new ArrayList();
				for (int j = 0; j < list1.size(); j++) {
					rowList2 = (ArrayList) list1.get(j);
					objDashBoardDTO.setApplicant_ind(rowList2.get(22));
					String app = (String) objDashBoardDTO.getApplicant_ind();
					System.out.println((rowList2.get(22)));
					if (app.equalsIgnoreCase("N")) {
						objDashBoardDTO.setPartyType(rowList2.get(2));
						if (objDashBoardDTO.getPartyType().toString().equals("2"))
						//}else{
						{
							ArrayList detailsNindv = objEstampingBD.getCRNindvDetailsOfApplicantDRS(regID, language);
							if (detailsNindv != null && detailsNindv.size() > 0) {
								ArrayList rowList4 = new ArrayList();
								for (int s = 0; s < detailsNindv.size(); s++) {
									rowList4 = (ArrayList) detailsNindv.get(s);
									objDashBoardDTO.setPartyType(rowList4.get(2));
									objDashBoardDTO.setPartyTypeName(rowList4.get(1));
									objDashBoardDTO.setPartyFirstName(rowList4.get(3));
									String middlename = (String) (rowList4.get(4));
									if ("".equalsIgnoreCase(middlename) || middlename == null) {
										objDashBoardDTO.setPartyMiddleName("");
									}
									{
										objDashBoardDTO.setPartyMiddleName(rowList4.get(4));
									}
									objDashBoardDTO.setPartyLastName(rowList4.get(5));
									objDashBoardDTO.setPartyGender(rowList4.get(6));
									String prtygender = rowList4.get(6).toString();
									if (prtygender.equalsIgnoreCase("M")) {
										if (language.equalsIgnoreCase("en"))
											objDashBoardDTO.setPartyGenderDisp("Male");
										else
											objDashBoardDTO.setPartyGenderDisp("पुस्र्ष");
									} else {
										if (language.equalsIgnoreCase("en"))
											objDashBoardDTO.setPartyGenderDisp("Female");
										else
											objDashBoardDTO.setPartyGenderDisp("महिला");
									}
									objDashBoardDTO.setPartyAge(rowList4.get(7));
									objDashBoardDTO.setPartyFatherName(rowList4.get(8));
									String mothername = (String) (rowList4.get(9));
									if ("".equalsIgnoreCase(mothername) || mothername == null) {
										objDashBoardDTO.setPartyMotherName("");
									} else {
										objDashBoardDTO.setPartyMotherName(rowList4.get(9));
									}
									objDashBoardDTO.setPartyCountryName(rowList4.get(10));
									objDashBoardDTO.setPartyStateName(rowList4.get(11));
									objDashBoardDTO.setPartyDistrictName(rowList4.get(12));
									objDashBoardDTO.setPartyAddress(rowList4.get(13));
									String postalcode = (String) (rowList4.get(14));
									if ("".equalsIgnoreCase(postalcode) || postalcode == null) {
										objDashBoardDTO.setPartyPostalCode("");
									} else {
										objDashBoardDTO.setPartyPostalCode(rowList4.get(14));
									}
									String phonenumber = (String) (rowList4.get(15));
									if ("".equalsIgnoreCase(phonenumber) || phonenumber == null) {
										objDashBoardDTO.setPartyPhno("");
									} else {
										objDashBoardDTO.setPartyPhno(rowList4.get(15));
									}
									objDashBoardDTO.setPartyMobno(rowList4.get(16));
									objDashBoardDTO.setPartyEmailID(rowList4.get(17));
									objDashBoardDTO.setPartyPhotoIdName(rowList4.get(18));
									objDashBoardDTO.setPartyPhotoIdno(rowList4.get(19));
									objDashBoardDTO.setPartyPersons(rowList4.get(20));
								}
							}
						} else {
							ArrayList detailsNorg = objEstampingBD.getCRNorgDetailsOfApplicantDRS(regID, language);
							if (detailsNorg != null && detailsNorg.size() > 0) {
								ArrayList rowList3 = new ArrayList();
								for (int s = 0; s < detailsNorg.size(); s++) {
									rowList3 = (ArrayList) detailsNorg.get(s);
									objDashBoardDTO.setPartyType(rowList3.get(2));
									objDashBoardDTO.setPartyTypeName(rowList3.get(1));
									objDashBoardDTO.setPartyOrgName(rowList3.get(20));
									objDashBoardDTO.setPartyAuthFirstName(rowList3.get(21));
									//objDashBoardDTO.setPartyAuthMiddleName(rowList2.get(4));
									//objDashBoardDTO.setPartyAuthLastName(rowList2.get(5));
									objDashBoardDTO.setPartyCountryName(rowList3.get(10));
									objDashBoardDTO.setPartyStateName(rowList3.get(11));
									objDashBoardDTO.setPartyDistrictName(rowList3.get(12));
									objDashBoardDTO.setPartyAddress(rowList3.get(13));
									objDashBoardDTO.setPartyPostalCode(rowList3.get(14));
									String phonenumber = (String) (rowList3.get(15));
									if ("".equalsIgnoreCase(phonenumber) || phonenumber == null) {
										objDashBoardDTO.setPartyPhno("");
									} else {
										objDashBoardDTO.setPartyPhno(rowList3.get(15));
									}
									objDashBoardDTO.setPartyMobno(rowList3.get(16));
									objDashBoardDTO.setPartyEmailID(rowList3.get(17));
									objDashBoardDTO.setPartyPersons(rowList3.get(19));
								}
							}
						}
					}
				}
			}
			detilsOfApplicantDRS.add(objDashBoardDTO);
			//added
			ArrayList list4 = objEstampingBD.getindvDetailsOfApplicantDRSAdpotion(txnid, (language));
			if (list4 != null && list4.size() > 0) {
				for (int g = 0; g < list4.size(); g++) {
					DashBoardDTO objDashBoardDTO1 = new DashBoardDTO();
					objDashBoardDTO1.setAdoptionFlag("Y");
					ArrayList rowList4 = new ArrayList();
					rowList4 = (ArrayList) list4.get(g);
					objDashBoardDTO1.setPartyType(rowList4.get(2));
					objDashBoardDTO1.setPartyTypeName(rowList4.get(1));
					objDashBoardDTO1.setPartyFirstName(rowList4.get(3));
					objDashBoardDTO1.setPartyMiddleName(rowList4.get(4));
					objDashBoardDTO1.setPartyLastName(rowList4.get(5));
					objDashBoardDTO1.setPartyGender(rowList4.get(6));
					String prtygender = rowList4.get(6).toString();
					if (prtygender.equalsIgnoreCase("M")) {
						if (language.equalsIgnoreCase("en")) {
							objDashBoardDTO1.setPartyGenderDisp("Male");
						} else {
							objDashBoardDTO1.setPartyGenderDisp("पुस्र्ष");
						}
					} else {
						if (language.equalsIgnoreCase("en"))
							objDashBoardDTO1.setPartyGenderDisp("Female");
						else
							objDashBoardDTO1.setPartyGenderDisp("महिला");
					}
					objDashBoardDTO1.setPartyAge(rowList4.get(7));
					objDashBoardDTO1.setPartyFatherName(rowList4.get(8));
					objDashBoardDTO1.setPartyMotherName(rowList4.get(9));
					objDashBoardDTO1.setPartyCountryName(rowList4.get(10));
					objDashBoardDTO1.setPartyStateName(rowList4.get(11));
					objDashBoardDTO1.setPartyDistrictName(rowList4.get(12));
					objDashBoardDTO1.setPartyAddress(rowList4.get(13));
					objDashBoardDTO1.setPartyPostalCode(rowList4.get(14));
					objDashBoardDTO1.setPartyPhno(rowList4.get(15));
					objDashBoardDTO1.setPartyMobno(rowList4.get(16));
					objDashBoardDTO1.setPartyEmailID(rowList4.get(17));
					objDashBoardDTO1.setPartyPhotoIdName(rowList4.get(18));
					objDashBoardDTO1.setPartyPhotoIdno(rowList4.get(19));
					objDashBoardDTO1.setPartyPersons(rowList4.get(20));
					detilsOfApplicantDRS.add(objDashBoardDTO1);
				}
			}
			//added
		}
		return detilsOfApplicantDRS;
	}

	public ArrayList deactEcodeDetails(String ecode, DashBoardDTO dto, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactEcodeDetailsDR = new ArrayList();
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		String amount = null;
		try {
			ArrayList list2 = objEstampingBD.estampAmount(ecode);
			if (list2 != null && list2.size() > 0) {
				ArrayList rowList2;
				for (int k = 0; k < list2.size(); k++) {
					rowList2 = (ArrayList) list2.get(k);
					amount = rowList2.get(0).toString();
				}
			}
			String type = objEstampingBD.getecodetype(ecode); //---added by satbir kumar---
			if ("E".equalsIgnoreCase(type)) {
				if (amount.equalsIgnoreCase("0")) {
					ArrayList list1 = objEstampingBD.deactEcodeDetailsZero(ecode);
					if (list1 != null && list1.size() > 0) {
						ArrayList rowList1 = new ArrayList();
						for (int j = 0; j < list1.size(); j++) {
							rowList1 = (ArrayList) list1.get(j);
							//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
							dto.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampPurchaseDate(rowList1.get(1));
							objDashBoardDTO.setEstampAmount(rowList1.get(2));
							objDashBoardDTO.setEstampPaymentMode("-");
							objDashBoardDTO.setIsjud(0);
							objDashBoardDTO.setDeedName(rowList1.get(3));
							deactEcodeDetailsDR.add(objDashBoardDTO);
						}
					}
				} else {
					ArrayList list = objEstampingBD.deactEcodeDetails(ecode);
					if (list != null && list.size() > 0) {
						ArrayList rowList;
						for (int i = 0; i < list.size(); i++) {
							//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
							rowList = (ArrayList) list.get(i);
							//objDashBoardDTO = new DashBoardDTO();
							//if(rowList.get(0).toString().length()==11){
							//}else{
							dto.setEstampId(rowList.get(0));
							objDashBoardDTO.setEstampId(rowList.get(0));
							objDashBoardDTO.setEstampPurchaseDate(rowList.get(1));
							objDashBoardDTO.setEstampAmount(rowList.get(2));
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampPaymentMode(rowList.get(3));
							else
								objDashBoardDTO.setEstampPaymentMode(rowList.get(5));
							objDashBoardDTO.setIsjud(1);
							objDashBoardDTO.setDocType(rowList.get(4));
							deactEcodeDetailsDR.add(objDashBoardDTO);
						}
					}
				}
			} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
				if (amount.equalsIgnoreCase("0")) {
					String regID = objEstampingBD.getregId(ecode);
					ArrayList list1 = objEstampingBD.deactCREcodeDetailsZero(ecode, regID);
					if (list1 != null && list1.size() > 0) {
						ArrayList rowList1 = new ArrayList();
						for (int j = 0; j < list1.size(); j++) {
							rowList1 = (ArrayList) list1.get(j);
							//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
							dto.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampPurchaseDate(rowList1.get(1));
							objDashBoardDTO.setEstampAmount(rowList1.get(2));
							objDashBoardDTO.setEstampPaymentMode("-");
							objDashBoardDTO.setIsjud(0);
							objDashBoardDTO.setDeedName(rowList1.get(4));
							deactEcodeDetailsDR.add(objDashBoardDTO);
						}
					}
				} else {
					String regID = objEstampingBD.getregId(ecode);
					ArrayList list1 = objEstampingBD.deactCREcodeDetailsZero(ecode, regID);
					if (list1 != null && list1.size() > 0) {
						ArrayList rowList1 = new ArrayList();
						for (int i = 0; i < list1.size(); i++) {
							//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
							rowList1 = (ArrayList) list1.get(i);
							//objDashBoardDTO = new DashBoardDTO();
							//if(rowList.get(0).toString().length()==11){
							//}else{
							dto.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampId(rowList1.get(0));
							objDashBoardDTO.setEstampPurchaseDate(rowList1.get(1));
							objDashBoardDTO.setEstampAmount(rowList1.get(2));
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampPaymentMode(rowList1.get(3));
							else
								objDashBoardDTO.setEstampPaymentMode(rowList1.get(5));
							objDashBoardDTO.setIsjud(1);
							objDashBoardDTO.setDocType(rowList1.get(4));
							deactEcodeDetailsDR.add(objDashBoardDTO);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deactEcodeDetailsDR;
	}

	public ArrayList deactDocTypeJud(String ecode, DashBoardDTO dto, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactEcodeDetailsJudDR = new ArrayList();
		String amount = null;
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		try {
			ArrayList list2 = objEstampingBD.estampAmount(ecode);
			if (list2 != null && list2.size() > 0) {
				ArrayList rowList2;
				for (int k = 0; k < list2.size(); k++) {
					rowList2 = (ArrayList) list2.get(k);
					amount = rowList2.get(0).toString();
				}
			}
			if (amount.equalsIgnoreCase("0")) {
				ArrayList list1 = objEstampingBD.deactEcodeDetailsJudZero(ecode);
				if (list1 != null && list1.size() > 0) {
					ArrayList rowList1;
					for (int j = 0; j < list1.size(); j++) {
						rowList1 = (ArrayList) list1.get(j);
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						dto.setEstampId(rowList1.get(0));
						objDashBoardDTO.setEstampId(rowList1.get(0));
						objDashBoardDTO.setEstampPurchaseDate(rowList1.get(1));
						objDashBoardDTO.setEstampAmount(rowList1.get(2));
						objDashBoardDTO.setEstampPaymentMode("-");
						objDashBoardDTO.setIsjud(1);
						if (lang.equalsIgnoreCase("en")) {
							objDashBoardDTO.setDocType(rowList1.get(3));
							objDashBoardDTO.setDistrictname(rowList1.get(8).toString());
						} else {
							objDashBoardDTO.setDocType(rowList1.get(4));
							objDashBoardDTO.setDistrictname(rowList1.get(9).toString());
						}
						deactEcodeDetailsJudDR.add(objDashBoardDTO);
					}
				}
			} else {
				ArrayList list = objEstampingBD.deactDocTypeJud(ecode);
				if (list != null && list.size() > 0) {
					ArrayList rowList;
					for (int i = 0; i < list.size(); i++) {
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						dto.setEstampId(rowList.get(0));
						objDashBoardDTO.setEstampId(rowList.get(0));
						objDashBoardDTO.setEstampPurchaseDate(rowList.get(1));
						objDashBoardDTO.setEstampAmount(rowList.get(2));
						objDashBoardDTO.setIsjud(1);
						if (lang.equalsIgnoreCase("en")) {
							objDashBoardDTO.setDocType(rowList.get(4));
							objDashBoardDTO.setEstampPaymentMode(rowList.get(3));
							objDashBoardDTO.setCourtName(rowList.get(7).toString());
							objDashBoardDTO.setCourtDistrict(rowList.get(9).toString());
						} else {
							objDashBoardDTO.setDocType(rowList.get(5));
							objDashBoardDTO.setEstampPaymentMode(rowList.get(6));
							objDashBoardDTO.setCourtName(rowList.get(8).toString());
							objDashBoardDTO.setCourtDistrict(rowList.get(10).toString());
						}
						if (rowList.get(11).toString().trim().equals("D")) {
							if ("en".equalsIgnoreCase(lang)) {
								objDashBoardDTO.setCourtType("District Court");
							} else {
								objDashBoardDTO.setCourtType("जिला न्यायालय");
							}
						} else {
							if ("en".equalsIgnoreCase(lang)) {
								objDashBoardDTO.setCourtType("Tehsil Court");
							} else {
								objDashBoardDTO.setCourtType("तहसील न्यायालय");
							}
						}
						deactEcodeDetailsJudDR.add(objDashBoardDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deactEcodeDetailsJudDR;
	}

	public ArrayList deactEcodeDate() throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeDeactDate = new ArrayList();
		ArrayList list = objEstampingBD.deactEcodeDate();
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setEstampDeactDate(rowList.get(0).toString());
					System.out.println("------->Ecode Date" + objDashBoardDTO.getEstampDeactDate().toString());
					ecodeDeactDate.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeDeactDate;
	}

	public ArrayList deactEcodeAppDetails(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactEcodeAppDetailsDR = new ArrayList();
		ArrayList list = new ArrayList();
		String type = objEstampingBD.getecodetype(ecode);
		if (("E").equalsIgnoreCase(type)) {
			list = objEstampingBD.deactEcodeAppDetails(ecode);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						objDashBoardDTO.setEstampBuyerName(rowList.get(0));
						deactEcodeAppDetailsDR.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			list = objEstampingBD.deactCREcodeAppdetails(ecode, regID);
			//list=objEstampingBD.deactEcodeAppDetails(ecode);	
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						String name = (String) rowList.get(0);
						if ("  ".equalsIgnoreCase(name)) {
							objDashBoardDTO.setEstampBuyerName(rowList.get(1));
						} else {
							objDashBoardDTO.setEstampBuyerName(rowList.get(0));
						}
						deactEcodeAppDetailsDR.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return deactEcodeAppDetailsDR;
	}

	public ArrayList deactEcodePartyDetails(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactEcodePartyDetailsDR = new ArrayList();
		ArrayList list = new ArrayList();
		String type = objEstampingBD.getecodetype(ecode);
		if (("E").equalsIgnoreCase(type)) {
			list = objEstampingBD.deactEcodePartyDetails(ecode);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						objDashBoardDTO.setEstampParty2Name(rowList.get(0));
						deactEcodePartyDetailsDR.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			list = objEstampingBD.deactCREcodepartydetails(ecode, regID);
			//list=objEstampingBD.deactEcodeAppDetails(ecode);	
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						String name = (String) rowList.get(0);
						if ("  ".equalsIgnoreCase(name)) {
							objDashBoardDTO.setEstampParty2Name(rowList.get(1));
						} else {
							objDashBoardDTO.setEstampParty2Name(rowList.get(0));
						}
						deactEcodePartyDetailsDR.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return deactEcodePartyDetailsDR;
	}

	public ArrayList deactDRName(String userId, DashBoardDTO dto) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactEcodeDrName = new ArrayList();
		ArrayList list = objEstampingBD.deactDRName(userId);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					dto.setDrName(rowList.get(0));
					objDashBoardDTO.setDrName(rowList.get(0));
					deactEcodeDrName.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deactEcodeDrName;
	}

	public boolean insertDeactDetails(DutyCalculationForm eform, DashBoardDTO dto, String userId) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		boolean deactDetailsInsert = objEstampingBD.insertDeactDetails(eform, dto, userId);
		return deactDetailsInsert;
	}

	public ArrayList deactRequestId(DashBoardDTO dto) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList deactRequestId = new ArrayList();
		ArrayList list = objEstampingBD.deactRequestId(dto);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setEstampDeactID(rowList.get(0));
					deactRequestId.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deactRequestId;
	}

	public ArrayList checkEcodeDeact(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeNumber = new ArrayList();
		ArrayList list = objEstampingBD.checkEcodeDeact(ecode);
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setCheckDeact((String) rowList.get(0));
					ecodeNumber.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeNumber;
	}

	public ArrayList checkEcodeExp(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeNumberExp = new ArrayList();
		ArrayList list = objEstampingBD.checkEcodeExp(ecode);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setCheckExpiry((String) rowList.get(0));
					ecodeNumberExp.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeNumberExp;
	}

	public ArrayList checkEcodeDeactv(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeNumberExp = new ArrayList();
		ArrayList list = objEstampingBD.checkEcodeDeactv(ecode);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setCheckDeactv((String) rowList.get(0));
					ecodeNumberExp.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeNumberExp;
	}

	public ArrayList checkEcodeConsumed(String ecode) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeNumberConsumd = new ArrayList();
		ArrayList list = objEstampingBD.checkEcodeConsumed(ecode);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objDashBoardDTO.setCheckDeactv((String) rowList.get(0));
					ecodeNumberConsumd.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeNumberConsumd;
	}

	// ADDED BY LAVI FOR ESTAMP JUDICIAL
	public ArrayList getPendingApps(String userId, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list = objEstampingBD.getPendingApps(userId, lang);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					EstampDetailsDTO objEstampDetailsDTO = new EstampDetailsDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					objEstampDetailsDTO.setTransactionID(rowList.get(0).toString());
					if (rowList.get(2) == null)
						objEstampDetailsDTO.setHdntransactionID(rowList.get(0) + "~" + rowList.get(6));
					else
						objEstampDetailsDTO.setHdntransactionID(rowList.get(0) + "~" + rowList.get(3));
					//}else{
					objEstampDetailsDTO.setDocName(rowList.get(1).toString());
					//objDashBoardDTO.setPaidAmount(rowList.get(2));
					System.out.println("*******" + (String) rowList.get(2));
					if ((rowList.get(2)) == null) {
						System.out.println("*******");
						objEstampDetailsDTO.setPaidAmount("-");
					} else
						objEstampDetailsDTO.setPaidAmount(rowList.get(2).toString());
					if (rowList.get(2) == null) {
						objEstampDetailsDTO.setBalanceAmount(rowList.get(6).toString());
					} else
						objEstampDetailsDTO.setBalanceAmount(rowList.get(3).toString());
					//objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
					if ((rowList.get(4)) == null)
						objEstampDetailsDTO.setLastTransactionDate(rowList.get(5).toString());
					else
						objEstampDetailsDTO.setLastTransactionDate(rowList.get(4).toString());
					pendingAppListFinal.add(objEstampDetailsDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pendingAppListFinal;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getCountry(String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList country = new ArrayList();
		try {
			country = bd.getCountry(language);
		} catch (Exception e) {}
		return country;
	}

	public ArrayList getCourtList(String courtType, String DistrictCourtId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList courtList = new ArrayList();
		try {
			courtList = bd.getCourtList(courtType, DistrictCourtId, language);
		} catch (Exception e) {}
		return courtList;
	}

	public ArrayList getState(String cntryId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList state = new ArrayList();
		try {
			state = bd.getState(cntryId, language);
		} catch (Exception e) {}
		return state;
	}

	/**
	 * @param String[]
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getDistrict(String stateId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList district = new ArrayList();
		try {
			district = bd.getDistrict(stateId, language);
		} catch (Exception e) {}
		return district;
	}

	public ArrayList getDistrictCourt(String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList district = new ArrayList();
		try {
			district = bd.getDistrictCourt(language);
		} catch (Exception e) {}
		return district;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPhotoId(String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList photo = new ArrayList();
		try {
			photo = bd.getPhoto(language);
		} catch (Exception e) {}
		return photo;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAppellate(String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList appellate = new ArrayList();
		try {
			appellate = bd.getAppellate(language);
		} catch (Exception e) {}
		return appellate;
	}

	public boolean insertTxn(EstampFormBean form, String lang) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.insertTxn(form, lang);
		return flg;
	}

	public ArrayList getDocType(String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList docType = new ArrayList();
		try {
			docType = bd.getDocType(language);
		} catch (Exception e) {}
		return docType;
	}

	public String getCurrentYear() throws Exception {
		EstampingBD bd = new EstampingBD();
		String currDate = bd.getCurrDateTime();
		DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
		Date date = (Date) formatter.parse(currDate);
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		String currentYear = formatYear.format(date);
		System.out.println("year----------**********************>" + currentYear);
		return currentYear;
	}

	public String getDate() throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		String date = objEstampingBD.getDate();
		return date;
	}

	public ArrayList getPayDtls(String txnId) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getPayDtls(txnId);
		} catch (Exception e) {}
		return paydtls;
	}

	public String getDuty(String txnId) throws Exception {
		EstampingBD bd = new EstampingBD();
		String paydtls = null;
		try {
			paydtls = bd.getDuty(txnId);
		} catch (Exception e) {}
		return paydtls;
	}

	public boolean insertPay(EstampFormBean form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.insertPay(form, dto);
		return flg;
	}

	//added by gulrej
	//This method updates IGRS_ESTAMP_PAYMENT_DETLS on basis of ESTAMP_TXN_ID
	public boolean updatePay1(EstampFormBean form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.updatePay1(form, dto);
		return flg;
	}

	public boolean updatePay(EstampFormBean form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.updatePay(form, dto);
		return flg;
	}

	/**
	 * getCurrentYear for inserting registration initiation other property
	 * details in db.
	 * 
	 * @param String
	 * @return boolean
	 * @author Lavi
	 */
	public String getCurrentDate() throws Exception {
		EstampingBD bd = new EstampingBD();
		String currDate = bd.getCurrDateTime();
		/*DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
		Date date = (Date)formatter.parse(currDate);
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		String currentYear = formatYear.format(date);
		System.out.println("year----------**********************>"+currentYear);
		
		*/return currDate;
	}

	public String getRole(String uid) throws Exception {
		String role = null;
		PaymentDAO dao = new PaymentDAO();
		role = dao.getRole(uid);
		return role;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getruName(String txnId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList rudtls = new ArrayList();
		try {
			rudtls = bd.getruName(txnId, language);
		} catch (Exception e) {}
		return rudtls;
	}

	/**
	 * @return String
	 * @throws Exception
	 */
	public String getspName(String txnId) throws Exception {
		EstampingBD bd = new EstampingBD();
		String paydtls = null;
		try {
			paydtls = bd.getspName(txnId);
		} catch (Exception e) {}
		return paydtls;
	}

	public ArrayList getspDtls(String txnId, String lang) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList spDtls = new ArrayList();
		try {
			spDtls = bd.getspDtls(txnId, lang);
		} catch (Exception e) {}
		return spDtls;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getspbnkDtls(String txnId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getspbnkDtls(txnId, language);
		} catch (Exception e) {}
		return paydtls;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getAppDtls(String txnId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getAppDtls(txnId, language);
		} catch (Exception e) {}
		return paydtls;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getPartyDtls(String txnId, String language) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getPartyDtls(txnId, language);
		} catch (Exception e) {}
		return paydtls;
	}

	/******************************************************************
	 * Method Name : inserteCode() Arguments : Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean inserteCode(EstampFormBean form, EstampDetailsDTO dto) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.inserteCode(form, dto);
		return flg;
	}

	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList getEcodeDtls(String txnId) throws Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getEcodeDtls(txnId);
		} catch (Exception e) {}
		return paydtls;
	}

	/******************************************************************
	 * Method Name : updateTxn() Arguments : Form Return : Boolean Throws :
	 * NullPointer or SQLException or Exception
	 *******************************************************************/
	public boolean updateTxn(EstampFormBean form, String lang) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.updateTxn(form, lang);
		return flg;
	}

	public boolean updateStatus(EstampFormBean form) throws Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.updateStatus(form);
		return flg;
	}

	public ArrayList getjudDetails(String TxnId, String language) throws Exception {
		//String TxnId = dto.getHidnEstampTxnId();
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList judDetails = new ArrayList();
		ArrayList list = objEstampingBD.getjudDetails(TxnId, language);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					EstampDetailsDTO objEstampDetailsDTO = new EstampDetailsDTO();
					rowList = (ArrayList) list.get(i);
					objEstampDetailsDTO.setCourtDocTypeName(rowList.get(0).toString());
					objEstampDetailsDTO.setCourtName(rowList.get(1).toString());
					//objEstampDetailsDTO.setCourtPlace(rowList.get(2).toString());
					objEstampDetailsDTO.setCntryName(rowList.get(2).toString());
					objEstampDetailsDTO.setStateCourtName(rowList.get(3).toString());
					objEstampDetailsDTO.setDisttName(rowList.get(4).toString());
					objEstampDetailsDTO.setAmount(rowList.get(5).toString());
					if (rowList.get(6) != null) {
						objEstampDetailsDTO.setEstampPurps(rowList.get(6).toString());
					} else {
						objEstampDetailsDTO.setEstampPurps("--");
					}
					objEstampDetailsDTO.setDate(rowList.get(7));
					if (rowList.get(8).toString().trim().equals("D")) {
						if ("en".equalsIgnoreCase(language)) {
							objEstampDetailsDTO.setCourtType("District Court");
						} else {
							objEstampDetailsDTO.setCourtType("जिला न्यायालय ");
						}
					} else {
						if ("en".equalsIgnoreCase(language)) {
							objEstampDetailsDTO.setCourtType("Tehsil Court");
						} else {
							objEstampDetailsDTO.setCourtType("तहसील न्यायालय");
						}
					}
					//objEstampDetailsDTO.setD;
					judDetails.add(objEstampDetailsDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return judDetails;
	}

	public ArrayList getDetailsOfApplicantJud(String TxnId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfApplicant = new ArrayList();
		ArrayList list = objEstampingBD.getDetailsOfApplicantJud(TxnId, language);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					EstampDetailsDTO objEstampDetailsDTO = new EstampDetailsDTO();
					rowList = (ArrayList) list.get(i);
					//System.out.println("*********IN BO"+(String)rowList.get(22));
					//System.out.println("*********IN BO"+(String)rowList.get(2));
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					objEstampDetailsDTO.setApplicant_ind(rowList.get(22));
					if (objEstampDetailsDTO.getApplicant_ind().toString().equalsIgnoreCase("Y")) {
						objEstampDetailsDTO.setAppType(rowList.get(2).toString());
						if (objEstampDetailsDTO.getAppType().toString().equals("2"))
						//}else{
						{
							objEstampDetailsDTO.setAppType(rowList.get(2).toString());
							objEstampDetailsDTO.setAppTypeName(rowList.get(1).toString());
							objEstampDetailsDTO.setAppFirsName(rowList.get(3).toString());
							objEstampDetailsDTO.setAppMiddleName(rowList.get(4).toString());
							objEstampDetailsDTO.setAppLastName(rowList.get(5).toString());
							objEstampDetailsDTO.setAppGender(rowList.get(6).toString());
							String apgender = rowList.get(6).toString();
							if (apgender.equalsIgnoreCase("M")) {
								if (language.equalsIgnoreCase("en"))
									objEstampDetailsDTO.setAppGenderDisp("Male");
								else
									objEstampDetailsDTO.setAppGenderDisp("पुस्र्ष");
							} else {
								if (language.equalsIgnoreCase("en"))
									objEstampDetailsDTO.setAppGenderDisp("Female");
								else
									objEstampDetailsDTO.setAppGenderDisp("महिला");
							}
							objEstampDetailsDTO.setAppAge(rowList.get(7).toString());
							objEstampDetailsDTO.setAppFatherName(rowList.get(8).toString());
							objEstampDetailsDTO.setAppMotherName(rowList.get(9).toString());
							objEstampDetailsDTO.setAppCountryName(rowList.get(10).toString());
							objEstampDetailsDTO.setAppStateName(rowList.get(11).toString());
							objEstampDetailsDTO.setAppDistrictName(rowList.get(12).toString());
							objEstampDetailsDTO.setAppAddress(rowList.get(13).toString());
							if (rowList.get(14) != null || rowList.get(14) != "") {
								objEstampDetailsDTO.setAppPostalCode(rowList.get(14));
							} else {
								objEstampDetailsDTO.setAppPostalCode("-");
							}
							objEstampDetailsDTO.setAppPhno(rowList.get(15).toString());
							objEstampDetailsDTO.setAppMobno(rowList.get(16).toString());
							objEstampDetailsDTO.setAppEmailID(rowList.get(17).toString());
							objEstampDetailsDTO.setAppPhotoIdName(rowList.get(18).toString());
							objEstampDetailsDTO.setAppPhotoIdno(rowList.get(19).toString());
							objEstampDetailsDTO.setAppPersons(rowList.get(20).toString());
						} else {
							objEstampDetailsDTO.setAppType(rowList.get(2).toString());
							objEstampDetailsDTO.setAppTypeName(rowList.get(1).toString());
							objEstampDetailsDTO.setAppOrgName(rowList.get(21).toString());
							objEstampDetailsDTO.setAppAuthFirstName(rowList.get(3).toString());
							objEstampDetailsDTO.setAppAuthMiddleName(rowList.get(4).toString());
							objEstampDetailsDTO.setAppAuthLastName(rowList.get(5).toString());
							objEstampDetailsDTO.setAppCountryName(rowList.get(10).toString());
							objEstampDetailsDTO.setAppStateName(rowList.get(11).toString());
							objEstampDetailsDTO.setAppDistrictName(rowList.get(12).toString());
							objEstampDetailsDTO.setAppAddress(rowList.get(13).toString());
							if (rowList.get(14) != null || rowList.get(14) != "") {
								objEstampDetailsDTO.setAppPostalCode(rowList.get(14));
							} else {
								objEstampDetailsDTO.setAppPostalCode("-");
							}
							objEstampDetailsDTO.setAppPhno(rowList.get(15).toString());
							objEstampDetailsDTO.setAppMobno(rowList.get(16).toString());
							objEstampDetailsDTO.setAppEmailID(rowList.get(17).toString());
							objEstampDetailsDTO.setAppPersons(rowList.get(20).toString());
						}
					}
					if (objEstampDetailsDTO.getApplicant_ind().toString().equalsIgnoreCase("N")) {
						objEstampDetailsDTO.setPartyType(rowList.get(2).toString());
						if (objEstampDetailsDTO.getPartyType().toString().equals("2"))
						//}else{
						{
							objEstampDetailsDTO.setPartyType(rowList.get(2).toString());
							objEstampDetailsDTO.setPartyTypeName(rowList.get(1).toString());
							objEstampDetailsDTO.setPartyFirstName(rowList.get(3).toString());
							objEstampDetailsDTO.setPartyMiddleName(rowList.get(4).toString());
							objEstampDetailsDTO.setPartyLastName(rowList.get(5).toString());
							objEstampDetailsDTO.setPartyGender(rowList.get(6).toString());
							String prtygender = rowList.get(6).toString();
							if (prtygender.equalsIgnoreCase("M")) {
								if (language.equalsIgnoreCase("en"))
									objEstampDetailsDTO.setPartyGenderDisp("Male");
								else
									objEstampDetailsDTO.setPartyGenderDisp("पुस्र्ष");
							} else {
								if (language.equalsIgnoreCase("en"))
									objEstampDetailsDTO.setPartyGenderDisp("Female");
								else
									objEstampDetailsDTO.setPartyGenderDisp("महिला");
							}
							objEstampDetailsDTO.setPartyAge(rowList.get(7).toString());
							objEstampDetailsDTO.setPartyFatherName(rowList.get(8).toString());
							objEstampDetailsDTO.setPartyMotherName(rowList.get(9).toString());
							objEstampDetailsDTO.setPartyCountryName(rowList.get(10).toString());
							objEstampDetailsDTO.setPartyStateName(rowList.get(11).toString());
							objEstampDetailsDTO.setPartyDistrictName(rowList.get(12).toString());
							objEstampDetailsDTO.setPartyAddress(rowList.get(13).toString());
							if (rowList.get(14) != null || rowList.get(14) != "") {
								objEstampDetailsDTO.setPartyPostalCode(rowList.get(14));
							} else {
								objEstampDetailsDTO.setPartyPostalCode("-");
							}
							objEstampDetailsDTO.setPartyPhno(rowList.get(15).toString());
							objEstampDetailsDTO.setPartyMobno(rowList.get(16).toString());
							objEstampDetailsDTO.setPartyEmailID(rowList.get(17).toString());
							objEstampDetailsDTO.setPartyPhotoIdName(rowList.get(18).toString());
							objEstampDetailsDTO.setPartyPhotoIdno(rowList.get(19).toString());
							objEstampDetailsDTO.setPartyPersons(rowList.get(20).toString());
						} else {
							objEstampDetailsDTO.setPartyType(rowList.get(2).toString());
							objEstampDetailsDTO.setPartyTypeName(rowList.get(1).toString());
							objEstampDetailsDTO.setPartyOrgName(rowList.get(21).toString());
							objEstampDetailsDTO.setPartyAuthFirstName(rowList.get(3).toString());
							objEstampDetailsDTO.setPartyAuthMiddleName(rowList.get(4).toString());
							objEstampDetailsDTO.setPartyAuthLastName(rowList.get(5).toString());
							objEstampDetailsDTO.setPartyCountryName(rowList.get(10).toString());
							objEstampDetailsDTO.setPartyStateName(rowList.get(11).toString());
							objEstampDetailsDTO.setPartyDistrictName(rowList.get(12).toString());
							objEstampDetailsDTO.setPartyAddress(rowList.get(13).toString());
							if (rowList.get(14) != null || rowList.get(14) != "") {
								objEstampDetailsDTO.setPartyPostalCode(rowList.get(14));
							} else {
								objEstampDetailsDTO.setPartyPostalCode("-");
							}
							objEstampDetailsDTO.setPartyPhno(rowList.get(15).toString());
							objEstampDetailsDTO.setPartyMobno(rowList.get(16).toString());
							objEstampDetailsDTO.setPartyEmailID(rowList.get(17).toString());
							objEstampDetailsDTO.setPartyPersons(rowList.get(20).toString());
						}
					}
					detilsOfApplicant.add(objEstampDetailsDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detilsOfApplicant;
	}

	public ArrayList getDetailsOfDocumentJud(String TxnId) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList detilsOfDocuments = new ArrayList();
		ArrayList list = objEstampingBD.getDetailsOfDocumentJud(TxnId);
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					EstampDetailsDTO objEstampDetailsDTO = new EstampDetailsDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					objEstampDetailsDTO.setDoc(rowList.get(1));
					detilsOfDocuments.add(objEstampDetailsDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return detilsOfDocuments;
	}

	public ArrayList getdocTypeValue(String documentName, String language) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList docTypeValue = new ArrayList();
		docTypeValue = bd.getdocTypeValue(documentName, language);
		return docTypeValue;
	}

	public ArrayList getecodeConsumption(String ecode, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeConStatus = new ArrayList();
		ArrayList list = objEstampingBD.getecodeConsumption(ecode);
		String Status = null;
		if (list != null && list.size() > 0) {
			ArrayList rowList;
			try {
				for (int i = 0; i < list.size(); i++) {
					DashBoardDTO objDashBoardDTO = new DashBoardDTO();
					rowList = (ArrayList) list.get(i);
					//objDashBoardDTO = new DashBoardDTO();
					//if(rowList.get(0).toString().length()==11){
					//}else{
					Status = (String) rowList.get(0);
					objDashBoardDTO.setConStatus(rowList.get(0));
					if (Status.equalsIgnoreCase("Not Consumed")) {
						objDashBoardDTO.setIsConsumed(0);
					} else if (Status.equalsIgnoreCase("Consumed")) {
						objDashBoardDTO.setIsConsumed(1);
					}
					if (lang.equalsIgnoreCase("hi")) {
						if (Status.equalsIgnoreCase("Consumed"))
							objDashBoardDTO.setConStatus("उपयोग कर् लिया गया है");
						else
							objDashBoardDTO.setConStatus("उपयोग नही किया गया है");
					}
					//*******ADDED BY SIMRAN******************//
					if (objDashBoardDTO.getIsConsumed() == 1) {
						String remarks = objEstampingBD.getRemarksForConsumption(ecode);
						if (remarks.equals("Deactivated")) {
							if (lang.equalsIgnoreCase("en")) {
								remarks = "E-stamp Code is deactivated.";
							} else {
								objDashBoardDTO.setConStatus("ई स्टाम्प निष्क्रिय हो चुका है");
								remarks = "ई स्टाम्प निष्क्रिय हो चुका है";
							}
						} else if (remarks.equals("E-stamp Code is expired/deactivated.")) {
							if (lang.equalsIgnoreCase("en")) {
								objDashBoardDTO.setConStatus("Expired");
								remarks = "E-stamp Code is expired.";
							} else {
								objDashBoardDTO.setConStatus("ई स्टांप की अवधि समाप्त हो गई है");
								remarks = "ई स्टांप की अवधि समाप्त हो गई है";
							}
						}
						objDashBoardDTO.setRemarks(remarks);
					}
					ecodeConStatus.add(objDashBoardDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ecodeConStatus;
	}

	public String gettype(String userId) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		String type = objEstampingBD.gettype(userId);
		return type;
	}

	public ArrayList getDetails(String userId, EstampDetailsDTO objEstampDto, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList details = new ArrayList();
		details = objEstampingBD.getdetails(userId, objEstampDto, language);
		ArrayList list = new ArrayList();
		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				ArrayList lst = (ArrayList) details.get(i);
				objEstampDto.setDistrictid((String) lst.get(0));
				objEstampDto.setDistrictname((String) lst.get(1));
				list.add(objEstampDto);
			}
		}
		return list;
	}

	public ArrayList getruDetails(String userId, EstampDetailsDTO objEstampDto, String txnId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList details = new ArrayList();
		details = objEstampingBD.getrudetails(userId, objEstampDto, txnId, language);
		ArrayList list = new ArrayList();
		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				ArrayList lst = (ArrayList) details.get(i);
				objEstampDto.setDistrictid((String) lst.get(0));
				objEstampDto.setDistrictname((String) lst.get(1));
				list.add(objEstampDto);
			}
		}
		return list;
	}

	public ArrayList getiuDetails(EstampDetailsDTO objEstampDto, String offcId, String language) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList details = new ArrayList();
		details = objEstampingBD.getiudetails(offcId, objEstampDto, language);
		ArrayList list = new ArrayList();
		if (details != null) {
			for (int i = 0; i < details.size(); i++) {
				ArrayList lst = (ArrayList) details.get(i);
				objEstampDto.setIuofficeid((String) lst.get(0));
				objEstampDto.setIuofficename((String) lst.get(1));
				objEstampDto.setDistrictid((String) lst.get(2));
				objEstampDto.setDistrictname((String) lst.get(3));
				list.add(objEstampDto);
			}
		}
		return list;
	}

	//added by satbir kumar------
	public boolean insertDocDetls(EstampFormBean form) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.insertDocDetls(form);
		return flg;
	}

	public ArrayList getiuDtls(String userId, String officeId, String language) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList paydtls = new ArrayList();
		try {
			paydtls = bd.getiuDtls(userId, officeId, language);
		} catch (Exception e) {}
		return paydtls;
	}

	public String getCertChkDetails(String ecode) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		return bd.getCertChkDetails(ecode);
	}

	public String getEstampDocRegDetails(String ecode) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		return bd.getEstampDocRegDetails(ecode);
	}

	public String getEstampDocDetails(String ecode) throws NullPointerException, SQLException, Exception {
		EstampingBD bd = new EstampingBD();
		ArrayList list = bd.getEstampDocDetails(ecode);
		ArrayList finalList = new ArrayList();
		String docPath = "";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList subList = (ArrayList) list.get(i);
				String docName = (String) subList.get(0);
				String docPath1 = (String) subList.get(1);
				docPath = docPath1 + docName;
			}
		}
		return docPath;
	}

	public boolean updateCertificateGenerationChk(String eStampTxnId) throws Exception {
		EstampingBD bd = new EstampingBD();
		return bd.updateCertificateGenerationChk(eStampTxnId);
	}

	public void validateDeedId(EstampDetailsDTO objEstampDto, String language) {
		try {
			EstampingBD bd = new EstampingBD();
			String status = bd.deedDraftId(objEstampDto.getDeedDraftId());
			if (status == null || status.equalsIgnoreCase("")) {
				objEstampDto.setDeedDraftErrorFlag("Y");
				objEstampDto.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					objEstampDto.setDeedDraftError("Invalid Id");
				} else {
					objEstampDto.setDeedDraftError("अवैध आईडी|");
				}
			} else if (status.equalsIgnoreCase("D")) {
				objEstampDto.setDeedDraftErrorFlag("Y");
				objEstampDto.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					objEstampDto.setDeedDraftError("Already Consumed");
				} else {
					objEstampDto.setDeedDraftError("आईडी को पहले से ही इस्तेमाल किया जा चुका है।");
				}
			} else {
				objEstampDto.setDeedDraftErrorFlag("N");
				objEstampDto.setDeedDraftStatus("A");
				objEstampDto.setDeedDraftError("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateDeedId1(DutyCalculationForm form, String language, String userID) {
		try {
			EstampingBD bd = new EstampingBD();
			String status = bd.deedDraftIdValidate(form.getDeedDraftId(), userID, "E");
			if (status == null || status.equalsIgnoreCase("")) {
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				boolean flagReg = bd.getDeedAppDetls(form.getDeedDraftId(), userID, "R");
				boolean flagAdj = bd.getDeedAppDetls(form.getDeedDraftId(), userID, "A");
				if (flagReg) {
					if ("en".equalsIgnoreCase(language)) {
						form.setDeedDraftError(form.getDeedDraftId() + " is created for Registration purpose");
					} else {
						form.setDeedDraftError("डीड नंबर " + form.getDeedDraftId() + " पंजीकरण उद्देश्य के लिए बनाया गया है|");
					}
				} else if (flagAdj) {
					if ("en".equalsIgnoreCase(language)) {
						form.setDeedDraftError(form.getDeedDraftId() + " is created for Adjudication purpose");
					} else {
						form.setDeedDraftError("डीड नंबर " + form.getDeedDraftId() + " न्यायनिर्णयन के लिए बनाया गया है|");
					}
				} else {
					String incompDeedCheck = bd.deedDraftIdIncompValidate(form.getDeedDraftId(), userID);
					if ("I".equalsIgnoreCase(incompDeedCheck)) {
						form.setDeedDraftErrorFlag("Y");
						form.setDeedDraftStatus("D");
						if ("en".equalsIgnoreCase(language)) {
							form.setDeedDraftError("Incomplete Deed");
						} else {
							form.setDeedDraftError("डीड अपूर्ण है");
						}
					} else {
						if ("en".equalsIgnoreCase(language)) {
							form.setDeedDraftError("Invalid Id");
						} else {
							form.setDeedDraftError("अवैध आईडी|");
						}
					}
				}
			} else if (status.equalsIgnoreCase("D")) {
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					form.setDeedDraftError("Already Consumed");
				} else {
					form.setDeedDraftError("आईडी को पहले से ही इस्तेमाल किया जा चुका है।");
				}
			} else if ("I".equalsIgnoreCase(status)) {
				//reg.setDeedStat("3");
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					form.setDeedDraftError("Incomplete Deed");
				} else {
					form.setDeedDraftError("डीड अपूर्ण है");
				}
			} else {
				form.setDeedDraftErrorFlag("N");
				form.setDeedDraftStatus("A");
				form.setDeedDraftError("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean updateDeedStatus(DutyCalculationForm form, String langauge, HttpServletResponse response) throws Exception {
		EstampingBD bd = new EstampingBD();
		return bd.updateDeedStatus(form, langauge, response);
	}

	public String getAmount(String userId) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		String type = objEstampingBD.getAmount(userId);
		return type;
	}

	public String getSubjectName(String userId) {
		EstampingBD objEstampingBD = null;
		try {
			objEstampingBD = new EstampingBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objEstampingBD.getSubjectNameBD(userId);
	}

	public boolean checkEstamp(String regTxnID, String mod_flag) throws Exception {
		EstampingBD bd = new EstampingBD();
		boolean flg = false;
		flg = bd.checkEstamp(regTxnID, mod_flag);
		return flg;
	}

	public String getEcode(String txnID, String modFlag) {
		EstampingBD objEstampingBD = null;
		try {
			objEstampingBD = new EstampingBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objEstampingBD.getEcode(txnID, modFlag);
	}

	public String[] getFatherName(String txnID) {
		EstampingBD bd;
		String[] nameList = {};
		try {
			bd = new EstampingBD();
			nameList = bd.getFatherName(txnID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameList;
	}

	public String validateDeedIdBeforeSavingRecord(DutyCalculationForm form, String language, String userID, String deedDraftId) {
		String result = "failed";
		try {
			EstampingBD bd = new EstampingBD();
			String status = bd.deedDraftIdValidateInEstampTxn(deedDraftId);
			if (status == null || status.equalsIgnoreCase("")) {
				form.setDeedDraftErrorFlag("N");
				form.setDeedDraftStatus("A");
				form.setDeedDraftError("");
				result = "passed";
			} else {
				form.setDeedDraftErrorFlag("Y");
				form.setDeedDraftStatus("D");
				if ("en".equalsIgnoreCase(language)) {
					form.setDeedDraftError("Deed Draft Id Already Consumed");
				} else {
					form.setDeedDraftError("डीड ड्राफ्ट आईडी पहले से  कन्जूम कर लिया गया है।");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String checkUser(String estampCodeId, String userId) {
		String checkUser = null;
		try {
			EstampingBD bd = new EstampingBD();
			checkUser = bd.checkUser(estampCodeId, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUser;
	}

	public void getjudCourtDetails(String estampCodeId, DutyCalculationForm form, String language) {
		try {
			EstampingBD bd = new EstampingBD();
			ArrayList courtdetails = null;
			courtdetails = bd.getjudCourtDetails(estampCodeId);
			if (courtdetails != null) {
				for (int i = 0; i < courtdetails.size(); i++) {
					ArrayList subList = (ArrayList) courtdetails.get(i);
					form.setCourtName((String) subList.get(2));
					if (language.equalsIgnoreCase("en")) {
						form.setCourtName((String) subList.get(2));
						form.setCourtDistrict((String) subList.get(4));
					} else {
						form.setCourtName((String) subList.get(3));
						form.setCourtDistrict((String) subList.get(5));
					}
					if ("en".equalsIgnoreCase(language)) {
						if (subList.get(8).toString().trim().equals("D")) {
							form.setCourtType("District Court");
						} else {
							form.setCourtType("Tehsil Court");
						}
					} else {
						if (subList.get(8).toString().trim().equals("D")) {
							form.setCourtType("जिला न्यायालय ");
						} else {
							form.setCourtType("तहसील न्यायालय");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList viewEcodeDetailsNJ_DRS(String ecode, String lang) throws Exception {
		EstampingBD objEstampingBD = new EstampingBD();
		ArrayList ecodeDetailsDRS = new ArrayList();
		String type = objEstampingBD.getecodetype(ecode);
		/* Added by Gulrej ************** adds Refund Request no in View Screen *** Start */
		ArrayList refundRequestDtls = objEstampingBD.getRefundRequestDtls(ecode);
		DashBoardDTO objDashBoardDTO = new DashBoardDTO();
		if (refundRequestDtls != null && refundRequestDtls.size() > 0) {
			ArrayList refundRequestDtlsList = new ArrayList();
			for (int i = 0; i < refundRequestDtls.size(); i++) {
				refundRequestDtlsList = (ArrayList) refundRequestDtls.get(i);
				objDashBoardDTO.setRefundRequestNo(refundRequestDtlsList.get(0).toString());
			}
		} else {
			objDashBoardDTO.setRefundRequestNo("NA");
		}
		/* Added by Gulrej ************** adds Refund Request no in View Screen **** End*///---added by satbir kumar---
		if ("E".equalsIgnoreCase(type)) {
			ArrayList list = objEstampingBD.viewEcodeDetailsNJ_DRS(ecode);
			String deactivateCount = objEstampingBD.getDeactivateCount(ecode);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						objDashBoardDTO.setEstampId(rowList.get(0));
						objDashBoardDTO.setEstampPurpose(rowList.get(1));
						objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
						objDashBoardDTO.setEstampStatus(rowList.get(3));
						if (deactivateCount.equalsIgnoreCase("0")) {
							if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Active");
								else
									objDashBoardDTO.setEstampStatus("सक्रिय");
							} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Expired");
								else
									objDashBoardDTO.setEstampStatus("अवधि समाप्त");
							} else {
								if (lang.equalsIgnoreCase("en"))
									objDashBoardDTO.setEstampStatus("Deactive");
								else
									objDashBoardDTO.setEstampStatus("अक्रिय");
							}
						} else {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Deactive");
							else
								objDashBoardDTO.setEstampStatus("अक्रिय");
						}
						objDashBoardDTO.setEstampValidity(rowList.get(4));
						objDashBoardDTO.setEstampBuyerName(rowList.get(5));
						objDashBoardDTO.setEstampAmount(rowList.get(6));
						/* if (rowList.get(7)== null){
							objDashBoardDTO.setRegInitId("-");
						}
						else
						{
						objDashBoardDTO.setRegInitId(rowList.get(7));
						}*/
						ecodeDetailsDRS.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if ("C".equalsIgnoreCase(type) || "R".equalsIgnoreCase(type)) {
			String regID = objEstampingBD.getregId(ecode);
			ArrayList list = objEstampingBD.viewCREcodeDetailsDRS(ecode, regID);
			if (list != null && list.size() > 0) {
				ArrayList rowList = new ArrayList();
				try {
					for (int i = 0; i < list.size(); i++) {
						//DashBoardDTO objDashBoardDTO = new DashBoardDTO();
						rowList = (ArrayList) list.get(i);
						//objDashBoardDTO = new DashBoardDTO();
						//if(rowList.get(0).toString().length()==11){
						//}else{
						objDashBoardDTO.setEstampId(rowList.get(0));
						String purpose = (String) (rowList.get(1));
						if ("".equalsIgnoreCase(purpose) || purpose == null) {
							objDashBoardDTO.setEstampPurpose("-");
						} else {
							objDashBoardDTO.setEstampPurpose(rowList.get(1));
						}
						objDashBoardDTO.setEstampPurchaseDate(rowList.get(2));
						objDashBoardDTO.setEstampStatus(rowList.get(3));
						if (objDashBoardDTO.getEstampStatus().toString().equals("A")) {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Active");
							else
								objDashBoardDTO.setEstampStatus("सक्रिय");
						} else if (objDashBoardDTO.getEstampStatus().toString().equals("E")) {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Expired");
							else
								objDashBoardDTO.setEstampStatus("अवधि समाप्त");
						} else {
							if (lang.equalsIgnoreCase("en"))
								objDashBoardDTO.setEstampStatus("Deactive");
							else
								objDashBoardDTO.setEstampStatus("अक्रिय");
						}
						/*if (objDashBoardDTO.getEstampStatus().toString().equals("A"))
							objDashBoardDTO.setEstampStatus("Active");
						
						else if (objDashBoardDTO.getEstampStatus().toString().equals("E"))
							objDashBoardDTO.setEstampStatus("Expired");
						
						else
							objDashBoardDTO.setEstampStatus("Deactive");
						*/
						objDashBoardDTO.setEstampValidity(rowList.get(4));
						String buyername = (String) (rowList.get(5));
						if ("  ".equalsIgnoreCase(buyername) || buyername == null) {
							objDashBoardDTO.setEstampBuyerName(rowList.get(7));
						} else {
							objDashBoardDTO.setEstampBuyerName(rowList.get(5));
						}
						objDashBoardDTO.setEstampAmount(rowList.get(6));
						/* if (rowList.get(7)== null){
							objDashBoardDTO.setRegInitId("-");
						}
						else
						{
						objDashBoardDTO.setRegInitId(rowList.get(7));
						}*/
						ecodeDetailsDRS.add(objDashBoardDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ecodeDetailsDRS;
	}

	public String getGovtStamp(String txnID) {
		EstampingBD objEstampingBD = null;
		try {
			objEstampingBD = new EstampingBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objEstampingBD.getGovtStamp(txnID);
	}
}

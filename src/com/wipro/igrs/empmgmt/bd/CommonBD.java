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
package com.wipro.igrs.empmgmt.bd;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.empmgmt.dao.EmpMgntDAO;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.AddressDTO;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.BankMstDTO;
import com.wipro.igrs.empmgmt.dto.CommonItemsDTO;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.dto.RelativeTypeDTO;
import com.wipro.igrs.empmgmt.form.PersonalForm;

/**
* 
* CommonBD.java <br>
* CommonBD <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class CommonBD {
	
	private Logger logger = (Logger) Logger.getLogger(CommonBD.class);
	
	/**
	 * @throws Exception
	 */
	/**
	 * @throws Exception
	 */
/*	public CommonBD() throws Exception {
		//pDAO = new EmpMgntDAO();
	}
*/
	/**
	 * @param detailsDTO
	 * @return
	 */
	/**
	 * @param detailsDTO
	 * @return
	 */
	public ArrayList getReference(PersonalDetailsDTO detailsDTO) {
		ArrayList referList = new ArrayList();
		try {
			EmpMgntDAO pDAO = new EmpMgntDAO();
			ArrayList referencelist = pDAO.getReference(detailsDTO);
			if (referencelist != null) {
				for (int i = 0; i < referencelist.size(); i++) {
					ArrayList refferlist = (ArrayList) referencelist.get(i);
					if (refferlist != null) {
						PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();
						personalDetailsDTO.setFirstName((String) refferlist
								.get(0));
						personalDetailsDTO.setMiddleName((String) refferlist
								.get(1));
						personalDetailsDTO.setLastName((String) refferlist
								.get(2));
						personalDetailsDTO.setOfficename((String) refferlist
								.get(3));
						personalDetailsDTO
								.setOfficelocation((String) refferlist.get(4));
						referList.add(personalDetailsDTO);
					}
				}
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}
		return referList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountry(String locale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList countlist = common.getCountry();

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AddressDTO cntdto = new AddressDTO();
					cntdto.setCountryId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
					cntdto.setCountryName((String) cnttlist.get(2));
					}else{
					cntdto.setCountryName((String) cnttlist.get(1));
					}
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param _countryId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param _countryId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getState(String _countryId,String locale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList statelist = common.getState(_countryId);
		ArrayList stList = new ArrayList();
		if (statelist != null) {
			for (int i = 0; i < statelist.size(); i++) {
				ArrayList sttlist = (ArrayList) statelist.get(i);
				if (sttlist != null) {
					AddressDTO stdto = new AddressDTO();
					stdto.setStateId((String) sttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						stdto.setStateName((String) sttlist.get(2));

					}else{
					stdto.setStateName((String) sttlist.get(1));
					}
					stList.add(stdto);
				}
			}
		}
		return stList;
	}/**
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHomeState(String locale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList statelist = common.getHomeState();
		ArrayList stList = new ArrayList();
		if (statelist != null) {
			for (int i = 0; i < statelist.size(); i++) {
				ArrayList sttlist = (ArrayList) statelist.get(i);
				if (sttlist != null) {
					PersonalDetailsDTO stdto = new PersonalDetailsDTO();
					stdto.setHomeStateId((String) sttlist.get(0));
					stdto.setHomeState((String) sttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						stdto.setHomeStateName((String) sttlist.get(2));

					}else{
					stdto.setHomeStateName((String) sttlist.get(1));
					}
					stList.add(stdto);
				}
			}
		}
		return stList;
	}
	/**
	 * @param _stateId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param _stateId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict(String _stateId,String locale) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList distlist = common.getDistrict(_stateId);
		ArrayList dstList = new ArrayList();
		if (distlist != null) {
			for (int i = 0; i < distlist.size(); i++) {
				ArrayList disttlist = (ArrayList) distlist.get(i);
				if (disttlist != null) {
					AddressDTO dstdto = new AddressDTO();
					dstdto.setDistrictId((String) disttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
				dstdto.setDistrictName((String) disttlist.get(2));

					}else{
					dstdto.setDistrictName((String) disttlist.get(1));
					}
					dstList.add(dstdto);
				}
			}
		}
		return dstList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountry_curr() throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList countlist = common.getCountry();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					CommonItemsDTO cntdto = new CommonItemsDTO();
					cntdto.setCountryId((String) cnttlist.get(0));
					cntdto.setCountryName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param _countryId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param _countryId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getState_curr(String _countryId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList statelist = common.getState(_countryId);
		ArrayList stList = new ArrayList();
		if (statelist != null) {
			for (int i = 0; i < statelist.size(); i++) {
				ArrayList sttlist = (ArrayList) statelist.get(i);
				if (sttlist != null) {
					CommonItemsDTO stdto = new CommonItemsDTO();
					stdto.setStateId((String) sttlist.get(0));
					stdto.setStateName((String) sttlist.get(1));
					stList.add(stdto);
				}
			}
		}
		return stList;
	}

	/**
	 * @param _stateId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param _stateId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrict_curr(String _stateId) throws Exception {
		IGRSCommon common = new IGRSCommon();
		ArrayList distlist = common.getDistrict(_stateId);
		ArrayList dstList = new ArrayList();
		if (distlist != null) {
			for (int i = 0; i < distlist.size(); i++) {
				ArrayList disttlist = (ArrayList) distlist.get(i);
				if (disttlist != null) {
					CommonItemsDTO dstdto = new CommonItemsDTO();
					dstdto.setDistrictId((String) disttlist.get(0));
					dstdto.setDistrictName((String) disttlist.get(1));
					dstList.add(dstdto);
				}
			}
		}
		return dstList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReligion(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList countlist = pDAO.getReligion();

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PersonalDetailsDTO cntdto = new PersonalDetailsDTO();
					cntdto.setReligionId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
					cntdto.setReligionName((String) cnttlist.get(2));						
					}else{
					cntdto.setReligionName((String) cnttlist.get(1));
					}
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCaste(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList countlist = pDAO.getCaste();

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PersonalDetailsDTO cntdto = new PersonalDetailsDTO();
					cntdto.setCasteId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setCasteName((String) cnttlist.get(2));						
					}else{
					cntdto.setCasteName((String) cnttlist.get(1));
					}
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStream() throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList countlist = pDAO.getStream();

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AcademicDTO cntdto = new AcademicDTO();
					cntdto.setQualificainId((String) cnttlist.get(0));
					cntdto.setQualificainName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHomeDistrict(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList countlist = pDAO.getHomeDistrict();

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PersonalDetailsDTO cntdto = new PersonalDetailsDTO();
					cntdto.setHomeDistrictId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setHomeDistrictName((String) cnttlist.get(2));

					}else{
					cntdto.setHomeDistrictName((String) cnttlist.get(1));
					}
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}
	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHomeDistrictEmp(String homeStateID,String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList countlist = pDAO.getHomeDistrictEmp(homeStateID);

		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					PersonalDetailsDTO cntdto = new PersonalDetailsDTO();
					cntdto.setHomeDistrictId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setHomeDistrictName((String) cnttlist.get(2));

					}else{
					cntdto.setHomeDistrictName((String) cnttlist.get(1));
					}
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param eDTO
	 * @param childList
	 * @return
	 */
	/**
	 * @param eDTO
	 * @param childList
	 * @param userid
	 * @return
	 */
	public boolean insertPersonalDetails(PersonalDetailsDTO eDTO,
			ArrayList childList, String userid) {
		EmpMgntDAO pDAO;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean flag = false;
		String eDOB = null;
		String ed = (eDTO.getEmpDay() == null) ? null : eDTO.getEmpDay();
		String em = (eDTO.getEmpMonth() == null) ? null : eDTO.getEmpMonth();
		String ey = (eDTO.getEmpYear() == null) ? null : eDTO.getEmpYear();
		if (ed != null && em != null && ey != null) {
			eDOB = ed + em + ey;
		}
		String empMaster[] = new String[34];
		empMaster[0] = eDTO.getEmployeeId();
		empMaster[1] = eDTO.getReferenceId();
		empMaster[2] = eDTO.getFirstName();
		empMaster[3] = eDTO.getMiddleName();
		empMaster[4] = eDTO.getLastName();
		empMaster[5] = eDTO.getGender();
		empMaster[6] = eDOB;
		empMaster[7] = eDTO.getDateOfBirthWords();
		empMaster[8] = eDTO.getFatherGaurdName();
		empMaster[9] = eDTO.getMotherName();
		empMaster[10] = eDTO.getMaritalStatus();
		empMaster[11] = eDTO.getReligion();
		empMaster[12] = eDTO.getHomeDistrict();
		empMaster[13] = eDTO.getCaste();
		empMaster[14] = eDTO.getChkPhysically();
		empMaster[15] = eDTO.getPhysicallyChallanged();
		empMaster[16] = "" + eDTO.getHeight();
		empMaster[17] = eDTO.getNationality();
		empMaster[18] = eDTO.getIdentificationMarks();
		empMaster[19] = eDTO.getPhoneNo();
		empMaster[20] = eDTO.getMobileNo();
		empMaster[21] = eDTO.getEmailId();
		empMaster[22] = eDTO.getPermAddress();
		empMaster[23] = eDTO.getPermCountry();
		empMaster[24] = eDTO.getPermState();
		empMaster[25] = eDTO.getPermDistrict();
		empMaster[26] = "" + eDTO.getPermPin();
		empMaster[27] = eDTO.getCurrAddress();
		empMaster[28] = eDTO.getCurrCountry();
		empMaster[29] = eDTO.getCurrState();
		empMaster[30] = eDTO.getCurrDistrict();
		empMaster[31] = "" + eDTO.getCurrPin();
		empMaster[32] = userid;
		empMaster[33] = userid;
		String Userlist[]={eDTO.getEmployeeId(),"I",userid,userid,"A"};	
		
		try {
			pDAO = new EmpMgntDAO();
			flag = pDAO.insertEmpFamilyMaster(empMaster, childList, eDTO,
					userid,Userlist);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * @param academicList
	 * @param prevEmpList
	 * @param employeeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param academicList
	 * @param prevEmpList
	 * @param employeeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public boolean addTalent(ArrayList academicList, ArrayList prevEmpList,
			String employeeid, String userid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		return pDAO.addTalent(academicList, prevEmpList, employeeid, userid);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getFundType() throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		IGRSCommon common = new IGRSCommon();
		ArrayList fundlist = pDAO.fundDetails();
		ArrayList fundTypeList = new ArrayList();

		if (fundlist != null) {
			for (int i = 0; i < fundlist.size(); i++) {
				ArrayList fundList = (ArrayList) fundlist.get(i);
				if (fundList != null) {
					BankDTO bankDTO = new BankDTO();
					bankDTO.setFundTypeId((String) fundList.get(0));
					bankDTO.setFundTypeName((String) fundList.get(1));
					fundTypeList.add(bankDTO);
				}

			}
		}

		return fundTypeList;
	}

	// for bank
	/**
	 * @param bankDTO
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param bankDTO
	 * @param fundlist
	 * @param nomineeslist
	 * @param userId
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	public boolean insertBankDetails(BankDTO bankDTO, ArrayList fundlist,
			ArrayList nomineeslist, String userId, String employeeId)
			throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		boolean flag = false;
		try {
			flag = pDAO.insertBankDetails(bankDTO, bankDTO.getFundList(), nomineeslist,
					userId, employeeId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTraining(String empid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList financialfromyear = pDAO.getTraining(empid);
		ArrayList _fiscalfromyear = new ArrayList();
		ArrayList _leaves = null;
		for (int i = 0; i < financialfromyear.size(); i++) {
			_leaves = (ArrayList) financialfromyear.get(i);
			if (_leaves != null) {

				DeptTrainingDTO leavedto = new DeptTrainingDTO();
				leavedto.setTrainingNo((String) _leaves.get(0));
				leavedto.setTrainingName((String) _leaves.get(1));
				leavedto.setTrainingLevel((String) _leaves.get(2));
				leavedto.setOrganizingAuthority((String) _leaves.get(3));
				leavedto.setOrgainizingBody((String) _leaves.get(4));
				leavedto.setPlaceOfTraining((String) _leaves.get(5));
				leavedto.setFinancialYear((String) _leaves.get(6));
				// leavedto.setTrainingStartDate((String)_leaves.get(7));
				// leavedto.setTrainingEndDate((String)_leaves.get(8));
				leavedto.setAuthorisingAuthority((String) _leaves.get(7));
				// leavedto.setAuthorizationDate((String)_leaves.get(10));
				leavedto.setTrainingCost((String) _leaves.get(8));
				leavedto.setTrainingResult((String) _leaves.get(9));
				leavedto.setTrainingComments((String) _leaves.get(10));

				_fiscalfromyear.add(leavedto);

			}
		}
		return _fiscalfromyear;

	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getExam(String empid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList financialfromyear = pDAO.getExam(empid);
		ArrayList _fiscalfromyear = new ArrayList();
		ArrayList _leaves = null;
		for (int i = 0; i < financialfromyear.size(); i++) {
			_leaves = (ArrayList) financialfromyear.get(i);
			if (_leaves != null) {

				DeptExamDTO leavedto = new DeptExamDTO();
				leavedto.setNameOfExam((String) _leaves.get(0));
				leavedto.setExamDate((String) _leaves.get(1));
				leavedto.setExamsOrganizingAuthority((String) _leaves.get(2));
				leavedto.setPlaceOfExam((String) _leaves.get(3));
				leavedto.setResult((String) _leaves.get(4));
				leavedto.setResultDate((String) _leaves.get(5));
				leavedto.setExamsComments((String) _leaves.get(6));

				_fiscalfromyear.add(leavedto);

			}
		}
		return _fiscalfromyear;

	}

	/**
	 * @param trainingList
	 * @return
	 * @throws Exception
	 */
	// public boolean insertTraining(ArrayList trainingList) throws Exception {
	// return pDAO.insertTraining(trainingList);
	// }
	/**
	 * @param trainingList
	 * @param examList
	 * @param strUserId
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public boolean addDepartment(ArrayList trainingList, ArrayList examList,
			String strUserId, String empid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		return pDAO.addDepartment(trainingList, examList, strUserId, empid);
	}

	/**
	 * @param examList
	 * @return
	 * @throws Exception
	 */
	// public boolean insertExam(ArrayList examList) throws Exception {
	// return pDAO.insertExam(examList);
	// }
	// for Property
	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAssetTypeList() throws Exception {
		IGRSCommon common = new IGRSCommon();
		EmpMgntDAO pDAO = new EmpMgntDAO();
		// ArrayList countlist = empMgmtDAO.getDistrictList(state);
		ArrayList countlist = pDAO.getAssetTypeList();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AssetDTO cntdto = new AssetDTO();
					cntdto.setAssetTypeId((String) cnttlist.get(0));
					cntdto.setAssetTypeName((String) cnttlist.get(1));
					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param propertyList
	 * @param assetList
	 * @param strUserid
	 * @param strEmployeeId
	 * @return
	 * @throws Exception
	 */
	public boolean addProperty(ArrayList propertyList, ArrayList assetList,
			String strUserid, String strEmployeeId) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		return pDAO.addProperty(propertyList, assetList, strUserid,
				strEmployeeId);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getCountryList(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		// IGRSCommon common = new IGRSCommon();
		ArrayList countlist = pDAO.getCountryList();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AddressDTO cntdto = new AddressDTO();
					cntdto.setCountryId((String) cnttlist.get(0));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setCountryName((String) cnttlist.get(2));
						
					}else{
						cntdto.setCountryName((String) cnttlist.get(1));
						
					}

					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param country
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getStateList(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		// IGRSCommon common = new IGRSCommon();
		ArrayList countlist = pDAO.getStateList();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AddressDTO cntdto = new AddressDTO();
					cntdto.setCountryId((String) cnttlist.get(0));
					cntdto.setStateId((String) cnttlist.get(1));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setStateName((String) cnttlist.get(3));
		
					}else{
						cntdto.setStateName((String) cnttlist.get(2));
	
					}

					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	/**
	 * @param state
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDistrictList(String locale) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		// IGRSCommon common = new IGRSCommon();
		ArrayList countlist = pDAO.getDistrictList();
		ArrayList cntList = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					AddressDTO cntdto = new AddressDTO();
					cntdto.setStateId((String) cnttlist.get(0));
					cntdto.setDistrictId((String) cnttlist.get(1));
					if(locale.equalsIgnoreCase("hi_IN")){
						cntdto.setDistrictName((String) cnttlist.get(3));
						
					}else{
						cntdto.setDistrictName((String) cnttlist.get(2));
					
					}

					cntList.add(cntdto);
				}
			}
		}
		return cntList;
	}

	// /offical Info

	/*
	 * public boolean submitReportHirachy(OfficalInfoDTO officalInfoDTO)throws
	 * Exception{ return pDAO.submitReportingHirachy(officalInfoDTO); }
	 */
	/**
	 * @param servicelist
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param servicelist
	 * @return
	 * @throws Exception
	 */
	public boolean submitService(ArrayList servicelist) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		return pDAO.submitService(servicelist);
	}

	/**
	 * @param officalInfoDTO
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param officalInfoDTO
	 * @return
	 * @throws Exception
	 */
	public boolean submitOfficalInfo(OfficalInfoDTO officalInfoDTO)
			throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		return pDAO.insertOfficalInfo(officalInfoDTO);

	}

	/*
	 * public boolean uploadOfficialInfo(ArrayList documentList,String
	 * employeeid)throws Exception { return
	 * pDAO.uploadOfficialInfo(documentList,employeeid); }
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllgradeList() throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList list = pDAO.getAllgradeList();
		ArrayList _gradelist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			ArrayList gradelist = (ArrayList) list.get(i);
			if (gradelist != null) {
				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();
				officalInfoDTO.setGradeid(gradelist.get(0).toString());
				officalInfoDTO.setClass1(gradelist.get(1).toString());
				_gradelist.add(officalInfoDTO);
			}
		}
		return _gradelist;

	}

	/*
	 * public ArrayList getAllDocumentList()throws Exception { ArrayList
	 * documentlist=pDAO.getAllDocumentList(); ArrayList _documentlist=new
	 * ArrayList(); for(int i=0;i<documentlist.size();i++) { ArrayList
	 * _document=(ArrayList)documentlist.get(i);
	 * 
	 * 
	 * if(documentlist!=null) {
	 * 
	 * ServiceVerificationDTO verificationDTO=new ServiceVerificationDTO();
	 * verificationDTO.setDocumentid((String)_document.get(0));
	 * verificationDTO.setDocumentType((String)_document.get(1));
	 * _documentlist.add(verificationDTO); } } return _documentlist; }
	 */
	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllCadres(String gradeId) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList gradetypes = pDAO.getAllCadres(gradeId);
		ArrayList _cadrelist = new ArrayList();
		for (int i = 0; i < gradetypes.size(); i++) {
			ArrayList _cadres = (ArrayList) gradetypes.get(i);


			if (gradetypes != null) {

				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();
				officalInfoDTO.setCadreid((String) _cadres.get(0));
				officalInfoDTO.setDesignation((String) _cadres.get(1));

				_cadrelist.add(officalInfoDTO);

			}
		}
		return _cadrelist;

	}

	/*
	 * public ArrayList getAllOfficating(String officating)throws Exception {
	 * ArrayList gradetypes=pDAO.getAllOfficating(officating);
	 * ArrayList _cadrelist=new
	 * ArrayList(); for(int i=0;i<gradetypes.size();i++) { ArrayList
	 * _cadres=(ArrayList)gradetypes.get(i);
	 * 
	 * 
	 * 
	 * if(gradetypes!=null) {
	 * 
	 * OfficalInfoDTO officalInfoDTO=new OfficalInfoDTO();
	 * 
	 * officalInfoDTO. setDesignation((String)_cadres.get(0));
	 * officalInfoDTO.setOfficiatingid((String)_cadres.get(1));
	 * _cadrelist.add(officalInfoDTO); } } return _cadrelist; }
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getRepoteeList(String empid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList reportingHirachy = pDAO.getRepoteeList(empid);
		ArrayList hirachylist = new ArrayList();
		for (int i = 0; i < reportingHirachy.size(); i++) {
			ArrayList hirachy = (ArrayList) reportingHirachy.get(i);
			// ArrayList supervisorID=new ArrayList();
			if (reportingHirachy != null) {
				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();

				officalInfoDTO.setDesignation((String) hirachy.get(0));
				officalInfoDTO.setSupervisorID(empid);
				// officalInfoDTO.setSupervisorName((String)hirachy.get(1));
				hirachylist.add(officalInfoDTO);
			}

		}
		return hirachylist;

	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getReportingHirachy(String empid) throws Exception {
		EmpMgntDAO pDAO = new EmpMgntDAO();
		ArrayList reportingHirachy = pDAO.getReportingHirachy(empid);
		ArrayList hirachylist = new ArrayList();
		for (int i = 0; i < reportingHirachy.size(); i++) {
			ArrayList hirachy = (ArrayList) reportingHirachy.get(i);
			// ArrayList supervisorID=new ArrayList();
			if (reportingHirachy != null) {
				OfficalInfoDTO officalInfoDTO = new OfficalInfoDTO();

				officalInfoDTO.setSupervisorID((String) hirachy.get(0));
				officalInfoDTO.setSupervisorName((String) hirachy.get(1));
				hirachylist.add(officalInfoDTO);
			}

		}
		return hirachylist;
	}

	/**
	 * @param empId
	 * @return
	 */
	/**
	 * @param empId
	 * @return
	 */
	public String searchEmpID(String empId) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pDAO.searchEmpID(empId);

	}

	/**
	 * @return
	 */
	/**
	 * @return
	 */
	public ArrayList getFundAccNo() {
		ArrayList bankacclist = new ArrayList();
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList acclist = pDAO.getFundAccNo();

			if (acclist != null) {
				for (int i = 0; i < acclist.size(); i++) {
					ArrayList fundList = (ArrayList) acclist.get(i);
					if (fundList != null) {
						FundDTO fundDTO = new FundDTO();
						fundDTO.setAccountNo((String) fundList.get(0));
						fundDTO.setFromDB(true);
						bankacclist.add(fundDTO);
					}

				}
			}
		} catch (Exception exception) {
			logger.error(exception.getMessage(),exception);
		}
		return bankacclist;

	}

	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getPropertyList(String employeeId,String locale) {
		EmpMgntDAO pDAO=null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<PropertyDTO> propList = null;
		PropertyDTO prop = null;
		SimpleDateFormat dbSDF = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat pageSDF = new SimpleDateFormat("dd/MM/yyyy"); 
		Date tmpDate;
		try {
			//PROPERTY_ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, REGISTRATION_ID, REGISTRATION_DATE
			ArrayList tempList = pDAO.getPropertyList(employeeId);
			ArrayList row = null;
			if(tempList != null) {
				propList = new ArrayList<PropertyDTO>(tempList.size());
				for (Object item : tempList) {
					row = (ArrayList) item;
					prop = new PropertyDTO();
					prop.setEmpId(employeeId);
					prop.setEmployeeId(employeeId);
					prop.setPropertyaddress((String) row.get(0));
					prop.setPropertycountry((String) row.get(1));
					prop.setStateList(this.getState(prop.getPropertycountry(), locale));
					prop.setPropertystate((String) row.get(2));
					prop.setDistrictList(this.getDistrict(prop.getPropertystate(), locale));
					prop.setPropertydistrict((String) row.get(3));
					prop.setPropertypostalcode((String) row.get(4));
					prop.setPropertyregid((String) row.get(5));
					prop.setPropertyshare((String) row.get(7));
					tmpDate = dbSDF.parse((String) row.get(6));
					prop.setPropertyregdate(pageSDF.format(tmpDate));
					
					propList.add(prop);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return propList;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getAssetList(String employeeId) {
		ArrayList<AssetDTO> assetList = null;
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AssetDTO asset = null;
		try {
			//ASSET_TYPE_ID, ASSET_DESCRIPTION, ASSET_VALUE, ASSET_STATUS
			ArrayList tempList = pDAO.getAssetList(employeeId);
			ArrayList row = null;
			if(tempList != null) {
				assetList = new ArrayList<AssetDTO>(tempList.size());
				for (Object item : tempList) {
					row = (ArrayList) item;
					asset = new AssetDTO();
					asset.setAssettype((String) row.get(0));
					asset.setAssestdetails((String) row.get(1));
					asset.setAssetValue((String) row.get(2));
					asset.setAssetStatus((String) row.get(3));
					asset.setEmployeeId(employeeId);
					assetList.add(asset);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return assetList;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getAcademicDetails(String empId) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList academicList = pDAO.getAcademicInfoList(empId);
		ArrayList academiclist = new ArrayList();
		for (int i = 0; i < academicList.size(); i++) {
			ArrayList academic = (ArrayList) academicList.get(i);
			if (academic != null) {
				AcademicDTO academicDTO = new AcademicDTO();

				academicDTO.setDegree((String) academic.get(0));
				academicDTO.setGrade((String) academic.get(1));
				academicDTO.setStream((String) academic.get(2));
				academicDTO.setPassingYear((String) academic.get(3));

				academiclist.add(academicDTO);

			}
		}
		return academiclist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getPreviousEmploymentDetails(String empId) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList prevempList = pDAO
				.getPrevEmpList(empId);
		ArrayList prevlist = new ArrayList();
		for (int i = 0; i < prevempList.size(); i++) {
			ArrayList row = (ArrayList) prevempList.get(i);
			if (row != null) {

				PrevEmpDTO prevdto = new PrevEmpDTO();
				// 0 ORGANIZATION_NAME
				// 1 EMP_DESIGNATION
				// 2 YEARS
				// 3 MONTHS
				// 4 COMPENSATION
				// 5 PF_ACCOUNT_NO
				// 6 PF_ACCOUNT_LOCATION
				// 7 REASON_FOR_SEPERATION
				// 8 TAX_DEDUCTION

				prevdto.setOrganization((String) row.get(0));
				prevdto.setDesignation((String) row.get(1));
				prevdto.setDurationYears((String) row.get(2));
				prevdto.setDurationMonths((String) row.get(3));
				prevdto.setCompensation((String) row.get(4));
				prevdto.setPfAccNo((String) row.get(5));
				prevdto.setPfAccLocation((String) row.get(6));
				prevdto.setReasonForSeparation((String) row.get(7));
				prevdto.setTaxDeductions((String) row.get(8));
				prevdto.setFromDate((String) row.get(9));
				prevdto.setToDate((String) row.get(10));
				prevlist.add(prevdto);

			}
		}
		return prevlist;

	}
	
	  /**
	 * @return
	 */
	private String userIDgenerator() 
		 {
	        String id = "USR" + new Date().getTime();
	        logger.debug("this is userIDgenerator() & value of ID " + id);
	        return id;
	    }
	
	/**
	 * @param eDTO
	 * @return
	 */
	public boolean insertEmpRegDetails(PersonalDetailsDTO eDTO) {
		EmpMgntDAO pDAO;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		logger.debug("BD Layer--Inside insertEmpRegDetails method....AA");
		boolean flag = false;
		String eDOB = null;
		String ed = (eDTO.getEmpDay() == null) ? null : eDTO.getEmpDay();
		String em = (eDTO.getEmpMonth() == null) ? null : eDTO.getEmpMonth();
		String ey = (eDTO.getEmpYear() == null) ? null : eDTO.getEmpYear();
		if (ed != null && em != null && ey != null) {
			eDOB = ed + em + ey;
		}
		String empMaster[] = new String[26];
		empMaster[0] = eDTO.getFirstName();
		empMaster[1] = eDTO.getMiddleName();
		empMaster[2] = eDTO.getLastName();
		empMaster[3] = eDTO.getGender();
		empMaster[4] = eDOB;
		empMaster[5] = eDTO.getFatherGaurdName();
		empMaster[6] = eDTO.getMotherName();
		empMaster[7] = eDTO.getReligion();
		empMaster[8] = eDTO.getCaste();
		empMaster[9] = eDTO.getNationality();
		empMaster[10] = eDTO.getPhoneNo();
		empMaster[11] = eDTO.getMobileNo();
		empMaster[12] = eDTO.getEmailId();
		empMaster[13] = eDTO.getPermAddress();
		empMaster[14] = eDTO.getPermCountry();
		empMaster[15] = eDTO.getPermState();
		empMaster[16] = eDTO.getPermDistrict();
		empMaster[17] = ""+eDTO.getPermPin();
		empMaster[18] = eDTO.getEmployeeId();
		logger.debug("Employee id is: " + eDTO.getEmployeeId());
		CryptoLibrary crypt = new CryptoLibrary();
		String passWord="igrstest@123";
		String encryptpswd = crypt.SHAencryption(passWord);
		empMaster[19] = encryptpswd;
//		empMaster[20] = "A";
		empMaster[20] = "P";
		empMaster[21] = "Admin";
		empMaster[22] = "";
		String userID = userIDgenerator();
		empMaster[23] = userID;
		empMaster[24] = eDTO.getHintQuestID();
		empMaster[25] = eDTO.getUserHintAnswer();
		
		String[] rUser = new String[3];
		rUser[0] = eDTO.getEmployeeId();
		rUser[1] = CommonConstant.DEFAULT_ROLE_GROUP;	
		rUser[2] = CommonConstant.REGISTERED_USER;
		try {
			pDAO = new EmpMgntDAO();
			flag = pDAO.insertUserRegDetails(empMaster, rUser);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}
	
	/**
	 * getHintQuestions
	 * Returns ArrayList of Hint Questions from Database.
	 *
	 * @return ArrayList
	 * @throws Exception 
	 *
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getHintQuestions() throws Exception{
		logger.debug("BD:   We are in getHintQuestions");
		EmpMgntDAO dao = null;
		try {
			dao = new EmpMgntDAO();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		ArrayList ret = dao.getHintQuestions();
		logger.debug("BD:   Function called :- getHintQuestions");
		ArrayList list = new ArrayList();

		if (ret != null) {
			logger.debug("BD:  ret is not null");
			for (int i = 0; i < ret.size(); i++) {
				ArrayList lst = (ArrayList) ret.get(i);
				PersonalDetailsDTO eDTO= new PersonalDetailsDTO();
				eDTO.setHintQuestID((String) lst.get(0));
				eDTO.setHintQuestion((String) lst.get(1));
				list.add(eDTO);
				logger.debug("BD:  Dto values are added to the list");
			}
		}
		return list;
	}

	/**
	 * @param nomineeList
	 * @param userId
	 * @param employeeId
	 * @param fundAccountNo
	 * @return
	 */
	public boolean insertNomineeDetails(ArrayList nomineeList,String userId, String employeeId,String fundAccountNo) {
		boolean ret = false;
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ret = pDAO.insertNomineeDetails(nomineeList, userId, employeeId,
				fundAccountNo);

		return ret;
	}

	/**
	 * @param fundAccountNo
	 * @return
	 */
	public String[] getFundTypeData(String fundAccountNo) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] data;
		data = pDAO.getFundTypeData(fundAccountNo);
		return data;
	}

	/**
	 * @param type
	 * @return
	 */
	public String getFundTypeName(String type) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pDAO.getFundTypeName(type);
	}
	
	/**
	 * @return
	 */
	public ArrayList<BankMstDTO> getBankMasterList() {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pDAO.getBankMasterList();
	}
	
	/**
	 * @return
	 */
	public ArrayList<RelativeTypeDTO> getRelativeMasterList(String locale) {
		ArrayList<RelativeTypeDTO> retVal = new ArrayList<RelativeTypeDTO>();
		ArrayList data, row;
		RelativeTypeDTO relative;
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		
			//RELATIVE_TYPE_ID, RELATIVE_TYPE_NAME 
			data = pDAO.getRelativeMasterList();
			for (Object item : data) {
				relative = new RelativeTypeDTO();
				row = (ArrayList) item;
				relative.setRelativeID((String) row.get(0));
				if(locale.equalsIgnoreCase("hi_IN")){
				relative.setRelativeType((String) row.get(2));
				}else{
				relative.setRelativeType((String) row.get(1));
				}
				retVal.add(relative);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * @param dayMasterList
	 * @param monthMasterList
	 * @param yearMasterList
	 */
	public PersonalForm populateDateLists(ArrayList<DropDownDTO> dayMasterList,
			ArrayList<DropDownDTO> monthMasterList, ArrayList<DropDownDTO> yearMasterList,Locale locale) {
		PersonalForm pForm=new PersonalForm();
		try {
			DropDownDTO option;
			String localeString=locale.toString();;
			DateFormatSymbols dateSymbols = new DateFormatSymbols();
			int currYear = Calendar.getInstance().get(Calendar.YEAR);
			int limit = currYear-150;
			if (dayMasterList.isEmpty()) {
				for (int iLoop = 1; iLoop <= 31; iLoop++) {
					option = new DropDownDTO();
					option.setOptionLabel(Integer.toString(iLoop));
					option.setOptionValue(option.getOptionLabel());
					dayMasterList.add(option);
				}
			}
			if (monthMasterList.isEmpty()) {

				String[] months = DateFormatSymbols.getInstance(locale).getShortMonths();
				for (int iLoop = 0; iLoop < months.length; iLoop++) {
					String month = months[iLoop];
					if ("".equals(month) == false) {
						option = new DropDownDTO();
						option.setOptionLabel(month);
						if(localeString.equalsIgnoreCase("hi_IN")){
							if(option.getOptionLabel().equals("जनवरी")){
								option.setOptionValue("Jan");
								}
							if(option.getOptionLabel().equals("फ़रवरी")){
								option.setOptionValue("Feb");
								}
							if(option.getOptionLabel().equals("मार्च")){
								option.setOptionValue("Mar");
								}
							
							if(option.getOptionLabel().equals("अप्रैल")){
								option.setOptionValue("Apr");
								}
							if(option.getOptionLabel().equals("मई")){
								option.setOptionValue("May");
								}
							if(option.getOptionLabel().equals("जून")){
								option.setOptionValue("Jun");
								}
							if(option.getOptionLabel().equals("जुलाई")){
								option.setOptionValue("Jul");
								}
							if(option.getOptionLabel().equals("अगस्त")){
								option.setOptionValue("Aug");
								}
							if(option.getOptionLabel().equals("सितंबर")){
								option.setOptionValue("Sep");
								}
							if(option.getOptionLabel().equals("अक्‍तूबर")){
								option.setOptionValue("Oct");
								}
							if(option.getOptionLabel().equals("नवंबर")){
								option.setOptionValue("Nov");
								}
							if(option.getOptionLabel().equals("दिसंबर")){
								option.setOptionValue("Dec");
								}
						}
						else{
							option.setOptionValue(option.getOptionLabel());
						}
						monthMasterList.add(option);
					}

				}
			}
			if (yearMasterList.isEmpty()) {
				for (int iLoop = currYear; iLoop >= (limit); iLoop--) {
					option = new DropDownDTO();
					option.setOptionLabel(Integer.toString(iLoop));
					option.setOptionValue(option.getOptionLabel());
					yearMasterList.add(option);
				}
			}
			pForm.setDayMasterList(dayMasterList);
			pForm.setMonthMasterList(monthMasterList);
			pForm.setYearMasterList(yearMasterList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return  pForm;
	}
	
	/**
	 * @param personalDTO
	 * @param userID
	 * @return
	 */
	public boolean insertEmpPersonalFamilyDetails(
			PersonalDetailsDTO personalDTO, String userID) {
		boolean retVal = false;
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (pDAO == null) {
				pDAO = new EmpMgntDAO();
			}
			retVal = pDAO.insertEmpFamilyData(personalDTO, userID);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * @param personDTO
	 * @param userID
	 * @return
	 */
	public boolean updateEmployeeData(PersonalDetailsDTO personDTO,
			String userID) {
		EmpMgntDAO pDAO = null;
		try {
			pDAO = new EmpMgntDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean retVal = false;
		try {
			if (pDAO == null) {
				pDAO = new EmpMgntDAO();
			}
			retVal = pDAO.updateEmployeeData(personDTO, userID);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}

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

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wipro.igrs.empmgmt.dao.EmpmgmtViewDAO;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.ChildDetailsDTO;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;

/**
* 
* EmpmgmtViewBD.java <br>
* EmpmgmtViewBD <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmpmgmtViewBD {

	// EmpmgmtViewBD employeeViewBD = new EmpmgmtViewBD();
	private EmpmgmtViewDAO EmployeeViewDAO = new EmpmgmtViewDAO();
	private Logger logger = (Logger) Logger.getLogger(EmpmgmtViewBD.class);
	PersonalDetailsDTO pDTO = null;
	ChildDetailsDTO cDTO = null;

	/**
	 * @param empId
	 * @return
	 */
	public PersonalDetailsDTO getPersonalDetails(String empId,String locale) {

		ArrayList personalList = EmployeeViewDAO.getPersonalDetails(empId.toUpperCase());
		// ArrayList _personalList = new ArrayList();
		PersonalDetailsDTO personalDTO = new PersonalDetailsDTO();
		ArrayList personal = (ArrayList) personalList.get(0);
		if(locale.equalsIgnoreCase("hi_IN")){
			if (personalList != null && personalList.size()>0) {

				personalDTO.setEmployeeId((String) personal.get(0));
				personalDTO.setFirstName((String) personal.get(1));
				personalDTO.setMiddleName((String) personal.get(2));
				personalDTO.setLastName((String) personal.get(3));
				String gen=(String) personal.get(4);
				String phy=(String) personal.get(33);
				if(gen.equalsIgnoreCase("M")){
					personalDTO.setGenderLabel("पुरुष");
				}else{
					personalDTO.setGenderLabel("महिला");
				}
				if(gen.equalsIgnoreCase("No")){
					personalDTO.setPhysicalChallengeLabel("नहीं");
				}else{
					personalDTO.setPhysicalChallengeLabel("हां");
				}
				personalDTO.setGender((String) personal.get(4));
				personalDTO.setDateOfBirth((String) personal.get(5));
				personalDTO.setDateOfBirthWords((String) personal.get(6));
				personalDTO.setHeight((String) personal.get(7));
				personalDTO.setIdentificationMarks((String) personal.get(8));
//				personalDTO.setPhysicallyChallanged((String) personal.get(9));
				personalDTO.setChkPhysically((String) personal.get(9));
				personalDTO.setMaritalStatus((String) personal.get(10));
				personalDTO.setMaritalStatusLabel(getMaritalStatusLabel(personalDTO.getMaritalStatus(),locale));
				personalDTO.setReligion((String) personal.get(34));
				personalDTO.setCaste((String) personal.get(35));
				personalDTO.setFatherGaurdName((String) personal.get(13));
				personalDTO.setMotherName((String) personal.get(14));
				personalDTO.setHomeDistrict((String) personal.get(15));
				personalDTO.setHomeDistrictName(getDistrictName(personalDTO.getHomeDistrict(),locale));
				personalDTO.setPermAddress((String) personal.get(16));
				personalDTO.setPermCountry((String) personal.get(37));
				personalDTO.setPermState((String) personal.get(38));
				personalDTO.setPermDistrict((String) personal.get(39));
				personalDTO.setNationality((String) personal.get(20));
				personalDTO.setPermPin((String) personal.get(21));
				personalDTO.setCurrAddress((String) personal.get(22));
				personalDTO.setCurrCountry((String) personal.get(40));
				personalDTO.setCurrState((String) personal.get(41));
				personalDTO.setCurrDistrict((String) personal.get(42));
				personalDTO.setCurrPin((String) personal.get(26));
				personalDTO.setPhoneNo((String) personal.get(27));
				personalDTO.setMobileNo((String) personal.get(28));
				personalDTO.setEmailId((String) personal.get(29));
				personalDTO.setReferenceId((String) personal.get(30));
				personalDTO.setPhysicallyChallanged((String) personal.get(31));
				personalDTO.setHomeState((String) personal.get(43));
				personalDTO.setHomeStateId((String) personal.get(43));
				personalDTO.setHomeStateName(getHomeStateName(personalDTO.getHomeState(),locale));
				//personalDTO.setGenderLabel((String) personal.get(32));
			//	personalDTO.setPhysicalChallengeLabel((String) personal.get(33));
				
			}
		}else{

		if (personalList != null && personalList.size()>0) {

			personalDTO.setEmployeeId((String) personal.get(0));
			personalDTO.setFirstName((String) personal.get(1));
			personalDTO.setMiddleName((String) personal.get(2));
			personalDTO.setLastName((String) personal.get(3));
			personalDTO.setGender((String) personal.get(4));
			personalDTO.setDateOfBirth((String) personal.get(5));
			personalDTO.setDateOfBirthWords((String) personal.get(6));
			personalDTO.setHeight((String) personal.get(7));
			personalDTO.setIdentificationMarks((String) personal.get(8));
//			personalDTO.setPhysicallyChallanged((String) personal.get(9));
			personalDTO.setChkPhysically((String) personal.get(9));
			personalDTO.setMaritalStatus((String) personal.get(10));
			personalDTO.setMaritalStatusLabel(getMaritalStatusLabel(personalDTO.getMaritalStatus(),locale));
			personalDTO.setReligion((String) personal.get(11));
			personalDTO.setCaste((String) personal.get(12));
			personalDTO.setFatherGaurdName((String) personal.get(13));
			personalDTO.setMotherName((String) personal.get(14));
			personalDTO.setHomeDistrict((String) personal.get(15));
			personalDTO.setHomeDistrictName(getDistrictName(personalDTO.getHomeDistrict(),locale));
			personalDTO.setPermAddress((String) personal.get(16));
			personalDTO.setPermCountry((String) personal.get(17));
			personalDTO.setPermState((String) personal.get(18));
			personalDTO.setPermDistrict((String) personal.get(19));
			personalDTO.setNationality((String) personal.get(20));
			personalDTO.setPermPin((String) personal.get(21));
			personalDTO.setCurrAddress((String) personal.get(22));
			personalDTO.setCurrCountry((String) personal.get(23));
			personalDTO.setCurrState((String) personal.get(24));
			personalDTO.setCurrDistrict((String) personal.get(25));
			personalDTO.setCurrPin((String) personal.get(26));
			personalDTO.setPhoneNo((String) personal.get(27));
			personalDTO.setMobileNo((String) personal.get(28));
			personalDTO.setEmailId((String) personal.get(29));
			personalDTO.setReferenceId((String) personal.get(30));
			personalDTO.setPhysicallyChallanged((String) personal.get(31));
			personalDTO.setGenderLabel((String) personal.get(32));
			personalDTO.setPhysicalChallengeLabel((String) personal.get(33));
			personalDTO.setHomeState((String) personal.get(43));

			personalDTO.setHomeStateId((String) personal.get(43));
			personalDTO.setHomeStateName(getHomeStateName(personalDTO.getHomeStateId(),locale));
		}
		}
		return personalDTO;

	}

	/**
	 * @param homeDistrict
	 * @return
	 */
	private String getDistrictName(String homeDistrict,String locale) {
		return EmployeeViewDAO.getDistrictName(homeDistrict,locale);
	}
	/**
	 * @param homeDistrict
	 * @return
	 */
	private String getHomeStateName(String homeState,String locale) {
		return EmployeeViewDAO.getHomeStateName(homeState,locale);
	}

	/**
	 * @param empId
	 * @return
	 */
	public boolean getEmployeeID(String empId) {
		boolean flag = false;
		try {
			ArrayList emplist = EmployeeViewDAO.getEmployeeID(empId.toUpperCase());
			if (emplist.size() > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return flag;
	}

	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList getChildDetails(String employeeId) {
		ArrayList personalList = EmployeeViewDAO.getChildDetails(employeeId);
		ArrayList _personalList = new ArrayList();
		for (int i = 0; i < personalList.size(); i++) {
			ArrayList personal = (ArrayList) personalList.get(i);

			if (personalList != null) {
				ChildDetailsDTO childDTO = new ChildDetailsDTO();
				childDTO.setChildName((String) personal.get(0));
				childDTO.setChildDateOfBirth((String) personal.get(1));
				childDTO.setChildGender((String) personal.get(2));

				_personalList.add(childDTO);
			}

		}
		return _personalList;

	}
	
	/**
	 * @param employeeId
	 * @return
	 */
	public ArrayList<FamilyMemberDTO> getFamilyMemberDetails(String employeeId,String locale) {
		ArrayList<FamilyMemberDTO> retVal = new ArrayList<FamilyMemberDTO>();
		try {
			if(EmployeeViewDAO == null) {
				EmployeeViewDAO = new EmpmgmtViewDAO();
			}
			retVal = EmployeeViewDAO.getFamilyMemberDetails(employeeId,locale);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal ;

	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getPropertyDetails(String empId) {

		ArrayList propertyList = EmployeeViewDAO.getPropertyDetails(empId);
		ArrayList propList = new ArrayList();
		for (int i = 0; i < propertyList.size(); i++) {
			ArrayList propertylist = (ArrayList) propertyList.get(i);
			if (propertyList != null) {
				PropertyDTO propertydto = new PropertyDTO();
				propertydto.setPropertyaddress((String) propertylist.get(0));
				propertydto.setPropertycountry((String) propertylist.get(1));
				propertydto.setPropertystate((String) propertylist.get(2));
				propertydto.setPropertydistrict((String) propertylist.get(3));
				propertydto.setPropertypostalcode((String) propertylist.get(4));
				propertydto.setPropertyshare((String) propertylist.get(5));
				propertydto.setPropertyregid((String) propertylist.get(6));
				propertydto.setPropertyregdate((String) propertylist.get(7));

				propList.add(propertydto);

			}
		}

		return propList;

	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getAssetDetails(String empId) {
		ArrayList assetList = EmployeeViewDAO.getAssetDetails(empId);

		ArrayList assetlist = new ArrayList();
		for (int i = 0; i < assetList.size(); i++) {
			ArrayList asset = (ArrayList) assetList.get(i);
			if (assetList != null) {
				AssetDTO assetdto = new AssetDTO();
				assetdto.setAssetTypeName((String) asset.get(0));
				assetdto.setAssestdetails((String) asset.get(1));
				assetdto.setAssetValue((String) asset.get(2));

				assetlist.add(assetdto);

			}
		}

		return assetlist;

	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getAcademicDetails(String empId) {
		ArrayList academicList = EmployeeViewDAO.getAcademicDetails(empId);
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
		ArrayList prevempList = EmployeeViewDAO
				.getPreviousEmploymentDetails(empId);
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
	 * @param empId
	 * @return
	 */
	public ArrayList getTraingDetails(String empId) {
		ArrayList trainingList = EmployeeViewDAO.getTraingDetails(empId);

		ArrayList trainlist = new ArrayList();
		for (int i = 0; i < trainingList.size(); i++) {
			ArrayList training = (ArrayList) trainingList.get(i);
			if (training != null) {

				DeptTrainingDTO deptTrainDTO = new DeptTrainingDTO();
				deptTrainDTO.setTrainingNo((String) training.get(0));
				deptTrainDTO.setOrganizingAuthority((String) training.get(1));
				deptTrainDTO.setTrainingStartDate((String) training.get(2));
				deptTrainDTO.setTrainingEndDate((String) training.get(3));
				deptTrainDTO.setPlaceOfTraining((String) training.get(4));
				deptTrainDTO.setAuthorisingAuthority((String) training.get(5));
				deptTrainDTO.setAuthorizationDate((String) training.get(6));
				deptTrainDTO.setTrainingLevel((String) training.get(7));
				deptTrainDTO.setOrgainizingBody((String) training.get(8));
				deptTrainDTO.setTrainingCost((String) training.get(9));
				deptTrainDTO.setTrainingName((String) training.get(10));
				deptTrainDTO.setTrainingComments((String) training.get(11));
				deptTrainDTO.setFinancialYear((String) training.get(12));
				deptTrainDTO.setTrainingResult((String) training.get(13));

				trainlist.add(deptTrainDTO);

			}
		}

		return trainlist;

	}

	/**
	 * @param pDTO
	 * @return
	 * @throws Exception
	 */
	public boolean getPenality_Empid(PersonalDetailsDTO pDTO) throws Exception {
		boolean result = false;
		ArrayList _list = null;
		ArrayList list = EmployeeViewDAO.get_Empid();
		Outer: for (int i = 0; i < list.size(); i++) {
			_list = (ArrayList) list.get(i);
			for (int j = 0; j < _list.size(); j++) {
				String empid = (String) _list.get(j);
				if (empid.equals(pDTO.getEmployeeId())) {
					result = true;
					break Outer;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getExamDetails(String empId) {
		ArrayList examList = EmployeeViewDAO.getExamDetails(empId);
		ArrayList examlist = new ArrayList();
		for (int i = 0; i < examList.size(); i++) {

			ArrayList exam = (ArrayList) examList.get(i);
			if (examList != null) {

				DeptExamDTO deptExamDTO = new DeptExamDTO();

				deptExamDTO.setNameOfExam((String) exam.get(0));
				deptExamDTO.setExamDate((String) exam.get(1));
				deptExamDTO.setExamsOrganizingAuthority((String) exam.get(2));
				deptExamDTO.setPlaceOfExam((String) exam.get(3));
				deptExamDTO.setResult((String) exam.get(4));
				deptExamDTO.setResultDate((String) exam.get(5));
				deptExamDTO.setExamsComments((String) exam.get(6));

				examlist.add(deptExamDTO);
			}
		}

		return examlist;

	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getBankDetails(String empId) {
		ArrayList bankList = EmployeeViewDAO.getBankDetails(empId);
		ArrayList banklist = new ArrayList();
		for (int i = 0; i < bankList.size(); i++) {

			ArrayList bank = (ArrayList) bankList.get(i);
			if (bankList != null) {

				BankDTO bankDTO = new BankDTO();
				bankDTO.setBankName((String) bank.get(0));
				bankDTO.setBankBranch((String) bank.get(1));
				bankDTO.setBankAddress((String) bank.get(2));
				bankDTO.setNameAsInBank((String) bank.get(3));
				bankDTO.setPanNo((String) bank.get(4));
				bankDTO.setBankAccountNo((String) bank.get(5));

				banklist.add(bankDTO);

			}
		}

		return banklist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getFundDetails(String empId) {
		ArrayList fundList = EmployeeViewDAO.getFundDetails(empId);
		ArrayList fundlist = new ArrayList();
		for (int i = 0; i < fundList.size(); i++) {

			ArrayList fund = (ArrayList) fundList.get(i);
			if (fundList != null) {
				FundDTO fundDTO = new FundDTO();
				fundDTO.setType((String) fund.get(0));
				fundDTO.setAccountNo((String) fund.get(1));
				fundDTO.setAccountLocation((String) fund.get(2));

				fundlist.add(fundDTO);

			}

		}

		return fundlist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getNomineeDetails(String empId) {
		ArrayList nomineeList = EmployeeViewDAO.getNomineeDetails(empId);
		ArrayList nomineelist = new ArrayList();
		for (int i = 0; i < nomineeList.size(); i++) {

			ArrayList nominee = (ArrayList) nomineeList.get(i);
			if (nomineeList != null) {
				NomineeDTO nomineeDTO = new NomineeDTO();

				nomineeDTO.setFundTypeId((String) nominee.get(0));
				nomineeDTO.setNomineeName((String) nominee.get(1));
				nomineeDTO.setRelationWithNominee((String) nominee.get(2));
				nomineeDTO.setNomineeAddress((String) nominee.get(3));
				nomineeDTO.setNomineeAge((String) nominee.get(4));
				nomineeDTO.setStrAccountNumber((String) nominee.get(5));
				nomineelist.add(nomineeDTO);

			}
		}

		return nomineelist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getOfficialDetails(String empId,String locale) {
		ArrayList officeList = EmployeeViewDAO.getOfficialDetails(empId);
		ArrayList officelist = new ArrayList();
		DropDownDTO ddto= new DropDownDTO();

		if(locale.equalsIgnoreCase("hi_IN")){
			for (int i = 0; i < officeList.size(); i++) {

				ArrayList office = (ArrayList) officeList.get(i);
				if (officeList != null) {

					OfficalInfoDTO officeDTO = new OfficalInfoDTO();
					officeDTO.setOfficiating((String) office.get(0));
					if("O".equals(officeDTO.getOfficiating())) {
						officeDTO.setOfficiatingText("कार्यवाहक");
					}
					if("S".equals(officeDTO.getOfficiating())) {
						officeDTO.setOfficiatingText("मौलिक");
					}
					officeDTO.setSubstantive(officeDTO.getOfficiating());
					officeDTO.setClass1Text((String) office.get(13));
					officeDTO.setDesignationText((String) office.get(14));
					officeDTO.setDesiOffictText((String) office.get(15));
					officeDTO.setDateOfJoining((String) office.get(4));
					officeDTO.setDateOfSaperation((String) office.get(5));
					officeDTO.setCompEmplRefrence((String) office.get(6));
					officeDTO.setEmployeeStatus((String) office.get(7));
					ddto.setOptionValue((String) office.get(7));
					officeDTO.setClass1((String) office.get(8));
					officeDTO.setDesignation((String) office.get(10));
					officeDTO.setDesignationid((String) office.get(10));
					officeDTO.setDesiOffic((String) office.get(9));
					officeDTO.setSupervisorID((String) office.get(11));
					officeDTO.setDateFirstGovtService((String) office.get(12));
//					officeDTO.setSupervisorName((String) office.get(11));
					officelist.add(officeDTO);

				}
			}
		}else{

		for (int i = 0; i < officeList.size(); i++) {

			ArrayList office = (ArrayList) officeList.get(i);
			if (officeList != null) {

				OfficalInfoDTO officeDTO = new OfficalInfoDTO();

				officeDTO.setOfficiating((String) office.get(0));
				if("O".equals(officeDTO.getOfficiating())) {
					officeDTO.setOfficiatingText("Officiating");
				}
				if("S".equals(officeDTO.getOfficiating())) {
					officeDTO.setOfficiatingText("Substantive");
				}
				officeDTO.setSubstantive(officeDTO.getOfficiating());
				officeDTO.setClass1Text((String) office.get(1));
				officeDTO.setDesignationText((String) office.get(2));
				officeDTO.setDesiOffictText((String) office.get(3));
				officeDTO.setDateOfJoining((String) office.get(4));
				officeDTO.setDateOfSaperation((String) office.get(5));
				officeDTO.setCompEmplRefrence((String) office.get(6));
				officeDTO.setEmployeeStatus((String) office.get(7));
				officeDTO.setClass1((String) office.get(8));
				officeDTO.setDesignation((String) office.get(10));
				officeDTO.setDesignationid((String) office.get(10));
				officeDTO.setDesiOffic((String) office.get(9));
				officeDTO.setSupervisorID((String) office.get(11));
				officeDTO.setDateFirstGovtService((String) office.get(12));
//				officeDTO.setSupervisorName((String) office.get(11));
				officelist.add(officeDTO);
				ddto.setOptionValue((String) office.get(7));
			}
		}
		}
		return officelist;
	}

	
	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getReportingHierarchyDetails(String empId) {
		ArrayList reportList = EmployeeViewDAO
				.getReportingHierarchyDetails(empId);
		ArrayList reportlist = new ArrayList();

		for (int i = 0; i < reportList.size(); i++) {
			ArrayList report = (ArrayList) reportList.get(i);
			if (report != null) {

				OfficalInfoDTO officeDTO = new OfficalInfoDTO();
				officeDTO.setFirstName((String) report.get(0));
				officeDTO.setDesignation((String) report.get(1));
				officeDTO.setSupervisorID((String) report.get(2));
				reportlist.add(officeDTO);
			}
		}

		return reportlist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getServiceDetails(String empId) {
		ArrayList serviceList = EmployeeViewDAO.getServiceDetails(empId);
		ArrayList servicelist = new ArrayList();

		for (int i = 0; i < serviceList.size(); i++) {
			ArrayList service = (ArrayList) serviceList.get(i);
			if (serviceList != null) {

				ServiceVerificationDTO serviceDTO = new ServiceVerificationDTO();
				serviceDTO.setVerifyingAuthority((String) service.get(0));
				serviceDTO.setDateOfVerivication((String) service.get(1));
				serviceDTO.setComments((String) service.get(2));

				servicelist.add(serviceDTO);
			}
		}
		return servicelist;
	}

	/**
	 * @param empId
	 * @return
	 */
	public ArrayList getDocumentDetails(String empId,String locale) {

		ArrayList documentList = EmployeeViewDAO.getDocumentDetails(empId);
		ArrayList documentlist = new ArrayList();

		for (int i = 0; i < documentList.size(); i++) {
			ArrayList document = (ArrayList) documentList.get(i);
			if (documentList != null) {
				OfficalInfoDTO officeDTO = new OfficalInfoDTO();
				officeDTO.setDocumentid((String) document.get(0));
				
				if(locale.equalsIgnoreCase("hi_IN")){
					officeDTO.setDocumenttype((String) document.get(3));	
				}else{
					officeDTO.setDocumenttype((String) document.get(1));	
				}

				officeDTO.setDocumentName((String) document.get(2));
				officeDTO.setDocumentContents((byte[]) document.get(4));
				documentlist.add(officeDTO);
			}

		}
		return documentlist;
	}

	/**
	 * @param docID
	 * @param res
	 * @param fileName
	 */
	public void downloadEmpDocument(String docID, HttpServletResponse res, String fileName) {
		EmployeeViewDAO.downloadEmpDocument(docID, res, fileName);
	}
	
	/**
	 * @param empId
	 * @return
	 */
	public BankDTO getEmpBankDetails(String empId) {
		BankDTO retVal = null;
		retVal= EmployeeViewDAO.getEmpBankDetails(empId);
		return retVal;
	}
	
	/**
	 * @param status
	 * @return
	 */
	private String getMaritalStatusLabel(String status,String locale) {
		String label = "";
		HashMap<String, String> data = new HashMap<String, String>();
		if(locale.equalsIgnoreCase("hi_IN")){
			data.put("M", "विवाहित");
			data.put("S", "सिंग्ल");
			data.put("W", "विधवा");
			data.put("D", "तलाक्शुदा");
		}else{
			data.put("M", "Married");
			data.put("S", "Single");
			data.put("W", "Widowed");
			data.put("D", "Divorcees");
		}

	
		
		if(data.containsKey(status)) {
			label = data.get(status);
		}
		return label;
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public ArrayList<PersonalDetailsDTO> searchEmployees(String firstName, String lastName) {
		ArrayList<PersonalDetailsDTO> retVal = new ArrayList<PersonalDetailsDTO>();
		try {
			ArrayList dataSet = EmployeeViewDAO.searchEmployees(firstName, lastName);
			ArrayList row;
			PersonalDetailsDTO searchItem;
			//EMP_ID, FIRST_NAME, LAST_NAME
			for (Object item : dataSet) {
				row = (ArrayList) item;
				searchItem = new PersonalDetailsDTO();
				searchItem.setEmployeeId((String) row.get(0));
				searchItem.setFirstName((String) row.get(1));
				searchItem.setLastName((String) row.get(2));
				retVal.add(searchItem);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}
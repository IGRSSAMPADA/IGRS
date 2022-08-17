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
package com.wipro.igrs.empmgmt.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.CommonBD;
import com.wipro.igrs.empmgmt.bd.EmpmgmtViewBD;
import com.wipro.igrs.empmgmt.dto.AddressDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.RelativeTypeDTO;
import com.wipro.igrs.empmgmt.form.PersonalForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;
import com.wipro.igrs.util.DateToWords;

/**
* 
* EmpMgmtUpdateAction.java <br>
* EmpMgmtUpdateAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes" })
public class EmpMgmtUpdateAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(EmpMgmtUpdateAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		EmpMgmtRule empMgmtRule = null;
		
		String FORWARDPAGE = "updateEmpPage";
		CommonBD commonBD = null;
		try {
		
			ArrayList religionList = new ArrayList();
			ArrayList homeDistrictList = new ArrayList();
			ArrayList casteList = new ArrayList();
			ArrayList permCountrylist = new ArrayList();
			ArrayList permStatelist = new ArrayList();
			ArrayList permDistrictlist = new ArrayList();
			ArrayList currCountrylist = new ArrayList();
			ArrayList currStatelist = new ArrayList();
			ArrayList currDistrictlist = new ArrayList();
			ArrayList homeStatelist = new ArrayList();
			ArrayList<RelativeTypeDTO> relativeMasterList;
			ArrayList allCountries, allStates, allDistricts;
			PersonalForm personForm = (PersonalForm) form;
			PersonalDetailsDTO personDTO = personForm.getPersonalDTO();
			String formName = personForm.getFormName();
			splitDateValues(personDTO);
			commonBD = new CommonBD();
			String locale="";
			Locale currentLocale=new Locale(locale);
			if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
					currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
					locale=currentLocale.toString();
					
				}
			String label = request.getParameter("label");
			PersonalForm pF=populateDateLists(currentLocale);
			if ("enterUpdateEmployee".equals(formName)) {
				religionList = commonBD.getReligion(locale);
				homeDistrictList = commonBD.getHomeDistrict(locale);
				casteList = commonBD.getCaste(locale);
				allCountries = commonBD.getCountryList(locale);
				allStates = commonBD.getStateList(locale);
				allDistricts = commonBD.getDistrictList(locale);
				
				permCountrylist = (ArrayList) allCountries.clone();
				currCountrylist = (ArrayList) permCountrylist.clone();
				relativeMasterList = commonBD.getRelativeMasterList(locale);
				personForm.setRelativeMasterList(relativeMasterList);
				
				setCountryValue(personDTO, allCountries);
				setStateValue(personDTO, allStates);
				setDistrictValue(personDTO, allDistricts);
				setFamilyDataValues(personDTO.getFamilyMembers(), personForm.getMonthMasterList());
				
				allCountries.clear();
				allStates.clear();
				allDistricts.clear();
				
				personForm.getPermAddressDTO().setCountryList(permCountrylist);
				permStatelist = commonBD.getState(personForm.getPersonalDTO().getPermCountry(),locale);
				personForm.getPermAddressDTO().setStateList(permStatelist);				
				permDistrictlist = commonBD.getDistrict(personForm.getPersonalDTO().getPermState(),locale);
				personForm.getPermAddressDTO().setDistrictList(permDistrictlist);
				homeStatelist = commonBD.getHomeState(locale);
				personForm.getPersonalDTO().setHomeStateList(homeStatelist);
				//pDTO.setHomeStateList(homeStatelist);
				personForm.getCurrAddressDTO().setCountryList(currCountrylist);
				currStatelist = commonBD.getState(personForm.getPersonalDTO().getCurrCountry(),locale);
				personForm.getCurrAddressDTO().setStateList(currStatelist);				
				currDistrictlist = commonBD.getDistrict(personForm.getPersonalDTO().getPermState(),locale);
				personForm.getCurrAddressDTO().setDistrictList(currDistrictlist);
				personForm.setDayMasterList(pF.getDayMasterList());
				personForm.setMonthMasterList(pF.getMonthMasterList());
				personForm.setYearMasterList(pF.getYearMasterList());
				personDTO.setReligionList(religionList);
				personDTO.setHomeDistrictList(homeDistrictList);
				personDTO.setHomeStateList(homeStatelist);
				personDTO.setCasteList(casteList);

				setReligionValue(personDTO);
				setHomeDistrictValue(personDTO);
				setCasteValue(personDTO);

				
				
				session.setAttribute("religionBean", personDTO);
				session.setAttribute("homeDistrictBean", personDTO);
				session.setAttribute("homeStateBean", personDTO);
				session.setAttribute("casteBean", personDTO);
				session.setAttribute("permAddressBean", personForm.getPermAddressDTO());
				session.setAttribute("permStateBean", personForm.getPermAddressDTO());
				session.setAttribute("permDistrictBean", personForm.getPermAddressDTO());
				session.setAttribute("currAddressBean", personForm.getCurrAddressDTO());
				session.setAttribute("currStateBean", personForm.getCurrAddressDTO());
				session.setAttribute("currDistrictBean", personForm.getCurrAddressDTO());
				// FORWARDPAGE = "updateEmpPage";
			} else if ("populateDOBWords".equals(formName)) {
				// dayVal="+dayVal+"&monVal="+monVal+"&yerVal="+yerVal
				String dayVal = request.getParameter("dayVal");
				String monVal = request.getParameter("monVal");
				String yerVal = request.getParameter("yerVal");
				personDTO.setEmpDay(dayVal);
				personDTO.setEmpMonth(monVal);
				personDTO.setEmpYear(yerVal);
				String dobInWords = DateToWords.convertDateToWords(
						personDTO.getEmpDay(), personDTO.getEmpMonth(),
						personDTO.getEmpYear());
				personDTO.setDateOfBirthWords(dobInWords);
			} 
			else if ("permhomedistrict".equals(formName)) {
					commonBD = new CommonBD();
					homeDistrictList = commonBD.getHomeDistrictEmp(personForm
							.getPersonalDTO().getHomeState(),locale);
					personForm.getPersonalDTO().setHomeDistrictList(homeDistrictList);
					session.setAttribute("homeDistrictBean", personForm.getPersonalDTO());
			
			}else if ("permstate".equals(formName)) {
				permStatelist = commonBD.getState(personForm.getPersonalDTO()
						.getPermCountry(),locale);
				personForm.getPermAddressDTO().setStateList(permStatelist);
				session.setAttribute("permStateBean",
						personForm.getPermAddressDTO());
				session.removeAttribute("permDistrictBean");
			} else if ("permdistrict".equals(formName)) {
				permDistrictlist = commonBD.getDistrict(personForm.getPersonalDTO()
						.getPermState(),locale);
				personForm.getPermAddressDTO().setDistrictList(permDistrictlist);
				session.setAttribute("permDistrictBean",
						personForm.getPermAddressDTO());
			} else if ("currstate".equals(formName)) {
				currStatelist = commonBD.getState(personForm.getPersonalDTO()
						.getCurrCountry(),locale);
				personForm.getCurrAddressDTO().setStateList(currStatelist);
				session.setAttribute("currStateBean",
						personForm.getCurrAddressDTO());
				session.removeAttribute("currDistrictBean");
			} else if ("currdistrict".equals(formName)) {
				currDistrictlist = commonBD.getDistrict(personForm.getPersonalDTO()
						.getCurrState(),locale);
				personForm.getCurrAddressDTO().setDistrictList(currDistrictlist);
				session.setAttribute("currDistrictBean",
						personForm.getCurrAddressDTO());
			} else if ("copyPermAddress".equals(formName)) {
				AddressDTO permAddr = personForm.getPermAddressDTO();
				AddressDTO currAddr = personForm.getCurrAddressDTO();
				currAddr.setCountryId(permAddr.getCountryId());
				if(currAddr.getCountryId() == null || ("".equals(currAddr.getCountryId().trim()))) {
					currAddr.setCountryId(personDTO.getCurrCountry());
					permAddr.setCountryId(personDTO.getCurrCountry());
				}
				currAddr.setCountryList(permAddr.getCountryList());
				currAddr.setCountryName(permAddr.getCountryName());
				currAddr.setStateId(permAddr.getStateId());
				if(currAddr.getStateId() == null || ("".equals(currAddr.getStateId().trim()))) {
					currAddr.setStateId(personDTO.getCurrState());
					permAddr.setStateId(personDTO.getCurrState());
				}
				currAddr.setStateList(permAddr.getStateList());
				currAddr.setStateName(permAddr.getStateName());
				currAddr.setDistrictId(permAddr.getDistrictId());
				if(currAddr.getDistrictId() == null || ("".equals(currAddr.getDistrictId().trim()))) {
					currAddr.setDistrictId(personDTO.getCurrDistrict());
					permAddr.setDistrictId(personDTO.getCurrDistrict());
				}
				currAddr.setDistrictList(permAddr.getDistrictList());
				currAddr.setDistrictName(permAddr.getDistrictName());
				personDTO.setCurrAddress(personDTO.getPermAddress());
				personDTO.setCurrCountry(personDTO.getPermCountry());
				personDTO.setCurrCountryList(personDTO.getPermCountryList());
				personDTO.setCurrState(personDTO.getPermState());
				personDTO.setCurrStateList(personDTO.getPermStateList());
				personDTO.setCurrDistrict(personDTO.getPermDistrict());
				personDTO.setCurrDistrictList(personDTO.getPermDistrictList());
				personDTO.setCurrPin(personDTO.getPermPin());
				personForm.setCurrAddressDTO(currAddr);
				
				session.setAttribute("currAddressBean", currAddr);
				session.setAttribute("currStateBean", currAddr);
				session.setAttribute("currDistrictBean", currAddr);
			} else if ("addFamilyRow".equals(formName)) {
				ArrayList<FamilyMemberDTO> familyMembers = personDTO.getFamilyMembers();
				refreshFamilyData(familyMembers, request, personForm.getRelativeMasterList());
				familyMembers.add(new FamilyMemberDTO());
			} else if ("delFamilyRow".equals(formName)) {
				int index = Integer.parseInt(request.getParameter("selectedIndex"));
				ArrayList<FamilyMemberDTO> familyMembers = personDTO.getFamilyMembers();
				refreshFamilyData(familyMembers, request, personForm.getRelativeMasterList());
				familyMembers.remove(index);
			} else if ("saveUpdateEmployee".equals(formName)) {
				ArrayList<FamilyMemberDTO> familyMembers = personDTO.getFamilyMembers();
				refreshFamilyData(familyMembers, request, personForm.getRelativeMasterList());
				empMgmtRule = new EmpMgmtRule();
				ArrayList errorList = empMgmtRule.validatePersonalDetails(personDTO,
						null, ((Date)session.getAttribute("currSysDate")));
				if (empMgmtRule.isError()) {
					if(locale.equalsIgnoreCase("hi_IN")){
						request.setAttribute("errorMessage",
								"कर्मचारी व्यक्तिगत जानकारी: निम्नलिखित सत्यापन सही करें");			
					}else{
						request.setAttribute("errorMessage",
								"Employee Personal Information : Please correct the following validation(s)");					
					}

					
					request.setAttribute("errorList", errorList);
//					FORWARDPAGE = "personal";
				} else {
					String userID = null;
					if (session.getAttribute("UserId") != null) {
						userID = session.getAttribute("UserId")
								.toString();
					}
					boolean flag = commonBD.updateEmployeeData(personDTO, userID);
					if(flag) {
						EmpmgmtViewBD employeeViewBD = new EmpmgmtViewBD();
						PersonalDetailsDTO personalDetailsDTO = employeeViewBD
								.getPersonalDetails(personDTO.getEmployeeId(),locale);
						familyMembers = employeeViewBD.getFamilyMemberDetails(personDTO.getEmployeeId(),locale);
						personalDetailsDTO.setFamilyMembers(familyMembers );
						personForm.setPersonalDTO(personalDetailsDTO);
						session.setAttribute("employeeId",
								personalDetailsDTO.getEmployeeId());
						session.setAttribute("personalForm",
								personForm);
						session.setAttribute("Personal",
								personalDetailsDTO);
						if(locale.equalsIgnoreCase("hi_IN")){
							request.setAttribute("successMessage", "कर्मचारी की  व्यक्तिगत जानकारी सफलतापूर्वक सुरक्शित की गई है ");
						}else{
							request.setAttribute("successMessage", "Employee Personal Information saved successfully");			
						}
						
						FORWARDPAGE = "viewEmpPage";
					} else {
						if(locale.equalsIgnoreCase("hi_IN")){
							request.setAttribute("successMessage", "कर्मचारी की  व्यक्तिगत जानकारी सफलतापूर्वक सुरक्शित नही हो पाई है ");
						}else{
							request.setAttribute("errorMessage", "Employee Personal Information could not be saved");		
						}
						
					}
				}
				
			}
			// TODO clear below beans
			// session.removeAttribute("permStateBean");
			// session.removeAttribute("permDistrictBean");
			// session.removeAttribute("currStateBean");
			// session.removeAttribute("currDistrictBean");
			// session.removeAttribute("religionBean");
			// session.removeAttribute("casteBean");
			// session.removeAttribute("homeDistrictBean");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward(FORWARDPAGE);

	}
	private PersonalForm populateDateLists(Locale locale) {
		PersonalForm pForm=new PersonalForm();
		try {			
			
			ArrayList dayMasterList=pForm.getDayMasterList();
			ArrayList monthMasterList=pForm.getMonthMasterList();
			ArrayList yearMasterList=pForm.getYearMasterList();
			CommonBD delegateObject = new CommonBD();
			if (dayMasterList == null) {
				dayMasterList = new ArrayList<DropDownDTO>();
			}
			if (monthMasterList == null) {
				monthMasterList = new ArrayList<DropDownDTO>();
			}
			if (yearMasterList == null) {
				yearMasterList = new ArrayList<DropDownDTO>();
			}
			pForm=delegateObject.populateDateLists(dayMasterList, monthMasterList,
					yearMasterList,locale);
			/*pForm1.setDayMasterList(dayMasterList);
			pForm1.setMonthMasterList(monthMasterList);
			pForm1.setYearMasterList(yearMasterList);*/
		} catch (Exception e) {

		}
		return pForm;
	}
	private void splitDateValues(PersonalDetailsDTO personDTO) {
		try {
			// 12/05/1971
			Date dateOfBirth;
			String fullStringDOB = personDTO.getDateOfBirth();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dayFormat = new SimpleDateFormat("d");
			SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			dateFormat.setLenient(false);
			monthFormat.setLenient(false);
			dateOfBirth = dateFormat.parse(fullStringDOB);
			personDTO.setEmpDay(dayFormat.format(dateOfBirth));
			personDTO.setEmpMonth(monthFormat.format(dateOfBirth));
			personDTO.setEmpYear(yearFormat.format(dateOfBirth));

		} catch (Exception e) {
		}
	}

	private void refreshFamilyData(ArrayList<FamilyMemberDTO> familyMembers,
			HttpServletRequest request,
			ArrayList<RelativeTypeDTO> relativeMasterList) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
			SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
			inputFormat.setLenient(false);
			displayFormat.setLenient(false);
			String dateString;
			HashMap<String, String> relativeMaster = new HashMap<String, String>(
					relativeMasterList.size());
			String[] relativeNames = request.getParameterValues("relativeName");
			String[] relativeTypeIDs = request
					.getParameterValues("relativeTypeID");
			String[] relativeDays = request.getParameterValues("relativeDay");
			String[] relativeMonths = request
					.getParameterValues("relativeMonth");
			String[] relativeYears = request.getParameterValues("relativeYear");
			if (relativeNames == null) {
				relativeNames = new String[familyMembers.size()];
			}
			if (relativeTypeIDs == null) {
				relativeTypeIDs = new String[familyMembers.size()];
			}
			if (relativeDays == null) {
				relativeDays = new String[familyMembers.size()];
			}
			if (relativeMonths == null) {
				relativeMonths = new String[familyMembers.size()];
			}
			if (relativeYears == null) {
				relativeYears = new String[familyMembers.size()];
			}
			for (RelativeTypeDTO relativeTypeDTO : relativeMasterList) {
				relativeMaster.put(relativeTypeDTO.getRelativeID(),
						relativeTypeDTO.getRelativeType());
			}
			for (int iLoop = 0; iLoop < familyMembers.size(); iLoop++) {
				FamilyMemberDTO member = familyMembers.get(iLoop);
				member.setRelativeName(relativeNames[iLoop] != null ? relativeNames[iLoop]
						.trim() : "");
				member.setRelativeTypeID(relativeTypeIDs[iLoop] != null ? relativeTypeIDs[iLoop]
						.trim() : "");
				member.setRelativeTypeLabel(relativeMaster.get(member
						.getRelativeTypeID()));
				member.setRelativeDay(relativeDays[iLoop] != null ? relativeDays[iLoop]
						.trim() : "");
				member.setRelativeMonth(relativeMonths[iLoop] != null ? relativeMonths[iLoop]
						.trim() : "");
				member.setRelativeYear(relativeYears[iLoop] != null ? relativeYears[iLoop]
						.trim() : "");
				dateString = member.getRelativeDay()
						+ member.getRelativeMonth() + member.getRelativeYear();
				try {
					member.setRelativeDOB(displayFormat.format(inputFormat
							.parse(dateString)));
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void setReligionValue(PersonalDetailsDTO personDTO) {
		try {
			ArrayList religions = personDTO.getReligionList();
			for (Object item : religions) {
				PersonalDetailsDTO entry = (PersonalDetailsDTO) item;
				if (personDTO.getReligion().equals(entry.getReligionName())) {
					personDTO.setReligion(entry.getReligionId());
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	private void setHomeDistrictValue(PersonalDetailsDTO personDTO) {
		try {
			ArrayList districts = personDTO.getHomeDistrictList();
			for (Object item : districts) {
				PersonalDetailsDTO entry = (PersonalDetailsDTO) item;
				if (personDTO.getHomeDistrict().equals(
						entry.getHomeDistrictName())) {
					personDTO.setHomeDistrict(entry.getHomeDistrictId());
					break;
				}
			}
		} catch (Exception e) {
		}

	}

	private void setCasteValue(PersonalDetailsDTO personDTO) {
		try {
			ArrayList castes = personDTO.getCasteList();
			for (Object item : castes) {
				PersonalDetailsDTO entry = (PersonalDetailsDTO) item;
				if (personDTO.getCaste().equals(entry.getCasteName())) {
					personDTO.setCaste(entry.getCasteId());
					break;
				}
			}
		} catch (Exception e) {
		}
	}
	
	private void setCountryValue(PersonalDetailsDTO personDTO, ArrayList countries) {
		try {
			for (Object item : countries) {
				AddressDTO entry = (AddressDTO) item;
				if (personDTO.getPermCountry().equals(entry.getCountryName())) {
					personDTO.setPermCountry(entry.getCountryId());
				}
				if (personDTO.getCurrCountry().equals(entry.getCountryName())) {
					personDTO.setCurrCountry(entry.getCountryId());
				}
			}
		} catch (Exception e) {
		}
	}
	
	private void setStateValue(PersonalDetailsDTO personDTO, ArrayList states) {
		try {
			for (Object item : states) {
				AddressDTO entry = (AddressDTO) item;
				if (personDTO.getPermState().equals(entry.getStateName())) {
					personDTO.setPermState(entry.getStateId());
				}
				if (personDTO.getCurrState().equals(entry.getStateName())) {
					personDTO.setCurrState(entry.getStateId());
				}
			}
		} catch (Exception e) {
		}
	}

	private void setDistrictValue(PersonalDetailsDTO personDTO, ArrayList districts) {
		try {
			for (Object item : districts) {
				AddressDTO entry = (AddressDTO) item;
				if (personDTO.getPermDistrict().equals(entry.getDistrictName())) {
					personDTO.setPermDistrict(entry.getDistrictId());
				}
				if (personDTO.getCurrDistrict().equals(entry.getDistrictName())) {
					personDTO.setCurrDistrict(entry.getDistrictId());
				}
			}
		} catch (Exception e) {
		}
	}
	
	private void setFamilyDataValues(ArrayList<FamilyMemberDTO> members, ArrayList<DropDownDTO> monthsMaster) {
		try {
			for (FamilyMemberDTO member : members) {
				String month = member.getRelativeMonth();
				int index = Integer.parseInt(month)-1;
				member.setRelativeMonth(monthsMaster.get(index).getOptionValue());
			}
		} catch (Exception e) {
		}
	}
}

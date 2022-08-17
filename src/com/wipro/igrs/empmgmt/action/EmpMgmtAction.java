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

import java.io.PrintWriter;
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
import com.wipro.igrs.empmgmt.dto.AddressDTO;
import com.wipro.igrs.empmgmt.dto.ChildDetailsDTO;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;
import com.wipro.igrs.empmgmt.dto.FamilyMemberDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.RelativeTypeDTO;
import com.wipro.igrs.empmgmt.form.PersonalForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;
import com.wipro.igrs.util.DateToWords;

/**
* 
* EmpMgmtAction.java <br>
* EmpMgmtAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class EmpMgmtAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(EmpMgmtAction.class);

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
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		EmpMgmtRule empMgmtRule = null;
		String FORWARDPAGE = null;
		CommonBD commonBD = null;
		String locale="";
		Locale currentLocale=new Locale(locale);
		if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
				currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
				locale=currentLocale.toString();
				
			}
		String isMenuClick = request.getParameter("isMenuClick");
		if("yes".equals(isMenuClick)) {
		
			
			try {
				
				commonBD = new CommonBD();
				PersonalForm pForm = (PersonalForm) form;
			pForm.setLocale(currentLocale);
				PersonalDetailsDTO dto = pForm.getPersonalDTO();
				if (dto != null) {
					dto.setEmployeeId("");
				}
				pForm.setRelativeMasterList(commonBD.getRelativeMasterList(locale));
				pForm.setPersonalDTO(new PersonalDetailsDTO());
				pForm.setChildList(new ArrayList());
				pForm.setLocale(currentLocale);
				
				dto.setFamilyMembers(new ArrayList<FamilyMemberDTO>());
				//tempPopFamily(dto);
				session.removeAttribute("personalForm");
				session.removeAttribute("permStateBean");
				session.removeAttribute("permDistrictBean");
				session.removeAttribute("currStateBean");
				session.removeAttribute("currDistrictBean");
				session.removeAttribute("reference");
				session.removeAttribute("childList");
				session.removeAttribute("homeDistrictBean");
				session.removeAttribute("viewemployeeId");
				FORWARDPAGE = "personal";
				//			form = new PersonalForm();
				session.setAttribute("personalForm", pForm);
			} catch (Exception e) {
			}
		}
		
		String formName = request.getParameter("formName");
		if(session.getAttribute("personalForm") != null) {
			try {
				PersonalForm pForm = (PersonalForm) session.getAttribute("personalForm");
				commonBD = new CommonBD();
				String result = commonBD.searchEmpID(pForm.getPersonalDTO().getEmployeeId());
				if(("".equals(result))!=true) {
					form = new PersonalForm();
					((PersonalForm)form).setFormName("PersonalDetailsForm");
					session.setAttribute("personalForm",form);
					FORWARDPAGE = "personal";
				}
			} catch (Exception e) {
			}
		}
	
		ArrayList errorList = null;
		ArrayList permCountrylist = new ArrayList();
		ArrayList permStatelist = new ArrayList();
		ArrayList permDistrictlist = new ArrayList();
		ArrayList currCountrylist = new ArrayList();
		ArrayList currStatelist = new ArrayList();
		ArrayList currDistrictlist = new ArrayList();
		ArrayList religionList = new ArrayList();
		ArrayList casteList = new ArrayList();
		ArrayList homeDistrictList = new ArrayList();
		ArrayList homeStatelist = new ArrayList();
		AddressDTO addressDTO = null;
		PersonalDetailsDTO pDTO = null;
		//HttpSession session = request.getSession(true);
		try {
			PersonalForm personalForm = (PersonalForm) form;
			PersonalForm pF=populateDateLists(currentLocale);
			personalForm.setDayMasterList(pF.getDayMasterList());
			personalForm.setMonthMasterList(pF.getMonthMasterList());
			personalForm.setYearMasterList(pF.getYearMasterList());
			commonBD = new CommonBD();
			pDTO= new PersonalDetailsDTO();
			ArrayList listHintQuestion = commonBD.getHintQuestions();
			
			personalForm.setUserHintQuestion(listHintQuestion);
			String chooseHQues=personalForm.getPersonalDTO().getHintQuestID();
			pDTO.setHintQuestID(chooseHQues);
			
			
			ArrayList childlist = personalForm.getChildList();

			ArrayList childList = new ArrayList();
			for (int i = 0; i < childlist.size(); i++) {
				ChildDetailsDTO childDTO = (ChildDetailsDTO) childlist.get(i);
				if ((childDTO != null && childDTO.getChildName() == null)
						|| (childDTO != null && childDTO.getChildName().equals(
								""))) {
				} else {

					childList.add(childDTO);
				}
			}
			PersonalDetailsDTO personalDTOFamily = personalForm.getPersonalDTO();
			ArrayList<FamilyMemberDTO> familyMembersRefresh = personalDTOFamily.getFamilyMembers();
			refreshFamilyData(familyMembersRefresh, request, personalForm.getRelativeMasterList());
			//familyMembers.add(new FamilyMemberDTO());
			session.setAttribute("personalForm", personalForm);
			//default row
//			if(childList.size() == 0) {
//				childList.add(new ChildDetailsDTO());
//			}
			session.setAttribute("childList", childList);

			addressDTO = personalForm.getAddressDTO();
			if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase(
							"PersonalDetailsForm")) {
				try {
					session.removeAttribute("employeeId");
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					pDTO = new PersonalDetailsDTO();
					session.removeAttribute("permStateBean");
					session.removeAttribute("permDistrictBean");
					session.removeAttribute("currStateBean");
					session.removeAttribute("currDistrictBean");
					session.removeAttribute("reference");
					permCountrylist = commonBD.getCountry(locale);
					currCountrylist = commonBD.getCountry(locale);
					religionList = commonBD.getReligion(locale);
					casteList = commonBD.getCaste(locale);
					
					personalForm.getPermAddressDTO().setCountryList(permCountrylist);
					personalForm.getCurrAddressDTO().setCountryList(currCountrylist);
					pDTO.setReligionList(religionList);
					pDTO.setCasteList(casteList);
					
					homeStatelist = commonBD.getHomeState(locale);
					personalForm.getPersonalDTO().setHomeStateList(homeStatelist);
					pDTO.setHomeStateList(homeStatelist);
					session.setAttribute("permAddressBean", personalForm.getPermAddressDTO());
					session.setAttribute("currAddressBean", personalForm.getCurrAddressDTO());
					session.setAttribute("religionBean", pDTO);
					session.setAttribute("casteBean", pDTO);
				session.setAttribute("homeStateBean", pDTO);
					//session.setAttribute("homeDistrictBean", pDTO);
					session.setAttribute("childList", childList);
//					tempPopFamily(pDTO);
					personalForm.setPersonalDTO(pDTO);
					session.setAttribute("personalForm",personalForm);

					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("reference")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					ArrayList referencelist = commonBD.getReference(pDTO);
					session.setAttribute("reference", referencelist);
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("populateDOBWords")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					pDTO.setDateOfBirthWords(DateToWords.convertDateToWords(pDTO.getEmpDay(), pDTO.getEmpMonth(), pDTO.getEmpYear()));
					
					
//					logger.debug("day : " + pDTO.getEmpDay());
//					logger.debug("month : " + pDTO.getEmpMonth());
//					logger.debug("year : " + pDTO.getEmpYear());
					FORWARDPAGE = "personal";
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("permstate")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					permStatelist = commonBD.getState(personalForm
							.getPersonalDTO().getPermCountry(),locale);
					personalForm.getPermAddressDTO().setStateList(permStatelist);
					session.setAttribute("permStateBean", personalForm.getPermAddressDTO());
					session.setAttribute("childList", childList);
					session.removeAttribute("permDistrictBean");
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase(
							"permdistrict")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					permDistrictlist = commonBD.getDistrict(personalForm
							.getPersonalDTO().getPermState(),locale);
					personalForm.getPermAddressDTO().setDistrictList(permDistrictlist);
					session.setAttribute("childList", childList);
					session.setAttribute("permDistrictBean", personalForm.getPermAddressDTO());
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			}
			else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase(
							"permhomedistrict")) {
				try {
					
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					
					homeDistrictList = commonBD.getHomeDistrictEmp(personalForm
							.getPersonalDTO().getHomeState(),locale);
					/*homeDistrictList = commonBD.getDistrict(personalForm
							.getPersonalDTO().getHomeState(),locale);*/
					pDTO.setHomeDistrictList(homeDistrictList);
					//personalForm.getPermAddressDTO().setDistrictList(homeDistrictList);
					//session.setAttribute("childList", childList);
					session.setAttribute("homeDistrictBean", pDTO);
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			}else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("currstate")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					currStatelist = commonBD.getState(personalForm
							.getPersonalDTO().getCurrCountry(),locale);
					personalForm.getCurrAddressDTO().setStateList(currStatelist);
					session.setAttribute("currStateBean", personalForm.getCurrAddressDTO());
					session.setAttribute("childList", childList);
					session.removeAttribute("currDistrictBean");
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase(
							"currdistrict")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					currDistrictlist = commonBD.getDistrict(personalForm
							.getPersonalDTO().getCurrState(),locale);
					personalForm.getCurrAddressDTO().setDistrictList(currDistrictlist);
					session.setAttribute("childList", childList);
					session.setAttribute("currDistrictBean", personalForm.getCurrAddressDTO());
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}

			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase(
							"copyPermAddress")) {
				try {
					pDTO = personalForm.getPersonalDTO();
					AddressDTO permAddr = personalForm.getPermAddressDTO();
					AddressDTO currAddr = personalForm.getCurrAddressDTO();
					PersonalDetailsDTO perDTO = personalForm.getPersonalDTO();
					currAddr.setCountryId(permAddr.getCountryId());
					if(currAddr.getCountryId() == null || ("".equals(currAddr.getCountryId().trim()))) {
						currAddr.setCountryId(pDTO.getCurrCountry());
						permAddr.setCountryId(pDTO.getCurrCountry());
					}
					currAddr.setCountryList(permAddr.getCountryList());
					currAddr.setCountryName(permAddr.getCountryName());
					currAddr.setStateId(permAddr.getStateId());
					if(currAddr.getStateId() == null || ("".equals(currAddr.getStateId().trim()))) {
						currAddr.setStateId(pDTO.getCurrState());
						permAddr.setStateId(pDTO.getCurrState());
					}
					currAddr.setStateList(permAddr.getStateList());
					currAddr.setStateName(permAddr.getStateName());
					currAddr.setDistrictId(permAddr.getDistrictId());
					if(currAddr.getDistrictId() == null || ("".equals(currAddr.getDistrictId().trim()))) {
						currAddr.setDistrictId(pDTO.getCurrDistrict());
						permAddr.setDistrictId(pDTO.getCurrDistrict());
					}
					currAddr.setDistrictList(permAddr.getDistrictList());
					currAddr.setDistrictName(permAddr.getDistrictName());
					perDTO.setCurrAddress(perDTO.getPermAddress());
					perDTO.setCurrCountry(perDTO.getPermCountry());
					perDTO.setCurrCountryList(perDTO.getPermCountryList());
					perDTO.setCurrState(perDTO.getPermState());
					perDTO.setCurrStateList(perDTO.getPermStateList());
					perDTO.setCurrDistrict(perDTO.getPermDistrict());
					perDTO.setCurrDistrictList(perDTO.getPermDistrictList());
					perDTO.setCurrPin(perDTO.getPermPin());
					personalForm.setCurrAddressDTO(currAddr);
					
					session.setAttribute("currAddressBean", currAddr);
					session.setAttribute("currStateBean", currAddr);
					session.setAttribute("currDistrictBean", currAddr);
					session.setAttribute("personalForm", personalForm);
					FORWARDPAGE = "personal";
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}

			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("searchid")) {
				String empiddb="";
				PrintWriter out = response.getWriter();
				try {
					
					pDTO = personalForm.getPersonalDTO();
					commonBD = new CommonBD();
					String empid = request.getParameter("empid");
					empiddb= commonBD.searchEmpID(empid);
					
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
				out.print(empiddb);
				return null;
			}  else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("addChildRow")) {
				try {
					childList.add(new ChildDetailsDTO());
					personalForm.setChildList(childList);
					session.setAttribute("childList", childList);
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
				FORWARDPAGE = "personal";
			}  else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("delChildRow")) {
				try {
					int index = Integer.parseInt(request.getParameter("delRowIndex"));
					childList.remove(index);
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
				personalForm.setChildList(childList);
				session.setAttribute("childList", childList);
				FORWARDPAGE = "personal";
			} else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("addFamilyRow")) {
				try {
					PersonalDetailsDTO personalDTO = personalForm.getPersonalDTO();
					ArrayList<FamilyMemberDTO> familyMembers = personalDTO.getFamilyMembers();
					refreshFamilyData(familyMembers, request, personalForm.getRelativeMasterList());
					familyMembers.add(new FamilyMemberDTO());
					session.setAttribute("personalForm", personalForm);
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
				FORWARDPAGE = "personal";
			}  else if (personalForm.getFormName() != null
					&& personalForm.getFormName().equalsIgnoreCase("delFamilyRow")) {
				try {
					int index = Integer.parseInt(request.getParameter("selectedIndex"));
					PersonalDetailsDTO personalDTO = personalForm.getPersonalDTO();
					ArrayList<FamilyMemberDTO> familyMembers = personalDTO.getFamilyMembers();
					refreshFamilyData(familyMembers, request, personalForm.getRelativeMasterList());
					familyMembers.remove(index);
					session.setAttribute("personalForm", personalForm);
				} catch (Exception exception) {
					logger.error(exception.getMessage(),exception);
				}
				FORWARDPAGE = "personal";
			} else if (formName != null
					&& formName.equals("InsertPersonalDetails")) {
				try {
				
					pDTO = personalForm.getPersonalDTO();
					ArrayList<FamilyMemberDTO> familyMembers = pDTO.getFamilyMembers();
					refreshFamilyData(familyMembers, request, personalForm.getRelativeMasterList());
					commonBD = new CommonBD();
					empMgmtRule = new EmpMgmtRule();
					errorList = empMgmtRule.validatePersonalDetails(pDTO,
							childList, ((Date)session.getAttribute("currSysDate")));
					if (empMgmtRule.isError()) {
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_FLAG,
								Boolean.TRUE);
						request.setAttribute(
								EmpmgmtConstant.EMP_MGMT_ERROR_LIST, errorList);
						FORWARDPAGE = "personal";
					} else {
						String empiddb= commonBD.searchEmpID(pDTO.getEmployeeId());
						
						if (empiddb==null || (empiddb!=null && empiddb.trim().length()<=0 )) {
							String userid = null;
							if (session.getAttribute("UserId") != null) {
								userid = session.getAttribute("UserId")
										.toString();
							}
							logger.debug("Personal Form : " + personalForm);
							boolean flag = commonBD
									.insertEmpPersonalFamilyDetails(pDTO,
											userid);
							//insertEmpPersonalFamilyDetails
							if (flag) {
								boolean isEmpDataInserted=flag;//commonBD.insertEmpRegDetails(pDTO);
								if(isEmpDataInserted){
									session.setAttribute("newEmployeeID", pDTO.getEmployeeId());
									logger.debug("EMPloyee Data inserted Successfully into IGRSUSER_REG_DETAILS table");
								}
								else {		
									logger.debug("Data could not be inserted...");	
								}
								ArrayList list = new ArrayList();
								list.add("PERSONAL");
								session.setAttribute("tablist", list);
								session.setAttribute("employeeId", pDTO
										.getEmployeeId());
								if(locale.equalsIgnoreCase("hi_IN")){
									request
									.setAttribute("success",
											"<font color=green>व्यक्तिगत जानकारी सफलतापूर्वक सबमिट की गई है </font>");	
								}else{
									request
									.setAttribute("success",
											"<font color=green>Personal Information submitted successfully!</font>");						
								}

								
								session.removeAttribute("permAddressBean");
								session.removeAttribute("currAddressBean");
								session.removeAttribute("permStateBean");
								session.removeAttribute("permDistrictBean");
								session.removeAttribute("currStateBean");
								session.removeAttribute("currDistrictBean");
								session.removeAttribute("Personal");
								session.removeAttribute("childList");
								session.removeAttribute("reference");
								FORWARDPAGE = "office";
							} else {
								if(locale.equalsIgnoreCase("hi_IN")){
									request
									.setAttribute("failure",
											"<font color=red>व्यक्तिगत जानकारी सफलतापूर्वक सबमिट नहीं हो पाई है !</font>");	
								}else{
									request
									.setAttribute("failure",
											"<font color=red>Personal Information not submitted successfully!</font>");
								}

								
								FORWARDPAGE = "personal";
							}
						} else {
							if(locale.equalsIgnoreCase("hi_IN")){
								request
								.setAttribute("failure",
										"<font color=red>यह कर्मचारी आईडी पहले से मौजूद है</font>");
							}else{
								request
								.setAttribute("failure",
										"<font color=red>This Employee ID Already Exists</font>");
							}

							
							FORWARDPAGE = "personal";
						}

					}
				} catch (Exception ex) {
					logger.error(ex.getMessage(),ex);
				}

			}
		} catch (Exception e) {

			logger.error(e.getMessage(),e);
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
	private void refreshFamilyData(ArrayList<FamilyMemberDTO> familyMembers,
			HttpServletRequest request, ArrayList<RelativeTypeDTO> relativeMasterList) {
		try {
			SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
			SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
			inputFormat.setLenient(false);
			displayFormat.setLenient(false);
			String dateString;
			HashMap<String, String> relativeMaster = new HashMap<String, String>(relativeMasterList.size());
			String[] relativeNames = request.getParameterValues("relativeName");
			String[] relativeTypeIDs = request.getParameterValues("relativeTypeID");
			String[] relativeDays = request.getParameterValues("relativeDay");
			String[] relativeMonths = request.getParameterValues("relativeMonth");
			String[] relativeYears = request.getParameterValues("relativeYear");
			if(relativeNames == null) {
				relativeNames = new String[familyMembers.size()];
			}
			if(relativeTypeIDs == null) {
				relativeTypeIDs = new String[familyMembers.size()];
			}
			if(relativeDays == null) {
				relativeDays = new String[familyMembers.size()];
			}
			if(relativeMonths == null) {
				relativeMonths = new String[familyMembers.size()];
			}
			if(relativeYears == null) {
				relativeYears = new String[familyMembers.size()];
			}
			for (RelativeTypeDTO relativeTypeDTO : relativeMasterList) {
				relativeMaster.put(relativeTypeDTO.getRelativeID(), relativeTypeDTO.getRelativeType());
			}
			for (int iLoop = 0; iLoop < familyMembers.size(); iLoop++) {
				FamilyMemberDTO member = familyMembers.get(iLoop);
				member.setRelativeName(relativeNames[iLoop]!=null?relativeNames[iLoop].trim():"");
				member.setRelativeTypeID(relativeTypeIDs[iLoop]!=null?relativeTypeIDs[iLoop].trim():"");
				member.setRelativeTypeLabel(relativeMaster.get(member.getRelativeTypeID()));
				member.setRelativeDay(relativeDays[iLoop]!=null?relativeDays[iLoop].trim():"");
				member.setRelativeMonth(relativeMonths[iLoop]!=null?relativeMonths[iLoop].trim():"");
				member.setRelativeYear(relativeYears[iLoop]!=null?relativeYears[iLoop].trim():"");
				dateString = member.getRelativeDay()+member.getRelativeMonth()+member.getRelativeYear();
				try {
					member.setRelativeDOB(displayFormat.format(inputFormat.parse(dateString)));
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}

//	private void tempPopFamily(PersonalDetailsDTO dto) {
//		if(dto != null) {
//			return;
//		}
//		try {
//			ArrayList<FamilyMemberDTO> list = dto.getFamilyMembers();
//			FamilyMemberDTO member = new FamilyMemberDTO();
//			member.setRelativeName(member.hashCode()+"");
//			list.add(member);
//			member = new FamilyMemberDTO();
//			member.setRelativeName(member.hashCode()+"");
//			list.add(member);
//			member = new FamilyMemberDTO();
//			member.setRelativeName(member.hashCode()+"");
//			list.add(member);
//			member = new FamilyMemberDTO();
//			member.setRelativeName(member.hashCode()+"");
//			list.add(member);
//		} catch (Exception e) {
//		}
//
//	}
}

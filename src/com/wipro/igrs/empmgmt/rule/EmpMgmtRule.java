/*
 * EmpMgmtRule.java
 */

package com.wipro.igrs.empmgmt.rule;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.wipro.igrs.empmgmt.bd.DepartmentalExamsBD;
import com.wipro.igrs.empmgmt.bd.OfficalInfoBD;
import com.wipro.igrs.empmgmt.dao.EmpMgntDAO;
import com.wipro.igrs.empmgmt.dto.AcademicDTO;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.BankDTO;
import com.wipro.igrs.empmgmt.dto.ChildDetailsDTO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsDTO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsResultDTO;
import com.wipro.igrs.empmgmt.dto.DeptExamDTO;
import com.wipro.igrs.empmgmt.dto.DeptTrainingDTO;
import com.wipro.igrs.empmgmt.dto.EmpmgmtUploadDTO;
import com.wipro.igrs.empmgmt.dto.FundDTO;
import com.wipro.igrs.empmgmt.dto.NomineeDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.PersonalDetailsDTO;
import com.wipro.igrs.empmgmt.dto.PrevEmpDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;
import com.wipro.igrs.empmgmt.dto.ServiceVerificationDTO;
import com.wipro.igrs.empmgmt.dto.TalentDetailsDTO;
import com.wipro.igrs.empmgmt.dto.TransferDTO;
import com.wipro.igrs.util.CommonRoutines;
import com.wipro.igrs.util.CommonUtil;
import com.wipro.igrs.util.PropertiesFileReader;

@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public class EmpMgmtRule {
	
	public static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";
	public static DateFormat DATE_FORMATTER = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	PropertiesFileReader property = null;

	ArrayList errorlist = null;

	String document;

	boolean error;

	OfficalInfoDTO officalInfoDTO = null;

	PersonalDetailsDTO personalDTO = null;

	ServiceVerificationDTO serviceVerificationDTO = null;

	TalentDetailsDTO talentDTO = null;

	AcademicDTO acadamicDTO3 = null;

	PrevEmpDTO prevDTO = null;

	ChildDetailsDTO childDTO = null;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	
	public EmpMgmtRule() {
		try {

			property = PropertiesFileReader.getInstance("resources.igrs");
		} catch (Exception e) {
//			////System.out.println("Not Able to Initilize the property File" + e);
		}
	}

	/**
	 * @param list
	 * @return ArrayList
	 */
	
	public ArrayList validateList(ArrayList list) {
		errorlist = new ArrayList();
		String value = "in";
		try {
			errorlist.add(property.getValue("error.header"));

			if (list == null || list.size() <= 0) {
				errorlist.add(property.getValue("error.list.notpresent")
						+ value);
			}

		} catch (Exception e) {

		}
		return errorlist;

	}

	/**
	 * @param result
	 * @return ArrayList
	 */
	public ArrayList validateEmployeeID(boolean result) {
		errorlist = new ArrayList();
		String value = "in";
		try {
			errorlist.add(property.getValue("error.header"));

			if (result == false) {
				errorlist.add(property.getValue("error.depttraining.empid"));
				setError(true);
			}

		} catch (Exception e) {

		}
		return errorlist;

	}

	// /Department trainning list

	/**
	 * @param trainninglist
	 * @return ArrayList
	 */
	public ArrayList validateDepartmentalTrainningList(ArrayList trainninglist) {
		errorlist = new ArrayList();

		try {
			//System.out.println("---------------trainninglist"
					//+ trainninglist.size());
			if (trainninglist != null) {
				for (int i = 0; i < trainninglist.size(); i++) {

					DeptTrainingDTO trainningDTO = (DeptTrainingDTO) trainninglist
							.get(i);

					if (trainningDTO != null) {

						if (trainningDTO.getTrainingNo().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingno.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingName().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingname.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingLevel().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.traininglevel.required"));
							setError(true);

						}
						if (trainningDTO.getOrganizingAuthority().trim()
								.length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.organizingauthority.required"));
							setError(true);
						}
						if (trainningDTO.getOrgainizingBody().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.organizingbody.required"));
							setError(true);
						}
						if (trainningDTO.getPlaceOfTraining().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.placeoftraining.required"));
							setError(true);
						}
						if (trainningDTO.getFinancialYear().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.financialyear.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingStartDate().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingstartdate.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingEndDate().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingenddate.required"));
							setError(true);
						}
						if (trainningDTO.getAuthorisingAuthority().trim()
								.length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.authorisingauthority.required"));
							setError(true);
						}
						if (trainningDTO.getAuthorizationDate().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.authorizationdate.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingCost().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingcost.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingResult().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingresult.required"));
							setError(true);
						}
						if (trainningDTO.getTrainingComments().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.depttraining.trainingcomments.required"));
							setError(true);
						}
					}
				}
			}
		} catch (Exception e) {
//			//System.out.println(e);
		}
		return errorlist;
	}

	// /Departmental Exam list
	/**
	 * @param examlist
	 * @return ArrayList
	 */
	public ArrayList validateDepartmentalExamList(ArrayList examlist) {
		errorlist = new ArrayList();

		try {
			//System.out.println("---------------examlist" + examlist.size());
			if (examlist != null) {
				for (int i = 0; i < examlist.size(); i++) {

					DeptExamDTO examDTO = (DeptExamDTO) examlist.get(i);

					if (examDTO != null) {

						if (examDTO.getNameOfExam().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.nameofexam.required"));
							setError(true);
						}
						if (examDTO.getExamDate().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.examdate.required"));
							setError(true);
						}
						if (examDTO.getExamsOrganizingAuthority().trim()
								.length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.examsorganizingauthority.required"));
							setError(true);

						}
						if (examDTO.getPlaceOfExam().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.placeofexam.required"));
							setError(true);

						}
						if (examDTO.getResult().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.result.required"));
							setError(true);

						}
						if (examDTO.getResultDate().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.resultdate.required"));
							setError(true);

						}
						if (examDTO.getExamsComments().trim().length() == 0) {

							errorlist
									.add(property
											.getValue("error.deptexam.examscomments.required"));
							setError(true);

						}
					}
				}
			}
		} catch (Exception e) {
//			//System.out.println(e);
		}
		return errorlist;
	}

	/**
	 * @param value
	 * @return ArrayList
	 */
	public ArrayList validateEmpID(boolean value) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("resources.igrs");
			if (!value) {
				//errorList.add(pr.getValue("error.header"));
				errorList.add(pr.getValue("error.empidnotvalid"));
				flag = true;
			} else {
				flag = false;
			}
			setError(flag);
		}

		catch (Exception x) {
//			//System.out.println("validateWillDepositRule :-" + x);
		}
		return errorList;
	}

	/**
	 * @param OficalInfolist
	 * @return ArrayList
	 * @throws Exception
	 */
	public ArrayList validateOfficalInfo(ArrayList OficalInfolist)
			throws Exception {
		ArrayList errorList = new ArrayList();
		property = PropertiesFileReader.getInstance("resources.igrs");
		boolean flag = false;
		try {
			if (OficalInfolist.size() <= 0) {
				flag = true;
				errorList.add(property.getValue("error.Attendance.Noresults"));
			}
			setError(flag);

		} catch (Exception e) {
//			//System.out.println("error in check attendance" + e);
		}
		return errorList;
	}

	// //property/////////////

	/**
	 * @param propertyList
	 * @return ArrayList
	 */
	public ArrayList validatePropertyList(ArrayList propertyList) {

		errorlist = new ArrayList();
		try {
			//System.out.println("-------------propertyList : "
					//+ propertyList.size());
			SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
			sdfInput.setLenient(false);
			Date currDate = sdfInput.parse(sdfInput.format(new Date()));
			Date regDate = (Date) currDate.clone();
			if (propertyList != null) {
				for (int i = 0; i < propertyList.size(); i++) {
					PropertyDTO propertyDTO = (PropertyDTO) propertyList.get(i);

					if (propertyDTO != null) {
						if (propertyDTO.getPropertyaddress().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.address.required"));
							setError(true);
						}
						if (propertyDTO.getPropertycountry().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.country.required"));
							setError(true);
						}
						if (propertyDTO.getPropertystate().trim().length() == 0)

						{
							errorlist.add(property
									.getValue("error.property.state.required"));
							setError(true);
						}
						if (propertyDTO.getPropertydistrict().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.district.required"));
							setError(true);
						}
						if (propertyDTO.getPropertypostalcode().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.postalcode.required"));
							setError(true);
						}
						if (propertyDTO.getPropertyshare().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.shareofproperty.required"));
							setError(true);
						}
						if (propertyDTO.getPropertyregid().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.regcode.required"));
							setError(true);
						}
						if (propertyDTO.getPropertyregdate().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.property.regdate.required"));
							setError(true);
						}
						try {
							regDate = sdfInput.parse(propertyDTO.getPropertyregdate());
							if(regDate.after(currDate)) {
								errorlist
										.add(property
												.getValue("error.property.regdate.afterCurrent"));
								setError(true);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}

			}
		} catch (Exception e) {
//			//System.out.println(e);
		}
		return errorlist;
	}

	// //Asset////

	/**
	 * @param assertlist
	 * @return ArrayList
	 */
	public ArrayList validateAssertList(ArrayList assertlist) {
		errorlist = new ArrayList();

		try {
//			System.out
//					.println("---------------assert List" + assertlist.size());
			if (assertlist != null) {
				for (int i = 0; i < assertlist.size(); i++) {

					AssetDTO assertDTO = (AssetDTO) assertlist.get(i);

					if (assertDTO != null) {

						if (assertDTO.getAssettype().trim().length() == 0) {

							errorlist.add(property
									.getValue("error.assert.typeId.required"));
							setError(true);
						}
						if (assertDTO.getAssestdetails().trim().length() == 0) {

							errorlist.add(property
									.getValue("error.assert.details.required"));
							setError(true);
						}
						if (assertDTO.getAssetValue().trim().length() == 0) {

							errorlist.add(property
									.getValue("error.assert.value.required"));
							setError(true);

						}

					}
				}
			}
		} catch (Exception e) {
//			//System.out.println(e);
		}
		return errorlist;
	}

	/**
	 * @param officalInfoDTO
	 * @return ArrayList
	 */
	public ArrayList validateEmployeeMgmeOfficalInfo(
			OfficalInfoDTO officalInfoDTO) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			property = PropertiesFileReader.getInstance("resources.igrs");
			errorList.add(property.getValue("error.header"));
			if (officalInfoDTO.getDateOfJoining().trim().length() == 0) {
				flag = true;
				errorList.add(property.getValue("error.dateOfJoining"));
			}

			setError(flag);
		}

		catch (Exception x) {
//			//System.out.println("error");
		}
		return errorList;
	}

	/**
	 * @param email
	 * @return boolean
	 */
	public boolean validateEmail(String email) {
		String pattern = "([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
		return email.matches(pattern);
	}

	/**
	 * @param officalInfoDTO
	 * @param officalBD 
	 * @return ArrayList
	 */
	public ArrayList validateOfficalInfoDTORule(OfficalInfoDTO officalInfoDTO,
			ArrayList list, OfficalInfoBD officalBD) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			Date joinDate, sepDate, firstGovtDate, currDate;
			boolean isJoin = false, isSep = false, isFirst = false;
			SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy");
			sdfInput.setLenient(false);
			currDate = sdfInput.parse(sdfInput.format(new Date()));
			joinDate = (Date) currDate.clone();
			sepDate = (Date) currDate.clone();
			firstGovtDate = (Date) currDate.clone();
			property = PropertiesFileReader.getInstance("resources.igrs");
			// errorList.add(property.getValue("error.header"));
			if (officalInfoDTO.getDateOfJoining().trim().length() == 0) {
				flag = true;
				errorList.add(property.getValue("error.dateOfJoining"));
			} else {
				try {
					joinDate = sdfInput.parse((officalInfoDTO.getDateOfJoining()));
					isJoin = true;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			if (officalInfoDTO.getDateOfSaperation().trim().length() == 0) {
				flag = true;
				errorList.add(property.getValue("error.dateOfSaperation"));
			} else {
				try {
					sepDate = sdfInput.parse((officalInfoDTO.getDateOfSaperation()));
					isSep = true;
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			try {
				firstGovtDate = sdfInput.parse((officalInfoDTO.getDateFirstGovtService()));
				isFirst = true;
			} catch (Exception e) {
			}
			
			if(isJoin) {
				if(joinDate.after(currDate)) {
					flag = true;
					errorList.add(property.getValue("error.dateOfJoiningFuture"));
				}
			}

			if(isJoin && isSep) {
				if(sepDate.before(joinDate)) {
					flag = true;
					errorList.add(property.getValue("error.dateOfJoiningAfterSeparation"));
				}
			}
			
			if(isFirst) {
				if(firstGovtDate.after(currDate)) {
					flag = true;
					errorList.add(property.getValue("error.dateOfFirstGovtFuture"));
				}
			}
			
			if (officalInfoDTO.getSubstantive().trim().length() == 0) {
				flag = true;
				errorList.add(property.getValue("error.select.substantive"));
			}
			if (officalInfoDTO.getClass1().trim().length() == 0) {
				flag = true;
				errorList.add(property.getValue("error.select.class"));
			}

			// if (officalInfoDTO.getCompEmplRefrence().trim().length() == 0) {
			// flag = true;
			// errorList.add(property.getValue("error.com.emp.no"));
			// }

			if (list == null) {

				flag = true;
				errorList.add(property.getValue("error.official.nofile"));

			} else {
				EmpmgmtUploadDTO uploadedItem;
				ArrayList<String> attachedDocLabels = new ArrayList<String>();
				ArrayList<String> missingDocLabels = new ArrayList<String>(); 
				for (Object item : list) {
					uploadedItem = (EmpmgmtUploadDTO) item;
					attachedDocLabels.add(uploadedItem.getDocTypeLabel());
				}
				if(attachedDocLabels.isEmpty()) {
					flag = true;
					errorList.add(property.getValue("error.official.nofile"));
				} else {
					missingDocLabels = officalBD.getMissingDocLabels(attachedDocLabels);
					if((missingDocLabels.isEmpty() != true)) {
						StringBuilder strBldr = new StringBuilder();
						strBldr.append(property.getValue("error.official.filetypeMissing"));
						String temp = missingDocLabels.toString();
						temp = temp.replaceAll("\\[", "");
						temp = temp.replaceAll("\\]", "");
						strBldr.append(" : " +temp);
						strBldr.append("</font></li></br>");
//						flag = true;
//						errorList.add(strBldr.toString());
						//
					}
				}
			}
			setError(flag);
		}

		catch (Exception x) {
			// //System.out.println("error");
		}
		return errorList;
	}

	/**
	 * @param personaldto
	 * @param childlist
	 * @param date 
	 * @return ArrayList
	 */
	public ArrayList validatePersonalDetails(PersonalDetailsDTO personaldto,
			ArrayList childlist, Date currSysDate) {
		ArrayList errorList = new ArrayList();
		boolean flag = false;

		try {
			// pr=PropertiesFileReader.getInstance("com.wipro.igrs.employeemanagement");
			errorList.add(property.getValue("error.header"));
			// System.out.println("error list in error header" + errorList);
//			if (personaldto.getEmployeeId().trim().length() == 0) {
//
//				errorList.add(property.getValue("error.personal.employeeid"));
//				// //System.out.println("error list in rule" + errorList);
//				setError(true);
//			}
			if (personaldto.getFirstName().trim().length() == 0) {

				errorList.add(property.getValue("error.personal.firstName1"));
				// //System.out.println("error list in rule" + errorList);
				setError(true);
			}
			if (personaldto.getLastName().trim().length() == 0) {

				errorList.add(property.getValue("error.personal.lastName"));
				// //System.out.println("error list in rule" + errorList);
				setError(true);
			}

			if (personaldto.getEmpDay().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.empday"));
				setError(true);
			}

			if (personaldto.getEmpMonth().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.empmonth"));
				setError(true);
			}
			if (personaldto.getEmpYear().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.empyear"));
				setError(true);
			}
			if (checkDate(personaldto.getEmpDay(), personaldto.getEmpMonth(),
					personaldto.getEmpYear(), currSysDate) != true) {
				errorList.add(property.getValue("error.personal.empdatevalid"));
				setError(true);
			}
			if (checkFutureDate(personaldto.getEmpDay(),
					personaldto.getEmpMonth(), personaldto.getEmpYear(),
					currSysDate) != true) {
				errorList
						.add(property.getValue("error.personal.empdatefuture"));
				setError(true);
			}
			if (personaldto.getDateOfBirthWords().trim().length() == 0) {
				errorList.add(property
						.getValue("error.personal.getdateofbirthinwords"));
				setError(true);
			}
			if (personaldto.getFatherGaurdName().trim().length() == 0) {
				errorList.add(property
						.getValue("error.personal.gaurdfathername"));
				setError(true);
			}
			if (personaldto.getMotherName().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.motherName"));
				setError(true);
			}

			if (personaldto.getMaritalStatus().trim().length() == 0) {
				errorList.add(property
						.getValue("error.personal.maritalStatus1"));
				setError(true);
			}
			if (personaldto.getReligion().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.religion"));
				setError(true);
			}
			if (personaldto.getHomeDistrict().trim().length() == 0) {
				errorList.add(property
						.getValue("error.personal.gethomedistrict"));
				setError(true);
			}
			if (personaldto.getCaste().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.cast"));
				setError(true);
			}
			if (personaldto.getHeight().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.height"));
				setError(true);
			}

			if (personaldto.getNationality().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.nationality"));
				setError(true);
			}
			if (personaldto.getPhoneNo().trim().length() > 0) {

				if (!CommonRoutines.isNumber(personaldto.getPhoneNo())) {
					errorList.add(property
							.getValue("error.personal.currectmobinumber"));
					setError(true);
				}
			}
			if (personaldto.getMobileNo().trim().length() > 0) {

				if (!CommonRoutines.isNumber(personaldto.getMobileNo())) {

					errorList.add(property
							.getValue("error.personal.mobilenumber1"));
					setError(true);
				}
			}
			if (personaldto.getEmailId().trim().length() > 0) {

				if (!validateEmail(personaldto.getEmailId())) {
					errorList.add(property.getValue("error.personal.emailid"));

				}
			}

			if (personaldto.getPermPin().trim().length() > 0) {

				if (!CommonRoutines.isNumber(personaldto.getPermPin().trim())) {

					errorList.add(property.getValue("error.personal.permpin1"));
					setError(true);
				}
			}
			if (personaldto.getCurrPin().trim().length() > 0) {

				if (!CommonRoutines.isNumber(personaldto.getCurrPin().trim())) {

					errorList.add(property.getValue("error.personal.currpin1"));
					setError(true);
				}
			}
			if (personaldto.getPermAddress().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.permaddress"));
				setError(true);
			}
			if (personaldto.getPermCountry().trim().equals("")) {
				errorList.add(property
						.getValue("error.personal.getpermconutry"));
				setError(true);
			}
			if (personaldto.getPermState().trim().equals("")) {
				errorList.add(property.getValue("error.personal.getpermstate"));
				setError(true);
			}
			if (personaldto.getPermDistrict().trim().equals("")) {
				errorList.add(property.getValue("error.personal.getpermdist"));
				setError(true);
			}
			if (personaldto.getPermPin().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.getPermPin"));
				setError(true);
			}

			if (personaldto.getCurrAddress().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.curraddress"));
				setError(true);
			}

			if (personaldto.getCurrCountry().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.currcountry"));
				setError(true);
			}
			if (personaldto.getCurrState().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.currstate"));
				setError(true);
			}
			if (personaldto.getCurrDistrict().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.currdist"));
				setError(true);
			}
			if (personaldto.getCurrPin().trim().length() == 0) {
				errorList.add(property.getValue("error.personal.currentpenno"));
				setError(true);
			}
			// ////System.out.println("childlist Size   "+childlist.size());
			if (childlist != null) {
				if (childlist.size() > 0) {
					for (int i = 0; i < childlist.size(); i++) {
						if (childlist.get(i) != null) {
							childDTO = (ChildDetailsDTO) childlist.get(i);
						}
						if (childDTO != null) {
							if (childDTO.getChildName().trim().length() == 0) {
								errorlist
										.add(property
												.getValue("error.personaldetail.child.childname"));
								setError(true);
							}
							if (childDTO.getChildGender().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.childlist.gender"));
								setError(true);
							}
							if (childDTO.getChildDay().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.childlist.day"));
								setError(true);
							}
							if (childDTO.getChildMonth().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.childlist.month"));
								setError(true);
							}
							if (childDTO.getChildYear().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.childlist.year"));
								setError(true);
							}
							if (checkDate(childDTO.getChildDay(),
									childDTO.getChildMonth(),
									childDTO.getChildYear(), currSysDate) != true) {
								errorList
										.add(property
												.getValue("error.childlist.childdatevalid"));
								setError(true);
							}
							if (checkFutureDate(childDTO.getChildDay(),
									childDTO.getChildMonth(),
									childDTO.getChildYear(), currSysDate) != true) {
								errorList
										.add(property
												.getValue("error.childlist.childdatefuture"));
								setError(true);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// ////System.out.println("error in personal details" + e);
		}
		return errorList;
	}

	private boolean checkDate(String ed, String em, String ey, Date currSysDate) {
		boolean isValid = false;
		String strDOB = null;
		Date sysdate = currSysDate;
		strDOB = ed + em + ey;
		//31Jan2010
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
			sdf.setLenient(false);
			Date dob = sdf.parse(strDOB);
				isValid = true;
		} catch (java.text.ParseException e) {
			
		}
		catch (Exception e) {
			
		}
		return isValid;
	}
	
	private boolean checkFutureDate(String ed, String em, String ey, Date currSysDate) {
		boolean isValid = false;
		String strDOB = null;
		Date sysdate = currSysDate;
		strDOB = ed + em + ey;
		//31Jan2010
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyyyy");
			sdf.setLenient(false);
			Date dob = sdf.parse(strDOB);
			if(currSysDate.after(dob)) {
				isValid = true;
			}
		} catch (java.text.ParseException e) {
			
		}
		catch (Exception e) {
			
		}
		return isValid;
	}
	
	 public ArrayList validatelocationlist(ArrayList locationlist)throws Exception{
			ArrayList errorList=new ArrayList();
			property=PropertiesFileReader.getInstance("resources.igrs");
			boolean flag = false;
			//errorList.add(pr.getValue("error.header"));
			try{
				
				if(locationlist!=null && locationlist.size()<=0){
					
					flag=true;
					errorlist.add(property.getValue("error.transfer.nolocation"));
					setError(flag);
				}
				
			}catch(Exception e){
				////System.out.println(e);
			}
			return errorList;
			
		}








	    public ArrayList validateEmployeeDetailsList(TransferDTO transferDTO)
	  {
	    	////System.out.println(">>>>>>>>>>error in validate employeelist");
		  boolean flag = false;
	        ArrayList errorList = new ArrayList();
	        try {
	        	property = PropertiesFileReader.getInstance("resources.igrs");
	            errorList.add(property.getValue("error.header"));
	            if (transferDTO.getEmployeeId().trim().length()==0) {
	            	////System.out.println(">>>>>>>>>>transferDTO.getEmployeeId()"+transferDTO.getEmployeeId());
	                        flag = true;
	                        errorList.add(property.getValue("error.transfer.employeeid"));
	                        ////System.out.println(">>>>>>>>>>errorList"+errorList);
	              }
	           
	            if (transferDTO.getNewLoc().trim().length()==0) {
                 flag = true;
                 errorList.add(property.getValue("error.transfer.newLoc"));
               }
	            if (transferDTO.getComments().trim().length()==0) {
                 flag = true;
                 errorList.add(property.getValue("error.transfer.comments"));
               }
	            if (transferDTO.getDateOfJoining().trim().length()==0) {
                 flag = true;
                 errorList.add(property.getValue("error.transfer.dateOfJoining"));
               }
	          ////System.out.println("flag in rule>>>>>>>>>>>>>>.."+flag);
               setError(flag);
               ////System.out.println("flag in rule>>>>>>>>>>>>>>.."+flag);
	          }
	            
	         catch (Exception x) {
//	        	////System.out.println("error");
	        }
	        return errorList;
	  }
	  
	  
	
	
	/**
	 * @param personalChildList
	 * @return ArrayList
	 */
	public ArrayList validatePersonalChildList(ArrayList personalChildList) {
		errorlist = new ArrayList();
		try {
			if (personalChildList != null) {
				for (int i = 0; i < personalChildList.size(); i++) {
					if (personalChildList.get(i) != null) {
						childDTO = (ChildDetailsDTO) personalChildList.get(i);
					}
					if (childDTO != null) {
						if (childDTO.getChildName().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.personaldetail.child.childname"));

							setError(true);
						}
						if (childDTO.getChildGender().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.childlist.gender"));
							setError(true);
						}
						if (childDTO.getChildDay().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.childlist.day"));
							setError(true);
						}
						if (childDTO.getChildMonth().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.childlist.month"));
							setError(true);
						}
						if (childDTO.getChildYear().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.childlist.year"));
							setError(true);
						}
					}
				}
			}
		} catch (Exception e) {
//			////System.out.println("error in validate personal child details" + e);
		}
		return errorlist;

	}

	/**
	 * @param _servicelist
	 * @return ArrayList
	 */
	public ArrayList validateServiceList(ArrayList _servicelist) {
		errorlist = new ArrayList();
		try {
			if (_servicelist != null) {

				{

					for (int i = 0; i < _servicelist.size(); i++) {
						if (_servicelist.get(i) != null) {
							serviceVerificationDTO = (ServiceVerificationDTO) _servicelist
									.get(i);
						}

						if (serviceVerificationDTO != null) {

							if (serviceVerificationDTO.getVerifyingAuthority()
									.trim().length() == 0) {
								errorlist.add(property
										.getValue("error.service.authorName"));

								setError(true);
							}
							if (serviceVerificationDTO.getDateOfVerivication()
									.trim().length() == 0) {
								errorlist
										.add(property
												.getValue("error.service.dateofverification"));
								setError(true);
							}
							if (serviceVerificationDTO.getComments().trim()
									.length() == 0) {
								errorlist.add(property
										.getValue("error.service.comments"));
								setError(true);
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
//			////System.out.println("error" + e);
		}
		return errorlist;
	}

	/**
	 * @param qualiList
	 * @param prevlist
	 * @return ArrayList
	 */
	public ArrayList validateQuali(ArrayList qualiList, ArrayList prevlist) {
		errorlist = new ArrayList();
		try {
			if (qualiList != null) {
				for (int i = 0; i < qualiList.size(); i++) {

					if (qualiList.get(i) != null) {
						acadamicDTO3 = (AcademicDTO) qualiList.get(i);
						if (acadamicDTO3 != null) {
							if (acadamicDTO3.getDegree().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.degree"));
								setError(true);
							}
							////System.out.println("Degree "
									//+ acadamicDTO3.getDegree());
							if (acadamicDTO3.getStream().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.stream"));
								setError(true);
							}
							////System.out.println("passingYear"
									//+ acadamicDTO3.getPassingYear());
							if (acadamicDTO3.getPassingYear().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.passingYear"));
								setError(true);
							}
							if (acadamicDTO3.getGrade().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.grade"));
								setError(true);
							}
						}

					}
				}
			}
			if (prevlist != null) {
				for (int i = 0; i < prevlist.size(); i++) {
					if (prevlist.get(i) != null) {
						prevDTO = (PrevEmpDTO) prevlist.get(i);
						if (prevDTO != null) {
							if (prevDTO.getOrganization().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.organization"));
								setError(true);
							}
							if (prevDTO.getDesignation().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.designation"));
								setError(true);
							}
							if (prevDTO.getFromDate().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.fromDate"));
								setError(true);
							}
//							if (prevDTO.getDurationMonths().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.month"));
//								setError(true);
//							}
//							if (prevDTO.getDurationMonths().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.year"));
//								setError(true);
//							}
//							if (prevDTO.getPfAccLocation().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.accloc"));
//								setError(true);
//							}
//							if (prevDTO.getPfAccNo().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.accno"));
//								setError(true);
//							}
//							if (prevDTO.getReasonForSeparation().trim()
//									.length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.reason"));
//								setError(true);
//							}
//							if (prevDTO.getTaxDeductions().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.taxDeduction"));
//								setError(true);
//							}
//							if (prevDTO.getCompensation().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.compensation"));
//								setError(true);
//							}
						}
					}
				}
			}

		} catch (Exception e) {
//			////System.out.println("error in talent rule" + e);
		}
		return errorlist;
	}

	/**
	 * @param prevlist
	 * @return ArrayList
	 */
	public ArrayList validatePrevEmp(ArrayList prevlist) {
		errorlist = new ArrayList();
		try {
			if (prevlist != null) {
				for (int i = 0; i < prevlist.size(); i++) {
					if (prevlist.get(i) != null) {
						prevDTO = (PrevEmpDTO) prevlist.get(i);
						if (prevDTO != null) {
							if (prevDTO.getOrganization().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.organization"));
								setError(true);
							}
							if (prevDTO.getDesignation().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.designation"));
								setError(true);
							}
							if (prevDTO.getFromDate().trim().length() == 0) {
								errorlist.add(property
										.getValue("error.talent.fromDate"));
								setError(true);
							}
//							if (prevDTO.getDurationMonths().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.month"));
//								setError(true);
//							}
//							if (prevDTO.getDurationMonths().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.year"));
//								setError(true);
//							}
//							if (prevDTO.getPfAccLocation().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.accloc"));
//								setError(true);
//							}
//							if (prevDTO.getPfAccNo().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.accno"));
//								setError(true);
//							}
//							if (prevDTO.getReasonForSeparation().trim()
//									.length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.reason"));
//								setError(true);
//							}
//							if (prevDTO.getTaxDeductions().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.taxDeduction"));
//								setError(true);
//							}
//							if (prevDTO.getCompensation().trim().length() == 0) {
//								errorlist.add(property
//										.getValue("error.talent.compensation"));
//								setError(true);
//							}
						}
					}
				}
			}

		} catch (Exception e) {
//			////System.out.println("error in validatepreve" + e);
		}
		return errorlist;
	}

	/**
	 * @param value
	 * @return ArrayList
	 */
	public ArrayList validateOfficalInfo(boolean value) {
		boolean flag = false;
		ArrayList errorList = new ArrayList();
		try {
			property = PropertiesFileReader.getInstance("resources.igrs");
			if (!value) {
				errorList.add(property.getValue("error.header"));
				// errorList.add(property.getValue("error.empidnotvalid"));
				flag = true;
			} else {
				flag = false;
			}
			setError(flag);
		} catch (Exception e) {
//			////System.out.println("Error in ValidateOfficalInfo-->OfficalInfoRule"
//					+ e);
		}
		return errorList;
	}

	/**
	 * @param bankDTO
	 * @return ArrayList
	 */
	public ArrayList validateBank(BankDTO bankDTO) {
		errorlist = new ArrayList();

		try {
			errorlist.add(property.getValue("error.header"));
			if (bankDTO.getBankAccountNo().trim().length() == 0) {
				errorlist.add(property
						.getValue("error.bank.accountno.required"));
				setError(true);
			} else if(bankDTO.getBankAccountNo().trim().length() > 30) {
				errorlist.add(property
						.getValue("error.bank.accountno.maxlength"));
				setError(true);
			}
			if (bankDTO.getBankAddress().trim().length() == 0) {
				errorlist.add(property
						.getValue("error.bank.bankaddress.required"));
				setError(true);
			} else if (bankDTO.getBankAddress().trim().length() > 50) {
				errorlist.add(property
						.getValue("error.bank.bankaddress.maxlength"));
				setError(true);
			}
			if (bankDTO.getNameAsInBank().trim().length() == 0) {
				errorlist.add(property
						.getValue("error.bank.nameasinbank.required"));
				setError(true);
			} else if (bankDTO.getNameAsInBank().trim().length() > 30) {
				errorlist.add(property
						.getValue("error.bank.nameasinbank.maxlength"));
				setError(true);
			}
			if (bankDTO.getPanNo().trim().length() == 0) {
				errorlist.add(property
						.getValue("error.bank.pannumber.required"));
				setError(true);
			} else if (bankDTO.getPanNo().trim().length() > 20) {
				errorlist.add(property
						.getValue("error.bank.pannumber.maxlength"));
				setError(true);
			}

			if (bankDTO.getBankBranch().trim().length() == 0) {
				errorlist.add(property
						.getValue("error.bank.bankbranch.required"));
				setError(true);
			} else if (bankDTO.getBankBranch().trim().length() > 30) {
				errorlist.add(property
						.getValue("error.bank.bankbranch.maxlength"));
				setError(true);
			}

			if (bankDTO.getBankName().trim().length() == 0) {
				errorlist
						.add(property.getValue("error.bank.bankname.required"));
				setError(true);
			} else if (bankDTO.getBankName().trim().length() > 30) {
				errorlist.add(property
						.getValue("error.bank.bankname.maxlength"));
				setError(true);
			}

		} catch (Exception e) {
//			////System.out.println(e);
		}
		return errorlist;
	}

	/**
	 * @param nomineeslist
	 * @return ArrayList
	 */
	public ArrayList validateNomineeList(ArrayList nomineeslist) {
		errorlist = new ArrayList();
		HashMap<String, ArrayList<String>> accountPercentageMap = new HashMap<String, ArrayList<String>>();
		ArrayList<String> percentList;
		try {
			////System.out.println("Nominee List" + nomineeslist.size());
			if (nomineeslist != null) {
				for (int i = 0; i < nomineeslist.size(); i++) {
					NomineeDTO nomineeDTO = (NomineeDTO) nomineeslist.get(i);
					if(accountPercentageMap.containsKey(nomineeDTO.getStrAccountNumber())) {
						percentList = accountPercentageMap.get(nomineeDTO.getStrAccountNumber());
					} else {
						percentList = new ArrayList<String>();
						accountPercentageMap.put(nomineeDTO.getStrAccountNumber(), percentList);
					}
					percentList.add(nomineeDTO.getNomineePercentage());
					if (nomineeDTO != null) {
						if (nomineeDTO.getNomineeName().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.nominee.name.required"));
							setError(true);
						}
						if (nomineeDTO.getNomineeAge().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.nominee.age.required"));
							setError(true);
						}
						if (nomineeDTO.getNomineeAddress().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.nominee.address.required"));
							setError(true);

						}
						if (nomineeDTO.getRelationWithNominee().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.nominee.relationnominee.required"));
							setError(true);

						}
					}
				}
				int sumTotal, value;
				for (String accountNumber : accountPercentageMap.keySet()) {
					sumTotal = 0;
					percentList = accountPercentageMap.get(accountNumber);
					for (String percent : percentList) {
						try {
							value = Integer.parseInt(percent);
							sumTotal += value;
						} catch (Exception e) {
						}
					}
					if(sumTotal != 100) {
						String message = property
								.getValue("error.nominee.percentageShareTotal");
						message = message + " " + accountNumber;
						errorlist
								.add(message );
						setError(true);
					}
				}
			}
		} catch (Exception e) {
//			////System.out.println(e);
		}
		return errorlist;
	}

	/**
	 * @param fundlist
	 * @param employeeId 
	 * @return ArrayList
	 */
	public ArrayList validateFundList(ArrayList fundlist, String employeeId) {
		errorlist = new ArrayList();

		try {
			boolean isUniqueError = false;
			// errorlist.add(property.getValue("error.header"));
			if (fundlist != null) {
				for (int i = 0; i < fundlist.size(); i++) {
					FundDTO fundDTO = (FundDTO) fundlist.get(i);
					fundDTO.setEmployee_id(employeeId);
					if (fundDTO != null) {
						if (fundDTO.getType().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.fund.fundtype.required"));
							setError(true);
						}
						if (fundDTO.getAccountNo().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.fund.accountno.required"));
							setError(true);
						}
						if (checkDBFundAccount(fundDTO.getAccountNo().trim(), fundDTO.getEmployee_id())) {
							isUniqueError = true;
//							errorlist.add(property
//									.getValue("error.fund.accountno.unique"));
							setError(true);
						}
						if (checkEnterFundAccount(fundDTO.getAccountNo().trim(), i, fundlist)) {
							isUniqueError = true;
//							errorlist.add(property
//									.getValue("error.fund.accountno.unique"));
							setError(true);
						}
						if (fundDTO.getAccountLocation().trim().length() == 0) {
							errorlist
									.add(property
											.getValue("error.fund.accountlocation.required"));
							setError(true);

						}
					}
				}
				if(isUniqueError) {
					String errMsg = property.getValue("error.fund.accountno.unique");
					if(errorlist.contains(errMsg)!= true) {
						errorlist.add(errMsg);
					}
				}
			}
		} catch (Exception e) {
//			////System.out.println(e);
		}
		return errorlist;
	}

	private boolean checkEnterFundAccount(String accountNo, int skipIndex, ArrayList fundlist) {
		boolean retVal = false;
		try {
			for (int iLoop = 0; iLoop < fundlist.size(); iLoop++) {
				if(iLoop == skipIndex) {
					continue;
				}else {
					FundDTO fundDTO = (FundDTO) fundlist.get(iLoop);
					if(fundDTO.getAccountNo().equalsIgnoreCase(accountNo)) {
						retVal = true;
						break;
					}
				}
			}
		} catch (Exception e) {
		}
		return retVal;
	}

	private boolean checkDBFundAccount(String accountNo, String empID) {
		boolean retVal = false;
		try {
			EmpMgntDAO dao = new EmpMgntDAO();
			List data = dao.getExcludedFundAccNo(empID);
			ArrayList<String> dbFundList = new ArrayList<String>();
			for (Object item : data) {
				ArrayList row = (ArrayList) item;
				dbFundList.add((String) row.get(0));
			}
			data.clear();
			if(dbFundList.contains(accountNo)) {
				retVal = true;
			}
			dbFundList.clear();
		} catch (Exception e) {
		}
		return retVal;
	}

	/**
	 * @param result
	 * @return ArrayList
	 */
	public ArrayList setInsertedValue(boolean result) {
		try {
			errorlist = new ArrayList();
			if (result) {

				errorlist.add(property.getValue("success.personal.message"));
			}
		} catch (Exception e) {

		}
		return errorlist;
	}

	/**
	 * @param list
	 * @param name
	 * @return ArrayList
	 */
	public ArrayList validateList(ArrayList list, String name) {

		errorlist = new ArrayList();

		try {
			errorlist.add(property.getValue("error.header"));
			if (list == null || list.size() <= 0) {
				errorlist
						.add(property.getValue("error.list.notpresent") + name);
				setError(true);
			}
		} catch (Exception e) {
//			////System.out.println(e);
		}
		return errorlist;
	}

	/**
	 * @param deptExamDTO
	 * @param examList
	 * @return ArrayList
	 */
	public ArrayList validateDepartmentalExam(DepartmentalExamsDTO deptExamDTO,
			ArrayList examList) {
		errorlist = new ArrayList();
		try {
			setError(false);
			errorlist.add(property.getValue("error.header"));
			if (deptExamDTO.getDeptexamname().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.nameofexam"));
				setError(true);
			}
			if (deptExamDTO.getDateofexam().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.dateofexam"));
				setError(true);
			}
			if (deptExamDTO.getOrgauthority().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.orgauthority"));
				setError(true);
			}
			if (deptExamDTO.getOrgbody().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.orgbody"));
				setError(true);
			}
			if (deptExamDTO.getPlaceofexam().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.examplace"));
				setError(true);
			}
			if (deptExamDTO.getDateofresult().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.resultdate"));
				setError(true);
			}
//			Date comprision START
			if (deptExamDTO.getDateofexam().trim().length() > 0 && deptExamDTO.getDateofresult().trim().length() > 0) {
				    String examDate=deptExamDTO.getDateofexam();
				    String resultDate=deptExamDTO.getDateofresult();
					if(CommonUtil.isGreater(examDate, resultDate)){
						errorlist.add(property.getValue("error.deptexam.dateDiff"));						
						setError(true);
						
					}
			}
			//Date comprision END
			if (deptExamDTO.getExamdetails().trim().length() == 0) {
				errorlist.add(property.getValue("error.deptexam.examdetails"));
				setError(true);
			}
			if (examList != null) {
				for (int i = 0; i < examList.size(); i++) {
					DepartmentalExamsResultDTO resultDTO = (DepartmentalExamsResultDTO) examList
							.get(i);
					if (resultDTO != null) {
						if (resultDTO.getEmpid().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.deptexam.empid"));
							setError(true);
						}
						if (resultDTO.getEmpid().trim().length() > 0) {
							DepartmentalExamsBD deptExamBD = new DepartmentalExamsBD();
							boolean b = deptExamBD
									.checkEmpIdAvailability(resultDTO
											.getEmpid());
							if (!b) {
								errorlist.add(property
										.getValue ("error.deptexam.EmployeeId")+resultDTO.getEmpid());
										
								setError(true);
							}
						}
						if (resultDTO.getResultofexam().trim().length() == 0) {
							errorlist.add(property
									.getValue("error.deptexam.resultofexam"));
							setError(true);
						}
					}

				}
			}

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return errorlist;
	}

	/* added by Ragavedra for Training START */
	  


public ArrayList validateTrainingEmplist(ArrayList employeeList){
		 errorlist=new ArrayList();
		 try{
			 if(employeeList!=null && employeeList.size()<=0){
				 errorlist.add(property.getValue("error.add.trining.employeelist"));
	 				setError(true);
				 
			 }
			 
		 }catch(Exception e){
//			 ////System.out.println(e);
		 }
		return errorlist;
	 }

public ArrayList validateFileType(String fileExt,ArrayList arrayList) {
	 errorlist=new ArrayList();
	boolean flag = false;
	String[] arrFileExt = { "doc","docx", "xls", "xlsx", "pdf", "txt", "jpg", "tiff",
			"gif", "rtf", "zip" };
	boolean flagFile = false;
	try {
		for (int i = 0; i < arrFileExt.length; i++) {
			if (fileExt.equalsIgnoreCase(arrFileExt[i])) {
				flagFile = true;
				break;
			}
		}
		if (!flagFile) {
			errorlist.add(property.getValue("error.audit.fileType"));
			flag = true;
			setError(flag);
		}
		for(int i=0;i<arrayList.size();i++){
			////System.out.println("arraylist"+arrayList.size());
			EmpmgmtUploadDTO uploadDTO=(EmpmgmtUploadDTO)arrayList.get(i);
			for(int j=i;j<arrayList.size();j++){
				if(i!=j){
				EmpmgmtUploadDTO uploadDTO1=(EmpmgmtUploadDTO)arrayList.get(j);
				if(uploadDTO.getDocumenttype().equalsIgnoreCase(uploadDTO1.getDocumenttype())){
				errorlist.add(property.getValue("error.empmgmt.samefiletype"));
				flag = true;
				setError(flag);
				}
				if(uploadDTO.getFileName().equalsIgnoreCase(uploadDTO1.getFileName()))
				{
				errorlist.add(property.getValue("error.audit.samefile"));
				flag = true;
				setError(flag);
				}
				}
			}
		}
	} catch (Exception ex) {
//		ex.printStackTrace();
	}
	return errorlist;
}

	
}

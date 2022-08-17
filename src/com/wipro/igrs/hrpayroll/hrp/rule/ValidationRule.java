package com.wipro.igrs.hrpayroll.hrp.rule;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.wipro.igrs.hrpayroll.hrp.bd.HRPayrollBD;
import com.wipro.igrs.hrpayroll.hrp.dto.CadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeCadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.SalaryDTO;
import com.wipro.igrs.hrpayroll.hrp.form.HrForm;
import com.wipro.igrs.util.CommonRoutines;
import com.wipro.igrs.util.PropertiesFileReader;

public class ValidationRule {
	private boolean error;

	private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger.getLogger(ValidationRule.class);
	HRPayrollBD hrBD = null;

	/**
	 * @throws Exception
	 */
	public ValidationRule() throws Exception {

		logger.info("in Validation Constructor()");
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		hrBD = new HRPayrollBD();
	}

	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return
	 */
	public boolean getError() {
		return error;
	}

	/**
	 * @return
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param str
	 * @return
	 */
	public boolean nullOrBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateCadreMasterAdd(Object form) throws Exception {
		setError(false);
		HrForm myForm = (HrForm) form;
		CadreDTO cadreDTO = myForm.getCadreDTO();
		ArrayList errorList = new ArrayList();
		boolean b = false;

		if (!nullOrBlank(cadreDTO.getCadreName())) {
			b = hrBD.checkCadreNameExists(cadreDTO.getCadreName().trim()
					.toUpperCase());
			if (b) {
				errorList
						.add(pr.getValue("error.cadre.cadreNameAlreadyExists"));
				error = true;
			}
		}
		if (nullOrBlank(cadreDTO.getCadreName())) {
			error = true;
			errorList.add(pr.getValue("error.cadre.designationName"));
		}
		if (nullOrBlank(cadreDTO.getCadrePosts())) {
			error = true;
			errorList.add(pr.getValue("error.cadre.no_of_posts"));
		}
		if (!CommonRoutines.isNumber(cadreDTO.getCadrePosts())) {
			error = true;
			errorList.add(pr.getValue("error.cadre.no_of_posts_number_only"));
		}
		setError(error);

		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();

		return errorList;

	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateCadreMasterEdit(Object form) throws Exception {

		boolean b = false;
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		CadreDTO cadreDTO = myForm.getCadreDTO();
		String chkCadre[] = cadreDTO.getChkCadre();
		String editCadreIds[] = cadreDTO.getEditCadresIds();

		StringTokenizer st = new StringTokenizer(chkCadre[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String cadreIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editCadreIds.length; j++) {

				if (s.equals(editCadreIds[j])) {
					indexArray[ind] = j;
					cadreIdsArray[ind] = s;
					ind++;
				}
			}
		}

		String cadreIds[] = cadreDTO.getEditCadresIds();
		String cadrePosts[] = cadreDTO.getEditCadresPosts();

		for (int i = 0; i < indexArray.length; i++) {
			int index = indexArray[i];

			if (nullOrBlank(cadrePosts[index])) {
				error = true;
				errorList.add(pr.getValue("error.cadre.no_of_posts"));
			}
			if (!CommonRoutines.isNumber(cadrePosts[index])) {
				error = true;
				errorList.add(pr
						.getValue("error.cadre.no_of_posts_number_only"));
			}
			setError(error);

		}
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();

		return errorList;
	}// method

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateGradeMasterAdd(Object form) throws Exception {
		setError(false);
		HrForm myForm = (HrForm) form;
		GradeDTO gradeDTO = myForm.getGradeDTO();
		ArrayList errorList = new ArrayList();

		if (!nullOrBlank(gradeDTO.getGradeName())) {
			boolean b = hrBD.checkGradeNameExists(gradeDTO.getGradeName()
					.trim().toUpperCase());

			if (b) {
				errorList
						.add(pr.getValue("error.grade.gradeNameAlreadyExists"));
				error = true;
			}
		}

		if (nullOrBlank(gradeDTO.getGradeName())) {
			error = true;
			errorList.add(pr.getValue("error.grade.gradeName"));
		}
		if (nullOrBlank(gradeDTO.getMinSalSlab())) {
			error = true;
			errorList.add(pr.getValue("error.grade.minSlab"));
		}
		if (nullOrBlank(gradeDTO.getMaxSalSlab())) {
			error = true;
			errorList.add(pr.getValue("error.grade.maxSlab"));
		}
		if (nullOrBlank(gradeDTO.getIncreeAmount())) {
			error = true;
			errorList.add(pr.getValue("error.grade.increeAmount"));
		}
		if (!CommonRoutines.isAmount(gradeDTO.getMinSalSlab())) {
			error = true;
			errorList.add(pr.getValue("error.grade.minSalSlab_amountOnly"));
		}
		if (!CommonRoutines.isAmount(gradeDTO.getMaxSalSlab())) {
			error = true;
			errorList.add(pr.getValue("error.grade.maxSalSlab_amountOnly"));
		}
		if (!CommonRoutines.isAmount(gradeDTO.getIncreeAmount())) {
			error = true;
			errorList.add(pr.getValue("error.grade.increeAmount_amountOnly"));
		}
		if (!nullOrBlank(gradeDTO.getMinSalSlab())
				&& CommonRoutines.isNumber(gradeDTO.getMinSalSlab())) {
			if (!nullOrBlank(gradeDTO.getMaxSalSlab())
					&& CommonRoutines.isNumber(gradeDTO.getMaxSalSlab())) {
				float minf = Float.parseFloat(gradeDTO.getMinSalSlab());
				float maxf = Float.parseFloat(gradeDTO.getMaxSalSlab());

				if (maxf <= minf) {
					error = true;
					errorList.add(pr.getValue("error.grade.min-max"));
				}
				if (!nullOrBlank(gradeDTO.getIncreeAmount())
						&& CommonRoutines.isNumber(gradeDTO.getIncreeAmount())) {
					float incf = Float.parseFloat(gradeDTO.getIncreeAmount());

					if (incf >= maxf) {
						error = true;
						errorList.add(pr.getValue("error.grade.inc-max"));
					}
				}
			}
		}
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateGradeMasterEdit(Object form) throws Exception {
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		GradeDTO gradeDTO = myForm.getGradeDTO();
		String chkGrade[] = gradeDTO.getChkGrade();
		String editGradeIds[] = gradeDTO.getEditGradeIds();

		StringTokenizer st = new StringTokenizer(chkGrade[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String gradeIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editGradeIds.length; j++) {

				if (s.equals(editGradeIds[j])) {
					indexArray[ind] = j;
					gradeIdsArray[ind] = s;
					ind++;
				}
			}
		}
		String gradeMinSalSlabs[] = gradeDTO.getEditGradeMinSalSlabs();
		String gradeMaxSalSlabs[] = gradeDTO.getEditGradeMaxSalSlabs();
		String gradeIncreeAmounts[] = gradeDTO.getEditGradeIncreeAmounts();

		for (int i = 0; i < indexArray.length; i++) {
			int index = indexArray[i];

			if (nullOrBlank(gradeMinSalSlabs[index])) {
				error = true;
				errorList.add(pr.getValue("error.grade.minSlab"));
			}
			if (nullOrBlank(gradeMinSalSlabs[index])) {
				error = true;
				errorList.add(pr.getValue("error.grade.maxSlab"));
			}
			if (nullOrBlank(gradeIncreeAmounts[index])) {
				error = true;
				errorList.add(pr.getValue("error.grade.increeAmount"));
			}
			if (!nullOrBlank(gradeMinSalSlabs[index])
					&& !CommonRoutines.isAmount(gradeMinSalSlabs[index])) {
				error = true;
				errorList.add(pr.getValue("error.grade.minSalSlab_amountOnly"));
			}
			if (!nullOrBlank(gradeMaxSalSlabs[index])
					&& !CommonRoutines.isAmount(gradeMaxSalSlabs[index])) {
				error = true;
				errorList.add(pr.getValue("error.grade.maxSalSlab_amountOnly"));
			}
			if (!nullOrBlank(gradeIncreeAmounts[index])
					&& !CommonRoutines.isAmount(gradeIncreeAmounts[index])) {
				error = true;
				errorList.add(pr
						.getValue("error.grade.increeAmount_amountOnly"));
			}
			if (!nullOrBlank(gradeMinSalSlabs[index])
					&& CommonRoutines.isNumber(gradeMinSalSlabs[index])) {
				if (!nullOrBlank(gradeMaxSalSlabs[index])
						&& CommonRoutines.isNumber(gradeMaxSalSlabs[index])) {
					float minf = Float.parseFloat(gradeMinSalSlabs[index]);
					float maxf = Float.parseFloat(gradeMaxSalSlabs[index]);

					if (maxf <= minf) {
						error = true;
						errorList.add(pr.getValue("error.grade.min-max"));
					}
					if (!nullOrBlank(gradeIncreeAmounts[index])
							&& CommonRoutines
									.isNumber(gradeIncreeAmounts[index])) {
						float incf = Float
								.parseFloat(gradeIncreeAmounts[index]);

						if (incf >= maxf) {
							error = true;
							errorList.add(pr.getValue("error.grade.inc-max"));
						}
					}
				}
			}
		}// for loop
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}// method

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateLeaveMasterAdd(Object form) throws Exception {
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		LeaveDTO leaveDTO = myForm.getLeaveDTO();

		if (!nullOrBlank(leaveDTO.getLeave_type_name())) {
			boolean b = hrBD.checkLeaveTypeExists(leaveDTO.getLeave_type_name()
					.trim().toUpperCase());
			if (b) {
				errorList
						.add(pr.getValue("error.leave.leaveTypeAlreadyExists"));
				error = true;
			}
		}
		if (nullOrBlank(leaveDTO.getLeave_type_name())) {
			error = true;
			errorList.add(pr.getValue("error.leave.leaveType"));
		}
		if (nullOrBlank(leaveDTO.getGender())) {
			error = true;
			errorList.add(pr.getValue("error.leave.gender"));
		}
		if (nullOrBlank(leaveDTO.getMaximum_no_days())) {
			error = true;
			errorList.add(pr.getValue("error.leave.days"));
		}
		if (nullOrBlank(leaveDTO.getLeave_type_desc())) {
			error = true;
			errorList.add(pr.getValue("error.leave.remarks"));
		}
		if (!nullOrBlank(leaveDTO.getMaximum_no_days())
				&& !CommonRoutines.isNumber(leaveDTO.getMaximum_no_days())) {
			error = true;
			errorList.add(pr.getValue("error.leave.days_numberOnly"));
		}
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateLeaveMasterEdit(Object form) throws Exception {
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		LeaveDTO leaveDTO = myForm.getLeaveDTO();

		String chkLeave[] = leaveDTO.getChkLeave();
		String editLeaveIds[] = leaveDTO.getEditLeaveIds();

		StringTokenizer st = new StringTokenizer(chkLeave[0], ",");
		int indexArray[] = new int[st.countTokens()];
		String leaveIdsArray[] = new String[st.countTokens()];
		int ind = 0;
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			for (int j = 0; j < editLeaveIds.length; j++) {

				if (s.equals(editLeaveIds[j])) {
					indexArray[ind] = j;
					leaveIdsArray[ind] = s;
					ind++;
				}
			}
		}

		// String leaveTypes[] = leaveDTO.getEditLeaveTypes();
		String leaveGenders[] = leaveDTO.getEditLeaveGender();
		String leaveDays[] = leaveDTO.getEditLeaveDays();
		String leaveDescs[] = leaveDTO.getEditLeaveDesc();

		/*
		 * if(this.checkDuplicateStrings(leaveTypes)) {
		 * errorList.add(pr.getValue("error.leave.leaveTypeAlreadyExists"));
		 * error = true; }
		 */
		// errorList.add(pr.getValue("error.header"));
		for (int i = 0; i < indexArray.length; i++) {
			int index = indexArray[i];
			/*
			 * if( nullOrBlank(leaveTypes[index]) ) { error = true;
			 * errorList.add(pr.getValue("error.leave.leaveType")); }
			 */
			if (nullOrBlank(leaveGenders[index])) {
				error = true;
				errorList.add(pr.getValue("error.leave.gender"));
			}
			if (nullOrBlank(leaveDays[index])) {
				error = true;
				errorList.add(pr.getValue("error.leave.days"));
			}
			if (nullOrBlank(leaveDescs[index])) {
				error = true;
				errorList.add(pr.getValue("error.leave.remarks"));
			}

			if (!nullOrBlank(leaveDays[index])
					&& !CommonRoutines.isNumber(leaveDays[index])) {
				error = true;
				errorList.add(pr.getValue("error.leave.days_numberOnly"));
			}
		}// for loop ends here
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}// method

	// Grade - Cadre Mapping
	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateGradeCadre(Object form) throws Exception {
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		GradeCadreDTO gcDTO = myForm.getGcDTO();
		String gradeId = gcDTO.getGradeId();

		if (nullOrBlank(gradeId)) {
			error = true;
			errorList.add(pr.getValue("error.grade.gradeId"));
		}
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateSalaryStart(Object form) throws Exception {
		setError(false);
		ArrayList errorList = new ArrayList();
		HrForm myForm = (HrForm) form;
		SalaryDTO salDTO = myForm.getSalaryDTO();
		String gradeId = salDTO.getGradeId();
		String cadreId = salDTO.getCadreId();
		if (nullOrBlank(gradeId)) {
			error = true;
			errorList.add(pr.getValue("error.salary.gradeId"));
		}
		if (nullOrBlank(cadreId)) {
			error = true;
			errorList.add(pr.getValue("error.salary.cadreId"));
		}
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateSalaryDisplay(Object form) throws Exception {
		setError(false);
		HrForm myForm = (HrForm) form;
		SalaryDTO salDTO = myForm.getSalaryDTO();
		ArrayList errorList = new ArrayList();
		boolean b = false;

		if (nullOrBlank(salDTO.getGradeId())) {
			error = true;
			errorList.add(pr.getValue("error.salary.gradeId"));
		}
		if (nullOrBlank(salDTO.getCadreId())) {
			error = true;
			errorList.add(pr.getValue("error.salary.cadreId"));
		}
		if (nullOrBlank(salDTO.getComponentType())) {
			error = true;
			errorList.add(pr.getValue("error.salary.componentType"));
		}
		setError(error);
		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();
		return errorList;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateEmployeeGradeCadreSalaryMapping(Object form)
			throws Exception {
		setError(false);
		HrForm myForm = (HrForm) form;
		SalaryDTO salaryDTO = myForm.getSalaryDTO();
		ArrayList errorList = new ArrayList();

		if (nullOrBlank(salaryDTO.getEmpId())) {
			error = true;
			// errorList.add(pr.getValue("error.header"));
			errorList.add(pr.getValue("error.salary.empId"));
		}

		if (nullOrBlank(salaryDTO.getTypeId())) {
			error = true;
			// errorList.add(pr.getValue("error.header"));
			errorList.add(pr.getValue("error.salary.typeId"));
		}
		setError(error);

		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();

		return errorList;

	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList validateSalaryComponentMapping(Object form)
			throws Exception {
		setError(false);
		HrForm myForm = (HrForm) form;
		SalaryDTO salaryDTO = myForm.getSalaryDTO();
		ArrayList errorList = new ArrayList();

		if (nullOrBlank(salaryDTO.getGradeId())) {
			error = true;
			// errorList.add(pr.getValue("error.header"));
			errorList.add(pr.getValue("error.salary.gradeId"));
		}
		if (nullOrBlank(salaryDTO.getCadreId())) {
			error = true;
			// errorList.add(pr.getValue("error.header"));
			errorList.add(pr.getValue("error.salary.cadreId"));
		}
		if (nullOrBlank(salaryDTO.getTypeId())) {
			error = true;
			// errorList.add(pr.getValue("error.header"));
			errorList.add(pr.getValue("error.salary.typeId"));
		}
		setError(error);

		if (isError())
			errorList.add(0, pr.getValue("error.header"));
		errorList.trimToSize();

		return errorList;

	}

}// class

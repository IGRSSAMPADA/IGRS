/*
 * ValidationRule.java
 */


package com.wipro.igrs.loanadvance.rule;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.loanadvance.bd.LoanBD;
import com.wipro.igrs.loanadvance.bd.LoanadvanceBD;
import com.wipro.igrs.loanadvance.dto.LoanDTO;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;
import com.wipro.igrs.loanadvance.form.LoanForm;
import com.wipro.igrs.loanadvance.form.LoanadvanceForm;
import com.wipro.igrs.util.CommonRoutines;
import com.wipro.igrs.util.PropertiesFileReader;


/**
 * @author jagadish
 * 
 */
public class ValidationRule {
	private boolean			error;

	PropertiesFileReader	pr				= null;

	LoanadvanceBD			loanadvanceBD	= null;

	LoanBD					loanBD			= null;
	private Logger logger = (Logger) Logger.getLogger(ValidationRule.class);
	public ValidationRule() throws Exception {
		System.out.println("  Inside  ValidationRule ");
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		loanadvanceBD = new LoanadvanceBD();
	}

	/**
	 * @return boolean
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * @return boolean
	 */
	public boolean getError() {
		return error;
	}

	/**
	 * @param str
	 * @return boolean
	 */
	public boolean nullOrBlank(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}

	/**
	 * @param form
	 * @return ArrayList @
	 */
	public ArrayList validateAdvanceMaster(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {

			setError(false);
			LoanadvanceForm advanceForm = (LoanadvanceForm) form;
			LoanadvanceDTO loanadvanceDTO = advanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();

			if (!nullOrBlank(loanadvanceDTO.getAdvancetypename())) {
				b = loanadvanceBD.checkAdvanceName(loanadvanceDTO
						.getAdvancetypename().trim().toUpperCase());
				if (b) {
					errorList
							.add(pr
									.getValue("error.advance.advanceTypeAlreadyExists"));
					error = true;
				}
			}
			if (nullOrBlank(loanadvanceDTO.getAdvancetypename())) {
				error = true;
				errorList.add(pr.getValue("error.advance.advanceType"));
			}
			if (nullOrBlank(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.advanceAmount"));
			}
			if (nullOrBlank(loanadvanceDTO.getInstallmentno())) {
				error = true;
				errorList.add(pr.getValue("error.advance.installmentnos"));
			}
			if (nullOrBlank(loanadvanceDTO.getAdvancedescription())) {
				error = true;
				errorList.add(pr.getValue("error.advance.descriptions"));
			}
			if (!CommonRoutines.isAmount(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.amount_only"));
			}
			if (!CommonRoutines.isNumber(loanadvanceDTO.getInstallmentno())) {
				error = true;
				errorList.add(pr.getValue("error.advance.installments_only"));
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;

	}

	/**
	 * @param form
	 * @return ArrayList @
	 */
	public ArrayList validateAdvanceComponent(Object form) {
		ArrayList errorList = new ArrayList();
		try {

			setError(false);
			LoanadvanceForm advanceForm = (LoanadvanceForm) form;
			LoanadvanceDTO loanadvanceDTO = advanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();

			if (nullOrBlank(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.advanceAmount"));
			}
			if (nullOrBlank(loanadvanceDTO.getInstallmentno())) {
				error = true;
				errorList.add(pr.getValue("error.advance.installmentnos"));
			}
			if (nullOrBlank(loanadvanceDTO.getAdvancedescription())) {
				error = true;
				errorList.add(pr.getValue("error.advance.descriptions"));
			}
			if (!CommonRoutines.isAmount(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.amount_only"));
			}
			if (!CommonRoutines.isNumber(loanadvanceDTO.getInstallmentno())) {
				error = true;
				errorList.add(pr.getValue("error.advance.installments_only"));
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList chkempId(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {
			setError(false);
			LoanadvanceForm advanceForm = (LoanadvanceForm) form;
			LoanadvanceDTO loanadvanceDTO = advanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();
			if (!nullOrBlank(loanadvanceDTO.getEmpid())) {
				b = loanadvanceBD.checkEmpId(loanadvanceDTO.getEmpid().trim());
				if (!b) {
					errorList.add(pr
							.getValue("error.advance.EmployeeIdDoesnotExists"));
					error = true;
				}
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList referenceId(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {
			setError(false);
			LoanadvanceForm advanceForm = (LoanadvanceForm) form;
			LoanadvanceDTO loanadvanceDTO = advanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();
			if (!nullOrBlank(loanadvanceDTO.getAdvancetxnid())) {
				b = loanadvanceBD.checkRefId(loanadvanceDTO.getAdvancetxnid()
						.trim());
				if (!b) {
					errorList
							.add(pr
									.getValue("error.advance.ReferenceNumberDoesnotExists"));
					error = true;
				}
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param form
	 * @return ArrayList
	 * 
	 */
	public ArrayList validateApplyAdvanceComponent(Object form) {
		ArrayList errorList = new ArrayList();
		try {

			setError(false);
			LoanadvanceForm advanceForm = (LoanadvanceForm) form;
			LoanadvanceDTO loanadvanceDTO = advanceForm.getLoanadvanceDTO();
			loanadvanceBD = new LoanadvanceBD();

			String amount = "";
			String user = "";
			float fixedAmount = 0;

			if (CommonRoutines.isAmount(loanadvanceDTO.getAdvanceamount())) {
				amount = loanadvanceBD.checkAdvanceAmount(loanadvanceDTO
						.getAdvancetypename());

				if (amount != null) {
					fixedAmount = Float.parseFloat(amount);
				}
				user = loanadvanceDTO.getAdvanceamount();
				float userAmount = Float.parseFloat(user);
				if (fixedAmount < userAmount) {
					errorList
							.add(pr
									.getValue("error.advance.advanceAmountExceedsThanFixValue"));
					error = true;
				}
			}
			if (nullOrBlank(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.advanceAmount"));
			}

			if (nullOrBlank(loanadvanceDTO.getAdvancedescription())) {
				error = true;
				errorList.add(pr.getValue("error.advance.descriptions"));
			}
			if (!CommonRoutines.isAmount(loanadvanceDTO.getAdvanceamount())) {
				error = true;
				errorList.add(pr.getValue("error.advance.amount_only"));
			}

			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList validateloanMaster(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {

			setError(false);
			LoanForm loanForm = (LoanForm) form;
			LoanDTO loanDTO = loanForm.getLoanDTO();
			loanBD = new LoanBD();

			if (!nullOrBlank(loanDTO.getLoantype())) {
				b = loanBD.checkLoanName(loanDTO.getLoantype().trim()
						.toUpperCase());
				if (b) {
					errorList.add(pr
							.getValue("error.loan.loanTypeAlreadyExists"));
					error = true;
				}
			}
			if (nullOrBlank(loanDTO.getLoantype())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loanType"));
			}
			if (nullOrBlank(loanDTO.getLoanminamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.minLoanAmount"));
			}
			if (nullOrBlank(loanDTO.getLoanmaxamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.maxLoanAmount"));
			}
			if (nullOrBlank(loanDTO.getLoanmininstalment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.minLoaninstallments"));
			}
			if (nullOrBlank(loanDTO.getLoanmaxinstalment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.maxLoanInstallments"));
			}
			if (nullOrBlank(loanDTO.getLoaninteresttype())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loaninterestType"));
			}
			if (nullOrBlank(loanDTO.getLoaninterestrate())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loaninterestrate"));
			}
			if (nullOrBlank(loanDTO.getLoandescription())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loandescription"));
			}
			if (!CommonRoutines.isAmount(loanDTO.getLoanminamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.minloanamount_only"));
			}
			if (!CommonRoutines.isAmount(loanDTO.getLoanmaxamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.maxloanamount_only"));
			}
			if (!CommonRoutines.isNumber(loanDTO.getLoanmininstalment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.mininstallments_only"));
			}
			if (!CommonRoutines.isNumber(loanDTO.getLoanmaxinstalment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.maxinstallments_only"));
			}
			if (!CommonRoutines.isNumber(loanDTO.getLoaninterestrate())) {
				error = true;
				errorList.add(pr.getValue("error.loan.interestrate_only"));
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;

	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList validateAppliedloan(Object form) {
		ArrayList errorList = new ArrayList();
		try {

			setError(false);
			LoanForm loanForm = (LoanForm) form;
			LoanDTO loanDTO = loanForm.getLoanDTO();
			loanBD = new LoanBD();

			String amount = "";
			String userAmount = "";
			String installment = "";
			float fixedAmount = 0;
			float user = 0;

			if (!CommonRoutines.isAmount(loanDTO.getLoanamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loanamount_only"));
			}
			if (!CommonRoutines.isNumber(loanDTO.getUserinstallment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loannstallments_only"));
			}

			if (CommonRoutines.isAmount(loanDTO.getLoanamount())) {
				amount = loanBD.cheakLoanAmount(loanDTO.getLoanid());

				if (amount != null) {
					fixedAmount = Float.parseFloat(amount);
				}
				userAmount = loanDTO.getLoanamount();

				if (!userAmount.equals("")) {
					user = Float.parseFloat(userAmount);
				}
				if (fixedAmount < user) {
					errorList
							.add(pr
									.getValue("error.loan.loanAmountExceedsThanFixValue"));
					error = true;
				}
			}

			if (userAmount.equals("")) {
				errorList.add(pr.getValue("error.loan.notpresent"));
				error = true;
			}
			if (CommonRoutines.isNumber(loanDTO.getUserinstallment())) {
				int fixedInstallment = 0;
				amount = loanBD.cheakInstallmentNo(loanDTO.getLoanid());
				if (!amount.equals("")) {
					fixedInstallment = Integer.parseInt(amount);
				}
				installment = loanDTO.getUserinstallment();
				if (!installment.equals("")) {
					user = Integer.parseInt(installment);
				}

				if (fixedInstallment < user) {
					errorList
							.add(pr
									.getValue("error.loan.loanInstallmentExceedsThanFixValue"));
					error = true;
				}

			}

			if (nullOrBlank(loanDTO.getLoanamount())) {
				error = true;
				errorList.add(pr.getValue("error.loan.LoanAmount"));
			}

			if (nullOrBlank(loanDTO.getUserinstallment())) {
				error = true;
				errorList.add(pr.getValue("error.loan.Loaninstallments"));
			}

			if (nullOrBlank(loanDTO.getLoandescription())) {
				error = true;
				errorList.add(pr.getValue("error.loan.loandescription"));
			}

			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;

	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList chkloanempId(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {
			setError(false);
			LoanForm loanForm = (LoanForm) form;
			LoanDTO loanDTO = loanForm.getLoanDTO();
			loanBD = new LoanBD();
			if (!nullOrBlank(loanDTO.getEmpid())) {
				b = loanBD.chkloanempId(loanDTO.getEmpid().trim());
				if (!b) {
					errorList.add(pr
							.getValue("error.advance.EmployeeIdDoesnotExists"));
					error = true;
				}
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}

	/**
	 * @param form
	 * @return ArrayList
	 */
	public ArrayList loanReferenceId(Object form) {
		ArrayList errorList = new ArrayList();
		boolean b = false;
		try {
			setError(false);
			LoanForm loanForm = (LoanForm) form;
			LoanDTO loanDTO = loanForm.getLoanDTO();
			loanBD = new LoanBD();
			if (!nullOrBlank(loanDTO.getLoantxnid())) {
				b = loanBD.checkLoanRefId(loanDTO.getLoantxnid().trim());
				if (!b) {
					errorList
							.add(pr
									.getValue("error.advance.ReferenceNumberDoesnotExists"));
					error = true;
				}
			}
			setError(error);
			logger.info("isError():" + isError());
			if (isError())
				errorList.add(0, pr.getValue("error.header"));
			errorList.trimToSize();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return errorList;
	}
}

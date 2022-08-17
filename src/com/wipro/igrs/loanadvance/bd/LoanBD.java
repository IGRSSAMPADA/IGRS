/**
 * LoanBD.java
 */


package com.wipro.igrs.loanadvance.bd;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.loanadvance.dao.LoanDAO;
import com.wipro.igrs.loanadvance.dto.LoanDTO;


/**
 * @author jagadish
 * 
 */
public class LoanBD {

	private LoanDAO	loanDAO	= new LoanDAO();
	private Logger logger = (Logger) Logger.getLogger(LoanBD.class);
	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanMasterComponentsList() {
		ArrayList _loanMasterList = null;
		try {

			ArrayList loanMasterList = loanDAO.getLoanMasterComponentsList();
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoanid((String) loanList.get(0));
						loanDTO.setLoantype((String) loanList.get(1));
						loanDTO.setLoanminamount((String) loanList.get(2));
						loanDTO.setLoanmaxamount((String) loanList.get(3));
						loanDTO.setLoaninterestrate((String) loanList.get(4));
						_loanMasterList.add(loanDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param loanDTO
	 * @param userid
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertLoanMasterComponents(LoanDTO loanDTO, String userid) {
		boolean insert = false;
		try {
			insert = loanDAO.insertLoanMasterComponents(loanDTO, userid);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return insert;

	}

	/**
	 * @param componentid
	 * @return ArrayList
	 */
	public ArrayList getLoanMasterComponentsListForUpdate(String componentid) {
		ArrayList _loanMasterList = null;
		try {

			ArrayList loanMasterList = loanDAO
					.getLoanMasterComponentsListForUpdate(componentid);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoantype((String) loanList.get(0));
						loanDTO.setLoanminamount((String) loanList.get(1));
						loanDTO.setLoanmaxamount((String) loanList.get(2));
						loanDTO.setLoanmininstalment((String) loanList.get(3));
						loanDTO.setLoanmaxinstalment((String) loanList.get(4));
						loanDTO.setLoaninteresttype((String) loanList.get(5));
						loanDTO.setLoaninterestrate((String) loanList.get(6));
						loanDTO.setLoandescription((String) loanList.get(7));
						loanDTO.setLoanid((String) loanList.get(8));
						_loanMasterList.add(loanDTO);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param list
	 * @param loanDTO
	 * @param userId
	 * @return boolean
	 */
	public boolean updateLoanComponents(ArrayList list, LoanDTO loanDTO,
			String userId) {
		boolean update = false;
		try {
			update = loanDAO.updateLoanComponents(list, loanDTO, userId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanType() {

		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO.getLoanType();
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoanid((String) loanList.get(0));
						loanDTO.setLoantype((String) loanList.get(1));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return _loanMasterList;
	}

	/**
	 * @param loanid
	 * @return ArrayList
	 */
	public ArrayList getMaxLoanAmount(String loanid) {
		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO.getMaxLoanAmount(loanid);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setFixedloanamount((String) loanList.get(0));
						loanDTO.setFixedloaninstallments((String) loanList
								.get(1));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param loanDTO
	 * @param UserId
	 * @return String
	 */
	public String insertAppliedLoan(LoanDTO loanDTO, String UserId) {
		String txnId = "";
		try {
			txnId = loanDAO.insertAppliedLoan(loanDTO, UserId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txnId;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanDetails(String empId) {
		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO.getLoanDetails(empId);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoantxnid((String) loanList.get(0));
						loanDTO.setLoanamount((String) loanList.get(1));
						loanDTO.setLoanstatus((String) loanList.get(2));
						loanDTO.setLoanid((String) loanList.get(3));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getLoanApprovalComponents(String componentId) {
		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO
					.getLoanApprovalComponents(componentId);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoantxnid((String) loanList.get(0));
						loanDTO.setEmpid((String) loanList.get(1));
						loanDTO.setLoanid((String) loanList.get(2));
						loanDTO.setUserinstallment((String) loanList.get(3));
						loanDTO.setLoanamount((String) loanList.get(4));
						loanDTO.setLoandescription((String) loanList.get(5));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getLoanApprovalType(String componentId) {
		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO.getLoanApprovalType(componentId);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoanid((String) loanList.get(0));
						loanDTO.setLoantype((String) loanList.get(1));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param approveList
	 * @param loanDTO
	 * @param userId
	 * @return boolean
	 */
	public boolean getLoanApproved(ArrayList approveList, LoanDTO loanDTO,
			String userId) {
		boolean result = false;
		try {
			result = loanDAO.getLoanApproved(approveList, loanDTO, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param loantxnid
	 * @return ArrayList
	 */
	public ArrayList getLoanStatusDetails(String loantxnid) {
		ArrayList _loanMasterList = null;
		try {
			ArrayList loanMasterList = loanDAO.getLoanStatusDetails(loantxnid);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoanamount((String) loanList.get(0));
						loanDTO.setUserinstallment((String) loanList.get(1));
						loanDTO.setRequestedDate((String) loanList.get(2));
						loanDTO.setLoandescription((String) loanList.get(3));
						loanDTO.setComments((String) loanList.get(4));
						loanDTO.setLoanstatus((String) loanList.get(5));
						loanDTO.setApproverid((String) loanList.get(6));
						_loanMasterList.add(loanDTO);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param loantxnid
	 * @return ArrayList
	 */
	public ArrayList getLoanPaymentDetails(String loantxnid) {
		ArrayList _loanMasterList = null;
		LoanDTO loanDTO = null;
		try {
			ArrayList loanMasterList = loanDAO.getLoanPaymentDetails(loantxnid);
			_loanMasterList = new ArrayList();
			logger.info("Size in BD: " + loanMasterList.size());
			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {

					loanDTO = new LoanDTO();
					loanDTO.setPaidAmount((String) loanMasterList.get(0));
					loanDTO.setPaidinstallments((String) loanMasterList.get(1));
					loanDTO.setPendinginstallments((String) loanMasterList
							.get(2));
					loanDTO.setPendingAmount((String) loanMasterList.get(3));

				}

			}
			_loanMasterList.add(loanDTO);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param loantxnid
	 * @return ArrayList
	 */
	public ArrayList getLoanType(String loantxnid) {
		ArrayList _loanMasterList = null;
		try {

			ArrayList loanMasterList = loanDAO.getLoanType(loantxnid);
			_loanMasterList = new ArrayList();

			if (loanMasterList != null) {
				for (int i = 0; i < loanMasterList.size(); i++) {
					ArrayList loanList = (ArrayList) loanMasterList.get(i);
					if (loanList != null) {
						LoanDTO loanDTO = new LoanDTO();
						loanDTO.setLoantype((String) loanList.get(0));
						_loanMasterList.add(loanDTO);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return _loanMasterList;
	}

	/**
	 * @param LoanType
	 * @return boolean
	 */
	public boolean checkLoanName(String LoanType) {
		boolean result = false;
		try {
			result = loanDAO.checkLoanName(LoanType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param loantypename
	 * @return String
	 */
	public String cheakLoanAmount(String loantypename) {
		String amount = "";
		try {

			amount = loanDAO.cheakLoanAmount(loantypename);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

	/**
	 * @param loantypename
	 * @return String
	 */
	public String cheakInstallmentNo(String loantypename) {
		String amount = "";
		try {

			amount = loanDAO.cheakInstallmentNo(loantypename);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}

	/**
	 * @param empId
	 * @return boolean
	 */
	public boolean chkloanempId(String empId) {
		boolean result = false;
		try {
			result = loanDAO.chkloanempId(empId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param refId
	 * @return boolean
	 */
	public boolean checkLoanRefId(String refId) {

		boolean result = false;
		try {
			result = loanDAO.checkLoanRefId(refId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

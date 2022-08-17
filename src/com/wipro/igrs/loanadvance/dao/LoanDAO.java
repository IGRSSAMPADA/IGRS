/**
 * LoanDAO.java
 */


package com.wipro.igrs.loanadvance.dao;


import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.loanadvance.dto.LoanDTO;
import com.wipro.igrs.loanadvance.sql.CommonSQL;


/**
 * @author jagadish
 * 
 */

public class LoanDAO {
	DBUtility	dbUtil	= null;

	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanMasterComponentsList() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String SQL = CommonSQL.RETRIEVE_LOAN_MASTER_DETAILS;
			list = dbUtil.executeQuery(SQL);
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {

				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return list;
	}

	/**
	 * @return String
	 */
	private String getLoanTypeId() {
		String loanTxnId = "";
		String loanTypeId = "";
		String loanTypeIdConst = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			loanTypeId = dbUtil.executeQry(CommonSQL.SELECT_LOAN_TYPE_ID_SEQ);
			loanTypeIdConst = "LOAN-";
			loanTxnId = loanTypeIdConst + loanTypeId;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return loanTxnId;
	}

	/**
	 * @param loanDTO
	 * @param userid
	 * @return boolean
	 * @throws Exception
	 */
	public boolean insertLoanMasterComponents(LoanDTO loanDTO, String userid) {
		boolean result = false;

		String sqlValues[] = new String[12];
		sqlValues[0] = getLoanTypeId();
		sqlValues[1] = loanDTO.getLoantype();
		sqlValues[2] = loanDTO.getLoandescription();
		sqlValues[3] = loanDTO.getLoanstatus();
		sqlValues[4] = loanDTO.getLoanmaxamount();
		sqlValues[5] = loanDTO.getLoanminamount();
		sqlValues[6] = loanDTO.getLoanmininstalment();
		sqlValues[7] = loanDTO.getLoanmaxinstalment();
		sqlValues[8] = loanDTO.getLoaninterestrate();
		sqlValues[9] = loanDTO.getLoaninteresttype();
		sqlValues[10] = userid;
		sqlValues[11] = userid;
		String sqlQuery = CommonSQL.INSERT_LOAN_TYPE_MASTER_DETAILS;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValues);
			if (result) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return result;
	}

	/**
	 * @param componentid
	 * @return ArrayList
	 */
	public ArrayList getLoanMasterComponentsListForUpdate(String componentid) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_LOAN_COMPONENTLIST;
		String sqlValues[] = new String[1];
		sqlValues[0] = componentid;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValues);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
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
			LoanDTO newLoanDTO = (LoanDTO) list.get(0);
			String sqlValues[] = new String[8];
			sqlValues[0] = loanDTO.getLoanminamount();
			sqlValues[1] = loanDTO.getLoanmaxamount();
			sqlValues[2] = loanDTO.getLoanmininstalment();
			sqlValues[3] = loanDTO.getLoanmaxinstalment();
			sqlValues[4] = loanDTO.getLoaninterestrate();
			sqlValues[5] = loanDTO.getLoandescription();
			sqlValues[6] = userId;
			sqlValues[7] = newLoanDTO.getLoanid();
			String sqlQuery = CommonSQL.UPDATE_LOAN_MASTER_TYPE;
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			update = dbUtil.executeUpdate(sqlValues);
			if (update) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return update;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanType() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlQuery = CommonSQL.GET_LOAN_TYPES;
			list = dbUtil.executeQuery(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param loanid
	 * @return ArrayList
	 */
	public ArrayList getMaxLoanAmount(String loanid) {
		ArrayList list = null;
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = loanid;
			dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.GET_LOAN_AMOUNT_FROM_MASTER;
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * @param loanDTO
	 * @param userId
	 * @return String
	 */
	public String insertAppliedLoan(LoanDTO loanDTO, String userId) {
		String txnId = "";
		boolean result = false;
		try {
			String sqlValue[] = new String[9];
			sqlValue[0] = getLoanTxnId();
			sqlValue[1] = userId;
			sqlValue[2] = loanDTO.getLoanid();
			sqlValue[3] = loanDTO.getUserinstallment();
			sqlValue[4] = loanDTO.getLoanamount();
			sqlValue[5] = loanDTO.getLoanstatus();
			sqlValue[6] = loanDTO.getLoandescription();
			sqlValue[7] = userId;
			sqlValue[8] = userId;
			String sqlQuery = CommonSQL.INSERT_LOAN_TRANSACTION_MASTER_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValue);
			if (result) {
				dbUtil.commit();
				txnId = sqlValue[0];

			} else {

				dbUtil.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return txnId;
	}

	/**
	 * @return String
	 */
	private String getLoanTxnId() {
		String txnId = "";
		String txnIdConst = "";
		String txnValue = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			txnId = dbUtil
					.executeQry(CommonSQL.SELECT_LOAN_TXN_MASTER_DETAILS_ID);
			txnIdConst = "LN-TXN-";
			txnValue = txnIdConst + txnId;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return txnValue;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList getLoanDetails(String empId) {
		ArrayList list = null;
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = empId;
			String sqlQuery = CommonSQL.GET_LOAN_TRANSACTION_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getLoanApprovalComponents(String componentId) {
		ArrayList loanDetailsList = null;
		try {
			String sqlValues[] = new String[1];
			sqlValues[0] = componentId;
			String sqlQuery = CommonSQL.GET_LOAN_DETAILS_FROM_LOAN_TXN_DETAILS_MASTER;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			loanDetailsList = dbUtil.executeQuery(sqlValues);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return loanDetailsList;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getLoanApprovalType(String componentId) {
		ArrayList loanTypeList = null;
		try {
			String sqlValues[] = new String[1];
			sqlValues[0] = componentId;
			String sqlQuery = CommonSQL.GET_LOAN_TYPE_FROM_LOAN_TYPE_MASTER;
			dbUtil.createPreparedStatement(sqlQuery);
			loanTypeList = dbUtil.executeQuery(sqlValues);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return loanTypeList;
	}

	/**
	 * @param list
	 * @param loanDTO
	 * @param userId
	 * @return boolean
	 */
	public boolean getLoanApproved(ArrayList list, LoanDTO loanDTO,
			String userId) {
		boolean flag = false;
		String sqlQuery = CommonSQL.UPDATE_LOAN_DETAILS_TRANSACTION_MASTER;
		try {
			LoanDTO loanamountDTO = (LoanDTO) list.get(0);
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[6];

			sqlValues[0] = loanDTO.getLoanstatus();
			sqlValues[1] = loanDTO.getComments();
			sqlValues[2] = userId;
			sqlValues[3] = userId;
			sqlValues[4] = loanamountDTO.getLoantxnid();
			sqlValues[5] = loanamountDTO.getEmpid();

			flag = dbUtil.executeUpdate(sqlValues);

			dbUtil
					.createCallableStatement("call IGRS_LOAN_TXN_PROC(?,?,?,?,?,?,?)");
			String status = dbUtil.insertIntoDetailsHistiory(list);

			if (flag == true && status.equals("0")) {
				dbUtil.commit();

			} else {
				dbUtil.rollback();

			}
		} catch (Exception e) {
			try {
				dbUtil.rollback();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * @param loantxnid
	 * @return ArrayList
	 */
	public ArrayList getLoanType(String loantxnid) {
		ArrayList list = null;
		String sqlValue[] = new String[1];
		sqlValue[0] = loantxnid;
		String sqlQuery = CommonSQL.GET_LOAN_TYPE;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * @param loantxnid
	 * @return ArrayList
	 */
	public ArrayList getLoanStatusDetails(String loantxnid) {
		ArrayList list = null;
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = loantxnid;
			dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.GET_LOAN_STATUS_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			list = dbUtil.executeQuery(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param loanTxnId
	 * @return ArrayList
	 */
	public ArrayList getLoanPaymentDetails(String loanTxnId) {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createCallableStatement("call IGRS_LOAN_REPAYDTL_PROC(?,?,?,?,?,?,?)");
			list = dbUtil.calculateLoanEMIForApprovedOne(loanTxnId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @param loanType
	 * @return boolean
	 */
	public boolean checkLoanName(String loanType) {
		boolean result = false;
		String sqlQuery = CommonSQL.CHECK_LOAN_NAME;

		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = {
				loanType };
			ArrayList list = dbUtil.executeQuery(sqlValues);
			if (list.size() > 0)
				result = true;
			else
				result = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * @param LoanTypeName
	 * @return String
	 */
	public String cheakLoanAmount(String LoanTypeName) {
		String amount = "";
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = LoanTypeName;
			String sqlQuery = CommonSQL.CHECK_LOAN_AMOUNT;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			amount = dbUtil.executeQry(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return amount;
	}

	/**
	 * @param LoanTypeName
	 * @return String
	 */
	public String cheakInstallmentNo(String LoanTypeName) {
		String amount = "";
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = LoanTypeName;
			String sqlQuery = CommonSQL.CHECK_INSTALLMENT_NO;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			amount = dbUtil.executeQry(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			String sqlValue[] = new String[1];
			sqlValue[0] = empId;
			dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.CHECK_LOANAPPLIED_EMPID;
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			String sqlValue[] = new String[1];
			sqlValue[0] = refId;
			dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.CHECK_LOAN_TXN_ID;
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}

/**
 * LoanadvanceDAO.java
 * 
 */


package com.wipro.igrs.loanadvance.dao;


import java.sql.CallableStatement;
import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.loanadvance.dto.LoanadvanceDTO;
import com.wipro.igrs.loanadvance.sql.CommonSQL;


/**
 * @author jagadish Jun 26, 2008
 * 
 */
public class LoanadvanceDAO {
	DBUtility			dbUtil	= null;

	CallableStatement	clstmt	= null;

	/**
	 * @return ArrayList
	 */
	public ArrayList retrieveAdvanceMasterDetails() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String SQL = CommonSQL.RETRIEVE_ADVANCE_MASTER_DETAILS;
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
	 * @param loanadvanceDTO
	 * @param userid
	 * @return boolean
	 */
	public boolean insertAdvanceMasterDetails(LoanadvanceDTO loanadvanceDTO,
			String userid) {

		boolean result = false;
		try {
			String sqlValues[] = new String[12];
			sqlValues[0] = getAdvanceTypeId();
			sqlValues[1] = loanadvanceDTO.getAdvancetypename();
			sqlValues[2] = loanadvanceDTO.getAdvancedescription();
			sqlValues[3] = loanadvanceDTO.getAdvancestatus();
			sqlValues[4] = loanadvanceDTO.getAdvanceamount();
			sqlValues[5] = loanadvanceDTO.getInstallmentno();
			sqlValues[6] = userid;
			sqlValues[7] = userid;
			
			
			// Getting the employee class as array
			String[] empClass=loanadvanceDTO.getApplicableClass();
			String strEmpclass="";
			if(empClass!=null)				
			{
			    for (int i=0 ; i<empClass.length ; i++)       
			 	{        
				String classItem = empClass[i];        
				strEmpclass=strEmpclass+classItem+"|";
				}   
			    strEmpclass =strEmpclass.substring(0, strEmpclass.length() - 1);
			}	
			sqlValues[8]=loanadvanceDTO.getInterestRate();
			sqlValues[9]=strEmpclass;
			sqlValues[10]=loanadvanceDTO.getSalaryLimitation();
			sqlValues[11]=loanadvanceDTO.getCondition();
			
			String sqlQuery = CommonSQL.INSERT_ADVANCE_MASTER_DETAILS;
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValues);
			if (result == true) {
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

	// =============Advance Master Sequence===============

	/**
	 * @return String
	 */
	private String getAdvanceTypeId() {
		String advanceTxnId = "";
		String advanceTypeId = "";
		String advanceTypeIdConst = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			advanceTypeId = dbUtil
					.executeQry(CommonSQL.SELECT_ADVANCE_TYPE_ID_SEQ);
			advanceTypeIdConst = "ADV-";
			advanceTxnId = advanceTypeIdConst + advanceTypeId;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return advanceTxnId;
	}

	/**
	 * @param sqlValues
	 * @return ArrayList
	 */
	public ArrayList getDetailsOfAdvanceType(String[] sqlValues) {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			String sqlquery = CommonSQL.RETRIEVE_ADVANCE_MASTER_COMPONENT;
			dbUtil.createPreparedStatement(sqlquery);
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
	 * @param loanadvanceDTO
	 * @param userid
	 * @return String
	 */
	public String insertAppliedAdvance(LoanadvanceDTO loanadvanceDTO,
			String userid) {

		boolean result = false;
		String strAdvanceTXNId = "";
		try {

			String sqlValues[] = new String[8];
			sqlValues[0] = getAdvanceTxnId();
			sqlValues[1] = userid;
			sqlValues[2] = loanadvanceDTO.getAdvancetypename();
			sqlValues[3] = loanadvanceDTO.getAdvancestatus();
			sqlValues[4] = loanadvanceDTO.getAdvancedescription();
			sqlValues[5] = userid;
			sqlValues[6] = userid;
			sqlValues[7] = loanadvanceDTO.getAdvanceamount();
			String sqlQuery = CommonSQL.INSERT_ADVANCE_TXN_MASTER_DETAILS;

			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlValues);

			if (result) {

				dbUtil.commit();
				strAdvanceTXNId = sqlValues[0];

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
		return strAdvanceTXNId;
	}

	// =============Advance Transaction Sequence===============

	/**
	 * @return String
	 */
	public String getAdvanceTxnId() {
		String advanceTxnId = null;
		String advanceTypeId = "";
		String advanceTypeIdConst = "";
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			advanceTypeId = dbUtil
					.executeQry(CommonSQL.SELECT_ADVANCE_TXN_ID_SEQ);
			advanceTypeIdConst = "ADV-TXN-";
			advanceTxnId = advanceTypeIdConst + advanceTypeId;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return advanceTxnId;
	}

	// ************************ Advance Type List********************

	/**
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTypeList() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlquery = CommonSQL.RETRIEVE_ADVANCE_TYPE_LIST;
			list = dbUtil.executeQuery(sqlquery);

		} catch (Exception e) {
			e.printStackTrace();

		}

		finally {
			try {

				dbUtil.closeConnection();

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return list;
	}

	/**
	 * @param empId
	 * @return ArrayList
	 */
	public ArrayList getPendingAdvanceList(String empId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_PENDING_ADVANCELIST;
		String sqlValues[] = new String[1];
		sqlValues[0] = empId;

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
	 * @param advancetypeid
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTxnDetails(String advancetypeid) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_ADVANCE_DETAILS_LIST_FOR_APPROVE;
		String sqlValues[] = new String[1];
		sqlValues[0] = advancetypeid;

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
	 * @param loanadvance
	 * @param userId
	 * @return boolean
	 */
	public boolean getAdvanceApproved(ArrayList list,
			LoanadvanceDTO loanadvance, String userId) {

		boolean flag = false;
		String sqlQuery = CommonSQL.UPDATE_AVAILABLE_DETAILS_TRANSACTION_MASTER;
		try {
			LoanadvanceDTO loanadvanceDTO = (LoanadvanceDTO) list.get(0);
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[5];

			sqlValues[0] = loanadvance.getAdvancestatus();
			sqlValues[1] = loanadvance.getAdvancedescription();
			sqlValues[2] = userId;
			sqlValues[3] = loanadvanceDTO.getAdvancetxnid();
			sqlValues[4] = loanadvanceDTO.getEmpid();

			flag = dbUtil.executeUpdate(sqlValues);

			dbUtil
					.createCallableStatement("call IGRS_ADVANCE_TXN_PROC(?,?,?,?,?,?)");
			String status = dbUtil.insertAdvanceEMI(loanadvanceDTO);

			if (flag == true && status.equals("0")) {
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
		return flag;
	}

	/**
	 * @param componentId
	 * @return ArrayList
	 */

	public ArrayList getAdvanceMasterComponentsList(String componentId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_ADVANCE_COMPONENTS_FOR_VIEW;
		String sqlValues[] = new String[1];
		sqlValues[0] = componentId;

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
	 * @param componentId
	 * @return ArrayList
	 */
	public ArrayList getAdvanceTypeList(String componentId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_ADVANCE_TYPE_PER_TXN_ID;
		String sqlValues[] = new String[1];
		sqlValues[0] = componentId;

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
	 * @param loanadvanceDTO
	 * @return ArrayList
	 */
	public ArrayList getAdvanceDetails(String advanceTxnId) {
		ArrayList list = null;

		String sqlValues[] = new String[1];
		sqlValues[0] = advanceTxnId;
		String sqlQuery = CommonSQL.SELECT_AVAILABLE_COMPONENTS_WITH_STATUS;

		try {

			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
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
	 * @param advanceTxnId
	 * @return ArrayList
	 */
	public ArrayList callingProcedure(String advanceTxnId) {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil
					.createCallableStatement("call IGRS_ADVANCE_REPAYDTL_PROC(?,?,?,?,?,?,?)");
			list = dbUtil.calculateAdvanceEMIForApprovedOne(advanceTxnId);

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
	 * @param advanceName
	 * @return boolean
	 */
	public boolean checkAdvanceName(String advanceName) {
		String sqlQuery = CommonSQL.CHECK_ADVANCE_NAME;
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = {
				advanceName };
			ArrayList list = dbUtil.executeQuery(sqlValues);
			if (list.size() > 0)
				flag = true;
			else
				flag = false;
		} catch (Exception e) {
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
	 * @param advanceDTO
	 * @param list
	 * @param userId
	 * @return boolean
	 */
	public boolean updateAdvanceMasterDetails(LoanadvanceDTO advanceDTO,
			ArrayList list, String userId) {
		boolean update = false;
		LoanadvanceDTO loanDTO = (LoanadvanceDTO) list.get(0);
		String sqlValue[] = new String[8];
		sqlValue[0] = advanceDTO.getAdvanceamount();
		sqlValue[1] = advanceDTO.getInstallmentno();
		sqlValue[2] = advanceDTO.getAdvancedescription();		
		sqlValue[3] = advanceDTO.getInterestRate();		
		String[] empClass=advanceDTO.getApplicableClass();
		String strEmpclass="";
		if(empClass!=null)				
		{
		    for (int i=0 ; i<empClass.length ; i++)       
		 	{        
			String classItem = empClass[i];        
			strEmpclass=strEmpclass+classItem+"|";
			}   
		    strEmpclass =strEmpclass.substring(0, strEmpclass.length() - 1);
		}		
		sqlValue[4] = strEmpclass;
		sqlValue[5] = advanceDTO.getSalaryLimitation();
		sqlValue[6] = advanceDTO.getCondition();
		sqlValue[7] = loanDTO.getAdvancetypeid();
		String sqlQuery = CommonSQL.UPDATE_ADVANCE_MASTER_TYPE;
		try {

			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			update = dbUtil.executeUpdate(sqlValue);

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
	 * @param advancetypename
	 * @return ArrayList
	 */
	public ArrayList fetchAdvanceAmount(String advancetypename) {
		ArrayList AmountList = null;
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = advancetypename;
			String sqlQuery = CommonSQL.FETCH_ADVANCE_AMOUNT_BY_ID;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			AmountList = dbUtil.executeQuery(sqlValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return AmountList;
	}

	/**
	 * @param advanceTypeName
	 * @return String
	 */
	public String cheakAdvanceAmount(String advanceTypeName) {
		String amount = "";
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = advanceTypeName;
			String sqlQuery = CommonSQL.FETCH_ADVANCE_AMOUNT_BY_ID;
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
	 * @param empid
	 * @return boolean
	 */
	public boolean checkEmpId(String empid) {
		boolean result = false;
		try {
			String sqlvalue[] = new String[1];
			sqlvalue[0] = empid;
			String sqlQuery = CommonSQL.CHECK_AVAILABLE_EMPID;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String result1 = dbUtil.executeQry(sqlvalue);
			if(result1!=null && result1.length()>0){
				result=true;
			}else{
				result=false;
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
	 * @param refId
	 * @return boolean
	 */
	public boolean checkRefId(String refId) {
		boolean result = false;
		try {
			String sqlvalue[] = new String[1];
			sqlvalue[0] = refId;
			String sqlQuery = CommonSQL.CHECK_AVAILABLE_REFID;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			result = dbUtil.executeUpdate(sqlvalue);
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

package com.wipro.igrs.hrpayroll.hrp.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.hrpayroll.hrp.dto.CadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeCadreDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.GradeDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrp.dto.SalaryDTO;
import com.wipro.igrs.hrpayroll.hrp.sql.CommonSQL;

/**
 * @author oneapps
 *
 */
/**
 * @author oneapps
 *
 */
/**
 * @author oneapps
 *
 */
/**
 * @author oneapps
 *
 */
/**
 * @author oneapps
 * 
 */
public class HRPayrollDAO {
	DBUtility dbUtil = null;
	private Logger logger = (Logger) Logger.getLogger(HRPayrollDAO.class);

	/**
	 * @throws Exception
	 */
	public HRPayrollDAO() throws Exception {
		// dbUtil = new DBUtility();
	}

	/*
	 * CADRES
	 */
	/**
	 * @return
	 */
	public ArrayList displayCadres() {

		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_ALL_CADRES;
			list = dbUtil.executeQuery(sql);
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
	 * @return
	 */
	public int getCadrePostsCount() {
		int count = 0;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_COUNT_CADRES;
			String strCount = dbUtil.executeQry(sql);
			count = strCount == "" ? 0 : Integer.parseInt(strCount);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * @return
	 */
	public String getNewCadreId() {
		String str = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_CADRE_SEQ_ID;
			str = dbUtil.executeQry(sql);
			str = "CADRE" + str;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @param cadreDTO
	 * @param userId
	 * @return
	 */
	public boolean insertNewCadre(CadreDTO cadreDTO, String userId) {
		String sqlValues[] = { cadreDTO.getCadreId(),
				cadreDTO.getCadreName().trim().toUpperCase(),
				cadreDTO.getCadreStatus(), cadreDTO.getCadrePosts(), userId, // createdBy
				userId // updatedBy
		};
		String sqlQuery = CommonSQL.INSERT_CADRE;
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);

			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == true) {
				dbUtil.commit();
				// logger.info("Record Inserted");
			} else {
				dbUtil.rollback();
				// logger.info("Sorry !! Record Not Inserted");
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
	 * @param cadreDTO
	 * @param userId
	 * @param cadreIdsArray
	 * @param indexArray
	 * @return
	 */
	public boolean editCadres(CadreDTO cadreDTO, String userId,
			String cadreIdsArray[], int indexArray[]) {
		logger.info("In EDIT-editCadres()");
		String cadreNames[] = cadreDTO.getEditCadreNames();
		String cadrePosts[] = cadreDTO.getEditCadresPosts();

		boolean flag = false;
		String sqlQuery = CommonSQL.EDIT_CADRES;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[3];

			for (int i = 0; i < indexArray.length; i++) {
				// sqlValues[0] = cadreNames[indexArray[i]]; //Cadre Name
				sqlValues[0] = cadrePosts[indexArray[i]]; // No. of Posts
				sqlValues[1] = userId; // "IGRS-ADMIN";
				// //sess.getAttribute("current_user");
				sqlValues[2] = cadreIdsArray[i]; // Cadre Id

				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false)
					break;
			}
			if (flag == true) {
				dbUtil.commit();
				// logger.info("Record Inserted");
			} else {
				dbUtil.rollback();
				// logger.info("Sorry !! Record Not Inserted");
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
	 * @param cadreName
	 * @return
	 */
	public boolean checkCadreNameExists(String cadreName) {
		String sqlQuery = CommonSQL.CHECK_CADRE_NAME;
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = { cadreName };
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

	// ////////////////////////GRADES////////////////////////////////////////////////
	/**
	 * @return
	 */
	public ArrayList displayGrades() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_ALL_GRADES;
			list = dbUtil.executeQuery(sql);
			logger.info("In displayGrades List:" + list);
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
	 * @return
	 */
	public String getNewGradeId() {
		String str = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_GRADE_SEQ_ID;
			str = dbUtil.executeQry(sql);
			str = "GRADE" + str;
			logger.info("Generated Sequence Id:" + str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @param gradeName
	 * @return
	 */
	public boolean checkGradeNameExists(String gradeName) {
		boolean flag = false;
		String sqlQuery = CommonSQL.CHECK_GRADE_NAME;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = { gradeName };
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
	 * @param gradeDTO
	 * @param userId
	 * @return
	 */
	public boolean insertNewGrade(GradeDTO gradeDTO, String userId) {
		boolean flag = false;
		String sqlValues[] = { gradeDTO.getGradeId(),
				gradeDTO.getGradeName().trim().toUpperCase(),
				gradeDTO.getMinSalSlab(), gradeDTO.getMaxSalSlab(),
				gradeDTO.getIncreeAmount(), gradeDTO.getGradeStatus(), userId, // createdBy
				userId // updatedBy
		};
		String sqlQuery = CommonSQL.INSERT_GRADE;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == true) {
				dbUtil.commit();
				// logger.info("Record Inserted");
			} else {
				dbUtil.rollback();
				// logger.info("Sorry !! Record Not Inserted");
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
	 * @param gradeDTO
	 * @param userId
	 * @param gradeIdsArray
	 * @param indexArray
	 * @return
	 */
	public boolean editGrades(GradeDTO gradeDTO, String userId,
			String[] gradeIdsArray, int[] indexArray) {
		String gradeNames[] = gradeDTO.getEditGradeNames();
		String gradeMinSalSlabs[] = gradeDTO.getEditGradeMinSalSlabs();
		String gradeMaxSalSlabs[] = gradeDTO.getEditGradeMaxSalSlabs();
		String gradeIncreeAmounts[] = gradeDTO.getEditGradeIncreeAmounts();

		String sqlValues[] = new String[5];
		boolean flag = false;

		String sqlQuery = CommonSQL.EDIT_GRADES;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < indexArray.length; i++) {
				logger.info("In Update-Operation for Grade-Id :"
						+ gradeIdsArray[i]);
				// sqlValues[0] = gradeNames[indexArray[i]];
				sqlValues[0] = gradeMinSalSlabs[indexArray[i]];
				sqlValues[1] = gradeMaxSalSlabs[indexArray[i]];
				sqlValues[2] = gradeIncreeAmounts[indexArray[i]];
				sqlValues[3] = userId; // "IGRS-ADMIN";
				// //sess.getAttribute("current_user");
				sqlValues[4] = gradeIdsArray[i];
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false)
					break;
			}
			if (flag == true) {
				dbUtil.commit();
				// logger.info("Record Inserted");
			} else {
				dbUtil.rollback();
				// logger.info("Sorry !! Record Not Inserted");
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

	// /////////////////////////////////////////LEAVE/////////////////////////////////////////////////
	/**
	 * @return
	 */
	public ArrayList getLeave() {
		ArrayList list = null;
		logger.info("Inside getLeave");
		String sql = CommonSQL.RETRIVE_ALL_LEAVE;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
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
	 * @param leavedto
	 * @param userId
	 * @return
	 */
	public boolean insertNewLeave(LeaveDTO leavedto, String userId) {
		String sqlvalues[] = { leavedto.getLeave_type_id(),
				leavedto.getLeave_type_name().trim().toUpperCase(),
				leavedto.getGender(), leavedto.getMaximum_no_days(),
				leavedto.getLeave_type_desc(), leavedto.getLeaveStatus(),
				userId, userId };
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			String sql = CommonSQL.INSERT_INTO_LEAVE;
			// logger.info("Inside leaveDAO");
			dbUtil.createPreparedStatement(sql);
			// logger.info("Inside Sql"+sql);
			flag = dbUtil.executeUpdate(sqlvalues);

			if (flag == true) {
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
	 * @param leaveDTO
	 * @param userId
	 * @param leaveIdsArray
	 * @param indexArray
	 * @return
	 */
	public boolean editLeaveMaster(LeaveDTO leaveDTO, String userId,
			String[] leaveIdsArray, int[] indexArray) {
		// String leaveTypes[] = leaveDTO.getEditLeaveTypes();
		String leaveGenders[] = leaveDTO.getEditLeaveGender();
		String leaveDays[] = leaveDTO.getEditLeaveDays();
		String leaveDescs[] = leaveDTO.getEditLeaveDesc();

		String sqlValues[] = new String[5];
		boolean flag = false;

		String sqlQuery = CommonSQL.EDIT_LEAVE_MASTER;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < indexArray.length; i++) {
				logger.info("In Update-Operation for Leave-Id :"
						+ leaveIdsArray[i]);
				// sqlValues[0] = leaveTypes[indexArray[i]];
				sqlValues[0] = leaveGenders[indexArray[i]];
				sqlValues[1] = leaveDays[indexArray[i]];
				sqlValues[2] = leaveDescs[indexArray[i]];
				sqlValues[3] = userId; // "IGRS-ADMIN";
				// //sess.getAttribute("current_user");
				sqlValues[4] = leaveIdsArray[i];
				flag = dbUtil.executeUpdate(sqlValues);
				if (flag == false)
					break;
			}
			if (flag == true) {
				dbUtil.commit();
				// logger.info("Record Inserted");
			} else {
				dbUtil.rollback();
				// logger.info("Sorry !! Record Not Inserted");
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
	 * @return
	 */
	public String getNewLeaveId() {
		String str = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_LEAVE_SEQ_ID;
			str = dbUtil.executeQry(sql);
			str = "LEAVE" + str;
			logger.info("Generated Sequence Id:" + str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @param leaveTypeName
	 * @return
	 */
	public boolean checkLeaveTypeExists(String leaveTypeName) {
		String sqlQuery = CommonSQL.CHECK_LEAVE_TYPE_NAME;
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = { leaveTypeName };
			ArrayList list = dbUtil.executeQuery(sqlValues);
			logger.info("List.size: " + list.size());
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
	 * @param gradeId
	 * @return
	 */
	public String getGradeName(String gradeId) {
		String gradeName = null;
		try {
			String sqlQuery = CommonSQL.RETRIVE_GRADE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[1];
			sqlValues[0] = gradeId;
			gradeName = dbUtil.executeQry(sqlValues);
			logger.info("Grade Name :" + gradeName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeName;
	}

	public String getCadreName(String cadreId) {
		String cadreName = null;
		try {
			String sqlQuery = CommonSQL.RETRIVE_CADRE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[1];
			sqlValues[0] = cadreId;
			cadreName = dbUtil.executeQry(sqlValues);
			logger.info("Grade Name :" + cadreName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cadreName;
	}

	/**
	 * @return
	 */
	public ArrayList fetchGrades() {
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETRIVE_GRADES_FOR_SALARY_MASTER;
			list = dbUtil.executeQuery(sql);
			logger.info("In displayGrades List:" + list);
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
	 * @param gradeId
	 * @return
	 */
	public ArrayList fetchCadresForGrade(String gradeId) {
		ArrayList list = null;
		try {
			String sqlQuery = CommonSQL.RETRIVE_CADRES_FOR_SALARY_MASTER;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[1];
			sqlValues[0] = gradeId;
			list = dbUtil.executeQuery(sqlValues);
			logger.info("fecthCadresforGrade:" + list);
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
	 * @param gradeId
	 * @return
	 */
	public ArrayList displayGradeDetails(String gradeId) {
		ArrayList list = null;
		try {
			String sqlQuery = CommonSQL.RETRIVE_ONLY_GRADE;
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[1];
			sqlValues[0] = gradeId;
			list = dbUtil.executeQuery(sqlValues);
			logger.info("In displayGrades List:" + list);
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

	// ============== PAYMENTS =========================
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public ArrayList fetchPaymentDetails(String gradeId, String cadreId) {
		ArrayList list = null;
		try {
			String sqlQuery = CommonSQL.RETRIVE_PAYMENT_COMPONENTS;
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			//list = dbUtil.executeQuery(sqlValues);
			logger.info("Before fecthCadresforGrade:");
			list = dbUtil.executeQuery(sqlQuery);
			logger.info("fecthCadresforGrade:" + list);
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

	public String getPaymentsTotal(String gradeId, String cadreId) {
		String sqlQuery = CommonSQL.RETRIVE_PAYMENT_TOTAL;
		String grossAmount = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			grossAmount = dbUtil.executeQry(sqlValues);
			logger.info("getGrossSalary:" + grossAmount);
			if (grossAmount.length() == 0)
				grossAmount = 0 + "";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grossAmount;
	}

	/**
	 * @param salDTO
	 * @return
	 */
	public boolean updatePaymentComponents(SalaryDTO salDTO) {
		String sqlQuery = CommonSQL.UPDATE_PAYMENT_COMPONENTS;
		String sqlValues[] = new String[3];
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < salDTO.getComponentIdArr().length; i++) {
				sqlValues[0] = salDTO.getComponentValueArr()[i];
				sqlValues[1] = salDTO.getGradeId();
				sqlValues[2] = salDTO.getCadreId();
				// sqlValues[3] = salDTO.getComponentType();
				//sqlValues[3] = salDTO.getComponentIdArr()[i];
				flag = dbUtil.executeUpdate(sqlValues);
				if (!flag)
					break;
			}
			if (flag) {
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

	// -- TREASURY
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public ArrayList fetchTreasuryDetails(String gradeId, String cadreId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.RETRIVE_TREASURY_COMPONENTS;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			//list = dbUtil.executeQuery(sqlValues);
			list = dbUtil.executeQuery(sqlQuery);
			logger.info("fetchPaymentDetails:" + list);
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public String getTreasuryTotal(String gradeId, String cadreId) {
		String sqlQuery = CommonSQL.RETRIVE_TREASURY_TOTAL;
		String grossAmount = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			grossAmount = dbUtil.executeQry(sqlValues);
			logger.info("getTreasuryTotal:" + grossAmount);
			if (grossAmount.length() == 0)
				grossAmount = 0 + "";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grossAmount;
	}

	/**
	 * @param salDTO
	 * @return
	 */
	public boolean updateTreasuryComponents(SalaryDTO salDTO) {
		String sqlQuery = CommonSQL.UPDATE_TREASURY_COMPONENTS;
		String sqlValues[] = new String[3];
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < salDTO.getComponentIdArr().length; i++) {
				sqlValues[0] = salDTO.getComponentValueArr()[i];
				sqlValues[1] = salDTO.getGradeId();
				sqlValues[2] = salDTO.getCadreId();
			//	sqlValues[3] = salDTO.getComponentIdArr()[i];
				flag = dbUtil.executeUpdate(sqlValues);
				if (!flag)
					break;
			}
			if (flag) {
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

	// -- DEDUCTIONS
	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public ArrayList fetchDeductionDetails(String gradeId, String cadreId) {

		String sqlQuery = CommonSQL.RETRIVE_AGDEDUCTIONS_COMPONENTS;
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			//list = dbUtil.executeQuery(sqlValues);
			list = dbUtil.executeQuery(sqlQuery);
			logger.info("fetchDeductionDetails:" + list);
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public String getDeductionTotal(String gradeId, String cadreId) {

		String sqlQuery = CommonSQL.RETRIVE_AGDEDUCTION_TOTAL;
		String grossAmount = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			String sqlValues[] = new String[2];
			sqlValues[0] = gradeId;
			sqlValues[1] = cadreId;
			grossAmount = dbUtil.executeQry(sqlValues);
			logger.info("getDeductionTotal:" + grossAmount);
			if (grossAmount.length() == 0)
				grossAmount = 0 + "";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grossAmount;
	}

	/**
	 * @param salDTO
	 * @return
	 */
	public boolean updateDeductionComponents(SalaryDTO salDTO) {
		String sqlQuery = CommonSQL.UPDATE_DEDUCTION_COMPONENTS;
		String sqlValues[] = new String[3];
		boolean flag = false;
		try {
			dbUtil = new DBUtility();
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < salDTO.getComponentIdArr().length; i++) {
				sqlValues[0] = salDTO.getComponentValueArr()[i];
				sqlValues[1] = salDTO.getGradeId();
				sqlValues[2] = salDTO.getCadreId();
			//	sqlValues[3] = salDTO.getComponentIdArr()[i];
				flag = dbUtil.executeUpdate(sqlValues);
				if (!flag)
					break;
			}
			if (flag) {
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

	/*
	 * public String getSumOfComponents(SalaryDTO salDTO) throws Exception {
	 * return ""; //getSumOfComponents()///...pending }
	 */

	// --SALARY COMPONENT MAPPING WITH CADRE & GRADE
	/**
	 * @param gradeId
	 * @param cadreId
	 * @param typeId
	 * @return
	 */
	public ArrayList getSelectedSalaryComponents(String gradeId,
		String cadreId, String typeId) {
	ArrayList list = null;
	String sqlQuery = CommonSQL.SELECTED_SALARY_COMPONENTS;
	String sqlValues[] = new String[1];
	//sqlValues[0] = gradeId;
	//sqlValues[1] = cadreId;
	sqlValues[0] = typeId;
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public ArrayList getAvailableSalaryComponents(String gradeId, String cadreId) // ,String
	// typeId
	{
		String sqlQuery = CommonSQL.AVAILABLE_SALARY_COMPONENTS;
		String sqlValues[] = new String[2]; // 3
		sqlValues[0] = gradeId;
		sqlValues[1] = cadreId;
		// sqlValues[2] = typeId;
		ArrayList list = null;
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
	 * @param gradeId
	 * @param cadreId
	 * @param typeId
	 * @return
	 */
        	public ArrayList getOldSalaryComponents(String gradeId, String cadreId,
        		String typeId) {
        	ArrayList list = null;
        	String sqlQuery = CommonSQL.SELECTED_OLD_SALARY_COMPONENTS;
        	String sqlValues[] = new String[1];
        	//sqlValues[0] = gradeId;
        	//sqlValues[1] = cadreId;
        	sqlValues[0] = typeId;
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
	public ArrayList getOldSalaryComponentsWithStatus(String gradeId,
		String cadreId,String typeId) {
	ArrayList list = null;
	String sqlQuery = CommonSQL.SELECTED_OLD_SALARY_COMPONENTS_WITH_STATUS;
	String sqlValues[] = new String[1];
	//sqlValues[0] = gradeId;
	//sqlValues[1] = cadreId;
	sqlValues[0] = typeId;

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
	 * @param salaryDTO
	 * @return
	 */
	public boolean saveGradeCadreSalaryComponentMapping(SalaryDTO salaryDTO,String empId) 
	{

		boolean inserted = false;

		try {
			ArrayList oldSelectedSalaryComponents = this
			.getOldSalaryComponents(salaryDTO.getGradeId(), salaryDTO
					.getCadreId(), salaryDTO.getTypeId());
	ArrayList newSelectedSalaryComponents = new ArrayList();
	ArrayList newsalarylist=new ArrayList();
	ArrayList oldsalarylist=new ArrayList();
	ArrayList deactivelist=new ArrayList();
	ArrayList insertsalartlist=new ArrayList();
	ArrayList updatelist=new ArrayList();
	for (int i = 0; i < salaryDTO.getComponentIdArr().length; i++) {
		//System.out.println("salaryDTO.getComponentIdArr()[i] "+ i +salaryDTO.getComponentIdArr()[i]);
		newSelectedSalaryComponents.add(salaryDTO.getComponentIdArr()[i]);
		
	}
	if(oldSelectedSalaryComponents!=null&& newSelectedSalaryComponents.size()>0){
		for(int i=0;i<oldSelectedSalaryComponents.size();i++){
			ArrayList oldlist=(ArrayList)oldSelectedSalaryComponents.get(i);
			String oldvalue=(String)oldlist.get(0);
			//System.out.println("Old ValaueFrom Data Base"+oldvalue);
			if(newSelectedSalaryComponents.contains(oldvalue)){
				oldsalarylist.add(oldvalue);
			}else{
				deactivelist.add(oldvalue);
			}
		}
		
	}
	
	for(int i=0;i<newSelectedSalaryComponents.size();i++){
		String value=(String)newSelectedSalaryComponents.get(i);
		if(!oldsalarylist.contains(value)){
			newsalarylist.add(value);
		}
	}
	
	ArrayList oldSalaryComponentsWithStatusDeactive = this
	.getOldSalaryComponentsWithStatus(salaryDTO
			.getGradeId(), salaryDTO.getCadreId(),salaryDTO.getTypeId());
	
	for(int i=0;i<oldSalaryComponentsWithStatusDeactive.size();i++){
		ArrayList olddeactivelist=(ArrayList)oldSalaryComponentsWithStatusDeactive.get(i);
		String olddeactivevalue=(String)olddeactivelist.get(0);
		if(newsalarylist.contains(olddeactivevalue)){
			updatelist.add(olddeactivevalue);
		}
		
	}
	
	for(int i=0;i<newsalarylist.size();i++){
		String newsalary=(String)newsalarylist.get(i);
		if(!updatelist.contains(newsalary)){
			insertsalartlist.add(newsalary);
		}
	}
	
	/*for(int i=0;i<oldsalarylist.size();i++){
		System.out.println("Old Salary List from the Selected Components"+oldsalarylist.get(i));
	}
	for(int i=0;i<newsalarylist.size();i++){
		System.out.println("New Salary List from the Selected Components"+newsalarylist.get(i));
	}
	
	for(int i=0;i<deactivelist.size();i++){
		
		System.out.println("deactivelist List from the Selected Components"+deactivelist.get(i));
	}*/
	/*Update The Status to Active If the Deactive of the Same Present 
	 * 
	 */	
	
	dbUtil = new DBUtility();
	dbUtil.setAutoCommit(false);
	boolean updateDeactivateComponent=true;
	//String sql = CommonSQL.UPDATE_DEACTIVATE_SALARY_COMPONENTS;
	for(int i=0;i<updatelist.size();i++){
	//	System.out.println("Update DeActive Salary Components"+CommonSQL.UPDATE_DEACTIVATE_SALARY_COMPONENTS);
		String sqlValue[] = new String[4];
		sqlValue[0] = salaryDTO.getTypeId();
		sqlValue[1] = salaryDTO.getGradeId();
		sqlValue[2] = salaryDTO.getCadreId();
		// sqlValue[2] = salaryDTO.getTypeId();
		
		sqlValue[3] =(String) updatelist.get(i);
		/*System.out.println("Grade ID"+sqlValue[0]);
		System.out.println("Cadre ID"+sqlValue[1]);
		System.out.println("SalaryType ID"+sqlValue[2]);
		System.out.println("Salary Components"+sqlValue[3]);*/
		//dbUtil.createPreparedStatement(sql);
	//	updateDeactivateComponent = dbUtil
	//			.executeUpdate(sqlValue);
	}
	/*
	 * Change the Status Active to Deactive
	 * 
	 */
	
	//String deactivesql=CommonSQL.UPDATE_SALARY_COMPONENTS;
	for(int i=0;i<deactivelist.size();i++){
	//	System.out.println("Update Salary Components"+CommonSQL.UPDATE_SALARY_COMPONENTS);
		String sqlValue[] = new String[4];
		sqlValue[0] =salaryDTO.getGradeId();
		
		sqlValue[1] = salaryDTO.getCadreId();
		sqlValue[2] = salaryDTO.getTypeId();
		sqlValue[3] =(String) deactivelist.get(i);
		/*System.out.println("Grade ID"+sqlValue[0]);
		System.out.println("Cadre ID"+sqlValue[1]);
		System.out.println("SalaryType ID"+sqlValue[2]);
		System.out.println("Salary Components"+sqlValue[3]);*/
	//	dbUtil.createPreparedStatement(deactivesql);
	//	updateDeactivateComponent = dbUtil
	//			.executeUpdate(sqlValue);
	}

/*
 * Inserting New Values to the DB
 */	
	
	String INSERTQUERY = CommonSQL.INSERT_SELECTED_SALARY_COMPONENT;
	//System.out.println("After INSERTQUERY"+CommonSQL.INSERT_SELECTED_SALARY_COMPONENT);
	for(int i=0;i<insertsalartlist.size();i++){
	   
		
		String sqlValue[] = new String[4];
		sqlValue[0] =salaryDTO.getGradeId();
		System.out.println("salaryDTO.getGradeId() 0;==="+salaryDTO.getGradeId());
		
		sqlValue[1] = salaryDTO.getCadreId();
		System.out.println("salaryDTO.getCadreId()  1;=="+salaryDTO.getCadreId());
		
		//sqlValue[2] = salaryDTO.getTypeId();
		//System.out.println("salaryDTO.getTypeId();=="+salaryDTO.getTypeId());
		
		sqlValue[2] =(String) insertsalartlist.get(i);
		System.out.println("(String) insertsalartlist.get(i) 2;"+(String) insertsalartlist.get(i));
		
		sqlValue[3] = empId;
		System.out.println("empId is--------"+empId);
		
		
		//sqlValue[3] = salaryDTO.getComponentValueArr()[j];
		
		
		//System.out.println("salaryDTO.getComponentValueArr()[i]== 3"+salaryDTO.getComponentValueArr()[j]);
		
		//sqlValue[4]=getSalaryTxdID(dbUtil);
		//System.out.println("getSalaryTxdID(dbUtil);=="+getSalaryTxdID(dbUtil));
		
		
		dbUtil.createPreparedStatement(INSERTQUERY);
		updateDeactivateComponent = dbUtil.executeUpdate(sqlValue);
		System.out.println("updateDeactivateComponent falg-------"+updateDeactivateComponent);
	}
	
		
	if(updateDeactivateComponent){
		inserted=true;
	}
	} catch (Exception e) {try{
		dbUtil.rollback();
	}catch(Exception ex){
		ex.printStackTrace();
	} }finally {
		try {
			dbUtil.closeConnection();
		} catch (Exception exx) {
			exx.printStackTrace();
		}
	}
	return inserted;
	}

	private String getSalaryTxdID(DBUtility dbUtil) {
		String str = null;
		try {
			//dbUtil = new DBUtility();
			dbUtil.createStatement();
			System.out.println("After Create StateMent");
			String sql = CommonSQL.RETREIVE_SALARY_SEQ_ID;
			System.out.println("SQLSEQ"+sql);
			str = dbUtil.executeQry(sql);
			System.out.println("Sequence Before"+str);
			str = "S_T_" + str;
			System.out.println("Sequence"+str);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return str;
	}

	/**
	 * @return
	 */
	public String getNewSalaryTxnId() {
		String str = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			String sql = CommonSQL.RETREIVE_SALARY_SEQ_ID;
			str = dbUtil.executeQry(sql);
			str = "S_T_" + str;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
    	public ArrayList fetchPaymentComponentsForDisplay(String gradeId,
    		String cadreId,String typeId) {
    	ArrayList list = null;
    	String sqlQuery = CommonSQL.RETREIVE_PAYMENT_COMPONENTS_FOR_DISPLAY;
    
    	String sqlValues[] = new String[1];
    	//sqlValues[0] = gradeId;
    	//sqlValues[1] = cadreId;
    	sqlValues[0] = typeId;
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
    	public ArrayList fetchAgDeductionComponentsForDisplay(String gradeId,
		String cadreId,String typeId) {
	ArrayList list = null;
	String sqlQuery = CommonSQL.RETREIVE_AGDEDUCTION_COMPONENTS_FOR_DISPLAY;
	String sqlValues[] = new String[1];
	sqlValues[0] = typeId;
	//sqlValues[1] = cadreId;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sqlQuery);
		logger.debug("For checking ... ... ...typeId "+typeId);
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
	 * @param gradeId
	 * @param cadreId
	 * @return
	 */
    	public ArrayList fetchTreasuryComponentsForDisplay(String gradeId,
		String cadreId,String typeId) {
	ArrayList list = null;
	String sqlQuery = CommonSQL.RETREIVE_TREASURY_COMPONENTS_FOR_DISPLAY;
	String sqlValues[] = new String[1];
	sqlValues[0] = typeId;
	//sqlValues[1] = cadreId;
	try {
		dbUtil = new DBUtility();
		dbUtil.createPreparedStatement(sqlQuery);
		list = dbUtil.executeQuery(sqlValues);
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

	// =================EMPLOYEE GRADE CADRE SALARY
	// MAPPING==============================================
	/**
	 * @return
	 */
	public ArrayList fetchEmployees() {
		ArrayList list = null;
		String sql = CommonSQL.RETRIVE_EMPLOYEES_FOR_SALARY_MASTER;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
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
	 * @param empId
	 * @return
	 */
	public ArrayList fetchCadresGradeForEmp(String empId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.RETRIVE_GRADE_CADRE_FOR_SALARY_MASTER;
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
	 * @param gradeId
	 * @param cadreId
	 * @param typeId
	 * @return
	 */
	public ArrayList fetchSalaryComponents(String gradeId, String cadreId,
			String typeId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.SELECTED_SALARY_COMPONENTS;
		String sqlValues[] = new String[3];
		sqlValues[0] = gradeId;
		sqlValues[1] = cadreId;
		sqlValues[2] = typeId;
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
	 * @param salDTO
	 * @return
	 */
	public boolean insertSalaryComponentAmount(SalaryDTO salDTO) {
		boolean saved = false;
		String empId = salDTO.getEmpId();
		String GradeId = salDTO.getGradeId();
		String CadreId = salDTO.getCadreId();
		String componentStatus = salDTO.getComponentStatus();
		String componentIDs[] = salDTO.getComponentIdArr();
		String componentValues[] = salDTO.getComponentValueArr();

		if (componentIDs == null)
			return false;
		// Insert fresh records to that mapping table
		String sqlQuery = CommonSQL.INSERT_SALARY_AMOUNT;
		try {
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < componentIDs.length; i++) {
				String sqlValues[] = new String[6];
				sqlValues[0] = empId;
				sqlValues[1] = GradeId;
				sqlValues[2] = CadreId;
				sqlValues[3] = componentIDs[i];
				sqlValues[4] = componentValues[i];
				sqlValues[5] = componentStatus;
				saved = dbUtil.executeUpdate(sqlValues);
				if (!saved)
					break;
				sqlValues = null;
			}
			if (saved)
				dbUtil.commit();
			else
				dbUtil.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return saved;

	}

	// ==================GradeCadreMapping===================================

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getGradeList() throws Exception {
		String sql = CommonSQL.RETRIVE_GRADE_FOR_GRADE_CADRE_MAPPING;
		ArrayList list = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
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
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllCadres() throws Exception {
		ArrayList list = null;
		String sql = CommonSQL.RETREIVE_ALL_CADRES;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery(sql);
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
	 * @param gradeId
	 * @return
	 */
	public ArrayList getSelectedCadres(String gradeId) {
		ArrayList list = null;
		String sqlQuery = CommonSQL.SELECTED_CADRES_LIST;
		String sqlValues[] = new String[1];
		sqlValues[0] = gradeId;
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
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAvailableCadres(String gradeId) throws Exception {
		ArrayList list = null;
		String sqlQuery = CommonSQL.AVAILABLE_CADRES_LIST;
		String sqlValues[] = new String[1];
		sqlValues[0] = gradeId;

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
	 * @param gcDTO
	 * @return
	 * @throws Exception
	 */
	public boolean saveGradeCadreMapping(GradeCadreDTO gcDTO) throws Exception {
		boolean saved = false;
		// boolean delFlag = false;
		String gradeId = gcDTO.getGradeId();
		String cadreIDs[] = gcDTO.getCadreIds();

		// deletion of all cadres for that gradeId Selected from the table
		String sqlDeleteQuery = CommonSQL.DELETE_GRADE_CADRES_MAP;
		String sqlDeleteValues[] = new String[1];
		sqlDeleteValues[0] = gradeId;
		dbUtil = new DBUtility();
		dbUtil.setAutoCommit(false);
		dbUtil.createPreparedStatement(sqlDeleteQuery);
		boolean delFlag = dbUtil.executeUpdate(sqlDeleteValues);

		if (cadreIDs == null) {
			dbUtil.commit();
			return true;
		}

		try {
			// Insert fresh records to that mapping table
			String sqlQuery = CommonSQL.SAVE_GRADE_CADRE_MAP;
			dbUtil.createPreparedStatement(sqlQuery);
			for (int i = 0; i < cadreIDs.length; i++) {
				String sqlValues[] = new String[2];
				sqlValues[0] = gradeId;
				sqlValues[1] = cadreIDs[i];
				saved = dbUtil.executeUpdate(sqlValues);
				if (!saved)
					break;
			}
			if (saved)
				dbUtil.commit();
			else
				dbUtil.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return saved || delFlag;

	}
}
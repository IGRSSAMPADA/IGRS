/**
 * DepartmentalExamsDAO.java
 */

package com.wipro.igrs.empmgmt.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsDTO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsResultDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;

/**
 * @author jagadish Jun 4, 2008
 * 
 */
public class DepartmentalExamsDAO {

	private DBUtility dbUtil = null;
	private Logger logger = (Logger) Logger
			.getLogger(DepartmentalExamsDAO.class);

	/**
	 * @param deptExamDTO
	 * @param userId
	 * @return boolean
	 */
	public boolean insertDepartmentalExamDetails(
			DepartmentalExamsDTO deptExamDTO, ArrayList examList, String userId) {
		boolean insert = false;
		boolean insertdeptresult = false;
		try {
			dbUtil = new DBUtility();
			String sqlValue[] = new String[10];
			sqlValue[0] = getDepartmentalId();
			sqlValue[1] = deptExamDTO.getDeptexamname();
			sqlValue[2] = CommonUtil.getConvertedDate(deptExamDTO
					.getDateofexam());
			sqlValue[3] = deptExamDTO.getOrgauthority();
			sqlValue[4] = deptExamDTO.getOrgbody();
			sqlValue[5] = deptExamDTO.getPlaceofexam();
			sqlValue[6] = CommonUtil.getConvertedDate(deptExamDTO
					.getDateofresult());
			sqlValue[7] = deptExamDTO.getExamdetails();
			sqlValue[8] = userId;
			sqlValue[9] = userId;
			String sqlQuery = CommonSQL.INSERT_DEPT_EXAM_DETAILS;
			dbUtil.createPreparedStatement(sqlQuery);
			insert = dbUtil.executeUpdate(sqlValue);
			for (int i = 0; i < examList.size(); i++) {
				DepartmentalExamsResultDTO resultDTO = (DepartmentalExamsResultDTO) examList
						.get(i);
				String sqlValueResult[] = new String[5];
				sqlValueResult[0] = sqlValue[0];
				sqlValueResult[1] = resultDTO.getEmpid();
				sqlValueResult[2] = resultDTO.getResultofexam();
				sqlValueResult[3] = userId;
				sqlValueResult[4] = userId;
				String query = CommonSQL.INSERT_DEPT_EXAM_MAPPING_DETAILS;
				dbUtil.createPreparedStatement(query);
				insertdeptresult = dbUtil.executeUpdate(sqlValueResult);
			}

			if (insert && insertdeptresult) {
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

		return insert;
	}

	/**
	 * @return String
	 */
	private String getDepartmentalId() {
		String deptId = "";
		String deptseq = "";
		String deptConst = "";
		try {
			dbUtil = new DBUtility();
			String sqlQuery = CommonSQL.SELECT_DEPT_EXAM_ID_SEQ;
			dbUtil.createStatement();
			deptseq = dbUtil.executeQry(sqlQuery);
			deptConst = "DEPTEXAM-";
			deptId = deptConst + deptseq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * finally { try { dbUtil.closeConnection(); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */
		return deptId;
	}

	/**
	 * @param empId
	 * @return boolean
	 */
	public boolean checkEmpIdAvailability(String empId) {
		boolean check = false;
		try {
			String sqlValue[] = new String[1];
			sqlValue[0] = empId;
			dbUtil = new DBUtility();
			String query = CommonSQL.CHECK_EMPID;
			dbUtil.createPreparedStatement(query);
			String empid= dbUtil.executeQry(sqlValue);
			if(empid!=null && empid.length()>0){
				check=true;
			}else{
				check=false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}

}

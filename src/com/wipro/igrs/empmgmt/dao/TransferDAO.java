

package com.wipro.igrs.empmgmt.dao;


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.empmgmt.dto.TransferDTO;
import com.wipro.igrs.empmgmt.sql.CommonSQL;
import com.wipro.igrs.util.CommonUtil;


public class TransferDAO {
	DBUtility dbUtil;
	private Logger logger = (Logger) Logger.getLogger(TransferDAO.class);
	public TransferDAO() throws Exception {
		dbUtil = new DBUtility();
	}

	public ArrayList getnewLocation() throws Exception {
		ArrayList list = null;
		try {
			dbUtil.createStatement();
			String query = CommonSQL.RETRIVE_ALL_LOCATIONS;
			list = dbUtil.executeQuery(query);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public ArrayList displayEmpDetails(String[] empid) throws Exception {
		ArrayList list = new ArrayList();
		try {
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_TRANSFER_EMPLOYEE_DETAILS);

			list = dbUtil.executeQuery(empid);

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	public boolean submitTransferDetails(TransferDTO transferDTO,
			String strUserId) throws Exception {
		boolean flag = false;
		try {

			String values[] = {

					transferDTO.getEmployeeId(), // EMP_ID
					transferDTO.getOldLoc(), // DEPT_FROM
					transferDTO.getNewLoc(), // DEPT_TO
					CommonUtil.getConvertedDate(transferDTO.getDateOfJoining()), // DATE_OF_TRANSFER
					transferDTO.getComments(), // COMMENTS
					"INTER", // TRANSFER_TYPE
					"A", // TRANSFER_STATUS
					strUserId, // CREATED_BY
					// CREATED_DATE
					strUserId, // UPDATE_BY
			// UPDATE_DATE

			};
			String query = CommonSQL.INSERT_EMPMGMT_TRANSFER_DETAILS;
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(query);
			flag = dbUtil.executeUpdate(values);
			if (flag = true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;

	}
	
	
	public boolean submitIntraTransferDetails(TransferDTO transferDTO,
			String strUserId) throws Exception {
		boolean flag = false;
		try {

			String values[] = {

					transferDTO.getEmployeeId(), // EMP_ID
					transferDTO.getOldLoc(), // DEPT_FROM
					//transferDTO.getNewLoc(), // DEPT_TO
					//CommonUtil.getConvertedDate(transferDTO.getDateOfJoining()), // DATE_OF_TRANSFER
					transferDTO.getComments(), // COMMENTS
					"INTRA", // TRANSFER_TYPE
					"A", // TRANSFER_STATUS
					strUserId, // CREATED_BY
					// CREATED_DATE
					strUserId, // UPDATE_BY
			// UPDATE_DATE

			};
			String query = CommonSQL.INSERT_EMPMGMT_INTRATRANSFER_DETAILS;
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(query);
			flag = dbUtil.executeUpdate(values);
			if (flag = true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			dbUtil.closeConnection();
		}
		return flag;

	}
}

package com.wipro.igrs.hrpayroll.hrpl.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceReportDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.MappingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.PenalityDTO;
import com.wipro.igrs.hrpayroll.hrpl.sql.CommonSQL;

public class HrplinkingDAO {
	DBUtility dbUtil = null;

	AttendanceDTO attendanceReportDTO = null;
	private Logger logger = (Logger) Logger.getLogger(HrplinkingDAO.class);

	public HrplinkingDAO() throws Exception {
		// TODO Auto-generated constructor stub
		dbUtil = new DBUtility();
	}

	public ArrayList displayLeaves() throws Exception {
		ArrayList list = null;
		try {

			dbUtil.createStatement();
			String query = CommonSQL.RETRIVE_ALL_LEAVES;
			list = dbUtil.executeQuery(query);

			return list;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getFinancialYear() throws Exception {
		ArrayList list = null;
		try {
			dbUtil.createStatement();
			String query = CommonSQL.RETRIVE_ALL_FISCALFROMYEAR;
			list = dbUtil.executeQuery(query);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param transactionid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPendingLeavesonId(String transactionid)
			throws Exception {
		ArrayList list = null;
		try {
			String execute_values[] = { transactionid };
			String query = CommonSQL.RETRIVE_LEAVES_ON_ID1;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_values);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayLeaveEmpStatus(LeaveDTO leaveDTO) throws Exception {
		ArrayList list = null;
		try {
			// Date toyear=new Date();
			String execute_criteria[] = { leaveDTO.getFiscal_fromyear(),
					leaveDTO.getFiscal_toyear(), leaveDTO.getEmpid() };
			// String query=CommonSQL.RETRIEVE_LEAVE_EMP_STATUS;
			// dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_criteria);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayLeaveEmpLeaveReport(LeaveDTO leaveDTO)
			throws Exception {
		ArrayList list = null;
		try {
			String execute_criteria[] = { leaveDTO.getEmpid(),
					leaveDTO.getEmpid() };
			String query = CommonSQL.RETRIVE_LEAVE_EMP_REPORT;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_criteria);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	public boolean updateLeaveStatus(LeaveDTO leaveDTO, LeaveDTO leaveDTO1,
			String strUserId) throws Exception {
		boolean result = false;
		try {
			String leavetype = null;
			String conversionflag = "";
			if (leaveDTO.getConvert_leve_type().length() > 0) {
				conversionflag = "A";
			} else {
				conversionflag = "D";
			}

			String Updatablevlaues[] = { strUserId, // Upadted By
					conversionflag, // CONVERSION_FLAG
					leaveDTO.getConvert_leve_type(), // CONVERTED_FROM
					leaveDTO.getLeaveStatus(), // APPROVAL_STATUS
					strUserId, // APPROVED BY
					leaveDTO.getLeave_approved_reason(), // APPROVAL_REMARKS
					leaveDTO1.getTransactionid() };
			String query = CommonSQL.UPDATE_LEAVE_STATUS;
			// dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(query);
			result = dbUtil.executeUpdate(Updatablevlaues);

			if (leaveDTO.getLeaveStatus().equals("APPROVED")) {
				if (leaveDTO.getConvert_leve_type().trim().length() > 0) {
					leavetype = leaveDTO.getConvert_leve_type();
				} else {
					leavetype = leaveDTO.getLeavetype();
				}
				String values[] = { leaveDTO.getEmpid(), leavetype };
				String querybalance = CommonSQL.SELECT_BALANCE_LEAVES;
				dbUtil.createPreparedStatement(querybalance);
				ArrayList list = dbUtil.executeQuery(values);

				for (int i = 0; i < list.size(); i++) {
					ArrayList _leaves = (ArrayList) list.get(i);

					if (list != null) {
						leaveDTO.setLeaveAvail((String) _leaves.get(0));

					}
				}

				int days = Integer.parseInt(leaveDTO.getLeaveAvail())
						- Integer.parseInt(leaveDTO.getDays());
				String queryvalues[] = { String.valueOf(days),
						leaveDTO.getEmpid(), leavetype };
				String updatequery = CommonSQL.UPDATE_LEAVE_BALANCE;
				dbUtil.createPreparedStatement(updatequery);
				boolean result1 = dbUtil.executeUpdate(queryvalues);
			}
			if (result) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/**
	 * @param attendance_list
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean updateAttendanceReport(ArrayList attendance_list,
			String strUserId) throws Exception {
		dbUtil.setAutoCommit(false);
		boolean flag = false;
		String timein = null;
		String timeout = null;
		for (int i = 0; i < attendance_list.size(); i++) {
			attendanceReportDTO = (AttendanceDTO) attendance_list.get(i);

			if (attendanceReportDTO.getTimein_hours() != null
					&& attendanceReportDTO.getTimeout_hours() != null
					&& attendanceReportDTO.getCounter() != null) {
				timein = attendanceReportDTO.getDate() + " "
						+ attendanceReportDTO.getTimein_hours() + ":"
						+ attendanceReportDTO.getTimein_minutes()
						+ attendanceReportDTO.getTimein_session();

				timeout = attendanceReportDTO.getDate() + " "
						+ attendanceReportDTO.getTimeout_hours() + ":"
						+ attendanceReportDTO.getTimeout_minutes()
						+ attendanceReportDTO.getTimeout_session();
				String sqlValues[] = {
						timein,
						timeout,
						strUserId, // Updated_By
						attendanceReportDTO.getReason(),
						attendanceReportDTO.getDate(),
						attendanceReportDTO.getDate(),
						attendanceReportDTO.getEmpid() };
				String sqlQuery = CommonSQL.UPDATE_ATTENDANCE_QUERY;
				dbUtil.createPreparedStatement(sqlQuery);
				flag = dbUtil.executeUpdate(sqlValues);
			}
		}
		if (flag == true) {
			dbUtil.commit();
			dbUtil.closeConnection();
			return true;
		} else {
			dbUtil.rollback();
			dbUtil.closeConnection();
			return false;
		}

	}

	public ArrayList getLeavesType(LeaveDTO leaveDTO) throws Exception {
		ArrayList list = null;
		try {

			String execute_values[] = { leaveDTO.getLeavetypeID() };
			String query = CommonSQL.RETRIVE_LEAVES;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_values);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public ArrayList submitAttendanceReport(
			AttendanceReportDTO attendancereportdto) throws Exception {
		ArrayList list = null;
		try {
			System.out.println("=================================In DAO ========================");
			String execute_values[] = { attendancereportdto.getEmpid(),
					attendancereportdto.getMonth(),
					attendancereportdto.getYear() };
			System.out.println("In DAO After Array");
			String query = CommonSQL.RETRIVE_ATTENDANCE_DETAILS;
			dbUtil.createPreparedStatement(query);
			System.out.println("In DAO After PrepareStateMent");
			list = dbUtil.executeQuery(execute_values);
			System.out.println("In DAO After ExecuteQuery");
			dbUtil.closeConnection();
			return list;

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/*
	 * Arrears DAO operations
	 * 
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList get_Empid() throws Exception {
		ArrayList list = null;
		try {

			dbUtil.createStatement();
			String sql = CommonSQL.SELECT_ARREARS_EMPID;
			list = dbUtil.executeQuery(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param arrearsdto
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitArrears(ArrearsDTO arrearsdto, String strUserId)
			throws Exception {
		boolean flag = false;
		try {

			String sqlValues[] = { arrearsdto.getTxtEmpID(), // EMP_ID
					arrearsdto.getArrearType(), // ARREAR_TYPE
					arrearsdto.getArrearsamt(), // ARREAR_AMOUNT
					arrearsdto.getArrearPayMonth(), // ARREAR_PAY_MONTH
					arrearsdto.getArrearStatus(), // ARREAR_STATUS
					this.getArrearsTransId(), // EMP_PAY_ARREARS_ID
					strUserId, // CREATED_BY
					strUserId, // UPDATED_BY
					// CREATED_DATE
					// UPDATED_DATE
					arrearsdto.getReason() // ARREAR_COMMENTS
			};
			CommonSQL common = new CommonSQL();
			String sqlQuery = CommonSQL.INSERT_ARREARS;
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == true) {
				dbUtil.commit();
			} else {
				dbUtil.rollback();
			}
			return flag;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}

	/**
	 * @param penalitydto
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitPenality(PenalityDTO penalitydto, String strUserId)
			throws Exception {

		String sqlValues[] = { penalitydto.getPanEmpID(),
				penalitydto.getPanreason(), "", // PENALTY_ACTION
				penalitydto.getPanamt(), penalitydto.getPenalityAmountType(), // PENALTY_AMT_TYPE
				penalitydto.getPenalityDurationType(), // PENALTY_DURATION_TYPE
				penalitydto.getPenaltyDuration(), // PENALTY_DURATION
				penalitydto.getPenaltyStatus(), // PENALTY_STATUS
				strUserId, // CREATED_BY
				// CREATED_DATE
				strUserId, // UPDATED_BY
				// UPDATED_DATE
				this.getpenaltyTransId(), // EMP_PENLATY_ID
				"" // PENALTY_TYPE_ID
		};
		String sqlQuery = CommonSQL.INSERT_PENALTY;
		dbUtil.setAutoCommit(false);
		dbUtil.createPreparedStatement(sqlQuery);
		boolean flag = dbUtil.executeUpdate(sqlValues);
		if (flag == true) {
			dbUtil.commit();

		} else {
			dbUtil.rollback();
		}
		return flag;
	}

	/*
	 * Generate Transaction Id For Penalty
	 * 
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	private String getpenaltyTransId() throws Exception {
		String penaltyTransId = "";
		String penaltyTransIdConst = "";
		try {
			dbUtil.createStatement();
			penaltyTransId = dbUtil
					.executeQry(CommonSQL.SELECT_PENALTY_TRANS_ID_SEQ);
			penaltyTransIdConst = "PEN-";

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return penaltyTransIdConst + penaltyTransId;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private String getArrearsTransId() throws Exception {
		String arrearsTransId = "";
		String arrearsTransIdConst = "";
		try {
			dbUtil.createStatement();
			arrearsTransId = dbUtil
					.executeQry(CommonSQL.SELECT_ARREARS_TRANS_ID_SEQ);
			arrearsTransIdConst = "ARR";

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return arrearsTransIdConst + arrearsTransId;
	}

	/*
	 * Leave Grade Cadre Mapping
	 * 
	 */
	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllLeaves() throws Exception {
		ArrayList list = null;
		try {

			dbUtil.createStatement();
			String query = CommonSQL.RETRIVE_ALL_LEAVE_ID;
			list = dbUtil.executeQuery(query);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}

		return list;
	}

	/**
	 * @param mappingDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLeaveGradeCadre(MappingDTO mappingDTO) throws Exception {
		ArrayList list = null;
		try {
			String sqlValues[] = { mappingDTO.getGradetypeid(),
					mappingDTO.getCadretypeid() };
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_ALL_LEAVE_GRADE_CADRE);
			list = dbUtil.executeQuery(sqlValues);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	public boolean deleteLeaveGradeCadre(String leavetypeid,
			String gradetypeid, String cadretypeid) throws Exception {
		boolean result = false;
		try {
			String sqlValues[] = { leavetypeid, gradetypeid, cadretypeid };
			dbUtil.createPreparedStatement(CommonSQL.DELETE_LEAVE_GRADE_CADRE);
			result = dbUtil.executeUpdate(sqlValues);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
			return result;
		}
	}

	public ArrayList getAllGrades() throws Exception {
		ArrayList list = null;
		try {
			dbUtil.createStatement();
			String query = CommonSQL.RETRIVE_ALL_GRADE_ID;
			list = dbUtil.executeQuery(query);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param mappingDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllLeavesCadreGrade(MappingDTO mappingDTO)
			throws Exception {
		ArrayList list = null;
		try {
			String sqlValues[] = { mappingDTO.getGradetypeid(),
					mappingDTO.getCadretypeid() };
			dbUtil
					.createPreparedStatement(CommonSQL.RETRIVE_ALL_LEAVE_CADRE_NOTIN);
			list = dbUtil.executeQuery(sqlValues);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;

	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllCadres(String gradeId) throws Exception {
		ArrayList list = null;
		try {
			String sqlvalues[] = { gradeId };
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_ALL_CADRE_ID);
			// String query=CommonSQL.RETRIVE_ALL_CADRE_ID;
			list = dbUtil.executeQuery(sqlvalues);

			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param mappingDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitLeaveGradeCadre(MappingDTO mappingDTO, String strUserId)
			throws Exception {
		boolean flag = false;
		try {
			String sqlValues[] = { mappingDTO.getGradetypeid(),
					mappingDTO.getCadretypeid(), mappingDTO.getLeavetypeid()

			};

			String sqlQuery = CommonSQL.INSERT_LEAVE_GRADE_CADRE;
			dbUtil.setAutoCommit(false);
			dbUtil.createPreparedStatement(sqlQuery);
			flag = dbUtil.executeUpdate(sqlValues);
			if (flag == true) {
				dbUtil.commit();

			} else {
				dbUtil.rollback();
			}
			return flag;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return flag;
	}

	public ArrayList getAllLeavesonID(String empid) throws Exception {
		ArrayList list = null;
		try {
			String sqlvalues[] = { empid };
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_ALL_LEAVES_ID);
			// String query=CommonSQL.RETRIVE_ALL_CADRE_ID;
			list = dbUtil.executeQuery(sqlvalues);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param leaveDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitLeaveApplication(LeaveDTO leaveDTO, String strUserId)
			throws Exception {
		String sqlvalues[] = { strUserId, leaveDTO.getLeavetypeID(),
				getConvertedDate(leaveDTO.getLeave_fromdate()),
				getConvertedDate(leaveDTO.getLeave_todate()),
				leaveDTO.getLeave_reason(), leaveDTO.getAddressonleave(),
				leaveDTO.getPhonenumberonleave(), strUserId, // CREATED BY
				strUserId, // UPDATED_BY,
				// UPDATED_DATE,
				leaveDTO.getTransactionid(), // TRANSACTION_ID,
				"", // CONVERSION_FLAG,
				"", // CONVERTED_FROM,
				"PENDING", // APPROVAL STATUS
				"", // APPROVAL_DATE,
				"", // APPROVED_BY,
				"", // APPROVAL_REMARKS
		};
		String sqlQuery = CommonSQL.INSERT_IGRS_EMP_LEAVE_DETAILS;
		dbUtil.setAutoCommit(false);
		dbUtil.createPreparedStatement(sqlQuery);
		boolean flag = dbUtil.executeUpdate(sqlvalues);
		/*if(flag == true){
		String strparam[]={strUserId,leaveDTO.getLeavetypeID()};
		dbUtil.createPreparedStatement(CommonSQL.SELECT_LEAVE_BALANCE);
		ArrayList list=dbUtil.executeQuery(strparam);
		if(list.size()==0){
			String paramvalues[]={strUserId, leaveDTO.getLeavetypeID(),leaveDTO.getLeaveAvail()};
			dbUtil.createPreparedStatement(CommonSQL.INSERT_IGRS_EMP_LEAVE_BALANCE_DETAILS);
			dbUtil.executeUpdate(paramvalues);
		}
		}*/
		if (flag == true) {
			dbUtil.commit();

		} else {
			dbUtil.rollback();
		}
		return flag;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getTransactionId() throws Exception {
		ArrayList list = null;
		try {
			dbUtil.createStatement();
			list = dbUtil.executeQuery(CommonSQL.RETREIVE_LEAVE_TRANSACTIONID);
			return list;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * @param empid
	 * @param leavetype
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLeaveAvailable(String empid, String leavetype)
			throws Exception {
		ArrayList list = null;
		ArrayList leavereportlist=new ArrayList();
		try {
			// Date toyear=new Date();
			String execute_criteria[] = { empid, leavetype, empid, leavetype };
			String query = CommonSQL.SELECT_USED_AVAIL_LEAVE;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_criteria);
			for (int i = 0; i < list.size(); i++) {
				ArrayList _leaves = (ArrayList) list.get(i);
				
				if (_leaves != null && _leaves.size()>=2) {		
					
					LeaveDTO leavedto=new LeaveDTO();					
					leavedto.setLeaveUsed((String)_leaves.get(0));
					leavedto.setLeaveAvail((String)_leaves.get(1));
					leavereportlist.add(leavedto);

				}

			}
			return leavereportlist;
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return leavereportlist;
	}

	/**
	 * @param empid
	 * @param leavetype
	 * @return
	 * @throws Exception
	 */
	public ArrayList getBalanceLeave(String empid, String leavetype)
			throws Exception {
		ArrayList list = null;
		try {
			String execute_criteria[] = { empid, leavetype };
			String query = CommonSQL.SELECT_BALANCED_LEAVE;
			dbUtil.createPreparedStatement(query);
			list = dbUtil.executeQuery(execute_criteria);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			dbUtil.closeConnection();
		}
		return list;
	}

	/**
	 * @param mappingDTO
	 * @param leavegradecadrelist
	 * @return
	 * @throws Exception
	 */
	public boolean updateLeaveGradeCadreMapping(MappingDTO mappingDTO,
			ArrayList leavegradecadrelist) throws Exception {
		boolean result1 = true;
		if (leavegradecadrelist != null) {
			for (int i = 0; i < leavegradecadrelist.size(); i++) {
				MappingDTO mappingDTO2 = (MappingDTO) leavegradecadrelist
						.get(i);
				String sqlvalues[] = { mappingDTO2.getLeavetypeid(),
						mappingDTO.getGradetypeid(),
						mappingDTO.getCadretypeid() };
				dbUtil
						.createPreparedStatement(CommonSQL.DELETE_LEAVE_GRADE_CADRE);
				result1 = dbUtil.executeUpdate(sqlvalues);

			}

		}

		boolean result = true;

		if (mappingDTO.getLeavetypeids() != null) {
			String leavetypeid[] = mappingDTO.getLeavetypeids();

			for (int i = 0; i < leavetypeid.length; i++) {
				String sqlvalues[] = { mappingDTO.getGradetypeid(),
						mappingDTO.getCadretypeid(), leavetypeid[i] };
				dbUtil
						.createPreparedStatement(CommonSQL.INSERT_LEAVE_GRADE_CADRE);
				result = dbUtil.executeUpdate(sqlvalues);
				if (result == false) {
					break;
				}
			}
		}
		if (result || result1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * getConvertedDate Returns String which returns a parsed date format.
	 * 
	 * @param dFromDate
	 * 
	 * @return String
	 * 
	 * @Exception
	 */
	public static String getConvertedDate(String dFromDate) {
		String inputDate = dFromDate;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String finalDate = "";
		Date newDate = new Date();
		try {
			newDate = formatter.parse(inputDate);
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			finalDate = format.format(newDate);
		} catch (ParseException e) {
			System.out.print(e);
		}
		return finalDate;
	}

	/*public ArrayList getAvaliableLeavesFromLeaveDetails(String empid,
			String leavetype) {
		List usedlist=null;
		List availlist=null;
		LeaveDTO leavedto = new LeaveDTO();			
		ArrayList returnlist=new ArrayList();
		try {
			// Date toyear=new Date();
			String execute_criteria[] = {leavetype, empid};
			String query = CommonSQL.SELECT_USED_LEAVES;
			dbUtil.createPreparedStatement(query);
			usedlist = dbUtil.executeQuery(execute_criteria);
			dbUtil.createPreparedStatement(CommonSQL.SELECT_AVAILABLE_LEAVE_MASTER);
			availlist=dbUtil.executeQuery(execute_criteria);
			if(usedlist!=null && availlist!=null);
			{
				if(usedlist.size()>=1 && availlist.size()>=1){

					ArrayList usednewlist=(ArrayList)usedlist.get(0);
					ArrayList availnewlist=(ArrayList)availlist.get(0);
					leavedto.setLeaveUsed((String)usednewlist.get(0));
					leavedto.setLeaveAvail((String)availnewlist.get(0));
				}
			}
			
			
			returnlist.add(leavedto);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try{
			dbUtil.closeConnection();
			}catch (Exception e) {
				System.out.println(e);
			}
		}
		return returnlist;
	}*/

}
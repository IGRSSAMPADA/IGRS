

package com.wipro.igrs.hrpayroll.hrpl.bd;


import com.wipro.igrs.hrpayroll.hrpl.dao.HrplinkingDAO;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceReportDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.MappingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.PenalityDTO;
import java.util.ArrayList;


/**
 * @author root
 *
 */
/**
 * @author root
 * 
 */
public class HrplinkingBD {
	HrplinkingDAO hrDAO = null;

	LeaveDTO leavedto = null;

	public HrplinkingBD() throws Exception {

		hrDAO = new HrplinkingDAO();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayLeaves() throws Exception {
		ArrayList leaves = hrDAO.displayLeaves();
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leaves.size(); i++) {
			ArrayList _leaves = (ArrayList) leaves.get(i);

			if (leaves != null) {

				LeaveDTO leavedto = new LeaveDTO();
				leavedto.setTransactionid((String) _leaves.get(0));
				leavedto.setDays((String) _leaves.get(1));
				leavedto.setLeavetype((String) _leaves.get(2));
				leavedto.setLeaveStatus((String) _leaves.get(3));
				leavedto.setLeave_fromdate(_leaves.get(4).toString());
				leavedto.setLeave_todate(_leaves.get(5).toString());
				leavedto.setLeave_reason(_leaves.get(6).toString());
				_leavelist.add(leavedto);

			}
		}
		return _leavelist;
	}

	/**
	 * @param attendancereportDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList submitAttendanceReport(
			AttendanceReportDTO attendancereportDTO) throws Exception {
		
		ArrayList attendancelist = hrDAO
		.submitAttendanceReport(attendancereportDTO);
		
		ArrayList _leavelist = new ArrayList();
		ArrayList _attendancelist = null;
		AttendanceReportDTO attendancedto = null;
		
		for (int i = 0; i < attendancelist.size(); i++) {
			_attendancelist = (ArrayList) attendancelist.get(i);

			if (attendancelist != null) {

				attendancedto = new AttendanceReportDTO();
				
				attendancedto.setDate((String)_attendancelist.get(0));
				
				attendancedto.setDay((String)_attendancelist.get(1));
				
				String holyday_status =(String) _attendancelist.get(2);
				
				if (holyday_status.equals("A")) {
					attendancedto.setTimein(null);
					attendancedto.setTimeout(null);
					attendancedto.setTotalhours(null);
				} else {
					attendancedto.setTimein((String) _attendancelist.get(3));
					
					attendancedto.setTimeout((String) _attendancelist.get(4));
					
					int time_in_hours = getTimeinhours((String) _attendancelist
							.get(3));
					int time_in_minutes = getTimeinMinutes((String) _attendancelist
							.get(3));
					String time_in_session = getTimeSession((String) _attendancelist
							.get(3));
					int time_out_hours = getTimeinhours((String) _attendancelist
							.get(4));
					int time_out_minutes = getTimeinMinutes((String) _attendancelist
							.get(4));
					String time_out_session = getTimeSession((String) _attendancelist
							.get(4));
					if (time_in_session.equalsIgnoreCase("PM")) {
						time_in_hours = time_in_hours + 12;
					}
					if (time_out_session.equalsIgnoreCase("PM")) {
						time_out_hours = time_out_hours + 12;

					}
					int logintime_seconds = ((time_in_hours * 60) + time_in_minutes) * 60;
					int logouttime_seconds = ((time_out_hours * 60) + time_out_minutes) * 60;
					int diff = logouttime_seconds - logintime_seconds;
					String totaltime = convertSecondsToHHMMSS(diff);
					attendancedto.setTotalhours(totaltime);
				}
				_leavelist.add(attendancedto);

			}
		}
		return _leavelist;
	}

	/**
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayLeaveEmpStatus(LeaveDTO leaveDTO) throws Exception {
		ArrayList leave_emp_list = hrDAO.displayLeaveEmpStatus(leaveDTO);
		ArrayList leaveemplist = new ArrayList();
		for (int i = 0; i < leave_emp_list.size(); i++) {
			ArrayList _leaves = (ArrayList) leave_emp_list.get(i);

			if (_leaves != null) {
				LeaveDTO leavedto = new LeaveDTO();
				leavedto.setLeavetype((String) _leaves.get(0));
				leavedto.setLeave_fromdate((String) _leaves.get(1));
				leavedto.setLeave_todate((String) _leaves.get(2));
				leavedto.setLeave_reason((String) _leaves.get(3));
				leavedto.setLeave_requestDate((String) _leaves.get(4));
				leavedto.setLeaveStatus((String) _leaves.get(5));
				leaveemplist.add(leavedto);

			}
		}
		return leaveemplist;
	}

	/**
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayLeaveEmpLeaveReport(LeaveDTO leaveDTO)
	throws Exception {
		ArrayList leave_emp_list = hrDAO.displayLeaveEmpLeaveReport(leaveDTO);
		ArrayList leavereportlist = new ArrayList();
		for (int i = 0; i < leave_emp_list.size(); i++) {
			ArrayList _leaves = (ArrayList) leave_emp_list.get(i);

			if (_leaves != null) {
				LeaveDTO leavedto = new LeaveDTO();
				leavedto.setLeavetype((String) _leaves.get(0));
				leavedto.setLeaveUsed((String) _leaves.get(1));
				leavedto.setLeaveAvail((String) _leaves.get(2));

				leavereportlist.add(leavedto);

			}
		}
		return leavereportlist;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getFinancialYear() throws Exception {
		ArrayList financialfromyear = hrDAO.getFinancialYear();
		ArrayList _fiscalfromyear = new ArrayList();
		ArrayList _leaves = null;
		for (int i = 0; i < financialfromyear.size(); i++) {
			_leaves = (ArrayList) financialfromyear.get(i);
			if (_leaves != null) {

				leavedto = new LeaveDTO();
				leavedto.setFiscalyearid((String) _leaves.get(0));
				leavedto.setFiscal_fromyear((String) _leaves.get(1));
				leavedto.setFiscal_toyear((String) _leaves.get(2));
				_fiscalfromyear.add(leavedto);

			}
		}
		return _fiscalfromyear;

	}

	/**
	 * @param transactionid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getPendingLeavesonId(String transactionid)
	throws Exception {
		ArrayList leaves = hrDAO.getPendingLeavesonId(transactionid);

		ArrayList _leavelist = new ArrayList();
		ArrayList _leaves = null;
		for (int i = 0; i < leaves.size(); i++) {
			_leaves = (ArrayList) leaves.get(i);

			if (leaves != null) {

				LeaveDTO leavedto = new LeaveDTO();
				leavedto.setEmpid(_leaves.get(0).toString());
				leavedto.setName(_leaves.get(1).toString());
				leavedto.setDays((String) _leaves.get(2));
				leavedto.setLeavetype((String) _leaves.get(3));
				leavedto.setLeaveStatus((String) _leaves.get(4));
				leavedto.setLeave_fromdate(_leaves.get(5).toString());
				leavedto.setLeave_todate(_leaves.get(6).toString());
				leavedto.setLeave_reason(_leaves.get(7).toString());
				leavedto.setLeavetypeID(_leaves.get(8).toString());
				leavedto.setTransactionid(transactionid);
				_leavelist.add(leavedto);

			}
		}
		return _leavelist;

	}

	/**
	 * @param leaveDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLeavesType(LeaveDTO leaveDTO) throws Exception {
		ArrayList leavetypes = hrDAO.getLeavesType(leaveDTO);

		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				LeaveDTO leavedto = new LeaveDTO();
				leavedto.setLeavetypeID((String) _leaves.get(0));
				leavedto.setLeavetype((String) _leaves.get(1));
				_leavelist.add(leavedto);

			}
		}
		return _leavelist;
	}

	/**
	 * @param attendance_list
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean updateAttendanceReport(ArrayList attendance_list,
			String strUserId) throws Exception {
		boolean flag = false;

		flag = hrDAO.updateAttendanceReport(attendance_list, strUserId);

		return flag;

	}

	/**
	 * @param _leaveDTO
	 * @param leaveDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean updateLeaveStatus(LeaveDTO _leaveDTO, LeaveDTO leaveDTO,
			String strUserId) throws Exception {
		return hrDAO.updateLeaveStatus(_leaveDTO, leaveDTO, strUserId);
	}

	/**
	 * @param arrearsdto
	 * @return
	 * @throws Exception
	 */
	public boolean getArrears_Empid(ArrearsDTO arrearsdto) throws Exception {
		boolean result = false;
		ArrayList _list = null;
		ArrayList list = hrDAO.get_Empid();
		Outer: for (int i = 0; i < list.size(); i++) {
			_list = (ArrayList) list.get(i);
			for (int j = 0; j < _list.size(); j++) {
				String empid = (String) _list.get(j);
				if (empid.equals(arrearsdto.getTxtEmpID())) {
					result = true;
					break Outer;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * @param penalitydto
	 * @return
	 * @throws Exception
	 */
	public boolean getPenality_Empid(PenalityDTO penalitydto) throws Exception {
		boolean result = false;
		ArrayList _list = null;
		ArrayList list = hrDAO.get_Empid();
		Outer: for (int i = 0; i < list.size(); i++) {
			_list = (ArrayList) list.get(i);
			for (int j = 0; j < _list.size(); j++) {
				String empid = (String) _list.get(j);
				if (empid.equals(penalitydto.getPanEmpID())) {
					result = true;
					break Outer;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	public boolean submitArrears(ArrearsDTO arrearsDTO, String strUserId)
	throws Exception {
		return hrDAO.submitArrears(arrearsDTO, strUserId);

	}

	/**
	 * @param penalityDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitPenality(PenalityDTO penalityDTO, String strUserId)
	throws Exception {
		return hrDAO.submitPenality(penalityDTO, strUserId);
	}

	int getTimeinhours(String time) {
		int time_index = time.indexOf(":");
		int timeinhours = Integer.parseInt(time.substring(0, time_index));
		return timeinhours;
	}

	int getTimeinMinutes(String time) {
		int time_index = time.indexOf(":");
		int _time_index = time.indexOf("M");
		int timeinmin = Integer.parseInt(time.substring(time_index + 1,
				_time_index - 1));
		return timeinmin;
	}

	String getTimeSession(String time) {
		int _time_index = time.indexOf("M");
		String session = time.substring(_time_index - 1, _time_index + 1);
		return session;
	}

	String convertSecondsToHHMMSS(int seconds) {
		int secondsPerMinute = 60;
		int minutesPerHour = 60;
		int hours = (int) Math.floor(Math.floor(seconds / secondsPerMinute)
				/ minutesPerHour);
		int TotalMinutes = (int) Math.floor(seconds / secondsPerMinute);
		int minutes = TotalMinutes % minutesPerHour;
		minutes = (minutes == 60) ? 00 : minutes;

		return hours + ":" + minutes;

	}

	/*
	 * All Leaves
	 * 
	 * 
	 */

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllLeaves() throws Exception {
		ArrayList leavetypes = hrDAO.getAllLeaves();
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				MappingDTO mappingDTO = new MappingDTO();
				mappingDTO.setLeavetypeid((String) _leaves.get(0));
				mappingDTO.setLeavetypename((String) _leaves.get(1));
				_leavelist.add(mappingDTO);

			}
		}
		return _leavelist;

	}

	/**
	 * @param mappingDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLeaveGradeCadre(MappingDTO mappingDTO) throws Exception {
		ArrayList leavetypes = hrDAO.getLeaveGradeCadre(mappingDTO);
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				MappingDTO mappingDTO1 = new MappingDTO();
				mappingDTO1.setLeavetypeid((String) _leaves.get(0));
				mappingDTO1.setLeavetypename((String) _leaves.get(1));
				_leavelist.add(mappingDTO1);

			}
		}
		return _leavelist;
	}

	/**
	 * @param mappingDTO
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllLeavesCadreGrade(MappingDTO mappingDTO)
	throws Exception {
		ArrayList leavetypes = hrDAO.getAllLeavesCadreGrade(mappingDTO);
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				MappingDTO mappingDTO1 = new MappingDTO();
				mappingDTO1.setLeavetypeid((String) _leaves.get(0));
				mappingDTO1.setLeavetypename((String) _leaves.get(1));
				_leavelist.add(mappingDTO1);

			}
		}
		return _leavelist;
	}

	/**
	 * @param leavetypeid
	 * @param gradetypeid
	 * @param cadretypeid
	 * @return
	 * @throws Exception
	 */
	public boolean deleteLeaveGradeCadre(String leavetypeid,
			String gradetypeid, String cadretypeid) throws Exception {
		return hrDAO.deleteLeaveGradeCadre(leavetypeid, gradetypeid,
				cadretypeid);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllGrades() throws Exception {
		ArrayList leavetypes = hrDAO.getAllGrades();
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				MappingDTO mappingDTO = new MappingDTO();
				mappingDTO.setGradetypeid((String) _leaves.get(0));
				mappingDTO.setGradetypename((String) _leaves.get(1));
				_leavelist.add(mappingDTO);

			}
		}

		return _leavelist;

	}

	/**
	 * @param gradeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllCadres(String gradeId) throws Exception {
		ArrayList leavetypes = hrDAO.getAllCadres(gradeId);
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				MappingDTO mappingDTO = new MappingDTO();
				mappingDTO.setCadretypeid((String) _leaves.get(0));
				mappingDTO.setCadretypename((String)_leaves.get(1));
				_leavelist.add(mappingDTO);

			}
		}
		return _leavelist;

	}

	/**
	 * @param mappingDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitLeaveGradeCadre(MappingDTO mappingDTO, String strUserId)
	throws Exception {
		return hrDAO.submitLeaveGradeCadre(mappingDTO, strUserId);
	}

	/**
	 * @param empid
	 * @return
	 * @throws Exception
	 */
	public ArrayList getAllLeavesonID(String empid) throws Exception {
		ArrayList leavetypes = hrDAO.getAllLeavesonID(empid);
		ArrayList _leavelist = new ArrayList();
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);

			if (leavetypes != null) {

				LeaveDTO leaveDTO = new LeaveDTO();
				leaveDTO.setLeavetypeID((String) _leaves.get(0));
				leaveDTO.setLeavetype((String) _leaves.get(1));
				_leavelist.add(leaveDTO);

			}
		}
		return _leavelist;

	}

	/**
	 * @param leaveDTO
	 * @param strUserId
	 * @return
	 * @throws Exception
	 */
	public boolean submitLeaveApplication(LeaveDTO leaveDTO, String strUserId)
	throws Exception {
		return hrDAO.submitLeaveApplication(leaveDTO, strUserId);
	}

	/**
	 * @param empid
	 * @param leavetype
	 * @return
	 * @throws Exception
	 */
	public ArrayList getLeaveAvailable(String empid, String leavetype)
	throws Exception {
		
		ArrayList leave_emp_list=null;
		leave_emp_list = hrDAO.getLeaveAvailable(empid, leavetype);
	/*	if(leave_emp_list.size()==0){
			
			leave_emp_list=hrDAO.getAvaliableLeavesFromLeaveDetails(empid,leavetype);
		}
		ArrayList leavereportlist = new ArrayList();
		for (int i = 0; i < leave_emp_list.size(); i++) {
			ArrayList _leaves = (ArrayList) leave_emp_list.get(i);
			
			if (_leaves != null && _leaves.size()>=2) {		
				
				
				String usedleave=new String(_leaves.get(0).toString());
				String availleave=new String(_leaves.get(1).toString());
				leavedto.setLeaveUsed(usedleave);
				leavedto.setLeaveAvail(availleave);
				leavereportlist.add(leavedto);

			}

		}
		System.out.println("OUTSIDE CONDITIOn"+leavereportlist);*/
		return leave_emp_list;
		// public getAllLeavesonID
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public String getTransactionId() throws Exception {
		ArrayList leavetypes = hrDAO.getTransactionId();
		String transactionid = null;
		for (int i = 0; i < leavetypes.size(); i++) {
			ArrayList _leaves = (ArrayList) leavetypes.get(i);
			if (leavetypes != null) {
				transactionid = (String) _leaves.get(0);
			}
		}
		return transactionid;
	}

	public boolean updateLeaveGradeCadreMapping(MappingDTO mappingDTO,
			ArrayList leavegradecadrelist) throws Exception {

		return hrDAO.updateLeaveGradeCadreMapping(mappingDTO,
				leavegradecadrelist);

	}

}
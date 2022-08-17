

package com.wipro.igrs.hrpayroll.hrpl.form;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.hrpayroll.hrpl.dto.ArrearsDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.AttendanceReportDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.MappingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.PenalityDTO;


public class HrplinkingForm extends BaseForm {

	private LeaveDTO leaveDTO = new LeaveDTO();;

	private ArrearsDTO arrearsDTO = new ArrearsDTO();

	private PenalityDTO penalityDTO = new PenalityDTO();

	private AttendanceReportDTO attendanceReportDTO = new AttendanceReportDTO();

	private MappingDTO mappingDTO = new MappingDTO();

	private String pageName = null;

	private String editField = null;

	//private AttendanceReportDTO[] attendanceReportArrayDTO=null;

	private ArrayList attendanceList = new ArrayList();

	public HrplinkingForm() {

	}

	/**
	 * @return the leaveDTO
	 */
	public LeaveDTO getLeaveDTO() {
		return leaveDTO;
	}

	/**
	 * @param leaveDTO the leaveDTO to set
	 */
	public void setLeaveDTO(LeaveDTO leaveDTO) {
		this.leaveDTO = leaveDTO;
	}

	/**
	 * @return the arrearsDTO
	 */
	public ArrearsDTO getArrearsDTO() {
		return arrearsDTO;
	}

	/**
	 * @param arrearsDTO the arrearsDTO to set
	 */
	public void setArrearsDTO(ArrearsDTO arrearsDTO) {
		this.arrearsDTO = arrearsDTO;
	}

	/**
	 * @return the penalityDTO
	 */
	public PenalityDTO getPenalityDTO() {
		return penalityDTO;
	}

	/**
	 * @param penalityDTO the penalityDTO to set
	 */
	public void setPenalityDTO(PenalityDTO penalityDTO) {
		this.penalityDTO = penalityDTO;
	}

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the editField
	 */
	public String getEditField() {
		return editField;
	}

	/**
	 * @param editField the editField to set
	 */
	public void setEditField(String editField) {
		this.editField = editField;
	}

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	public AttendanceReportDTO getAttendanceReportDTO() {
		return attendanceReportDTO;
	}

	public void setAttendanceReportDTO(AttendanceReportDTO attendanceReportDTO) {
		this.attendanceReportDTO = attendanceReportDTO;
	}

	public void setAttendanceReportArrayDTO(int index, AttendanceDTO value) {
		System.gc();
		for (; index >= attendanceList.size(); attendanceList
		.add(new AttendanceDTO()))
			;
		attendanceList.add(index, value);
	}

	public AttendanceDTO getAttendanceReportArrayDTO(int index) {
		System.gc();
		for (; index >= attendanceList.size(); attendanceList
		.add(new AttendanceDTO()))
			;
		return (AttendanceDTO) attendanceList.get(index);
	}

	public ArrayList getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(ArrayList attendanceList) {
		this.attendanceList = attendanceList;
	}

	public MappingDTO getMappingDTO() {
		return mappingDTO;
	}

	public void setMappingDTO(MappingDTO mappingDTO) {
		this.mappingDTO = mappingDTO;
	}

}
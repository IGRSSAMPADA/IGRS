package com.wipro.igrs.empAttendance.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.empAttendance.dto.EmpDTO;

public class EmpFORM extends ActionForm
{
	EmpDTO empDto = new EmpDTO();
	private String empDetailsJs;
	private String date;
	private String noOfRecords;

	/**
	 * @return the empDto
	 */
	public EmpDTO getEmpDto()
	{
		return empDto;
	}

	/**
	 * @param empDto the empDto to set
	 */
	public void setEmpDto(EmpDTO empDto)
	{
		this.empDto = empDto;
	}

	/**
	 * @return the empDetailsJs
	 */
	public String getEmpDetailsJs()
	{
		return empDetailsJs;
	}

	/**
	 * @param empDetailsJs the empDetailsJs to set
	 */
	public void setEmpDetailsJs(String empDetailsJs)
	{
		this.empDetailsJs = empDetailsJs;
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/**
	 * @return the noOfRecords
	 */
	public String getNoOfRecords()
	{
		return noOfRecords;
	}

	/**
	 * @param noOfRecords the noOfRecords to set
	 */
	public void setNoOfRecords(String noOfRecords)
	{
		this.noOfRecords = noOfRecords;
	}
}

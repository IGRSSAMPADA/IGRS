/**
 * DepartmentalExamsResultDTO.java
 */
package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;

/**
 * @author jagadish
 *Jun 6, 2008
 * 
 */
public class DepartmentalExamsResultDTO implements Serializable {
	
	private String empid;
	
	private String resultofexam;

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the resultofexam
	 */
	public String getResultofexam() {
		return resultofexam;
	}

	/**
	 * @param resultofexam the resultofexam to set
	 */
	public void setResultofexam(String resultofexam) {
		this.resultofexam = resultofexam;
	}

}



package com.wipro.igrs.hrpayroll.hrpl.dto;


import java.io.Serializable;


public class AttendanceReportDTO implements Serializable {

	public AttendanceReportDTO() {
		// TODO Auto-generated constructor stub
	}

	private String attendanceForm = null;

	private String empid = null;

	private String month = null;

	private String year = null;

	private String date = null;

	private String day = null;

	private String timein = null;

	private String timeout = null;

	private String totalhours = null;

	private String holiday = null;

	/**
	 * @return the attendanceForm
	 */
	public String getAttendanceForm() {
		return attendanceForm;
	}

	/**
	 * @param attendanceForm
	 *            the attendanceForm to set
	 */
	public void setAttendanceForm(String attendanceForm) {
		this.attendanceForm = attendanceForm;
	}

	/**
	 * @return the empid
	 */
	public String getEmpid() {
		return empid;
	}

	/**
	 * @param empid
	 *            the empid to set
	 */
	public void setEmpid(String empid) {
		this.empid = empid;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the timein
	 */
	public String getTimein() {
		return timein;
	}

	/**
	 * @param timein
	 *            the timein to set
	 */
	public void setTimein(String timein) {
		this.timein = timein;
	}

	/**
	 * @return the timeout
	 */
	public String getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            the timeout to set
	 */
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the totalhours
	 */
	public String getTotalhours() {
		return totalhours;
	}

	/**
	 * @param totalhours
	 *            the totalhours to set
	 */
	public void setTotalhours(String totalhours) {
		this.totalhours = totalhours;
	}

	/**
	 * @return the holiday
	 */
	public String getHoliday() {
		return holiday;
	}

	/**
	 * @param holiday
	 *            the holiday to set
	 */
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

}

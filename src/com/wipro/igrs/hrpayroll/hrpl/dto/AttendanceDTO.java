package com.wipro.igrs.hrpayroll.hrpl.dto;

import java.io.Serializable;

public class AttendanceDTO implements Serializable {
	private String empid=null;
	private String date=null;
	private String counter=null;
	private String timein_hours=null;
	private String timein_minutes=null;
	private String timein_session=null;
	private String timeout_hours=null;
	private String timeout_minutes=null;
	private String timeout_session=null;
	private String reason=null;
	private String totalhours=null;
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the counter
	 */
	public String getCounter() {
		return counter;
	}
	/**
	 * @param counter the counter to set
	 */
	public void setCounter(String counter) {
		this.counter = counter;
	}
	/**
	 * @return the timein_hours
	 */
	public String getTimein_hours() {
		return timein_hours;
	}
	/**
	 * @param timein_hours the timein_hours to set
	 */
	public void setTimein_hours(String timein_hours) {
		this.timein_hours = timein_hours;
	}
	/**
	 * @return the timein_minutes
	 */
	public String getTimein_minutes() {
		return timein_minutes;
	}
	/**
	 * @param timein_minutes the timein_minutes to set
	 */
	public void setTimein_minutes(String timein_minutes) {
		this.timein_minutes = timein_minutes;
	}
	/**
	 * @return the timein_session
	 */
	public String getTimein_session() {
		return timein_session;
	}
	/**
	 * @param timein_session the timein_session to set
	 */
	public void setTimein_session(String timein_session) {
		this.timein_session = timein_session;
	}
	/**
	 * @return the timeout_hours
	 */
	public String getTimeout_hours() {
		return timeout_hours;
	}
	/**
	 * @param timeout_hours the timeout_hours to set
	 */
	public void setTimeout_hours(String timeout_hours) {
		this.timeout_hours = timeout_hours;
	}
	/**
	 * @return the timeout_minutes
	 */
	public String getTimeout_minutes() {
		return timeout_minutes;
	}
	/**
	 * @param timeout_minutes the timeout_minutes to set
	 */
	public void setTimeout_minutes(String timeout_minutes) {
		this.timeout_minutes = timeout_minutes;
	}
	/**
	 * @return the timeout_session
	 */
	public String getTimeout_session() {
		return timeout_session;
	}
	/**
	 * @param timeout_session the timeout_session to set
	 */
	public void setTimeout_session(String timeout_session) {
		this.timeout_session = timeout_session;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the totalhours
	 */
	public String getTotalhours() {
		return totalhours;
	}
	/**
	 * @param totalhours the totalhours to set
	 */
	public void setTotalhours(String totalhours) {
		this.totalhours = totalhours;
	}
	
}

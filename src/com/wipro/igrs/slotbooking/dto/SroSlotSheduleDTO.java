package com.wipro.igrs.slotbooking.dto;

import java.io.Serializable;
import java.util.ArrayList;

  

/**
 * @author venshis
 *
 */
public class SroSlotSheduleDTO implements Serializable{
	
	private String radiodate = "refID";  
	private String selectDate;
	private  String slottime;
	private  String slottimeId;
	private String status;
	private String remarks;	
	private String sno;
	private String button;
	private String slotCheck;
	private String checkboxvalues;
	private String checkRemarks;
	private String actiontaken;
	private String userId;
	private String createdBy;
	private String durationFrom;
    private String durationTo;
    private String slotDate;
    private String label;
    
    private String type;
    
    private String selectScheduleDate;
    
    
    
    
    
	public String getSelectScheduleDate() {
		return selectScheduleDate;
	}

	public void setSelectScheduleDate(String selectScheduleDate) {
		this.selectScheduleDate = selectScheduleDate;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}


	private ArrayList slotshedule=new ArrayList();
	
	public String getRadiodate() {
		return radiodate;
	}

	public void setRadiodate(String radiodate) {
		this.radiodate = radiodate;
	}
//Start:===== for time comparison for showing slots for the same day
	

	private int hours;
	private int minutes;
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	//End:===== for time comparison
	
	//added by ankita 17-10-2012
	
	 
	    
	    
	    /**
		 * @return the durationFrom
		 */
		public String getDurationFrom() {
			return durationFrom;
		}

		/**
		 * @param durationFrom the durationFrom to set
		 */
		public void setDurationFrom(String durationFrom) {
			this.durationFrom = durationFrom;
			
			//added by ankita
			//----start----- 17-10-2012
			//System.out.println(this.durationFrom);
		}

		/**
		 * @return the durationTo
		 */
		public String getDurationTo() {
			return durationTo;
		}

		/**
		 * @param durationTo the durationTo to set
		 */
		public void setDurationTo(String durationTo) {
			this.durationTo = durationTo;
			

		}
		
		//--------end-------
	    
	    
	    
	    
	    
	    
	
	/**
	 * 
	 */

	/**
	 * @return
	 */
	public String getSlotCheck() {
		return slotCheck;
	}
	/**
	 * @param slotCheck
	 */
	public void setSlotCheck(String slotCheck) {
		this.slotCheck = slotCheck;
	}
	/**
	 * @return
	 */
	public String getButton() {
		return button;
	}
	/**
	 * @param button
	 */
	public void setButton(String button) {
		this.button = button;
	}
	
	/**
	 * @return
	 */
	public String getSlottime() {
		return slottime;
	}
	/**
	 * @param slottime
	 */
	public void setSlottime(String slottime) {
		this.slottime = slottime;
	}
	/**
	 * @return
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return
	 */
	public String getSelectDate() {
		return selectDate;
	}
	/**
	 * @param selectDate
	 */
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	/**
	 * @return
	 */
	public ArrayList getSlotshedule() {
		return slotshedule;
	}
	/**
	 * @param slotshedule
	 */
	public void setSlotshedule(ArrayList slotshedule) {
		this.slotshedule = slotshedule;
	}
	/**
	 * @return
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return
	 */
	public String getCheckboxvalues() {
		return checkboxvalues;
	}
	/**
	 * @param checkboxvalues
	 */
	public void setCheckboxvalues(String checkboxvalues) {
		this.checkboxvalues = checkboxvalues;
	}
	/**
	 * @return
	 */
	public String getCheckRemarks() {
		return checkRemarks;
	}
	/**
	 * @param checkRemarks
	 */
	public void setCheckRemarks(String checkRemarks) {
		this.checkRemarks = checkRemarks;
	}
	/**
	 * @return
	 */
	public String getSlottimeId() {
		return slottimeId;
	}
	/**
	 * @param slottimeId
	 */
	public void setSlottimeId(String slottimeId) {
		this.slottimeId = slottimeId;
	}
	/**
	 * @return
	 */
	public String getActiontaken() {
		return actiontaken;
	}
	/**
	 * @param actiontaken
	 */
	public void setActiontaken(String actiontaken) {
		this.actiontaken = actiontaken;
	}
	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
  
}

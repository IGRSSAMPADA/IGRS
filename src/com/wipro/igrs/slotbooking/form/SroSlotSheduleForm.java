package com.wipro.igrs.slotbooking.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.slotbooking.dto.SlotBookDTO;
import com.wipro.igrs.slotbooking.dto.SroSlotSheduleDTO;

/**
 * @author venshis
 *
 */
public class SroSlotSheduleForm extends ActionForm {
	
	private SroSlotSheduleDTO sheduledto=new SroSlotSheduleDTO();
	private SlotBookDTO bookdto = new SlotBookDTO();
	//Start:Added by SreeLatha for sroSlotBlockView
	private SlotBookActionForm sbform = new SlotBookActionForm();
	//End:Added by SreeLatha for sroSlotBlockView
    private String selectDate;
   
	private String durationTo;
	private String durationFrom;
	//Start:Added by Ankita for  sroSlotBlockView    
    private ArrayList district;
   	private ArrayList distid;
    private ArrayList sroname;
    private String label;
    private int listsize;
  	//End:Added by Ankita for  sroSlotBlockView
    
    
    private String selectSrDate;
    private String actId;
    private String selectScheduleDate;
    
	private ArrayList districtList = new ArrayList();
    private ArrayList sroNameList = new ArrayList();
    private ArrayList srNameList = new ArrayList();
    private ArrayList scheduleOfficeList = new ArrayList();
    
   

    
    public ArrayList getScheduleOfficeList() {
		return scheduleOfficeList;
	}

	public void setScheduleOfficeList(ArrayList scheduleOfficeList) {
		this.scheduleOfficeList = scheduleOfficeList;
	}

	public String getSelectScheduleDate() {
		return selectScheduleDate;
	}

	public void setSelectScheduleDate(String selectScheduleDate) {
		this.selectScheduleDate = selectScheduleDate;
	}

	public String getSelectSrDate() {
		return selectSrDate;
	}

	public void setSelectSrDate(String selectSrDate) {
		this.selectSrDate = selectSrDate;
	}

	public ArrayList getSrNameList() {
		return srNameList;
	}

	public void setSrNameList(ArrayList srNameList) {
		this.srNameList = srNameList;
	}

	public ArrayList getSroNameList() {
		return sroNameList;
	}

	public void setSroNameList(ArrayList sroNameList) {
		this.sroNameList = sroNameList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public SlotBookDTO getBookdto() {
		return bookdto;
	}

	public void setBookdto(SlotBookDTO bookdto) {
		this.bookdto = bookdto;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	
	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ArrayList getDistrict() {
		return district;
	}

	public void setDistrict(ArrayList district) {
		this.district = district;
	}



	public ArrayList getDistid() {
		return distid;
	}

	public void setDistid(ArrayList distid) {
		this.distid = distid;
	}

	public ArrayList getSroname() {
		return sroname;
	}

	public void setSroname(ArrayList sroname) {
		this.sroname = sroname;
	}

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
	public SroSlotSheduleDTO getSheduledto() {
		return sheduledto;
	}

	/**
	 * @param sheduledto
	 */
	public void setSheduledto(SroSlotSheduleDTO sheduledto) {
		this.sheduledto = sheduledto;
	}

	/**
	 * @return the sbform
	 */
	public SlotBookActionForm getSbform() {
		return sbform;
	}

	/**
	 * @param sbform the sbform to set
	 */
	public void setSbform(SlotBookActionForm sbform) {
		this.sbform = sbform;
		
		System.out.println(this.sbform);
	}
	
	

}

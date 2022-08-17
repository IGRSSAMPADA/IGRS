package com.wipro.igrs.regcompletion.form;


/**
 * ===========================================================================
 * File           :   ApplicantForm.java
 * Description    :   Represents the Applicant Form Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;

/**
 * @author Imran Shaik
 *
 */
public class RegInitCompleteForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String formName;
	private String actionName;
	private RegInitCompleteDTO regDTO = new RegInitCompleteDTO();
	private String checkRegNo  ;
	private String instrument;
	private ArrayList deedList = new ArrayList();
	private ArrayList instList = new ArrayList();
	private ArrayList exemList = new ArrayList();
	private ArrayList formList = new ArrayList();
	private ArrayList deedTxnList = new ArrayList();
	private CommonDTO commonDTO = new  CommonDTO();
	private HashMap deedTxnIdList = new HashMap();
	private ArrayList propertyTxnIdList = new ArrayList(); 
	private ArrayList partyTxnIdList = new ArrayList();
	private final Map values = new HashMap();
	private String compId;
	private String noOfProperty;
	private String stampDuty;
	private String otherFee;
	private String gMarketVal;
	private String regFee;
	private String total;
	private String spotStatus;
	private RegCompletDTO regcompletDto ;
	private HashMap mapBuildingDetails = new HashMap(); 
	private ArrayList complList = new ArrayList();
	
	public ArrayList getComplList() {
		return complList;
	}

	public void setComplList(ArrayList complList) {
		this.complList = complList;
	}

	public RegCompletDTO getRegcompletDto() {
		return regcompletDto;
	}

	public void setRegcompletDto(RegCompletDTO regcompletDto) {
		this.regcompletDto = regcompletDto;
	}

	public String getSpotStatus() {
		return spotStatus;
	}

	public void setSpotStatus(String spotStatus) {
		this.spotStatus = spotStatus;
	}

	public String getNoOfProperty() {
		return noOfProperty;
	}

	public void setNoOfProperty(String noOfProperty) {
		this.noOfProperty = noOfProperty;
	}

	public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public ArrayList getInstList() {
		return instList;
	}
	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}
	public ArrayList getFormList() {
		return formList;
	}
	public void setFormList(ArrayList formList) {
		this.formList = formList;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public ArrayList getDeedList() {
		return deedList;
	}
	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public RegInitCompleteDTO getRegDTO() {
		return regDTO;
	}
	public void setRegDTO(RegInitCompleteDTO regDTO) {
		this.regDTO = regDTO;
	}
	public String getCheckRegNo() {
		return checkRegNo;
	}
	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}

	public ArrayList getExemList() {
		return exemList;
	}

	public void setExemList(ArrayList exemList) {
		this.exemList = exemList;
	}

	public ArrayList getDeedTxnList() {
		return deedTxnList;
	}

	public void setDeedTxnList(ArrayList deedTxnList) {
		this.deedTxnList = deedTxnList;
	}

	public CommonDTO getCommonDTO() {
		return commonDTO;
	}

	public void setCommonDTO(CommonDTO commonDTO) {
		this.commonDTO = commonDTO;
	}

	public HashMap getDeedTxnIdList() {
		return deedTxnIdList;
	}

	public void setDeedTxnIdList(HashMap deedTxnIdList) {
		this.deedTxnIdList = deedTxnIdList;
	}

	public ArrayList getPropertyTxnIdList() {
		return propertyTxnIdList;
	}

	public void setPropertyTxnIdList(ArrayList propertyTxnIdList) {
		this.propertyTxnIdList = propertyTxnIdList;
	}

	public ArrayList getPartyTxnIdList() {
		return partyTxnIdList;
	}

	public void setPartyTxnIdList(ArrayList partyTxnIdList) {
		this.partyTxnIdList = partyTxnIdList;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public String getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}

	public String getGMarketVal() {
		return gMarketVal;
	}

	public void setGMarketVal(String marketVal) {
		gMarketVal = marketVal;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public HashMap getMapBuildingDetails() {
		return mapBuildingDetails;
	}

	public void setMapBuildingDetails(HashMap mapBuildingDetails) {
		this.mapBuildingDetails = mapBuildingDetails;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

		
}

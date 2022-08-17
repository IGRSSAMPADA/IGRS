package com.wipro.igrs.deedDraft.form;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.deedDraft.dto.DeedDraftAppDTO;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;

public class DeedDraftForm extends ActionForm {
	DeedDraftDTO deedDTO = new DeedDraftDTO();
	ArrayList deedDraftViewList = new ArrayList();
	ArrayList deedIdList = new ArrayList();
	private String valueCheckBox;
	private String deedId;
	DeedDraftAppDTO deedAppDTO = new DeedDraftAppDTO();
	private String appTypeSelect;
	private ArrayList appTypeList;
	private String deedAppId;
	private String isValidate;
	private String propId;
	private String isSelected;
	private ArrayList<String> regExemption;
	private ArrayList<String> stampExemption;
	private String isRegExemp;
	private String isEstampExemp;
	private String isReg;
	private String appTypeCheck;
	private BigDecimal totMv;

	public BigDecimal getTotMv() {
		return totMv;
	}

	public void setTotMv(BigDecimal totMv) {
		this.totMv = totMv;
	}

	public String getAppTypeCheck() {
		return appTypeCheck;
	}

	public void setAppTypeCheck(String appTypeCheck) {
		this.appTypeCheck = appTypeCheck;
	}

	public String getIsReg() {
		return isReg;
	}

	public void setIsReg(String isReg) {
		this.isReg = isReg;
	}

	public String getIsRegExemp() {
		return isRegExemp;
	}

	public void setIsRegExemp(String isRegExemp) {
		this.isRegExemp = isRegExemp;
	}

	public String getIsEstampExemp() {
		return isEstampExemp;
	}

	public void setIsEstampExemp(String isEstampExemp) {
		this.isEstampExemp = isEstampExemp;
	}

	public ArrayList<String> getRegExemption() {
		return regExemption;
	}

	public void setRegExemption(ArrayList<String> regExemption) {
		this.regExemption = regExemption;
	}

	public ArrayList<String> getStampExemption() {
		return stampExemption;
	}

	public void setStampExemption(ArrayList<String> stampExemption) {
		this.stampExemption = stampExemption;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public String getPropID() {
		return propId;
	}

	public void setPropID(String propID) {
		this.propId = propID;
	}
	private ArrayList propList;
	private String isProp;

	public String getIsProp() {
		return isProp;
	}

	public void setIsProp(String isProp) {
		this.isProp = isProp;
	}

	public ArrayList getPropList() {
		return propList;
	}

	public void setPropList(ArrayList propList) {
		this.propList = propList;
	}

	public ArrayList getValList() {
		return valList;
	}

	public void setValList(ArrayList valList) {
		this.valList = valList;
	}

	public String getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(String isValidate) {
		this.isValidate = isValidate;
	}
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDeedAppId() {
		return deedAppId;
	}

	public void setDeedAppId(String deedAppId) {
		this.deedAppId = deedAppId;
	}

	public DeedDraftAppDTO getDeedAppDTO() {
		return deedAppDTO;
	}

	public void setDeedAppDTO(DeedDraftAppDTO deedAppDTO) {
		this.deedAppDTO = deedAppDTO;
	}

	public String getAppTypeSelect() {
		return appTypeSelect;
	}

	public void setAppTypeSelect(String appTypeSelect) {
		this.appTypeSelect = appTypeSelect;
	}

	public ArrayList getAppTypeList() {
		return appTypeList;
	}

	public void setAppTypeList(ArrayList appTypeList) {
		this.appTypeList = appTypeList;
	}

	public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public DeedDraftDTO getDeedDTO() {
		return deedDTO;
	}

	public void setDeedDTO(DeedDraftDTO deedDTO) {
		this.deedDTO = deedDTO;
	}

	public ArrayList getDeedDraftViewList() {
		return deedDraftViewList;
	}

	public void setDeedDraftViewList(ArrayList deedDraftViewList) {
		this.deedDraftViewList = deedDraftViewList;
	}

	public ArrayList getDeedIdList() {
		return deedIdList;
	}

	public void setDeedIdList(ArrayList deedIdList) {
		this.deedIdList = deedIdList;
	}

	public String getValueCheckBox() {
		return valueCheckBox;
	}

	public void setValueCheckBox(String valueCheckBox) {
		this.valueCheckBox = valueCheckBox;
	}
	private ArrayList valList;
	//for building
	private ArrayList commonDTO;

	public ArrayList getCommonDTO() {
		return commonDTO;
	}

	public void setCommonDTO(ArrayList commonDTO) {
		this.commonDTO = commonDTO;
	}
	//For Consenter Details
	private ArrayList consenterList;

	public ArrayList getConsenterList() {
		return consenterList;
	}

	public void setConsenterList(ArrayList consenterList) {
		this.consenterList = consenterList;
	}
	private int propCount;

	public int getPropCount() {
		return propCount;
	}

	public void setPropCount(int propCount) {
		this.propCount = propCount;
	}
	//For POA/Partnership
	private String propOptionalCheck;
	private String isVal;

	public String getIsVal() {
		return isVal;
	}

	public void setIsVal(String isVal) {
		this.isVal = isVal;
	}

	public String getPropOptionalCheck() {
		return propOptionalCheck;
	}

	public void setPropOptionalCheck(String propOptionalCheck) {
		this.propOptionalCheck = propOptionalCheck;
	}
}

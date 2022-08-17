package com.wipro.igrs.dataEntry.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.dataEntry.dto.DataEntryDto;

public class DataEntryForm extends ActionForm
{
    
    private String deedType;
    private String district;
    private String temp;
    private String regDate;
    
    private String deed;
    private ArrayList deedList;
    private String dataEntryTxnId;
    
    private String removeAction;
    private String attachAction;
    
    
    DataEntryDto dto = new DataEntryDto();
    
    public String getDeed() {
        return deed;
    }
    public void setDeed(String deed) {
        this.deed = deed;
    }
    public DataEntryDto getDto() {
        return dto;
    }
    public void setDto(DataEntryDto dto) {
        this.dto = dto;
    }
    public ArrayList getDeedList() {
        return deedList;
    }
    public void setDeedList(ArrayList deedList) {
        this.deedList = deedList;
    }
    public String getDeedType() {
        return deedType;
    }
    public void setDeedType(String deedType) {
        this.deedType = deedType;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getTemp() {
        return temp;
    }
    public void setTemp(String temp) {
        this.temp = temp;
    }
   
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public String getDataEntryTxnId() {
        return dataEntryTxnId;
    }
    public void setDataEntryTxnId(String dataEntryTxnId) {
        this.dataEntryTxnId = dataEntryTxnId;
    }
    public String getRemoveAction() {
        return removeAction;
    }
    public void setRemoveAction(String removeAction) {
        this.removeAction = removeAction;
    }
    public String getAttachAction() {
        return attachAction;
    }
    public void setAttachAction(String attachAction) {
        this.attachAction = attachAction;
    }
   

}

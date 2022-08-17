package com.wipro.igrs.rti.form;

//import com.wipro.igrs.rti.dto.RTIStatusTwoDTO;

import com.wipro.igrs.rti.dto.RTIRequestDTO;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class RTIStatusTwoForm extends ActionForm {
    private String resolutionInformation;
    private String resolutionRemark;
    private String actionID;
    private String challanDate;
    private String radiobuttonPayment1;
    private String chkChallanDel;
    private String cashReceiptID;
    private String bankName;
    private String amount;
    private String challanNo;

    public RTIStatusTwoForm() {
    }


    RTIRequestDTO rtiDTO = new RTIRequestDTO();


    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    public String getActionID() {
        return actionID;
    }

    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        String s = "";
        /*  if (resolutionInformation == null || resolutionInformation.length() == 0) {
            errors.add("resolution",
                       new ActionError("errors.resolution.required"));
        }
        if (resolutionRemark == null || resolutionRemark.length() == 0) {
            errors.add("remark", new ActionError("errors.remark.required"));
        }

        if(rtiDTO.getCashReceiptID()==null || rtiDTO.getCashReceiptID().length()==0){
            errors.add("cashreceiptid", new ActionError("errors.cashreceiptid.required"));
        }
        if(rtiDTO.getBankName()==null||rtiDTO.getBankName().length()==0){
            errors.add("bankname", new ActionError("errors.bankname.required"));
        }
        if(rtiDTO.getAmount()==null||rtiDTO.getAmount().length()==0){
            errors.add("amount", new ActionError("errors.amount.required"));
        }
        if(rtiDTO.getChallanNo()==null||rtiDTO.getChallanNo().length()==0)
            errors.add("challan", new ActionError("errors.challan.required")); */
        return errors;
    }

    public void setResolutionInformation(String resolutionInformation) {
        this.resolutionInformation = resolutionInformation;
    }

    public String getResolutionInformation() {
        return resolutionInformation;
    }

    public void setResolutionRemark(String resolutionRemark) {
        this.resolutionRemark = resolutionRemark;
    }

    public String getResolutionRemark() {
        return resolutionRemark;
    }

    public void setRtiDTO(RTIRequestDTO rtiDTO) {
        this.rtiDTO = rtiDTO;
    }

    public RTIRequestDTO getRtiDTO() {
        return rtiDTO;
    }

    public void setChallanDate(String challanDate) {
        this.challanDate = challanDate;
    }

    public String getChallanDate() {
        return challanDate;
    }

    public void setRadiobuttonPayment1(String radiobuttonPayment1) {
        this.radiobuttonPayment1 = radiobuttonPayment1;
    }

    public String getRadiobuttonPayment1() {
        return radiobuttonPayment1;
    }

    public void setChkChallanDel(String chkChallanDel) {
        this.chkChallanDel = chkChallanDel;
    }

    public String getChkChallanDel() {
        return chkChallanDel;
    }

    public void setCashReceiptID(String cashReceiptID) {
        this.cashReceiptID = cashReceiptID;
    }

    public String getCashReceiptID() {
        return cashReceiptID;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
    }

    public String getChallanNo() {
        return challanNo;
    }
}

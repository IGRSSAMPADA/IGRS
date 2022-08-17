package com.wipro.igrs.slotbooking.form;

import com.wipro.igrs.report.dto.PendingCourtCasesDTO;
import com.wipro.igrs.slotbooking.dto.SRReportDTO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SRSlotReportForm extends ActionForm
{

    public SRSlotReportForm()
    {
        penDTO = new PendingCourtCasesDTO();
        reportDTO = new SRReportDTO();
        usageReportList = new ArrayList();
        misReportList = new ArrayList();
        sroURReportList = new ArrayList();
        monthlyRevenueList = new ArrayList();
        cessMunicipalList = new ArrayList();
        mohallaList = new ArrayList();
        districtList = new ArrayList();
        officeTypeLst = new ArrayList();
        officeNameList = new ArrayList();
        tehsilList = new ArrayList();
        areaTypeList = new ArrayList();
        wardList = new ArrayList();
        patwariList = new ArrayList();
        factorList = new ArrayList();
        reportList = new ArrayList();
        regUserIdList = new ArrayList();
        fiscalYearList = new ArrayList();
        droList = new ArrayList();
    }

    public SRReportDTO getReportDTO()
    {
        return reportDTO;
    }

    public void setReportDTO(SRReportDTO reportDTO)
    {
        this.reportDTO = reportDTO;
    }

    public String getExemptionOn()
    {
        return exemptionOn;
    }

    public void setExemptionOn(String exemptionOn)
    {
        this.exemptionOn = exemptionOn;
    }

    public String getDeedDraftId()
    {
        return deedDraftId;
    }

    public void setDeedDraftId(String deedDraftId)
    {
        this.deedDraftId = deedDraftId;
    }

    public String getExemptionName()
    {
        return exemptionName;
    }

    public void setExemptionName(String exemptionName)
    {
        this.exemptionName = exemptionName;
    }

    public String getConsiderationAmount()
    {
        return considerationAmount;
    }

    public void setConsiderationAmount(String considerationAmount)
    {
        this.considerationAmount = considerationAmount;
    }

    public String getDeedName()
    {
        return deedName;
    }

    public void setDeedName(String deedName)
    {
        this.deedName = deedName;
    }

    public String getInstName()
    {
        return instName;
    }

    public void setInstName(String instName)
    {
        this.instName = instName;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getSlotDate()
    {
        return slotDate;
    }

    public void setSlotDate(String slotDate)
    {
        this.slotDate = slotDate;
    }

    public String getSlotTime()
    {
        return slotTime;
    }

    public void setSlotTime(String slotTime)
    {
        this.slotTime = slotTime;
    }

    public String getRegTxnId()
    {
        return regTxnId;
    }

    public void setRegTxnId(String regTxnId)
    {
        this.regTxnId = regTxnId;
    }

    public String getInitBy()
    {
        return initBy;
    }

    public void setInitBy(String initBy)
    {
        this.initBy = initBy;
    }

    public String getRegId()
    {
        return regId;
    }

    public void setRegId(String regId)
    {
        this.regId = regId;
    }

    public String getOfficeId()
    {
        return officeId;
    }

    public void setOfficeId(String officeId)
    {
        this.officeId = officeId;
    }

    public String getOffcMand()
    {
        return offcMand;
    }

    public void setOffcMand(String offcMand)
    {
        this.offcMand = offcMand;
    }

    public String getReportName()
    {
        return reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public String getJrxmlName()
    {
        return jrxmlName;
    }

    public void setJrxmlName(String jrxmlName)
    {
        this.jrxmlName = jrxmlName;
    }

    public PendingCourtCasesDTO getPenDTO()
    {
        return penDTO;
    }

    public void setPenDTO(PendingCourtCasesDTO penDTO)
    {
        this.penDTO = penDTO;
    }

    public String getFinancialYear()
    {
        return financialYear;
    }

    public void setFinancialYear(String financialYear)
    {
        this.financialYear = financialYear;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request)
    {
        super.reset(mapping, request);
    }

    public ArrayList getDistrictList()
    {
        return districtList;
    }

    public void setDistrictList(ArrayList districtList)
    {
        this.districtList = districtList;
    }

    public ArrayList getOfficeTypeLst()
    {
        return officeTypeLst;
    }

    public void setOfficeTypeLst(ArrayList officeTypeLst)
    {
        this.officeTypeLst = officeTypeLst;
    }

    public ArrayList getOfficeNameList()
    {
        return officeNameList;
    }

    public void setOfficeNameList(ArrayList officeNameList)
    {
        this.officeNameList = officeNameList;
    }

    public String getActionID()
    {
        return actionName;
    }

    public void setActionID(String actionName)
    {
        this.actionName = actionName;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(String fromDate)
    {
        this.fromDate = fromDate;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate(String toDate)
    {
        this.toDate = toDate;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public ArrayList getReportList()
    {
        return reportList;
    }

    public void setReportList(ArrayList reportList)
    {
        this.reportList = reportList;
    }

    public String getFormName()
    {
        return formName;
    }

    public void setFormName(String formName)
    {
        this.formName = formName;
    }

    public String getActionName()
    {
        return actionName;
    }

    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

    private static final long serialVersionUID = 1L;
    PendingCourtCasesDTO penDTO;
    SRReportDTO reportDTO;
    private String radioSearch;
    private String regDocSearch;
    private String genderSearch;
    private String fromDate;
    private String toDate;
    private String month;
    private String userType;
    private String actionName;
    private String areaType;
    private String formName;
    private String jrxmlName;
    private String reportName;
    private String offcMand;
    private String officeId;
    private String deedName;
    private String considerationAmount;
    private String exemptionName;
    private String deedDraftId;
    private String exemptionOn;
    private String slotDate;
    private String slotTime;
    private String regTxnId;
    private String initBy;
    private String regId;
    private String instName;
    private ArrayList usageReportList;
    private ArrayList misReportList;
    private ArrayList sroURReportList;
    private ArrayList monthlyRevenueList;
    private ArrayList cessMunicipalList;
    private ArrayList mohallaList;
    private ArrayList districtList;
    private ArrayList officeTypeLst;
    private ArrayList officeNameList;
    private ArrayList tehsilList;
    private ArrayList areaTypeList;
    private ArrayList wardList;
    private ArrayList patwariList;
    private ArrayList factorList;
    private ArrayList reportList;
    private ArrayList regUserIdList;
    private ArrayList fiscalYearList;
    private ArrayList droList;
    private String financialYear;
    private String year;
}
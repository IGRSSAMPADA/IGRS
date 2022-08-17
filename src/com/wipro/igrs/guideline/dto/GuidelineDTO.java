/*
 * 
 * @(#)GuidelineDTO.java	1.0 4th MARCH 2008
 *
 * Copyright 2007-08 WIPRO LIMITED, All rights reserved.
 * WIPRO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * 
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 4th MARCH 2008 
 * MODIFIED ON:	  12th APRIL 2008
 * @DESCRIPTION:  THIS CLASS REPRESENTS THE DATA TRANSFER OBJECTS FOR GUIDELINE PREPARATION 
 */


package com.wipro.igrs.guideline.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author NIHAR M.
 *
 */
public class GuidelineDTO  implements Serializable{
	
    private String districtID;
    private String district;
    private String tehsilID;
    private int tehID;
    private int wardId;
    private int patwariId;
    private int mohallaId;
    private int villageId;
    private ArrayList subAreaList=new ArrayList();
    private int derivedFrom;
    private int hdnStatus;
    private String guideDerivedFrom;
    private boolean newMohalla;
    private String hdnPropTypes;
    private String hdnAppType;
    private boolean modify;
    private String praroopType;
    private boolean guideDraftFinal;
    private int index;
    private String previousPage;
    
    //---added by satbir kumar---####
    
    private String guidelineIdTop;
    private String guidelineIdYearTop;
    private String urbanFlag;
    private String areaFlag;
    private String guidFlag;
    private Double rccVal;
    private Double rbcVal;
    private Double tinVal;
    private Double kabeluVal;
    private String mohallaChkId;
    private String downlaodChkId;
    private String loggedOfficeId;
    private String colorCode;
    private String userType;
    private String DisableBtn;
    private String newTehsilName;
    private String newAreaName;
    private String newSubAreaName;
    private String newWardName;
    private String newMohallaName;
    private String newMohallaID;
    //---end of addition
    
    
    public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
    public boolean isModify() {
		return modify;
	}
	public void setModify(boolean modify) {
		this.modify = modify;
	}
	public String getHdnAppType() {
		return hdnAppType;
	}
	public void setHdnAppType(String hdnAppType) {
		this.hdnAppType = hdnAppType;
	}
	public boolean isNewMohalla() {
		return newMohalla;
	}
	public void setNewMohalla(boolean newMohalla) {
		this.newMohalla = newMohalla;
	}
	public String getGuideDerivedFrom() {
		return guideDerivedFrom;
	}
	public void setGuideDerivedFrom(String guideDerivedFrom) {
		this.guideDerivedFrom = guideDerivedFrom;
	}
	public int getDerivedFrom() {
		return derivedFrom;
	}
	public void setDerivedFrom(int derivedFrom) {
		this.derivedFrom = derivedFrom;
	}
	public int getVillageId() {
		return villageId;
	}
	public void setVillageId(int villageId) {
		this.villageId = villageId;
	}
	public int getMohallaId() {
		return mohallaId;
	}
	public void setMohallaId(int mohallaId) {
		this.mohallaId = mohallaId;
	}
	public int getPatwariId() {
		return patwariId;
	}
	public void setPatwariId(int patwariId) {
		this.patwariId = patwariId;
	}
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	public int getTehID() {
		return tehID;
	}
	public void setTehID(int tehID) {
		this.tehID = tehID;
	}
	private String tehsil;
    private String hdnDistrict;
    private String hdnTehsil;
    private String hdnWard;
    private String hdnPatwari;
    private String wardID;
    private String ward;
    private String radioRuralUrban;
    private String listPropertyType;
    private String durationFrom;
    private String durationTo;
    private String selecttehsilid;
    private String selectdistrictid;
    private String guideLineID;
    private long guideID;
    private long guideIdOld;
    private String guideType;
    private String guideSt;
    private int guideStatus;
    
	public String getGuideSt() {
		return guideSt;
	}
	public void setGuideSt(String guideSt) {
		this.guideSt = guideSt;
	}
	private String derived;
    
	
	public String getDerived() {
		return derived;
	}
	public void setDerived(String derived) {
		this.derived = derived;
	}
	public int getGuideStatus() {
		return guideStatus;
	}
	public void setGuideStatus(int guideStatus) {
		this.guideStatus = guideStatus;
	}
	public String getGuideType() {
		return guideType;
	}
	public void setGuideType(String guideType) {
		this.guideType = guideType;
	}
	public long getGuideIdOld() {
		return guideIdOld;
	}
	public void setGuideIdOld(long guideIdOld) {
		this.guideIdOld = guideIdOld;
	}
	private String hdnMohalla;
    private String errorFlag;
    private String selectedGuideLineID;
    private String hdnGuideDuration;
    
    public long getGuideID() {
		return guideID;
	}
	public void setGuideID(long guideID) {
		this.guideID = guideID;
	}
	public String getHdnGuideDuration() {
		return hdnGuideDuration;
	}
	public void setHdnGuideDuration(String hdnGuideDuration) {
		this.hdnGuideDuration = hdnGuideDuration;
	}
	public String getSelectedGuideLineID() {
		return selectedGuideLineID;
	}
	public void setSelectedGuideLineID(String selectedGuideLineID) {
		this.selectedGuideLineID = selectedGuideLineID;
	}
	private ArrayList errorList = new ArrayList();
    
    private String areaTypeID;
    private String areaName;
    private String wardPatwari = "";
    private String wardPatwariID;
    
    private String mohalla;
    private String mohallaID;
    
    private String guidelineAction;
    private String guidelinePreparationForm;
    private String guidelineCreationForm;
    
    public String getGuidelineCreationForm() {
		return guidelineCreationForm;
	}
	public void setGuidelineCreationForm(String guidelineCreationForm) {
		this.guidelineCreationForm = guidelineCreationForm;
	}
	private String guidelineTehsilAction;
    private String guidelineWardAction;
    private String guidelineMohallaAction;
    
    private String actionName;
    private String fromDepositeDate;
    private String toDepositeDate;
    private String basePeriodFrom;
    private String basePeriodTo;
    private String createdDate;
    private String createdBy;
    private String updatedDate;
    private String updatedBy;
    private String incrementon;
    private ArrayList mohList;
    private String moh;
    private String sideSubId;
    private String sideSubName;
    
    private String resetPageAction;
    private String mohalaDisplayForm;
    private String mohallaDetailsReadableMode;
    private String guidelineViewForm;
    private String guidelineApprovalForm;
    
    private String patwariID;
    private String patwari;
    private String villageID;
    private String village;
    private String propertyTypeName;
    private String L1Name;
    private String L2Name;
    private String propertyID;
    private String L1_ID;
    private String L2_ID;
    private String ratePerSqm;
    private String financialYear;
    private String financialYearID; 
    private String hdnFinancialYear;
    private String guidelineStatus;
    private String uomID;
    private String uomName;
    private String verId;
    private String guideTypeChk;
    private String subClauseChkBox;
    private String gotTypeChk;
	private String propDetails;
    
  //added by Shreeraj
	 private String subAreaID;
	// private String subAreaName;
	 private String wardMappingID;
	 private String colonyMappingID;
	
	 
	 
	 public String getColonyMappingID() {
		return colonyMappingID;
	}
	public void setColonyMappingID(String colonyMappingID) {
		this.colonyMappingID = colonyMappingID;
	}
	public String getWardMappingID() {
		return wardMappingID;
	}
	public void setWardMappingID(String wardMappingID) {
		this.wardMappingID = wardMappingID;
	}
	public String getSubAreaID() {
		return subAreaID;
	}
	public void setSubAreaID(String subAreaID) {
		this.subAreaID = subAreaID;
	}
	/*public String getSubAreaName() {
		return subAreaName;
	}
	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}*/
	public String getSubClauseChkBox() {
		return subClauseChkBox;
	}
	public void setSubClauseChkBox(String subClauseChkBox) {
		this.subClauseChkBox = subClauseChkBox;
	}
	public String getPropDetails() {
			return propDetails;
		}
		public void setPropDetails(String propDetails) {
			this.propDetails = propDetails;
		}
    
    
   
	public String getVerId() {
		return verId;
	}
	public void setVerId(String verId) {
		this.verId = verId;
	}
	public String getUomID() {
		return uomID;
	}
	public void setUomID(String uomID) {
		this.uomID = uomID;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	/*public int getGuidelineStatus() {
		return guidelineStatus;
	}
	public void setGuidelineStatus(int guidelineStatus) {
		this.guidelineStatus = guidelineStatus;
	}*/
	public String getHdnFinancialYear() {
		return hdnFinancialYear;
	}
	public void setHdnFinancialYear(String hdnFinancialYear) {
		this.hdnFinancialYear = hdnFinancialYear;
	}
	private String unitMeasureID;
    public String getFinancialYearID() {
		return financialYearID;
	}
	public void setFinancialYearID(String financialYearID) {
		this.financialYearID = financialYearID;
	}
	private String status;
	private String statusChecker;
	private String chkStatus;
    
    public String getStatusChecker() {
		return statusChecker;
	}
	public void setStatusChecker(String statusChecker) {
		this.statusChecker = statusChecker;
	}
	private String guideLineValue;
   // private int proposedValue ;
	//private double proposedValue;
	//private long proposedValue;
	//private float proposedValue;
	private BigDecimal proposedValue;
    private double incrementValue;
    private String averageValue;
    private String radioValue;
    private String mohallaReadOnlyApprovalForm;
    private String mohallaModifyApprovalForm;
    private String hdnArea;
    private String page;
    private String listType;
    private int approvalType;
    private String approvalTypeDisp;
    /**
     * @return averageValue
     */
    public String getAverageValue() {
		return averageValue;
	}
    
    
	
	public BigDecimal getProposedValue() {
		return proposedValue;
	}
	public void setProposedValue(BigDecimal proposedValue) {
		this.proposedValue = proposedValue;
	}
	/*public float getProposedValue() {
		return proposedValue;
	}
	public void setProposedValue(float proposedValue) {
		this.proposedValue = proposedValue;
	}*/
	/*public long getProposedValue() {
		return proposedValue;
	}
	public void setProposedValue(long proposedValue) {
		this.proposedValue = proposedValue;
	}*/
	/**
	 * @param averageValue
	 */
	public void setAverageValue(String averageValue) {
		this.averageValue = averageValue;
	}
	/**
	 * @return radioValue
	 */
	public String getRadioValue() {
		return radioValue;
	}
	/**
	 * @param radioValue
	 */
	public void setRadioValue(String radioValue) {
		this.radioValue = radioValue;
	}
	
	/**
	 * CONSTRUCTOR
	 */
	public GuidelineDTO() {
    	proposedValue = BigDecimal.valueOf(0.0);
    	incrementValue = 0;
    	
    }
    /**
     * @return proposedValue
     */
   // public int getProposedValue() {
		//return proposedValue;
	//}

	/**
	 * @param proposedValue
	 */
	//public void setProposedValue(int proposedValue) {
		//this.proposedValue = proposedValue;
	//}

	/**
	 * @return propertyTypeName
	 */
	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	/**
	 * @param propertyTypeName
	 */
	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}

	/**
	 * @return L1Name
	 */
	public String getL1Name() {
		return L1Name;
	}

	/**
	 * @param name
	 */
	public void setL1Name(String name) {
		L1Name = name;
	}

	/**
	 * @return L2Name
	 */
	public String getL2Name() {
		return L2Name;
	}

	/**
	 * @param name
	 */
	public void setL2Name(String name) {
		L2Name = name;
	}

	/**
	 * @param district
	 */
	public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @return mohalla
     */
    public String getMohalla() {
		return mohalla;
	}

	/**
	 * @param mohalla
	 */
	public void setMohalla(String mohalla) {
		this.mohalla = mohalla;
	}

	/**
	 * @return mohallaID
	 */
	public String getMohallaID() {
		return mohallaID;
	}

	/**
	 * @param mohallaID
	 */
	public void setMohallaID(String mohallaID) {
		this.mohallaID = mohallaID;
	}

	/**
	 * @param tehsil
	 */
	public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    /**
     * @return tehsil
     */
    public String getTehsil() {
        return tehsil;
    }

    /**
     * @param radioRuralUrban
     */
    public void setRadioRuralUrban(String radioRuralUrban) {
        this.radioRuralUrban = radioRuralUrban;
    }

    /**
     * @return radioRuralUrban
     */
    public String getRadioRuralUrban() {
        return radioRuralUrban;
    }

    /**
     * @param listPropertyType
     */
    public void setListPropertyType(String listPropertyType) {
        this.listPropertyType = listPropertyType;
    }

    /**
     * @return listPropertyType
     */
    public String getListPropertyType() {
        return listPropertyType;
    }

    /**
     * @param durationFrom
     */
    public void setDurationFrom(String durationFrom) {
        this.durationFrom = durationFrom;
    }

    /**
     * @return durationFrom
     */
    public String getDurationFrom() {
        return durationFrom;
    }

    /**
     * @param durationTo
     */
    public void setDurationTo(String durationTo) {
        this.durationTo = durationTo;
    }

    /**
     * @return durationTo
     */
    public String getDurationTo() {
        return durationTo;
    }

    
   
	/**
     * @param tehsilID
     */
    public void setTehsilID(String tehsilID) {
        this.tehsilID = tehsilID;
    }

    /**
     * @return tehsilID
     */
    public String getTehsilID() {
        return tehsilID;
    }

    /**
     * @param selecttehsilid
     */
    public void setSelecttehsilid(String selecttehsilid) {
        this.selecttehsilid = selecttehsilid;
    }

    /**
     * @return selecttehsilid
     */
    public String getSelecttehsilid() {
        return selecttehsilid;
    }

    /**
     * @param selectdistrictid
     */
    public void setSelectdistrictid(String selectdistrictid) {
        this.selectdistrictid = selectdistrictid;
    }

    /**
     * @return selectdistrictid
     */
    public String getSelectdistrictid() {
        return selectdistrictid;
    }

    /**
     * @param guidelineAction
     */
    public void setGuidelineAction(String guidelineAction) {
        this.guidelineAction = guidelineAction;
    }

    /**
     * @return guidelineAction
     */
    public String getGuidelineAction() {
        return guidelineAction;
    }

    /**
     * @param guidelinePreparationForm
     */
    public void setGuidelinePreparationForm(String guidelinePreparationForm) {
        this.guidelinePreparationForm = guidelinePreparationForm;
    }

    /**
     * @return guidelinePreparationForm
     */
    public String getGuidelinePreparationForm() {
        return guidelinePreparationForm;
    }

	/**
	 * @return guidelineTehsilAction
	 */
	public String getGuidelineTehsilAction() {
		return guidelineTehsilAction;
	}

	/**
	 * @param guidelineTehsilAction
	 */
	public void setGuidelineTehsilAction(String guidelineTehsilAction) {
		this.guidelineTehsilAction = guidelineTehsilAction;
	}

	/**
	 * @return ward
	 */
	public String getWard() {
		return ward;
	}

	/**
	 * @param ward
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}

	/**
	 * @return wardID
	 */
	public String getWardID() {
		return wardID;
	}

	/**
	 * @param wardID
	 */
	public void setWardID(String wardID) {
		this.wardID = wardID;
	}

	/**
	 * @return guidelineWardAction
	 */
	public String getGuidelineWardAction() {
		return guidelineWardAction;
	}

	/**
	 * @param guidelineWardAction
	 */
	public void setGuidelineWardAction(String guidelineWardAction) {
		this.guidelineWardAction = guidelineWardAction;
	}

	/**
	 * @return guidelineMohallaAction
	 */
	public String getGuidelineMohallaAction() {
		return guidelineMohallaAction;
	}

	/**
	 * @param guidelineMohallaAction
	 */
	public void setGuidelineMohallaAction(String guidelineMohallaAction) {
		this.guidelineMohallaAction = guidelineMohallaAction;
	}

	/**
	 * @return hdnTehsil
	 */
	public String getHdnTehsil() {
		return hdnTehsil;
	}

	/**
	 * @param hdnTehsil
	 */
	public void setHdnTehsil(String hdnTehsil) {
		this.hdnTehsil = hdnTehsil;
	}

	/**
	 * @return hdnWard
	 */
	public String getHdnWard() {
		return hdnWard;
	}

	/**
	 * @param hdnWard
	 */
	public void setHdnWard(String hdnWard) {
		this.hdnWard = hdnWard;
	}

	/**
	 * @return hdnDistrict
	 */
	public String getHdnDistrict() {
		return hdnDistrict;
	}

	/**
	 * @param hdnDistrict
	 */
	public void setHdnDistrict(String hdnDistrict) {
		this.hdnDistrict = hdnDistrict;
	}

	/**
	 * @return fromDepositeDate
	 */
	public String getFromDepositeDate() {
		return fromDepositeDate;
	}

	/**
	 * @param fromDepositeDate
	 */
	public void setFromDepositeDate(String fromDepositeDate) {
		this.fromDepositeDate = fromDepositeDate;
	}

	/**
	 * @return toDepositeDate
	 */
	public String getToDepositeDate() {
		return toDepositeDate;
	}

	/**
	 * @param toDepositeDate
	 */
	public void setToDepositeDate(String toDepositeDate) {
		this.toDepositeDate = toDepositeDate;
	}

	/**
	 * @return actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return resetPageAction
	 */
	public String getResetPageAction() {
		return resetPageAction;
	}

	/**
	 * @param resetPageAction
	 */
	public void setResetPageAction(String resetPageAction) {
		this.resetPageAction = resetPageAction;
	}

	/**
	 * @return mohalaDisplayForm
	 */
	public String getMohalaDisplayForm() {
		return mohalaDisplayForm;
	}

	/**
	 * @param mohalaDisplayForm
	 */
	public void setMohalaDisplayForm(String mohalaDisplayForm) {
		this.mohalaDisplayForm = mohalaDisplayForm;
	}

	/**
	 * @return areaTypeID
	 */
	public String getAreaTypeID() {
		return areaTypeID;
	}

	/**
	 * @param areaTypeID
	 */
	public void setAreaTypeID(String areaTypeID) {
		this.areaTypeID = areaTypeID;
	}

	/**
	 * @return areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return wardPatwari
	 */
	public String getWardPatwari() {
		return wardPatwari;
	}

	/**
	 * @param wardPatwari
	 */
	public void setWardPatwari(String wardPatwari) {
		this.wardPatwari = wardPatwari;
	}

	/**
	 * @return wardPatwariID
	 */
	public String getWardPatwariID() {
		return wardPatwariID;
	}

	/**
	 * @param wardPatwariID
	 */
	public void setWardPatwariID(String wardPatwariID) {
		this.wardPatwariID = wardPatwariID;
	}

	/**
	 * @return patwariID
	 */
	public String getPatwariID() {
		return patwariID;
	}

	/**
	 * @param patwariID
	 */
	public void setPatwariID(String patwariID) {
		this.patwariID = patwariID;
	}

	/**
	 * @return patwari
	 */
	public String getPatwari() {
		return patwari;
	}

	/**
	 * @param patwari
	 */
	public void setPatwari(String patwari) {
		this.patwari = patwari;
	}

	/**
	 * @return villageID
	 */
	public String getVillageID() {
		return villageID;
	}

	/**
	 * @param villageID
	 */
	public void setVillageID(String villageID) {
		this.villageID = villageID;
	}

	/**
	 * @return village
	 */
	public String getVillage() {
		return village;
	}

	/**
	 * @param village
	 */
	public void setVillage(String village) {
		this.village = village;
	}

	/**
	 * @return hdnPatwari
	 */
	public String getHdnPatwari() {
		return hdnPatwari;
	}

	/**
	 * @param hdnPatwari
	 */
	public void setHdnPatwari(String hdnPatwari) {
		this.hdnPatwari = hdnPatwari;
	}

	/**
	 * @return guideLineValue
	 */
	public String getGuideLineValue() {
		return guideLineValue;
	}

	/**
	 * @param guideLineValue
	 */
	public void setGuideLineValue(String guideLineValue) {
		this.guideLineValue = guideLineValue;
	}

	/**
	 * @return L1_ID
	 */
	public String getL1_ID() {
		return L1_ID;
	}

	/**
	 * @param l1_id
	 */
	public void setL1_ID(String l1_id) {
		L1_ID = l1_id;
	}

	/**
	 * @return L2_ID
	 */
	public String getL2_ID() {
		return L2_ID;
	}

	/**
	 * @param l2_id
	 */
	public void setL2_ID(String l2_id) {
		L2_ID = l2_id;
	}

	/**
	 * @return ratePerSqm
	 */
	public String getRatePerSqm() {
		return ratePerSqm;
	}

	/**
	 * @param ratePerSqm
	 */
	public void setRatePerSqm(String ratePerSqm) {
		this.ratePerSqm = ratePerSqm;
	}

	/**
	 * @return financialYear
	 */
	public String getFinancialYear() {
		return financialYear;
	}

	/**
	 * @param financialYear
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return status
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
	 * @param proposedValue
	 */
	//public GuidelineDTO(int proposedValue) {
		//super();
		//this.proposedValue = proposedValue;
	//}
	public GuidelineDTO(BigDecimal proposedValue) {
		super();
		this.proposedValue = proposedValue;
	}
	
	/**
	 * @return mohallaDetailsReadableMode
	 */
	public String getMohallaDetailsReadableMode() {
		return mohallaDetailsReadableMode;
	}
	/**
	 * @param mohallaDetailsReadableMode
	 */
	public void setMohallaDetailsReadableMode(String mohallaDetailsReadableMode) {
		this.mohallaDetailsReadableMode = mohallaDetailsReadableMode;
	}
	
	/**
	 * @return propertyID
	 */
	public String getPropertyID() {
		return propertyID;
	}
	
	/**
	 * @param propertyID
	 */
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	
	/**
	 * @return basePeriodFrom
	 */
	public String getBasePeriodFrom() {
		return basePeriodFrom;
	}
	
	/**
	 * @param basePeriodFrom
	 */
	public void setBasePeriodFrom(String basePeriodFrom) {
		this.basePeriodFrom = basePeriodFrom;
	}
	
	/**
	 * @return basePeriodTo
	 */
	public String getBasePeriodTo() {
		return basePeriodTo;
	}
	
	/**
	 * @param basePeriodTo
	 */
	public void setBasePeriodTo(String basePeriodTo) {
		this.basePeriodTo = basePeriodTo;
	}
	
	/**
	 * @return guidelineViewForm
	 */
	public String getGuidelineViewForm() {
		return guidelineViewForm;
	}

	/**
	 * @param guidelineViewForm
	 */
	public void setGuidelineViewForm(String guidelineViewForm) {
		this.guidelineViewForm = guidelineViewForm;
	}
	
	/**
	 * @return unitMeasureID
	 */
	public String getUnitMeasureID() {
		return unitMeasureID;
	}
	
	/**
	 * @param unitMeasureID
	 */
	public void setUnitMeasureID(String unitMeasureID) {
		this.unitMeasureID = unitMeasureID;
	}
	/**
	 * @return guidelineApprovalForm
	 */
	public String getGuidelineApprovalForm() {
		return guidelineApprovalForm;
	}
	/**
	 * @param guidelineApprovalForm
	 */
	public void setGuidelineApprovalForm(String guidelineApprovalForm) {
		this.guidelineApprovalForm = guidelineApprovalForm;
	}
	/**
	 * @return guideLineID
	 */
	public String getGuideLineID() {
		return guideLineID;
	}
	/**
	 * @param guideLineID
	 */
	public void setGuideLineID(String guideLineID) {
		this.guideLineID = guideLineID;
	}
	/**
	 * @return createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return createdBy
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
	/**
	 * @return updatedDate
	 */
	public String getUpdatedDate() {
		return updatedDate;
	}
	/**
	 * @param updatedDate
	 */
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	/**
	 * @return updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return incrementon
	 */
	public String getIncrementon() {
		return incrementon;
	}
	/**
	 * @param incrementon
	 */
	public void setIncrementon(String incrementon) {
		this.incrementon = incrementon;
	}
	/**
	 * @return mohallaReadOnlyApprovalForm
	 */
	public String getMohallaReadOnlyApprovalForm() {
		return mohallaReadOnlyApprovalForm;
	}
	/**
	 * @param mohallaReadOnlyApprovalForm
	 */
	public void setMohallaReadOnlyApprovalForm(String mohallaReadOnlyApprovalForm) {
		this.mohallaReadOnlyApprovalForm = mohallaReadOnlyApprovalForm;
	}
	/**
	 * @return mohallaModifyApprovalForm
	 */
	public String getMohallaModifyApprovalForm() {
		return mohallaModifyApprovalForm;
	}
	/**
	 * @param mohallaModifyApprovalForm
	 */
	public void setMohallaModifyApprovalForm(String mohallaModifyApprovalForm) {
		this.mohallaModifyApprovalForm = mohallaModifyApprovalForm;
	}
	/**
	 * @return hdnMohalla
	 */
	public String getHdnMohalla() {
		return hdnMohalla;
	}
	/**
	 * @param hdnMohalla
	 */
	public void setHdnMohalla(String hdnMohalla) {
		this.hdnMohalla = hdnMohalla;
	}
	/**
	 * @return String
	 */
	public String getHdnArea() {
		return hdnArea;
	}
	/**
	 * @param hdnArea
	 */
	public void setHdnArea(String hdnArea) {
		this.hdnArea = hdnArea;
	}
	/*public String getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}*/
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	public ArrayList getErrorList() {
		return errorList;
	}
	public void setErrorList(ArrayList errorList) {
		this.errorList = errorList;
	}
	public String getMoh() {
		return moh;
	}
	public void setMoh(String moh) {
		this.moh = moh;
	}
	public ArrayList getMohList() {
		return mohList;
	}
	public void setMohList(ArrayList mohList) {
		this.mohList = mohList;
	}
	public int getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(int approvalType) {
		this.approvalType = approvalType;
	}
	public int getHdnStatus() {
		return hdnStatus;
	}
	public void setHdnStatus(int hdnStatus) {
		this.hdnStatus = hdnStatus;
	}
	public String getGuidelineStatus() {
		return guidelineStatus;
	}
	public void setGuidelineStatus(String guidelineStatus) {
		this.guidelineStatus = guidelineStatus;
	}
	public String getHdnPropTypes() {
		return hdnPropTypes;
	}
	public void setHdnPropTypes(String hdnPropTypes) {
		this.hdnPropTypes = hdnPropTypes;
	}
	public String getApprovalTypeDisp() {
		return approvalTypeDisp;
	}
	public void setApprovalTypeDisp(String approvalTypeDisp) {
		this.approvalTypeDisp = approvalTypeDisp;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	
/*
 * SUBCLAUSES	
 */
	
	
	
	private String subClauseId;
	private String subClauseName;
	private String subName;
	private String subClauseSelected;
	private String mapped;
	
	private String[] subClauseIds;
	private String propId;
	private String propId1;
	private String propId2;
	
	
	/**
	 * 
	 * @return
	 */
	public String getSubClauseId() {
		return subClauseId;
	}
	
	/**
	 * 
	 * @param subClauseId
	 */
	public void setSubClauseId(String subClauseId) {
		this.subClauseId = subClauseId;
	}
	
	/**
	 * 
	 * @return subClauseName
	 */
	public String getSubClauseName() {
		return subClauseName;
	}
	
	/**
	 * 
	 * @param subClauseName
	 */
	public void setSubClauseName(String subClauseName) {
		this.subClauseName = subClauseName;
	}
	/**
	 * 
	 * @return subClauseIds
	 */
	public String[] getSubClauseIds() {
		return subClauseIds;
	}
	/**
	 * 
	 * @param subClauseIds
	 */
	public void setSubClauseIds(String[] subClauseIds) {
		this.subClauseIds = subClauseIds;
	}
	
	/**
	 * 
	 * @return String
	 */
	public String getMapped() {
		return mapped;
	}
	/**
	 * 
	 * @param mapped
	 */
	public void setMapped(String mapped) {
		this.mapped = mapped;
	}
	/**
	 * 
	 * @return String
	 */
	public String getPropId() {
		return propId;
	}
	/**
	 * 
	 * @param propId
	 */
	public void setPropId(String propId) {
		this.propId = propId;
	}
	/**
	 * 
	 * @return String
	 */
	public String getPropId1() {
		return propId1;
	}
	/**
	 * 
	 * @param propId1
	 */
	public void setPropId1(String propId1) {
		this.propId1 = propId1;
	}
	/**
	 * 
	 * @return String
	 */
	public String getPropId2() {
		return propId2;
	}
	/**
	 * 
	 * @param propId2
	 */
	public void setPropId2(String propId2) {
		this.propId2 = propId2;
	}
	
	
	private HashMap hMap = new HashMap();
	
	/**
	 * 
	 * @return HashMap
	 */
	public HashMap getHMap() {
		return hMap;
	}
	/**
	 * 
	 * @param map
	 */
	public void setHMap(HashMap map) {
		hMap = map;
	}
	/**
	 * 
	 * @return String
	 */
	public String getSubClauseSelected() {
		return subClauseSelected;
	}
	
	/**
	 * 
	 * @param subClauseSelected
	 */
	public void setSubClauseSelected(String subClauseSelected) {
		this.subClauseSelected = subClauseSelected;
	}
	
	/**
	 * @return subName;
	 */
	public String getSubName() {
		return subName;
	}
	
	/**
	 * 
	 * @param subName
	 */
	public void setSubName(String subName) {
		this.subName = subName;
	}
	
private String checkDraft;	// Added by Lavi for Draft Feedback
private String checkModify; // Added by Simran 
private String finalGuidelineDetls;
private String guidelineFinalValue;
private String finalGuideline;
private String finanYearFinalGuideline;
private String firstCheck;
    
    public String getCheckDraft() {
		return checkDraft;
	}
	public void setCheckDraft(String checkDraft) {
		this.checkDraft = checkDraft;
	}
	
private String mailContent;

public String getMailContent() {
	return mailContent;
}
public void setMailContent(String mailContent) {
	this.mailContent = mailContent;
}

public String user_id;

public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getCheckModify() {
	return checkModify;
}
public void setCheckModify(String checkModify) {
	this.checkModify = checkModify;
}
public String getPraroopType() {
	return praroopType;
}
public void setPraroopType(String praroopType) {
	this.praroopType = praroopType;
}

private String check;
private String praroopTypeId;

public String getCheck() {
	return check;
}
public void setCheck(String check) {
	this.check = check;
}
public String getPraroopTypeId() {
	return praroopTypeId;
}
public void setPraroopTypeId(String praroopTypeId) {
	this.praroopTypeId = praroopTypeId;
}
public String getFinalGuidelineDetls() {
	return finalGuidelineDetls;
}
public void setFinalGuidelineDetls(String finalGuidelineDetls) {
	this.finalGuidelineDetls = finalGuidelineDetls;
}
public String getGuidelineFinalValue() {
	return guidelineFinalValue;
}
public void setGuidelineFinalValue(String guidelineFinalValue) {
	this.guidelineFinalValue = guidelineFinalValue;
}
public String getFinalGuideline() {
	return finalGuideline;
}
public void setFinalGuideline(String finalGuideline) {
	this.finalGuideline = finalGuideline;
}
public String getFinanYearFinalGuideline() {
	return finanYearFinalGuideline;
}
public void setFinanYearFinalGuideline(String finanYearFinalGuideline) {
	this.finanYearFinalGuideline = finanYearFinalGuideline;
}
public boolean isGuideDraftFinal() {
	return guideDraftFinal;
}
public void setGuideDraftFinal(boolean guideDraftFinal) {
	this.guideDraftFinal = guideDraftFinal;
}
public int getIndex() {
	return index;
}
public void setIndex(int index) {
	this.index = index;
}
public double getIncrementValue() {
	return incrementValue;
}
public void setIncrementValue(double incrementValue) {
	this.incrementValue = incrementValue;
}
public String getPreviousPage() {
	return previousPage;
}
public void setPreviousPage(String previousPage) {
	this.previousPage = previousPage;
}


//*******************for hindi**************************//
private String confirmationChk;


public String getChkStatus() {
	return chkStatus;
}
public void setChkStatus(String chkStatus) {
	this.chkStatus = chkStatus;
}
private String language;

public String getLanguage() {
	return language;
}
public void setLanguage(String language) {
	this.language = language;
}
public String getConfirmationChk() {
	return confirmationChk;
}
public void setConfirmationChk(String confirmationChk) {
	this.confirmationChk = confirmationChk;
}


// Added by SIMRAN - 19 march

private String subAreaId;
private String subAreaName;
private String computationLogic;
private String subPropId;
private String propL1Pv;

public String getSubAreaId() {
	return subAreaId;
}
public void setSubAreaId(String subAreaId) {
	this.subAreaId = subAreaId;
}

public String getSubAreaName() {
	return subAreaName;
}
public void setSubAreaName(String subAreaName) {
	this.subAreaName = subAreaName;
}
public String getComputationLogic() {
	return computationLogic;
}
public void setComputationLogic(String computationLogic) {
	this.computationLogic = computationLogic;
}
public String getFirstCheck() {
	return firstCheck;
}
public void setFirstCheck(String firstCheck) {
	this.firstCheck = firstCheck;
}
public String getSubPropId() {
	return subPropId;
}
public void setSubPropId(String subPropId) {
	this.subPropId = subPropId;
}
public String getPropL1Pv() {
	return propL1Pv;
}
public void setPropL1Pv(String propL1Pv) {
	this.propL1Pv = propL1Pv;
}
public void setGuidelineIdTop(String guidelineIdTop) {
	this.guidelineIdTop = guidelineIdTop;
}
public String getGuidelineIdTop() {
	return guidelineIdTop;
}
public void setGuidelineIdYearTop(String guidelineIdYearTop) {
	this.guidelineIdYearTop = guidelineIdYearTop;
}
public String getGuidelineIdYearTop() {
	return guidelineIdYearTop;
}
public void setSubAreaList(ArrayList subAreaList) {
	this.subAreaList = subAreaList;
}
public ArrayList getSubAreaList() {
	return subAreaList;
}
public void setSideSubId(String sideSubId) {
	this.sideSubId = sideSubId;
}
public String getSideSubId() {
	return sideSubId;
}
public void setSideSubName(String sideSubName) {
	this.sideSubName = sideSubName;
}
public String getSideSubName() {
	return sideSubName;
}
public void setUrbanFlag(String urbanFlag) {
	this.urbanFlag = urbanFlag;
}
public String getUrbanFlag() {
	return urbanFlag;
}
public void setAreaFlag(String areaFlag) {
	this.areaFlag = areaFlag;
}
public String getAreaFlag() {
	return areaFlag;
}
public void setGuidFlag(String guidFlag) {
	this.guidFlag = guidFlag;
}
public String getGuidFlag() {
	return guidFlag;
}
public void setRccVal(Double rccVal) {
	this.rccVal = rccVal;
}
public Double getRccVal() {
	return rccVal;
}
public void setRbcVal(Double rbcVal) {
	this.rbcVal = rbcVal;
}
public Double getRbcVal() {
	return rbcVal;
}
public void setTinVal(Double tinVal) {
	this.tinVal = tinVal;
}
public Double getTinVal() {
	return tinVal;
}
public void setKabeluVal(Double kabeluVal) {
	this.kabeluVal = kabeluVal;
}
public Double getKabeluVal() {
	return kabeluVal;
}
public void setMohallaChkId(String mohallaChkId) {
	this.mohallaChkId = mohallaChkId;
}
public String getMohallaChkId() {
	return mohallaChkId;
}
public void setDownlaodChkId(String downlaodChkId) {
	this.downlaodChkId = downlaodChkId;
}
public String getDownlaodChkId() {
	return downlaodChkId;
}
public void setLoggedOfficeId(String loggedOfficeId) {
	this.loggedOfficeId = loggedOfficeId;
}
public String getLoggedOfficeId() {
	return loggedOfficeId;
}
public void setColorCode(String colorCode) {
	this.colorCode = colorCode;
}
public String getColorCode() {
	return colorCode;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public String getUserType() {
	return userType;
}
public void setDisableBtn(String disableBtn) {
	DisableBtn = disableBtn;
}
public String getDisableBtn() {
	return DisableBtn;
}
public void setNewMohallaName(String newMohallaName) {
	this.newMohallaName = newMohallaName;
}
public String getNewMohallaName() {
	return newMohallaName;
}
public void setNewTehsilName(String newTehsilName) {
	this.newTehsilName = newTehsilName;
}
public String getNewTehsilName() {
	return newTehsilName;
}
public void setNewAreaName(String newAreaName) {
	this.newAreaName = newAreaName;
}
public String getNewAreaName() {
	return newAreaName;
}
public void setNewSubAreaName(String newSubAreaName) {
	this.newSubAreaName = newSubAreaName;
}
public String getNewSubAreaName() {
	return newSubAreaName;
}
public void setNewWardName(String newWardName) {
	this.newWardName = newWardName;
}
public String getNewWardName() {
	return newWardName;
}
public void setNewMohallaID(String newMohallaID) {
	this.newMohallaID = newMohallaID;
}
public String getNewMohallaID() {
	return newMohallaID;
}
public void setGuideTypeChk(String guideTypeChk) {
	this.guideTypeChk = guideTypeChk;
}
public String getGuideTypeChk() {
	return guideTypeChk;
}
public void setGotTypeChk(String gotTypeChk) {
	this.gotTypeChk = gotTypeChk;
}
public String getGotTypeChk() {
	return gotTypeChk;
}
public void setListType(String listType) {
	this.listType = listType;
}
public String getListType() {
	return listType;
}






}

	
	



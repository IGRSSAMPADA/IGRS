package com.wipro.igrs.report.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MISReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromDate;
	private String toDate;
	private String month;
	private String year;
	private String month1;
	private String month2;
	private String year1;
	private String year2;
	private String finYear2;
	private String finYear1;
   
	public String getFinYear2() {
		return finYear2;
	}

	public void setFinYear2(String finYear2) {
		this.finYear2 = finYear2;
	}

	public String getFinYear1() {
		return finYear1;
	}

	public void setFinYear1(String finYear1) {
		this.finYear1 = finYear1;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	private String functionality;
	private String noTimeAccessed;
	private String accessTime;

	private String searchType;
	private String userType;

	private String userDate;
	private String userID;
	private String userName;
	private String searchDocumentDetails;
	private String searchPropertyOwnerName;
	private String searchCount;
	private String searchTotal;

	private String revenueRuralCur;
	private String revenueUrbanCur;
	private String revenueCurTotal;
	private String revenueRuralPrev;
	private String revenueUrbanPrev;
	private String revenuePrevTotal;
	private String revenueRuralPer;
	private String revenueUrbanPer;
	private String revenuePerTotal;

	private String noOfDocRuralCur;
	private String noOfDocUrbanCur;
	private String noOfDocCurTotal;
	private String noOfDocRuralPrev;
	private String noOfDocUrbanPrev;
	private String noOfDocPrevTotal;
	private String question;

	private String accessdate; // --added by satbir kumar----

	    private String colonyId;
		public String getColonyId() {
			return colonyId;
		}

		public void setColonyId(String colonyId) {
			this.colonyId = colonyId;
		}

		public String getColonyName() {
			return colonyName;
		}

		public void setColonyName(String colonyName) {
			this.colonyName = colonyName;
		}

		private String colonyName;
	
	private ArrayList zoneList = new ArrayList(); //
	private String tehsilId;
	
	   public String getWardIds() {
		return wardIds;
	}

	public void setWardIds(String wardIds) {
		this.wardIds = wardIds;
	}

	public String getWardPatwariName() {
		return wardPatwariName;
	}

	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}

	private String wardIds;
	   private String wardPatwariName;

	private String districtID;
	private String districtName;
	private String tehsilName;
	private String totTehsilName;
	private String revenueApril;
	private String revenueMay;
	private String revenueJune;
	private String revenueJuly;
	private String revenueAug;
	private String revenueSept;
	private String revenueOct;
	private String revenueNov;
	private String revenueDec;
	private String revenueJan;
	private String revenueFeb;
	private String revenueMar;

	private String nameOfOrg;
	private String noOfDocOrg;
	private String revenueReceiptOrg;
	private String revenueTotalOrg;
	private String YearValue;
	// added by ShreeraJ
	private String date;
	private ArrayList subReportList = new ArrayList();
	private ArrayList subReportList1 = new ArrayList();
	private String deedName;
	private String instName;
	private ArrayList ownerNameList = new ArrayList();
	private ArrayList reportList = new ArrayList();
	private ArrayList sroList = new ArrayList();
	private String ownName;
	private String count;
	private String totSum;
	private String regId;
	private String officeId;
	private String officeName;
	private String radio;

	// Revenure Rcpt SRO wise
	private String incMonthRural;
	private String incMonthUrban;
	private String incMonthTotal;
	private String incPrvMonthRural;
	private String incPrvMonthUrban;
	private String incPrvMonthTotal;
	private String incCompMonthRural;
	private String incCompMonthUrban;
	private String incCompMonthTotal;
	private String incAprlRural;
	private String incAprlUrban;
	private String incAprlTotal;
	private String incPrvAprlRural;
	private String incPrvAprlUrban;
	private String incPrvAprlTotal;
	private String incCompAprlRural;
	private String incCompAprlUrban;
	private String incCompAprlTotal;
	private String noDocMonthRural;
	private String noDocMonthUrban;
	private String noDocMonthTotal;
	private String noDocPrvMonthRural;
	private String noDocPrvMonthUrban;
	private String noDocPrvMonthTotal;
	private String noDocAprilRural;
	private String noDocAprilUrban;
	private String noDocAprilTotal;
	private String noDocPrvAprilRural;
	private String noDocPrvAprilUrban;
	private String noDocPrvAprilTotal;
	private String noDocCompRural;
	private String noDocCompUrban;
	private String noDocCompTotal;
	private String sroName;
	private String monthName;
	private String sroFlag;

	private String deedCount;
	private ArrayList deedList = new ArrayList();
	private String stampDuty;
	private String gramDuty;
	private String nagarDuty;
	private String upkarDuty;
	private String totalDuty;
	private String regFee;
	private String deedID;
	private ArrayList deedTypeList = new ArrayList();
	private String slotBooked;
	private String slotAvailable;
	private String regComp;
	private String slotPending;
	private String totSlotBooked;
	private String totRegComp;
	private String totSlotPending;
	private String totSlotAvailable;
	private String totDocDel;
	private String districtCount;
	private String considerationAmount;
	private String marketValue;
	private String docInitSP;
	private String makerStart;
	private String makerEnd;
	private String checkerStart;
	private String checkerEnd;
	private String makerPrintOut;
	private String sign;
	private String docDelivered;
private String districtZoneId;
private String districtZoneName;
	private String districtId;
    private String zoneId;
    private String selectedName;
    private String selectedId;
    	private String tehsilreg;
	private String tehsilIdreg;
    private String subAreaId;
    private String subAreaName;
	private String areaTypeId;
	private String areaTypeName;
	private ArrayList areaTypeList;
	private String zoneName;
	private String district;
	
	private String officeSROName;
	
	private ArrayList instList;
	private String instID;
	private ArrayList tehsilListreg=new ArrayList();
	private String propertyName;
	private ArrayList propertyList;
		private ArrayList offcListreg=new ArrayList();
	private String propertySubID;
	private String propertySubName;
	private ArrayList propertySubList;
	private String officeType;
	
	
	
   

public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

public ArrayList getPropertySubList() {
		return propertySubList;
	}

	public void setPropertySubList(ArrayList propertySubList) {
		this.propertySubList = propertySubList;
	}

public ArrayList getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}

public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

public String getInstID() {
		return instID;
	}

	public void setInstID(String instID) {
		this.instID = instID;
	}

public ArrayList getInstList() {
		return instList;
	}

	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}

public String getOfficeSROName() {
		return officeSROName;
	}

	public void setOfficeSROName(String officeSROName) {
		this.officeSROName = officeSROName;
	}

private ArrayList sroOfficeList  ;
   
   public ArrayList getSroOfficeList() {
	return sroOfficeList;
}

public void setSroOfficeList(ArrayList sroOfficeList) {
	this.sroOfficeList = sroOfficeList;
}

public ArrayList getSrList() {
	return srList;
}

public void setSrList(ArrayList srList) {
	this.srList = srList;
}
public ArrayList getOffcListreg() {
		return offcListreg;
	}
	public void setOffcListreg(ArrayList offcListreg) {
		this.offcListreg = offcListreg;
	}
	public ArrayList getTehsilListreg() {
		return tehsilListreg;
	}
	public void setTehsilListreg(ArrayList tehsilListreg) {
		this.tehsilListreg = tehsilListreg;
	}



private ArrayList srList;
	private String srName;
	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	public String getSrId() {
		return srId;
	}

	public void setSrId(String srId) {
		this.srId = srId;
	}

	private String srId;
	
	public String getTehsilreg() {
		return tehsilreg;
	}
	public void setTehsilreg(String tehsilreg) {
		this.tehsilreg = tehsilreg;
	}
	
	
	
    
    

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}

	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getSelectedName() {
		return selectedName;
	}

	public void setSelectedName(String selectedName) {
		this.selectedName = selectedName;
	}

	public String getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(String selectedId) {
		this.selectedId = selectedId;
	}
		public String getTehsilIdreg() {
		return tehsilIdreg;
	}
	public void setTehsilIdreg(String tehsilIdreg) {
		this.tehsilIdreg = tehsilIdreg;
	}



	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		districtID = districtId;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public ArrayList getZoneList() {
		return zoneList;
	}

	public void setZoneList(ArrayList zoneList) {
		this.zoneList = zoneList;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public ArrayList getSubReportList1() {
		return subReportList1;
	}

	public void setSubReportList1(ArrayList subReportList1) {
		this.subReportList1 = subReportList1;
	}

	public ArrayList getSubReportList() {
		return subReportList;
	}

	public void setSubReportList(ArrayList subReportList) {
		this.subReportList = subReportList;
	}
	private String officeReg;
	private String officeRegId;
	
	public String getOfficeReg() {
		return officeReg;
	}
	public void setOfficeReg(String officeReg) {
		this.officeReg = officeReg;
	}
	public String getOfficeRegId() {
		return officeRegId;
	}
	public void setOfficeRegId(String officeRegId) {
		this.officeRegId = officeRegId;
	}
	
	private String srUserIdreg;
	
	private String srNamereg;
	private ArrayList srListreg=new ArrayList();
	
	public String getSrUserIdreg() {
		return srUserIdreg;
	}
	public void setSrUserIdreg(String srUserIdreg) {
		this.srUserIdreg = srUserIdreg;
	}
	public String getSrNamereg() {
		return srNamereg;
	}
	public void setSrNamereg(String srNamereg) {
		this.srNamereg = srNamereg;
	}
	public ArrayList getSrListreg() {
		return srListreg;
	}
	public void setSrListreg(ArrayList srListreg) {
		this.srListreg = srListreg;
	}
	

	private String spNumber;
	private String spTotCrdLmt;
	private String amtEstampReg;
	private String amtEstampUnreg;
	private String spCommEarn;
	private String spCrdBal;
	private String spCrdPrvBal;
	private String carryFwdAmt;
	private String debFrmOthSer;
	private String totDistrict;
	private String totRegNo;
	private String totRegDate;
	private String totDeed;
	private String totStamp;
	private String totGram;
	private String totNagar;
	private String totUpkar;
	private String totDuty;
	private String totRegFee;
	private String totRegDutySum;
	private String regDutySum;
	private String makerID;
	private String checkerID;
	private String drillDistID;
	private String drillDeedID;
	private String drillTehsilID;
	private String tehsilID;
	private String propertyID;
	private String spLicSumm;
	private String deed1;
	private String count1;
	private String amount1;
	private String comm1;
	private String totspLicSumm;
	private String totdeed1;
	private String totcount1;
	private String totamount1;
	private String totcomm1;
	private String totestamp;
	private String totamount2;
	private String totcomm2;
	private String uploadID;
	private String uploadFile;
private ArrayList OfficeListreg= new ArrayList();
	
	public ArrayList getOfficeListreg() {
		return OfficeListreg;
	}
	public void setOfficeListreg(ArrayList officeListreg) {
		OfficeListreg = officeListreg;
	}
	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadID() {
		return uploadID;
	}

	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}

	public String getTotestamp() {
		return totestamp;
	}

	public void setTotestamp(String totestamp) {
		this.totestamp = totestamp;
	}

	public String getTotamount2() {
		return totamount2;
	}

	public void setTotamount2(String totamount2) {
		this.totamount2 = totamount2;
	}

	public String getTotcomm2() {
		return totcomm2;
	}

	public void setTotcomm2(String totcomm2) {
		this.totcomm2 = totcomm2;
	}

	public String getTotspLicSumm() {
		return totspLicSumm;
	}

	public void setTotspLicSumm(String totspLicSumm) {
		this.totspLicSumm = totspLicSumm;
	}

	public String getTotdeed1() {
		return totdeed1;
	}

	public void setTotdeed1(String totdeed1) {
		this.totdeed1 = totdeed1;
	}

	public String getTotcount1() {
		return totcount1;
	}

	public void setTotcount1(String totcount1) {
		this.totcount1 = totcount1;
	}

	public String getTotamount1() {
		return totamount1;
	}

	public void setTotamount1(String totamount1) {
		this.totamount1 = totamount1;
	}

	public String getTotcomm1() {
		return totcomm1;
	}

	public void setTotcomm1(String totcomm1) {
		this.totcomm1 = totcomm1;
	}

	public String getSpLicSumm() {
		return spLicSumm;
	}

	public void setSpLicSumm(String spLicSumm) {
		this.spLicSumm = spLicSumm;
	}

	public String getDeed1() {
		return deed1;
	}

	public void setDeed1(String deed1) {
		this.deed1 = deed1;
	}

	public String getCount1() {
		return count1;
	}

	public void setCount1(String count1) {
		this.count1 = count1;
	}

	public String getAmount1() {
		return amount1;
	}

	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

	public String getComm1() {
		return comm1;
	}

	public void setComm1(String comm1) {
		this.comm1 = comm1;
	}

	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	public String getDrillTehsilID() {
		return drillTehsilID;
	}

	public void setDrillTehsilID(String drillTehsilID) {
		this.drillTehsilID = drillTehsilID;
	}

	public String getTehsilID() {
		return tehsilID;
	}

	public void setTehsilID(String tehsilID) {
		this.tehsilID = tehsilID;
	}

	public String getDrillDistID() {
		return drillDistID;
	}

	public void setDrillDistID(String drillDistID) {
		this.drillDistID = drillDistID;
	}

	public String getDrillDeedID() {
		return drillDeedID;
	}

	public void setDrillDeedID(String drillDeedID) {
		this.drillDeedID = drillDeedID;
	}

	public String getTotTehsilName() {
		return totTehsilName;
	}

	public void setTotTehsilName(String totTehsilName) {
		this.totTehsilName = totTehsilName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getMakerID() {
		return makerID;
	}

	public void setMakerID(String makerID) {
		this.makerID = makerID;
	}

	public String getCheckerID() {
		return checkerID;
	}

	public void setCheckerID(String checkerID) {
		this.checkerID = checkerID;
	}

	public String getRegDutySum() {
		return regDutySum;
	}

	public void setRegDutySum(String regDutySum) {
		this.regDutySum = regDutySum;
	}

	public String getTotRegDutySum() {
		return totRegDutySum;
	}

	public void setTotRegDutySum(String totRegDutySum) {
		this.totRegDutySum = totRegDutySum;
	}

	public String getTotUpkar() {
		return totUpkar;
	}

	public void setTotUpkar(String totUpkar) {
		this.totUpkar = totUpkar;
	}

	public String getTotDistrict() {
		return totDistrict;
	}

	public void setTotDistrict(String totDistrict) {
		this.totDistrict = totDistrict;
	}

	public String getTotRegNo() {
		return totRegNo;
	}

	public void setTotRegNo(String totRegNo) {
		this.totRegNo = totRegNo;
	}

	public String getTotRegDate() {
		return totRegDate;
	}

	public void setTotRegDate(String totRegDate) {
		this.totRegDate = totRegDate;
	}

	public String getTotDeed() {
		return totDeed;
	}

	public void setTotDeed(String totDeed) {
		this.totDeed = totDeed;
	}

	public String getTotStamp() {
		return totStamp;
	}

	public void setTotStamp(String totStamp) {
		this.totStamp = totStamp;
	}

	public String getTotGram() {
		return totGram;
	}

	public void setTotGram(String totGram) {
		this.totGram = totGram;
	}

	public String getTotNagar() {
		return totNagar;
	}

	public void setTotNagar(String totNagar) {
		this.totNagar = totNagar;
	}

	public String getTotDuty() {
		return totDuty;
	}

	public void setTotDuty(String totDuty) {
		this.totDuty = totDuty;
	}

	public String getTotRegFee() {
		return totRegFee;
	}

	public void setTotRegFee(String totRegFee) {
		this.totRegFee = totRegFee;
	}

	public String getSpNumber() {
		return spNumber;
	}

	public void setSpNumber(String spNumber) {
		this.spNumber = spNumber;
	}

	public String getSpTotCrdLmt() {
		return spTotCrdLmt;
	}

	public void setSpTotCrdLmt(String spTotCrdLmt) {
		this.spTotCrdLmt = spTotCrdLmt;
	}

	public String getAmtEstampReg() {
		return amtEstampReg;
	}

	public void setAmtEstampReg(String amtEstampReg) {
		this.amtEstampReg = amtEstampReg;
	}

	public String getAmtEstampUnreg() {
		return amtEstampUnreg;
	}

	public void setAmtEstampUnreg(String amtEstampUnreg) {
		this.amtEstampUnreg = amtEstampUnreg;
	}
	
	public String getDebFrmOthSer() {
		return debFrmOthSer;
	}

	public void setDebFrmOthSer(String debFrmOthSer) {
		this.debFrmOthSer = debFrmOthSer;
	}

	public String getSpCommEarn() {
		return spCommEarn;
	}

	public void setSpCommEarn(String spCommEarn) {
		this.spCommEarn = spCommEarn;
	}

	public String getSpCrdBal() {
		return spCrdBal;
	}

	public void setSpCrdBal(String spCrdBal) {
		this.spCrdBal = spCrdBal;
	}
	
	public String getSpCrdPrvBal() {
		return spCrdPrvBal;
	}

	public void setSpCrdPrvBal(String spCrdPrvBal) {
		this.spCrdPrvBal = spCrdPrvBal;
	}
	
	public String getCarryFwdAmt() {
		return carryFwdAmt;
	}

	public void setCarryFwdAmt(String carryFwdAmt) {
		this.carryFwdAmt = carryFwdAmt;
	}

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public String getDocInitSP() {
		return docInitSP;
	}

	public void setDocInitSP(String docInitSP) {
		this.docInitSP = docInitSP;
	}

	public String getMakerStart() {
		return makerStart;
	}

	public void setMakerStart(String makerStart) {
		this.makerStart = makerStart;
	}

	public String getMakerEnd() {
		return makerEnd;
	}

	public void setMakerEnd(String makerEnd) {
		this.makerEnd = makerEnd;
	}

	public String getCheckerStart() {
		return checkerStart;
	}

	public void setCheckerStart(String checkerStart) {
		this.checkerStart = checkerStart;
	}

	public String getCheckerEnd() {
		return checkerEnd;
	}

	public void setCheckerEnd(String checkerEnd) {
		this.checkerEnd = checkerEnd;
	}

	public String getMakerPrintOut() {
		return makerPrintOut;
	}

	public void setMakerPrintOut(String makerPrintOut) {
		this.makerPrintOut = makerPrintOut;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getDocDelivered() {
		return docDelivered;
	}

	public void setDocDelivered(String docDelivered) {
		this.docDelivered = docDelivered;
	}

	public String getConsiderationAmount() {
		return considerationAmount;
	}

	public void setConsiderationAmount(String considerationAmount) {
		this.considerationAmount = considerationAmount;
	}

	public String getDistrictCount() {
		return districtCount;
	}

	public void setDistrictCount(String districtCount) {
		this.districtCount = districtCount;
	}

	public String getSlotBooked() {
		return slotBooked;
	}

	public void setSlotBooked(String slotBooked) {
		this.slotBooked = slotBooked;
	}

	public String getSlotAvailable() {
		return slotAvailable;
	}

	public void setSlotAvailable(String slotAvailable) {
		this.slotAvailable = slotAvailable;
	}

	public String getRegComp() {
		return regComp;
	}

	public void setRegComp(String regComp) {
		this.regComp = regComp;
	}

	public String getSlotPending() {
		return slotPending;
	}

	public void setSlotPending(String slotPending) {
		this.slotPending = slotPending;
	}

	public String getTotSlotBooked() {
		return totSlotBooked;
	}

	public void setTotSlotBooked(String totSlotBooked) {
		this.totSlotBooked = totSlotBooked;
	}

	public String getTotRegComp() {
		return totRegComp;
	}

	public void setTotRegComp(String totRegComp) {
		this.totRegComp = totRegComp;
	}

	public String getTotSlotPending() {
		return totSlotPending;
	}

	public void setTotSlotPending(String totSlotPending) {
		this.totSlotPending = totSlotPending;
	}

	public String getTotSlotAvailable() {
		return totSlotAvailable;
	}

	public void setTotSlotAvailable(String totSlotAvailable) {
		this.totSlotAvailable = totSlotAvailable;
	}

	public ArrayList getDeedTypeList() {
		return deedTypeList;
	}

	public void setDeedTypeList(ArrayList deedTypeList) {
		this.deedTypeList = deedTypeList;
	}

	public String getDeedID() {
		return deedID;
	}

	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}

	public String getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getGramDuty() {
		return gramDuty;
	}

	public void setGramDuty(String gramDuty) {
		this.gramDuty = gramDuty;
	}

	public String getNagarDuty() {
		return nagarDuty;
	}

	public void setNagarDuty(String nagarDuty) {
		this.nagarDuty = nagarDuty;
	}

	public String getUpkarDuty() {
		return upkarDuty;
	}

	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}

	public String getTotalDuty() {
		return totalDuty;
	}

	public void setTotalDuty(String totalDuty) {
		this.totalDuty = totalDuty;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	public String getDeedCount() {
		return deedCount;
	}

	public void setDeedCount(String deedCount) {
		this.deedCount = deedCount;
	}

	/**
	 * @return the sroFlag
	 */
	public String getSroFlag() {
		return sroFlag;
	}

	/**
	 * @param sroFlag
	 *            the sroFlag to set
	 */
	public void setSroFlag(String sroFlag) {
		this.sroFlag = sroFlag;
	}

	/**
	 * @return the monthName
	 */
	public String getMonthName() {
		return monthName;
	}

	/**
	 * @param monthName
	 *            the monthName to set
	 */
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	/**
	 * @return the sroName
	 */
	public String getSroName() {
		return sroName;
	}

	/**
	 * @param sroName
	 *            the sroName to set
	 */
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	/**
	 * @return the incMonthRural
	 */
	public String getIncMonthRural() {
		return incMonthRural;
	}

	/**
	 * @param incMonthRural
	 *            the incMonthRural to set
	 */
	public void setIncMonthRural(String incMonthRural) {
		this.incMonthRural = incMonthRural;
	}

	/**
	 * @return the incMonthUrban
	 */
	public String getIncMonthUrban() {
		return incMonthUrban;
	}

	/**
	 * @param incMonthUrban
	 *            the incMonthUrban to set
	 */
	public void setIncMonthUrban(String incMonthUrban) {
		this.incMonthUrban = incMonthUrban;
	}

	/**
	 * @return the incMonthTotal
	 */
	public String getIncMonthTotal() {
		return incMonthTotal;
	}

	/**
	 * @param incMonthTotal
	 *            the incMonthTotal to set
	 */
	public void setIncMonthTotal(String incMonthTotal) {
		this.incMonthTotal = incMonthTotal;
	}

	/**
	 * @return the incPrvMonthRural
	 */
	public String getIncPrvMonthRural() {
		return incPrvMonthRural;
	}

	/**
	 * @param incPrvMonthRural
	 *            the incPrvMonthRural to set
	 */
	public void setIncPrvMonthRural(String incPrvMonthRural) {
		this.incPrvMonthRural = incPrvMonthRural;
	}

	/**
	 * @return the incPrvMonthUrban
	 */
	public String getIncPrvMonthUrban() {
		return incPrvMonthUrban;
	}

	/**
	 * @param incPrvMonthUrban
	 *            the incPrvMonthUrban to set
	 */
	public void setIncPrvMonthUrban(String incPrvMonthUrban) {
		this.incPrvMonthUrban = incPrvMonthUrban;
	}

	/**
	 * @return the incPrvMonthTotal
	 */
	public String getIncPrvMonthTotal() {
		return incPrvMonthTotal;
	}

	/**
	 * @param incPrvMonthTotal
	 *            the incPrvMonthTotal to set
	 */
	public void setIncPrvMonthTotal(String incPrvMonthTotal) {
		this.incPrvMonthTotal = incPrvMonthTotal;
	}

	/**
	 * @return the incCompMonthRural
	 */
	public String getIncCompMonthRural() {
		return incCompMonthRural;
	}

	/**
	 * @param incCompMonthRural
	 *            the incCompMonthRural to set
	 */
	public void setIncCompMonthRural(String incCompMonthRural) {
		this.incCompMonthRural = incCompMonthRural;
	}

	/**
	 * @return the incCompMonthUrban
	 */
	public String getIncCompMonthUrban() {
		return incCompMonthUrban;
	}

	/**
	 * @param incCompMonthUrban
	 *            the incCompMonthUrban to set
	 */
	public void setIncCompMonthUrban(String incCompMonthUrban) {
		this.incCompMonthUrban = incCompMonthUrban;
	}

	/**
	 * @return the incCompMonthTotal
	 */
	public String getIncCompMonthTotal() {
		return incCompMonthTotal;
	}

	/**
	 * @param incCompMonthTotal
	 *            the incCompMonthTotal to set
	 */
	public void setIncCompMonthTotal(String incCompMonthTotal) {
		this.incCompMonthTotal = incCompMonthTotal;
	}

	/**
	 * @return the incAprlRural
	 */
	public String getIncAprlRural() {
		return incAprlRural;
	}

	/**
	 * @param incAprlRural
	 *            the incAprlRural to set
	 */
	public void setIncAprlRural(String incAprlRural) {
		this.incAprlRural = incAprlRural;
	}

	/**
	 * @return the incAprlUrban
	 */
	public String getIncAprlUrban() {
		return incAprlUrban;
	}

	/**
	 * @param incAprlUrban
	 *            the incAprlUrban to set
	 */
	public void setIncAprlUrban(String incAprlUrban) {
		this.incAprlUrban = incAprlUrban;
	}

	/**
	 * @return the incAprlTotal
	 */
	public String getIncAprlTotal() {
		return incAprlTotal;
	}

	/**
	 * @param incAprlTotal
	 *            the incAprlTotal to set
	 */
	public void setIncAprlTotal(String incAprlTotal) {
		this.incAprlTotal = incAprlTotal;
	}

	/**
	 * @return the incPrvAprlRural
	 */
	public String getIncPrvAprlRural() {
		return incPrvAprlRural;
	}

	/**
	 * @param incPrvAprlRural
	 *            the incPrvAprlRural to set
	 */
	public void setIncPrvAprlRural(String incPrvAprlRural) {
		this.incPrvAprlRural = incPrvAprlRural;
	}

	/**
	 * @return the incPrvAprlUrban
	 */
	public String getIncPrvAprlUrban() {
		return incPrvAprlUrban;
	}

	/**
	 * @param incPrvAprlUrban
	 *            the incPrvAprlUrban to set
	 */
	public void setIncPrvAprlUrban(String incPrvAprlUrban) {
		this.incPrvAprlUrban = incPrvAprlUrban;
	}

	/**
	 * @return the incPrvAprlTotal
	 */
	public String getIncPrvAprlTotal() {
		return incPrvAprlTotal;
	}

	/**
	 * @param incPrvAprlTotal
	 *            the incPrvAprlTotal to set
	 */
	public void setIncPrvAprlTotal(String incPrvAprlTotal) {
		this.incPrvAprlTotal = incPrvAprlTotal;
	}

	/**
	 * @return the incCompAprlRural
	 */
	public String getIncCompAprlRural() {
		return incCompAprlRural;
	}

	/**
	 * @param incCompAprlRural
	 *            the incCompAprlRural to set
	 */
	public void setIncCompAprlRural(String incCompAprlRural) {
		this.incCompAprlRural = incCompAprlRural;
	}

	/**
	 * @return the incCompAprlUrban
	 */
	public String getIncCompAprlUrban() {
		return incCompAprlUrban;
	}

	/**
	 * @param incCompAprlUrban
	 *            the incCompAprlUrban to set
	 */
	public void setIncCompAprlUrban(String incCompAprlUrban) {
		this.incCompAprlUrban = incCompAprlUrban;
	}

	/**
	 * @return the incCompAprlTotal
	 */
	public String getIncCompAprlTotal() {
		return incCompAprlTotal;
	}

	/**
	 * @param incCompAprlTotal
	 *            the incCompAprlTotal to set
	 */
	public void setIncCompAprlTotal(String incCompAprlTotal) {
		this.incCompAprlTotal = incCompAprlTotal;
	}

	/**
	 * @return the noDocMonthRural
	 */
	public String getNoDocMonthRural() {
		return noDocMonthRural;
	}

	/**
	 * @param noDocMonthRural
	 *            the noDocMonthRural to set
	 */
	public void setNoDocMonthRural(String noDocMonthRural) {
		this.noDocMonthRural = noDocMonthRural;
	}

	/**
	 * @return the noDocMonthUrban
	 */
	public String getNoDocMonthUrban() {
		return noDocMonthUrban;
	}

	/**
	 * @param noDocMonthUrban
	 *            the noDocMonthUrban to set
	 */
	public void setNoDocMonthUrban(String noDocMonthUrban) {
		this.noDocMonthUrban = noDocMonthUrban;
	}

	/**
	 * @return the noDocMonthTotal
	 */
	public String getNoDocMonthTotal() {
		return noDocMonthTotal;
	}

	/**
	 * @param noDocMonthTotal
	 *            the noDocMonthTotal to set
	 */
	public void setNoDocMonthTotal(String noDocMonthTotal) {
		this.noDocMonthTotal = noDocMonthTotal;
	}

	/**
	 * @return the noDocPrvMonthRural
	 */
	public String getNoDocPrvMonthRural() {
		return noDocPrvMonthRural;
	}

	/**
	 * @param noDocPrvMonthRural
	 *            the noDocPrvMonthRural to set
	 */
	public void setNoDocPrvMonthRural(String noDocPrvMonthRural) {
		this.noDocPrvMonthRural = noDocPrvMonthRural;
	}

	/**
	 * @return the noDocPrvMonthUrban
	 */
	public String getNoDocPrvMonthUrban() {
		return noDocPrvMonthUrban;
	}

	/**
	 * @param noDocPrvMonthUrban
	 *            the noDocPrvMonthUrban to set
	 */
	public void setNoDocPrvMonthUrban(String noDocPrvMonthUrban) {
		this.noDocPrvMonthUrban = noDocPrvMonthUrban;
	}

	/**
	 * @return the noDocPrvMonthTotal
	 */
	public String getNoDocPrvMonthTotal() {
		return noDocPrvMonthTotal;
	}

	/**
	 * @param noDocPrvMonthTotal
	 *            the noDocPrvMonthTotal to set
	 */
	public void setNoDocPrvMonthTotal(String noDocPrvMonthTotal) {
		this.noDocPrvMonthTotal = noDocPrvMonthTotal;
	}

	/**
	 * @return the noDocAprilRural
	 */
	public String getNoDocAprilRural() {
		return noDocAprilRural;
	}

	/**
	 * @param noDocAprilRural
	 *            the noDocAprilRural to set
	 */
	public void setNoDocAprilRural(String noDocAprilRural) {
		this.noDocAprilRural = noDocAprilRural;
	}

	/**
	 * @return the noDocAprilUrban
	 */
	public String getNoDocAprilUrban() {
		return noDocAprilUrban;
	}

	/**
	 * @param noDocAprilUrban
	 *            the noDocAprilUrban to set
	 */
	public void setNoDocAprilUrban(String noDocAprilUrban) {
		this.noDocAprilUrban = noDocAprilUrban;
	}

	/**
	 * @return the noDocAprilTotal
	 */
	public String getNoDocAprilTotal() {
		return noDocAprilTotal;
	}

	/**
	 * @param noDocAprilTotal
	 *            the noDocAprilTotal to set
	 */
	public void setNoDocAprilTotal(String noDocAprilTotal) {
		this.noDocAprilTotal = noDocAprilTotal;
	}

	/**
	 * @return the noDocPrvAprilRural
	 */
	public String getNoDocPrvAprilRural() {
		return noDocPrvAprilRural;
	}

	/**
	 * @param noDocPrvAprilRural
	 *            the noDocPrvAprilRural to set
	 */
	public void setNoDocPrvAprilRural(String noDocPrvAprilRural) {
		this.noDocPrvAprilRural = noDocPrvAprilRural;
	}

	/**
	 * @return the noDocPrvAprilUrban
	 */
	public String getNoDocPrvAprilUrban() {
		return noDocPrvAprilUrban;
	}

	/**
	 * @param noDocPrvAprilUrban
	 *            the noDocPrvAprilUrban to set
	 */
	public void setNoDocPrvAprilUrban(String noDocPrvAprilUrban) {
		this.noDocPrvAprilUrban = noDocPrvAprilUrban;
	}

	/**
	 * @return the noDocPrvAprilTotal
	 */
	public String getNoDocPrvAprilTotal() {
		return noDocPrvAprilTotal;
	}

	/**
	 * @param noDocPrvAprilTotal
	 *            the noDocPrvAprilTotal to set
	 */
	public void setNoDocPrvAprilTotal(String noDocPrvAprilTotal) {
		this.noDocPrvAprilTotal = noDocPrvAprilTotal;
	}

	/**
	 * @return the noDocCompRural
	 */
	public String getNoDocCompRural() {
		return noDocCompRural;
	}

	/**
	 * @param noDocCompRural
	 *            the noDocCompRural to set
	 */
	public void setNoDocCompRural(String noDocCompRural) {
		this.noDocCompRural = noDocCompRural;
	}

	/**
	 * @return the noDocCompUrban
	 */
	public String getNoDocCompUrban() {
		return noDocCompUrban;
	}

	/**
	 * @param noDocCompUrban
	 *            the noDocCompUrban to set
	 */
	public void setNoDocCompUrban(String noDocCompUrban) {
		this.noDocCompUrban = noDocCompUrban;
	}

	/**
	 * @return the noDocCompTotal
	 */
	public String getNoDocCompTotal() {
		return noDocCompTotal;
	}

	/**
	 * @param noDocCompTotal
	 *            the noDocCompTotal to set
	 */
	public void setNoDocCompTotal(String noDocCompTotal) {
		this.noDocCompTotal = noDocCompTotal;
	}

	/**
	 * @return the radio
	 */
	public String getRadio() {
		return radio;
	}

	/**
	 * @param radio
	 *            the radio to set
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}

	/**
	 * @return the sroList
	 */
	public ArrayList getSroList() {
		return sroList;
	}

	/**
	 * @param sroList
	 *            the sroList to set
	 */
	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId
	 *            the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the totSum
	 */
	public String getTotSum() {
		return totSum;
	}

	/**
	 * @param totSum
	 *            the totSum to set
	 */
	public void setTotSum(String totSum) {
		this.totSum = totSum;
	}

	/**
	 * @return the count
	 */
	public String getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(String count) {
		this.count = count;
	}

	/**
	 * @return the reportList
	 */
	public ArrayList getReportList() {
		return reportList;
	}

	/**
	 * @param reportList
	 *            the reportList to set
	 */
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
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
	 * @return the deedName
	 */
	public String getDeedName() {
		return deedName;
	}

	/**
	 * @param deedName
	 *            the deedName to set
	 */
	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}

	/**
	 * @return the instName
	 */
	public String getInstName() {
		return instName;
	}

	/**
	 * @param instName
	 *            the instName to set
	 */
	public void setInstName(String instName) {
		this.instName = instName;
	}

	/**
	 * @return the ownerNameList
	 */
	public ArrayList getOwnerNameList() {
		return ownerNameList;
	}

	/**
	 * @param ownerNameList
	 *            the ownerNameList to set
	 */
	public void setOwnerNameList(ArrayList ownerNameList) {
		this.ownerNameList = ownerNameList;
	}

	/**
	 * @return the ownName
	 */
	public String getOwnName() {
		return ownName;
	}

	/**
	 * @param ownName
	 *            the ownName to set
	 */
	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYearValue() {
		return YearValue;
	}

	public void setYearValue(String yearValue) {
		YearValue = yearValue;
	}

	public String getRevenueTotalOrg() {
		return revenueTotalOrg;
	}

	public void setRevenueTotalOrg(String revenueTotalOrg) {
		this.revenueTotalOrg = revenueTotalOrg;
	}

	public String getNameOfOrg() {
		return nameOfOrg;
	}

	public void setNameOfOrg(String nameOfOrg) {
		this.nameOfOrg = nameOfOrg;
	}

	public String getNoOfDocOrg() {
		return noOfDocOrg;
	}

	public void setNoOfDocOrg(String noOfDocOrg) {
		this.noOfDocOrg = noOfDocOrg;
	}

	public String getRevenueReceiptOrg() {
		return revenueReceiptOrg;
	}

	public void setRevenueReceiptOrg(String revenueReceiptOrg) {
		this.revenueReceiptOrg = revenueReceiptOrg;
	}

	public String getRevenueApril() {
		return revenueApril;
	}

	public void setRevenueApril(String revenueApril) {
		this.revenueApril = revenueApril;
	}

	public String getRevenueMay() {
		return revenueMay;
	}

	public void setRevenueMay(String revenueMay) {
		this.revenueMay = revenueMay;
	}

	public String getRevenueJune() {
		return revenueJune;
	}

	public void setRevenueJune(String revenueJune) {
		this.revenueJune = revenueJune;
	}

	public String getRevenueJuly() {
		return revenueJuly;
	}

	public void setRevenueJuly(String revenueJuly) {
		this.revenueJuly = revenueJuly;
	}

	public String getRevenueAug() {
		return revenueAug;
	}

	public void setRevenueAug(String revenueAug) {
		this.revenueAug = revenueAug;
	}

	public String getRevenueSept() {
		return revenueSept;
	}

	public void setRevenueSept(String revenueSept) {
		this.revenueSept = revenueSept;
	}

	public String getRevenueOct() {
		return revenueOct;
	}

	public void setRevenueOct(String revenueOct) {
		this.revenueOct = revenueOct;
	}

	public String getRevenueNov() {
		return revenueNov;
	}

	public void setRevenueNov(String revenueNov) {
		this.revenueNov = revenueNov;
	}

	public String getRevenueDec() {
		return revenueDec;
	}

	public void setRevenueDec(String revenueDec) {
		this.revenueDec = revenueDec;
	}

	public String getRevenueJan() {
		return revenueJan;
	}

	public void setRevenueJan(String revenueJan) {
		this.revenueJan = revenueJan;
	}

	public String getRevenueFeb() {
		return revenueFeb;
	}

	public void setRevenueFeb(String revenueFeb) {
		this.revenueFeb = revenueFeb;
	}

	public String getRevenueMar() {
		return revenueMar;
	}

	public void setRevenueMar(String revenueMar) {
		this.revenueMar = revenueMar;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSearchDocumentDetails() {
		return searchDocumentDetails;
	}

	public void setSearchDocumentDetails(String searchDocumentDetails) {
		this.searchDocumentDetails = searchDocumentDetails;
	}

	public String getSearchPropertyOwnerName() {
		return searchPropertyOwnerName;
	}

	public void setSearchPropertyOwnerName(String searchPropertyOwnerName) {
		this.searchPropertyOwnerName = searchPropertyOwnerName;
	}

	public String getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(String searchCount) {
		this.searchCount = searchCount;
	}

	public String getSearchTotal() {
		return searchTotal;
	}

	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getNoTimeAccessed() {
		return noTimeAccessed;
	}

	public void setNoTimeAccessed(String noTimeAccessed) {
		this.noTimeAccessed = noTimeAccessed;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getRevenueRuralCur() {
		return revenueRuralCur;
	}

	public void setRevenueRuralCur(String revenueRuralCur) {
		this.revenueRuralCur = revenueRuralCur;
	}

	public String getRevenueUrbanCur() {
		return revenueUrbanCur;
	}

	public void setRevenueUrbanCur(String revenueUrbanCur) {
		this.revenueUrbanCur = revenueUrbanCur;
	}

	public String getRevenueCurTotal() {
		return revenueCurTotal;
	}

	public void setRevenueCurTotal(String revenueCurTotal) {
		this.revenueCurTotal = revenueCurTotal;
	}

	public String getRevenueRuralPrev() {
		return revenueRuralPrev;
	}

	public void setRevenueRuralPrev(String revenueRuralPrev) {
		this.revenueRuralPrev = revenueRuralPrev;
	}

	public String getRevenueUrbanPrev() {
		return revenueUrbanPrev;
	}

	public void setRevenueUrbanPrev(String revenueUrbanPrev) {
		this.revenueUrbanPrev = revenueUrbanPrev;
	}

	public String getRevenuePrevTotal() {
		return revenuePrevTotal;
	}

	public void setRevenuePrevTotal(String revenuePrevTotal) {
		this.revenuePrevTotal = revenuePrevTotal;
	}

	public String getRevenueRuralPer() {
		return revenueRuralPer;
	}

	public void setRevenueRuralPer(String revenueRuralPer) {
		this.revenueRuralPer = revenueRuralPer;
	}

	public String getRevenueUrbanPer() {
		return revenueUrbanPer;
	}

	public void setRevenueUrbanPer(String revenueUrbanPer) {
		this.revenueUrbanPer = revenueUrbanPer;
	}

	public String getRevenuePerTotal() {
		return revenuePerTotal;
	}

	public void setRevenuePerTotal(String revenuePerTotal) {
		this.revenuePerTotal = revenuePerTotal;
	}

	public String getNoOfDocRuralCur() {
		return noOfDocRuralCur;
	}

	public void setNoOfDocRuralCur(String noOfDocRuralCur) {
		this.noOfDocRuralCur = noOfDocRuralCur;
	}

	public String getNoOfDocUrbanCur() {
		return noOfDocUrbanCur;
	}

	public void setNoOfDocUrbanCur(String noOfDocUrbanCur) {
		this.noOfDocUrbanCur = noOfDocUrbanCur;
	}

	public String getNoOfDocCurTotal() {
		return noOfDocCurTotal;
	}

	public void setNoOfDocCurTotal(String noOfDocCurTotal) {
		this.noOfDocCurTotal = noOfDocCurTotal;
	}

	public String getNoOfDocRuralPrev() {
		return noOfDocRuralPrev;
	}

	public void setNoOfDocRuralPrev(String noOfDocRuralPrev) {
		this.noOfDocRuralPrev = noOfDocRuralPrev;
	}

	public String getNoOfDocUrbanPrev() {
		return noOfDocUrbanPrev;
	}

	public void setNoOfDocUrbanPrev(String noOfDocUrbanPrev) {
		this.noOfDocUrbanPrev = noOfDocUrbanPrev;
	}

	public String getNoOfDocPrevTotal() {
		return noOfDocPrevTotal;
	}

	public void setNoOfDocPrevTotal(String noOfDocPrevTotal) {
		this.noOfDocPrevTotal = noOfDocPrevTotal;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setAccessdate(String accessdate) {
		this.accessdate = accessdate;
	}

	public String getAccessdate() {
		return accessdate;
	}

	public void setTotDocDel(String totDocDel) {
		this.totDocDel = totDocDel;
	}

	public String getTotDocDel() {
		return totDocDel;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getAreaTypeId() {
		return areaTypeId;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrict() {
		return district;
	}

	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}

	public String getSubAreaName() {
		return subAreaName;
	}

	public void setDistrictZoneName(String districtZoneName) {
		this.districtZoneName = districtZoneName;
	}

	public String getDistrictZoneName() {
		return districtZoneName;
	}

	public void setDistrictZoneId(String districtZoneId) {
		this.districtZoneId = districtZoneId;
	}

	public String getDistrictZoneId() {
		return districtZoneId;
	}

	public void setPropertySubName(String propertySubName) {
		this.propertySubName = propertySubName;
	}

	public String getPropertySubName() {
		return propertySubName;
	}

	public void setPropertySubID(String propertySubID) {
		this.propertySubID = propertySubID;
	}

	public String getPropertySubID() {
		return propertySubID;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}
	
	HashMap<Integer, String> recordSet;

	public HashMap<Integer, String> getRecordSet() {
		return recordSet;
	}

	public void setRecordSet(HashMap<Integer, String> recordSet) {
		this.recordSet = recordSet;
	}
	
}

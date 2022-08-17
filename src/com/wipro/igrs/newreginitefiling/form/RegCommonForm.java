package com.wipro.igrs.newreginitefiling.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.newPropvaluationefiling.dto.KhasraClrDTO;
import com.wipro.igrs.newreginitefiling.dto.CommonDTO;
import com.wipro.igrs.newreginitefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCompletDTO;
import com.wipro.igrs.propertyvaluationefiling.dto.DutyCalculationDTO;


public class RegCommonForm extends ActionForm implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partypo;
	private String partysr;
	private String fileError1;
	private String fileSizeError;
	private String fileSizeUploadError1;
	private String uploadFileError;
	//private String govdesg;
	//private String govfather;
	//private String govaddress;
	private String adduploadflag;
	private String adduploadflag1;
	private String propImage2DocumentName1;
	private FormFile propImage2;
	private String digpath;
	private String govdesg;
	private String govfather;
	private String govaddress;
	
	private String indname;
	private String indfather;
	private String indiaddress;
	
	private String orgname;
	private String orgfather;
	private String orgiaddress;
	
	//below to display duty from deshboard resume
	private String exemptionFlag;
	private String statusFlagSR;
	private String modifyFlagSR;
	private String modifyFlagPO;
	private String statusFlagPO;
	private String tehsilId;
	private String efileFeePrint;
	private String physicalStampFlag;
	private String physicalStampFlag1;
	private String physicalStampFlagPo;
	private String uploadpath;
	private String uploadflag;
	private String uploadpath1;
	private String uploadflag1;
	private String formuploadpath;
	private String formuploadflag2;
	private String formuploadpath1;
	private String formuploadflag1;
	
	private String commonView;
	
	private String poaorgauthname;
	private String poaorgfather;
	private String poaorgaddress;
	private String poaorgpartytype;




	private String physicalStampFlagPo1;
	private String estamppayflag;
	private String phyCode;
	private String phyCodePo;
	private String poStampVendorCode;
	private String poSeriesNo;
	private String poDateOfStamp;
	private String fileError;
	private String stampDutyDashboard;
	
	private String signedPath; // Added By Gulrej on 28th July, 2017
	private String printCheck ; // Added By Gulrej on 31st July, 2017
	private String owmFlag;
	private String owmFile;
	
	public String getOwmFile() {
		return owmFile;
	}
	public void setOwmFile(String owmFile) {
		this.owmFile = owmFile;
	}
	public String getOwmFlag() {
		return owmFlag;
	}
	public void setOwmFlag(String owmFlag) {
		this.owmFlag = owmFlag;
	}
	public String getPrintCheck() {
		return printCheck;
	}
	public void setPrintCheck(String printCheck) {
		this.printCheck = printCheck;
	}
	public String getSignedPath() {
		return signedPath;
	}
	public void setSignedPath(String signedPath) {
		this.signedPath = signedPath;
	}
	public String getStampDutyDashboard() {
		return stampDutyDashboard;
	}
	public void setStampDutyDashboard(String stampDutyDashboard) {
		this.stampDutyDashboard = stampDutyDashboard;
	}
	public String getMunDutyDashboard() {
		return munDutyDashboard;
	}
	public void setMunDutyDashboard(String munDutyDashboard) {
		this.munDutyDashboard = munDutyDashboard;
	}
	public String getJanpadDutyDashboard() {
		return janpadDutyDashboard;
	}
	public void setJanpadDutyDashboard(String janpadDutyDashboard) {
		this.janpadDutyDashboard = janpadDutyDashboard;
	}
	public String getUpkarDutyDashboard() {
		return upkarDutyDashboard;
	}
	public void setUpkarDutyDashboard(String upkarDutyDashboard) {
		this.upkarDutyDashboard = upkarDutyDashboard;
	}
	public String getTotalDutyDashboard() {
		return totalDutyDashboard;
	}
	public void setTotalDutyDashboard(String totalDutyDashboard) {
		this.totalDutyDashboard = totalDutyDashboard;
	}
	public String getTotalafterExemDashboard() {
		return totalafterExemDashboard;
	}
	public void setTotalafterExemDashboard(String totalafterExemDashboard) {
		this.totalafterExemDashboard = totalafterExemDashboard;
	}
	


private String propImage2DocumentName;
	public String getPropImage2DocumentName() {
	return propImage2DocumentName;
}
public void setPropImage2DocumentName(String propImage2DocumentName) {
	this.propImage2DocumentName = propImage2DocumentName;
}

private String propImage5DocumentName;

	private String munDutyDashboard;
	private String janpadDutyDashboard;
	private String upkarDutyDashboard;
	private String totalDutyDashboard;
	private String totalafterExemDashboard;
	private String efileFee;
	private String estampCodeDashboard;
	private String estampStatusDashboard;
	private String dutyAlreadyPaid; // added by siddhartha
	//end of display duty from deshboard resume
	
	
	//party details gov type
	private String nameofOffical;
	private String desgofOffical;
	private String addofOffical;
	private String addOutMp;
	private String districttype;
	private String statetype;
	private String countrytype;
	private String tehsiltype;
	
	
	private String govpartyType;
	private String indpartyType;
	private String orgpartyType;
	
	private String poaauthname;
	private String poafather;
	private String poaaddress;
	private String poapartytype;
	
	//end
	
	//party deatils for ind
	
	
	private String partyRoleInd;
	private String partyTypeInd;
	
	
	private String efilepartyfirstname;
	private String	efilepartymiddlename;
	private String	efilepartylastname;
	private String	efilepartygender;
	private String	efilepartyage;
	private String efilerelationind;
	private String efilecategoryind;
	private String efilemembermin;
	private String belowline;
	private String efileoccupationind;
	private String efilephotoidtype;
	private String efilephotoid;
	private String phonenoind;
	private String efiledisableity;
	private String efilepoasr;
	private String efilepoareg;
	public String getEfilepoasr() {
		return efilepoasr;
	}
	public void setEfilepoasr(String efilepoasr) {
		this.efilepoasr = efilepoasr;
	}
	public String getEfilepoareg() {
		return efilepoareg;
	}
	public void setEfilepoareg(String efilepoareg) {
		this.efilepoareg = efilepoareg;
	}
	public String getEfilepoadate() {
		return efilepoadate;
	}
	public void setEfilepoadate(String efilepoadate) {
		this.efilepoadate = efilepoadate;
	}



	private String efilepoadate;
	private String tehsilintype;
	public String getEfilerelationind() {
		return efilerelationind;
	}
	public void setEfilerelationind(String efilerelationind) {
		this.efilerelationind = efilerelationind;
	}
	public String getEfilecategoryind() {
		return efilecategoryind;
	}
	public void setEfilecategoryind(String efilecategoryind) {
		this.efilecategoryind = efilecategoryind;
	}
	public String getEfilemembermin() {
		return efilemembermin;
	}
	public void setEfilemembermin(String efilemembermin) {
		this.efilemembermin = efilemembermin;
	}
	public String getBelowline() {
		return belowline;
	}
	public void setBelowline(String belowline) {
		this.belowline = belowline;
	}
	public String getEfileoccupationind() {
		return efileoccupationind;
	}
	public void setEfileoccupationind(String efileoccupationind) {
		this.efileoccupationind = efileoccupationind;
	}
	public String getEfilephotoidtype() {
		return efilephotoidtype;
	}
	public void setEfilephotoidtype(String efilephotoidtype) {
		this.efilephotoidtype = efilephotoidtype;
	}
	public String getEfilephotoid() {
		return efilephotoid;
	}
	public void setEfilephotoid(String efilephotoid) {
		this.efilephotoid = efilephotoid;
	}



	private String	efilepartyspouse;
	private String	efilepartynationality;
	private String	efilepartynal;
	private String	efilepartypin;
	private String	efilepartyphno;
	private String	efilepartymobno;
	private String	efilepostalcode;
	private String efilephotoId;
	private String	efilepartyadd;
	private String	efilepartyaddoutmp;
	private String	efilepartydist;
	private String	efilepartystate;
	private String	efilepartycontry;
	private String	efilepartyorgname;
	private String	efilepartyauthname;
	private String	efilepartyguraname;
	private String	efilepartymothername;
	private String	efilepartyemail;
	private String efileupload1;
	private String efileupload2;
	private String efileupload3;
	private String efileupload4;
	private String efileupload5;
	private String efileupload6;
	private String efileupload7;
	private String efileuploadflag1;
	private String efileuploadflag2;
	private String efileuploadflag3;
	private String efileuploadflag4;
	private String efileuploadflag5;
	private String efileuploadflag6;
	private String efileuploadflag7;
	public String getEfileupload6() {
		return efileupload6;
	}
	public void setEfileupload6(String efileupload6) {
		this.efileupload6 = efileupload6;
	}
	public String getEfileupload7() {
		return efileupload7;
	}
	public void setEfileupload7(String efileupload7) {
		this.efileupload7 = efileupload7;
	}
	public String getEfileuploadflag6() {
		return efileuploadflag6;
	}
	public void setEfileuploadflag6(String efileuploadflag6) {
		this.efileuploadflag6 = efileuploadflag6;
	}
	public String getEfileuploadflag7() {
		return efileuploadflag7;
	}
	public void setEfileuploadflag7(String efileuploadflag7) {
		this.efileuploadflag7 = efileuploadflag7;
	}
	public String getEfileupload4() {
		return efileupload4;
	}
	public void setEfileupload4(String efileupload4) {
		this.efileupload4 = efileupload4;
	}
	public String getEfileupload5() {
		return efileupload5;
	}
	public void setEfileupload5(String efileupload5) {
		this.efileupload5 = efileupload5;
	}
	public String getEfileuploadflag4() {
		return efileuploadflag4;
	}
	public void setEfileuploadflag4(String efileuploadflag4) {
		this.efileuploadflag4 = efileuploadflag4;
	}
	public String getEfileuploadflag5() {
		return efileuploadflag5;
	}
	public void setEfileuploadflag5(String efileuploadflag5) {
		this.efileuploadflag5 = efileuploadflag5;
	}



	private String efileUploadView;
	private String efileuploadOrg1;
	private String efileuploadOrg2;
	private String efileuploadOrg3;
	private String efileuploadOrg4;
	private String efileuploadOrg5;
	private String efileuploadOrg6;
	private String efileuploadOrg7;
	private String efileuploadOrgflag1;
	private String efileuploadOrgflag2;
	private String efileuploadOrgflag3;
	private String efileuploadOrgflag4;
	private String efileuploadOrgflag5;
	private String efileuploadOrgflag6;
	private String efileuploadOrgflag7;
	public String getEfileuploadOrg5() {
		return efileuploadOrg5;
	}
	public void setEfileuploadOrg5(String efileuploadOrg5) {
		this.efileuploadOrg5 = efileuploadOrg5;
	}
	public String getEfileuploadOrg6() {
		return efileuploadOrg6;
	}
	public void setEfileuploadOrg6(String efileuploadOrg6) {
		this.efileuploadOrg6 = efileuploadOrg6;
	}
	public String getEfileuploadOrg7() {
		return efileuploadOrg7;
	}
	public void setEfileuploadOrg7(String efileuploadOrg7) {
		this.efileuploadOrg7 = efileuploadOrg7;
	}
	public String getEfileuploadOrgflag4() {
		return efileuploadOrgflag4;
	}
	public void setEfileuploadOrgflag4(String efileuploadOrgflag4) {
		this.efileuploadOrgflag4 = efileuploadOrgflag4;
	}
	public String getEfileuploadOrgflag5() {
		return efileuploadOrgflag5;
	}
	public void setEfileuploadOrgflag5(String efileuploadOrgflag5) {
		this.efileuploadOrgflag5 = efileuploadOrgflag5;
	}
	public String getEfileuploadOrgflag6() {
		return efileuploadOrgflag6;
	}
	public void setEfileuploadOrgflag6(String efileuploadOrgflag6) {
		this.efileuploadOrgflag6 = efileuploadOrgflag6;
	}
	public String getEfileuploadOrgflag7() {
		return efileuploadOrgflag7;
	}
	public void setEfileuploadOrgflag7(String efileuploadOrgflag7) {
		this.efileuploadOrgflag7 = efileuploadOrgflag7;
	}
	public String getPartyorgname() {
		return partyorgname;
	}
	public void setPartyorgname(String partyorgname) {
		this.partyorgname = partyorgname;
	}
	public String getAuthopersonname() {
		return authopersonname;
	}
	public void setAuthopersonname(String authopersonname) {
		this.authopersonname = authopersonname;
	}
	public String getPartygender() {
		return partygender;
	}
	public void setPartygender(String partygender) {
		this.partygender = partygender;
	}
	public String getPartyrelationorg() {
		return partyrelationorg;
	}
	public void setPartyrelationorg(String partyrelationorg) {
		this.partyrelationorg = partyrelationorg;
	}
	public String getOrgaddoutmp() {
		return orgaddoutmp;
	}
	public void setOrgaddoutmp(String orgaddoutmp) {
		this.orgaddoutmp = orgaddoutmp;
	}
	public String getOrgadd() {
		return orgadd;
	}
	public void setOrgadd(String orgadd) {
		this.orgadd = orgadd;
	}
	public String getOrgcountry() {
		return orgcountry;
	}
	public void setOrgcountry(String orgcountry) {
		this.orgcountry = orgcountry;
	}
	public String getOrgstate() {
		return orgstate;
	}
	public void setOrgstate(String orgstate) {
		this.orgstate = orgstate;
	}
	public String getOrgdistict() {
		return orgdistict;
	}
	public void setOrgdistict(String orgdistict) {
		this.orgdistict = orgdistict;
	}
	public String getAddoutmpautho() {
		return addoutmpautho;
	}
	public void setAddoutmpautho(String addoutmpautho) {
		this.addoutmpautho = addoutmpautho;
	}
	public String getAuthopartyadd() {
		return authopartyadd;
	}
	public void setAuthopartyadd(String authopartyadd) {
		this.authopartyadd = authopartyadd;
	}
	public String getAuthocountry() {
		return authocountry;
	}
	public void setAuthocountry(String authocountry) {
		this.authocountry = authocountry;
	}
	public String getAuthostate() {
		return authostate;
	}
	public void setAuthostate(String authostate) {
		this.authostate = authostate;
	}
	public String getAuthodistrict() {
		return authodistrict;
	}
	public void setAuthodistrict(String authodistrict) {
		this.authodistrict = authodistrict;
	}
	public String getOrgphoneno() {
		return orgphoneno;
	}
	public void setOrgphoneno(String orgphoneno) {
		this.orgphoneno = orgphoneno;
	}
	public String getOrgmoblieno() {
		return orgmoblieno;
	}
	public void setOrgmoblieno(String orgmoblieno) {
		this.orgmoblieno = orgmoblieno;
	}



	private String efileUploadOrgView;
	//end
	
	//for party org
	private String partyorgname;
	private String authopersonname;
	private String partygender;
	private String partyrelationorg;
	private String orgaddoutmp;
	private String orgadd;
	private String orgcountry;
	private String orgstate;
	private String orgdistict;
	private String addoutmpautho;
	private String authopartyadd;
	private String authocountry;
	private String authostate;
	private String authodistrict;
	private String orgphoneno;
	private String orgmoblieno;
	
	
	
	
	
	//
	private String	partyaddorg;
	private String	partymobileorg;
	private String	partyorg;
	private String	pratyautho;
	private String	partygura;
	private String	partyauthoadd;
	private String	partyaddoutmp;
	private String	partyaddauthooutmp;
	
	//end
	
	//added for dashboard
	/*private String efileTxnId;
	private String status;
	private String excDate;
	private String toDate;*/
	
	
	/*private Object efileTxnId;
	public Object getEfileTxnId() {
		return efileTxnId;
	}
	public void setEfileTxnId(Object efileTxnId) {
		this.efileTxnId = efileTxnId;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getToDate() {
		return toDate;
	}
	public void setToDate(Object toDate) {
		this.toDate = toDate;
	}
	public Object getApplicationefileTxnId() {
		return applicationefileTxnId;
	}
	public void setApplicationefileTxnId(Object applicationefileTxnId) {
		this.applicationefileTxnId = applicationefileTxnId;
	}*/



	/*private Object status;
	private Object excDate;
	private Object toDate;
	private Object applicationefileTxnId;*/
	
	//end of dashboard
	
	//private ArrayList pendingApplicationList = new ArrayList();
	
	
	
	
	//below for print details at po end 
	
	public String getEfilepartyfirstname() {
		return efilepartyfirstname;
	}
	public void setEfilepartyfirstname(String efilepartyfirstname) {
		this.efilepartyfirstname = efilepartyfirstname;
	}
	public String getEfilepartymiddlename() {
		return efilepartymiddlename;
	}
	public void setEfilepartymiddlename(String efilepartymiddlename) {
		this.efilepartymiddlename = efilepartymiddlename;
	}
	public String getEfilepartylastname() {
		return efilepartylastname;
	}
	public void setEfilepartylastname(String efilepartylastname) {
		this.efilepartylastname = efilepartylastname;
	}
	public String getEfilepartygender() {
		return efilepartygender;
	}
	public void setEfilepartygender(String efilepartygender) {
		this.efilepartygender = efilepartygender;
	}
	public String getEfilepartyage() {
		return efilepartyage;
	}
	public void setEfilepartyage(String efilepartyage) {
		this.efilepartyage = efilepartyage;
	}
	public String getEfilepartynal() {
		return efilepartynal;
	}
	public void setEfilepartynal(String efilepartynal) {
		this.efilepartynal = efilepartynal;
	}
	public String getEfilepartypin() {
		return efilepartypin;
	}
	public void setEfilepartypin(String efilepartypin) {
		this.efilepartypin = efilepartypin;
	}
	public String getEfilepartyphno() {
		return efilepartyphno;
	}
	public void setEfilepartyphno(String efilepartyphno) {
		this.efilepartyphno = efilepartyphno;
	}
	public String getEfilepartymobno() {
		return efilepartymobno;
	}
	public void setEfilepartymobno(String efilepartymobno) {
		this.efilepartymobno = efilepartymobno;
	}
	public String getEfilepartyadd() {
		return efilepartyadd;
	}
	public void setEfilepartyadd(String efilepartyadd) {
		this.efilepartyadd = efilepartyadd;
	}
	public String getEfilepartydist() {
		return efilepartydist;
	}
	public void setEfilepartydist(String efilepartydist) {
		this.efilepartydist = efilepartydist;
	}
	public String getEfilepartystate() {
		return efilepartystate;
	}
	public void setEfilepartystate(String efilepartystate) {
		this.efilepartystate = efilepartystate;
	}
	public String getEfilepartycontry() {
		return efilepartycontry;
	}
	public void setEfilepartycontry(String efilepartycontry) {
		this.efilepartycontry = efilepartycontry;
	}
	public String getEfilepartyorgname() {
		return efilepartyorgname;
	}
	public void setEfilepartyorgname(String efilepartyorgname) {
		this.efilepartyorgname = efilepartyorgname;
	}
	public String getEfilepartyauthname() {
		return efilepartyauthname;
	}
	public void setEfilepartyauthname(String efilepartyauthname) {
		this.efilepartyauthname = efilepartyauthname;
	}
	public String getEfilepartyguraname() {
		return efilepartyguraname;
	}
	public void setEfilepartyguraname(String efilepartyguraname) {
		this.efilepartyguraname = efilepartyguraname;
	}
	public String getEfilepartymothername() {
		return efilepartymothername;
	}
	public void setEfilepartymothername(String efilepartymothername) {
		this.efilepartymothername = efilepartymothername;
	}
	public String getEfilepartyemail() {
		return efilepartyemail;
	}
	public void setEfilepartyemail(String efilepartyemail) {
		this.efilepartyemail = efilepartyemail;
	}
	public String getNameofOffical() {
		return nameofOffical;
	}
	public void setNameofOffical(String nameofOffical) {
		this.nameofOffical = nameofOffical;
	}
	public String getDesgofOffical() {
		return desgofOffical;
	}
	public void setDesgofOffical(String desgofOffical) {
		this.desgofOffical = desgofOffical;
	}
	public String getAddofOffical() {
		return addofOffical;
	}
	public void setAddofOffical(String addofOffical) {
		this.addofOffical = addofOffical;
	}
	public String getAddOutMp() {
		return addOutMp;
	}
	public void setAddOutMp(String addOutMp) {
		this.addOutMp = addOutMp;
	}
	public String getDistricttype() {
		return districttype;
	}
	public void setDistricttype(String districttype) {
		this.districttype = districttype;
	}
	public String getStatetype() {
		return statetype;
	}
	public void setStatetype(String statetype) {
		this.statetype = statetype;
	}
	public String getCountrytype() {
		return countrytype;
	}
	public void setCountrytype(String countrytype) {
		this.countrytype = countrytype;
	}


   private String DEEDNAME;
	private String 	EFILETXNID;
	private String EFILEDATE;
	private String EXCDATE;
	private String DATEOFSUBMISSIONPO;
	private String SRNAME;
	private String SUBREGISTRAROFFICE;
	private String STAMPDUTY;
	private String PURLOAN;
	private String ECODE;
	private String partytxnid;
	private String APPELLATETYPENAME;
	private String partyfirstname;
	private String PARTYLASTNAME;
	private String PARTYADDRESS;
	private String TypePropertyEFile;
	private String AREAEFILE;
	private String filedValue;
	public String getFiledValue() {
		return filedValue;
	}
	public void setFiledValue(String filedValue) {
		this.filedValue = filedValue;
	}
	public String getEFILEDATE() {
		return EFILEDATE;
	}
	public void setEFILEDATE(String efiledate) {
		EFILEDATE = efiledate;
	}
	public String getEXCDATE() {
		return EXCDATE;
	}
	public void setEXCDATE(String excdate) {
		EXCDATE = excdate;
	}
	public String getDATEOFSUBMISSIONPO() {
		return DATEOFSUBMISSIONPO;
	}
	public void setDATEOFSUBMISSIONPO(String dateofsubmissionpo) {
		DATEOFSUBMISSIONPO = dateofsubmissionpo;
	}
	public String getSRNAME() {
		return SRNAME;
	}
	public void setSRNAME(String srname) {
		SRNAME = srname;
	}
	public String getSUBREGISTRAROFFICE() {
		return SUBREGISTRAROFFICE;
	}
	public void setSUBREGISTRAROFFICE(String subregistraroffice) {
		SUBREGISTRAROFFICE = subregistraroffice;
	}
	public String getSTAMPDUTY() {
		return STAMPDUTY;
	}
	public void setSTAMPDUTY(String stampduty) {
		STAMPDUTY = stampduty;
	}
	public String getPartyfirstname() {
		return partyfirstname;
	}
	public void setPartyfirstname(String partyfirstname) {
		this.partyfirstname = partyfirstname;
	}
	public String getPARTYLASTNAME() {
		return PARTYLASTNAME;
	}
	public void setPARTYLASTNAME(String partylastname) {
		PARTYLASTNAME = partylastname;
	}
	public String getPARTYADDRESS() {
		return PARTYADDRESS;
	}
	public void setPARTYADDRESS(String partyaddress) {
		PARTYADDRESS = partyaddress;
	}
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bankname) {
		BANKNAME = bankname;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String address) {
		ADDRESS = address;
	}
	public String getBANKAUTHORITYNAME() {
		return BANKAUTHORITYNAME;
	}
	public void setBANKAUTHORITYNAME(String bankauthorityname) {
		BANKAUTHORITYNAME = bankauthorityname;
	}
	public String getBANKAUTHORITYDESIGNATION() {
		return BANKAUTHORITYDESIGNATION;
	}
	public void setBANKAUTHORITYDESIGNATION(String bankauthoritydesignation) {
		BANKAUTHORITYDESIGNATION = bankauthoritydesignation;
	}
	public String getPROPERTYID() {
		return PROPERTYID;
	}
	public void setPROPERTYID(String propertyid) {
		PROPERTYID = propertyid;
	}
	public String getPROPERTYADDRESS() {
		return PROPERTYADDRESS;
	}
	public void setPROPERTYADDRESS(String propertyaddress) {
		PROPERTYADDRESS = propertyaddress;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}



	private String BANKNAME;
	private String ADDRESS;
	private String BANKAUTHORITYNAME;
	private String BANKAUTHORITYDESIGNATION;
	private String PROPERTYID;
	private String PROPERTYVALID;
	private String PROPERTYADDRESS;
	private String areaType;
	
	//end for print details at po end 
	
	private String statusSearch;
	private String duration;
	private String efilingNo;
	
	
	public String getStatusSearch() {
		return statusSearch;
	}
	public void setStatusSearch(String statusSearch) {
		this.statusSearch = statusSearch;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEfilingNo() {
		return efilingNo;
	}
	public void setEfilingNo(String efilingNo) {
		this.efilingNo = efilingNo;
	}



	private String radioResi;
	private String radioResi2;
	private String radioResi1;
	
	
	public String getRadioResi2() {
		return radioResi2;
	}
	public void setRadioResi2(String radioResi2) {
		this.radioResi2 = radioResi2;
	}
	public String getRadioResi1() {
		return radioResi1;
	}
	public void setRadioResi1(String radioResi1) {
		this.radioResi1 = radioResi1;
	}
	public String getRadioResi() {
		return radioResi;
	}
	public void setRadioResi(String radioResi) {
		this.radioResi = radioResi;
	}



	//below for SR upload
	private String remarks;
	private FormFile propImage1;
	
	public FormFile getPropImage1() {
		return propImage1;
	}
	public void setPropImage1(FormFile propImage1) {
		this.propImage1 = propImage1;
	}



	//below for propety details
	private String distEfileName;
	private String uploadPathSR;
	private String uploadView;
	public String getDistEfileName() {
		return distEfileName;
	}
	public void setDistEfileName(String distEfileName) {
		this.distEfileName = distEfileName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public String getAreaEfileType() {
		return areaEfileType;
	}
	public void setAreaEfileType(String areaEfileType) {
		this.areaEfileType = areaEfileType;
	}
	public String getPropertyEfileId() {
		return propertyEfileId;
	}
	public void setPropertyEfileId(String propertyEfileId) {
		this.propertyEfileId = propertyEfileId;
	}
	public String getVikasKhand() {
		return vikasKhand;
	}
	public void setVikasKhand(String vikasKhand) {
		this.vikasKhand = vikasKhand;
	}
	public String getRiCircle() {
		return riCircle;
	}
	public void setRiCircle(String riCircle) {
		this.riCircle = riCircle;
	}
	public String getLayoutDtls() {
		return layoutDtls;
	}
	public void setLayoutDtls(String layoutDtls) {
		this.layoutDtls = layoutDtls;
	}
	public String getNazzolSheetNo() {
		return nazzolSheetNo;
	}
	public void setNazzolSheetNo(String nazzolSheetNo) {
		this.nazzolSheetNo = nazzolSheetNo;
	}
	public String getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}
	public String getPropadd() {
		return propadd;
	}
	public void setPropadd(String propadd) {
		this.propadd = propadd;
	}
	public String getEastBound() {
		return eastBound;
	}
	public void setEastBound(String eastBound) {
		this.eastBound = eastBound;
	}
	public String getWestBound() {
		return westBound;
	}
	public void setWestBound(String westBound) {
		this.westBound = westBound;
	}
	public String getNorthBound() {
		return northBound;
	}
	public void setNorthBound(String northBound) {
		this.northBound = northBound;
	}
	public String getSouthBound() {
		return southBound;
	}
	public void setSouthBound(String southBound) {
		this.southBound = southBound;
	}


	private String fileSizeUploadError;
	private String tehsilName;
	private String colonyName;
    private String areaEfileType;
	private String propertyEfileId;
	private String propertyEfileValId;
	private String vikasKhand;
	private String riCircle;
	private String layoutDtls;
	private String nazzolSheetNo;
	private String plotNo;
	private String propadd;
	private String propType;
	private String khasraNo;
	private String lagaan;
	private String rinNo;
	private String khasraArea;
	private String eastBound;
	private String westBound;
	private String northBound;
	private String southBound;
	private String propertyUpload1;
	private String propertyUpload2;
	private String propertyUpload3;
	private String propertyUpload4;
	private String propertyUpload5;
	private String propertyUpload6;
	private String propertyUploadFlag1;
	private String propertyUploadFlag2;
	private String propertyUploadFlag3;
	private String propertyUploadFlag4;
	private String propertyUploadFlag5;
	private String propertyUploadFlag6;
	
	//end of property detasils
	
	
	public String getPropertyUploadFlag1() {
		return propertyUploadFlag1;
	}
	public void setPropertyUploadFlag1(String propertyUploadFlag1) {
		this.propertyUploadFlag1 = propertyUploadFlag1;
	}
	public String getPropertyUploadFlag2() {
		return propertyUploadFlag2;
	}
	public void setPropertyUploadFlag2(String propertyUploadFlag2) {
		this.propertyUploadFlag2 = propertyUploadFlag2;
	}
	public String getPropertyUploadFlag3() {
		return propertyUploadFlag3;
	}
	public void setPropertyUploadFlag3(String propertyUploadFlag3) {
		this.propertyUploadFlag3 = propertyUploadFlag3;
	}
	public String getPropertyUploadFlag4() {
		return propertyUploadFlag4;
	}
	public void setPropertyUploadFlag4(String propertyUploadFlag4) {
		this.propertyUploadFlag4 = propertyUploadFlag4;
	}
	public String getPropertyUploadFlag5() {
		return propertyUploadFlag5;
	}
	public void setPropertyUploadFlag5(String propertyUploadFlag5) {
		this.propertyUploadFlag5 = propertyUploadFlag5;
	}
	public String getPropertyUploadFlag6() {
		return propertyUploadFlag6;
	}
	public void setPropertyUploadFlag6(String propertyUploadFlag6) {
		this.propertyUploadFlag6 = propertyUploadFlag6;
	}
	public String getPropertyUpload1() {
		return propertyUpload1;
	}
	public void setPropertyUpload1(String propertyUpload1) {
		this.propertyUpload1 = propertyUpload1;
	}
	public String getPropertyUpload2() {
		return propertyUpload2;
	}
	public void setPropertyUpload2(String propertyUpload2) {
		this.propertyUpload2 = propertyUpload2;
	}
	public String getPropertyUpload3() {
		return propertyUpload3;
	}
	public void setPropertyUpload3(String propertyUpload3) {
		this.propertyUpload3 = propertyUpload3;
	}
	public String getPropertyUpload4() {
		return propertyUpload4;
	}
	public void setPropertyUpload4(String propertyUpload4) {
		this.propertyUpload4 = propertyUpload4;
	}
	public String getPropertyUpload5() {
		return propertyUpload5;
	}
	public void setPropertyUpload5(String propertyUpload5) {
		this.propertyUpload5 = propertyUpload5;
	}
	public String getPropertyUpload6() {
		return propertyUpload6;
	}
	public void setPropertyUpload6(String propertyUpload6) {
		this.propertyUpload6 = propertyUpload6;
	}



	//below array list for party deatils
	private ArrayList partyListSelected= new ArrayList();
	private ArrayList partyListSelected1= new ArrayList();
	
	private ArrayList getpartyPrintDetails=new ArrayList();
	private ArrayList getpartyPrintDetailsGov=new ArrayList();
	private ArrayList getpartyPrintDetailsInd=new ArrayList();
	private ArrayList getpartyPrintDetailsIndPOA=new ArrayList();
	private ArrayList getpartyPrintDetailsOrgPOA=new ArrayList();
	private ArrayList getpartyPrintDetailsOrg=new ArrayList();
	private ArrayList getpartyPrintDetailsPOA=new ArrayList();
	public ArrayList getGetpartyPrintDetailsGov() {
		return getpartyPrintDetailsGov;
	}
	public void setGetpartyPrintDetailsGov(ArrayList getpartyPrintDetailsGov) {
		this.getpartyPrintDetailsGov = getpartyPrintDetailsGov;
	}
	public ArrayList getGetpartyPrintDetailsInd() {
		return getpartyPrintDetailsInd;
	}
	public void setGetpartyPrintDetailsInd(ArrayList getpartyPrintDetailsInd) {
		this.getpartyPrintDetailsInd = getpartyPrintDetailsInd;
	}
	public ArrayList getGetpartyPrintDetailsOrg() {
		return getpartyPrintDetailsOrg;
	}
	public void setGetpartyPrintDetailsOrg(ArrayList getpartyPrintDetailsOrg) {
		this.getpartyPrintDetailsOrg = getpartyPrintDetailsOrg;
	}
	public ArrayList getGetpartyPrintDetailsPOA() {
		return getpartyPrintDetailsPOA;
	}
	public void setGetpartyPrintDetailsPOA(ArrayList getpartyPrintDetailsPOA) {
		this.getpartyPrintDetailsPOA = getpartyPrintDetailsPOA;
	}



	private ArrayList getpropPrintDetails=new ArrayList();
	private ArrayList exemptionListEfileSelected= new ArrayList();
	private ArrayList exemptionListEfileSelected4= new ArrayList();
	private ArrayList ownerListEfileSelected= new ArrayList();
	private ArrayList phyListEfileSelected= new ArrayList();
	
	private String exempflag;
	private String exempflag4;
	private String exemptionEfile;
	private String applicantTypeEfile;
	private String partyTypeEfile;
	private String partyIdEfile;
	private String idparty;
	
	private String applicantTypeEfile1;
	private String partyTypeEfile1;
	private String partyIdEfile1;
	private String popropflag;
	private String popropflag1;
	//end
	
	//below for sr dashboard duty display
	
	public String getApplicantTypeEfile1() {
		return applicantTypeEfile1;
	}
	public void setApplicantTypeEfile1(String applicantTypeEfile1) {
		this.applicantTypeEfile1 = applicantTypeEfile1;
	}
	public String getPartyTypeEfile1() {
		return partyTypeEfile1;
	}
	public void setPartyTypeEfile1(String partyTypeEfile1) {
		this.partyTypeEfile1 = partyTypeEfile1;
	}
	public String getPartyIdEfile1() {
		return partyIdEfile1;
	}
	public void setPartyIdEfile1(String partyIdEfile1) {
		this.partyIdEfile1 = partyIdEfile1;
	}
	public String getApplicantTypeEfile() {
		return applicantTypeEfile;
	}
	public void setApplicantTypeEfile(String applicantTypeEfile) {
		this.applicantTypeEfile = applicantTypeEfile;
	}
	public String getPartyTypeEfile() {
		return partyTypeEfile;
	}
	public void setPartyTypeEfile(String partyTypeEfile) {
		this.partyTypeEfile = partyTypeEfile;
	}
	public String getPartyIdEfile() {
		return partyIdEfile;
	}
	public void setPartyIdEfile(String partyIdEfile) {
		this.partyIdEfile = partyIdEfile;
	}



	private String deedNameEfile;
	private String instNameEfile;
	private String displaypurploan;
	private String displaypropoutMP;
	private String displaypropoutMPFlag;
	private String displaypropoutMP1;
	private String displaypropoutMPFlag1;
	public String getDisplaypropoutMP1() {
		return displaypropoutMP1;
	}
	public void setDisplaypropoutMP1(String displaypropoutMP1) {
		this.displaypropoutMP1 = displaypropoutMP1;
	}



	private String stampDutyEfile;
	private String munDUTYEfile;
	private String blockDutyEfile;
	private String upkarDutyEfile;
	
	private String estampCodeDisplay;
	public String getEstampCodeDisplay() {
		return estampCodeDisplay;
	}
	public void setEstampCodeDisplay(String estampCodeDisplay) {
		this.estampCodeDisplay = estampCodeDisplay;
	}
	public String getEstampCodeStatus() {
		return estampCodeStatus;
	}
	public void setEstampCodeStatus(String estampCodeStatus) {
		this.estampCodeStatus = estampCodeStatus;
	}



	private String estampCodeStatus;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCotry() {
		return cotry;
	}
	public void setCotry(String cotry) {
		this.cotry = cotry;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	public String getBankauthoname() {
		return bankauthoname;
	}
	public void setBankauthoname(String bankauthoname) {
		this.bankauthoname = bankauthoname;
	}
	public String getBankauthodeg() {
		return bankauthodeg;
	}
	public void setBankauthodeg(String bankauthodeg) {
		this.bankauthodeg = bankauthodeg;
	}
	public String getBankauthophno() {
		return bankauthophno;
	}
	public void setBankauthophno(String bankauthophno) {
		this.bankauthophno = bankauthophno;
	}
	public String getBankauthoemail() {
		return bankauthoemail;
	}
	public void setBankauthoemail(String bankauthoemail) {
		this.bankauthoemail = bankauthoemail;
	}
	public String getCreateddate() {
		return createddate;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}



	private String regfeeEfile;
	private String dutypaidEfile;
	private String totalpayableduty;
	private String totalAfterexmpEfile;
	
	private String bank;
	private String address;
	private String cotry;
	private String state;
	private String dis;
	private String pincode;
	private String phnumber;
	private String bankauthoname;
	private String bankauthodeg;
	private String bankauthophno;
	private String bankauthoemail;
	private String createddate;
	
	
	private String bankdepositedeed;
	private String branchdepositdeed;
	private String bankaddressdeed;
	private String bankauthdeed;
	private String loanamt;
	private String sanamt;
	
	private String withpossession;
	private String securedamt;
	
	private String extraflag;
	private String extraflag1;
	//end
	
	public String getBankdepositedeed() {
		return bankdepositedeed;
	}
	public void setBankdepositedeed(String bankdepositedeed) {
		this.bankdepositedeed = bankdepositedeed;
	}
	public String getBranchdepositdeed() {
		return branchdepositdeed;
	}
	public void setBranchdepositdeed(String branchdepositdeed) {
		this.branchdepositdeed = branchdepositdeed;
	}
	public String getBankaddressdeed() {
		return bankaddressdeed;
	}
	public void setBankaddressdeed(String bankaddressdeed) {
		this.bankaddressdeed = bankaddressdeed;
	}
	public String getBankauthdeed() {
		return bankauthdeed;
	}
	public void setBankauthdeed(String bankauthdeed) {
		this.bankauthdeed = bankauthdeed;
	}
	public String getLoanamt() {
		return loanamt;
	}
	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
	}
	public String getSanamt() {
		return sanamt;
	}
	public void setSanamt(String sanamt) {
		this.sanamt = sanamt;
	}
	public String getDeedNameEfile() {
		return deedNameEfile;
	}
	public void setDeedNameEfile(String deedNameEfile) {
		this.deedNameEfile = deedNameEfile;
	}
	public String getInstNameEfile() {
		return instNameEfile;
	}
	public void setInstNameEfile(String instNameEfile) {
		this.instNameEfile = instNameEfile;
	}
	public String getStampDutyEfile() {
		return stampDutyEfile;
	}
	public void setStampDutyEfile(String stampDutyEfile) {
		this.stampDutyEfile = stampDutyEfile;
	}
	public String getMunDUTYEfile() {
		return munDUTYEfile;
	}
	public void setMunDUTYEfile(String munDUTYEfile) {
		this.munDUTYEfile = munDUTYEfile;
	}
	public String getBlockDutyEfile() {
		return blockDutyEfile;
	}
	public void setBlockDutyEfile(String blockDutyEfile) {
		this.blockDutyEfile = blockDutyEfile;
	}
	public String getUpkarDutyEfile() {
		return upkarDutyEfile;
	}
	public void setUpkarDutyEfile(String upkarDutyEfile) {
		this.upkarDutyEfile = upkarDutyEfile;
	}
	public String getRegfeeEfile() {
		return regfeeEfile;
	}
	public void setRegfeeEfile(String regfeeEfile) {
		this.regfeeEfile = regfeeEfile;
	}
	public String getDutypaidEfile() {
		return dutypaidEfile;
	}
	public void setDutypaidEfile(String dutypaidEfile) {
		this.dutypaidEfile = dutypaidEfile;
	}
	public String getTotalAfterexmpEfile() {
		return totalAfterexmpEfile;
	}
	public void setTotalAfterexmpEfile(String totalAfterexmpEfile) {
		this.totalAfterexmpEfile = totalAfterexmpEfile;
	}

private String physicalStampCode;
private String srStampVendorCode;
private String srSeriesNo;
private String srDateOfStamp;

	private Object efileTxnId;
	public Object getEfileTxnId() {
		return efileTxnId;
	}
	public void setEfileTxnId(Object efileTxnId) {
		this.efileTxnId = efileTxnId;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getExcDate() {
		return excDate;
	}
	public void setExcDate(Object excDate) {
		this.excDate = excDate;
	}
	public Object getToDate() {
		return toDate;
	}
	public void setToDate(Object toDate) {
		this.toDate = toDate;
	}
	public Object getApplicationefileTxnId() {
		return applicationefileTxnId;
	}
	public void setApplicationefileTxnId(Object applicationefileTxnId) {
		this.applicationefileTxnId = applicationefileTxnId;
	}



	private Object status;
	private Object excDate;
	private Object toDate;
	private Object applicationefileTxnId;
	
	private String slotdate;
	public String getSlotdate() {
		return slotdate;
	}
	public void setSlotdate(String slotdate) {
		this.slotdate = slotdate;
	}
	public String getTodate() {
		return todate;
	}
	public void setTodate(String todate) {
		this.todate = todate;
	}
	public String getRadioResiComm() {
		return radioResiComm;
	}
	public void setRadioResiComm(String radioResiComm) {
		this.radioResiComm = radioResiComm;
	}
	public String getRadioResiComm1() {
		return radioResiComm1;
	}
	public void setRadioResiComm1(String radioResiComm1) {
		this.radioResiComm1 = radioResiComm1;
	}

	
	
	private String todate;
	private String radioResiComm;
	private String radioResiComm1;
	private String addressIndOutMp;
	private String addressOrgOutMp;
	private String addressAuthPerOutMp;
	private String addressOwnerOutMp;
	private String addressGovtOffclOutMp;
	
	private String addressIndOutMpTrns;
	private String addressOrgOutMpTrns;
	private String addressAuthPerOutMpTrns;
	private String addressOwnerOutMpTrns;
	private String addressGovtOffclOutMpTrns;
	
	private String totalConsenterConsid;
	
	private HashMap appOwnerList=new HashMap();
	private HashMap trnsOwnerList=new HashMap();
	private String deleteOwnerID;
	private String hdnDeleteOwnerID;
	
	private String displayOnwerPartyKey;
	
	
	private ArrayList mineralListSelected= new ArrayList();
	private String mineralSelected;
	
	private double miningDuration;
	
	public String getDisplayOnwerPartyKey() {
		return displayOnwerPartyKey;
	}
	public void setDisplayOnwerPartyKey(String displayOnwerPartyKey) {
		this.displayOnwerPartyKey = displayOnwerPartyKey;
	}
	public String getHdnDeleteOwnerID() {
		return hdnDeleteOwnerID;
	}
	public void setHdnDeleteOwnerID(String hdnDeleteOwnerID) {
		this.hdnDeleteOwnerID = hdnDeleteOwnerID;
	}
	public String getDeleteOwnerID() {
		return deleteOwnerID;
	}
	public void setDeleteOwnerID(String deleteOwnerID) {
		this.deleteOwnerID = deleteOwnerID;
	}
	public HashMap getAppOwnerList() {
		return appOwnerList;
	}
	public void setAppOwnerList(HashMap appOwnerList) {
		this.appOwnerList = appOwnerList;
	}
	public HashMap getTrnsOwnerList() {
		return trnsOwnerList;
	}
	public void setTrnsOwnerList(HashMap trnsOwnerList) {
		this.trnsOwnerList = trnsOwnerList;
	}
	public String getTotalConsenterConsid() {
		return totalConsenterConsid;
	}
	public void setTotalConsenterConsid(String totalConsenterConsid) {
		this.totalConsenterConsid = totalConsenterConsid;
	}
	public String getAddressIndOutMp() {
		return addressIndOutMp;
	}
	public void setAddressIndOutMp(String addressIndOutMp) {
		this.addressIndOutMp = addressIndOutMp;
	}
	public String getAddressOrgOutMp() {
		return addressOrgOutMp;
	}
	public void setAddressOrgOutMp(String addressOrgOutMp) {
		this.addressOrgOutMp = addressOrgOutMp;
	}
	public String getAddressAuthPerOutMp() {
		return addressAuthPerOutMp;
	}
	public void setAddressAuthPerOutMp(String addressAuthPerOutMp) {
		this.addressAuthPerOutMp = addressAuthPerOutMp;
	}
	public String getAddressOwnerOutMp() {
		return addressOwnerOutMp;
	}
	public void setAddressOwnerOutMp(String addressOwnerOutMp) {
		this.addressOwnerOutMp = addressOwnerOutMp;
	}
	public String getAddressGovtOffclOutMp() {
		return addressGovtOffclOutMp;
	}
	public void setAddressGovtOffclOutMp(String addressGovtOffclOutMp) {
		this.addressGovtOffclOutMp = addressGovtOffclOutMp;
	}
	public String getAddressIndOutMpTrns() {
		return addressIndOutMpTrns;
	}
	public void setAddressIndOutMpTrns(String addressIndOutMpTrns) {
		this.addressIndOutMpTrns = addressIndOutMpTrns;
	}
	public String getAddressOrgOutMpTrns() {
		return addressOrgOutMpTrns;
	}
	public void setAddressOrgOutMpTrns(String addressOrgOutMpTrns) {
		this.addressOrgOutMpTrns = addressOrgOutMpTrns;
	}
	public String getAddressAuthPerOutMpTrns() {
		return addressAuthPerOutMpTrns;
	}
	public void setAddressAuthPerOutMpTrns(String addressAuthPerOutMpTrns) {
		this.addressAuthPerOutMpTrns = addressAuthPerOutMpTrns;
	}
	public String getAddressOwnerOutMpTrns() {
		return addressOwnerOutMpTrns;
	}
	public void setAddressOwnerOutMpTrns(String addressOwnerOutMpTrns) {
		this.addressOwnerOutMpTrns = addressOwnerOutMpTrns;
	}
	public String getAddressGovtOffclOutMpTrns() {
		return addressGovtOffclOutMpTrns;
	}
	public void setAddressGovtOffclOutMpTrns(String addressGovtOffclOutMpTrns) {
		this.addressGovtOffclOutMpTrns = addressGovtOffclOutMpTrns;
	}

	private String deedPath;
	private byte[] deedContents;
	
	private String purposeAddedFlag;
	
	private String appStatus;
	private String appStatusEfile;
	private String termsCondPartyApp;
	private String termsCondPartyTrns;
	private String termsCondProp;
	
	private String consenterFlag;
	private String consenterWithConsidFlag;
	private int consenterCount;
	private int consenterAddedCount;
	private HashMap dtlsMapConsntr = new HashMap();
	private String consenterId;
	private String deleteConsenter;
	private String hdnDeleteConsenter;
	private ArrayList consenterWiseDutyList= new ArrayList();
	private String familyFlag;
	
	
	
	public String getConsenterWithConsidFlag() {
		return consenterWithConsidFlag;
	}
	public void setConsenterWithConsidFlag(String consenterWithConsidFlag) {
		this.consenterWithConsidFlag = consenterWithConsidFlag;
	}
	public String getFamilyFlag() {
		return familyFlag;
	}
	public void setFamilyFlag(String familyFlag) {
		this.familyFlag = familyFlag;
	}
	public ArrayList getConsenterWiseDutyList() {
		return consenterWiseDutyList;
	}
	public void setConsenterWiseDutyList(ArrayList consenterWiseDutyList) {
		this.consenterWiseDutyList = consenterWiseDutyList;
	}
	public String getHdnDeleteConsenter() {
		return hdnDeleteConsenter;
	}
	public void setHdnDeleteConsenter(String hdnDeleteConsenter) {
		this.hdnDeleteConsenter = hdnDeleteConsenter;
	}
	public String getDeleteConsenter() {
		return deleteConsenter;
	}
	public void setDeleteConsenter(String deleteConsenter) {
		this.deleteConsenter = deleteConsenter;
	}
	public String getConsenterId() {
		return consenterId;
	}
	public void setConsenterId(String consenterId) {
		this.consenterId = consenterId;
	}
	public HashMap getDtlsMapConsntr() {
		return dtlsMapConsntr;
	}
	public void setDtlsMapConsntr(HashMap dtlsMapConsntr) {
		this.dtlsMapConsntr = dtlsMapConsntr;
	}
	public int getConsenterAddedCount() {
		return consenterAddedCount;
	}
	public void setConsenterAddedCount(int consenterAddedCount) {
		this.consenterAddedCount = consenterAddedCount;
	}
	public String getConsenterFlag() {
		return consenterFlag;
	}
	public void setConsenterFlag(String consenterFlag) {
		this.consenterFlag = consenterFlag;
	}
	public int getConsenterCount() {
		return consenterCount;
	}
	public void setConsenterCount(int consenterCount) {
		this.consenterCount = consenterCount;
	}
	public String getTermsCondPartyTrns() {
		return termsCondPartyTrns;
	}
	public void setTermsCondPartyTrns(String termsCondPartyTrns) {
		this.termsCondPartyTrns = termsCondPartyTrns;
	}
	public String getTermsCondProp() {
		return termsCondProp;
	}
	public void setTermsCondProp(String termsCondProp) {
		this.termsCondProp = termsCondProp;
	}
	public String getTermsCondPartyApp() {
		return termsCondPartyApp;
	}
	public void setTermsCondPartyApp(String termsCondPartyApp) {
		this.termsCondPartyApp = termsCondPartyApp;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getPurposeAddedFlag() {
		return purposeAddedFlag;
	}
	public void setPurposeAddedFlag(String purposeAddedFlag) {
		this.purposeAddedFlag = purposeAddedFlag;
	}
	public String getDeedPath() {
		return deedPath;
	}
	public void setDeedPath(String deedPath) {
		this.deedPath = deedPath;
	}
	public byte[] getDeedContents() {
		return deedContents;
	}
	public void setDeedContents(byte[] deedContents) {
		this.deedContents = deedContents;
	}

	//common for both
	private String listAdoptedParty;

	private String actionName;
	private String formName;
	
	//By Mohit for multipleuploads
	private FormFile fileAUPath = null;
	private String fileAUName;
	private String uniqueUploadId;
	private String checkBoxVerified;
	private String transCheck ;
	
	private FormFile fileAUPathAdju = null;
	private String fileAUNameAdju;
	private String uniqueUploadIdAdju;
	
	
	private FormFile fileAUPathP = null;
	private String fileAUNameP;
	private String uniqueUploadIdP;
	
	
	
	public FormFile getFileAUPathAdju() {
		return fileAUPathAdju;
	}
	public void setFileAUPathAdju(FormFile fileAUPathAdju) {
		this.fileAUPathAdju = fileAUPathAdju;
	}
	public String getFileAUNameAdju() {
		return fileAUNameAdju;
	}
	public void setFileAUNameAdju(String fileAUNameAdju) {
		this.fileAUNameAdju = fileAUNameAdju;
	}
	public String getUniqueUploadIdAdju() {
		return uniqueUploadIdAdju;
	}
	public void setUniqueUploadIdAdju(String uniqueUploadIdAdju) {
		this.uniqueUploadIdAdju = uniqueUploadIdAdju;
	}
	public String getFileAUNameP() {
		return fileAUNameP;
	}
	public void setFileAUNameP(String fileAUNameP) {
		this.fileAUNameP = fileAUNameP;
	}
	public String getUniqueUploadIdP() {
		return uniqueUploadIdP;
	}
	public void setUniqueUploadIdP(String uniqueUploadIdP) {
		this.uniqueUploadIdP = uniqueUploadIdP;
	}
	public FormFile getFileAUPathP() {
		return fileAUPathP;
	}
	public void setFileAUPathP(FormFile fileAUPathP) {
		this.fileAUPathP = fileAUPathP;
	}
	public ArrayList<CommonDTO> getListDtoP() {
		return listDtoP;
	}
	public void setListDtoP(ArrayList<CommonDTO> listDtoP) {
		this.listDtoP = listDtoP;
	}
	
	
	public ArrayList<CommonDTO> getListDtoAdju() {
		return listDtoAdju;
	}
	public void setListDtoAdju(ArrayList<CommonDTO> listDtoAdju) {
		this.listDtoAdju = listDtoAdju;
	}

	private ArrayList<CommonDTO> listDtoAdju = new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> listDto = new ArrayList<CommonDTO>();
	private ArrayList<CommonDTO> listDtoP = new ArrayList<CommonDTO>();
	private ArrayList<DutyCalculationDTO> physicalStampList = new ArrayList<DutyCalculationDTO>();
	
	



	//organization details
	private String ogrName;
	private String authPerName;
	private String orgaddress;
	private String country;
	private String statename;
	private String district;
	private String phno;
	private String mobno;
	
	//following for relationship for organization
	private String authPerAddress;
	private String authPerCountry;
	private String authPerStatename;
	private String authPerDistrict;
	
	private String authPerFatherName;
	private int authPerRelationship;
	private String authPerGendar = "M";
	
	private String authPerAddressTrns;
	private String authPerCountryTrns;
	private String authPerStatenameTrns;
	private String authPerDistrictTrns;
	
	private String authPerFatherNameTrns;
	private int authPerRelationshipTrns;
	private String authPerGendarTrns = "M";
	
	private String authPerCountryName;
	private String authPerStatenameName;
	private String authPerDistrictName;
	
	private String authPerCountryNameTrns;
	private String authPerStatenameNameTrns;
	private String authPerDistrictNameTrns;
	
	//end of variables for relationship for organization
	
	//individual details
	private String fname;
	private String mname; 
	private String lname;
	private String gendar = "M";
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String nationality;
	private String postalCode;
	private String emailID;
	private String indaddress;
	private String indcountry;
	private String indstatename;
	private String inddistrict;
	private String indphno;
	private String indmobno;
	private String listID;
	private String idno;
	private String bname;
	private String baddress;
	private String indtehsil;
	public String getIndtehsil() {
		return indtehsil;
	}
	public void setIndtehsil(String indtehsil) {
		this.indtehsil = indtehsil;
	}

	// Minor Guardian - Rahul 
	private String guardian;
	
	public String getGuardian() {
		return guardian;
	}
	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}

	//common for both
	private String tparties;
	private String organisation;
	private String deedType;
	private String marketValue;
	private double consiAmount=0.00;
	private String purpose;
	private String instrument;
	private String exemption;
	private String stampDuty = "0";
	private String others = "0";
	private String total = "0";
	private String page = "success";
	private RegCommonDTO commonDto;
	private RegCompletDTO regcompletDto = new RegCompletDTO();
	private PropertyValuationDTO propertyDTO = 
						new PropertyValuationDTO();
	private RegCompleteMakerDTO regDTO = new RegCompleteMakerDTO();
	private ArrayList selectedSubclause = new ArrayList ();
	private HashMap mapBuildingDetails = new HashMap();
	private HashMap selectedMap = new HashMap();
	private String payID;
	
	//Following added by Roopam for deed selection page.
	private ArrayList deedList = new ArrayList();
	private ArrayList instrumentList = new ArrayList();
	private ArrayList exemptionList = new ArrayList();
	
	
	//common transacting party details
	private String listAdoptedPartyTrns;
	private String listAdoptedPartyNameTrns;
	private String purposeTrns;
	
	
	//transacting party details for individual
	private String fnameTrns;
	private String mnameTrns; 
	private String lnameTrns;
	private String gendarTrns = "M";
	private String ageTrns;
	private String fatherNameTrns;
	private String motherNameTrns;
	private String spouseNameTrns;
	private String nationalityTrns;
	private String postalCodeTrns;
	private String emailIDTrns;
	private String indaddressTrns;
	private String indcountryTrns;
	private String indstatenameTrns;
	private String inddistrictTrns;
	private String indtehsilmodify;
	private String indtehsilmodify1;
	private String indcountryNameTrns;
	private String indstatenameNameTrns;
	private String inddistrictNameTrns;
	private String indphnoTrns;
	private String indmobnoTrns;
	private String listIDTrns;
	private String listIDNameTrns;
	private String idnoTrns;
	private String bnameTrns;
	private String baddressTrns;
	// new field of minor party -- Rahul
	private String guardianTrans;
	
	
	public String getGuardianTrans() {
		return guardianTrans;
	}
	public void setGuardianTrans(String guardianTrans) {
		this.guardianTrans = guardianTrans;
	}

	//transacting party details for organization
	private String ogrNameTrns;
	private String authPerNameTrns;
	private String orgaddressTrns;
	private String countryTrns;
	private String statenameTrns;
	private String districtTrns;
	private String countryNameTrns;
	private String statenameNameTrns;
	private String districtNameTrns;
	
	private String phnoTrns;
	private String mobnoTrns;
	private String marketValueTrns;
	private String consiAmountTrns;
	
	private int addMoreCounter;
	private HashMap mapTransactingParties = new HashMap();
	private String deleteTrnsPrtyID;
	
	//new
	private String indReligion;      //varchar   y
	private String indCaste;         //varchar   y
	private String indDisability="N";    //varchar
	private String hdnIndDisability;    //varchar
	private String hdnIndDisabilityTrns;
	private String partyType;                  //Y
	private String ownershipShare;
	private String relationWithOwner;//varchar
	private String[] shareOfProp;
	private String executionDate;    //date      Y
	private String presentationDate; //date
	private String existingRegDocNo1;
	private String dateOfReg1;       //date
	private String existingRegDocNo2;
	private String dateOfReg2;       //date
	private String existingRegDocNo;
	private String dateOfReg;        //date
	private String noBuyers;
	private String considerationFaceValue;
	private String noSellers;
	private String noDonors;
	private String noDonees;
	private String noParty1;
	private String noParty2;
	private String ownersAuthPoa;
	private String authPersAcceptPoa;
	private String periodPoa;        //varchar 1
	private String noOwners;
	private String nameOfBank;       //varchar
	private String bankBranch;       //varchar
	private String bankBranchAddress;//varchar
	private String authOfBank;       //varchar
	private String depWithPossession;//varchar 1
	private String loanAmount;
	private String sanctdAmount;
	private String poaWithConsidrtn; //varchar 1
	private String aucAmount;
	private String authType;         //varchar 1
	private String authName;         //varchar
	private String authPhoneNo;
	private String authAddress;      //varchar
	private String hidnRegTxnId;
	private String hidnRegTxnIdEfile;
	private String hidnUserId;
	private String partyRoleTypeId;
	private String listAdoptedPartyName;
	private String indcountryName;
	private String indstatenameName;
	private String inddistrictName;
	private String countryName;
	private String statenameName;
	private String districtName;
	private String deedId;
	private String roleId;
	private String partyTypeTrns;
	private String indReligionTrns;
	private String indCasteTrns;
	private String indDisabilityTrns="N";
	private String ownershipShareTrns;
	private String relationWithOwnerTrns;
	private String shareOfPropTrns;
	private String applicantOrTransParty;
	private HashMap mapTransactingPartiesDisp = new HashMap();
	private String roleName;
	private String listIDName;
	private String partyRoleId;
	private String confirmationFlag;
	//counters
	private int ownerCount=0;
	private int sellerSelfCount=0;
	private int sellerPoaCount=0;
	private int buyerCount=0;
	private int donorCount=0;
	private int doneeCount=0;
	private int poaHolderCount=0;
	private int poaAccepterCount=0;
	private int party1OwnerCount=0;
	private int party2OwnerCount=0;
	private int party1PoaHolderCount=0;
	private int party2PoaHolderCount=0;
	private int totalPoaCount=0;
	private int totalOwnerCount=0;
	private String poaListId;
	private String ownerListId;
	private String radio;
	//arraylists
	private ArrayList poaList=new ArrayList();
	private ArrayList ownerList=new ArrayList();
	private String poaSelectedId;
	
	//starings
	private String poaList1;
	private String ownerList1;
	//txn ids
	private String regInitPermTxnId;
	private String regInitPaymntTxnId;
	private String regInitEstampCode;
	private HashMap mapRegTxnIdDisp = new HashMap();
	//
	private String ownerId;
	private String hdnOwnerId;
	private String hdnDeleteTrnsPrtyId;
	private String abc;
	//
	private String selectedPoa;
	private String selectedPoaName;
	private String currDateTime;
	private int deedID;
	
	//Start:==== Added by ankita
	private String deedtype1;
	private String instType;
	/*private double stampduty1;
	private double nagarPalikaDuty;
	private double panchayatDuty;
	private double upkarDuty;
	private double totalduty ;
	private double registrationFee;*/
	private String stampduty1;
	
	private String nagarPalikaDuty;
	private String panchayatDuty;
	private String upkarDuty;
	
	private String totalduty ;
	private String registrationFee;
	private ArrayList selectedExemptionList = new ArrayList();
	private String exemType;
	private String fromModule;
	private String checkModule;
	private String checkRegNo;
	private ArrayList selectedSubClauseList = new ArrayList();
	private String check;
	//End :====== Added by ankita
	
	//following added roopam after first demo.
	
	private String applicantUserIdCheck;
	private String hdnapplicantUserIdCheck;
	private int instID;
	private String exmpID;
	private ArrayList pendingRegApplicationList = new ArrayList();
	private ArrayList pendingEfileApplicationList = new ArrayList();
	private ArrayList pendingApplicationListPo= new ArrayList();
	private ArrayList pendingSearchList = new ArrayList();
	private ArrayList submitSearchList = new ArrayList();
	private ArrayList rejectSearchList = new ArrayList();
	private ArrayList referSearchList = new ArrayList();
	private ArrayList durationSearchList = new ArrayList();
	private ArrayList efileSearchList = new ArrayList();
	private ArrayList submitSearchListPo = new ArrayList();
	private ArrayList pendingSearchListPo = new ArrayList();
	private ArrayList rejectSearchListPo = new ArrayList();
	private ArrayList referSearchListPo = new ArrayList();
	private ArrayList durationSearchListPo = new ArrayList();
	private ArrayList efileSearchListPo = new ArrayList();
	private ArrayList efilePrintListPo = new ArrayList();
	private ArrayList efilePaymentPendingListPo = new ArrayList();
	private ArrayList efilePaymentCompleteListPo = new ArrayList();
	private ArrayList pendingInitateApplicationListPo= new ArrayList();
	public ArrayList getEfilePaymentPendingListPo() {
		return efilePaymentPendingListPo;
	}
	public void setEfilePaymentPendingListPo(ArrayList efilePaymentPendingListPo) {
		this.efilePaymentPendingListPo = efilePaymentPendingListPo;
	}
	public ArrayList getEfilePrintListPo() {
		return efilePrintListPo;
	}
	public void setEfilePrintListPo(ArrayList efilePrintListPo) {
		this.efilePrintListPo = efilePrintListPo;
	}
	public ArrayList getSubmitSearchList() {
		return submitSearchList;
	}
	public void setSubmitSearchList(ArrayList submitSearchList) {
		this.submitSearchList = submitSearchList;
	}
	public ArrayList getPendingSearchList() {
		return pendingSearchList;
	}
	public void setPendingSearchList(ArrayList pendingSearchList) {
		this.pendingSearchList = pendingSearchList;
	}

private String efileNumber;
private String durationFrom;
private String durationTo;
	public String getDurationFrom() {
	return durationFrom;
}
public void setDurationFrom(String durationFrom) {
	this.durationFrom = durationFrom;
}
public String getDurationTo() {
	return durationTo;
}
public void setDurationTo(String durationTo) {
	this.durationTo = durationTo;
}



	private String propertyId;
	private String valuationId;
	private int isDashboardFlag=0;
	private int isMultiplePropsFlag=0;
	private int isTransactingPartyAddedFlag=0;
	private int isPoaAddedFlag=0;
	private HashMap mapPropertyTransParty = new HashMap();
	private HashMap mapPropertyTransPartyDisp = new HashMap();
	//private float totalMarketValue=0;
	private String totalMarketValue;
	private int applicantRoleId;
	private int totalShareOfProp;
	private int applicantRoleId2;
	private int mapKeyCount=0;
	private HashMap mapTransPartyDbInsertion = new HashMap();
	
	private int totalShareOfPropBuyer;              //USED FOR SALE/CONVEYANCE/SETTLEMENT DEEDS
	private int totalShareOfPropSellerSelf;         //USED FOR CONVEYANCE/SETTLEMENT DEEDS
	private int totalShareOfPropSellerPoa;
	//GIFT
	private int totalShareOfPropDonor;
	private int totalShareOfPropDonee;
	//SALE & PoA 
	private int totalShareOfPropOwnerSelf;
	//EXCHANGE
	private int totalShareOfPropOwnerSelfParty1;
	private int totalShareOfPropOwnerSelfParty2;
	//LEASE PV
	private int totalShareOfPropLesser;
	private int totalShareOfPropLessee;
	
	//private float marketValue;
	
	//following fields for owner of poa
	private String ownerGendarVal;
	private String ownerName;
	private String ownerOgrName;
	private String ownerGendar="M";
	private String ownerNationality;
	private String ownerAddress;
	private String ownerPhno;
	private String ownerEmailID;
	private String ownerListID;
	private String ownerIdno;
	private String ownerAge;
	private String ownerProofName;
	
	 private String ownerIdTrns;
	private String ownerNameTrns;
	private String ownerOgrNameTrns;
	private String ownerGendarTrns="M";
	private String ownerNationalityTrns;
	private String ownerAddressTrns;
	private String ownerPhnoTrns;
	private String ownerEmailIDTrns;
	private String ownerListIDTrns;
	private String ownerIdnoTrns;
	private String ownerAgeTrns;
	private String ownerProofNameTrns;
	
	private String poaOwnerId;
	private String hdnExAmount;
	private int isDutyCalculated=0;
	private String marketValueShares;
	private String dutyPaid;
	
	private String labelAmountFlag;
	private String regPaid;
	
	private String adjudicationNumber;
	
	private String errorMsg=" ";
	private ArrayList<CommonDTO> exchangePropertyList=new ArrayList<CommonDTO>();
	private ArrayList<String> exchangeAgriPropertyListDisp=new ArrayList<String>();
	private String exchangeDeedMrktValue;
	
	private String indMinority="N";
	private String indMinorityTrns="N";
	private String selectedCategoryName;
	private String selectedCategoryNameTrns;
	
	private String indDisabilityDesc="N";
	private String indDisabilityDescTrns="N";
	private String indPovertyLine="N";
	private String indPovertyLineTrns="N";
	private String userDay;
	private String userMonth;
	private String userYear;
	private String userDOB;
	private String userDayTrns;
	private String userMonthTrns;
	private String userYearTrns;
	private String userDOBTrns;
	private String indCategory;
	private String indCategoryTrns;
	
	private String occupationId;
	private String occupationIdTrns;
	private String selectedOccupationName;
	
	private String indScheduleArea="Y";
	private String hdnIndScheduleArea;
	private String hdnIndScheduleAreaTrns;
	private String permissionNo;
	private String indScheduleAreaTrns="Y";
	private String permissionNoTrns;
	
	 //following added by roopam for compliance list
	//UPLOAD COLLECTORS PERMISSION NO.
	private FormFile certificate = null;
	private String documentName;
	private String documentSize;
	private byte[] docContents;
	private String photoCheck;
	private String filePath;
	private String partyOrProp;
	private String uploadType;
	
	private FormFile certificateTrns = null;
	private String documentNameTrns;
	private String documentSizeTrns;
	private byte[] docContentsTrns;
	private String photoCheckTrns;
	private String filePathTrns;
	private String partyOrPropTrns;
	private String uploadTypeTrns;
	
	//UPLOAD PHOTO ID PROOF
	private FormFile u2 = null;
	private String u2DocumentName;
	private String u2DocumentSize;
	private byte[] u2DocContents;
	private String u2PhotoCheck;
	private String u2FilePath;
	private String u2PartyOrProp;
	private String u2UploadType;
	
	private FormFile u2Trns = null;
	private String u2DocumentNameTrns;
	private String u2DocumentSizeTrns;
	private byte[] u2DocContentsTrns;
	private String u2PhotoCheckTrns;
	private String u2FilePathTrns;
	private String u2PartyOrPropTrns;
	private String u2UploadTypeTrns;
	
	//UPLOAD NOTARIZED AFFIDAVIT BY EXECUTANT
	private FormFile u3 = null;
	private String u3DocumentName;
	private String u3DocumentSize;
	private byte[] u3DocContents;
	private String u3PhotoCheck;
	private String u3FilePath;
	private String u3PartyOrProp;
	private String u3UploadType;
	
	private FormFile u3Trns = null;
	private String u3DocumentNameTrns;
	private String u3DocumentSizeTrns;
	private byte[] u3DocContentsTrns;
	private String u3PhotoCheckTrns;
	private String u3FilePathTrns;
	private String u3PartyOrPropTrns;
	private String u3UploadTypeTrns;
	
	//UPLOAD EXECUTANT PHOTO
	private FormFile u4 = null;
	private String u4DocumentName;
	private String u4DocumentSize;
	private byte[] u4DocContents;
	private String u4PhotoCheck;
	private String u4FilePath;
	private String u4PartyOrProp;
	private String u4UploadType;
	
	private FormFile u4Trns = null;
	private String u4DocumentNameTrns;
	private String u4DocumentSizeTrns;
	private byte[] u4DocContentsTrns;
	private String u4PhotoCheckTrns;
	private String u4FilePathTrns;
	private String u4PartyOrPropTrns;
	private String u4UploadTypeTrns;
	
	//UPLOAD NOTARIZED AFFIDAVIT BY ATTORNEY
	private FormFile u5 = null;
	private String u5DocumentName;
	private String u5DocumentSize;
	private byte[] u5DocContents;
	private String u5PhotoCheck;
	private String u5FilePath;
	private String u5PartyOrProp;
	private String u5UploadType;
	
	private FormFile u5Trns = null;
	private String u5DocumentNameTrns;
	private String u5DocumentSizeTrns;
	private byte[] u5DocContentsTrns;
	private String u5PhotoCheckTrns;
	private String u5FilePathTrns;
	private String u5PartyOrPropTrns;
	private String u5UploadTypeTrns;
	
	//UPLOAD ATTORNEY PHOTO
	private FormFile u6 = null;
	private String u6DocumentName;
	private String u6DocumentSize;
	private byte[] u6DocContents;
	private String u6PhotoCheck;
	private String u6FilePath;
	private String u6PartyOrProp;
	private String u6UploadType;
	
	private FormFile u6Trns = null;
	private String u6DocumentNameTrns;
	private String u6DocumentSizeTrns;
	private byte[] u6DocContentsTrns;
	private String u6PhotoCheckTrns;
	private String u6FilePathTrns;
	private String u6PartyOrPropTrns;
	private String u6UploadTypeTrns;
	
	//UPLOAD EXECUTANT PHOTO 2
	private FormFile u7 = null;
	private String u7DocumentName;
	private String u7DocumentSize;
	private byte[] u7DocContents;
	private String u7PhotoCheck;
	private String u7FilePath;
	private String u7PartyOrProp;
	private String u7UploadType;
	
	private FormFile u7Trns = null;
	private String u7DocumentNameTrns;
	private String u7DocumentSizeTrns;
	private byte[] u7DocContentsTrns;
	private String u7PhotoCheckTrns;
	private String u7FilePathTrns;
	private String u7PartyOrPropTrns;
	private String u7UploadTypeTrns;
	
	//UPLOAD CLAIMANT PHOTO
	private FormFile u8 = null;
	private String u8DocumentName;
	private String u8DocumentSize;
	private byte[] u8DocContents;
	private String u8PhotoCheck;
	private String u8FilePath;
	private String u8PartyOrProp;
	private String u8UploadType;
	
	private FormFile u8Trns = null;
	private String u8DocumentNameTrns;
	private String u8DocumentSizeTrns;
	private byte[] u8DocContentsTrns;
	private String u8PhotoCheckTrns;
	private String u8FilePathTrns;
	private String u8PartyOrPropTrns;
	private String u8UploadTypeTrns;
	
	//UPLOAD ATTORNEY PHOTO 2
	private FormFile u9 = null;
	private String u9DocumentName;
	private String u9DocumentSize;
	private byte[] u9DocContents;
	private String u9PhotoCheck;
	private String u9FilePath;
	private String u9PartyOrProp;
	private String u9UploadType;
	
	private FormFile u9Trns = null;
	private String u9DocumentNameTrns;
	private String u9DocumentSizeTrns;
	private byte[] u9DocContentsTrns;
	private String u9PhotoCheckTrns;
	private String u9FilePathTrns;
	private String u9PartyOrPropTrns;
	private String u9UploadTypeTrns;
	
	//UPLOAD PAN EXECUTANT
	private FormFile u10 = null;
	private String u10DocumentName;
	private String u10DocumentSize;
	private byte[] u10DocContents;
	private String u10PhotoCheck;
	private String u10FilePath;
	private String u10PartyOrProp;
	private String u10UploadType;
	
	private FormFile u10Trns = null;
	private String u10DocumentNameTrns;
	private String u10DocumentSizeTrns;
	private byte[] u10DocContentsTrns;
	private String u10PhotoCheckTrns;
	private String u10FilePathTrns;
	private String u10PartyOrPropTrns;
	private String u10UploadTypeTrns;
	
	//UPLOAD PAN CLAIMANT
	private FormFile u11 = null;
	private String u11DocumentName;
	private String u11DocumentSize;
	private byte[] u11DocContents;
	private String u11PhotoCheck;
	private String u11FilePath;
	private String u11PartyOrProp;
	private String u11UploadType;
	
	private FormFile u11Trns = null;
	private String u11DocumentNameTrns;
	private String u11DocumentSizeTrns;
	private byte[] u11DocContentsTrns;
	private String u11PhotoCheckTrns;
	private String u11FilePathTrns;
	private String u11PartyOrPropTrns;
	private String u11UploadTypeTrns;
	
	//POA DETAILS
	private String srOfficeName;
	private String poaRegNo;
	private String datePoaReg;
	
	private String srOfficeNameTrns;
	private String poaRegNoTrns;
	private String datePoaRegTrns;
	
	
	private String jspName="";
	
	
	private String ownerDay;
	private String ownerMonth;
	private String ownerYear;
	private String ownerDOB;
	private String ownerDayTrns;
	private String ownerMonthTrns;
	private String ownerYearTrns;
	private String ownerDOBTrns;
	
	private String[] declareShare;
	private String hdnDeclareShareCheck="Y";
	
	
	
	//Start:=== Added By Ankita Fr showing stamp Duty Details

    
	private String estampCode;

    private String estampAmt;

    private String estampDateTime;

    private ArrayList estampDetLst = new ArrayList();

            

    //End:=== Added By Ankita For showinf Stamp Duty Details


  //ADDED BY SHRUTI FOR TITLE DEED
    private String bankName;
    private String branchName;
    private String bankAddress;
    private String bankAuthPer;
    private String bankLoanAmt;
    private String bankSancAmt;
    private int duty_txn_id;
    //END OF CODE BY SHRUTI
    
    //added by roopam
    
    private ArrayList transPartyListNonPropDeed;
    private ArrayList propListNonPropDeed;
    
    private String totalPayableAmount;
    private String totalPaidAmount;
    private String totalBalanceAmount;
    
    private String totalPayableAmountFee="0";
    private String totalPaidAmountFee="0";
    private String totalBalanceAmountFee="0";
    private int paymentCompleteFlagFee=0;
    
    
    private String totalPayableAmountAdjuFee="0";
    private String totalPaidAmountAdjuFee="0";
    private String totalBalanceAmountAdjuFee="0";
    private int paymentCompleteFlagAdjuFee=0;
    private String adjuDutyAddedFlag;
    
    private String paymentTxnSeqId;
    private int paymentCompleteFlag=0;
    private int partyModifyFlag=0;
    private int ownerModifyFlag=0;
    private int extraDetlsModifyFlag=0;
    private String partyTxnId;
    private int propertyModifyFlag=0;
    //private int propertyModifyFlag=0;
    private String postalAddress1="Y";
    private String postalAddress2="N";
    private String hdnPostalAddress1;
	private String postalAddress;
	private String postalCountry="1";
	private String postalState="1";
	private String postalDistrict;
	private String addMoreTransParty = "N";
	//extra details for tust deed
	private String trustName;
	private String trustDate;
	
	private String pvReqFlag;
	private String propReqFlag;
    
	private int deedTypeFlag=0;
	private int adoptionDeedParty1Added=0;
	private int adoptionDeedParty2Added=0;
	private int adoptionDeedParty3Added=0;
	private int cancelDeedRadio=0;           //0 FOR WILL, 1 FOR POA
	
	//extra fields for lease pv deed
	private double rent=0.00;
	private double advance=0.00;
	private double devlpmtCharge=0.00;
	private double otherRecCharge=0.00;
	private double premium=0.00;
	private double termLease=0.00;
	
	//extra fields for mortgage deed
	private String withPos;
	private double secLoanAmt=0.00;
	
	private String callingAction;
	
	
	//ARBITRATER DETAILS
	
	public int getOwnerModifyFlag() {
		return ownerModifyFlag;
	}
	public void setOwnerModifyFlag(int ownerModifyFlag) {
		this.ownerModifyFlag = ownerModifyFlag;
	}

	private String fnameArb;
	private String mnameArb; 
	private String lnameArb;
	private String gendarArb = "M";
	private String gendarNameArb;
	private String ageArb;
	private String fatherNameArb;
	private String motherNameArb;
	private String spouseNameArb;
	private String nationalityArb;
	//private String postalCodeArb;
	private String emailIDArb;
	private String indaddressArb;
	private String indcountryArb;
	private String indstatenameArb;
	private String indcountryNameArb;
	private String indstatenameNameArb;
	private String inddistrictArb;
	private String inddistrictNameArb;
	private String indphnoArb;
	private String indmobnoArb;
	private String listIDArb;
	private String listIDNameArb;
	private String idnoArb;
	private String indCategoryArb;
	private String indCategoryNameArb;
	private String indDisabilityDescArb;
	private String indDisabilityArb="N";    //varchar
	private String indDisabilityNameArb;    //varchar
	private String hdnIndDisabilityArb;    //varchar
	
	//for agreement of memorandum
	
	private String advancePaymntDetails;
	private String advancePaymntDate;
	private String possGiven="N";
	private String possGivenName;
   // Added for New Instrument 
	private String agreementDetails;
	
   private String contractDetails;
   
	
	public String getAgreementDetails() {
		return agreementDetails;
	}
	public void setAgreementDetails(String agreementDetails) {
		this.agreementDetails = agreementDetails;
	}

	private ArrayList transPartyListPVDeed;
    private ArrayList propListPVDeed;
    
    private String[] partyTxnIdArr;
    
    //for poa npv deed
  	private String poaWithConsid;
  	private String poaShareDebenture;
	private int poaPeriod;
	
	//reconveyance of mortgage
	private double paidLoanAmt=0;
	
	private String extraFieldLabel;
	
	//partnership
	private String contriProp;
	private int agreeMemoInstFlag;
	private int claimantFlag;
	private int poaHolderFlag;
	
	//dissolution of partnership
	private String dissolutionDate;
	private String retirmentDate;
	
	
	//added by Shreeraj for the stamp duties entered by DR inAdjudication
	private String stampduty1Adju;
	private String nagarPalikaDutyAdju;
	private String panchayatDutyAdju;
	private String upkarDutyAdju;
	private String totaldutyAdju;
	private String registrationFeeAdju;
	private String stampManually; 
	private int fromAdjudication=0;
	
	
	private int drAutoTot = 0;
	private int stdt = 0;
	private int mncpdt = 0;
	private int jnpddt = 0;	
	private int upkrdt=0;
	
	//flags for common deeds
	private int commonDeed;
	private int party1or2;
	//private int addAnotherParty;
	private int addPartyNewRole;
	private String commonDeedRoleName;
	private int roleSameAsPrevious;
	private String particularName;
	private String particularDesc;
	private String deleteParticularID;
	private String hdnDeleteParticularID;
	private int addMoreParticularCounter;
	private HashMap mapParticulars=new HashMap();
	private String particularTxnId;
	
	private int initiateAdjuApp=0;
	
private int relationship;
private int relationshipTrns;

//for correcting trust deed
private int isFirstPartyAddedFlag=0;

private int executantClaimant;
private String executantClaimantName;
private int executantClaimantTrns;
private String executantClaimantNameTrns;

	private String adjuRemarks;
	private String propValReqFlag;
	
	
	private HashMap propWithMapping=new HashMap();
	private HashMap propWithoutMapping=new HashMap();
	private HashMap particularWithMapping=new HashMap();
	private HashMap particularWithoutMapping=new HashMap();
	
	private String checkParty;
	private String[] checkPartyArr;
	
	private String[] mappingArray;
	private String[] partyArray;
	
	private int particularCount;
	
	private ArrayList userEnterableList= new ArrayList();
	private ArrayList propWiseUserEnterableDutyList= new ArrayList();
	
	
	private String registrationDate;
	private String registrationNo;
	private double receiptAmount;
	private String receiptAmountDisp;
	
	private String propDetls;
	private String deedNamePreReg;
	private String deedTypePreReg;
	
    private String mapOrderNo;
	private String mapOrderDate;
	private String tcpOrderNo;
	private String tcpOrderDate;
	
	private String cancellationLabel;
	
	private String totaldutyBeforeExemp ;
	private String registrationFeeBeforeExemp;
	
	private String exempStamp;
	private String exempReg;
	
	private String finalDisclaimerCheck;
	
	private String paymentType;
	
	private String checkRegFeeBox;
	
	private String addPropertyOptional;
	
	
	private String hdnAddPropertyOptional;
	
	private String deedDraftId;
	
	private String deedStat="4";
	
	private String erMsg="";
	
	private String govtOfcCheck;
	
	private String nameOfOfficial;
	
	private String desgOfOfficial;
	
	private String addressOfOfficial;
	
	private String officialCheck;
	
	
private String govtOfcCheckTrns;
	
	private String nameOfOfficialTrns;
	
	private String desgOfOfficialTrns;
	
	private String addressOfOfficialTrns;
	
	
	//added by Shreeraj for Deed Draft
	private int deedPageNo;
	private String upOrNot;
	private String uploadCheck="1";
	private FormFile deedUploadPath;
	private int consenterNoPages;
	private FormFile consenterUploadPath;
	private int consenterPages;
	private ArrayList consenterUploadList= new ArrayList();
	private String consUploadFlag="0";
	private String indexCons;
	private String consUploadSucc="N";
	private FormFile conpath;
	private String mergeSuccess="N";
	private int consenterUploadPage;
	private String consBrowseFlag="Y";
	private String consDeleteFlag;
	private String finalPage;
	
	
	public String getOwnerGendarVal() {
		return ownerGendarVal;
	}
	public void setOwnerGendarVal(String ownerGendarVal) {
		this.ownerGendarVal = ownerGendarVal;
	}
	public String getPoaShareDebenture() {
		return poaShareDebenture;
	}
	public void setPoaShareDebenture(String poaShareDebenture) {
		this.poaShareDebenture = poaShareDebenture;
	}
	public String getFinalPage() {
		return finalPage;
	}
	public void setFinalPage(String finalPage) {
		this.finalPage = finalPage;
	}
	public String getMergeSuccess() {
		return mergeSuccess;
	}
	public void setMergeSuccess(String mergeSuccess) {
		this.mergeSuccess = mergeSuccess;
	}
	public String getConsDeleteFlag() {
		return consDeleteFlag;
	}
	public void setConsDeleteFlag(String consDeleteFlag) {
		this.consDeleteFlag = consDeleteFlag;
	}
	public String getConsBrowseFlag() {
		return consBrowseFlag;
	}
	public void setConsBrowseFlag(String consBrowseFlag) {
		this.consBrowseFlag = consBrowseFlag;
	}
	public int getConsenterUploadPage() {
		return consenterUploadPage;
	}
	public void setConsenterUploadPage(int consenterUploadPage) {
		this.consenterUploadPage = consenterUploadPage;
	}
	
	public FormFile getConpath() {
		return conpath;
	}
	public void setConpath(FormFile conpath) {
		this.conpath = conpath;
	}
	public String getConsUploadSucc() {
		return consUploadSucc;
	}
	public void setConsUploadSucc(String consUploadSucc) {
		this.consUploadSucc = consUploadSucc;
	}
	public String getIndexCons() {
		return indexCons;
	}
	public void setIndexCons(String indexCons) {
		this.indexCons = indexCons;
	}
	public String getConsUploadFlag() {
		return consUploadFlag;
	}
	public void setConsUploadFlag(String consUploadFlag) {
		this.consUploadFlag = consUploadFlag;
	}
	public ArrayList getConsenterUploadList() {
		return consenterUploadList;
	}
	public void setConsenterUploadList(ArrayList consenterUploadList) {
		this.consenterUploadList = consenterUploadList;
	}
	
	public int getConsenterNoPages() {
		return consenterNoPages;
	}
	public void setConsenterNoPages(int consenterNoPages) {
		this.consenterNoPages = consenterNoPages;
	}
	public FormFile getConsenterUploadPath() {
		return consenterUploadPath;
	}
	public void setConsenterUploadPath(FormFile consenterUploadPath) {
		this.consenterUploadPath = consenterUploadPath;
	}

	public int getConsenterPages() {
		return consenterPages;
	}
	public void setConsenterPages(int consenterPages) {
		this.consenterPages = consenterPages;
	}
	public int getDeedPageNo() {
		return deedPageNo;
	}
	public void setDeedPageNo(int deedPageNo) {
		this.deedPageNo = deedPageNo;
	}
	public FormFile getDeedUploadPath() {
		return deedUploadPath;
	}
	public void setDeedUploadPath(FormFile deedUploadPath) {
		this.deedUploadPath = deedUploadPath;
	}
	public String getUploadCheck() {
		return uploadCheck;
	}
	public void setUploadCheck(String uploadCheck) {
		this.uploadCheck = uploadCheck;
	}

	public String getUpOrNot() {
		return upOrNot;
	}
	public void setUpOrNot(String upOrNot) {
		this.upOrNot = upOrNot;
	}

	public String getAdjuDutyAddedFlag() {
		return adjuDutyAddedFlag;
	}
	public void setAdjuDutyAddedFlag(String adjuDutyAddedFlag) {
		this.adjuDutyAddedFlag = adjuDutyAddedFlag;
	}
	public String getTotalPayableAmountAdjuFee() {
		return totalPayableAmountAdjuFee;
	}
	public void setTotalPayableAmountAdjuFee(String totalPayableAmountAdjuFee) {
		this.totalPayableAmountAdjuFee = totalPayableAmountAdjuFee;
	}
	public String getTotalPaidAmountAdjuFee() {
		return totalPaidAmountAdjuFee;
	}
	public void setTotalPaidAmountAdjuFee(String totalPaidAmountAdjuFee) {
		this.totalPaidAmountAdjuFee = totalPaidAmountAdjuFee;
	}
	public String getTotalBalanceAmountAdjuFee() {
		return totalBalanceAmountAdjuFee;
	}
	public void setTotalBalanceAmountAdjuFee(String totalBalanceAmountAdjuFee) {
		this.totalBalanceAmountAdjuFee = totalBalanceAmountAdjuFee;
	}
	public int getPaymentCompleteFlagAdjuFee() {
		return paymentCompleteFlagAdjuFee;
	}
	public void setPaymentCompleteFlagAdjuFee(int paymentCompleteFlagAdjuFee) {
		this.paymentCompleteFlagAdjuFee = paymentCompleteFlagAdjuFee;
	}
	public String getGovtOfcCheckTrns() {
		return govtOfcCheckTrns;
	}
	public void setGovtOfcCheckTrns(String govtOfcCheckTrns) {
		this.govtOfcCheckTrns = govtOfcCheckTrns;
	}
	public String getNameOfOfficialTrns() {
		return nameOfOfficialTrns;
	}
	public void setNameOfOfficialTrns(String nameOfOfficialTrns) {
		this.nameOfOfficialTrns = nameOfOfficialTrns;
	}
	public String getDesgOfOfficialTrns() {
		return desgOfOfficialTrns;
	}
	public void setDesgOfOfficialTrns(String desgOfOfficialTrns) {
		this.desgOfOfficialTrns = desgOfOfficialTrns;
	}
	public String getAddressOfOfficialTrns() {
		return addressOfOfficialTrns;
	}
	public void setAddressOfOfficialTrns(String addressOfOfficialTrns) {
		this.addressOfOfficialTrns = addressOfOfficialTrns;
	}
	public String getOfficialCheck() {
		return officialCheck;
	}
	public void setOfficialCheck(String officialCheck) {
		this.officialCheck = officialCheck;
	}
	public String getGovtOfcCheck() {
		return govtOfcCheck;
	}
	public void setGovtOfcCheck(String govtOfcCheck) {
		this.govtOfcCheck = govtOfcCheck;
	}
	public String getNameOfOfficial() {
		return nameOfOfficial;
	}
	public void setNameOfOfficial(String nameOfOfficial) {
		this.nameOfOfficial = nameOfOfficial;
	}
	public String getDesgOfOfficial() {
		return desgOfOfficial;
	}
	public void setDesgOfOfficial(String desgOfOfficial) {
		this.desgOfOfficial = desgOfOfficial;
	}
	public String getAddressOfOfficial() {
		return addressOfOfficial;
	}
	public void setAddressOfOfficial(String addressOfOfficial) {
		this.addressOfOfficial = addressOfOfficial;
	}
	public String getErMsg() {
		return erMsg;
	}
	public void setErMsg(String erMsg) {
		this.erMsg = erMsg;
	}
	public String getDeedStat() {
		return deedStat;
	}
	public void setDeedStat(String deedStat) {
		this.deedStat = deedStat;
	}
	public String getDeedDraftId() {
		return deedDraftId;
	}
	public void setDeedDraftId(String deedDraftId) {
		this.deedDraftId = deedDraftId;
	}
	public String getHdnAddPropertyOptional() {
		return hdnAddPropertyOptional;
	}
	public void setHdnAddPropertyOptional(String hdnAddPropertyOptional) {
		this.hdnAddPropertyOptional = hdnAddPropertyOptional;
	}
	public String getAddPropertyOptional() {
		return addPropertyOptional;
	}
	public void setAddPropertyOptional(String addPropertyOptional) {
		this.addPropertyOptional = addPropertyOptional;
	}
	public String getCheckRegFeeBox() {
		return checkRegFeeBox;
	}
	public void setCheckRegFeeBox(String checkRegFeeBox) {
		this.checkRegFeeBox = checkRegFeeBox;
	}
	public String getAdvancePaymntDetails() {
		return advancePaymntDetails;
	}
	public void setAdvancePaymntDetails(String advancePaymntDetails) {
		this.advancePaymntDetails = advancePaymntDetails;
	}
	public String getTotalPayableAmountFee() {
		return totalPayableAmountFee;
	}
	public void setTotalPayableAmountFee(String totalPayableAmountFee) {
		this.totalPayableAmountFee = totalPayableAmountFee;
	}
	public String getTotalPaidAmountFee() {
		return totalPaidAmountFee;
	}
	public void setTotalPaidAmountFee(String totalPaidAmountFee) {
		this.totalPaidAmountFee = totalPaidAmountFee;
	}
	public String getTotalBalanceAmountFee() {
		return totalBalanceAmountFee;
	}
	public void setTotalBalanceAmountFee(String totalBalanceAmountFee) {
		this.totalBalanceAmountFee = totalBalanceAmountFee;
	}
	public int getPaymentCompleteFlagFee() {
		return paymentCompleteFlagFee;
	}
	public void setPaymentCompleteFlagFee(int paymentCompleteFlagFee) {
		this.paymentCompleteFlagFee = paymentCompleteFlagFee;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getFinalDisclaimerCheck() {
		return finalDisclaimerCheck;
	}
	public void setFinalDisclaimerCheck(String finalDisclaimerCheck) {
		this.finalDisclaimerCheck = finalDisclaimerCheck;
	}
	public String getExempStamp() {
		return exempStamp;
	}
	public void setExempStamp(String exempStamp) {
		this.exempStamp = exempStamp;
	}
	public String getExempReg() {
		return exempReg;
	}
	public void setExempReg(String exempReg) {
		this.exempReg = exempReg;
	}
	public ArrayList getPropWiseUserEnterableDutyList() {
		return propWiseUserEnterableDutyList;
	}
	public void setPropWiseUserEnterableDutyList(
			ArrayList propWiseUserEnterableDutyList) {
		this.propWiseUserEnterableDutyList = propWiseUserEnterableDutyList;
	}
	public String getTotaldutyBeforeExemp() {
		return totaldutyBeforeExemp;
	}
	public void setTotaldutyBeforeExemp(String totaldutyBeforeExemp) {
		this.totaldutyBeforeExemp = totaldutyBeforeExemp;
	}
	public String getRegistrationFeeBeforeExemp() {
		return registrationFeeBeforeExemp;
	}
	public void setRegistrationFeeBeforeExemp(String registrationFeeBeforeExemp) {
		this.registrationFeeBeforeExemp = registrationFeeBeforeExemp;
	}
	public String getCancellationLabel() {
		return cancellationLabel;
	}
	public void setCancellationLabel(String cancellationLabel) {
		this.cancellationLabel = cancellationLabel;
	}
	public String getMapOrderNo() {
		return mapOrderNo;
	}
	public void setMapOrderNo(String mapOrderNo) {
		this.mapOrderNo = mapOrderNo;
	}
	public String getMapOrderDate() {
		return mapOrderDate;
	}
	public void setMapOrderDate(String mapOrderDate) {
		this.mapOrderDate = mapOrderDate;
	}
	public String getTcpOrderNo() {
		return tcpOrderNo;
	}
	public void setTcpOrderNo(String tcpOrderNo) {
		this.tcpOrderNo = tcpOrderNo;
	}
	public String getTcpOrderDate() {
		return tcpOrderDate;
	}
	public void setTcpOrderDate(String tcpOrderDate) {
		this.tcpOrderDate = tcpOrderDate;
	}
	public String getDeedNamePreReg() {
		return deedNamePreReg;
	}
	public void setDeedNamePreReg(String deedNamePreReg) {
		this.deedNamePreReg = deedNamePreReg;
	}
	public String getDeedTypePreReg() {
		return deedTypePreReg;
	}
	public void setDeedTypePreReg(String deedTypePreReg) {
		this.deedTypePreReg = deedTypePreReg;
	}
	public String getPropDetls() {
		return propDetls;
	}
	public void setPropDetls(String propDetls) {
		this.propDetls = propDetls;
	}
	public String getReceiptAmountDisp() {
		return receiptAmountDisp;
	}
	public void setReceiptAmountDisp(String receiptAmountDisp) {
		this.receiptAmountDisp = receiptAmountDisp;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public double getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public String getCheckPartyArr(int index) {
		return checkPartyArr[index];
	}
	public String[] getCheckPartyArr() {
		return checkPartyArr;
	}
	public void setCheckPartyArr(String[] checkPartyArr) {
		this.checkPartyArr = checkPartyArr;
	}
	public ArrayList getUserEnterableList() {
		return userEnterableList;
	}
	public void setUserEnterableList(ArrayList userEnterableList) {
		this.userEnterableList = userEnterableList;
	}
	public int getParticularCount() {
		return particularCount;
	}
	public void setParticularCount(int particularCount) {
		this.particularCount = particularCount;
	}
	public String[] getPartyArray() {
		return partyArray;
	}
	public void setPartyArray(String[] partyArray) {
		this.partyArray = partyArray;
	}
	public String[] getMappingArray() {
		return mappingArray;
	}
	public void setMappingArray(String[] mappingArray) {
		this.mappingArray = mappingArray;
	}
	public String getCheckParty() {
		return checkParty;
	}
	public void setCheckParty(String checkParty) {
		this.checkParty = checkParty;
	}
	public HashMap getPropWithMapping() {
		return propWithMapping;
	}
	public void setPropWithMapping(HashMap propWithMapping) {
		this.propWithMapping = propWithMapping;
	}
	public HashMap getPropWithoutMapping() {
		return propWithoutMapping;
	}
	public void setPropWithoutMapping(HashMap propWithoutMapping) {
		this.propWithoutMapping = propWithoutMapping;
	}
	public HashMap getParticularWithMapping() {
		return particularWithMapping;
	}
	public void setParticularWithMapping(HashMap particularWithMapping) {
		this.particularWithMapping = particularWithMapping;
	}
	public HashMap getParticularWithoutMapping() {
		return particularWithoutMapping;
	}
	public void setParticularWithoutMapping(HashMap particularWithoutMapping) {
		this.particularWithoutMapping = particularWithoutMapping;
	}

	private ArrayList agriPropertyList=new ArrayList();
	

public String getPvReqFlag() {
		return pvReqFlag;
	}
	public void setPvReqFlag(String pvReqFlag) {
		this.pvReqFlag = pvReqFlag;
	}
	public String getPropReqFlag() {
		return propReqFlag;
	}
	public void setPropReqFlag(String propReqFlag) {
		this.propReqFlag = propReqFlag;
	}
public ArrayList<String> getExchangeAgriPropertyListDisp() {
		return exchangeAgriPropertyListDisp;
	}
	public void setExchangeAgriPropertyListDisp(
			ArrayList<String> exchangeAgriPropertyListDisp) {
		this.exchangeAgriPropertyListDisp = exchangeAgriPropertyListDisp;
	}
public ArrayList getAgriPropertyList() {
		return agriPropertyList;
	}
	public void setAgriPropertyList(ArrayList agriPropertyList) {
		this.agriPropertyList = agriPropertyList;
	}
public String getPropValReqFlag() {
		return propValReqFlag;
	}
	public void setPropValReqFlag(String propValReqFlag) {
		this.propValReqFlag = propValReqFlag;
	}
public String getAdjuRemarks() {
		return adjuRemarks;
	}
	public void setAdjuRemarks(String adjuRemarks) {
		this.adjuRemarks = adjuRemarks;
	}
public String getAuthPerCountryName() {
	return authPerCountryName;
}
public void setAuthPerCountryName(String authPerCountryName) {
	this.authPerCountryName = authPerCountryName;
}
public String getAuthPerStatenameName() {
	return authPerStatenameName;
}
public void setAuthPerStatenameName(String authPerStatenameName) {
	this.authPerStatenameName = authPerStatenameName;
}
public String getAuthPerDistrictName() {
	return authPerDistrictName;
}
public void setAuthPerDistrictName(String authPerDistrictName) {
	this.authPerDistrictName = authPerDistrictName;
}
public String getAuthPerCountryNameTrns() {
	return authPerCountryNameTrns;
}
public void setAuthPerCountryNameTrns(String authPerCountryNameTrns) {
	this.authPerCountryNameTrns = authPerCountryNameTrns;
}
public String getAuthPerStatenameNameTrns() {
	return authPerStatenameNameTrns;
}
public void setAuthPerStatenameNameTrns(String authPerStatenameNameTrns) {
	this.authPerStatenameNameTrns = authPerStatenameNameTrns;
}
public String getAuthPerDistrictNameTrns() {
	return authPerDistrictNameTrns;
}
public void setAuthPerDistrictNameTrns(String authPerDistrictNameTrns) {
	this.authPerDistrictNameTrns = authPerDistrictNameTrns;
}
public String getAuthPerAddress() {
	return authPerAddress;
}
public void setAuthPerAddress(String authPerAddress) {
	this.authPerAddress = authPerAddress;
}
public String getAuthPerCountry() {
	return authPerCountry;
}
public void setAuthPerCountry(String authPerCountry) {
	this.authPerCountry = authPerCountry;
}
public String getAuthPerStatename() {
	return authPerStatename;
}
public void setAuthPerStatename(String authPerStatename) {
	this.authPerStatename = authPerStatename;
}
public String getAuthPerDistrict() {
	return authPerDistrict;
}
public void setAuthPerDistrict(String authPerDistrict) {
	this.authPerDistrict = authPerDistrict;
}
public String getAuthPerFatherName() {
	return authPerFatherName;
}
public void setAuthPerFatherName(String authPerFatherName) {
	this.authPerFatherName = authPerFatherName;
}
public int getAuthPerRelationship() {
	return authPerRelationship;
}
public void setAuthPerRelationship(int authPerRelationship) {
	this.authPerRelationship = authPerRelationship;
}
public String getAuthPerGendar() {
	return authPerGendar;
}
public void setAuthPerGendar(String authPerGendar) {
	this.authPerGendar = authPerGendar;
}
public String getAuthPerAddressTrns() {
	return authPerAddressTrns;
}
public void setAuthPerAddressTrns(String authPerAddressTrns) {
	this.authPerAddressTrns = authPerAddressTrns;
}
public String getAuthPerCountryTrns() {
	return authPerCountryTrns;
}
public void setAuthPerCountryTrns(String authPerCountryTrns) {
	this.authPerCountryTrns = authPerCountryTrns;
}
public String getAuthPerStatenameTrns() {
	return authPerStatenameTrns;
}
public void setAuthPerStatenameTrns(String authPerStatenameTrns) {
	this.authPerStatenameTrns = authPerStatenameTrns;
}
public String getAuthPerDistrictTrns() {
	return authPerDistrictTrns;
}
public void setAuthPerDistrictTrns(String authPerDistrictTrns) {
	this.authPerDistrictTrns = authPerDistrictTrns;
}
public String getAuthPerFatherNameTrns() {
	return authPerFatherNameTrns;
}
public void setAuthPerFatherNameTrns(String authPerFatherNameTrns) {
	this.authPerFatherNameTrns = authPerFatherNameTrns;
}
public int getAuthPerRelationshipTrns() {
	return authPerRelationshipTrns;
}
public void setAuthPerRelationshipTrns(int authPerRelationshipTrns) {
	this.authPerRelationshipTrns = authPerRelationshipTrns;
}
public String getAuthPerGendarTrns() {
	return authPerGendarTrns;
}
public void setAuthPerGendarTrns(String authPerGendarTrns) {
	this.authPerGendarTrns = authPerGendarTrns;
}
public int getExecutantClaimant() {
	return executantClaimant;
}
public void setExecutantClaimant(int executantClaimant) {
	this.executantClaimant = executantClaimant;
}
public int getExecutantClaimantTrns() {
	return executantClaimantTrns;
}
public void setExecutantClaimantTrns(int executantClaimantTrns) {
	this.executantClaimantTrns = executantClaimantTrns;
}
public String getExecutantClaimantNameTrns() {
	return executantClaimantNameTrns;
}
public void setExecutantClaimantNameTrns(String executantClaimantNameTrns) {
	this.executantClaimantNameTrns = executantClaimantNameTrns;
}

public String getExecutantClaimantName() {
	return executantClaimantName;
}
public void setExecutantClaimantName(String executantClaimantName) {
	this.executantClaimantName = executantClaimantName;
}
	public int getIsFirstPartyAddedFlag() {
	return isFirstPartyAddedFlag;
}
public void setIsFirstPartyAddedFlag(int isFirstPartyAddedFlag) {
	this.isFirstPartyAddedFlag = isFirstPartyAddedFlag;
}
	public int getRelationshipTrns() {
	return relationshipTrns;
}
public void setRelationshipTrns(int relationshipTrns) {
	this.relationshipTrns = relationshipTrns;
}
	public int getRelationship() {
	return relationship;
}
public void setRelationship(int relationship) {
	this.relationship = relationship;
}
	public String getDissolutionDate() {
		return dissolutionDate;
	}
	public void setDissolutionDate(String dissolutionDate) {
		this.dissolutionDate = dissolutionDate;
	}
	public String getRetirmentDate() {
		return retirmentDate;
	}
	public void setRetirmentDate(String retirmentDate) {
		this.retirmentDate = retirmentDate;
	}
	public int getInitiateAdjuApp() {
		return initiateAdjuApp;
	}
	public void setInitiateAdjuApp(int initiateAdjuApp) {
		this.initiateAdjuApp = initiateAdjuApp;
	}
	
	
	
	public String getParticularTxnId() {
		return particularTxnId;
	}
	public void setParticularTxnId(String particularTxnId) {
		this.particularTxnId = particularTxnId;
	}
	public String getParticularName() {
		return particularName;
	}
	public void setParticularName(String particularName) {
		this.particularName = particularName;
	}
	public String getParticularDesc() {
		return particularDesc;
	}
	public void setParticularDesc(String particularDesc) {
		this.particularDesc = particularDesc;
	}
	public String getDeleteParticularID() {
		return deleteParticularID;
	}
	public void setDeleteParticularID(String deleteParticularID) {
		this.deleteParticularID = deleteParticularID;
	}
	public String getHdnDeleteParticularID() {
		return hdnDeleteParticularID;
	}
	public void setHdnDeleteParticularID(String hdnDeleteParticularID) {
		this.hdnDeleteParticularID = hdnDeleteParticularID;
	}
	public int getAddMoreParticularCounter() {
		return addMoreParticularCounter;
	}
	public void setAddMoreParticularCounter(int addMoreParticularCounter) {
		this.addMoreParticularCounter = addMoreParticularCounter;
	}
	public HashMap getMapParticulars() {
		return mapParticulars;
	}
	public void setMapParticulars(HashMap mapParticulars) {
		this.mapParticulars = mapParticulars;
	}
	public int getRoleSameAsPrevious() {
		return roleSameAsPrevious;
	}
	public void setRoleSameAsPrevious(int roleSameAsPrevious) {
		this.roleSameAsPrevious = roleSameAsPrevious;
	}
	public String getCommonDeedRoleName() {
		return commonDeedRoleName;
	}
	public void setCommonDeedRoleName(String commonDeedRoleName) {
		this.commonDeedRoleName = commonDeedRoleName;
	}
	public int getCommonDeed() {
		return commonDeed;
	}
	public void setCommonDeed(int commonDeed) {
		this.commonDeed = commonDeed;
	}
	
	/*public int getAddAnotherParty() {
		return addAnotherParty;
	}
	public void setAddAnotherParty(int addAnotherParty) {
		this.addAnotherParty = addAnotherParty;
	}*/
	public int getAddPartyNewRole() {
		return addPartyNewRole;
	}
	public void setAddPartyNewRole(int addPartyNewRole) {
		this.addPartyNewRole = addPartyNewRole;
	}
	public int getDrAutoTot() {
		return drAutoTot;
	}
	public void setDrAutoTot(int drAutoTot) {
		this.drAutoTot = drAutoTot;
	}
	public int getStdt() {
		return stdt;
	}
	public void setStdt(int stdt) {
		this.stdt = stdt;
	}
	public int getMncpdt() {
		return mncpdt;
	}
	public void setMncpdt(int mncpdt) {
		this.mncpdt = mncpdt;
	}
	public int getJnpddt() {
		return jnpddt;
	}
	public void setJnpddt(int jnpddt) {
		this.jnpddt = jnpddt;
	}
	public int getUpkrdt() {
		return upkrdt;
	}
	public void setUpkrdt(int upkrdt) {
		this.upkrdt = upkrdt;
	}

	
	
	
	public String getStampManually() {
		return stampManually;
	}
	public void setStampManually(String stampManually) {
		this.stampManually = stampManually;
	}
	
	public String getNagarPalikaDutyAdju() {
		return nagarPalikaDutyAdju;
	}
	public void setNagarPalikaDutyAdju(String nagarPalikaDutyAdju) {
		this.nagarPalikaDutyAdju = nagarPalikaDutyAdju;
	}
	public String getPanchayatDutyAdju() {
		return panchayatDutyAdju;
	}
	public void setPanchayatDutyAdju(String panchayatDutyAdju) {
		this.panchayatDutyAdju = panchayatDutyAdju;
	}
	public String getUpkarDutyAdju() {
		return upkarDutyAdju;
	}
	public void setUpkarDutyAdju(String upkarDutyAdju) {
		this.upkarDutyAdju = upkarDutyAdju;
	}
	public String getTotaldutyAdju() {
		return totaldutyAdju;
	}
	public void setTotaldutyAdju(String totaldutyAdju) {
		this.totaldutyAdju = totaldutyAdju;
	}
	public String getRegistrationFeeAdju() {
		return registrationFeeAdju;
	}
	public void setRegistrationFeeAdju(String registrationFeeAdju) {
		this.registrationFeeAdju = registrationFeeAdju;
	}
	public int getFromAdjudication() {
		return fromAdjudication;
	}
	public void setFromAdjudication(int fromAdjudication) {
		this.fromAdjudication = fromAdjudication;
	}
	public int getPoaHolderFlag() {
		return poaHolderFlag;
	}
	public void setPoaHolderFlag(int poaHolderFlag) {
		this.poaHolderFlag = poaHolderFlag;
	}
	public int getClaimantFlag() {
		return claimantFlag;
	}
	public void setClaimantFlag(int claimantFlag) {
		this.claimantFlag = claimantFlag;
	}
	public int getAgreeMemoInstFlag() {
		return agreeMemoInstFlag;
	}
	public void setAgreeMemoInstFlag(int agreeMemoInstFlag) {
		this.agreeMemoInstFlag = agreeMemoInstFlag;
	}
	public String getContriProp() {
		return contriProp;
	}
	public void setContriProp(String contriProp) {
		this.contriProp = contriProp;
	}
	public String getExtraFieldLabel() {
		return extraFieldLabel;
	}
	public void setExtraFieldLabel(String extraFieldLabel) {
		this.extraFieldLabel = extraFieldLabel;
	}
	public double getPaidLoanAmt() {
		return paidLoanAmt;
	}
	public void setPaidLoanAmt(double paidLoanAmt) {
		this.paidLoanAmt = paidLoanAmt;
	}
	public String getPoaWithConsid() {
		return poaWithConsid;
	}
	public void setPoaWithConsid(String poaWithConsid) {
		this.poaWithConsid = poaWithConsid;
	}
	public int getPoaPeriod() {
		return poaPeriod;
	}
	public void setPoaPeriod(int poaPeriod) {
		this.poaPeriod = poaPeriod;
	}
	public String[] getPartyTxnIdArr() {
		return partyTxnIdArr;
	}
	public void setPartyTxnIdArr(String[] partyTxnIdArr) {
		this.partyTxnIdArr = partyTxnIdArr;
	}
	public ArrayList getTransPartyListPVDeed() {
		return transPartyListPVDeed;
	}
	public void setTransPartyListPVDeed(ArrayList transPartyListPVDeed) {
		this.transPartyListPVDeed = transPartyListPVDeed;
	}
	public ArrayList getPropListPVDeed() {
		return propListPVDeed;
	}
	public void setPropListPVDeed(ArrayList propListPVDeed) {
		this.propListPVDeed = propListPVDeed;
	}
	public String getPossGivenName() {
		return possGivenName;
	}
	public void setPossGivenName(String possGivenName) {
		this.possGivenName = possGivenName;
	}
	public String getAdvancePaymntDate() {
		return advancePaymntDate;
	}
	public void setAdvancePaymntDate(String advancePaymntDate) {
		this.advancePaymntDate = advancePaymntDate;
	}
	public String getPossGiven() {
		return possGiven;
	}
	public void setPossGiven(String possGiven) {
		this.possGiven = possGiven;
	}
	public String getIndcountryNameArb() {
		return indcountryNameArb;
	}
	public void setIndcountryNameArb(String indcountryNameArb) {
		this.indcountryNameArb = indcountryNameArb;
	}
	public String getIndstatenameNameArb() {
		return indstatenameNameArb;
	}
	public void setIndstatenameNameArb(String indstatenameNameArb) {
		this.indstatenameNameArb = indstatenameNameArb;
	}
	public String getGendarNameArb() {
		return gendarNameArb;
	}
	public void setGendarNameArb(String gendarNameArb) {
		this.gendarNameArb = gendarNameArb;
	}
	public String getInddistrictNameArb() {
		return inddistrictNameArb;
	}
	public void setInddistrictNameArb(String inddistrictNameArb) {
		this.inddistrictNameArb = inddistrictNameArb;
	}
	public String getListIDNameArb() {
		return listIDNameArb;
	}
	public void setListIDNameArb(String listIDNameArb) {
		this.listIDNameArb = listIDNameArb;
	}
	public String getIndCategoryNameArb() {
		return indCategoryNameArb;
	}
	public void setIndCategoryNameArb(String indCategoryNameArb) {
		this.indCategoryNameArb = indCategoryNameArb;
	}
	public String getIndDisabilityNameArb() {
		return indDisabilityNameArb;
	}
	public void setIndDisabilityNameArb(String indDisabilityNameArb) {
		this.indDisabilityNameArb = indDisabilityNameArb;
	}
	public String getFnameArb() {
		return fnameArb;
	}
	public void setFnameArb(String fnameArb) {
		this.fnameArb = fnameArb;
	}
	public String getMnameArb() {
		return mnameArb;
	}
	public void setMnameArb(String mnameArb) {
		this.mnameArb = mnameArb;
	}
	public String getLnameArb() {
		return lnameArb;
	}
	public void setLnameArb(String lnameArb) {
		this.lnameArb = lnameArb;
	}
	public String getGendarArb() {
		return gendarArb;
	}
	public void setGendarArb(String gendarArb) {
		this.gendarArb = gendarArb;
	}
	public String getAgeArb() {
		return ageArb;
	}
	public void setAgeArb(String ageArb) {
		this.ageArb = ageArb;
	}
	public String getFatherNameArb() {
		return fatherNameArb;
	}
	public void setFatherNameArb(String fatherNameArb) {
		this.fatherNameArb = fatherNameArb;
	}
	public String getMotherNameArb() {
		return motherNameArb;
	}
	public void setMotherNameArb(String motherNameArb) {
		this.motherNameArb = motherNameArb;
	}
	public String getSpouseNameArb() {
		return spouseNameArb;
	}
	public void setSpouseNameArb(String spouseNameArb) {
		this.spouseNameArb = spouseNameArb;
	}
	public String getNationalityArb() {
		return nationalityArb;
	}
	public void setNationalityArb(String nationalityArb) {
		this.nationalityArb = nationalityArb;
	}
	public String getEmailIDArb() {
		return emailIDArb;
	}
	public void setEmailIDArb(String emailIDArb) {
		this.emailIDArb = emailIDArb;
	}
	public String getIndaddressArb() {
		return indaddressArb;
	}
	public void setIndaddressArb(String indaddressArb) {
		this.indaddressArb = indaddressArb;
	}
	public String getIndcountryArb() {
		return indcountryArb;
	}
	public void setIndcountryArb(String indcountryArb) {
		this.indcountryArb = indcountryArb;
	}
	public String getIndstatenameArb() {
		return indstatenameArb;
	}
	public void setIndstatenameArb(String indstatenameArb) {
		this.indstatenameArb = indstatenameArb;
	}
	public String getInddistrictArb() {
		return inddistrictArb;
	}
	public void setInddistrictArb(String inddistrictArb) {
		this.inddistrictArb = inddistrictArb;
	}
	public String getIndphnoArb() {
		return indphnoArb;
	}
	public void setIndphnoArb(String indphnoArb) {
		this.indphnoArb = indphnoArb;
	}
	public String getIndmobnoArb() {
		return indmobnoArb;
	}
	public void setIndmobnoArb(String indmobnoArb) {
		this.indmobnoArb = indmobnoArb;
	}
	public String getListIDArb() {
		return listIDArb;
	}
	public void setListIDArb(String listIDArb) {
		this.listIDArb = listIDArb;
	}
	public String getIdnoArb() {
		return idnoArb;
	}
	public void setIdnoArb(String idnoArb) {
		this.idnoArb = idnoArb;
	}
	public String getIndCategoryArb() {
		return indCategoryArb;
	}
	public void setIndCategoryArb(String indCategoryArb) {
		this.indCategoryArb = indCategoryArb;
	}
	public String getIndDisabilityDescArb() {
		return indDisabilityDescArb;
	}
	public void setIndDisabilityDescArb(String indDisabilityDescArb) {
		this.indDisabilityDescArb = indDisabilityDescArb;
	}
	public String getIndDisabilityArb() {
		return indDisabilityArb;
	}
	public void setIndDisabilityArb(String indDisabilityArb) {
		this.indDisabilityArb = indDisabilityArb;
	}
	public String getHdnIndDisabilityArb() {
		return hdnIndDisabilityArb;
	}
	public void setHdnIndDisabilityArb(String hdnIndDisabilityArb) {
		this.hdnIndDisabilityArb = hdnIndDisabilityArb;
	}
	public String getWithPos() {
		return withPos;
	}
	public void setWithPos(String withPos) {
		this.withPos = withPos;
	}
	public double getSecLoanAmt() {
		return secLoanAmt;
	}
	public void setSecLoanAmt(double secLoanAmt) {
		this.secLoanAmt = secLoanAmt;
	}
	public String getCallingAction() {
		return callingAction;
	}
	public void setCallingAction(String callingAction) {
		this.callingAction = callingAction;
	}
	public double getTermLease() {
		return termLease;
	}
	public void setTermLease(double termLease) {
		this.termLease = termLease;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getAdvance() {
		return advance;
	}
	public void setAdvance(double advance) {
		this.advance = advance;
	}
	public double getDevlpmtCharge() {
		return devlpmtCharge;
	}
	public void setDevlpmtCharge(double devlpmtCharge) {
		this.devlpmtCharge = devlpmtCharge;
	}
	public double getOtherRecCharge() {
		return otherRecCharge;
	}
	public void setOtherRecCharge(double otherRecCharge) {
		this.otherRecCharge = otherRecCharge;
	}
	public double getPremium() {
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}
	public int getTotalShareOfPropLesser() {
		return totalShareOfPropLesser;
	}
	public void setTotalShareOfPropLesser(int totalShareOfPropLesser) {
		this.totalShareOfPropLesser = totalShareOfPropLesser;
	}
	public int getTotalShareOfPropLessee() {
		return totalShareOfPropLessee;
	}
	public void setTotalShareOfPropLessee(int totalShareOfPropLessee) {
		this.totalShareOfPropLessee = totalShareOfPropLessee;
	}
	public int getCancelDeedRadio() {
		return cancelDeedRadio;
	}
	public void setCancelDeedRadio(int cancelDeedRadio) {
		this.cancelDeedRadio = cancelDeedRadio;
	}
	public int getAdoptionDeedParty1Added() {
		return adoptionDeedParty1Added;
	}
	public void setAdoptionDeedParty1Added(int adoptionDeedParty1Added) {
		this.adoptionDeedParty1Added = adoptionDeedParty1Added;
	}
	public int getAdoptionDeedParty2Added() {
		return adoptionDeedParty2Added;
	}
	public void setAdoptionDeedParty2Added(int adoptionDeedParty2Added) {
		this.adoptionDeedParty2Added = adoptionDeedParty2Added;
	}
	public int getAdoptionDeedParty3Added() {
		return adoptionDeedParty3Added;
	}
	public void setAdoptionDeedParty3Added(int adoptionDeedParty3Added) {
		this.adoptionDeedParty3Added = adoptionDeedParty3Added;
	}
	public int getDeedTypeFlag() {
		return deedTypeFlag;
	}
	public void setDeedTypeFlag(int deedTypeFlag) {
		this.deedTypeFlag = deedTypeFlag;
	}
	public String getTrustName() {
		return trustName;
	}
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}
	public String getTrustDate() {
		return trustDate;
	}
	public void setTrustDate(String trustDate) {
		this.trustDate = trustDate;
	}
	public int getExtraDetlsModifyFlag() {
		return extraDetlsModifyFlag;
	}
	public void setExtraDetlsModifyFlag(int extraDetlsModifyFlag) {
		this.extraDetlsModifyFlag = extraDetlsModifyFlag;
	}
	public String getAddMoreTransParty() {
		return addMoreTransParty;
	}
	public void setAddMoreTransParty(String addMoreTransParty) {
		this.addMoreTransParty = addMoreTransParty;
	}
	public String getHdnPostalAddress1() {
		return hdnPostalAddress1;
	}
	public void setHdnPostalAddress1(String hdnPostalAddress1) {
		this.hdnPostalAddress1 = hdnPostalAddress1;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getPostalCountry() {
		return postalCountry;
	}
	public void setPostalCountry(String postalCountry) {
		this.postalCountry = postalCountry;
	}
	public String getPostalState() {
		return postalState;
	}
	public void setPostalState(String postalState) {
		this.postalState = postalState;
	}
	public String getPostalDistrict() {
		return postalDistrict;
	}
	public void setPostalDistrict(String postalDistrict) {
		this.postalDistrict = postalDistrict;
	}
	public String getPostalAddress1() {
		return postalAddress1;
	}
	public void setPostalAddress1(String postalAddress1) {
		this.postalAddress1 = postalAddress1;
	}
	public String getPostalAddress2() {
		return postalAddress2;
	}
	public void setPostalAddress2(String postalAddress2) {
		this.postalAddress2 = postalAddress2;
	}
	public int getPaymentCompleteFlag() {
		return paymentCompleteFlag;
	}
	public String getHdnIndDisabilityTrns() {
		return hdnIndDisabilityTrns;
	}
	public void setHdnIndDisabilityTrns(String hdnIndDisabilityTrns) {
		this.hdnIndDisabilityTrns = hdnIndDisabilityTrns;
	}
	public String getHdnIndScheduleAreaTrns() {
		return hdnIndScheduleAreaTrns;
	}
	public void setHdnIndScheduleAreaTrns(String hdnIndScheduleAreaTrns) {
		this.hdnIndScheduleAreaTrns = hdnIndScheduleAreaTrns;
	}
	public String getHdnIndDisability() {
		return hdnIndDisability;
	}
	public void setHdnIndDisability(String hdnIndDisability) {
		this.hdnIndDisability = hdnIndDisability;
	}
	public String getHdnIndScheduleArea() {
		return hdnIndScheduleArea;
	}
	public void setHdnIndScheduleArea(String hdnIndScheduleArea) {
		this.hdnIndScheduleArea = hdnIndScheduleArea;
	}
	public String getOwnerIdTrns() {
		return ownerIdTrns;
	}
	public void setOwnerIdTrns(String ownerIdTrns) {
		this.ownerIdTrns = ownerIdTrns;
	}
	public int getPropertyModifyFlag() {
		return propertyModifyFlag;
	}
	public void setPropertyModifyFlag(int propertyModifyFlag) {
		this.propertyModifyFlag = propertyModifyFlag;
	}
	public String getPartyTxnId() {
		return partyTxnId;
	}
	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}
	public int getPartyModifyFlag() {
		return partyModifyFlag;
	}
	public void setPartyModifyFlag(int partyModifyFlag) {
		this.partyModifyFlag = partyModifyFlag;
	}
	public void setPaymentCompleteFlag(int paymentCompleteFlag) {
		this.paymentCompleteFlag = paymentCompleteFlag;
	}
	public String getPaymentTxnSeqId() {
		return paymentTxnSeqId;
	}
	public void setPaymentTxnSeqId(String paymentTxnSeqId) {
		this.paymentTxnSeqId = paymentTxnSeqId;
	}
	public String getTotalPayableAmount() {
		return totalPayableAmount;
	}
	public void setTotalPayableAmount(String totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}
	public String getTotalPaidAmount() {
		return totalPaidAmount;
	}
	public void setTotalPaidAmount(String totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}
	public String getTotalBalanceAmount() {
		return totalBalanceAmount;
	}
	public void setTotalBalanceAmount(String totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}
	public ArrayList getTransPartyListNonPropDeed() {
		return transPartyListNonPropDeed;
	}
	public void setTransPartyListNonPropDeed(ArrayList transPartyListNonPropDeed) {
		this.transPartyListNonPropDeed = transPartyListNonPropDeed;
	}
	public ArrayList getPropListNonPropDeed() {
		return propListNonPropDeed;
	}
	public void setPropListNonPropDeed(ArrayList propListNonPropDeed) {
		this.propListNonPropDeed = propListNonPropDeed;
	}
	public String getIndCategory() {
		return indCategory;
	}
	public String getSrOfficeName() {
		return srOfficeName;
	}
	public void setSrOfficeName(String srOfficeName) {
		this.srOfficeName = srOfficeName;
	}
	public String getPoaRegNo() {
		return poaRegNo;
	}
	public void setPoaRegNo(String poaRegNo) {
		this.poaRegNo = poaRegNo;
	}
	public String getDatePoaReg() {
		return datePoaReg;
	}
	public void setDatePoaReg(String datePoaReg) {
		this.datePoaReg = datePoaReg;
	}
	public String getSrOfficeNameTrns() {
		return srOfficeNameTrns;
	}
	public void setSrOfficeNameTrns(String srOfficeNameTrns) {
		this.srOfficeNameTrns = srOfficeNameTrns;
	}
	public String getPoaRegNoTrns() {
		return poaRegNoTrns;
	}
	public void setPoaRegNoTrns(String poaRegNoTrns) {
		this.poaRegNoTrns = poaRegNoTrns;
	}
	public String getDatePoaRegTrns() {
		return datePoaRegTrns;
	}
	public void setDatePoaRegTrns(String datePoaRegTrns) {
		this.datePoaRegTrns = datePoaRegTrns;
	}
	public FormFile getU10() {
		return u10;
	}
	public void setU10(FormFile u10) {
		this.u10 = u10;
	}
	public String getU10DocumentName() {
		return u10DocumentName;
	}
	public void setU10DocumentName(String documentName) {
		u10DocumentName = documentName;
	}
	public String getU10DocumentSize() {
		return u10DocumentSize;
	}
	public void setU10DocumentSize(String documentSize) {
		u10DocumentSize = documentSize;
	}
	public byte[] getU10DocContents() {
		return u10DocContents;
	}
	public void setU10DocContents(byte[] docContents) {
		u10DocContents = docContents;
	}
	public String getU10PhotoCheck() {
		return u10PhotoCheck;
	}
	public void setU10PhotoCheck(String photoCheck) {
		u10PhotoCheck = photoCheck;
	}
	public String getU10FilePath() {
		return u10FilePath;
	}
	public void setU10FilePath(String filePath) {
		u10FilePath = filePath;
	}
	public String getU10PartyOrProp() {
		return u10PartyOrProp;
	}
	public void setU10PartyOrProp(String partyOrProp) {
		u10PartyOrProp = partyOrProp;
	}
	public String getU10UploadType() {
		return u10UploadType;
	}
	public void setU10UploadType(String uploadType) {
		u10UploadType = uploadType;
	}
	public FormFile getU10Trns() {
		return u10Trns;
	}
	public void setU10Trns(FormFile trns) {
		u10Trns = trns;
	}
	public String getU10DocumentNameTrns() {
		return u10DocumentNameTrns;
	}
	public void setU10DocumentNameTrns(String documentNameTrns) {
		u10DocumentNameTrns = documentNameTrns;
	}
	public String getU10DocumentSizeTrns() {
		return u10DocumentSizeTrns;
	}
	public void setU10DocumentSizeTrns(String documentSizeTrns) {
		u10DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU10DocContentsTrns() {
		return u10DocContentsTrns;
	}
	public void setU10DocContentsTrns(byte[] docContentsTrns) {
		u10DocContentsTrns = docContentsTrns;
	}
	public String getU10PhotoCheckTrns() {
		return u10PhotoCheckTrns;
	}
	public void setU10PhotoCheckTrns(String photoCheckTrns) {
		u10PhotoCheckTrns = photoCheckTrns;
	}
	public String getU10FilePathTrns() {
		return u10FilePathTrns;
	}
	public void setU10FilePathTrns(String filePathTrns) {
		u10FilePathTrns = filePathTrns;
	}
	public String getU10PartyOrPropTrns() {
		return u10PartyOrPropTrns;
	}
	public void setU10PartyOrPropTrns(String partyOrPropTrns) {
		u10PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU10UploadTypeTrns() {
		return u10UploadTypeTrns;
	}
	public void setU10UploadTypeTrns(String uploadTypeTrns) {
		u10UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU11() {
		return u11;
	}
	public void setU11(FormFile u11) {
		this.u11 = u11;
	}
	public String getU11DocumentName() {
		return u11DocumentName;
	}
	public void setU11DocumentName(String documentName) {
		u11DocumentName = documentName;
	}
	public String getU11DocumentSize() {
		return u11DocumentSize;
	}
	public void setU11DocumentSize(String documentSize) {
		u11DocumentSize = documentSize;
	}
	public byte[] getU11DocContents() {
		return u11DocContents;
	}
	public void setU11DocContents(byte[] docContents) {
		u11DocContents = docContents;
	}
	public String getU11PhotoCheck() {
		return u11PhotoCheck;
	}
	public void setU11PhotoCheck(String photoCheck) {
		u11PhotoCheck = photoCheck;
	}
	public String getU11FilePath() {
		return u11FilePath;
	}
	public void setU11FilePath(String filePath) {
		u11FilePath = filePath;
	}
	public String getU11PartyOrProp() {
		return u11PartyOrProp;
	}
	public void setU11PartyOrProp(String partyOrProp) {
		u11PartyOrProp = partyOrProp;
	}
	public String getU11UploadType() {
		return u11UploadType;
	}
	public void setU11UploadType(String uploadType) {
		u11UploadType = uploadType;
	}
	public FormFile getU11Trns() {
		return u11Trns;
	}
	public void setU11Trns(FormFile trns) {
		u11Trns = trns;
	}
	public String getU11DocumentNameTrns() {
		return u11DocumentNameTrns;
	}
	public void setU11DocumentNameTrns(String documentNameTrns) {
		u11DocumentNameTrns = documentNameTrns;
	}
	public String getU11DocumentSizeTrns() {
		return u11DocumentSizeTrns;
	}
	public void setU11DocumentSizeTrns(String documentSizeTrns) {
		u11DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU11DocContentsTrns() {
		return u11DocContentsTrns;
	}
	public void setU11DocContentsTrns(byte[] docContentsTrns) {
		u11DocContentsTrns = docContentsTrns;
	}
	public String getU11PhotoCheckTrns() {
		return u11PhotoCheckTrns;
	}
	public void setU11PhotoCheckTrns(String photoCheckTrns) {
		u11PhotoCheckTrns = photoCheckTrns;
	}
	public String getU11FilePathTrns() {
		return u11FilePathTrns;
	}
	public void setU11FilePathTrns(String filePathTrns) {
		u11FilePathTrns = filePathTrns;
	}
	public String getU11PartyOrPropTrns() {
		return u11PartyOrPropTrns;
	}
	public void setU11PartyOrPropTrns(String partyOrPropTrns) {
		u11PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU11UploadTypeTrns() {
		return u11UploadTypeTrns;
	}
	public void setU11UploadTypeTrns(String uploadTypeTrns) {
		u11UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU9() {
		return u9;
	}
	public void setU9(FormFile u9) {
		this.u9 = u9;
	}
	public String getU9DocumentName() {
		return u9DocumentName;
	}
	public void setU9DocumentName(String documentName) {
		u9DocumentName = documentName;
	}
	public String getU9DocumentSize() {
		return u9DocumentSize;
	}
	public void setU9DocumentSize(String documentSize) {
		u9DocumentSize = documentSize;
	}
	public byte[] getU9DocContents() {
		return u9DocContents;
	}
	public void setU9DocContents(byte[] docContents) {
		u9DocContents = docContents;
	}
	public String getU9PhotoCheck() {
		return u9PhotoCheck;
	}
	public void setU9PhotoCheck(String photoCheck) {
		u9PhotoCheck = photoCheck;
	}
	public String getU9FilePath() {
		return u9FilePath;
	}
	public void setU9FilePath(String filePath) {
		u9FilePath = filePath;
	}
	public String getU9PartyOrProp() {
		return u9PartyOrProp;
	}
	public void setU9PartyOrProp(String partyOrProp) {
		u9PartyOrProp = partyOrProp;
	}
	public String getU9UploadType() {
		return u9UploadType;
	}
	public void setU9UploadType(String uploadType) {
		u9UploadType = uploadType;
	}
	public FormFile getU9Trns() {
		return u9Trns;
	}
	public void setU9Trns(FormFile trns) {
		u9Trns = trns;
	}
	public String getU9DocumentNameTrns() {
		return u9DocumentNameTrns;
	}
	public void setU9DocumentNameTrns(String documentNameTrns) {
		u9DocumentNameTrns = documentNameTrns;
	}
	public String getU9DocumentSizeTrns() {
		return u9DocumentSizeTrns;
	}
	public void setU9DocumentSizeTrns(String documentSizeTrns) {
		u9DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU9DocContentsTrns() {
		return u9DocContentsTrns;
	}
	public void setU9DocContentsTrns(byte[] docContentsTrns) {
		u9DocContentsTrns = docContentsTrns;
	}
	public String getU9PhotoCheckTrns() {
		return u9PhotoCheckTrns;
	}
	public void setU9PhotoCheckTrns(String photoCheckTrns) {
		u9PhotoCheckTrns = photoCheckTrns;
	}
	public String getU9FilePathTrns() {
		return u9FilePathTrns;
	}
	public void setU9FilePathTrns(String filePathTrns) {
		u9FilePathTrns = filePathTrns;
	}
	public String getU9PartyOrPropTrns() {
		return u9PartyOrPropTrns;
	}
	public void setU9PartyOrPropTrns(String partyOrPropTrns) {
		u9PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU9UploadTypeTrns() {
		return u9UploadTypeTrns;
	}
	public void setU9UploadTypeTrns(String uploadTypeTrns) {
		u9UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU8() {
		return u8;
	}
	public void setU8(FormFile u8) {
		this.u8 = u8;
	}
	public String getU8DocumentName() {
		return u8DocumentName;
	}
	public void setU8DocumentName(String documentName) {
		u8DocumentName = documentName;
	}
	public String getU8DocumentSize() {
		return u8DocumentSize;
	}
	public void setU8DocumentSize(String documentSize) {
		u8DocumentSize = documentSize;
	}
	public byte[] getU8DocContents() {
		return u8DocContents;
	}
	public void setU8DocContents(byte[] docContents) {
		u8DocContents = docContents;
	}
	public String getU8PhotoCheck() {
		return u8PhotoCheck;
	}
	public void setU8PhotoCheck(String photoCheck) {
		u8PhotoCheck = photoCheck;
	}
	public String getU8FilePath() {
		return u8FilePath;
	}
	public void setU8FilePath(String filePath) {
		u8FilePath = filePath;
	}
	public String getU8PartyOrProp() {
		return u8PartyOrProp;
	}
	public void setU8PartyOrProp(String partyOrProp) {
		u8PartyOrProp = partyOrProp;
	}
	public String getU8UploadType() {
		return u8UploadType;
	}
	public void setU8UploadType(String uploadType) {
		u8UploadType = uploadType;
	}
	public FormFile getU8Trns() {
		return u8Trns;
	}
	public void setU8Trns(FormFile trns) {
		u8Trns = trns;
	}
	public String getU8DocumentNameTrns() {
		return u8DocumentNameTrns;
	}
	public void setU8DocumentNameTrns(String documentNameTrns) {
		u8DocumentNameTrns = documentNameTrns;
	}
	public String getU8DocumentSizeTrns() {
		return u8DocumentSizeTrns;
	}
	public void setU8DocumentSizeTrns(String documentSizeTrns) {
		u8DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU8DocContentsTrns() {
		return u8DocContentsTrns;
	}
	public void setU8DocContentsTrns(byte[] docContentsTrns) {
		u8DocContentsTrns = docContentsTrns;
	}
	public String getU8PhotoCheckTrns() {
		return u8PhotoCheckTrns;
	}
	public void setU8PhotoCheckTrns(String photoCheckTrns) {
		u8PhotoCheckTrns = photoCheckTrns;
	}
	public String getU8FilePathTrns() {
		return u8FilePathTrns;
	}
	public void setU8FilePathTrns(String filePathTrns) {
		u8FilePathTrns = filePathTrns;
	}
	public String getU8PartyOrPropTrns() {
		return u8PartyOrPropTrns;
	}
	public void setU8PartyOrPropTrns(String partyOrPropTrns) {
		u8PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU8UploadTypeTrns() {
		return u8UploadTypeTrns;
	}
	public void setU8UploadTypeTrns(String uploadTypeTrns) {
		u8UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU7() {
		return u7;
	}
	public void setU7(FormFile u7) {
		this.u7 = u7;
	}
	public String getU7DocumentName() {
		return u7DocumentName;
	}
	public void setU7DocumentName(String documentName) {
		u7DocumentName = documentName;
	}
	public String getU7DocumentSize() {
		return u7DocumentSize;
	}
	public void setU7DocumentSize(String documentSize) {
		u7DocumentSize = documentSize;
	}
	public byte[] getU7DocContents() {
		return u7DocContents;
	}
	public void setU7DocContents(byte[] docContents) {
		u7DocContents = docContents;
	}
	public String getU7PhotoCheck() {
		return u7PhotoCheck;
	}
	public void setU7PhotoCheck(String photoCheck) {
		u7PhotoCheck = photoCheck;
	}
	public String getU7FilePath() {
		return u7FilePath;
	}
	public void setU7FilePath(String filePath) {
		u7FilePath = filePath;
	}
	public String getU7PartyOrProp() {
		return u7PartyOrProp;
	}
	public void setU7PartyOrProp(String partyOrProp) {
		u7PartyOrProp = partyOrProp;
	}
	public String getU7UploadType() {
		return u7UploadType;
	}
	public void setU7UploadType(String uploadType) {
		u7UploadType = uploadType;
	}
	public FormFile getU7Trns() {
		return u7Trns;
	}
	public void setU7Trns(FormFile trns) {
		u7Trns = trns;
	}
	public String getU7DocumentNameTrns() {
		return u7DocumentNameTrns;
	}
	public void setU7DocumentNameTrns(String documentNameTrns) {
		u7DocumentNameTrns = documentNameTrns;
	}
	public String getU7DocumentSizeTrns() {
		return u7DocumentSizeTrns;
	}
	public void setU7DocumentSizeTrns(String documentSizeTrns) {
		u7DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU7DocContentsTrns() {
		return u7DocContentsTrns;
	}
	public void setU7DocContentsTrns(byte[] docContentsTrns) {
		u7DocContentsTrns = docContentsTrns;
	}
	public String getU7PhotoCheckTrns() {
		return u7PhotoCheckTrns;
	}
	public void setU7PhotoCheckTrns(String photoCheckTrns) {
		u7PhotoCheckTrns = photoCheckTrns;
	}
	public String getU7FilePathTrns() {
		return u7FilePathTrns;
	}
	public void setU7FilePathTrns(String filePathTrns) {
		u7FilePathTrns = filePathTrns;
	}
	public String getU7PartyOrPropTrns() {
		return u7PartyOrPropTrns;
	}
	public void setU7PartyOrPropTrns(String partyOrPropTrns) {
		u7PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU7UploadTypeTrns() {
		return u7UploadTypeTrns;
	}
	public void setU7UploadTypeTrns(String uploadTypeTrns) {
		u7UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU5() {
		return u5;
	}
	public void setU5(FormFile u5) {
		this.u5 = u5;
	}
	public String getU5DocumentName() {
		return u5DocumentName;
	}
	public void setU5DocumentName(String documentName) {
		u5DocumentName = documentName;
	}
	public String getU5DocumentSize() {
		return u5DocumentSize;
	}
	public void setU5DocumentSize(String documentSize) {
		u5DocumentSize = documentSize;
	}
	public byte[] getU5DocContents() {
		return u5DocContents;
	}
	public void setU5DocContents(byte[] docContents) {
		u5DocContents = docContents;
	}
	public String getU5PhotoCheck() {
		return u5PhotoCheck;
	}
	public void setU5PhotoCheck(String photoCheck) {
		u5PhotoCheck = photoCheck;
	}
	public String getU5FilePath() {
		return u5FilePath;
	}
	public void setU5FilePath(String filePath) {
		u5FilePath = filePath;
	}
	public String getU5PartyOrProp() {
		return u5PartyOrProp;
	}
	public void setU5PartyOrProp(String partyOrProp) {
		u5PartyOrProp = partyOrProp;
	}
	public String getU5UploadType() {
		return u5UploadType;
	}
	public void setU5UploadType(String uploadType) {
		u5UploadType = uploadType;
	}
	public FormFile getU5Trns() {
		return u5Trns;
	}
	public void setU5Trns(FormFile trns) {
		u5Trns = trns;
	}
	public String getU5DocumentNameTrns() {
		return u5DocumentNameTrns;
	}
	public void setU5DocumentNameTrns(String documentNameTrns) {
		u5DocumentNameTrns = documentNameTrns;
	}
	public String getU5DocumentSizeTrns() {
		return u5DocumentSizeTrns;
	}
	public void setU5DocumentSizeTrns(String documentSizeTrns) {
		u5DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU5DocContentsTrns() {
		return u5DocContentsTrns;
	}
	public void setU5DocContentsTrns(byte[] docContentsTrns) {
		u5DocContentsTrns = docContentsTrns;
	}
	public String getU5PhotoCheckTrns() {
		return u5PhotoCheckTrns;
	}
	public void setU5PhotoCheckTrns(String photoCheckTrns) {
		u5PhotoCheckTrns = photoCheckTrns;
	}
	public String getU5FilePathTrns() {
		return u5FilePathTrns;
	}
	public void setU5FilePathTrns(String filePathTrns) {
		u5FilePathTrns = filePathTrns;
	}
	public String getU5PartyOrPropTrns() {
		return u5PartyOrPropTrns;
	}
	public void setU5PartyOrPropTrns(String partyOrPropTrns) {
		u5PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU5UploadTypeTrns() {
		return u5UploadTypeTrns;
	}
	public void setU5UploadTypeTrns(String uploadTypeTrns) {
		u5UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU6() {
		return u6;
	}
	public void setU6(FormFile u6) {
		this.u6 = u6;
	}
	public String getU6DocumentName() {
		return u6DocumentName;
	}
	public void setU6DocumentName(String documentName) {
		u6DocumentName = documentName;
	}
	public String getU6DocumentSize() {
		return u6DocumentSize;
	}
	public void setU6DocumentSize(String documentSize) {
		u6DocumentSize = documentSize;
	}
	public byte[] getU6DocContents() {
		return u6DocContents;
	}
	public void setU6DocContents(byte[] docContents) {
		u6DocContents = docContents;
	}
	public String getU6PhotoCheck() {
		return u6PhotoCheck;
	}
	public void setU6PhotoCheck(String photoCheck) {
		u6PhotoCheck = photoCheck;
	}
	public String getU6FilePath() {
		return u6FilePath;
	}
	public void setU6FilePath(String filePath) {
		u6FilePath = filePath;
	}
	public String getU6PartyOrProp() {
		return u6PartyOrProp;
	}
	public void setU6PartyOrProp(String partyOrProp) {
		u6PartyOrProp = partyOrProp;
	}
	public String getU6UploadType() {
		return u6UploadType;
	}
	public void setU6UploadType(String uploadType) {
		u6UploadType = uploadType;
	}
	public FormFile getU6Trns() {
		return u6Trns;
	}
	public void setU6Trns(FormFile trns) {
		u6Trns = trns;
	}
	public String getU6DocumentNameTrns() {
		return u6DocumentNameTrns;
	}
	public void setU6DocumentNameTrns(String documentNameTrns) {
		u6DocumentNameTrns = documentNameTrns;
	}
	public String getU6DocumentSizeTrns() {
		return u6DocumentSizeTrns;
	}
	public void setU6DocumentSizeTrns(String documentSizeTrns) {
		u6DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU6DocContentsTrns() {
		return u6DocContentsTrns;
	}
	public void setU6DocContentsTrns(byte[] docContentsTrns) {
		u6DocContentsTrns = docContentsTrns;
	}
	public String getU6PhotoCheckTrns() {
		return u6PhotoCheckTrns;
	}
	public void setU6PhotoCheckTrns(String photoCheckTrns) {
		u6PhotoCheckTrns = photoCheckTrns;
	}
	public String getU6FilePathTrns() {
		return u6FilePathTrns;
	}
	public void setU6FilePathTrns(String filePathTrns) {
		u6FilePathTrns = filePathTrns;
	}
	public String getU6PartyOrPropTrns() {
		return u6PartyOrPropTrns;
	}
	public void setU6PartyOrPropTrns(String partyOrPropTrns) {
		u6PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU6UploadTypeTrns() {
		return u6UploadTypeTrns;
	}
	public void setU6UploadTypeTrns(String uploadTypeTrns) {
		u6UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU4() {
		return u4;
	}
	public void setU4(FormFile u4) {
		this.u4 = u4;
	}
	public String getU4DocumentName() {
		return u4DocumentName;
	}
	public void setU4DocumentName(String documentName) {
		u4DocumentName = documentName;
	}
	public String getU4DocumentSize() {
		return u4DocumentSize;
	}
	public void setU4DocumentSize(String documentSize) {
		u4DocumentSize = documentSize;
	}
	public byte[] getU4DocContents() {
		return u4DocContents;
	}
	public void setU4DocContents(byte[] docContents) {
		u4DocContents = docContents;
	}
	public String getU4PhotoCheck() {
		return u4PhotoCheck;
	}
	public void setU4PhotoCheck(String photoCheck) {
		u4PhotoCheck = photoCheck;
	}
	public String getU4FilePath() {
		return u4FilePath;
	}
	public void setU4FilePath(String filePath) {
		u4FilePath = filePath;
	}
	public String getU4PartyOrProp() {
		return u4PartyOrProp;
	}
	public void setU4PartyOrProp(String partyOrProp) {
		u4PartyOrProp = partyOrProp;
	}
	public String getU4UploadType() {
		return u4UploadType;
	}
	public void setU4UploadType(String uploadType) {
		u4UploadType = uploadType;
	}
	public FormFile getU4Trns() {
		return u4Trns;
	}
	public void setU4Trns(FormFile trns) {
		u4Trns = trns;
	}
	public String getU4DocumentNameTrns() {
		return u4DocumentNameTrns;
	}
	public void setU4DocumentNameTrns(String documentNameTrns) {
		u4DocumentNameTrns = documentNameTrns;
	}
	public String getU4DocumentSizeTrns() {
		return u4DocumentSizeTrns;
	}
	public void setU4DocumentSizeTrns(String documentSizeTrns) {
		u4DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU4DocContentsTrns() {
		return u4DocContentsTrns;
	}
	public void setU4DocContentsTrns(byte[] docContentsTrns) {
		u4DocContentsTrns = docContentsTrns;
	}
	public String getU4PhotoCheckTrns() {
		return u4PhotoCheckTrns;
	}
	public void setU4PhotoCheckTrns(String photoCheckTrns) {
		u4PhotoCheckTrns = photoCheckTrns;
	}
	public String getU4FilePathTrns() {
		return u4FilePathTrns;
	}
	public void setU4FilePathTrns(String filePathTrns) {
		u4FilePathTrns = filePathTrns;
	}
	public String getU4PartyOrPropTrns() {
		return u4PartyOrPropTrns;
	}
	public void setU4PartyOrPropTrns(String partyOrPropTrns) {
		u4PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU4UploadTypeTrns() {
		return u4UploadTypeTrns;
	}
	public void setU4UploadTypeTrns(String uploadTypeTrns) {
		u4UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU3() {
		return u3;
	}
	public void setU3(FormFile u3) {
		this.u3 = u3;
	}
	public String getU3DocumentName() {
		return u3DocumentName;
	}
	public void setU3DocumentName(String documentName) {
		u3DocumentName = documentName;
	}
	public String getU3DocumentSize() {
		return u3DocumentSize;
	}
	public void setU3DocumentSize(String documentSize) {
		u3DocumentSize = documentSize;
	}
	public byte[] getU3DocContents() {
		return u3DocContents;
	}
	public void setU3DocContents(byte[] docContents) {
		u3DocContents = docContents;
	}
	public String getU3PhotoCheck() {
		return u3PhotoCheck;
	}
	public void setU3PhotoCheck(String photoCheck) {
		u3PhotoCheck = photoCheck;
	}
	public String getU3FilePath() {
		return u3FilePath;
	}
	public void setU3FilePath(String filePath) {
		u3FilePath = filePath;
	}
	public String getU3PartyOrProp() {
		return u3PartyOrProp;
	}
	public void setU3PartyOrProp(String partyOrProp) {
		u3PartyOrProp = partyOrProp;
	}
	public String getU3UploadType() {
		return u3UploadType;
	}
	public void setU3UploadType(String uploadType) {
		u3UploadType = uploadType;
	}
	public FormFile getU3Trns() {
		return u3Trns;
	}
	public void setU3Trns(FormFile trns) {
		u3Trns = trns;
	}
	public String getU3DocumentNameTrns() {
		return u3DocumentNameTrns;
	}
	public void setU3DocumentNameTrns(String documentNameTrns) {
		u3DocumentNameTrns = documentNameTrns;
	}
	public String getU3DocumentSizeTrns() {
		return u3DocumentSizeTrns;
	}
	public void setU3DocumentSizeTrns(String documentSizeTrns) {
		u3DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU3DocContentsTrns() {
		return u3DocContentsTrns;
	}
	public void setU3DocContentsTrns(byte[] docContentsTrns) {
		u3DocContentsTrns = docContentsTrns;
	}
	public String getU3PhotoCheckTrns() {
		return u3PhotoCheckTrns;
	}
	public void setU3PhotoCheckTrns(String photoCheckTrns) {
		u3PhotoCheckTrns = photoCheckTrns;
	}
	public String getU3FilePathTrns() {
		return u3FilePathTrns;
	}
	public void setU3FilePathTrns(String filePathTrns) {
		u3FilePathTrns = filePathTrns;
	}
	public String getU3PartyOrPropTrns() {
		return u3PartyOrPropTrns;
	}
	public void setU3PartyOrPropTrns(String partyOrPropTrns) {
		u3PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU3UploadTypeTrns() {
		return u3UploadTypeTrns;
	}
	public void setU3UploadTypeTrns(String uploadTypeTrns) {
		u3UploadTypeTrns = uploadTypeTrns;
	}
	public FormFile getU2() {
		return u2;
	}
	public void setU2(FormFile u2) {
		this.u2 = u2;
	}
	public String getU2DocumentName() {
		return u2DocumentName;
	}
	public void setU2DocumentName(String documentName) {
		u2DocumentName = documentName;
	}
	public String getU2DocumentSize() {
		return u2DocumentSize;
	}
	public void setU2DocumentSize(String documentSize) {
		u2DocumentSize = documentSize;
	}
	public byte[] getU2DocContents() {
		return u2DocContents;
	}
	public void setU2DocContents(byte[] docContents) {
		u2DocContents = docContents;
	}
	public String getU2PhotoCheck() {
		return u2PhotoCheck;
	}
	public void setU2PhotoCheck(String photoCheck) {
		u2PhotoCheck = photoCheck;
	}
	public String getU2FilePath() {
		return u2FilePath;
	}
	public void setU2FilePath(String filePath) {
		u2FilePath = filePath;
	}
	public String getU2PartyOrProp() {
		return u2PartyOrProp;
	}
	public void setU2PartyOrProp(String partyOrProp) {
		u2PartyOrProp = partyOrProp;
	}
	public String getU2UploadType() {
		return u2UploadType;
	}
	public void setU2UploadType(String uploadType) {
		u2UploadType = uploadType;
	}
	public FormFile getU2Trns() {
		return u2Trns;
	}
	public void setU2Trns(FormFile trns) {
		u2Trns = trns;
	}
	public String getU2DocumentNameTrns() {
		return u2DocumentNameTrns;
	}
	public void setU2DocumentNameTrns(String documentNameTrns) {
		u2DocumentNameTrns = documentNameTrns;
	}
	public String getU2DocumentSizeTrns() {
		return u2DocumentSizeTrns;
	}
	public void setU2DocumentSizeTrns(String documentSizeTrns) {
		u2DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU2DocContentsTrns() {
		return u2DocContentsTrns;
	}
	public void setU2DocContentsTrns(byte[] docContentsTrns) {
		u2DocContentsTrns = docContentsTrns;
	}
	public String getU2PhotoCheckTrns() {
		return u2PhotoCheckTrns;
	}
	public void setU2PhotoCheckTrns(String photoCheckTrns) {
		u2PhotoCheckTrns = photoCheckTrns;
	}
	public String getU2FilePathTrns() {
		return u2FilePathTrns;
	}
	public void setU2FilePathTrns(String filePathTrns) {
		u2FilePathTrns = filePathTrns;
	}
	public String getU2PartyOrPropTrns() {
		return u2PartyOrPropTrns;
	}
	public void setU2PartyOrPropTrns(String partyOrPropTrns) {
		u2PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU2UploadTypeTrns() {
		return u2UploadTypeTrns;
	}
	public void setU2UploadTypeTrns(String uploadTypeTrns) {
		u2UploadTypeTrns = uploadTypeTrns;
	}
	public String getPartyOrProp() {
		return partyOrProp;
	}
	public void setPartyOrProp(String partyOrProp) {
		this.partyOrProp = partyOrProp;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getPartyOrPropTrns() {
		return partyOrPropTrns;
	}
	public void setPartyOrPropTrns(String partyOrPropTrns) {
		this.partyOrPropTrns = partyOrPropTrns;
	}
	public String getUploadTypeTrns() {
		return uploadTypeTrns;
	}
	public void setUploadTypeTrns(String uploadTypeTrns) {
		this.uploadTypeTrns = uploadTypeTrns;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFilePathTrns() {
		return filePathTrns;
	}
	public void setFilePathTrns(String filePathTrns) {
		this.filePathTrns = filePathTrns;
	}
	public String getHdnDeclareShareCheck() {
		return hdnDeclareShareCheck;
	}
	public void setHdnDeclareShareCheck(String hdnDeclareShareCheck) {
		this.hdnDeclareShareCheck = hdnDeclareShareCheck;
	}
	public String getDeclareShare(int index) {
		return declareShare[index];
	}
	public void setDeclareShare(int index, String value) {
		this.declareShare[index] = value;
	}
	public void setDeclareShareSize(int size) {
		this.declareShare = new String[size];
	}
	public int getDeclareShareSize() {
		return this.declareShare.length;
	}
	public String getOwnerDay() {
		return ownerDay;
	}
	public void setOwnerDay(String ownerDay) {
		this.ownerDay = ownerDay;
	}
	public String getOwnerMonth() {
		return ownerMonth;
	}
	public void setOwnerMonth(String ownerMonth) {
		this.ownerMonth = ownerMonth;
	}
	public String getOwnerYear() {
		return ownerYear;
	}
	public void setOwnerYear(String ownerYear) {
		this.ownerYear = ownerYear;
	}
	public String getOwnerDOB() {
		return ownerDOB;
	}
	public void setOwnerDOB(String ownerDOB) {
		this.ownerDOB = ownerDOB;
	}
	public String getOwnerDayTrns() {
		return ownerDayTrns;
	}
	public void setOwnerDayTrns(String ownerDayTrns) {
		this.ownerDayTrns = ownerDayTrns;
	}
	public String getOwnerMonthTrns() {
		return ownerMonthTrns;
	}
	public void setOwnerMonthTrns(String ownerMonthTrns) {
		this.ownerMonthTrns = ownerMonthTrns;
	}
	public String getOwnerYearTrns() {
		return ownerYearTrns;
	}
	public void setOwnerYearTrns(String ownerYearTrns) {
		this.ownerYearTrns = ownerYearTrns;
	}
	public String getOwnerDOBTrns() {
		return ownerDOBTrns;
	}
	public void setOwnerDOBTrns(String ownerDOBTrns) {
		this.ownerDOBTrns = ownerDOBTrns;
	}
	public FormFile getCertificateTrns() {
		return certificateTrns;
	}
	public void setCertificateTrns(FormFile certificateTrns) {
		this.certificateTrns = certificateTrns;
	}
	public String getDocumentNameTrns() {
		return documentNameTrns;
	}
	public void setDocumentNameTrns(String documentNameTrns) {
		this.documentNameTrns = documentNameTrns;
	}
	public String getDocumentSizeTrns() {
		return documentSizeTrns;
	}
	public void setDocumentSizeTrns(String documentSizeTrns) {
		this.documentSizeTrns = documentSizeTrns;
	}
	public String getStampduty1Adju() {
		return stampduty1Adju;
	}
	public void setStampduty1Adju(String stampduty1Adju) {
		this.stampduty1Adju = stampduty1Adju;
	}
	public byte[] getDocContentsTrns() {
		return docContentsTrns;
	}
	public void setDocContentsTrns(byte[] docContentsTrns) {
		this.docContentsTrns = docContentsTrns;
	}
	public String getPhotoCheckTrns() {
		return photoCheckTrns;
	}
	public void setPhotoCheckTrns(String photoCheckTrns) {
		this.photoCheckTrns = photoCheckTrns;
	}
	public String getJspName() {
		return jspName;
	}
	public void setJspName(String jspName) {
		this.jspName = jspName;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public String getPhotoCheck() {
		return photoCheck;
	}
	public void setPhotoCheck(String photoCheck) {
		this.photoCheck = photoCheck;
	}
	public String getDocumentSize() {
		return documentSize;
	}
	public void setDocumentSize(String documentSize) {
		this.documentSize = documentSize;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	
	public String getIndScheduleAreaTrns() {
		return indScheduleAreaTrns;
	}
	public void setIndScheduleAreaTrns(String indScheduleAreaTrns) {
		this.indScheduleAreaTrns = indScheduleAreaTrns;
	}
	public String getPermissionNoTrns() {
		return permissionNoTrns;
	}
	public FormFile getCertificate() {
		return certificate;
	}
	public void setCertificate(FormFile certificate) {
		this.certificate = certificate;
	}
	public void setPermissionNoTrns(String permissionNoTrns) {
		this.permissionNoTrns = permissionNoTrns;
	}
	public String getPermissionNo() {
		return permissionNo;
	}
	public void setPermissionNo(String permissionNo) {
		this.permissionNo = permissionNo;
	}
	public String getIndScheduleArea() {
		return indScheduleArea;
	}
	public void setIndScheduleArea(String indScheduleArea) {
		this.indScheduleArea = indScheduleArea;
	}
	public String getOccupationIdTrns() {
		return occupationIdTrns;
	}
	public void setOccupationIdTrns(String occupationIdTrns) {
		this.occupationIdTrns = occupationIdTrns;
	}
	public String getSelectedOccupationName() {
		return selectedOccupationName;
	}
	public void setSelectedOccupationName(String selectedOccupationName) {
		this.selectedOccupationName = selectedOccupationName;
	}
	public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public void setIndCategory(String indCategory) {
		this.indCategory = indCategory;
	}
	public String getIndCategoryTrns() {
		return indCategoryTrns;
	}
	public void setIndCategoryTrns(String indCategoryTrns) {
		this.indCategoryTrns = indCategoryTrns;
	}
    
    
    public int getTotalShareOfPropOwnerSelfParty1() {
		return totalShareOfPropOwnerSelfParty1;
	}
	public String getUserDay() {
		return userDay;
	}
	public void setUserDay(String userDay) {
		this.userDay = userDay;
	}
	public String getUserMonth() {
		return userMonth;
	}
	public void setUserMonth(String userMonth) {
		this.userMonth = userMonth;
	}
	public String getUserYear() {
		return userYear;
	}
	public void setUserYear(String userYear) {
		this.userYear = userYear;
	}
	public String getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}
	public String getUserDayTrns() {
		return userDayTrns;
	}
	public void setUserDayTrns(String userDayTrns) {
		this.userDayTrns = userDayTrns;
	}
	public String getUserMonthTrns() {
		return userMonthTrns;
	}
	public void setUserMonthTrns(String userMonthTrns) {
		this.userMonthTrns = userMonthTrns;
	}
	public String getUserYearTrns() {
		return userYearTrns;
	}
	public void setUserYearTrns(String userYearTrns) {
		this.userYearTrns = userYearTrns;
	}
	public String getUserDOBTrns() {
		return userDOBTrns;
	}
	public void setUserDOBTrns(String userDOBTrns) {
		this.userDOBTrns = userDOBTrns;
	}
	public String getIndPovertyLine() {
		return indPovertyLine;
	}
	public void setIndPovertyLine(String indPovertyLine) {
		this.indPovertyLine = indPovertyLine;
	}
	public String getIndPovertyLineTrns() {
		return indPovertyLineTrns;
	}
	public void setIndPovertyLineTrns(String indPovertyLineTrns) {
		this.indPovertyLineTrns = indPovertyLineTrns;
	}
	public String getIndDisabilityDesc() {
		return indDisabilityDesc;
	}
	public void setIndDisabilityDesc(String indDisabilityDesc) {
		this.indDisabilityDesc = indDisabilityDesc;
	}
	public String getIndDisabilityDescTrns() {
		return indDisabilityDescTrns;
	}
	public void setIndDisabilityDescTrns(String indDisabilityDescTrns) {
		this.indDisabilityDescTrns = indDisabilityDescTrns;
	}
	public String getSelectedCategoryNameTrns() {
		return selectedCategoryNameTrns;
	}
	public void setSelectedCategoryNameTrns(String selectedCategoryNameTrns) {
		this.selectedCategoryNameTrns = selectedCategoryNameTrns;
	}
	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}
	public void setSelectedCategoryName(String selectedCategoryName) {
		this.selectedCategoryName = selectedCategoryName;
	}
	public String getIndMinorityTrns() {
		return indMinorityTrns;
	}
	public void setIndMinorityTrns(String indMinorityTrns) {
		this.indMinorityTrns = indMinorityTrns;
	}
	public String getIndMinority() {
		return indMinority;
	}
	public void setIndMinority(String indMinority) {
		this.indMinority = indMinority;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankAuthPer() {
		return bankAuthPer;
	}
	public void setBankAuthPer(String bankAuthPer) {
		this.bankAuthPer = bankAuthPer;
	}
	public String getBankLoanAmt() {
		return bankLoanAmt;
	}
	public void setBankLoanAmt(String bankLoanAmt) {
		this.bankLoanAmt = bankLoanAmt;
	}
	public String getBankSancAmt() {
		return bankSancAmt;
	}
	public void setBankSancAmt(String bankSancAmt) {
		this.bankSancAmt = bankSancAmt;
	}
	public int getDuty_txn_id() {
		return duty_txn_id;
	}
	public void setDuty_txn_id(int duty_txn_id) {
		this.duty_txn_id = duty_txn_id;
	}
	public ArrayList getSelectedSubClauseList() {
		return selectedSubClauseList;
	}
	public void setSelectedSubClauseList(ArrayList selectedSubClauseList) {
		this.selectedSubClauseList = selectedSubClauseList;
	}
	public String getExchangeDeedMrktValue() {
		return exchangeDeedMrktValue;
	}
	public void setExchangeDeedMrktValue(String exchangeDeedMrktValue) {
		this.exchangeDeedMrktValue = exchangeDeedMrktValue;
	}
	public void setTotalShareOfPropOwnerSelfParty1(
			int totalShareOfPropOwnerSelfParty1) {
		this.totalShareOfPropOwnerSelfParty1 = totalShareOfPropOwnerSelfParty1;
	}
	public int getTotalShareOfPropOwnerSelfParty2() {
		return totalShareOfPropOwnerSelfParty2;
	}
	public void setTotalShareOfPropOwnerSelfParty2(
			int totalShareOfPropOwnerSelfParty2) {
		this.totalShareOfPropOwnerSelfParty2 = totalShareOfPropOwnerSelfParty2;
	}
    
    
    
    
	
    public ArrayList<CommonDTO> getExchangePropertyList() {
		return exchangePropertyList;
	}
	
	public void setExchangePropertyList(ArrayList exchangePropertyList) {
		this.exchangePropertyList = exchangePropertyList;
	}
	
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public String getEstampCode() {
		return estampCode;
	}
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}
	public String getEstampAmt() {
		return estampAmt;
	}
	public void setEstampAmt(String estampAmt) {
		this.estampAmt = estampAmt;
	}
	public String getEstampDateTime() {
		return estampDateTime;
	}
	public void setEstampDateTime(String estampDateTime) {
		this.estampDateTime = estampDateTime;
	}
	public ArrayList getEstampDetLst() {
		return estampDetLst;
	}
	public void setEstampDetLst(ArrayList estampDetLst) {
		this.estampDetLst = estampDetLst;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getAdjudicationNumber() {
		return adjudicationNumber;
	}
	public void setAdjudicationNumber(String adjudicationNumber) {
		this.adjudicationNumber = adjudicationNumber;
	}
	public int getIsPoaAddedFlag() {
		return isPoaAddedFlag;
	}
	public void setIsPoaAddedFlag(int isPoaAddedFlag) {
		this.isPoaAddedFlag = isPoaAddedFlag;
	}
	public String getRegPaid() {
		return regPaid;
	}
	public void setRegPaid(String regPaid) {
		this.regPaid = regPaid;
	}
	public int getTotalShareOfPropOwnerSelf() {
		return totalShareOfPropOwnerSelf;
	}
	public void setTotalShareOfPropOwnerSelf(int totalShareOfPropOwnerSelf) {
		this.totalShareOfPropOwnerSelf = totalShareOfPropOwnerSelf;
	}
	public int getTotalShareOfPropDonor() {
		return totalShareOfPropDonor;
	}
	public void setTotalShareOfPropDonor(int totalShareOfPropDonor) {
		this.totalShareOfPropDonor = totalShareOfPropDonor;
	}
	public int getTotalShareOfPropDonee() {
		return totalShareOfPropDonee;
	}
	public void setTotalShareOfPropDonee(int totalShareOfPropDonee) {
		this.totalShareOfPropDonee = totalShareOfPropDonee;
	}
	public String getMarketValueShares() {
		return marketValueShares;
	}
	public void setMarketValueShares(String marketValueShares) {
		this.marketValueShares = marketValueShares;
	}
	public String getDutyPaid() {
		return dutyPaid;
	}
	public void setDutyPaid(String dutyPaid) {
		this.dutyPaid = dutyPaid;
	}
	public int getIsTransactingPartyAddedFlag() {
		return isTransactingPartyAddedFlag;
	}
	public void setIsTransactingPartyAddedFlag(int isTransactingPartyAddedFlag) {
		this.isTransactingPartyAddedFlag = isTransactingPartyAddedFlag;
	}
	
	public double getConsiAmount() {
		return consiAmount;
	}
	public void setConsiAmount(double consiAmount) {
		this.consiAmount = consiAmount;
	}
	public String getLabelAmountFlag() {
		return labelAmountFlag;
	}
	public void setLabelAmountFlag(String labelAmountFlag) {
		this.labelAmountFlag = labelAmountFlag;
	}
	/*public double getMarketValueShares() {
		return marketValueShares;
	}
	public void setMarketValueShares(double marketValueShares) {
		this.marketValueShares = marketValueShares;
	}
	public double getDutyPaid() {
		return dutyPaid;
	}
	public void setDutyPaid(double dutyPaid) {
		this.dutyPaid = dutyPaid;
	}*/
	public String getStampduty1() {
		return stampduty1;
	}
	public void setStampduty1(String stampduty1) {
		this.stampduty1 = stampduty1;
	}
	public String getNagarPalikaDuty() {
		return nagarPalikaDuty;
	}
	public void setNagarPalikaDuty(String nagarPalikaDuty) {
		this.nagarPalikaDuty = nagarPalikaDuty;
	}
	public String getPanchayatDuty() {
		return panchayatDuty;
	}
	public void setPanchayatDuty(String panchayatDuty) {
		this.panchayatDuty = panchayatDuty;
	}
	public String getUpkarDuty() {
		return upkarDuty;
	}
	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}
	public String getTotalduty() {
		return totalduty;
	}
	public void setTotalduty(String totalduty) {
		this.totalduty = totalduty;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}
	public int getIsDutyCalculated() {
		return isDutyCalculated;
	}
	public void setIsDutyCalculated(int isDutyCalculated) {
		this.isDutyCalculated = isDutyCalculated;
	}
	public String getHdnExAmount() {
		return hdnExAmount;
	}
	public void setHdnExAmount(String hdnExAmount) {
		this.hdnExAmount = hdnExAmount;
	}
	public String getPoaOwnerId() {
		return poaOwnerId;
	}
	public void setPoaOwnerId(String poaOwnerId) {
		this.poaOwnerId = poaOwnerId;
	}
	public String getOwnerProofName() {
		return ownerProofName;
	}
	public void setOwnerProofName(String ownerProofName) {
		this.ownerProofName = ownerProofName;
	}
	public String getOwnerProofNameTrns() {
		return ownerProofNameTrns;
	}
	public void setOwnerProofNameTrns(String ownerProofNameTrns) {
		this.ownerProofNameTrns = ownerProofNameTrns;
	}
	public String getOwnerNameTrns() {
		return ownerNameTrns;
	}
	public void setOwnerNameTrns(String ownerNameTrns) {
		this.ownerNameTrns = ownerNameTrns;
	}
	public String getOwnerOgrNameTrns() {
		return ownerOgrNameTrns;
	}
	public void setOwnerOgrNameTrns(String ownerOgrNameTrns) {
		this.ownerOgrNameTrns = ownerOgrNameTrns;
	}
	public String getOwnerGendarTrns() {
		return ownerGendarTrns;
	}
	public void setOwnerGendarTrns(String ownerGendarTrns) {
		this.ownerGendarTrns = ownerGendarTrns;
	}
	public String getOwnerNationalityTrns() {
		return ownerNationalityTrns;
	}
	public void setOwnerNationalityTrns(String ownerNationalityTrns) {
		this.ownerNationalityTrns = ownerNationalityTrns;
	}
	public String getOwnerAddressTrns() {
		return ownerAddressTrns;
	}
	public void setOwnerAddressTrns(String ownerAddressTrns) {
		this.ownerAddressTrns = ownerAddressTrns;
	}
	public String getOwnerPhnoTrns() {
		return ownerPhnoTrns;
	}
	public void setOwnerPhnoTrns(String ownerPhnoTrns) {
		this.ownerPhnoTrns = ownerPhnoTrns;
	}
	public String getOwnerEmailIDTrns() {
		return ownerEmailIDTrns;
	}
	public void setOwnerEmailIDTrns(String ownerEmailIDTrns) {
		this.ownerEmailIDTrns = ownerEmailIDTrns;
	}
	public String getOwnerListIDTrns() {
		return ownerListIDTrns;
	}
	public void setOwnerListIDTrns(String ownerListIDTrns) {
		this.ownerListIDTrns = ownerListIDTrns;
	}
	public String getOwnerIdnoTrns() {
		return ownerIdnoTrns;
	}
	public void setOwnerIdnoTrns(String ownerIdnoTrns) {
		this.ownerIdnoTrns = ownerIdnoTrns;
	}
	public String getOwnerAgeTrns() {
		return ownerAgeTrns;
	}
	public void setOwnerAgeTrns(String ownerAgeTrns) {
		this.ownerAgeTrns = ownerAgeTrns;
	}
	public String getOwnerAge() {
		return ownerAge;
	}
	public void setOwnerAge(String ownerAge) {
		this.ownerAge = ownerAge;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerOgrName() {
		return ownerOgrName;
	}
	public void setOwnerOgrName(String ownerOgrName) {
		this.ownerOgrName = ownerOgrName;
	}
	public String getOwnerGendar() {
		return ownerGendar;
	}
	public void setOwnerGendar(String ownerGendar) {
		this.ownerGendar = ownerGendar;
	}
	public String getOwnerNationality() {
		return ownerNationality;
	}
	public void setOwnerNationality(String ownerNationality) {
		this.ownerNationality = ownerNationality;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerPhno() {
		return ownerPhno;
	}
	public void setOwnerPhno(String ownerPhno) {
		this.ownerPhno = ownerPhno;
	}
	public String getOwnerEmailID() {
		return ownerEmailID;
	}
	public void setOwnerEmailID(String ownerEmailID) {
		this.ownerEmailID = ownerEmailID;
	}
	public String getOwnerListID() {
		return ownerListID;
	}
	public void setOwnerListID(String ownerListID) {
		this.ownerListID = ownerListID;
	}
	public String getOwnerIdno() {
		return ownerIdno;
	}
	public void setOwnerIdno(String ownerIdno) {
		this.ownerIdno = ownerIdno;
	}
	public int getIsDashboardFlag() {
		return isDashboardFlag;
	}
	public int getTotalShareOfPropBuyer() {
		return totalShareOfPropBuyer;
	}
	public void setTotalShareOfPropBuyer(int totalShareOfPropBuyer) {
		this.totalShareOfPropBuyer = totalShareOfPropBuyer;
	}
	public int getTotalShareOfPropSellerSelf() {
		return totalShareOfPropSellerSelf;
	}
	public void setTotalShareOfPropSellerSelf(int totalShareOfPropSellerSelf) {
		this.totalShareOfPropSellerSelf = totalShareOfPropSellerSelf;
	}
	public int getTotalShareOfPropSellerPoa() {
		return totalShareOfPropSellerPoa;
	}
	public void setTotalShareOfPropSellerPoa(int totalShareOfPropSellerPoa) {
		this.totalShareOfPropSellerPoa = totalShareOfPropSellerPoa;
	}
	public String getTotalMarketValue() {
		return totalMarketValue;
	}
	public void setTotalMarketValue(String totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}
	/*public double getTotalMarketValue() {
		return totalMarketValue;
	}
	public void setTotalMarketValue(double totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}*/
	public HashMap getMapTransPartyDbInsertion() {
		return mapTransPartyDbInsertion;
	}
	public void setMapTransPartyDbInsertion(HashMap mapTransPartyDbInsertion) {
		this.mapTransPartyDbInsertion = mapTransPartyDbInsertion;
	}
	public int getMapKeyCount() {
		return mapKeyCount;
	}
	public void setMapKeyCount(int mapKeyCount) {
		this.mapKeyCount = mapKeyCount;
	}
	public int getApplicantRoleId2() {
		return applicantRoleId2;
	}
	public void setApplicantRoleId2(int applicantRoleId2) {
		this.applicantRoleId2 = applicantRoleId2;
	}
	public int getTotalShareOfProp() {
		return totalShareOfProp;
	}
	public void setTotalShareOfProp(int totalShareOfProp) {
		this.totalShareOfProp = totalShareOfProp;
	}
	public int getApplicantRoleId() {
		return applicantRoleId;
	}
	public void setApplicantRoleId(int applicantRoleId) {
		this.applicantRoleId = applicantRoleId;
	}
	
	/*public float getTotalMarketValue() {
		return totalMarketValue;
	}
	public void setTotalMarketValue(float totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}*/
	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}
	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}
	public String getFromModule() {
		return fromModule;
	}
	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}
	public String getCheckModule() {
		return checkModule;
	}
	public void setCheckModule(String checkModule) {
		this.checkModule = checkModule;
	}
	public HashMap getMapPropertyTransParty() {
		return mapPropertyTransParty;
	}
	public void setMapPropertyTransParty(HashMap mapPropertyTransParty) {
		this.mapPropertyTransParty = mapPropertyTransParty;
	}
	public int getIsMultiplePropsFlag() {
		return isMultiplePropsFlag;
	}
	public void setIsMultiplePropsFlag(int isMultiplePropsFlag) {
		this.isMultiplePropsFlag = isMultiplePropsFlag;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public void setIsDashboardFlag(int isDashboardFlag) {
		this.isDashboardFlag = isDashboardFlag;
	}
	/*public long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(long propertyId) {
		this.propertyId = propertyId;
	}*/
	public String getValuationId() {
		return valuationId;
	}
	public void setValuationId(String valuationId) {
		this.valuationId = valuationId;
	}
	/*public ArrayList getPendingRegApplicationList() {
		return pendingRegApplicationList;
	}
	public void setPendingRegApplicationList(ArrayList pendingRegApplicationList) {
		this.pendingRegApplicationList = pendingRegApplicationList;
	}*/
	public int getInstID() {
		return instID;
	}
	public void setInstID(int instID) {
		this.instID = instID;
	}
	
	
	public String getExmpID() {
		return exmpID;
	}
	public void setExmpID(String exmpID) {
		this.exmpID = exmpID;
	}
	/*public int getExmpID() {
		return exmpID;
	}
	public void setExmpID(int exmpID) {
		this.exmpID = exmpID;
	}*/
	public String getHdnapplicantUserIdCheck() {
		return hdnapplicantUserIdCheck;
	}
	public void setHdnapplicantUserIdCheck(String hdnapplicantUserIdCheck) {
		this.hdnapplicantUserIdCheck = hdnapplicantUserIdCheck;
	}
	public String getApplicantUserIdCheck() {
		return applicantUserIdCheck;
	}
	public void setApplicantUserIdCheck(String applicantUserIdCheck) {
		this.applicantUserIdCheck = applicantUserIdCheck;
	}
	public int getDeedID() {
		return deedID;
	}
	public void setDeedID(int deedID) {
		this.deedID = deedID;
	}

	public String getExemType() {
		return exemType;
	}
	public void setExemType(String exemType) {
		this.exemType = exemType;
	}
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}
	public void setSelectedExemptionList(ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}
	public String getDeedtype1() {
		return deedtype1;
	}
	public void setDeedtype1(String deedtype1) {
		this.deedtype1 = deedtype1;
	}
	public String getInstType() {
		return instType;
	}
	public void setInstType(String instType) {
		this.instType = instType;
	}
	
	/*public String getTotalduty() {
		return totalduty;
	}
	public void setTotalduty(String totalduty) {
		this.totalduty = totalduty;
	}*/
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getCurrDateTime() {
		return currDateTime;
	}
	public void setCurrDateTime(String currDateTime) {
		this.currDateTime = currDateTime;
	}
	public String getHdnDeleteTrnsPrtyId() {
		return hdnDeleteTrnsPrtyId;
	}
	public void setHdnDeleteTrnsPrtyId(String hdnDeleteTrnsPrtyId) {
		this.hdnDeleteTrnsPrtyId = hdnDeleteTrnsPrtyId;
	}
	public String getSelectedPoaName() {
		return selectedPoaName;
	}
	public void setSelectedPoaName(String selectedPoaName) {
		this.selectedPoaName = selectedPoaName;
	}
	public String getSelectedPoa() {
		return selectedPoa;
	}
	public void setSelectedPoa(String selectedPoa) {
		this.selectedPoa = selectedPoa;
	}
	public String getAbc() {
		return abc;
	}
	public void setAbc(String abc) {
		this.abc = abc;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getHdnOwnerId() {
		return hdnOwnerId;
	}
	public void setHdnOwnerId(String hdnOwnerId) {
		this.hdnOwnerId = hdnOwnerId;
	}
	public String getPoaSelectedId() {
		return poaSelectedId;
	}
	public void setPoaSelectedId(String poaSelectedId) {
		this.poaSelectedId = poaSelectedId;
	}
	public String getPoaList1() {
		return poaList1;
	}
	public void setPoaList1(String poaList1) {
		this.poaList1 = poaList1;
	}
	public String getOwnerList1() {
		return ownerList1;
	}
	public void setOwnerList1(String ownerList1) {
		this.ownerList1 = ownerList1;
	}
	public HashMap getMapRegTxnIdDisp() {
		return mapRegTxnIdDisp;
	}
	public void setMapRegTxnIdDisp(HashMap mapRegTxnIdDisp) {
		this.mapRegTxnIdDisp = mapRegTxnIdDisp;
	}
	public String getRegInitPermTxnId() {
		return regInitPermTxnId;
	}
	public void setRegInitPermTxnId(String regInitPermTxnId) {
		this.regInitPermTxnId = regInitPermTxnId;
	}
	public String getRegInitPaymntTxnId() {
		return regInitPaymntTxnId;
	}
	public void setRegInitPaymntTxnId(String regInitPaymntTxnId) {
		this.regInitPaymntTxnId = regInitPaymntTxnId;
	}
	public String getRegInitEstampCode() {
		return regInitEstampCode;
	}
	public void setRegInitEstampCode(String regInitEstampCode) {
		this.regInitEstampCode = regInitEstampCode;
	}
	public String getConfirmationFlag() {
		return confirmationFlag;
	}
	public void setConfirmationFlag(String confirmationFlag) {
		this.confirmationFlag = confirmationFlag;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getOwnerListId() {
		return ownerListId;
	}
	public void setOwnerListId(String ownerListId) {
		this.ownerListId = ownerListId;
	}
	public String getPoaListId() {
		return poaListId;
	}
	public void setPoaListId(String poaListId) {
		this.poaListId = poaListId;
	}
	public ArrayList getPoaList() {
		return poaList;
	}
	public void setPoaList(ArrayList poaList) {
		this.poaList = poaList;
	}
	public ArrayList getOwnerList() {
		return ownerList;
	}
	public void setOwnerList(ArrayList ownerList) {
		this.ownerList = ownerList;
	}
	public int getTotalPoaCount() {
		return totalPoaCount;
	}
	public void setTotalPoaCount(int totalPoaCount) {
		this.totalPoaCount = totalPoaCount;
	}
	public int getTotalOwnerCount() {
		return totalOwnerCount;
	}
	public void setTotalOwnerCount(int totalOwnerCount) {
		this.totalOwnerCount = totalOwnerCount;
	}
	public int getOwnerCount() {
		return ownerCount;
	}
	public void setOwnerCount(int ownerCount) {
		this.ownerCount = ownerCount;
	}
	public int getSellerSelfCount() {
		return sellerSelfCount;
	}
	public void setSellerSelfCount(int sellerSelfCount) {
		this.sellerSelfCount = sellerSelfCount;
	}
	public int getSellerPoaCount() {
		return sellerPoaCount;
	}
	public void setSellerPoaCount(int sellerPoaCount) {
		this.sellerPoaCount = sellerPoaCount;
	}
	public int getBuyerCount() {
		return buyerCount;
	}
	public void setBuyerCount(int buyerCount) {
		this.buyerCount = buyerCount;
	}
	public int getDonorCount() {
		return donorCount;
	}
	public void setDonorCount(int donorCount) {
		this.donorCount = donorCount;
	}
	public int getDoneeCount() {
		return doneeCount;
	}
	public void setDoneeCount(int doneeCount) {
		this.doneeCount = doneeCount;
	}
	public int getPoaHolderCount() {
		return poaHolderCount;
	}
	public void setPoaHolderCount(int poaHolderCount) {
		this.poaHolderCount = poaHolderCount;
	}
	public int getPoaAccepterCount() {
		return poaAccepterCount;
	}
	public void setPoaAccepterCount(int poaAccepterCount) {
		this.poaAccepterCount = poaAccepterCount;
	}
	public int getParty1OwnerCount() {
		return party1OwnerCount;
	}
	public void setParty1OwnerCount(int party1OwnerCount) {
		this.party1OwnerCount = party1OwnerCount;
	}
	public int getParty2OwnerCount() {
		return party2OwnerCount;
	}
	public void setParty2OwnerCount(int party2OwnerCount) {
		this.party2OwnerCount = party2OwnerCount;
	}
	public int getParty1PoaHolderCount() {
		return party1PoaHolderCount;
	}
	public void setParty1PoaHolderCount(int party1PoaHolderCount) {
		this.party1PoaHolderCount = party1PoaHolderCount;
	}
	public int getParty2PoaHolderCount() {
		return party2PoaHolderCount;
	}
	public void setParty2PoaHolderCount(int party2PoaHolderCount) {
		this.party2PoaHolderCount = party2PoaHolderCount;
	}
	public String getPartyRoleId() {
		return partyRoleId;
	}
	public void setPartyRoleId(String partyRoleId) {
		this.partyRoleId = partyRoleId;
	}
	public String getListIDName() {
		return listIDName;
	}
	public void setListIDName(String listIDName) {
		this.listIDName = listIDName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public HashMap getMapTransactingPartiesDisp() {
		return mapTransactingPartiesDisp;
	}
	public void setMapTransactingPartiesDisp(HashMap mapTransactingPartiesDisp) {
		this.mapTransactingPartiesDisp = mapTransactingPartiesDisp;
	}
	public String getApplicantOrTransParty() {
		return applicantOrTransParty;
	}
	public void setApplicantOrTransParty(String applicantOrTransParty) {
		this.applicantOrTransParty = applicantOrTransParty;
	}
	public String getIndReligionTrns() {
		return indReligionTrns;
	}
	public void setIndReligionTrns(String indReligionTrns) {
		this.indReligionTrns = indReligionTrns;
	}
	public String getIndCasteTrns() {
		return indCasteTrns;
	}
	public void setIndCasteTrns(String indCasteTrns) {
		this.indCasteTrns = indCasteTrns;
	}
	public String getIndDisabilityTrns() {
		return indDisabilityTrns;
	}
	public void setIndDisabilityTrns(String indDisabilityTrns) {
		this.indDisabilityTrns = indDisabilityTrns;
	}
	public String getOwnershipShareTrns() {
		return ownershipShareTrns;
	}
	public void setOwnershipShareTrns(String ownershipShareTrns) {
		this.ownershipShareTrns = ownershipShareTrns;
	}
	public String getRelationWithOwnerTrns() {
		return relationWithOwnerTrns;
	}
	public void setRelationWithOwnerTrns(String relationWithOwnerTrns) {
		this.relationWithOwnerTrns = relationWithOwnerTrns;
	}
	public String getShareOfPropTrns() {
		return shareOfPropTrns;
	}
	public void setShareOfPropTrns(String shareOfPropTrns) {
		this.shareOfPropTrns = shareOfPropTrns;
	}
	public String getPartyTypeTrns() {
		return partyTypeTrns;
	}
	public void setPartyTypeTrns(String partyTypeTrns) {
		this.partyTypeTrns = partyTypeTrns;
	}
	public String getDeedId() {
		return deedId;
	}
	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getIndcountryName() {
		return indcountryName;
	}
	public void setIndcountryName(String indcountryName) {
		this.indcountryName = indcountryName;
	}
	public String getIndstatenameName() {
		return indstatenameName;
	}
	public void setIndstatenameName(String indstatenameName) {
		this.indstatenameName = indstatenameName;
	}
	public String getInddistrictName() {
		return inddistrictName;
	}
	public void setInddistrictName(String inddistrictName) {
		this.inddistrictName = inddistrictName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStatenameName() {
		return statenameName;
	}
	public void setStatenameName(String statenameName) {
		this.statenameName = statenameName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getListAdoptedPartyName() {
		return listAdoptedPartyName;
	}
	public void setListAdoptedPartyName(String listAdoptedPartyName) {
		this.listAdoptedPartyName = listAdoptedPartyName;
	}
	public String getHidnRegTxnId() {
		return hidnRegTxnId;
	}
	public void setHidnRegTxnId(String hidnRegTxnId) {
		this.hidnRegTxnId = hidnRegTxnId;
	}
	
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getListAdoptedPartyNameTrns() {
		return listAdoptedPartyNameTrns;
	}
	public void setListAdoptedPartyNameTrns(String listAdoptedPartyNameTrns) {
		this.listAdoptedPartyNameTrns = listAdoptedPartyNameTrns;
	}
	public String getIndcountryNameTrns() {
		return indcountryNameTrns;
	}
	public void setIndcountryNameTrns(String indcountryNameTrns) {
		this.indcountryNameTrns = indcountryNameTrns;
	}
	public String getIndstatenameNameTrns() {
		return indstatenameNameTrns;
	}
	public void setIndstatenameNameTrns(String indstatenameNameTrns) {
		this.indstatenameNameTrns = indstatenameNameTrns;
	}
	public String getInddistrictNameTrns() {
		return inddistrictNameTrns;
	}
	public void setInddistrictNameTrns(String inddistrictNameTrns) {
		this.inddistrictNameTrns = inddistrictNameTrns;
	}
	public String getListIDNameTrns() {
		return listIDNameTrns;
	}
	public void setListIDNameTrns(String listIDNameTrns) {
		this.listIDNameTrns = listIDNameTrns;
	}
	public String getCountryNameTrns() {
		return countryNameTrns;
	}
	public void setCountryNameTrns(String countryNameTrns) {
		this.countryNameTrns = countryNameTrns;
	}
	public String getStatenameNameTrns() {
		return statenameNameTrns;
	}
	public void setStatenameNameTrns(String statenameNameTrns) {
		this.statenameNameTrns = statenameNameTrns;
	}
	public String getDistrictNameTrns() {
		return districtNameTrns;
	}
	public void setDistrictNameTrns(String districtNameTrns) {
		this.districtNameTrns = districtNameTrns;
	}
	public String getDeleteTrnsPrtyID() {
		return deleteTrnsPrtyID;
	}
	public void setDeleteTrnsPrtyID(String deleteTrnsPrtyID) {
		this.deleteTrnsPrtyID = deleteTrnsPrtyID;
	}
	public ArrayList getDeedList() {
		return deedList;
	}
	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}
	public ArrayList getInstrumentList() {
		return instrumentList;
	}
	public void setInstrumentList(ArrayList instrumentList) {
		this.instrumentList = instrumentList;
	}
	public ArrayList getExemptionList() {
		return exemptionList;
	}
	public void setExemptionList(ArrayList exemptionList) {
		this.exemptionList = exemptionList;
	}
	
	//end of addition by Roopam
	/**
	 * @return the listAdoptedParty
	 */
	public String getListAdoptedParty() {
		return listAdoptedParty;
	}
	/**
	 * @param listAdoptedParty the listAdoptedParty to set
	 */
	public void setListAdoptedParty(String listAdoptedParty) {
		this.listAdoptedParty = listAdoptedParty;
	}
	/**
	 * @return the ogrName
	 */
	public String getOgrName() {
		return ogrName;
	}
	/**
	 * @param ogrName the ogrName to set
	 */
	public void setOgrName(String ogrName) {
		this.ogrName = ogrName;
	}
	/**
	 * @return the authPerName
	 */
	public String getAuthPerName() {
		return authPerName;
	}
	/**
	 * @param authPerName the authPerName to set
	 */
	public void setAuthPerName(String authPerName) {
		this.authPerName = authPerName;
	}
	/**
	 * @return the orgaddress
	 */
	public String getOrgaddress() {
		return orgaddress;
	}
	/**
	 * @param orgaddress the orgaddress to set
	 */
	public void setOrgaddress(String orgaddress) {
		this.orgaddress = orgaddress;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the statename
	 */
	public String getStatename() {
		return statename;
	}
	/**
	 * @param statename the statename to set
	 */
	public void setStatename(String statename) {
		this.statename = statename;
	}
	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return the phno
	 */
	public String getPhno() {
		return phno;
	}
	/**
	 * @param phno the phno to set
	 */
	public void setPhno(String phno) {
		this.phno = phno;
	}
	/**
	 * @return the mobno
	 */
	public String getMobno() {
		return mobno;
	}
	/**
	 * @param mobno the mobno to set
	 */
	public void setMobno(String mobno) {
		this.mobno = mobno;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the gendar
	 */
	public String getGendar() {
		return gendar;
	}
	/**
	 * @param gendar the gendar to set
	 */
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param spouseName the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return the listID
	 */
	public String getListID() {
		return listID;
	}
	/**
	 * @param listID the listID to set
	 */
	public void setListID(String listID) {
		this.listID = listID;
	}
	/**
	 * @return the idno
	 */
	public String getIdno() {
		return idno;
	}
	/**
	 * @param idno the idno to set
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	/**
	 * @return the bname
	 */
	public String getBname() {
		return bname;
	}
	/**
	 * @param bname the bname to set
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}
	/**
	 * @return the baddress
	 */
	public String getBaddress() {
		return baddress;
	}
	/**
	 * @param baddress the baddress to set
	 */
	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}
	/**
	 * @return the tparties
	 */
	public String getTparties() {
		return tparties;
	}
	/**
	 * @param tparties the tparties to set
	 */
	public void setTparties(String tparties) {
		this.tparties = tparties;
	}
	/**
	 * @return the organisation
	 */
	public String getOrganisation() {
		return organisation;
	}
	/**
	 * @param organisation the organisation to set
	 */
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	/**
	 * @return the deedType
	 */
	public String getDeedType() {
		return deedType;
	}
	/**
	 * @param deedType the deedType to set
	 */
	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}
	/**
	 * @return the marketValue
	 */
	public String getMarketValue() {
		return marketValue;
	}
	/**
	 * @param marketValue the marketValue to set
	 */
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}
	/**
	 * @return the consiAmount
	 */
	/*public String getConsiAmount() {
		return consiAmount;
	}*/
	/**
	 * @param consiAmount the consiAmount to set
	 */
	/*public void setConsiAmount(String consiAmount) {
		this.consiAmount = consiAmount;
	}*/
	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return the stampDuty
	 */
	public String getStampDuty() {
		return stampDuty;
	}
	/**
	 * @param stampDuty the stampDuty to set
	 */
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the indaddress
	 */
	public String getIndaddress() {
		return indaddress;
	}
	/**
	 * @param indaddress the indaddress to set
	 */
	public void setIndaddress(String indaddress) {
		this.indaddress = indaddress;
	}
	/**
	 * @return the indcountry
	 */
	public String getIndcountry() {
		return indcountry;
	}
	/**
	 * @param indcountry the indcountry to set
	 */
	public void setIndcountry(String indcountry) {
		this.indcountry = indcountry;
	}
	/**
	 * @return the indstatename
	 */
	public String getIndstatename() {
		return indstatename;
	}
	/**
	 * @param indstatename the indstatename to set
	 */
	public void setIndstatename(String indstatename) {
		this.indstatename = indstatename;
	}
	/**
	 * @return the inddistrict
	 */
	public String getInddistrict() {
		return inddistrict;
	}
	/**
	 * @param inddistrict the inddistrict to set
	 */
	public void setInddistrict(String inddistrict) {
		this.inddistrict = inddistrict;
	}
	/**
	 * @return the indphno
	 */
	public String getIndphno() {
		return indphno;
	}
	/**
	 * @param indphno the indphno to set
	 */
	public void setIndphno(String indphno) {
		this.indphno = indphno;
	}
	/**
	 * @return the indmobno
	 */
	public String getIndmobno() {
		return indmobno;
	}
	/**
	 * @param indmobno the indmobno to set
	 */
	public void setIndmobno(String indmobno) {
		this.indmobno = indmobno;
	}
	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return the exemption
	 */
	public String getExemption() {
		return exemption;
	}
	/**
	 * @param exemption the exemption to set
	 */
	public void setExemption(String exemption) {
		this.exemption = exemption;
	}
	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
	/**
	 * @return the commonDto
	 */
	public RegCommonDTO getCommonDto() {
		return commonDto;
	}
	/**
	 * @param commonDto the commonDto to set
	 */
	public void setCommonDto(RegCommonDTO commonDto) {
		this.commonDto = commonDto;
	}
	/**
	 * @return the payID
	 */
	public String getPayID() {
		return payID;
	}
	/**
	 * @param payID the payID to set
	 */
	public void setPayID(String payID) {
		this.payID = payID;
	}
	public RegCompletDTO getRegcompletDto() {
		return regcompletDto;
	}
	public void setRegcompletDto(RegCompletDTO regcompletDto) {
		this.regcompletDto = regcompletDto;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public PropertyValuationDTO getPropertyDTO() {
		return propertyDTO;
	}
	public void setPropertyDTO(PropertyValuationDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}
	public ArrayList getSelectedSubclause() {
		return selectedSubclause;
	}
	public void setSelectedSubclause(ArrayList selectedSubclause) {
		this.selectedSubclause = selectedSubclause;
	}
	public HashMap getSelectedMap() {
		return selectedMap;
	}
	public void setSelectedMap(HashMap selectedMap) {
		this.selectedMap = selectedMap;
	}
	public HashMap getMapBuildingDetails() {
		return mapBuildingDetails;
	}
	public void setMapBuildingDetails(HashMap mapBuildingDetails) {
		this.mapBuildingDetails = mapBuildingDetails;
	}
	
	//Following getters setters for transacting parties. 
	public String getFnameTrns() {
		return fnameTrns;
	}
	public void setFnameTrns(String fnameTrns) {
		this.fnameTrns = fnameTrns;
	}
	public String getMnameTrns() {
		return mnameTrns;
	}
	public void setMnameTrns(String mnameTrns) {
		this.mnameTrns = mnameTrns;
	}
	public String getLnameTrns() {
		return lnameTrns;
	}
	public void setLnameTrns(String lnameTrns) {
		this.lnameTrns = lnameTrns;
	}
	public String getGendarTrns() {
		return gendarTrns;
	}
	public void setGendarTrns(String gendarTrns) {
		this.gendarTrns = gendarTrns;
	}
	public String getAgeTrns() {
		return ageTrns;
	}
	public void setAgeTrns(String ageTrns) {
		this.ageTrns = ageTrns;
	}
	public String getFatherNameTrns() {
		return fatherNameTrns;
	}
	public void setFatherNameTrns(String fatherNameTrns) {
		this.fatherNameTrns = fatherNameTrns;
	}
	public String getMotherNameTrns() {
		return motherNameTrns;
	}
	public void setMotherNameTrns(String motherNameTrns) {
		this.motherNameTrns = motherNameTrns;
	}
	public String getSpouseNameTrns() {
		return spouseNameTrns;
	}
	public void setSpouseNameTrns(String spouseNameTrns) {
		this.spouseNameTrns = spouseNameTrns;
	}
	public String getNationalityTrns() {
		return nationalityTrns;
	}
	public void setNationalityTrns(String nationalityTrns) {
		this.nationalityTrns = nationalityTrns;
	}
	public String getPostalCodeTrns() {
		return postalCodeTrns;
	}
	public void setPostalCodeTrns(String postalCodeTrns) {
		this.postalCodeTrns = postalCodeTrns;
	}
	public String getEmailIDTrns() {
		return emailIDTrns;
	}
	public void setEmailIDTrns(String emailIDTrns) {
		this.emailIDTrns = emailIDTrns;
	}
	public String getIndaddressTrns() {
		return indaddressTrns;
	}
	public void setIndaddressTrns(String indaddressTrns) {
		this.indaddressTrns = indaddressTrns;
	}
	public String getIndcountryTrns() {
		return indcountryTrns;
	}
	public void setIndcountryTrns(String indcountryTrns) {
		this.indcountryTrns = indcountryTrns;
	}
	public String getIndstatenameTrns() {
		return indstatenameTrns;
	}
	public void setIndstatenameTrns(String indstatenameTrns) {
		this.indstatenameTrns = indstatenameTrns;
	}
	public String getInddistrictTrns() {
		return inddistrictTrns;
	}
	public void setInddistrictTrns(String inddistrictTrns) {
		this.inddistrictTrns = inddistrictTrns;
	}
	public String getIndphnoTrns() {
		return indphnoTrns;
	}
	public void setIndphnoTrns(String indphnoTrns) {
		this.indphnoTrns = indphnoTrns;
	}
	public String getIndmobnoTrns() {
		return indmobnoTrns;
	}
	public void setIndmobnoTrns(String indmobnoTrns) {
		this.indmobnoTrns = indmobnoTrns;
	}
	public String getListIDTrns() {
		return listIDTrns;
	}
	public void setListIDTrns(String listIDTrns) {
		this.listIDTrns = listIDTrns;
	}
	public String getIdnoTrns() {
		return idnoTrns;
	}
	public void setIdnoTrns(String idnoTrns) {
		this.idnoTrns = idnoTrns;
	}
	public String getBnameTrns() {
		return bnameTrns;
	}
	public void setBnameTrns(String bnameTrns) {
		this.bnameTrns = bnameTrns;
	}
	public String getBaddressTrns() {
		return baddressTrns;
	}
	public void setBaddressTrns(String baddressTrns) {
		this.baddressTrns = baddressTrns;
	}
	public String getOgrNameTrns() {
		return ogrNameTrns;
	}
	public void setOgrNameTrns(String ogrNameTrns) {
		this.ogrNameTrns = ogrNameTrns;
	}
	public String getAuthPerNameTrns() {
		return authPerNameTrns;
	}
	public void setAuthPerNameTrns(String authPerNameTrns) {
		this.authPerNameTrns = authPerNameTrns;
	}
	public String getOrgaddressTrns() {
		return orgaddressTrns;
	}
	public void setOrgaddressTrns(String orgaddressTrns) {
		this.orgaddressTrns = orgaddressTrns;
	}
	public String getCountryTrns() {
		return countryTrns;
	}
	public void setCountryTrns(String countryTrns) {
		this.countryTrns = countryTrns;
	}
	public String getStatenameTrns() {
		return statenameTrns;
	}
	public void setStatenameTrns(String statenameTrns) {
		this.statenameTrns = statenameTrns;
	}
	public String getDistrictTrns() {
		return districtTrns;
	}
	public void setDistrictTrns(String districtTrns) {
		this.districtTrns = districtTrns;
	}
	public String getPhnoTrns() {
		return phnoTrns;
	}
	public void setPhnoTrns(String phnoTrns) {
		this.phnoTrns = phnoTrns;
	}
	public String getMobnoTrns() {
		return mobnoTrns;
	}
	public void setMobnoTrns(String mobnoTrns) {
		this.mobnoTrns = mobnoTrns;
	}
	public String getListAdoptedPartyTrns() {
		return listAdoptedPartyTrns;
	}
	public void setListAdoptedPartyTrns(String listAdoptedPartyTrns) {
		this.listAdoptedPartyTrns = listAdoptedPartyTrns;
	}
	public String getMarketValueTrns() {
		return marketValueTrns;
	}
	public void setMarketValueTrns(String marketValueTrns) {
		this.marketValueTrns = marketValueTrns;
	}
	public String getConsiAmountTrns() {
		return consiAmountTrns;
	}
	public void setConsiAmountTrns(String consiAmountTrns) {
		this.consiAmountTrns = consiAmountTrns;
	}
	public String getPurposeTrns() {
		return purposeTrns;
	}
	public void setPurposeTrns(String purposeTrns) {
		this.purposeTrns = purposeTrns;
	}
	public int getAddMoreCounter() {
		return addMoreCounter;
	}
	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}
	public HashMap getMapTransactingParties() {
		return mapTransactingParties;
	}
	public void setMapTransactingParties(HashMap mapTransactingParties) {
		this.mapTransactingParties = mapTransactingParties;
	}
	public String getIndReligion() {
		return indReligion;
	}
	public void setIndReligion(String indReligion) {
		this.indReligion = indReligion;
	}
	public String getIndCaste() {
		return indCaste;
	}
	public void setIndCaste(String indCaste) {
		this.indCaste = indCaste;
	}
	public String getIndDisability() {
		return indDisability;
	}
	public void setIndDisability(String indDisability) {
		this.indDisability = indDisability;
	}
	public String getOwnershipShare() {
		return ownershipShare;
	}
	public void setOwnershipShare(String ownershipShare) {
		this.ownershipShare = ownershipShare;
	}
	public String getRelationWithOwner() {
		return relationWithOwner;
	}
	public void setRelationWithOwner(String relationWithOwner) {
		this.relationWithOwner = relationWithOwner;
	}
	public String[] getShareOfProp() {
		return shareOfProp;
	}
	public String getShareOfProp(int index) {
		return shareOfProp[index];
	}
	public void setShareOfProp(int index, String value) {
		this.shareOfProp[index] = value;
	}
	public void setShareOfPropSize(int size) {
		this.shareOfProp = new String[size];
	}
	public int getShareOfPropSize() {
		return this.shareOfProp.length;
	}
	public String getExecutionDate() {
		return executionDate;
	}
	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}
	public String getPresentationDate() {
		return presentationDate;
	}
	public void setPresentationDate(String presentationDate) {
		this.presentationDate = presentationDate;
	}
	public String getExistingRegDocNo1() {
		return existingRegDocNo1;
	}
	public void setExistingRegDocNo1(String existingRegDocNo1) {
		this.existingRegDocNo1 = existingRegDocNo1;
	}
	public String getDateOfReg1() {
		return dateOfReg1;
	}
	public void setDateOfReg1(String dateOfReg1) {
		this.dateOfReg1 = dateOfReg1;
	}
	public String getExistingRegDocNo2() {
		return existingRegDocNo2;
	}
	public void setExistingRegDocNo2(String existingRegDocNo2) {
		this.existingRegDocNo2 = existingRegDocNo2;
	}
	public String getDateOfReg2() {
		return dateOfReg2;
	}
	public void setDateOfReg2(String dateOfReg2) {
		this.dateOfReg2 = dateOfReg2;
	}
	public String getExistingRegDocNo() {
		return existingRegDocNo;
	}
	public void setExistingRegDocNo(String existingRegDocNo) {
		this.existingRegDocNo = existingRegDocNo;
	}
	public String getDateOfReg() {
		return dateOfReg;
	}
	public void setDateOfReg(String dateOfReg) {
		this.dateOfReg = dateOfReg;
	}
	public String getNoBuyers() {
		return noBuyers;
	}
	public void setNoBuyers(String noBuyers) {
		this.noBuyers = noBuyers;
	}
	public String getConsiderationFaceValue() {
		return considerationFaceValue;
	}
	public void setConsiderationFaceValue(String considerationFaceValue) {
		this.considerationFaceValue = considerationFaceValue;
	}
	public String getNoSellers() {
		return noSellers;
	}
	public void setNoSellers(String noSellers) {
		this.noSellers = noSellers;
	}
	public String getNoDonors() {
		return noDonors;
	}
	public void setNoDonors(String noDonors) {
		this.noDonors = noDonors;
	}
	public String getNoDonees() {
		return noDonees;
	}
	public void setNoDonees(String noDonees) {
		this.noDonees = noDonees;
	}
	public String getNoParty1() {
		return noParty1;
	}
	public void setNoParty1(String noParty1) {
		this.noParty1 = noParty1;
	}
	public String getNoParty2() {
		return noParty2;
	}
	public void setNoParty2(String noParty2) {
		this.noParty2 = noParty2;
	}
	public String getOwnersAuthPoa() {
		return ownersAuthPoa;
	}
	public void setOwnersAuthPoa(String ownersAuthPoa) {
		this.ownersAuthPoa = ownersAuthPoa;
	}
	public String getAuthPersAcceptPoa() {
		return authPersAcceptPoa;
	}
	public void setAuthPersAcceptPoa(String authPersAcceptPoa) {
		this.authPersAcceptPoa = authPersAcceptPoa;
	}
	public String getPeriodPoa() {
		return periodPoa;
	}
	public void setPeriodPoa(String periodPoa) {
		this.periodPoa = periodPoa;
	}
	public String getNoOwners() {
		return noOwners;
	}
	public void setNoOwners(String noOwners) {
		this.noOwners = noOwners;
	}
	public String getNameOfBank() {
		return nameOfBank;
	}
	public void setNameOfBank(String nameOfBank) {
		this.nameOfBank = nameOfBank;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setUniqueUploadId(String uniqueUploadId) {
		this.uniqueUploadId = uniqueUploadId;
	}
	public String getUniqueUploadId() {
		return uniqueUploadId;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankBranchAddress() {
		return bankBranchAddress;
	}
	public void setBankBranchAddress(String bankBranchAddress) {
		this.bankBranchAddress = bankBranchAddress;
	}
	public String getAuthOfBank() {
		return authOfBank;
	}
	public void setAuthOfBank(String authOfBank) {
		this.authOfBank = authOfBank;
	}
	public String getDepWithPossession() {
		return depWithPossession;
	}
	public void setDepWithPossession(String depWithPossession) {
		this.depWithPossession = depWithPossession;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getSanctdAmount() {
		return sanctdAmount;
	}
	public void setSanctdAmount(String sanctdAmount) {
		this.sanctdAmount = sanctdAmount;
	}
	public String getPoaWithConsidrtn() {
		return poaWithConsidrtn;
	}
	public void setPoaWithConsidrtn(String poaWithConsidrtn) {
		this.poaWithConsidrtn = poaWithConsidrtn;
	}
	public String getAucAmount() {
		return aucAmount;
	}
	public void setAucAmount(String aucAmount) {
		this.aucAmount = aucAmount;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}
	public String getAuthPhoneNo() {
		return authPhoneNo;
	}
	public void setAuthPhoneNo(String authPhoneNo) {
		this.authPhoneNo = authPhoneNo;
	}
	public String getAuthAddress() {
		return authAddress;
	}
	public void setAuthAddress(String authAddress) {
		this.authAddress = authAddress;
	}
	public String getHidnUserId() {
		return hidnUserId;
	}
	public void setHidnUserId(String hidnUserId) {
		this.hidnUserId = hidnUserId;
	}
	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}
	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}
	public String getCheckRegNo() {
		return checkRegNo;
	}
	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public RegCompleteMakerDTO getRegDTO() {
		return regDTO;
	}
	public void setRegDTO(RegCompleteMakerDTO regDTO) {
		this.regDTO = regDTO;
	}
	
	//ADDED BY SIMRAN
	private String deedDocName;
	private String govPartyType;
	public String getGovPartyType() {
		return govPartyType;
	}
	public void setGovPartyType(String govPartyType) {
		this.govPartyType = govPartyType;
	}
	
	



	private String deedDocPath;
	private String deedDoc;
	private String deedDocChkBox;
	private String forwardJsp;
	private String pdfFlag = "";
	
	

	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getPropaddress() {
		return propaddress;
	}
	public void setPropaddress(String propaddress) {
		this.propaddress = propaddress;
	}
	public String getProparear() {
		return proparear;
	}
	public void setProparear(String proparear) {
		this.proparear = proparear;
	}



	private String protype;
	private String propaddress;
	private String proparear;

	public String getDeedDocName() {
		return deedDocName;
	}
	public void setDeedDocName(String deedDocName) {
		this.deedDocName = deedDocName;
	}
	public String getDeedDocPath() {
		return deedDocPath;
	}
	public void setDeedDocPath(String deedDocPath) {
		this.deedDocPath = deedDocPath;
	}
	public String getDeedDoc() {
		return deedDoc;
	}
	public void setDeedDoc(String deedDoc) {
		this.deedDoc = deedDoc;
	}
	public String getDeedDocChkBox() {
		return deedDocChkBox;
	}
	public void setDeedDocChkBox(String deedDocChkBox) {
		this.deedDocChkBox = deedDocChkBox;
	}
	
	
	public FormFile getFileAUPath() {
		return fileAUPath;
	}
	public void setFileAUPath(FormFile fileAUPath) {
		this.fileAUPath = fileAUPath;
	}
	public String getFileAUName() {
		return fileAUName;
	}
	public void setFileAUName(String fileAUName) {
		this.fileAUName = fileAUName;
	}
	public void setListDto(ArrayList<CommonDTO> listDto) {
		this.listDto = listDto;
	}
	public ArrayList<CommonDTO> getListDto() {
		return listDto;
	}
	public void setCheckBoxVerified(String checkBoxVerified) {
		this.checkBoxVerified = checkBoxVerified;
	}
	public String getCheckBoxVerified() {
		return checkBoxVerified;
	}
	public void setTransCheck(String transCheck) {
		this.transCheck = transCheck;
	}
	public String getTransCheck() {
		return transCheck;
	}
	public String getForwardJsp() {
		return forwardJsp;
	}
	public void setForwardJsp(String forwardJsp) {
		this.forwardJsp = forwardJsp;
	}
	public String getPdfFlag() {
		return pdfFlag;
	}
	public void setPdfFlag(String pdfFlag) {
		this.pdfFlag = pdfFlag;
	}
	public void setParty1or2(int party1or2) {
		this.party1or2 = party1or2;
	}
	public int getParty1or2() {
		return party1or2;
	}
	public void setMineralListSelected(ArrayList mineralListSelected) {
		this.mineralListSelected = mineralListSelected;
	}
	public ArrayList getMineralListSelected() {
		return mineralListSelected;
	}
	public void setMineralSelected(String mineralSelected) {
		this.mineralSelected = mineralSelected;
	}
	public String getMineralSelected() {
		return mineralSelected;
	}
	public void setMiningDuration(double miningDuration) {
		this.miningDuration = miningDuration;
	}
	public double getMiningDuration() {
		return miningDuration;
	}
	
	
	public void setContractDetails(String contractDetails) {
		this.contractDetails = contractDetails;
	}
	public String getContractDetails() {
		return contractDetails;
	}
	/*public void setApplicationIdDisp(String applicationIdDisp) {
		this.applicationIdDisp = applicationIdDisp;
	}
	public String getApplicationIdDisp() {
		return applicationIdDisp;
	}
	*/
	/*public void setEfileTxnId(String efileTxnId) {
		this.efileTxnId = efileTxnId;
	}
	public String getEfileTxnId() {
		return efileTxnId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setExcDate(String excDate) {
		this.excDate = excDate;
	}
	public String getExcDate() {
		return excDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getToDate() {
		return toDate;
	}*/
	/*public void setPendingApplicationList(ArrayList pendingApplicationList) {
		this.pendingApplicationList = pendingApplicationList;
	}
	public ArrayList getPendingApplicationList() {
		return pendingApplicationList;
	}*/
	/*public void setExcDate(Object excDate) {
		this.excDate = excDate;
	}
	public Object getExcDate() {
		return excDate;
	}*/
	public void setPendingRegApplicationList(ArrayList pendingRegApplicationList) {
		this.pendingRegApplicationList = pendingRegApplicationList;
	}
	public ArrayList getPendingRegApplicationList() {
		return pendingRegApplicationList;
	}
	public void setPendingEfileApplicationList(
			ArrayList pendingEfileApplicationList) {
		this.pendingEfileApplicationList = pendingEfileApplicationList;
	}
	public ArrayList getPendingEfileApplicationList() {
		return pendingEfileApplicationList;
	}
	public void setPartyListSelected(ArrayList partyListSelected) {
		this.partyListSelected = partyListSelected;
	}
	public ArrayList getPartyListSelected() {
		return partyListSelected;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRejectSearchList(ArrayList rejectSearchList) {
		this.rejectSearchList = rejectSearchList;
	}
	public ArrayList getRejectSearchList() {
		return rejectSearchList;
	}
	public void setReferSearchList(ArrayList referSearchList) {
		this.referSearchList = referSearchList;
	}
	public ArrayList getReferSearchList() {
		return referSearchList;
	}
	public void setDurationSearchList(ArrayList durationSearchList) {
		this.durationSearchList = durationSearchList;
	}
	public ArrayList getDurationSearchList() {
		return durationSearchList;
	}
	public void setEfileNumber(String efileNumber) {
		this.efileNumber = efileNumber;
	}
	public String getEfileNumber() {
		return efileNumber;
	}
	public void setEfileSearchList(ArrayList efileSearchList) {
		this.efileSearchList = efileSearchList;
	}
	public ArrayList getEfileSearchList() {
		return efileSearchList;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBank() {
		return bank;
	}
	public void setDis(String dis) {
		this.dis = dis;
	}
	public String getDis() {
		return dis;
	}
	public void setPendingApplicationListPo(ArrayList pendingApplicationListPo) {
		this.pendingApplicationListPo = pendingApplicationListPo;
	}
	public ArrayList getPendingApplicationListPo() {
		return pendingApplicationListPo;
	}
	public void setSubmitSearchListPo(ArrayList submitSearchListPo) {
		this.submitSearchListPo = submitSearchListPo;
	}
	public ArrayList getSubmitSearchListPo() {
		return submitSearchListPo;
	}
	public void setPendingSearchListPo(ArrayList pendingSearchListPo) {
		this.pendingSearchListPo = pendingSearchListPo;
	}
	public ArrayList getPendingSearchListPo() {
		return pendingSearchListPo;
	}
	public void setRejectSearchListPo(ArrayList rejectSearchListPo) {
		this.rejectSearchListPo = rejectSearchListPo;
	}
	public ArrayList getRejectSearchListPo() {
		return rejectSearchListPo;
	}
	public void setReferSearchListPo(ArrayList referSearchListPo) {
		this.referSearchListPo = referSearchListPo;
	}
	public ArrayList getReferSearchListPo() {
		return referSearchListPo;
	}
	public void setDurationSearchListPo(ArrayList durationSearchListPo) {
		this.durationSearchListPo = durationSearchListPo;
	}
	public ArrayList getDurationSearchListPo() {
		return durationSearchListPo;
	}
	public void setEfileSearchListPo(ArrayList efileSearchListPo) {
		this.efileSearchListPo = efileSearchListPo;
	}
	public ArrayList getEfileSearchListPo() {
		return efileSearchListPo;
	}
	public void setPartytxnid(String partytxnid) {
		this.partytxnid = partytxnid;
	}
	public String getPartytxnid() {
		return partytxnid;
	}
	public void setEFILETXNID(String eFILETXNID) {
		EFILETXNID = eFILETXNID;
	}
	public String getEFILETXNID() {
		return EFILETXNID;
	}
	public void setAPPELLATETYPENAME(String aPPELLATETYPENAME) {
		APPELLATETYPENAME = aPPELLATETYPENAME;
	}
	public String getAPPELLATETYPENAME() {
		return APPELLATETYPENAME;
	}
	public void setGetpartyPrintDetails(ArrayList getpartyPrintDetails) {
		this.getpartyPrintDetails = getpartyPrintDetails;
	}
	public ArrayList getGetpartyPrintDetails() {
		return getpartyPrintDetails;
	}
	public void setPartyaddorg(String partyaddorg) {
		this.partyaddorg = partyaddorg;
	}
	public String getPartyaddorg() {
		return partyaddorg;
	}
	public void setPartymobileorg(String partymobileorg) {
		this.partymobileorg = partymobileorg;
	}
	public String getPartymobileorg() {
		return partymobileorg;
	}
	public void setPartyorg(String partyorg) {
		this.partyorg = partyorg;
	}
	public String getPartyorg() {
		return partyorg;
	}
	public void setPratyautho(String pratyautho) {
		this.pratyautho = pratyautho;
	}
	public String getPratyautho() {
		return pratyautho;
	}
	public void setPartygura(String partygura) {
		this.partygura = partygura;
	}
	public String getPartygura() {
		return partygura;
	}
	public void setPartyauthoadd(String partyauthoadd) {
		this.partyauthoadd = partyauthoadd;
	}
	public String getPartyauthoadd() {
		return partyauthoadd;
	}
	public void setPartyaddoutmp(String partyaddoutmp) {
		this.partyaddoutmp = partyaddoutmp;
	}
	public String getPartyaddoutmp() {
		return partyaddoutmp;
	}
	public void setPartyaddauthooutmp(String partyaddauthooutmp) {
		this.partyaddauthooutmp = partyaddauthooutmp;
	}
	public String getPartyaddauthooutmp() {
		return partyaddauthooutmp;
	}
	public void setPROPERTYVALID(String pROPERTYVALID) {
		PROPERTYVALID = pROPERTYVALID;
	}
	public String getPROPERTYVALID() {
		return PROPERTYVALID;
	}
	public void setPropertyEfileValId(String propertyEfileValId) {
		this.propertyEfileValId = propertyEfileValId;
	}
	public String getPropertyEfileValId() {
		return propertyEfileValId;
	}
	public void setEfilePaymentCompleteListPo(ArrayList efilePaymentCompleteListPo) {
		this.efilePaymentCompleteListPo = efilePaymentCompleteListPo;
	}
	public ArrayList getEfilePaymentCompleteListPo() {
		return efilePaymentCompleteListPo;
	}
	public void setPendingInitateApplicationListPo(
			ArrayList pendingInitateApplicationListPo) {
		this.pendingInitateApplicationListPo = pendingInitateApplicationListPo;
	}
	public ArrayList getPendingInitateApplicationListPo() {
		return pendingInitateApplicationListPo;
	}
	public void setAppStatusEfile(String appStatusEfile) {
		this.appStatusEfile = appStatusEfile;
	}
	public String getAppStatusEfile() {
		return appStatusEfile;
	}
	public void setHidnRegTxnIdEfile(String hidnRegTxnIdEfile) {
		this.hidnRegTxnIdEfile = hidnRegTxnIdEfile;
	}
	public String getHidnRegTxnIdEfile() {
		return hidnRegTxnIdEfile;
	}
	public void setEstampCodeDashboard(String estampCodeDashboard) {
		this.estampCodeDashboard = estampCodeDashboard;
	}
	public String getEstampCodeDashboard() {
		return estampCodeDashboard;
	}
	public void setEstampStatusDashboard(String estampStatusDashboard) {
		this.estampStatusDashboard = estampStatusDashboard;
	}
	public String getEstampStatusDashboard() {
		return estampStatusDashboard;
	}
	public void setEfileFee(String efileFee) {
		this.efileFee = efileFee;
	}
	public String getEfileFee() {
		return efileFee;
	}
	public void setPURLOAN(String pURLOAN) {
		PURLOAN = pURLOAN;
	}
	public String getPURLOAN() {
		return PURLOAN;
	}
	public void setDEEDNAME(String dEEDNAME) {
		DEEDNAME = dEEDNAME;
	}
	public String getDEEDNAME() {
		return DEEDNAME;
	}
	public void setECODE(String eCODE) {
		ECODE = eCODE;
	}
	public String getECODE() {
		return ECODE;
	}
	public void setTypePropertyEFile(String typePropertyEFile) {
		TypePropertyEFile = typePropertyEFile;
	}
	public String getTypePropertyEFile() {
		return TypePropertyEFile;
	}
	public void setAREAEFILE(String aREAEFILE) {
		AREAEFILE = aREAEFILE;
	}
	public String getAREAEFILE() {
		return AREAEFILE;
	}
	public void setFileError(String fileError) {
		this.fileError = fileError;
	}
	public String getFileError() {
		return fileError;
	}
	public void setPhysicalStampCode(String physicalStampCode) {
		this.physicalStampCode = physicalStampCode;
	}
	public String getPhysicalStampCode() {
		return physicalStampCode;
	}
	public void setPhysicalStampFlag(String physicalStampFlag) {
		this.physicalStampFlag = physicalStampFlag;
	}
	public String getPhysicalStampFlag() {
		return physicalStampFlag;
	}
	public void setPhyCode(String phyCode) {
		this.phyCode = phyCode;
	}
	public String getPhyCode() {
		return phyCode;
	}
	public void setPhysicalStampFlagPo(String physicalStampFlagPo) {
		this.physicalStampFlagPo = physicalStampFlagPo;
	}
	public String getPhysicalStampFlagPo() {
		return physicalStampFlagPo;
	}
	public void setPhyCodePo(String phyCodePo) {
		this.phyCodePo = phyCodePo;
	}
	public String getPhyCodePo() {
		return phyCodePo;
	}
	public void setEfileFeePrint(String efileFeePrint) {
		this.efileFeePrint = efileFeePrint;
	}
	public String getEfileFeePrint() {
		return efileFeePrint;
	}
	public void setFileSizeUploadError(String fileSizeUploadError) {
		this.fileSizeUploadError = fileSizeUploadError;
	}
	public String getFileSizeUploadError() {
		return fileSizeUploadError;
	}
	public void setSrStampVendorCode(String srStampVendorCode) {
		this.srStampVendorCode = srStampVendorCode;
	}
	public String getSrStampVendorCode() {
		return srStampVendorCode;
	}
	public void setSrSeriesNo(String srSeriesNo) {
		this.srSeriesNo = srSeriesNo;
	}
	public String getSrSeriesNo() {
		return srSeriesNo;
	}
	public void setSrDateOfStamp(String srDateOfStamp) {
		this.srDateOfStamp = srDateOfStamp;
	}
	public String getSrDateOfStamp() {
		return srDateOfStamp;
	}
	public void setPoStampVendorCode(String poStampVendorCode) {
		this.poStampVendorCode = poStampVendorCode;
	}
	public String getPoStampVendorCode() {
		return poStampVendorCode;
	}
	public void setPoSeriesNo(String poSeriesNo) {
		this.poSeriesNo = poSeriesNo;
	}
	public String getPoSeriesNo() {
		return poSeriesNo;
	}
	public void setPoDateOfStamp(String poDateOfStamp) {
		this.poDateOfStamp = poDateOfStamp;
	}
	public String getPoDateOfStamp() {
		return poDateOfStamp;
	}
	public void setPropType(String propType) {
		this.propType = propType;
	}
	public String getPropType() {
		return propType;
	}
	public void setKhasraNo(String khasraNo) {
		this.khasraNo = khasraNo;
	}
	public String getKhasraNo() {
		return khasraNo;
	}
	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}
	public String getLagaan() {
		return lagaan;
	}
	public void setRinNo(String rinNo) {
		this.rinNo = rinNo;
	}
	public String getRinNo() {
		return rinNo;
	}
	public void setKhasraArea(String khasraArea) {
		this.khasraArea = khasraArea;
	}
	public String getKhasraArea() {
		return khasraArea;
	}
	public void setUploadPathSR(String uploadPathSR) {
		this.uploadPathSR = uploadPathSR;
	}
	public String getUploadPathSR() {
		return uploadPathSR;
	}
	public void setUploadView(String uploadView) {
		this.uploadView = uploadView;
	}
	public String getUploadView() {
		return uploadView;
	}
	public void setEfilepartyspouse(String efilepartyspouse) {
		this.efilepartyspouse = efilepartyspouse;
	}
	public String getEfilepartyspouse() {
		return efilepartyspouse;
	}
	public void setEfilepartynationality(String efilepartynationality) {
		this.efilepartynationality = efilepartynationality;
	}
	public String getEfilepartynationality() {
		return efilepartynationality;
	}
	public void setEfilepartyaddoutmp(String efilepartyaddoutmp) {
		this.efilepartyaddoutmp = efilepartyaddoutmp;
	}
	public String getEfilepartyaddoutmp() {
		return efilepartyaddoutmp;
	}
	public void setEfilepostalcode(String efilepostalcode) {
		this.efilepostalcode = efilepostalcode;
	}
	public String getEfilepostalcode() {
		return efilepostalcode;
	}
	public void setEfilephotoId(String efilephotoId) {
		this.efilephotoId = efilephotoId;
	}
	public String getEfilephotoId() {
		return efilephotoId;
	}
	public void setEfileupload1(String efileupload1) {
		this.efileupload1 = efileupload1;
	}
	public String getEfileupload1() {
		return efileupload1;
	}
	public void setEfileupload2(String efileupload2) {
		this.efileupload2 = efileupload2;
	}
	public String getEfileupload2() {
		return efileupload2;
	}
	public void setEfileupload3(String efileupload3) {
		this.efileupload3 = efileupload3;
	}
	public String getEfileupload3() {
		return efileupload3;
	}
	public void setEfileuploadflag1(String efileuploadflag1) {
		this.efileuploadflag1 = efileuploadflag1;
	}
	public String getEfileuploadflag1() {
		return efileuploadflag1;
	}
	public void setEfileuploadflag2(String efileuploadflag2) {
		this.efileuploadflag2 = efileuploadflag2;
	}
	public String getEfileuploadflag2() {
		return efileuploadflag2;
	}
	public void setEfileuploadflag3(String efileuploadflag3) {
		this.efileuploadflag3 = efileuploadflag3;
	}
	public String getEfileuploadflag3() {
		return efileuploadflag3;
	}
	public void setEfileUploadView(String efileUploadView) {
		this.efileUploadView = efileUploadView;
	}
	public String getEfileUploadView() {
		return efileUploadView;
	}
	public void setEfileuploadOrg1(String efileuploadOrg1) {
		this.efileuploadOrg1 = efileuploadOrg1;
	}
	public String getEfileuploadOrg1() {
		return efileuploadOrg1;
	}
	public void setEfileuploadOrg2(String efileuploadOrg2) {
		this.efileuploadOrg2 = efileuploadOrg2;
	}
	public String getEfileuploadOrg2() {
		return efileuploadOrg2;
	}
	public void setEfileuploadOrg3(String efileuploadOrg3) {
		this.efileuploadOrg3 = efileuploadOrg3;
	}
	public String getEfileuploadOrg3() {
		return efileuploadOrg3;
	}
	public void setEfileuploadOrgflag1(String efileuploadOrgflag1) {
		this.efileuploadOrgflag1 = efileuploadOrgflag1;
	}
	public String getEfileuploadOrgflag1() {
		return efileuploadOrgflag1;
	}
	public void setEfileuploadOrgflag2(String efileuploadOrgflag2) {
		this.efileuploadOrgflag2 = efileuploadOrgflag2;
	}
	public String getEfileuploadOrgflag2() {
		return efileuploadOrgflag2;
	}
	public void setEfileuploadOrgflag3(String efileuploadOrgflag3) {
		this.efileuploadOrgflag3 = efileuploadOrgflag3;
	}
	public String getEfileuploadOrgflag3() {
		return efileuploadOrgflag3;
	}
	public void setEfileUploadOrgView(String efileUploadOrgView) {
		this.efileUploadOrgView = efileUploadOrgView;
	}
	public String getEfileUploadOrgView() {
		return efileUploadOrgView;
	}
	public void setPropImage5DocumentName(String propImage5DocumentName) {
		this.propImage5DocumentName = propImage5DocumentName;
	}
	public String getPropImage5DocumentName() {
		return propImage5DocumentName;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setStatusFlagSR(String statusFlagSR) {
		this.statusFlagSR = statusFlagSR;
	}
	public String getStatusFlagSR() {
		return statusFlagSR;
	}
	public void setStatusFlagPO(String statusFlagPO) {
		this.statusFlagPO = statusFlagPO;
	}
	public String getStatusFlagPO() {
		return statusFlagPO;
	}
	public void setExemptionEfile(String exemptionEfile) {
		this.exemptionEfile = exemptionEfile;
	}
	public String getExemptionEfile() {
		return exemptionEfile;
	}
	public void setExemptionListEfileSelected(ArrayList exemptionListEfileSelected) {
		this.exemptionListEfileSelected = exemptionListEfileSelected;
	}
	public ArrayList getExemptionListEfileSelected() {
		return exemptionListEfileSelected;
	}
	public void setExemptionFlag(String exemptionFlag) {
		this.exemptionFlag = exemptionFlag;
	}
	public String getExemptionFlag() {
		return exemptionFlag;
	}
	public void setPartyRoleInd(String partyRoleInd) {
		this.partyRoleInd = partyRoleInd;
	}
	public String getPartyRoleInd() {
		return partyRoleInd;
	}
	public void setPartyTypeInd(String partyTypeInd) {
		this.partyTypeInd = partyTypeInd;
	}
	public String getPartyTypeInd() {
		return partyTypeInd;
	}
	public void setPhonenoind(String phonenoind) {
		this.phonenoind = phonenoind;
	}
	public String getPhonenoind() {
		return phonenoind;
	}
	public void setEfiledisableity(String efiledisableity) {
		this.efiledisableity = efiledisableity;
	}
	public String getEfiledisableity() {
		return efiledisableity;
	}
	public void setEfileuploadOrg4(String efileuploadOrg4) {
		this.efileuploadOrg4 = efileuploadOrg4;
	}
	public String getEfileuploadOrg4() {
		return efileuploadOrg4;
	}
	public void setOwnerListEfileSelected(ArrayList ownerListEfileSelected) {
		this.ownerListEfileSelected = ownerListEfileSelected;
	}
	public ArrayList getOwnerListEfileSelected() {
		return ownerListEfileSelected;
	}
	public void setPhyListEfileSelected(ArrayList phyListEfileSelected) {
		this.phyListEfileSelected = phyListEfileSelected;
	}
	public ArrayList getPhyListEfileSelected() {
		return phyListEfileSelected;
	}
	public void setEstamppayflag(String estamppayflag) {
		this.estamppayflag = estamppayflag;
	}
	public String getEstamppayflag() {
		return estamppayflag;
	}
	public void setPartyListSelected1(ArrayList partyListSelected1) {
		this.partyListSelected1 = partyListSelected1;
	}
	public ArrayList getPartyListSelected1() {
		return partyListSelected1;
	}
	public void setTehsiltype(String tehsiltype) {
		this.tehsiltype = tehsiltype;
	}
	public String getTehsiltype() {
		return tehsiltype;
	}
	public void setTehsilintype(String tehsilintype) {
		this.tehsilintype = tehsilintype;
	}
	public String getTehsilintype() {
		return tehsilintype;
	}
	public void setIdparty(String idparty) {
		this.idparty = idparty;
	}
	public String getIdparty() {
		return idparty;
	}
	public void setTotalpayableduty(String totalpayableduty) {
		this.totalpayableduty = totalpayableduty;
	}
	public String getTotalpayableduty() {
		return totalpayableduty;
	}
	public void setExempflag(String exempflag) {
		this.exempflag = exempflag;
	}
	public String getExempflag() {
		return exempflag;
	}
	public void setDisplaypurploan(String displaypurploan) {
		this.displaypurploan = displaypurploan;
	}
	public String getDisplaypurploan() {
		return displaypurploan;
	}
	public void setExemptionListEfileSelected4(
			ArrayList exemptionListEfileSelected4) {
		this.exemptionListEfileSelected4 = exemptionListEfileSelected4;
	}
	public ArrayList getExemptionListEfileSelected4() {
		return exemptionListEfileSelected4;
	}
	public void setExempflag4(String exempflag4) {
		this.exempflag4 = exempflag4;
	}
	public String getExempflag4() {
		return exempflag4;
	}
	public void setPhysicalStampFlagPo1(String physicalStampFlagPo1) {
		this.physicalStampFlagPo1 = physicalStampFlagPo1;
	}
	public String getPhysicalStampFlagPo1() {
		return physicalStampFlagPo1;
	}
	public void setPopropflag(String popropflag) {
		this.popropflag = popropflag;
	}
	public String getPopropflag() {
		return popropflag;
	}
	public void setPopropflag1(String popropflag1) {
		this.popropflag1 = popropflag1;
	}
	public String getPopropflag1() {
		return popropflag1;
	}
	public void setDisplaypropoutMP(String displaypropoutMP) {
		this.displaypropoutMP = displaypropoutMP;
	}
	public String getDisplaypropoutMP() {
		return displaypropoutMP;
	}
	public void setDisplaypropoutMPFlag(String displaypropoutMPFlag) {
		this.displaypropoutMPFlag = displaypropoutMPFlag;
	}
	public String getDisplaypropoutMPFlag() {
		return displaypropoutMPFlag;
	}
	public void setDisplaypropoutMPFlag1(String displaypropoutMPFlag1) {
		this.displaypropoutMPFlag1 = displaypropoutMPFlag1;
	}
	public String getDisplaypropoutMPFlag1() {
		return displaypropoutMPFlag1;
	}
	public void setPhysicalStampFlag1(String physicalStampFlag1) {
		this.physicalStampFlag1 = physicalStampFlag1;
	}
	public String getPhysicalStampFlag1() {
		return physicalStampFlag1;
	}
	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}
	public String getUploadpath() {
		return uploadpath;
	}
	public void setUploadflag(String uploadflag) {
		this.uploadflag = uploadflag;
	}
	public String getUploadflag() {
		return uploadflag;
	}
	public void setUploadpath1(String uploadpath1) {
		this.uploadpath1 = uploadpath1;
	}
	public String getUploadpath1() {
		return uploadpath1;
	}
	public void setUploadflag1(String uploadflag1) {
		this.uploadflag1 = uploadflag1;
	}
	public String getUploadflag1() {
		return uploadflag1;
	}
	public void setFormuploadpath(String formuploadpath) {
		this.formuploadpath = formuploadpath;
	}
	public String getFormuploadpath() {
		return formuploadpath;
	}
	public void setFormuploadflag2(String formuploadflag2) {
		this.formuploadflag2 = formuploadflag2;
	}
	public String getFormuploadflag2() {
		return formuploadflag2;
	}
	public void setFormuploadpath1(String formuploadpath1) {
		this.formuploadpath1 = formuploadpath1;
	}
	public String getFormuploadpath1() {
		return formuploadpath1;
	}
	public void setFormuploadflag1(String formuploadflag1) {
		this.formuploadflag1 = formuploadflag1;
	}
	public String getFormuploadflag1() {
		return formuploadflag1;
	}
	public void setCommonView(String commonView) {
		this.commonView = commonView;
	}
	public String getCommonView() {
		return commonView;
	}
	public void setIndtehsilmodify(String indtehsilmodify) {
		this.indtehsilmodify = indtehsilmodify;
	}
	public String getIndtehsilmodify() {
		return indtehsilmodify;
	}
	public void setIndtehsilmodify1(String indtehsilmodify1) {
		this.indtehsilmodify1 = indtehsilmodify1;
	}
	public String getIndtehsilmodify1() {
		return indtehsilmodify1;
	}
	public void setGetpropPrintDetails(ArrayList getpropPrintDetails) {
		this.getpropPrintDetails = getpropPrintDetails;
	}
	public ArrayList getGetpropPrintDetails() {
		return getpropPrintDetails;
	}
	
		public void setDutyAlreadyPaid(String dutyAlreadyPaid) {
		this.dutyAlreadyPaid = dutyAlreadyPaid;
	}
	public String getDutyAlreadyPaid() {
		return dutyAlreadyPaid;
	}
	public void setSecuredamt(String securedamt) {
		this.securedamt = securedamt;
	}
	public String getSecuredamt() {
		return securedamt;
	}
	public void setWithpossession(String withpossession) {
		this.withpossession = withpossession;
	}
	public String getWithpossession() {
		return withpossession;
	}
	public void setExtraflag(String extraflag) {
		this.extraflag = extraflag;
	}
	public String getExtraflag() {
		return extraflag;
	}
	public void setExtraflag1(String extraflag1) {
		this.extraflag1 = extraflag1;
	}
	public String getExtraflag1() {
		return extraflag1;
	}
	public void setGovdesg(String govdesg) {
		this.govdesg = govdesg;
	}
	public String getGovdesg() {
		return govdesg;
	}
	public void setGovfather(String govfather) {
		this.govfather = govfather;
	}
	public String getGovfather() {
		return govfather;
	}
	public void setGovaddress(String govaddress) {
		this.govaddress = govaddress;
	}
	public String getGovaddress() {
		return govaddress;
	}
	public void setIndname(String indname) {
		this.indname = indname;
	}
	public String getIndname() {
		return indname;
	}
	public void setIndfather(String indfather) {
		this.indfather = indfather;
	}
	public String getIndfather() {
		return indfather;
	}
	public void setIndiaddress(String indiaddress) {
		this.indiaddress = indiaddress;
	}
	public String getIndiaddress() {
		return indiaddress;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgfather(String orgfather) {
		this.orgfather = orgfather;
	}
	public String getOrgfather() {
		return orgfather;
	}
	public void setOrgiaddress(String orgiaddress) {
		this.orgiaddress = orgiaddress;
	}
	public String getOrgiaddress() {
		return orgiaddress;
	}
	public void setGovpartyType(String govpartyType) {
		this.govpartyType = govpartyType;
	}
	public String getGovpartyType() {
		return govpartyType;
	}
	public void setIndpartyType(String indpartyType) {
		this.indpartyType = indpartyType;
	}
	public String getIndpartyType() {
		return indpartyType;
	}
	public void setOrgpartyType(String orgpartyType) {
		this.orgpartyType = orgpartyType;
	}
	public String getOrgpartyType() {
		return orgpartyType;
	}
	public void setPoaauthname(String poaauthname) {
		this.poaauthname = poaauthname;
	}
	public String getPoaauthname() {
		return poaauthname;
	}
	public void setPoafather(String poafather) {
		this.poafather = poafather;
	}
	public String getPoafather() {
		return poafather;
	}
	public void setPoaaddress(String poaaddress) {
		this.poaaddress = poaaddress;
	}
	public String getPoaaddress() {
		return poaaddress;
	}
	public void setPoapartytype(String poapartytype) {
		this.poapartytype = poapartytype;
	}
	public String getPoapartytype() {
		return poapartytype;
	}
	public void setGetpartyPrintDetailsIndPOA(ArrayList getpartyPrintDetailsIndPOA) {
		this.getpartyPrintDetailsIndPOA = getpartyPrintDetailsIndPOA;
	}
	public ArrayList getGetpartyPrintDetailsIndPOA() {
		return getpartyPrintDetailsIndPOA;
	}
	public void setGetpartyPrintDetailsOrgPOA(ArrayList getpartyPrintDetailsOrgPOA) {
		this.getpartyPrintDetailsOrgPOA = getpartyPrintDetailsOrgPOA;
	}
	public ArrayList getGetpartyPrintDetailsOrgPOA() {
		return getpartyPrintDetailsOrgPOA;
	}
	public void setPoaorgauthname(String poaorgauthname) {
		this.poaorgauthname = poaorgauthname;
	}
	public String getPoaorgauthname() {
		return poaorgauthname;
	}
	public void setPoaorgfather(String poaorgfather) {
		this.poaorgfather = poaorgfather;
	}
	public String getPoaorgfather() {
		return poaorgfather;
	}
	public void setPoaorgaddress(String poaorgaddress) {
		this.poaorgaddress = poaorgaddress;
	}
	public String getPoaorgaddress() {
		return poaorgaddress;
	}
	public void setPoaorgpartytype(String poaorgpartytype) {
		this.poaorgpartytype = poaorgpartytype;
	}
	public String getPoaorgpartytype() {
		return poaorgpartytype;
	}
	public void setDigpath(String digpath) {
		this.digpath = digpath;
	}
	public String getDigpath() {
		return digpath;
	}
	public void setModifyFlagSR(String modifyFlagSR) {
		this.modifyFlagSR = modifyFlagSR;
	}
	public String getModifyFlagSR() {
		return modifyFlagSR;
	}
	public void setModifyFlagPO(String modifyFlagPO) {
		this.modifyFlagPO = modifyFlagPO;
	}
	public String getModifyFlagPO() {
		return modifyFlagPO;
	}
	public void setAdduploadflag(String adduploadflag) {
		this.adduploadflag = adduploadflag;
	}
	public String getAdduploadflag() {
		return adduploadflag;
	}
	public void setAdduploadflag1(String adduploadflag1) {
		this.adduploadflag1 = adduploadflag1;
	}
	public String getAdduploadflag1() {
		return adduploadflag1;
	}
	public void setPropImage2DocumentName1(String propImage2DocumentName1) {
		this.propImage2DocumentName1 = propImage2DocumentName1;
	}
	public String getPropImage2DocumentName1() {
		return propImage2DocumentName1;
	}
	public void setPropImage2(FormFile propImage2) {
		this.propImage2 = propImage2;
	}
	public FormFile getPropImage2() {
		return propImage2;
	}
	public void setFileError1(String fileError1) {
		this.fileError1 = fileError1;
	}
	public String getFileError1() {
		return fileError1;
	}
	public void setFileSizeError(String fileSizeError) {
		this.fileSizeError = fileSizeError;
	}
	public String getFileSizeError() {
		return fileSizeError;
	}
	public void setFileSizeUploadError1(String fileSizeUploadError1) {
		this.fileSizeUploadError1 = fileSizeUploadError1;
	}
	public String getFileSizeUploadError1() {
		return fileSizeUploadError1;
	}
	public void setUploadFileError(String uploadFileError) {
		this.uploadFileError = uploadFileError;
	}
	public String getUploadFileError() {
		return uploadFileError;
	}
	public void setPartypo(String partypo) {
		this.partypo = partypo;
	}
	public String getPartypo() {
		return partypo;
	}
	public void setPartysr(String partysr) {
		this.partysr = partysr;
	}
	public String getPartysr() {
		return partysr;
	}
	
	//Added by Gulrej
	
	public ArrayList<DutyCalculationDTO> getPhysicalStampList() {
		return physicalStampList;
	}
	public void setPhysicalStampList(ArrayList<DutyCalculationDTO> physicalStampList) {
		this.physicalStampList = physicalStampList;
	}
	
	//Added by Rakesh for CLR Integration: Start
	private KhasraClrDTO clrDto= new KhasraClrDTO();

	public KhasraClrDTO getClrDto() {
		return clrDto;
	}
	public void setClrDto(KhasraClrDTO clrDto) {
		this.clrDto = clrDto;
	}

	
}
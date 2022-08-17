package com.wipro.igrs.RegCompMaker.bd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.wipro.igrs.RegCompMaker.bo.RegCompMkrBO;
import com.wipro.igrs.RegCompMaker.dao.RegCompMkrDAO;
import com.wipro.igrs.RegCompMaker.dto.CommonMkrDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteDutiesDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.RegCompMaker.form.RegCompleteMakerForm;
import com.wipro.igrs.newreginit.bd.RegCommonBD;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.newreginit.dto.RegCommonDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;
import com.wipro.igrs.regCompChecker.dao.RegCompCheckerDAO;

public class RegCompMkrBD
{
  private Logger logger = Logger.getLogger(RegCompMkrBD.class);
  RegCompMkrBO refBO = null;
  
  public ArrayList getComplList()
  {
    this.refBO = new RegCompMkrBO();
    ArrayList list = this.refBO.getComplList();
    ArrayList list1 = new ArrayList();
    if (list != null) {
      for (int i = 0; i < list.size(); i++)
      {
        ArrayList subList = (ArrayList)list.get(i);
        CommonMkrDTO dto = new CommonMkrDTO();
        dto.setId((String)subList.get(0));
        dto.setName((String)subList.get(1));
        list1.add(dto);
      }
    }
    return list1;
  }
  
  public ArrayList getCompDeedList(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getCompDeedList(regNumber);
  }
  
  public ArrayList getDeedType()
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDeedType();
  }
  
  public ArrayList getPropertyListByRegId(String regNumber)
  {
    this.refBO = new RegCompMkrBO();
    return this.refBO.getPropertyListByRegId(regNumber);
  }
  
  public Boolean checkID(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkID(regNumber);
  }

  public Boolean checkEtokenFlag(String officeId)
  throws Exception
{
boolean flag = false;
  RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
  String etokenflag = regCompDAO.checkEtokenFlag(officeId);
  if(etokenflag.equalsIgnoreCase("Y"))
	{
		flag = true;
	}
	
	return flag;	
}
  
  public String checkEtokenUserId(String regId,String userId)
  throws Exception
{
boolean flag = false;
  RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
  String counterUserId  = regCompDAO.checkEtokenUserId(regId,userId);
	return counterUserId;	
}
  
  public ArrayList getpropIdList(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getPropertyIdList(regNumber);
  }
  
  public ArrayList getLinkedAmt(String num, String searchBy)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getLinkedAmt(num, searchBy);
  }
  
  public boolean getConsumedDoc(String num, String searchBy)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    boolean flag = regCompDAO.getConsumedDoc(num, searchBy);
    return flag;
  }
  
  public RegCompleteDutiesDTO getTotalDuties(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    RegCompleteDutiesDTO dutydto = regCompDAO.getTotalDuties(regNumber);
    return dutydto;
  }
  
  public boolean saveLinkedAmt(String regNum, Map myMap, double balDuty, double balfee, String compRegNumber, String userId, String cdate, RegCompleteMakerDTO dto)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    boolean flag = regCompDAO.saveLinkedAmt(regNum, myMap, balDuty, balfee, compRegNumber, userId, cdate, dto);
    return flag;
  }
  
  public String getEmailHoldContent(String regNum, String holdRemarks, String officeId, String language, String ofcId)
  {
    String eMailcontent = "";
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    String time = regCompDAO.getDateTIME();
    String officeName = regCompDAO.getofficeName(officeId, language);
    if ("en".equalsIgnoreCase(language)) {
      eMailcontent = "Your application " + regNum + " which is being processed at " + officeName + " is put on hold at " + time + " due to following reason: \n " + holdRemarks;
    } else {
      eMailcontent = "आपकी आवेदन सन्ख्या " + regNum + "जो  की " + officeName + "मे पूरी की जा रही थी वो निम्न कारणो की वजह से इस समय" + "रोक दी गई हैः\n" + holdRemarks;
    }
    return eMailcontent;
  }
  
  public String getSMSContent(String regNumber)
  {
    String eMailcontent = "";
    eMailcontent = "Your application " + regNumber + " has been put on hold.";
    return eMailcontent;
  }
  
  public String saveHoldData(String regNum, String hldFlag, String fwdPage, String date, String createdBy, String holdId, String holdRemarks, String officeId)
    throws Exception
  {
    String mailContent = getEmailHoldContent(regNum, holdRemarks, officeId, "en", officeId);
    String smsContent = getSMSContent(regNum);
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    regCompDAO.sendMail(regNum, mailContent, createdBy);
    regCompDAO.sendSMS(regNum, smsContent, createdBy);
    return regCompDAO.saveHoldData(regNum, hldFlag, fwdPage, date, createdBy, holdId, holdRemarks);
  }
  
  public String checkHold(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkHold(regNumber);
  }
  
  public boolean updtHoldTbl(String regNum, String userId, String updtDate, String hldFlg)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.updtHoldTbl(regNum, userId, updtDate, hldFlg);
  }
  
  public CommonMkrDTO getLnkdPmnt(String regNum, CommonMkrDTO dto)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getLnkdPmnt(regNum, dto);
  }
  
  public ArrayList getCountry()
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getCountry();
  }
  
  public ArrayList getState(String country)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getState(country);
  }
  
  public ArrayList getDistrict(String state)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDistrict(state);
  }
  
  public boolean insertDtlsMap(String regNum, Map dtlsMap, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertDtlsMap(regNum, dtlsMap, userId);
  }
  
  public boolean linkhistory(String propId, String regNum, String propIdInit, String oldregNum, String status, String userId, String cdate, String transactionType)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.linkhistory(propId, regNum, propIdInit, oldregNum, status, userId, cdate, transactionType);
  }
  
  public boolean chkAlrdyLinked(String propId, String regNum, String propIdInit, String oldregNum, String status)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.chkAlrdyLinked(propId, regNum, propIdInit, oldregNum, status);
  }
  
  public boolean updateLinkngTble(String propId, String regNum, String propIdInit, String oldregNum, String status, String userId, String cdate, String transactionType)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.updateLinkngTble(propId, regNum, propIdInit, 
      oldregNum, status, userId, cdate, transactionType);
  }
  
  public ArrayList getTransactingParties(String regNum, String language)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getTransactingParties(regNum, language);
  }
  
  public ArrayList getWitnessList(String regNum, String cdate, String language)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getWitnessList(regNum, cdate, language);
  }
  
  public ArrayList getConsenterList(String regNum, String language)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getConsenterList(regNum, language);
  }
  
  public ArrayList getofficialList()
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getofficialList();
  }
  
  public boolean insrtPrtyPrsntDet(String hdnPresentParty, String cdate, String userId, String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insrtPrtyPrsntDet(hdnPresentParty, cdate, userId, regNum);
  }
  
  public boolean updtOthrDeedDtlTbl(String hdnPresentWitness, String cdate, String userId, String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.updtOthrDeedDtlTbl(hdnPresentWitness, cdate, userId, regNum);
  }
  
  public String getTypeOfArea(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getTypeOfArea(regNumber);
  }
  
  public boolean getIsPoa(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getIsPoa(regNumber);
  }
  
  public ArrayList getPartyDet(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getPartyDet(regNumber);
  }
  
  public boolean insertComplianceID(String complId, String regNum, String cdate, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertComplianceID(complId, regNum, cdate, userId);
  }
  
  public ArrayList getcheckedCompliance(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getcheckedCompliance(regNumber);
  }
  
  public String[] getDeedInstId(String regNumber)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList list = regCompDAO.getDeedInstId(regNumber);
    
    String str = list.toString();
    str = str.substring(2, str.length() - 2);
    String[] array = str.split(",");
    
    return array;
  }
  
  public ArrayList getPropertyIdMarketVal(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getPropertyIdMarketVal(regNumber);
  }
  
  public ArrayList getAlrdyPaidDuty(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getAlrdyPaidDuty(regNumber);
  }
  
  public boolean saveChecklist(RegCompleteMakerDTO dto, String cdate, String userId, Map dthCertList, Map poaList)
    throws Exception
  {
    boolean flag1 = true;
    
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    String num = regCompDAO.saveChecklist(dto, cdate, userId);
    if (!"".equalsIgnoreCase(num))
    {
      if (dthCertList.size() != 0) {
        flag1 = regCompDAO.saveDthCertDetails(dto, cdate, userId, num, dthCertList);
      }
      if (flag1) {
        if (poaList.size() != 0) {
          flag1 = regCompDAO.savePOADetails(dto, cdate, userId, num, poaList);
        }
      }
      this.logger.debug("BD------->" + flag1);
    }
    else
    {
      flag1 = false;
    }
    return flag1;
  }
  
  public Date getsysDate()
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getsysDate();
  }
  
  public boolean saveLinkedAmtChecker(String regNum, Map myMap, double balDuty, double balfee, boolean tmpflg, String comRegNum, String userId, String cdate, RegCompleteMakerDTO dto)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    boolean flag = regCompDAO.saveLinkedAmtChecker(regNum, myMap, balDuty, balfee, tmpflg, comRegNum, userId, cdate, dto);
    return flag;
  }
  
  public boolean getConsumedDocChecker(String num, String searchBy)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    boolean flag = regCompDAO.getConsumedDocChecker(num, searchBy);
    return flag;
  }
  // methode by akansha to check the consumption status of estamp
  public boolean getConsumedEstamp(String num)
  throws Exception
{
  RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
  boolean flag = regCompDAO.getConsumedEstamp(num);
  return flag;
}
  
  public ArrayList getlnkdamt(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getlnkdamt(regNumber);
  }
  
  public boolean checkAlrdyInsertdRegID(String regNum, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkAlrdyInsertdRegID(regNum, userId, cdate);
  }
  
  public boolean checkalrdyPrsntWtness(String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkalrdyPrsntWtness(regNum);
  }
  
  public String getPaymentFlag(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getPaymentFlag(regNumber);
  }
  
  public boolean insertPaymentDetails(String regNumber, String valueOf, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertPaymentDetails(regNumber, valueOf, 
      userId, cdate);
  }
  
  public boolean updtStatus(String pkey, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.updtStatus(pkey, userId, cdate);
  }
  
  public boolean checkstatusM(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkstatusM(regNumber);
  }
  
  public ArrayList getCaveatDet(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getCaveatDet(regNumber);
  }
  
  public boolean checkalrdyPaidDuty(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkalrdyPaidDuty(regNumber);
  }
  
  public ArrayList getBnkCaveatDet(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getBnkCaveatDet(regNumber);
  }
  
  public boolean dltAlrdyPresentData(String regNumber, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.dltAlrdyPresentData(regNumber, userId, cdate);
  }
  
  public ArrayList getHoldData()
    throws Exception
  {
    ArrayList holdList = new ArrayList();
    ArrayList list = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    list = regCompDAO.holdDetails();
    for (int i = 0; i < list.size(); i++)
    {
      ArrayList list1 = (ArrayList)list.get(i);
      RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
      rDTO.setHoldId((String)list1.get(0));
      rDTO.setHoldName((String)list1.get(1));
      holdList.add(rDTO);
    }
    return holdList;
  }
  
  public String getHoldName(String holdId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getHoldName(holdId);
  }
  
  public boolean checkAlrdyInsertdCheckerLinkin(String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkAlrdyInsertdLinkChkr(regNum);
  }
  
  public boolean insertCompliancedata(String partyTxnId, String partyName, String photoDocName, byte[] content, String cdate, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertCompliancedata(partyTxnId, partyName, 
      photoDocName, content, cdate, userId);
  }
  
  public boolean checkCompliancedata(String partyTxnId)
  {
    return false;
  }
  
  public String getPartyName(String partyTxnId)
    throws Exception
  {
    return null;
  }
  
  public String getreginitNumber(String compNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getreginitNumber(compNumber);
  }
  
  SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
  SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
  
  public ArrayList getCompleteCaveatDetails(String transactionId)
    throws Exception
  {
    ArrayList caveatDetails = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList caveatRecord = regCompDAO.getCompleteCaveatDetails(transactionId);
    for (int i = 0; i < caveatRecord.size(); i++)
    {
      ArrayList list1 = (ArrayList)caveatRecord.get(i);
      RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
      String instName = (String)list1.get(1);
      instName = instName == null ? "" : instName;
      if (instName.equals("")) {
        rDTO.setInstituteName("-");
      } else {
        rDTO.setInstituteName((String)list1.get(1));
      }
      String repName = (String)list1.get(3);
      repName = repName == null ? "" : repName;
      if (repName.equals("")) {
        rDTO.setRepName("-");
      } else {
        rDTO.setRepName((String)list1.get(3));
      }
      String caveatId = (String)list1.get(4);
      caveatId = caveatId == null ? "" : caveatId;
      if (caveatId.equals("")) {
        rDTO.setCaveatId("-");
      } else {
        rDTO.setCaveatId((String)list1.get(4));
      }
      String stayOrderNumber = (String)list1.get(5);
      stayOrderNumber = stayOrderNumber == null ? "" : stayOrderNumber;
      if (stayOrderNumber.equals("")) {
        rDTO.setStayOrdrNum("-");
      } else {
        rDTO.setStayOrdrNum((String)list1.get(5));
      }
      String stayOrderDate = (String)list1.get(6);
      stayOrderDate = stayOrderDate == null ? "" : stayOrderDate;
      if (stayOrderDate.equals("")) {
        rDTO.setStayOrdrDet("-");
      } else {
        rDTO.setStayOrdrDet((String)list1.get(6));
      }
      String stayOrderStrtDate = (String)list1.get(7);
      stayOrderStrtDate = stayOrderStrtDate == null ? "" : stayOrderStrtDate;
      if (stayOrderStrtDate.equals("")) {
        rDTO.setStayOrdrStrtDate("-");
      } else {
        rDTO.setStayOrdrStrtDate(stayOrderStrtDate);
      }
      String stayOrderUptoDate = (String)list1.get(8);
      stayOrderUptoDate = stayOrderUptoDate == null ? "" : stayOrderUptoDate;
      if (stayOrderUptoDate.equals("")) {
        rDTO.setStayOrdrUptoDate("-");
      } else {
        rDTO.setStayOrdrUptoDate(stayOrderUptoDate);
      }
      String caveatDet = (String)list1.get(9);
      caveatDet = caveatDet == null ? "" : caveatDet;
      if (caveatDet.equals("")) {
        rDTO.setCaveatDet("-");
      } else {
        rDTO.setCaveatDet((String)list1.get(9));
      }
      String regID = (String)list1.get(11);
      regID = regID == null ? "" : regID;
      if (regID.equals("")) {
        rDTO.setRegId("-");
      } else {
        rDTO.setRegId((String)list1.get(11));
      }
      String caveatDate = (String)list1.get(12);
      caveatDate = caveatDate == null ? "" : caveatDate;
      if (caveatDate.equals("")) {
        rDTO.setCaveatLoggedDate("-");
      } else {
        rDTO.setCaveatLoggedDate((String)list1.get(12));
      }
      String propID = (String)list1.get(16);
      propID = propID == null ? "" : propID;
      if (propID.equals("")) {
        rDTO.setPropIdInit("-");
      } else {
        rDTO.setPropIdInit((String)list1.get(16));
      }
      caveatDetails.add(rDTO);
    }
    return caveatDetails;
  }
  
  public ArrayList getBankCaveatDetails(String transactionId, String lang)
  {
    ArrayList caveatDetails = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList caveatRecord = regCompDAO.getBankCaveatDetails(transactionId);
    for (int i = 0; i < caveatRecord.size(); i++)
    {
      ArrayList list1 = (ArrayList)caveatRecord.get(i);
      RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
      
      String instName = (String)list1.get(7);
      instName = instName == null ? "" : instName;
      if (instName.equals("")) {
        rDTO.setInstituteName("-");
      } else {
        rDTO.setInstituteName((String)list1.get(7));
      }
      String repName = (String)list1.get(9);
      repName = repName == null ? "" : repName;
      if (repName.equals("")) {
        rDTO.setRepName("-");
      } else {
        rDTO.setRepName((String)list1.get(9));
      }
      String caveatId = (String)list1.get(10);
      caveatId = caveatId == null ? "" : caveatId;
      if (caveatId.equals(""))
      {
        if (lang.equalsIgnoreCase("en")) {
          rDTO.setCaveatId("Protest By Bank");
        } else {
          rDTO.setCaveatId("Protest By Bank");
        }
      }
      else {
        rDTO.setCaveatId((String)list1.get(10));
      }
      String regID = (String)list1.get(11);
      regID = regID == null ? "" : regID;
      if (regID.equals("")) {
        rDTO.setRegId("-");
      } else {
        rDTO.setRegId((String)list1.get(11));
      }
      String caveatDate = (String)list1.get(12);
      caveatDate = caveatDate == null ? "" : caveatDate;
      if (caveatDate.equals("")) {
        rDTO.setCaveatLoggedDate("-");
      } else {
        rDTO.setCaveatLoggedDate((String)list1.get(12));
      }
      String propID = (String)list1.get(14);
      propID = propID == null ? "" : propID;
      if (propID.equals("")) {
        rDTO.setPropIdInit("-");
      } else {
        rDTO.setPropIdInit((String)list1.get(14));
      }
      caveatDetails.add(rDTO);
    }
    return caveatDetails;
  }
  
  public String getDeedId(String regInit)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDeedId(regInit);
  }
  
  public String getInstId(String regInit)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getInstd(regInit);
  }
  
  public boolean insertOtherDeedDetls(RegCompleteMakerDTO rDTO, String userId, String cdate)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertOtherDeedDetls(rDTO, userId, cdate);
  }
  
  public boolean isLinkingPage(String regInitNumber)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.isLinkingPage(regInitNumber);
  }
  
  public boolean checkPOA(String poaAuthNo, String regInit)
    throws Exception
  {
    boolean flag = false;
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    String regNo = regCompDAO.checkPOA(poaAuthNo);
    if (regNo.equalsIgnoreCase(regInit)) {
      flag = true;
    }
    return flag;
  }
  
  public ArrayList getTransPartyDets(String regInitId)
    throws Exception
  {
    ArrayList list = new ArrayList();
    ArrayList partyDetlsList = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    list = regCompDAO.getTransPartyDets(regInitId);
    for (int i = 0; i < list.size(); i++)
    {
      RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
      ArrayList subList = (ArrayList)list.get(i);
      String fName = (String)subList.get(0);
      fName = fName == null ? "" : fName;
      if (!fName.equalsIgnoreCase("")) {
        rDTO.setPartyFirstName((String)subList.get(0));
      } else {
        rDTO.setPartyFirstName((String)subList.get(1));
      }
      partyDetlsList.add(rDTO);
    }
    return partyDetlsList;
  }
  
  public ArrayList getTransPartyIds(String regInitId, String partyName)
    throws Exception
  {
    ArrayList list = new ArrayList();
    ArrayList partyIdsList = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    list = regCompDAO.getTransPartyIds(regInitId, partyName);
    for (int i = 0; i < list.size(); i++)
    {
      RegCompleteMakerDTO rDTO = new RegCompleteMakerDTO();
      ArrayList subList = (ArrayList)list.get(i);
      rDTO.setPartyTypeID((String)subList.get(0));
      partyIdsList.add(rDTO);
    }
    return partyIdsList;
  }
  
  public boolean checkListData(String regInitId, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkListData(regInitId, userId, cdate);
  }
  
  public HashMap getPropertyRelatedComplianeList(String regInitId)
    throws Exception
  {
    HashMap propertyRelatedComplianceMap = new HashMap();
    ArrayList propertyRelatedComplianceList = new ArrayList();
    
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    propertyRelatedComplianceList = regCompDAO.getPropertyRelatedComplianeList(regInitId);
    for (int i = 0; i < propertyRelatedComplianceList.size(); i++)
    {
      ArrayList propertyRelatedComplianceList1 = new ArrayList();
      ArrayList list = (ArrayList)propertyRelatedComplianceList.get(i);
      RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
      String propType = (String)list.get(1);
      if ((list.get(2) != null) && (!list.get(2).toString().equalsIgnoreCase("")))
      {
        rdto.setPropImage1DocumentName("ANGLE 1.JPG");
        rdto.setPropImage1FilePath((String)list.get(2));
      }
      if ((list.get(3) != null) && (!list.get(3).toString().equalsIgnoreCase("")))
      {
        rdto.setPropImage2DocumentName("ANGLE 2.JPG");
        rdto.setPropImage2FilePath((String)list.get(3));
      }
      if ((list.get(4) != null) && (!list.get(4).toString().equalsIgnoreCase("")))
      {
        rdto.setPropImage3DocumentName("ANGLE 3.JPG");
        rdto.setPropImage3FilePath((String)list.get(4));
      }
      if ((list.get(5) != null) && (!list.get(5).toString().equalsIgnoreCase("")))
      {
        rdto.setPropMapDocumentName("MAP.JPG");
        rdto.setPropMapFilePath((String)list.get(5));
      }
      if (propType.equalsIgnoreCase("3"))
      {
        if ((list.get(6) != null) && (!list.get(6).toString().equalsIgnoreCase("")))
        {
          rdto.setPropRinDocumentName("RIN.JPG");
          
          rdto.setPropRinFilePath((String)list.get(6));
        }
        if ((list.get(7) != null) && (!list.get(7).toString().equalsIgnoreCase("")))
        {
          rdto.setPropKhasraDocumentName("KHASRA.JPG");
          rdto.setPropKhasraFilePath((String)list.get(7));
        }
      }
      rdto.setPropertyTypeId(propType);
      rdto.setIsUpload("");
      propertyRelatedComplianceList1.add(rdto);
      propertyRelatedComplianceMap.put((String)list.get(0), propertyRelatedComplianceList1);
    }
    return propertyRelatedComplianceMap;
  }
  
  public HashMap getPartyRelatedComplianeList(String regInitId,String language)
    throws Exception
  {
    HashMap partyRelatedComplianceMap = new HashMap();
    ArrayList partyRelatedComplianceList = new ArrayList();
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    
	RegCompCheckerDAO regCompDAONew =  new RegCompCheckerDAO();

    partyRelatedComplianceList = regCompDAO.getPartyRelatedComplianeList(regInitId);
    
    RegCommonDTO mapDto =new RegCommonDTO();
	RegCommonForm form = new RegCommonForm();
	
	RegCommonBO commonBO = new RegCommonBO();
	RegCommonBD commonBD = new RegCommonBD();
	
	String OwnerRolePoA="";
	String partyId = "";
	String CommonDeedFlag = "";
     CommonDeedFlag=regCompDAONew.getCommonFlowFlag(regInitId);
    
    for (int i = 0; i < partyRelatedComplianceList.size(); i++)
    {
      ArrayList finalList = new ArrayList();
      ArrayList list = (ArrayList)partyRelatedComplianceList.get(i);
      

		if(list.get(0)!=null)
		{
			partyId = list.get(0).toString();
		}
      
      RegCompleteMakerDTO rdto = new RegCompleteMakerDTO();
      String firstName = (String)list.get(1);
      
  	int roleId = Integer.parseInt((String)list.get(3));
	  String[] claimantArr=commonBO.getClaimantFlag(Integer.toString(roleId));
	  mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
	  int ExecutantClaimantfromDB=0;
	  if (list.get(20)!=null && !list.get(20).toString().equalsIgnoreCase(""))
 	  {
      	 ExecutantClaimantfromDB = Integer.parseInt((String)list.get(20));
 	  }	
      
      if ((firstName != "") && (firstName != null))
      {
        rdto.setPartyFirstName((String)list.get(1));
        rdto.setPartyRoleId((String)list.get(3));
        rdto.setRole((String)list.get(4));
        
        rdto.setCollectorCertDocName("Collector's Certificate");
        rdto.setCollectorCertDocPath((String)list.get(5));
        rdto.setPhotoProofDocName("ID Proof(PAN,DL,Aadhar,Voter ID,Passport)");
        rdto.setPhotoProofDocPath((String)list.get(6));
        rdto.setNotAffdExecDocName("Notarized Affidavit by the Executant");
        rdto.setNotAffdExecDocPath((String)list.get(7));
        rdto.setExecPhotoName("Executant's Photograph");
        rdto.setExecPhotoPath((String)list.get(8));
        rdto.setNotAffdAttrName("Notarized Affidavit of Attorney");
        rdto.setNotAffdAttrPath((String)list.get(9));
        rdto.setAttrPhotoName("Attorney Photograph");
        rdto.setAttrPhotoPath((String)list.get(10));
        rdto.setClaimntPhotoName("Claimants's Photograph");
        rdto.setClaimntPhotoPath((String)list.get(11));
        rdto.setPanForm60Name("PAN or form 60/61");
        rdto.setPanForm60Path((String)list.get(12));
        rdto.setIsUpload("");
        
   	 if(list.get(15)!=null &&list.get(15).toString().equalsIgnoreCase("2"))
	  {
	  if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
			  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
		    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 ||
		    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5
		    	||
		    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6)
		    	
	  {
		  mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			 //int ExecutantClaimantfromDB = Integer.parseInt((String)subList.get(15));
			 System.out.println("ExecutantClaimant" +ExecutantClaimantfromDB);
			form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
		 String RoleName = form.getExecutantClaimantName();
   	 System.out.println("Role Name " +RoleName); 
   	// to check hindi and english role 
  	 String HindiEnglishRole[] = RoleName.split(",");
  	 String hindesc = HindiEnglishRole[0];
  	 String EngDesc =HindiEnglishRole[1];
  	 
  	 
  	 String PoaName = null;
  	 if (EngDesc.contains("Authenticated")){ 
  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      		   PoaName =" By(Authenticated PoA Holder) ";
    		  }
    		  else {
    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
    		  }}    
  	 else {
  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
  			 PoaName =" By(PoA Holder) ";
  		  }
  		  else {
  			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
  		  }
  	 }
       	 String OwnerDetail =null;
       	 
       	String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
       	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
       	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;


       
       	rdto.setPartyFirstName(OwnerRolePoA);
     	        //sDTO.setPartyLastName("");
     	      //  i1++;
   	        
	  }
	  // will deed Authenticaed POA finish - Rahul 
	  else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
	     {
	    	 
	     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
	     	 String  RoleName= null;
	     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
	     	mapDto.setRoleName(commonBO.getRoleNameForPOA((String) list.get(3)));
	     	    RoleName = mapDto.getRoleName();
	     	   System.out.println("Role Name " +RoleName); 
            	
	     	  // to check hindi and english role 
        	 String HindiEnglishRole[] = RoleName.split(",");
        	 String hindesc = HindiEnglishRole[0];
        	 String EngDesc =HindiEnglishRole[1];
        	 
        	 
        	 String PoaName = null;
        	 if (EngDesc.contains("Authenticated")){ 
          		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
               		   PoaName =" By(Authenticated PoA Holder) ";
               		  }
               		  else {
               			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
               		  }}    
        	 else {
        		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        			 PoaName =" By(PoA Holder) ";
      		  }
      		  else {
      			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
      		  }
		}


	     	   
            	 System.out.println("POA NAMe is "+PoaName);
      	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
      	      //  System.out.println("PoAOwnerlistdisplay"+poAOwnerList.get(i));
   	         
    	        String OwnerDetail =null;
    	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
            	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
            	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
            	
            	rdto.setPartyFirstName(OwnerRolePoA);
     	       // sDTO.setPartyLastName("");
     	       // i1++;
	    }
	  else
		{
          // Minor name , Guardian set - Rahul
			int age=list.get(16)!=null?Integer.parseInt((String)list.get(16)):0;
			
		
          if(age<RegCompCheckerConstant.MINOR_AGE_LIMIT){
				
				 String relations = commonBO.getRelationshipName((list.get(19).toString()), language);

       	   
				
				if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
				{
				//sDTO.setPartyFirstName("(Minor ) "+MinorName);
				rdto.setPartyFirstName( "(Minor ) "+firstName+" "+(String)list.get(2)+"  "+relations+" "+(String)list.get(17)+" By "+(String)list.get(18));
				}
				else
				{
				//	sDTO.setPartyFirstName("(अवयस्‍क ) "+MinorName);
					//sDTO.setPartyLastName((String)subList.get(2)+"  "+relations+" "+(String)subList.get(11)+" द्वारा "+(String)subList.get(13));
					rdto.setPartyFirstName( "(अवयस्‍क ) "+firstName+" "+(String)list.get(2)+"  "+relations+" "+(String)list.get(17)+" द्वारा "+(String)list.get(18));

				}	
			}
			
		}
	  }
        
        finalList.add(rdto);
        partyRelatedComplianceMap.put((String)list.get(0), finalList);
      }
      else
      {
        rdto.setPartyFirstName((String)list.get(13));
        if ("3".equalsIgnoreCase((String)list.get(15))) {
			if(list.get(14)!=null&&!list.get(14).toString().equalsIgnoreCase(""))
			{
				rdto.setPartyFirstName((String)list.get(14));
			}
			else
			{
				rdto.setPartyFirstName("--");
			}
		}
        
        else{
        	
        	
			rdto.setPartyFirstName((String)list.get(13));
			
			
			if(list.get(15)!=null &&list.get(15).toString().equalsIgnoreCase("1"))
			{
				 if(CommonDeedFlag!=null && "Y".equalsIgnoreCase(CommonDeedFlag)
						  && ExecutantClaimantfromDB==RegInitConstant.CLAIMANT_FLAG_2 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_4 
					    	||ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_5 ||
					    	ExecutantClaimantfromDB ==RegInitConstant.CLAIMANT_FLAG_6
					    	)
	                		
	               {
						//poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						// int ExecutantClaimantfromDB = Integer.parseInt((String)row_list.get(14));
						 System.out.println("ExecutantClaimant" + ExecutantClaimantfromDB );
						form.setExecutantClaimantName(commonBO.getExecutantClaimantNameForPOA(Integer.toString(ExecutantClaimantfromDB)));
	            		 String RoleName = form.getExecutantClaimantName();
	                	 System.out.println("Role Name " +RoleName); 
	                	 String PoaName = null;
	                	// to check hindi and english role 
	                	 String HindiEnglishRole[] = RoleName.split(",");
	                	 String hindesc = HindiEnglishRole[0];
	                	 String EngDesc =HindiEnglishRole[1];
	                	
	                	 if (EngDesc.contains("Authenticated")){ 
	                    		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
 	  	                    		   PoaName =" By(Authenticated PoA Holder) ";
 		                    		  }
 		                    		  else {
 		                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
 		                    		  }}    
	                	 else {
	                		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                			 PoaName =" By(PoA Holder) ";
	                		  }
	                		  else {
	                			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
	                		  }
	                	 }
	                	 
	                    	 String OwnerDetail =null;
	                    	 String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
	                      	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                      	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                      	rdto.setPartyFirstName(OwnerRolePoA);
	                	     
	               }
				
				 else  if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG )
			     {
					// poAOwnerListForOrg = commonBD.getOwnerPoaDescDisplayForOrg(lang, regNumber);
			     	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
			     	 String  RoleName= null;
			     	// sDTO.setRoleName(sealDAO.getPartyRoleDetails((String)subList.get(3),lang));
			     	mapDto.setRoleName(commonBO.getRoleNameForPOA((String) list.get(3)));
		     	    RoleName = mapDto.getRoleName();
			     	   System.out.println("Role Name " +RoleName); 
	                 	
			     	  // to check hindi and english role 
                  	 String HindiEnglishRole[] = RoleName.split(",");
                  	 String hindesc = HindiEnglishRole[0];
                  	 String EngDesc =HindiEnglishRole[1];
                  	 
                  	 
                  	 String PoaName = null;
                  	 if (EngDesc.contains("Authenticated")){ 
                   		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    		   PoaName =" By(Authenticated PoA Holder) ";
	                    		  }
	                    		  else {
	                    			  PoaName =" द्वारा(प्रमाणीकृत  मुख्‍त्‍यार) ";
	                    		  }}    
                  	 else {
                  		 if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  			 PoaName =" By(PoA Holder) ";
                		  }
                		  else {
                			  PoaName =" द्वारा (मुख्‍त्‍यार आम)";
                		  }
				}


			     	   
	                 	 System.out.println("POA NAMe is "+PoaName);
	           	         System.out.println("POA holder flag is "+mapDto.getPoaHolderFlag());
	           	        //System.out.println("PoAOwnerlistdisplay"+poAOwnerListForOrg.get(i));
	        	         
	         	        String OwnerDetail =null;
	         	       String   poAOwnerListNew = commonBD.getOwnerDetails(regInitId,partyId,language);
	                 	String poaNameDetails = commonBD.getOwnerPOAnameDetails(partyId);
	                 	OwnerRolePoA=poAOwnerListNew+PoaName+poaNameDetails;
	                 	rdto.setPartyFirstName(OwnerRolePoA);
	          	       
			    }
				else{
					rdto.setPartyFirstName((String)list.get(13));
					  
						}
			}
			
			
			
        }
        rdto.setPartyRoleId((String)list.get(3));
        rdto.setRole((String)list.get(4));
        
        rdto.setCollectorCertDocName("Collector's Certificate");
        rdto.setCollectorCertDocPath((String)list.get(5));
        rdto.setPhotoProofDocName("ID Proof(PAN,DL,Aadhar,Voter ID,Passport)");
        rdto.setPhotoProofDocPath((String)list.get(6));
        rdto.setNotAffdExecDocName("Notarized Affidavit by the Executant");
        rdto.setNotAffdExecDocPath((String)list.get(7));
        rdto.setExecPhotoName("Executant's Photograph");
        rdto.setExecPhotoPath((String)list.get(8));
        rdto.setNotAffdAttrName("Notarized Affidavit of Attorney");
        rdto.setNotAffdAttrPath((String)list.get(9));
        rdto.setAttrPhotoName("Attorney Photograph");
        rdto.setAttrPhotoPath((String)list.get(10));
        rdto.setClaimntPhotoName("Claimants's Photograph");
        rdto.setClaimntPhotoPath((String)list.get(11));
        rdto.setPanForm60Name("PAN or form 60/61");
        rdto.setPanForm60Path((String)list.get(12));
        rdto.setIsUpload("");
        finalList.add(rdto);
        partyRelatedComplianceMap.put((String)list.get(0), finalList);
      }
    }
    return partyRelatedComplianceMap;
  }
  
  public boolean modifyComplianceListData(RegCompleteMakerForm eForm, String regInitId, String userId, String date)
  {
    boolean flag1 = false;
    boolean flag2 = false;
    
    flag1 = true;
    flag2 = true;
    if ((flag1) && (flag2)) {
      flag1 = true;
    }
    return flag1;
  }
  
  public HashMap getWitnessDetailsForCompliance(String regNumber)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList list = regCompDAO.getWitnessDet(regNumber);
    
    ArrayList sublist = new ArrayList();
    
    HashMap mainMap = new HashMap();
    if (list.size() != 0) {
      for (int i = 0; i < list.size(); i++)
      {
        sublist = (ArrayList)list.get(i);
        ArrayList mainList = new ArrayList();
        RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
        dto.setWitnessTxnNummber((String)sublist.get(0));
        dto.setFnameTrns((String)sublist.get(1));
        dto.setLnameTrns((String)sublist.get(2));
        dto.setRoleName("Witness");
        dto.setWitnessDocName("");
        dto.setWitnessDocContents(dto.getWitnessDocContents());
        dto.setWitnessDocSize("");
        dto.setWitnessDocPath("");
        mainList.add(dto);
        
        mainMap.put((String)sublist.get(0), mainList);
      }
    }
    return mainMap;
  }
  
  public boolean addWitnessPhotographDetails(Map witnessUploadMap, String regInitId, String userId, String cdate)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.addWitnessPhotographDetails(witnessUploadMap, regInitId, userId, cdate);
  }
  
  public String getCancelledDetails(String regInitId)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getCancelledDetails(regInitId);
  }
  
  public boolean getPropLockDetails(String regCompNumber, String propId, String regInitNo, String propInitId)
  {
    boolean flag = false;
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    String status = regCompDAO.getPropLockDetails(regCompNumber, propId);
    if (!status.equalsIgnoreCase("1"))
    {
      ArrayList partyTypeIdList = regCompDAO.getPartyRoleIdDetails(regInitNo, propInitId);
      for (int i = 0; i < partyTypeIdList.size(); i++)
      {
        ArrayList subList = (ArrayList)partyTypeIdList.get(i);
        String partyTypeId = (String)subList.get(0);
		//Removed 50014,50008  Buyer POA holder for property locking.
         if ((partyTypeId.equals("50003")) || (partyTypeId.equals("50007")) || (partyTypeId.equals("50011")) || (partyTypeId.equals("50012")) ) {
          flag = true;
        }
      }
    }
    return flag;
  }
  
  public ArrayList getDeedInstId1(String appId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDeedInstId1(appId);
  }
  
  public ArrayList getAdjudicatedDutyDetls(String appId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getAdjudicatedDutyDetls(appId);
  }
  
  public String emailAlertPresentation(String exeDate, String regInitId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
    Date exDate = sdf2.parse(exeDate);
    Calendar cal = Calendar.getInstance();
    cal.setTime(exDate);
    cal.add(2, 4);
    String presentationDate = getDateFromCal(cal);
    
    String emailContent = "This is in reference of Registration Id (" + regInitId + "). Last day of presentation " + 
      "of property documents is: " + presentationDate;
    return regCompDAO.emailAlert(regInitId, "PRESENTATION DUE", emailContent);
  }
  
  public static String getDateFromCal(Calendar cal)
  {
    String calDate = cal.get(5) + "/" + (cal.get(2) + 1) + "/" + cal.get(1);
    return calDate;
  }
  
  public String emailAlertHold(String regInitId, String officeCode)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
    String districtCode = regCheckDAO.getDistrictCodeChecker(officeCode);
    String emailSub = "Application on Hold";
    String emailContent = "Execution of the registration document (Registration Initiation Id - " + regInitId + ") is kept on hold. Please acknowledge whether to proceed or not";
    return regCompDAO.emailAlertDr(regInitId, emailSub, emailContent, districtCode);
  }
  
  public boolean insertConsenterDtlsMap(String regNum, Map dtlsMap, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertConsenterDtlsMap(regNum, dtlsMap, userId);
  }
  
  public boolean checkalrdyPrsntConsenter(String regNum)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkalrdyPrsntConsenter(regNum);
  }
  
  public boolean insertDeedDocDetails(RegCompleteMakerDTO rdto, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.insertDeedDocDetails(rdto, userId);
  }
  
  public ArrayList getRelationshipList(String gender, String language)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getRelationshipList(gender, language);
  }
  
  public String getPinDetails(String propertyId, String pinEntered)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList list = regCompDAO.getPinDetails(propertyId);
    
    ArrayList subList = new ArrayList();
    String val = "";
    if ((pinEntered != null) && (!pinEntered.isEmpty()))
    {
      String input = pinEntered;
      String output = input.trim();
      while ((output.length() > 1) && (output.charAt(0) == '0')) {
        output = output.substring(1);
      }
      pinEntered = output;
    }
    for (int i = 0; i < list.size(); i++)
    {
      subList = (ArrayList)list.get(i);
      String pinNo = (String)(subList.get(0) == null ? "" : subList.get(0));
      String pinFlag = (String)(subList.get(1) == null ? "" : subList.get(1));
      if (!pinNo.equals(pinEntered)) {
        val = "WrongPin";
      } else if (pinFlag.equalsIgnoreCase("D")) {
        val = "pinExpired";
      } else {
        val = "Y";
      }
    }
    return val;
  }
  
  public boolean UpdateRegistrationCompletionStatus(String regInitId, String status, String date, String userId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.UpdateRegistrationCompletionStatus(regInitId, status, date, userId);
  }
  
  public String getHoldRemarks(String regNumber, String holdId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getHoldRemarks(regNumber, holdId);
  }
  
  public boolean saveHoldResumeDetails(String remarks, String suppDocFileName, String suppDocFilePath, String regNumber, String holdId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.saveHoldResumeDetails(remarks, suppDocFileName, suppDocFilePath, regNumber, holdId);
  }
  
  public boolean updatePhotographDetails(RegCompleteMakerForm eForm, String userId, String date, String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.updatePhotographDetails(eForm, userId, date, regNum);
  }
  
  public boolean resumeLinkingProp(String regNo)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList propIds = regCompDAO.getPropertyLinking(regNo);
    boolean flag = false;
    if ((propIds != null) && (propIds.size() > 0)) {
      for (int i = 0; i < propIds.size(); i++)
      {
        ArrayList propIdRow = (ArrayList)propIds.get(i);
        String propId = (String)propIdRow.get(0);
        String pinFlag = regCompDAO.getPropertyStatus(propId);
        if ("D".equalsIgnoreCase(pinFlag)) {
          flag = true;
        }
      }
    }
    return flag;
  }
  
  public boolean checkPinRequired(String regNo)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkPinRequired(regNo);
  }
  
  public boolean checkPinRequiredBuilding(String propId)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkPinRequiredBuilding(propId);
  }
  
  public String getEstampTxnId(String estampCode)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getEstampTxnId(estampCode);
  }
  
  public String getRegInitNumber(String estampCode)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getRegInitNumber(estampCode);
  }
  
  public ArrayList getDutyDetlsForConfirmation(String appId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDutyDetlsForConfirmation(appId);
  }
  
  public ArrayList getDutyDetlsAdjuForConfirmation(String appId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDutyDetlsAdjuForConfirmation(appId);
  }
  
  public String getInstdFromProp(String propId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getInstdFromProp(propId);
  }
  
  public String getInst(String regTxn)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getInst(regTxn);
  }
  
  public String getDeed(String regTxn)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDeed(regTxn);
  }
  
  public String validateDeedDoc(String deedId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.validateDeedDoc(deedId);
  }
  
  public ArrayList getDeedDocDetails(String regNum)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.getDeedDocDetails(regNum);
  }
  
  public ArrayList getAdjudicatedDutyDetlsChecker(String appId)
    throws Exception
  {
    RegCompCheckerDAO regCheckerDAO = new RegCompCheckerDAO();
    return regCheckerDAO.getAdjudicatedDutyDetls(appId);
  }
  
  public ArrayList getDutyDetlsChecker(String appId)
    throws Exception
  {
    RegCompCheckerDAO regCheckerDAO = new RegCompCheckerDAO();
    return regCheckerDAO.getDutyDetls(appId);
  }
  
  public boolean checkConsumptionOfDeedDoc(String deedId, String path, String name, String regTxnId)
    throws Exception
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    return regCompDAO.checkConsumptionOfDeedDoc(deedId, path, name, regTxnId);
  }
  
  public boolean updatePhotoDetails(String partyId, String photoName, String photoPath)
  {
    try
    {
      RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
      return regCompDAO.updatePhotoDetails(partyId, photoName, photoPath);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean updatePhotoDetailsWitness(String partyId, String photoName, String photoPath)
  {
    try
    {
      RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
      return regCompDAO.updatePhotoDetailsWitness(partyId, photoName, photoPath);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean updatePhotoDetailsConsenter(String partyId, String photoName, String photoPath)
  {
    try
    {
      RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
      return regCompDAO.updatePhotoDetailsConsenter(partyId, photoName, photoPath);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean insertTimeMakerStart(RegCompleteMakerForm eForm, String userId, String sroid)
  {
    try
    {
      RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
      return regCompDAO.insertTimeMakerStart(eForm, userId, sroid);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return false;
  }
  
  public boolean checkArea(String propInitId, String propId, String transactionType, HttpServletRequest request, String language)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    String id1 = regCompDAO.isBuilding(propInitId);
    String id2 = regCompDAO.isBuilding(propId);
    if (("2".equalsIgnoreCase(id1)) || ("2".equalsIgnoreCase(id2))) {
      return true;
    }
    if (regCompDAO.checkPinRequiredBuilding(propId)) {
      return true;
    }
    double totalArea = 0.0D;
    double currentArea = 0.0D;
    String id3 = regCompDAO.getPropertyType(propInitId);
    if ("1".equalsIgnoreCase(id3)) {
      currentArea += regCompDAO.getPlotArea(propInitId);
    } else {
      currentArea += regCompDAO.getAgriArea(propInitId);
    }
    ArrayList list = regCompDAO.areaList(propId);
    if ((list != null) && (list.size() > 0)) {
      for (int i = 0; i < list.size(); i++)
      {
        ArrayList subList = (ArrayList)list.get(i);
        String id = (String)subList.get(1);
        String propid1 = (String)subList.get(0);
        if ("1".equalsIgnoreCase(id)) {
          totalArea += regCompDAO.getPlotArea(propid1);
        } else {
          totalArea += regCompDAO.getAgriArea(propid1);
        }
      }
    } else {
      totalArea = 0.0D;
    }
    double totalArea1 = 0.0D;
    String id4 = regCompDAO.getPropertyType(propId);
    if ("1".equalsIgnoreCase(id4)) {
      totalArea1 += regCompDAO.getPlotArea(propId);
    } else {
      totalArea1 += regCompDAO.getAgriArea(propId);
    }
    double remainingArea = totalArea1 - totalArea;
    if ("F".equalsIgnoreCase(transactionType))
    {
      if (currentArea != remainingArea)
      {
        if ("en".equalsIgnoreCase(language)) {
          request.setAttribute("msg", "Area of both the property must be same for full transaction ");
        } else {
          request.setAttribute("msg", "पूर्ण  ट्रान्जेक्शन के लिए  दोनों संपत्ति का क्षेत्रफल बराबर ही होना चाहिए");
        }
        return false;
      }
      return true;
    }
    if (currentArea > remainingArea)
    {
      if ("en".equalsIgnoreCase(language)) {
        request.setAttribute("msg", "This property has already been sold");
      } else {
        request.setAttribute("msg", "यह संपत्ति पहले ही बेची जा चुकी है।");
      }
      return false;
    }
    return true;
  }
  
  public ArrayList linkingDashBoardList(String regInitNo, String language)
  {
    RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
    ArrayList list = regCompDAO.linkingDashBoardList(regInitNo);
    ArrayList returnList = null;
    if ((list != null) && (list.size() > 0))
    {
      returnList = new ArrayList();
      for (int i = 0; i < list.size(); i++)
      {
        RegCompleteMakerDTO dto = new RegCompleteMakerDTO();
        ArrayList subList = (ArrayList)list.get(i);
        String newPropId = (String)subList.get(0);
        String oldPropId = (String)subList.get(1);
        String oldRegNo = (String)subList.get(2);
        dto.setOldPropId(oldPropId);
        dto.setNewPropId(newPropId);
        dto.setOldRegNoLink(oldRegNo);
        if ("en".equalsIgnoreCase(language)) {
          dto.setPinLinkStatus("Not Generated");
        } else {
          dto.setPinLinkStatus("जनरेट नहीं");
        }
        returnList.add(dto);
      }
    }
    return returnList;
  }
  
  public boolean updatePropertyUpload(String propid, String regNo, String columnNamePath, String docPath)
  {
    RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
    return regCheckDAO.updatePropertyUpload(propid, regNo, columnNamePath, docPath);
  }
  
  public boolean updatePartyUpload(String partyId, String regNo, String columnNamePath, String docPath)
  {
    RegCompCheckerDAO regCheckDAO = new RegCompCheckerDAO();
    return regCheckDAO.updatePartyUpload(partyId, regNo, columnNamePath, docPath);
  }

	//added by saurav for slot booking
	public ArrayList<String> getSlotActiveCheck(String regNumber, String officeId){
		RegCompMkrDAO regMkrDAO = new RegCompMkrDAO();
		return regMkrDAO.getSlotActiveCheck(regNumber,officeId);
	}
	  //Added by Rustam
	  public ArrayList getCyberTehsilFormDetails(String regNum)
	  throws Exception
	  {
		  RegCompMkrDAO regCompDAO = new RegCompMkrDAO();
		  return regCompDAO.getCyberTehsilFormDetails(regNum);
	  }
}

package com.wipro.igrs.slotbooking.bo;

import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dao.SlotReportDAO;
import com.wipro.igrs.slotbooking.dto.SRReportDTO;
import com.wipro.igrs.slotbooking.form.SRSlotReportForm;
import java.util.ArrayList;

public class SlotReportBO
{

    public SlotReportBO()
    {
    }

    public ArrayList getSlotReport(SRSlotReportForm sbform, String officeId)
        throws Exception
    {
        SlotReportDAO dao = new SlotReportDAO();
        ArrayList list = new ArrayList();
        ArrayList reportList = dao.getSRSlotReport(sbform, officeId);
        try
        {
            for(int i = 0; i < reportList.size(); i++)
            {
                ArrayList subList = (ArrayList)reportList.get(i);
                SRReportDTO srDTO = new SRReportDTO();
                srDTO.setSlotDate(subList.get(0).toString());
                srDTO.setSlotTime(subList.get(1).toString());
                srDTO.setRegTxnId(subList.get(2).toString());
                srDTO.setInitBy(subList.get(3).toString());
                srDTO.setDeedtypeName(subList.get(4).toString());
                srDTO.setExemptionName(subList.get(5).toString());
                srDTO.setExemptionOn(subList.get(6).toString());
                srDTO.setInstName(subList.get(7).toString());
                srDTO.setDeedDraftId(subList.get(8).toString());
                srDTO.setDeedDocPath(subList.get(9).toString());
                list.add(srDTO);
            }

        }
        catch(Exception exception) { }
        return list;
    }

    public ArrayList getPartyDetails(String regTxnId)
        throws Exception
    {
        SlotReportDAO dao = new SlotReportDAO();
        ArrayList list1 = new ArrayList();
        ArrayList reportList2 = dao.getPartyDetails(regTxnId);
        try
        {
            for(int i = 0; i < reportList2.size(); i++)
            {
                ArrayList subList = (ArrayList)reportList2.get(i);
                SRReportDTO srDTO = new SRReportDTO();
                srDTO.setPartyFirstname(subList.get(0).toString());
                srDTO.setDutyId(subList.get(1).toString());
                srDTO.setPropId(subList.get(2).toString());
                srDTO.setRegTxnId(subList.get(3).toString());
                srDTO.setPartyId(subList.get(4).toString());
                srDTO.setPrtyTypeId(subList.get(5).toString());
                srDTO.setInstrId(subList.get(6).toString());
                list1.add(srDTO);
            }

        }
        catch(Exception exception) { }
        return list1;
    }

    public ArrayList getDuties(String dutyID)
        throws Exception
    {
        SlotReportDAO dao = new SlotReportDAO();
        ArrayList list1 = new ArrayList();
        ArrayList reportList2 = dao.getDuties(dutyID);
        try
        {
            for(int i = 0; i < reportList2.size(); i++)
            {
                ArrayList subList = (ArrayList)reportList2.get(i);
                SRReportDTO srDTO = new SRReportDTO();
                srDTO.setStampDuty(subList.get(0).toString());
                srDTO.setGramDuty(subList.get(1).toString());
                srDTO.setNagarDuty(subList.get(2).toString());
                srDTO.setUpkarDuty(subList.get(3).toString());
                srDTO.setRegFee(subList.get(4).toString());
                srDTO.setTotalStamp(subList.get(5).toString());
                srDTO.setDutyalrdPaid(subList.get(6).toString());
                srDTO.setRegalreadyPaid(subList.get(7).toString());
                srDTO.setRegFeeExemp(subList.get(8).toString());
                srDTO.setTotalaftrExem(subList.get(9).toString());
                srDTO.setExemDuty(subList.get(10).toString());
                srDTO.setExemFee(subList.get(11).toString());
                list1.add(srDTO);
            }

        }
        catch(Exception exception) { }
        return list1;
    }

    public ArrayList getpartyNamedetails(String partyID)
        throws Exception
    {
        SlotReportDAO dao = new SlotReportDAO();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList reportList2 = dao.getpartyNamedetails(partyID);
        try
        {
            for(int i = 0; i < reportList2.size(); i++)
            {
                ArrayList subList = (ArrayList)reportList2.get(i);
                SRReportDTO srDTO = new SRReportDTO();
                srDTO.setPartyType(subList.get(0).toString());
                srDTO.setPartyName(subList.get(1).toString());
                srDTO.setPartyOccupation(subList.get(2).toString());
                srDTO.setDistrict(subList.get(3).toString());
                srDTO.setPartyAddress(subList.get(4).toString());
                srDTO.setMobileNo(subList.get(5).toString());
                srDTO.setGuardianName(subList.get(6).toString());
                srDTO.setPhotoPath(subList.get(7).toString());
                srDTO.setAffdvitPath(subList.get(8).toString());
                srDTO.setClaimantPath(subList.get(9).toString());
                srDTO.setPanPath(subList.get(10).toString());
                list1.add(srDTO);
            }

        }
        catch(Exception exception) { }
        return list1;
    }

    public ArrayList getconsentorDetails(String regTxnId)
        throws Exception
    {
        SlotReportDAO dao = new SlotReportDAO();
        ArrayList list2 = new ArrayList();
        ArrayList reportList3 = dao.getconsentorDetails(regTxnId);
        try
        {
            for(int i = 0; i < reportList3.size(); i++)
            {
                ArrayList subList = (ArrayList)reportList3.get(i);
                SRReportDTO srDTO = new SRReportDTO();
                srDTO.setConsentorTxnnum(subList.get(0).toString());
                srDTO.setConsentorFatherName(subList.get(1).toString());
                srDTO.setConsentorName(subList.get(2).toString());
                srDTO.setRegInit(subList.get(3).toString());
                srDTO.setConsentorFlag(subList.get(4).toString());
                srDTO.setConsentorRegfee(subList.get(5).toString());
                srDTO.setConsentorStampduty(subList.get(6).toString());
                srDTO.setConsentorexemRegfee(subList.get(7).toString());
                srDTO.setConsentorexemStampduty(subList.get(8).toString());
                list2.add(srDTO);
            }

        }
        catch(Exception exception) { }
        return list2;
    }
    public ArrayList<String> getSlotDetail(String regTxnId) throws Exception{
    	ArrayList<String> response =  new ArrayList<String>();
    	SlotBookBD sbd = new SlotBookBD();
    	response=sbd.getSlotDetail(regTxnId);
    	return response;
    }
    
    public String getIfPresent(String regTxnId) throws Exception{
    	String response="1";
    	SlotBookBD sbd = new SlotBookBD();
    	response = sbd.getIfPresent(regTxnId);
    	return response;
    }
    public String getWillDeedCheck(String regTxnId) throws Exception{
    	String response="0";
    	SlotBookBD sbd = new SlotBookBD();
    	response = sbd.getWillDeedCheck(regTxnId);
    	return response;
    }
    public String getIfRescheduled(String regTxnId) throws Exception{
    	String response="1";
    	SlotBookBD sbd = new SlotBookBD();
    	response = sbd.getIfRescheduled(regTxnId);
    	return response;
    }
}
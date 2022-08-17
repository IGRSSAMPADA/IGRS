package com.wipro.igrs.auditinspection.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dao.AddComplianceDAO;
import com.wipro.igrs.auditinspection.dao.SROReportDetailsDAO;
import com.wipro.igrs.auditinspection.dto.DROEmpMapDTO;
import com.wipro.igrs.auditinspection.dto.DROReasonMapDTO;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.dto.SROCheckMapDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SRODocMapDTO;
import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.SROPendingItemsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;

public class AddComplianceBD {
	private Logger logger = (Logger) Logger.getLogger(AddComplianceBD.class);
	
	AddComplianceDAO dao=new AddComplianceDAO();
	
	public String getRole(String officeid)
	{
		return dao.getRole(officeid);
	}
	public ArrayList getSrDashboard(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getSrDashboard(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getDroDashboardDR(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getDroDashboardDR(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getSroDashboardDR(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getSroDashboardDR(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getSroDashboardDIG(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getSroDashboardDIG(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getDroDashboardDIG(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getDroDashboardDIG(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getSroDashboardIG(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getSroDashboardIG(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getDroDashboardIG(String officeId,String language)
	{
		ArrayList retList=new ArrayList();
		ArrayList list=dao.getDroDashboardIG(officeId);
		if(list!=null&&list.size()>0)
		{
			for(int i=0;i<list.size();i++)
			{
			ArrayList rowList=(ArrayList) list.get(i);
			PublicDTO pdto= new PublicDTO();
			pdto.setTxnId((String)rowList.get(0));
			pdto.setCreatedDate((String)rowList.get(1));
			if("en".equalsIgnoreCase(language))
			{	
			pdto.setDistrict((String)rowList.get(2));
			}
			else
			{
			pdto.setDistrict((String)rowList.get(3));
			}
			retList.add(pdto);
			
			}
		}
		return retList;
	}
	public ArrayList getCheckMapDetails(SROReportDetailsDTO sroReportDto,String language) {
		ArrayList listChkMapItems = new ArrayList();
		ArrayList listChkMapDto = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroReportDto.getInspectionId();
		// System.out.println("BD VALUE " + sroReportDto.getInspectionId());
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList listAllChkItems = dao.getCheckListItems(language);
			ArrayList listSelectedChkItems = dao.getSelectedChkMap(arrReportId,language);
			// System.out.println("CheckList to Inspection Id in BD"
			// + listSelectedChkItems);
			for (int i = 0; i < listAllChkItems.size(); i++) {
				SROCheckMapDTO sroChkItemDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listAllChkItems.get(i);
				sroChkItemDto.setItemName((String) rowList.get(0));
				boolean flag = false;
				for (int j = 0; j < listSelectedChkItems.size(); j++) {
					ArrayList rowListSelected = (ArrayList) listSelectedChkItems
							.get(j);
					if (((String) rowList.get(0))
							.equalsIgnoreCase(((String) rowListSelected.get(0)))) {
						// System.out.println("How many times yes "+j);
						if("en".equalsIgnoreCase(language))
						{	
						sroChkItemDto.setChkVal("Yes");
						}
						else if("hi".equalsIgnoreCase(language))
						{
							sroChkItemDto.setChkVal("हां");
						}
						flag = true;
						break;
						// flag = true;

					}

				}
				if (flag == false) {
					if("en".equalsIgnoreCase(language))
					{	
					sroChkItemDto.setChkVal("No");
					}
					else if("hi".equalsIgnoreCase(language))
					{
						sroChkItemDto.setChkVal("नहीं");
					}
				}
				listChkMapItems.add(sroChkItemDto);
				// System.out.println("In BD DTO Values itemname "
				// + sroChkItemDto.getItemName());
				// System.out.println("In BD DTO Values chkval "
				// + sroChkItemDto.getChkVal());
				// listChkMapItems.add(sroChkItemDto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.out.println("List size " + listChkMapItems.size());
		return listChkMapItems;
	}
	public ArrayList getCheckMapOtherDetails(SROReportDetailsDTO sroReportDto,String language) {
		ArrayList listChkMapItems = new ArrayList();
		ArrayList listChkMapDto = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroReportDto.getInspectionId();
		// System.out.println("Before sending to DAO "
		// + sroReportDto.getInspectionId());
		// ArrayList listAllChkItems = getChkListItems();
		try {
				ArrayList listAllChkItems = dao.getOtherCheckListItems(language);
				ArrayList listSelectedChkItems = dao
					.getSelectedChkMapOthers(arrReportId,language);
			for (int i = 0; i < listAllChkItems.size(); i++) {
				SROCheckMapDTO sroChkItemDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listAllChkItems.get(i);
				sroChkItemDto.setItemName((String) rowList.get(0));

				boolean flag = false;
				for (int j = 0; j < listSelectedChkItems.size(); j++) {
					ArrayList rowListSelected = (ArrayList) listSelectedChkItems
							.get(j);
					if (((String) rowList.get(0))
							.equalsIgnoreCase(((String) rowListSelected.get(0)))) {
						sroChkItemDto.setItemRemarks((String) rowListSelected
								.get(1));
						if("en".equalsIgnoreCase(language))
						{	
						sroChkItemDto.setChkVal("Yes");
						}
						else if("hi".equalsIgnoreCase(language))
						{
							sroChkItemDto.setChkVal("हां");
						}

						flag = true;
						break;

					}

				}
				if (flag == false) {
					if("en".equalsIgnoreCase(language))
					{	
					sroChkItemDto.setChkVal("No");
					}
					else if("hi".equalsIgnoreCase(language))
					{
						sroChkItemDto.setChkVal("नहीं");
					}
				}
				listChkMapItems.add(sroChkItemDto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listChkMapItems;
	}
	public ArrayList getPendingItems(SROReportDetailsDTO sroReportDto) {
		ArrayList listPendingItemDto = new ArrayList();
		ArrayList listPndingItemsDB = new ArrayList();
		String inspId[] = new String[1];
		inspId[0] = sroReportDto.getInspectionId();
		String reportId[] = new String[4];
		reportId[0] = sroReportDto.getInspectionStartDate();
		reportId[1] = sroReportDto.getInspectionEndDate();
		reportId[2] = sroReportDto.getSroName();
		reportId[3] = sroReportDto.getInspectionId();
		listPndingItemsDB = dao.getPendingItems(inspId);
		for (int i = 0; i < listPndingItemsDB.size(); i++) {
			ArrayList rowList = (ArrayList) listPndingItemsDB.get(i);
			SROPendingItemsDTO sroPendingDto = new SROPendingItemsDTO();
			sroPendingDto.setPendingItem((String) rowList.get(0));
			sroPendingDto.setPendingFrom(((String) rowList.get(1)));
			sroPendingDto.setPendingTaskNo(((String) rowList.get(2)));
			listPendingItemDto.add(sroPendingDto);
		}

		return listPendingItemDto;
	}
	public ArrayList getEmpDetailsList(SROReportDetailsDTO sroDto) {
		ArrayList listEmpDto = new ArrayList();
		ArrayList listFromDao = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroDto.getInspectionId();
		
		listFromDao = dao.getEmpDtoList(arrReportId);
		for (int i = 0; i < listFromDao.size(); i++) {
			ArrayList rowList = (ArrayList) listFromDao.get(0);
			SROEmpDTO newEmpDto = new SROEmpDTO();
			newEmpDto.setEmployeename((String) rowList.get(0));
			newEmpDto.setDesignation((String) rowList.get(1));
			newEmpDto.setJoinDate((String) rowList.get(2));
			newEmpDto.setSeperarionDate((String) rowList.get(3));
			newEmpDto.setUserId((String) rowList.get(4));
			listEmpDto.add(newEmpDto);
		}

		return listEmpDto;
	}
	public ArrayList getSroComments(SROReportDetailsDTO sroReportDetailDto) {
		ArrayList newSroCommList = new ArrayList();
		ArrayList listFromDao = new ArrayList();
		String[] arr = new String[1];
		arr[0] = sroReportDetailDto.getInspectionId();
		
		listFromDao = dao.getSroComments(arr);
		for (int i = 0; i < listFromDao.size(); i++) {
			ArrayList rowList = (ArrayList) listFromDao.get(i);
			SROCommentsDTO sroComm = new SROCommentsDTO();
			sroComm.setSroComments((String) rowList.get(0));
			newSroCommList.add(sroComm);
		}

		return newSroCommList;
	}

	public ArrayList getDocNames(SROReportDetailsDTO sroReportDetailDto) {
		ArrayList newDocList = new ArrayList();
		ArrayList listFromDao = new ArrayList();
		String[] arr = new String[1];
		arr[0] = sroReportDetailDto.getInspectionId();
	
		listFromDao = dao.getDocumentNames(arr);
		if (listFromDao.size() > 0) {
			for (int i = 0; i < listFromDao.size(); i++) {
				ArrayList rowList = (ArrayList) listFromDao.get(i);
				SRODocMapDTO sroDocDto = new SRODocMapDTO();
				sroDocDto.setDocument_Name((String) rowList.get(0));
				sroDocDto.setFilePath((String) rowList.get(1)+(String) rowList.get(0));
				newDocList.add(sroDocDto);
			}
		}

		return newDocList;
	}
	public SROReportDetailsDTO getSroReportDetails(
			SROReportDetailsDTO sroReportDto,String langauge) {
		SROReportDetailsDTO newSroDetailsDto = new SROReportDetailsDTO();
		String reportId[] = new String[1];
		reportId[0] = sroReportDto.getInspectionId();
		ArrayList listFromDb = dao.getSroReportDetails(reportId,langauge);
		ArrayList rowList = (ArrayList) listFromDb.get(0);
		newSroDetailsDto.setInspectionId((String) rowList.get(0));
		newSroDetailsDto.setInspectionStartDate((String) rowList.get(1));
		newSroDetailsDto.setInspectionEndDate((String) rowList.get(2));
		newSroDetailsDto.setLfyAnnualTargets((String) rowList.get(3));
		newSroDetailsDto.setLfyAnnualReceipts((String) rowList.get(4));
		newSroDetailsDto.setLfyPctComp((String) rowList.get(5));
		newSroDetailsDto.setLfyPctIncr((String) rowList.get(6));
		newSroDetailsDto.setCfyAnnualIncome((String) rowList.get(7));
		newSroDetailsDto.setCfyAnnualReceipt((String) rowList.get(8));
		newSroDetailsDto.setLfyIncome((String) rowList.get(9));
		newSroDetailsDto.setIncomReasonCfy((String) rowList.get(10));
		newSroDetailsDto.setIncomeReasonLfy((String) rowList.get(11));
		if("en".equalsIgnoreCase(langauge))
		{	
		newSroDetailsDto.setCashDepositBank(rowList.get(12).toString().equalsIgnoreCase("N")?"NO":"YES");
		}
		else
		{
			newSroDetailsDto.setCashDepositBank(rowList.get(12).toString().equalsIgnoreCase("N")?"नहीं":"हां");
		}
		newSroDetailsDto.setNoRtiRequestCFY((String) rowList.get(13));
		newSroDetailsDto.setNoRtiDiscloreCFY((String) rowList.get(14));
		newSroDetailsDto.setReason((String) rowList.get(15));
		newSroDetailsDto.setInspectionStatus((String) rowList.get(16));
		newSroDetailsDto.setInspectionAuthorityName((String) rowList.get(17));
		newSroDetailsDto.setSrName((String) rowList.get(18));
		newSroDetailsDto.setCfyRevenueFromDate((String) rowList.get(19));
		newSroDetailsDto.setCfyRevenueToDate((String) rowList.get(20));
		newSroDetailsDto.setCfyRevenueColFromDate((String) rowList.get(21));
		newSroDetailsDto.setCfyRevenueColToDate((String) rowList.get(22));
		newSroDetailsDto.setPfyRevenueFromDate((String) rowList.get(23));
		newSroDetailsDto.setPfyRevenueToDate((String) rowList.get(24));
		newSroDetailsDto.setInspDueDate((String) rowList.get(25));
		newSroDetailsDto.setObjectedEntryDate((String) rowList.get(26));
		newSroDetailsDto.setPastInspDetails((String) rowList.get(27));
		newSroDetailsDto.setInspectionEntryDate((String) rowList.get(28));
		newSroDetailsDto.setInspectionDate((String) rowList.get(29));
		newSroDetailsDto.setSroName((String) rowList.get(30));
		newSroDetailsDto.setDistrictName((String) rowList.get(31));
		newSroDetailsDto.setTenure((String) rowList.get(32));
		newSroDetailsDto.setComplianceFlag((String) rowList.get(33));
		newSroDetailsDto.setSrCompliance((String) rowList.get(34));
		newSroDetailsDto.setDrCompliance((String) rowList.get(35));
		newSroDetailsDto.setDigCompliance((String) rowList.get(36));
		newSroDetailsDto.setIgCompliance((String) rowList.get(37));
		return newSroDetailsDto;
	}
	public DROReportDetailsDTO getDroReportDetails(String reportId,String langauge){
		String[] droDetails = new String[1];
		droDetails[0]  = reportId;
		DROReportDetailsDTO droReportDto = new DROReportDetailsDTO();
		ArrayList listDroDto = dao.getDROInspDetails(droDetails,langauge);
		if(listDroDto!=null && listDroDto.size()>=1){
		ArrayList rowList = (ArrayList)listDroDto.get(0);
		
		droReportDto.setDroName((String)rowList.get(0));
		droReportDto.setDistrictName((String)rowList.get(1));
		droReportDto.setAuthorityName((String)rowList.get(2));
		droReportDto.setDroInspId((String)rowList.get(3));
		droReportDto.setInspStartDate(getStrDate((String)rowList.get(4)));
		droReportDto.setInspToDate(getStrDate((String)rowList.get(5)));
		droReportDto.setInspDate(getStrDate((String)rowList.get(6)));
		droReportDto.setSrName((String)rowList.get(7));
		droReportDto.setAnnualTargetPfy((String)rowList.get(8));
		droReportDto.setRevenueCollectionPfy((String)rowList.get(9));
		droReportDto.setTargetCompPfy((String)rowList.get(10));
		droReportDto.setIncrDcrPfy((String)rowList.get(11));
		droReportDto.setCfyRevenueTarget((String)rowList.get(12));
		droReportDto.setCfyRevenueColl((String)rowList.get(13));
		droReportDto.setRevenueTargetPfy((String)rowList.get(14));
		droReportDto.setInspStatus((String)rowList.get(15));
		droReportDto.setInspEntryDate((String)rowList.get(16));
		droReportDto.setInspDueDate((String)rowList.get(17));
		droReportDto.setTenure((String)rowList.get(18));
		droReportDto.setRevenuefrommonth((String)rowList.get(19));
		droReportDto.setRevenuetomonth((String)rowList.get(20));
		droReportDto.setFrommonth((String)rowList.get(21));
		droReportDto.setTomonth((String)rowList.get(22));
		droReportDto.setPfyfrommonth((String)rowList.get(23));
		droReportDto.setPfytomonth((String)rowList.get(24));
		droReportDto.setIgComment((String)rowList.get(25));
		droReportDto.setPastdetails((String)rowList.get(26));
		droReportDto.setObjEntryDate((String)rowList.get(27));
		droReportDto.setStampDetails((String)rowList.get(28));
		droReportDto.setPendingDetails((String)rowList.get(29));
		droReportDto.setRrcDetails((String)rowList.get(30));
		droReportDto.setDeDetails((String)rowList.get(31));
		droReportDto.setPensionDetails((String)rowList.get(32));
		droReportDto.setComplianceFlag((String)rowList.get(33));
		droReportDto.setDrCompliance((String)rowList.get(34));
		droReportDto.setDigCompliance((String)rowList.get(35));
		droReportDto.setIgCompliance((String)rowList.get(36));
		}
		return droReportDto;
		
	}
	private String getStrDate(String strDate) {
		String sdf = null;
		try {

			java.util.Date date = new java.text.SimpleDateFormat(
			"yyyy-MM-dd hh:mm:ss").parse(strDate);
			sdf = new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sdf;
	}
	public ArrayList getDROEmpDetails(String inspId)
	{
		String[] insp = new String[1];
		insp[0] = inspId;
		ArrayList listFromDB  = null;
		listFromDB  =  dao.getDROEmpDetails(insp);
		ArrayList listEmpDto  = new ArrayList();
		for(int i=0;i<listFromDB.size();i++){
			ArrayList rowList  = (ArrayList)listFromDB.get(i);
			DROEmpMapDTO empDto = new DROEmpMapDTO();
			empDto.setEmpId((String)rowList.get(0));
			empDto.setEmpName((String)rowList.get(1));
			empDto.setDesignation((String)rowList.get(2));
			empDto.setJoiningDate(getStrDate((String)rowList.get(3)));
			empDto.setSeperationDate(getStrDate((String)rowList.get(4)));
			listEmpDto.add(empDto);
		}
		
		
		return listEmpDto;
	}
	public ArrayList getDROReasonDetails(String inspid)
	{
		ArrayList listDB = null;
		String[] insp = new String[1];
		insp[0] = inspid;
		ArrayList listReasonMap = new ArrayList();
		listDB = dao.getReasonMapDetails(insp);
		for(int i=0;i<listDB.size();i++){
			ArrayList rowList = (ArrayList)listDB.get(i);
			DROReasonMapDTO reasonMap = new DROReasonMapDTO();
			reasonMap.setReason((String)rowList.get(0));
			listReasonMap.add(reasonMap);
		}
		return listReasonMap;
	}
	public ArrayList getDocumentNames(String inspId){
		ArrayList listDB = null;
		ArrayList listDocNames = new ArrayList();
		String[] insp = new String[1];
		insp[0]  =  inspId;
		//insp[1]  =  "PEN";
		listDB  =  dao.getDocNames(insp);
		if(listDB!=null){
		for(int i=0;i<listDB.size();i++){
			ArrayList rowList = (ArrayList)listDB.get(i);
			UploadFileDTO uploadDto = new UploadFileDTO();
			uploadDto.setFileName((String)rowList.get(0));
			uploadDto.setFilePath((String)rowList.get(1)+(String)rowList.get(0));
			listDocNames.add(uploadDto);
		}
		}
		return listDocNames;
	}

public boolean updateSROComliance(PublicDTO pdto)
{
	return dao.updateSROCompliance(pdto);
}
public boolean updateDROComliance(PublicDTO pdto)
{
	return dao.updateDROCompliance(pdto);
}
public ArrayList getSrDashboardSearch(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getSrDashboardSearch(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getSroDashboardSearchDR(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getSroDashboardSearchDR(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getDroDashboardSearchDR(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getDroDashboardSearchDR(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getSroDashboardSearchDIG(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getSroDashboardSearchDIG(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getDroDashboardSearchDIG(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getDroDashboardSearchDIG(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getDroDashboardSearchIG(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getDroDashboardSearchIG(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
public ArrayList getSroDashboardSearchIG(String officeId,PublicDTO pdto1,String language)
{
	ArrayList retList=new ArrayList();
	ArrayList list=dao.getSroDashboardSearchIG(officeId,pdto1);
	if(list!=null&&list.size()>0)
	{
		for(int i=0;i<list.size();i++)
		{
		ArrayList rowList=(ArrayList) list.get(i);
		PublicDTO pdto= new PublicDTO();
		pdto.setTxnId((String)rowList.get(0));
		pdto.setCreatedDate((String)rowList.get(1));
		if("en".equalsIgnoreCase(language))
		{	
		pdto.setDistrict((String)rowList.get(2));
		}
		else
		{
		pdto.setDistrict((String)rowList.get(3));
		}
		retList.add(pdto);
		
		}
	}
	return retList;
}
}
	


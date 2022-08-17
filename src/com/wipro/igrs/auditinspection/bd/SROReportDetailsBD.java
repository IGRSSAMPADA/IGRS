/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.auditinspection.bd;

import java.util.ArrayList;

import com.wipro.igrs.auditinspection.dao.SROReportDetailsDAO;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.SROCheckMapDTO;
import com.wipro.igrs.auditinspection.dto.SROCommentsDTO;
import com.wipro.igrs.auditinspection.dto.SRODocMapDTO;
import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.SROPendingItemsDTO;
import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.CommonUtil;

public class SROReportDetailsBD {

	/**
	 * @param offTypeId
	 * @return
	 * @throws Exception
	 */
	public ArrayList getSroName(String userId) {

		ArrayList sroList1 = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList sroList = sroDetailsDao.getSRO(userId);
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sroList1;
	}
	//ADDED BY SHRUTI---18TH OCT 2013
	public ArrayList getJrsdctnSroName(String OffId,String language) {

		ArrayList sroList1 = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList sroList = sroDetailsDao.getJursdctnSRO(OffId,language);
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sroList1;
	}
	//END

	public ArrayList getDistrict(String office,String language) {

		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		ArrayList countlist = sroDetailsDao.getDistrict(office,language);

		ArrayList listDistrictNames = new ArrayList();
		if (countlist != null) {
			for (int i = 0; i < countlist.size(); i++) {
				ArrayList cnttlist = (ArrayList) countlist.get(i);
				if (cnttlist != null) {
					SROReportDetailsDTO sroDto = new SROReportDetailsDTO();
					sroDto.setDistrictId((String) cnttlist.get(0));
					sroDto.setDistrictName((String) cnttlist.get(1));
					listDistrictNames.add(sroDto);
				}
			}
		}
		return listDistrictNames;
	}

	/**
	 * @return
	 */
	public ArrayList getChkListItems(String language) {
		ArrayList listCheckItems = new ArrayList();
		ArrayList listCheckItemsDb = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();

			listCheckItemsDb = sroDetailsDao.getCheckListItems(language);
			for (int i = 0; i < listCheckItemsDb.size(); i++) {
				SROCheckMapDTO checkDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listCheckItemsDb.get(i);
				checkDto.setItemName((String) rowList.get(0));
				checkDto.setItemId((String) rowList.get(1));
				listCheckItems.add(checkDto);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listCheckItems;
	}
	
	//added by shruti---29 april 2014
	public ArrayList getChkListNames(String[] checklistids,String language) {
		ArrayList listCheckItems = new ArrayList();
		ArrayList listCheckItemsDb = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();

			listCheckItemsDb = sroDetailsDao.getCheckListNames(checklistids,language);
			for (int i = 0; i < listCheckItemsDb.size(); i++) {
				SROCheckMapDTO checkDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listCheckItemsDb.get(i);
				//checkDto.setItemName((String) rowList.get(0));
				checkDto.setItemId((String) rowList.get(0));
				listCheckItems.add(checkDto);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listCheckItems;
	}
	//end
	//added by shruti---15 april 2014
	public ArrayList getUpdatedChkListItems(String[] itemnames,String language) {
		ArrayList listCheckItems = new ArrayList();
		ArrayList listCheckItemsDb = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();

			listCheckItemsDb = sroDetailsDao.getUpdatedCheckListItems(itemnames,language);
			for (int i = 0; i < listCheckItemsDb.size(); i++) {
				SROCheckMapDTO checkDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listCheckItemsDb.get(i);
				checkDto.setItemName((String) rowList.get(0));
				checkDto.setItemId((String) rowList.get(1));
				listCheckItems.add(checkDto);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listCheckItems;
	}
	//end

	/**
	 * @return
	 */
	public ArrayList getChkDetailsOthers(String language) {
		ArrayList listCheckItems = new ArrayList();
		ArrayList listCheckItemsDb = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();

			listCheckItemsDb = sroDetailsDao.getOtherCheckListItems(language);
			for (int i = 0; i < listCheckItemsDb.size(); i++) {
				SROCheckMapDTO checkDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listCheckItemsDb.get(i);
				checkDto.setItemName((String) rowList.get(0));
				checkDto.setItemId((String) rowList.get(1));
				listCheckItems.add(checkDto);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listCheckItems;
	}

	// method for searching for CheckMapItems by inspection id
	/**
	 * @param sroReportDto
	 * @return
	 */
	public ArrayList getCheckMapDetails(SROReportDetailsDTO sroReportDto,String language) {
		ArrayList listChkMapItems = new ArrayList();
		ArrayList listChkMapDto = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroReportDto.getInspectionId();
		// System.out.println("BD VALUE " + sroReportDto.getInspectionId());
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList listAllChkItems = sroDetailsDao.getCheckListItems(language);
			ArrayList listSelectedChkItems = sroDetailsDao
					.getSelectedChkMap(arrReportId,language);
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

	// method for searching for CheckMapItemsOthers by inspection id
	/**
	 * @param sroReportDto
	 * @return
	 */
	public ArrayList getCheckMapOtherDetails(SROReportDetailsDTO sroReportDto,String language) {
		ArrayList listChkMapItems = new ArrayList();
		ArrayList listChkMapDto = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroReportDto.getInspectionId();
		// System.out.println("Before sending to DAO "
		// + sroReportDto.getInspectionId());
		// ArrayList listAllChkItems = getChkListItems();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList listAllChkItems = sroDetailsDao.getOtherCheckListItems(language);
			ArrayList listSelectedChkItems = sroDetailsDao.getSelectedChkMapOthers(arrReportId,language);
			for (int i = 0; i < listAllChkItems.size(); i++) {
				SROCheckMapDTO sroChkItemDto = new SROCheckMapDTO();
				ArrayList rowList = (ArrayList) listAllChkItems.get(i);
				sroChkItemDto.setItemName((String) rowList.get(0));

				boolean flag = false;
				for (int j = 0; j < listSelectedChkItems.size(); j++) {
					ArrayList rowListSelected = (ArrayList) listSelectedChkItems
							.get(j);
					// System.out.println("list all Bd"+(String)
					// rowList.get(0)+"list selected"+(String)
					// rowListSelected.get(0));
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

				// System.out.println("In BD DTO Values itemname "
				// + sroChkItemDto.getItemName());
				// System.out.println("In BD DTO Values chkval "
				// + sroChkItemDto.getChkVal());
				// System.out.println("In BD DTO Values itemRemarks "
				// + sroChkItemDto.getItemRemarks());
				listChkMapItems.add(sroChkItemDto);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return listChkMapItems;
	}

	/**
	 * @param sroReportDto
	 * @return
	 */
	public ArrayList getInspectionIds(SROReportDetailsDTO sroReportDto) {
		String reportId[] = new String[5];
		String strStartDate = null;
		String strEndDate = null;
		String strSROName = null;
		String strInspctionId = null;
		String strInspStatus=null;
		
	//modified by shruti-field removed from front end
		sroReportDto.setInspectionStatus("OPEN");
//end
		if(sroReportDto.getInspectionStatus()!=null && !sroReportDto.getInspectionStatus().equalsIgnoreCase("") )
		{
			strInspStatus=sroReportDto.getInspectionStatus();
		}
		if(sroReportDto.getInspectionId()!=null && !sroReportDto.getInspectionId().equalsIgnoreCase("")) {
			strInspctionId = sroReportDto.getInspectionId();
		}
		if (sroReportDto.getInspectionStartDate() != null && sroReportDto.getInspectionEndDate() != null ) {
			if( !sroReportDto.getInspectionStartDate().equalsIgnoreCase("") && !sroReportDto.getInspectionEndDate().equalsIgnoreCase(""))
			{
				strStartDate = CommonUtil.getConvertedDate(sroReportDto
					.getInspectionStartDate());
				strEndDate = CommonUtil.getConvertedDate(sroReportDto
					.getInspectionEndDate());
				
			}
		} 
		if(sroReportDto.getSroName()!=null &&!sroReportDto.getSroName().equalsIgnoreCase(""))
		{
			strSROName = sroReportDto.getSroName();
		}
		
			// System.out.println("Please print this before setting the value
		// "+strStartDate+" strEndDate "+" strSROName "+strSROName+"
		// strInspctionId "+strInspctionId);

		/*
		 * if(!strInspctionId.equals("")){ strStartDate = "NULL"; strEndDate =
		 * "NULL"; strSROName = "NULL"; }
		 */
		/*
		 * if (strStartDate.equals("")) { System.out.println("Comming inside
		 * null values set teh value null"); strStartDate = "NULL"; }
		 * if(strEndDate.equals("")){ strEndDate = "NULL"; }
		 * if(strSROName.equals("")){ strSROName = "NULL"; }
		 * if(strInspctionId.equals("")){ strInspctionId = "NULL"; }
		 */

		reportId[0] = strStartDate;
		reportId[1] = strEndDate;
		reportId[2] = strSROName;
		reportId[3] = strInspctionId;
		reportId[4] = strInspStatus;
		// System.out.println("Printing @@@@@ " + sroReportDto.getSroName());

		ArrayList listIds = new ArrayList();
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		ArrayList listSroDto = sroDetailsDao.getInspectionIds(reportId);
		SROReportDetailsDTO sroDto = null;
		for (int i = 0; i < listSroDto.size(); i++) {
			sroDto = new SROReportDetailsDTO();
			ArrayList rowList = (ArrayList) listSroDto.get(i);
			sroDto.setInspectionId((String) rowList.get(0));
			sroDto.setInspectionStartDate((String) rowList.get(1));
			sroDto.setInspectionEndDate((String) rowList.get(2));
			sroDto.setInspectionStatus((String) rowList.get(3));
			listIds.add(sroDto);
		}
		// System.out.println("GIVE MET HTE SIZE " + listIds.size());
		return listIds;
	}

	/**
	 * @param sroReportDto
	 * @return
	 */
	public SROReportDetailsDTO getSroReportDetails(
			SROReportDetailsDTO sroReportDto,String language) {
		SROReportDetailsDTO newSroDetailsDto = new SROReportDetailsDTO();
		String reportId[] = new String[1];
		reportId[0] = sroReportDto.getInspectionId();
		/*
		 * String reportId[] = new String[4]; reportId[0] =
		 * sroReportDto.getInspectionStartDate(); reportId[1] =
		 * sroReportDto.getInspectionEndDate(); reportId[2] =
		 * sroReportDto.getSroName(); reportId[3] =
		 * sroReportDto.getInspectionId();
		 */
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		ArrayList listFromDb = sroDetailsDao.getSroReportDetails(reportId,language);
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
		if("en".equalsIgnoreCase(language))
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
		if(rowList.get(33)!=null&&!rowList.get(33).toString().equalsIgnoreCase(""))
		{
			newSroDetailsDto.setSrCompliance((String) rowList.get(33));
		}
		else
		{
			newSroDetailsDto.setSrCompliance("");
		}
		if(rowList.get(34)!=null&&!rowList.get(34).toString().equalsIgnoreCase(""))
		{
			newSroDetailsDto.setDrCompliance((String) rowList.get(34));
		}
		else
		{
			newSroDetailsDto.setDrCompliance("");
		}
		if(rowList.get(35)!=null&&!rowList.get(35).toString().equalsIgnoreCase(""))
		{
			newSroDetailsDto.setDigCompliance((String) rowList.get(35));
		}
		else
		{
			newSroDetailsDto.setDigCompliance("");
		}
		if(rowList.get(36)!=null&&!rowList.get(36).toString().equalsIgnoreCase(""))
		{
			newSroDetailsDto.setIgCompliance((String) rowList.get(36));
		}
		else
		{
			newSroDetailsDto.setIgCompliance("");
		}
		return newSroDetailsDto;
	}

	/**
	 * @param sroReportDto
	 * @return
	 */
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
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		listPndingItemsDB = sroDetailsDao.getPendingItems(inspId);
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

	/**
	 * @param userId
	 * @return
	 */
	public SROEmpDTO getEmpDetails(String userId) {
		ArrayList listEmpDetails = new ArrayList();
		SROEmpDTO sroEmpDto = null;
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		listEmpDetails = sroDetailsDao.getEmpDetail(userId);
		//System.out.println("Emplist values in BD " + listEmpDetails);
		if (listEmpDetails.size() > 0) {
			sroEmpDto = new SROEmpDTO();
			ArrayList rowList = (ArrayList) listEmpDetails.get(0);
			sroEmpDto.setEmployeename((String) rowList.get(0));
			sroEmpDto.setDesignation((String) rowList.get(1));
			sroEmpDto.setJoinDate((String) rowList.get(2));
			sroEmpDto.setSeperarionDate((String) rowList.get(3));
			// sroEmpDto.setUserId((String)rowList.get(4));
		}
		return sroEmpDto;
	}

	/**
	 * @param sroDto
	 * @return
	 */
	public ArrayList getEmpDetailsList(SROReportDetailsDTO sroDto) {
		ArrayList listEmpDto = new ArrayList();
		ArrayList listFromDao = new ArrayList();
		String arrReportId[] = new String[1];
		arrReportId[0] = sroDto.getInspectionId();
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		listFromDao = sroDetailsDao.getEmpDtoList(arrReportId);
		for (int i = 0; i < listFromDao.size(); i++) {
			ArrayList rowList = (ArrayList) listFromDao.get(0);
			SROEmpDTO newEmpDto = new SROEmpDTO();
			newEmpDto.setEmployeename((String) rowList.get(0));
			newEmpDto.setDesignation((String) rowList.get(1));
			newEmpDto.setJoinDate((String) rowList.get(2));
			newEmpDto.setSeperarionDate((String) rowList.get(3));
			//added by shruti
			newEmpDto.setUserId((String) rowList.get(4));
			listEmpDto.add(newEmpDto);
		}

		return listEmpDto;
	}

	/**
	 * @param sroCommentsDto
	 * @return
	 */
	public String addSroComments(SROCommentsDTO sroCommentsDto) {
		String inspid = sroCommentsDto.getInspectionId();
		sroCommentsDto.getInspectionId();
		SROReportDetailsDAO sroReportDao = new SROReportDetailsDAO();
		String comments[] = new String[3];
		comments[0] = sroCommentsDto.getUserId();
		comments[1] = sroCommentsDto.getSroComments();
		comments[2] = sroCommentsDto.getInspectionId();
		sroReportDao.saveSroComments(comments);

		return inspid;
	}

	/**
	 * @param sroReportDto
	 * @param listFileNames
	 * @param filePath
	 * @return
	 */
	public String saveSroInspectionDetails(SROReportDetailsDTO sroReportDto,
			ArrayList listFileNames, String filePath) {
		boolean flag = false;
		SROReportDetailsDAO sroReportDao = new SROReportDetailsDAO();

		ArrayList listEmpDetailsDto = sroReportDto.getListSROEmp();
		ArrayList listSroCommentsDto = sroReportDto.getListSROComments();
		ArrayList listPendingItemDto = sroReportDto.getListSROPendingItem();
		ArrayList listCheckMapDto = sroReportDto.getListSROCheckMap();

		// SROEmpDTO sroEmpDto = (SROEmpDTO)listEmpDetailsDto.get(0);
		SROCommentsDTO sroCommentsDto = (SROCommentsDTO) listSroCommentsDto.get(0);

		String arrReportDetails[] = new String[34];

		arrReportDetails[0] = CommonUtil.getConvertedDate(sroReportDto.getInspectionDate()); // INSPECTION_DATE
		arrReportDetails[1] = sroReportDto.getInspectedBy(); // Inspected By
		arrReportDetails[2] = sroReportDto.getSroId();// "InspOffice Inspection Office Id
		arrReportDetails[3] = CommonUtil.getConvertedDate(sroReportDto.getInspectionStartDate()); // inspection start date
		arrReportDetails[4] = CommonUtil.getConvertedDate(sroReportDto.getInspectionEndDate()); // InspectionEndDate
		arrReportDetails[5] = sroReportDto.getSroId(); // "OfficeUserId";
		arrReportDetails[6] = sroReportDto.getLfyAnnualTargets(); // LFY Annual Target
		arrReportDetails[7] = sroReportDto.getLfyAnnualReceipts(); // LFY Annual Receipt
		arrReportDetails[8] = sroReportDto.getLfyPctComp(); // Lfy Pct Compleated
		arrReportDetails[9] = sroReportDto.getPercentageincr(); // Lfy Pct Incr/Decr
		arrReportDetails[10] = sroReportDto.getCfyAnnualIncome(); // CFY Annual income
		arrReportDetails[11] = sroReportDto.getCfyAnnualReceipt(); // CFY  Annual receipts
		arrReportDetails[12] = sroReportDto.getPfyrevenuetaget(); // Lfy Income
		arrReportDetails[13] = sroReportDto.getIncomReasonCfy(); // INCOME_UNACCOM_REASON_CFY
		arrReportDetails[14] = sroReportDto.getIncomeReasonLfy(); // INCOME_UNACCOM_REASON_LFY
		arrReportDetails[15] = sroReportDto.getCashDepositBank();//CASH_DEPOSIT_BANK
		arrReportDetails[16] = sroReportDto.getNoRtiRequestCFY(); // NO_RTI_REQUEST_CFY
		arrReportDetails[17] = sroReportDto.getNoRtiDiscloreCFY(); // NO_DISPOSED_RTI_REQ_CFY
		arrReportDetails[18] = sroReportDto.getReason(); // REASON_FOR_PENDING_REQ
		arrReportDetails[19] = "Open";// sroReportDto.getInspectionStatus();
		arrReportDetails[20] = sroReportDto.getInspectedBy();// "created by";
		arrReportDetails[21] = sroReportDto.getInspectionAuthorityName();
		arrReportDetails[22] = sroReportDto.getSrName();
		arrReportDetails[23] = sroReportDto.getInspectedBy();
		arrReportDetails[25] =sroReportDto.getCfyRevenueFromDate()+"-"+sroReportDto.getCfyRevenueFromYear();
		arrReportDetails[26] =sroReportDto.getCfyRevenueToDate()+"-"+sroReportDto.getCfyRevenueToYear();
		arrReportDetails[27] =sroReportDto.getCfyRevenueColFromDate()+"-"+sroReportDto.getCfyRevenueColFromYear();		
		arrReportDetails[28] =sroReportDto.getCfyRevenueColToDate()+"-"+sroReportDto.getCfyRevenueColToYear();	
		arrReportDetails[29] =sroReportDto.getPfyRevenueFromDate()+"-"+sroReportDto.getPfyRevenueFromYear();			
		arrReportDetails[30] =sroReportDto.getPfyRevenueToDate()+"-"+sroReportDto.getPfyRevenueToYear();
		arrReportDetails[31] =CommonUtil.getConvertedDate(sroReportDto.getObjectedEntryDate());
		arrReportDetails[32] =sroReportDto.getPastInspDetails();
		arrReportDetails[33] =sroReportDto.getTenure();
		String arrSroComments[] = new String[3];
		//ADDED BY VINAY
		arrSroComments[0] = sroReportDto.getUserId();//"11223344";//sroCommentsDto.getUserId(); // USER_Id
		//
		arrSroComments[1] = sroCommentsDto.getSroComments(); // SRO Comments

		ArrayList listAllDetails = new ArrayList();

		listAllDetails.add(arrReportDetails);
		listAllDetails.add(arrSroComments);
		listAllDetails.add(listEmpDetailsDto);
		listAllDetails.add(listPendingItemDto);
		listAllDetails.add(listCheckMapDto);
		listAllDetails.add(filePath);
		listAllDetails.add(listFileNames);
		String inspectionId = sroReportDao.saveSROReportDetails(listAllDetails,sroReportDto.getLoggedInOffice());

		return inspectionId;
	}

	public ArrayList getSroComments(SROReportDetailsDTO sroReportDetailDto) {
		ArrayList newSroCommList = new ArrayList();
		ArrayList listFromDao = new ArrayList();
		String[] arr = new String[1];
		arr[0] = sroReportDetailDto.getInspectionId();
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		listFromDao = sroDetailsDao.getSroComments(arr);
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
		SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
		listFromDao = sroDetailsDao.getDocumentNames(arr);
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
	
	public String getOfficeTypeId(String officeId)
	{

		String officeTypeId = null;

		String arr[]={officeId};
		try{
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			officeTypeId=sroDetailsDao.getOfficeTypeId(officeId);
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		return officeTypeId;
	}
	
	public ArrayList getAllSroName(String language) {

		ArrayList sroList1 = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList sroList = sroDetailsDao.getAllSRO(language);
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sroList1;
	}
	public ArrayList getLoggedInSroName(String OffId,String language) {

		ArrayList sroList1 = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList sroList = sroDetailsDao.getLoggedInSRO(OffId, language);
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sroList1;
	}
	public ArrayList getSROForDIG(String OffId,String language) {

		ArrayList sroList1 = new ArrayList();
		try {
			SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
			ArrayList sroList = sroDetailsDao.getSROForDIG(language, OffId);
			if (sroList != null) {
				for (int i = 0; i < sroList.size(); i++) {
					ArrayList sroTemp = (ArrayList) sroList.get(i);
					if (sroTemp != null) {
						AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
						agmpreportdetailsdto.setSroId((String) sroTemp.get(0));
						agmpreportdetailsdto.setSroName((String) sroTemp.get(1));
						sroList1.add(agmpreportdetailsdto);
					}
				}
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sroList1;
	}
	
}
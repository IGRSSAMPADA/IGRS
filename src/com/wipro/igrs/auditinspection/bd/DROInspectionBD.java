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

import com.wipro.igrs.auditinspection.dao.DROInspectionDAO;
import com.wipro.igrs.auditinspection.dao.SROReportDetailsDAO;
import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.DROEmpMapDTO;
import com.wipro.igrs.auditinspection.dto.DROInspItemMap;
import com.wipro.igrs.auditinspection.dto.DROPendingTaskDTO;
import com.wipro.igrs.auditinspection.dto.DROReasonMapDTO;
import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.util.CommonUtil;

public class DROInspectionBD {
	
DROInspectionDAO   droReportDAO  =  new  DROInspectionDAO();

/**
 * @param userId
 * @return
 */
public DROEmpMapDTO getEmpDetails(String userId) {
	ArrayList listEmpDetails = new ArrayList();
	DROEmpMapDTO droEmpDto = null;	
	listEmpDetails = droReportDAO.getEmpDetail(userId);
	
	if(listEmpDetails.size()>0)
	{
		droEmpDto=new DROEmpMapDTO();
		ArrayList rowList = (ArrayList) listEmpDetails.get(0);
		droEmpDto.setEmpName((String) rowList.get(0));
		droEmpDto.setDesignation((String) rowList.get(1));
		droEmpDto.setJoiningDate((String) rowList.get(2));
		droEmpDto.setSeperationDate((String) rowList.get(3));
		//sroEmpDto.setUserId((String)rowList.get(4));
	}
	return droEmpDto;
}

public String getDistrictName(String sroname,String language) {
	String districtname=null;
	districtname = droReportDAO.getDistrictName(sroname,language);
	return districtname;
}







public ArrayList getSroName(String userId)  { 
		
		ArrayList sroList1 = new ArrayList();
		try {
	        //SROReportDetailsDAO sroDetailsDao = new SROReportDetailsDAO();
	        ArrayList sroList = droReportDAO.getSRO(userId);
	        
	        if (sroList != null) {
	        	for (int i = 0; i < sroList.size(); i++) {
	        		ArrayList sroTemp = (ArrayList) sroList.get(i);
	        		if (sroTemp != null) {
	        			//AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();
	        			DROReportDetailsDTO  droInspDto  =  new DROReportDetailsDTO();
	        			droInspDto.setSroId((String) sroTemp.get(0));
	        			//System.out.println("In BD----"
	        				//	+ agmpreportdetailsdto.getSroId());
	        			droInspDto.setSroName((String) sroTemp.get(1));
	        			sroList1.add(droInspDto);
	        		}
	        	}
	        }
        } catch (RuntimeException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return sroList1;
	}

public ArrayList getDRONames(String language,String officeId){
	
	ArrayList droList     =  droReportDAO.getDroNames(language,officeId);
	ArrayList droNewList  =  new ArrayList();
	if(droList!=null){
	for(int i=0;i<droList.size();i++)
	{
		ArrayList row_list   =  (ArrayList)droList.get(i);
		DROReportDetailsDTO droDto  =  new DROReportDetailsDTO();
		droDto.setDroId((String)row_list.get(0));
		droDto.setDroName((String)row_list.get(1));
		droNewList.add(droDto);
	}
	}
	return droNewList;
}

public ArrayList getPendingItems(){
	
	String[]  pending   =  new  String[2];
	pending[0]  =  "PEN";
	pending[1]  =  "Y";
	ArrayList listpending  =   droReportDAO.getChkItems(pending);
	ArrayList listpendingItes  =  new ArrayList();
	
	for(int i =0;i<listpending.size();i++)
	{
		ArrayList rowList = (ArrayList)listpending.get(i);
		DROInspItemMap   droInspMap  =  new DROInspItemMap();
		droInspMap.setItemName((String)rowList.get(0));
		listpendingItes.add(droInspMap);
	}
	
	return listpendingItes;
}

public DROInspItemMap getItemName(){
	
	DROInspItemMap    itemMapDto  =  new  DROInspItemMap();
	String[] itemId  =  new  String[1];
	itemId[0] = "PEN";
	ArrayList  listName  =  droReportDAO.getItem(itemId);
	ArrayList  itemName  =  (ArrayList)listName.get(0);
	itemMapDto.setItemName((String)itemName.get(0));
	itemMapDto.setItemId((String)itemName.get(1));
	
	return itemMapDto;
}

public ArrayList getPendingTextItems(){
	
	String[]  pending   =  new  String[2];
	pending[0]  =  "PEN";
	pending[1]  =  "Y";
	ArrayList listpendingItes  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(pending);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList  = (ArrayList)listFromDB.get(i);
		DROInspItemMap   droInspMap  =  new DROInspItemMap();
		droInspMap.setItemName((String)rowList.get(0));
		listpendingItes.add(droInspMap);
	}
	return listpendingItes;
}

public ArrayList getPendingRemarkItems(){
	
	String[]  pending   =  new  String[2];
	pending[0]  =  "PEN";
	pending[1]  =  "Y";
	ArrayList listItems   =  new ArrayList();
	ArrayList listFromDB  = droReportDAO.getRemarksItems(pending);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList =  (ArrayList)listFromDB.get(i);
		DROInspItemMap   droInspMap  =  new DROInspItemMap();
		droInspMap.setItemName((String)rowList.get(0));
		listItems.add(droInspMap);
	}
	return listItems;
}

public DROInspItemMap getItemNameMud(){
	
	DROInspItemMap droInspItemMap  =  new  DROInspItemMap();
	String[] itemId  =  new  String[1];
	itemId[0] = "MUD";
	ArrayList  listName  =  droReportDAO.getItem(itemId);
	ArrayList  itemName  =  (ArrayList)listName.get(0);
	droInspItemMap.setItemName((String)itemName.get(0));
	
	
	return droInspItemMap;
}

public ArrayList getMudTextItems(){
	
	String[]  pending   =  new  String[2];
	pending[0]  =  "MUD";
	pending[1]  =  "Y";
	ArrayList listpendingItes  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(pending);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listpendingItes.add(droItemDto);
	}	
	return listpendingItes;
}

public ArrayList getMudRemarksItems(){
	
	String[]  mudrank   =  new  String[2];
	mudrank[0]  =  "MUD";
	mudrank[1]  =  "Y";
	ArrayList listItems   =  new ArrayList();
	ArrayList listFromDB  = droReportDAO.getRemarksItems(mudrank);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droDto =  new  DROInspItemMap();
		droDto.setItemName((String)rowList.get(0));
		listItems.add(droDto);
	}
	return listItems;
}

public DROInspItemMap getItemNameRrc(){
	
	DROInspItemMap droInspItemMap  =  new  DROInspItemMap();
	String[] itemId  =  new  String[1];
	itemId[0] = "RRC";
	ArrayList  listName  =  droReportDAO.getItem(itemId);
	ArrayList  itemName  =  (ArrayList)listName.get(0);
	droInspItemMap.setItemName((String)itemName.get(0));
	return droInspItemMap;
}

public ArrayList getRrcTextItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "RRC";
	item[1]  =  "Y";
	ArrayList listRrcItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listRrcItems.add(droItemDto);
	}	
	return listRrcItems;
}

public ArrayList getRrcChkItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "RRC";
	chkItems[1]  =  "Y";
	ArrayList listFromDb  =   droReportDAO.getChkItems(chkItems);
	ArrayList listRrcItems  =  new ArrayList();
	
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList = (ArrayList)listFromDb.get(i);
		DROInspItemMap  droItemMap  =  new  DROInspItemMap();
		droItemMap.setItemName((String)rowList.get(0));
		listRrcItems.add(droItemMap);
	}
	
	return listRrcItems;
}

public ArrayList getRrcChkRadioItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "RRC";
	chkItems[1]  =  "Y"; 
	ArrayList listFromDb     =   droReportDAO.getChkRadioItems(chkItems);
	ArrayList listDroItemDto =  new ArrayList();
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList =  (ArrayList)listFromDb.get(i);
		DROInspItemMap  droInspItemMap = new  DROInspItemMap();
		droInspItemMap.setItemName((String)rowList.get(0));
		listDroItemDto.add(droInspItemMap);
	}
	return listDroItemDto;
}

public DROInspItemMap getItemNameSro(){
	
	String[] itemName  =  new String[1];
	itemName[0]  =  "SRO";
	ArrayList listFromDb     =   droReportDAO.getItem(itemName);
	ArrayList  listItemName  =  (ArrayList)listFromDb.get(0);
	DROInspItemMap  drInspMapDto  =  new  DROInspItemMap();
	drInspMapDto.setItemName((String)listItemName.get(0));
	return drInspMapDto;  
}

public ArrayList getSroTxtItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "SRO";
	item[1]  =  "Y";
	ArrayList listSroItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listSroItems.add(droItemDto);
	}	
	return  listSroItems;
}

public ArrayList getSroCheckItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "SRO";
	chkItems[1]  =  "Y";
	ArrayList listFromDb  =   droReportDAO.getChkItems(chkItems);
	ArrayList listSroItems  =  new ArrayList();
	
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList = (ArrayList)listFromDb.get(i);
		DROInspItemMap  droItemMap  =  new  DROInspItemMap();
		droItemMap.setItemName((String)rowList.get(0));
		listSroItems.add(droItemMap);
	}
	return listSroItems;
}

public DROInspItemMap getItemNameRos(){
	
	String[] item    =    new String[1];
	item[0]          =    "ROS";
	ArrayList listFromDb     =   droReportDAO.getItem(item);
	ArrayList  listItemName  =  (ArrayList)listFromDb.get(0);
	DROInspItemMap  drInspMapDto  =  new  DROInspItemMap();
	drInspMapDto.setItemName((String)listItemName.get(0));
	return drInspMapDto;  
}

public ArrayList getRosTxtItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "ROS";
	item[1]  =  "Y";
	ArrayList listRosItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listRosItems.add(droItemDto);
	}	
	return listRosItems;
}

public ArrayList getRosChkRadioItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "ROS";
	chkItems[1]  =  "Y"; 
	ArrayList listFromDb     =   droReportDAO.getChkRadioItems(chkItems);
	ArrayList listRosItemDto =  new ArrayList();
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList =  (ArrayList)listFromDb.get(i);
		DROInspItemMap  droInspItemMap = new  DROInspItemMap();
		droInspItemMap.setItemName((String)rowList.get(0));
		listRosItemDto.add(droInspItemMap);
	}
	return listRosItemDto;
}

public ArrayList getRosChkItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "ROS";
	chkItems[1]  =  "Y";
	ArrayList listFromDb  =   droReportDAO.getChkItems(chkItems);
	ArrayList listRosItems  =  new ArrayList();
	
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList = (ArrayList)listFromDb.get(i);
		DROInspItemMap  droItemMap  =  new  DROInspItemMap();
		droItemMap.setItemName((String)rowList.get(0));
		listRosItems.add(droItemMap);
	}
	return listRosItems;
}

public DROInspItemMap getItemNameCas(){
	
	String[] item    =    new String[1];
	item[0]          =    "CAS";
	ArrayList listFromDb     =   droReportDAO.getItem(item);
	ArrayList  listItemName  =  (ArrayList)listFromDb.get(0);
	DROInspItemMap  drInspMapDto  =  new  DROInspItemMap();
	drInspMapDto.setItemName((String)listItemName.get(0));
	return drInspMapDto;
}

public ArrayList getCasTxtItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "CAS";
	item[1]  =  "Y";
	ArrayList listCasItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listCasItems.add(droItemDto);
	}
	return listCasItems;
}

public DROInspItemMap getItemNameSta(){
	
	String[] item    =    new String[1];
	item[0]          =    "STA";
	ArrayList listFromDb     =   droReportDAO.getItem(item);
	ArrayList  listItemName  =  (ArrayList)listFromDb.get(0);
	DROInspItemMap  drInspMapDto  =  new  DROInspItemMap();
	drInspMapDto.setItemName((String)listItemName.get(0));
	
	return drInspMapDto;
}

public ArrayList getStaChkRadioItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "STA";
	chkItems[1]  =  "Y"; 
	ArrayList listFromDb     =   droReportDAO.getChkRadioItems(chkItems);
	ArrayList listStaItemDto =  new ArrayList();
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList =  (ArrayList)listFromDb.get(i);
		DROInspItemMap  droInspItemMap = new  DROInspItemMap();
		droInspItemMap.setItemName((String)rowList.get(0));
		listStaItemDto.add(droInspItemMap);
	}
	return listStaItemDto;
}

public ArrayList getStaChkItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "STA";
	chkItems[1]  =  "Y";
	ArrayList listFromDb  =   droReportDAO.getChkItems(chkItems);
	ArrayList listStaItems  =  new ArrayList();
	
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList = (ArrayList)listFromDb.get(i);
		DROInspItemMap  droItemMap  =  new  DROInspItemMap();
		droItemMap.setItemName((String)rowList.get(0));
		listStaItems.add(droItemMap);
	}
	return listStaItems;
}

public ArrayList getStaRemarksItems(){
	
	String[]  chkItems   =  new  String[2];
	chkItems[0]  =  "STA";
	chkItems[1]  =  "Y";
	ArrayList listItems   =  new ArrayList();
	ArrayList listFromDB  = droReportDAO.getRemarksItems(chkItems);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droDto =  new  DROInspItemMap();
		droDto.setItemName((String)rowList.get(0));
		listItems.add(droDto);
	}
	return listItems;
}

public DROInspItemMap getItemNameTes(){
	
	String[]  item         =   new  String[1];
	DROInspItemMap  itemDto=   new DROInspItemMap();
	item[0]                =   "TES";
	ArrayList  listItem    =   new ArrayList();
	ArrayList  listFromDB  =   droReportDAO.getItem(item);
	ArrayList  rowList     =   (ArrayList)listFromDB.get(0);
	itemDto.setItemName((String)rowList.get(0));
	
	return itemDto;
}

public ArrayList getTesTextItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "TES";
	item[1]  =  "Y";
	ArrayList listTesItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listTesItems.add(droItemDto);
	}
	return listTesItems;
}

public ArrayList getTesRemarksItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "TES";
	item[1]  =  "Y";
	ArrayList listItems   =  new ArrayList();
	ArrayList listFromDB  = droReportDAO.getRemarksItems(item);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droDto =  new  DROInspItemMap();
		droDto.setItemName((String)rowList.get(0));
		listItems.add(droDto);
	}
	
	return listItems;
}

public DROInspItemMap getItemNameDes(){
	
	String[] item      =  new String[1];
	item[0]            =  "DES";
	DROInspItemMap  itemNameDto  =  new  DROInspItemMap();
	ArrayList listItemName       =  droReportDAO.getItem(item);
	ArrayList rowList            =  (ArrayList)listItemName.get(0);
	itemNameDto.setItemName((String)rowList.get(0));
	
	return itemNameDto;
}

public ArrayList getDesTextItems(){
	
	String[]  item   =  new  String[2];
	item[0]  =  "DES";
	item[1]  =  "Y";
	ArrayList listDesItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listDesItems.add(droItemDto);
	}
	return listDesItems;
}

public ArrayList getChkRadioDes(){
	
	String[]  item    =  new String[2]; 
	item[0]  =  "DES";
	item[1]  =  "Y"; 
	ArrayList listFromDb     =   droReportDAO.getChkRadioItems(item);
	ArrayList listDesItemDto =  new ArrayList();
	for(int i=0;i<listFromDb.size();i++)
	{
		ArrayList rowList =  (ArrayList)listFromDb.get(i);
		DROInspItemMap  droInspItemMap = new  DROInspItemMap();
		droInspItemMap.setItemName((String)rowList.get(0));
		listDesItemDto.add(droInspItemMap);
	}
	return listDesItemDto;
}

public DROInspItemMap getItemNameFru(){
	
	String[]  item    =    new String[1];
	item[0]           =    "FRU";
	DROInspItemMap droInspDto    =     new DROInspItemMap();
	ArrayList  listFromDB        =     droReportDAO.getItem(item);
	ArrayList  rowList           =     (ArrayList)listFromDB.get(0);
	droInspDto.setItemName((String)rowList.get(0));
	
	return droInspDto;
}

public ArrayList getChkRadioFru(){
	
	String[]   item   =  new String[2];
	item[0]           =  "FRU";
	item[1]           =  "Y";
	ArrayList listItems     =   new ArrayList();
	ArrayList listFromDB    =   droReportDAO.getChkRadioItems(item);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList       =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  newDto  =  new DROInspItemMap();
		newDto.setItemName((String)rowList.get(0));
		listItems.add(newDto);
	}	
	return listItems;
}

public ArrayList getDocItemsFru(){
	
	String[]  item   =   new String[2];
	item[0]          =   "FRU";
	item[1]          =   "Y";
	ArrayList  listItems  =  new ArrayList();
	ArrayList  listFromBD =  droReportDAO.getDocItems(item);
	for(int i=0;i<listFromBD.size();i++)
	{
		ArrayList rowList              =  (ArrayList)listFromBD.get(i);
		DROInspItemMap   itemNamesDto  =  new DROInspItemMap();
		itemNamesDto.setItemName((String)rowList.get(0));
		listItems.add(itemNamesDto);
	}
	return listItems;
}

public DROInspItemMap getItemNameLek(){
	
	String[]  item  = new String[1];
	item[0]  =  "MAH";
	DROInspItemMap droInspDto    =     new DROInspItemMap();
	ArrayList  listFromDB        =     droReportDAO.getItem(item);
	ArrayList  rowList           =     (ArrayList)listFromDB.get(0);
	droInspDto.setItemName((String)rowList.get(0));
	
	return droInspDto;
}

public ArrayList getLekTxtItems(){
	
	String[] item  =  new String[2];
	item[0]  =  "MAH";
	item[1]  =  "Y";
	
	ArrayList listLekItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listLekItems.add(droItemDto);
	}
	return listLekItems;
}

public ArrayList getLekDocItems(){
	
	String[] item  =  new String[2];
	item[0]    =  "MAH";
	item[1]    =  "Y";
	ArrayList  listItems  =  new ArrayList();
	ArrayList  listFromBD =  droReportDAO.getDocItems(item);
	for(int i=0;i<listFromBD.size();i++)
	{
		ArrayList rowList              =  (ArrayList)listFromBD.get(i);
		DROInspItemMap   itemNamesDto  =  new DROInspItemMap();
		itemNamesDto.setItemName((String)rowList.get(0));
		listItems.add(itemNamesDto);
	}
	return listItems;
}

public ArrayList getLekRemarkItems(){
	
	String[] item  = new String[2];
	item[0]  =  "MAH";
	item[1]  =  "Y";
	ArrayList listItems   =  new ArrayList();
	ArrayList listFromDB  = droReportDAO.getRemarksItems(item);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droDto =  new  DROInspItemMap();
		droDto.setItemName((String)rowList.get(0));
		listItems.add(droDto);
	}
	return listItems;
}

public DROInspItemMap getItemNameLekha(){
	
	String[] item  =  new String[1];
	item[0]   =  "LEK";
	DROInspItemMap droInspDto    =     new DROInspItemMap();
	ArrayList  listFromDB        =     droReportDAO.getItem(item);
	ArrayList  rowList           =     (ArrayList)listFromDB.get(0);
	droInspDto.setItemName((String)rowList.get(0));
	
	return droInspDto;
}

public ArrayList getTxtItemsLekha(){
	
	String[] item = new String[2];
	item[0]    = "LEK";
	item[1]    = "Y";
	ArrayList listLekhaItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listLekhaItems.add(droItemDto);
	}
	return listLekhaItems;
}

public ArrayList getDocItemsLekha(){
	
	String[] item  =  new String[2];
	item[0]  =  "LEK";
	item[1]  =  "Y";
	ArrayList  listItems  =  new ArrayList();
	ArrayList  listFromBD =  droReportDAO.getDocItems(item);
	for(int i=0;i<listFromBD.size();i++)
	{
		ArrayList rowList              =  (ArrayList)listFromBD.get(i);
		DROInspItemMap   itemNamesDto  =  new DROInspItemMap();
		itemNamesDto.setItemName((String)rowList.get(0));
		listItems.add(itemNamesDto);
	}
	return listItems;
}

public DROInspItemMap getItemNameRep(){
	
	String[] item  =  new String[1];
	item[0]        =  "REP";
	DROInspItemMap droInspDto    =     new DROInspItemMap();
	ArrayList  listFromDB        =     droReportDAO.getItem(item);
	ArrayList  rowList           =     (ArrayList)listFromDB.get(0);
	droInspDto.setItemName((String)rowList.get(0));
	
	return droInspDto;
}

public ArrayList getTxtItemsRep(){
	
	String[] item   =  new String[2];
	item[0]         =  "REP";
	item[1]         =  "Y";
	ArrayList listRepItems  =  new ArrayList();
	ArrayList listFromDB  =  droReportDAO.getTextItem(item);
	for(int i =0;i<listFromDB.size();i++)
	{
		ArrayList rowList  =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  droItemDto =  new DROInspItemMap();
		droItemDto.setItemName((String)rowList.get(0));
		droItemDto.setFromDate((String)rowList.get(1));
		droItemDto.setToDate((String)rowList.get(2));
		listRepItems.add(droItemDto);
	}
	return listRepItems;
}

public ArrayList getRadioItemsRep(){
	
	String[]   item  =  new String[2];
	item[0]  =  "REP";
	item[1]  =  "Y";
	ArrayList listItems     =   new ArrayList();
	ArrayList listFromDB    =   droReportDAO.getChkRadioItems(item);
	for(int i=0;i<listFromDB.size();i++)
	{
		ArrayList rowList       =  (ArrayList)listFromDB.get(i);
		DROInspItemMap  newDto  =  new DROInspItemMap();
		newDto.setItemName((String)rowList.get(0));
		listItems.add(newDto);
	}
	return listItems;
}

public String saveDROInspectionDetails(DROReportDetailsDTO droInspDto,ArrayList pendingfiles,ArrayList auditfiles, ArrayList internalauditfiles, ArrayList stampfiles, String filePath,String officeId)
{
 	String droReportId   = null;
 	
 	String[] droInsp     = new String[34];
 	droInsp[0]           = CommonUtil.getConvertedDate(droInspDto.getInspDate());
 	droInsp[1]           = droInspDto.getInspectedBy();
 	droInsp[2]           = droInspDto.getDroId();
 	droInsp[3]           = CommonUtil.getConvertedDate(droInspDto.getInspStartDate());
 	droInsp[4]           = CommonUtil.getConvertedDate(droInspDto.getInspToDate());
 	droInsp[5]           = droInspDto.getInspectedBy();
 	droInsp[6]           = droInspDto.getAnnualTargetPfy();
 	droInsp[7]           = droInspDto.getRevenueCollectionPfy();
 	droInsp[8]           = droInspDto.getTargetCompPfy();
 	droInsp[9]           = droInspDto.getIncrDcrPfy();
 	droInsp[10]          = droInspDto.getCfyRevenueTarget();
 	droInsp[11]          = droInspDto.getCfyRevenueColl();
 	droInsp[12]          = droInspDto.getRevenueTargetPfy();
 	droInsp[13]          = "Open";
 	droInsp[14]          = droInspDto.getInspectedBy();
 	droInsp[15]          = droInspDto.getInspectedBy();
 	droInsp[17]			 = droInspDto.getIgComment();
 	droInsp[18]			 =	droInspDto.getFrommonth()+"-"+droInspDto.getFromyear();
 	droInsp[19]			 =	droInspDto.getTomonth()+"-"+droInspDto.getToyear();
 	droInsp[20]			 =	droInspDto.getRevenuefrommonth()+"-"+droInspDto.getRevenuefromyear();
 	droInsp[21]			 =	droInspDto.getRevenuetomonth()+"-"+droInspDto.getRevenuetoyear();
 	droInsp[22]			 =	droInspDto.getPfyfrommonth()+"-"+droInspDto.getPfyfromyear();
 	droInsp[23]			 =	droInspDto.getPfytomonth()+"-"+droInspDto.getPfytoyear();
 	droInsp[24]			=	droInspDto.getSrName();
 	droInsp[25]			=	droInspDto.getTenure();
 	droInsp[26]			=	droInspDto.getAuthorityName();
 	droInsp[27]			=  	CommonUtil.getConvertedDate(droInspDto.getObjEntryDate());
 	droInsp[28]			= 	 droInspDto.getPastdetails();
 	droInsp[29]			= 	 droInspDto.getStampDetails();
 	droInsp[30]			= 	 droInspDto.getPendingDetails();
 	droInsp[31]			= 	 droInspDto.getRrcDetails();
 	droInsp[32]			= 	 droInspDto.getDeDetails();
 	droInsp[33]			= 	 droInspDto.getPensionDetails();
 	ArrayList listPendingTaskDto = droInspDto.getListDroPendingTask();
 	ArrayList listItemMapDto     = droInspDto.getListDroItemMap();
 	ArrayList listEmpMapDto      = droInspDto.getListEmpMapDto();  
 	ArrayList listReasonMap      = droInspDto.getListDROReasonMap();
 	
 	ArrayList listAllDto         = new ArrayList();
 	
 	listAllDto.add(droInsp);
 	listAllDto.add(pendingfiles);
 	listAllDto.add(auditfiles);
 	listAllDto.add(internalauditfiles);
 	listAllDto.add(stampfiles);
 	listAllDto.add(listPendingTaskDto);
 	listAllDto.add(listItemMapDto);
 	listAllDto.add(listEmpMapDto);
 	listAllDto.add(listReasonMap);
 	listAllDto.add(filePath);
 	
 	droReportId  = droReportDAO.saveDroDetails(listAllDto,officeId);
 	
 	
 	
 	return droReportId;
}

//DRO Inspection View Start
public DROReportDetailsDTO getDroReportDetails(String reportId,String language){
	String[] droDetails = new String[1];
	droDetails[0]  = reportId;
	DROReportDetailsDTO droReportDto = new DROReportDetailsDTO();
	ArrayList listDroDto = droReportDAO.getDROInspDetails(droDetails,language);
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
	if(rowList.get(33)!=null&&!rowList.get(33).toString().equalsIgnoreCase(""))
	{
		droReportDto.setDrCompliance((String) rowList.get(33));
	}
	else
	{
		droReportDto.setDrCompliance("");
	}
	if(rowList.get(34)!=null&&!rowList.get(34).toString().equalsIgnoreCase(""))
	{
		droReportDto.setDigCompliance((String) rowList.get(34));
	}
	else
	{
		droReportDto.setDigCompliance("");
	}
	if(rowList.get(35)!=null&&!rowList.get(35).toString().equalsIgnoreCase(""))
	{
		droReportDto.setIgCompliance((String) rowList.get(35));
	}
	else
	{
		droReportDto.setIgCompliance("");
	}
	}
	return droReportDto;
	
}

public ArrayList getDroInspIds(DROReportDetailsDTO droReport)
{
    String[] droDetails    =   new String[5];
    droDetails[0]   =  null;//CommonUtil.getConvertedDate(droReport.getInspStartDate());
    droDetails[1]   =  null;//CommonUtil.getConvertedDate(droReport.getInspToDate());
    droDetails[2]   =  null;//droReport.getDroId();
    droDetails[3]   =  null;//droReport.getDroInspId();
    droDetails[4]   =  null;
    
    //MODIFIED BY SHRUTI--STATUS FIELD REMOVED FROM FRONT END
    droReport.setInspectionStatus("OPEN");
    
    if(droReport.getInspStartDate()!=null && droReport.getInspToDate()!=null)
    {
    	if(!droReport.getInspStartDate().equalsIgnoreCase("") && !droReport.getInspToDate().equalsIgnoreCase(""))
    	droDetails[0]   =  CommonUtil.getConvertedDate(droReport.getInspStartDate());
    	droDetails[1]   =  CommonUtil.getConvertedDate(droReport.getInspToDate());
    	
    }
    if(droReport.getDroId()!=null && !droReport.getDroId().equalsIgnoreCase(""))
    {
    	droDetails[2]   =  droReport.getDroId();
    }
   	if(droReport.getDroInspId()!=null && !droReport.getDroInspId().equalsIgnoreCase(""))
   	{
   		droDetails[3]   =  droReport.getDroInspId();
   	}
   	if(droReport.getInspectionStatus()!=null && !droReport.getInspectionStatus().equalsIgnoreCase(""))
    {
   		droDetails[4]   = droReport.getInspectionStatus();
    }	
   		ArrayList listFromDB  = null;
   	
    ArrayList listIds     = new ArrayList();
    listFromDB   =   droReportDAO.getDroInspIds(droDetails);
    if (listFromDB != null) {
		for (int i = 0; i < listFromDB.size(); i++) {
              ArrayList rowList = (ArrayList)listFromDB.get(i);
              DROReportDetailsDTO  droDto = new DROReportDetailsDTO();
              droDto.setDroInspId((String)rowList.get(0));
              droDto.setInspStartDate(getStrDate((String)rowList.get(1)));
              droDto.setInspToDate(getStrDate((String)rowList.get(2)));
              droDto.setInspStatus((String)rowList.get(3));
              listIds.add(droDto);
		}
	}
    return listIds;
}

public ArrayList getDROEmpDetails(String inspId)
{
	String[] insp = new String[1];
	insp[0] = inspId;
	ArrayList listFromDB  = null;
	listFromDB  =  droReportDAO.getDROEmpDetails(insp);
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
	listDB = droReportDAO.getReasonMapDetails(insp);
	for(int i=0;i<listDB.size();i++){
		ArrayList rowList = (ArrayList)listDB.get(i);
		DROReasonMapDTO reasonMap = new DROReasonMapDTO();
		reasonMap.setReason((String)rowList.get(0));
		listReasonMap.add(reasonMap);
	}
	return listReasonMap;
}

public ArrayList getPendingDetails(String inspid){
	ArrayList listDB = null;
	String[] insp = new String[1];
	insp[0] = inspid;
	ArrayList listPendingDto = new ArrayList();
	listDB = droReportDAO.getPendingDetails(insp);
	for(int i = 0;i<listDB.size();i++){
		ArrayList rowList = (ArrayList)listDB.get(0);
		DROPendingTaskDTO pendingDto = new DROPendingTaskDTO();
		pendingDto.setPendingTask((String)rowList.get(0));
		pendingDto.setPendingFrom(getStrDate((String)rowList.get(1)));
		pendingDto.setPendingNo((String)rowList.get(2));
		listPendingDto.add(pendingDto);
	}
	return listPendingDto;
}

public ArrayList getDocumentNames(String inspId){
	ArrayList listDB = null;
	ArrayList listDocNames = new ArrayList();
	String[] insp = new String[1];
	insp[0]  =  inspId;
	//insp[1]  =  "PEN";
	listDB  =  droReportDAO.getDocNames(insp);
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


/**
 * @param strDate
 * @return
 */
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

//DRO Inspection View END
public String getOfficeName(String officeId,String language)
{
	return droReportDAO.getofficeName(officeId, language);
}
public ArrayList getViewDRONames(String language){
	
	ArrayList droList     =  droReportDAO.getViewDroNames(language);
	ArrayList droNewList  =  new ArrayList();
	if(droList!=null){
	for(int i=0;i<droList.size();i++)
	{
		ArrayList row_list   =  (ArrayList)droList.get(i);
		DROReportDetailsDTO droDto  =  new DROReportDetailsDTO();
		droDto.setDroId((String)row_list.get(0));
		droDto.setDroName((String)row_list.get(1));
		droNewList.add(droDto);
	}
	}
	return droNewList;
}
public ArrayList getLoggedInDRONames(String language,String officeId){
	
	ArrayList droList     =  droReportDAO.getLoggedInDroNames(language, officeId);
	ArrayList droNewList  =  new ArrayList();
	if(droList!=null){
	for(int i=0;i<droList.size();i++)
	{
		ArrayList row_list   =  (ArrayList)droList.get(i);
		DROReportDetailsDTO droDto  =  new DROReportDetailsDTO();
		droDto.setDroId((String)row_list.get(0));
		droDto.setDroName((String)row_list.get(1));
		droNewList.add(droDto);
	}
	}
	return droNewList;
}
}



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

package com.wipro.igrs.auditinspection.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dto.DROEmpMapDTO;
import com.wipro.igrs.auditinspection.dto.DROInspItemMap;
import com.wipro.igrs.auditinspection.dto.DROPendingTaskDTO;
import com.wipro.igrs.auditinspection.dto.DROReasonMapDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.documentsearch.action.DocumentSearchAction;
import com.wipro.igrs.util.CommonUtil;

public class DROInspectionDAO {
	private Logger logger =(Logger) Logger.getLogger(DROInspectionDAO.class);
	public ArrayList getSRO(String OffTypeId)
	{
		DBUtility dbUtil  = null;
        ArrayList list=null;
        String SQL = CommonSQL.GETSRO;
        String arry[] = new String[1];
        if (OffTypeId != null) {
            arry[0] = OffTypeId;
        }
        try{
        	  dbUtil = new DBUtility();
        	 dbUtil.createPreparedStatement(SQL);
             
             list= dbUtil.executeQuery(arry);
              
             
        }
        catch(Exception ex)
        {
        	ex.printStackTrace(); 
        }
        finally
        {
			try
			{
				dbUtil.closeConnection();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
        
        return list;
    }
	
	public ArrayList getDroNames(String language,String officeId){
		
		DBUtility dbUtil    =  null;
		ArrayList listDro   =  null;
		String[] param={officeId};
		try 
		{
	        dbUtil = new DBUtility();
	        if(language.equalsIgnoreCase("en"))
	        {
	        	dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_NAMES);
	        	listDro=dbUtil.executeQuery(param);
	        }
	        else
	        {
	        	dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_NAMES_H);
	        	listDro=dbUtil.executeQuery(param);
	        }
        } 
		catch (Exception e) 
		{
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		finally
        {
			try
			{
				dbUtil.closeConnection();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return listDro;
	}
	
	
	public ArrayList getChkItems(String[] pending){
		
		DBUtility dbUtil        =  null;
		ArrayList listPending   =  null;
		try 
		{
	        dbUtil     =    new  DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHECK_ITEMS);
	        listPending  = dbUtil.executeQuery(pending);
        } 
		catch (Exception e)
		{
	        e.printStackTrace();
        }
		finally
		{
			try
			{
				dbUtil.closeConnection();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return listPending;
	}
	
	public ArrayList getItem(String[] itemId){
		
		DBUtility dbUtil        =  null;
		ArrayList listName   =  null;
		
		 try
		 {
	        dbUtil     =    new  DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_GROUP_NAME);
	        listName = dbUtil.executeQuery(itemId);
         }
		 catch (Exception e) 
		 {
	        e.printStackTrace();
         }
		 finally
		 {
			 try
				{
					dbUtil.closeConnection();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
		 }
		 return listName;
	}
	
	public ArrayList getTextItem(String[] pending)
	{
		DBUtility dbUtil        =  null;
		ArrayList listName   =  null;
		try
		{
	        dbUtil     =    new  DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_TEXT_ITEM_NAMES);
	        listName  =  dbUtil.executeQuery(pending);
        }
		catch (Exception e) {
	        e.printStackTrace();
        }
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listName;
	}
	
	public ArrayList getRemarksItems(String[] pending)
	{
		DBUtility dbUtil        =  null;
		ArrayList listNames   =  null;
		 try
		 {
	        dbUtil     =    new  DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_REMARKS_ITEM_NAMES);
	        listNames  =  dbUtil.executeQuery(pending);
         }
		 catch (Exception e) {
	        e.printStackTrace();
         }
		 finally{
				try{
					if(dbUtil!=null){
						dbUtil.closeConnection();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		 return listNames;
	}
	
	public ArrayList getChkRadioItems(String[] items)
	{
		DBUtility dbUtil        =  null;
		ArrayList listNames   =  null;
		try
		{
	        dbUtil     =    new  DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHK_RADIO_ITEMS);
	        listNames = dbUtil.executeQuery(items);
        }
		catch (Exception e) 
		{
	        e.printStackTrace();
        }
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listNames;
	}
	
	public ArrayList getDocItems(String[] item)
	{
		DBUtility dbUtil        =   null;
		ArrayList listNames     =   null;
		try
		{
	        dbUtil  =  new DBUtility();
	        dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DOC_ITEMS);
	        listNames   =  dbUtil.executeQuery(item);
        }
		catch (Exception e)
		{
	        e.printStackTrace();
        }
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listNames;
	}
	
	public String saveDroDetails(ArrayList listAllDto,String officeId){
		
		String droReportId = null;
		String droTxnId = null;
		DBUtility dbUtil   = null;
		boolean droReport  = false;
		boolean empMap     = false;
		boolean pending    = false;
		boolean itemMap    = false;
		boolean reason     = false;
		boolean uploadfile = false;
		try {
			dbUtil  =  new DBUtility();
			droReportId = getDROInspSeqId(dbUtil,officeId);
			
			String[] droInspDetails = (String[])listAllDto.get(0);
			droInspDetails[16]  = droReportId;
			
			/*System.out.println("Checking the values in Array :"+droInspDetails.length);
			System.out.println("droInspDetails[0]"+droInspDetails[0]);
			System.out.println("droInspDetails[0]"+droInspDetails[1]);
			System.out.println("droInspDetails[0]"+droInspDetails[2]);
			System.out.println("droInspDetails[0]"+droInspDetails[3]);
			System.out.println("droInspDetails[0]"+droInspDetails[4]);
			System.out.println("droInspDetails[0]"+droInspDetails[5]);
			System.out.println("droInspDetails[0]"+droInspDetails[6]);
			System.out.println("droInspDetails[0]"+droInspDetails[7]);
			System.out.println("droInspDetails[0]"+droInspDetails[8]);
			System.out.println("droInspDetails[0]"+droInspDetails[9]);
			System.out.println("droInspDetails[0]"+droInspDetails[10]);
			System.out.println("droInspDetails[0]"+droInspDetails[11]);
			System.out.println("droInspDetails[0]"+droInspDetails[12]);
			System.out.println("droInspDetails[0]"+droInspDetails[13]);
			System.out.println("droInspDetails[0]"+droInspDetails[14]);
			System.out.println("droInspDetails[0]"+droInspDetails[15]);
			System.out.println("droInspDetails[0]"+droInspDetails[16]);*/
			
			
			ArrayList listFilesPen    =   (ArrayList)listAllDto.get(1);
			ArrayList listFilesMah    =   (ArrayList)listAllDto.get(2);
			ArrayList listFilesLek    =   (ArrayList)listAllDto.get(3);
			ArrayList listFilesFru    =   (ArrayList)listAllDto.get(4);
			ArrayList listPendingTask =   (ArrayList)listAllDto.get(5);
			ArrayList listItemMap     =   (ArrayList)listAllDto.get(6);
			ArrayList listEmpMapDto   =   (ArrayList)listAllDto.get(7);
			ArrayList listReasonMap   =   (ArrayList)listAllDto.get(8);
			String filePath           =   (String)listAllDto.get(9);
			//inserting DROInspDetails
			dbUtil.setAutoCommit(false);
			
			dbUtil.createPreparedStatement(CommonSQL.INSERT_DRO_INSP_DETAILS);
			droReport  = dbUtil.executeUpdate(droInspDetails);
			
			if(droReport)
			{
				
				
				//inserting EmpMAp Details
				for(int i=0;i<listEmpMapDto.size();i++){
					//MODIFIED BY SHRUTI---18/10/2013
					String[] empData  =  new String[2];
					DROEmpMapDTO droEmpDto = (DROEmpMapDTO)listEmpMapDto.get(i);
					empData[0]  = droReportId;
					empData[1]  = droEmpDto.getEmpId();
					dbUtil.createPreparedStatement(CommonSQL.INSERT_DRO_EMP_MAP);
					empMap = dbUtil.executeUpdate(empData);
					
					if(!empMap){
						droReport = false;
						break;
					}
				}
			}
			/*if(droReport && listPendingTask!=null)
			{
				//inserting pending item Details
				for(int i=0;i<listPendingTask.size();i++)
				{
					String[] pendingItem         = new String[4];
					DROPendingTaskDTO droPending = (DROPendingTaskDTO)listPendingTask.get(i);
					pendingItem[0]       = droReportId;
					pendingItem[1]       = droPending.getPendingTask();
					pendingItem[2]       = CommonUtil.getConvertedDate(droPending.getPendingFrom());
					pendingItem[3]       = droPending.getPendingNo();
					dbUtil.createPreparedStatement(CommonSQL.INSERT_DRO_PENDING_ITEMS);
					pending  = dbUtil.executeUpdate(pendingItem);
					
					if(!pending)
					{
						droReport = false;
						break;
					}
					
				}
			}*/
			
			/*if(droReport && listItemMap!=null)
			{
				//inserting Item Map Details
				for(int i=0;i<listItemMap.size();i++)
				{
					 //ArrayList listItem = (ArrayList)listItemMap.get(i);
					 //DROInspItemMap droItemMapDto =(DROInspItemMap)listItem.get(i); 
					 //Object  droItemMapDto =(Object)listItemMap.get(i);
					// System.out.println(droItemMapDto);
					DROInspItemMap droItemMapDto = (DROInspItemMap)listItemMap.get(i);
					String itemId  = getItemId(droItemMapDto.getItemName(),dbUtil);
					String[] item = new String[4];
					
					
					item[0]  =  droReportId;
					item[1]  =  itemId;
					item[2]  =  droItemMapDto.getItemRemarks();
					item[3]  =  String.valueOf(droItemMapDto.getItemValue());
					dbUtil.createPreparedStatement(CommonSQL.INSERT_DRO_ITEM_MAP);
					itemMap = dbUtil.executeUpdate(item);
					if(!itemMap)
					{
						droReport = false;
						break;
					}
				}
			}
			*/
			if(droReport && listReasonMap!=null)
			{
				//inserting Reason Map Details 
				for(int i=0;i<listReasonMap.size();i++)
				{
					DROReasonMapDTO droReasonMap  =  (DROReasonMapDTO)listReasonMap.get(i);
					String[] reasonMap = new String[2];
					reasonMap[0]  = droReportId;
					reasonMap[1]  = droReasonMap.getReason();
					dbUtil.createPreparedStatement(CommonSQL.INSERT_DRO_REASON_MAP);
					reason = dbUtil.executeUpdate(reasonMap);
					if(!reason)
					{
						droReport = false;
						break;
					}
				}
			}
			
			if(droReport && listFilesPen !=null )
			{
				//Uploading PEN files to DB
				for(int i=0;i<listFilesPen.size();i++)
				{
					UploadFileDTO uploadDto  =  (UploadFileDTO)listFilesPen.get(i);
					String docTxnId    =   getDocTxnId(dbUtil);
					String fileName    =   uploadDto.getFileName();
					String filePathLen=uploadDto.getFilePath();
            		//File newFile        = new File(filesPath+filename);
            		String path=uploadDto.getFilePath().substring(0, filePathLen.length()-(uploadDto.getFileName().length()+2)).concat(droReportId.concat("\\"));
					//modified by shruti----18/10/2013
					uploadfile =dbUtil.saveDROInspDocuments(droReportId, docTxnId, fileName, path, "PEN");
					//uploadfile = dbUtil.saveDRODocuments(droReportId, docTxnId, fileName, filePath, "PEN");
					if(!uploadfile)
					{
						droReport = false;
						break;
					}
				}
			}
			
			/*if(droReport && listFilesMah !=null)
			{
				//Uploading MAH files to DB
				for(int i=0;i<listFilesMah.size();i++)
				{
					UploadFileDTO uploadDto  =  (UploadFileDTO)listFilesMah.get(i);
					String docTxnId    =   getDocTxnId(dbUtil);
					String fileName    =   uploadDto.getFileName();
					uploadfile = dbUtil.saveDRODocuments(droReportId, docTxnId, fileName, filePath, "MAH");
					if(!uploadfile)
					{
						droReport = false;
						break;
					}
				}
			}
			
			if(droReport && listFilesLek !=null)
			{
				//Uploading LEK files to DB
				for(int i=0;i<listFilesLek.size();i++)
				{
					UploadFileDTO uploadDto  =  (UploadFileDTO)listFilesLek.get(i);
					String docTxnId    =   getDocTxnId(dbUtil);
					String fileName    =   uploadDto.getFileName();
					uploadfile = dbUtil.saveDRODocuments(droReportId, docTxnId, fileName, filePath, "LEK");
					if(!uploadfile)
					{
						droReport = false;
						break;
					}
				}
			}
			
			if(droReport && listFilesFru!=null)
			{
				//Uploading FRU files to DB
				if(listFilesFru.size()>0)
				{
					for(int i=0;i<listFilesFru.size();i++)
					{
						UploadFileDTO uploadDto  =  (UploadFileDTO)listFilesFru.get(i);
						String docTxnId    =   getDocTxnId(dbUtil);
						String fileName    =   uploadDto.getFileName();
						uploadfile = dbUtil.saveDRODocuments(droReportId, docTxnId, fileName, filePath, "FRU");
						if(!uploadfile)
						{
							droReport = false;
							break;
						}
					}
				}
			}*/
			
			if(droReport)
			{
				dbUtil.commit();
				
				droTxnId = droReportId;
			}
			else
			{
				dbUtil.rollback();
			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try 
			{
			   if(dbUtil!=null){	dbUtil.closeConnection();
				
			   }
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return droTxnId;
	}
	
	private String getDROInspSeqId(DBUtility dbUtil,String officeId){
		
		String reportId = null;
		ArrayList listSeq = new ArrayList();
		try{
			//dbUtil = new DBUtility();
			dbUtil.createStatement();
			listSeq  = dbUtil.executeQuery(CommonSQL.RETRIEVE_SRO_INSP_SEQ);
			ArrayList rowList = (ArrayList)listSeq.get(0);
			rowList     =   (ArrayList)listSeq.get(0);
			reportId    =   (String)rowList.get(0);
			//modified by shruti----2 march 2014
			//String sqlCurrentdate=CommonSQL.GETCURRENTDATE;
			String sqlCurrentdate=CommonSQL.GETCURRENTDATENEWFORMAT;
			
            dbUtil.createStatement();
            String currentDate=dbUtil.executeQry(sqlCurrentdate);     
            String district=getDistrictId(officeId);
            //CHANGED AS PER FUCNTIONAL TRACKER
            reportId="1"+district+currentDate+reportId;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		//finally cannot be added----part of transaction-12 may 2014 -shruti
		return reportId;
	}
	
	private String getItemId(String itemName,DBUtility dbUtil)
	{
		String itemId = null;
		String[] item  = new String[1];
		item[0]  = itemName;
		ArrayList listId = null;
		try{
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_ITEM_ID);
			listId = dbUtil.executeQuery(item);
			ArrayList rowList = (ArrayList)listId.get(0);
			itemId = (String)rowList.get(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return itemId;
	}
	
	private String getDocTxnId(DBUtility dbUtil)
	{
		String txnId  = null;
		try{
			dbUtil.createStatement();
			ArrayList listSeqId = dbUtil.executeQuery(CommonSQL.RETRIEVE_SEQ_DOC_TXN);
			ArrayList rowList = (ArrayList)listSeqId.get(0);
			txnId = (String)rowList.get(0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		/*finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}*/
		return txnId;
	}
	
	public ArrayList getEmpDetail(String userid)
	{
		DBUtility dbUtil = null;
		ArrayList listEmpDetails   =  new ArrayList();
		String userId[]            =  new String[1];
		userId[0]                  =  userid;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIVE_EMPLOYEE_DETAILS);
			listEmpDetails   = dbUtil.executeQuery(userId);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listEmpDetails;
	}
	
	public String getDistrictName(String sroname,String language)
	{	DBUtility dbUtil=null;
		String distname=null;
		try{
			dbUtil = new DBUtility();
			String sro[]={sroname};
			if(language.equalsIgnoreCase("en"))
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIVE_DISTRICT_NAME);
			}
			else
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIVE_DISTRICT_NAME_H);
			}
			distname = dbUtil.executeQry(sro);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return distname;
	}
	
	public ArrayList getDROInspDetails(String[] droInsp,String language){
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{	
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_DETAILS);
			}
			else
			{
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_DETAILS_H);
			}
			listFromDb = dbUtil.executeQuery(droInsp);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listFromDb;
	}
	
	public ArrayList getDroInspIds(String[] droDetails){
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try {
			
			String param[]=null;
			dbUtil  =  new DBUtility();
			String sql=CommonSQL.RETRIEVE_DRO_INSP_IDS;
			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			if(droDetails[3]!=null)
			{
				
				paramsMap.put("1txnID", droDetails[3]);
				sql=sql+" AND INSP.TRANSACTION_ID=?";
			}
			if(droDetails[4]!=null)
			{
				paramsMap.put("2Status", droDetails[4]);
				sql=sql+" AND UPPER(INSP.INSPECTION_STATUS)=?";
			}
			if( droDetails[0]!=null && droDetails[1]!=null)
			{
				sql=sql+" AND INSP.INSPECTION_START_DATE  BETWEEN ? AND ?";
				paramsMap.put("3fromDate", droDetails[0]);
				paramsMap.put("4toDate", droDetails[1]);
			}
			if(droDetails[2]!=null)
			{
				sql=sql+" AND INSP.INSPECTED_OFFICE_ID = ?";
				paramsMap.put("5officeid", droDetails[2]);
			}
			dbUtil.createPreparedStatement(sql);
			Collection<String> values = paramsMap.values();
			ArrayList<String> tmp = new ArrayList(values);
//			Collections.reverse(tmp);
			String[] params = tmp.toArray(new String[]{}); 
			listFromDb  =  dbUtil.executeQuery(params);
			logger.debug("size is"+listFromDb.size());
			//logger.debug("list is"+listFromDb);
			/*if(droDetails[3]!=null && droDetails[0]==null && droDetails[1]==null && droDetails[2]==null && droDetails[4]==null)
			{
			String [] param={droDetails[3]}; 
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.TRANSACTION_ID=?");
			listFromDb  =  dbUtil.executeQuery(param);
			}
			else if(droDetails[3]==null && droDetails[0]==null && droDetails[1]==null && droDetails[2]==null && droDetails[4]!=null)
			{
			String [] param={droDetails[4]}; 
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" UPPER(INSP.INSPECTION_STATUS)=?");
			listFromDb  =  dbUtil.executeQuery(param);
			}
			else if(droDetails[3]!=null && droDetails[0]==null && droDetails[1]==null && droDetails[2]==null && droDetails[4]!=null)
			{
				String [] param={droDetails[3],droDetails[4]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.TRANSACTION_ID=? AND UPPER(INSP.INSPECTION_STATUS)=?");
				listFromDb  =  dbUtil.executeQuery(param);
			}
			else if(droDetails[3]==null && droDetails[0]!=null && droDetails[1]!=null && droDetails[2]!=null && droDetails[4]==null)
			{
				String [] param={droDetails[0],droDetails[1],droDetails[2]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.INSPECTED_OFFICE_ID = ?");
				listFromDb  =  dbUtil.executeQuery(param);
			}
			else if(droDetails[3]!=null && droDetails[0]!=null && droDetails[1]!=null && droDetails[2]!=null && droDetails[4]==null)
			{
				String [] param={droDetails[0],droDetails[1],droDetails[2],droDetails[3]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.INSPECTED_OFFICE_ID = ? AND INSP.TRANSACTION_ID=?");
				listFromDb  =  dbUtil.executeQuery(param);
			}
			else if(droDetails[3]==null && droDetails[0]!=null && droDetails[1]!=null && droDetails[2]!=null && droDetails[4]!=null)
			{
				String [] param={droDetails[0],droDetails[1],droDetails[2],droDetails[4]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.INSPECTED_OFFICE_ID = ? AND UPPER(INSP.INSPECTION_STATUS)=?");
				listFromDb  =  dbUtil.executeQuery(param);
			}
			else
			{
				String [] param={droDetails[0],droDetails[1],droDetails[2],droDetails[4],droDetails[3]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.INSPECTED_OFFICE_ID = ? AND UPPER(INSP.INSPECTION_STATUS)=? AND INSP.TRANSACTION_ID=?");
				listFromDb  =  dbUtil.executeQuery(param);
			}
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_INSP_IDS);
			listFromDb  =  dbUtil.executeQuery(droDetails);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listFromDb;
	}
	
	public ArrayList getDROEmpDetails(String[] insp){
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_EMP_DETAILS);
			listFromDb = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listFromDb;
	}
	
	public ArrayList getReasonMapDetails(String[] insp)
	{
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_REASON_DETAILS);
			listFromDb = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listFromDb;
	}
	
	public ArrayList getPendingDetails(String[] inspid)
	{
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_PENDING_DETAILS);
			listFromDb = dbUtil.executeQuery(inspid);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listFromDb;
	}
	
	public ArrayList getDocNames(String[] insp){
		DBUtility  dbUtil = null;
		ArrayList listDB = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DRO_DOCUMENTS);
			listDB = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return listDB;
	}
	
	private String getDistrictId(String officeId)
	{
		DBUtility  dbUtil = null;
		String districitId = null;
		String arr[]={officeId};
		try{
	    dbUtil = new DBUtility();
	    dbUtil.createPreparedStatement(CommonSQL.GETDISTRICTID);
	    districitId = dbUtil.executeQry(arr);
	    if(districitId.length()<2)
	    {
	    	districitId="0"+districitId;
	    }
	}
		catch(Exception ex)
		{
		ex.printStackTrace();
		}
		finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return districitId;
	}

public String getofficeName(String officeId,String language)
{
	DBUtility  dbUtil = null;
	String districitId = null;
	String arr[]={officeId};
	try{
    dbUtil = new DBUtility();
   if(language.equalsIgnoreCase("en")){
	   
   
    dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME);
   } else
   {
	   dbUtil.createPreparedStatement(CommonSQL.GETOFFICENAME_H);
   }
    districitId = dbUtil.executeQry(arr);
    if(districitId.length()<2)
    {
    	districitId="0"+districitId;
    }
}
	catch(Exception ex)
	{
	ex.printStackTrace();
	}
	finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return districitId;
}

//ADDED BY SHRUTI
public ArrayList getViewDroNames(String language){
	
	DBUtility dbUtil    =  null;
	ArrayList listDro   =  null;
	try 
	{
        dbUtil = new DBUtility();
        if(language.equalsIgnoreCase("en"))
        {
        	dbUtil.createStatement();
        	listDro=dbUtil.executeQuery(CommonSQL.RETRIEVE_VIEW_DRO_NAMES);
        }
        else
        {
        	dbUtil.createStatement();
        	listDro=dbUtil.executeQuery(CommonSQL.RETRIEVE_VIEW_DRO_NAMES_H);
        }
    } 
	catch (Exception e) 
	{
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	finally
    {
		try
		{
			dbUtil.closeConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	return listDro;
}
public ArrayList getLoggedInDroNames(String language,String officeId){
	
	DBUtility dbUtil    =  null;
	ArrayList listDro   =  null;
	String[] param={officeId};
	try 
	{
        dbUtil = new DBUtility();
        if(language.equalsIgnoreCase("en"))
        {
        	dbUtil.createPreparedStatement(CommonSQL.GETLOGGEDINDRO);
        	listDro=dbUtil.executeQuery(param);
        }
        else
        {
        	dbUtil.createPreparedStatement(CommonSQL.GETLOGGEDINDRO_H);
        	listDro=dbUtil.executeQuery(param);
        }
    } 
	catch (Exception e) 
	{
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	finally
    {
		try
		{
			dbUtil.closeConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	return listDro;
}


}



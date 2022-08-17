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

import com.wipro.igrs.auditinspection.dto.SROCheckMapDTO;
import com.wipro.igrs.auditinspection.dto.SROEmpDTO;
import com.wipro.igrs.auditinspection.dto.SROPendingItemsDTO;
import com.wipro.igrs.auditinspection.dto.UploadFileDTO;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.CommonUtil;

public class SROReportDetailsDAO {

	/**
	 * @param OffTypeId
	 * @return
	 */
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
	//added by shruti--18th oct 2013
	public ArrayList getJursdctnSRO(String OffId,String language)
	{
		DBUtility dbUtil  = null;
        ArrayList list=null;
        String SQL = "";
        if(language.equalsIgnoreCase("en"))
        {
        SQL=CommonSQL.GETJURISDICTIONSRO;
        }
        else
        {
        	SQL=CommonSQL.GETJURISDICTIONSRO_HI;
        }
        String arry[] = new String[1];
        if (OffId != null) {
            arry[0] = OffId;
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
	//end
	
	public ArrayList getDistrict(String office,String language) {
		ArrayList list=null;
		DBUtility dbUtil = null;
		String str="";
		try{
			
			 dbUtil=new DBUtility();
			 String[] param={office};
			
			 if(language.equalsIgnoreCase("en"))
			 {
				 str=CommonSQL.GETOFFICEDISTRICT;
			 }
			 else
			 {
				 str=CommonSQL.GETOFFICEDISTRICT_HI;
			 }
		     dbUtil.createPreparedStatement(str);
		     list = dbUtil.executeQuery(param);
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		finally{
			try{
				if(dbUtil!=null){
					dbUtil.closeConnection();
				}
			}catch(Exception exp){
				exp.printStackTrace();
			}
		}  
		
		return list;
	}
	
	/**
	 * @return
	 */
	public ArrayList getCheckListItems(String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		String item[] = new String[1];
		item[0]="Y";
		try{
		    dbUtil = new DBUtility();
		    if(language.equalsIgnoreCase("en"))
		    {
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHKLIST_ITEMS);
		    }
		    else
		    {
		    	dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHKLIST_ITEMS_HI);
		    }
			listChkItems = dbUtil.executeQuery(item);
			
		}
		catch(Exception ex){
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
		return listChkItems;
	}
	//added by shruti---29 april 2014
	public ArrayList getCheckListNames(String[] checklistids,String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		
		String str="";
		try{
		    dbUtil = new DBUtility();
		    if(language.equalsIgnoreCase("en"))
		    {
			str=CommonSQL.RETRIEVE_CHKLIST_NAMES;
			StringBuilder squery = new StringBuilder(str);
			
			 for(int i=0;i<checklistids.length;i++)
				{
					if(i!=checklistids.length-1)
					squery.append("?,");
					else
						squery.append("?");
					
				}
			 str=squery.append(CommonSQL.RETRIEVE_CHKLIST_NAMES1).toString();
		    }
		    else
		    {
		    str=CommonSQL.RETRIEVE_CHKLIST_NAMES_HI;
		    StringBuilder squery = new StringBuilder(str);
			
			 for(int i=0;i<checklistids.length;i++)
				{
					if(i!=checklistids.length-1)
					squery.append("?,");
					else
						squery.append("?");
					
				}
			 str=squery.append(CommonSQL.RETRIEVE_CHKLIST_NAMES_HI1).toString();
		    }
		    
			 dbUtil.createPreparedStatement(str);
			listChkItems = dbUtil.executeQuery(checklistids);
			
		}
		catch(Exception ex){
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
		return listChkItems;
		
		
	}
	
	//end
	//added by shruti---18 april 2014
	public ArrayList getUpdatedCheckListItems(String[] itemnames,String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		
		String str="";
		try{
		    dbUtil = new DBUtility();
		    if(language.equalsIgnoreCase("en"))
		    {
			str=CommonSQL.GET_CHKLIST_ITEMS;
			StringBuilder squery = new StringBuilder(str);
			
			 for(int i=0;i<itemnames.length;i++)
				{
					if(i!=itemnames.length-1)
					squery.append("?,");
					else
						squery.append("?");
					
				}
			 str=squery.append(CommonSQL.GET_CHKLIST_ITEMS1).toString();
		    }
		    else
		    {
		    str=CommonSQL.GET_CHKLIST_ITEMS_HI;
		    StringBuilder squery = new StringBuilder(str);
			
			 for(int i=0;i<itemnames.length;i++)
				{
					if(i!=itemnames.length-1)
					squery.append("?,");
					else
						squery.append("?");
					
				}
			 str=squery.append(CommonSQL.GET_CHKLIST_ITEMS_HI1).toString();
		    }
		    
			 dbUtil.createPreparedStatement(str);
			listChkItems = dbUtil.executeQuery(itemnames);
			
		}
		catch(Exception ex){
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
		return listChkItems;
	}
	//end
	/**
	 * @param arrReportId
	 * @return
	 */
	public ArrayList getSelectedChkMap(String[] arrReportId,String langauge)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems     =  new ArrayList();
		
		try 
		{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(langauge))
			{	
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SELECTED_CHK_ITEMS);
			}
			else
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SELECTED_CHK_ITEMS_H);
			}
			listChkItems  =  dbUtil.executeQuery(arrReportId);
			
		}
		catch (Exception e) 
		{
			
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
		return  listChkItems;
	}
	
	/**
	 * @param arrReportId
	 * @return
	 */
	public ArrayList getSelectedChkMapOthers(String[] arrReportId,String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems     =  new ArrayList();
		//System.out.println("I am printign ghere "+arrReportId);
		//System.out.println("DAO chk others  Access >>>>>>> "+arrReportId[0]);
		
		try 
		{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language)){
				
			
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SELECTED_CHK_OTHERS_ITEMS);
			}
			else 
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SELECTED_CHK_OTHERS_ITEMS_H);
			}
			listChkItems  =  dbUtil.executeQuery(arrReportId);
			//System.out.println("Selected Chk Other Items in DAO "+listChkItems);
		}
		catch (Exception e) 
		{
			//System.out.println("catch this exception "+e);
			e.printStackTrace();
		}finally {
			try {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return  listChkItems;
	}
	
	/**
	 * @param reportId
	 * @return
	 */
	public ArrayList getSroReportDetails(String[] reportId,String langauge)
	{
		ArrayList listSroDetails  =  new ArrayList();
		DBUtility dbUtil  = null;
		try
		{
			dbUtil  =  new DBUtility();
			if("en".equalsIgnoreCase(langauge))
			{	
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SRO_DETAILS);
			}
			else
			{
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SRO_DETAILS_H);
			}
			listSroDetails = dbUtil.executeQuery(reportId);
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
		return listSroDetails;
	}
	
	/**
	 * @param reportId
	 * @return
	 */
	public ArrayList getInspectionIds(String[] reportId)
	{
		ArrayList listSroDto  =  new ArrayList();
		DBUtility dbUtil  = null;
		
		try
		{
			dbUtil  =  new DBUtility();
			TreeMap<String, String> paramsMap = new TreeMap<String, String>();
			String sql=CommonSQL.RETRIEVE_INSP_IDS;
			if(reportId[3]!=null)
			{
				paramsMap.put("1txnID", reportId[3]);
				sql=sql+" AND INSP.TRANSACTION_ID=?";
			}
			if(reportId[4]!=null)
			{
				paramsMap.put("2Status", reportId[4]);

				sql=sql+" AND UPPER(INSP.INSPECTION_STATUS)=?";
			}
			if(reportId[2]!=null)
			{
				sql=sql+" AND INSP.OFFICE_USER_ID = ?";
				paramsMap.put("3officeid", reportId[2]);
			}
			if(reportId[0]!=null && reportId[1]!=null)
			{
				sql=sql+" AND INSP.INSPECTION_START_DATE  BETWEEN ? AND ?";
				paramsMap.put("4fromDate", reportId[0]);
				paramsMap.put("5toDate", reportId[1]);
			}
			dbUtil.createPreparedStatement(sql);
			Collection<String> values = paramsMap.values();
			ArrayList<String> tmp = new ArrayList(values);
//			Collections.reverse(tmp);
			String[] params = tmp.toArray(new String[]{}); 
			listSroDto  =  dbUtil.executeQuery(params);
			/*if(reportId[3]!=null && reportId[0]==null && reportId[1]==null && reportId[2]==null && reportId[4]==null)
			{
			String [] param={reportId[3]}; 
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.TRANSACTION_ID=?");
			listSroDto  =  dbUtil.executeQuery(param);
			}
			else if(reportId[3]==null && reportId[0]==null && reportId[1]==null && reportId[2]==null && reportId[4]!=null)
			{
			String [] param={reportId[4]}; 
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" UPPER(INSP.INSPECTION_STATUS)=?");
			listSroDto  =  dbUtil.executeQuery(param);
			}
			else if(reportId[3]!=null && reportId[0]==null && reportId[1]==null && reportId[2]==null && reportId[4]!=null)
			{
				String [] param={reportId[3],reportId[4]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.TRANSACTION_ID=? AND UPPER(INSP.INSPECTION_STATUS)=?");
				listSroDto  =  dbUtil.executeQuery(param);
			}
			else if(reportId[3]==null && reportId[0]!=null && reportId[1]!=null && reportId[2]!=null && reportId[4]==null)
			{
				String [] param={reportId[0],reportId[1],reportId[2]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.OFFICE_USER_ID = ?");
				listSroDto  =  dbUtil.executeQuery(param);
			}
			else if(reportId[3]!=null && reportId[0]!=null && reportId[1]!=null && reportId[2]!=null && reportId[4]==null)
			{
				String [] param={reportId[0],reportId[1],reportId[2],reportId[3]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.OFFICE_USER_ID = ? AND INSP.TRANSACTION_ID=?");
				listSroDto  =  dbUtil.executeQuery(param);
			}
			else if(reportId[3]==null && reportId[0]!=null && reportId[1]!=null && reportId[2]!=null && reportId[4]!=null)
			{
				String [] param={reportId[0],reportId[1],reportId[2],reportId[4]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.OFFICE_USER_ID = ? AND UPPER(INSP.INSPECTION_STATUS)=?");
				listSroDto  =  dbUtil.executeQuery(param);
			}
			else
			{
				String [] param={reportId[0],reportId[1],reportId[2],reportId[4],reportId[3]}; 
				dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_INSP_IDS+" INSP.INSPECTION_START_DATE  BETWEEN ? AND ? AND INSP.OFFICE_USER_ID = ? AND UPPER(INSP.INSPECTION_STATUS)=? AND INSP.TRANSACTION_ID=?");
				listSroDto  =  dbUtil.executeQuery(param);
			}*/
			//	dbUtil.createStatement();
		//	listSroDto = dbUtil.executeQuery(CommonSQL.RETRIEVE_INSP_IDS);
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
		return listSroDto;
		
	}
	
	/**
	 * @param reportId
	 * @return
	 */
	public ArrayList getPendingItems(String[] reportId)
	{
		ArrayList listPening  =  new ArrayList();
		DBUtility dbUtil  = null;
		try
		{
			dbUtil  =  new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_PENDING_ITEMS);
			listPening = dbUtil.executeQuery(reportId);
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
		return listPening;
	}
	
	/**
	 * @return
	 */
	public ArrayList getOtherCheckListItems(String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		String item[] = new String[1];
		item[0]="N";
		try{
			 dbUtil = new DBUtility();
			 if(language.equalsIgnoreCase("en"))
			 {
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHKLIST_ITEMS);
			 }
			 else
			 {
				 dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_CHKLIST_ITEMS_HI);
			 }
			listChkItems = dbUtil.executeQuery(item);
			
		}
		catch(Exception ex){
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
		return listChkItems;
	}
	
	/**
	 * @param userid
	 * @return
	 */
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
			
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listEmpDetails;
	}
	
	/**
	 * @param arrReportId
	 * @return
	 */
	public ArrayList getEmpDtoList(String[] arrReportId)
	{
		DBUtility dbUtil = null;
		ArrayList listEmpDto   =  new ArrayList();
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRRIEVE_INSP_EMP_DETAILS);
			listEmpDto   = dbUtil.executeQuery(arrReportId);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
        {
        	try{
       	         dbUtil.closeConnection();
       	    
       	      }
       	    catch(Exception e){
       		     e.printStackTrace();
       	      }
        }
		return listEmpDto;
	}
	
	/**
	 * @param comments
	 * @return
	 */
	public boolean saveSroComments(String[] comments)
	{
		DBUtility dbUtil = null;
		boolean flag     = false;
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.INSERT_SRO_COMMENTS);
			dbUtil.executeUpdate(comments);
			flag = true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
        {
        	try{
       	         dbUtil.closeConnection();
       	      
       	      }
       	    catch(Exception e){
       		     e.printStackTrace();
       	      }
        }
		return flag;
	}
	
	/**
	 * @param listSroDetails
	 * @return
	 */
	public String saveSROReportDetails(ArrayList listSroDetails,String officeId)
	{
		boolean sroReport = false;
		DBUtility dbUtil = null;
		boolean sroDocMap = false;
		boolean sroComments = false;
		boolean sroEmpDetails = false;
		boolean sroPending  = false;
		boolean checkMap = false;
		
		String sroInspectionId       =    getInspectionSeqId(officeId);
		 
		String arrSroDetails[]       =   (String[])listSroDetails.get(0);
		       arrSroDetails[24]     =   sroInspectionId;
		       String arrSROcomments1[] =new String[4];
        String arrSroComments[]   =(String[])listSroDetails.get(1);
               arrSroComments[2]     =   sroInspectionId;
            //ADDED BY VINAY
               arrSROcomments1[0]=arrSroComments[0];
            arrSROcomments1[1]=arrSroComments[1];
            arrSROcomments1[2]=arrSroComments[2];
            arrSROcomments1[3]=getSrCommentId();
            // END
            ArrayList  listEmpDetails    =   (ArrayList)listSroDetails.get(2);
        ArrayList  listPendingItems  =   (ArrayList)listSroDetails.get(3);
        ArrayList  listCheckList     =   (ArrayList)listSroDetails.get(4);
        String     filesPath         =   (String)listSroDetails.get(5);
        ArrayList  listFileNames     =   (ArrayList)listSroDetails.get(6);
        
        try{
        	
        	dbUtil = new DBUtility();
    		dbUtil.setAutoCommit(false);
        	
        	// Saving SRO INSPECTION details
        	dbUtil.createPreparedStatement(CommonSQL.INSERT_SRO_INSP_DETAILS);
        	sroReport  = dbUtil.executeUpdate(arrSroDetails);
        	
        	if(sroReport){
            	// Saving DOC Mapping details
            	for(int i=0;i<listFileNames.size();i++)
            	{
            		String docTxnId     = getDocTxnId();
            		
            		UploadFileDTO uploadDto  =  (UploadFileDTO)listFileNames.get(i);
            		String filename     = uploadDto.getFileName();//(String)listFileNames.get(0);
            		String inspectionId = sroInspectionId;
            		String filePathLen=uploadDto.getFilePath();
            		//File newFile        = new File(filesPath+filename);
            		String path=uploadDto.getFilePath().substring(0, filePathLen.length()-(uploadDto.getFileName().length()+2)).concat(sroInspectionId.concat("\\"));
            	//	String path=filename.substring(0, filesPath.length()-2).concat("\\"+sroInspectionId);
            		//modified by shruti----29 th nov 2013
            		sroDocMap = dbUtil.saveSroDocumentsNew(docTxnId,inspectionId,filename,path);
            		
            		if(!sroDocMap){
            			sroReport=false;
            			break;
            		}
            	}	
        	}

        	if(sroReport){
            	//Saving Emp details 
            	for(int i=0;i<listEmpDetails.size();i++)
            	{
            		SROEmpDTO sroEmp = (SROEmpDTO)listEmpDetails.get(i);
            		String empId = sroEmp.getUserId();
            		String empDetails[]   =  new String[2];
            		       empDetails[0]  =  sroInspectionId;
            		       empDetails[1]  =  empId;
            		       
            		       dbUtil.createPreparedStatement(CommonSQL.INSERT_SRO_EMP_MAP);
            		       sroEmpDetails = dbUtil.executeUpdate(empDetails);
                   		if(!sroEmpDetails){
                			sroReport=false;
                			break;
                		}
            	}
        	}
        	
        	if(sroReport){
            	//Saving Inspection SRO Comments
            	
        		dbUtil.createPreparedStatement(CommonSQL.INSERT_SRO_COMMENTS);
            	sroReport = dbUtil.executeUpdate(arrSROcomments1);
        	}
        	
        	if(sroReport){
               	//Saving Pending Items details
            	for(int i=0;i<listPendingItems.size();i++)
            	{
            		String pendingItems[]               =  new String[5];
            		SROPendingItemsDTO sroPendingItem   =  (SROPendingItemsDTO)listPendingItems.get(i);
            		                   pendingItems[0]  =  sroInspectionId;
            		                   pendingItems[1]  =  sroPendingItem.getPendingItem();
            		                   pendingItems[2]  =  CommonUtil.getConvertedDate(sroPendingItem.getPendingFrom());
            		                   pendingItems[3]  =  sroPendingItem.getPendingTaskNo();
            		                   pendingItems[4]  =  sroPendingItem.getPendingComment();
            		                   dbUtil.createPreparedStatement(CommonSQL.INSERT_PENDING_ITEMS);
            		                   sroPending =  dbUtil.executeUpdate(pendingItems);
            	                   		if(!sroPending){
            	                			sroReport=false;
            	                			break;
            	                		}
            	}       		
        	}
        	
        	if(sroReport){
            	//Saving Checklist map details
            	for(int i=0;i<listCheckList.size();i++)
            	{
            		SROCheckMapDTO sroCheckDetails = (SROCheckMapDTO)listCheckList.get(i);
            		//String itemId                  =  getCheckItemId(sroCheckDetails.getItemName());
            		String checkItems[]            =  new String[3];  
            		       checkItems[0]           =  sroInspectionId;
            		String  checkItemId            =  getCheckItemId(sroCheckDetails.getItemId(),dbUtil);    
            		if(checkItemId==null){
            			throw new Exception("Can bot Insert Null");
            		}
    		       checkItems[1]           =  checkItemId;
    		       checkItems[2]           =  sroCheckDetails.getItemRemarks();
    		       dbUtil.createPreparedStatement(CommonSQL.INSERT_CHECK_MAP_DETAILS);
    		       checkMap = dbUtil.executeUpdate(checkItems);
    		       if(!checkMap){
    		    	   sroReport =false;
    		    	   break;
    		       }
            	}
        	}
        	
        	if(sroReport){
        		dbUtil.commit();
        	}
        	else{
        		 dbUtil.rollback();
        		 sroInspectionId= null;
        	}
        
        	
        }
        catch(Exception ex){
        	sroInspectionId = null;
        	try{
        	     dbUtil.rollback();
        	    
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        	ex.printStackTrace();
        }
        finally
        {
        	try{
       	         dbUtil.closeConnection();


       	      }
       	    catch(Exception e){
       		     e.printStackTrace();
       	      }
        }
        
		return sroInspectionId;
	}
	
	/**
	 * @return
	 */
	private String getInspectionSeqId(String officeId)
	{
		String reportId = null;
		ArrayList listSeq = new ArrayList();
		DBUtility dbUtil = null;
		
		try{
			dbUtil=new DBUtility();
			dbUtil.createStatement();
			listSeq  = dbUtil.executeQuery(CommonSQL.RETRIEVE_SRO_INSP_SEQ);
			ArrayList rowList = (ArrayList)listSeq.get(0);
			rowList     =   (ArrayList)listSeq.get(0);
			reportId    =   (String)rowList.get(0);
			//modified by shruti---2 march 2014
			//String sqlCurrentdate=CommonSQL.GETCURRENTDATE;
			String sqlCurrentdate=CommonSQL.GETCURRENTDATENEWFORMAT;
			
            dbUtil.createStatement();
            String currentDate=dbUtil.executeQry(sqlCurrentdate);     
            String district=getDistrictId(officeId);
            //MODIFIED BY SHRUTI--1 MARCH 2013 AS PER FUNCTIONAL TRACKER
            reportId="2"+district+currentDate+reportId;
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
		return reportId;
	}
	
	/**
	 * @return
	 */
	private String getDocTxnId()
	{
		String docTxn = null;
		ArrayList listSeq = new ArrayList();
		DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
		    dbUtil.createStatement();
		    listSeq  =  dbUtil.executeQuery(CommonSQL.RETRIEVE_INSP_DOC_MAP_SEQ);
		    ArrayList rowList = (ArrayList)listSeq.get(0);
		    docTxn            = (String)rowList.get(0);
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
		
		return docTxn;
	}
	
	public ArrayList getSroComments(String[] arr)
	{
		ArrayList listComm   =   new  ArrayList();
		DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_SRO_COMMENTS);
			listComm  = dbUtil.executeQuery(arr);
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
		return listComm;
	}

	public ArrayList getDocumentNames(String[] arr)
	{
		ArrayList list   =   new ArrayList();
		DBUtility dbUtil=null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_DOC_NAME);
			list  = dbUtil.executeQuery(arr);
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
		
		return list;
	}
	
	private String getCheckItemId(String strItemName,DBUtility dbUtil)
	{
		String itemId = null;
		String itemName[] = new String[1];
		       itemName[0] = strItemName;
		       //itemName[1]=strItemName;
		ArrayList listName = new ArrayList();
		
		try{
		    //DBUtility dbUtil = new DBUtility();
		    dbUtil.createPreparedStatement(CommonSQL.RETRIEVE_ITEM_ID);
		    listName = dbUtil.executeQuery(itemName);
		    if(listName!= null){
			    ArrayList rowList = (ArrayList)listName.get(0);
			    itemId            = (String)rowList.get(0);
		    }
		    else
		    {
		    	return itemId;
		    }
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
//FINALLY BLOCK REMOVED DUE TO TRANSACTION COMPLETION ISSUE----12 MAY 2014--SHRUTI
		return itemId;
	}
    
	private String getSrCommentId()
	{
		String commentId = null;
		DBUtility dbUtil=null;
		try{
		    dbUtil = new DBUtility();
		    dbUtil.createStatement();
		   commentId = dbUtil.executeQry(CommonSQL.GETCOMMNTID);
		    
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
		return commentId;
	}
   


// added by vinay

private String getDistrictId(String officeId)
	{
	DBUtility dbUtil=null;
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

public String getOfficeTypeId(String officeId)
{
	DBUtility dbUtil =null;
	String officeTypeId = null;
	String arr[]={officeId};
	try{
    dbUtil = new DBUtility();
    dbUtil.createPreparedStatement(CommonSQL.GETOFFICETYPEID);
    officeTypeId = dbUtil.executeQry(arr);
}
	catch(Exception ex)
	{
	ex.printStackTrace();
	}finally {
		try {
			if (dbUtil != null) {
				dbUtil.closeConnection();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	return officeTypeId;
}
public ArrayList getAllSRO(String language)
{
	DBUtility dbUtil  = null;
    ArrayList list=null;
    String SQL = "";
    if(language.equalsIgnoreCase("en"))
    {
    SQL=CommonSQL.GETALLSRO;
    }
    else
    {
    	SQL=CommonSQL.GETALLSRO_HI;
    }
    try{
    	  dbUtil = new DBUtility();
    	 dbUtil.createStatement();
        list=dbUtil.executeQuery(SQL);
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
//end
public ArrayList getLoggedInSRO(String OffId,String language)
{
	DBUtility dbUtil  = null;
    ArrayList list=null;
    String SQL = "";
    if(language.equalsIgnoreCase("en"))
    {
    SQL=CommonSQL.GETLOGGEDINSRO;
    }
    else
    {
    	SQL=CommonSQL.GETLOGGEDINSRO_H;
    }
    String arry[] = new String[1];
    if (OffId != null) {
        arry[0] = OffId;
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
public ArrayList getSROForDIG(String language,String OffId)
{
	DBUtility dbUtil  = null;
    ArrayList list=null;
    String SQL = "";
    if(language.equalsIgnoreCase("en"))
    {
    SQL=CommonSQL.GETSROFORDIG;
    }
    else
    {
    	SQL=CommonSQL.GETSROFORDIG_H;
    }
    String arry[] = new String[1];
    if (OffId != null) {
        arry[0] = OffId;
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
}

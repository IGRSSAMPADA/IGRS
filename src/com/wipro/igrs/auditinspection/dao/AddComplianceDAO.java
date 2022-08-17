package com.wipro.igrs.auditinspection.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.auditinspection.dto.PublicDTO;
import com.wipro.igrs.auditinspection.sql.AddComplianceSQL;
import com.wipro.igrs.auditinspection.sql.CommonSQL;
import com.wipro.igrs.auditinspection.sql.PublicOfficeInspectionSQL;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.login.action.LoginAction;

public class AddComplianceDAO {
	private Logger logger = (Logger) Logger.getLogger(AddComplianceDAO.class);
	//DBUtility dbUtil = null;
	
	public String getRole(String officeid)
	{
		String list=null;
		DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={officeid.toUpperCase()};
			 String str=AddComplianceSQL.GET_ROLE;
			 dbUtil.createPreparedStatement(str);
			 list = dbUtil.executeQry(param);
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
	public ArrayList getSrDashboard(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_SR_DASHBOARD;
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
	public ArrayList getDroDashboardDR(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_DRO_DASHBOARD_DR;
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

	public ArrayList getSroDashboardDR(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 //MODIFIED BY SHRUTI--28TH NOV 2013
			 String[] param={officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_SRO_DASHBOARD_DR;
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
	public ArrayList getSroDashboardDIG(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 //modified by shruti
			 String[] param={officeId.toUpperCase(),officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_SRO_DASHBOARD_DIG;
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
	public ArrayList getDroDashboardDIG(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 String[] param={officeId.toUpperCase(),officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_DRO_DASHBOARD_DIG;
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
	public ArrayList getSroDashboardIG(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 //String[] param={officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_SRO_DASHBOARD_IG;
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery(str);
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
	public ArrayList getDroDashboardIG(String officeId)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			 //String[] param={officeId.toUpperCase()};
			 String str=AddComplianceSQL.GET_DRO_DASHBOARD_IG;
			 dbUtil.createStatement();
			 list = dbUtil.executeQuery(str);
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
	public ArrayList getCheckListItems(String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		String item[] = new String[1];
		item[0]="Y";
		try{
		    dbUtil = new DBUtility();
			
		    if("en".equalsIgnoreCase(language))
		    {	
		    dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_CHKLIST_ITEMS);
		    }
		    else
		    {
		    dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_CHKLIST_ITEMS_H);
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
	public ArrayList getOtherCheckListItems(String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems = new ArrayList();
		String item[] = new String[1];
		item[0]="N";
		try{
			 dbUtil = new DBUtility();
			 if("en".equalsIgnoreCase(language))
			 {	 
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_CHKLIST_ITEMS);
			 }
			 else
			 {
				 dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_CHKLIST_ITEMS_H);
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
	public ArrayList getPendingItems(String[] reportId)
	{
		ArrayList listPening  =  new ArrayList();
		DBUtility dbUtil  = null;
		try
		{
			dbUtil  =  new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_PENDING_ITEMS);
			listPening = dbUtil.executeQuery(reportId);
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
		return listPening;
	}
	public ArrayList getEmpDtoList(String[] arrReportId)
	{
		DBUtility dbUtil = null;
		ArrayList listEmpDto   =  new ArrayList();
		try
		{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRRIEVE_INSP_EMP_DETAILS);
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
	
	public ArrayList getSroReportDetails(String[] reportId,String langauge)
	{
		ArrayList listSroDetails  =  new ArrayList();
		DBUtility dbUtil  = null;
		try
		{
			dbUtil  =  new DBUtility();
			if("en".equalsIgnoreCase(langauge))
			{
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SRO_DETAILS);
			}
			else
			{
				dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SRO_DETAILS_H);
			}
			listSroDetails = dbUtil.executeQuery(reportId);
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
		return listSroDetails;
	}
	public ArrayList getSroComments(String[] arr)
	{
		ArrayList listComm   =   new  ArrayList();
		DBUtility dbUtil = null;
		try{
			 dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SRO_COMMENTS);
			listComm  = dbUtil.executeQuery(arr);
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
		return listComm;
	}
	public ArrayList getDocumentNames(String[] arr)
	{
		ArrayList list   =   new ArrayList();
		DBUtility dbUtil = null;
		try{
			 dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DOC_NAME);
			list  = dbUtil.executeQuery(arr);
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
		
		return list;
	}
	public ArrayList getSelectedChkMap(String[] arrReportId,String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems     =  new ArrayList();
		
		try 
		{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{	
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SELECTED_CHK_ITEMS);
			}
			else
			{
				dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SELECTED_CHK_ITEMS_H);	
			}
			listChkItems  =  dbUtil.executeQuery(arrReportId);
			
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
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
		return  listChkItems;
	}
	public ArrayList getSelectedChkMapOthers(String[] arrReportId,String language)
	{
		DBUtility dbUtil  = null;
		ArrayList listChkItems     =  new ArrayList();
		//System.out.println("I am printign ghere "+arrReportId);
		//System.out.println("DAO chk others  Access >>>>>>> "+arrReportId[0]);
		
		try 
		{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(language))
			{	
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SELECTED_CHK_OTHERS_ITEMS);
			}
			else
			{
				dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_SELECTED_CHK_OTHERS_ITEMS_H);
			}
			listChkItems  =  dbUtil.executeQuery(arrReportId);
			//System.out.println("Selected Chk Other Items in DAO "+listChkItems);
		}
		catch (Exception e) 
		{
			//System.out.println("catch this exception "+e);
			e.printStackTrace();
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
		return  listChkItems;
	}
	public ArrayList getDROInspDetails(String[] droInsp,String langauge){
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			if("en".equalsIgnoreCase(langauge))
			{	
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DRO_INSP_DETAILS);
			}
			else
			{
				dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DRO_INSP_DETAILS_H);
			}
			listFromDb = dbUtil.executeQuery(droInsp);
		}
		catch(Exception ex){
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
		return listFromDb;
	}
	public ArrayList getDROEmpDetails(String[] insp){
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DRO_EMP_DETAILS);
			listFromDb = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
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
		return listFromDb;
	}
	public ArrayList getReasonMapDetails(String[] insp)
	{
		DBUtility dbUtil=null;
		ArrayList listFromDb = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DRO_REASON_DETAILS);
			listFromDb = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
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
		return listFromDb;
	}
	public ArrayList getDocNames(String[] insp){
		DBUtility  dbUtil = null;
		ArrayList listDB = null;
		try{
			dbUtil = new DBUtility();
			dbUtil.createPreparedStatement(AddComplianceSQL.RETRIEVE_DRO_DOCUMENTS);
			listDB = dbUtil.executeQuery(insp);
		}
		catch(Exception ex){
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
		return listDB;
	}
	public boolean updateSROCompliance(PublicDTO pdto)
	{
		DBUtility  dbUtil = null;
		boolean flag = false;
		try{
			String query="";
			dbUtil = new DBUtility();
			String arr[]=new String[2];
			arr[1]=pdto.getTxnId();
			if("1".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getSrCompliance();
				query=AddComplianceSQL.UPDATE_SRO_SR_COMPLIANCE;
			}
			else if("2".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getDrCompliance();
				query=AddComplianceSQL.UPDATE_SRO_DR_COMPLIANCE;
			}
			else if("3".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getDigCompliance();
				query=AddComplianceSQL.UPDATE_SRO_DIG_COMPLIANCE;
			}
			else if("4".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getIgCompliance();
				query=AddComplianceSQL.UPDATE_SRO_IG_COMPLIANCE;
			}
			dbUtil.createPreparedStatement(query);
			flag = dbUtil.executeUpdate(arr);
		}
		catch(Exception ex){
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
	public boolean updateDROCompliance(PublicDTO pdto)
	{
		DBUtility  dbUtil = null;
		boolean flag = false;
		try{
			String query="";
			dbUtil = new DBUtility();
			String arr[]=new String[2];
			arr[1]=pdto.getTxnId();
			if("1".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getDrCompliance();
				query=AddComplianceSQL.UPDATE_DRO_DR_COMPLIANCE;
			}
			else if("2".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getDigCompliance();
				query=AddComplianceSQL.UPDATE_DRO_DIG_COMPLIANCE;
			}
			else if("3".equalsIgnoreCase(pdto.getComplianceFlag()))
			{
				arr[0]=pdto.getIgCompliance();
				query=AddComplianceSQL.UPDATE_DRO_IG_COMPLIANCE;
			}
			dbUtil.createPreparedStatement(query);
			flag = dbUtil.executeUpdate(arr);
		}
		catch(Exception ex){
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
	public ArrayList getSrDashboardSearch(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SR_DASHBOARD+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={officeId.toUpperCase(),pdto.getReportId()};
				String str=AddComplianceSQL.GET_SR_DASHBOARD+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SR_DASHBOARD+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getSroDashboardSearchDR(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DR+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={officeId.toUpperCase(),pdto.getReportId()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DR+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DR+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getDroDashboardSearchDR(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DR+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={officeId.toUpperCase(),pdto.getReportId()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DR+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DR+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getSroDashboardSearchDIG(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DIG+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getReportId()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DIG+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_DIG+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getDroDashboardSearchDIG(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DIG+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getReportId()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DIG+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={officeId.toUpperCase(),officeId.toUpperCase(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_DIG+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getSroDashboardSearchIG(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_IG+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={pdto.getReportId()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_IG+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_SRO_DASHBOARD_IG+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
	public ArrayList getDroDashboardSearchIG(String officeId,PublicDTO pdto)
	{
		ArrayList list=null;DBUtility dbUtil = null;
		try{
			 dbUtil=new DBUtility();
			
			if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase("")&&pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={pdto.getReportId(),pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_IG+" AND DET.TRANSACTION_ID=? AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			else if(pdto.getReportId()!=null&&!pdto.getReportId().equalsIgnoreCase(""))
			{
				String[] param={pdto.getReportId()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_IG+" AND DET.TRANSACTION_ID=? ";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			if(pdto.getFromDate()!=null&&!pdto.getFromDate().equalsIgnoreCase("")&&pdto.getToDate()!=null&&!pdto.getToDate().equalsIgnoreCase("")){
				 String[] param={pdto.getFromDate(),pdto.getToDate()};
				String str=AddComplianceSQL.GET_DRO_DASHBOARD_IG+"  AND DET.INSPECTION_ENTRY_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";
				 dbUtil.createPreparedStatement(str);
				 list = dbUtil.executeQuery(param);
			}
			
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
}

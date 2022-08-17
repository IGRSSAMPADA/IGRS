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
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.caseMonitoring.form.CaseMonHistoryForm;
import com.wipro.igrs.caseMonitoring.sql.CaseMonHistorySQL;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.util.GUIDGenerator;

public class CaseMonHistoryDAO {
	//DBUtility dbUtility = null;

	private Logger logger = (Logger) Logger.getLogger(CaseMonDAO.class);
	CaseMonHistorySQL caseSQL=new CaseMonHistorySQL();
	public CaseMonHistoryDAO() {

	}
	
	public ArrayList getCaseSectionList(String language)
	{
		DBUtility dbUtil=null;
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
			list=dbUtil.executeQuery(caseSQL.GETCASESECTONLIST);
			}
			else
			{
				list=dbUtil.executeQuery(caseSQL.GETCASESECTONLIST_H);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}
	
	public ArrayList getCaseStatusList(String language)
	{
		DBUtility dbUtil=null;
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createStatement();
			if("en".equalsIgnoreCase(language))
			{
			list=dbUtil.executeQuery(caseSQL.GETCASESTATUSLIST);
			}
			else
			{
				list=dbUtil.executeQuery(caseSQL.GETCASESTATUSLIST_H);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}
	
	public ArrayList getCaseDivisionList(String language)
	{
		DBUtility dbUtil=null;
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createStatement();
			if("en".equals(language))
			{
			list=dbUtil.executeQuery(caseSQL.GETCASEDIVISIONLIST);
			}
			else
			{
				list=dbUtil.executeQuery(caseSQL.GETCASEDIVISIONLIST_H);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  list;
	}
	
	public CaseMonHistoryDTO saveCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		DBUtility dbUtil=null;
		String[] param=new String[16];
		CaseMonHistoryDAO caseDAO=new CaseMonHistoryDAO();
		dto.setReferenceNo(caseDAO.getReferenceNo());
		logger.info("refrence number generated>>>"+dto.getReferenceNo());
		param[0]=dto.getReferenceNo();
		param[1]=dto.getCaseType();
		param[2]=dto.getSectionId();
		param[3]=dto.getCaseNumber();
		param[4]=dto.getPartyDtls();
		param[5]=dto.getPropDtls();
		param[6]=dto.getStampAmt();
		param[7]=dto.getStampDuty();
		param[8]=dto.getRegFee();
		param[9]=dto.getRecStampDuty();
		param[10]=dto.getRecRegFee();
		param[11]=dto.getCaseStatusId();
		param[12]=dto.getDivisionId();
		if(dto.getRrcAmt()==null)
		{
			dto.setRrcAmt("0");
			param[13]=dto.getRrcAmt();
		}
		else
		{
		param[13]=dto.getRrcAmt();
		}
		param[14]=dto.getRemarks();
		param[15]=dto.getLoggedInUser();
		
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createPreparedStatement(caseSQL.SAVECASEDATA);
			boolean flag=dbUtil.executeUpdate(param);
			if(flag==true)
			{
			dbUtil.commit();
			
			//--added by satbir kumar--
			String[] param1=new String[5];
			
			  ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
			
			 CaseMonHistoryDTO download =null;
			 
			 for(int i=0;i<listDto.size();i++)
			 {
				 CaseMonHistoryDTO dto1 = listDto.get(i);
				 
				 download = dto1;
				 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				 
				 String RefId=dto.getReferenceNo();
				 String str = GUIDGenerator.getGUID();
				 String docPath = pr.getValue("igrs_upload_path")+File.separator+"UPLOAD/27/"+RefId+"/"+str;
				 
				 boolean up=uploadFile(dto1.getFile(),dto1.getDocumentName(),docPath);
					
					//dbUtil	=new DBUtility();
					dbUtil.createStatement();
					String srno=dbUtil.executeQry(caseSQL.GETUPLOADSRNO);
					
					
					
					param1[0]=srno;
					param1[1]=dto.getReferenceNo();
					param1[2]=dto1.getDocumentName();
					param1[3]=docPath;
					param1[4]=dto.getLoggedInUser();
					
					//dbUtil	=new DBUtility();
					dbUtil.createPreparedStatement(caseSQL.INSERTDOCDETLS);
					boolean flag1=dbUtil.executeUpdate(param1);
					
					if(flag==true && up==true)
					{
						dbUtil.commit();
					}
					
					else
					{
						dbUtil.rollback();
					}
				 
			}
			}
			else
			{dbUtil.rollback();}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  dto;
	}

	public String getReferenceNo()
	{
		DBUtility dbUtil=null;
		String datepart="";
		String serialpart="";
		String referenceno="";
		try
		{
			dbUtil	=new DBUtility();
		dbUtil.createStatement();
		datepart=dbUtil.executeQry(caseSQL.GETDATEDATA);
		
		dbUtil.createStatement();
		serialpart=dbUtil.executeQry(caseSQL.GETSERIALNUMBER);
		
			referenceno=datepart+serialpart;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  referenceno;
	}
	public ArrayList searchCaseData(CaseMonHistoryDTO dto)
	{
		DBUtility dbUtil=null;
		String[] param=new String[1];
		param[0]=dto.getReferenceNo();
		
		String[] param1=new String[2];
		param1[0]=dto.getFromDate();
		param1[1]=dto.getToDate();
		
		String[] param3=new String[3];
		param3[0]=dto.getReferenceNo();
		param3[1]=dto.getFromDate();
		param3[2]=dto.getToDate();
		
		ArrayList list=new ArrayList();
		try
		{
			dbUtil	=new DBUtility();
			if(dto.getReferenceNo()!=null &&( dto.getFromDate().equals("") || dto.getToDate().equals("")))
			{
				dbUtil.createPreparedStatement(caseSQL.REFRENCENOBASEDSEARCH);
				list=dbUtil.executeQuery(param);
			}
			else if((dto.getFromDate()!=null && dto.getToDate()!=null)&& dto.getReferenceNo().equalsIgnoreCase(""))
			{
				dbUtil.createPreparedStatement(caseSQL.DURATIONBASEDSEARCH);
				list=dbUtil.executeQuery(param1);	
			}
			else
			{
				dbUtil.createPreparedStatement(caseSQL.MIXEDSEARCH);
				list=dbUtil.executeQuery(param3);	
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return  list;
	}

	public boolean updateCaseData(CaseMonHistoryDTO dto, CaseMonHistoryForm caseForm)
	{
		DBUtility dbUtil=null;
		String[] param=new String[15];
		
		
		param[0]=dto.getSectionId();
		param[1]=dto.getCaseNumber();
		param[2]=dto.getPartyDtls();
		param[3]=dto.getPropDtls();
		param[4]=dto.getStampAmt();
		param[5]=dto.getStampDuty();
		param[6]=dto.getRegFee();
		param[7]=dto.getRecStampDuty();
		param[8]=dto.getRecRegFee();
		param[9]=dto.getCaseStatusId();
		param[10]=dto.getDivisionId();
		if(dto.getRrcAmt()==null)
		{
			dto.setRrcAmt("0");
			param[11]=dto.getRrcAmt();
		}
		else
		{
		param[11]=dto.getRrcAmt();
		}
		param[12]=dto.getRemarks();
		param[13]=dto.getLoggedInUser();
		param[14]=dto.getReferenceNo();
		
		ArrayList list=new ArrayList();
		boolean flag=false;
		try
		{
			dbUtil	=new DBUtility();
			dbUtil.createPreparedStatement(caseSQL.UPDATECASEDATA);
			flag=dbUtil.executeUpdate(param);
			if(flag==true)
			{dbUtil.commit();
			
			
			String[] param1=new String[5];
			
			  ArrayList<CaseMonHistoryDTO> listDto = caseForm.getUploadList();
			
			 CaseMonHistoryDTO download =null;
			 
			 for(int i=0;i<listDto.size();i++)
			 {
				 CaseMonHistoryDTO dto1 = listDto.get(i);
				 
				 download = dto1;
				
				 
				 if(download.getDbaseUpload()!=null && download.getDbaseUpload().equalsIgnoreCase("D"))
				 {
					 
				 }
				 
				 else
				 {
					 PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
					 
					 String RefId=dto.getReferenceNo();
					 String str = GUIDGenerator.getGUID();
					 String docPath = pr.getValue("igrs_upload_path")+File.separator+"UPLOAD/27/"+RefId+"/"+str;
					 
				 boolean up=uploadFile(dto1.getFile(),dto1.getDocumentName(),docPath);
					
					//dbUtil	=new DBUtility();
					dbUtil.createStatement();
					String srno=dbUtil.executeQry(caseSQL.GETUPLOADSRNO);
					
					
					
					param1[0]=srno;
					param1[1]=dto.getReferenceNo();
					param1[2]=dto1.getDocumentName();
					param1[3]=docPath;
					param1[4]=dto.getLoggedInUser();
					
					//dbUtil	=new DBUtility();
					dbUtil.createPreparedStatement(caseSQL.INSERTDOCDETLS);
					boolean flag1=dbUtil.executeUpdate(param1);
					
					if(flag==true && up==true)
					{
						dbUtil.commit();
					}
					
					else
					{
						dbUtil.rollback();
					}
				 }
			}
			
			
			
			
			}
			else
			{dbUtil.rollback();}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return  flag;
	}

	public ArrayList viewCaseData(CaseMonHistoryDTO dto)
	{
		DBUtility dbUtil=null;
		String[] param=new String[1];
		param[0]=dto.getReferenceNo();
		ArrayList list=new ArrayList();
		try
		{	
				dbUtil	=new DBUtility();
				dbUtil.createPreparedStatement(caseSQL.REFRENCENOBASEDSEARCH);
				list=dbUtil.executeQuery(param);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  list;
	}
	 private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {
		 
	        
	        
	        try {
	              File folder = new File(filePath);
	              if (!folder.exists()) {
	              folder.mkdirs();
	               }
	              
	                    File newFile = new File(filePath, fileName);
	                   // logger.info("NEW FILE NAME:-" + newFile);
	                    FileOutputStream fos = new FileOutputStream(newFile);
	                    fos.write(filetobeuploaded.getFileData());
	                    fos.close();
	              

	        } catch (Exception ex) {
	              ex.printStackTrace();
	        }
	        return true;
	  }

	public ArrayList getuploadUrlList(CaseMonHistoryDTO caseDTO) {
		
		DBUtility dbUtil=null;
		String[] param=new String[1];
		param[0]=caseDTO.getReferenceNo();
		ArrayList list=new ArrayList();
		try
		{	
				dbUtil	=new DBUtility();
				dbUtil.createPreparedStatement(caseSQL.GETSAVEDUPLOADS);
				list=dbUtil.executeQuery(param);		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  list;
	}

	public Boolean getremoveUpload(CaseMonHistoryDTO caseDTO) {
		
		DBUtility dbUtil=null;
		String[] param=new String[2];
		param[0]=caseDTO.getUploadSrNo();
		param[1]=caseDTO.getReferenceNo();
		Boolean flag=false;
		try
		{	
				dbUtil	=new DBUtility();
				dbUtil.createPreparedStatement(caseSQL.REMOVESAVEDUPLOADS);
				flag=dbUtil.executeUpdate(param);	
				
				if(flag==true)
				{dbUtil.commit();
				logger.debug("Upload removed");
				}
				
				else
				{dbUtil.rollback();
				logger.debug("Upload removal failed");
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				dbUtil.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  flag;
		
	}


}// Dao End

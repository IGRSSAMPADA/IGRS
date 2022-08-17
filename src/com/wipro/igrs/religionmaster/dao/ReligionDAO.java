/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionDAO.java
 * Author      :   Sara Hussain 
 * Description :   Represents the Data Access Object Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */


package com.wipro.igrs.religionmaster.dao;

import java.util.ArrayList;
import java.util.List;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.form.ReligionForm;
import com.wipro.igrs.religionmaster.sql.ReligionCommonSQL;

public class ReligionDAO {
	
	public static void main(String [] args)
	{
		ReligionDAO dao=new ReligionDAO();
		try {
			
			ReligionDTO dto=new ReligionDTO();
			//dto.setReligionId("ACT6");
			dto.setReligionName("Test5");
			dto.setReligionDesc("religion test");
			//dto.setCreatedBy("test");
			//dto.setReligionStatus("D");
			
			//dao.addReligion(dto);
			//dao.updateReligionMaster(dto);
			//List allReligions=dao.getReligionList();
			
			//dto=dao.getReligionId("ACT6");
			//System.out.println("##"+dto.getReligionName());
			//for (int i = 0; i < allReligions.size(); i++) {
				//System.out.println("##"+((ReligionDTO)allReligions.get(i)).getReligionId());
			//}
			
			
			if(dao.isExist(dto))
				System.out.println("#already exist#");
			else
				System.out.println("#new name#");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ArrayList religionList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	ReligionDTO dto = null;
	String activityID = null;
	//private Logger logger = (Logger) Logger.getLogger(ActivityDAO.class);
	
	public ReligionDTO getReligionId(String religionId) throws Exception
	{
		try
		{
			dbUtility=new DBUtility();
			sql=ReligionCommonSQL.SELECT_Religion_MASTER_ID;
			dbUtility.createPreparedStatement(sql);
	    	String sd[] = new String[1];
	    	sd[0]=religionId;
			ArrayList list=dbUtility.executeQuery(sd);
			ArrayList result=(ArrayList)list.get(0);
			dto=new ReligionDTO();
			if(result.get(0)==null)
				dto.setReligionId("");
			else
				dto.setReligionId(result.get(0).toString());
			if(result.get(1)==null)
				dto.setReligionId("");
			else
				dto.setReligionName(result.get(1).toString());
			if(result.get(2)==null)
				dto.setReligionId("");
			else
				dto.setReligionDesc(result.get(2).toString());
			if(result.get(3)==null)
				dto.setReligionId("");
			else
				dto.setReligionStatus(result.get(3).toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbUtility.closeConnection();
		}
		return dto;
	}
	
    public ArrayList getReligionList() throws Exception {
        
    	dbUtility = new DBUtility();
    	try {
        	 
        	sql = ReligionCommonSQL.SELECT_Religion_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new ReligionDTO();
    			if(subList.get(0)==null)
    				dto.setReligionId("");
    			else
    				dto.setReligionId(subList.get(0).toString());
    			if(subList.get(1)==null)
    				dto.setReligionId("");
    			else
    				dto.setReligionName(subList.get(1).toString());
    			if(subList.get(2)==null)
    				dto.setReligionId("");
    			else
    				dto.setReligionDesc(subList.get(2).toString());
    			if(subList.get(3)==null)
    				dto.setReligionId("");
    			else
    				dto.setReligionStatus(subList.get(3).toString());
    			if(subList.get(4)==null)
    				dto.setReligionId("");
    			else
    				dto.setCreatedBy(subList.get(4).toString());
    			if(subList.get(5)==null)
    				dto.setReligionId("");
    			else
    				dto.setCreatedDate(subList.get(5).toString());
    			if(subList.get(6)==null)
    				dto.setReligionId("");
    			else
    				dto.setUpdateBy(subList.get(6).toString());
    			if(subList.get(7)==null)
    				dto.setReligionId("");
    			else
    				dto.setUpdateDate(subList.get(7).toString());
                religionList.add(dto);
              }
            } catch (Exception e) {
              e.getStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
        return religionList;
    }
	
	public void addReligion(ReligionDTO religionDTO, String roleId, String funId, String userId) throws Exception
	{
		String param[] = new String[3];
		param[0]=religionDTO.getReligionName();
		param[1]=religionDTO.getReligionDesc();
		param[2]=religionDTO.getCreatedBy();
		sql=ReligionCommonSQL.INSERT_Religion_MASTER;
		try
		{
			dbUtility=new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon(); 
			dbUtility.createPreparedStatement(sql);
			boolean check=dbUtility.executeUpdate(param);
			if(check)
			{
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_RELIGION_MASTER","INSERT","T",funId,userId,roleId);
			}
			else
			{
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_RELIGION_MASTER","INSERT","F",funId,userId,roleId);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			dbUtility.closeConnection();
		}
	}
	
	 public void updateReligionMaster(ReligionDTO religionDTO,String roleId, String funId, String userId)
	    {
	    	String param[] = new String[5];
	    	param[0] = religionDTO.getReligionName();
	    	param[1] = religionDTO.getReligionDesc();
	    	param[2] = religionDTO.getReligionStatus();
	    	param[3] = religionDTO.getUpdateBy();
	    	param[4]=religionDTO.getReligionId();
	    	sql=ReligionCommonSQL.UPDATE_Religion_MASTER; 
	    	      
	    try {
	    	IGRSCommon igrsCommon = new IGRSCommon();
	    	dbUtility = new DBUtility();
	        dbUtility.createPreparedStatement(sql);
	        boolean boo = dbUtility.executeUpdate(param);
	        if (boo){
	        	dbUtility.commit();
	        	igrsCommon.saveLogDet("IGRS_RELIGION_MASTER","UPDATE","T",funId,userId,roleId);
	        	
	         }    
	        else
	        {
	        	//System.out.println("dto"+religionDTO.getReligionDesc());
	            dbUtility.rollback();
	            igrsCommon.saveLogDet("IGRS_RELIGION_MASTER","UPDATE","F",funId,userId,roleId);
	        }
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	finally {
	    		try {
	    			dbUtility.closeConnection();
	    		} catch (Exception e) {
	    			e.printStackTrace();
	        	}
	        
	   	 	}
	    }
	 public boolean isExist(ReligionDTO religionDTO)
	 {
		 boolean exist=false;
		 try {
			dbUtility=new DBUtility();
			sql=ReligionCommonSQL.SELECT_Religion_MASTER_Name;
			dbUtility.createPreparedStatement(sql);
	    	String sd[] = new String[1];
	    	sd[0]=religionDTO.getReligionName();
	    	ArrayList list=dbUtility.executeQuery(sd);
	    	
	    	if(!list.isEmpty())
	    		exist=true;

			} 
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return exist;
	 }

}

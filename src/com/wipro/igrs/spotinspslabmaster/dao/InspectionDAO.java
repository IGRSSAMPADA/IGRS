/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2008-09
 *==============================================================================
 *
 * File Name   :   InspectionBD.java
 * Author      :   Yousry Ibrahem 
 * Description :   Represents the DAO Class for  Spot Inspection Slab master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.spotinspslabmaster.dao;


import java.util.ArrayList;
import java.util.Vector;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;
import com.wipro.igrs.spotinspslabmaster.sql.InspectioncreateCommonSQL;
import com.wipro.igrs.util.CommonUtil;



public class InspectionDAO {
	
	
	public InspectionDAO()
	{

	}
	/**
	 * 
	 * @param language 
	 * @return
	 */
	public Vector getAllInspections(String language)
	{
		InspectionDTO insDTO;
		Vector insVector=new Vector();
		ArrayList newIns;
		DBUtility utility=null;
		try
		   {
				String sql=InspectioncreateCommonSQL.SELECT_ALL_INSPECTIONS;
				utility=new DBUtility();
			
				utility.createStatement();
			
				ArrayList allIns=utility.executeQuery(sql);
			
				for(int i=0;i<allIns.size();i++)
				{					
					newIns=(ArrayList)allIns.get(i);
					insDTO=new InspectionDTO();				
					insDTO.setSlabID(newIns.get(0).toString());
					insDTO.setSlabMinRange((String)newIns.get(1));
					insDTO.setSlabMaxRange((String)newIns.get(2));
					insDTO.setStatus((String)newIns.get(3));
					if("A".equalsIgnoreCase((String)newIns.get(3)))
					{
						if(language.equalsIgnoreCase("en"))
						insDTO.setStatusDesc("Active");
						else
							insDTO.setStatusDesc("निष्क्रिय");
					}
					insDTO.setCreatedBy((String)newIns.get(4));
					insDTO.setCreatedDate(newIns.get(5).toString());
					insVector.add(insDTO);					
				}
			
			return insVector;

		   }
		   catch(Exception v)
		   {
			   v.printStackTrace();
		   }
		   
		   finally
		   {
			   try
			   {
				   utility.closeConnection();
			   }
			   catch(Exception r)
			   {
				   r.printStackTrace();
			   }
			}
		   
		   return null;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Vector getAllActiveInspections()
	{
	
		InspectionDTO insDTO;
		Vector insVector=new Vector();
		ArrayList newIns;
		DBUtility utility=null;
		try
		   {
				//String sql=InspectioncreateCommonSQL.SELECT_ALL_INSPECTIONS; //Ramesh Comented on 03 Jan 13
				String sql=InspectioncreateCommonSQL.SELECT_ALL_ACTIVE_INSPECTIONS;
				utility=new DBUtility();
			
				utility.createStatement();
			
				ArrayList allIns=utility.executeQuery(sql);
			
				for(int i=0;i<allIns.size();i++)
				{					
					newIns=(ArrayList)allIns.get(i);
					insDTO=new InspectionDTO();
				
					insDTO.setSlabID(newIns.get(0).toString());
					insDTO.setSlabMinRange((String)newIns.get(1));
					insDTO.setSlabMaxRange((String)newIns.get(2));
					insDTO.setStatus((String)newIns.get(3));
					if("A".equalsIgnoreCase((String)newIns.get(3)))
					{
						insDTO.setStatusDesc("Active");
					}
					insDTO.setCreatedBy((String)newIns.get(4));
					insDTO.setCreatedDate(newIns.get(5).toString());
					
					insVector.add(insDTO);					
				}
			
			return insVector;

		   }
		   catch(Exception v)
		   {
			   v.printStackTrace();
		   }
		   
		   finally
		   {
			   try
			   {
				   utility.closeConnection();
			   }
			   catch(Exception r)
			   {
				   r.printStackTrace();
			   }
			}
		return null;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean isExist(String id)
	{
		   try
		   {
			   String sql=InspectioncreateCommonSQL.SELECT_INSPECTION_BY_ID;
			   DBUtility utility=new DBUtility();
			   String[] param= new String[1];
			   param[0]=id;
			   utility.createPreparedStatement(sql);
			   ArrayList result=utility.executeQuery(param);
			   if(!result.isEmpty())
			   {
				   ArrayList subList=(ArrayList)result.get(0);
				   if(subList.isEmpty())
				   {
					   return false;
				   }
				   else
				   {
					   return true;
				   }
			   }

		   }
		   
		   catch(Exception d)
		   {
			   d.printStackTrace();
		   }
		   finally
		   {

			}
		   return false;
	}
	/**
	 * 
	 * @param insDTO
	 */
	public void addInspection(InspectionDTO insDTO)
	{
		
		String sql=InspectioncreateCommonSQL.ADD_NEW_INSPECTION;
		String[] param=new String[6];
		DBUtility utility=null;
		try
		   {				
				utility=new DBUtility();
				utility.createPreparedStatement(sql);
				
				param[0]=insDTO.getSlabID();
				param[1]=insDTO.getSlabMinRange();				
				param[2]=insDTO.getSlabMaxRange();
				param[3]=insDTO.getStatus();
				param[4]=insDTO.getCreatedBy();
				param[5]=insDTO.getModifiedBy();
				//param[7]=insDTO.getMinSpotInsp();
				
				
				boolean add = utility.executeUpdate(param);
				if(add)
				{
					utility.commit();
				}
				else
				{
					utility.rollback();
				}
	
				   
		   }
		   catch(Exception g)
		   {
			   g.printStackTrace();
		   }
		   
		   finally
		   {
			   try
			   {
				   utility.closeConnection();
			   }
			   catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		   }
	}
	/**
	 * 
	 * @param id
	 */
	public void deleteInspection(String id)
	{
		DBUtility utility=null;
		try
		   {
			   String sql=InspectioncreateCommonSQL.DELETE_INSPECTION_BY_ID;
			   utility=new DBUtility();
			   String[] param= new String[1];
			   param[0]=id;
			   utility.createPreparedStatement(sql);
			   boolean deleted=utility.executeUpdate(param);
			   if(deleted)
			   {
				   utility.commit();
			   }
			   else
			   {
				   utility.rollback();
			   }
		   }
		catch(Exception f)
		{
			f.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		
	}
/**
 * 
 * @param insDTO
 */
	public void updateInspection(InspectionDTO insDTO)
	{
		DBUtility utility=null;
		try
		   {
			   String sql=InspectioncreateCommonSQL.UPDATE_INSPECTION;
			   
			   String[] param= new String[6];
			   
			   param[0]=insDTO.getSlabMinRange();			  
			   param[1]=insDTO.getSlabMaxRange();			  
			   param[2]=insDTO.getStatus();			   
			   param[3]=insDTO.getCreatedBy();	           
			   //param[5]=insDTO.getCreatedDate();			  
               param[4]=insDTO.getModifiedBy();               
              param[5]=insDTO.getSlabID();  
			   utility=new DBUtility();
			   utility.createPreparedStatement(sql);
			   boolean deleted=utility.executeUpdate(param);
			   
			   if(deleted)
			   {
				   utility.commit();
			   }
			   else
			   {
				   utility.rollback();
			   }
		   }
		catch(Exception f)
		{
			f.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
	}

	/**
	 * 	
	 * @param insDTO
	 * @return
	 */
	public String updtApproveInspection(InspectionDTO insDTO)
	{
		DBUtility utility=null;
		String retMsg="";
		try
		   {
			   String sql=InspectioncreateCommonSQL.UPDATE_APPROVE_INSPECTION;
			   
			   String[] param= new String[3];
			   
			   param[0]=insDTO.getApprovalStatus();			  
			   param[1]=insDTO.getApprovalRemarks();			  
               param[2]=insDTO.getSlabID();
               utility=new DBUtility();
			   utility.createPreparedStatement(sql);
			   boolean deleted=utility.executeUpdate(param);
			  
			   if(deleted)
			   {
				   
				   utility.commit();
				   retMsg="succ";
			   }
			   else
			   {
				   utility.rollback();
			   }
		   }
		catch(Exception f)
		{
			f.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		return retMsg;
	}
	
	/**
	 * 	
	 * @param id
	 * @param language 
	 * @return
	 */
	public InspectionDTO getInspectionById(String id, String language)
	{
		DBUtility utility=null;
		InspectionDTO insDTO=new InspectionDTO();

		try
		   {
			
			   String sql=InspectioncreateCommonSQL.SELECT_INSPECTION_BY_ID;
			   
			   String[] param= new String[1];
			   param[0]=id;
			   
			   utility=new DBUtility();
			   utility.createPreparedStatement(sql);			  
			   ArrayList result=utility.executeQuery(param);			  
			   if(!result.isEmpty())
			   {
				   ArrayList newIns=(ArrayList)result.get(0);
				   
				   if(newIns.isEmpty())
				   {
					   return null;
				   }
				   else
				   {
						insDTO.setSlabID((String)newIns.get(0));
						insDTO.setSlabMinRange((String)newIns.get(1));
						insDTO.setSlabMaxRange((String)newIns.get(2));
						insDTO.setStatus(((String)newIns.get(3)));
						if("A".equalsIgnoreCase((String)newIns.get(3)))
						{
							if(language.equalsIgnoreCase("en"))
							insDTO.setStatusDesc("Active");
							else
								insDTO.setStatusDesc("सक्रिय");
						}
						insDTO.setCreatedBy((String)newIns.get(4));
						insDTO.setCreatedDate((String)newIns.get(5));
				
						//insDTO.setStatusDesc((String)newIns.get(13));
						
						return insDTO; 
				   }
			   }
		   }
		catch(Exception f)
		{
			f.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList getSlabTxnId() {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery("SELECT IGRS_SPOT_INS_TXN_ID_SEQ.NEXTVAL FROM DUAL");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
	
	/**
	 * 
	 * @return
	 */
	public ArrayList getMaxSalbRange() {
		ArrayList list = new ArrayList();
		DBUtility dbUtil = null;
		try {
			dbUtil = new DBUtility();
			dbUtil.createStatement();
			list = dbUtil.executeQuery("select nvl(max(SLAB_MAX_RANGE),0) SLAB_MAX_RANGE from IGRS_SPOT_INSP_SLAB_MASTER");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
	
}

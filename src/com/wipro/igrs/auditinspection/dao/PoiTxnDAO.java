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

import com.wipro.igrs.auditinspection.dto.PoiTxnDTO;
import com.wipro.igrs.auditinspection.sql.PoiTxnCreateCommonSql;
import com.wipro.igrs.db.DBUtility;

public class PoiTxnDAO {
	
	public PoiTxnDTO getTxnDtlsByID(String txnID)
	{
		DBUtility utility=null;
		PoiTxnDTO poiDTO=new PoiTxnDTO();
		String sql=PoiTxnCreateCommonSql.GET_TXN_DTLS_BY_ID;
		
		try
		{
			utility=new DBUtility();
			String[] param= new String[1];
			param[0]=txnID;
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			
			if(result!=null && !result.isEmpty())
			{
				ArrayList subList=(ArrayList)result.get(0);
				
				 if(!subList.isEmpty())
				 {

					 poiDTO.setTxnID(subList.get(0).toString());
					 poiDTO.setPoName(subList.get(1).toString());
					 poiDTO.setPoAddress(subList.get(2).toString());
					 poiDTO.setInspFromDate(subList.get(3).toString());
					 poiDTO.setInspToDate(subList.get(4).toString());
					 
					 if(subList.get(5)!= null)
					 {
						 poiDTO.setInspDate(subList.get(5).toString());
					 }
					 poiDTO.setDispatchDate(subList.get(8).toString());
					 poiDTO.setCreatedBy(subList.get(13).toString());
					 poiDTO.setCreatedDate(subList.get(14).toString());
					 poiDTO.setUpdatedBy(subList.get(15).toString());
					 poiDTO.setUpdatedDate(subList.get(16).toString());
					 
					 if(subList.get(17)!=null)
					 {
						 poiDTO.setDispatchNo(subList.get(17).toString());
					 }
					 
					 return poiDTO;
				 }
				 
			}
			
			return null;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
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

	public boolean updateTxnDtls(PoiTxnDTO poiDTO)
	{
		DBUtility utility=null;
		String sql=PoiTxnCreateCommonSql.UPDATE_TXN_TDLS;
		String[] param= new String[3];
		
		try
		{
			utility=new DBUtility();
			param[0]= poiDTO.getInspDate();
			param[1]= poiDTO.getDispatchNo();
			param[2]= poiDTO.getTxnID();
			
			utility.createPreparedStatement(sql);
			boolean updated=utility.executeUpdate(param);
			
			if(updated)
			   {
				   utility.commit();
			   }
		   else
			   {
				   utility.rollback();
			   }
			
			return updated;
		}
		catch( Exception v)
		{
			v.printStackTrace();
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
		return false;
	}
}

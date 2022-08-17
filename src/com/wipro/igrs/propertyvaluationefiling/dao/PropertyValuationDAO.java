 /*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :  PropertyValuationDAO.java
 * Author      :  Madan Mohan 
 * Description :   
*/


package com.wipro.igrs.propertyvaluationefiling.dao;

import com.wipro.igrs.commonEfiling.CommonSQL;
import com.wipro.igrs.db.DBUtility;


/**
 * @since 15 JulY 2013
 * @author Vinay Sharma && Satbir Kumar
 */
public class PropertyValuationDAO {
	
	public double getBaseValueAgri(String districtId,String tehsilId,String patwariId,String villageId,String l,String l1,String l2) throws Exception
	{
		String baseValue="";
		String query=  "SELECT g2.guideline_value  "+
		 " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 "+ 
		 " WHERE g.district_id = ? "+
		 " AND g.guideline_id = g1.guideline_id "+
		 " AND g1.tehsil_id = ? "+ 
		 " AND g1.ward_patwari_id =  ?" +
		 " AND g1.mohalla_village_id =?" +
		 " AND g1.guideline_child1_id = g2.guideline_child1_id "+  
		 " AND g2.property_type_id = ?  "+
		 " AND g2.property_type_l1_id =? ";
		String query2="";
		if(l2==null)
		{	
		 query2= " AND g2.property_type_l2_id is NULL ";
		 DBUtility dbUtil = new DBUtility();;
			
			try {
				
				dbUtil.createPreparedStatement(query+query2);
				String parameterArray[]={districtId,tehsilId,patwariId,villageId,l,l1};
				baseValue = dbUtil.executeQry(parameterArray);
			} catch (Exception x) {
				x.printStackTrace();
			} finally {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
		}
		}
		else
		{
			query2= " AND g2.property_type_l2_id is null ";	
			DBUtility dbUtil = new DBUtility();;
			
			try {
				if(l2.equalsIgnoreCase("26"))
				{
					l1="1";
				}
				else
				{
					l1="2";
				}
				dbUtil.createPreparedStatement(query+query2);
				String parameterArray[]={districtId,tehsilId,patwariId,villageId,"1",l1};
				baseValue = dbUtil.executeQry(parameterArray);
			} catch (Exception x) {
				x.printStackTrace();
			} finally {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
		}
		 
	
		
	}
		return Double.parseDouble(baseValue);
}
	public double getBaseValuePlot(String districtId,String tehsilId,String patwariId,String villageId,String l,String l1,String l2) throws Exception
	{
		String baseValue="";
		String query=  "SELECT g2.guideline_value  "+
		 " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 "+ 
		 " WHERE g.district_id = ? "+
		 " AND g.guideline_id = g1.guideline_id "+
		 " AND g1.tehsil_id = ? "+ 
		 " AND g1.ward_patwari_id =  ?" +
		 " AND g1.mohalla_village_id =?" +
		 " AND g1.guideline_child1_id = g2.guideline_child1_id "+  
		 " AND g2.property_type_id = ?  "+
		 " AND g2.property_type_l1_id =? "+
		 " AND g2.property_type_l2_id is NULL ";
		DBUtility dbUtil = new DBUtility();;
			
			try {
				
				dbUtil.createPreparedStatement(query);
				String parameterArray[]={districtId,tehsilId,patwariId,villageId,l,l1};
				baseValue = dbUtil.executeQry(parameterArray);
			} catch (Exception x) {
				x.printStackTrace();
			} finally {
				if (dbUtil != null) {
					dbUtil.closeConnection();
				}
		}
		return Double.parseDouble(baseValue);
}
}
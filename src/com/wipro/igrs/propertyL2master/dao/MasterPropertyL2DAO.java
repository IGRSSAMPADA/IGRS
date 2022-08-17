/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL2DAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.dao;

import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertyL2master.form.MasterPropertyL2Form;
import com.wipro.igrs.propertyL2master.dto.PropertyL2DTO;
import com.wipro.igrs.propertyL2master.sql.PropertyL2CommonSQL;

public class MasterPropertyL2DAO {
	ArrayList propertyL1idList = new ArrayList();
	ArrayList propertyL2List = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    PropertyL2DTO dto = null;
    public MasterPropertyL2DAO() {

        try {
           
        } catch (Exception e) {
        	e.getStackTrace();
        }
    }
    //Method for getting Propertyid List from Database
    public ArrayList getPropertyL1idList() throws Exception {
        try {
        	 dbUtility = new DBUtility();
            sql = PropertyL2CommonSQL.SELECT_MASTER_L2_PROPERTY;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto = new PropertyL2DTO();
             dto.setName(subList.get(1).toString());
             dto.setValue(subList.get(0).toString());
             propertyL1idList.add(dto);
             dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
            	dbUtility.closeConnection();
            }
        return propertyL1idList;
    }
  //Method for getting PropertyL2 List from Database
    public ArrayList getPropertyL2List() throws Exception {
        try {
        	 dbUtility = new DBUtility();
            sql = PropertyL2CommonSQL.SELECT_MASTER_L2_PROPERTY_ID;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto = new PropertyL2DTO();
             dto.setPropertyL2id(subList.get(0).toString());
             dto.setPropertyL2name(subList.get(1).toString());
             dto.setPropertyL2desc(subList.get(2).toString());
             dto.setPropertyL2status(subList.get(3).toString());
             dto.setPropertyL1id(subList.get(4).toString());
             propertyL2List.add(dto);
             dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
            	dbUtility.closeConnection();
            }
            
        return propertyL2List;
    }
    //Method for getting L2Property List from Database
    public PropertyL2DTO getL2property(String L2pid) throws Exception
    {
     try{
    	 dbUtility = new DBUtility();
    	 sql = PropertyL2CommonSQL.SELECT_MASTER_L2_PROPERTY_L2ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=L2pid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new PropertyL2DTO();
    	 dto.setPropertyL2id(list1.get(0).toString());
    	 dto.setPropertyL2name(list1.get(1).toString());
    	 dto.setPropertyL2desc(list1.get(2).toString());
    	 dto.setPropertyL2status(list1.get(3).toString());
       	}catch(Exception e){
    	        e.getStackTrace();
    	}finally {
    		dbUtility.closeConnection();
    	}
    	    return dto;
    }
    //Method for inserting PropertyL2 details into Database
    public void addpropertyL2master(MasterPropertyL2Form masterl2PropertyForm, String roleId, String funId, String userId)
    {
    	String param[] = null;
    	param = new String[3];
    	param[0]=masterl2PropertyForm.getProperty();
    	param[1]=masterl2PropertyForm.getDescription();
    	param[2]=masterl2PropertyForm.getPropertyL1id();
    	sql=PropertyL2CommonSQL.INSERT_MASTER_L2_PROPERTY; 
    	      
    try {
    	 IGRSCommon igrsCommon = new IGRSCommon();
    	 dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_PROP_TYPE_L2_MASTER","INSERT","T",funId,userId,roleId);
        }
            
        else
            dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_PROP_TYPE_L2_MASTER","INSERT","F",funId,userId,roleId);
    } catch (Exception e) {
        e.getStackTrace();
    }finally {
    	try {
    		dbUtility.closeConnection();
    	}catch(Exception x) {
    		x.getStackTrace();
    	}
    }
}
  //Method for updating PropertyL2 details into Database
    public void updatepropertyL2master(MasterPropertyL2Form masterl2PropertyForm, String roleId, String funId, String userId)
    {
    	String param[] = null;
    	param = new String[4];
    	param[0]=masterl2PropertyForm.getDto().getPropertyL2name();
    	param[1]=masterl2PropertyForm.getDto().getPropertyL2desc();
    	param[2]=masterl2PropertyForm.getDto().getPropertyL2status();
    	param[3]=masterl2PropertyForm.getDto().getPropertyL2id();
    	sql=PropertyL2CommonSQL.UPDATE_MASTER_L2_PROPERTYL2_ID; 
    	       
    	try {
    		dbUtility = new DBUtility();
    		 IGRSCommon igrsCommon = new IGRSCommon();
    		dbUtility.createPreparedStatement(sql);
    		boolean boo = dbUtility.executeUpdate(param);
    		if (boo){
            	dbUtility.commit();
            	igrsCommon.saveLogDet("IGRS_PROP_TYPE_L2_MASTER","UPDATE","T",funId,userId,roleId);
            }
                
            else
                dbUtility.rollback();
            igrsCommon.saveLogDet("IGRS_PROP_TYPE_L2_MASTER","UPDATE","F",funId,userId,roleId);
        } catch (Exception e) {
	    		e.getStackTrace();
	    }finally {
	    	try {
	    		dbUtility.closeConnection();
	    	}catch(Exception x) {
	    		x.getStackTrace();
	    	}
	    }
    }
}

/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL1DAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	26/02/2008
 */
package com.wipro.igrs.propertyL1master.dao;

import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertyL1master.form.MasterPropertyL1Form;
import com.wipro.igrs.propertyL1master.dto.PropertyL1DTO;
import com.wipro.igrs.propertyL1master.sql.PropertyL1CommonSQL;

public class MasterPropertyL1DAO {
	ArrayList propertyidList = new ArrayList();
	ArrayList propertyL1List = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    PropertyL1DTO dto = null;
    public MasterPropertyL1DAO() {

        try {
            
        } catch (Exception e) {
        	e.getStackTrace();
        }
    }
    //Method for updating Propertyid from Database
    public ArrayList getPropertyidList() throws Exception {
        try {
        	dbUtility = new DBUtility();
            sql = PropertyL1CommonSQL.SELECT_MASTER_L1_PROPERTY;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto = new PropertyL1DTO();
             dto.setName(subList.get(1).toString());
             dto.setValue(subList.get(0).toString());
             propertyidList.add(dto);
             //dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
              //dbUtility.rollback();
             
        }finally {
        	dbUtility.closeConnection();
        }
        return propertyidList;
    }
    //Method for getting PropertyL1 List details from Database
    public ArrayList getpropertyL1List() throws Exception {
        try {
        	dbUtility = new DBUtility();
            sql = PropertyL1CommonSQL.SELECT_MASTER_L1_PROPERTY_ID;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto= new PropertyL1DTO();
             dto.setPropertyL1id(subList.get(0).toString());
             dto.setPropertyL1name(subList.get(1).toString());
             dto.setPropertyL1desc(subList.get(2).toString());
             dto.setPropertyL1status(subList.get(3).toString());
             dto.setPropertyid(subList.get(4).toString());
             propertyL1List.add(dto);
             //dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
              //dbUtility.rollback();
        }finally {
        	dbUtility.closeConnection();
        }
        return propertyL1List;
    }
    //Method for getting PropertyL1 details from Database
    public PropertyL1DTO getL1property(String L1pid) throws Exception
    {
     try{
    	 dbUtility = new DBUtility();
    	 sql = PropertyL1CommonSQL.SELECT_MASTER_L1_PROPERTY_L1ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=L1pid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 System.out.println(list1);
    	 dto = new PropertyL1DTO();
    	 dto.setPropertyL1id(list1.get(0).toString());
    	 dto.setPropertyL1name(list1.get(1).toString());
    	 dto.setPropertyL1desc(list1.get(2).toString());
    	 dto.setPropertyL1status(list1.get(3).toString());
       	}catch(Exception e){
    	        e.getStackTrace();
    	}finally {
    		dbUtility.closeConnection();
    	}
    	    return dto;
    }
    //Method for inserting PropertyL1 details into Database
    public void addpropertyL1master(MasterPropertyL1Form masterl1PropertyForm,String roleId, String funId, String userId)
    {
    	 
    	String param[] = null;
    	param = new String[3];
    	param[0]=masterl1PropertyForm.getPropertyL1name();
    	param[1]=masterl1PropertyForm.getPropertyL1description();
    	param[2]=masterl1PropertyForm.getPropertyid();
    	sql=PropertyL1CommonSQL.INSERT_MASTER_L1_PROPERTY; 
    	       
    try {
    	dbUtility = new DBUtility();
    	IGRSCommon igrsCommon = new IGRSCommon();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_PROP_TYPE_L1_MASTER","INSERT","T",funId,userId,roleId);
        }
            
        else
            dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_PROP_TYPE_L1_MASTER","INSERT","F",funId,userId,roleId);
    } catch (Exception e) {
        e.getStackTrace();
    } finally {
    	try {
    		dbUtility.closeConnection();
    	}catch(Exception x) {
    		x.getStackTrace();
    	}
    }
}
    //Method for updating PropertyL1 details into Database
    public void updatepropertyL1master(MasterPropertyL1Form masterl1PropertyForm, String roleId, String funId, String userId)
    {
    	String param[] = new String[4];
    	param[0] = masterl1PropertyForm.getDto().getPropertyL1name();
    	param[1] = masterl1PropertyForm.getDto().getPropertyL1desc();
    	param[2] = masterl1PropertyForm.getDto().getPropertyL1status();
    	param[3] = masterl1PropertyForm.getDto().getPropertyL1id();
    	sql=PropertyL1CommonSQL.UPDATE_MASTER_L1_PROPERTYL1_ID;
    try {
    	dbUtility = new DBUtility();
    	IGRSCommon igrsCommon = new IGRSCommon();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_PROP_TYPE_L1_MASTER","UPDATE","T",funId,userId,roleId);
        }
        else
            dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_PROP_TYPE_L1_MASTER","UPDATE","F",funId,userId,roleId);
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

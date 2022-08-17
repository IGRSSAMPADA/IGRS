/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyDAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.dao;
import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertytypemaster.form.MasterPropertyForm;
import com.wipro.igrs.propertytypemaster.dto.PropertyDTO;
import com.wipro.igrs.propertytypemaster.sql.PropertyCommonSQL;

public class MasterPropertyDAO {
	ArrayList propertyList = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    PropertyDTO dto=null;
    ArrayList subList = null;
    public MasterPropertyDAO() {

        
    }
    //Method for getting Property List from Database
    public ArrayList getPropertyList() throws Exception {
        try {
        	int j = 1;
            sql = PropertyCommonSQL.SELECT_MASTER_PROPERTY;
            dbUtility = new DBUtility();
            dbUtility.createStatement();  
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new PropertyDTO();
            	dto.setSno(j);
            	dto.setPropertyid(subList.get(0).toString());
                dto.setPropertyname(subList.get(1).toString());
                dto.setPropertydesc(subList.get(2).toString());
                dto.setPropertystatus(subList.get(3).toString());
                propertyList.add(dto);
                j++;
              }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
            	dbUtility.closeConnection();
            }
        return propertyList;
    }
  //Method for getting PropertyId from Database
    public PropertyDTO getProperty(String pid) throws Exception
    {
     try{
    	 sql = PropertyCommonSQL.SELECT_MASTER_PROPERTY_ID;
    	 dbUtility = new DBUtility();
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=pid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new PropertyDTO();
    	 dto.setPropertyid(list1.get(0).toString());
    	 dto.setPropertyname(list1.get(1).toString());
    	 dto.setPropertydesc(list1.get(2).toString());
    	 dto.setPropertystatus(list1.get(3).toString());
    	}catch(Exception e){
    	        e.getStackTrace();
    	}finally {
    		dbUtility.closeConnection();
    	}
    	    return dto;
    }
    //Method for inserting Property Details into Database
    public void addpropertymaster(MasterPropertyForm masterPropertyForm,String roleId, String funId, String userId)
    {
    	String param[] = null;
    	param = new String[2];
    	param[0]=masterPropertyForm.getProperty();
    	param[1]=masterPropertyForm.getDescription();
    	sql=PropertyCommonSQL.INSERT_MASTER_PROPERTY; 
    try {
    	dbUtility = new DBUtility();
    	 IGRSCommon igrsCommon = new IGRSCommon();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_PROPERTY_TYPE_MASTER","INSERT","T",funId,userId,roleId);
        }else
            dbUtility.rollback();
            igrsCommon.saveLogDet("IGRS_PROPERTY_TYPE_MASTER","INSERT","F",funId,userId,roleId);
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
  //Method for updating Property Details into Database
    public void updatepropertymaster(MasterPropertyForm masterPropertyForm, String roleId, String funId, String userId)
    {
    	String param[] = new String[4];
    	param[0] = masterPropertyForm.getDto().getPropertyname();
    	param[1] = masterPropertyForm.getDto().getPropertydesc();
    	param[2] = masterPropertyForm.getDto().getPropertystatus();
    	param[3] = masterPropertyForm.getDto().getPropertyid();
    	sql=PropertyCommonSQL.UPDATE_MASTER_PROPERTY; 
    	       
    try {
    	dbUtility = new DBUtility();
    	 IGRSCommon igrsCommon = new IGRSCommon();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
        	igrsCommon.saveLogDet("IGRS_PROPERTY_TYPE_MASTER","UPDATE","T",funId,userId,roleId);
        }
        else
        dbUtility.rollback();
        igrsCommon.saveLogDet("IGRS_PROPERTY_TYPE_MASTER","UPDATE","F",funId,userId,roleId);
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

/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictDAO.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 01/03/2008
 */
package com.wipro.igrs.districtmaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.districtmaster.dto.DistrictDTO;
import com.wipro.igrs.districtmaster.form.DistrictForm;
import com.wipro.igrs.districtmaster.sql.DistrictCommonSQL;


public class DistrictDAO {
	private String districtId;
	private String districtName;
	private String districtDesc;
	private String districtStatus;
	private String stateId;
	private ArrayList districtList = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    DistrictDTO dto = null;
    public DistrictDAO() {

        try {
           
        } catch (Exception e) {
        	e.getStackTrace();
        }
    }
    //Method for getting Districtid from Database
    public DistrictDTO getDistrictId(String distid) throws Exception
    {
    	try{
    		 dbUtility = new DBUtility();	
    	 sql = DistrictCommonSQL.SELECT_DISTRICT_MASTER_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=distid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new DistrictDTO();
    	 dto.setDistrictId(list1.get(0).toString());
    	 dto.setDistrictName(list1.get(1).toString());
    	 dto.setDistrictDesc(list1.get(2).toString());
    	 dto.setDistrictStatus(list1.get(3).toString());
    	 dto.setStateId(list1.get(4).toString());
    	}catch(Exception e){
    	        e.getStackTrace();
    	 }finally {
    		 dbUtility.closeConnection();
    	 }
    	    return dto;
    }
    //Method for getting District List from Database
    public ArrayList getDistrictList() throws Exception {
        try {
        	 dbUtility = new DBUtility();
        	sql = DistrictCommonSQL.SELECT_DISTRICT_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new DistrictDTO();
            	dto.setDistrictId(subList.get(0).toString());
                dto.setDistrictName(subList.get(1).toString());
                dto.setDistrictDesc(subList.get(2).toString());
                dto.setDistrictStatus(subList.get(3).toString());
                dto.setStateId(subList.get(4).toString());
                districtList.add(dto);
              }
            } catch (Exception e) {
              e.getStackTrace();
            }
            finally {
       		 	dbUtility.closeConnection();
       	    }
        return districtList;
    }
    //Method for inserting District details into Database
    public void adddistrictmaster(DistrictForm districtForm)
    {
    	 
    	String param[] = new String[2];
    	param[0]=districtForm.getDistrictName();
    	param[1]=districtForm.getDistrictDesc();
    	sql=DistrictCommonSQL.INSERT_DISTRICT_MASTER;
    	try {
    		dbUtility = new DBUtility();
            dbUtility.createPreparedStatement(sql);
            boolean boo = dbUtility.executeUpdate(param);
            if (boo){
            	dbUtility.commit();
            	}
                
            else
                dbUtility.rollback();
           } catch (Exception e) {
        	   e.getStackTrace();
           }
           finally {
        	   try {
        		   dbUtility.closeConnection();
        	   }catch(Exception x) {
        		   x.getStackTrace();
        	   }
      	 }
    }	
  //Method for updating District details into Database
    public void updatedistrictmaster(DistrictForm districtForm)
    {
    	String param[] = new String[4];
    	param[0] = districtForm.getDto().getDistrictName();
    	param[1] = districtForm.getDto().getDistrictDesc();
    	param[2] = districtForm.getDto().getDistrictStatus();
    	param[3] = districtForm.getDto().getDistrictId();
    	sql=DistrictCommonSQL.UPDATE_DISTRICT_MASTER; 
    	      
    try {
    	 dbUtility = new DBUtility();
        dbUtility.createPreparedStatement(sql);
        boolean boo = dbUtility.executeUpdate(param);
        if (boo){
        	dbUtility.commit();
         }    
        else
            dbUtility.rollback();
    	} catch (Exception e) {
    		e.getStackTrace();
    	}
    	finally {
    		try {
    			dbUtility.closeConnection();
    		} catch (Exception e) {
        		e.getStackTrace();
        	}
        
   	 	}
    }
}

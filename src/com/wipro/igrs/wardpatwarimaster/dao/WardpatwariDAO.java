/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariDAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.wardpatwarimaster.dto.WardpatwariDTO;
import com.wipro.igrs.wardpatwarimaster.form.WardpatwariForm;
import com.wipro.igrs.wardpatwarimaster.sql.WardpatwariCommonSQL;

public class WardpatwariDAO {
	private ArrayList tehsilidList = new ArrayList();
	private ArrayList wardpatwariList = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    WardpatwariDTO dto = null;
    public WardpatwariDAO() {

        try {
            dbUtility = new DBUtility();
        } catch (Exception e) {
        	e.getStackTrace();
        }
    }
    //Method for getting TehsilId list from DataBase
    public ArrayList getTehsilidList() throws Exception {
        try {
            sql = WardpatwariCommonSQL.SELECT_WARDPATWARI_MASTER_TEHSILID;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto = new WardpatwariDTO();
             dto.setName(subList.get(1).toString());
             dto.setValue(subList.get(0).toString());
             tehsilidList.add(dto);
             dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
              dbUtility.rollback();
        }
        return tehsilidList;
    }
  //Method for getting Wardpatwari list from DataBase
    public ArrayList getWardpatwariList() throws Exception {
        try {
        	sql = WardpatwariCommonSQL.SELECT_WARDPATWARI_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new WardpatwariDTO();
            	dto.setWardpatwariId(subList.get(0).toString());
            	dto.setWardpatwariName(subList.get(1).toString());
            	dto.setWardpatwariDesc(subList.get(2).toString());
            	dto.setWardpatwariStatus(subList.get(3).toString());
                wardpatwariList.add(dto);
              }
            } catch (Exception e) {
              e.getStackTrace();
              }
        return wardpatwariList;
    }
  //Method for getting WardpatwariId from DataBase
    public WardpatwariDTO getWardpatwariId(String wardid) throws Exception
    {
    	try{
    	 sql = WardpatwariCommonSQL.SELECT_WARDPATWARI_MASTER_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=wardid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 System.out.println(list1);
    	 dto = new WardpatwariDTO();
    	 dto.setWardpatwariId(list1.get(0).toString());
    	 dto.setWardpatwariName(list1.get(1).toString());
    	 dto.setWardpatwariDesc(list1.get(2).toString());
    	 dto.setWardpatwariStatus(list1.get(3).toString());
    	}catch(Exception e){
    	        e.getStackTrace();
    	    }
    	    return dto;
    }
    //Method for inserting Wardpatwari details into DataBase
    public void addwardpatwarimaster(WardpatwariForm wardpatwariForm)
    {
    	String param[] = new String[3];
    	param[0]=wardpatwariForm.getWardpatwariName();
    	param[1]=wardpatwariForm.getWardpatwariDesc();
    	param[2]=wardpatwariForm.getTehsilId();
    	sql=WardpatwariCommonSQL.INSERT_WARDPATWARI_MASTER;
    	try {
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
    }
  //Method for Updating Wardpatwari details into DataBase
    public void updatepatwarimaster(WardpatwariForm wardpatwariForm)
    {
    	String param[] = new String[4];
    	param[0] = wardpatwariForm.getDto().getWardpatwariName();
    	param[1] = wardpatwariForm.getDto().getWardpatwariDesc();
    	param[2] = wardpatwariForm.getDto().getWardpatwariStatus();
    	param[3] = wardpatwariForm.getDto().getWardpatwariId();
    	sql=WardpatwariCommonSQL.UPDATE_WARDPATWARI_MASTER; 
    	       
    try {
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
    }
}

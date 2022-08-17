/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilDAO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.tehsilmaster.dto.TehsilDTO;
import com.wipro.igrs.tehsilmaster.form.TehsilForm;
import com.wipro.igrs.tehsilmaster.sql.TehsilCommonSQL;
public class TehsilDAO {
	private ArrayList tehsilList = new ArrayList();
	private ArrayList districtidList = new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    TehsilDTO dto = null;
    public TehsilDAO() {

      
    }
    //Method for Getting TehsilId from Database
    public TehsilDTO getTehsilId(String tehsilid) throws Exception
    {
    	try{
    		dbUtility = new DBUtility();	
    	 sql = TehsilCommonSQL.SELECT_TEHSIL_MASTER_ID;
    	 dbUtility.createPreparedStatement(sql);
    	 String sd[] = new String[1];
    	 sd[0]=tehsilid;
    	 ArrayList list=dbUtility.executeQuery(sd);
    	 ArrayList list1 = (ArrayList)list.get(0);
    	 dto = new TehsilDTO();
    	 dto.setTehsilId(list1.get(0).toString());
    	 dto.setTehsilName(list1.get(1).toString());
    	 dto.setTehsilDesc(list1.get(2).toString());
    	 dto.setTehsilStatus(list1.get(3).toString());
    	}catch(Exception e){
    	        e.getStackTrace();
    	}finally {
    		dbUtility.closeConnection();
    	}
    	    return dto;
    }
  //Method for Getting TehsilList from Database
    public ArrayList getTehsilList() throws Exception {
        try {
        	sql = TehsilCommonSQL.SELECT_TEHSIL_MASTER;
        	dbUtility = new DBUtility();
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	dto = new TehsilDTO();
            	dto.setTehsilId(subList.get(0).toString());
            	dto.setTehsilName(subList.get(1).toString());
            	dto.setTehsilDesc(subList.get(2).toString());
            	dto.setTehsilStatus(subList.get(3).toString());
                tehsilList.add(dto);
              }
            } catch (Exception e) {
              e.getStackTrace();
              }finally {
            	  dbUtility.closeConnection();
              }
        return tehsilList;
    }
  //Method for Getting DistrictId from Database
    public ArrayList getDistrictidList() throws Exception {
        try {
            sql = TehsilCommonSQL.SELECT_TEHSIL_MASTER_DISTID;
            dbUtility = new DBUtility();
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);                      
             dto = new TehsilDTO();
             dto.setName(subList.get(1).toString());
             dto.setValue(subList.get(0).toString());
             districtidList.add(dto);
            
             }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
            	dbUtility.closeConnection();
            }
        return districtidList;
    }
  //Method for Inserting Tehsil Details into  Database
    public void addtehsilmaster(TehsilForm tehsilForm)
    {
    	String param[] = new String[3];
    	param[0]=tehsilForm.getTehsilName();
    	param[1]=tehsilForm.getTehsilDesc();
    	param[2]=tehsilForm.getDistrictId();
    	sql=TehsilCommonSQL.INSERT_TEHSIL_MASTER;
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
         }finally {
        	 try {
        		 dbUtility.closeConnection();
        	 }catch(Exception x) {
        		 x.getStackTrace();
        	 }
         }
    }
  //Method for Updating Tehsil Details into  Database
    public void updatetehsilmaster(TehsilForm tehsilForm)
    {
    	String param[] = new String[4];
    	param[0] = tehsilForm.getDto().getTehsilName();
    	param[1] = tehsilForm.getDto().getTehsilDesc();
    	param[2] = tehsilForm.getDto().getTehsilStatus();
    	param[3] = tehsilForm.getDto().getTehsilId();
    	sql=TehsilCommonSQL.UPDATE_TEHSIL_MASTER; 
    	       
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
    }finally {
    	try {
    		dbUtility.closeConnection();
    	}catch(Exception x ) {
    		x.getStackTrace();
    	}
    }
    }

}


/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmappingmaster.dao;
import java.util.ArrayList;

//import sun.security.krb5.internal.crypto.t;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.userrolegroupmappingmaster.dto.URolegroupDTO;
import com.wipro.igrs.userrolegroupmappingmaster.form.URolegroupForm;
import com.wipro.igrs.userrolegroupmappingmaster.sql.URolegroupCommonSQL;
//import com.wipro.igrs.Log4J.LoggerMsg;


public class URolegroupDAO {
	
	private ArrayList userList = new ArrayList();
	private ArrayList rolegroupList = new ArrayList();
	private ArrayList userroleList = new ArrayList();
    DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    URolegroupDTO dto = null;
    /*DAO constructor*/
    public URolegroupDAO() {

        try {
        	//LoggerMsg.info("we are in URolegroupDAO");
        	
        } catch (Exception e) {
        	 e.getStackTrace();
        	//LoggerMsg.error("rgrouprole Master DAO in dao start" + e.getStackTrace());
        }
    }
    /* ADD USER ROLEGROUPMAPPING MASTER */
    
    public void addUserrolemappingmaster(URolegroupForm urolegroupForm)
    {
    	
    		sql=URolegroupCommonSQL.INSERT_USER_ROLE_GROUP_MAPPING_MASTER;
        	String param[] = new String[2];
        	param[0]=urolegroupForm.getUserId();
        	param[1]=urolegroupForm.getRolegroupId();
        	             	
        try {
        	dbUtility = new DBUtility();
            dbUtility.createPreparedStatement(sql);
            boolean boo = dbUtility.executeUpdate(param);
            if (boo){
            	dbUtility.commit();
            	System.out.println("---->data inserted");
            }else {
            	dbUtility.rollback();
            }
            	
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
    /* GET ROLE LIST */
	public ArrayList getUserList()throws Exception
	{
		try {
            sql = URolegroupCommonSQL.SELECT_USER_MASTER;
            dbUtility = new DBUtility();
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
             subList = (ArrayList)mainList1.get(i);
             dto = new URolegroupDTO();
             dto.setName(subList.get(0).toString());
             dto.setValue(subList.get(0).toString());
             userList.add(dto);
             //dbUtility.commit();
             }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
        		dbUtility.closeConnection();
           }
            
        return userList;
		
	}

/* GET ROLE GROUP LIST */
public ArrayList getRolegroupList()throws Exception
{
	try {
		dbUtility = new DBUtility();
        sql = URolegroupCommonSQL.SELECT_ROLE_GROUP_MASTER;
        dbUtility.createStatement();
       
        ArrayList mainList1= dbUtility.executeQuery(sql);
        for (int i=0;i<mainList1.size();i++) 
         {
         subList = (ArrayList)mainList1.get(i);
         dto = new URolegroupDTO();
         dto.setValue(subList.get(0).toString());
         dto.setName(subList.get(1).toString());
         rolegroupList.add(dto);
         //dbUtility.commit();
         }
        } catch (Exception e) {
          e.getStackTrace();
          //dbUtility.rollback();
        }finally {
    		dbUtility.closeConnection();
        }
    return rolegroupList;
	
}

    /* GET RGROUPMAPPING LIST */
    
    public ArrayList getUserroleList() throws Exception {
        try {
        	dbUtility = new DBUtility();
        	sql = URolegroupCommonSQL.SELECT_USER_ROLE_GROUP_MAPPING_MASTER;
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	//System.out.println("sublist value..."+subList);
            	dto = new URolegroupDTO();
            	dto.setUserId(subList.get(0).toString());
            	dto.setRolegroupId(subList.get(1).toString());
            	dto.setActiveFlag(subList.get(2).toString());
            	
            	userroleList.add(dto);
             }
            } catch (Exception e) {
              e.getStackTrace();
            }finally {
        		dbUtility.closeConnection();
            }
        return userroleList;
    }
    
   /* UPDATE RGROUPMAPPING MASTER*/
   
   public void updateUserrolemappingmaster(URolegroupForm urolegroupForm)
   {
   	
   	dto = new URolegroupDTO();
   	String param[] = new String[2];
   	param[0]=urolegroupForm.getDto().getActiveFlag();
   	param[1]=urolegroupForm.getDto().getUserId();
   	sql=URolegroupCommonSQL.UPDATE_USER_ROLE_GROUP_MAPPING_MASTER; 
   //	LoggerMsg.debug("sql values in dao..."+sql);
   	      
   try {
	   dbUtility = new DBUtility();
       dbUtility.createPreparedStatement(sql);
       boolean boo = dbUtility.executeUpdate(param);
       if (boo){
       	dbUtility.commit();
       	System.out.println("---->data updated");
       }else {
    	   dbUtility.rollback();
       }
    	   
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
    /* GET RGROUPMAPPING ID */
   public URolegroupDTO getUserId(String userid) throws Exception
   {
   	try{
   		dbUtility = new DBUtility();
   	 sql = URolegroupCommonSQL.SELECT_USER_GROUP_MAPPING_MASTER_ID;
   	 dbUtility.createPreparedStatement(sql);
   	 String sd[] = new String[1];
   	 sd[0]=userid;
   	 ArrayList list=dbUtility.executeQuery(sd);
   	 ArrayList list1 = (ArrayList)list.get(0);
   	 dto = new URolegroupDTO();
   	 dto.setUserId(list1.get(0).toString());
   	 dto.setRolegroupId(list1.get(1).toString());
   	 dto.setActiveFlag(list1.get(2).toString());
   	    	 
   	}catch(Exception e){
   	        e.getStackTrace();
   	}finally {
 	   dbUtility.closeConnection();
	
   }
   	
   	    return dto;
   	}
   
   }
   

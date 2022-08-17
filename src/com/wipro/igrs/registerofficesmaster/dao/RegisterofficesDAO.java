
/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RegisterofficesDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for RegisterofficeMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  4th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.registerofficesmaster.dao;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.registerofficesmaster.dto.RegisterofficesDTO;
import com.wipro.igrs.registerofficesmaster.form.RegisterofficesForm;
import com.wipro.igrs.registerofficesmaster.sql.RegisterofficesCommonSQL;


public class RegisterofficesDAO {
	
	private ArrayList officeList = new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList tehsilList = new ArrayList();
	private ArrayList wardList = new ArrayList();
	private ArrayList typeList = new ArrayList();
	private ArrayList registerList=new ArrayList();
	DBUtility dbUtility = null;
    String sql = null;
    ArrayList subList = null;
    RegisterofficesDTO dto = null;
    private Logger logger = (Logger) Logger.getLogger(RegisterofficesDAO.class);
    
    public RegisterofficesDAO() {

       
    }
    /* ADD REGISTRATIONOFFICE MASTER */
    
    public void addRegisterofficemaster(RegisterofficesForm registerofficesForm,String roleId, String funId, String userId)
    {
       		sql=RegisterofficesCommonSQL.INSERT_OFFICE_MASTER;
        	String param[] = new String[6];
        	param[0]=registerofficesForm.getRegisterofficeName();
        	param[1]=registerofficesForm.getRegisterofficeDesc();
        	param[2]=registerofficesForm.getRegisterofficedisticId();
        	param[3]=registerofficesForm.getRegisterofficetehsilId();
        	param[4]=registerofficesForm.getRegisterofficewardId();
        	param[5]=registerofficesForm.getRegisterofficetypeId();
        	
        	try {
        		dbUtility = new DBUtility();
        		IGRSCommon igrsCommon = new IGRSCommon();
               dbUtility.createPreparedStatement(sql);
               boolean boo = dbUtility.executeUpdate(param);
            if (boo){
            	dbUtility.commit();
            	igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","T",funId,userId,roleId);
            	logger.debug("---->data inserted");
            }
                
            else {
            	dbUtility.rollback();
            	igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","F",funId,userId,roleId);
            }
            	logger.debug("---->data not inserted:" + sql);
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
    /* GET REGISTRATIONOFFICE LIST */
    
    public ArrayList getRegisterofficesList() throws Exception {
        try {
        	sql = RegisterofficesCommonSQL.SELECT_OFFICE_MASTER;
        	dbUtility = new DBUtility();
            dbUtility.createStatement();
           
            ArrayList mainList1= dbUtility.executeQuery(sql);
            for (int i=0;i<mainList1.size();i++) 
             {
            	subList = (ArrayList)mainList1.get(i);
            	//System.out.println("sublist value..."+subList);
            	dto = new RegisterofficesDTO();
            	dto.setRegisterofficeId(subList.get(0).toString());
            	dto.setRegisterofficeName(subList.get(1).toString());
            	dto.setRegisterofficeDesc(subList.get(2).toString());
            	dto.setRegisterofficeStatus(subList.get(3).toString());
            	dto.setRegisterofficedisticId(subList.get(4).toString());
            	dto.setRegisterofficetypeId(subList.get(5).toString());
            	
            	officeList.add(dto);
              }
            } catch (Exception e) {
              e.getStackTrace();
              }finally {
            	  dbUtility.closeConnection();
              }
        return officeList;
    }
    
   /* UPDATE REGISTRATIONOFFICE MASTER*/
   
    public void updateRegisterofficemaster(RegisterofficesForm registerofficesForm,String roleId, String funId, String userId)
   {
   	dto = new RegisterofficesDTO();
   	String param[] = new String[4];
   	param[0] = registerofficesForm.getDto().getRegisterofficeName();
   	param[1] = registerofficesForm.getDto().getRegisterofficeDesc();
   	param[2] = registerofficesForm.getDto().getRegisterofficeStatus();
   	param[3] = registerofficesForm.getDto().getRegisterofficeId();
   	sql=RegisterofficesCommonSQL.UPDATE_OFFICE_MASTER; 
   	logger.debug("sql values in dao..."+sql);
   	      
   try {
	   dbUtility = new DBUtility();
	   IGRSCommon igrsCommon = new IGRSCommon();
       dbUtility.createPreparedStatement(sql);
       boolean boo = dbUtility.executeUpdate(param);
       if (boo){
       	dbUtility.commit();
       	igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","UPDATE","T",funId,userId,roleId);
       	logger.debug("---->data updated");
       }else {
    	   dbUtility.rollback();
    	   igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","UPDATE","F",funId,userId,roleId);
       }
    	   logger.debug("---->data not updated:" + sql);
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
    /* GET REGISTRATIONOFFICE ID */
   public RegisterofficesDTO getRegisterofficeId(String officeid) throws Exception
   {
   	try{
   	 sql = RegisterofficesCommonSQL.SELECT_OFFICE_MASTER_ID;
   	 dbUtility = new DBUtility();
   	 dbUtility.createPreparedStatement(sql);
   	 String sd[] = new String[1];
   	 sd[0]=officeid;
   	 ArrayList list=dbUtility.executeQuery(sd);
   	 ArrayList list1 = (ArrayList)list.get(0);
   	//System.out.println(list1);
   	 dto = new RegisterofficesDTO();
   	 dto.setRegisterofficeId(list1.get(0).toString());
   	 dto.setRegisterofficeName(list1.get(1).toString());
   	 dto.setRegisterofficeDesc(list1.get(2).toString());
   	 dto.setRegisterofficeStatus(list1.get(3).toString());
   	 dto.setRegisterofficedisticId(list1.get(4).toString());
   	 dto.setRegisterofficetypeId(list1.get(5).toString());
   	 
   	}catch(Exception e){
   	        e.getStackTrace();
   	}finally {
   		dbUtility.closeConnection();
   	}
   	    return dto;
   }
   
   /* GET REGISTRAIONOFFICE DISTRICT LIST */
   
   public ArrayList getDistrictList()throws Exception
	{
		try {
           sql = RegisterofficesCommonSQL.SELECT_OFFICE_MASTER_DIS_ID;
           dbUtility = new DBUtility();
           dbUtility.createStatement();
          
           ArrayList mainList1= dbUtility.executeQuery(sql);
           for (int i=0;i<mainList1.size();i++) 
            {
            subList = (ArrayList)mainList1.get(i);                      
            dto = new RegisterofficesDTO();
            dto.setName(subList.get(0).toString());
            dto.setValue(subList.get(0).toString());
            districtList.add(dto);
            //dbUtility.commit();
            }
           } catch (Exception e) {
             e.getStackTrace();
             
       }finally {
    	   dbUtility.closeConnection();
       }
       return districtList;
		
	}
   /* GET REGISTRATIONOFFICE TEHSIL LIST */
   
   public ArrayList getTehsilList()throws Exception
	{
		try {
           sql = RegisterofficesCommonSQL.SELECT_OFFICE_MASTER_TEHSIL_ID;
           dbUtility = new DBUtility();
           dbUtility.createStatement();
          
           ArrayList mainList1= dbUtility.executeQuery(sql);
           for (int i=0;i<mainList1.size();i++) 
            {
            subList = (ArrayList)mainList1.get(i);                      
            dto = new RegisterofficesDTO();
            dto.setName(subList.get(0).toString());
            dto.setValue(subList.get(0).toString());
            tehsilList.add(dto);
            //dbUtility.commit();
            }
           } catch (Exception e) {
             e.getStackTrace();
             
       }finally {
    	   dbUtility.closeConnection();
       }
       return tehsilList;
		
	}
   
   /* GET REGISTRATIONOFFICE WARD LIST */
   
   public ArrayList getWardList()throws Exception
	{
		try {
           sql = RegisterofficesCommonSQL.SELECT_OFFICE_MASTER_WARD_ID;
           dbUtility = new DBUtility();
           dbUtility.createStatement();
          
           ArrayList mainList1= dbUtility.executeQuery(sql);
           for (int i=0;i<mainList1.size();i++) 
            {
            subList = (ArrayList)mainList1.get(i);                      
            dto = new RegisterofficesDTO();
            dto.setName(subList.get(0).toString());
            dto.setValue(subList.get(0).toString());
            wardList.add(dto);
            //dbUtility.commit();
            }
           } catch (Exception e) {
             e.getStackTrace();
             
       }finally {
    	   dbUtility.closeConnection();
       }
       return wardList;
		
	}
 /* GET REGISTRATIONOFFICE TYPE LIST */
   
   public ArrayList getTypeList()throws Exception
	{
		try {
           sql = RegisterofficesCommonSQL.SELECT_OFFICE_TYPE_ID;
           dbUtility = new DBUtility();
           dbUtility.createStatement();
          
           ArrayList mainList1= dbUtility.executeQuery(sql);
           for (int i=0;i<mainList1.size();i++) 
            {
            subList = (ArrayList)mainList1.get(i);                      
            dto = new RegisterofficesDTO();
            dto.setName(subList.get(0).toString());
            dto.setValue(subList.get(0).toString());
            typeList.add(dto);
            //dbUtility.commit();
            }
           } catch (Exception e) {
             e.getStackTrace();
             
       }finally {
    	   dbUtility.closeConnection();
       }
       return typeList;
		
	}
   }

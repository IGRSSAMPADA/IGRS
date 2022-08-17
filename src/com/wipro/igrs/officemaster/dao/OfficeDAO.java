/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FunctionDeptDAO.java
 * Author      :   Mahmoud Eid
 * Description :   Represents the DAO Class for Function Department Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mahmoud Eid  10th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officemaster.dao;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.officemaster.dto.OfficeTypeDTO;
import com.wipro.igrs.officemaster.sql.OfficeCommonSQL;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;


public class OfficeDAO {
		
	private ArrayList activityList = null;
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	OfficeDTO dto = null;
	String activityID = null;
	/*
	private  Logger logger = 
		(Logger) Logger.getLogger(OfficeDAO.class);
	*/
	public OfficeDAO()
	{
		
	}
	
	public String getSequenceNumber(String _seqName,String _moduleConstant) throws Exception {    

        String seqnumber="";

        try{
        
        dbUtility = new DBUtility();	
        	
        Date date=new Date();       
        Format yearformat= new SimpleDateFormat("yyyy");   
        Format monthformat= new SimpleDateFormat("MM");  

        Format dateformat= new SimpleDateFormat("dd");  

        String dfmt=  dateformat.format(date);

        String yfmt=  yearformat.format(date);

        String mfmt=  monthformat.format(date);      

        String SQL =  "select "+_seqName+".nextval from dual";

        dbUtility.createStatement();

        

        String number= dbUtility.executeQry(SQL);

        //seqnumber=_moduleConstant+dfmt+mfmt+yfmt+number;
        seqnumber=_moduleConstant+number;
        
        }catch(Exception e)

        {

            //logger.debug("Exception occur in PIN Generation: "+ e);

        }finally{

            if(dbUtility!=null){
            	dbUtility.closeConnection();
            }
        }
        return seqnumber;

    }
	
	public void addOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId) {
		sql = OfficeCommonSQL.INSERT_OFFICE_MASTER;
		String param[] = new String[14];
		
		try
		{
			param[0] = this.getSequenceNumber("IGRS_OFFICE_MASTER_SEQ", "OFC");
			param[1] = officeDTO.getOfficeName();
			param[2] = officeDTO.getOfficeDesc();
			param[3] = officeDTO.getOfficeStatus();
			param[4] = officeDTO.getParentId();
			param[5] = officeDTO.getDistrictId();
			param[6] = officeDTO.getTehsilId();
			param[7] = officeDTO.getWardId();
			param[8] = officeDTO.getOfficeAddress();
			param[9] = officeDTO.getOfficePhoneNumber();
			param[10] = officeDTO.getOfficeFaxNumber();
			param[11] = officeDTO.getCreatedby();
			param[12] = officeDTO.getMohallaVillageId();
			param[13] = officeDTO.getOfficeTypeId();
		}
		catch(Exception e)
		{
			System.out.println("terminated during set parameters");
			e.printStackTrace();
		}
		

		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				System.out.println("Commited<<<>>>>");
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","T",funId,userId,roleId);
			} else {
				System.out.println("Rolled back<<<>>>>");
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","F",funId,userId,roleId);
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				x.printStackTrace();
			}
		}
	}
	
	public ArrayList getOfficeList()  {
		try {
			sql = OfficeCommonSQL.SELECT_OFFICE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			activityList = new ArrayList();
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new OfficeDTO();
				dto.setOfficeId(subList.get(0)==null?"":subList.get(0).toString());
				dto.setOfficeName(subList.get(1)==null?"":subList.get(1).toString());
				dto.setOfficeDesc(subList.get(2)==null?"":subList.get(2).toString());
				dto.setOfficeStatus(subList.get(3)==null?"":subList.get(3).toString());
				dto.setParentId(subList.get(4)==null?"":subList.get(4).toString());
				dto.setDistrictId(subList.get(5)==null?"":subList.get(5).toString());
				dto.setTehsilId(subList.get(6)==null?"":subList.get(6).toString());
				dto.setWardId(subList.get(7)==null?"":subList.get(7).toString());
				dto.setOfficeAddress(subList.get(8)==null?"":subList.get(8).toString());
				dto.setOfficePhoneNumber(subList.get(9)==null?"":subList.get(9).toString());
				dto.setOfficeFaxNumber(subList.get(10)==null?"":subList.get(10).toString());
				dto.setCreatedby(subList.get(11)==null?"":subList.get(11).toString());
				dto.setCreatedDate(subList.get(12)==null?"":subList.get(12).toString());
				dto.setUpdatedby(subList.get(13)==null?"":subList.get(13).toString());
				dto.setUpdatedDate(subList.get(14)==null?"":subList.get(14).toString());
				dto.setMohallaVillageId(subList.get(15)==null?"":subList.get(15).toString());
				dto.setOfficeTypeId(subList.get(16)==null?"":subList.get(16).toString());
				
				dto.setDistrictName(subList.get(17)==null?"":subList.get(17).toString());
				dto.setTehsilName(subList.get(18)==null?"":subList.get(18).toString());
				dto.setWardName(subList.get(19)==null?"":subList.get(19).toString());
				dto.setMohallaVillageName(subList.get(20)==null?"":subList.get(20).toString());
				dto.setOfficeTypeName(subList.get(21)==null?"":subList.get(21).toString());
				
				activityList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	}
	
	public void updateOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId) {

		dto = new OfficeDTO();
		String param[] = new String[14];
		param[0] = officeDTO.getOfficeName();
		param[1] = officeDTO.getOfficeDesc();
		param[2] = officeDTO.getOfficeStatus();
		param[3] = officeDTO.getParentId();
		param[4] = officeDTO.getDistrictId();
		param[5] = officeDTO.getTehsilId();
		param[6] = officeDTO.getWardId();
		param[7] = officeDTO.getOfficeAddress();
		param[8] = officeDTO.getOfficePhoneNumber();
		param[9] = officeDTO.getOfficeFaxNumber();
		param[10] = officeDTO.getUpdatedby();
		param[11] = officeDTO.getMohallaVillageId();
		param[12] = officeDTO.getOfficeTypeId();
		param[13] = officeDTO.getOfficeId();
		System.out.println("Name:- "+ param[0] +" ID:- "+param[13]);
		sql = OfficeCommonSQL.UPDATE_OFFICE_MASTER;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				System.out.println("Update commited>>>>>>>>>>>>");
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","T",funId,userId,roleId);
			}else {
				System.out.println("Update RolledBack>>>>>>>>>>>>");
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","INSERT","T",funId,userId,roleId);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				System.out.println("Error in connection");
				x.getStackTrace();
			}
		}
	}
	
	public OfficeDTO getOfficeById(String officeid){
		try {
			sql = OfficeCommonSQL.SELECT_OFFICE_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = officeid;
			System.out.println("Office ID ===="+officeid);
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new OfficeDTO();
			
			dto.setOfficeId(list1.get(0)==null? "":list1.get(0).toString());
			dto.setOfficeName(list1.get(1)==null? "":list1.get(1).toString());
			dto.setOfficeDesc(list1.get(2)==null? "":list1.get(2).toString());
			dto.setOfficeStatus(list1.get(3)==null? "":list1.get(3).toString());
			dto.setParentId(list1.get(4)==null? "":list1.get(4).toString());
			dto.setDistrictId(list1.get(5)==null? "":list1.get(5).toString());
			dto.setTehsilId(list1.get(6)==null? "":list1.get(6).toString());
			dto.setWardId(list1.get(7)==null? "":list1.get(7).toString());
			dto.setOfficeAddress(list1.get(8)==null? "":list1.get(8).toString());
			dto.setOfficePhoneNumber(list1.get(9)==null? "":list1.get(9).toString());
			dto.setOfficeFaxNumber(list1.get(10)==null? "":list1.get(10).toString());
			dto.setCreatedby(list1.get(11)==null? "":list1.get(11).toString());
			dto.setCreatedDate(list1.get(12)==null? "":list1.get(12).toString());
			dto.setUpdatedby(list1.get(13)==null? "":list1.get(13).toString());
			dto.setUpdatedDate(list1.get(14)==null? "":list1.get(14).toString());
			dto.setMohallaVillageId(list1.get(15)==null? "":list1.get(15).toString());
			dto.setOfficeTypeId(list1.get(16)==null? "":list1.get(16).toString());			
			dto.setDistrictName(list1.get(17)==null? "":list1.get(17).toString());
			dto.setTehsilName(list1.get(18)==null? "":list1.get(18).toString());
			dto.setWardName(list1.get(19)==null? "":list1.get(19).toString());
			dto.setMohallaVillageName(list1.get(20)==null? "":list1.get(20).toString());
			dto.setOfficeTypeName(list1.get(21)==null? "":list1.get(21).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	public boolean isOfficeExists(String officeName)
	{
		try {
			sql = OfficeCommonSQL.SELECT_OFFICE_MASTER_NAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = officeName;
			ArrayList list = dbUtility.executeQuery(sd);
			if(!list.isEmpty())
			{
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error in run SQL connection <<<<>>>>>");
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error in cloing connection <<<<>>>>>");
				e.printStackTrace();
			}
		}
		return false;	
	}
	
	public ArrayList getOfficeTypeList()
	{
		ArrayList typeList = new ArrayList();
		try {
			sql = OfficeCommonSQL.SELECT_OFFICE_TYPE;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			OfficeTypeDTO typeDTO = null;
			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				typeDTO = new OfficeTypeDTO();
				typeDTO.setTypeId(subList.get(0).toString());
				typeDTO.setTypeName(subList.get(1).toString());
				
				
				typeList.add(typeDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}
	
	public void deleteOffice(String[] officeIds,String roleId, String funId, String userId)
	{
		for(int i =0;i<officeIds.length;i++)
		{
				String param[] = new String[1];
				param[0] = officeIds[i];
				sql = OfficeCommonSQL.DELETE_OFFICE_MASTER;
	
			try {
				IGRSCommon igrsCommon = new IGRSCommon();
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean boo = dbUtility.executeUpdate(param);
				if (boo) {
					System.out.println("commited>>>>>>>>>>>>");
					dbUtility.commit();
					igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","DELETE","T",funId,userId,roleId);
				}else {
					System.out.println("RolledBack>>>>>>>>>>>>");
					dbUtility.rollback();
					igrsCommon.saveLogDet("IGRS_OFFICE_MASTER","DELETE","F",funId,userId,roleId);
				}	
			} catch (Exception e) {
				System.out.println("Error in Executing update");
				e.printStackTrace();
			}finally {
				try {
					dbUtility.closeConnection();
				}catch(Exception x){
					System.out.println("Error in connection");
					x.getStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args,String roleId, String funId, String userId) {
		OfficeDAO officeDAO = new OfficeDAO();
		OfficeDTO officeDTO = new OfficeDTO();
		officeDTO.setOfficeId("60");
		officeDTO.setOfficeName("fsdf");
		officeDTO.setOfficeAddress("asdasd");
		officeDTO.setOfficeStatus("D");
		officeDTO.setDistrictId("DIST-001");
		officeDTO.setWardId("PAT-001");
		officeDTO.setTehsilId("124");
		officeDTO.setOfficeTypeId("DRO");
		
		officeDAO.updateOfficemaster(officeDTO,roleId,funId,userId);
		//officeDAO.addOfficemaster(officeDTO);
		//System.out.println("finish<<<>>>>");
		//System.out.println("Number of offices>>>>>>> "+officeDAO.getOfficeById("15").getOfficeName());
	}
	
}

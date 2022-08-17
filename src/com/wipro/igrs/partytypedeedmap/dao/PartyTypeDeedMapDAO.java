/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PartyTypeDeedMapDAO.java
 * Author      :   Sayed Taha
 * Description :   Represents the DAO Class for Party Type DeedMap DAO Master.
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
	
package com.wipro.igrs.partytypedeedmap.dao;

import java.util.ArrayList;
import java.util.Date;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;
import com.wipro.igrs.partytypedeedmap.sql.PartyTypeDeedMapSQL;
public class PartyTypeDeedMapDAO {
	//Singleton Design patterns
	private static PartyTypeDeedMapDAO emailEventMappingDAO = new PartyTypeDeedMapDAO();
	public static PartyTypeDeedMapDAO getInstance(){
		return emailEventMappingDAO;
	}
	private PartyTypeDeedMapDAO(){
	}
	/**
	 * To get all email event mappings in the DB.
	 * @return ArrayList all of result
	 */
	public ArrayList getAllPartyTypeDeedMaps() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		PartyTypeDeedMapDTO dto = null;
		DBUtility dbUtility = null;
		try {
			String sql = PartyTypeDeedMapSQL.SELECT_ALL_PARTY_DEED_MAPS;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			System.out.println("Size from the Db   :"+eventsList.size());
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new PartyTypeDeedMapDTO();
				
				dto.setPartyTypeDeedmapID(subList.get(0).toString());
				dto.setDeedTypeName(subList.get(1).toString());
				dto.setPartyTypeName(subList.get(2).toString());
				result.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Size From DAO      :"+result.size());
		return result;
	}
	/**
	 * DELETE EPARTY DEED MAPS
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * @param deptID
	 */
	public void deletePartyTypeDeedMaps(String mapID, String roleId, String funId, String userId) {
		String sql = PartyTypeDeedMapSQL.DELETE_EPARTY_DEED_MAPS;
		String param[] = new String[1];
		param[0]=mapID;
		DBUtility dbUtility = null;
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","DELETE","T",funId,userId,roleId);
				
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","DELETE","F",funId,userId,roleId);
			}
				 
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.getStackTrace();
			}
		}
		
	}
	/**
	 * To Get Deed Types
	 * @return
	 */
	public ArrayList getDeedTypes() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		PartyTypeDeedMapDTO dto = null;
		DBUtility dbUtility = null;
		
		try {
			String sql = PartyTypeDeedMapSQL.SELECT_DEED_TYPES;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			dto = new PartyTypeDeedMapDTO();
			dto.setDeedTypeName("Select Deed >>>");
			dto.setDeedTypeID("Select Deed >>>");
			result.add(dto);
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new PartyTypeDeedMapDTO();
				
				dto.setDeedTypeID((subList.get(0).toString()));
				dto.setDeedTypeName((subList.get(1).toString()));
				
				result.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList getPartyTypes() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		PartyTypeDeedMapDTO dto = null;
		DBUtility dbUtility = null;
		
		try {
			String sql = PartyTypeDeedMapSQL.SELECT_PARTY_TYPES;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList eventsList = dbUtility.executeQuery(sql);
			dto = new PartyTypeDeedMapDTO();
			dto.setPartyTypeName("Select Party >>>");
			dto.setPartyTypeID("Select Party >>>");
			
			result.add(dto);
			for (int i = 0; i < eventsList.size(); i++) {
				subList = (ArrayList) eventsList.get(i);
				dto = new PartyTypeDeedMapDTO();
				
				dto.setPartyTypeID(((subList.get(0).toString())));
				dto.setPartyTypeName(((subList.get(1).toString())));
				
				result.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("size of Party s     ..............."+result.size());
		return result;
	}
	/**
	 * 
	 * @param dto
	 */
	
	public void addPartyDeedMapping(PartyTypeDeedMapDTO dto,String roleId, String funId, String userId) {
		String sql = PartyTypeDeedMapSQL.INSERT_PART_DEED_MAPPING;
		DBUtility dbUtility = null;
		String param[] = new String[2];
		param[0] = dto.getPartyTypeID();
		param[1] = dto.getDeedTypeID();
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean succeed = dbUtility.executeUpdate(param);
			if (succeed) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","INSERT","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","INSERT","F",funId,userId,roleId);
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param mapID
	 * @return
	 */
	public PartyTypeDeedMapDTO getPartyDeedMapByID(String mapID){
		PartyTypeDeedMapDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=mapID;
		try {
			String sql = PartyTypeDeedMapSQL.GET_MAP_BY_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			
			dto = new PartyTypeDeedMapDTO();
			dto.setDeedTypeID(subList.get(0).toString());
			dto.setPartyTypeID(subList.get(1).toString());
			dto.setPartyTypeDeedmapID(mapID);
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	/**
	 * 
	 * @param dto
	 */
		public void updatePartyDeedMap(PartyTypeDeedMapDTO dto,String roleId, String funId, String userId){
	    	String sql = PartyTypeDeedMapSQL.UPDATE_PARTY_DEED_MAPPING;
			DBUtility dbUtility = null;
			String param[] = new String[3];
			param[0] = dto.getPartyTypeID();
			param[1] = dto.getDeedTypeID();
			param[2] = dto.getPartyTypeDeedmapID();
			
			
			try {
				IGRSCommon igrsCommon = new IGRSCommon();
				dbUtility = new DBUtility();
				dbUtility.createPreparedStatement(sql);
				boolean succeed = dbUtility.executeUpdate(param);
				if (succeed) {
					dbUtility.commit();
					igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","INSERT","T",funId,userId,roleId);
					System.out.println(">>>>>>>>>>>>>commit");
				} else {
					System.out.println(">>>>>>>>>>>>>rollback");
					dbUtility.rollback();
					igrsCommon.saveLogDet("IGRS_PARTY_TYPE_DEED_MAP","INSERT","T",funId,userId,roleId);
				}
					 
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dbUtility.closeConnection();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}	
	
		}
	/**
	 * 
	 * @param dto
	 * @return
	 */
		public boolean isMapExists(PartyTypeDeedMapDTO dto){
	    	System.out.println("CALLING...........................ISEXISTS()");
			boolean found = false;
	    	DBUtility dbUtility = null;
	    	String [] param=new String[2];
			param[1]=dto.getDeedTypeID();
			param[0]=dto.getPartyTypeID();
			
			try {
					String sql = PartyTypeDeedMapSQL.IS_MAP_EXISTS;
					dbUtility = new DBUtility();
					dbUtility.createPreparedStatement(sql);
					
					ArrayList municipalList = dbUtility.executeQuery(param);
					
					if(municipalList.isEmpty()){
						found=false;
						System.out.println(">>>>>>>>>>>>>>>>>NOT FOUND");
					}else{
						found=true;
						System.out.println(">>>>>>>>>>>>>>>>>FOUND");
					}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					dbUtility.closeConnection();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return found;
	    }
	
	
	
	}
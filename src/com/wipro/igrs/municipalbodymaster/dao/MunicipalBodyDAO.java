/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MunicipalBodyDAO.java
 * Author      :   Mostafa Mahmoud
 * Description :   Represents the DAO Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mostafa Mahmoud  10th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.municipalbodymaster.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.municipalbodymaster.dto.MunicipalBodyDTO;
import com.wipro.igrs.municipalbodymaster.sql.MunicipalBodyCommonSQL;

public class MunicipalBodyDAO {
	
	private DBUtility dbUtility = null;
	private Logger logger = (Logger) Logger.getLogger(MunicipalBodyDAO.class);
	
	
	/**
	 * MunicipalBodyDAO constructor
	 */
	public MunicipalBodyDAO() {
 
	}
	
	/**
	 * To get all Municipal Bodies in the DB.
	 * @return ArrayList all of MUNICIPAL BODY
	 */
	public ArrayList getAllMunicipalBodies() {
		ArrayList result = new ArrayList();
		ArrayList subList = null;
		MunicipalBodyDTO dto = null;
		try {
			String sql = MunicipalBodyCommonSQL.SELECT_MUNICIPAL_BODY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList municipalList = dbUtility.executeQuery(sql);
			
			for (int i = 0; i < municipalList.size(); i++) {
				subList = (ArrayList) municipalList.get(i);
				if ((subList != null) && (!subList.isEmpty())) {
					dto = new MunicipalBodyDTO();
					if (subList.get(0) != null)
						dto.setMunicipalBodyId(subList.get(0).toString());
					else
						dto.setMunicipalBodyId("");
					if (subList.get(1) != null)
						dto.setMunicipalBodyName(subList.get(1).toString());
					else
						dto.setMunicipalBodyName("");
					if (subList.get(2) != null)
						dto.setMunicipalBodyDesc(subList.get(2).toString());
					else
						dto.setMunicipalBodyDesc("");
					if (subList.get(3) != null)
						dto.setMunicipalBodyStatus(subList.get(3).toString());
					else
						dto.setMunicipalBodyStatus("");
					if (subList.get(4) != null)
						dto.setMunicipalBodyCreatedById(subList.get(4).toString());
					else
						dto.setMunicipalBodyCreatedById("");
					if (subList.get(5) != null)
						dto.setMunicipalBodyCreatedDate(subList.get(5).toString());
					else
						dto.setMunicipalBodyCreatedDate("");
					if (subList.get(6) != null)
						dto.setMunicipalBodyModifiedById(subList.get(6).toString());
					else
						dto.setMunicipalBodyModifiedById("");
					if (subList.get(7) != null)
						dto.setMunicipalBodyModificationDate(subList.get(7).toString());
					else
						dto.setMunicipalBodyModificationDate("");
					result.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public void updateMunicipalBody(MunicipalBodyDTO bean) {
		String sql = MunicipalBodyCommonSQL.UPDATE_MUNICIPAL_BODY_MASTER;
		String param[] = new String[5];
		param[0] = bean.getMunicipalBodyName();
		param[1] = bean.getMunicipalBodyDesc();
		param[2] = bean.getMunicipalBodyStatus();
		param[3] = bean.getMunicipalBodyModifiedById();
		param[4] = bean.getMunicipalBodyId();
	
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean queryExcuted = dbUtility.executeUpdate(param);
			
			if (queryExcuted) {
				dbUtility.commit();
			}else {
				dbUtility.rollback();
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
	}
	
	public void addMunicipalBody(MunicipalBodyDTO bean) {
		String sql = MunicipalBodyCommonSQL.INSERT_MUNICIPAL_BODY_MASTER;
		String param[] = new String[4];
		param[0] = bean.getMunicipalBodyName();
		param[1] = bean.getMunicipalBodyDesc();
		param[2] = bean.getMunicipalBodyCreatedById();
		param[3] = bean.getMunicipalBodyModifiedById();
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
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
	
	public void deleteMunicipalBody(MunicipalBodyDTO bean) {
		String municipalBodyId = bean.getMunicipalBodyId();
		String sql = MunicipalBodyCommonSQL.DELETE_MUNICIPAL_BODY_MASTER;
		String param[] = new String[] {municipalBodyId};

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean queryExcuted = dbUtility.executeUpdate(param);
			
			if (queryExcuted) {
				dbUtility.commit();
			} else {
				dbUtility.rollback();
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
	
	public MunicipalBodyDTO getMunicipalBodyById(MunicipalBodyDTO bean) {
		String municipalBodyId = bean.getMunicipalBodyId();
		MunicipalBodyDTO result = null;
		try {
			String sql = MunicipalBodyCommonSQL.SELECT_MUNICIPAL_BODY_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList municipalList = dbUtility.executeQuery(new String[] {municipalBodyId});
			if ((municipalList != null) && (!municipalList.isEmpty())) {
				ArrayList subList = (ArrayList) municipalList.get(0);
				result = new MunicipalBodyDTO();
				result.setMunicipalBodyId(municipalBodyId);
				if ((subList != null) && (!subList.isEmpty())) {
					if (subList.get(0) != null)
						result.setMunicipalBodyName(subList.get(0).toString());
					else
						result.setMunicipalBodyName("");
					if (subList.get(1) != null)
						result.setMunicipalBodyDesc(subList.get(1).toString());
					else
						result.setMunicipalBodyDesc("");
					if (subList.get(2) != null)
						result.setMunicipalBodyStatus(subList.get(2).toString());
					else
						result.setMunicipalBodyStatus("");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public MunicipalBodyDTO getMunicipalBodyByName(MunicipalBodyDTO bean) {
		String municipalBodyName = bean.getMunicipalBodyName();
		MunicipalBodyDTO result = null;
		try {
			String sql = MunicipalBodyCommonSQL.SELECT_MUNICIPAL_BODY_MASTER_NAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList municipalList = dbUtility.executeQuery(new String[] {municipalBodyName});	
			if ((municipalList!= null) && (!municipalList.isEmpty())) {
				ArrayList subList = (ArrayList) municipalList.get(0);
				result = new MunicipalBodyDTO();
				result.setMunicipalBodyName(municipalBodyName);
				if (subList.get(0) != null)
					result.setMunicipalBodyId(subList.get(0).toString());
				else
					result.setMunicipalBodyId("");
				if (subList.get(1) != null)
					result.setMunicipalBodyDesc(subList.get(1).toString());
				else
					result.setMunicipalBodyDesc("");
				if (subList.get(2) != null)
					result.setMunicipalBodyStatus(subList.get(2).toString());
				else
					result.setMunicipalBodyStatus("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public boolean isMunicipalBodyExist(MunicipalBodyDTO bean) {
		String municipalBodyName = bean.getMunicipalBodyName();
		boolean result = false;
		try {
			String sql = MunicipalBodyCommonSQL.SELECT_MUNICIPAL_BODY_MASTER_NAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			ArrayList list = dbUtility.executeQuery(new String[]{municipalBodyName});
			
			if ((list!= null) && (!list.isEmpty())) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtility.closeConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}

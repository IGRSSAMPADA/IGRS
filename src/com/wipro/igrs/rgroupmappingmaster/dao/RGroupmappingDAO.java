/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rgroupmappingmaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;
import com.wipro.igrs.rgroupmappingmaster.sql.RGroupmappingCommonSQL;

public class RGroupmappingDAO {

	private ArrayList roleList = new ArrayList();
	private ArrayList rolegroupList = new ArrayList();
	private ArrayList rgroupmappingList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	RGroupmappingDTO dto = null;

	/* DAO constructor */
	public RGroupmappingDAO() {
	}

	/* ADD RGROUPMAPPING MASTER */

	public void addRgroupmappingmaster(RGroupmappingForm rgroupmappingForm, String roleId, String funId, String userId) {
		sql = RGroupmappingCommonSQL.INSERT_RGROUP_ROLE_MAPPING_MASTER;
		String param[] = new String[2];
		param[0] = rgroupmappingForm.getRolegroupId();
		param[1] = rgroupmappingForm.getRoleId();

		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_RGROUP_ROLE_MAPPING","INSERT","T",funId,userId,roleId);
			}

			else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_RGROUP_ROLE_MAPPING","INSERT","F",funId,userId,roleId);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}
	}

	/* GET ROLE LIST */
	public ArrayList getRoleList() throws Exception {
		try {
			sql = RGroupmappingCommonSQL.SELECT_ROLE_MASTER;
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			// System.out.println("IN DAO mainList1 ==="+mainList1);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new RGroupmappingDTO();
				dto.setValue(subList.get(0).toString());
				dto.setName(subList.get(1).toString());
				roleList.add(dto);
				// System.out.println("IN DAO roleList==="+roleList);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
			dbUtility.rollback();
		}
		return roleList;

	}

	/* GET ROLE GROUP LIST */
	public ArrayList getRolegroupList() throws Exception {
		try {
			sql = RGroupmappingCommonSQL.SELECT_ROLE_GROUP_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new RGroupmappingDTO();
				dto.setValue(subList.get(0).toString());
				dto.setName(subList.get(1).toString());

				rolegroupList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}
		return rolegroupList;

	}

	/* GET RGROUPMAPPING LIST */

	public ArrayList getRgroupmappingList() throws Exception {
		try {
			sql = RGroupmappingCommonSQL.SELECT_RGROUP_MAPPING_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			System.out.println("in DAo mainList1==" + mainList1);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				// System.out.println("sublist value..."+subList);
				dto = new RGroupmappingDTO();
				dto.setRolegroupName(subList.get(0).toString());
				dto.setRolegroupId(subList.get(1).toString());
				dto.setRoleName(subList.get(2).toString());
				dto.setRoleId(subList.get(3).toString());
				// dto.setActiveFlag(subList.get(2).toString());

				rgroupmappingList.add(dto);
			}
			System.out.println("out  of for in DAo rgroupmappingList=="
					+ rgroupmappingList);
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return rgroupmappingList;
	}

	/* UPDATE RGROUPMAPPING MASTER */

	public void updateRgroupmappingmaster(RGroupmappingForm rgroupmappingForm, String roleId, String funId, String userId) {

		dto = new RGroupmappingDTO();
		String param[] = new String[2];
		param[0] = rgroupmappingForm.getDto().getActiveFlag();
		param[1] = rgroupmappingForm.getDto().getRolegroupId();
		sql = RGroupmappingCommonSQL.UPDATE_RGROUP_MAPPING_MASTER;

		try {
			dbUtility = new DBUtility();
			 IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_RGROUP_ROLE_MAPPING","UPDATE","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_RGROUP_ROLE_MAPPING","UPDATE","F",funId,userId,roleId);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}
	}

	/* GET RGROUPMAPPING ID */
	public RGroupmappingDTO getRolegrouopId(String rolegroupid)
			throws Exception {
		try {
			dbUtility = new DBUtility();
			sql = RGroupmappingCommonSQL.SELECT_RGROUP_MAPPING_MASTER_ID;
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = rolegroupid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new RGroupmappingDTO();
			dto.setRolegroupId(list1.get(0).toString());
			dto.setRoleId(list1.get(1).toString());
			dto.setActiveFlag(list1.get(2).toString());

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

}

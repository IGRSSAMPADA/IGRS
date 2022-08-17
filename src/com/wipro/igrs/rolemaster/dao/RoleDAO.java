/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for RoleMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rolemaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.rolemaster.form.RoleForm;
import com.wipro.igrs.rolemaster.sql.RoleCommonSQL;

public class RoleDAO {

	private ArrayList roleList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	RoleDTO dto = null;

	public RoleDAO() {

	}

	/* ADD ROLE MASTER */

	public void addRolemaster(RoleForm roleForm,String roleId, String funId, String userId) {

		sql = RoleCommonSQL.INSERT_ROLE_MASTER;
		String param[] = new String[2];
		param[0] = roleForm.getRoleName();
		param[1] = roleForm.getRoleDesc();

		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","INSERT","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","INSERT","F",funId,userId,roleId);
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

	/* GET REGISTRATIONOFFICE LIST */

	public ArrayList getRoleList() throws Exception {
		try {
			sql = RoleCommonSQL.SELECT_ROLE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				System.out.println("sublist value..." + subList);
				dto = new RoleDTO();
				dto.setRoleId(subList.get(0).toString());
				dto.setRoleName(subList.get(1).toString());
				dto.setRoleDesc(subList.get(2).toString());
				dto.setRoleStatus(subList.get(3).toString());
				roleList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return roleList;
	}

	/* UPDATE REGISTRATIONOFFICE MASTER */

	public void updateRolemaster(RoleForm roleForm,String roleId, String funId, String userId) {
		dto = new RoleDTO();
		String param[] = new String[4];
		param[0] = roleForm.getDto().getRoleName();
		param[1] = roleForm.getDto().getRoleDesc();
		param[2] = roleForm.getDto().getRoleStatus();
		param[3] = roleForm.getRoleId();
		sql = RoleCommonSQL.UPDATE_ROLE_MASTER;

		try {
			dbUtility = new DBUtility();
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","UPDATE","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_ROLE_MASTER","UPDATE","F",funId,userId,roleId);
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

	/* GET REGISTRATIONOFFICE ID */
	public RoleDTO getRoleId(String roleid) throws Exception {
		try {

			sql = RoleCommonSQL.SELECT_ROLE_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = roleid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new RoleDTO();
			dto.setRoleId(list1.get(0).toString());
			dto.setRoleName(list1.get(1).toString());
			dto.setRoleDesc(list1.get(2).toString());
			dto.setRoleStatus(list1.get(3).toString());

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

}

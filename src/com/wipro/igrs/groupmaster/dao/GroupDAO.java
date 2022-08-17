/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.groupmaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.groupmaster.dto.GroupDTO;
import com.wipro.igrs.groupmaster.form.GroupForm;
import com.wipro.igrs.groupmaster.sql.GroupCommonSQL;

public class GroupDAO {

	private ArrayList groupList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	GroupDTO dto = null;

	/* DAO constructor */
	public GroupDAO() {

	}

	/* ADD ROLE MASTER */

	public void addGroupmaster(GroupForm groupForm) {

		sql = GroupCommonSQL.INSERT_ROLE_GROUP_MASTER;
		String param[] = new String[2];
		param[0] = groupForm.getRgroupName();
		param[1] = groupForm.getRgroupDesc();

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();

			} else
				dbUtility.rollback();

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

	public ArrayList getGroupList() throws Exception {
		try {
			dbUtility = new DBUtility();
			sql = GroupCommonSQL.SELECT_ROLE_GROUP_MASTER;
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				// System.out.println("sublist value..."+subList);
				dto = new GroupDTO();
				dto.setRgroupId(subList.get(0).toString());
				dto.setRgroupName(subList.get(1).toString());
				dto.setRgroupDesc(subList.get(2).toString());
				dto.setRgroupStatus(subList.get(3).toString());
				groupList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {

			dbUtility.closeConnection();

		}
		return groupList;
	}

	/* UPDATE REGISTRATIONOFFICE MASTER */

	public void updateGroupmaster(GroupForm groupForm) {

		dto = new GroupDTO();
		String param[] = new String[4];
		param[0] = groupForm.getDto().getRgroupName();

		param[1] = groupForm.getDto().getRgroupDesc();
		param[2] = groupForm.getDto().getRgroupStatus();
		param[3] = groupForm.getDto().getRgroupId();
		sql = GroupCommonSQL.UPDATE_ROLE_GROUP_MASTER;

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
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
	public GroupDTO getGroupId(String groupid) throws Exception {
		try {
			dbUtility = new DBUtility();
			sql = GroupCommonSQL.SELECT_ROLE_GROUP_MASTER_ID;
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = groupid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new GroupDTO();
			dto.setRgroupId(list1.get(0).toString());
			dto.setRgroupName(list1.get(1).toString());
			dto.setRgroupDesc(list1.get(2).toString());
			dto.setRgroupStatus(list1.get(3).toString());

		} catch (Exception e) {
			e.getStackTrace();
		} finally {

			dbUtility.closeConnection();

		}
		return dto;
	}

}

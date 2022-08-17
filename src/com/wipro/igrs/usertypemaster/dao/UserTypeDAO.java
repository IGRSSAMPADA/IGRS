/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the DAO Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.sql.UserTypeCommonSQL;

public class UserTypeDAO {

	private ArrayList userTypeList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	UserTypeDTO dto = null;
	String userTypeID = null;
	private Logger logger = (Logger) Logger.getLogger(UserTypeDAO.class);

	/* DAO constructor */
	public UserTypeDAO() {

	}

	/* ADD USERTYPE MASTER */

	public void addUserTypemaster(UserTypeDTO dto) {
		sql = UserTypeCommonSQL.INSERT_USER_TYPE_MASTER;
		String param[] = new String[4];
		param[0] = dto.getUserTypeName();
		param[1] = dto.getUserTypeDesc();
		param[2] = dto.getCreatedBy();
		param[3] = dto.getUpdatedBy();

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
			// e.getStackTrace();
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
				x.printStackTrace();
			}
		}
	}

	/* GET USERTYPE LIST */

	public ArrayList getUserTypeList() throws Exception {
		try {
			sql = UserTypeCommonSQL.SELECT_USER_TYPE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new UserTypeDTO();
				dto.setUserTypeId((String) subList.get(0));
				dto.setUserTypeName((String) subList.get(1));
				dto.setUserTypeDesc((String) subList.get(2));
				dto.setUserTypeStatus((String) subList.get(3));
				dto.setCreatedBy((String) subList.get(4));
				dto.setCreatedDate((String) subList.get(5));
				dto.setUpdatedBy((String) subList.get(6));
				dto.setUpdatedDate((String) subList.get(7));

				userTypeList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return userTypeList;
	}

	/* UPDATE USERTYPE MASTER */

	public void updateUserTypemaster(UserTypeDTO dto) {

		String param[] = new String[5];
		param[0] = dto.getUserTypeName();
		param[1] = dto.getUserTypeDesc();
		param[2] = dto.getUserTypeStatus();
		param[3] = dto.getUpdatedBy();
		param[4] = dto.getUserTypeId();
		sql = UserTypeCommonSQL.UPDATE_USER_TYPE_MASTER;

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
			// e.getStackTrace();
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				// x.getStackTrace();
				x.printStackTrace();
			}
		}
	}

	/* GET USERTYPE ID */

	public UserTypeDTO getUserTypeId(String userid) throws Exception {
		try {
			sql = UserTypeCommonSQL.SELECT_USER_TYPE_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = userid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new UserTypeDTO();
			dto.setUserTypeId((String) list1.get(0));
			dto.setUserTypeName((String) list1.get(1));
			dto.setUserTypeDesc((String) list1.get(2));
			dto.setUserTypeStatus((String) list1.get(3));
			dto.setCreatedBy((String) list1.get(4));
			dto.setCreatedDate((String) list1.get(5));
			dto.setUpdatedBy((String) list1.get(6));
			dto.setUpdatedDate((String) list1.get(7));

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	public UserTypeDTO getUserTypeName(String userTypeName) throws Exception {
		try {
			sql = UserTypeCommonSQL.SELECT_USER_TYPE_MASTER_NAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = userTypeName;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new UserTypeDTO();

			dto.setUserTypeId((String) list1.get(0));
			dto.setUserTypeName((String) list1.get(1));
			dto.setUserTypeDesc((String) list1.get(2));
			dto.setUserTypeStatus((String) list1.get(3));
			dto.setCreatedBy((String) list1.get(4));
			dto.setCreatedDate((String) list1.get(5));
			dto.setUpdatedBy((String) list1.get(6));
			dto.setUpdatedDate((String) list1.get(7));

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

}

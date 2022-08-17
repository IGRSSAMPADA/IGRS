/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;
import com.wipro.igrs.mohallavillagemaster.form.MohallavillageForm;
import com.wipro.igrs.mohallavillagemaster.sql.MohallavillageCommonSQL;

public class MohallavillageDAO {

	private ArrayList mohallaList = new ArrayList();
	private ArrayList wardpatwariList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	MohallavillageDTO dto = null;

	public MohallavillageDAO() {

	}

	/* ADD MOHALLAVILLAGE MASTER */
	public void addMohallavilagemaster(MohallavillageForm mohallavillageForm) {
		sql = MohallavillageCommonSQL.INSERT_MOHALLAVILAGE_MASTER;
		String param[] = new String[3];
		param[0] = mohallavillageForm.getMohallavillageName();
		param[1] = mohallavillageForm.getMohallavillageDesc();
		param[2] = mohallavillageForm.getWardpatwariId();

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
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}
	}

	/* GET MOHALLAVILLAGE ID */
	public MohallavillageDTO getMohallavillageId(String mohallaid)
			throws Exception {
		try {
			sql = MohallavillageCommonSQL.SELECT_MOHALLAVILAGE_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = mohallaid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			// System.out.println(list1);
			dto = new MohallavillageDTO();
			dto.setMohallavillageId(list1.get(0).toString());
			dto.setMohallavillageName(list1.get(1).toString());
			dto.setMohallavillageDesc(list1.get(2).toString());
			dto.setMohallavillageStatus(list1.get(3).toString());
			dto.setWardpatwariId(list1.get(4).toString());
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	/* GET MOHALLAVILLAGE LIST */
	public ArrayList getMohallavillageList() throws Exception {
		try {
			sql = MohallavillageCommonSQL.SELECT_MOHALLAVILAGE_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				// System.out.println("sublist value..."+subList);
				dto = new MohallavillageDTO();
				dto.setMohallavillageId(subList.get(0).toString());
				dto.setMohallavillageName(subList.get(1).toString());
				dto.setMohallavillageDesc(subList.get(2).toString());
				dto.setMohallavillageStatus(subList.get(3).toString());
				dto.setWardpatwariId(subList.get(4).toString());
				mohallaList.add(dto);
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return mohallaList;
	}

	/* UPDATE MOHALLAVILLAGE MASTER */
	public void updateMohallavilagemaster(MohallavillageForm mohallavillageForm) {
		dto = new MohallavillageDTO();
		String param[] = new String[4];
		param[0] = mohallavillageForm.getDto().getMohallavillageName();
		param[1] = mohallavillageForm.getDto().getMohallavillageDesc();
		param[2] = mohallavillageForm.getDto().getMohallavillageStatus();
		param[3] = mohallavillageForm.getDto().getMohallavillageId();
		sql = MohallavillageCommonSQL.UPDATE_MOHALLAVILAGE_MASTER;

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
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception x) {
				x.getStackTrace();
			}
		}

	}

	/* GET MOHALLAVILLAGE WARDPATWARILIST */
	public ArrayList getWardpatwariList() throws Exception {
		try {
			sql = MohallavillageCommonSQL.SELECT_MOHALLAVILAGE_WARD_PATWARI;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new MohallavillageDTO();
				dto.setName(subList.get(0).toString());
				dto.setValue(subList.get(0).toString());
				wardpatwariList.add(dto);
				// dbUtility.commit();
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return wardpatwariList;

	}
}

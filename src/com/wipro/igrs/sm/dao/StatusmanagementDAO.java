/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementDAO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DAO Class for Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.sm.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.sm.dto.StatusmanagementDTO;
import com.wipro.igrs.sm.form.StatusmanagementForm;
import com.wipro.igrs.sm.sql.StatusmanagementCommonSQL;

public class StatusmanagementDAO {

	private ArrayList statusList = new ArrayList();

	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	StatusmanagementDTO dto = null;

	/* DAO constructor */
	public StatusmanagementDAO() {

	}

	/* Get employee list */
	public StatusmanagementDTO getEmpList(String eno) throws Exception {
		try {
			dbUtility = new DBUtility();
			System.out.println("we are in getEmpList method");
			sql = StatusmanagementCommonSQL.SELECT_EMP_ID;
			String en[] = new String[1];
			dbUtility.createPreparedStatement(sql);
			en[0] = eno;
			ArrayList list = dbUtility.executeQuery(en);
			ArrayList list1 = (ArrayList) list.get(0);
			System.out.println("list1+++++++=" + list1);
			dto = new StatusmanagementDTO();
			System.out.println("list1=" + list1.size());
			dto.setEno(list1.get(0).toString());
			dto.setFirstName(list1.get(1).toString());
			dto.setLastName(list1.get(2).toString());
			dto.setLocation(list1.get(3).toString());
			dto.setCurrentStatus(list1.get(4).toString());

			System.out.println("after setCurrentStatus=================="
					+ list1.get(4).toString());

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return dto;
	}

	/* Get status list */

	/*
	 * public ArrayList<StatusmanagementDTO> getStatusList()throws Exception {
	 * try { sql = StatusmanagementCommonSQL.SELECT_ACTIVITY_STATUS;
	 * dbUtility.createStatement();
	 * 
	 * ArrayList mainList1= dbUtility.executeQuery(sql); for (int i=0;i<mainList1.size();i++) {
	 * subList = (ArrayList)mainList1.get(i); dto = new StatusmanagementDTO();
	 * dto.setName(subList.get(0).toString());
	 * dto.setValue(subList.get(0).toString()); statusList.add(dto);
	 * //dbUtility.commit(); } } catch (Exception e) { e.getStackTrace();
	 * dbUtility.rollback(); } return statusList;
	 *  }
	 */

	/* update employee list */

	public boolean updateemp(StatusmanagementForm statusmanagementForm)
			throws Exception {
		dbUtility = new DBUtility();
		boolean boo = false;
		dto = new StatusmanagementDTO();
		String param[] = new String[2];
		param[0] = statusmanagementForm.getDto().getCurrentStatus();
		param[1] = statusmanagementForm.getDto().getEno();
		sql = StatusmanagementCommonSQL.UPDATE_EMP_DETAILS;

		try {
			dbUtility.createPreparedStatement(sql);
			boo = dbUtility.executeUpdate(param);
			if (boo) {
				System.out.println("booooooooooooooooo" + boo);
				dbUtility.commit();
			}

			else {
				dbUtility.rollback();
			}

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			dbUtility.closeConnection();
		}
		return boo;
	}
}

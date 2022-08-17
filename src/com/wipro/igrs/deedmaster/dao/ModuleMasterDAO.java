/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDAO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DAO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedmaster.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.deedmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.deedmaster.form.ModuleMasterForm;
import com.wipro.igrs.deedmaster.sql.CommonSQL;

public class ModuleMasterDAO {

	DBUtility dbUtility = null;
	Connection con = null;
	Statement st = null;

	public ModuleMasterDAO() {
		try {
			dbUtility = new DBUtility();
		} catch (Exception e) {
			System.out.println("in ModuleMasterDAO start" + e.getStackTrace());
		}

	}

	/**
	 * check the availability of the deep type 's name
	 * 
	 * @param name
	 * @return
	 */
	public boolean isDeepTypeExists(String name) {
		String SQL = CommonSQL.IS_DEEP_TYPE_EXIST;
		boolean found = false;
		DBUtility dbUtility = null;
		String[] param = new String[1];
		param[0] = name;
		try {
			String sql = CommonSQL.IS_DEEP_TYPE_EXIST;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);

			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);
			if (subList.isEmpty()) {
				found = false;
				System.out.println("not exist........................");
			} else {
				found = true;
				System.out.println("exist........................");
			}
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		return found;
	}

	/**
	 * Inserting Module Details
	 * 
	 * @param params
	 */
	public void addModuleDetails(String params[]) {
		String SQL = CommonSQL.INSERT_MODULE_MASTER;

		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected list of records based on listType submodule/function
	 * for drop box
	 * 
	 * @param listType
	 * @return ArrayList
	 */
	public ArrayList getModulesList(String listType) {
		String SQL = null;
		if (listType.equalsIgnoreCase("submodule")) {
			SQL = CommonSQL.SELECT_MODULES_LIST;
		}
		if (listType.equalsIgnoreCase("function")) {
			SQL = CommonSQL.SELECT_SUB_MODULES_LIST;
		}
		if (listType.equalsIgnoreCase("instrument")) {
			SQL = CommonSQL.SELECT_DEEDS_LIST;
		}
		if (listType.equalsIgnoreCase("exemption")) {
			SQL = CommonSQL.SELECT_INSTRUMENTS_LIST;
		}

		ArrayList mainlist = null;
		ArrayList list = null;
		try {
			dbUtility.createStatement();
			mainlist = new ArrayList();
			ArrayList list1 = dbUtility.executeQuery(SQL);
			for (int i = 0; i < list1.size(); i++) {
				list = (ArrayList) list1.get(i);
				ModuleMasterDTO dto = new ModuleMasterDTO();
				dto.setId(list.get(0).toString());
				dto.setName(list.get(1).toString());
				mainlist.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainlist;
	}

	/**
	 * Inserting sub module details
	 * 
	 * @param params
	 */
	public void addSubModuleDetails(String params[]) {
		String SQL = CommonSQL.INSERT_SUB_MODULE_MASTER;
		System.out.println(SQL);
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserting function details
	 * 
	 * @param params
	 */
	public void addFunctionDetails(String params[]) {
		String SQL = CommonSQL.INSERT_FUNCTION_MASTER;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserting deed type details
	 * 
	 * @param params
	 */
	public void addDeedDetails(String params[]) {
		System.out.println("From Daoooooo");
		String SQL = CommonSQL.INSERT_DEED_TYPE_MASTER;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected list of records based on listType
	 * moduleList/submoduleList/functionList/clauseList/deedList
	 * 
	 * @param listType
	 * @return ArrayList
	 */
	public ArrayList getModulesDetails(String listType) {
		String SQL = null;
		if (listType.equalsIgnoreCase("moduleList")) {
			SQL = CommonSQL.SELECT_MODULE_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("submoduleList")) {
			SQL = CommonSQL.SELECT_SUB_MODULE_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("functionList")) {
			SQL = CommonSQL.SELECT_FUNCTION_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("clauseList")) {
			SQL = CommonSQL.SELECT_SUB_CLAUSE_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("deedList")) {
			SQL = CommonSQL.SELECT_DEED_TYPE_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("instrumentList")) {
			SQL = CommonSQL.SELECT_INSTRUMENT_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("exemptionList")) {
			SQL = CommonSQL.SELECT_EXEMPTION_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("test")) {
			SQL = CommonSQL.SELECT_EXEMPTION_MASTER_LIST;
		}
		if (listType.equalsIgnoreCase("deedview")) {
			SQL = CommonSQL.SELECT_DEED_TYPE_NAME;
		}
		ArrayList mainlist = null;
		ArrayList list = null;
		try {
			dbUtility.createStatement();
			mainlist = new ArrayList();
			ArrayList list1 = dbUtility.executeQuery(SQL);
			for (int i = 0; i < list1.size(); i++) {
				list = (ArrayList) list1.get(i);
				ModuleMasterDTO dto = new ModuleMasterDTO();
				dto.setId(list.get(0).toString());
				dto.setName(list.get(1).toString());
				dto.setDescription(list.get(2).toString());
				mainlist.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainlist;
	}

	/**
	 * returns the selected module details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getModuleDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_MODULE;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setDescription(list.get(2).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * returns Y/N depends upon the boolean value
	 * 
	 * @param boo
	 * @return String
	 */
	public String getTrueOrFalse(boolean boo) {
		String temp = null;
		if (boo) {
			temp = "A";
		} else {
			temp = "D";
		}
		return temp;
	}

	/**
	 * returns Y/N depends upon the boolean value
	 * 
	 * @param boo
	 * @return String
	 */
	public String getYOrN(String _tmp) {

		if (_tmp != null && _tmp.equalsIgnoreCase("YES")) {
			_tmp = "Y";
		} else {
			_tmp = "N";
		}
		return _tmp;
	}

	/**
	 * returns true/false depends upon the Y/N value
	 * 
	 * @param temp
	 * @return boolean
	 */
	public boolean getTrueOrFalse(String temp) {
		boolean boo;
		if (temp.equalsIgnoreCase("A")) {
			boo = true;
			;
		} else {
			boo = false;
		}
		return boo;
	}

	/**
	 * returns true/false depends upon the Y/N value
	 * 
	 * @param temp
	 * @return boolean
	 */
	public String getYesOrNo(String temp) {
		String boo;
		if (temp == null)
			temp = "";
		if (temp.equalsIgnoreCase("Y")) {
			boo = "YES";
			;
		} else {
			boo = "NO";
		}
		return boo;
	}

	/**
	 * returns the selected sub module details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getSubModuleDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_SUB_MODULE;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setModulename(list.get(4).toString());
			form.setDescription(list.get(2).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * returns the selected function details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getFunctionDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_FUNCTION;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setModulename(list.get(4).toString());
			form.setPayment(getTrueOrFalse(list.get(5).toString()));
			form.setIntimation(getTrueOrFalse(list.get(6).toString()));
			form.setDescription(list.get(2).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * updates the module details
	 * 
	 * @param params
	 */
	public void updateModuleDetails(String params[]) {

		String SQL = CommonSQL.UPDATE_MODULE;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * updates the sub module details
	 * 
	 * @param params
	 */
	public void updateSubModuleDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_SUB_MODULE;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * updates the function details
	 * 
	 * @param params
	 */
	public void updateFunctionDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_FUNCTION;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserting Sub Clause Details
	 * 
	 * @param params
	 */
	public void addSubClauseDetails(String params[]) {
		String SQL = CommonSQL.INSERT_SUB_CLAUSE_MASTER;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected sub clause details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getSubClauseDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_SUB_CLAUSE;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setDescription(list.get(2).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * UPDATE SUB CLAUSE DETAILS
	 * 
	 * @param params
	 */
	public void updateSubClauseDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_SUB_CLAUSE;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected deed type details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ArrayList getDeedNameDetails() {
		String SQL = CommonSQL.SELECT_DEED_TYPE_NAME;
		// DEED_TYPE_ID, DEED_TYPE_NAME,DEED_STATUS

		ArrayList retList = new ArrayList();

		try {
			dbUtility.createStatement();
			ArrayList list1 = dbUtility.executeQuery(SQL);
			if (list1.size() > 0) {
				ArrayList list = (ArrayList) list1.get(0);
				ModuleMasterForm form = new ModuleMasterForm();
				form.setId(list.get(0).toString());
				form.setName(list.get(1).toString());
				retList.add(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	/**
	 * returns the selected deed type details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getDeedTypeDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_DEED_TYPE;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setPayment(getTrueOrFalse(list.get(4).toString()));
			form.setDescription(list.get(2).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * returns the selected deed type details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ArrayList getDeedIdDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_DEED_TYPE_SEARCH;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList retList = new ArrayList();

		try {
			dbUtility.createPreparedStatement(SQL);
			System.out.println(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			if (list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					/**
					 * DEED_TYPE_ID ,DEED_TYPE_NAME,
					 * DEED_TYPE_DESCRIPTION,DEED_STATUS,DEED_CATEGORY,PIN_REQUIRED,PROPERTY_RELATED_DEED,
					 * ROR_REQUIRED,DEED_PRIORITY,PROPERTY_VAL_REQD,DUTY_CAL_REQD
					 * FROM IGRS_DEED_TYPE_MASTER where DEED_TYPE_ID
					 */

					ArrayList list = (ArrayList) list1.get(i);
					form = new ModuleMasterForm();
					form.setId(list.get(0).toString());
					form.setName(list.get(1).toString());
					form.setDescription(list.get(2).toString());
					form.setDeedStatus(list.get(3).toString());
					if (list.get(4) != null) {
						form.setDeedCategory(list.get(4).toString().toString());
					}
					if (list.get(5) != null) {
						if (list.get(5).toString().equals("Y")) {
							form.setDeedreq(true);
						} else {
							form.setDeedreq(false);
						}
					}
					if (list.get(6) != null) {
						if (list.get(6).toString().equals("Y")) {
							form.setDeedlinkprop(true);
						} else {
							form.setDeedlinkprop(false);
						}
					}
					if (list.get(7) != null) {
						if (list.get(7).toString().equals("Y")) {
							form.setDeedRor(true);
						} else {
							form.setDeedRor(false);
						}
					}
					if (list.get(8) != null) {
						form.setDeedPeriority(list.get(8).toString());
					}
					if (list.get(9) != null) {
						if (list.get(9).toString().equals("Y")) {
							form.setPropertyValuerequired(true);
						} else {
							form.setPropertyValuerequired(false);
						}
					}
					if (list.get(10) != null) {
						if (list.get(10).toString().equals("Y")) {
							form.setDutyCelRequired(true);
						} else {
							form.setDutyCelRequired(false);
						}
					}
					System.out.println("data to edit \n"
							+ list.get(0).toString() + list.get(1).toString()
							+ list.get(2).toString() + list.get(3).toString());
					retList.add(form);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retList;
	}

	/**
	 * UPDATE DEED TYPE DETAILS
	 * 
	 * @param params
	 */
	public void updatedDeedTypeDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_DEED_TYPE;
		System.out.println(SQL);
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserting Instrument Details
	 * 
	 * @param params
	 */
	public void addInstrumentDetails(String params[]) {
		String SQL = CommonSQL.INSERT_INSTRUMENT_MASTER;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected Instrument details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getInstrumentDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_INSTRUMENT;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(3).toString()));
			form.setModulename(list.get(4).toString());
			form.setDescription(list.get(2).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * updates the Instrument details
	 * 
	 * @param params
	 */
	public void updateInstrumentDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_INSTRUMENT;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * INSERTS the Exemption details
	 * 
	 * @param params
	 */
	public void addExemptionDetails(String params[]) {
		String SQL = CommonSQL.INSERT_EXEMPTION_MASTER;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the selected Exemption details
	 * 
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getExemptionDetails(String linkParam) {
		String SQL = CommonSQL.SELECT_EXEMPTION;

		String arr[] = new String[1];
		arr[0] = linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1 = dbUtility.executeQuery(arr);
			list = (ArrayList) list1.get(0);
			form = new ModuleMasterForm();
			form.setId(list.get(0).toString());
			form.setName(list.get(1).toString());
			form.setStatus(getTrueOrFalse(list.get(5).toString()));
			form.setSubname(list.get(3).toString());
			if (list.get(4) != null)
				form.setModulename(list.get(4).toString());
			else
				form.setModulename("");
			form.setDescription(list.get(2).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return form;
	}

	/**
	 * updates the Exemption Details
	 * 
	 * @param params
	 */

	public void updateExemptionDetails(String params[]) {
		String SQL = CommonSQL.UPDATE_EXEMPTION;
		try {
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDeedTypeByName(String string) {
		String[] param = new String[1];
		param[0] = string;
		String id = null;
		try {
			String sql = CommonSQL.GET_DEEP_TYPE_BYNAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);

			ArrayList municipalList = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) municipalList.get(0);

			id = subList.get(0).toString();

		} catch (Exception e) {
			e.getStackTrace();
		}
		return id;
	}
}

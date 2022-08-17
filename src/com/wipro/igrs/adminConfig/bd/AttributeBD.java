/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.adminConfig.dao.AttributeDAO;
import com.wipro.igrs.adminConfig.form.AdminAttrForm;
import com.wipro.igrs.empmgmt.dto.DropDownDTO;

/**
* 
* AttributeBD.java <br>
* AttributeBD <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class AttributeBD {
	
	private Logger logger = (Logger) Logger.getLogger(AttributeBD.class);

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList gettingData(AdminAttrForm form) throws Exception {
		AttributeDAO dao = new AttributeDAO();

		String param2[] = new String[3];
		ArrayList tlist = null;
		param2[0] = form.getModuleID();
		param2[1] = form.getSubModuleID();
		param2[2] = form.getFunctionID();
		tlist = new ArrayList();
		tlist = dao.gettingData(param2);// getting Attribute data based on
										// moduleid,submoduleid and funcid
//		logger.debug("list of attributedata" + tlist);
		return tlist;
	}

	/**
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public ArrayList displayData(AdminAttrForm form) throws Exception {
		AttributeDAO dao = new AttributeDAO();

		String param2[] = new String[3];
		ArrayList tlist = null;
		param2[0] = form.getModuleID();
		param2[1] = form.getSubModuleID();
		param2[2] = form.getFunctionID();
		tlist = new ArrayList();
		tlist = dao.displayData(param2);// getting Attribute data based on
										// moduleid,submoduleid and funcid
		return tlist;
	}

	/**
	 * 
	 * @param form
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean addingData(AdminAttrForm form, String roleId, String funId, String userId) throws Exception {
		AttributeDAO dao = new AttributeDAO();
		boolean insertflag = false;

		String param2[] = new String[7];
		String subModuleID = dao.getSubModuleID(form.getFunctionID());
		form.setSubModuleID(subModuleID);
		param2[2] = form.getModuleID();
		param2[3] = form.getSubModuleID();
		param2[4] = form.getFunctionID();
		param2[0] = form.getAttributeName();
		param2[1] = form.getAttributeValue();
		param2[6] = form.getRemarks();
		param2[5] = userId;
		insertflag = dao.addingData(param2,roleId,funId,userId);
		return insertflag;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ArrayList getDataById(String id) throws Exception {
		AttributeDAO dao = new AttributeDAO();
		ArrayList tlist = null;
		tlist = new ArrayList();
		tlist = dao.getDataById(id);
//		logger.debug("list of attribute data" + tlist);
		return tlist;
	}

	/**
	 * 
	 * @param form
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(AdminAttrForm form, String roleId, String funId, String userId) throws Exception {
		AttributeDAO dao = new AttributeDAO();
		boolean uflag;
		/*
		UPDATE IGRS_CONFIG_PARAM_LIST SET ATTRIBUTE_NAME=?,"
			+ "ATTRIBUTE_VALUE=?, REMARKS=?, ATTRIBUTE_STATUS = ?" + " WHERE ATTRIBUTE_ID=?
		 */
		String p[] = new String[]{
				form.getAttributeName(),
				form.getAttributeValue(),
				form.getRemarks(),
				form.getStatusVal(),
				form.getAttributeId()
		};
		uflag = dao.updateData(p,roleId,funId,userId);
		logger.debug("flag of status=" + uflag);
		return uflag;
	}

	/**
	 * 
	 * @param id
	 * @param roleId
	 * @param funId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteDataById(String id, String roleId, String funId, String userId) throws Exception {
		AttributeDAO dao = new AttributeDAO();
		boolean uflag;
		String p[] = new String[1];
		p[0] = id;
		uflag = dao.deleteDataById(p,roleId,funId,userId);// getting challn gendata based on Txnid
		logger.debug("flag of status=" + uflag);
		return uflag;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<DropDownDTO> getModuleList() {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			AttributeDAO dao = new AttributeDAO();
			retVal  = dao.getModuleList();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * 
	 * @param moduleID
	 * @return
	 */
	public ArrayList<DropDownDTO> getFunctionList(String moduleID) {
		ArrayList<DropDownDTO> retVal = new ArrayList<DropDownDTO>();
		try {
			AttributeDAO dao = new AttributeDAO();
			retVal  = dao.getFunctionList(moduleID);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}

	/**
	 * 
	 * @param adminForm
	 * @return
	 */
	public boolean checkAttribute(AdminAttrForm adminForm) {
		boolean retVal = false;
		try {
			AttributeDAO dao = new AttributeDAO();
			retVal  = dao.checkAttributeUniqueName(adminForm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return retVal;
	}
}

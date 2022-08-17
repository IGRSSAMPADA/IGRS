package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.SubClauseMapBD;
import com.wipro.igrs.adminConfig.dto.SubClauseMapDTO;
import com.wipro.igrs.adminConfig.form.SubClauseMapForm;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * ===========================================================================
 * File : SubClauseMapAction .java Description : Represents the
 * SubClauseMapAction Class Author : vengamamba P Created Date : Apr 28, 2008
 * 
 * ===========================================================================
 */
public class SubClauseMapAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(SubClauseMapAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.debug("enter in to the SubClauseMapAction");
		String forwardName = "success";
		SubClauseMapForm scmform = (SubClauseMapForm) form;
		SubClauseMapBD scmBD = new SubClauseMapBD();

		ArrayList districtList = scmBD.getDistrictList();// getting
																			// list
																			// of
																			// districtids

		ArrayList tehsilList = new ArrayList();
		ArrayList wardpatwariList = new ArrayList();
		ArrayList villageList = new ArrayList();
		ArrayList operatorList = new ArrayList();
		ArrayList propertyList = new ArrayList();
		ArrayList propertyL1List = new ArrayList();
		ArrayList propertyL2List = new ArrayList();
		ArrayList subclauseList;
		
		//HttpSession session = request.getSession();
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");


		String district = request.getParameter("district");
		if (district != null) {
			tehsilList = scmBD.getTehsilList(district);

		}
		String tehsil = request.getParameter("tehsil");
		if (tehsil != null) // if tehsil value not null
		{
			logger.debug("enter in to tehsil" + tehsil);
			wardpatwariList = scmBD.getWardPatwariList(tehsil);
		}
		String patwari = request.getParameter("patwari");
		if (patwari != null) // if tehsil value not null
		{
			logger.debug("enter in to tehsil" + tehsil);
			villageList = scmBD.getVillageIDList(patwari);
		}

		propertyList = scmBD.getPropertyIdList();
		String propertyid = request.getParameter("propid");
		if (propertyid != null)
			propertyL1List = scmBD.getPropertyL1IdList(propertyid);

		String propertyL1id = request.getParameter("propL1id");
		if (propertyL1id != null)
			propertyL2List = scmBD.getPropertyL2IdList(propertyL1id);

		operatorList = scmBD.getOperatorList();
		subclauseList = scmBD.getSubClauseList();
		SubClauseMapDTO dto = new SubClauseMapDTO();
		// setting list of values to districtids,tehsilids,patwariids
		// villageids,propertyid,propertyl1id,propertyl2id,subcluseid,
		// operatorid,functionoperatorid

		dto.setDistrictIDList(districtList);
		dto.setTehsilIDList(tehsilList);
		dto.setPatwariIDList(wardpatwariList);
		dto.setVillageIDList(villageList);
		dto.setPropertyIDList(propertyList);
		dto.setOperatorIDList(operatorList);
		dto.setPropertyL2List(propertyL2List);
		dto.setPropertyL1List(propertyL1List);
		dto.setSubClauseIDList(subclauseList);
		scmform.setClausedto(dto);
		logger.debug("dto--->" + scmform.getClausedto().getSubClauseIDList());
		// if operation is save
		if ("subClauseForm".equalsIgnoreCase(scmform.getFormName())) {
			String actionValue = scmform.getActionValue();
			boolean iflag = false;
			// action belong to save button
			if ("Save".equalsIgnoreCase(actionValue)) {
				if (scmform.getSubClauseID() != null
						&& !scmform.getSubClauseID().equalsIgnoreCase("")) {
					logger.debug("scmform.getSubClauseID()="
							+ scmform.getSubClauseID());
					String tempClause[] = scmform.getSubClauseID().split(",");
					logger.debug("arraydata=" + tempClause);
					logger.debug("arraydatalength=" + tempClause.length);
					for (int j = 0; j < tempClause.length; j++) {
						if (tempClause[j].equals("0"))
							tempClause[j] = null;
					}
					for (int j = 0; j < tempClause.length; j++) {
						logger.debug("arraydata=" + tempClause[j] + "j=" + j);
						String cid = tempClause[j];
						j = j + 1;
						logger.debug("arraydata=" + tempClause[j]);
						String cval = tempClause[j];
						j = j + 1;
						logger.debug("arraydata=" + tempClause[j]);
						String oid = tempClause[j];
						j = j + 1;
						logger.debug("arraydata=" + tempClause[j]);
						String fid = tempClause[j];

						// j=j+3;
						iflag = scmBD.addingData(scmform, cid, cval, oid, fid,roleId,funId,userId);
						if (!iflag)
							break;
					}
				} else {
					iflag = scmBD.addingData(scmform, null, null, null, null,roleId,funId,userId);
				}
				if (iflag) {
					scmBD.commitingData();
					forwardName = "Save";
				} else {
					scmBD.cancellingData();
					forwardName = "Unsave";
				}

			}// end of save
			if ("Cancel".equalsIgnoreCase(actionValue)) {
				forwardName = "home";
			}
			scmform.setActionValue(null);
		}// end of form
		 session = request.getSession(true);
		session.setAttribute("subClauseMapForm", scmform);
		return mapping.findForward(forwardName);

	}
}

package com.wipro.igrs.treasuryMaster.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.exception.IllegalTreasuryException;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;

public class TreasuryAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(TreasuryAction.class);
	private TreasureBD treasureBD;
	private TreasureDTO dto;
	private TreasuryForm treasuryForm;
	ArrayList treasuryList = null;

	public TreasuryAction() {

	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception

	{
		treasuryForm = (TreasuryForm) form;
		logger.info("we are in TreasuryAction");
		String target = "success";
		ActionErrors errors;
		dto = new TreasureDTO();
		dto.setTreasuryName(treasuryForm.getTreasuryName());
		dto.setTreasuryAddress(treasuryForm.getTreasuryAddress());
		dto.setTreasuryPhone(treasuryForm.getTreasuryPhone());
		String user = (String) session.getAttribute("UserId");

		// user = "Fabrigaz";

		treasureBD = new TreasureBD();

		if (treasuryForm.getPageName() != null) {
			if (treasuryForm.getPageName().equalsIgnoreCase("treasurecreate")) {
				if (isCancelled(request)) {
					setList(request);
					treasuryForm.reset(mapping, request);
					return mapping.findForward("viewTreasuryMaster");
				}
				/* Add Treasury master */
				try {
					dto.setCreatedBy(user);
					dto.setUpdatedBy(user);
					treasureBD.addTreasuryMaster(dto);
				} catch (IllegalTreasuryException e) {
					errors = new ActionErrors();
					errors.add("treasury", new ActionError(
							"errors.treasury.usernameexists"));
					saveErrors(request, errors);
					return mapping.findForward("createtreasurymaster");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		setList(request);
		return mapping.findForward(target);
	}

	private void setList(HttpServletRequest request) {
		try {
			treasuryList = treasureBD.getTreasuryList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("treasuryList", treasuryList);
	}

}
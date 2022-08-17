package com.wipro.igrs.treasuryMaster.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.treasuryMaster.bd.TreasureBD;
import com.wipro.igrs.treasuryMaster.dto.TreasureDTO;
import com.wipro.igrs.treasuryMaster.form.TreasuryForm;

public class preEditTreasuryAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(preEditTreasuryAction.class);
	private TreasureBD treasureBD;
	private TreasureDTO dto;
	private TreasuryForm treasuryForm;

	public preEditTreasuryAction() {

	}

	// public ActionForward execute(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) throws
	// Exception {
	// // TODO: Write method body
	// throw new UnsupportedOperationException("Method is not implemented");
	// }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception

	{
		String target = "success";
		treasureBD = new TreasureBD();
		dto = new TreasureDTO();
		treasuryForm = (TreasuryForm) form;
		List st = new ArrayList();
		st.add("removed");
		st.add("Activate");
		st.add("Deactivate");
		String id = request.getParameter("treasuryId");
		dto.setTreasuryId(id);
		dto = treasureBD.getTreasuryId(dto);
		treasuryForm.setTreasuryName(dto.getTreasuryName());
		treasuryForm.setTreasuryPhone(dto.getTreasuryPhone());
		treasuryForm.setTreasuryAddress(dto.getTreasuryAddress());
		treasuryForm.setStatus(dto.getStatus());
		treasuryForm.setStatusList(st);
		return mapping.findForward(target);
	}

}
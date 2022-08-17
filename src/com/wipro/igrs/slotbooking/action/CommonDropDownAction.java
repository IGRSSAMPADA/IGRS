package com.wipro.igrs.slotbooking.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.slotbooking.bd.SlotBookBD;
import com.wipro.igrs.slotbooking.dto.CommonDropDownDTO;
import com.wipro.igrs.slotbooking.form.CommonDropDownForm;

/**
 * @author venshis
 *
 */
public class CommonDropDownAction extends BaseAction {
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(CommonDropDownAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {

		

		CommonDropDownForm dropform = (CommonDropDownForm) form;

	
		try {
			
			SlotBookBD bd = new SlotBookBD();
			CommonDropDownDTO dto = new CommonDropDownDTO();
			ArrayList stlist = new ArrayList();
			ArrayList distlist = new ArrayList();
			ArrayList tallist = new ArrayList();
			if ((dropform.getDropdto().getCountryId()) == null
					|| dropform.getDropdto().getCountryId() == "") {
				ArrayList countlist = bd.getCountry();
				dropform.getDropdto().setCountryList(countlist);
			}
			if ((dropform.getDropdto().getCountryId()) != null
					|| dropform.getDropdto().getCountryId() != "") {
				stlist = bd.getState(dropform.getDropdto().getCountryId());

			}

			else {
				dto.setStateId("");
				dto.setStateName("");
				stlist.add(dto);
			}

			
			dropform.getDropdto().setStateList(stlist);
			if ((dropform.getDropdto().getStateId()) != null
					|| dropform.getDropdto().getStateId() != "") {

				distlist = bd.getDistrict(dropform.getDropdto().getStateId());

			} else {
				dto.setDistId("");
				dto.setDistName("");
				distlist.add(dto);

			}
			dropform.getDropdto().setDistList(distlist);
		
			dropform.getDropdto().setMohallaList(tallist);
			request.setAttribute("dropform", dropform);

		} catch (Exception x) {
			logger.error(x);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");

	}
}

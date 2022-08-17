package com.wipro.igrs.pot.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.form.PotFORM;


public class PotView extends BaseAction
{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws Exception 
     */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception
	{
		ArrayList list = new ArrayList();
		
		PotFORM data1 = (PotFORM) form;
		// START | POT Tool Changes | santosh
		data1.setImpound("");
		// END | POT Tool Changes | santosh
		String language=(String)session.getAttribute("languageLocale");
		String userId = (String) session.getAttribute("UserId");
		data1.setLanguage(language);
		//data1.setDurationFrom("");
        //data1.setDurationTo("");
		String officeId = (String)session.getAttribute("loggedToOffice");
		
		String potId = request.getParameter("actionID");
		String fnView = request.getParameter("fnName");	
		data1.setCreatedBy(userId);
		PotBD bd = null;
		String pageName = data1.getPageName();
		
		//START | Changes to fetch data on POT view and update page in search grid | santosh
		if(data1.getTransactionId().equalsIgnoreCase(null) || data1.getTransactionId().equalsIgnoreCase("")){
			if(!(data1.getDurationFrom().equalsIgnoreCase(null)) && !(data1.getDurationFrom().equalsIgnoreCase(""))){
				session.setAttribute("durationFrm", data1.getDurationFrom());
				session.setAttribute("durationTo", data1.getDurationTo());
			}
			if(data1.getDurationFrom().equalsIgnoreCase(null) || data1.getDurationFrom().equalsIgnoreCase("")){
				String durationFrm = (String) session.getAttribute("durationFrm");
				data1.setDurationFrom(durationFrm);
				String durationTo = (String) session.getAttribute("durationTo");
				data1.setDurationTo(durationTo);
			}
		}
		//END | Changes to fetch data on POT view and update page in search grid | santosh
		
		if(fnView==null)		
		{
		try
		{
			bd = new PotBD();
			String dis = bd.getDistrict(userId);
			String distId = bd.getDistrict1(officeId);
			if(pageName!=null && pageName.equalsIgnoreCase("potUpdate"))
			{
				list = bd.getPotList1(data1,language,distId);
			}
			else
			{
				list = bd.getPotList(data1,language,dis);
			}
			//data1.setDurationFrom("");
			// data1.setDurationTo("");
	       // data1.setTransactionId("");
			data1.setDurationFrom("");
			data1.getPotDTO().setActionName(null);
	        data1.setDurationTo("");
	        data1.setTransactionId("");
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("error.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
					
		}
		//HttpSession session = request.getSession();
		session.setAttribute("potList",list);
		return mapping.findForward("success");
		
	}
	
	else
	{
		data1.setDurationFrom("");
	        data1.setDurationTo("");
	        data1.setTransactionId("");
		return mapping.findForward("first");
	}
	}
}

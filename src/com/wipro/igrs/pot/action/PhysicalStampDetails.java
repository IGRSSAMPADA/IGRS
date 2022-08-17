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

public class PhysicalStampDetails extends BaseAction
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
		
		PotFORM formData = (PotFORM) form;
		String txnId = formData.getTransactionId();
		ArrayList plist = new ArrayList();
		PotBD bd = null;
		try
		{
			bd = new PotBD();
			plist = bd.getPotPhysicalList(txnId);
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
		System.out.println("i am in end Action classes");
	//	HttpSession session = request.getSession();
		session.setAttribute("pList", plist);
		//request.setAttribute("potList",list);
		return mapping.findForward("success");
	}
	

}

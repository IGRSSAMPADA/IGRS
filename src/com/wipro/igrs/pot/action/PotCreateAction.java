package com.wipro.igrs.pot.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.form.PotFORM;

public class PotCreateAction extends BaseAction
{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws Exception 
     */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		PotFORM formData = (PotFORM) form;
		boolean flag = false;
		PotBD bd = null;
      //  System.out.println(".....in pot creation action..........");
		try
		{
			bd = new PotBD();
			flag = bd.potCreate(formData,formData.getPhysicalStamp());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(flag)
		{
			//formData.setTransactionId(bd.getRID());
			request.setAttribute("txnId", bd.getRID());
			return mapping.findForward("success");
		}
		else
		{
			return mapping.findForward("failed");
		}
		

	}
	

}

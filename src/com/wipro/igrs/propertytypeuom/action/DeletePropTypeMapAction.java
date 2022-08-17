package com.wipro.igrs.propertytypeuom.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertytypeuom.bd.PropTypeMapBD;
import com.wipro.igrs.propertytypeuom.form.PropTypeMapForm;

public class DeletePropTypeMapAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		PropTypeMapForm propForm=(PropTypeMapForm)form;
		PropTypeMapBD propBD=new PropTypeMapBD();
		
		/* knowing the number of selected mapping will be deleted*/
		if(propForm.getSelected()!=null || propForm.getSelected().length!=0)
		{
			for(int i=0;i<propForm.getSelected().length;i++)
			{
				/* invoking Business Delegate Method that delete one record of data*/
				propBD.deleteMapping(propForm.getSelected()[i]);
			}
	     }
		return mapping.findForward("viewAllPropertyType");
	}
	
	
}

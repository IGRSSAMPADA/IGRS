package com.wipro.igrs.functiontorolemapping.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.functiontorolemapping.bd.FunToRoleMappingBD;
import com.wipro.igrs.functiontorolemapping.dto.FunToRoleMappingDto;
import com.wipro.igrs.functiontorolemapping.form.FunToRoleMappingForm;

public class DeleteFunToRoleMap extends BaseAction {
    
		
private Logger logger = (Logger) Logger.getLogger(DeleteFunToRoleMap.class);
	
	private FunToRoleMappingBD funToRoleMappingBD; 
	private FunToRoleMappingForm funToRoleMappingForm;
	private ArrayList funToRoleMappingList=null;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception 
			
	{
		funToRoleMappingForm = (FunToRoleMappingForm)form;
		String target = "viewFunToRoleMapping";
		ActionErrors errors;
		
		funToRoleMappingBD = new FunToRoleMappingBD();
		String [] deleted = funToRoleMappingForm.getSelected();
		for (int i = 0; i < deleted.length; i++) {
			funToRoleMappingBD.deleteFunToRoleMapping(deleted[i]);
		}
		setList(request);
		
		return mapping.findForward(target);
	}
		
	private void setList(HttpServletRequest request)
	{
		try {
			funToRoleMappingList = funToRoleMappingBD.getFunToRoleMappingDetailed();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("funToRoleMappingList", funToRoleMappingList);
	}
}
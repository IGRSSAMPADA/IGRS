package com.wipro.igrs.categorymaster.action;

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
import com.wipro.igrs.categorymaster.bd.CategoryBD;
import com.wipro.igrs.categorymaster.dto.CategoryDTO;
import com.wipro.igrs.categorymaster.exception.UserNameAlreadyExistException;
import com.wipro.igrs.categorymaster.formbean.CategoryForm;

public class AddCategoryAction extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(AddCategoryAction.class);
    ActionErrors error=new ActionErrors();
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
		
	CategoryForm catForm = (CategoryForm)form;
	// session = request.getSession();
    String roleId = (String)session.getAttribute("role");
	  String funId = (String)session.getAttribute("functionId");
	  String userId = (String)session.getAttribute("UserId");

	CategoryBD catbd = new CategoryBD();
	/* add Category master */
     
	CategoryDTO dto=new CategoryDTO();	
	String userid =(String)request.getSession().getAttribute("UserID");
	userid="1";
	dto.setCategoryId(catForm.getCategoryId());
	dto.setCategoryName(catForm.getCategoryName());
	dto.setCategoryStatus(catForm.getCategoryStatus());
	
	dto.setCreatedBy(userid);
	dto.setUpdatedBy(userid);
	
	try{
	catbd.addCategoryMaster(dto,roleId,funId,userId);
	}catch(UserNameAlreadyExistException e){
		error.add("nameerror",new ActionError("errors.categoryerror"));
		saveErrors(request, error);
		return mapping.findForward("createcategorymaster");
	}
	System.out.println(catForm.getCategoryName());
	

		return mapping.findForward("add");
	}

}

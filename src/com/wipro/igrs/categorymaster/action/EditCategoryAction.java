package com.wipro.igrs.categorymaster.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.categorymaster.bd.CategoryBD;
import com.wipro.igrs.categorymaster.dto.CategoryDTO;
import com.wipro.igrs.categorymaster.formbean.CategoryForm;

public class EditCategoryAction extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(EditCategoryAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
		
	System.out.println("i am in edit action");
	CategoryForm catForm = (CategoryForm)form;
	String userid =(String)request.getSession().getAttribute("UserID");
	userid="1";
	CategoryBD catbd = new CategoryBD();
	/* edit Category master */
	String id=request.getParameter("catId");
	System.out.println("i am in edit>>>>>>>>> "+id);
	CategoryDTO dto=catbd.getCategoryById(id);	
	
	catForm.setCategoryId(dto.getCategoryId());
	catForm.setCategoryName(dto.getCategoryName());
	catForm.setCategoryStatus(dto.getCategoryStatus());
	
	catForm.setUpdatedBy(userid);

	
	
	System.out.println("dfdfd"+catForm.getCategoryName());
	

		return mapping.findForward("edit");
	}
	

}


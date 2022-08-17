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

public class ViewallCategoryAction extends BaseAction{
	
	private Logger logger = (Logger) Logger.getLogger(ViewallCategoryAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
	CategoryForm catForm = (CategoryForm)form;

	CategoryBD catbd = new CategoryBD();
	/* viewALL Category master */
	catForm.setCategoryList(catbd.getList());	
	CategoryDTO d=(CategoryDTO)catForm.getCategoryList().get(0);
	System.out.println(d.getCategoryStatus());
	catForm.setSelectedCat(null);
	request.setAttribute("List", catForm.getCategoryList());
		return mapping.findForward("viewall");
	}
	

}

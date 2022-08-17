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

public class DeleteCategoryAction extends BaseAction {
	
	private Logger logger = (Logger) Logger.getLogger(EditCategoryAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception 
	{
	
	CategoryForm catForm = (CategoryForm)form;
	//session = request.getSession();
    String roleId = (String)session.getAttribute("role");
	  String funId = (String)session.getAttribute("functionId");
	  String userId = (String)session.getAttribute("UserId");

	CategoryBD catbd = new CategoryBD();
	/* update Category master */
	String [] str=catForm.getSelectedCat();
	CategoryDTO dto=new CategoryDTO();	
	for (int i=0;i<catForm.getSelectedCat().length;i++)
	{
//		System.out.println(">>>>>>>>"+str[i]);
		dto.setCategoryId(str[i]);
		dto.setCategoryStatus("R");
		catbd.deleteCategoryMaster(dto,roleId,funId,userId);
	}

	
	

	
	//System.out.println(catForm.getCategoryName());
	

		return mapping.findForward("viewallcategory");
	}
	


}

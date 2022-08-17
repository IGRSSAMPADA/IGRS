package com.wipro.igrs.serviceProviderTop.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.serviceProviderTop.bd.ServProBD;
import com.wipro.igrs.serviceProviderTop.dto.DTO;
import com.wipro.igrs.serviceProviderTop.form.Form;


public class ServiceProviderAction extends Action{
	String forward = new String("success");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(true);
		//added by shruti
    	session.setAttribute("langModule", "serviceProvide");
		 String language=(String)session.getAttribute("languageLocale");
		 
		 //end
		// TODO Auto-generated method stub
		System.out.println("anuj");
		Form eForm = (Form) form;
		DTO dto= eForm.getDto();
		ServProBD bd = new ServProBD();
		ArrayList alist = bd.getDistrict(language);
		eForm.setDistrictList(alist);
		String page = (String)request.getParameter("page");
		String formName = (String)request.getParameter("formName");
		
		if("serviceProvide".equalsIgnoreCase(formName)){
			page="";
			ArrayList list=bd.getData(dto);
			request.setAttribute("alist", list);
			forward="display";
		}
		//added by shruti
		String nextAction=(String)request.getParameter("next");
		if(request.getParameter("next")!=null)
		{
			if("next".equalsIgnoreCase(nextAction))
			{
				page="";
				ArrayList list=bd.getData(dto);
				request.setAttribute("alist", list);
				forward="display";
			}
		}
			//end
		if("first".equals(page)){
			dto.setDistrict("");
			eForm.setDto(dto);
			forward="district";
		}
		//added by shruti
		else if("display".equalsIgnoreCase(page))
		{
			ArrayList list=bd.getData(dto);
			request.setAttribute("alist", list);
			forward="display";
		}
		String actionName=(String)request.getParameter("actionName");
		if("Reset".equalsIgnoreCase(actionName))
		{
			dto.setDistrict("");
			eForm.setDto(dto);
			forward="district";
		}
		
		return mapping.findForward(forward);
	}

}

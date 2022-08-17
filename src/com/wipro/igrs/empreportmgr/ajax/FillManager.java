package com.wipro.igrs.empreportmgr.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.log4j.Logger;

import com.wipro.igrs.empreportmgr.action.PreLoadEmpReportMgrAction;
import com.wipro.igrs.empreportmgr.bd.EmpReportMgrBD;
import com.wipro.igrs.empreportmgr.dto.empReportMgrDto;
import com.wipro.igrs.empreportmgr.form.EmpReportMgrForm;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

/**
 * Servlet implementation class for Servlet: FillManager
 *
 */

		
 public class FillManager extends BaseAjaxServlet {

	 private Logger logger = (Logger) Logger.getLogger(PreLoadEmpReportMgrAction.class);
	private EmpReportMgrBD empReportMgrBD;
	private empReportMgrDto dto; 
	private EmpReportMgrForm reportMgrForm;
	private ArrayList EmpReportMgrList=null;
	 
	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		
		AjaxXmlBuilder xmlBuilder=null;
		try {
			String roleId = (String) request.getParameter("role");

			empReportMgrBD = new EmpReportMgrBD();
			ArrayList managers = empReportMgrBD.getUsersForThisRole(roleId);
			xmlBuilder = new AjaxXmlBuilder();
			
			for (int i = 0; i < managers.size(); i++) {
				UserProfileDTO userProfileDTO = ((UserProfileDTO)managers.get(i));
				xmlBuilder.addItem(userProfileDTO.getFirstName(), userProfileDTO.getUserId());
			}
			System.out.println("roles loaded from servlet");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xmlBuilder.toString();  
		
		
		//setList(request);
		
	}
  
}
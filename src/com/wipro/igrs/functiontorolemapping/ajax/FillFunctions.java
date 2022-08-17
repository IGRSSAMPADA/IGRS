package com.wipro.igrs.functiontorolemapping.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
import com.wipro.igrs.functiontorolemapping.bd.FunToRoleMappingBD;

/**
 * Servlet implementation class for Servlet: FillFunctions
 *
 */
 public class FillFunctions extends BaseAjaxServlet  {
   
	 private FunToRoleMappingBD funToRoleMappingBD;
		public String getXmlContent(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			AjaxXmlBuilder xmlBuilder=null;
			try {
				String subModule = (String) request.getParameter("subModule");

				funToRoleMappingBD = new FunToRoleMappingBD();
				ArrayList functions = funToRoleMappingBD.getFunctionsForThisSubmodule(subModule);
				xmlBuilder = new AjaxXmlBuilder();
				
				for (int i = 0; i < functions.size(); i++) {
					FnActivityDTO fnActivityDTO = ((FnActivityDTO)functions.get(i));
					xmlBuilder.addItem(fnActivityDTO.getName(), fnActivityDTO.getValue());
				}
				System.out.println("roles loaded from servlet");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xmlBuilder.toString();  
		}
}
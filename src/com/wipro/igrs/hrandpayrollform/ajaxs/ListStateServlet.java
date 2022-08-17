package com.wipro.igrs.hrandpayrollform.ajaxs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.hrandpayrollform.bd.HrAndPayrollBD;
import com.wipro.igrs.hrandpayrollform.dto.StateMasterDTO;

/**
 * Servlet implementation class for Servlet: ListStateServlet
 *
 */
 public class ListStateServlet extends org.ajaxtags.servlets.BaseAjaxServlet implements javax.servlet.Servlet {
   
	 HrAndPayrollBD hrAndPayrollBD = new HrAndPayrollBD();
		
		

		public String getXmlContent(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			String countryId = request.getParameter("countryId");
			AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
			
			List allStates = hrAndPayrollBD.getStatesByCountryId(countryId);
			
			xmlBuilder.addItem("-- Select one --", "");
			
			StateMasterDTO stateMasterDTO;
			for (int i = 0; i < allStates.size(); i++) {
				stateMasterDTO = (StateMasterDTO)allStates.get(i);
				xmlBuilder.addItem(stateMasterDTO.getName(), stateMasterDTO.getId());
			}

			return xmlBuilder.toString();
		}
    	  	    
}
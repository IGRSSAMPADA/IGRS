package com.wipro.igrs.hrandpayrollform.ajaxs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.hrandpayrollform.bd.HrAndPayrollBD;
import com.wipro.igrs.hrandpayrollform.dto.DistrictMasterDTO;

/**
 * Servlet implementation class for Servlet: ListDistrictServlet
 *
 */
 public class ListDistrictServlet extends org.ajaxtags.servlets.BaseAjaxServlet implements javax.servlet.Servlet {
     	    
	 HrAndPayrollBD hrAndPayrollBD = new HrAndPayrollBD();
		
	
		public String getXmlContent(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			String stateId = request.getParameter("stateId");
			AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
			
			List allDistrict = hrAndPayrollBD.getDistrictsByStateId(stateId);
			
			xmlBuilder.addItem("-- Select one --", "");
			
			DistrictMasterDTO districtMasterDTO;
			for (int i = 0; i < allDistrict.size(); i++) {
				districtMasterDTO = (DistrictMasterDTO)allDistrict.get(i);
				xmlBuilder.addItem(districtMasterDTO.getName(), districtMasterDTO.getId());
			}
			
			return xmlBuilder.toString();
		}
}
package com.wipro.igrs.officearea.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD;
import com.wipro.igrs.officearea.bd.OfficeAreaMappingBD;
import com.wipro.igrs.officearea.dto.TehsilDTO;

/**
 * Servlet implementation class for Servlet: ListTehsilsServlet
 *
 */
 public class ListTehsilsServlet extends org.ajaxtags.servlets.BaseAjaxServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
   
    /**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ListTehsilsServlet() {
	}

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String districtId = request.getParameter("districtId");
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		List tehsils = areaMappingBD.getTehsils(districtId);
		
		xmlBuilder.addItem("-- Select a Tehsil --", "");
		
		for (int i = 0; i < tehsils.size(); i++) {
			TehsilDTO tehsilDTO = (TehsilDTO)tehsils.get(i);
			xmlBuilder.addItem(tehsilDTO.getName(), tehsilDTO.getId());
		}
		
		return xmlBuilder.toString();
	}   	 	  	  	    
}
package com.wipro.igrs.officearea.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD;
import com.wipro.igrs.officearea.bd.OfficeAreaMappingBD;
import com.wipro.igrs.officearea.dto.MohallaVillageDTO;

/**
 * Servlet implementation class for Servlet: ListMohallaVillageServlet
 *
 */
 public class ListMohallaVillageServlet extends org.ajaxtags.servlets.BaseAjaxServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
   
    /**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ListMohallaVillageServlet() {
	}

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String wardPatwariId = request.getParameter("wardPatwariId");
		
		List mohalaVilliges = areaMappingBD.getMohalaVilliges(wardPatwariId);
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		xmlBuilder.addItem("-- Select a Mohalla / Village --", "");
		
		for (int i = 0; i < mohalaVilliges.size(); i++) {
			MohallaVillageDTO mohallaVillageDTO = (MohallaVillageDTO)mohalaVilliges.get(i);
			xmlBuilder.addItem(mohallaVillageDTO.getName(), mohallaVillageDTO.getId());
		}
		
		return xmlBuilder.toString();
		
	}   	 	  	  	    
	
	
}
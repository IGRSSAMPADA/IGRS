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
import com.wipro.igrs.officearea.dto.WardPatwariDTO;

/**
 * Servlet implementation class for Servlet: ListWardPatwariServlet
 *
 */
 public class ListWardPatwariServlet extends org.ajaxtags.servlets.BaseAjaxServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   private IOfficeAreaMappingBD areaMappingBD = new OfficeAreaMappingBD();
   
    /**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ListWardPatwariServlet() {
	}

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String tehsilId = request.getParameter("tehsilId");
		
		List wardPatwariList = areaMappingBD.getWardPatwaris(tehsilId);
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		xmlBuilder.addItem("-- Select a Ward Patwari --", "");
		
		for (int i = 0; i < wardPatwariList.size(); i++) {
			WardPatwariDTO wardPatwariDTO = (WardPatwariDTO)wardPatwariList.get(i);
			
			xmlBuilder.addItem(wardPatwariDTO.getName(), wardPatwariDTO.getId());
		}
		
		return xmlBuilder.toString();
	}   	 	  	  	    
}
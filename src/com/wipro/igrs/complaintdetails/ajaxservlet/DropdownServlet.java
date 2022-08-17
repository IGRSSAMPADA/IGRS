package com.wipro.igrs.complaintdetails.ajaxservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.complaintdetails.dao.ComplaintDetailsDAO;
import com.wipro.igrs.officemaster.bd.OfficeBD;
import com.wipro.igrs.officemaster.dto.OfficeDTO;


/**
 * Servlet implementation class for Servlet: ListOfficeByType
 *
 */
 public class DropdownServlet extends BaseAjaxServlet {
   static final long serialVersionUID = 1L;
   
   private ComplaintDetailsDAO complaintDetailsDAO = new ComplaintDetailsDAO();
   
	public DropdownServlet() {
	}

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String officeTypeId = request.getParameter("locationId");
		List officeList = complaintDetailsDAO.getOfficesByType(officeTypeId);
		AjaxXmlBuilder xml = new AjaxXmlBuilder();
		
		xml.addItem("ALL", "");
		
		for(int i=0; i<officeList.size(); i++) {
			OfficeDTO officeDTO = (OfficeDTO)officeList.get(i);
			xml.addItem(officeDTO.getOfficeName(), officeDTO.getOfficeId());
		//xml.addItem("heba", "1");
		}
		
		return xml.toString();
	}   	 	  	  	    
}
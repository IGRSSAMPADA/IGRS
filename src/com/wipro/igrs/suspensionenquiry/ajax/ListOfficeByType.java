package com.wipro.igrs.suspensionenquiry.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.officemaster.bd.OfficeBD;
import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.suspensionenquiry.dao.ISuspensionEnquiryDAO;
import com.wipro.igrs.suspensionenquiry.dao.SuspensionEnquiryDAO;
import com.wipro.igrs.suspensionenquiry.dto.OfficesDTO;

/**
 * Servlet implementation class for Servlet: ListOfficeByType
 *
 */
 public class ListOfficeByType extends BaseAjaxServlet {
   static final long serialVersionUID = 1L;
   
   private ISuspensionEnquiryDAO suspensionEnquiryDAO = new SuspensionEnquiryDAO();
   
	public ListOfficeByType() {
	}

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String officeTypeId = request.getParameter("officeTypeId");
		
		
		List officeList = suspensionEnquiryDAO.getOfficesByType(officeTypeId);
		
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		if(!officeList.isEmpty())
			xmlBuilder.addItem("-- ALL --", "");
		
		for(int i=0; i<officeList.size(); i++) {
			OfficesDTO officeDTO = (OfficesDTO)officeList.get(i);
			xmlBuilder.addItem(officeDTO.getOfficeName(), officeDTO.getOfficeId());
		}
		
		return xmlBuilder.toString();
	}   	 	  	  	    
}
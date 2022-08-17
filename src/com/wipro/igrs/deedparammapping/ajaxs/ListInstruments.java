package com.wipro.igrs.deedparammapping.ajaxs;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.deedparammapping.bd.DeedParamBD;
import com.wipro.igrs.deedparammapping.dto.DeedInstrumentMasterDTO;

/**
 * Servlet implementation class for Servlet: ListInstruments
 *
 */
 public class ListInstruments extends BaseAjaxServlet implements javax.servlet.Servlet {

	 
	 DeedParamBD deedParamBD = new DeedParamBD();
	 
	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		String id = request.getParameter("deedTypeId");
		
		List deedInstrument = deedParamBD.getDeedInstrumentByDeedTypeId(id);
		
		xmlBuilder.addItem("--Select one--", "");
		
		DeedInstrumentMasterDTO deedInstrumentMasterDTO;
		for (int i = 0; i < deedInstrument.size(); i++) {
			deedInstrumentMasterDTO = (DeedInstrumentMasterDTO)deedInstrument.get(i);
			xmlBuilder.addItem(deedInstrumentMasterDTO.getName(), deedInstrumentMasterDTO.getId());			
		}
		
		return xmlBuilder.toString();
	}
	 
	 
}
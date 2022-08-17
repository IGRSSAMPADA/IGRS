package com.wipro.igrs.deedparammapping.ajaxs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.deedparammapping.bd.DeedParamBD;
import com.wipro.igrs.deedparammapping.dto.DeedExemptionMasterDTO;

/**
 * Servlet implementation class for Servlet: ListExemptions
 * 
 */
public class ListExemptions extends BaseAjaxServlet implements
		javax.servlet.Servlet {

	DeedParamBD deedParamBD = new DeedParamBD();

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		String id = request.getParameter("deedTypeId");
		// System.out.println("hiiiiiiiiiiiiiiiiii servlet" + id);
		List deedExemption = deedParamBD.getDeedExemptionByDeedTypeId(id);

		xmlBuilder.addItem("--Select one--", "");
		DeedExemptionMasterDTO deedExemptionMasterDTO;

		for (int i = 0; i < deedExemption.size(); i++) {
			deedExemptionMasterDTO = (DeedExemptionMasterDTO) deedExemption
					.get(i);
			xmlBuilder.addItem(deedExemptionMasterDTO.getName(),
					deedExemptionMasterDTO.getId());
		}

		return xmlBuilder.toString();
	}

}
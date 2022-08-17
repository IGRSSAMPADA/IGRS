package com.wipro.igrs.expform76b.ajax;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.expform76b.bd.ExpForm76BBD;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;

/**
 * Servlet implementation class for Servlet: MinorServlet
 * 
 */
public class MinorServlet extends BaseAjaxServlet {

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String subMajorID = request.getParameter("subMajorID"); // to get the
																// submajorid
																// from the
																// request;

		ExpForm76BBD expBD = new ExpForm76BBD();
		ExpForm76BSelectDTO expDTO = null;

		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		ArrayList minor = expBD.getAllMinorHeadRelatedToSubMajor(subMajorID);

		for (int i = 0; i < minor.size(); i++) {
			expDTO = new ExpForm76BSelectDTO();
			expDTO = (ExpForm76BSelectDTO) minor.get(i);

			xmlBuilder.addItem(expDTO.getMinorHeadName(), expDTO
					.getMinorHeadID());
		}

		return xmlBuilder.toString();
	}

}
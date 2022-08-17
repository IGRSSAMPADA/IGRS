package com.wipro.igrs.propertytypeuom.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.propertytypeuom.bd.PropTypeMapBD;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;

/**
 * Servlet implementation class for Servlet: L1AjaxServlet
 *
 */
 public class L1AjaxServlet extends BaseAjaxServlet {

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		PropTypeMapBD propBD=new PropTypeMapBD();
		String prop_id = request.getParameter("prop_id");
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		ArrayList l1_data=propBD.getRelatedL1Data(prop_id);
		
		if(l1_data==null)
		{
			xmlBuilder.addItem("NO DATA FOUND","NO DATA FOUND");
		}
		else
		{
			for(int i=0;i<l1_data.size();i++)
			{
				MappingDataDTO dataDto=(MappingDataDTO)l1_data.get(i);
				xmlBuilder.addItem(dataDto.getPropertyTypeL2Name(), dataDto.getPropertyTypeL2ID());
			}
		}
		return xmlBuilder.toString();
	}

   
	    
}
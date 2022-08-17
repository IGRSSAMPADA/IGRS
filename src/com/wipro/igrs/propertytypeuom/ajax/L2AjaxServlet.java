package com.wipro.igrs.propertytypeuom.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.servlets.BaseAjaxServlet;
import org.ajaxtags.xml.AjaxXmlBuilder;

import com.wipro.igrs.propertytypeuom.bd.PropTypeMapBD;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;


 public class L2AjaxServlet extends BaseAjaxServlet {

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		PropTypeMapBD propBD=new PropTypeMapBD();
		
		String l1_id = request.getParameter("l1_id");
		
		AjaxXmlBuilder xmlBuilder = new AjaxXmlBuilder();
		
		ArrayList l2_data=propBD.getRelatedL2Data(l1_id);
		
		if(l2_data==null)
		{
			xmlBuilder.addItem("NO DATA FOUND","NO DATA FOUND");
		}
		else
		{
			for(int i=0;i<l2_data.size();i++)
			{
				MappingDataDTO dataDto=(MappingDataDTO)l2_data.get(i);
				xmlBuilder.addItem(dataDto.getPropertyTypeL2Name(), dataDto.getPropertyTypeL2ID());
			}
		}
		
		return xmlBuilder.toString();
	}

 
}
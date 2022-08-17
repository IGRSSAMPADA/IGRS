package com.wipro.igrs.templateDictionary.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;



import com.wipro.igrs.regTime.dao.RegTimeingsDao;
import com.wipro.igrs.templateDictionary.bd.TemplateBd;
import com.wipro.igrs.templateDictionary.dao.TemplateDao;

public class TemplateBo
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TemplateBo.class);
	public ArrayList getMoudleName() throws Exception
	{
		TemplateDao dao = new TemplateDao();
		return dao.getMoudleName();
	}
	public ArrayList getSubMoudle() throws Exception
	{
		TemplateDao dao = new TemplateDao();
		return dao.getSubMoudle();
	}
	public ArrayList getFunction() throws Exception
	{
		TemplateDao dao = new TemplateDao();
		return dao.getFunction();
	}
	
	public boolean tempCreate(String param1[]) throws Exception
	 {
		TemplateDao dao = new TemplateDao();
		 	return dao.tempCreate(param1);
	 }
	
	public ArrayList getModules() throws Exception
	 {
		TemplateDao dao = new TemplateDao();
		 	return dao.getModules();
	 }
	 public ArrayList getTemplateContent(String tempId) throws Exception
	 {
		TemplateDao dao = new TemplateDao();
		 	return dao.getTemplateContent(tempId);
	 }
	 
	 public boolean templateUpdate(String param1[]) throws Exception
		{
		 TemplateDao dao = new TemplateDao();
			return dao.templateUpdate(param1);
		}

}

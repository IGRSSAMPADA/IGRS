package com.wipro.igrs.templateDictionary.bd;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.apache.log4j.Logger;




import com.wipro.igrs.regTime.bo.RegTimeingsBo;
import com.wipro.igrs.regTime.form.RegTimeForm;
import com.wipro.igrs.regcompletion.dao.RegCompMailDAO;
import com.wipro.igrs.templateDictionary.action.TemplateCreateAction;
import com.wipro.igrs.templateDictionary.bo.TemplateBo;
import com.wipro.igrs.templateDictionary.dao.TemplateDao;
import com.wipro.igrs.templateDictionary.dto.TemplateDto;
import com.wipro.igrs.templateDictionary.form.TemplateForm;



public class TemplateBd
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TemplateBd.class);

	public static String RID = null;
	
	public  void getMoudleName(HttpServletRequest request) throws IOException
	{
		ArrayList moudleName = new ArrayList();
		HttpSession session = null;
		TemplateBo bo = null; 
		try
		{
			session = request.getSession();
		bo = new TemplateBo();
			ArrayList list1 = bo.getMoudleName();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						TemplateDto reg = new TemplateDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setModuleId((String)list.get(0));
						reg.setModuleName((String)list.get(1));
						moudleName.add(reg);
					}
		    }
			TemplateDto dto= new TemplateDto();
			dto.setList1(moudleName);
			session.setAttribute("modulesKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in  in BD" + e);
	    }
	}
	
	
	public  void getSubMoudle(HttpServletRequest request) throws IOException
	{
		ArrayList subModule = new ArrayList();
		HttpSession session = null;
		TemplateBo bo = null; 
		try
		{
			session = request.getSession();
		
			bo = new TemplateBo();
			ArrayList list1 = bo.getSubMoudle();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						TemplateDto reg = new TemplateDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setSubModuleId((String)list.get(0));
						reg.setSubModuleName((String)list.get(1));
						subModule.add(reg);
					}
		    }
			TemplateDto dto= new TemplateDto();
			dto.setList2(subModule);
			session.setAttribute("subModulesKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in  in BD" + e);
	    }
	}
	
	public  void getFunction(HttpServletRequest request) throws IOException
	{
		ArrayList functionName = new ArrayList();
		HttpSession session = null;
		TemplateBo bo = null; 
		try
		{
			session = request.getSession();
			//BudgetDAO dao = new BudgetDAO();
			bo = new TemplateBo();
			ArrayList list1 = bo.getFunction();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						TemplateDto reg = new TemplateDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setFunctionId((String)list.get(0));
						reg.setFunctionName((String)list.get(1));
						functionName.add(reg);
					}
		    }
			TemplateDto dto= new TemplateDto();
			dto.setList3(functionName);
			session.setAttribute("functionKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in  in BD" + e);
	    }
	}
	
	
	
	 public String getRID()
     {
         return RID;
     }
     
     private String templateId() 
	 {
         String id = "TE" + new Date().getTime();
         return id;
     }   
	
	public boolean tempCreate(TemplateForm data) throws SQLException,Exception
	{
		boolean flag = false;
		TemplateBo bo = null;
	

		try
		{
			
			RID = templateId();
			ArrayList setList = new ArrayList();
			
				bo = new TemplateBo();
				String[] param1 = new String[9];
				String module = data.getModule();
				String subModule = data.getSubModule();
				String function = data.getFunction();
				String status = data.getStatus();
				String tempName = data.getTemplateName();
				String tempCont = data.getTempContent();
				String tempHeader = data.getTempHeader();
				String tempFooter = data.getTempFooter();
				
				String tempId = RID;
				
				param1[0] = module;
				param1[1] = subModule;
				param1[2] = function;
				param1[3] = status;
				param1[4] = tempName;
				param1[5] = tempCont;
				param1[6] = tempHeader;
				param1[7] = tempFooter;
				param1[8] = tempId;
				
					flag = bo.tempCreate(param1); 
					
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
	}
	
	
	 public  ArrayList getModules() throws SQLException
		{
			ArrayList mainList = new ArrayList();
			TemplateBo bo = null;
			try
			{
				bo = new TemplateBo();
				ArrayList list1 = bo.getModules();
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					TemplateForm temp = new TemplateForm();
					ArrayList list =(ArrayList)list1.get(i);
					temp.setTemplateId((String)list.get(0));
					temp.setTemplateName((String)list.get(1));
					temp.setModule((String)list.get(2));
					temp.setSubModule((String)list.get(3));
					temp.setFunction((String)list.get(4));
					temp.setStatus((String)list.get(5));
							
					mainList.add(temp);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in  in BD" + e);
		    }
			return mainList;
		}
	 
	 
	 public  TemplateForm getTemplateContent(String tempId) throws SQLException
		{
		//	ArrayList mainList = new ArrayList();
			TemplateBo bo = null;
			TemplateForm temp = null;
			try
			{
				bo = new TemplateBo();
				ArrayList list1 = bo.getTemplateContent(tempId);
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					temp = new TemplateForm();
					ArrayList list =(ArrayList)list1.get(i);
					temp.setTemplateName((String)list.get(0));
					temp.setTempContent((String)list.get(1));
					temp.setTempHeader((String)list.get(2));
					temp.setTempFooter((String)list.get(3));
							
				//	mainList.add(temp);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in  in BD" + e);
		    }
			return temp;
		}
	
	 
	 
	 public boolean templateUpdate(String tempId,TemplateForm data) throws SQLException
		{
			boolean flag = false;
			String functId = null;
			TemplateBo bo = null;
			try
			{
				bo = new TemplateBo();
				
				String[] param1 = new String[4];
				
				String tempCont = data.getTempContent();
				String tempHead = data.getTempHeader();
				String tempFoot = data.getTempFooter();

				param1[0] = tempCont;
				param1[1] = tempHead;
				param1[2] = tempFoot;
				param1[3] = tempId;
				
					flag = bo.templateUpdate(param1);  
		
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
		
			}
			return flag;
			
		}
	
	

	
	
	
}




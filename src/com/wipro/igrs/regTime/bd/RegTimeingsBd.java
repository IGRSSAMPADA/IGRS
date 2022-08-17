package com.wipro.igrs.regTime.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;





import com.wipro.igrs.regTime.action.RegTimeingsAction;
import com.wipro.igrs.regTime.bo.RegTimeingsBo;
import com.wipro.igrs.regTime.dto.RegTimeingsDto;
import com.wipro.igrs.regTime.form.RegTimeForm;

public class RegTimeingsBd
{
	private  Logger logger = 
		(Logger) Logger.getLogger(RegTimeingsBd.class);
	public  void getMoudleName(HttpServletRequest request) throws IOException
	{
		ArrayList moudleName = new ArrayList();
		HttpSession session = null;
		RegTimeingsBo bo = null; 
		try
		{
			session = request.getSession();
			bo = new RegTimeingsBo();
			ArrayList list1 = bo.getMoudleName();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						RegTimeingsDto reg = new RegTimeingsDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setModuleId((String)list.get(0));
						reg.setModuleName((String)list.get(1));
						moudleName.add(reg);
					}
		    }
			RegTimeingsDto dto= new RegTimeingsDto();
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
		RegTimeingsBo bo = null; 
		try
		{
			session = request.getSession();
			bo = new RegTimeingsBo();
			ArrayList list1 = bo.getSubMoudle();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						RegTimeingsDto reg = new RegTimeingsDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setSubModuleId((String)list.get(0));
						reg.setSubModuleName((String)list.get(1));
						subModule.add(reg);
					}
		    }
			RegTimeingsDto dto= new RegTimeingsDto();
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
		RegTimeingsBo bo = null; 
		try
		{
			session = request.getSession();
			//BudgetDAO dao = new BudgetDAO();
			bo = new RegTimeingsBo();
			ArrayList list1 = bo.getFunction();
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						RegTimeingsDto reg = new RegTimeingsDto();
						ArrayList list =(ArrayList)list1.get(i);
						reg.setFunctionID((String)list.get(0));
						reg.setFunctionName((String)list.get(1));
						functionName.add(reg);
					}
		    }
			RegTimeingsDto dto= new RegTimeingsDto();
			dto.setList3(functionName);
			session.setAttribute("functionKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in  in BD" + e);
	    }
	}
	
	
	 public boolean regCreate(RegTimeForm data) throws SQLException,Exception
		{
			boolean flag = false;
			RegTimeingsBo bo = null;
			
			try
			{
				
				ArrayList setList = new ArrayList();
				
					bo = new RegTimeingsBo();
					String[] param1 = new String[10];
					String module = data.getModule();
					String subModule = data.getSubModule();
					String function = data.getFunction();
					String startHor = data.getSrtHor();
					String startMin = data.getSrtMin();
					String startAmOrPm = data.getSrtAmPm();
					String endHor = data.getEndHor();
					String endMin = data.getEndMin();
					String endAmOrPm = data.getEndAmPm();
					String status = data.getStatus();
					
					param1[0] = module;
					param1[1] = subModule;
					param1[2] = function;
					param1[3] = startHor;
					param1[4] = startMin;
					param1[5] = startAmOrPm;
					param1[6] = endHor;
					param1[7] = endMin;
					param1[8] = endAmOrPm;
					param1[9] = status;
					
					
					
						flag = bo.regCreate(param1); 
					
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
			RegTimeingsBo bo = null;
			try
			{
				bo = new RegTimeingsBo();
				ArrayList list1 = bo.getModules();
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					RegTimeForm reg = new RegTimeForm();
					ArrayList list =(ArrayList)list1.get(i);
							reg.setModule((String)list.get(0));
							reg.setSubModule((String)list.get(1));
							reg.setFunction((String)list.get(2));
							reg.setStartTime((String)list.get(3) + " : " +(String)list.get(4) + " "+(String)list.get(5));
							//reg.setSrtMin((String)list.get(4));
						//	reg.setSrtAmPm((String)list.get(5));
							reg.setEndTime((String)list.get(6) + " : "+(String)list.get(7) + " "+(String)list.get(8));
						//	reg.setEndMin((String)list.get(7));
						//	reg.setEndAmPm((String)list.get(8));
						reg.setStatus((String)list.get(9));
						//reg.setStatus(getTime(list));
							
							
					mainList.add(reg);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in  in BD" + e);
		    }
			return mainList;
		}
	 

	 
	 public boolean moudleUpdate(String function,RegTimeForm data) throws SQLException
		{
			boolean flag = false;
			String functId = null;
			RegTimeingsBo bo = null;
			try
			{
				bo = new RegTimeingsBo();
				ArrayList fucId = bo.getFuctionId(function);
				
				if(fucId!=null)
				{
					for(int i=0;i<fucId.size();i++)
					{
						ArrayList list = (ArrayList)fucId.get(0);
						functId = (String) list.get(0);
					}
				}
				
				String[] param1 = new String[8];
				
				String sthr = data.getSrtHor();
				String stmin = data.getSrtMin();
				String stam = data.getSrtAmPm();
				String endHr = data.getEndHor();
				String endMin = data.getEndMin();
				String endAm = data.getEndAmPm();
				String status = data.getStatus();
				
				param1[0] = sthr;
				param1[1] = stmin;
				param1[2] = stam;
				param1[3] = endHr;
				param1[4] = endMin;
				param1[5] = endAm;
				param1[6] = status;
				param1[7] = functId;
			
					flag = bo.moudleUpdate(param1);  
		
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
		
			}
			return flag;
			
		}
	

}

package com.wipro.igrs.reconciliation.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import com.wipro.igrs.reconciliation.action.DetailedHeadDetails;
import com.wipro.igrs.reconciliation.bo.ReconciliationBo;
import com.wipro.igrs.reconciliation.dao.ReconciliationDao;
import com.wipro.igrs.reconciliation.dto.ReconciliationDto;
import com.wipro.igrs.reconciliation.form.ReconciliationForm;


public class ReconciliationBd
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReconciliationBd.class);
	
	public void getDistrict(HttpServletRequest request) throws IOException
	{
		ReconciliationBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			bo = new ReconciliationBo();
			session = request.getSession();
			ArrayList fullList = bo.getDistrict(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					ReconciliationDto vo = new ReconciliationDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					vo.setDistrictId((String)subList.get(0));
					vo.setDistrictName((String)subList.get(1));
					setList.add(vo);
										
				}
			}
			ReconciliationDto dto = new ReconciliationDto();
				dto.setList1(setList);
				session.setAttribute("districts",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDistrict in BD" + e);
	    }
				
	}
	
	public void getDro(HttpServletRequest request) throws IOException
	{
		ReconciliationBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			bo = new ReconciliationBo();
			session = request.getSession();
			ArrayList fullList = bo.getDro(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					ReconciliationDto setValue = new ReconciliationDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setDroId((String)subList.get(0));
					setValue.setDroName((String)subList.get(1));
					setList.add(setValue);
				}
			}
			ReconciliationDto dto = new ReconciliationDto();
				dto.setList7(setList);
				session.setAttribute("dros",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getDro in BD" + e);
	    }
				
	}
	
	public void getFinYear(HttpServletRequest request) throws IOException
	{
		ReconciliationBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			bo = new ReconciliationBo();
			session = request.getSession();
			ArrayList fullList = bo.getFinYear(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					ReconciliationDto setValue = new ReconciliationDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setFinancialYearId((String)subList.get(0));
					setValue.setFinancialYearName((String)subList.get(1));
					setList.add(setValue);
				}
			}
			ReconciliationDto dto = new ReconciliationDto();
				dto.setList2(setList);
				session.setAttribute("years",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getFinYear in BD" + e);
	    }
	}
	
		
	public void getDetailedTo(HttpServletRequest request) throws IOException
	{
		ReconciliationBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			bo = new ReconciliationBo();
			session = request.getSession();
			ArrayList fullList = bo.getDetailedTo(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					ReconciliationDto setValue = new ReconciliationDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setDetailedToId((String)subList.get(0));
					setValue.setDetailedToName((String)subList.get(1));
					setList.add(setValue);
				}
			}
			ReconciliationDto dto = new ReconciliationDto();
				dto.setList6(setList);
				session.setAttribute("detaildKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getDetailedTo in BD" + e);
	    }
	}
			
	 public boolean reconCreate(HttpServletRequest request,ReconciliationForm data) throws SQLException,Exception
		{
			boolean flag = false;
			ReconciliationBo bo = null;
			
			try
			{
				//ReconciliationBo bo = null;
				ArrayList setList = new ArrayList();
				HttpSession session = null;
				
					 
					bo = new ReconciliationBo();
					String[] param1 = new String[3];
					String officeId = data.getDro();
					String finYear = data.getFinancialYear();
					String detailed = data.getDetailedHead();
					
					param1[0] = officeId;
					param1[1] = finYear;
					param1[2] = detailed;
					
					flag = bo.isPrimaryKeyExists(param1);
					
					if(flag)
					{
						//System.out.println("I am in BD Record Already exists");
						throw new Exception("Record Already exists (OfficeName,FinancialYear,DetailedHead)"); //Throwing an Exception
						
					}
					else{
						
						
						bo = new ReconciliationBo();
						String[] param2 = new String[7];
						String districtId = data.getDistrict();
						String officeId2 = data.getDro();
						String finYear2 = data.getFinancialYear();
						String detailed2= data.getDetailedHead();
						String allocated = data.getAllocated();
						String exhausted = data.getExhausted();
						String balance = data.getBalance();
					
						param2[0] = districtId;
						param2[1] = officeId2;
						param2[2] = finYear2;
						param2[3] = detailed2;
						param2[4] = allocated;
						param2[5] = exhausted;
						param2[6] = balance;  
					
						flag = bo.reconCreate(param2); 
						
					}
		
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
		
			}
			return flag;
		}
	 

	 
	 
	 
	 public  ArrayList getAllHeads(String dstrctNm, String financYr, String duratnFrm, String duratnTo) throws SQLException
		{
			ArrayList mainList = new ArrayList();
			
			try
			{
				ReconciliationBo bo = new ReconciliationBo();
				ArrayList list1 = bo.getAllHeads(dstrctNm,financYr,duratnFrm,duratnTo);
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				{
					ReconciliationForm fund1 = new ReconciliationForm();
					ArrayList list =(ArrayList)list1.get(i);
							fund1.setDetailedHead((String)list.get(0));
							fund1.setDro((String)list.get(1));
							fund1.setDistrict((String)list.get(2));
							fund1.setFinancialYear((String)list.get(3));
					mainList.add(fund1);
				}
			  }
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getAllHeads in BD" + e);
		    }
			return mainList;
		}
	 
	 
	 public ReconciliationForm headDetails(String detailedHeadId) throws Exception
		{
			ArrayList getFullList = new ArrayList();
			ReconciliationForm fund = null;
			try
			{
				
				ReconciliationBo bo = new ReconciliationBo();
				ArrayList list1 = bo.headDetails(detailedHeadId);
				if(list1!=null)
				{	
				for(int i=0;i<list1.size();i++)
				
							{
								fund = new ReconciliationForm();
								
								ArrayList list =(ArrayList)list1.get(i);
								fund.setDistrict((String)list.get(0));
								fund.setDro((String)list.get(1));
								fund.setFinancialYear((String)list.get(2));
								fund.setDetailedHead((String)list.get(3));
								fund.setAllocated((String)list.get(4));
								fund.setExhausted((String)list.get(5));
								fund.setBalance((String)list.get(6));
																
							}
					}
			}
			catch(Exception e)
			{
				logger.error("this is Exception in headDetails in BD" + e);
		    }
			
			return fund;
		}
	 
	 
	 public  void getMajorHeadId(HttpServletRequest request) throws IOException
		{
			
			ArrayList getMajorHeadId = new ArrayList();
			HttpSession session1 = null;
			HttpSession session = null;
			
			session = request.getSession();
			String userId = (String)session.getAttribute("UserId");
			if(userId==null)
			{
				userId="prakash";
			}
			try
			{
				session1 = request.getSession();
				ReconciliationDao dao = new ReconciliationDao();
				ArrayList list1 = dao.getMajorHeadId(userId);
				if(list1!=null)
				{	
						for(int i=0;i<list1.size();i++)
						{
							ReconciliationDto names = new ReconciliationDto();
							ArrayList list =(ArrayList)list1.get(i);
							names.setMajorHeadId((String)list.get(0));
							names.setMajorHeadName((String)list.get(1));
							getMajorHeadId.add(names);
						}
			    }
				ReconciliationDto dto= new ReconciliationDto();
				dto.setList5(getMajorHeadId);
				session1.setAttribute("majorHeadIds",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getMajorHeadId in BD" + e);
		    }
		}
	 
	 
	 public  void getSubMajorHeadId(HttpServletRequest request,String majorId) throws IOException
		{
			
			ArrayList getSubMajorHeadId = new ArrayList();
			HttpSession session1 = null;
			HttpSession session = null;
			session = request.getSession();
			String userId = (String)session.getAttribute("UserId");
			if(userId==null)
			{
				userId="prakash";
			}
		
			try
			{
				session1 = request.getSession();
				ReconciliationDao dao = new ReconciliationDao();
				ArrayList list1 = dao.getSubMajorHeadId(userId,majorId);
				if(list1!=null)
				{	
						for(int i=0;i<list1.size();i++)
						{
							ReconciliationDto names = new ReconciliationDto();
							ArrayList list =(ArrayList)list1.get(i);
							names.setSubMajorId((String)list.get(0));
							names.setSubMajorName((String)list.get(1));
							getSubMajorHeadId.add(names);
						}
			    }
				ReconciliationDto dto= new ReconciliationDto();
				dto.setList6(getSubMajorHeadId);
				session1.setAttribute("subMagerHeadIds",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getSubMajorHeadId in BD" + e);
		    }
		}
	 
	 public  void getMinorHeadId(HttpServletRequest request,String subMjorId,String majorId) throws IOException
		{
			
			ArrayList getMinorHeadId = new ArrayList();
			HttpSession session1 = null;
			HttpSession session = null;
			session = request.getSession();
			String userId = (String)session.getAttribute("UserId");
			if(userId==null)
			{
				userId="prakash";
			}
			try
			{
				session1 = request.getSession();
				ReconciliationDao dao = new ReconciliationDao();
				ArrayList list1 = dao.getMinorHeadId(userId,subMjorId,majorId);
				if(list1!=null)
				{	
						for(int i=0;i<list1.size();i++)
						{
							ReconciliationDto names = new ReconciliationDto();
							ArrayList list =(ArrayList)list1.get(i);
							names.setMinorHeadId((String)list.get(0));
							names.setMinorHeadName((String)list.get(1));
							getMinorHeadId.add(names);
						}
			    }
				ReconciliationDto dto= new ReconciliationDto();
				dto.setList7(getMinorHeadId);
				session1.setAttribute("minorHeadIds",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getMinorHeadId in BD" + e);
		    }
			
		}	
			
			public  void getScheme(HttpServletRequest request,String minorId,String subMjorId,String majorId) throws IOException
			{
				
				ArrayList schemeNames = new ArrayList();
				HttpSession session1 = null;
				HttpSession session = null;
				
				//String dro = session.getAttribute(")
				session = request.getSession();
				String userId = (String)session.getAttribute("UserId");
				if(userId==null)
				{
					userId="prakash";
				}
				try
				{
					session1 = request.getSession();
					ReconciliationDao dao = new ReconciliationDao();
					ArrayList list1 = dao.getScheme(userId,minorId,subMjorId,majorId);
					if(list1!=null)
					{	
							for(int i=0;i<list1.size();i++)
							{
								ReconciliationDto names = new ReconciliationDto();
								ArrayList list =(ArrayList)list1.get(i);
								names.setSchemeId((String)list.get(0));
								names.setSchemeName((String)list.get(1));
								schemeNames.add(names);
							}
				    }
					ReconciliationDto dto= new ReconciliationDto();
					dto.setList8(schemeNames);
					session1.setAttribute("schemeNames",dto);
				}
				catch(Exception e)
				{
					logger.error("this is Exception in getScheme in BD" + e);
			    }
			}
			
			
			public  void getSegmentNames(HttpServletRequest request,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
			{
				
				ArrayList getSegmentNames = new ArrayList();
				HttpSession session1 = null;
				HttpSession session = null;
				
				//String dro = session.getAttribute(")
				session = request.getSession();
				String userId = (String)session.getAttribute("UserId");
				if(userId==null)
				{
					userId="prakash";
				}
				try
				{
					session1 = request.getSession();
					ReconciliationDao dao = new ReconciliationDao();
					ArrayList list1 = dao.getSegmentNames(userId,schemeId,minorId,subMjorId,majorId);
					if(list1!=null)
					{	
							for(int i=0;i<list1.size();i++)
							{
								ReconciliationDto names = new ReconciliationDto();
								ArrayList list =(ArrayList)list1.get(i);
								names.setSegmentId((String)list.get(0));
								names.setSegmentName((String)list.get(1));
								getSegmentNames.add(names);
							}
				    }
					ReconciliationDto dto= new ReconciliationDto();
					dto.setList4(getSegmentNames);
					session1.setAttribute("segmentNames",dto);
				}
				catch(Exception e)
				{
					logger.error("this is Exception in getSegmentNames in BD" + e);
			    }
			}
			
			
			
			public  void getObjectNames(HttpServletRequest request,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
			{
				ArrayList getObjectNames = new ArrayList();
				HttpSession session1 = null;
				HttpSession session = null;
				session = request.getSession();
				String userId = (String)session.getAttribute("UserId");
				if(userId==null)
				{
					userId="prakash";
				}
				try
				{
					session1 = request.getSession();
					ReconciliationDao dao = new ReconciliationDao();
					ArrayList list1 = dao.getObjectNames(userId,segmentId,schemeId,minorId,subMjorId,majorId);
					if(list1!=null)
					{	
							for(int i=0;i<list1.size();i++)
							{
								ReconciliationDto names = new ReconciliationDto();
								ArrayList list =(ArrayList)list1.get(i);
								names.setObjectId((String)list.get(0));
								names.setObjectName((String)list.get(1));
								getObjectNames.add(names);
							}
				    }
					ReconciliationDto dto= new ReconciliationDto();
					dto.setList3(getObjectNames);
					session1.setAttribute("objectNames",dto);
				}
				catch(Exception e)
				{
					logger.error("this is Exception in getObjectNames in BD" + e);
			    }
			}
			
			
			
			public void getDHead(HttpServletRequest request,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
			{
				ReconciliationDao dao = null;
				ArrayList setList = new ArrayList();
				HttpSession session1 = null;
				HttpSession session = null;
				session = request.getSession();
				String userId = (String)session.getAttribute("UserId");
				if(userId==null)
				{
					userId="prakash";
				}
				try
				{
					dao = new ReconciliationDao();
					session1 = request.getSession();
					
					ArrayList fullList = dao.getDHead(userId,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
					if(fullList!=null)
					{
						for(int i=0;i<fullList.size();i++)
						{
							ReconciliationDto vo = new ReconciliationDto();
							//BudgetForm vao = new BudgetForm();
							ArrayList subList = (ArrayList)fullList.get(i);
							vo.setDetailedHeadId((String)subList.get(0));
							vo.setDetailedHeadName((String)subList.get(1));
							setList.add(vo);
												
						}
					}
					ReconciliationDto dto = new ReconciliationDto();
						dto.setList9(setList);
						session1.setAttribute("detailedHeads",dto);
				}
				catch(Exception e)
				{
					logger.error("this is Exception in getDHead in BD" + e);
			    }
				//return setList;
						
			}
			
		
	 
}

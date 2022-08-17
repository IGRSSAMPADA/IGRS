package com.wipro.igrs.fundTransfer.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.budgetRequest.dao.BudgetDAO;
import com.wipro.igrs.budgetRequest.form.BudgetForm;
import com.wipro.igrs.fundTransfer.action.Approved;
import com.wipro.igrs.fundTransfer.bo.FundTransferBo;
import com.wipro.igrs.fundTransfer.dao.FundTransferDAO;
import com.wipro.igrs.fundTransfer.dto.FundTransferDto;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;

public class FundTransferBD
{
	private  Logger logger = 
		(Logger) Logger.getLogger(FundTransferBD.class);
	public static String RID = null;
	
	public void getDistrict(HttpServletRequest request) throws IOException
	{
		//FundTransferDAO dao = null;
		FundTransferBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
			//dao = new FundTransferDAO();
			bo = new FundTransferBo();
			session = request.getSession();
			ArrayList fullList = bo.getDistrict(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					FundTransferDto setValue = new FundTransferDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setDistrictId((String)subList.get(0));
					setValue.setDistrictName((String)subList.get(1));
					setList.add(setValue);
										
				}
			}
				FundTransferDto dto = new FundTransferDto();
				dto.setList1(setList);
				session.setAttribute("districts",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getDistrict in BD" + e);
	    }
				
	}
	
	
	
	
	public void getFinYear(HttpServletRequest request) throws IOException
	{
		//FundTransferDAO dao = null;
		FundTransferBo bo = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		try
		{
		//	dao = new FundTransferDAO();
			bo = new FundTransferBo();
			session = request.getSession();
			ArrayList fullList = bo.getFinYear(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					FundTransferDto setValue = new FundTransferDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setFinancialYearId((String)subList.get(0));
					setValue.setFinancialYearName((String)subList.get(1));
					setList.add(setValue);
										
				}
			}
				FundTransferDto dto = new FundTransferDto();
				dto.setList2(setList);
				session.setAttribute("years",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getFinYear in BD" + e);
	    }
				
	}
	
	
    public boolean fundTransfer(FundTransferForm formData) throws IOException
    {
    	ArrayList FullList = new ArrayList();
		boolean flag = false;
		//FundTransferDAO dao = null;
		FundTransferBo bo = null;
		try
		{
			
			// dao = new FundTransferDAO();
			bo = new FundTransferBo();
			 
			 String param2[] = new String[7];
				
				
				String dro = formData.getDro();
				String district = formData.getDistrict();
				String Fyear = formData.getFinancialYear();
				String detailedFrom = formData.getDetaildHeadFrom();
				String detailedTo = formData.getDetaildHeadTo();
				String amount = formData.getAmountTransfered();
				
				param2[0] = dro;
				param2[1] = district;
				param2[2] = Fyear;
				param2[3] = detailedFrom;
				param2[4] = detailedTo;
				param2[5] = amount;
				param2[6] = "D";
				//param2[7] = "DH";
							
				flag = bo.fundTransfer2(param2);
			 
			 
			 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return flag;
	}
    
    
    public void getDro(HttpServletRequest request) throws IOException
	{
	//	FundTransferDAO dao = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		FundTransferBo bo = null;
		try
		{
			bo = new FundTransferBo();
			//dao = new FundTransferDAO();
			session = request.getSession();
			ArrayList fullList = bo.getDro(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					FundTransferDto setValue = new FundTransferDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setDroId((String)subList.get(0));
					setValue.setDroName((String)subList.get(1));
					setList.add(setValue);
										
				}
			}
				FundTransferDto dto = new FundTransferDto();
				dto.setList7(setList);
				session.setAttribute("dros",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getDro in BD" + e);
	    }
				
	}
    
    
    public  ArrayList getAllHeads() throws SQLException
	{
			ArrayList mainList = new ArrayList();
			FundTransferBo bo = null;
		try
		{
		//	FundTransferDAO dao = new FundTransferDAO();
			bo = new FundTransferBo();
			ArrayList list1 = bo.getAllHeads();
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			{
				FundTransferForm fund = new FundTransferForm();
				ArrayList list =(ArrayList)list1.get(i);
					
						fund.setDro((String)list.get(0));
						fund.setDistrict((String)list.get(1));
						fund.setFinancialYear((String)list.get(2));
					mainList.add(fund);
				}
			
		  }
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
	    }
		return mainList;
	}
    
    
    
    public FundTransferForm headDetails(HttpServletRequest request,String droId) throws Exception
	{
		ArrayList getFullList = new ArrayList();
		FundTransferForm fund = null;
		FundTransferBo bo = null;
		try
		{
			bo = new FundTransferBo();
			//FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.headDetails(droId);
				if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			
						{
							fund = new FundTransferForm();
							
							ArrayList list =(ArrayList)list1.get(i);
							fund.setDro((String)list.get(0));
							fund.setSysDate((String)list.get(1));
							fund.setDistrict((String)list.get(2));
							fund.setFinancialYear((String)list.get(3));
							
							fund.setObjectHeadFrom((String)list.get(4));
							fund.setObjectHeadTo((String)list.get(5));
							fund.setDetaildHeadFrom((String)list.get(6));
							fund.setDetaildHeadTo((String)list.get(7));
							fund.setAmountTransfered((String)list.get(8));
							fund.setTempValue((String)list.get(9));
							
							
							HttpSession session = request.getSession();
							session.setAttribute("key1", (String)list.get(9));
							session.getAttribute("key1");
							
							getFullList.add(fund);
							
							if(((String)list.get(4).toString()=="")&& (String)list.get(5).toString()==""){
								fund.setFundType("D");
							}
							if((String)list.get(6).toString()=="" && (String)list.get(7).toString()==""){
								fund.setFundType("O");
							}
						}
			
			}
		}
		catch(Exception e)
		{
			logger.error("this is Exception in headDetails in BD" + e);
	    }
		
		return fund;
		
	}
    
    
    
    public boolean fundApprove(String droKeyId,FundTransferForm data) throws SQLException
	{
		boolean flag = false;
		//FundTransferDAO dao = null;
		FundTransferBo bo = null;
		try
		{
			//dao = new FundTransferDAO();
			bo = new FundTransferBo();
			
			String[] param1 = new String[5];
			String[] param2 = new String[5];
			
			
			String district = data.getDistrict();
			String finYear = data.getFinancialYear();
			String fromObject = data.getObjectHeadFrom();
			String toObject = data.getObjectHeadTo();
			String fromDD = data.getDetaildHeadFrom();
			String toDD = data.getDetaildHeadTo();
			String amountTransfer = data.getAmountTransfered();
			
		if(fromDD == null && toDD==null)
		{
			param1[0] = fromObject;
			param1[1] = toObject;
			param1[2] = amountTransfer;
			param1[3] = "A";
			param1[4] = droKeyId;
			flag = bo.fundApprove2(param1); 
			
	    
	
		}
			
		if(fromObject == null && toObject==null)
		{
				param2[0] = fromDD;
				param2[1] = toDD;
				param2[2] = amountTransfer;
				param2[3] = "A";
				param2[4] = droKeyId;
				flag = bo.fundApprove1(param2);  
		
		}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
		
	}
    
    
    public  ArrayList fundType() throws SQLException
	{
		ArrayList mainList = new ArrayList();
		FundTransferBo bo = null;
		try
		{
		//	FundTransferDAO dao = new FundTransferDAO();
			bo = new FundTransferBo();
			ArrayList list1 = bo.fundType();
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			{
				FundTransferForm fund = new FundTransferForm();
				ArrayList list =(ArrayList)list1.get(i);
					
					fund.setDro((String)list.get(0));
				fund.setDistrict((String)list.get(1));
				fund.setFinancialYear((String)list.get(2));
						mainList.add(fund);
				}
			
		  }
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
	    }
		return mainList;
	}
    
    
    
    
    public  ArrayList viewDroDH() throws SQLException
	{
		ArrayList mainList = new ArrayList();
		FundTransferBo bo = null;
		try
		{
			bo = new FundTransferBo();
			//FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.viewDroDH();
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			{
				FundTransferForm fund = new FundTransferForm();
				ArrayList list =(ArrayList)list1.get(i);
				fund.setDro((String)list.get(0));
				fund.setDistrict((String)list.get(1));
				fund.setFinancialYear((String)list.get(2));
					mainList.add(fund);
			}
			
		  }
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
	    }
		return mainList;
	}
    
    
    
    
    public  ArrayList getHeadList(String droId) throws SQLException
	{
		ArrayList mainList = new ArrayList();
		FundTransferBo bo = null;
		try
		{
			bo = new FundTransferBo();
		//	FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.getHeadList(droId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			{
				FundTransferForm fund = new FundTransferForm();
				ArrayList list =(ArrayList)list1.get(i);
				fund.setDro((String)list.get(0));
				fund.setDistrict((String)list.get(1));
				fund.setFinancialYear((String)list.get(2));
					mainList.add(fund);
			}
			
		  }
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
	    }
		return mainList;
	}
    
    
    
    public FundTransferForm droStatusDetails(HttpServletRequest request,String droId) throws Exception
	{
		ArrayList getFullList = new ArrayList();
		FundTransferForm fund = null;
		FundTransferBo bo = null;
		try
		{
			bo = new FundTransferBo();
		//	FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.droStatusDetails(droId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
			
						{
							fund = new FundTransferForm();
							
							ArrayList list =(ArrayList)list1.get(i);
							fund.setDro((String)list.get(0));
							fund.setSysDate((String)list.get(1));
							fund.setDistrict((String)list.get(2));
							fund.setFinancialYear((String)list.get(3));
							fund.setObjectHeadFrom((String)list.get(4));
							//System.out.println("first nameisssssssssssssssssssssss"+(String)list.get(4));
							fund.setObjectHeadTo((String)list.get(5));
							fund.setDetaildHeadFrom((String)list.get(6));
							fund.setDetaildHeadTo((String)list.get(7));
							fund.setAmountTransfered((String)list.get(8));
							fund.setTransferStatus((String)list.get(9));
							fund.setTempValue((String)list.get(10));
							
							HttpSession session = request.getSession();
							session.setAttribute("key1", (String)list.get(10));
							session.getAttribute("key1");
							
							getFullList.add(fund);
							
							if(((String)list.get(4).toString()=="")&& (String)list.get(5).toString()==""){
								fund.setFundType("D");
								//System.out.println("Test1     ssssssssssssss"+fund.getFundType());
							}
							if((String)list.get(6).toString()=="" && (String)list.get(7).toString()==""){
								fund.setFundType("O");
								//System.out.println("Test2     ssssssssssssss"+fund.getFundType());
							}
						}
				}
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
	    }
		
		return fund;
		
	}
       
    public boolean fundReqReject(String droKeyId,FundTransferForm data) throws SQLException
	{
		boolean flag = false;
		//FundTransferDAO dao = null;
		FundTransferBo bo = null;
		try
		{
		//	dao = new FundTransferDAO();
			bo = new FundTransferBo();
			String[] param1 = new String[5];
			String[] param2 = new String[5];
			
			
			String district = data.getDistrict();
			String finYear = data.getFinancialYear();
			String fromObject = data.getObjectHeadFrom();
			String toObject = data.getObjectHeadTo();
			String fromDD = data.getDetaildHeadFrom();
			String toDD = data.getDetaildHeadTo();
			String amountTransfer = data.getAmountTransfered();
			
	
			
		if(fromDD == null && toDD==null)
		{
			param1[0] = fromObject;
			param1[1] = toObject;
			param1[2] = amountTransfer;
			param1[3] = "R";
			param1[4] = droKeyId;
			flag = bo.fundReqReject2(param1); 
		}
			
		if(fromObject == null && toObject == null)
		{
				param2[0] = fromDD;
				param2[1] = toDD;
				param2[2] = amountTransfer;
				param2[3] = "R";
				param2[4] = droKeyId;
				flag = bo.fundReqReject1(param2);  
		
		}
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
	
		}
		return flag;
		
	}
	
    
    
    public void listOfDros(HttpServletRequest request) throws IOException
	{
	//	FundTransferDAO dao = null;
		ArrayList setList = new ArrayList();
		HttpSession session = null;
		FundTransferBo bo = null;
		try
		{
			
			//dao = new FundTransferDAO();
			bo = new FundTransferBo();
			session = request.getSession();
			ArrayList fullList = bo.getDroIds(request);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					FundTransferDto setValue = new FundTransferDto();
					ArrayList subList = (ArrayList)fullList.get(i);
					setValue.setDroId2((String)subList.get(0));
					setValue.setDroName2((String)subList.get(1));
					setList.add(setValue);
										
				}
			}
				FundTransferDto dto = new FundTransferDto();
				dto.setList8(setList);
				session.setAttribute("drosList2",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getOfficeNames in BD" + e);
	    }
				
	}
	
			
    public  void getMajorHeadId(HttpServletRequest request) throws IOException
	{
		
		ArrayList getMajorHeadId = new ArrayList();
		HttpSession session1 = null;
		HttpSession session = null;
		FundTransferBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			bo = new FundTransferBo();
			session1 = request.getSession();
		//	FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.getMajorHeadId(userId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setMajorHeadId((String)list.get(0));
						names.setMajorHeadName((String)list.get(1));
						getMajorHeadId.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
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
		FundTransferBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
	
		try
		{
			session1 = request.getSession();
			//FundTransferDAO dao = new FundTransferDAO();
			bo = new FundTransferBo();
			ArrayList list1 = bo.getSubMajorHeadId(userId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setSubMajorId((String)list.get(0));
						names.setSubMajorName((String)list.get(1));
						getSubMajorHeadId.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
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
		FundTransferBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			session1 = request.getSession();
		//	FundTransferDAO dao = new FundTransferDAO();
			bo = new FundTransferBo();
			
			ArrayList list1 = bo.getMinorHeadId(userId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setMinorHeadId((String)list.get(0));
						names.setMinorHeadName((String)list.get(1));
						getMinorHeadId.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
			dto.setList10(getMinorHeadId);
			session1.setAttribute("minorHeadIds",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getMinorHeadId in BD" + e);
	    }
	}
    
    
    public  void getSchemeNames(HttpServletRequest request,String minorId,String subMjorId,String majorId) throws IOException
	{
		
		ArrayList getSchemeNames = new ArrayList();
		HttpSession session1 = null;
		HttpSession session = null;
		FundTransferBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			session1 = request.getSession();
		//	FundTransferDAO dao = new FundTransferDAO();
			bo = new FundTransferBo();
			ArrayList list1 = bo.getSchemeNames(userId,minorId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setSchemeId((String)list.get(0));
						names.setSchemeName((String)list.get(1));
						getSchemeNames.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
			dto.setList11(getSchemeNames);
			session1.setAttribute("schemeNames",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getSchemeNames in BD" + e);
	    }
	}
    
    
    	
    public  void getSegmentNames(HttpServletRequest request,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
	{
		
		ArrayList getSegmentNames = new ArrayList();
		HttpSession session1 = null;
		HttpSession session = null;
		FundTransferBo bo = null;
		//String dro = session.getAttribute(")
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			bo = new FundTransferBo();
			session1 = request.getSession();
			//FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.getSegmentNames(userId,schemeId,minorId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setSegmentId((String)list.get(0));
						names.setSegmentName((String)list.get(1));
						getSegmentNames.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
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
		FundTransferBo bo = null;
		//String dro = session.getAttribute(")
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			bo = new FundTransferBo();
			session1 = request.getSession();
		//	FundTransferDAO dao = new FundTransferDAO();
			ArrayList list1 = bo.getObjectNames(userId,segmentId,schemeId,minorId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						FundTransferDto names = new FundTransferDto();
						ArrayList list =(ArrayList)list1.get(i);
						names.setObjectId((String)list.get(0));
						names.setObjectName((String)list.get(1));
						getObjectNames.add(names);
					}
		    }
			FundTransferDto dto= new FundTransferDto();
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
    //	FundTransferDAO dao = null;
		ArrayList setList = new ArrayList();
		HttpSession session1 = null;
		HttpSession session = null;
		FundTransferBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			bo = new FundTransferBo();
			session1 = request.getSession();
			
			ArrayList fullList = bo.getDHead(userId,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
			if(fullList!=null)
			{
			   	for(int i=0;i<fullList.size();i++)
				{
				 	FundTransferDto vo = new FundTransferDto();
					
					ArrayList subList = (ArrayList)fullList.get(i);
					vo.setDhId(subList.get(0).toString());
					//System.out.println("subList.get(0).toString()--"+subList.get(0).toString());
					vo.setDhName(subList.get(1).toString());
					//System.out.println("subList.get(1).toString()--"+subList.get(1).toString());
					setList.add(vo);
										
				}
			}
			FundTransferDto dto = new FundTransferDto();
				dto.setList13(setList);
				session1.setAttribute("detailedKey",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getDHead in BD" + e);
			e.printStackTrace();
			
	    }
		//return setList;
				
	}
    
    
    public void getDHeadTo(HttpServletRequest request,String dhId) throws IOException
	{
    	FundTransferForm data = new FundTransferForm();
    	//FundTransferDAO dao = null;
    	FundTransferBo bo = null;
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
			//dao = new FundTransferDAO();
			bo = new FundTransferBo();
			session1 = request.getSession();
			
			ArrayList fullList = bo.getDHead2(userId,dhId);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					FundTransferDto vo = new FundTransferDto();
					
					ArrayList subList = (ArrayList)fullList.get(i);
					vo.setDhId2(subList.get(0).toString());
					vo.setDhName2(subList.get(1).toString());
					setList.add(vo);
										
				}
			}
				FundTransferDto dto = new FundTransferDto();
				dto.setList14(setList);
				session1.setAttribute("detailedKey2",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getOfficeNames in BD" + e);
	    }
		//return setList;
				
	}
    
    
    
    

}

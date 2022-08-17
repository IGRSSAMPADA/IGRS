package com.wipro.igrs.receiptBudget.bd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;





import com.wipro.igrs.receiptBudget.action.ReceiptBudgetView;
import com.wipro.igrs.receiptBudget.bo.receiptBudgetBo;
import com.wipro.igrs.receiptBudget.dao.BudgetDAO;
import com.wipro.igrs.receiptBudget.dto.OfficeMasterDTO;

import com.wipro.igrs.receiptBudget.form.ReceiptBudgetForm;

public class BudgetBD
{
	private  Logger logger = 
		(Logger) Logger.getLogger(BudgetBD.class);
	public static String RID = null;
	
	
	
//	 public String getRID()
//     {
//         return RID;
//     }
     
     private String potDgenerator() 
	 {
         String id = "BD" + new Date().getTime();
        // System.out.println("this is potDgenerator () & value of ID " + id);
         return id;
     }   
	
	
	public  void getOfficeNames(HttpServletRequest request) throws IOException
	{
		ArrayList getOfficeNames = new ArrayList();
		HttpSession session = null;
		receiptBudgetBo bo = null;
		try
		{
			session = request.getSession();
			//BudgetDAO dao = new BudgetDAO();
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getOfficeNames(request);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setOfficeId((String)list.get(0));
						names.setOfficeName((String)list.get(1));
						getOfficeNames.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList(getOfficeNames);
			session.setAttribute("officeNames",dto);
		}
		catch(Exception e)
		{
	        logger.error("this is Exception in getOfficeNames in BD" + e);
	        e.printStackTrace();
	    }
	}
	
	
	public  void getSchemeNames(HttpServletRequest request,String minorId,String subMjorId,String majorId) throws IOException
	{
		
		ArrayList getSchemeNames = new ArrayList();
		
		HttpSession session = null;
		receiptBudgetBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getSchemeNames(userId,minorId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setSchemeId((String)list.get(0));
						names.setSchemeName((String)list.get(1));
						getSchemeNames.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList2(getSchemeNames);
			session.setAttribute("schemeNames",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getSchemeNames in BD" + e);
			e.printStackTrace();
	    }
	}
	
	
	public  void getObjectNames(HttpServletRequest request,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
	{
		ArrayList getObjectNames = new ArrayList();
		
		HttpSession session = null;
		receiptBudgetBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getObjectNames(userId,segmentId,schemeId,minorId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setObjectId((String)list.get(0));
						names.setObjectName((String)list.get(1));
						getObjectNames.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList3(getObjectNames);
			session.setAttribute("objectNames",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getObjectNames in BD" + e);
			e.printStackTrace();
	    }
	}
	
	
	
		public  void getSegmentNames(HttpServletRequest request,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
		{
			
			ArrayList getSegmentNames = new ArrayList();
			
			HttpSession session = null;
			receiptBudgetBo bo = null;
			
			session = request.getSession();
			String userId = (String)session.getAttribute("UserId");
			if(userId==null)
			{
				userId="prakash";
			}
			try
			{
				
				bo = new receiptBudgetBo();
				ArrayList list1 = bo.getSegmentNames(userId,schemeId,minorId,subMjorId,majorId);
				if(list1!=null)
				{	
						for(int i=0;i<list1.size();i++)
						{
							OfficeMasterDTO names = new OfficeMasterDTO();
							ArrayList list =(ArrayList)list1.get(i);
							names.setSegmentId((String)list.get(0));
							names.setSegmentName((String)list.get(1));
							getSegmentNames.add(names);
						}
			    }
				OfficeMasterDTO dto= new OfficeMasterDTO();
				dto.setList4(getSegmentNames);
				session.setAttribute("segmentNames",dto);
			}
			catch(Exception e)
			{
				logger.error("this is Exception in getSegmentNames in BD" + e);
				e.printStackTrace();
		    }
	}
	
	
	public  void getMajorHeadId(HttpServletRequest request) throws IOException
	{
		ArrayList getMajorHeadId = new ArrayList();
		
		HttpSession session = null;
		receiptBudgetBo bo = null;

		//String dro = session.getAttribute(")
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getMajorHeadId(userId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setMajorHeadId((String)list.get(0));
						names.setMajorHeadName((String)list.get(1));
						getMajorHeadId.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList5(getMajorHeadId);
			session.setAttribute("majorHeadIds",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getMajorHeadId in BD" + e);
			e.printStackTrace();
	    }
	}
	
	
	public  void getSubMajorHeadId(HttpServletRequest request,String majorId) throws IOException
	{
		ArrayList getSubMajorHeadId = new ArrayList();
		
		HttpSession session = null;
		receiptBudgetBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
	
		try
		{
			
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getSubMajorHeadId(userId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setSubMajorId((String)list.get(0));
						names.setSubMajorName((String)list.get(1));
						getSubMajorHeadId.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList6(getSubMajorHeadId);
			session.setAttribute("subMagerHeadIds",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getSubMajorHeadId in BD" + e);
			e.printStackTrace();
	    }
	}
	
	
	public  void getMinorHeadId(HttpServletRequest request,String subMjorId,String majorId) throws IOException
	{
		
		ArrayList getMinorHeadId = new ArrayList();
		
		HttpSession session = null;
		receiptBudgetBo bo = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash";
		}
		try
		{
			
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getMinorHeadId(userId,subMjorId,majorId);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setMinorHeadId((String)list.get(0));
						names.setMinorHeadName((String)list.get(1));
						getMinorHeadId.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList7(getMinorHeadId);
			session.setAttribute("minorHeadIds",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getMinorHeadId in BD" + e);
			e.printStackTrace();
	    }
	}
	
	
	
	     public String getDHeadName(String str)
	     {
		 String DHead = null;
		 DHead = str;
		String DHeadId = null;
		receiptBudgetBo bo = null;
		 try
		 {
		     bo = new receiptBudgetBo();
		
		 ArrayList list1 = bo.getDetailedHead(DHead);
		 if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						DHeadId = ((String)list.get(0));
					}
		    }
		
		 }
		 catch(Exception ex)
		 {
		     ex.printStackTrace();
		 }
		 return DHeadId;
	     }
	
	
	public boolean receiptBudgetCreate(HttpServletRequest request,ReceiptBudgetForm formData) throws IOException
	{
		ArrayList budgetFullList = new ArrayList();
		boolean flag = false;
		//BudgetDAO dao = null;
		receiptBudgetBo bo = null;

		try
		{
			
			RID = potDgenerator();
		//	 dao = new BudgetDAO();
			bo = new receiptBudgetBo();
			String param1[] = new String[10];
			
			String param2[] = new String[4];
			String param3[] = new String[4];
			String param4[] = new String[4];
			
	
			//String requestType = formData.getRequestType();
			String officeName = formData.getOfficeMaster();
			String fiscalYearId = formData.getFinancialYear();
			String majorHeadMaster = formData.getMajorHeadMaster();
			String subMajorHeadMaster = formData.getSubMajorHeadMaster();
			String minorHeadMaster = formData.getMinorHeadMaster();
			String scheme = formData.getSchemeMaster();
			String segment = formData.getSegmentMaster();
			String object = formData.getObjectMaster();
			String dHead = formData.getDetailedHead();
			String budgetId = RID;
		

			String apr = formData.getApr();
			String may = formData.getMay();
			String jun = formData.getJun();
			String jul = formData.getJul();
			String aug = formData.getAug();
			String sep = formData.getSep();
			String oct = formData.getOct();
			String nov = formData.getNov();
			String dec = formData.getDec();
			String jan = formData.getJan();
			String feb = formData.getFeb();
			String mar = formData.getMar();
			
			param1[0] = budgetId;
			//param1[1] = requestType;
			param1[1] = officeName;
			param1[2] = fiscalYearId;
			param1[3] = majorHeadMaster;
			param1[4] = subMajorHeadMaster;
			param1[5] = minorHeadMaster;
			param1[6] = scheme;
			param1[7] = segment;
			param1[8] = object;
			param1[9] = "P";
			
			
			flag = bo.receiptBudgetCreate(param1);
			
			String countAll = formData.getCount();
			String noOfRows = formData.getRowSize();
			int numberOfRows = Integer.parseInt(noOfRows);
			
			String myArray[] = countAll.split(",");
		/*	for(int i=0;i<myArray.length;i++)
			{
				//System.out.println("MyArray is ----------------"+myArray[i]);
			}
		
			for(int i=0;i<numberOfRows;i++)
			{
				//System.out.println("numberOfRows is --------------"+numberOfRows);
			} */
			
			 for(int j=0;j<myArray.length;)
			    {
				String str = myArray[0];
				
				myArray[j] = getDHeadName(myArray[j]);
				j = j+13;
			    }
		
			
			//for(int i=0;i<myArray.length;i++)
			//{
			//	System.out.println("After Alter DynamicValues form the Input ----------------"+myArray[i]);
			//}
			
			
			ArrayList myList = new ArrayList(); 
			//ArrayList fullList = bo.getMonthId(formData.getFinancialYear());
			
			ArrayList fullList = bo.getMonthId(fiscalYearId);
			
			
			for(int i=0;i<fullList.size();i++)
			{
			ArrayList subList = (ArrayList)fullList.get(i);
					myList.add(subList.get(0));
					
			}
			
			int count=0;
			int headCount=0;
			
			
		for(int i=0;i<numberOfRows;i++)
		{
			String detaildHead=myArray[count];
			count=count+1;
			param2[0] = budgetId;
				param2[1] = myArray[count];
			param2[2] = (String)myList.get(0);
			param2[3] = myArray[headCount];
			flag = bo.budgetMonthReq(param2);
			
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(1);
			param2[3] = myArray[headCount];
			
			flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(2);
			param2[3] = myArray[headCount];
				
			flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(3);
			param2[3] = myArray[headCount];
			
			flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(4);
			param2[3] = myArray[headCount];
						
			flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(5);
			param2[3] = myArray[headCount];
			
			flag = bo.budgetMonthReq(param2);
			
			
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(6);
			param2[3] = myArray[headCount];
			
						flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(7);
			param2[3] = myArray[headCount];
			
					flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(8);
			param2[3] = myArray[headCount];
				
						flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(9);
				param2[3] = myArray[headCount];
			
						flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(10);
			param2[3] = myArray[headCount];
			
			flag = bo.budgetMonthReq(param2);
			
			count=count+1;
			param2[0] = budgetId;
			param2[1] = myArray[count];
			param2[2] = (String)myList.get(11);
			param2[3] = myArray[headCount];
				
			flag = bo.budgetMonthReq(param2);
			count = count+1;
			headCount = headCount+13;
			
			}
			
		}
			
			
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return flag;
		
	}
	
	
	
	public ArrayList getBudgerRequestList(ReceiptBudgetForm data) throws Exception 
	{
		ArrayList list = new ArrayList();
		BudgetDAO dao = null;
		ReceiptBudgetForm budget = null;
		receiptBudgetBo bo = null;
		try
		{
			//dao = new BudgetDAO();
			bo = new receiptBudgetBo();
			
			String[] param = new String[1];
			String[] param2 = new String[2];
			
			String actionID = data.getTransactionId();
			
			String durationFrom = data.getDurationFrom();
			String durationTo = data.getDurationTo();
			
			
			param[0] = actionID;
			
			param2[0] = durationFrom;
			param2[1] = durationTo;
			
			
			ArrayList mainList = bo.getBudgerRequestList1(param);
			
			if(actionID != null & (!actionID.equals("")))
			{
				for(int i=0;i<mainList.size();i++)
				{
					ArrayList subList =(ArrayList) mainList.get(i);	
					 budget = new ReceiptBudgetForm();
					 budget.setTransactionId((String)subList.get(0));
					// budget.setRequestType((String)subList.get(1));
					 budget.setOfficeId((String)subList.get(1));
					 budget.setMajorHeadId((String)subList.get(2));
					 budget.setSubMajorHeadId((String)subList.get(3));
					 budget.setApprovedStatus(getStatus(subList));
						
					 //budget.setApr((String)subList.get(5));
					
					list.add(budget);
				}
			}
			else
			{
				ArrayList mainList2 = bo.getBudgerRequestList2(durationFrom,durationTo);
				if(mainList2!=null)
				{
					
					for(int i=0;i<mainList2.size();i++)
					{
					ArrayList subList =(ArrayList) mainList2.get(i);	
					budget = new ReceiptBudgetForm();
					 budget.setTransactionId((String)subList.get(0));
					// budget.setRequestType((String)subList.get(1));
					 budget.setOfficeId((String)subList.get(1));
					 budget.setMajorHeadId((String)subList.get(2));
					 budget.setSubMajorHeadId((String)subList.get(3));
					 budget.setApprovedStatus(getStatus(subList));
					list.add(budget);
					}
				}
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
	public String getStatus(ArrayList list)
	{
	    String str = (String)list.get(4);
	    String status = null;
	    if(str.equals("P"))
	    {
		status = "Pending";
	    }
	    if(str.equals("A"))
	    {
		status = "Approved";
	    }
	    if(str.equals("R"))
	    {
		status = "Rejected";
	    }
	   
	    return status;
	}
	
	

	
	
	
	public  void getFiscalYear(HttpServletRequest request) throws IOException
	{
		ArrayList fiscalYear = new ArrayList();
		HttpSession session = null;
		receiptBudgetBo bo = null;
		try
		{
			session = request.getSession();
			//BudgetDAO dao = new BudgetDAO();
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getFiscalYear(request);
			if(list1!=null)
			{	
					for(int i=0;i<list1.size();i++)
					{
						OfficeMasterDTO names = new OfficeMasterDTO();
						ArrayList list =(ArrayList)list1.get(i);
						names.setFinancialYearId((String)list.get(0));
						names.setFinancialYear((String)list.get(1));
						fiscalYear.add(names);
					}
		    }
			OfficeMasterDTO dto= new OfficeMasterDTO();
			dto.setList8(fiscalYear);
			session.setAttribute("fiscalYears",dto);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getOfficeNames in BD" + e);
			e.printStackTrace();
	    }
	}
	

	
	

	
	public String columnData(ArrayList al)
	{
		String listValue = al.get(0).toString();
		return listValue;
	}
	
	

	
	
		
	public void getDHead(HttpServletRequest request,String ojectId,String segmentId,String schemeId,String minorId,String subMjorId,String majorId) throws IOException
	{
	 //BudgetDAO dao = null;
		receiptBudgetBo bo = null;
		ArrayList setList = new ArrayList();
		
		HttpSession session = null;
		session = request.getSession();
		String userId = (String)session.getAttribute("UserId");
		if(userId==null)
		{
			userId="prakash"; 
		}
		try
		{
			
			bo = new receiptBudgetBo();
			
			
			ArrayList fullList = bo.getDHead(userId,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
			if(fullList!=null)
			{
				for(int i=0;i<fullList.size();i++)
				{
					//OfficeMasterDTO vo = new OfficeMasterDTO();
					ReceiptBudgetForm vao = new ReceiptBudgetForm();
					ArrayList subList = (ArrayList)fullList.get(i);
					vao.setDetailedHead(subList.get(1).toString());
					//vo.setDhName(subList.get(1).toString());
					setList.add(vao);
										
				}
			}
			//OfficeMasterDTO dto = new OfficeMasterDTO();
			//	dto.setList10(setList);
				session.setAttribute("detailedKey",setList);
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getOfficeNames in BD" + e);
			e.printStackTrace();
	    }
		//return setList;
				
	}
	

	
	
	
	public  ReceiptBudgetForm getBudgetRequestDetails(String budgetReqId) throws Exception
	{
	ArrayList getFullList = new ArrayList();
	ReceiptBudgetForm budget = null;
	ReceiptBudgetForm budget1 = null;
	receiptBudgetBo bo = null;
		try
		{
			
			//BudgetDAO potDao = new BudgetDAO();
			bo = new receiptBudgetBo();
			ArrayList list1 = bo.getBudgetRequestDetails(budgetReqId);
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();i++)
						{
				budget = new ReceiptBudgetForm();
				//budget1 = new BudgetForm();		
							ArrayList list =(ArrayList)list1.get(i);
							
							budget.setTransactionId((String)list.get(0));
							//budget.setRequestType((String)list.get(1));
							budget.setFinancialYear((String)list.get(1));
							budget.setMajorHeadId((String)list.get(2));
							budget.setSubMajorHeadId((String)list.get(3));
							budget.setMinorHeadId((String)list.get(4));
							budget.setOfficeMaster((String)list.get(5));
							budget.setSchemeMaster((String)list.get(6));
							budget.setSegmentMaster((String)list.get(7));
							budget.setObjectMaster((String)list.get(8));
							budget.setApprovedStatus(getStatusInDetails(list));
							
						    getFullList.add(budget);
													
						}
			}
		}
		catch(Exception e)
		{
			logger.error("this is Exception in getAllList in BD" + e);
			e.printStackTrace();
	    }
		
		return budget;
	}
	
	
	public String getStatusInDetails(ArrayList list)
	{
	    String str = (String)list.get(9);
	    String status = null;
	    if(str.equals("P"))
	    {
		status = "Pending";
	    }
	    if(str.equals("A"))
	    {
		status = "Approved";
	    }
	    if(str.equals("R"))
	    {
		status = "Rejected";
	    }
	    return status;
	}
	
	
	
	public  ArrayList getBudgetReqMonthDetails(String budgetReqId) throws Exception
	{
		ReceiptBudgetForm budget2 = null;
		
		ArrayList newList = new ArrayList();
		ArrayList monthList = null;
		ArrayList headList = null;
		receiptBudgetBo bo = null;
		try
		{
			monthList =new ArrayList();
			headList = new ArrayList();
		//	BudgetDAO potDao = new BudgetDAO();
			bo = new receiptBudgetBo();
			
			/*ArrayList list2 = potDao.getDetailedHeadId(budgetReqId);
			System.out.println("list2="+list2);*/
			ArrayList list1 = bo.getBudgetReqMonthDetails(budgetReqId);
			
			
			ReceiptBudgetForm form = null;
			ReceiptBudgetForm headId = null;
			
			
//			String head=null;
			ArrayList list = null;
			if(list1!=null)
			{	
			for(int i=0;i<list1.size();)
						{
				form = new ReceiptBudgetForm();
				list =(ArrayList)list1.get(i);
				form.setDetailedHead((String)list.get(0));
				list =(ArrayList)list1.get(i);
				form.setApr((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setMay((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setJun((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setJul((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setAug((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setSep((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setOct((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setNov((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setDec((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setJan((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setFeb((String)list.get(1));
				i=i+1;
				list =(ArrayList)list1.get(i);
				form.setMar((String)list.get(1));
				i=i+1;
			monthList.add(form);	
				}
			}
			
		}
		
		catch(Exception e)
		{
			logger.error("this is Exception getBudgetReqMonthDetails in BD" );
	        e.getStackTrace();
	        e.printStackTrace();
	    }
		
		return monthList;
	}
	
	
	
	public boolean budgetRequestApprove(ReceiptBudgetForm formData) throws SQLException
	{
		boolean flag = false;
	//	BudgetDAO dao = null;
		receiptBudgetBo bo = null;
		try
			{
			
			//	dao = new BudgetDAO();
			bo = new receiptBudgetBo();
				//String param1[] = new String[8];
				
				String param2[] = new String[4];
				String param3[] = new String[3];
				String param4[] = new String[3];
				
				String param5[] = new String[2];
				
				String year = formData.getFinancialYear();
				
				String apr = formData.getApr();
				String may = formData.getMay();
				String jun = formData.getJun();
				String jul = formData.getJul();
				String aug = formData.getAug();
				String sep = formData.getSep();
				String oct = formData.getOct();
				String nov = formData.getNov();
				String dec = formData.getDec();
				String jan = formData.getJan();
				String feb = formData.getFeb();
				String mar = formData.getMar();
				
			
				
				String budgetId = formData.getTransactionId();
				
				
				
				//String apporveId = "USER001";
				//String apporvedStatus = "A";
				String approvedStatus = formData.getApprovedStatus();
				
				//param5[0] = apporveId;
				param5[0] = approvedStatus;
				param5[1] = budgetId;
			
				flag = bo.budgetApporve(param5);
				
				
				String myArray[] = formData.getCount().split(",");
				String rowSize = formData.getRowSize();
				
				
				for(int j=0;j<myArray.length;)
				    {
					String str = myArray[0];
					
					myArray[j] = getDHeadName(myArray[j]);
					j = j+13;
				    }
				
//				for(int i=0;i<myArray.length;i++)
//				{
//					System.out.println("After Alter DynamicValues form the Input in ApproveFunction ----------------"+myArray[i]);
//				}
				
				ArrayList myList = new ArrayList();
				ArrayList fullList = bo.getMonthIdApprove(year);
				
				for(int i=0;i<fullList.size();i++)
				{
					ArrayList subList = (ArrayList)fullList.get(i);
					myList.add(subList.get(0));
						
				}
				
					
				int amount = 0;
				int headId = 0;
				
				amount = amount+1;
			int noOfRecords = Integer.parseInt(rowSize);
			
				for(int i=0;i<noOfRecords;i++)
				{
					String headIdValue = myArray[headId];
					int monthId = 0;
					
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
					
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
					
						monthId = monthId+1;
						amount = amount+1;
						
						
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
						
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
											
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
											
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
												
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
						param2[3] = budgetId;
							flag = bo.budgetMonthReqUpdate(param2);
						monthId = monthId+1;
						amount = amount+1;
											
						param2[0] = myArray[amount];
						param2[1] = (String)myList.get(monthId);
						param2[2] = headIdValue;
							param2[3] = budgetId;
								flag = bo.budgetMonthReqUpdate(param2);
						headId = headId+13;
						monthId = monthId+1;
						amount = amount+2;
						
				}
			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return flag;
		
	} 
	
	
	

}

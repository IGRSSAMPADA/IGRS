package com.wipro.igrs.expform76b.dao;

import java.util.ArrayList;


import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.expform76b.dto.ExpForm76BSelectDTO;
import com.wipro.igrs.expform76b.sql.ExpForm76BCommonSQL;

public class ExpForm76BDAO {
	
	public ArrayList getNextValueOfSeq()
	{
		System.out.println("getNextValueOfSeq");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_NEXT_VALUE_OF_SEQ;
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setBillNumber(subList.get(0).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
	}
	
	public ArrayList getAllDistricts()
	{
		System.out.println("getAllDistricts");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		ArrayList subList;
		DBUtility utility=null;
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_DISTRICTS;
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setDistrictName(subList.get(0).toString());
						expDTO.setDistrictID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
		
	}

	public ArrayList getAllFiscalYear()
	{
		System.out.println("getAllFiscalYear");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_FISCAL_YEARS;
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setFiscalYear(subList.get(0).toString());
						expDTO.setFiscalYearID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
		
	}
	
	public ArrayList getAllFiscalMonths()
	{
		System.out.println("getAllFiscalMonths");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_FISCAL_MONTHS;
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setFiscalMonth(subList.get(0).toString());
						expDTO.setFiscalMonthID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
		
	}

	public ArrayList getAllMajorHeads()
	{
		System.out.println("getAllMajorHeads");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_MAJOR_HEADS;
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setMajorHeadName(subList.get(0).toString());
						expDTO.setMajorHeadID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
		
	}
	
	public ArrayList getAllSubMajorHeads()
	{
		System.out.println("getAllSubMajorHeads");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_SUB_MAJOR_HEADS;
			
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setSubMajorName(subList.get(0).toString());
						expDTO.setSubMajorID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		return null;
	}
		
	public ArrayList getAllAccountHeads()
	{
		System.out.println("getAllAccountHeads");
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_ACCOUNT_HEADS;
			
			utility=new DBUtility();
			utility.createStatement();
			ArrayList result=utility.executeQuery(sql);
			
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setAccountHeadName(subList.get(0).toString());
						expDTO.setAccountHeadID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		return null;
	}
	
	public ArrayList getAllMinorHeadsRelatedToSubMajor(String subMajorID)
	{
		ExpForm76BSelectDTO expDTO=null;
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_ALL_MINOR_HEADS_RELATED_TO_SUB_MAJOR;
			String[] param=new String[1];
			param[0]=subMajorID;
			
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			
			if(result!=null && !result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					
					if(subList!=null && !subList.isEmpty())
					{
						expDTO=new ExpForm76BSelectDTO();
						expDTO.setMinorHeadName(subList.get(0).toString());
						expDTO.setMinorHeadID(subList.get(1).toString());
						returnedResult.add(expDTO);
					}
				}
				return returnedResult;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
		
	}
	
	public boolean isEmployeeHasStatuse(String empId)
	{
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.IS_EMPLOYEE_HAS_STATUSE;
			String[] param= new String[1];
			param[0]=empId;
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			
			if(result==null || result.isEmpty())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		return false;
	}
	
	public ExpForm76BSelectDTO getStatuseRelatedToEmployee(String empId)
	{
		ExpForm76BSelectDTO expDTO=new ExpForm76BSelectDTO();
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.GET_JOP_AND_PAY;
			String[] param= new String[1];
			param[0]=empId;
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			
			
			if(result!=null && ! result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					ArrayList subList=(ArrayList)result.get(i);
					if(subList!=null && ! subList.isEmpty())
					{
						expDTO.setEmpStatuse(subList.get(0).toString());
						expDTO.setEmpSalary(subList.get(1).toString());
					}
				}
				
				return expDTO;
				
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return null;
	}
	
	
	public boolean addNewDTLS(ExpForm76BSelectDTO expDTO)
	{
		System.out.println("Adding DAo");
		DBUtility utility=null;
		
		try
		{
			String sql=ExpForm76BCommonSQL.SAVE_NEW_RECORD;
			String[] param= new String[13];
			
			param[0]=expDTO.getBillNumber();
			param[1]=expDTO.getDistrictID();
			param[2]=expDTO.getFiscalYearID();
			param[3]=expDTO.getFiscalMonthID();
			param[4]=expDTO.getAccountHeadID();
			param[5]=expDTO.getMajorHeadID();
			param[6]=expDTO.getSubMajorID();
			param[7]=expDTO.getMinorHeadID();
			param[8]=expDTO.getGrantNumber();
			param[9]=expDTO.getEmpID();
			param[10]=expDTO.getAmountRequired();
			param[11]=expDTO.getSuretyTaken();
			param[12]=expDTO.getCreatedBy();
			
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			
			System.out.println("beforeeeeeeeeeeeeee");
			boolean add = utility.executeUpdate(param);
			System.out.println("afterrrrrrrrrrrrrrrr");
			System.out.println("=========== "+ add);
			
			if(add)
			{
				utility.commit();
				return true;
			}
			else
			{
				utility.rollback();
				return false;
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			   {
				   utility.closeConnection();
			   }
			   catch(Exception m)
			   {
				   m.printStackTrace();
			   }
		}
		
		return false;
	}
	
}

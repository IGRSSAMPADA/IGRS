package com.wipro.igrs.propertytypeuom.dao;

import java.util.ArrayList;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;
import com.wipro.igrs.propertytypeuom.dto.PropTypeUOMDTO;
import com.wipro.igrs.propertytypeuom.sql.PropTypeMapCommonSQL;

public class PropTypeMapDAO {
	
	public ArrayList getAllMappedData()
	{
		PropTypeUOMDTO propDTO=null;
		ArrayList returnedResult=new ArrayList();
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.SELECT_ALL_TYPE_MAPPING;
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
						propDTO=new PropTypeUOMDTO();
						
						propDTO.setId(subList.get(0).toString());
						propDTO.setPropertyTypeName(subList.get(1).toString());
						propDTO.setPropertyTypeL1Name(subList.get(2).toString());
						propDTO.setPropertyTypeL2Name(subList.get(3).toString());
						propDTO.setUom(subList.get(4).toString());
					}
					returnedResult.add(propDTO);
				}
				
				return returnedResult;
			}
		}
		catch(Exception e)
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

	public void deleteMapping(String id)
	{
		DBUtility utility=null;
		try
		{
			String sql=PropTypeMapCommonSQL.DELETE_MAPPING_BY_ID;
			utility=new DBUtility();
			String[] param= new String[1];
			param[0]=id;
			utility.createPreparedStatement(sql);
			boolean deleted=utility.executeUpdate(param);
			   if(deleted)
			   {
				   utility.commit();
			   }
			   else
			   {
				   utility.rollback();
			   }
		}
		catch(Exception g)
		{
			g.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
	}

	public ArrayList getAllPropertyType()
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		
		dataDTO.setPropertyTypeID("-select property name-");
		dataDTO.setPropertyTypeName("-select property name-");
		returnedResult.add(dataDTO);
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_ALL_PROPERTY_TYPE_MASTER;
			
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
						dataDTO=new MappingDataDTO();
						dataDTO.setPropertyTypeID(subList.get(0).toString());
						dataDTO.setPropertyTypeName(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception h)
		{
			h.printStackTrace();
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
	
	public ArrayList getAllL1Data()
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		
		dataDTO.setPropertyTypeL1ID("-select L1 name-");
		dataDTO.setPropertyTypeL1Name("-select L1 name-");
		returnedResult.add(dataDTO);
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_ALL_L1_MASTER;
			
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
						dataDTO=new MappingDataDTO();
						dataDTO.setPropertyTypeL1ID(subList.get(0).toString());
						dataDTO.setPropertyTypeL1Name(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception h)
		{
			h.printStackTrace();
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
	
	public ArrayList getAllL2Data()
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		
		dataDTO.setPropertyTypeL2ID("-select L2 name-");
		dataDTO.setPropertyTypeL2Name("-select L2 name-");
		returnedResult.add(dataDTO);
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_ALL_L2_MASTER;
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
						dataDTO=new MappingDataDTO();
						dataDTO.setPropertyTypeL2ID(subList.get(0).toString());
						dataDTO.setPropertyTypeL2Name(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception h)
		{
			h.printStackTrace();
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
	
	public ArrayList getAllUOMData()
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		
		dataDTO.setUomID("-select uom name-");
		dataDTO.setUomName("-select uom name-");
		returnedResult.add(dataDTO);
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_ALL_UOM_MASTER;
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
						dataDTO=new MappingDataDTO();
						dataDTO.setUomID(subList.get(0).toString());
						dataDTO.setUomName(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception h)
		{
			h.printStackTrace();
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
	
	public boolean addNewMapping(MappingDataDTO dataDTO)
	{
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.ADD_NEW_MAPPING;
			String[] param= new String[5];
			param[0]=dataDTO.getPropertyTypeID();
			param[1]=dataDTO.getPropertyTypeL1ID();
			param[2]=dataDTO.getPropertyTypeL2ID();
			param[3]=dataDTO.getUomID();
			param[4]="map";
			
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			

				boolean add = utility.executeUpdate(param);
				
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
		catch(Exception g)
		{
			g.printStackTrace();
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
	
	public boolean isExist(MappingDataDTO dataDTO)
	{
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.IS_EXIST;
			String[] param= new String[4];
			param[0]=dataDTO.getPropertyTypeID();
			param[1]=dataDTO.getPropertyTypeL1ID();
			param[2]=dataDTO.getPropertyTypeL2ID();
			param[3]=dataDTO.getUomID();
			
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
		catch(Exception f)
		{
			f.printStackTrace();
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

	public MappingDataDTO getMappingByID(String id)
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		DBUtility utility=null;
		try
		{
			String sql=PropTypeMapCommonSQL.GET_MAPPING_BY_ID;
			String[] param= new String[1];
			param[0]=id;
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
						dataDTO.setPropertyTypeID(subList.get(0).toString());
						dataDTO.setPropertyTypeL1ID(subList.get(1).toString());
						dataDTO.setPropertyTypeL2ID(subList.get(2).toString());
						dataDTO.setUomID(subList.get(3).toString());
					}
				}
				
			}
		}
		catch(Exception s)
		{
			s.printStackTrace();
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
		System.out.println(dataDTO.getPropertyTypeID());
		System.out.println(dataDTO.getPropertyTypeL1ID());
		System.out.println(dataDTO.getPropertyTypeL2ID());
		System.out.println(dataDTO.getUomID());
		return dataDTO;
	}

	public boolean updateMApping(MappingDataDTO dataDTO)
	{
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.UPDATE_MAPPING_DATA;
			String[] param= new String[5];
			param[0]=dataDTO.getPropertyTypeID();
			param[1]=dataDTO.getPropertyTypeL1ID();
			param[2]=dataDTO.getPropertyTypeL2ID();
			param[3]=dataDTO.getUomID();
			param[4]=dataDTO.getId();
			
			utility=new DBUtility();
			utility.createPreparedStatement(sql);
			boolean updated=utility.executeUpdate(param);
			if(updated)
			{
				utility.commit();
			}
			else
			{
				utility.rollback();
			}
			return updated;
		}
		catch(Exception r)
		{
			r.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		
		return false;
	}

	public ArrayList getRelatedL2Data(String l1)
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_RELATED_L2_DATA;
			utility=new DBUtility();
			String[] param= new String[1];
			param[0]=l1;
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			if(result!=null && ! result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					if(subList!=null && ! subList.isEmpty())
					{
						dataDTO=new MappingDataDTO();
						dataDTO.setPropertyTypeL2ID(subList.get(0).toString());
						dataDTO.setPropertyTypeL2Name(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList getRelatedL1Data(String property)
	{
		MappingDataDTO dataDTO=new MappingDataDTO();
		ArrayList returnedResult=new ArrayList();
		
		ArrayList subList;
		
		DBUtility utility=null;
		
		try
		{
			String sql=PropTypeMapCommonSQL.GET_RELATED_L1_DATA;
			utility=new DBUtility();
			String[] param= new String[1];
			param[0]=property;
			utility.createPreparedStatement(sql);
			ArrayList result=utility.executeQuery(param);
			if(result!=null && ! result.isEmpty())
			{
				for(int i=0;i<result.size();i++)
				{
					subList=(ArrayList)result.get(i);
					if(subList!=null && ! subList.isEmpty())
					{
						dataDTO=new MappingDataDTO();
						dataDTO.setPropertyTypeL2ID(subList.get(0).toString());
						dataDTO.setPropertyTypeL2Name(subList.get(1).toString());
					}
					returnedResult.add(dataDTO);
				}
				return returnedResult;
			}
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
		finally
		{
			try
			{
				utility.closeConnection();
			}
			catch(Exception d)
			{
				d.printStackTrace();
			}
		}
		return null;
	}
}

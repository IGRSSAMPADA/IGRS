package com.wipro.igrs.propertytypeuom.bao;

import java.util.ArrayList;

import com.wipro.igrs.propertytypeuom.dao.PropTypeMapDAO;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;

public class PropTypeMapBAO {
	
	PropTypeMapDAO propDAO=new PropTypeMapDAO();
	
	public ArrayList getAllMappedData()
	{
		return propDAO.getAllMappedData();
	}
	
	public void deleteMapping(String id)
	{
		propDAO.deleteMapping(id);
	}
	
	public ArrayList getAllDataToMap(int y)
	{
		ArrayList returnedResult=new ArrayList();
		ArrayList property=propDAO.getAllPropertyType();
		ArrayList l1=propDAO.getAllL1Data();
		ArrayList l2=propDAO.getAllL2Data();
		ArrayList uom=propDAO.getAllUOMData();
		
		returnedResult.add(property);
		
		// for the dependency
		if(y==0)
		{
			ArrayList temp1=new ArrayList();
			ArrayList temp2=new ArrayList();
			
			temp1.add((MappingDataDTO)l1.get(0));
			temp2.add((MappingDataDTO)l2.get(0));
			returnedResult.add(temp1);
			returnedResult.add(temp2);
		}
		else
		{
			returnedResult.add(l1);
			returnedResult.add(l2);
		}
		
		
		returnedResult.add(uom);
		
		return returnedResult;
	}
	
	public boolean addNewMapping(MappingDataDTO dataDTO)
	{
		if(! propDAO.isExist(dataDTO))
		{
			return propDAO.addNewMapping(dataDTO);
		}
		
			return false;
	}
	
	public MappingDataDTO getMappingByID(String id)
	{
		return propDAO.getMappingByID(id);
	}
	
	public boolean updateMapping(MappingDataDTO dataDTO)
	{
		if(!propDAO.isExist(dataDTO))
		{
			return propDAO.updateMApping(dataDTO);
		}
		return false;
	}
	
	public ArrayList getRelatedL2Data(String l1)
	{
		return propDAO.getRelatedL2Data(l1);
	}

	public ArrayList getRelatedL1Data(String property)
	{
		return propDAO.getRelatedL1Data(property);
	}
}

package com.wipro.igrs.propertytypeuom.bd;

import java.util.ArrayList;

import com.wipro.igrs.propertytypeuom.bao.PropTypeMapBAO;
import com.wipro.igrs.propertytypeuom.dto.MappingDataDTO;

public class PropTypeMapBD {
	
	PropTypeMapBAO propBAO=new PropTypeMapBAO();
	
	public ArrayList getAllMappedData()
	{
		return propBAO.getAllMappedData();
	}

	public void deleteMapping(String id)
	{
		propBAO.deleteMapping(id);
	}
	
	public ArrayList getAllDataToMap(int y)
	{
		return propBAO.getAllDataToMap(y);
	}
	
	public boolean addNewMapping(MappingDataDTO dataDTO)
	{
		return propBAO.addNewMapping(dataDTO);
	}
	
	public MappingDataDTO getMappingByID(String id)
	{
		return propBAO.getMappingByID(id);
	}
	public boolean updateMapping(MappingDataDTO dataDTO)
	{
		return propBAO.updateMapping(dataDTO);
	}
	
	public ArrayList getRelatedL2Data(String l1)
	{
		return propBAO.getRelatedL2Data(l1);
	}
	public ArrayList getRelatedL1Data(String property)
	{
		return propBAO.getRelatedL1Data(property);
	}
}

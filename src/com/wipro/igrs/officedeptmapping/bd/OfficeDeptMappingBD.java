package com.wipro.igrs.officedeptmapping.bd;

import java.util.Collection;

import com.wipro.igrs.officedeptmapping.bo.OfficeDeptMappingBO;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.exception.officeDeptMappingFoundException;

public class OfficeDeptMappingBD {
	
	
	OfficeDeptMappingBO officeDeptMappingBO = null;
	
	public OfficeDeptMappingBD()
	{
		officeDeptMappingBO = new OfficeDeptMappingBO();
	}
	
	public void addOfficeDeptMap(OfficeDeptDTO officeDeptDTO) throws officeDeptMappingFoundException
	{
		officeDeptMappingBO.addOfficeDeptMap(officeDeptDTO);
	}
	
	public void deleteOfficeDeptMap(OfficeDeptDTO officeDeptDTO)
	{
		officeDeptMappingBO.deleteOfficeDeptMap(officeDeptDTO);
	}
	
	public void deleteOfficeDeptMaps(String[] officeDeptIds)
	{
		officeDeptMappingBO.deleteOfficeDeptMaps(officeDeptIds);
	}
	
	public Collection getOfficeDeptList()
	{
		return officeDeptMappingBO.getOfficeDeptList();
	}
	
	public void editOfficeDeptMap(OfficeDeptDTO officeDeptDTO)
	{
		officeDeptMappingBO.editOfficeDeptMap(officeDeptDTO);
	}
	
	public OfficeDeptDTO getOfficeDeptByID(String officeDeptId)
	{
		return officeDeptMappingBO.getOfficeDeptByID(officeDeptId);
	}

}

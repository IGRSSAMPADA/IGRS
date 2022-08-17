package com.wipro.igrs.officedeptmapping.bo;

import java.util.Collection;

import com.wipro.igrs.officedeptmapping.dao.OfficeDeptMappingDAO;
import com.wipro.igrs.officedeptmapping.dto.OfficeDeptDTO;
import com.wipro.igrs.officedeptmapping.exception.officeDeptMappingFoundException;

public class OfficeDeptMappingBO {
	
	
	OfficeDeptMappingDAO officeDeptMapDAO = null;
	
	public OfficeDeptMappingBO()
	{
		officeDeptMapDAO = new  OfficeDeptMappingDAO();
	}
	
	public void addOfficeDeptMap(OfficeDeptDTO officeDeptDTO) throws officeDeptMappingFoundException
	{
		if(!officeDeptMapDAO.isOfficeDeptMapExists(officeDeptDTO))
		{
			officeDeptMapDAO.addOfficeDeptMapping(officeDeptDTO);
		}
		else
		{
			throw new officeDeptMappingFoundException();
		}
	}
	
	public void deleteOfficeDeptMap(OfficeDeptDTO officeDeptDTO)
	{
		if(officeDeptMapDAO.isOfficeDeptMapExists(officeDeptDTO))
			officeDeptMapDAO.deleteOfficeDeptMap(officeDeptDTO);
	}
	
	public void deleteOfficeDeptMaps(String[] officeDeptIds)
	{
		officeDeptMapDAO.deleteOfficeDeptMaps(officeDeptIds);
	}
	
	public Collection getOfficeDeptList()
	{
		return officeDeptMapDAO.getOfficeDeptList();
	}
	
	public void editOfficeDeptMap(OfficeDeptDTO officeDeptDTO)
	{
		officeDeptMapDAO.updateOfficeDeptMap(officeDeptDTO);
	}
	
	public OfficeDeptDTO getOfficeDeptByID(String officeDeptId)
	{
		return officeDeptMapDAO.getOfficeDeptByID(officeDeptId);
	}
	
	

}

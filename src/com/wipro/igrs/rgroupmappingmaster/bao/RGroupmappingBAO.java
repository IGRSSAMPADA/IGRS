/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionBD.java
 * Author      :   Sara Hussain 
 * Description :   Represents the Religion Bussiness Delegate Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */

package com.wipro.igrs.rgroupmappingmaster.bao;

import java.util.ArrayList;


import com.wipro.igrs.rgroupmappingmaster.dao.RGroupmappingDAO;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.exception.RGroupMappingAlreadyExistsException;

public class RGroupmappingBAO {
	
	RGroupmappingDAO rgmdao=new RGroupmappingDAO();
	
	public ArrayList getRgroupmappingList() throws Exception
	{
		return rgmdao.getRgroupmappingList();
	}
	
	public void addRGroupMapping(RGroupmappingDTO dto) throws Exception
	{
		/*if(!rgmdao.isExist(dto))
			rgmdao.addRGroupMapping(dto);
		else
			throw new RGroupMappingAlreadyExistsException();*/
	}
	
	public ArrayList getRoleList() throws Exception
	{
		return rgmdao.getRoleList();
	}
	
	public ArrayList getRolegroupList() throws Exception
	{
		return rgmdao.getRolegroupList();
	}	
	public void updateRGroupMapping(RGroupmappingDTO dto,RGroupmappingDTO oldDTO) throws Exception
	{
	/*	if(!rgmdao.isExist(dto) || (dto.getRolegroupId().equals(oldDTO.getRolegroupId()) && dto.getRoleId().equals(oldDTO.getRoleId())))
		{
			rgmdao.updateRgroupmapping(dto);
		} 
		else
			throw new RGroupMappingAlreadyExistsException();*/
	}
	
	/*public void deleteRGroupMapping(String mappingId) throws Exception
	{
		rgmdao.deleteRGroupMapping(mappingId);
	}
	public RGroupmappingDTO getMapping(String mappingId) throws Exception
	{
		return rgmdao.getMapping(mappingId);
	}*/


}

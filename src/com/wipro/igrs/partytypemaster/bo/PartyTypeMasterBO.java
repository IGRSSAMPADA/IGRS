package com.wipro.igrs.partytypemaster.bo;

import java.util.List;

import com.wipro.igrs.partytypemaster.dao.PartyTypeMasterDAO;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.exception.PartyTypeAlreadyExistException;

public class PartyTypeMasterBO {

	PartyTypeMasterDAO partyTypeMasterDAO = new PartyTypeMasterDAO();
	
	public boolean addPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO, String roleId, String funId, String userId) throws PartyTypeAlreadyExistException{
        boolean successAdding = false;
        if(partyTypeMasterDAO.isExist(partyTypeMasterDTO))
        	throw new PartyTypeAlreadyExistException();
        else if(partyTypeMasterDAO.addPartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId))
        	successAdding = true;
	   return successAdding;
	}
	
	public boolean editPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO, String roleId, String funId, String userId) throws PartyTypeAlreadyExistException{
		boolean successEditing = false;
		PartyTypeMasterDTO partyTypeMasterById = partyTypeMasterDAO.getPartyTypeMasterById(partyTypeMasterDTO.getId());
		if(partyTypeMasterDAO.isExist(partyTypeMasterDTO))
			if(!partyTypeMasterById.getName().equals(partyTypeMasterDTO.getName()))
	        	throw new PartyTypeAlreadyExistException();
		if(partyTypeMasterDAO.editPartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId))
			successEditing = true;
		
		return successEditing;
	}
	
	public boolean deleteAllPartyTypeMaster(String[] ids, String updateBy, String roleId, String funId, String userId){
		boolean successdeleting = false;
		for (int i = 0; i < ids.length; i++) {
			
			PartyTypeMasterDTO partyTypeMasterDTO = new PartyTypeMasterDTO();
			partyTypeMasterDTO.setId(ids[i]);
			partyTypeMasterDTO.setStatus("R");
			partyTypeMasterDTO.setUpdateBy(updateBy);
			if(!partyTypeMasterDAO.deletePartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId))
				return false;
			else
				successdeleting = true;
		}
		
			
		return successdeleting;
	}
	
	public List viewAllPartyTypeMaster(){
		List allParty = partyTypeMasterDAO.getAllPartyTypeMaster();
		return allParty;
	}
	
	public List getAllFunctions(){;
		return partyTypeMasterDAO.getAllFunctions();
	}
	
	public List getAllDependences(){
		return partyTypeMasterDAO.getAllDependences();
	}
	
	public PartyTypeMasterDTO getPartyTypeMasterById(String id){
	
		return partyTypeMasterDAO.getPartyTypeMasterById(id);
		
	}
	
	
}

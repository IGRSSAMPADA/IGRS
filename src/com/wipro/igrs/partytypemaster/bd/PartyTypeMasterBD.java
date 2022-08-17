package com.wipro.igrs.partytypemaster.bd;

import java.util.List;

import com.wipro.igrs.partytypemaster.bo.PartyTypeMasterBO;
import com.wipro.igrs.partytypemaster.dao.PartyTypeMasterDAO;
import com.wipro.igrs.partytypemaster.dto.PartyTypeMasterDTO;
import com.wipro.igrs.partytypemaster.exception.PartyTypeAlreadyExistException;

public class PartyTypeMasterBD {

		PartyTypeMasterBO partyTypeMasterBO = new PartyTypeMasterBO();
		
		public boolean addPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO, String roleId, String funId, String userId) throws PartyTypeAlreadyExistException{

			return partyTypeMasterBO.addPartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId);
		}
		
		public boolean editPartyTypeMaster(PartyTypeMasterDTO partyTypeMasterDTO, String roleId, String funId, String userId) throws PartyTypeAlreadyExistException{

			return partyTypeMasterBO.editPartyTypeMaster(partyTypeMasterDTO,roleId,funId,userId);
		}
		
		public boolean deleteAllPartyTypeMaster(String[] ids, String updateBy, String roleId, String funId, String userId){
			
			return partyTypeMasterBO.deleteAllPartyTypeMaster(ids,updateBy,roleId,funId,userId
);
		}
		
		public List viewAllPartyTypeMaster(){
			
			return partyTypeMasterBO.viewAllPartyTypeMaster();
		}
		
		public List getAllFunctions(){;
		return partyTypeMasterBO.getAllFunctions();
		}
	
		public List getAllDependences(){
		return partyTypeMasterBO.getAllDependences();
		}
		
		public PartyTypeMasterDTO getPartyTypeMasterById(String id){
			return partyTypeMasterBO.getPartyTypeMasterById(id);
		}
		
	

}

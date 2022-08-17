package com.wipro.igrs.deedparammapping.bo;

import java.util.List;

import com.wipro.igrs.deedparammapping.dao.DeedParamDAO;
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.exception.DeedParamMappingAlreadyExistException;

public class DeedParamBO{

	DeedParamDAO deedParamDAO = new DeedParamDAO();
	
	public List getAllDeedExemption() {
	
		return deedParamDAO.getAllDeedExemption();
	}

	public List getAllDeedParamMaster(){
		return deedParamDAO.getAllDeedParamMaster();
	}
	
	public List getAllDeedInstrument() {

		return deedParamDAO.getAllDeedInstrument();
	}

	
	public List getAllDeedParamMapping() {

		return deedParamDAO.getAllDeedParamMapping();
	}

	
	public List getAllDeedTypeMaster() {

		return deedParamDAO.getAllDeedTypeMaster();
	}

	
	public List getDeedExemptionByDeedTypeId(String id) {

		return deedParamDAO.getDeedExemptionByDeedTypeId(id);
	}

	
	public List getDeedInstrumentByDeedTypeId(String id) {

		return deedParamDAO.getDeedInstrumentByDeedTypeId(id);
	}

	
	public boolean mapTablesToDeepParam(DeedParamDTO deedParamDTO) throws DeedParamMappingAlreadyExistException {

		if(isExist(deedParamDTO)){
			throw new DeedParamMappingAlreadyExistException();
		}
		else{
			return deedParamDAO.mapTablesToDeepParam(deedParamDTO);
		}
		
	}

	
	public boolean updateMapTablesToDeepParam(DeedParamDTO deedParamDTO) throws DeedParamMappingAlreadyExistException{
		
		if(isExist(deedParamDTO)){
			throw new DeedParamMappingAlreadyExistException();
		}
		else{
			return deedParamDAO.updateMapTablesToDeepParam(deedParamDTO);
		}
		
	}

	public boolean deleteMapTablesToDeepParam(String[] ids){
		boolean b = false;
		DeedParamDTO deedParamMapping;
		for (int i = 0; i < ids.length; i++) {
			deedParamMapping = deedParamDAO.getDeedParamMappingById(ids[i]);
			deedParamMapping.setStatus("D");
			b = deedParamDAO.deleteMapTablesToDeepParam(deedParamMapping);
		}
		
		return b;
	}
	
	public List getExemptionsByInstrumentId(String id){
		return deedParamDAO.getExemptionsByInstrumentId(id);
	}
	
	public DeedParamDTO getDeedParamMappingById(String id){
		return deedParamDAO.getDeedParamMappingById(id);
	}
	
	public boolean isExist(DeedParamDTO deedParamDTO){
		
		return deedParamDAO.isExist(deedParamDTO);
	}

}

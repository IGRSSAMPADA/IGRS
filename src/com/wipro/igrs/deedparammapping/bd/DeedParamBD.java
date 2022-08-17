package com.wipro.igrs.deedparammapping.bd;

import java.util.List;

import com.wipro.igrs.deedparammapping.bo.DeedParamBO;
import com.wipro.igrs.deedparammapping.dto.DeedParamDTO;
import com.wipro.igrs.deedparammapping.exception.DeedParamMappingAlreadyExistException;

public class DeedParamBD{

	DeedParamBO deedParamBO = new DeedParamBO();
	
	public List getAllDeedExemption() {
	
		return deedParamBO.getAllDeedExemption();
	}
	
	public List getAllDeedParamMaster(){
		return deedParamBO.getAllDeedParamMaster();
	}
	

	public List getAllDeedInstrument() {
	
		return deedParamBO.getAllDeedInstrument();
	}

	public List getAllDeedParamMapping() {
	
		return deedParamBO.getAllDeedParamMapping();
	}


	public List getAllDeedTypeMaster() {
	
		return deedParamBO.getAllDeedTypeMaster();
	}


	public List getDeedExemptionByDeedTypeId(String id) {
	
		return deedParamBO.getDeedExemptionByDeedTypeId(id);
	}


	public List getDeedInstrumentByDeedTypeId(String id) {
	
		return deedParamBO.getDeedInstrumentByDeedTypeId(id);
	}

	
	public boolean mapTablesToDeepParam(DeedParamDTO deedParamDTO) throws DeedParamMappingAlreadyExistException {
	
		return deedParamBO.mapTablesToDeepParam(deedParamDTO);
	}

	public boolean updateMapTablesToDeepParam(DeedParamDTO deedParamDTO) throws DeedParamMappingAlreadyExistException {

		return deedParamBO.updateMapTablesToDeepParam(deedParamDTO);
	}

	public boolean deleteMapTablesToDeepParam(String[] ids){
		return deedParamBO.deleteMapTablesToDeepParam(ids);
	}
	
	public List getExemptionsByInstrumentId(String id){
		return deedParamBO.getExemptionsByInstrumentId(id);
	}
	
	public DeedParamDTO getDeedParamMappingById(String id){
		return deedParamBO.getDeedParamMappingById(id);
	}
	public boolean isExist(DeedParamDTO deedParamDTO){
		
		return deedParamBO.isExist(deedParamDTO);
	}
}

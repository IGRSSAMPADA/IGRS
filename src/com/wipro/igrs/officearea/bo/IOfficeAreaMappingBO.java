/**
 * 
 */
package com.wipro.igrs.officearea.bo;

import java.util.List;

import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.exception.AreaMappingAlreadyExistException;
import com.wipro.igrs.officearea.exception.OfficeAlreadyMappedException;
import com.wipro.igrs.officearea.exception.OfficeAreaMappingNotFoundException;

/**
 * @author HMOHAM
 *
 */
public interface IOfficeAreaMappingBO {

	List getOfficeMappings();
	void addOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) throws AreaMappingAlreadyExistException, OfficeAlreadyMappedException;
	void editOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) throws OfficeAreaMappingNotFoundException, AreaMappingAlreadyExistException;
	OfficeAreaMappingDTO getByOfficeId(String officeId);
	void deleteOfficeAreaMapping(List officeMappingList);
	void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping);
	OfficeAreaMappingDTO getByAreaMapping(OfficeAreaMappingDTO areaMapping);
	
	List getOffices();
	List getDistricts();
	List getTehsils(String districtId);
	List getWardPatwaris(String tehsilId);
	List getMohalaVilliges(String wardPatwariId);
}

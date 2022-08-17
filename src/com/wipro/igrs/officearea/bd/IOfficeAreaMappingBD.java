/**
 * 
 */
package com.wipro.igrs.officearea.bd;

import java.util.List;

import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.exception.AreaMappingAlreadyExistException;
import com.wipro.igrs.officearea.exception.OfficeAlreadyMappedException;
import com.wipro.igrs.officearea.exception.OfficeAreaMappingNotFoundException;

/**
 * @author HMOHAM
 *
 */
public interface IOfficeAreaMappingBD {

	List getOfficeMappings();
	void addOfficeAreaMapping(OfficeAreaMappingDTO areaDTO,String roleId, String funId, String userId) throws AreaMappingAlreadyExistException, OfficeAlreadyMappedException;
	void editOfficeAreaMapping(OfficeAreaMappingDTO areaDTO,String roleId, String funId, String userId) throws OfficeAreaMappingNotFoundException, AreaMappingAlreadyExistException;
	OfficeAreaMappingDTO getByOfficeId(String officeId);
	void deleteOfficeAreaMapping(List officeMappingList,String roleId, String funId, String userId);
	void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping,String roleId, String funId, String userId);
	OfficeAreaMappingDTO getByAreaMapping(OfficeAreaMappingDTO areaMapping);
	
	List getOffices();
	List getDistricts();
	List getTehsils(String districtId);
	List getWardPatwaris(String tehsilId);
	List getMohalaVilliges(String wardPatwariId);
}

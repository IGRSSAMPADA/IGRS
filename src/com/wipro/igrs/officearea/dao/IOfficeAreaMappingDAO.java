/**
 * 
 */
package com.wipro.igrs.officearea.dao;

import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import java.util.List;

/**
 * @author HMOHAM
 *
 */
public interface IOfficeAreaMappingDAO {

	List getOfficeMappings();
	OfficeAreaMappingDTO getByOfficeId(String OfficeId);
	void insertOfficeAreaMapping(OfficeAreaMappingDTO areaDTO);
	void updateOfficeAreaMapping(OfficeAreaMappingDTO areaDTO);
	void deleteOfficeAreaMapping(List officeMappingList);
	void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping);
	OfficeAreaMappingDTO getByAreaMapping(OfficeAreaMappingDTO areaMapping);
	
	List getOffices();
	List getDistricts();
	List getTehsils(String districtId);
	List getWardPatwaris(String tehsilId);
	List getMohalaVilliges(String wardPatwariId);
	
	
}

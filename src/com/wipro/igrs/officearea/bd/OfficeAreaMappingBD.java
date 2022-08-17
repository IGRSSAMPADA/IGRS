/**
 * 
 */
package com.wipro.igrs.officearea.bd;

import java.util.List;

import com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO;
import com.wipro.igrs.officearea.bo.OfficeAreaMappingBO;
import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.exception.AreaMappingAlreadyExistException;
import com.wipro.igrs.officearea.exception.OfficeAlreadyMappedException;
import com.wipro.igrs.officearea.exception.OfficeAreaMappingNotFoundException;

/**
 * @author HMOHAM
 *
 */
public class OfficeAreaMappingBD implements IOfficeAreaMappingBD {

	private IOfficeAreaMappingBO areaMappingBO = new OfficeAreaMappingBO();
	
	/**
	 * @throws OfficeAlreadyMappedException 
	 * @throws AreaMappingAlreadyExistException 
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#addOfficeAreaMapping(com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO)
	 */
	public void addOfficeAreaMapping(OfficeAreaMappingDTO areaDTO,String roleId, String funId, String userId) throws AreaMappingAlreadyExistException, OfficeAlreadyMappedException {
		areaMappingBO.addOfficeAreaMapping(areaDTO);
	}

	/**
	 * @throws AreaMappingAlreadyExistException 
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#editOfficeAreaMapping(com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO)
	 */
	public void editOfficeAreaMapping(OfficeAreaMappingDTO areaDTO,String roleId, String funId, String userId)
			throws OfficeAreaMappingNotFoundException, AreaMappingAlreadyExistException {
		
		areaMappingBO.editOfficeAreaMapping(areaDTO);

	}

	/**
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#getDistricts()
	 */
	public List getDistricts() {
		return areaMappingBO.getDistricts();
	}

	/**
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#getMohalaVilliges(java.lang.String)
	 */
	public List getMohalaVilliges(String wardPatwariId) {
		return areaMappingBO.getMohalaVilliges(wardPatwariId);
	}

	/**
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#getOffices()
	 */
	public List getOffices() {
		return areaMappingBO.getOffices();
	}

	/**
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#getTehsils(java.lang.String)
	 */
	public List getTehsils(String districtId) {
		return areaMappingBO.getTehsils(districtId);
	}

	/**
	 * @see com.wipro.igrs.officearea.bd.IOfficeAreaMappingBD#getWardPatwaris(java.lang.String)
	 */
	public List getWardPatwaris(String tehsilId) {
		return areaMappingBO.getWardPatwaris(tehsilId);
	}

	public OfficeAreaMappingDTO getByOfficeId(String officeId) {
		return areaMappingBO.getByOfficeId(officeId);
	}

	public List getOfficeMappings() {
		return areaMappingBO.getOfficeMappings();
	}

	public void deleteOfficeAreaMapping(List officeMappingList,String roleId, String funId, String userId) {
		areaMappingBO.deleteOfficeAreaMapping(officeMappingList);
	}

	public void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping,String roleId, String funId, String userId) {
		areaMappingBO.deleteOfficeAreaMapping(areaMapping);
	}

	public OfficeAreaMappingDTO getByAreaMapping(
			OfficeAreaMappingDTO areaMapping) {
		return areaMappingBO.getByAreaMapping(areaMapping);
	}

}

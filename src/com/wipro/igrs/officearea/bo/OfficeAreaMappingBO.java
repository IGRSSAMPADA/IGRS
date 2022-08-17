/**
 * 
 */
package com.wipro.igrs.officearea.bo;

import java.util.List;

import com.wipro.igrs.officearea.dao.IOfficeAreaMappingDAO;
import com.wipro.igrs.officearea.dao.OfficeAreaMappingDAO;
import com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO;
import com.wipro.igrs.officearea.exception.AreaMappingAlreadyExistException;
import com.wipro.igrs.officearea.exception.OfficeAlreadyMappedException;
import com.wipro.igrs.officearea.exception.OfficeAreaMappingNotFoundException;

/**
 * @author HMOHAM
 *
 */
public class OfficeAreaMappingBO implements IOfficeAreaMappingBO {

	private IOfficeAreaMappingDAO areaMappingDAO = new OfficeAreaMappingDAO();
	
	/**
	 * @throws AreaMappingAlreadyExistException 
	 * @throws OfficeAlreadyMappedException 
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#addOfficeAreaMapping(com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO)
	 */
	public void addOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) throws AreaMappingAlreadyExistException, OfficeAlreadyMappedException {
		
		if(getByOfficeId(areaDTO.getOfficeId()) != null)
			throw new OfficeAlreadyMappedException();
		
		if(getByAreaMapping(areaDTO) != null)
			throw new AreaMappingAlreadyExistException();
		
		areaMappingDAO.insertOfficeAreaMapping(areaDTO);
	}

	/** (non-Javadoc)
	 * @throws AreaMappingAlreadyExistException 
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#editOfficeAreaMapping(com.wipro.igrs.officearea.dto.OfficeAreaMappingDTO)
	 */
	public void editOfficeAreaMapping(OfficeAreaMappingDTO areaDTO) throws OfficeAreaMappingNotFoundException, AreaMappingAlreadyExistException {
		OfficeAreaMappingDTO officeMapping = areaMappingDAO.getByOfficeId(areaDTO.getOfficeId());
		
		if(officeMapping == null) {
			throw new OfficeAreaMappingNotFoundException();
		}
		
		OfficeAreaMappingDTO byAreaMapping = getByAreaMapping(areaDTO);
		if(byAreaMapping != null && !byAreaMapping.getOfficeId().equals(officeMapping.getOfficeId()))
			throw new AreaMappingAlreadyExistException();
		
		areaMappingDAO.updateOfficeAreaMapping(areaDTO);
	}

	/**
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#getDistricts()
	 */
	public List getDistricts() {
		return areaMappingDAO.getDistricts();
	}

	/**
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#getMohalaVilliges(java.lang.String)
	 */
	public List getMohalaVilliges(String wardPatwariId) {
		return areaMappingDAO.getMohalaVilliges(wardPatwariId);
	}

	/**
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#getOffices()
	 */
	public List getOffices() {
		return areaMappingDAO.getOffices();
	}

	/**
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#getTehsils(java.lang.String)
	 */
	public List getTehsils(String districtId) {
		return areaMappingDAO.getTehsils(districtId);
	}

	/**
	 * @see com.wipro.igrs.officearea.bo.IOfficeAreaMappingBO#getWardPatwaris(java.lang.String)
	 */
	public List getWardPatwaris(String tehsilId) {
		return areaMappingDAO.getWardPatwaris(tehsilId);
	}

	public OfficeAreaMappingDTO getByOfficeId(String officeId) {
		return areaMappingDAO.getByOfficeId(officeId);
	}

	public List getOfficeMappings() {
		return areaMappingDAO.getOfficeMappings();
	}

	public void deleteOfficeAreaMapping(List officeMappingList) {
		areaMappingDAO.deleteOfficeAreaMapping(officeMappingList);
		
	}

	public void deleteOfficeAreaMapping(OfficeAreaMappingDTO areaMapping) {
		areaMappingDAO.deleteOfficeAreaMapping(areaMapping);
		
	}

	public OfficeAreaMappingDTO getByAreaMapping(
			OfficeAreaMappingDTO areaMapping) {
		return areaMappingDAO.getByAreaMapping(areaMapping);
	}

	

}

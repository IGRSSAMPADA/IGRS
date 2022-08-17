package com.wipro.igrs.officemaster.bo;

import java.util.Collection;
import java.util.List;

import com.wipro.igrs.officemaster.dao.OfficeDAO;
import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.officemaster.exception.OfficeFoundException;
 
public class OfficeBO {
	
	OfficeDAO officeDAO = null;
	
	public OfficeBO()
	{
		officeDAO = new OfficeDAO();
	}

	public void addOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId) throws OfficeFoundException{
		if(!officeDAO.isOfficeExists(officeDTO.getOfficeName()))
			officeDAO.addOfficemaster(officeDTO,roleId,funId,userId);
		else
			throw new OfficeFoundException();
		
	}

	public Collection getOfficeList() {
		// TODO Auto-generated method stub
		return officeDAO.getOfficeList();
	}

	public OfficeDTO getOfficeById(String officeid) {
		// TODO Auto-generated method stub
		return officeDAO.getOfficeById(officeid);
	}

	public void updateOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId) throws OfficeFoundException{
		if(!officeDAO.isOfficeExists(officeDTO.getOfficeName()))
			officeDAO.updateOfficemaster(officeDTO,roleId,funId,userId);
		else
			throw new OfficeFoundException();
		
	}
	
	public void deleteOffice(String[] officeIds,String roleId, String funId, String userId)
	{
		officeDAO.deleteOffice(officeIds,roleId,funId,userId);
	}
	
	public List getOfficeTypeList() {
		return officeDAO.getOfficeTypeList();
	}

}

package com.wipro.igrs.officemaster.bd;

import java.util.Collection;
import java.util.List;

import com.wipro.igrs.officemaster.bo.OfficeBO;
import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.officemaster.exception.OfficeFoundException;

public class OfficeBD {
	OfficeBO officeBO = null;
	
	public OfficeBD()
	{
		officeBO = new OfficeBO();
	}
	
	public void addOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId) throws OfficeFoundException
	{
		officeBO.addOfficemaster(officeDTO,roleId,funId,userId);
	}
	
	public Collection getOfficeList() 
	{
		return officeBO.getOfficeList();
	}
	
	public void updateOfficemaster(OfficeDTO officeDTO,String roleId, String funId, String userId)throws OfficeFoundException
	{
		officeBO.updateOfficemaster(officeDTO,roleId,funId,userId);
	}
	
	public OfficeDTO getOfficeById(String officeid)
	{
		return officeBO.getOfficeById(officeid);
	}
	public void deleteOffice(String[] officeIds,String roleId, String funId, String userId)
	{
		officeBO.deleteOffice(officeIds,roleId,funId,userId);
	}
	
	public List getOfficeTypeList() {
		return officeBO.getOfficeTypeList();
	}

}

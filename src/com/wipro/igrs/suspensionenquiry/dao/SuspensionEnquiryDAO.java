/**
 * 
 */
package com.wipro.igrs.suspensionenquiry.dao;

import java.util.ArrayList;
import java.util.List;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.suspensionenquiry.dto.OfficesDTO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionDTO;
import com.wipro.igrs.suspensionenquiry.sql.SuspensionEnquirySQL;

/**
 * @author MRAMAD
 *
 */
public class SuspensionEnquiryDAO implements ISuspensionEnquiryDAO{
	
	DBUtility dbUtility = null;
	String sql = null;
	SuspensionDTO susDTO = null;
	List activityList = null;
	ArrayList subList = null;
	
	public List search(SuspensionCriteriaDTO dto){

		String param[] = null;
		
		if(dto.getLocation() == null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[1];
			param[0] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.REVOCATION_EXACT_DATE_ONLY;	
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[1];
			param[0] = dto.getLocation();
			sql = SuspensionEnquirySQL.LOCATION_ONLY;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[1];
			param[0] = dto.getOfficeId();
			sql = SuspensionEnquirySQL.BY_OFFICE_ONLY;
		}
		else if(dto.getLocation()== null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[1];
			param[0] = dto.getSuspensionOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_EXACT_DATE_ONLY;
		}
		else if(dto.getLocation() == null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[2];
			param[0] = dto.getRevocationOrderDateFrom();
			param[1] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.REVOCATION_RANGE_DATE_ONLY;
		}
		else if(dto.getLocation() == null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[2];
			param[0] = dto.getSuspensionOrderDateFrom();
			param[1] = dto.getSuspensionOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_RANGE_DATE_ONLY;
		}
		else if(dto.getLocation() == null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[4];
			param[0] = dto.getSuspensionOrderDateFrom();
			param[1] = dto.getSuspensionOrderDateTo();
			param[2] = dto.getRevocationOrderDateFrom();
			param[3] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_RANGE;
		}
		else if(dto.getLocation() == null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[2];
			param[0] = dto.getSuspensionOrderDateFrom();
			param[1] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_EXACT;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[5];
			param[0] = dto.getLocation();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getSuspensionOrderDateTo();
			param[3] = dto.getRevocationOrderDateFrom();
			param[4] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_RANGE_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[3];
			param[0] = dto.getLocation();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_EXACT_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[5];
			param[0] = dto.getOfficeId();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getSuspensionOrderDateTo();
			param[3] = dto.getRevocationOrderDateFrom();
			param[4] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_RANGE_OFFICE;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[3];
			param[0] = dto.getOfficeId();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_REVOCATION_EXACT_OFFICE;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[3];
			param[0] = dto.getLocation();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getSuspensionOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_RANGE_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()!=null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[3];
			param[0] = dto.getOfficeId();
			param[1] = dto.getSuspensionOrderDateFrom();
			param[2] = dto.getSuspensionOrderDateTo();
			sql = SuspensionEnquirySQL.SUSPENSION_RANGE_OFFICE;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[3];
			param[0] = dto.getLocation();
			param[1] = dto.getRevocationOrderDateFrom();
			param[2] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.REVOCATION_RANGE_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() != null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[3];
			param[0] = dto.getOfficeId();
			param[1] = dto.getRevocationOrderDateFrom();
			param[2] = dto.getRevocationOrderDateTo();
			sql = SuspensionEnquirySQL.REVOCATION_RANGE_OFFICE;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[2];
			param[0] = dto.getLocation();
			param[1] = dto.getSuspensionOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_EXACT_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() != null && dto.getRevocationOrderDateFrom() == null)
		{
			param = new String[2];
			param[0] = dto.getOfficeId();
			param[1] = dto.getSuspensionOrderDateFrom();
			sql = SuspensionEnquirySQL.SUSPENSION_EXACT_OFFICE;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()==null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[2];
			param[0] = dto.getLocation();
			param[1] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.REVOCATION_EXACT_LOCATION;
		}
		else if(dto.getLocation() != null && dto.getOfficeId()!=null && dto.getRevocationOrderDateTo() == null && dto.getSuspensionOrderDateTo()==null && dto.getSuspensionOrderDateFrom() == null && dto.getRevocationOrderDateFrom() != null)
		{
			param = new String[2];
			param[0] = dto.getOfficeId();
			param[1] = dto.getRevocationOrderDateFrom();
			sql = SuspensionEnquirySQL.REVOCATION_EXACT_OFFICE;
		}
		
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			activityList = new ArrayList();
			ArrayList mainList1 = dbUtility.executeQuery(param);
			for (int i = 0; i < mainList1.size(); i++) {
				
				subList = (ArrayList) mainList1.get(i);
				susDTO = new SuspensionDTO();
				
				susDTO.setEmpName(subList.get(0).toString());
				susDTO.setDesignation(subList.get(1).toString());
				susDTO.setOffice(subList.get(2).toString());
				susDTO.setSuspensionDate(subList.get(3).toString());
				susDTO.setSuspensionOrderNo(subList.get(4).toString());
				susDTO.setRevocationDate(subList.get(5).toString());
				susDTO.setRevocationOrderNo(subList.get(6).toString());
				if(subList.get(7) != null)
					susDTO.setCompetentAuthorityName(subList.get(7).toString());
				
				susDTO.setCompetentAuthorityDesignation(subList.get(8).toString());
				activityList.add(susDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	}
	 
	
	public List getOfficesByType(String officeTypeId){
		ArrayList typeList = new ArrayList();
		String[] param = new String[1];
		param[0] = officeTypeId;
		try {
			sql = SuspensionEnquirySQL.SELECT_OFFICES_BY_TYPE;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			OfficesDTO typeDTO = null;
			ArrayList mainList1 = dbUtility.executeQuery(param);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				typeDTO = new OfficesDTO();
				typeDTO.setOfficeId(subList.get(0).toString());
				typeDTO.setOfficeName(subList.get(1).toString());
				
				
				typeList.add(typeDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}

}

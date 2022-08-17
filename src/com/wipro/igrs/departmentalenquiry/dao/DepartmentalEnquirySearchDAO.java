package com.wipro.igrs.departmentalenquiry.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.departmentalenquiry.dto.ComplaintStatusDTO;
import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;
import com.wipro.igrs.departmentalenquiry.dto.DepartmentalEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.sql.DepartmentalEnquirySearchSQL;
import com.wipro.igrs.suspensionenquiry.dto.OfficesDTO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionDTO;
import com.wipro.igrs.suspensionenquiry.sql.SuspensionEnquirySQL;

public class DepartmentalEnquirySearchDAO implements
		IDepartmentalEnquirySearchDAO {

	DBUtility dbUtility = null;
	String sql = null;
	List activityList = null;
	ArrayList subList = null;
	DepartmentalEnquiryDTO deptEnquiryDTO = null;

	public List getOfficesByType(String officeTypeId) {

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
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return typeList;
	}

	public List search(DepartmentalCriteriaDTO dto) {

		String param[] = null;

		if (dto.getOfficeId() == null && dto.getOfficeTypeId() == null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() == null) {
			param = new String[1];
			param[0] = dto.getOrderDate_from();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_ONLY;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() == null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() == null) {
			param = new String[2];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_ONLY;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() == null
				&& dto.getOrderDate_to() == null && dto.getStatus() == null) {
			param = new String[1];
			param[0] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_LOCATION_ONLY;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() == null
				&& dto.getOrderDate_to() == null && dto.getStatus() == null) {
			param = new String[1];
			param[0] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_OFFICE_ONLY;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() == null
				&& dto.getOrderDate_from() == null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[1];
			param[0] = dto.getStatus();
			sql = DepartmentalEnquirySearchSQL.BY_STATUS_ONLY;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() == null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[2];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getStatus();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_STATUS;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() == null) {
			param = new String[2];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_LOCATION;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() == null) {
			param = new String[2];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_OFFICE;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() == null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() != null) {
			param = new String[3];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			param[2] = dto.getStatus();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_STATUS;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() == null) {
			param = new String[3];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			param[2] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_LOCATION;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() == null) {
			param = new String[3];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			param[2] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_OFFICE;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[3];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getStatus();
			param[2] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_STATUS_LOCATION;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() != null) {
			param = new String[4];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			param[2] = dto.getStatus();
			param[3] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_STATUS_LOCATION;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[3];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getStatus();
			param[2] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_EXACT_DATE_STATUS_OFFICE;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() != null
				&& dto.getOrderDate_to() != null && dto.getStatus() != null) {
			param = new String[4];
			param[0] = dto.getOrderDate_from();
			param[1] = dto.getOrderDate_to();
			param[2] = dto.getStatus();
			param[3] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_RANGE_DATE_STATUS_OFFICE;
		} else if (dto.getOfficeId() == null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() == null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[2];
			param[0] = dto.getStatus();
			param[1] = dto.getOfficeTypeId();
			sql = DepartmentalEnquirySearchSQL.BY_STATUS_LOCATION;
		} else if (dto.getOfficeId() != null && dto.getOfficeTypeId() != null
				&& dto.getOrderDate_from() == null
				&& dto.getOrderDate_to() == null && dto.getStatus() != null) {
			param = new String[4];
			param[0] = dto.getStatus();
			param[1] = dto.getOfficeId();
			sql = DepartmentalEnquirySearchSQL.BY_STATUS_OFFICE;
		}

		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			activityList = new ArrayList();
			ArrayList mainList1 = dbUtility.executeQuery(param);
			for (int i = 0; i < mainList1.size(); i++) {

				subList = (ArrayList) mainList1.get(i);
				deptEnquiryDTO = new DepartmentalEnquiryDTO();

				
				if(subList.get(0) != null)
					deptEnquiryDTO.setEmployeeName(subList.get(0).toString());
				
				if(subList.get(1) != null)
					deptEnquiryDTO.setEmployeeDesignation(subList.get(1).toString());
				
				if(subList.get(2) != null)
					deptEnquiryDTO.setEmployeeOffice(subList.get(2).toString());
				
				if(subList.get(3) != null)
					deptEnquiryDTO.setChargesheetDate(subList.get(3).toString());
				
				if(subList.get(4) != null)
					deptEnquiryDTO.setChargesheetNumber(subList.get(4).toString());
				
				if(subList.get(5) != null)
					deptEnquiryDTO.setCompetentAuthorityName(subList.get(5).toString());
				
				if(subList.get(6) != null)
					deptEnquiryDTO.setCompetentAuthorityDesignation(subList.get(6).toString());
				
				if(subList.get(7) != null)
					deptEnquiryDTO.setOrderDate(subList.get(7).toString());
				
				if(subList.get(8) != null)
					deptEnquiryDTO.setOrderNumber(subList.get(8).toString());
				
				if(subList.get(9) != null)
					deptEnquiryDTO.setOfficerName(subList.get(9).toString());
				
				if(subList.get(10) != null)
					deptEnquiryDTO.setOfficerDesignation(subList.get(10).toString());
				
				activityList.add(deptEnquiryDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return activityList;
	}

	public List getComplaintStatusList() {

		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList result = dbUtility
					.executeQuery(DepartmentalEnquirySearchSQL.GET_ALL_COMPLAINT_STATUS);

			ArrayList complaintStatusList = new ArrayList(result.size());

			ArrayList subList = null;
			for (int i = 0; i < result.size(); i++) {
				subList = (ArrayList) result.get(i);

				ComplaintStatusDTO statusDTO = new ComplaintStatusDTO();
				int indx = 0;

				statusDTO.setStatusId((String) subList.get(indx++));
				statusDTO.setStatusName((String) subList.get(indx++));

				complaintStatusList.add(statusDTO);
			}

			return complaintStatusList;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}

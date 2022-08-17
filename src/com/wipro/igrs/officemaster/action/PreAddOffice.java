package com.wipro.igrs.officemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.districtmaster.dao.DistrictDAO;
import com.wipro.igrs.mohallavillagemaster.dao.MohallavillageDAO;
import com.wipro.igrs.officemaster.dao.OfficeDAO;
import com.wipro.igrs.officemaster.dto.OfficeDTO;
import com.wipro.igrs.officemaster.form.OfficeMasterForm;
import com.wipro.igrs.tehsilmaster.dao.TehsilDAO;
import com.wipro.igrs.wardpatwarimaster.dao.WardpatwariDAO;

public class PreAddOffice extends BaseAction {
    

	DistrictDAO districtDAO = new DistrictDAO();
	TehsilDAO tehsilDAO = new TehsilDAO();
	WardpatwariDAO wardDAO = new WardpatwariDAO();
	MohallavillageDAO mohallaDAO = new MohallavillageDAO();
	OfficeDAO officeDAO = new OfficeDAO();
    
    public PreAddOffice() {
    }
    
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		OfficeMasterForm theForm = (OfficeMasterForm)form;
		
		OfficeDTO officeDTO = new OfficeDTO();
		officeDTO.setCreatedby("");
		officeDTO.setCreatedDate("");
		officeDTO.setDistrictId("");
		officeDTO.setDistrictName("");
		officeDTO.setMohallaVillageId("");
		officeDTO.setMohallaVillageName("");
		officeDTO.setOfficeAddress("");
		officeDTO.setOfficeDesc("");
		officeDTO.setOfficeFaxNumber("");
		officeDTO.setOfficeId("");
		officeDTO.setOfficeName("");
		officeDTO.setOfficePhoneNumber("");
		officeDTO.setOfficeStatus("");
		officeDTO.setOfficeTypeId("");
		officeDTO.setOfficeTypeName("");
		officeDTO.setParentId("");
		
		
		theForm.setOfficeDTO(officeDTO);
		theForm.setDistricts(districtDAO.getDistrictList());
		theForm.setTehsils(tehsilDAO.getTehsilList());
		theForm.setWards(wardDAO.getWardpatwariList());
		theForm.setMohallaVillages(mohallaDAO.getMohallavillageList());
		theForm.setOfficeTypes(officeDAO.getOfficeTypeList());
		theForm.setParents(officeDAO.getOfficeList());
		
//		request.setAttribute("parents", theForm.getParents());
//		request.setAttribute("districts", theForm.getDistricts());
//		request.setAttribute("tehsils", theForm.getTehsils());
//		request.setAttribute("wards", theForm.getWards());
//		request.setAttribute("mohallaVillages", theForm.getMohallaVillages());
//		request.setAttribute("officeTypes", theForm.getOfficeTypes());
		
		return mapping.findForward("createofficemaster");
	}

}
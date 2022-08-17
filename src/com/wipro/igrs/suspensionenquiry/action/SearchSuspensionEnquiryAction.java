package com.wipro.igrs.suspensionenquiry.action;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.suspensionenquiry.bd.SuspensionEnquiryBD;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionDTO;
import com.wipro.igrs.suspensionenquiry.form.SuspensionEnquirySearchForm;

public class SearchSuspensionEnquiryAction extends BaseAction {
    
    public SearchSuspensionEnquiryAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		SuspensionEnquirySearchForm bean = (SuspensionEnquirySearchForm)form;
		
		SuspensionCriteriaDTO criteriaDTO = bean.getCriteriaDTO();
		
		
//		if(bean.getSuspensionOrderDateFrom() != null && bean.getSuspensionOrderDateFrom() != "") {
//			Date date = sourceDateFormat.parse(bean.getSuspensionOrderDateFrom());
//			criteriaDTO.setSuspensionOrderDateFrom(targetDateFormat.format(date));
//		}
//		
//		if(bean.getSuspensionOrderDateTo() != null && bean.getSuspensionOrderDateTo() != "") {
//			Date date = sourceDateFormat.parse(bean.getSuspensionOrderDateTo());
//			criteriaDTO.setSuspensionOrderDateTo(targetDateFormat.format(date));
//		}
//		
//		if(bean.getRevocationOrderDateFrom() != null && bean.getRevocationOrderDateFrom() != "") {
//			Date date = sourceDateFormat.parse(bean.getRevocationOrderDateFrom());
//			criteriaDTO.setRevocationOrderDateFrom(targetDateFormat.format(date));
//		}
//		
//		if(bean.getRevocationOrderDateTo() != null && bean.getRevocationOrderDateTo() != "") {
//			Date date = sourceDateFormat.parse(bean.getRevocationOrderDateTo());
//			criteriaDTO.setRevocationOrderDateTo(targetDateFormat.format(date));
//		}
//		
//		if(bean.getLocation().equals("")){
//			System.out.println("Location Empty");
//			criteriaDTO.setLocation(null);
//		}
//		else
//			criteriaDTO.setLocation(bean.getLocation());
//		
//		if(bean.getOfficeId() == ""){
//			criteriaDTO.setOfficeId(null);
//			
//		}
//		else
//			criteriaDTO.setOfficeId(bean.getOfficeId());
//		
//		
		
		
		
		SuspensionEnquiryBD susBD = new SuspensionEnquiryBD();
		
		List list = (List)susBD.search(criteriaDTO);
		
		
		request.setAttribute("suspensionEnquiryResultList", list);
		
		return mapping.findForward("preSearchSuspension");
	}

}
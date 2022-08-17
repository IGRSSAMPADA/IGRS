package com.wipro.igrs.departmentalenquiry.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquirySearchBD;
import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;
import com.wipro.igrs.departmentalenquiry.form.DepartmentalEnquirySearchForm;

public class DepartmentalEnquirySearchAction extends BaseAction {
    

	private DepartmentalEnquirySearchBD enquirySearchBD = new DepartmentalEnquirySearchBD();
    
    public DepartmentalEnquirySearchAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        
    	DepartmentalEnquirySearchForm bean = (DepartmentalEnquirySearchForm)form;
    	
    	DepartmentalCriteriaDTO criteriaDTO = bean.getCriteriaDTO();
    	
    	Collection deptEnquiryList = enquirySearchBD.search(criteriaDTO);
    	
    	request.setAttribute("deptEnquiryResultList", deptEnquiryList);
    	
    	return mapping.findForward("preDeptEnquirySearch");
    }

}
package com.wipro.igrs.complaintdetails.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.complaintdetails.bd.ComplaintDetailsBD;
import com.wipro.igrs.complaintdetails.dto.ComplaintDetailsCriteriaDTO;
import com.wipro.igrs.complaintdetails.form.ComplaintDetailsForm;


public class SearchComplaintAction extends BaseAction {
    
	private SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");


    
    public SearchComplaintAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
     
    	try{
    		
    		ComplaintDetailsForm bean = (ComplaintDetailsForm)form;
    		
    		ComplaintDetailsCriteriaDTO criteriaDTO = new ComplaintDetailsCriteriaDTO();
    		
    	    		
    		if(bean.getPaymentDateFrom() != null && bean.getPaymentDateFrom() != "") {
    			Date date = sourceDateFormat.parse(bean.getPaymentDateFrom());
    			criteriaDTO.setPaymentDateFrom(targetDateFormat.format(date));
    		}
    		
    		if(bean.getPaymentDateTo() != null && bean.getPaymentDateTo() != "") {
    			Date date = sourceDateFormat.parse(bean.getPaymentDateTo());
    			criteriaDTO.setPaymentDateTo(targetDateFormat.format(date));
    		}
    		
    		if(bean.getEmpFirstName().equals("")){
    			criteriaDTO.setEmpFirstName(null);
    		}
    		else
    			criteriaDTO.setEmpFirstName(bean.getEmpFirstName());
    		
       		if(bean.getEmpMidName().equals("")){
    			criteriaDTO.setEmpMidName(null);
    		}
    		else
    			criteriaDTO.setEmpMidName(bean.getEmpMidName());
       		
      		if(bean.getEmpLastName().equals("")){
    			criteriaDTO.setEmpLastName(null);
    		}
    		else
    			criteriaDTO.setEmpLastName(bean.getEmpLastName());
       		
      		if(bean.getEmpCode().equals("")){
    			criteriaDTO.setEmpCode(null);
    		}
    		else
    			criteriaDTO.setEmpCode(bean.getEmpCode());
      		
      		if(bean.getStatus().equals("")){
    			criteriaDTO.setStatus(null);
    		}
    		else
    			criteriaDTO.setStatus(bean.getStatus());
      		
      		if(bean.getLocation().equals("")){
    			System.out.println("Location Empty");
    			criteriaDTO.setLocation(null);
    		}
    		else
    			criteriaDTO.setLocation(bean.getLocation());
    		
    		if(bean.getOfficeId() == ""){
    			criteriaDTO.setOfficeId(null);
    			
    		}
    		else
    			criteriaDTO.setOfficeId(bean.getOfficeId());
   
    		
    		ComplaintDetailsBD compBD = new ComplaintDetailsBD();
    		
    		List list = (List)compBD.search(criteriaDTO);
    		
    		System.out.println("size"+list.size());
  
    		request.setAttribute("ComplaintDetailsResultList", list);
    	   return mapping.findForward("preSearch");
	    }
	    catch(Exception e)
	    {
		   e.printStackTrace();
		   return null;
	    }
    }
}
package com.wipro.igrs.hrpayroll.hrp.action;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.hrpayroll.hrp.bd.BasicSlabBD;
import com.wipro.igrs.hrpayroll.hrp.dto.BasicSlabDTO;
import com.wipro.igrs.hrpayroll.hrp.form.BasicSlabForm;

public class BasicSlabAction  extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(BasicSlabAction.class);

	String FORWARD_PAGE = "success";
	BasicSlabBD basicSlabBD = null;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {

			String roleId = (String)session.getAttribute("role");
			String funId = (String)session.getAttribute("functionId");
			String userId = (String)session.getAttribute("UserId");
			ArrayList slabList = null;
			String nextAction  = request.getParameter("nextAction");
			boolean insert = false;
			BasicSlabForm basicSlabForm = (BasicSlabForm)form;

			if(nextAction != null && nextAction.length() > 0){ 
					if(nextAction.equalsIgnoreCase("create")){
				basicSlabBD = new BasicSlabBD();
				BasicSlabDTO basicSlabDTO = convertDTO(basicSlabForm);
				insert = basicSlabBD.createBasicSlab(basicSlabDTO,userId);
				
				logger.debug("In the action--> "+insert);
				FORWARD_PAGE="success";
			}
			else if(nextAction.equalsIgnoreCase("viewList")){
				basicSlabBD = new BasicSlabBD();
				slabList = basicSlabBD.retrieveBasicSlabList();  
				
				if(slabList != null){
					session.setAttribute("slabList", slabList);
				}
				FORWARD_PAGE = "viewSlabs";
			}
			else if(nextAction.equalsIgnoreCase("view")){
				String slabId = request.getParameter("basicSlabId");
				
				basicSlabBD = new BasicSlabBD();
				BasicSlabDTO  basicSlabDTO = basicSlabBD.retrieveBasicSlabDetails(slabId);  
				basicSlabForm = convertToForm(basicSlabDTO);
				
				session.setAttribute("basicSlabForm", basicSlabForm);
				FORWARD_PAGE = "viewSlabDetails";
			}
			else if(nextAction.equalsIgnoreCase("delete")){
				String deleteBank = request.getParameter("deleteBank");
				basicSlabBD = new BasicSlabBD();
			
				StringTokenizer st = new StringTokenizer(deleteBank, "*");

				while (st.hasMoreTokens()) {
					basicSlabBD.deleteSlabMaster(st.nextToken());
				}
								
				FORWARD_PAGE = "success";
			}
			else if(nextAction.equalsIgnoreCase("update")){
					basicSlabBD = new BasicSlabBD();
					BasicSlabDTO basicSlabDTO = convertDTO(basicSlabForm);
					insert = basicSlabBD.updateBasicSlab(basicSlabDTO,userId);
					
					logger.debug("In the action--> "+insert);
					FORWARD_PAGE="success";
				}		
		
		return mapping.findForward(FORWARD_PAGE);
		
	}
			
			return mapping.findForward(FORWARD_PAGE);
	}
	
	
	
	
	private BasicSlabDTO convertDTO(BasicSlabForm basicSlabForm){
		BasicSlabDTO basicSlabDTO = new BasicSlabDTO();
		basicSlabDTO.setBasicSlabMin(basicSlabForm.getBasicSlabMin());
		basicSlabDTO.setBasicSlabMax(basicSlabForm.getBasicSlabMax());
		basicSlabDTO.setBasicSlabIncrement(basicSlabForm.getBasicSlabIncrement());
		basicSlabDTO.setBasicSlabValidFrom(basicSlabForm.getBasicSlabValidFrom());
		basicSlabDTO.setBasicSlabValidTo(basicSlabForm.getBasicSlabValidTo());
		basicSlabDTO.setBasicSlabRemarks(basicSlabForm.getBasicSlabRemarks());
		basicSlabDTO.setStatus(basicSlabForm.getStatus());
		basicSlabDTO.setBasicSlabId(basicSlabForm.getBasicSlabId());
		
		return basicSlabDTO;
	}
	private BasicSlabForm convertToForm(BasicSlabDTO basicSlabDTO){
		BasicSlabForm basicSlabForm = new BasicSlabForm();
		basicSlabForm.setBasicSlabId(basicSlabDTO.getBasicSlabId());
		basicSlabForm.setBasicSlabMin(basicSlabDTO.getBasicSlabMin());
		basicSlabForm.setBasicSlabMax(basicSlabDTO.getBasicSlabMax());
		basicSlabForm.setBasicSlabIncrement(basicSlabDTO.getBasicSlabIncrement());
		basicSlabForm.setBasicSlabValidFrom(basicSlabDTO.getBasicSlabValidFrom());
		basicSlabForm.setBasicSlabValidTo(basicSlabDTO.getBasicSlabValidTo());
		basicSlabForm.setBasicSlabRemarks(basicSlabDTO.getBasicSlabRemarks());
		basicSlabForm.setStatus(basicSlabDTO.getStatus());
		
		return basicSlabForm;
	}
	
}

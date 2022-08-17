package com.wipro.igrs.regcompletion.action;


/**
 * ===========================================================================
 * File           :   DeedAction.java
 * Description    :   Represents the Deed Action Class
 * @author        :   Imran Shaik
 * Created Date   :   April 26, 2008
 * Updated Date			Version			UpdatedBy
 * May 27, 2008			1.0				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;
import com.wipro.igrs.regcompletion.form.ApplicantForm;


public class DeedAction extends 
BaseAction  {
	
	private  Logger logger = 
		(Logger) Logger.getLogger(DeedAction.class);
	
	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session){
		String forwardName = "success";

		RegCompBO regCompBO = new RegCompBO();
		ApplicantForm appForm = (ApplicantForm)form;
		
		String deed = appForm.getDeed();
		String bname = appForm.getBname();

		//ArrayList deedList = regCompBO.getDeedType();
		CommonDTO dto = null;
		RegCompBD regCompBD = new RegCompBD();
	//	HttpSession session = request.getSession();
		
		String inst = request.getParameter("inst");
		String deedId = request.getParameter("deedId");
		String deedIdMap=request.getParameter("deedIdMap");
		//getting form object from request
		
			if(request.getAttribute("regCommonDto")!= null){
				dto = (CommonDTO)request.getAttribute("regCommonDto");
				appForm = (ApplicantForm)request.getAttribute("appForm");
			}else{
				dto = new CommonDTO();
				appForm = (ApplicantForm)form;
			}
		// Added By Aruna
		if(deedIdMap!=null)
		{						  
				try {
						appForm.setRegInitCompDTO(regCompBD.getCompleteDeedDetails(deedIdMap));
						forwardName="compDeedDtls";
						return mapping.findForward(forwardName);
					} catch (Exception e) {			
						logger.debug(e);
					}
		}

		
		//getting application details for given regNo
		if(appForm.getPageTitle()!= null && appForm.getPageTitle().equalsIgnoreCase("regDetails")){
			RegInitCompleteDTO regInitCompDTO = (RegInitCompleteDTO)regCompBO.getRegApplicationDetails(appForm.getRegNo());
			session.setAttribute("selectedDeeds", new ArrayList());
			forwardName = "page1";
		}
		
		//getting deed details for given Deed Id
		if(deedId!=null){
			dto = null;
			appForm = null;
			//RegInitCompleteDTO  regInitCompDTO = (RegInitCompleteDTO) regCompBO.getDeedDetails(deedId);
			dto = appForm.getCommonDTO();
			forwardName = "details";
			deed = appForm.getDeed(); 
			bname = appForm.getBname();
			appForm.setNofProperty(deedId);
			logger.info("forwardName="+forwardName);
			request.setAttribute("regCommonDto", dto);
			request.setAttribute("appForm", appForm);
			return mapping.findForward(forwardName);
		}
		
		//setting instruments,exemptions and form fields for selected deed
		if(deed != null){
			//  dto.setInstList(regCompBO.getInstrument(deed));
			//  dto.setExmpList(regCompBO.getExemption(deed, "deed",null));
			  appForm.setDeed(deed);
			  appForm.setBname(bname);
			//  appForm.setFormFields(regCompBO.getFormFields(deed));
		}
		

		//setting exemptions for selected instrument
		if(inst != null){
		//	ArrayList list = regCompBO.getExemption(inst,"instrument",appForm.getDeed());
			//dto.setExmpList(list);
			appForm.setDeed(deed);
			appForm.setBname(bname);
		}
		
		//updating all values in data base
		if(request.getParameter("update")!=null){
			String action=request.getParameter("update").toString();
			
			boolean boo = false;
			appForm = (ApplicantForm)form;
			appForm.setExemption(appForm.getMname());
			if(action.equalsIgnoreCase("update")){
					//boo = regCompBO.updateDeedDetails(appForm);
					request.setAttribute("update","Updated Deed Details Successfully");
			}else{
					//boo = regCompBO.deleteDeedDetails(appForm);
					request.setAttribute("update","Deleted Deed Details Successfully");
					ArrayList txnDeedList=(ArrayList) session.getAttribute("txnDeedList");
					if(txnDeedList != null & txnDeedList.size()>0){
						for(int i=0;i<txnDeedList.size();i++)
							if(txnDeedList.get(i).toString().equalsIgnoreCase(appForm.getNofProperty())){
								txnDeedList.remove(i);
								session.setAttribute("txnDeedList", txnDeedList);
							if(txnDeedList.size()>0){
								ArrayList list=(ArrayList) session.getAttribute("deedList");
								if(list!=null)
									list.add(regCompBO.getDeed(deed));
								session.setAttribute("deedList", list);
								action="update";
							}
						}
					}
			}
			if(boo){
				forwardName = action;
				logger.info("forwardName="+forwardName);
				return mapping.findForward(forwardName);
			}
		}
		

		logger.info("forwardName="+forwardName);
		request.setAttribute("regCommonDto", dto);
		request.setAttribute("appForm", appForm);
		return mapping.findForward(forwardName);
	}
}

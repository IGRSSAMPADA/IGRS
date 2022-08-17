package com.wipro.igrs.regcompletion.action;


/**
 * ===========================================================================
 * File           :   CommonAction.java
 * Description    :   Represents the Common Action Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.form.ApplicantForm;


public class CommonAction extends 
BaseAction  {
	private  Logger logger = 
		(Logger) Logger.getLogger(CommonAction.class);
	
	
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
		RegCompBD regCompBD = new RegCompBD();
		ApplicantForm appForm = (ApplicantForm)form;
		
		String param = request.getParameter("param") ;
		if("regSearch".equals(param)) {
			forwardName = "page1";
		}
		
		 if(appForm != null) {
			String formName = appForm.getFormName();
			String actionName = appForm.getActionName();
			logger.debug("formName:-"+formName);
			logger.debug("actionName:-"+actionName);
			
			CommonDTO dto = appForm.getCommonDTO();
			if(RegCompConstant.REGCOMP_SEARCH_FORM.equals(formName)){
				if(RegCompConstant.SEARCH_ACTION.equals(actionName)) {
					appForm.setCheckRegNo("W");
					logger.debug("appForm.getRegNo():-"+appForm.getRegNo());
					//appForm = regCompBO.getRegApplicationDetails(appForm.getRegNo());
					if(appForm == null)
						request.setAttribute("update","Application Number is Unavailable.Please Check the Application Number");
					else
						session.setAttribute("App_No", appForm.getRegNo());
					forwardName = "page1";
				}
				//appForm.setCommonDTO(dto);
			}
		
		 }
		/*String formInst = appForm.getInstrument();
		String formExm = appForm.getMname();
		String formAttr = appForm.getFname();
		CommonDTO dto = null;
		String serviceId = "SER_006"; 
		double gStampDuty = 0.0;
	    double gOtherFee  = 0.0;
	    double gDutyVal  = 0.0;
	    double gRegFee  = 0.0;
	    double gTotal  = 0.0;
	    double dPaid = 0.0;
		
		ArrayList deedList = regCompBO.getDeedType();
		ArrayList complList = null ;
		ArrayList propDeedList = null ;
		//HttpSession session = request.getSession();
		
		String deed = request.getParameter("deed");
		String inst = request.getParameter("inst");
		String check = request.getParameter("check");
		String comp =  request.getParameter("complSuc");
		String spot =  request.getParameter("spot");
        String userId = (String)session.getAttribute("EmpId");
        String userType = (String)session.getAttribute("roleId");
        String regTxnId = (String)session.getAttribute("App_No");
        String deedRegId  = request.getParameter("deedRegId");
        String functionId = (String)session.getAttribute("functionId");
        
        if(appForm != null)
        appForm.setUserId(userId);
		
		ArrayList selectedDeeds = (ArrayList)session.getAttribute("selectedDeeds");
		if(selectedDeeds==null)
			selectedDeeds = new ArrayList();
		
		//getting form object from request
		if(request.getAttribute("regCommonDto")!= null){
			dto = (CommonDTO)request.getAttribute("regCommonDto");
			appForm = (ApplicantForm)request.getAttribute("appForm");
		}else{
			dto = new CommonDTO();
			appForm = (ApplicantForm)form;
		}
		
		
		
		
		
		dto.setDeedList(deedList);
		
		//getting application details for given regNo
		if(appForm.getPageTitle()!= null && appForm.getPageTitle().equalsIgnoreCase("regDetails")){
			
			appForm = regCompBO.getRegApplicationDetails(appForm.getRegNo());
			forwardName = "page1";
			if(appForm == null)
				request.setAttribute("update","Application Number is Unavailable.Please Check the Application Number");
				else
					session.setAttribute("App_No", appForm.getRegNo());
		}
		
	
		//setting instruments,exemptions and form fields for selected deed
		if(deed != null){
			  dto.setInstList(regCompBO.getInstrument(deed));
			  dto.setExmpList(regCompBO.getExemption(deed, "deed",null));
			  appForm.setDeed(deed);
			  appForm.setInstrument("");
			  appForm.setFormFields(regCompBO.getFormFields(deed));
		}
		
		//clearing instruments and exemptions for selected deed value is [Select] 
		if(deed == null || deed.equalsIgnoreCase("")){
			dto.setInstList(null);
			dto.setExmpList(null);
			if(appForm!=null)
			appForm.setFormFields(null);
		}

		//setting exemptions for selected instrument
		if(inst != null ){
			ArrayList list = regCompBO.getExemption(inst,"instrument",appForm.getDeed());
			dto.setExmpList(list);
			session.setAttribute("inst", inst);
		}
		
		//setting data in to session for add more deeds to the list
		if(request.getParameter("addMore")!=null){
			ArrayList selectedList = (ArrayList) session.getAttribute("txnDeedList");
			session.setAttribute("txnDeedList", null);
			session.setAttribute("selectedDeeds", null);
			session.setAttribute("selectedList", selectedList);
		}
		//updating all values in session
		if(request.getParameter("addNew")!=null && request.getParameter("addNew").length() > 0){
			String temp = appForm.getDeed();
			//getting all the selected deeds list from session
			ArrayList list = (ArrayList)session.getAttribute("deedList");
			
			logger.info("selectedDeeds="+selectedDeeds.size());
			if(session.getAttribute("inst")!=null)
				formInst = session.getAttribute("inst").toString();
			if(list == null)
				list = dto.getDeedList();
			if(selectedDeeds == null)
				selectedDeeds = new ArrayList();
			CommonDTO tempDto= null;
			appForm.setInstrument((String) session.getAttribute("inst"));
			appForm.setExemption(formExm);
			appForm.setPageTitle(formAttr);
			logger.info("session.getAttribute('inst')="+session.getAttribute("inst"));
			for(int i=0;i<list.size();i++){
				tempDto = (CommonDTO)list.get(i);
					if(tempDto.getId().equalsIgnoreCase(temp)){
						selectedDeeds.add(appForm);
						appForm = new ApplicantForm();
						dto = new CommonDTO();
						list.remove(i);
						session.setAttribute("inst", null);
					}
			}
			dto.setDeedList(list);
			session.setAttribute("deedList", list);
			session.setAttribute("selectedDeeds", selectedDeeds);
			String page=request.getParameter("addNew").toString();
			if(page.equalsIgnoreCase("addNew")){
				forwardName = "success";
				appForm.setInstrument(null);
			}
			else{
				//save data in to database
				ArrayList txnDeedList = regCompBO.saveDeedDetails(selectedDeeds);
				ArrayList deedDetails = (ArrayList) session.getAttribute("selectedList");
				if(txnDeedList.size()>0){
					if(deedDetails != null && deedDetails.size()>0)
						for(int i=0;i<deedDetails.size();i++)
							txnDeedList.add(deedDetails.get(i));
					session.setAttribute("txnDeedList", txnDeedList);
					forwardName = "page2";
					request.setAttribute("update","Saved Deed Details Successfully");
				}
			}
				
		}
		
		ArrayList list = (ArrayList)session.getAttribute("deedList");
		if(list == null)
			list = dto.getDeedList();
		else
			dto.setDeedList(list);
		
		//when we click on next button from main page of deed
		if(request.getParameter("main")!=null && request.getParameter("main").length() > 0 && appForm!=null){
			forwardName = "page2";
		}
		if(request.getParameter("next")!=null){
			dto.setDeedList(regCompBO.getDeedType());
		}
		//source one code
		if(check!=null)
		{			
			complList = regCompBD.getComplList();
			forwardName = "compliance";
		}

		//inserting complains data
		if(comp != null)
		{
			boolean boo = false;
			String regId = (String)session.getAttribute("App_No"); 
			boo = regCompBD.insComplDet(appForm.getRegNo(),appForm.getCompId(),appForm.getUserId());
			String regCompId = (String)session.getAttribute("regCompId"); 
			boo = regCompBD.updateRegDetails(regCompId);
			if(boo){
			forwardName = "complSuc";
			}
		}
		//storing registration details and sending application for spot inspection
		if(spot != null)
		{
			try {
				//inserting the selected registration document details here
				ArrayList attachments= (ArrayList)session.getAttribute("attachment1");
				if(attachments!=null)
					regCompBD.storeRegDocuments(attachments);
				} catch (Exception e) {
					logger.error(e);
				}
			String payId=(String)session.getAttribute("status"); 
			session.setAttribute("App_No",appForm.getRegNo());
			String regId = regCompBD.insertRegDetails(appForm.getRegNo(),appForm.getStampDuty(),appForm.getRegFee(),appForm.getTotal(),payId,appForm.getUserId(),spot);
			session.setAttribute("regCompId",regId);
			if(spot.equalsIgnoreCase("spot"))
				forwardName = "toSpotInspection";
			else
				forwardName = "toPayment";
		}

		
		if(deedRegId != null)
		{
			dto.setRegNo(deedRegId);
			try {
				propDeedList = regCompBD.getPrDeedDet(dto.getRegNo(),functionId,userType,serviceId);
			for(int i=0;i<propDeedList.size();i++)
				{
					dto = (CommonDTO)propDeedList.get(i);
					if(dto.getStampDuty()!=null)
						gStampDuty = gStampDuty +Double.parseDouble(dto.getStampDuty());
					else
						gStampDuty = gStampDuty + 0;
					if(dto.getOtherFee()!=null)
						gOtherFee = gOtherFee + Double.parseDouble(dto.getOtherFee());
					else
						gOtherFee = gOtherFee + 0;
					if(dto.getDutyVal()!=null)
						gDutyVal = gDutyVal + Double.parseDouble(dto.getDutyVal());
					else
						gDutyVal = gDutyVal + 0;
					if(dto.getRegFee()!=null)
						gRegFee = gRegFee + Double.parseDouble(dto.getRegFee());
					else
						gRegFee = gRegFee + 0;
					if(dto.getTotal()!=null)
						gTotal = gTotal  + Double.parseDouble(dto.getTotal());
					else
						gTotal = gTotal  + 0;
				}
				String dutyPaid = regCompBD.getDutyPaid(dto.getRegNo());
				dPaid = Double.parseDouble(dutyPaid);
				gTotal = gTotal - dPaid;
				IGRSCommon common = new IGRSCommon();
				appForm.setStampDuty(dutyPaid);
				appForm.setOtherFee(String.valueOf(gOtherFee));
				appForm.setGMarketVal(String.valueOf(gDutyVal));
				appForm.setRegFee(common.getCurrencyFormat(gRegFee));
				appForm.setTotal(String.valueOf(gTotal));
			} catch (Exception e) {
				e.printStackTrace();
			}
			forwardName = "mapRes";
			session.setAttribute("forwardPath","paySuc1.do");
		}
		
		dto.setMapList(propDeedList);
		dto.setComplList(complList);
		dto.setDeedList(regCompBO.getDeedType());
		//session.setAttribute("user",userType);
        session.setAttribute("amount",appForm.getTotal());
		
        if(appForm!=null){
		appForm.setInstrument(null);
		appForm.setCommonDTO(dto);
		}
		
        logger.debug("forwardName="+forwardName);
		request.setAttribute("regCommonDto", dto);
		request.setAttribute("appForm", appForm);*/
		//System.out.println("forwardName----------------->   "+forwardName+"   userId   "+userId);
		return mapping.findForward(forwardName);
	}
}

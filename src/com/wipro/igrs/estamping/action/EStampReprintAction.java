package com.wipro.igrs.estamping.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.estamping.bo.DutyCalculationBO;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.estamping.form.DutyCalculationForm;


public class EStampReprintAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws  
                                                 Exception {
    	Logger logger = 
    		(Logger) Logger.getLogger(EStampReprintAction.class);
    		String forwardJsp = "estampReprint1";
    	
    		String language=(String)session.getAttribute("languageLocale");
    		String officeId = (String)session.getAttribute("loggedToOffice");
    		DutyCalculationForm esform = (DutyCalculationForm) form;
       
        String lang=(String)session.getAttribute("languageLocale");
        String fwdPage = (String)request.getParameter("fwdName");
        String regTxnId=(String)request.getParameter("regisId");
        String dutyID=(String)request.getParameter("dutyId");
        String deedPath=(String)request.getParameter("path");
        String partyID=(String)request.getParameter("partyId");
        String actionName = "";
		boolean checkEstamp=false;
		if (request.getParameter("actionName") != null) {

			actionName = request.getParameter("actionName");

		}

		/*else

		{

			actionName = dto.getActionName();
		}*/
//ActionForward srSlotReport = null;
        /*Date date = new Date();
        SRReportDTO srDTO = new SRReportDTO();
		SimpleDatesformat yearformat = new SimpleDatesformat("MM/dd/yyyy");
		String yfmt = yearformat.format(date);
		srDTO.setFromDateSys(yfmt);*/
		DashBoardDTO objDashBoardDTO1 = esform.getObjDashBoardDTO();
		EstampBO objEstampBO = new EstampBO();
		DutyCalculationBO bo = new DutyCalculationBO();
		objDashBoardDTO1.setReprintFlag("");
		
if(esform !=null) {
	
	if ("SEARCH_ESTAMP_DETAILS".equalsIgnoreCase(actionName)) {

		esform.getObjDashBoardDTO().setCheck("");
		esform.getObjDashBoardDTO().setIsConsumedChecked(0);

		ArrayList estampCode;
		String estampCodeId = esform.getObjDashBoardDTO().getEcode();
		objDashBoardDTO1.setEcode(estampCodeId);
		logger.debug("--------------->" + objDashBoardDTO1.getEcode());
		logger.debug("--------------->" + estampCodeId);
		estampCode = objEstampBO.checkEcode(estampCodeId);

		logger.debug("----------------> inside search action");

		if (estampCode.size() == 0) {
			esform.getObjDashBoardDTO().setCheck("Y");
			logger
			.debug("----------------> no ecode obtained from database");
			forwardJsp = "estampReprint1";
			logger.debug("-------------->" + forwardJsp);
		} else {
			
			String estampid = estampCodeId.substring(3, 4);
			ArrayList ecodeDetailsDRS = new ArrayList();
									if (estampid.equalsIgnoreCase("1")) {
										ecodeDetailsDRS = objEstampBO.viewEcodeDetailsNJ_DRS(estampCodeId, language);  
										logger.debug("----------------> ecode obtained");
										
									} 
									else if (estampid.equalsIgnoreCase("2")) {
										ecodeDetailsDRS = objEstampBO.viewEcodeDetailsDRS(estampCodeId, language);
										logger.debug("----------------> ecode obtained");	
									}
//			ArrayList ecodeDetailsDRS = objEstampBO
//			.viewEcodeDetailsDRS(estampCodeId,language);
			logger.debug("----------------> ecode obtained");
			if (ecodeDetailsDRS.size() == 0)
				esform.getObjDashBoardDTO().setViewDRSEcodeDetails(null);
			else
				esform.getObjDashBoardDTO().setViewDRSEcodeDetails(
						ecodeDetailsDRS);

			request.setAttribute("ecodeDetails", esform
					.getObjDashBoardDTO().getViewDRSEcodeDetails());
			logger.debug("----------------> ecodeDetails obtained");

			String ecodeTypeID = estampCodeId.substring(3, 4);

			ArrayList ecodeType = objEstampBO
			.viewEcodeType(ecodeTypeID,language);

			if (ecodeType.size() == 0)
				esform.getObjDashBoardDTO().setViewRUEcodeType(null);
			else
				esform.getObjDashBoardDTO()
				.setViewRUEcodeType(ecodeType);

			request.setAttribute("ecodeType", esform
					.getObjDashBoardDTO().getViewRUEcodeType());

			ArrayList viewApplicantDetailsDRS = objEstampBO
			.getDetailsOfApplicantDRS(estampCodeId,language);
			logger.info("--------------->"
					+ viewApplicantDetailsDRS.size());
			String moduleFlag=bo.moduleFlag(estampCodeId);
			if("R".equalsIgnoreCase(moduleFlag))
			{
				String txnId=bo.getRegId(estampCodeId);
				bo.getDutyDetailsInitiation(txnId, esform,"I");
				esform.setBreifDocument("NA");
			}
			else if("C".equalsIgnoreCase(moduleFlag))
			{
				String txnId=bo.getRegId(estampCodeId);
				bo.getDutyDetailsInitiation(txnId, esform,"C");
				esform.setBreifDocument("NA");
			}
			else
			{
			esform.setBreifDocument(bo.getBreifDocument(estampCodeId));
			bo.getDutyDetails(estampCodeId,esform);
			}
			esform.setEstampTypeCheck(bo.getEstampCodeType(estampCodeId));
			if (viewApplicantDetailsDRS.size() == 0)
				esform.getObjDashBoardDTO().setPartyDetailsDRS(null);
			else
				esform.getObjDashBoardDTO().setPartyDetailsDRS(
						viewApplicantDetailsDRS);
			
			//request.setAttribute("estampType",esform.getEstampTypeCheck());

			request.setAttribute("applicantDetails", esform
					.getObjDashBoardDTO().getPartyDetailsDRS());

			for (int i = 0; i < viewApplicantDetailsDRS.size(); i++) {
				DashBoardDTO ddto = (DashBoardDTO) viewApplicantDetailsDRS
				.get(i);

				logger.info("--------------->" + ddto.getAppStatus());
				logger.info("--------------->" + ddto.getAppType());
				logger.info("--------------->" + ddto.getPartyType());
				logger.info("--------------->"
						+ ddto.getAppAuthFirstName());
				logger.info("--------------->"
						+ ddto.getPartyAuthFirstName());
			}
			
			ArrayList ecodeConsumption = objEstampBO
			.getecodeConsumption(estampCodeId,language);
			logger.debug("----------------> ecode obtained");
			if (ecodeDetailsDRS.size() == 0)
				esform.getObjDashBoardDTO().setEcodeConStatus(null);
			else
				esform.getObjDashBoardDTO().setEcodeConStatus(
						ecodeConsumption);

			request.setAttribute("ecodeDetails", esform
					.getObjDashBoardDTO().getEcodeConStatus());
			logger.debug("----------------> ecodeDetails obtained");

			
			logger.debug("----------------> ecodeConsumption Status obtained");
			if(esform.getEstampTypeCheck().trim().equals("jud")){
				forwardJsp = "estampReprintJud";
			}else{
				forwardJsp = "estampReprintNonJud";
			}
			//forwardJsp = "estampReprint2";
		}
		// return mapping.findForward(forwardJsp);
		//esform.getObjDashBoardDTO().setEcode("");
		
	}
	if ("SUBMIT_REPRINT".equalsIgnoreCase(actionName)) {
		
		String estampCodeId = esform.getObjDashBoardDTO().getEcode();
		ArrayList reportList=new ArrayList<DashBoardDTO>();
		ArrayList list=new ArrayList();
	    reportList= bo.getReprintStatus(estampCodeId);

			
			for(int i=0;i<reportList.size();i++)
			{
			
			ArrayList subList=(ArrayList)reportList.get(i);
			
			objDashBoardDTO1.setModflag(subList.get(0).toString());
			objDashBoardDTO1.setEstatus(subList.get(1).toString());	
			objDashBoardDTO1.setCstatus(subList.get(2).toString());
			objDashBoardDTO1.setRefundId(subList.get(3).toString());
			
			}
			
			// Changes added by Gulrej Gera on 26th Oct, to show court details on submission
			//request.setAttribute("estampType", esform.getEstampTypeCheck().trim());
	if(reportList.size()>0){
		if (objDashBoardDTO1.getRefundId().equalsIgnoreCase("--")){
		if (objDashBoardDTO1.getModflag().equalsIgnoreCase("E")){
			if (objDashBoardDTO1.getCstatus().equalsIgnoreCase("Not Consumed")){
		String Status=objDashBoardDTO1.getEstatus();
		System.out.println(Status);
		if(objDashBoardDTO1.getEstatus().equalsIgnoreCase("A")){
			objDashBoardDTO1.setReprintFlag("Y");
			//forwardJsp = "estampReprint2";
		}
		else if(objDashBoardDTO1.getEstatus().equalsIgnoreCase("D")){
			objDashBoardDTO1.setReprintFlag("N");
			
		//forwardJsp = "estampReprint2";
		
	}
		}
			else{
				objDashBoardDTO1.setReprintFlag("Consumed");
				//forwardJsp = "estampReprint2";
			}
		}
		else{
			objDashBoardDTO1.setReprintFlag("R");
			//forwardJsp = "estampReprint2";
		}


  
}else{
			
			objDashBoardDTO1.setReprintFlag("Refund");
			//forwardJsp = "estampReprint2";
		}
		
		if(esform.getEstampTypeCheck().trim().equals("jud")){
			forwardJsp = "estampReprintJud";
		}else{
			forwardJsp = "estampReprintNonJud";
		}
	}
		

}
	

	
	if ("SUBMIT_REMARKS".equalsIgnoreCase(actionName)) {
		String estampCodeId = esform.getObjDashBoardDTO().getEcode();
		String userId=(String) session.getAttribute("UserId");
		
		String remarks=esform.getRemarks();
		Boolean insert = false;
		
		insert= bo.insertdata(estampCodeId,userId,remarks);
if(insert){
	Boolean update = false;
	update= bo.updatedata(estampCodeId);
	esform.setRemarks(""); 	
	forwardJsp ="estampReprint3";
}
else{
	forwardJsp ="failure";
}
		
	}
	if ("RESET_ACTION".equalsIgnoreCase(actionName)) {
		esform.getObjDashBoardDTO().setEcode("");
	}

} 

return mapping.findForward(forwardJsp);
    }
}



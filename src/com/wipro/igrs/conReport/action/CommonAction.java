package com.wipro.igrs.conReport.action;

/**
 * ===========================================================================
 * File           :   CommonAction.java
 * Description    :   Represents the Common Action Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.conReport.bd.ConReportBD;
import com.wipro.igrs.conReport.dto.ConReportDTO;
import com.wipro.igrs.conReport.form.ConReportForm;

public class CommonAction extends BaseAction{

	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @param session The HTTP Session we are processing.
     */
    private Logger logger = 
	(Logger) Logger.getLogger(CommonAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
	    logger.debug("Enter in common action");
		ConReportBD bd = null;
		ConReportDTO dto = null;
		ConReportForm conform = (ConReportForm)form;
		String forward = "fail";
		String param = request.getParameter("logged");
		String action = request.getParameter("action");
		String empId = (String) session.getAttribute("UserId");
		String empConId = request.getParameter("empConId");
		String supreConId = request.getParameter("supreConId");
		String reviewConId = request.getParameter("reviewConId");
		
		String editSupreId = request.getParameter("editSupreId");
		String editReviewId = request.getParameter("editReviewId");
		String editEmpId = request.getParameter("editEmpId");
		boolean boo = false;
		try{
		if(param!=null){
			dto = new ConReportDTO();
			if(param.equalsIgnoreCase("employee")){
				bd = new ConReportBD();
				dto = bd.getEmpDetails(empId);
			}else if(param.equalsIgnoreCase("supervisor")){
				bd = new ConReportBD();
				dto = bd.getEmpsList(empId,param);
			}
			else if(param.equalsIgnoreCase("review")){
				bd = new ConReportBD();
				dto = bd.getEmpsList(empId,param);
			}
			else if(param.equalsIgnoreCase("empview")){
				bd = new ConReportBD();
				dto = bd.getEmpsList(empId,param);
			}
			forward = param;
		}
		
		if(action!=null){
		    dto = new ConReportDTO();
		    bd = new ConReportBD();
			dto = bd.getEmpDetails(empId);
			dto.setEmpId(empId);
			dto.setFiYearId(conform.getFiYear());
			dto.setComments(conform.getComments());
			dto.setRepStatus(conform.getRepStatus());
			
			if(action.equalsIgnoreCase("empsave")){
				String args[] = {empId,conform.getFiYear(),conform.getComments(),conform.getRepStatus()};
				boo = bd.saveEmaployeeComments(args);
			}
			if(action.equalsIgnoreCase("supersave") || action.equalsIgnoreCase("superUpdate")){
				String args[] = {conform.getRepStatus(),conform.getRemarks(),conform.getComments(),empId,conform.getConId()};
				boo = bd.saveSupervisorComments(args);
			}
			if(action.equalsIgnoreCase("reviewsave") || action.equalsIgnoreCase("reviewUpdate")){
				String args[] = {conform.getRepStatus(),conform.getRemarks(),conform.getComments(),empId,conform.getConId()};
				boo = bd.saveReviewerComments(args);
			}
			if(action.equalsIgnoreCase("empUpdate")){
				String args[] = {conform.getRepStatus(),conform.getFiYear(),conform.getComments(),conform.getConId()};
				boo = bd.updateEmaployeeComments(args);
			}
			forward = "success";
			/*dto = new ConReportDTO();
			dto = bd.getEmpDetails(empId);*/
			
		}
		
		if(empConId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			dto = bd.getConReportInfo(reviewConId);
			forward = "empReport";
		}
		
		if(supreConId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			dto = bd.getConReportInfo(supreConId);
			forward = "superReport";
		}

		if(reviewConId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			dto = bd.getConReportInfo(reviewConId);
			forward = "reviewReport";
		}
		
		
		
		if(editSupreId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			conform = bd.getSuperEditInfo(editSupreId);
			dto = conform.getDto();
			forward = "superEdit";
		}
		
		if(editReviewId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			conform = bd.getReviewEditInfo(editReviewId);
			dto = conform.getDto();
			forward = "reviewEdit";
		}
		
		if(editEmpId!=null){
			dto = new ConReportDTO();
			bd = new ConReportBD();
			conform = bd.getEmpEditInfo(editEmpId);
			dto = conform.getDto();
			forward = "empEdit";
		}
		}catch (Exception e) {
			logger.debug("Exception in common action");
			e.printStackTrace();
		}
		
		request.setAttribute("conform", conform);
		session.setAttribute("conDto", dto);
		session.setAttribute("fiYear", dto.getFiYear());
		logger.debug("forward------------------->"+forward);
		return mapping.findForward(forward);
	}
	
}
/**
 * 
 * TrainingAction.java
 */
package com.wipro.igrs.empmgmt.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.EmpmgmtConstant;
import com.wipro.igrs.empmgmt.bd.TrainingBD;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;
import com.wipro.igrs.empmgmt.dto.TrainingDTO;
import com.wipro.igrs.empmgmt.form.TrainingForm;
import com.wipro.igrs.empmgmt.rule.EmpMgmtRule;

/** 
 * MyEclipse Struts
 * Creation date: 06-04-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */

public class TrainingAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(TrainingAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session
			) throws Exception  {
		logger.debug("inside action");
		//HttpSession session=request.getSession();
		String Forward_Page=null;
		
		TrainingDTO trainingDTO=null;
		EmployeeDTO employeeDTO=null;
		TrainingBD trainingBD=null;
		TrainingForm trainingForm=(TrainingForm)form;
		String strActionType = trainingForm.getActionType();
		logger.debug("Action Type "+strActionType);
		
		if(strActionType==null){
		   strActionType ="New";	
		}
		
		EmpMgmtRule rule=null;
        PrintWriter out = response.getWriter();
        
	logger.debug("actionType>>>>>"+	trainingForm.getActionType());
		try{
		
			ArrayList employeelist =trainingForm.getEmployeeList();

			ArrayList allemployeeList = new ArrayList();
			for (int i = 0; i < employeelist.size(); i++) {
				EmployeeDTO employeeDTO2 = (EmployeeDTO) employeelist.get(i);
				if ((employeeDTO2 != null && employeeDTO2.getEmployeeNumber() == null)
						|| (employeeDTO2 != null && employeeDTO2.getEmployeeNumber().equals(
								""))) {
				} else {

					allemployeeList.add(employeeDTO2);
				}
			}
			if(request.getParameter("employeeId")!=null){
				trainingBD=new TrainingBD();
				ArrayList employeeList=null;
				employeeDTO=new EmployeeDTO();
				String employeeId=request.getParameter("employeeId");
				
				employeeDTO= trainingBD.getEmployeeDetails(employeeId);
				StringBuffer sbEmployee = new StringBuffer();
				if(employeeDTO.getEmployeeName()!=null){
				sbEmployee.append(employeeDTO.getEmployeeName()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getEmployeeDesignation()!=null){
				sbEmployee.append(employeeDTO.getEmployeeDesignation()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getDateOfJoining()!=null){
				sbEmployee.append(employeeDTO.getDateOfJoining()+"|");
				}else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getPlaceOfPosting()!=null){
					sbEmployee.append(employeeDTO.getPlaceOfPosting()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getOfficalAddress()!=null){
					sbEmployee.append(employeeDTO.getOfficalAddress()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getResidencalAddress()!=null){
					sbEmployee.append(employeeDTO.getResidencalAddress()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				if(employeeDTO.getContactNumber()!=null){
					sbEmployee.append(employeeDTO.getContactNumber()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				
				if(employeeDTO.getEmail()!=null){
					sbEmployee.append(employeeDTO.getEmail()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				
				if(employeeDTO.getEmpClass()!=null){
					sbEmployee.append(employeeDTO.getEmpClass()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}
				
				if(employeeDTO.getEmpSalary()!=null){
					sbEmployee.append(employeeDTO.getEmpSalary()+"|");
				}
				else{
					sbEmployee.append(" "+"|");
				}				

				logger.debug("Just to check the value "+sbEmployee.toString());
				out.print(sbEmployee.toString());
				return null;
			}else if(strActionType.equalsIgnoreCase("New")){
				logger.debug("nEW ACCTION :");
				session.removeAttribute("allemployeeList");
				session.removeAttribute("TrainingDTO");
			   Forward_Page="newPage";	
			}else if(strActionType.equalsIgnoreCase("view")){
				//logger.debug("nEW ACCTION :");
				trainingDTO=trainingForm.getTrainingDTO();
				session.setAttribute("allemployeeList", allemployeeList);
				session.setAttribute("TrainingDTO", trainingDTO);
			   Forward_Page="editPage";	
			}
			else if(strActionType.equalsIgnoreCase("Edit")){
				Forward_Page="newPage";
			}
			else if(strActionType.equalsIgnoreCase("Save")){
				trainingBD=new TrainingBD();
				rule=new EmpMgmtRule();
				ArrayList errorList=null;
				ArrayList submitEmployeeList=null;
				trainingDTO=(TrainingDTO)session.getAttribute("TrainingDTO");
				submitEmployeeList=(ArrayList)session.getAttribute("allemployeeList");
				String userid="";
				if(session.getAttribute("UserId")!=null){
					userid=(String)session.getAttribute("UserId");
				}
				String trainingnumber=trainingBD.submitTrainingDetails(trainingDTO,submitEmployeeList,userid);
				if(trainingnumber.trim().length()>0){
					session.setAttribute("trainingnumber", trainingnumber);
					Forward_Page="training_submit";
					//session.removeAttribute("trainingDTO");
					//session.removeAttribute("employeeList");
				}else{
					errorList=new ArrayList();
					errorList.add("<font color=red>Training has not been Submitted Success Fully</font>");
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_FLAG, "true");
					request.setAttribute(EmpmgmtConstant.EMP_MGMT_ERROR_LIST,errorList);
					Forward_Page="newPage";
				}
				
			}
			}catch(Exception e){
			//logger.debug(e);
		}
			logger.debug("forward page>>>>>"+Forward_Page);
		return mapping.findForward(Forward_Page);
	}
}

/**
 * PayrollAction.java
 */
package com.wipro.igrs.hrpayroll.payroll.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.estamping.action.EStampAction;
import com.wipro.igrs.hrpayroll.payroll.bd.PayrollBD;
import com.wipro.igrs.hrpayroll.payroll.dto.PayrollDTO;
import com.wipro.igrs.hrpayroll.payroll.form.PayrollForm;

/**
 * @author pranalk
 *
 */
public class PayrollAction extends BaseAction 
{
	private Logger logger = 
		(Logger) Logger.getLogger(PayrollAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception {

		logger.info("In Payroll Action Class");
	 session = request.getSession();
		
		
		String forwardpage="payrollnew";
		ArrayList compIdList = null;
		ArrayList oprIdList = null;
		ArrayList empSalList = null;
		boolean boo = false;
		try{
		PayrollForm payrollForm=(PayrollForm)form;
		String actiontype=payrollForm.getActionType();
		PayrollDTO payrollDTO=payrollForm.getPayrollDTO();
		String fwdName = (String)request.getParameter("pageName");
		String param = (String)request.getParameter("delparam");
		logger.info("Employee Salary components fwdName =--------->"+fwdName);
		logger.info("Employee Salary components pageTitle =--------->"+payrollForm.getPageTitle());
		logger.info("Employee Salary components actiontype =--------->"+payrollForm.getActionType());
		
		PayrollBD payrollBD = new PayrollBD();
		if(actiontype==null){
			session.removeAttribute("payrollcomponentsdto");
			forwardpage="payrollnew";
		}else if(actiontype!=null && actiontype.equalsIgnoreCase("parollcalc")){
			
			
			PayrollDTO payrollcomponentsdto=payrollBD.calculatePayroll(payrollDTO);
			payrollcomponentsdto.setEmployeeid(payrollDTO.getEmployeeid());
			if(payrollcomponentsdto.getErrormsg()==null || (payrollcomponentsdto.getErrormsg()!=null) ){
				logger.info("Inside the Salary loop and forwarding to correct page");
			session.setAttribute("payrollcomponentsdto",payrollcomponentsdto);
			forwardpage="payrollcomponents";
			}else{
				session.setAttribute("payrollcomponentsdto",payrollcomponentsdto);
				forwardpage="payrollnew";
			}
			
		}
		//------------------ code is added for adding payroll Formula
		
		if(fwdName != null && fwdName.equalsIgnoreCase("empSal"))
		{
		    logger.info("Employee Salary components");
		    compIdList = payrollBD.getcompIdList();
		    oprIdList = payrollBD.getOprIdList();
		    payrollDTO.setCompIdList(compIdList);
		    payrollDTO.setOprIdList(oprIdList);
		    payrollForm.setPayrollDTO(payrollDTO);		   
		    logger.info("compIdList=========>"+compIdList);
		    forwardpage = "empSalComp";
		    
		}
		
		if(payrollForm.getPageTitle() != null)
		if(payrollForm.getPageTitle().equalsIgnoreCase("empSalComp"))
		{
		   
		    logger.info("Employee Salary components before inserting");
		    boo = payrollBD.addEmpSalComp(payrollForm);
		    logger.info(" Payroll Action Employee Salary components boo--------->"+boo);
		    forwardpage = "addSuccess";
		    
		}
		if(fwdName != null && fwdName.equalsIgnoreCase("empSalView"))
		{
		    logger.info("Employee Salary components View");
		    empSalList = payrollBD.getEmpSalComp();
		    payrollForm.setEmpSalViewRes(empSalList);
		    forwardpage = "empSalView";
		    
		}
		
		if(fwdName != null && fwdName.equalsIgnoreCase("delCompDet"))
		{
		    logger.info("Employee Salary components delete"); 
		    //payrollForm.setCompId(compId);
		    boo = payrollBD.delCompDet(payrollForm.getCompId());
		    if(boo)
		    empSalList = payrollBD.getEmpSalComp();
		    payrollForm.setEmpSalViewRes(empSalList);
		    forwardpage = "empSalView";
		    
		}
		session.setAttribute("empSalViewList", payrollForm.getEmpSalViewRes());
		session.setAttribute("payForm", payrollForm);
		}
		catch (Exception e) {
		    logger.info("Exception in Payroll Action");
			forwardpage="payrollnew";
			logger.error(e);
		}
		logger.info("forwardpage------->"+forwardpage);
		return mapping.findForward(forwardpage);
	}

}

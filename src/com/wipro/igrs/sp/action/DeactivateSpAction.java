package com.wipro.igrs.sp.action;
import com.wipro.igrs.baseaction.action.BaseAction;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.common.ServiceProviderConstants;
import com.wipro.igrs.sp.bd.ServiceProviderBD;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.sp.form.ServiceProviderForm;
import com.wipro.igrs.sp.rule.ServiceProviderRule;

public class DeactivateSpAction extends BaseAction {

	ServiceProviderBD serviceProviderBD = null;
	ServiceProviderDTO providerDTO = null;
	ServiceProviderRule serviceProviderRule = null;
	ArrayList errorlist = null;
	String userid=new String();
	String spuname=new String();
	String forwardpage;

	private Logger logger = 
		(Logger) Logger.getLogger(DeactivateSpAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {

		String actionname = request.getParameter("name");
		
		if (actionname != null && actionname.equals("Deactivate")) {
			try {
				forwardpage = ServiceProviderConstants.SPLICENCEDEACTIVATE;
			} catch (Exception e) {
				logger.error("Exception in execute() ServiceProviderAction"+ e);
			}
		}
		//forwardpage = ServiceProviderConstants.SPLICENCEDEACTIVATE;
		ServiceProviderForm providerForm = (ServiceProviderForm) form;
		
		  if (form != null) {
			try
			{
				String strActionType = providerForm.getActionType();
				if(strActionType.equals("LicencedUser"))
				{
					forwardpage=executeLicencedUserList(mapping,providerForm,request,response,session);
				}
				else if(strActionType.equals("Deactivate"))
				{
					forwardpage=executedeactivateUser(mapping,providerForm,request,response,session);	
				}
				
				if (actionname != null && actionname.equals("Deactivate")) {
					try {
						forwardpage =ServiceProviderConstants.SPLICENCEDEACTIVATE;
					} 
					catch (Exception e) {
						logger.error("Exception in execute() ServiceProviderAction"
								+ e);
					}
				}
				
	}
	catch(Exception e)
	{
	logger.info("Exception in execute() ServiceProviderAction for Deactivate"+ e);
	}
			
	}
		  logger.error("FORWARD PAGE"+forwardpage);
		return mapping.findForward(forwardpage);
	}
	
	private String executeLicencedUserList(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
			ArrayList deacLicencedUserDetailsList = null;
			try {
				serviceProviderBD=new ServiceProviderBD();
				providerDTO = providerForm.getProviderDTO();
				serviceProviderRule = new ServiceProviderRule();
				 deacLicencedUserDetailsList = serviceProviderBD.getdeacLicencedUserDetailsList(providerDTO, request);
			
			if(deacLicencedUserDetailsList.isEmpty()) {
				errorlist = new ArrayList();
				errorlist.add("<font color=red>No Results For This UserName</font>");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_FLAG,"true");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_LIST,errorlist);
				forwardpage = ServiceProviderConstants.SPLICENCEDEACTIVATE;
				}
				else
				{request.setAttribute("deacLicencedUserDetailsList",deacLicencedUserDetailsList);
				forwardpage =ServiceProviderConstants.SPLICENCEDEACTIVATE;	
				}
			} catch (Exception e) {
				logger.error(e);
			}
		return ServiceProviderConstants.SPLICENCEDEACTIVATE;
	}
	
	private String executedeactivateUser(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
	{
		String forwardpage=null;
		try
		{
			providerDTO=providerForm.getProviderDTO();
			String drcomments=providerDTO.getSpOtherInformation();
			providerDTO.setSpOtherInformation(providerDTO.getSpOtherInformation());
			providerForm.setProviderDTO(providerDTO);
			serviceProviderBD=new ServiceProviderBD();
			providerDTO=providerForm.getProviderDTO();
			
			String[] deacUser=new String[4];
			deacUser[0]=ServiceProviderConstants.RU_ROLE_GROUP;
			deacUser[1]=ServiceProviderConstants.REGISTERED_USER;
			deacUser[2]=providerDTO.getSpusername();
			deacUser[3]=providerDTO.getSpusername();
			
			boolean updtlicenceduserdetails =serviceProviderBD.deacUser(providerDTO,deacUser);
			if(updtlicenceduserdetails==true)
			{forwardpage=ServiceProviderConstants.SPACNTDEACTIVATESUCCESS;}
			else
			{return ServiceProviderConstants.SPLICENCEDEACTIVATE;}
		}
		catch(Exception e)
		{logger.error(e);}
		return forwardpage;
	
	}
	
}
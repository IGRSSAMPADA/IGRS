package com.wipro.igrs.applicationlog.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.applicationlog.bd.ApplicationLogBD;
import com.wipro.igrs.applicationlog.dao.ApplicationLogDAO;
import com.wipro.igrs.applicationlog.dto.ApplicationLogDTO;
import com.wipro.igrs.applicationlog.form.ApplicationLogForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.useracctmgmt.constant.CommonConstant;


public class ApplicationLogAction extends BaseAction {
    
	private SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");
	String frwdjsp = new String();
	ApplicationLogBD appBD = new ApplicationLogBD();
    
    public ApplicationLogAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	 
    	
    	String timestamp = (String) session.getAttribute("timestamp");
    	IGRSCommon save =  new IGRSCommon();
    	
    	try{
    		
    		ApplicationLogForm bean = (ApplicationLogForm)form;
    		String appDate = null;
    		String activityid = request.getParameter("ACTID");
    		String language = (String)session.getAttribute("languageLocale");
    		String mode = request.getParameter("mode");
    		String userId = (String) session.getAttribute("UserId");
    		String frdName=(String)request.getParameter("fwdName");
			
    		
    		bean.setLanguage(language);
    		if(request.getParameter("fwdName")!=null){
				session.setAttribute("FwdName", frdName);
				bean.setFwdName(frdName);
				}
    		
    		/*if(mode != null && mode.equalsIgnoreCase("new")){
    			bean.setDate("");
    		}*/
    	    		
    		/*if(bean.getFromdate() != null && bean.getFromdate() != "") {
    			Date date = sourceDateFormat.parse(bean.getFromdate());                   //added by satbir kumar
    			appDate = targetDateFormat.format(date);
    		}*/
    		
    		if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("app")&& mode != null && mode.equalsIgnoreCase("new")){
    			
    			
    			bean.getAppformdto().setSearchFlag("");
    		//String activityid1 =CommonConstant.IGRS_VIEW_APPLICATION_LOG;
    			bean.setActionName("");
    			save.saveactivitylog(userId, activityid);
    			bean.setLogic("0");
    			frwdjsp ="searchApplicationLog";
    			
    		}
    		
    		
    		if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("trans")&& mode != null && mode.equalsIgnoreCase("new"))
    		{
    			bean.getAppformdto().setSearchFlag("");
    			bean.setActionName("");
    			save.saveactivitylog(userId, activityid);
    			bean.setLogic("0");
    			frwdjsp ="searchTransactionLog";
    		}
            	
    			
    		
    		if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("app")){
    			
    			if ("SEARCH".equalsIgnoreCase(bean
						.getActionName()))
    			{
    			String durationfrom=bean.getFromdate();
    			String durationto = bean.getTodate();
    			
    			Date dateFrom = sourceDateFormat.parse(durationfrom);
    			 durationfrom = targetDateFormat.format(dateFrom);
    			bean.getAppformdto().setSearchFlag("durSearch");
    			 
    			 Date dateTo = sourceDateFormat.parse(durationto);
    			 durationto = targetDateFormat.format(dateTo);
    			 
    			 List list = (List)appBD.searchByDate(durationfrom,durationto,userId,language);
    			 request.setAttribute("ApplicationLogResultList", list);
    			
    			 if(list.size()>0)
     			{
     			frwdjsp ="searchApplicationLog";
     			bean.setLogic("1");
     			
     			}
    			
    			 else
    			 {
    				 frwdjsp ="nologsfound";
    			 }
      			bean.setActionName(null);

    			}
    			
    			
    		}
    		
    		
    		if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("app")){
    			
    			if ("DELETELOG".equalsIgnoreCase(bean
						.getActionName()))
    			{
    				String durationfrom=bean.getFromdate();
					String durationto = bean.getTodate();
					
					
					/*Date dateFrom = sourceDateFormat.parse(durationfrom);
	    			 durationfrom = targetDateFormat.format(dateFrom);
	    			
	    			 Date dateTo = sourceDateFormat.parse(durationto);
	    			 durationto = targetDateFormat.format(dateTo);
					*/
					
					boolean delete = appBD.deleteactivitylog(durationfrom,durationto,userId);
					
					if(delete== true)
					{
						frwdjsp ="logsdeletesuccess";
						bean.setActionName(null);
						
					}
    			
					else
					{
						
					}
    			}
    			
    			
    		
    		}
    		if(bean.getActionName()!=null && bean.getActionName().equalsIgnoreCase("refresh")){
    			bean.getAppformdto().setSearchFlag("");
    			bean.setLogic("0");
    			
    			frwdjsp ="searchApplicationLog";
    		}
    		
    		
    		if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("trans")){
    			
    			if ("DELETELOG".equalsIgnoreCase(bean
						.getActionName()))
    			{
    				String durationfrom=bean.getFromdate();
					String durationto = bean.getTodate();
					
					
					/*Date dateFrom = sourceDateFormat.parse(durationfrom);
	    			 durationfrom = targetDateFormat.format(dateFrom);
	    			
	    			 Date dateTo = sourceDateFormat.parse(durationto);
	    			 durationto = targetDateFormat.format(dateTo);
					*/
					
					boolean delete = appBD.deletetxnlog(durationfrom,durationto,userId);
					
					if(delete== true)
					{
						
						frwdjsp ="logsdeletesuccess";
					}
    			
					else
					{
						
					}
    			}
    			
    			
    		
    		}
    		
    		
    if(bean.getFwdName()!=null && bean.getFwdName().equalsIgnoreCase("trans")){
    			
    			/*request.setAttribute("TransactionLogResultList", null);
    			if(request.getParameter("actionName") != null)
    			{
    				if(request.getParameter("actionName").equalsIgnoreCase("SEARCH"))
    				{
    					bean.setActionName("SEARCH");
    				}
    			}*/
    			if ("SEARCH".equalsIgnoreCase(bean
						.getActionName()))
    			{
    				String durationfrom=bean.getFromdate();
        			String durationto = bean.getTodate();
        		 
        		Date dateFrom = sourceDateFormat.parse(durationfrom);
       			 durationfrom = targetDateFormat.format(dateFrom);
       			
       			 Date dateTo = sourceDateFormat.parse(durationto);
       			 durationto = targetDateFormat.format(dateTo);
    			
    			
    			List translist = (List)appBD.transSearchByDate(durationfrom,durationto,userId,language);
    			
    			request.setAttribute("TransactionLogResultList", translist);
    			//return mapping.findForward("searchApplicationLog");
    			//bean.setFromdate("");
    			//bean.setTodate("");
    			
    			if(translist.size()>0)
    			{
    				
    				
    				String rolegroup = appBD.getrolegroup(userId);
    				
    				if(rolegroup.equalsIgnoreCase("IGRSAdminRoleGroup"))
    				{
    					
    					bean.setLogic("1");
    					bean.setActionName(null);
    					frwdjsp ="searchTransactionLog";
        			
    				}
    				
    				else
    				{
    					
    					bean.setLogic("0");
    					bean.setActionName(null);
    					frwdjsp ="searchTransactionLog";
        				
    				}
    				
    				
    			}
    				else
    				{
    					frwdjsp ="nologsfound";
        				
    				}
    				
    				
    				
         			
    			
    			
    		}
    		
    			
    		
    		}
    		return mapping.findForward(frwdjsp);
    		
    	   
	    }
	    catch(Exception e)
	    {
		   e.printStackTrace();
		   return null;
	    }
    }
}
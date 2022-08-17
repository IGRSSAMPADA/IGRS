/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileAction.java
 * Author		:	Rafiq Rahiman.T 
 * Date			:   18/03/2008
 */
package com.wipro.igrs.useracctmgmt.action;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.useracctmgmt.bd.UserProfileBD;
import com.wipro.igrs.useracctmgmt.dto.UserAccntUnlockDTO;
import com.wipro.igrs.useracctmgmt.form.UserProfileForm;
import com.wipro.igrs.common.IGRSCommon;

public class UserUnlockAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
				
		
		UserProfileBD userBD = new UserProfileBD();
		UserProfileForm eForm = (UserProfileForm) form;
		UserAccntUnlockDTO uadto = eForm.getUuDTO();
		ActionMessages messages 	= new ActionMessages();
		String locale="";
		Locale currentLocale=new Locale(locale);
		if(session.getAttribute("org.apache.struts.action.LOCALE")!=null){
				currentLocale=(Locale)session.getAttribute("org.apache.struts.action.LOCALE");
				locale=currentLocale.toString();
				
			}
		String roleId 				= (String)session.getAttribute("role");
   		String funId 				= (String)session.getAttribute("functionId");
		String loggedUserId				= (String)session.getAttribute("UserId");  
		String activityid               = request.getParameter("ACTID");
		
		String forwardJsp ="success";
		String param="";
		IGRSCommon save =  new IGRSCommon();
		try
		{
						
			if(request.getParameter("param")!=null)		
				{
				param = (String) request.getParameter("param");
				}
			
			
			if(param.equalsIgnoreCase("view"))
				{
				save.saveactivitylog(loggedUserId, activityid);
				eForm.setUuDTO(new UserAccntUnlockDTO());
				forwardJsp="success";
				}
			if(param.equalsIgnoreCase("searchvalidid"))
				{
					ArrayList uNameList=userBD.getLockedUser(uadto,locale);
					if (uNameList.size()==0)
					{
						messages.add("MSG", new ActionMessage("no.user.found"));
			            saveMessages(request, messages);
			            uadto.setUserLockedFlag("nodata");
			            eForm.setUuDTO(uadto);
			            
					}
					else
					{
						uadto.setUserDtlList(uNameList);
						eForm.setUuDTO(uadto);
						
					}
					
				}
			
					
			if(param.equalsIgnoreCase("unlock"))
			{
				if(request.getParameter("userId")!=null)
				{
					String userID = (String)request.getParameter("userId");
					boolean unlockFlg = userBD.unLockUser(userID,loggedUserId);
					if(unlockFlg)
					{
						
						messages.add("MSG", new ActionMessage("user.unlocked.successfully"));
			            saveMessages(request, messages);
			            uadto.setUserLockedFlag("success");
			            uadto.setUserDtlList(new ArrayList());
						
			            eForm.setUuDTO(uadto);
					}
					else
					{
						messages.add("MSG", new ActionMessage("user.unlocked.failed"));
			            saveMessages(request, messages);
			            uadto.setUserLockedFlag("fail");
			            eForm.setUuDTO(uadto);
					}
				}
				
			}
			
		}
		catch(Exception e)
		{
			forwardJsp="failure";
		}
		
		
		
		
		
		
		
		return mapping.findForward(forwardJsp);
		
			

		
		
	
	}

}

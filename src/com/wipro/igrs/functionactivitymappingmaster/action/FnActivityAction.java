/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FnActivityAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.functionactivitymappingmaster.action;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.functionactivitymappingmaster.bd.FnActivityBD;
import com.wipro.igrs.functionactivitymappingmaster.dto.FnActivityDTO;
import com.wipro.igrs.functionactivitymappingmaster.form.FnActivityForm;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;

public class FnActivityAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
		
		
		//LoggerMsg.info("we are in FnActivityAction");
		String target = "success";
		String exit = "exit";
		/* Populate the fnactivityForm form */
		FnActivityForm fnactivityForm = (FnActivityForm)form;
//		FnActivityDTO dto = new FnActivityDTO();
		FnActivityDTO dto= fnactivityForm.getDto();
		FnActivityBD fnbd=new FnActivityBD();
		//ArrayList functionList = fnbd.getFunctionList();
		ArrayList roleList = fnbd.getRoleList();
		fnactivityForm.setRoleList(roleList);
		//ArrayList activityList = fnbd.getActivityList();
		//ArrayList moduleList = fnbd.getModuleList();
		//ArrayList submoduleList = fnbd.getSubmoduleList();
		//ArrayList fnactivityList = fnbd.getFnactivityList();
		request.setAttribute("list", null);
		String frdName=request.getParameter("fwdName");
		String actionname=fnactivityForm.getActionname();
		System.out.println(actionname);
		
		String roleid=fnactivityForm.getRoleId();
		System.out.println(roleid);
		String submid=fnactivityForm.getSubmoduleId();
		String fxnid=fnactivityForm.getFunctionId();
		String modid=fnactivityForm.getModuleId();
		
		
		
		
		
		//
		String chosenModule ="";	
		if(frdName!=null && frdName.equalsIgnoreCase("module"))
		{	
			
			
			dto.setRoleId("");
			dto.setModuleId("");
			dto.setModuleName("");
			dto.setSubmoduleId("");
			dto.setSubModuleName("");
			dto.setFunctionId("");
			dto.setFunctionName("");
			dto.setActivityId("");
			dto.setActivityName("");
			fnactivityForm.setRoleId("");
			fnactivityForm.setModuleId("");
			fnactivityForm.setSubmoduleId("");
			fnactivityForm.setFunctionId("");
			fnactivityForm.setActivityId("");
			fnactivityForm.setDisclamer("0");
			fnactivityForm.setActivityList(null);
			
			
			ArrayList modlist = new ArrayList();
			modlist = fnbd.getModuleList();
//			dto.setModuleList(modlist);
			fnactivityForm.setModuleList(modlist);
			//chosenModule = fnactivityForm.getDto().getModuleId();
			
			return mapping.findForward(target);
			
			
		}
		else if("refresh".equalsIgnoreCase(fnactivityForm.getActionname()))
		{
			dto.setModuleId("");
			dto.setModuleName("");
			dto.setSubmoduleId("");
			dto.setSubModuleName("");
			dto.setFunctionId("");
			dto.setFunctionName("");
			dto.setActivityId("");
			dto.setActivityName("");
			fnactivityForm.setModuleId("");
			fnactivityForm.setSubmoduleId("");
			fnactivityForm.setFunctionId("");
			fnactivityForm.setActivityId("");
			fnactivityForm.setActivityList(null);
			fnactivityForm.setActionname("");
			return mapping.findForward(target);
			
		}
		
		else if(frdName!=null && frdName.equalsIgnoreCase("submodule")){
			chosenModule =request.getParameter("Mod");
			ArrayList subModList = new ArrayList();
				if(chosenModule!=null && chosenModule.equals("")!=true ){		
					subModList = fnbd.getSubModuleMasterList(chosenModule);
					fnactivityForm.setSubmoduleList(subModList);
					//dto.setSubmoduleList(subModList);
			}
			return mapping.findForward(target);
		}
		
		
		
		else if("submitmappedactivites".equalsIgnoreCase(fnactivityForm.getActionname()))
		{
			System.out.println("in action");
			ArrayList propDetls = new ArrayList();
			
			boolean inserted=false;
			boolean deleted = false;
			if(fnactivityForm.getDto().getCheckedactivitylist() != null)
			{
				System.out.println("in checked");
				String[] checkedid  = fnactivityForm.getDto().getCheckedactivitylist().split(",");
				
				inserted = fnbd.getcheckedinsertedid(checkedid,roleid,modid,submid,fxnid);
				
				if(inserted== true)
				{
					System.out.println("total success");
				}
			
				else
				{
					System.out.println("total failure");
				}
			}
			 if(fnactivityForm.getDto().getUncheckedactivitylist() != null)
				{
				System.out.println("in unchecked");
					String[] uncheckedid  = fnactivityForm.getDto().getUncheckedactivitylist().split(",");
					deleted = fnbd.getdeleteuncheckedid(uncheckedid,roleid,modid,submid,fxnid);
					
					if(deleted== true)
					{
						System.out.println("total success");
					}
				
					else
					{
						System.out.println("total failure");
					}
					
		}
			 fnactivityForm.setActionname("");
			 
			 return mapping.findForward(exit);
		}
			
		else if(frdName!=null && frdName.equalsIgnoreCase("functn")){
			String chosenSubModuleID;
			chosenSubModuleID= request.getParameter("SubMod");
			ArrayList funcList = new ArrayList();
				if(chosenSubModuleID!=null && chosenSubModuleID.equals("")!=true ){		
					funcList = fnbd.getFunctionMasterList(chosenSubModuleID);
					fnactivityForm.setFunctionList(funcList);
					//dto.setFunctionList(funcList);
					
			}
			return mapping.findForward(target);
		}
		else if(frdName!=null && frdName.equalsIgnoreCase("activity")){
			String chosenFuncID;
			chosenFuncID =request.getParameter("Functn");
			
			ArrayList actList = new ArrayList();
				if(chosenFuncID!=null && chosenFuncID.equals("")!=true ){		
					actList = fnbd.getActivityMasterList(chosenFuncID,fnactivityForm.getRoleId());
					if(actList.size()!=0)
					{
					fnactivityForm.setActivityList(actList);
					request.setAttribute("list", actList);
					//dto.setActivityList(actList);
					//System.out.println("Leaving activity module.....");
					fnactivityForm.setDisclamer("1");
					}
			}
			return mapping.findForward(target);
		}			
		
		else {
			
			String selectedActVal[] = request.getParameterValues("activityId");
//			for(int i=0;i<selectedActVal.length;i++){
//				System.out.println("act val:" + i +" " + selectedActVal[i]);
//			}
			fnactivityForm.setSelectedActVal(selectedActVal);
			
			
			
					String pageName=request.getParameter("pageName");
					//System.out.println("page name: " + pageName);
		            if(pageName!=null){
			        if(pageName.equalsIgnoreCase("activecreate"))		        	
			            {
			        	
			        	/*Add Functionactivitymapping master*/
			        	//System.out.println("Before Adding data....");
			        	
			        	fnbd.addFnactivitymappingmaster(fnactivityForm);
			            }
			        else{
			        	         /*Update Functionactivitymapping master*/
			        	fnbd.updateFnactivitymappingmaster(fnactivityForm);
			            }
		            }		            
		}
		            /* Parameter functionId */
//		    		String fnid = request.getParameter("functionId");
//		    		
//		    		if(fnid!=null)
//		    		{
//		    			dto = fnbd.getFunctionId(fnid);
//		    		}
		    		
		    		
		    		
		         
	                 /*Setting master List items*/
//		                dto.setFunctionList(functionList);
//		                dto.setActivityList(activityList);
		                //dto.setRoleList(roleList);
//		                dto.setModuleList(moduleList);
//		                dto.setSubmoduleList(submoduleList);
		                //dto.setFnactivityList(fnactivityList);
		                fnactivityForm.setDto(dto);
		                 /* Set the sessions */
		                
		                session.setAttribute("fnactivityList",fnactivityForm.getDto().getFnactivityList());
		                session.setAttribute("URolegroupForm", fnactivityForm);
		            
		                
		
		return mapping.findForward(target);	
	}
}

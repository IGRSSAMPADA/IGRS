/******************************************************************************
 * File Name     : ModulesAction.java
 * Author        : Imran Shaik
 * Date          : 2006/02/29
 * Description   : Represents the Master Action for Master Module(screens)
 *
 * Modification Log:
 *  Ver.No.  Date        Author                    Modification
 *  0.0a     2006/02/29  Imran Shaik               Initial Version
 *  1.0      2008/08/27  Sayed Taha(Wipro-Egypt)
 *****************************************************************************/
package com.wipro.igrs.deedmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.deedmaster.bd.ModuleMasterBD;
import com.wipro.igrs.deedmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.deedmaster.form.ModuleMasterForm;

public class ModulesAction extends BaseAction {

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session){
//System.out.println("_________Modules Action_____________");
		ModuleMasterForm moduleMasterForm = (ModuleMasterForm)form; 
		System.out.println("moduleMasterForm.getId()--<>"+moduleMasterForm.getId());
		ModuleMasterBD moduleMasterBD = new ModuleMasterBD();
		String target = null;
		String linkParam = null;
		ModuleMasterDTO dto = new ModuleMasterDTO();
		ArrayList moduleList = null;
		if(request.getParameter("view") != null){
			target = request.getParameter("view").toString();
			moduleList = moduleMasterBD.getModulesDetails(target);
			//System.out.println(moduleList);
			dto.setModuleList(moduleList);
		}
		if(request.getParameter("list") != null){
			target = request.getParameter("list").toString();
			moduleList = moduleMasterBD.getModulesList(target);
			dto.setModuleList(moduleList);
			if(target.equalsIgnoreCase("exemption")){
				moduleList = moduleMasterBD.getModulesList("instrument");
				dto.setSubList(moduleList);
			}
		}
		if(request.getParameter("viewList") != null){
			target = "deedview";
			String deedId=moduleMasterForm.getId();
			ArrayList viewList = moduleMasterBD.getModulesDetails(target);
			moduleMasterForm.setDeedList(viewList);
			if(deedId!=null){		
				moduleList = moduleMasterBD.getDeedIdDetails(deedId);
				dto.setModuleList(moduleList);
				//moduleMasterForm.setmDeedList(moduleList);
				
			}
		}
		
		
		
		// Pe Edit by : Sayed Taha wipro-egypt
		if(request.getAttribute("editDeed") != null){
			target = "deedid";
			String deedId=moduleMasterForm.getId();
			if(deedId!=null){		
				ModuleMasterForm deedForm=(ModuleMasterForm)form;
				moduleList = moduleMasterBD.getDeedIdDetails(deedId);
				moduleMasterForm=(ModuleMasterForm)moduleList.get(0);
			}
		}
		//  Edit by : Sayed Taha wipro-egypt
		if(moduleMasterForm.getPageTitle()!=null){
			ModuleMasterForm deedForm=(ModuleMasterForm)form;
			if(deedForm.getPageTitle().equalsIgnoreCase("deedTypeUpdate") ){
			String params[] = new String[11];
				        String UserId = null;
						if(session.getAttribute("UserId").toString() == null | session.getAttribute("UserId").toString().equalsIgnoreCase(""))
							UserId = "ADMIN";
						else
					    UserId = (String)session.getAttribute("UserId").toString();
						params[0] = deedForm.getName();
						params[1] = deedForm.getDescription();
						params[2] = UserId;
						params[3] = deedForm.getDeedCategory();
						if(deedForm.getDeedreqstr().equals("true")){
							params[4]="Y";
						}else{
							params[4]="N";
						}
						if(deedForm.getDeedlinkpropstr().equals("true")){
							params[5]="Y";
						}else{
							params[5]="N";
						}
						if(deedForm.getDeedRorstr().equals("true")){
							params[6]="Y";
						}else{
							params[6]="N";
						}
			            params[7]=deedForm.getDeedPeriority();
			            if(deedForm.getPropertyValuerequiredstr().equals("true")){
							params[8]="Y";
						}else{
							params[8]="N";
						}
			            if(deedForm.getDutyCelRequiredstr().equals("true")){
							params[9]="Y";
						}else{
							params[9]="N";
						}
			            params[10] = deedForm.getId();
						//check the presence of the name
						if(moduleMasterBD.isDeepTypeExists(params[0])){
							if(moduleMasterBD.getDeedTypeIdByName(params[0]).equals(deedForm.getId())){
							  moduleMasterBD.updatedDeedTypeDetails(params);
							  target = "deedTypeUpdate";
							}else{
							//throw error
							ActionErrors error=new ActionErrors();
							error.add("deednameerror", new ActionError("errors.deedtypenamealreadyexists"));
							saveErrors(request, error);
							target="editDeedType";
						}
						}else{
							moduleMasterBD.updatedDeedTypeDetails(params);
				            target = "deedTypeUpdate";
						}
	
				}
		}
		
		//End Of Edit Deed Type
		
		if(request.getParameter("moduleid") != null){
			linkParam = request.getParameter("moduleid").toString();
			moduleMasterForm = moduleMasterBD.getModuleDetails(linkParam);
				target = "moduleid";
			}else if(request.getParameter("submoduleid") != null){
				linkParam = request.getParameter("submoduleid").toString();
				moduleMasterForm = moduleMasterBD.getSubModuleDetails(linkParam);
				moduleList = moduleMasterBD.getModulesDetails("moduleList");
				dto.setModuleList(moduleList);
				target = "submoduleid";
			}else if(request.getParameter("functionid") != null){
				linkParam = request.getParameter("functionid").toString();
				moduleMasterForm = moduleMasterBD.getFunctionDetails(linkParam);
				moduleList = moduleMasterBD.getModulesDetails("submoduleList");
				dto.setModuleList(moduleList);
				target = "functionid";
			}else if(request.getParameter("clauseid") != null){
				linkParam = request.getParameter("clauseid").toString();
				moduleMasterForm = moduleMasterBD.getSubClauseDetails(linkParam);
				target = "clauseid";
			}else if(request.getParameter("deedid") != null){
				linkParam = request.getParameter("deedid").toString();
				moduleMasterForm = moduleMasterBD.getDeedTypeDetails(linkParam);
				target = "deedid";
			}else if(request.getParameter("instrumentid") != null){
				linkParam = request.getParameter("instrumentid").toString();
				moduleMasterForm = moduleMasterBD.getInstrumentDetails(linkParam);
				moduleList = moduleMasterBD.getModulesDetails("deedList");
				dto.setModuleList(moduleList);
				target = "instrumentid";
			}else if(request.getParameter("exemptionid") != null){
				linkParam = request.getParameter("exemptionid").toString();
				moduleMasterForm = moduleMasterBD.getExemptionDetails(linkParam);
				moduleList = moduleMasterBD.getModulesDetails("deedList");
				dto.setModuleList(moduleList);
				moduleList = moduleMasterBD.getModulesDetails("instrumentList");
				dto.setSubList(moduleList);
				target = "exemptionid";
			}
		
		//System.out.println("target--<>"+target+"path--<>"+request.getContextPath());
		//System.out.println("masterForm getDeedList--<>"+moduleMasterForm.getDeedList());
		
		moduleMasterForm.setDto(dto);
		//System.out.println("masterForm moduleList--<>"+moduleMasterForm.getDto().getModuleList());
		session.setAttribute("moduleList", moduleMasterForm.getDto().getModuleList());
		session.setAttribute("masterForm", moduleMasterForm);
		//request.getSession().setAttribute("masterForm", moduleMasterForm);
		return mapping.findForward(target); 
	}
}

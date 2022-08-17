/******************************************************************************
 * File Name     : ModuleMasterAction.java
 * Author        : Imran Shaik
 * Date          : 2006/02/29
 * Description   : Represents the Common Action for Master Module(screens)
 *
 * Modification Log:
 *  Ver.No.  Date        Author                Modification
 *  0.0a     2006/02/29  Imran Shaik      Initial Version
 *****************************************************************************/
package com.wipro.igrs.igrsmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.igrsmaster.bo.ModuleMasterBO;
import com.wipro.igrs.igrsmaster.form.ModuleMasterForm;

/**
 * @author Imran Shaik
 *
 */
public class ModuleMasterAction extends BaseAction {

	/**
	 * 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session){
		
		String language = (String)session.getAttribute("languageLocale");
		
		
		String UserId = null;
		if(session.getAttribute("UserId").toString() == null | session.getAttribute("UserId").toString().equalsIgnoreCase(""))
			UserId = "ADMIN";
		else
			UserId = session.getAttribute("UserId").toString();
		String target = "update";
		ModuleMasterForm masterForm = (ModuleMasterForm)form; 
		masterForm.setLanguage(language);
		ModuleMasterBO moduleMaster = new ModuleMasterBO();
		if(masterForm.getPageTitle().equalsIgnoreCase("moduleMaster")){
			String params[] = new String[3];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = UserId;
			moduleMaster.addModuleDetails(params);
			target = "module";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("subModuleMaster")){
			String params[] = new String[4];
			params[0] = masterForm.getModulename();
			params[1] = masterForm.getName();
			params[2] = masterForm.getDescription();
			params[3] = UserId;
			moduleMaster.addSubModuleDetails(params);
			target = "submodule";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("functionMaster")){
			String params[] = new String[6];
			params[0] = masterForm.getModulename();
			params[1] = masterForm.getName();
			params[2] = masterForm.getDescription();
			params[3] = moduleMaster.getTrueOrFalse(masterForm.isPayment());
			params[4] = moduleMaster.getTrueOrFalse(masterForm.isIntimation());
			params[5] = UserId;
			moduleMaster.addFunctionDetails(params);
			target = "function";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateModule")){
			String params[] = new String[5];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[3] = UserId;
			params[4] = masterForm.getId();
			moduleMaster.updateModuleDetails(params);
//			target = "updateModule";
			target = "update";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateSubModule")){
			String params[] = new String[6];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[3] = masterForm.getModulename();
			params[4] = UserId;
			params[5] = masterForm.getId();
			moduleMaster.updateSubModuleDetails(params);
//			target = "updateModule";
			target = "update";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateFunction")){
			String params[] = new String[8];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[3] = moduleMaster.getTrueOrFalse(masterForm.isPayment());
			params[4] = moduleMaster.getTrueOrFalse(masterForm.isIntimation());
			params[5] = masterForm.getModulename();
			params[6] = UserId;
			params[7] = masterForm.getId();
			moduleMaster.updateFunctionDetails(params);
//			target = "updateFunction";
			target = "update";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("clauseMaster")){
			String params[] = new String[3];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = UserId;
			moduleMaster.addSubClauseDetails(params);
			target = "clause";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateClause")){
			//String params[] = new String[5];
			String params[] = new String[4];
			//params[0] = masterForm.getName();
			params[0] = masterForm.getDescription();
			params[1] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[2] = UserId;
			params[3] = masterForm.getId();
			moduleMaster.updateSubClauseDetails(params);
//			target = "updateClause";
			target = "update1";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("deedMaster")){	
			String params[] = new String[7];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = UserId;
			params[3] = masterForm.getDeedCategory();
			params[4] = masterForm.getDeedreq();
			params[5] = masterForm.getDeedlinkprop();
			params[6] = masterForm.getDeedRor();		
			moduleMaster.addDeedDetails(params);
			target = "deedlist";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateDeedTypeId")){
				String params[] = new String[8];
				params[0] = masterForm.getName();
				params[1] = masterForm.getDescription();
				params[2] = masterForm.getDeedCategory();
				params[4] = masterForm.getDeedreq();
				params[3] = UserId;
				params[5] =masterForm.getDeedlinkprop();
				params[6] =masterForm.getDeedRor();
				params[7] =masterForm.getId();
				moduleMaster.updatedDeedTypeDetails(params);
				target = "deedtype";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("instrumentMaster")){
			String params[] = new String[4];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = masterForm.getModulename();
			params[3] = UserId;
			moduleMaster.addInstrumentDetails(params);
			target = "instrument";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateInstrument")){
			String params[] = new String[6];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[3] = masterForm.getModulename();
			params[4] = UserId;
			params[5] = masterForm.getId();
			moduleMaster.updateInstrumentDetails(params);
			//target = "updateInstrument";
			target = "update";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("exemptionMaster")){
			String params[] = new String[5];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = masterForm.getSubname();
			params[3] = masterForm.getModulename();
			params[4] = UserId;
			moduleMaster.addExemptionDetails(params);
			target = "exemption";
		}else if(masterForm.getPageTitle().equalsIgnoreCase("updateExemption")){
			String params[] = new String[7];
			params[0] = masterForm.getName();
			params[1] = masterForm.getDescription();
			params[2] = moduleMaster.getTrueOrFalse(masterForm.isStatus());
			params[3] = masterForm.getSubname();
			params[4] = masterForm.getModulename();
			params[5] = UserId;
			params[6] = masterForm.getId();
			moduleMaster.updateExemptionDetails(params);
			//target = "updateExemption";
			target = "update";
		}
		/*ArrayList deedList=moduleMaster.getDeedNameDetails();
		if(deedList!=null && deedList.size()>0){
			masterForm.setDeedList(deedList);
			target = "viewdeed";
		}*/
	//	System.out.println("target--->  "+target);
		return mapping.findForward(target); 
	}
}

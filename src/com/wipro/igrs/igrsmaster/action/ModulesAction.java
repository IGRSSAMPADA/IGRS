/******************************************************************************
 * File Name     : ModulesAction.java
 * Author        : Imran Shaik
 * Date          : 2006/02/29
 * Description   : Represents the Master Action for Master Module(screens)
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

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.action.UserRegistrationAction;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.igrsmaster.bo.ModuleMasterBO;
import com.wipro.igrs.igrsmaster.constant.SubClauseConstant;
import com.wipro.igrs.igrsmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.igrsmaster.form.ModuleMasterForm;

/**
 * @author Imran Shaik
 *
 */
public class ModulesAction extends BaseAction {

	/**
	 * 
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(ModulesAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception{
		ModuleMasterForm moduleMasterForm = (ModuleMasterForm)form; 
		ModuleMasterBO moduleMasterBO = new ModuleMasterBO();
		String target = null;
		String linkParam = null;
		String UserId = "";
		String language= (String)session.getAttribute("languageLocale");
		if(session.getAttribute("UserId").toString() == null | session.getAttribute("UserId").toString().equalsIgnoreCase(""))
			UserId = "ADMIN";
		else
			UserId = session.getAttribute("UserId").toString();
		moduleMasterForm.setLanguage(language);
		ModuleMasterDTO dto = new ModuleMasterDTO();
		ArrayList moduleList = null;
		ArrayList viewPropDetails=null; 
		ArrayList list=new ArrayList();
		if(request.getParameter("view") != null){
			target = request.getParameter("view").toString();
			if(target.equalsIgnoreCase("clauseList"))
			{
				moduleList = moduleMasterBO.getSubclaueList();
			}
			else
			{
				moduleList = moduleMasterBO.getModulesDetails(target);
			}
		
			dto.setModuleList(moduleList);
		}
		if(request.getParameter("list") != null){
			target = request.getParameter("list").toString();
			moduleList = moduleMasterBO.getModulesList(target);
			dto.setModuleList(moduleList);
			if(target.equalsIgnoreCase("exemption")){
				moduleList = moduleMasterBO.getModulesList("instrument");
				dto.setSubList(moduleList);
			}
		}
		
		if(request.getParameter("moduleid") != null){
			linkParam = request.getParameter("moduleid").toString();
			moduleMasterForm = moduleMasterBO.getModuleDetails(linkParam);
				target = "moduleid";
			}else if(request.getParameter("submoduleid") != null){
				linkParam = request.getParameter("submoduleid").toString();
				moduleMasterForm = moduleMasterBO.getSubModuleDetails(linkParam);
				moduleList = moduleMasterBO.getModulesDetails("moduleList");
				dto.setModuleList(moduleList);
				target = "submoduleid";
			}else if(request.getParameter("functionid") != null){
				linkParam = request.getParameter("functionid").toString();
				moduleMasterForm = moduleMasterBO.getFunctionDetails(linkParam);
				moduleList = moduleMasterBO.getModulesDetails("submoduleList");
				dto.setModuleList(moduleList);
				target = "functionid";
 			}else if(request.getParameter("clauseid") != null){
				linkParam = request.getParameter("clauseid").toString();
				moduleMasterForm.setSubId(linkParam);
				moduleMasterForm = moduleMasterBO.getSubClauseDetails(linkParam,language);
				moduleMasterForm.setLanguage(language);
				
				//moduleMasterForm.setViewPropDetails(viewPropDetails);
				//request.setAttribute("viewPropDetails", viewPropDetails);
				moduleMasterForm.setPropertyId(moduleMasterBO.getPropDetails(linkParam));
				viewPropDetails=moduleMasterBO.getSubClauseViewDetails(linkParam,language,moduleMasterForm.getPropertyId());
				moduleMasterForm.setOperatorList(moduleMasterBO.getOperatorList());
				moduleMasterForm.setPropertyTypeList(moduleMasterBO.getPropertyTypeList(language));
				logger.debug("size of prop ArrayList  "+viewPropDetails.size());
				if(viewPropDetails.size() == 0)
				{
					dto.setViewPropDetails(null);
					request.removeAttribute("viewPropDetails");
				}
				else
				{
					dto.setViewPropDetails(viewPropDetails);
					request.setAttribute("viewPropDetails",viewPropDetails );
				}
				
				target = "clauseid";
			}else if(request.getParameter("deedid") != null){
				linkParam = request.getParameter("deedid").toString();
				moduleMasterForm = moduleMasterBO.getDeedTypeDetails(linkParam);
				target = "deedid";
			}else if(request.getParameter("instrumentid") != null){
				linkParam = request.getParameter("instrumentid").toString();
				moduleMasterForm = moduleMasterBO.getInstrumentDetails(linkParam);
				moduleList = moduleMasterBO.getModulesDetails("deedlist");
				dto.setModuleList(moduleList);
				target = "instrumentid";
			}else if(request.getParameter("exemptionid") != null){
				linkParam = request.getParameter("exemptionid").toString();
				moduleMasterForm = moduleMasterBO.getExemptionDetails(linkParam);
				moduleList = moduleMasterBO.getModulesDetails("deedlist");
				dto.setModuleList(moduleList);
				moduleList = moduleMasterBO.getModulesDetails("instrumentList");
				dto.setSubList(moduleList);
				target = "exemptionid";
			}else if(request.getParameter("delete")!= null){   // Added by Simran for deleting a subclause 
				String subId = request.getParameter("delete");
				boolean flag = moduleMasterBO.deleteSubclause(subId);
				logger.debug("subclause deactivated-------->"+flag);
				if(flag)
				{
					dto.setSuccessCheck("Y");
					target = "update1";
				}
					
				else
				{
					dto.setSuccessCheck("N");
					target = "update1";
				}
					
				
			}
			else if(request.getParameter("action")!= null)
			{  
				
			if(request.getParameter("action").equals("populate_propL1"))
			{
				ArrayList propDetails = new ArrayList();
				String prop_Type_Id=moduleMasterForm.getPropertyId().toString();
				logger.debug("^^^^^^^^^^^^^^^"+prop_Type_Id);
				
				if(prop_Type_Id != "" && (!prop_Type_Id.equals(SubClauseConstant.PLOT_PROP_TYPE_ID)))
				{
					logger.debug("if");
					propDetails=moduleMasterBO.getSubClausePropDisplayNew(prop_Type_Id,"hi");
					moduleMasterForm.setPropDetails(propDetails);
					dto.setViewPropDetails(propDetails);
					request.setAttribute("viewPropDetails", propDetails);
				}
				else
				{
					logger.debug("else");
					moduleMasterForm.setPropDetails(null);
					dto.setViewPropDetails(null);
					request.removeAttribute("viewPropDetails");
				}
				moduleMasterForm.setOperatorList(moduleMasterBO.getOperatorList());
				moduleMasterForm.setPropertyTypeList(moduleMasterBO.getPropertyTypeList(language));
				target = "clauseid";	
			}
			else if(request.getParameter("action").equals("update_subclause"))
			{
				String params[] = new String[2];
				params[0] = UserId;
				params[1]=UserId;
				ArrayList list_propids=new ArrayList();
				if(!moduleMasterForm.getPropertyId().equals("1"))
				{
					String[] propDetails=moduleMasterForm.getHdnPropDetails().split(",");
					
					for(int count1=0;count1<propDetails.length;count1++)
					{
						String propids=propDetails[count1];
						String[] pid=propids.split("~");
						ModuleMasterDTO dto1=new ModuleMasterDTO();
						dto1.setChkdPropTypeId(pid[0].toString());
						dto1.setChkdPropTypeL1Id(pid[1].toString());
						dto1.setChkdPropTypeL2Id(pid[2].toString());
						list_propids.add(dto1);
					}
				}
				boolean flag = moduleMasterBO.updateSubClauseDetails(params,moduleMasterForm,list_propids);
				if(flag)
					dto.setSuccessCheck("Y");
				else
					dto.setSuccessCheck("N");
				target="update1";
			}
		}	
			
		
		
		moduleMasterForm.setDto(dto);
		request.setAttribute("viewPropDetails", moduleMasterForm.getDto().getViewPropDetails());
		request.setAttribute("moduleList", moduleMasterForm.getDto().getModuleList());
		request.setAttribute("masterForm", moduleMasterForm);
		return mapping.findForward(target); 
	}
}

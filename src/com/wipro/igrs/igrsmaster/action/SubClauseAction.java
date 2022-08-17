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

public class SubClauseAction extends BaseAction {

	String forwardpage;
	String prop_Type_Id;
	String prop_Type_L1_Id;
	String comp_prop_Type_Id;
	ArrayList operatorList;
	ArrayList propertyTypeList;
	ArrayList addFactorList;
	ArrayList propTypeL1List;
	ArrayList propTypeL2List;
	ArrayList propDetails;
	
	private Logger logger = 
		(Logger) Logger.getLogger(UserRegistrationAction.class);
	
	
		public ActionForward execute(ActionMapping mapping, ActionForm form, 
	            HttpServletRequest request, 
	            HttpServletResponse response,HttpSession session){
			String UserId = null;
			if(session.getAttribute("UserId").toString() == null | session.getAttribute("UserId").toString().equalsIgnoreCase(""))
				UserId = "ADMIN";
			else
				UserId = session.getAttribute("UserId").toString();
			
			String page=request.getParameter("page");
			
			
			ModuleMasterForm masterForm = (ModuleMasterForm)form; 
			ModuleMasterBO moduleMaster = new ModuleMasterBO();
			ModuleMasterDTO dto=new ModuleMasterDTO();
			String language= (String)session.getAttribute("languageLocale");
			masterForm.setLanguage(language);
			if(form!=null)
			{
				if("Create".equals(page))
				{
					masterForm.setActionName("");
					masterForm.setDto(new ModuleMasterDTO());
					forwardpage="createsubclause";
				}
				try
				{
					dto=masterForm.getDto();
					masterForm.getDto().setAddFactorArea(masterForm.getDto().getAddFactorArea());
				    masterForm.getDto().setAddFactorOprndValue(masterForm.getDto().getAddFactorOprndValue());
					masterForm.setOperatorList(moduleMaster.getOperatorList());
					//masterForm.setAddFactorList(moduleMaster.getAddFactorList());
					masterForm.setPropertyTypeList(moduleMaster.getPropertyTypeList("hi"));
			
					if(masterForm.getDto().getPropTypeId()!="")
					{
						masterForm.setPropTypeL1List(moduleMaster.getPropTypeL1List(comp_prop_Type_Id,"hi"));
					}
					
					if(masterForm.getDto().getPropertyTypeId()!="")
					{
					 masterForm.setPropDetails(moduleMaster.getSubClausePropDetails(prop_Type_Id,"hi"));
					}
					if(masterForm.getDto().getPropTypeL1Id()!="")
					{
						masterForm.setPropTypeL2List(moduleMaster.getPropTypeL2List(prop_Type_L1_Id,"hi"));
					}
			
			if(masterForm.getActionName()!=null)
			{
				if(masterForm.getActionName().equals("populate_propL1"))
				{  
					//comp_prop_Type_Id=masterForm.getDto().getPropTypeId() .toString();
					//masterForm.setPropTypeL1List(moduleMaster.getPropTypeL1List((comp_prop_Type_Id),masterForm.getLanguage()));
					logger.debug("^^^^^^^^^^^^^^^"+prop_Type_Id);
					prop_Type_Id=masterForm.getDto().getPropertyTypeId().toString();
					logger.debug("^^^^^^^^^^^^^^^"+prop_Type_Id);
					if(prop_Type_Id != "" && (!prop_Type_Id.equals(SubClauseConstant.PLOT_PROP_TYPE_ID)))
					{
						logger.debug("if");
						propDetails=moduleMaster.getSubClausePropDisplayNew(prop_Type_Id,"hi");
						masterForm.setPropDetails(propDetails);
						request.setAttribute("propDetails", propDetails);
					}
					else
					{
						logger.debug("else");
						masterForm.setPropDetails(null);
						request.removeAttribute("propDetails");
					}
					dto=masterForm.getDto();
					masterForm.setDto(dto);
					forwardpage = "createsubclause";
				}
				else if(masterForm.getActionName().equals("populate_propL2"))
				{   prop_Type_L1_Id=masterForm.getDto().getPropTypeL1Id().toString();
					masterForm.setPropTypeL2List(moduleMaster.getPropTypeL2List(prop_Type_L1_Id,masterForm.getLanguage()));
					dto=masterForm.getDto();
					 prop_Type_Id=masterForm.getDto().getPropertyTypeId().toString();
					 if(prop_Type_Id!="")
					 {
					 propDetails=moduleMaster.getSubClausePropDetails(prop_Type_Id,masterForm.getLanguage());
					request.setAttribute("propDetails", propDetails);
					 }
					masterForm.setDto(dto);
					forwardpage = "createsubclause";
				}
				else if(masterForm.getActionName().equals("checked_MapAll"))
				{
					dto=masterForm.getDto();
					String params[] = new String[2];
					params[0] = UserId;
					params[1]=UserId;
					
					String[] propDetails=masterForm.getHdnPropDetails().split(",");
					int count;
					ArrayList list_propids=new ArrayList();
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
					if(masterForm.getDto().getUserDefinedField()!=null){
							if(masterForm.getDto().getUserDefinedField().equalsIgnoreCase("on")){
								dto.setUserDefinedField("Y");
							}
					}
					else{
						dto.setUserDefinedField("N");	
						}
					if(masterForm.getDto().getNormalVal()!=null){	
							if(masterForm.getDto().getNormalVal().equalsIgnoreCase("on")){
								dto.setNormalVal("Y");
							}
					}else
						{
						dto.setNormalVal("N");	
						}
					if(masterForm.getDto().getAreaBasedDiv()!=null){	
						if(masterForm.getDto().getAreaBasedDiv().equalsIgnoreCase("on")){
							dto.setAreaBasedDiv("Y");
						}
				}else
					{
					dto.setAreaBasedDiv("N");	
					}
					moduleMaster.addSubClauseDetailsNew(params,dto,list_propids);
					forwardpage="successInsert";
				}
				else if(masterForm.getActionName().equals("subclause_submit"))
				{
					dto=masterForm.getDto();
					String params[] = new String[2];
					params[0] = UserId;
					params[1]=UserId;
					ArrayList list_propids=new ArrayList();
					if(!dto.getPropertyTypeId().equals("1"))
					{
						String[] propDetails=masterForm.getHdnPropDetails().split(",");
						
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
					
					/*if(masterForm.getDto().getUserDefinedField()!=null)
					{
						if(masterForm.getDto().getUserDefinedField().equalsIgnoreCase("on"))
						{
						dto.setUserDefinedField("Y");	
						}
					}
						else
						{
						dto.setUserDefinedField("N");	
						}
					if(masterForm.getDto().getNormalVal()!=null)
					{	
						if(masterForm.getDto().getNormalVal().equalsIgnoreCase("on"))
						{
						dto.setNormalVal("Y");
						}
					}
						else
						{
						dto.setNormalVal("N");	
						}
					if(masterForm.getDto().getAreaBasedDiv()!=null){	
						if(masterForm.getDto().getAreaBasedDiv().equalsIgnoreCase("on")){
							dto.setAreaBasedDiv("Y");
						}
				}else
					{
					dto.setAreaBasedDiv("N");	
					}*/
					boolean flag = moduleMaster.addSubClauseDetails(params,dto,list_propids);
					if(flag)
						dto.setSuccessCheck("Y");
					else
						dto.setSuccessCheck("N");
					forwardpage="successInsert";
				}
				
				}
				}
				catch(Exception e){
				logger.error("Exception in execute() SubClauseAction"
								+ e);
				}
			}
			return mapping.findForward(forwardpage);
		}
	}

	
	
	
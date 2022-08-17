package com.wipro.igrs.regcompletion.action;


/**
 * ===========================================================================
 * File           :   CommonAction.java
 * Description    :   Represents the Common Action Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;
import com.wipro.igrs.regcompletion.form.RegInitCompleteForm;
import com.wipro.igrs.regcompletion.rule.RegCompletionRule;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;


public class RegInitCompleteAction extends 
  BaseAction  {
	private  Logger logger = 
		(Logger) Logger.getLogger(RegInitCompleteAction.class);
	
	
	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	String forwardJsp = "";
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) {
		
		//HttpSession session = request.getSession();
		
		RegCompBO regCompBO = new RegCompBO();
		 
		RegCompBD regCompBD = new RegCompBD();
		
		
		RegInitCompleteForm eForm = (RegInitCompleteForm)form;
		String userId = (String) session.getAttribute("UserId");
		//Added By Aruna
		ArrayList regCompDeedList;
		
		String param = request.getParameter("param") ;
		logger.debug("param:-"+param);
		
		if("regSearch".equals(param)) {
			RegInitCompleteDTO dto = new RegInitCompleteDTO();
			eForm.setFormName("");
			eForm.setActionName("");
			eForm.setRegDTO(dto);
			// Added By Aruna
			eForm.setCheckRegNo("");
			forwardJsp = "page1";
		}
		
		
		/*if("listPage".equals(param)) {
			RegInitCompleteDTO dto = new RegInitCompleteDTO();
			eForm.setFormName("");
			eForm.setActionName("");
			eForm.setRegDTO(dto);
			forwardJsp = "deedViewList";
		}*/
		if("success".equals(param)) {
			RegInitCompleteDTO dto = new RegInitCompleteDTO();
			eForm.setFormName("");
			eForm.setActionName("");
			eForm.setRegDTO(dto);
			ArrayList complList = regCompBD.getComplList();
			eForm.setComplList(complList);
			forwardJsp = "compliance";
		}
		if(eForm != null) {
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			logger.debug("formName:-"+formName);
			logger.debug("actionName:-"+actionName);
			
			RegInitCompleteDTO dto = eForm.getRegDTO();
			/// Start Search Form 
			if(RegCompConstant.REGCOMP_SEARCH_FORM.equals(formName)){
				if(RegCompConstant.SEARCH_ACTION.equals(actionName)) {
					
					String regNumber = dto.getRegNumber();
					
					RegCompletionRule rule = new RegCompletionRule();
					ArrayList errorList = rule.checkRegID(regNumber);
					System.out.println(errorList.size());
					if(rule.isError()) {
						request.setAttribute(RegCompConstant.ERROR_FLAG, "true");
						request.setAttribute(RegCompConstant.ERROR_LIST, errorList);
						eForm.setCheckRegNo("");
					}else {
						dto = regCompBO.getRegApplicationDetails(dto.getRegNumber());
						// Added By Aruna
						session.setAttribute("eStampCode", dto.getEcode());
						eForm.setCheckRegNo("W");
					}	
					forwardJsp = "page1";
				}
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					eForm.setCheckRegNo("");
					resetFields(dto);
					forwardJsp = "welcome";
				}
				if(RegCompConstant.RESET_ACTION.equals(actionName)) {
					eForm.setCheckRegNo("");
				    resetFields(dto);
					forwardJsp = "page1";
				}
				if(RegCompConstant.NEXT_ACTION.equals(actionName)) {
					//eForm.setCheckRegNo("");
					String regNumber = dto.getRegNumber();
					
					RegCompletionRule rule = new RegCompletionRule();
					
					ArrayList errorList = 
						rule.checkRegistrationNumber(regNumber);
					if(rule.isError()) {
						request.setAttribute(RegCompConstant.ERROR_FLAG, "true");
						request.setAttribute(RegCompConstant.ERROR_LIST, errorList);
						eForm.setCheckRegNo("");
						forwardJsp = "page1";
					}else {
						
					}
						// Added By Aruna					
						try {
								regCompDeedList=regCompBO.getCompDeedList(regNumber);						
									if(regCompDeedList!=null && regCompDeedList.size()>0)
									{
										eForm.setDeedTxnList(regCompDeedList);										
										forwardJsp = "deedViewList";
									}else{										
										forwardJsp = "page2";
						            }
									session.setAttribute("sessionRegTxnId",regNumber);
									eForm.setDeedList(regCompBO.getDeedType());
								    eForm.setInstList(new ArrayList());
									eForm.setFormList(new ArrayList());
									eForm.setExemList(new ArrayList());
									// Added By Aruna
									dto.setDeedId("");
							} catch (Exception e) {
								logger.debug(e);
							}
				}
			}
			//End Search Form
			/// Start Search Form 
			if(RegCompConstant.DUTY_SEARCH_FORM.equals(formName)){
				
				
				logger.debug("isTokenValid(request):-"+isTokenValid(request));
				
			 
				
				if(RegCompConstant.CHANGE_ACTION.equals(actionName)) {
					saveToken(request);
					String deedId = dto.getDeedId();
					try {
						RegInitCompleteDTO dtoList = 
							regCompBO.getDeedDetails(deedId);
						eForm.setInstList(dtoList.getInstList());
						 
						eForm.setFormList(dtoList.getFormList());					
						// Added By Aruna
						if(dtoList.getFormList()!=null)
						{
						session.setAttribute("SaleDeedPropertiesList", dtoList.getFormList());
						 final PropertiesFileReader pr;
					     pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
						 String NO_OF_PROPERTY = pr.getValue("NO_OF_PROPERTY");
						 if(NO_OF_PROPERTY==null)
						 {
							 NO_OF_PROPERTY="";
						 }
						 session.setAttribute("NoOfProperty", NO_OF_PROPERTY);
						}
						
						// Edited By aruna
						eForm.setExemList(new ArrayList());
						logger.debug("inside change action");
						forwardJsp = "page2";
					}catch(Exception x) {
						logger.debug(x);
					}
					
				}
				if(RegCompConstant.INSTRUMENT_ACTION.equals(actionName)) {
					saveToken(request);	
					eForm.setExemList(regCompBO.getExemption(dto));
					logger.debug("Inside Exemption");
				}
				if(RegCompConstant.NEXT_ACTION.equals(actionName)) {
					logger.debug("isTokenValid(request) Inside Action:-"
							+isTokenValid(request));
					if( isTokenValid(request))
			        {
						logger.debug("eForm.getFormName():-"+eForm.getFormName());
						ArrayList listForm = eForm.getFormList();
						HashMap mapForm = new HashMap();
						 eForm.setFormName("");
						eForm.setActionName("");
						if(listForm!=null) {
							for(int i =0;i<listForm.size();i++){
								RegInitCompleteDTO formListDTO = 
										(RegInitCompleteDTO) listForm.get(i);
								logger.debug("formValue:-"
											+formListDTO.getInputTypeId()
											+":"+eForm.getValue(
											formListDTO.getInputTypeId()));
								mapForm.put(formListDTO.getInputTypeId(),
										eForm.getValue(
												formListDTO.getInputTypeId()));
							}
							dto.setMapForm(mapForm);
						}
						try {
							String regNo = (String)
							session.getAttribute("sessionRegTxnId");
							dto.setRegNo(regNo);
							eForm.setDeedTxnList(regCompBO.saveDeedDetail(dto));
							 
						}catch(Exception x) {
							logger.debug(x);
						}
						
						
						forwardJsp = "deedViewList";
			            resetToken(request);
			        } else {
			        	 
			        	 logger.debug("eForm.getFormName():-"+eForm.getFormName());
				        // resetToken(request);
			        }
					
				
					//String fieldValue = eForm.getValue(key);
				}
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					//session.removeAttribute("formList");
					session.removeAttribute("sessionRegTxnId");
					forwardJsp = "welcome";
				}
				
				// Added By Aruna
				if(RegCompConstant.RESET_ACTION.equals(actionName)) {
					dto.setDeedId("");
					dto.setExemptionId("");
					dto.setInstrumentId("");
					 eForm.setInstList(new ArrayList());
					 eForm.setFormList(new ArrayList());
					 eForm.setExemList(new ArrayList());
					forwardJsp = "page2";
				}
				if(RegCompConstant.PREVIOUS_ACTION.equals(actionName)) {
					forwardJsp = "page1";
				}
				
				
			}
			//End Search Form
			
			// Start View Deed Form
			if(RegCompConstant.DEED_LIST_VIEW_PAGE.equals(formName)) {
				if(RegCompConstant.DELETE_ACTION.equals(actionName)) {
					//session.removeAttribute("formList");
					String deedTxnId = dto.getDeedTxnId();
					logger.debug("Delete deedTxnId:-"+deedTxnId);
					String regNo = (String)
							session.getAttribute("sessionRegTxnId");
					dto.setRegNo(regNo);
					eForm.setDeedTxnList(regCompBO.deleteDeedDetails(dto));
					
					forwardJsp = "deedViewList";
				}
				if(RegCompConstant.ADD_ACTION.equals(actionName)) {
					 
					try {
						dto.setDeedId("");
						dto.setInstrumentId("off"); 
						dto.setExemptionId("");
						eForm.setInstList(new ArrayList());
						eForm.setFormList(new ArrayList());
						eForm.setExemList(new ArrayList());
						logger.debug("inside change action");
						
					}catch(Exception x) {
						logger.debug(x);
					}
					forwardJsp = "page2";
				}
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					//session.removeAttribute("formList");
					session.removeAttribute("sessionRegTxnId");
					forwardJsp = "welcome";
				}
				if(RegCompConstant.NEXT_ACTION.equals(actionName)) {
					forwardJsp = "addPartyDet";
				}
			}
			//End View Deed Form
			
			//Start Compliance
			if(RegCompConstant.COMPLIANCE_PAGE.equals(formName)) {
				if(RegCompConstant.COMPLIANCE_SUBMIT.equals(actionName)) {
					boolean boo = false;
					String regId = (String)session.getAttribute("sessionRegTxnId"); 
					String regCompId = (String)session.getAttribute("regCompId"); 
					// Edited By Aruna
					//boo = regCompBD.insComplDet(regId,eForm.getCompId(),userId);
					boo = regCompBD.insComplDet(regCompId,eForm.getCompId(),userId);
					// Edited By Aruna
					boo = regCompBD.updateRegDetails(regCompId,"COMPLETED");
					// Added By Aruna
					//boo =regCompBD.insertOldAndNewRegIDS(regId,regCompId);	
					 ArrayList propDeedList=(ArrayList)session.getAttribute("propDeedList");
					 StringBuffer rorIDS=new StringBuffer();
					 if(propDeedList!=null && propDeedList.size()>0)
					 {
					      rorIDS =(StringBuffer)regCompBD.updateDeedRORDtls(propDeedList);					      
					 }
					 session.setAttribute("rorIDS" ,rorIDS);
					 StringBuffer deedTypeNames=new StringBuffer();
					 if(propDeedList!=null && propDeedList.size()>0)
					 {
						 int size=propDeedList.size();
						 for(int i=0;i<size;i++)
						 {
						     CommonDTO commonDTO=(CommonDTO)propDeedList.get(i);						     
						     deedTypeNames.append(commonDTO.getDeedTypeName());
						     if(i!=(size-1))
						     {
						    	 deedTypeNames.append(",  ");
						     }
						 }
					 }
					 session.setAttribute("deedTypeNames" ,deedTypeNames);
					 String param1[]=new String[1];
					 param1[0]=regId;
					 String regInitDate=regCompBD.getRegInitDate(param1);	
					 session.setAttribute("regInitDate" ,regInitDate);					 
					if(boo){
						forwardJsp = "complSuc";
					}
				}
			}
			//End Compliance
			
			eForm.setRegDTO(dto);
		}
		
		
	
		return mapping.findForward(forwardJsp);
	}
	
	private void resetFields(RegInitCompleteDTO dto) {
		  dto.setRegNumber("");
	}
}

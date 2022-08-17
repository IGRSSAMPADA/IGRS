/**
 * 
 */
package com.wipro.igrs.revenueManagement.action;

/**
 * @author SHREERAJ KHARE
 *
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.revenueManagement.bd.RevMgtAdvBD;
import com.wipro.igrs.revenueManagement.bo.RevMgtAdvBO;
import com.wipro.igrs.revenueManagement.dto.RevMgtAdvDTO;
import com.wipro.igrs.revenueManagement.form.RevMgtAdvForm;

public class RevMgtAdvAction  extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(RevMgtAction.class);
	private String forwardPage = "";
	private String reqParam = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		try{
			if(form != null){
				
				String languageLocale=RegInitConstant.LANGUAGE_HINDI;
				if(session.getAttribute("languageLocale")!=null){
					languageLocale=(String)session.getAttribute("languageLocale");
				}
				
				RevMgtAdvForm eForm = (RevMgtAdvForm) form;
				RevMgtAdvBD revBD = new RevMgtAdvBD();
				RevMgtAdvBO bo = new RevMgtAdvBO();
				RevMgtAdvDTO dto = eForm.getRmapDto();
				String userId = (String) session.getAttribute("UserId");
				String officeId = (String) session.getAttribute("loggedToOffice");
				eForm.setuId(userId);
				String nextPage = "";
				String formName = eForm.getFormName();
				String actionName=eForm.getActionName();
				// eForm.getRmDto().setViewResultList(null);
				// String actionName = eForm.getActionName();
				logger.debug("inside action class, Action name is......."
						+ eForm.getActionName());

				String label = null;

				label = (String) request.getParameter("label");
				
				if(label !=null && label.equalsIgnoreCase("revAdvPmt")){
					logger.debug("Label Name-----"+label); 
					eForm.getRmapDto().setLicenseNo("");
					eForm.setFromDate("");
					eForm.setToDate("");
					eForm.getRmapDto().setRadio("dur");
					eForm.setActionName("");
					eForm.getRmapDto().setAccTypeRadio("");
					actionName="";
					eForm.getRmapDto().setListFound(1);
					eForm.getRmapDto().setLicenseList(null);
					forwardPage="advPaySearch";
				}
				//when user serach in advance payment
				 if(!(actionName.equalsIgnoreCase(null)) && actionName.equalsIgnoreCase("AdvanceSearchAction")){
					 logger.debug("Action Name-----"+eForm.getActionName()); 
					 if(eForm.getRmapDto().getRadio().equalsIgnoreCase("lic")){				//check if license number is selected
						 logger.debug("Fteching via License Number"+eForm.getRmapDto().getLicenseNo()); 
						 ArrayList licenseList=bo.fetchSearchDetails(eForm.getRmapDto().getLicenseNo(),languageLocale);
						 if(licenseList!=null && licenseList.size()>0){
						 eForm.getRmapDto().setLicenseList(licenseList);
						 }else{
							 eForm.getRmapDto().setListFound(0);
						 }
						 request.setAttribute("licenseList", licenseList);
						 eForm.getRmapDto().setRadio("");
						 eForm.getRmapDto().setAccountbalance("-");
					 }
					 else if(eForm.getRmapDto().getRadio().equalsIgnoreCase("dur")){																	// if duration is selected
						 ArrayList advDetails=bo.fetchSearchDetails(eForm.getFromDate(),eForm.getToDate(),languageLocale);
						 if(advDetails!=null && advDetails.size()>0){
						 eForm.getRmapDto().setLicenseList(advDetails);
						 }else{
							 eForm.getRmapDto().setListFound(0);
						 }
						 request.setAttribute("licenseList", advDetails);
						 eForm.getRmapDto().setRadio("");
						 eForm.getRmapDto().setAccountbalance("-");
					 }
					
					 forwardPage="advPaySearch";
				 }
				 if(!(actionName.equalsIgnoreCase(null)) && actionName.equalsIgnoreCase("radio")){
					 logger.debug("Action Name-----"+eForm.getActionName()); 
					 eForm.getRmapDto().setLicenseNo("");
					 eForm.getRmapDto().setLicenseList(null);
					 eForm.setFromDate("");
					 eForm.setToDate("");
					 eForm.getRmapDto().setListFound(1);
					 forwardPage="advPaySearch";
				 }
				 	
				 //when user click on radio button in advance payment search view
				   if(request.getParameter("hdnApplicationId")!=null){                 
					   String startPage="";
					  startPage = (String)request.getParameter("hdnApplicationId");
					  String stCumDtList[]= startPage.split("~");
					  String licNo="";
					  String spTypeID="";
					  String spTypeName="";
					  String licToDate="";
					  String licFromDate="";
					  String fullName="";
					 
						if(stCumDtList.length == 5)
						{
							licNo=stCumDtList[0];
							spTypeName=stCumDtList[1];
							licFromDate=stCumDtList[2];
							licToDate=stCumDtList[3];
							spTypeID=stCumDtList[4];
						}
						eForm.getRmapDto().setLicenseNo(licNo);
						eForm.getRmapDto().setSpType(spTypeName);
						if(spTypeID.equalsIgnoreCase("1")){					//if person is SP individual
							
							fullName=bo.getIndvdualName(licNo);
							
							eForm.getRmapDto().setIndvName(fullName);
							
						}
						
						else{												//FOR SP BANK,FI,PO
							fullName=bo.getOthersName(licNo,languageLocale);
							eForm.getRmapDto().setIndvName(fullName);
							
						}
						if(fullName==null){
							eForm.getRmapDto().setIndvName("-");
						}
						
						request.setAttribute("licno", licNo);
					  forwardPage="advPaySearchView";
				   }
				   
					 if(!(actionName.equalsIgnoreCase(null)) && actionName.equalsIgnoreCase("No_Action")){
						 logger.debug("Account type is:::"+eForm.getRmapDto().getAccTypeRadio()); 
						 logger.debug("lic no is"+  eForm.getRmapDto().getLicenseNo()); 
						 //String licNo=(String)request.getAttribute("licno");
						String flag="";
						String accBal="";
						ArrayList stampList=null;
						 if(eForm.getRmapDto().getAccTypeRadio().equalsIgnoreCase("eStamp")){
							 flag="eStamp";
							
							stampList= bo.getSPStmtEstamp(eForm.getRmapDto().getLicenseNo(),flag,languageLocale);//HINDI
							
						 }
						 else if(eForm.getRmapDto().getAccTypeRadio().equalsIgnoreCase("other")){
							 flag="other";
							stampList= bo.getSPStmtEstamp(eForm.getRmapDto().getLicenseNo(),flag,languageLocale);//HINDI
							 
						 }
							request.setAttribute("stampList", stampList);
							eForm.getRmapDto().seteStampList(stampList);
						 accBal=bo.getAccountBal(eForm.getRmapDto().getLicenseNo(),flag);
						 
						 eForm.getRmapDto().setAccountbalance(accBal);
						
						
							  forwardPage="advPaySearchView";
						 
					 }

						 
				
			}			
	}catch (Exception e) {
		logger.error(e.getMessage(), e);
	}
	logger.info("Returning forwardPage : " + forwardPage);
	return mapping.findForward(forwardPage);
	}
}

package com.wipro.igrs.pot.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.pot.bd.PotBD;
import com.wipro.igrs.pot.bo.PotBo;
import com.wipro.igrs.pot.dto.potDTO;
import com.wipro.igrs.pot.form.PotFORM;

public class GetDistrictNames extends BaseAction
{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws Exception 
     */
	
	private  Logger logger = 
		(Logger) Logger.getLogger(GetDistrictNames.class);
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception
	{
		
		try
		{
			logger.info("I am in try of GetDistrictNames");
			
			//
			potDTO dto =  new potDTO();
			PotFORM formData = (PotFORM) form;
			
	           potDTO pdto = formData.getPotDTO();
	           pdto.setCloneSelectedItems(null);
	           pdto.setSelectedItems(null);
	           pdto.setDenominationStamps(0);
	           pdto.setCodeStamps("");
			formData.setDistrict("");
			formData.setDateOfObjection("");
			formData.setTransactionId("");
			formData.setRegNumber("");
			formData.setStampDutyNow("");
			formData.setCaseNum("");
			formData.setCaseStat("");
			formData.setEstampCode("");
			formData.setFirstName("");
			formData.setLastName("");
			formData.setMiddleName("");
			dto.setSelectedItems(null);
			dto.setCloneSelectedItems(null);
			formData.setStampFeeDoc("");
			formData.setStampRequired("");
			formData.setImpound("");
			formData.setPoComments("");
			formData.setCaseOpend("");
			formData.setDrComments("");
			formData.setCaseCh("");
			formData.setCaseIDCh("");
			formData.setEstampCodech("");
			formData.setEstampAmountch("");
			formData.getPotDTO().setAttachedDoc(null);
			formData.getPotDTO().setDocumentName(null);
			formData.getPotDTO().setDocContents(null);
			formData.getPotDTO().setDocFileSize("");
			formData.getPotDTO().setReqStampDuties(0.0);
			formData.getPotDTO().setErrorMsg("");
			formData.setPstampGender("M");
			formData.setPstampGender1("M");
			formData.getPotDTO().setStampDutyNow(0.0);
			formData.setPstampDetails(new HashMap());
			formData.setPstampFirstName("");
			formData.setPstampMiddleName("");
			formData.setPstampLastName("");
			formData.setPstampFathersName("");
			formData.setPstampMothersName("");
			formData.setPstampAddress("");
			formData.setPstampPincode("");
			formData.setPstampMobileNo("");
			formData.setPstampPhoneNo("");
			formData.setPstampEmailId("");
			formData.setPstampAge("");
			formData.setPstampFirstName1("");
			formData.setPstampMiddleName1("");
			formData.setPstampLastName1("");
			formData.setPstampFathersName1("");
			formData.setPstampMothersName1("");
			formData.setPstampAddress1("");
			formData.setPstampPincode1("");
			formData.setPstampMobileNo1("");
			formData.setPstampPhoneNo1("");
			formData.setPstampEmailId1("");
			formData.setPstampAge1("");
			formData.setDrComments("");
			formData.setPstampNoOfPersons("");
			formData.setPstampNoOfPersons1("");
			formData.setImpundCheck("");
			formData.setErrorMsg("");
		//formData.setDataShow("N");
		//formData.setDataShow1("N");
		//formData.setCheckRadio("N");
		//formData.setCheckRadioEP("N");
		
		formData.setDataShow("");
		formData.setDataShow1("N");
		formData.setCheckRadio("");
		formData.setCheckRadioEP("N");
		formData.setRegNumber("");
			///
			
			PotBD bd = new PotBD();
			PotBo objBO = new PotBo();
			PotFORM potfrm  =  (PotFORM)form;
			formData.setOldNew("New");
			//formData.setImpound("");
			String userId = (String) session.getAttribute("UserId");
			String officeId = (String)session.getAttribute("loggedToOffice");

			String language=(String)session.getAttribute("languageLocale");
			formData.setLanguage(language);
			bd.getDistrictNames(request,language);
			
			bd.getDocumentTypeId(request);
			bd.getBookId(request);
			bd.getPhysicalStampCodes(request);
			//bd.getDistrictId(request,language);
			
			 bd.getPotNameBD(userId);
			ArrayList list=(ArrayList) bd.getPotNameBD(userId);
			for(int i=0;i<list.size();i++)
			{
				potDTO potdto=new potDTO();
				potdto=(potDTO)list.get(i);
				//potdto.getFirstName();
				potdto.getPoFirstName();
				//System.out.println(potdto.getFirstName());
				//System.out.println(potdto.getMiddleName());
				//System.out.println(potdto.getLastName());
				
			/*potfrm.setFirstName(potdto.getFirstName());
			potfrm.setMiddleName(potdto.getMiddleName());
			potfrm.setLastName(potdto.getLastName());
			*/

				
				potfrm.setPoFirstName(potdto.getPoFirstName());
				potfrm.setPoMiddleName(potdto.getPoMiddleName());
				potfrm.setPoLastName(potdto.getPoLastName());
				potfrm.setDistrict(potdto.getDistrict());




			}
			
			
			
			///

    		/*ArrayList pendingApplicationList = objBO.getPendingApps(session.getAttribute("UserId").toString());
    		logger.info("--------------->"+pendingApplicationList.size());
			if(pendingApplicationList.size()==0)
			
				potfrm.getPotDto().setPendingApps(null);
			else
			{
				potfrm.getPotDto().setPendingApps(pendingApplicationList);
			    request.setAttribute("pendingApplicationList", pendingApplicationList);
			}
			*/
			///
			
			 
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("businessError.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
		}
		//request.setAttribute("districtNames",list);
		return mapping.findForward("success");
		
		
	}
}
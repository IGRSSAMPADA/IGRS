package com.wipro.igrs.spotInsp.action;
/**
 * ===========================================================================
 * File           :   SpotInspAction1.java
 * Description    :   Represents the Action Class to getting the Drop Down values

 * Author         :   pavani Param
 * Created Date   :   Apr 08, 2008.

 * ===========================================================================
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.newreginit.bo.RegCommonBO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.spotInsp.bd.SpotInspBD;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;
import com.wipro.igrs.spotInsp.form.SpotInspForm;


public class SpotInspAction1 extends  BaseAction

{

	private final Logger logger = Logger.getLogger(SpotInspAction1.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request,
	HttpServletResponse response,HttpSession session ) throws IOException,
	ServletException
	{
		String FORWARD_JSP = "success";
		logger.info("Enter in to the SpotInspAction1 .."+form);
		String strAppNo;
		String strSecApplNo;
		String strCompApplNo;
		String strDrReqApplNo;
		String strDrViewApplNo;
		String regTxnId=null;
		ArrayList propIdList = null;
		RegCompBO regcompbo = new RegCompBO();
		HttpSession sessions = request.getSession(true);
		SpotInspForm spotInsForm;
		SpotInspDTO objSpotDTo =  new SpotInspDTO();
		RegCompletDTO dto = new RegCompletDTO();
		String strPId =  null;
		strPId = request.getParameter("pTxnId");
		strAppNo=request.getParameter("applicationNumber");
		strSecApplNo=request.getParameter("schApplNo");
		strCompApplNo=request.getParameter("compApplNo");
		strDrReqApplNo=request.getParameter("drReqApplNo");
		strDrViewApplNo=request.getParameter("drViewApplNo");
		String strComNo = request.getParameter("spotref");
		String userId = (String)session.getAttribute("UserId");
		String language=(String)session.getAttribute("languageLocale");
		String reqPrint=request.getParameter("getprint");
		session.removeAttribute("regform");
		session.removeAttribute("spotInsForm");



		if(session.getAttribute("spotInsFormSess")!=  null)
		{
			spotInsForm  =  (SpotInspForm)session.getAttribute("spotInsFormSess");
			objSpotDTo  =  spotInsForm.getObjSIDto();
		}
		// reg id getting from reg completion
		//regTxnId = (String)session.getAttribute("sessionRegTxnId");
		spotInsForm = (SpotInspForm)form;
		if(strComNo!=null){
			String[] setSpotref = strComNo == null ? null
			: strComNo.split("~");

			if(setSpotref.length>1&setSpotref!=null){
				spotInsForm.setCompApplNo(setSpotref[0]);
				logger.info("setSpotref[0] "+setSpotref[0]);
				spotInsForm.setStatus(setSpotref[1]);
				logger.info("setSpotref[1] "+setSpotref[1]);
			}
		}
		if(regTxnId == null)
		{
			//reg id getting from spot inspection
			if(spotInsForm.getCompApplNo() != null)
				regTxnId = spotInsForm.getCompApplNo();
			else if(spotInsForm.getDrReqApplNo() != null)
				regTxnId = spotInsForm.getDrReqApplNo();
			else if(spotInsForm.getSchApplNo() != null)
				regTxnId = spotInsForm.getSchApplNo();
			if(regTxnId!=null)
			{
				session.setAttribute("sessionRegTxnId", regTxnId);
				spotInsForm.setRegTxnId(regTxnId);
			}
			else
			{
				session.setAttribute("sessionRegTxnId", strDrViewApplNo);
				spotInsForm.setRegTxnId(strDrViewApplNo);
			}

			//spotInsForm.setRegTxnId(strDrViewApplNo);
			// session.setAttribute("sessionRegTxnId", regTxnId); //Ramesh commented on 09 Jan 13
		}



		String frwdName=request.getParameter("fwdPage");

		//spotInsForm.setRegTxnId(regTxnId); //ramesh commented on 09 jan 13
		//session.removeAttribute("sessionRegTxnId");
		objSpotDTo = new SpotInspDTO();
		String StrCountryID = request.getParameter("countryID");
		String strStateID = request.getParameter("stateID");
		SpotInspBD objBD = new SpotInspBD();

		try
		{
			ArrayList countryList  =  objBD.getCountry();
			//      ArrayList districtList = objBD.getDistrict(strStateID);
			ArrayList  sroList	  = objBD.getSroList();
			objSpotDTo.setCountryList(countryList);
			//   objSpotDTo.setDistrictList(districtList);
			objSpotDTo.setSroList(sroList);

			if(frwdName!=null&&(frwdName.equalsIgnoreCase("viewProperty")))
			{
				RegCommonBO commonBo = new RegCommonBO();
				String propertyId=request.getParameter("propertyTxnID");
				String regId=request.getParameter("registrationNo");
				if(propertyId.length()==15){
					propertyId="0"+propertyId;
				}
				String reginitId="";
				if(regId!=null){
					CaveatsBO objCaveatBO = new CaveatsBO();
					reginitId=objCaveatBO.getReginitId(regId);
				}

				String propval = new CaveatsDAO().getPropVal(regId);
				RegCommonBO bo = new  RegCommonBO();
				RegCommonForm regForm = new RegCommonForm();
				//cDto.setAddressOfInsti(propval);
				if(propval.equalsIgnoreCase("Y"))
				{
					regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirm(reginitId, propertyId,language));
					request.setAttribute("reginit", regForm);
					FORWARD_JSP = "propertyView";
				}
				else
				{
					regForm.setMapPropertyTransPartyDisp(bo.getPropertyDetsForViewConfirmNonProp(reginitId, propertyId, language));
					request.setAttribute("reginit", regForm);
					FORWARD_JSP = "propertyView1";
				}
			}


			if(frwdName!=null&&(frwdName.equalsIgnoreCase("resetpage")))
			{

				spotInsForm.setSchApplNo(spotInsForm.getRegTxnId());
				spotInsForm.setPropId("");
				logger.info("SpotInspAction1--before getting the Schduele details  SpotInspSechDetails");
				spotInsForm =objBD.getSpotInspSechDet(spotInsForm);
				spotInsForm.setRemarks("");
				spotInsForm.setSiReason("");
				objSpotDTo.setPropList(null);
				session.removeAttribute("spotInsFormSess");
				//spotInsForm.setReqType("Select");
			}

			if(strAppNo != null)  //spot--Inspcetion View.
			{
				spotInsForm.setReferenceID(strAppNo);
				spotInsForm =objBD.getSpotInspViewDet(spotInsForm);
				logger.info("SpotInspAction1--after getting the Schduele details  SpotInspView Details");
			}

			if(strSecApplNo != null)   //spot--Inspcetion Schdule.
			{

				objSpotDTo.setPropList(null);
				spotInsForm.setReqiDate("");
				spotInsForm.setSiReason("");
				spotInsForm.setRemarks("");
				session.removeAttribute("spotInsFormSess");
				spotInsForm.setSchApplNo(strSecApplNo);
				logger.info("SpotInspAction1--before getting the Schduele details  SpotInspSechDetails");
				spotInsForm =objBD.getSpotInspSechDet(spotInsForm);
				spotInsForm.setSchApplNo(strSecApplNo);
			}
			if(strCompApplNo != null)  // spot--Inspcetion Completion.
			{
				spotInsForm.setSchApplNo(strCompApplNo);
				spotInsForm =objBD.getSpotInspCompDet(spotInsForm);
				logger.info("SpotInspAction1--after getting the completion details  SpotInspCompDetails");
			}
			if(strDrReqApplNo != null)  // spot--Inspcetion DR Request Details.
			{
				spotInsForm.setDrRemarks("");
				spotInsForm.setDrReason("");
				spotInsForm.setSchApplNo(strDrReqApplNo);
				spotInsForm.setApplicationNumber(strDrReqApplNo);
				spotInsForm =objBD.getSpotDrReqDet(spotInsForm);
				spotInsForm=	objBD.getSpotInspViewDet(spotInsForm);
				spotInsForm.getSpotCompViewList().size();
				spotInsForm.setSrList(objBD.getSrList(userId));
				spotInsForm.setStatus("Completed");
				logger.info(" Completed spotInsForm.getSrList().size() ;= "+spotInsForm.getSrList().size());
				logger.info("SpotInspAction1--after getting DR Request upadate  details  ");
			}
			if(strDrViewApplNo != null)  // spot--Inspcetion DR Request View  Details.
			{
				logger.info("SpotInspAction1--in side if  strDrViewApplNo = "+strDrViewApplNo);
				spotInsForm.setSchApplNo(strDrViewApplNo);
				String status=spotInsForm.getStatus();
				if(status.equalsIgnoreCase("C"))
				{

					spotInsForm.setApplicationNumber(strDrViewApplNo);
					spotInsForm=	objBD.getSpotInspViewDet(spotInsForm);
					spotInsForm.getSpotCompViewList().size();
					spotInsForm.setStatus("Completed");
					logger.info(" Completed spotInsForm.getSpotCompViewList().size();= "+spotInsForm.getSpotCompViewList().size());
				}
				else  if(status.equalsIgnoreCase("Y"))
				{
					spotInsForm.setSpotCompViewList(objBD.getPropIdList(strDrViewApplNo));
					logger.info("Pending spotInsForm.getSpotCompViewList().size();= "+spotInsForm.getSpotCompViewList().size());
					spotInsForm.setStatus("Pending");
				}


			}
			if(strPId != null)
			{

				String PropStatus =objBD.getPropStatus(strPId);
				System.out.println("PropStatus = "+PropStatus);
				spotInsForm.setPropStatus("");
				request.setAttribute("PropStatus", null);
				if("C".equalsIgnoreCase(PropStatus))
				{
					spotInsForm.setPropStatus("C");
					String msg="The property id "+strPId+" has already been used for spot inspection.Please try with another peroperty id.";
					request.setAttribute("PropStatus", msg);
				}else{
					ArrayList	viewList=null;
					viewList = regcompbo.getSelectedProperty(strPId);
					dto.setPropertyDispList(viewList);
					spotInsForm.setRegcompletDto(dto);
					session.setAttribute("regform", spotInsForm);
				}

			}
			if(spotInsForm.getSpotId()!=null)
			{

				if(spotInsForm.getSpotId()!=null){
					String[] setSpotref = spotInsForm.getSpotId() == null ? null
					: spotInsForm.getSpotId().split("~");

					if(setSpotref.length>1&setSpotref!=null){
						spotInsForm.setDistrictId((setSpotref[0]));
						logger.info("setSpotref[0] "+setSpotref[0]);
						spotInsForm.setStatus(setSpotref[1]);
						spotInsForm.setFoundStatus((setSpotref[2]));
						logger.info("setSpotref[1] "+setSpotref[1]);
						logger.info("setSpotref[2] "+setSpotref[2]);
					}
				}
				spotInsForm.setViewType("Date");
				spotInsForm=objBD.getDrViewRes(spotInsForm);
				logger.info("spotInsForm  getSpotViewList size "+spotInsForm.getSpotViewList().size());
				request.setAttribute("spotVRList",spotInsForm.getSpotViewList());

			}
			if(spotInsForm.getRegTxnId() != null  && (frwdName!=null&&(frwdName.equalsIgnoreCase("sdsd"))))
			{
				SpotInspDTO temp=null;
				String regTxnIds = objBD.getRegCompletionNumber(spotInsForm.getRegTxnId());


				if(spotInsForm.getStatus().equalsIgnoreCase("R"))
				{
					//propIdList = objBD.getNewPropIdList(spotInsForm.getRegTxnId());
					propIdList =  objBD.getPropIdListLR(spotInsForm.getRegTxnId());
					temp = objBD.getReRegInfo(spotInsForm.getRegTxnId());
					if(session.getAttribute("currentLoggedInDistrict")!=null){
						String sdistrict = (String)   	    session.getAttribute("currentLoggedInDistrict");
						spotInsForm.setDistrictId(sdistrict);
						logger.debug("spotInsForm.getDistrictId() "+spotInsForm.getDistrictId());
						FORWARD_JSP="resuccess";
					}
				}
				else{
					propIdList = objBD.getPropIdListL(regTxnIds);
					temp = objBD.getRegInfo(regTxnIds);
					if(session.getAttribute("currentLoggedInDistrict")!=null){
						String sdistrict = (String)session.getAttribute("currentLoggedInDistrict");
						spotInsForm.setDistrictId(sdistrict);
						logger.debug("spotInsForm.getDistrictId() "+spotInsForm.getDistrictId());
					}
					spotInsForm.setRegTxnId(null);
					FORWARD_JSP="success";
				}
				if(spotInsForm.getStatus().equalsIgnoreCase("R"))
				{
					objSpotDTo.setPropList(propIdList);
					objSpotDTo.setRegFee(temp.getRegFee());
					objSpotDTo.setNewRegfee(temp.getCrossRegFee());

					objSpotDTo.setNewStampDuty(temp.getCrossStampDuty());
					objSpotDTo.setStampDuty(temp.getStampDuty());
					objSpotDTo.setRemark(temp.getRemark());
					objSpotDTo.setNewRemark(temp.getCrossRemark());

					objSpotDTo.setCrossRegFee(temp.getCrossRegFee());
					objSpotDTo.setCrossStampDuty(temp.getCrossStampDuty());
					objSpotDTo.setCrossRemark(temp.getCrossRemark());

					objSpotDTo.setJandpadDuty(temp.getJandpadDuty());
					objSpotDTo.setNewJandpadDuty(temp.getCrossJanpadDuty());
					objSpotDTo.setCrossJanpadDuty(temp.getCrossJanpadDuty());

					objSpotDTo.setTotalDuty(temp.getTotalDuty());
					objSpotDTo.setNewTotalDuty(temp.getCrossTotalDuty());
					objSpotDTo.setCrossTotalDuty(temp.getCrossTotalDuty());

					objSpotDTo.setMunicipalDuty(temp.getMunicipalDuty());
					objSpotDTo.setNewMunicipalDuty(temp.getCrossMuncipalDuty());
					objSpotDTo.setCrossMuncipalDuty(temp.getCrossMuncipalDuty());

					objSpotDTo.setUpkarDuty(temp.getUpkarDuty());
					objSpotDTo.setNewUpkarDuty(temp.getCrossUpkarDuty());
					objSpotDTo.setCrossUpkarDuty(temp.getCrossUpkarDuty());


				}
				else
				{
					objSpotDTo.setPropList(propIdList);
					objSpotDTo.setRegFee(temp.getRegFee());
					objSpotDTo.setNewRegfee(temp.getRegFee());
					objSpotDTo.setNewStampDuty(temp.getStampDuty());
					objSpotDTo.setStampDuty(temp.getStampDuty());
					objSpotDTo.setJandpadDuty(temp.getJandpadDuty());
					objSpotDTo.setMunicipalDuty(temp.getMunicipalDuty());
					objSpotDTo.setUpkarDuty(temp.getUpkarDuty());
					objSpotDTo.setTotalDuty(temp.getTotalDuty());
					objSpotDTo.setNewJandpadDuty(temp.getJandpadDuty());
					objSpotDTo.setNewTotalDuty(temp.getTotalDuty());
					objSpotDTo.setNewJandpadDuty(temp.getJandpadDuty());
					objSpotDTo.setNewUpkarDuty(temp.getUpkarDuty());
					objSpotDTo.setNewMunicipalDuty(temp.getMunicipalDuty());
				}

			}
			//spotInsForm.setSpotCompViewList(propIdList);
			spotInsForm.setObjSIDto(objSpotDTo);

			session.setAttribute("spotInsFormSess",spotInsForm);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// logger.info(" list size  "+spotInsForm.getSpotCompViewList().size());
		return mapping.findForward(FORWARD_JSP);
	}

}

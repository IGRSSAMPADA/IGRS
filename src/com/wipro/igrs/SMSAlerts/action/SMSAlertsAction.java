package com.wipro.igrs.SMSAlerts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.SMSAlerts.bd.SMSAlertsBD;
import com.wipro.igrs.SMSAlerts.dto.SMSAlertsDTO;
import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;

public class SMSAlertsAction extends BaseAction{
  String forward_Jsp;
  private Logger logger = Logger.getLogger(SMSAlertsAction.class);
  
  public SMSAlertsAction() {}
  
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception
  {
    logger.info("we are in SMS Alerts Action");
    
    String frdName = request.getParameter("pageName");
    String funName = request.getParameter("funId");
    String userId = (String)session.getAttribute("UserId");
    SMSAlertsForm SMSForm = (SMSAlertsForm)form;
    SMSAlertsDTO dto = SMSForm.getSmsdto();
    SMSAlertsBD SRObd = new SMSAlertsBD();
    String lang = (String)session.getAttribute("languageLocale");
    String activityid = request.getParameter("actid");
    String officeId = (String)session.getAttribute("loggedToOffice");
    IGRSCommon save = new IGRSCommon();
    SendSMSAlertsAction sendSMS = null;
    
    dto.setLinkClick("");
    dto.setNowAdd("");
    SMSForm.getSmsdto().setErrorFlag("");
    String ZoneId = SMSForm.getZoneId();
    
    if (frdName != null)
    {

      if ((frdName.equalsIgnoreCase("smsAlertAdd")) && (funName.equalsIgnoreCase("FUN_503")))
      {
        logger.debug("frdName");
        
        String StateId = SMSForm.getSmsdto().getStateId();
        
        String zoneANme = SMSForm.getSmsdto().getZoneName();
        
        SMSForm.setDistrictList(new ArrayList());
        SMSForm.setZoneList(new ArrayList());
        save.saveactivitylog(userId, activityid);
        dto.setLogicBtn("all");
        dto.setNowAdd("");
        ArrayList zoneList = new ArrayList();
        zoneList = SMSAlertsBD.getSMSZone(lang);
        SMSForm.setZoneList(zoneList);
        SMSForm.setUserType("");
        SMSForm.setUserTypes("");
        logger.debug("sucess");
        dto.setLogicBtn("");
        
        SMSForm.setActionName(null);
        
        frdName = "";
        forward_Jsp = "smsAlertAdd";
      }
    }

     //Added By Bhaskar

    if (frdName != null)
    {

      if ((frdName.equalsIgnoreCase("smsAlertCustomSMS")) && (funName.equalsIgnoreCase("FUN_503")))
      {
        logger.debug("frdName");
        
        String StateId = SMSForm.getSmsdto().getStateId();
        
        String zoneANme = SMSForm.getSmsdto().getZoneName();
        
        SMSForm.setDistrictList(new ArrayList());
        SMSForm.setZoneList(new ArrayList());
        save.saveactivitylog(userId, activityid);
        dto.setLogicBtn("all");
        dto.setNowAdd("");
        ArrayList zoneList = new ArrayList();
        zoneList = SMSAlertsBD.getSMSZone(lang);
        SMSForm.setZoneList(zoneList);
        
        SMSForm.setUserTypes("");
        SMSForm.setSmsHindiContent("");
        logger.debug("sucess");
        dto.setLogicBtn("");
        SMSForm.setActionName(null);
        frdName = "";
        forward_Jsp = "smsAlertCustomSMS";
      }
    }
    
    if (frdName != null)
    {

      if ((frdName.equalsIgnoreCase("smsAlertSearch")) && (funName.equalsIgnoreCase("FUN_503")))
      {
        logger.debug("frdName");
        
        String StateId = SMSForm.getSmsdto().getStateId();
        
        String zoneANme = SMSForm.getSmsdto().getZoneName();
        
        SMSForm.setDistrictList(new ArrayList());
        SMSForm.setZoneList(new ArrayList());
        save.saveactivitylog(userId, activityid);
        dto.setLogicBtn("all");
        dto.setNowAdd("");
        ArrayList zoneList = new ArrayList();
        zoneList = SMSAlertsBD.getSMSZone(lang);
        SMSForm.setZoneList(zoneList);
        
        logger.debug("sucess");
        dto.setLogicBtn("");
        SMSForm.setActionName(null);
        frdName = "";
        SMSForm.setUserType("");
        forward_Jsp = "smsAlertSearch";
      }
    }
    
    //Added by Neeti
	
	if(frdName!=null){
		if(frdName.equalsIgnoreCase("sendSmsToMobileNumbers") && funName.equalsIgnoreCase("FUN_503")){
			SMSForm.setUserMobileNumber("");
			SMSForm.setSmsHindiContent("");
			SMSForm.setActionName(null);
			forward_Jsp="sendSmsToMobileNumbers";
		}
	}
	
	if(frdName!=null){
		if(frdName.equalsIgnoreCase("smsGroupCreate") && funName.equalsIgnoreCase("FUN_510")){
			SMSForm.setUserMobileNumber("");
			SMSForm.setSmsHindiContent("");
			SMSForm.setSaveGrpSuccess(0);
			SMSForm.setAddMoreCounter(0);
			SMSForm.setMapGroupMembers(new HashMap());
			SMSForm.setUserName("");
			SMSForm.setDeleteTrnsPrtyID("");
			SMSForm.setGroupInfo(new HashMap());
			SMSForm.setGroupListCounter(0);
			SMSForm.setGroupMemberCounter(0);
			SMSForm.setGroupMembersInfo(new HashMap());
			SMSForm.setGroupName("");
			SMSForm.setActionName(null);
			forward_Jsp="smsGroupCreate";
		}
	}
	
	if(frdName!=null){
		if(frdName.equalsIgnoreCase("smsGroupEdit") && funName.equalsIgnoreCase("FUN_510")){
			SMSForm.setUserMobileNumber("");
			SMSForm.setSmsHindiContent("");
			SMSForm.setGroupListCounter(0);
			SMSForm.setGroupMemberCounter(0);
			SMSForm.setDurationFrom("");
			SMSForm.setDurationTo("");
			SMSForm.setActionName(null);
			forward_Jsp="smsGroupEdit";
		}
	}
	
	
	if(frdName!=null){
		if(frdName.equalsIgnoreCase("smsGroupSend") && funName.equalsIgnoreCase("FUN_510")){
			SMSForm.setUserMobileNumber("");
			SMSForm.setSmsHindiContent("");
			SMSForm.setGroupListCounter(0);
			SMSForm.setGroupMemberCounter(0);
			SMSForm.setDurationFrom("");
			SMSForm.setDurationTo("");
			SMSForm.setSmsSendSuccess(0);
			SMSForm.setSystemUsers(0);
			SMSForm.setNonSystemUsers(0);
			SMSForm.setUserType("");
			SMSForm.setUserTypes("");
			SMSForm.setHdnDeleteGroup("");
			SMSForm.setHdnDeleteGrpMember("");
			SMSForm.setHdnDistrictList("");
			SMSForm.setHdnRoleList("");
			SMSForm.setHdnZoneList("");
			SMSForm.setActionName(null);
			SMSForm.setDeleteTrnsPrtyZone("");
			SMSForm.setDeleteTrnsPrtyRole("");
			SMSForm.setDeleteTrnsPrtyDist("");
			SMSForm.setDeleteTrnsPrtyID("");
			forward_Jsp="smsGroupSend";
		}
	}
    
    if (frdName != null)
    {

      if ((frdName.equalsIgnoreCase("smsAlertContentSMS")) && (funName.equalsIgnoreCase("FUN_503")))
      {
        logger.debug("frdName");
        
        String StateId = SMSForm.getSmsdto().getStateId();
        
        String zoneANme = SMSForm.getSmsdto().getZoneName();
        
        SMSForm.setDistrictList(new ArrayList());
        SMSForm.setZoneList(new ArrayList());
        save.saveactivitylog(userId, activityid);
        dto.setLogicBtn("all");
        dto.setNowAdd("");
        ArrayList zoneList = new ArrayList();
        zoneList = SMSAlertsBD.getSMSZone(lang);
        SMSForm.setZoneList(zoneList);
        
        SMSForm.setUserTypes("");
        SMSForm.setSmsHindiContent("");
        
        logger.debug("sucess");
        dto.setLogicBtn("");
        SMSForm.setActionName(null);
        frdName = "";
        SMSForm.setUserType("");
        
        forward_Jsp = "smsAlertContentSMS";
      }
      

    }
    else {
      System.out.println("Else loop in Action class");
    }
    
	//Added By Neeti 
	
	String nextPage="";
	if(request.getParameter("GroupDtls")!=null){
		nextPage = (String)request.getParameter("GroupDtls");
	
	    if(!(nextPage).equalsIgnoreCase("")){
	    	if(nextPage.equalsIgnoreCase("searchGroupDetails")){
	    		String grpName="";
	    		if(request.getParameter("key")!=null)
	    			grpName=request.getParameter("key");
	    		
	    		//String name = new String(grpName.getBytes("ISO-8859-1"), "UTF-8");
	            //System.out.println("name :-   " + name);
	            
	    		ArrayList memberDtls = SRObd.searchMemberDtls(grpName,userId); 
	   		 	System.out.println("dtls"+memberDtls);
	   		 	SMSForm.setSmsGrpMembers(memberDtls);
		   		System.out.println(SMSForm.getSmsGrpMembers().size());
				if(SMSForm.getSmsGrpMembers().size()>0){
					request.setAttribute("memberList", SMSForm.getSmsGrpMembers());
					SMSForm.setActionName(null);
					forward_Jsp="smsGroupMembersEdit";	
				}
				else{
					SMSForm.setActionName(null);
					dto.setErrorFlag("NoData");
					 forward_Jsp="smsGroupMembersEdit";
				}
				SMSForm.setGroupName(grpName);
				SMSForm.setSaveGrpSuccess(1);
				return mapping.findForward(forward_Jsp);
	   		 	
	   		 	
	    	}
	    }
	
	}
	
    
    if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("getZone")))
    {
      ArrayList editdistrictlist = new ArrayList();
      editdistrictlist = SMSAlertsBD.getSMSDistrict(lang, ZoneId);
      System.out.println("districtlist" + editdistrictlist.size());
      SMSForm.setDistrictList(editdistrictlist);
      SMSForm.setActionName(null);
      forward_Jsp = "smsAlertAdd";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("stateAction")))
    {

      ArrayList stateData = SMSAlertsBD.getStateData(lang);
      System.out.println("stateData" + stateData);
      
      SMSForm.setStateData(stateData);
      System.out.println(SMSForm.getStateData().size());
      request.setAttribute("stateList", SMSForm.getStateData());
      //SMSForm.setActionName(null);
      forward_Jsp = "stateSmsEdit";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("addMoreState")))
    {
      System.out.println("addMoreState");
      SMSForm.setAddMoreState("NEW");
      System.out.println("NEW" + SMSForm.getAddMoreState());
      SMSForm.setActionName(null);
      forward_Jsp = "stateSmsEdit";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("zoneAction")))
    {

      ArrayList zoneData = new ArrayList();
      zoneData = SMSAlertsBD.getZoneData(lang);
      System.out.println("districtData" + zoneData.size());
      
      SMSForm.setZoneData(zoneData);
      System.out.println(SMSForm.getZoneData().size());
      request.setAttribute("zoneList", SMSForm.getZoneData());
      //SMSForm.setActionName(null);
      forward_Jsp = "zoneSmsEdit";


    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("districtAction")))
    {

      ArrayList districtData = new ArrayList();
      ArrayList smsData = new ArrayList();
      districtData = SMSAlertsBD.getDistrictData(lang);
      System.out.println("districtData" + districtData.size());
      System.out.println("check Flag" + SMSForm.getSmsdto().getCheckFlag());
     
      SMSForm.setDistrictData(districtData);
      System.out.println(SMSForm.getDistrictData().size());
      request.setAttribute("districtList", SMSForm.getDistrictData());
      //SMSForm.setActionName(null);
      forward_Jsp = "districtSmsEdit";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("stateExternalAction")))
    {
      SMSForm.getSmsdto().setUserName("");
      SMSForm.getSmsdto().setUserDesignation("");
      SMSForm.getSmsdto().setUserMobileNumber("");
      
      SMSForm.setActionName(null);
      forward_Jsp = "smsAlertAdd";
    }
    
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("zoneExternalAction")))
    {

      SMSForm.setActionName(null);
      SMSForm.setZoneId("");
      SMSForm.setZoneName("");
      SMSForm.getSmsdto().setUserName("");
      SMSForm.getSmsdto().setUserDesignation("");
      SMSForm.getSmsdto().setUserMobileNumber("");
      
      forward_Jsp = "smsAlertAdd";
    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("districtExternalAction")))
    {

      SMSForm.setActionName(null);
      SMSForm.setZoneId("");
      SMSForm.setZoneName("");
      
      SMSForm.setDistrictName("");
      SMSForm.getSmsdto().setDistrictId("");
      SMSForm.getSmsdto().setUserName("");
      SMSForm.getSmsdto().setUserDesignation("");
      SMSForm.getSmsdto().setUserMobileNumber(""); 

      forward_Jsp = "smsAlertAdd";
    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("insertData")))
    {

      boolean flag = false;
      boolean insertData = SRObd.insertSMSData(SMSForm, userId);
      System.out.println("insertData flag value in action class" + insertData);
      if (insertData) {
        dto.setErrorFlag("ConstN");

      }
      else
      {
        dto.setErrorFlag("ConstY");
      }
      SMSForm.setActionName(null);
      SMSForm.setUserDesignation("");
      SMSForm.setUserName("");
      SMSForm.setUserMobileNumber("");
      forward_Jsp = "SMSMessage";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("submitStateData")))
    {

      String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
      
      if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
      {

        boolean flag = false;
        boolean updateData = SRObd.updateStateSMSData(SMSForm, userId, checkBoxSize);
        if (updateData)
        {
          dto.setErrorFlag("SubmitN");
        }
        else
        {
          dto.setErrorFlag("SubmitY");
        }

      }
      else
      {

        boolean flag = false;
        boolean updateData = SRObd.updateStateSMSData(SMSForm, userId, checkBoxSize);
        
        if (updateData)
        {
          dto.setErrorFlag("SubmitN");
        }
        else
        {
          dto.setErrorFlag("SubmitY");
        }
      }

      SMSForm.setActionName(null);
      forward_Jsp = "SMSMessage";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("statedelete")))
    {

      String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
      
      if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
      {
        boolean flag = false;
        boolean updateData = SRObd.deleteSMSData(SMSForm, userId, checkBoxSize);
        
        if (updateData)
        {
          dto.setErrorFlag("DeleteN");
        }
        else
        {
          dto.setErrorFlag("DeleteY");
        }

      }
      else
      {

        boolean flag = false;
        boolean updateData = SRObd.deleteSMSData(SMSForm, userId, checkBoxSize);
        
        if (updateData)
        {
          dto.setErrorFlag("DeleteN");
        }
        else
        {
          dto.setErrorFlag("DeleteY");
        }
      }
      
      SMSForm.setActionName(null);
      forward_Jsp = "SMSMessage";

    }
    else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("updateStateData")))
    {
      String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
      if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
      {
        boolean flag = false;
        boolean updateData = SRObd.updateStateMobileData(SMSForm, userId, checkBoxSize);
        
        if (updateData)
        {
          dto.setErrorFlag("UpdateN");
        }
        else
        {
          dto.setErrorFlag("UpdateY");
        }

      }
      else
      {
        boolean flag = false;
        boolean updateData = SRObd.updateStateMobileData(SMSForm, userId, checkBoxSize);
        
        if (updateData)
        {
          dto.setErrorFlag("UpdateN");
        }
        else
        {
          dto.setErrorFlag("UpdateY");
        }
      }
      SMSForm.setActionName(null);
      forward_Jsp = "SMSMessage";

    }
    else
    {

      if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("submitZoneData")))
      {
        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        System.out.println("Zone Id in Action Class " + SMSForm.getZoneId());
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {
          boolean flag = false;
          boolean updateData = SRObd.updatezoneSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("SubmitN");
          }
          else
          {
            dto.setErrorFlag("SubmitY");
          }
          SMSForm.setActionName(null);
          forward_Jsp = "SMSMessage";

        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.updatezoneSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("SubmitN");
          }
          else
          {
            dto.setErrorFlag("SubmitY");
          }
          SMSForm.setActionName(null);
          forward_Jsp = "SMSMessage";
        }
        return mapping.findForward(forward_Jsp);
      }

      if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("zonedelete")))
      {

        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {
          boolean flag = false;
          boolean updateData = SRObd.deletezoneSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("DeleteN");
          }
          else
          {
            dto.setErrorFlag("DeleteY");

          }
          

        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.deletezoneSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("DeleteN");
          }
          else
          {
            dto.setErrorFlag("DeleteY");
          }
        }
        SMSForm.setActionName(null);
        forward_Jsp = "SMSMessage";

      }
      else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("updateZoneData")))
      {

        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {
          boolean flag = false;
          boolean updateData = SRObd.updatezoneSMSMobileData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("UpdateN");
          }
          else
          {
            dto.setErrorFlag("UpdateY");

          } 

        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.updatezoneSMSMobileData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("UpdateN");
          }
          else
          {
            dto.setErrorFlag("UpdateY");
          }
        }
        SMSForm.setActionName(null);
        forward_Jsp = "SMSMessage";

      }
      else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("submitDistrictData")))
      {
        System.out.println(SMSForm.getCheckBoxFlag());
        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        System.out.println("checkbox flag" + SMSForm.getCheckFlag());
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {

          boolean flag = false;
          boolean updateData = SRObd.updateDistrictSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("SubmitN");
          }
          else
          {
            dto.setErrorFlag("SubmitY");

          }         

        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.updateDistrictSMSData(SMSForm, userId, checkBoxSize, lang);
          
          if (updateData)
          {
            dto.setErrorFlag("SubmitN");
          }
          else
          {
            dto.setErrorFlag("SubmitY");
          }
        }
        SMSForm.setActionName(null);
        forward_Jsp = "SMSMessage";

      }
      else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("districtDelete")))
      {
        System.out.println(SMSForm.getCheckBoxFlag());
        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        System.out.println("checkbox flag" + SMSForm.getCheckFlag());
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {

          boolean flag = false;
          boolean updateData = SRObd.deleteDistrictSMSData(SMSForm, userId, checkBoxSize);
          
          if (updateData)
          {
            dto.setErrorFlag("DeleteN");
          }
          else
          {
            dto.setErrorFlag("DeleteY");

          }
          
        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.deleteDistrictSMSData(SMSForm, userId, checkBoxSize);
          
          if (updateData)
          {
            dto.setErrorFlag("DeleteN");
          }
          else
          {
            dto.setErrorFlag("DeleteY");
          }
        }
        SMSForm.setActionName(null);
        forward_Jsp = "SMSMessage";



      }
      else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("updateDistrictMobileData")))
      {
        System.out.println(SMSForm.getCheckBoxFlag());
        String[] checkBoxSize = SMSForm.getCheckBoxFlag().split(",");
        System.out.println("checkbox flag" + SMSForm.getCheckFlag());
        if ((SMSForm.getCheckBoxFlag() != null) && (SMSForm.getCheckBoxFlag().contains(",")))
        {

          boolean flag = false;
          boolean updateData = SRObd.updateDistrictSMSMobileData(SMSForm, userId, checkBoxSize);
          
          if (updateData)
          {
            dto.setErrorFlag("UpdateN");
          }
          else
          {
            dto.setErrorFlag("UpdateY");

          }
          

        }
        else
        {

          boolean flag = false;
          boolean updateData = SRObd.updateDistrictSMSMobileData(SMSForm, userId, checkBoxSize);
          
          if (updateData)
          {
            dto.setErrorFlag("UpdateN");
          }
          else
          {
            dto.setErrorFlag("UpdateY");
          }
        }
        SMSForm.setActionName(null);
        forward_Jsp = "SMSMessage";

      }
      else
      {

        if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("stateUsers")))
        {


          ArrayList stateUsersData = SMSAlertsBD.getStateUsersData(SMSForm, lang);
          System.out.println("stateData" + stateUsersData);
          
          SMSForm.setStateUsersData(stateUsersData);
          System.out.println(SMSForm.getStateData().size());
          if (SMSForm.getStateUsersData().size() > 0) {
            request.setAttribute("stateList", SMSForm.getStateUsersData());
            //SMSForm.setActionName(null);
            forward_Jsp = "stateSearchSms";
          }
          else {
            SMSForm.setActionName(null);
            dto.setErrorFlag("NoData");
            forward_Jsp = "SMSMessage";
          }
          return mapping.findForward(forward_Jsp);
        }
        



        if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("zoneUsers")))
        {

          ArrayList zoneData = new ArrayList();
          zoneData = SMSAlertsBD.getZoneUsersData(SMSForm, lang);
          SMSForm.setZoneUsersData(zoneData);
          
          if (SMSForm.getZoneUsersData().size() > 0) {
            request.setAttribute("zoneList", SMSForm.getZoneUsersData());
            //SMSForm.setActionName(null);
            forward_Jsp = "zoneSearchSms";
          }
          else {
            SMSForm.setActionName(null);
            dto.setErrorFlag("NoData");
            forward_Jsp = "SMSMessage";
          }
          return mapping.findForward(forward_Jsp);
        }
        
        if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("districtUsers")))
        {

          ArrayList districtData = new ArrayList();
          ArrayList smsData = new ArrayList();
          districtData = SMSAlertsBD.getDistrictUsersData(SMSForm, lang);
          
          SMSForm.setDistrictUsersData(districtData);
          System.out.println(SMSForm.getDistrictUsersData().size());
          if (SMSForm.getDistrictUsersData().size() > 0) {
            request.setAttribute("districtList", SMSForm.getDistrictUsersData());
            //SMSForm.setActionName(null);
            forward_Jsp = "districtSearchSms";
          }
          else {
            SMSForm.setActionName(null);
            dto.setErrorFlag("NoData");
            forward_Jsp = "SMSMessage";
          }
          return mapping.findForward(forward_Jsp);
        }
        
        if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("insertCustomSMSAction")))
        {
          SendSMSAlertsAction sms = new SendSMSAlertsAction();
          sms.call(SMSForm, userId);
          dto.setErrorFlag("CustomSMS");
          //SMSForm.setActionName(null);
          forward_Jsp = "SMSMessage";

        }
        else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("updateCustomSMSAction")))
        {
        	ArrayList getData = SRObd.getCustomSMSData(SMSForm, userId);
          SMSForm.setSmsData(getData);
          System.out.println("Data Size in Custom sms" + SMSForm.getSmsData().size());
          SMSForm.setActionName(null);
          forward_Jsp = "smsAlertContentSMS";

        }
        else if ((SMSForm.getActionName() != null) && (SMSForm.getActionName().equals("updateSMSCustomAction")))
        {

          SendSMSAlertsAction sms = new SendSMSAlertsAction();
          sms.sendConfigSms(SMSForm, userId);
          dto.setErrorFlag("CustomSMS");
          forward_Jsp = "SMSMessage";
          SMSForm.setActionName(null);
        }
        
      //Added by Neeti --- SMS Alerts
		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("sendSMStoMobile"))
   	 {
   		 logger.debug("Entered sendSMStoMobile... ");
   		 //String s = new String(SMSForm.getSmsHindiContent().getBytes("ISO-8859-1"), "UTF-8");
   		//System.out.println("S :-   " + s);
   		// SMSForm.setSmsHindiContent(s);
   		SendSMSAlertsAction sendSMSObj = new SendSMSAlertsAction();
   		sendSMSObj.call(SMSForm, userId);
   		//sendSMSObj.call(SMSForm);
   		SMSForm.setActionName(null);
   		forward_Jsp="sendSMSSuccess";
   		 
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("addMoreAction"))
   	 {
   		 logger.debug("addMoreAction... ");
   		SMSAlertsDTO sAlertsDTO= new SMSAlertsDTO(); 
   		HashMap map =null;
   		map = SMSForm.getMapGroupMembers();
   		System.out.println("EEEE  "+SMSForm.getMapGroupMembers().get(1));
   		//String name = new String(SMSForm.getUserName().getBytes("ISO-8859-1"), "UTF-8");
        //System.out.println("name :-   " + name);
   		sAlertsDTO.setUserName(SMSForm.getUserName());
   		sAlertsDTO.setUserMobileNumber(SMSForm.getUserMobileNumber());
           int count=SMSForm.getMapKeyCount();
           if(count==0)
          	 count=1;
           
           if(map.containsKey(Integer.toString(count))){
          	 count++;
          	 map.put(Integer.toString(count), sAlertsDTO);
               }
           else
          	 map.put(Integer.toString(count), sAlertsDTO);
                
           SMSForm.setMapKeyCount(count);
           SMSForm.setAddMoreCounter(map.size());
           SMSForm.setMapGroupMembers(map);
           //String s = new String(SMSForm.getGroupName().getBytes("ISO-8859-1"), "UTF-8");
           // System.out.println("S :-   " + s);
           //SMSForm.setGroupName(s);
   		SMSForm.setUserName("");
   		SMSForm.setUserMobileNumber("");
   	 SMSForm.setActionName(null);
   	 forward_Jsp="smsGroupCreate";
   		 
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("deleteMoreMembers"))
   	 {
   		System.out.println("Entered deleteMoreMembers... ");
   		HashMap map=null;
   		String[] trnsPrtyID= SMSForm.getHdnDeleteGrpMember().split(",");
   		 
   		 int count=SMSForm.getMapKeyCount()-1;
   		 map=SMSForm.getMapGroupMembers();
   		
   		 for(int i = 0 ;i < trnsPrtyID.length ;i++) {
                map.remove(trnsPrtyID[i]);
            }
   		 SMSForm.setMapGroupMembers(map);
   		 map=new HashMap();
            map=SMSForm.getMapGroupMembers();
   			
   		 for(int j = 0 ;j < trnsPrtyID.length ;j++) {
                logger.debug(trnsPrtyID[j]+" is removed...");
                map.remove(trnsPrtyID[j]);
   		 }
   		 SMSForm.setAddMoreCounter(map.size());
   		 SMSForm.setMapKeyCount(count);
   		 
   		 if(map.size()==0){
   			 SMSForm.setAddMoreCounter(0);
   		 }
   		 
   		//String name = new String(SMSForm.getGroupName().getBytes("ISO-8859-1"), "UTF-8");
        //System.out.println("name :-   " + name);
   		 //SMSForm.setGroupName(name);
   		 SMSForm.setMapGroupMembers(map);
   		 SMSForm.setActionName(null);
   		 forward_Jsp="smsGroupCreate";
   		 
   	 }	
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("saveGroupMembers"))
   	 {
   		System.out.println("saveGroupMembers... ");
   		HashMap map=null;
   		SMSAlertsDTO sAlertsDTO= new SMSAlertsDTO(); 
   		map = SMSForm.getMapGroupMembers();
   		//String name = new String(SMSForm.getUserName().getBytes("ISO-8859-1"), "UTF-8");
       // System.out.println("name :-   " + name);
   		sAlertsDTO.setUserName(SMSForm.getUserName());
   		sAlertsDTO.setUserMobileNumber(SMSForm.getUserMobileNumber());
   		//String s = new String(SMSForm.getGroupName().getBytes("ISO-8859-1"), "UTF-8");
        //System.out.println("S :-   " + s);
        //SMSForm.setGroupName(s);
   		String grpName = SMSForm.getGroupName();
   		int count=SMSForm.getMapKeyCount();
   		//Check if the group already exists
   		if((sAlertsDTO.getUserName()!=null || !sAlertsDTO.getUserName().isEmpty()) && (sAlertsDTO.getUserMobileNumber()!=null || !sAlertsDTO.getUserMobileNumber().isEmpty()))
   		{
			boolean check =false;
			check = SRObd.verifyGroupExists(grpName);
			if(check){
		        if(count==0)
		       	 count=1;
		        
		        if(map.containsKey(Integer.toString(count))){
		       	 count++;
		       	 map.put(Integer.toString(count), sAlertsDTO);
		            }
		        else
		       	 map.put(Integer.toString(count), sAlertsDTO);
		             
		        boolean flag= false;
		        flag= SRObd.createGroup(SMSForm,userId);
				 if(map.size()==0){
					 SMSForm.setAddMoreCounter(0);
				 }
				 
				 if(flag)
					 SMSForm.setSaveGrpSuccess(1);
				 else{
					 SMSForm.setSaveGrpSuccess(3);
				 }
			 }
			 else{
				SMSForm.setSaveGrpSuccess(2);
			 }
   		 }
   		 SMSForm.setMapKeyCount(count);
         SMSForm.setAddMoreCounter(map.size());
         //SMSForm.setGroupName(s);
   		 SMSForm.setUserName("");
   		 SMSForm.setUserMobileNumber("");
   		 SMSForm.setMapGroupMembers(map);
   		 SMSForm.setActionName(null);
   	     forward_Jsp="smsGroupCreate";
   		 
   	     }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("submitSearch"))
   	 {
   		 logger.debug("submitSearch... ");
   		 SMSAlertsDTO smAlertsDTO = new SMSAlertsDTO();
   		 String fromDt= SMSForm.getDurationFrom();
   		 String toDt= SMSForm.getDurationTo();
   		 boolean flag= false;
   		 ArrayList dtls = new ArrayList();
   		 dtls= SRObd.searchEditDetails(fromDt,toDt,userId); 
   		 System.out.println("dtls"+dtls);
     		 //SMSForm.setGroupInfo(dtls);
   		// System.out.println(SMSForm.getGroupInfo().size());
   		 HashMap<String, SMSAlertsDTO> grpMap = new HashMap<String, SMSAlertsDTO>();
   		 
   		 if(dtls!=null && !dtls.isEmpty()){
   				if(dtls.size()>0 && dtls!=null){
   					for(int p=0;p<dtls.size();p++){
   						smAlertsDTO=(SMSAlertsDTO) dtls.get(p);
   						dto.setGroupName(smAlertsDTO.getGroupName());
   						dto.setCreatedDate(smAlertsDTO.getCreatedDate());
   						grpMap.put(dto.getGroupName(), smAlertsDTO);
   					}
   				}
   				SMSForm.setGroupListCounter(1);
   		    }
   		 else{
   			 SMSForm.setGroupListCounter(2);
   		 }
   		 SMSForm.setGroupInfo(grpMap);
   		 SMSForm.setGroupMemberCounter(0);
   		 SMSForm.setActionName(null);
   		 forward_Jsp="smsGroupEdit";	
   		 
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("deleteGroup"))
   	 {
   		 logger.debug("deleteGroup... ");
   		HashMap map=null;
   		String[] trnsPrtyID= SMSForm.getHdnDeleteGroup().split(",");
   		 
   		 int count=SMSForm.getMapKeyCount()-1;
   		 //SMSForm.setGroupInfo(map);
            map=SMSForm.getGroupInfo();
   			
   		 for(int j = 0 ;j < trnsPrtyID.length ;j++){
                logger.debug(trnsPrtyID[j]+" is removed...");
                map.remove(trnsPrtyID[j]);
   		 }
   		 SMSForm.setAddMoreCounter(map.size());
   		 SMSForm.setMapKeyCount(count);
   		 
   		 if(map.size()==0){
   			 SMSForm.setAddMoreCounter(0);
   		 }
   		 
   		 //Delete group
   		 boolean flag=false;
   		 flag = SRObd.deleteGroup(map,trnsPrtyID); 
   		 
   		 SMSForm.setGroupMembersInfo(new HashMap());
   		 SMSForm.setGroupMemberCounter(0);
   		 SMSForm.setGroupInfo(map);
   		 SMSForm.setActionName(null);
   	     forward_Jsp="smsGroupEdit";
   		 
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("deleteGroupMember"))
   	 {
   		 logger.debug("deleteGroupMember... ");
   		String checkBoxSize[] = SMSForm.getCheckBoxFlag().split(",");
   		if(SMSForm.getCheckBoxFlag()!=null && SMSForm.getCheckBoxFlag().contains(","))
   		{
   			boolean flag=false;
   			boolean updateData = SRObd.deleteGroupMembers(SMSForm,userId,checkBoxSize);
   			if(updateData){
   				dto.setErrorFlag("DeleteN");
   			}
   			else{
   				dto.setErrorFlag("DeleteY");
   			}
   		}
   		else
   		{
   			boolean flag=false;
   			boolean updateData = SRObd.deleteGroupMembers(SMSForm,userId,checkBoxSize);
   			if(updateData){
   				dto.setErrorFlag("DeleteN");
   			}
   			else{
   				dto.setErrorFlag("DeleteY");
   			}
   		}
   		SMSForm.setSaveGrpSuccess(0);
   		SMSForm.setActionName(null);
   	    forward_Jsp="smsGroupMembersEdit";
   		 
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("saveMemberDetails"))
   	 {
   		logger.debug("saveMemberDetails... ");
   		String[] memberStr= SMSForm.getCheckBoxFlag().split(",");
   		if(SMSForm.getCheckBoxFlag()!=null && SMSForm.getCheckBoxFlag().contains(","))
   		{
   			boolean updateData = SRObd.updateMembersData(SMSForm,userId,memberStr);
   			if(updateData){
   				dto.setErrorFlag("UpdateN");
   			}
   			else{
   				dto.setErrorFlag("UpdateY");
   			}  			
   		}
   		else
   		{
   		 boolean updateData = SRObd.updateMembersData(SMSForm,userId,memberStr);
   		 if(updateData){
   			 dto.setErrorFlag("UpdateN");
   		 }
   		 else{
   			 dto.setErrorFlag("UpdateY");
   		 }
   		}
   		SMSForm.setSaveGrpSuccess(0);
   		SMSForm.setActionName(null);
   		forward_Jsp="smsGroupMembersEdit";
   		
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("SystemUsers"))
   	 {
   		SMSForm.setActionName(null);
   		SMSForm.setSystemUsers(1);
   		SMSForm.setNonSystemUsers(0);
   		SMSForm.setStateCounter(0);
   		SMSForm.setZoneCounter(0);
   		SMSForm.setDistrictCounter(0);
   		SMSForm.setSmsSendSuccess(0);
   		SMSForm.setSmsHindiContent("");
   		SMSForm.setCheckBoxFlag("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		SMSForm.setRoleViewCounter(2);
   		SMSForm.setGroupMemberId("");
   		SMSForm.setUserTypes("");
   		SMSForm.setDeleteTrnsPrtyRole("");
   		SMSForm.setDeleteTrnsPrtyDist("");
   		SMSForm.setDeleteTrnsPrtyZone("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		SMSForm.setHdnDeleteGroup("");
   		SMSForm.setHdnDeleteGrpMember("");
   		SMSForm.setHdnDistrictList("");
   		SMSForm.setHdnRoleList("");
   		SMSForm.setHdnZoneList("");
   		forward_Jsp="smsGroupSend";	
   		return mapping.findForward(forward_Jsp);
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("NonSystemUsers"))
   	 {
   		ArrayList nonSysUserList = new ArrayList();
   		nonSysUserList=SMSAlertsBD.getNonSystemUserList(lang);
   		SMSAlertsDTO tempDto = new SMSAlertsDTO();
   		LinkedHashMap nonUserMap = new LinkedHashMap();
   		for(int i=0;i<nonSysUserList.size();i++){
   			tempDto=(SMSAlertsDTO) nonSysUserList.get(i);
   			dto.setGroupId(tempDto.getGroupId());
   			dto.setGroupName(tempDto.getGroupName());
   			nonUserMap.put(tempDto.getGroupId(), tempDto);
   		}
   		SMSForm.setNonSystemUserList(nonUserMap);
   		SMSForm.setActionName(null);
   		SMSForm.setNonSystemUsers(1);
   		SMSForm.setSystemUsers(0);
   		SMSForm.setSmsSendSuccess(0);
   		SMSForm.setSmsHindiContent("");
   		SMSForm.setCheckBoxFlag("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		SMSForm.setDeleteTrnsPrtyRole("");
   		SMSForm.setDeleteTrnsPrtyDist("");
   		SMSForm.setDeleteTrnsPrtyZone("");
   		SMSForm.setGroupMemberId("");
   		forward_Jsp="smsGroupSend";	
   		return mapping.findForward(forward_Jsp);
   		
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("getStateSMSGrp"))
   	 {
   		 
   		 
   		ArrayList zoneList = new ArrayList();
   		zoneList=SMSAlertsBD.getSMSZone(lang);
   		SMSAlertsDTO tempDto = new SMSAlertsDTO();
   		LinkedHashMap zoneMap = new LinkedHashMap();
   		for(int i=0;i<zoneList.size();i++){
   			tempDto=(SMSAlertsDTO) zoneList.get(i);
   			dto.setZoneId(tempDto.getZoneId());
   			dto.setZoneName(tempDto.getZoneName());
   			dto.setZoneFlag("");
   			zoneMap.put(tempDto.getZoneId(), tempDto);
   		}
   		dto.setZoneList(zoneList);
   		SMSForm.setSmsAlertsDTO(dto);
   		
   		SMSForm.setZoneFlag("");
   		SMSForm.setMapZoneList(zoneMap);
   		SMSForm.setZoneList(zoneList); 
   		SMSForm.setHdnDistrictList("");
   		SMSForm.setHdnZoneList("");
   		SMSForm.setHdnRoleList("");
   		SMSForm.setActionName(null);
   		SMSForm.setNonSystemUsers(0);
   		SMSForm.setSystemUsers(1);
   		SMSForm.setStateCounter(1);
   		SMSForm.setZoneCounter(1);
   		SMSForm.setDistrictCounter(0);
   		SMSForm.setSmsSendSuccess(0);
   		SMSForm.setRoleViewCounter(0);
   		SMSForm.setSmsHindiContent("");
   		SMSForm.setDeleteTrnsPrtyRole("");
   		SMSForm.setDeleteTrnsPrtyDist("");
   		SMSForm.setDeleteTrnsPrtyZone("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		forward_Jsp="smsGroupSend";	
   		return mapping.findForward(forward_Jsp);
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("getZoneSMSGrp"))
   	 {
   		ArrayList zoneList = new ArrayList();
   		zoneList=SMSAlertsBD.getSMSZone(lang);
   		SMSAlertsDTO tempDto = new SMSAlertsDTO();
   		LinkedHashMap zoneMap = new LinkedHashMap();
   		for(int i=0;i<zoneList.size();i++){
   			tempDto=(SMSAlertsDTO) zoneList.get(i);
   			dto.setZoneId(tempDto.getZoneId());
   			dto.setZoneName(tempDto.getZoneName());
   			zoneMap.put(tempDto.getZoneId(), tempDto);
   		}
   		
   		dto.setZoneList(zoneList);
   		SMSForm.setSmsAlertsDTO(dto);
   		
   		SMSForm.setMapZoneList(zoneMap);
   	 	SMSForm.setZoneList(zoneList);
   	 	SMSForm.setZoneFlag("");
   		SMSForm.setActionName(null);
   		SMSForm.setNonSystemUsers(0);
   		SMSForm.setSystemUsers(1);
   		SMSForm.setStateCounter(0);
   		SMSForm.setZoneCounter(1);
   		SMSForm.setDistrictCounter(0);
   		SMSForm.setRoleViewCounter(0);
   		SMSForm.setSmsSendSuccess(0);
   		SMSForm.setSmsHindiContent("");
   		SMSForm.setDeleteTrnsPrtyRole("");
   		SMSForm.setDeleteTrnsPrtyDist("");
   		SMSForm.setDeleteTrnsPrtyZone("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		SMSForm.setHdnDistrictList("");
   		SMSForm.setHdnZoneList("");
   		SMSForm.setHdnRoleList("");
   		forward_Jsp="smsGroupSend";	
   		return mapping.findForward(forward_Jsp);
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("getDistrictSMSGrp"))
   	 {
   		ArrayList districtList = new ArrayList();
   		
   		if(SMSForm.getUserTypes().equalsIgnoreCase("state")){
   			districtList = SMSAlertsBD.getDistrictSendGrp(lang,SMSForm,SMSForm.getHdnZoneList());
   			SMSForm.setStateCounter(1);
   			SMSForm.setZoneCounter(1);
   			SMSForm.setDistrictCounter(1);
   		}
   		if(SMSForm.getUserTypes().equalsIgnoreCase("zone")){
   			districtList = SMSAlertsBD.getDistrictSendGrp(lang,SMSForm,SMSForm.getHdnZoneList());
   			SMSForm.setStateCounter(0);
   			SMSForm.setZoneCounter(1);
   			SMSForm.setDistrictCounter(1);
   		}
   		if(SMSForm.getUserTypes().equalsIgnoreCase("district")){
   			districtList = SMSAlertsBD.getDistrictSendGrp(lang,SMSForm,SMSForm.getHdnZoneList());
   			SMSForm.setStateCounter(0);
   			SMSForm.setZoneCounter(0);
   			SMSForm.setDistrictCounter(1);
   		}
   		System.out.println("districtlist"+districtList.size());
   		SMSAlertsDTO tempDto = new SMSAlertsDTO();
   		LinkedHashMap districtMap = new LinkedHashMap();
   		LinkedHashMap zoneMap = new LinkedHashMap();
   		ArrayList zoneList = SMSForm.getZoneList();
   		for(int i=0;i<zoneList.size();i++){
   			tempDto=(SMSAlertsDTO) zoneList.get(i);
   			dto.setZoneId(tempDto.getZoneId());
   			dto.setZoneName(tempDto.getZoneName());
   			dto.setZoneFlag("A");
   			tempDto.setZoneFlag("A");
   			zoneMap.put(tempDto.getZoneId(), tempDto);
   		}
   		SMSForm.setMapZoneList(zoneMap);
   		for(int i=0;i<districtList.size();i++){
   			tempDto=(SMSAlertsDTO) districtList.get(i);
   			dto.setDistrictId(tempDto.getDistrictId());
   			dto.setDistrictName(tempDto.getDistrictName());
   			districtMap.put(tempDto.getDistrictId(), tempDto);
   		}
   		
   		
   		//Method for checkbox check
   		dto.setCheckedZone(new ArrayList());
   		dto.setZoneCheckList("");
   		SMSAlertsDTO dto1 = SMSForm.getSmsAlertsDTO();
   		ArrayList checkList= dto1.getZoneList();
   		String selecArray[]= SMSForm.getHdnZoneList().split(",");
   		for(int j=0;j<selecArray.length;j++)
   		{
			for(int i=0;i<checkList.size();i++)
			{
				SMSAlertsDTO dto2=(SMSAlertsDTO) checkList.get(i);
				if(selecArray[j].equalsIgnoreCase(dto2.getZoneId()))
				{
					dto2.setZoneCheckList(selecArray[j]);
					dto1.getCheckedZone().add(dto2);
				}
			}
   		}
   		
   		dto1.setDistrictList(districtList);
		SMSForm.setSmsAlertsDTO(dto1);
   		SMSForm.setDeleteTrnsPrtyZone(SMSForm.getHdnZoneList());
   		SMSForm.setZoneFlag("A");
   		SMSForm.setMapDistrictList(districtMap);
   		SMSForm.setDistrictList(districtList);
   		SMSForm.setActionName(null);
   		SMSForm.setNonSystemUsers(0);
   		SMSForm.setSystemUsers(1);
   		SMSForm.setSmsSendSuccess(0);
   		SMSForm.setRoleViewCounter(0);
   		SMSForm.setSmsHindiContent("");
   		SMSForm.setDeleteTrnsPrtyRole("");
   		SMSForm.setDeleteTrnsPrtyDist("");
   		//SMSForm.setDeleteTrnsPrtyZone("");
   		SMSForm.setDeleteTrnsPrtyID("");
   		SMSForm.setHdnDistrictList("");
   		//SMSForm.setHdnZoneList("");
   		SMSForm.setHdnRoleList("");
   		forward_Jsp="smsGroupSend";	
   		return mapping.findForward(forward_Jsp);
   	 }
   		
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("getRolesForSystemUsers"))
   	 {
   		 logger.debug("getRolesForSystemUsers... ");
   		 if(SMSForm.getUserType().equalsIgnoreCase("S")){
   			 ArrayList roleList = new ArrayList();
   			 String stateId=""; String zoneId=""; String districtId="";
   			 if(SMSForm.getUserTypes().equalsIgnoreCase("state")){
   				 stateId="1";
   			 }
   			 else if(SMSForm.getUserTypes().equalsIgnoreCase("zone")){
   				 zoneId="1";
   			 }
   			 else if (SMSForm.getUserTypes().equalsIgnoreCase("district")){
   				 districtId="1";
   			 }
   			roleList = SMSAlertsBD.getRoleGroup(SMSForm,lang,stateId,zoneId,districtId);
   			System.out.println("roleList"+roleList.size());
   			SMSAlertsDTO tempDto = new SMSAlertsDTO();
   			LinkedHashMap roleMap = new LinkedHashMap();
   			LinkedHashMap zoneMap = new LinkedHashMap();
   			LinkedHashMap districtMap = new LinkedHashMap();
   			String roleName="";
   			for(int i=0;i<roleList.size();i++){
   				tempDto=(SMSAlertsDTO) roleList.get(i);
   				
   				dto.setZoneName(tempDto.getZoneName());
   				dto.setDistrictName(tempDto.getDistrictName());
   				dto.setOfficeId(tempDto.getOfficeId());
   				roleName= tempDto.getRoleName();
   				dto.setRoleName(tempDto.getOfficeName()+" - " + tempDto.getRoleName());
   				tempDto.setRoleName(dto.getRoleName());
   				dto.setZoneFlag("A");
   				tempDto.setZoneFlag("A");
   				dto.setDistrictFlag("A");
   				tempDto.setDistrictFlag("A");
   				roleMap.put(tempDto.getOfficeId()+"#"+tempDto.getOfficeName()+"#"+roleName, tempDto);
   			}
   			SMSForm.setMapRoleList(roleMap);
   			ArrayList zoneList = SMSForm.getZoneList();
   			for(int i=0;i<zoneList.size();i++){
   				tempDto=(SMSAlertsDTO) zoneList.get(i);
   				dto.setZoneId(tempDto.getZoneId());
   				dto.setZoneName(tempDto.getZoneName());
   				dto.setZoneFlag("A");
   				tempDto.setZoneFlag("A");
   				zoneMap.put(tempDto.getZoneId(), tempDto);
   			}
   			SMSForm.setMapZoneList(zoneMap);
   			SMSForm.setZoneFlag("A");
   			
   			ArrayList districtList = SMSForm.getDistrictList();
   			
   			for(int i=0;i<districtList.size();i++){
   				tempDto=(SMSAlertsDTO) districtList.get(i);
   				dto.setDistrictId(tempDto.getDistrictId());
   				dto.setDistrictName(tempDto.getDistrictName());
   				dto.setDistrictFlag("A");
   				tempDto.setDistrictFlag("A");
   				districtMap.put(tempDto.getDistrictId(), tempDto);
   			}
   			
   			//Method for checkbox check
   	   		dto.setCheckedDistrict(new ArrayList());
   	   		dto.setDistrictCheckList("");
   	   		SMSAlertsDTO dto1 = SMSForm.getSmsAlertsDTO();
   	   		ArrayList checkList= dto1.getDistrictList();
   	   		String selecArray[]= SMSForm.getHdnDistrictList().split(",");
   	   		for(int j=0;j<selecArray.length;j++)
   	   		{
   				for(int i=0;i<checkList.size();i++)
   				{
   					SMSAlertsDTO dto2=(SMSAlertsDTO) checkList.get(i);
   					if(selecArray[j].equalsIgnoreCase(dto2.getDistrictId()))
   					{
   						dto2.setDistrictCheckList(selecArray[j]);
   						dto1.getCheckedDistrict().add(dto2);
   					}
   				}
   	   		}
   	   		
   			SMSForm.setSmsAlertsDTO(dto1);
   			
   			SMSForm.setMapDistrictList(districtMap);
   			SMSForm.setDistrictFlag("A");
   			SMSForm.setRoleViewCounter(1);
   			SMSForm.setActionName(null);
   			SMSForm.setNonSystemUsers(0);
   			SMSForm.setSystemUsers(1);
   			//SMSForm.setStateCounter(0);
   			//SMSForm.setZoneCounter(0);
   			//SMSForm.setDistrictCounter(0);
   			SMSForm.setSmsSendSuccess(0);
   		 }
   			forward_Jsp="smsGroupSend";	
   			return mapping.findForward(forward_Jsp);
   	 }
   	
   	 else if(SMSForm.getActionName()!=null && SMSForm.getActionName().equals("sendSMStoGroup"))
   	 {
   		 System.out.println("Entered sendSMStoGroup... ");
   	 	 int val=1;
   	 	 
   	 	 if(SMSForm.getUserType().equalsIgnoreCase("S")){
   	 		if(SMSForm.getSystemUsers() == val){
   				 ArrayList memberList = new ArrayList();
   				 memberList = SMSAlertsBD.getNSMemberList(lang,SMSForm,SMSForm.getUserType(),SMSForm.getHdnDeleteGrpMember());
   				 SMSAlertsDTO tempDto = new SMSAlertsDTO();
   				 System.out.println("memberList"+memberList.size());
   				 String mobNumbers="";
   				 for(int i=0;i<memberList.size();i++){
   					 tempDto=(SMSAlertsDTO) memberList.get(i);
   					 if(i==0)
   						mobNumbers=mobNumbers+tempDto.getUserMobileNumber();
   					 else{
   						if(tempDto.getUserMobileNumber()!="" && tempDto.getUserMobileNumber() !=null && tempDto.getUserMobileNumber()!=" ") 
   							mobNumbers=mobNumbers+","+tempDto.getUserMobileNumber();
   					 }
   				 }
   				 SMSForm.setUserMobileNumber(mobNumbers);
   			 }
   	 	 }
   	 	 
   		 if(SMSForm.getUserType().equalsIgnoreCase("NS")){
   			 if(SMSForm.getNonSystemUsers() == val){
   				 ArrayList memberList = new ArrayList();
   				 memberList = SMSAlertsBD.getNSMemberList(lang,SMSForm,SMSForm.getUserType(),SMSForm.getHdnDeleteGrpMember());
   				 SMSAlertsDTO tempDto = new SMSAlertsDTO();
   				 System.out.println("memberList"+memberList.size());
   				 String mobNumbers="";
   				 for(int i=0;i<memberList.size();i++){
   					 tempDto=(SMSAlertsDTO) memberList.get(i);
   					 if(i==0)
   						mobNumbers=mobNumbers+tempDto.getUserMobileNumber();
   					 else
   						mobNumbers=mobNumbers+","+tempDto.getUserMobileNumber();	
   				 }
   				 SMSForm.setUserMobileNumber(mobNumbers);
   			 }
   		 }
   		 
   		 //String s = new String(SMSForm.getSmsHindiContent().getBytes("ISO-8859-1"), "UTF-8");
   		 //System.out.println("S :-   " + s);
   		 //SMSForm.setSmsHindiContent(s);
   		 
   		 //Message sending
   		 SendSMSAlertsAction sendSMSObj = new SendSMSAlertsAction();
   		 sendSMSObj.call(SMSForm, userId);
   		 
   		/* 
   		 String status = sendSMSObj.call(SMSForm);
   		 if(status.equalsIgnoreCase("Completed"))
   		 	 SMSForm.setSmsSendSuccess(1);
   		 */
   		 
   		 SMSForm.setSmsSendSuccess(1);
   		 SMSForm.setActionName(null);
   		 forward_Jsp="smsGroupSend";	
   		 return mapping.findForward(forward_Jsp);
   	 }
     }
    }

    return mapping.findForward(forward_Jsp);
  }
}
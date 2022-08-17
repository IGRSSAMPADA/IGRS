/**
 * ===========================================================================
 * File           :   IntimationTxnAction.java
 * Description    :   Represents the Action Class

 * Author         :   Nithya
 * Created Date   :   Nov 28, 2007
 * Updated By	  :   Imran Shaik
 * Updated Data	  :   Oct 21,2008 
 * ===========================================================================
 */

package com.wipro.igrs.intimationtransaction.action;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.intimationtransaction.bd.IntimationTransactionBD;
import com.wipro.igrs.intimationtransaction.constant.CommonConstant;
import com.wipro.igrs.intimationtransaction.dto.IntimationTxnDetailsDTO;
import com.wipro.igrs.intimationtransaction.dto.PaymentDTO;
import com.wipro.igrs.intimationtransaction.form.IntimationTxnActionForm;
import com.wipro.igrs.intimationtransaction.rule.IntimationTransactionRule;
//import com.wipro.igrs.propertylock.form.PropertyLockingForm;


public class IntimationTxnAction extends BaseAction {

    private static Logger log = org.apache.log4j.Logger.getLogger(IntimationTxnAction.class);
    String FORWARD_JSP = new String("intimationtxnreq");
 //    String FORWARD_JSP=""; 

    /** 
     * @see org.apache.struts.action.Action#execute(ActionMapping, ActionForm, 
     * HttpServletRequest, HttpServletResponse)
     */

    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws Exception {

        log.debug("***you are in action class intial step****"+form);
        IntimationTransactionBD bd = new IntimationTransactionBD();
        //IntimationTxnDetailsDTO dto=new IntimationTxnDetailsDTO();
        IntimationTxnActionForm intimationTxnActionForm = (IntimationTxnActionForm) form ;
        IntimationTxnDetailsDTO dto=new IntimationTxnDetailsDTO();
        log.debug("you are in action class if part");
        dto=intimationTxnActionForm.getIntimationDTO();
        
        String language = (String)session.getAttribute("languageLocale");
        dto.setLanguage(language);
        String activityId = (String)request.getParameter("actId");
        
        String pymntParamStatus = (String)request.getParameter("paymentStatus");
        log.debug("**************payment status***"+pymntParamStatus);
     //   HttpSession session = request.getSession();
        String feeVal = null;
        String otherFeeVal = null;
        String userType = null;
        String serviceId = null;
        String requestParam = null;
		String viewParam = null;
		String refId = null;
		String refID = null;
		String age_inti=null;
		ArrayList listIntimationType = null;
		ArrayList listPhotoId = null;
		ArrayList listCountry = null;
		ArrayList listState = null;
		ArrayList listDistrict = null;
	
	 requestParam = (String) request.getParameter("request");
	 viewParam = (String) request.getParameter("view");
	 String fromPayment = (String)session.getAttribute("forwardPath");
	 
	 float total = 0;
     String roleId = (String)session.getAttribute("role");
     String funId = (String)session.getAttribute("functionId");
     String userId = (String)session.getAttribute("UserId");
     if(activityId!=null&&!activityId.equalsIgnoreCase(""))
     {
     	new IGRSCommon().saveactivitylog(userId, activityId);
     }
     log.debug("*****userId*****************"+userId);
     String ofcId=(String)session.getAttribute("loggedToOffice");
     intimationTxnActionForm.getIntimationDTO().setOfficeId(ofcId);
     String roleID=(String)session.getAttribute("loggedInRole");
     

     log.debug("*****officeId*****************"+ofcId);
	 log.debug("*****roleid*****************"+roleID);
	 String page=null;
	 page=request.getParameter("pageName");
	 if(page!=null)
	 {
		 if(page.equalsIgnoreCase("dashboard"))
		 {
		    ArrayList pendingApplicationList = bd.getPendingApps(dto,userId);
     		log.debug("--------------->"+pendingApplicationList.size());
				if(pendingApplicationList.size()==0)
					intimationTxnActionForm.getIntimationDTO().setPendingApps(null);
				else
					intimationTxnActionForm.getIntimationDTO().setPendingApps(pendingApplicationList);
				request.setAttribute("pendingApplicationList", pendingApplicationList);  
			 
			 return mapping.findForward("dashscreenInti");
		 }
	 
	 
	 }
	 
	 log.debug("*********view modify******************"+CommonConstant.INTIMATION_TXN_VIEW_MODIFY_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnModifyAction()));
	 
	 //To modify after view
     if (CommonConstant.INTIMATION_TXN_VIEW_MODIFY_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnModifyAction())) {
      	IntimationTxnActionForm frm =(IntimationTxnActionForm) session.getAttribute("intimationForm");
          dto=frm.getIntimationDTO();
          String tID=dto.getTxnId();
          log.debug("*******txnid in modify after view*********"+tID);
         // dto.setTxnId(tID);
          ArrayList details = new ArrayList();
     	 details  = bd.getDetailsOfApplicant(tID,language);
     	 if (details.size() == 0)
     	 {
     		 intimationTxnActionForm.getIntimationDTO().setDetailsList(null);
     	 }
     	 else
     	 {
     	 
     	 intimationTxnActionForm.getIntimationDTO().setDetailsList(details);
     	 intimationTxnActionForm.setIntimationDTO((IntimationTxnDetailsDTO) details.get(0));
     	 }
     	 
     	
  
      	
     
     	request.setAttribute("tranID",tID); 	
      	 request.setAttribute("intimationtransaction",intimationTxnActionForm);
           FORWARD_JSP = CommonConstant.INTIMATION_MODIFY_REQ;
      }
      

	 
	 
	 
	 
	 log.debug("*****************viewsubmit***********8"+CommonConstant.INTIMATION_VIEW_SUBMIT_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationViewSubmitAction()));
	
	 // To get the intimtaion list corresponding to the refid,status,fromdate and todate.				 
        if (CommonConstant.INTIMATION_VIEW_SUBMIT_ACTION.equalsIgnoreCase((intimationTxnActionForm.getIntimationDTO().getIntimationViewSubmitAction()))) {
     
        	
        	
        	log.debug("Actions are matched for Intimation View Details :-  ");
           
          
             dto = intimationTxnActionForm.getIntimationDTO();
             String txnId=dto.getTxnId();
             ArrayList details = bd.getIntimationSearch(dto,txnId,language);
              
         	log.debug("roopam---> "+details);

             
              if (details==null || details.size() == 0)
         	 {
            	  dto.setNotFound("0");
         		 intimationTxnActionForm.getIntimationDTO().setDetailsList(null);
         		 
         	 }
         	 else
         	 {
         		 dto.setNotFound("1");
         		 dto.setDetailsList(details);
         	 //intimationTxnActionForm.getIntimationDTO().setDetailsList(details);
         	 //intimationTxnActionForm.setIntimationDTO((IntimationTxnDetailsDTO) details.get(0));
         	 }
         	
             
            log.debug("hai");
            
            log.debug("*****txnid in view********"+dto.getIntId());
            if(dto.getIntId()!=null && !dto.getIntId().equalsIgnoreCase(""))
            {
            	dto.setTxnId(dto.getIntId());
            }
            
           // request.setAttribute("intimation_view_list",list);
            intimationTxnActionForm.setIntimationDTO(dto);
            request.setAttribute("intimationForm",intimationTxnActionForm);
            log.debug("************************"+dto.getNotFound());
            FORWARD_JSP = CommonConstant.INTIMATION_VIEW_SUBMIT;     
            
           return mapping.findForward(CommonConstant.INTIMATION_VIEW_SUBMIT);
        }   

	 
	
     
	//after click on any pending application id from dashboard
	 if (request.getParameter("txnId") != null) {
		
    	 String tranxId=request.getParameter("txnId") ;
    	 session.setAttribute("txnID", tranxId);
    	 intimationTxnActionForm.getIntimationDTO().setTransactionID(tranxId);
    	 log.debug("********************txnid="+intimationTxnActionForm.getIntimationDTO().getTransactionID());
    	 
    	 String value = intimationTxnActionForm.getIntimationDTO().getTransactionID();
    	 
    	 
    	 ArrayList details = new ArrayList();
    	 details  = bd.getDetailsOfApplicant(tranxId,language);
    	 if (details.size() == 0)
    	 {
    		 intimationTxnActionForm.getIntimationDTO().setDetailsList(null);
    	 }
    	 else
    	 {
    	 
    	 intimationTxnActionForm.getIntimationDTO().setDetailsList(details);
    	 
    	 }
    	 
    	 intimationTxnActionForm.setIntimationDTO((IntimationTxnDetailsDTO) details.get(0));
    //	 intimationTxnActionForm.setIntimationDTO(dto);
    	// System.out.println(dto.getCountry());
     //    System.out.println(dto.getState());
      //   System.out.println(dto.getCity());
         String coun=bd.getCountryName(dto.getCountry(),language);
         dto.setCountryName(bd.getCountryName(dto.getCountry(),language));
         dto.setStateName(bd.getStateName(dto.getState(),language));
         dto.setCityName(bd.getDistrictName(dto.getCity(),language));
  //       log.debug("************photo id type in next action***********"+dto.getPhotoIdType());
         dto.setPhotoIdTypeName(bd.getPhotoIdName(dto.getPhotoIdType(),language));

    //	 intimationTxnActionForm.getIntimationDTO().setDetailsList(details);
    	 request.setAttribute("transactionid",tranxId);
    	// request.setAttribute("transactionid",intimationTxnActionForm);
    	 request.setAttribute("intimationtransaction",intimationTxnActionForm);
         return mapping.findForward("INTIMATIONREQDASHBOARD");

     }  
     
     
	 
	 
		 if(fromPayment!=null){
	     //return from payment action
	     if(fromPayment.equalsIgnoreCase("/IntimationTransaction.do"))
	     intimationTxnActionForm.getIntimationDTO().setIntimationTxnSuccessAction(CommonConstant.INTIMATION_TXN_SUCCESS_ACTION);
	     session.setAttribute("forwardPath","");
	 }
//	 log.debug("**********************requestParam="+requestParam);
	// log.debug("**********************viewParam="+viewParam);
      /*  float total = 0;
	      String roleId = (String)session.getAttribute("role");
	      String funId = (String)session.getAttribute("functionId");
	      String userId = (String)session.getAttribute("UserId");    */
		if(funId ==  null) {
		    funId = "FUN_010";
		}
		userType = (String)session.getAttribute("role");
	//	log.debug("*****userType*****************"+userType);
		if(userType ==  null) {
		    userType = "SRO";
		}
	//setting user id in intimation form dto
	intimationTxnActionForm.getIntimationDTO().setUserId(userId);
            listIntimationType = bd.getIntimationType(funId, userId);
            request.setAttribute("intimationtype",listIntimationType);
        //setting type of intimation functions in sessions
            ArrayList typeOfIntimation = bd.getTypeOfIntimation(language);
            intimationTxnActionForm.getIntimationDTO().setTypeOfIntimation(typeOfIntimation);
            request.setAttribute("intimationDTO",intimationTxnActionForm.getIntimationDTO());
            listPhotoId = bd.getIDProof(language);
            intimationTxnActionForm.setIntimationPhotoIdType(listPhotoId);
            
            
            
            feeVal = bd.getFee(funId)==null?"0.0":bd.getFee(funId);        
            
            if(feeVal != null)
    			if(!feeVal.equalsIgnoreCase(""))
    				intimationTxnActionForm.getIntimationDTO().setFee(feeVal);
    		otherFeeVal = bd.getOthersFeeDuty(funId, serviceId, userType)==null?"0.0":bd.getOthersFeeDuty(funId, serviceId, userType);
    //		log.debug("IntimationAction -- otherFeeVal ="+otherFeeVal);
    		if(otherFeeVal != null) {
    		    intimationTxnActionForm.getIntimationDTO().setOtherFee(otherFeeVal);
    		total = Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getFee()==null?"0.0":
    			intimationTxnActionForm.getIntimationDTO().getFee()) + 
    				Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getOtherFee()==null?"0.0":
    				intimationTxnActionForm.getIntimationDTO().getOtherFee());
    		intimationTxnActionForm.getIntimationDTO().setTotalFee(String.valueOf(total));
   // 		log.debug("IntimationAction -- total ="+total);
    		}  		
    		 
    		
             
            log.debug("Values are loaded from the database.");
           
            request.setAttribute("fee",feeVal);
            request.setAttribute("otherFee",otherFeeVal);
            request.setAttribute("totalFee",String.valueOf(total));
            
            
            String frwdName=request.getParameter("fwdName");
            
            if("P".equalsIgnoreCase(pymntParamStatus)){
            	String txn_Id="";
      		   System.out.println(intimationTxnActionForm.getIntimationDTO().getTransactionID());
      		   if(session.getAttribute("txnID")!=null){
      		 txn_Id=(String)session.getAttribute("txnID");
      		   }
      		 if(session.getAttribute("newTXNID")!=null){
          		 txn_Id=(String)session.getAttribute("newTXNID");
          		   }
     		   //String txn_Id=request.getParameter("txnID");
     		  
     //		   log.debug("********transaction id in action***********"+txn_Id);
            	dto.setTxnId(txn_Id);
            	double blnce=bd.isCompletePayment(dto);
            	if(txn_Id!=null)
            	{
            	if(blnce<=0){
            		
            		String successMsg="";
         //           log.debug("*******blnce in action)********"+blnce);
                    successMsg=bd.updatePaymentInfo(dto);

                    if("succ".equalsIgnoreCase(successMsg))

                            {

                                    request.setAttribute("refId",dto.getTxnId());

                            request.setAttribute("intimationForm",intimationTxnActionForm);

                           FORWARD_JSP = CommonConstant.INTIMATION_REQ_CONFIRM;         		
            	}
            
            	}       

            	}
         
            }
            intimationTxnActionForm.getIntimationDTO().getAge();
            
         
            IntimationTxnDetailsDTO dto1=new IntimationTxnDetailsDTO();
            listCountry = bd.getCountry(language);
            intimationTxnActionForm.setIntimationCountry(listCountry);
            intimationTxnActionForm.getIntimationDTO().getCountry();
          
            listState = bd.getState(intimationTxnActionForm.getIntimationDTO().getCountry(),language);
            intimationTxnActionForm.setIntimationState(listState);
            ArrayList district= new ArrayList();
	    	district = bd.getCity(intimationTxnActionForm.getIntimationDTO().getState(),language);
            String coId=request.getParameter("selectedCountry");
    //		log.debug("*******************************coid="+coId);
   //         log.debug("********************************countryid="+ intimationTxnActionForm.getIntimationDTO().getCountry());
    //        log.debug("********************************statelist="+listState);
            
           intimationTxnActionForm.setIntimationCity(district);
          String s= request.getParameter("fwdName");
    //      System.out.println(s);
          if ("state".equalsIgnoreCase(s))
           {
        	  // listCountry = bd.getCountry();
        	  // intimationTxnActionForm.setIntimationCountry(listCountry);
         String cID=  intimationTxnActionForm.getIntimationDTO().getCountry();   
     //    log.debug("*******************************cid="+cID);
         intimationTxnActionForm.getIntimationDTO().setStateList(bd.getState(cID,language));
         
         
         // IntimationTxnActionForm intiTxnActionForm =(IntimationTxnActionForm) request.getAttribute("intimationForm"); 
         
		//IntimationTxnDetailsDTO  dto = intiTxnActionForm.getIntimationDTO();
          
        // request.setAttribute("intimationForm",intimationTxnActionForm);  
   //      log.debug("********intimationTxnActionForm="+intimationTxnActionForm);
         session.setAttribute("intimationtransaction",intimationTxnActionForm);
         request.setAttribute("fee",feeVal);
         request.setAttribute("otherFee",otherFeeVal);
         request.setAttribute("totalFee",String.valueOf(total));
           FORWARD_JSP = CommonConstant.TXN_DETAILS;   
           return mapping.findForward(FORWARD_JSP);
           }
          
          
          
          String s1= request.getParameter("fwdName");
          if ("city".equalsIgnoreCase(s1))
           {
               String cID=  intimationTxnActionForm.getIntimationDTO().getState();   
               intimationTxnActionForm.getIntimationDTO().setDistrictList(bd.getState(cID,language));
               session.setAttribute("intimationtransaction",intimationTxnActionForm); 
               request.setAttribute("fee",feeVal);
               request.setAttribute("otherFee",otherFeeVal);
               request.setAttribute("totalFee",String.valueOf(total));
               FORWARD_JSP = CommonConstant.TXN_DETAILS;   
               
               return mapping.findForward(FORWARD_JSP);
           }
          
          
            
 /*   	    feeVal = bd.getFee(funId)==null?"0.0":bd.getFee(funId);        
                      
            if(feeVal != null)
    			if(!feeVal.equalsIgnoreCase(""))
    				intimationTxnActionForm.getIntimationDTO().setFee(feeVal);
    		otherFeeVal = bd.getOthersFeeDuty(funId, serviceId, userType)==null?"0.0":bd.getOthersFeeDuty(funId, serviceId, userType);
    		log.debug("IntimationAction -- otherFeeVal ="+otherFeeVal);
    		if(otherFeeVal != null) {
    		    intimationTxnActionForm.getIntimationDTO().setOtherFee(otherFeeVal);
    		total = Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getFee()==null?"0.0":
    			intimationTxnActionForm.getIntimationDTO().getFee()) + 
    				Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getOtherFee()==null?"0.0":
    				intimationTxnActionForm.getIntimationDTO().getOtherFee());
    		intimationTxnActionForm.getIntimationDTO().setTotalFee(String.valueOf(total));
    		log.debug("IntimationAction -- total ="+total);
    		}  		
    		
    		
             
            log.debug("Values are loaded from the database.");
           
            request.setAttribute("fee",feeVal);
            request.setAttribute("otherFee",otherFeeVal);
            request.setAttribute("totalFee",String.valueOf(total));                   */
    		
    	      
        if (viewParam!=null) {
            FORWARD_JSP = CommonConstant.INTIMATION_VIEW;
        }
        if (requestParam!=null) {
            FORWARD_JSP = CommonConstant.INTIMATION_REQ;
        }
        
        
        log.debug("********modify next n view**********"+CommonConstant.INTIMATION_TXN_ACTION_FORM.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnActionForm()));
       //To go to next after modifying
        if (CommonConstant.INTIMATION_TXN_ACTION_FORM.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnActionForm())) {
            String actionForm = intimationTxnActionForm.getIntimationDTO().getIntimationTxnSubmitAction();
            log.debug("submitmodify="+actionForm);
             dto = intimationTxnActionForm.getIntimationDTO();
            PaymentDTO pdto = intimationTxnActionForm.getPayDTO();
            log.debug("if1");
            if ("submitIntimationModifyInfo".equalsIgnoreCase(actionForm)) {
                log.debug("if2");
                IntimationTransactionRule rule = new IntimationTransactionRule();
               // ArrayList errorList = rule.validateIntimationTransactionRule(intimationTxnActionForm);
                IntimationTxnActionForm frm =(IntimationTxnActionForm)form;
           
                dto = frm.getIntimationDTO();
                 bd=new IntimationTransactionBD();
                 String tID=dto.getTranID();
                 dto.setTxnId(tID);
             //    log.debug("*********txn id after modify*****"+tID);
            //     log.debug("*********txn id after modify*****"+dto.getTxnId());
                 
                 if(dto.getTxnId()!=null &&!dto.getTxnId().equalsIgnoreCase(""))
                 {
                	 refId= bd.updateIntimDetails(dto,tID);
                	 dto.setTxnId(refId);
                 }
                 request.setAttribute("intimationForm",intimationTxnActionForm);
                 FORWARD_JSP = CommonConstant.INTIMATION_NEXT_CONFIRM;

            }
        
        }
                 
                 
                 
        //To View the Intimation Txn Request Details
        if (CommonConstant.INTIMATION_TXN_ACTION_FORM.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnActionForm())) {
            String actionForm = intimationTxnActionForm.getIntimationDTO().getIntimationTxnSubmitAction();
            log.debug("submit="+actionForm);
             dto = intimationTxnActionForm.getIntimationDTO();
            PaymentDTO pdto = intimationTxnActionForm.getPayDTO();
            log.debug("if1");
            if ("submitIntimationInfo".equalsIgnoreCase(actionForm)) {
            	
            	 if(!language.equalsIgnoreCase("en")){
                 	if(dto.getGender().equalsIgnoreCase("Male")){
                 		dto.setGender("पुस्र्ष");
                 	}else{
                 		dto.setGender("महिला");
                 	}
                 }
                log.debug("if2");
                IntimationTransactionRule rule = new IntimationTransactionRule();
               // ArrayList errorList = rule.validateIntimationTransactionRule(intimationTxnActionForm);
                IntimationTxnActionForm frm =(IntimationTxnActionForm)form;
               
                dto = frm.getIntimationDTO();
                 bd=new IntimationTransactionBD();
                 String tID=dto.getTranID();
                 dto.setTxnId(tID);
        //         log.debug("*********txn id after modify*****"+tID);
         //        log.debug("*********txn id after modify*****"+dto.getTxnId());
                 
                 if(dto.getTxnId()!=null &&!dto.getTxnId().equalsIgnoreCase(""))
                 {
                	 bd.updateIntimationDetails(dto);
                	 refID = bd.submitIntimationDetails(dto,funId,userId,frm.getFilePhoto(),frm.getFileSignature(),frm.getFileThumb());
                      dto.setTxnId(refID);

                 }
                 else
                 { 
                refId = bd.submitIntimationDetails(dto,funId,userId,frm.getFilePhoto(),frm.getFileSignature(),frm.getFileThumb());
                dto.setTxnId(refId);
                 }
          //      log.debug("refId...."+refId);
                log.debug("Outside error....");
               // rule.setError(false);
                if (rule.isError()) {
                    log.debug("if3");
                    FORWARD_JSP = "IntimationError";
                    request.setAttribute(CommonConstant.INTIMATION_TRANSACTION_ERROR_FLAG,"true");
                   // request.setAttribute(CommonConstant.INTIMATION_TRANSACTION_ERROR_LIST,errorList);
                    log.debug("Inside error....");
                }    else {
                    log.debug("if4");
                    log.debug("Getting DTO Objects....");
                    request.setAttribute(CommonConstant.INTIMATION_TRANSACTION_ERROR_FLAG,"false");
                    request.setAttribute(CommonConstant.INTIMATION_TRANSACTION_ERROR_LIST,null);
                    log.debug("Next JSP.");

                    IntimationTxnDetailsDTO intiDTO = (IntimationTxnDetailsDTO) intimationTxnActionForm.getIntimationDTO();
                    log.debug("photo="+intiDTO.getPhotoIdType());
          //          System.out.println(dto.getCountry());
            //        System.out.println(dto.getState());
            //        System.out.println(dto.getCity());
                    String coun=bd.getCountryName(dto.getCountry(),language);
                    dto.setCountryName(bd.getCountryName(dto.getCountry(),language));
                    dto.setStateName(bd.getStateName(dto.getState(),language));
                    dto.setCityName(bd.getDistrictName(dto.getCity(),language));
         //           log.debug("************photo id type in next action***********"+dto.getPhotoIdType());
                    dto.setPhotoIdTypeName(bd.getPhotoIdName(dto.getPhotoIdType(),language));
                   // intiDTO.setIntimationValues(intiVals);
                    intimationTxnActionForm.setIntimationDTO(intiDTO);
                    request.setAttribute("intimationForm",intimationTxnActionForm);
                    FORWARD_JSP = CommonConstant.INTIMATION_REQ_NEXT;
                    
                }
            }
        }
        
log.debug("*********view modify******************"+CommonConstant.INTIMATION_TXN_VIEW_MODIFY_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnModifyAction()));
       
              
        
        //To modify the Intimation Transaction Details
        if (CommonConstant.INTIMATION_TXN_MODIFY_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnModifyAction())) {
            log.debug("Next JSP1.");
            IntimationTxnActionForm frm =(IntimationTxnActionForm) session.getAttribute("intimationForm");
            dto=frm.getIntimationDTO();
            String tID=dto.getTxnId();
            dto.setTxnId(tID);
           /* String[] intiVals = frm.getIntimationDTO().getIntimationValues();
            String intiValNew[] = new String[intiVals.length];
            for (int i=0;i<intiVals.length;i++) {
            	if(intiVals[i] != null)
            		intiValNew[i] = intiVals[i].replaceAll("\\r\n","%0D%0A");
            	else
            		intiValNew[i] = "";
                log.debug("intiVal"+i+"="+intiValNew[i]);
            }*/
            IntimationTxnDetailsDTO intiDTO = (IntimationTxnDetailsDTO) frm.getIntimationDTO();
      //      log.debug("photo="+intiDTO.getPhotoIdType());
          //  intiDTO.setIntimationValues(intiValNew);
            intimationTxnActionForm.setIntimationDTO(intiDTO);
            request.setAttribute("tranID",tID);
            request.setAttribute("intimationtransaction",frm);
            FORWARD_JSP = CommonConstant.INTIMATION_REQ;
            log.debug("Next JSP1.");
        }

        //To go to Proceed Payment Payment after entering details
        if (CommonConstant.INTIMATION_PROCEED_PAYMENT_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationProceedPaymentAction())) {
            log.debug("Next JSP1.");
             IntimationTxnActionForm frm =(IntimationTxnActionForm) session.getAttribute("intimationForm");
             dto = frm.getIntimationDTO();
             String txnID=dto.getTxnId();
             session.setAttribute("newTXNID", txnID);
             IntimationTxnDetailsDTO  intDTO = frm.getIntimationDTO();
             PaymentDTO payDTO = frm.getPayDTO();
    //         log.debug("Next JSP1 Fathername..."+frm.getIntimationDTO().getFatherName());
             
             if(total>0)
             {
            // refId = bd.submitIntimationInfo1(dto,payDTO,funId,frm.getFilePhoto(),frm.getFileSignature(),frm.getFileThumb());
     String  cnId = bd.submitPaymentDetails1(dto,funId,userId);
            //request.setAttribute("intimationForm",frm);
            
            
            String officeId=dto.getOfficeId();
            String officeName=null;
            String districtId=null;
            String districtName=null;
            if(officeId!=null && !officeId.equalsIgnoreCase(""))
            {
            ArrayList alist = bd.getofficeDetails(officeId);
			ArrayList rowList = (ArrayList)alist.get(0);
		    officeName=(String)rowList.get(1);
		    districtId=(String)rowList.get(2);
			
			districtName=(String)rowList.get(3);
            }
            else if(officeId==null && officeId.equalsIgnoreCase(""))
            {
            	
            	ArrayList list = bd.getuserDetails(userId);
            	     if(list!=null && list.size()>0)
            	        {
    		            	ArrayList rowList = (ArrayList)list.get(0);
    		 
    		                 districtId=(String)rowList.get(0);
    		                 districtName=(String)rowList.get(1);
    		                 officeId="NA";
    		                 officeName="NA";
            	}
            	else{
            		districtId="NA";
        			districtName="NA";
        			officeId="NA";
        			officeName="NA";
            	}
            }
			//String willRefId=formDTO.getPayWillId();
     log.debug("***************total amt in proceed to payment****"+ String.valueOf(total));
            request.setAttribute("parentFunId", "FUN_010");
            request.setAttribute("parentModName", "INTIMATION OF TRANSACTION");
            request.setAttribute("parentFunName", "INTIMATION OF TRANSACTION");
          //  session.setAttribute("user", roleId);
            request.setAttribute("parentAmount", String.valueOf(total));
            request.setAttribute("parentTable", CommonConstant.IGRS_INTI_TXN_INFO);
            request.setAttribute("parentKey", cnId);
            request.setAttribute("parentOfficeId", officeId);
            request.setAttribute("parentOfficeName", officeName);
            request.setAttribute("parentDistrictId", districtId);
            request.setAttribute("parentDistrictName", districtName);
            request.setAttribute("parentReferenceId",txnID);
            
            request.setAttribute("forwardPath","./IntimationTransaction.do?TRFS=NGI&txnID="+txnID);
            request.setAttribute("parentColumnName", CommonConstant.INTIMATION_TXN_ID_COLNAME);
        	    //CommonConstant.INTIMATION_REQ_PAYMT_NEXT);
            //for inserting data stored in session
            request.setAttribute("intimationForm",frm); 
            FORWARD_JSP =  CommonConstant.INTIMATION_REQ_PROCEED_PAYMT;
            log.debug("Next JSP1.");
        }
        
        else
        {
        	 request.setAttribute("refId",dto.getTxnId());
        
        	  request.setAttribute("intimationForm",intimationTxnActionForm);

              FORWARD_JSP = CommonConstant.INTIMATION_REQ_CONFIRM;         		

        }
        
        }
        
       // To go to proceed partial payment
        if (CommonConstant.INTIMATION_PROCEED_PARTIAL_PAYMENT_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationProceedPaymentAction())) { 
       //     IntimationTxnActionForm frm =(IntimationTxnActionForm)form;
       //     dto = frm.getIntimationDTO();
           
        	
        	String txn_id=intimationTxnActionForm.getIntimationDTO().getTransactionID();
        	dto.setTxnId(txn_id);
        	double balance=bd.isCompletePayment(dto);
        	if(txn_id!=null)
        	{
        	 if(balance>0)
        		{
        	
 
       // 	log.debug("*******txn_id in partial payment"+txn_id);
  
         

            IntimationTxnDetailsDTO  intDTO = intimationTxnActionForm.getIntimationDTO();
            PaymentDTO payDTO = intimationTxnActionForm.getPayDTO();
        //    log.debug("Next JSP1 Fathername..."+intimationTxnActionForm.getIntimationDTO().getFatherName());
           // refId = bd.submitIntimationInfo1(dto,payDTO,funId,frm.getFilePhoto(),frm.getFileSignature(),frm.getFileThumb());
            dto.setTotalFee(Double.toString(balance));
        //    log.debug("*******total after pymnt in partial payment****"+dto.getTotalFee());
            String  unId = bd.submitPaymentDetails(intimationTxnActionForm.getIntimationDTO(),funId,userId);
            
            String officeId=dto.getOfficeId();
            String officeName=null;
            String districtId=null;
            String districtName=null;
            if(officeId!=null && !officeId.equalsIgnoreCase(""))
            {
            ArrayList alist = bd.getofficeDetails(officeId);
			ArrayList rowList = (ArrayList)alist.get(0);
		    officeName=(String)rowList.get(1);
		    districtId=(String)rowList.get(2);
			
			districtName=(String)rowList.get(3);
            }
            else if(officeId==null && officeId.equalsIgnoreCase(""))
            {
            	
            	ArrayList list = bd.getuserDetails(userId);
            	     if(list!=null && list.size()>0)
            	        {
    		            	ArrayList rowList = (ArrayList)list.get(0);
    		 
    		                 districtId=(String)rowList.get(0);
    		                 districtName=(String)rowList.get(1);
    		                 officeId="NA";
    		                 officeName="NA";
            	}
            	else{
            		districtId="NA";
        			districtName="NA";
        			officeId="NA";
        			officeName="NA";
            	}
            }
			
            
           
           request.setAttribute("parentFunId", "FUN_010");
           request.setAttribute("parentModName", "INTIMATION OF TRANSACTION");
           request.setAttribute("parentFunName", "INTIMATION OF TRANSACTION");
         //  session.setAttribute("user", roleId);
         //  request.setAttribute("parentAmount", String.valueOf(total));
           request.setAttribute("parentAmount", dto.getTotalFee());
      //     log.debug("*******payable amt in partial payment*****"+dto.getPaidAmnt());
           request.setAttribute("parentTable", CommonConstant.IGRS_INTI_TXN_INFO);
           request.setAttribute("parentKey", unId);
           request.setAttribute("parentOfficeId", officeId);
           request.setAttribute("parentOfficeName", officeName);
           request.setAttribute("parentDistrictId", districtId);
           request.setAttribute("parentDistrictName", districtName);
           
           request.setAttribute("parentReferenceId",txn_id);
           request.setAttribute("forwardPath","./IntimationTransaction.do?TRFS=NGI");
           request.setAttribute("parentColumnName", CommonConstant.INTIMATION_TXN_UNQ_ID_COLNAME);
       	    //CommonConstant.INTIMATION_REQ_PAYMT_NEXT);
           //for inserting data stored in session
           request.setAttribute("intimationForm",intimationTxnActionForm); 
           FORWARD_JSP =  CommonConstant.INTIMATION_REQ_PROCEED_PAYMT;
           log.debug("Next JSP1.");
        }	
        	
        
        
        else

        {

            String succMsg="";

            succMsg=bd.updatePaymentInfo(dto);

            if("succ".equalsIgnoreCase(succMsg))

                    {

                            request.setAttribute("refId",intimationTxnActionForm.getIntimationDTO().getTransactionID());

                    request.setAttribute("intimationForm",intimationTxnActionForm);

                  //  dto.setPaymentSuccessAction("");

                  //  dto.setCopyIssuanceSuccessAction("");                   

                    //dto.setCopyIssuanceSuccessAction(CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION);

                    FORWARD_JSP = CommonConstant.INTIMATION_REQ_CONFIRM;           

                    }
            
          	}
        	}
        	
        	       
        	
        	
  /*  	if(pymtFlg.equalsIgnoreCase("I")){
			
			request.setAttribute("parentModName", "Registration Process");
			request.setAttribute("parentFunName", "Delivery of Document");
			request.setAttribute("parentFunId", "FUN_207");
			request.setAttribute("parentAmount", amtToBePaid);
			request.setAttribute("parentTable", "IGRS_DEL_DOC_PAYMENT_DETLS");
			request.setAttribute("parentKey", uniqId);
			request.setAttribute("forwardPath", "./dod.do");
			request.setAttribute("parentColumnName", "PAYMENT_DETL_ID");
									
			forwardPage = "nextToPay";
		}
   */
        /*  request.setAttribute("forwardPath", "./CopyIssuance.do");
        request.setAttribute("parentTable", CommonConstant.COPY_ISSUANCE_TXN_TABLE);
        request.setAttribute("parentAmount",String.valueOf(total));
        request.setAttribute("parentFunId", funId);
        request.setAttribute("parentKey", copyRefId);
        request.setAttribute("parentModName", cerDTO.getParentModName());
        request.setAttribute("parentFunName", cerDTO.getParentFunName());
        request.setAttribute("parentColumnName", CommonConstant.COPY_ISSUANCE_TXN_ID_COLNAME);	
        //Ramesh added attributes for payment integration on 15 Feb 13
        request.setAttribute("refId",copyRefId);
        request.setAttribute("copyForm",coForm);
        dto.setPaymentSuccessAction("");
        FORWARD_JSP = CommonConstant.PROCEED_PAYMENT;
        */
        
        

        /*//To go to previous Form(showing details entered) from Payment input Form
        if (CommonConstant.INTIMATION_PREVIOUS_PAYMENT_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationPreviousPaymentAction())) {
            log.debug("Next JSP1.");
            IntimationTxnActionForm frm =(IntimationTxnActionForm) session.getAttribute("intimationForm");
            log.debug("Next JSP1 Fathername..."+frm.getIntimationDTO().getFatherName());
            String[] intiReqType = frm.getIntimationDTO().getIntimationReqType();
            request.setAttribute("intimationreqtype",frm.getIntimationDTO().getIntimationReqType());
            request.setAttribute("intimationtypes",frm.getIntimationDTO().getIntimationTypes());
            request.setAttribute("intimationvalues",frm.getIntimationDTO().getIntimationValues());
            request.setAttribute("intimationForm",frm);
            FORWARD_JSP = CommonConstant.INTIMATION_REQ_PAYMT_PREV;
            log.debug("Next JSP1.");
        }

        //To validate the payment before save details into Table
        if (CommonConstant.PAYMENT_VALIDATE_ACTION.equalsIgnoreCase(intimationTxnActionForm.getPayDTO().getPaymentValidateAction())) {
            log.debug("Inside Payment Validate");
            PaymentDTO dto = intimationTxnActionForm.getPayDTO();
           IntimationTxnActionForm frm = (IntimationTxnActionForm) session.getAttribute("intimationForm");
            //request.setAttribute("intimationForm",frm);
            String challanNo = dto.getChallanNo();
            String challanDate = dto.getChallanDate();
            String challanAmt = dto.getAmount();
            String bnkName = dto.getBankName();
            SimpleDateFormat stringFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            try {
                date = stringFormat.parse(challanDate);
                log.debug("date="+date);
            } catch (ParseException pe) {
                log.debug("ParseException: " + pe);

            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
            String challanDt = null;
            try {
                challanDt = dateFormat.format(date);
            } catch (Exception e) {
                log.debug("Error getting date field  in 'M/d/yyyy' format: " + e ); 
            }

            ArrayList intimation_payment = bd.validatePayment(challanNo,challanDt,challanAmt,bnkName);
            Iterator itr = intimation_payment.iterator();
            log.debug("LIST="+intimation_payment.size());
            //int flag = 0;
            while (itr.hasNext()) {
                log.debug("Inside While");
                dto = (PaymentDTO) itr.next();
            }

            if (intimation_payment.size()!=0) {
                frm.setPayDTO(dto);
                FORWARD_JSP = CommonConstant.INTIMATION_REQ_PAYMT_NEXT; 
            } else {
                String[] params = {challanNo,challanDate,challanAmt,bnkName};
                request.setAttribute("paymtParam",params);
                FORWARD_JSP = CommonConstant.INTIMATION_PAYMT_ERROR;
            }

            request.setAttribute("intimationForm",frm);
        }

        //To Modify the Payment details
        if (CommonConstant.PAYMENT_MODIFY_ACTION.equalsIgnoreCase(intimationTxnActionForm.getPayDTO().getPaymentModifyAction())) {
            IntimationTxnActionForm frm = (IntimationTxnActionForm)  session.getAttribute("intimationForm");
            request.setAttribute("intimationForm",frm); 
            log.debug("modify payment="+frm);
            FORWARD_JSP = CommonConstant.INTIMATION_PAYMT_MODIFY;
        }*/
        
        //To insert Intimation Req Form Details into Table
        
       
        if (CommonConstant.INTIMATION_TXN_SUCCESS_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationTxnSuccessAction())) {
        String payId = (String)session.getAttribute("status");
        if(session.getAttribute("payConfirm") == null) {       
            if(payId != null){
                IntimationTxnActionForm frm = (IntimationTxnActionForm)  session.getAttribute("intimationForm");
                 dto = frm.getIntimationDTO();
                PaymentDTO payDTO = frm.getPayDTO();
                payDTO.setPaymentTxnId(payId);
              //  refId = bd.submitIntimationInfo1(dto,payDTO,funId,frm.getFilePhoto(),frm.getFileSignature(),frm.getFileThumb());
                request.setAttribute("refId",refId);
                request.setAttribute("intimationForm",frm);
                session.setAttribute("payConfirm" ,"paymentConf");            
                FORWARD_JSP = CommonConstant.INTIMATION_REQ_CONFIRM;
            }
        }
        

        //To show the pop up window for print
        String pop = request.getParameter("pop");
        log.debug("pop="+pop);
        if (pop!=null) {
            if (pop.equals("true")) {
                log.debug("if2");
                IntimationTxnActionForm  frm = (IntimationTxnActionForm) session.getAttribute("intimationForm");
                String refId1 = (String) session.getAttribute("refId");
                request.setAttribute("intimationForm",frm);
                request.setAttribute("refId",refId1);
                FORWARD_JSP = CommonConstant.INTIMATION_REQ_POPUP;   
            }
        }

        //  To get the intimtaion list corresponding to the refid,status,fromdate and todate.				 
      //  if (CommonConstant.INTIMATION_VIEW_SUBMIT_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationViewSubmitAction())) {
       //     log.debug("Actions are matched for Intimation View Details :-  ");
       //      dto = intimationTxnActionForm.getIntimationDTO();
            /*String referId = dto.getTxnId();
            String status = dto.getStatus();
            String fromDate = dto.getFromDate();
            String toDate = dto.getToDate();
            String docRegNo = dto.getDocRegNo();
            ArrayList list = bd.getIntimationSearch(referId, status, fromDate, toDate, docRegNo);
            if (list!=null) {
                for (int i=0;i<list.size();i++) {
                    IntimationTxnDetailsDTO dt = (IntimationTxnDetailsDTO) list.get(i);
                    log.debug("ID:-"+dt.getTxnId());
                    log.debug("ST:-"+dt.getStatus());
                }
            }*/
        //    log.debug("hai");
           // request.setAttribute("intimation_view_list",list);
           // FORWARD_JSP = CommonConstant.INTIMATION_VIEW_SUBMIT;       
          //  return mapping.findForward(CommonConstant.INTIMATION_VIEW_SUBMIT);
      //  }   

       
        
        //To get the details  corresponding  to the Ref Id(clicked)        
        if (CommonConstant.INTIMATION_VIEW_ID_DETAILS_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationViewIdDetailsAction())) {
            log.debug("Actions are matched for CERTIFIED ID Details");
            log.debug("CertifiedID is matched.....");
             dto = intimationTxnActionForm.getIntimationDTO();
            String refId1 = (String)request.getParameter("refId");
            request.setAttribute("refId",refId1);
        //    log.debug("Request certified Id:-"+refId1);
            if (refId1 != null) {
                //getting intimation data
        	dto1 = bd.getIntimationDetails(refId1);
             //   log.debug("REF ID"+refId1);  
                intimationTxnActionForm.setIntimationDTO(dto1);
                //for getting other fee and total for that
                intimationTxnActionForm.getIntimationDTO().setOtherFee(otherFeeVal);
                intimationTxnActionForm.getIntimationDTO().setTotalFee(String.valueOf(total));
                /*if(!intimationTxnActionForm.getIntimationDTO().getFee().equalsIgnoreCase("")){
                otherFeeVal = bd.getOthersFeeDuty(funId, serviceId, userType)==null?"0.0":bd.getOthersFeeDuty(funId, serviceId, userType);
    		if(otherFeeVal != null) {
    		    intimationTxnActionForm.getIntimationDTO().setOtherFee(otherFeeVal);
    		total = Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getFee()==null?"0.0":
    			intimationTxnActionForm.getIntimationDTO().getFee()) + 
    				Float.parseFloat(intimationTxnActionForm.getIntimationDTO().getOtherFee()==null?"0.0":
    				intimationTxnActionForm.getIntimationDTO().getOtherFee());
    		intimationTxnActionForm.getIntimationDTO().setTotalFee(String.valueOf(total));*/
                intimationTxnActionForm.setIntimationDTO(dto1);
                request.setAttribute("intimationForm",intimationTxnActionForm);
                FORWARD_JSP = CommonConstant.INTIMATION_VIEW_ID_DETAILS;
    			}
               // }
        }

        //To get default JSP for Ok & cancel Button
        if (CommonConstant.INTIMATION_OK_BUTTON_ACTION.equalsIgnoreCase(intimationTxnActionForm.getIntimationDTO().getIntimationOkButtonAction())) {
            FORWARD_JSP = CommonConstant.INTIMATION_OK_CANCEL;
        }

      //  return mapping.findForward(FORWARD_JSP);
    }
        
    }
       
      
            return mapping.findForward(FORWARD_JSP);    
    }
            
}

    


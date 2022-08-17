/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.action;

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
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.dto.OTTDetailDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.copyissuance.constant.CommonConstant;
import com.wipro.igrs.estamping.bo.EstampBO;
import com.wipro.igrs.estamping.form.EstampFormBean;
import com.wipro.igrs.reginit.constant.RegInitConstant;

/**
* 
* InitAction.java <br>
* InitAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "unchecked", "rawtypes" })
public class Caveat_partial extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatActionConfirm.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param 
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		try {
			
			
			 CaveatsDelegate cavBD = new CaveatsDelegate();
			 CaveatsBO obj= new CaveatsBO();
			 CaveatsForm fm = (CaveatsForm) form;
		      CaveatsDTO DTO = fm.getCaveatsDTO();
              String actionName = DTO.getActionName();
              String pageName = (String) request.getParameter("pageName");
              CaveatsDAO dao= new CaveatsDAO();
              String caveatId;
              String language=(String)session.getAttribute("languageLocale");
				String reqNo;

             DTO = fm.getCaveatsDTO();
             String FORWAD_SUCCESS="";
		
            	
        	
        	
        	        	
             
        	if ("partialpayment".equals(request.getParameter("actionName"))) 
        	{
			
					
        		
        		
				      String functionId = (String)session.getAttribute("functionId");
				  	          String bal="";
			            String officeId = (String)session.getAttribute("loggedToOffice");
			          ArrayList partialList = obj.getPartial(fm.getCaveatsDTO().getTransactionID().toString());
		        for(int i=0;i<partialList.size();i++)
			          {
			        	  CaveatsDTO objCaveatDTO =(CaveatsDTO) partialList.get(i);
			        	  bal=(String) objCaveatDTO.getBalanceAmount();
			        	  

			          }
			          
			          
			          
		        	  fm.getCaveatsDTO().setPartial(partialList);
						bal=DTO.getBalanceAmount().toString();
		        	   float balanceAmount = Float.parseFloat(bal);
		        	  
		        	   DTO.setBalanceAmount(balanceAmount);
		        	   
		  			 
		  			String userId = (String)session.getAttribute("UserId");
					   
		        	 //   logger.info("--------------->"+partialList.size());
		        		
		  			if(language.equalsIgnoreCase("en")){
                        session.setAttribute("modName","Protest Charges");  
                  }else{
                        session.setAttribute("modName","चेतावनी प्रभार");
                  }

		  					if(language.equalsIgnoreCase("en")){
                              session.setAttribute("fnName","Log Protest");
                        }else{
                              session.setAttribute("fnName","चेतावनी प्रवेश");
                        }
						
						if(balanceAmount>0)
							
						{
							caveatId= fm.getCaveatsDTO().getReferenceID();
							reqNo= fm.getCaveatsDTO().getReqNum();
					         String parentKey=obj.setPaymentDetails(DTO,functionId,userId);
					         
					         ArrayList alist = CaveatsDelegate.getDetails(officeId);
								ArrayList rowList = (ArrayList)alist.get(0);
								String officeName=(String)rowList.get(1);
								String districtId=(String)rowList.get(2);
								

								String districtName=(String)rowList.get(3);
							
    						request.setAttribute("forwardPath","./Caveat_partial.do?pageName=submit&TRFS=NGI");
    						request.setAttribute("parentTable","IGRS_CAVEAT_PAYMENT_DETAILS");
    					   	
    					   
    						request.setAttribute("parentFunId",functionId);
    						
    						request.setAttribute("parentModName",session.getAttribute("modName"));
    						request.setAttribute("parentFunName",session.getAttribute("fnName"));
    						request.setAttribute("parentAmount",DTO.getBalanceAmount().toString());
    						request.setAttribute("parentKey",parentKey);
    						request.setAttribute("parentColumnName","TRANSACTION_ID");
    						request.setAttribute("parentOfficeId", officeId);
    		                request.setAttribute("parentOfficeName", officeName);
    		                request.setAttribute("parentDistrictId", districtId);
    		                request.setAttribute("parentDistrictName", districtName);
    		                request.setAttribute("parentReferenceId", DTO.getTransactionID());
    						logger.info("Inside Action being SUCCESS..");
    						DTO.setActionName("");
    						
    							FORWAD_SUCCESS = "payment";
    					
    						/* 
        						logger.info(" forward path"+request.getAttribute("forwardPath"));
        						logger.info(" parentTable"+request.getAttribute("parentTable"));
        						logger.info(" parentFunId"+request.getAttribute("parentFunId"));
        						logger.info(" parentModName"+request.getAttribute("parentModName"));
        						logger.info(" parentFunName"+request.getAttribute("parentFunName"));
        						logger.info(" parentAmount"+request.getAttribute("parentAmount"));
        						logger.info(" parentKey"+request.getAttribute("parentKey"));
        						logger.info(" parentColumnName"+request.getAttribute("parentColumnName"));
        						logger.info(" parentOfficeId"+request.getAttribute("parentOfficeId"));
                                logger.info(" parentOfficeName"+request.getAttribute("parentOfficeName"));
                                logger.info(" parentDistrictId"+request.getAttribute("parentDistrictId"));
                                logger.info(" parentDistrictName"+request.getAttribute("parentDistrictName"));
                                logger.info(" parentReferenceId"+request.getAttribute("parentReferenceId"));
*/
  


    							

    							
    								
    							return mapping.findForward(FORWAD_SUCCESS);
    						}
    				
						
						
								
						  if(balanceAmount<=0){
							  //request.setAttribute("parentKey",DTO.getTransactionID());
							   dao.updateFlagDAO(fm.getCaveatsDTO());
							   dao.updateStatusDAO(fm.getCaveatsDTO());

							   
							   FORWAD_SUCCESS = "completeSuccess";
				              return mapping.findForward("completeSuccess");	
							 }
						 
        	
						
        	}
		
        	else if ("submit".equals(pageName))
        	{ 
        		
        		String bal="";
	            String officeId = (String)session.getAttribute("loggedToOffice");
	          ArrayList partialList = obj.getPartial(fm.getCaveatsDTO().getTransactionID().toString());
              for(int i=0;i<partialList.size();i++)
	          {
	        	  CaveatsDTO objCaveatDTO =(CaveatsDTO) partialList.get(i);
	        	  bal=(String) objCaveatDTO.getBalanceAmount();
	        	  

	          }
	          
              bal=bal;
	          
        	  fm.getCaveatsDTO().setPartial(partialList);
				
        	   float balanceAmount = Float.parseFloat(bal);
        	  
        	   DTO.setBalanceAmount(balanceAmount);
				
        	   
        		if(balanceAmount<=0){
					  //request.setAttribute("parentKey",DTO.getTransactionID());
					   dao.updateFlagDAO(fm.getCaveatsDTO());
					   dao.updateStatusDAO(fm.getCaveatsDTO());

					   
					   FORWAD_SUCCESS = "completeSuccess";
		              return mapping.findForward("completeSuccess");	
					 }
        		else
        		{
        		FORWAD_SUCCESS = "success";
        		}
        	return mapping.findForward(FORWAD_SUCCESS);
        	}
        	return mapping.findForward(FORWAD_SUCCESS);

		}
		
	//	}
        	catch (Exception e) {
        		
    			//logger.error(e);
    			e.printStackTrace();
    			return mapping.findForward("Failure");
    		}
		
		
		
		

        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
               // System.out.println(dto.getAge());

        /*      String copyRefId = "";
              String transactionID = (String) session.getAttribute("tansactionID");
				//DTO.setPropertyTxnId(propertId);
				DTO.setTransactionID(transactionID);
				
                copyRefId = DTO.ge();       

                System.out.println(dto.getBalanceAmount());

                float balance =Float.parseFloat(DTO.getBalanceAmount());

                if(copyRefId!="")

        {

  if(balance>0)

            {

                 

                

                CertifiedCopyDetailsDTO cerDTO = coForm.getIssuanceDTO();

                    String parentKey=bd.setPaymentDetails(dto,funId,userId);

                cerDTO=bd.getFunctionName(funId);

                    request.setAttribute("forwardPath", "./CopyIssuance.do");

                    request.setAttribute("parentTable", "IGRS_CERT_COPY_PAYMENT_DTLS");

                    request.setAttribute("parentAmount",String.valueOf(balance));

                    System.out.println(funId);

                    request.setAttribute("parentFunId", funId);

                    System.out.println(copyRefId);

                    request.setAttribute("parentKey",parentKey);

                    System.out.println(cerDTO.getParentModName());

                    request.setAttribute("parentModName", cerDTO.getParentModName());

                    System.out.println(cerDTO.getParentFunName());

                    request.setAttribute("parentFunName", cerDTO.getParentFunName());

                    request.setAttribute("parentColumnName","CERT_PAYMENT_ID"); 

                    //Ramesh added attributes for payment integration on 15 Feb 13

                    request.setAttribute("refId",copyRefId);

                    request.setAttribute("copyForm",coForm);

                    dto.setPaymentSuccessAction("");

                    FORWARD_JSP = CommonConstant.PROCEED_PAYMENT;

                    return mapping.findForward(FORWARD_JSP);

            }

 else

            {

                String succMsg="";

                succMsg=bd.updatePaymentInfo(dto);

                if("succ".equalsIgnoreCase(succMsg))

                        {

                                request.setAttribute("refId",dto.getCertifiedId());

                        request.setAttribute("copyForm",coForm);

                        dto.setPaymentSuccessAction("");

                        dto.setCopyIssuanceSuccessAction("");                   

                        //dto.setCopyIssuanceSuccessAction(CommonConstant.COPY_ISSUANCE_SUCCESS_ACTION);

                        FORWARD_JSP = CommonConstant.CONFIRM;           

                }

                        else

                {

                          String msg="Data could not saved.";

                          dto.setCopyIssuanceSuccessAction("");

                          request.setAttribute("CopyStatus", msg);                      

                          FORWARD_JSP = "copyissuance";

                }

            }

        

        }


	 }*/
                 
		
	
	/**
	 * 
	 * @param form
	 * @param errorList
	 * @param request
	 * @param currSysDate
	 * @return
	 */
}
	}

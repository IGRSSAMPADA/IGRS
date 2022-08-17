package com.wipro.igrs.caveats.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.bo.CaveatsBO;
import com.wipro.igrs.caveats.dao.CaveatsDAO;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.payment.constant.CommonConstant;
import com.wipro.igrs.reginit.constant.RegInitConstant;

/**
* 
* CaveatAction.java <br>
* CaveatAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings("rawtypes")
public class CaveatAction extends BaseAction {
    private Logger logger = 
		(Logger) Logger.getLogger(CaveatAction.class);

	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response,HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	logger.debug("WE ARE IN CaveatAction Debug");
		logger.info("WE ARE IN  CaveatAction INFO");
		
		
        try{
            
        	CaveatsForm formBean = (CaveatsForm) form;
            CaveatsDTO cavDTO = formBean.getCaveatsDTO();
            
            String language=(String)session.getAttribute("languageLocale");
            cavDTO.setLanguage(language);
            if(session.getAttribute("languageLocale")!=null){
				language=(String)session.getAttribute("languageLocale");
			}
			
	/*		if(language.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				session.setAttribute("modName",RegInitConstant.MODNAME_EN);	
				session.setAttribute("fnName",RegInitConstant.FUNCTION_CAVEAT_ENGLISH);
			}else{
				session.setAttribute("modName",RegInitConstant.MODNAME_HI);
				session.setAttribute("fnName",RegInitConstant.FUNCTION_CAVEAT_HINDI);
			}
            */
            CaveatsDelegate cavBD = new CaveatsDelegate();
            CaveatsBO bo= new CaveatsBO();
            String actionName = cavDTO.getActionName();
        	logger.info("WE ARE IN  CaveatAction INFO"+ actionName);
        	 CaveatsDAO dao= new CaveatsDAO();
        	String pageName = (String) request.getParameter("pageName");
    		if (pageName == null){
    			pageName = "";
    		}
            String FORWAD_SUCCESS = "success";
     
          
            ///
            
        if ("downloadMainDoc".equalsIgnoreCase(actionName)) 
	        {
	        	try
	        	{
	        	response.setContentType("application/download");
	            
				String filename=cavDTO.getUploaded_doc_path();
				File fileToDownload = new File(filename);

				response.setHeader("Content-Disposition", "attachment; filename="

	                                                + URLEncoder.encode(cavDTO.getDocName().toString(),"UTF-8"));
				
	            FileInputStream fileInputStream = new FileInputStream(fileToDownload);

	            OutputStream out = response.getOutputStream();

	            byte[] buf = new byte[2048];



	            int readNum;

	            while ((readNum=fileInputStream.read(buf))!=-1)

	            {

	               out.write(buf,0,readNum);

	            }

	            fileInputStream.close();

	            out.close();
	            cavDTO.setActionName("");
	            FORWAD_SUCCESS = "confirm";
	        
	        }
            catch (Exception e) {
			}	
	        }	
            ///
            else if ("payment".equals(actionName)) {
				String propertId = (String) session.getAttribute("propTxnNo");
				cavDTO.setPropertyTxnId(propertId);
				String loggedIn = (String) session.getAttribute("UserId");
				String functionId = (String)session.getAttribute("functionId");
				String officeId = (String)session.getAttribute("loggedToOffice");
				CaveatsDAO daos = new CaveatsDAO();
				String ofcId = daos.getOthersFeeDuty(officeId);
				String payAmount = daos.getOthersFeeDutyNew(functionId, null, officeId);
				
				String caveatId;
				String reqNo;
				if (loggedIn == null || "".equalsIgnoreCase(loggedIn)) {
					loggedIn = "igrs";
				}
				cavDTO.setLoggedIn(loggedIn);
				boolean log = cavBD.logCaveats(cavDTO,loggedIn,functionId,payAmount);
				boolean isZeroFee = checkZeroFee(payAmount);
				
				//logger.debug("Is zero fee : " + isZeroFee);
				if (log) {
					caveatId=cavDTO.getReferenceID();
					reqNo=cavDTO.getReqNum();
					ArrayList alist = CaveatsDelegate.getDetails(officeId);
					ArrayList rowList = (ArrayList)alist.get(0);
					String officeName=(String)rowList.get(1);
					String districtId=(String)rowList.get(2);
					

					String districtName=(String)rowList.get(3);
					
						caveatId=cavDTO.getReferenceID();
						request.setAttribute("forwardPath","./CaveatAction.do?pageName=submit&TRFS=NGI");
						request.setAttribute("parentTable","IGRS_CAVEAT_PAYMENT_DETAILS");
						request.setAttribute("parentAmount",payAmount);	
						request.setAttribute("parentFunId",functionId);
					    request.setAttribute("parentKey",caveatId);
						

						request.setAttribute("parentModName",session.getAttribute("modName"));
						request.setAttribute("parentFunName",session.getAttribute("fnName"));
						request.setAttribute("parentColumnName","CAVEAT_TXN_ID");
						request.setAttribute("parentOfficeId", officeId);
		                request.setAttribute("parentOfficeName", officeName);
		                request.setAttribute("parentDistrictId", districtId);
		                request.setAttribute("parentDistrictName", districtName);
		                request.setAttribute("parentReferenceId", reqNo);
						logger.info("Inside Action being SUCCESS..");
						cavDTO.setActionName("");
						
						
						
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
				//////		Upload Document folder structure
						
					          FormFile photo =formBean.getCaveatsDTO().getAttachedDoc();
					          byte contents [] = formBean.getCaveatsDTO().getPhoto();
					          String fileExt = getFileExtension(photo.getFileName());
					          String docName ="Caveat_Document." +fileExt;
							  //System.out.println(docName);
					          formBean.getCaveatsDTO().setDocName(docName);
					          String docPath = "D:/Upload/13/"+caveatId;
					             
					          formBean.getCaveatsDTO().setCaveatId(caveatId);
					          formBean.getCaveatsDTO().setUploaded_doc_path(docPath+"/"+docName);
					           
					          String docpath =formBean.getCaveatsDTO().getUploaded_doc_path();
					          boolean up=uploadFile(contents,docName,docPath);
					          boolean insertTxn = false;
					          
					          insertTxn = CaveatsBO.insertSpDocDetls1(formBean); 
					          if(language.equalsIgnoreCase("en")){
			                        session.setAttribute("modName","Protest Charges");  
			                  }else{
			                        session.setAttribute("modName","चेतावनी प्रभार");
			                  }

			if(language.equalsIgnoreCase("en")){
			                              session.setAttribute("fnName","Log Portest");
			                        }else{
			                              session.setAttribute("fnName","चेतावनी प्रवेश");
			                        }

					          
				}
						
						//////
				
				
				
						FORWAD_SUCCESS = "payment";
						return mapping.findForward(FORWAD_SUCCESS);
						// }
				} 
            else if("paymentService".equalsIgnoreCase(actionName)){

				String propertId = (String) session.getAttribute("propTxnNo");
				cavDTO.setPropertyTxnId(propertId);
				String loggedIn = (String) session.getAttribute("UserId");
				String functionId = (String)session.getAttribute("functionId");
				String officeId = (String)session.getAttribute("loggedToOffice");
				CaveatsDAO daos = new CaveatsDAO();
				String ofcId = daos.getOthersFeeDuty(officeId);
				String payAmount = daos.getOthersFeeDutyNew(functionId, null, officeId);
				
				String caveatId;
				String reqNo;
				if (loggedIn == null || "".equalsIgnoreCase(loggedIn)) {
					loggedIn = "igrs";
				}
				cavDTO.setLoggedIn(loggedIn);
				boolean log = cavBD.logCaveats(cavDTO,loggedIn,functionId,payAmount);
				boolean isZeroFee = checkZeroFee(payAmount);
				
				//logger.debug("Is zero fee : " + isZeroFee);
				if (log) {
					 caveatId=cavDTO.getReferenceID();
					  FormFile photo =formBean.getCaveatsDTO().getAttachedDoc();
			          byte contents [] = formBean.getCaveatsDTO().getPhoto();
			          String fileExt = getFileExtension(photo.getFileName());
			          String docName ="Caveat_Document." +fileExt;
					  //System.out.println(docName);
			          formBean.getCaveatsDTO().setDocName(docName);
			          String docPath = "D:/Upload/13/"+caveatId;
			             
			          formBean.getCaveatsDTO().setCaveatId(caveatId);
			          formBean.getCaveatsDTO().setUploaded_doc_path(docPath+"/"+docName);
			           
			          String docpath =formBean.getCaveatsDTO().getUploaded_doc_path();
			          boolean up=uploadFile(contents,docName,docPath);
			          boolean insertTxn = false;
			          
			          insertTxn = CaveatsBO.insertSpDocDetls1(formBean); 
			      	if (language.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.SUCCESS_MSG,
								"Receipt was downloaded successfully. \n Proceed to Pay via Payment of Services module.");
					else
						request.setAttribute(CommonConstant.SUCCESS_MSG,
								"रसीद सफलतापूर्वक डाउनलोड हो चुकि है।  \n भुगतान करने के लिए सेवा के भुगतान मॉड्यूल से आगे बढ़ें |");
				}else{

					if (language.equalsIgnoreCase("en"))
						request.setAttribute(CommonConstant.FAILURE_MSG,
								"Receipt could not be generated. Please try again!!");
					else
						request.setAttribute(CommonConstant.FAILURE_MSG,
								"रसीद उत्पन्न नहीं हो पाई है। कृपया पुनः प्रयास करें!!");
				}
				request.setAttribute("referenceID", cavDTO.getReferenceID());
				request.setAttribute("payFuncID", "FUN_016");
				request.setAttribute("payableAmt", payAmount);
				FORWAD_SUCCESS = "paymentService";
				return mapping.findForward(FORWAD_SUCCESS);
            }
				else if ("submit".equals(pageName)) {
					String bal="";
					CaveatsDTO objCaveatDTO=new CaveatsDTO();
		            String officeId = (String)session.getAttribute("loggedToOffice");
		          ArrayList partialList = bo.getPartial1(formBean.getCaveatsDTO().getReferenceID().toString());
	              for(int i=0;i<partialList.size();i++)
		          {
		        	  objCaveatDTO =(CaveatsDTO) partialList.get(i);
		        	  bal=(String) objCaveatDTO.getBalanceAmount();
		        	  

		          }
		          
		          
		          
	              formBean.getCaveatsDTO().setPartial(partialList);
					
	        	   float balanceAmount = Float.parseFloat(bal);
	        	  
	        	   objCaveatDTO.setBalanceAmount(balanceAmount);
	        	   if(balanceAmount<=0){
						  //request.setAttribute("parentKey",DTO.getTransactionID());
						   

	  			 
					//String caveatId= formBean.getCaveatsDTO().getReferenceID();
					dao.updateFlagDAO1(formBean.getCaveatsDTO());
					   dao.updateStatusDAO1(formBean.getCaveatsDTO());

						//FORWAD_SUCCESS = "success";

					FORWAD_SUCCESS = "success";
				}
	        	   else
	        	   {
	        		   FORWAD_SUCCESS = "partialSuccess";  
	        	   }
				}
            session.setAttribute("caveatfrm", formBean);
            return mapping.findForward(FORWAD_SUCCESS); 
        }catch(Exception e)
        {
         //   logger.error(e);
            return mapping.findForward("confirm");
        }
    }

	private boolean checkZeroFee(String payAmount) {
		boolean ret = false;
	
		try {
			double d = Double.parseDouble(payAmount);
			if(d <= 0.0) {
				   
				ret = true;
			}
		} catch (Exception e) {
		}
		return ret;
	}

	
	private String getTotalFees(ArrayList list) {
		String ret = "0";
		try {
			ret = list.get(2).toString();
			Double.parseDouble(ret);
		} catch (Exception e) {
		}
		return ret;
	}

////--------------------------------------
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	
	
	
	private boolean uploadFile(byte[] contents,String fileName,String filePath) {
		 
        
        
        try {
              File folder = new File(filePath);
              if (!folder.exists()) {
              folder.mkdirs();
               }
              
                    File newFile = new File(filePath, fileName);
                    logger.info("NEW FILE NAME:-" + newFile);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    fos.write(contents);
                    fos.close();
              

        } catch (Exception ex) {
              ex.printStackTrace();
        }
        return true;
  }





	
	
	
	
	/**
	 * 
	 * @param length
	 * @return
	 */
	private String getFileSize(int length) {
		double fileSizeInBytes = length;
		// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
		double fileSizeInKB = fileSizeInBytes / 1024.0;
		// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
		double fileSizeInMB = fileSizeInKB / 1024.0;

		//System.out.println("fileSizeInBytes = "+fileSizeInBytes+" fileSizeInKB = "+fileSizeInKB+" fileSizeInMB = "+fileSizeInMB);
		DecimalFormat decim = new DecimalFormat("#.##");
		Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
		String photoSize="("+fileSize+" MB)";
		return photoSize;
	}

////----------------------------
}




package com.wipro.igrs.regcompletion.action;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.auditinspection.form.SROUploadForm;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.regcompletion.dto.UploadFileDTO;


public class RegUploadAction extends BaseAction  {
	
	private  Logger logger = 
		(Logger) Logger.getLogger(RegUploadAction.class);
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		ActionErrors errors      = new ActionErrors();
		ActionForward forward    = new ActionForward(); 
		SROUploadForm uploadForm    = (SROUploadForm)form;
		//HttpSession session      = request.getSession(); 
		ArrayList errorList      = new ArrayList();
		String filePath          = getServlet().getServletContext().getRealPath("") + "/temp/";
		session.setAttribute("FilePath", filePath);
		AuditInspectionRule rule = null; 
		int  currFileSize        = 0;

		
			  logger.debug("In REG UPLOAD action");
			
			  if(uploadForm.getAttachAction().equalsIgnoreCase("attach"))
			  {
				  logger.debug("Test I am in Attach Block");
			     if(session.getAttribute("attachment1")==null)
			     {
			    	//Get the list of files
			        FormFile  strFileName = uploadForm.getTheFile();
    		        logger.debug("file type:" + strFileName.getContentType());
    		        logger.debug("File Name "+strFileName.getFileName());
				    int size = strFileName.getFileSize();
				    logger.debug("REG UPLOAD FILE SIZE WHEN SESSION IS NOT CREATED :"+size);
				    
				    // save file in the app server 
				    String fileExt = getFileExtension(strFileName.getFileName());
				    logger.debug("File Extenssion :"+fileExt);
				    rule = new AuditInspectionRule();
				    errorList=rule.validateFileType(fileExt);
				    if(rule.isError())
				    {
				    	request.setAttribute("errorsList", errorList);
				    	return mapping.findForward("upload");
				    }
				    else
				    if(size > AuditInspectionConstant.MAX_FILE_SIZE)
				    {
				        	errorList.add("<li><font color="+"red"+">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
				        	request.setAttribute("errorsList",errorList);
				        	return mapping.findForward("upload");
				        	
				     }
				     else
				        {
				    	 File file = null;
				    	 try{
				               File newFile =  new File(filePath + strFileName.getFileName());
				               FileOutputStream fos = new FileOutputStream(newFile);
				               fos.write(strFileName.getFileData());
				               fos.close();
				               file = newFile;
				             }catch(Exception ex){
				        	         ex.printStackTrace();
				           }
				           ArrayList lsUPloadDTO = new ArrayList();
				           UploadFileDTO uploadFileDTO = new UploadFileDTO();
				           uploadFileDTO.setFileName(strFileName.getFileName());//set doctype to uploaddto
				           uploadFileDTO.setFileSize(size);
				           uploadFileDTO.setFile(file);
				        
				           lsUPloadDTO.add(uploadFileDTO);
				           session.setAttribute("attachment1",lsUPloadDTO);
				         }
			     }
			     else
			     {
			    	 ArrayList arrList        = (ArrayList)session.getAttribute("attachment1");
			    	 logger.debug("REG UPLOAD From the UI list "+arrList.size());
			    	 
			    	 ArrayList arrForSession = new ArrayList();
			    	 
			    	 UploadFileDTO uploadFileDTO=null;
			    	 for(int i=0;i< arrList.size();i++)
			    	 {
			    		 uploadFileDTO = (UploadFileDTO)arrList.get(i);
			    		 currFileSize  = currFileSize + uploadFileDTO.getFileSize();//set doctype to uploaddto
			    		 arrForSession.add(uploadFileDTO);
			    	 }
			    	 FormFile forFile      = uploadForm.getTheFile();
			    	 			    	 
			    	 String fileExt = getFileExtension(forFile.getFileName());
					 logger.debug("REG UPLOAD File Extenssion :"+fileExt);
					 rule = new AuditInspectionRule();
					  errorList=rule.validateFileType(fileExt);
					   if(rule.isError())
					    {
					    	request.setAttribute("errorsList", errorList);
					    	return mapping.findForward("upload");
					    }
					   else
					   {   
						 int size = forFile.getFileSize();
					     currFileSize = currFileSize+size;
					     logger.debug("SIZE OF File In Else Block :"+size);
				    	 logger.debug("Combined FIle Size in Else Block :"+currFileSize);
						 if(currFileSize>AuditInspectionConstant.MAX_FILE_SIZE)
			    	     {
			    		   errorList.add("<li><font color="+"red"+">File Size is Greater Than 10MB,Please Select Other File.</font></li>");
				           request.setAttribute("errorsList",errorList);
				           return mapping.findForward("upload"); 
			    	     }
			    	     else
			    	     {
			    	       //uploadForm.setSizeOfFile(currFileSize);
			    	       if(!forFile.getFileName().equals(""))
			    	       {
				    	     uploadFileDTO = new UploadFileDTO();
				    	     uploadFileDTO.setFileName(forFile.getFileName());
				    	     File file = null;
					    	 try{
					               File newFile =  new File(filePath + forFile.getFileName());
					               FileOutputStream fos = new FileOutputStream(newFile);
					               fos.write(forFile.getFileData());
					               fos.close();
					               file = newFile;
					             }catch(Exception ex){
					        	         ex.printStackTrace();
					           }
				    	     uploadFileDTO.setFile(file);
				    	     arrForSession.add(uploadFileDTO);
				    	     try{   
				    		     File newFile =  new File(filePath + forFile.getFileName());
						         FileOutputStream fos = new FileOutputStream(newFile);
						         fos.write(forFile.getFileData());
						         fos.close();
				    	        }catch(Exception ex){
				    	    	   ex.printStackTrace();
				    	       }			    		 
		    		        }//End if
                           session.removeAttribute("attchment1");
                           session.setAttribute("attachment1", arrForSession);
			    	       
			             }//else END IF	
				    }//End Else Block		
			      }//else END IF
			     return (mapping.findForward("upload"));
			   }//end if
			    
			  
		 if(uploadForm.getRemoveAction().equalsIgnoreCase("remove"))
		 {
			 logger.debug("REG UPLOAD Action :"+uploadForm.getRemoveAction());
			 ArrayList arrList        = (ArrayList)session.getAttribute("attachment1");
			 
			 try{
			 String param[]=request.getParameterValues("checkbox");
			 for(int i=0;i<param.length;i++)
			 {
				 String checkedFileName =param[i];
				 logger.debug("Checked REG UPLOAD File Name :"+checkedFileName);
				 for(int j=0;j<arrList.size();j++)
				 { 
					 UploadFileDTO uploadFileDTO = (UploadFileDTO)arrList.get(j);
					 if(uploadFileDTO.getFileName().equalsIgnoreCase(checkedFileName))
					 {
						 File newFile      = new File(filePath + checkedFileName);
						 newFile.delete();
						 arrList.remove(j);
					 }
				 }
			 }
			 }catch(Exception ex ){ex.printStackTrace();} 
			 session.removeAttribute("attachemnt1");
			 session.setAttribute("attachment1", arrList);
			 return mapping.findForward("upload");
		 }
			logger.debug("End of REG UPLOAD action");

		
	 return mapping.findForward("failure");
	}
	private String getFileExtension(String fileName)
	{
		int pos       = fileName.lastIndexOf(".");
		String strExt = fileName.substring(pos+1,fileName.length()); 
		return strExt;
	}
}

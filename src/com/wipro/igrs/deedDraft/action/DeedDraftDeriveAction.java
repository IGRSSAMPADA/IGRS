package com.wipro.igrs.deedDraft.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.deedDraft.bd.DeedDraftDeriveBD;
import com.wipro.igrs.deedDraft.constant.DeedDraftConstant;
import com.wipro.igrs.deedDraft.dto.DeedDraftDTO;
import com.wipro.igrs.deedDraft.form.DeedDraftForm;

public class DeedDraftDeriveAction extends BaseAction{

	String forwardJsp ="";
	private Logger logger = Logger.getLogger(DeedDraftDeriveAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
		
		String label = (String)request.getParameter("label");
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String officeId = (String)session.getAttribute("loggedToOffice");
		String language = (String)session.getAttribute("languageLocale");
		
		if(form!= null)
		{
			DeedDraftForm eForm = (DeedDraftForm)form;
			DeedDraftDTO formDTO = eForm.getDeedDTO();
			DeedDraftDeriveBD bd = new DeedDraftDeriveBD();
			String actionName = null;
			if(label!= null)
			{
				
				if(label.equalsIgnoreCase("derive"))
				{
					formDTO.setRadioValue("");
					formDTO.setSelectedDeed("");
					formDTO.setDeedId("");
					ArrayList deedViewDetails = bd.getDraftDeedDetailsForDashboard(userId, language);
					if(deedViewDetails.size() >0)
					{
						eForm.setDeedDraftViewList(deedViewDetails);
						request.setAttribute("deedDraftViewList", deedViewDetails);
						
					}
					else
					{
						
							if(language.equalsIgnoreCase("en"))
								request.setAttribute(DeedDraftConstant.FAILURE_MSG,"No records found");
							else
								request.setAttribute(DeedDraftConstant.FAILURE_MSG,"कोई रिकॉर्ड नहीं मिला");
							
						
						
					}
					formDTO.setActionName("");
					forwardJsp = "deriveDeedDashboard";
				}
			}
		
			if(request.getParameter("actionName")!= null)
				actionName = request.getParameter("actionName");
			else
				actionName = formDTO.getActionName();
			
			logger.debug("ACTION NAME ----------->"+actionName);
			
			if(DeedDraftConstant.DERIVE_DEED_ACTION.equalsIgnoreCase(actionName))
			{
				boolean flag = bd.copyDeedDraftData(formDTO.getSelectedDeed(), userId, formDTO);
				if(flag)
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG_WITH_ID,"Deed has been derived successfully. Kindly go to Registration Process-> Deed Draft->View/Edit deed and complete the deed before consuming it in other modules.");
					else
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG_WITH_ID,"विलेख सफलतापूर्वक व्युत्पन्न कर लिया गया है। कृपया पंजीयन प्रक्रिया पर जाएं--> विलेख प्रारूप --> विलेख अवलोकन / संशोधन करें और इसे अन्य माड्यूलों में कन्ज्यूम होने से पूर्व पूर्ण करें।");
					forwardJsp = "confirmation";
				}
				else
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"Data has not been inserted properly.Try again");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"डाटा समुचित रूप से नहीं डाला गया है। पुनः प्रयास करें।");
					forwardJsp = "deriveDeedDashboard";
				}
				
			}
		/*	else if(actionName.equalsIgnoreCase("Print")){
				FormFile abc = formDTO.getDeedUploadPath();
				String path=pr.getValue("deed_draft_upload_location");
				String filePath = path+formDTO.getDeedId()+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC;
				String ext = getFileExtension((String)abc.getFileName());
				if("pdf".equalsIgnoreCase(ext)){
					
					boolean ret = generatePDF2(filePath, abc.getFileData());
					if(ret){
						formDTO.setUpOrNot("1");	
					}
					else{
						formDTO.setUpOrNot("0");
					}
					}
				else if("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext)
						|| "bmp".equalsIgnoreCase(ext)){
					ArrayList aaaa = new ArrayList();
					aaaa.add(abc.getFileData());
					boolean ret=generatePDF(filePath, aaaa);
					if(ret){
						formDTO.setUpOrNot("1");
					}
					else{
						formDTO.setUpOrNot("0");
					}
				}
				logger.debug("Uploaded successfu6lly");
				
				formDTO.setUploadCheck("0");
				forwardJsp = "printDeed1";
			}
			
			
			if("Final_upload".equalsIgnoreCase(actionName))
			{
				boolean flag = bd.saveDeedDraftPath(formDTO, userId);
				if(flag)
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG_WITH_ID,"Deed has been drafted successfully");
					else
						request.setAttribute(DeedDraftConstant.SUCCESS_MSG_WITH_ID,"डीड सफलतापूर्वक बना दी गयी है");
					forwardJsp = "confirmation";
				}
				else
				{
					if(language.equalsIgnoreCase("en"))
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"Data has not been inserted properly.Try again");
					else
						request.setAttribute(DeedDraftConstant.FAILURE_MSG,"डेटा ठीक से दर्ज नहीं किया गया । पुनः प्रयास करें");
					forwardJsp = "deriveDeedDashboard";
				}
			}
			String viewUpload = (String)request.getParameter("fwdName");
			if("viewUpload".equalsIgnoreCase(viewUpload)){
				try{
					String path=pr.getValue("deed_draft_upload_location");
					String filename = path+formDTO.getDeedId()+"/"+DeedDraftConstant.UPLOAD_NAME_SIGN_DOC;
					
					   // set the http content type to "APPLICATION/OCTET-STREAM
					   response.setContentType("application/download");
					   String file = filename.substring(filename.lastIndexOf("/")+1, filename.length());
					   // initialize the http content-disposition header to
					   // indicate a file attachment with the default filename
					   // "myFile.txt"
					  // String fileName = (String)formDTO.getCompThumbPath();
					   //Filename=\"myFile.txt\"";
					   response.setHeader("Content-Disposition", "attachment; filename="
								+ URLEncoder.encode(file,"UTF-8"));
					   
					   File fileToDownload = new File(filename);
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
				
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}*/
		}
		return mapping.findForward(forwardJsp);
	}
	/*private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
	private boolean generatePDF2(String fileName, byte[] array)throws Exception{
		boolean ret = false;
		
		try{
			File output = new File(fileName);
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			
			
			FileOutputStream fos = new FileOutputStream(output);
            fos.write(array);
            fos.close();
            ret=true;
		}
		catch(Exception e){
	
		}
		return ret;
		
	}
	
	private boolean generatePDF(String fileName,
			ArrayList<byte[]> arrays) throws Exception {
		boolean ret = false;
		logger.info("Entering method generatePDF");
		try {
			
			File output = new File(fileName);
			if (output.exists()) {
				logger.info("file already exists deleting....");
				output.delete();
			}
			if (output.getParentFile().exists() == false) {
				logger.info("Parent not found creating directory....");
				output.getParentFile().mkdirs();
			}
			Image pdfImage;
			logger.info("No. of arrays : " + arrays.size());
			Document document = new Document(PageSize.A4, 20, 20, 20, 20);
			OutputStream fos = new FileOutputStream(fileName, false);
			PdfWriter.getInstance(document, fos);
			document.open();
			for (byte[] bytes : arrays) {
				pdfImage = Image.getInstance(bytes);
				pdfImage.scalePercent(25.0f);
				document.newPage();
				document.add(pdfImage);
			}

			document.close();
			ret=true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			
		}
		logger.info("Leaving method generatePDF");
		return ret;
	}*/
}

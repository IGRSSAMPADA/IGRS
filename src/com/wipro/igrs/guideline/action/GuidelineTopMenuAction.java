package com.wipro.igrs.guideline.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.guideline.bd.GuidelineViewBD;
import com.wipro.igrs.guideline.constant.CommonConstant;
import com.wipro.igrs.guideline.dto.GuidelineDTO;
import com.wipro.igrs.guideline.form.GuidelinePreparationForm;

public class GuidelineTopMenuAction extends Action {


		private Logger logger = Logger.getLogger(GuidelineTopMenuAction.class);
		String forwardJsp = new String("view1");

		/**
		 * @param mapping
		 * @param form
		 * @param request
		 * @return ActionForward
		 * @throws Exception
		 */
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String page = request.getParameter("page");
			System.out.println(page);
			HttpSession session = request.getSession(true);
			if("viewFinal".equals(page))
			{
				session.setAttribute("langModule", "guidelineTopFinal");
			}
			else
			{
				session.setAttribute("langModule", "guidelineTopDraft");
			}
			
			
			String language=(String)session.getAttribute("languageLocale");
			

			GuidelinePreparationForm eForm = (GuidelinePreparationForm) form;
			GuidelineDTO formDTO=eForm.getGuideDTO();
			
			ActionMessages messages = new ActionMessages();		
			String districtName = null;
			String target = null;
			String actionName = null;
			
			actionName=formDTO.getActionName();
			formDTO.setCheck("");
			
			if (form != null) {
				
				GuidelineViewBD viewBD = new GuidelineViewBD();
				
				
				formDTO.setLanguage(language);
				//ArrayList areaTypes = viewBD.getAreasTypeList();
				//eForm.setAreaTypeList(areaTypes);
				
				
				/**
				 * 
				 * Getting Status for the guideline from COMMONCONSTANTS
				 */
				
				/*HashMap guidelineStatusList = new HashMap();
				
				if(formDTO.getLanguage().equalsIgnoreCase("en"))
				{
					guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.DSP_GUIDELINESTATUS_DRAFT);
					guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.DSP_GUIDELINESTATUS_FINAL);
				}
				else
				{
					guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_DRAFT, CommonConstant.H_DSP_GUIDELINESTATUS_DRAFT);
					guidelineStatusList.put(CommonConstant.GUIDELINESTATUS_FINAL, CommonConstant.H_DSP_GUIDELINESTATUS_FINAL);
				}
				
				
				eForm.setGuidelineStatusList(guidelineStatusList);*/
				
				
			
			if(page!=null){
				if("viewFinal".equals(page)){
					ArrayList districtList = viewBD.getDistrictList(language);
					eForm.setDistrictList(districtList);
					
					ArrayList financialYearList = viewBD.getActiveFinancialYearList();
					eForm.setFinancialYearList(financialYearList);
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					
					eForm.setGuideDTO(new GuidelineDTO());
					formDTO.setActionName("");
					formDTO.setCheck("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setFinancialYear("");
					formDTO.setWard("");
					formDTO.setStatus("");
					formDTO.setGuideType("F");
					formDTO.setHdnAppType("F");
					eForm.getGuideDTO().setGuideType("F");
					eForm.getGuideDTO().setHdnAppType("F");
					formDTO.setGuidelineStatus("");
					session.removeAttribute("mohallalist");
					//************for log*****************//
					

	                //****************************//
					forwardJsp = CommonConstant.TOP_FINAL_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
				}
			}
			
			
			
			if("downloadpdffinal".equalsIgnoreCase(actionName))
			{
				
				
				
				String filename=null;
				
				
				
				
				String guideId=viewBD.getFinalGuidelineId(formDTO);
				
				 
				if(guideId!=null && !guideId.equalsIgnoreCase(""))
				{
				
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
					
				      String downloadPath=proper.getValue("GuidelineSavePath");
				 	downloadPath=downloadPath.replace("-", File.separator);

				      if("en".equalsIgnoreCase(language))
					 	{
				    	  filename=downloadPath+File.separator+guideId+File.separator+"ENGLISH"+File.separator+"GuidelineFull.pdf";
					 	}
					 	else
					 	{
					 		filename=downloadPath+File.separator+guideId+File.separator+"HINDI"+File.separator+"GuidelineFull.pdf";
					 	}
					 	File Fr1 = new File(filename);
				
					try {
						if(Fr1.exists())
					 	{
							response.setContentType("application/download");

				  			  
				 			   response.setHeader("Content-Disposition", "attachment; filename="
				 						+ URLEncoder.encode(filename,"UTF-8"));
				 			   
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
				 			  forwardJsp = CommonConstant.TOP_FINAL_VIEW ;
								logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
								formDTO.setActionName("");
					 	}
						
						else
						{
							formDTO.setCheck("Y");
							forwardJsp = CommonConstant.TOP_FINAL_VIEW ;
							logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
							formDTO.setActionName("");
						}
						/*if("P1".equalsIgnoreCase(formDTO.getPraroopTypeId()))
						{
							 filename = filename+CommonConstant.PDF_NAME_PRAROOP1_EXT;
						}
						
						else if("P2".equalsIgnoreCase(formDTO.getPraroopTypeId()))
						{
							 filename = filename+CommonConstant.PDF_NAME_PRAROOP2_EXT;
						}
						
						else if("P3".equalsIgnoreCase(formDTO.getPraroopTypeId()))
						{
							 filename = filename+CommonConstant.PDF_NAME_PRAROOP3_EXT;
						}
						
						else
						{
							 filename = filename+CommonConstant.PDF_NAME_PRAROOPALL_EXT;
						}*/
						 
						
						
						}
					
					catch (FileNotFoundException e) {
						
						forwardJsp = CommonConstant.TOP_FINAL_VIEW ;
						logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
						formDTO.setActionName("");
						// TODO Auto-generated catch block
						
					}
					
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else
				{
					formDTO.setCheck("X");
					forwardJsp = CommonConstant.TOP_FINAL_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
					formDTO.setActionName("");
				}
				
			}
			
			if("checkId".equalsIgnoreCase(actionName))
			{
				ArrayList guidelineList= viewBD.getGuidelineIdList(eForm);
				eForm.setGuidelineIdList(guidelineList);
				
				if(guidelineList==null || guidelineList.size()==0)

				{
					eForm.setGuidelineIdList(new ArrayList());
					formDTO.setCheck("F");
					forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
					
				}
				
				else
				{
					
					formDTO.setCheck("T");
					forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
					
				}
				
			}
			
			
			if("downloadpdfdraft".equalsIgnoreCase(actionName))
			{
				
				
				
				String filename=null;
				
				
				
				
				String guideId=formDTO.getGuidelineIdTop();
				
				 
				if(guideId!=null && !guideId.equalsIgnoreCase(""))
				{
					
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
					
				      String downloadPath=proper.getValue("GuidelineSavePath");
				 	downloadPath=downloadPath.replace("-", File.separator);

				      if("en".equalsIgnoreCase(language))
					 	{
				    	  filename=downloadPath+File.separator+guideId+File.separator+"ENGLISH"+File.separator+"GuidelineFull.pdf";
					 	}
					 	else
					 	{
					 		filename=downloadPath+File.separator+guideId+File.separator+"HINDI"+File.separator+"GuidelineFull.pdf";
					 	}
					 	File Fr1 = new File(filename);
				
					try {
						if(Fr1.exists())
					 	{
							response.setContentType("application/download");

				  			  
				 			   response.setHeader("Content-Disposition", "attachment; filename="
				 						+ URLEncoder.encode(filename,"UTF-8"));
				 			   
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
				 			  formDTO.setCheck("X");
								forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
								logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
								formDTO.setActionName("");
					 	}
						
						else
						{
							formDTO.setCheck("Y");
							forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
							logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
								formDTO.setActionName("");
						}
					
						
						}
					
					catch (FileNotFoundException e) {
						
						forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
						logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
						formDTO.setActionName("");
						// TODO Auto-generated catch block
						
					}
					
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				else
				{
					formDTO.setCheck("X");
					forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
					formDTO.setActionName("");
				}
				
			}
			
			if(page!=null){
				if("viewDraft".equals(page)){
					//************for log*****************//
					

	                //****************************//
				
					ArrayList districtList = viewBD.getDistrictList(language);
					eForm.setDistrictList(districtList);
					
					ArrayList financialYearList = viewBD.getActiveFinancialYearList();
					
					eForm.setFinancialYearList(financialYearList);
					eForm.setTehsilList(new ArrayList());
					eForm.setWardList(new ArrayList());
					eForm.setMohallaList(new ArrayList());
					eForm.setPatwariList(new ArrayList());
					eForm.setGuidelineIdList(new ArrayList());
					eForm.setGuideDTO(new GuidelineDTO());
					
					
					
					
					formDTO.setActionName("");
					formDTO.setDistrict("");
					formDTO.setDistrictID("");
					formDTO.setFinancialYear("");
					formDTO.setWard("");
					formDTO.setStatus("");
					formDTO.setGuidelineStatus("");
					session.removeAttribute("mohallalist");
					forwardJsp = CommonConstant.TOP_DRAFT_VIEW ;
					logger.debug("GUIDELINEVIEW ACTION::  FORWARDED TO :-     "+forwardJsp);
				}
			}
			
			}
					return mapping.findForward(forwardJsp);
		
	
	
			}
		
}

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveats.bd.CaveatsDelegate;
import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.caveats.form.CaveatsForm;
import com.wipro.igrs.common.IGRSCommon;

/**
* 
* CaveatsViewSearchAction.java <br>
* CaveatsViewSearchAction <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes" })
public class CaveatsViewSearchAction extends BaseAction {
	
	private Logger logger = (Logger) Logger
			.getLogger(CaveatsViewSearchAction.class);

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
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		//logger.debug("WE ARE IN CaveatsViewSearchAction Debug");
		//logger.info("WE ARE IN  CaveatsViewSearchAction INFO");
		String FORWAD_SUCCESS = "failure";
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO cDTO = fm.getCaveatsDTO();
			CaveatsDelegate cBD = new CaveatsDelegate();
			ArrayList viewSearchlist = new ArrayList();
			String language=(String)session.getAttribute("languageLocale");
			String activityid = request.getParameter("ACTID");
			String userId = (String) session.getAttribute("UserId");
			
			String csrfToken = (String) session.getAttribute("ESLMNP");
			fm.setCsrfToken(csrfToken);
			
			cDTO.setLanguage(language);
			

			if(activityid!=null)
			{
			IGRSCommon save=null;
			try {
				save = new IGRSCommon();
				save.saveactivitylog(userId, activityid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
			
			if ("view".equalsIgnoreCase(request.getParameter("pageName"))) {
				String referenceID = request.getParameter("referenceID");
				String download = request.getParameter("download");
				String downloadRelease = request.getParameter("downloadRelease");
				String downloadCheck = request.getParameter("downloadCheck");
				
				//for download
				if("yes".equals(download)) {
					/*try {
						byte[] content = cDTO.getDocContents();
						String filename = cDTO.getDocumentName();
						if(content != null) {
							CaveatsViewSearchAction.downloadDocument(response, content, filename);
						}
					} catch (Exception e) {
					}		*/
					try
		        	{
		        	response.setContentType("application/download");
		            
					String filename=cDTO.getUploaded_doc_path();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

		                                                + URLEncoder.encode(cDTO.getDocumentName().toString(),"UTF-8"));
					
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
		            out.flush();
		            cDTO.setActionName("");
		            FORWAD_SUCCESS = "confirm";
		            
		        
		        }
	            catch (Exception e) {
				}
		       
				}
				if("yes".equals(downloadCheck)) {
					/*try {
						byte[] content = cDTO.getDocContents();
						String filename = cDTO.getDocumentName();
						if(content != null) {
							CaveatsViewSearchAction.downloadDocument(response, content, filename);
						}
					} catch (Exception e) {
					}		*/
					try
		        	{
		        	response.setContentType("application/download");
		            
					String filename=cDTO.getUploaded_doc_path();
					File fileToDownload = new File(filename);

					response.setHeader("Content-Disposition", "attachment; filename="

		                                                + URLEncoder.encode(cDTO.getDocName().toString(),"UTF-8"));
					
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
		            out.flush();
		            cDTO.setActionName("");
		            FORWAD_SUCCESS = "confirm";
		            
		        
		        }
	            catch (Exception e) {
				}
		       
					
			
				}else if("yes".equals(downloadRelease)) {
					downloadDocument(response, cDTO.getReleaseContents(), cDTO.getReleaseDocName());
				}
				else {
					ArrayList list = fm.getSearchResultList();
					for (Object item : list) {
						CaveatsDTO tmpDTO = (CaveatsDTO) item;
						if(tmpDTO.getReferenceID().equals(referenceID)) {
							cBD.getotherDetails(tmpDTO);
							fm.setCaveatsDTO(tmpDTO);
							cDTO = fm.getCaveatsDTO();
							cBD.searchByRefID(cDTO,language);
							break;
						}
					}
				}
				FORWAD_SUCCESS = "refId";
			} else if ("search".equalsIgnoreCase(request.getParameter("pageName"))) {
				//logger.info("Inside Search by All other fields");
				ArrayList<String> errorList = validate(cDTO, session);
				if (errorList.isEmpty()) {
					viewSearchlist = cBD.searchByAll(
							cDTO.getReferenceIDSearch(),
							cDTO.getRegistrationNoSearch(), cDTO.getFlag(),
							cDTO.getFromDate(), cDTO.getToDate(),language);
					fm.setSearchResultList(viewSearchlist);
					session.setAttribute("searchResultList", viewSearchlist);
					session.setAttribute("caveatfrm", fm);
					FORWAD_SUCCESS = "regNo";
				} else {
					request.setAttribute("errorsList", errorList);
					FORWAD_SUCCESS = "searchPage";
				}
			} else if ("yes".equalsIgnoreCase(request.getParameter("isMenuClick"))) {
				//logger.info("Inside isMenuClick = yes");
				session.removeAttribute("searchResultList");
				session.removeAttribute("caveatfrm");
				FORWAD_SUCCESS = "searchPage";
			} else {
				
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			//logger.error(e.getMessage(),e);
			return mapping.findForward("faliure");
		}
	}

	public static void downloadDocument(HttpServletResponse res, byte[] docContents, String fileName) {
		try {
			OutputStream os = res.getOutputStream();
			res.setContentType("application/download");
			res.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(fileName,"UTF-8"));
			os.write(docContents);
			os.flush();
			os.close();
		} catch (Exception e) {
		}
	}
	
	private ArrayList<String> validate(CaveatsDTO cDTO, HttpSession session) {
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			Calendar sysCal = Calendar.getInstance();
			Date currSysDate = (Date) session.getAttribute("currSysDate");
			Date fromDate, toDate;
			boolean isFromDate, isToDate;
			SimpleDateFormat inputDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy");
			inputDateFormat.setLenient(false);
			sysCal.setTime(currSysDate);
			sysCal.set(Calendar.HOUR_OF_DAY, 0);
			sysCal.set(Calendar.MINUTE, 0);
			sysCal.set(Calendar.SECOND, 0);
			sysCal.set(Calendar.MILLISECOND, 0);
			currSysDate = sysCal.getTime();
			fromDate = new Date(currSysDate.getTime());
			toDate = new Date(currSysDate.getTime());
			String fromDateString = cDTO.getFromDate();
			String toDateString = cDTO.getToDate();

			try {
				fromDate = inputDateFormat.parse(fromDateString);
				isFromDate = true;
				
			} catch (ParseException e) {
				isFromDate = false;
			}
			try {
				toDate = inputDateFormat.parse(toDateString);
				isToDate = true;
			} catch (ParseException e) {
				isToDate = false;
			}
			if (isFromDate) {
				if (currSysDate.before(fromDate)) {
					errorList
							.add("Duration From cannot be greater than current date/से अवधि आज की तारीख से अधिक नहीं हो सकता");
				}
			}
			
			
			if (isFromDate==true  && isToDate==false)
			{
			errorList
			.add("Choose both the date fields./तारीख के दोनों क्षेत्रों चुनें");
			}
			
			if (isFromDate==false  && isToDate==true)
			{
			errorList
			.add("Choose both the date fields./तारीख के दोनों क्षेत्रों चुनें");
			}
		
			if (isToDate) {
				if (currSysDate.before(toDate)) {
					errorList
							.add("Duration To cannot be greater than current date/अवधि तक आज की तारीख से अधिक नहीं हो सकता");
				}
			}
			
			if (isFromDate && isToDate) {
				if (fromDate.after(toDate)) {
					errorList
							.add("Duration From cannot be greater than Duration To/से अवधि ,अवधि तक से अधिक नहीं हो सकता");
				}
			}
		} catch (Exception e) {
		}
		return errorList;
	}
	
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}
	
}

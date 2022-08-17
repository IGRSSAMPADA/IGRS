/**
 * ServiceProviderAction.java
 */

package com.wipro.igrs.sp.action;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.UserRegistration.action.UserRegistrationAction;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.common.ServiceProviderConstants;
import com.wipro.igrs.sp.bd.ServiceProviderBD;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;
import com.wipro.igrs.sp.form.ServiceProviderForm;
import com.wipro.igrs.sp.rule.ServiceProviderRule;


public class ServiceProviderAction extends BaseAction {

	ServiceProviderBD serviceProviderBD = null;
	ServiceProviderDTO providerDTO = null;
	ServiceProviderRule serviceProviderRule = null;

	ArrayList errorlist = null;
	ArrayList listFileNames = null;
	String licencett = new String();
	String distId = new String();
	String bankid = new String();
	String userid = new String();
	String spuname = new String();
	String forwardpage;
	String sptypeid;
	String spbanktypeid;
	String spuser=null;

	public ServiceProviderDTO providerDTO1 = new ServiceProviderDTO();
	public ServiceProviderDTO providerDTO3 = new ServiceProviderDTO();

	private Logger logger = (Logger) Logger.getLogger(ServiceProviderAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		String actionname = request.getParameter("name");
		ServiceProviderForm providerForm = (ServiceProviderForm) form;

		if (form != null) {
			try {
				//added by shruti
				providerDTO=providerForm.getProviderDTO();
				//end of code
				serviceProviderBD = new ServiceProviderBD();
				providerForm.setSpUserTypeList(serviceProviderBD.getSPUserTypeList());
				sptypeid = serviceProviderBD.getSpUserTypeId();
				spbanktypeid = serviceProviderBD.getSpBankUserTypeId();
				userid = session.getAttribute("UserId").toString();
				providerForm.setDistricts(serviceProviderBD.getUserDistrict(userid));
				distId = providerForm.getProviderDTO().getSpdistrctid();
				providerForm.setTehsils(serviceProviderBD.getThesil(distId));
				providerForm.setSpbanks(serviceProviderBD.getSPBanks());
				providerForm.setUserDetailsList(serviceProviderBD.getUserDetails(providerDTO,request));
				request.setAttribute("userDetailsList", providerForm.getUserDetailsList());
				
				String strActionType = providerForm.getActionType();
				
				if(actionname!=null && actionname.equals("Create"))
				{
					try
					{
					forwardpage="spSearch";
					}
					catch(Exception e)
					{
						logger.info("Forward null in SP ACTION CLASS");
					}
				}
				if (actionname != null&& actionname.equals("searchSpUserDetails")) {
					try {
						forwardpage = searchSpUserDetails(mapping,
								providerForm, request, response, session);
					} catch (Exception e) {
						logger.error("Exception in execute() ServiceProviderAction"+ e);
					}
				}
				
				if (actionname != null && actionname.equals("spView")) {
					try {
						forwardpage = spView(mapping, providerForm, request,
								response, session);
					} catch (Exception e) {
						logger.error("Exception in execute() ServiceProviderAction"+ e);
					}
				}
				
				
				//On click of reference id
				if (request.getParameter("id") != null) {
					String refid = request.getParameter("id");
					spuser = request.getParameter("userName");
					forwardpage = spViewDisaplay(mapping, providerForm,
							request, response, session, refid);
				} 
				if (strActionType!=null && strActionType.equals("SearchUser")) {
					forwardpage = searchSpUserDetails(mapping, providerForm,
							request, response, session);
				}
				else if (strActionType!=null && strActionType.equals("spView")) {
					forwardpage = spView(mapping, providerForm, request,
							response, session);
				}
				/*else if (strActionType!=null && strActionType.equals("returnattachment")) {
					forwardpage = spreturnAttachment(mapping, providerForm,
							request, response, session);
				}*/
				else if (strActionType!=null && strActionType.equals("returnattachment1")) {
					forwardpage = spreturnAttachment1(mapping, providerForm,
							request, response, session);
				}
				else if (strActionType!=null && strActionType.equals("Submit")) {
					try {
						forwardpage = submitUserDetailsNew(mapping,
								providerForm, request, response, session);

					} catch (Exception e) {
						logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+ e);
					}
				}
				else if (strActionType!=null && strActionType.equals("sptypeselectionaction")) {
					String[] spusertypeAry = providerForm.getProviderDTO().getSpusertypename().split("~");
					if (spusertypeAry != null && spusertypeAry.length == 2) {
						providerDTO = providerForm.getProviderDTO();
						providerDTO.setSpusertypeid(spusertypeAry[0]);
						licencett = providerDTO.getSpusertypeid();
						providerDTO.setLicencett(licencett);
						providerDTO.setSplicencenumber("");
						providerForm.setUserDetailsList((ArrayList)request.getAttribute("userDetailsList"));
					}
					forwardpage = "spSearch";
				}
				else if (strActionType!=null && strActionType.equals("selectedspbank")) {
					String bankid = providerForm.getProviderDTO().getSpbankid();
					forwardpage = "spSearch";
				}
				else if (strActionType!=null && strActionType.equals("selecteddistrict")) {
					try {
						distId = providerForm.getProviderDTO().getSpdistrctid();
						providerForm.setTehsils(serviceProviderBD.getThesil(distId));
						forwardpage = "spSearch";
					} catch (Exception e) {
						logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+ e);
					}
				}
				else if (strActionType!=null && strActionType.equals("Reset")) {
					//session.removeAttribute("userDetailsList");
					//session.removeAttribute("attachment1");
					listFileNames = null;
					providerForm.getProviderDTO().setSpusername("");
					forwardpage = "spSearch";
				}
				else if (strActionType!=null && strActionType.equals("IssueCertificate")) {
					ArrayList userDetailsList = null;
					serviceProviderBD = new ServiceProviderBD();
					userid = providerDTO.getSpusername();
					userDetailsList = serviceProviderBD.getdeacLicencedUserDetailsList(providerDTO,request);
					if (userDetailsList != null && userDetailsList.size() > 0) {
						request.setAttribute("userDetailsList",userDetailsList);
						forwardpage = ServiceProviderConstants.ISSUECERTIFICATE;
					}
				}
				else if (strActionType!=null && strActionType.equals("IssueRenewalCertificate")) {
					ArrayList userDetailsList = null;
					serviceProviderBD = new ServiceProviderBD();
					userid = providerDTO.getSpusername();
					providerDTO = serviceProviderBD.renewLicence(providerDTO);
					providerForm.setProviderDTO(providerDTO);
					forwardpage = ServiceProviderConstants.RENEW_LICENSE_CERTIFICATE;
				}
				else if (strActionType!=null && strActionType.equals("RenewLicence")) {
					
					serviceProviderBD = new ServiceProviderBD();
					providerDTO.setSpusername(spuser);
					//userid=providerForm.getSpusername();
					//userid = providerDTO.getSpusername();
					providerDTO = serviceProviderBD.renewLicence(providerDTO);
					providerForm.setProviderDTO(providerDTO);
					providerDTO1 = providerForm.getProviderDTO();
					logger.info(">>>>>>>>" + spuser);
					String[] spUname = { spuser };
					boolean checkUserLicenceRenewal = serviceProviderBD.checkUserLicenceRenewal(spUname);
					if (checkUserLicenceRenewal == true) {
						forwardpage = "spLicenseRenewalRestricted";
					} else {
						forwardpage = ServiceProviderConstants.RENEWLICENSE;
					}
				}
				else if (strActionType!=null && strActionType.equals("spLicenseRenewal")) {
					logger.info("INSIDE LICENSE RENEWAL METHOD");
					forwardpage = executespLicenseRenewal(mapping,
							providerForm, request, response, session);
				}
				else if(strActionType!=null && strActionType.equals("uploadLicense"))
				{
					forwardpage = executespLicenseUpload(mapping,
							providerForm, request, response, session);
				}
				else if(strActionType!=null && strActionType.equals("uploadRenewedLicense"))
				{
					forwardpage = executeUploadRenewedLicense(mapping,
							providerForm, request, response, session);
				}
				
				else if (strActionType!=null && strActionType.equals("remove")) {
					forwardpage = executeRemoveAttachment(mapping,
							providerForm, request, response, session);
				}
				else if (strActionType!=null && strActionType.equals("removeUploadedLicense")) {
							forwardpage = executeRemoveUploadedLicense(mapping,
									providerForm, request, response, session);
				}
				else if (strActionType!=null && strActionType.equals("removeUploadedRenewedLicense")) {
					forwardpage = executeRemoveUploadedRenewedLicense(mapping,
							providerForm, request, response, session);
		}
				
				else if (strActionType!=null && strActionType.equals("removeRenewDoc")) {
					forwardpage = executeRemoveRenewDoc(mapping, providerForm,
							request, response, session);
				}
				if (request.getParameter("actionType") != null) {
					if (request.getParameter("actionType").equalsIgnoreCase("SPLicence")) {
						try {
							forwardpage = executeGenereteSPLicence(mapping,
									providerForm, request, response, session);
						} catch (Exception e) {
							logger.error("Exception in execute() ServiceProviderAction"+ e);
						}
					}
				}
				if (session.getAttribute("attachment1") != null) {
					listFileNames = (ArrayList) session.getAttribute("attachment1");
				}
				
			} catch (Exception e) {
				logger.info("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+ e);
			}

		}
		logger.error("FORWARD PAGE" + forwardpage);
		return mapping.findForward(forwardpage);
	}

	/*
	*//**
	 * Method name :executeServiceProvider
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param session
	 * @return /** Method name :executeServiceProviderLicence
*/
	private String executeGenereteSPLicence(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		try {
			providerDTO = providerForm.getProviderDTO();
			String spdistrictid = request.getParameter("did");
			String licencenumber = serviceProviderBD.genereteSPLicence(spdistrictid, licencett);
			providerDTO.setSplicencenumber(licencenumber);
			providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
			providerDTO.setSpDurationTo(providerForm.getDurationTo());
			//session.setAttribute("spdto", providerDTO);
			serviceProviderBD.storelicence(providerDTO);
			providerForm.setProviderDTO(providerDTO);
			userid = session.getAttribute("UserId").toString();
			providerForm.setDistricts(serviceProviderBD.getUserDistrict(userid));
			distId = providerForm.getProviderDTO().getSpdistrctid();
			providerForm.setTehsils(serviceProviderBD.getThesil(distId));
			providerDTO3 = providerDTO;
			forwardpage = "spSearch";
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>" + e);
		}
		return forwardpage;
	}

	/**
	 * Method name :searchSpUserDetails
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private String searchSpUserDetails(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		ArrayList userDetailsList = null;
		try {
			serviceProviderBD = new ServiceProviderBD();
			providerDTO = providerForm.getProviderDTO();
			userDetailsList = serviceProviderBD.getUserDetails(providerDTO,request);
			serviceProviderRule = new ServiceProviderRule();
			spuname = providerForm.getProviderDTO().getSpusername();

			String[] spUname = new String[] { spuname };
			boolean checkUserLicenceIssuance = serviceProviderBD.checkUserLicenceIssuance(spUname);
			if (checkUserLicenceIssuance == true) {
				forwardpage = "spLicenceIssuanceRestricted";
			}

			else if (userDetailsList != null && userDetailsList.size() > 0) {
				ArrayList users = serviceProviderBD.getSPUsers();
				ArrayList tahsils = serviceProviderBD.getSPTehsils();
				 request.setAttribute("userDetailsList", userDetailsList);
				/*session.setAttribute("userDetailsList", userDetailsList);*/
				forwardpage = "spSearch";
			} else {

				errorlist = new ArrayList();
				errorlist.add("<font color=red>No Results For This UserName</font>");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_FLAG,"true");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_LIST,errorlist);
				forwardpage = "spSearch";
			}
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	/**
	 * Method name :spView
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private String spView(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String forwardpage = null;
		ArrayList userDetailsList = null;
		try {
			//session.removeAttribute("userDetailsList");
			serviceProviderBD = new ServiceProviderBD();
			providerDTO = providerForm.getProviderDTO();
			String userid[] = new String[1];
			userid[0] = session.getAttribute("UserId").toString();
			userDetailsList = serviceProviderBD.getrefrenceId(userid,providerDTO, request);
			if (providerDTO.equals("")) {
				forwardpage = "spView";
			} else {
				request.setAttribute("userDetailsList", userDetailsList);
				providerForm.setUserDetailsList(userDetailsList);
				//session.setAttribute("userDetailsList", userDetailsList);
				forwardpage = "spViewDisplay";
			}
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	/**
	 * Method name :spViewDisaplay
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private String spViewDisaplay(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session, String refid) {

		String forwardpage = null;
		ArrayList userDetailsList = null;
		try {
			serviceProviderBD = new ServiceProviderBD();
			serviceProviderRule = new ServiceProviderRule();
			ArrayList licenceuserlist = serviceProviderBD.getlicenceuserdetails(refid);
			request.setAttribute("licenceuserlist", licenceuserlist);
			providerForm.setLicenceuserlist(licenceuserlist);
			//session.setAttribute("licenceuserlist", licenceuserlist);
			forwardpage = "spViewUser";
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);

		}
		return forwardpage;
	}

	/**
	 * Method name :spreturnAttachment
	 * 
	 * @param mapping
	 * @param providerForm
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * 
	 */
	private String spreturnAttachment(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String forwardpage = null;
		ArrayList userDetailsList = null;
		try {
			providerDTO = providerForm.getProviderDTO();
			providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
			providerDTO.setSpDurationTo(providerForm.getDurationTo());
			providerDTO.setSpOfficeAddress(providerForm.getProviderDTO().getSpOfficeAddress());
			providerDTO.setSpOtherInformation(providerForm.getProviderDTO().getSpOtherInformation());
			providerDTO.setSplicencenumber(providerForm.getProviderDTO().getSplicencenumber());
			providerDTO3 = providerDTO;
			forwardpage = "spSearch";
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	private String spreturnAttachment1(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		try {
			providerDTO = providerDTO1;
			providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
			providerDTO.setSpDurationTo(providerForm.getDurationTo());
			providerDTO1 = providerDTO;
			providerForm.setProviderDTO(providerDTO);
			forwardpage = ServiceProviderConstants.RENEWLICENSE;
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	private String submitUserDetailsNew(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String forwardpage = null;

		try {
			serviceProviderBD = new ServiceProviderBD();
			providerDTO = providerForm.getProviderDTO();
			String strFilePath = (String) session.getAttribute("FilePath");

			if (licencett.equals(sptypeid)) {
				String[] spUser = new String[3];
				spUser[0] = ServiceProviderConstants.SP_ROLE_GROUP;
				spUser[1] = ServiceProviderConstants.SP_USER;
				spUser[2] = providerDTO.getSpusername();
				providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
				providerDTO.setSpDurationTo(providerForm.getDurationTo());
				boolean submitUserDetailsNew = serviceProviderBD
						.submitspUserDetailsNew(providerDTO, listFileNames,
								strFilePath, session, spUser);
				providerForm.setLicensenumber(providerDTO.getSplicencenumber());
				if (submitUserDetailsNew == true) {
					forwardpage = "spSuccess";
				}
			} else if (licencett.equals(spbanktypeid)) {
				String[] spBankUser = new String[3];
				spBankUser[0] = ServiceProviderConstants.SP_BANK_ROLE_GROUP;
				spBankUser[1] = ServiceProviderConstants.SP_BANK_USER;
				spBankUser[2] = providerDTO.getSpusername();
				providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
				providerDTO.setSpDurationTo(providerForm.getDurationTo());
				boolean submitspBankDetailsNew = serviceProviderBD
						.submitspBankDetailsNew(providerDTO, listFileNames,
								strFilePath, session, spBankUser);
				providerForm.setLicensenumber(providerDTO.getSplicencenumber());
				if (submitspBankDetailsNew == true) {
					forwardpage = "spSuccess";
				}
			}

			else {
				errorlist = new ArrayList();
				errorlist.add("<font color=red>Licence Information Has Not been Submitted SuccessFully..</font>");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_FLAG,"true");
				request.setAttribute(ServiceProviderConstants.SERVICEPROVIDER_ERROR_LIST,errorlist);
				forwardpage = "spSearch";
			}
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	private String executespLicenseRenewal(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String forwardpage = null;

		try {
			providerDTO = providerDTO1;
			providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
			providerDTO.setSpDurationTo(providerForm.getDurationTo());
			providerForm.setProviderDTO(providerDTO);
			providerDTO1 = providerDTO;
			providerDTO = providerForm.getProviderDTO();
			String licencenumber = providerDTO.getSplicencenumber();
			licencett = licencenumber.substring(3, 4);
			String strFilePath = (String) session.getAttribute("FilePath");

			if (licencett.equals(sptypeid)) {
				boolean submitUserDetails = serviceProviderBD
						.submitspUserDetails(providerDTO, listFileNames,
								strFilePath, session);
				logger.debug("Wipro in ServiceProviderAction- "
						+ "renewsptypeidaction()");
				if (submitUserDetails == true) {
					logger.debug("Wipro in ServiceProviderAction - "
							+ "submituserdetails");
					session.setAttribute("licencedto", providerDTO);
					logger.debug("Wipro in ServiceProviderAction - "
							+ "before forward renew in case of sp");
					forwardpage = "spLicenserenewSuccess";
				}
			} else if (licencett.equals(spbanktypeid)) {
				boolean submitspBankDetails = serviceProviderBD
						.submitspBankDetails1(providerDTO, listFileNames,
								strFilePath, session);

				logger.debug("Wipro in ServiceProviderAction - "
						+ "renewspbanktypeidaction()");

				if (submitspBankDetails == true) {
					logger.debug("Wipro in ServiceProviderAction - "
							+ "submitspbankdetails");
					session.setAttribute("licencedto", providerDTO);
					logger.debug("Wipro in ServiceProviderAction - "
							+ "before forward renew in case of sp bank");
					forwardpage = "spLicenserenewSuccess";
				}

			}
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}

	private String executeRemoveAttachment(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String forwardpage = null;
		try {
			providerDTO = providerDTO3;
			providerForm.setProviderDTO(providerDTO);
			session.removeAttribute("attachment1");
			listFileNames = null;
			providerForm.setDurationFrom(providerDTO.getSpDurationFrom());
			providerForm.setDurationTo(providerDTO.getSpDurationTo());
			userid = session.getAttribute("UserId").toString();
			providerForm.setDistricts(serviceProviderBD.getUserDistrict(userid));
			distId = providerForm.getProviderDTO().getSpdistrctid();
			providerForm.setTehsils(serviceProviderBD.getThesil(distId));
			providerForm.setProviderDTO(providerDTO);
			forwardpage = "spSearch";

		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;

	}

	private String executeRemoveRenewDoc(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String forwardpage = null;
		try {
			providerDTO = providerDTO1;
			providerForm.setProviderDTO(providerDTO);
			session.removeAttribute("attachment1");
			listFileNames = null;
			providerForm.setDurationFrom(providerDTO.getSpDurationFrom());
			providerForm.setDurationTo(providerDTO.getSpDurationTo());
			providerForm.setProviderDTO(providerDTO);
			forwardpage = ServiceProviderConstants.RENEWLICENSE;
		} catch (Exception e) {
			logger.error("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return forwardpage;
	}
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.info("EXCEPTION IN SERVICE PROVIDER ACTION>>>>>"+e);
		}
		return "";
	}
	private String executespLicenseUpload(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String forwardpage = null;
		ArrayList errorList=new ArrayList(); 
		try {
			FormFile uploadedFile=providerForm.getProviderDTO().getUploadLicense();
			//FormFile uploadedFile = poaDTO.getAwrdrSignature();
			if ("".equals(uploadedFile.getFileName())) 
			{
				errorList.add("Invalid file selection. Please try again.");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String signatureSize="("+fileSize+"MB)";
			
			if (rule.isError()) {
				   errorList.add("Invalid file type. Please select another file.");
				   request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
					request.setAttribute("errorsList", errorList);
				} else {
					providerDTO.setFileName(uploadedFile.getFileName());
				}
		} 
			forwardpage = "spSearch";   
		}catch (Exception e) {
			errorList.add("Unable to upload file. Please try again.");
			request.setAttribute("errorsList", errorList);
		}
		
	return forwardpage;
	}
	private String executeRemoveUploadedLicense(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		
	/*	providerDTO = providerForm.getProviderDTO();
		providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
		providerDTO.setSpDurationTo(providerForm.getDurationTo());
		providerDTO.setSpOfficeAddress(providerForm.getProviderDTO().getSpOfficeAddress());
		providerDTO.setSpOtherInformation(providerForm.getProviderDTO().getSpOtherInformation());
		providerDTO.setSplicencenumber(providerForm.getProviderDTO().getSplicencenumber());
		providerDTO3 = providerDTO;*/
		providerDTO.setFileName("");
		providerForm.setProviderDTO(providerDTO);
		forwardpage="spSearch";
		return forwardpage;
	}
	private String executeUploadRenewedLicense(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String forwardpage = null;
		ArrayList errorList=new ArrayList(); 
		try {
			FormFile uploadedFile=providerForm.getProviderDTO().getUploadLicense();
			//FormFile uploadedFile = poaDTO.getAwrdrSignature();
			if ("".equals(uploadedFile.getFileName())) 
			{
				errorList.add("Invalid file selection. Please try again.");
			}
			String fileExt = getFileExtension(uploadedFile.getFileName());
			AuditInspectionRule rule = new AuditInspectionRule();
			rule.validateFileType(fileExt);
			int size = uploadedFile.getFileSize();
			double fileSizeInBytes = size;
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024.0;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024.0;
			DecimalFormat decim = new DecimalFormat("#.##");
			Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
			String signatureSize="("+fileSize+"MB)";
			
			if (rule.isError()) {
				   errorList.add("Invalid file type. Please select another file.");
				   request.setAttribute("errorsList", errorList);
			} else {
				if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
					errorList.add("File size is Greater than 10 MB. Please select another file.");
					request.setAttribute("errorsList", errorList);
				} else {
					providerDTO.setFileName(uploadedFile.getFileName());
				}
		} 
			forwardpage = ServiceProviderConstants.RENEWLICENSE; 
		}catch (Exception e) {
			errorList.add("Unable to upload file. Please try again.");
			request.setAttribute("errorsList", errorList);
		}
		
	return forwardpage;
	}
	private String executeRemoveUploadedRenewedLicense(ActionMapping mapping,
			ServiceProviderForm providerForm, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		
	/*	providerDTO = providerForm.getProviderDTO();
		providerDTO.setSpDurationFrom(providerForm.getDurationFrom());
		providerDTO.setSpDurationTo(providerForm.getDurationTo());
		providerDTO.setSpOfficeAddress(providerForm.getProviderDTO().getSpOfficeAddress());
		providerDTO.setSpOtherInformation(providerForm.getProviderDTO().getSpOtherInformation());
		providerDTO.setSplicencenumber(providerForm.getProviderDTO().getSplicencenumber());
		providerDTO3 = providerDTO;*/
		providerDTO.setFileName("");
		providerForm.setProviderDTO(providerDTO);
		forwardpage = ServiceProviderConstants.RENEWLICENSE; 
		return forwardpage;
	}
}
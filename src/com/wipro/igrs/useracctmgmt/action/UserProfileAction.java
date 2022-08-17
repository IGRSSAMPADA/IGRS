/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileAction.java
 * Author		:	SATBIR KUMAR 
 * Date			:   5/09/2013
 */
package com.wipro.igrs.useracctmgmt.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

import java.util.Calendar;
import java.util.Date;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.useracctmgmt.bd.UserProfileBD;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.useracctmgmt.form.UserProfileForm;
import com.wipro.igrs.util.GUIDGenerator;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.mime.MIMECheck;
import com.wipro.igrs.newreginit.action.CommonAction;
import com.wipro.igrs.officearea.dto.TehsilDTO;
import com.wipro.igrs.useracctmgmt.constant.CommonConstant;;

public class UserProfileAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		System.out.println("****************************************in UserProfileAction executed");
		// HttpSession session = request.getSession();
		UserProfileForm userProfileForm = (UserProfileForm) form;
		System.out.println("****************************************Received UserProfileForm");
		String language = (String) session.getAttribute("languageLocale");
		UserProfileBD userProfileBD = new UserProfileBD();

		UserProfileDTO dto = userProfileForm.getUsrProDTO();
		System.out.println("****************************************usrProDTO userACtion");
		dto.setOnLoadChk("");
		String activityid = request.getParameter("ACTID");
		userProfileForm.setMainCheck("");
		dto.setCheckUrl("");
		ArrayList errorList = new ArrayList();
		String name = "";
		String chosenCountry = "";
		String chosenState = "";
		String type = "";
		String forward = "success";
		String frdName = request.getParameter("fwdName");
		String userId = (String) session.getAttribute("UserId");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd_HH-mm-ss");
		Date date = new Date();
		String datetime=dateFormat.format(date);
		System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
		IGRSCommon save = new IGRSCommon();
		PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

		System.out.println("fwdName name in user Action:  " + frdName);
		System.out.println("ACtion Name is = " + userProfileForm.getActionName());
		System.out.println("****************************************in BD DTO executed USer Profile ACtion");
		 

		if (frdName != null && frdName.equalsIgnoreCase("create")) {
			System.out.println("****************************************in if condtion: create");
			dto.setType("");
			save.saveactivitylog(userId, activityid);

			if (session.getAttribute("UserId") != null) {
				System.out.println("****************************************in if condtion: UserId");
				name = session.getAttribute("UserId").toString();
				type = userProfileBD.getUsertype(userId);
				ArrayList photoproofList = userProfileBD.getPhotoproofList(language);
				ArrayList hintList = userProfileBD.getHintList(language);

				if ("I".equalsIgnoreCase(type)) {
					// dto = userProfileForm.getUsrProDTO();
					dto = userProfileBD.getUserdetails(name, language, userProfileForm, type);
					// userProfileForm.setUsrProDTO(userProfileBD.getUserdetails(name,language,userProfileForm));

					// userProfileForm.setUserDetail((userProfileBD.getUserdetails(name,language,userProfileForm)));

					ArrayList countrylist = new ArrayList();
					countrylist = userProfileBD.getCountry(language);
					dto.setUserCountryList(countrylist);
					userProfileForm.setUserCountryList(countrylist);

					chosenCountry = dto.getUserCountryID();
					dto.setUserCountryID(chosenCountry);
					String chosenCountryName = dto.getCountry();
					ArrayList statelist = new ArrayList();
					if (chosenCountry != null && chosenCountry != "") {
						statelist = userProfileBD.getState(chosenCountry, language);
						userProfileForm.setUserStateList(statelist);
					}

					chosenState = dto.getUserStateID();
					dto.setStateName(chosenState);
					dto.setUserStateID(chosenState);
					ArrayList districtlist = new ArrayList();

					if (chosenState != null && chosenState != "") {
						districtlist = userProfileBD.getDistrictCity(chosenState, language);
						userProfileForm.setUserCityList(districtlist);
					}

					dto.setPhotoproofList(photoproofList);

					dto.setHintList(hintList);
					userProfileForm.setUsrProDTO(dto);
					request.setAttribute("UserProfileForm", userProfileForm);
					dto.setType("I");
					System.out.println("path:" + dto.getSaveUrl());
					if (dto.getSaveUrl() != null && dto.getSaveUrl() != "") {
						dto.setCheckUrl("S");
						dto.setSignature("SIGNATURE.JPG");
						System.out.println("****************************************findForward : userprofile");
						return mapping.findForward("userprofile");
					}

					String str = GUIDGenerator.getGUID();
					dto.setUniqueId(str);
					System.out.println("satbir " + dto.getUniqueId());
					dto.setSignature("SIGNATURE.JPG");
					System.out.println("satbir " + dto.getSignature());
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");

					String downloadPath = proper.getValue("igrs_upload_path");
					dto.setSignPath(downloadPath + File.separator + "UPLOAD/25/11/");
					System.out.println("satbir " + dto.getSignPath());
					dto.setForwardPath("/userprofile.do?fwdName=signature");
					System.out.println("satbir " + dto.getForwardPath());
					dto.setForwardName("userprofile");
					System.out.println("satbir " + dto.getForwardName());
					frdName = "";
					return mapping.findForward("userprofile");

				} else {
					System.out.println("****************************************in ELSE condtion: UserId");

					dto = userProfileBD.getUserdetails(name, language, userProfileForm, type);
					ArrayList countrylist = new ArrayList();
					countrylist = userProfileBD.getCountry(language);
					dto.setUserCountryList(countrylist);
					userProfileForm.setUserCountryList(countrylist);

					chosenCountry = dto.getUserCountryID();
					dto.setUserCountryID(chosenCountry);
					String chosenCountryName = dto.getCountry();
					ArrayList statelist = new ArrayList();
					if (chosenCountry != null && chosenCountry != "") {
						statelist = userProfileBD.getState(chosenCountry, language);
						userProfileForm.setUserStateList(statelist);
					}

					chosenState = dto.getUserStateID();
					dto.setStateName(chosenState);
					dto.setUserStateID(chosenState);
					ArrayList districtlist = new ArrayList();

					if (chosenState != null && chosenState != "") {
						districtlist = userProfileBD.getDistrictCity(chosenState, language);
						userProfileForm.setUserCityList(districtlist);
					}

					dto.setPhotoproofList(photoproofList);
					dto.setHintList(hintList);
					// to Hide hint answer in display user details ..
					dto.setHintAnswer("");
					userProfileForm.setUsrProDTO(dto);
					request.setAttribute("UserProfileForm", userProfileForm);
					System.out.println("path:" + dto.getSaveUrl());
					dto.setType("E");
					if (dto.getSaveUrl() != null && dto.getSaveUrl() != "") {
						dto.setCheckUrl("S");
						dto.setSignature("SIGNATURE.JPG");
						return mapping.findForward("userprofile");
					}

					frdName = "";
					String str = GUIDGenerator.getGUID();
					dto.setUniqueId(str);
					System.out.println("satbir " + dto.getUniqueId());
					dto.setSignature("SIGNATURE.JPG");
					System.out.println("satbir " + dto.getSignature());
					PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");

					String downloadPath = proper.getValue("igrs_upload_path");
					dto.setSignPath(downloadPath + File.separator + "UPLOAD/25/11/");
					System.out.println("satbir " + dto.getSignPath());
					dto.setForwardPath("/userprofile.do?fwdName=signature");
					System.out.println("satbir " + dto.getForwardPath());
					dto.setForwardName("/userprofile");
					System.out.println("satbir " + dto.getForwardName());
					System.out.println("****************************************in findForward: userprofile");
					return mapping.findForward("userprofile");
				}

			} else {
				dto = userProfileBD.getUserdetails(name, language, userProfileForm, type);

				return mapping.findForward("userprofile");
			}

		}

		if (frdName != null && frdName.equalsIgnoreCase("assignright")) {
			System.out.println("****************************************in assignright if condition");
			save.saveactivitylog(userId, activityid);
			userProfileForm.getUsrProDTO().setOption("");
			userProfileForm.getUsrProDTO().setPouserid("");
			userProfileForm.getUsrProDTO().setSpuserid("");
			userProfileForm.getUsrProDTO().setLicenseno("");
			userProfileForm.getUsrProDTO().setSearch("");
			userProfileForm.setActionName("");
			userProfileForm.getUsrProDTO().setHidefield("");
			frdName = "";
			return mapping.findForward("assignrights");
		}

		if (frdName != null && frdName.equalsIgnoreCase("tehsildarrights")) {
			System.out.println("****************************************in tehsildarrights if condition");
			save.saveactivitylog(userId, activityid);
			userProfileForm.getUsrProDTO().setOption("");
			userProfileForm.getUsrProDTO().setPouserid("");
			userProfileForm.getUsrProDTO().setSpuserid("");
			userProfileForm.getUsrProDTO().setLicenseno("");
			userProfileForm.getUsrProDTO().setSearch("");
			userProfileForm.setActionName("");
			userProfileForm.getUsrProDTO().setHidefield("");
			frdName = "";
			return mapping.findForward("tehsildarrights");
		}

		/*
		 * if("SEARCH".equalsIgnoreCase(userProfileForm.getActionName())) {
		 * 
		 * userProfileForm.getUsrProDTO().setOption("");
		 * 
		 * }
		 */

		/*
		 * if("SP".equalsIgnoreCase(userProfileForm.getUsrProDTO().getOption()))
		 * { userProfileForm.getUsrProDTO().setHidefield("1");
		 * userProfileForm.getUsrProDTO().setPouserid("");
		 * userProfileForm.getUsrProDTO().setSearch("SPS"); return
		 * mapping.findForward("assignrights");
		 * 
		 * }
		 * 
		 * if("PO".equalsIgnoreCase(userProfileForm.getUsrProDTO().getOption()))
		 * { userProfileForm.getUsrProDTO().setHidefield("0");
		 * userProfileForm.getUsrProDTO().setSpuserid("");
		 * userProfileForm.getUsrProDTO().setLicenseno("");
		 * userProfileForm.getUsrProDTO().setSearch("POS"); return
		 * mapping.findForward("assignrights"); }
		 */
		if (frdName != null && frdName.equalsIgnoreCase("SUBMIT_ACTION")) {
			System.out.println("**********in SUBMIT_ACTION");

			String str = GUIDGenerator.getGUID();
			dto.setUniqueId(str);
			System.out.println("satbir " + dto.getUniqueId());
			dto.setSignature("SIGNATURE.JPG");
			System.out.println("satbir " + dto.getSignature());
			PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");

			String downloadPath = proper.getValue("igrs_upload_path");
			dto.setSignPath(downloadPath + File.separator + "UPLOAD/25/11/");
			System.out.println("satbir " + dto.getSignPath());
			dto.setForwardPath("/userprofile.do?fwdName=signature");
			System.out.println("satbir " + dto.getForwardPath());
			dto.setForwardName("/userprofile");
			System.out.println("satbir " + dto.getForwardName());
			dto.setOnLoadChk("CAPTURE");
			userProfileForm.setActionName("");
			return mapping.findForward("userprofile");

		}
		if ("UPDATERIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("**********in UPDATERIGHTS");
			String durationfrom = userProfileForm.getUsrProDTO().getPorightsfromdate();
			String durationto = userProfileForm.getUsrProDTO().getPorightstodate();

			String pouserid = userProfileForm.getUsrProDTO().getPouserid();
			String loggedinuserid = session.getAttribute("UserId").toString();

			boolean success1 = userProfileBD.updaterightsduration(durationfrom, durationto, pouserid, loggedinuserid);

			if (success1 == true) {
				System.out.println("updated duration");
				return mapping.findForward("updatesuccess");
			} else {
				System.out.println("duration updation failed");
			}

		}

		if ("UPDATETEHSILDARRIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("**********in UPDATERIGHTS");
			String durationfrom = userProfileForm.getUsrProDTO().getRightsFromDate();
			String durationto = userProfileForm.getUsrProDTO().getRightsToDate();
			System.out.println("durationfrom = " + durationfrom);
			System.out.println("durationto = " + durationto);

			String tehsilId = userProfileForm.getTehsilID();
			String pouserid = userProfileForm.getUsrProDTO().getPouserid();
			String loggedinuserid = session.getAttribute("UserId").toString();

			String savepath="";
			//String fileName=userProfileForm.getCertificate().getFileName();
			String fileName=userProfileForm.getDocumentName();
			System.out.println("fileName = "+fileName);
			
			CommonAction action=new CommonAction();
			
			
			if(fileName!="")
			{
			
				//savepath=	action.uploadFile(pouserid,userProfileForm.getCertificate().getFileData() ,datetime, "02", fileName);
				savepath = userProfileForm.getFilePath();
			}
			
			System.out.println("savepath is =  "+savepath);
			
			boolean success1 = userProfileBD.updateTehsildarrightsduration(durationfrom, durationto, pouserid,
					loggedinuserid, tehsilId,savepath);

			if (success1 == true) {
				System.out.println("updated duration");
				return mapping.findForward("updatesuccess");
			} else {
				System.out.println("duration updation failed");
			}

		}

		if ("POASSIGNRIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("**********in POASSIGNRIGHTS");
			String durationfrom = userProfileForm.getUsrProDTO().getPorightsfromdate();
			String durationto = userProfileForm.getUsrProDTO().getPorightstodate();
			String pouserid = userProfileForm.getUsrProDTO().getPouserid();
			String loggedinuserid = session.getAttribute("UserId").toString();
			boolean rights = userProfileBD.assignporights(pouserid);

			System.out.println(userProfileForm.getUsrProDTO().getPorightsfromdate());

			if (rights == true) {

				boolean success = userProfileBD.insertrightsduration(durationfrom, durationto, pouserid,
						loggedinuserid);

				if (success == true) {
					System.out.println("inserted duration");
					return mapping.findForward("rightsassigned");
				} else {
					System.out.println("duration insertion failed");
				}

			}

			else {
				System.out.println("failed");
			}

		}

		// Added By Praveen For Tehsildar Flow

		if ("RUASSIGNRIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("**********in RUASSIGNRIGHTS");
			String durationfrom = userProfileForm.getUsrProDTO().getRightsFromDate();
			String durationto = userProfileForm.getUsrProDTO().getRightsToDate();
			String userid = userProfileForm.getUsrProDTO().getPouserid();
			String loggedinuserid = session.getAttribute("UserId").toString();
			
			
			
	String savepath="";
	//String fileName=userProfileForm.getCertificate().getFileName();
	String fileName=userProfileForm.getDocumentName();
	System.out.println("fileName = "+fileName);
	
	CommonAction action=new CommonAction();
	
	
	if(fileName!="")
	{
	
		//savepath=action.uploadFile(userid,userProfileForm.getCertificate().getFileData() ,datetime, "02", fileName);
		 savepath = userProfileForm.getFilePath();
	}
		//	System.out.println("userProfileForm.getCertificate().getFileName() = "+userProfileForm.getCertificate().getFileName());
			
//			if(userProfileForm.getCertificate().getFileName()!="")
//			{
//				savepath=userProfileBD.saveFile(userProfileForm.getCertificate(), userid);
//				f
//				if(savepath=="") {
//					
//					return mapping.findForward("failure");
//				}
//				System.out.println("savepath = "+savepath);
//			}
			
			boolean rights = userProfileBD.assignTehsildarRights(userid);
			String tehsilId = userProfileForm.getTehsilID();
			
			
			System.out.println(
					"*********fromDate in RUAssignRiths = " + userProfileForm.getUsrProDTO().getRightsFromDate());

			if (rights == true) {

				boolean success = userProfileBD.insertrightsduration(durationfrom, durationto, userid, loggedinuserid,
						tehsilId, savepath,fileName);

				if (success == true) {
					System.out.println("inserted duration");
					return mapping.findForward("rightsassigned");
				} else {
					System.out.println("duration insertion failed");
				}

			}

			else {
				System.out.println("failed");
			}

		}

		if ("SEARCH".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("*********************** IN Search ");

			dto = userProfileForm.getUsrProDTO();
			String pouserid = userProfileForm.getUsrProDTO().getPouserid();
			ArrayList podetails = new ArrayList();
			podetails = userProfileBD.getpodetails(pouserid, dto, language);

			if (podetails.size() > 0) {
				for (int i = 0; i < podetails.size(); i++) {
					ArrayList lst = (ArrayList) podetails.get(i);

					userProfileForm.getUsrProDTO().setPofirstname((String) lst.get(0));
					userProfileForm.getUsrProDTO().setPolastname((String) lst.get(1));
					userProfileForm.getUsrProDTO().setPodob((String) lst.get(2));
					userProfileForm.getUsrProDTO().setPoDistrict((String) lst.get(3));
					userProfileForm.getUsrProDTO().setPoAddress((String) lst.get(4));
					userProfileForm.getUsrProDTO().setPophoneno((String) lst.get(5));
					userProfileForm.getUsrProDTO().setPoMobilenumber((String) lst.get(6));
					userProfileForm.getUsrProDTO().setPoemailid((String) lst.get(7));
					userProfileForm.getUsrProDTO().setPoOccupation((String) lst.get(8));

				}
				System.out.println("*********************** returning porights ");
				return mapping.findForward("porights");
			} else {
				userProfileForm.setActionName(null);
				return mapping.findForward("norecordsfound");

			}

		}
		// Added By Praveen to Search Registered USER
		if ("SEARCHRG".equalsIgnoreCase(request.getParameter("actionName"))) {

			System.out.println("******************* IN Search RG");

			dto = userProfileForm.getUsrProDTO();
			String pouserid = userProfileForm.getUsrProDTO().getPouserid();
			String userIDType = userProfileBD.getUsertype(pouserid);
			System.out.println("******************* userIDType=" + userIDType);
			// to fetch logged user district 
			String loggedinuserOffice = session.getAttribute("loggedToOffice").toString();
			System.out.println("loggedd office "+loggedinuserOffice );
			String loggedinUserDistrict = userProfileBD.getUserDistrict(loggedinuserOffice,language);
			
			if (userIDType.equals("2") || userIDType.equals("9") ){

				ArrayList podetails = new ArrayList();
				ArrayList tehsilDetails = new ArrayList();
				tehsilDetails = userProfileBD.getTehsilList(pouserid, language);
				dto.setTehsilDetails(tehsilDetails);
				userProfileForm.setTehsilDetails(tehsilDetails);
				System.out.println("******************* Tehsil list received");
				System.out.println("******************* Tehsil list is added new code" + tehsilDetails);

				// if(tehsilDetails.size()!=0){
				// ArrayList tehsilNames=new ArrayList();
				//
				// for (int i=0; i<=tehsilDetails.size(); i++)
				// {
				// TehsilDTO tehsiobj=(TehsilDTO) tehsilDetails.get(i);
				// String tehsilname=(String)tehsiobj.getName();
				// tehsilNames.add(tehsilname);
				// System.out.println("******************* Tehsil name added to
				// list is" +tehsilname);
				// }
				// userProfileForm.setTehsilNameList(tehsilNames);
				//
				// }
				//

				podetails = userProfileBD.getrudetails(pouserid, dto, language);
				// Added for tehsil ,filepath,file name
				String TehsilID = dto.getTehsilId();
				userProfileForm.setTehsilID(TehsilID);
				userProfileForm.setFilePath(dto.getFilePath());
				//dto.setUploadDocument("");
				userProfileForm.setDocumentName(dto.getDocumentName());
				
				System.out.println("******************* User profile fetched. . . .");
				
				if (podetails.size() > 0) {
					for (int i = 0; i < podetails.size(); i++) {
						ArrayList lst = (ArrayList) podetails.get(i);
						System.out.println("size of array list form DB is = " + podetails.size());
						System.out.println("First Name is = " + (String) lst.get(0));
						System.out.println("list is " + lst);

						userProfileForm.getUsrProDTO().setFirstName((String) lst.get(0));
						userProfileForm.getUsrProDTO().setMiddleName((String) lst.get(1));
						userProfileForm.getUsrProDTO().setLastName((String) lst.get(2));
						userProfileForm.getUsrProDTO().setGender((String) lst.get(3));
						userProfileForm.getUsrProDTO().setAge((String) lst.get(4));
						userProfileForm.getUsrProDTO().setOccupation((String) lst.get(5));
						userProfileForm.getUsrProDTO().setFatherName((String) lst.get(6));
						userProfileForm.getUsrProDTO().setMotherName((String) lst.get(7));
						userProfileForm.getUsrProDTO().setSpouseName((String) lst.get(8));
						userProfileForm.getUsrProDTO().setCategory((String) lst.get(9));
						userProfileForm.getUsrProDTO().setMinority((String) lst.get(10));
						userProfileForm.getUsrProDTO().setAddress((String) lst.get(11));
						userProfileForm.getUsrProDTO().setCountry((String) lst.get(12));
						userProfileForm.getUsrProDTO().setState((String) lst.get(13));
						userProfileForm.getUsrProDTO().setDistrict((String) lst.get(14));
						userProfileForm.getUsrProDTO().setPostalCode((String) lst.get(15));
						userProfileForm.getUsrProDTO().setPhoneNumber((String) lst.get(16));
						userProfileForm.getUsrProDTO().setMobileNumber((String) lst.get(17));
						userProfileForm.getUsrProDTO().setPrimaryEmail((String) lst.get(18));
						userProfileForm.getUsrProDTO().setAlternateEmail((String) lst.get(19));

					}
					// setting Gender -Rahul
					 String Gender = userProfileForm.getUsrProDTO().getGender();
					   if (Gender.equalsIgnoreCase("M"))
					   {
						   if(language.equalsIgnoreCase("en"))
						   {
						   Gender ="Male";
						   }
						   else 
						   {
							   Gender ="पुरूष";
						   }
					   }
					   if (Gender.equalsIgnoreCase("F"))
					   {
						   if(language.equalsIgnoreCase("en"))
						   {
						   Gender ="Female";
						   }
						   else 
						   {
							   Gender ="महिला";
						   }
					   }
					   userProfileForm.getUsrProDTO().setGender(Gender);
					  String RuDistrict = userProfileForm.getUsrProDTO().getDistrict();
					  boolean chkDistrict = false;
					   if (loggedinUserDistrict.equalsIgnoreCase(RuDistrict))
					   {
						   chkDistrict = true ;
					   }
					 
					   if(chkDistrict){
                          if(dto.getPoupdate()=="0")
                          {
                        	  return mapping.findForward("updatetehsilrights"); 
                          }
                          else {
                        	  userProfileForm.setTehsilID("");
                        	  userProfileForm.setDocumentName("");
                        	  return mapping.findForward("rgrights");
                          }
					   }
					   else {
						   userProfileForm.setActionName(null);
							return mapping.findForward("norecordsfound");
					      }
				}

				else {
					userProfileForm.setActionName(null);
					return mapping.findForward("norecordsfound");

				}

			}

			else {
				userProfileForm.setActionName(null);
				return mapping.findForward("norecordsfound");

			}

		}
		// For upload file Praveen 
		if ("UPLOADFILE".equalsIgnoreCase(userProfileForm.getActionName()))
		{
			System.out.println("In upload file of tehsildar");
			try
			{
				FormFile uploadedFile = userProfileForm.getCertificate();
				byte contents[] = uploadedFile.getFileData();
				if ("".equals(uploadedFile.getFileName()))
				{
				ActionMessages message = new ActionMessages();
                message.add("message", new ActionMessage("tehsildar.invalid.fileName"));
                saveMessages(request,message);
                
                dto.setRightsFromDate("NA");
    			dto.setRightsToDate("NA");
                return mapping.findForward("rgrights");
				}
				  MIMECheck mimeCheck = new MIMECheck();
                  String fileExt=mimeCheck.getFileExtension(uploadedFile.getFileName());
                  UserProfileAction usAction = new UserProfileAction();
                  Boolean mime = usAction.validateMIMEAndPDFFileType(uploadedFile); 
                  int size = uploadedFile.getFileSize();
                  double fileSizeInBytes = size;
					// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
					double fileSizeInKB = fileSizeInBytes / 1024.0;
					// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
					double fileSizeInMB = fileSizeInKB / 1024.0;
					DecimalFormat decim = new DecimalFormat("#.##");
					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
					String photoSize="("+fileSize+"MB)";
                  if (!mime) 
                  {
                	  ActionMessages message = new ActionMessages();
                      message.add("message", new ActionMessage("tehsildar.invalid.fileType"));
                      saveMessages(request,message);
                   }
                  else 
                  {
                	  if (size > CommonConstant.MAX_FILE_SIZE) {
                          ActionMessages message = new ActionMessages();
                          message.add("message", new ActionMessage("tehsildar.invalid.fileSize"));
                          saveMessages(request,message);	  
                     }
                	  else {
                		  String uploadPath = pr.getValue("igrs_upload_path");
                		  String fileName = uploadedFile.getFileName();
                		  userProfileForm.setFilePath(uploadPath+fileName);
                		  
                		 userProfileForm.setDocumentName(fileName);
                		 userProfileForm.setDocContents(uploadedFile.getFileData());
                		 userProfileForm.setDocumentSize(photoSize);
                		 File file2 = new File(uploadPath+fileName);
                         OutputStream os  = null;
                         try {
                               os = new FileOutputStream(file2);
                            for(int x = 0; x < contents.length ; x++) {
                               os.write( contents[x] ); 
                            }
                         } catch (IOException e) {
                               
                         }finally{
                               if (os != null) {
                               try {
                                     os.close();
                                     } catch (IOException e) {
                                           e.printStackTrace();
                                     }
                            }
                         }
                         
                		  }
                	  
                	  }
                  dto.setRightsFromDate("NA");
      			dto.setRightsToDate("NA");
                  return mapping.findForward("rgrights");
			}
			catch(Exception e)
			{
				ActionMessages message = new ActionMessages();
                message.add("message", new ActionMessage("tehsildar.invalid.fileUploadErr"));
                saveMessages(request,message);
			}
			
		}
           // To download document 
		if ("downloadMainDoc".equalsIgnoreCase(userProfileForm.getActionName())) {
            try {
                  byte[] content = userProfileForm.getDocContents();
                  String filename = userProfileForm.getDocumentName();
              
                	  
                  if(content != null) {
                	  try {
                		
              			OutputStream os = response.getOutputStream();
              			response.setContentType("application/download");
              			response.setHeader("Content-Disposition", "attachment; filename="
              					+ URLEncoder.encode(filename,"UTF-8"));
              			os.write(content);
              			os.flush();
              			os.close();
              		} catch (Exception e) {
              		}
                  }
                  return mapping.findForward("rgrights");
            } catch (Exception e) {
            }                 
      }
		// Download update rights document tehsildar
		if ("downloadUpdatedMainDoc".equalsIgnoreCase(userProfileForm.getActionName())) {

            try
            {
            response.setContentType("application/download");
               String filePath=userProfileForm.getFilePath();
                String fileName=userProfileForm.getDocumentName();
               File fileToDownload = new File(filePath);
               response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(userProfileForm.getDocumentName(),"UTF-8"));
             FileInputStream fileInputStream = new FileInputStream(fileToDownload);

        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[2048];

        int readNum;

        while ((readNum=fileInputStream.read(buf))!=-1)
        {
           out.write(buf,0,readNum);
        }
        fileInputStream.close();
        if(null!=out){
            out.close();
        }
        return mapping.findForward("refId");
    
    }
            		catch (Exception e) {
            					// logger.error(e);
            			e.getMessage();
            		}
		}
		// end 
		if (frdName != null && frdName.equalsIgnoreCase("signature")) {
			System.out.println("****************************************in signature");
			userProfileForm.setMainCheck("Yes");
			return mapping.findForward("userprofile");

		}

		if (frdName != null && frdName.equalsIgnoreCase("signdownload")) {
			System.out.println("****************************************in signdownload");
			try {
				String filename = request.getParameter("path").toString();

				response.setContentType("application/download");
				String file = filename.substring(filename.lastIndexOf("/") + 1, filename.length());
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(file, "UTF-8"));

				File fileToDownload = new File(filename);

				BufferedImage bi = ImageIO.read(fileToDownload);
				OutputStream out = response.getOutputStream();
				ImageIO.write(bi, "jpg", out);
				out.close();

				return mapping.findForward("userprofile");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (frdName != null && frdName.equalsIgnoreCase("changepassword")) {
			save.saveactivitylog(userId, activityid);
			System.out.println("your action name is" + userProfileForm.getActionName());

			userProfileForm.getUsrProDTO().setOldpassword("");
			userProfileForm.getUsrProDTO().setNewpassword("");
			userProfileForm.getUsrProDTO().setConfirmpassword("");
			userProfileForm.getUsrProDTO().setNotmatch("0");

			return mapping.findForward("changepasswd");
		}

		if ("POREVOKERIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			String userid = userProfileForm.getUsrProDTO().getPouserid();
			boolean flag = userProfileBD.revokerights(userid);

			if (flag == false) {
				return mapping.findForward("revokesuccess");
			} else {
				System.out.println("revoking rights failed");
			}

		}

		if ("TEHSILDARREVOKERIGHTS".equalsIgnoreCase(userProfileForm.getActionName())) {
			String userid = userProfileForm.getUsrProDTO().getPouserid();
			boolean flag = userProfileBD.revokTehsildarRights(userid);

			if (flag == false) {
				return mapping.findForward("revokesuccess");
			} else {
				System.out.println("revoking rights failed");
			}

		}

		if ("SUBMIT_ACTION".equalsIgnoreCase(userProfileForm.getActionName())) {
			System.out.println("**********in SUBMIT_ACTION");
			String name1 = session.getAttribute("UserId").toString();

			String oldencryptpswd = userProfileForm.getUsrProDTO().getOldpassword();

			System.out.println("old salted password u entered" + oldencryptpswd);
			CryptoLibrary crypt = new CryptoLibrary();
			/*
			 * String oldencryptpswd =
			 * crypt.SHAencryption(userProfileForm.getUsrProDTO().getOldpassword
			 * ());
			 */ // added by satbir kumar

			String salt1 = session.getAttribute("saltkey1").toString();
			String salt2 = session.getAttribute("saltkey2").toString();
			session.setAttribute("saltkey1", "");
			session.setAttribute("saltkey2", "");

			String userDBpassword = userProfileBD.userDBpassword(name1);
			userDBpassword = salt1 + userDBpassword + salt2;
			userDBpassword = crypt.SHAencryption(userDBpassword);

			System.out.println("USERDATABASEPASSWORD" + userDBpassword);

			if (userDBpassword.equalsIgnoreCase(oldencryptpswd) == false) {
				userProfileForm.getUsrProDTO().setNotmatch("1");

				userProfileForm.getUsrProDTO().setOldpassword("");
				userProfileForm.getUsrProDTO().setNewpassword("");
				userProfileForm.getUsrProDTO().setConfirmpassword("");

				return mapping.findForward("changepasswd");

			}

			else if (userDBpassword.equalsIgnoreCase(oldencryptpswd) == true) {

				String newpassword = userProfileForm.getUsrProDTO().getNewpassword();
				// crypt.SHAencryption(userProfileForm.getUsrProDTO().getNewpassword());

				System.out.println("new passwrd entered:-" + newpassword);

				boolean passwordchangesuccess = userProfileBD.changepassword(newpassword, name1);

				if (passwordchangesuccess == true) {
					System.out.println("password change successfull!!");

					return mapping.findForward("passwrdchangesuccess");
				}

				else {
					System.out.println("password change unsuccessfull!!");
				}

			}
		}

		if (frdName != null && frdName.equalsIgnoreCase("State")) {
			System.out.println("****************************************in State");
			chosenCountry = request.getParameter("Country");
			dto.setUserCountryID(chosenCountry);
			String chosenCountryName = dto.getCountry();
			ArrayList statelist = new ArrayList();
			if (chosenCountry != null && chosenCountry != "") {
				statelist = userProfileBD.getState(chosenCountry, language);
				userProfileForm.setUserStateList(statelist);
			}
			return mapping.findForward("userprofile");
		}

		// aclcreateForm.setUserStateList(statelist);

		else if (frdName != null && frdName.equalsIgnoreCase("District")) {
			System.out.println("****************************************in District");

			chosenState = request.getParameter("State");
			dto.setStateName(chosenState);
			dto.setUserStateID(chosenState);
			ArrayList districtlist = new ArrayList();

			if (chosenState != null && chosenState != "") {
				districtlist = userProfileBD.getDistrictCity(chosenState, language);
				userProfileForm.setUserCityList(districtlist);
			}
			return mapping.findForward("userprofile");
		}
		//

		if (frdName != null && frdName.equalsIgnoreCase("update")) {
			System.out.println("***************update");
			userProfileBD.updateuserdetails(userProfileForm, userId);

			userProfileForm.setMainCheck("");
			dto.setUniqueId("");
			dto.setSignature("");
			dto.setSignPath("");
			dto.setForwardPath("");
			dto.setForwardName("");

			return mapping.findForward(forward);
		}

		return mapping.findForward(forward);
	}

	/*
	 * private String getFileExtension(String fileName) { try { int pos =
	 * fileName.lastIndexOf("."); String strExt = fileName.substring(pos + 1,
	 * fileName.length()); return strExt; } catch (Exception e) { } return ""; }
	 */

	 // To validate only jpg file format.
	public boolean validateMIMEAndPDFFileType(FormFile uploadedFile){
			
			if (isValidMIME(uploadedFile) && validateFileTypePDF(getFileExtension(uploadedFile.getFileName()))){
				return true;
			}
			
			return false;
		}
	public boolean isValidMIME(FormFile uploadedFile) {

		ArrayList errors = new ArrayList();
		
			String fileName = uploadedFile.getFileName();
			String temp = fileName.replaceFirst("\\.", "");
			if (temp.contains(".") || uploadedFile.getFileSize() <= 0) {
				return false;
			}

			String mimeType = uploadedFile.getContentType();
			mimeType = mimeType.toLowerCase();
			String fileextension = getFileExtension(fileName);
			fileextension = fileextension.toLowerCase();

		//	String[] arrFileExt = { "tif", "jpg", "tiff", "gif", "jpeg" };

			if (mimeType != null && mimeType.contains("image")) {
				if (mimeType.contains("jpeg")) {
					if (fileextension.equalsIgnoreCase("jpg")
							|| fileextension.equalsIgnoreCase("jpeg")) {
						return true;
					}
				} else if (mimeType.contains("gif")) {
					if (fileextension.equalsIgnoreCase("gif")) {
						return true;
					}
				} else if (mimeType.contains("tiff")) {
					if (fileextension.equalsIgnoreCase("tiff")
							|| fileextension.equalsIgnoreCase("tif")) {
						return true;
					}
				}

			} else if (mimeType != null && mimeType.contains("application")) {
				if (mimeType.contains("pdf")) {
					if (fileextension.equalsIgnoreCase("pdf")) {
						return true;
					}
				} else if (mimeType.contains("msword")) {
					if (fileextension.equalsIgnoreCase("doc")
							|| fileextension.equalsIgnoreCase("docx")) {
						return true;
					}
				} else if (mimeType.contains("zip")) {
					if (fileextension.equalsIgnoreCase("zip")) {
						return true;
					}
				} else if (mimeType.contains("rtf")) {
					if (fileextension.equalsIgnoreCase("rtf")) {
						return true;
					}
				} else if (mimeType.contains("ms-excel")) {
					if (fileextension.equalsIgnoreCase(".xls")
							|| fileextension.equalsIgnoreCase(".xlsx")) {
						return true;
					}
				}
			}

			return false;

		}
	public boolean validateFileTypePDF(String fileExt) {
		String ValidFileExt = "pdf";
		try {
			if (fileExt.equalsIgnoreCase(ValidFileExt)) {
					return true;
				}
			}
			 catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			if (strExt != null && !" ".equalsIgnoreCase(strExt)) {
				strExt = strExt.toLowerCase();
			}
			return strExt;
		} catch (Exception e) {
		}
		return "";
	}

	
}

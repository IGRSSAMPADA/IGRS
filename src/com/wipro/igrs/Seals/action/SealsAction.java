package com.wipro.igrs.Seals.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import com.wipro.igrs.Seals.bd.SealsBD;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.Seals.dto.SealsDTO;
import com.wipro.igrs.Seals.form.SealsForm;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.login.dto.MasterListDTO;
import com.wipro.igrs.regCompChecker.constant.RegCompCheckerConstant;


public class SealsAction extends Action{

	String forwardJsp = "";
	private Logger logger = Logger.getLogger(SealsAction.class);
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response ) throws Exception {
		
			String page=(String)request.getAttribute("page");
			//String page=(String)request.getParameter("page");
			logger.debug("page in Seal Action"+page);
			 HttpSession session = request.getSession();
			String roleId = (String)session.getAttribute("role");
			String funId = (String)session.getAttribute("functionId");
			String userId = (String)session.getAttribute("UserId");
			String officeId = (String)session.getAttribute("loggedToOffice");
			String language = (String)session.getAttribute("languageLocale");
			MasterListDTO mDto=(MasterListDTO) session.getAttribute("userDetls");
			
			logger.debug("office    "+officeId);
			
			if(form != null)
			{
				try{
				SealsForm eForm = (SealsForm)form;
				//String headerimg=getServlet().getServletContext().getRealPath("")+ "/images/header img.jpg";
				
				SealsBD bd = new SealsBD();
				SealsDTO sealDTO = eForm.getSealDTO();
				if(eForm.getLangauge()==null||eForm.getLangauge().equalsIgnoreCase(""))
				{
					String regNumber = (String)request.getAttribute("regNumber");
					String lang=bd.getLangauge(regNumber);
					
					
						eForm.setLangauge(lang);
						
						if("English".equalsIgnoreCase(lang))
						{
							language="en";
						}
						else
						{
							language="hi";
						}
					
				}
				if(mDto!=null)
				{
					sealDTO.setUserName(mDto.getUserName());
					sealDTO.setUserDesignation(mDto.getDesignation());
					sealDTO.setUserSignature(mDto.getSignature());
					// to be commented
					//sealDTO.setUserSignature("D://signature.GIF");
					sealDTO.setUserOfficeName(mDto.getOfficeName());
					logger.debug("office Name:"+mDto.getOfficeName());
				}
				request.setAttribute("Seals", eForm);
				
				if(page != null)
				{
					if("sealForm1".equalsIgnoreCase(page))
					{
						String sealId  = (String)request.getAttribute("sealId");
						if(sealId.equals("1"))
						{
							
						}
						else
						{
							sealDTO = new SealsDTO();
						}
						//sealDTO = new SealsDTO();
						sealDTO.setErrorFlag("N");
						sealDTO.setAllowPrint("N");
						eForm.setRadioChk("N");
						eForm.setConsenterList(new ArrayList());
						eForm.setPresentationPartyDetails(new ArrayList());
						eForm.setWitnessList(new ArrayList());
						eForm.setSealDTO(new SealsDTO());
						//TODO : - get sealId From Parent module
						//String sealId = "9";  // ExecutionSeal
						//String regNumber = "40414000001"; // testing
						String regNumber = (String)request.getAttribute("regNumber");
						String regCompNumber = (String)request.getAttribute("regCompNum");
						//String sealId  = (String)request.getAttribute("sealId");
						logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^reg number"+regNumber);
						logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^Complition number"+regCompNumber);
						logger.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^sealId"+sealId);
						sealDTO.setRegCompNumber(regCompNumber);
						sealDTO.setRegNumber(regNumber);
						eForm.setHeaderImage((String)request.getAttribute("headerImagePath"));
						eForm.setBackPage((String)request.getAttribute("backPage"));
						ArrayList subSealList = bd.getSubSealIds(sealId,regNumber,language);
						logger.debug("Seal list:"+subSealList.size());
						if(sealId.equals("1"))
						{
							String partyPresent = (String)request.getAttribute("partyPresentArray");
							eForm.setPresentParty(partyPresent);
							sealDTO.setSelectedSubId("1");
							eForm.setSealDTO(sealDTO);
							String selectedParties[] = eForm.getPresentParty().split("_");
							logger.debug("size of array"+selectedParties.length);
							int len = selectedParties.length;
							String partyArr[] = new String[len];
							for(int i = 0; i<selectedParties.length;i++)
							{
								String tmpArr[] = selectedParties[i].split(",");
								partyArr[i] = tmpArr[0];
							}
							try{
								String selectedSeal="1";
								String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
								if("Y".equalsIgnoreCase(applyFlag))
								{
									int result =0;
									logger.debug("insertion----->"+result);
									logger.debug("insertion----->"+result);
									request.setAttribute("resultCode", result);
									forwardJsp =  "backToPrsentation";
								}
								else
								{
									
									int result = bd.insertPresentationSealDetails(eForm.getPresentationPartyDetails(),sealDTO, officeId, userId,partyArr,eForm,language);
									logger.debug("insertion----->"+result);
									logger.debug("insertion----->"+result);
									request.setAttribute("resultCode", result);
									forwardJsp =  "backToPrsentation";
								}
								}
								catch(Exception e)
								{
									logger.debug("Presentation failed ");
									throw new Exception();
								}
								
							return mapping.findForward(forwardJsp);
						}
						if(sealId.equals("9"))   // adjudication seal
						{
							String deedName = (String)request.getAttribute("DeedType");
							sealDTO.setDeedName(deedName);
						}
						eForm.setSubSealList(subSealList);
						eForm.setSealDTO(sealDTO);
						forwardJsp = SealsConstant.SEAL_FORM1_JSP;
						
					}
				}
				
				String actionName = sealDTO.getActionName();
				String formName = sealDTO.getFormName();
				logger.debug("ACTION NAME SEALS"+actionName);
				logger.debug("FORM NAME SEALS"+formName);
				logger.debug("Reg Number"+sealDTO.getRegNumber());
				if(RegCompCheckerConstant.GENERATE_OTP.equalsIgnoreCase(actionName))
				{
					OTPUtility otp = new OTPUtility();
					boolean flag = otp.generateOTP(userId, sealDTO.getRegNumber(), "CHECKER");
					
					if(flag==false)
					{
						sealDTO.setErrrorMessageOTP("The Server is Not available at the moment . Please Generate OTP later./ सर्वर  उपलब्ध नहीं है। OTP बाद में उत्पन्न करें।");
					}
					else
					{
						sealDTO.setErrrorMessageOTP("");
					}
					
					forwardJsp = SealsConstant.SEAL_FORM1_JSP;
					return mapping.findForward(forwardJsp);
					
				}
				else if(RegCompCheckerConstant.VALIDATE_OTP.equalsIgnoreCase(actionName))
				{
					logger.debug("In  Valdiate OTP in Checker Action ");
					OTPUtility otp = new OTPUtility();
					boolean flag = 	otp.validateOTP(sealDTO.getRegNumber(), "CHECKER", userId, sealDTO.getOtp());
					flag = true;
					if(flag)
					{
						sealDTO.setAllowPrint("Y");
						sealDTO.setErrorFlag("N");

						String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
						if("Y".equalsIgnoreCase(applyFlag))
						{
							int result=0;
							logger.debug("insertion----->"+result);
							request.setAttribute("resultCode", result);
						}
						else
						{	
						bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
						
						
						int result = bd.insertRegistrationSealDetails(eForm,userId,officeId,response);
						
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						
							boolean flag1=	bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
							if(flag1)
							{
								flag1=bd.updateCompTime(sealDTO.getRegNumber());
							}
							if(!flag1)
							{
								logger.debug("Execution Seal Failed----->"+result);
								throw new Exception();
							}
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						//done
						request.setAttribute("resultCode", result);
						}
						forwardJsp = "backToRemainingSeal";
					
						
					}
					else
					{
						sealDTO.setErrorFlag("Y");
						forwardJsp = SealsConstant.SEAL_FORM1_JSP;
					}
				
				return mapping.findForward(forwardJsp);
				}
				else if(SealsConstant.VIEW_SEAL_CONTENT.equalsIgnoreCase(actionName))
				{
					logger.debug("VIEW SEAL CONTENT");
					logger.debug("radio value" + sealDTO.getSelectedSubId());
					String selectedSeal = sealDTO.getSelectedSubId();
					if(selectedSeal.equals("1"))   // PRESENTATION SEAL
					{
						logger.debug("^^^^^^reg number^^^^^^^^^^^^^^^"+eForm.getSealDTO().getRegNumber());
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						//String selectedSeal = sealDTO.getSelectedSubId();
					
					}
					else if(selectedSeal.equals("2"))  // EXECUTION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						sealDTO.setConsAmt("0");
						sealDTO.setAmntSr("0");
						sealDTO.setAmntToBePaid("0");
					}
					else if(selectedSeal.equals("3"))
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						sealDTO.setConsAmt("0");
						sealDTO.setAmntSr("0");
						sealDTO.setAmntToBePaid("0");
					}
					else if(selectedSeal.equals("4"))  //EXECUTION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						sealDTO.setConsAmt("0");
						sealDTO.setAmntSr("0");
						sealDTO.setAmntToBePaid("0");
					}
					else if(selectedSeal.equals("5"))  //EXECUTION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						
						ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(),language);
						logger.debug("witness list"+witnessList.size());
						eForm.setWitnessList(witnessList);
					}
					else if(selectedSeal.equals("6"))  //EXECUTION SEAL
					{
						
					}
					else if(selectedSeal.equals("7"))  //EXECUTION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
					}
					else if(selectedSeal.equals("8"))  // WITNESS SEAL
					{
						ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(),language);
						logger.debug("witness list"+witnessList.size());
						eForm.setWitnessList(witnessList);
					}
					else if(selectedSeal.equals("10"))   // CONSENTER SEAL
					{
						ArrayList consenterList = bd.getConsenterDetails(sealDTO.getRegNumber(),language);
						logger.debug("consenterList: "+consenterList.size());
						eForm.setConsenterList(consenterList);
					}
					else if(selectedSeal.equals("9"))   // THUMB IMPRESSION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						
						ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(),language);
						logger.debug("witness list"+witnessList.size());
						eForm.setWitnessList(witnessList);
						
						ArrayList consenterList = bd.getConsenterDetails(sealDTO.getRegNumber(),language);
						logger.debug("consenterList: "+consenterList.size());
						eForm.setConsenterList(consenterList);
						logger.debug("****************************"+sealDTO.getSelectedSubId());
					}
					else if(selectedSeal.equals("11"))  // STAMP DUTY SEAL
					{
						sealDTO.setOthers("0");
						bd.getStampDutyDetails(eForm, sealDTO.getRegNumber());
					}
					else if(selectedSeal.equals("12")) // REGISTRATION FEE SEAL
					{
						sealDTO.setOthers("0");
						sealDTO.setCopyFee("0");
						bd.getRegFeeDetails(sealDTO.getRegNumber(), eForm);
					}
					
					else if(selectedSeal.equals("13")) // REGISTRATION SEAL
					{
						
					}
					else if(selectedSeal.equals("14"))  //ADJUDICATION SEAL
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
						
						bd.getDutyDeedDetails(eForm, sealDTO.getRegNumber(),language);
						
					}
					else if(selectedSeal.equals("15")) // POA SEAL
					{
						ArrayList witnessList = bd.getWitnessDetails(sealDTO.getRegNumber(),language);
						logger.debug("wit list: "+witnessList.size());
						eForm.setWitnessList(witnessList);
					}
					else if(selectedSeal.equals("16"))  // pOA seal
					{
						
					}
					else if(selectedSeal.equals("17")) // pOA seal
					{
						
					}
					else if(selectedSeal.equals("18")) // pOA seal
					{

						String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
						if("Y".equalsIgnoreCase(applyFlag))
						{
							int result=0;
							request.setAttribute("resultCode", result);
							logger.debug("insertion----->"+result);
							forwardJsp =  "backToRemainingSeal";
							return 	mapping.findForward(forwardJsp); 
						}
						else
						{		
							bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
							int result = bd.insertPOASealDetails(sealDTO, officeId, userId,eForm);
						
							if(result==0)
							{	
								bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
								request.setAttribute("resultCode", result);
								logger.debug("insertion----->"+result);
								forwardJsp =  "backToRemainingSeal";
								return 	mapping.findForward(forwardJsp); 
							}
							else
							{
								throw new Exception();
							}
						}	
					}
					else if(selectedSeal.equals("19"))    //Report of commissioner 1
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
					}
					else if(selectedSeal.equals("20"))   //Report of commissioner 2
					{
						ArrayList partyList = bd.getPresentationPartyDetails(sealDTO.getRegNumber(),language);
						logger.debug("Seal list"+partyList.size());
						eForm.setPresentationPartyDetails(partyList);
					}
					else if(selectedSeal.equals("22"))  // rule 35
					{
						sealDTO.setMarketValue("0");
						sealDTO.setCorrectMarketValue("0");
						sealDTO.setStampduty("0");
					}
					else if(selectedSeal.equals("23"))   // rule 35
					{
						sealDTO.setStampduty("0");
						sealDTO.setCorrectStampDuty("0");
						sealDTO.setBookId("");
						sealDTO.setBookName("");
						ArrayList bookDetails = bd.getBookDetails();
						eForm.setBookDetailsList(bookDetails);
					}
					else if(selectedSeal.equals("24"))   // refusal
					{
						logger.debug("refusal seal");
						int result = bd.insertRefusalSeal(eForm,userId,officeId,response);
						request.setAttribute("resultCode", result);
						forwardJsp = "backToStampSeal";
					}
					forwardJsp = SealsConstant.SEAL_FORM1_JSP;
				}
				else if("RADIO_CHK".equalsIgnoreCase(actionName))
				{
					eForm.setRadioChk("Y");
					forwardJsp = SealsConstant.SEAL_FORM1_JSP;
				}
				else if(SealsConstant.SAVE_ACTION.equalsIgnoreCase(actionName))
				{
					logger.debug("SAVE ACTION");
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParties[] = eForm.getPresentParty().split("_");
					logger.debug("size of array"+selectedParties.length);
					int len = selectedParties.length;
					String partyArr[] = new String[len];
					for(int i = 0; i<selectedParties.length;i++)
					{
						String tmpArr[] = selectedParties[i].split(",");
						partyArr[i] = tmpArr[0];
					}
					if(selectedSeal.equals("1"))
					{
						//String partyArr[] = {"1"};
						try{
					
						String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
						if("Y".equalsIgnoreCase(applyFlag))
						{
							int result =0;
							logger.debug("insertion----->"+result);
							logger.debug("insertion----->"+result);
							request.setAttribute("resultCode", result);
							forwardJsp =  "backToPrsentation";
						}
						else
						{
							
							int result = bd.insertPresentationSealDetails(eForm.getPresentationPartyDetails(),sealDTO, officeId, userId,partyArr,eForm,language);
							logger.debug("insertion----->"+result);
							logger.debug("insertion----->"+result);
							request.setAttribute("resultCode", result);
							forwardJsp =  "backToPrsentation";
						}
						}
						catch(Exception e)
						{
							throw new Exception();
						}
						//done
						
					}
				}
				else if(SealsConstant.SAVE_ACTION_EXECUTION.equalsIgnoreCase(actionName))
				{
					logger.debug("SAVE ACTION EXECUTION");
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					logger.debug("size of array"+selectedParties.length);
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("2") || selectedSeal.equals("4")||selectedSeal.equals("3"))
					{
						int result = bd.insertExecution1Details(eForm.getPresentationPartyDetails(),sealDTO, officeId, userId,selectedParties,eForm,language);
						//done
						if(result==0)
						{
							bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						
					}
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_EXECUTION_SEAL_COMM.equalsIgnoreCase(actionName)) // selected seal 7
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result =0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("7"))
					{
						
						int result = bd.insertExecution7Details(eForm.getPresentationPartyDetails(),sealDTO, officeId, userId,selectedParties,eForm,language);
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						//done
					}	
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_ACTION_EXECUTION_GOVT_OFFCR.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{
						bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					
					if(selectedSeal.equals("6"))
					{
						int result  = bd.insertExecution6Details(eForm, officeId, userId);
						//done
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
					}
						//request.setAttribute("resultCode", result);
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_ACTION_EXECUTION_WILL.equals(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					/*String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					logger.debug("size of array"+selectedParties.length);*/
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					String selectedWitness[] = sealDTO.getSelectedWitnessList().split(",");
					logger.debug("size of array"+selectedWitness.length);
					
					int result = bd.insertExecutionSealWillDetails(eForm.getPresentationPartyDetails(), sealDTO, officeId, userId, eForm.getWitnessList(), selectedWitness,eForm,language);
					//done
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					//request.setAttribute("resultCode", result);
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_ACTION_WITNESS.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedWitness[] = sealDTO.getSelectedWitnessList().split(",");
					logger.debug("size of array"+selectedWitness.length);
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("8"))
					{
						int result = bd.insertWitnessDetails(eForm.getWitnessList(),sealDTO, officeId, userId,selectedWitness,eForm,language);
						//done
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				
				else if(SealsConstant.SAVE_ACTION_CONSENTER.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedConsenter[] = sealDTO.getSelectedConsenterList().split(",");
					logger.debug("size of array"+selectedConsenter.length);
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("10"))
					{
						boolean flag = bd.insertConsenterDetails(eForm.getConsenterList(), sealDTO, officeId, userId, selectedConsenter);
						logger.debug("insertion----->"+flag);
						
					}
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_ACTION_THUMB_SEAL.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParty[] = sealDTO.getSelectedPartyList().split(",");
					logger.debug("size of array"+selectedParty.length);
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("9"))
					{
						int result = bd.insertThumbSealDetails(eForm,sealDTO, officeId, userId,selectedParty,language);
						//done
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					}
					forwardJsp =  "backToAdmissionSeal";
				}
				else if(SealsConstant.SAVE_ADJ_SEAL.equalsIgnoreCase(actionName))
				{
					logger.debug("SAVE ACTION ADJUDICATION");
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					logger.debug("size of array"+selectedParties.length);
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("14"))
					{
						int result = bd.insertAdjudicationSealDetails(eForm.getPresentationPartyDetails(),sealDTO, officeId, userId,selectedParties,eForm,language);
						//done
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					}
					forwardJsp =  "backToStampSeal";
				}
				else if(SealsConstant.SAVE_ACTION_POA.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedWitness[] = sealDTO.getSelectedWitnessList().split(",");
					logger.debug("size of array"+selectedWitness.length);
					logger.debug("in action^^^^^^^^^^"+sealDTO.getName());
					logger.debug("in action^^^^^^^^^^"+sealDTO.getAddress());
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					if(selectedSeal.equals("15"))
					{
						int result = bd.insertPOASealDetails(eForm.getWitnessList(),sealDTO, officeId, userId,selectedWitness,eForm,language);
					//done
						if(result==0)
						{
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
						bd.updateSealApplied(sealDTO.getRegNumber(), selectedSeal,"Y");
						}
						else
						{
							logger.debug("Execution Seal Failed----->"+result);
							throw new Exception();
						}
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					}
					forwardJsp =  "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_ACTION_POA_VISIT.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertPOASealVisitDetails(sealDTO, officeId, userId,eForm);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					//done
					
					request.setAttribute("resultCode", result);
					logger.debug("insertion----->"+result);
					}
					forwardJsp =  "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_ACTION_POA_COMM.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertPOASealCommDetails(sealDTO, officeId, userId,eForm);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					}
					forwardJsp =  "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_POA.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertPOASealDetails(sealDTO, officeId, userId,eForm);
					//done
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					request.setAttribute("resultCode", result);
					logger.debug("insertion----->"+result);
					}
					forwardJsp =  "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_ACTION_REPORT_COMMISSIONER.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					String selectedSeal = sealDTO.getSelectedSubId();
					String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					logger.debug("size of array"+selectedParties.length);
					
					boolean flag = bd.insertReportCommissionerDetails(eForm.getPresentationPartyDetails(), sealDTO, officeId, userId, selectedParties,"1");
					logger.debug("insertion---->"+flag);
					}
				}
				else if(SealsConstant.SAVE_ACTION_REPORT_COMMISSIONER_2.equalsIgnoreCase(actionName))
				{
					String selectedSeal = sealDTO.getSelectedSubId();
					
					String selectedParties[] = sealDTO.getSelectedPartyList().split(",");
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), selectedSeal);
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					logger.debug("size of array"+selectedParties.length);
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					boolean flag = bd.insertReportCommissionerDetails(eForm.getPresentationPartyDetails(), sealDTO, officeId, userId, selectedParties,"2");
					logger.debug("insertion---->"+flag);
					logger.debug("insertion---->"+flag);
					}
				}
				
				else if(SealsConstant.SAVE_STAMP_SEAL.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.applyStampDutySeal(eForm, userId);
					//done
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					if(result == 0)
						logger.debug("seal applied successfully");
					else
						logger.debug("exception...................");
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToStampSeal";
				}
				else if(SealsConstant.SAVE_REG_FEE_SEAL.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					
					
					int result = bd.insertRegistrationSealDetails(eForm,userId,officeId,response);
					
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					//done
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_REFUSAL_SEAL.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					logger.debug("refusal seal");
					int result = bd.insertRefusalSeal(eForm,userId,officeId,response);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToRefusalSeal";
				}
				else if(SealsConstant.SAVE_ACTION_CORRECTION_SEAL.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertCorrectionSealDetails(eForm, userId);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToRemainingSeal";
				}
				else if(SealsConstant.SAVE_ACTION_RULE_35_1.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertRule35Seal1(eForm, userId);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToStampSeal";
				}
				else if(SealsConstant.SAVE_ACTION_RULE_35_2.equalsIgnoreCase(actionName))
				{
					String applyFlag=bd.getSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId());
					if("Y".equalsIgnoreCase(applyFlag))
					{
						int result=0;
						logger.debug("insertion----->"+result);
						request.setAttribute("resultCode", result);
					}
					else
					{	
					bd.insertCommonSealDetails(sealDTO.getRegNumber(),sealDTO.getSelectedSubId());
					int result = bd.insertRule35Seal2(eForm, userId);
					if(result==0)
					{
					logger.debug("insertion----->"+result);
					request.setAttribute("resultCode", result);
					bd.updateSealApplied(sealDTO.getRegNumber(), sealDTO.getSelectedSubId(),"Y");
					}
					else
					{
						logger.debug("Execution Seal Failed----->"+result);
						throw new Exception();
					}
					request.setAttribute("resultCode", result);
					}
					forwardJsp = "backToStampSeal";
				}
				else if("Cancel_Action_Seal".equalsIgnoreCase(actionName))
				{
					String backPage = eForm.getBackPage();
					if(backPage.equalsIgnoreCase("admission"))
					{
						forwardJsp =  "backToAdmissionSeal";
					}
					else if(backPage.equalsIgnoreCase("bookType"))
					{
						forwardJsp = "backToStampSeal";
					}
					else if(backPage.equalsIgnoreCase("regFeeSeal"))
					{
						forwardJsp = "backToRemainingSeal";
					}
					else
					{
						forwardJsp = "welcome";
					}
				}
				
				}catch(Exception e)
				{
					session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
					forwardJsp="failureSeals";
				}
				}
		return mapping.findForward(forwardJsp);
	}
}

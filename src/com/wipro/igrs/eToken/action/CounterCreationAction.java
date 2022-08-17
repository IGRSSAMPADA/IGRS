package com.wipro.igrs.eToken.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.eToken.bd.ETokenBD;
import com.wipro.igrs.eToken.constant.ETokenConstant;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.eToken.form.ETokenForm;

public class CounterCreationAction extends BaseAction {

	String forwardJsp =  "";
	private final Logger logger = Logger.getLogger(CounterCreationAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response,HttpSession session)
	throws Exception {

		ActionMessages messages = new ActionMessages();
		String page=request.getParameter("page");
		logger.debug("page"+page);
		String language=(String)session.getAttribute("languageLocale");

		System.out.println(language);
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		String officeId = (String)session.getAttribute("loggedToOffice");
		Date createdDate = new Date();
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		String tDate = sdfrmt.format(createdDate);

		if(form != null)
		{
			ETokenForm eForm = (ETokenForm)form;
			eForm.setLanguage(language);
			ETokenDTO eDTO = eForm.getEtokenDTO();
			eDTO.setLanguage(language);
			ETokenBD bd = new ETokenBD();
			String actionName = null;
			if(page!= null)
			{
				eForm.setDistrictList(new ArrayList());
				eForm.setSroList(new ArrayList());
				eForm.setUserCounterMappingList(new ArrayList());
				eForm.setCounterOfficeMappingList(new ArrayList());
				eForm.setCounterTypeList(new ArrayList());
				eForm.setLanguage(language);
				eForm.setCheckDisplay("");
				eForm.setCounterDetlsList(new ArrayList());
				eForm.setEtokenDTO(new ETokenDTO());
				eDTO.setActionName("");
				eForm.setErrorMsg("");
				eForm.setAvailableUsersList(new ArrayList());
				if("createCounter".equals(page))
				{
					logger.debug("*****in counter creation Action****");
					System.out.println(eForm.getLanguage());

					eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));

					String id = bd.getLock(officeId);
					eForm.setSroList(bd.getSROList(id, language));
					eForm.getEtokenDTO().setSroId(officeId);
					// eForm.setDistrictList(bd.getDistrictList(eForm
					// .getLanguage()));

					eForm.getEtokenDTO().setDistrictId(id);
					
					if(	eForm.getDistrictList().size()==0){
						
						forwardJsp = ETokenConstant.ErrorPAge;
					}
					
					else{
						
						forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
					}
				//	forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
				}
				else if("viewCounter".equals(page))
				{
					logger.debug("*****in counter view Action****");
					//eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));
					eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));
					String id = bd.getLock(officeId);
					eForm.setSroList(bd.getSROList(id, language));
					eForm.getEtokenDTO().setSroId(officeId);
					eForm.getEtokenDTO().setDistrictId(id);
						if(	eForm.getDistrictList().size()==0){
						
						forwardJsp = ETokenConstant.ErrorPAge;
					}
					
					else{
						
						forwardJsp = ETokenConstant.VIEW_COUNTER_JSP;
					}
					//forwardJsp = ETokenConstant.VIEW_COUNTER_JSP;
				}
				else if("makerMapping".equals(page))
				{
					logger.debug("*****in makerMapping Action****");
					eForm.setCountersList(bd.getCounterMakerDetls(eForm, officeId,language));
					eForm.setMakersList(bd.getMakerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedMakerList= bd.getMappedMakerDetls(officeId);
					if(mappedMakerList.size() > 0)
					{
						eForm.setMakerCounterMappingList(mappedMakerList);
						request.setAttribute("counterDetlsList", mappedMakerList);
					}

					forwardJsp = ETokenConstant.MAKER_COUNTER_MAPPING_JSP;
				}
				else if("checkerMapping".equals(page))
				{
					logger.debug("*****in checkerMapping Action****");

					eForm.setCountersList(bd.getCounterCheckerDetls(eForm, officeId,language));
					eForm.setCheckersList(bd.getCheckerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedCheckerList= bd.getMappedCheckerDetls(officeId);
					if(mappedCheckerList.size() > 0)
					{
						eForm.setCheckerCounterMappingList(mappedCheckerList);
						request.setAttribute("counterDetlsList", mappedCheckerList);
					}

					forwardJsp = ETokenConstant.CHECKER_COUNTER_MAPPING_JSP;
				}
				else if("userStatus".equals(page))
				{
					logger.debug("*****in status Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());

					forwardJsp = ETokenConstant.RECEPTION_COUNTER_JSP;
				}
				else if("BODAttendence".equals(page))
				{
					
					//eForm.setErrorMsg("");
					eForm.setErrorMsg("");
					logger.debug("*****BODAttendence****");
					eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));
					String id = bd.getLock(officeId);
					eForm.getEtokenDTO().setDistrictId(id);
					eForm.setSroList(bd.getSROList(id,language));
					eForm.getEtokenDTO().setSroId(officeId);
                 

					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					ArrayList counterData = new ArrayList();
					ArrayList attendenceData = new ArrayList();
					counterData=bd.getAttendenceData(language,eForm.getEtokenDTO().getDistrictId(),eForm.getEtokenDTO().getSroId());
					System.out.println("counterData"+counterData.size());
					eForm.setCounterData(counterData);
					System.out.println(eForm.getCounterData());
					request.setAttribute("counterList",eForm.getCounterData());
					
					
					if(	eForm.getDistrictList().size()==0){
						
						forwardJsp = ETokenConstant.ErrorPAge;
					}
					
					else{
					
					
					if(eForm.getCounterData().size()==0){
						eForm.setErrorMsg("noCounter");
						forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
						
					}
					
					else{
						forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
					}
					}
					//forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
					//System.out.println("check Flag"+SMSForm.getSmsdto().getCheckFlag());
				}
				else if("EODAttendence".equals(page))
				{
					forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
				}
			}
			if(request.getParameter("actionName")!= null)
			{
				actionName = request.getParameter("actionName");
			}
			else
			{
				actionName = eDTO.getActionName();
			}

			logger.debug("****Action name in Counter creation Action****"+actionName);



			if(ETokenConstant.GET_SRO.equalsIgnoreCase(actionName))
			{
				eForm.setErrorMsg("");
				logger.debug("****GET SRO" +
				" DETAILS*****");
				eForm.setCheckDisplay("");
				eForm.setSroList(new ArrayList());
				eForm.setUserId("");
				eForm.setCounterName("");
				//eForm.setSroList(bd.getSROList(eDTO.getDistrictId(),language));
				eForm.setSroList(bd.getEtokenSROList(eDTO.getDistrictId(),language));

				forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
			}
			if(ETokenConstant.GET_USER.equalsIgnoreCase(actionName))
			{
				eForm.setErrorMsg("");
				logger.debug("****GET USER" +
				" DETAILS*****");
				eForm.getEtokenDTO().setCounterType("");
				eForm.getEtokenDTO().setCounterUserId("");
				eForm.getEtokenDTO().setCounterOfficeMappingId("");
				eForm.setCounterOfficeMappingList(new ArrayList());
				// eForm.setUserCounterMappingList(new ArrayList());
				eForm.setCheckDisplay("");
				//eForm.getEtokenDTO().setSroId(officeId);
				eForm.setUserCounterMappingList(bd.getUserCounterMappingList(eDTO,eDTO.getUserType(),language));
				System.out.println(("nnn "+eForm.getUserCounterMappingList()));

				forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
			}
			if(ETokenConstant.GET_COUNTER_NAME.equalsIgnoreCase(actionName))
			{eForm.setErrorMsg("");
				logger.debug("****GET COUNTER NAME" +
				" DETAILS*****");
				eForm.setCheckDisplay("");
				eForm.setCounterOfficeMappingList(new ArrayList());
				eForm.getEtokenDTO().setCounterOfficeMappingId("");
				//eForm.getEtokenDTO().setSroId(officeId);
				eDTO.getCounterUserId();
				eDTO.setCounterUserId(eDTO.getCounterUserId());
				eDTO.getSroId();
				eDTO.getSroName();
				eDTO.getCounterType();
				eForm.getEtokenDTO().getCounterUserId();
				eForm.getEtokenDTO().getCounterType();
				//eForm.setCounterTypeList(bd.getCounterType(eDTO.getCounterType(),eDTO.getDistrictId(),eDTO.getSroId(),language));
				eForm.setCounterOfficeMappingList(bd.getcounterOfficeMappingList(eDTO.getSroId(),	eDTO.getUserType()));
				
				if(eForm.getCounterOfficeMappingList().size()==0){
					
					eForm.setErrorMsg("true");
				}
				//eForm.setCounterUserId(cId);

				forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
			}
			if(ETokenConstant.SAVE_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******SAVE Counter Detls*************");
				String dataInserted = bd.insertCounterDetls(eDTO, userId, tDate);
				if(dataInserted.equalsIgnoreCase("I"))
				{
					messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("S");
					eDTO.setDistrictId("");
					eDTO.setDistrictName("");
					eDTO.setSroId("");
					eDTO.setSroName("");
					eDTO.setCounterUserId("");
					eDTO.setCounterType("");
					eDTO.setCounterName("");
					eDTO.setCounterOfficeMappingId("");
					eDTO.setCounterDesc("");
					eDTO.setUserType("");
					//	String id = bd.getLock(officeId);
					//	eForm.setSroList(bd.getSROList(id,language));
					//	eForm.getEtokenDTO().setSroId(officeId);
					//	eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));

					//eForm.getEtokenDTO().setDistrictId(id);

					String id = bd.getLock(officeId);
					eForm.setSroList(bd.getSROList(id, language));
					eForm.getEtokenDTO().setSroId(officeId);
					eForm.setDistrictList(bd.getDistrictList(eForm
							.getLanguage()));
					eForm.getEtokenDTO().setDistrictId(id);
				}
				else if(dataInserted.equalsIgnoreCase("NI"))
				{
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
				}
				else
				{
					messages.add("ERROR_MSG_1", new ActionMessage("counter.unique.constarint"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("UN");
				}

				forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;

			}

			if(ETokenConstant.RESET_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******RESET Counter Detls*************");
				eDTO.setDistrictId("");
				eDTO.setDistrictName("");
				eDTO.setSroId("");
				eDTO.setSroName("");
				eDTO.setUserId("");
				eDTO.setCounterName("");
				eDTO.setCounterDesc("");
				eDTO.setUserType("");
				eDTO.setUserCounterMappingList(new ArrayList());
				eDTO.setCounterOfficeMappingList(new ArrayList());
				eDTO.setCounterOfficeMappingId("");
				eDTO.setCounterType("");
				eForm.setErrorMsg("");

				String id = bd.getLock(officeId);
				eForm.setSroList(bd.getSROList(id,language));
				eForm.getEtokenDTO().setSroId(officeId);
				// eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));

				eForm.getEtokenDTO().setDistrictId(id);
				System.out.println(eForm.getLanguage());

				eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));
				eForm.setUserCounterMappingList(new ArrayList());
				eForm.setCounterOfficeMappingList(new ArrayList());

				forwardJsp = ETokenConstant.CREATE_COUNTER_JSP;
			}

			if(ETokenConstant.GET_SRO_VIEW.equalsIgnoreCase(actionName))
			{
				logger.debug("****GET SRO DETAILS*****");
				eForm.setCheckDisplay("");
				//eForm.setSroList(bd.getSROList(eDTO.getDistrictId(),language));
				eForm.setSroList(bd.getEtokenSROList(eDTO.getDistrictId(),language));

				forwardJsp = ETokenConstant.VIEW_COUNTER_JSP;
			}

			if(ETokenConstant.VIEW_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******View Counter Detls*************");
				eForm.setCounterDetlsList(bd.getCounterDetails(eDTO,language));
				request.setAttribute("counterDetlsList", eForm.getCounterDetlsList());
				forwardJsp = ETokenConstant.VIEW_COUNTER_JSP;
			}

			if(ETokenConstant.RESET_VIEW_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******RESET VIEW Detls*************");
				eDTO.setDistrictId("");
				eDTO.setDistrictName("");
				eDTO.setSroId("");
				eDTO.setSroName("");
				eDTO.setUserType("");
				String id = bd.getLock(officeId);
				eForm.setSroList(bd.getSROList(id, language));
				eForm.getEtokenDTO().setSroId(officeId);
				// eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));
				eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));
				eForm.getEtokenDTO().setDistrictId(id);
				eForm.setCounterDetlsList(new ArrayList());
				forwardJsp = ETokenConstant.VIEW_COUNTER_JSP;
			}

			if(ETokenConstant.EDIT_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******EDIT VIEW Detls*************");
				String counterId = request.getParameter("counterId");
				eDTO.setCounterId(counterId);
				bd.getCounterDetailsComplete(counterId, eDTO,language);
				forwardJsp = ETokenConstant.EDIT_COUNTER_JSP;
			}

			if(ETokenConstant.SAVE_EDIT_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*******SAVE EDIT DETLS*************");
				logger.debug("*********status*********"+eDTO.getStatusChk());
				String dataInserted = bd.updateCounterDetls(eDTO, userId, tDate);
				com.wipro.igrs.device.applet.ETokenDTO dto = new com.wipro.igrs.device.applet.ETokenDTO();
				if(eDTO.getStatusChk().equalsIgnoreCase("A"))
					dto.setActiveDeactive("Active");
				else
					dto.setActiveDeactive("DeActivate");
				dto.setCounterNo(eDTO.getCounterId());
				dto.setCounterName(eDTO.getCounterName());
				dto.setCounterType(eDTO.getUserType());
				String ipAdress = new ETokenDAO().getOfficeIp(officeId);
				/*
				 * try { Socket clientSocket = new Socket(ipAdress, 6789);
				 * ObjectOutputStream stream = new
				 * ObjectOutputStream(clientSocket .getOutputStream());
				 * System.out.println(ipAdress);
				 * System.out.println(stream.toString());
				 * 
				 * stream.writeObject(dto);
				 * 
				 * stream.close(); clientSocket.close();
				 * 
				 * } catch (UnknownHostException e) {
				 * 
				 * e.printStackTrace(); } catch (IOException e) {
				 * 
				 * e.printStackTrace(); }
				 */
				if(dataInserted.equalsIgnoreCase("I"))
				{
					messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("S");
					eDTO.setDistrictId("");
					eDTO.setDistrictName("");
					eDTO.setSroId(officeId);
					eDTO.setSroName("");
					eDTO.setCounterName("");
					eDTO.setCounterDesc("");
					eDTO.setUserType("");
					forwardJsp = ETokenConstant.CONFIRMATION_JSP;
				}
				else if(dataInserted.equalsIgnoreCase("NI"))
				{
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
					forwardJsp = ETokenConstant.EDIT_COUNTER_JSP;
				}
				else
				{
					messages.add("ERROR_MSG_1", new ActionMessage("counter.unique.constarint"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("UN");
					forwardJsp = ETokenConstant.EDIT_COUNTER_JSP;
				}


			}

			if(ETokenConstant.MAKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("********MAKER COUNTER MAPPING*****");
				boolean flag = bd.insertMakerCounterDetls(eDTO, userId, tDate);
				logger.debug("data inserted : "+flag);
				if(flag)
				{
					//messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					//saveMessages(request, messages);
					//eForm.setCheckDisplay("S");
					eDTO.setMakerId("");
					eDTO.setCounterId("");
					eDTO.setMakerName("");
					eForm.setCountersList(bd.getCounterMakerDetls(eForm, officeId,language));
					eForm.setMakersList(bd.getMakerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedMakerList= bd.getMappedMakerDetls(officeId);
					if(mappedMakerList.size() > 0)
					{
						eForm.setMakerCounterMappingList(mappedMakerList);
						request.setAttribute("counterDetlsList", mappedMakerList);
					}
				}
				else
				{
					request.setAttribute("counterDetlsList", eForm.getMakerCounterMappingList());
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
				}

				forwardJsp = ETokenConstant.MAKER_COUNTER_MAPPING_JSP;
			}


			if(ETokenConstant.DELETE_MAKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("********Delete maker Mapping*****");
				String mappingId = request.getParameter("mappingId");
				logger.debug("mapping id---->"+mappingId);
				boolean flag = bd.updateMakerCounterDetls(mappingId, userId, tDate);
				logger.debug("data updated : "+ flag);
				if(flag)
				{
					//messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					//saveMessages(request, messages);
					//eForm.setCheckDisplay("S");
					eDTO.setMakerId("");
					eDTO.setCounterId("");
					eDTO.setMakerName("");
					eForm.setCountersList(bd.getCounterMakerDetls(eForm, officeId,language));
					eForm.setMakersList(bd.getMakerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedMakerList= bd.getMappedMakerDetls(officeId);
					if(mappedMakerList.size() > 0)
					{
						eForm.setMakerCounterMappingList(mappedMakerList);
						request.setAttribute("counterDetlsList", mappedMakerList);
					}
					else
					{
						eForm.setMakerCounterMappingList(null);
					}
				}
				else
				{
					request.setAttribute("counterDetlsList", eForm.getMakerCounterMappingList());
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
				}


				forwardJsp = ETokenConstant.MAKER_COUNTER_MAPPING_JSP;
			}

			if(ETokenConstant.RESET_MAKER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("*****RESET MAKER MAPPING***********");
				eDTO.setCounterId("");
				eDTO.setCounterName("");
				eDTO.setMakerId("");
				eDTO.setMakerName("");
				request.setAttribute("counterDetlsList", eForm.getMakerCounterMappingList());
				forwardJsp = ETokenConstant.MAKER_COUNTER_MAPPING_JSP;
			}

			if(ETokenConstant.CHECKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("********CHECKER COUNTER MAPPING*****");
				boolean flag = bd.insertCheckerCounterDetls(eDTO, userId, tDate);
				logger.debug("data inserted : "+flag);
				if(flag)
				{
					//messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					//saveMessages(request, messages);
					//eForm.setCheckDisplay("S");
					eDTO.setCheckerId("");
					eDTO.setCheckerName("");
					eDTO.setCounterId("");
					eForm.setCountersList(bd.getCounterCheckerDetls(eForm, officeId,language));
					eForm.setCheckersList(bd.getCheckerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedCheckerList= bd.getMappedCheckerDetls(officeId);
					if(mappedCheckerList.size() > 0)
					{
						eForm.setMakerCounterMappingList(mappedCheckerList);
						request.setAttribute("counterDetlsList", mappedCheckerList);
					}
				}
				else
				{
					request.setAttribute("counterDetlsList", eForm.getCheckerCounterMappingList());
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
				}

				forwardJsp = ETokenConstant.CHECKER_COUNTER_MAPPING_JSP;
			}


			if(ETokenConstant.DELETE_CHECKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("********Delete maker Mapping*****");
				String mappingId = request.getParameter("mappingId");
				logger.debug("mapping id---->"+mappingId);
				boolean flag = bd.updateCheckerCounterDetls(mappingId, userId, tDate);
				logger.debug("data updated : "+ flag);
				if(flag)
				{
					//messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));
					//saveMessages(request, messages);
					//eForm.setCheckDisplay("S");
					eDTO.setCheckerId("");
					eDTO.setCheckerName("");
					eDTO.setCounterId("");
					eForm.setCountersList(bd.getCounterCheckerDetls(eForm, officeId,language));
					eForm.setCheckersList(bd.getCheckerSRODetls(officeId));
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					ArrayList mappedCheckerList= bd.getMappedCheckerDetls(officeId);
					if(mappedCheckerList.size() > 0)
					{
						eForm.setCheckerCounterMappingList(mappedCheckerList);
						request.setAttribute("counterDetlsList", mappedCheckerList);
					}
					else
					{
						eForm.setCheckerCounterMappingList(null);
					}
				}
				else
				{
					request.setAttribute("counterDetlsList", eForm.getCheckerCounterMappingList());
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
				}


				forwardJsp = ETokenConstant.CHECKER_COUNTER_MAPPING_JSP;
			}

			if(ETokenConstant.RESET_CHECKER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("*****RESET CHECKER MAPPING***********");
				eDTO.setCounterId("");
				eDTO.setCounterName("");
				eDTO.setCheckerId("");
				eDTO.setCheckerName("");
				request.setAttribute("counterDetlsList", eForm.getCheckerCounterMappingList());
				forwardJsp = ETokenConstant.CHECKER_COUNTER_MAPPING_JSP;
			}
			if(ETokenConstant.GET_CHECKER_NAME.equalsIgnoreCase(actionName))
			{
				logger.debug("*****GET CHECKER NAME***********");
				ArrayList list = eForm.getCheckersList();
				Iterator itr = list.iterator();
				while(itr.hasNext())
				{
					ETokenDTO eedto = (ETokenDTO)itr.next();
					String checkerId = eedto.getCheckerId();
					if(checkerId.equalsIgnoreCase(eDTO.getCheckerId()))
					{
						eDTO.setCheckerName(eedto.getCheckerName());
					}
				}
				ArrayList mappedCheckerList= bd.getMappedCheckerDetls(officeId);
				eForm.setCheckerCounterMappingList(mappedCheckerList);
				request.setAttribute("counterDetlsList", eForm.getCheckerCounterMappingList());
				forwardJsp = ETokenConstant.CHECKER_COUNTER_MAPPING_JSP;
			}

			if(ETokenConstant.GET_MAKER_NAME.equalsIgnoreCase(actionName))
			{
				logger.debug("*****GET MAKER NAME***********");
				ArrayList list = eForm.getMakersList();
				Iterator itr = list.iterator();
				while(itr.hasNext())
				{
					ETokenDTO eedto = (ETokenDTO)itr.next();
					String makerId = eedto.getMakerId();
					if(makerId.equalsIgnoreCase(eDTO.getMakerId()))
					{
						eDTO.setMakerName(eedto.getMakerName());
					}
				}
				ArrayList mappedMakerList= bd.getMappedMakerDetls(officeId);
				eForm.setMakerCounterMappingList(mappedMakerList);
				request.setAttribute("counterDetlsList", eForm.getMakerCounterMappingList());
				forwardJsp = ETokenConstant.MAKER_COUNTER_MAPPING_JSP;
			}

			if(ETokenConstant.GET_MAPPED_USER_DETLS.equalsIgnoreCase(actionName))
			{
				logger.debug("*****GET MAPPED USER DETLS***********");
				eForm.setAvailableUsersList(bd.getAvailableUsersList(officeId, eDTO.getUserType()));
				request.setAttribute("mappedUsersList", eForm.getAvailableUsersList());
				forwardJsp = ETokenConstant.RECEPTION_COUNTER_JSP;
			}

			if(ETokenConstant.SAVE_MAPPED_DETAILS.equalsIgnoreCase(actionName))
			{
				logger.debug("**SAVE MAPPED DETAILS****");
				String ids[] = eDTO.getSelectedUserMappingIds().split(",");
				boolean flag = bd.updateAvailableUsersList(ids, eDTO.getUserType(), eForm);
				logger.debug("********DATA INSERTED**"+flag);
				if(flag)
				{

					messages.add("SUCCESS_MSG", new ActionMessage("counter.created.succesfully"));


					saveMessages(request, messages);
					eForm.setCheckDisplay("S");
					forwardJsp = ETokenConstant.CONFIRMATION_JSP;
				}
				else
				{
					messages.add("ERROR_MSG", new ActionMessage("counter.data.not.inserted"));
					saveMessages(request, messages);
					eForm.setCheckDisplay("NI");
					forwardJsp = ETokenConstant.RECEPTION_COUNTER_JSP;
				}


			}

			if(ETokenConstant.RESET_RECEPTION_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("****reset action*****");
				eForm.setAvailableUsersList(new ArrayList());
				eDTO.setUserType("");

				forwardJsp = ETokenConstant.RECEPTION_COUNTER_JSP;
			}
			//code Added for new Functionality at 24/08/2016

			if(ETokenConstant.OFFICE_VIEW.equalsIgnoreCase(actionName))
			{
				logger.debug("*******OFFICE_VIEW*************");
				//eDTO.setDistrictId("");
				//eDTO.setDistrictName("");
				eDTO.setSroId("");
				eDTO.setSroName("");
				eDTO.setCounterName("");
				eDTO.setCounterDesc("");
				eDTO.setUserType("");
				String id=eForm.getEtokenDTO().getDistrictId();
				eForm.setSroList(bd.getEtokenSROList(id,language));
				//eForm.getEtokenDTO().setSroId(officeId);
				//	eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));
				//eForm.getEtokenDTO().setDistrictId(id);
				//eForm.getEtokenDTO().setDistrictId(id);

				forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
			}
			/*if(ETokenConstant.COUNTERUSER_MAPPING.equalsIgnoreCase(actionName))
			{
				logger.debug("*******COUNTERUSER_MAPPING*************");
				//eDTO.setDistrictId("");
				//eDTO.setDistrictName("");
			//	eDTO.setSroId("");
			//	eDTO.setSroName("");

				eDTO.setCounterName("");
				eDTO.setCounterDesc("");
				eDTO.setUserType("");
				String id=eForm.getEtokenDTO().getDistrictId();
				String office=eForm.getEtokenDTO().getSroId();
				Map<String, String> userCounterMappingListMAp = new HashMap<String, String>();
				ArrayList userCounterMappingList = new ArrayList();
				ArrayList counterOfficeMappingList=new ArrayList();
			//	userCounterMappingList=bd.getUserCounterMappingList(id,office,language);
				eDTO.getUserId();
				eDTO.getCounterType();
				//counterOfficeMappingList=bd.getcounterOfficeMappingList(office);
				eForm.setUserCounterMappingList(userCounterMappingList);
				for(int i=0;i<userCounterMappingList.size();i++)
				{
					System.out.println(userCounterMappingList);
				}
				request.setAttribute("userCounterMappingList", userCounterMappingList);
				eForm.setCounterOfficeMappingList(counterOfficeMappingList);
				request.setAttribute("counterOfficeMappingList", counterOfficeMappingList);
				eForm.getEtokenDTO().setUserId(eDTO.getUserId());
				eForm.getEtokenDTO().setUserName(eDTO.getUserName());
				eForm.getEtokenDTO().setSroId(eDTO.getSroId());


				//eForm.getEtokenDTO().setSroId(officeId);
			//	eForm.setDistrictList(bd.getDistrictList(eForm.getLanguage()));
				//eForm.getEtokenDTO().setDistrictId(id);
				//eForm.getEtokenDTO().setDistrictId(id);

				forwardJsp = ETokenConstant.CREATE_ADMINSCREEN_TOKEN_JSP;
			}*/
			if(ETokenConstant.SAVE_COUNTER_MAPPING_DETLS.equalsIgnoreCase(actionName))
			{
				System.out.println(eForm.getCheckBoxFlag());
				System.out.println(eForm.getUncheckBoxFlag());
				String checkBoxSize[] = eForm.getCheckBoxFlag().split(",");
				String UncheckBoxSize[] = eForm.getUncheckBoxFlag().split(",");
				System.out.println("checkbox flag" + eForm.getCheckFlag());
				System.out.println("Uncheckbox flag" + eForm.getUncheckBoxFlag());
				/*if (eForm.getCheckBoxFlag() != null
						&& eForm.getCheckBoxFlag().contains(",")) {
					boolean flag = false;*/
				if(eForm.getCheckBoxFlag().contains("#"))
				{
					boolean updateData = bd.insertAttendenceData(eForm,
							checkBoxSize, eForm.getEtokenDTO().getDistrictId(),
							eForm.getEtokenDTO().getSroId(), userId, language);
					if (updateData) {
						eDTO.setErrorFlag("SubmitN");
					} else {
						eDTO.setErrorFlag("SubmitY");
					}
				}
				if( eForm.getUncheckBoxFlag().contains("#")){

					boolean updateData = bd.updateAttendenceData(eForm,
							UncheckBoxSize, eForm.getEtokenDTO().getDistrictId(),
							eForm.getEtokenDTO().getSroId(), userId, language);
					if (updateData) {
						eDTO.setErrorFlag("SubmitN");
					} else {
						eDTO.setErrorFlag("SubmitY");
					}

				}
				//	}
				/*else {
					boolean flag = false;
					boolean updateData = bd.insertAttendenceData(eForm,
							checkBoxSize, eForm.getEtokenDTO().getDistrictId(),
							eForm.getEtokenDTO().getSroId(), userId, language);
					if (updateData) {
						eDTO.setErrorFlag("SubmitN");

					} else {
						eDTO.setErrorFlag("SubmitY");
					}
				}*/
				eForm.setActionName(null);
				forwardJsp = ETokenConstant.MESSAGE_JSP;
			}
			// code End
		}
		return mapping.findForward(forwardJsp);
	}
}

package com.wipro.igrs.regcompletion.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.UserRegistration.action.CryptoLibrary;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.regcompletion.bd.RegComp1BD;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;
import com.wipro.igrs.regcompletion.form.DeedInitCompleteForm;
import com.wipro.igrs.regcompletion.rule.RegCompletionRule;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;

public class DeedAction1 extends BaseAction  {
	private  Logger logger = 
		(Logger) Logger.getLogger(DeedAction1.class);
	
	HashMap map = new HashMap();
	
	int count = 0;
	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	private String forwardJsp = "";
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session  ) throws Exception{
		
	    //HttpSession session = request.getSession();
		
		RegCompBD regCompBD = new RegCompBD();
		RegComp1BD bd = new RegComp1BD();
		DeedInitCompleteForm eForm = (DeedInitCompleteForm)form;
		PropertiesFileReader pr = 
			PropertiesFileReader.getInstance("resources.igrs");
		if(eForm !=null) {
			  String sessionRegTxnId = (String) 
				session.getAttribute("sessionRegTxnId");  
			  //  String sessionRegTxnId = "RegInit1225185324771";
		
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			// Added By Aruna			
			String regNum=request.getParameter("regID");
			String deedNum=request.getParameter("deedID");
			String propertyNum=request.getParameter("propertyID");
			if((regNum!=null && regNum.length()>0) && (deedNum!=null && deedNum.length()>0))			
			{
				String param[] =new String[3];
				param[0]=regNum;
				param[1]=deedNum;
				param[2]=propertyNum;				
				eForm.setFormList(regCompBD.getPartyIdList(param));
				forwardJsp="partyIds";
				return mapping.findForward(forwardJsp);				
			}
			
			if(sessionRegTxnId !=null || !"".equals(sessionRegTxnId.trim())) {
				
				String deedPro = request.getParameter("deedProp");
				if("start".equals(deedPro)) {
					eForm.setDeedTxnIdList(
							regCompBD.getDeedsList(sessionRegTxnId));
				}
				//eForm.setDeedTxnIdList(regCompBD.getDeedsList(sessionRegTxnId));
				
				eForm.setFormName("");
				eForm.setActionName("");
				// Added By Aruna
				//eForm.getCommonDTO().setAddMoreCounter(String.valueOf(count));
				logger.debug("inside deedProp");
				forwardJsp = "deedPropertyMap";
				
			}
			
			CommonDTO dtoCommon = eForm.getCommonDTO();
			RegInitCompleteDTO regDTO = eForm.getRegDTO();
		 
		    String roleId =  (String)session.getAttribute("role");
		    session.setAttribute("roleName", roleId);
		    String funId  = (String)session.getAttribute("functionId");
		    String userId  = (String)session.getAttribute("UserId");
		    dtoCommon.setUserId(userId);
		    logger.debug("roleId:-"+roleId+"funId:-"+"userId:-"+userId);
		    
			if(RegCompConstant.DEED_PROPERTY_MAP_PAGE.equals(formName)) {
				if(RegCompConstant.STORE_ACTION.equals(actionName)) {
					if(isTokenValid(request)) {
						logger.debug("hdnTxnProperty:-"
								+dtoCommon.getHdnPropertyTxnId());
						
						String deedTxnID = dtoCommon.getDeed();										
						String propertyTxnID = dtoCommon.getHdnPropertyTxnId();
						logger.debug("propertyTxnID:-"+propertyTxnID);
						logger.debug("deedTxnID:-"+deedTxnID);
						dtoCommon.setRegNo(sessionRegTxnId);
						HashMap mapDeed = new  HashMap();
						// Edited By Aruna
						if(propertyTxnID!=null && propertyTxnID.length()>0)
						{
							if(!"".equals(deedTxnID)) {
								  mapDeed = 
									regCompBD.saveDeedToPropAndTranMap(
												dtoCommon,eForm.getNoOfProperty(),
												eForm.getDeedTxnIdList());
								dtoCommon.setDeed("");
								eForm.setPartyTxnIdList(new ArrayList());
								eForm.setDeedTxnIdList(mapDeed);
							}
						}
						logger.debug("mapDeed.size():-"+mapDeed.size());
						
						if(mapDeed!=null) {
							if(mapDeed.size() > 0 ) {
								forwardJsp = "deedPropertyMap";
							}else if(mapDeed.size() == 0 ) {
								dtoCommon.setDeedList(
										regCompBD.getDeedList(
												dtoCommon.getRegNo()));
								// Added By Aruna
								session.setAttribute("DeedList",dtoCommon.getDeedList());
								/*dtoCommon.setPhotoIdList(
										regCompBD.getDeedPropAndTreanMapping(
												dtoCommon.getRegNo()));*/
								logger.debug("inside DeedProp");
								// Added By Aruna
							
								count=0;
								dtoCommon.setAddMoreCounter((String.valueOf(count)));
								map = new HashMap();
								dtoCommon.setMapProperty(new HashMap());
						
								dtoCommon.setProDeedList(new ArrayList());
								dtoCommon.setPartyDeedList(new ArrayList());
								dtoCommon.setPartyTypeList(new ArrayList());
								dtoCommon.setDeed("");
								dtoCommon.setPropId("");
								
								forwardJsp = "deedProp";
							}
						}
						resetToken(request);
						// Added By Aruna
						saveToken(request);
						eForm.setFormName("");
						eForm.setActionName("");
					}
				}
				
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					dtoCommon = new CommonDTO();
					eForm.setFormName("");
					eForm.setActionName("");
					forwardJsp = "welcome";
				}
				if(RegCompConstant.CHANGE_ACTION.equals(actionName)) {					
					String noOfProperty = 
								regCompBD.getNofProperty(dtoCommon.getDeed());
					int noOfPro = noOfProperty != null ? 
								Integer.parseInt(noOfProperty) :
								0;
					eForm.setNoOfProperty(noOfProperty);
					String[] param = new String[2];
					param[0] = dtoCommon.getDeed();
					param[1] = sessionRegTxnId;
					if(noOfPro > 0) {
						
						eForm.setPropertyTxnIdList(
								regCompBD.getPropertyListByRegId(
										param));
					}else {
						eForm.setPropertyTxnIdList(
								bd.selectParties(
										param));
					}
					saveToken(request);
					forwardJsp = "deedPropertyMap";
				}
			}
			if(RegCompConstant.DEED_PARTY_PROPERTY_MAP_PAGE.equals(formName)){
				logger.debug("actionName:--"+actionName);
				if(RegCompConstant.DEED_CHANGE_ACTION.equals(actionName)) {
					String deedTxnId = dtoCommon.getDeed();
					String regNo = dtoCommon.getRegNo();
					
					logger.debug("deedTxnId:-"+deedTxnId);
					logger.debug("regNo:-"+regNo);
					
					dtoCommon.setProDeedList(
							regCompBD.getPropertyList(deedTxnId, regNo));
					
					dtoCommon.setPartyDeedList(
							regCompBD.getPartyList(deedTxnId, regNo));
					dtoCommon.setPartyTypeList(
							regCompBD.getPartyTypeList(deedTxnId,regNo));
					logger.debug("inside Deed Change");
					forwardJsp = "deedProp";
					saveToken(request);
				}
				if(RegCompConstant.PARTY_CHANGE_ACTION.equals(actionName)) {
					String deedTxnId = dtoCommon.getDeed();
					String regNo = dtoCommon.getRegNo();
					
					logger.debug("deedTxnId:-"+deedTxnId);
					logger.debug("regNo:-"+regNo);
					
					dtoCommon.setProDeedList(
							regCompBD.getPropertyList(deedTxnId, regNo));
					
					dtoCommon.setPartyDeedList(
							regCompBD.getPartyList(deedTxnId, regNo));
					dtoCommon.setPartyTypeList(
							regCompBD.getPartyTypeList(deedTxnId,regNo));
					logger.debug("inside Deed Change");
					forwardJsp = "deedProp";
					saveToken(request);
				}
				if(RegCompConstant.DELETE_ACTION.equals(actionName)) {
				
					count = count - 1;
					
					dtoCommon.setAddMoreCounter(String.valueOf(count));
					String deedTxnId =dtoCommon.getHdnDeleteProperty();
					logger.debug("deedTxnId:-"+deedTxnId);
					String[] deedTxn = deedTxnId.split(",");
					HashMap map = dtoCommon.getMapProperty();
					if(deedTxn!=null) {
						for(int i=0;i<deedTxn.length;i++) {
							map.remove(deedTxn[i]);						
						}
					} 
					dtoCommon.setMapProperty(map);
					forwardJsp = "deedProp";
					saveToken(request);
				}
				if(RegCompConstant.ADD_ACTION.equals(actionName)) {
					
					count = count +1;
					
					CommonDTO dto = new CommonDTO();
					dtoCommon.setAddMoreCounter(String.valueOf(count));					 
					dto.setRegNo(sessionRegTxnId);
					dto.setDeed(dtoCommon.getDeed());
					dto.setPropId(dtoCommon.getPropId());
					String partyTxn = dtoCommon.getHdnPropertyTxnId();
					// Edited By Aruna
					//HashMap mpPin = eForm.getMapPin();
					logger.debug("partyTxn:-"+partyTxn);
					ArrayList list = new ArrayList();
					HashMap mapPin = eForm.getMapPin();
					RegCompletionRule rule = new RegCompletionRule();
					ArrayList errorList = rule.verifyPIN(mapPin,partyTxn);
					if(rule.isError()) {
						request.setAttribute(RegCompConstant.ERROR_LIST,
											errorList);
						request.setAttribute(RegCompConstant.ERROR_FLAG,
											"true");						
					}else {
						if(partyTxn!=null) {
							String[] partyTxnId = partyTxn.split(",");
							if(partyTxnId !=null && !"".equals(partyTxn.trim())) {
								for(int i=0;i<partyTxnId.length;i++){
									String[] partyId = partyTxnId[i].split("~");
									CommonDTO dtoList = new CommonDTO();
									
									if(partyId[0]!=null && !"".equals(
												partyId[0].trim())){
										logger.debug("partyId:-"+partyId[0]);
										dtoList.setPartyId(partyId[0]);
									}
									if(partyId[1]!=null&& !"".equals(
											partyId[0].trim())){
										logger.debug("partyId:-"+partyId[1]);
										String[] partyType = partyId[1].split("#");
										dtoList.setPartyTypeName(partyType[0]);
										dtoList.setPartyTypeId(partyType[1]);										
										dtoList.setPartyOwnerFlag(
														partyType[2]);
										if("Y".equals(
												dtoList.getPartyOwnerFlag())) {
											// Edited By aruna
											/*CommonDTO dtoMap = (CommonDTO)mpPin.get(
													dtoList.getPartyId());*/
											CommonDTO dtoMap = (CommonDTO)mapPin.get(
														dtoList.getPartyId());
											
										}else {
											dtoList.setChkOwnerFlag(partyType[3]);
										}
									}
									list.add(dtoList);
								}
							}
						}						
						dto.setPropertyList(list);						
         				//map.put(dto.getDeed(), dto);
						map.put(dto.getDeed()+"~"+dto.getPropId(), dto);
						dtoCommon.setMapProperty(map);
					}
					saveToken(request);
					// Added By Aruna
					eForm.getCommonDTO().setDeed("");
					eForm.getCommonDTO().setPropId("");
					forwardJsp = "deedProp";
				}
				if(RegCompConstant.VERIFY_ACTION.equals(actionName)) {
					dtoCommon.setOldPropertyId("");
					dtoCommon.setOldRegNo("");
					dtoCommon.setPinNumber("");
					dtoCommon.setFname("");
					dtoCommon.setMname("");
					dtoCommon.setLname("");
					dtoCommon.setOldPartyId("");
					request.setAttribute("partyId", 
							dtoCommon.getVerifyPinParty());
					forwardJsp = "pinverify";
				}
				if(RegCompConstant.NEXT_ACTION.equals(actionName)) {
					HashMap mapPin = eForm.getMapPin();
					RegCompletionRule rule = new RegCompletionRule();
					String partyTxn = dtoCommon.getHdnPropertyTxnId();
					ArrayList errorList = rule.verifyPIN(mapPin,partyTxn);
					if(rule.isError()) {
						request.setAttribute(RegCompConstant.ERROR_LIST,
											errorList);
						request.setAttribute(RegCompConstant.ERROR_FLAG,
											"true");
						forwardJsp = "deedProp";
					}else {
						if(isTokenValid(request)) {
						
							logger.debug("instrument:-"
									+eForm.getInstrument());
							
							HashMap mapReturn = dtoCommon.getMapProperty();							
							CommonDTO dto = new CommonDTO();							
        					dtoCommon.setAddMoreCounter(String.valueOf(count));
							//dtoCommon.setAddMoreCounter(String.valueOf(0));							
							dto.setRegNo(sessionRegTxnId);
							dto.setDeed(dtoCommon.getDeed());
							dto.setPropId(dtoCommon.getPropId());
						
							logger.debug("partyTxn:-"+partyTxn);
							ArrayList list = new ArrayList();
							if(partyTxn!=null && !"".equals(partyTxn.trim())) {
								String[] partyTxnId = partyTxn.split(",");
								if(partyTxnId !=null && !"".equals(partyTxn.trim())) {
									for(int i=0;i<partyTxnId.length;i++){
										String[] partyId = partyTxnId[i].split("~");
										CommonDTO dtoList = new CommonDTO();
										if(partyId[0]!=null){
											logger.debug("partyId:-"+partyId[0]);
											dtoList.setPartyId(partyId[0]);
										}
										if(partyId[1]!=null){
											logger.debug("partyId:-"+partyId[1]);
											String[] partyType = partyId[1].split("#");
											dtoList.setPartyTypeName(partyType[0]);
											dtoList.setPartyTypeId(partyType[1]);
											dtoList.setPartyOwnerFlag(
													partyType[2]);
											dtoList.setChkOwnerFlag(partyType[3]);
										}
										list.add(dtoList);
									}
								}
							}
							dto.setPropertyList(list);							
							//mapReturn.put(dto.getDeed(), dto);
							mapReturn.put(dto.getDeed()+"~"+dto.getPropId(), dto);
							dtoCommon.setMapProperty(mapReturn);
							
							
							boolean boo
								=regCompBD.saveDeedToPropAndTranMap(
									mapReturn,sessionRegTxnId);
							String functionId = (String)session.getAttribute("functionId");
							String userType = (String)session.getAttribute("roleId");
							String serviceId = pr.getValue("REGISTRATION_SERVICE_ID");
						    dtoCommon.setRegNo(sessionRegTxnId);
						    double gStampDuty = 0.0;
						    double gOtherFee  = 0.0;
						    double gDutyVal  = 0.0;
						    double gRegFee  = 0.0;
						    double gTotal  = 0.0;
						    double dPaid = 0.0;
							try {
								ArrayList propDeedList = 
									regCompBD.getPrDeedDet(sessionRegTxnId,
															functionId,
															userType,
															serviceId);
								for(int i=0;i<propDeedList.size();i++)
									{
										dtoCommon = (CommonDTO)propDeedList.get(i);
										if(dtoCommon.getStampDuty()!=null)
											gStampDuty = gStampDuty + 
												Double.parseDouble(
													dtoCommon.getStampDuty());
										else
											gStampDuty = gStampDuty + 0;
										if(dtoCommon.getOtherFee()!=null)
											gOtherFee = gOtherFee + 
												Double.parseDouble(
														dtoCommon.getOtherFee());
										else
											gOtherFee = gOtherFee + 0;
										if(dtoCommon.getDutyVal()!=null)
											gDutyVal = gDutyVal + 
												Double.parseDouble(
														dtoCommon.getDutyVal());
										else
											gDutyVal = gDutyVal + 0;
										if(dtoCommon.getRegFee()!=null)
											gRegFee = gRegFee + 
												Double.parseDouble(
														dtoCommon.getRegFee());
										else
											gRegFee = gRegFee + 0;
										if(dtoCommon.getTotal()!=null)
											gTotal = gTotal  + 
												Double.parseDouble(
														dtoCommon.getTotal());
										else
											gTotal = gTotal  + 0;
									}
									String dutyPaid = 
										regCompBD.getDutyPaid(dtoCommon.getRegNo());
									dPaid = Double.parseDouble(dutyPaid);
									gTotal = gTotal - dPaid;
									IGRSCommon common = new IGRSCommon();
									eForm.setStampDuty(dutyPaid);
									eForm.setOtherFee(String.valueOf(gOtherFee));
									eForm.setGMarketVal(String.valueOf(gDutyVal));
									eForm.setRegFee(common.getCurrencyFormat(gRegFee));
									eForm.setTotal(String.valueOf(gTotal));
									
									logger.debug("eForm.getStampDuty():-"
												+eForm.getStampDuty());
									logger.debug("eForm.getOtherFee():-"
												+eForm.getOtherFee());
									logger.debug("eForm.getGMarketVal():-"
												+eForm.getGMarketVal());
									logger.debug("gRegFee:-"+gRegFee);
									logger.debug("gTotal:-"+gTotal);
									
									dtoCommon.setMapList(propDeedList);
									ArrayList complList = regCompBD.getComplList();
									dtoCommon.setComplList(complList);
									dtoCommon.setDeedList(regCompBD.getDeedType());
									//session.setAttribute("user",userType);
							        session.setAttribute("amount",eForm.getTotal());
									// Added By Aruna
							        session.setAttribute("propDeedList", propDeedList);									
									request.setAttribute("regCommonDto", dtoCommon);
							        
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							
								forwardJsp = "mapRes";
								resetToken(request);
								eForm.setFormName("");
								eForm.setActionName("");
						}
					}
				}
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					dtoCommon = new CommonDTO();
					eForm.setFormName("");
					eForm.setActionName("");
					forwardJsp = "welcome";
				}
				// Added By Aruna
				if(RegCompConstant.RESET_ACTION.equals(actionName)) 
				{
					dtoCommon.setDeedList(
							regCompBD.getDeedList(
									dtoCommon.getRegNo()));				
					session.setAttribute("DeedList",dtoCommon.getDeedList());
					/*dtoCommon.setPhotoIdList(
							regCompBD.getDeedPropAndTreanMapping(
									dtoCommon.getRegNo()));*/
					logger.debug("inside DeedProp");
					count=0;
					dtoCommon.setAddMoreCounter((String.valueOf(count)));
					map = new HashMap();
					dtoCommon.setMapProperty(new HashMap());					
					dtoCommon.setProDeedList(new ArrayList());
					dtoCommon.setPartyDeedList(new ArrayList());
					dtoCommon.setPartyTypeList(new ArrayList());
					dtoCommon.setDeed("");
					dtoCommon.setPropId("");					
					forwardJsp = "deedProp";
				}
			}
			if(RegCompConstant.PIN_VERIFY_POPUP_PAGE.equals(formName)) {
				if(RegCompConstant.VERIFY_ACTION.equals(actionName)) {
					 String partyId = dtoCommon.getVerifyPinParty();
					 logger.debug("partyId:-"+partyId);
					 String pinNumber = dtoCommon.getPinNumber();
					 logger.debug("pinNumber:-"+pinNumber);
					 CryptoLibrary crypt = new CryptoLibrary();
					 CommonDTO dtoMap = new CommonDTO();
					 //dtoCommon.setPinNumber(
					 //		 crypt.encrypt(dtoCommon.getPinNumber()));
					 dtoMap.setPinNumber(
							 crypt.encrypt(dtoCommon.getPinNumber()));
					 dtoMap.setPartyId(partyId);
					 dtoMap.setOldRegNo(dtoCommon.getOldRegNo());
					 dtoMap.setOldPartyId(dtoCommon.getOldPartyId());
					 dtoMap.setOldPropertyId(dtoCommon.getOldPropertyId());
					 dtoMap.setFname(dtoCommon.getFname());
					 dtoMap.setMname(dtoCommon.getMname());
					 dtoMap.setLname(dtoCommon.getLname());
					 dtoMap.setOrgName(dtoCommon.getOrgName());	
					 dtoMap.setPinStatus(regCompBD.verifyPin(dtoMap));
					 HashMap mapPin = eForm.getMapPin();
					 mapPin.put(partyId, dtoMap);
					 //session.setAttribute("mapPin", mapPin);
					 eForm.setMapPin(mapPin);
					 forwardJsp = "deedProp";
					 saveToken(request);
					//forwardJsp = "welcome";
				}
			}
			if(RegCompConstant.PARTY_PROERTY_DISPLAY_PAGE.equals(formName)) {
				if(RegCompConstant.SPOT_NEXT_ACTION.equals(actionName)) {
					
					String propertyID = dtoCommon.getPropId();
					logger.debug("propertyID:-"+propertyID);
					dtoCommon.setRegNo(sessionRegTxnId);
					
					boolean bol = regCompBD.insSpReqDet(dtoCommon,
											roleId,funId,userId);
					request.setAttribute("bol", String.valueOf(bol));
					String regId = regCompBD.insertRegDetails(sessionRegTxnId,
							eForm.getStampDuty(),eForm.getRegFee(),eForm.getTotal(),
							"",eForm.getUserId(),"spot");
					session.setAttribute("regCompId",regId);
					
					forwardJsp = "welcome";
					
					/*eForm.setPropertyTxnIdList(
							regCompBD.getPropertyList(sessionRegTxnId));
					forwardJsp = "toSpotInspection";*/
				}
				if(RegCompConstant.PAYMENT_NEXT_ACTION.equals(actionName)) {
					 forwardJsp = "toPayment";
					 String payId=(String)session.getAttribute("status"); 
				     session.setAttribute("amount",eForm.getTotal());
				     String regId = regCompBD.insertRegDetails(sessionRegTxnId,
								eForm.getStampDuty(),eForm.getRegFee(),eForm.getTotal(),
								payId,eForm.getUserId(),"");
				     session.setAttribute("regCompId",regId);
					 session.setAttribute("forwardPath","reginitComplete.do?param=success");
				}
				if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
					dtoCommon = new CommonDTO();
					eForm.setFormName("");
					eForm.setActionName("");
					forwardJsp = "welcome";
				}
			}
			
			eForm.setCommonDTO(dtoCommon);
		}
	
	 	 
/*		String forward = "success";
		//HttpSession session = request.getSession();
		String regId = (String)request.getParameter("deedProp");
		if(regId==null)
			regId = (String)session.getAttribute("App_No");
		else
			session.setAttribute("App_No",regId);
				
		//getting form object from request
		if(request.getAttribute("r
						egCommonDto")!= null){
			dto = (CommonDTO)request.getAttribute("regCommonDto");
			appForm = (ApplicantForm)request.getAttribute("appForm");
		}else{
			dto = new CommonDTO();
			appForm = (ApplicantForm)form;
		}
		
		if(regId!=null){
			dto = new CommonDTO();
			dto1 = new Common1DTO();
			appForm = (ApplicantForm)form;
			appForm.setRegNo(regId);
			dto.setDeedList(regCompBD.getDeedsList(regId));
			dto.setPhotoIdList(regCompBD.getPropertyListByRegId(regId));
			appForm.setNofProperty(regCompBD.getNofProperty(appForm.getDeed()));
			appForm.setCommon1DTO(dto1);
			appForm.setCommonDTO(dto);
		}
		
		if(request.getParameter("deedChange")!=null){
			ArrayList list = (ArrayList)session.getAttribute("deedList1");
			if(list == null)
				dto.setDeedList(regCompBD.getDeedsList(appForm.getRegNo()));
			else{
				dto.setDeedList(list);
				dto.setPhotoIdList(regCompBD.getPropertyListByRegId(appForm.getRegNo()));
				appForm.setNofProperty(regCompBD.getNofProperty(appForm.getDeed()));
				RegComp1BD bd = new RegComp1BD();
				dto1 = new Common1DTO();
				try {
					if(appForm.getNofProperty().equalsIgnoreCase("0")){
						dto1.setPhotoIdList(bd.selectParties(appForm.getRegNo()));
					}
				} catch (Exception e) {
					logger.error("DeedAction1 in dao execute:" + e.getStackTrace());
				}
				appForm.setCommon1DTO(dto1);
				appForm.setCommonDTO(dto);
			}
				
		}
		
		if(request.getParameter("addNext")!=null){
			String temp = appForm.getDeed();
			String tempid = appForm.getRegNo();
			//getting all the selected deeds list from session
			ArrayList list = (ArrayList)session.getAttribute("deedList1");
			
			if(list == null)
				list = dto.getDeedList();
			logger.debug("list------>"+list);
			CommonDTO tempDto= null;
			String params[] = new String[3];
			for(int i=0;i<list.size();i++){
				tempDto = (CommonDTO)list.get(i);
					if(tempDto.getId().equalsIgnoreCase(temp)){
						//saving the data
						String inst = appForm.getInstrument();
						params[0] = appForm.getRegNo();
						params[1] = appForm.getDeed();
						if(inst != null){
							String arr[] = inst.split(",");
							for(int j=0;j<arr.length;j++){
								params[2] = arr[j];
								try {
								boolean boo=regCompBD.saveDeedToPropAndTranMap(params,appForm.getNofProperty(),list.size());
								}catch (Exception e) {
									logger.error("DeedAction1 in action saveDeedToPropAndTranMap:" + e.getStackTrace());
								}
							}
							dto = new CommonDTO();
							dto1 = new Common1DTO();
						list.remove(i);
						session.setAttribute("deedList1", list);
						}
				}
			}
			logger.debug("request.getParameter(addNext):-"+request.getParameter("addNext"));
			if("next".equalsIgnoreCase(request.getParameter("addNext"))){
				forward = "deedProp";
				logger.debug("forward:-"+forward);
				try {
					dto.setPhotoIdList(regCompBD.getDeedPropAndTreanMapping(appForm.getRegNo()));
					logger.debug("dto.setPhotoIdList():-"+dto.getPhotoIdList());
				} catch (Exception e) {
					logger.error("DeedAction1 in action getDeedPropAndTreanMapping:" + e.getStackTrace());
				}
			}else
			{
				dto.setPhotoIdList(new ArrayList());
				if(dto1!=null)
				dto1.setPhotoIdList(new ArrayList());
			}
			dto.setDeedList(list);
			appForm.setCommonDTO(dto);
			appForm.setCommon1DTO(dto1);
			appForm.setRegNo(tempid);
		}
		logger.debug("deed Map---->"+request.getParameter("deedMap"));
		if("next".equalsIgnoreCase(request.getParameter("deedMap"))){
			boolean boo=regCompBD.saveDeedToPropAndTranMap(appForm.getInstrument());
			forward="deedToMap";
		}
		logger.debug("forwardAction:-"+forward);
		appForm.setCommonDTO(dto);
		request.setAttribute("regCommonDto", dto);
		request.setAttribute("appForm", appForm);*/
		
		return mapping.findForward(forwardJsp);
	}
}

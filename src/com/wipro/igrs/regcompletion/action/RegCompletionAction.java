package com.wipro.igrs.regcompletion.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.Action;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.regcompletion.bd.CommonDropDownBD;
import com.wipro.igrs.regcompletion.bd.RegCompBD;
import com.wipro.igrs.regcompletion.bo.PropertyValuationBO;
import com.wipro.igrs.regcompletion.bo.RegCompBO;
import com.wipro.igrs.regcompletion.constants.RegCompConstant;
import com.wipro.igrs.regcompletion.dao.RegCompDAO;
import com.wipro.igrs.regcompletion.dto.PropertyValuationDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.regcompletion.form.RegCompletionForm;
import com.wipro.igrs.regcompletion.util.PropertiesFileReader;

/**
 * @author Hari Krishna G.V
 * 
 */
public class RegCompletionAction extends   BaseAction  {
	private int count = 0;
	private HashMap map = null;
	 
	private Logger logger = (Logger) Logger.getLogger(RegCompletionAction.class);
	private int floorCount = 0;
	boolean bol = true;
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp ; 
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons,
			HttpSession session )
			throws IOException, ServletException, Exception {
		
		try {
			
			    //HttpSession session = request.getSession();
			
			if (logger.isDebugEnabled()) {
				logger
						.debug("execute(ActionMapping, ActionForm, " 
								+ "HttpServletRequest, HttpServletResponse) -"
									+ " start");
			}
			if(request.getParameter("caveatFlag").equals("true")) {
				String proTxn=request.getParameter("propertyTxnID");
				session.setAttribute("propTxnNo", proTxn);
				String sessionRegTxnId=request.getParameter("registrationNo");
				session.setAttribute("sessionRegTxnId", sessionRegTxnId);
			}
			PropertiesFileReader pr = 
	    		PropertiesFileReader.
	    		 getInstance("com.wipro.igrs.propertyvaluation");
			if (form != null) {
				RegCompletionForm eForm = (RegCompletionForm) form;
			    //String sessionRegTxnId = "RegInit1225185324771";
				String sessionRegTxnId = (
						 String) session.getAttribute("sessionRegTxnId");
				
				logger.debug("sessionRegTxnId:-"+sessionRegTxnId);
				/*if (sessionRegTxnId == null || sessionRegTxnId.equals("")) {
					sessionRegTxnId = "MP123456";
				}*/
				
				String distId = null;
				String tehsilId = null;
				String areaTypeId = null;
				String govMunicipalId = null;
				String wardOrPatwariId = null;
				String mohallaOrVillageId = null;
				String mohallaOrVillageName = "";
				String wardId = null;
				String patwariId = null;
				String mohallaId = null;
				String villageId = null;

				String propertyTypeId = null;
				String wardOrPatwariName = "";
				
				IGRSCommon common = new IGRSCommon();
				PropertyValuationBO valueBo = new PropertyValuationBO();
				CommonDropDownBD bd = new CommonDropDownBD();
				
				RegCompletDTO proDetailsdto = null;
				RegCompletDTO dto = new RegCompletDTO();
				ArrayList resultSplitPartList = bd
				.getLocsOfSplitPart();

				eForm.getRegcompletDto().
					setSplitPartList(resultSplitPartList);
				
				RegCompBO regcompbo = new RegCompBO();
				RegCompletDTO refdto = eForm.getRegcompletDto();
				
				eForm.setDistList(valueBo.getDistrict());
				logger.debug("area type:-"+valueBo.getAreaType()
						         +":"+valueBo.getDistrict());
				eForm.setAreaTypeList(valueBo.getAreaType());
				eForm.setMunicipalList(valueBo.getMunicipalBody());
				
				eForm.setPropertyType(valueBo.getPropertyType());
				eForm.setLandTypeList(valueBo.getLandType());
				
				String formName = eForm.getFormName();
				String actionName = eForm.getActionName();
                 
				logger.debug("formName:-"+formName);
				logger.debug("actionName:-"+actionName);
				String pageName = request.getParameter("pageName");
				
				//String proTxn = request.getParameter("partyId1");
				String proTxn=(String)session.getAttribute("propTxnNo");
				if(proTxn!=null) {
						 
						logger.debug("propertyTxnId:-"+proTxn);
						 
						RegCompletDTO bDTO = regcompbo
						.getSelectedPropertyDetails(proTxn);
						logger.debug("bDTO.getPropertyTypeId():-"
								+bDTO.getPropertyTypeId());
						String propertyType = bDTO.getPropertyTypeId();
						String[] pType = propertyType!=null ?
										propertyType.split("~"):null;
						 
						if(valueBo.getPropertyID(
								RegCompConstant.BUILDING_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_BUILDING");
							eForm.setMapBuildingDetails(bDTO.getMapBuilding());
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.PLOT_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_PLOT");
							 
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.AGRICULTURELAND_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_AGRI_LAND");
							 
						}	
						eForm.setRegcompletDto(bDTO);
						forwardJsp =  
							 RegCompConstant.VALUATION_VIEW_POPUP_PAGE;
						session.removeAttribute("propertyTxn"); 
						// Added By Aruna
						return mapping.findForward(forwardJsp);
				}
				if (pageName != null && "Submit".equals(pageName)) {
					
					// Added By Aruna
					sessionRegTxnId = 
						(String)session.getAttribute("sessionRegTxnId");
					// Added By Aruna
					if(!regcompbo.shouldPropertyBeAdded(sessionRegTxnId))
					{
						forwardJsp = "nextPage";
						return mapping.findForward(forwardJsp);
					}
					ArrayList retPropList = regcompbo
							.getPropertyTxnIdList(sessionRegTxnId);
					logger.debug(retPropList);
					if(retPropList!=null && retPropList.size()>0)
					{
					eForm.getRegcompletDto().setPropertyList(retPropList);
					forwardJsp = "propListView";
					return mapping.findForward(forwardJsp);
					}else{
							ArrayList distlist = bd.getDistrict();
							dto.setDistList(distlist);
		
							if (refdto.getPropertyTxnNo() != null
									&& !refdto.getPropertyTxnNo().equals("")) {
		
								session.setAttribute("sessionPropertyTxnId",
										refdto.getPropertyTxnNo());
								
								//ArrayList rsList = (ArrayList) 
								//			session.getAttribute("propertyList");
								//if (rsList == null || rsList.size() == 0) {
									RegCompletDTO rsdto = 
										regcompbo.getSelectedPropertyDetails(refdto
											.getPropertyTxnNo());
									distId = rsdto.getDistId();// + "~" +
									// rsdto.getDistName();
									tehsilId = rsdto.getTehsilId();// + "~" +
									// rsdto.getTehsilName();
									areaTypeId = rsdto.getAreaTypeId();// + "~" +
									// rsdto.getAreaTypeName();
									govMunicipalId = rsdto.getGovmunciplId();// + "~" +
									// rsdto.getGovmunciplName();
									wardOrPatwariId = rsdto.getWardpatwarId();// + "~" +
									// rsdto.getWardpatwarName();
		
									mohallaOrVillageId = rsdto.getMohallaId();// + "~" +
									// rsdto.getMohallaName();
									if(wardOrPatwariId!=null) {
										if (wardOrPatwariId.length() > 0) {
											char wardOrPatwariIdtmp = wardOrPatwariId.charAt(0);
											if (wardOrPatwariIdtmp == 'W') {
												wardOrPatwariName = "W";
												mohallaOrVillageName = "M";
												wardId = wardOrPatwariId;
												mohallaId = mohallaOrVillageId;
											} else {
												wardOrPatwariName = "P";
												mohallaOrVillageName = "V";
												patwariId = wardOrPatwariId;
												villageId = mohallaOrVillageId;
											}
										}
									}
									logger.debug("rsdto.getVikasId()-<>"
											+ rsdto.getVikasId());
									propertyTypeId = rsdto.getPropertyTypeId();// + "~" +
									logger.debug("rsdto.getVikasId()--<>"
											+ rsdto.getVikasId());
									dto.setVikasId(rsdto.getVikasId());
									dto.setRicircle(rsdto.getRicircle());
									dto.setLayoutdet(rsdto.getLayoutdet());
									dto.setKhasaraNum(rsdto.getKhasaraNum());
									dto.setNazoolstNum(rsdto.getNazoolstNum());
									dto.setRinpustikaNum(rsdto.getRinpustikaNum());
									dto.setAddress(rsdto.getAddress());
									dto.setNorth(rsdto.getNorth());
									dto.setSouth(rsdto.getSouth());
									dto.setEast(rsdto.getEast());
									dto.setWest(rsdto.getWest());
									//dto.setSubclauseString(rsdto.getSubclauseString());
									//dto.setFloorValuesList(rsdto.getFloorValuesList());
									//dto.setSelectedFloor(rsdto.getSelectedFloor());
									//eForm.setMapBuilding(rsdto.getFloorValuesList());
									
									
								//}
								eForm.getRegcompletDto().setDistId(distId);
		
							} else {
								distId = (String) eForm.getRegcompletDto().getDistId();
								proDetailsdto = eForm.getRegcompletDto();
								// Added By Aruna
								eForm.getRegcompletDto().setRegisterList(getRegisterList());
							}	
							
							 forwardJsp = "nextdisp";
						}
				}
				
				if(RegCompConstant.REGCOM_PROPERTY_SEARCH.equals(formName)){
					if(RegCompConstant.REGCOMP_DISTRICT_ACTION.
								equals(actionName)) {
						if(eForm.getRegcompletDto().getDistId()!=null) {
							logger.debug("dto.getDistId():-"
									+eForm.getRegcompletDto());
							String[] param =
								eForm.getRegcompletDto().getDistId().split("~");					
							if(param!=null && param.length>0) {	
								eForm.setTehsilList(valueBo.getTehsil(param[0]));	
								logger.debug("inside");
								forwardJsp = "forward";
							}
						}
					}
					if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
								eForm.getRegcompletDto());
						session.removeAttribute(
								RegCompConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(
								RegCompConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("poaAdjDto");
						session.removeAttribute("sessionRegTxnId");
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute("propTxnNo");
						session.removeAttribute(
								CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						forwardJsp = "welcome";
					}
					if(RegCompConstant.PREVIOUS_ACTION.equals(actionName)) {
						forwardJsp = "nextdisp";
					}
					if(RegCompConstant.REGCOMP_WARDPATWARI_ACTION.
							equals(actionName)) {
						String thesilId = 
							eForm.getRegcompletDto().getTehsilId();
						String[] paramTehsil  = new String[0];
						paramTehsil = thesilId.split("~");
							
						logger.debug("thesilId:-"+thesilId+":"+paramTehsil[0]);
						String paramTeh = "";
						if(paramTehsil !=null && paramTehsil.length == 2) {
							paramTeh = paramTehsil[0];
						}
						 String status = 
							 eForm.getRegcompletDto().getWardpatwarId();
						 
						 eForm.getRegcompletDto().setWardStatus(status);
						 String[] paramArea = 
							 eForm.getRegcompletDto().
							 getAreaTypeId().split("~");
						 
						 String paramA = "";
						 if(paramArea != null & paramArea.length == 2 ) {
							paramA = paramArea[0];
						 }
						 eForm.setWardList(
									 valueBo.getWard(paramTeh, paramA, status));
						 forwardJsp = "forward";
					}
					if(RegCompConstant.REGCOMP_MOHALLA_ACTION.
							equals(actionName)) {
						String[] paramWard = 
							eForm.getRegcompletDto().getWardId().split("~");
						String mohId = "";
						if(paramWard!=null && 
								paramWard.length == 2) {
							mohId = paramWard[0];
						}
						logger.debug("mohalla Id:-"+mohId+":"+paramWard);
						eForm.setMahallaList(valueBo.getMahalla(mohId));
						
						 forwardJsp = "forward";
					}
					if(RegCompConstant.NEXT_ACTION.
							equals(actionName)) {
						String[] property = 
							eForm.getRegcompletDto().
								getPropertyTypeId().split("~");
						eForm.getPropertyDTO().setAreaId(
									eForm.getRegcompletDto().getAreaTypeId());
						if(property!=null && property.length == 2){
							String propertyId = property[0];
							eForm.setPlotResidentList(
									valueBo.getUsePlot(propertyId));
							//eForm.setSubClauseList(new ArrayList());
							if(eForm.getSubClauseList()==null || eForm.getSubClauseList().size()==0)
							{
							eForm.setSubClauseList(new ArrayList());
							}else{
								RegCompletDTO regDTO = eForm.getRegcompletDto();
								PropertyValuationDTO dtoValuation 
								= eForm.getPropertyValuation();
								dtoValuation.setDistrictID(regDTO.getDistId());
								dtoValuation.setTehsilID(regDTO.getTehsilId());
								dtoValuation.setWardId(regDTO.getWardId());
								dtoValuation.setMahallaId(regDTO.getMohallaId());
								dtoValuation.setPropertyTypeID(
											regDTO.getPropertyTypeId());
				
								valueBo.getSubClause(dtoValuation);
							}
							eForm.setCeilingList(new ArrayList());
							eForm.setFloorList(new ArrayList());
							session.setAttribute(
									RegCompConstant.PROPERTY_DTO_SESSION,
									eForm);
							logger.debug("propertyId:-"+propertyId);
							
							count = 0;
							eForm.getRegcompletDto().setAddMoreCounter(count);
							
							if(propertyId.equals(
									valueBo.getPropertyID(
									RegCompConstant.PLOT_ID))) {
							//Plot Page
								session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									RegCompConstant.PROPERTY_TYPE_PLOT);
								forwardJsp 
									= RegCompConstant.VALUATION_PLOT_PAGE;
								
							}
							if(propertyId.equals(
									valueBo.getPropertyID(
									RegCompConstant.BUILDING_ID))) {
							//Building Page	
								session.setAttribute(
										RegCompConstant.PROPERTY_TYPE_SESSION, 
										RegCompConstant.PROPERTY_TYPE_BUILDING);
								
								session.setAttribute(
										RegCompConstant.HASH_SESSION, null);
								eForm.setMapBuilding(new HashMap());
								eForm.setListBuilding(new ArrayList());
								bol = false;
								// forward to building page
								forwardJsp 
									= RegCompConstant.VALUATION_BUILDING_PAGE;
							
								logger.debug("Forwarding to Building page");
							}
							if(propertyId.equals(
									valueBo.getPropertyID(
									RegCompConstant.AGRICULTURELAND_ID))) {
								
								session.setAttribute(
										RegCompConstant.PROPERTY_TYPE_SESSION, 
										RegCompConstant.PROPERTY_TYPE_AGRI_LAND);
								eForm.setLandTypeList(valueBo.getLandType());
							 
								
								// forward to agricultureland page
								logger.debug("Forwarding to Agriculture Land"
									+ " page");
								forwardJsp 
								= RegCompConstant.VALUATION_AGRI_PAGE;
							
							//Agri Page	
							}
							
						}
					}
				}
				//
				//Start Plot Valuation
				if(RegCompConstant.VALUATION_PLOT_PAGE.equals(formName)){
					saveToken(request);
					if(RegCompConstant.RESET_ACTION.equals(actionName)) {
						
						PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
						dtoProperty.setTotalSqMeter(0.0);
						dtoProperty.setConsiderAmt(0.0);
						dtoProperty.setUsePlotId("");
						dtoProperty.setHdnSubClause("");
						eForm.setSubClauseList( new ArrayList());
						forwardJsp = RegCompConstant.VALUATION_PLOT_PAGE;
					}
					if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
								eForm.getRegcompletDto());
						session.removeAttribute(
								RegCompConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(
								RegCompConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("poaAdjDto");
						session.removeAttribute("sessionRegTxnId");
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute("propTxnNo");
						session.removeAttribute(
								CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						forwardJsp = "welcome";
					}
					 
					if(RegCompConstant.PREVIOUS_ACTION.
							equals(actionName)) {
						logger.debug("Inside previous");
						 forwardJsp = "forward";
					}
					if(RegCompConstant.PLOT_USAGE_ACTION.equals(actionName)) {
						RegCompletDTO regDTO = eForm.getRegcompletDto();
						PropertyValuationDTO dtoValuation 
										= eForm.getPropertyValuation();
						dtoValuation.setDistrictID(regDTO.getDistId());
						dtoValuation.setTehsilID(regDTO.getTehsilId());
						dtoValuation.setWardId(regDTO.getWardId());
						dtoValuation.setMahallaId(regDTO.getMohallaId());
						dtoValuation.setPropertyTypeID(
									regDTO.getPropertyTypeId());
						
						logger.debug("regDTO.getUsePlotId():-"
									+dtoValuation.getUsePlotId());
						 
						eForm.setSubClauseList(
								valueBo.getSubClause(dtoValuation));
						
						forwardJsp 
						= RegCompConstant.VALUATION_PLOT_PAGE;
					}
					if(RegCompConstant.PLOT_SUBMIT_ACTION.equals(actionName)) {
						RegCompletionForm regForm = 
							     (RegCompletionForm) session.getAttribute(
							    		 RegCompConstant.PROPERTY_DTO_SESSION);
						PropertyValuationDTO dtoValuation
											= eForm.getPropertyDTO();
						PropertyValuationDTO useDTO = regForm.getPropertyDTO();
						RegCompletDTO regDTO = regForm.getRegcompletDto();
						
						useDTO.setAreaId(regDTO.getAreaTypeId());
						useDTO.setMunicipalBodyID(regDTO.getGovmunciplId());
						
						useDTO.setHdnSubClause(dtoValuation.getHdnSubClause());
						
						
						logger.debug("Use Plot:-"+dtoValuation.getUsePlotId());
						logger.debug("regDTO.getPropertyTypeId():-"
								+regDTO.getPropertyTypeId());
						
						
						logger.debug("regDTO.getGovmunciplId():-"
									+regDTO.getGovmunciplId());
						
						useDTO.setUsePlotId(dtoValuation.getUsePlotId());
						System.out.println("hello i "+dtoValuation.getUsePlotId()); 
						logger.debug(regForm.getHdnSubClauseName());
						useDTO.setHdnSubClauseName(
									regForm.getHdnSubClauseName());
						
						
						String[] marketValue 
									= valueBo.getMarketValue(useDTO);
						
						if(marketValue !=null) {
							String value = 
								common.getCurrencyFormat(
										Double.parseDouble(marketValue[0]));
							if(value!=null) {
								useDTO.setMarketValue(
										Double.parseDouble(value));
							}
							logger.debug(marketValue[1]+":"+marketValue[2]);
						}
						
						
						PropertyValuationDTO sessionDTO 
							= valueBo.getPropertyValuationDTO(useDTO);
						regForm.setPropertyValuation(sessionDTO);
						
						String[] subclauseary = 
							useDTO.getHdnSubClauseName() == null ? null :
							useDTO.getHdnSubClauseName().split(",");
						String property = useDTO.getPropertyTypeID();
						
						 
						HashMap mapsubclause = eForm.getMapBuilding();
						
						Iterator Itr = mapsubclause.entrySet().iterator();
						while(Itr.hasNext()) {
							Map.Entry mee = (Map.Entry) Itr.next();
							PropertyValuationDTO mapDTO = 
								(PropertyValuationDTO) mee.getValue();
							logger.debug("display action get KKK:-"+mee.getKey()
									+":"+mapDTO.getHdnSubClauseName());
						}
						
						logger.debug("subclause name:-"+useDTO.getHdnSubClauseName
								());
						
						//if(property.length == 2) {
							logger.debug("Property Type:-"+property);
						if(pr.getValue(CommonConstant.BUILDING_ID).
								      equals(property)) {
								HashMap selectMap = valueBo.getSubClauseMap(
										mapsubclause, 
										subclauseary, 
										useDTO.getTypeFloorID());
								
								Iterator I = selectMap.entrySet().iterator();
								while(I.hasNext()) {
									 Map.Entry me = (Map.Entry) I.next();
									 logger.debug("Inside Action getKey :-"
											 +me.getKey());
									 ArrayList list = (ArrayList)me.getValue();
									 for(int i = 0; i <list.size() ;i++) {
										 PropertyValuationDTO dt = 
											 (PropertyValuationDTO) list.get(i);
										 logger.debug("Inside Action :-"
												 +dt.getSubClause());
									 }
								}
								eForm.setSelectedMap( selectMap);
						}else {
							eForm.setSelectedSubclause(
										valueBo.getSubClauseList(subclauseary));
						}
						//}
						
						session.setAttribute(
					    	RegCompConstant.PROPERTY_DTO_SESSION,regForm);						
						logger.debug("Market value:-"+useDTO.getMarketValue());
						forwardJsp = 
								RegCompConstant.DISPLAY_VALUATION_PAGE;
						//forwardJsp = "beforeSave";
					}
				}
				
				//End Plot Valutaion
				
				
				//Start Building Valuation
				if(RegCompConstant.VALUATION_BUILDING_PAGE.equals(formName)){
					saveToken(request);
					if(RegCompConstant.USAGE_BUILDING_ACTION.equals(actionName)) {
						PropertyValuationDTO dtoValuation 
										= eForm.getPropertyDTO();
						String[] usePlot 
									= dtoValuation.getUsePlotId().split("~");
						if(usePlot != null && usePlot.length == 3) {
							if(usePlot[2] != null && usePlot[2].trim() != "") {
								dtoValuation.setLblUsage(usePlot[2]);
								eForm.setCeilingList(
										valueBo.getL2Type(usePlot[0]));
								eForm.setFloorList(
											valueBo.getFloorType(usePlot[0]));
								eForm.setPropertyDTO(dtoValuation);
							}
						}
						forwardJsp = RegCompConstant.VALUATION_BUILDING_PAGE;
					}
					
					if(RegCompConstant.ADD_MORE_ACTION.equals(actionName)) {
						count = count + 1;
						
						PropertyValuationDTO mapdto = eForm.getPropertyDTO();
						
						map = eForm.getMapBuilding();
						
						PropertyValuationDTO hDTO = 
									new PropertyValuationDTO(); 
						mapdto.setAddMoreCounter(count);
						
						
						String[] marketValue 
										= valueBo.getFloorWiseMarketValue(mapdto);
						String[] floor = mapdto.getTypeFloorID() == null  ? null:
											mapdto.getTypeFloorID().split("~");
						String[] usagePlot = mapdto.getUsePlotId()== null ? null :
											mapdto.getUsePlotId().split("~");
						hDTO.setConsiderAmt(mapdto.getConsiderAmt());
						hDTO.setConstructedArea(mapdto.getConstructedArea());
						hDTO.setTotalSqMeter(mapdto.getTotalSqMeter());
						
						String[] ceilingType = mapdto.getCeilingTypeId() == null ? null :
												mapdto.getCeilingTypeId().split("~");
						
						
						if(ceilingType !=null && ceilingType.length == 3) {
							hDTO.setCeilingType(ceilingType[1]);
							hDTO.setCeilingTypeId(ceilingType[0]);
							mapdto.setCeilingTypeId(ceilingType[0]);
							
						}
						//hDTO.setAreaId(mapdto.getAreaId());
						hDTO.setMunicipalBodyID(
								eForm.getRegcompletDto().getGovmunciplId());
						
						
						logger.debug("SubClause Name:-"+eForm.getHdnSubClauseName());
						hDTO.setHdnSubClauseName(eForm.getHdnSubClauseName());
						hDTO.setHdnSubClause(eForm.getHdnExtSubClause());
						
						String key = null;
						
						if(floor != null && floor.length == 3) {
						    key = floor[0];
						    hDTO.setTypeFloorDesc(floor[2]);
						    hDTO.setFloorID(key);
						    mapdto.setTypeFloorDesc(floor[2]);
						    mapdto.setFloorID(key);
							logger.debug("Count:-"+count+":"+key);
						}
						
						if(usagePlot !=null && usagePlot.length == 3) {
							mapdto.setUsageBuilding(usagePlot[1]);
							hDTO.setUsageBuilding(usagePlot[1]);
							hDTO.setUsePlotId(usagePlot[0]);
							mapdto.setUsePlotId(usagePlot[0]);
						}
						//logger.debug("Floor Details:-"+floor[2]+":"+usagePlot[1]+":"+marketValue[0]);
						 
							//Double.parseDouble("1000000.00");
						if(marketValue!=null && marketValue.length == 3) {
					        double mktValue = marketValue[0] == null ? 0.0 : 
					        					Double.parseDouble(marketValue[0]);
					        
							mapdto.setMarketValue(mktValue);
							hDTO.setMarketValue(mktValue);
							mapdto.setMktValue(common.getCurrencyFormat(mktValue));
							hDTO.setMktValue(common.getCurrencyFormat(mktValue));
						}
						
						if(!map.containsKey(key) ) {
							bol = true;
							
						}else {
							Iterator I = map.entrySet().iterator();
							while(I.hasNext()) {
								Map.Entry me = (Map.Entry) I.next();
								String tempKey = (String)me.getKey();
								
								if(tempKey != null && tempKey.length() >=3) {
									 String str = tempKey.substring(0,3);
									 if(RegCompConstant.THIRD_FLOOR.equals(str) ||
									    RegCompConstant.FOURTH_FLOOR.equals(str)) {
						    			floorCount ++;
										key = key + floorCount;
										logger.debug("Key :-"+key);
										//map.put(key, mapdto);
										bol = true;
										//eForm.setMapBuilding(map);
										break;
									}
								}
								if(tempKey != null && tempKey.equals(key)) {
										logger.debug("This floor calculation is already done.");
										break;
								}
							}
						}
						logger.debug("map.containsValue(mapdto):"+map.containsValue(mapdto));
						HashMap mapBuilding = new HashMap();
						if(bol ) {
							//mapdto.setFloorID(key);
							logger.debug("key:-"+bol+":"+mapdto.getFloorID());
							map.put(key, hDTO);
							
							mapBuilding.put(key, hDTO);
							
						}	
						eForm.setMapBuilding(map);
						eForm.setMapBuildingDetails(map);

						//dto.setUsePlotId("");
						 
					 
						PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
						dtoProperty.setTotalSqMeter(0.0);
						dtoProperty.setConsiderAmt(0.0);
						dtoProperty.setUsePlotId("");
						eForm.setCeilingList(new ArrayList());
						dtoProperty.setConstructedArea(0.0);
						eForm.setFloorList(new ArrayList());
						dtoProperty.setHdnSubClause("");
						dtoProperty.setTypeFloorID("");
						dtoProperty.setCeilingTypeId("");
						eForm.setSubClauseList( new ArrayList());
						
						//eForm.setPropertyDTO(dto);
						logger.debug("Add More pressed:-"+count);
						forwardJsp = RegCompConstant.VALUATION_BUILDING_PAGE;
					}
					if(RegCompConstant.DELETE_MORE_ACTION.equals(actionName)) { 
						
						PropertyValuationDTO dtoValuation 
										= eForm.getPropertyDTO();
						String[] floorID 
									= dtoValuation.getHdnDeleteFloorID().
									        split(",");
						count = count - floorID.length;
						dtoValuation.setAddMoreCounter(count);
						
						for(int i = 0 ;i < floorID.length ;i++) {
							logger.debug(floorID[i]+" is removed...");
							map.remove(floorID[i]);
						}
						eForm.setMapBuilding(map);
						forwardJsp = RegCompConstant.VALUATION_BUILDING_PAGE;
					}
					if(RegCompConstant.FLOOR_ACTION.equals(actionName)) {
						RegCompletDTO regDTO = eForm.getRegcompletDto();
						PropertyValuationDTO dtoValuation 
										= eForm.getPropertyValuation();
						dtoValuation.setDistrictID(regDTO.getDistId());
						dtoValuation.setTehsilID(regDTO.getTehsilId());
						dtoValuation.setWardId(regDTO.getWardId());
						dtoValuation.setMahallaId(regDTO.getMohallaId());
						dtoValuation.setPropertyTypeID(
									regDTO.getPropertyTypeId());
						
						logger.debug("regDTO.getUsePlotId():-"
									+dtoValuation.getUsePlotId());
						
						eForm.setSubClauseList(
									valueBo.getSubClause(dtoValuation));
						forwardJsp = RegCompConstant.VALUATION_BUILDING_PAGE;
					}
					if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
								eForm.getRegcompletDto());
						session.removeAttribute(
								RegCompConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(
								RegCompConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("poaAdjDto");
						session.removeAttribute("sessionRegTxnId");
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute("propTxnNo");
						session.removeAttribute(
								CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						forwardJsp = "welcome";
					}
					if(RegCompConstant.PREVIOUS_ACTION.
							equals(actionName)) {
						logger.debug("Inside previous");
						 forwardJsp = "forward";
					}
					if(RegCompConstant.RESET_ACTION.equals(actionName)) {
						
						PropertyValuationDTO dtoProperty 
									= eForm.getPropertyDTO();
						dtoProperty.setTotalSqMeter(0.0);
						dtoProperty.setConsiderAmt(0.0);
						dtoProperty.setUsePlotId("");
						eForm.setCeilingList(new ArrayList());
						dtoProperty.setConstructedArea(0.0);
						eForm.setFloorList(new ArrayList());
						dtoProperty.setHdnSubClause("");
						dtoProperty.setTypeFloorID("");
						dtoProperty.setCeilingTypeId("");
						eForm.setSubClauseList( new ArrayList());
						forwardJsp = RegCompConstant.VALUATION_BUILDING_PAGE;
					}
					if(RegCompConstant.BUILDING_SUBMIT_ACTION.
								equals(actionName)) {
						
						
						RegCompletionForm rForm = 
						     (RegCompletionForm) session.getAttribute(
						    		 CommonConstant.PROPERTY_DTO_SESSION);
						PropertyValuationDTO useDTO = rForm.getPropertyDTO();
						
						PropertyValuationDTO valuedto = eForm.getPropertyDTO();
						useDTO.setHdnExtSubClause(eForm.getHdnExtSubClause());
						
						logger.debug("Use Plot:-"+valuedto.getUsePlotId());
						//useDTO.setUsePlotId(dto.getUsePlotId());
						logger.debug(valuedto.getHdnSubClause());
						useDTO.setAreaId(valuedto.getAreaId());
						useDTO.setMunicipalBodyID(
								rForm.getRegcompletDto().getGovmunciplId());
						
						useDTO.setHdnSubClauseName(eForm.getHdnSubClauseName());
						logger.debug("eForm.getHdnSubClauseName():-"+
									eForm.getHdnSubClauseName());
						String[] marketValue = valueBo.getFloorWiseMarketValue(useDTO);
						HashMap map = eForm.getMapBuilding();
						String value ="0.0";
						
					   if(marketValue !=null) {
						  value = 
							common.getCurrencyFormat( 
									Double.parseDouble(marketValue[0] == null  
											? "0.0" : marketValue[0]));
						/*if(value!=null) {
							useDTO.setMarketValue(
									Double.parseDouble(value));
						}*/
					   }	
						logger.debug("Ceiling Type:-"
									+valuedto.getCeilingTypeId());
						String[] ceilingType = 	
							valuedto.getCeilingTypeId() == null ? null :
							valuedto.getCeilingTypeId().split("~");
	
	
						if(ceilingType !=null && ceilingType.length == 3) {
							useDTO.setCeilingType(ceilingType[1]);
						}
						logger.debug("dto.getTypeFloorID():-"
											+valuedto.getTypeFloorID());
						String[] floor = 
										valuedto.getTypeFloorID() == null 
										? null :
									    valuedto.getTypeFloorID().split("~");
						String[] usagePlot = valuedto.getUsePlotId() == null 
										? null :
										valuedto.getUsePlotId().split("~");
						
						String key = null;
						
						if(floor != null && floor.length == 3) {
						    key = floor[0];
						    useDTO.setTypeFloorDesc(floor[2]);
						    useDTO.setFloorID(key);
							logger.debug("Count:-"+count+":"+key);
						}
						
						if(usagePlot !=null && usagePlot.length == 3) {
							 
							useDTO.setUsageBuilding(usagePlot[1]);
						}	
						
						 
					 /*Iterator I = map.entrySet().iterator();
					 double sumMarketValue=0.0;
					 while(I.hasNext()) {
						 Map.Entry me = (Map.Entry) I.next();
						 PropertyValuationDTO hDTO = 
							 	(PropertyValuationDTO) me.getValue();
						 sumMarketValue = 
							 	sumMarketValue 
							 	+ hDTO.getMarketValue();
						 
					 }
					 sumMarketValue = sumMarketValue 
					 			+ Double.parseDouble(value);*/
					 useDTO.setMktValue(common.getCurrencyFormat(Double.parseDouble(value)));
					
					
					 
					 useDTO.setMarketValue(Double.parseDouble(value));
					 
					//}
					 
					 String[] subclauseary = useDTO.getHdnSubClauseName() == null ? null :
							useDTO.getHdnSubClauseName().split(",");
					HashMap hMap = eForm.getMapBuilding();
					
					String[] property = useDTO.getPropertyTypeID().split("~");
					
					if(property.length == 2) {
						logger.debug("Property Type:-"+property[1]);
						if(property[0].equals(
								pr.getValue(
									CommonConstant.BUILDING_ID))) {
							HashMap selectMap = valueBo.getSubClauseMap(
									hMap, 
									subclauseary, 
									useDTO.getTypeFloorID());
							
							Iterator I2 = selectMap.entrySet().iterator();
							while(I2.hasNext()) {
								 Map.Entry me = (Map.Entry) I2.next();
								 logger.debug("Inside Action getKey :-"+me.getKey());
								 ArrayList list = (ArrayList)me.getValue();
								 for(int i = 0; i <list.size() ;i++) {
									 PropertyValuationDTO dt = 
										 (PropertyValuationDTO) list.get(i);
									 logger.debug("Inside Action :-"+dt.getSubClause());
								 }
							}
							eForm.setSelectedMap( selectMap);
						} 
					}
					hMap.put("key", useDTO);
					eForm.setMapBuildingDetails(hMap);
					eForm.setPropertyDTO(useDTO);
					
					
					PropertyValuationDTO sessionDTO 
							= valueBo.getPropertyValuationDTO(useDTO);
					
					
					eForm.setPropertyValuation(sessionDTO);
					
					session.setAttribute(
							CommonConstant.PROPERTY_DTO_SESSION, 
							eForm);
					
					session.setAttribute(
							CommonConstant.HASHMAP_DTO_SESSION, map);
					
					logger.debug("Market value:-"+useDTO.getMarketValue());	
						
						forwardJsp = 
								RegCompConstant.DISPLAY_VALUATION_PAGE;
					}
				}	
				
				//End Building Valutaion
				
				//Start Agricultural Valuation
				
				if(RegCompConstant.VALUATION_AGRI_PAGE.equals(formName)){
					
					saveToken(request);
					if(RegCompConstant.AGRI_USAGE_ACTION.equals(actionName)) {
						RegCompletDTO regDTO = eForm.getRegcompletDto();
						PropertyValuationDTO dtoValuation 
										= eForm.getPropertyValuation();
						dtoValuation.setDistrictID(regDTO.getDistId());
						dtoValuation.setTehsilID(regDTO.getTehsilId());
						dtoValuation.setWardId(regDTO.getWardId());
						dtoValuation.setMahallaId(regDTO.getMohallaId());
						dtoValuation.setPropertyTypeID(
									regDTO.getPropertyTypeId());
						
						logger.debug("regDTO.getUsePlotId():-"
									+dtoValuation.getUsePlotId());
						
						eForm.setSubClauseList(
								valueBo.getSubClause(dtoValuation));
						forwardJsp = RegCompConstant.VALUATION_AGRI_PAGE;
					}
					if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
								eForm.getRegcompletDto());
						session.removeAttribute(
								RegCompConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(
								RegCompConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("poaAdjDto");
						session.removeAttribute("sessionRegTxnId");
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute("propTxnNo");
						session.removeAttribute(
								CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						forwardJsp = "welcome";
					}
					if(RegCompConstant.RESET_ACTION.equals(actionName)) {
						
						PropertyValuationDTO dtoProperty = eForm.getPropertyDTO();
						dtoProperty.setTotalSqMeter(0.0);
						dtoProperty.setConsiderAmt(0.0);
						dtoProperty.setUsePlotId("");
						 
						dtoProperty.setLandMeterId("");
						dtoProperty.setHdnSubClause("");
						
						eForm.setSubClauseList( new ArrayList());
						forwardJsp = RegCompConstant.VALUATION_AGRI_PAGE;
					}
					if(RegCompConstant.PLOT_SUBMIT_ACTION.equals(actionName)) {
						RegCompletionForm regForm = 
						     (RegCompletionForm) session.getAttribute(
						    		 RegCompConstant.PROPERTY_DTO_SESSION);
						PropertyValuationDTO dtoValuation
											= eForm.getPropertyDTO();
						PropertyValuationDTO useDTO = regForm.getPropertyDTO();
						RegCompletDTO regDTO = regForm.getRegcompletDto();
						
						useDTO.setAreaId(regDTO.getAreaTypeId());
						useDTO.setMunicipalBodyID(regDTO.getGovmunciplId());
						
						useDTO.setHdnSubClause(dtoValuation.getHdnSubClause());
						
						
						
						logger.debug("Use Plot:-"+dtoValuation.getUsePlotId());
						logger.debug("regDTO.getPropertyTypeId():-"
								+regDTO.getPropertyTypeId());					
						
						logger.debug("regDTO.getGovmunciplId():-"
									+regDTO.getGovmunciplId());
						
						useDTO.setUsePlotId(dtoValuation.getUsePlotId());
						logger.debug(regForm.getHdnSubClauseName());
						useDTO.setHdnSubClauseName(
									regForm.getHdnSubClauseName());
						
						
						String[] marketValue 
									= valueBo.getMarketValue(useDTO);
						
						if(marketValue !=null) {
							String value = 
								common.getCurrencyFormat(
										Double.parseDouble(marketValue[0]));
							if(value!=null) {
								useDTO.setMarketValue(
										Double.parseDouble(value));
							}
							logger.debug(marketValue[1]+":"+marketValue[2]);
						}
						
						
						useDTO.setConsiderAmt(dtoValuation.getConsiderAmt());
						
						PropertyValuationDTO sessionDTO 
							= valueBo.getPropertyValuationDTO(useDTO);
						regForm.setPropertyValuation(sessionDTO);
						
						String[] subclauseary = 
							useDTO.getHdnSubClauseName() == null ? null :
							useDTO.getHdnSubClauseName().split(",");
						String property = useDTO.getPropertyTypeID();						 
						HashMap mapsubclause = eForm.getMapBuilding();
						
						Iterator Itr = mapsubclause.entrySet().iterator();
						while(Itr.hasNext()) {
							Map.Entry mee = (Map.Entry) Itr.next();
							PropertyValuationDTO mapDTO = 
								(PropertyValuationDTO) mee.getValue();
							logger.debug("display action get KKK:-"+mee.getKey()
									+":"+mapDTO.getHdnSubClauseName());
						}
						
						logger.debug("out side the plot option");
						logger.debug("subclause name:-"+useDTO.getHdnSubClauseName
								());
						
						//if(property.length == 2) {
							logger.debug("Property Type:-"+property);
							if(property.equals(
									pr.getValue(
										CommonConstant.BUILDING_ID))) {
								HashMap selectMap = valueBo.getSubClauseMap(
										mapsubclause, 
										subclauseary, 
										useDTO.getTypeFloorID());
								
								Iterator I = selectMap.entrySet().iterator();
								while(I.hasNext()) {
									 Map.Entry me = (Map.Entry) I.next();
									 logger.debug("Inside Action getKey :-"
											 +me.getKey());
									 ArrayList list = (ArrayList)me.getValue();
									 for(int i = 0; i <list.size() ;i++) {
										 PropertyValuationDTO dt = 
											 (PropertyValuationDTO) list.get(i);
										 logger.debug("Inside Action :-"
												 +dt.getSubClause());
									 }
								}
								eForm.setSelectedMap( selectMap);
							}else {
								eForm.setSelectedSubclause(
										valueBo.getSubClauseList(subclauseary));
							}
						//}
						
						regForm.getPropertyDTO().setMarketValue(
										useDTO.getMarketValue());
						regForm.setPropertyDTO(useDTO);						
						regForm.setRegcompletDto(regDTO);
						session.setAttribute(
					    	RegCompConstant.PROPERTY_DTO_SESSION,regForm);
						
						logger.debug("Market value:-"+useDTO.getMarketValue());
						forwardJsp = 
								RegCompConstant.DISPLAY_VALUATION_PAGE;
					}
					if(RegCompConstant.PREVIOUS_ACTION.
									equals(actionName)) {
						logger.debug("Inside previous");
						 forwardJsp = "forward";
					}
					
				}
				
				//End Agricultural Valuation
				
				//Start Inserting data
				if(RegCompConstant.DISPLAY_VALUATION_PAGE.equals(formName)){
					if(RegCompConstant.PREVIOUS_ACTION.equals(actionName)) {
						
						String propertyType = 
							(String)session.getAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION);
						logger.debug("Inside previous");
						if(RegCompConstant.PROPERTY_TYPE_PLOT.
								equals(propertyType)) {								
							// Added By Aruna
							RegCompletionForm regForm = 
							     (RegCompletionForm) session.getAttribute(
							    		 RegCompConstant.PROPERTY_DTO_SESSION);
							PropertyValuationDTO useDTO = regForm.getPropertyDTO();
							eForm.getPropertyDTO().setUsePlotId(useDTO.getUsePlotIDForDisplay());
							forwardJsp 
							= RegCompConstant.VALUATION_PLOT_PAGE;
						}
						if(RegCompConstant.PROPERTY_TYPE_AGRI_LAND.
								equals(propertyType)) {
							// Added By Aruna
							RegCompletionForm regForm = 
							     (RegCompletionForm) session.getAttribute(
							    		 RegCompConstant.PROPERTY_DTO_SESSION);
							PropertyValuationDTO useDTO = regForm.getPropertyDTO();
							eForm.getPropertyDTO().setUsePlotId(useDTO.getUsePlotIDForDisplay());
							forwardJsp 
							= RegCompConstant.VALUATION_AGRI_PAGE;
						} 
						if(RegCompConstant.PROPERTY_TYPE_BUILDING.
								equals(propertyType)) {
							forwardJsp 
							= RegCompConstant.VALUATION_BUILDING_PAGE;
						} 
					}
					if(RegCompConstant.STORE_ACTION.equals(actionName)) {
						
						logger.debug("isTokenValid(request):-"+ isTokenValid(request));
						
						if(isTokenValid(request)) {
							String propertyTxn = 
								(String)session.getAttribute("propertyTxn");
						
							logger.debug("propertyTxn:-"+propertyTxn);
						
							if(propertyTxn == null) {
						
								RegCompBD rbd = new RegCompBD();
								RegCompletionForm rForm = (RegCompletionForm)
										session.getAttribute(
										RegCompConstant.PROPERTY_DTO_SESSION);
								RegCompletDTO regDTO = rForm.getRegcompletDto();
								// Added By Aruna
								regDTO.setOldRegNo(sessionRegTxnId);
								PropertyValuationDTO propertyDTO = 
										rForm.getPropertyValuation();
								
								logger.debug("rForm.getHdnExtSubClause():-"
											+rForm.getHdnExtSubClause());
								propertyDTO.setHdnExtSubClause(
										rForm.getHdnExtSubClause());
								regDTO.setMapBuilding(rForm.getMapBuildingDetails());
								
								String propTxnNo = 
										rbd.storePropertyDetails(regDTO, propertyDTO);
							
								if (session.getAttribute("suppleDetails") != null) {
	
									/*session.setAttribute("propTxnNo",
										propTxnNo);*/
									forwardJsp = "suppleForwardPath";
								} else {
	
									forwardJsp = "propListView";
								}
								logger.debug("propTxnNo--<>" + propTxnNo);
								session.setAttribute("propTxnNo",propTxnNo);
								PropertyValuationDTO completeDTO = 
										rForm.getPropertyValuation();
								 
								session.setAttribute("systemValue",
											String.valueOf(completeDTO.getMarketValue()));
								session.setAttribute("considerAmt",
											String.valueOf(
													completeDTO.getConsiderAmt()));
								
								logger.debug("Successfuly Stored....." + propTxnNo);
								sessionRegTxnId = 
									(String)session.getAttribute("sessionRegTxnId");
								ArrayList retPropList = regcompbo
										.getPropertyTxnIdList(sessionRegTxnId);
								logger.debug(retPropList);
								eForm.getRegcompletDto().setPropertyList(retPropList);
							}else if(propertyTxn !=null) {
							
								String propertyType = (String)session.getAttribute(
										RegCompConstant.PROPERTY_TYPE_SESSION);
								logger.debug("propertyTxn:-"+propertyTxn);
								boolean retflg = 
									regcompbo.deletePropertyDetails(
											propertyTxn,propertyType);
								
								if(retflg) {
									sessionRegTxnId = 
										(String)session.getAttribute("sessionRegTxnId");
							
									
									RegCompBD rbd = new RegCompBD();
									RegCompletionForm rForm = (RegCompletionForm)
											session.getAttribute(
											RegCompConstant.PROPERTY_DTO_SESSION);
									RegCompletDTO regDTO = rForm.getRegcompletDto();
									
									regDTO.setOldRegNo(sessionRegTxnId);
									
									PropertyValuationDTO propertyDTO = 
											rForm.getPropertyValuation();
									
									logger.debug("rForm.getHdnExtSubClause():-"
												+rForm.getHdnExtSubClause());
									propertyDTO.setHdnExtSubClause(
											rForm.getHdnExtSubClause());
									regDTO.setMapBuilding(
											rForm.getMapBuildingDetails());
									regDTO.setPropertyTxnNo(propertyTxn);
									String propTxnNo = 
											rbd.updatePropertyDetails(
													regDTO, propertyDTO);
									
									if (session.getAttribute("suppleDetails") != null) {
			
										/*session.setAttribute("propTxnNo",
												propTxnNo);*/
										forwardJsp = "suppleForwardPath";
									} else {
			
										forwardJsp = "propListView";
									}
									logger.debug("propTxnNo--<>" + propTxnNo);								 
									logger.debug("Successfuly Stored....." + propTxnNo);							
									ArrayList retPropList = regcompbo
											.getPropertyTxnIdList(sessionRegTxnId);
									logger.debug(retPropList);
									eForm.getRegcompletDto().setPropertyList(retPropList);
									forwardJsp = "propListView";
								}
							}	
							resetToken(request);
							 eForm.setActionName("");
							eForm.setFormName("");
						}
					}
					
					if(RegCompConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
								eForm.getRegcompletDto());
						session.removeAttribute(
								RegCompConstant.PROPERTY_DTO_SESSION);
						session.removeAttribute(
								RegCompConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("poaAdjDto");
						session.removeAttribute("sessionRegTxnId");
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session.removeAttribute("propTxnNo");
						session.removeAttribute(
								CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						forwardJsp = "welcome";
					}
				}
				//End Inserting Data
				
				//Start List Page Action
				if(RegCompConstant.DISPLAY_PROPERTY_LIST_PAGE.
								equals(formName)){
					if(RegCompConstant.ADD_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), 
									eForm.getRegcompletDto());
						session.removeAttribute("propertyTxn");
						forwardJsp = "forward";
					}
					if(RegCompConstant.MODIFY_ACTION.equals(actionName)) {
						// Edited By Aruna
						//String propertyTxn = eForm.getPropertyTxnId();
						String propertyTxn = eForm.getRegcompletDto().getPropertyTxnNo();
						logger.debug("Modify Action:-"+propertyTxn);
						session.setAttribute("propertyTxn", propertyTxn);
						RegCompletDTO bDTO = regcompbo
						.getSelectedPropertyDetails(propertyTxn);
						logger.debug("bDTO.getPropertyTypeId():-"
								+bDTO.getPropertyTypeId());						
						if(valueBo.getPropertyID(
								RegCompConstant.BUILDING_ID).equals(
									bDTO.getPropertyTypeId())) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_BUILDING");
							eForm.setMapBuildingDetails(bDTO.getMapBuilding());
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.PLOT_ID).equals(
									bDTO.getPropertyTypeId())) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_PLOT");
							 
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.AGRICULTURELAND_ID).equals(
									bDTO.getPropertyTypeId())) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_AGRI_LAND");							 
						}	
						PropertyValuationDTO dtoProperty 
									= new PropertyValuationDTO();						
						dtoProperty.setAreaId(bDTO.getAreaTypeId());
						dtoProperty.setConsiderAmt(bDTO.getConsiderAmt());
						dtoProperty.setConstructedArea(
								bDTO.getConstructedArea() == null ?0.0 :
								Double.parseDouble(
										bDTO.getConstructedArea()));
						
						 dtoProperty.setTotalSqMeter(
								 bDTO.getTotalArea()== null ? 0.0 :
								 Double.parseDouble(
								 bDTO.getTotalArea()));						
						eForm.setRegcompletDto(bDTO);
						forwardJsp = "forward";
					}
					
					if(RegCompConstant.VIEW_ACTION.equals(actionName)) {
						
						
						String propertyTxnId = eForm.getPropertyTxnId();
							;//eForm.getRegcompletDto().getPropertyTxnNo();
						 
						logger.debug("propertyTxnId:-"+propertyTxnId);
						 
						RegCompletDTO bDTO = regcompbo
						.getSelectedPropertyDetails(propertyTxnId);
						logger.debug("bDTO.getPropertyTypeId():-"
								+bDTO.getPropertyTypeId());
						String propertyType = bDTO.getPropertyTypeId();
						String[] pType = propertyType!=null ?
								propertyType.split("~"):null;
				 
						if(valueBo.getPropertyID(
								RegCompConstant.BUILDING_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_BUILDING");
							eForm.setMapBuildingDetails(bDTO.getMapBuilding());
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.PLOT_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_PLOT");
							 
						}	
						if(valueBo.getPropertyID(
								RegCompConstant.AGRICULTURELAND_ID).equals(
										pType[0])) {
							session.setAttribute(
									RegCompConstant.PROPERTY_TYPE_SESSION, 
									"PROPERTY_TYPE_AGRI_LAND");
							 
						}	
						eForm.setRegcompletDto(bDTO);
						forwardJsp =  
							 RegCompConstant.VALUATION_VIEW_POPUP_PAGE;
						session.removeAttribute("propertyTxn");
					}
					if(RegCompConstant.DELETE_ACTION.equals(actionName)) {
						String propertyType = (String)session.getAttribute(
										RegCompConstant.PROPERTY_TYPE_SESSION);
						logger.debug("refdto.getPropertyTxnNo():-"
									+refdto.getPropertyTxnNo());
						System.out.println("propertytxmid for delete id : "+refdto.getPropertyTxnNo());
						boolean retflg = regcompbo.deletePropertyDetails(
								refdto.getPropertyTxnNo(),
								propertyType);
						logger.debug("in action delete status--" + retflg);
						sessionRegTxnId = 
							(String)session.getAttribute("sessionRegTxnId");
						if (retflg) {
							ArrayList retPropList = regcompbo
									.getPropertyTxnIdList(sessionRegTxnId);
							logger.debug(retPropList);
							eForm.getRegcompletDto()
									.setPropertyList(retPropList);
							 
						}  
						forwardJsp = "propListView";
						session.removeAttribute("propertyTxn");
					}
					if(RegCompConstant.NEXT_ACTION.equals(actionName)) {
						 
						logger.debug("Inside action");
					    forwardJsp =  "nextPage";
						//response.sendRedirect("/regDeedProp.do?deedProp=start"); 
						session.removeAttribute("propertyTxn");
					}
				}
				//End Start List Page Action
				
				
				//Start Search Form
				if(RegCompConstant.REGCOM_SEARCH_FORM.equals(formName)){
					
					//Start Property Search	
					if(RegCompConstant.REGCOMP_ACTION_PROPERTY_SEARCH.
							equals(actionName)){
						session.setAttribute("sessionOldRegTxnId",
								refdto.getOldRegNo());						  						
						forwardJsp = "forward";		
					}
					//End Property Search
					
					//Start Validation POA
					if(RegCompConstant.REGCOMP_ACTION_POA.
							equals(actionName)) {
						logger.debug("=======request.getParameter(pageName)===<>"
								+ request.getParameter("pageName"));
						logger.debug("--given poa id in jsp--<>"
								+ eForm.getRegcompletDto().getPoaNo());
						if (eForm.getRegcompletDto().getPoaNo() != null) {
							boolean retFlg = 
								regcompbo.validateRegComplPoaTxnId(eForm
									.getRegcompletDto().getPoaNo());
							logger.debug("retFlg--<><>" + retFlg);
							if (!eForm.getRegcompletDto().getAdjustAppNo().equals("")
									&& eForm.getRegcompletDto().getAdjustAppNo() != null) {

								
								eForm.getRegcompletDto().setAudjuStatus(
										eForm.getRegcompletDto().isAudjuStatus());
								eForm.getRegcompletDto().setAdjustAppNo(
										eForm.getRegcompletDto().getAdjustAppNo());
								dto.setAdjustAppNo(eForm.getRegcompletDto()
										.getAdjustAppNo());
								eForm.getRegcompletDto().setPropertyTxnNo(
										eForm.getPropertyTxnId());
							}
							eForm.getRegcompletDto().setPoaStatus(retFlg);

							eForm.getRegcompletDto().setPoaNo(
									eForm.getRegcompletDto().getPoaNo());

							dto.setPoaNo(eForm.getRegcompletDto().getPoaNo());
						}
//						request.getSession().setAttribute("poaAdjDto",regform.getRegcompletDto());
						session.setAttribute("poaAdjDto",eForm.getRegcompletDto());
						logger.debug("--in action poaStatus--<>"
								+ eForm.getRegcompletDto().isPoaStatus());
						forwardJsp = "nextdisp";
					}
					//End Validation POA
					
					//Start Validation Audjudication
					if(RegCompConstant.REGCOMP_ACTION_AUDJ.
							equals(actionName)) {
						
						logger.debug("--given audj app id in jsp--<>"
							+ eForm.getRegcompletDto().getAdjustAppNo());
						if (eForm.getRegcompletDto().getAdjustAppNo() != null &&
							!"".equals
							(eForm.getRegcompletDto().getAdjustAppNo().trim())	) {
							
							String propTxnId = regcompbo
									.validateRegComplAdjudicationId(eForm
											.getRegcompletDto().getAdjustAppNo());
							if (propTxnId != null) {
								eForm.getRegcompletDto().setPropertyTxnNo(propTxnId);
								// regform.getRegcompletDto().setPropertyTxnNo(propTxnId);
								eForm.getRegcompletDto().setAdjustAppNo(
										eForm.getRegcompletDto().getAdjustAppNo());

								eForm.getRegcompletDto().setAudjuStatus(true);

								eForm.getRegcompletDto().setPoaStatus(
										eForm.getRegcompletDto().isPoaStatus());

								dto.setAdjustAppNo(eForm.getRegcompletDto()
										.getAdjustAppNo());
								if (eForm.getRegcompletDto().getPoaNo() != null
										&& !eForm.getRegcompletDto().getPoaNo()
												.equals("")) {

									dto.setPoaNo(eForm.getRegcompletDto().getPoaNo());
									eForm.getRegcompletDto().setPoaNo(
											eForm.getRegcompletDto().getPoaNo());
									eForm.getRegcompletDto().setPoaStatus(
											eForm.getRegcompletDto().isPoaStatus());
								}

							} else {
								if (eForm.getRegcompletDto().getPoaNo() != null
										&& !eForm.getRegcompletDto().getPoaNo()
												.equals("")) {
									dto.setAdjustAppNo(eForm.getRegcompletDto()
											.getAdjustAppNo());
									dto.setPoaNo(eForm.getRegcompletDto().getPoaNo());
									eForm.getRegcompletDto().setPoaNo(
											eForm.getRegcompletDto().getPoaNo());
									eForm.getRegcompletDto().setPoaStatus(
											eForm.getRegcompletDto().isPoaStatus());
								}

								eForm.getRegcompletDto().setPropertyTxnNo(propTxnId);
								eForm.getRegcompletDto().setAudjuStatus(false);
							}
//							request.getSession().setAttribute("poaAdjDto",regform.getRegcompletDto());
							session.setAttribute("poaAdjDto",eForm.getRegcompletDto());
							logger.debug("--in action AudjStatus--<>"
									+ dto.isAudjuStatus() + "--PropertyTxnNo --<>"
									+ dto.getPropertyTxnNo());
						}
						forwardJsp = "nextdisp";
					}
					//End Validation Audjudication
					
					
				}
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			forwardJsp = "Failure";
		}
		return mapping.findForward(forwardJsp);
	}

	
	private void resetValue(PropertyValuationDTO dto,RegCompletDTO rdto) {
		rdto.setAddress("");
		rdto.setAdjustAppNo("");
		rdto.setAreaList(new ArrayList());
		rdto.setAreaTypeId("");
	
		rdto.setCeilinghidType("");
		rdto.setCeilingTypeId("");
		rdto.setConsiderAmt(0);
		rdto.setConstructedArea("");
		rdto.setDistId("");
		rdto.setEast("");
		rdto.setNorth("");
		rdto.setWardStatus("");
		rdto.setWardId("");
		rdto.setMohallaId("");
		rdto.setWardList(new ArrayList());
		rdto.setMohallList(new ArrayList());
		rdto.setRicircle("");
		rdto.setLayoutdet("");
		rdto.setRinpustikaNum("");
		rdto.setPropertyTypeId("");
		rdto.setGovmunciplId("");
		rdto.setHdnExtSubClause("");
		rdto.setKhasaraNum("");
		rdto.setMapBuilding(new HashMap());
		rdto.setWest("");
		rdto.setWardpatwarName("");
		rdto.setWardList(new ArrayList());
		rdto.setWardpatwariList(new ArrayList());
		rdto.setVikasId("");
		rdto.setUsePlotId("");
		rdto.setUsageBuilding("");
		rdto.setMarketValue("");
		rdto.setNazoolstNum("");
		rdto.setTehsilId("");
		rdto.setMohallaId("");
		rdto.setPoaNo("");
		rdto.setSouth("");
		rdto.setTehsilList(new ArrayList());
		rdto.setMohallList(new ArrayList());
		
		dto.setConsiderAmt(0);
		dto.setConstructedArea(0);
		dto.setTotalSqMeter(0);
		dto.setCeilingTypeId("");
		dto.setHdnExtSubClause("");
		dto.setTehsilID("");
		dto.setSubClause("");
		
	}
	private String[] getSubClauses(String _subClause) {
		String[] retString = new String[100];
		logger.debug("_subClause--<>" + _subClause);
		StringTokenizer rsTokenOne = new StringTokenizer(_subClause, ",");
		int i = 0;
		while (rsTokenOne.hasMoreTokens()) {
			logger.debug("in while");
			String subone = rsTokenOne.nextToken();
			String[] rsTokenTwo = subone.split("~");
			logger.debug("--rsTokenTwo--<>" + rsTokenTwo);
			logger.debug("in else sub");
			retString[i] = rsTokenTwo[1];

			logger.debug("retString---<><>" + retString);
			i++;
		}
		logger.debug("---sub clause out put" + retString);
		return retString;
	}
	
	public ArrayList getRegisterList()
	{
		ArrayList registerList=new ArrayList();
		RegCompletDTO dto0 = new RegCompletDTO();
		dto0.setRegisterID("AdjudicationNumber");
		RegCompletDTO dto1 = new RegCompletDTO();
		dto1.setRegisterID("PAONumberNumber");
		RegCompletDTO dto2 = new RegCompletDTO();
		dto2.setRegisterID("ExistingRegisterNumber");		
		registerList.add(dto0);
		registerList.add(dto1);
		registerList.add(dto2);		
		return registerList;
	}

}

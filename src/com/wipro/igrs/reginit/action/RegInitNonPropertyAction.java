package com.wipro.igrs.reginit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.regcompletion.bd.CommonDropDownBD;
import com.wipro.igrs.reginit.bo.PropertyValuationBO;
import com.wipro.igrs.reginit.bo.RegCommonBO;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.form.RegCompletionForm;
import com.wipro.igrs.reginit.util.PropertiesFileReader;

/**
 * @author Madan Mohan
 * 
 */
public class RegInitNonPropertyAction extends Action {
	private int count = 0;
	private HashMap map = null;
	

	private Logger logger = (Logger) Logger
			.getLogger(RegInitNonPropertyAction.class);
	private int floorCount = 0;
	boolean bol = true;
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse respons)
			throws IOException, ServletException, Exception {

		try {
			HttpSession session = request.getSession();
			
			String languageLocale="hi";
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
			
			System.out.println("request ij propert action:" + request);
			if (logger.isDebugEnabled()) {
				logger.debug("execute(ActionMapping, ActionForm, "
						+ "HttpServletRequest, HttpServletResponse) -"
						+ " start");
			}
			PropertiesFileReader pr = PropertiesFileReader
					.getInstance("resources.propertyvaluation");
			PropertyValuationBO valueBo = new PropertyValuationBO();

			if (form != null) {
				RegCompletionForm eForm = (RegCompletionForm) form;

				if (request.getParameter("pageName") != null) {
					if (request.getParameter("pageName").equalsIgnoreCase(
							"propertyvalutioan")) {
						eForm.setDistList(valueBo.getDistrict(languageLocale));
						eForm.setAreaTypeList(valueBo.getAreaType(languageLocale));
						eForm.setMunicipalList(valueBo.getMunicipalBody(languageLocale));
						eForm.setPropertyType(valueBo.getPropertyType(languageLocale));
						//eForm.setLandTypeList(valueBo.getLandType());
						
						eForm.setNumfloors(0);
						eForm.setMapBuildingDetails1(new HashMap());
						
						eForm.setMapProperty(new HashMap());
		                 eForm.setAddMoreCounter(0);
		                 eForm.setActionName("");
					}
				}

				CommonDropDownBD bd = new CommonDropDownBD();

				RegCompletDTO proDetailsdto = null;
				RegCompletDTO dto = new RegCompletDTO();
				ArrayList resultSplitPartList = bd.getLocsOfSplitPart();

				eForm.getRegcompletDto().setSplitPartList(resultSplitPartList);

				RegCompletDTO refdto = eForm.getRegcompletDto();
				RegCommonBO commonBo = new RegCommonBO();
				RegCommonForm regInitForm;
				if (request.getAttribute("regInitForm") != null) {

					regInitForm = (RegCommonForm) request
							.getAttribute("regInitForm");
					// session.setAttribute("regInitForm", regInitForm);
					eForm.setRegInitForm((RegCommonForm) request
							.getAttribute("regInitForm"));
				} else {
					// regInitForm=(RegCommonForm)session.getAttribute("regInitForm");
					regInitForm = eForm.getRegInitForm();
				}
				eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
				
				logger.debug("I am RegInitPropertyAction 1------------");
				if (request.getAttribute("propAction") != null) {
					if (request.getAttribute("propAction").toString()
							.equalsIgnoreCase("propAction")) {
						eForm.setRegcompletDto(new RegCompletDTO());
					}
				}
				String formName = eForm.getFormName();
				String actionName = eForm.getActionName();

				
				
				logger.debug("I am RegInitPropertyAction 2------------");
				
				logger.debug("formName:-" + formName);
				logger.debug("actionName:-" + actionName);
				String pageName = request.getParameter("pageName");
				
				if(request.getAttribute("fromAdju")!=null)
				{
					//by shreeraj
					int fromAdju=Integer.parseInt(request.getAttribute("fromAdju").toString());
					System.out.println("from Adju flag is: "+fromAdju);
				//	session.setAttribute("fromAdju", fromAdju);
					eForm.setFromAdjudication(fromAdju);
					//end
				}
				
				
				if (pageName != null
						&& "propertyvalutioan".equalsIgnoreCase(pageName)) {
					
					dto.getKhasraDetlsDisplay().add(new CommonDTO());
					
					// Added By Aruna
					if (session.getAttribute(RegInitConstant.PROPERTY_DTO_SESSION) != null) {
						
						forwardJsp = "propdetails";
					} else {
						
						ArrayList distlist = bd.getDistrict();
						dto.setDistList(distlist);
						eForm.setFormName("");
						eForm.setActionName("");
						eForm.setRegcompletDto(dto);
						
						forwardJsp = "propdetails";
						
					}
				}
				
				if (pageName != null && "propertyView".equalsIgnoreCase(pageName)) { 
					
					if(RegInitConstant.MODIFY_PROPERTY_ACTION.equals(actionName)) {
						/*//by shreeraj
						int fromAdju=Integer.parseInt(request.getAttribute("fromAdju").toString());
						System.out.println("from Adju flag is: "+fromAdju);
						eForm.setFromAdjudication(fromAdju);
						//end
						 * 
						 * 
						 
*/						//session.getAttribute("fromAdju");
						/*System.out.println("AAAAAAAAAAAAAAA");
						System.out.println("VALUE>>>>>>>>>"+(String)session.getAttribute("fromAdju"));
						int fromAdju=Integer.parseInt((String)session.getAttribute("fromAdju"));*/
						//System.out.println("from aju issss>>>>>>>>>>>>>>>>>>>>:"+fromAdju);
						//eForm.setFromAdjudication(fromAdju);
						
						
						eForm.setActionName(RegInitConstant.NO_ACTION);
						regInitForm.setActionName(RegInitConstant.NO_ACTION);
						eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
						eForm.setPropertyId(regInitForm.getPropertyId());
						eForm.setDeedID(regInitForm.getDeedID());
						HashMap propMap=regInitForm.getMapPropertyTransPartyDisp();
						
						commonBo.getPropertyDetsForViewConfirmModify(eForm,propMap,regInitForm.getDeedID());
						
						forwardJsp="editPropDetails";
						
					}
				}

				if (RegInitConstant.REGCOM_PROPERTY_SEARCH.equals(formName)) {
					
					
					if(RegInitConstant.ADD_KHASRA_ACTION.equals(actionName)) {
						
						try {
							//NoEncumDTO floorDTO = ncForm.getNoEncumDTO();
							ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
							commonBo.refreshKhasraData(khasraDets, request);
							khasraDets.add(new CommonDTO());
							
							eForm.getRegcompletDto().setKhasraDetlsDisplay(khasraDets);
							//request.setAttribute("noencumfrm", ncForm);
						} catch (Exception exception) {
							logger.error(exception.getMessage(), exception);
						}
						
						
						forwardJsp="propdetails";
						
					}
					
					if (RegInitConstant.DEL_KHASRA_ACTION.equals(actionName)) {
						try {
							//String[] index = eForm.getRegcompletDto().getSelectedIndex();
							
							String[] index = request.getParameterValues("selectDeleteNot");
							
							ArrayList<CommonDTO> khasraDets = eForm.getRegcompletDto().getKhasraDetlsDisplay();
							commonBo.refreshKhasraData(khasraDets, request);
							//khasraDets.add(new CommonDTO());
							
							if(index!=null && index.length>0)
							{
							for(int i=0;i<index.length;i++){
							khasraDets.remove(Integer.parseInt(index[i].trim())-i);
							//commonBo.refreshKhasraData(khasraDets, request);
							}
							
							}
							
							//commonBo.refreshKhasraData(khasraDets, request);
							
							eForm.getRegcompletDto().setKhasraDetlsDisplay(khasraDets);
							
							
							
							//request.setAttribute("noencumfrm", ncForm);
						} catch (Exception exception) {
							logger.error(exception.getMessage(), exception);
						}
						forwardJsp="propdetails";
					}
					
					
					if (RegInitConstant.REGCOMP_DISTRICT_ACTION.equals(actionName)) {
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						eForm.setTehsilList(new ArrayList());
						if (eForm.getRegcompletDto().getDistId() != null) {
							logger.debug("dto.getDistId():-"
									+ eForm.getRegcompletDto());
							String[] param = eForm.getRegcompletDto().getDistId().split("~");
							if (param != null && param.length > 0) {
								eForm.setTehsilList(valueBo.getTehsil(param[0],languageLocale));//hindi missing
								logger.debug("inside");
								forwardJsp = "propdetails";
								
							}
						}
					}
					// added by shruti
					if (RegInitConstant.REGCOMP_PROPERTYTYPE_ACTION.equals(actionName))              //NEEDED    CORRECT

					{
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						commonBo = new RegCommonBO();
						String propId = eForm.getRegcompletDto().getPropertyTypeId();
						String thesilId = eForm.getRegcompletDto().getTehsilId();
						String[] paramProperty = new String[0];
						paramProperty = propId.split("~");
						logger.debug("propertyId:-" + propId + ":"+ paramProperty[0]);
						String paramProp = "0";
						if (paramProperty != null) {
							paramProp = paramProperty[0];
							
							if(paramProp.equalsIgnoreCase("select")){
								paramProp = "0";
							}
							
						}
						eForm.setPropTypeL1List(commonBo.getPropTypeL1List(paramProp,languageLocale));
						eForm.setPropTypeL2List(new ArrayList());
						eForm.setUnitList(new ArrayList());
						eForm.setFloorTypeList(new ArrayList());
						if(!paramProp.equalsIgnoreCase("2")){                        //2 is for building
							eForm.getRegcompletDto().setMapBuilding(new HashMap());
							eForm.getRegcompletDto().setAddMoreFloorCounter(0);
							eForm.getRegcompletDto().setUnit("");
							
						}else{
							if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							eForm.getRegcompletDto().setUnit(RegInitConstant.SQM_ENGLISH);
							}else{
								eForm.getRegcompletDto().setUnit(RegInitConstant.SQM_HINDI);
							}
						}
						eForm.getRegcompletDto().setArea(0);
						eForm.getRegcompletDto().setPropTypeL2Flag(0);
						
						
						forwardJsp = "propdetails";
					}
					if (RegInitConstant.REGCOMP_PROPL1TYPE_ACTION.equals(actionName))					//NEEDED

					{
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						commonBo = new RegCommonBO();
						String propL1Id=eForm.getRegcompletDto().getPropTypeL1Id();
						String[] paramProperty;
						paramProperty = propL1Id.split("~");
						logger.debug("propertyId:-" + propL1Id + ":"+ paramProperty[0]);
						String paramProp = "";
						if (paramProperty != null && paramProperty.length == 2) {
							paramProp = paramProperty[0];
						}
						eForm.setPropTypeL2List(commonBo.getPropTypeL2List(paramProp,languageLocale));
						
						if(eForm.getPropTypeL2List()!=null && eForm.getPropTypeL2List().size()>0){
							eForm.getRegcompletDto().setPropTypeL2Flag(1);
						}else{
							eForm.getRegcompletDto().setPropTypeL2Flag(0);
						}
						
						
						if(eForm.getRegcompletDto().getPropertyTypeId().equalsIgnoreCase("2")){
							eForm.setFloorTypeList(commonBo.getFloorType(paramProp,languageLocale));                  //get floor list in case of property type building
							}
						eForm.setUnitList(commonBo.getUnitList(paramProp,languageLocale));
						
						forwardJsp = "propdetails";
					}

					// end of code added by shruti

					if (RegInitConstant.CANCEL_ACTION.equals(actionName)) {
						resetValue(eForm.getPropertyDTO(), eForm
								.getRegcompletDto());
						session
								.removeAttribute(RegInitConstant.PROPERTY_DTO_SESSION);
						session
								.removeAttribute(RegInitConstant.PROPERTY_TYPE_SESSION);
						session.removeAttribute("considerAmt");
						session.removeAttribute("systemValue");
						session
								.removeAttribute(CommonConstant.HASHMAP_DTO_SESSION);
						session.removeAttribute("sessionPropertyTxnId");
						
						CommonAction.cancelAction(session, regInitForm);
						forwardJsp = "welcome";
					}
					
					if (RegInitConstant.REGCOMP_WARDPATWARI_ACTION.equals(actionName)) {              //NEEDED         CORRECT
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						String thesilId = eForm.getRegcompletDto().getTehsilId();
						String[] paramTehsil = new String[0];
						paramTehsil = thesilId.split("~");

						logger.debug("thesilId:-" + thesilId + ":"+ paramTehsil[0]);
						String paramTeh = "";
						if (paramTehsil != null && paramTehsil.length == 2) {
							paramTeh = paramTehsil[0];
						}
						String status = (String) eForm.getRegcompletDto().getWardpatwarId();

						eForm.getRegcompletDto().setWardStatus(status);
						// modified by shruti-according to ids in int as per
						// master data
						if (status.equalsIgnoreCase("W")) {
							status = "1";
						} else if (status.equalsIgnoreCase("P")) {
							status = "2";
						}

						String[] paramArea = eForm.getRegcompletDto()
								.getAreaTypeId().split("~");

						String paramA = "";
						if (paramArea != null & paramArea.length == 2) {
							paramA = paramArea[0];
						}
						eForm.setWardList(valueBo.getWard(paramTeh, paramA,status,languageLocale));
						forwardJsp = "propdetails";
						// modified by shruti
						// forwardJsp = "forward";
						// end of modification by shruti
					}
					if (RegInitConstant.REGCOMP_MOHALLA_ACTION                                      //NEEDED              CORRECT
							.equals(actionName)) {
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						String[] paramWard = eForm.getRegcompletDto().getWardId().split("~");
						String mohId = "";
						if (paramWard != null && paramWard.length == 2) {
							mohId = paramWard[0];
						}
						// modified by shruti
						mohId = paramWard[0];
						// end of modification by shruti
						// logger.debug("mohalla Id:-"+mohId+":"+paramWard);
						eForm.setMahallaList(valueBo.getMahalla(mohId,languageLocale));
						forwardJsp = "propdetails";
						// commented by shruti

						// forwardJsp = "forward";
						// end of commented code
					}
					
					if (RegInitConstant.ADD_FLOOR_ACTION.equals(actionName))					//NEEDED

					{
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						
						logger.debug("action name floor 1--->"+eForm.getActionName());
						
						 eForm.setActionName(RegInitConstant.NO_ACTION);
						 
						 logger.debug("action name floor 2--->"+eForm.getActionName());
						HashMap mapFloor = eForm.getRegcompletDto().getMapBuilding();
						
						RegCompletDTO mapDto=new RegCompletDTO();
						
						mapDto.setPropTypeL1Id(eForm.getRegcompletDto().getPropTypeL1Id());
						String[] l1=eForm.getRegcompletDto().getPropTypeL1Id().split("~");
						mapDto.setPropTypeL1Name(l1[1]);                                     //prop type l1 name
						mapDto.setPropTypeL2Id(eForm.getRegcompletDto().getPropTypeL2Id());
						mapDto.setTypeFloorID(eForm.getRegcompletDto().getFloorId());
						String[] floor=eForm.getRegcompletDto().getFloorId().split("~");
						mapDto.setTypeFloorName(floor[1]);                                   //floor name
						mapDto.setAreaConstructed(eForm.getRegcompletDto().getAreaConstructed());
						mapDto.setArea(eForm.getRegcompletDto().getArea());
						mapDto.setUnitTypeId(eForm.getRegcompletDto().getUnitId());
						String[] unit=eForm.getRegcompletDto().getUnitId().split("~");
						mapDto.setUnitType(unit[1]);                                         //unit name
						//mapDto.setFloorTxnId(floorTxnId);
						
						int count=eForm.getRegcompletDto().getAddMoreFloorCounter();
		                 if(count==0)
		                	 count=1;
		             
		                 if(mapFloor.containsKey(Integer.toString(count))){
			                 
		                	 count++;
		                	 mapFloor.put(Integer.toString(count), mapDto);
		                	 
			                 }
		                 else
		                	 mapFloor.put(Integer.toString(count), mapDto);
						//mapFloor.put("", mapDto);
		                 
		                 eForm.getRegcompletDto().setMapBuilding(mapFloor);
		                 eForm.getRegcompletDto().setAddMoreFloorCounter(count);
		                 eForm.setPropTypeL1List(commonBo.getPropTypeL1List(eForm.getRegcompletDto().getPropertyTypeId(),languageLocale));
							eForm.setPropTypeL2List(new ArrayList());
							eForm.setUnitList(new ArrayList());
							eForm.setFloorTypeList(new ArrayList());
							eForm.getRegcompletDto().setArea(0);
							eForm.getRegcompletDto().setAreaConstructed(0);
							eForm.getRegcompletDto().setPropTypeL1Id("");
						
						forwardJsp = "propdetails";
						eForm.setForwardJsp(forwardJsp);
					}
					if (RegInitConstant.DELETE_FLOOR_ACTION.equals(actionName))					//NEEDED

					{
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						String[] floorID= eForm.getRegcompletDto().getHdnDeleteFloorID().split(",");
						HashMap floorMap=eForm.getRegcompletDto().getMapBuilding();
						
						
						for(int j = 0 ;j < floorID.length ;j++) {
	                         logger.debug(floorID[j]+" is removed...");
	                         floorMap.remove(floorID[j]);
	                         
	                         
						 }
						eForm.getRegcompletDto().setMapBuilding(floorMap);
						
						forwardJsp = "propdetails";
					}
					
					if (RegInitConstant.UNIT_ACTION.equals(actionName))                           //NEEDED    CORRECT
							 {
						
						commonBo.refreshKhasraData(eForm.getRegcompletDto().getKhasraDetlsDisplay(), request);
						
						if (eForm.getRegcompletDto().getUnitId() != null) {
							logger.debug("dto.getUnitId():-"
									+ eForm.getRegcompletDto().getUnitId());
							String[] param = eForm.getRegcompletDto().getUnitId().split("~");
							if (param != null && param.length ==2) {
								eForm.getRegcompletDto().setUnit(param[1].trim());
								logger.debug("inside UNIT_ACTION");
								
								
							}else{
								eForm.getRegcompletDto().setUnit("");
							}
							forwardJsp = "propdetails";
						}
					}
					
				}
				
			
				// below code by roopam
				if (RegInitConstant.REGCOM_PROPERTY_SEARCH.equals(formName)) {
					if (RegInitConstant.NEXT_TO_CONFIRM_ACTION                            //NEEDED
							.equals(actionName)) {

						if(eForm.getRegcompletDto().getKhasraNoArray() != null && eForm.getRegcompletDto().getKhasraAreaArray()!=null
								&& eForm.getRegcompletDto().getLagaanArray()!=null && eForm.getRegcompletDto().getRinPustikaArray()!=null 
								&& !eForm.getRegcompletDto().getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getRegcompletDto().getKhasraAreaArray().equalsIgnoreCase("null")
								&& !eForm.getRegcompletDto().getLagaanArray().equalsIgnoreCase("null") && !eForm.getRegcompletDto().getRinPustikaArray().equalsIgnoreCase("null")
								&& !eForm.getRegcompletDto().getKhasraNoArray().equalsIgnoreCase("") && !eForm.getRegcompletDto().getKhasraAreaArray().equalsIgnoreCase("")
								&& !eForm.getRegcompletDto().getLagaanArray().equalsIgnoreCase("") && !eForm.getRegcompletDto().getRinPustikaArray().equalsIgnoreCase("")) {

							logger.debug("khasra nos. :-"+ eForm.getRegcompletDto().getKhasraNoArray());
							logger.debug("khasra areas. :-"+ eForm.getRegcompletDto().getKhasraAreaArray());
							logger.debug("lagaans :-" + eForm.getRegcompletDto().getLagaanArray());
							logger.debug("rin pustikas :-"+ eForm.getRegcompletDto().getRinPustikaArray());

							String str = "";
							str = eForm.getRegcompletDto().getKhasraNoArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setKhasraNoArray(str);
							//
							str = eForm.getRegcompletDto().getKhasraAreaArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setKhasraAreaArray(str);
							//
							str = eForm.getRegcompletDto().getLagaanArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setLagaanArray(str);
							//
							str = eForm.getRegcompletDto().getRinPustikaArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setRinPustikaArray(str);

						}
						
		      			//following code for property id generation added by roopam
		      			
		      			String districtAry=eForm.getRegcompletDto().getDistId();
		      			String[] districtParams=districtAry.split("~");
		      			String districtId=districtParams[0];
		      			//distId=eForm.getRegcompletDto().getDistId();
		      				if(districtId.length()==1)
		      					districtId="0"+districtId;
		      				
		      				String tehsilAry=eForm.getRegcompletDto().getTehsilId();
		      				String[] tehsilParams=tehsilAry.split("~");
		      				String tehslId=tehsilParams[0];
		      				//String tehslId=eForm.getRegcompletDto().getTehsilId();
		      				if(tehslId.length()==1)
		      					tehslId="0"+tehslId;
		      				
		      				if(eForm.getRegcompletDto().getPropertyId()=="" || eForm.getRegcompletDto().getPropertyId()==null) //change
		      				{
		      				String propertyId=districtId+tehslId+commonBo.getPropertyIdSequence();
		      				logger.debug("property id------------>"+propertyId);
		      				if(propertyId.length()==15)
		      				eForm.getRegcompletDto().setPropertyId("0"+propertyId);
		      				else
		      					eForm.getRegcompletDto().setPropertyId(propertyId);
		      				}
		      				
		      				//end of code for property id generation by roopam
		                  
		                  
		      				//below code for adding last floor into floor map
		      			if(eForm.getRegcompletDto().getPropertyTypeId().equalsIgnoreCase("2"))
		      				{
		      				HashMap mapFloor = eForm.getRegcompletDto().getMapBuilding();
							
							RegCompletDTO mapDto=new RegCompletDTO();
							
							mapDto.setPropTypeL1Id(eForm.getRegcompletDto().getPropTypeL1Id());
							String[] l1=eForm.getRegcompletDto().getPropTypeL1Id().split("~");
							mapDto.setPropTypeL1Name(l1[1]);                                     //prop type l1 name
							mapDto.setPropTypeL2Id(eForm.getRegcompletDto().getPropTypeL2Id());
							mapDto.setTypeFloorID(eForm.getRegcompletDto().getFloorId());
							String[] floor=eForm.getRegcompletDto().getFloorId().split("~");
							mapDto.setTypeFloorName(floor[1]);                                   //floor name
							mapDto.setAreaConstructed(eForm.getRegcompletDto().getAreaConstructed());
							mapDto.setArea(eForm.getRegcompletDto().getArea());
							mapDto.setUnitTypeId(eForm.getRegcompletDto().getUnitId());
							String[] unit=eForm.getRegcompletDto().getUnitId().split("~");
							mapDto.setUnitType(unit[1]);                                         //unit name
							//mapDto.setFloorTxnId(floorTxnId);
							
							int count=eForm.getRegcompletDto().getAddMoreFloorCounter();
			                 if(count==0)
			                	 count=1;
			             
			                 if(mapFloor.containsKey(Integer.toString(count))){
				                 
			                	 count++;
			                	 mapFloor.put(Integer.toString(count), mapDto);
			                	 
				                 }
			                 else
			                	 mapFloor.put(Integer.toString(count), mapDto);
							//mapFloor.put("", mapDto);
			                 
			                 eForm.getRegcompletDto().setMapBuilding(mapFloor);
			                 eForm.getRegcompletDto().setAddMoreFloorCounter(count);
			                 
			                 //eForm.setPropTypeL1List(commonBo.getPropTypeL1List(eForm.getRegcompletDto().getPropertyTypeId()));
								//eForm.setPropTypeL2List(new ArrayList());
								//eForm.setUnitList(new ArrayList());
								//eForm.setFloorTypeList(new ArrayList());
								//eForm.getRegcompletDto().setArea(0);
								//eForm.getRegcompletDto().setAreaConstructed(0);
								//eForm.getRegcompletDto().setPropTypeL1Id("");
		      				
					}
		      			
		      			eForm.getRegcompletDto().setPropertyTypeName(commonBo.getPropertyTypeName(eForm.getRegcompletDto().getPropertyTypeId(),languageLocale));
		      				HashMap mapProperty=eForm.getMapProperty();
		      				int countProp=eForm.getAddMoreCounter();
		      				
		      				if(countProp==0)
		      					countProp=1;
			             
			                 if(mapProperty.containsKey(Integer.toString(countProp))){
				                 
			                	 countProp++;
			                	 mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
			                	 
				                 }
			                 else{
			                	 mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
			                 }
		      				
			                 eForm.setMapProperty(mapProperty);
			                 eForm.setAddMoreCounter(countProp);
						
						
						
						
						
			                 Collection mapCollection=eForm.getMapProperty().values();
			                    Object[] l1=mapCollection.toArray();
			                    RegCompletDTO mapDto1 =new RegCompletDTO();
			                  
			                    boolean propertyDetailsInserted=false;
			                   
			                    for(int i=0;i<l1.length;i++)
			                    {
			                    	
			                    	mapDto1=(RegCompletDTO)l1[i];
			                    	
			                    	
			                    	CommonAction action=new CommonAction();
									String filePathAngle1="";
									String filePathAngle2="";
									String filePathAngle3="";
									String filePathMap="";
									String filePathRin="";
									String filePathKhasra="";
									//boolean propertyDetailsInserted=false;
									
									
									filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage1DocContents(),
											mapDto1.getPropertyId(),mapDto1.getPropImage1PartyOrProp(),mapDto1.getPropImage1UploadType());
									
									if(filePathAngle1!=null && !filePathAngle1.equalsIgnoreCase("")){
										mapDto1.setPropImage1FilePath(filePathAngle1);
										filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage2DocContents(),
												mapDto1.getPropertyId(),mapDto1.getPropImage2PartyOrProp(),mapDto1.getPropImage2UploadType());
										if(filePathAngle2!=null && !filePathAngle2.equalsIgnoreCase("")){
											mapDto1.setPropImage2FilePath(filePathAngle2);
											filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage3DocContents(),
													mapDto1.getPropertyId(),mapDto1.getPropImage3PartyOrProp(),mapDto1.getPropImage3UploadType());
											if(filePathAngle3!=null && !filePathAngle3.equalsIgnoreCase("")){
												mapDto1.setPropImage3FilePath(filePathAngle3);
												filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropMapDocContents(),
														mapDto1.getPropertyId(),mapDto1.getPropMapPartyOrProp(),mapDto1.getPropMapUploadType());
												if(filePathMap!=null && !filePathMap.equalsIgnoreCase("") && mapDto1.getPropertyTypeId().equalsIgnoreCase("3")){
													mapDto1.setPropMapFilePath(filePathMap);
													filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropRinDocContents(),
															mapDto1.getPropertyId(),mapDto1.getPropRinPartyOrProp(),mapDto1.getPropRinUploadType());
													if(filePathRin!=null && !filePathRin.equalsIgnoreCase("")){
														mapDto1.setPropRinFilePath(filePathRin);
														filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropKhasraDocContents(),
																mapDto1.getPropertyId(),mapDto1.getPropKhasraPartyOrProp(),mapDto1.getPropKhasraUploadType());
														if(filePathKhasra!=null && !filePathKhasra.equalsIgnoreCase("")){
															mapDto1.setPropKhasraFilePath(filePathKhasra);
															propertyDetailsInserted =
															commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_PROP_SAVED);
															
															
														}else{
															break;
														}
														
														
													}else{
														break;
													}
													
													
												}else if(filePathMap!=null && !filePathMap.equalsIgnoreCase("")){
													
													mapDto1.setPropMapFilePath(filePathMap);
													propertyDetailsInserted =
														commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_PROP_SAVED);
												}else{
													break;
												}
												
												
											}else{
												break;
											}
											
											
										}else{
											break;
										}
										
									}else{
										break;
									}
									
										  logger.debug("property details inserted :- "+propertyDetailsInserted);
			                    	
			                    	
			                    	/*propertyDetailsInserted =
			  						  commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_PROP_SAVED);
			  						  logger.debug("property details inserted :- "+propertyDetailsInserted);*/
			  						  
			  						  
			                    }
						
						 // boolean propertyDetailsInserted;
						  
						 
					if(propertyDetailsInserted){
						forwardJsp = "reginitConfirm";
						eForm.setActionName("");
						eForm.getRegcompletDto().setPropTypeL2Flag(0);
					}else{

						eForm.setHidnRegTxnId(null);
						eForm.setActionName("");
						eForm.getRegcompletDto().setPropTypeL2Flag(0);
						eForm.setMapProperty(new HashMap());
						forwardJsp = "propdetails";
						
					
					}

					}
					if (RegInitConstant.ADD_MORE_PROP_ACTION.equals(actionName)) {                   //NEEDED
						
						

						
						logger.debug("action name prop 1--->"+eForm.getActionName());
						
						 eForm.setActionName(RegInitConstant.NO_ACTION);
						 
						 logger.debug("action name prop 2--->"+eForm.getActionName());
						
						 if(eForm.getRegcompletDto().getKhasraNoArray() != null && eForm.getRegcompletDto().getKhasraAreaArray()!=null
									&& eForm.getRegcompletDto().getLagaanArray()!=null && eForm.getRegcompletDto().getRinPustikaArray()!=null 
									&& !eForm.getRegcompletDto().getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getRegcompletDto().getKhasraAreaArray().equalsIgnoreCase("null")
									&& !eForm.getRegcompletDto().getLagaanArray().equalsIgnoreCase("null") && !eForm.getRegcompletDto().getRinPustikaArray().equalsIgnoreCase("null")
									&& !eForm.getRegcompletDto().getKhasraNoArray().equalsIgnoreCase("") && !eForm.getRegcompletDto().getKhasraAreaArray().equalsIgnoreCase("")
									&& !eForm.getRegcompletDto().getLagaanArray().equalsIgnoreCase("") && !eForm.getRegcompletDto().getRinPustikaArray().equalsIgnoreCase("")) {

							logger.debug("khasra nos. :-"
									+ eForm.getRegcompletDto().getKhasraNoArray());
							logger.debug("khasra areas. :-"
									+ eForm.getRegcompletDto().getKhasraAreaArray());
							logger.debug("lagaans :-" + eForm.getRegcompletDto().getLagaanArray());
							logger.debug("rin pustikas :-"
									+ eForm.getRegcompletDto().getRinPustikaArray());

							String str = "";
							str = eForm.getRegcompletDto().getKhasraNoArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setKhasraNoArray(str);
							//
							str = eForm.getRegcompletDto().getKhasraAreaArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setKhasraAreaArray(str);
							//
							str = eForm.getRegcompletDto().getLagaanArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setLagaanArray(str);
							//
							str = eForm.getRegcompletDto().getRinPustikaArray();
							str = str.replace(",", RegInitConstant.SPLIT_CONSTANT_KHASRA);
							eForm.getRegcompletDto().setRinPustikaArray(str);

						}
						//modified by shruti as property id required at this point

		      			//following code for property id generation added by roopam
		      			
		      			String districtAry=eForm.getRegcompletDto().getDistId();
		      			String[] districtParams=districtAry.split("~");
		      			String districtId=districtParams[0];
		      			//distId=eForm.getRegcompletDto().getDistId();
		      				if(districtId.length()==1)
		      					districtId="0"+districtId;
		      				
		      				String tehsilAry=eForm.getRegcompletDto().getTehsilId();
		      				String[] tehsilParams=tehsilAry.split("~");
		      				String tehslId=tehsilParams[0];
		      				//String tehslId=eForm.getRegcompletDto().getTehsilId();
		      				if(tehslId.length()==1)
		      					tehslId="0"+tehslId;
		      				
		      				if(eForm.getRegcompletDto().getPropertyId()=="" || eForm.getRegcompletDto().getPropertyId()==null) //change
		      				{
		      				String propertyId=districtId+tehslId+commonBo.getPropertyIdSequence();
		      				logger.debug("property id------------>"+propertyId);
		      				if(propertyId.length()==15)
		      				eForm.getRegcompletDto().setPropertyId("0"+propertyId);
		      				else
		      					eForm.getRegcompletDto().setPropertyId(propertyId);
		      				}
		      				
		      				//end of code for property id generation by roopam
		                  //end of modification by shruti
		                  
		      				//below code for adding last floor into floor map
		      			if(eForm.getRegcompletDto().getPropertyTypeId().equalsIgnoreCase("2"))
		      				{
		      				HashMap mapFloor = eForm.getRegcompletDto().getMapBuilding();
							
							RegCompletDTO mapDto=new RegCompletDTO();
							
							mapDto.setPropTypeL1Id(eForm.getRegcompletDto().getPropTypeL1Id());
							String[] l1=eForm.getRegcompletDto().getPropTypeL1Id().split("~");
							mapDto.setPropTypeL1Name(l1[1]);                                     //prop type l1 name
							mapDto.setPropTypeL2Id(eForm.getRegcompletDto().getPropTypeL2Id());
							mapDto.setTypeFloorID(eForm.getRegcompletDto().getFloorId());
							String[] floor=eForm.getRegcompletDto().getFloorId().split("~");
							mapDto.setTypeFloorName(floor[1]);                                   //floor name
							mapDto.setAreaConstructed(eForm.getRegcompletDto().getAreaConstructed());
							mapDto.setArea(eForm.getRegcompletDto().getArea());
							mapDto.setUnitTypeId(eForm.getRegcompletDto().getUnitId());
							String[] unit=eForm.getRegcompletDto().getUnitId().split("~");
							mapDto.setUnitType(unit[1]);                                         //unit name
							//mapDto.setFloorTxnId(floorTxnId);
							
							int count=eForm.getRegcompletDto().getAddMoreFloorCounter();
			                 if(count==0)
			                	 count=1;
			             
			                 if(mapFloor.containsKey(Integer.toString(count))){
				                 
			                	 count++;
			                	 mapFloor.put(Integer.toString(count), mapDto);
			                	 
				                 }
			                 else
			                	 mapFloor.put(Integer.toString(count), mapDto);
							//mapFloor.put("", mapDto);
			                 
			                 eForm.getRegcompletDto().setMapBuilding(mapFloor);
			                 eForm.getRegcompletDto().setAddMoreFloorCounter(count);
			                 
			                 //eForm.setPropTypeL1List(commonBo.getPropTypeL1List(eForm.getRegcompletDto().getPropertyTypeId()));
								//eForm.setPropTypeL2List(new ArrayList());
								//eForm.setUnitList(new ArrayList());
								//eForm.setFloorTypeList(new ArrayList());
								//eForm.getRegcompletDto().setArea(0);
								//eForm.getRegcompletDto().setAreaConstructed(0);
								//eForm.getRegcompletDto().setPropTypeL1Id("");
		      				
					}
		      			
		      			eForm.getRegcompletDto().setPropertyTypeName(commonBo.getPropertyTypeName(eForm.getRegcompletDto().getPropertyTypeId(),languageLocale));
		      				HashMap mapProperty=eForm.getMapProperty();
		      				int countProp=eForm.getAddMoreCounter();
		      				
		      				if(countProp==0)
		      					countProp=1;
			             
			                 if(mapProperty.containsKey(Integer.toString(countProp))){
				                 
			                	 countProp++;
			                	 mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
			                	 
				                 }
			                 else{
			                	 mapProperty.put(Integer.toString(countProp), eForm.getRegcompletDto());
			                 }
		      				
			                 eForm.setMapProperty(mapProperty);
			                 eForm.setAddMoreCounter(countProp);
			                 
			                 
			                 //add property details insertion code here for lease deed
			                 
			                 

						if(regInitForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
								regInitForm.getDeedID()==RegInitConstant.DEED_POA || 
								regInitForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_WILL_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV || 
								regInitForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
           					 (regInitForm.getDeedID()==RegInitConstant.DEED_TRUST && regInitForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
           					 (regInitForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
           							 (regInitForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regInitForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
                 					(regInitForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
                 							&& regInitForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                         			(regInitForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
                         					&& regInitForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                                 	(regInitForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
                                 			&& regInitForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1))		
						{
			                 Collection mapCollection=eForm.getMapProperty().values();
			                    Object[] l1=mapCollection.toArray();
			                    RegCompletDTO mapDto1 =new RegCompletDTO();
			                  
			                    boolean propertyDetailsInserted=false;
			                   
			                    for(int i=0;i<l1.length;i++)
			                    {
			                    	
			                    	mapDto1=(RegCompletDTO)l1[i];
			                    	CommonAction action=new CommonAction();
									String filePathAngle1="";
									String filePathAngle2="";
									String filePathAngle3="";
									String filePathMap="";
									String filePathRin="";
									String filePathKhasra="";
									//boolean propertyDetailsInserted=false;
									
									
									filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage1DocContents(),
											mapDto1.getPropertyId(),mapDto1.getPropImage1PartyOrProp(),mapDto1.getPropImage1UploadType());
									
									if(filePathAngle1!=null && !filePathAngle1.equalsIgnoreCase("")){
										mapDto1.setPropImage1FilePath(filePathAngle1);
										filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage2DocContents(),
												mapDto1.getPropertyId(),mapDto1.getPropImage2PartyOrProp(),mapDto1.getPropImage2UploadType());
										if(filePathAngle2!=null && !filePathAngle2.equalsIgnoreCase("")){
											mapDto1.setPropImage2FilePath(filePathAngle2);
											filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropImage3DocContents(),
													mapDto1.getPropertyId(),mapDto1.getPropImage3PartyOrProp(),mapDto1.getPropImage3UploadType());
											if(filePathAngle3!=null && !filePathAngle3.equalsIgnoreCase("")){
												mapDto1.setPropImage3FilePath(filePathAngle3);
												filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropMapDocContents(),
														mapDto1.getPropertyId(),mapDto1.getPropMapPartyOrProp(),mapDto1.getPropMapUploadType());
												if(filePathMap!=null && !filePathMap.equalsIgnoreCase("") && mapDto1.getPropertyTypeId().equalsIgnoreCase("3")){
													mapDto1.setPropMapFilePath(filePathMap);
													filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropRinDocContents(),
															mapDto1.getPropertyId(),mapDto1.getPropRinPartyOrProp(),mapDto1.getPropRinUploadType());
													if(filePathRin!=null && !filePathRin.equalsIgnoreCase("")){
														mapDto1.setPropRinFilePath(filePathRin);
														filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),mapDto1.getPropKhasraDocContents(),
																mapDto1.getPropertyId(),mapDto1.getPropKhasraPartyOrProp(),mapDto1.getPropKhasraUploadType());
														if(filePathKhasra!=null && !filePathKhasra.equalsIgnoreCase("")){
															mapDto1.setPropKhasraFilePath(filePathKhasra);
															propertyDetailsInserted =
															commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS);
															
															
														}else{
															break;
														}
														
														
													}else{
														break;
													}
													
													
												}else if(filePathMap!=null && !filePathMap.equalsIgnoreCase("")){
													
													mapDto1.setPropMapFilePath(filePathMap);
													propertyDetailsInserted =
													commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS);
												}else{
													break;
												}
												
												
											}else{
												break;
											}
											
											
										}else{
											break;
										}
										
									}else{
										break;
									}
									
										  logger.debug("property details inserted :- "+propertyDetailsInserted);
									
									
			                    	
			                    	/*propertyDetailsInserted =
			  						  commonBo.insertPropertyDetailsNonPropDeeds(regInitForm,mapDto1,RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS);
			  						  logger.debug("property details inserted :- "+propertyDetailsInserted);*/
			  						  
			  						  
			                    }
						
						if(propertyDetailsInserted){
						eForm.setActionName("");
						eForm.getRegcompletDto().setPropTypeL2Flag(0);
						eForm.setMapProperty(new HashMap());
						forwardJsp = "multipleProps";
						}else{
							eForm.setHidnRegTxnId(null);
							eForm.setActionName("");
							eForm.getRegcompletDto().setPropTypeL2Flag(0);
							eForm.setMapProperty(new HashMap());
							forwardJsp = "propdetails";
							
						}
						
						}else{
			                 
			                 eForm.setRegcompletDto(new RegCompletDTO());
			                 eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
			                 eForm.setDistList(valueBo.getDistrict(languageLocale));
								eForm.setAreaTypeList(valueBo.getAreaType(languageLocale));
								eForm.setMunicipalList(valueBo.getMunicipalBody(languageLocale));
								eForm.setPropertyType(valueBo.getPropertyType(languageLocale));
								eForm.getRegcompletDto().setPropTypeL2Flag(0);
		      				
								forwardJsp = "propdetails";	
								
						}
								
								/*if(regInitForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV){
									forwardJsp = "multipleProps";
								}else{
									
								}*/
						
						eForm.setForwardJsp(forwardJsp);

					}

					if (RegInitConstant.RESET_PROPERTY_ACTION                                    //NEEDED
							.equals(actionName)) {

						PropertyValuationDTO dto1 = new PropertyValuationDTO();
						RegCompletDTO comDto = new RegCompletDTO();
						comDto.getKhasraDetlsDisplay().add(new CommonDTO());
						eForm.setRegcompletDto(comDto);
						eForm.setPropertyDTO(dto1);

						forwardJsp = "propdetails";
					}
					if(RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
						
						eForm.setActionName(RegInitConstant.NO_ACTION);
						  regInitForm.setActionName(RegInitConstant.NO_ACTION);
						  request.setAttribute("deedId", regInitForm.getDeedID());
	            			request.setAttribute("instId", regInitForm.getInstID());
					
					forwardJsp ="reginitConfirmModify";
						
				}
					if(RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
						
						if(eForm.getKhasraNoArray() != null && eForm.getKhasraAreaArray()!=null
								&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null 
								&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
								&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")
								&& !eForm.getKhasraNoArray().equalsIgnoreCase("") && !eForm.getKhasraAreaArray().equalsIgnoreCase("")
								&& !eForm.getLagaanArray().equalsIgnoreCase("") && !eForm.getRinPustikaArray().equalsIgnoreCase("")) {

                           
                            logger.debug("khasra nos. :-"+eForm.getKhasraNoArray());
                            logger.debug("khasra areas. :-"+eForm.getKhasraAreaArray());
                            logger.debug("lagaans :-"+eForm.getLagaanArray());
                            logger.debug("rin pustikas :-"+eForm.getRinPustikaArray());
                            
                            String str="";
                            str=eForm.getKhasraNoArray();
                            str=str.replace(",",RegInitConstant.SPLIT_CONSTANT_KHASRA);
                            eForm.setKhasraNoArray(str);
                            //
                            str=eForm.getKhasraAreaArray();
                            str=str.replace(",",RegInitConstant.SPLIT_CONSTANT_KHASRA);
                            eForm.setKhasraAreaArray(str);
                            //
                            str=eForm.getLagaanArray();
                            str=str.replace(",",RegInitConstant.SPLIT_CONSTANT_KHASRA);
                            eForm.setLagaanArray(str);
                            //
                            str=eForm.getRinPustikaArray();
                            str=str.replace(",",RegInitConstant.SPLIT_CONSTANT_KHASRA);
                            eForm.setRinPustikaArray(str);
                            
                            
                            
                        }
						
						CommonAction action=new CommonAction();
						String filePathAngle1="";
						String filePathAngle2="";
						String filePathAngle3="";
						String filePathMap="";
						String filePathRin="";
						String filePathKhasra="";
						boolean propertyDetailsInserted=false;
						
						
						filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage1DocContents(),
								eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage1PartyOrProp(),eForm.getRegcompletDto().getPropImage1UploadType());
						
						if(filePathAngle1!=null && !filePathAngle1.equalsIgnoreCase("")){
							eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
							filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage2DocContents(),
									eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage2PartyOrProp(),eForm.getRegcompletDto().getPropImage2UploadType());
							if(filePathAngle2!=null && !filePathAngle2.equalsIgnoreCase("")){
								eForm.getRegcompletDto().setPropImage2FilePath(filePathAngle2);
								filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage3DocContents(),
										eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage3PartyOrProp(),eForm.getRegcompletDto().getPropImage3UploadType());
								if(filePathAngle3!=null && !filePathAngle3.equalsIgnoreCase("")){
									eForm.getRegcompletDto().setPropImage3FilePath(filePathAngle3);
									filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropMapDocContents(),
											eForm.getPropertyId(),eForm.getRegcompletDto().getPropMapPartyOrProp(),eForm.getRegcompletDto().getPropMapUploadType());
									if(filePathMap!=null && !filePathMap.equalsIgnoreCase("") && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)){
										eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
										filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropRinDocContents(),
												eForm.getPropertyId(),eForm.getRegcompletDto().getPropRinPartyOrProp(),eForm.getRegcompletDto().getPropRinUploadType());
										if(filePathRin!=null && !filePathRin.equalsIgnoreCase("")){
											eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
											filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropKhasraDocContents(),
													eForm.getPropertyId(),eForm.getRegcompletDto().getPropKhasraPartyOrProp(),eForm.getRegcompletDto().getPropKhasraUploadType());
											if(filePathKhasra!=null && !filePathKhasra.equalsIgnoreCase("")){
												eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);
												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
												
												
											}
											
											
										}
										
										
									}else if(filePathMap!=null && !filePathMap.equalsIgnoreCase("")){
										
										eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
									}
									
									
								}
								
								
							}
							
						}
						
						
						 
							  
						
							  logger.debug("property details inserted :- "+propertyDetailsInserted);
						
							 /* if(regInitForm.getDeedID()==RegInitConstant.DEED_EXCHANGE && !RegInitConstant.SAVE_PROP_ACTION.equals(actionName)){
							  regInitForm.setPropertyId("");	 
							  eForm.setPropertyId("");
							  }*/
							  
							  if(propertyDetailsInserted){
								  
								 
										  eForm.setActionName(RegInitConstant.NO_ACTION);
										  regInitForm.setActionName(RegInitConstant.NO_ACTION);
										  request.setAttribute("deedId", regInitForm.getDeedID());
					            			request.setAttribute("instId", regInitForm.getInstID());
										  forwardJsp ="reginitConfirmModify";
									 
								  
						//forwardJsp ="reginitConfirm";
							  }
							  else{
								  eForm.setHidnRegTxnId(null);
								 
									  eForm.setActionName(RegInitConstant.NO_ACTION);
									  regInitForm.setActionName(RegInitConstant.NO_ACTION);
									  forwardJsp ="editPropDetails";
								  
							  }
						eForm.setActionName("");
							
					}

				}
				
				if (RegInitConstant.NO_ACTION                                    //NEEDED
						.equals(actionName)) {
					forwardJsp=eForm.getForwardJsp();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			forwardJsp = "Failure";
		}
		return mapping.findForward(forwardJsp);
	}

	private void resetValue(PropertyValuationDTO dto, RegCompletDTO rdto) {
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

}

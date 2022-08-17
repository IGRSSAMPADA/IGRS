package com.wipro.igrs.reginit.action;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
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
import org.apache.struts.util.TokenProcessor;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.noEncumbrance.dto.NoEncumDTO;
import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
import com.wipro.igrs.regcompletion.bd.CommonDropDownBD;
import com.wipro.igrs.reginit.bo.PropertyValuationBO;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.form.RegCompletionForm;
import com.wipro.igrs.reginit.util.PropertiesFileReader;
import com.wipro.igrs.reginit.bo.RegCommonBO;

/**
 * @author Madan Mohan
 * 
 */
public class RegInitPropertyAction extends    Action  {
	//private int count = 0;
	//private HashMap map = null;
	 
	private Logger logger = (Logger) Logger.getLogger(RegInitPropertyAction.class);
	//private int floorCount = 0;
	boolean bol = true;
	/**
	 * @see forwardJsp is used for redirecting
	 */
	private String forwardJsp ; 
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form,
			HttpServletRequest request, 
			HttpServletResponse respons )
			throws IOException, ServletException, Exception {
		
		try {
			     
			
			     HttpSession session = request.getSession();
			     //System.out.println("request ij propert action:"+request);
			     
			     String languageLocale="hi";
					if(session.getAttribute("languageLocale")!=null){
						languageLocale=(String)session.getAttribute("languageLocale");
					}
			     
			     NumberFormat obj=new DecimalFormat("#0.00");
			if (logger.isDebugEnabled()) {
			 	logger.debug("execute(ActionMapping, ActionForm, " 
								+ "HttpServletRequest, HttpServletResponse) -"
									+ " start");
			}
			/*PropertiesFileReader pr = 
	    		PropertiesFileReader.
	    		 getInstance("resouces.propertyvaluation");*/
			
			
			if (form != null) {
				RegCompletionForm eForm = (RegCompletionForm) form;
				
				if(request.getParameter("pageName")!=null){
					if(request.getParameter("pageName").equalsIgnoreCase("propertyvalutioan")){
						eForm.setNumfloors(0);
						eForm.setMapBuildingDetails1(new HashMap());
						eForm.setExchangePropertyList(new ArrayList());
						eForm.setPropertySelected(0);
						eForm.setIsPropertyRemaining(0);
						eForm.setAllPropertiesAdded(0);
						eForm.setSelectedSubClauseList(new ArrayList());
						eForm.setKhasraNoArray("");
                        eForm.setKhasraAreaArray("");
                        eForm.setLagaanArray("");
                        eForm.setRinPustikaArray("");
                        eForm.setForwardJsp("");
                      }
					
				}
				
				/*String distId = null;
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
				String wardOrPatwariName = "";*/
				
				//IGRSCommon common = new IGRSCommon();
				//PropertyValuationBO valueBo = new PropertyValuationBO();
				//CommonDropDownBD bd = new CommonDropDownBD();
				
				//RegCompletDTO proDetailsdto = null;
				//RegCompletDTO dto = new RegCompletDTO();

				
				/*ArrayList resultSplitPartList = bd.getLocsOfSplitPart();
				eForm.getRegcompletDto().setSplitPartList(resultSplitPartList);*/
				
				
				
				//RegCompBO regcompbo = new RegCompBO();
				//RegCompletDTO refdto = eForm.getRegcompletDto();
				RegCommonBO commonBo=new RegCommonBO();
                RegCommonForm regInitForm;
                if(request.getAttribute("regInitForm")!=null){
                	
                	regInitForm=(RegCommonForm)request.getAttribute("regInitForm");
                	eForm.setRegInitForm(regInitForm);
                }
                else{
                	
                	regInitForm=eForm.getRegInitForm();
                }



				//Start:====== Added by Ankita 
				/*put a condition here if comming from rishabs module **/
				
				if(request.getAttribute("propAction")!=null){
				if(request.getAttribute("propAction").toString().equalsIgnoreCase("propAction")){
					eForm.setRegcompletDto(new RegCompletDTO());
				}
				}
				String formName = eForm.getFormName();
				String actionName = eForm.getActionName();
				
				/*if(session.getAttribute("eform")!=null){
				PropertyValuationForm pform = (PropertyValuationForm)session.getAttribute("eform");
				
				
				
				eForm.setAreaType(pform.getAreaDTO().getAreaType());
				eForm.setDistrict(pform.getDistDTO().getDistrict());
				eForm.setTehsil(pform.getTehsilDTO().getTehsil());
				eForm.setPropertyType1(pform.getPropDTO().getPropertyType());
				
				 //this code should actually get data from db table of rishab*
				//start here
				if(eForm.getPropertyType1().equalsIgnoreCase("plot") ||
						eForm.getPropertyType1().equalsIgnoreCase("building"))
				{
					
					eForm.setUnit("sq mtr");
				}
				else
				{
					
					String unit=pform.getPropertyDTO().getLandMeterId();
					if(unit.equalsIgnoreCase("2"))
					{
						eForm.setUnit("SQM");
					}
					else if(unit.equalsIgnoreCase("3"))
					{
						eForm.setUnit("SQHECTRE");
					}
					
				}
				//end here
				eForm.setMunicipalBody(pform.getMunicipalDTO().getMunicipalBody());
				eForm.setMahalla(pform.getMahallaDTO().getMahalla());
				eForm.setWard(pform.getWardDTO().getWard());
				//eForm.setWardId(pform.getWardDTO().getWardId());
				eForm.setNumfloors(pform.getMapBuildingDetails().size());
				eForm.setTotalSqMeter(pform.getPropertyDTO().getTotalSqMeter());
				String wardstatus=pform.getPropertyDTO().getPatwariStatus();
			
				if (wardstatus.equalsIgnoreCase("W"))
				{
					wardstatus="Ward";
				}
				else
				{
					wardstatus="Patwari/Halka";
				}
				eForm.setPatwariStatus(wardstatus);
				
				System.out.println(eForm.getNumfloors());
				eForm.setMapBuildingDetails1(pform.getMapBuildingDetails());
				eForm.setSubCls(pform.getSubClauseList());
				System.out.println(pform.getSubClauseList().size());
				System.out.println(eForm.getMapBuildingDetails1().size());
				HashMap mapBuild = pform.getMapBuildingDetails();
				
				}*/
				//End:=======Added by Ankita
				//following added by roopam to remove session and fetch property details from database.
				String pageName = request.getParameter("pageName");
				if(RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName) || RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName) ||
						(pageName != null && "propertyvalutioan".equalsIgnoreCase(pageName))){
					
					eForm.setHidnRegTxnId(eForm.getRegInitForm().getHidnRegTxnId());
					eForm.setDeedID(eForm.getRegInitForm().getDeedID());
					
					logger.debug("reg id in property action is----->"+eForm.getHidnRegTxnId());
					logger.debug("deed id in property action is----->"+eForm.getDeedID());
					
					
					if(regInitForm.getExchangePropertyList().size()>0 && !(RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName))){
						
						eForm.setExchangePropertyList(regInitForm.getExchangePropertyList());
						logger.debug("exchange property list--->"+eForm.getExchangePropertyList());
						eForm.setPropertySelected(0);
						//eForm.setIsPropertyRemaining(1);
						if(eForm.getExchangePropertyList().size()==1){
							eForm.setAllPropertiesAdded(1);
							eForm.setIsPropertyRemaining(0);
						}else
						{
							eForm.setIsPropertyRemaining(1);
						}
					}
					
					if(RegInitConstant.CHANGE_PROPERTY_ACTION.equals(actionName)){
					eForm.setNumfloors(0);
					eForm.setMapBuildingDetails1(new HashMap());
					eForm.setSelectedSubClauseList(new ArrayList());
					eForm.setPropertyId(eForm.getRegcompletDto().getSelectedPropId());
					
					if(eForm.getRegcompletDto().getSelectedPropValId().equalsIgnoreCase("select"))
						{eForm.setPropertySelected(0);
						eForm.setPropertyId(eForm.getRegInitForm().getPropertyId());
						}
					}else{
						eForm.setPropertyId(eForm.getRegInitForm().getPropertyId());
						
					}
					logger.debug("prop id in property action is----->"+eForm.getPropertyId());
				if( (eForm.getExchangePropertyList().size()==0 &&(regInitForm.getValuationId()!=null && regInitForm.getValuationId()!="")) 
						|| (eForm.getExchangePropertyList().size()>0 &&(eForm.getRegcompletDto().getSelectedPropValId()!=null 
								&& !eForm.getRegcompletDto().getSelectedPropValId().equalsIgnoreCase("select"))) )
				{
					
					String pform[]=new String[16];
					
					if(eForm.getExchangePropertyList().size()==0)
					{
					pform = commonBo.getPropDetlsForDashboard(regInitForm.getValuationId());
					
					}
					else
					{					
						pform = commonBo.getPropDetlsForDashboard(eForm.getRegcompletDto().getSelectedPropValId());	
					}
					
					//if(pform[5].trim()!=null)
					/*eForm.setAreaType(pform[5].trim());
					//else
					//	eForm.setAreaType("-");
					eForm.setDistrict(pform[1].trim());
					eForm.setTehsil(pform[3].trim());
					eForm.setPropertyType1(pform[14].trim());*/
					
					
					/*if(eForm.getPropertyType1().equalsIgnoreCase("plot") ||
							eForm.getPropertyType1().equalsIgnoreCase("building"))*/
					
					if(pform[13]!=null){
					eForm.setPropertyTypeId(pform[13].trim());
					}else{
						eForm.setPropertyTypeId("0");	
					}
					if(pform[13]!=null){
						
					}else{
						pform[13]="0";
					}
					
					if(pform[13].trim().equalsIgnoreCase("1") ||    // 1 FOR PLOT
							pform[13].trim().equalsIgnoreCase("2")) // 2 FOR BUILDING
					{
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						eForm.setUnit(RegInitConstant.SQM_ENGLISH);
						}else{
							eForm.setUnit(RegInitConstant.SQM_HINDI);
						}
					}
					else
					{
						
						/*String unit=pform[16].trim();
						if(unit.equalsIgnoreCase("2"))
						{
							if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
								eForm.setUnit(RegInitConstant.SQM_ENGLISH);
								}else{
									eForm.setUnit(RegInitConstant.SQM_HINDI);
								}
						}
						else if(unit.equalsIgnoreCase("3"))
						{
							if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
								eForm.setUnit(RegInitConstant.HECTARE_ENGLISH);
								}else{
									eForm.setUnit(RegInitConstant.HECTARE_HINDI);
								}
						}else{
							eForm.setUnit(commonBo.getUnitName(pform[16].trim(), languageLocale));
						}*/
						eForm.setUnit(commonBo.getUnitName(pform[16].trim(), languageLocale));
						
					}
					
					if(!pform[13].trim().equalsIgnoreCase("2")) // 2 FOR BUILDING
					{
						
						if(eForm.getExchangePropertyList().size()==0)
						{
							eForm.setSelectedSubClauseList(commonBo.getSubClauseListNonBuilding(regInitForm.getValuationId()));
						
						}
						else
						{					
							eForm.setSelectedSubClauseList(commonBo.getSubClauseListNonBuilding(eForm.getRegcompletDto().getSelectedPropValId()));	
						}
						
					}
					
					if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					eForm.setAreaType(pform[5].trim());
					eForm.setDistrict(pform[1].trim());
					eForm.setTehsil(pform[3].trim());
					eForm.setPropertyType1(pform[14].trim());
					eForm.setMunicipalBody(pform[12].trim());
					eForm.setMahalla(pform[10].trim());
					eForm.setWard(pform[7].trim());
					}else{
						eForm.setAreaType(pform[19].trim());
						eForm.setDistrict(pform[17].trim());
						eForm.setTehsil(pform[18].trim());
						eForm.setPropertyType1(pform[23].trim());
						eForm.setMunicipalBody(pform[22].trim());
						eForm.setMahalla(pform[21].trim());
						eForm.setWard(pform[20].trim());
					}
					
					
					eForm.setTotalSqMeter(Double.parseDouble(pform[15].trim()));
					eForm.setTotalSqMeterDisplay(commonBo.getCurrencyFormat(Double.parseDouble(pform[15].trim())));
					String wardstatus=pform[8].trim();
			
					if (wardstatus.equalsIgnoreCase("1"))
					{
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
						wardstatus=RegInitConstant.WARD_ENGLISH;
						}else{
							wardstatus=RegInitConstant.WARD_HINDI;
						}
					}
					else
					{
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							wardstatus=RegInitConstant.PATWARI_ENGLISH;
							}else{
								wardstatus=RegInitConstant.PATWARI_HINDI;
							}
					}
					eForm.setPatwariStatus(wardstatus);
					
					
					if(pform[13].trim().equalsIgnoreCase("2")){  // 2 FOR BUILDING
						
						
						//following code for getting building floor details
						ArrayList buildingList=new ArrayList();
						if(eForm.getExchangePropertyList().size()==0)
						{
							buildingList=commonBo.getGuildingFloorDetails(regInitForm.getValuationId());
						}
							else
							{
								buildingList = commonBo.getGuildingFloorDetails(eForm.getRegcompletDto().getSelectedPropValId());	
							}
						
						
						if(buildingList.size()>0){
							
							eForm.setNumfloors(buildingList.size());
							for(int j=0;j<buildingList.size();j++){
								RegCompletDTO dto1=new RegCompletDTO();
								ArrayList row_list=(ArrayList)buildingList.get(j);
								String str=row_list.toString();
								str=str.substring(1,str.length()-1);
								String[] strArray=str.split(",");
								
								
								//dto1.setUsageBuilding(strArray[7]);  //l1
								//dto1.setCeilingType(strArray[8]);    //l2
								//dto1.setTypeFloorDesc(strArray[9]);  //floor
								
								dto1.setUsageBuilding(commonBo.getPropertyL1Name(strArray[1].trim(), languageLocale));  //l1
								dto1.setCeilingType(commonBo.getPropertyL2Name(strArray[2].trim(), languageLocale));    //l2
								dto1.setTypeFloorDesc(commonBo.getFloorName(strArray[3].trim(), languageLocale));  //floor
								
								
								dto1.setConsiderAmt(Double.parseDouble(strArray[4]));  //format conversion req. so below statement added
								dto1.setConsiderAmtDisplay(commonBo.getCurrencyFormat(Double.parseDouble(strArray[4])));
								dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
								dto1.setConstructedArea(strArray[6]);
								
								
								if(eForm.getExchangePropertyList().size()==0)
								{
									dto1.setSelectedSubClauseList(commonBo.getSubClauseListBuilding(regInitForm.getValuationId(),strArray[0]));
									
								}
									else
									{
										dto1.setSelectedSubClauseList(commonBo.getSubClauseListBuilding(eForm.getRegcompletDto().getSelectedPropValId(),strArray[0]));
											
									}
								
								
								eForm.getMapBuildingDetails1().put(strArray[0], dto1);
								
								
							}
								
						}
						
					}else{
						eForm.setNumfloors(0);
					}
					
					eForm.setPropertySelected(1);
					}
				
				eForm.setActionName("");
				
				}
				//end of addition by roopam
				
				
				
				
				 
				
				if (pageName != null && "propertyvalutioan".equalsIgnoreCase(pageName)) { 
					
					
					eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
						forwardJsp="propdetails";
						
						if(regInitForm.getFromAdjudication()==1){
							//PropertyValuationForm pform = null;
							//regInitForm.setFromAdjudication(eForm.getFromAdjudication());
							//eForm.setFromAdjudication(1);
							 //session.setAttribute("fnName","Adjudication");
							 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
									session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_ENGLISH);
								}else{
									session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_HINDI);
								}

						}//else{
							//eForm.setFromAdjudication(0);
						//}
					
				}
				
				if (pageName != null && "propertyView".equalsIgnoreCase(pageName)) { 
					
					if(RegInitConstant.MODIFY_PROPERTY_ACTION.equals(actionName)) {
						
						eForm.setActionName(RegInitConstant.NO_ACTION);
						regInitForm.setActionName(RegInitConstant.NO_ACTION);
						eForm.setHidnRegTxnId(regInitForm.getHidnRegTxnId());
						eForm.setPropertyId(regInitForm.getPropertyId());
						eForm.setDeedID(regInitForm.getDeedID());
						HashMap propMap=regInitForm.getMapPropertyTransPartyDisp();
						
						commonBo.getPropertyDetsForViewConfirmModify(eForm,propMap,regInitForm.getDeedID());
						//request.setAttribute("deedId", regInitForm.getDeedID());
						forwardJsp="editPropDetails";
						
					}
				}
				
				
				//below code by roopam
				if(RegInitConstant.REGCOM_PROPERTY_SEARCH.equals(formName)){
					
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
					
					if(RegInitConstant.NEXT_TO_CONFIRM_ACTION.equals(actionName) || RegInitConstant.SAVE_PROP_ACTION.equals(actionName)) {
						
						if(eForm.getKhasraNoArray() != null && eForm.getKhasraAreaArray()!=null
								&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null 
								&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
								&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")) {

                           
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
						
						if((regInitForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
								regInitForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV) && !RegInitConstant.SAVE_PROP_ACTION.equals(actionName)){
						CommonDTO dto1=new CommonDTO();
                        for(int i=0;i<eForm.getExchangePropertyList().size();i++){
							  
							  dto1=(CommonDTO)eForm.getExchangePropertyList().get(i);
							  if(dto1.getValuationId().equalsIgnoreCase(eForm.getRegcompletDto().getSelectedPropValId()))
							  {
								  
								  regInitForm.setPropertyId(dto1.getPropertyId());
								  eForm.setPropertyId(dto1.getPropertyId());
								  break;
							  }
							  
							  
						  }
						}
						
						CommonAction action=new CommonAction();
						String filePathAngle1;
						String filePathAngle2;
						String filePathAngle3;
						String filePathMap;
						String filePathRin;
						String filePathKhasra;
						boolean propertyDetailsInserted=false;
						
						
						filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage1DocContents(),
								eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage1PartyOrProp(),eForm.getRegcompletDto().getPropImage1UploadType());
						
						if(filePathAngle1!=null){
							eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
							filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage2DocContents(),
									eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage2PartyOrProp(),eForm.getRegcompletDto().getPropImage2UploadType());
							if(filePathAngle2!=null){
								eForm.getRegcompletDto().setPropImage2FilePath(filePathAngle2);
								filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage3DocContents(),
										eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage3PartyOrProp(),eForm.getRegcompletDto().getPropImage3UploadType());
								if(filePathAngle3!=null){
									eForm.getRegcompletDto().setPropImage3FilePath(filePathAngle3);
									filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropMapDocContents(),
											eForm.getPropertyId(),eForm.getRegcompletDto().getPropMapPartyOrProp(),eForm.getRegcompletDto().getPropMapUploadType());
									if(filePathMap!=null && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)){
										eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
										filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropRinDocContents(),
												eForm.getPropertyId(),eForm.getRegcompletDto().getPropRinPartyOrProp(),eForm.getRegcompletDto().getPropRinUploadType());
										if(filePathRin!=null){
											eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
											filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropKhasraDocContents(),
													eForm.getPropertyId(),eForm.getRegcompletDto().getPropKhasraPartyOrProp(),eForm.getRegcompletDto().getPropKhasraUploadType());
											if(filePathKhasra!=null){
												eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);
												propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
												
												
											}
											
											
										}
										
										
									}else if(filePathMap!=null){
										
										eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
										propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
										/*if(regInitForm.getFromAdjudication()==1){
											PropertyValuationForm pform = null;
											regInitForm.setFromAdjudication(pform.getFromAdjudication());
											 session.setAttribute("fnName","Adjudication");

										}*/
									}
									
									
								}
								
								
							}
							
						}
						
						
						 
							  
						
							  logger.debug("property details inserted :- "+propertyDetailsInserted);
						
							  if((regInitForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
										regInitForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV) && !RegInitConstant.SAVE_PROP_ACTION.equals(actionName)){
							  regInitForm.setPropertyId("");	 
							  eForm.setPropertyId("");
							  }
							  
							  if(propertyDetailsInserted){
								  
								  if(RegInitConstant.NEXT_TO_CONFIRM_ACTION.equals(actionName)){
									  forwardJsp ="reginitConfirm";
									  }
									  else{
										  eForm.setActionName(RegInitConstant.NO_ACTION);
										  regInitForm.setActionName(RegInitConstant.NO_ACTION);
										  request.setAttribute("deedId", regInitForm.getDeedID());
					            			request.setAttribute("instId", regInitForm.getInstID());
										  forwardJsp ="reginitConfirmModify";
									  } 
								  
						//forwardJsp ="reginitConfirm";
							  }
							  else{
								  eForm.setHidnRegTxnId(null);
								  if(RegInitConstant.NEXT_TO_CONFIRM_ACTION.equals(actionName)){
								  forwardJsp ="propdetails";
								  }
								  else{
									  eForm.setActionName(RegInitConstant.NO_ACTION);
									  regInitForm.setActionName(RegInitConstant.NO_ACTION);
									  forwardJsp ="editPropDetails";
								  }
							  }
						eForm.setActionName("");
							
					}
						if(RegInitConstant.ADD_MORE_PROP_ACTION.equals(actionName)) {
							
							if(eForm.getKhasraNoArray() != null && eForm.getKhasraAreaArray()!=null
									&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
									&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
									&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")) {

		                           
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
							String filePathAngle1;
							String filePathAngle2;
							String filePathAngle3;
							String filePathMap;
							String filePathRin;
							String filePathKhasra;
							boolean propertyDetailsInserted=false;
						
							
							
							
							filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage1DocContents(),
									eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage1PartyOrProp(),eForm.getRegcompletDto().getPropImage1UploadType());
							
							if(filePathAngle1!=null){
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
								filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage2DocContents(),
										eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage2PartyOrProp(),eForm.getRegcompletDto().getPropImage2UploadType());
								if(filePathAngle2!=null){
									eForm.getRegcompletDto().setPropImage2FilePath(filePathAngle2);
									filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage3DocContents(),
											eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage3PartyOrProp(),eForm.getRegcompletDto().getPropImage3UploadType());
									if(filePathAngle3!=null){
										eForm.getRegcompletDto().setPropImage3FilePath(filePathAngle3);
										filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropMapDocContents(),
												eForm.getPropertyId(),eForm.getRegcompletDto().getPropMapPartyOrProp(),eForm.getRegcompletDto().getPropMapUploadType());
										if(filePathMap!=null  && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)){
											eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
											filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropRinDocContents(),
													eForm.getPropertyId(),eForm.getRegcompletDto().getPropRinPartyOrProp(),eForm.getRegcompletDto().getPropRinUploadType());
											if(filePathRin!=null){
												eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
												filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropKhasraDocContents(),
														eForm.getPropertyId(),eForm.getRegcompletDto().getPropKhasraPartyOrProp(),eForm.getRegcompletDto().getPropKhasraUploadType());
												if(filePathKhasra!=null){
													eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);

													  propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
													
													
												}
												
												
											}
											
											
										}else if(filePathMap!=null){
											
											eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
										}
										
										
									}
									
									
								}
								
							}
							
								  logger.debug("property details inserted :- "+propertyDetailsInserted);
							
								 
								  if(propertyDetailsInserted){
										forwardJsp ="multipleProps";
											  }
											  else{
												  eForm.setHidnRegTxnId(null);
												  forwardJsp ="propdetails";
											  }
						
						eForm.setActionName("");
						
							
					}
					
					if(RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)) {
						
						
						
						PropertyValuationDTO dto1= new PropertyValuationDTO();
		            	RegCompletDTO comDto= new RegCompletDTO();
		            	eForm.setRegcompletDto(comDto);
		            	eForm.setPropertyDTO(dto1);
		            	eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
						
		            	forwardJsp="propdetails";
		            	eForm.setActionName("");
					}
						if(RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
							
							eForm.setActionName(RegInitConstant.NO_ACTION);
							  regInitForm.setActionName(RegInitConstant.NO_ACTION);
							  request.setAttribute("deedId", regInitForm.getDeedID());
		            			request.setAttribute("instId", regInitForm.getInstID());
		            				
						forwardJsp ="reginitConfirmModify";
							
					}
						if(RegInitConstant.ADD_NEXT_PROP_DETLS_ACTION.equals(actionName)) {
							
							CommonDTO dto1=new CommonDTO();
							int index=0;
							
							if(eForm.getKhasraNoArray() != null && eForm.getKhasraAreaArray()!=null
									&& eForm.getLagaanArray()!=null && eForm.getRinPustikaArray()!=null
									&& !eForm.getKhasraNoArray().equalsIgnoreCase("null") && !eForm.getKhasraAreaArray().equalsIgnoreCase("null")
									&& !eForm.getLagaanArray().equalsIgnoreCase("null") && !eForm.getRinPustikaArray().equalsIgnoreCase("null")) {

		                           
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
							
							for(int i=0;i<eForm.getExchangePropertyList().size();i++){
								  
								  dto1=(CommonDTO)eForm.getExchangePropertyList().get(i);
								  if(dto1.getValuationId().equalsIgnoreCase(eForm.getRegcompletDto().getSelectedPropValId()))
								  {
									  index=i;
									  regInitForm.setPropertyId(dto1.getPropertyId());
									  eForm.setPropertyId(dto1.getPropertyId());
									  break;
								  }
								  
								  
							  }
							CommonAction action=new CommonAction();
							String filePathAngle1;
							String filePathAngle2;
							String filePathAngle3;
							String filePathMap;
							String filePathRin;
							String filePathKhasra;
							boolean propertyDetailsInserted=false;
							  
							

							filePathAngle1=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage1DocContents(),
									eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage1PartyOrProp(),eForm.getRegcompletDto().getPropImage1UploadType());
							
							if(filePathAngle1!=null){
								eForm.getRegcompletDto().setPropImage1FilePath(filePathAngle1);
								filePathAngle2=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage2DocContents(),
										eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage2PartyOrProp(),eForm.getRegcompletDto().getPropImage2UploadType());
								if(filePathAngle2!=null){
									eForm.getRegcompletDto().setPropImage2FilePath(filePathAngle2);
									filePathAngle3=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropImage3DocContents(),
											eForm.getPropertyId(),eForm.getRegcompletDto().getPropImage3PartyOrProp(),eForm.getRegcompletDto().getPropImage3UploadType());
									if(filePathAngle3!=null){
										eForm.getRegcompletDto().setPropImage3FilePath(filePathAngle3);
										filePathMap=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropMapDocContents(),
												eForm.getPropertyId(),eForm.getRegcompletDto().getPropMapPartyOrProp(),eForm.getRegcompletDto().getPropMapUploadType());
										if(filePathMap!=null  && eForm.getPropertyTypeId().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)){
											eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
											filePathRin=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropRinDocContents(),
													eForm.getPropertyId(),eForm.getRegcompletDto().getPropRinPartyOrProp(),eForm.getRegcompletDto().getPropRinUploadType());
											if(filePathRin!=null){
												eForm.getRegcompletDto().setPropRinFilePath(filePathRin);
												filePathKhasra=  action.uploadFile(eForm.getHidnRegTxnId(),eForm.getRegcompletDto().getPropKhasraDocContents(),
														eForm.getPropertyId(),eForm.getRegcompletDto().getPropKhasraPartyOrProp(),eForm.getRegcompletDto().getPropKhasraUploadType());
												if(filePathKhasra!=null){
													eForm.getRegcompletDto().setPropKhasraFilePath(filePathKhasra);
													propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
													
													
													
												}
												
												
											}
											
											
										}else if(filePathMap!=null){
											
											eForm.getRegcompletDto().setPropMapFilePath(filePathMap);
											propertyDetailsInserted = commonBo.insertPropertyDetails(regInitForm,eForm);
										}
										
										
									}
									
									
								}
								
							}
							
							
							if(propertyDetailsInserted){

								  regInitForm.setPropertyId("");
								  eForm.setPropertyId("");
								  
								  logger.debug("index----->"+index);
								  
						eForm.getExchangePropertyList().remove(index);
						
						if(eForm.getExchangePropertyList().size()==1){
							eForm.setAllPropertiesAdded(1);
							eForm.setIsPropertyRemaining(0);
						}else
						{
							eForm.setIsPropertyRemaining(1);
						}
						
						
		            	RegCompletDTO comDto= new RegCompletDTO();
		            	eForm.setRegcompletDto(comDto);
		            	eForm.setActionName("");
						eForm.setPropertySelected(0);
						forwardJsp ="propdetails";
						eForm.setActionName("");
						eForm.getRegcompletDto().getKhasraDetlsDisplay().add(new CommonDTO());
									  }
									  else{
										  eForm.setHidnRegTxnId(null);
										  forwardJsp ="propdetails";
									  }
							
							
							
							
					}
						if(RegInitConstant.CANCEL_ACTION.equals(actionName)) {
							
							PropertyValuationDTO dto1= new PropertyValuationDTO();
			            	RegCompletDTO comDto= new RegCompletDTO();
			            	eForm.setRegcompletDto(comDto);
			            	eForm.setPropertyDTO(dto1);
							//CommonAction.cancelAction(session,regInitForm);
							forwardJsp = "welcome";
						}
						
						
						eForm.setActionName("");
					
					}
				 
			 	request.setAttribute("reginitproperty", eForm);
				 
			
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			forwardJsp = "Failure";
		}
		
		return mapping.findForward(forwardJsp);
	}

	
/*	private void resetValue(PropertyValuationDTO dto,RegCompletDTO rdto) {
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
		
	}*/
/*	private String[] getSubClauses(String _subClause) {
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
	}*/

}

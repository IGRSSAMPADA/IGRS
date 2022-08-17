package com.wipro.igrs.suppleDoc.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.newgen.burning.BurningImageAndText;
import com.newgen.burning.Dataclass;
import com.newgen.burning.DocumentOperations;
import com.newgen.burning.ODServerDetails;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.Seals.constant.SealsConstant;
import com.wipro.igrs.auditinspection.rule.AuditInspectionRule;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.AuditInspectionConstant;
import com.wipro.igrs.reginit.bo.RegCommonBO;
import com.wipro.igrs.suppleDoc.bd.SuppleDocBD;
import com.wipro.igrs.suppleDoc.bo.SuppleDocBO;
import com.wipro.igrs.suppleDoc.constant.SuppleDocConstant;
import com.wipro.igrs.suppleDoc.dao.SuppleDocDAO;
import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;
import com.wipro.igrs.suppleDoc.form.SuppleDocForm;
import com.wipro.igrs.util.PropertiesFileReader;
public class SuppleDocAction extends BaseAction {
    /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	public String counter = "";
	public HashMap<String, SuppleDocDTO> map=null;;
	public String counterPStamp = "";
	public HashMap<String, SuppleDocDTO> mapPStamp=null;;
	public String counterEStamp = "";
	public HashMap<String, SuppleDocDTO> mapEStamp=null;;
	private Logger logger = 
		(Logger) Logger.getLogger(SuppleDocAction.class);
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response, HttpSession session) throws IOException, 
                                                                      ServletException,Exception {
    	String FORWAD_SUCCESS="";
    	
    	//HttpSession session = request.getSession(true);
    	logger.debug("WE ARE IN SuppleDocAction Debug");
		logger.info("WE ARE IN  SuppleDocAction INFO"); 
		String roleId = (String) session.getAttribute("roleId");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		String officeId = (String)session.getAttribute("loggedToOffice"); 
		String language = (String)session.getAttribute("languageLocale");
		
		
		SuppleDocForm fm;
		
		if(form!=null){
			fm = (SuppleDocForm) form;
			if(fm.getSuppleDocDTO()==null)
			{
				fm.setSuppleDocDTO(new SuppleDocDTO());
			}
		}else{
			fm=new SuppleDocForm();
		}
		
        if(form!=null)
        {
        	
        	fm.setLanguage(language);
        	
        	
        	try{
	        	//SuppleDocForm fm = (SuppleDocForm) form;
	        	SuppleDocBD sdBD=new SuppleDocBD();
	        	String frwdName=request.getParameter("fwdName");
	        	String resumeName=request.getParameter("actionResume");
	        	String display = request.getParameter("display");
	        	String displayNext = request.getParameter("displayNext");
	        	SuppleDocDTO sdDTO=fm.getSuppleDocDTO();
	        	SuppleDocBO refBo = new SuppleDocBO();
	        	if(!((String)request.getParameter("frm")==null || (String)request.getParameter("axn")==null))
            	{
	        		sdDTO.setActionName(request.getParameter("axn"));
	        		sdDTO.setFormName(request.getParameter("frm"));
            	}
	        	
	        	if(!((String)request.getParameter("repeatPage")==null ))
            	{
	        		if("done".equalsIgnoreCase((String)request.getParameter("repeatPage")))
	        				{
	        			
	        			
	        			logger.info("Inside searchDetails Action");
	            		ArrayList  searchList=new ArrayList ();
	            		sdDTO.setFormName(null);
	            		sdDTO.setActionName(null);
	            		
		        		searchList=sdBD.searchListViewBD(sdDTO.getSearchFiling(),sdDTO.getSearchFromDate(),sdDTO.getSearchToDate(),officeId);
		        		if(searchList.isEmpty())
		        		{
		        			sdDTO.setErrorMsg("Records are not found");
		        		}
		        		else
		        		{
		        			sdDTO.setErrorMsg(null);
		        			session.setAttribute("docSearchDetails",searchList);
		        		}
		        		FORWAD_SUCCESS="searchResultScreen";
	        			
	        			
	        			
	        			
	        			
	        			
	        			
	        			
	        				}
            	}
	        	String formName="";
	        	String actionName="";
	        	if(sdDTO!=null)
	        	{
	        	 formName=sdDTO.getFormName();
	        	actionName=sdDTO.getActionName();
	        	}
	        	logger.debug("FORM NAME"+formName);
	        	logger.debug("display"+display);
	        	logger.debug("Forward Name"+frwdName);
	        	logger.debug("Action Name"+actionName);
	        	
	        //	String crntUser=(String)request.getSession().getAttribute("user_id");
            	if(userId==null || "".equalsIgnoreCase(userId))
            		userId="igrs";
            	if(sdDTO!=null)
            	sdDTO.setCurrentUserId(userId);
            	
            	if(resumeName!=null&&(resumeName.equalsIgnoreCase("resume")||resumeName.equalsIgnoreCase("")))
            	{
            		formName = "";
            		actionName = "";
            		sdDTO.setActionName(null);
    				sdDTO.setFormName(null);	
            		
            		String combinedata = request.getParameter("combineData");
            		String combinedatas[] = combinedata.split("~");
            		String refernceid = combinedatas[0] ; 
            		String cases = combinedatas[1];
            		String pageno=combinedatas[2];
            		String caseType = combinedatas[3];
            		
            		if("1".equalsIgnoreCase(pageno))
            		{
            			
            			sdDTO.setDistListProperty(sdBD.districtStackBD("1","Property",fm.getLanguage()));
            			sdDTO.setAreaTypeList(sdBD.getAreaType(fm.getLanguage()));
                		sdDTO.setMunicipalList(refBo.getMunicipalBody(fm.getLanguage()));
    					sdDTO.setPropertyTypeList(refBo.getPropertyType(fm.getLanguage()));
            			sdDTO.setPurposeList(sdBD.purposeListBD("1",fm.getLanguage()));
                		sdDTO.setPurposeList1(sdBD.purposeListBD("",fm.getLanguage()));
                		sdDTO.setStampDutyNow(0.0);
                		
                		
                		
                		
                		sdDTO.setOfficeId(officeId);
                		sdDTO.setReferenceId(refernceid);
                		sdDTO.setUserId(userId);
                		
                		
                	
            			
            			
            			
            			FORWAD_SUCCESS="PartialPage2";
            			
            		}
            		else if("2".equalsIgnoreCase(pageno))
            		{
            			sdDTO.setDistListProperty(sdBD.districtStackBD("1","Property",fm.getLanguage()));
            			sdDTO.setAreaTypeList(sdBD.getAreaType(fm.getLanguage()));
                		sdDTO.setMunicipalList(refBo.getMunicipalBody(fm.getLanguage()));
    					sdDTO.setPropertyTypeList(refBo.getPropertyType(fm.getLanguage()));
    					
    					
    					sdDTO.setOfficeId(officeId);
                		sdDTO.setReferenceId(refernceid);
                		sdDTO.setUserId(userId);
                		
                		
                		
                		ArrayList data = sdBD.getDtoData(refernceid);
                		
                		ArrayList pendingData = (ArrayList)data.get(0);
                		
                		sdDTO.setStampDutyNow(Double.valueOf((String) pendingData.get(1)));
                	//	sdDTO.setStampDuty(Double.valueOf((String) pendingData.get(0)));
                    		sdDTO.setStampDuty((String) pendingData.get(0));///vinay

                		
                		//logger.debug("Stamp Duty Paid"+sdDTO.getStampDutyNow());
                	//	logger.debug("Stamp Duty To be Paid"+sdDTO.getStampDuty());
                		
            			FORWAD_SUCCESS="PartialPage3";
            		}
            		else if("3".equalsIgnoreCase(pageno))
            		{
            			sdDTO.setDistListProperty(sdBD.districtStackBD("1","Property",fm.getLanguage()));
            			sdDTO.setOfficeId(officeId);
                		sdDTO.setReferenceId(refernceid);
                		sdDTO.setUserId(userId);
            			
                		ArrayList data = sdBD.getDtoData(refernceid);
                		
                		ArrayList pendingData = (ArrayList)data.get(0);
                		
                		sdDTO.setStampDutyNow(Double.valueOf((String) pendingData.get(1)));
                		//sdDTO.setStampDuty(Double.valueOf((String) pendingData.get(0)));
                		sdDTO.setStampDuty((String) pendingData.get(0));//vinay

                		ArrayList disData =sdBD.getDistrictId(refernceid);
                		ArrayList disDatas = (ArrayList)disData.get(0);
                		
                		sdDTO.setDistrictIDProperty((String)disDatas.get(0));
                		//logger.debug("Stamp Duty Paid"+sdDTO.getStampDutyNow());
                		//logger.debug("Stamp Duty To be Paid"+sdDTO.getStampDuty());
                		//logger.debug("District Id of property"+sdDTO.getDistrictIDProperty());
                		
            			String username = sdBD.getUserName(sdDTO.getUserId());
            			String srname[] = username.split(",");
            			String sroName =  sdBD.getSROName(officeId,language);
            			sdDTO.setSrName(srname[0]);
            			sdDTO.setPresentDate(srname[1]);
            			sdDTO.setSroName(sroName);
            			sdDTO.setBookTypeList(refBo.getBookTypeList());
            			sdDTO.setBookTypeId("4");
            			sdDTO.setBookTypeName("4");
            			sdDTO.setThumb("ScannedDoc.pdf");
            			sdDTO.setThumbPath("D:/Upload/91/SuppleDoc/");
            			 Date date = new Date();
							  Format yearformat  = new SimpleDateFormat("yy");
							  Format monthformat = new SimpleDateFormat("MM");
							  Format dateformat  = new SimpleDateFormat("dd");
							  Format hourformat  = new SimpleDateFormat("hh");
							  Format minformat  = new SimpleDateFormat("mm");
							  Format secformat  = new SimpleDateFormat("ss");
							  Format ampmformat  = new SimpleDateFormat("a");
							  String dfmt = dateformat.format(date);
							  String yfmt = yearformat.format(date);
							  String mfmt = monthformat.format(date);
							  String hfmt = hourformat.format(date);
							  String mifmt = minformat.format(date);
							  String sfmt = secformat.format(date);
							  String ampmfmt = ampmformat.format(date);
							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
            			sdDTO.setUniqueId(fldrnm);
            			
            			FORWAD_SUCCESS="submitPage";
            			
            			
            			
            			
            		}
            		else
            		{

    					sdDTO.setOfficeId(officeId);
                		sdDTO.setReferenceId(refernceid);
                		sdDTO.setUserId(userId);
                		
                		ArrayList data = sdBD.getDtoData(refernceid);
                		
                		ArrayList pendingData = (ArrayList)data.get(0);
                		String dataS = (String) pendingData.get(1);
                		if(dataS==null)
                		{
                			dataS="0.0";
                		}
                		String dataS1 =(String) pendingData.get(0);
                		if(dataS1==null)
                		{
                			dataS1="0.0";
                		}
                		sdDTO.setStampDutyNow(Double.valueOf(dataS1));
                	//	sdDTO.setStampDuty(Double.valueOf(dataS));
                		sdDTO.setStampDuty((dataS));//vinay
                		
                		String username = sdBD.getUserName(sdDTO.getUserId());
            			String srname[] = username.split(",");
            			String sroName =  sdBD.getSROName(officeId,language);
            			sdDTO.setSrName(srname[0]);
            			sdDTO.setPresentDate(srname[1]);
            			sdDTO.setSroName(sroName);
                		
            		//	System.out.println("Cheheheheheh"+refernceid);
            			SuppleDocDTO fulldetails = sdBD.getTotalDetailsReferenceBD(refernceid,fm.getLanguage());
            			fm.setSuppleDocDTO(fulldetails);
            			request.setAttribute("partyDetails", fulldetails.getPartyDetails());
            			//System.out.println(username+" "+(String) sroName);
            			FORWAD_SUCCESS="ViewRefer1";
            		}
            		
            	}
            	
            	if(frwdName!=null&&(frwdName.equalsIgnoreCase("Create1")||frwdName.equalsIgnoreCase("")))
            	{
            		
            		//--Page 1--//
            		sdDTO.setRole("");
            		sdDTO.setFname("");
            		sdDTO.setMname("");
            		sdDTO.setLname("");
            		sdDTO.setFatherName("");
            		sdDTO.setMotherName("");
            		sdDTO.setOfficeId(officeId);
            		sdDTO.setMobNo("");
            		sdDTO.setPhoneNo("");
            		sdDTO.setMailId("");
            		sdDTO.setPostalCode(0);
            		sdDTO.setAddress("");
            		sdDTO.setAge(0);
            		sdDTO.setBankPersonMob("");
            		sdDTO.setBankName("");
            		sdDTO.setDocumentName("");
            		sdDTO.setOtherId("0");
            		sdDTO.setDistrictName("");
            		sdDTO.setBankDistrictName("");
            		sdDTO.setBankAddress("");
            		sdDTO.setOtherDocument("");
            		sdDTO.setBankPostalCode(0);
            		sdDTO.setBankPersonDesi("");
            		sdDTO.setBankPhone("");
            		sdDTO.setBankPersonEmail("");
            		sdDTO.setBankPersonName("");
            		sdDTO.setExecuteDate("");
            		sdDTO.setPresentDate("");
            		sdDTO.setTransactionAmount(0.0);
            		fm.setPartyDetails(new HashMap());
            		sdDTO.setReferenceId("");
            		sdDTO.setErrorDate("0");
            		
            		//--Page-2--//
            		sdDTO.setStampDuty("0.0");
            		sdDTO.setDenominationStamps(0);
            		sdDTO.setCodeStamps("");
            		sdDTO.setSeriesNo("");
            		fm.setPstampDetails(new HashMap());
            		fm.setEstampDetails(new HashMap());
            		sdDTO.setEstampCode("");
            		sdDTO.setEstampDuty("");
            		sdDTO.setEstampPurpose("");
            		sdDTO.setKeysStringPurpose("");
            		sdDTO.setAcqElements("");
            		sdDTO.setDocumentNumber("");
            		sdDTO.setStampDutyNow(0);
            		sdDTO.setPurposeCheckbox("");
            		sdDTO.setExemptions("");
            		sdDTO.setNameVendorPStamps("");
            		sdDTO.setDatePStamps("");
            		//--Page-3--//
            		sdDTO.setDistrictIDProperty("");
            		sdDTO.setTehsilID("");
            		sdDTO.setAreaId("");
            		sdDTO.setWardIds("");
            		sdDTO.setSubAreaId("");
            		sdDTO.setColonyId("");
            		sdDTO.setWardPatwariId("");
            		sdDTO.setWardId("");
            		sdDTO.setMahallaId("");
            		sdDTO.setPropertyTypeId("");
            		sdDTO.setMunicipalBodyID("");
            		sdDTO.setPropTypeL1Id("");
            		sdDTO.setPropTypeL2Id("");
            		sdDTO.setUnitTypeId("");
            		sdDTO.setFloorId("");
            		sdDTO.setMapBuilding(new HashMap());
            		sdDTO.setUnitTypeId("");
            		sdDTO.setAddMoreFloorCounter(0);
            		sdDTO.setArea(0);
            		sdDTO.setAreaConstructed(0);
            		sdDTO.setVikasKhand("");
            		sdDTO.setRicircle("");
            		sdDTO.setNazoolstNum("");
            		sdDTO.setSheetnum("");
            		sdDTO.setLayoutdet("");
            		sdDTO.setPlotnum("");
            		sdDTO.setPropAddress("");
            		sdDTO.setPropLandmark("");
            		sdDTO.setNorth("");
            		sdDTO.setSouth("");
            		sdDTO.setWest("");
            		sdDTO.setEast("");
            		sdDTO.setKhasaraNum1("");
            		sdDTO.setKhasraArea1("");
            		sdDTO.setLagaan("");
            		sdDTO.setRinpustikaNum("");
            		sdDTO.setProptypeflag("");
            		
            		
            		//--Page-4--//
            		sdDTO.setSroName("");
            		sdDTO.setSrName("");
            		sdDTO.setBookTypeId("4");
            		sdDTO.setScannedCopyPath("");
            		sdDTO.setScannedCopyName("");
            		sdDTO.setActionName("");
            		sdDTO.setFormName("");
            		sdDTO.setBookTypeName("4");
            		sdDTO.setImpound("");
            		
            		ArrayList pendingDetails = new ArrayList();
				    pendingDetails= refBo.getPendingDetls(userId,officeId,fm.getLanguage());
					 if (pendingDetails.size()==0){
						 //dto.setNoRecFound(1);
						  }
					 if (pendingDetails.size()>0){
						 sdDTO.setPendingDetails(pendingDetails);
						 request.setAttribute("pendingDetails", pendingDetails);
						}
				formName="";
				actionName="";
				sdDTO.setActionName(null);
				sdDTO.setFormName(null);
            		FORWAD_SUCCESS="dashboardScreen";
            	}
            	
            	
            	if(frwdName!=null&&(frwdName.equalsIgnoreCase("Create")||frwdName.equalsIgnoreCase("")))
            	{
            	
            		formName = "";
            		actionName = "";
            		
            	 	logger.info("Inside COUTNRY,PhotoIdProof,Caste,Religion master retrieve Action");
            		sdDTO.setCountryList(sdBD.countryStackBD("",fm.getLanguage()));
            		sdDTO.setStateList(sdBD.stateStackBD("1","",fm.getLanguage()));
            		sdDTO.setDistrictList(sdBD.districtStackBD("1","",fm.getLanguage()));
            		
            		sdDTO.setDistListProperty(sdBD.districtStackBD("1","Property",fm.getLanguage()));
            		
            		sdDTO.setBankCountryList(sdBD.countryStackBD("BankCountryType",fm.getLanguage()));// For Bank Country
	                sdDTO.setBankStateList(sdBD.stateStackBD("1","BankStateType",fm.getLanguage()));
	                sdDTO.setBankDistrictList(sdBD.districtStackBD("1","BankDistrictType",fm.getLanguage()));
            		
            		
            		
            		sdDTO.setDocumentList(sdBD.documentListBD(fm.getLanguage()));
            		sdDTO.setActionName(null);
    				sdDTO.setFormName(null);
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	
            	
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "State".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside State Action");
            		sdDTO.setStateList(sdBD.stateStackBD(sdDTO.getCountryId(),"",fm.getLanguage()));
            		sdDTO.setDistrictList(null);
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "District".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside District Action");
            		sdDTO.setDistrictList(sdBD.districtStackBD(sdDTO.getStateId(),"",fm.getLanguage()));
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "BankState".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside BankState Action");
            		sdDTO.setBankStateList(sdBD.stateStackBD(sdDTO.getBankCountryId(),"BankState",fm.getLanguage()));
            		sdDTO.setBankDistrictList(null);
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "OtherDropDown".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside OtherField Action");
            		String docSel = sdDTO.getDocumentName();
            		
            		if("4".equalsIgnoreCase(docSel)){
            		sdDTO.setOtherId("4");
            		}
            		else
            		{
            			sdDTO.setOtherId("0");
            		}
            		actionName="";
            		sdDTO.setActionName("");
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "SavePartyDetails".equalsIgnoreCase(actionName))
            	{
            		
            		
            		counter = String.valueOf(sdDTO.getCounter()+1);
            		map = fm.getPartyDetails();
            		
            		
            		
            		SuppleDocDTO ndto = new SuppleDocDTO();
            		ndto.setRole(sdDTO.getRole());
            		
            		ndto.setFname(sdDTO.getFname());
            		ndto.setLname(sdDTO.getLname());
            		ndto.setMname(sdDTO.getMname());
            		
            		ndto.setFatherName(sdDTO.getFatherName());
            		ndto.setMotherName(sdDTO.getMotherName());
            		
            		ndto.setGender(sdDTO.getGender());
            		ndto.setAddress(sdDTO.getAddress());
            		
            		ndto.setCountryId(sdDTO.getCountryId());
            		ndto.setDistrictName(sdDTO.getDistrictName());
            		ndto.setStateId(sdDTO.getStateId());
            			
            		ndto.setAge(sdDTO.getAge());
            		ndto.setPostalCode(sdDTO.getPostalCode());
            		ndto.setPhoneNo(sdDTO.getPhoneNo());
            		ndto.setMobNo(sdDTO.getMobNo());
            		ndto.setMailId(sdDTO.getMailId());
            		
            		map.put(counter, ndto);
            		sdDTO.setCounter(Integer.parseInt(counter));
            		fm.setPartyDetails(map);
            		
            		// Setting them back to null to show empty form//
            		sdDTO.setRole("");
            		sdDTO.setFname("");
            		sdDTO.setMname("");
            		sdDTO.setLname("");
            		sdDTO.setFatherName("");
            		sdDTO.setMotherName("");
            		
            		sdDTO.setMobNo("");
            		sdDTO.setPhoneNo("");
            		sdDTO.setMailId("");
            		sdDTO.setPostalCode(0);
            		sdDTO.setAddress("");
            		sdDTO.setAge(0);
            		
            		sdDTO.setDistrictName("");
            		
            		
            		//System.out.println(ndto.getFname());
            		/*SuppleDocDAO obj = new SuppleDocDAO();
            		String referenceId = obj.getReferenceNumber(SuppleDocConstant.REFERENCE_ID_SEQ_NAME);
            		sdDTO.setUserId((String)session.getAttribute("UserId"));
            		sdDTO.setReferenceId(referenceId);
            		System.out.println(sdBD.insertSuppliPartyDetailsBD(sdDTO));
            		*/
            		actionName="";
            		sdDTO.setActionName("");
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("clearEstampValues".equalsIgnoreCase(actionName) && "estampPopupForm".equalsIgnoreCase(formName)){
            		
            		System.out.println("0");
            		
            	}
            	else if("clearEstampValues1".equalsIgnoreCase(actionName) && "estampPopupForm".equalsIgnoreCase(formName)){
            		
            		System.out.println("0");
            		
            	}
            	
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "deletePartyDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside deletePartyDetailst Action");
            		
            		String deleteKeys[] = fm.getKeysString().split(",");
            		map = fm.getPartyDetails();
            		for(int i = 0; i<deleteKeys.length;i++)
					{
						if(map.containsKey(deleteKeys[i]))
						{
							map.remove(deleteKeys[i]);
						}
					}
					logger.debug("Size of map after deletion"+map.size());
					fm.setPartyDetails(map);
					actionName="";
            		sdDTO.setActionName("");
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "ClearPartialSave1".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside Clear Action");
            		
            		
					fm.setPartyDetails(new HashMap());
					sdDTO.setDocumentName("");
					sdDTO.setRole("");
					sdDTO.setFname("");
					sdDTO.setFatherName("");
					sdDTO.setAge(0);
					sdDTO.setMotherName("");
					sdDTO.setLname("");
					sdDTO.setMname("");
					sdDTO.setAddress("");
					sdDTO.setDistrictName("");
					sdDTO.setMobNo("");
					sdDTO.setPhoneNo("");
					sdDTO.setMailId("");
					sdDTO.setBankAddress("");
					sdDTO.setBankName("");
					sdDTO.setBankDistrictName("");
					sdDTO.setBankPostalCode(0);
					sdDTO.setBankPhone("");
					sdDTO.setBankPersonName("");
					sdDTO.setBankPersonDesi("");
					sdDTO.setBankPersonEmail("");
					sdDTO.setBankPersonMob("");
					sdDTO.setTransactionAmount(0);
					sdDTO.setExecuteDate("");
					sdDTO.setPresentDate("");
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
            		sdDTO.setPostalCode(0);
            		sdDTO.setErrorDate("");
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "CheckDate".equalsIgnoreCase(actionName))
            	{
            		int check = sdBD.dateComparison(sdDTO.getPresentDate(),sdDTO.getExecuteDate());
            		
            		if(check<0)
            		{
            			sdDTO.setErrorDate("1");
            			FORWAD_SUCCESS="CreateScreen";
            		}
            		else if(check>30)
            		{
            			sdDTO.setErrorDate("2");
            			FORWAD_SUCCESS="CreateScreen";
            		}
            		else
            		{
            			sdDTO.setErrorDate("0");
            			FORWAD_SUCCESS="PartialSave1";
            		}
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "PartialSave1".equalsIgnoreCase(actionName))
            	{
            		sdDTO.setErrorDate("");
            		Date sysDate = new Date();
            	String	strOutDt = new SimpleDateFormat("dd/MM/yyyy").format(sysDate);
            		int check = sdBD.dateComparison(sdDTO.getPresentDate(),sdDTO.getExecuteDate());
            		int checkFirst = sdBD.dateComparison(sdDTO.getPresentDate(), strOutDt);
            		int checkSecond = sdBD.dateComparison(sdDTO.getExecuteDate(), strOutDt);
            		String maxDays = sdBD.getConfigParamter(); 
            		int days=0;
            	
            		if(maxDays!=null & maxDays!="")
            		{
            			days = Integer.parseInt(maxDays);
            		}
            		else
            		{
            			days = 30;
            		}
            		sdDTO.setDays(days);
            	if(checkFirst>0 ||checkSecond>0)
            		{
            			sdDTO.setErrorDate("5");
            			sdDTO.setFormName(null);
                		sdDTO.setActionName(null);
            			FORWAD_SUCCESS="CreateScreen";
            		}
            	else
            	{
            		if(check<0)
            		{
            			sdDTO.setErrorDate("1");
            			sdDTO.setFormName(null);
                		sdDTO.setActionName(null);
            			FORWAD_SUCCESS="CreateScreen";
            		}
            		else if(check>days)
            		{
            			sdDTO.setErrorDate("2");
            			sdDTO.setFormName(null);
                		sdDTO.setActionName(null);
            			FORWAD_SUCCESS="CreateScreen";
            		}
            		else
            		{
            			sdDTO.setErrorDate("0");
            			FORWAD_SUCCESS="PartialPage2";
            		
            		SuppleDocDAO obj = new SuppleDocDAO();
            		String referenceId = obj.getReferenceNumber(SuppleDocConstant.REFERENCE_ID_SEQ_NAME);
            		sdDTO.setUserId((String)session.getAttribute("UserId"));
            		sdDTO.setReferenceId(referenceId);
            		sdDTO.setBankCountryName("1");
            		sdDTO.setBankStateName("1");
            		
            		
            		map = fm.getPartyDetails();
            		counter = String.valueOf(sdDTO.getCounter()+1);
            		
            		
            		
            		
            		SuppleDocDTO ndto = new SuppleDocDTO();
            		ndto.setRole(sdDTO.getRole());
            		
            		ndto.setFname(sdDTO.getFname());
            		ndto.setLname(sdDTO.getLname());
            		ndto.setMname(sdDTO.getMname());
            		
            		ndto.setFatherName(sdDTO.getFatherName());
            		ndto.setMotherName(sdDTO.getMotherName());
            		
            		ndto.setGender(sdDTO.getGender());
            		ndto.setAddress(sdDTO.getAddress());
            		
            		
            		
            		ndto.setCountryId(sdDTO.getCountryId());
            		ndto.setDistrictName(sdDTO.getDistrictName());
            		ndto.setStateId(sdDTO.getStateId());
            		
            			
            		ndto.setAge(sdDTO.getAge());
            		ndto.setPostalCode(sdDTO.getPostalCode());
            		ndto.setPhoneNo(sdDTO.getPhoneNo());
            		ndto.setMobNo(sdDTO.getMobNo());
            		ndto.setMailId(sdDTO.getMailId());
            		
            		
            		
            		map.put(counter, ndto);
            		sdDTO.setCounter(Integer.parseInt(counter));
            		fm.setPartyDetails(map);
            		
            		
            		map = fm.getPartyDetails();
            		sdDTO.setOfficeId(officeId);
            		sdBD.partialSave1(sdDTO,map);
            		//Setting second page variables
            		sdDTO.setPurposeList(sdBD.purposeListBD("1",fm.getLanguage()));
            		sdDTO.setPurposeList1(sdBD.purposeListBD("",fm.getLanguage()));
            		sdDTO.setStampDutyNow(0.0);
            		sdDTO.setFormName("detailsCreateForm2");
            		
            		sdDTO.setFormName(null);
            		sdDTO.setActionName(null);
            		
            		FORWAD_SUCCESS="PartialPage2";
            		}
            		
            	}
            		
            		logger.debug(sdDTO.getErrorDate());
            	sdDTO.setFormName(null);
        		sdDTO.setActionName(null);
            			
            		
            		//
            		
            		
            	}
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "PartialSave2".equalsIgnoreCase(actionName))
            	{
            		mapPStamp = fm.getPstampDetails();
            		mapEStamp = fm.getEstampDetails();
            		
            		
            		
            	boolean check = sdBD.partialSave2(sdDTO,mapPStamp,mapEStamp);
            		//Setting second page variables
            	sdDTO.setFormName(null);
        		sdDTO.setActionName(null);	
            	
            		sdDTO.setAreaTypeList(sdBD.getAreaType(fm.getLanguage()));
            		sdDTO.setMunicipalList(refBo.getMunicipalBody(fm.getLanguage()));
					sdDTO.setPropertyTypeList(refBo.getPropertyType(fm.getLanguage()));
					sdDTO.setPropertyTypeList(refBo.getPropertyType(fm.getLanguage()));
					FORWAD_SUCCESS="PartialPage3";
					
            	}
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "ClearPartialSave2".equalsIgnoreCase(actionName))
            	{
            		
            		
            		fm.setPstampDetails(new HashMap());
            		fm.setEstampDetails(new HashMap());
            		sdDTO.setAcqElements("");
            		sdDTO.setStampDuty("0");
            		sdDTO.setDenominationStamps(0.0);
            		sdDTO.setCodeStamps("");
            		sdDTO.setSeriesNo("");
            		sdDTO.setDocumentNumber("");
            		sdDTO.setKeysStringPurpose("");
            		sdDTO.setPurposeCheckbox("");
            		sdDTO.setStampDutyNow(0);
            		sdDTO.setEstampCode("");
            		sdDTO.setEstampCodeSearch("");
            		sdDTO.setEstampDuty("");
            		sdDTO.setEstampPurpose("");
            		FORWAD_SUCCESS="PartialPage2";
            	}
            	
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "SavePStampDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside Physical Stamps Action");
            		
            		counterPStamp = String.valueOf(sdDTO.getCounterPStamp()+1);
            		mapPStamp = fm.getPstampDetails();
            		
            		SuppleDocDTO ndto = new SuppleDocDTO();
            		ndto.setDenominationStamps(sdDTO.getDenominationStamps());
            		ndto.setCodeStamps(sdDTO.getCodeStamps());
            		ndto.setSeriesNo(sdDTO.getSeriesNo());
            		ndto.setNameVendorPStamps(sdDTO.getNameVendorPStamps());
            		ndto.setDatePStamps(sdDTO.getDatePStamps());
            		sdDTO.setStampDutyNow(sdDTO.getStampDutyNow()+sdDTO.getDenominationStamps());
            		
            		
            		mapPStamp.put(counterPStamp, ndto);
            		sdDTO.setCounterPStamp(Integer.parseInt(counterPStamp));
            		fm.setPstampDetails(mapPStamp);
            		
            		sdDTO.setDenominationStamps(0.0);
            		sdDTO.setCodeStamps("");
            		sdDTO.setSeriesNo("");
            		sdDTO.setDatePStamps("");
            		sdDTO.setNameVendorPStamps("");
            		
            		sdDTO.setFormName(null);
            		sdDTO.setActionName(null);	
            		
            		
            		FORWAD_SUCCESS="PartialPage2";
            	}
            	
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "SaveEStampDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside E-Stamps Stamps Action");
            		
            		counterEStamp = String.valueOf(sdDTO.getCounterEStamp()+1);
            		mapEStamp = fm.getEstampDetails();
            	
            		SuppleDocDTO ndto = new SuppleDocDTO();
            		ndto.setEstampCode(sdDTO.getEstampCode());
            		ndto.setEstampDuty(sdDTO.getEstampDuty());
            		ndto.setEstampPurpose(sdDTO.getEstampPurpose());
            		sdDTO.setStampDutyNow(sdDTO.getStampDutyNow()+Double.parseDouble(sdDTO.getEstampDuty()));
            		sdBD.setStatusq(sdDTO.getEstampCode());
            		
            		
            		mapEStamp.put(counterEStamp, ndto);
            		sdDTO.setCounterEStamp(Integer.parseInt(counterEStamp));
            		fm.setEstampDetails(mapEStamp);
            		
            	sdDTO.setEstampCode("");
            	sdDTO.setEstampCodeSearch("");
            	sdDTO.setEstampDuty("");
            	sdDTO.setEstampPurpose("");
            		
            	sdDTO.setFormName(null);
        		sdDTO.setActionName(null);	
            	
            	
            		FORWAD_SUCCESS="PartialPage2";
            	}
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "deletePStampDetail".equalsIgnoreCase(actionName))
            	{
            		
            		logger.info("Inside deletePStampDetail Action");
            		double value=0.0;
            		double valueStamps = sdDTO.getStampDutyNow();
            		String deleteKeys[] = fm.getKeysStringPStamp().split(",");
            		
            		mapPStamp = fm.getPstampDetails();
            		for(int i = 0; i<deleteKeys.length;i++)
					{
						if(mapPStamp.containsKey(deleteKeys[i]))
						{
							value = value + mapPStamp.get(deleteKeys[i]).getDenominationStamps();
							
							mapPStamp.remove(deleteKeys[i]);
						}
					}
					logger.debug("Size of map after deletion"+mapPStamp.size());
					
					valueStamps = valueStamps-value;
					sdDTO.setStampDutyNow(valueStamps);
					fm.setPstampDetails(mapPStamp);
					
					
					sdDTO.setFormName(null);
	        		sdDTO.setActionName(null);	
					
            		FORWAD_SUCCESS="PartialPage2";
            	}
            	
            	
            	else if("detailsCreateForm2".equalsIgnoreCase(formName)&& "deleteEStampDetail".equalsIgnoreCase(actionName))
            	{
            		
            		logger.info("Inside deleteEStampDetail Action");
            		double value=0.0;
            		double valueStamps = sdDTO.getStampDutyNow();
            		String deleteKeys[] = fm.getKeysStringEStamp().split(",");
            		
            		mapEStamp = fm.getEstampDetails();
            		for(int i = 0; i<deleteKeys.length;i++)
					{
						if(mapEStamp.containsKey(deleteKeys[i]))
						{
							value = value + Double.parseDouble(mapEStamp.get(deleteKeys[i]).getEstampDuty());
							String code = mapEStamp.get(deleteKeys[i]).getEstampCode();
							
							sdBD.setStatusN(code);
							
							mapEStamp.remove(deleteKeys[i]);
						}
					}
					logger.debug("Size of map after deletion"+mapEStamp.size());
					
					valueStamps = valueStamps-value;
					sdDTO.setStampDutyNow(valueStamps);
					fm.setEstampDetails(mapEStamp);
					
					sdDTO.setFormName(null);
	        		sdDTO.setActionName(null);	
					
            		FORWAD_SUCCESS="PartialPage2";
            	}
            	
            	
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "BankDistrict".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside BankDistrict Action");
            		sdDTO.setBankDistrictList(sdBD.districtStackBD(sdDTO.getBankStateId(),"BankDistrict",fm.getLanguage()));
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("estampPopupForm".equalsIgnoreCase(formName)&& "EstampSearch".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside EstampSearch Action");
            		
            		sdBD.estampCheckBD(sdDTO,fm.getLanguage());
            		
            		FORWAD_SUCCESS="PopupScreen";
            	}
            	
            	else if("confirmForm".equalsIgnoreCase(formName)&& "modifyDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside modifyDetails Action");
            		//----------Setting all Master
            		sdDTO.setCountryList(sdBD.countryStackBD("",fm.getLanguage()));
            		sdDTO.setStateList(sdBD.stateStackBD(sdDTO.getCountryId(),"",fm.getLanguage()));
            		sdDTO.setDistrictList(sdBD.districtStackBD(sdDTO.getStateId(),"",fm.getLanguage()));
	                sdDTO.setBankCountryList(sdBD.countryStackBD("BankCountry",fm.getLanguage()));// For Bank Country
            		
            		
            		//-----------End of All
            		
            		fm.setSuppleDocDTO((SuppleDocDTO)session.getAttribute("suppleDetails"));
            		session.setAttribute("frwdedByEdit", "yes");
            		FORWAD_SUCCESS="CreateScreen";
            	}
            	else if("confirmForm".equalsIgnoreCase(formName)&& "saveDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside saveDetails Action");
            		String propertId=(String)session.getAttribute("propTxnNo");
            		sdDTO.setPropertyTxnId(propertId);
            		if(sdBD.insertSuppliDetailsBD(sdDTO))
            			FORWAD_SUCCESS="submitSuccess";
            		else
            			FORWAD_SUCCESS="failure";
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
            	}
            	else if("viewDetails3Form".equalsIgnoreCase(formName)&& "downloadPic".equalsIgnoreCase(actionName))
            	{
            		String filePath=sdDTO.getScannedCopyPath()+"/"+sdDTO.getScannedCopyName();
	    		//	logger.debug("retrieval path of sign-->"+filePath);
	    			/*DMSUtility dmsUtil=new DMSUtility();
	    			
	    			DMSUtility.downloadDocument(response, filePath, dmsUtil.getImageBytes(filePath));*/
            		  response.setContentType("application/download");
      	    		//	logger.debug("retrieval path of sign-->"+filePath);
      	    			/*DMSUtility dmsUtil=new DMSUtility();
      	    			
      	    			DMSUtility.downloadDocument(response, filePath, dmsUtil.getImageBytes(filePath));*/
                  		  response.setHeader("Content-Disposition", "attachment; filename="
            						+ URLEncoder.encode(filePath,"UTF-8"));
            			   
            			   File fileToDownload = new File(filePath);
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
	    			
	    			sdDTO.setActionName(null);
	    			sdDTO.setFormName(null);
	    			actionName="";
	    			formName="";
	    			FORWAD_SUCCESS="viewPropertyPopup";
            		
            	}
            	else if("viewDetails3Form".equalsIgnoreCase(formName)&& "returnToView".equalsIgnoreCase(actionName))
            	{
            		request.setAttribute("partyDetails", sdDTO.getPartyDetails());
            		sdDTO.setKhasraDetlsDisplay(new ArrayList());
        			
        		
        			
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
	    			actionName="";
	    			formName="";
	    			FORWAD_SUCCESS="viewScreen";
            		
            	}
            	else if("ViewReferPage3".equalsIgnoreCase(formName)&& "downloadPicRefer".equalsIgnoreCase(actionName))
            	{
            		String filePath=sdDTO.getScannedCopyPath()+"/"+sdDTO.getScannedCopyName();
            		  response.setContentType("application/download");
	    		//	logger.debug("retrieval path of sign-->"+filePath);
	    			/*DMSUtility dmsUtil=new DMSUtility();
	    			
	    			DMSUtility.downloadDocument(response, filePath, dmsUtil.getImageBytes(filePath));*/
            		  response.setHeader("Content-Disposition", "attachment; filename="
      						+ URLEncoder.encode(filePath,"UTF-8"));
      			   
      			   File fileToDownload = new File(filePath);
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
	    			
	    			sdDTO.setActionName(null);
	    			sdDTO.setFormName(null);
	    			actionName="";
	    			formName="";
	    			FORWAD_SUCCESS="ViewRefer2";
            		
            	}
            	
            	else if("ViewReferPage3".equalsIgnoreCase(formName)&& "confrimGenerate".equalsIgnoreCase(actionName))
            	{
	    			
	    			sdDTO.setActionName(null);
	    			sdDTO.setFormName(null);
	    			actionName="";
	    			formName="";
	    			FORWAD_SUCCESS="ViewRefer2";
            		
            	}
            	
            	
            	else if("ViewReferPage3".equalsIgnoreCase(formName)&& "returnToViewRefer".equalsIgnoreCase(actionName))
            	{
            		request.setAttribute("partyDetails", sdDTO.getPartyDetails());
            		sdDTO.setKhasraDetlsDisplay(new ArrayList());
        			
        		
        			
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
	    			actionName="";
	    			formName="";
	    			FORWAD_SUCCESS="ViewRefer1";
            		
            	}
            	
            	else if("confirmForm".equalsIgnoreCase(formName)&& "PropertyPageForward".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside PropertyPageForward Action");
            		
            		SuppleDocBO a = new SuppleDocBO();
            		String refId = a.getReferenceId(sdDTO.getFilingNumber());
            		a.getPropertyDetails(refId, sdDTO,fm.getLanguage());
            		fm.setSuppleDocDTO(sdDTO);
            			FORWAD_SUCCESS="viewPropertyPopup";
            		
            			//FORWAD_SUCCESS="failure";
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
            	}
            	else if("ViewReferPage2".equalsIgnoreCase(formName)&& "PropertyPageForwardReference".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside PropertyPageForwardReference Action");
            		
            		SuppleDocBO a = new SuppleDocBO();
            		//String refId = a.getReferenceId(sdDTO.getFilingNumber());
            		
            		
            	sdDTO =	a.getPropertyDetails(sdDTO.getReferenceId(), sdDTO,fm.getLanguage());
            		fm.setSuppleDocDTO(sdDTO);
            		request.setAttribute("partyDetails", sdDTO.getPartyDetails());
            			FORWAD_SUCCESS="ViewRefer2";
            		
            			//FORWAD_SUCCESS="failure";
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
            	}
            	else if((frwdName!=null && frwdName.equalsIgnoreCase("View"))|| 
            			("searchResultForm".equalsIgnoreCase(formName)&& "backToSearch".equalsIgnoreCase(actionName))
            			||("confirmForm".equalsIgnoreCase(formName)&& "backToSearchResult".equalsIgnoreCase(actionName)))
            	{
            		logger.info("Inside View Action");
            		FORWAD_SUCCESS="viewMainScreen";
            	}
            	else if("searchViewForm".equalsIgnoreCase(formName)&& "searchDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside searchDetails Action");
            		ArrayList  searchList=new ArrayList ();
            		sdDTO.setFormName(null);
            		sdDTO.setActionName(null);
            		
	        		searchList=sdBD.searchListViewBD(sdDTO.getSearchFiling(),sdDTO.getSearchFromDate(),sdDTO.getSearchToDate(),officeId);
	        		if(searchList.isEmpty())
	        		{
	        			if(language.equalsIgnoreCase("en"))
	        			sdDTO.setErrorMsg("Records are not found");
	        			else
	        				sdDTO.setErrorMsg("रिकार्ड नहीं पाए गये है");
	        		}
	        		else
	        		{
	        			sdDTO.setErrorMsg(null);
	        			session.setAttribute("docSearchDetails",searchList);
	        		}
	        		FORWAD_SUCCESS="searchResultScreen";
            	}
            
            	else if("searchViewForm".equalsIgnoreCase(formName)&& "clearForToDate".equalsIgnoreCase(actionName))
            	{
            		
            		logger.info("Inside searchDetails Action");
            		sdDTO.setActionName(null);
            		sdDTO.setFormName(null);
            		sdDTO.setSearchFromDate("");
            		sdDTO.setSearchToDate("");
	        		FORWAD_SUCCESS="viewMainScreen";
            	}
            	
            	
            	
            	else if("searchViewForm".equalsIgnoreCase(formName)&& "SetDataCheckbox".equalsIgnoreCase(actionName))
            	{
            		
            		
	        		FORWAD_SUCCESS="viewMainScreen";
            	}
            	
            	
            	else if("viewResultForm".equalsIgnoreCase(formName)&& "completeView".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside completeView Action");
            		String suppTxnId=request.getParameter("supplementaryId");
            		sdDTO=sdBD.getTotalDetailsBD(suppTxnId,fm.getLanguage());
            		if(sdDTO!=null)
            		{
            			session.setAttribute("propTxnNo", sdDTO.getPropertyTxnId());
            			request.setAttribute("partyDetails", sdDTO.getPartyDetails());
            			request.setAttribute("estampDetails", sdDTO.getEstampDetails());
            			request.setAttribute("pstampDetails", sdDTO.getPstampDetails());
            			request.setAttribute("exemptions", sdDTO.getExemptions());
            			fm.setSuppleDocDTO(sdDTO);
            			FORWAD_SUCCESS="viewScreen";
            		}
            		else
            			FORWAD_SUCCESS="failure";
            	}
            	
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getPropertyDistrict".equalsIgnoreCase(actionName)) {
					
            		
            			logger.info("Inside Tehsil Action");
            			
            			sdDTO.setTehsilList(sdBD.getTehsil(sdDTO.getDistrictIDProperty(),fm.getLanguage()));
            			sdDTO.setAreaId("");
            			sdDTO.setWardPatwariId("");
            			sdDTO.setWardId("");
            			sdDTO.setMahallaId("");
            			FORWAD_SUCCESS="PartialPage3";
            			
            		}
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "ClearPropertyPage".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside Clear Property Action");
        		
        			
        			
        			sdDTO.setDistrictIDProperty("");
        			sdDTO.setTehsilID("");
        			sdDTO.setAreaId("");
        			sdDTO.setWardPatwariId("");
        			sdDTO.setWardId("");
        			sdDTO.setMahallaId("");
        			sdDTO.setSubAreaId("");
        			sdDTO.setSubAreaName("");
        			sdDTO.setWardPatwariName("");
        			sdDTO.setWardIds("");
        			sdDTO.setColonyId("");
        			sdDTO.setColonyName("");
        			sdDTO.setPropertyTypeId("");
        			sdDTO.setMunicipalBodyID("");
        			sdDTO.setAddMoreFloorCounter(0);
        			sdDTO.setPropTypeL1Id("");
        			sdDTO.setPropTypeL2Id("");
        			sdDTO.setArea(0);
        			sdDTO.setFloorId("");
        			sdDTO.setUnitId("");
        			sdDTO.setUnitTypeId("");
        			sdDTO.setAreaConstructed(0);
        			sdDTO.setVikasKhand("");
        			sdDTO.setRicircle("");
        			sdDTO.setLayoutdet("");
        			sdDTO.setNazoolstNum("");
        			sdDTO.setPlotnum("");
        			sdDTO.setPropAddress("");
        			sdDTO.setPropLandmark("");
        			sdDTO.setMapBuilding(new HashMap());
        			sdDTO.setNorth("");
        			sdDTO.setSouth("");
        			sdDTO.setWest("");
        			sdDTO.setEast("");
        			
        			sdDTO.setKhasaraNum1("");
        			sdDTO.setLagaan("");
        			sdDTO.setRinpustikaNum("");
        			sdDTO.setKhasraArea1("");
        			sdDTO.setFormName(null);
        			sdDTO.setActionName(null);
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getSubArea".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside GetSubArea Action");
        			String areaId = sdDTO.getAreaId();
        			SuppleDocBO bo = new SuppleDocBO();

				//	logger.debug("thesilId:-" + tehsilId );
					
				

					if("2".equalsIgnoreCase(areaId))
					{	
					 sdDTO.setSubAreaList(bo.getSubArea(language,areaId,sdDTO.getTehsilID(),"Y"));
					}
					else
					{
						 sdDTO.setSubAreaList(bo.getSubArea(language,areaId,sdDTO.getTehsilID(),"N"));

					}
        			
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            	
            		else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getWard".equalsIgnoreCase(actionName)) {
            				
            			
            			SuppleDocBO bo = new SuppleDocBO();
            		
        			logger.info("Inside Get Ward Action");
        			 String municipalFlag=bo.getMuncipalFlag(sdDTO.getSubAreaId());
    				 if("RF".equalsIgnoreCase(municipalFlag))
    				 {
    					 sdDTO.setMunicipalCheckDisplay("Y");
    					 sdDTO.setMunicipalFlag("");
    				 }
    				 else if("NF".equalsIgnoreCase(municipalFlag))
    				 {
    					 sdDTO.setMunicipalCheckDisplay("N");
    					 sdDTO.setMunicipalFlag("Y");
    				 }
    				 else
    				 {
    					 sdDTO.setMunicipalCheckDisplay("N");
    					 sdDTO.setMunicipalFlag("N");
    				 }
    				 sdDTO.setWardPatwariList(bo.getWardPatwari(language,sdDTO.getSubAreaId(),sdDTO.getTehsilID()));
        			
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getColony".equalsIgnoreCase(actionName)) {
            				
            			
            			SuppleDocBO bo = new SuppleDocBO();
            		
        			logger.info("Inside Get Colony Action");
        			sdDTO.setColonyList(bo.getColonyName(language,sdDTO.getWardIds()));
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            	
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getWardPatwari".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside WardPatwari Action");
        			String tehsilId = sdDTO.getTehsilID();
					

				//	logger.debug("thesilId:-" + tehsilId );
					
					String status = (String) sdDTO.getWardPatwariId();

					//eForm.getRegcompletDto().setWardStatus(status);
					
					if (status.equalsIgnoreCase("W")) {
						status = "1";
					} else if (status.equalsIgnoreCase("P")) {
						status = "2";
					}

					
					String paramAr = sdDTO.getAreaId();
					
					sdDTO.setWardList(refBo.getWard(tehsilId, paramAr, status,fm.getLanguage()));
					
        			
        			sdDTO.setWardId("");
        			sdDTO.setMahallaId("");
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getMohallaAction".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside Mohalla Action");
        			
        			
					
					sdDTO.setMahallaList(refBo.getMahalla(sdDTO.getWardId(),fm.getLanguage()));
        			
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "dummy".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside dummy Action");
        			
        			
				sdDTO.setDummy(fm.getSuppleDocDTO().getDummy());
				
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "setunitNameTemp".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside unitname Action");
        			
        			
				sdDTO.setUnitNameTemp((fm.getSuppleDocDTO().getUnitNameTemp()));
				
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getTypeDetails".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside TypeDetails Action");
        			
        			RegCommonBO	commonBo = new RegCommonBO();
					String propId = sdDTO.getPropertyTypeId();
					String tehsilId = sdDTO.getTehsilID();
				
					
					sdDTO.setPropTypeL1List((refBo.getPropTypeL1List(sdDTO.getPropertyTypeId(),fm.getLanguage())));
					sdDTO.setPropTypeL2List(new ArrayList());
					sdDTO.setUnitList(new ArrayList());
					sdDTO.setFloorTypeList(new ArrayList());
					if(!propId.equalsIgnoreCase("2")){                        //2 is for building
						sdDTO.setMapBuilding(new HashMap());
						sdDTO.setAddMoreFloorCounter(0);
						sdDTO.setUnitType("");
						sdDTO.setBuildingFlag("0");
						
					}else{
						sdDTO.setUnit("sq mtr");
						sdDTO.setBuildingFlag("1");
					}
					sdDTO.setArea(0);
					sdDTO.setPropTypeL2Flag(0);
					
					
        			
        			FORWAD_SUCCESS="PartialPage3";
        			
        		}
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getPropeL1Type".equalsIgnoreCase(actionName))					//NEEDED

				{
            		
            		
					String propL1Id=sdDTO.getPropTypeL1Id();
					
					if(propL1Id.equalsIgnoreCase("3")||propL1Id.equalsIgnoreCase("8"))
					{
						sdDTO.setProptypeflag("1");
					}
					else
					{
						sdDTO.setProptypeflag("2");
					}
					sdDTO.setPropTypeL2List(sdBD.getPropTypeL2List(propL1Id,fm.getLanguage()));
					
					if(sdDTO.getPropTypeL2List()!=null && sdDTO.getPropTypeL2List().size()>0){
						sdDTO.setPropTypeL2Flag(1);
					}else{
						sdDTO.setPropTypeL2Flag(0);
					}
					
					
					if(sdDTO.getPropertyTypeId().equalsIgnoreCase("2")){
						sdDTO.setFloorTypeList(refBo.getFloorType(propL1Id,fm.getLanguage()));                  //get floor list in case of property type building
						}
					sdDTO.setUnitList(refBo.getUnitList(propL1Id,fm.getLanguage()));
					refreshKhasraData(sdDTO,request);
					
					FORWAD_SUCCESS="PartialPage3";
				}
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "getUnitAction".equalsIgnoreCase(actionName))                           //NEEDED    CORRECT
				 {
							logger.debug("dto.getUnitId():-"
						+ sdDTO.getUnitId());
				String param = sdDTO.getUnitId();
				if (param != null) {
					sdDTO.setUnit(param);
					logger.debug("inside UNIT_ACTION");
					
					
				}else{
					sdDTO.setUnit("");
				}
				FORWAD_SUCCESS="PartialPage3";
			
		}
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "addFloorAction".equalsIgnoreCase(actionName))                           //NEEDED    CORRECT
				 {
            		//eForm.setActionName(RegInitConstant.NO_ACTION);
					 
					 logger.debug("action name floor 2--->"+sdDTO.getActionName());
					
					 
					 
					 HashMap mapFloor = sdDTO.getMapBuilding();
					
					
					SuppleDocDTO mapDto=new SuppleDocDTO();
					
					mapDto.setPropTypeL1Id(sdDTO.getPropTypeL1Id());
					
					mapDto.setPropTypeL1(sdDTO.getPropTypeL1());                                     //prop type l1 name
					mapDto.setPropTypeL2Id(sdDTO.getPropTypeL2Id());
					mapDto.setFloorId(sdDTO.getFloorId());
					
					mapDto.setTypeFloorName(sdDTO.getTypeFloorName());                                   //floor name
					mapDto.setAreaConstructed(sdDTO.getAreaConstructed());
					mapDto.setArea(sdDTO.getArea());
					mapDto.setUnitId(sdDTO.getUnitId());
					mapDto.setTypeFloorNameTemp(sdDTO.getTypeFloorNameTemp());
					mapDto.setUnitType(sdDTO.getUnitType());     
					
					mapDto.setUnitNameTemp(sdDTO.getUnitNameTemp());
				
					mapDto.setDummy(sdDTO.getDummy());
				
					mapDto.setTypeUsageTemp(sdDTO.getTypeUsageTemp());
					//unit name
					//mapDto.setFloorTxnId(floorTxnId);
					
					int count=sdDTO.getAddMoreFloorCounter();
	                 if(count==0)
	                	 count=1;
	                 logger.debug("***size of MAp------>"+mapFloor.size());
	                 logger.debug("***count------>"+count);
	                 if(mapFloor.size()>0)
	                 {
	                 
	                 if(mapFloor.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                 }
	                 else
	                 {
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                 }
	                 
					//mapFloor.put("", mapDto);
	                 
	                sdDTO.setMapBuilding(mapFloor);
	                sdDTO.setAddMoreFloorCounter(count);
	                 sdDTO.setPropTypeL1List(refBo.getPropTypeL1List(sdDTO.getPropertyTypeId(),fm.getLanguage()));
						sdDTO.setPropTypeL2List(new ArrayList());
						sdDTO.setUnitList(new ArrayList());
						sdDTO.setFloorTypeList(new ArrayList());
						sdDTO.setArea(0);
						sdDTO.setAreaConstructed(0);
						sdDTO.setPropTypeL1Id("");
				FORWAD_SUCCESS="PartialPage3";
			
		}
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "goNext".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside Next Action");
        			
        			
        			String prop  = sdDTO.getPropertyTypeId();
        		
        			HashMap mapFloor=null;
        			if(prop.equalsIgnoreCase("2")){
        			 mapFloor = sdDTO.getMapBuilding();
        			

					
					SuppleDocDTO mapDto=new SuppleDocDTO();
					
					mapDto.setPropTypeL1Id(sdDTO.getPropTypeL1Id());
					
					mapDto.setPropTypeL1(sdDTO.getPropTypeL1());                                     //prop type l1 name
					mapDto.setPropTypeL2Id(sdDTO.getPropTypeL2Id());
					mapDto.setFloorId(sdDTO.getFloorId());
					
					mapDto.setTypeFloorName(sdDTO.getTypeFloorName());                                   //floor name
					mapDto.setAreaConstructed(sdDTO.getAreaConstructed());
					mapDto.setArea(sdDTO.getArea());
					mapDto.setUnitId(sdDTO.getUnitId());
					mapDto.setTypeFloorNameTemp(sdDTO.getTypeFloorNameTemp());
					mapDto.setUnitType(sdDTO.getUnitType());     
					
					mapDto.setUnitNameTemp(sdDTO.getUnitNameTemp());
				
					mapDto.setDummy(sdDTO.getDummy());
				
					mapDto.setTypeUsageTemp(sdDTO.getTypeUsageTemp());
					//unit name
					//mapDto.setFloorTxnId(floorTxnId);
					
					int count=sdDTO.getAddMoreFloorCounter();
	                 if(count==0)
	                	 count=1;
	                 logger.debug("***size of MAp------>"+mapFloor.size());
	                 logger.debug("***count------>"+count);
	                 if(mapFloor.size()>0)
	                 {
	                 
	                 if(mapFloor.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                 }
	                 else
	                 {
	                	 mapFloor.put(Integer.toString(count), mapDto);
	                 }
	                 
					//mapFloor.put("", mapDto);
	                 
	                sdDTO.setMapBuilding(mapFloor);
	                sdDTO.setAddMoreFloorCounter(count);
        			}
        			mapFloor = sdDTO.getMapBuilding();
        			
        		//	logger.debug("khasra nos. :-"+ sdDTO.getKhasraNoArray());
				//	logger.debug("khasra areas. :-"+ sdDTO.getKhasraAreaArray());
					//logger.debug("lagaans :-" + sdDTO.getLagaanArray());
					//logger.debug("rin pustikas :-"+ sdDTO.getRinPustikaArray());

					String str = "";
					str = sdDTO.getKhasraNoArray();
					
					boolean checks = checkrate(str);
					if(str.indexOf('@')>=0)
					
					sdDTO.setKhasraNoArray(null);
					else
					{
						sdDTO.setKhasraNoArray(str);
					}
					//
					str = sdDTO.getKhasraAreaArray();
				
					
					if(str.indexOf('@')>=0)
				
					sdDTO.setKhasraAreaArray(null);
					else
					{
						sdDTO.setKhasraAreaArray(str);
					}
					//
					str = sdDTO.getLagaanArray();
					
					
					if(str.indexOf('@')>=0)
					
					sdDTO.setLagaanArray(null);
					else
					{
						sdDTO.setLagaanArray(str);
					}
					//
					str = sdDTO.getRinPustikaArray();
					
				
					
					if(str.indexOf('@')>=0)
					
					sdDTO.setRinPustikaArray(null);
					else
					{
						sdDTO.setRinPustikaArray(str);
					}

        			
        			
        			
        			boolean ch = refBo.insertCompletePropertyDetails(sdDTO,mapFloor);
        			
        			
        			
					
					//sdDTO.setMahallaList(refBo.getMahalla(sdDTO.getWardId()));
        			String username = sdBD.getUserName(sdDTO.getUserId());
        			String srname[] = username.split(",");
        			String sroName =  sdBD.getSROName(officeId,language);
        			sdDTO.setSrName(srname[0]);
        			sdDTO.setPresentDate(srname[1]);
        			sdDTO.setSroName(sroName);
        			sdDTO.setScannedCopyPath("");
            		sdDTO.setScannedCopyName("");
        			sdDTO.setThumb("ScannedDoc.pdf");
        			sdDTO.setThumbPath("D:/Upload/91/SuppleDoc/");
        			 Date date = new Date();
						  Format yearformat  = new SimpleDateFormat("yy");
						  Format monthformat = new SimpleDateFormat("MM");
						  Format dateformat  = new SimpleDateFormat("dd");
						  Format hourformat  = new SimpleDateFormat("hh");
						  Format minformat  = new SimpleDateFormat("mm");
						  Format secformat  = new SimpleDateFormat("ss");
						  Format ampmformat  = new SimpleDateFormat("a");
						  String dfmt = dateformat.format(date);
						  String yfmt = yearformat.format(date);
						  String mfmt = monthformat.format(date);
						  String hfmt = hourformat.format(date);
						  String mifmt = minformat.format(date);
						  String sfmt = secformat.format(date);
						  String ampmfmt = ampmformat.format(date);
						  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
        			sdDTO.setUniqueId(fldrnm);
        			sdDTO.setBookTypeList(refBo.getBookTypeList());
        			sdDTO.setBookTypeId("4");
        			sdDTO.setBookTypeName("4");
        			sdDTO.setFormName(null);
            		sdDTO.setActionName(null);	
        			if(ch)
        			FORWAD_SUCCESS="submitPage";
        			else
        				FORWAD_SUCCESS="failure";
        		}
            	
            	else if ("detailsCreateForm3".equalsIgnoreCase(formName)&& "deleteFloorAction".equalsIgnoreCase(actionName))					//NEEDED

				{
					String[] floorID= sdDTO.getHdnDeleteFloorID().split(",");
					HashMap floorMap=sdDTO.getMapBuilding();
					int count =0;
					
					for(int j = 0 ;j < floorID.length ;j++) {
                         logger.debug(floorID[j]+" is removed...");
                         floorMap.remove(floorID[j]);
                         count++;
                         
					 }
					sdDTO.setMapBuilding(floorMap);
					sdDTO.setAddMoreFloorCounter(sdDTO.getAddMoreFloorCounter()-count);
					
					sdDTO.setFormName(null);
	        		sdDTO.setActionName(null);	
					
					FORWAD_SUCCESS="PartialPage3";
				}
            	
else if ("SubmitForm".equalsIgnoreCase(formName)&& "SubmitForm".equalsIgnoreCase(actionName)) {
					
            		
        			logger.info("Inside Submittting Action");
        			String filingNo = null;
        			if(sdDTO.getImpound().equalsIgnoreCase("G"))
        			{
        				filingNo = sdBD.generateFilingNumber(sdDTO);
        			
        			sdDTO.setFilingNumber(filingNo);
        			}
        			else if(sdDTO.getImpound().equalsIgnoreCase("R"))
        			{
        				filingNo="";
        				sdDTO.setFilingNumber("");
        			}
        			sdBD.insertLastPageDetails(sdDTO,filingNo);
        			
        			
        		
        			
        			sdDTO.setFormName(null);
            		sdDTO.setActionName(null);	
				
					
					
					
        			FORWAD_SUCCESS="submitSuccess";
        			
        		}
            	
else if ("SubmitForm".equalsIgnoreCase(formName)&& "GenerateorRefer".equalsIgnoreCase(actionName)) {
	
	
	logger.info("Inside Button Generate Action");
	

	
	sdDTO.setFormName(null);
	sdDTO.setActionName(null);	

	
	
	
	FORWAD_SUCCESS="submitPage";
	
}

            	
else if ("displayParty".equalsIgnoreCase(display)) {
	
	String combine = request.getParameter("combineData");
	SuppleDocDTO dto = sdBD.getTotalPartyDetailsBD(combine,sdDTO,fm.getLanguage());
	
	logger.info("CONFIRM FORM"+ combine);
	
	
	

	sdDTO.setActionName(null);
	sdDTO.setFormName(null);
	

	
	
	
	FORWAD_SUCCESS="viewPartyPopup";
	
}
            	
else if ("displayPartyRefer".equalsIgnoreCase(display)) {
	
	String combine = request.getParameter("combineData");
	SuppleDocDTO dto = sdBD.getTotalPartyDetailsBD(combine,sdDTO,fm.getLanguage());
	
	logger.info("CONFIRM FORM"+ combine);
	
	
	

	sdDTO.setActionName(null);
	sdDTO.setFormName(null);
	

	
	
	
	FORWAD_SUCCESS="partyPopupRefer";
	
}
else if ("NextDisplay".equalsIgnoreCase(displayNext)) {
	
	
	 request.setAttribute("partyDetails", sdDTO.getPartyDetails());
	 request.setAttribute("estampDetails", sdDTO.getEstampDetails());
	 request.setAttribute("pstampDetails", sdDTO.getPstampDetails());
	FORWAD_SUCCESS="viewScreen";
	formName="";
			actionName="";
			sdDTO.setActionName(null);
			sdDTO.setFormName(null);
}
            	
else if ("partyPopupForm".equalsIgnoreCase(formName)&& "PopupReturn".equalsIgnoreCase(actionName)) {
	
	
	 request.setAttribute("partyDetails", sdDTO.getPartyDetails());
	 request.setAttribute("estampDetails", sdDTO.getEstampDetails());
	 request.setAttribute("pstampDetails", sdDTO.getPstampDetails());
	FORWAD_SUCCESS="viewScreen";
	
}
else if ("ViewReferPage3".equalsIgnoreCase(formName)&& "generateFilingNumber".equalsIgnoreCase(actionName)) {
	
	
	sdDTO.setActionName(null);
	sdDTO.setFormName(null);
	sdDTO.setOfficeId(officeId);
	String filingNo = sdBD.generateFilingNumber(sdDTO);
	String referId = sdDTO.getReferenceId();
	String remarks = sdDTO.getRemarks();
	sdDTO.setFilingNumber(filingNo);
	sdDTO.setImpound("G");
	boolean ch = sdBD.updateFilingNumber(referId,remarks,filingNo);
	if(ch)
	{
	FORWAD_SUCCESS="submitSuccess";
	}
	else {
		FORWAD_SUCCESS="failure";
	}
}
else if ("partyPopupReferForm".equalsIgnoreCase(formName)&& "PopupReturnRefer".equalsIgnoreCase(actionName)) {
	
	
	 request.setAttribute("partyDetails", sdDTO.getPartyDetails());
	// request.setAttribute("estampDetails", sdDTO.getEstampDetails());
	 //request.setAttribute("pstampDetails", sdDTO.getPstampDetails());
	 sdDTO.setFormName(null);
	 sdDTO.setActionName(null);
	FORWAD_SUCCESS="ViewRefer1";
	
}
     //Insertion here     
else if ("SubmitForm".equalsIgnoreCase(formName)&& "CheckUpload".equalsIgnoreCase(actionName)) {
	
	File f = new File(sdDTO.getThumbPath()+sdDTO.getUniqueId()+"/"+ sdDTO.getThumb());
	 

	BurningImageAndText burnObj = new BurningImageAndText();  

    PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

         DocumentOperations docOperations = new DocumentOperations();

     ODServerDetails connDetails = new ODServerDetails();

     Dataclass metaDataInfo = new Dataclass();

     connDetails.setAppServerIp(pr.getValue("AppServerIp"));

     connDetails.setCabinetName(pr.getValue("CabinetName"));

     connDetails.setAppServerUserId(pr.getValue("AppServerUserId"));

     connDetails.setAppServerUserPass(pr.getValue("AppServerUserPass"));

     connDetails.setImageServerIp(pr.getValue("ImageServerIP"));

     

connDetails.setImageServerPort(pr.getValue("ImageServerPort"));

connDetails.setIniPath("D:\\workspace_25Feb\\IGRS\\WebContent\\WEB-INF\\lib\\userDbId.ini");

metaDataInfo.setDataclassName(pr.getValue("IGRSDataclass"));

   //metaDataInfo.setUniqueNo(sForm.getSealDTO().getRegNumber());

metaDataInfo.setType(SealsConstant.UNIQUE_CONSTANT_FOR_SEALS_DOC);


	
	//System.out.println(sdDTO.getThumbPath()+sdDTO.getUniqueId()+"/"+ sdDTO.getThumb());
	sdDTO.setScannedCopyPath(sdDTO.getThumbPath()+sdDTO.getUniqueId());
	sdDTO.setScannedCopyName(sdDTO.getThumb());
	
	FORWAD_SUCCESS="submitPage";
	formName="";
		actionName="";
		sdDTO.setActionName(null);
		sdDTO.setFormName(null);
}
else if ("SubmitForm".equalsIgnoreCase(formName)&& "downloadDoc".equalsIgnoreCase(actionName)) {
	String pathName=sdDTO.getScannedCopyPath()+"/"+sdDTO.getScannedCopyName();
	DMSUtility dms=new DMSUtility();
	byte b[] = dms.getDocumentBytes(pathName);
	dms.downloadDocument(response, pathName, b);
	
	formName="";
		actionName="";
		sdDTO.setActionName(null);
		sdDTO.setFormName(null);
}            	
else if ("SubmitForm".equalsIgnoreCase(formName)&& "setUploadScannedCopy".equalsIgnoreCase(actionName)) {
					
            		
            		
            		 ArrayList	errorList = new ArrayList();
   				  FormFile uploadedFile = sdDTO.getScannedCopy();
   					if ("".equals(uploadedFile.getFileName())) {
   						errorList.add("Invalid file selection. Please try again.");
   					}
   					String fileExt = getFileExtension(uploadedFile.getFileName());
   					AuditInspectionRule rule = new AuditInspectionRule();
   					rule.validateFileType(fileExt);
   					int size = uploadedFile.getFileSize();
   					double fileSizeInBytes = size;
   					double fileSizeInKB = fileSizeInBytes / 1024.0;
   					double fileSizeInMB = fileSizeInKB / 1024.0;
   					DecimalFormat decim = new DecimalFormat("#.##");
   					Double fileSize = Double.parseDouble(decim.format(fileSizeInMB));
   					String thumbSize = "(" + fileSize + "MB)";
   					if (rule.isError()) {
   						errorList.add("Invalid file type. Please select another file.");
   						request.setAttribute("errorsList",errorList);
   					} else {
   						if (size > AuditInspectionConstant.MAX_FILE_SIZE) {
   							errorList.add("File size is Greater than 10 MB. Please select another file.");
   							request.setAttribute("errorsList",errorList);
   						} else {
   							 Date date = new Date();
   							  Format yearformat  = new SimpleDateFormat("yy");
   							  Format monthformat = new SimpleDateFormat("MM");
   							  Format dateformat  = new SimpleDateFormat("dd");
   							  Format hourformat  = new SimpleDateFormat("hh");
   							  Format minformat  = new SimpleDateFormat("mm");
   							  Format secformat  = new SimpleDateFormat("ss");
   							  Format ampmformat  = new SimpleDateFormat("a");
   							  String dfmt = dateformat.format(date);
   							  String yfmt = yearformat.format(date);
   							  String mfmt = monthformat.format(date);
   							  String hfmt = hourformat.format(date);
   							  String mifmt = minformat.format(date);
   							  String sfmt = secformat.format(date);
   							  String ampmfmt = ampmformat.format(date);
   							  String fldrnm = dfmt+mfmt+yfmt+hfmt+mifmt+sfmt+ampmfmt;
   						//	logger.debug("the new date in upload thumb is----------------------- >"+fldrnm);
   							String thumbName = uploadedFile.getFileName();
   							String thumbPath = "D:/Upload/91/SuppleDoc/"+fldrnm;
   							boolean up=uploadFile(sdDTO.getScannedCopy(),thumbName,thumbPath);
   							if(up)
   							{
   							sdDTO.setScannedCopyName(uploadedFile.getFileName());
   							sdDTO.setScannedCopyPath(thumbPath);
   							//sdDTO.setThumbContents(uploadedFile.getFileData());
   							//sdDTO.setThumbSize(thumbSize);
   						//	sdDTO.setThumbCheck("thloded");
   							
   							FORWAD_SUCCESS="submitPage";
   						
   							System.out.println("Start of EStamp Generation with Barcode ff");
   							// generating EStamp with bar code
   							String outputPath = "C:\\TestFiles\\Deed Inputs";
   						//	BurningImageAndText burnImg=new BurningImageAndText();
   							//burnImg.
   						
   							//burnImg.generateEStampWithBarcode(outputPath, "C:\\TestFiles\\Deed Inputs\\header.jpg", "C:\\TestFiles\\Deed Inputs\\E-Stamp Certificate21Feb.pdf", "EStampPdf21Feb.pdf");
   						//	burnImg.generateEStampNoBarcode(outputPath, "C:\\TestFiles\\Deed Inputs\\header.jpg", "C:\\TestFiles\\Deed Inputs\\E-Stamp Certificate21Feb.pdf", "EStampPdf21Febnobarcode.pdf");		
   							//end of DMS
   						   //DocumentOperations docOperations = new DocumentOperations();
   							System.out.println("Hii"); 
   						/*ODServerDetails connDetails = new ODServerDetails();
   				        Dataclass metaDataInfo = new Dataclass();
   				        connDetails.setAppServerIp("10.105.115.64");
   				        connDetails.setCabinetName("igrsdms");
   				        connDetails.setAppServerUserId("supervisor2");
   				        connDetails.setAppServerUserPass("supervisor351");
   				        connDetails.setImageServerIp("10.105.115.64");
   				        connDetails.setImageServerPort("3333");
   				        //File file = new File("C:\\IGRS\\Property.pdf");
   				     File file = new File("C:\\IGRS\\Annexure UAT letter.pdf");
   				        metaDataInfo.setDataclassName("IGRS");   
   				        metaDataInfo.setUniqueNo("EMCR220044");
   				     String docId = docOperations.uploadDocument(connDetails, file, "IGRS", true, metaDataInfo);*/
   						
   				    
   							}
   							else
   							{
   								FORWAD_SUCCESS="failure";
   							}
   							} 
   						}
   					formName="";
   					actionName="";
   					sdDTO.setActionName(null);
   					sdDTO.setFormName(null);
        			
        		}
        		
            	else if("detailsCreateForm".equalsIgnoreCase(formName)&& "supplDetails".equalsIgnoreCase(actionName))
            	{
            		logger.info("Inside supplDetails Action");
            	    session.setAttribute("suppleDetails",sdDTO);
            	    if(session.getAttribute("frwdedByEdit")!=null)
            	    { 
            	    	String str=(String)session.getAttribute("frwdedByEdit");
            	    	if(str.equalsIgnoreCase("yes"))
            	    	{
            	    		FORWAD_SUCCESS="ConfirmScreen";
            	    	}
            	    }
            	    else if(sdDTO!=null)
            		{
            			//fm.setSuppleDocDTO(sdDTO);
            			FORWAD_SUCCESS="propertyForwardPath";
            		}
            		else
            			FORWAD_SUCCESS="failure";
            	}
            	if("dispSupple".equalsIgnoreCase(request.getParameter("pageName")))
            	{
            		logger.info("Inside confirmDetails Action");
            		sdDTO=(SuppleDocDTO)session.getAttribute("suppleDetails");
            		fm.setSuppleDocDTO(sdDTO);
            		FORWAD_SUCCESS="ConfirmScreen";
            	}
            }catch(Exception e)
	        {
	        	logger.error(e);
	        	e.printStackTrace();
	        	return mapping.findForward("failure");
	        }
        }
        
        
        request.setAttribute("suppledocfrm", fm);
        
        logger.info("FORWARD SUCCESS -->"+FORWAD_SUCCESS);
        
        return mapping.findForward(FORWAD_SUCCESS);
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

    private boolean uploadFile(FormFile filetobeuploaded,String fileName,String filePath) {

    	
    	
    	try {
    		File folder = new File(filePath);
    	      if (!folder.exists()) {
    		folder.mkdirs();
    	       }
    	      
    			File newFile = new File(filePath, fileName);
    			logger.info("NEW FILE NAME:-" + newFile);
    			FileOutputStream fos = new FileOutputStream(newFile);
    			fos.write(filetobeuploaded.getFileData());
    			fos.close();
    		

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return true;
    }

    private void refreshKhasraData(SuppleDocDTO sdDTO,
			HttpServletRequest request) {
		try {
			/*
			 * SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
			 * SimpleDateFormat displayFormat = new
			 * SimpleDateFormat("dd/MM/yyyy"); inputFormat.setLenient(false);
			 * displayFormat.setLenient(false); String dateString;
			 */
			String[] buildingSize = request.getParameterValues("suppleDocDTO.khasaraNum1");
			String[] buildingArea = request.getParameterValues("suppleDocDTO.rinpustikaNum");
			String[] khasraArea=request.getParameterValues("suppleDocDTO.khasraArea1");
			String[] lagaan=request.getParameterValues("suppleDocDTO.lagaan");

			String values = buildingSize.toString();
			
			
			sdDTO.setKhasraNoArray(values);
			//
		
			sdDTO.setKhasraAreaArray(khasraArea.toString());
			
			
			sdDTO.setLagaanArray(lagaan.toString());
			//
			
			sdDTO.setRinPustikaArray(buildingArea.toString());


			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}
    
    public boolean checkrate(String value)
    {
    	boolean check=true;
    	for(int i =0;i<value.length();i++)
    	{
    		if(value.charAt(i)=='@')
    		{
    			check=false;
    		}
    		
    	}
    	
    	return check;
    }
 }




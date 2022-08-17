package com.wipro.igrs.payments.action;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.payment.dto.OnlinePaymentDto;
import com.wipro.igrs.payment.util.AESEncrypt;
import com.wipro.igrs.payments.bd.CashInternalBD;
import com.wipro.igrs.payments.bd.PaymentBD;
import com.wipro.igrs.payments.bo.CashInternalBO;
import com.wipro.igrs.payments.bo.PaymentBO;
import com.wipro.igrs.payments.constant.PaymentConstant;
import com.wipro.igrs.payments.dto.ChallanDTO;
import com.wipro.igrs.payments.dto.OnlineDTO;
import com.wipro.igrs.payments.dto.PaymentDTO;
import com.wipro.igrs.payments.form.PaymentForm;
import com.wipro.igrs.payments.util.PropertiesFileReader;
import com.wipro.igrs.serviceProvider.bd.ServiceProviderAccountBD;
import com.wipro.igrs.util.DateToWords;


public class PaymentActionCash extends BaseAction {

    Logger logger = (Logger) Logger.getLogger(PaymentActionCash.class);
	PaymentForm eForm = new PaymentForm() ;
    public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response,
	    HttpSession session) throws Exception {
	// TODO Auto-generated method stub
	
    	
    	String forwardJsp = "";
    	String jspPage= "";
    	CashInternalBD paymentBD = new CashInternalBD();
    	PaymentBD payBd = new PaymentBD();
    	String languageLocale="hi";
		if(session.getAttribute("languageLocale")!=null){
			languageLocale=(String)session.getAttribute("languageLocale");
		}
    	
    	String param = request.getParameter("param") ;
    	
    	String online = request.getParameter("online");
		String officeIdLoggedIn = (String)session.getAttribute("loggedToOffice");
System.out.println(officeIdLoggedIn +"  officeIdLoggedIn");
    	

//akansha changes
if (request.getParameter("flag") != null) {
	if (request.getParameter("flag").equalsIgnoreCase(
			"cashModule")) {
		String	funid          = (String)session.getAttribute("parentFunId");
		if(request.getAttribute("transactionId")!=null && request.getAttribute("serviceFee")!=null){
		String transactionId= (String) request.getAttribute("transactionId");
	String serviceFee=	(String) request.getAttribute("serviceFee");
		eForm = (PaymentForm) form; 
		
		
		double d = Math.ceil(Double.parseDouble(serviceFee));
	    int entAmtnew = (int) d;
	    
	  String serviceFeeNew=  Integer.toString(entAmtnew);
	  eForm.setParentAmount(serviceFeeNew);
		eForm.setReceiptID(transactionId);
		eForm.setEntrAmt(serviceFeeNew);
		request.removeAttribute("transactionId");
		request.removeAttribute("serviceFee");
		}
		jspPage="SROpayment";
		   forwardJsp = jspPage;
		   eForm.setSearchRsptID("");
			 eForm.setChallansearchRsptID("");
			 eForm.setOnlineSearchRsptID("");
			 eForm.setCheckAvlblty(0);
			String rspIds = eForm.getReceiptID();
		    String rspId = rspIds.toLowerCase();
		    String entAmt = eForm.getEntrAmt();
		    String parentAmountNew =eForm.getParentAmount();
		    
		   PaymentDTO dto = new PaymentDTO();
 			ChallanDTO dto1 = null;
 			OnlineDTO  dto2 = null;
			    
 			
 			double d = Math.ceil(Double.parseDouble(entAmt));
		    int entAmtnew = (int) d;
		    
		  String amount=  Integer.toString(entAmtnew);
 			dto = paymentBD.getRsptDetailsNew(rspId, amount, funid,languageLocale,officeIdLoggedIn,parentAmountNew);//HINDI
  			dto.setReceiptID(rspId);
  			logger.debug ("INSIDE ACTION..... THE OFFICE ID....."+dto.getOffice());
  			eForm.setCheckAvlblty(1);
  			request.setAttribute("rsptDetails",dto);
  			request.setAttribute("challanRsptDetails",dto1);
  			request.setAttribute("onlineRsptDetails",dto2);
			  
			    
			
		return mapping.findForward(forwardJsp);
		 
	}
	
	
	
if (request.getParameter("flag").equalsIgnoreCase(
	"existingCash")) {
String	funid          = (String)session.getAttribute("parentFunId");

jspPage="SROpayment";

if(session.getAttribute("ExistingId")!=null &&(session.getAttribute("amt")!=null)){
	eForm.setReceiptID((String) session.getAttribute("ExistingId"));
	 eForm.setEntrAmt((String) session.getAttribute("amt"));
	 eForm.setParentAmount(eForm.getEntrAmt());
	 
	 session.removeAttribute("ExistingId");
	 session.removeAttribute("amt");
	 
}
   forwardJsp = jspPage;
  // eForm.setSearchRsptID("");
	// eForm.setChallansearchRsptID("");
	// eForm.setOnlineSearchRsptID("");
	// eForm.setCheckAvlblty(0);
	String rspIds = eForm.getReceiptID();
    String rspId = rspIds.toLowerCase();
    String entAmt = eForm.getEntrAmt();
    String parentAmountNew =eForm.getParentAmount();
    
   PaymentDTO dto = new PaymentDTO();
		ChallanDTO dto1 = null;
		OnlineDTO  dto2 = null;
	    
		
		double d = Math.ceil(Double.parseDouble(entAmt));
    int entAmtnew = (int) d;
    
  String amount=  Integer.toString(entAmtnew);
		dto = paymentBD.getRsptDetailsNew(rspId, amount, funid,languageLocale,officeIdLoggedIn,parentAmountNew);//HINDI
		dto.setReceiptID(rspId);
		logger.debug ("INSIDE ACTION..... THE OFFICE ID....."+dto.getOffice());
		eForm.setCheckAvlblty(1);
		request.setAttribute("rsptDetails",dto);
		request.setAttribute("challanRsptDetails",dto1);
		request.setAttribute("onlineRsptDetails",dto2);
	  
	    session.removeAttribute("ExistingId");
	    session.removeAttribute("amt");
	
return mapping.findForward(forwardJsp);
	
}

}

//akansha

    	
    	if("payment".equals(param)) {
    		eForm.setReceiptID("");
    		eForm.setChallanNO("");
    		eForm.setEntrAmt("");
    		eForm.setChallanentrAmt("");
    		eForm.setChallanBankId("");
    		eForm.setOnlineCinNo("");
    		eForm.setOnlineEntrAmt("");
    		eForm.getPayDTO().setBankList(new ArrayList());
    		eForm.setCrEntrAmt("");
    		eForm.setParentAmount("");
    		eForm.setActionName("");
    		eForm.setFormName("");
    		eForm.setSearchRsptID("");
    		eForm.setChallansearchRsptID("");
    		eForm.setRadioSelect("");
    		eForm.setCheckAvlblty(0);
    		//eForm = new PaymentForm() ;
    	  //  PaymentDTO paymentDTO = new PaymentDTO();
    	    eForm.setPaymentDTO( new PaymentDTO());
    	    eForm.setPaymentList(new ArrayList());
    	    eForm.setRadioSelect("");
    	    eForm.setSpFlag(null);
    	    eForm.getPaymentDTO().setSelectButton("");
    	    eForm.getPaymentDTO().setSelectType("");
    	    eForm.getPaymentDTO().setCcheck("");
    	    
    	    //below 2 statements added by roopam for bug id. 1612
    	    eForm.setDwnldChallanOfficeId("");
    	    eForm.setDwnldChallanDistrictId("");
    	    eForm.setDateParam("");
    	    eForm.setTreasuryUrl("");
    	     eForm.setEnableCash(payBd.getEnableCash());
    	   // request.setAttribute("radio", "");
    	    request.removeAttribute("radio");
    	    request.removeAttribute("PaidAmt");
    	    request.removeAttribute("PayTransId");
    	    request.removeAttribute("rsptDetails");
    	    request.removeAttribute("challanRsptDetails");
    	    request.removeAttribute("onlineRsptDetails");
    	    request.removeAttribute("mesgBundle");
    	    request.removeAttribute("parentKeyAftrPay");
    		request.removeAttribute("parentTableAftrPay");
    	   // session.removeAttribute("totamt");
    	    forwardJsp = jspPage;
    	   // forwardJsp = "SP";
    	}
    	
    
    	if(form!=null) {
    		eForm = (PaymentForm) form;   
    		if(online!=null && online.equalsIgnoreCase("onlinePayment"))
        	{
        		logger.debug("In online Payment");
        		String crn = (String)request.getAttribute("crn");	
        		OnlinePaymentDto dto = 	LoadMenuExternal.getInstance().getOnlinePaymentDto(crn);
        		
        		eForm.setActionName("NEXT_ACTION");
        		PaymentDTO dtoPayment = eForm.getPaymentDTO();
        		dtoPayment.setSelectType("online");

        		eForm.setFuncId(dto.getFuncId());
        	    eForm.setParentAmount(dto.getParentAmount());
        	    eForm.setParentTable(dto.getParentTable());
        	    eForm.setParentKey(dto.getParentKey());
        	    eForm.setForwardPath(dto.getForwardPath());
        	    eForm.setParentColumnName(dto.getParentColumnName());
        	    eForm.setParentOffId(dto.getParentOffId());
        	    eForm.setParentOffName(dto.getParentOffName());
        	    eForm.setParentDistId(dto.getParentDistId());
        	    eForm.setParentDistName(dto.getParentDistName());
        	    eForm.setParentRefId(dto.getParentRefId());
        	    eForm.setFormName(dto.getOldformName());
        	    eForm.setCrnNumber(dto.getCrnNumber());
        	    eForm.setOnlineEntrAmt(dto.getPayingAmout());
        	    eForm.setChallanNumber(dto.getChallanNumber());
        	    eForm.setBrn(dto.getBrn());
        	    eForm.setTrasactionDate(dto.getTrasaction_date_time());
        	    eForm.setScrollNumber(dto.getScrollNumber());
        	    eForm.setScrollDate(dto.getScrollDate());
        	    eForm.setCin(dto.getCin());
        	    
        	    
        	    String path = dto.getFormPath();
        	    String pathPayment = dto.getPaymentFormPath();
        	    request.setAttribute("finalForwardPath", dto.getForwardPath());
        	    LoadMenuExternal.getInstance().deleteOnlinePayementDto(crn);//Removing from Application Context
        	try{
        	    clearResources(path);
        	    clearResources(pathPayment);
        	}
        	catch (Exception e) {
				logger.debug(e.getMessage(),e);
			}
        	    logger.debug("In online Payment form set the values");
        		
        	}
    	    //request.setAttribute("payChform", eForm);
    		String par = request.getParameter("Prnt");
    		if("CH".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			String depoName = paymentBD.getdepoDtl(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payChform", eForm);
    			jspPage ="printPop2";	
    		}
    		if("ON".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String trans = (String) session.getAttribute("PayTransId");
    			eForm.setTransId(trans);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			String depoName = paymentBD.getdepoDtlON(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			//paymentBD.getPaymentDetailsOnline(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payONform", eForm);
    			jspPage ="printPop4";	
    		}
    		if("CA".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			String depoName = paymentBD.getdepoDtlCa(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payCaform", eForm);
    			jspPage ="printPop1";	
    		}
    		if("CR".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			//String depoName = paymentBD.getdepoDtl(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			//eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payCrform", eForm);
    			jspPage ="printPop3";	
    		}
    	}
	//String forwardJsp = "";
	PropertiesFileReader pr = 
	PropertiesFileReader.getInstance("resources.igrs");
	
	ChallanDTO challanDTO = new ChallanDTO();
	
	
	
	//String loginBy = (String)session.getAttribute("roleName");//Commented by ramesh on 03 dec12
	String userId = (String)session.getAttribute("UserId");
	String funid="";
	String parentAmount="";
	String parentTable="";
	String parentKey= "";
	String parentPath="";
	String moduleName="";
	String funName="";
	String parentColumnName= "";
	String spFlg = "";
	String parentOffid="";
	String parentOffName="";
	String parentDistId="";
	String parentDistName="";
	String parentRefId;
	String formName1;
	if(request.getAttribute("parentFunId")!=null && request.getAttribute("parentAmount")!=null && request.getAttribute("parentFunName")!=null
			&& request.getAttribute("forwardPath")!=null && request.getAttribute("parentKey")!=null
			&& request.getAttribute("parentOfficeId")!=null && request.getAttribute("parentOfficeName")!=null
			&& request.getAttribute("parentDistrictId")!=null && request.getAttribute("parentDistrictName")!=null
			&& request.getAttribute("parentReferenceId")!=null && request.getAttribute("parentModName")!=null
			&& request.getAttribute("parentColumnName")!=null && request.getAttribute("parentTable")!=null ){
		
		eForm.setIsBothNA(0);
		eForm.setIsNoneNA(0);
		eForm.setIsONA(0);
		
	funid          = (String)request.getAttribute("parentFunId");
	parentAmount   = (String)request.getAttribute("parentAmount");
	parentTable    = (String)request.getAttribute("parentTable");
	parentKey      = (String)request.getAttribute("parentKey");
	moduleName     = (String)request.getAttribute("parentModName");
	funName        = (String)request.getAttribute("parentFunName");
	parentPath     = (String)request.getAttribute("forwardPath");
	parentOffid    = (String)request.getAttribute("parentOfficeId");
	parentOffName  = (String)request.getAttribute("parentOfficeName");
	parentDistId   = (String)request.getAttribute("parentDistrictId");
	parentDistName = (String)request.getAttribute("parentDistrictName");
	parentRefId    = (String)request.getAttribute("parentReferenceId");
	parentColumnName = (String)request.getAttribute("parentColumnName");
	formName1 = (String)request.getAttribute("formName");
	
		
		int ind = parentPath.indexOf("?");
	logger.debug("index value is.........................."+ind);
	if(ind == -1){
		parentPath = parentPath+"?paymentStatus=P&parentKey="+parentKey;
		logger.debug("parentpath................"+parentPath);
	}else{
		parentPath = parentPath+"&paymentStatus=P&parentKey="+parentKey;
		logger.debug("parentpath................"+parentPath);
	}
	
	eForm.setFuncId(funid);
    eForm.setParentAmount(parentAmount);
    eForm.setParentTable(parentTable);
    eForm.setParentKey(parentKey);
    eForm.setForwardPath(parentPath);
    eForm.setParentColumnName(parentColumnName);
    eForm.setParentOffId(parentOffid);
    eForm.setParentOffName(parentOffName);
    eForm.setParentDistId(parentDistId);
    eForm.setParentDistName(parentDistName);
    eForm.setParentRefId(parentRefId);
    eForm.setFormName1(formName1);
    request.setAttribute("finalForwardPath", request.getAttribute("forwardPath"));
    if(request.getAttribute("TxnFlag")!=null){
    spFlg = (String)request.getAttribute("TxnFlag");
    eForm.setSpFlag(spFlg);
    }
    
	}
	else{
		funid = eForm.getFuncId();
        parentAmount = eForm.getParentAmount(); 
        parentTable = eForm.getParentTable();
        parentKey = eForm.getParentKey();
        parentPath = eForm.getForwardPath();
        spFlg = eForm.getSpFlag();
        parentColumnName = eForm.getParentColumnName();
        parentOffid=eForm.getParentOffId();
        parentOffName = eForm.getParentOffName();
        parentDistId = eForm.getParentDistId();
        parentDistName = eForm.getParentDistName();
        parentRefId =eForm.getParentRefId();
        formName1 = eForm.getFormName1();
        request.setAttribute("finalForwardPath", parentPath);
        

	}
	logger.debug("the parent amount is.."+parentAmount);
	logger.debug("the FUNCTION name is.."+funid);
	logger.debug("the parentTable is.."+parentTable);
	logger.debug("the parentKey is.."+parentKey);
	logger.debug("the parentPath2 is.."+parentPath);
	logger.debug("the parentcolumn is.."+parentColumnName);
	logger.debug("the sp flag is.."+spFlg);
	
	
	//String roleId = (String)session.getAttribute("roleId");//Commented by Ramesh
	//String roleIds = (String)session.getAttribute("loggedInEMPDesignation");
	String roleId =  paymentBD.getRole(userId); // Added by Aakriti for redirection to payment pages according to the role
	logger.debug("the role id is.."+roleId);
	ArrayList challanList = new ArrayList();
	//String checkFlag = null;
	String getStatus = null;
	//---Start------Added by Aakriti-----   // these are the fixed roles as told by TL, if roles change, module will not work as expected
	String srrole = "GR1356609170484";
	String drrole = "GR1356671177515";
	String sprole = "GR1378207030122";
	String rurole = "GR1357368369828";
	String spbankrole = "GR1378207030461";
	String spFile =  "GR1378207030531";
	String spPo = "GR1378207030599";
	//String getStatus1 = null;
	//---End------Added by Aakriti-----
	
	//if(roleId.equalsIgnoreCase(srrole) || loginBy.equalsIgnoreCase(srrole)) {//Commented by Ramesh
	if(roleId.equalsIgnoreCase(srrole)) {	
	    jspPage = "SROnew";
		String par = request.getParameter("Prnt");
		if (par!= null || par!= ""){
		if("CH".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String transDate1=paymentBD.gettranDate(eForm);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtl(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payChform", eForm);
			jspPage ="printPop2";	
		}
		if("ON".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String transDate1=paymentBD.gettranDate(eForm);
			String trans = (String) session.getAttribute("PayTransId");
			eForm.setTransId(trans);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtlON(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payONform", eForm);
			jspPage ="printPop4";	
		}
		if("CA".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String transDate1=paymentBD.gettranDate(eForm);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtlCa(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payCaform", eForm);
			request.setAttribute("parentKeyAftrPay",eForm.getParentKey());
			request.setAttribute("parentTableAftrPay",eForm.getParentTable());
			logger.debug("Value in parent key first------------------------------"+request.getAttribute("parentKeyAftrPay"));
			logger.debug("Value in parent key Second------------------------------"+eForm.getParentKey());
			jspPage ="printPop1";	
		}
		}
	}
	if(roleId.equalsIgnoreCase(drrole)) {	
	    jspPage = "SRO";
		String par = request.getParameter("Prnt");
		if (par!= null || par!= ""){
		if("CH".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String transDate1=paymentBD.gettranDate(eForm);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtl(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payChform", eForm);
			jspPage ="printPop2";	
		}
		if("ON".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String trans = (String) session.getAttribute("transid");
			eForm.setTransId(trans);
			String transDate1=paymentBD.gettranDate(eForm);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtlON(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payONform", eForm);
			jspPage ="printPop4";	
		}
		if("CA".equalsIgnoreCase(par)){
			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
			String transDate1=paymentBD.gettranDate(eForm);
			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
			Date d1 = sdf1.parse(transDate1);
			String transDate = sdf2.format(d1);
			//String depoName= eForm.getPayDTO().getDepositorName();
			String depoName = paymentBD.getdepoDtlCa(eForm);
			String issueName=paymentBD.getissueName(eForm);
			eForm.setActionName("");
			eForm.setFormName("");
			eForm.setFunName(purpname);
			eForm.setDeposName(depoName);
			eForm.setTranDate(transDate);
			eForm.setIssuName(issueName);
			request.setAttribute("payCaform", eForm);
			jspPage ="printPop1";	
		}
		}
	}
	//if(roleId.equalsIgnoreCase(sprole) || loginBy.equalsIgnoreCase(sprole)) {//Commented by Ramesh
	    
	if(roleId.equalsIgnoreCase(sprole) ||roleId.equalsIgnoreCase(spbankrole)||roleId.equalsIgnoreCase(spFile)||roleId.equalsIgnoreCase(spPo)) {
		//spFlg = (String)session.getAttribute("TxnFlag");
		if ("Credit".equalsIgnoreCase(spFlg)){
			//eForm.setSpFlag("");
			jspPage = "SP1";
			String par = request.getParameter("Prnt");
			if (par!= null || par!= ""){
			if("CH".equalsIgnoreCase(par)){
				String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
				String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
				//String depoName= eForm.getPayDTO().getDepositorName();
    			String depoName = paymentBD.getdepoDtl(eForm);
				String issueName=paymentBD.getissueName(eForm);
				eForm.setActionName("");
				eForm.setFormName("");
				eForm.setFunName(purpname);
				eForm.setDeposName(depoName);
				eForm.setTranDate(transDate);
				eForm.setIssuName(issueName);
				request.setAttribute("payChform", eForm);
				jspPage ="printPop2";	
			}
			if("ON".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			String crn = (String) session.getAttribute("CRN");
    			eForm.setCrnNumber(crn);
    			eForm.setLoggedUser((String)session.getAttribute("UserId"));
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			paymentBD.getPaymentDetailsOnline(eForm);
    			String depoName = paymentBD.getdepoDtlON(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payONform", eForm);
    			jspPage ="printPop4";	
    		}
			if("CA".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			String depoName = paymentBD.getdepoDtlCa(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payCaform", eForm);
    			jspPage ="printPop1";	
    		}
			if("CR".equalsIgnoreCase(par)){
    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
    			String transDate1=paymentBD.gettranDate(eForm);
    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
    			Date d1 = sdf1.parse(transDate1);
    			String transDate = sdf2.format(d1);
    			//String depoName= eForm.getPayDTO().getDepositorName();
    			//String depoName = paymentBD.getdepoDtl(eForm);
    			String issueName=paymentBD.getissueName(eForm);
    			eForm.setActionName("");
    			eForm.setFormName("");
    			eForm.setFunName(purpname);
    			//eForm.setDeposName(depoName);
    			eForm.setTranDate(transDate);
    			eForm.setIssuName(issueName);
    			request.setAttribute("payCrform", eForm);
    			jspPage ="printPop3";	
    		}
			}
		}else{
			
			    jspPage = "SP";
				String par = request.getParameter("Prnt");
				if (par!= null || par!= ""){
				if("CH".equalsIgnoreCase(par)){
					String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
					String transDate1=paymentBD.gettranDate(eForm);
	    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
	    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
	    			Date d1 = sdf1.parse(transDate1);
	    			String transDate = sdf2.format(d1);
					//String depoName= eForm.getPayDTO().getDepositorName();
	    			String depoName = paymentBD.getdepoDtl(eForm);
					String issueName=paymentBD.getissueName(eForm);
					eForm.setActionName("");
					eForm.setFormName("");
					eForm.setFunName(purpname);
					eForm.setDeposName(depoName);
					eForm.setTranDate(transDate);
					eForm.setIssuName(issueName);
					request.setAttribute("payChform", eForm);
					jspPage ="printPop2";	
				}
				if("ON".equalsIgnoreCase(par)){
					//Mohit SP2
	    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
	    			
	    			String trans = (String) session.getAttribute("PayTransId");
	    			eForm.setTransId(trans);
	    			String transDate1=paymentBD.gettranDate(eForm);
	    			
	    			String crn = (String) session.getAttribute("CRN");
	    			eForm.setCrnNumber(crn);
	    			eForm.setLoggedUser((String)session.getAttribute("UserId"));
	    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
	    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
	    			Date d1 = sdf1.parse(transDate1);
	    			String transDate = sdf2.format(d1);
	    			//String depoName= eForm.getPayDTO().getDepositorName();
	    			String depoName = paymentBD.getdepoDtlON(eForm);
	    			String issueName=paymentBD.getissueName(eForm);
	    			paymentBD.getPaymentDetailsOnline(eForm);
	    			eForm.setActionName("");
	    			eForm.setFormName("");
	    			eForm.setFunName(purpname);
	    			eForm.setDeposName(depoName);
	    			eForm.setTranDate(transDate);
	    			eForm.setIssuName(issueName);
	    			request.setAttribute("payONform", eForm);
	    			jspPage ="printPop4";	
	    		}
				if("CA".equalsIgnoreCase(par)){
	    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
	    			String transDate1=paymentBD.gettranDate(eForm);
	    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
	    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
	    			Date d1 = sdf1.parse(transDate1);
	    			String transDate = sdf2.format(d1);
	    			//String depoName= eForm.getPayDTO().getDepositorName();
	    			String depoName = paymentBD.getdepoDtlCa(eForm);
	    			String issueName=paymentBD.getissueName(eForm);
	    			eForm.setActionName("");
	    			eForm.setFormName("");
	    			eForm.setFunName(purpname);
	    			eForm.setDeposName(depoName);
	    			eForm.setTranDate(transDate);
	    			eForm.setIssuName(issueName);
	    			request.setAttribute("payCaform", eForm);
	    			jspPage ="printPop1";	
	    		}
				if("CR".equalsIgnoreCase(par)){
	    			String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
	    			String transDate1=paymentBD.gettranDate(eForm);
	    			SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
	    			SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
	    			Date d1 = sdf1.parse(transDate1);
	    			String transDate = sdf2.format(d1);
	    			//String depoName= eForm.getPayDTO().getDepositorName();
	    			//String depoName = paymentBD.getdepoDtl(eForm);
	    			String issueName=paymentBD.getissueName(eForm);
	    			eForm.setActionName("");
	    			eForm.setFormName("");
	    			eForm.setFunName(purpname);
	    			//eForm.setDeposName(depoName);
	    			eForm.setTranDate(transDate);
	    			eForm.setIssuName(issueName);
	    			request.setAttribute("payCrform", eForm);
	    			jspPage ="printPop3";	
	    		}
				}
	    }
	}
	
	
	//if(roleId.equalsIgnoreCase(rurole) || loginBy.equalsIgnoreCase(rurole)) {//Commented by Ramesh
	if(roleId.equalsIgnoreCase(rurole)) { 
		
	jspPage = "RU";
	String par = request.getParameter("Prnt");
	if (par!= null || par!= ""){
	if("CH".equalsIgnoreCase(par)){
		String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
		String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
		Date d1 = sdf1.parse(transDate1);
		String transDate = sdf2.format(d1);
		//String depoName= eForm.getPayDTO().getDepositorName();
		String depoName = paymentBD.getdepoDtl(eForm);
		String issueName=paymentBD.getissueName(eForm);
		eForm.setActionName("");
		eForm.setFormName("");
		eForm.setFunName(purpname);
		eForm.setDeposName(depoName);
		eForm.setTranDate(transDate);
		eForm.setIssuName(issueName);
		request.setAttribute("payChform", eForm);
		jspPage ="printPop2";	
	}
	if("ON".equalsIgnoreCase(par)){
		String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
		String trans = (String) session.getAttribute("PayTransId");
		eForm.setTransId(trans);
		String crn = (String) session.getAttribute("CRN");
		eForm.setCrnNumber(crn);
		eForm.setLoggedUser((String)session.getAttribute("UserId"));
		String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
		Date d1 = sdf1.parse(transDate1);
		String transDate = sdf2.format(d1);
		//String depoName= eForm.getPayDTO().getDepositorName();
		String depoName = paymentBD.getdepoDtlON(eForm);
		String issueName=paymentBD.getissueName(eForm);
		paymentBD.getPaymentDetailsOnline(eForm);
		eForm.setActionName("");
		eForm.setFormName("");
		eForm.setFunName(purpname);
		eForm.setDeposName(depoName);
		eForm.setTranDate(transDate);
		eForm.setIssuName(issueName);
		request.setAttribute("payONform", eForm);
		jspPage ="printPop4";	
	}
	if("CA".equalsIgnoreCase(par)){
		String purpname=paymentBD.getFunctionName(eForm.getFuncId(),languageLocale);
		String transDate1=paymentBD.gettranDate(eForm);
		SimpleDateFormat sdf1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
		Date d1 = sdf1.parse(transDate1);
		String transDate = sdf2.format(d1);
		//String depoName= eForm.getPayDTO().getDepositorName();
		String depoName = paymentBD.getdepoDtlCa(eForm);
		String issueName=paymentBD.getissueName(eForm);
		eForm.setActionName("");
		eForm.setFormName("");
		eForm.setFunName(purpname);
		eForm.setDeposName(depoName);
		eForm.setTranDate(transDate);
		eForm.setIssuName(issueName);
		request.setAttribute("payCaform", eForm);
		jspPage ="printPop1";	
	}
	}
	
	}
	
	
	if(jspPage.equals(""))
	{
		jspPage="SROnew";
	}
	System.out.println("Forward to SR or SP===== ? ? ? "+jspPage);
	String formName = eForm.getFormName();
	String actionName = eForm.getActionName();
	 
	ArrayList bankList = paymentBD.getBankIds();
	logger.debug("bankList is --------"+bankList);
	challanDTO.setBankList(bankList);
	eForm.setPayDTO(challanDTO);
	/*session.setAttribute("bankList", bankList);
	session.setAttribute("challanList", challanList);*/
	
	
	    
	//Code start for SP------Edited By Aakriti----------
/*	if(roleId.equalsIgnoreCase(sprole)||roleId.equalsIgnoreCase(spbankrole)) {
	    try
	    {
	    ArrayList spList = new ArrayList();
	    spList = paymentBD.getSpDetails(userId);
		System.out.println("Size of list"+spList.size());
		if(spList.size()>0)
		{	
				for(int i=0;i<spList.size();i++)
				{
					//SELECT LICENSE_NO, LICENSE_TXN_ID FROM IGRS_SP_USER_LICENSE_DETAILS
					ArrayList list =(ArrayList)spList.get(i);
					eForm.getPaymentDTO().setSpLicenseNo((String) list.get(0));
					eForm.getPaymentDTO().setSpAmt((String) list.get(1));
					 //eForm.getPaymentDTO().setSpLicenseTxnid((String) list.get(2));
					eForm.getPaymentDTO().setSpName((String) list.get(2));
					
				}
				// ----------Commented by Aakriti--------------
				 String funId = (String) session.getAttribute("functionId");
				    String subFunId = (String) session.getAttribute("subFunId");
				    if (funId == null) {
						session.setAttribute("functionId", "1001");
						funId = (String) session.getAttribute(
							"functionId");
				    }
				    String spFee = paymentBD.getSPFee(funId, forwardJsp);
				    session.setAttribute("commision", spFee);
				    double amt = Double.parseDouble(amount);
				    double totamt = amt - (Double.valueOf(spFee.trim()).doubleValue());
				    double crdtLimit = (Double.valueOf(eForm.getPaymentDTO().getSpAmt().trim()).doubleValue());
				    double remCredit = crdtLimit - totamt;
				    eForm.getPaymentDTO().setSpFnlAmt(Double.toString(remCredit));
				    session.setAttribute("totAmt",Double.toString(remCredit));
		
		      }		
		
	    }
	    catch(Exception e)
	    {
		e.printStackTrace();
	    }
	    
	    //End of SP code
	}*/
	
	
	// for challan download
	if(request.getParameter("ChallanDownload")!=null){
		 String chllnDwnldPage = (String)request.getParameter("ChallanDownload");
	
	if(!(chllnDwnldPage).equalsIgnoreCase("")){//DOWNLOAD CHALLAN
		if(chllnDwnldPage.equalsIgnoreCase("Yes")){
		logger.debug("inside challan download");
		String receivedFunId=eForm.getFuncId();
		eForm.setFunName(paymentBD.getFunctionName(receivedFunId,languageLocale));//HINDI
		ArrayList revlist=new ArrayList();
		ArrayList revSublist =new ArrayList();
		revlist = paymentBD.getRevenueHeads(receivedFunId);
		 if (revlist.size()>0){
				for(int i =0; i<revlist.size(); i++){
					revSublist.add((ArrayList)revlist.get(i));
					  if(revSublist != null && revSublist.size()>0){ 
					  for (int k=0; k< revSublist.size(); k++){
						  ArrayList compSub = (ArrayList)revSublist.get(k);
						  eForm.setRevMjrHd((String) compSub.get(0));
						  eForm.setRevSbMjrHd((String) compSub.get(1));
						  eForm.setRevMnrHd((String) compSub.get(2));
						 }
					  }
					  }
				}
		 String districtID = eForm.getParentDistId();
		 String officeId = eForm.getParentOffId();
		
		 
		
		 if(districtID.equalsIgnoreCase("NA")){
			 
			 eForm.setIsBothNA(1);
			 eForm.setDwnldChallanOfficeList(new ArrayList());
			 eForm.setDwnldChallanDistrictList(paymentBD.getDistrictList(languageLocale));
			 
			 }else{
				 if(officeId.equalsIgnoreCase("NA")){
					eForm.setIsONA(1);
					eForm.setDwnldChallanOfficeList(paymentBD.getOfficeList(districtID,languageLocale));
				 }else {
					 eForm.setIsNoneNA(1);				 
				 }
			 }
		    jspPage="challanDwnldPage";
			formName="";
			actionName="";
		}
	}
	}
	// end of challan download parameter
	
	
	
	//start of online download
	if(request.getParameter("OnlineDownload")!=null){
		 String onlineDwnldPage = (String)request.getParameter("OnlineDownload");
	
	if(!(onlineDwnldPage).equalsIgnoreCase("")){
		if(onlineDwnldPage.equalsIgnoreCase("Yes")){
		logger.debug("inside online download");
		String receivedFunId=eForm.getFuncId();
		eForm.setFunName(paymentBD.getFunctionName(receivedFunId,languageLocale));
		ArrayList revlist=new ArrayList();
		ArrayList revSublist =new ArrayList();
		revlist = paymentBD.getRevenueHeads(receivedFunId);
		 if (revlist.size()>0){
				for(int i =0; i<revlist.size(); i++){
					revSublist.add((ArrayList)revlist.get(i));
					  if(revSublist != null && revSublist.size()>0){ 
					  for (int k=0; k< revSublist.size(); k++){
						  ArrayList compSub = (ArrayList)revSublist.get(k);
						  eForm.setRevMjrHd((String) compSub.get(0));
						  eForm.setRevSbMjrHd((String) compSub.get(1));
						  eForm.setRevMnrHd((String) compSub.get(2));
						  eForm.setRevSchemeHead((String) compSub.get(3));
						 }
					  }
					  }
				}
		 String districtID = eForm.getParentDistId();
		 String officeId = eForm.getParentOffId();
		 if(districtID!=null)
		 {
			 String districtIdTreasury = paymentBD.getTreasuryDistrict(districtID);
			 eForm.setTreasuryList(paymentBD.getTreasuryList(districtIdTreasury, languageLocale));
			 
			 
		 }
		
		 if(districtID.equalsIgnoreCase("NA")){
			 
			 eForm.setIsBothNA(1);
			 eForm.setDwnldChallanOfficeList(new ArrayList());
			 eForm.setDwnldChallanDistrictList(paymentBD.getDistrictList(languageLocale));
			 
			 }else{
				 if(officeId.equalsIgnoreCase("NA")){
					eForm.setIsONA(1);
					eForm.setDwnldChallanOfficeList(paymentBD.getOfficeList(districtID,languageLocale));
					String districtIdTreasury = paymentBD.getTreasuryDistrict(districtID);
					eForm.setTreasuryList(paymentBD.getTreasuryList(districtIdTreasury, languageLocale));
				 }else {
					 eForm.setIsNoneNA(1);	
					 String districtIdTreasury = paymentBD.getTreasuryDistrict(districtID);
					 eForm.setTreasuryList(paymentBD.getTreasuryList(districtIdTreasury, languageLocale));
				 }
			 }
		 eForm.setTreasuryId("");
		    jspPage="onlineDwnldPage";
			formName="";
			actionName="";
		}
	}
	}
	// end of oline download
	
	
	
	
	//start of challanDownloadPage form
    if("challanDownloadPage".equalsIgnoreCase(formName)) {
    	
    	//start of districtToOffice action
    	if (actionName.equalsIgnoreCase("districtToOffice")){
    		eForm.setParentDistName(eForm.getDwnldChallanDistrictName());
    		String disId=eForm.getDwnldChallanDistrictId();
    		eForm.setParentDistId(disId);
    		eForm.setDwnldChallanOfficeList(paymentBD.getOfficeList(eForm.getDwnldChallanDistrictId(),languageLocale));
    	}// end of districtToOffice action
    	
    	
    	// start of officeToName action
        if (actionName.equalsIgnoreCase("officeToName")){
    		eForm.setParentOffName(eForm.getDwnldChallanOfficeName());
    		eForm.setParentOffId(eForm.getDwnldChallanOfficeId());
    	}// end of officeToName action
    	
        
        //start of printToPDF action
        if (actionName.equalsIgnoreCase("printToPDF")){
        	boolean trans = paymentBD.ChallanDwnldInsert(eForm, userId,jspPage);
        	
        	if(trans){
        		String uniqid=eForm.getDwnldChallanUniqueId();
        		logger.debug(uniqid);
        		ArrayList mainlist = new ArrayList();
        		ArrayList sublist = new ArrayList();
        		mainlist = paymentBD.getDwnldChallanInsertedDet(uniqid);
        		if(mainlist.size()>0){
        		sublist = (ArrayList)mainlist.get(0);

        		logger.debug((String)sublist.get(0));
        		logger.debug((String)sublist.get(1));
        		logger.debug((String)sublist.get(2));
        		logger.debug((String)sublist.get(3));
        		logger.debug((String)sublist.get(4));
        		logger.debug((String)sublist.get(5));
        		logger.debug((String)sublist.get(6));
        		logger.debug((String)sublist.get(7));
        		logger.debug((String)sublist.get(8));
        		logger.debug((String)sublist.get(9));
        		logger.debug((String)sublist.get(10));
        		logger.debug((String)sublist.get(11));
        		logger.debug((String)sublist.get(12));
        		logger.debug((String)sublist.get(13));
        		
        		logger.debug((String)sublist.get(14));
        		logger.debug((String)sublist.get(15));
        		logger.debug((String)sublist.get(16));
        		
        		
        		String wholeNo="";
        		String decimalNo="0";
        		String[] amountArr=sublist.get(7).toString().split("\\.");
        		
        		if(amountArr!=null && amountArr.length==2){
        			if(amountArr[0].equalsIgnoreCase("")){
        				wholeNo="0";
        			}else{
        			wholeNo=amountArr[0];
        			}
            		decimalNo=amountArr[1];
        		}else{
        			wholeNo=(String)sublist.get(7);
            		//decimalNo=amountArr[1];	
        		}
        		
        		
        		
        		
        		//start of pdf
        		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        		Document document = new Document(PageSize.A4,20,20,70,20);
        		PdfWriter.getInstance(document, baos);
        		document.open();
        	    Table datatable = new Table(28);
        	    
        	    datatable.setWidth(100);
        	    datatable.setPadding(1);
        	    
        	    Table datatable2 = new Table(5);
        	     		      
        	    //datatable2.setWidth(20);
        	    datatable2.setPadding(0);
        	    datatable2.setAlignment(Element.ALIGN_RIGHT);
        	    datatable2.setBorder(0);
        	    
        	    
        	    Cell titl1 = new Cell(new Phrase("ePRN: "+(String)sublist.get(13), FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
        	    titl1.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    //title1.setLeading(20);
        	    titl1.setColspan(5);
        	    titl1.setBorder(Rectangle.NO_BORDER);
        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable2.addCell(titl1);
        	    //datatable.setBorderWidth(2);
        	    datatable2.setAlignment(1);
        	    
        	    Cell titl2 = new Cell(new Phrase("Office Name: "+(String)sublist.get(11), FontFactory.getFont(FontFactory.COURIER, 8)));
        	    titl2.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    //title1.setLeading(20);
        	    titl2.setColspan(5);
        	    titl2.setBorder(Rectangle.NO_BORDER);
        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable2.addCell(titl2);
        	    //datatable.setBorderWidth(2);
        	    datatable2.setAlignment(1);
        	    
        	    Cell titl3 = new Cell(new Phrase("District Name: "+(String)sublist.get(10), FontFactory.getFont(FontFactory.COURIER, 8)));
        	    titl3.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    //title1.setLeading(20);
        	    titl3.setColspan(5);
        	    titl3.setBorder(Rectangle.NO_BORDER);
        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable2.addCell(titl3);
        	    //datatable.setBorderWidth(2);
        	    datatable2.setAlignment(1);
        	    
        	    Cell titl4 = new Cell(new Phrase(" . ", FontFactory.getFont(FontFactory.COURIER, 8)));
        	    titl4.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    //title1.setLeading(20);
        	    titl4.setColspan(5);
        	    titl4.setBorder(Rectangle.NO_BORDER);
        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable2.addCell(titl4);
        	    //datatable.setBorderWidth(2);
        	    datatable2.setAlignment(1);
        	   
        	    /*Cell title = new Cell(new Phrase("Vidya Bhawan, Bhopal", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    title.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    title.setLeading(20);
        	    title.setColspan(10);
        	    title.setBorder(Rectangle.NO_BORDER);
        	    //title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title);
        	    //datatable.setBorderWidth(2);
        	    datatable.setAlignment(1);*/
        	   
        	    Cell title1 = new Cell(new Phrase("FORM M.P.T.C. 7", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD)));
        	    title1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title1.setLeading(20);
        	    title1.setColspan(28);
        	    title1.setBorder(Rectangle.NO_BORDER);
        	    //title1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title1);
        	    //datatable.setBorderWidth(2);
        	    datatable.setAlignment(1);
        	    
        	    Cell title2 = new Cell(new Phrase("(See Subsidiary Rule 69)", FontFactory.getFont(FontFactory.COURIER, 8)));
        	    title2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title2.setLeading(20);
        	    title2.setColspan(28);
        	    title2.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    //Cell title3 = new Cell(new Phrase("CHALLAN No. "+(String)sublist.get(0), FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
        	    Cell title3 = new Cell(new Phrase("CHALLAN No. ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
        	    title3.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title3.setLeading(20);
        	    title3.setColspan(28);
        	    title3.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title3);
        	    //datatable.setBorderWidth(2);
        	    datatable.setAlignment(1);
        	    
        	    Cell title4 = new Cell(new Phrase("Challan of Cash Paid into the Treasury/Sub. Treasury/State/Reserve Bank of India", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
        	    title4.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title4.setLeading(20);
        	    title4.setColspan(28);
        	    title4.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title4);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    //Cell title5 = new Cell(new Phrase("at "+(String)sublist.get(11)+", "+(String)sublist.get(10)+" ("+(String)sublist.get(9)+")", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
        	    Cell title5 = new Cell(new Phrase("at ", FontFactory.getFont(FontFactory.COURIER, 10,Font.BOLD)));
        	    title5.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title5.setLeading(20);
        	    title5.setColspan(28);
        	    title5.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title5);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell title6 = new Cell(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
        	    title6.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title6.setLeading(20);
        	    title6.setColspan(28);
        	    title6.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title6);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell title7 = new Cell(new Phrase(" ", FontFactory.getFont(FontFactory.COURIER, 16,Font.BOLD)));
        	    title7.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    title7.setLeading(20);
        	    title7.setColspan(28);
        	    title7.setBorder(Rectangle.BOTTOM);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title7);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell title8 = new Cell(new Phrase("To be filled in by the Remitter", FontFactory.getFont(FontFactory.COURIER, 6, Font.BOLD)));
        	    title8.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    title8.setLeading(20);
        	    title8.setColspan(14);
        	    title8.setBorder(Rectangle.TOP);
        	    title8.setBorder(Rectangle.BOTTOM);
        	    //title7.setBorder(Rectangle.NO_BORDER);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title8);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell title9 = new Cell(new Phrase("To be filled in by the Departmental Officer or the Treasury Officer", FontFactory.getFont(FontFactory.COURIER, 6, Font.BOLD)));
        	    title9.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    title9.setLeading(20);
        	    title9.setColspan(14);
        	    title9.setBorder(Rectangle.TOP);
        	    title9.setBorder(Rectangle.BOTTOM);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(title9);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	            	   
        	    Cell subTitleTendered = new Cell(new Phrase("By whom Tendered", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleTendered.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleTendered.setLeading(20);
        	    subTitleTendered.setColspan(3);
        	    subTitleTendered.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleTendered);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleName = new Cell(new Phrase("Name or Designation & address of the person on whose behalf money is Paid", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleName.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleName.setLeading(20);
        	    subTitleName.setColspan(5);
        	    subTitleName.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleName);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleParticulars = new Cell(new Phrase("Full particulars of the remittance and/of the authority (if any)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleParticulars.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleParticulars.setLeading(20);
        	    subTitleParticulars.setColspan(5);
        	    subTitleParticulars.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleParticulars);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleAmount = new Cell(new Phrase("Amount", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleAmount.setLeading(20);
        	    subTitleAmount.setColspan(4);
        	    subTitleAmount.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleAmount);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleAccount = new Cell(new Phrase("Head of Account", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleAccount.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleAccount.setLeading(20);
        	    subTitleAccount.setColspan(4);
        	    subTitleAccount.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleAccount);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleOfficer = new Cell(new Phrase("Accounts officer by whom adjustable", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleOfficer.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleOfficer.setLeading(20);
        	    subTitleOfficer.setColspan(4);
        	    subTitleOfficer.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleOfficer);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitleOrder = new Cell(new Phrase("Order to the bank", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitleOrder.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitleOrder.setLeading(20);
        	    subTitleOrder.setColspan(3);
        	    subTitleOrder.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitleOrder);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle1 = new Cell(new Phrase("1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle1.setLeading(20);
        	    subTitle1.setColspan(3);
        	    //subTitle1.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle2 = new Cell(new Phrase("2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle2.setLeading(20);
        	    subTitle2.setColspan(5);
        	    //subTitle2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle3 = new Cell(new Phrase("3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle3.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle3.setLeading(20);
        	    subTitle3.setColspan(5);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle3);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle4 = new Cell(new Phrase("4", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle4.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle4.setLeading(20);
        	    subTitle4.setColspan(4);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle4);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle5 = new Cell(new Phrase("5", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle5.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle5.setLeading(20);
        	    subTitle5.setColspan(4);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle5);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle6 = new Cell(new Phrase("6", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle6.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle6.setLeading(20);
        	    subTitle6.setColspan(4);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle6);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle7 = new Cell(new Phrase("7", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle7.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle7.setLeading(20);
        	    subTitle7.setColspan(3);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle7);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle1_1 = new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle1_1.setLeading(20);
        	    subTitle1_1.setColspan(3);
        	    subTitle1_1.setBorder(Rectangle.TOP);
        	    subTitle1_1.setBorder(Rectangle.LEFT);
        	    subTitle1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle2_1 = new Cell(new Phrase((String)sublist.get(8)+",  "+(String)sublist.get(12), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    subTitle2_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle2_1.setLeading(20);
        	    subTitle2_1.setColspan(5);
        	    subTitle2_1.setBorder(Rectangle.TOP);
        	    subTitle2_1.setBorder(Rectangle.LEFT);
        	    subTitle2_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle2_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle3_1 = new Cell(new Phrase("Purpose - "+(String)sublist.get(3)+" Ref. No. - "+(String)sublist.get(1), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    subTitle3_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle3_1.setLeading(20);
        	    subTitle3_1.setColspan(5);
        	    subTitle3_1.setBorder(Rectangle.TOP);
        	    subTitle3_1.setBorder(Rectangle.LEFT);
        	    subTitle3_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle3_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle4_1 = new Cell(new Phrase("Rs.    \n"+wholeNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle4_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle4_1.setLeading(20);
        	    subTitle4_1.setColspan(3);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle4_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle4_2 = new Cell(new Phrase("P.  "+decimalNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle4_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle4_2.setLeading(20);
        	    subTitle4_2.setColspan(1);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle4_2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle5_1 = new Cell(new Phrase((String)sublist.get(4)+" - "+  (String)sublist.get(14)  +" , "+(String)sublist.get(5)+" - "+  (String)sublist.get(15)  +" , "+(String)sublist.get(6)+" - "+  (String)sublist.get(16), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle5_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle5_1.setLeading(20);
        	    subTitle5_1.setColspan(4);
        	    subTitle5_1.setBorder(Rectangle.TOP);
        	    subTitle5_1.setBorder(Rectangle.LEFT);
        	    subTitle5_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle5_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle6_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle6_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle6_1.setLeading(20);
        	    subTitle6_1.setColspan(4);
        	    subTitle6_1.setBorder(Rectangle.TOP);
        	    subTitle6_1.setBorder(Rectangle.LEFT);
        	    subTitle6_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle6_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle7_1 = new Cell(new Phrase("Date             correct recieve and grant receipt(Sign & full designation of the officer ordering the money to be paid in)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle7_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle7_1.setLeading(20);
        	    subTitle7_1.setColspan(3);
        	    subTitle7_1.setBorder(Rectangle.TOP);
        	    subTitle7_1.setBorder(Rectangle.LEFT);
        	    subTitle7_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle7_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle1_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle1_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle1_1_1.setLeading(20);
        	    subTitle1_1_1.setColspan(3);
        	    subTitle1_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle1_1_1.setBorder(Rectangle.LEFT);
        	    subTitle1_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle1_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle2_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle2_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle2_1_1.setLeading(20);
        	    subTitle2_1_1.setColspan(5);
        	    subTitle2_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle2_1_1.setBorder(Rectangle.LEFT);
        	    subTitle2_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle2_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle3_1_1 = new Cell(new Phrase("Total", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle3_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    subTitle3_1_1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        	    subTitle3_1_1.setLeading(20);
        	    subTitle3_1_1.setColspan(5);
        	    subTitle3_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle3_1_1.setBorder(Rectangle.LEFT);
        	    subTitle3_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle3_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle4_1_1 = new Cell(new Phrase(wholeNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle4_1_1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle4_1_1.setLeading(20);
        	    subTitle4_1_1.setColspan(3);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle4_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle4_1_2 = new Cell(new Phrase(decimalNo, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle4_1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    subTitle4_1_2.setLeading(20);
        	    subTitle4_1_2.setColspan(1);
        	   //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle4_1_2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle5_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle5_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    subTitle5_1_1.setLeading(20);
        	    subTitle5_1_1.setColspan(4);
        	    subTitle5_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle5_1_1.setBorder(Rectangle.LEFT);
        	    subTitle5_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle5_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle6_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle6_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    subTitle6_1_1.setLeading(20);
        	    subTitle6_1_1.setColspan(4);
        	    subTitle6_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle6_1_1.setBorder(Rectangle.LEFT);
        	    subTitle6_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle6_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell subTitle7_1_1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    subTitle7_1_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    subTitle7_1_1.setVerticalAlignment(Element.ALIGN_BOTTOM);
        	    subTitle7_1_1.setLeading(20);
        	    subTitle7_1_1.setColspan(3);
        	    subTitle7_1_1.setBorder(Rectangle.BOTTOM);
        	    subTitle7_1_1.setBorder(Rectangle.LEFT);
        	    subTitle7_1_1.setBorder(Rectangle.RIGHT);
        	    //subTitle3.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(subTitle7_1_1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw = new Cell(new Phrase("Amount in words: "+DateToWords.convertAmountToWords((String)sublist.get(7)), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    titleBottomRw.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw.setLeading(20);
        	    titleBottomRw.setColspan(16);
        	    titleBottomRw.setBorderWidthRight(0);
        	    titleBottomRw.setBorderColorRight(Color.white);
        	    //title2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    titleBottomRw.setBorderColorTop(Color.black);
        	    titleBottomRw.setBorderColorBottom(Color.black);
        	    titleBottomRw.setBorderWidthTop(1);
        	    titleBottomRw.setBorderWidthBottom(1);
        	    datatable.addCell(titleBottomRw);
        	    
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw1 = new Cell(new Phrase("To be used only in the case of remittances to the bank through Departmental Officer or the Treasury Officer", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    titleBottomRw1.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	    titleBottomRw1.setLeading(20);
        	    titleBottomRw1.setColspan(12);
        	    titleBottomRw1.setBorderWidthLeft(0);
        	    titleBottomRw1.setBorderColorLeft(Color.white);
        	    titleBottomRw1.setBorderColorTop(Color.black);
        	    titleBottomRw1.setBorderWidthTop(1);
        	    titleBottomRw1.setBorderColorBottom(Color.black);
        	    titleBottomRw1.setBorderWidthBottom(1);
        	    datatable.addCell(titleBottomRw1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw2 = new Cell(new Phrase("Received Payment (in words) Rupees", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
        	    titleBottomRw2.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw2.setLeading(20);
        	    titleBottomRw2.setColspan(28);
        	    titleBottomRw2.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBottomRw2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBlank = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
        	    titleBlank.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBlank.setLeading(20);
        	    titleBlank.setColspan(28);
        	    titleBlank.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBlank);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBlank1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
        	    titleBlank1.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBlank1.setLeading(20);
        	    titleBlank1.setColspan(28);
        	    titleBlank1.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBlank1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBlank2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
        	    titleBlank2.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBlank2.setLeading(20);
        	    titleBlank2.setColspan(28);
        	    titleBlank2.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBlank2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBlank3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD)));
        	    titleBlank3.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBlank3.setLeading(20);
        	    titleBlank3.setColspan(28);
        	    titleBlank3.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBlank3);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    
        	    Cell titleBottomRw3 = new Cell(new Phrase("Treasurer", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    titleBottomRw3.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw3.setLeading(20);
        	    titleBottomRw3.setColspan(7);
        	    titleBottomRw3.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBottomRw3);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw4 = new Cell(new Phrase("Accountant", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    titleBottomRw4.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw4.setLeading(20);
        	    titleBottomRw4.setColspan(7);
        	    titleBottomRw4.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBottomRw4);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw5 = new Cell(new Phrase("Date", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    titleBottomRw5.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw5.setLeading(20);
        	    titleBottomRw5.setColspan(7);
        	    titleBottomRw5.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBottomRw5);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell titleBottomRw6 = new Cell(new Phrase("Treasury Officer/Agent", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    titleBottomRw6.setHorizontalAlignment(Element.ALIGN_LEFT);
        	    titleBottomRw6.setLeading(20);
        	    titleBottomRw6.setColspan(7);
        	    titleBottomRw6.setBorder(Rectangle.NO_BORDER);
        	    datatable.addCell(titleBottomRw6);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell blankrow = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow.setLeading(20);
        	    blankrow.setColspan(28);
        	    blankrow.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	   
        	    Cell blankrow1 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow1.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow1.setLeading(20);
        	    blankrow1.setColspan(28);
        	    blankrow1.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow1);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell blankrow2 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow2.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow2.setLeading(20);
        	    blankrow2.setColspan(28);
        	    blankrow2.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow2);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    Cell blankrow3 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow3.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow3.setLeading(20);
        	    blankrow3.setColspan(28);
        	    blankrow3.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow3);
        	    datatable.setBorderWidth(1);
        	    datatable.setAlignment(1);
        	    
        	    /*Cell blankrow4 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow4.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow4.setLeading(20);
        	    blankrow4.setColspan(28);
        	    blankrow4.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow4);
        	    datatable.setBorderWidth(2);
        	    datatable.setAlignment(1);
        	    
        	    Cell blankrow5 = new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));
        	    blankrow5.setHorizontalAlignment(Element.ALIGN_CENTER);
        	    blankrow5.setLeading(20);
        	    blankrow5.setColspan(28);
        	    blankrow5.setBorder(Rectangle.NO_BORDER);
        	    //subTitleBldg2.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
        	    datatable.addCell(blankrow5);
        	    datatable.setBorderWidth(2);
        	    datatable.setAlignment(1);*/
        	   
        	    datatable.setCellsFitPage(true);
      	        
      	        document.add(datatable2);
      	        
      	      document.add(datatable);
      		    document.close();
      				response.setContentType("Challan/pdf");
      				response.setHeader("Content-Disposition", "attachment; filename=\"Challan.pdf");
      				response.setContentLength(baos.size());
      				ServletOutputStream out = response.getOutputStream();
      				baos.writeTo(out);
      				out.flush();
        		
        		
        		}
        	}// end of(trans)
    	}//end of printToPDF action
    	
        
    	jspPage="challanDwnldPage";
		formName="";
		actionName="";
	}
	//end of challanDownloadPage form
	
	
    // start of onlineDownloadPage
    if("onlineDownloadPage".equalsIgnoreCase(formName)) {
    	
    	//start of districtToOffice action
    	if (actionName.equalsIgnoreCase("districtToOffice")){
    		eForm.setParentDistName(eForm.getDwnldChallanDistrictName());
    		String disId=eForm.getDwnldChallanDistrictId();
    		eForm.setParentDistId(disId);
    		String treasuryDistrcitId = paymentBD.getTreasuryDistrict(disId);
    		eForm.setTreasuryList(paymentBD.getTreasuryList(treasuryDistrcitId, languageLocale));
    		eForm.setDwnldChallanOfficeList(paymentBD.getOfficeList(eForm.getDwnldChallanDistrictId(),languageLocale));
    		formName="";
    		   actionName="";
    		   eForm.setActionName("");
    		   eForm.setFormName("");
    		   jspPage="onlineDwnldPage";   // added by roopam : 8 Oct 2013. Bug id 930. resolved
    	}// end of districtToOffice action
    	
    	
    	// start of officeToName action
        if (actionName.equalsIgnoreCase("officeToName")){
    		eForm.setParentOffName(eForm.getDwnldChallanOfficeName());
    		eForm.setParentOffId(eForm.getDwnldChallanOfficeId());
    		formName="";
    		   actionName="";
    		   eForm.setActionName("");
    		   eForm.setFormName("");
    		   jspPage="onlineDwnldPage";  // added by roopam : 8 Oct 2013. Bug id 930. resolved
    	}// end of officeToName action
    	
        if (actionName.equalsIgnoreCase("forwardToTreasurySite"))
        {
        	
        	try{
            	logger.debug("******************In side forwardToTreasurySite...");
            	CashInternalBO bo = new CashInternalBO();
            	String crn= bo.getCRNumber();
            
            	boolean trans = paymentBD.onlineDwnldInsert(eForm, userId,crn,jspPage);
            	logger.debug("******************In side onlineDwnldInsert...");
        	
            	
            	String uniqid=eForm.getDwnldChallanUniqueId();
        		logger.debug(" Uniq ID :- "+uniqid);
        		logger.debug(uniqid);
        		ArrayList mainlist = new ArrayList();
        		ArrayList sublist = new ArrayList();
        		mainlist = paymentBD.getDwnldChallanInsertedDet(uniqid);
        		if(mainlist.size()>0){
        		sublist = (ArrayList)mainlist.get(0);

        		
        		eForm.setParentRefId((String)sublist.get(1));
        		//eForm.setParentAmount((String)sublist.get(7));
        		//eForm.setDwnldChallanAmt((String)sublist.get(7));
        		eForm.setDwnldChallanFirstName((String)sublist.get(8));
        	//	eForm.setDwnldChallanMiddleName((String)sublist.get(9));
        	//	eForm.setDwnldChallanLastName((String)sublist.get(10));
        		eForm.setDwnldChallanAddress((String)sublist.get(12));
        		
        		
        		
        		eForm.setDwnldChallanUniqueId((String)sublist.get(0));
        		}
        		eForm.setDwnldChallanUniqueId(crn);
        		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
        		   if (ipAddress == null) {  
        			   ipAddress = request.getRemoteAddr();  
        		   }
        		
        		eForm.setOnlineIp(ipAddress);
        		eForm.setCrnNumber(crn);
        		formName="";
     		   actionName="";
     		   eForm.setActionName("");
     		   eForm.setFormName("");
            	
        	}
        	catch (Exception e) {
				logger.debug("Forward to Payment Error :-"+ e.getMessage(),e);
			}
        	 jspPage="onlineDwnldPageConfirm";
        }
        
        
        //start of forwardToTreasurySite action
        if (actionName.equalsIgnoreCase("forwardToTreasurySiteBF")){
        	
        	try{
        	logger.debug("******************In side forwardToTreasurySite...");
        	CashInternalBO bo = new CashInternalBO();
        	//String crn= bo.getCRNumber();
        
        	//boolean trans = paymentBD.onlineDwnldInsert(eForm, userId,crn,jspPage);
        	logger.debug("******************In side onlineDwnldInsert...");
        	
        		String uniqid=eForm.getDwnldChallanUniqueId();
        		logger.debug(" Uniq ID :- "+uniqid);
        		logger.debug(uniqid);
        		ArrayList mainlist = new ArrayList();
        		ArrayList sublist = new ArrayList();
        		
        		
        		HashMap<String, String> mapIpAddress  = LoadMenuExternal.getInstance().getMapIP();
        		
        		String ipAddressLoggedIn = mapIpAddress.get(userId);
        		
        		//Checking for IP address while logging in and at payment time.
        		if(!ipAddressLoggedIn.equalsIgnoreCase(eForm.getOnlineIp()))
        		{
        			jspPage = "failure";
        			throw new Exception();
        		}
        		
        		
        		bo.updateIP(eForm.getCrnNumber(),eForm.getOnlineIp());
        		//mainlist = paymentBD.getDwnldChallanInsertedDet(uniqid);
        		//if(mainlist.size()>0){
        		//sublist = (ArrayList)mainlist.get(0);

        	//	logger.debug(" sublist.get(0) "+(String)sublist.get(0));
        	//	logger.debug(" sublist.get(1) "+(String)sublist.get(1));
        	//	logger.debug(" sublist.get(2) "+(String)sublist.get(2));
        	//	logger.debug(" sublist.get(3) "+(String)sublist.get(3));
        	//	logger.debug(" sublist.get(4) "+(String)sublist.get(4));
        	//	logger.debug(" sublist.get(5) "+(String)sublist.get(5));
        	//	logger.debug(" sublist.get(6) "+(String)sublist.get(6));
        	///	logger.debug(" sublist.get(7) "+(String)sublist.get(7));
        	//	logger.debug(" sublist.get(8) "+(String)sublist.get(8));
        	//	logger.debug(" sublist.get(9) "+(String)sublist.get(9));
        //		logger.debug(" sublist.get(10) "+(String)sublist.get(10));
        //		logger.debug(" sublist.get(11) "+(String)sublist.get(11));
        //		logger.debug(" sublist.get(12) "+(String)sublist.get(12));
        //		eForm.setDwnldChallanUniqueId((String)sublist.get(0));
        //		}
        //		eForm.setDwnldChallanUniqueId(crn);
        	
        	//eForm.setDwnldChallanUniqueId(crn);
        	formName="";
        	   actionName="";
        	   eForm.setActionName("");
        	   eForm.setFormName("");
        	   
        	   //set sysdate here
        	   eForm.setDateParam(paymentBD.getCurrDateTime());
        	 
        	   
        	   //String url=PaymentConstant.TREASURY_URL;
        	   String url=PaymentConstant.TREASURY_URL_TEST;

        	   logger.debug("Before  AESEncrypt ---");
        	   AESEncrypt enc = new AESEncrypt();
        	   logger.debug("After  AESEncrypt ---");
        	   
        	   OnlinePaymentDto dto = new OnlinePaymentDto();
        	   
        	  dto.setFuncId(eForm.getFuncId()) ;
        	  dto.setParentAmount(eForm.getParentAmount());
        	  dto.setParentTable(eForm.getParentTable()) ;
        	  dto.setParentKey(eForm.getParentKey());
        	  dto.setForwardPath(eForm.getForwardPath())    ;
        	  dto.setParentColumnName(eForm.getParentColumnName());
        	  
        	  dto.setParentOffId( eForm.getParentOffId())  ;
        	  dto.setParentOffName( eForm.getParentOffName());
        	  dto.setParentDistId( eForm.getParentDistId()) ;
        	  dto.setParentDistName(eForm.getParentDistName()) ;
       	   	  dto.setParentRefId( eForm.getParentRefId());
       	   String modName = (String) session.getAttribute("modName");
       	   String fnName = (String) session.getAttribute("fnName");
       	   	  dto.setModName(modName);
       	   	  dto.setFnName(fnName);
       	   	  
       	   	  dto.setCrnNumber(eForm.getCrnNumber());
       	   	  dto.setPayingAmout(eForm.getDwnldChallanAmt());
       	   	  dto.setLanguage((String)session.getAttribute("languageLocale"));
        	   dto.setUserId((String)session.getAttribute("UserId"));
        	   String formNames =  eForm.getFormName1();
        	ActionForm forms =    (ActionForm) session.getAttribute(formNames);
        	   
        	   String check = paymentBD.saveObject(eForm.getFuncId(), forms); 
        	   String check1 = paymentBD.saveObject("FUN_156", eForm);
        	   if(check!="")
        	   {
        		   dto.setFormPath(check);
        	   }
        	   if(check1!="")
        	   {
        		   dto.setPaymentFormPath(check1);
        	   }
        	   dto.setFormName(formNames);
        	  dto.setOldformName((String)session.getAttribute("OldFormName")) ;
        	   LoadMenuExternal.getInstance().getDto().put(eForm.getCrnNumber(), dto);
        	   
        	   logger.debug("Before mp_treasury_key  ---");
        	   PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
       		
        	   String keyPath=proper.getValue("mp_treasury_key");
        	   logger.debug("After mp_treasury_key  ---"+keyPath);
        	   
        	   logger.debug("Before SecretKey enycription");
        	   enc.setSecretKey(keyPath);
        	   logger.debug("After SecretKey enycription");
        	   
        	   String treasurydistId = paymentBD.getTreasuryDistrict(eForm.getParentDistId());
        	   String descriptionHOA = paymentBD.getDescriptionHOA(eForm.getRevMjrHd(),eForm.getRevSbMjrHd(),eForm.getRevMnrHd());
       		String []a = new String[]{"igrs_deptt_code","igrs_major_head","igrs_submajor_head","igrs_minor_head","igrs_scheme_head","igrs_merchant_code","Igrs_description_hoa","igrs_district_code","igrs_district_name","igrs_email","igrs_purpose","igrs_circle_code ","Igrs_parent_treasury_code","igrs_office_name","igrs_transaction_ref_no","igrs_amount","igrs_user_name"};
    		String []b = new String[]{"05",eForm.getRevMjrHd(),eForm.getRevSbMjrHd(),eForm.getRevMnrHd(),eForm.getRevSchemeHead(),"IGR",descriptionHOA,treasurydistId,  eForm.getParentDistName(),eForm.getEmailAddress(),eForm.getFunName(),"00",eForm.getTreasuryId(), eForm.getParentOffName(),eForm.getCrnNumber(),eForm.getDwnldChallanAmt(),eForm.getDwnldChallanFirstName()};
    		String urls = 	paymentBD.createUrl(a, b, "");
        	
    		logger.debug("Before SecretKey urls ENCODING...");
        	String encoded =  enc.encryptFile(urls);
        	logger.debug("After SecretKey urls ENCODING... encoded value :- "+encoded);
        	
        	logger.debug("Before SecretKey encodedUrl...");
        	String encodedUrl =URLEncoder.encode(encoded);
        	logger.debug("Before SecretKey encodedUrl...");
        	
        	logger.debug("Before SecretKey finalEncodedUrl...");
        	String finalEncodedUrl = "https://www.mptreasury.org/MPTWar/frmCyberTreasIGR.jsp?encdata="+encodedUrl+"&merchant_code=MPGOVT_IGR";
        	logger.debug("Before SecretKey finalEncodedUrl...");
        	
        	session.removeAttribute(WebConstants.SES_USER_DO);
        	
        	
        	   
        	/*   Object[] arguments = {
        			   eForm.getRevMjrHd(),
        			   eForm.getRevSbMjrHd(),
        			   eForm.getRevMnrHd(),
        			   eForm.getParentDistId(),
        			   eForm.getParentDistName(),
        			   eForm.getDwnldChallanFirstName()+" "+eForm.getDwnldChallanLastName(),
        			   eForm.getParentOffName(),
        			   eForm.getDateParam(),
        			   eForm.getFunName(),
        			   eForm.getParentRefId()
        			 };*/
        	   
        	   Object[] arguments = {
        			  
        			 };
        	   
        	   logger.debug("Before MessageFormat...");
        	   url= MessageFormat.format(url, arguments);
        	   logger.debug("After MessageFormat...");
        	  // url= "http://www.mptreasury.org/MPTWar/frmCyberTreasigrastest.jsp";
        	   
        	   logger.debug("Before eForm.setTreasuryUrl...");
        	   eForm.setTreasuryUrl(finalEncodedUrl);
        	   logger.debug("After eForm.setTreasuryUrl...");
        	   
        	   logger.debug("Before onlineDwnldPage2...");
        	   jspPage="onlineDwnldPage2";  // added by roopam : 8 Oct 2013. Bug id 930. resolved
        	   logger.debug("Before onlineDwnldPage2...");
        	   
        	}catch(Exception ex){
        		   logger.debug("After clicking on Cash radio button"+ex.getMessage());
        	}
        
}// end of action forwardToTreasurySite
   /*formName="";
   actionName="";
   eForm.setActionName("");
   eForm.setFormName("");
   jspPage="onlineDwnldPage2";*/     		// commented by roopam : 8 Oct 2013. Bug id 930. resolved
    	
    	
    	
    } // end of onlineDownloadPage
    
	
	//------------Start for SRO Page-------------//
	if(PaymentConstant.SRO_PAGE.equals(formName)) {
	    logger.debug("After clicking on Cash radio button");
	    try
	    {
		if(PaymentConstant.SEARCH_RECEIPT_ACTION.equals(actionName)) {
			 eForm.setSearchRsptID("");
			 eForm.setChallansearchRsptID("");
			 eForm.setOnlineSearchRsptID("");
			 eForm.setCheckAvlblty(0);
			String rspIds = eForm.getReceiptID();
  		    String rspId = rspIds.toLowerCase();
  		    String entAmt = eForm.getEntrAmt();
  		    String parentAmountNew =eForm.getParentAmount();
  		    
  		    logger.debug("IN ACTION........AMOUNT ENTERED BY USER....."+entAmt);
  		    logger.debug("for checking consumed receipt id...receipt id is.. "+rspId);
  		    try {
  			 
  			PaymentDTO dto = new PaymentDTO();
  			ChallanDTO dto1 = null;
  			OnlineDTO  dto2 = null;
  			dto = paymentBD.getRsptDetails(rspId, entAmt, funid,languageLocale,officeIdLoggedIn,parentAmountNew);//HINDI
  			dto.setReceiptID(rspId);
  			logger.debug ("INSIDE ACTION..... THE OFFICE ID....."+dto.getOffice());
  			eForm.setCheckAvlblty(1);
  			request.setAttribute("rsptDetails",dto);
  			request.setAttribute("challanRsptDetails",dto1);
  			request.setAttribute("onlineRsptDetails",dto2);
  			//saveToken(request);//CSRF
  			
  		    }
  		    catch(Exception e){
  			e.printStackTrace();
  			String msg = e.getMessage();
  			logger.debug(e);
  			if (msg.equalsIgnoreCase("30000")){
 				eForm.setSearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30000);
 	 			forwardJsp = jspPage;
 			     }
  			if (msg.equalsIgnoreCase("30001")){
 				eForm.setSearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30001);
 	 			forwardJsp = jspPage;
 			     }
  			if (msg.equalsIgnoreCase("30002")){
 				eForm.setSearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30002);
 	 			forwardJsp = jspPage;
 			     }
  			if (msg.equalsIgnoreCase("30003")){
 				eForm.setSearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30003);
 	 			forwardJsp = jspPage;
 			     }
  			/*if (msg.equalsIgnoreCase("30004")){
 				eForm.setSearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30004);
 	 			forwardJsp = jspPage;
 			     }*/
  		
  			
  			forwardJsp = jspPage;
  			}
  		  forwardJsp = jspPage;
  		 
		}
		
		if(PaymentConstant.SEARCH_CHALLAN_RECEIPT_ACTION.equals(actionName)) {
			 eForm.setSearchRsptID("");
			 eForm.setChallansearchRsptID("");
			 eForm.setOnlineSearchRsptID("");
			 eForm.setCheckAvlblty(0);
			 logger.debug("For checking consumed receipt ids");
			 logger.debug("on the click of search button");
		    
 		    
 		    String rspIds = eForm.getChallanNO();
 		    String rspId = rspIds.toLowerCase();
 		    String entAmt = eForm.getChallanentrAmt();
 		    String chRefIdid = eForm.getChallanRefNo();
 		   // String bankid = eForm.getChallanBankId(); // commentd to remove bankid
 		    logger.debug("IN ACTION........AMOUNT ENTERED BY USER....."+entAmt);
 		    //System.out.println("value of challan id entered is.."+rspId);
 		    logger.debug("for checking consumed receipt id...receipt id is.. "+rspId);
 		    try {
 			 
 			PaymentDTO dto =null;
 			ChallanDTO dto1 = new ChallanDTO();
 			OnlineDTO dto2 = null;
 		  //dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, bankid);
 			dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, chRefIdid);
 			dto1.setChallanNo(rspId);
 			//logger.debug ("INSIDE ACTION..... THE OFFICE ID....."+dto.getOffice());
 			eForm.setCheckAvlblty(1);
 			request.setAttribute("challanRsptDetails",dto1);
 			request.setAttribute("rsptDetails",dto);
 			request.setAttribute("onlineRsptDetails",dto2);
 			logger.debug("the attribute in request."+(PaymentDTO)request.getAttribute("rsptDetails"));
 			//request.setAttribute("rsptDetails","");
 			//saveToken(request);//CSRF
 			
 		    }
 		    catch(Exception e){
 			e.printStackTrace();
 			logger.debug(e);
 			String msg =e.getMessage();
 			logger.debug(msg);
 			if (msg.equalsIgnoreCase("20000")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
 	 			forwardJsp = jspPage;
 			}
 			if (msg.equalsIgnoreCase("20001")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
 	 			forwardJsp = jspPage;
 			}
 			if (msg.equalsIgnoreCase("20002")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
 	 			forwardJsp = jspPage;
 			}
 			if (msg.equalsIgnoreCase("20003")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
 	 			forwardJsp = jspPage;
 			}
 			if (msg.equalsIgnoreCase("20004")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
 	 			forwardJsp = jspPage;
 			}
 			if (msg.equalsIgnoreCase("20005")){
 				eForm.setChallansearchRsptID("NoReceiptId");
 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
 	 			forwardJsp = jspPage;
 			}
 			
 			forwardJsp = jspPage;
 			
 			//forwardJsp = "SP";
 		    }
 		  forwardJsp = jspPage;
 		  //forwardJsp = "SP";
		}
		
		
		if(PaymentConstant.SEARCH_ONLINE_RECEIPT_ACTION.equals(actionName)) {
			 eForm.setSearchRsptID("");
			 eForm.setChallansearchRsptID("");
			 eForm.setOnlineSearchRsptID("");
			 eForm.setCheckAvlblty(0);
			 logger.debug("For checking consumed receipt ids");
			 logger.debug("on the click of search button");
		    
		    
		    String rspIds = eForm.getOnlineCinNo();
		    String rspId = rspIds.toLowerCase();
		    String entAmt = eForm.getOnlineEntrAmt();
		    logger.debug("IN ACTION........AMOUNT ENTERED BY USER....."+entAmt);
		    logger.debug("for checking consumed receipt id...receipt id is.. "+rspId);
		    try {
			 
			PaymentDTO dto =null;
			ChallanDTO dto1 = null;
			OnlineDTO  dto2 = new OnlineDTO();
		    dto2 = paymentBD.getOnlineRsptDetails(rspId, entAmt, funid);
			dto2.setOnlineCIN(rspId);
			eForm.setCheckAvlblty(1);
			request.setAttribute("challanRsptDetails",dto1);
			request.setAttribute("rsptDetails",dto);
			request.setAttribute("onlineRsptDetails",dto2);
			//saveToken(request);//CSRF
			
		    }
		    catch(Exception e){
			e.printStackTrace();
			logger.debug(e);
			String msg =e.getMessage();
			logger.debug(msg);
			if (msg.equalsIgnoreCase("40000")){
				eForm.setOnlineSearchRsptID("NoReceiptId");
	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
	 			forwardJsp = jspPage;
			}
			if (msg.equalsIgnoreCase("40001")){
				eForm.setOnlineSearchRsptID("NoReceiptId");
	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
	 			forwardJsp = jspPage;
			}
			if (msg.equalsIgnoreCase("40002")){
				eForm.setOnlineSearchRsptID("NoReceiptId");
	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
	 			forwardJsp = jspPage;
			}
			if (msg.equalsIgnoreCase("40003")){
				eForm.setOnlineSearchRsptID("NoReceiptId");
	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
	 			forwardJsp = jspPage;
			}
			
			
			forwardJsp = jspPage;
			
			//forwardJsp = "SP";
		    }
		  forwardJsp = jspPage;
		  //forwardJsp = "SP";
		}
		
		
		
		
		
		
		
		if(PaymentConstant.NEXT_ACTION.equals(actionName)) {
			PaymentDTO dtoPayment = eForm.getPaymentDTO();
		
			logger.debug("ON CLICK OF PAYMENT BUTTON...Checking for consumed id");
			if(!dtoPayment.getSelectType().equalsIgnoreCase("online")){
		   // if(!isTokenValid(request)) {//CSRF
			forwardJsp = jspPage;//CSRF
			//forwardJsp = "SP";
		   // }//CSRF
		}
		    if(
		    		//isTokenValid(request)  || //CSRF
		    		true ||
		    		dtoPayment.getSelectType().equalsIgnoreCase("online")) {
		    	logger.debug("ON CLICK OF PAYMENT BUTTON...Checking for consumed id");
        		  
        		    String selectType = dtoPayment.getSelectType();
        		    logger.debug("selectType:-"+selectType);
        		    //PaymentDTO dto = new PaymentDTO();
        		   // ChallanDTO dto2 = new ChallanDTO();
        		   // String receiptIds = eForm.getReceiptID();
        		   // String receiptId = receiptIds.toLowerCase();
        		   // String cashAmt = "";
        		   // double totalAmt = 0.0;
        		  
        		    if("cash".equals(selectType)) 
        		    {
        			try
        			{
        			
        			
        			    String entAmt = eForm.getEntrAmt();
        			    double d = Math.ceil(Double.parseDouble(entAmt));
        			    int entAmtnew = (int) d;
        			    
        			  String amount=  Integer.toString(entAmtnew);
        			  eForm.setEntrAmt(amount);
        			    eForm.setPayMode("1");
        			    eForm.setFuncId(funid);
        			    eForm.setLoggedUser(userId);
        			    
        			    String payableAmount = paymentBD.cashPayableAmt(eForm);
        			    double d1 = Math.ceil(Double.parseDouble(payableAmount));
        			    int entAmtnew1 = (int) d1;
        			    
        			  String amount1=  Integer.toString(entAmtnew1);
        			 if(amount.equals(amount1)){
        			    
        			  	boolean trans = paymentBD.PaymentTransactionFinalNew(eForm);
                			if (trans){
                				String transid = eForm.getTransId();
                				request.setAttribute("PayTransId", transid);
                				request.setAttribute("PaidAmt", amount);
                				logger.debug("in action class the value in request attribute >>>>>>>>>>>>>"+eForm.getParentKey());
                				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
               				    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
                				jspPage = "processPayment1";
                				forwardJsp = jspPage;
                				session.removeAttribute("transactionId");
                				
                				 session.removeAttribute("serviceFee");
                				// session.removeAttribute("parentFunId");
                			}}
        			 else{
        				 
        				 jspPage = "refreshCancelInvalid";
         	  			forwardJsp = jspPage;
        			 }
                			/*else {
                				jspPage = "checkPayment";
                			}*/              			
                		}catch (NullPointerException ne) {
                			jspPage = "checkPayment";
                			ne.printStackTrace();
                				logger.error("Null Exception at PaymentTransactionFinal  in DAO " + ne);
                				forwardJsp = jspPage;
                		    }
                	   	    catch (SQLException se) {
                	   	    	jspPage = "checkPayment";
                	   	    	se.printStackTrace();
                	   		logger.error("SQL Exception at PaymentTransactionFinal  in DAO " + se); 
                	   		forwardJsp = jspPage;
                			}
                		    catch(Exception e){

                		    	String msg = e.getMessage();
                		    	logger.error(" Exception at PaymentTransactionFinal in action " + e);
                		    	if( msg.equalsIgnoreCase("30000")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30000);
                    	  			jspPage = "refreshCancel";
                    	  			forwardJsp = jspPage;
                		    	}
                		    	else if( msg.equalsIgnoreCase("30007")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			//request.setAttribute("mesgBundle", PaymentConstant.ERROR_30001);
                		    		jspPage = "refreshCancel";
                    	  			forwardJsp = jspPage;
                		    	}
                		    	else if( msg.equalsIgnoreCase("30010")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			//request.setAttribute("mesgBundle", PaymentConstant.ERROR_30001);
                		    		jspPage = "refreshCancelInvalid";
                    	  			forwardJsp = jspPage;
                		    	}
                		    	
                		    	else if( msg.equalsIgnoreCase("30001")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30001);
                    	  			forwardJsp = jspPage;
                		    	}
                		    	else if( msg.equalsIgnoreCase("30002")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30002);
                    	  			forwardJsp = jspPage;
                		    	}
                		    	else if( msg.equalsIgnoreCase("30003")){
                		    		eForm.setSearchRsptID("NoReceiptId");
                    	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_30003);
                    	  			forwardJsp = jspPage;
                		    	}else{
                		    		jspPage = "checkPayment";
                		    		forwardJsp = jspPage;
                		    	}			
                		    
                		    }
                		    }
        		    
        		    if("challan".equals(selectType))
        		    {
        		    try{
        		    	
        		    	//if(roleId.equalsIgnoreCase(rurole)){
        		    		
        		    		
        		    		//method for getting dist id and office id from challan id.
        		    		//eform.getChallanRefNo()
        		    		String[] distOfficeId = paymentBD.getDistIdOfficeIdForRUChallan(eForm);
        		    		
        		    		if(distOfficeId!=null){
        		    		eForm.setParentDistId(distOfficeId[0].trim());
        		    		eForm.setParentOffId(distOfficeId[1].trim());
        		    		}else{
        		    			eForm.setParentDistId("0");
            		    		eForm.setParentOffId("NA");	
        		    		}
        		    		
        		    	//}
        		    	
        		    	eForm.setFuncId(funid);
        			    eForm.setLoggedUser(userId);
        			    eForm.setPayMode("2");
        			    boolean challantrans = paymentBD.PaymentChallanTransactionFinal(eForm);
        			    if (challantrans){
            				String transid = eForm.getTransId();
            				request.setAttribute("PayTransId", transid);
            				request.setAttribute("PaidAmt", eForm.getChallanentrAmt());
            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
           				    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
            				jspPage = "processPayment2";
            				forwardJsp = jspPage;
            			}
        		    }catch (NullPointerException ne) {
            			jspPage = "checkPayment";
        				logger.error("Null Exception at PaymentTransactionFinal  in action " + ne);
        				forwardJsp = jspPage;
        		    }
        	   	    catch (SQLException se) {
        	   	    	jspPage = "checkPayment";
        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
        	   		forwardJsp = jspPage;
        			}
        		    catch(Exception ex){
        		    	String msg = ex.getMessage();
        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
        		    	if( msg.equalsIgnoreCase("20000")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("20001")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("20002")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("20003")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("20004")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("20005")){
        		    		eForm.setChallansearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
            	  			forwardJsp = jspPage;
        		    	}else{
        		    		jspPage = "checkPayment";
        		    		forwardJsp = jspPage;
        		    	}
        			
        		    }
        			
        		    }
        		    
        		    if("online".equals(selectType))
        		    {
        		    try{
        		    	
        		    	//if(roleId.equalsIgnoreCase(rurole)){
        		    		
        		    		
        		    		//method for getting dist id and office id from challan id.
        		    		//eform.getChallanRefNo()
        		    		String[] distOfficeId = paymentBD.getDistIdOfficeIdForRUChallan(eForm);
        		    		
        		    		if(distOfficeId!=null){
        		    		eForm.setParentDistId(distOfficeId[0].trim());
        		    		eForm.setParentOffId(distOfficeId[1].trim());
        		    		}else{
        		    			eForm.setParentDistId("0");
            		    		eForm.setParentOffId("NA");	
        		    		}
        		    		
        		    	//}
        		    	
        		    	eForm.setFuncId(funid);
        			    eForm.setLoggedUser(userId);
        			    eForm.setPayMode("3");
        			    boolean challantrans = paymentBD.PaymentOnlineTransactionFinal(eForm);
        			    if (challantrans){
            				String transid = eForm.getTransId();
            				request.setAttribute("PayTransId", transid);
            				session.setAttribute("PayTransId", transid);
            				session.setAttribute("CRN", eForm.getCrnNumber());
            				request.setAttribute("PaidAmt", eForm.getOnlineEntrAmt());
            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
           				    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
            				jspPage = "processPayment4";
            				forwardJsp = jspPage;
            			}
        		    }catch (NullPointerException ne) {
            			jspPage = "checkPayment";
        				logger.error("Null Exception at PaymentTransactionFinal  in action " + ne);
        				forwardJsp = jspPage;
        		    }
        	   	    catch (SQLException se) {
        	   	    	jspPage = "checkPayment";
        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
        	   		forwardJsp = jspPage;
        			}
        		    catch(Exception ex){
        		    	String msg = ex.getMessage();
        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
        		    	if( msg.equalsIgnoreCase("40000")){
        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("40001")){
        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("40002")){
        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else if( msg.equalsIgnoreCase("40003")){
        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
            	  			forwardJsp = jspPage;
        		    	}
        		    	else{
        		    		jspPage = "checkPayment";
        		    		forwardJsp = jspPage;
        		    	}
        			
        		    }
        			
        		    }
        		    
        		    /*if("challan".equals(selectType)) 		    {
        		    	eForm.setFuncId(funid);
        			    eForm.setLoggedUser(userId);
        			 logger.debug("eForm.getPaymentList():-"+eForm.getPaymentList());
        			 if(eForm.getPaymentList()!=null) {
        			 
        			     	for (int i = 0; i < eForm.getPaymentList().size(); i++) {
        			     		  eForm.setPayMode("IGRS_PT_002");
        			     	    getStatus = paymentBD.getChallanDetails(eForm, 
        			     		    			amount);
        			     	    eForm.getPaymentDTO().setSelectType("");
        			     	   session.setAttribute("PayMode", "IGRS_PT_002"); 
        			     	    if ("fail".equalsIgnoreCase(getStatus)) {
        			     		session.setAttribute("status", getStatus);
        			     		logger.debug("inside Challan Payment");
        			     		//return mapping.findForward("checkPayment");
        			     		forwardJsp = "checkPayment";
        			     	    } else {
        			     		session.setAttribute("status", getStatus);
        			     		 double totamt = eForm.getTotAmt();
        			     		String finalamt = String.valueOf(totamt); 
        			     		session.setAttribute("totamt", 
        			     				String.valueOf(totalAmt));
        			     		PaymentDTO paymentDto = new PaymentDTO();
        			     		paymentDto = null;
        			     		eForm.setPaymentDTO(paymentDto);
        			     		logger.debug("inside Challan Process Payment");
        			     		 forwardJsp = "processPayment";
        			     		//return mapping.findForward("processPayment");
        			     	    }
        			     	}
        			 }
        		    }*/
        		    //resetToken(request); //ramesh commented on 14 dec 12
        		   eForm.setActionName(""); //ramesh commented on 14 dec 12
        		  // eForm.setFormName(""); //ramesh commented on 14 dec 12
		    }
		}
		if("Reset".equalsIgnoreCase(actionName)){
			logger.info("INSIDE RESET IN ACTION CLASS");
			//eForm = new PaymentForm();
		    request.removeAttribute("PaidAmt");
		    request.removeAttribute("PayTransId");
		    request.removeAttribute("rsptDetails");
		    request.removeAttribute("challanRsptDetails");
		    request.removeAttribute("onlineRsptDetails");
		    //session.removeAttribute("totamt");
		    request.removeAttribute("mesgBundle");
			//eForm.setReceiptID("");
			//eForm.setEntrAmt("");
			eForm.setChallanNO("");
			eForm.setChallanRefNo("");
			eForm.setChallanentrAmt("");
			//eForm.setSearchRsptID("");
			eForm.setChallansearchRsptID("");
			eForm.setOnlineSearchRsptID("");
			eForm.setOnlineCinNo("");
			eForm.setOnlineEntrAmt("");
		eForm.setCheckAvlblty(0);
			eForm.setChallanBankId("");
			eForm.getPayDTO().setBankList(new ArrayList());
			//eForm.getPaymentDTO().setReceiptID("");
			//eForm.getPaymentDTO().setAmount(0);
			//request.setAttribute("rsptDetails","");
			//request.setAttribute("rsptDetails",null);
			logger.info("AFTER RESET ACTION ");
			jspPage ="SROnew";
			forwardJsp = jspPage;
		}
		if("emptyValue".equalsIgnoreCase(actionName)){
			request.setAttribute("rsptDetails","");
			request.setAttribute("rsptDetails",null);
			request.setAttribute("challanRsptDetails","");
			request.setAttribute("challanRsptDetails",null);
			 eForm.setSearchRsptID("");
			 eForm.setChallansearchRsptID("");
			forwardJsp = jspPage;
		}
		
		
		if ("selectedCash".equals(actionName))
		    {
			//Start-----done by akansha on 5thfeb for cash automation issue
		    	eForm.setRadioSelect("cash");
		    	//eForm.setCrEntrAmt(parentAmount);
		    	
		    		String payableAmount = paymentBD.cashPayableAmt(eForm);
	    		
		    		//boolean paidAmount = paymentBD.cashCheck(eForm);
		    		
		    		
	    		eForm.setParentAmount(payableAmount);
	    		
	    		
			//	request.setAttribute("BalanceparentAmount", amount);

		    	/*else{
				eForm.setParentAmount(parentAmount);
				request.setAttribute("BalanceparentAmount", parentAmount);
		    	}*/
		    	//end
				eForm.setCheckAvlblty(0);
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanBankId("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setDwnldChallanAddress("");
				eForm.setDwnldChallanFirstName("");
				eForm.setDwnldChallanMiddleName("");
				eForm.setDwnldChallanLastName("");
				eForm.setDwnldChallanAmt("");
				//akansha
				jspPage = "cashCreate";
				forwardJsp = jspPage;
				actionName ="";
		    	//forwardJsp = "SP";
		    	//saveToken(request);//CSRF
		    }
		
		
		    if ("selectedChallan".equals(actionName))
		    {
		    	eForm.setRadioSelect("challan");
		    	//eForm.setCrEntrAmt(parentAmount);
				eForm.setParentAmount(parentAmount);
				eForm.setCheckAvlblty(0);
				//eForm.setChallanBankId("");
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setDwnldChallanAddress("");
				eForm.setDwnldChallanFirstName("");
				eForm.setDwnldChallanMiddleName("");
				eForm.setDwnldChallanLastName("");
				eForm.setDwnldChallanAmt("");
		    	
		    	forwardJsp = jspPage;
		    	//forwardJsp = "SP";
		    	//saveToken(request);//CSRF
		    	//return mapping.findForward(forwardJsp);
		    }
		    if ("selectedCredit".equals(actionName))
		    {
		    	eForm.setRadioSelect("credit");
		    	eForm.setCrEntrAmt(parentAmount);
				eForm.setParentAmount(parentAmount);
				eForm.setCheckAvlblty(0);
				eForm.setChallanBankId("");
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setDwnldChallanAddress("");
				eForm.setDwnldChallanFirstName("");
				eForm.setDwnldChallanMiddleName("");
				eForm.setDwnldChallanLastName("");
				eForm.setDwnldChallanAmt("");
		    	
		    	forwardJsp = jspPage;
		    	//forwardJsp = "SP";
		    //	saveToken(request);//CSRF
		    }
		    if ("selectedOnline".equals(actionName))
		    {
		    	eForm.setRadioSelect("online");
		    	//eForm.setCrEntrAmt(parentAmount);
				eForm.setParentAmount(parentAmount);
				eForm.setCheckAvlblty(0);
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanBankId("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setDwnldChallanAddress("");
				eForm.setDwnldChallanFirstName("");
				eForm.setDwnldChallanMiddleName("");
				eForm.setDwnldChallanLastName("");
				eForm.setDwnldChallanAmt("");
		    	eForm.setEmailAddress("");
		    	forwardJsp = jspPage;
		    	//forwardJsp = "SP";
		    	//saveToken(request);//CSRF
		    }
		    
		 
		 if ( ("challancheck".equals(actionName)))  { 
		     logger.debug("I am pay Action 8------------");
		     PaymentDTO dto = eForm.getPaymentDTO();
		     String chkCount = dto.getCheckButton();
		     int i = chkCount == null ? 0 :Integer.parseInt(chkCount);
		     getStatus = paymentBD.getChallanNoDetails(
				    eForm,i );
		     //session.setAttribute("status",getStatus);
		     forwardJsp = jspPage;
		  //   forwardJsp = "SP";
		     //saveToken(request);//CSRF
		 } 
		
	    }catch(Exception ex)
    	{
    	    ex.printStackTrace();
    	}

	}
	
	
	// if ("sp".equalsIgnoreCase(forwardJsp)) 
	//    {
	//------------Start---------Commented by Aakriti-------------  
	   /*  if("SPSearchPage".equals(formName))
        	     {
			
        			if ("spProcess".equalsIgnoreCase(eForm.getPaymentDTO().getSpProcess()))
        	   // if ("spProcess".equals(actionName))
        			{
        			    eForm.getPaymentDTO().setSpProcess("");
        			    PaymentDTO paymentDTO = new PaymentDTO();
        			    paymentDTO = eForm.getPaymentDTO();
        			    String spTxnId = paymentBD.setSpDetails(paymentDTO);
        			    
        			    session.setAttribute("status", spTxnId);
        			    session.setAttribute(
        				    "totamt",
        				    (String) session
        					    .getAttribute("amount"));
        			    PaymentDTO paymentDto = new PaymentDTO();
        			    paymentDto = null;
        			    eForm.setPaymentDTO(paymentDto);
        			    logger.debug("inside SPProcess");
        			    return mapping.findForward("processPayment");
        	
        			}
        	
        			return mapping.findForward(forwardJsp);
        	    }*/
	//-------------End Commented by Aakriti-----------------
	
	//Start SP page for avail purpose-----Added by Aakriti------
	
	 if("SPSearchPage1".equalsIgnoreCase(formName)){

		    try
		    {
			if(PaymentConstant.SEARCH_CHALLAN_RECEIPT_ACTION.equals(actionName)) {
				 eForm.setSearchRsptID("");
				 eForm.setChallansearchRsptID("");
				 eForm.setOnlineSearchRsptID("");
				 eForm.setCheckAvlblty(0);
				 logger.debug("For checking consumed receipt ids");
				 logger.debug("on the click of search button");
			    
	 		    
	 		    String rspIds = eForm.getChallanNO();
	 		    String rspId = rspIds.toLowerCase();
	 		    String entAmt = eForm.getChallanentrAmt();
	 		    String chRefIdid = eForm.getChallanRefNo();
	 		    try {
	 			 
	 			PaymentDTO dto =null;
	 			ChallanDTO dto1 = new ChallanDTO();
	 			OnlineDTO dto2 =null;
	 		  //dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, bankid);
	 			dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, chRefIdid);
	 			dto1.setChallanNo(rspId);
	 			eForm.setCheckAvlblty(1);
	 			request.setAttribute("challanRsptDetails",dto1);
	 			request.setAttribute("onlineRsptDetails",dto2);
	 			request.setAttribute("rsptDetails",dto);
	 			logger.debug("the attribute in request."+(PaymentDTO)request.getAttribute("rsptDetails"));
	 			//saveToken(request);//CSRF
	 			
	 		    }
	 		    catch(Exception e){
	 			e.printStackTrace();
	 			logger.debug(e);
	 			String msg =e.getMessage();
	 			logger.debug(msg);
	 			if (msg.equalsIgnoreCase("20000")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
	 	 			forwardJsp = "SP1";
	 			}
	 			if (msg.equalsIgnoreCase("20001")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
	 	 			forwardJsp = "SP1";
	 			}
	 			if (msg.equalsIgnoreCase("20002")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
	 	 			forwardJsp = "SP1";
	 			}
	 			if (msg.equalsIgnoreCase("20003")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
	 	 			forwardJsp = "SP1";
	 			}
	 			if (msg.equalsIgnoreCase("20004")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
	 	 			forwardJsp = "SP1";
	 			}
	 			if (msg.equalsIgnoreCase("20005")){
	 				eForm.setChallansearchRsptID("NoReceiptId");
	 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
	 	 			forwardJsp = "SP1";
	 			}
	 			
	 			forwardJsp = "SP1";
	 			}
	 		  forwardJsp = "SP1";
	 		 
			}
			
			if(PaymentConstant.SEARCH_ONLINE_RECEIPT_ACTION.equals(actionName)) {
				 eForm.setSearchRsptID("");
				 eForm.setChallansearchRsptID("");
				 eForm.setOnlineSearchRsptID("");
				 eForm.setCheckAvlblty(0);
				 logger.debug("For checking consumed receipt ids");
				 logger.debug("on the click of search button");
			    
			    
			    String rspIds = eForm.getOnlineCinNo();
			    String rspId = rspIds.toLowerCase();
			    String entAmt = eForm.getOnlineEntrAmt();
			    logger.debug("IN ACTION........AMOUNT ENTERED BY USER....."+entAmt);
			    logger.debug("for checking consumed receipt id...receipt id is.. "+rspId);
			    try {
				 
				PaymentDTO dto =null;
				ChallanDTO dto1 = null;
				OnlineDTO  dto2 = new OnlineDTO();
			    dto2 = paymentBD.getOnlineRsptDetails(rspId, entAmt, funid);
				dto2.setOnlineCIN(rspId);
				eForm.setCheckAvlblty(1);
				request.setAttribute("challanRsptDetails",dto1);
				request.setAttribute("rsptDetails",dto);
				request.setAttribute("onlineRsptDetails",dto2);
				//saveToken(request);//CSRF
				
			    }
			    catch(Exception e){
				e.printStackTrace();
				logger.debug(e);
				String msg =e.getMessage();
				logger.debug(msg);
				if (msg.equalsIgnoreCase("40000")){
					eForm.setOnlineSearchRsptID("NoReceiptId");
		 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
		 			forwardJsp = "SP1";
				}
				if (msg.equalsIgnoreCase("40001")){
					eForm.setOnlineSearchRsptID("NoReceiptId");
		 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
		 			forwardJsp = "SP1";
				}
				if (msg.equalsIgnoreCase("40002")){
					eForm.setOnlineSearchRsptID("NoReceiptId");
		 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
		 			forwardJsp = "SP1";
				}
				if (msg.equalsIgnoreCase("40003")){
					eForm.setOnlineSearchRsptID("NoReceiptId");
		 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
		 			forwardJsp = "SP1";
				}
				
				
				forwardJsp = "SP1";
				
				//forwardJsp = "SP";
			    }
			  forwardJsp = "SP1";
			  //forwardJsp = "SP";
			}
			if(PaymentConstant.NEXT_ACTION.equals(actionName)) {
				 PaymentDTO dtoPayment = eForm.getPaymentDTO();
				 String selectType = dtoPayment.getSelectType();
				logger.debug("ON CLICK OF PAYMENT BUTTON-SP1 Page");
				if(!dtoPayment.getSelectType().equalsIgnoreCase("online")){
			    //if(!isTokenValid(request)) {//CSRF
				forwardJsp = "SP1";//CSRF
				//forwardJsp = "SP";
			    //}//CSRF
				}
			    if(
			    		//isTokenValid(request) ||//CSRF
			    		true ||
			    		"online".equalsIgnoreCase(selectType)) {
			    	logger.debug("ON CLICK OF PAYMENT BUTTON-SP1 Page");
	        		   
	        		    
	        		    logger.debug("selectType:-"+selectType);
	        		    //PaymentDTO dto = new PaymentDTO();
	        		    //ChallanDTO dto2 = new ChallanDTO();
	        		    //String receiptIds = eForm.getReceiptID();
	        		    //String receiptId = receiptIds.toLowerCase();
	        		    //String cashAmt = "";
	        		   // double totalAmt = 0.0;
	        		  
	        		    if("challan".equals(selectType))
	        		    {
	        		    try{
	        		    	eForm.setFuncId(funid);
	        		    	String funId=eForm.getFuncId();
	        		    	boolean challantrans=false;
	        			    eForm.setLoggedUser(userId);
	        			    eForm.setPayMode("2");
	        			    ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
	        			    String licNo = spbd.getLicenseNumber(userId);
	        			    eForm.setSpLicenceNo(licNo);
	        			    if(funId.equalsIgnoreCase("FUN_300")){
	        			    challantrans = paymentBD.PaymentChallanAvailTransactionFinal(eForm,session);
	        			    }
	        			    if(funId.equalsIgnoreCase("FUN_200")){
		        			    challantrans = paymentBD.PaymentChallanAvailTransactionFinal2(eForm,session);
		        			    }
	        			    if (challantrans){
	            				String transid = eForm.getTransId();
	            				request.setAttribute("PayTransId", transid);
	            				request.setAttribute("PaidAmt", eForm.getChallanentrAmt());
	            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
	            				request.setAttribute("parentTableAftrPay", eForm.getParentTable());
	            				jspPage = "processPayment2";
	            				forwardJsp = jspPage;
	            			}
	        		    }catch (NullPointerException ne) {
	            			jspPage = "checkPayment";
	        				logger.error("Null Exception at PaymentTransactionFinal  in action " + ne);
	        				forwardJsp = jspPage;
	        		    }
	        	   	    catch (SQLException se) {
	        	   	    	jspPage = "checkPayment";
	        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
	        	   		forwardJsp = jspPage;
	        			}
	        		    catch(Exception ex){
	        		    	String msg = ex.getMessage();
	        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
	        		    	if( msg.equalsIgnoreCase("20000")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20001")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20002")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20003")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20004")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20005")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
	            	  			forwardJsp = "SP1";
	        		    	}else{
	        		    		jspPage = "checkPayment";
	        		    		forwardJsp = jspPage;
	        		    	}
	        			
	        		    }
	        			
	        		    }
	        		    
	        		    
	        		    
	        		    if("online".equals(selectType))
	        		    {
	        		    try{
	        		    	eForm.setFuncId(funid);
	        		    	String funId=eForm.getFuncId();
	        		    	boolean challantrans=false;
	        			    eForm.setLoggedUser(userId);
	        			    eForm.setPayMode("3");
	        			    ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
	        			    String licNo = spbd.getLicenseNumber(userId);
	        			    eForm.setSpLicenceNo(licNo);
	        			    if(funId.equalsIgnoreCase("FUN_200")){
	        			    challantrans = paymentBD.PaymentOnlineTransactionFinalSP(eForm,session);
	        			    }
	        			    if(funId.equalsIgnoreCase("FUN_300")){
		        			    challantrans = paymentBD.PaymentOnlineTransactionFinalSP2(eForm,session);
		        			    }
	        			    if (challantrans){
	            				String transid = eForm.getTransId();
	            				request.setAttribute("PayTransId", transid);
	            				session.setAttribute("PayTransId", transid);
	            				session.setAttribute("CRN", eForm.getCrnNumber());
	            				request.setAttribute("PaidAmt", eForm.getOnlineEntrAmt());
	            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
	           				    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
	            				jspPage = "processPayment4";
	            				forwardJsp = jspPage;
	            			}
	        		    }catch (NullPointerException ne) {
	            			jspPage = "checkPayment";
	        				logger.debug("Null Exception at PaymentTransactionFinal  in action " + ne.getMessage(),ne);
	        				forwardJsp = jspPage;
	        		    }
	        	   	    catch (SQLException se) {
	        	   	    	jspPage = "checkPayment";
	        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
	        	   		forwardJsp = jspPage;
	        			}
	        		    catch(Exception ex){
	        		    	String msg = ex.getMessage();
	        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
	        		    	if( msg.equalsIgnoreCase("40000")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40001")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40002")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40003")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
	            	  			forwardJsp = "SP1";
	        		    	}
	        		    	else{
	        		    		jspPage = "checkPayment";
	        		    		forwardJsp = jspPage;
	        		    	}
	        			
	        		    }
	        			
	        		    }
	        		    
			    }
			}
			if("Reset".equalsIgnoreCase(actionName)){
				logger.info("INSIDE RESET IN ACTION CLASS");
				//eForm = new PaymentForm();
			    request.removeAttribute("PaidAmt");
			    request.removeAttribute("PayTransId");
			    request.removeAttribute("rsptDetails");
			    request.removeAttribute("challanRsptDetails");
			    request.removeAttribute("onlineRsptDetails");
			   // session.removeAttribute("totamt");
			    request.removeAttribute("mesgBundle");
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setSearchRsptID("");
				eForm.setChallansearchRsptID("");
				eForm.setChallanBankId("");
				eForm.setOnlineSearchRsptID("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setCheckAvlblty(0);
				//eForm.getPayDTO().setBankList(new ArrayList());
				eForm.getPaymentDTO().setReceiptID("");
				eForm.getPaymentDTO().setAmount(0);
				request.setAttribute("rsptDetails","");
				request.setAttribute("rsptDetails",null);
				logger.info("AFTER RESET ACTION ");
				forwardJsp = "SP1";
			}
			if("emptyValue".equalsIgnoreCase(actionName)){
				request.setAttribute("rsptDetails","");
				request.setAttribute("rsptDetails",null);
				request.setAttribute("challanRsptDetails","");
				request.setAttribute("challanRsptDetails",null);
				 eForm.setSearchRsptID("");
				 eForm.setChallansearchRsptID("");
				forwardJsp = "SP1";
			}
			    if ("selectedChallan".equals(actionName))
			    {
			    	eForm.setRadioSelect("challan");
			    	//eForm.setCrEntrAmt(parentAmount);
					eForm.setParentAmount(parentAmount);
			    	eForm.setActionName("");
			    	eForm.setChallanRefNo("");
			    	eForm.setCheckAvlblty(0);
					eForm.setReceiptID("");
					eForm.setEntrAmt("");
					eForm.setChallanBankId("");
					eForm.setChallanNO("");
					eForm.setChallanRefNo("");
					eForm.setChallanentrAmt("");
					eForm.setOnlineCinNo("");
					eForm.setOnlineEntrAmt("");
					eForm.setDwnldChallanAddress("");
					eForm.setDwnldChallanFirstName("");
					eForm.setDwnldChallanMiddleName("");
					eForm.setDwnldChallanLastName("");
					eForm.setDwnldChallanAmt("");
			    	forwardJsp = "SP1";
			    	//saveToken(request);//CSRF
			    	
			    }
			    
			    if ("selectedOnline".equals(actionName))
			    {
			    	eForm.setRadioSelect("online");
			    	//eForm.setCrEntrAmt(parentAmount);
					eForm.setParentAmount(parentAmount);
					eForm.setCheckAvlblty(0);
					eForm.setReceiptID("");
					eForm.setEntrAmt("");
					eForm.setChallanBankId("");
					eForm.setChallanNO("");
					eForm.setChallanRefNo("");
					eForm.setChallanentrAmt("");
					eForm.setOnlineCinNo("");
					eForm.setOnlineEntrAmt("");
					eForm.setDwnldChallanAddress("");
					eForm.setDwnldChallanFirstName("");
					eForm.setDwnldChallanMiddleName("");
					eForm.setDwnldChallanLastName("");
					eForm.setDwnldChallanAmt("");
			    	
			    	forwardJsp = "SP1";
			    	//forwardJsp = "SP";
			    	//saveToken(request);//CSRF
			    }
			    /*if ("selectedCredit".equals(actionName))
			    {
			    	eForm.setRadioSelect("credit");
			    	forwardJsp = jspPage;
			    	saveToken(request);
			    }*/
			  
		    }catch(Exception ex)
        	{
        	    ex.printStackTrace();
        	}
		    
	 }
		 
	   //Start SP page for usage purpose------Added by Aakriti
	     if("SPSearchPage2".equalsIgnoreCase(formName))
	     {
		    try
		    {
		    	if(PaymentConstant.SEARCH_CHALLAN_RECEIPT_ACTION.equals(actionName)) {
		    		
		    		
		    		 eForm.setSearchRsptID("");
					 eForm.setChallansearchRsptID("");
					 eForm.setOnlineSearchRsptID("");
					 eForm.setCheckAvlblty(0);
					 logger.debug("For checking consumed receipt ids");
					 logger.debug("on the click of search button");
				    
		 		    
		 		    String rspIds = eForm.getChallanNO();
		 		    String rspId = rspIds.toLowerCase();
		 		    String entAmt = eForm.getChallanentrAmt();
		 		    String chRefIdid = eForm.getChallanRefNo();
		 		    try {
		 			 
		 			PaymentDTO dto =null;
		 			ChallanDTO dto1 = new ChallanDTO();
		 			OnlineDTO dto2=null;
		 		  //dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, bankid);
		 			dto1 = paymentBD.getchallanRsptDetails(rspId, entAmt, funid, chRefIdid);
		 			dto1.setChallanNo(rspId);
		 			eForm.setCheckAvlblty(1);
		 			request.setAttribute("challanRsptDetails",dto1);
		 			request.setAttribute("onlineRsptDetails", dto2);
		 			request.setAttribute("rsptDetails",dto);
		 			request.setAttribute("radio", "");
		 			logger.debug("the attribute in request."+(PaymentDTO)request.getAttribute("rsptDetails"));
		 			//saveToken(request);//CSRF
		 			
		 		    }
		 		    catch(Exception e){
		 			e.printStackTrace();
		 			logger.debug(e);
		 			String msg =e.getMessage();
		 			logger.debug(msg);
		 			if (msg.equalsIgnoreCase("20000")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
		 	 			forwardJsp = "SP";
		 			}
		 			if (msg.equalsIgnoreCase("20001")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
		 	 			forwardJsp = "SP";
		 			}
		 			if (msg.equalsIgnoreCase("20002")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
		 	 			forwardJsp = "SP";
		 			}
		 			if (msg.equalsIgnoreCase("20003")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
		 	 			forwardJsp = "SP";
		 			}
		 			if (msg.equalsIgnoreCase("20004")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
		 	 			forwardJsp = "SP";
		 			}
		 			if (msg.equalsIgnoreCase("20005")){
		 				eForm.setChallansearchRsptID("NoReceiptId");
		 	 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
		 	 			forwardJsp = "SP";
		 			}
		 			forwardJsp = "SP";
		 			
		 			//forwardJsp = "SP";
		 		    }
		 		  forwardJsp = "SP";
		 		  //forwardJsp = "SP";
				}
		    	
		    	
		    	if(PaymentConstant.SEARCH_ONLINE_RECEIPT_ACTION.equals(actionName)) {
					 eForm.setSearchRsptID("");
					 eForm.setChallansearchRsptID("");
					 eForm.setOnlineSearchRsptID("");
					 eForm.setCheckAvlblty(0);
					 logger.debug("For checking consumed receipt ids");
					 logger.debug("on the click of search button");
				    
				    
				    String rspIds = eForm.getOnlineCinNo();
				    String rspId = rspIds.toLowerCase();
				    String entAmt = eForm.getOnlineEntrAmt();
				    logger.debug("IN ACTION........AMOUNT ENTERED BY USER....."+entAmt);
				    logger.debug("for checking consumed receipt id...receipt id is.. "+rspId);
				    try {
					 
					PaymentDTO dto =null;
					ChallanDTO dto1 = null;
					OnlineDTO  dto2 = new OnlineDTO();
				    dto2 = paymentBD.getOnlineRsptDetails(rspId, entAmt, funid);
					dto2.setOnlineCIN(rspId);
					eForm.setCheckAvlblty(1);
					request.setAttribute("challanRsptDetails",dto1);
					request.setAttribute("rsptDetails",dto);
					request.setAttribute("onlineRsptDetails",dto2);
					request.setAttribute("radio", "");
					//saveToken(request);//CSRF
					
				    }
				    catch(Exception e){
					e.printStackTrace();
					logger.debug(e);
					String msg =e.getMessage();
					logger.debug(msg);
					if (msg.equalsIgnoreCase("40000")){
						eForm.setOnlineSearchRsptID("NoReceiptId");
			 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
			 			forwardJsp = "SP";
					}
					if (msg.equalsIgnoreCase("40001")){
						eForm.setOnlineSearchRsptID("NoReceiptId");
			 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
			 			forwardJsp = "SP";
					}
					if (msg.equalsIgnoreCase("40002")){
						eForm.setOnlineSearchRsptID("NoReceiptId");
			 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
			 			forwardJsp ="SP";
					}
					if (msg.equalsIgnoreCase("40003")){
						eForm.setOnlineSearchRsptID("NoReceiptId");
			 			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
			 			forwardJsp = "SP";
					}
					
					
					forwardJsp = "SP";
					
					//forwardJsp = "SP";
				    }
				  forwardJsp = "SP";
				  //forwardJsp = "SP";
				}
		    	
			
			if(PaymentConstant.NEXT_ACTION.equals(actionName)) {
				logger.debug("ON CLICK OF PAYMENT BUTTON...Checking for consumed id");
				 PaymentDTO dtoPayment = eForm.getPaymentDTO();
     		    String selectType = dtoPayment.getSelectType();
     		   if(!dtoPayment.getSelectType().equalsIgnoreCase("online")){
			   // if(!isTokenValid(request)) {//CSRF
				//forwardJsp = jspPage;
				forwardJsp = "SP";//CSRF
			   // }//CSRF
     		   }
			    //Mohit SP2
			    if(
			    		//isTokenValid(request) || //CSRF
			    		true ||
			    		dtoPayment.getSelectType().equalsIgnoreCase("online")) {
			    	logger.debug("ON CLICK OF PAYMENT BUTTON...Checking for consumed id");
	        		   
	        		    logger.debug("selectType:-"+selectType);
	        		    //PaymentDTO dto = new PaymentDTO();
	        		   // String receiptIds = eForm.getReceiptID();
	        		    //String receiptId = receiptIds.toLowerCase();
	        		    //String cashAmt = "";
	        		    //double totalAmt = 0.0;
	        		    logger.debug("OUTSIDE CHALLAN VALIDATION-------"+"challan".equals(selectType));
	        		    if("challan".equals(selectType))
	        		    {
	        		    try{
	        		    	eForm.setFuncId(funid);
	        			    eForm.setLoggedUser(userId);
	        			    eForm.setPayMode("2");
	        			    boolean challantrans = paymentBD.PaymentChallanTransactionFinal(eForm);
	        			    if (challantrans){
	            				String transid = eForm.getTransId();
	            				request.setAttribute("PayTransId", transid);
	            				request.setAttribute("PaidAmt", eForm.getChallanentrAmt());
	            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
	            			    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
	            				jspPage = "processPayment2";
	            				forwardJsp = jspPage;
	            			}
	        		    }catch (NullPointerException ne) {
	            			jspPage = "checkPayment";
	        				logger.error("Null Exception at PaymentTransactionFinal  in action " + ne);
	        				forwardJsp = jspPage;
	        		    }
	        	   	    catch (SQLException se) {
	        	   	    	jspPage = "checkPayment";
	        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
	        	   		forwardJsp = jspPage;
	        			}
	        		    catch(Exception ex){
	        		    	String msg = ex.getMessage();
	        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
	        		    	if( msg.equalsIgnoreCase("20000")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20000);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20001")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20001);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20002")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20002);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20003")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20003);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20004")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20004);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("20005")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_20005);
	            	  			forwardJsp = "SP";
	        		    	}else{
	        		    		jspPage = "checkPayment";
	        		    		forwardJsp = jspPage;
	        		    	}
	        			
	        		    }
	        			
	        		    }
	        		    
	        		    if("online".equals(selectType))
	        		    {
	        		    try{
	        		    	eForm.setFuncId(funid);
	        			    eForm.setLoggedUser(userId);
	        			    eForm.setPayMode("3");
	        			    boolean challantrans = paymentBD.PaymentOnlineTransactionFinal(eForm);
	        			    if (challantrans){
	            				String transid = eForm.getTransId();
	            				request.setAttribute("PayTransId", transid);
	            				session.setAttribute("PayTransId", transid);
	            				session.setAttribute("CRN", eForm.getCrnNumber());
	            				request.setAttribute("PayTransId", transid);
	            				request.setAttribute("PaidAmt", eForm.getOnlineEntrAmt());
	            				request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
	           				    request.setAttribute("parentTableAftrPay", eForm.getParentTable());
	            				jspPage = "processPayment4";
	            				forwardJsp = jspPage;
	            			}
	        		    }catch (NullPointerException ne) {
	            			jspPage = "checkPayment";
	        				logger.error("Null Exception at PaymentTransactionFinal  in action " + ne);
	        				forwardJsp = jspPage;
	        		    }
	        	   	    catch (SQLException se) {
	        	   	    	jspPage = "checkPayment";
	        	   		logger.error("SQL Exception at PaymentTransactionFinal  in action " + se);
	        	   		forwardJsp = jspPage;
	        			}
	        		    catch(Exception ex){
	        		    	String msg = ex.getMessage();
	        		    	logger.error(" Exception at PaymentTransactionFinal in action " + ex);
	        		    	if( msg.equalsIgnoreCase("40000")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40000);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40001")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40001);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40002")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40002);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else if( msg.equalsIgnoreCase("40003")){
	        		    		eForm.setOnlineSearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_40003);
	            	  			forwardJsp = "SP";
	        		    	}
	        		    	else{
	        		    		jspPage = "checkPayment";
	        		    		forwardJsp = "jspPage";
	        		    	}
	        			
	        		    }
	        			
	        		    }
	        		    logger.debug("OUTSIDE CREDIT VALIDATION-------"+"credit".equals(selectType));
	        		    if("credit".equals(selectType)){
	        		    	try{
	        		    	eForm.setSearchRsptID("");
	       					eForm.setChallansearchRsptID("");
	       					eForm.setOnlineSearchRsptID("");
	       					request.removeAttribute("mesgBundle");
		        		    logger.debug("INSIDE ACTION FOR SP credit usage");
		        		    String a = eForm.getCrEntrAmt();
		        		    double b = Double.parseDouble(a);
		        		    eForm.setPayMode("4");
		        		    eForm.setFuncId(funid);
		        		    eForm.setLoggedUser(userId);
		        		    ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
		        		    String spamt = spbd.getSPCommission(funid,a);
		        		    logger.debug("the spamt is................"+spamt);
		        		    double spamt1 = Double.parseDouble(spamt);
		        		    double comsn = b-spamt1;
		        		    String comsn1 = Double.toString(comsn);
		        		    eForm.setSpComsn(comsn1);
		        		    eForm.setSpAmt(spamt);
		        		    eForm.setSpamtd(spamt1);
		        		    boolean creditTrans = paymentBD.PaymentCreditTransactionFinal(eForm,session);
		        		     if (creditTrans)
	            			{ String transid = eForm.getTransId();
            				  request.setAttribute("PayTransId", transid);
            				  request.setAttribute("PaidAmt", eForm.getCrEntrAmt());
            				  request.setAttribute("parentKeyAftrPay", eForm.getParentKey());
            				  request.setAttribute("parentTableAftrPay", eForm.getParentTable());
	            				jspPage = "processPayment3";
	            			}
	            			else 
	            			{ jspPage = "checkPayment";
	            			}
		        		    forwardJsp = jspPage;
	        		    }catch(Exception e){
	        		    	logger.debug("");
	        		    	String msg = e.getMessage();
	        		    	logger.error(" Exception at PaymentTransactionFinal in action " + e);
	        		    	if( msg.equalsIgnoreCase("10001")){
	        		    		eForm.setChallansearchRsptID("NoReceiptId");
	            	  			request.setAttribute("mesgBundle", PaymentConstant.ERROR_10001+eForm.getSpActBal());
	            	  			forwardJsp = "SP";
	        		    	}
	        		    }
	        		    
	        		   }
	        		  
	        		     /*if("credit".equals(selectType)){
	        		    logger.debug("INSIDE ACTION FOR SP");
	        		    String a = eForm.getCrEntrAmt();
	        		    double b = Double.parseDouble(a);
	        		    eForm.setPayMode("IGRS_PT_004");
	        		    eForm.setFuncId(funid);
	        		   	PaymentDAO paymentDao = new PaymentDAO();
	        		    //getStatus1 = paymentBD.getCreditDetails(eForm, b);
	        		    getStatus = paymentBD.getCreditDetails1(eForm, userId, b);
	        		    //getStatus = paymentDao.setTxnID(b);
	        		    dto.setPayTxnSuccId(getStatus);
	        		    request.setAttribute("success1", dto);
	        		    logger.debug("INSIDE CREDIT......STatus"+getStatus);
	        		    
	        		    logger.debug("the user id is:-----"+userId);
	        		    
	        		    if (getStatus.equalsIgnoreCase("fail"))
            			{
            				jspPage = "checkPayment";
            			}
            			else 
            			{
            				ServiceProviderAccountBD spbd = new ServiceProviderAccountBD();
	        		        boolean dbt = false;
	        		        dbt = spbd.getSPBalanceAfterDebit(userId, getStatus, "IGRS_PT_004", a);
	        		        if (dbt == true){
            				session.setAttribute("status", getStatus);
    	        		    //session.setAttribute("PaidAmt", a);
    	        		    session.setAttribute("PayMode", "IGRS_PT_004");
            				jspPage = "processPayment";
            				logger.debug("INSIDE CREDIT....VALUE OF JSP"+jspPage);
	        		        }else{jspPage = "checkPayment";}
            			}
	        		    forwardJsp = jspPage;
	        		    }*/
	        		     
	        		     
	        		    //resetToken(request); //ramesh commented on 14 dec 12
	        		   eForm.setActionName(""); //ramesh commented on 14 dec 12
	        		  // eForm.setFormName(""); //ramesh commented on 14 dec 12
	        		    
			    }
			}
			if("Reset".equalsIgnoreCase(actionName)){
				logger.info("INSIDE RESET IN ACTION CLASS");
				//eForm = new PaymentForm();
			    request.removeAttribute("PaidAmt");
			    request.removeAttribute("PayTransId");
			    request.removeAttribute("rsptDetails");
			    request.removeAttribute("challanRsptDetails");
			    request.removeAttribute("onlineRsptDetails");
			    request.removeAttribute("mesgBundle");
			   // session.removeAttribute("totamt");
				eForm.setReceiptID("");
				eForm.setEntrAmt("");
				eForm.setChallanNO("");
				eForm.setChallanRefNo("");
				eForm.setChallanentrAmt("");
				eForm.setCrEntrAmt("");
				eForm.setSearchRsptID("");
				eForm.setChallansearchRsptID("");
				eForm.setOnlineSearchRsptID("");
				eForm.setOnlineCinNo("");
				eForm.setOnlineEntrAmt("");
				eForm.setCheckAvlblty(0);
				eForm.setChallanBankId("");
				//eForm.getPayDTO().setBankList(new ArrayList());
				eForm.getPaymentDTO().setReceiptID("");
				eForm.getPaymentDTO().setAmount(0);
				request.setAttribute("rsptDetails","");
				request.setAttribute("rsptDetails",null);
				logger.info("AFTER RESET ACTION ");
				forwardJsp = "SP";
			}
			if("emptyValue".equalsIgnoreCase(actionName)){
				request.setAttribute("rsptDetails","");
				request.setAttribute("rsptDetails",null);
				request.setAttribute("challanRsptDetails","");
				request.setAttribute("challanRsptDetails",null);
				request.removeAttribute("mesgBundle");
				forwardJsp = "SP";
			}
			
			
			if ("selectedCash".equals(actionName))
			    {
			    	eForm.setRadioSelect("cash");
			    	//eForm.setCrEntrAmt(parentAmount);
					eForm.setParentAmount(parentAmount);
			    	forwardJsp = "SP";
			    	//forwardJsp = "SP";
			    	//saveToken(request);//CSRF
			    }
			    if ("selectedChallan".equals(actionName))
			    {
			    	eForm.setRadioSelect("challan");
			    	eForm.setParentAmount(parentAmount);
					eForm.setCheckAvlblty(0);
					eForm.setChallanNO("");
					eForm.setChallanRefNo("");
					eForm.setChallanentrAmt("");
					eForm.setReceiptID("");
					eForm.setEntrAmt("");
					eForm.setOnlineCinNo("");
					eForm.setOnlineEntrAmt("");
					eForm.setDwnldChallanAddress("");
					eForm.setDwnldChallanFirstName("");
					eForm.setDwnldChallanMiddleName("");
					eForm.setDwnldChallanLastName("");
					eForm.setDwnldChallanAmt("");
					request.setAttribute("radio", "");
			        forwardJsp = "SP";
			    	//saveToken(request);//CSRF
			    	//return mapping.findForward(forwardJsp);
			    }
			    if ("selectedOnline".equals(actionName))
			    {
			    	eForm.setRadioSelect("online");
			    	//eForm.setCrEntrAmt(parentAmount);
					eForm.setParentAmount(parentAmount);
					eForm.setCheckAvlblty(0);
					eForm.setReceiptID("");
					eForm.setEntrAmt("");
					eForm.setChallanBankId("");
					eForm.setChallanNO("");
					eForm.setChallanRefNo("");
					eForm.setChallanentrAmt("");
					eForm.setOnlineCinNo("");
					eForm.setOnlineEntrAmt("");
					eForm.setDwnldChallanAddress("");
					eForm.setDwnldChallanFirstName("");
					eForm.setDwnldChallanMiddleName("");
					eForm.setDwnldChallanLastName("");
					eForm.setDwnldChallanAmt("");
					request.setAttribute("radio", "");
			    	
			    	forwardJsp = "SP";
			    	//forwardJsp = "SP";
			    	//saveToken(request);//CSRF
			    }
			    if ("selectedCredit".equals(actionName))
			    {
			    	eForm.setRadioSelect("credit");
			    	eForm.setCrEntrAmt(parentAmount);
					eForm.setParentAmount(parentAmount);
					eForm.setCheckAvlblty(0);
					eForm.setChallanBankId("");
					eForm.setChallanNO("");
					eForm.setChallanRefNo("");
					eForm.setChallanentrAmt("");
					eForm.setOnlineCinNo("");
					eForm.setOnlineEntrAmt("");
					eForm.setDwnldChallanAddress("");
					eForm.setDwnldChallanFirstName("");
					eForm.setDwnldChallanMiddleName("");
					eForm.setDwnldChallanLastName("");
					eForm.setDwnldChallanAmt("");
			    	PaymentDTO dto =null;
		 			ChallanDTO dto1 = null;
		 			OnlineDTO dto2=null;
		 			//added by aakriti
		 			eForm.setSpFlag("");
		 			request.setAttribute("challanRsptDetails",dto1);
		 			request.setAttribute("onlineRsptDetails",dto2);
		 			request.setAttribute("rsptDetails",dto);
		 			request.setAttribute("radio", "credit");
		 			request.removeAttribute("mesgBundle");
		 			 try
		 		    {
		 		    ArrayList spList = new ArrayList();
		 		    
		 		   // spList = paymentBD.getSpDetails(userId,funid);
		 			System.out.println("Size of list"+spList.size());
		 			if(spList.size()>0)
		 			{	
		 					for(int i=0;i<spList.size();i++)
		 					{
		 						//SELECT LICENSE_NO, LICENSE_TXN_ID FROM IGRS_SP_USER_LICENSE_DETAILS
		 						ArrayList list =(ArrayList)spList.get(i);
		 						eForm.getPaymentDTO().setSpLicenseNo((String) list.get(0));
		 						eForm.setSpLicenceNo((String) list.get(0));
		 						eForm.getPaymentDTO().setSpAmt((String) list.get(1));
		 						eForm.getPaymentDTO().setSpName((String) list.get(2));
		 						forwardJsp = "SP";
		 				    	//saveToken(request);//CSRF
		 						}
		 					
		 			      }		
		 			
		 		    }
		 		    catch(Exception e)
		 		    {
		 			e.printStackTrace();
		 		    }
			    	
			    }
			  
			
		    }catch(Exception ex)
        	{
        	    ex.printStackTrace();
        	}
			
			return mapping.findForward(forwardJsp);
	    }
	
	 if ("PaymentFailPage".equals(formName)) {
	    if ("payFail".equalsIgnoreCase(actionName)) 
		
	    {
			/*session.setAttribute("status", "payFail");
			eForm.getPaymentDTO().setSelectButton("");
			String failflag = "fail";
			session.setAttribute("failflag", failflag);
			failflag = null;
			challanList.clear();
			session.setAttribute("challanList", challanList);
			logger.debug("inside payFail(paypayment)");*/
			forwardJsp = jspPage;
			//forwardJsp = "SP";
	   } 
	    
	 }   
	 
	PaymentDTO paymentDTO10 = eForm.getPaymentDTO();
	if(eForm.getFormName()!=null && !eForm.getFormName().equalsIgnoreCase("") && !eForm.getFormName().equalsIgnoreCase("onlineDownloadPage"))
	{
		session.setAttribute("OldFormName", eForm.getFormName());
	}
	    if(paymentDTO10 !=null) 
	    {
	      eForm.getPaymentDTO().setCheckButton("");
	      
	     forwardJsp = jspPage; 
	     logger.debug("forwardJsp:-"+forwardJsp);
	    
	    }
		logger.debug("Final forward jsp is --------->"+forwardJsp);
	 
	    
	return mapping.findForward(forwardJsp);
    }
	private void clearResources(String path) {
	
		File index = new File(path);
		if(index.exists())
		{
			
			index.delete();
			logger.debug("Cleared Data From Path : "+path );
		}
		
	}

}

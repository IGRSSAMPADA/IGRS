package com.wipro.igrs.eToken.action;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.wipro.igrs.db.OTPUtility;
import com.wipro.igrs.device.applet.EtokenChange;
import com.wipro.igrs.eToken.bd.ETokenBD;
import com.wipro.igrs.eToken.bo.ETokenBO;
import com.wipro.igrs.eToken.constant.ETokenConstant;
import com.wipro.igrs.eToken.dao.ETokenDAO;
import com.wipro.igrs.eToken.dto.ETokenDTO;
import com.wipro.igrs.eToken.form.ETokenForm;

public class GenerateETokenAction extends BaseAction {
	
	String forwardJsp =  "";
	private Logger logger = Logger.getLogger(CounterCreationAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	throws Exception {
		
		ActionMessages messages = new ActionMessages();
		String page=request.getParameter("page");
		logger.debug("page"+page);
		String language=(String)session.getAttribute("languageLocale");
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
			ETokenBO bo = new ETokenBO();
			String actionName = null;
			if(page!= null)
			{
				
				eForm.setEtokenDTO(new ETokenDTO());
				eForm.getEtokenDTO().setLanguage(language);
				if("generateToken".equals(page))
				{
					logger.debug("*****in generateToken Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					logger.debug("***** counter****"+eForm.getEtokenDTO().getCounterType());
					eForm.setDistrictList(bd.getEtokenDistrictList(eForm.getLanguage(),officeId));
					eForm.setActiveMakerCheckerList(bd.getEtokenActiveCounterList(eForm.getLanguage(),officeId));
					
						if(	eForm.getDistrictList().size()==0){
						
						forwardJsp = ETokenConstant.ErrorPAge;
							}
						else if(eForm.getActiveMakerCheckerList().size()==0){
						forwardJsp = ETokenConstant.ATTENDENCE_ERR_PAGE;
		
						}
					
					else{
						
						forwardJsp = ETokenConstant.GENERATE_TOKEN_JSP;
					}
					//forwardJsp = ETokenConstant.GENERATE_TOKEN_JSP;
				}
				
				//added by tanushree on 27th december for update e-token
				if("updateToken".equals(page))
				{
					logger.debug("*****in updateToken Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					
					forwardJsp = ETokenConstant.UPDATE_TOKEN_JSP;
				}
				
				if("reporteToken".equals(page))
				{
					logger.debug("*****in reportToken Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					eForm.getEtokenDTO().setSearchOffc(officeId);	
					eForm.getEtokenDTO().setTypeOfToken("-1");
					String of = eForm.getEtokenDTO().getSroName();
					
					//eForm.getEtokenDTO().setSroName(eForm.getEtokenDTO().getSroName());
				//	String m[] = eForm.getEtokenDTO().getCheckBOxSelected().split(",");
					
					
					
			//	ArrayList reportDetails = new ArrayList();
			//	reportDetails = bd.todayReportEtoken(eForm.getEtokenDTO().getSearchFromDate(), eForm.getEtokenDTO().getSearchToDate(), eForm.getEtokenDTO().getSearchOffc(), m);
			//		ArrayList mainList = new ArrayList();
					
			//		ArrayList subList = new ArrayList();

		//			
		//		for(int j= 0; j <reportDetails.size(); j++)
		//		{
		//			ETokenDTO n = new ETokenDTO();
		//	subList = (ArrayList)reportDetails.get(j);
		//			    n.setEtokenNumberR((String)subList.get(0));
		//			    n.setStatusR((String)subList.get(1));
		//			    n.setCounterNumberR((String)subList.get(2));
		//				    n.setTimeR((String)subList.get(3));
		//				    n.setRegIDR((String)subList.get(4));
		//				    mainList.add(n);
		//			}
			//		request.setAttribute("det", mainList);
			//		eDTO.setETokenReport(mainList);
	//				ArrayList mainList = new ArrayList();  
					ETokenForm objForm = (ETokenForm)form;
					ETokenDTO n = new ETokenDTO();
				//	mainList.add("");
				//	n.setEtokenReportDetails(mainList);
					n.setAppStatus(1);
					n.setSearchOffc(officeId);
					n.setSroName(of);
					objForm.setEtokenDTO(n);
					
					
				
					//eDTO.setEtokenNumberR("");
					forwardJsp = ETokenConstant.REPORT_TOKEN_JSP;
				}
				
				
				
				if("summaryreporteToken".equals(page))
				{
					logger.debug("*****in summaryreportToken Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					eForm.getEtokenDTO().setSearchOffc(officeId);	
					eForm.getEtokenDTO().setTypeOfToken("-1");
					String of = eForm.getEtokenDTO().getSroName();
					eForm.setLanguage(language);
					ETokenForm objForm = (ETokenForm)form;
					ETokenDTO n = new ETokenDTO();
				//	mainList.add("");
				//	n.setEtokenReportDetails(mainList);
					n.setAppStatus(1);
					n.setSearchOffc(officeId);
					n.setSroName(of);
					objForm.setEtokenDTO(n);
					eForm.setDistrictList(new ArrayList());
					objForm.setDistrictList(new ArrayList());
				
					//eDTO.setEtokenNumberR("");
					forwardJsp = ETokenConstant.SUMMARY_REPORT_TOKEN_JSP;
				}
			
				if("timetakenreporteToken".equals(page))
				{
					logger.debug("*****in summaryreportToken Action****");
					bd.getLoggedinDetls(eForm, officeId,language);
					logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
					logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
					eForm.getEtokenDTO().setSearchOffc(officeId);	
					eForm.getEtokenDTO().setTypeOfToken("-1");
					String of = eForm.getEtokenDTO().getSroName();
					eForm.setLanguage(language);
					ETokenForm objForm = (ETokenForm)form;
					ETokenDTO n = new ETokenDTO();
				//	mainList.add("");
				//	n.setEtokenReportDetails(mainList);
					n.setAppStatus(1);
					n.setSearchOffc(officeId);
					n.setSroName(of);
					objForm.setEtokenDTO(n);
					eForm.setDistrictList(new ArrayList());
					objForm.setDistrictList(new ArrayList());
					
				
					//eDTO.setEtokenNumberR("");
					forwardJsp = ETokenConstant.TIME_TAKEN_REPORT_TOKEN_JSP;
				}
				//Code for admin screen
			
				
				
				
				
				
			}
			
			
			
			
			
			if(request.getParameter("actionName")!= null)
			{
				actionName = request.getParameter("actionName");
			}
			else
			{
				actionName = eDTO.getActionName();
			}
			
			logger.debug("****Action name in GenerateEToken Action****"+actionName);
			
			
			
			if(ETokenConstant.CHECK_ACTION.equalsIgnoreCase(actionName))
			{
				
				//String regTxnId=eDTO.getRegInitId();
				bo.checkRegInitId(officeId, eForm);
				forwardJsp = ETokenConstant.GENERATE_TOKEN_JSP;
				
			}
			if("RESET_REPORT".equalsIgnoreCase(actionName))
			{
				eForm.getEtokenDTO().setSearchFromDate("");
				eForm.getEtokenDTO().setSearchToDate("");
				eForm.getEtokenDTO().setCheckBox("");
				
				eForm.getEtokenDTO().setAppStatus(3);
				//String regTxnId=eDTO.getRegInitId();
				//bo.checkRegInitId(officeId, eForm);
				forwardJsp = "reporteToken";
				
			}
			if("RESET_SUMMARY_REPORT".equalsIgnoreCase(actionName))
			{
				eForm.getEtokenDTO().setSearchFromDate("");
				eForm.getEtokenDTO().setSearchToDate("");
				eForm.getEtokenDTO().setCheckBox("");
				eForm.getEtokenDTO().setNoOfTokens("");
				eForm.getEtokenDTO().setTypeOfToken("-1");
				eForm.getEtokenDTO().setAppStatus(3);
				//String regTxnId=eDTO.getRegInitId();
				//bo.checkRegInitId(officeId, eForm);
				forwardJsp = "summaryreporteToken";
				
			}
			if("RESET_TIME_REPORT".equalsIgnoreCase(actionName))
			{
				eForm.getEtokenDTO().setSearchFromDate("");
				eForm.getEtokenDTO().setSearchToDate("");
				eForm.getEtokenDTO().setCheckBox("");
				eForm.getEtokenDTO().setNoOfTokens("");
				eForm.getEtokenDTO().setTypeOfToken("-1");
				eForm.getEtokenDTO().setAppStatus(3);
				eForm.setDistrictList(new ArrayList());
				//String regTxnId=eDTO.getRegInitId();
				//bo.checkRegInitId(officeId, eForm);
				forwardJsp = "timetakenreporteToken";
				
			}
			
			//added by tanushree
			if(ETokenConstant.UPDATE_ACTION.equalsIgnoreCase(actionName))
			{
				
				com.wipro.igrs.device.applet.ETokenDTO dto = bo.getDtoUpdate(eDTO.getRegInitId(),officeId);
				boolean check = bo.updateEToken(eDTO.getRegInitId(), eForm,language,officeId);
				eDTO.setUpdateRemarks("");
				if(dto!=null)
				{
				String ipAdress = new ETokenDAO().getOfficeIp(officeId);
				try {
					Socket clientSocket = new Socket(ipAdress, 6789);
					ObjectOutputStream stream = new ObjectOutputStream(clientSocket
							.getOutputStream());
					System.out.println(ipAdress);
					System.out.println(stream.toString());

					stream.writeObject(dto);

					stream.close();
					clientSocket.close();
					
				} catch (UnknownHostException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				}
				eDTO.setActionName("");
				forwardJsp = ETokenConstant.UPDATE_TOKEN_JSP;
				
			}
			
			if(ETokenConstant.SEARCH_ACTION.equalsIgnoreCase(actionName))
			{
				bo.searchEToken(eDTO.getRegInitId(), eForm,officeId,language);
			eDTO.setUpdateRemarks("");
			//eDTO.setRegInitId("");
			eDTO.setActionName("");
				forwardJsp = ETokenConstant.UPDATE_TOKEN_JSP;
				
			}
			
//new codeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
			
			
			if(ETokenConstant.REPORT_ACTION.equalsIgnoreCase(actionName))
			{
				logger.debug("*****in reportToken Action****");
				bd.getLoggedinDetls(eForm, officeId,language);
				logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
				logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
				ArrayList reportDetails = new ArrayList();
				String toDate = eForm.getEtokenDTO().getSearchToDate();
				String fromDate =eForm.getEtokenDTO().getSearchFromDate();
				String off = eForm.getEtokenDTO().getSearchOffc();
				String offs= eForm.getEtokenDTO().getSroName();
				
				String m[] = eForm.getEtokenDTO().getCheckBOxSelected().split(",");
				
				
				
				
				reportDetails = bd.todayReportEtoken(toDate,fromDate,off, m);
				ArrayList mainList = new ArrayList();
				
				ArrayList subList = new ArrayList();
				if(reportDetails.size()>0)
				{
					eForm.setErrorMsg("");
					
				}
				else
				{
					eForm.setErrorMsg("No Records Found");
				}
				
				for(int j= 0; j <reportDetails.size(); j++)
				{
					ETokenDTO n = new ETokenDTO();
					subList = (ArrayList)reportDetails.get(j);
					    n.setEtokenNumberR((String)subList.get(0));
					    n.setStatusR((String)subList.get(1));
					    n.setCounterNumberR((String)subList.get(2));
					    n.setTimeR((String)subList.get(3));
					    n.setRegIDR((String)subList.get(4));
					    mainList.add(n);
				}
				request.setAttribute("det", mainList);
				eDTO.setETokenReport(mainList);
				ETokenForm objForm = (ETokenForm)form;
				
				
				
				//ArrayList mainList = new ArrayList();  
				//ETokenForm objForm = (ETokenForm)form;
				ETokenDTO n1 = new ETokenDTO();
				if(reportDetails.size()>0)
				n1.setAppStatus(1);
				else
					n1.setAppStatus(0);
				n1.setEtokenReportDetails(mainList);
				n1.setSearchOffc(officeId);
				n1.setSroName(offs);
				objForm.setEtokenDTO(n1);
				
				objForm.setDistrictList(mainList);
			
			     
				//eDTO.setEtokenNumberR("");
				forwardJsp = ETokenConstant.REPORT_TOKEN_JSP;
			}
		
			
			
			if(ETokenConstant.REPORT_ACTION_ISSUED.equalsIgnoreCase(actionName))
			{
				logger.debug("*****in reportToken Issued Action****");
				bd.getLoggedinDetls(eForm, officeId,language);
				logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
				logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
				ArrayList reportDetails = new ArrayList();
				String toDate = eForm.getEtokenDTO().getSearchToDate();
				String fromDate =eForm.getEtokenDTO().getSearchFromDate();
				String off = eForm.getEtokenDTO().getSearchOffc();
				String offs= eForm.getEtokenDTO().getSroName();
				String id = eForm.getEtokenDTO().getTypeOfToken();
				//String m[] = eForm.getEtokenDTO().getCheckBOxSelected().split(",");
				
				
				
				
				String ans = bd.ReportEtoken(toDate,fromDate,off,id,language);
				
				if(ans!=null)
				{
					eForm.setErrorMsg("");
					eForm.getEtokenDTO().setNoOfTokens(ans);
				}
				else
				{
					eForm.setErrorMsg("No Records Found ");
					if(language.equalsIgnoreCase("en"))
					eForm.getEtokenDTO().setNoOfTokens("No tokens issued in this period");
					else
					eForm.getEtokenDTO().setNoOfTokens("कोई टोकन इस अवधि में जारी नहीं किए गए है");
				}
				
	
				
			
			
			     
				eDTO.setActionName("");
				forwardJsp = ETokenConstant.SUMMARY_REPORT_TOKEN_JSP;
			}
			
			if("TIME_TAKEN_REPORT_ACTION".equalsIgnoreCase(actionName))
			{
				logger.debug("*****in reportToken Action****");
				bd.getLoggedinDetls(eForm, officeId,language);
				logger.debug("***** distt****"+eForm.getEtokenDTO().getDistrictName());
				logger.debug("***** office****"+eForm.getEtokenDTO().getSroName());
				ArrayList reportDetails = new ArrayList();
				String toDate = eForm.getEtokenDTO().getSearchToDate();
				String fromDate =eForm.getEtokenDTO().getSearchFromDate();
				String off = eForm.getEtokenDTO().getSearchOffc();
				String offs= eForm.getEtokenDTO().getSroName();
				
				String m[] = eForm.getEtokenDTO().getCheckBOxSelected().split(",");
				
				String id  = eForm.getEtokenDTO().getTypeOfToken();
				
				
				reportDetails = bd.TimeTakenReportEtoken(toDate,fromDate,off, id);
				ArrayList mainList = new ArrayList();
				
				ArrayList subList = new ArrayList();
				if(reportDetails!=null && reportDetails.size()>0)
				{
					eForm.setErrorMsg("");
					
				}
				else
				{
					eForm.setErrorMsg("No Records Found");
				}
				
				
				request.setAttribute("det", reportDetails);
				eDTO.setETokenReport(reportDetails);
				ETokenForm objForm = (ETokenForm)form;
				
				
				
				//ArrayList mainList = new ArrayList();  
				//ETokenForm objForm = (ETokenForm)form;
				ETokenDTO n1 = new ETokenDTO();
				if(reportDetails!=null && reportDetails.size()>0)
				n1.setAppStatus(1);
				else
					n1.setAppStatus(0);
				n1.setEtokenReportDetails(mainList);
				n1.setSearchOffc(officeId);
				n1.setSroName(offs);
				objForm.setEtokenDTO(n1);
				
				objForm.setDistrictList(reportDetails);
			
			     
				//eDTO.setEtokenNumberR("");
				forwardJsp = ETokenConstant.TIME_TAKEN_REPORT_TOKEN_JSP;
			}
			
			
			//end codeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
			
			if(ETokenConstant.GENERATE_TOKEN_ACTION.equalsIgnoreCase(actionName))
			{
				
				logger.debug("****in GENERATE_TOKEN_ACTION_MOHIT****"+actionName);
				
				logger.debug("****NUMBER OF PERSONS****"+eDTO.getNoPersons());
				logger.debug("****CATEGORY****"+eDTO.getCategory());
				
			String check  = bo.generateToken(eForm,response,officeId,userId);
				if(check.equalsIgnoreCase("E"))
				{
					eForm.setErrorMsg("Etoken for this Registration Number already  Exist. Please cancel the token To genrate New Token");
					
				}
				else
				{
					eForm.setErrorMsg("");
				}
				forwardJsp = ETokenConstant.GENERATE_TOKEN_JSP;
				
				
			}
			if("SEND_RANDOM_DATA".equalsIgnoreCase(actionName))
			{
				OTPUtility util = new OTPUtility();
				String ar[] =eForm.getEtokenDTO().getRegInitId().split(";");
				EtokenChange c = new EtokenChange(ar[0],ar[1] , request);
				Thread temp =new Thread(c);
				temp.start();
				//util.validateOTP("123456", "MOD_11", "IGRS000104","1232");
				/*String ar[] =eForm.getEtokenDTO().getRegInitId().split(";");
				
				final String s = (String) session.getAttribute("OFC02");
				  Runnable r = new Runnable() {
				         public void run() {

				             try {
								Socket clientSocket = new Socket(s, 6789);
								ObjectOutputStream stream = new ObjectOutputStream(clientSocket.getOutputStream());
								System.out.println(s);
								System.out.println(stream.toString());
								
								stream.writeObject(("170114000001;13"));
								
								stream.close();
								clientSocket.close();
								Thread.sleep(5000);
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				         }
				     };

				     new Thread(r).start();
				*/
				
				forwardJsp ="generateToken";
				
				
			}
			
			
			
			if(ETokenConstant.RESET_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.GET_SRO_VIEW.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.VIEW_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.RESET_VIEW_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.EDIT_COUNTER_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.SAVE_EDIT_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.MAKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{}
			
			
			if(ETokenConstant.DELETE_MAKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.RESET_MAKER_MAPPING.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.CHECKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{}
			
			
			if(ETokenConstant.DELETE_CHECKER_COUNTER_MAPPING.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.RESET_CHECKER_MAPPING.equalsIgnoreCase(actionName))
			{}
			if(ETokenConstant.GET_CHECKER_NAME.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.GET_MAKER_NAME.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.GET_MAPPED_USER_DETLS.equalsIgnoreCase(actionName))
			{}
			
			if(ETokenConstant.SAVE_MAPPED_DETAILS.equalsIgnoreCase(actionName))
			{}
			
			
		}
	
		return mapping.findForward(forwardJsp);
	}
}

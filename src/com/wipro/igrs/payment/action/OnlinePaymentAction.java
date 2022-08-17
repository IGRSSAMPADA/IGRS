package com.wipro.igrs.payment.action;

import java.io.File;
import java.net.URLDecoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.categorymaster.util.PropertiesFileReader;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.payment.dto.OnlinePaymentDto;
import com.wipro.igrs.payment.form.OnlinePymtForm;
import com.wipro.igrs.payment.util.AESEncrypt;

public class OnlinePaymentAction extends BaseAction{
	private Logger logger = (Logger) Logger.getLogger(OnlinePaymentAction.class);
	
	

	public static void main(String[] args) {
		AESEncrypt enc = new AESEncrypt();
		enc.setSecretKey("D:\\MPT_CYBER.key");
		String finalData = 	enc.decryptFile("PreA1qqOsy6V4XF7hevRZg==");
		System.out.println(finalData);
	}



	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String crn = (String)request.getAttribute("crn");	
		OnlinePaymentDto dto = 	LoadMenuExternal.getInstance().getOnlinePaymentDto(crn);
		try
		{
		
		
		
	//	LoadMenuExternal.getInstance().deleteOnlinePayementDto(crn);
		
		
		
		
		System.out.println("Ma");
		
	OnlinePymtForm f = (OnlinePymtForm)form;
	


	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ActionForward("/estampJudicialNew.do?paymentStatus=P&parentKey=603");
	}
}

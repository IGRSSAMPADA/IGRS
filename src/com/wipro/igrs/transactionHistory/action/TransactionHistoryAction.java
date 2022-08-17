package com.wipro.igrs.transactionHistory.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.transactionHistory.BD.TransactionHistoryBD;
import com.wipro.igrs.transactionHistory.DTO.TransactionHistoryDTO;
import com.wipro.igrs.transactionHistory.form.TransHistory;
import com.wipro.igrs.willdeposit.bd.WillDepositBD;

public class TransactionHistoryAction extends BaseAction{
	

	String forwardJsp = new String("search");
	private Logger logger = (Logger) Logger.getLogger(TransactionHistoryAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		TransactionHistoryBD bd = new TransactionHistoryBD();
		String frwdName = request.getParameter("fwdName");
		TransHistory eForm = (TransHistory) form;
		TransactionHistoryDTO dto = eForm.getWillDTO();
		
		String userId = (String) session.getAttribute("UserId");
		dto.setUserId(userId);
        String language=(String)session.getAttribute("languageLocale");
		ArrayList district = bd.getDistrict(language);
		String status = (String)request.getParameter("status");
		eForm.setDistrict(district);
		
		String a = (String)request.getParameter("type");
		if("first".equalsIgnoreCase(a)){
			
			forwardJsp="search";
		}
		if("second".equalsIgnoreCase(a)){
			
			
			
			forwardJsp="stamp";
		}
		if("third".equalsIgnoreCase(a)){
			
			forwardJsp="stampSearch";
		}
		
		
		String regNo=(String)request.getParameter("regNo");
		
		
		//TODO show details method 
		if("search".equalsIgnoreCase(regNo)){
			System.out.println("abc");
			
			forwardJsp="showDetails";
			}
		// TODO submit details
		if("submit".equalsIgnoreCase(regNo)){
			String abc=bd.setData(dto);
			forwardJsp="what";
		}
		
		
		if("upload".equalsIgnoreCase(frwdName)){
			FormFile abc = dto.getUpload();
			String fileName = abc.getFileName();
			String ext = getFileExtension(fileName);
			dto.setUploadContent(abc.getFileData());
			fileName=fileName+"."+ext;
			dto.setFileName(fileName);
			forwardJsp="showDetails";
		}
		if("manualStampDetails".equalsIgnoreCase(regNo)){
			
			System.out.println(dto.getAmount());
			
			bd.setStampData(dto);
			forwardJsp="success";
		}
		if("manualStampSearch".equalsIgnoreCase(regNo)){
			
			ArrayList alist = bd.getStampList(dto);
			request.setAttribute("alist", alist);
			forwardJsp="searchStamp1";
			
		}
		if("manualStampView".equalsIgnoreCase(regNo)){
		System.out.println(dto.getCodeStamp());
		}
		if("stampView".equalsIgnoreCase(status)){
			
			System.out.println(dto.getCodeStamp());
			String code = (String)request.getParameter("codeStamp");
			dto.setCodeStamp(code);
			ArrayList alist = bd.getStampData(dto);
			forwardJsp = "showStamp";
		}
		
		return mapping.findForward(forwardJsp);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

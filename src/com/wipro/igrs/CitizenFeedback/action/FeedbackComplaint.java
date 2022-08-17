package com.wipro.igrs.CitizenFeedback.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import java.io.FileOutputStream;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletResponse;
import com.wipro.igrs.CitizenFeedback.bd.FeedbackComplaintBD;
import com.wipro.igrs.CitizenFeedback.constant.FeedbackConstant;
import com.wipro.igrs.CitizenFeedback.dao.FeedbackComplaintDAO;
import com.wipro.igrs.CitizenFeedback.dto.FeedbackComplaintDTO;
import com.wipro.igrs.CitizenFeedback.form.FeedbackComplaintForm;
import com.wipro.igrs.CitizenFeedback.rule.FeedbackComplaintRule;
import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import com.wipro.igrs.UserRegistration.form.UserRegistrationForm;


public class FeedbackComplaint extends Action {
	
	
			private Logger logger = 
			(Logger) Logger.getLogger(FeedbackComplaint.class);
		
		public ActionForward execute(	ActionMapping mapping, ActionForm form,	HttpServletRequest request, 
				HttpServletResponse response) 
			throws Exception 
			{
			
			if (form != null) {
				FeedbackComplaintForm eForm = (FeedbackComplaintForm) form;
				ArrayList listDistrict = new ArrayList();
				ArrayList listService = new ArrayList();
				FeedbackComplaintDTO fcDTO = eForm.getfcDTO();
				String action = eForm.getfcDTO().getfeedbackComplaint();
				FeedbackComplaintBD bd = new FeedbackComplaintBD();
				//String name=bd.getFname();
				ArrayList FDistrict = bd.getFDistrict();   
				//eForm.set(FDistrict);    
				eForm.setFdistrict(bd.getFDistrict());
			    
				
				return null;
			
			
			}
			return null;

			}
}

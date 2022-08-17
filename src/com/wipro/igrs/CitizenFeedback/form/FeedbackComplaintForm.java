package com.wipro.igrs.CitizenFeedback.form;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;
import com.wipro.igrs.CitizenFeedback.dto.FeedbackComplaintDTO;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;

public class FeedbackComplaintForm  extends ActionForm {
	
	/**
	 * @author Sourav Khatri
	 *
	 */
	private CitizenFeedbackDTO citizenDTO = new CitizenFeedbackDTO();
	private String Fname;	
	private String FEmailId;
	private ArrayList FServices=new ArrayList();
	private ArrayList Fdistrict =new ArrayList();
	private String FeedCompComments;
	private String RadioFValue;
	private String RadioCValue;
	private String formName;
	private String actionName;
	private ArrayList PendingRegApplicationList=new ArrayList();
	private ArrayList detailList=new ArrayList();
	
	private String language;
	
	public String getRadioFValue() {
		return RadioFValue;
	}

	public void setRadioFValue(String radioFValue) {
		RadioFValue = radioFValue;
	}

	public String getRadioCValue() {
		return RadioCValue;
	}

	public void setRadioCValue(String radioCValue) {
		RadioCValue = radioCValue;
	}

		public String getFeedCompComments() {
		return FeedCompComments;
	}

	public void setFeedCompComments(String feedCompComments) {
		FeedCompComments = feedCompComments;
	}

		FeedbackComplaintDTO fcDTO = new FeedbackComplaintDTO();

		public String getFname() {
			return Fname;
		}

		public void setFname(String fname) {
			Fname = fname;
		}

		public ArrayList getFdistrict() {
			return Fdistrict;
		}

		public void setFdistrict(ArrayList Fdistrict) {
			Fdistrict = Fdistrict;
		}

		public String getFEmailId() {
			return FEmailId;
		}

		public void setFEmailId(String emailId) {
			FEmailId = emailId;
		}

		public ArrayList getFServices() {
			return FServices;
		}

		public void setFServices(ArrayList services) {
			FServices = services;
		}

		public FeedbackComplaintDTO getfcDTO() {
			return fcDTO;
		}

		public void setFcDTO(FeedbackComplaintDTO fcDTO) {
			this.fcDTO = fcDTO;
		}

		public ArrayList getPendingRegApplicationList() {
			return PendingRegApplicationList;
		}

		public void setPendingRegApplicationList(
				ArrayList pendingRegApplicationList) {
			PendingRegApplicationList = pendingRegApplicationList;
		}

		public CitizenFeedbackDTO getCitizenDTO() {
			return citizenDTO;
		}

		public void setCitizenDTO(CitizenFeedbackDTO citizenDTO) {
			this.citizenDTO = citizenDTO;
		}

		public String getFormName() {
			return formName;
		}

		public void setFormName(String formName) {
			this.formName = formName;
		}

		public String getActionName() {
			return actionName;
		}

		public void setActionName(String actionName) {
			this.actionName = actionName;
		}

		public ArrayList getDetailList() {
			return detailList;
		}

		public void setDetailList(ArrayList detailList) {
			this.detailList = detailList;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

		public String getLanguage() {
			return language;
		}
		
	

}

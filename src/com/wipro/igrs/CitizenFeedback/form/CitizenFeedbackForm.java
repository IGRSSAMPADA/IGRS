/**
 * 
 */
package com.wipro.igrs.CitizenFeedback.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.CitizenFeedback.dto.CitizenFeedbackDTO;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackForm extends ActionForm {

	private CitizenFeedbackDTO citizenDTO = new CitizenFeedbackDTO();
	
	private ArrayList willCountry = new ArrayList();
    private ArrayList willState = new ArrayList();
    private ArrayList willDistrict = new ArrayList();
    private ArrayList proofs = new ArrayList();
    private ArrayList feedbacks = new ArrayList();
    private ArrayList PendingRegApplicationList=new ArrayList();
	private String language;
	private String formName;
	private String actionName;
	// Added By Mohit Soni
	
	private String erroMessage;
	
	
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
		System.out.println("name" + actionName);
		this.actionName = actionName;
	}
	public CitizenFeedbackDTO getCitizenDTO() {
		return citizenDTO;
	}
	public void setCitizenDTO(CitizenFeedbackDTO citizenDTO) {
		this.citizenDTO = citizenDTO;
	}
	public ArrayList getWillCountry() {
		return willCountry;
	}
	public void setWillCountry(ArrayList willCountry) {
		this.willCountry = willCountry;
	}
	public ArrayList getWillState() {
		return willState;
	}
	public void setWillState(ArrayList willState) {
		this.willState = willState;
	}
	public ArrayList getWillDistrict() {
		return willDistrict;
	}
	public void setWillDistrict(ArrayList willDistrict) {
		this.willDistrict = willDistrict;
	}
	public ArrayList getProofs() {
		return proofs;
	}
	public void setProofs(ArrayList proofs) {
		this.proofs = proofs;
	}
	public ArrayList getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(ArrayList feedbacks) {
		this.feedbacks = feedbacks;
	}
	public ArrayList getPendingRegApplicationList() {
		return PendingRegApplicationList;
	}
	public void setPendingRegApplicationList(ArrayList pendingRegApplicationList) {
		PendingRegApplicationList = pendingRegApplicationList;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
	public String getErroMessage() {
		return erroMessage;
	}
}

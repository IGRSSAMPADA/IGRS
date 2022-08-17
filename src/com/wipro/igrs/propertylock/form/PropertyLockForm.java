package com.wipro.igrs.propertylock.form;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.propertylock.dto.PropertyLockDTO;

public class PropertyLockForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	PropertyLockDTO lockdto = new PropertyLockDTO();
	
	private String formName;
	private String actionName;
	private String uid;
	private ArrayList lockCountry = new ArrayList();
    private ArrayList lockState = new ArrayList();
    private ArrayList lockDistrict = new ArrayList();
    private ArrayList lockId = new ArrayList();
    
    //added by shruti
    private String language;
    private HashMap mapPropertyTransPartyDisp = new HashMap();
    
	
    
	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}
	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	//ArrayList for Challan/ Payment Detils
    private ArrayList paymentList = new ArrayList();
    //private PaymentDTO payDTO = new PaymentDTO();
	
	
	public PropertyLockForm() {
		try {
			//TODO Parent module needs to shift this logic
			/*lockdto.setGuidUpload(GUIDGenerator.getGUID());
			lockdto.setParentPathFP("D:/Upload/11/Lock/ThumbPrint");
			lockdto.setFileNameFP("FingerPrint.BMP");
			lockdto.setParentPathScan("D:/Upload/11/Lock/Scan");
			lockdto.setFileNameScan("CapturedDocument.PDF");
			lockdto.setParentPathSign("D:/Upload/11/Lock/Sign");
			lockdto.setFileNameSign("CapturedScan.GIF");
			lockdto.setForwardPath("/lockAction.do?uploadSign=true");
			lockdto.setForwardName("lockFirst");
			lockdto.setPhotoName("Photo.GIF");
			lockdto.setWebcameraPath("D:/Upload/11/Lock/Photo");*/
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	
	
    
    
    
    
    
    
    
    
    public ArrayList getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}
	/*public PaymentDTO getPayDTO() {
		return payDTO;
	}
	public void setPayDTO(PaymentDTO payDTO) {
		this.payDTO = payDTO;
	}*/
	public ArrayList getLockCountry() {
		return lockCountry;
	}
	public void setLockCountry(ArrayList lockCountry) {
		this.lockCountry = lockCountry;
	}
	public ArrayList getLockState() {
		return lockState;
	}
	public void setLockState(ArrayList lockState) {
		this.lockState = lockState;
	}
	public ArrayList getLockDistrict() {
		return lockDistrict;
	}
	public void setLockDistrict(ArrayList lockDistrict) {
		this.lockDistrict = lockDistrict;
	}
	public ArrayList getLockId() {
		return lockId;
	}
	public void setLockId(ArrayList lockId) {
		this.lockId = lockId;
	}
	public PropertyLockDTO getLockdto() {
		return lockdto;
	}
	public void setLockdto(PropertyLockDTO lockdto) {
		this.lockdto = lockdto;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
}

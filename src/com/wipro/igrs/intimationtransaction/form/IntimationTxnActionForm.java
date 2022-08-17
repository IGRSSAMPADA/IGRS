/**
  * ===========================================================================
  * File           :   IntimationTxnActionForm.java
  * Description    :   Represents the Form Class

  * Author         :   Nithya
  * Created Date   :   Jan 8, 2008

  * ===========================================================================
  */

package com.wipro.igrs.intimationtransaction.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.intimationtransaction.dto.IntimationTxnDetailsDTO;
import com.wipro.igrs.intimationtransaction.dto.PaymentDTO;

public class IntimationTxnActionForm extends ActionForm {

    private IntimationTxnDetailsDTO intimationDTO = new IntimationTxnDetailsDTO();
    private ArrayList intimationCountry = new ArrayList();
    private ArrayList intimationState = new ArrayList();
    private ArrayList intimationCity = new ArrayList();
    private ArrayList intimationPhotoIdType = new ArrayList();
    private ArrayList intimationType = new ArrayList();
    
    
    private FormFile filePhoto = null;
    private FormFile fileThumb = null;
    private FormFile fileSignature = null;
    private String countryId;
   

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	//ArrayList for Challan/ Payment Detils
    private ArrayList paymentList = new ArrayList();
    private PaymentDTO payDTO = new PaymentDTO();

    public void setIntimationDTO(IntimationTxnDetailsDTO intimationDTO) {
        this.intimationDTO = intimationDTO;
    }

    public IntimationTxnDetailsDTO getIntimationDTO() {
        return intimationDTO;
    }
    
   

    public void setIntimationCountry(ArrayList intimationCountry) {
        this.intimationCountry = intimationCountry;
    }

    public ArrayList getIntimationCountry() {
        return intimationCountry;
    }

    public void setIntimationState(ArrayList intimationState) {
        this.intimationState = intimationState;
    }

    public ArrayList getIntimationState() {
        return intimationState;
    }

    public void setIntimationCity(ArrayList intimationCity) {
        this.intimationCity = intimationCity;
    }

    public ArrayList getIntimationCity() {
        return intimationCity;
    }

    public void setIntimationPhotoIdType(ArrayList intimationPhotoIdType) {
        this.intimationPhotoIdType = intimationPhotoIdType;
    }

    public ArrayList getIntimationPhotoIdType() {
        return intimationPhotoIdType;
    }

    public void setIntimationType(ArrayList intimationType) {
        this.intimationType = intimationType;
    }

    public ArrayList getIntimationType() {
        return intimationType;
    }

    public void setPayDTO(PaymentDTO payDTO) {
        this.payDTO = payDTO;
    }

    public PaymentDTO getPayDTO() {
        return payDTO;
    }

    public ArrayList getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList paymentList) {
        this.paymentList = paymentList;
    }

    public void setPaymentItems(int index, PaymentDTO vo)
    {
        for (; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
        paymentList.set(index,vo);
    }

    /**
     * Return the value of the SCHEME_CODE collection.
     * @return SchemeItem at a particular location of the itemList
     */

    public PaymentDTO getPaymentItems(int index)
    {
        for (; index >= paymentList.size(); paymentList.add(new PaymentDTO()));
        return(PaymentDTO)paymentList.get(index);
    }

    /**
     * @return the filePhoto
     */
    public FormFile getFilePhoto() {
        return filePhoto;
    }

    /**
     * @param filePhoto the filePhoto to set
     */
    public void setFilePhoto(FormFile filePhoto) {
        this.filePhoto = filePhoto;
    }

    /**
     * @return the fileThumb
     */
    public FormFile getFileThumb() {
        return fileThumb;
    }

    /**
     * @param fileThumb the fileThumb to set
     */
    public void setFileThumb(FormFile fileThumb) {
        this.fileThumb = fileThumb;
    }

    /**
     * @return the fileSignature
     */
    public FormFile getFileSignature() {
        return fileSignature;
    }

    /**
     * @param fileSignature the fileSignature to set
     */
    public void setFileSignature(FormFile fileSignature) {
        this.fileSignature = fileSignature;
    }
}

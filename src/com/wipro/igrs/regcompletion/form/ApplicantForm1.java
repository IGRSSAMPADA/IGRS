package com.wipro.igrs.regcompletion.form;

import com.wipro.igrs.regcompletion.dto.Common1DTO;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
public class ApplicantForm1 extends ActionForm {
    private Common1DTO commonDTO=new Common1DTO();
    
    	
         private FormFile filePhoto = null;
         private FormFile fileThumb = null;
         private FormFile fileSignature = null;
   	/**
	 * @return the commonDTO
	 */
	public Common1DTO getCommonDTO() {
		return commonDTO;
	}
	/**
	 * @param commonDTO the commonDTO to set
	 */
	public void setCommonDTO(Common1DTO commonDTO) {
		this.commonDTO = commonDTO;
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

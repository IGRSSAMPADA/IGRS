package com.wipro.igrs.suspensionenquiry.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.departmentalenquiry.form.DepartmentalEnquirySearchForm;
import com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO;

public class SuspensionEnquirySearchForm extends org.apache.struts.action.ActionForm {

	private SuspensionCriteriaDTO criteriaDTO = new SuspensionCriteriaDTO();
	
	private SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");
	
	private Logger logger = (Logger) Logger.getLogger(SuspensionEnquirySearchForm.class);
	

    public SuspensionEnquirySearchForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }
    
	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getLocation()
	 */
	public String getLocation() {
		return criteriaDTO.getLocation();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getOfficeId()
	 */
	public String getOfficeId() {
		return criteriaDTO.getOfficeId();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getRevocationOrderDateFrom()
	 */
	public String getRevocationOrderDateFrom() {
		if(criteriaDTO.getRevocationOrderDateFrom() != null && criteriaDTO.getRevocationOrderDateFrom() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getRevocationOrderDateFrom());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return criteriaDTO.getRevocationOrderDateFrom();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getRevocationOrderDateTo()
	 */
	public String getRevocationOrderDateTo() {
		if(criteriaDTO.getRevocationOrderDateTo() != null && criteriaDTO.getRevocationOrderDateTo() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getRevocationOrderDateTo());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return criteriaDTO.getRevocationOrderDateTo();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getSuspensionOrderDateFrom()
	 */
	public String getSuspensionOrderDateFrom() {
		if(criteriaDTO.getSuspensionOrderDateFrom() != null && criteriaDTO.getSuspensionOrderDateFrom() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getSuspensionOrderDateFrom());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return criteriaDTO.getSuspensionOrderDateFrom();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#getSuspensionOrderDateTo()
	 */
	public String getSuspensionOrderDateTo() {
		if(criteriaDTO.getSuspensionOrderDateTo() != null && criteriaDTO.getSuspensionOrderDateTo() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getSuspensionOrderDateTo());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return criteriaDTO.getSuspensionOrderDateTo();
	}

	/**
	 * @param location
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setLocation(java.lang.String)
	 */
	public void setLocation(String location) {
		if(location == "")
			criteriaDTO.setLocation(null);
		else
			criteriaDTO.setLocation(location);
	}

	/**
	 * @param officeId
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setOfficeId(java.lang.String)
	 */
	public void setOfficeId(String officeId) {
		if(officeId == "")
			criteriaDTO.setOfficeId(null);
		else
			criteriaDTO.setOfficeId(officeId);
	}

	/**
	 * @param revocationOrderDateFrom
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setRevocationOrderDateFrom(java.lang.String)
	 */
	public void setRevocationOrderDateFrom(String revocationOrderDateFrom) {
		if (revocationOrderDateFrom == "")
			criteriaDTO.setRevocationOrderDateFrom(null);
		else {

			try {
				Date date = sourceDateFormat.parse(revocationOrderDateFrom);
				criteriaDTO.setRevocationOrderDateFrom(targetDateFormat
						.format(date));
			} catch (ParseException e) {
			}
		}
	}

	/**
	 * @param revocationOrderDateTo
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setRevocationOrderDateTo(java.lang.String)
	 */
	public void setRevocationOrderDateTo(String revocationOrderDateTo) {
		if (revocationOrderDateTo == "")
			criteriaDTO.setRevocationOrderDateTo(null);
		else {

			try {
				Date date = sourceDateFormat.parse(revocationOrderDateTo);
				criteriaDTO.setRevocationOrderDateTo(targetDateFormat
						.format(date));
			} catch (ParseException e) {
			}
		}
	}

	/**
	 * @param suspensionOrderDateFrom
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setSuspensionOrderDateFrom(java.lang.String)
	 */
	public void setSuspensionOrderDateFrom(String suspensionOrderDateFrom) {
		if(suspensionOrderDateFrom == "")
			criteriaDTO.setSuspensionOrderDateFrom(null);
		else {
			
			try {
				Date date = sourceDateFormat.parse(suspensionOrderDateFrom);
				criteriaDTO.setSuspensionOrderDateFrom(targetDateFormat.format(date));
			} catch (ParseException e) {
			}
		}
	}

	/**
	 * @param suspensionOrderDateTo
	 * @see com.wipro.igrs.suspensionenquiry.dto.SuspensionCriteriaDTO#setSuspensionOrderDateTo(java.lang.String)
	 */
	public void setSuspensionOrderDateTo(String suspensionOrderDateTo) {
		if(suspensionOrderDateTo == "")
			criteriaDTO.setSuspensionOrderDateTo(null);
		else {
			
			try {
				Date date = sourceDateFormat.parse(suspensionOrderDateTo);
				criteriaDTO.setSuspensionOrderDateTo(targetDateFormat.format(date));
			} catch (ParseException e) {
			}
		}
	}

	/**
	 * @return the criteriaDTO
	 */
	public SuspensionCriteriaDTO getCriteriaDTO() {
		return criteriaDTO;
	}

	/**
	 * @param criteriaDTO the criteriaDTO to set
	 */
	public void setCriteriaDTO(SuspensionCriteriaDTO criteriaDTO) {
		this.criteriaDTO = criteriaDTO;
	}
    
    
    


}
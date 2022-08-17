package com.wipro.igrs.departmentalenquiry.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO;

public class DepartmentalEnquirySearchForm extends org.apache.struts.action.ActionForm {

	
	private DepartmentalCriteriaDTO criteriaDTO = new DepartmentalCriteriaDTO();
	
	private SimpleDateFormat sourceDateFormat = new SimpleDateFormat("dd/MM/yy");
	private SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MMM-yy");
	private Logger logger = (Logger) Logger.getLogger(DepartmentalEnquirySearchForm.class);

    public DepartmentalEnquirySearchForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }
	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getOfficeId()
	 */
	public String getOfficeId() {
		return criteriaDTO.getOfficeId();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getOfficeTypeId()
	 */
	public String getOfficeTypeId() {
		return criteriaDTO.getOfficeTypeId();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getOrderDate_from()
	 */
	public String getOrderDate_from() {
		
		if(criteriaDTO.getOrderDate_from() != null && criteriaDTO.getOrderDate_from() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getOrderDate_from());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		
		return criteriaDTO.getOrderDate_from();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getOrderDate_to()
	 */
	public String getOrderDate_to() {
		if(criteriaDTO.getOrderDate_to() != null && criteriaDTO.getOrderDate_to() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getOrderDate_to());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		
		return criteriaDTO.getOrderDate_to();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getOrderDate()
	 */
	public String getOrderDate() {
		if(criteriaDTO.getOrderDate() != null && criteriaDTO.getOrderDate() != "") {
			try {
				Date date = targetDateFormat.parse(criteriaDTO.getOrderDate());
				return sourceDateFormat.format(date);
			} catch (ParseException e) {
				logger.error(e);
			}
		}
		return criteriaDTO.getOrderDate();
	}

	/**
	 * @return
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#getStatus()
	 */
	public String getStatus() {
		return criteriaDTO.getStatus();
	}

	/**
	 * @param officeId
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setOfficeId(java.lang.String)
	 */
	public void setOfficeId(String officeId) {
		if(officeId == "")
			criteriaDTO.setOfficeId(null);
		else
			criteriaDTO.setOfficeId(officeId);
	}

	/**
	 * @param officeTypeId
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setOfficeTypeId(java.lang.String)
	 */
	public void setOfficeTypeId(String officeTypeId) {
		if(officeTypeId == "")
			criteriaDTO.setOfficeTypeId(null);
		else
			criteriaDTO.setOfficeTypeId(officeTypeId);
	}

	/**
	 * @param orderDate_from
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setOrderDate_from(java.lang.String)
	 */
	public void setOrderDate_from(String orderDate_from) {
		if(orderDate_from == "")
			criteriaDTO.setOrderDate_from(null);
		else {
			try {
				Date date = sourceDateFormat.parse(orderDate_from);
				criteriaDTO.setOrderDate_from(targetDateFormat.format(date));
			} catch (ParseException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * @param orderDate_to
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setOrderDate_to(java.lang.String)
	 */
	public void setOrderDate_to(String orderDate_to) {
		if(orderDate_to == "")
			criteriaDTO.setOrderDate_to(null);
		else {
			try {
				Date date = sourceDateFormat.parse(orderDate_to);
				criteriaDTO.setOrderDate_to(targetDateFormat.format(date));
			} catch (ParseException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * @param orderDate
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setOrderDate(java.lang.String)
	 */
	public void setOrderDate(String orderDate) {
		if(orderDate == "")
			criteriaDTO.setOrderDate(null);
		else {
			try {
				Date date = sourceDateFormat.parse(orderDate);
				criteriaDTO.setOrderDate(targetDateFormat.format(date));
			} catch (ParseException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * @param status
	 * @see com.wipro.igrs.departmentalenquiry.dto.DepartmentalCriteriaDTO#setStatus(java.lang.String)
	 */
	public void setStatus(String status) {
		if(status == "")
			criteriaDTO.setStatus(null);
		else
			criteriaDTO.setStatus(status);
	}

	/**
	 * @return the criteriaDTO
	 */
	public DepartmentalCriteriaDTO getCriteriaDTO() {
		return criteriaDTO;
	}

	/**
	 * @param criteriaDTO the criteriaDTO to set
	 */
	public void setCriteriaDTO(DepartmentalCriteriaDTO criteriaDTO) {
		this.criteriaDTO = criteriaDTO;
	}
    
   
	


}
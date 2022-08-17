/**
 * ServiceProviderAccountForm.java
 */


package com.wipro.igrs.serviceProvider.form;


import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.serviceProvider.dto.ServiceProviderAccountDTO;


/**
 * @author root
 * 
 */
public class ServiceProviderAccountForm extends BaseForm {

	/**
	 * Constructor name : ServiceProviderAccountForm
	 * 
	 */
	public ServiceProviderAccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	ServiceProviderAccountDTO accountDTO = new ServiceProviderAccountDTO();
	String sr_no;
	String openingBal;
	

	public String getOpeningBal() {
		return openingBal;
	}

	public void setOpeningBal(String openingBal) {
		this.openingBal = openingBal;
	}

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	/**
	 * @return the accountDTO
	 */
	public ServiceProviderAccountDTO getAccountDTO() {
		return accountDTO;
	}

	/**
	 * @param accountDTO
	 *            the accountDTO to set
	 */
	public void setAccountDTO(ServiceProviderAccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
}

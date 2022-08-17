/**
 * ServiceProviderAccountForm.java
 */


package com.wipro.igrs.serviceProvider.form;


import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.serviceProvider.dto.JudicialServiceProviderAccountDTO;


/**
 * @author root
 * 
 */
public class JudicialServiceProviderAccountForm extends BaseForm {

	/**
	 * Constructor name : ServiceProviderAccountForm
	 * 
	 */
	public JudicialServiceProviderAccountForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	JudicialServiceProviderAccountDTO accountDTO = new JudicialServiceProviderAccountDTO();
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
	public JudicialServiceProviderAccountDTO getAccountDTO() {
		return accountDTO;
	}

	/**
	 * @param accountDTO
	 *            the accountDTO to set
	 */
	public void setAccountDTO(JudicialServiceProviderAccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}
}

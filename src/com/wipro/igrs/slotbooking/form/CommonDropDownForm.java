package com.wipro.igrs.slotbooking.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.slotbooking.dto.CommonDropDownDTO;

/**
 * @author venshis
 *
 */
public class CommonDropDownForm extends ActionForm {
   private	CommonDropDownDTO dropdto= new CommonDropDownDTO();

/**
 * @return
 */
public CommonDropDownDTO getDropdto() {
	return dropdto;
}

/**
 * @param dropdto
 */
public void setDropdto(CommonDropDownDTO dropdto) {
	this.dropdto = dropdto;
}
}

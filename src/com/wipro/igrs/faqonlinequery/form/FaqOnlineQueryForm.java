package com.wipro.igrs.faqonlinequery.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.faqonlinequery.dto.FaqOnlineQueryDTO;



public class FaqOnlineQueryForm extends ActionForm{
	private FaqOnlineQueryDTO faqDTO = new FaqOnlineQueryDTO();
		private String actionName;
		private String erroMessage;
	public String getActionName() {
			return actionName;
		}

		public void setActionName(String actionName) {
			this.actionName = actionName;
		}

	public FaqOnlineQueryDTO getFaqDTO() {
		return faqDTO;
	}

	public void setFaqDTO(FaqOnlineQueryDTO faqDTO) {
		this.faqDTO = faqDTO;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	public String getErroMessage() {
		return erroMessage;
	}
}

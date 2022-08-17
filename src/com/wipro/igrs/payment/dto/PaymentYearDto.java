package com.wipro.igrs.payment.dto;

import java.io.Serializable;

public class PaymentYearDto implements Serializable {

	private String fromYear;
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	private String toYear;
	
}

/**
 * 
 */
package com.wipro.igrs.empfund.dto;

import java.io.Serializable;

/**
 * @author MRAMAD
 *
 */
public class FundRangeDTO implements Serializable {

	private String minimum;
	private String maximum;
	/**
	 * @return the minimum
	 */
	public String getMinimum() {
		return minimum;
	}
	/**
	 * @param minimum the minimum to set
	 */
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	/**
	 * @return the maximum
	 */
	public String getMaximum() {
		return maximum;
	}
	/**
	 * @param maximum the maximum to set
	 */
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	
	
}

/**
 * 
 */
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */

public class DeedCertificateOfSaleStampDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1126373148091228965L;
	private int id;
	private String stamp_number;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the stamp_number
	 */
	public String getStamp_number() {
		return stamp_number;
	}

	/**
	 * @param stamp_number
	 *            the stamp_number to set
	 */
	public void setStamp_number(String stamp_number) {
		this.stamp_number = stamp_number;
	}

}

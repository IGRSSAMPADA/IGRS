/**
 * 
 */
package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;

/**
 * @author HMOHAM
 * 
 */
public class DepartmentalCriteriaDTO implements Serializable{

	private String orderDate_from;
	private String orderDate_to;
	private String status;
	private String officeTypeId;
	private String officeId;

	/**
	 * @return the orderDate_from
	 */
	public String getOrderDate_from() {
		return orderDate_from;
	}
	/**
	 * @param orderDate_from
	 *            the orderDate_from to set
	 */
	public void setOrderDate_from(String orderDate_from) {
		this.orderDate_from = orderDate_from;
	}
	/**
	 * @return the orderDate_to
	 */
	public String getOrderDate_to() {
		return orderDate_to;
	}
	/**
	 * @param orderDate_to
	 *            the orderDate_to to set
	 */
	public void setOrderDate_to(String orderDate_to) {
		this.orderDate_to = orderDate_to;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the officeTypeId
	 */
	public String getOfficeTypeId() {
		return officeTypeId;
	}
	/**
	 * @param officeTypeId
	 *            the officeTypeId to set
	 */
	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}
	/**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId
	 *            the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	/**
	 * @param orderDate
	 */
	public void setOrderDate(String orderDate) {
		this.setOrderDate_from(orderDate);
	}
	/**
	 * @return
	 */
	public String getOrderDate() {
		return this.getOrderDate_from();
	}
}

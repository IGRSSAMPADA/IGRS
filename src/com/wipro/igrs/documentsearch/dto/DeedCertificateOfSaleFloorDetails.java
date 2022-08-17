package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */

public class DeedCertificateOfSaleFloorDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1402725274200657297L;
	private int id;
	private String property_number;
	private String floor_type;
	private String constructed_area;
	private String measurement_unit;
	private String market_value;

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
	 * @return the property_number
	 */
	public String getProperty_number() {
		return property_number;
	}

	/**
	 * @param property_number
	 *            the property_number to set
	 */
	public void setProperty_number(String property_number) {
		this.property_number = property_number;
	}

	/**
	 * @return the floor_type
	 */
	public String getFloor_type() {
		return floor_type;
	}

	/**
	 * @param floor_type
	 *            the floor_type to set
	 */
	public void setFloor_type(String floor_type) {
		this.floor_type = floor_type;
	}

	/**
	 * @return the constructed_area
	 */
	public String getConstructed_area() {
		return constructed_area;
	}

	/**
	 * @param constructed_area
	 *            the constructed_area to set
	 */
	public void setConstructed_area(String constructed_area) {
		this.constructed_area = constructed_area;
	}

	/**
	 * @return the measurement_unit
	 */
	public String getMeasurement_unit() {
		return measurement_unit;
	}

	/**
	 * @param measurement_unit
	 *            the measurement_unit to set
	 */
	public void setMeasurement_unit(String measurement_unit) {
		this.measurement_unit = measurement_unit;
	}

	/**
	 * @return the market_value
	 */
	public String getMarket_value() {
		return market_value;
	}

	/**
	 * @param market_value
	 *            the market_value to set
	 */
	public void setMarket_value(String market_value) {
		this.market_value = market_value;
	}

}

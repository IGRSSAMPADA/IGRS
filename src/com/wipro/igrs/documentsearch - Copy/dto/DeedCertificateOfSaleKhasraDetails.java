/**
 * 
 */
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */

public class DeedCertificateOfSaleKhasraDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1126373148091228965L;
	private int id;
	private String property_number;
	private String khasra_number;
	private String area;
	private String measurement_unit;
	private String land_Record;
	private String party_id;

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
	 * @return the khasra_number
	 */
	public String getKhasra_number() {
		return khasra_number;
	}

	/**
	 * @param khasra_number
	 *            the khasra_number to set
	 */
	public void setKhasra_number(String khasra_number) {
		this.khasra_number = khasra_number;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(String area) {
		this.area = area;
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
	 * @return the land_Record
	 */
	public String getLand_Record() {
		return land_Record;
	}

	/**
	 * @param land_Record
	 *            the land_Record to set
	 */
	public void setLand_Record(String land_Record) {
		this.land_Record = land_Record;
	}

	/**
	 * @param party_id
	 *            the party_id to set
	 */
	public void setParty_id(String party_id) {
		this.party_id = party_id;
	}

	/**
	 * @return the party_id
	 */
	public String getParty_id() {
		return party_id;
	}

}

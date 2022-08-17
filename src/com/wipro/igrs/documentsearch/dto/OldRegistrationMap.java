/**
 * 
 */
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 *
 */
public class OldRegistrationMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2907413894545184167L;
	private String registration_number;
	private String district;
	private String sro_name;
	private String volume_number;
	private String book_number;
	private String document_number;
	private String date_of_registration;
	private String type;
	private String oldRegistrationMapObject;
	private String radioButton;
	private String name_of_sr;

	/**
	 * @return the registration_number
	 */
	public String getRegistration_number() {
		return registration_number;
	}

	/**
	 * @param registration_number
	 *            the registration_number to set
	 */
	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the sro_name
	 */
	public String getSro_name() {
		return sro_name;
	}

	/**
	 * @param sro_name
	 *            the sro_name to set
	 */
	public void setSro_name(String sro_name) {
		this.sro_name = sro_name;
	}

	/**
	 * @return the volume_number
	 */
	public String getVolume_number() {
		return volume_number;
	}

	/**
	 * @param volume_number
	 *            the volume_number to set
	 */
	public void setVolume_number(String volume_number) {
		this.volume_number = volume_number;
	}

	/**
	 * @return the book_number
	 */
	public String getBook_number() {
		return book_number;
	}

	/**
	 * @param book_number
	 *            the book_number to set
	 */
	public void setBook_number(String book_number) {
		this.book_number = book_number;
	}

	/**
	 * @return the document_number
	 */
	public String getDocument_number() {
		return document_number;
	}

	/**
	 * @param document_number
	 *            the document_number to set
	 */
	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	/**
	 * @return the date_of_registration
	 */
	public String getDate_of_registration() {
		return date_of_registration;
	}

	/**
	 * @param date_of_registration
	 *            the date_of_registration to set
	 */
	public void setDate_of_registration(String date_of_registration) {
		this.date_of_registration = date_of_registration;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the oldRegistrationMapObject
	 */
	public String getOldRegistrationMapObject() {
		return oldRegistrationMapObject;
	}

	/**
	 * @param oldRegistrationMapObject
	 *            the oldRegistrationMapObject to set
	 */
	public void setOldRegistrationMapObject(String oldRegistrationMapObject) {
		this.oldRegistrationMapObject = oldRegistrationMapObject;
	}

	/**
	 * @return the radioButton
	 */
	public String getRadioButton() {
		return radioButton;
	}

	/**
	 * @param radioButton
	 *            the radioButton to set
	 */
	public void setRadioButton(String radioButton) {
		this.radioButton = radioButton;
	}

	/**
	 * @return the name_of_sr
	 */
	public String getName_of_sr() {
		return name_of_sr;
	}

	/**
	 * @param name_of_sr
	 *            the name_of_sr to set
	 */
	public void setName_of_sr(String name_of_sr) {
		this.name_of_sr = name_of_sr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "registration_number=" + registration_number + ",district=" + district + ",sro_name=" + sro_name
				+ ",volume_number=" + volume_number + ",book_number=" + book_number + ",document_number="
				+ document_number + ",date_of_registration=" + date_of_registration + ",type=" + type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book_number == null) ? 0 : book_number.hashCode());
		result = prime * result + ((date_of_registration == null) ? 0 : date_of_registration.hashCode());
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((document_number == null) ? 0 : document_number.hashCode());
		result = prime * result + ((sro_name == null) ? 0 : sro_name.hashCode());
		result = prime * result + ((volume_number == null) ? 0 : volume_number.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof OldRegistrationMap)) {
			return false;
		}
		OldRegistrationMap other = (OldRegistrationMap) obj;
		if (book_number == null) {
			if (other.book_number != null) {
				return false;
			}
		} else if (!book_number.equals(other.book_number)) {
			return false;
		}
		if (date_of_registration == null) {
			if (other.date_of_registration != null) {
				return false;
			}
		} else if (!date_of_registration.equals(other.date_of_registration)) {
			return false;
		}
		if (district == null) {
			if (other.district != null) {
				return false;
			}
		} else if (!district.equals(other.district)) {
			return false;
		}
		if (document_number == null) {
			if (other.document_number != null) {
				return false;
			}
		} else if (!document_number.equals(other.document_number)) {
			return false;
		}
		if (sro_name == null) {
			if (other.sro_name != null) {
				return false;
			}
		} else if (!sro_name.equals(other.sro_name)) {
			return false;
		}
		if (volume_number == null) {
			if (other.volume_number != null) {
				return false;
			}
		} else if (!volume_number.equals(other.volume_number)) {
			return false;
		}
		return true;
	}

}

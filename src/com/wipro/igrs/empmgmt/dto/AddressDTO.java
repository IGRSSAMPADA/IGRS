/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.empmgmt.dto;
    import java.io.Serializable;
import java.util.ArrayList;


	/**
	* 
	* AddressDTO.java <br>
	* AddressDTO <br>
	* 
	* @version 1.0
	* 
	* @author <b>WIPRO JAVA TEAM</b> <br>
	*         Created on 12-Apr-2008 <br>
	*         Last Modified on 28-Mar-2013
	*/
	@SuppressWarnings({ "rawtypes" })
	public class AddressDTO implements Serializable
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 6899030702188404173L;
		private String countryId;
		private String countryName;
		private ArrayList countryList;
		
		private String stateId;
		private String stateName;
		private ArrayList stateList;

		private String districtId;
		private String districtName;
		private ArrayList districtList;
		
			
		//constructor
		public AddressDTO() {
		}

		//	 setters and getters 
				
		public String getCountryId() {
			return countryId;
		}


		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}


		public ArrayList getCountryList() {
			return countryList;
		}


		public void setCountryList(ArrayList countryList) {
			this.countryList = countryList;
		}


		public String getCountryName() {
			return countryName;
		}


		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}


		public String getDistrictId() {
			return districtId;
		}


		public void setDistrictId(String districtId) {
			this.districtId = districtId;
		}


		public ArrayList getDistrictList() {
			return districtList;
		}


		public void setDistrictList(ArrayList districtList) {
			this.districtList = districtList;
		}


		public String getDistrictName() {
			return districtName;
		}


		public void setDistrictName(String districtName) {
			this.districtName = districtName;
		}


		public String getStateId() {
			return stateId;
		}


		public void setStateId(String stateId) {
			this.stateId = stateId;
		}


		public ArrayList getStateList() {
			return stateList;
		}


		public void setStateList(ArrayList stateList) {
			this.stateList = stateList;
		}


		public String getStateName() {
			return stateName;
		}


		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		
		
	}




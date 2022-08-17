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
package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.AssetDTO;
import com.wipro.igrs.empmgmt.dto.PropertyDTO;


/**
* 
* PropertyForm.java <br>
* PropertyForm <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PropertyForm extends BaseForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3592609318093007445L;
	private PropertyDTO propertyDTO=new PropertyDTO();
	private AssetDTO assetDTO=new AssetDTO();
	private ArrayList assestlist=new ArrayList();
	private ArrayList propertylist=new ArrayList();
	

	
	private String actionName;
	private String formName;
	/**
	 * @param index
	 * @param value
	 */
	public void setPropertyArrDTO(int index,PropertyDTO value){
		for(;index>=propertylist.size();propertylist.add(new PropertyDTO()));
		
		propertylist.add(index,value);
		
	}
	/**
	 * @param index
	 * @return
	 */
	public PropertyDTO getPropertyArrDTO(int index){
		for(;index>=propertylist.size();propertylist.add(new PropertyDTO()));
		return (PropertyDTO)propertylist.get(index);
	}
	
	
	
	/**
	 * @param index
	 * @param value
	 */
	public void setAssestArrDTO(int index ,AssetDTO value){
		for(;index>=assestlist.size();assestlist.add(new AssetDTO()));
		
		assestlist.add(index,value);
		
	}
	/**
	 * @param index
	 * @return
	 */
	public AssetDTO getAssestArrDTO(int index){
		for(;index>=assestlist.size();assestlist.add(new AssetDTO()));
		return (AssetDTO)assestlist.get(index);
	}

	/**
	 * @return
	 */
	public ArrayList getAssestlist() {
		return assestlist;
	}

	/**
	 * @param assestlist
	 */
	public void setAssestlist(ArrayList assestlist) {
		this.assestlist = assestlist;
	}

	/**
	 * @return
	 */
	public AssetDTO getAssetDTO() {
		return assetDTO;
	}

	/**
	 * @param assetDTO
	 */
	public void setAssetDTO(AssetDTO assetDTO) {
		this.assetDTO = assetDTO;
	}

	/**
	 * @return
	 */
	public PropertyDTO getPropertyDTO() {
		return propertyDTO;
	}

	/**
	 * @param propertyDTO
	 */
	public void setPropertyDTO(PropertyDTO propertyDTO) {
		this.propertyDTO = propertyDTO;
	}

	/**
	 * @return
	 */
	public ArrayList getPropertylist() {
		return propertylist;
	}

	/**
	 * @param propertylist
	 */
	public void setPropertylist(ArrayList propertylist) {
		this.propertylist = propertylist;
	}
	
	/**
	 * @return
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
}

/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterBD.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master BD Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By        Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		        18th Mar, 2008	 		 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedparammaster.bd;


import java.util.ArrayList;

import com.wipro.igrs.deedparammaster.bo.DeedParamMasterBO;
import com.wipro.igrs.deedparammaster.dto.DeedParamMasterDTO;
import com.wipro.igrs.deedparammaster.exception.UserNameAlreadyExistException;



public class DeedParamMasterBD{
	
	DeedParamMasterBO deedparam = null;
	
	public DeedParamMasterBD(){
		deedparam = new DeedParamMasterBO();
	}
	public boolean isDeedParamExists(String name){
		return deedparam.isDeedParamExists(name);
	}
	public void addDeedParam(DeedParamMasterDTO dto) throws Exception{
		deedparam.addDeedParam(dto);
	}
	public ArrayList getAllDeedParamDetails(){
		return deedparam.getAllDeedParamDetails();
	}
	public void updateDeedParamDetails(DeedParamMasterDTO dto)  throws Exception{
		deedparam.updateDeedParamDetails(dto);
	}
	public void deleteDeedParamDetails(DeedParamMasterDTO dto){
		deedparam.deleteDeedParamDetails(dto);
	}
	public DeedParamMasterDTO getDeedparamById(String ID) {
		return deedparam.getDeedparamById(ID);
	}
	
}
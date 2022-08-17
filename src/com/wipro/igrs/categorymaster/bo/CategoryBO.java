/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterBO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master BO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.categorymaster.bo;


import java.util.ArrayList;

import com.wipro.igrs.categorymaster.dao.CategoryDAO;
import com.wipro.igrs.categorymaster.dto.CategoryDTO;
import com.wipro.igrs.categorymaster.exception.UserNameAlreadyExistException;
import com.wipro.igrs.categorymaster.formbean.CategoryForm;




public class CategoryBO{
	
	CategoryDAO categoryMaster = null;
	
	public CategoryBO(){
		categoryMaster = new CategoryDAO();
	}
	
	public void addCategoryMaster(CategoryDTO category, String roleId, String funId, String userId) throws UserNameAlreadyExistException{
		
		 if(!isCategoryExists(category))
		 {
		  categoryMaster.addCategoryMaster(category,roleId,funId,userId);
		 }else
		 {
			 throw new UserNameAlreadyExistException();
		 }
	
	}
	public ArrayList getList() throws Exception{
		return categoryMaster.getList();
	}
	public void updateCAtegorymaster(CategoryDTO category, String roleId, String funId, String userId)  throws UserNameAlreadyExistException {
		categoryMaster.updateCAtegorymaster(category,roleId,funId,userId);
	}
	public CategoryDTO getCategoryById(String activeid) throws Exception {
		return categoryMaster.getCategoryById(activeid);
	}
	public boolean isCategoryExists(CategoryDTO category)  {
		return categoryMaster.isCategoryExists(category);
	}
	public void deleteCategoryMaster(CategoryDTO category, String roleId, String funId, String userId) throws Exception
	{
		categoryMaster.deleteCategoryMaster(category,roleId,funId,userId);
	}
}
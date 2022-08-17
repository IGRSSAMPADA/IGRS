/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CategoryDAO.java
 * Author      :   BASEM.A
 * Description :   Represents the DAO Class for Category Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             BASEM.A  10th AUG, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.categorymaster.dao;

import java.util.ArrayList;
import java.util.logging.Logger;




import com.sun.org.apache.regexp.internal.RE;
import com.wipro.igrs.categorymaster.db.DBUtility;
import com.wipro.igrs.categorymaster.dto.CategoryDTO;
import com.wipro.igrs.categorymaster.exception.UserNameAlreadyExistException;
import com.wipro.igrs.categorymaster.formbean.CategoryForm;
import com.wipro.igrs.categorymaster.sql.CategoryCommonSQL;
import com.wipro.igrs.common.IGRSCommon;




public class CategoryDAO {

	private ArrayList categoryList = new ArrayList();
	DBUtility dbUtility = null;
	String sql = null;
	ArrayList subList = null;
	CategoryDTO dto = null;
	String categoryID = null;
	
//	private Logger logger = (Logger) Logger.getLogger(CategoryDAO.class);

	/* DAO constructor */
	public CategoryDAO() { 
	}

	/* ADD CATEGORY MASTER */

	public void addCategoryMaster(CategoryDTO category, String roleId, String funId, String userId) {
		sql = CategoryCommonSQL.INSERT_CATEGORY_MASTER;
		String param[] = new String[4];
		param[0] = "Cat";
		param[1] = category.getCategoryName();
		param[2] = category.getCreatedBy();
		param[3] = category.getUpdatedBy();
		try {
			IGRSCommon igrsCommon = new IGRSCommon();
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","INSERT","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","INSERT","F",funId,userId,roleId);
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				x.printStackTrace();
			}
		}
	}

	/* GET CATEGORY LIST */

	public ArrayList getList() throws Exception {
		try {
			sql = CategoryCommonSQL.SELECT_CATEGORY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createStatement();

			ArrayList mainList1 = dbUtility.executeQuery(sql);
			for (int i = 0; i < mainList1.size(); i++) {
				subList = (ArrayList) mainList1.get(i);
				dto = new CategoryDTO();
				dto.setCategoryId(subList.get(0).toString());
				dto.setCategoryName(subList.get(1).toString());
				dto.setCategoryStatus(subList.get(2).toString());
				categoryList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtility.closeConnection();
		}
		return categoryList;
	}

	/* UPDATE CATEGORY MASTER */

	public void updateCAtegorymaster(CategoryDTO category, String roleId, String funId, String userId) {
		IGRSCommon igrsCommon = null;
		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
        	
		String param[] = new String[4];
		CategoryDTO to;
		param[0] = category.getCategoryName();
		param[1] = category.getCategoryStatus();
		param[2] = category.getUpdatedBy();
		param[3] = category.getCategoryId();
	    dbUtility = new DBUtility();
		sql = CategoryCommonSQL.UPDATE_CATEGORY_MASTER;
			dbUtility.createPreparedStatement(sql);
			to = getCategoryByName(category.getCategoryName());
		
		System.out.println("Category name "+category.getCategoryName());
	
		if(to!=null)	
		{
			  if(to.getCategoryId().equals(category.getCategoryId()))
			  {
					boolean boo = dbUtility.executeUpdate(param);
					if (boo) {
						dbUtility.commit();
						igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","UPDATE","T",funId,userId,roleId);
					}else {
						dbUtility.rollback();
						igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","UPDATE","T",funId,userId,roleId);
					}	
			  }else
			  {
				  throw new UserNameAlreadyExistException();
			  }
		}else
		{
			System.out.println("okkkkk");
			   boolean boo = dbUtility.executeUpdate(param);
			   System.out.println("okkkkk");
			   if (boo) {
				dbUtility.commit();
			   }else {
				dbUtility.rollback();
			   }	
		}
		
		
		
		
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x){
				x.printStackTrace();
			}
		}

	}

	/* GET CATEGORY BY ID */
	public CategoryDTO getCategoryById(String activeid) throws Exception {
		try {
			sql = CategoryCommonSQL.SELECT_CATEGORY_MASTER_ID;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = activeid;
			ArrayList list = dbUtility.executeQuery(sd);
			ArrayList list1 = (ArrayList) list.get(0);
			dto = new CategoryDTO();
			dto.setCategoryId(list1.get(0).toString());
			dto.setCategoryName(list1.get(1).toString());
			dto.setCategoryStatus(list1.get(2).toString());

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbUtility.closeConnection();
		}
		return dto;
	}
	/* IS EXIST Category*/
	public boolean isCategoryExists(CategoryDTO category) {
		// TODO Auto-generated method stub
		boolean flag=true;
		try {
			sql = CategoryCommonSQL.CHECK_CATEGORY_MASTER;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			String sd[] = new String[1];
			sd[0] = category.getCategoryName();
			ArrayList list = dbUtility.executeQuery(sd);
			
			System.out.println("size ***"+list.size());
			
			 if(list.size()>=1)
			 {      System.out.println("true");
					flag= true;
			 }else
			 {     
				    System.out.println("false");
					flag= false;
			 }
	
			    dbUtility.closeConnection();
				return flag; 
		} catch (Exception e) {
			e.printStackTrace();
			try {
				dbUtility.closeConnection();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return flag; 
		}
		
		
	}
	public CategoryDTO getCategoryByName(String categoryName) throws Exception {
		// TODO Auto-generated method stub
		CategoryDTO dto = null;
		DBUtility dbUtility=null;
		String [] param=new String[1];
		param[0]=categoryName;
		try {
			String sql = CategoryCommonSQL.SELECT_CATEGORY_MASTER_NAME;
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			
			ArrayList List = dbUtility.executeQuery(param);
			ArrayList subList = (ArrayList) List.get(0);
			
			dto = new CategoryDTO();
			dto.setCategoryId(subList.get(0).toString());
			dto.setCategoryName(subList.get(1).toString());
			dto.setCategoryStatus(subList.get(2).toString());
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			try {
				dbUtility.closeConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dto;
		
		
	}
	
	public void deleteCategoryMaster(CategoryDTO category, String roleId, String funId, String userId) {
		sql = CategoryCommonSQL.DELETE_CATEGORY_MASTER;
		String param[] = new String[2];
		param[0] = category.getCategoryStatus();
		param[1] = category.getCategoryId();
		IGRSCommon igrsCommon = null;
		try {
			igrsCommon = new IGRSCommon();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(sql);
			boolean boo = dbUtility.executeUpdate(param);
			if (boo) {
				dbUtility.commit();
				igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","INSERT","T",funId,userId,roleId);
			} else {
				dbUtility.rollback();
				igrsCommon.saveLogDet("IGRS_PERSON_CATEGORY_MASTER","INSERT","F",funId,userId,roleId);
			}
				 
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtility.closeConnection();
			}catch(Exception x) {
				x.printStackTrace();
			}
		}
	}
	
	
	
	public static void main(String[] args) throws Exception {
	CategoryDTO d =new CategoryDTO();
	CategoryDAO doi =new CategoryDAO();
	d.setCategoryId("2");
	d.setCategoryName("best");
	d.setCategoryStatus("A");
	//doi.updateCAtegorymaster(d);
}

	

}


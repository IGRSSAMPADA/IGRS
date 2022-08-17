package com.wipro.igrs.publicOff.bo;

import java.util.ArrayList;

import com.wipro.igrs.publicOff.dao.PublicOffDAO;

/**
 * ===========================================================================
 * File           :   PublicOffBO.java
 * Description    :   Represents the BO  Class 

 * Author         :   pavani Param
 * Created Date   :   Aug 23, 2008

 * ===========================================================================
 */




      
public class PublicOffBO  
{

	public ArrayList getDeptList() 
	{
		PublicOffDAO dao = new PublicOffDAO();
		return dao.getDeptList();
	}

	public ArrayList getUserDet(String userId) {
		
		PublicOffDAO dao = new PublicOffDAO();
		return dao.getUserDet(userId);
	}

	public boolean poDetInsert(String[] poDet) {
		PublicOffDAO dao = new PublicOffDAO();
		return dao.poDetInsert(poDet);
	}

	public ArrayList getpublicOffDet(String userId) {
		PublicOffDAO dao = new PublicOffDAO();
		return dao.getpublicOffDet(userId);
	}

	public boolean updateActDet(String[] actDet) {
		PublicOffDAO dao = new PublicOffDAO();
		return dao.updateActDet(actDet);
	}
	
	
}


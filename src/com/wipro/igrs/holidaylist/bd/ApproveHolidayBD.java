
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
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
 */ 

/* 
 * FILE NAME: ApproveHolidayBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DAO FOR HOLIDAY APPROVE.
 */


package com.wipro.igrs.holidaylist.bd;

import java.util.HashMap;

import com.wipro.igrs.holidaylist.bo.ApproveHolidayBO;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;

/**
 * @author NIHAR M.
 *
 */
public class ApproveHolidayBD {
	
	
	/**
	 * @return ArrayList
	 * @throws Exception
	 */
	public HashMap getAllHolidayList() throws Exception{
		ApproveHolidayBO holidayBO = new ApproveHolidayBO();
		return holidayBO.getAllHolidayList();
	}
	

	/**
	 * @param holidayID
	 * @return
	 * @throws Exception
	 */
	public boolean removeHoliday(String[] holidayID) throws Exception{
		ApproveHolidayBO holidayBO = new ApproveHolidayBO();
		return holidayBO.removeHoliday(holidayID);
		
	}

	/**
	 * @param hid
	 * @return
	 * @throws Exception
	 */
	public HolidayListDTO getIndHolidayDetails(String hid) throws Exception{
		ApproveHolidayBO holidayBO = new ApproveHolidayBO();
		return holidayBO.getIndHolidayDetails(hid);
	}


	/**
	 * @param dto
	 * @return boolean
	 */
	public boolean updateHolidayDetails(HolidayListDTO dto,String userId) throws Exception {
		ApproveHolidayBO holidayBO = new ApproveHolidayBO();
		return holidayBO.updateHolidayDetails(dto,userId);
	}
	
	

}

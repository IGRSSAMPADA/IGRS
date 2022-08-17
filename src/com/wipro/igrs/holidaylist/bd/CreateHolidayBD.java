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
 * FILE NAME: CreateHolidayBD.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE BD FOR HOLIDAY CREATION.
 */


package com.wipro.igrs.holidaylist.bd;

import com.wipro.igrs.holidaylist.bo.CreateHolidayBO;
import com.wipro.igrs.holidaylist.dto.HolidayListDTO;

/**
 * @author NIHAR M.
 *
 */
public class CreateHolidayBD {

	CreateHolidayBO holidayBO = new CreateHolidayBO();
	
	
	/**
	 * @return boolean
	 * @throws Exception
	 */
	public boolean createNewHoliday(HolidayListDTO formDTO, String userId) throws Exception{
		return holidayBO.createNewHoliday(formDTO, userId);
	}

	
}

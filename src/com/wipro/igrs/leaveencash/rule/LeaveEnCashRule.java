package com.wipro.igrs.leaveencash.rule;
/* Copyright (c) 2006-2008 WIPRO. All Rights Reserved.
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
 */


import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.leaveencash.constant.CommonConstant;
import com.wipro.igrs.leaveencash.dao.LeaveEnCashmentDAO;
import com.wipro.igrs.leaveencash.dto.LeaveEnCashmentDTO;
import com.wipro.igrs.util.PropertiesFileReader;
 

/**
 * @author Madan Mohan
 * @since 20 Feb 2008
 * Description : created for server side validation
 */
public class LeaveEnCashRule {

	private boolean error;
	//private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger
	.getLogger(LeaveEnCashRule.class);

	public LeaveEnCashRule() {

	}
	 
	public ArrayList validateLeaveEnCash(LeaveEnCashmentDTO dto, 
										PropertiesFileReader pr) {
		boolean flag = false;
		logger.debug("inside validateLeaveEnCash");
	 
		logger.debug("*******" + dto.getAge());
		ArrayList errorList = new ArrayList();
		try {
			//pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
			errorList.add(pr.getValue("error.header"));
			 
		    LeaveEnCashmentDAO dao = new LeaveEnCashmentDAO();
		    String[] paramMaxEnCash = new String[1];
		    
		    paramMaxEnCash[0] = dto.getEmpID();
		    String maxEnCash = dao.getEmpEnCashLeaveDetails(paramMaxEnCash);
		    maxEnCash = maxEnCash !=null ? maxEnCash : "0";
			if(Integer.parseInt(maxEnCash) > 0  ) {
				errorList.add("<li><font color=\"red\">"
						+dto.getFirstName()+"&nbsp;"
						+dto.getMidName()  +"&nbsp;"
						+dto.getLastName()+ "&nbsp;"
						+pr.getValue("error.leave.encashed")
						+"&nbsp;"+dto.getYear()+".</font></li></br>");
				flag = true;
			}
			
			String[] paramMaxEnCashed = new String[1];
		    
		    paramMaxEnCashed[0] = dto.getEmpID();
		    String maxEnCashed = dao.getMaxLeaveEnCash(paramMaxEnCashed);
		    maxEnCashed = maxEnCashed !=null ? maxEnCashed : "0";
		    int maxEncashing = dto.getLeaveEnCash();
		    
			if(!"".equals(maxEnCashed.trim()) && 
					maxEncashing >Integer.parseInt(maxEnCashed)) {
				errorList.add("<li><font color=\"red\">"
						+pr.getValue("error.leave.maxencashed")+"&nbsp;"
						+maxEnCashed+" or equal to 0.</font></li></br>");
				flag = true;
			}
			logger.debug("EnCash:-"+dto.getLeaveEnCash()
					+":"+dto.getLeaveBalance());
			if( dto.getLeaveEnCash() > dto.getLeaveBalance() 
					|| dto.getLeaveEnCash() == 0) {
				errorList.add("<li><font color=\"red\">"
						+pr.getValue("error.leave.maxencashed")+"&nbsp;"
						+dto.getLeaveBalance()+" or equal to 0."+
						"</font></li></br>");
				flag = true;
			}
			setError(flag);
				 
			 
		} catch (Exception x) {
			logger.debug("validateLeaveEnCash :-" + x);
		}
		return errorList;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public boolean isError() {
		return error;
	}

}

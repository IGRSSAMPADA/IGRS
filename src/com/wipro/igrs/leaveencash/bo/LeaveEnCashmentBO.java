package com.wipro.igrs.leaveencash.bo;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.leaveencash.bd.LeaveEnCashmentBD;
import com.wipro.igrs.leaveencash.constant.CommonConstant;
import com.wipro.igrs.leaveencash.dao.LeaveEnCashmentDAO;
import com.wipro.igrs.leaveencash.dto.LeaveEnCashmentDTO;


public class LeaveEnCashmentBO {

	private Logger logger = (Logger) Logger
	.getLogger(LeaveEnCashmentBO.class);
	
	public LeaveEnCashmentDTO getEmpDetails(String empID) {
		LeaveEnCashmentDTO dto = new LeaveEnCashmentDTO ();
		
		try {
			LeaveEnCashmentBD bd = new LeaveEnCashmentBD ();
			String[] param = new String[1];
			param[0] = empID;
			
			ArrayList list = bd.getEmpDetails(param);
			
			if(list != null) {
				for(int i =0;i<list.size();i++) {
					ArrayList rList = (ArrayList)list.get(i);
					dto.setFirstName((String)rList.get(0));
					dto.setMidName((String)rList.get(1));
					dto.setLastName((String)rList.get(2));
					dto.setDOB((String)rList.get(3));
					String sex = (String)rList.get(4);
					if("M".equals(sex) || "Male".equalsIgnoreCase(sex)) {
						dto.setSex("Male");
					}
					if("F".equals(sex) || "FeMale".equalsIgnoreCase(sex)) {
						dto.setSex("Female");
					}
					dto.setDOJ((String)rList.get(5));
					dto.setAge((String)rList.get(6));
					dto.setYear((String)rList.get(7));
					logger.debug("First Name:-"+dto.getFirstName());
				}
			}
			
			 
			String [] returnLeave = bd.getEmpLeaveDetails(empID);
			if(returnLeave != null) {
				 if(returnLeave[0]!=null) {
					 dto.setLeaveAvailed(Integer.parseInt(returnLeave[0]));
				 }
				 if(returnLeave[1]!=null) {
					 dto.setLeaveEarned(Integer.parseInt(returnLeave[1]));
				 }
				 if(returnLeave[2]!=null) {
					 dto.setLeaveBalance(Integer.parseInt(returnLeave[2]));
				 }
				 if(returnLeave[3]!=null) {
					 dto.setMaxFiscalYearID(returnLeave[3]);
					 logger.debug("Fiscal Year:-"+dto.getMaxFiscalYearID());
				 }
			}
			
			dto.setEmpID(empID);
			logger.debug("EmpID:-"+dto.getEmpID());
		}catch(Exception x) {
			logger.debug(x);
		}
		return dto;
	}
	
	public boolean insertEnCashLeave(LeaveEnCashmentDTO dto) {
		boolean bol = false;
		try {
			String[] param = new String[5];
			
			param[0] = dto.getEmpID();
			param[1] = dto.getMaxFiscalYearID();
			param[2] = String.valueOf(dto.getLeaveEnCash());
			param[3] = dto.getRemarks();
			param[4] = dto.getCreatedBy();
			for(int i =0; i<param.length;i++) {
				logger.debug("param["+i+"]="+param[i]);
			}
			bol = new LeaveEnCashmentDAO().insertEnCashLeave(param); 
		}catch(Exception x) {
			logger.debug(x);
		}
		return bol;
	}
}

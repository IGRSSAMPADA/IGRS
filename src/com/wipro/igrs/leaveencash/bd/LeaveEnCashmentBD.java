package com.wipro.igrs.leaveencash.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.leaveencash.dao.LeaveEnCashmentDAO;


public class LeaveEnCashmentBD {

	
	private Logger logger = (Logger) Logger
	.getLogger(LeaveEnCashmentBD.class);
	
	public ArrayList getEmpDetails(String[] param) throws Exception{
		LeaveEnCashmentDAO dao = new LeaveEnCashmentDAO();
		ArrayList list = dao.getEmpDetails(param);
		logger.debug("inside getEmpDetails BD ");
		return list;
	}
	
	public String[] getEmpLeaveDetails(String empID) throws Exception{
		LeaveEnCashmentDAO dao = new LeaveEnCashmentDAO();
		String[] list = dao.getEmpAvaliedLeaves(empID);
		logger.debug("inside getEmpLeaveDetails BD ");
		return list;
	}
	
	public boolean insertEnCashLeave(String[] param) throws Exception{
		LeaveEnCashmentDAO dao = new LeaveEnCashmentDAO();
		boolean list = dao.insertEnCashLeave(param);
		logger.debug("inside getEmpLeaveDetails BD ");
		return list;
	}
}

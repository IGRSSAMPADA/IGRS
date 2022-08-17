package com.wipro.igrs.conReport.bo;


/**
 * ===========================================================================
 * File           :   ConReportBO.java
 * Description    :   Represents the BO Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import java.util.ArrayList;

import com.wipro.igrs.conReport.dao.ConReportDAO;
import com.wipro.igrs.conReport.dto.ConReportDTO;


/**
 * @author Imran Shaik
 *
 */


public class ConReportBO {

	ConReportDAO dao = null;
	public ArrayList getFiYear() {
		dao = new ConReportDAO(); 
		return dao.getFiYear();
	}
	
	public ArrayList empDetails(String empId)
	 {
		dao = new ConReportDAO();
		return dao.empDetails(empId);
	 }

	public boolean saveEmaployeeComments(String[] args) {
		dao = new ConReportDAO();
		return dao.saveEmaployeeComments(args);
	}

	public ArrayList getEmpsList(String empId, String param) {
		dao = new ConReportDAO();
		return dao.getEmpsList(empId,param);
	}

	public ArrayList getConReportInfo(String conId) {
		dao = new ConReportDAO();
		return dao.getConReportInfo(conId);
	}
	
	public boolean updateEmaployeeComments(String[] args) {
		dao = new ConReportDAO();
		return dao.updateEmaployeeComments(args);
	}

	public boolean saveSupervisorComments(String[] args) {
		dao = new ConReportDAO();
		return dao.saveSupervisorComments(args);
	}

	public boolean saveReviewerComments(String[] args) {
		dao = new ConReportDAO();
		return dao.saveReviewerComments(args);
	}

	public ArrayList getSuperEditInfo(String conId) {
		dao = new ConReportDAO();
		return dao.getSuperEditInfo(conId);
	}

	public ArrayList getReviewEditInfo(String conId) {
		dao = new ConReportDAO();
		return dao.getReviewEditInfo(conId);
	}

	public ArrayList getEmpEditInfo(String conId) {
		dao = new ConReportDAO();
		return dao.getEmpEditInfo(conId);
	}

}

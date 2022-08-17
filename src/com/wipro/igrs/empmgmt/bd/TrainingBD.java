package com.wipro.igrs.empmgmt.bd;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.wipro.igrs.empmgmt.dao.TrainingDAO;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;
import com.wipro.igrs.empmgmt.dto.TrainingDTO;
import com.wipro.igrs.hrpayroll.hrpl.dto.LeaveDTO;

public class TrainingBD {
	TrainingDAO trainingDAO=null;
	private Logger logger = Logger.getLogger(TrainingBD.class);
	
	public TrainingBD() throws Exception{
		trainingDAO=new TrainingDAO();
	}
	/*public boolean submitTrining(TrainingDTO triningDTO,ArrayList employeeList) throws Exception{
		return trainingDAO.submitTraining(triningDTO,employeeList);
	}*/

	public String submitTrainingDetails(TrainingDTO trainingDTO,ArrayList employeeList,String userid)throws Exception{
		return trainingDAO.submitTrainingDetails(trainingDTO,employeeList,userid);
	}
	
	public ArrayList getEmployeeList(EmployeeDTO employeeDTO) throws Exception {
		
		logger.debug("in BD start ");
		
		String[] empId     =   new  String[1];
		empId[0] =  employeeDTO.getEmployeeNumber();
		ArrayList empDetails = trainingDAO.getEmployeeList(empId);
		ArrayList _empDetailslist = new ArrayList();
		for (int i = 0; i < empDetails.size(); i++) {
			ArrayList employee = (ArrayList)empDetails.get(i);
			if (empDetails != null) {
				EmployeeDTO employeeDTO1=new EmployeeDTO();
				employeeDTO1.setEmployeeName((String)employee.get(0));
				employeeDTO1.setEmployeeDesignation((String)employee.get(1));
				employeeDTO1.setPayScale((String)employee.get(2));
				employeeDTO1.setDateOfJoining((String)employee.get(3));
				employeeDTO1.setPlaceOfPosting((String)employee.get(4));
				employeeDTO1.setOfficalAddress((String)employee.get(5));
				employeeDTO1.setResidencalAddress((String)employee.get(6));
				employeeDTO1.setContactNumber((String)employee.get(7));
				employeeDTO1.setEmail((String)employee.get(8));
				_empDetailslist.add(employeeDTO1);
				
				
			}
		}
		return _empDetailslist;
	}

	//By praveenkumar k START
	public EmployeeDTO getEmployeeDetails(String employeeId) throws Exception {
		
		
		
		EmployeeDTO empDetails = trainingDAO.getEmployeeDetails(employeeId);
		
		
		return empDetails;
	}
	//END
	
	//By praveenkumar k START
	public EmployeeDTO getEmployeeSearch(String employeeId) throws Exception {
		
		logger.debug("in BD start ");
		
		EmployeeDTO empDetails = trainingDAO.getEmployeeSearch(employeeId);
//		logger.debug("empdetails in bd"+empDetails);
		
		
		return empDetails;
	}
	//END
	
}


package com.wipro.igrs.empmgmt.bd;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.wipro.igrs.empmgmt.dao.OfficalInfoDAO;
import com.wipro.igrs.empmgmt.dao.TransferDAO;
import com.wipro.igrs.empmgmt.dto.EmployeeDTO;
import com.wipro.igrs.empmgmt.dto.OfficalInfoDTO;
import com.wipro.igrs.empmgmt.dto.TransferDTO;

public class TransferBD {
TransferDAO transferDAO=null;

public TransferBD() throws Exception {
	transferDAO = new TransferDAO();
}
	
/*
public ArrayList getnewLocation(TransferDTO transferDTO) throws Exception {
	
	String[] empId     =   new  String[1];
	empId[0] =  transferDTO.getEmployeeId();
	ArrayList locationTypes = transferDAO.getnewLocation(empId);
	ArrayList locationList = new ArrayList();
	for (int i = 0; i < locationTypes.size(); i++) {
		ArrayList locations = (ArrayList) locationTypes.get(i);


		if (locationTypes != null) {

			TransferDTO transferDTO=new  TransferDTO();
			transferDTO.setEmployeeName((String) locations.get(0));
			transferDTO.setDesignation((String) locations.get(1));
			transferDTO.setOldLoc((String) locations.get(2));

			locationList.add(transferDTO);

		}
	}
	return locationList;

}*/
public ArrayList getnewLocation()throws Exception{
	ArrayList locationtypes=transferDAO.getnewLocation();
	
	ArrayList locationList=new ArrayList();
	if(locationtypes!=null){
			for(int i=0;i<locationtypes.size();i++){
				ArrayList locations=(ArrayList)locationtypes.get(i);
				
					TransferDTO transferDTO=new TransferDTO();
					transferDTO.setNewLoc((String)locations.get(0));
					transferDTO.setOfficeId((String)locations.get(1));
					locationList.add(transferDTO);
				
			}
	}
	
	return locationList;
}

public TransferDTO displayEmpDetails(TransferDTO transferDTO,HttpServletRequest request ) throws Exception {
	
	

	String[] empId     =   new  String[1];
	empId[0] =  transferDTO.getEmployeeId();
	ArrayList empDetails = transferDAO.displayEmpDetails(empId);
	
	ArrayList _empDetailslist = new ArrayList();
	TransferDTO transferDTO1=null;
	if(empDetails.size()==0){
	request.setAttribute("noData","noData");
		transferDTO1=new TransferDTO();
		transferDTO1.setEmployeeId("");
		transferDTO1.setOldLoc("");
		transferDTO1.setDesignation("");
		transferDTO1.setEmployeeName("");
		transferDTO1.setDepartment("");
		
		
	}else{
	
		for (int i = 0; i < empDetails.size(); i++) {
			ArrayList employee = (ArrayList)empDetails.get(i);
			if (empDetails != null) {
				transferDTO1=new TransferDTO();
				transferDTO1.setEmployeeName((String)employee.get(0));
				transferDTO1.setDesignation((String)employee.get(1));
				transferDTO1.setOldLoc((String)employee.get(2));
				transferDTO1.setDepartment((String)employee.get(3));
			//	_empDetailslist.add(transferDTO1);
				
				
			}
		}
	}
	return transferDTO1;
}

public boolean submitTransferDetails(TransferDTO transferDTO,String strUserId) throws Exception{
	return transferDAO.submitTransferDetails(transferDTO,strUserId);
}

public boolean submitIntraTransferDetails(TransferDTO transferDTO,String strUserId) throws Exception{
	return transferDAO.submitIntraTransferDetails(transferDTO,strUserId);
}

}

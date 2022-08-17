package com.wipro.igrs.conReport.bd;


/**
 * ===========================================================================
 * File           :   ConReportBD.java
 * Description    :   Represents the BD Class ECR
 * @author        :   Imran Shaik
 * Created Date   :   September 08, 2008
 * ===========================================================================
 */

import java.util.ArrayList;

import com.wipro.igrs.conReport.bo.ConReportBO;
import com.wipro.igrs.conReport.dto.ConReportDTO;
import com.wipro.igrs.conReport.dto.DataDto;
import com.wipro.igrs.conReport.form.ConReportForm;

/**
 * @author Imran Shaik
 *
 */
public class ConReportBD {
	ConReportBO conBo = null;

	//getting the details of an Employee
	public ConReportDTO getEmpDetails(String empId){
		
		conBo = new ConReportBO();
		ConReportDTO dto = new ConReportDTO();
		ArrayList list1 = new ArrayList();
		
			list1 = conBo.empDetails(empId);
		
		if(list1!=null)
			for(int i=0;i<list1.size();i++){
						ArrayList list =(ArrayList)list1.get(i);
						dto.setFirstName((String)list.get(0));
						dto.setMiddleName((String)list.get(1));
						dto.setLastName((String)list.get(2));
						dto.setDateOfJoin((String)list.get(3));
						dto.setDateOfBirth((String)list.get(4));
						String temp = (String)list.get(5);
						if(temp!=null)
							if(temp.equalsIgnoreCase("m"))
								dto.setGender("Male");
							else if(temp.equalsIgnoreCase("f"))
								dto.setGender("Female");
					}
		list1 = null;
		list1 = conBo.getFiYear();
		ArrayList list = new ArrayList();
		if(list1!=null)
				for(int i=0;i<list1.size();i++)
					{
					ArrayList list2 =(ArrayList)list1.get(i);
					DataDto Dto = new DataDto();
						Dto.setId((String)list2.get(0));
						Dto.setName((String)list2.get(1));
						list.add(Dto);
					}
		dto.setFiYear(list);
		return dto;
	}

	public boolean saveEmaployeeComments(String[] args) {
		conBo = new ConReportBO();
		return conBo.saveEmaployeeComments(args);
	}

	public ConReportDTO getEmpsList(String empId, String param) {
		conBo = new ConReportBO();
		ConReportDTO dto = new ConReportDTO();
		ArrayList list = new ArrayList();
		ArrayList list1 = conBo.getEmpsList(empId,param);
		if(list1!=null)
			for(int i=0;i<list1.size();i++)
				{
				ArrayList list2 =(ArrayList)list1.get(i);
				DataDto Dto = new DataDto();
					Dto.setId((String)list2.get(0));
					Dto.setName((String)list2.get(1));
					Dto.setFiYear((String)list2.get(2));
					Dto.setRepStatus((String)list2.get(3));
					list.add(Dto);
				}
	dto.setFiYear(list);
	return dto;
	}

	public ConReportDTO getConReportInfo(String conId) {
		conBo = new ConReportBO();
		ConReportDTO dto = new ConReportDTO();
		ArrayList list1 = new ArrayList();
		
		list1 = conBo.getConReportInfo(conId);
			if(list1!=null){
				ArrayList list =(ArrayList)list1.get(0);
				dto.setConId((String)list.get(0));
				dto.setEmpId((String)list.get(1));
				dto.setFiYearId((String)list.get(2));
				dto.setRepStatus((String)list.get(3));
				dto.setComments((String)list.get(4));
			}
			
		list1 = null;
		list1 = conBo.empDetails(dto.getEmpId());
			if(list1!=null){
						ArrayList list =(ArrayList)list1.get(0);
						dto.setFirstName((String)list.get(0));
						dto.setMiddleName((String)list.get(1));
						dto.setLastName((String)list.get(2));
						dto.setDateOfJoin((String)list.get(3));
						dto.setDateOfBirth((String)list.get(4));
						String temp = (String)list.get(5);
						if(temp!=null)
							if(temp.equalsIgnoreCase("m"))
								dto.setGender("Male");
							else if(temp.equalsIgnoreCase("f"))
								dto.setGender("Female");
					}
		return dto;
	}
	
	public boolean updateEmaployeeComments(String[] args) {
		conBo = new ConReportBO();
		return conBo.updateEmaployeeComments(args);
	}
	
	public boolean saveSupervisorComments(String[] args) {
		conBo = new ConReportBO();
		return conBo.saveSupervisorComments(args);
	}

	public boolean saveReviewerComments(String[] args) {
		conBo = new ConReportBO();
		return conBo.saveReviewerComments(args);
	}

	public ConReportForm getSuperEditInfo(String conId) {
		ConReportForm form = new ConReportForm();
		form .setDto(getConReportInfo(conId));
		conBo = new ConReportBO();
		ArrayList list1 = new ArrayList();
		list1 = conBo.getSuperEditInfo(conId);
			if(list1!=null){
				ArrayList list =(ArrayList)list1.get(0);
				form.setConId(conId);
				form.setRepStatus((String)list.get(0));
				form.setRemarks((String)list.get(1));
				form.setComments((String)list.get(2));
			}
		return form;
	}

	public ConReportForm getReviewEditInfo(String conId) {
		ConReportForm form = new ConReportForm();
		form .setDto(getConReportInfo(conId));
		conBo = new ConReportBO();
		ArrayList list1 = new ArrayList();
		list1 = conBo.getReviewEditInfo(conId);
			if(list1!=null){
				ArrayList list =(ArrayList)list1.get(0);
				form.setConId(conId);
				form.setRepStatus((String)list.get(0));
				form.setRemarks((String)list.get(1));
				form.setComments((String)list.get(2));
			}
		return form;
	}
	
	public ConReportForm getEmpEditInfo(String conId) {
		ConReportForm form = new ConReportForm();
		form .setDto(getConReportInfo(conId));
		conBo = new ConReportBO();
		ArrayList list1 = new ArrayList();
		list1 = conBo.getEmpEditInfo(conId);
			if(list1!=null){
				ArrayList list =(ArrayList)list1.get(0);
				form.setConId(conId);
				form.setRepStatus((String)list.get(0));
				form.setRemarks((String)list.get(1));
				form.setComments((String)list.get(2));
				form.setFiYear((String)list.get(3));
				
			}
		return form;
	}

}

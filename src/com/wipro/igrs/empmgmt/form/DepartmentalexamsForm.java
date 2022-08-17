/**
 * DepartmentalexamsForm.java
 */

package com.wipro.igrs.empmgmt.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsDTO;
import com.wipro.igrs.empmgmt.dto.DepartmentalExamsResultDTO;

/**
 * MyEclipse Struts Creation date: 06-04-2008
 * 
 * XDoclet definition:
 * 
 * @struts.form name="departmentalexamsForm"
 */
/**
 * @author jagadish Jun 4, 2008
 * 
 */
public class DepartmentalexamsForm extends BaseForm {
	/*
	 * Generated Methods
	 */

	private DepartmentalExamsDTO deptExamDTO = new DepartmentalExamsDTO();

	private String pageName;

	private String pageAction;

	private ArrayList deptexamList = new ArrayList();

	public void setExamResultArrDTO(int index, DepartmentalExamsResultDTO value) {

		for (; index >= deptexamList.size(); deptexamList
				.add(new DepartmentalExamsResultDTO()))
			;
		deptexamList.add(index, value);
	}

	public DepartmentalExamsResultDTO getExamResultArrDTO(int index) {
		for (; index >= deptexamList.size(); deptexamList
				.add(new DepartmentalExamsResultDTO()))
			;
		return (DepartmentalExamsResultDTO) deptexamList.get(index);
	}

	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the pageAction
	 */
	public String getPageAction() {
		return pageAction;
	}

	/**
	 * @param pageAction
	 *            the pageAction to set
	 */
	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the deptExamDTO
	 */
	public DepartmentalExamsDTO getDeptExamDTO() {
		return deptExamDTO;
	}

	/**
	 * @param deptExamDTO
	 *            the deptExamDTO to set
	 */
	public void setDeptExamDTO(DepartmentalExamsDTO deptExamDTO) {
		this.deptExamDTO = deptExamDTO;
	}

	/**
	 * @return the deptexamList
	 */
	public ArrayList getDeptexamList() {
		return deptexamList;
	}

	/**
	 * @param deptexamList
	 *            the deptexamList to set
	 */
	public void setDeptexamList(ArrayList deptexamList) {
		this.deptexamList = deptexamList;
	}
}
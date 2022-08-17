package com.wipro.igrs.functionDetailedHeadMap.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class FunctionMapDelete extends org.apache.struts.action.ActionForm {

	private String[] select;


	public String[] getSelect() {
	return select;
}

public void setSelect(String[] select) {
	this.select = select;
}
    public FunctionMapDelete () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
     
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
       return null;
    }


}
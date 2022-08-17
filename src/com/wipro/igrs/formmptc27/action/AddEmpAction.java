package com.wipro.igrs.formmptc27.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.formmptc27.bd.FormMPTC27BD;
import com.wipro.igrs.formmptc27.dto.FormMPTC27DTO;
import com.wipro.igrs.formmptc27.form.FormMPTC27Bean;

public class AddEmpAction extends BaseAction {
    

	FormMPTC27BD formMPTC27BD = new FormMPTC27BD();
	
    
    public AddEmpAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

    	FormMPTC27Bean bean = (FormMPTC27Bean)form;
    	//HttpSession session = request.getSession();
    	
    	String id = bean.getEmpId();
    	ArrayList allEmpDetails = new ArrayList();
    	
    	ArrayList restIds = bean.getAllEmpId();
    	for (int i = 0; i < restIds.size(); i++) {
    		if(restIds.get(i).equals(id)){
    			restIds.remove(i);
    		}
		}
    	bean.setAllEmpId(restIds);
    	
    	//ArrayList allIds = formMPTC27BD.getAllIds();
    	/*for (int i = 0; i < allIds.size(); i++) {
				if(!restIds.contains(allIds.get(i))){
					FormMPTC27DTO empDetails = formMPTC27BD.getEmpDetailsById((String)allIds.get(i));
					allEmpDetails.add(empDetails);
				}
    		
		}*/
    	
    	String[] selectList = bean.getSelectEmp();
    	for (int i = 0; i < selectList.length; i++) {
			System.out.println("hiiiiiiiiii"+selectList[i]);
		}
    	
    	for (int i = 0; i < selectList.length; i++) {
	
				FormMPTC27DTO empDetails = formMPTC27BD.getEmpDetailsById((String)selectList[i]);
				allEmpDetails.add(empDetails);
		}
    	ArrayList list = new ArrayList();
    	for (int i = 0; i < selectList.length; i++) {
			list.add(selectList[i]);
		}
    	bean.setSelectEmpList(list);
    	
    	request.setAttribute("allEmp", allEmpDetails);
    	
        return mapping.findForward("viewEmpPage");
    }

}
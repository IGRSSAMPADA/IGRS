package com.wipro.igrs.loginInternal.form;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.login.dto.MasterListDTO;


public class LoginForm extends BaseForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
    private String password;
    private String noAttempt;
    private String actionName; 
    private String language;
    private ArrayList listRole = new ArrayList();
    private MasterListDTO masterList = new MasterListDTO();
    
    ArrayList officeList = new ArrayList();
    
    

    
	public ArrayList getOfficeList() {
		return officeList;
	}
	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public MasterListDTO getMasterList() {
		return masterList;
	}
	public void setMasterList(MasterListDTO masterList) {
		this.masterList = masterList;
	}
	public ArrayList getListRole() {
		return listRole;
	}
	public void setListRole(ArrayList listRole) {
		this.listRole = listRole;
	}
	
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionName() {
		return actionName;
	}
	public LoginForm() {
    }
    /**
     * @webmethod
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @webmethod
     */
    public String getPassword() {
        return password;
    }
    public ActionErrors validate(ActionMapping mapping, 
                                 HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        /*
        String userId = this.userId;
        String password = this.password;
        String actionType = this.getActionType();
        if (actionType != null && actionType.length() > 0 &&
            actionType.equalsIgnoreCase(Constants.AUTHENTICATE)) {
            if (!Validations.checkBlankData(userId)) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("BLO0001"));
            } else if (!Validations.checkMaxLength(userId, 10)) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("BL00012"));
            } else if (!Validations.checkBlankData(password)) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("BLO0002"));
            } else if (!Validations.checkMaxLength(password, 20)) {
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("BL00013"));
            }
        }*/

        return errors;
    }
    /**
     * @webmethod
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @webmethod
     */
    public String getUserId() {
        return userId;
    }
	public String getNoAttempt() {
		return noAttempt;
	}
	public void setNoAttempt(String noAttempt) {
		this.noAttempt = noAttempt;
	}
}

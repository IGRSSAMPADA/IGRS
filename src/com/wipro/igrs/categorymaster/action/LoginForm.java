package com.wipro.igrs.categorymaster.action;



import javax.servlet.http.HttpServletRequest;

//import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;



public class LoginForm extends BaseForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
    private String password;
    private String noAttempt;
    private String actionName; 

    public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
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

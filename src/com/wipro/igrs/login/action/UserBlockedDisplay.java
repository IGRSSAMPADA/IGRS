package com.wipro.igrs.login.action;


import java.util.Enumeration;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.login.bd.ResetPasswordBD;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.util.PropertiesFileReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * 
 * @author Roopam Mehta
 *
 */
public class UserBlockedDisplay extends BaseAction {

	/**
	 * @author Roopam Mehta
	 */
//	private Logger logger = 
//		(Logger) Logger.getLogger(LogoutAction.class);
		
	/**
	 * @author Roopam Mehta
	 */
	public UserBlockedDisplay() {
    }
	/**
     * @author Roopam Mehta
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param session
     * @exception Exception
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, 
                                 HttpServletResponse response, 
                                 HttpSession session) throws Exception {

        String forwardTo = "success";
       
    
        return mapping.findForward(forwardTo);
    }
}

package com.wipro.igrs.login.action;


import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.login.bd.ResetPasswordBD;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.newreginit.constant.RegInitConstant;
import com.wipro.igrs.util.PropertiesFileReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.HTTPUtilities;
import org.owasp.esapi.User;


/**
 * 
 * @author Madan Mohan
 *
 */
public class LogoutAction extends BaseAction {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = 
		(Logger) Logger.getLogger(LogoutAction.class);
		
	/**
	 * @author Madan Mohan
	 */
	public LogoutAction() {
    }
	/**
     * @author Madan Mohan
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

        String forwardTo;
        String userTypeId=session.getAttribute("userTypeId").toString();
        
        if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)){
        	forwardTo = "loginInternal";
        }else{
        	forwardTo = "login";
        }
       
        String userId = (String) session.getAttribute("UserId");

        // Remove all the attributes from the session 
        Enumeration enumueration = session.getAttributeNames();
        while (enumueration.hasMoreElements()) {
            String attributeName = (String) enumueration.nextElement();
            session.removeAttribute(attributeName);
        }
        PropertiesFileReader pr = 
    		PropertiesFileReader.getInstance("resources.igrs");
       
        if(session.getAttribute("UserId") == null) {
        	ResetPasswordBD bd = new ResetPasswordBD();
        	bd.setUpdateLoginStatus(userId);
        }
       // session.invalidate();
        
    	
        if(userId != null) {
        	request.setAttribute(LoginConstant.USER_LOGGEDOUT, userId
        		+" "+ pr.getValue(LoginConstant.USER_LOGGEDOUT));
        }else if(session.getAttribute("UserId") == null){
        	request.setAttribute(LoginConstant.USER_LOGGEDOUT, 
            		pr.getValue(LoginConstant.SESSION_EXPIRED));
        }
        
       
        
        request.removeAttribute(LoginConstant.USER_ERROR);
        request.removeAttribute(LoginConstant.USER_ERROR_LIST);
        

		try {
			
					
				for(Enumeration e = session.getAttributeNames(); e.hasMoreElements();)
				{
					 String attribName = (String)e.nextElement();
					 
						session.removeAttribute(attribName);
					
				}
			
			
		
		} catch (Exception e) {
			logger.debug(e.getMessage(),e);
		}
	
	
		 session.invalidate();
        logger.debug("Inside logout:-"+userId);
        return mapping.findForward(forwardTo);
    }
    
    
    public void logout() {

    	ESAPI.httpUtilities().killCookie( ESAPI.currentRequest(), ESAPI.currentResponse(), HTTPUtilities.REMEMBER_TOKEN_COOKIE_NAME );

    	

    	HttpSession session = ESAPI.currentRequest().getSession(false);

    	if (session != null) {

    		
    		session.invalidate();

    	}

    	ESAPI.httpUtilities().killCookie(ESAPI.currentRequest(), ESAPI.currentResponse(), "JSESSIONID");

    
    	logger.debug( "Logout successful" );

    	ESAPI.authenticator().setCurrentUser(User.ANONYMOUS);

    }
}



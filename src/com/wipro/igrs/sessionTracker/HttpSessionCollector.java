package com.wipro.igrs.sessionTracker;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wipro.igrs.common.dao.CommonDAO;

public class HttpSessionCollector implements HttpSessionListener{
	 private static final Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	    @Override
	    public void sessionCreated(HttpSessionEvent event) {
	        HttpSession session = event.getSession();
	        sessions.put(session.getId(), session);
	        //session.setMaxInactiveInterval(25);
	        System.out.println("size of session is "+sessions.size());
	    }


	    @Override
	    public void sessionDestroyed(HttpSessionEvent event) {
	    	try {
				CommonDAO dao = new CommonDAO();
				String userId = (String)event.getSession().getAttribute("UserId");
				dao.updateLogoutDetails(event.getSession().getId(), userId);
				//event.getSession().removeAttribute("mainUserList");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        sessions.remove(event.getSession().getId());
	    }

	    public static HttpSession find(String sessionId) {
	        return sessions.get(sessionId);
	    }


		
			
		}


	
			
		



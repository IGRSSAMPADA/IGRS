package com.wipro.igrs.csrf;

import javax.servlet.http.HttpServletRequest;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.User;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.reference.DefaultEncoder;

public class CSRF {

	final static String CSRF_TOKEN_NAME = "ctoken";

	
	public String addCSRFToken() {

	
		String	csrfToken = ESAPI.randomizer().getRandomString(8, DefaultEncoder.CHAR_ALPHANUMERICS);

			return csrfToken;

		
	

	}

	

	public String getCSRFToken() {

		User user = ESAPI.authenticator().getCurrentUser();

		if (user == null) return null;

		return user.getCSRFToken();

	}
	
	public Boolean verifyCSRFToken(HttpServletRequest request,String token) throws IntrusionException {

		User user = ESAPI.authenticator().getCurrentUser();


	

		if( token == null ) {

			return false;

		}

	

		else	if ( !user.getCSRFToken().equals( token ) ) {

			throw new IntrusionException("Authentication failed", "Possibly forged HTTP request without proper CSRF token detected");
			
		}
		else
		{
			return true;
		}

	}
	
	
}
